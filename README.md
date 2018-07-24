# Gestion de comptes bancaires
Le but de cet exercice consiste à implémenter une application permettant la gestion de comptes bancaires.
Cette application a été conçue et développée en JAVA/JEE avec l'utilisation du Spring boot, Web service(REST), JPA/Hibernate, Modelmapper(pour le mapping dto/entity), Log4j(pour la journalisation), Lombok, Mockito(pour les tests de 2 couches controller/service).

## Choix techniques
* L'application se compose de 3 couches Controller/Service/DAO.
* IOC est effectuée par Spring.
* La couche web est basée sur Spring mvc.
* Le mapping objet-relationnel est basé sur Hibernate implémentation JPA.
* Le mapping dto-entity est basé sur l'API modelmapper.
* La journalisation est basée sur l'API log4j.
* Les méthodes métier sont générées à la compilation par Lombok.

## Fonctionnalités
* Ajouter un client.
* Créer un compte bancaire (compte courant).
* Réaliser 3 opérations :
  * US1 - Effectuer un versement.
  * US2 - Effectuer un retrait.
  * US3 - Voir l'historique des opérations.

![ScreenShot](https://image.ibb.co/iq6rwy/banque_Account.png)

## Prérequis
* Eclipse IDE for JEE Developers (oxygen.2).
* Apache Maven.
* Lombok (Vous devez configurer lombok avec eclipse https://projectlombok.org/setup/eclipse).
* MySQL UI (MySql Workbench).
* Mysql-connector.
