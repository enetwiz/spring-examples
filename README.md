Spring framework - przyklady
============================

Glownym zalozeniem tego repozytorium jest prezentacja moich umiejetnosci z zakresu uzycia technologii Spring Framework. Jednakze poza tym stanowi on swietna baze do nauki Spring'a dzieki bogato opisanym przykladom. Poruszam tu wiele popularnych przypadkow uzycia, ktore moga okazac sie przydatne jak rowniez niezbedne do prawidlowego rozumienia zasad dzialania ww technologii. 

Przyklady podzielilem na przejrzyste moduly ( patrz katalog [modules/](modules/) ), ktorych pelna liste znajdziesz ponizej. Co wazne - wlozylem sporo wysilku w to, aby mozliwie jak najlepiej opisac dzialanie kazdego z nich. Dlatego mam nadzieje, ze stanie sie to skarbnica wiedzy zarowni dla mnie jak i dla Ciebie.

Jezeli jestes programista/programistka pragnacym szybko nauczyc sie Springa polecam rozpoczecie nauki od swietnej ksiazki: ``Spring w akcji`` Craig'a Walls'a, gdyz stanowi ona genialne wprowadzenie do tematyki oraz uzupelnienie moich wlasnych przykladow w czesc teoretyczna. Z kolei jezeli nigdy nie miales/mialas doczynienia z jezykiem JAVA swoja przygode warto rozpoczac od ``Java. Podstawy`` autorstwa: Gary Cornell, Cay S. Horstmann.

Pobierajac repozytorium uzyskasz pelnowartosciowy projekt Maven, w ktorym kazdy przyklad stanowi oddzielny modul. Dodatkowo kazdy modul zawiera czesc opisowa, ktora z kolei doglebnie wyjasnia jak on dziala :).


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
 * [HibernateTransactions](modules/HibernateTransactions) - aplikacja pokazujaca przyklady roznych rodzajow transakcji z uzyciem Hibernate ORM
 * [HibernateRelationship](modules/HibernateRelationship) - aplikacja pokazujaca przyklad relacji (OneToMany, ManyToMany) miedzy tabelami w bazie danych, w srodowisku Hibernate ORM
 * [HibernateInheritance](modules/HibernateInheritance) - aplikacja pokazujaca rozne strategie dziedziczenia w ramach encji (tj. jak odwzorowac dziedziczenie w bazie danych)
 * [PropertyAccess](modules/PropertyAccess) - aplikacja prezentujaca przyklady dostepu do ustawien zapisanych w plikach *.properties
