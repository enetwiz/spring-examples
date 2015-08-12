Modul - HibernateUsage
==================

Modul prezentuje przyklad implementacji ORM'a Hibernate 4.x + MySQL w srodowisku Spring Framework


Lista plikow (wg waznosci)
--------------------------

 * structure.sql - struktura tabeli danych, wymagana do utrwalenia danych w bazie danych
 * pom.xml - plik konfiguracyjny buildera projektu [Maven](http://4programmers.net/Java/Maven#id-A-moe-by-tak-Maven); plik ten zawiera 
   odnosniki do zaleznosci (sekcja "dependencies"), czyli bibliotek zaleznych, potrzebnych do budowy aplikacji. M.in. Maven pilnuje, aby 
   zaladowana zostala biblioteka Spring'a w okreslonej w pliku POM wersji
 * AppConfig.java - klasa konfiguracyjna Spring'a, ktory odgrywa duze znaczenie przy AOP i wstrzykiwaniu zaleznosci (DI)
 * HibernateUsage.java - serce aplikacji, czyli plik wykonawczy (zawiera metode main()), ktory pozwala uruchomic aplikacje w kontekscie Springa
 * ExampleEntity.java - przykladowy, prosty obiekt Javy (POJO), ktory stanowi obiektowe odwzorowanie tabeli w bazie danych (tzw. encja/model)
 * ExampleEntityDao.java - obiekt dostepu do danych (Data Access Object, DAO, repozytorium), dzieki ktoremu dokonujemy roznych operacji na danych 
   jak np. dodawanie, usuwanie rekordow itp


Wymagane zaleznosci
-------------------

Aby aplikacja funkcjonowala nalezy zaladowac potrzebne biblioteki, w odpowiednich wersjach:

 * spring-context - podstawowa biblioteka kontekstow Springa
 * mysql-connector-java - sterownik typu JDBC umozliwiajacy nawiazanie polaczenia z baza MySQL
 * hibernate-core - biblioteka frameworka ORM umozliwiajaca zarzadzanie danymi z bazy danych
 * spring-orm - projekt springa umozliwiajacy wspolprace z popularnymi frameworkami ORM oraz wspomagajacy proces mapowania obiektowo-relacyjnego
 * commons-dbcp2 - biblioteka wspomagajaca proces tworzenia tzw. puli polaczen, bez ktorych nie moglibysmy m.in. utrzymac stalej sesji polaczenia 
   z baza danych

Inne wymagania
--------------

 * nalezy miec zainstalowany (i uruchomniony) serwer MySQL
 * nalezy utworzyc baze o nazwie "spring" i zaimportowac do niej strukture z pliku: structure.sql


Opis dzialania
--------------

**AppConfig.java**

Zawiera konfiguracje kontekstu Springa.

Anotacja @Configuration - oznacza, ze mamy do czynienia z plikiem konfiguracyjnym (odpowiednik konfiguracyjnego pliku *.xml)

Anotacja @EnableTransactionManagement - jest odpowiedzialna za zarzadzanie transakcjami poprzez anotacje; anotacja ta jest wymagana - jej brak 
powoduje wyjatek o tresci: "HibernateException: Could not obtain transaction-synchronized Session for current thread"

Anotacja @ComponentScan("com.enetwiz.helloworld") - wyszukuje/skanuje klasy oznaczone anotacja @Component w przestrzeni nazw 
"com.enetwiz.helloworld". Po odnalezieniu komponentu zostaje utworzony obiekt o id zgodnym z nazwa klasy. Przykladowo jezeli Spring 
odnajdzie komponent klasy HelloComponent() wowczas referencja/id tego komponentu nazywac sie bedzie "helloComponent".

Anotacja @Bean nad metoda - oznacza, ze kontekst Springa utworzy obiekt POJO o referencji/id zgodny z nazwa metody np.

*
@Bean
public HelloBean helloBean() {
    return new HelloBean();
}
*

oznacza to samo co:

*HelloBean helloBean = new HelloBean();*

Jedna z pierwszych rzeczy, ktora wykonuje plik konfiguracyjny w przypadku korzystania z bazy danych z pomoca Hibernate jest inicjacja sesji 
polaczenia ( metoda sessionFactory() ) oraz konfiguracja dostepu do zrodla danych ( metoda dataSource() ) - w tym przypadku do bazy MySQL. 
W metodzie dataSource() mozna skonfigurowac szczegoly polaczenia takie jak: 

 * adres dostepu do zrodla danych ("jdbc:mysql://localhost:3306/spring"), na ktory sklada sie m.in:
    * nazwa zrodla/sterownika JDBC -> mysql
    * nazwa bazy danych, do ktorej chcemy sie podlaczyc -> /spring
 * dane uwierzytelniajace jak: login ( setUsername(..) ) i haslo ( setPassword(..) )

Nalezy pamietac, ze nie mozna polaczyc sie do zrodla danych bez odpowiedniego sterownika znajdujacego sie na sciezce klas - stad tez w pliku 
POM mamy zostala dodana zaleznosc "mysql-connector-java".


Wracajac do metody sessionFactory() - jest ona odpowiedzialna za zainicjowanie sesji polaczenia. Sesja w tym przypadku jest odpowiedzialna za 
podtrzymywanie polaczenia z baza danych w calym cyklu zycia aplikacji (BTW - nie jest to jedyne zadanie sesji!).

Powyzsza metoda dodatkowo korzysta z metody prywatnej o nazwie: getHibernateProperties() - jak sama nazwa wskazuje - odpowiada ona za 
konfiguracje samego frameworka Hibernate:

 * wlasciwosc: hibernate.show_sql - pozwala na wyswietlanie zapytan SQL (np. select this_.code from true.employee this_ where this_.code=?)  
   bezposrednio konsoli podczas dzialania aplikacji
 * wlasciwosc: hibernate.dialect - hibernate bierze pod uwage roznice pomiedzy implementacjami SQL w roznych bazach danych, stad nalezy podac 
   dialekt, ktorym powinien sie poslugiwac Hibernate podczas komunikacji z baza
 
Kolejna wazna metoda jest transactionManager(), ktorej zadaniem jest implementacja pojedynczej sesji Hibernate w ramach dzialania glownego watku 
aplikacji. Bez niej mozemy spodziewac sie wyjatku o tresci: 
"HibernateException: Could not obtain transaction-synchronized Session for current thread"


**ExampleEntity.java**

Typowy prosty obiekt Javy (POJO), ktory stanowi obiektowe odwzorowanie pojedynczego rekordu z tabeli o nazwie entities 
(patrz plik: structure.sql). Klasa tego typu nazywana jest rowniez encja/modelem. W celu rozpoznania jej przez Hibernate zostala oznaczony 
anotacja @Entity. 
Kolejna encja @Table(name = "entities") wskazuje na to, ze obiekt ExampleEntity() stanowi rekord tabeli "entities".

Wewnatrz encji znajduja sie pola symbolizujace kolumny tabeli "entities". Kazde z pol jest oznaczone anotacjami, ktore pozwalaja frameworkowi 
Hibernate okreslic zachowania wzgledem kazdego z nich:

 * @Id - okresla, ze Hibernate ma do czynienia z polem typu PRIMARY; Conajmniej jedno pole musi zawierac encje @Id - w przeciwnym wypadku 
   wystapi wyjatek: AnnotationException: No identifier specified for entity
 * @GeneratedValue - okresla, ze pole ma byc wypelniane automatycznie (tj. kazdy rekord bedzie mial uniklany, kolejny numer) zgodnie z 
   mechanizmem AUTO_INCREMENT
 * @Column(nullable = false, length = 50) - encja ta okresla, ze Hibernate ma do czynienia z typowa kolumna w bazie danych. Dodatkowo zawiera ona 
   wlasnosci takie jak:
    * nullable - okresla czy pole jest wymagane (nie moze byc puste)
    * length - okresla maksymalna ilosc znakow (dlugosc pola), ktore mozna wprowadzic


**ExampleEntityDao.java**

Klasa ExampleEntityDao, stanowi tzw. DataAccessObject (DAO), nazywany inaczej repozytorium. Glownym zadaniem klasy jest wykonywanie operacji na 
bazie danych (np. zapisz, odczyt rekordow) przy uzyciu encji ExampleEntity. Dobra praktyka jest to, aby klase repozytorium oznaczac jako 
@Repository - dzieki takim zachowaniom mozemy zbudowac wielowarstwowy podzial aplikacji. Podzial taki ma znaczenie w chwili, gdy nasza aplikacja 
staje sie coraz wieksza - latwiej jest wtedy ogarnac zorganizowana strukture plikow.

Do obiektu DAO zostaje rowniez wstrzyknieta (dzieki DI) sesja Hibernate za pomoca anotacji @Autowired nad wlasnoscia "sessionFactory".


**HibernateUsage.java**

Klasa ta zawieraja statyczna metode glowna: "main()", dzieki czemu program po kompilacji moze zostac uruchomiony.
W metodzie glownej tworzony jest kontekst Springa o nazwie "AnnotationConfigApplicationContext" - ten kontekst jest stosowany, gdy aplikacja ma 
korzystac z ustawien zdefiniowanych w anotacjach innych klas (np. klasy konfiguracyjnej AppConfig.java). 
Co wazne - Spring posiada rozne konteksty (tym samym nie jestesmy skazani tylko na anotacje jako podstawe konfiguracji frameworka).

Dzialanie przykladowej aplikacji Hibernate jest podzielone na kilka etapow:

 * utworzenie obiektu encji i nadanie mu przykladowych danych w wierszu:
   
   ExampleEntity exampleEntity = new ExampleEntity();
 * zapis nowego rekordu (ExampleEntity) za posrednictwem repozytorium:
   
   exampleEntityDAO.saveOrUpdate( exampleEntity );
 * odczyt dodanego rekordu/rekordow - rozniez za posrednictwem metody z repozytorium:
   
   for (ExampleEntity entity : exampleEntityDAO.list()) { .. }