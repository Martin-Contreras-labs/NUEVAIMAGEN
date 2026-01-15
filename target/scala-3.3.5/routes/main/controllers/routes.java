// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes3.routes

package controllers;

import routes3.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseMnuMantencion MnuMantencion = new controllers.ReverseMnuMantencion(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseMnuReportes MnuReportes = new controllers.ReverseMnuReportes(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseMnuMantencion MnuMantencion = new controllers.javascript.ReverseMnuMantencion(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseMnuReportes MnuReportes = new controllers.javascript.ReverseMnuReportes(RoutesPrefix.byNamePrefix());
  }

}
