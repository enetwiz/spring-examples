Modul - InitDestroyMethods
==================

Modul prezentuje w jaki sposob mozemy wplynac na cykl zycia projektu za pomoc anotacji.


Lista plikow (wg waznosci)
--------------------------

 * pom.xml - plik konfiguracyjny buildera projektu [Maven](http://4programmers.net/Java/Maven#id-A-moe-by-tak-Maven); plik ten zawiera 
   odnosniki do zaleznosci (sekcja "dependencies"), czyli bibliotek zaleznych, potrzebnych do budowy aplikacji. M.in. Maven pilnuje, aby 
   zaladowana zostala biblioteka Spring'a w okreslonej w pliku POM wersji
 * AppConfig.java - klasa konfiguracyjna Spring'a, ktory odgrywa duze znaczenie przy AOP i wstrzykiwaniu zaleznosci (DI)
 * ExampleComponent.java - przykladowy komponent Springa z anotacjami umozliwiajacymi zarzadzanie jego cyklem zycia
 * InitDestroyMethods.java - serce aplikacji, czyli plik wykonawczy (zawiera metode main()), ktory pozwala uruchomic aplikacje w kontekscie Springa


Wymagane zaleznosci
-------------------

Aby aplikacja funkcjonowala nalezy zaladowac potrzebne biblioteki, w odpowiednich wersjach:

 * spring-context - podstawowa biblioteka kontekstow Springa


Opis dzialania
--------------

**AppConfig.java**

Zawiera konfiguracje kontekstu Springa.

Anotacja @Configuration - oznacza, ze mamy do czynienia z plikiem konfiguracyjnym (odpowiednik konfiguracyjnego pliku *.xml)

Anotacja @ComponentScan("com.enetwiz.initdestroymethods") - wyszukuje/skanuje klasy oznaczone anotacja @Component w przestrzeni nazw 
"com.enetwiz.initdestroymethods". Po odnalezieniu komponentu zostaje utworzony obiekt o id zgodnym z nazwa klasy. Przykladowo jezeli Spring 
odnajdzie komponent klasy HelloComponent() wowczas referencja/id tego komponentu nazywac sie bedzie "helloComponent".


**ExampleComponent.java**

Komponent Springa, ktory w odroznieniu od beana moze byc wyszukiwany automatycznie po zdefiniowaniu anotacji @ComponentScan()
Komponent ten zawiera dwie dodatkowe anotacje majace realny wplyw na cykl zycia projektu:

 * @PostConstruct - metoda oznaczona anotacja @PostConstruct będzie wywolywana po stworzeniu instancji komponentu, po DI, ale przed wykonaniem 
   jego metod
 * @PreDestroy - wywolywana jest przed usunieciem instancji komponentu

Praktycznym sposobem uzycia metody oznaczonej przez np. @PreDestroy moze byc zamkniecie zasobow, ktore obiekt otworzyl w czasie swojego dzialania.


**InitDestroyMethods.java**

Klasa ta zawieraja statyczna metode glowna: "main()", dzieki czemu program po kompilacji moze zostac uruchomiony.
W metodzie glownej tworzony jest kontekst Springa o nazwie "AnnotationConfigApplicationContext" - ten kontekst jest stosowany, gdy aplikacja ma 
korzystac z ustawien zdefiniowanych w anotacjach innych klas (np. klasy konfiguracyjnej AppConfig.java). 
Co wazne - Spring posiada rozne konteksty (tym samym nie jestesmy skazani tylko na anotacje jako podstawe konfiguracji frameworka).

W kolejnym wierszu:

*ExampleComponent component = (ExampleComponent) context.getBean("exampleComponent");*

nastepuje odwolanie do referencji obiektu ExampleComponent(), ktory zostal utworzony zaraz po uruchomieniu kontekstu aplikacji Springa, na bazie 
instrukcji zawartych w anotacjach klas.

Wywolanie:

*context.close();*

powoduje zamkniecie kontekstu Springa oraz uwolnienie zasobow i wszelkich blokad zalozonych w czasie dzialania kontekstu (inaczej mowiac 
wywolanie tej metody wymusza zamkniecie aplikacji Springa)