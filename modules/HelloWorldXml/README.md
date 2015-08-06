Modul - HelloWorldXml
=====================

Modul prezentuje najprostsza z mozliwych aplikacje drukujaca napis: "Hello World!". Aplikacja ta jest konfigurowana za posrednictwem plikow XML.


Lista plikow (wg waznosci)
--------------------------

 * pom.xml - plik konfiguracyjny buildera projektu [Maven](http://4programmers.net/Java/Maven#id-A-moe-by-tak-Maven); plik ten zawiera 
   odnosniki do zaleznosci (sekcja "dependencies"), czyli bibliotek zaleznych, potrzebnych do budowy aplikacji. M.in. Maven pilnuje, aby 
   zaladowana zostala biblioteka Spring'a w okreslonej w pliku POM wersji
 * hello.xml - podstawowy plik konfiguracyjny Spring'a, ktory odgrywa duze znaczenie przy AOP i wstrzykiwaniu zaleznosci (DI)
 * HelloWorld.java - serce aplikacji, czyli plik wykonawczy (zawiera metode main()), ktory pozwala uruchomic aplikacje w kontekscie Springa
 * Hello.java - przykladowy, prosty obiekt Javy (POJO)


Wymagane zaleznosci
-------------------

Aby aplikacja funkcjonowala nalezy zaladowac potrzebne biblioteki, w odpowiednich wersjach:

 * [spring-context](http://search.maven.org/#artifactdetails|org.springframework|spring-context|3.2.14.RELEASE|jar) - podstawowa biblioteka 
   kontekstow Springa

Opis dzialania
--------------

**hello.xml**

Zawiera schemat konfiguracyjny kontekstu Springa (inaczej mowiac - jest to plik konfiguracyjny frameworka w formacie XML).
W pliku tym w sekcji \<beans\> zdefiniowana zostala instrukcja (sekcja \<bean\>) utworzenia obiektu Hello() i przypisaniu go do referencji "hello".
Zapis ten jest odpowiednikiem ponizszego zapisu w "czystej" Javie:

*Hello hello = new Hello();*


**HelloWorld.java**

Klasa ta zawieraja statyczna metode glowna: "main()", dzieki czemu program po kompilacji moze zostac uruchomiony.
W metodzie glownej tworzony jest kontekst Springa o nazwie "ClassPathXmlApplicationContext" - ten kontekst jest stosowany, gdy aplikacja ma 
korzystac z ustawien kontekstu zapisanych w pliku konfiguracyjnym, w formacie XML. Co wazne - Spring posiada rozne konteksty (tym samym nie 
jestesmy skazani tylko na pliki XML jako podstawe konfiguracji frameworka).

W kolejnym wierszu:

*Hello hello = (Hello) context.getBean("hello");*

nastepuje odwolanie do referencji obiektu Hello(), ktory zostal utworzony zaraz po uruchomieniu kontekstu aplikacji Springa na bazie instrukcji z 
pliku hello.xml.