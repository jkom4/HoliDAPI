# HolliD API – API REST de planification de vacances

## Description

**HolliD API** est une API RESTful permettant la planification de vacances. L'application permet de gérer les participants, définir des destinations, planifier des activités, et consulter les prévisions météorologiques. Elle utilise des services externes comme **OpenWeather** et **Google Places API** pour offrir une expérience complète.

## Fonctionnalités

- **Gestion des participants** : Ajout et gestion des participants pour un voyage.
- **Définition des destinations** : Recherche et sélection de destinations via l'API Google Places.
- **Planification des activités** : Ajout d'activités aux itinéraires des voyages.
- **Consultation des prévisions météorologiques** : Intégration avec OpenWeather pour fournir des prévisions météo pour les destinations.

## Technologies utilisées

- **Java Spring Boot** : Framework backend.
- **Oracle** : Base de données relationnelle pour le stockage des données.
- **OpenWeather API** : Utilisé pour récupérer les prévisions météorologiques.
- **Google Places API** : Utilisé pour récupérer des informations sur les destinations.
- **OAuth** : Gestion de l'authentification sécurisée.

## Installation

1. Clonez le repository :
    ```bash
    git clone https://github.com/tonusername/holidAPI.git
    cd holidAPI
    ```

2. Configurez votre fichier `application.properties` pour y ajouter vos clés API (OpenWeather, Google Places) et les informations de connexion à votre base de données Oracle.

3. Compilez et exécutez l'application :
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. L'API sera disponible sur `http://localhost:8080`.

## Endpoints principaux

- **GET /participants** : Récupérer la liste des participants.
- **POST /participants** : Ajouter un nouveau participant.
- **GET /destinations** : Rechercher une destination via Google Places.
- **GET /weather** : Obtenir les prévisions météo d'une destination via OpenWeather.
- **POST /activities** : Ajouter une activité à un voyage.

## Configuration des API externes

- **OpenWeather API** : Inscrivez-vous sur [openweathermap.org](https://openweathermap.org) pour obtenir une clé API.
- **Google Places API** : Obtenez une clé API via la [Google Cloud Console](https://console.cloud.google.com/).

## Licence

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.
