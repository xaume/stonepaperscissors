# Stone Paper Scissors
Simple implementation of [Stone Paper Scissors](https://en.wikipedia.org/wiki/Rock_paper_scissors) 
game using Spring Boot and Angular.

## Running instances
The applications are deployed to Heroku and available in the following links:
* Backend: https://jaime-sps-backend.herokuapp.com/
* Frontend: https://jaime-sps-frontend.herokuapp.com/

## Local development

### Backend
Requires Maven and Java 11.

```bash
cd backend
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

The backend will be available at http://localhost:9080/sps/.

SwaggerUI is available at http://localhost:9080/sps/swagger-ui.html.

### Frontend
Requires NodeJS and AngularCLI.

```bash
cd frontend
npm install
ng serve -o
```

The frontend application will be available at http://localhost:4200.