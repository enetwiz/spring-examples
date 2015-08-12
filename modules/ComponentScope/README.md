Modul - ComponentScope
======================

Modul prezentuje manipulacje "zakresem komponentu" definiowanego za pomoca parametru "scope".


Lista plikow (wg waznosci)
--------------------------

 * pom.xml - plik konfiguracyjny buildera projektu [Maven](http://4programmers.net/Java/Maven#id-A-moe-by-tak-Maven); plik ten zawiera 
   odnosniki do zaleznosci (sekcja "dependencies"), czyli bibliotek zaleznych, potrzebnych do budowy aplikacji. M.in. Maven pilnuje, aby 
   zaladowana zostala biblioteka Spring'a w okreslonej w pliku POM wersji
 * AppConfig.java - klasa konfiguracyjna Spring'a, ktory odgrywa duze znaczenie przy AOP i wstrzykiwaniu zaleznosci (DI)
 * ComponentScope.java - serce aplikacji, czyli plik wykonawczy (zawiera metode main()), ktory pozwala uruchomic aplikacje w kontekscie Springa
 * DefaultComponent.java - przykladowy komponent Springa
 * PrototypeComponent.java - przykladowy komponent Springa, w ktorym zmieniony zostal zakres (scope)


Wymagane zaleznosci
-------------------

Aby aplikacja funkcjonowala nalezy zaladowac potrzebne biblioteki, w odpowiednich wersjach:

 * spring-context - podstawowa biblioteka kontekstow Springa


Opis dzialania
--------------

**AppConfig.java**

Zawiera konfiguracje kontekstu Springa.

Anotacja @Configuration - oznacza, ze mamy do czynienia z plikiem konfiguracyjnym (odpowiednik konfiguracyjnego pliku *.xml)

Anotacja @ComponentScan("com.enetwiz.componentscope") - wyszukuje/skanuje klasy oznaczone anotacja @Component w przestrzeni nazw 
"com.enetwiz.componentscope". Po odnalezieniu komponentu zostaje utworzony obiekt o id zgodnym z nazwa klasy. Przykladowo jezeli Spring 
odnajdzie komponent klasy HelloComponent() wowczas referencja/id tego komponentu nazywac sie bedzie "helloComponent".

**DefaultComponent.java**

Typowy komponent Springa, ktory w odroznieniu od beana moze byc wyszukiwany automatycznie po zdefiniowaniu anotacji @ComponentScan()

**PrototypeComponent.java**

Typowy komponent Springa, ktory w odroznieniu od beana moze byc wyszukiwany automatycznie po zdefiniowaniu anotacji @ComponentScan().

**ComponentScope.java**

Klasa ta zawieraja statyczna metode glowna: "main()", dzieki czemu program po kompilacji moze zostac uruchomiony.
W metodzie glownej tworzony jest kontekst Springa o nazwie "AnnotationConfigApplicationContext" - ten kontekst jest stosowany, gdy aplikacja ma 
korzystac z ustawien zdefiniowanych w anotacjach innych klas (np. klasy konfiguracyjnej AppConfig.java). 
Co wazne - Spring posiada rozne konteksty (tym samym nie jestesmy skazani tylko na anotacje jako podstawe konfiguracji frameworka).

W kolejnym wierszach:

*DefaultComponent dc = (DefaultComponent) context.getBean("defaultComponent");
dc.setLabel("label");
DefaultComponent dc2 = (DefaultComponent) context.getBean("defaultComponent");
dc2.setLabel("another label");*

probujemy utworzyc dwa niezalezne obiekty typu DefaultComponent, z roznymi etykietami - nie jest to jednak mozliwe poniewaz domyslnie kazdy 
komponent w Springu ma zasieg (scope) ustawiony na wartosc "singleton" - co sprawia, ze odnosimy sie zawsze do jednej i tej samej instancji 
obiektu. Dlatego tez uzywajac komponentu (ProtypeComponent) z anotacja @Scope("prototype") wymuszamy na frameworku tworzenie nowych instancji 
przy kazdym wywolaniu metody context.getBean("nazwaKomponentu") - przykladowo:

*PrototypeComponent pc = (PrototypeComponent) context.getBean("prototypeComponent");
pc.setLabel("label");
PrototypeComponent pc2 = (PrototypeComponent) context.getBean("prototypeComponent");
pc2.setLabel("another label");*

Tym razem Spring utworzy dwie instancje obiektu PrototypeComponent, zmieniajac przy tym etykiete dla kazdego z nich.

Wartosci anotacji scope jest znacznie wiecej, m.in.: request, session, global-session. Kazda z tych wartosci sprawia zmiany w cyklu 
zycia komponentu.