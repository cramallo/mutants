# Mutants code challenge

## Exercise

### Check if DNA sequence is mutant or not and persist stats. 
The exercise consist on create an endpoint that will receive a DNA sequence by a **POST** request made like this:

```
{
    String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
}
```

With this I have to create a method that will check if it's a valid sequence, check if this dna is human or mutant,
persist the stats about the human count, mutant count and mutant ratio, and then persist the dna in a database if the DNA
is mutant.

The characters allowed in sequence must be **(A,T,C,G)**. And the matrix must be **NXN**.

### Get the last persisted stats

The second endpoint is made to request about the last stats(GET request), that were persisted and it was explained in the first part.

Example of asked response:

```
{“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
```

## Technologies 

- Java 11
- Spring Boot with Gradle
- Junit for testing
- JPA for ORM
- PostgresSQL for database
- Swagger for documentation
- Heroku for deployment

## About the solution

- I tried to make the code the most modularized as possible. I used inheritance for Classes that had similar behavior and 
dependency injection when it's needed.

- According to the search indeed I created for each direction a boolean matrix. This matrix decrease the searching time,
because it was made for don't pass for the same position when searching in a loop in some direction.

- I added Swagger for API information.

- The code coverage is 88%.

## Deployed endpoints

**Post a DNA sequence and get the response of the mutation created or 403 if it's not mutant**

Below is the endpoint to send a **POST** request:

```
https://magneto-mutants.herokuapp.com/mutants
```

You can check the information about this endpoint in [swaager](https://magneto-mutants.herokuapp.com/swagger-ui.html#/mutant-controller/isMutantUsingPOST).
 
**Get stats about mutants ratio**
 
Below is the **Get** request to get the stats.

```
https://magneto-mutants.herokuapp.com/mutants/stats
```
You can check the information about this endpoint in [swaager](https://magneto-mutants.herokuapp.com/swagger-ui.html#/mutant-controller/getLastStatsUsingGET).


## Instructions to execute the program locally

- Download [SDKMAN](https://sdkman.io/install).
- Install Java 11 with SKDMAN, follow the example [here](https://sdkman.io/usage).
- Install [PostgresSQL]( https://www.postgresql.org/download/).
- Changes the file application.properties according with your port and username and password database configuration.




