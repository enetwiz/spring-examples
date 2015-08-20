Modul - HibernateRelationship
=============================

Modul prezentuje przyklad relacji (OneToMany, ManyToMany) miedzy tabelami w bazie danych zaimplementowany za pomoca Hibernate ORM


Lista plikow (wg waznosci)
--------------------------

 * structure.sql - struktura tabeli danych, wymagana do utrwalenia danych w bazie danych
 * pom.xml - plik konfiguracyjny buildera projektu [Maven](http://4programmers.net/Java/Maven#id-A-moe-by-tak-Maven); plik ten zawiera 
   odnosniki do zaleznosci (sekcja "dependencies"), czyli bibliotek zaleznych, potrzebnych do budowy aplikacji. M.in. Maven pilnuje, aby 
   zaladowana zostala biblioteka Spring'a w okreslonej w pliku POM wersji
 * AppConfig.java - klasa konfiguracyjna Spring'a, ktory odgrywa duze znaczenie przy AOP i wstrzykiwaniu zaleznosci (DI)
 * CompanyEntity.java - przykladowy, prosty obiekt Javy (POJO/encja/model), ktory stanowi obiektowe odwzorowanie tabeli "company" w bazie danych
 * EmployeeEntity.java - przykladowy, prosty obiekt Javy (POJO/encja/model), ktory stanowi obiektowe odwzorowanie tabeli "employee" w bazie danych
 * UserEntity.java - przykladowy, prosty obiekt Javy (POJO/encja/model), ktory stanowi obiektowe odwzorowanie tabeli "muser" w bazie danych
 * GroupEntity.java - przykladowy, prosty obiekt Javy (POJO/encja/model), ktory stanowi obiektowe odwzorowanie tabeli "mgroup" w bazie danych
 * OneToMany.java - plik wykonawczy (zawiera metode main()), ktory pozwala uruchomic aplikacje w kontekscie Springa, w celu pokazania relacji typu 
   "One to Many"
 * ManyToMany.java - plik wykonawczy (zawiera metode main()), ktory pozwala uruchomic aplikacje w kontekscie Springa, w celu pokazania relacji 
   typu "Many to Many"


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

**structure.sql**

Plik zawiera strukture bazy danych potrzebna do uruchomienia wszystkich przykladow relacji. To na co nalezy zwrocic uwage to:

 * zapis: ``CONSTRAINT `FK_company_id` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)`` - okresla nam klucz obcy (`FOREIGN KEY`) czyli pole, 
   ktore stanowi powiazanie pomiedzy jedna, a druga tabela w bazie danych. W tym przypadku klucz obcy stanowi pole "company_id" tabeli "employee". 
   Pole "company_id" z kolei przechowuje referencje do konkretnego rekordu (uzywajac pola "id") w tabeli "company". 
   W strukturze tabeli "company" brakuje definicji klucza obcego co sprawia, ze mamy do czynienia z relacja typu "One to many" - mowiac obrazowo: 
   JEDNA firma moze miec WIELU pracownikow.
 * tabele muser i mgroup maja dodany prefix "m" - poniewaz nazwy USER i GROUP sa elementami skladni SQL (SQL-99) - brak prefixu spowodowalby blad 
   skladni SQL w chwili wyslanai zapytania do bazy
 * tabela "user_group" - posiada dwa klucze obce (a tabele "muser", "mgroup" - nie posiadaja zadnego klucza obcego) - wynikiem tego zapisu jest 
   utworzenie relacji typu "Many to Many" - czyli: Uzytkownik moze nalezec do WIELU grup, a do grupy moze nalezec WIELU uzytkownikow.


**AppConfig.java**

Zawiera konfiguracje kontekstu Springa.

Anotacja @Configuration - oznacza, ze mamy do czynienia z plikiem konfiguracyjnym (odpowiednik konfiguracyjnego pliku *.xml)

Anotacja @EnableTransactionManagement - jest odpowiedzialna za zarzadzanie transakcjami poprzez anotacje; anotacja ta jest wymagana - jej brak 
powoduje wyjatek o tresci: "HibernateException: Could not obtain transaction-synchronized Session for current thread"

Anotacja @ComponentScan("..") - wyszukuje/skanuje klasy oznaczone anotacja @Component w przestrzeni nazw "..". Po odnalezieniu komponentu 
zostaje utworzony obiekt o id zgodnym z nazwa klasy. Przykladowo jezeli Spring odnajdzie komponent klasy HelloComponent() wowczas referencja/id 
tego komponentu nazywac sie bedzie "helloComponent".

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


**CompanyEntity.java**

