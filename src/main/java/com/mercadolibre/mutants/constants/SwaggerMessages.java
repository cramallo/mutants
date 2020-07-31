package com.mercadolibre.mutants.constants;

public class SwaggerMessages {
    public static final String getSuccess = "Successfully found resource";
    public static final String createSuccess = "Successfully created resource";
    public static final String badRequest = "The resource could not be retrieved for bad request";
    public static final String notFound = "The resource you were trying to reach is not found";
    public static final String forbidden = "The resource you were trying to create is not mutant";
    public static final String internalServerError = "The resource could not be retrieved for internal server error";

    private SwaggerMessages() {
        throw new AssertionError();
    }
}
