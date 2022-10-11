# BuurBak
BuurBak webapp voor het huren en verhuren van aanhangers!

## Git workflow
### Main branch
No direct pushes, only MR’s are allowed
Production ready, no bugs allowed
### Dev branch
No direct pushes, only MR’s are allowed
Semi-production ready, bugs are allowed but must be fixed before they get pushed to the main prod branch.
### Creating a feature or bugfix branch
If you want to work on a user story you have to create a new branch for it. This branch must be based on the dev branch. You will name the branch as such:

```#US-XXX-name-of-user-story```

Where `XXX` is the number. When you are done working on the user story you must create a merge request back in to dev that has to be reviewed by a team member.

# Setup dev environment

Ik (Luca) gebruik intellij IDEA als mijn IDE. In deze IDE open ik de /api folder, dat zorgt er namelijk voor dat alles zoals code completion/ building en running werkt. 

### JDK

Wij gebruiken temurin 17.0.4.1+1 als JDK. Dit doen wij via docker. 

### Postgres setup commands
Als database gebruiken wij nu Postgres, het is belangrijk dat je die op je localhost of ergens draaiende hebt. De commands om een fresh postgres install klaar te maken voor development zijn als volgt. Zorg er wel voor dat je al in de postgres terminal zit met `psql`.

    CREATE DATABASE buurbak;
    CREATE USER buurbak WITH PASSWORD 'buurbak';
    GRANT ALL PRIVILEGES ON DATABASE buurbak TO buurbak;

Zorg er dan ook voor dat je in de /api/src/main/resources/application.properties file je de juiste url uncomment. Namelijk met `localhost` i.p.v. `db`.
 
 ### Mail server
 Tijdens het registreren van een nieuwe gebruiker moet zijn/ haar email geconfirmed worden voordat het account enabled is. Hiervoor moet een mail verstuurd worden. In onze dev environment gebruiken wij daar [MailDev](https://maildev.github.io/maildev/) voor. Deze is via npm geinstalleerd en wordt ook via npm gerund op onze lokale dev environment.

Installatie stappen (zorg ervoor dat npm al geinstalleerd is!):

1. Om te installeren: `npm i -g maildev`
2. Om te runnen: `maildev`

Beiden commands in de terminal uitvoeren.



### Docker 

Onze production environment runt in docker. development doen wij buiten docker. 

### Environment variables
Om ervoor te zorgen dat je de goede environment running hebt in development is het cruciaal dat je de dev environment gebruikt voor Java. Dat doe je als volgt:

1. Edit configurations
2. Voeg `--spring.profiles.active=dev` toe aan je VM CLI options OF voeg `SPRING_PROFILES_ACTIVE=dev` toe aan je environment variables.
3. Voeg `JWT_SECRET=secret` aan de environment variables
