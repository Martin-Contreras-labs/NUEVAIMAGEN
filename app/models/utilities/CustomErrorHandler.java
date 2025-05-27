package models.utilities;


import play.Logger;
import play.http.HttpErrorHandler;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Singleton
public class CustomErrorHandler implements HttpErrorHandler {
    private static final Logger.ALogger logger = Logger.of(CustomErrorHandler.class);

    @Override
    public CompletionStage<Result> onClientError(Http.RequestHeader request, int statusCode, String message) {
        if(statusCode == 404){
            return CompletableFuture.completedFuture(Results.notFound());
        }
        logger.warn("[ClientError] {} {} - {}", statusCode, message, request.path());
        return CompletableFuture.completedFuture(
                Results.status(statusCode, Json.newObject()
                        .put("error", message)
                        .put("status", statusCode)
                        .put("path", request.path()))
        );
    }

    @Override
    public CompletionStage<Result> onServerError(Http.RequestHeader request, Throwable exception) {
        logger.error("[ServerError] " + exception.getMessage(), exception);
        return CompletableFuture.completedFuture(
                Results.internalServerError(Json.newObject()
                        .put("error", "Internal Server Error")
                        .put("message", exception.getMessage())
                        .put("path", request.path()))
        );
    }

}


