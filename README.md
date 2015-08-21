Spring framework - przyklady
============================

W ponizszym repozytorium znajduja sie przyklady / code snippety, ktore pozwalaja okreslic poziom moich umiejetnosci / wiedzy dotyczacej 
popularnego frameworka Spring.

Wszystkie wykonane przeze mnie przyklady znajduja sie w katalogu [modules/](modules/) . 

Pobierajac repozytorium uzyskasz pelnowartosciowy projekt Maven, w ktorym kazdy przyklad stanowi oddzielny modul. Dodatkowo kazdy modul zawiera 
czesc opisowa, ktora z kolei wyjasnia to w jaki sposob rozumiem dzialanie wybranej funkcjonalnosci frameworka.



Lista modulow
-------------

 * [HelloWorldXml](modules/HelloWorldXml/) - najprostsza z mozliwych aplikacja drukujaca napis: "Hello World!" - aplikacja konfigurowana przez XML
 * [HelloWorld](modules/HelloWorld/) - najprostsza z mozliwych aplikacja drukujaca napis: "Hello World!" - aplikacja konfigurowana w calosci przez anotacje
 * [HelloWorldWeb](modules/HelloWorldWeb/) - najprostsza z mozliwych aplikacja internetowa (strona internetowa) - drukujaca napis: "Hello World!"
 * [ComponentScope](modules/ComponentScope/) - aplikacja prezentujaca moja znajomosc pojecia "zakres komponentu" (parametr scope)
 * [SingletonFactory](modules/SingletonFactory/) - aplikacja pokazujaca w jaki sposob zainicjowac klase utworzona na bazie wzorca Singleton
 * [InitDestroyMethods](modules/InitDestroyMethods/) - aplikacja pokazuje w jaki sposob mozemy wplywac na cykl zycia obiektu za pomoca anotacji
 * [DependencyInjection](modules/DependencyInjection/) - aplikacja prezentuje technike wstrzykiwania zaleznosci w srodowisku Springa
 * [AspectOrientedProgramming](modules/AspectOrientedProgramming/) - aplikacja pokazuje praktyczne zastosowanie programowania aspektowego z wykorzystaniem AspectJ
 * [HibernateUsage](modules/HibernateUsage) - aplikacja pokazujaca przyklad polaczenia ORM'a Hibernate 4.x ze Springiem
 * [HibernateWebUsage](modules/HibernateWebUsage) - aplikacja internetowa pokazujaca przyklad polaczenia ORM'a Hibernate 4.x ze Springiem
 * [HibernateRelationship](modules/HibernateRelationship) - aplikacja pokazujaca przyklad relacji (OneToMany, ManyToMany) miedzy tabelami w bazie danych, w srodowisku Hibernate ORM
 * [HibernateInheritance](modules/HibernateInheritance) - aplikacja pokazujaca rozne strategie dziedziczenia w ramach encji (tj. jak odwzorowac dziedziczenie w bazie danych)