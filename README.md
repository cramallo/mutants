# MUTANTS CODE CHALLENGE

## Exercise



## Technologies 

- Java 11
- Spring Boot with Gradle
- Junit for testing
- JPA for ORM
- PostgresSQL for database
- Swagger for documentation
- Heroku for deployment

## About the solution

I tried to make the code the most modularized as possible. I used inherence for Classes that had similiar behavior and 
dependency injection when it's needed.

According to the search indeed I created for each direction a boolean matrix. This matrix decrease the searching time,
because it was made for don't pass for the same position when searching in the loop in some direction.

I added Swagger for API information.

The code coverage is 88%

## Deployed endpoints

### Post a dna sequence and get the response of the mutation created or 403 if it's not mutant.

The endpoint will check if the dna is or not mutants. A service will save the information about if it's human or 
mutant and the actual ratio of mutants according with this las request for stats purposes.

If mutant then the DNA will be save in the database.

https://magneto-mutants.herokuapp.com/mutants
 
### Get stats about mutants ratio for all safe
 
This endpoint will return the mutants ratio about all the POST requests made, and see how many of the DNA were human or mutant.
 
https://magneto-mutants.herokuapp.com/mutants/stats


## Instructions to execute the program locally

- Download **SKDMAN**, follow instructions [here](https://sdkman.io/install)
- Install Java 11 with SKDMAN, follow the example [here](https://sdkman.io/usage)
- Install PostgresSQL, you can download it from [here]( https://www.postgresql.org/download/)
- Changes the file application.properties accoding with your port and username and password database configuration




