# Gestion de comptes bancaires
Le but de cet exercice consiste à implémenter une application permettant la gestion de comptes bancaires.
Cette application a été conçue et développée en JEE avec l'utilisation du Spring boot, Web service(REST), JPA/Hibernate, Modelmapper(pour le mapping dto/entity), log4j(pour la journalisation), mockito(pour les tests de 2 couches controller/service).


## Choix technique
* L'application se compose de 3 couches Controller/Service/DAO.
* IOC est effectuée par Spring.
* La couche web est basée sur Spring mvc.
* Le mapping objet-relationnel est basé sur Hibernate implémentation JPA.
* Le mapping dto-entity est basé sur l'API modelmapper.
* La journalisation est basée sur l'API log4j.

## Fonctionnalités
* Ajouter un client.
* Créer un compte bancaire (compte courant).
* Réaliser 3 opérations :
  * Effectuer un versement.
  * Effectuer un retrait.
  * Voir l'historique des opérations.
