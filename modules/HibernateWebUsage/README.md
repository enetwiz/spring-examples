Modul - HibernateWebUsage
=========================

Modul prezentuje przyklad implementacji ORM'a Hibernate 4.x + MySQL w ramach aplikacji internetowej Springa (Spring Web MVC).


Lista plikow (wg waznosci)
--------------------------

 * structure.sql - struktura tabeli danych, wymagana do utrwalenia danych w bazie danych
 * pom.xml - plik konfiguracyjny buildera projektu [Maven](http://4programmers.net/Java/Maven#id-A-moe-by-tak-Maven); plik ten zawiera 
   odnosniki do zaleznosci (sekcja "dependencies"), czyli bibliotek zaleznych, potrzebnych do budowy aplikacji. M.in. Maven pilnuje, aby 
   zaladowana zostala biblioteka Spring'a w okreslonej w pliku POM wersji
 * AppConfig.java - klasa konfiguracyjna Spring'a, ktory odgrywa duze znaczenie przy AOP i wstrzykiwaniu zaleznosci (DI)
 * WebAppInitializer.java - klasa inicjujaca aplikacje internetowa. Pozwala na import ustawien z innych klas (AppConfig.java), definicje sciezek 
   dostepu do aplikacji (mapowanie) i wiele innych ustawien. Bez tej klasy aplikacja internetowa nie moglaby zostac obsluzona przez 
   servlet aplikacji.
 * ExampleEntity.java - przykladowy, prosty obiekt Javy (POJO), ktory stanowi obiektowe odwzorowanie tabeli w bazie danych (tzw. encja/model)
 * ExampleEntityDao.java - obiekt dostepu do danych (Data Access Object, DAO, repozytorium), dzieki ktoremu dokonujemy roznych operacji na danych 
   jak np. dodawanie, usuwanie rekordow itp
 * form.html - plik widoku generujacy formularz dodawania rekordow do bazy danych
 * list.html - plik widoku generujacy liste wszystkich dodanych rekordow
 * AppController.java - typowy kontroler Springa w ramach wzorca MVC
 * context.xml - plik kontekstu jest przydany w chwili uruchamiania aplikacji bezposrednio z poziomu IDE (np. poleceniem Run w Netbeans) - 
   bez niego nie byloby to mozliwe


Wymagane zaleznosci
-------------------

Aby aplikacja funkcjonowala nalezy zaladowac potrzebne biblioteki, w odpowiednich wersjach:

 * spring-context - podstawowa biblioteka kontekstow Springa
 * spring-webmvc - biblioteka pod-frameworka umozliwiajaca budowanie aplikacji internetowych w ramach wzorca MVC
 * servlet-api - biblioteka odpowiedzialna za wyświetlanie dynamicznych stron WWW (tj. przetwarzanie formularzy, forwarding itp.)
 * thymeleaf - biblioteka zawierajaca silnik szablonow XML/XHTML/HTML5 dedykowanych dla jezyka Java
 * thymeleaf-spring4 - biblioteka umozliwiajaca integracje/instalacje szablonow Thymeleaf w Spring 4.x
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

Zawiera konfiguracje kontekstu Springa - zarowno w postaci anotacji jak i obiektow konfiguracyjnych.

Anotacja @Configuration - oznacza, ze mamy do czynienia z plikiem konfiguracyjnym (odpowiednik konfiguracyjnego pliku *.xml)

Anotacja @EnableTransactionManagement - jest odpowiedzialna za zarzadzanie transakcjami poprzez anotacje; anotacja ta jest wymagana - jej brak 
powoduje wyjatek o tresci: "HibernateException: Could not obtain transaction-synchronized Session for current thread"

Anotacja @EnableWebMvc - oznacza, ze framework Spring Web MVC bedzie wyszukiwal anotacji typu @Controller i innych anotacji powiazanych ze 
wzorcem MVC.
Anotacja @EnableWebMvc jest to ekwiwalentem sekcji: \<mvc:annotation-driven\> dla konfguracji pochodzacej z XML'a.

Anotacja @ComponentScan("com.enetwiz.helloworldweb") - wyszukuje/skanuje klasy oznaczone anotacja @Component w przestrzeni nazw 
"com.enetwiz.helloworldweb". Po odnalezieniu komponentu zostaje utworzony obiekt o id zgodnym z nazwa klasy. Przykladowo jezeli Spring 
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

Klasa AppConfig() rozszerza adapter WebMvcConfigurerAdapter() (stanowi on klase projektu Spring Web MVC), ktory z kolei jest odpowiedzialny za 
definiowanie metod zwrotnych (callback method) w aplikacjach sterowanych przez anotacje. W sytuacji rozszerzania tej klasy konieczne jest uzycie 
anotacji @EnableWebMvc.

W module HibernateWebUsage zostal uzyty silnik szablonow o nazwie Thymeleaf (w pliku POM zostaly umieszczone jego zaleznosci). Aby Thymeleaf zostal 
zintegrowany ze srodowiskiem Springa nalezalo wykonac konfiguracje za pomoca metod: 

 * templateResolver() - metoda pomocnicza, ktora pozwala zdefiniowac podstawowe ustawienia silnika szablonow (np. prefix, suffix itp.)
 * templateEngine() - metoda pomocnicza tworzaca obiekt silnika szablonow
 * viewResolver() - metoda ta jest najwazniejsza, poniewaz nadpisuje ustawienia oryginalnego ViewResolver'a. Jej zadaniem jest zmiana 
   glownego silnika szablonow ( poprzez metode setTemplateEngine() ) oraz jego konfiguracja poprzez metode templateResolver()

Kolejnym etapem jest konfiguracja utrwalania danych za pomoca Hibernate. W pierwszej kolejnosci nastepuje inicjacja sesji polaczenia 
( metoda sessionFactory() ) oraz konfiguracja dostepu do zrodla danych ( metoda dataSource() ) - w tym przypadku do bazy MySQL. 
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


**WebAppInitializer.java**

Klasa inicjujaca Spring Web MVC - bez niej framework nie znalby podstaw konfiguracji. 
Klasa rozszerza klase abstrakcyjna AbstractAnnotationConfigDispatcherServletInitializer() - ktora ma kilka zadan:

 * ma zainicjowac automatycznie (tj. z uzyciem wylacznie kodu Java) servlet aplikacji (DispatcherServlet)
 * ma uzywac konfiguracji z klas oznaczonych adnotacja @Configuration 
 * ma wyeliminowac koniecznosc uzycia pliku konfiguracyjnego aplikacji internetowych tj. web.xml

Poszczegolne metody super klas (np. getServletMappings()) pozwalaja na konfiguracje glownego prefixu do aplikacji dzieki czemu dostep do 
aplikacji jest poprzedzony globalnym prefixem: http://strona.pl/glownyprefix/kontroler/akcja


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


**form.html**

Jest to plik widoku (innymi slowy standardowy dokument HTML zawierajacy znaczniki silnika szablonow Thymeleaf), ktory jest zwracany w kontrolerze 
"AppController" przez metode/akcje "form()". Szablon generuje formularz dodawania rekordow do bazy danych.


**list.html**

Jest to plik widoku (innymi slowy standardowy dokument HTML zawierajacy znaczniki silnika szablonow Thymeleaf), ktory jest zwracany w kontrolerze 
"AppController" przez metode/akcje "list()". Szablon generuje liste rekordow dodanych do bazy danych.


**AppController.java**

Jest przykladem kontrolera aplikacji internetowej zgodnego z wzorcem MVC. Kazdy kontroler w ramach podframeworka Spring Web MVC musi byc oznaczony 
anotacja @Controller.
Z kolei kazda metoda powinna miec zdefiniowane zasady mapowania za pomoca anotacji:

*@RequestMapping(value = "/nazwapodstrony")*

Metody kontrolera zwracaja nazwy widokow w postaci obiektu String lub obiektu ModelAndView. Jezeli w konfiguracji ustawilismy odpowiedni 
prefix i suffix wowczas wystarczy podac nazwe pliku widoku bez rozszerzenia.

Przykladowo zalozmy ze mamy plik widoku umieszczony w WEB-INF/views/hello.html. Po odpowiednim zdefiniowaniu prefixow w klasie konfiguracyjnej 
(AppConfig.java) metoda naszego kontolera moze wygladac w ten sposob:

*@RequestMapping(value = "/nazwapodstrony")
public String showHello() {
    return "hello";
}*

Metody oznaczone anotacja @RequestMapping posiadaja rowniez wewnetrzny parametr: RequestMethod.GET lub RequestMethod.POST. Parametr ten okresla 
wymagana metode zadania. Np. metoda oznaczona: @RequestMapping(value="/save", method=RequestMethod.POST) nie obsluzy zadania typu GET 
(serwlet zwroci blad: "405 - Request method 'GET' not supported").

Ostatnia kwestia, na ktora warto zwrocic uwage to wlasnosc "exampleEntityDao", ktora jest uzywana wewnatrz metod kontrolera. Ww wlasnosc jest 
automatycznie wiazana (anotacja @Autowired) przez Springa z obiektem DAO/repozytorium > ExampleEntityDao(). Bez tego wiazania nie byloby mozliwe 
wykonywanie jakiejkolwiek operacji na danych.