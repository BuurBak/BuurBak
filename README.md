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
If you want to work on a user story you have to create a new branch for it. This branch must be based on the dev branch. You will name the branch the same as the title of the user story in Trello. When you are done working on the user story you must create a merge request back in to dev.


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

