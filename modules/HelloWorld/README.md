Modul - HelloWorld
==================

Modul prezentuje najprostsza z mozliwych aplikacje drukujaca napis: "Hello World!". Aplikacja ta jest konfigurowana za posrednictwem anotacji. 


Lista plikow (wg waznosci)
--------------------------

 * pom.xml - plik konfiguracyjny buildera projektu [Maven](http://4programmers.net/Java/Maven#id-A-moe-by-tak-Maven); plik ten zawiera 
   odnosniki do zaleznosci (sekcja "dependencies"), czyli bibliotek zaleznych, potrzebnych do budowy aplikacji. M.in. Maven pilnuje, aby 
   zaladowana zostala biblioteka Spring'a w okreslonej w pliku POM wersji
 * AppConfig.java - klasa konfiguracyjna Spring'a, ktory odgrywa duze znaczenie przy AOP i wstrzykiwaniu zaleznosci (DI)
 * HelloWorld.java - serce aplikacji, czyli plik wykonawczy (zawiera metode main()), ktory pozwala uruchomic aplikacje w kontekscie Springa
 * HelloBean.java - przykladowy, prosty obiekt Javy (POJO)
 * HelloComponent.java - przykladowy komponent Springa


Wymagane zaleznosci
-------------------

Aby aplikacja funkcjonowala nalezy zaladowac potrzebne biblioteki, w odpowiednich wersjach:

 * [spring-context](http://search.maven.org/#artifactdetails|org.springframework|spring-context|3.2.14.RELEASE|jar) - podstawowa biblioteka 
   kontekstow Springa
 * [cglib](http://search.maven.org/#artifactdetails|cglib|cglib|3.1|jar) - biblioteka rozszerzajaca klasy i interfejsy w czasie dzialania 
   aplikacji (at runtime); jest uzywana glownie w aplikacjach uzywajacych anotacji (@Configuration) jako konfiguracji


Opis dzialania
--------------

**AppConfig.java**

Zawiera konfiguracje kontekstu Springa.

Anotacja @Configuration - oznacza, ze mamy do czynienia z plikiem konfiguracyjnym (odpowiednik konfiguracyjnego pliku *.xml)

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

**HelloBean.java**

Typowy prosty obiekt Javy (POJO); Obiekt wielokrotnego uzytku.

**HelloComponent.java**

Komponent Springa, ktory w odroznieniu od beana moze byc wyszukiwany automatycznie po zdefiniowaniu anotacji @ComponentScan()

**HelloWorld.java**

Klasa ta zawieraja statyczna metode glowna: "main()", dzieki czemu program po kompilacji moze zostac uruchomiony.
W metodzie glownej tworzony jest kontekst Springa o nazwie "AnnotationConfigApplicationContext" - ten kontekst jest stosowany, gdy aplikacja ma 
korzystac z ustawien zdefiniowanych w anotacjach innych klas (np. klasy konfiguracyjnej AppConfig.java). 
Co wazne - Spring posiada rozne konteksty (tym samym nie jestesmy skazani tylko na anotacje jako podstawe konfiguracji frameworka).

W kolejnym wierszu:

*HelloBean hello = (HelloBean) context.getBean("helloBean");*

nastepuje odwolanie do referencji obiektu HelloBean(), ktory zostal utworzony zaraz po uruchomieniu kontekstu aplikacji Springa, na bazie 
instrukcji zawartych w anotacjach klas.

W podobny sposob odwolujemy sie do komponentu:

*HelloComponent helloComponent = (HelloComponent) context.getBean("helloComponent");*

Jedyna roznica jest to, ze obiekt Bean'a ( HelloBean() ) musielismy zadeklarowac jawnie w klasie konfiguracyjnej (AppConfig.java),
natomiast komponent zostal utworzony automatycznie dzieki anotacji @ComponentScan().