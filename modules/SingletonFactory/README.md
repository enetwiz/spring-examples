Modul - SingletonFactory
========================

Modul prezentuje prosty sposob tworzenia instancji klas utworzonych na bazie wzorca Singleton.


Lista plikow (wg waznosci)
--------------------------

 * pom.xml - plik konfiguracyjny buildera projektu [Maven](http://4programmers.net/Java/Maven#id-A-moe-by-tak-Maven); plik ten zawiera 
   odnosniki do zaleznosci (sekcja "dependencies"), czyli bibliotek zaleznych, potrzebnych do budowy aplikacji. M.in. Maven pilnuje, aby 
   zaladowana zostala biblioteka Spring'a w okreslonej w pliku POM wersji
 * AppConfig.java - klasa konfiguracyjna Spring'a, ktory odgrywa duze znaczenie przy AOP i wstrzykiwaniu zaleznosci (DI)
 * ExampleSingleton.java - klasa typu POJO zbudowana w ramach wzorca Singleton
 * SingletonFactory.java - serce aplikacji, czyli plik wykonawczy (zawiera metode main()), ktory pozwala uruchomic aplikacje w kontekscie Springa


Wymagane zaleznosci
-------------------

Aby aplikacja funkcjonowala nalezy zaladowac potrzebne biblioteki, w odpowiednich wersjach:

 * spring-context - podstawowa biblioteka kontekstow Springa


Opis dzialania
--------------

**AppConfig.java**

Zawiera konfiguracje kontekstu Springa.

Anotacja @Configuration - oznacza, ze mamy do czynienia z plikiem konfiguracyjnym (odpowiednik konfiguracyjnego pliku *.xml)

Anotacja @Bean nad metoda - oznacza, ze kontekst Springa utworzy obiekt POJO o referencji/id zgodny z nazwa metody np.

*
@Bean
public HelloBean helloBean() {
    return new HelloBean();
}
*

oznacza to samo co:

*HelloBean helloBean = new HelloBean();*


**ExampleSingleton.java**

Typowy prosty obiekt Javy (POJO) zbudowany w ramach wzorca Singleton. Co jest rownoznaczne z mozliwoscia utworzenia tylko jednej instancji obiektu 
w czasie dzialania aplikacji.


**SingletonFactory.java**

Klasa ta zawieraja statyczna metode glowna: "main()", dzieki czemu program po kompilacji moze zostac uruchomiony.
W metodzie glownej tworzony jest kontekst Springa o nazwie "AnnotationConfigApplicationContext" - ten kontekst jest stosowany, gdy aplikacja ma 
korzystac z ustawien zdefiniowanych w anotacjach innych klas (np. klasy konfiguracyjnej AppConfig.java). 
Co wazne - Spring posiada rozne konteksty (tym samym nie jestesmy skazani tylko na anotacje jako podstawe konfiguracji frameworka).

W kolejnym wierszu:

*ExampleSingelton singelton = (ExampleSingelton) context.getBean("exampleSingelton");*

nastepuje odwolanie do referencji obiektu ExampleSingelton(), ktory zostal utworzony zaraz po uruchomieniu kontekstu aplikacji Springa w 
pliku AppConfig.java. Nalezy pamietac ze obiekt ExampleSingelton jest klasa typu singleton.