// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:8
  HomeController_18: controllers.HomeController,
  // @LINE:14
  Apis_2: controllers.Apis,
  // @LINE:38
  MnuMantencion_16: controllers.MnuMantencion,
  // @LINE:91
  MnuToolsAdmin_3: controllers.MnuToolsAdmin,
  // @LINE:102
  MnuAyuda_14: controllers.MnuAyuda,
  // @LINE:109
  MnuOdo_12: controllers.MnuOdo,
  // @LINE:159
  MnuOdoAppWeb_0: controllers.MnuOdoAppWeb,
  // @LINE:259
  AppCPanel_10: controllers.AppCPanel,
  // @LINE:273
  MnuPlanes_11: controllers.MnuPlanes,
  // @LINE:342
  MnuReportes_7: controllers.MnuReportes,
  // @LINE:584
  MnuDisponibilidad_8: controllers.MnuDisponibilidad,
  // @LINE:603
  MnuQr_17: controllers.MnuQr,
  // @LINE:649
  MnuPpto_13: controllers.MnuPpto,
  // @LINE:664
  MnuCotizar_4: controllers.MnuCotizar,
  // @LINE:768
  MnuCompras_6: controllers.MnuCompras,
  // @LINE:801
  MnuMovimientos_9: controllers.MnuMovimientos,
  // @LINE:879
  MnuBajas_5: controllers.MnuBajas,
  // @LINE:896
  MnuBodegas_19: controllers.MnuBodegas,
  // @LINE:944
  MnuTablas_1: controllers.MnuTablas,
  // @LINE:1076
  Assets_15: controllers.Assets,
  // @LINE:1080
  routes2_Routes_0: routes2.Routes,
  // @LINE:1081
  routes3_Routes_1: routes3.Routes,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:8
    HomeController_18: controllers.HomeController,
    // @LINE:14
    Apis_2: controllers.Apis,
    // @LINE:38
    MnuMantencion_16: controllers.MnuMantencion,
    // @LINE:91
    MnuToolsAdmin_3: controllers.MnuToolsAdmin,
    // @LINE:102
    MnuAyuda_14: controllers.MnuAyuda,
    // @LINE:109
    MnuOdo_12: controllers.MnuOdo,
    // @LINE:159
    MnuOdoAppWeb_0: controllers.MnuOdoAppWeb,
    // @LINE:259
    AppCPanel_10: controllers.AppCPanel,
    // @LINE:273
    MnuPlanes_11: controllers.MnuPlanes,
    // @LINE:342
    MnuReportes_7: controllers.MnuReportes,
    // @LINE:584
    MnuDisponibilidad_8: controllers.MnuDisponibilidad,
    // @LINE:603
    MnuQr_17: controllers.MnuQr,
    // @LINE:649
    MnuPpto_13: controllers.MnuPpto,
    // @LINE:664
    MnuCotizar_4: controllers.MnuCotizar,
    // @LINE:768
    MnuCompras_6: controllers.MnuCompras,
    // @LINE:801
    MnuMovimientos_9: controllers.MnuMovimientos,
    // @LINE:879
    MnuBajas_5: controllers.MnuBajas,
    // @LINE:896
    MnuBodegas_19: controllers.MnuBodegas,
    // @LINE:944
    MnuTablas_1: controllers.MnuTablas,
    // @LINE:1076
    Assets_15: controllers.Assets,
    // @LINE:1080
    routes2_Routes_0: routes2.Routes,
    // @LINE:1081
    routes3_Routes_1: routes3.Routes
  ) = this(errorHandler, HomeController_18, Apis_2, MnuMantencion_16, MnuToolsAdmin_3, MnuAyuda_14, MnuOdo_12, MnuOdoAppWeb_0, AppCPanel_10, MnuPlanes_11, MnuReportes_7, MnuDisponibilidad_8, MnuQr_17, MnuPpto_13, MnuCotizar_4, MnuCompras_6, MnuMovimientos_9, MnuBajas_5, MnuBodegas_19, MnuTablas_1, Assets_15, routes2_Routes_0, routes3_Routes_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_18, Apis_2, MnuMantencion_16, MnuToolsAdmin_3, MnuAyuda_14, MnuOdo_12, MnuOdoAppWeb_0, AppCPanel_10, MnuPlanes_11, MnuReportes_7, MnuDisponibilidad_8, MnuQr_17, MnuPpto_13, MnuCotizar_4, MnuCompras_6, MnuMovimientos_9, MnuBajas_5, MnuBodegas_19, MnuTablas_1, Assets_15, routes2_Routes_0, routes3_Routes_1, prefix)
  }

  private val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ping/""", """controllers.HomeController.ping()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """Mada_APIs""", """controllers.Apis.Mada_APIs(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """authenticar/""", """controllers.Apis.Mada_APIs(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """authenticar/""", """controllers.Apis.authenticar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pingToken/""", """controllers.Apis.pingToken(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """listadoDeBodegas/""", """controllers.Apis.listadoDeBodegas(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientosEntreFechas/""", """controllers.Apis.movimientosEntreFechas(request:Request, desdeAAMMDD:String, hastaAAMMDD:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """findBodega/""", """controllers.Apis.findBodega(request:Request, id_bodegaEmpresa:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """findCliente/""", """controllers.Apis.findCliente(request:Request, id_cliente:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """findProyecto/""", """controllers.Apis.findProyecto(request:Request, id_proyecto:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """inventarios_al_dia/""", """controllers.Apis.inventarios_al_dia(request:Request, fechaCorte:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """estadoDePagoPorPeriodo/""", """controllers.Apis.estadoDePagoPorPeriodo(request:Request, desdeAAMMDD:String, hastaAAMMDD:String, uf:Double, usd:Double, eur:Double)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """totalesEPporPeriodo/""", """controllers.Apis.totalesEPporPeriodo(request:Request, desdeAAMMDD:String, hastaAAMMDD:String, uf:Double, usd:Double, eur:Double)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """matrizInventarioPorCoti/""", """controllers.Apis.matrizInventarioPorCoti(request:Request, fechaCorte:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """consolidadoPorGrupoMeses/""", """controllers.Apis.consolidadoPorGrupoMeses(request:Request, fechaCorte:String, cantMeses:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """consolidadoPorEquipoMeses/""", """controllers.Apis.consolidadoPorEquipoMeses(request:Request, fechaCorte:String, cantMeses:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesPorPeriodo/""", """controllers.Apis.ajustesPorPeriodo(request:Request, desdeAAMMDD:String, hastaAAMMDD:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """estadoBodegasJson/""", """controllers.Apis.estadoBodegasJson(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheTodoResumenJson/""", """controllers.Apis.hoheTodoResumenJson(request:Request, desdeAAMMDD:String, hastaAAMMDD:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """report""", """controllers.MnuMantencion.mantWebInicio0(id_equipo:Long ?= 0)"""),
    ("""GET""", this.prefix, """controllers.HomeController.inicio()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """vistaPrincipal/""", """controllers.HomeController.vistaPrincipal(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """home/""", """controllers.HomeController.home(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """homeOdoVentasWeb/""", """controllers.HomeController.homeOdoVentasWeb(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """homeOdoAutorizaVentasWeb/""", """controllers.HomeController.homeOdoAutorizaVentasWeb(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """usuarioModalUpdate2""", """controllers.HomeController.usuarioModalUpdate2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """usuarioValida2""", """controllers.HomeController.usuarioValida2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """usuarioUpdate2""", """controllers.HomeController.usuarioUpdate2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recuperarClave/""", """controllers.HomeController.recuperarClave(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """admin""", """controllers.HomeController.login(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """callback""", """controllers.HomeController.callback(request:Request, code:String, state:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """vistaAdminCallback""", """controllers.HomeController.vistaAdminCallback(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """inicio2Admin/""" + "$" + """db<[^/]+>""", """controllers.HomeController.inicio2Admin(db:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """viewImg/""" + "$" + """img<[^/]+>""", """controllers.HomeController.viewImg(img:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """viewImgAlbum/""" + "$" + """img<[^/]+>,""" + "$" + """carp<[^/]+>""", """controllers.HomeController.viewImgAlbum(img:String, carp:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """showPdf/""" + "$" + """fileNamePdf<[^/]+>""", """controllers.HomeController.showPdf(fileNamePdf:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """showPdfmasAlbum/""" + "$" + """id_guia<[^/]+>,""" + "$" + """fileNamePdf<[^/]+>""", """controllers.HomeController.showPdfmasAlbum(id_guia:Long, fileNamePdf:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """showPdfManuales/""" + "$" + """fileNamePdf<[^/]+>""", """controllers.HomeController.showPdfManuales(fileNamePdf:String, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """downloadPDF/""", """controllers.HomeController.downloadPDF(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tasasDeFechaAjax/""", """controllers.HomeController.tasasDeFechaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sendEmail/""", """controllers.HomeController.sendEmail(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """muestraAlbumFotos/""" + "$" + """carp<[^/]+>""", """controllers.HomeController.muestraAlbumFotos(carp:String, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """muestraAlbumFotosWord/""", """controllers.HomeController.muestraAlbumFotosWord(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """administraModulos/""", """controllers.MnuToolsAdmin.administraModulos(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificarModulosAjax/""", """controllers.MnuToolsAdmin.modificarModulosAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """administrarEmisor/""", """controllers.MnuToolsAdmin.administrarEmisor(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificarEmisor/""", """controllers.MnuToolsAdmin.modificarEmisor(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """manualBasico/""", """controllers.MnuAyuda.manualBasico(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """manualPrimerosPasos/""", """controllers.MnuAyuda.manualPrimerosPasos(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """claseMantencion/""", """controllers.MnuOdo.claseMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """claseModifica/""", """controllers.MnuOdo.claseModifica(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """claseAgrega/""", """controllers.MnuOdo.claseAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """claseNuevo/""", """controllers.MnuOdo.claseNuevo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """claseElimina/""", """controllers.MnuOdo.claseElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaClasePorCampoAjax/""", """controllers.MnuOdo.modificaClasePorCampoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioMantencion/""", """controllers.MnuOdo.servicioMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioMantencionExcel/""", """controllers.MnuOdo.servicioMantencionExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioNuevo/""" + "$" + """id_clase<[^/]+>""", """controllers.MnuOdo.servicioNuevo(id_clase:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaCodigoServicioAjax/""", """controllers.MnuOdo.verificaCodigoServicioAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioGraba/""", """controllers.MnuOdo.servicioGraba(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioModifica/""" + "$" + """id_servicio<[^/]+>""", """controllers.MnuOdo.servicioModifica(id_servicio:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioUpdate/""", """controllers.MnuOdo.servicioUpdate(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioElimina/""", """controllers.MnuOdo.servicioElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modPrecioServicioAjax/""", """controllers.MnuOdo.modPrecioServicioAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioPrecios/""", """controllers.MnuOdo.servicioPrecios(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioListaPrecios/""" + "$" + """id_bod<[^/]+>""", """controllers.MnuOdo.servicioListaPrecios(id_bod:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioAgregaPrecio/""", """controllers.MnuOdo.servicioAgregaPrecio(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modPorCampoListaPServicioAjax/""", """controllers.MnuOdo.modPorCampoListaPServicioAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioPreciosVariable0/""", """controllers.MnuOdo.servicioPreciosVariable0(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioPreciosVariable1/""" + "$" + """id_bod<[^/]+>""", """controllers.MnuOdo.servicioPreciosVariable1(id_bod:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioPreciosVariable2/""" + "$" + """id_bod<[^/]+>,""" + "$" + """id_ser<[^/]+>,""" + "$" + """id_cot<[^/]+>""", """controllers.MnuOdo.servicioPreciosVariable2(id_bod:Long, id_ser:Long, id_cot:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioAgregaVariable/""", """controllers.MnuOdo.servicioAgregaVariable(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modPrecioVariableServicioAjax/""", """controllers.MnuOdo.modPrecioVariableServicioAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioEliminaVariable/""", """controllers.MnuOdo.servicioEliminaVariable(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioEquipos0/""", """controllers.MnuOdo.servicioEquipos0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modVigEquipoServicioAjax/""", """controllers.MnuOdo.modVigEquipoServicioAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioEquipos1/""", """controllers.MnuOdo.servicioEquipos1(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioEquipos2/""" + "$" + """id_bod<[^/]+>""", """controllers.MnuOdo.servicioEquipos2(id_bod:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioEquipo3/""", """controllers.MnuOdo.servicioEquipo3(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """operadorMantencion/""", """controllers.MnuOdo.operadorMantencion(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """operadorNuevo/""", """controllers.MnuOdo.operadorNuevo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaRutOperadorAjax/""", """controllers.MnuOdo.verificaRutOperadorAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """operadorGraba/""", """controllers.MnuOdo.operadorGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modVigOperadorServicioAjax/""", """controllers.MnuOdo.modVigOperadorServicioAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """operadorModifica/""" + "$" + """id_oper<[^/]+>""", """controllers.MnuOdo.operadorModifica(id_oper:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """operadorUpdate/""", """controllers.MnuOdo.operadorUpdate(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoVentas/""", """controllers.MnuOdo.odoVentas(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoListaServiciosAjax/""", """controllers.MnuOdo.odoListaServiciosAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoListaEquiposAjax/""", """controllers.MnuOdo.odoListaEquiposAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoVentasGrabar/""", """controllers.MnuOdo.odoVentasGrabar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoVentasGrabaDocAnexo/""", """controllers.MnuOdo.odoVentasGrabaDocAnexo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoVentasGrabaDocAnexoWeb/""", """controllers.MnuOdoAppWeb.odoVentasGrabaDocAnexoWeb(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoGrabaAlbumFotosAjax/""", """controllers.MnuOdo.odoGrabaAlbumFotosAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoListarVentas0/""", """controllers.MnuOdo.odoListarVentas0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoListarVentas1/""", """controllers.MnuOdo.odoListarVentas1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoListarVentas1Excel/""", """controllers.MnuOdo.odoListarVentas1Excel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoListarVentas1aux/""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""", """controllers.MnuOdo.odoListarVentas1aux(request:Request, desde:String, hasta:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoCambiaEquipoAjax/""", """controllers.MnuOdo.odoCambiaEquipoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoDetalleVenta/""" + "$" + """id_venta<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""", """controllers.MnuOdo.odoDetalleVenta(request:Request, id_venta:Long, desde:String, hasta:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoFirmaOperador/""" + "$" + """id_venta<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""", """controllers.MnuOdo.odoFirmaOperador(request:Request, id_venta:Long, desde:String, hasta:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabarFirmaOperador/""", """controllers.MnuOdo.grabarFirmaOperador(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoFirmaAutorizador/""" + "$" + """id_venta<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""", """controllers.MnuOdo.odoFirmaAutorizador(request:Request, id_venta:Long, desde:String, hasta:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabarFirmaAutorizador/""", """controllers.MnuOdo.grabarFirmaAutorizador(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoVentaServicioElimina/""", """controllers.MnuOdo.odoVentaServicioElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportProfPerOdo/""", """controllers.MnuOdo.reportProfPerOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportProfProyOdo/""" + "$" + """archivoPDF<[^/]+>""", """controllers.MnuOdo.reportProfProyOdo(request:Request, archivoPDF:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportProfProyOdoExcel/""", """controllers.MnuOdo.reportProfProyOdoExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportProfProyDetalleOdo/""", """controllers.MnuOdo.reportProfProyDetalleOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportProfProyDetalleOdoExcel/""", """controllers.MnuOdo.reportProfProyDetalleOdoExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generarProfPdfXlsxXmlJsonOdo/""", """controllers.MnuOdo.generarProfPdfXlsxXmlJsonOdo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaListaOdo/""", """controllers.MnuOdo.proformaListaOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaEliminaOdo/""", """controllers.MnuOdo.proformaEliminaOdo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpOdo/""", """controllers.MnuOdo.ajustesEpOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpListadoOdo/""", """controllers.MnuOdo.ajustesEpListadoOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpNuevoOdo/""", """controllers.MnuOdo.ajustesEpNuevoOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpGrabarOdo/""", """controllers.MnuOdo.ajustesEpGrabarOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpEliminarOdo/""", """controllers.MnuOdo.ajustesEpEliminarOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpModificarOdo/""", """controllers.MnuOdo.ajustesEpModificarOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpUpdateOdo/""", """controllers.MnuOdo.ajustesEpUpdateOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajusteGrabaPDFodo/""", """controllers.MnuOdo.ajusteGrabaPDFodo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpRptOdo/""", """controllers.MnuOdo.ajustesEpRptOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpRptDetalleOdo/""", """controllers.MnuOdo.ajustesEpRptDetalleOdo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesPeriodoEpRpt1Odo/""", """controllers.MnuOdo.ajustesPeriodoEpRpt1Odo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesPeriodoEpRpt2Odo/""", """controllers.MnuOdo.ajustesPeriodoEpRpt2Odo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOdoPeriodoResumen/""", """controllers.MnuOdo.reportOdoPeriodoResumen(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOdoResumen/""", """controllers.MnuOdo.reportOdoResumen(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOdoResumenExcel/""", """controllers.MnuOdo.reportOdoResumenExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOdoConsolidado/""", """controllers.MnuOdo.reportOdoConsolidado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOdoConsolidadoRtp/""", """controllers.MnuOdo.reportOdoConsolidadoRtp(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOdoConsolidadoRtpExcel/""", """controllers.MnuOdo.reportOdoConsolidadoRtpExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovOdoCantidades/""", """controllers.MnuOdo.reporteMovOdoCantidades(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovOdoCantLista/""", """controllers.MnuOdo.reporteMovOdoCantLista(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovOdoCantDetalle/""", """controllers.MnuOdo.reporteMovOdoCantDetalle(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovOdoCantDetalleExcel/""", """controllers.MnuOdo.reporteMovOdoCantDetalleExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovOdoValorizado/""", """controllers.MnuOdo.reporteMovOdoValorizado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovOdoValorizadoLista/""", """controllers.MnuOdo.reporteMovOdoValorizadoLista(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovOdoValorizadoDetalle/""", """controllers.MnuOdo.reporteMovOdoValorizadoDetalle(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovOdoValorizadoDetalleExcel/""", """controllers.MnuOdo.reporteMovOdoValorizadoDetalleExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOperadorConsol0/""", """controllers.MnuOdo.reportOperadorConsol0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOperadorConsol1/""", """controllers.MnuOdo.reportOperadorConsol1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOperadorConsol1Excel/""", """controllers.MnuOdo.reportOperadorConsol1Excel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odo""", """controllers.HomeController.inicio()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoVentasWeb/""", """controllers.MnuOdoAppWeb.odoVentasWeb(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoVentasWeb/""", """controllers.MnuOdoAppWeb.odoVentasHomeWeb(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoVentasHomeWeb/""", """controllers.MnuOdoAppWeb.odoVentasHomeWeb(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoVentasGrabarWeb/""", """controllers.MnuOdoAppWeb.odoVentasGrabarWeb(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoListarVentasWeb/""" + "$" + """year<[^/]+>""", """controllers.MnuOdoAppWeb.odoListarVentasWeb(request:Request, year:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoDetalleVentaWeb/""" + "$" + """id_venta<[^/]+>""", """controllers.MnuOdoAppWeb.odoDetalleVentaWeb(request:Request, id_venta:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoFirmaOperadorWeb/""" + "$" + """id_venta<[^/]+>""", """controllers.MnuOdoAppWeb.odoFirmaOperadorWeb(request:Request, id_venta:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabarFirmaOperadorWeb/""", """controllers.MnuOdoAppWeb.grabarFirmaOperadorWeb(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoFirmaAutorizadorWeb/""" + "$" + """id_venta<[^/]+>""", """controllers.MnuOdoAppWeb.odoFirmaAutorizadorWeb(request:Request, id_venta:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabarFirmaAutorizadorWeb/""", """controllers.MnuOdoAppWeb.grabarFirmaAutorizadorWeb(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoVentaServicioEliminaWeb/""", """controllers.MnuOdoAppWeb.odoVentaServicioEliminaWeb(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoAutorizaDetalleVentaWeb/""" + "$" + """id_venta<[^/]+>""", """controllers.MnuOdoAppWeb.odoAutorizaDetalleVentaWeb(request:Request, id_venta:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoAutorizaListarVentasWeb/""", """controllers.MnuOdoAppWeb.odoAutorizaListarVentasWeb(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """odoAutorizaFirmaWeb/""" + "$" + """id_venta<[^/]+>""", """controllers.MnuOdoAppWeb.odoAutorizaFirmaWeb(request:Request, id_venta:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabarAutorizaFirmaWeb/""", """controllers.MnuOdoAppWeb.grabarAutorizaFirmaWeb(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cpanel""", """controllers.AppCPanel.vistaCPanel()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """inicioCPanelHome/""", """controllers.AppCPanel.inicioCPanelHome(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """inicioCPanel/""", """controllers.AppCPanel.inicioCPanel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cPanelModalItemsControlados/""", """controllers.AppCPanel.cPanelModalItemsControlados(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cPanelModalVigentes/""", """controllers.AppCPanel.cPanelModalVigentes(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cPanelModalNoVigentes/""", """controllers.AppCPanel.cPanelModalNoVigentes(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """inicioCPanel2/""", """controllers.AppCPanel.inicioCPanel2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """plantillaIConstruyeDownload/""", """controllers.MnuPlanes.plantillaIConstruyeDownload(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """plantillaIConstruyeUpload/""", """controllers.MnuPlanes.plantillaIConstruyeUpload(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """importaHojaVidaIconstruye0/""", """controllers.MnuPlanes.importaHojaVidaIconstruye0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """importaHojaVidaIconstruye1/""", """controllers.MnuPlanes.importaHojaVidaIconstruye1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """importaHojaVidaIconstruye2/""", """controllers.MnuPlanes.importaHojaVidaIconstruye2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """detalleProductoUploadIConstruye/""", """controllers.MnuPlanes.detalleProductoUploadIConstruye(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """detalleProductoDownloadMada/""", """controllers.MnuPlanes.detalleProductoDownloadMada(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """detalleProductoUploadMada/""", """controllers.MnuPlanes.detalleProductoUploadMada(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoTrabajoMantencion/""", """controllers.MnuPlanes.tipoTrabajoMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoTrabajoModifica/""", """controllers.MnuPlanes.tipoTrabajoModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaTipoPorCampoAjax/""", """controllers.MnuPlanes.modificaTipoPorCampoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoTrabajoAgrega/""", """controllers.MnuPlanes.tipoTrabajoAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoTrabajoNuevo/""", """controllers.MnuPlanes.tipoTrabajoNuevo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoTrabajoElimina/""", """controllers.MnuPlanes.tipoTrabajoElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoPlanMantencion/""", """controllers.MnuPlanes.tipoPlanMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoPlanModifica/""", """controllers.MnuPlanes.tipoPlanModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaPlanPorCampoAjax/""", """controllers.MnuPlanes.modificaPlanPorCampoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoPlanAgrega/""", """controllers.MnuPlanes.tipoPlanAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoPlanNuevo/""", """controllers.MnuPlanes.tipoPlanNuevo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoPlanElimina/""", """controllers.MnuPlanes.tipoPlanElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaPlanMantencion/""", """controllers.MnuPlanes.hojaVidaPlanMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaPlanModifica/""", """controllers.MnuPlanes.hojaVidaPlanModifica(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaPlanModificaGet/""" + "$" + """idEquipo<[^/]+>""", """controllers.MnuPlanes.hojaVidaPlanModificaGet(request:Request, idEquipo:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaAgregaPlan/""", """controllers.MnuPlanes.hojaVidaAgregaPlan(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaPlanElimina/""", """controllers.MnuPlanes.hojaVidaPlanElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaPlanCrear/""", """controllers.MnuPlanes.hojaVidaPlanCrear(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaPlanCrear2/""", """controllers.MnuPlanes.hojaVidaPlanCrear2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualCampoPlanMantencionAjax/""", """controllers.MnuPlanes.actualCampoPlanMantencionAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaMantencionLista/""" + "$" + """flag<[^/]+>""", """controllers.MnuPlanes.hojaVidaMantencionLista(request:Request, flag:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaMantencionPlan/""", """controllers.MnuPlanes.hojaVidaMantencionPlan(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaMantencionHoja/""", """controllers.MnuPlanes.hojaVidaMantencionHoja(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaMantencionHojaReturn/""" + "$" + """id_equipo<[^/]+>""", """controllers.MnuPlanes.hojaVidaMantencionHojaReturn(request:Request, id_equipo:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaMantencionListaExcel/""", """controllers.MnuPlanes.hojaVidaMantencionListaExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaMantencionHojaAgrega/""", """controllers.MnuPlanes.hojaVidaMantencionHojaAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaMantencionHojaElimina/""", """controllers.MnuPlanes.hojaVidaMantencionHojaElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabarHojaVidaPdf/""", """controllers.MnuPlanes.grabarHojaVidaPdf(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualCampoHojaVidaAjax/""", """controllers.MnuPlanes.actualCampoHojaVidaAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaReportLista/""", """controllers.MnuPlanes.hojaVidaReportLista(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaReportDetalle/""", """controllers.MnuPlanes.hojaVidaReportDetalle(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaReportDetalleExcel/""", """controllers.MnuPlanes.hojaVidaReportDetalleExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaReportKpis/""", """controllers.MnuPlanes.hojaVidaReportKpis(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaReportKpisExcel/""", """controllers.MnuPlanes.hojaVidaReportKpisExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaVidaReportGrafico/""" + "$" + """id_grupo<[^/]+>,""" + "$" + """periodo<[^/]+>""", """controllers.MnuPlanes.hojaVidaReportGrafico(request:Request, id_grupo:Long, periodo:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioEquipoCorte/""" + "$" + """tipo<[^/]+>""", """controllers.MnuReportes.reportInventarioEquipoCorte(request:Request, tipo:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioEquipo/""", """controllers.MnuReportes.reportInventarioEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioGeneralXEquipo/""", """controllers.MnuReportes.reportInventarioGeneralXEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventTrazaEquipoEnBod/""", """controllers.MnuReportes.reportInventTrazaEquipoEnBod(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioEquipoExcel/""", """controllers.MnuReportes.reportInventarioEquipoExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportEquipoBodegaCorte/""" + "$" + """tipo<[^/]+>""", """controllers.MnuReportes.reportEquipoBodegaCorte(request:Request, tipo:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportEquipoBodega/""", """controllers.MnuReportes.reportEquipoBodega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportEquipoBodegaXEquipo/""", """controllers.MnuReportes.reportEquipoBodegaXEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportEquipoBodegaExcel/""", """controllers.MnuReportes.reportEquipoBodegaExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportEquipoBodegaTrazaEquipoEnBod/""", """controllers.MnuReportes.reportEquipoBodegaTrazaEquipoEnBod(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioBodegaCorte/""" + "$" + """tipo<[^/]+>""", """controllers.MnuReportes.reportInventarioBodegaCorte(request:Request, tipo:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioBodega/""", """controllers.MnuReportes.reportInventarioBodega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioSelectivoXBodega/""", """controllers.MnuReportes.reportInventarioSelectivoXBodega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventProyectoTrazaEquipoEnBod/""", """controllers.MnuReportes.reportInventProyectoTrazaEquipoEnBod(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioSelectivoXBodegaExcel/""", """controllers.MnuReportes.reportInventarioSelectivoXBodegaExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioGrupoCorte/""" + "$" + """tipo<[^/]+>""", """controllers.MnuReportes.reportInventarioGrupoCorte(request:Request, tipo:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioGrupo/""", """controllers.MnuReportes.reportInventarioGrupo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioSelectivoXGrupo/""", """controllers.MnuReportes.reportInventarioSelectivoXGrupo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioSelectivoXGrupoExcel/""", """controllers.MnuReportes.reportInventarioSelectivoXGrupoExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioMatrizCorte/""" + "$" + """tipo<[^/]+>""", """controllers.MnuReportes.reportInventarioMatrizCorte(request:Request, tipo:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioMatriz/""", """controllers.MnuReportes.reportInventarioMatriz(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioMatrizExcel/""", """controllers.MnuReportes.reportInventarioMatrizExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioTodo/""", """controllers.MnuReportes.reportInventarioTodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioTodoExcel/""", """controllers.MnuReportes.reportInventarioTodoExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioProyecto/""", """controllers.MnuReportes.reportInventarioProyecto(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioProyectoDetalle/""", """controllers.MnuReportes.reportInventarioProyectoDetalle(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportInventarioProyectoDetalleExcel/""", """controllers.MnuReportes.reportInventarioProyectoDetalleExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mailingInventarioProyecto""", """controllers.MnuReportes.reportMailInventarioProyecto(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mailingInventarioProyectoExcel/""", """controllers.MnuReportes.reportMailInventarioProyectoExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mailingInventarioProyectoEnviar/""", """controllers.MnuReportes.reportMailInventarioProyectoEnviar(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovimientosPeriodoAgrupado/""", """controllers.MnuReportes.reporteMovimientosPeriodoAgrupado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovimientosListaProyectosAgrupado/""", """controllers.MnuReportes.reporteMovimientosListaProyectosAgrupado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovimientosDetalleAgrupado/""", """controllers.MnuReportes.reporteMovimientosDetalleAgrupado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovimientosDetalleAgrupadoExcel/""", """controllers.MnuReportes.reporteMovimientosDetalleAgrupadoExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovSoloBodInternas0/""", """controllers.MnuReportes.reporteMovSoloBodInternas0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovSoloBodInternas1/""", """controllers.MnuReportes.reporteMovSoloBodInternas1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovSoloBodInternas2/""", """controllers.MnuReportes.reporteMovSoloBodInternas2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovSoloBodInternas2Excel/""", """controllers.MnuReportes.reporteMovSoloBodInternas2Excel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovimientosPeriodo/""", """controllers.MnuReportes.reporteMovimientosPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovimientosListaProyectos/""", """controllers.MnuReportes.reporteMovimientosListaProyectos(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovimientosDetalle/""", """controllers.MnuReportes.reporteMovimientosDetalle(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovimientosDetalleExcel/""", """controllers.MnuReportes.reporteMovimientosDetalleExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovimientosListaProyectosIE/""", """controllers.MnuReportes.reporteMovimientosListaProyectosIE(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovimientosDetalleIE/""", """controllers.MnuReportes.reporteMovimientosDetalleIE(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovimientosIEExcel/""", """controllers.MnuReportes.reporteMovimientosIEExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteExcedentesListaProyectos/""", """controllers.MnuReportes.reporteExcedentesListaProyectos(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteExcedentesDetalle/""", """controllers.MnuReportes.reporteExcedentesDetalle(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteExcedentesDetalleExcel/""", """controllers.MnuReportes.reporteExcedentesDetalleExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEstadosTodos/""", """controllers.MnuReportes.reporteEstadosTodos(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEstadosTodosDetalle/""", """controllers.MnuReportes.reporteEstadosTodosDetalle(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEstadosTodosDetalleExcel/""", """controllers.MnuReportes.reporteEstadosTodosDetalleExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEstadosPeriodo0/""", """controllers.MnuReportes.reporteEstadosPeriodo0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEstadosPeriodo1/""", """controllers.MnuReportes.reporteEstadosPeriodo1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEstadosPeriodo1Excel/""", """controllers.MnuReportes.reporteEstadosPeriodo1Excel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEstadosAll0/""", """controllers.MnuReportes.reporteEstadosAll0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEstadosAll1/""", """controllers.MnuReportes.reporteEstadosAll1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEstadosAll1Excel/""", """controllers.MnuReportes.reporteEstadosAll1Excel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportTrazaEquipo/""", """controllers.MnuReportes.reportTrazaEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportTrazaEquipo2/""", """controllers.MnuReportes.reportTrazaEquipo2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportTrazaEquipo2Excel/""", """controllers.MnuReportes.reportTrazaEquipo2Excel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEjecutivo1/""", """controllers.MnuReportes.reporteEjecutivo1(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """graficoInversionExcel/""", """controllers.MnuReportes.graficoInversionExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """graficoOcupacionExcel/""", """controllers.MnuReportes.graficoOcupacionExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """graficoValorizadoGrupoExcel/""", """controllers.MnuReportes.graficoValorizadoGrupoExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """graficoUnidadesExcel/""", """controllers.MnuReportes.graficoUnidadesExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """graficoValorizadoBodegaExcel/""", """controllers.MnuReportes.graficoValorizadoBodegaExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """graficoPotencialExcel/""", """controllers.MnuReportes.graficoPotencialExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteGerencial/""", """controllers.MnuReportes.reporteGerencial(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteGerencialGrupo/""", """controllers.MnuReportes.reporteGerencialGrupo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteGerencialGrupoDetalle/""", """controllers.MnuReportes.reporteGerencialGrupoDetalle(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteGerencialKG/""", """controllers.MnuReportes.reporteGerencialKG(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteGerencialKGExcel/""", """controllers.MnuReportes.reporteGerencialKGExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteGerencialVentas/""", """controllers.MnuReportes.reporteGerencialVentas(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportGraficoMovResumen/""" + "$" + """listG<[^/]+>,""" + "$" + """vista<[^/]+>""", """controllers.MnuReportes.reportGraficoMovResumen(request:Request, listG:String, vista:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """refreshGraficoMovResumen/""", """controllers.MnuReportes.refreshGraficoMovResumen(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportGraficoMovPorGrupo/""" + "$" + """lG<[^/]+>,""" + "$" + """lB<[^/]+>,""" + "$" + """vista<[^/]+>,""" + "$" + """idT<[^/]+>""", """controllers.MnuReportes.reportGraficoMovPorGrupo(request:Request, lG:String, lB:String, vista:String, idT:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """refreshGraficoMovPorGrupo/""" + "$" + """idTipoBodega<[^/]+>""", """controllers.MnuReportes.refreshGraficoMovPorGrupo(request:Request, idTipoBodega:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportGraficoMovUso/""", """controllers.MnuReportes.reportGraficoMovUso(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportGraficoMovUsoPropiedad/""", """controllers.MnuReportes.reportGraficoMovUsoPropiedad(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaPeriodo/""", """controllers.MnuReportes.reportFacturaPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyecto/""" + "$" + """archivoPDF<[^/]+>""", """controllers.MnuReportes.reportFacturaProyecto(request:Request, archivoPDF:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoGet/""" + "$" + """p<[^/]+>,""" + "$" + """d<[^/]+>,""" + "$" + """a<[^/]+>,""" + "$" + """uf<[^/]+>,""" + "$" + """us<[^/]+>,""" + "$" + """eu<[^/]+>""", """controllers.MnuReportes.reportFacturaProyectoGet(request:Request, p:String, d:String, a:String, uf:Double, us:Double, eu:Double)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoExcel/""", """controllers.MnuReportes.reportFacturaProyectoExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalle/""", """controllers.MnuReportes.reportFacturaProyectoDetalle(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetExcel/""", """controllers.MnuReportes.reportFacturaProyectoDetExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generarProformaPdfXlsxXmlJson/""", """controllers.MnuReportes.generarProformaPdfXlsxXmlJson(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaPeriodoH/""", """controllers.MnuReportes.reportFacturaPeriodoH(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoH/""" + "$" + """archivoPDF<[^/]+>""", """controllers.MnuReportes.reportFacturaProyectoH(request:Request, archivoPDF:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoHExcel/""", """controllers.MnuReportes.reportFacturaProyectoHExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoHGet/""" + "$" + """p<[^/]+>,""" + "$" + """d<[^/]+>,""" + "$" + """a<[^/]+>,""" + "$" + """uf<[^/]+>,""" + "$" + """us<[^/]+>,""" + "$" + """eu<[^/]+>""", """controllers.MnuReportes.reportFacturaProyectoHGet(request:Request, p:String, d:String, a:String, uf:Double, us:Double, eu:Double)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaListaHPeriodo/""", """controllers.MnuReportes.proformaListaHPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaListaH/""", """controllers.MnuReportes.proformaListaH(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaListaHGet/""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""", """controllers.MnuReportes.proformaListaHGet(request:Request, desde:String, hasta:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaEliminaH/""", """controllers.MnuReportes.proformaEliminaH(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalleH/""", """controllers.MnuReportes.reportFacturaProyectoDetalleH(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalleHExcel/""", """controllers.MnuReportes.reportFacturaProyectoDetalleHExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalleHProforma/""", """controllers.MnuReportes.reportFacturaProyectoDetalleHProforma(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalleHVta/""", """controllers.MnuReportes.reportFacturaProyectoDetalleHVta(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalleHVtaExcel/""", """controllers.MnuReportes.reportFacturaProyectoDetalleHVtaExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalleHVtaProforma/""", """controllers.MnuReportes.reportFacturaProyectoDetalleHVtaProforma(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalleHServ/""", """controllers.MnuReportes.reportFacturaProyectoDetalleHServ(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalleHServExcel/""", """controllers.MnuReportes.reportFacturaProyectoDetalleHServExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalleHServProforma/""", """controllers.MnuReportes.reportFacturaProyectoDetalleHServProforma(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalleHALL/""", """controllers.MnuReportes.reportFacturaProyectoDetalleHALL(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalleHAllExcel/""", """controllers.MnuReportes.reportFacturaProyectoDetalleHAllExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaProyectoDetalleHAllProforma/""", """controllers.MnuReportes.reportFacturaProyectoDetalleHAllProforma(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaPeriodoResumen0/""", """controllers.MnuReportes.reportFacturaPeriodoResumen0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaResumen0/""", """controllers.MnuReportes.reportFacturaResumen0(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaPeriodoResumen/""", """controllers.MnuReportes.reportFacturaPeriodoResumen(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaResumen/""", """controllers.MnuReportes.reportFacturaResumen(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaPeriodoResumen2/""", """controllers.MnuReportes.reportFacturaPeriodoResumen2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaResumen2/""", """controllers.MnuReportes.reportFacturaResumen2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaResumenJson/""", """controllers.MnuReportes.reportFacturaResumenJson(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaResumenExcel0/""", """controllers.MnuReportes.reportFacturaResumenExcel0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaResumenExcel/""", """controllers.MnuReportes.reportFacturaResumenExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaResumenExcel2/""", """controllers.MnuReportes.reportFacturaResumenExcel2(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaConsolidado/""", """controllers.MnuReportes.reportFacturaConsolidado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaConsolidadoRtp/""", """controllers.MnuReportes.reportFacturaConsolidadoRtp(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFacturaConsolidadoRtpExcel/""", """controllers.MnuReportes.reportFacturaConsolidadoRtpExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFactConsolconGrupo/""", """controllers.MnuReportes.reportFactConsolconGrupo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFactConsolconGrupoRtp/""", """controllers.MnuReportes.reportFactConsolconGrupoRtp(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFactConsolconGrupoRtpExcel/""", """controllers.MnuReportes.reportFactConsolconGrupoRtpExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFactConsolconEquipos/""", """controllers.MnuReportes.reportFactConsolconEquipos(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFactConsolconEquiposRtp/""", """controllers.MnuReportes.reportFactConsolconEquiposRtp(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportFactConsolconEquiposRtpExcel/""", """controllers.MnuReportes.reportFactConsolconEquiposRtpExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaListaPeriodo/""", """controllers.MnuReportes.proformaListaPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaLista/""", """controllers.MnuReportes.proformaLista(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaListaGet/""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""", """controllers.MnuReportes.proformaListaGet(request:Request, desde:String, hasta:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaListaExcel/""", """controllers.MnuReportes.proformaListaExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaProformaXml/""", """controllers.MnuReportes.generaProformaXml(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sendXMLFacura/""" + "$" + """id_proforma<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""", """controllers.MnuReportes.sendXMLFacura(request:Request, id_proforma:Long, desde:String, hasta:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaProformaApiManager/""", """controllers.MnuReportes.generaProformaApiManager(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaProformaApiNubox/""", """controllers.MnuReportes.generaProformaApiNubox(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaProformaApiSapConconcreto/""", """controllers.MnuReportes.generaProformaApiSapConconcreto(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaProformaWebMaximise/""", """controllers.MnuReportes.generaProformaWebMaximise(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """downFacturaMaximise/""" + "$" + """nroInte<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""", """controllers.MnuReportes.downFacturaMaximise(nroInte:Long, request:Request, desde:String, hasta:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaFacturaIConstruye/""", """controllers.MnuReportes.generaFacturaIConstruye(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """downFacturaIconstruye/""", """controllers.MnuReportes.downFacturaIconstruye(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaProformaApiSapSchwager/""", """controllers.MnuReportes.generaProformaApiSapSchwager(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaProformaApiRelBase/""", """controllers.MnuReportes.generaProformaApiRelBase(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaProformaApiRelBase2/""", """controllers.MnuReportes.generaProformaApiRelBase2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """validarClienteAjax/""", """controllers.MnuReportes.validarClienteAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaElimina/""", """controllers.MnuReportes.proformaElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEp/""", """controllers.MnuReportes.ajustesEp(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpListado/""", """controllers.MnuReportes.ajustesEpListado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajusteGrabaPDF/""", """controllers.MnuReportes.ajusteGrabaPDF(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpModificar/""", """controllers.MnuReportes.ajustesEpModificar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpUpdate/""", """controllers.MnuReportes.ajustesEpUpdate(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpEliminar/""", """controllers.MnuReportes.ajustesEpEliminar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpNuevo/""", """controllers.MnuReportes.ajustesEpNuevo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpGrabar/""", """controllers.MnuReportes.ajustesEpGrabar(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpRpt/""", """controllers.MnuReportes.ajustesEpRpt(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesEpRptDetalle/""", """controllers.MnuReportes.ajustesEpRptDetalle(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesPeriodoEpRpt1/""", """controllers.MnuReportes.ajustesPeriodoEpRpt1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """ajustesPeriodoEpRpt2/""", """controllers.MnuReportes.ajustesPeriodoEpRpt2(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheReportTodo0/""", """controllers.MnuReportes.hoheReportTodo0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheReportTodo/""", """controllers.MnuReportes.hoheReportTodo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheTodoResumen0/""", """controllers.MnuReportes.hoheTodoResumen0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheTodoResumen/""", """controllers.MnuReportes.hoheTodoResumen(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheMatrizVerticalInventario0/""", """controllers.MnuReportes.hoheMatrizVerticalInventario0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheMatrizVerticalInventario/""", """controllers.MnuReportes.hoheMatrizVerticalInventario(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheMatrizVerticalInv0Coti/""", """controllers.MnuReportes.hoheMatrizVerticalInv0Coti(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheMatrizVerticalInvCoti/""", """controllers.MnuReportes.hoheMatrizVerticalInvCoti(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheEstadoCotiOt/""", """controllers.MnuReportes.hoheEstadoCotiOt(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheEstadoCotiOtExcel/""", """controllers.MnuReportes.hoheEstadoCotiOtExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheEstadoCotiSinOt/""", """controllers.MnuReportes.hoheEstadoCotiSinOt(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheEstadoCotiSinOtExcel/""", """controllers.MnuReportes.hoheEstadoCotiSinOtExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hoheValySubePlantillaNV/""", """controllers.MnuReportes.hoheValySubePlantillaNV(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hohePlantillaNV/""", """controllers.MnuReportes.hohePlantillaNV(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """disponibleActualizaAll/""", """controllers.MnuDisponibilidad.disponibleActualizaAll(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cambiaFechaDisponibleAjax/""", """controllers.MnuDisponibilidad.cambiaFechaDisponibleAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportDisponibilidadAll/""", """controllers.MnuDisponibilidad.reportDisponibilidadAll(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportDisponibilidadAllExcel/""", """controllers.MnuDisponibilidad.reportDisponibilidadAllExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """disponibleInscribirEquipo/""", """controllers.MnuDisponibilidad.disponibleInscribirEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """disponibleInscribirEquipo2/""", """controllers.MnuDisponibilidad.disponibleInscribirEquipo2(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """disponibleEliminarEquipo/""", """controllers.MnuDisponibilidad.disponibleEliminarEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """disponibleEliminarEquipo2/""", """controllers.MnuDisponibilidad.disponibleEliminarEquipo2(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """disponibleInscribirBodega/""", """controllers.MnuDisponibilidad.disponibleInscribirBodega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """disponibleInscribirBodega2/""", """controllers.MnuDisponibilidad.disponibleInscribirBodega2(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """disponibleEliminarBodega/""", """controllers.MnuDisponibilidad.disponibleEliminarBodega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """disponibleEliminarBodega2/""", """controllers.MnuDisponibilidad.disponibleEliminarBodega2(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrListaEquipos/""", """controllers.MnuQr.qrListaEquipos(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrSelectEquipos/""", """controllers.MnuQr.qrSelectEquipos(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrAgregaEquipo/""", """controllers.MnuQr.qrAgregaEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrCambiaEstadoEquipos/""", """controllers.MnuQr.qrCambiaEstadoEquipos(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrRevisarDatos/""", """controllers.MnuQr.qrRevisarDatos(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrRevisarDatosAllVigentes/""", """controllers.MnuQr.qrRevisarDatosAllVigentes(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrRevisarDatosAllVigentesExcel/""", """controllers.MnuQr.qrRevisarDatosAllVigentesExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrCambiarPubEquipo/""", """controllers.MnuQr.qrCambiarPubEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrCambiarPubQr/""", """controllers.MnuQr.qrCambiarPubQr(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrEditEquipo/""", """controllers.MnuQr.qrEditEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrAgregaCampoEquipo/""", """controllers.MnuQr.qrAgregaCampoEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrActualizaPorCampo/""", """controllers.MnuQr.qrActualizaPorCampo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrEliminaCampoEquipo/""", """controllers.MnuQr.qrEliminaCampoEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrGrabaAnexoEquipo/""", """controllers.MnuQr.qrGrabaAnexoEquipo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrListaAtributoEquipos/""", """controllers.MnuQr.qrListaAtributoEquipos(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrEditarAtributoEquipo/""" + "$" + """idQrAtributoEquipo<[^/]+>""", """controllers.MnuQr.qrEditarAtributoEquipo(request:Request, idQrAtributoEquipo:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrEditarAtributoEquipo2/""", """controllers.MnuQr.qrEditarAtributoEquipo2(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrDeleteAtributoEquipo/""" + "$" + """idQrAtributoEquipo<[^/]+>""", """controllers.MnuQr.qrDeleteAtributoEquipo(request:Request, idQrAtributoEquipo:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrAgregaAtributoEquipo/""", """controllers.MnuQr.qrAgregaAtributoEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrAgregaAtributoEquipo2/""", """controllers.MnuQr.qrAgregaAtributoEquipo2(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrPrintQrEquipos/""", """controllers.MnuQr.qrPrintQrEquipos(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrPrintQrEquiposWord/""", """controllers.MnuQr.qrPrintQrEquiposWord(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """leeUnQr/""" + "$" + """strEncoded<[^/]+>""", """controllers.MnuQr.leeUnQr(request:Request, strEncoded:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """viewImgQr/""" + "$" + """base<[^/]+>,""" + "$" + """img<[^/]+>""", """controllers.MnuQr.viewImgQr(request:Request, base:String, img:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrImagen/""", """controllers.MnuQr.qrImagen(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrAtributo/""", """controllers.MnuQr.qrAtributo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrUbicacion/""", """controllers.MnuQr.qrUbicacion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrTrazabilidad/""", """controllers.MnuQr.qrTrazabilidad(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrHistoria/""", """controllers.MnuQr.qrHistoria(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrAtributosQr/""", """controllers.MnuQr.qrAtributosQr(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """qrImagenQr/""", """controllers.MnuQr.qrImagenQr(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pptoAdministrar/""", """controllers.MnuPpto.pptoAdministrar(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pptoEditar/""" + "$" + """id_bodega<[^/]+>""", """controllers.MnuPpto.pptoEditar(request:Request, id_bodega:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pptoAgregar/""", """controllers.MnuPpto.pptoAgregar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pptoGrabar/""", """controllers.MnuPpto.pptoGrabar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pptoModificar/""", """controllers.MnuPpto.pptoModificar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pptoUpdate/""", """controllers.MnuPpto.pptoUpdate(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pptoEliminar/""", """controllers.MnuPpto.pptoEliminar(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pptoListadoPptos/""", """controllers.MnuPpto.pptoListadoPptos(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pptoVsRealxBodega/""" + "$" + """id_bodega<[^/]+>""", """controllers.MnuPpto.pptoVsRealxBodega(request:Request, id_bodega:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaIngreso/""", """controllers.MnuCotizar.cotizaIngreso(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaIngreso2/""" + "$" + """id_bodega<[^/]+>""", """controllers.MnuCotizar.cotizaIngreso2(request:Request, id_bodega:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tablaInvPorEquipoAjax/""", """controllers.MnuCotizar.tablaInvPorEquipoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizarNuevo/""", """controllers.MnuCotizar.cotizarNuevo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """existeNumeroCotizacionAjax/""", """controllers.MnuCotizar.existeNumeroCotizacionAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaModificaJson/""", """controllers.MnuCotizar.cotizaModificaJson(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualizaComercialesAjax/""", """controllers.MnuCotizar.actualizaComercialesAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualizaComerciales2Ajax/""", """controllers.MnuCotizar.actualizaComerciales2Ajax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualizaListaCotiAjax/""", """controllers.MnuCotizar.actualizaListaCotiAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualizaPreciosAjax/""", """controllers.MnuCotizar.actualizaPreciosAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaModificaPeriodo/""", """controllers.MnuCotizar.cotizaListaModificaPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaModifica/""", """controllers.MnuCotizar.cotizaListaModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaElimina/""", """controllers.MnuCotizar.cotizaElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaModifica/""", """controllers.MnuCotizar.cotizaModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizarUpdate/""", """controllers.MnuCotizar.cotizarUpdate(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaCambiaEstadoPeriodo/""", """controllers.MnuCotizar.cotizaListaCambiaEstadoPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaCambiaEstado/""", """controllers.MnuCotizar.cotizaListaCambiaEstado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cambiarCotizaEstadoAjax/""", """controllers.MnuCotizar.cambiarCotizaEstadoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cambiarCotizaFechaProbableAjax/""", """controllers.MnuCotizar.cambiarCotizaFechaProbableAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cambiarCotizaDibujanteAjax/""", """controllers.MnuCotizar.cambiarCotizaDibujanteAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cambiarCotizaNotaAjax/""", """controllers.MnuCotizar.cambiarCotizaNotaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizacionFindIdForNumAjax/""", """controllers.MnuCotizar.cotizacionFindIdForNumAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaEstadoMantencion/""", """controllers.MnuCotizar.cotizaEstadoMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaEstadoElimina/""", """controllers.MnuCotizar.cotizaEstadoElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaEstadoModifica/""", """controllers.MnuCotizar.cotizaEstadoModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaCotizaEstadoPorCampoAjax/""", """controllers.MnuCotizar.modificaCotizaEstadoPorCampoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaEstadoAgrega/""", """controllers.MnuCotizar.cotizaEstadoAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaEstadoNuevo/""", """controllers.MnuCotizar.cotizaEstadoNuevo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaImprimirPeriodo/""", """controllers.MnuCotizar.cotizaListaImprimirPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaImprimir/""", """controllers.MnuCotizar.cotizaListaImprimir(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaImprimir/""" + "$" + """id_cotizacion<[^/]+>""", """controllers.MnuCotizar.cotizaImprimir(request:Request, id_cotizacion:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaExcel/""", """controllers.MnuCotizar.cotizaExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pdfCotizaArriendo/""", """controllers.MnuCotizar.pdfCotizaArriendo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pdfCotizaVenta/""", """controllers.MnuCotizar.pdfCotizaVenta(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pdfCotizaArrVta/""", """controllers.MnuCotizar.pdfCotizaArrVta(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportCotizaSel/""", """controllers.MnuCotizar.reportCotizaSel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportCotizaRpt/""", """controllers.MnuCotizar.reportCotizaRpt(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaConfirma/""", """controllers.MnuCotizar.cotizaListaConfirma(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaConfirma/""", """controllers.MnuCotizar.cotizaConfirma(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaCotizaSinOt/""", """controllers.MnuCotizar.otListaCotizaSinOt(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otHacer/""", """controllers.MnuCotizar.otHacer(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otGrabar/""", """controllers.MnuCotizar.otGrabar(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaEliminar/""", """controllers.MnuCotizar.otListaEliminar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verOtAjax/""", """controllers.MnuCotizar.verOtAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otElimina/""", """controllers.MnuCotizar.otElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualFechaEnvioOtAjax/""", """controllers.MnuCotizar.actualFechaEnvioOtAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaCambiarEstadoPeriodo/""", """controllers.MnuCotizar.otListaCambiarEstadoPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaCambiarEstado/""", """controllers.MnuCotizar.otListaCambiarEstado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cambiarOtEstadoAjax/""", """controllers.MnuCotizar.cambiarOtEstadoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cambiarOtNotaAjax/""", """controllers.MnuCotizar.cambiarOtNotaAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otEstadoMantencion/""", """controllers.MnuCotizar.otEstadoMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otEstadoElimina/""", """controllers.MnuCotizar.otEstadoElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otEstadoModifica/""", """controllers.MnuCotizar.otEstadoModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaOtEstadoPorCampoAjax/""", """controllers.MnuCotizar.modificaOtEstadoPorCampoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otEstadoAgrega/""", """controllers.MnuCotizar.otEstadoAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otEstadoNuevo/""", """controllers.MnuCotizar.otEstadoNuevo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaConfirma/""", """controllers.MnuCotizar.otListaConfirma(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otConfirma/""", """controllers.MnuCotizar.otConfirma(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaDespacharPeriodo/""", """controllers.MnuCotizar.otListaDespacharPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaDespachar/""", """controllers.MnuCotizar.otListaDespachar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otDespachar/""", """controllers.MnuCotizar.otDespachar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tblListEquipoConStockAjax/""", """controllers.MnuCotizar.tblListEquipoConStockAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabaDespacho/""", """controllers.MnuCotizar.grabaDespacho(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaDespachoModificarPeriodo/""", """controllers.MnuCotizar.otListaDespachoModificarPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaDespachoModificar/""", """controllers.MnuCotizar.otListaDespachoModificar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otDespachoElimina/""", """controllers.MnuCotizar.otDespachoElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otDespachoModificar/""", """controllers.MnuCotizar.otDespachoModificar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaDespacho/""", """controllers.MnuCotizar.modificaDespacho(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiPlantilla/""", """controllers.MnuCotizar.cotiPlantilla(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiValidarPlantilla/""", """controllers.MnuCotizar.cotiValidarPlantilla(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiCargaPlantilla/""", """controllers.MnuCotizar.cotiCargaPlantilla(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraImportIconstruye0/""", """controllers.MnuCompras.compraImportIconstruye0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraImportIconstruye1/""", """controllers.MnuCompras.compraImportIconstruye1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraImportIconstruye2/""", """controllers.MnuCompras.compraImportIconstruye2(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraIngreso/""", """controllers.MnuCompras.compraIngreso(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaNumeroFacturaAjax/""", """controllers.MnuCompras.verificaNumeroFacturaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraNuevo/""", """controllers.MnuCompras.compraNuevo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modalPreciosAjax/""", """controllers.MnuCompras.modalPreciosAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """nuevoEquipoAjax/""", """controllers.MnuCompras.nuevoEquipoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraConfirma/""", """controllers.MnuCompras.compraConfirma(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraConfirmaIngreso/""", """controllers.MnuCompras.compraConfirmaIngreso(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraListaModifica/""", """controllers.MnuCompras.compraListaModifica(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraModifica/""" + "$" + """id_factura<[^/]+>""", """controllers.MnuCompras.compraModifica(id_factura:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraModificaGraba/""", """controllers.MnuCompras.compraModificaGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaFacturaPorCampoAjax/""", """controllers.MnuCompras.modificaFacturaPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaCompraPorCampoAjax/""", """controllers.MnuCompras.modificaCompraPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """eliminaCompraAjax/""", """controllers.MnuCompras.eliminaCompraAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """listComprasPorCompra/""", """controllers.MnuCompras.listComprasPorCompra(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """listComprasPorCompraExcel/""", """controllers.MnuCompras.listComprasPorCompraExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraFacturaPrint/""" + "$" + """id_factura<[^/]+>""", """controllers.MnuCompras.compraFacturaPrint(id_factura:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraFacturaPrintExcel/""", """controllers.MnuCompras.compraFacturaPrintExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """listComprasPorEquipo/""", """controllers.MnuCompras.listComprasPorEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """listComprasPorEquipoExcel/""", """controllers.MnuCompras.listComprasPorEquipoExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraEquipoPrint/""" + "$" + """id_equipo<[^/]+>""", """controllers.MnuCompras.compraEquipoPrint(id_equipo:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraElimina/""", """controllers.MnuCompras.compraElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """eliminaCompra/""", """controllers.MnuCompras.eliminaCompra(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoSelectBodegaOrigen/""", """controllers.MnuMovimientos.movimientoSelectBodegaOrigen(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoOrigenDestinoMultiple/""", """controllers.MnuMovimientos.movimientoOrigenDestinoMultiple(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoOrigenDestinoMultiple1/""", """controllers.MnuMovimientos.movimientoOrigenDestinoMultiple1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaNumeroGuiaAjax/""", """controllers.MnuMovimientos.verificaNumeroGuiaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """nameComercialAjax/""", """controllers.MnuMovimientos.nameComercialAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """nickNameClienteAjax/""", """controllers.MnuMovimientos.nickNameClienteAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoAplicar/""", """controllers.MnuMovimientos.movimientoAplicar(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoSelectModificarPeriodo/""", """controllers.MnuMovimientos.movimientoSelectModificarPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoSelectModificar/""", """controllers.MnuMovimientos.movimientoSelectModificar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verCotizacionAjax/""", """controllers.MnuMovimientos.verCotizacionAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoOrigenDestinoModifica/""", """controllers.MnuMovimientos.movimientoOrigenDestinoModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoOrigenDestinoElimina/""", """controllers.MnuMovimientos.movimientoOrigenDestinoElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoAplicarCambios/""", """controllers.MnuMovimientos.movimientoAplicarCambios(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabaAlbumFotosAjax/""", """controllers.MnuMovimientos.grabaAlbumFotosAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoListarPeriodo/""", """controllers.MnuMovimientos.movimientoListarPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoListar/""", """controllers.MnuMovimientos.movimientoListar(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoListarGet/""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""", """controllers.MnuMovimientos.movimientoListarGet(request:Request, desde:String, hasta:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoListarExcel/""", """controllers.MnuMovimientos.movimientoListarExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """showPdfRelBase/""" + "$" + """tipo<[^/]+>,""" + "$" + """nroFiscal<[^/]+>""", """controllers.MnuMovimientos.showPdfRelBase(tipo:String, nroFiscal:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoDetalleGuia/""" + "$" + """id_guia<[^/]+>""", """controllers.MnuMovimientos.movimientoDetalleGuia(id_guia:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaGuiaXLS/""", """controllers.MnuMovimientos.generaGuiaXLS(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaGuiaExcel/""", """controllers.MnuMovimientos.generaGuiaExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """guiaCambiarNroRef/""", """controllers.MnuMovimientos.guiaCambiarNroRef(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """editaTransportistaAjax/""", """controllers.MnuMovimientos.editaTransportistaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """nuevoTransportistaAjax/""", """controllers.MnuMovimientos.nuevoTransportistaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaTransportistaAjax/""", """controllers.MnuMovimientos.modificaTransportistaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """eliminaTransportistaAjax/""", """controllers.MnuMovimientos.eliminaTransportistaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaGuiaXML/""", """controllers.MnuMovimientos.generaGuiaXML(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaGuiaApiManager/""", """controllers.MnuMovimientos.generaGuiaApiManager(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaGuiaApiNubox/""", """controllers.MnuMovimientos.generaGuiaApiNubox(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaGuiaWebFacturacion/""", """controllers.MnuMovimientos.generaGuiaWebFacturacion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaGuiaWebMaximise/""", """controllers.MnuMovimientos.generaGuiaWebMaximise(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """downGuiaMaximiseAjax/""" + "$" + """nroInte<[^/]+>""", """controllers.MnuMovimientos.downGuiaMaximiseAjax(nroInte:String, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaGuiaWebIConstruye/""", """controllers.MnuMovimientos.generaGuiaWebIConstruye(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """downGuiaIconstruye/""", """controllers.MnuMovimientos.downGuiaIconstruye(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaGuiaApiRelBase/""", """controllers.MnuMovimientos.generaGuiaApiRelBase(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """buscaCotiSolucionAjax/""", """controllers.MnuMovimientos.buscaCotiSolucionAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """generaGuiaSapSchwager/""", """controllers.MnuMovimientos.generaGuiaSapSchwager(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoListarImgPeriodo/""", """controllers.MnuMovimientos.movimientoListarImgPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoListarImg/""", """controllers.MnuMovimientos.movimientoListarImg(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoDetalleGuiaConImg/""" + "$" + """id_guia<[^/]+>""", """controllers.MnuMovimientos.movimientoDetalleGuiaConImg(id_guia:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaChequeoSelectBodegaAgrupado/""", """controllers.MnuMovimientos.hojaChequeoSelectBodegaAgrupado(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaChequeoDetalleAgrupado/""" + "$" + """id_bodega<[^/]+>""", """controllers.MnuMovimientos.hojaChequeoDetalleAgrupado(id_bodega:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaChequeoSelectBodega/""", """controllers.MnuMovimientos.hojaChequeoSelectBodega(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaChequeoDetalle/""" + "$" + """id_bodega<[^/]+>""", """controllers.MnuMovimientos.hojaChequeoDetalle(id_bodega:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaChequeoSelectPorGrupo/""", """controllers.MnuMovimientos.hojaChequeoSelectPorGrupo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaChequeoDetallePorGrupo/""", """controllers.MnuMovimientos.hojaChequeoDetallePorGrupo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaChequeoAgrupadoExcel/""", """controllers.MnuMovimientos.hojaChequeoAgrupadoExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaChequeoExcel/""", """controllers.MnuMovimientos.hojaChequeoExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hojaChequeoDetallePorGrupoExcel/""", """controllers.MnuMovimientos.hojaChequeoDetallePorGrupoExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cierreProyectoSel/""", """controllers.MnuMovimientos.cierreProyectoSel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cierreProyectoDet/""", """controllers.MnuMovimientos.cierreProyectoDet(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cierreAplicar/""", """controllers.MnuMovimientos.cierreAplicar(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bajaIngreso/""", """controllers.MnuBajas.bajaIngreso(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bajaNuevo/""", """controllers.MnuBajas.bajaNuevo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bajaListaModifica/""", """controllers.MnuBajas.bajaListaModifica(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bajaModifica/""" + "$" + """id_actaBaja<[^/]+>""", """controllers.MnuBajas.bajaModifica(id_actaBaja:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bajaModificaGraba/""", """controllers.MnuBajas.bajaModificaGraba(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """confirmaBaja/""", """controllers.MnuBajas.confirmaBaja(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bajaConfirmaIngreso/""", """controllers.MnuBajas.bajaConfirmaIngreso(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """listBajasPorActa/""", """controllers.MnuBajas.listBajasPorActa(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bajaActaPrint/""" + "$" + """id_actaBaja<[^/]+>""", """controllers.MnuBajas.bajaActaPrint(id_actaBaja:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """listBajasPorEquipo/""", """controllers.MnuBajas.listBajasPorEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """listBajasPorEquipoExcel/""", """controllers.MnuBajas.listBajasPorEquipoExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bajaEquipoPrint/""" + "$" + """id_equipo<[^/]+>""", """controllers.MnuBajas.bajaEquipoPrint(id_equipo:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaAdministrar/""", """controllers.MnuBodegas.bodegaAdministrar(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaCrear/""", """controllers.MnuBodegas.bodegaCrear(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """vistaSelectComercialAjax/""", """controllers.MnuBodegas.vistaSelectComercialAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaNombreBodegaAjax/""", """controllers.MnuBodegas.verificaNombreBodegaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaGraba/""", """controllers.MnuBodegas.bodegaGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaModificar/""", """controllers.MnuBodegas.bodegaModificar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaModifica/""", """controllers.MnuBodegas.bodegaModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaElimina/""", """controllers.MnuBodegas.bodegaElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaAsignaDctos/""", """controllers.MnuBodegas.bodegaAsignaDctos(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaFijaDctos/""" + "$" + """id_bodega<[^/]+>""", """controllers.MnuBodegas.bodegaFijaDctos(id_bodega:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificarDctoArriendoAjax/""", """controllers.MnuBodegas.modificarDctoArriendoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """eliminarDctoArriendoAjax/""", """controllers.MnuBodegas.eliminarDctoArriendoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabaDctoArriendoAjax/""", """controllers.MnuBodegas.grabaDctoArriendoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaAsignaTasas/""", """controllers.MnuBodegas.bodegaAsignaTasas(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaFijaTasas/""" + "$" + """id_bodega<[^/]+>""", """controllers.MnuBodegas.bodegaFijaTasas(id_bodega:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificarTasaArriendoAjax/""", """controllers.MnuBodegas.modificarTasaArriendoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """eliminarTasaArriendoAjax/""", """controllers.MnuBodegas.eliminarTasaArriendoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabaTasaArriendoAjax/""", """controllers.MnuBodegas.grabaTasaArriendoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaPrecios/""", """controllers.MnuBodegas.bodegaPrecios(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaListaPrecios/""" + "$" + """id_bodega<[^/]+>""", """controllers.MnuBodegas.bodegaListaPrecios(id_bodega:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualizaListaPrecioAjax/""", """controllers.MnuBodegas.actualizaListaPrecioAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaFactorViga/""", """controllers.MnuBodegas.bodegaFactorViga(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualizaFactorVigaAjax/""", """controllers.MnuBodegas.actualizaFactorVigaAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaContactos/""", """controllers.MnuBodegas.bodegaContactos(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaContactosExcel/""", """controllers.MnuBodegas.bodegaContactosExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaModificaContacto/""" + "$" + """id_bodega<[^/]+>""", """controllers.MnuBodegas.bodegaModificaContacto(id_bodega:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaContactoModifica/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_bodega<[^/]+>""", """controllers.MnuBodegas.bodegaContactoModifica(id_contacto:Long, id_bodega:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaContactoAgrega/""" + "$" + """id_bodega<[^/]+>""", """controllers.MnuBodegas.bodegaContactoAgrega(id_bodega:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaContactoGraba/""", """controllers.MnuBodegas.bodegaContactoGraba(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaContactoElimina/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_bodega<[^/]+>""", """controllers.MnuBodegas.bodegaContactoElimina(id_contacto:Long, id_bodega:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaContactoBodegaPorCampoAjax/""", """controllers.MnuBodegas.modificaContactoBodegaPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaContactosAjax/""", """controllers.MnuBodegas.bodegaContactosAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaFindIdForNomAjax/""", """controllers.MnuBodegas.bodegaFindIdForNomAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bodegaVigenteNoVigente/""", """controllers.MnuBodegas.bodegaVigenteNoVigente(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificarBodegaEstado/""" + "$" + """id_bodega<[^/]+>,""" + "$" + """estado<[^/]+>""", """controllers.MnuBodegas.modificarBodegaEstado(id_bodega:Long, estado:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """precioMantencion/""", """controllers.MnuTablas.precioMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualizaPrecioAjax/""", """controllers.MnuTablas.actualizaPrecioAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grupoMantencion/""", """controllers.MnuTablas.grupoMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grupoElimina/""", """controllers.MnuTablas.grupoElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grupoModifica/""", """controllers.MnuTablas.grupoModifica(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grupoAgrega/""", """controllers.MnuTablas.grupoAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grupoNuevo/""", """controllers.MnuTablas.grupoNuevo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaGrupoPorCampoAjax/""", """controllers.MnuTablas.modificaGrupoPorCampoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grupoNuevoAtributo/""" + "$" + """id_grupo<[^/]+>""", """controllers.MnuTablas.grupoNuevoAtributo(id_grupo:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grupoGrabaAtributo/""", """controllers.MnuTablas.grupoGrabaAtributo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grupoEliminaAtributo/""", """controllers.MnuTablas.grupoEliminaAtributo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """equipoMantencion/""", """controllers.MnuTablas.equipoMantencion(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """equipoModifica/""" + "$" + """id_equipo<[^/]+>""", """controllers.MnuTablas.equipoModifica(id_equipo:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """equipoNuevo/""" + "$" + """id_grupo<[^/]+>""", """controllers.MnuTablas.equipoNuevo(id_grupo:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cambiarGrupoEquipo/""" + "$" + """id_grup<[^/]+>,""" + "$" + """id_equipo<[^/]+>""", """controllers.MnuTablas.cambiarGrupoEquipo(id_grup:Long, id_equipo:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaEquipoPorCampoAjax/""", """controllers.MnuTablas.modificaEquipoPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaAtributoEquipoAjax/""", """controllers.MnuTablas.modificaAtributoEquipoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabaImgEquipo/""", """controllers.MnuTablas.grabaImgEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """equipoElimina/""", """controllers.MnuTablas.equipoElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """equipoGraba/""", """controllers.MnuTablas.equipoGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaCodigoEquipoAjax/""", """controllers.MnuTablas.verificaCodigoEquipoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """equipoDescripcionAjax/""", """controllers.MnuTablas.equipoDescripcionAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """equipoFindIdForCodAjax/""", """controllers.MnuTablas.equipoFindIdForCodAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoMantencion/""", """controllers.MnuTablas.proyectoMantencion(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoModifica/""" + "$" + """id_proyecto<[^/]+>""", """controllers.MnuTablas.proyectoModifica(id_proyecto:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """selComuna1Ajax/""", """controllers.MnuTablas.selComuna1Ajax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """selComuna2Ajax/""", """controllers.MnuTablas.selComuna2Ajax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaProyectoPorCampoAjax/""", """controllers.MnuTablas.modificaProyectoPorCampoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoContactoModifica/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_proyecto<[^/]+>""", """controllers.MnuTablas.proyectoContactoModifica(id_contacto:Long, id_proyecto:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoContactoAgrega/""" + "$" + """id_proyecto<[^/]+>""", """controllers.MnuTablas.proyectoContactoAgrega(id_proyecto:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoContactoGraba/""", """controllers.MnuTablas.proyectoContactoGraba(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoContactoElimina/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_proyecto<[^/]+>""", """controllers.MnuTablas.proyectoContactoElimina(id_contacto:Long, id_proyecto:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaContactoProyectoPorCampoAjax/""", """controllers.MnuTablas.modificaContactoProyectoPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoElimina/""", """controllers.MnuTablas.proyectoElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoAgrega/""", """controllers.MnuTablas.proyectoAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoGraba/""", """controllers.MnuTablas.proyectoGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoGrabaAjax/""", """controllers.MnuTablas.proyectoGrabaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaNickProyectoAjax/""", """controllers.MnuTablas.verificaNickProyectoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoContactosAjax/""", """controllers.MnuTablas.proyectoContactosAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoFindIdForNickNameAjax/""", """controllers.MnuTablas.proyectoFindIdForNickNameAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteMantencion/""", """controllers.MnuTablas.clienteMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteMantencionExcel/""", """controllers.MnuTablas.clienteMantencionExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteModifica/""" + "$" + """id_cliente<[^/]+>""", """controllers.MnuTablas.clienteModifica(id_cliente:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaClientePorCampoAjax/""", """controllers.MnuTablas.modificaClientePorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """selComuna3Ajax/""", """controllers.MnuTablas.selComuna3Ajax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteContactoModifica/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_cliente<[^/]+>""", """controllers.MnuTablas.clienteContactoModifica(id_contacto:Long, id_cliente:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteContactoAgrega/""" + "$" + """id_cliente<[^/]+>""", """controllers.MnuTablas.clienteContactoAgrega(id_cliente:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteContactoGraba/""", """controllers.MnuTablas.clienteContactoGraba(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteContactoElimina/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_cliente<[^/]+>""", """controllers.MnuTablas.clienteContactoElimina(id_contacto:Long, id_cliente:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaContactoClientePorCampoAjax/""", """controllers.MnuTablas.modificaContactoClientePorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteElimina/""", """controllers.MnuTablas.clienteElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteAgrega/""", """controllers.MnuTablas.clienteAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteGraba/""", """controllers.MnuTablas.clienteGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteGrabaAjax/""", """controllers.MnuTablas.clienteGrabaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaNickClienteAjax/""", """controllers.MnuTablas.verificaNickClienteAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteContactosAjax/""", """controllers.MnuTablas.clienteContactosAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteFindIdForNickNameAjax/""", """controllers.MnuTablas.clienteFindIdForNickNameAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """fabricaMantencion/""", """controllers.MnuTablas.fabricaMantencion(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """fabricaModifica/""" + "$" + """id_fabrica<[^/]+>""", """controllers.MnuTablas.fabricaModifica(id_fabrica:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaFabricaPorCampoAjax/""", """controllers.MnuTablas.modificaFabricaPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """selComuna4Ajax/""", """controllers.MnuTablas.selComuna4Ajax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """fabricaContactoModifica/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_fabrica<[^/]+>""", """controllers.MnuTablas.fabricaContactoModifica(id_contacto:Long, id_fabrica:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """fabricaContactoAgrega/""" + "$" + """id_fabrica<[^/]+>""", """controllers.MnuTablas.fabricaContactoAgrega(id_fabrica:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """fabricaContactoGraba/""", """controllers.MnuTablas.fabricaContactoGraba(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """fabricaContactoElimina/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_fabrica<[^/]+>""", """controllers.MnuTablas.fabricaContactoElimina(id_contacto:Long, id_fabrica:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaContactoFabricaPorCampoAjax/""", """controllers.MnuTablas.modificaContactoFabricaPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """fabricaElimina/""", """controllers.MnuTablas.fabricaElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """fabricaAgrega/""", """controllers.MnuTablas.fabricaAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """fabricaGraba/""", """controllers.MnuTablas.fabricaGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaNickFabricaAjax/""", """controllers.MnuTablas.verificaNickFabricaAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """allContactosCliProvFabrExcel/""", """controllers.MnuTablas.allContactosCliProvFabrExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """transportistaMantencion/""", """controllers.MnuTablas.transportistaMantencion(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proveedorMantencion/""", """controllers.MnuTablas.proveedorMantencion(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proveedorModifica/""" + "$" + """id_proveedor<[^/]+>""", """controllers.MnuTablas.proveedorModifica(id_proveedor:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaProveedorPorCampoAjax/""", """controllers.MnuTablas.modificaProveedorPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """selComuna5Ajax/""", """controllers.MnuTablas.selComuna5Ajax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proveedorContactoModifica/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_proveedor<[^/]+>""", """controllers.MnuTablas.proveedorContactoModifica(id_contacto:Long, id_proveedor:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proveedorContactoAgrega/""" + "$" + """id_proveedor<[^/]+>""", """controllers.MnuTablas.proveedorContactoAgrega(id_proveedor:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proveedorContactoGraba/""", """controllers.MnuTablas.proveedorContactoGraba(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proveedorContactoElimina/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_proveedor<[^/]+>""", """controllers.MnuTablas.proveedorContactoElimina(id_contacto:Long, id_proveedor:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaContactoProveedorPorCampoAjax/""", """controllers.MnuTablas.modificaContactoProveedorPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proveedorElimina/""", """controllers.MnuTablas.proveedorElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proveedorAgrega/""", """controllers.MnuTablas.proveedorAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proveedorGraba/""", """controllers.MnuTablas.proveedorGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proveedorGrabaAjax/""", """controllers.MnuTablas.proveedorGrabaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaNickProveedorAjax/""", """controllers.MnuTablas.verificaNickProveedorAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoEstadoMantencion/""", """controllers.MnuTablas.tipoEstadoMantencion(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoEstadoModifica/""" + "$" + """id_estado<[^/]+>""", """controllers.MnuTablas.tipoEstadoModifica(id_estado:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaTipoEstadoPorCampoAjax/""", """controllers.MnuTablas.modificaTipoEstadoPorCampoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoReparacionModifica/""" + "$" + """id_tipoRep<[^/]+>,""" + "$" + """id_tipoEst<[^/]+>""", """controllers.MnuTablas.tipoReparacionModifica(id_tipoRep:Long, id_tipoEst:Long, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoReparacionAgrega/""" + "$" + """id_tipoEst<[^/]+>""", """controllers.MnuTablas.tipoReparacionAgrega(id_tipoEst:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaSiglaTipoReparacionAjax/""", """controllers.MnuTablas.verificaSiglaTipoReparacionAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoReparacionGraba/""", """controllers.MnuTablas.tipoReparacionGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoReparacionElimina/""", """controllers.MnuTablas.tipoReparacionElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaTipoReparacionPorCampoAjax/""", """controllers.MnuTablas.modificaTipoReparacionPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoEstadoElimina/""", """controllers.MnuTablas.tipoEstadoElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoEstadoAgrega/""", """controllers.MnuTablas.tipoEstadoAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tipoEstadoGraba/""", """controllers.MnuTablas.tipoEstadoGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaSiglaTipoEstadoAjax/""", """controllers.MnuTablas.verificaSiglaTipoEstadoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """decimalNumero/""", """controllers.MnuTablas.decimalNumero(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """decimalModificaAjax/""", """controllers.MnuTablas.decimalModificaAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """usuarioMantencion/""", """controllers.MnuTablas.usuarioMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """usuarioModifica/""", """controllers.MnuTablas.usuarioModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaUsuarioPorCampoAjax/""", """controllers.MnuTablas.modificaUsuarioPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """usuarioElimina/""", """controllers.MnuTablas.usuarioElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """usuarioAgrega/""", """controllers.MnuTablas.usuarioAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """usuarioGraba/""", """controllers.MnuTablas.usuarioGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verificaUserNameUsuarioAjax/""", """controllers.MnuTablas.verificaUserNameUsuarioAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """usuarioListBodegaEmpresa/""", """controllers.MnuTablas.usuarioListBodegaEmpresa(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """usuarioBodegaSelect/""", """controllers.MnuTablas.usuarioBodegaSelect(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """usuarioBodegaEmpresaElimina/""", """controllers.MnuTablas.usuarioBodegaEmpresaElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modVigUsuarioAjax/""", """controllers.MnuTablas.modVigUsuarioAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """registroDeCambios/""", """controllers.MnuTablas.registroDeCambios(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    prefixed_routes2_Routes_0_738.router.documentation,
    prefixed_routes3_Routes_1_739.router.documentation,
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """""" + "$" + """path<.+>""", """controllers.HomeController.onUrlNotFind(request:Request, path:String)"""),
    Nil
  ).foldLeft(Seq.empty[(String, String, String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String, String, String)]
    case l => s ++ l.asInstanceOf[List[(String, String, String)]]
  }}


  // @LINE:8
  private lazy val controllers_HomeController_ping0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ping/")))
  )
  private lazy val controllers_HomeController_ping0_invoker = createInvoker(
    HomeController_18.ping(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "ping",
      Nil,
      "GET",
      this.prefix + """ping/""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private lazy val controllers_Apis_Mada_APIs1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("Mada_APIs")))
  )
  private lazy val controllers_Apis_Mada_APIs1_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.Mada_APIs(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "Mada_APIs",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """Mada_APIs""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private lazy val controllers_Apis_Mada_APIs2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("authenticar/")))
  )
  private lazy val controllers_Apis_Mada_APIs2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.Mada_APIs(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "Mada_APIs",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """authenticar/""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private lazy val controllers_Apis_authenticar3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("authenticar/")))
  )
  private lazy val controllers_Apis_authenticar3_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.authenticar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "authenticar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """authenticar/""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private lazy val controllers_Apis_pingToken4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pingToken/")))
  )
  private lazy val controllers_Apis_pingToken4_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.pingToken(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "pingToken",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """pingToken/""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private lazy val controllers_Apis_listadoDeBodegas5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("listadoDeBodegas/")))
  )
  private lazy val controllers_Apis_listadoDeBodegas5_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.listadoDeBodegas(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "listadoDeBodegas",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """listadoDeBodegas/""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private lazy val controllers_Apis_movimientosEntreFechas6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientosEntreFechas/")))
  )
  private lazy val controllers_Apis_movimientosEntreFechas6_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.movimientosEntreFechas(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "movimientosEntreFechas",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "POST",
      this.prefix + """movimientosEntreFechas/""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private lazy val controllers_Apis_findBodega7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("findBodega/")))
  )
  private lazy val controllers_Apis_findBodega7_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.findBodega(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "findBodega",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "POST",
      this.prefix + """findBodega/""",
      """""",
      Seq()
    )
  )

  // @LINE:23
  private lazy val controllers_Apis_findCliente8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("findCliente/")))
  )
  private lazy val controllers_Apis_findCliente8_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.findCliente(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "findCliente",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "POST",
      this.prefix + """findCliente/""",
      """""",
      Seq()
    )
  )

  // @LINE:24
  private lazy val controllers_Apis_findProyecto9_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("findProyecto/")))
  )
  private lazy val controllers_Apis_findProyecto9_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.findProyecto(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "findProyecto",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "POST",
      this.prefix + """findProyecto/""",
      """""",
      Seq()
    )
  )

  // @LINE:25
  private lazy val controllers_Apis_inventarios_al_dia10_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("inventarios_al_dia/")))
  )
  private lazy val controllers_Apis_inventarios_al_dia10_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.inventarios_al_dia(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "inventarios_al_dia",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "POST",
      this.prefix + """inventarios_al_dia/""",
      """""",
      Seq()
    )
  )

  // @LINE:27
  private lazy val controllers_Apis_estadoDePagoPorPeriodo11_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("estadoDePagoPorPeriodo/")))
  )
  private lazy val controllers_Apis_estadoDePagoPorPeriodo11_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.estadoDePagoPorPeriodo(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String], fakeValue[Double], fakeValue[Double], fakeValue[Double]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "estadoDePagoPorPeriodo",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String], classOf[Double], classOf[Double], classOf[Double]),
      "POST",
      this.prefix + """estadoDePagoPorPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:29
  private lazy val controllers_Apis_totalesEPporPeriodo12_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("totalesEPporPeriodo/")))
  )
  private lazy val controllers_Apis_totalesEPporPeriodo12_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.totalesEPporPeriodo(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String], fakeValue[Double], fakeValue[Double], fakeValue[Double]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "totalesEPporPeriodo",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String], classOf[Double], classOf[Double], classOf[Double]),
      "POST",
      this.prefix + """totalesEPporPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:30
  private lazy val controllers_Apis_matrizInventarioPorCoti13_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("matrizInventarioPorCoti/")))
  )
  private lazy val controllers_Apis_matrizInventarioPorCoti13_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.matrizInventarioPorCoti(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "matrizInventarioPorCoti",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "POST",
      this.prefix + """matrizInventarioPorCoti/""",
      """""",
      Seq()
    )
  )

  // @LINE:31
  private lazy val controllers_Apis_consolidadoPorGrupoMeses14_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("consolidadoPorGrupoMeses/")))
  )
  private lazy val controllers_Apis_consolidadoPorGrupoMeses14_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.consolidadoPorGrupoMeses(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "consolidadoPorGrupoMeses",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[Long]),
      "POST",
      this.prefix + """consolidadoPorGrupoMeses/""",
      """""",
      Seq()
    )
  )

  // @LINE:32
  private lazy val controllers_Apis_consolidadoPorEquipoMeses15_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("consolidadoPorEquipoMeses/")))
  )
  private lazy val controllers_Apis_consolidadoPorEquipoMeses15_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.consolidadoPorEquipoMeses(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "consolidadoPorEquipoMeses",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[Long]),
      "POST",
      this.prefix + """consolidadoPorEquipoMeses/""",
      """""",
      Seq()
    )
  )

  // @LINE:33
  private lazy val controllers_Apis_ajustesPorPeriodo16_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesPorPeriodo/")))
  )
  private lazy val controllers_Apis_ajustesPorPeriodo16_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.ajustesPorPeriodo(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "ajustesPorPeriodo",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "POST",
      this.prefix + """ajustesPorPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:34
  private lazy val controllers_Apis_estadoBodegasJson17_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("estadoBodegasJson/")))
  )
  private lazy val controllers_Apis_estadoBodegasJson17_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.estadoBodegasJson(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "estadoBodegasJson",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """estadoBodegasJson/""",
      """""",
      Seq()
    )
  )

  // @LINE:35
  private lazy val controllers_Apis_hoheTodoResumenJson18_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheTodoResumenJson/")))
  )
  private lazy val controllers_Apis_hoheTodoResumenJson18_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      Apis_2.hoheTodoResumenJson(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Apis",
      "hoheTodoResumenJson",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "POST",
      this.prefix + """hoheTodoResumenJson/""",
      """""",
      Seq()
    )
  )

  // @LINE:38
  private lazy val controllers_MnuMantencion_mantWebInicio019_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("report")))
  )
  private lazy val controllers_MnuMantencion_mantWebInicio019_invoker = createInvoker(
    MnuMantencion_16.mantWebInicio0(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMantencion",
      "mantWebInicio0",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """report""",
      """""",
      Seq()
    )
  )

  // @LINE:45
  private lazy val controllers_HomeController_inicio20_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private lazy val controllers_HomeController_inicio20_invoker = createInvoker(
    HomeController_18.inicio(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "inicio",
      Nil,
      "GET",
      this.prefix + """""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MODULO DE USUARIO INGRESAR/MODIFICAR/ELIMINAR/LISTAR/RECUPERAR CLAVE
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:46
  private lazy val controllers_HomeController_vistaPrincipal21_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("vistaPrincipal/")))
  )
  private lazy val controllers_HomeController_vistaPrincipal21_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.vistaPrincipal(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "vistaPrincipal",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """vistaPrincipal/""",
      """""",
      Seq()
    )
  )

  // @LINE:48
  private lazy val controllers_HomeController_home22_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("home/")))
  )
  private lazy val controllers_HomeController_home22_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.home(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "home",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """home/""",
      """""",
      Seq()
    )
  )

  // @LINE:49
  private lazy val controllers_HomeController_homeOdoVentasWeb23_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("homeOdoVentasWeb/")))
  )
  private lazy val controllers_HomeController_homeOdoVentasWeb23_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.homeOdoVentasWeb(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "homeOdoVentasWeb",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """homeOdoVentasWeb/""",
      """""",
      Seq()
    )
  )

  // @LINE:50
  private lazy val controllers_HomeController_homeOdoAutorizaVentasWeb24_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("homeOdoAutorizaVentasWeb/")))
  )
  private lazy val controllers_HomeController_homeOdoAutorizaVentasWeb24_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.homeOdoAutorizaVentasWeb(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "homeOdoAutorizaVentasWeb",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """homeOdoAutorizaVentasWeb/""",
      """""",
      Seq()
    )
  )

  // @LINE:52
  private lazy val controllers_HomeController_usuarioModalUpdate225_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("usuarioModalUpdate2")))
  )
  private lazy val controllers_HomeController_usuarioModalUpdate225_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.usuarioModalUpdate2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "usuarioModalUpdate2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """usuarioModalUpdate2""",
      """""",
      Seq()
    )
  )

  // @LINE:53
  private lazy val controllers_HomeController_usuarioValida226_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("usuarioValida2")))
  )
  private lazy val controllers_HomeController_usuarioValida226_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.usuarioValida2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "usuarioValida2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """usuarioValida2""",
      """""",
      Seq()
    )
  )

  // @LINE:54
  private lazy val controllers_HomeController_usuarioUpdate227_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("usuarioUpdate2")))
  )
  private lazy val controllers_HomeController_usuarioUpdate227_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.usuarioUpdate2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "usuarioUpdate2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """usuarioUpdate2""",
      """""",
      Seq()
    )
  )

  // @LINE:55
  private lazy val controllers_HomeController_recuperarClave28_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recuperarClave/")))
  )
  private lazy val controllers_HomeController_recuperarClave28_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.recuperarClave(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "recuperarClave",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """recuperarClave/""",
      """""",
      Seq()
    )
  )

  // @LINE:62
  private lazy val controllers_HomeController_login29_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("admin")))
  )
  private lazy val controllers_HomeController_login29_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.login(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "login",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """admin""",
      """""",
      Seq()
    )
  )

  // @LINE:63
  private lazy val controllers_HomeController_callback30_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("callback")))
  )
  private lazy val controllers_HomeController_callback30_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.callback(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "callback",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "GET",
      this.prefix + """callback""",
      """""",
      Seq()
    )
  )

  // @LINE:65
  private lazy val controllers_HomeController_vistaAdminCallback31_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("vistaAdminCallback")))
  )
  private lazy val controllers_HomeController_vistaAdminCallback31_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.vistaAdminCallback(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "vistaAdminCallback",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """vistaAdminCallback""",
      """""",
      Seq()
    )
  )

  // @LINE:66
  private lazy val controllers_HomeController_inicio2Admin32_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("inicio2Admin/"), DynamicPart("db", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_inicio2Admin32_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.inicio2Admin(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "inicio2Admin",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """inicio2Admin/""" + "$" + """db<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:73
  private lazy val controllers_HomeController_viewImg33_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("viewImg/"), DynamicPart("img", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_viewImg33_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.viewImg(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "viewImg",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """viewImg/""" + "$" + """img<[^/]+>""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MODULO DE USO GENERAL
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:74
  private lazy val controllers_HomeController_viewImgAlbum34_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("viewImgAlbum/"), DynamicPart("img", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("carp", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_viewImgAlbum34_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.viewImgAlbum(fakeValue[String], fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "viewImgAlbum",
      Seq(classOf[String], classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """viewImgAlbum/""" + "$" + """img<[^/]+>,""" + "$" + """carp<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:75
  private lazy val controllers_HomeController_showPdf35_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("showPdf/"), DynamicPart("fileNamePdf", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_showPdf35_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.showPdf(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "showPdf",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """showPdf/""" + "$" + """fileNamePdf<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:76
  private lazy val controllers_HomeController_showPdfmasAlbum36_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("showPdfmasAlbum/"), DynamicPart("id_guia", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("fileNamePdf", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_showPdfmasAlbum36_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.showPdfmasAlbum(fakeValue[Long], fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "showPdfmasAlbum",
      Seq(classOf[Long], classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """showPdfmasAlbum/""" + "$" + """id_guia<[^/]+>,""" + "$" + """fileNamePdf<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:78
  private lazy val controllers_HomeController_showPdfManuales37_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("showPdfManuales/"), DynamicPart("fileNamePdf", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_showPdfManuales37_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.showPdfManuales(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "showPdfManuales",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """showPdfManuales/""" + "$" + """fileNamePdf<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:79
  private lazy val controllers_HomeController_downloadPDF38_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("downloadPDF/")))
  )
  private lazy val controllers_HomeController_downloadPDF38_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.downloadPDF(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "downloadPDF",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """downloadPDF/""",
      """""",
      Seq()
    )
  )

  // @LINE:80
  private lazy val controllers_HomeController_tasasDeFechaAjax39_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tasasDeFechaAjax/")))
  )
  private lazy val controllers_HomeController_tasasDeFechaAjax39_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.tasasDeFechaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "tasasDeFechaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tasasDeFechaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:81
  private lazy val controllers_HomeController_sendEmail40_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sendEmail/")))
  )
  private lazy val controllers_HomeController_sendEmail40_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.sendEmail(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "sendEmail",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """sendEmail/""",
      """""",
      Seq()
    )
  )

  // @LINE:83
  private lazy val controllers_HomeController_muestraAlbumFotos41_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("muestraAlbumFotos/"), DynamicPart("carp", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_muestraAlbumFotos41_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.muestraAlbumFotos(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "muestraAlbumFotos",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """muestraAlbumFotos/""" + "$" + """carp<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:84
  private lazy val controllers_HomeController_muestraAlbumFotosWord42_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("muestraAlbumFotosWord/")))
  )
  private lazy val controllers_HomeController_muestraAlbumFotosWord42_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.muestraAlbumFotosWord(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "muestraAlbumFotosWord",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """muestraAlbumFotosWord/""",
      """""",
      Seq()
    )
  )

  // @LINE:91
  private lazy val controllers_MnuToolsAdmin_administraModulos43_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("administraModulos/")))
  )
  private lazy val controllers_MnuToolsAdmin_administraModulos43_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuToolsAdmin_3.administraModulos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuToolsAdmin",
      "administraModulos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """administraModulos/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU TOOLS ADMIN
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:92
  private lazy val controllers_MnuToolsAdmin_modificarModulosAjax44_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificarModulosAjax/")))
  )
  private lazy val controllers_MnuToolsAdmin_modificarModulosAjax44_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuToolsAdmin_3.modificarModulosAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuToolsAdmin",
      "modificarModulosAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificarModulosAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:94
  private lazy val controllers_MnuToolsAdmin_administrarEmisor45_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("administrarEmisor/")))
  )
  private lazy val controllers_MnuToolsAdmin_administrarEmisor45_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuToolsAdmin_3.administrarEmisor(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuToolsAdmin",
      "administrarEmisor",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """administrarEmisor/""",
      """""",
      Seq()
    )
  )

  // @LINE:95
  private lazy val controllers_MnuToolsAdmin_modificarEmisor46_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificarEmisor/")))
  )
  private lazy val controllers_MnuToolsAdmin_modificarEmisor46_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuToolsAdmin_3.modificarEmisor(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuToolsAdmin",
      "modificarEmisor",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificarEmisor/""",
      """""",
      Seq()
    )
  )

  // @LINE:102
  private lazy val controllers_MnuAyuda_manualBasico47_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("manualBasico/")))
  )
  private lazy val controllers_MnuAyuda_manualBasico47_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuAyuda_14.manualBasico(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuAyuda",
      "manualBasico",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """manualBasico/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU AYUDA
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:103
  private lazy val controllers_MnuAyuda_manualPrimerosPasos48_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("manualPrimerosPasos/")))
  )
  private lazy val controllers_MnuAyuda_manualPrimerosPasos48_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuAyuda_14.manualPrimerosPasos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuAyuda",
      "manualPrimerosPasos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """manualPrimerosPasos/""",
      """""",
      Seq()
    )
  )

  // @LINE:109
  private lazy val controllers_MnuOdo_claseMantencion49_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("claseMantencion/")))
  )
  private lazy val controllers_MnuOdo_claseMantencion49_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.claseMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "claseMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """claseMantencion/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU ODO
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:110
  private lazy val controllers_MnuOdo_claseModifica50_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("claseModifica/")))
  )
  private lazy val controllers_MnuOdo_claseModifica50_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.claseModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "claseModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """claseModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:111
  private lazy val controllers_MnuOdo_claseAgrega51_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("claseAgrega/")))
  )
  private lazy val controllers_MnuOdo_claseAgrega51_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.claseAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "claseAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """claseAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:112
  private lazy val controllers_MnuOdo_claseNuevo52_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("claseNuevo/")))
  )
  private lazy val controllers_MnuOdo_claseNuevo52_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.claseNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "claseNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """claseNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:113
  private lazy val controllers_MnuOdo_claseElimina53_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("claseElimina/")))
  )
  private lazy val controllers_MnuOdo_claseElimina53_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.claseElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "claseElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """claseElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:114
  private lazy val controllers_MnuOdo_modificaClasePorCampoAjax54_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaClasePorCampoAjax/")))
  )
  private lazy val controllers_MnuOdo_modificaClasePorCampoAjax54_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.modificaClasePorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "modificaClasePorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaClasePorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:116
  private lazy val controllers_MnuOdo_servicioMantencion55_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioMantencion/")))
  )
  private lazy val controllers_MnuOdo_servicioMantencion55_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:117
  private lazy val controllers_MnuOdo_servicioMantencionExcel56_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioMantencionExcel/")))
  )
  private lazy val controllers_MnuOdo_servicioMantencionExcel56_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioMantencionExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioMantencionExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """servicioMantencionExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:119
  private lazy val controllers_MnuOdo_servicioNuevo57_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioNuevo/"), DynamicPart("id_clase", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_servicioNuevo57_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioNuevo(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioNuevo",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioNuevo/""" + "$" + """id_clase<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:120
  private lazy val controllers_MnuOdo_verificaCodigoServicioAjax58_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaCodigoServicioAjax/")))
  )
  private lazy val controllers_MnuOdo_verificaCodigoServicioAjax58_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.verificaCodigoServicioAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "verificaCodigoServicioAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaCodigoServicioAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:121
  private lazy val controllers_MnuOdo_servicioGraba59_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioGraba/")))
  )
  private lazy val controllers_MnuOdo_servicioGraba59_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """servicioGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:122
  private lazy val controllers_MnuOdo_servicioModifica60_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioModifica/"), DynamicPart("id_servicio", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_servicioModifica60_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioModifica(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioModifica",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioModifica/""" + "$" + """id_servicio<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:123
  private lazy val controllers_MnuOdo_servicioUpdate61_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioUpdate/")))
  )
  private lazy val controllers_MnuOdo_servicioUpdate61_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """servicioUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:124
  private lazy val controllers_MnuOdo_servicioElimina62_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioElimina/")))
  )
  private lazy val controllers_MnuOdo_servicioElimina62_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """servicioElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:125
  private lazy val controllers_MnuOdo_modPrecioServicioAjax63_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modPrecioServicioAjax/")))
  )
  private lazy val controllers_MnuOdo_modPrecioServicioAjax63_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.modPrecioServicioAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "modPrecioServicioAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modPrecioServicioAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:127
  private lazy val controllers_MnuOdo_servicioPrecios64_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioPrecios/")))
  )
  private lazy val controllers_MnuOdo_servicioPrecios64_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioPrecios(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioPrecios",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioPrecios/""",
      """""",
      Seq()
    )
  )

  // @LINE:128
  private lazy val controllers_MnuOdo_servicioListaPrecios65_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioListaPrecios/"), DynamicPart("id_bod", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_servicioListaPrecios65_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioListaPrecios(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioListaPrecios",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioListaPrecios/""" + "$" + """id_bod<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:129
  private lazy val controllers_MnuOdo_servicioAgregaPrecio66_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioAgregaPrecio/")))
  )
  private lazy val controllers_MnuOdo_servicioAgregaPrecio66_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioAgregaPrecio(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioAgregaPrecio",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """servicioAgregaPrecio/""",
      """""",
      Seq()
    )
  )

  // @LINE:130
  private lazy val controllers_MnuOdo_modPorCampoListaPServicioAjax67_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modPorCampoListaPServicioAjax/")))
  )
  private lazy val controllers_MnuOdo_modPorCampoListaPServicioAjax67_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.modPorCampoListaPServicioAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "modPorCampoListaPServicioAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modPorCampoListaPServicioAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:132
  private lazy val controllers_MnuOdo_servicioPreciosVariable068_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioPreciosVariable0/")))
  )
  private lazy val controllers_MnuOdo_servicioPreciosVariable068_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioPreciosVariable0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioPreciosVariable0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioPreciosVariable0/""",
      """""",
      Seq()
    )
  )

  // @LINE:133
  private lazy val controllers_MnuOdo_servicioPreciosVariable169_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioPreciosVariable1/"), DynamicPart("id_bod", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_servicioPreciosVariable169_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioPreciosVariable1(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioPreciosVariable1",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioPreciosVariable1/""" + "$" + """id_bod<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:134
  private lazy val controllers_MnuOdo_servicioPreciosVariable270_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioPreciosVariable2/"), DynamicPart("id_bod", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_ser", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_cot", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_servicioPreciosVariable270_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioPreciosVariable2(fakeValue[Long], fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioPreciosVariable2",
      Seq(classOf[Long], classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioPreciosVariable2/""" + "$" + """id_bod<[^/]+>,""" + "$" + """id_ser<[^/]+>,""" + "$" + """id_cot<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:135
  private lazy val controllers_MnuOdo_servicioAgregaVariable71_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioAgregaVariable/")))
  )
  private lazy val controllers_MnuOdo_servicioAgregaVariable71_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioAgregaVariable(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioAgregaVariable",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """servicioAgregaVariable/""",
      """""",
      Seq()
    )
  )

  // @LINE:136
  private lazy val controllers_MnuOdo_modPrecioVariableServicioAjax72_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modPrecioVariableServicioAjax/")))
  )
  private lazy val controllers_MnuOdo_modPrecioVariableServicioAjax72_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.modPrecioVariableServicioAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "modPrecioVariableServicioAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modPrecioVariableServicioAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:137
  private lazy val controllers_MnuOdo_servicioEliminaVariable73_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioEliminaVariable/")))
  )
  private lazy val controllers_MnuOdo_servicioEliminaVariable73_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioEliminaVariable(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioEliminaVariable",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """servicioEliminaVariable/""",
      """""",
      Seq()
    )
  )

  // @LINE:139
  private lazy val controllers_MnuOdo_servicioEquipos074_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioEquipos0/")))
  )
  private lazy val controllers_MnuOdo_servicioEquipos074_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioEquipos0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioEquipos0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioEquipos0/""",
      """""",
      Seq()
    )
  )

  // @LINE:140
  private lazy val controllers_MnuOdo_modVigEquipoServicioAjax75_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modVigEquipoServicioAjax/")))
  )
  private lazy val controllers_MnuOdo_modVigEquipoServicioAjax75_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.modVigEquipoServicioAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "modVigEquipoServicioAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modVigEquipoServicioAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:141
  private lazy val controllers_MnuOdo_servicioEquipos176_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioEquipos1/")))
  )
  private lazy val controllers_MnuOdo_servicioEquipos176_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioEquipos1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioEquipos1",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioEquipos1/""",
      """""",
      Seq()
    )
  )

  // @LINE:142
  private lazy val controllers_MnuOdo_servicioEquipos277_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioEquipos2/"), DynamicPart("id_bod", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_servicioEquipos277_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioEquipos2(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioEquipos2",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioEquipos2/""" + "$" + """id_bod<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:143
  private lazy val controllers_MnuOdo_servicioEquipo378_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioEquipo3/")))
  )
  private lazy val controllers_MnuOdo_servicioEquipo378_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.servicioEquipo3(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "servicioEquipo3",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """servicioEquipo3/""",
      """""",
      Seq()
    )
  )

  // @LINE:145
  private lazy val controllers_MnuOdo_operadorMantencion79_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("operadorMantencion/")))
  )
  private lazy val controllers_MnuOdo_operadorMantencion79_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.operadorMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "operadorMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """operadorMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:146
  private lazy val controllers_MnuOdo_operadorNuevo80_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("operadorNuevo/")))
  )
  private lazy val controllers_MnuOdo_operadorNuevo80_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.operadorNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "operadorNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """operadorNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:147
  private lazy val controllers_MnuOdo_verificaRutOperadorAjax81_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaRutOperadorAjax/")))
  )
  private lazy val controllers_MnuOdo_verificaRutOperadorAjax81_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.verificaRutOperadorAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "verificaRutOperadorAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaRutOperadorAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:148
  private lazy val controllers_MnuOdo_operadorGraba82_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("operadorGraba/")))
  )
  private lazy val controllers_MnuOdo_operadorGraba82_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.operadorGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "operadorGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """operadorGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:149
  private lazy val controllers_MnuOdo_modVigOperadorServicioAjax83_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modVigOperadorServicioAjax/")))
  )
  private lazy val controllers_MnuOdo_modVigOperadorServicioAjax83_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.modVigOperadorServicioAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "modVigOperadorServicioAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modVigOperadorServicioAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:150
  private lazy val controllers_MnuOdo_operadorModifica84_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("operadorModifica/"), DynamicPart("id_oper", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_operadorModifica84_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.operadorModifica(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "operadorModifica",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """operadorModifica/""" + "$" + """id_oper<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:151
  private lazy val controllers_MnuOdo_operadorUpdate85_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("operadorUpdate/")))
  )
  private lazy val controllers_MnuOdo_operadorUpdate85_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.operadorUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "operadorUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """operadorUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:153
  private lazy val controllers_MnuOdo_odoVentas86_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoVentas/")))
  )
  private lazy val controllers_MnuOdo_odoVentas86_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoVentas(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoVentas",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """odoVentas/""",
      """""",
      Seq()
    )
  )

  // @LINE:154
  private lazy val controllers_MnuOdo_odoListaServiciosAjax87_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoListaServiciosAjax/")))
  )
  private lazy val controllers_MnuOdo_odoListaServiciosAjax87_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoListaServiciosAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoListaServiciosAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoListaServiciosAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:155
  private lazy val controllers_MnuOdo_odoListaEquiposAjax88_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoListaEquiposAjax/")))
  )
  private lazy val controllers_MnuOdo_odoListaEquiposAjax88_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoListaEquiposAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoListaEquiposAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoListaEquiposAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:156
  private lazy val controllers_MnuOdo_odoVentasGrabar89_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoVentasGrabar/")))
  )
  private lazy val controllers_MnuOdo_odoVentasGrabar89_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoVentasGrabar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoVentasGrabar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoVentasGrabar/""",
      """""",
      Seq()
    )
  )

  // @LINE:158
  private lazy val controllers_MnuOdo_odoVentasGrabaDocAnexo90_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoVentasGrabaDocAnexo/")))
  )
  private lazy val controllers_MnuOdo_odoVentasGrabaDocAnexo90_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoVentasGrabaDocAnexo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoVentasGrabaDocAnexo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoVentasGrabaDocAnexo/""",
      """""",
      Seq()
    )
  )

  // @LINE:159
  private lazy val controllers_MnuOdoAppWeb_odoVentasGrabaDocAnexoWeb91_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoVentasGrabaDocAnexoWeb/")))
  )
  private lazy val controllers_MnuOdoAppWeb_odoVentasGrabaDocAnexoWeb91_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoVentasGrabaDocAnexoWeb(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoVentasGrabaDocAnexoWeb",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoVentasGrabaDocAnexoWeb/""",
      """""",
      Seq()
    )
  )

  // @LINE:160
  private lazy val controllers_MnuOdo_odoGrabaAlbumFotosAjax92_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoGrabaAlbumFotosAjax/")))
  )
  private lazy val controllers_MnuOdo_odoGrabaAlbumFotosAjax92_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoGrabaAlbumFotosAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoGrabaAlbumFotosAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoGrabaAlbumFotosAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:164
  private lazy val controllers_MnuOdo_odoListarVentas093_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoListarVentas0/")))
  )
  private lazy val controllers_MnuOdo_odoListarVentas093_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoListarVentas0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoListarVentas0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """odoListarVentas0/""",
      """""",
      Seq()
    )
  )

  // @LINE:165
  private lazy val controllers_MnuOdo_odoListarVentas194_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoListarVentas1/")))
  )
  private lazy val controllers_MnuOdo_odoListarVentas194_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoListarVentas1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoListarVentas1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoListarVentas1/""",
      """""",
      Seq()
    )
  )

  // @LINE:166
  private lazy val controllers_MnuOdo_odoListarVentas1Excel95_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoListarVentas1Excel/")))
  )
  private lazy val controllers_MnuOdo_odoListarVentas1Excel95_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoListarVentas1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoListarVentas1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoListarVentas1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:168
  private lazy val controllers_MnuOdo_odoListarVentas1aux96_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoListarVentas1aux/"), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_odoListarVentas1aux96_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoListarVentas1aux(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoListarVentas1aux",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "GET",
      this.prefix + """odoListarVentas1aux/""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:169
  private lazy val controllers_MnuOdo_odoCambiaEquipoAjax97_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoCambiaEquipoAjax/")))
  )
  private lazy val controllers_MnuOdo_odoCambiaEquipoAjax97_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoCambiaEquipoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoCambiaEquipoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoCambiaEquipoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:170
  private lazy val controllers_MnuOdo_odoDetalleVenta98_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoDetalleVenta/"), DynamicPart("id_venta", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_odoDetalleVenta98_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoDetalleVenta(fakeValue[play.mvc.Http.Request], fakeValue[Long], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoDetalleVenta",
      Seq(classOf[play.mvc.Http.Request], classOf[Long], classOf[String], classOf[String]),
      "GET",
      this.prefix + """odoDetalleVenta/""" + "$" + """id_venta<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:171
  private lazy val controllers_MnuOdo_odoFirmaOperador99_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoFirmaOperador/"), DynamicPart("id_venta", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_odoFirmaOperador99_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoFirmaOperador(fakeValue[play.mvc.Http.Request], fakeValue[Long], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoFirmaOperador",
      Seq(classOf[play.mvc.Http.Request], classOf[Long], classOf[String], classOf[String]),
      "GET",
      this.prefix + """odoFirmaOperador/""" + "$" + """id_venta<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:172
  private lazy val controllers_MnuOdo_grabarFirmaOperador100_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabarFirmaOperador/")))
  )
  private lazy val controllers_MnuOdo_grabarFirmaOperador100_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.grabarFirmaOperador(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "grabarFirmaOperador",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grabarFirmaOperador/""",
      """""",
      Seq()
    )
  )

  // @LINE:173
  private lazy val controllers_MnuOdo_odoFirmaAutorizador101_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoFirmaAutorizador/"), DynamicPart("id_venta", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_odoFirmaAutorizador101_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoFirmaAutorizador(fakeValue[play.mvc.Http.Request], fakeValue[Long], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoFirmaAutorizador",
      Seq(classOf[play.mvc.Http.Request], classOf[Long], classOf[String], classOf[String]),
      "GET",
      this.prefix + """odoFirmaAutorizador/""" + "$" + """id_venta<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:174
  private lazy val controllers_MnuOdo_grabarFirmaAutorizador102_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabarFirmaAutorizador/")))
  )
  private lazy val controllers_MnuOdo_grabarFirmaAutorizador102_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.grabarFirmaAutorizador(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "grabarFirmaAutorizador",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grabarFirmaAutorizador/""",
      """""",
      Seq()
    )
  )

  // @LINE:175
  private lazy val controllers_MnuOdo_odoVentaServicioElimina103_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoVentaServicioElimina/")))
  )
  private lazy val controllers_MnuOdo_odoVentaServicioElimina103_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.odoVentaServicioElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "odoVentaServicioElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoVentaServicioElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:177
  private lazy val controllers_MnuOdo_reportProfPerOdo104_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportProfPerOdo/")))
  )
  private lazy val controllers_MnuOdo_reportProfPerOdo104_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportProfPerOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportProfPerOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportProfPerOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:178
  private lazy val controllers_MnuOdo_reportProfProyOdo105_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportProfProyOdo/"), DynamicPart("archivoPDF", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_reportProfProyOdo105_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportProfProyOdo(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportProfProyOdo",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "POST",
      this.prefix + """reportProfProyOdo/""" + "$" + """archivoPDF<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:179
  private lazy val controllers_MnuOdo_reportProfProyOdoExcel106_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportProfProyOdoExcel/")))
  )
  private lazy val controllers_MnuOdo_reportProfProyOdoExcel106_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportProfProyOdoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportProfProyOdoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportProfProyOdoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:180
  private lazy val controllers_MnuOdo_reportProfProyDetalleOdo107_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportProfProyDetalleOdo/")))
  )
  private lazy val controllers_MnuOdo_reportProfProyDetalleOdo107_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportProfProyDetalleOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportProfProyDetalleOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportProfProyDetalleOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:181
  private lazy val controllers_MnuOdo_reportProfProyDetalleOdoExcel108_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportProfProyDetalleOdoExcel/")))
  )
  private lazy val controllers_MnuOdo_reportProfProyDetalleOdoExcel108_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportProfProyDetalleOdoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportProfProyDetalleOdoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportProfProyDetalleOdoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:182
  private lazy val controllers_MnuOdo_generarProfPdfXlsxXmlJsonOdo109_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generarProfPdfXlsxXmlJsonOdo/")))
  )
  private lazy val controllers_MnuOdo_generarProfPdfXlsxXmlJsonOdo109_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.generarProfPdfXlsxXmlJsonOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "generarProfPdfXlsxXmlJsonOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generarProfPdfXlsxXmlJsonOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:184
  private lazy val controllers_MnuOdo_proformaListaOdo110_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaListaOdo/")))
  )
  private lazy val controllers_MnuOdo_proformaListaOdo110_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.proformaListaOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "proformaListaOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proformaListaOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:185
  private lazy val controllers_MnuOdo_proformaEliminaOdo111_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaEliminaOdo/")))
  )
  private lazy val controllers_MnuOdo_proformaEliminaOdo111_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.proformaEliminaOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "proformaEliminaOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proformaEliminaOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:187
  private lazy val controllers_MnuOdo_ajustesEpOdo112_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpOdo/")))
  )
  private lazy val controllers_MnuOdo_ajustesEpOdo112_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.ajustesEpOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "ajustesEpOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """ajustesEpOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:188
  private lazy val controllers_MnuOdo_ajustesEpListadoOdo113_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpListadoOdo/")))
  )
  private lazy val controllers_MnuOdo_ajustesEpListadoOdo113_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.ajustesEpListadoOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "ajustesEpListadoOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpListadoOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:189
  private lazy val controllers_MnuOdo_ajustesEpNuevoOdo114_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpNuevoOdo/")))
  )
  private lazy val controllers_MnuOdo_ajustesEpNuevoOdo114_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.ajustesEpNuevoOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "ajustesEpNuevoOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpNuevoOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:190
  private lazy val controllers_MnuOdo_ajustesEpGrabarOdo115_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpGrabarOdo/")))
  )
  private lazy val controllers_MnuOdo_ajustesEpGrabarOdo115_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.ajustesEpGrabarOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "ajustesEpGrabarOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpGrabarOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:191
  private lazy val controllers_MnuOdo_ajustesEpEliminarOdo116_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpEliminarOdo/")))
  )
  private lazy val controllers_MnuOdo_ajustesEpEliminarOdo116_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.ajustesEpEliminarOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "ajustesEpEliminarOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpEliminarOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:192
  private lazy val controllers_MnuOdo_ajustesEpModificarOdo117_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpModificarOdo/")))
  )
  private lazy val controllers_MnuOdo_ajustesEpModificarOdo117_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.ajustesEpModificarOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "ajustesEpModificarOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpModificarOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:193
  private lazy val controllers_MnuOdo_ajustesEpUpdateOdo118_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpUpdateOdo/")))
  )
  private lazy val controllers_MnuOdo_ajustesEpUpdateOdo118_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.ajustesEpUpdateOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "ajustesEpUpdateOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpUpdateOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:194
  private lazy val controllers_MnuOdo_ajusteGrabaPDFodo119_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajusteGrabaPDFodo/")))
  )
  private lazy val controllers_MnuOdo_ajusteGrabaPDFodo119_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.ajusteGrabaPDFodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "ajusteGrabaPDFodo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajusteGrabaPDFodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:195
  private lazy val controllers_MnuOdo_ajustesEpRptOdo120_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpRptOdo/")))
  )
  private lazy val controllers_MnuOdo_ajustesEpRptOdo120_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.ajustesEpRptOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "ajustesEpRptOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """ajustesEpRptOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:196
  private lazy val controllers_MnuOdo_ajustesEpRptDetalleOdo121_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpRptDetalleOdo/")))
  )
  private lazy val controllers_MnuOdo_ajustesEpRptDetalleOdo121_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.ajustesEpRptDetalleOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "ajustesEpRptDetalleOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpRptDetalleOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:197
  private lazy val controllers_MnuOdo_ajustesPeriodoEpRpt1Odo122_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesPeriodoEpRpt1Odo/")))
  )
  private lazy val controllers_MnuOdo_ajustesPeriodoEpRpt1Odo122_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.ajustesPeriodoEpRpt1Odo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "ajustesPeriodoEpRpt1Odo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """ajustesPeriodoEpRpt1Odo/""",
      """""",
      Seq()
    )
  )

  // @LINE:198
  private lazy val controllers_MnuOdo_ajustesPeriodoEpRpt2Odo123_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesPeriodoEpRpt2Odo/")))
  )
  private lazy val controllers_MnuOdo_ajustesPeriodoEpRpt2Odo123_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.ajustesPeriodoEpRpt2Odo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "ajustesPeriodoEpRpt2Odo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesPeriodoEpRpt2Odo/""",
      """""",
      Seq()
    )
  )

  // @LINE:201
  private lazy val controllers_MnuOdo_reportOdoPeriodoResumen124_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOdoPeriodoResumen/")))
  )
  private lazy val controllers_MnuOdo_reportOdoPeriodoResumen124_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportOdoPeriodoResumen(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportOdoPeriodoResumen",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportOdoPeriodoResumen/""",
      """""",
      Seq()
    )
  )

  // @LINE:202
  private lazy val controllers_MnuOdo_reportOdoResumen125_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOdoResumen/")))
  )
  private lazy val controllers_MnuOdo_reportOdoResumen125_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportOdoResumen(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportOdoResumen",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportOdoResumen/""",
      """""",
      Seq()
    )
  )

  // @LINE:203
  private lazy val controllers_MnuOdo_reportOdoResumenExcel126_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOdoResumenExcel/")))
  )
  private lazy val controllers_MnuOdo_reportOdoResumenExcel126_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportOdoResumenExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportOdoResumenExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportOdoResumenExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:205
  private lazy val controllers_MnuOdo_reportOdoConsolidado127_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOdoConsolidado/")))
  )
  private lazy val controllers_MnuOdo_reportOdoConsolidado127_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportOdoConsolidado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportOdoConsolidado",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportOdoConsolidado/""",
      """""",
      Seq()
    )
  )

  // @LINE:206
  private lazy val controllers_MnuOdo_reportOdoConsolidadoRtp128_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOdoConsolidadoRtp/")))
  )
  private lazy val controllers_MnuOdo_reportOdoConsolidadoRtp128_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportOdoConsolidadoRtp(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportOdoConsolidadoRtp",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportOdoConsolidadoRtp/""",
      """""",
      Seq()
    )
  )

  // @LINE:207
  private lazy val controllers_MnuOdo_reportOdoConsolidadoRtpExcel129_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOdoConsolidadoRtpExcel/")))
  )
  private lazy val controllers_MnuOdo_reportOdoConsolidadoRtpExcel129_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportOdoConsolidadoRtpExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportOdoConsolidadoRtpExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportOdoConsolidadoRtpExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:209
  private lazy val controllers_MnuOdo_reporteMovOdoCantidades130_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovOdoCantidades/")))
  )
  private lazy val controllers_MnuOdo_reporteMovOdoCantidades130_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reporteMovOdoCantidades(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reporteMovOdoCantidades",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteMovOdoCantidades/""",
      """""",
      Seq()
    )
  )

  // @LINE:210
  private lazy val controllers_MnuOdo_reporteMovOdoCantLista131_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovOdoCantLista/")))
  )
  private lazy val controllers_MnuOdo_reporteMovOdoCantLista131_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reporteMovOdoCantLista(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reporteMovOdoCantLista",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovOdoCantLista/""",
      """""",
      Seq()
    )
  )

  // @LINE:211
  private lazy val controllers_MnuOdo_reporteMovOdoCantDetalle132_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovOdoCantDetalle/")))
  )
  private lazy val controllers_MnuOdo_reporteMovOdoCantDetalle132_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reporteMovOdoCantDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reporteMovOdoCantDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovOdoCantDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:212
  private lazy val controllers_MnuOdo_reporteMovOdoCantDetalleExcel133_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovOdoCantDetalleExcel/")))
  )
  private lazy val controllers_MnuOdo_reporteMovOdoCantDetalleExcel133_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reporteMovOdoCantDetalleExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reporteMovOdoCantDetalleExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovOdoCantDetalleExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:214
  private lazy val controllers_MnuOdo_reporteMovOdoValorizado134_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovOdoValorizado/")))
  )
  private lazy val controllers_MnuOdo_reporteMovOdoValorizado134_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reporteMovOdoValorizado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reporteMovOdoValorizado",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteMovOdoValorizado/""",
      """""",
      Seq()
    )
  )

  // @LINE:215
  private lazy val controllers_MnuOdo_reporteMovOdoValorizadoLista135_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovOdoValorizadoLista/")))
  )
  private lazy val controllers_MnuOdo_reporteMovOdoValorizadoLista135_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reporteMovOdoValorizadoLista(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reporteMovOdoValorizadoLista",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovOdoValorizadoLista/""",
      """""",
      Seq()
    )
  )

  // @LINE:216
  private lazy val controllers_MnuOdo_reporteMovOdoValorizadoDetalle136_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovOdoValorizadoDetalle/")))
  )
  private lazy val controllers_MnuOdo_reporteMovOdoValorizadoDetalle136_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reporteMovOdoValorizadoDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reporteMovOdoValorizadoDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovOdoValorizadoDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:217
  private lazy val controllers_MnuOdo_reporteMovOdoValorizadoDetalleExcel137_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovOdoValorizadoDetalleExcel/")))
  )
  private lazy val controllers_MnuOdo_reporteMovOdoValorizadoDetalleExcel137_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reporteMovOdoValorizadoDetalleExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reporteMovOdoValorizadoDetalleExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovOdoValorizadoDetalleExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:219
  private lazy val controllers_MnuOdo_reportOperadorConsol0138_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOperadorConsol0/")))
  )
  private lazy val controllers_MnuOdo_reportOperadorConsol0138_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportOperadorConsol0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportOperadorConsol0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportOperadorConsol0/""",
      """""",
      Seq()
    )
  )

  // @LINE:220
  private lazy val controllers_MnuOdo_reportOperadorConsol1139_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOperadorConsol1/")))
  )
  private lazy val controllers_MnuOdo_reportOperadorConsol1139_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportOperadorConsol1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportOperadorConsol1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportOperadorConsol1/""",
      """""",
      Seq()
    )
  )

  // @LINE:221
  private lazy val controllers_MnuOdo_reportOperadorConsol1Excel140_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOperadorConsol1Excel/")))
  )
  private lazy val controllers_MnuOdo_reportOperadorConsol1Excel140_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_12.reportOperadorConsol1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdo",
      "reportOperadorConsol1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportOperadorConsol1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:232
  private lazy val controllers_HomeController_inicio141_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odo")))
  )
  private lazy val controllers_HomeController_inicio141_invoker = createInvoker(
    HomeController_18.inicio(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "inicio",
      Nil,
      "GET",
      this.prefix + """odo""",
      """""",
      Seq()
    )
  )

  // @LINE:234
  private lazy val controllers_MnuOdoAppWeb_odoVentasWeb142_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoVentasWeb/")))
  )
  private lazy val controllers_MnuOdoAppWeb_odoVentasWeb142_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoVentasWeb(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoVentasWeb",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoVentasWeb/""",
      """""",
      Seq()
    )
  )

  // @LINE:235
  private lazy val controllers_MnuOdoAppWeb_odoVentasHomeWeb143_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoVentasWeb/")))
  )
  private lazy val controllers_MnuOdoAppWeb_odoVentasHomeWeb143_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoVentasHomeWeb(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoVentasHomeWeb",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """odoVentasWeb/""",
      """""",
      Seq()
    )
  )

  // @LINE:236
  private lazy val controllers_MnuOdoAppWeb_odoVentasHomeWeb144_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoVentasHomeWeb/")))
  )
  private lazy val controllers_MnuOdoAppWeb_odoVentasHomeWeb144_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoVentasHomeWeb(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoVentasHomeWeb",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """odoVentasHomeWeb/""",
      """""",
      Seq()
    )
  )

  // @LINE:237
  private lazy val controllers_MnuOdoAppWeb_odoVentasGrabarWeb145_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoVentasGrabarWeb/")))
  )
  private lazy val controllers_MnuOdoAppWeb_odoVentasGrabarWeb145_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoVentasGrabarWeb(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoVentasGrabarWeb",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoVentasGrabarWeb/""",
      """""",
      Seq()
    )
  )

  // @LINE:238
  private lazy val controllers_MnuOdoAppWeb_odoListarVentasWeb146_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoListarVentasWeb/"), DynamicPart("year", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdoAppWeb_odoListarVentasWeb146_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoListarVentasWeb(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoListarVentasWeb",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """odoListarVentasWeb/""" + "$" + """year<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:239
  private lazy val controllers_MnuOdoAppWeb_odoDetalleVentaWeb147_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoDetalleVentaWeb/"), DynamicPart("id_venta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdoAppWeb_odoDetalleVentaWeb147_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoDetalleVentaWeb(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoDetalleVentaWeb",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """odoDetalleVentaWeb/""" + "$" + """id_venta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:240
  private lazy val controllers_MnuOdoAppWeb_odoFirmaOperadorWeb148_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoFirmaOperadorWeb/"), DynamicPart("id_venta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdoAppWeb_odoFirmaOperadorWeb148_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoFirmaOperadorWeb(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoFirmaOperadorWeb",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """odoFirmaOperadorWeb/""" + "$" + """id_venta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:241
  private lazy val controllers_MnuOdoAppWeb_grabarFirmaOperadorWeb149_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabarFirmaOperadorWeb/")))
  )
  private lazy val controllers_MnuOdoAppWeb_grabarFirmaOperadorWeb149_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.grabarFirmaOperadorWeb(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "grabarFirmaOperadorWeb",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grabarFirmaOperadorWeb/""",
      """""",
      Seq()
    )
  )

  // @LINE:242
  private lazy val controllers_MnuOdoAppWeb_odoFirmaAutorizadorWeb150_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoFirmaAutorizadorWeb/"), DynamicPart("id_venta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdoAppWeb_odoFirmaAutorizadorWeb150_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoFirmaAutorizadorWeb(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoFirmaAutorizadorWeb",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """odoFirmaAutorizadorWeb/""" + "$" + """id_venta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:243
  private lazy val controllers_MnuOdoAppWeb_grabarFirmaAutorizadorWeb151_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabarFirmaAutorizadorWeb/")))
  )
  private lazy val controllers_MnuOdoAppWeb_grabarFirmaAutorizadorWeb151_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.grabarFirmaAutorizadorWeb(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "grabarFirmaAutorizadorWeb",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grabarFirmaAutorizadorWeb/""",
      """""",
      Seq()
    )
  )

  // @LINE:244
  private lazy val controllers_MnuOdoAppWeb_odoVentaServicioEliminaWeb152_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoVentaServicioEliminaWeb/")))
  )
  private lazy val controllers_MnuOdoAppWeb_odoVentaServicioEliminaWeb152_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoVentaServicioEliminaWeb(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoVentaServicioEliminaWeb",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """odoVentaServicioEliminaWeb/""",
      """""",
      Seq()
    )
  )

  // @LINE:247
  private lazy val controllers_MnuOdoAppWeb_odoAutorizaDetalleVentaWeb153_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoAutorizaDetalleVentaWeb/"), DynamicPart("id_venta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdoAppWeb_odoAutorizaDetalleVentaWeb153_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoAutorizaDetalleVentaWeb(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoAutorizaDetalleVentaWeb",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """odoAutorizaDetalleVentaWeb/""" + "$" + """id_venta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:248
  private lazy val controllers_MnuOdoAppWeb_odoAutorizaListarVentasWeb154_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoAutorizaListarVentasWeb/")))
  )
  private lazy val controllers_MnuOdoAppWeb_odoAutorizaListarVentasWeb154_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoAutorizaListarVentasWeb(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoAutorizaListarVentasWeb",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """odoAutorizaListarVentasWeb/""",
      """""",
      Seq()
    )
  )

  // @LINE:249
  private lazy val controllers_MnuOdoAppWeb_odoAutorizaFirmaWeb155_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("odoAutorizaFirmaWeb/"), DynamicPart("id_venta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdoAppWeb_odoAutorizaFirmaWeb155_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.odoAutorizaFirmaWeb(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "odoAutorizaFirmaWeb",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """odoAutorizaFirmaWeb/""" + "$" + """id_venta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:250
  private lazy val controllers_MnuOdoAppWeb_grabarAutorizaFirmaWeb156_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabarAutorizaFirmaWeb/")))
  )
  private lazy val controllers_MnuOdoAppWeb_grabarAutorizaFirmaWeb156_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.grabarAutorizaFirmaWeb(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuOdoAppWeb",
      "grabarAutorizaFirmaWeb",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grabarAutorizaFirmaWeb/""",
      """""",
      Seq()
    )
  )

  // @LINE:259
  private lazy val controllers_AppCPanel_vistaCPanel157_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cpanel")))
  )
  private lazy val controllers_AppCPanel_vistaCPanel157_invoker = createInvoker(
    AppCPanel_10.vistaCPanel(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AppCPanel",
      "vistaCPanel",
      Nil,
      "GET",
      this.prefix + """cpanel""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MODULO CPANEL
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:260
  private lazy val controllers_AppCPanel_inicioCPanelHome158_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("inicioCPanelHome/")))
  )
  private lazy val controllers_AppCPanel_inicioCPanelHome158_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      AppCPanel_10.inicioCPanelHome(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AppCPanel",
      "inicioCPanelHome",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """inicioCPanelHome/""",
      """""",
      Seq()
    )
  )

  // @LINE:261
  private lazy val controllers_AppCPanel_inicioCPanel159_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("inicioCPanel/")))
  )
  private lazy val controllers_AppCPanel_inicioCPanel159_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      AppCPanel_10.inicioCPanel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AppCPanel",
      "inicioCPanel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """inicioCPanel/""",
      """""",
      Seq()
    )
  )

  // @LINE:262
  private lazy val controllers_AppCPanel_cPanelModalItemsControlados160_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cPanelModalItemsControlados/")))
  )
  private lazy val controllers_AppCPanel_cPanelModalItemsControlados160_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      AppCPanel_10.cPanelModalItemsControlados(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AppCPanel",
      "cPanelModalItemsControlados",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cPanelModalItemsControlados/""",
      """""",
      Seq()
    )
  )

  // @LINE:263
  private lazy val controllers_AppCPanel_cPanelModalVigentes161_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cPanelModalVigentes/")))
  )
  private lazy val controllers_AppCPanel_cPanelModalVigentes161_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      AppCPanel_10.cPanelModalVigentes(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AppCPanel",
      "cPanelModalVigentes",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cPanelModalVigentes/""",
      """""",
      Seq()
    )
  )

  // @LINE:264
  private lazy val controllers_AppCPanel_cPanelModalNoVigentes162_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cPanelModalNoVigentes/")))
  )
  private lazy val controllers_AppCPanel_cPanelModalNoVigentes162_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      AppCPanel_10.cPanelModalNoVigentes(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AppCPanel",
      "cPanelModalNoVigentes",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cPanelModalNoVigentes/""",
      """""",
      Seq()
    )
  )

  // @LINE:265
  private lazy val controllers_AppCPanel_inicioCPanel2163_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("inicioCPanel2/")))
  )
  private lazy val controllers_AppCPanel_inicioCPanel2163_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      AppCPanel_10.inicioCPanel2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AppCPanel",
      "inicioCPanel2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """inicioCPanel2/""",
      """""",
      Seq()
    )
  )

  // @LINE:273
  private lazy val controllers_MnuPlanes_plantillaIConstruyeDownload164_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("plantillaIConstruyeDownload/")))
  )
  private lazy val controllers_MnuPlanes_plantillaIConstruyeDownload164_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.plantillaIConstruyeDownload(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "plantillaIConstruyeDownload",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """plantillaIConstruyeDownload/""",
      """""",
      Seq()
    )
  )

  // @LINE:274
  private lazy val controllers_MnuPlanes_plantillaIConstruyeUpload165_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("plantillaIConstruyeUpload/")))
  )
  private lazy val controllers_MnuPlanes_plantillaIConstruyeUpload165_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.plantillaIConstruyeUpload(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "plantillaIConstruyeUpload",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """plantillaIConstruyeUpload/""",
      """""",
      Seq()
    )
  )

  // @LINE:277
  private lazy val controllers_MnuPlanes_importaHojaVidaIconstruye0166_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("importaHojaVidaIconstruye0/")))
  )
  private lazy val controllers_MnuPlanes_importaHojaVidaIconstruye0166_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.importaHojaVidaIconstruye0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "importaHojaVidaIconstruye0",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """importaHojaVidaIconstruye0/""",
      """""",
      Seq()
    )
  )

  // @LINE:278
  private lazy val controllers_MnuPlanes_importaHojaVidaIconstruye1167_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("importaHojaVidaIconstruye1/")))
  )
  private lazy val controllers_MnuPlanes_importaHojaVidaIconstruye1167_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.importaHojaVidaIconstruye1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "importaHojaVidaIconstruye1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """importaHojaVidaIconstruye1/""",
      """""",
      Seq()
    )
  )

  // @LINE:279
  private lazy val controllers_MnuPlanes_importaHojaVidaIconstruye2168_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("importaHojaVidaIconstruye2/")))
  )
  private lazy val controllers_MnuPlanes_importaHojaVidaIconstruye2168_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.importaHojaVidaIconstruye2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "importaHojaVidaIconstruye2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """importaHojaVidaIconstruye2/""",
      """""",
      Seq()
    )
  )

  // @LINE:282
  private lazy val controllers_MnuPlanes_detalleProductoUploadIConstruye169_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("detalleProductoUploadIConstruye/")))
  )
  private lazy val controllers_MnuPlanes_detalleProductoUploadIConstruye169_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.detalleProductoUploadIConstruye(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "detalleProductoUploadIConstruye",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """detalleProductoUploadIConstruye/""",
      """""",
      Seq()
    )
  )

  // @LINE:283
  private lazy val controllers_MnuPlanes_detalleProductoDownloadMada170_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("detalleProductoDownloadMada/")))
  )
  private lazy val controllers_MnuPlanes_detalleProductoDownloadMada170_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.detalleProductoDownloadMada(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "detalleProductoDownloadMada",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """detalleProductoDownloadMada/""",
      """""",
      Seq()
    )
  )

  // @LINE:284
  private lazy val controllers_MnuPlanes_detalleProductoUploadMada171_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("detalleProductoUploadMada/")))
  )
  private lazy val controllers_MnuPlanes_detalleProductoUploadMada171_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.detalleProductoUploadMada(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "detalleProductoUploadMada",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """detalleProductoUploadMada/""",
      """""",
      Seq()
    )
  )

  // @LINE:294
  private lazy val controllers_MnuPlanes_tipoTrabajoMantencion172_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoTrabajoMantencion/")))
  )
  private lazy val controllers_MnuPlanes_tipoTrabajoMantencion172_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.tipoTrabajoMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "tipoTrabajoMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """tipoTrabajoMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:295
  private lazy val controllers_MnuPlanes_tipoTrabajoModifica173_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoTrabajoModifica/")))
  )
  private lazy val controllers_MnuPlanes_tipoTrabajoModifica173_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.tipoTrabajoModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "tipoTrabajoModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tipoTrabajoModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:296
  private lazy val controllers_MnuPlanes_modificaTipoPorCampoAjax174_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaTipoPorCampoAjax/")))
  )
  private lazy val controllers_MnuPlanes_modificaTipoPorCampoAjax174_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.modificaTipoPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "modificaTipoPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaTipoPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:297
  private lazy val controllers_MnuPlanes_tipoTrabajoAgrega175_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoTrabajoAgrega/")))
  )
  private lazy val controllers_MnuPlanes_tipoTrabajoAgrega175_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.tipoTrabajoAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "tipoTrabajoAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """tipoTrabajoAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:298
  private lazy val controllers_MnuPlanes_tipoTrabajoNuevo176_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoTrabajoNuevo/")))
  )
  private lazy val controllers_MnuPlanes_tipoTrabajoNuevo176_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.tipoTrabajoNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "tipoTrabajoNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tipoTrabajoNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:299
  private lazy val controllers_MnuPlanes_tipoTrabajoElimina177_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoTrabajoElimina/")))
  )
  private lazy val controllers_MnuPlanes_tipoTrabajoElimina177_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.tipoTrabajoElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "tipoTrabajoElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tipoTrabajoElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:301
  private lazy val controllers_MnuPlanes_tipoPlanMantencion178_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoPlanMantencion/")))
  )
  private lazy val controllers_MnuPlanes_tipoPlanMantencion178_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.tipoPlanMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "tipoPlanMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """tipoPlanMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:302
  private lazy val controllers_MnuPlanes_tipoPlanModifica179_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoPlanModifica/")))
  )
  private lazy val controllers_MnuPlanes_tipoPlanModifica179_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.tipoPlanModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "tipoPlanModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tipoPlanModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:303
  private lazy val controllers_MnuPlanes_modificaPlanPorCampoAjax180_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaPlanPorCampoAjax/")))
  )
  private lazy val controllers_MnuPlanes_modificaPlanPorCampoAjax180_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.modificaPlanPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "modificaPlanPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaPlanPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:304
  private lazy val controllers_MnuPlanes_tipoPlanAgrega181_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoPlanAgrega/")))
  )
  private lazy val controllers_MnuPlanes_tipoPlanAgrega181_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.tipoPlanAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "tipoPlanAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """tipoPlanAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:305
  private lazy val controllers_MnuPlanes_tipoPlanNuevo182_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoPlanNuevo/")))
  )
  private lazy val controllers_MnuPlanes_tipoPlanNuevo182_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.tipoPlanNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "tipoPlanNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tipoPlanNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:306
  private lazy val controllers_MnuPlanes_tipoPlanElimina183_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoPlanElimina/")))
  )
  private lazy val controllers_MnuPlanes_tipoPlanElimina183_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.tipoPlanElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "tipoPlanElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tipoPlanElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:308
  private lazy val controllers_MnuPlanes_hojaVidaPlanMantencion184_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaPlanMantencion/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaPlanMantencion184_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaPlanMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaPlanMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hojaVidaPlanMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:309
  private lazy val controllers_MnuPlanes_hojaVidaPlanModifica185_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaPlanModifica/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaPlanModifica185_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaPlanModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaPlanModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaPlanModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:310
  private lazy val controllers_MnuPlanes_hojaVidaPlanModificaGet186_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaPlanModificaGet/"), DynamicPart("idEquipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuPlanes_hojaVidaPlanModificaGet186_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaPlanModificaGet(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaPlanModificaGet",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """hojaVidaPlanModificaGet/""" + "$" + """idEquipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:311
  private lazy val controllers_MnuPlanes_hojaVidaAgregaPlan187_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaAgregaPlan/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaAgregaPlan187_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaAgregaPlan(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaAgregaPlan",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaAgregaPlan/""",
      """""",
      Seq()
    )
  )

  // @LINE:312
  private lazy val controllers_MnuPlanes_hojaVidaPlanElimina188_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaPlanElimina/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaPlanElimina188_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaPlanElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaPlanElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaPlanElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:313
  private lazy val controllers_MnuPlanes_hojaVidaPlanCrear189_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaPlanCrear/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaPlanCrear189_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaPlanCrear(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaPlanCrear",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaPlanCrear/""",
      """""",
      Seq()
    )
  )

  // @LINE:314
  private lazy val controllers_MnuPlanes_hojaVidaPlanCrear2190_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaPlanCrear2/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaPlanCrear2190_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaPlanCrear2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaPlanCrear2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaPlanCrear2/""",
      """""",
      Seq()
    )
  )

  // @LINE:315
  private lazy val controllers_MnuPlanes_actualCampoPlanMantencionAjax191_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualCampoPlanMantencionAjax/")))
  )
  private lazy val controllers_MnuPlanes_actualCampoPlanMantencionAjax191_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.actualCampoPlanMantencionAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "actualCampoPlanMantencionAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualCampoPlanMantencionAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:317
  private lazy val controllers_MnuPlanes_hojaVidaMantencionLista192_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaMantencionLista/"), DynamicPart("flag", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuPlanes_hojaVidaMantencionLista192_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaMantencionLista(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaMantencionLista",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """hojaVidaMantencionLista/""" + "$" + """flag<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:318
  private lazy val controllers_MnuPlanes_hojaVidaMantencionPlan193_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaMantencionPlan/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaMantencionPlan193_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaMantencionPlan(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaMantencionPlan",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaMantencionPlan/""",
      """""",
      Seq()
    )
  )

  // @LINE:319
  private lazy val controllers_MnuPlanes_hojaVidaMantencionHoja194_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaMantencionHoja/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaMantencionHoja194_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaMantencionHoja(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaMantencionHoja",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaMantencionHoja/""",
      """""",
      Seq()
    )
  )

  // @LINE:320
  private lazy val controllers_MnuPlanes_hojaVidaMantencionHojaReturn195_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaMantencionHojaReturn/"), DynamicPart("id_equipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuPlanes_hojaVidaMantencionHojaReturn195_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaMantencionHojaReturn(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaMantencionHojaReturn",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """hojaVidaMantencionHojaReturn/""" + "$" + """id_equipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:321
  private lazy val controllers_MnuPlanes_hojaVidaMantencionListaExcel196_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaMantencionListaExcel/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaMantencionListaExcel196_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaMantencionListaExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaMantencionListaExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaMantencionListaExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:324
  private lazy val controllers_MnuPlanes_hojaVidaMantencionHojaAgrega197_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaMantencionHojaAgrega/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaMantencionHojaAgrega197_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaMantencionHojaAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaMantencionHojaAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaMantencionHojaAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:325
  private lazy val controllers_MnuPlanes_hojaVidaMantencionHojaElimina198_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaMantencionHojaElimina/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaMantencionHojaElimina198_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaMantencionHojaElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaMantencionHojaElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaMantencionHojaElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:326
  private lazy val controllers_MnuPlanes_grabarHojaVidaPdf199_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabarHojaVidaPdf/")))
  )
  private lazy val controllers_MnuPlanes_grabarHojaVidaPdf199_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.grabarHojaVidaPdf(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "grabarHojaVidaPdf",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grabarHojaVidaPdf/""",
      """""",
      Seq()
    )
  )

  // @LINE:327
  private lazy val controllers_MnuPlanes_actualCampoHojaVidaAjax200_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualCampoHojaVidaAjax/")))
  )
  private lazy val controllers_MnuPlanes_actualCampoHojaVidaAjax200_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.actualCampoHojaVidaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "actualCampoHojaVidaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualCampoHojaVidaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:329
  private lazy val controllers_MnuPlanes_hojaVidaReportLista201_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaReportLista/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaReportLista201_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaReportLista(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaReportLista",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hojaVidaReportLista/""",
      """""",
      Seq()
    )
  )

  // @LINE:330
  private lazy val controllers_MnuPlanes_hojaVidaReportDetalle202_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaReportDetalle/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaReportDetalle202_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaReportDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaReportDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaReportDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:331
  private lazy val controllers_MnuPlanes_hojaVidaReportDetalleExcel203_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaReportDetalleExcel/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaReportDetalleExcel203_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaReportDetalleExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaReportDetalleExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaReportDetalleExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:333
  private lazy val controllers_MnuPlanes_hojaVidaReportKpis204_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaReportKpis/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaReportKpis204_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaReportKpis(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaReportKpis",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hojaVidaReportKpis/""",
      """""",
      Seq()
    )
  )

  // @LINE:334
  private lazy val controllers_MnuPlanes_hojaVidaReportKpisExcel205_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaReportKpisExcel/")))
  )
  private lazy val controllers_MnuPlanes_hojaVidaReportKpisExcel205_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaReportKpisExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaReportKpisExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaVidaReportKpisExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:336
  private lazy val controllers_MnuPlanes_hojaVidaReportGrafico206_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaVidaReportGrafico/"), DynamicPart("id_grupo", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("periodo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuPlanes_hojaVidaReportGrafico206_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPlanes_11.hojaVidaReportGrafico(fakeValue[play.mvc.Http.Request], fakeValue[Long], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPlanes",
      "hojaVidaReportGrafico",
      Seq(classOf[play.mvc.Http.Request], classOf[Long], classOf[Long]),
      "GET",
      this.prefix + """hojaVidaReportGrafico/""" + "$" + """id_grupo<[^/]+>,""" + "$" + """periodo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:342
  private lazy val controllers_MnuReportes_reportInventarioEquipoCorte207_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioEquipoCorte/"), DynamicPart("tipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_reportInventarioEquipoCorte207_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioEquipoCorte(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioEquipoCorte",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """reportInventarioEquipoCorte/""" + "$" + """tipo<[^/]+>""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU REPORTES
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:343
  private lazy val controllers_MnuReportes_reportInventarioEquipo208_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioEquipo/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioEquipo208_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:344
  private lazy val controllers_MnuReportes_reportInventarioGeneralXEquipo209_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioGeneralXEquipo/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioGeneralXEquipo209_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioGeneralXEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioGeneralXEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioGeneralXEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:345
  private lazy val controllers_MnuReportes_reportInventTrazaEquipoEnBod210_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventTrazaEquipoEnBod/")))
  )
  private lazy val controllers_MnuReportes_reportInventTrazaEquipoEnBod210_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventTrazaEquipoEnBod(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventTrazaEquipoEnBod",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventTrazaEquipoEnBod/""",
      """""",
      Seq()
    )
  )

  // @LINE:346
  private lazy val controllers_MnuReportes_reportInventarioEquipoExcel211_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioEquipoExcel/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioEquipoExcel211_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioEquipoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioEquipoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioEquipoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:348
  private lazy val controllers_MnuReportes_reportEquipoBodegaCorte212_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportEquipoBodegaCorte/"), DynamicPart("tipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_reportEquipoBodegaCorte212_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportEquipoBodegaCorte(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportEquipoBodegaCorte",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """reportEquipoBodegaCorte/""" + "$" + """tipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:349
  private lazy val controllers_MnuReportes_reportEquipoBodega213_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportEquipoBodega/")))
  )
  private lazy val controllers_MnuReportes_reportEquipoBodega213_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportEquipoBodega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportEquipoBodega",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportEquipoBodega/""",
      """""",
      Seq()
    )
  )

  // @LINE:350
  private lazy val controllers_MnuReportes_reportEquipoBodegaXEquipo214_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportEquipoBodegaXEquipo/")))
  )
  private lazy val controllers_MnuReportes_reportEquipoBodegaXEquipo214_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportEquipoBodegaXEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportEquipoBodegaXEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportEquipoBodegaXEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:351
  private lazy val controllers_MnuReportes_reportEquipoBodegaExcel215_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportEquipoBodegaExcel/")))
  )
  private lazy val controllers_MnuReportes_reportEquipoBodegaExcel215_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportEquipoBodegaExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportEquipoBodegaExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportEquipoBodegaExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:352
  private lazy val controllers_MnuReportes_reportEquipoBodegaTrazaEquipoEnBod216_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportEquipoBodegaTrazaEquipoEnBod/")))
  )
  private lazy val controllers_MnuReportes_reportEquipoBodegaTrazaEquipoEnBod216_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportEquipoBodegaTrazaEquipoEnBod(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportEquipoBodegaTrazaEquipoEnBod",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportEquipoBodegaTrazaEquipoEnBod/""",
      """""",
      Seq()
    )
  )

  // @LINE:355
  private lazy val controllers_MnuReportes_reportInventarioBodegaCorte217_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioBodegaCorte/"), DynamicPart("tipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_reportInventarioBodegaCorte217_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioBodegaCorte(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioBodegaCorte",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """reportInventarioBodegaCorte/""" + "$" + """tipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:356
  private lazy val controllers_MnuReportes_reportInventarioBodega218_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioBodega/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioBodega218_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioBodega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioBodega",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioBodega/""",
      """""",
      Seq()
    )
  )

  // @LINE:357
  private lazy val controllers_MnuReportes_reportInventarioSelectivoXBodega219_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioSelectivoXBodega/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioSelectivoXBodega219_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioSelectivoXBodega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioSelectivoXBodega",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioSelectivoXBodega/""",
      """""",
      Seq()
    )
  )

  // @LINE:358
  private lazy val controllers_MnuReportes_reportInventProyectoTrazaEquipoEnBod220_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventProyectoTrazaEquipoEnBod/")))
  )
  private lazy val controllers_MnuReportes_reportInventProyectoTrazaEquipoEnBod220_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventProyectoTrazaEquipoEnBod(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventProyectoTrazaEquipoEnBod",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventProyectoTrazaEquipoEnBod/""",
      """""",
      Seq()
    )
  )

  // @LINE:359
  private lazy val controllers_MnuReportes_reportInventarioSelectivoXBodegaExcel221_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioSelectivoXBodegaExcel/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioSelectivoXBodegaExcel221_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioSelectivoXBodegaExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioSelectivoXBodegaExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioSelectivoXBodegaExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:361
  private lazy val controllers_MnuReportes_reportInventarioGrupoCorte222_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioGrupoCorte/"), DynamicPart("tipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_reportInventarioGrupoCorte222_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioGrupoCorte(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioGrupoCorte",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """reportInventarioGrupoCorte/""" + "$" + """tipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:362
  private lazy val controllers_MnuReportes_reportInventarioGrupo223_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioGrupo/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioGrupo223_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioGrupo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioGrupo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioGrupo/""",
      """""",
      Seq()
    )
  )

  // @LINE:363
  private lazy val controllers_MnuReportes_reportInventarioSelectivoXGrupo224_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioSelectivoXGrupo/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioSelectivoXGrupo224_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioSelectivoXGrupo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioSelectivoXGrupo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioSelectivoXGrupo/""",
      """""",
      Seq()
    )
  )

  // @LINE:364
  private lazy val controllers_MnuReportes_reportInventarioSelectivoXGrupoExcel225_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioSelectivoXGrupoExcel/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioSelectivoXGrupoExcel225_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioSelectivoXGrupoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioSelectivoXGrupoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioSelectivoXGrupoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:366
  private lazy val controllers_MnuReportes_reportInventarioMatrizCorte226_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioMatrizCorte/"), DynamicPart("tipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_reportInventarioMatrizCorte226_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioMatrizCorte(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioMatrizCorte",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """reportInventarioMatrizCorte/""" + "$" + """tipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:367
  private lazy val controllers_MnuReportes_reportInventarioMatriz227_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioMatriz/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioMatriz227_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioMatriz(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioMatriz",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioMatriz/""",
      """""",
      Seq()
    )
  )

  // @LINE:368
  private lazy val controllers_MnuReportes_reportInventarioMatrizExcel228_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioMatrizExcel/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioMatrizExcel228_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioMatrizExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioMatrizExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioMatrizExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:370
  private lazy val controllers_MnuReportes_reportInventarioTodo229_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioTodo/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioTodo229_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioTodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioTodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportInventarioTodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:371
  private lazy val controllers_MnuReportes_reportInventarioTodoExcel230_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioTodoExcel/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioTodoExcel230_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioTodoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioTodoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioTodoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:373
  private lazy val controllers_MnuReportes_reportInventarioProyecto231_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioProyecto/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioProyecto231_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioProyecto(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioProyecto",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportInventarioProyecto/""",
      """""",
      Seq()
    )
  )

  // @LINE:374
  private lazy val controllers_MnuReportes_reportInventarioProyectoDetalle232_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioProyectoDetalle/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioProyectoDetalle232_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioProyectoDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioProyectoDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioProyectoDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:375
  private lazy val controllers_MnuReportes_reportInventarioProyectoDetalleExcel233_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportInventarioProyectoDetalleExcel/")))
  )
  private lazy val controllers_MnuReportes_reportInventarioProyectoDetalleExcel233_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportInventarioProyectoDetalleExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportInventarioProyectoDetalleExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportInventarioProyectoDetalleExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:377
  private lazy val controllers_MnuReportes_reportMailInventarioProyecto234_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mailingInventarioProyecto")))
  )
  private lazy val controllers_MnuReportes_reportMailInventarioProyecto234_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportMailInventarioProyecto(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportMailInventarioProyecto",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mailingInventarioProyecto""",
      """""",
      Seq()
    )
  )

  // @LINE:378
  private lazy val controllers_MnuReportes_reportMailInventarioProyectoExcel235_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mailingInventarioProyectoExcel/")))
  )
  private lazy val controllers_MnuReportes_reportMailInventarioProyectoExcel235_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportMailInventarioProyectoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportMailInventarioProyectoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mailingInventarioProyectoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:379
  private lazy val controllers_MnuReportes_reportMailInventarioProyectoEnviar236_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mailingInventarioProyectoEnviar/")))
  )
  private lazy val controllers_MnuReportes_reportMailInventarioProyectoEnviar236_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportMailInventarioProyectoEnviar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportMailInventarioProyectoEnviar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mailingInventarioProyectoEnviar/""",
      """""",
      Seq()
    )
  )

  // @LINE:383
  private lazy val controllers_MnuReportes_reporteMovimientosPeriodoAgrupado237_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovimientosPeriodoAgrupado/")))
  )
  private lazy val controllers_MnuReportes_reporteMovimientosPeriodoAgrupado237_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovimientosPeriodoAgrupado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovimientosPeriodoAgrupado",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteMovimientosPeriodoAgrupado/""",
      """""",
      Seq()
    )
  )

  // @LINE:384
  private lazy val controllers_MnuReportes_reporteMovimientosListaProyectosAgrupado238_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovimientosListaProyectosAgrupado/")))
  )
  private lazy val controllers_MnuReportes_reporteMovimientosListaProyectosAgrupado238_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovimientosListaProyectosAgrupado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovimientosListaProyectosAgrupado",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovimientosListaProyectosAgrupado/""",
      """""",
      Seq()
    )
  )

  // @LINE:385
  private lazy val controllers_MnuReportes_reporteMovimientosDetalleAgrupado239_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovimientosDetalleAgrupado/")))
  )
  private lazy val controllers_MnuReportes_reporteMovimientosDetalleAgrupado239_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovimientosDetalleAgrupado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovimientosDetalleAgrupado",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovimientosDetalleAgrupado/""",
      """""",
      Seq()
    )
  )

  // @LINE:386
  private lazy val controllers_MnuReportes_reporteMovimientosDetalleAgrupadoExcel240_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovimientosDetalleAgrupadoExcel/")))
  )
  private lazy val controllers_MnuReportes_reporteMovimientosDetalleAgrupadoExcel240_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovimientosDetalleAgrupadoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovimientosDetalleAgrupadoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovimientosDetalleAgrupadoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:388
  private lazy val controllers_MnuReportes_reporteMovSoloBodInternas0241_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovSoloBodInternas0/")))
  )
  private lazy val controllers_MnuReportes_reporteMovSoloBodInternas0241_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovSoloBodInternas0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovSoloBodInternas0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteMovSoloBodInternas0/""",
      """""",
      Seq()
    )
  )

  // @LINE:389
  private lazy val controllers_MnuReportes_reporteMovSoloBodInternas1242_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovSoloBodInternas1/")))
  )
  private lazy val controllers_MnuReportes_reporteMovSoloBodInternas1242_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovSoloBodInternas1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovSoloBodInternas1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovSoloBodInternas1/""",
      """""",
      Seq()
    )
  )

  // @LINE:390
  private lazy val controllers_MnuReportes_reporteMovSoloBodInternas2243_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovSoloBodInternas2/")))
  )
  private lazy val controllers_MnuReportes_reporteMovSoloBodInternas2243_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovSoloBodInternas2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovSoloBodInternas2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovSoloBodInternas2/""",
      """""",
      Seq()
    )
  )

  // @LINE:391
  private lazy val controllers_MnuReportes_reporteMovSoloBodInternas2Excel244_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovSoloBodInternas2Excel/")))
  )
  private lazy val controllers_MnuReportes_reporteMovSoloBodInternas2Excel244_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovSoloBodInternas2Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovSoloBodInternas2Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovSoloBodInternas2Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:396
  private lazy val controllers_MnuReportes_reporteMovimientosPeriodo245_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovimientosPeriodo/")))
  )
  private lazy val controllers_MnuReportes_reporteMovimientosPeriodo245_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovimientosPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovimientosPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteMovimientosPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:397
  private lazy val controllers_MnuReportes_reporteMovimientosListaProyectos246_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovimientosListaProyectos/")))
  )
  private lazy val controllers_MnuReportes_reporteMovimientosListaProyectos246_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovimientosListaProyectos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovimientosListaProyectos",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovimientosListaProyectos/""",
      """""",
      Seq()
    )
  )

  // @LINE:398
  private lazy val controllers_MnuReportes_reporteMovimientosDetalle247_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovimientosDetalle/")))
  )
  private lazy val controllers_MnuReportes_reporteMovimientosDetalle247_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovimientosDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovimientosDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovimientosDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:399
  private lazy val controllers_MnuReportes_reporteMovimientosDetalleExcel248_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovimientosDetalleExcel/")))
  )
  private lazy val controllers_MnuReportes_reporteMovimientosDetalleExcel248_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovimientosDetalleExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovimientosDetalleExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovimientosDetalleExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:401
  private lazy val controllers_MnuReportes_reporteMovimientosListaProyectosIE249_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovimientosListaProyectosIE/")))
  )
  private lazy val controllers_MnuReportes_reporteMovimientosListaProyectosIE249_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovimientosListaProyectosIE(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovimientosListaProyectosIE",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteMovimientosListaProyectosIE/""",
      """""",
      Seq()
    )
  )

  // @LINE:402
  private lazy val controllers_MnuReportes_reporteMovimientosDetalleIE250_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovimientosDetalleIE/")))
  )
  private lazy val controllers_MnuReportes_reporteMovimientosDetalleIE250_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovimientosDetalleIE(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovimientosDetalleIE",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovimientosDetalleIE/""",
      """""",
      Seq()
    )
  )

  // @LINE:403
  private lazy val controllers_MnuReportes_reporteMovimientosIEExcel251_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovimientosIEExcel/")))
  )
  private lazy val controllers_MnuReportes_reporteMovimientosIEExcel251_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteMovimientosIEExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteMovimientosIEExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovimientosIEExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:405
  private lazy val controllers_MnuReportes_reporteExcedentesListaProyectos252_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteExcedentesListaProyectos/")))
  )
  private lazy val controllers_MnuReportes_reporteExcedentesListaProyectos252_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteExcedentesListaProyectos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteExcedentesListaProyectos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteExcedentesListaProyectos/""",
      """""",
      Seq()
    )
  )

  // @LINE:406
  private lazy val controllers_MnuReportes_reporteExcedentesDetalle253_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteExcedentesDetalle/")))
  )
  private lazy val controllers_MnuReportes_reporteExcedentesDetalle253_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteExcedentesDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteExcedentesDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteExcedentesDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:407
  private lazy val controllers_MnuReportes_reporteExcedentesDetalleExcel254_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteExcedentesDetalleExcel/")))
  )
  private lazy val controllers_MnuReportes_reporteExcedentesDetalleExcel254_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteExcedentesDetalleExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteExcedentesDetalleExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteExcedentesDetalleExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:409
  private lazy val controllers_MnuReportes_reporteEstadosTodos255_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEstadosTodos/")))
  )
  private lazy val controllers_MnuReportes_reporteEstadosTodos255_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteEstadosTodos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteEstadosTodos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteEstadosTodos/""",
      """""",
      Seq()
    )
  )

  // @LINE:410
  private lazy val controllers_MnuReportes_reporteEstadosTodosDetalle256_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEstadosTodosDetalle/")))
  )
  private lazy val controllers_MnuReportes_reporteEstadosTodosDetalle256_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteEstadosTodosDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteEstadosTodosDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteEstadosTodosDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:411
  private lazy val controllers_MnuReportes_reporteEstadosTodosDetalleExcel257_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEstadosTodosDetalleExcel/")))
  )
  private lazy val controllers_MnuReportes_reporteEstadosTodosDetalleExcel257_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteEstadosTodosDetalleExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteEstadosTodosDetalleExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteEstadosTodosDetalleExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:413
  private lazy val controllers_MnuReportes_reporteEstadosPeriodo0258_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEstadosPeriodo0/")))
  )
  private lazy val controllers_MnuReportes_reporteEstadosPeriodo0258_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteEstadosPeriodo0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteEstadosPeriodo0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteEstadosPeriodo0/""",
      """""",
      Seq()
    )
  )

  // @LINE:414
  private lazy val controllers_MnuReportes_reporteEstadosPeriodo1259_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEstadosPeriodo1/")))
  )
  private lazy val controllers_MnuReportes_reporteEstadosPeriodo1259_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteEstadosPeriodo1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteEstadosPeriodo1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteEstadosPeriodo1/""",
      """""",
      Seq()
    )
  )

  // @LINE:415
  private lazy val controllers_MnuReportes_reporteEstadosPeriodo1Excel260_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEstadosPeriodo1Excel/")))
  )
  private lazy val controllers_MnuReportes_reporteEstadosPeriodo1Excel260_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteEstadosPeriodo1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteEstadosPeriodo1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteEstadosPeriodo1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:418
  private lazy val controllers_MnuReportes_reporteEstadosAll0261_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEstadosAll0/")))
  )
  private lazy val controllers_MnuReportes_reporteEstadosAll0261_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteEstadosAll0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteEstadosAll0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteEstadosAll0/""",
      """""",
      Seq()
    )
  )

  // @LINE:419
  private lazy val controllers_MnuReportes_reporteEstadosAll1262_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEstadosAll1/")))
  )
  private lazy val controllers_MnuReportes_reporteEstadosAll1262_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteEstadosAll1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteEstadosAll1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteEstadosAll1/""",
      """""",
      Seq()
    )
  )

  // @LINE:420
  private lazy val controllers_MnuReportes_reporteEstadosAll1Excel263_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEstadosAll1Excel/")))
  )
  private lazy val controllers_MnuReportes_reporteEstadosAll1Excel263_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteEstadosAll1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteEstadosAll1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteEstadosAll1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:423
  private lazy val controllers_MnuReportes_reportTrazaEquipo264_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportTrazaEquipo/")))
  )
  private lazy val controllers_MnuReportes_reportTrazaEquipo264_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportTrazaEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportTrazaEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportTrazaEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:424
  private lazy val controllers_MnuReportes_reportTrazaEquipo2265_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportTrazaEquipo2/")))
  )
  private lazy val controllers_MnuReportes_reportTrazaEquipo2265_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportTrazaEquipo2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportTrazaEquipo2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportTrazaEquipo2/""",
      """""",
      Seq()
    )
  )

  // @LINE:425
  private lazy val controllers_MnuReportes_reportTrazaEquipo2Excel266_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportTrazaEquipo2Excel/")))
  )
  private lazy val controllers_MnuReportes_reportTrazaEquipo2Excel266_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportTrazaEquipo2Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportTrazaEquipo2Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportTrazaEquipo2Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:427
  private lazy val controllers_MnuReportes_reporteEjecutivo1267_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEjecutivo1/")))
  )
  private lazy val controllers_MnuReportes_reporteEjecutivo1267_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteEjecutivo1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteEjecutivo1",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteEjecutivo1/""",
      """""",
      Seq()
    )
  )

  // @LINE:428
  private lazy val controllers_MnuReportes_graficoInversionExcel268_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("graficoInversionExcel/")))
  )
  private lazy val controllers_MnuReportes_graficoInversionExcel268_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.graficoInversionExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "graficoInversionExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """graficoInversionExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:429
  private lazy val controllers_MnuReportes_graficoOcupacionExcel269_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("graficoOcupacionExcel/")))
  )
  private lazy val controllers_MnuReportes_graficoOcupacionExcel269_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.graficoOcupacionExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "graficoOcupacionExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """graficoOcupacionExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:430
  private lazy val controllers_MnuReportes_graficoValorizadoGrupoExcel270_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("graficoValorizadoGrupoExcel/")))
  )
  private lazy val controllers_MnuReportes_graficoValorizadoGrupoExcel270_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.graficoValorizadoGrupoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "graficoValorizadoGrupoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """graficoValorizadoGrupoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:431
  private lazy val controllers_MnuReportes_graficoUnidadesExcel271_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("graficoUnidadesExcel/")))
  )
  private lazy val controllers_MnuReportes_graficoUnidadesExcel271_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.graficoUnidadesExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "graficoUnidadesExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """graficoUnidadesExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:432
  private lazy val controllers_MnuReportes_graficoValorizadoBodegaExcel272_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("graficoValorizadoBodegaExcel/")))
  )
  private lazy val controllers_MnuReportes_graficoValorizadoBodegaExcel272_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.graficoValorizadoBodegaExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "graficoValorizadoBodegaExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """graficoValorizadoBodegaExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:433
  private lazy val controllers_MnuReportes_graficoPotencialExcel273_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("graficoPotencialExcel/")))
  )
  private lazy val controllers_MnuReportes_graficoPotencialExcel273_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.graficoPotencialExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "graficoPotencialExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """graficoPotencialExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:435
  private lazy val controllers_MnuReportes_reporteGerencial274_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteGerencial/")))
  )
  private lazy val controllers_MnuReportes_reporteGerencial274_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteGerencial(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteGerencial",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteGerencial/""",
      """""",
      Seq()
    )
  )

  // @LINE:436
  private lazy val controllers_MnuReportes_reporteGerencialGrupo275_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteGerencialGrupo/")))
  )
  private lazy val controllers_MnuReportes_reporteGerencialGrupo275_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteGerencialGrupo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteGerencialGrupo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteGerencialGrupo/""",
      """""",
      Seq()
    )
  )

  // @LINE:437
  private lazy val controllers_MnuReportes_reporteGerencialGrupoDetalle276_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteGerencialGrupoDetalle/")))
  )
  private lazy val controllers_MnuReportes_reporteGerencialGrupoDetalle276_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteGerencialGrupoDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteGerencialGrupoDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteGerencialGrupoDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:439
  private lazy val controllers_MnuReportes_reporteGerencialKG277_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteGerencialKG/")))
  )
  private lazy val controllers_MnuReportes_reporteGerencialKG277_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteGerencialKG(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteGerencialKG",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteGerencialKG/""",
      """""",
      Seq()
    )
  )

  // @LINE:440
  private lazy val controllers_MnuReportes_reporteGerencialKGExcel278_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteGerencialKGExcel/")))
  )
  private lazy val controllers_MnuReportes_reporteGerencialKGExcel278_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteGerencialKGExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteGerencialKGExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteGerencialKGExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:442
  private lazy val controllers_MnuReportes_reporteGerencialVentas279_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteGerencialVentas/")))
  )
  private lazy val controllers_MnuReportes_reporteGerencialVentas279_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reporteGerencialVentas(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reporteGerencialVentas",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteGerencialVentas/""",
      """""",
      Seq()
    )
  )

  // @LINE:444
  private lazy val controllers_MnuReportes_reportGraficoMovResumen280_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportGraficoMovResumen/"), DynamicPart("listG", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("vista", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_reportGraficoMovResumen280_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportGraficoMovResumen(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportGraficoMovResumen",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "GET",
      this.prefix + """reportGraficoMovResumen/""" + "$" + """listG<[^/]+>,""" + "$" + """vista<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:445
  private lazy val controllers_MnuReportes_refreshGraficoMovResumen281_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("refreshGraficoMovResumen/")))
  )
  private lazy val controllers_MnuReportes_refreshGraficoMovResumen281_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.refreshGraficoMovResumen(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "refreshGraficoMovResumen",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """refreshGraficoMovResumen/""",
      """""",
      Seq()
    )
  )

  // @LINE:446
  private lazy val controllers_MnuReportes_reportGraficoMovPorGrupo282_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportGraficoMovPorGrupo/"), DynamicPart("lG", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("lB", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("vista", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("idT", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_reportGraficoMovPorGrupo282_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportGraficoMovPorGrupo(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportGraficoMovPorGrupo",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String], classOf[String], classOf[String]),
      "GET",
      this.prefix + """reportGraficoMovPorGrupo/""" + "$" + """lG<[^/]+>,""" + "$" + """lB<[^/]+>,""" + "$" + """vista<[^/]+>,""" + "$" + """idT<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:447
  private lazy val controllers_MnuReportes_refreshGraficoMovPorGrupo283_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("refreshGraficoMovPorGrupo/"), DynamicPart("idTipoBodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_refreshGraficoMovPorGrupo283_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.refreshGraficoMovPorGrupo(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "refreshGraficoMovPorGrupo",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "POST",
      this.prefix + """refreshGraficoMovPorGrupo/""" + "$" + """idTipoBodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:448
  private lazy val controllers_MnuReportes_reportGraficoMovUso284_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportGraficoMovUso/")))
  )
  private lazy val controllers_MnuReportes_reportGraficoMovUso284_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportGraficoMovUso(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportGraficoMovUso",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportGraficoMovUso/""",
      """""",
      Seq()
    )
  )

  // @LINE:449
  private lazy val controllers_MnuReportes_reportGraficoMovUsoPropiedad285_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportGraficoMovUsoPropiedad/")))
  )
  private lazy val controllers_MnuReportes_reportGraficoMovUsoPropiedad285_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportGraficoMovUsoPropiedad(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportGraficoMovUsoPropiedad",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportGraficoMovUsoPropiedad/""",
      """""",
      Seq()
    )
  )

  // @LINE:451
  private lazy val controllers_MnuReportes_reportFacturaPeriodo286_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaPeriodo/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaPeriodo286_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportFacturaPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:452
  private lazy val controllers_MnuReportes_reportFacturaProyecto287_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyecto/"), DynamicPart("archivoPDF", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyecto287_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyecto(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyecto",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "POST",
      this.prefix + """reportFacturaProyecto/""" + "$" + """archivoPDF<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:453
  private lazy val controllers_MnuReportes_reportFacturaProyectoGet288_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoGet/"), DynamicPart("p", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("d", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("a", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("uf", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("us", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("eu", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoGet288_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoGet(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[Double], fakeValue[Double], fakeValue[Double]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoGet",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String], classOf[String], classOf[Double], classOf[Double], classOf[Double]),
      "GET",
      this.prefix + """reportFacturaProyectoGet/""" + "$" + """p<[^/]+>,""" + "$" + """d<[^/]+>,""" + "$" + """a<[^/]+>,""" + "$" + """uf<[^/]+>,""" + "$" + """us<[^/]+>,""" + "$" + """eu<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:454
  private lazy val controllers_MnuReportes_reportFacturaProyectoExcel289_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoExcel/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoExcel289_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:455
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalle290_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalle/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalle290_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:456
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetExcel291_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetExcel/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetExcel291_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:457
  private lazy val controllers_MnuReportes_generarProformaPdfXlsxXmlJson292_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generarProformaPdfXlsxXmlJson/")))
  )
  private lazy val controllers_MnuReportes_generarProformaPdfXlsxXmlJson292_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.generarProformaPdfXlsxXmlJson(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "generarProformaPdfXlsxXmlJson",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generarProformaPdfXlsxXmlJson/""",
      """""",
      Seq()
    )
  )

  // @LINE:459
  private lazy val controllers_MnuReportes_reportFacturaPeriodoH293_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaPeriodoH/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaPeriodoH293_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaPeriodoH(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaPeriodoH",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportFacturaPeriodoH/""",
      """""",
      Seq()
    )
  )

  // @LINE:460
  private lazy val controllers_MnuReportes_reportFacturaProyectoH294_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoH/"), DynamicPart("archivoPDF", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoH294_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoH(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoH",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "POST",
      this.prefix + """reportFacturaProyectoH/""" + "$" + """archivoPDF<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:461
  private lazy val controllers_MnuReportes_reportFacturaProyectoHExcel295_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoHExcel/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoHExcel295_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoHExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoHExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoHExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:462
  private lazy val controllers_MnuReportes_reportFacturaProyectoHGet296_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoHGet/"), DynamicPart("p", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("d", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("a", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("uf", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("us", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("eu", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoHGet296_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoHGet(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[Double], fakeValue[Double], fakeValue[Double]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoHGet",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String], classOf[String], classOf[Double], classOf[Double], classOf[Double]),
      "GET",
      this.prefix + """reportFacturaProyectoHGet/""" + "$" + """p<[^/]+>,""" + "$" + """d<[^/]+>,""" + "$" + """a<[^/]+>,""" + "$" + """uf<[^/]+>,""" + "$" + """us<[^/]+>,""" + "$" + """eu<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:465
  private lazy val controllers_MnuReportes_proformaListaHPeriodo297_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaListaHPeriodo/")))
  )
  private lazy val controllers_MnuReportes_proformaListaHPeriodo297_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.proformaListaHPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "proformaListaHPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proformaListaHPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:466
  private lazy val controllers_MnuReportes_proformaListaH298_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaListaH/")))
  )
  private lazy val controllers_MnuReportes_proformaListaH298_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.proformaListaH(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "proformaListaH",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proformaListaH/""",
      """""",
      Seq()
    )
  )

  // @LINE:467
  private lazy val controllers_MnuReportes_proformaListaHGet299_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaListaHGet/"), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_proformaListaHGet299_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.proformaListaHGet(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "proformaListaHGet",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "GET",
      this.prefix + """proformaListaHGet/""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:468
  private lazy val controllers_MnuReportes_proformaEliminaH300_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaEliminaH/")))
  )
  private lazy val controllers_MnuReportes_proformaEliminaH300_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.proformaEliminaH(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "proformaEliminaH",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proformaEliminaH/""",
      """""",
      Seq()
    )
  )

  // @LINE:474
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleH301_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalleH/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleH301_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalleH(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalleH",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalleH/""",
      """""",
      Seq()
    )
  )

  // @LINE:475
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHExcel302_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalleHExcel/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHExcel302_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalleHExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalleHExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalleHExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:476
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHProforma303_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalleHProforma/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHProforma303_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalleHProforma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalleHProforma",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalleHProforma/""",
      """""",
      Seq()
    )
  )

  // @LINE:478
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHVta304_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalleHVta/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHVta304_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalleHVta(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalleHVta",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalleHVta/""",
      """""",
      Seq()
    )
  )

  // @LINE:479
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHVtaExcel305_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalleHVtaExcel/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHVtaExcel305_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalleHVtaExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalleHVtaExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalleHVtaExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:480
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHVtaProforma306_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalleHVtaProforma/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHVtaProforma306_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalleHVtaProforma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalleHVtaProforma",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalleHVtaProforma/""",
      """""",
      Seq()
    )
  )

  // @LINE:482
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHServ307_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalleHServ/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHServ307_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalleHServ(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalleHServ",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalleHServ/""",
      """""",
      Seq()
    )
  )

  // @LINE:483
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHServExcel308_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalleHServExcel/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHServExcel308_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalleHServExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalleHServExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalleHServExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:484
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHServProforma309_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalleHServProforma/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHServProforma309_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalleHServProforma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalleHServProforma",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalleHServProforma/""",
      """""",
      Seq()
    )
  )

  // @LINE:486
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHALL310_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalleHALL/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHALL310_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalleHALL(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalleHALL",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalleHALL/""",
      """""",
      Seq()
    )
  )

  // @LINE:487
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHAllExcel311_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalleHAllExcel/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHAllExcel311_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalleHAllExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalleHAllExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalleHAllExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:488
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHAllProforma312_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaProyectoDetalleHAllProforma/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaProyectoDetalleHAllProforma312_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaProyectoDetalleHAllProforma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaProyectoDetalleHAllProforma",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaProyectoDetalleHAllProforma/""",
      """""",
      Seq()
    )
  )

  // @LINE:492
  private lazy val controllers_MnuReportes_reportFacturaPeriodoResumen0313_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaPeriodoResumen0/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaPeriodoResumen0313_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaPeriodoResumen0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaPeriodoResumen0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportFacturaPeriodoResumen0/""",
      """""",
      Seq()
    )
  )

  // @LINE:493
  private lazy val controllers_MnuReportes_reportFacturaResumen0314_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaResumen0/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaResumen0314_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaResumen0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaResumen0",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaResumen0/""",
      """""",
      Seq()
    )
  )

  // @LINE:494
  private lazy val controllers_MnuReportes_reportFacturaPeriodoResumen315_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaPeriodoResumen/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaPeriodoResumen315_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaPeriodoResumen(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaPeriodoResumen",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportFacturaPeriodoResumen/""",
      """""",
      Seq()
    )
  )

  // @LINE:495
  private lazy val controllers_MnuReportes_reportFacturaResumen316_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaResumen/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaResumen316_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaResumen(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaResumen",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaResumen/""",
      """""",
      Seq()
    )
  )

  // @LINE:496
  private lazy val controllers_MnuReportes_reportFacturaPeriodoResumen2317_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaPeriodoResumen2/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaPeriodoResumen2317_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaPeriodoResumen2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaPeriodoResumen2",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportFacturaPeriodoResumen2/""",
      """""",
      Seq()
    )
  )

  // @LINE:497
  private lazy val controllers_MnuReportes_reportFacturaResumen2318_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaResumen2/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaResumen2318_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaResumen2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaResumen2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaResumen2/""",
      """""",
      Seq()
    )
  )

  // @LINE:499
  private lazy val controllers_MnuReportes_reportFacturaResumenJson319_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaResumenJson/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaResumenJson319_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaResumenJson(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaResumenJson",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaResumenJson/""",
      """""",
      Seq()
    )
  )

  // @LINE:500
  private lazy val controllers_MnuReportes_reportFacturaResumenExcel0320_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaResumenExcel0/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaResumenExcel0320_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaResumenExcel0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaResumenExcel0",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaResumenExcel0/""",
      """""",
      Seq()
    )
  )

  // @LINE:501
  private lazy val controllers_MnuReportes_reportFacturaResumenExcel321_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaResumenExcel/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaResumenExcel321_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaResumenExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaResumenExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaResumenExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:502
  private lazy val controllers_MnuReportes_reportFacturaResumenExcel2322_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaResumenExcel2/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaResumenExcel2322_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaResumenExcel2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaResumenExcel2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaResumenExcel2/""",
      """""",
      Seq()
    )
  )

  // @LINE:504
  private lazy val controllers_MnuReportes_reportFacturaConsolidado323_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaConsolidado/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaConsolidado323_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaConsolidado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaConsolidado",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportFacturaConsolidado/""",
      """""",
      Seq()
    )
  )

  // @LINE:505
  private lazy val controllers_MnuReportes_reportFacturaConsolidadoRtp324_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaConsolidadoRtp/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaConsolidadoRtp324_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaConsolidadoRtp(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaConsolidadoRtp",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaConsolidadoRtp/""",
      """""",
      Seq()
    )
  )

  // @LINE:506
  private lazy val controllers_MnuReportes_reportFacturaConsolidadoRtpExcel325_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFacturaConsolidadoRtpExcel/")))
  )
  private lazy val controllers_MnuReportes_reportFacturaConsolidadoRtpExcel325_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFacturaConsolidadoRtpExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFacturaConsolidadoRtpExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFacturaConsolidadoRtpExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:508
  private lazy val controllers_MnuReportes_reportFactConsolconGrupo326_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFactConsolconGrupo/")))
  )
  private lazy val controllers_MnuReportes_reportFactConsolconGrupo326_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFactConsolconGrupo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFactConsolconGrupo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportFactConsolconGrupo/""",
      """""",
      Seq()
    )
  )

  // @LINE:509
  private lazy val controllers_MnuReportes_reportFactConsolconGrupoRtp327_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFactConsolconGrupoRtp/")))
  )
  private lazy val controllers_MnuReportes_reportFactConsolconGrupoRtp327_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFactConsolconGrupoRtp(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFactConsolconGrupoRtp",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFactConsolconGrupoRtp/""",
      """""",
      Seq()
    )
  )

  // @LINE:510
  private lazy val controllers_MnuReportes_reportFactConsolconGrupoRtpExcel328_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFactConsolconGrupoRtpExcel/")))
  )
  private lazy val controllers_MnuReportes_reportFactConsolconGrupoRtpExcel328_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFactConsolconGrupoRtpExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFactConsolconGrupoRtpExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFactConsolconGrupoRtpExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:512
  private lazy val controllers_MnuReportes_reportFactConsolconEquipos329_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFactConsolconEquipos/")))
  )
  private lazy val controllers_MnuReportes_reportFactConsolconEquipos329_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFactConsolconEquipos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFactConsolconEquipos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportFactConsolconEquipos/""",
      """""",
      Seq()
    )
  )

  // @LINE:513
  private lazy val controllers_MnuReportes_reportFactConsolconEquiposRtp330_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFactConsolconEquiposRtp/")))
  )
  private lazy val controllers_MnuReportes_reportFactConsolconEquiposRtp330_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFactConsolconEquiposRtp(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFactConsolconEquiposRtp",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFactConsolconEquiposRtp/""",
      """""",
      Seq()
    )
  )

  // @LINE:514
  private lazy val controllers_MnuReportes_reportFactConsolconEquiposRtpExcel331_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportFactConsolconEquiposRtpExcel/")))
  )
  private lazy val controllers_MnuReportes_reportFactConsolconEquiposRtpExcel331_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.reportFactConsolconEquiposRtpExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "reportFactConsolconEquiposRtpExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportFactConsolconEquiposRtpExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:518
  private lazy val controllers_MnuReportes_proformaListaPeriodo332_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaListaPeriodo/")))
  )
  private lazy val controllers_MnuReportes_proformaListaPeriodo332_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.proformaListaPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "proformaListaPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proformaListaPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:519
  private lazy val controllers_MnuReportes_proformaLista333_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaLista/")))
  )
  private lazy val controllers_MnuReportes_proformaLista333_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.proformaLista(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "proformaLista",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proformaLista/""",
      """""",
      Seq()
    )
  )

  // @LINE:520
  private lazy val controllers_MnuReportes_proformaListaGet334_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaListaGet/"), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_proformaListaGet334_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.proformaListaGet(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "proformaListaGet",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "GET",
      this.prefix + """proformaListaGet/""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:521
  private lazy val controllers_MnuReportes_proformaListaExcel335_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaListaExcel/")))
  )
  private lazy val controllers_MnuReportes_proformaListaExcel335_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.proformaListaExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "proformaListaExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proformaListaExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:522
  private lazy val controllers_MnuReportes_generaProformaXml336_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaProformaXml/")))
  )
  private lazy val controllers_MnuReportes_generaProformaXml336_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.generaProformaXml(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "generaProformaXml",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaProformaXml/""",
      """""",
      Seq()
    )
  )

  // @LINE:523
  private lazy val controllers_MnuReportes_sendXMLFacura337_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sendXMLFacura/"), DynamicPart("id_proforma", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_sendXMLFacura337_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.sendXMLFacura(fakeValue[play.mvc.Http.Request], fakeValue[Long], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "sendXMLFacura",
      Seq(classOf[play.mvc.Http.Request], classOf[Long], classOf[String], classOf[String]),
      "POST",
      this.prefix + """sendXMLFacura/""" + "$" + """id_proforma<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:525
  private lazy val controllers_MnuReportes_generaProformaApiManager338_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaProformaApiManager/")))
  )
  private lazy val controllers_MnuReportes_generaProformaApiManager338_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.generaProformaApiManager(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "generaProformaApiManager",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaProformaApiManager/""",
      """""",
      Seq()
    )
  )

  // @LINE:526
  private lazy val controllers_MnuReportes_generaProformaApiNubox339_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaProformaApiNubox/")))
  )
  private lazy val controllers_MnuReportes_generaProformaApiNubox339_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.generaProformaApiNubox(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "generaProformaApiNubox",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaProformaApiNubox/""",
      """""",
      Seq()
    )
  )

  // @LINE:527
  private lazy val controllers_MnuReportes_generaProformaApiSapConconcreto340_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaProformaApiSapConconcreto/")))
  )
  private lazy val controllers_MnuReportes_generaProformaApiSapConconcreto340_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.generaProformaApiSapConconcreto(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "generaProformaApiSapConconcreto",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaProformaApiSapConconcreto/""",
      """""",
      Seq()
    )
  )

  // @LINE:528
  private lazy val controllers_MnuReportes_generaProformaWebMaximise341_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaProformaWebMaximise/")))
  )
  private lazy val controllers_MnuReportes_generaProformaWebMaximise341_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.generaProformaWebMaximise(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "generaProformaWebMaximise",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaProformaWebMaximise/""",
      """""",
      Seq()
    )
  )

  // @LINE:530
  private lazy val controllers_MnuReportes_downFacturaMaximise342_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("downFacturaMaximise/"), DynamicPart("nroInte", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_downFacturaMaximise342_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.downFacturaMaximise(fakeValue[Long], fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "downFacturaMaximise",
      Seq(classOf[Long], classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "GET",
      this.prefix + """downFacturaMaximise/""" + "$" + """nroInte<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:533
  private lazy val controllers_MnuReportes_generaFacturaIConstruye343_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaFacturaIConstruye/")))
  )
  private lazy val controllers_MnuReportes_generaFacturaIConstruye343_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.generaFacturaIConstruye(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "generaFacturaIConstruye",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaFacturaIConstruye/""",
      """""",
      Seq()
    )
  )

  // @LINE:534
  private lazy val controllers_MnuReportes_downFacturaIconstruye344_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("downFacturaIconstruye/")))
  )
  private lazy val controllers_MnuReportes_downFacturaIconstruye344_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.downFacturaIconstruye(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "downFacturaIconstruye",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """downFacturaIconstruye/""",
      """""",
      Seq()
    )
  )

  // @LINE:536
  private lazy val controllers_MnuReportes_generaProformaApiSapSchwager345_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaProformaApiSapSchwager/")))
  )
  private lazy val controllers_MnuReportes_generaProformaApiSapSchwager345_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.generaProformaApiSapSchwager(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "generaProformaApiSapSchwager",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaProformaApiSapSchwager/""",
      """""",
      Seq()
    )
  )

  // @LINE:537
  private lazy val controllers_MnuReportes_generaProformaApiRelBase346_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaProformaApiRelBase/")))
  )
  private lazy val controllers_MnuReportes_generaProformaApiRelBase346_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.generaProformaApiRelBase(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "generaProformaApiRelBase",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaProformaApiRelBase/""",
      """""",
      Seq()
    )
  )

  // @LINE:538
  private lazy val controllers_MnuReportes_generaProformaApiRelBase2347_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaProformaApiRelBase2/")))
  )
  private lazy val controllers_MnuReportes_generaProformaApiRelBase2347_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.generaProformaApiRelBase2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "generaProformaApiRelBase2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaProformaApiRelBase2/""",
      """""",
      Seq()
    )
  )

  // @LINE:539
  private lazy val controllers_MnuReportes_validarClienteAjax348_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("validarClienteAjax/")))
  )
  private lazy val controllers_MnuReportes_validarClienteAjax348_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.validarClienteAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "validarClienteAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """validarClienteAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:543
  private lazy val controllers_MnuReportes_proformaElimina349_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaElimina/")))
  )
  private lazy val controllers_MnuReportes_proformaElimina349_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.proformaElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "proformaElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proformaElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:545
  private lazy val controllers_MnuReportes_ajustesEp350_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEp/")))
  )
  private lazy val controllers_MnuReportes_ajustesEp350_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.ajustesEp(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "ajustesEp",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """ajustesEp/""",
      """""",
      Seq()
    )
  )

  // @LINE:546
  private lazy val controllers_MnuReportes_ajustesEpListado351_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpListado/")))
  )
  private lazy val controllers_MnuReportes_ajustesEpListado351_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.ajustesEpListado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "ajustesEpListado",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpListado/""",
      """""",
      Seq()
    )
  )

  // @LINE:547
  private lazy val controllers_MnuReportes_ajusteGrabaPDF352_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajusteGrabaPDF/")))
  )
  private lazy val controllers_MnuReportes_ajusteGrabaPDF352_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.ajusteGrabaPDF(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "ajusteGrabaPDF",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajusteGrabaPDF/""",
      """""",
      Seq()
    )
  )

  // @LINE:548
  private lazy val controllers_MnuReportes_ajustesEpModificar353_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpModificar/")))
  )
  private lazy val controllers_MnuReportes_ajustesEpModificar353_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.ajustesEpModificar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "ajustesEpModificar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpModificar/""",
      """""",
      Seq()
    )
  )

  // @LINE:549
  private lazy val controllers_MnuReportes_ajustesEpUpdate354_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpUpdate/")))
  )
  private lazy val controllers_MnuReportes_ajustesEpUpdate354_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.ajustesEpUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "ajustesEpUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:550
  private lazy val controllers_MnuReportes_ajustesEpEliminar355_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpEliminar/")))
  )
  private lazy val controllers_MnuReportes_ajustesEpEliminar355_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.ajustesEpEliminar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "ajustesEpEliminar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpEliminar/""",
      """""",
      Seq()
    )
  )

  // @LINE:551
  private lazy val controllers_MnuReportes_ajustesEpNuevo356_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpNuevo/")))
  )
  private lazy val controllers_MnuReportes_ajustesEpNuevo356_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.ajustesEpNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "ajustesEpNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:552
  private lazy val controllers_MnuReportes_ajustesEpGrabar357_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpGrabar/")))
  )
  private lazy val controllers_MnuReportes_ajustesEpGrabar357_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.ajustesEpGrabar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "ajustesEpGrabar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpGrabar/""",
      """""",
      Seq()
    )
  )

  // @LINE:554
  private lazy val controllers_MnuReportes_ajustesEpRpt358_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpRpt/")))
  )
  private lazy val controllers_MnuReportes_ajustesEpRpt358_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.ajustesEpRpt(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "ajustesEpRpt",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """ajustesEpRpt/""",
      """""",
      Seq()
    )
  )

  // @LINE:555
  private lazy val controllers_MnuReportes_ajustesEpRptDetalle359_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesEpRptDetalle/")))
  )
  private lazy val controllers_MnuReportes_ajustesEpRptDetalle359_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.ajustesEpRptDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "ajustesEpRptDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesEpRptDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:557
  private lazy val controllers_MnuReportes_ajustesPeriodoEpRpt1360_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesPeriodoEpRpt1/")))
  )
  private lazy val controllers_MnuReportes_ajustesPeriodoEpRpt1360_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.ajustesPeriodoEpRpt1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "ajustesPeriodoEpRpt1",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """ajustesPeriodoEpRpt1/""",
      """""",
      Seq()
    )
  )

  // @LINE:558
  private lazy val controllers_MnuReportes_ajustesPeriodoEpRpt2361_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("ajustesPeriodoEpRpt2/")))
  )
  private lazy val controllers_MnuReportes_ajustesPeriodoEpRpt2361_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.ajustesPeriodoEpRpt2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "ajustesPeriodoEpRpt2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """ajustesPeriodoEpRpt2/""",
      """""",
      Seq()
    )
  )

  // @LINE:561
  private lazy val controllers_MnuReportes_hoheReportTodo0362_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheReportTodo0/")))
  )
  private lazy val controllers_MnuReportes_hoheReportTodo0362_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheReportTodo0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheReportTodo0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hoheReportTodo0/""",
      """ SOLO HOHE:""",
      Seq()
    )
  )

  // @LINE:562
  private lazy val controllers_MnuReportes_hoheReportTodo363_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheReportTodo/")))
  )
  private lazy val controllers_MnuReportes_hoheReportTodo363_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheReportTodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheReportTodo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hoheReportTodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:563
  private lazy val controllers_MnuReportes_hoheTodoResumen0364_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheTodoResumen0/")))
  )
  private lazy val controllers_MnuReportes_hoheTodoResumen0364_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheTodoResumen0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheTodoResumen0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hoheTodoResumen0/""",
      """""",
      Seq()
    )
  )

  // @LINE:564
  private lazy val controllers_MnuReportes_hoheTodoResumen365_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheTodoResumen/")))
  )
  private lazy val controllers_MnuReportes_hoheTodoResumen365_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheTodoResumen(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheTodoResumen",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hoheTodoResumen/""",
      """""",
      Seq()
    )
  )

  // @LINE:566
  private lazy val controllers_MnuReportes_hoheMatrizVerticalInventario0366_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheMatrizVerticalInventario0/")))
  )
  private lazy val controllers_MnuReportes_hoheMatrizVerticalInventario0366_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheMatrizVerticalInventario0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheMatrizVerticalInventario0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hoheMatrizVerticalInventario0/""",
      """""",
      Seq()
    )
  )

  // @LINE:567
  private lazy val controllers_MnuReportes_hoheMatrizVerticalInventario367_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheMatrizVerticalInventario/")))
  )
  private lazy val controllers_MnuReportes_hoheMatrizVerticalInventario367_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheMatrizVerticalInventario(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheMatrizVerticalInventario",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hoheMatrizVerticalInventario/""",
      """""",
      Seq()
    )
  )

  // @LINE:569
  private lazy val controllers_MnuReportes_hoheMatrizVerticalInv0Coti368_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheMatrizVerticalInv0Coti/")))
  )
  private lazy val controllers_MnuReportes_hoheMatrizVerticalInv0Coti368_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheMatrizVerticalInv0Coti(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheMatrizVerticalInv0Coti",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hoheMatrizVerticalInv0Coti/""",
      """""",
      Seq()
    )
  )

  // @LINE:570
  private lazy val controllers_MnuReportes_hoheMatrizVerticalInvCoti369_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheMatrizVerticalInvCoti/")))
  )
  private lazy val controllers_MnuReportes_hoheMatrizVerticalInvCoti369_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheMatrizVerticalInvCoti(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheMatrizVerticalInvCoti",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hoheMatrizVerticalInvCoti/""",
      """""",
      Seq()
    )
  )

  // @LINE:572
  private lazy val controllers_MnuReportes_hoheEstadoCotiOt370_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheEstadoCotiOt/")))
  )
  private lazy val controllers_MnuReportes_hoheEstadoCotiOt370_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheEstadoCotiOt(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheEstadoCotiOt",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hoheEstadoCotiOt/""",
      """""",
      Seq()
    )
  )

  // @LINE:573
  private lazy val controllers_MnuReportes_hoheEstadoCotiOtExcel371_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheEstadoCotiOtExcel/")))
  )
  private lazy val controllers_MnuReportes_hoheEstadoCotiOtExcel371_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheEstadoCotiOtExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheEstadoCotiOtExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hoheEstadoCotiOtExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:575
  private lazy val controllers_MnuReportes_hoheEstadoCotiSinOt372_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheEstadoCotiSinOt/")))
  )
  private lazy val controllers_MnuReportes_hoheEstadoCotiSinOt372_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheEstadoCotiSinOt(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheEstadoCotiSinOt",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hoheEstadoCotiSinOt/""",
      """""",
      Seq()
    )
  )

  // @LINE:576
  private lazy val controllers_MnuReportes_hoheEstadoCotiSinOtExcel373_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheEstadoCotiSinOtExcel/")))
  )
  private lazy val controllers_MnuReportes_hoheEstadoCotiSinOtExcel373_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheEstadoCotiSinOtExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheEstadoCotiSinOtExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hoheEstadoCotiSinOtExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:578
  private lazy val controllers_MnuReportes_hoheValySubePlantillaNV374_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hoheValySubePlantillaNV/")))
  )
  private lazy val controllers_MnuReportes_hoheValySubePlantillaNV374_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hoheValySubePlantillaNV(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hoheValySubePlantillaNV",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hoheValySubePlantillaNV/""",
      """""",
      Seq()
    )
  )

  // @LINE:579
  private lazy val controllers_MnuReportes_hohePlantillaNV375_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hohePlantillaNV/")))
  )
  private lazy val controllers_MnuReportes_hohePlantillaNV375_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_7.hohePlantillaNV(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuReportes",
      "hohePlantillaNV",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hohePlantillaNV/""",
      """""",
      Seq()
    )
  )

  // @LINE:584
  private lazy val controllers_MnuDisponibilidad_disponibleActualizaAll376_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("disponibleActualizaAll/")))
  )
  private lazy val controllers_MnuDisponibilidad_disponibleActualizaAll376_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuDisponibilidad_8.disponibleActualizaAll(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuDisponibilidad",
      "disponibleActualizaAll",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """disponibleActualizaAll/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU DISPONIBILIDAD
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:585
  private lazy val controllers_MnuDisponibilidad_cambiaFechaDisponibleAjax377_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cambiaFechaDisponibleAjax/")))
  )
  private lazy val controllers_MnuDisponibilidad_cambiaFechaDisponibleAjax377_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuDisponibilidad_8.cambiaFechaDisponibleAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuDisponibilidad",
      "cambiaFechaDisponibleAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cambiaFechaDisponibleAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:586
  private lazy val controllers_MnuDisponibilidad_reportDisponibilidadAll378_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportDisponibilidadAll/")))
  )
  private lazy val controllers_MnuDisponibilidad_reportDisponibilidadAll378_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuDisponibilidad_8.reportDisponibilidadAll(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuDisponibilidad",
      "reportDisponibilidadAll",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportDisponibilidadAll/""",
      """""",
      Seq()
    )
  )

  // @LINE:587
  private lazy val controllers_MnuDisponibilidad_reportDisponibilidadAllExcel379_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportDisponibilidadAllExcel/")))
  )
  private lazy val controllers_MnuDisponibilidad_reportDisponibilidadAllExcel379_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuDisponibilidad_8.reportDisponibilidadAllExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuDisponibilidad",
      "reportDisponibilidadAllExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportDisponibilidadAllExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:589
  private lazy val controllers_MnuDisponibilidad_disponibleInscribirEquipo380_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("disponibleInscribirEquipo/")))
  )
  private lazy val controllers_MnuDisponibilidad_disponibleInscribirEquipo380_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuDisponibilidad_8.disponibleInscribirEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuDisponibilidad",
      "disponibleInscribirEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """disponibleInscribirEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:590
  private lazy val controllers_MnuDisponibilidad_disponibleInscribirEquipo2381_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("disponibleInscribirEquipo2/")))
  )
  private lazy val controllers_MnuDisponibilidad_disponibleInscribirEquipo2381_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuDisponibilidad_8.disponibleInscribirEquipo2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuDisponibilidad",
      "disponibleInscribirEquipo2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """disponibleInscribirEquipo2/""",
      """""",
      Seq()
    )
  )

  // @LINE:591
  private lazy val controllers_MnuDisponibilidad_disponibleEliminarEquipo382_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("disponibleEliminarEquipo/")))
  )
  private lazy val controllers_MnuDisponibilidad_disponibleEliminarEquipo382_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuDisponibilidad_8.disponibleEliminarEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuDisponibilidad",
      "disponibleEliminarEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """disponibleEliminarEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:592
  private lazy val controllers_MnuDisponibilidad_disponibleEliminarEquipo2383_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("disponibleEliminarEquipo2/")))
  )
  private lazy val controllers_MnuDisponibilidad_disponibleEliminarEquipo2383_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuDisponibilidad_8.disponibleEliminarEquipo2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuDisponibilidad",
      "disponibleEliminarEquipo2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """disponibleEliminarEquipo2/""",
      """""",
      Seq()
    )
  )

  // @LINE:594
  private lazy val controllers_MnuDisponibilidad_disponibleInscribirBodega384_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("disponibleInscribirBodega/")))
  )
  private lazy val controllers_MnuDisponibilidad_disponibleInscribirBodega384_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuDisponibilidad_8.disponibleInscribirBodega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuDisponibilidad",
      "disponibleInscribirBodega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """disponibleInscribirBodega/""",
      """""",
      Seq()
    )
  )

  // @LINE:595
  private lazy val controllers_MnuDisponibilidad_disponibleInscribirBodega2385_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("disponibleInscribirBodega2/")))
  )
  private lazy val controllers_MnuDisponibilidad_disponibleInscribirBodega2385_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuDisponibilidad_8.disponibleInscribirBodega2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuDisponibilidad",
      "disponibleInscribirBodega2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """disponibleInscribirBodega2/""",
      """""",
      Seq()
    )
  )

  // @LINE:596
  private lazy val controllers_MnuDisponibilidad_disponibleEliminarBodega386_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("disponibleEliminarBodega/")))
  )
  private lazy val controllers_MnuDisponibilidad_disponibleEliminarBodega386_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuDisponibilidad_8.disponibleEliminarBodega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuDisponibilidad",
      "disponibleEliminarBodega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """disponibleEliminarBodega/""",
      """""",
      Seq()
    )
  )

  // @LINE:597
  private lazy val controllers_MnuDisponibilidad_disponibleEliminarBodega2387_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("disponibleEliminarBodega2/")))
  )
  private lazy val controllers_MnuDisponibilidad_disponibleEliminarBodega2387_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuDisponibilidad_8.disponibleEliminarBodega2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuDisponibilidad",
      "disponibleEliminarBodega2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """disponibleEliminarBodega2/""",
      """""",
      Seq()
    )
  )

  // @LINE:603
  private lazy val controllers_MnuQr_qrListaEquipos388_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrListaEquipos/")))
  )
  private lazy val controllers_MnuQr_qrListaEquipos388_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrListaEquipos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrListaEquipos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """qrListaEquipos/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU QR
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:604
  private lazy val controllers_MnuQr_qrSelectEquipos389_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrSelectEquipos/")))
  )
  private lazy val controllers_MnuQr_qrSelectEquipos389_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrSelectEquipos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrSelectEquipos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """qrSelectEquipos/""",
      """""",
      Seq()
    )
  )

  // @LINE:605
  private lazy val controllers_MnuQr_qrAgregaEquipo390_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrAgregaEquipo/")))
  )
  private lazy val controllers_MnuQr_qrAgregaEquipo390_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrAgregaEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrAgregaEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrAgregaEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:606
  private lazy val controllers_MnuQr_qrCambiaEstadoEquipos391_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrCambiaEstadoEquipos/")))
  )
  private lazy val controllers_MnuQr_qrCambiaEstadoEquipos391_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrCambiaEstadoEquipos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrCambiaEstadoEquipos",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrCambiaEstadoEquipos/""",
      """""",
      Seq()
    )
  )

  // @LINE:608
  private lazy val controllers_MnuQr_qrRevisarDatos392_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrRevisarDatos/")))
  )
  private lazy val controllers_MnuQr_qrRevisarDatos392_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrRevisarDatos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrRevisarDatos",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrRevisarDatos/""",
      """""",
      Seq()
    )
  )

  // @LINE:609
  private lazy val controllers_MnuQr_qrRevisarDatosAllVigentes393_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrRevisarDatosAllVigentes/")))
  )
  private lazy val controllers_MnuQr_qrRevisarDatosAllVigentes393_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrRevisarDatosAllVigentes(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrRevisarDatosAllVigentes",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """qrRevisarDatosAllVigentes/""",
      """""",
      Seq()
    )
  )

  // @LINE:610
  private lazy val controllers_MnuQr_qrRevisarDatosAllVigentesExcel394_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrRevisarDatosAllVigentesExcel/")))
  )
  private lazy val controllers_MnuQr_qrRevisarDatosAllVigentesExcel394_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrRevisarDatosAllVigentesExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrRevisarDatosAllVigentesExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrRevisarDatosAllVigentesExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:615
  private lazy val controllers_MnuQr_qrCambiarPubEquipo395_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrCambiarPubEquipo/")))
  )
  private lazy val controllers_MnuQr_qrCambiarPubEquipo395_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrCambiarPubEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrCambiarPubEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrCambiarPubEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:616
  private lazy val controllers_MnuQr_qrCambiarPubQr396_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrCambiarPubQr/")))
  )
  private lazy val controllers_MnuQr_qrCambiarPubQr396_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrCambiarPubQr(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrCambiarPubQr",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrCambiarPubQr/""",
      """""",
      Seq()
    )
  )

  // @LINE:617
  private lazy val controllers_MnuQr_qrEditEquipo397_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrEditEquipo/")))
  )
  private lazy val controllers_MnuQr_qrEditEquipo397_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrEditEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrEditEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrEditEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:618
  private lazy val controllers_MnuQr_qrAgregaCampoEquipo398_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrAgregaCampoEquipo/")))
  )
  private lazy val controllers_MnuQr_qrAgregaCampoEquipo398_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrAgregaCampoEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrAgregaCampoEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrAgregaCampoEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:619
  private lazy val controllers_MnuQr_qrActualizaPorCampo399_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrActualizaPorCampo/")))
  )
  private lazy val controllers_MnuQr_qrActualizaPorCampo399_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrActualizaPorCampo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrActualizaPorCampo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrActualizaPorCampo/""",
      """""",
      Seq()
    )
  )

  // @LINE:620
  private lazy val controllers_MnuQr_qrEliminaCampoEquipo400_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrEliminaCampoEquipo/")))
  )
  private lazy val controllers_MnuQr_qrEliminaCampoEquipo400_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrEliminaCampoEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrEliminaCampoEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrEliminaCampoEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:621
  private lazy val controllers_MnuQr_qrGrabaAnexoEquipo401_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrGrabaAnexoEquipo/")))
  )
  private lazy val controllers_MnuQr_qrGrabaAnexoEquipo401_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrGrabaAnexoEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrGrabaAnexoEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrGrabaAnexoEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:623
  private lazy val controllers_MnuQr_qrListaAtributoEquipos402_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrListaAtributoEquipos/")))
  )
  private lazy val controllers_MnuQr_qrListaAtributoEquipos402_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrListaAtributoEquipos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrListaAtributoEquipos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """qrListaAtributoEquipos/""",
      """""",
      Seq()
    )
  )

  // @LINE:624
  private lazy val controllers_MnuQr_qrEditarAtributoEquipo403_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrEditarAtributoEquipo/"), DynamicPart("idQrAtributoEquipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuQr_qrEditarAtributoEquipo403_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrEditarAtributoEquipo(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrEditarAtributoEquipo",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """qrEditarAtributoEquipo/""" + "$" + """idQrAtributoEquipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:625
  private lazy val controllers_MnuQr_qrEditarAtributoEquipo2404_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrEditarAtributoEquipo2/")))
  )
  private lazy val controllers_MnuQr_qrEditarAtributoEquipo2404_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrEditarAtributoEquipo2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrEditarAtributoEquipo2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrEditarAtributoEquipo2/""",
      """""",
      Seq()
    )
  )

  // @LINE:626
  private lazy val controllers_MnuQr_qrDeleteAtributoEquipo405_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrDeleteAtributoEquipo/"), DynamicPart("idQrAtributoEquipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuQr_qrDeleteAtributoEquipo405_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrDeleteAtributoEquipo(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrDeleteAtributoEquipo",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """qrDeleteAtributoEquipo/""" + "$" + """idQrAtributoEquipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:627
  private lazy val controllers_MnuQr_qrAgregaAtributoEquipo406_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrAgregaAtributoEquipo/")))
  )
  private lazy val controllers_MnuQr_qrAgregaAtributoEquipo406_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrAgregaAtributoEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrAgregaAtributoEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """qrAgregaAtributoEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:628
  private lazy val controllers_MnuQr_qrAgregaAtributoEquipo2407_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrAgregaAtributoEquipo2/")))
  )
  private lazy val controllers_MnuQr_qrAgregaAtributoEquipo2407_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrAgregaAtributoEquipo2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrAgregaAtributoEquipo2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrAgregaAtributoEquipo2/""",
      """""",
      Seq()
    )
  )

  // @LINE:630
  private lazy val controllers_MnuQr_qrPrintQrEquipos408_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrPrintQrEquipos/")))
  )
  private lazy val controllers_MnuQr_qrPrintQrEquipos408_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrPrintQrEquipos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrPrintQrEquipos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """qrPrintQrEquipos/""",
      """""",
      Seq()
    )
  )

  // @LINE:631
  private lazy val controllers_MnuQr_qrPrintQrEquiposWord409_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrPrintQrEquiposWord/")))
  )
  private lazy val controllers_MnuQr_qrPrintQrEquiposWord409_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrPrintQrEquiposWord(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrPrintQrEquiposWord",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrPrintQrEquiposWord/""",
      """""",
      Seq()
    )
  )

  // @LINE:635
  private lazy val controllers_MnuQr_leeUnQr410_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("leeUnQr/"), DynamicPart("strEncoded", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuQr_leeUnQr410_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.leeUnQr(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "leeUnQr",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """leeUnQr/""" + "$" + """strEncoded<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:636
  private lazy val controllers_MnuQr_viewImgQr411_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("viewImgQr/"), DynamicPart("base", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("img", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuQr_viewImgQr411_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.viewImgQr(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "viewImgQr",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "GET",
      this.prefix + """viewImgQr/""" + "$" + """base<[^/]+>,""" + "$" + """img<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:637
  private lazy val controllers_MnuQr_qrImagen412_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrImagen/")))
  )
  private lazy val controllers_MnuQr_qrImagen412_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrImagen(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrImagen",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrImagen/""",
      """""",
      Seq()
    )
  )

  // @LINE:638
  private lazy val controllers_MnuQr_qrAtributo413_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrAtributo/")))
  )
  private lazy val controllers_MnuQr_qrAtributo413_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrAtributo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrAtributo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrAtributo/""",
      """""",
      Seq()
    )
  )

  // @LINE:639
  private lazy val controllers_MnuQr_qrUbicacion414_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrUbicacion/")))
  )
  private lazy val controllers_MnuQr_qrUbicacion414_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrUbicacion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrUbicacion",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrUbicacion/""",
      """""",
      Seq()
    )
  )

  // @LINE:640
  private lazy val controllers_MnuQr_qrTrazabilidad415_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrTrazabilidad/")))
  )
  private lazy val controllers_MnuQr_qrTrazabilidad415_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrTrazabilidad(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrTrazabilidad",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrTrazabilidad/""",
      """""",
      Seq()
    )
  )

  // @LINE:641
  private lazy val controllers_MnuQr_qrHistoria416_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrHistoria/")))
  )
  private lazy val controllers_MnuQr_qrHistoria416_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrHistoria(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrHistoria",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrHistoria/""",
      """""",
      Seq()
    )
  )

  // @LINE:642
  private lazy val controllers_MnuQr_qrAtributosQr417_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrAtributosQr/")))
  )
  private lazy val controllers_MnuQr_qrAtributosQr417_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrAtributosQr(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrAtributosQr",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrAtributosQr/""",
      """""",
      Seq()
    )
  )

  // @LINE:643
  private lazy val controllers_MnuQr_qrImagenQr418_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("qrImagenQr/")))
  )
  private lazy val controllers_MnuQr_qrImagenQr418_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuQr_17.qrImagenQr(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuQr",
      "qrImagenQr",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """qrImagenQr/""",
      """""",
      Seq()
    )
  )

  // @LINE:649
  private lazy val controllers_MnuPpto_pptoAdministrar419_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pptoAdministrar/")))
  )
  private lazy val controllers_MnuPpto_pptoAdministrar419_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPpto_13.pptoAdministrar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPpto",
      "pptoAdministrar",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """pptoAdministrar/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU PPTO
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:650
  private lazy val controllers_MnuPpto_pptoEditar420_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pptoEditar/"), DynamicPart("id_bodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuPpto_pptoEditar420_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPpto_13.pptoEditar(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPpto",
      "pptoEditar",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """pptoEditar/""" + "$" + """id_bodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:651
  private lazy val controllers_MnuPpto_pptoAgregar421_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pptoAgregar/")))
  )
  private lazy val controllers_MnuPpto_pptoAgregar421_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPpto_13.pptoAgregar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPpto",
      "pptoAgregar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """pptoAgregar/""",
      """""",
      Seq()
    )
  )

  // @LINE:652
  private lazy val controllers_MnuPpto_pptoGrabar422_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pptoGrabar/")))
  )
  private lazy val controllers_MnuPpto_pptoGrabar422_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPpto_13.pptoGrabar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPpto",
      "pptoGrabar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """pptoGrabar/""",
      """""",
      Seq()
    )
  )

  // @LINE:653
  private lazy val controllers_MnuPpto_pptoModificar423_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pptoModificar/")))
  )
  private lazy val controllers_MnuPpto_pptoModificar423_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPpto_13.pptoModificar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPpto",
      "pptoModificar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """pptoModificar/""",
      """""",
      Seq()
    )
  )

  // @LINE:654
  private lazy val controllers_MnuPpto_pptoUpdate424_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pptoUpdate/")))
  )
  private lazy val controllers_MnuPpto_pptoUpdate424_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPpto_13.pptoUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPpto",
      "pptoUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """pptoUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:655
  private lazy val controllers_MnuPpto_pptoEliminar425_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pptoEliminar/")))
  )
  private lazy val controllers_MnuPpto_pptoEliminar425_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPpto_13.pptoEliminar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPpto",
      "pptoEliminar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """pptoEliminar/""",
      """""",
      Seq()
    )
  )

  // @LINE:656
  private lazy val controllers_MnuPpto_pptoListadoPptos426_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pptoListadoPptos/")))
  )
  private lazy val controllers_MnuPpto_pptoListadoPptos426_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPpto_13.pptoListadoPptos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPpto",
      "pptoListadoPptos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """pptoListadoPptos/""",
      """""",
      Seq()
    )
  )

  // @LINE:657
  private lazy val controllers_MnuPpto_pptoVsRealxBodega427_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pptoVsRealxBodega/"), DynamicPart("id_bodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuPpto_pptoVsRealxBodega427_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuPpto_13.pptoVsRealxBodega(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuPpto",
      "pptoVsRealxBodega",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """pptoVsRealxBodega/""" + "$" + """id_bodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:664
  private lazy val controllers_MnuCotizar_cotizaIngreso428_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaIngreso/")))
  )
  private lazy val controllers_MnuCotizar_cotizaIngreso428_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaIngreso(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaIngreso",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaIngreso/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU COTIZAR
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:665
  private lazy val controllers_MnuCotizar_cotizaIngreso2429_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaIngreso2/"), DynamicPart("id_bodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuCotizar_cotizaIngreso2429_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaIngreso2(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaIngreso2",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """cotizaIngreso2/""" + "$" + """id_bodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:666
  private lazy val controllers_MnuCotizar_tablaInvPorEquipoAjax430_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tablaInvPorEquipoAjax/")))
  )
  private lazy val controllers_MnuCotizar_tablaInvPorEquipoAjax430_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.tablaInvPorEquipoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "tablaInvPorEquipoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tablaInvPorEquipoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:667
  private lazy val controllers_MnuCotizar_cotizarNuevo431_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizarNuevo/")))
  )
  private lazy val controllers_MnuCotizar_cotizarNuevo431_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizarNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizarNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizarNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:668
  private lazy val controllers_MnuCotizar_existeNumeroCotizacionAjax432_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("existeNumeroCotizacionAjax/")))
  )
  private lazy val controllers_MnuCotizar_existeNumeroCotizacionAjax432_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.existeNumeroCotizacionAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "existeNumeroCotizacionAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """existeNumeroCotizacionAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:669
  private lazy val controllers_MnuCotizar_cotizaModificaJson433_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaModificaJson/")))
  )
  private lazy val controllers_MnuCotizar_cotizaModificaJson433_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaModificaJson(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaModificaJson",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaModificaJson/""",
      """""",
      Seq()
    )
  )

  // @LINE:671
  private lazy val controllers_MnuCotizar_actualizaComercialesAjax434_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualizaComercialesAjax/")))
  )
  private lazy val controllers_MnuCotizar_actualizaComercialesAjax434_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.actualizaComercialesAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "actualizaComercialesAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualizaComercialesAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:672
  private lazy val controllers_MnuCotizar_actualizaComerciales2Ajax435_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualizaComerciales2Ajax/")))
  )
  private lazy val controllers_MnuCotizar_actualizaComerciales2Ajax435_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.actualizaComerciales2Ajax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "actualizaComerciales2Ajax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualizaComerciales2Ajax/""",
      """""",
      Seq()
    )
  )

  // @LINE:675
  private lazy val controllers_MnuCotizar_actualizaListaCotiAjax436_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualizaListaCotiAjax/")))
  )
  private lazy val controllers_MnuCotizar_actualizaListaCotiAjax436_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.actualizaListaCotiAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "actualizaListaCotiAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualizaListaCotiAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:676
  private lazy val controllers_MnuCotizar_actualizaPreciosAjax437_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualizaPreciosAjax/")))
  )
  private lazy val controllers_MnuCotizar_actualizaPreciosAjax437_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.actualizaPreciosAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "actualizaPreciosAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualizaPreciosAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:679
  private lazy val controllers_MnuCotizar_cotizaListaModificaPeriodo438_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaModificaPeriodo/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaModificaPeriodo438_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaListaModificaPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaListaModificaPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaListaModificaPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:680
  private lazy val controllers_MnuCotizar_cotizaListaModifica439_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaModifica/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaModifica439_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaListaModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaListaModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaListaModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:681
  private lazy val controllers_MnuCotizar_cotizaElimina440_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaElimina/")))
  )
  private lazy val controllers_MnuCotizar_cotizaElimina440_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:682
  private lazy val controllers_MnuCotizar_cotizaModifica441_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaModifica/")))
  )
  private lazy val controllers_MnuCotizar_cotizaModifica441_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:683
  private lazy val controllers_MnuCotizar_cotizarUpdate442_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizarUpdate/")))
  )
  private lazy val controllers_MnuCotizar_cotizarUpdate442_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizarUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizarUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizarUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:686
  private lazy val controllers_MnuCotizar_cotizaListaCambiaEstadoPeriodo443_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaCambiaEstadoPeriodo/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaCambiaEstadoPeriodo443_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaListaCambiaEstadoPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaListaCambiaEstadoPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaListaCambiaEstadoPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:687
  private lazy val controllers_MnuCotizar_cotizaListaCambiaEstado444_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaCambiaEstado/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaCambiaEstado444_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaListaCambiaEstado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaListaCambiaEstado",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaListaCambiaEstado/""",
      """""",
      Seq()
    )
  )

  // @LINE:688
  private lazy val controllers_MnuCotizar_cambiarCotizaEstadoAjax445_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cambiarCotizaEstadoAjax/")))
  )
  private lazy val controllers_MnuCotizar_cambiarCotizaEstadoAjax445_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cambiarCotizaEstadoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cambiarCotizaEstadoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cambiarCotizaEstadoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:689
  private lazy val controllers_MnuCotizar_cambiarCotizaFechaProbableAjax446_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cambiarCotizaFechaProbableAjax/")))
  )
  private lazy val controllers_MnuCotizar_cambiarCotizaFechaProbableAjax446_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cambiarCotizaFechaProbableAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cambiarCotizaFechaProbableAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cambiarCotizaFechaProbableAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:690
  private lazy val controllers_MnuCotizar_cambiarCotizaDibujanteAjax447_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cambiarCotizaDibujanteAjax/")))
  )
  private lazy val controllers_MnuCotizar_cambiarCotizaDibujanteAjax447_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cambiarCotizaDibujanteAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cambiarCotizaDibujanteAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cambiarCotizaDibujanteAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:691
  private lazy val controllers_MnuCotizar_cambiarCotizaNotaAjax448_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cambiarCotizaNotaAjax/")))
  )
  private lazy val controllers_MnuCotizar_cambiarCotizaNotaAjax448_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cambiarCotizaNotaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cambiarCotizaNotaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cambiarCotizaNotaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:693
  private lazy val controllers_MnuCotizar_cotizacionFindIdForNumAjax449_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizacionFindIdForNumAjax/")))
  )
  private lazy val controllers_MnuCotizar_cotizacionFindIdForNumAjax449_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizacionFindIdForNumAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizacionFindIdForNumAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizacionFindIdForNumAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:695
  private lazy val controllers_MnuCotizar_cotizaEstadoMantencion450_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaEstadoMantencion/")))
  )
  private lazy val controllers_MnuCotizar_cotizaEstadoMantencion450_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaEstadoMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaEstadoMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaEstadoMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:696
  private lazy val controllers_MnuCotizar_cotizaEstadoElimina451_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaEstadoElimina/")))
  )
  private lazy val controllers_MnuCotizar_cotizaEstadoElimina451_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaEstadoElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaEstadoElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaEstadoElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:697
  private lazy val controllers_MnuCotizar_cotizaEstadoModifica452_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaEstadoModifica/")))
  )
  private lazy val controllers_MnuCotizar_cotizaEstadoModifica452_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaEstadoModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaEstadoModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaEstadoModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:698
  private lazy val controllers_MnuCotizar_modificaCotizaEstadoPorCampoAjax453_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaCotizaEstadoPorCampoAjax/")))
  )
  private lazy val controllers_MnuCotizar_modificaCotizaEstadoPorCampoAjax453_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.modificaCotizaEstadoPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "modificaCotizaEstadoPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaCotizaEstadoPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:699
  private lazy val controllers_MnuCotizar_cotizaEstadoAgrega454_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaEstadoAgrega/")))
  )
  private lazy val controllers_MnuCotizar_cotizaEstadoAgrega454_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaEstadoAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaEstadoAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaEstadoAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:700
  private lazy val controllers_MnuCotizar_cotizaEstadoNuevo455_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaEstadoNuevo/")))
  )
  private lazy val controllers_MnuCotizar_cotizaEstadoNuevo455_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaEstadoNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaEstadoNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaEstadoNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:704
  private lazy val controllers_MnuCotizar_cotizaListaImprimirPeriodo456_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaImprimirPeriodo/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaImprimirPeriodo456_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaListaImprimirPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaListaImprimirPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaListaImprimirPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:705
  private lazy val controllers_MnuCotizar_cotizaListaImprimir457_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaImprimir/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaImprimir457_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaListaImprimir(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaListaImprimir",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaListaImprimir/""",
      """""",
      Seq()
    )
  )

  // @LINE:706
  private lazy val controllers_MnuCotizar_cotizaImprimir458_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaImprimir/"), DynamicPart("id_cotizacion", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuCotizar_cotizaImprimir458_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaImprimir(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaImprimir",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """cotizaImprimir/""" + "$" + """id_cotizacion<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:707
  private lazy val controllers_MnuCotizar_cotizaExcel459_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaExcel/")))
  )
  private lazy val controllers_MnuCotizar_cotizaExcel459_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:708
  private lazy val controllers_MnuCotizar_pdfCotizaArriendo460_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pdfCotizaArriendo/")))
  )
  private lazy val controllers_MnuCotizar_pdfCotizaArriendo460_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.pdfCotizaArriendo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "pdfCotizaArriendo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """pdfCotizaArriendo/""",
      """""",
      Seq()
    )
  )

  // @LINE:709
  private lazy val controllers_MnuCotizar_pdfCotizaVenta461_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pdfCotizaVenta/")))
  )
  private lazy val controllers_MnuCotizar_pdfCotizaVenta461_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.pdfCotizaVenta(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "pdfCotizaVenta",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """pdfCotizaVenta/""",
      """""",
      Seq()
    )
  )

  // @LINE:710
  private lazy val controllers_MnuCotizar_pdfCotizaArrVta462_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pdfCotizaArrVta/")))
  )
  private lazy val controllers_MnuCotizar_pdfCotizaArrVta462_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.pdfCotizaArrVta(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "pdfCotizaArrVta",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """pdfCotizaArrVta/""",
      """""",
      Seq()
    )
  )

  // @LINE:712
  private lazy val controllers_MnuCotizar_reportCotizaSel463_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportCotizaSel/")))
  )
  private lazy val controllers_MnuCotizar_reportCotizaSel463_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.reportCotizaSel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "reportCotizaSel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportCotizaSel/""",
      """""",
      Seq()
    )
  )

  // @LINE:713
  private lazy val controllers_MnuCotizar_reportCotizaRpt464_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportCotizaRpt/")))
  )
  private lazy val controllers_MnuCotizar_reportCotizaRpt464_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.reportCotizaRpt(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "reportCotizaRpt",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportCotizaRpt/""",
      """""",
      Seq()
    )
  )

  // @LINE:716
  private lazy val controllers_MnuCotizar_cotizaListaConfirma465_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaConfirma/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaConfirma465_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaListaConfirma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaListaConfirma",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaListaConfirma/""",
      """""",
      Seq()
    )
  )

  // @LINE:717
  private lazy val controllers_MnuCotizar_cotizaConfirma466_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaConfirma/")))
  )
  private lazy val controllers_MnuCotizar_cotizaConfirma466_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotizaConfirma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotizaConfirma",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaConfirma/""",
      """""",
      Seq()
    )
  )

  // @LINE:719
  private lazy val controllers_MnuCotizar_otListaCotizaSinOt467_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaCotizaSinOt/")))
  )
  private lazy val controllers_MnuCotizar_otListaCotizaSinOt467_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otListaCotizaSinOt(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otListaCotizaSinOt",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otListaCotizaSinOt/""",
      """""",
      Seq()
    )
  )

  // @LINE:720
  private lazy val controllers_MnuCotizar_otHacer468_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otHacer/")))
  )
  private lazy val controllers_MnuCotizar_otHacer468_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otHacer(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otHacer",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otHacer/""",
      """""",
      Seq()
    )
  )

  // @LINE:721
  private lazy val controllers_MnuCotizar_otGrabar469_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otGrabar/")))
  )
  private lazy val controllers_MnuCotizar_otGrabar469_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otGrabar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otGrabar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otGrabar/""",
      """""",
      Seq()
    )
  )

  // @LINE:723
  private lazy val controllers_MnuCotizar_otListaEliminar470_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaEliminar/")))
  )
  private lazy val controllers_MnuCotizar_otListaEliminar470_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otListaEliminar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otListaEliminar",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otListaEliminar/""",
      """""",
      Seq()
    )
  )

  // @LINE:724
  private lazy val controllers_MnuCotizar_verOtAjax471_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verOtAjax/")))
  )
  private lazy val controllers_MnuCotizar_verOtAjax471_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.verOtAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "verOtAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verOtAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:725
  private lazy val controllers_MnuCotizar_otElimina472_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otElimina/")))
  )
  private lazy val controllers_MnuCotizar_otElimina472_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:726
  private lazy val controllers_MnuCotizar_actualFechaEnvioOtAjax473_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualFechaEnvioOtAjax/")))
  )
  private lazy val controllers_MnuCotizar_actualFechaEnvioOtAjax473_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.actualFechaEnvioOtAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "actualFechaEnvioOtAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualFechaEnvioOtAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:729
  private lazy val controllers_MnuCotizar_otListaCambiarEstadoPeriodo474_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaCambiarEstadoPeriodo/")))
  )
  private lazy val controllers_MnuCotizar_otListaCambiarEstadoPeriodo474_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otListaCambiarEstadoPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otListaCambiarEstadoPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otListaCambiarEstadoPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:730
  private lazy val controllers_MnuCotizar_otListaCambiarEstado475_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaCambiarEstado/")))
  )
  private lazy val controllers_MnuCotizar_otListaCambiarEstado475_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otListaCambiarEstado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otListaCambiarEstado",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otListaCambiarEstado/""",
      """""",
      Seq()
    )
  )

  // @LINE:731
  private lazy val controllers_MnuCotizar_cambiarOtEstadoAjax476_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cambiarOtEstadoAjax/")))
  )
  private lazy val controllers_MnuCotizar_cambiarOtEstadoAjax476_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cambiarOtEstadoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cambiarOtEstadoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cambiarOtEstadoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:732
  private lazy val controllers_MnuCotizar_cambiarOtNotaAjax477_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cambiarOtNotaAjax/")))
  )
  private lazy val controllers_MnuCotizar_cambiarOtNotaAjax477_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cambiarOtNotaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cambiarOtNotaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cambiarOtNotaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:734
  private lazy val controllers_MnuCotizar_otEstadoMantencion478_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otEstadoMantencion/")))
  )
  private lazy val controllers_MnuCotizar_otEstadoMantencion478_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otEstadoMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otEstadoMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otEstadoMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:735
  private lazy val controllers_MnuCotizar_otEstadoElimina479_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otEstadoElimina/")))
  )
  private lazy val controllers_MnuCotizar_otEstadoElimina479_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otEstadoElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otEstadoElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otEstadoElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:736
  private lazy val controllers_MnuCotizar_otEstadoModifica480_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otEstadoModifica/")))
  )
  private lazy val controllers_MnuCotizar_otEstadoModifica480_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otEstadoModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otEstadoModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otEstadoModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:737
  private lazy val controllers_MnuCotizar_modificaOtEstadoPorCampoAjax481_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaOtEstadoPorCampoAjax/")))
  )
  private lazy val controllers_MnuCotizar_modificaOtEstadoPorCampoAjax481_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.modificaOtEstadoPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "modificaOtEstadoPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaOtEstadoPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:738
  private lazy val controllers_MnuCotizar_otEstadoAgrega482_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otEstadoAgrega/")))
  )
  private lazy val controllers_MnuCotizar_otEstadoAgrega482_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otEstadoAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otEstadoAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otEstadoAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:739
  private lazy val controllers_MnuCotizar_otEstadoNuevo483_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otEstadoNuevo/")))
  )
  private lazy val controllers_MnuCotizar_otEstadoNuevo483_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otEstadoNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otEstadoNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otEstadoNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:742
  private lazy val controllers_MnuCotizar_otListaConfirma484_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaConfirma/")))
  )
  private lazy val controllers_MnuCotizar_otListaConfirma484_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otListaConfirma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otListaConfirma",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otListaConfirma/""",
      """""",
      Seq()
    )
  )

  // @LINE:743
  private lazy val controllers_MnuCotizar_otConfirma485_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otConfirma/")))
  )
  private lazy val controllers_MnuCotizar_otConfirma485_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otConfirma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otConfirma",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otConfirma/""",
      """""",
      Seq()
    )
  )

  // @LINE:745
  private lazy val controllers_MnuCotizar_otListaDespacharPeriodo486_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaDespacharPeriodo/")))
  )
  private lazy val controllers_MnuCotizar_otListaDespacharPeriodo486_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otListaDespacharPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otListaDespacharPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otListaDespacharPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:746
  private lazy val controllers_MnuCotizar_otListaDespachar487_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaDespachar/")))
  )
  private lazy val controllers_MnuCotizar_otListaDespachar487_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otListaDespachar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otListaDespachar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otListaDespachar/""",
      """""",
      Seq()
    )
  )

  // @LINE:747
  private lazy val controllers_MnuCotizar_otDespachar488_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otDespachar/")))
  )
  private lazy val controllers_MnuCotizar_otDespachar488_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otDespachar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otDespachar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otDespachar/""",
      """""",
      Seq()
    )
  )

  // @LINE:748
  private lazy val controllers_MnuCotizar_tblListEquipoConStockAjax489_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tblListEquipoConStockAjax/")))
  )
  private lazy val controllers_MnuCotizar_tblListEquipoConStockAjax489_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.tblListEquipoConStockAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "tblListEquipoConStockAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tblListEquipoConStockAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:749
  private lazy val controllers_MnuCotizar_grabaDespacho490_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabaDespacho/")))
  )
  private lazy val controllers_MnuCotizar_grabaDespacho490_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.grabaDespacho(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "grabaDespacho",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grabaDespacho/""",
      """""",
      Seq()
    )
  )

  // @LINE:751
  private lazy val controllers_MnuCotizar_otListaDespachoModificarPeriodo491_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaDespachoModificarPeriodo/")))
  )
  private lazy val controllers_MnuCotizar_otListaDespachoModificarPeriodo491_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otListaDespachoModificarPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otListaDespachoModificarPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otListaDespachoModificarPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:752
  private lazy val controllers_MnuCotizar_otListaDespachoModificar492_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaDespachoModificar/")))
  )
  private lazy val controllers_MnuCotizar_otListaDespachoModificar492_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otListaDespachoModificar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otListaDespachoModificar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otListaDespachoModificar/""",
      """""",
      Seq()
    )
  )

  // @LINE:753
  private lazy val controllers_MnuCotizar_otDespachoElimina493_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otDespachoElimina/")))
  )
  private lazy val controllers_MnuCotizar_otDespachoElimina493_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otDespachoElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otDespachoElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otDespachoElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:754
  private lazy val controllers_MnuCotizar_otDespachoModificar494_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otDespachoModificar/")))
  )
  private lazy val controllers_MnuCotizar_otDespachoModificar494_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.otDespachoModificar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "otDespachoModificar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otDespachoModificar/""",
      """""",
      Seq()
    )
  )

  // @LINE:755
  private lazy val controllers_MnuCotizar_modificaDespacho495_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaDespacho/")))
  )
  private lazy val controllers_MnuCotizar_modificaDespacho495_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.modificaDespacho(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "modificaDespacho",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaDespacho/""",
      """""",
      Seq()
    )
  )

  // @LINE:758
  private lazy val controllers_MnuCotizar_cotiPlantilla496_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiPlantilla/")))
  )
  private lazy val controllers_MnuCotizar_cotiPlantilla496_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotiPlantilla(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotiPlantilla",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiPlantilla/""",
      """""",
      Seq()
    )
  )

  // @LINE:759
  private lazy val controllers_MnuCotizar_cotiValidarPlantilla497_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiValidarPlantilla/")))
  )
  private lazy val controllers_MnuCotizar_cotiValidarPlantilla497_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotiValidarPlantilla(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotiValidarPlantilla",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiValidarPlantilla/""",
      """""",
      Seq()
    )
  )

  // @LINE:760
  private lazy val controllers_MnuCotizar_cotiCargaPlantilla498_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiCargaPlantilla/")))
  )
  private lazy val controllers_MnuCotizar_cotiCargaPlantilla498_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_4.cotiCargaPlantilla(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCotizar",
      "cotiCargaPlantilla",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiCargaPlantilla/""",
      """""",
      Seq()
    )
  )

  // @LINE:768
  private lazy val controllers_MnuCompras_compraImportIconstruye0499_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraImportIconstruye0/")))
  )
  private lazy val controllers_MnuCompras_compraImportIconstruye0499_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraImportIconstruye0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraImportIconstruye0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """compraImportIconstruye0/""",
      """""",
      Seq()
    )
  )

  // @LINE:769
  private lazy val controllers_MnuCompras_compraImportIconstruye1500_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraImportIconstruye1/")))
  )
  private lazy val controllers_MnuCompras_compraImportIconstruye1500_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraImportIconstruye1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraImportIconstruye1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """compraImportIconstruye1/""",
      """""",
      Seq()
    )
  )

  // @LINE:770
  private lazy val controllers_MnuCompras_compraImportIconstruye2501_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraImportIconstruye2/")))
  )
  private lazy val controllers_MnuCompras_compraImportIconstruye2501_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraImportIconstruye2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraImportIconstruye2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """compraImportIconstruye2/""",
      """""",
      Seq()
    )
  )

  // @LINE:773
  private lazy val controllers_MnuCompras_compraIngreso502_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraIngreso/")))
  )
  private lazy val controllers_MnuCompras_compraIngreso502_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraIngreso(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraIngreso",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """compraIngreso/""",
      """""",
      Seq()
    )
  )

  // @LINE:774
  private lazy val controllers_MnuCompras_verificaNumeroFacturaAjax503_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaNumeroFacturaAjax/")))
  )
  private lazy val controllers_MnuCompras_verificaNumeroFacturaAjax503_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.verificaNumeroFacturaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "verificaNumeroFacturaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaNumeroFacturaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:775
  private lazy val controllers_MnuCompras_compraNuevo504_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraNuevo/")))
  )
  private lazy val controllers_MnuCompras_compraNuevo504_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """compraNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:776
  private lazy val controllers_MnuCompras_modalPreciosAjax505_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modalPreciosAjax/")))
  )
  private lazy val controllers_MnuCompras_modalPreciosAjax505_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.modalPreciosAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "modalPreciosAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modalPreciosAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:777
  private lazy val controllers_MnuCompras_nuevoEquipoAjax506_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("nuevoEquipoAjax/")))
  )
  private lazy val controllers_MnuCompras_nuevoEquipoAjax506_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.nuevoEquipoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "nuevoEquipoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """nuevoEquipoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:778
  private lazy val controllers_MnuCompras_compraConfirma507_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraConfirma/")))
  )
  private lazy val controllers_MnuCompras_compraConfirma507_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraConfirma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraConfirma",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """compraConfirma/""",
      """""",
      Seq()
    )
  )

  // @LINE:779
  private lazy val controllers_MnuCompras_compraConfirmaIngreso508_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraConfirmaIngreso/")))
  )
  private lazy val controllers_MnuCompras_compraConfirmaIngreso508_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraConfirmaIngreso(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraConfirmaIngreso",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """compraConfirmaIngreso/""",
      """""",
      Seq()
    )
  )

  // @LINE:780
  private lazy val controllers_MnuCompras_compraListaModifica509_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraListaModifica/")))
  )
  private lazy val controllers_MnuCompras_compraListaModifica509_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraListaModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraListaModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """compraListaModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:781
  private lazy val controllers_MnuCompras_compraModifica510_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraModifica/"), DynamicPart("id_factura", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuCompras_compraModifica510_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraModifica(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraModifica",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """compraModifica/""" + "$" + """id_factura<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:782
  private lazy val controllers_MnuCompras_compraModificaGraba511_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraModificaGraba/")))
  )
  private lazy val controllers_MnuCompras_compraModificaGraba511_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraModificaGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraModificaGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """compraModificaGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:783
  private lazy val controllers_MnuCompras_modificaFacturaPorCampoAjax512_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaFacturaPorCampoAjax/")))
  )
  private lazy val controllers_MnuCompras_modificaFacturaPorCampoAjax512_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.modificaFacturaPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "modificaFacturaPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaFacturaPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:784
  private lazy val controllers_MnuCompras_modificaCompraPorCampoAjax513_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaCompraPorCampoAjax/")))
  )
  private lazy val controllers_MnuCompras_modificaCompraPorCampoAjax513_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.modificaCompraPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "modificaCompraPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaCompraPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:785
  private lazy val controllers_MnuCompras_eliminaCompraAjax514_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("eliminaCompraAjax/")))
  )
  private lazy val controllers_MnuCompras_eliminaCompraAjax514_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.eliminaCompraAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "eliminaCompraAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """eliminaCompraAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:786
  private lazy val controllers_MnuCompras_listComprasPorCompra515_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("listComprasPorCompra/")))
  )
  private lazy val controllers_MnuCompras_listComprasPorCompra515_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.listComprasPorCompra(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "listComprasPorCompra",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """listComprasPorCompra/""",
      """""",
      Seq()
    )
  )

  // @LINE:787
  private lazy val controllers_MnuCompras_listComprasPorCompraExcel516_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("listComprasPorCompraExcel/")))
  )
  private lazy val controllers_MnuCompras_listComprasPorCompraExcel516_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.listComprasPorCompraExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "listComprasPorCompraExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """listComprasPorCompraExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:788
  private lazy val controllers_MnuCompras_compraFacturaPrint517_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraFacturaPrint/"), DynamicPart("id_factura", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuCompras_compraFacturaPrint517_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraFacturaPrint(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraFacturaPrint",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """compraFacturaPrint/""" + "$" + """id_factura<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:789
  private lazy val controllers_MnuCompras_compraFacturaPrintExcel518_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraFacturaPrintExcel/")))
  )
  private lazy val controllers_MnuCompras_compraFacturaPrintExcel518_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraFacturaPrintExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraFacturaPrintExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """compraFacturaPrintExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:790
  private lazy val controllers_MnuCompras_listComprasPorEquipo519_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("listComprasPorEquipo/")))
  )
  private lazy val controllers_MnuCompras_listComprasPorEquipo519_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.listComprasPorEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "listComprasPorEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """listComprasPorEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:791
  private lazy val controllers_MnuCompras_listComprasPorEquipoExcel520_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("listComprasPorEquipoExcel/")))
  )
  private lazy val controllers_MnuCompras_listComprasPorEquipoExcel520_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.listComprasPorEquipoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "listComprasPorEquipoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """listComprasPorEquipoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:793
  private lazy val controllers_MnuCompras_compraEquipoPrint521_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraEquipoPrint/"), DynamicPart("id_equipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuCompras_compraEquipoPrint521_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraEquipoPrint(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraEquipoPrint",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """compraEquipoPrint/""" + "$" + """id_equipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:794
  private lazy val controllers_MnuCompras_compraElimina522_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraElimina/")))
  )
  private lazy val controllers_MnuCompras_compraElimina522_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.compraElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "compraElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """compraElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:795
  private lazy val controllers_MnuCompras_eliminaCompra523_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("eliminaCompra/")))
  )
  private lazy val controllers_MnuCompras_eliminaCompra523_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_6.eliminaCompra(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuCompras",
      "eliminaCompra",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """eliminaCompra/""",
      """""",
      Seq()
    )
  )

  // @LINE:801
  private lazy val controllers_MnuMovimientos_movimientoSelectBodegaOrigen524_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoSelectBodegaOrigen/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoSelectBodegaOrigen524_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoSelectBodegaOrigen(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoSelectBodegaOrigen",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """movimientoSelectBodegaOrigen/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU MOVIMIENTOS
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:802
  private lazy val controllers_MnuMovimientos_movimientoOrigenDestinoMultiple525_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoOrigenDestinoMultiple/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoOrigenDestinoMultiple525_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoOrigenDestinoMultiple(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoOrigenDestinoMultiple",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movimientoOrigenDestinoMultiple/""",
      """""",
      Seq()
    )
  )

  // @LINE:803
  private lazy val controllers_MnuMovimientos_movimientoOrigenDestinoMultiple1526_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoOrigenDestinoMultiple1/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoOrigenDestinoMultiple1526_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoOrigenDestinoMultiple1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoOrigenDestinoMultiple1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movimientoOrigenDestinoMultiple1/""",
      """""",
      Seq()
    )
  )

  // @LINE:804
  private lazy val controllers_MnuMovimientos_verificaNumeroGuiaAjax527_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaNumeroGuiaAjax/")))
  )
  private lazy val controllers_MnuMovimientos_verificaNumeroGuiaAjax527_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.verificaNumeroGuiaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "verificaNumeroGuiaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaNumeroGuiaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:805
  private lazy val controllers_MnuMovimientos_nameComercialAjax528_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("nameComercialAjax/")))
  )
  private lazy val controllers_MnuMovimientos_nameComercialAjax528_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.nameComercialAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "nameComercialAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """nameComercialAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:806
  private lazy val controllers_MnuMovimientos_nickNameClienteAjax529_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("nickNameClienteAjax/")))
  )
  private lazy val controllers_MnuMovimientos_nickNameClienteAjax529_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.nickNameClienteAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "nickNameClienteAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """nickNameClienteAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:807
  private lazy val controllers_MnuMovimientos_movimientoAplicar530_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoAplicar/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoAplicar530_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoAplicar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoAplicar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movimientoAplicar/""",
      """""",
      Seq()
    )
  )

  // @LINE:809
  private lazy val controllers_MnuMovimientos_movimientoSelectModificarPeriodo531_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoSelectModificarPeriodo/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoSelectModificarPeriodo531_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoSelectModificarPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoSelectModificarPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """movimientoSelectModificarPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:810
  private lazy val controllers_MnuMovimientos_movimientoSelectModificar532_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoSelectModificar/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoSelectModificar532_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoSelectModificar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoSelectModificar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movimientoSelectModificar/""",
      """""",
      Seq()
    )
  )

  // @LINE:813
  private lazy val controllers_MnuMovimientos_verCotizacionAjax533_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verCotizacionAjax/")))
  )
  private lazy val controllers_MnuMovimientos_verCotizacionAjax533_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.verCotizacionAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "verCotizacionAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verCotizacionAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:814
  private lazy val controllers_MnuMovimientos_movimientoOrigenDestinoModifica534_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoOrigenDestinoModifica/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoOrigenDestinoModifica534_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoOrigenDestinoModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoOrigenDestinoModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movimientoOrigenDestinoModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:815
  private lazy val controllers_MnuMovimientos_movimientoOrigenDestinoElimina535_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoOrigenDestinoElimina/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoOrigenDestinoElimina535_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoOrigenDestinoElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoOrigenDestinoElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movimientoOrigenDestinoElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:816
  private lazy val controllers_MnuMovimientos_movimientoAplicarCambios536_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoAplicarCambios/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoAplicarCambios536_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoAplicarCambios(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoAplicarCambios",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movimientoAplicarCambios/""",
      """""",
      Seq()
    )
  )

  // @LINE:818
  private lazy val controllers_MnuMovimientos_grabaAlbumFotosAjax537_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabaAlbumFotosAjax/")))
  )
  private lazy val controllers_MnuMovimientos_grabaAlbumFotosAjax537_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.grabaAlbumFotosAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "grabaAlbumFotosAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grabaAlbumFotosAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:820
  private lazy val controllers_MnuMovimientos_movimientoListarPeriodo538_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoListarPeriodo/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoListarPeriodo538_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoListarPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoListarPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """movimientoListarPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:821
  private lazy val controllers_MnuMovimientos_movimientoListar539_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoListar/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoListar539_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoListar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoListar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movimientoListar/""",
      """""",
      Seq()
    )
  )

  // @LINE:822
  private lazy val controllers_MnuMovimientos_movimientoListarGet540_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoListarGet/"), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMovimientos_movimientoListarGet540_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoListarGet(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoListarGet",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "GET",
      this.prefix + """movimientoListarGet/""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:823
  private lazy val controllers_MnuMovimientos_movimientoListarExcel541_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoListarExcel/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoListarExcel541_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoListarExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoListarExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movimientoListarExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:824
  private lazy val controllers_MnuMovimientos_showPdfRelBase542_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("showPdfRelBase/"), DynamicPart("tipo", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("nroFiscal", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMovimientos_showPdfRelBase542_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.showPdfRelBase(fakeValue[String], fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "showPdfRelBase",
      Seq(classOf[String], classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """showPdfRelBase/""" + "$" + """tipo<[^/]+>,""" + "$" + """nroFiscal<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:827
  private lazy val controllers_MnuMovimientos_movimientoDetalleGuia543_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoDetalleGuia/"), DynamicPart("id_guia", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMovimientos_movimientoDetalleGuia543_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoDetalleGuia(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoDetalleGuia",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """movimientoDetalleGuia/""" + "$" + """id_guia<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:828
  private lazy val controllers_MnuMovimientos_generaGuiaXLS544_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaGuiaXLS/")))
  )
  private lazy val controllers_MnuMovimientos_generaGuiaXLS544_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.generaGuiaXLS(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "generaGuiaXLS",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaGuiaXLS/""",
      """""",
      Seq()
    )
  )

  // @LINE:829
  private lazy val controllers_MnuMovimientos_generaGuiaExcel545_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaGuiaExcel/")))
  )
  private lazy val controllers_MnuMovimientos_generaGuiaExcel545_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.generaGuiaExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "generaGuiaExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaGuiaExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:830
  private lazy val controllers_MnuMovimientos_guiaCambiarNroRef546_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("guiaCambiarNroRef/")))
  )
  private lazy val controllers_MnuMovimientos_guiaCambiarNroRef546_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.guiaCambiarNroRef(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "guiaCambiarNroRef",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """guiaCambiarNroRef/""",
      """""",
      Seq()
    )
  )

  // @LINE:833
  private lazy val controllers_MnuMovimientos_editaTransportistaAjax547_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("editaTransportistaAjax/")))
  )
  private lazy val controllers_MnuMovimientos_editaTransportistaAjax547_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.editaTransportistaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "editaTransportistaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """editaTransportistaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:834
  private lazy val controllers_MnuMovimientos_nuevoTransportistaAjax548_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("nuevoTransportistaAjax/")))
  )
  private lazy val controllers_MnuMovimientos_nuevoTransportistaAjax548_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.nuevoTransportistaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "nuevoTransportistaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """nuevoTransportistaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:835
  private lazy val controllers_MnuMovimientos_modificaTransportistaAjax549_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaTransportistaAjax/")))
  )
  private lazy val controllers_MnuMovimientos_modificaTransportistaAjax549_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.modificaTransportistaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "modificaTransportistaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaTransportistaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:836
  private lazy val controllers_MnuMovimientos_eliminaTransportistaAjax550_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("eliminaTransportistaAjax/")))
  )
  private lazy val controllers_MnuMovimientos_eliminaTransportistaAjax550_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.eliminaTransportistaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "eliminaTransportistaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """eliminaTransportistaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:838
  private lazy val controllers_MnuMovimientos_generaGuiaXML551_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaGuiaXML/")))
  )
  private lazy val controllers_MnuMovimientos_generaGuiaXML551_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.generaGuiaXML(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "generaGuiaXML",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaGuiaXML/""",
      """""",
      Seq()
    )
  )

  // @LINE:839
  private lazy val controllers_MnuMovimientos_generaGuiaApiManager552_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaGuiaApiManager/")))
  )
  private lazy val controllers_MnuMovimientos_generaGuiaApiManager552_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.generaGuiaApiManager(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "generaGuiaApiManager",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaGuiaApiManager/""",
      """""",
      Seq()
    )
  )

  // @LINE:840
  private lazy val controllers_MnuMovimientos_generaGuiaApiNubox553_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaGuiaApiNubox/")))
  )
  private lazy val controllers_MnuMovimientos_generaGuiaApiNubox553_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.generaGuiaApiNubox(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "generaGuiaApiNubox",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaGuiaApiNubox/""",
      """""",
      Seq()
    )
  )

  // @LINE:841
  private lazy val controllers_MnuMovimientos_generaGuiaWebFacturacion554_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaGuiaWebFacturacion/")))
  )
  private lazy val controllers_MnuMovimientos_generaGuiaWebFacturacion554_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.generaGuiaWebFacturacion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "generaGuiaWebFacturacion",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaGuiaWebFacturacion/""",
      """""",
      Seq()
    )
  )

  // @LINE:842
  private lazy val controllers_MnuMovimientos_generaGuiaWebMaximise555_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaGuiaWebMaximise/")))
  )
  private lazy val controllers_MnuMovimientos_generaGuiaWebMaximise555_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.generaGuiaWebMaximise(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "generaGuiaWebMaximise",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaGuiaWebMaximise/""",
      """""",
      Seq()
    )
  )

  // @LINE:843
  private lazy val controllers_MnuMovimientos_downGuiaMaximiseAjax556_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("downGuiaMaximiseAjax/"), DynamicPart("nroInte", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMovimientos_downGuiaMaximiseAjax556_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.downGuiaMaximiseAjax(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "downGuiaMaximiseAjax",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """downGuiaMaximiseAjax/""" + "$" + """nroInte<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:845
  private lazy val controllers_MnuMovimientos_generaGuiaWebIConstruye557_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaGuiaWebIConstruye/")))
  )
  private lazy val controllers_MnuMovimientos_generaGuiaWebIConstruye557_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.generaGuiaWebIConstruye(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "generaGuiaWebIConstruye",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaGuiaWebIConstruye/""",
      """""",
      Seq()
    )
  )

  // @LINE:846
  private lazy val controllers_MnuMovimientos_downGuiaIconstruye558_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("downGuiaIconstruye/")))
  )
  private lazy val controllers_MnuMovimientos_downGuiaIconstruye558_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.downGuiaIconstruye(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "downGuiaIconstruye",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """downGuiaIconstruye/""",
      """""",
      Seq()
    )
  )

  // @LINE:848
  private lazy val controllers_MnuMovimientos_generaGuiaApiRelBase559_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaGuiaApiRelBase/")))
  )
  private lazy val controllers_MnuMovimientos_generaGuiaApiRelBase559_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.generaGuiaApiRelBase(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "generaGuiaApiRelBase",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaGuiaApiRelBase/""",
      """""",
      Seq()
    )
  )

  // @LINE:849
  private lazy val controllers_MnuMovimientos_buscaCotiSolucionAjax560_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("buscaCotiSolucionAjax/")))
  )
  private lazy val controllers_MnuMovimientos_buscaCotiSolucionAjax560_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.buscaCotiSolucionAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "buscaCotiSolucionAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """buscaCotiSolucionAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:850
  private lazy val controllers_MnuMovimientos_generaGuiaSapSchwager561_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("generaGuiaSapSchwager/")))
  )
  private lazy val controllers_MnuMovimientos_generaGuiaSapSchwager561_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.generaGuiaSapSchwager(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "generaGuiaSapSchwager",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """generaGuiaSapSchwager/""",
      """""",
      Seq()
    )
  )

  // @LINE:852
  private lazy val controllers_MnuMovimientos_movimientoListarImgPeriodo562_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoListarImgPeriodo/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoListarImgPeriodo562_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoListarImgPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoListarImgPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """movimientoListarImgPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:853
  private lazy val controllers_MnuMovimientos_movimientoListarImg563_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoListarImg/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoListarImg563_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoListarImg(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoListarImg",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movimientoListarImg/""",
      """""",
      Seq()
    )
  )

  // @LINE:854
  private lazy val controllers_MnuMovimientos_movimientoDetalleGuiaConImg564_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoDetalleGuiaConImg/"), DynamicPart("id_guia", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMovimientos_movimientoDetalleGuiaConImg564_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.movimientoDetalleGuiaConImg(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "movimientoDetalleGuiaConImg",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """movimientoDetalleGuiaConImg/""" + "$" + """id_guia<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:856
  private lazy val controllers_MnuMovimientos_hojaChequeoSelectBodegaAgrupado565_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaChequeoSelectBodegaAgrupado/")))
  )
  private lazy val controllers_MnuMovimientos_hojaChequeoSelectBodegaAgrupado565_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.hojaChequeoSelectBodegaAgrupado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "hojaChequeoSelectBodegaAgrupado",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hojaChequeoSelectBodegaAgrupado/""",
      """""",
      Seq()
    )
  )

  // @LINE:857
  private lazy val controllers_MnuMovimientos_hojaChequeoDetalleAgrupado566_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaChequeoDetalleAgrupado/"), DynamicPart("id_bodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMovimientos_hojaChequeoDetalleAgrupado566_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.hojaChequeoDetalleAgrupado(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "hojaChequeoDetalleAgrupado",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hojaChequeoDetalleAgrupado/""" + "$" + """id_bodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:858
  private lazy val controllers_MnuMovimientos_hojaChequeoSelectBodega567_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaChequeoSelectBodega/")))
  )
  private lazy val controllers_MnuMovimientos_hojaChequeoSelectBodega567_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.hojaChequeoSelectBodega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "hojaChequeoSelectBodega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hojaChequeoSelectBodega/""",
      """""",
      Seq()
    )
  )

  // @LINE:859
  private lazy val controllers_MnuMovimientos_hojaChequeoDetalle568_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaChequeoDetalle/"), DynamicPart("id_bodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMovimientos_hojaChequeoDetalle568_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.hojaChequeoDetalle(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "hojaChequeoDetalle",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hojaChequeoDetalle/""" + "$" + """id_bodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:862
  private lazy val controllers_MnuMovimientos_hojaChequeoSelectPorGrupo569_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaChequeoSelectPorGrupo/")))
  )
  private lazy val controllers_MnuMovimientos_hojaChequeoSelectPorGrupo569_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.hojaChequeoSelectPorGrupo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "hojaChequeoSelectPorGrupo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """hojaChequeoSelectPorGrupo/""",
      """""",
      Seq()
    )
  )

  // @LINE:863
  private lazy val controllers_MnuMovimientos_hojaChequeoDetallePorGrupo570_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaChequeoDetallePorGrupo/")))
  )
  private lazy val controllers_MnuMovimientos_hojaChequeoDetallePorGrupo570_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.hojaChequeoDetallePorGrupo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "hojaChequeoDetallePorGrupo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaChequeoDetallePorGrupo/""",
      """""",
      Seq()
    )
  )

  // @LINE:865
  private lazy val controllers_MnuMovimientos_hojaChequeoAgrupadoExcel571_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaChequeoAgrupadoExcel/")))
  )
  private lazy val controllers_MnuMovimientos_hojaChequeoAgrupadoExcel571_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.hojaChequeoAgrupadoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "hojaChequeoAgrupadoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaChequeoAgrupadoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:866
  private lazy val controllers_MnuMovimientos_hojaChequeoExcel572_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaChequeoExcel/")))
  )
  private lazy val controllers_MnuMovimientos_hojaChequeoExcel572_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.hojaChequeoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "hojaChequeoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaChequeoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:868
  private lazy val controllers_MnuMovimientos_hojaChequeoDetallePorGrupoExcel573_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hojaChequeoDetallePorGrupoExcel/")))
  )
  private lazy val controllers_MnuMovimientos_hojaChequeoDetallePorGrupoExcel573_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.hojaChequeoDetallePorGrupoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "hojaChequeoDetallePorGrupoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hojaChequeoDetallePorGrupoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:871
  private lazy val controllers_MnuMovimientos_cierreProyectoSel574_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cierreProyectoSel/")))
  )
  private lazy val controllers_MnuMovimientos_cierreProyectoSel574_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.cierreProyectoSel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "cierreProyectoSel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cierreProyectoSel/""",
      """""",
      Seq()
    )
  )

  // @LINE:872
  private lazy val controllers_MnuMovimientos_cierreProyectoDet575_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cierreProyectoDet/")))
  )
  private lazy val controllers_MnuMovimientos_cierreProyectoDet575_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.cierreProyectoDet(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "cierreProyectoDet",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cierreProyectoDet/""",
      """""",
      Seq()
    )
  )

  // @LINE:873
  private lazy val controllers_MnuMovimientos_cierreAplicar576_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cierreAplicar/")))
  )
  private lazy val controllers_MnuMovimientos_cierreAplicar576_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_9.cierreAplicar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuMovimientos",
      "cierreAplicar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cierreAplicar/""",
      """""",
      Seq()
    )
  )

  // @LINE:879
  private lazy val controllers_MnuBajas_bajaIngreso577_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bajaIngreso/")))
  )
  private lazy val controllers_MnuBajas_bajaIngreso577_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBajas_5.bajaIngreso(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBajas",
      "bajaIngreso",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bajaIngreso/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU BAJAS
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:880
  private lazy val controllers_MnuBajas_bajaNuevo578_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bajaNuevo/")))
  )
  private lazy val controllers_MnuBajas_bajaNuevo578_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBajas_5.bajaNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBajas",
      "bajaNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """bajaNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:881
  private lazy val controllers_MnuBajas_bajaListaModifica579_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bajaListaModifica/")))
  )
  private lazy val controllers_MnuBajas_bajaListaModifica579_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBajas_5.bajaListaModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBajas",
      "bajaListaModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bajaListaModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:882
  private lazy val controllers_MnuBajas_bajaModifica580_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bajaModifica/"), DynamicPart("id_actaBaja", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuBajas_bajaModifica580_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBajas_5.bajaModifica(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBajas",
      "bajaModifica",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bajaModifica/""" + "$" + """id_actaBaja<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:883
  private lazy val controllers_MnuBajas_bajaModificaGraba581_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bajaModificaGraba/")))
  )
  private lazy val controllers_MnuBajas_bajaModificaGraba581_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBajas_5.bajaModificaGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBajas",
      "bajaModificaGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """bajaModificaGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:884
  private lazy val controllers_MnuBajas_confirmaBaja582_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("confirmaBaja/")))
  )
  private lazy val controllers_MnuBajas_confirmaBaja582_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBajas_5.confirmaBaja(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBajas",
      "confirmaBaja",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """confirmaBaja/""",
      """""",
      Seq()
    )
  )

  // @LINE:885
  private lazy val controllers_MnuBajas_bajaConfirmaIngreso583_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bajaConfirmaIngreso/")))
  )
  private lazy val controllers_MnuBajas_bajaConfirmaIngreso583_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBajas_5.bajaConfirmaIngreso(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBajas",
      "bajaConfirmaIngreso",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """bajaConfirmaIngreso/""",
      """""",
      Seq()
    )
  )

  // @LINE:886
  private lazy val controllers_MnuBajas_listBajasPorActa584_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("listBajasPorActa/")))
  )
  private lazy val controllers_MnuBajas_listBajasPorActa584_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBajas_5.listBajasPorActa(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBajas",
      "listBajasPorActa",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """listBajasPorActa/""",
      """""",
      Seq()
    )
  )

  // @LINE:887
  private lazy val controllers_MnuBajas_bajaActaPrint585_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bajaActaPrint/"), DynamicPart("id_actaBaja", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuBajas_bajaActaPrint585_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBajas_5.bajaActaPrint(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBajas",
      "bajaActaPrint",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bajaActaPrint/""" + "$" + """id_actaBaja<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:888
  private lazy val controllers_MnuBajas_listBajasPorEquipo586_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("listBajasPorEquipo/")))
  )
  private lazy val controllers_MnuBajas_listBajasPorEquipo586_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBajas_5.listBajasPorEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBajas",
      "listBajasPorEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """listBajasPorEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:889
  private lazy val controllers_MnuBajas_listBajasPorEquipoExcel587_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("listBajasPorEquipoExcel/")))
  )
  private lazy val controllers_MnuBajas_listBajasPorEquipoExcel587_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBajas_5.listBajasPorEquipoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBajas",
      "listBajasPorEquipoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """listBajasPorEquipoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:890
  private lazy val controllers_MnuBajas_bajaEquipoPrint588_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bajaEquipoPrint/"), DynamicPart("id_equipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuBajas_bajaEquipoPrint588_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBajas_5.bajaEquipoPrint(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBajas",
      "bajaEquipoPrint",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bajaEquipoPrint/""" + "$" + """id_equipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:896
  private lazy val controllers_MnuBodegas_bodegaAdministrar589_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaAdministrar/")))
  )
  private lazy val controllers_MnuBodegas_bodegaAdministrar589_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaAdministrar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaAdministrar",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaAdministrar/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU BODEGAS
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:897
  private lazy val controllers_MnuBodegas_bodegaCrear590_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaCrear/")))
  )
  private lazy val controllers_MnuBodegas_bodegaCrear590_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaCrear(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaCrear",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaCrear/""",
      """""",
      Seq()
    )
  )

  // @LINE:898
  private lazy val controllers_MnuBodegas_vistaSelectComercialAjax591_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("vistaSelectComercialAjax/")))
  )
  private lazy val controllers_MnuBodegas_vistaSelectComercialAjax591_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.vistaSelectComercialAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "vistaSelectComercialAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """vistaSelectComercialAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:899
  private lazy val controllers_MnuBodegas_verificaNombreBodegaAjax592_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaNombreBodegaAjax/")))
  )
  private lazy val controllers_MnuBodegas_verificaNombreBodegaAjax592_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.verificaNombreBodegaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "verificaNombreBodegaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaNombreBodegaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:900
  private lazy val controllers_MnuBodegas_bodegaGraba593_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaGraba/")))
  )
  private lazy val controllers_MnuBodegas_bodegaGraba593_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """bodegaGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:901
  private lazy val controllers_MnuBodegas_bodegaModificar594_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaModificar/")))
  )
  private lazy val controllers_MnuBodegas_bodegaModificar594_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaModificar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaModificar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """bodegaModificar/""",
      """""",
      Seq()
    )
  )

  // @LINE:902
  private lazy val controllers_MnuBodegas_bodegaModifica595_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaModifica/")))
  )
  private lazy val controllers_MnuBodegas_bodegaModifica595_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """bodegaModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:903
  private lazy val controllers_MnuBodegas_bodegaElimina596_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaElimina/")))
  )
  private lazy val controllers_MnuBodegas_bodegaElimina596_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """bodegaElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:905
  private lazy val controllers_MnuBodegas_bodegaAsignaDctos597_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaAsignaDctos/")))
  )
  private lazy val controllers_MnuBodegas_bodegaAsignaDctos597_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaAsignaDctos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaAsignaDctos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaAsignaDctos/""",
      """""",
      Seq()
    )
  )

  // @LINE:906
  private lazy val controllers_MnuBodegas_bodegaFijaDctos598_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaFijaDctos/"), DynamicPart("id_bodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuBodegas_bodegaFijaDctos598_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaFijaDctos(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaFijaDctos",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaFijaDctos/""" + "$" + """id_bodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:907
  private lazy val controllers_MnuBodegas_modificarDctoArriendoAjax599_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificarDctoArriendoAjax/")))
  )
  private lazy val controllers_MnuBodegas_modificarDctoArriendoAjax599_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.modificarDctoArriendoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "modificarDctoArriendoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificarDctoArriendoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:908
  private lazy val controllers_MnuBodegas_eliminarDctoArriendoAjax600_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("eliminarDctoArriendoAjax/")))
  )
  private lazy val controllers_MnuBodegas_eliminarDctoArriendoAjax600_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.eliminarDctoArriendoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "eliminarDctoArriendoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """eliminarDctoArriendoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:909
  private lazy val controllers_MnuBodegas_grabaDctoArriendoAjax601_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabaDctoArriendoAjax/")))
  )
  private lazy val controllers_MnuBodegas_grabaDctoArriendoAjax601_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.grabaDctoArriendoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "grabaDctoArriendoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grabaDctoArriendoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:911
  private lazy val controllers_MnuBodegas_bodegaAsignaTasas602_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaAsignaTasas/")))
  )
  private lazy val controllers_MnuBodegas_bodegaAsignaTasas602_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaAsignaTasas(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaAsignaTasas",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaAsignaTasas/""",
      """""",
      Seq()
    )
  )

  // @LINE:912
  private lazy val controllers_MnuBodegas_bodegaFijaTasas603_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaFijaTasas/"), DynamicPart("id_bodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuBodegas_bodegaFijaTasas603_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaFijaTasas(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaFijaTasas",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaFijaTasas/""" + "$" + """id_bodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:913
  private lazy val controllers_MnuBodegas_modificarTasaArriendoAjax604_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificarTasaArriendoAjax/")))
  )
  private lazy val controllers_MnuBodegas_modificarTasaArriendoAjax604_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.modificarTasaArriendoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "modificarTasaArriendoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificarTasaArriendoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:914
  private lazy val controllers_MnuBodegas_eliminarTasaArriendoAjax605_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("eliminarTasaArriendoAjax/")))
  )
  private lazy val controllers_MnuBodegas_eliminarTasaArriendoAjax605_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.eliminarTasaArriendoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "eliminarTasaArriendoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """eliminarTasaArriendoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:915
  private lazy val controllers_MnuBodegas_grabaTasaArriendoAjax606_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabaTasaArriendoAjax/")))
  )
  private lazy val controllers_MnuBodegas_grabaTasaArriendoAjax606_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.grabaTasaArriendoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "grabaTasaArriendoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grabaTasaArriendoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:917
  private lazy val controllers_MnuBodegas_bodegaPrecios607_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaPrecios/")))
  )
  private lazy val controllers_MnuBodegas_bodegaPrecios607_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaPrecios(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaPrecios",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaPrecios/""",
      """""",
      Seq()
    )
  )

  // @LINE:918
  private lazy val controllers_MnuBodegas_bodegaListaPrecios608_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaListaPrecios/"), DynamicPart("id_bodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuBodegas_bodegaListaPrecios608_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaListaPrecios(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaListaPrecios",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaListaPrecios/""" + "$" + """id_bodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:919
  private lazy val controllers_MnuBodegas_actualizaListaPrecioAjax609_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualizaListaPrecioAjax/")))
  )
  private lazy val controllers_MnuBodegas_actualizaListaPrecioAjax609_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.actualizaListaPrecioAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "actualizaListaPrecioAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualizaListaPrecioAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:921
  private lazy val controllers_MnuBodegas_bodegaFactorViga610_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaFactorViga/")))
  )
  private lazy val controllers_MnuBodegas_bodegaFactorViga610_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaFactorViga(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaFactorViga",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaFactorViga/""",
      """""",
      Seq()
    )
  )

  // @LINE:922
  private lazy val controllers_MnuBodegas_actualizaFactorVigaAjax611_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualizaFactorVigaAjax/")))
  )
  private lazy val controllers_MnuBodegas_actualizaFactorVigaAjax611_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.actualizaFactorVigaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "actualizaFactorVigaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualizaFactorVigaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:924
  private lazy val controllers_MnuBodegas_bodegaContactos612_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaContactos/")))
  )
  private lazy val controllers_MnuBodegas_bodegaContactos612_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaContactos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaContactos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaContactos/""",
      """""",
      Seq()
    )
  )

  // @LINE:925
  private lazy val controllers_MnuBodegas_bodegaContactosExcel613_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaContactosExcel/")))
  )
  private lazy val controllers_MnuBodegas_bodegaContactosExcel613_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaContactosExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaContactosExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaContactosExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:926
  private lazy val controllers_MnuBodegas_bodegaModificaContacto614_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaModificaContacto/"), DynamicPart("id_bodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuBodegas_bodegaModificaContacto614_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaModificaContacto(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaModificaContacto",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaModificaContacto/""" + "$" + """id_bodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:927
  private lazy val controllers_MnuBodegas_bodegaContactoModifica615_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaContactoModifica/"), DynamicPart("id_contacto", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_bodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuBodegas_bodegaContactoModifica615_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaContactoModifica(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaContactoModifica",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaContactoModifica/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_bodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:928
  private lazy val controllers_MnuBodegas_bodegaContactoAgrega616_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaContactoAgrega/"), DynamicPart("id_bodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuBodegas_bodegaContactoAgrega616_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaContactoAgrega(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaContactoAgrega",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaContactoAgrega/""" + "$" + """id_bodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:929
  private lazy val controllers_MnuBodegas_bodegaContactoGraba617_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaContactoGraba/")))
  )
  private lazy val controllers_MnuBodegas_bodegaContactoGraba617_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaContactoGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaContactoGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """bodegaContactoGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:930
  private lazy val controllers_MnuBodegas_bodegaContactoElimina618_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaContactoElimina/"), DynamicPart("id_contacto", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_bodega", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuBodegas_bodegaContactoElimina618_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaContactoElimina(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaContactoElimina",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaContactoElimina/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_bodega<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:931
  private lazy val controllers_MnuBodegas_modificaContactoBodegaPorCampoAjax619_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaContactoBodegaPorCampoAjax/")))
  )
  private lazy val controllers_MnuBodegas_modificaContactoBodegaPorCampoAjax619_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.modificaContactoBodegaPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "modificaContactoBodegaPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaContactoBodegaPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:932
  private lazy val controllers_MnuBodegas_bodegaContactosAjax620_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaContactosAjax/")))
  )
  private lazy val controllers_MnuBodegas_bodegaContactosAjax620_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaContactosAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaContactosAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """bodegaContactosAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:933
  private lazy val controllers_MnuBodegas_bodegaFindIdForNomAjax621_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaFindIdForNomAjax/")))
  )
  private lazy val controllers_MnuBodegas_bodegaFindIdForNomAjax621_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaFindIdForNomAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaFindIdForNomAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """bodegaFindIdForNomAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:935
  private lazy val controllers_MnuBodegas_bodegaVigenteNoVigente622_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bodegaVigenteNoVigente/")))
  )
  private lazy val controllers_MnuBodegas_bodegaVigenteNoVigente622_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.bodegaVigenteNoVigente(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "bodegaVigenteNoVigente",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """bodegaVigenteNoVigente/""",
      """""",
      Seq()
    )
  )

  // @LINE:936
  private lazy val controllers_MnuBodegas_modificarBodegaEstado623_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificarBodegaEstado/"), DynamicPart("id_bodega", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("estado", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuBodegas_modificarBodegaEstado623_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_19.modificarBodegaEstado(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuBodegas",
      "modificarBodegaEstado",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """modificarBodegaEstado/""" + "$" + """id_bodega<[^/]+>,""" + "$" + """estado<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:944
  private lazy val controllers_MnuTablas_precioMantencion624_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("precioMantencion/")))
  )
  private lazy val controllers_MnuTablas_precioMantencion624_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.precioMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "precioMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """precioMantencion/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU TABLAS
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:945
  private lazy val controllers_MnuTablas_actualizaPrecioAjax625_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualizaPrecioAjax/")))
  )
  private lazy val controllers_MnuTablas_actualizaPrecioAjax625_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.actualizaPrecioAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "actualizaPrecioAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualizaPrecioAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:946
  private lazy val controllers_MnuTablas_grupoMantencion626_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grupoMantencion/")))
  )
  private lazy val controllers_MnuTablas_grupoMantencion626_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.grupoMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "grupoMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """grupoMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:947
  private lazy val controllers_MnuTablas_grupoElimina627_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grupoElimina/")))
  )
  private lazy val controllers_MnuTablas_grupoElimina627_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.grupoElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "grupoElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grupoElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:948
  private lazy val controllers_MnuTablas_grupoModifica628_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grupoModifica/")))
  )
  private lazy val controllers_MnuTablas_grupoModifica628_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.grupoModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "grupoModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grupoModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:949
  private lazy val controllers_MnuTablas_grupoAgrega629_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grupoAgrega/")))
  )
  private lazy val controllers_MnuTablas_grupoAgrega629_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.grupoAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "grupoAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """grupoAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:950
  private lazy val controllers_MnuTablas_grupoNuevo630_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grupoNuevo/")))
  )
  private lazy val controllers_MnuTablas_grupoNuevo630_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.grupoNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "grupoNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grupoNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:951
  private lazy val controllers_MnuTablas_modificaGrupoPorCampoAjax631_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaGrupoPorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaGrupoPorCampoAjax631_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaGrupoPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaGrupoPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaGrupoPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:952
  private lazy val controllers_MnuTablas_grupoNuevoAtributo632_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grupoNuevoAtributo/"), DynamicPart("id_grupo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_grupoNuevoAtributo632_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.grupoNuevoAtributo(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "grupoNuevoAtributo",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """grupoNuevoAtributo/""" + "$" + """id_grupo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:953
  private lazy val controllers_MnuTablas_grupoGrabaAtributo633_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grupoGrabaAtributo/")))
  )
  private lazy val controllers_MnuTablas_grupoGrabaAtributo633_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.grupoGrabaAtributo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "grupoGrabaAtributo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grupoGrabaAtributo/""",
      """""",
      Seq()
    )
  )

  // @LINE:954
  private lazy val controllers_MnuTablas_grupoEliminaAtributo634_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grupoEliminaAtributo/")))
  )
  private lazy val controllers_MnuTablas_grupoEliminaAtributo634_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.grupoEliminaAtributo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "grupoEliminaAtributo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grupoEliminaAtributo/""",
      """""",
      Seq()
    )
  )

  // @LINE:956
  private lazy val controllers_MnuTablas_equipoMantencion635_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("equipoMantencion/")))
  )
  private lazy val controllers_MnuTablas_equipoMantencion635_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.equipoMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "equipoMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """equipoMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:957
  private lazy val controllers_MnuTablas_equipoModifica636_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("equipoModifica/"), DynamicPart("id_equipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_equipoModifica636_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.equipoModifica(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "equipoModifica",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """equipoModifica/""" + "$" + """id_equipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:958
  private lazy val controllers_MnuTablas_equipoNuevo637_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("equipoNuevo/"), DynamicPart("id_grupo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_equipoNuevo637_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.equipoNuevo(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "equipoNuevo",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """equipoNuevo/""" + "$" + """id_grupo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:959
  private lazy val controllers_MnuTablas_cambiarGrupoEquipo638_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cambiarGrupoEquipo/"), DynamicPart("id_grup", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_equipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_cambiarGrupoEquipo638_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.cambiarGrupoEquipo(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "cambiarGrupoEquipo",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cambiarGrupoEquipo/""" + "$" + """id_grup<[^/]+>,""" + "$" + """id_equipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:960
  private lazy val controllers_MnuTablas_modificaEquipoPorCampoAjax639_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaEquipoPorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaEquipoPorCampoAjax639_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaEquipoPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaEquipoPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaEquipoPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:961
  private lazy val controllers_MnuTablas_modificaAtributoEquipoAjax640_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaAtributoEquipoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaAtributoEquipoAjax640_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaAtributoEquipoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaAtributoEquipoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaAtributoEquipoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:962
  private lazy val controllers_MnuTablas_grabaImgEquipo641_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabaImgEquipo/")))
  )
  private lazy val controllers_MnuTablas_grabaImgEquipo641_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.grabaImgEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "grabaImgEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """grabaImgEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:963
  private lazy val controllers_MnuTablas_equipoElimina642_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("equipoElimina/")))
  )
  private lazy val controllers_MnuTablas_equipoElimina642_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.equipoElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "equipoElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """equipoElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:964
  private lazy val controllers_MnuTablas_equipoGraba643_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("equipoGraba/")))
  )
  private lazy val controllers_MnuTablas_equipoGraba643_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.equipoGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "equipoGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """equipoGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:965
  private lazy val controllers_MnuTablas_verificaCodigoEquipoAjax644_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaCodigoEquipoAjax/")))
  )
  private lazy val controllers_MnuTablas_verificaCodigoEquipoAjax644_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.verificaCodigoEquipoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "verificaCodigoEquipoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaCodigoEquipoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:966
  private lazy val controllers_MnuTablas_equipoDescripcionAjax645_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("equipoDescripcionAjax/")))
  )
  private lazy val controllers_MnuTablas_equipoDescripcionAjax645_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.equipoDescripcionAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "equipoDescripcionAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """equipoDescripcionAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:967
  private lazy val controllers_MnuTablas_equipoFindIdForCodAjax646_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("equipoFindIdForCodAjax/")))
  )
  private lazy val controllers_MnuTablas_equipoFindIdForCodAjax646_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.equipoFindIdForCodAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "equipoFindIdForCodAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """equipoFindIdForCodAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:970
  private lazy val controllers_MnuTablas_proyectoMantencion647_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoMantencion/")))
  )
  private lazy val controllers_MnuTablas_proyectoMantencion647_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proyectoMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proyectoMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proyectoMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:971
  private lazy val controllers_MnuTablas_proyectoModifica648_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoModifica/"), DynamicPart("id_proyecto", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_proyectoModifica648_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proyectoModifica(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proyectoModifica",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proyectoModifica/""" + "$" + """id_proyecto<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:972
  private lazy val controllers_MnuTablas_selComuna1Ajax649_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("selComuna1Ajax/")))
  )
  private lazy val controllers_MnuTablas_selComuna1Ajax649_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.selComuna1Ajax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "selComuna1Ajax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """selComuna1Ajax/""",
      """""",
      Seq()
    )
  )

  // @LINE:973
  private lazy val controllers_MnuTablas_selComuna2Ajax650_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("selComuna2Ajax/")))
  )
  private lazy val controllers_MnuTablas_selComuna2Ajax650_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.selComuna2Ajax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "selComuna2Ajax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """selComuna2Ajax/""",
      """""",
      Seq()
    )
  )

  // @LINE:974
  private lazy val controllers_MnuTablas_modificaProyectoPorCampoAjax651_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaProyectoPorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaProyectoPorCampoAjax651_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaProyectoPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaProyectoPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaProyectoPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:975
  private lazy val controllers_MnuTablas_proyectoContactoModifica652_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoContactoModifica/"), DynamicPart("id_contacto", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_proyecto", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_proyectoContactoModifica652_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proyectoContactoModifica(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proyectoContactoModifica",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proyectoContactoModifica/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_proyecto<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:976
  private lazy val controllers_MnuTablas_proyectoContactoAgrega653_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoContactoAgrega/"), DynamicPart("id_proyecto", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_proyectoContactoAgrega653_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proyectoContactoAgrega(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proyectoContactoAgrega",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proyectoContactoAgrega/""" + "$" + """id_proyecto<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:977
  private lazy val controllers_MnuTablas_proyectoContactoGraba654_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoContactoGraba/")))
  )
  private lazy val controllers_MnuTablas_proyectoContactoGraba654_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proyectoContactoGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proyectoContactoGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proyectoContactoGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:978
  private lazy val controllers_MnuTablas_proyectoContactoElimina655_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoContactoElimina/"), DynamicPart("id_contacto", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_proyecto", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_proyectoContactoElimina655_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proyectoContactoElimina(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proyectoContactoElimina",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proyectoContactoElimina/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_proyecto<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:979
  private lazy val controllers_MnuTablas_modificaContactoProyectoPorCampoAjax656_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaContactoProyectoPorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaContactoProyectoPorCampoAjax656_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaContactoProyectoPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaContactoProyectoPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaContactoProyectoPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:980
  private lazy val controllers_MnuTablas_proyectoElimina657_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoElimina/")))
  )
  private lazy val controllers_MnuTablas_proyectoElimina657_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proyectoElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proyectoElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proyectoElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:981
  private lazy val controllers_MnuTablas_proyectoAgrega658_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoAgrega/")))
  )
  private lazy val controllers_MnuTablas_proyectoAgrega658_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proyectoAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proyectoAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proyectoAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:982
  private lazy val controllers_MnuTablas_proyectoGraba659_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoGraba/")))
  )
  private lazy val controllers_MnuTablas_proyectoGraba659_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proyectoGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proyectoGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proyectoGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:983
  private lazy val controllers_MnuTablas_proyectoGrabaAjax660_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoGrabaAjax/")))
  )
  private lazy val controllers_MnuTablas_proyectoGrabaAjax660_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proyectoGrabaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proyectoGrabaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proyectoGrabaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:984
  private lazy val controllers_MnuTablas_verificaNickProyectoAjax661_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaNickProyectoAjax/")))
  )
  private lazy val controllers_MnuTablas_verificaNickProyectoAjax661_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.verificaNickProyectoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "verificaNickProyectoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaNickProyectoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:985
  private lazy val controllers_MnuTablas_proyectoContactosAjax662_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoContactosAjax/")))
  )
  private lazy val controllers_MnuTablas_proyectoContactosAjax662_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proyectoContactosAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proyectoContactosAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proyectoContactosAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:986
  private lazy val controllers_MnuTablas_proyectoFindIdForNickNameAjax663_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoFindIdForNickNameAjax/")))
  )
  private lazy val controllers_MnuTablas_proyectoFindIdForNickNameAjax663_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proyectoFindIdForNickNameAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proyectoFindIdForNickNameAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proyectoFindIdForNickNameAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:989
  private lazy val controllers_MnuTablas_clienteMantencion664_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteMantencion/")))
  )
  private lazy val controllers_MnuTablas_clienteMantencion664_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """clienteMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:990
  private lazy val controllers_MnuTablas_clienteMantencionExcel665_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteMantencionExcel/")))
  )
  private lazy val controllers_MnuTablas_clienteMantencionExcel665_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteMantencionExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteMantencionExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """clienteMantencionExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:991
  private lazy val controllers_MnuTablas_clienteModifica666_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteModifica/"), DynamicPart("id_cliente", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_clienteModifica666_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteModifica(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteModifica",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """clienteModifica/""" + "$" + """id_cliente<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:992
  private lazy val controllers_MnuTablas_modificaClientePorCampoAjax667_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaClientePorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaClientePorCampoAjax667_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaClientePorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaClientePorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaClientePorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:993
  private lazy val controllers_MnuTablas_selComuna3Ajax668_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("selComuna3Ajax/")))
  )
  private lazy val controllers_MnuTablas_selComuna3Ajax668_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.selComuna3Ajax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "selComuna3Ajax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """selComuna3Ajax/""",
      """""",
      Seq()
    )
  )

  // @LINE:994
  private lazy val controllers_MnuTablas_clienteContactoModifica669_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteContactoModifica/"), DynamicPart("id_contacto", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_cliente", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_clienteContactoModifica669_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteContactoModifica(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteContactoModifica",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """clienteContactoModifica/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_cliente<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:995
  private lazy val controllers_MnuTablas_clienteContactoAgrega670_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteContactoAgrega/"), DynamicPart("id_cliente", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_clienteContactoAgrega670_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteContactoAgrega(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteContactoAgrega",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """clienteContactoAgrega/""" + "$" + """id_cliente<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:996
  private lazy val controllers_MnuTablas_clienteContactoGraba671_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteContactoGraba/")))
  )
  private lazy val controllers_MnuTablas_clienteContactoGraba671_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteContactoGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteContactoGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """clienteContactoGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:997
  private lazy val controllers_MnuTablas_clienteContactoElimina672_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteContactoElimina/"), DynamicPart("id_contacto", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_cliente", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_clienteContactoElimina672_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteContactoElimina(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteContactoElimina",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """clienteContactoElimina/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_cliente<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:998
  private lazy val controllers_MnuTablas_modificaContactoClientePorCampoAjax673_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaContactoClientePorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaContactoClientePorCampoAjax673_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaContactoClientePorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaContactoClientePorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaContactoClientePorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:999
  private lazy val controllers_MnuTablas_clienteElimina674_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteElimina/")))
  )
  private lazy val controllers_MnuTablas_clienteElimina674_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """clienteElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:1000
  private lazy val controllers_MnuTablas_clienteAgrega675_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteAgrega/")))
  )
  private lazy val controllers_MnuTablas_clienteAgrega675_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """clienteAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:1001
  private lazy val controllers_MnuTablas_clienteGraba676_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteGraba/")))
  )
  private lazy val controllers_MnuTablas_clienteGraba676_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """clienteGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:1002
  private lazy val controllers_MnuTablas_clienteGrabaAjax677_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteGrabaAjax/")))
  )
  private lazy val controllers_MnuTablas_clienteGrabaAjax677_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteGrabaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteGrabaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """clienteGrabaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1003
  private lazy val controllers_MnuTablas_verificaNickClienteAjax678_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaNickClienteAjax/")))
  )
  private lazy val controllers_MnuTablas_verificaNickClienteAjax678_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.verificaNickClienteAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "verificaNickClienteAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaNickClienteAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1004
  private lazy val controllers_MnuTablas_clienteContactosAjax679_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteContactosAjax/")))
  )
  private lazy val controllers_MnuTablas_clienteContactosAjax679_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteContactosAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteContactosAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """clienteContactosAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1005
  private lazy val controllers_MnuTablas_clienteFindIdForNickNameAjax680_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteFindIdForNickNameAjax/")))
  )
  private lazy val controllers_MnuTablas_clienteFindIdForNickNameAjax680_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.clienteFindIdForNickNameAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "clienteFindIdForNickNameAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """clienteFindIdForNickNameAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1008
  private lazy val controllers_MnuTablas_fabricaMantencion681_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("fabricaMantencion/")))
  )
  private lazy val controllers_MnuTablas_fabricaMantencion681_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.fabricaMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "fabricaMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """fabricaMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:1009
  private lazy val controllers_MnuTablas_fabricaModifica682_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("fabricaModifica/"), DynamicPart("id_fabrica", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_fabricaModifica682_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.fabricaModifica(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "fabricaModifica",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """fabricaModifica/""" + "$" + """id_fabrica<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:1010
  private lazy val controllers_MnuTablas_modificaFabricaPorCampoAjax683_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaFabricaPorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaFabricaPorCampoAjax683_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaFabricaPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaFabricaPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaFabricaPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1011
  private lazy val controllers_MnuTablas_selComuna4Ajax684_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("selComuna4Ajax/")))
  )
  private lazy val controllers_MnuTablas_selComuna4Ajax684_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.selComuna4Ajax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "selComuna4Ajax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """selComuna4Ajax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1012
  private lazy val controllers_MnuTablas_fabricaContactoModifica685_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("fabricaContactoModifica/"), DynamicPart("id_contacto", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_fabrica", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_fabricaContactoModifica685_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.fabricaContactoModifica(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "fabricaContactoModifica",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """fabricaContactoModifica/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_fabrica<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:1013
  private lazy val controllers_MnuTablas_fabricaContactoAgrega686_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("fabricaContactoAgrega/"), DynamicPart("id_fabrica", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_fabricaContactoAgrega686_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.fabricaContactoAgrega(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "fabricaContactoAgrega",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """fabricaContactoAgrega/""" + "$" + """id_fabrica<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:1014
  private lazy val controllers_MnuTablas_fabricaContactoGraba687_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("fabricaContactoGraba/")))
  )
  private lazy val controllers_MnuTablas_fabricaContactoGraba687_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.fabricaContactoGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "fabricaContactoGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """fabricaContactoGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:1015
  private lazy val controllers_MnuTablas_fabricaContactoElimina688_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("fabricaContactoElimina/"), DynamicPart("id_contacto", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_fabrica", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_fabricaContactoElimina688_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.fabricaContactoElimina(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "fabricaContactoElimina",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """fabricaContactoElimina/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_fabrica<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:1016
  private lazy val controllers_MnuTablas_modificaContactoFabricaPorCampoAjax689_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaContactoFabricaPorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaContactoFabricaPorCampoAjax689_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaContactoFabricaPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaContactoFabricaPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaContactoFabricaPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1017
  private lazy val controllers_MnuTablas_fabricaElimina690_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("fabricaElimina/")))
  )
  private lazy val controllers_MnuTablas_fabricaElimina690_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.fabricaElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "fabricaElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """fabricaElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:1018
  private lazy val controllers_MnuTablas_fabricaAgrega691_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("fabricaAgrega/")))
  )
  private lazy val controllers_MnuTablas_fabricaAgrega691_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.fabricaAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "fabricaAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """fabricaAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:1019
  private lazy val controllers_MnuTablas_fabricaGraba692_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("fabricaGraba/")))
  )
  private lazy val controllers_MnuTablas_fabricaGraba692_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.fabricaGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "fabricaGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """fabricaGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:1020
  private lazy val controllers_MnuTablas_verificaNickFabricaAjax693_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaNickFabricaAjax/")))
  )
  private lazy val controllers_MnuTablas_verificaNickFabricaAjax693_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.verificaNickFabricaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "verificaNickFabricaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaNickFabricaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1022
  private lazy val controllers_MnuTablas_allContactosCliProvFabrExcel694_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("allContactosCliProvFabrExcel/")))
  )
  private lazy val controllers_MnuTablas_allContactosCliProvFabrExcel694_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.allContactosCliProvFabrExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "allContactosCliProvFabrExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """allContactosCliProvFabrExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:1024
  private lazy val controllers_MnuTablas_transportistaMantencion695_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("transportistaMantencion/")))
  )
  private lazy val controllers_MnuTablas_transportistaMantencion695_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.transportistaMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "transportistaMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """transportistaMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:1027
  private lazy val controllers_MnuTablas_proveedorMantencion696_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proveedorMantencion/")))
  )
  private lazy val controllers_MnuTablas_proveedorMantencion696_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proveedorMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proveedorMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proveedorMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:1028
  private lazy val controllers_MnuTablas_proveedorModifica697_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proveedorModifica/"), DynamicPart("id_proveedor", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_proveedorModifica697_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proveedorModifica(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proveedorModifica",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proveedorModifica/""" + "$" + """id_proveedor<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:1029
  private lazy val controllers_MnuTablas_modificaProveedorPorCampoAjax698_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaProveedorPorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaProveedorPorCampoAjax698_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaProveedorPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaProveedorPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaProveedorPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1030
  private lazy val controllers_MnuTablas_selComuna5Ajax699_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("selComuna5Ajax/")))
  )
  private lazy val controllers_MnuTablas_selComuna5Ajax699_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.selComuna5Ajax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "selComuna5Ajax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """selComuna5Ajax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1031
  private lazy val controllers_MnuTablas_proveedorContactoModifica700_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proveedorContactoModifica/"), DynamicPart("id_contacto", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_proveedor", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_proveedorContactoModifica700_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proveedorContactoModifica(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proveedorContactoModifica",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proveedorContactoModifica/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_proveedor<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:1032
  private lazy val controllers_MnuTablas_proveedorContactoAgrega701_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proveedorContactoAgrega/"), DynamicPart("id_proveedor", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_proveedorContactoAgrega701_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proveedorContactoAgrega(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proveedorContactoAgrega",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proveedorContactoAgrega/""" + "$" + """id_proveedor<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:1033
  private lazy val controllers_MnuTablas_proveedorContactoGraba702_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proveedorContactoGraba/")))
  )
  private lazy val controllers_MnuTablas_proveedorContactoGraba702_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proveedorContactoGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proveedorContactoGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proveedorContactoGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:1034
  private lazy val controllers_MnuTablas_proveedorContactoElimina703_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proveedorContactoElimina/"), DynamicPart("id_contacto", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_proveedor", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_proveedorContactoElimina703_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proveedorContactoElimina(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proveedorContactoElimina",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proveedorContactoElimina/""" + "$" + """id_contacto<[^/]+>,""" + "$" + """id_proveedor<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:1035
  private lazy val controllers_MnuTablas_modificaContactoProveedorPorCampoAjax704_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaContactoProveedorPorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaContactoProveedorPorCampoAjax704_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaContactoProveedorPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaContactoProveedorPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaContactoProveedorPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1036
  private lazy val controllers_MnuTablas_proveedorElimina705_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proveedorElimina/")))
  )
  private lazy val controllers_MnuTablas_proveedorElimina705_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proveedorElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proveedorElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proveedorElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:1037
  private lazy val controllers_MnuTablas_proveedorAgrega706_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proveedorAgrega/")))
  )
  private lazy val controllers_MnuTablas_proveedorAgrega706_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proveedorAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proveedorAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proveedorAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:1038
  private lazy val controllers_MnuTablas_proveedorGraba707_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proveedorGraba/")))
  )
  private lazy val controllers_MnuTablas_proveedorGraba707_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proveedorGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proveedorGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proveedorGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:1039
  private lazy val controllers_MnuTablas_proveedorGrabaAjax708_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proveedorGrabaAjax/")))
  )
  private lazy val controllers_MnuTablas_proveedorGrabaAjax708_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.proveedorGrabaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "proveedorGrabaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proveedorGrabaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1040
  private lazy val controllers_MnuTablas_verificaNickProveedorAjax709_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaNickProveedorAjax/")))
  )
  private lazy val controllers_MnuTablas_verificaNickProveedorAjax709_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.verificaNickProveedorAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "verificaNickProveedorAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaNickProveedorAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1042
  private lazy val controllers_MnuTablas_tipoEstadoMantencion710_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoEstadoMantencion/")))
  )
  private lazy val controllers_MnuTablas_tipoEstadoMantencion710_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.tipoEstadoMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "tipoEstadoMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """tipoEstadoMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:1043
  private lazy val controllers_MnuTablas_tipoEstadoModifica711_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoEstadoModifica/"), DynamicPart("id_estado", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_tipoEstadoModifica711_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.tipoEstadoModifica(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "tipoEstadoModifica",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """tipoEstadoModifica/""" + "$" + """id_estado<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:1044
  private lazy val controllers_MnuTablas_modificaTipoEstadoPorCampoAjax712_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaTipoEstadoPorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaTipoEstadoPorCampoAjax712_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaTipoEstadoPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaTipoEstadoPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaTipoEstadoPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1045
  private lazy val controllers_MnuTablas_tipoReparacionModifica713_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoReparacionModifica/"), DynamicPart("id_tipoRep", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_tipoEst", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_tipoReparacionModifica713_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.tipoReparacionModifica(fakeValue[Long], fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "tipoReparacionModifica",
      Seq(classOf[Long], classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """tipoReparacionModifica/""" + "$" + """id_tipoRep<[^/]+>,""" + "$" + """id_tipoEst<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:1046
  private lazy val controllers_MnuTablas_tipoReparacionAgrega714_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoReparacionAgrega/"), DynamicPart("id_tipoEst", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuTablas_tipoReparacionAgrega714_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.tipoReparacionAgrega(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "tipoReparacionAgrega",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """tipoReparacionAgrega/""" + "$" + """id_tipoEst<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:1047
  private lazy val controllers_MnuTablas_verificaSiglaTipoReparacionAjax715_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaSiglaTipoReparacionAjax/")))
  )
  private lazy val controllers_MnuTablas_verificaSiglaTipoReparacionAjax715_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.verificaSiglaTipoReparacionAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "verificaSiglaTipoReparacionAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaSiglaTipoReparacionAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1048
  private lazy val controllers_MnuTablas_tipoReparacionGraba716_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoReparacionGraba/")))
  )
  private lazy val controllers_MnuTablas_tipoReparacionGraba716_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.tipoReparacionGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "tipoReparacionGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tipoReparacionGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:1049
  private lazy val controllers_MnuTablas_tipoReparacionElimina717_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoReparacionElimina/")))
  )
  private lazy val controllers_MnuTablas_tipoReparacionElimina717_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.tipoReparacionElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "tipoReparacionElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tipoReparacionElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:1050
  private lazy val controllers_MnuTablas_modificaTipoReparacionPorCampoAjax718_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaTipoReparacionPorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaTipoReparacionPorCampoAjax718_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaTipoReparacionPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaTipoReparacionPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaTipoReparacionPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1051
  private lazy val controllers_MnuTablas_tipoEstadoElimina719_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoEstadoElimina/")))
  )
  private lazy val controllers_MnuTablas_tipoEstadoElimina719_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.tipoEstadoElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "tipoEstadoElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tipoEstadoElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:1052
  private lazy val controllers_MnuTablas_tipoEstadoAgrega720_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoEstadoAgrega/")))
  )
  private lazy val controllers_MnuTablas_tipoEstadoAgrega720_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.tipoEstadoAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "tipoEstadoAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """tipoEstadoAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:1053
  private lazy val controllers_MnuTablas_tipoEstadoGraba721_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tipoEstadoGraba/")))
  )
  private lazy val controllers_MnuTablas_tipoEstadoGraba721_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.tipoEstadoGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "tipoEstadoGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tipoEstadoGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:1054
  private lazy val controllers_MnuTablas_verificaSiglaTipoEstadoAjax722_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaSiglaTipoEstadoAjax/")))
  )
  private lazy val controllers_MnuTablas_verificaSiglaTipoEstadoAjax722_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.verificaSiglaTipoEstadoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "verificaSiglaTipoEstadoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaSiglaTipoEstadoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1056
  private lazy val controllers_MnuTablas_decimalNumero723_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("decimalNumero/")))
  )
  private lazy val controllers_MnuTablas_decimalNumero723_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.decimalNumero(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "decimalNumero",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """decimalNumero/""",
      """""",
      Seq()
    )
  )

  // @LINE:1057
  private lazy val controllers_MnuTablas_decimalModificaAjax724_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("decimalModificaAjax/")))
  )
  private lazy val controllers_MnuTablas_decimalModificaAjax724_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.decimalModificaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "decimalModificaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """decimalModificaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1059
  private lazy val controllers_MnuTablas_usuarioMantencion725_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("usuarioMantencion/")))
  )
  private lazy val controllers_MnuTablas_usuarioMantencion725_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.usuarioMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "usuarioMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """usuarioMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:1060
  private lazy val controllers_MnuTablas_usuarioModifica726_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("usuarioModifica/")))
  )
  private lazy val controllers_MnuTablas_usuarioModifica726_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.usuarioModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "usuarioModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """usuarioModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:1061
  private lazy val controllers_MnuTablas_modificaUsuarioPorCampoAjax727_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaUsuarioPorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaUsuarioPorCampoAjax727_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modificaUsuarioPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modificaUsuarioPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaUsuarioPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1062
  private lazy val controllers_MnuTablas_usuarioElimina728_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("usuarioElimina/")))
  )
  private lazy val controllers_MnuTablas_usuarioElimina728_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.usuarioElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "usuarioElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """usuarioElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:1063
  private lazy val controllers_MnuTablas_usuarioAgrega729_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("usuarioAgrega/")))
  )
  private lazy val controllers_MnuTablas_usuarioAgrega729_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.usuarioAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "usuarioAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """usuarioAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:1064
  private lazy val controllers_MnuTablas_usuarioGraba730_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("usuarioGraba/")))
  )
  private lazy val controllers_MnuTablas_usuarioGraba730_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.usuarioGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "usuarioGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """usuarioGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:1065
  private lazy val controllers_MnuTablas_verificaUserNameUsuarioAjax731_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verificaUserNameUsuarioAjax/")))
  )
  private lazy val controllers_MnuTablas_verificaUserNameUsuarioAjax731_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.verificaUserNameUsuarioAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "verificaUserNameUsuarioAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verificaUserNameUsuarioAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1066
  private lazy val controllers_MnuTablas_usuarioListBodegaEmpresa732_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("usuarioListBodegaEmpresa/")))
  )
  private lazy val controllers_MnuTablas_usuarioListBodegaEmpresa732_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.usuarioListBodegaEmpresa(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "usuarioListBodegaEmpresa",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """usuarioListBodegaEmpresa/""",
      """""",
      Seq()
    )
  )

  // @LINE:1067
  private lazy val controllers_MnuTablas_usuarioBodegaSelect733_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("usuarioBodegaSelect/")))
  )
  private lazy val controllers_MnuTablas_usuarioBodegaSelect733_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.usuarioBodegaSelect(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "usuarioBodegaSelect",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """usuarioBodegaSelect/""",
      """""",
      Seq()
    )
  )

  // @LINE:1068
  private lazy val controllers_MnuTablas_usuarioBodegaEmpresaElimina734_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("usuarioBodegaEmpresaElimina/")))
  )
  private lazy val controllers_MnuTablas_usuarioBodegaEmpresaElimina734_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.usuarioBodegaEmpresaElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "usuarioBodegaEmpresaElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """usuarioBodegaEmpresaElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:1069
  private lazy val controllers_MnuTablas_modVigUsuarioAjax735_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modVigUsuarioAjax/")))
  )
  private lazy val controllers_MnuTablas_modVigUsuarioAjax735_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.modVigUsuarioAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "modVigUsuarioAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modVigUsuarioAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:1072
  private lazy val controllers_MnuTablas_registroDeCambios736_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("registroDeCambios/")))
  )
  private lazy val controllers_MnuTablas_registroDeCambios736_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_1.registroDeCambios(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.MnuTablas",
      "registroDeCambios",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """registroDeCambios/""",
      """""",
      Seq()
    )
  )

  // @LINE:1076
  private lazy val controllers_Assets_versioned737_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""", encodeable=false)))
  )
  private lazy val controllers_Assets_versioned737_invoker = createInvoker(
    Assets_15.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:1080
  private val prefixed_routes2_Routes_0_738 = Include(routes2_Routes_0.withPrefix(this.prefix + (if (this.prefix.endsWith("/")) "" else "/") + "routes2"))

  // @LINE:1081
  private val prefixed_routes3_Routes_1_739 = Include(routes3_Routes_1.withPrefix(this.prefix + (if (this.prefix.endsWith("/")) "" else "/") + "routes3"))

  // @LINE:1085
  private lazy val controllers_HomeController_onUrlNotFind740_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), DynamicPart("path", """.+""", encodeable=false)))
  )
  private lazy val controllers_HomeController_onUrlNotFind740_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_18.onUrlNotFind(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "onUrlNotFind",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """""" + "$" + """path<.+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:8
    case controllers_HomeController_ping0_route(params@_) =>
      call { 
        controllers_HomeController_ping0_invoker.call(HomeController_18.ping())
      }
  
    // @LINE:14
    case controllers_Apis_Mada_APIs1_route(params@_) =>
      call { 
        controllers_Apis_Mada_APIs1_invoker.call(
          req => Apis_2.Mada_APIs(req))
      }
  
    // @LINE:15
    case controllers_Apis_Mada_APIs2_route(params@_) =>
      call { 
        controllers_Apis_Mada_APIs2_invoker.call(
          req => Apis_2.Mada_APIs(req))
      }
  
    // @LINE:17
    case controllers_Apis_authenticar3_route(params@_) =>
      call { 
        controllers_Apis_authenticar3_invoker.call(
          req => Apis_2.authenticar(req))
      }
  
    // @LINE:18
    case controllers_Apis_pingToken4_route(params@_) =>
      call { 
        controllers_Apis_pingToken4_invoker.call(
          req => Apis_2.pingToken(req))
      }
  
    // @LINE:20
    case controllers_Apis_listadoDeBodegas5_route(params@_) =>
      call { 
        controllers_Apis_listadoDeBodegas5_invoker.call(
          req => Apis_2.listadoDeBodegas(req))
      }
  
    // @LINE:21
    case controllers_Apis_movimientosEntreFechas6_route(params@_) =>
      call(params.fromQuery[String]("desdeAAMMDD", None), params.fromQuery[String]("hastaAAMMDD", None)) { (desdeAAMMDD, hastaAAMMDD) =>
        controllers_Apis_movimientosEntreFechas6_invoker.call(
          req => Apis_2.movimientosEntreFechas(req, desdeAAMMDD, hastaAAMMDD))
      }
  
    // @LINE:22
    case controllers_Apis_findBodega7_route(params@_) =>
      call(params.fromQuery[Long]("id_bodegaEmpresa", None)) { (id_bodegaEmpresa) =>
        controllers_Apis_findBodega7_invoker.call(
          req => Apis_2.findBodega(req, id_bodegaEmpresa))
      }
  
    // @LINE:23
    case controllers_Apis_findCliente8_route(params@_) =>
      call(params.fromQuery[Long]("id_cliente", None)) { (id_cliente) =>
        controllers_Apis_findCliente8_invoker.call(
          req => Apis_2.findCliente(req, id_cliente))
      }
  
    // @LINE:24
    case controllers_Apis_findProyecto9_route(params@_) =>
      call(params.fromQuery[Long]("id_proyecto", None)) { (id_proyecto) =>
        controllers_Apis_findProyecto9_invoker.call(
          req => Apis_2.findProyecto(req, id_proyecto))
      }
  
    // @LINE:25
    case controllers_Apis_inventarios_al_dia10_route(params@_) =>
      call(params.fromQuery[String]("fechaCorte", None)) { (fechaCorte) =>
        controllers_Apis_inventarios_al_dia10_invoker.call(
          req => Apis_2.inventarios_al_dia(req, fechaCorte))
      }
  
    // @LINE:27
    case controllers_Apis_estadoDePagoPorPeriodo11_route(params@_) =>
      call(params.fromQuery[String]("desdeAAMMDD", None), params.fromQuery[String]("hastaAAMMDD", None), params.fromQuery[Double]("uf", None), params.fromQuery[Double]("usd", None), params.fromQuery[Double]("eur", None)) { (desdeAAMMDD, hastaAAMMDD, uf, usd, eur) =>
        controllers_Apis_estadoDePagoPorPeriodo11_invoker.call(
          req => Apis_2.estadoDePagoPorPeriodo(req, desdeAAMMDD, hastaAAMMDD, uf, usd, eur))
      }
  
    // @LINE:29
    case controllers_Apis_totalesEPporPeriodo12_route(params@_) =>
      call(params.fromQuery[String]("desdeAAMMDD", None), params.fromQuery[String]("hastaAAMMDD", None), params.fromQuery[Double]("uf", None), params.fromQuery[Double]("usd", None), params.fromQuery[Double]("eur", None)) { (desdeAAMMDD, hastaAAMMDD, uf, usd, eur) =>
        controllers_Apis_totalesEPporPeriodo12_invoker.call(
          req => Apis_2.totalesEPporPeriodo(req, desdeAAMMDD, hastaAAMMDD, uf, usd, eur))
      }
  
    // @LINE:30
    case controllers_Apis_matrizInventarioPorCoti13_route(params@_) =>
      call(params.fromQuery[String]("fechaCorte", None)) { (fechaCorte) =>
        controllers_Apis_matrizInventarioPorCoti13_invoker.call(
          req => Apis_2.matrizInventarioPorCoti(req, fechaCorte))
      }
  
    // @LINE:31
    case controllers_Apis_consolidadoPorGrupoMeses14_route(params@_) =>
      call(params.fromQuery[String]("fechaCorte", None), params.fromQuery[Long]("cantMeses", None)) { (fechaCorte, cantMeses) =>
        controllers_Apis_consolidadoPorGrupoMeses14_invoker.call(
          req => Apis_2.consolidadoPorGrupoMeses(req, fechaCorte, cantMeses))
      }
  
    // @LINE:32
    case controllers_Apis_consolidadoPorEquipoMeses15_route(params@_) =>
      call(params.fromQuery[String]("fechaCorte", None), params.fromQuery[Long]("cantMeses", None)) { (fechaCorte, cantMeses) =>
        controllers_Apis_consolidadoPorEquipoMeses15_invoker.call(
          req => Apis_2.consolidadoPorEquipoMeses(req, fechaCorte, cantMeses))
      }
  
    // @LINE:33
    case controllers_Apis_ajustesPorPeriodo16_route(params@_) =>
      call(params.fromQuery[String]("desdeAAMMDD", None), params.fromQuery[String]("hastaAAMMDD", None)) { (desdeAAMMDD, hastaAAMMDD) =>
        controllers_Apis_ajustesPorPeriodo16_invoker.call(
          req => Apis_2.ajustesPorPeriodo(req, desdeAAMMDD, hastaAAMMDD))
      }
  
    // @LINE:34
    case controllers_Apis_estadoBodegasJson17_route(params@_) =>
      call { 
        controllers_Apis_estadoBodegasJson17_invoker.call(
          req => Apis_2.estadoBodegasJson(req))
      }
  
    // @LINE:35
    case controllers_Apis_hoheTodoResumenJson18_route(params@_) =>
      call(params.fromQuery[String]("desdeAAMMDD", None), params.fromQuery[String]("hastaAAMMDD", None)) { (desdeAAMMDD, hastaAAMMDD) =>
        controllers_Apis_hoheTodoResumenJson18_invoker.call(
          req => Apis_2.hoheTodoResumenJson(req, desdeAAMMDD, hastaAAMMDD))
      }
  
    // @LINE:38
    case controllers_MnuMantencion_mantWebInicio019_route(params@_) =>
      call(params.fromQuery[Long]("id_equipo", Some(0))) { (id_equipo) =>
        controllers_MnuMantencion_mantWebInicio019_invoker.call(MnuMantencion_16.mantWebInicio0(id_equipo))
      }
  
    // @LINE:45
    case controllers_HomeController_inicio20_route(params@_) =>
      call { 
        controllers_HomeController_inicio20_invoker.call(HomeController_18.inicio())
      }
  
    // @LINE:46
    case controllers_HomeController_vistaPrincipal21_route(params@_) =>
      call { 
        controllers_HomeController_vistaPrincipal21_invoker.call(
          req => HomeController_18.vistaPrincipal(req))
      }
  
    // @LINE:48
    case controllers_HomeController_home22_route(params@_) =>
      call { 
        controllers_HomeController_home22_invoker.call(
          req => HomeController_18.home(req))
      }
  
    // @LINE:49
    case controllers_HomeController_homeOdoVentasWeb23_route(params@_) =>
      call { 
        controllers_HomeController_homeOdoVentasWeb23_invoker.call(
          req => HomeController_18.homeOdoVentasWeb(req))
      }
  
    // @LINE:50
    case controllers_HomeController_homeOdoAutorizaVentasWeb24_route(params@_) =>
      call { 
        controllers_HomeController_homeOdoAutorizaVentasWeb24_invoker.call(
          req => HomeController_18.homeOdoAutorizaVentasWeb(req))
      }
  
    // @LINE:52
    case controllers_HomeController_usuarioModalUpdate225_route(params@_) =>
      call { 
        controllers_HomeController_usuarioModalUpdate225_invoker.call(
          req => HomeController_18.usuarioModalUpdate2(req))
      }
  
    // @LINE:53
    case controllers_HomeController_usuarioValida226_route(params@_) =>
      call { 
        controllers_HomeController_usuarioValida226_invoker.call(
          req => HomeController_18.usuarioValida2(req))
      }
  
    // @LINE:54
    case controllers_HomeController_usuarioUpdate227_route(params@_) =>
      call { 
        controllers_HomeController_usuarioUpdate227_invoker.call(
          req => HomeController_18.usuarioUpdate2(req))
      }
  
    // @LINE:55
    case controllers_HomeController_recuperarClave28_route(params@_) =>
      call { 
        controllers_HomeController_recuperarClave28_invoker.call(
          req => HomeController_18.recuperarClave(req))
      }
  
    // @LINE:62
    case controllers_HomeController_login29_route(params@_) =>
      call { 
        controllers_HomeController_login29_invoker.call(
          req => HomeController_18.login(req))
      }
  
    // @LINE:63
    case controllers_HomeController_callback30_route(params@_) =>
      call(params.fromQuery[String]("code", None), params.fromQuery[String]("state", None)) { (code, state) =>
        controllers_HomeController_callback30_invoker.call(
          req => HomeController_18.callback(req, code, state))
      }
  
    // @LINE:65
    case controllers_HomeController_vistaAdminCallback31_route(params@_) =>
      call { 
        controllers_HomeController_vistaAdminCallback31_invoker.call(
          req => HomeController_18.vistaAdminCallback(req))
      }
  
    // @LINE:66
    case controllers_HomeController_inicio2Admin32_route(params@_) =>
      call(params.fromPath[String]("db", None)) { (db) =>
        controllers_HomeController_inicio2Admin32_invoker.call(
          req => HomeController_18.inicio2Admin(db, req))
      }
  
    // @LINE:73
    case controllers_HomeController_viewImg33_route(params@_) =>
      call(params.fromPath[String]("img", None)) { (img) =>
        controllers_HomeController_viewImg33_invoker.call(
          req => HomeController_18.viewImg(img, req))
      }
  
    // @LINE:74
    case controllers_HomeController_viewImgAlbum34_route(params@_) =>
      call(params.fromPath[String]("img", None), params.fromPath[String]("carp", None)) { (img, carp) =>
        controllers_HomeController_viewImgAlbum34_invoker.call(
          req => HomeController_18.viewImgAlbum(img, carp, req))
      }
  
    // @LINE:75
    case controllers_HomeController_showPdf35_route(params@_) =>
      call(params.fromPath[String]("fileNamePdf", None)) { (fileNamePdf) =>
        controllers_HomeController_showPdf35_invoker.call(
          req => HomeController_18.showPdf(fileNamePdf, req))
      }
  
    // @LINE:76
    case controllers_HomeController_showPdfmasAlbum36_route(params@_) =>
      call(params.fromPath[Long]("id_guia", None), params.fromPath[String]("fileNamePdf", None)) { (id_guia, fileNamePdf) =>
        controllers_HomeController_showPdfmasAlbum36_invoker.call(
          req => HomeController_18.showPdfmasAlbum(id_guia, fileNamePdf, req))
      }
  
    // @LINE:78
    case controllers_HomeController_showPdfManuales37_route(params@_) =>
      call(params.fromPath[String]("fileNamePdf", None)) { (fileNamePdf) =>
        controllers_HomeController_showPdfManuales37_invoker.call(
          req => HomeController_18.showPdfManuales(fileNamePdf, req))
      }
  
    // @LINE:79
    case controllers_HomeController_downloadPDF38_route(params@_) =>
      call { 
        controllers_HomeController_downloadPDF38_invoker.call(
          req => HomeController_18.downloadPDF(req))
      }
  
    // @LINE:80
    case controllers_HomeController_tasasDeFechaAjax39_route(params@_) =>
      call { 
        controllers_HomeController_tasasDeFechaAjax39_invoker.call(
          req => HomeController_18.tasasDeFechaAjax(req))
      }
  
    // @LINE:81
    case controllers_HomeController_sendEmail40_route(params@_) =>
      call { 
        controllers_HomeController_sendEmail40_invoker.call(
          req => HomeController_18.sendEmail(req))
      }
  
    // @LINE:83
    case controllers_HomeController_muestraAlbumFotos41_route(params@_) =>
      call(params.fromPath[String]("carp", None)) { (carp) =>
        controllers_HomeController_muestraAlbumFotos41_invoker.call(
          req => HomeController_18.muestraAlbumFotos(carp, req))
      }
  
    // @LINE:84
    case controllers_HomeController_muestraAlbumFotosWord42_route(params@_) =>
      call { 
        controllers_HomeController_muestraAlbumFotosWord42_invoker.call(
          req => HomeController_18.muestraAlbumFotosWord(req))
      }
  
    // @LINE:91
    case controllers_MnuToolsAdmin_administraModulos43_route(params@_) =>
      call { 
        controllers_MnuToolsAdmin_administraModulos43_invoker.call(
          req => MnuToolsAdmin_3.administraModulos(req))
      }
  
    // @LINE:92
    case controllers_MnuToolsAdmin_modificarModulosAjax44_route(params@_) =>
      call { 
        controllers_MnuToolsAdmin_modificarModulosAjax44_invoker.call(
          req => MnuToolsAdmin_3.modificarModulosAjax(req))
      }
  
    // @LINE:94
    case controllers_MnuToolsAdmin_administrarEmisor45_route(params@_) =>
      call { 
        controllers_MnuToolsAdmin_administrarEmisor45_invoker.call(
          req => MnuToolsAdmin_3.administrarEmisor(req))
      }
  
    // @LINE:95
    case controllers_MnuToolsAdmin_modificarEmisor46_route(params@_) =>
      call { 
        controllers_MnuToolsAdmin_modificarEmisor46_invoker.call(
          req => MnuToolsAdmin_3.modificarEmisor(req))
      }
  
    // @LINE:102
    case controllers_MnuAyuda_manualBasico47_route(params@_) =>
      call { 
        controllers_MnuAyuda_manualBasico47_invoker.call(
          req => MnuAyuda_14.manualBasico(req))
      }
  
    // @LINE:103
    case controllers_MnuAyuda_manualPrimerosPasos48_route(params@_) =>
      call { 
        controllers_MnuAyuda_manualPrimerosPasos48_invoker.call(
          req => MnuAyuda_14.manualPrimerosPasos(req))
      }
  
    // @LINE:109
    case controllers_MnuOdo_claseMantencion49_route(params@_) =>
      call { 
        controllers_MnuOdo_claseMantencion49_invoker.call(
          req => MnuOdo_12.claseMantencion(req))
      }
  
    // @LINE:110
    case controllers_MnuOdo_claseModifica50_route(params@_) =>
      call { 
        controllers_MnuOdo_claseModifica50_invoker.call(
          req => MnuOdo_12.claseModifica(req))
      }
  
    // @LINE:111
    case controllers_MnuOdo_claseAgrega51_route(params@_) =>
      call { 
        controllers_MnuOdo_claseAgrega51_invoker.call(
          req => MnuOdo_12.claseAgrega(req))
      }
  
    // @LINE:112
    case controllers_MnuOdo_claseNuevo52_route(params@_) =>
      call { 
        controllers_MnuOdo_claseNuevo52_invoker.call(
          req => MnuOdo_12.claseNuevo(req))
      }
  
    // @LINE:113
    case controllers_MnuOdo_claseElimina53_route(params@_) =>
      call { 
        controllers_MnuOdo_claseElimina53_invoker.call(
          req => MnuOdo_12.claseElimina(req))
      }
  
    // @LINE:114
    case controllers_MnuOdo_modificaClasePorCampoAjax54_route(params@_) =>
      call { 
        controllers_MnuOdo_modificaClasePorCampoAjax54_invoker.call(
          req => MnuOdo_12.modificaClasePorCampoAjax(req))
      }
  
    // @LINE:116
    case controllers_MnuOdo_servicioMantencion55_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioMantencion55_invoker.call(
          req => MnuOdo_12.servicioMantencion(req))
      }
  
    // @LINE:117
    case controllers_MnuOdo_servicioMantencionExcel56_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioMantencionExcel56_invoker.call(
          req => MnuOdo_12.servicioMantencionExcel(req))
      }
  
    // @LINE:119
    case controllers_MnuOdo_servicioNuevo57_route(params@_) =>
      call(params.fromPath[Long]("id_clase", None)) { (id_clase) =>
        controllers_MnuOdo_servicioNuevo57_invoker.call(
          req => MnuOdo_12.servicioNuevo(id_clase, req))
      }
  
    // @LINE:120
    case controllers_MnuOdo_verificaCodigoServicioAjax58_route(params@_) =>
      call { 
        controllers_MnuOdo_verificaCodigoServicioAjax58_invoker.call(
          req => MnuOdo_12.verificaCodigoServicioAjax(req))
      }
  
    // @LINE:121
    case controllers_MnuOdo_servicioGraba59_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioGraba59_invoker.call(
          req => MnuOdo_12.servicioGraba(req))
      }
  
    // @LINE:122
    case controllers_MnuOdo_servicioModifica60_route(params@_) =>
      call(params.fromPath[Long]("id_servicio", None)) { (id_servicio) =>
        controllers_MnuOdo_servicioModifica60_invoker.call(
          req => MnuOdo_12.servicioModifica(id_servicio, req))
      }
  
    // @LINE:123
    case controllers_MnuOdo_servicioUpdate61_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioUpdate61_invoker.call(
          req => MnuOdo_12.servicioUpdate(req))
      }
  
    // @LINE:124
    case controllers_MnuOdo_servicioElimina62_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioElimina62_invoker.call(
          req => MnuOdo_12.servicioElimina(req))
      }
  
    // @LINE:125
    case controllers_MnuOdo_modPrecioServicioAjax63_route(params@_) =>
      call { 
        controllers_MnuOdo_modPrecioServicioAjax63_invoker.call(
          req => MnuOdo_12.modPrecioServicioAjax(req))
      }
  
    // @LINE:127
    case controllers_MnuOdo_servicioPrecios64_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioPrecios64_invoker.call(
          req => MnuOdo_12.servicioPrecios(req))
      }
  
    // @LINE:128
    case controllers_MnuOdo_servicioListaPrecios65_route(params@_) =>
      call(params.fromPath[Long]("id_bod", None)) { (id_bod) =>
        controllers_MnuOdo_servicioListaPrecios65_invoker.call(
          req => MnuOdo_12.servicioListaPrecios(id_bod, req))
      }
  
    // @LINE:129
    case controllers_MnuOdo_servicioAgregaPrecio66_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioAgregaPrecio66_invoker.call(
          req => MnuOdo_12.servicioAgregaPrecio(req))
      }
  
    // @LINE:130
    case controllers_MnuOdo_modPorCampoListaPServicioAjax67_route(params@_) =>
      call { 
        controllers_MnuOdo_modPorCampoListaPServicioAjax67_invoker.call(
          req => MnuOdo_12.modPorCampoListaPServicioAjax(req))
      }
  
    // @LINE:132
    case controllers_MnuOdo_servicioPreciosVariable068_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioPreciosVariable068_invoker.call(
          req => MnuOdo_12.servicioPreciosVariable0(req))
      }
  
    // @LINE:133
    case controllers_MnuOdo_servicioPreciosVariable169_route(params@_) =>
      call(params.fromPath[Long]("id_bod", None)) { (id_bod) =>
        controllers_MnuOdo_servicioPreciosVariable169_invoker.call(
          req => MnuOdo_12.servicioPreciosVariable1(id_bod, req))
      }
  
    // @LINE:134
    case controllers_MnuOdo_servicioPreciosVariable270_route(params@_) =>
      call(params.fromPath[Long]("id_bod", None), params.fromPath[Long]("id_ser", None), params.fromPath[Long]("id_cot", None)) { (id_bod, id_ser, id_cot) =>
        controllers_MnuOdo_servicioPreciosVariable270_invoker.call(
          req => MnuOdo_12.servicioPreciosVariable2(id_bod, id_ser, id_cot, req))
      }
  
    // @LINE:135
    case controllers_MnuOdo_servicioAgregaVariable71_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioAgregaVariable71_invoker.call(
          req => MnuOdo_12.servicioAgregaVariable(req))
      }
  
    // @LINE:136
    case controllers_MnuOdo_modPrecioVariableServicioAjax72_route(params@_) =>
      call { 
        controllers_MnuOdo_modPrecioVariableServicioAjax72_invoker.call(
          req => MnuOdo_12.modPrecioVariableServicioAjax(req))
      }
  
    // @LINE:137
    case controllers_MnuOdo_servicioEliminaVariable73_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioEliminaVariable73_invoker.call(
          req => MnuOdo_12.servicioEliminaVariable(req))
      }
  
    // @LINE:139
    case controllers_MnuOdo_servicioEquipos074_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioEquipos074_invoker.call(
          req => MnuOdo_12.servicioEquipos0(req))
      }
  
    // @LINE:140
    case controllers_MnuOdo_modVigEquipoServicioAjax75_route(params@_) =>
      call { 
        controllers_MnuOdo_modVigEquipoServicioAjax75_invoker.call(
          req => MnuOdo_12.modVigEquipoServicioAjax(req))
      }
  
    // @LINE:141
    case controllers_MnuOdo_servicioEquipos176_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioEquipos176_invoker.call(
          req => MnuOdo_12.servicioEquipos1(req))
      }
  
    // @LINE:142
    case controllers_MnuOdo_servicioEquipos277_route(params@_) =>
      call(params.fromPath[Long]("id_bod", None)) { (id_bod) =>
        controllers_MnuOdo_servicioEquipos277_invoker.call(
          req => MnuOdo_12.servicioEquipos2(id_bod, req))
      }
  
    // @LINE:143
    case controllers_MnuOdo_servicioEquipo378_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioEquipo378_invoker.call(
          req => MnuOdo_12.servicioEquipo3(req))
      }
  
    // @LINE:145
    case controllers_MnuOdo_operadorMantencion79_route(params@_) =>
      call { 
        controllers_MnuOdo_operadorMantencion79_invoker.call(
          req => MnuOdo_12.operadorMantencion(req))
      }
  
    // @LINE:146
    case controllers_MnuOdo_operadorNuevo80_route(params@_) =>
      call { 
        controllers_MnuOdo_operadorNuevo80_invoker.call(
          req => MnuOdo_12.operadorNuevo(req))
      }
  
    // @LINE:147
    case controllers_MnuOdo_verificaRutOperadorAjax81_route(params@_) =>
      call { 
        controllers_MnuOdo_verificaRutOperadorAjax81_invoker.call(
          req => MnuOdo_12.verificaRutOperadorAjax(req))
      }
  
    // @LINE:148
    case controllers_MnuOdo_operadorGraba82_route(params@_) =>
      call { 
        controllers_MnuOdo_operadorGraba82_invoker.call(
          req => MnuOdo_12.operadorGraba(req))
      }
  
    // @LINE:149
    case controllers_MnuOdo_modVigOperadorServicioAjax83_route(params@_) =>
      call { 
        controllers_MnuOdo_modVigOperadorServicioAjax83_invoker.call(
          req => MnuOdo_12.modVigOperadorServicioAjax(req))
      }
  
    // @LINE:150
    case controllers_MnuOdo_operadorModifica84_route(params@_) =>
      call(params.fromPath[Long]("id_oper", None)) { (id_oper) =>
        controllers_MnuOdo_operadorModifica84_invoker.call(
          req => MnuOdo_12.operadorModifica(id_oper, req))
      }
  
    // @LINE:151
    case controllers_MnuOdo_operadorUpdate85_route(params@_) =>
      call { 
        controllers_MnuOdo_operadorUpdate85_invoker.call(
          req => MnuOdo_12.operadorUpdate(req))
      }
  
    // @LINE:153
    case controllers_MnuOdo_odoVentas86_route(params@_) =>
      call { 
        controllers_MnuOdo_odoVentas86_invoker.call(
          req => MnuOdo_12.odoVentas(req))
      }
  
    // @LINE:154
    case controllers_MnuOdo_odoListaServiciosAjax87_route(params@_) =>
      call { 
        controllers_MnuOdo_odoListaServiciosAjax87_invoker.call(
          req => MnuOdo_12.odoListaServiciosAjax(req))
      }
  
    // @LINE:155
    case controllers_MnuOdo_odoListaEquiposAjax88_route(params@_) =>
      call { 
        controllers_MnuOdo_odoListaEquiposAjax88_invoker.call(
          req => MnuOdo_12.odoListaEquiposAjax(req))
      }
  
    // @LINE:156
    case controllers_MnuOdo_odoVentasGrabar89_route(params@_) =>
      call { 
        controllers_MnuOdo_odoVentasGrabar89_invoker.call(
          req => MnuOdo_12.odoVentasGrabar(req))
      }
  
    // @LINE:158
    case controllers_MnuOdo_odoVentasGrabaDocAnexo90_route(params@_) =>
      call { 
        controllers_MnuOdo_odoVentasGrabaDocAnexo90_invoker.call(
          req => MnuOdo_12.odoVentasGrabaDocAnexo(req))
      }
  
    // @LINE:159
    case controllers_MnuOdoAppWeb_odoVentasGrabaDocAnexoWeb91_route(params@_) =>
      call { 
        controllers_MnuOdoAppWeb_odoVentasGrabaDocAnexoWeb91_invoker.call(
          req => MnuOdoAppWeb_0.odoVentasGrabaDocAnexoWeb(req))
      }
  
    // @LINE:160
    case controllers_MnuOdo_odoGrabaAlbumFotosAjax92_route(params@_) =>
      call { 
        controllers_MnuOdo_odoGrabaAlbumFotosAjax92_invoker.call(
          req => MnuOdo_12.odoGrabaAlbumFotosAjax(req))
      }
  
    // @LINE:164
    case controllers_MnuOdo_odoListarVentas093_route(params@_) =>
      call { 
        controllers_MnuOdo_odoListarVentas093_invoker.call(
          req => MnuOdo_12.odoListarVentas0(req))
      }
  
    // @LINE:165
    case controllers_MnuOdo_odoListarVentas194_route(params@_) =>
      call { 
        controllers_MnuOdo_odoListarVentas194_invoker.call(
          req => MnuOdo_12.odoListarVentas1(req))
      }
  
    // @LINE:166
    case controllers_MnuOdo_odoListarVentas1Excel95_route(params@_) =>
      call { 
        controllers_MnuOdo_odoListarVentas1Excel95_invoker.call(
          req => MnuOdo_12.odoListarVentas1Excel(req))
      }
  
    // @LINE:168
    case controllers_MnuOdo_odoListarVentas1aux96_route(params@_) =>
      call(params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None)) { (desde, hasta) =>
        controllers_MnuOdo_odoListarVentas1aux96_invoker.call(
          req => MnuOdo_12.odoListarVentas1aux(req, desde, hasta))
      }
  
    // @LINE:169
    case controllers_MnuOdo_odoCambiaEquipoAjax97_route(params@_) =>
      call { 
        controllers_MnuOdo_odoCambiaEquipoAjax97_invoker.call(
          req => MnuOdo_12.odoCambiaEquipoAjax(req))
      }
  
    // @LINE:170
    case controllers_MnuOdo_odoDetalleVenta98_route(params@_) =>
      call(params.fromPath[Long]("id_venta", None), params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None)) { (id_venta, desde, hasta) =>
        controllers_MnuOdo_odoDetalleVenta98_invoker.call(
          req => MnuOdo_12.odoDetalleVenta(req, id_venta, desde, hasta))
      }
  
    // @LINE:171
    case controllers_MnuOdo_odoFirmaOperador99_route(params@_) =>
      call(params.fromPath[Long]("id_venta", None), params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None)) { (id_venta, desde, hasta) =>
        controllers_MnuOdo_odoFirmaOperador99_invoker.call(
          req => MnuOdo_12.odoFirmaOperador(req, id_venta, desde, hasta))
      }
  
    // @LINE:172
    case controllers_MnuOdo_grabarFirmaOperador100_route(params@_) =>
      call { 
        controllers_MnuOdo_grabarFirmaOperador100_invoker.call(
          req => MnuOdo_12.grabarFirmaOperador(req))
      }
  
    // @LINE:173
    case controllers_MnuOdo_odoFirmaAutorizador101_route(params@_) =>
      call(params.fromPath[Long]("id_venta", None), params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None)) { (id_venta, desde, hasta) =>
        controllers_MnuOdo_odoFirmaAutorizador101_invoker.call(
          req => MnuOdo_12.odoFirmaAutorizador(req, id_venta, desde, hasta))
      }
  
    // @LINE:174
    case controllers_MnuOdo_grabarFirmaAutorizador102_route(params@_) =>
      call { 
        controllers_MnuOdo_grabarFirmaAutorizador102_invoker.call(
          req => MnuOdo_12.grabarFirmaAutorizador(req))
      }
  
    // @LINE:175
    case controllers_MnuOdo_odoVentaServicioElimina103_route(params@_) =>
      call { 
        controllers_MnuOdo_odoVentaServicioElimina103_invoker.call(
          req => MnuOdo_12.odoVentaServicioElimina(req))
      }
  
    // @LINE:177
    case controllers_MnuOdo_reportProfPerOdo104_route(params@_) =>
      call { 
        controllers_MnuOdo_reportProfPerOdo104_invoker.call(
          req => MnuOdo_12.reportProfPerOdo(req))
      }
  
    // @LINE:178
    case controllers_MnuOdo_reportProfProyOdo105_route(params@_) =>
      call(params.fromPath[String]("archivoPDF", None)) { (archivoPDF) =>
        controllers_MnuOdo_reportProfProyOdo105_invoker.call(
          req => MnuOdo_12.reportProfProyOdo(req, archivoPDF))
      }
  
    // @LINE:179
    case controllers_MnuOdo_reportProfProyOdoExcel106_route(params@_) =>
      call { 
        controllers_MnuOdo_reportProfProyOdoExcel106_invoker.call(
          req => MnuOdo_12.reportProfProyOdoExcel(req))
      }
  
    // @LINE:180
    case controllers_MnuOdo_reportProfProyDetalleOdo107_route(params@_) =>
      call { 
        controllers_MnuOdo_reportProfProyDetalleOdo107_invoker.call(
          req => MnuOdo_12.reportProfProyDetalleOdo(req))
      }
  
    // @LINE:181
    case controllers_MnuOdo_reportProfProyDetalleOdoExcel108_route(params@_) =>
      call { 
        controllers_MnuOdo_reportProfProyDetalleOdoExcel108_invoker.call(
          req => MnuOdo_12.reportProfProyDetalleOdoExcel(req))
      }
  
    // @LINE:182
    case controllers_MnuOdo_generarProfPdfXlsxXmlJsonOdo109_route(params@_) =>
      call { 
        controllers_MnuOdo_generarProfPdfXlsxXmlJsonOdo109_invoker.call(
          req => MnuOdo_12.generarProfPdfXlsxXmlJsonOdo(req))
      }
  
    // @LINE:184
    case controllers_MnuOdo_proformaListaOdo110_route(params@_) =>
      call { 
        controllers_MnuOdo_proformaListaOdo110_invoker.call(
          req => MnuOdo_12.proformaListaOdo(req))
      }
  
    // @LINE:185
    case controllers_MnuOdo_proformaEliminaOdo111_route(params@_) =>
      call { 
        controllers_MnuOdo_proformaEliminaOdo111_invoker.call(
          req => MnuOdo_12.proformaEliminaOdo(req))
      }
  
    // @LINE:187
    case controllers_MnuOdo_ajustesEpOdo112_route(params@_) =>
      call { 
        controllers_MnuOdo_ajustesEpOdo112_invoker.call(
          req => MnuOdo_12.ajustesEpOdo(req))
      }
  
    // @LINE:188
    case controllers_MnuOdo_ajustesEpListadoOdo113_route(params@_) =>
      call { 
        controllers_MnuOdo_ajustesEpListadoOdo113_invoker.call(
          req => MnuOdo_12.ajustesEpListadoOdo(req))
      }
  
    // @LINE:189
    case controllers_MnuOdo_ajustesEpNuevoOdo114_route(params@_) =>
      call { 
        controllers_MnuOdo_ajustesEpNuevoOdo114_invoker.call(
          req => MnuOdo_12.ajustesEpNuevoOdo(req))
      }
  
    // @LINE:190
    case controllers_MnuOdo_ajustesEpGrabarOdo115_route(params@_) =>
      call { 
        controllers_MnuOdo_ajustesEpGrabarOdo115_invoker.call(
          req => MnuOdo_12.ajustesEpGrabarOdo(req))
      }
  
    // @LINE:191
    case controllers_MnuOdo_ajustesEpEliminarOdo116_route(params@_) =>
      call { 
        controllers_MnuOdo_ajustesEpEliminarOdo116_invoker.call(
          req => MnuOdo_12.ajustesEpEliminarOdo(req))
      }
  
    // @LINE:192
    case controllers_MnuOdo_ajustesEpModificarOdo117_route(params@_) =>
      call { 
        controllers_MnuOdo_ajustesEpModificarOdo117_invoker.call(
          req => MnuOdo_12.ajustesEpModificarOdo(req))
      }
  
    // @LINE:193
    case controllers_MnuOdo_ajustesEpUpdateOdo118_route(params@_) =>
      call { 
        controllers_MnuOdo_ajustesEpUpdateOdo118_invoker.call(
          req => MnuOdo_12.ajustesEpUpdateOdo(req))
      }
  
    // @LINE:194
    case controllers_MnuOdo_ajusteGrabaPDFodo119_route(params@_) =>
      call { 
        controllers_MnuOdo_ajusteGrabaPDFodo119_invoker.call(
          req => MnuOdo_12.ajusteGrabaPDFodo(req))
      }
  
    // @LINE:195
    case controllers_MnuOdo_ajustesEpRptOdo120_route(params@_) =>
      call { 
        controllers_MnuOdo_ajustesEpRptOdo120_invoker.call(
          req => MnuOdo_12.ajustesEpRptOdo(req))
      }
  
    // @LINE:196
    case controllers_MnuOdo_ajustesEpRptDetalleOdo121_route(params@_) =>
      call { 
        controllers_MnuOdo_ajustesEpRptDetalleOdo121_invoker.call(
          req => MnuOdo_12.ajustesEpRptDetalleOdo(req))
      }
  
    // @LINE:197
    case controllers_MnuOdo_ajustesPeriodoEpRpt1Odo122_route(params@_) =>
      call { 
        controllers_MnuOdo_ajustesPeriodoEpRpt1Odo122_invoker.call(
          req => MnuOdo_12.ajustesPeriodoEpRpt1Odo(req))
      }
  
    // @LINE:198
    case controllers_MnuOdo_ajustesPeriodoEpRpt2Odo123_route(params@_) =>
      call { 
        controllers_MnuOdo_ajustesPeriodoEpRpt2Odo123_invoker.call(
          req => MnuOdo_12.ajustesPeriodoEpRpt2Odo(req))
      }
  
    // @LINE:201
    case controllers_MnuOdo_reportOdoPeriodoResumen124_route(params@_) =>
      call { 
        controllers_MnuOdo_reportOdoPeriodoResumen124_invoker.call(
          req => MnuOdo_12.reportOdoPeriodoResumen(req))
      }
  
    // @LINE:202
    case controllers_MnuOdo_reportOdoResumen125_route(params@_) =>
      call { 
        controllers_MnuOdo_reportOdoResumen125_invoker.call(
          req => MnuOdo_12.reportOdoResumen(req))
      }
  
    // @LINE:203
    case controllers_MnuOdo_reportOdoResumenExcel126_route(params@_) =>
      call { 
        controllers_MnuOdo_reportOdoResumenExcel126_invoker.call(
          req => MnuOdo_12.reportOdoResumenExcel(req))
      }
  
    // @LINE:205
    case controllers_MnuOdo_reportOdoConsolidado127_route(params@_) =>
      call { 
        controllers_MnuOdo_reportOdoConsolidado127_invoker.call(
          req => MnuOdo_12.reportOdoConsolidado(req))
      }
  
    // @LINE:206
    case controllers_MnuOdo_reportOdoConsolidadoRtp128_route(params@_) =>
      call { 
        controllers_MnuOdo_reportOdoConsolidadoRtp128_invoker.call(
          req => MnuOdo_12.reportOdoConsolidadoRtp(req))
      }
  
    // @LINE:207
    case controllers_MnuOdo_reportOdoConsolidadoRtpExcel129_route(params@_) =>
      call { 
        controllers_MnuOdo_reportOdoConsolidadoRtpExcel129_invoker.call(
          req => MnuOdo_12.reportOdoConsolidadoRtpExcel(req))
      }
  
    // @LINE:209
    case controllers_MnuOdo_reporteMovOdoCantidades130_route(params@_) =>
      call { 
        controllers_MnuOdo_reporteMovOdoCantidades130_invoker.call(
          req => MnuOdo_12.reporteMovOdoCantidades(req))
      }
  
    // @LINE:210
    case controllers_MnuOdo_reporteMovOdoCantLista131_route(params@_) =>
      call { 
        controllers_MnuOdo_reporteMovOdoCantLista131_invoker.call(
          req => MnuOdo_12.reporteMovOdoCantLista(req))
      }
  
    // @LINE:211
    case controllers_MnuOdo_reporteMovOdoCantDetalle132_route(params@_) =>
      call { 
        controllers_MnuOdo_reporteMovOdoCantDetalle132_invoker.call(
          req => MnuOdo_12.reporteMovOdoCantDetalle(req))
      }
  
    // @LINE:212
    case controllers_MnuOdo_reporteMovOdoCantDetalleExcel133_route(params@_) =>
      call { 
        controllers_MnuOdo_reporteMovOdoCantDetalleExcel133_invoker.call(
          req => MnuOdo_12.reporteMovOdoCantDetalleExcel(req))
      }
  
    // @LINE:214
    case controllers_MnuOdo_reporteMovOdoValorizado134_route(params@_) =>
      call { 
        controllers_MnuOdo_reporteMovOdoValorizado134_invoker.call(
          req => MnuOdo_12.reporteMovOdoValorizado(req))
      }
  
    // @LINE:215
    case controllers_MnuOdo_reporteMovOdoValorizadoLista135_route(params@_) =>
      call { 
        controllers_MnuOdo_reporteMovOdoValorizadoLista135_invoker.call(
          req => MnuOdo_12.reporteMovOdoValorizadoLista(req))
      }
  
    // @LINE:216
    case controllers_MnuOdo_reporteMovOdoValorizadoDetalle136_route(params@_) =>
      call { 
        controllers_MnuOdo_reporteMovOdoValorizadoDetalle136_invoker.call(
          req => MnuOdo_12.reporteMovOdoValorizadoDetalle(req))
      }
  
    // @LINE:217
    case controllers_MnuOdo_reporteMovOdoValorizadoDetalleExcel137_route(params@_) =>
      call { 
        controllers_MnuOdo_reporteMovOdoValorizadoDetalleExcel137_invoker.call(
          req => MnuOdo_12.reporteMovOdoValorizadoDetalleExcel(req))
      }
  
    // @LINE:219
    case controllers_MnuOdo_reportOperadorConsol0138_route(params@_) =>
      call { 
        controllers_MnuOdo_reportOperadorConsol0138_invoker.call(
          req => MnuOdo_12.reportOperadorConsol0(req))
      }
  
    // @LINE:220
    case controllers_MnuOdo_reportOperadorConsol1139_route(params@_) =>
      call { 
        controllers_MnuOdo_reportOperadorConsol1139_invoker.call(
          req => MnuOdo_12.reportOperadorConsol1(req))
      }
  
    // @LINE:221
    case controllers_MnuOdo_reportOperadorConsol1Excel140_route(params@_) =>
      call { 
        controllers_MnuOdo_reportOperadorConsol1Excel140_invoker.call(
          req => MnuOdo_12.reportOperadorConsol1Excel(req))
      }
  
    // @LINE:232
    case controllers_HomeController_inicio141_route(params@_) =>
      call { 
        controllers_HomeController_inicio141_invoker.call(HomeController_18.inicio())
      }
  
    // @LINE:234
    case controllers_MnuOdoAppWeb_odoVentasWeb142_route(params@_) =>
      call { 
        controllers_MnuOdoAppWeb_odoVentasWeb142_invoker.call(
          req => MnuOdoAppWeb_0.odoVentasWeb(req))
      }
  
    // @LINE:235
    case controllers_MnuOdoAppWeb_odoVentasHomeWeb143_route(params@_) =>
      call { 
        controllers_MnuOdoAppWeb_odoVentasHomeWeb143_invoker.call(
          req => MnuOdoAppWeb_0.odoVentasHomeWeb(req))
      }
  
    // @LINE:236
    case controllers_MnuOdoAppWeb_odoVentasHomeWeb144_route(params@_) =>
      call { 
        controllers_MnuOdoAppWeb_odoVentasHomeWeb144_invoker.call(
          req => MnuOdoAppWeb_0.odoVentasHomeWeb(req))
      }
  
    // @LINE:237
    case controllers_MnuOdoAppWeb_odoVentasGrabarWeb145_route(params@_) =>
      call { 
        controllers_MnuOdoAppWeb_odoVentasGrabarWeb145_invoker.call(
          req => MnuOdoAppWeb_0.odoVentasGrabarWeb(req))
      }
  
    // @LINE:238
    case controllers_MnuOdoAppWeb_odoListarVentasWeb146_route(params@_) =>
      call(params.fromPath[Long]("year", None)) { (year) =>
        controllers_MnuOdoAppWeb_odoListarVentasWeb146_invoker.call(
          req => MnuOdoAppWeb_0.odoListarVentasWeb(req, year))
      }
  
    // @LINE:239
    case controllers_MnuOdoAppWeb_odoDetalleVentaWeb147_route(params@_) =>
      call(params.fromPath[Long]("id_venta", None)) { (id_venta) =>
        controllers_MnuOdoAppWeb_odoDetalleVentaWeb147_invoker.call(
          req => MnuOdoAppWeb_0.odoDetalleVentaWeb(req, id_venta))
      }
  
    // @LINE:240
    case controllers_MnuOdoAppWeb_odoFirmaOperadorWeb148_route(params@_) =>
      call(params.fromPath[Long]("id_venta", None)) { (id_venta) =>
        controllers_MnuOdoAppWeb_odoFirmaOperadorWeb148_invoker.call(
          req => MnuOdoAppWeb_0.odoFirmaOperadorWeb(req, id_venta))
      }
  
    // @LINE:241
    case controllers_MnuOdoAppWeb_grabarFirmaOperadorWeb149_route(params@_) =>
      call { 
        controllers_MnuOdoAppWeb_grabarFirmaOperadorWeb149_invoker.call(
          req => MnuOdoAppWeb_0.grabarFirmaOperadorWeb(req))
      }
  
    // @LINE:242
    case controllers_MnuOdoAppWeb_odoFirmaAutorizadorWeb150_route(params@_) =>
      call(params.fromPath[Long]("id_venta", None)) { (id_venta) =>
        controllers_MnuOdoAppWeb_odoFirmaAutorizadorWeb150_invoker.call(
          req => MnuOdoAppWeb_0.odoFirmaAutorizadorWeb(req, id_venta))
      }
  
    // @LINE:243
    case controllers_MnuOdoAppWeb_grabarFirmaAutorizadorWeb151_route(params@_) =>
      call { 
        controllers_MnuOdoAppWeb_grabarFirmaAutorizadorWeb151_invoker.call(
          req => MnuOdoAppWeb_0.grabarFirmaAutorizadorWeb(req))
      }
  
    // @LINE:244
    case controllers_MnuOdoAppWeb_odoVentaServicioEliminaWeb152_route(params@_) =>
      call { 
        controllers_MnuOdoAppWeb_odoVentaServicioEliminaWeb152_invoker.call(
          req => MnuOdoAppWeb_0.odoVentaServicioEliminaWeb(req))
      }
  
    // @LINE:247
    case controllers_MnuOdoAppWeb_odoAutorizaDetalleVentaWeb153_route(params@_) =>
      call(params.fromPath[Long]("id_venta", None)) { (id_venta) =>
        controllers_MnuOdoAppWeb_odoAutorizaDetalleVentaWeb153_invoker.call(
          req => MnuOdoAppWeb_0.odoAutorizaDetalleVentaWeb(req, id_venta))
      }
  
    // @LINE:248
    case controllers_MnuOdoAppWeb_odoAutorizaListarVentasWeb154_route(params@_) =>
      call { 
        controllers_MnuOdoAppWeb_odoAutorizaListarVentasWeb154_invoker.call(
          req => MnuOdoAppWeb_0.odoAutorizaListarVentasWeb(req))
      }
  
    // @LINE:249
    case controllers_MnuOdoAppWeb_odoAutorizaFirmaWeb155_route(params@_) =>
      call(params.fromPath[Long]("id_venta", None)) { (id_venta) =>
        controllers_MnuOdoAppWeb_odoAutorizaFirmaWeb155_invoker.call(
          req => MnuOdoAppWeb_0.odoAutorizaFirmaWeb(req, id_venta))
      }
  
    // @LINE:250
    case controllers_MnuOdoAppWeb_grabarAutorizaFirmaWeb156_route(params@_) =>
      call { 
        controllers_MnuOdoAppWeb_grabarAutorizaFirmaWeb156_invoker.call(
          req => MnuOdoAppWeb_0.grabarAutorizaFirmaWeb(req))
      }
  
    // @LINE:259
    case controllers_AppCPanel_vistaCPanel157_route(params@_) =>
      call { 
        controllers_AppCPanel_vistaCPanel157_invoker.call(AppCPanel_10.vistaCPanel())
      }
  
    // @LINE:260
    case controllers_AppCPanel_inicioCPanelHome158_route(params@_) =>
      call { 
        controllers_AppCPanel_inicioCPanelHome158_invoker.call(
          req => AppCPanel_10.inicioCPanelHome(req))
      }
  
    // @LINE:261
    case controllers_AppCPanel_inicioCPanel159_route(params@_) =>
      call { 
        controllers_AppCPanel_inicioCPanel159_invoker.call(
          req => AppCPanel_10.inicioCPanel(req))
      }
  
    // @LINE:262
    case controllers_AppCPanel_cPanelModalItemsControlados160_route(params@_) =>
      call { 
        controllers_AppCPanel_cPanelModalItemsControlados160_invoker.call(
          req => AppCPanel_10.cPanelModalItemsControlados(req))
      }
  
    // @LINE:263
    case controllers_AppCPanel_cPanelModalVigentes161_route(params@_) =>
      call { 
        controllers_AppCPanel_cPanelModalVigentes161_invoker.call(
          req => AppCPanel_10.cPanelModalVigentes(req))
      }
  
    // @LINE:264
    case controllers_AppCPanel_cPanelModalNoVigentes162_route(params@_) =>
      call { 
        controllers_AppCPanel_cPanelModalNoVigentes162_invoker.call(
          req => AppCPanel_10.cPanelModalNoVigentes(req))
      }
  
    // @LINE:265
    case controllers_AppCPanel_inicioCPanel2163_route(params@_) =>
      call { 
        controllers_AppCPanel_inicioCPanel2163_invoker.call(
          req => AppCPanel_10.inicioCPanel2(req))
      }
  
    // @LINE:273
    case controllers_MnuPlanes_plantillaIConstruyeDownload164_route(params@_) =>
      call { 
        controllers_MnuPlanes_plantillaIConstruyeDownload164_invoker.call(
          req => MnuPlanes_11.plantillaIConstruyeDownload(req))
      }
  
    // @LINE:274
    case controllers_MnuPlanes_plantillaIConstruyeUpload165_route(params@_) =>
      call { 
        controllers_MnuPlanes_plantillaIConstruyeUpload165_invoker.call(
          req => MnuPlanes_11.plantillaIConstruyeUpload(req))
      }
  
    // @LINE:277
    case controllers_MnuPlanes_importaHojaVidaIconstruye0166_route(params@_) =>
      call { 
        controllers_MnuPlanes_importaHojaVidaIconstruye0166_invoker.call(
          req => MnuPlanes_11.importaHojaVidaIconstruye0(req))
      }
  
    // @LINE:278
    case controllers_MnuPlanes_importaHojaVidaIconstruye1167_route(params@_) =>
      call { 
        controllers_MnuPlanes_importaHojaVidaIconstruye1167_invoker.call(
          req => MnuPlanes_11.importaHojaVidaIconstruye1(req))
      }
  
    // @LINE:279
    case controllers_MnuPlanes_importaHojaVidaIconstruye2168_route(params@_) =>
      call { 
        controllers_MnuPlanes_importaHojaVidaIconstruye2168_invoker.call(
          req => MnuPlanes_11.importaHojaVidaIconstruye2(req))
      }
  
    // @LINE:282
    case controllers_MnuPlanes_detalleProductoUploadIConstruye169_route(params@_) =>
      call { 
        controllers_MnuPlanes_detalleProductoUploadIConstruye169_invoker.call(
          req => MnuPlanes_11.detalleProductoUploadIConstruye(req))
      }
  
    // @LINE:283
    case controllers_MnuPlanes_detalleProductoDownloadMada170_route(params@_) =>
      call { 
        controllers_MnuPlanes_detalleProductoDownloadMada170_invoker.call(
          req => MnuPlanes_11.detalleProductoDownloadMada(req))
      }
  
    // @LINE:284
    case controllers_MnuPlanes_detalleProductoUploadMada171_route(params@_) =>
      call { 
        controllers_MnuPlanes_detalleProductoUploadMada171_invoker.call(
          req => MnuPlanes_11.detalleProductoUploadMada(req))
      }
  
    // @LINE:294
    case controllers_MnuPlanes_tipoTrabajoMantencion172_route(params@_) =>
      call { 
        controllers_MnuPlanes_tipoTrabajoMantencion172_invoker.call(
          req => MnuPlanes_11.tipoTrabajoMantencion(req))
      }
  
    // @LINE:295
    case controllers_MnuPlanes_tipoTrabajoModifica173_route(params@_) =>
      call { 
        controllers_MnuPlanes_tipoTrabajoModifica173_invoker.call(
          req => MnuPlanes_11.tipoTrabajoModifica(req))
      }
  
    // @LINE:296
    case controllers_MnuPlanes_modificaTipoPorCampoAjax174_route(params@_) =>
      call { 
        controllers_MnuPlanes_modificaTipoPorCampoAjax174_invoker.call(
          req => MnuPlanes_11.modificaTipoPorCampoAjax(req))
      }
  
    // @LINE:297
    case controllers_MnuPlanes_tipoTrabajoAgrega175_route(params@_) =>
      call { 
        controllers_MnuPlanes_tipoTrabajoAgrega175_invoker.call(
          req => MnuPlanes_11.tipoTrabajoAgrega(req))
      }
  
    // @LINE:298
    case controllers_MnuPlanes_tipoTrabajoNuevo176_route(params@_) =>
      call { 
        controllers_MnuPlanes_tipoTrabajoNuevo176_invoker.call(
          req => MnuPlanes_11.tipoTrabajoNuevo(req))
      }
  
    // @LINE:299
    case controllers_MnuPlanes_tipoTrabajoElimina177_route(params@_) =>
      call { 
        controllers_MnuPlanes_tipoTrabajoElimina177_invoker.call(
          req => MnuPlanes_11.tipoTrabajoElimina(req))
      }
  
    // @LINE:301
    case controllers_MnuPlanes_tipoPlanMantencion178_route(params@_) =>
      call { 
        controllers_MnuPlanes_tipoPlanMantencion178_invoker.call(
          req => MnuPlanes_11.tipoPlanMantencion(req))
      }
  
    // @LINE:302
    case controllers_MnuPlanes_tipoPlanModifica179_route(params@_) =>
      call { 
        controllers_MnuPlanes_tipoPlanModifica179_invoker.call(
          req => MnuPlanes_11.tipoPlanModifica(req))
      }
  
    // @LINE:303
    case controllers_MnuPlanes_modificaPlanPorCampoAjax180_route(params@_) =>
      call { 
        controllers_MnuPlanes_modificaPlanPorCampoAjax180_invoker.call(
          req => MnuPlanes_11.modificaPlanPorCampoAjax(req))
      }
  
    // @LINE:304
    case controllers_MnuPlanes_tipoPlanAgrega181_route(params@_) =>
      call { 
        controllers_MnuPlanes_tipoPlanAgrega181_invoker.call(
          req => MnuPlanes_11.tipoPlanAgrega(req))
      }
  
    // @LINE:305
    case controllers_MnuPlanes_tipoPlanNuevo182_route(params@_) =>
      call { 
        controllers_MnuPlanes_tipoPlanNuevo182_invoker.call(
          req => MnuPlanes_11.tipoPlanNuevo(req))
      }
  
    // @LINE:306
    case controllers_MnuPlanes_tipoPlanElimina183_route(params@_) =>
      call { 
        controllers_MnuPlanes_tipoPlanElimina183_invoker.call(
          req => MnuPlanes_11.tipoPlanElimina(req))
      }
  
    // @LINE:308
    case controllers_MnuPlanes_hojaVidaPlanMantencion184_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaPlanMantencion184_invoker.call(
          req => MnuPlanes_11.hojaVidaPlanMantencion(req))
      }
  
    // @LINE:309
    case controllers_MnuPlanes_hojaVidaPlanModifica185_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaPlanModifica185_invoker.call(
          req => MnuPlanes_11.hojaVidaPlanModifica(req))
      }
  
    // @LINE:310
    case controllers_MnuPlanes_hojaVidaPlanModificaGet186_route(params@_) =>
      call(params.fromPath[Long]("idEquipo", None)) { (idEquipo) =>
        controllers_MnuPlanes_hojaVidaPlanModificaGet186_invoker.call(
          req => MnuPlanes_11.hojaVidaPlanModificaGet(req, idEquipo))
      }
  
    // @LINE:311
    case controllers_MnuPlanes_hojaVidaAgregaPlan187_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaAgregaPlan187_invoker.call(
          req => MnuPlanes_11.hojaVidaAgregaPlan(req))
      }
  
    // @LINE:312
    case controllers_MnuPlanes_hojaVidaPlanElimina188_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaPlanElimina188_invoker.call(
          req => MnuPlanes_11.hojaVidaPlanElimina(req))
      }
  
    // @LINE:313
    case controllers_MnuPlanes_hojaVidaPlanCrear189_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaPlanCrear189_invoker.call(
          req => MnuPlanes_11.hojaVidaPlanCrear(req))
      }
  
    // @LINE:314
    case controllers_MnuPlanes_hojaVidaPlanCrear2190_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaPlanCrear2190_invoker.call(
          req => MnuPlanes_11.hojaVidaPlanCrear2(req))
      }
  
    // @LINE:315
    case controllers_MnuPlanes_actualCampoPlanMantencionAjax191_route(params@_) =>
      call { 
        controllers_MnuPlanes_actualCampoPlanMantencionAjax191_invoker.call(
          req => MnuPlanes_11.actualCampoPlanMantencionAjax(req))
      }
  
    // @LINE:317
    case controllers_MnuPlanes_hojaVidaMantencionLista192_route(params@_) =>
      call(params.fromPath[Long]("flag", None)) { (flag) =>
        controllers_MnuPlanes_hojaVidaMantencionLista192_invoker.call(
          req => MnuPlanes_11.hojaVidaMantencionLista(req, flag))
      }
  
    // @LINE:318
    case controllers_MnuPlanes_hojaVidaMantencionPlan193_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaMantencionPlan193_invoker.call(
          req => MnuPlanes_11.hojaVidaMantencionPlan(req))
      }
  
    // @LINE:319
    case controllers_MnuPlanes_hojaVidaMantencionHoja194_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaMantencionHoja194_invoker.call(
          req => MnuPlanes_11.hojaVidaMantencionHoja(req))
      }
  
    // @LINE:320
    case controllers_MnuPlanes_hojaVidaMantencionHojaReturn195_route(params@_) =>
      call(params.fromPath[Long]("id_equipo", None)) { (id_equipo) =>
        controllers_MnuPlanes_hojaVidaMantencionHojaReturn195_invoker.call(
          req => MnuPlanes_11.hojaVidaMantencionHojaReturn(req, id_equipo))
      }
  
    // @LINE:321
    case controllers_MnuPlanes_hojaVidaMantencionListaExcel196_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaMantencionListaExcel196_invoker.call(
          req => MnuPlanes_11.hojaVidaMantencionListaExcel(req))
      }
  
    // @LINE:324
    case controllers_MnuPlanes_hojaVidaMantencionHojaAgrega197_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaMantencionHojaAgrega197_invoker.call(
          req => MnuPlanes_11.hojaVidaMantencionHojaAgrega(req))
      }
  
    // @LINE:325
    case controllers_MnuPlanes_hojaVidaMantencionHojaElimina198_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaMantencionHojaElimina198_invoker.call(
          req => MnuPlanes_11.hojaVidaMantencionHojaElimina(req))
      }
  
    // @LINE:326
    case controllers_MnuPlanes_grabarHojaVidaPdf199_route(params@_) =>
      call { 
        controllers_MnuPlanes_grabarHojaVidaPdf199_invoker.call(
          req => MnuPlanes_11.grabarHojaVidaPdf(req))
      }
  
    // @LINE:327
    case controllers_MnuPlanes_actualCampoHojaVidaAjax200_route(params@_) =>
      call { 
        controllers_MnuPlanes_actualCampoHojaVidaAjax200_invoker.call(
          req => MnuPlanes_11.actualCampoHojaVidaAjax(req))
      }
  
    // @LINE:329
    case controllers_MnuPlanes_hojaVidaReportLista201_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaReportLista201_invoker.call(
          req => MnuPlanes_11.hojaVidaReportLista(req))
      }
  
    // @LINE:330
    case controllers_MnuPlanes_hojaVidaReportDetalle202_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaReportDetalle202_invoker.call(
          req => MnuPlanes_11.hojaVidaReportDetalle(req))
      }
  
    // @LINE:331
    case controllers_MnuPlanes_hojaVidaReportDetalleExcel203_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaReportDetalleExcel203_invoker.call(
          req => MnuPlanes_11.hojaVidaReportDetalleExcel(req))
      }
  
    // @LINE:333
    case controllers_MnuPlanes_hojaVidaReportKpis204_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaReportKpis204_invoker.call(
          req => MnuPlanes_11.hojaVidaReportKpis(req))
      }
  
    // @LINE:334
    case controllers_MnuPlanes_hojaVidaReportKpisExcel205_route(params@_) =>
      call { 
        controllers_MnuPlanes_hojaVidaReportKpisExcel205_invoker.call(
          req => MnuPlanes_11.hojaVidaReportKpisExcel(req))
      }
  
    // @LINE:336
    case controllers_MnuPlanes_hojaVidaReportGrafico206_route(params@_) =>
      call(params.fromPath[Long]("id_grupo", None), params.fromPath[Long]("periodo", None)) { (id_grupo, periodo) =>
        controllers_MnuPlanes_hojaVidaReportGrafico206_invoker.call(
          req => MnuPlanes_11.hojaVidaReportGrafico(req, id_grupo, periodo))
      }
  
    // @LINE:342
    case controllers_MnuReportes_reportInventarioEquipoCorte207_route(params@_) =>
      call(params.fromPath[String]("tipo", None)) { (tipo) =>
        controllers_MnuReportes_reportInventarioEquipoCorte207_invoker.call(
          req => MnuReportes_7.reportInventarioEquipoCorte(req, tipo))
      }
  
    // @LINE:343
    case controllers_MnuReportes_reportInventarioEquipo208_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioEquipo208_invoker.call(
          req => MnuReportes_7.reportInventarioEquipo(req))
      }
  
    // @LINE:344
    case controllers_MnuReportes_reportInventarioGeneralXEquipo209_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioGeneralXEquipo209_invoker.call(
          req => MnuReportes_7.reportInventarioGeneralXEquipo(req))
      }
  
    // @LINE:345
    case controllers_MnuReportes_reportInventTrazaEquipoEnBod210_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventTrazaEquipoEnBod210_invoker.call(
          req => MnuReportes_7.reportInventTrazaEquipoEnBod(req))
      }
  
    // @LINE:346
    case controllers_MnuReportes_reportInventarioEquipoExcel211_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioEquipoExcel211_invoker.call(
          req => MnuReportes_7.reportInventarioEquipoExcel(req))
      }
  
    // @LINE:348
    case controllers_MnuReportes_reportEquipoBodegaCorte212_route(params@_) =>
      call(params.fromPath[String]("tipo", None)) { (tipo) =>
        controllers_MnuReportes_reportEquipoBodegaCorte212_invoker.call(
          req => MnuReportes_7.reportEquipoBodegaCorte(req, tipo))
      }
  
    // @LINE:349
    case controllers_MnuReportes_reportEquipoBodega213_route(params@_) =>
      call { 
        controllers_MnuReportes_reportEquipoBodega213_invoker.call(
          req => MnuReportes_7.reportEquipoBodega(req))
      }
  
    // @LINE:350
    case controllers_MnuReportes_reportEquipoBodegaXEquipo214_route(params@_) =>
      call { 
        controllers_MnuReportes_reportEquipoBodegaXEquipo214_invoker.call(
          req => MnuReportes_7.reportEquipoBodegaXEquipo(req))
      }
  
    // @LINE:351
    case controllers_MnuReportes_reportEquipoBodegaExcel215_route(params@_) =>
      call { 
        controllers_MnuReportes_reportEquipoBodegaExcel215_invoker.call(
          req => MnuReportes_7.reportEquipoBodegaExcel(req))
      }
  
    // @LINE:352
    case controllers_MnuReportes_reportEquipoBodegaTrazaEquipoEnBod216_route(params@_) =>
      call { 
        controllers_MnuReportes_reportEquipoBodegaTrazaEquipoEnBod216_invoker.call(
          req => MnuReportes_7.reportEquipoBodegaTrazaEquipoEnBod(req))
      }
  
    // @LINE:355
    case controllers_MnuReportes_reportInventarioBodegaCorte217_route(params@_) =>
      call(params.fromPath[String]("tipo", None)) { (tipo) =>
        controllers_MnuReportes_reportInventarioBodegaCorte217_invoker.call(
          req => MnuReportes_7.reportInventarioBodegaCorte(req, tipo))
      }
  
    // @LINE:356
    case controllers_MnuReportes_reportInventarioBodega218_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioBodega218_invoker.call(
          req => MnuReportes_7.reportInventarioBodega(req))
      }
  
    // @LINE:357
    case controllers_MnuReportes_reportInventarioSelectivoXBodega219_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioSelectivoXBodega219_invoker.call(
          req => MnuReportes_7.reportInventarioSelectivoXBodega(req))
      }
  
    // @LINE:358
    case controllers_MnuReportes_reportInventProyectoTrazaEquipoEnBod220_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventProyectoTrazaEquipoEnBod220_invoker.call(
          req => MnuReportes_7.reportInventProyectoTrazaEquipoEnBod(req))
      }
  
    // @LINE:359
    case controllers_MnuReportes_reportInventarioSelectivoXBodegaExcel221_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioSelectivoXBodegaExcel221_invoker.call(
          req => MnuReportes_7.reportInventarioSelectivoXBodegaExcel(req))
      }
  
    // @LINE:361
    case controllers_MnuReportes_reportInventarioGrupoCorte222_route(params@_) =>
      call(params.fromPath[String]("tipo", None)) { (tipo) =>
        controllers_MnuReportes_reportInventarioGrupoCorte222_invoker.call(
          req => MnuReportes_7.reportInventarioGrupoCorte(req, tipo))
      }
  
    // @LINE:362
    case controllers_MnuReportes_reportInventarioGrupo223_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioGrupo223_invoker.call(
          req => MnuReportes_7.reportInventarioGrupo(req))
      }
  
    // @LINE:363
    case controllers_MnuReportes_reportInventarioSelectivoXGrupo224_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioSelectivoXGrupo224_invoker.call(
          req => MnuReportes_7.reportInventarioSelectivoXGrupo(req))
      }
  
    // @LINE:364
    case controllers_MnuReportes_reportInventarioSelectivoXGrupoExcel225_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioSelectivoXGrupoExcel225_invoker.call(
          req => MnuReportes_7.reportInventarioSelectivoXGrupoExcel(req))
      }
  
    // @LINE:366
    case controllers_MnuReportes_reportInventarioMatrizCorte226_route(params@_) =>
      call(params.fromPath[String]("tipo", None)) { (tipo) =>
        controllers_MnuReportes_reportInventarioMatrizCorte226_invoker.call(
          req => MnuReportes_7.reportInventarioMatrizCorte(req, tipo))
      }
  
    // @LINE:367
    case controllers_MnuReportes_reportInventarioMatriz227_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioMatriz227_invoker.call(
          req => MnuReportes_7.reportInventarioMatriz(req))
      }
  
    // @LINE:368
    case controllers_MnuReportes_reportInventarioMatrizExcel228_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioMatrizExcel228_invoker.call(
          req => MnuReportes_7.reportInventarioMatrizExcel(req))
      }
  
    // @LINE:370
    case controllers_MnuReportes_reportInventarioTodo229_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioTodo229_invoker.call(
          req => MnuReportes_7.reportInventarioTodo(req))
      }
  
    // @LINE:371
    case controllers_MnuReportes_reportInventarioTodoExcel230_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioTodoExcel230_invoker.call(
          req => MnuReportes_7.reportInventarioTodoExcel(req))
      }
  
    // @LINE:373
    case controllers_MnuReportes_reportInventarioProyecto231_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioProyecto231_invoker.call(
          req => MnuReportes_7.reportInventarioProyecto(req))
      }
  
    // @LINE:374
    case controllers_MnuReportes_reportInventarioProyectoDetalle232_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioProyectoDetalle232_invoker.call(
          req => MnuReportes_7.reportInventarioProyectoDetalle(req))
      }
  
    // @LINE:375
    case controllers_MnuReportes_reportInventarioProyectoDetalleExcel233_route(params@_) =>
      call { 
        controllers_MnuReportes_reportInventarioProyectoDetalleExcel233_invoker.call(
          req => MnuReportes_7.reportInventarioProyectoDetalleExcel(req))
      }
  
    // @LINE:377
    case controllers_MnuReportes_reportMailInventarioProyecto234_route(params@_) =>
      call { 
        controllers_MnuReportes_reportMailInventarioProyecto234_invoker.call(
          req => MnuReportes_7.reportMailInventarioProyecto(req))
      }
  
    // @LINE:378
    case controllers_MnuReportes_reportMailInventarioProyectoExcel235_route(params@_) =>
      call { 
        controllers_MnuReportes_reportMailInventarioProyectoExcel235_invoker.call(
          req => MnuReportes_7.reportMailInventarioProyectoExcel(req))
      }
  
    // @LINE:379
    case controllers_MnuReportes_reportMailInventarioProyectoEnviar236_route(params@_) =>
      call { 
        controllers_MnuReportes_reportMailInventarioProyectoEnviar236_invoker.call(
          req => MnuReportes_7.reportMailInventarioProyectoEnviar(req))
      }
  
    // @LINE:383
    case controllers_MnuReportes_reporteMovimientosPeriodoAgrupado237_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovimientosPeriodoAgrupado237_invoker.call(
          req => MnuReportes_7.reporteMovimientosPeriodoAgrupado(req))
      }
  
    // @LINE:384
    case controllers_MnuReportes_reporteMovimientosListaProyectosAgrupado238_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovimientosListaProyectosAgrupado238_invoker.call(
          req => MnuReportes_7.reporteMovimientosListaProyectosAgrupado(req))
      }
  
    // @LINE:385
    case controllers_MnuReportes_reporteMovimientosDetalleAgrupado239_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovimientosDetalleAgrupado239_invoker.call(
          req => MnuReportes_7.reporteMovimientosDetalleAgrupado(req))
      }
  
    // @LINE:386
    case controllers_MnuReportes_reporteMovimientosDetalleAgrupadoExcel240_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovimientosDetalleAgrupadoExcel240_invoker.call(
          req => MnuReportes_7.reporteMovimientosDetalleAgrupadoExcel(req))
      }
  
    // @LINE:388
    case controllers_MnuReportes_reporteMovSoloBodInternas0241_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovSoloBodInternas0241_invoker.call(
          req => MnuReportes_7.reporteMovSoloBodInternas0(req))
      }
  
    // @LINE:389
    case controllers_MnuReportes_reporteMovSoloBodInternas1242_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovSoloBodInternas1242_invoker.call(
          req => MnuReportes_7.reporteMovSoloBodInternas1(req))
      }
  
    // @LINE:390
    case controllers_MnuReportes_reporteMovSoloBodInternas2243_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovSoloBodInternas2243_invoker.call(
          req => MnuReportes_7.reporteMovSoloBodInternas2(req))
      }
  
    // @LINE:391
    case controllers_MnuReportes_reporteMovSoloBodInternas2Excel244_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovSoloBodInternas2Excel244_invoker.call(
          req => MnuReportes_7.reporteMovSoloBodInternas2Excel(req))
      }
  
    // @LINE:396
    case controllers_MnuReportes_reporteMovimientosPeriodo245_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovimientosPeriodo245_invoker.call(
          req => MnuReportes_7.reporteMovimientosPeriodo(req))
      }
  
    // @LINE:397
    case controllers_MnuReportes_reporteMovimientosListaProyectos246_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovimientosListaProyectos246_invoker.call(
          req => MnuReportes_7.reporteMovimientosListaProyectos(req))
      }
  
    // @LINE:398
    case controllers_MnuReportes_reporteMovimientosDetalle247_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovimientosDetalle247_invoker.call(
          req => MnuReportes_7.reporteMovimientosDetalle(req))
      }
  
    // @LINE:399
    case controllers_MnuReportes_reporteMovimientosDetalleExcel248_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovimientosDetalleExcel248_invoker.call(
          req => MnuReportes_7.reporteMovimientosDetalleExcel(req))
      }
  
    // @LINE:401
    case controllers_MnuReportes_reporteMovimientosListaProyectosIE249_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovimientosListaProyectosIE249_invoker.call(
          req => MnuReportes_7.reporteMovimientosListaProyectosIE(req))
      }
  
    // @LINE:402
    case controllers_MnuReportes_reporteMovimientosDetalleIE250_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovimientosDetalleIE250_invoker.call(
          req => MnuReportes_7.reporteMovimientosDetalleIE(req))
      }
  
    // @LINE:403
    case controllers_MnuReportes_reporteMovimientosIEExcel251_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteMovimientosIEExcel251_invoker.call(
          req => MnuReportes_7.reporteMovimientosIEExcel(req))
      }
  
    // @LINE:405
    case controllers_MnuReportes_reporteExcedentesListaProyectos252_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteExcedentesListaProyectos252_invoker.call(
          req => MnuReportes_7.reporteExcedentesListaProyectos(req))
      }
  
    // @LINE:406
    case controllers_MnuReportes_reporteExcedentesDetalle253_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteExcedentesDetalle253_invoker.call(
          req => MnuReportes_7.reporteExcedentesDetalle(req))
      }
  
    // @LINE:407
    case controllers_MnuReportes_reporteExcedentesDetalleExcel254_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteExcedentesDetalleExcel254_invoker.call(
          req => MnuReportes_7.reporteExcedentesDetalleExcel(req))
      }
  
    // @LINE:409
    case controllers_MnuReportes_reporteEstadosTodos255_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEstadosTodos255_invoker.call(
          req => MnuReportes_7.reporteEstadosTodos(req))
      }
  
    // @LINE:410
    case controllers_MnuReportes_reporteEstadosTodosDetalle256_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEstadosTodosDetalle256_invoker.call(
          req => MnuReportes_7.reporteEstadosTodosDetalle(req))
      }
  
    // @LINE:411
    case controllers_MnuReportes_reporteEstadosTodosDetalleExcel257_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEstadosTodosDetalleExcel257_invoker.call(
          req => MnuReportes_7.reporteEstadosTodosDetalleExcel(req))
      }
  
    // @LINE:413
    case controllers_MnuReportes_reporteEstadosPeriodo0258_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEstadosPeriodo0258_invoker.call(
          req => MnuReportes_7.reporteEstadosPeriodo0(req))
      }
  
    // @LINE:414
    case controllers_MnuReportes_reporteEstadosPeriodo1259_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEstadosPeriodo1259_invoker.call(
          req => MnuReportes_7.reporteEstadosPeriodo1(req))
      }
  
    // @LINE:415
    case controllers_MnuReportes_reporteEstadosPeriodo1Excel260_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEstadosPeriodo1Excel260_invoker.call(
          req => MnuReportes_7.reporteEstadosPeriodo1Excel(req))
      }
  
    // @LINE:418
    case controllers_MnuReportes_reporteEstadosAll0261_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEstadosAll0261_invoker.call(
          req => MnuReportes_7.reporteEstadosAll0(req))
      }
  
    // @LINE:419
    case controllers_MnuReportes_reporteEstadosAll1262_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEstadosAll1262_invoker.call(
          req => MnuReportes_7.reporteEstadosAll1(req))
      }
  
    // @LINE:420
    case controllers_MnuReportes_reporteEstadosAll1Excel263_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEstadosAll1Excel263_invoker.call(
          req => MnuReportes_7.reporteEstadosAll1Excel(req))
      }
  
    // @LINE:423
    case controllers_MnuReportes_reportTrazaEquipo264_route(params@_) =>
      call { 
        controllers_MnuReportes_reportTrazaEquipo264_invoker.call(
          req => MnuReportes_7.reportTrazaEquipo(req))
      }
  
    // @LINE:424
    case controllers_MnuReportes_reportTrazaEquipo2265_route(params@_) =>
      call { 
        controllers_MnuReportes_reportTrazaEquipo2265_invoker.call(
          req => MnuReportes_7.reportTrazaEquipo2(req))
      }
  
    // @LINE:425
    case controllers_MnuReportes_reportTrazaEquipo2Excel266_route(params@_) =>
      call { 
        controllers_MnuReportes_reportTrazaEquipo2Excel266_invoker.call(
          req => MnuReportes_7.reportTrazaEquipo2Excel(req))
      }
  
    // @LINE:427
    case controllers_MnuReportes_reporteEjecutivo1267_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEjecutivo1267_invoker.call(
          req => MnuReportes_7.reporteEjecutivo1(req))
      }
  
    // @LINE:428
    case controllers_MnuReportes_graficoInversionExcel268_route(params@_) =>
      call { 
        controllers_MnuReportes_graficoInversionExcel268_invoker.call(
          req => MnuReportes_7.graficoInversionExcel(req))
      }
  
    // @LINE:429
    case controllers_MnuReportes_graficoOcupacionExcel269_route(params@_) =>
      call { 
        controllers_MnuReportes_graficoOcupacionExcel269_invoker.call(
          req => MnuReportes_7.graficoOcupacionExcel(req))
      }
  
    // @LINE:430
    case controllers_MnuReportes_graficoValorizadoGrupoExcel270_route(params@_) =>
      call { 
        controllers_MnuReportes_graficoValorizadoGrupoExcel270_invoker.call(
          req => MnuReportes_7.graficoValorizadoGrupoExcel(req))
      }
  
    // @LINE:431
    case controllers_MnuReportes_graficoUnidadesExcel271_route(params@_) =>
      call { 
        controllers_MnuReportes_graficoUnidadesExcel271_invoker.call(
          req => MnuReportes_7.graficoUnidadesExcel(req))
      }
  
    // @LINE:432
    case controllers_MnuReportes_graficoValorizadoBodegaExcel272_route(params@_) =>
      call { 
        controllers_MnuReportes_graficoValorizadoBodegaExcel272_invoker.call(
          req => MnuReportes_7.graficoValorizadoBodegaExcel(req))
      }
  
    // @LINE:433
    case controllers_MnuReportes_graficoPotencialExcel273_route(params@_) =>
      call { 
        controllers_MnuReportes_graficoPotencialExcel273_invoker.call(
          req => MnuReportes_7.graficoPotencialExcel(req))
      }
  
    // @LINE:435
    case controllers_MnuReportes_reporteGerencial274_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteGerencial274_invoker.call(
          req => MnuReportes_7.reporteGerencial(req))
      }
  
    // @LINE:436
    case controllers_MnuReportes_reporteGerencialGrupo275_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteGerencialGrupo275_invoker.call(
          req => MnuReportes_7.reporteGerencialGrupo(req))
      }
  
    // @LINE:437
    case controllers_MnuReportes_reporteGerencialGrupoDetalle276_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteGerencialGrupoDetalle276_invoker.call(
          req => MnuReportes_7.reporteGerencialGrupoDetalle(req))
      }
  
    // @LINE:439
    case controllers_MnuReportes_reporteGerencialKG277_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteGerencialKG277_invoker.call(
          req => MnuReportes_7.reporteGerencialKG(req))
      }
  
    // @LINE:440
    case controllers_MnuReportes_reporteGerencialKGExcel278_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteGerencialKGExcel278_invoker.call(
          req => MnuReportes_7.reporteGerencialKGExcel(req))
      }
  
    // @LINE:442
    case controllers_MnuReportes_reporteGerencialVentas279_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteGerencialVentas279_invoker.call(
          req => MnuReportes_7.reporteGerencialVentas(req))
      }
  
    // @LINE:444
    case controllers_MnuReportes_reportGraficoMovResumen280_route(params@_) =>
      call(params.fromPath[String]("listG", None), params.fromPath[String]("vista", None)) { (listG, vista) =>
        controllers_MnuReportes_reportGraficoMovResumen280_invoker.call(
          req => MnuReportes_7.reportGraficoMovResumen(req, listG, vista))
      }
  
    // @LINE:445
    case controllers_MnuReportes_refreshGraficoMovResumen281_route(params@_) =>
      call { 
        controllers_MnuReportes_refreshGraficoMovResumen281_invoker.call(
          req => MnuReportes_7.refreshGraficoMovResumen(req))
      }
  
    // @LINE:446
    case controllers_MnuReportes_reportGraficoMovPorGrupo282_route(params@_) =>
      call(params.fromPath[String]("lG", None), params.fromPath[String]("lB", None), params.fromPath[String]("vista", None), params.fromPath[String]("idT", None)) { (lG, lB, vista, idT) =>
        controllers_MnuReportes_reportGraficoMovPorGrupo282_invoker.call(
          req => MnuReportes_7.reportGraficoMovPorGrupo(req, lG, lB, vista, idT))
      }
  
    // @LINE:447
    case controllers_MnuReportes_refreshGraficoMovPorGrupo283_route(params@_) =>
      call(params.fromPath[String]("idTipoBodega", None)) { (idTipoBodega) =>
        controllers_MnuReportes_refreshGraficoMovPorGrupo283_invoker.call(
          req => MnuReportes_7.refreshGraficoMovPorGrupo(req, idTipoBodega))
      }
  
    // @LINE:448
    case controllers_MnuReportes_reportGraficoMovUso284_route(params@_) =>
      call { 
        controllers_MnuReportes_reportGraficoMovUso284_invoker.call(
          req => MnuReportes_7.reportGraficoMovUso(req))
      }
  
    // @LINE:449
    case controllers_MnuReportes_reportGraficoMovUsoPropiedad285_route(params@_) =>
      call { 
        controllers_MnuReportes_reportGraficoMovUsoPropiedad285_invoker.call(
          req => MnuReportes_7.reportGraficoMovUsoPropiedad(req))
      }
  
    // @LINE:451
    case controllers_MnuReportes_reportFacturaPeriodo286_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaPeriodo286_invoker.call(
          req => MnuReportes_7.reportFacturaPeriodo(req))
      }
  
    // @LINE:452
    case controllers_MnuReportes_reportFacturaProyecto287_route(params@_) =>
      call(params.fromPath[String]("archivoPDF", None)) { (archivoPDF) =>
        controllers_MnuReportes_reportFacturaProyecto287_invoker.call(
          req => MnuReportes_7.reportFacturaProyecto(req, archivoPDF))
      }
  
    // @LINE:453
    case controllers_MnuReportes_reportFacturaProyectoGet288_route(params@_) =>
      call(params.fromPath[String]("p", None), params.fromPath[String]("d", None), params.fromPath[String]("a", None), params.fromPath[Double]("uf", None), params.fromPath[Double]("us", None), params.fromPath[Double]("eu", None)) { (p, d, a, uf, us, eu) =>
        controllers_MnuReportes_reportFacturaProyectoGet288_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoGet(req, p, d, a, uf, us, eu))
      }
  
    // @LINE:454
    case controllers_MnuReportes_reportFacturaProyectoExcel289_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoExcel289_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoExcel(req))
      }
  
    // @LINE:455
    case controllers_MnuReportes_reportFacturaProyectoDetalle290_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalle290_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalle(req))
      }
  
    // @LINE:456
    case controllers_MnuReportes_reportFacturaProyectoDetExcel291_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetExcel291_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetExcel(req))
      }
  
    // @LINE:457
    case controllers_MnuReportes_generarProformaPdfXlsxXmlJson292_route(params@_) =>
      call { 
        controllers_MnuReportes_generarProformaPdfXlsxXmlJson292_invoker.call(
          req => MnuReportes_7.generarProformaPdfXlsxXmlJson(req))
      }
  
    // @LINE:459
    case controllers_MnuReportes_reportFacturaPeriodoH293_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaPeriodoH293_invoker.call(
          req => MnuReportes_7.reportFacturaPeriodoH(req))
      }
  
    // @LINE:460
    case controllers_MnuReportes_reportFacturaProyectoH294_route(params@_) =>
      call(params.fromPath[String]("archivoPDF", None)) { (archivoPDF) =>
        controllers_MnuReportes_reportFacturaProyectoH294_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoH(req, archivoPDF))
      }
  
    // @LINE:461
    case controllers_MnuReportes_reportFacturaProyectoHExcel295_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoHExcel295_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoHExcel(req))
      }
  
    // @LINE:462
    case controllers_MnuReportes_reportFacturaProyectoHGet296_route(params@_) =>
      call(params.fromPath[String]("p", None), params.fromPath[String]("d", None), params.fromPath[String]("a", None), params.fromPath[Double]("uf", None), params.fromPath[Double]("us", None), params.fromPath[Double]("eu", None)) { (p, d, a, uf, us, eu) =>
        controllers_MnuReportes_reportFacturaProyectoHGet296_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoHGet(req, p, d, a, uf, us, eu))
      }
  
    // @LINE:465
    case controllers_MnuReportes_proformaListaHPeriodo297_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaListaHPeriodo297_invoker.call(
          req => MnuReportes_7.proformaListaHPeriodo(req))
      }
  
    // @LINE:466
    case controllers_MnuReportes_proformaListaH298_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaListaH298_invoker.call(
          req => MnuReportes_7.proformaListaH(req))
      }
  
    // @LINE:467
    case controllers_MnuReportes_proformaListaHGet299_route(params@_) =>
      call(params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None)) { (desde, hasta) =>
        controllers_MnuReportes_proformaListaHGet299_invoker.call(
          req => MnuReportes_7.proformaListaHGet(req, desde, hasta))
      }
  
    // @LINE:468
    case controllers_MnuReportes_proformaEliminaH300_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaEliminaH300_invoker.call(
          req => MnuReportes_7.proformaEliminaH(req))
      }
  
    // @LINE:474
    case controllers_MnuReportes_reportFacturaProyectoDetalleH301_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalleH301_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalleH(req))
      }
  
    // @LINE:475
    case controllers_MnuReportes_reportFacturaProyectoDetalleHExcel302_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalleHExcel302_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalleHExcel(req))
      }
  
    // @LINE:476
    case controllers_MnuReportes_reportFacturaProyectoDetalleHProforma303_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalleHProforma303_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalleHProforma(req))
      }
  
    // @LINE:478
    case controllers_MnuReportes_reportFacturaProyectoDetalleHVta304_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalleHVta304_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalleHVta(req))
      }
  
    // @LINE:479
    case controllers_MnuReportes_reportFacturaProyectoDetalleHVtaExcel305_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalleHVtaExcel305_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalleHVtaExcel(req))
      }
  
    // @LINE:480
    case controllers_MnuReportes_reportFacturaProyectoDetalleHVtaProforma306_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalleHVtaProforma306_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalleHVtaProforma(req))
      }
  
    // @LINE:482
    case controllers_MnuReportes_reportFacturaProyectoDetalleHServ307_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalleHServ307_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalleHServ(req))
      }
  
    // @LINE:483
    case controllers_MnuReportes_reportFacturaProyectoDetalleHServExcel308_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalleHServExcel308_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalleHServExcel(req))
      }
  
    // @LINE:484
    case controllers_MnuReportes_reportFacturaProyectoDetalleHServProforma309_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalleHServProforma309_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalleHServProforma(req))
      }
  
    // @LINE:486
    case controllers_MnuReportes_reportFacturaProyectoDetalleHALL310_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalleHALL310_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalleHALL(req))
      }
  
    // @LINE:487
    case controllers_MnuReportes_reportFacturaProyectoDetalleHAllExcel311_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalleHAllExcel311_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalleHAllExcel(req))
      }
  
    // @LINE:488
    case controllers_MnuReportes_reportFacturaProyectoDetalleHAllProforma312_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaProyectoDetalleHAllProforma312_invoker.call(
          req => MnuReportes_7.reportFacturaProyectoDetalleHAllProforma(req))
      }
  
    // @LINE:492
    case controllers_MnuReportes_reportFacturaPeriodoResumen0313_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaPeriodoResumen0313_invoker.call(
          req => MnuReportes_7.reportFacturaPeriodoResumen0(req))
      }
  
    // @LINE:493
    case controllers_MnuReportes_reportFacturaResumen0314_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaResumen0314_invoker.call(
          req => MnuReportes_7.reportFacturaResumen0(req))
      }
  
    // @LINE:494
    case controllers_MnuReportes_reportFacturaPeriodoResumen315_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaPeriodoResumen315_invoker.call(
          req => MnuReportes_7.reportFacturaPeriodoResumen(req))
      }
  
    // @LINE:495
    case controllers_MnuReportes_reportFacturaResumen316_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaResumen316_invoker.call(
          req => MnuReportes_7.reportFacturaResumen(req))
      }
  
    // @LINE:496
    case controllers_MnuReportes_reportFacturaPeriodoResumen2317_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaPeriodoResumen2317_invoker.call(
          req => MnuReportes_7.reportFacturaPeriodoResumen2(req))
      }
  
    // @LINE:497
    case controllers_MnuReportes_reportFacturaResumen2318_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaResumen2318_invoker.call(
          req => MnuReportes_7.reportFacturaResumen2(req))
      }
  
    // @LINE:499
    case controllers_MnuReportes_reportFacturaResumenJson319_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaResumenJson319_invoker.call(
          req => MnuReportes_7.reportFacturaResumenJson(req))
      }
  
    // @LINE:500
    case controllers_MnuReportes_reportFacturaResumenExcel0320_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaResumenExcel0320_invoker.call(
          req => MnuReportes_7.reportFacturaResumenExcel0(req))
      }
  
    // @LINE:501
    case controllers_MnuReportes_reportFacturaResumenExcel321_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaResumenExcel321_invoker.call(
          req => MnuReportes_7.reportFacturaResumenExcel(req))
      }
  
    // @LINE:502
    case controllers_MnuReportes_reportFacturaResumenExcel2322_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaResumenExcel2322_invoker.call(
          req => MnuReportes_7.reportFacturaResumenExcel2(req))
      }
  
    // @LINE:504
    case controllers_MnuReportes_reportFacturaConsolidado323_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaConsolidado323_invoker.call(
          req => MnuReportes_7.reportFacturaConsolidado(req))
      }
  
    // @LINE:505
    case controllers_MnuReportes_reportFacturaConsolidadoRtp324_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaConsolidadoRtp324_invoker.call(
          req => MnuReportes_7.reportFacturaConsolidadoRtp(req))
      }
  
    // @LINE:506
    case controllers_MnuReportes_reportFacturaConsolidadoRtpExcel325_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFacturaConsolidadoRtpExcel325_invoker.call(
          req => MnuReportes_7.reportFacturaConsolidadoRtpExcel(req))
      }
  
    // @LINE:508
    case controllers_MnuReportes_reportFactConsolconGrupo326_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFactConsolconGrupo326_invoker.call(
          req => MnuReportes_7.reportFactConsolconGrupo(req))
      }
  
    // @LINE:509
    case controllers_MnuReportes_reportFactConsolconGrupoRtp327_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFactConsolconGrupoRtp327_invoker.call(
          req => MnuReportes_7.reportFactConsolconGrupoRtp(req))
      }
  
    // @LINE:510
    case controllers_MnuReportes_reportFactConsolconGrupoRtpExcel328_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFactConsolconGrupoRtpExcel328_invoker.call(
          req => MnuReportes_7.reportFactConsolconGrupoRtpExcel(req))
      }
  
    // @LINE:512
    case controllers_MnuReportes_reportFactConsolconEquipos329_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFactConsolconEquipos329_invoker.call(
          req => MnuReportes_7.reportFactConsolconEquipos(req))
      }
  
    // @LINE:513
    case controllers_MnuReportes_reportFactConsolconEquiposRtp330_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFactConsolconEquiposRtp330_invoker.call(
          req => MnuReportes_7.reportFactConsolconEquiposRtp(req))
      }
  
    // @LINE:514
    case controllers_MnuReportes_reportFactConsolconEquiposRtpExcel331_route(params@_) =>
      call { 
        controllers_MnuReportes_reportFactConsolconEquiposRtpExcel331_invoker.call(
          req => MnuReportes_7.reportFactConsolconEquiposRtpExcel(req))
      }
  
    // @LINE:518
    case controllers_MnuReportes_proformaListaPeriodo332_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaListaPeriodo332_invoker.call(
          req => MnuReportes_7.proformaListaPeriodo(req))
      }
  
    // @LINE:519
    case controllers_MnuReportes_proformaLista333_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaLista333_invoker.call(
          req => MnuReportes_7.proformaLista(req))
      }
  
    // @LINE:520
    case controllers_MnuReportes_proformaListaGet334_route(params@_) =>
      call(params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None)) { (desde, hasta) =>
        controllers_MnuReportes_proformaListaGet334_invoker.call(
          req => MnuReportes_7.proformaListaGet(req, desde, hasta))
      }
  
    // @LINE:521
    case controllers_MnuReportes_proformaListaExcel335_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaListaExcel335_invoker.call(
          req => MnuReportes_7.proformaListaExcel(req))
      }
  
    // @LINE:522
    case controllers_MnuReportes_generaProformaXml336_route(params@_) =>
      call { 
        controllers_MnuReportes_generaProformaXml336_invoker.call(
          req => MnuReportes_7.generaProformaXml(req))
      }
  
    // @LINE:523
    case controllers_MnuReportes_sendXMLFacura337_route(params@_) =>
      call(params.fromPath[Long]("id_proforma", None), params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None)) { (id_proforma, desde, hasta) =>
        controllers_MnuReportes_sendXMLFacura337_invoker.call(
          req => MnuReportes_7.sendXMLFacura(req, id_proforma, desde, hasta))
      }
  
    // @LINE:525
    case controllers_MnuReportes_generaProformaApiManager338_route(params@_) =>
      call { 
        controllers_MnuReportes_generaProformaApiManager338_invoker.call(
          req => MnuReportes_7.generaProformaApiManager(req))
      }
  
    // @LINE:526
    case controllers_MnuReportes_generaProformaApiNubox339_route(params@_) =>
      call { 
        controllers_MnuReportes_generaProformaApiNubox339_invoker.call(
          req => MnuReportes_7.generaProformaApiNubox(req))
      }
  
    // @LINE:527
    case controllers_MnuReportes_generaProformaApiSapConconcreto340_route(params@_) =>
      call { 
        controllers_MnuReportes_generaProformaApiSapConconcreto340_invoker.call(
          req => MnuReportes_7.generaProformaApiSapConconcreto(req))
      }
  
    // @LINE:528
    case controllers_MnuReportes_generaProformaWebMaximise341_route(params@_) =>
      call { 
        controllers_MnuReportes_generaProformaWebMaximise341_invoker.call(
          req => MnuReportes_7.generaProformaWebMaximise(req))
      }
  
    // @LINE:530
    case controllers_MnuReportes_downFacturaMaximise342_route(params@_) =>
      call(params.fromPath[Long]("nroInte", None), params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None)) { (nroInte, desde, hasta) =>
        controllers_MnuReportes_downFacturaMaximise342_invoker.call(
          req => MnuReportes_7.downFacturaMaximise(nroInte, req, desde, hasta))
      }
  
    // @LINE:533
    case controllers_MnuReportes_generaFacturaIConstruye343_route(params@_) =>
      call { 
        controllers_MnuReportes_generaFacturaIConstruye343_invoker.call(
          req => MnuReportes_7.generaFacturaIConstruye(req))
      }
  
    // @LINE:534
    case controllers_MnuReportes_downFacturaIconstruye344_route(params@_) =>
      call { 
        controllers_MnuReportes_downFacturaIconstruye344_invoker.call(
          req => MnuReportes_7.downFacturaIconstruye(req))
      }
  
    // @LINE:536
    case controllers_MnuReportes_generaProformaApiSapSchwager345_route(params@_) =>
      call { 
        controllers_MnuReportes_generaProformaApiSapSchwager345_invoker.call(
          req => MnuReportes_7.generaProformaApiSapSchwager(req))
      }
  
    // @LINE:537
    case controllers_MnuReportes_generaProformaApiRelBase346_route(params@_) =>
      call { 
        controllers_MnuReportes_generaProformaApiRelBase346_invoker.call(
          req => MnuReportes_7.generaProformaApiRelBase(req))
      }
  
    // @LINE:538
    case controllers_MnuReportes_generaProformaApiRelBase2347_route(params@_) =>
      call { 
        controllers_MnuReportes_generaProformaApiRelBase2347_invoker.call(
          req => MnuReportes_7.generaProformaApiRelBase2(req))
      }
  
    // @LINE:539
    case controllers_MnuReportes_validarClienteAjax348_route(params@_) =>
      call { 
        controllers_MnuReportes_validarClienteAjax348_invoker.call(
          req => MnuReportes_7.validarClienteAjax(req))
      }
  
    // @LINE:543
    case controllers_MnuReportes_proformaElimina349_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaElimina349_invoker.call(
          req => MnuReportes_7.proformaElimina(req))
      }
  
    // @LINE:545
    case controllers_MnuReportes_ajustesEp350_route(params@_) =>
      call { 
        controllers_MnuReportes_ajustesEp350_invoker.call(
          req => MnuReportes_7.ajustesEp(req))
      }
  
    // @LINE:546
    case controllers_MnuReportes_ajustesEpListado351_route(params@_) =>
      call { 
        controllers_MnuReportes_ajustesEpListado351_invoker.call(
          req => MnuReportes_7.ajustesEpListado(req))
      }
  
    // @LINE:547
    case controllers_MnuReportes_ajusteGrabaPDF352_route(params@_) =>
      call { 
        controllers_MnuReportes_ajusteGrabaPDF352_invoker.call(
          req => MnuReportes_7.ajusteGrabaPDF(req))
      }
  
    // @LINE:548
    case controllers_MnuReportes_ajustesEpModificar353_route(params@_) =>
      call { 
        controllers_MnuReportes_ajustesEpModificar353_invoker.call(
          req => MnuReportes_7.ajustesEpModificar(req))
      }
  
    // @LINE:549
    case controllers_MnuReportes_ajustesEpUpdate354_route(params@_) =>
      call { 
        controllers_MnuReportes_ajustesEpUpdate354_invoker.call(
          req => MnuReportes_7.ajustesEpUpdate(req))
      }
  
    // @LINE:550
    case controllers_MnuReportes_ajustesEpEliminar355_route(params@_) =>
      call { 
        controllers_MnuReportes_ajustesEpEliminar355_invoker.call(
          req => MnuReportes_7.ajustesEpEliminar(req))
      }
  
    // @LINE:551
    case controllers_MnuReportes_ajustesEpNuevo356_route(params@_) =>
      call { 
        controllers_MnuReportes_ajustesEpNuevo356_invoker.call(
          req => MnuReportes_7.ajustesEpNuevo(req))
      }
  
    // @LINE:552
    case controllers_MnuReportes_ajustesEpGrabar357_route(params@_) =>
      call { 
        controllers_MnuReportes_ajustesEpGrabar357_invoker.call(
          req => MnuReportes_7.ajustesEpGrabar(req))
      }
  
    // @LINE:554
    case controllers_MnuReportes_ajustesEpRpt358_route(params@_) =>
      call { 
        controllers_MnuReportes_ajustesEpRpt358_invoker.call(
          req => MnuReportes_7.ajustesEpRpt(req))
      }
  
    // @LINE:555
    case controllers_MnuReportes_ajustesEpRptDetalle359_route(params@_) =>
      call { 
        controllers_MnuReportes_ajustesEpRptDetalle359_invoker.call(
          req => MnuReportes_7.ajustesEpRptDetalle(req))
      }
  
    // @LINE:557
    case controllers_MnuReportes_ajustesPeriodoEpRpt1360_route(params@_) =>
      call { 
        controllers_MnuReportes_ajustesPeriodoEpRpt1360_invoker.call(
          req => MnuReportes_7.ajustesPeriodoEpRpt1(req))
      }
  
    // @LINE:558
    case controllers_MnuReportes_ajustesPeriodoEpRpt2361_route(params@_) =>
      call { 
        controllers_MnuReportes_ajustesPeriodoEpRpt2361_invoker.call(
          req => MnuReportes_7.ajustesPeriodoEpRpt2(req))
      }
  
    // @LINE:561
    case controllers_MnuReportes_hoheReportTodo0362_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheReportTodo0362_invoker.call(
          req => MnuReportes_7.hoheReportTodo0(req))
      }
  
    // @LINE:562
    case controllers_MnuReportes_hoheReportTodo363_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheReportTodo363_invoker.call(
          req => MnuReportes_7.hoheReportTodo(req))
      }
  
    // @LINE:563
    case controllers_MnuReportes_hoheTodoResumen0364_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheTodoResumen0364_invoker.call(
          req => MnuReportes_7.hoheTodoResumen0(req))
      }
  
    // @LINE:564
    case controllers_MnuReportes_hoheTodoResumen365_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheTodoResumen365_invoker.call(
          req => MnuReportes_7.hoheTodoResumen(req))
      }
  
    // @LINE:566
    case controllers_MnuReportes_hoheMatrizVerticalInventario0366_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheMatrizVerticalInventario0366_invoker.call(
          req => MnuReportes_7.hoheMatrizVerticalInventario0(req))
      }
  
    // @LINE:567
    case controllers_MnuReportes_hoheMatrizVerticalInventario367_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheMatrizVerticalInventario367_invoker.call(
          req => MnuReportes_7.hoheMatrizVerticalInventario(req))
      }
  
    // @LINE:569
    case controllers_MnuReportes_hoheMatrizVerticalInv0Coti368_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheMatrizVerticalInv0Coti368_invoker.call(
          req => MnuReportes_7.hoheMatrizVerticalInv0Coti(req))
      }
  
    // @LINE:570
    case controllers_MnuReportes_hoheMatrizVerticalInvCoti369_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheMatrizVerticalInvCoti369_invoker.call(
          req => MnuReportes_7.hoheMatrizVerticalInvCoti(req))
      }
  
    // @LINE:572
    case controllers_MnuReportes_hoheEstadoCotiOt370_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheEstadoCotiOt370_invoker.call(
          req => MnuReportes_7.hoheEstadoCotiOt(req))
      }
  
    // @LINE:573
    case controllers_MnuReportes_hoheEstadoCotiOtExcel371_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheEstadoCotiOtExcel371_invoker.call(
          req => MnuReportes_7.hoheEstadoCotiOtExcel(req))
      }
  
    // @LINE:575
    case controllers_MnuReportes_hoheEstadoCotiSinOt372_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheEstadoCotiSinOt372_invoker.call(
          req => MnuReportes_7.hoheEstadoCotiSinOt(req))
      }
  
    // @LINE:576
    case controllers_MnuReportes_hoheEstadoCotiSinOtExcel373_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheEstadoCotiSinOtExcel373_invoker.call(
          req => MnuReportes_7.hoheEstadoCotiSinOtExcel(req))
      }
  
    // @LINE:578
    case controllers_MnuReportes_hoheValySubePlantillaNV374_route(params@_) =>
      call { 
        controllers_MnuReportes_hoheValySubePlantillaNV374_invoker.call(
          req => MnuReportes_7.hoheValySubePlantillaNV(req))
      }
  
    // @LINE:579
    case controllers_MnuReportes_hohePlantillaNV375_route(params@_) =>
      call { 
        controllers_MnuReportes_hohePlantillaNV375_invoker.call(
          req => MnuReportes_7.hohePlantillaNV(req))
      }
  
    // @LINE:584
    case controllers_MnuDisponibilidad_disponibleActualizaAll376_route(params@_) =>
      call { 
        controllers_MnuDisponibilidad_disponibleActualizaAll376_invoker.call(
          req => MnuDisponibilidad_8.disponibleActualizaAll(req))
      }
  
    // @LINE:585
    case controllers_MnuDisponibilidad_cambiaFechaDisponibleAjax377_route(params@_) =>
      call { 
        controllers_MnuDisponibilidad_cambiaFechaDisponibleAjax377_invoker.call(
          req => MnuDisponibilidad_8.cambiaFechaDisponibleAjax(req))
      }
  
    // @LINE:586
    case controllers_MnuDisponibilidad_reportDisponibilidadAll378_route(params@_) =>
      call { 
        controllers_MnuDisponibilidad_reportDisponibilidadAll378_invoker.call(
          req => MnuDisponibilidad_8.reportDisponibilidadAll(req))
      }
  
    // @LINE:587
    case controllers_MnuDisponibilidad_reportDisponibilidadAllExcel379_route(params@_) =>
      call { 
        controllers_MnuDisponibilidad_reportDisponibilidadAllExcel379_invoker.call(
          req => MnuDisponibilidad_8.reportDisponibilidadAllExcel(req))
      }
  
    // @LINE:589
    case controllers_MnuDisponibilidad_disponibleInscribirEquipo380_route(params@_) =>
      call { 
        controllers_MnuDisponibilidad_disponibleInscribirEquipo380_invoker.call(
          req => MnuDisponibilidad_8.disponibleInscribirEquipo(req))
      }
  
    // @LINE:590
    case controllers_MnuDisponibilidad_disponibleInscribirEquipo2381_route(params@_) =>
      call { 
        controllers_MnuDisponibilidad_disponibleInscribirEquipo2381_invoker.call(
          req => MnuDisponibilidad_8.disponibleInscribirEquipo2(req))
      }
  
    // @LINE:591
    case controllers_MnuDisponibilidad_disponibleEliminarEquipo382_route(params@_) =>
      call { 
        controllers_MnuDisponibilidad_disponibleEliminarEquipo382_invoker.call(
          req => MnuDisponibilidad_8.disponibleEliminarEquipo(req))
      }
  
    // @LINE:592
    case controllers_MnuDisponibilidad_disponibleEliminarEquipo2383_route(params@_) =>
      call { 
        controllers_MnuDisponibilidad_disponibleEliminarEquipo2383_invoker.call(
          req => MnuDisponibilidad_8.disponibleEliminarEquipo2(req))
      }
  
    // @LINE:594
    case controllers_MnuDisponibilidad_disponibleInscribirBodega384_route(params@_) =>
      call { 
        controllers_MnuDisponibilidad_disponibleInscribirBodega384_invoker.call(
          req => MnuDisponibilidad_8.disponibleInscribirBodega(req))
      }
  
    // @LINE:595
    case controllers_MnuDisponibilidad_disponibleInscribirBodega2385_route(params@_) =>
      call { 
        controllers_MnuDisponibilidad_disponibleInscribirBodega2385_invoker.call(
          req => MnuDisponibilidad_8.disponibleInscribirBodega2(req))
      }
  
    // @LINE:596
    case controllers_MnuDisponibilidad_disponibleEliminarBodega386_route(params@_) =>
      call { 
        controllers_MnuDisponibilidad_disponibleEliminarBodega386_invoker.call(
          req => MnuDisponibilidad_8.disponibleEliminarBodega(req))
      }
  
    // @LINE:597
    case controllers_MnuDisponibilidad_disponibleEliminarBodega2387_route(params@_) =>
      call { 
        controllers_MnuDisponibilidad_disponibleEliminarBodega2387_invoker.call(
          req => MnuDisponibilidad_8.disponibleEliminarBodega2(req))
      }
  
    // @LINE:603
    case controllers_MnuQr_qrListaEquipos388_route(params@_) =>
      call { 
        controllers_MnuQr_qrListaEquipos388_invoker.call(
          req => MnuQr_17.qrListaEquipos(req))
      }
  
    // @LINE:604
    case controllers_MnuQr_qrSelectEquipos389_route(params@_) =>
      call { 
        controllers_MnuQr_qrSelectEquipos389_invoker.call(
          req => MnuQr_17.qrSelectEquipos(req))
      }
  
    // @LINE:605
    case controllers_MnuQr_qrAgregaEquipo390_route(params@_) =>
      call { 
        controllers_MnuQr_qrAgregaEquipo390_invoker.call(
          req => MnuQr_17.qrAgregaEquipo(req))
      }
  
    // @LINE:606
    case controllers_MnuQr_qrCambiaEstadoEquipos391_route(params@_) =>
      call { 
        controllers_MnuQr_qrCambiaEstadoEquipos391_invoker.call(
          req => MnuQr_17.qrCambiaEstadoEquipos(req))
      }
  
    // @LINE:608
    case controllers_MnuQr_qrRevisarDatos392_route(params@_) =>
      call { 
        controllers_MnuQr_qrRevisarDatos392_invoker.call(
          req => MnuQr_17.qrRevisarDatos(req))
      }
  
    // @LINE:609
    case controllers_MnuQr_qrRevisarDatosAllVigentes393_route(params@_) =>
      call { 
        controllers_MnuQr_qrRevisarDatosAllVigentes393_invoker.call(
          req => MnuQr_17.qrRevisarDatosAllVigentes(req))
      }
  
    // @LINE:610
    case controllers_MnuQr_qrRevisarDatosAllVigentesExcel394_route(params@_) =>
      call { 
        controllers_MnuQr_qrRevisarDatosAllVigentesExcel394_invoker.call(
          req => MnuQr_17.qrRevisarDatosAllVigentesExcel(req))
      }
  
    // @LINE:615
    case controllers_MnuQr_qrCambiarPubEquipo395_route(params@_) =>
      call { 
        controllers_MnuQr_qrCambiarPubEquipo395_invoker.call(
          req => MnuQr_17.qrCambiarPubEquipo(req))
      }
  
    // @LINE:616
    case controllers_MnuQr_qrCambiarPubQr396_route(params@_) =>
      call { 
        controllers_MnuQr_qrCambiarPubQr396_invoker.call(
          req => MnuQr_17.qrCambiarPubQr(req))
      }
  
    // @LINE:617
    case controllers_MnuQr_qrEditEquipo397_route(params@_) =>
      call { 
        controllers_MnuQr_qrEditEquipo397_invoker.call(
          req => MnuQr_17.qrEditEquipo(req))
      }
  
    // @LINE:618
    case controllers_MnuQr_qrAgregaCampoEquipo398_route(params@_) =>
      call { 
        controllers_MnuQr_qrAgregaCampoEquipo398_invoker.call(
          req => MnuQr_17.qrAgregaCampoEquipo(req))
      }
  
    // @LINE:619
    case controllers_MnuQr_qrActualizaPorCampo399_route(params@_) =>
      call { 
        controllers_MnuQr_qrActualizaPorCampo399_invoker.call(
          req => MnuQr_17.qrActualizaPorCampo(req))
      }
  
    // @LINE:620
    case controllers_MnuQr_qrEliminaCampoEquipo400_route(params@_) =>
      call { 
        controllers_MnuQr_qrEliminaCampoEquipo400_invoker.call(
          req => MnuQr_17.qrEliminaCampoEquipo(req))
      }
  
    // @LINE:621
    case controllers_MnuQr_qrGrabaAnexoEquipo401_route(params@_) =>
      call { 
        controllers_MnuQr_qrGrabaAnexoEquipo401_invoker.call(
          req => MnuQr_17.qrGrabaAnexoEquipo(req))
      }
  
    // @LINE:623
    case controllers_MnuQr_qrListaAtributoEquipos402_route(params@_) =>
      call { 
        controllers_MnuQr_qrListaAtributoEquipos402_invoker.call(
          req => MnuQr_17.qrListaAtributoEquipos(req))
      }
  
    // @LINE:624
    case controllers_MnuQr_qrEditarAtributoEquipo403_route(params@_) =>
      call(params.fromPath[Long]("idQrAtributoEquipo", None)) { (idQrAtributoEquipo) =>
        controllers_MnuQr_qrEditarAtributoEquipo403_invoker.call(
          req => MnuQr_17.qrEditarAtributoEquipo(req, idQrAtributoEquipo))
      }
  
    // @LINE:625
    case controllers_MnuQr_qrEditarAtributoEquipo2404_route(params@_) =>
      call { 
        controllers_MnuQr_qrEditarAtributoEquipo2404_invoker.call(
          req => MnuQr_17.qrEditarAtributoEquipo2(req))
      }
  
    // @LINE:626
    case controllers_MnuQr_qrDeleteAtributoEquipo405_route(params@_) =>
      call(params.fromPath[Long]("idQrAtributoEquipo", None)) { (idQrAtributoEquipo) =>
        controllers_MnuQr_qrDeleteAtributoEquipo405_invoker.call(
          req => MnuQr_17.qrDeleteAtributoEquipo(req, idQrAtributoEquipo))
      }
  
    // @LINE:627
    case controllers_MnuQr_qrAgregaAtributoEquipo406_route(params@_) =>
      call { 
        controllers_MnuQr_qrAgregaAtributoEquipo406_invoker.call(
          req => MnuQr_17.qrAgregaAtributoEquipo(req))
      }
  
    // @LINE:628
    case controllers_MnuQr_qrAgregaAtributoEquipo2407_route(params@_) =>
      call { 
        controllers_MnuQr_qrAgregaAtributoEquipo2407_invoker.call(
          req => MnuQr_17.qrAgregaAtributoEquipo2(req))
      }
  
    // @LINE:630
    case controllers_MnuQr_qrPrintQrEquipos408_route(params@_) =>
      call { 
        controllers_MnuQr_qrPrintQrEquipos408_invoker.call(
          req => MnuQr_17.qrPrintQrEquipos(req))
      }
  
    // @LINE:631
    case controllers_MnuQr_qrPrintQrEquiposWord409_route(params@_) =>
      call { 
        controllers_MnuQr_qrPrintQrEquiposWord409_invoker.call(
          req => MnuQr_17.qrPrintQrEquiposWord(req))
      }
  
    // @LINE:635
    case controllers_MnuQr_leeUnQr410_route(params@_) =>
      call(params.fromPath[String]("strEncoded", None)) { (strEncoded) =>
        controllers_MnuQr_leeUnQr410_invoker.call(
          req => MnuQr_17.leeUnQr(req, strEncoded))
      }
  
    // @LINE:636
    case controllers_MnuQr_viewImgQr411_route(params@_) =>
      call(params.fromPath[String]("base", None), params.fromPath[String]("img", None)) { (base, img) =>
        controllers_MnuQr_viewImgQr411_invoker.call(
          req => MnuQr_17.viewImgQr(req, base, img))
      }
  
    // @LINE:637
    case controllers_MnuQr_qrImagen412_route(params@_) =>
      call { 
        controllers_MnuQr_qrImagen412_invoker.call(
          req => MnuQr_17.qrImagen(req))
      }
  
    // @LINE:638
    case controllers_MnuQr_qrAtributo413_route(params@_) =>
      call { 
        controllers_MnuQr_qrAtributo413_invoker.call(
          req => MnuQr_17.qrAtributo(req))
      }
  
    // @LINE:639
    case controllers_MnuQr_qrUbicacion414_route(params@_) =>
      call { 
        controllers_MnuQr_qrUbicacion414_invoker.call(
          req => MnuQr_17.qrUbicacion(req))
      }
  
    // @LINE:640
    case controllers_MnuQr_qrTrazabilidad415_route(params@_) =>
      call { 
        controllers_MnuQr_qrTrazabilidad415_invoker.call(
          req => MnuQr_17.qrTrazabilidad(req))
      }
  
    // @LINE:641
    case controllers_MnuQr_qrHistoria416_route(params@_) =>
      call { 
        controllers_MnuQr_qrHistoria416_invoker.call(
          req => MnuQr_17.qrHistoria(req))
      }
  
    // @LINE:642
    case controllers_MnuQr_qrAtributosQr417_route(params@_) =>
      call { 
        controllers_MnuQr_qrAtributosQr417_invoker.call(
          req => MnuQr_17.qrAtributosQr(req))
      }
  
    // @LINE:643
    case controllers_MnuQr_qrImagenQr418_route(params@_) =>
      call { 
        controllers_MnuQr_qrImagenQr418_invoker.call(
          req => MnuQr_17.qrImagenQr(req))
      }
  
    // @LINE:649
    case controllers_MnuPpto_pptoAdministrar419_route(params@_) =>
      call { 
        controllers_MnuPpto_pptoAdministrar419_invoker.call(
          req => MnuPpto_13.pptoAdministrar(req))
      }
  
    // @LINE:650
    case controllers_MnuPpto_pptoEditar420_route(params@_) =>
      call(params.fromPath[Long]("id_bodega", None)) { (id_bodega) =>
        controllers_MnuPpto_pptoEditar420_invoker.call(
          req => MnuPpto_13.pptoEditar(req, id_bodega))
      }
  
    // @LINE:651
    case controllers_MnuPpto_pptoAgregar421_route(params@_) =>
      call { 
        controllers_MnuPpto_pptoAgregar421_invoker.call(
          req => MnuPpto_13.pptoAgregar(req))
      }
  
    // @LINE:652
    case controllers_MnuPpto_pptoGrabar422_route(params@_) =>
      call { 
        controllers_MnuPpto_pptoGrabar422_invoker.call(
          req => MnuPpto_13.pptoGrabar(req))
      }
  
    // @LINE:653
    case controllers_MnuPpto_pptoModificar423_route(params@_) =>
      call { 
        controllers_MnuPpto_pptoModificar423_invoker.call(
          req => MnuPpto_13.pptoModificar(req))
      }
  
    // @LINE:654
    case controllers_MnuPpto_pptoUpdate424_route(params@_) =>
      call { 
        controllers_MnuPpto_pptoUpdate424_invoker.call(
          req => MnuPpto_13.pptoUpdate(req))
      }
  
    // @LINE:655
    case controllers_MnuPpto_pptoEliminar425_route(params@_) =>
      call { 
        controllers_MnuPpto_pptoEliminar425_invoker.call(
          req => MnuPpto_13.pptoEliminar(req))
      }
  
    // @LINE:656
    case controllers_MnuPpto_pptoListadoPptos426_route(params@_) =>
      call { 
        controllers_MnuPpto_pptoListadoPptos426_invoker.call(
          req => MnuPpto_13.pptoListadoPptos(req))
      }
  
    // @LINE:657
    case controllers_MnuPpto_pptoVsRealxBodega427_route(params@_) =>
      call(params.fromPath[Long]("id_bodega", None)) { (id_bodega) =>
        controllers_MnuPpto_pptoVsRealxBodega427_invoker.call(
          req => MnuPpto_13.pptoVsRealxBodega(req, id_bodega))
      }
  
    // @LINE:664
    case controllers_MnuCotizar_cotizaIngreso428_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaIngreso428_invoker.call(
          req => MnuCotizar_4.cotizaIngreso(req))
      }
  
    // @LINE:665
    case controllers_MnuCotizar_cotizaIngreso2429_route(params@_) =>
      call(params.fromPath[Long]("id_bodega", None)) { (id_bodega) =>
        controllers_MnuCotizar_cotizaIngreso2429_invoker.call(
          req => MnuCotizar_4.cotizaIngreso2(req, id_bodega))
      }
  
    // @LINE:666
    case controllers_MnuCotizar_tablaInvPorEquipoAjax430_route(params@_) =>
      call { 
        controllers_MnuCotizar_tablaInvPorEquipoAjax430_invoker.call(
          req => MnuCotizar_4.tablaInvPorEquipoAjax(req))
      }
  
    // @LINE:667
    case controllers_MnuCotizar_cotizarNuevo431_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizarNuevo431_invoker.call(
          req => MnuCotizar_4.cotizarNuevo(req))
      }
  
    // @LINE:668
    case controllers_MnuCotizar_existeNumeroCotizacionAjax432_route(params@_) =>
      call { 
        controllers_MnuCotizar_existeNumeroCotizacionAjax432_invoker.call(
          req => MnuCotizar_4.existeNumeroCotizacionAjax(req))
      }
  
    // @LINE:669
    case controllers_MnuCotizar_cotizaModificaJson433_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaModificaJson433_invoker.call(
          req => MnuCotizar_4.cotizaModificaJson(req))
      }
  
    // @LINE:671
    case controllers_MnuCotizar_actualizaComercialesAjax434_route(params@_) =>
      call { 
        controllers_MnuCotizar_actualizaComercialesAjax434_invoker.call(
          req => MnuCotizar_4.actualizaComercialesAjax(req))
      }
  
    // @LINE:672
    case controllers_MnuCotizar_actualizaComerciales2Ajax435_route(params@_) =>
      call { 
        controllers_MnuCotizar_actualizaComerciales2Ajax435_invoker.call(
          req => MnuCotizar_4.actualizaComerciales2Ajax(req))
      }
  
    // @LINE:675
    case controllers_MnuCotizar_actualizaListaCotiAjax436_route(params@_) =>
      call { 
        controllers_MnuCotizar_actualizaListaCotiAjax436_invoker.call(
          req => MnuCotizar_4.actualizaListaCotiAjax(req))
      }
  
    // @LINE:676
    case controllers_MnuCotizar_actualizaPreciosAjax437_route(params@_) =>
      call { 
        controllers_MnuCotizar_actualizaPreciosAjax437_invoker.call(
          req => MnuCotizar_4.actualizaPreciosAjax(req))
      }
  
    // @LINE:679
    case controllers_MnuCotizar_cotizaListaModificaPeriodo438_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaModificaPeriodo438_invoker.call(
          req => MnuCotizar_4.cotizaListaModificaPeriodo(req))
      }
  
    // @LINE:680
    case controllers_MnuCotizar_cotizaListaModifica439_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaModifica439_invoker.call(
          req => MnuCotizar_4.cotizaListaModifica(req))
      }
  
    // @LINE:681
    case controllers_MnuCotizar_cotizaElimina440_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaElimina440_invoker.call(
          req => MnuCotizar_4.cotizaElimina(req))
      }
  
    // @LINE:682
    case controllers_MnuCotizar_cotizaModifica441_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaModifica441_invoker.call(
          req => MnuCotizar_4.cotizaModifica(req))
      }
  
    // @LINE:683
    case controllers_MnuCotizar_cotizarUpdate442_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizarUpdate442_invoker.call(
          req => MnuCotizar_4.cotizarUpdate(req))
      }
  
    // @LINE:686
    case controllers_MnuCotizar_cotizaListaCambiaEstadoPeriodo443_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaCambiaEstadoPeriodo443_invoker.call(
          req => MnuCotizar_4.cotizaListaCambiaEstadoPeriodo(req))
      }
  
    // @LINE:687
    case controllers_MnuCotizar_cotizaListaCambiaEstado444_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaCambiaEstado444_invoker.call(
          req => MnuCotizar_4.cotizaListaCambiaEstado(req))
      }
  
    // @LINE:688
    case controllers_MnuCotizar_cambiarCotizaEstadoAjax445_route(params@_) =>
      call { 
        controllers_MnuCotizar_cambiarCotizaEstadoAjax445_invoker.call(
          req => MnuCotizar_4.cambiarCotizaEstadoAjax(req))
      }
  
    // @LINE:689
    case controllers_MnuCotizar_cambiarCotizaFechaProbableAjax446_route(params@_) =>
      call { 
        controllers_MnuCotizar_cambiarCotizaFechaProbableAjax446_invoker.call(
          req => MnuCotizar_4.cambiarCotizaFechaProbableAjax(req))
      }
  
    // @LINE:690
    case controllers_MnuCotizar_cambiarCotizaDibujanteAjax447_route(params@_) =>
      call { 
        controllers_MnuCotizar_cambiarCotizaDibujanteAjax447_invoker.call(
          req => MnuCotizar_4.cambiarCotizaDibujanteAjax(req))
      }
  
    // @LINE:691
    case controllers_MnuCotizar_cambiarCotizaNotaAjax448_route(params@_) =>
      call { 
        controllers_MnuCotizar_cambiarCotizaNotaAjax448_invoker.call(
          req => MnuCotizar_4.cambiarCotizaNotaAjax(req))
      }
  
    // @LINE:693
    case controllers_MnuCotizar_cotizacionFindIdForNumAjax449_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizacionFindIdForNumAjax449_invoker.call(
          req => MnuCotizar_4.cotizacionFindIdForNumAjax(req))
      }
  
    // @LINE:695
    case controllers_MnuCotizar_cotizaEstadoMantencion450_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaEstadoMantencion450_invoker.call(
          req => MnuCotizar_4.cotizaEstadoMantencion(req))
      }
  
    // @LINE:696
    case controllers_MnuCotizar_cotizaEstadoElimina451_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaEstadoElimina451_invoker.call(
          req => MnuCotizar_4.cotizaEstadoElimina(req))
      }
  
    // @LINE:697
    case controllers_MnuCotizar_cotizaEstadoModifica452_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaEstadoModifica452_invoker.call(
          req => MnuCotizar_4.cotizaEstadoModifica(req))
      }
  
    // @LINE:698
    case controllers_MnuCotizar_modificaCotizaEstadoPorCampoAjax453_route(params@_) =>
      call { 
        controllers_MnuCotizar_modificaCotizaEstadoPorCampoAjax453_invoker.call(
          req => MnuCotizar_4.modificaCotizaEstadoPorCampoAjax(req))
      }
  
    // @LINE:699
    case controllers_MnuCotizar_cotizaEstadoAgrega454_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaEstadoAgrega454_invoker.call(
          req => MnuCotizar_4.cotizaEstadoAgrega(req))
      }
  
    // @LINE:700
    case controllers_MnuCotizar_cotizaEstadoNuevo455_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaEstadoNuevo455_invoker.call(
          req => MnuCotizar_4.cotizaEstadoNuevo(req))
      }
  
    // @LINE:704
    case controllers_MnuCotizar_cotizaListaImprimirPeriodo456_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaImprimirPeriodo456_invoker.call(
          req => MnuCotizar_4.cotizaListaImprimirPeriodo(req))
      }
  
    // @LINE:705
    case controllers_MnuCotizar_cotizaListaImprimir457_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaImprimir457_invoker.call(
          req => MnuCotizar_4.cotizaListaImprimir(req))
      }
  
    // @LINE:706
    case controllers_MnuCotizar_cotizaImprimir458_route(params@_) =>
      call(params.fromPath[Long]("id_cotizacion", None)) { (id_cotizacion) =>
        controllers_MnuCotizar_cotizaImprimir458_invoker.call(
          req => MnuCotizar_4.cotizaImprimir(req, id_cotizacion))
      }
  
    // @LINE:707
    case controllers_MnuCotizar_cotizaExcel459_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaExcel459_invoker.call(
          req => MnuCotizar_4.cotizaExcel(req))
      }
  
    // @LINE:708
    case controllers_MnuCotizar_pdfCotizaArriendo460_route(params@_) =>
      call { 
        controllers_MnuCotizar_pdfCotizaArriendo460_invoker.call(
          req => MnuCotizar_4.pdfCotizaArriendo(req))
      }
  
    // @LINE:709
    case controllers_MnuCotizar_pdfCotizaVenta461_route(params@_) =>
      call { 
        controllers_MnuCotizar_pdfCotizaVenta461_invoker.call(
          req => MnuCotizar_4.pdfCotizaVenta(req))
      }
  
    // @LINE:710
    case controllers_MnuCotizar_pdfCotizaArrVta462_route(params@_) =>
      call { 
        controllers_MnuCotizar_pdfCotizaArrVta462_invoker.call(
          req => MnuCotizar_4.pdfCotizaArrVta(req))
      }
  
    // @LINE:712
    case controllers_MnuCotizar_reportCotizaSel463_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportCotizaSel463_invoker.call(
          req => MnuCotizar_4.reportCotizaSel(req))
      }
  
    // @LINE:713
    case controllers_MnuCotizar_reportCotizaRpt464_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportCotizaRpt464_invoker.call(
          req => MnuCotizar_4.reportCotizaRpt(req))
      }
  
    // @LINE:716
    case controllers_MnuCotizar_cotizaListaConfirma465_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaConfirma465_invoker.call(
          req => MnuCotizar_4.cotizaListaConfirma(req))
      }
  
    // @LINE:717
    case controllers_MnuCotizar_cotizaConfirma466_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaConfirma466_invoker.call(
          req => MnuCotizar_4.cotizaConfirma(req))
      }
  
    // @LINE:719
    case controllers_MnuCotizar_otListaCotizaSinOt467_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaCotizaSinOt467_invoker.call(
          req => MnuCotizar_4.otListaCotizaSinOt(req))
      }
  
    // @LINE:720
    case controllers_MnuCotizar_otHacer468_route(params@_) =>
      call { 
        controllers_MnuCotizar_otHacer468_invoker.call(
          req => MnuCotizar_4.otHacer(req))
      }
  
    // @LINE:721
    case controllers_MnuCotizar_otGrabar469_route(params@_) =>
      call { 
        controllers_MnuCotizar_otGrabar469_invoker.call(
          req => MnuCotizar_4.otGrabar(req))
      }
  
    // @LINE:723
    case controllers_MnuCotizar_otListaEliminar470_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaEliminar470_invoker.call(
          req => MnuCotizar_4.otListaEliminar(req))
      }
  
    // @LINE:724
    case controllers_MnuCotizar_verOtAjax471_route(params@_) =>
      call { 
        controllers_MnuCotizar_verOtAjax471_invoker.call(
          req => MnuCotizar_4.verOtAjax(req))
      }
  
    // @LINE:725
    case controllers_MnuCotizar_otElimina472_route(params@_) =>
      call { 
        controllers_MnuCotizar_otElimina472_invoker.call(
          req => MnuCotizar_4.otElimina(req))
      }
  
    // @LINE:726
    case controllers_MnuCotizar_actualFechaEnvioOtAjax473_route(params@_) =>
      call { 
        controllers_MnuCotizar_actualFechaEnvioOtAjax473_invoker.call(
          req => MnuCotizar_4.actualFechaEnvioOtAjax(req))
      }
  
    // @LINE:729
    case controllers_MnuCotizar_otListaCambiarEstadoPeriodo474_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaCambiarEstadoPeriodo474_invoker.call(
          req => MnuCotizar_4.otListaCambiarEstadoPeriodo(req))
      }
  
    // @LINE:730
    case controllers_MnuCotizar_otListaCambiarEstado475_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaCambiarEstado475_invoker.call(
          req => MnuCotizar_4.otListaCambiarEstado(req))
      }
  
    // @LINE:731
    case controllers_MnuCotizar_cambiarOtEstadoAjax476_route(params@_) =>
      call { 
        controllers_MnuCotizar_cambiarOtEstadoAjax476_invoker.call(
          req => MnuCotizar_4.cambiarOtEstadoAjax(req))
      }
  
    // @LINE:732
    case controllers_MnuCotizar_cambiarOtNotaAjax477_route(params@_) =>
      call { 
        controllers_MnuCotizar_cambiarOtNotaAjax477_invoker.call(
          req => MnuCotizar_4.cambiarOtNotaAjax(req))
      }
  
    // @LINE:734
    case controllers_MnuCotizar_otEstadoMantencion478_route(params@_) =>
      call { 
        controllers_MnuCotizar_otEstadoMantencion478_invoker.call(
          req => MnuCotizar_4.otEstadoMantencion(req))
      }
  
    // @LINE:735
    case controllers_MnuCotizar_otEstadoElimina479_route(params@_) =>
      call { 
        controllers_MnuCotizar_otEstadoElimina479_invoker.call(
          req => MnuCotizar_4.otEstadoElimina(req))
      }
  
    // @LINE:736
    case controllers_MnuCotizar_otEstadoModifica480_route(params@_) =>
      call { 
        controllers_MnuCotizar_otEstadoModifica480_invoker.call(
          req => MnuCotizar_4.otEstadoModifica(req))
      }
  
    // @LINE:737
    case controllers_MnuCotizar_modificaOtEstadoPorCampoAjax481_route(params@_) =>
      call { 
        controllers_MnuCotizar_modificaOtEstadoPorCampoAjax481_invoker.call(
          req => MnuCotizar_4.modificaOtEstadoPorCampoAjax(req))
      }
  
    // @LINE:738
    case controllers_MnuCotizar_otEstadoAgrega482_route(params@_) =>
      call { 
        controllers_MnuCotizar_otEstadoAgrega482_invoker.call(
          req => MnuCotizar_4.otEstadoAgrega(req))
      }
  
    // @LINE:739
    case controllers_MnuCotizar_otEstadoNuevo483_route(params@_) =>
      call { 
        controllers_MnuCotizar_otEstadoNuevo483_invoker.call(
          req => MnuCotizar_4.otEstadoNuevo(req))
      }
  
    // @LINE:742
    case controllers_MnuCotizar_otListaConfirma484_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaConfirma484_invoker.call(
          req => MnuCotizar_4.otListaConfirma(req))
      }
  
    // @LINE:743
    case controllers_MnuCotizar_otConfirma485_route(params@_) =>
      call { 
        controllers_MnuCotizar_otConfirma485_invoker.call(
          req => MnuCotizar_4.otConfirma(req))
      }
  
    // @LINE:745
    case controllers_MnuCotizar_otListaDespacharPeriodo486_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaDespacharPeriodo486_invoker.call(
          req => MnuCotizar_4.otListaDespacharPeriodo(req))
      }
  
    // @LINE:746
    case controllers_MnuCotizar_otListaDespachar487_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaDespachar487_invoker.call(
          req => MnuCotizar_4.otListaDespachar(req))
      }
  
    // @LINE:747
    case controllers_MnuCotizar_otDespachar488_route(params@_) =>
      call { 
        controllers_MnuCotizar_otDespachar488_invoker.call(
          req => MnuCotizar_4.otDespachar(req))
      }
  
    // @LINE:748
    case controllers_MnuCotizar_tblListEquipoConStockAjax489_route(params@_) =>
      call { 
        controllers_MnuCotizar_tblListEquipoConStockAjax489_invoker.call(
          req => MnuCotizar_4.tblListEquipoConStockAjax(req))
      }
  
    // @LINE:749
    case controllers_MnuCotizar_grabaDespacho490_route(params@_) =>
      call { 
        controllers_MnuCotizar_grabaDespacho490_invoker.call(
          req => MnuCotizar_4.grabaDespacho(req))
      }
  
    // @LINE:751
    case controllers_MnuCotizar_otListaDespachoModificarPeriodo491_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaDespachoModificarPeriodo491_invoker.call(
          req => MnuCotizar_4.otListaDespachoModificarPeriodo(req))
      }
  
    // @LINE:752
    case controllers_MnuCotizar_otListaDespachoModificar492_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaDespachoModificar492_invoker.call(
          req => MnuCotizar_4.otListaDespachoModificar(req))
      }
  
    // @LINE:753
    case controllers_MnuCotizar_otDespachoElimina493_route(params@_) =>
      call { 
        controllers_MnuCotizar_otDespachoElimina493_invoker.call(
          req => MnuCotizar_4.otDespachoElimina(req))
      }
  
    // @LINE:754
    case controllers_MnuCotizar_otDespachoModificar494_route(params@_) =>
      call { 
        controllers_MnuCotizar_otDespachoModificar494_invoker.call(
          req => MnuCotizar_4.otDespachoModificar(req))
      }
  
    // @LINE:755
    case controllers_MnuCotizar_modificaDespacho495_route(params@_) =>
      call { 
        controllers_MnuCotizar_modificaDespacho495_invoker.call(
          req => MnuCotizar_4.modificaDespacho(req))
      }
  
    // @LINE:758
    case controllers_MnuCotizar_cotiPlantilla496_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotiPlantilla496_invoker.call(
          req => MnuCotizar_4.cotiPlantilla(req))
      }
  
    // @LINE:759
    case controllers_MnuCotizar_cotiValidarPlantilla497_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotiValidarPlantilla497_invoker.call(
          req => MnuCotizar_4.cotiValidarPlantilla(req))
      }
  
    // @LINE:760
    case controllers_MnuCotizar_cotiCargaPlantilla498_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotiCargaPlantilla498_invoker.call(
          req => MnuCotizar_4.cotiCargaPlantilla(req))
      }
  
    // @LINE:768
    case controllers_MnuCompras_compraImportIconstruye0499_route(params@_) =>
      call { 
        controllers_MnuCompras_compraImportIconstruye0499_invoker.call(
          req => MnuCompras_6.compraImportIconstruye0(req))
      }
  
    // @LINE:769
    case controllers_MnuCompras_compraImportIconstruye1500_route(params@_) =>
      call { 
        controllers_MnuCompras_compraImportIconstruye1500_invoker.call(
          req => MnuCompras_6.compraImportIconstruye1(req))
      }
  
    // @LINE:770
    case controllers_MnuCompras_compraImportIconstruye2501_route(params@_) =>
      call { 
        controllers_MnuCompras_compraImportIconstruye2501_invoker.call(
          req => MnuCompras_6.compraImportIconstruye2(req))
      }
  
    // @LINE:773
    case controllers_MnuCompras_compraIngreso502_route(params@_) =>
      call { 
        controllers_MnuCompras_compraIngreso502_invoker.call(
          req => MnuCompras_6.compraIngreso(req))
      }
  
    // @LINE:774
    case controllers_MnuCompras_verificaNumeroFacturaAjax503_route(params@_) =>
      call { 
        controllers_MnuCompras_verificaNumeroFacturaAjax503_invoker.call(
          req => MnuCompras_6.verificaNumeroFacturaAjax(req))
      }
  
    // @LINE:775
    case controllers_MnuCompras_compraNuevo504_route(params@_) =>
      call { 
        controllers_MnuCompras_compraNuevo504_invoker.call(
          req => MnuCompras_6.compraNuevo(req))
      }
  
    // @LINE:776
    case controllers_MnuCompras_modalPreciosAjax505_route(params@_) =>
      call { 
        controllers_MnuCompras_modalPreciosAjax505_invoker.call(
          req => MnuCompras_6.modalPreciosAjax(req))
      }
  
    // @LINE:777
    case controllers_MnuCompras_nuevoEquipoAjax506_route(params@_) =>
      call { 
        controllers_MnuCompras_nuevoEquipoAjax506_invoker.call(
          req => MnuCompras_6.nuevoEquipoAjax(req))
      }
  
    // @LINE:778
    case controllers_MnuCompras_compraConfirma507_route(params@_) =>
      call { 
        controllers_MnuCompras_compraConfirma507_invoker.call(
          req => MnuCompras_6.compraConfirma(req))
      }
  
    // @LINE:779
    case controllers_MnuCompras_compraConfirmaIngreso508_route(params@_) =>
      call { 
        controllers_MnuCompras_compraConfirmaIngreso508_invoker.call(
          req => MnuCompras_6.compraConfirmaIngreso(req))
      }
  
    // @LINE:780
    case controllers_MnuCompras_compraListaModifica509_route(params@_) =>
      call { 
        controllers_MnuCompras_compraListaModifica509_invoker.call(
          req => MnuCompras_6.compraListaModifica(req))
      }
  
    // @LINE:781
    case controllers_MnuCompras_compraModifica510_route(params@_) =>
      call(params.fromPath[Long]("id_factura", None)) { (id_factura) =>
        controllers_MnuCompras_compraModifica510_invoker.call(
          req => MnuCompras_6.compraModifica(id_factura, req))
      }
  
    // @LINE:782
    case controllers_MnuCompras_compraModificaGraba511_route(params@_) =>
      call { 
        controllers_MnuCompras_compraModificaGraba511_invoker.call(
          req => MnuCompras_6.compraModificaGraba(req))
      }
  
    // @LINE:783
    case controllers_MnuCompras_modificaFacturaPorCampoAjax512_route(params@_) =>
      call { 
        controllers_MnuCompras_modificaFacturaPorCampoAjax512_invoker.call(
          req => MnuCompras_6.modificaFacturaPorCampoAjax(req))
      }
  
    // @LINE:784
    case controllers_MnuCompras_modificaCompraPorCampoAjax513_route(params@_) =>
      call { 
        controllers_MnuCompras_modificaCompraPorCampoAjax513_invoker.call(
          req => MnuCompras_6.modificaCompraPorCampoAjax(req))
      }
  
    // @LINE:785
    case controllers_MnuCompras_eliminaCompraAjax514_route(params@_) =>
      call { 
        controllers_MnuCompras_eliminaCompraAjax514_invoker.call(
          req => MnuCompras_6.eliminaCompraAjax(req))
      }
  
    // @LINE:786
    case controllers_MnuCompras_listComprasPorCompra515_route(params@_) =>
      call { 
        controllers_MnuCompras_listComprasPorCompra515_invoker.call(
          req => MnuCompras_6.listComprasPorCompra(req))
      }
  
    // @LINE:787
    case controllers_MnuCompras_listComprasPorCompraExcel516_route(params@_) =>
      call { 
        controllers_MnuCompras_listComprasPorCompraExcel516_invoker.call(
          req => MnuCompras_6.listComprasPorCompraExcel(req))
      }
  
    // @LINE:788
    case controllers_MnuCompras_compraFacturaPrint517_route(params@_) =>
      call(params.fromPath[Long]("id_factura", None)) { (id_factura) =>
        controllers_MnuCompras_compraFacturaPrint517_invoker.call(
          req => MnuCompras_6.compraFacturaPrint(id_factura, req))
      }
  
    // @LINE:789
    case controllers_MnuCompras_compraFacturaPrintExcel518_route(params@_) =>
      call { 
        controllers_MnuCompras_compraFacturaPrintExcel518_invoker.call(
          req => MnuCompras_6.compraFacturaPrintExcel(req))
      }
  
    // @LINE:790
    case controllers_MnuCompras_listComprasPorEquipo519_route(params@_) =>
      call { 
        controllers_MnuCompras_listComprasPorEquipo519_invoker.call(
          req => MnuCompras_6.listComprasPorEquipo(req))
      }
  
    // @LINE:791
    case controllers_MnuCompras_listComprasPorEquipoExcel520_route(params@_) =>
      call { 
        controllers_MnuCompras_listComprasPorEquipoExcel520_invoker.call(
          req => MnuCompras_6.listComprasPorEquipoExcel(req))
      }
  
    // @LINE:793
    case controllers_MnuCompras_compraEquipoPrint521_route(params@_) =>
      call(params.fromPath[Long]("id_equipo", None)) { (id_equipo) =>
        controllers_MnuCompras_compraEquipoPrint521_invoker.call(
          req => MnuCompras_6.compraEquipoPrint(id_equipo, req))
      }
  
    // @LINE:794
    case controllers_MnuCompras_compraElimina522_route(params@_) =>
      call { 
        controllers_MnuCompras_compraElimina522_invoker.call(
          req => MnuCompras_6.compraElimina(req))
      }
  
    // @LINE:795
    case controllers_MnuCompras_eliminaCompra523_route(params@_) =>
      call { 
        controllers_MnuCompras_eliminaCompra523_invoker.call(
          req => MnuCompras_6.eliminaCompra(req))
      }
  
    // @LINE:801
    case controllers_MnuMovimientos_movimientoSelectBodegaOrigen524_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoSelectBodegaOrigen524_invoker.call(
          req => MnuMovimientos_9.movimientoSelectBodegaOrigen(req))
      }
  
    // @LINE:802
    case controllers_MnuMovimientos_movimientoOrigenDestinoMultiple525_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoOrigenDestinoMultiple525_invoker.call(
          req => MnuMovimientos_9.movimientoOrigenDestinoMultiple(req))
      }
  
    // @LINE:803
    case controllers_MnuMovimientos_movimientoOrigenDestinoMultiple1526_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoOrigenDestinoMultiple1526_invoker.call(
          req => MnuMovimientos_9.movimientoOrigenDestinoMultiple1(req))
      }
  
    // @LINE:804
    case controllers_MnuMovimientos_verificaNumeroGuiaAjax527_route(params@_) =>
      call { 
        controllers_MnuMovimientos_verificaNumeroGuiaAjax527_invoker.call(
          req => MnuMovimientos_9.verificaNumeroGuiaAjax(req))
      }
  
    // @LINE:805
    case controllers_MnuMovimientos_nameComercialAjax528_route(params@_) =>
      call { 
        controllers_MnuMovimientos_nameComercialAjax528_invoker.call(
          req => MnuMovimientos_9.nameComercialAjax(req))
      }
  
    // @LINE:806
    case controllers_MnuMovimientos_nickNameClienteAjax529_route(params@_) =>
      call { 
        controllers_MnuMovimientos_nickNameClienteAjax529_invoker.call(
          req => MnuMovimientos_9.nickNameClienteAjax(req))
      }
  
    // @LINE:807
    case controllers_MnuMovimientos_movimientoAplicar530_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoAplicar530_invoker.call(
          req => MnuMovimientos_9.movimientoAplicar(req))
      }
  
    // @LINE:809
    case controllers_MnuMovimientos_movimientoSelectModificarPeriodo531_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoSelectModificarPeriodo531_invoker.call(
          req => MnuMovimientos_9.movimientoSelectModificarPeriodo(req))
      }
  
    // @LINE:810
    case controllers_MnuMovimientos_movimientoSelectModificar532_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoSelectModificar532_invoker.call(
          req => MnuMovimientos_9.movimientoSelectModificar(req))
      }
  
    // @LINE:813
    case controllers_MnuMovimientos_verCotizacionAjax533_route(params@_) =>
      call { 
        controllers_MnuMovimientos_verCotizacionAjax533_invoker.call(
          req => MnuMovimientos_9.verCotizacionAjax(req))
      }
  
    // @LINE:814
    case controllers_MnuMovimientos_movimientoOrigenDestinoModifica534_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoOrigenDestinoModifica534_invoker.call(
          req => MnuMovimientos_9.movimientoOrigenDestinoModifica(req))
      }
  
    // @LINE:815
    case controllers_MnuMovimientos_movimientoOrigenDestinoElimina535_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoOrigenDestinoElimina535_invoker.call(
          req => MnuMovimientos_9.movimientoOrigenDestinoElimina(req))
      }
  
    // @LINE:816
    case controllers_MnuMovimientos_movimientoAplicarCambios536_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoAplicarCambios536_invoker.call(
          req => MnuMovimientos_9.movimientoAplicarCambios(req))
      }
  
    // @LINE:818
    case controllers_MnuMovimientos_grabaAlbumFotosAjax537_route(params@_) =>
      call { 
        controllers_MnuMovimientos_grabaAlbumFotosAjax537_invoker.call(
          req => MnuMovimientos_9.grabaAlbumFotosAjax(req))
      }
  
    // @LINE:820
    case controllers_MnuMovimientos_movimientoListarPeriodo538_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoListarPeriodo538_invoker.call(
          req => MnuMovimientos_9.movimientoListarPeriodo(req))
      }
  
    // @LINE:821
    case controllers_MnuMovimientos_movimientoListar539_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoListar539_invoker.call(
          req => MnuMovimientos_9.movimientoListar(req))
      }
  
    // @LINE:822
    case controllers_MnuMovimientos_movimientoListarGet540_route(params@_) =>
      call(params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None)) { (desde, hasta) =>
        controllers_MnuMovimientos_movimientoListarGet540_invoker.call(
          req => MnuMovimientos_9.movimientoListarGet(req, desde, hasta))
      }
  
    // @LINE:823
    case controllers_MnuMovimientos_movimientoListarExcel541_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoListarExcel541_invoker.call(
          req => MnuMovimientos_9.movimientoListarExcel(req))
      }
  
    // @LINE:824
    case controllers_MnuMovimientos_showPdfRelBase542_route(params@_) =>
      call(params.fromPath[String]("tipo", None), params.fromPath[String]("nroFiscal", None)) { (tipo, nroFiscal) =>
        controllers_MnuMovimientos_showPdfRelBase542_invoker.call(
          req => MnuMovimientos_9.showPdfRelBase(tipo, nroFiscal, req))
      }
  
    // @LINE:827
    case controllers_MnuMovimientos_movimientoDetalleGuia543_route(params@_) =>
      call(params.fromPath[Long]("id_guia", None)) { (id_guia) =>
        controllers_MnuMovimientos_movimientoDetalleGuia543_invoker.call(
          req => MnuMovimientos_9.movimientoDetalleGuia(id_guia, req))
      }
  
    // @LINE:828
    case controllers_MnuMovimientos_generaGuiaXLS544_route(params@_) =>
      call { 
        controllers_MnuMovimientos_generaGuiaXLS544_invoker.call(
          req => MnuMovimientos_9.generaGuiaXLS(req))
      }
  
    // @LINE:829
    case controllers_MnuMovimientos_generaGuiaExcel545_route(params@_) =>
      call { 
        controllers_MnuMovimientos_generaGuiaExcel545_invoker.call(
          req => MnuMovimientos_9.generaGuiaExcel(req))
      }
  
    // @LINE:830
    case controllers_MnuMovimientos_guiaCambiarNroRef546_route(params@_) =>
      call { 
        controllers_MnuMovimientos_guiaCambiarNroRef546_invoker.call(
          req => MnuMovimientos_9.guiaCambiarNroRef(req))
      }
  
    // @LINE:833
    case controllers_MnuMovimientos_editaTransportistaAjax547_route(params@_) =>
      call { 
        controllers_MnuMovimientos_editaTransportistaAjax547_invoker.call(
          req => MnuMovimientos_9.editaTransportistaAjax(req))
      }
  
    // @LINE:834
    case controllers_MnuMovimientos_nuevoTransportistaAjax548_route(params@_) =>
      call { 
        controllers_MnuMovimientos_nuevoTransportistaAjax548_invoker.call(
          req => MnuMovimientos_9.nuevoTransportistaAjax(req))
      }
  
    // @LINE:835
    case controllers_MnuMovimientos_modificaTransportistaAjax549_route(params@_) =>
      call { 
        controllers_MnuMovimientos_modificaTransportistaAjax549_invoker.call(
          req => MnuMovimientos_9.modificaTransportistaAjax(req))
      }
  
    // @LINE:836
    case controllers_MnuMovimientos_eliminaTransportistaAjax550_route(params@_) =>
      call { 
        controllers_MnuMovimientos_eliminaTransportistaAjax550_invoker.call(
          req => MnuMovimientos_9.eliminaTransportistaAjax(req))
      }
  
    // @LINE:838
    case controllers_MnuMovimientos_generaGuiaXML551_route(params@_) =>
      call { 
        controllers_MnuMovimientos_generaGuiaXML551_invoker.call(
          req => MnuMovimientos_9.generaGuiaXML(req))
      }
  
    // @LINE:839
    case controllers_MnuMovimientos_generaGuiaApiManager552_route(params@_) =>
      call { 
        controllers_MnuMovimientos_generaGuiaApiManager552_invoker.call(
          req => MnuMovimientos_9.generaGuiaApiManager(req))
      }
  
    // @LINE:840
    case controllers_MnuMovimientos_generaGuiaApiNubox553_route(params@_) =>
      call { 
        controllers_MnuMovimientos_generaGuiaApiNubox553_invoker.call(
          req => MnuMovimientos_9.generaGuiaApiNubox(req))
      }
  
    // @LINE:841
    case controllers_MnuMovimientos_generaGuiaWebFacturacion554_route(params@_) =>
      call { 
        controllers_MnuMovimientos_generaGuiaWebFacturacion554_invoker.call(
          req => MnuMovimientos_9.generaGuiaWebFacturacion(req))
      }
  
    // @LINE:842
    case controllers_MnuMovimientos_generaGuiaWebMaximise555_route(params@_) =>
      call { 
        controllers_MnuMovimientos_generaGuiaWebMaximise555_invoker.call(
          req => MnuMovimientos_9.generaGuiaWebMaximise(req))
      }
  
    // @LINE:843
    case controllers_MnuMovimientos_downGuiaMaximiseAjax556_route(params@_) =>
      call(params.fromPath[String]("nroInte", None)) { (nroInte) =>
        controllers_MnuMovimientos_downGuiaMaximiseAjax556_invoker.call(
          req => MnuMovimientos_9.downGuiaMaximiseAjax(nroInte, req))
      }
  
    // @LINE:845
    case controllers_MnuMovimientos_generaGuiaWebIConstruye557_route(params@_) =>
      call { 
        controllers_MnuMovimientos_generaGuiaWebIConstruye557_invoker.call(
          req => MnuMovimientos_9.generaGuiaWebIConstruye(req))
      }
  
    // @LINE:846
    case controllers_MnuMovimientos_downGuiaIconstruye558_route(params@_) =>
      call { 
        controllers_MnuMovimientos_downGuiaIconstruye558_invoker.call(
          req => MnuMovimientos_9.downGuiaIconstruye(req))
      }
  
    // @LINE:848
    case controllers_MnuMovimientos_generaGuiaApiRelBase559_route(params@_) =>
      call { 
        controllers_MnuMovimientos_generaGuiaApiRelBase559_invoker.call(
          req => MnuMovimientos_9.generaGuiaApiRelBase(req))
      }
  
    // @LINE:849
    case controllers_MnuMovimientos_buscaCotiSolucionAjax560_route(params@_) =>
      call { 
        controllers_MnuMovimientos_buscaCotiSolucionAjax560_invoker.call(
          req => MnuMovimientos_9.buscaCotiSolucionAjax(req))
      }
  
    // @LINE:850
    case controllers_MnuMovimientos_generaGuiaSapSchwager561_route(params@_) =>
      call { 
        controllers_MnuMovimientos_generaGuiaSapSchwager561_invoker.call(
          req => MnuMovimientos_9.generaGuiaSapSchwager(req))
      }
  
    // @LINE:852
    case controllers_MnuMovimientos_movimientoListarImgPeriodo562_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoListarImgPeriodo562_invoker.call(
          req => MnuMovimientos_9.movimientoListarImgPeriodo(req))
      }
  
    // @LINE:853
    case controllers_MnuMovimientos_movimientoListarImg563_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoListarImg563_invoker.call(
          req => MnuMovimientos_9.movimientoListarImg(req))
      }
  
    // @LINE:854
    case controllers_MnuMovimientos_movimientoDetalleGuiaConImg564_route(params@_) =>
      call(params.fromPath[Long]("id_guia", None)) { (id_guia) =>
        controllers_MnuMovimientos_movimientoDetalleGuiaConImg564_invoker.call(
          req => MnuMovimientos_9.movimientoDetalleGuiaConImg(id_guia, req))
      }
  
    // @LINE:856
    case controllers_MnuMovimientos_hojaChequeoSelectBodegaAgrupado565_route(params@_) =>
      call { 
        controllers_MnuMovimientos_hojaChequeoSelectBodegaAgrupado565_invoker.call(
          req => MnuMovimientos_9.hojaChequeoSelectBodegaAgrupado(req))
      }
  
    // @LINE:857
    case controllers_MnuMovimientos_hojaChequeoDetalleAgrupado566_route(params@_) =>
      call(params.fromPath[Long]("id_bodega", None)) { (id_bodega) =>
        controllers_MnuMovimientos_hojaChequeoDetalleAgrupado566_invoker.call(
          req => MnuMovimientos_9.hojaChequeoDetalleAgrupado(id_bodega, req))
      }
  
    // @LINE:858
    case controllers_MnuMovimientos_hojaChequeoSelectBodega567_route(params@_) =>
      call { 
        controllers_MnuMovimientos_hojaChequeoSelectBodega567_invoker.call(
          req => MnuMovimientos_9.hojaChequeoSelectBodega(req))
      }
  
    // @LINE:859
    case controllers_MnuMovimientos_hojaChequeoDetalle568_route(params@_) =>
      call(params.fromPath[Long]("id_bodega", None)) { (id_bodega) =>
        controllers_MnuMovimientos_hojaChequeoDetalle568_invoker.call(
          req => MnuMovimientos_9.hojaChequeoDetalle(id_bodega, req))
      }
  
    // @LINE:862
    case controllers_MnuMovimientos_hojaChequeoSelectPorGrupo569_route(params@_) =>
      call { 
        controllers_MnuMovimientos_hojaChequeoSelectPorGrupo569_invoker.call(
          req => MnuMovimientos_9.hojaChequeoSelectPorGrupo(req))
      }
  
    // @LINE:863
    case controllers_MnuMovimientos_hojaChequeoDetallePorGrupo570_route(params@_) =>
      call { 
        controllers_MnuMovimientos_hojaChequeoDetallePorGrupo570_invoker.call(
          req => MnuMovimientos_9.hojaChequeoDetallePorGrupo(req))
      }
  
    // @LINE:865
    case controllers_MnuMovimientos_hojaChequeoAgrupadoExcel571_route(params@_) =>
      call { 
        controllers_MnuMovimientos_hojaChequeoAgrupadoExcel571_invoker.call(
          req => MnuMovimientos_9.hojaChequeoAgrupadoExcel(req))
      }
  
    // @LINE:866
    case controllers_MnuMovimientos_hojaChequeoExcel572_route(params@_) =>
      call { 
        controllers_MnuMovimientos_hojaChequeoExcel572_invoker.call(
          req => MnuMovimientos_9.hojaChequeoExcel(req))
      }
  
    // @LINE:868
    case controllers_MnuMovimientos_hojaChequeoDetallePorGrupoExcel573_route(params@_) =>
      call { 
        controllers_MnuMovimientos_hojaChequeoDetallePorGrupoExcel573_invoker.call(
          req => MnuMovimientos_9.hojaChequeoDetallePorGrupoExcel(req))
      }
  
    // @LINE:871
    case controllers_MnuMovimientos_cierreProyectoSel574_route(params@_) =>
      call { 
        controllers_MnuMovimientos_cierreProyectoSel574_invoker.call(
          req => MnuMovimientos_9.cierreProyectoSel(req))
      }
  
    // @LINE:872
    case controllers_MnuMovimientos_cierreProyectoDet575_route(params@_) =>
      call { 
        controllers_MnuMovimientos_cierreProyectoDet575_invoker.call(
          req => MnuMovimientos_9.cierreProyectoDet(req))
      }
  
    // @LINE:873
    case controllers_MnuMovimientos_cierreAplicar576_route(params@_) =>
      call { 
        controllers_MnuMovimientos_cierreAplicar576_invoker.call(
          req => MnuMovimientos_9.cierreAplicar(req))
      }
  
    // @LINE:879
    case controllers_MnuBajas_bajaIngreso577_route(params@_) =>
      call { 
        controllers_MnuBajas_bajaIngreso577_invoker.call(
          req => MnuBajas_5.bajaIngreso(req))
      }
  
    // @LINE:880
    case controllers_MnuBajas_bajaNuevo578_route(params@_) =>
      call { 
        controllers_MnuBajas_bajaNuevo578_invoker.call(
          req => MnuBajas_5.bajaNuevo(req))
      }
  
    // @LINE:881
    case controllers_MnuBajas_bajaListaModifica579_route(params@_) =>
      call { 
        controllers_MnuBajas_bajaListaModifica579_invoker.call(
          req => MnuBajas_5.bajaListaModifica(req))
      }
  
    // @LINE:882
    case controllers_MnuBajas_bajaModifica580_route(params@_) =>
      call(params.fromPath[Long]("id_actaBaja", None)) { (id_actaBaja) =>
        controllers_MnuBajas_bajaModifica580_invoker.call(
          req => MnuBajas_5.bajaModifica(id_actaBaja, req))
      }
  
    // @LINE:883
    case controllers_MnuBajas_bajaModificaGraba581_route(params@_) =>
      call { 
        controllers_MnuBajas_bajaModificaGraba581_invoker.call(
          req => MnuBajas_5.bajaModificaGraba(req))
      }
  
    // @LINE:884
    case controllers_MnuBajas_confirmaBaja582_route(params@_) =>
      call { 
        controllers_MnuBajas_confirmaBaja582_invoker.call(
          req => MnuBajas_5.confirmaBaja(req))
      }
  
    // @LINE:885
    case controllers_MnuBajas_bajaConfirmaIngreso583_route(params@_) =>
      call { 
        controllers_MnuBajas_bajaConfirmaIngreso583_invoker.call(
          req => MnuBajas_5.bajaConfirmaIngreso(req))
      }
  
    // @LINE:886
    case controllers_MnuBajas_listBajasPorActa584_route(params@_) =>
      call { 
        controllers_MnuBajas_listBajasPorActa584_invoker.call(
          req => MnuBajas_5.listBajasPorActa(req))
      }
  
    // @LINE:887
    case controllers_MnuBajas_bajaActaPrint585_route(params@_) =>
      call(params.fromPath[Long]("id_actaBaja", None)) { (id_actaBaja) =>
        controllers_MnuBajas_bajaActaPrint585_invoker.call(
          req => MnuBajas_5.bajaActaPrint(id_actaBaja, req))
      }
  
    // @LINE:888
    case controllers_MnuBajas_listBajasPorEquipo586_route(params@_) =>
      call { 
        controllers_MnuBajas_listBajasPorEquipo586_invoker.call(
          req => MnuBajas_5.listBajasPorEquipo(req))
      }
  
    // @LINE:889
    case controllers_MnuBajas_listBajasPorEquipoExcel587_route(params@_) =>
      call { 
        controllers_MnuBajas_listBajasPorEquipoExcel587_invoker.call(
          req => MnuBajas_5.listBajasPorEquipoExcel(req))
      }
  
    // @LINE:890
    case controllers_MnuBajas_bajaEquipoPrint588_route(params@_) =>
      call(params.fromPath[Long]("id_equipo", None)) { (id_equipo) =>
        controllers_MnuBajas_bajaEquipoPrint588_invoker.call(
          req => MnuBajas_5.bajaEquipoPrint(id_equipo, req))
      }
  
    // @LINE:896
    case controllers_MnuBodegas_bodegaAdministrar589_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaAdministrar589_invoker.call(
          req => MnuBodegas_19.bodegaAdministrar(req))
      }
  
    // @LINE:897
    case controllers_MnuBodegas_bodegaCrear590_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaCrear590_invoker.call(
          req => MnuBodegas_19.bodegaCrear(req))
      }
  
    // @LINE:898
    case controllers_MnuBodegas_vistaSelectComercialAjax591_route(params@_) =>
      call { 
        controllers_MnuBodegas_vistaSelectComercialAjax591_invoker.call(
          req => MnuBodegas_19.vistaSelectComercialAjax(req))
      }
  
    // @LINE:899
    case controllers_MnuBodegas_verificaNombreBodegaAjax592_route(params@_) =>
      call { 
        controllers_MnuBodegas_verificaNombreBodegaAjax592_invoker.call(
          req => MnuBodegas_19.verificaNombreBodegaAjax(req))
      }
  
    // @LINE:900
    case controllers_MnuBodegas_bodegaGraba593_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaGraba593_invoker.call(
          req => MnuBodegas_19.bodegaGraba(req))
      }
  
    // @LINE:901
    case controllers_MnuBodegas_bodegaModificar594_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaModificar594_invoker.call(
          req => MnuBodegas_19.bodegaModificar(req))
      }
  
    // @LINE:902
    case controllers_MnuBodegas_bodegaModifica595_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaModifica595_invoker.call(
          req => MnuBodegas_19.bodegaModifica(req))
      }
  
    // @LINE:903
    case controllers_MnuBodegas_bodegaElimina596_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaElimina596_invoker.call(
          req => MnuBodegas_19.bodegaElimina(req))
      }
  
    // @LINE:905
    case controllers_MnuBodegas_bodegaAsignaDctos597_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaAsignaDctos597_invoker.call(
          req => MnuBodegas_19.bodegaAsignaDctos(req))
      }
  
    // @LINE:906
    case controllers_MnuBodegas_bodegaFijaDctos598_route(params@_) =>
      call(params.fromPath[Long]("id_bodega", None)) { (id_bodega) =>
        controllers_MnuBodegas_bodegaFijaDctos598_invoker.call(
          req => MnuBodegas_19.bodegaFijaDctos(id_bodega, req))
      }
  
    // @LINE:907
    case controllers_MnuBodegas_modificarDctoArriendoAjax599_route(params@_) =>
      call { 
        controllers_MnuBodegas_modificarDctoArriendoAjax599_invoker.call(
          req => MnuBodegas_19.modificarDctoArriendoAjax(req))
      }
  
    // @LINE:908
    case controllers_MnuBodegas_eliminarDctoArriendoAjax600_route(params@_) =>
      call { 
        controllers_MnuBodegas_eliminarDctoArriendoAjax600_invoker.call(
          req => MnuBodegas_19.eliminarDctoArriendoAjax(req))
      }
  
    // @LINE:909
    case controllers_MnuBodegas_grabaDctoArriendoAjax601_route(params@_) =>
      call { 
        controllers_MnuBodegas_grabaDctoArriendoAjax601_invoker.call(
          req => MnuBodegas_19.grabaDctoArriendoAjax(req))
      }
  
    // @LINE:911
    case controllers_MnuBodegas_bodegaAsignaTasas602_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaAsignaTasas602_invoker.call(
          req => MnuBodegas_19.bodegaAsignaTasas(req))
      }
  
    // @LINE:912
    case controllers_MnuBodegas_bodegaFijaTasas603_route(params@_) =>
      call(params.fromPath[Long]("id_bodega", None)) { (id_bodega) =>
        controllers_MnuBodegas_bodegaFijaTasas603_invoker.call(
          req => MnuBodegas_19.bodegaFijaTasas(id_bodega, req))
      }
  
    // @LINE:913
    case controllers_MnuBodegas_modificarTasaArriendoAjax604_route(params@_) =>
      call { 
        controllers_MnuBodegas_modificarTasaArriendoAjax604_invoker.call(
          req => MnuBodegas_19.modificarTasaArriendoAjax(req))
      }
  
    // @LINE:914
    case controllers_MnuBodegas_eliminarTasaArriendoAjax605_route(params@_) =>
      call { 
        controllers_MnuBodegas_eliminarTasaArriendoAjax605_invoker.call(
          req => MnuBodegas_19.eliminarTasaArriendoAjax(req))
      }
  
    // @LINE:915
    case controllers_MnuBodegas_grabaTasaArriendoAjax606_route(params@_) =>
      call { 
        controllers_MnuBodegas_grabaTasaArriendoAjax606_invoker.call(
          req => MnuBodegas_19.grabaTasaArriendoAjax(req))
      }
  
    // @LINE:917
    case controllers_MnuBodegas_bodegaPrecios607_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaPrecios607_invoker.call(
          req => MnuBodegas_19.bodegaPrecios(req))
      }
  
    // @LINE:918
    case controllers_MnuBodegas_bodegaListaPrecios608_route(params@_) =>
      call(params.fromPath[Long]("id_bodega", None)) { (id_bodega) =>
        controllers_MnuBodegas_bodegaListaPrecios608_invoker.call(
          req => MnuBodegas_19.bodegaListaPrecios(id_bodega, req))
      }
  
    // @LINE:919
    case controllers_MnuBodegas_actualizaListaPrecioAjax609_route(params@_) =>
      call { 
        controllers_MnuBodegas_actualizaListaPrecioAjax609_invoker.call(
          req => MnuBodegas_19.actualizaListaPrecioAjax(req))
      }
  
    // @LINE:921
    case controllers_MnuBodegas_bodegaFactorViga610_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaFactorViga610_invoker.call(
          req => MnuBodegas_19.bodegaFactorViga(req))
      }
  
    // @LINE:922
    case controllers_MnuBodegas_actualizaFactorVigaAjax611_route(params@_) =>
      call { 
        controllers_MnuBodegas_actualizaFactorVigaAjax611_invoker.call(
          req => MnuBodegas_19.actualizaFactorVigaAjax(req))
      }
  
    // @LINE:924
    case controllers_MnuBodegas_bodegaContactos612_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaContactos612_invoker.call(
          req => MnuBodegas_19.bodegaContactos(req))
      }
  
    // @LINE:925
    case controllers_MnuBodegas_bodegaContactosExcel613_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaContactosExcel613_invoker.call(
          req => MnuBodegas_19.bodegaContactosExcel(req))
      }
  
    // @LINE:926
    case controllers_MnuBodegas_bodegaModificaContacto614_route(params@_) =>
      call(params.fromPath[Long]("id_bodega", None)) { (id_bodega) =>
        controllers_MnuBodegas_bodegaModificaContacto614_invoker.call(
          req => MnuBodegas_19.bodegaModificaContacto(id_bodega, req))
      }
  
    // @LINE:927
    case controllers_MnuBodegas_bodegaContactoModifica615_route(params@_) =>
      call(params.fromPath[Long]("id_contacto", None), params.fromPath[Long]("id_bodega", None)) { (id_contacto, id_bodega) =>
        controllers_MnuBodegas_bodegaContactoModifica615_invoker.call(
          req => MnuBodegas_19.bodegaContactoModifica(id_contacto, id_bodega, req))
      }
  
    // @LINE:928
    case controllers_MnuBodegas_bodegaContactoAgrega616_route(params@_) =>
      call(params.fromPath[Long]("id_bodega", None)) { (id_bodega) =>
        controllers_MnuBodegas_bodegaContactoAgrega616_invoker.call(
          req => MnuBodegas_19.bodegaContactoAgrega(id_bodega, req))
      }
  
    // @LINE:929
    case controllers_MnuBodegas_bodegaContactoGraba617_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaContactoGraba617_invoker.call(
          req => MnuBodegas_19.bodegaContactoGraba(req))
      }
  
    // @LINE:930
    case controllers_MnuBodegas_bodegaContactoElimina618_route(params@_) =>
      call(params.fromPath[Long]("id_contacto", None), params.fromPath[Long]("id_bodega", None)) { (id_contacto, id_bodega) =>
        controllers_MnuBodegas_bodegaContactoElimina618_invoker.call(
          req => MnuBodegas_19.bodegaContactoElimina(id_contacto, id_bodega, req))
      }
  
    // @LINE:931
    case controllers_MnuBodegas_modificaContactoBodegaPorCampoAjax619_route(params@_) =>
      call { 
        controllers_MnuBodegas_modificaContactoBodegaPorCampoAjax619_invoker.call(
          req => MnuBodegas_19.modificaContactoBodegaPorCampoAjax(req))
      }
  
    // @LINE:932
    case controllers_MnuBodegas_bodegaContactosAjax620_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaContactosAjax620_invoker.call(
          req => MnuBodegas_19.bodegaContactosAjax(req))
      }
  
    // @LINE:933
    case controllers_MnuBodegas_bodegaFindIdForNomAjax621_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaFindIdForNomAjax621_invoker.call(
          req => MnuBodegas_19.bodegaFindIdForNomAjax(req))
      }
  
    // @LINE:935
    case controllers_MnuBodegas_bodegaVigenteNoVigente622_route(params@_) =>
      call { 
        controllers_MnuBodegas_bodegaVigenteNoVigente622_invoker.call(
          req => MnuBodegas_19.bodegaVigenteNoVigente(req))
      }
  
    // @LINE:936
    case controllers_MnuBodegas_modificarBodegaEstado623_route(params@_) =>
      call(params.fromPath[Long]("id_bodega", None), params.fromPath[Long]("estado", None)) { (id_bodega, estado) =>
        controllers_MnuBodegas_modificarBodegaEstado623_invoker.call(
          req => MnuBodegas_19.modificarBodegaEstado(id_bodega, estado, req))
      }
  
    // @LINE:944
    case controllers_MnuTablas_precioMantencion624_route(params@_) =>
      call { 
        controllers_MnuTablas_precioMantencion624_invoker.call(
          req => MnuTablas_1.precioMantencion(req))
      }
  
    // @LINE:945
    case controllers_MnuTablas_actualizaPrecioAjax625_route(params@_) =>
      call { 
        controllers_MnuTablas_actualizaPrecioAjax625_invoker.call(
          req => MnuTablas_1.actualizaPrecioAjax(req))
      }
  
    // @LINE:946
    case controllers_MnuTablas_grupoMantencion626_route(params@_) =>
      call { 
        controllers_MnuTablas_grupoMantencion626_invoker.call(
          req => MnuTablas_1.grupoMantencion(req))
      }
  
    // @LINE:947
    case controllers_MnuTablas_grupoElimina627_route(params@_) =>
      call { 
        controllers_MnuTablas_grupoElimina627_invoker.call(
          req => MnuTablas_1.grupoElimina(req))
      }
  
    // @LINE:948
    case controllers_MnuTablas_grupoModifica628_route(params@_) =>
      call { 
        controllers_MnuTablas_grupoModifica628_invoker.call(
          req => MnuTablas_1.grupoModifica(req))
      }
  
    // @LINE:949
    case controllers_MnuTablas_grupoAgrega629_route(params@_) =>
      call { 
        controllers_MnuTablas_grupoAgrega629_invoker.call(
          req => MnuTablas_1.grupoAgrega(req))
      }
  
    // @LINE:950
    case controllers_MnuTablas_grupoNuevo630_route(params@_) =>
      call { 
        controllers_MnuTablas_grupoNuevo630_invoker.call(
          req => MnuTablas_1.grupoNuevo(req))
      }
  
    // @LINE:951
    case controllers_MnuTablas_modificaGrupoPorCampoAjax631_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaGrupoPorCampoAjax631_invoker.call(
          req => MnuTablas_1.modificaGrupoPorCampoAjax(req))
      }
  
    // @LINE:952
    case controllers_MnuTablas_grupoNuevoAtributo632_route(params@_) =>
      call(params.fromPath[Long]("id_grupo", None)) { (id_grupo) =>
        controllers_MnuTablas_grupoNuevoAtributo632_invoker.call(
          req => MnuTablas_1.grupoNuevoAtributo(id_grupo, req))
      }
  
    // @LINE:953
    case controllers_MnuTablas_grupoGrabaAtributo633_route(params@_) =>
      call { 
        controllers_MnuTablas_grupoGrabaAtributo633_invoker.call(
          req => MnuTablas_1.grupoGrabaAtributo(req))
      }
  
    // @LINE:954
    case controllers_MnuTablas_grupoEliminaAtributo634_route(params@_) =>
      call { 
        controllers_MnuTablas_grupoEliminaAtributo634_invoker.call(
          req => MnuTablas_1.grupoEliminaAtributo(req))
      }
  
    // @LINE:956
    case controllers_MnuTablas_equipoMantencion635_route(params@_) =>
      call { 
        controllers_MnuTablas_equipoMantencion635_invoker.call(
          req => MnuTablas_1.equipoMantencion(req))
      }
  
    // @LINE:957
    case controllers_MnuTablas_equipoModifica636_route(params@_) =>
      call(params.fromPath[Long]("id_equipo", None)) { (id_equipo) =>
        controllers_MnuTablas_equipoModifica636_invoker.call(
          req => MnuTablas_1.equipoModifica(id_equipo, req))
      }
  
    // @LINE:958
    case controllers_MnuTablas_equipoNuevo637_route(params@_) =>
      call(params.fromPath[Long]("id_grupo", None)) { (id_grupo) =>
        controllers_MnuTablas_equipoNuevo637_invoker.call(
          req => MnuTablas_1.equipoNuevo(id_grupo, req))
      }
  
    // @LINE:959
    case controllers_MnuTablas_cambiarGrupoEquipo638_route(params@_) =>
      call(params.fromPath[Long]("id_grup", None), params.fromPath[Long]("id_equipo", None)) { (id_grup, id_equipo) =>
        controllers_MnuTablas_cambiarGrupoEquipo638_invoker.call(
          req => MnuTablas_1.cambiarGrupoEquipo(id_grup, id_equipo, req))
      }
  
    // @LINE:960
    case controllers_MnuTablas_modificaEquipoPorCampoAjax639_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaEquipoPorCampoAjax639_invoker.call(
          req => MnuTablas_1.modificaEquipoPorCampoAjax(req))
      }
  
    // @LINE:961
    case controllers_MnuTablas_modificaAtributoEquipoAjax640_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaAtributoEquipoAjax640_invoker.call(
          req => MnuTablas_1.modificaAtributoEquipoAjax(req))
      }
  
    // @LINE:962
    case controllers_MnuTablas_grabaImgEquipo641_route(params@_) =>
      call { 
        controllers_MnuTablas_grabaImgEquipo641_invoker.call(
          req => MnuTablas_1.grabaImgEquipo(req))
      }
  
    // @LINE:963
    case controllers_MnuTablas_equipoElimina642_route(params@_) =>
      call { 
        controllers_MnuTablas_equipoElimina642_invoker.call(
          req => MnuTablas_1.equipoElimina(req))
      }
  
    // @LINE:964
    case controllers_MnuTablas_equipoGraba643_route(params@_) =>
      call { 
        controllers_MnuTablas_equipoGraba643_invoker.call(
          req => MnuTablas_1.equipoGraba(req))
      }
  
    // @LINE:965
    case controllers_MnuTablas_verificaCodigoEquipoAjax644_route(params@_) =>
      call { 
        controllers_MnuTablas_verificaCodigoEquipoAjax644_invoker.call(
          req => MnuTablas_1.verificaCodigoEquipoAjax(req))
      }
  
    // @LINE:966
    case controllers_MnuTablas_equipoDescripcionAjax645_route(params@_) =>
      call { 
        controllers_MnuTablas_equipoDescripcionAjax645_invoker.call(
          req => MnuTablas_1.equipoDescripcionAjax(req))
      }
  
    // @LINE:967
    case controllers_MnuTablas_equipoFindIdForCodAjax646_route(params@_) =>
      call { 
        controllers_MnuTablas_equipoFindIdForCodAjax646_invoker.call(
          req => MnuTablas_1.equipoFindIdForCodAjax(req))
      }
  
    // @LINE:970
    case controllers_MnuTablas_proyectoMantencion647_route(params@_) =>
      call { 
        controllers_MnuTablas_proyectoMantencion647_invoker.call(
          req => MnuTablas_1.proyectoMantencion(req))
      }
  
    // @LINE:971
    case controllers_MnuTablas_proyectoModifica648_route(params@_) =>
      call(params.fromPath[Long]("id_proyecto", None)) { (id_proyecto) =>
        controllers_MnuTablas_proyectoModifica648_invoker.call(
          req => MnuTablas_1.proyectoModifica(id_proyecto, req))
      }
  
    // @LINE:972
    case controllers_MnuTablas_selComuna1Ajax649_route(params@_) =>
      call { 
        controllers_MnuTablas_selComuna1Ajax649_invoker.call(
          req => MnuTablas_1.selComuna1Ajax(req))
      }
  
    // @LINE:973
    case controllers_MnuTablas_selComuna2Ajax650_route(params@_) =>
      call { 
        controllers_MnuTablas_selComuna2Ajax650_invoker.call(
          req => MnuTablas_1.selComuna2Ajax(req))
      }
  
    // @LINE:974
    case controllers_MnuTablas_modificaProyectoPorCampoAjax651_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaProyectoPorCampoAjax651_invoker.call(
          req => MnuTablas_1.modificaProyectoPorCampoAjax(req))
      }
  
    // @LINE:975
    case controllers_MnuTablas_proyectoContactoModifica652_route(params@_) =>
      call(params.fromPath[Long]("id_contacto", None), params.fromPath[Long]("id_proyecto", None)) { (id_contacto, id_proyecto) =>
        controllers_MnuTablas_proyectoContactoModifica652_invoker.call(
          req => MnuTablas_1.proyectoContactoModifica(id_contacto, id_proyecto, req))
      }
  
    // @LINE:976
    case controllers_MnuTablas_proyectoContactoAgrega653_route(params@_) =>
      call(params.fromPath[Long]("id_proyecto", None)) { (id_proyecto) =>
        controllers_MnuTablas_proyectoContactoAgrega653_invoker.call(
          req => MnuTablas_1.proyectoContactoAgrega(id_proyecto, req))
      }
  
    // @LINE:977
    case controllers_MnuTablas_proyectoContactoGraba654_route(params@_) =>
      call { 
        controllers_MnuTablas_proyectoContactoGraba654_invoker.call(
          req => MnuTablas_1.proyectoContactoGraba(req))
      }
  
    // @LINE:978
    case controllers_MnuTablas_proyectoContactoElimina655_route(params@_) =>
      call(params.fromPath[Long]("id_contacto", None), params.fromPath[Long]("id_proyecto", None)) { (id_contacto, id_proyecto) =>
        controllers_MnuTablas_proyectoContactoElimina655_invoker.call(
          req => MnuTablas_1.proyectoContactoElimina(id_contacto, id_proyecto, req))
      }
  
    // @LINE:979
    case controllers_MnuTablas_modificaContactoProyectoPorCampoAjax656_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaContactoProyectoPorCampoAjax656_invoker.call(
          req => MnuTablas_1.modificaContactoProyectoPorCampoAjax(req))
      }
  
    // @LINE:980
    case controllers_MnuTablas_proyectoElimina657_route(params@_) =>
      call { 
        controllers_MnuTablas_proyectoElimina657_invoker.call(
          req => MnuTablas_1.proyectoElimina(req))
      }
  
    // @LINE:981
    case controllers_MnuTablas_proyectoAgrega658_route(params@_) =>
      call { 
        controllers_MnuTablas_proyectoAgrega658_invoker.call(
          req => MnuTablas_1.proyectoAgrega(req))
      }
  
    // @LINE:982
    case controllers_MnuTablas_proyectoGraba659_route(params@_) =>
      call { 
        controllers_MnuTablas_proyectoGraba659_invoker.call(
          req => MnuTablas_1.proyectoGraba(req))
      }
  
    // @LINE:983
    case controllers_MnuTablas_proyectoGrabaAjax660_route(params@_) =>
      call { 
        controllers_MnuTablas_proyectoGrabaAjax660_invoker.call(
          req => MnuTablas_1.proyectoGrabaAjax(req))
      }
  
    // @LINE:984
    case controllers_MnuTablas_verificaNickProyectoAjax661_route(params@_) =>
      call { 
        controllers_MnuTablas_verificaNickProyectoAjax661_invoker.call(
          req => MnuTablas_1.verificaNickProyectoAjax(req))
      }
  
    // @LINE:985
    case controllers_MnuTablas_proyectoContactosAjax662_route(params@_) =>
      call { 
        controllers_MnuTablas_proyectoContactosAjax662_invoker.call(
          req => MnuTablas_1.proyectoContactosAjax(req))
      }
  
    // @LINE:986
    case controllers_MnuTablas_proyectoFindIdForNickNameAjax663_route(params@_) =>
      call { 
        controllers_MnuTablas_proyectoFindIdForNickNameAjax663_invoker.call(
          req => MnuTablas_1.proyectoFindIdForNickNameAjax(req))
      }
  
    // @LINE:989
    case controllers_MnuTablas_clienteMantencion664_route(params@_) =>
      call { 
        controllers_MnuTablas_clienteMantencion664_invoker.call(
          req => MnuTablas_1.clienteMantencion(req))
      }
  
    // @LINE:990
    case controllers_MnuTablas_clienteMantencionExcel665_route(params@_) =>
      call { 
        controllers_MnuTablas_clienteMantencionExcel665_invoker.call(
          req => MnuTablas_1.clienteMantencionExcel(req))
      }
  
    // @LINE:991
    case controllers_MnuTablas_clienteModifica666_route(params@_) =>
      call(params.fromPath[Long]("id_cliente", None)) { (id_cliente) =>
        controllers_MnuTablas_clienteModifica666_invoker.call(
          req => MnuTablas_1.clienteModifica(id_cliente, req))
      }
  
    // @LINE:992
    case controllers_MnuTablas_modificaClientePorCampoAjax667_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaClientePorCampoAjax667_invoker.call(
          req => MnuTablas_1.modificaClientePorCampoAjax(req))
      }
  
    // @LINE:993
    case controllers_MnuTablas_selComuna3Ajax668_route(params@_) =>
      call { 
        controllers_MnuTablas_selComuna3Ajax668_invoker.call(
          req => MnuTablas_1.selComuna3Ajax(req))
      }
  
    // @LINE:994
    case controllers_MnuTablas_clienteContactoModifica669_route(params@_) =>
      call(params.fromPath[Long]("id_contacto", None), params.fromPath[Long]("id_cliente", None)) { (id_contacto, id_cliente) =>
        controllers_MnuTablas_clienteContactoModifica669_invoker.call(
          req => MnuTablas_1.clienteContactoModifica(id_contacto, id_cliente, req))
      }
  
    // @LINE:995
    case controllers_MnuTablas_clienteContactoAgrega670_route(params@_) =>
      call(params.fromPath[Long]("id_cliente", None)) { (id_cliente) =>
        controllers_MnuTablas_clienteContactoAgrega670_invoker.call(
          req => MnuTablas_1.clienteContactoAgrega(id_cliente, req))
      }
  
    // @LINE:996
    case controllers_MnuTablas_clienteContactoGraba671_route(params@_) =>
      call { 
        controllers_MnuTablas_clienteContactoGraba671_invoker.call(
          req => MnuTablas_1.clienteContactoGraba(req))
      }
  
    // @LINE:997
    case controllers_MnuTablas_clienteContactoElimina672_route(params@_) =>
      call(params.fromPath[Long]("id_contacto", None), params.fromPath[Long]("id_cliente", None)) { (id_contacto, id_cliente) =>
        controllers_MnuTablas_clienteContactoElimina672_invoker.call(
          req => MnuTablas_1.clienteContactoElimina(id_contacto, id_cliente, req))
      }
  
    // @LINE:998
    case controllers_MnuTablas_modificaContactoClientePorCampoAjax673_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaContactoClientePorCampoAjax673_invoker.call(
          req => MnuTablas_1.modificaContactoClientePorCampoAjax(req))
      }
  
    // @LINE:999
    case controllers_MnuTablas_clienteElimina674_route(params@_) =>
      call { 
        controllers_MnuTablas_clienteElimina674_invoker.call(
          req => MnuTablas_1.clienteElimina(req))
      }
  
    // @LINE:1000
    case controllers_MnuTablas_clienteAgrega675_route(params@_) =>
      call { 
        controllers_MnuTablas_clienteAgrega675_invoker.call(
          req => MnuTablas_1.clienteAgrega(req))
      }
  
    // @LINE:1001
    case controllers_MnuTablas_clienteGraba676_route(params@_) =>
      call { 
        controllers_MnuTablas_clienteGraba676_invoker.call(
          req => MnuTablas_1.clienteGraba(req))
      }
  
    // @LINE:1002
    case controllers_MnuTablas_clienteGrabaAjax677_route(params@_) =>
      call { 
        controllers_MnuTablas_clienteGrabaAjax677_invoker.call(
          req => MnuTablas_1.clienteGrabaAjax(req))
      }
  
    // @LINE:1003
    case controllers_MnuTablas_verificaNickClienteAjax678_route(params@_) =>
      call { 
        controllers_MnuTablas_verificaNickClienteAjax678_invoker.call(
          req => MnuTablas_1.verificaNickClienteAjax(req))
      }
  
    // @LINE:1004
    case controllers_MnuTablas_clienteContactosAjax679_route(params@_) =>
      call { 
        controllers_MnuTablas_clienteContactosAjax679_invoker.call(
          req => MnuTablas_1.clienteContactosAjax(req))
      }
  
    // @LINE:1005
    case controllers_MnuTablas_clienteFindIdForNickNameAjax680_route(params@_) =>
      call { 
        controllers_MnuTablas_clienteFindIdForNickNameAjax680_invoker.call(
          req => MnuTablas_1.clienteFindIdForNickNameAjax(req))
      }
  
    // @LINE:1008
    case controllers_MnuTablas_fabricaMantencion681_route(params@_) =>
      call { 
        controllers_MnuTablas_fabricaMantencion681_invoker.call(
          req => MnuTablas_1.fabricaMantencion(req))
      }
  
    // @LINE:1009
    case controllers_MnuTablas_fabricaModifica682_route(params@_) =>
      call(params.fromPath[Long]("id_fabrica", None)) { (id_fabrica) =>
        controllers_MnuTablas_fabricaModifica682_invoker.call(
          req => MnuTablas_1.fabricaModifica(id_fabrica, req))
      }
  
    // @LINE:1010
    case controllers_MnuTablas_modificaFabricaPorCampoAjax683_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaFabricaPorCampoAjax683_invoker.call(
          req => MnuTablas_1.modificaFabricaPorCampoAjax(req))
      }
  
    // @LINE:1011
    case controllers_MnuTablas_selComuna4Ajax684_route(params@_) =>
      call { 
        controllers_MnuTablas_selComuna4Ajax684_invoker.call(
          req => MnuTablas_1.selComuna4Ajax(req))
      }
  
    // @LINE:1012
    case controllers_MnuTablas_fabricaContactoModifica685_route(params@_) =>
      call(params.fromPath[Long]("id_contacto", None), params.fromPath[Long]("id_fabrica", None)) { (id_contacto, id_fabrica) =>
        controllers_MnuTablas_fabricaContactoModifica685_invoker.call(
          req => MnuTablas_1.fabricaContactoModifica(id_contacto, id_fabrica, req))
      }
  
    // @LINE:1013
    case controllers_MnuTablas_fabricaContactoAgrega686_route(params@_) =>
      call(params.fromPath[Long]("id_fabrica", None)) { (id_fabrica) =>
        controllers_MnuTablas_fabricaContactoAgrega686_invoker.call(
          req => MnuTablas_1.fabricaContactoAgrega(id_fabrica, req))
      }
  
    // @LINE:1014
    case controllers_MnuTablas_fabricaContactoGraba687_route(params@_) =>
      call { 
        controllers_MnuTablas_fabricaContactoGraba687_invoker.call(
          req => MnuTablas_1.fabricaContactoGraba(req))
      }
  
    // @LINE:1015
    case controllers_MnuTablas_fabricaContactoElimina688_route(params@_) =>
      call(params.fromPath[Long]("id_contacto", None), params.fromPath[Long]("id_fabrica", None)) { (id_contacto, id_fabrica) =>
        controllers_MnuTablas_fabricaContactoElimina688_invoker.call(
          req => MnuTablas_1.fabricaContactoElimina(id_contacto, id_fabrica, req))
      }
  
    // @LINE:1016
    case controllers_MnuTablas_modificaContactoFabricaPorCampoAjax689_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaContactoFabricaPorCampoAjax689_invoker.call(
          req => MnuTablas_1.modificaContactoFabricaPorCampoAjax(req))
      }
  
    // @LINE:1017
    case controllers_MnuTablas_fabricaElimina690_route(params@_) =>
      call { 
        controllers_MnuTablas_fabricaElimina690_invoker.call(
          req => MnuTablas_1.fabricaElimina(req))
      }
  
    // @LINE:1018
    case controllers_MnuTablas_fabricaAgrega691_route(params@_) =>
      call { 
        controllers_MnuTablas_fabricaAgrega691_invoker.call(
          req => MnuTablas_1.fabricaAgrega(req))
      }
  
    // @LINE:1019
    case controllers_MnuTablas_fabricaGraba692_route(params@_) =>
      call { 
        controllers_MnuTablas_fabricaGraba692_invoker.call(
          req => MnuTablas_1.fabricaGraba(req))
      }
  
    // @LINE:1020
    case controllers_MnuTablas_verificaNickFabricaAjax693_route(params@_) =>
      call { 
        controllers_MnuTablas_verificaNickFabricaAjax693_invoker.call(
          req => MnuTablas_1.verificaNickFabricaAjax(req))
      }
  
    // @LINE:1022
    case controllers_MnuTablas_allContactosCliProvFabrExcel694_route(params@_) =>
      call { 
        controllers_MnuTablas_allContactosCliProvFabrExcel694_invoker.call(
          req => MnuTablas_1.allContactosCliProvFabrExcel(req))
      }
  
    // @LINE:1024
    case controllers_MnuTablas_transportistaMantencion695_route(params@_) =>
      call { 
        controllers_MnuTablas_transportistaMantencion695_invoker.call(
          req => MnuTablas_1.transportistaMantencion(req))
      }
  
    // @LINE:1027
    case controllers_MnuTablas_proveedorMantencion696_route(params@_) =>
      call { 
        controllers_MnuTablas_proveedorMantencion696_invoker.call(
          req => MnuTablas_1.proveedorMantencion(req))
      }
  
    // @LINE:1028
    case controllers_MnuTablas_proveedorModifica697_route(params@_) =>
      call(params.fromPath[Long]("id_proveedor", None)) { (id_proveedor) =>
        controllers_MnuTablas_proveedorModifica697_invoker.call(
          req => MnuTablas_1.proveedorModifica(id_proveedor, req))
      }
  
    // @LINE:1029
    case controllers_MnuTablas_modificaProveedorPorCampoAjax698_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaProveedorPorCampoAjax698_invoker.call(
          req => MnuTablas_1.modificaProveedorPorCampoAjax(req))
      }
  
    // @LINE:1030
    case controllers_MnuTablas_selComuna5Ajax699_route(params@_) =>
      call { 
        controllers_MnuTablas_selComuna5Ajax699_invoker.call(
          req => MnuTablas_1.selComuna5Ajax(req))
      }
  
    // @LINE:1031
    case controllers_MnuTablas_proveedorContactoModifica700_route(params@_) =>
      call(params.fromPath[Long]("id_contacto", None), params.fromPath[Long]("id_proveedor", None)) { (id_contacto, id_proveedor) =>
        controllers_MnuTablas_proveedorContactoModifica700_invoker.call(
          req => MnuTablas_1.proveedorContactoModifica(id_contacto, id_proveedor, req))
      }
  
    // @LINE:1032
    case controllers_MnuTablas_proveedorContactoAgrega701_route(params@_) =>
      call(params.fromPath[Long]("id_proveedor", None)) { (id_proveedor) =>
        controllers_MnuTablas_proveedorContactoAgrega701_invoker.call(
          req => MnuTablas_1.proveedorContactoAgrega(id_proveedor, req))
      }
  
    // @LINE:1033
    case controllers_MnuTablas_proveedorContactoGraba702_route(params@_) =>
      call { 
        controllers_MnuTablas_proveedorContactoGraba702_invoker.call(
          req => MnuTablas_1.proveedorContactoGraba(req))
      }
  
    // @LINE:1034
    case controllers_MnuTablas_proveedorContactoElimina703_route(params@_) =>
      call(params.fromPath[Long]("id_contacto", None), params.fromPath[Long]("id_proveedor", None)) { (id_contacto, id_proveedor) =>
        controllers_MnuTablas_proveedorContactoElimina703_invoker.call(
          req => MnuTablas_1.proveedorContactoElimina(id_contacto, id_proveedor, req))
      }
  
    // @LINE:1035
    case controllers_MnuTablas_modificaContactoProveedorPorCampoAjax704_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaContactoProveedorPorCampoAjax704_invoker.call(
          req => MnuTablas_1.modificaContactoProveedorPorCampoAjax(req))
      }
  
    // @LINE:1036
    case controllers_MnuTablas_proveedorElimina705_route(params@_) =>
      call { 
        controllers_MnuTablas_proveedorElimina705_invoker.call(
          req => MnuTablas_1.proveedorElimina(req))
      }
  
    // @LINE:1037
    case controllers_MnuTablas_proveedorAgrega706_route(params@_) =>
      call { 
        controllers_MnuTablas_proveedorAgrega706_invoker.call(
          req => MnuTablas_1.proveedorAgrega(req))
      }
  
    // @LINE:1038
    case controllers_MnuTablas_proveedorGraba707_route(params@_) =>
      call { 
        controllers_MnuTablas_proveedorGraba707_invoker.call(
          req => MnuTablas_1.proveedorGraba(req))
      }
  
    // @LINE:1039
    case controllers_MnuTablas_proveedorGrabaAjax708_route(params@_) =>
      call { 
        controllers_MnuTablas_proveedorGrabaAjax708_invoker.call(
          req => MnuTablas_1.proveedorGrabaAjax(req))
      }
  
    // @LINE:1040
    case controllers_MnuTablas_verificaNickProveedorAjax709_route(params@_) =>
      call { 
        controllers_MnuTablas_verificaNickProveedorAjax709_invoker.call(
          req => MnuTablas_1.verificaNickProveedorAjax(req))
      }
  
    // @LINE:1042
    case controllers_MnuTablas_tipoEstadoMantencion710_route(params@_) =>
      call { 
        controllers_MnuTablas_tipoEstadoMantencion710_invoker.call(
          req => MnuTablas_1.tipoEstadoMantencion(req))
      }
  
    // @LINE:1043
    case controllers_MnuTablas_tipoEstadoModifica711_route(params@_) =>
      call(params.fromPath[Long]("id_estado", None)) { (id_estado) =>
        controllers_MnuTablas_tipoEstadoModifica711_invoker.call(
          req => MnuTablas_1.tipoEstadoModifica(id_estado, req))
      }
  
    // @LINE:1044
    case controllers_MnuTablas_modificaTipoEstadoPorCampoAjax712_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaTipoEstadoPorCampoAjax712_invoker.call(
          req => MnuTablas_1.modificaTipoEstadoPorCampoAjax(req))
      }
  
    // @LINE:1045
    case controllers_MnuTablas_tipoReparacionModifica713_route(params@_) =>
      call(params.fromPath[Long]("id_tipoRep", None), params.fromPath[Long]("id_tipoEst", None)) { (id_tipoRep, id_tipoEst) =>
        controllers_MnuTablas_tipoReparacionModifica713_invoker.call(
          req => MnuTablas_1.tipoReparacionModifica(id_tipoRep, id_tipoEst, req))
      }
  
    // @LINE:1046
    case controllers_MnuTablas_tipoReparacionAgrega714_route(params@_) =>
      call(params.fromPath[Long]("id_tipoEst", None)) { (id_tipoEst) =>
        controllers_MnuTablas_tipoReparacionAgrega714_invoker.call(
          req => MnuTablas_1.tipoReparacionAgrega(id_tipoEst, req))
      }
  
    // @LINE:1047
    case controllers_MnuTablas_verificaSiglaTipoReparacionAjax715_route(params@_) =>
      call { 
        controllers_MnuTablas_verificaSiglaTipoReparacionAjax715_invoker.call(
          req => MnuTablas_1.verificaSiglaTipoReparacionAjax(req))
      }
  
    // @LINE:1048
    case controllers_MnuTablas_tipoReparacionGraba716_route(params@_) =>
      call { 
        controllers_MnuTablas_tipoReparacionGraba716_invoker.call(
          req => MnuTablas_1.tipoReparacionGraba(req))
      }
  
    // @LINE:1049
    case controllers_MnuTablas_tipoReparacionElimina717_route(params@_) =>
      call { 
        controllers_MnuTablas_tipoReparacionElimina717_invoker.call(
          req => MnuTablas_1.tipoReparacionElimina(req))
      }
  
    // @LINE:1050
    case controllers_MnuTablas_modificaTipoReparacionPorCampoAjax718_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaTipoReparacionPorCampoAjax718_invoker.call(
          req => MnuTablas_1.modificaTipoReparacionPorCampoAjax(req))
      }
  
    // @LINE:1051
    case controllers_MnuTablas_tipoEstadoElimina719_route(params@_) =>
      call { 
        controllers_MnuTablas_tipoEstadoElimina719_invoker.call(
          req => MnuTablas_1.tipoEstadoElimina(req))
      }
  
    // @LINE:1052
    case controllers_MnuTablas_tipoEstadoAgrega720_route(params@_) =>
      call { 
        controllers_MnuTablas_tipoEstadoAgrega720_invoker.call(
          req => MnuTablas_1.tipoEstadoAgrega(req))
      }
  
    // @LINE:1053
    case controllers_MnuTablas_tipoEstadoGraba721_route(params@_) =>
      call { 
        controllers_MnuTablas_tipoEstadoGraba721_invoker.call(
          req => MnuTablas_1.tipoEstadoGraba(req))
      }
  
    // @LINE:1054
    case controllers_MnuTablas_verificaSiglaTipoEstadoAjax722_route(params@_) =>
      call { 
        controllers_MnuTablas_verificaSiglaTipoEstadoAjax722_invoker.call(
          req => MnuTablas_1.verificaSiglaTipoEstadoAjax(req))
      }
  
    // @LINE:1056
    case controllers_MnuTablas_decimalNumero723_route(params@_) =>
      call { 
        controllers_MnuTablas_decimalNumero723_invoker.call(
          req => MnuTablas_1.decimalNumero(req))
      }
  
    // @LINE:1057
    case controllers_MnuTablas_decimalModificaAjax724_route(params@_) =>
      call { 
        controllers_MnuTablas_decimalModificaAjax724_invoker.call(
          req => MnuTablas_1.decimalModificaAjax(req))
      }
  
    // @LINE:1059
    case controllers_MnuTablas_usuarioMantencion725_route(params@_) =>
      call { 
        controllers_MnuTablas_usuarioMantencion725_invoker.call(
          req => MnuTablas_1.usuarioMantencion(req))
      }
  
    // @LINE:1060
    case controllers_MnuTablas_usuarioModifica726_route(params@_) =>
      call { 
        controllers_MnuTablas_usuarioModifica726_invoker.call(
          req => MnuTablas_1.usuarioModifica(req))
      }
  
    // @LINE:1061
    case controllers_MnuTablas_modificaUsuarioPorCampoAjax727_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaUsuarioPorCampoAjax727_invoker.call(
          req => MnuTablas_1.modificaUsuarioPorCampoAjax(req))
      }
  
    // @LINE:1062
    case controllers_MnuTablas_usuarioElimina728_route(params@_) =>
      call { 
        controllers_MnuTablas_usuarioElimina728_invoker.call(
          req => MnuTablas_1.usuarioElimina(req))
      }
  
    // @LINE:1063
    case controllers_MnuTablas_usuarioAgrega729_route(params@_) =>
      call { 
        controllers_MnuTablas_usuarioAgrega729_invoker.call(
          req => MnuTablas_1.usuarioAgrega(req))
      }
  
    // @LINE:1064
    case controllers_MnuTablas_usuarioGraba730_route(params@_) =>
      call { 
        controllers_MnuTablas_usuarioGraba730_invoker.call(
          req => MnuTablas_1.usuarioGraba(req))
      }
  
    // @LINE:1065
    case controllers_MnuTablas_verificaUserNameUsuarioAjax731_route(params@_) =>
      call { 
        controllers_MnuTablas_verificaUserNameUsuarioAjax731_invoker.call(
          req => MnuTablas_1.verificaUserNameUsuarioAjax(req))
      }
  
    // @LINE:1066
    case controllers_MnuTablas_usuarioListBodegaEmpresa732_route(params@_) =>
      call { 
        controllers_MnuTablas_usuarioListBodegaEmpresa732_invoker.call(
          req => MnuTablas_1.usuarioListBodegaEmpresa(req))
      }
  
    // @LINE:1067
    case controllers_MnuTablas_usuarioBodegaSelect733_route(params@_) =>
      call { 
        controllers_MnuTablas_usuarioBodegaSelect733_invoker.call(
          req => MnuTablas_1.usuarioBodegaSelect(req))
      }
  
    // @LINE:1068
    case controllers_MnuTablas_usuarioBodegaEmpresaElimina734_route(params@_) =>
      call { 
        controllers_MnuTablas_usuarioBodegaEmpresaElimina734_invoker.call(
          req => MnuTablas_1.usuarioBodegaEmpresaElimina(req))
      }
  
    // @LINE:1069
    case controllers_MnuTablas_modVigUsuarioAjax735_route(params@_) =>
      call { 
        controllers_MnuTablas_modVigUsuarioAjax735_invoker.call(
          req => MnuTablas_1.modVigUsuarioAjax(req))
      }
  
    // @LINE:1072
    case controllers_MnuTablas_registroDeCambios736_route(params@_) =>
      call { 
        controllers_MnuTablas_registroDeCambios736_invoker.call(
          req => MnuTablas_1.registroDeCambios(req))
      }
  
    // @LINE:1076
    case controllers_Assets_versioned737_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned737_invoker.call(Assets_15.versioned(path, file))
      }
  
    // @LINE:1080
    case prefixed_routes2_Routes_0_738(handler) => handler
  
    // @LINE:1081
    case prefixed_routes3_Routes_1_739(handler) => handler
  
    // @LINE:1085
    case controllers_HomeController_onUrlNotFind740_route(params@_) =>
      call(params.fromPath[String]("path", None)) { (path) =>
        controllers_HomeController_onUrlNotFind740_invoker.call(
          req => HomeController_18.onUrlNotFind(req, path))
      }
  }
}