Typowy prosty obiekt Javy (POJO), ktory stanowi obiektowe odwzorowanie pojedynczego rekordu z tabeli o nazwie "company" 
(patrz plik: structure.sql). Klasa tego typu nazywana jest rowniez encja/modelem. W celu rozpoznania jej przez Hibernate zostala oznaczona 
anotacja @Entity. 
Kolejna anotacja @Table(name = "company") wskazuje na to, ze encja stanowi rekord tabeli "company".

Wewnatrz encji znajduja sie pola symbolizujace kolumny ww tabeli. Przez co kazde z pol jest oznaczone anotacjami, ktore pozwalaja frameworkowi 
Hibernate okreslic zachowania wzgledem kazdego z nich:

 * @Id - okresla, ze Hibernate ma do czynienia z polem typu PRIMARY; Conajmniej jedno pole musi zawierac encje @Id - w przeciwnym wypadku 
   wystapi wyjatek: AnnotationException: No identifier specified for entity
 * @GeneratedValue - okresla, ze pole ma byc wypelniane automatycznie (tj. kazdy rekord bedzie mial uniklany, kolejny numer) zgodnie z 
   mechanizmem AUTO_INCREMENT
 * @Column(nullable = false, length = 50) - encja ta okresla, ze Hibernate ma do czynienia z typowa kolumna w bazie danych. Dodatkowo zawiera ona 
   wlasnosci takie jak:
    * nullable - okresla czy pole jest wymagane (nie moze byc puste)
    * length - okresla maksymalna ilosc znakow (dlugosc pola), ktore mozna wprowadzic
 * @OneToMany(mappedBy = "company") - encja okresla, ze biezacy obiekt/rekord CompanyEntity() moze zawierac wiele rekordow typu 
   EmployeeEntity(). 
   Z kolei parametr "mappedBy" wskazuje na wlasciwosc encji EmployeeEntity(), która jest wlascicielem relacji. Obiekt EmployeeEntity() jest 
   wlascicielem relacji, poniewaz to on zawiera klucz obcy w swojej wlasciwosci "company". (patrz plik: structure.sql)


**EmployeeEntity.java**

Stanowi kolejna encje (tj. zmapowany rekord bazy danych), ktory zawiera dodatkowe anotacje:

 * @ManyToOne - okresla, ze wiele encji EmployeeEntity() moze nalezec do encji CompanyEntity()
 * @JoinColumn(name = "company_id", nullable = false) - jest ekwiwalentem encji @Column z tym, ze stosuje sie ja w przypadku pol bedacych 
   kluczem obcym. Parametr "name" okresla nazwe pola klucza obcego w bazie danych, z kolei parametr "nullable" okresla czy pole to jest wymagane.


**UserEntity.java**

Kolejna encja (tj. zmapowany rekord bazy danych), ktory zawiera dodatkowe anotacje:

 * @ManyToMany( mappedBy = "users" ) - okresla, ze encja UserEntity() moze nalezec do WIELU encji GroupEntity(). Z kolei parametr "mappedBy" 
   wskazuje na wlasciwosc encji GroupEntity(), w ktorej moze zostac zapisane wielu uzytkownikow (dlatego obiektem wlasnosci "users" jest kolekcja).

Warto zwrocic uwage na metode addGroup(), ktora jest zwyklym aliasem - wynika to m.in. z faktu, ze wlasnosc "groups" jest obiektem kolekcji - 
co powoduje, ze operacje mozna wykonywac przez wywolanie metod kolekcji na tym obiekcie. Przykladowo metode addGroup() mozna zastapic wywolaniem 
UserEntity.getGroups().add(). Co za tym idzie - nie musimy definiowac zadnych dodatkowych metod dla kolekcji, poniewaz obiekt ten posiada wlasne 
metody dostepowe.

**GroupEntity.java**

