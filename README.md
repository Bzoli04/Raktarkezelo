# Raktarkezelo webalkalmazas

Ez a projekt egy egyszeru raktarkezelo webalkalmazas Spring Boot alapon. A program termekek nyilvantartasara keszult: 
bejelentkezes utan meg lehet nezni a termeklistat, lehet termeket keresni nev alapjan, uj termeket felvenni, valamint meglevo termeket torolni. Egy termekhez nev, kategoria, mennyiseg es ar tartozik.

## Hasznalt technologiak
- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Thymeleaf
- H2 memoriabeli adatbazis
- Maven
- JUnit tesztek

- ## Inditas
Az alkalmazas a `warehouse` mappabol indithato.

```powershell
cd warehouse
mvn spring-boot:run
```


Linux vagy macOS rendszeren:
```bash
cd warehouse
mvn spring-boot:run
```
Sikeres inditas utan az alkalmazas alapertelmezetten a `8080`-as porton fut.
## Leallitas

Ha az alkalmazast terminalbol inditottad a `mvn spring-boot:run` paranccsal, akkor ugyanabban a terminalablakban igy lehet leallitani:

```text
Ctrl + C
```

## Fontos linkek
- Webalkalmazas fooldala: http://localhost:8080/
- Bejelentkezes: http://localhost:8080/login
- H2 adatbazis konzol: http://localhost:8080/h2-console
- 
## Bejelentkezesi adatok

- Felhasznalonev: `admin`
- Jelszo: `admin123`


## H2 adatbazis
A projekt H2 memoriabeli adatbazist hasznal, tehat az adatok az alkalmazas ujrainditasa utan torlodhetnek.

A H2 konzolban ezeket az adatokat kell megadni:

- JDBC URL: `jdbc:h2:mem:testdb`
- User Name: `sa`
- Password: uresen kell hagyni

## Teszteles

A tesztek Mavenbol futtathatok a `warehouse` mappabol:

```powershell
cd warehouse
mvn test
```

Linux vagy macOS rendszeren:

```bash
cd warehouse
mvn test
```
A projektben jelenleg ezek a tesztek vannak:

- `WarehouseApplicationTests`: ellenorzi, hogy a Spring alkalmazaskontextus elindul.
- `ProductServiceTest`: ellenorzi, hogy egy termek mentese utan kap adatbazis-azonositot.
- `ValidationTest`: ellenorzi a `Product` modell validacios szabalyait.

## A program mukodese roviden

Az alkalmazas inditas utan bejelentkezest ker. Sikeres belepes utan a fooldalon megjelenik a raktarban szereplo termekek listaja. 
A felhasznalo uj termeket tud rogzitani, a rendszer pedig ellenorzi, hogy a kotelezo adatok ki vannak-e toltve, a mennyiseg legalabb `1`, 
az ar pedig nagyobb mint `0`. A termekek az H2 adatbazisba kerulnek, a webes felulet pedig Thymeleaf sablonokkal jelenik meg.











