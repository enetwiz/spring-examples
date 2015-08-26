Modul - HibernateInheritance
============================

Modul prezentuje przyklady dziedziczenia w ramach encji tj. jak odwzorowac dziedziczenie w bazie danych.


Lista plikow (wg waznosci)
--------------------------

 * pom.xml - plik konfiguracyjny buildera projektu [Maven](http://4programmers.net/Java/Maven#id-A-moe-by-tak-Maven); plik ten zawiera 
   odnosniki do zaleznosci (sekcja "dependencies"), czyli bibliotek zaleznych, potrzebnych do budowy aplikacji. M.in. Maven pilnuje, aby 
   zaladowana zostala biblioteka Spring'a w okreslonej w pliku POM wersji
 * AppConfig.java - klasa konfiguracyjna Spring'a, ktory odgrywa duze znaczenie przy AOP i wstrzykiwaniu zaleznosci (DI)
 * AnimalEntity.java - encja/model - obiekt ten stanowi abstrakcyjna nadklase dla innych encji
 * ReptileEntity.java - encja/model, ktory stanowi podklase klasy "AnimalEntity" oraz obiektowe odwzorowanie tabeli "reptile" w bazie danych
 * BirdEntity.java - encja/model, ktory stanowi podklase klasy "AnimalEntity" oraz obiektowe odwzorowanie tabeli "bird" w bazie danych
 * PhoneEntity.java - encja/model - obiekt ten stanowi abstrakcyjna nadklase dla innych encji
 * CellphoneEntity.java - encja/model, ktory stanowi podklase klasy "PhoneEntity" oraz obiektowe odwzorowanie tabeli "cellphone" w bazie danych
 * SmartphoneEntity.java - encja/model, ktory stanowi podklase klasy "PhoneEntity" oraz obiektowe odwzorowanie tabeli "smartphone" w bazie danych
 * VehicleEntity.java - encja/model - obiekt ten stanowi abstrakcyjna nadklase dla innych encji oraz zawiera obiektowe odwzorowanie tabeli 
   "vehicle" w bazie danych
 * MotorcycleEntity.java - encja/model, ktory stanowi podklase klasy "VehicleEntity" - encja ta nie zawiera bezposredniego odwzorowania w 
   bazie danych
 * TruckEntity.java - encja/model, ktory stanowi podklase klasy "VehicleEntity" - encja ta nie zawiera bezposredniego odwzorowania w 
   bazie danych
 * HibernateInheritance.java - plik wykonawczy (zawiera metode main()), ktory pozwala uruchomic aplikacje w kontekscie Springa, w celu 
   przedstawienia roznych rodzajow dziedziczenia encji w bazie danych


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
 * nalezy utworzyc baze o nazwie "spring"


Opis dzialania
--------------


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
 * wlasciwosc: hibernate.hbm2ddl.auto z ustawieniem "create-drop" - oznacza, ze Hibernate przy uruchomieniu aplikacji musi usunac schemat 
   bazy danych, a nastepnie utworzyc nowy bazujac na strukturze wszystkich dostepnych encji (dzieki tej wlasciwosci nie potrzebujemy pliku ze 
   struktura bazy np. structure.sql)

Kolejna wazna metoda jest transactionManager(), ktorej zadaniem jest implementacja pojedynczej sesji Hibernate w ramach dzialania glownego watku 
aplikacji. Bez niej mozemy spodziewac sie wyjatku o tresci: 
"HibernateException: Could not obtain transaction-synchronized Session for current thread"


**AnimalEntity.java**

Klasa ta stanowi encje abstrakcyjna, po ktorej dziedzicza inne encje standardowe. Jednym slowem jest to klasa o znaczeniu najbardziej ogolnym, 
z wlasnosci ktorej korzystaja inne encje. To wlasnie w klasie abstrakcyjnej okresla sie sposob w odwzorowania dziedziczenia 
(co warto zapamietac: dostepne sa trzy sposoby, stad w projekcie mamy 3 encje abstrakcyjne, gdzie kazda prezentuje odrebny sposob odwzorowania 
dziedziczenia).

W celu rozpoznania klasy przez Hibernate zostala ona oznaczona anotacja @Entity. 
Kolejna anotacje stanowi @Inheritance, ktora zawiera parametr "strategy" ustawiony na typ: InheritanceType.TABLE_PER_CLASS. Typ 
ten oznacza to, ze kazda nieabstrakcyjna klasy/encja bedzie miala swoj odpowiadnik w postaci tabeli w bazie danych. Mowiac obrazowo, gdy z 
abstrakcyjnej encji dziedzicza dwie kolejne typowe encje, wowczas powstana dwie tabele w bazie danych, ktore beda zawieraly zarowno pola encji 
jak i abstrakcyjnej encji (tj. nadklasy).
Jest to podejscie najprostsze jednakze pozbawione jakiegokolwiek związku między klasami dziedziczącymi, dlatego tez metoda ta nie jest polecana. 

Wewnatrz encji znajduja sie pola symbolizujace kolumny danych. Kazde z pol jest oznaczone anotacjami, ktore pozwalaja frameworkowi 
Hibernate okreslic ich zachowania:

 * @Id - okresla, ze Hibernate ma do czynienia z polem typu PRIMARY; Conajmniej jedno pole musi zawierac encje @Id - w przeciwnym wypadku 
   wystapi wyjatek: AnnotationException: No identifier specified for entity
 * @GeneratedValue - okresla, ze pole ma byc wypelniane automatycznie (tj. kazdy rekord bedzie mial uniklany, kolejny numer) zgodnie z 
   mechanizmem AUTO_INCREMENT. Dodatkowo w przypadku tego typu dziedziczenia (tj. InheritanceType.TABLE_PER_CLASS) nalezy ustawic strategie 
   generowania kluczy na typ "GenerationType.TABLE" - oznacza, to ze pomimo tego iz kazda (nieabstrakcyjna) encja bedzie posiadala wlasna tabele 
   to i tak kolejne numery kluczy pierwotnych beda nadawane w taki sposob jak gdyby wszystkie encje stanowily jedna wspolna tabele. Innymi slowy 
   jezeli dodamy rekord ReptileEntity to otrzyma on ID = 1, natomiast dodajac rekord innej encji np. BirdEntity otrzyma on juz ID = 2.
   Brak ustawienia strategii generowania kluczy na GenerationType.TABLE bedzie skutkowalo wyjatkiem: 
   NonUniqueObjectException: A different object with the same identifier value was already associated with the session
 * @Column - encja ta okresla, ze Hibernate ma do czynienia z typowa kolumna w bazie danych


**ReptileEntity.java** i **BirdEntity.java** 

Stanowia encje (tj. zmapowane rekordy bazy danych), dziedziczace z encji abstrakcyjnej (tj. nadklasy). 
Roznica pomiedzy tymi encjami polega na ich dodatkowych, uniklanych wlasnosciach np. encja BirdEntity posiada wlasnosc "beak" (dziob), 
gdy encja ReptileEntity juz jej nie posiada (gady nie maja dziobow). Mimo wszystko obie encje naleza do abstrakcyjnej grupy AnimalEnity i ich 
wspolna wlasnocia sa konczyny (legs) - wlasnosc ta pochodzi z wspolnej nadklasy.

Powyzsze encje posiadaja anotacje: 
 * @Entity - dzieki tej anotacji Hibernate "wie", ze ma do czynienia z modelem/rekordem tabeli danych 
 * @Table(name = "NAZWA_TABELI") - anotacja okresla nazwe tabeli, ktora ma powstac w bazie danych
 * @Column - encja ta okresla, ze Hibernate ma do czynienia z typowa kolumna w bazie danych


**PhoneEntity.java**

Klasa ta stanowi encje abstrakcyjna, po ktorej dziedzicza inne encje standardowe. Jednym slowem jest to klasa o znaczeniu najbardziej ogolnym, 
z wlasnosci ktorej korzystaja inne encje. To wlasnie w klasie abstrakcyjnej okresla sie sposob w odwzorowania dziedziczenia 
(co warto zapamietac: dostepne sa trzy sposoby, stad w projekcie mamy 3 encje abstrakcyjne, gdzie kazda prezentuje odrebny sposob odwzorowania 
dziedziczenia).

W celu rozpoznania klasy przez Hibernate zostala ona oznaczona anotacja @Entity. 
Kolejna anotacje stanowi @Inheritance, ktora zawiera parametr "strategy" ustawiony na typ: InheritanceType.JOINED. Typ 
ten stanowi wierne odwzorowanie modelu wzgledem tabel. Tym sposobem w bazie danych powstanie zarowno tabela reprezentujaca nadklase 
(czyli encje abstrakcyjna - phone) jak rowniez tabele reprezentujace dziedziczace po niej encje. Co wazne tabele automatycznie sa ze soba 
powiazane relacja OneToMany. Dodatkowo wlasnosci z nadklasy NIE duplikuja sie w podklasach. Powyzsza strategia dziedziczenia nie jest jednak 
idealna i ostatecznie jest zalecana w przypadku malo zlozonej hierarchii klas. W sytuacji duzej struktury podklas wydajnosc tego rozwiazania 
drastycznie spada przez co nalezy uzyc innej strategii. 

Wewnatrz encji znajduja sie pola symbolizujace kolumny danych. Kazde z pol jest oznaczone anotacjami, ktore pozwalaja frameworkowi 
Hibernate okreslic ich zachowania:

 * @Id - okresla, ze Hibernate ma do czynienia z polem typu PRIMARY; Conajmniej jedno pole musi zawierac encje @Id - w przeciwnym wypadku 
   wystapi wyjatek: AnnotationException: No identifier specified for entity
 * @GeneratedValue - okresla, ze pole ma byc wypelniane automatycznie (tj. kazdy rekord bedzie mial uniklany, kolejny numer) zgodnie z 
   mechanizmem AUTO_INCREMENT.
 * @Column - encja ta okresla, ze Hibernate ma do czynienia z typowa kolumna w bazie danych


**CellphoneEntity.java** i **SmartphoneEntity.java** 

Stanowia encje (tj. zmapowane rekordy bazy danych), dziedziczace z encji abstrakcyjnej (tj. nadklasy). 
Roznica pomiedzy tymi encjami polega na ich dodatkowych, uniklanych wlasnosciach np. encja CellphoneEntity posiada wlasnosc "keyboard" 
(klawiatura), gdy encja SmartphoneEntity juz jej nie posiada. Mimo wszystko obie encje naleza do abstrakcyjnej grupy PhoneEntity, ktora dzieli z 
nimi pewne ogolne wlasnosci.

Powyzsze encje posiadaja anotacje: 
 * @Entity - dzieki tej anotacji Hibernate "wie", ze ma do czynienia z modelem/rekordem tabeli danych 
 * @Table(name = "NAZWA_TABELI") - anotacja okresla nazwe tabeli, ktora ma powstac w bazie danych
 * @Column - encja ta okresla, ze Hibernate ma do czynienia z typowa kolumna w bazie danych


**VehicleEntity.java**

Klasa ta stanowi encje abstrakcyjna, po ktorej dziedzicza inne encje standardowe. Jednym slowem jest to klasa o znaczeniu najbardziej ogolnym, 
z wlasnosci ktorej korzystaja inne encje. To wlasnie w klasie abstrakcyjnej okresla sie sposob w odwzorowania dziedziczenia 
(co warto zapamietac: dostepne sa trzy sposoby, stad w projekcie mamy 3 encje abstrakcyjne, gdzie kazda prezentuje odrebny sposob odwzorowania 
dziedziczenia).

W celu rozpoznania klasy przez Hibernate zostala ona oznaczona anotacja @Entity. 
Kolejna anotacje stanowi @Inheritance, ktora zawiera parametr "strategy" ustawiony na typ: InheritanceType.SINGLE_TABLE. Typ 
ten tworzy pojedyncza tabele, w ktorej zawarte sa wszyskie wlasnosci (pola) encji abstrakcyjnej oraz encji po niej dziedziczacych (podklas). 
Nie jest to rozwiazanie zbyt eleganckie od strony wygladu tabeli w bazie danych (powstaje jedna tabela, gromadzaca wlasnosci pol z wielu 
podklas). Jest ono jednak zalecane ze wzgledu na duza wydajnosc (1 tabela, oznacza brak dodatkowych zlaczen typu JOIN).
Warto rowniez przypomniec, ze od strony kodu aplikacji (w Springu) przejrzystosc modelu zostaje wciaz zachowana.

Warto rowniez zwrocic uwage na bardzo wazna anotacje "@DiscriminatorColumn(name = "discriminator")" - wymusza ona utworzenie dodatkowej kolumny 
o nazwie "discriminator" (nazwa ta moze byc dowolna), ktora zawiera nazwe podklasy, do ktorej nalezy dany rekord. Dzieki ww kolumnie Hibernate 
wie na jaki typ nalezy rzutowac obiekt stanowiacy pojedynczy rekord danych juz po stronie aplikacji.

Wewnatrz encji znajduja sie pola symbolizujace kolumny danych. Kazde z pol jest oznaczone anotacjami, ktore pozwalaja frameworkowi 
Hibernate okreslic ich zachowania:

 * @Id - okresla, ze Hibernate ma do czynienia z polem typu PRIMARY; Conajmniej jedno pole musi zawierac encje @Id - w przeciwnym wypadku 
   wystapi wyjatek: AnnotationException: No identifier specified for entity
 * @GeneratedValue - okresla, ze pole ma byc wypelniane automatycznie (tj. kazdy rekord bedzie mial uniklany, kolejny numer) zgodnie z 
   mechanizmem AUTO_INCREMENT.
 * @Column - encja ta okresla, ze Hibernate ma do czynienia z typowa kolumna w bazie danych


**MotorcycleEntity.java** i **TruckEntity.java** 

Stanowia encje (tj. zmapowane rekordy bazy danych), dziedziczace z encji abstrakcyjnej (tj. nadklasy). 
Roznica pomiedzy tymi encjami polega na ich dodatkowych, uniklanych wlasnosciach np. encja MotorcycleEntity posiada wlasnosc "maxSpeed", gdy 
encja TruckEntity juz jej nie posiada. Mimo wszystko obie encje naleza do abstrakcyjnej grupy VehicleEntity, ktora dzieli z nimi pewne ogolne 
wlasnosci.

Powyzsze encje posiadaja anotacje: 
 * @Entity - dzieki tej anotacji Hibernate "wie", ze ma do czynienia z modelem/rekordem tabeli danych 
 * @Column - encja ta okresla, ze Hibernate ma do czynienia z typowa kolumna w bazie danych