Kolejna encja (tj. zmapowany rekord bazy danych), ktory zawiera dodatkowe anotacje:

 * @ManyToMany - okresla, ze encja GroupEntity() moze zawierac WIELE encji UserEntity()
 * @JoinTable( name = "user_group", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id") ) - 
   jest ekwiwalentem wywolania INNER JOIN w jezyku SQL, czyli chodzi o korzystanie z danych tabeli okreslonej parametrem "name" w celu 
   odnalezienia wszystkich rekordow encji UserEntity(), powiazanych z aktualna encja GroupEntity(). W celu dokladego okreslenia powiazan 
   typu MTM nalezy rowniez okreslic pola bedace kluczami obcymi w ramach tabeli "user_group". Do tego celu sluza parametry 
   "joinColumns" oraz "inverseJoinColumns". Pierwszy z nich ("joinColumns") okresla nam nazwe pola przechowujacego referencje do encji typu 
   GroupEntity(), drugi z kolei wskazuje gdzie szukac referencji dla wszystkich obiektow typu UserEntity().

Warto zwrocic uwage na brak anotacji @JoinTable w encji UserEntity() - wlasciwie, skad encja "wie", ze ma skorzystac z tabeli "user_group"? 
Otoz jest to mozliwe dzieki zastosowaniu parametru "mappedBy" w obiekcie UserEntity(). W przypadku relacji zmapowanych obiektowo ww parametr 
zezwala na pobranie wszystkich ustawien/parametrow zdefiniowanych w polu "users" obiektu bedacego druga strona relacji (druga strona relacji dla 
UserEntity() jest wlasnie encja GroupEntity()). Reasumujac encje komunikuja sie miedzy soba ograniczajac do minimum ilosc kodu potrzebna do 
opisania relacji.
   

**OneToMany.java**

Klasa ta zawieraja statyczna metode glowna: "main()", dzieki czemu program po kompilacji moze zostac uruchomiony.
W metodzie glownej tworzony jest kontekst Springa o nazwie "AnnotationConfigApplicationContext" - ten kontekst jest stosowany, gdy aplikacja ma 
korzystac z ustawien zdefiniowanych w anotacjach innych klas (np. klasy konfiguracyjnej AppConfig.java). 
Co wazne - Spring posiada rozne konteksty (tym samym nie jestesmy skazani tylko na anotacje jako podstawe konfiguracji frameworka).

Aby umozliwic zapis danych w relacji "One to Many" zamiast obiektu DAO mozna bezposrednio odwolac sie do biezacej sesji Hibernate poleceniem:

```java
SessionFactory sessionFactory = context.getBean( SessionFactory.class );
Session session = sessionFactory.openSession();
```

Metoda openSession() otwiera nowa sesje Hibernate, co w efekcie pozwala na uzycie transakcji lub operacji odczytu/zapisu danych z bazy. 
Kolejnym etapem jest otwarcie nowej transakcji:

```java
session.beginTransaction();
```

utworzenie nowych rekordow/encji, ktore maja podlegac zapisowi do bazy:

```java
CompanyEntity company1 = new CompanyEntity();
(...)
company1.getEmployees().add( c1Employee1 );
```

Po zakonczeniu pracy z encja mozna ja zapisac w ten sposob:

```java
session.saveOrUpdate( company1 );
```

Ostatecznie aby zatwierdzic operacje i dokonac utrwalania danych w bazie nalezy wywolac metode:

```java
session.getTransaction().commit();
```

Powyzsze wywolanie definitywnie konczy transakcje zapisujac wszystkie encje (plus ich powiazania) w bazie danych.


**ManyToMany.java**

Klasa ta zawieraja statyczna metode glowna: "main()", dzieki czemu program po kompilacji moze zostac uruchomiony.
W metodzie glownej tworzony jest kontekst Springa o nazwie "AnnotationConfigApplicationContext" - ten kontekst jest stosowany, gdy aplikacja ma 
korzystac z ustawien zdefiniowanych w anotacjach innych klas (np. klasy konfiguracyjnej AppConfig.java). 
Co wazne - Spring posiada rozne konteksty (tym samym nie jestesmy skazani tylko na anotacje jako podstawe konfiguracji frameworka).

Aby umozliwic zapis danych w relacji "Many to Many" zamiast obiektu DAO mozna bezposrednio odwolac sie do biezacej sesji Hibernate poleceniem:

```java
SessionFactory sessionFactory = context.getBean( SessionFactory.class );
Session session = sessionFactory.openSession();
```

Metoda openSession() otwiera nowa sesje Hibernate, co w efekcie pozwala na uzycie transakcji lub operacji odczytu/zapisu danych z bazy. 
Kolejnym etapem jest otwarcie nowej transakcji:

```java
session.beginTransaction();
```

utworzenie nowych rekordow/encji, ktore maja podlegac zapisowi do bazy:

```java
GroupEntity groupAdmin = new GroupEntity();
(...)
groupGuest.addUser( guestUser1 );
```

Po zakonczeniu pracy z encjami mozna je zapisac w ponizszy sposob:

```java
session.saveOrUpdate( groupAdmin )
```

Ostatecznie aby zatwierdzic operacje i dokonac utrwalania danych w bazie nalezy wywolac metode:

```java
session.getTransaction().commit();
```

Powyzsze wywolanie definitywnie konczy transakcje zapisujac wszystkie encje (plus ich powiazania) w bazie danych.