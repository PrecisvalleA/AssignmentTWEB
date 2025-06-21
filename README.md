# AssignmentTWEB

FrameVerse - IUM-TWEB 2024/2025

FrameVerse is a full-stack platform for fans and journalists to access data about films, to explore, filter and analyze comprehensive movie data. The system integrates a large movie dataset and it offers an interactive user-friendly interface.

## Technologies
- Frontend: HTML, CSS, JavaScript
- Backend: Express.js, Java SpringBoot
- Databases: MongoDB, Postgres
- Data Visualization: Jupyter Notebooks


## Data Cleaning and Analysis (Python and Jupyter Notebooks)
#Cleaning:
The project starts with csv cleaning and analysis using Pandas library. Csv files include movies, actors, countries, crews, languages, genres, languages, themes, posters, releases, studios, the oscar awards and reviews. 
Reviews.csv is the only dynamic dataset because it changes more frequently.
The data preparation involved: 
- Loading and parsing all CSV datasets.
- Handling missing values and duplicates.
- Standardizing data types and data formats.
- Validating consistency and referential integrity.
- Preparing fully cleaned datasets ready for SQL (PostgreSQL) and NoSQL (MongoDB) ingestion.
#Analysis:

## Backend Servers Implementation:
1) SpringBoot backend (static data)
- Language: Java (version 19)
- Framework: SpringBoot
- DataBase: PostgreSQL
- Build Tool: Gradle
Handled entities: Movies, Actors, Crews, Countries, Languages, Genres, Themes, Posters, Releases, Studios, The_Oscar_Awards.
#RESTful API Endpoints:
  1) Retrieve movies and detailed information.
  2) Search movies by keywords.
  3) Filter movies by rating, release year, genres and duration.
  4) Full pagination support for scalable querying.
  5) Create or delete movie entries.
 -Documentation:
  1) Complete Swagger (OpenAPI 3) dovumentation is provided.
  2) Code-level JavaDoc is present in all source files.

2) Express + MongoDB Microservice (Dynamic reviews)
- Language: Node.js
- Framework: Express.js
- DataBase: MongoDB (NoSQL)

#Main features: 
 1) Storage of Rotten Tomatoes reviews.
 2) Rich querying on reviews per movie or critic.
 3) Filters include Top Critic status, review type (Fresh/Rotten), sort by date.
 4) Pagination support for scalable review browsing.

#Endpoints:
 1) Retrieve reviews by movie or critic.
 2) Submit new reviews.
 3) Complex query hanfling with flexible parameters.

## Central API Gateway (Express.js)
A Node.js Express main server is designed to rapidly and efficiently process requests from the other two servers, then forward them to the frontend.
#Main features: 
 1) Acts as a router (no heavy logic)
 2) Forwards /spring requests to the springboot server
 3) Forwards /mongo requests to the Express server
 4) Simplifies frontend/backend integration
 5) Uses Axios library for HTTP forwarding between internal services

## Frontend Web Application
The client-side application was developed using standard web technologies, without any advanced frontend framework.
- Technologies: HTML5, CSS3 (Bootstrap 5), Vanilla Javascript.
- Fully responsive design.
- No frameworks used.

#Main functionalities:
Homepage (index.html):
- Navbar with the search bar.
- Filters by: star rating (interactive clickable stars), release year range, genre selection, sort order (rating, name asc/desc).
- Movie list paginated with loading.

Movie Details Page (details.html):
- General info (title, plot, tagline ...)
- Genres, themes, languages, countries, studios, world releases.
- Cast and crew lists.
- Oscars.
- Reviews (live querying to MongoDB microservice): filters by Top Critic, review type (fresh or rotten, date sorting).
- Design notes: Smooth section navigation with in-page scrolling.

## Project Setup and Launch Instructions (backend and frontend, NO data cleaning)
Once you have the databases populated, then you need to install:
- Java 19+
- Gradle
- Node.js 18+
- npm 9+
- MongoDB (running locally on port mongodb://localhost:27017/)
- PostgreSQL (running locally on port 5432)

To start Spring Boot server:
- Navigate to the backend Spring Boot folder (cd backend/springboot)
- Build and run the spring boot server using gradle (using IDE shortcuts or "./gradlew bootRun")
- The server will run on "http://localhost:8080" (swagger documentation available on "http://localhost:8080/swagger-ui/index.html")
To start Express MongoDB server:
- Navigate to express folder (cd backend/express)
- Install dependencies: npm install
- Start the server: npm start
- The server will run on "http://localhost:3001"
To start Main Server:
- Navigate to main_server folder (cd backend/main_server)
- Install dependencies: npm install
- Start the server: npm start
- The server will run on "http://localhost:3000"
Frontend FrameVerse Web App:
- Open index.html file in a browser ("frontend/index.html). No build is required.

Ports recap:
- SpringBoot (java): 8080
- Express Mongo Reviews (node.js): 3001
- Express Main server: 3000
- Frontend: Html on browser

## Technologies Summary:
- Backend (static): Java Spring Boot with MySQL
- Microservice (dynamic): Node.js Express with MongoDB
- Central Router: Node.js Express API Gateway
- Frontend: HTML5, CSS3, Bootstrap 5, Vanilla JavaScript
- Data Preparation: Python Jupyter Notebooks with Pandas and Matplotlib/Seaborn


