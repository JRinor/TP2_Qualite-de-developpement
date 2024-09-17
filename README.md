# TP API - Spring Boot User Management

Ce projet est une API REST développée avec Spring Boot pour la gestion des utilisateurs.

### Configuration du projet

### Configuration du projet

1. Clonez le repository :
2. 
    ```sh
    git clone https://github.com/JRinor/TP2_Qualite-de-developpement.git
    ```

2. Assurez-vous d'avoir Java 17 et Maven installés :
    ```sh
    java -version
    mvn -version
    ```

3. Importez le projet dans votre IDE préféré en tant que projet Maven.
### Structure du projet

- `com.example.tpapi.controller` : Contient les contrôleurs REST.
- `com.example.tpapi.model` : Contient les entités JPA.
- `com.example.tpapi.repository` : Contient les interfaces repository.
- `com.example.tpapi.test` : Contient les tests unitaires.

### Base de données

Le projet utilise H2, une base de données en mémoire. La configuration se trouve dans `application.properties`.

### Exécution des tests

Les tests unitaires utilisent TestNG. Exécutez-les via votre IDE ou avec la commande Maven :

```sh
mvn test
```

### Documentation de l'API

La documentation Swagger est générée automatiquement. Accédez-y via :

[http://localhost:8081/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Pour les utilisateurs de l'API

### Endpoints disponibles

1. **GET** `/users` : Récupère tous les utilisateurs.
2. **GET** `/users/{id}` : Récupère un utilisateur par son ID.
3. **POST** `/users` : Crée un nouvel utilisateur.
4. **PUT** `/users/{id}` : Met à jour un utilisateur existant.
5. **DELETE** `/users/{id}` : Supprime un utilisateur.

### Exemple d'utilisation avec Postman

1. **Créer un utilisateur** :
    - Méthode : POST
    - URL : `http://localhost:8080/users`
    - Body (JSON) :
      ```json
      {
        "firstName": "John",
        "lastName": "Doe"
      }
      ```
    - Commande `curl` :
      ```sh
      curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"firstName": "John", "lastName": "Doe"}'
      ```

2. **Récupérer tous les utilisateurs** :
    - Méthode : GET
    - URL : `http://localhost:8080/users`
    - Commande `curl` :
      ```sh
      curl -X GET http://localhost:8080/users
      ```

3. **Récupérer un utilisateur spécifique** :
    - Méthode : GET
    - URL : `http://localhost:8080/users/1`
    - Commande `curl` :
      ```sh
      curl -X GET http://localhost:8080/users/1
      ```

4. **Mettre à jour un utilisateur** :
    - Méthode : PUT
    - URL : `http://localhost:8080/users/1`
    - Body (JSON) :
      ```json
      {
        "firstName": "Jane",
        "lastName": "Doe"
      }
      ```
    - Commande `curl` :
      ```sh
      curl -X PUT http://localhost:8080/users/1 -H "Content-Type: application/json" -d '{"firstName": "Jane", "lastName": "Doe"}'
      ```

5. **Supprimer un utilisateur** :
    - Méthode : DELETE
    - URL : `http://localhost:8080/users/1`
    - Commande `curl` :
      ```sh
      curl -X DELETE http://localhost:8080/users/1
      ```

5. **Supprimer un utilisateur** :
    - Méthode : DELETE
    - URL : `http://localhost:8080/users/1`

   