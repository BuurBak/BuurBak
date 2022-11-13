# BuurBak
BuurBak webapp voor het huren en verhuren van aanhangers!

Frontend built in [React](https://reactjs.org)

Backend built in [Spring Boot](https://spring.io/projects/spring-boot)

# Running BuurBak
## Requirements
| Category | Name | Version | Link | Description |
| --- | --- | --- | --- | --- |
| Dev | Temurin JDK | 17.0.4.1 | https://adoptium.net/installation/ | We use Temurin as our JDK for the backend |
| Dev | Node | 16.8.1 | https://nodejs.org/download/release/v16.18.1/ | Our frontend is developed in React node 16 |
| Dev | Maildev | 2.0.5 | https://github.com/maildev/maildev | Maildev is used in development to catch all outgoing emails |
| Dev | PostgreSQL | 14 | https://www.postgresql.org | PostgreSQL is our database for both development as production. |
| Testing | Docker | 20.10.20 | https://www.docker.com/products/docker-desktop/ | You can use the latest docker desktop to run the application in a mock production environment on your local machine. You can use this environment to test your new features before pushing them to production. |

## Environment variables

| Location | Name | Example value | Type | Description |
| ----------- | ----------- | ----------- | ----------- | ----------- |
| Backend | JWT_SECRET | secret | String | JWT secret for generating access tokens | 
| Backend | RANDOM_DATA | false | Boolean | Wether or nat the application should randomly generate data, for testing purposes, also generates a standard user in with username/email=`lucabergman@yahoo.com` and password=`hallo123` which you can quickly use for testing purposes |
| Backend | HOST | http://localhost:3000 | String | Under what url the application is hosted, must **NOT** include a slash (`/`) at the end |
| Backend | SPRING_PROFILES_ACTIVE | dev | String | Which spring application profile to use, defaults to `prod`, you must set it to dev in your development environment |
## Google cloud storage
Wij gebruiken google cloud storage als object storage voor al onze images/ files. De images worden ook van hun servers opgehaald, waardoor de load op onze api veel lager is dan als het via onze api server/ database moest gaan. Hiervoor moet je een file genaamd, `gcp-account-file.json` hebben en in de map /api/src/main/resources zetten. Deze file is secret omdat het de credentials van onze google cloud bevat. **Deel deze dus niet!**. Vraag de Luca Bergman a.k.a. @spark-156 om deze file.

## Running development environment locally 
You can skip the downloading of PostgreSQL and maildev locally by running them inside of docker. To do so please run the commands: `docker compose -f docker-compose.dev.yml up` in your terminal. That will launch all the requirements for the backend. You can access maildev under `http://mail.localhost/` and an instance of adminer under `http://adminer.localhost/`. You can find the hostname, username and password for the database in the `docker-compose.dev.yml` file.

## Running the production environment locally 
You can run, build and test the production environment with `docker compose up --build`. This will run all the containers necessary for the backend. You can access maildev under `http://mail.localhost/` and an instance of adminer under `http://adminer.localhost/`. You can find the hostname, username and password for the database in the `docker-compose.yml` file.

# Contributing to BuurBak
## We Develop with Github
We use github to host code, to track issues and feature requests, as well as accept pull requests.

## We Use [Github Flow](https://guides.github.com/introduction/flow/index.html), So All Code Changes Happen Through Pull Requests
Pull requests are the best way to propose changes to the codebase (we use [Github Flow](https://guides.github.com/introduction/flow/index.html)). We actively welcome your pull requests:

1. Clone the repo and create your branch from `dev`.
2. Name the branch in the following format: ```US-XXX-name-of-user-story```.
2. If you've added code that should be tested, add tests.
3. If you've changed APIs, update the documentation.
4. Ensure the test suite passes.
5. Make sure your code lints.
6. Issue that pull request and make sure to merge into the `dev` branch!

## Report bugs using Github's [issues](https://github.com/BuurBak/BuurBak/issues)
We use GitHub issues to track public bugs. Report a bug by [opening a new issue](https://github.com/BuurBak/BuurBak/issues/new); it's that easy!
