
import com.typesafe.config.Config;

import play.*;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.mvc.Http.*;
import play.mvc.*;

import javax.inject.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Singleton
public class ErrorHandler extends DefaultHttpErrorHandler {

  @Inject
  public ErrorHandler(
      Config config,
      Environment environment,
      OptionalSourceMapper sourceMapper,
      Provider<Router> routes) {
    super(config, environment, sourceMapper, routes);
  }

  protected CompletionStage<Result> onProdServerError(RequestHeader request, UsefulException exception) {
	  

	  
    return CompletableFuture.completedFuture(
        Results.internalServerError("A server error occurred: " + exception.getMessage()));
  }

  
  
  
  public CompletionStage<Result> onClientError(RequestHeader request, int statusCode, String message) {
    if (statusCode == 400) {
      return onBadRequest(request, message);
    } else if (statusCode == 403) {
      return onForbidden(request, message);
    } else if (statusCode == 404) {
      return onNotFound(request, message);
    } else if (statusCode >= 400 && statusCode < 500) {
      return onOtherClientError(request, statusCode, message);
    } else {
      throw new IllegalArgumentException("onClientError invoked with non client error status code " + statusCode + ": " + message);
    }
  }
  
  
  
}