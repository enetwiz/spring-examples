Modul - HelloWorldWeb
=====================

Modul prezentuje najprostsza z mozliwych aplikacja internetowa (strona internetowa) - drukujaca napis: "Hello World!". 
Aplikacja jest utworzona w ramach pod-frameworka Spring Web MVC.
Cala aplikacja jest skonfigurowana za pomoca anotacji.


Lista plikow (wg waznosci)
--------------------------

 * pom.xml - plik konfiguracyjny buildera projektu [Maven](http://4programmers.net/Java/Maven#id-A-moe-by-tak-Maven); plik ten zawiera 
   odnosniki do zaleznosci (sekcja "dependencies"), czyli bibliotek zaleznych, potrzebnych do budowy aplikacji. M.in. Maven pilnuje, aby 
   zaladowana zostala biblioteka Spring'a w okreslonej w pliku POM wersji
 * AppConfig.java - klasa konfiguracyjna Spring'a, ktory odgrywa duze znaczenie przy AOP i wstrzykiwaniu zaleznosci (DI)
 * WebAppInitializer.java - klasa inicjujaca aplikacje internetowa. Pozwala na import ustawien z innych klas (AppConfig.java), definicje sciezek 
   dostepu do aplikacji (mapowanie) i wiele innych ustawien. Bez tej klasy aplikacja internetowa nie moglaby zostac obsluzona przez 
   servlet aplikacji.
 * HomeController.java - typowy kontroler Springa w ramach wzorca MVC
 * hello.jsp - plik widoku Springa w ramach wzorca MVC
 * context.xml - plik staje sie przydany w chwili uruchamiania aplikacji bezposrednio z poziomu IDE (np. poleceniem Run w Netbeans) - bez niego
   nie byloby to mozliwe


Wymagane zaleznosci
-------------------

Aby aplikacja funkcjonowala nalezy zaladowac potrzebne biblioteki, w odpowiednich wersjach:

 * spring-context - podstawowa biblioteka kontekstow Springa
 * servlet-api - biblioteka odpowiedzialna za wyświetlanie dynamicznych stron WWW (tj. przetwarzanie formularzy, forwarding itp.)
 * jstl - standardowa biblioteka znacznikow Javy ulatwajaca pisanie stron JSP
 * jsp-api - biblioteka odpowiedzialna za parsowanie i obsluge dokumentow zawierajacych znaczniki JavaServer Pages (JSP)


Opis dzialania
--------------

**AppConfig.java**

Zawiera konfiguracje kontekstu Springa - zarowno w postaci anotacji jak i obiektow konfiguracyjnych.

Anotacja @Configuration - oznacza, ze mamy do czynienia z plikiem konfiguracyjnym (odpowiednik konfiguracyjnego pliku *.xml)

Anotacja @EnableWebMvc - oznacza, ze framework Spring Web MVC bedzie wyszukiwal anotacji typu @Controller i innych anotacji powiazanych ze 
wzorcem MVC.
Anotacja @EnableWebMvc jest to ekwiwalentem sekcji: \<mvc:annotation-driven\> dla konfguracji pochodzacej z XML'a.

Anotacja @ComponentScan("com.enetwiz.helloworldweb") - wyszukuje/skanuje klasy oznaczone anotacja @Component w przestrzeni nazw 
"com.enetwiz.helloworldweb". Po odnalezieniu komponentu zostaje utworzony obiekt o id zgodnym z nazwa klasy. Przykladowo jezeli Spring 
odnajdzie komponent klasy HelloComponent() wowczas referencja/id tego komponentu nazywac sie bedzie "helloComponent".

Klasa AppConfig() rozszerza adapter WebMvcConfigurerAdapter() (stanowi on klase projektu Spring Web MVC), ktory z kolei jest odpowiedzialny za 
definiowanie metod zwrotnych (callback method) w aplikacjach sterowanych przez anotacje. W sytuacji rozszerzania tej klasy konieczne jest uzycie anotacji 
@EnableWebMvc.

Klasa AppConfig() zawiera metode: viewResolver(), ktora tworzy obiekt typu ViewResolver. Obiekt ten jest odpowiedzialny za tworzenie nazw dla 
poszczegolnych widokow. Jezeli prefix i suffix tego obiektu zostanie skonfigurowany wowczas w metodach kontolera moga byc podawane znacznie 
uproszczone nazwy widokow.


**WebAppInitializer.java**

Klasa inicjujaca Spring Web MVC - bez niej framework nie znalby podstaw konfiguracji. 
Klasa rozszerza klase abstrakcyjna AbstractAnnotationConfigDispatcherServletInitializer() - ktora ma kilka zadan:

 * ma zainicjowac automatycznie (tj. z uzyciem wylacznie kodu Java) servlet aplikacji (DispatcherServlet)
 * ma uzywac konfiguracji z klas oznaczonych adnotacja @Configuration 
 * ma wyeliminowac koniecznosc uzycia pliku konfiguracyjnego aplikacji internetowych tj. web.xml

Poszczegolne metody super klas (np. getServletMappings()) pozwalaja na konfiguracje glownego prefixu do aplikacji dzieki czemu dostep do 
aplikacji jest poprzedzony globalnym prefixem: http://strona.pl/glownyprefix/kontroler/akcja


**HomeController.java**

Jest przykladem kontrolera aplikacji internetowej zgodnego z wzorcem MVC. Kazdy kontroler w ramach podframeworka Spring Web MVC musi byc oznaczony 
anotacja @Controller.
Z kolei kazda metoda powinna miec zdefiniowane zasady mapowania za pomoca anotacji:

*@RequestMapping(value = "/nazwapodstrony")*

Metody kontrolera zwracaja nazwy widokow w postaci obiektu String. Jezeli w konfiguracji ustawilismy odpowiedni prefix i suffix wowczas wystaczy 
podac nazwe pliku widoku bez rozszerzenia.

Przykladowo zalozmy ze mamy plik widoku umieszczony w WEB-INF/views/hello.jsp. Po odpowiednim zdefiniowaniu prefixow w klasie konfiguracyjnej 
(AppConfig.java) metoda naszego kontolera moze wygladac w ten sposob:

*@RequestMapping(value = "/nazwapodstrony")
public String showHello() {
    return "hello";
}*

**hello.jsp**

Jest to plik widoku (innymi slowy standardowy dokument HTML zawierajacy znaczniki JSP), ktory jest zwracany w kontolerze "HomeController" 
przez metode/akcje "showHello()". 
Nalezy pamietac aby zawieral on kodowanie oraz definicje tagow w standardu JavaServer Pages:

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

Powyzsze 2 definicje wstawiamy na samym poczatku pliku.