Modul - DependencyInjection
===========================

Modul prezentuje technike wstrzykiwania zaleznosci (DependencyInjection) w srodowisku Springa. 


Lista plikow (wg waznosci)
--------------------------

 * pom.xml - plik konfiguracyjny buildera projektu [Maven](http://4programmers.net/Java/Maven#id-A-moe-by-tak-Maven); plik ten zawiera 
   odnosniki do zaleznosci (sekcja "dependencies"), czyli bibliotek zaleznych, potrzebnych do budowy aplikacji. M.in. Maven pilnuje, aby 
   zaladowana zostala biblioteka Spring'a w okreslonej w pliku POM wersji
 * AppConfig.java - klasa konfiguracyjna Spring'a, ktory odgrywa duze znaczenie przy AOP i wstrzykiwaniu zaleznosci (DI)
 * ExampleBean.java - przykladowy, prosty obiekt Javy (POJO)
 * PropertyComponent.java - komponent prezentujacy wstrzykiwanie zaleznosci bezposrednio przez wybrana wlasciwosc klasy
 * ConstructorComponent.java - komponent prezentujacy wstrzykiwanie zaleznosci bezposrednio przez konstruktor klasy
 * SetterComponent.java - komponent prezentujacy wstrzykiwanie zaleznosci bezposrednio przez metode typu setter
 * DependencyInjection.java - serce aplikacji, czyli plik wykonawczy (zawiera metode main()), ktory pozwala uruchomic aplikacje w kontekscie Springa


Wymagane zaleznosci
-------------------

Aby aplikacja funkcjonowala nalezy zaladowac potrzebne biblioteki, w odpowiednich wersjach:

 * spring-context - podstawowa biblioteka kontekstow Springa


Opis dzialania
--------------

**AppConfig.java**

Zawiera konfiguracje kontekstu Springa.

Anotacja @Configuration - oznacza, ze mamy do czynienia z plikiem konfiguracyjnym (odpowiednik konfiguracyjnego pliku *.xml)

Anotacja @ComponentScan("com.enetwiz.dependencyinjection") - wyszukuje/skanuje klasy oznaczone anotacja @Component w przestrzeni nazw 
"com.enetwiz.dependencyinjection". Po odnalezieniu komponentu zostaje utworzony obiekt o id zgodnym z nazwa klasy. Przykladowo jezeli Spring 
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

Anotacja @Scope("prototype") nad metoda - oznacza zmiane zakresu tworzonego obiektu. Dzieki zakresowi "prototype" obiekt moze byc tworzony 
w wielu egzemplarzach/instancjach (po jednym na kazde wywolanie ApplicationContext.getBean()). Uzycie atrybutu "prototype" jest przykladem zmiany 
domyslnego zakresu (domyslnie jest nim: singleton).


**ExampleBean.java**

Typowy prosty obiekt Javy (POJO); Obiekt wielokrotnego uzytku.
W ramach aplikacji obiekt ten jest wstrzykiwany do komponentow, na rozne sposoby.


**PropertyComponent.java**

Klasyczny komponent Springa, ktory w odroznieniu od beana moze byc wyszukiwany automatycznie po zdefiniowaniu anotacji @ComponentScan().

Anotacja @Autowired wskazuje, ze obiekt ExampleBean() zostanie po utworzeniu automatycznie wstrzykniety do wlasciwosci "bean".


**ConstructorComponent.java**

Klasyczny komponent Springa, ktory w odroznieniu od beana moze byc wyszukiwany automatycznie po zdefiniowaniu anotacji @ComponentScan().

Anotacja @Autowired wskazuje, ze obiekt ExampleBean() zostanie po utworzeniu automatycznie wstrzykniety do konstruktora w parametrze 
"pExampleBean".


**SetterComponent.java**

Klasyczny komponent Springa, ktory w odroznieniu od beana moze byc wyszukiwany automatycznie po zdefiniowaniu anotacji @ComponentScan().

Anotacja @Autowired wskazuje, ze obiekt ExampleBean() zostanie po utworzeniu automatycznie wstrzykniety jako parametr "pExampleBean" 
metody setBean().


**HelloWorld.java**

Klasa ta zawieraja statyczna metode glowna: "main()", dzieki czemu program po kompilacji moze zostac uruchomiony.
W metodzie glownej tworzony jest kontekst Springa o nazwie "AnnotationConfigApplicationContext" - ten kontekst jest stosowany, gdy aplikacja ma 
korzystac z ustawien zdefiniowanych w anotacjach innych klas (np. klasy konfiguracyjnej AppConfig.java). 
Co wazne - Spring posiada rozne konteksty (tym samym nie jestesmy skazani tylko na anotacje jako podstawe konfiguracji frameworka).

Ponizej utworzonego obiektu kontekstu Springa (tj.: ApplicationContext context = (..);) nastepuje pobranie trzech instancji komponentow, ktorym 
automatycznie wstrzykiwany zostaje obiekt ExampleBean:

 * komponentowi PropertyComponent - obiekt ExampleBean zostaje wstrzykniety bezposrednio przez wlasciwosc
 * komponentowi ConstructorComponent - obiekt ExampleBean zostaje wstrzykniety bezposrednio przez konstruktor
 * komponentowi SetterComponent - obiekt ExampleBean zostaje wstrzykniety bezposrednio przez metode setter'a