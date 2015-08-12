Modul - AspectOrientedProgramming
=================================

Modul prezentuje praktyczne zastosowanie programowania aspektowego (Aspect Oriented Programming, AOP) z uzyciem biblioteki AspectJ.


Lista plikow (wg waznosci)
--------------------------

 * pom.xml - plik konfiguracyjny buildera projektu [Maven](http://4programmers.net/Java/Maven#id-A-moe-by-tak-Maven); plik ten zawiera 
   odnosniki do zaleznosci (sekcja "dependencies"), czyli bibliotek zaleznych, potrzebnych do budowy aplikacji. M.in. Maven pilnuje, aby 
   zaladowana zostala biblioteka Spring'a w okreslonej w pliku POM wersji
 * AppConfig.java - klasa konfiguracyjna Spring'a, ktory odgrywa duze znaczenie przy AOP i wstrzykiwaniu zaleznosci (DI)
 * ExampleComponent.java - przykladowy komponent Springa, do ktorego zostana dodane punkty przeciecia
 * LoggingAspect.java - klasa aspektu, ktora definiuje operacje AOP na okreslonym - w jej punktach przeciecia - obiekcie
 * AspectOrientedProgramming.java - serce aplikacji, czyli plik wykonawczy (zawiera metode main()), ktory pozwala uruchomic aplikacje w 
   kontekscie Springa


Wymagane zaleznosci
-------------------

Aby aplikacja funkcjonowala nalezy zaladowac potrzebne biblioteki, w odpowiednich wersjach:

 * spring-context - podstawowa biblioteka kontekstow Springa
 * aspectjrt - biblioteka AspectJ wdrazajaca paradygmat programowania aspektowego do Javy
 * aspectjweaver - biblioteka pozwalajaca na automatyczne laczenie AspectJ ze Springiem za pomoca anotacji (np. anotacji @EnableAspectJAutoProxy)


Opis dzialania
--------------

**AppConfig.java**

Zawiera konfiguracje kontekstu Springa.

Anotacja @Configuration - oznacza, ze mamy do czynienia z plikiem konfiguracyjnym (odpowiednik konfiguracyjnego pliku *.xml)

Anotacja @ComponentScan("com.enetwiz.aspectorientedprogramming") - wyszukuje/skanuje klasy oznaczone anotacja @Component w przestrzeni nazw 
"com.enetwiz.aspectorientedprogramming". Po odnalezieniu komponentu zostaje utworzony obiekt o id zgodnym z nazwa klasy. Przykladowo jezeli Spring 
odnajdzie komponent klasy HelloComponent() wowczas referencja/id tego komponentu nazywac sie bedzie "helloComponent".

Anotacja @Bean nad metoda - oznacza, ze kontekst Springa utworzy obiekt POJO o referencji/id zgodny z nazwa metody np.

*@Bean
public HelloBean helloBean() {
    return new HelloBean();
}*

oznacza to samo co:

*HelloBean helloBean = new HelloBean();*

W przypadku aplikacji AspectOrientedProgramming w konfiguracji ladowana jest obiekt aspektu/porady o nazwie LoggingAspect, ktory zawiera 
wszystkie punkty przeciecia.


**HelloComponent.java**

Komponent Springa, ktory w odroznieniu od beana moze byc wyszukiwany automatycznie po zdefiniowaniu anotacji @ComponentScan()


**LoggingAspect.java**

Klasa ta stanowi cos na ksztal pliku konfiguracyjnego dedykowanego tylko dla biblioteki AspectJ. Kazda klasa okreslana jako aspekt/porada jest 
oznaczona anotacja @Aspect.
Dodatkowo zawiera ona inne anotacje stanowiace tzw. punkty przeciecia:

 * @Before("execution(* ExampleComponent.doSomething(..))") - metoda okreslona ta anotacja zostanie wywolana na poczatku metody 
   ExampleComponent.doSomething()
 * @After("execution(* ExampleComponent.doSomething(..))") - metoda okreslona ta anotacja zostanie wywolana na koncu metody 
   ExampleComponent.doSomething()
 * @AfterReturning(pointcut = "execution(* ExampleComponent.returnSomething(..))", returning= "result") - metoda okreslona ta anotacja zostanie 
   wywolana po zwroceniu wartosci (return) przez metode ExampleComponent.returnSomething()

To o czym warto pamietac to fakt, ze porade/aspekt mozna stosowac dla wielu klas (a nawet calych przestrzeni nazw) jednoczesnie, dzieki czemu 
mozna stworzyc np. swietny system logowania zdarzen w aplikacji lub sprawdzac czy biezacy uzytkownik jest zalogowany (sprawdzajac to na 
poczatku kazdej metody). 
Reasumujac AOP uzywa sie wszedzie tam gdzie mamy do czynienia czynnosciami powszechnymi (jak rejestrowanie zdarzen, sprawdzanie statusu 
uzytkownika itp) w aplikacji. Czynnosci takie nie powinny byc bezposrednio definiowane w kodzie obiektu, gdy zmniejsza to jego czytelnosc, 
utrudnia testowanie obiektu oraz sprawia ze aplikacja staje sie podatna na bledy wynikajace z komplikacji kodu.


**HelloWorld.java**

Klasa ta zawieraja statyczna metode glowna: "main()", dzieki czemu program po kompilacji moze zostac uruchomiony.
W metodzie glownej tworzony jest kontekst Springa o nazwie "AnnotationConfigApplicationContext" - ten kontekst jest stosowany, gdy aplikacja ma 
korzystac z ustawien zdefiniowanych w anotacjach innych klas (np. klasy konfiguracyjnej AppConfig.java). 
Co wazne - Spring posiada rozne konteksty (tym samym nie jestesmy skazani tylko na anotacje jako podstawe konfiguracji frameworka).

W kolejnym wierszu:

*ExampleComponent component = (ExampleComponent) context.getBean("exampleComponent");*

nastepuje odwolanie do referencji komponentu ExampleComponent(), ktory zostal utworzony zaraz po uruchomieniu kontekstu aplikacji Springa 
(bazujac na instrukcji zawartych w anotacjach klas).

Fragment: 

*component.doSomething();
component.doMore();
component.returnSomething();*

stanowi klasyczne wywolanie metod komponentu. Jedyna roznica jest to, ze w tle dziala skonfigurowany przez nas  aspekt/porada
(patrz. AppConfig oraz LogginAspect), ktory w odpowiednich momentach wywola dodatkowe procesy (punkty przeciecia).