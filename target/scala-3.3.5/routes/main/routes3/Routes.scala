// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes3.routes

package routes3

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:4
  Assets_0: controllers.Assets,
  // @LINE:9
  HomeController_1: controllers.HomeController,
  // @LINE:27
  MnuMantencion_2: controllers.MnuMantencion,
  // @LINE:160
  MnuReportes_3: controllers.MnuReportes,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:4
    Assets_0: controllers.Assets,
    // @LINE:9
    HomeController_1: controllers.HomeController,
    // @LINE:27
    MnuMantencion_2: controllers.MnuMantencion,
    // @LINE:160
    MnuReportes_3: controllers.MnuReportes
  ) = this(errorHandler, Assets_0, HomeController_1, MnuMantencion_2, MnuReportes_3, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    routes3.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Assets_0, HomeController_1, MnuMantencion_2, MnuReportes_3, prefix)
  }

  private val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """viewImg/""" + "$" + """img<[^/]+>""", """controllers.HomeController.viewImg(img:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """showPdf/""" + "$" + """fileNamePdf<[^/]+>""", """controllers.HomeController.showPdf(fileNamePdf:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """showPdfManuales/""" + "$" + """fileNamePdf<[^/]+>""", """controllers.HomeController.showPdfManuales(fileNamePdf:String, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """downloadPDF/""", """controllers.HomeController.downloadPDF(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tasasDeFechaAjax/""", """controllers.HomeController.tasasDeFechaAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sendEmail/""", """controllers.HomeController.sendEmail(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redirShowPDF/""" + "$" + """fileNamePdf<[^/]+>,""" + "$" + """titulo<[^/]+>,""" + "$" + """url<[^/]+>""", """controllers.HomeController.redirShowPDF(fileNamePdf:String, titulo:String, url:String, request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redirOdoShowPDF/""" + "$" + """fileNamePdf<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>,""" + "$" + """url<[^/]+>""", """controllers.HomeController.redirOdoShowPDF(fileNamePdf:String, desde:String, hasta:String, url:String, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebInicio1/""", """controllers.MnuMantencion.mantWebInicio1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebReportNewSave/""", """controllers.MnuMantencion.mantWebReportNewSave(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebInicio1get/""" + "$" + """id_operMec<[^/]+>,""" + "$" + """id_equipo<[^/]+>""", """controllers.MnuMantencion.mantWebInicio1get(request:Request, id_operMec:Long, id_equipo:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebListarReports0/""", """controllers.MnuMantencion.mantWebListarReports0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebListarReports1/""", """controllers.MnuMantencion.mantWebListarReports1(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebListarReports1Get/""" + "$" + """de<[^/]+>,""" + "$" + """a<[^/]+>,""" + "$" + """id_equipo<[^/]+>""", """controllers.MnuMantencion.mantWebListarReports1Get(request:Request, de:String, a:String, id_equipo:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebListarReports1Excel/""", """controllers.MnuMantencion.mantWebListarReports1Excel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebReportDetalle/""", """controllers.MnuMantencion.mantWebReportDetalle(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebReportGrabaDocAnexo/""", """controllers.MnuMantencion.mantWebReportGrabaDocAnexo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebFirmaOperador/""" + "$" + """id_venta<[^/]+>,""" + "$" + """de<[^/]+>,""" + "$" + """a<[^/]+>,""" + "$" + """id_equipo<[^/]+>""", """controllers.MnuMantencion.mantWebFirmaOperador(request:Request, id_venta:Long, de:String, a:String, id_equipo:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebGrabarFirmaOperador/""", """controllers.MnuMantencion.mantWebGrabarFirmaOperador(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebFirmaAutorizador/""" + "$" + """id_venta<[^/]+>,""" + "$" + """de<[^/]+>,""" + "$" + """a<[^/]+>,""" + "$" + """id_equipo<[^/]+>""", """controllers.MnuMantencion.mantWebFirmaAutorizador(request:Request, id_venta:Long, de:String, a:String, id_equipo:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantWebGrabarFirmaAutorizador/""", """controllers.MnuMantencion.mantWebGrabarFirmaAutorizador(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantReportNew/""", """controllers.MnuMantencion.mantReportNew(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantReportNewSave/""", """controllers.MnuMantencion.mantReportNewSave(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantListarReports0/""", """controllers.MnuMantencion.mantListarReports0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantListarReports1/""", """controllers.MnuMantencion.mantListarReports1(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantListarReports1Get/""" + "$" + """d<[^/]+>,""" + "$" + """a<[^/]+>""", """controllers.MnuMantencion.mantListarReports1Get(request:Request, d:String, a:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantReportDetalle/""", """controllers.MnuMantencion.mantReportDetalle(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantListarReports1Excel/""", """controllers.MnuMantencion.mantListarReports1Excel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantReportGrabaDocAnexo/""", """controllers.MnuMantencion.mantReportGrabaDocAnexo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantFirmaOperador/""" + "$" + """id_venta<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""", """controllers.MnuMantencion.mantFirmaOperador(request:Request, id_venta:Long, desde:String, hasta:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantGrabarFirmaOperador/""", """controllers.MnuMantencion.mantGrabarFirmaOperador(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantFirmaAutorizador/""" + "$" + """id_venta<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""", """controllers.MnuMantencion.mantFirmaAutorizador(request:Request, id_venta:Long, desde:String, hasta:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantGrabarFirmaAutorizador/""", """controllers.MnuMantencion.mantGrabarFirmaAutorizador(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantReportElimina/""", """controllers.MnuMantencion.mantReportElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantHistorialReports0/""", """controllers.MnuMantencion.mantHistorialReports0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantHistorialReports1/""", """controllers.MnuMantencion.mantHistorialReports1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantHistorialReports1Excel/""", """controllers.MnuMantencion.mantHistorialReports1Excel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantControlMantenciones/""", """controllers.MnuMantencion.mantControlMantenciones(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantControlMantencionesExcel/""", """controllers.MnuMantencion.mantControlMantencionesExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantCantOperacional0/""", """controllers.MnuMantencion.mantCantOperacional0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantCantOperacional1/""", """controllers.MnuMantencion.mantCantOperacional1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantCantOperacional1Excel/""", """controllers.MnuMantencion.mantCantOperacional1Excel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantCantMantencion0/""", """controllers.MnuMantencion.mantCantMantencion0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantCantMantencion1/""", """controllers.MnuMantencion.mantCantMantencion1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantCantMantencion1Excel/""", """controllers.MnuMantencion.mantCantMantencion1Excel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblItemMant/""", """controllers.MnuMantencion.mantTblItemMant(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblItemNew/""", """controllers.MnuMantencion.mantTblItemNew(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblItemNewSave/""", """controllers.MnuMantencion.mantTblItemNewSave(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblItemUpdate/""", """controllers.MnuMantencion.mantTblItemUpdate(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaItemPorCampoAjax/""", """controllers.MnuMantencion.modificaItemPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblItemDel/""", """controllers.MnuMantencion.mantTblItemDel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblActividadMant/""", """controllers.MnuMantencion.mantTblActividadMant(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblActividadNew/""", """controllers.MnuMantencion.mantTblActividadNew(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblActividadNewSave/""", """controllers.MnuMantencion.mantTblActividadNewSave(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblActividadUpdate/""", """controllers.MnuMantencion.mantTblActividadUpdate(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaActividadPorCampoAjax/""", """controllers.MnuMantencion.modificaActividadPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblActividadDel/""", """controllers.MnuMantencion.mantTblActividadDel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblTipoActividadMant/""", """controllers.MnuMantencion.mantTblTipoActividadMant(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblTipoActividadNew/""", """controllers.MnuMantencion.mantTblTipoActividadNew(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblTipoActividadNewSave/""", """controllers.MnuMantencion.mantTblTipoActividadNewSave(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblTipoActividadUpdate/""", """controllers.MnuMantencion.mantTblTipoActividadUpdate(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaTipoActividadPorCampoAjax/""", """controllers.MnuMantencion.modificaTipoActividadPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblTipoActividadDel/""", """controllers.MnuMantencion.mantTblTipoActividadDel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblComponenteMant/""", """controllers.MnuMantencion.mantTblComponenteMant(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblComponenteNew/""", """controllers.MnuMantencion.mantTblComponenteNew(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblComponenteNewSave/""", """controllers.MnuMantencion.mantTblComponenteNewSave(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblComponenteUpdate/""", """controllers.MnuMantencion.mantTblComponenteUpdate(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaComponentePorCampoAjax/""", """controllers.MnuMantencion.modificaComponentePorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblComponenteDel/""", """controllers.MnuMantencion.mantTblComponenteDel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoEnObraMant/""", """controllers.MnuMantencion.mantTblEstadoEnObraMant(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoEnObraNew/""", """controllers.MnuMantencion.mantTblEstadoEnObraNew(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoEnObraNewSave/""", """controllers.MnuMantencion.mantTblEstadoEnObraNewSave(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoEnObraUpdate/""", """controllers.MnuMantencion.mantTblEstadoEnObraUpdate(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaEstadoEnObraPorCampoAjax/""", """controllers.MnuMantencion.modificaEstadoEnObraPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoEnObraDel/""", """controllers.MnuMantencion.mantTblEstadoEnObraDel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoEnTallerMant/""", """controllers.MnuMantencion.mantTblEstadoEnTallerMant(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoEnTallerNew/""", """controllers.MnuMantencion.mantTblEstadoEnTallerNew(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoEnTallerNewSave/""", """controllers.MnuMantencion.mantTblEstadoEnTallerNewSave(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoEnTallerUpdate/""", """controllers.MnuMantencion.mantTblEstadoEnTallerUpdate(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaEstadoEnTallerPorCampoAjax/""", """controllers.MnuMantencion.modificaEstadoEnTallerPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoEnTallerDel/""", """controllers.MnuMantencion.mantTblEstadoEnTallerDel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoOperacionalMant/""", """controllers.MnuMantencion.mantTblEstadoOperacionalMant(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoOperacionalNew/""", """controllers.MnuMantencion.mantTblEstadoOperacionalNew(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoOperacionalNewSave/""", """controllers.MnuMantencion.mantTblEstadoOperacionalNewSave(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoOperacionalUpdate/""", """controllers.MnuMantencion.mantTblEstadoOperacionalUpdate(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaEstadoOperacionalPorCampoAjax/""", """controllers.MnuMantencion.modificaEstadoOperacionalPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblEstadoOperacionalDel/""", """controllers.MnuMantencion.mantTblEstadoOperacionalDel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblOperadorMecanicoMant/""", """controllers.MnuMantencion.mantTblOperadorMecanicoMant(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblOperadorMecanicoNew/""", """controllers.MnuMantencion.mantTblOperadorMecanicoNew(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblOperadorMecanicoNewSave/""", """controllers.MnuMantencion.mantTblOperadorMecanicoNewSave(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblOperadorMecanicoUpdate/""", """controllers.MnuMantencion.mantTblOperadorMecanicoUpdate(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaOperadorMecanicoPorCampoAjax/""", """controllers.MnuMantencion.modificaOperadorMecanicoPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblOperadorMecanicoVig/""", """controllers.MnuMantencion.mantTblOperadorMecanicoVig(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """mantTblOperadorMecanicoVerifica/""", """controllers.MnuMantencion.mantTblOperadorMecanicoVerifica(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """estadosMantCobraArriendo0/""", """controllers.MnuReportes.estadosMantCobraArriendo0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """estadosMantCobraArriendo1/""", """controllers.MnuReportes.estadosMantCobraArriendo1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modCobraArriendoAjax/""", """controllers.MnuReportes.modCobraArriendoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaEstadoListaPeriodo/""", """controllers.MnuReportes.proformaEstadoListaPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaEstadoLista/""", """controllers.MnuReportes.proformaEstadoLista(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaEstadoListaGet/""" + "$" + """d<[^/]+>,""" + "$" + """h<[^/]+>""", """controllers.MnuReportes.proformaEstadoListaGet(request:Request, d:String, h:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaEstadoElimina/""", """controllers.MnuReportes.proformaEstadoElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """estadosFacturaPeriodo/""", """controllers.MnuReportes.estadosFacturaPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """estadosFacturaProyecto/""" + "$" + """archivoPDF<[^/]+>""", """controllers.MnuReportes.estadosFacturaProyecto(request:Request, archivoPDF:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """estadosFacturaProyectoGet/""" + "$" + """d<[^/]+>,""" + "$" + """a<[^/]+>,""" + "$" + """u<[^/]+>,""" + "$" + """e<[^/]+>,""" + "$" + """f<[^/]+>,""" + "$" + """p<[^/]+>""", """controllers.MnuReportes.estadosFacturaProyectoGet(request:Request, d:String, a:String, u:Double, e:Double, f:Double, p:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """estadosFacturaProyectoExcel/""", """controllers.MnuReportes.estadosFacturaProyectoExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """estadosFacturaProyectoDetalle/""", """controllers.MnuReportes.estadosFacturaProyectoDetalle(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """estadosFacturaProyectoDetExcel/""", """controllers.MnuReportes.estadosFacturaProyectoDetExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """enviarAlistAjustes/""", """controllers.MnuReportes.enviarAlistAjustes(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """envioMasivoAlistAjustes/""", """controllers.MnuReportes.envioMasivoAlistAjustes(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaEstadoListaPdf/""", """controllers.MnuReportes.proformaEstadoListaPdf(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaEstadoListaExcel/""", """controllers.MnuReportes.proformaEstadoListaExcel(request:Request)"""),
    Nil
  ).foldLeft(Seq.empty[(String, String, String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String, String, String)]
    case l => s ++ l.asInstanceOf[List[(String, String, String)]]
  }}


  // @LINE:4
  private lazy val controllers_Assets_versioned0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""", encodeable=false)))
  )
  private lazy val controllers_Assets_versioned0_invoker = createInvoker(
    Assets_0.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:9
  private lazy val controllers_HomeController_viewImg1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("viewImg/"), DynamicPart("img", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_viewImg1_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_1.viewImg(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
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

  // @LINE:10
  private lazy val controllers_HomeController_showPdf2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("showPdf/"), DynamicPart("fileNamePdf", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_showPdf2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_1.showPdf(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.HomeController",
      "showPdf",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """showPdf/""" + "$" + """fileNamePdf<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private lazy val controllers_HomeController_showPdfManuales3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("showPdfManuales/"), DynamicPart("fileNamePdf", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_showPdfManuales3_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_1.showPdfManuales(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.HomeController",
      "showPdfManuales",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """showPdfManuales/""" + "$" + """fileNamePdf<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private lazy val controllers_HomeController_downloadPDF4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("downloadPDF/")))
  )
  private lazy val controllers_HomeController_downloadPDF4_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_1.downloadPDF(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.HomeController",
      "downloadPDF",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """downloadPDF/""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private lazy val controllers_HomeController_tasasDeFechaAjax5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tasasDeFechaAjax/")))
  )
  private lazy val controllers_HomeController_tasasDeFechaAjax5_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_1.tasasDeFechaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.HomeController",
      "tasasDeFechaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tasasDeFechaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private lazy val controllers_HomeController_sendEmail6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sendEmail/")))
  )
  private lazy val controllers_HomeController_sendEmail6_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_1.sendEmail(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.HomeController",
      "sendEmail",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """sendEmail/""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private lazy val controllers_HomeController_redirShowPDF7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redirShowPDF/"), DynamicPart("fileNamePdf", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("titulo", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("url", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_redirShowPDF7_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_1.redirShowPDF(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.HomeController",
      "redirShowPDF",
      Seq(classOf[String], classOf[String], classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """redirShowPDF/""" + "$" + """fileNamePdf<[^/]+>,""" + "$" + """titulo<[^/]+>,""" + "$" + """url<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private lazy val controllers_HomeController_redirOdoShowPDF8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redirOdoShowPDF/"), DynamicPart("fileNamePdf", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("url", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_redirOdoShowPDF8_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_1.redirOdoShowPDF(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.HomeController",
      "redirOdoShowPDF",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """redirOdoShowPDF/""" + "$" + """fileNamePdf<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>,""" + "$" + """url<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:27
  private lazy val controllers_MnuMantencion_mantWebInicio19_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebInicio1/")))
  )
  private lazy val controllers_MnuMantencion_mantWebInicio19_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebInicio1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebInicio1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantWebInicio1/""",
      """""",
      Seq()
    )
  )

  // @LINE:28
  private lazy val controllers_MnuMantencion_mantWebReportNewSave10_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebReportNewSave/")))
  )
  private lazy val controllers_MnuMantencion_mantWebReportNewSave10_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebReportNewSave(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebReportNewSave",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantWebReportNewSave/""",
      """""",
      Seq()
    )
  )

  // @LINE:29
  private lazy val controllers_MnuMantencion_mantWebInicio1get11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebInicio1get/"), DynamicPart("id_operMec", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_equipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMantencion_mantWebInicio1get11_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebInicio1get(fakeValue[play.mvc.Http.Request], fakeValue[Long], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebInicio1get",
      Seq(classOf[play.mvc.Http.Request], classOf[Long], classOf[Long]),
      "GET",
      this.prefix + """mantWebInicio1get/""" + "$" + """id_operMec<[^/]+>,""" + "$" + """id_equipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:31
  private lazy val controllers_MnuMantencion_mantWebListarReports012_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebListarReports0/")))
  )
  private lazy val controllers_MnuMantencion_mantWebListarReports012_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebListarReports0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebListarReports0",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantWebListarReports0/""",
      """""",
      Seq()
    )
  )

  // @LINE:32
  private lazy val controllers_MnuMantencion_mantWebListarReports113_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebListarReports1/")))
  )
  private lazy val controllers_MnuMantencion_mantWebListarReports113_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebListarReports1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebListarReports1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantWebListarReports1/""",
      """""",
      Seq()
    )
  )

  // @LINE:33
  private lazy val controllers_MnuMantencion_mantWebListarReports1Get14_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebListarReports1Get/"), DynamicPart("de", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("a", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_equipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMantencion_mantWebListarReports1Get14_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebListarReports1Get(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebListarReports1Get",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String], classOf[Long]),
      "GET",
      this.prefix + """mantWebListarReports1Get/""" + "$" + """de<[^/]+>,""" + "$" + """a<[^/]+>,""" + "$" + """id_equipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:35
  private lazy val controllers_MnuMantencion_mantWebListarReports1Excel15_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebListarReports1Excel/")))
  )
  private lazy val controllers_MnuMantencion_mantWebListarReports1Excel15_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebListarReports1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebListarReports1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantWebListarReports1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:36
  private lazy val controllers_MnuMantencion_mantWebReportDetalle16_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebReportDetalle/")))
  )
  private lazy val controllers_MnuMantencion_mantWebReportDetalle16_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebReportDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebReportDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantWebReportDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:38
  private lazy val controllers_MnuMantencion_mantWebReportGrabaDocAnexo17_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebReportGrabaDocAnexo/")))
  )
  private lazy val controllers_MnuMantencion_mantWebReportGrabaDocAnexo17_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebReportGrabaDocAnexo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebReportGrabaDocAnexo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantWebReportGrabaDocAnexo/""",
      """""",
      Seq()
    )
  )

  // @LINE:39
  private lazy val controllers_MnuMantencion_mantWebFirmaOperador18_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebFirmaOperador/"), DynamicPart("id_venta", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("de", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("a", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_equipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMantencion_mantWebFirmaOperador18_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebFirmaOperador(fakeValue[play.mvc.Http.Request], fakeValue[Long], fakeValue[String], fakeValue[String], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebFirmaOperador",
      Seq(classOf[play.mvc.Http.Request], classOf[Long], classOf[String], classOf[String], classOf[Long]),
      "GET",
      this.prefix + """mantWebFirmaOperador/""" + "$" + """id_venta<[^/]+>,""" + "$" + """de<[^/]+>,""" + "$" + """a<[^/]+>,""" + "$" + """id_equipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:40
  private lazy val controllers_MnuMantencion_mantWebGrabarFirmaOperador19_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebGrabarFirmaOperador/")))
  )
  private lazy val controllers_MnuMantencion_mantWebGrabarFirmaOperador19_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebGrabarFirmaOperador(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebGrabarFirmaOperador",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantWebGrabarFirmaOperador/""",
      """""",
      Seq()
    )
  )

  // @LINE:41
  private lazy val controllers_MnuMantencion_mantWebFirmaAutorizador20_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebFirmaAutorizador/"), DynamicPart("id_venta", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("de", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("a", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("id_equipo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMantencion_mantWebFirmaAutorizador20_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebFirmaAutorizador(fakeValue[play.mvc.Http.Request], fakeValue[Long], fakeValue[String], fakeValue[String], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebFirmaAutorizador",
      Seq(classOf[play.mvc.Http.Request], classOf[Long], classOf[String], classOf[String], classOf[Long]),
      "GET",
      this.prefix + """mantWebFirmaAutorizador/""" + "$" + """id_venta<[^/]+>,""" + "$" + """de<[^/]+>,""" + "$" + """a<[^/]+>,""" + "$" + """id_equipo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:42
  private lazy val controllers_MnuMantencion_mantWebGrabarFirmaAutorizador21_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantWebGrabarFirmaAutorizador/")))
  )
  private lazy val controllers_MnuMantencion_mantWebGrabarFirmaAutorizador21_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantWebGrabarFirmaAutorizador(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantWebGrabarFirmaAutorizador",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantWebGrabarFirmaAutorizador/""",
      """""",
      Seq()
    )
  )

  // @LINE:50
  private lazy val controllers_MnuMantencion_mantReportNew22_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantReportNew/")))
  )
  private lazy val controllers_MnuMantencion_mantReportNew22_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantReportNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantReportNew",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantReportNew/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU INGRESO REPORT
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:51
  private lazy val controllers_MnuMantencion_mantReportNewSave23_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantReportNewSave/")))
  )
  private lazy val controllers_MnuMantencion_mantReportNewSave23_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantReportNewSave(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantReportNewSave",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantReportNewSave/""",
      """""",
      Seq()
    )
  )

  // @LINE:56
  private lazy val controllers_MnuMantencion_mantListarReports024_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantListarReports0/")))
  )
  private lazy val controllers_MnuMantencion_mantListarReports024_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantListarReports0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantListarReports0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantListarReports0/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU LISTAR REPORT
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:57
  private lazy val controllers_MnuMantencion_mantListarReports125_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantListarReports1/")))
  )
  private lazy val controllers_MnuMantencion_mantListarReports125_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantListarReports1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantListarReports1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantListarReports1/""",
      """""",
      Seq()
    )
  )

  // @LINE:58
  private lazy val controllers_MnuMantencion_mantListarReports1Get26_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantListarReports1Get/"), DynamicPart("d", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("a", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMantencion_mantListarReports1Get26_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantListarReports1Get(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantListarReports1Get",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "GET",
      this.prefix + """mantListarReports1Get/""" + "$" + """d<[^/]+>,""" + "$" + """a<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:59
  private lazy val controllers_MnuMantencion_mantReportDetalle27_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantReportDetalle/")))
  )
  private lazy val controllers_MnuMantencion_mantReportDetalle27_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantReportDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantReportDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantReportDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:60
  private lazy val controllers_MnuMantencion_mantListarReports1Excel28_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantListarReports1Excel/")))
  )
  private lazy val controllers_MnuMantencion_mantListarReports1Excel28_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantListarReports1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantListarReports1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantListarReports1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:61
  private lazy val controllers_MnuMantencion_mantReportGrabaDocAnexo29_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantReportGrabaDocAnexo/")))
  )
  private lazy val controllers_MnuMantencion_mantReportGrabaDocAnexo29_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantReportGrabaDocAnexo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantReportGrabaDocAnexo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantReportGrabaDocAnexo/""",
      """""",
      Seq()
    )
  )

  // @LINE:62
  private lazy val controllers_MnuMantencion_mantFirmaOperador30_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantFirmaOperador/"), DynamicPart("id_venta", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMantencion_mantFirmaOperador30_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantFirmaOperador(fakeValue[play.mvc.Http.Request], fakeValue[Long], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantFirmaOperador",
      Seq(classOf[play.mvc.Http.Request], classOf[Long], classOf[String], classOf[String]),
      "GET",
      this.prefix + """mantFirmaOperador/""" + "$" + """id_venta<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:63
  private lazy val controllers_MnuMantencion_mantGrabarFirmaOperador31_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantGrabarFirmaOperador/")))
  )
  private lazy val controllers_MnuMantencion_mantGrabarFirmaOperador31_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantGrabarFirmaOperador(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantGrabarFirmaOperador",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantGrabarFirmaOperador/""",
      """""",
      Seq()
    )
  )

  // @LINE:64
  private lazy val controllers_MnuMantencion_mantFirmaAutorizador32_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantFirmaAutorizador/"), DynamicPart("id_venta", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuMantencion_mantFirmaAutorizador32_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantFirmaAutorizador(fakeValue[play.mvc.Http.Request], fakeValue[Long], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantFirmaAutorizador",
      Seq(classOf[play.mvc.Http.Request], classOf[Long], classOf[String], classOf[String]),
      "GET",
      this.prefix + """mantFirmaAutorizador/""" + "$" + """id_venta<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:65
  private lazy val controllers_MnuMantencion_mantGrabarFirmaAutorizador33_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantGrabarFirmaAutorizador/")))
  )
  private lazy val controllers_MnuMantencion_mantGrabarFirmaAutorizador33_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantGrabarFirmaAutorizador(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantGrabarFirmaAutorizador",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantGrabarFirmaAutorizador/""",
      """""",
      Seq()
    )
  )

  // @LINE:66
  private lazy val controllers_MnuMantencion_mantReportElimina34_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantReportElimina/")))
  )
  private lazy val controllers_MnuMantencion_mantReportElimina34_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantReportElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantReportElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantReportElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:71
  private lazy val controllers_MnuMantencion_mantHistorialReports035_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantHistorialReports0/")))
  )
  private lazy val controllers_MnuMantencion_mantHistorialReports035_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantHistorialReports0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantHistorialReports0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantHistorialReports0/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU HISTORIAL REPORT
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:72
  private lazy val controllers_MnuMantencion_mantHistorialReports136_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantHistorialReports1/")))
  )
  private lazy val controllers_MnuMantencion_mantHistorialReports136_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantHistorialReports1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantHistorialReports1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantHistorialReports1/""",
      """""",
      Seq()
    )
  )

  // @LINE:73
  private lazy val controllers_MnuMantencion_mantHistorialReports1Excel37_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantHistorialReports1Excel/")))
  )
  private lazy val controllers_MnuMantencion_mantHistorialReports1Excel37_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantHistorialReports1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantHistorialReports1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantHistorialReports1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:78
  private lazy val controllers_MnuMantencion_mantControlMantenciones38_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantControlMantenciones/")))
  )
  private lazy val controllers_MnuMantencion_mantControlMantenciones38_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantControlMantenciones(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantControlMantenciones",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantControlMantenciones/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU CONTROL MANTENCIONES
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:79
  private lazy val controllers_MnuMantencion_mantControlMantencionesExcel39_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantControlMantencionesExcel/")))
  )
  private lazy val controllers_MnuMantencion_mantControlMantencionesExcel39_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantControlMantencionesExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantControlMantencionesExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantControlMantencionesExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:84
  private lazy val controllers_MnuMantencion_mantCantOperacional040_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantCantOperacional0/")))
  )
  private lazy val controllers_MnuMantencion_mantCantOperacional040_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantCantOperacional0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantCantOperacional0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantCantOperacional0/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU Cantidad Operacional y Mantencion
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:85
  private lazy val controllers_MnuMantencion_mantCantOperacional141_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantCantOperacional1/")))
  )
  private lazy val controllers_MnuMantencion_mantCantOperacional141_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantCantOperacional1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantCantOperacional1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantCantOperacional1/""",
      """""",
      Seq()
    )
  )

  // @LINE:86
  private lazy val controllers_MnuMantencion_mantCantOperacional1Excel42_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantCantOperacional1Excel/")))
  )
  private lazy val controllers_MnuMantencion_mantCantOperacional1Excel42_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantCantOperacional1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantCantOperacional1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantCantOperacional1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:88
  private lazy val controllers_MnuMantencion_mantCantMantencion043_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantCantMantencion0/")))
  )
  private lazy val controllers_MnuMantencion_mantCantMantencion043_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantCantMantencion0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantCantMantencion0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantCantMantencion0/""",
      """""",
      Seq()
    )
  )

  // @LINE:89
  private lazy val controllers_MnuMantencion_mantCantMantencion144_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantCantMantencion1/")))
  )
  private lazy val controllers_MnuMantencion_mantCantMantencion144_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantCantMantencion1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantCantMantencion1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantCantMantencion1/""",
      """""",
      Seq()
    )
  )

  // @LINE:90
  private lazy val controllers_MnuMantencion_mantCantMantencion1Excel45_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantCantMantencion1Excel/")))
  )
  private lazy val controllers_MnuMantencion_mantCantMantencion1Excel45_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantCantMantencion1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantCantMantencion1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantCantMantencion1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:98
  private lazy val controllers_MnuMantencion_mantTblItemMant46_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblItemMant/")))
  )
  private lazy val controllers_MnuMantencion_mantTblItemMant46_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblItemMant(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblItemMant",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblItemMant/""",
      """""",
      Seq()
    )
  )

  // @LINE:99
  private lazy val controllers_MnuMantencion_mantTblItemNew47_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblItemNew/")))
  )
  private lazy val controllers_MnuMantencion_mantTblItemNew47_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblItemNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblItemNew",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblItemNew/""",
      """""",
      Seq()
    )
  )

  // @LINE:100
  private lazy val controllers_MnuMantencion_mantTblItemNewSave48_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblItemNewSave/")))
  )
  private lazy val controllers_MnuMantencion_mantTblItemNewSave48_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblItemNewSave(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblItemNewSave",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblItemNewSave/""",
      """""",
      Seq()
    )
  )

  // @LINE:101
  private lazy val controllers_MnuMantencion_mantTblItemUpdate49_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblItemUpdate/")))
  )
  private lazy val controllers_MnuMantencion_mantTblItemUpdate49_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblItemUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblItemUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblItemUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:102
  private lazy val controllers_MnuMantencion_modificaItemPorCampoAjax50_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaItemPorCampoAjax/")))
  )
  private lazy val controllers_MnuMantencion_modificaItemPorCampoAjax50_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.modificaItemPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "modificaItemPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaItemPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:103
  private lazy val controllers_MnuMantencion_mantTblItemDel51_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblItemDel/")))
  )
  private lazy val controllers_MnuMantencion_mantTblItemDel51_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblItemDel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblItemDel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblItemDel/""",
      """""",
      Seq()
    )
  )

  // @LINE:105
  private lazy val controllers_MnuMantencion_mantTblActividadMant52_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblActividadMant/")))
  )
  private lazy val controllers_MnuMantencion_mantTblActividadMant52_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblActividadMant(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblActividadMant",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblActividadMant/""",
      """""",
      Seq()
    )
  )

  // @LINE:106
  private lazy val controllers_MnuMantencion_mantTblActividadNew53_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblActividadNew/")))
  )
  private lazy val controllers_MnuMantencion_mantTblActividadNew53_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblActividadNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblActividadNew",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblActividadNew/""",
      """""",
      Seq()
    )
  )

  // @LINE:107
  private lazy val controllers_MnuMantencion_mantTblActividadNewSave54_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblActividadNewSave/")))
  )
  private lazy val controllers_MnuMantencion_mantTblActividadNewSave54_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblActividadNewSave(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblActividadNewSave",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblActividadNewSave/""",
      """""",
      Seq()
    )
  )

  // @LINE:108
  private lazy val controllers_MnuMantencion_mantTblActividadUpdate55_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblActividadUpdate/")))
  )
  private lazy val controllers_MnuMantencion_mantTblActividadUpdate55_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblActividadUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblActividadUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblActividadUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:109
  private lazy val controllers_MnuMantencion_modificaActividadPorCampoAjax56_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaActividadPorCampoAjax/")))
  )
  private lazy val controllers_MnuMantencion_modificaActividadPorCampoAjax56_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.modificaActividadPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "modificaActividadPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaActividadPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:110
  private lazy val controllers_MnuMantencion_mantTblActividadDel57_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblActividadDel/")))
  )
  private lazy val controllers_MnuMantencion_mantTblActividadDel57_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblActividadDel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblActividadDel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblActividadDel/""",
      """""",
      Seq()
    )
  )

  // @LINE:112
  private lazy val controllers_MnuMantencion_mantTblTipoActividadMant58_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblTipoActividadMant/")))
  )
  private lazy val controllers_MnuMantencion_mantTblTipoActividadMant58_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblTipoActividadMant(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblTipoActividadMant",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblTipoActividadMant/""",
      """""",
      Seq()
    )
  )

  // @LINE:113
  private lazy val controllers_MnuMantencion_mantTblTipoActividadNew59_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblTipoActividadNew/")))
  )
  private lazy val controllers_MnuMantencion_mantTblTipoActividadNew59_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblTipoActividadNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblTipoActividadNew",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblTipoActividadNew/""",
      """""",
      Seq()
    )
  )

  // @LINE:114
  private lazy val controllers_MnuMantencion_mantTblTipoActividadNewSave60_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblTipoActividadNewSave/")))
  )
  private lazy val controllers_MnuMantencion_mantTblTipoActividadNewSave60_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblTipoActividadNewSave(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblTipoActividadNewSave",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblTipoActividadNewSave/""",
      """""",
      Seq()
    )
  )

  // @LINE:115
  private lazy val controllers_MnuMantencion_mantTblTipoActividadUpdate61_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblTipoActividadUpdate/")))
  )
  private lazy val controllers_MnuMantencion_mantTblTipoActividadUpdate61_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblTipoActividadUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblTipoActividadUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblTipoActividadUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:116
  private lazy val controllers_MnuMantencion_modificaTipoActividadPorCampoAjax62_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaTipoActividadPorCampoAjax/")))
  )
  private lazy val controllers_MnuMantencion_modificaTipoActividadPorCampoAjax62_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.modificaTipoActividadPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "modificaTipoActividadPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaTipoActividadPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:117
  private lazy val controllers_MnuMantencion_mantTblTipoActividadDel63_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblTipoActividadDel/")))
  )
  private lazy val controllers_MnuMantencion_mantTblTipoActividadDel63_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblTipoActividadDel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblTipoActividadDel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblTipoActividadDel/""",
      """""",
      Seq()
    )
  )

  // @LINE:119
  private lazy val controllers_MnuMantencion_mantTblComponenteMant64_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblComponenteMant/")))
  )
  private lazy val controllers_MnuMantencion_mantTblComponenteMant64_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblComponenteMant(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblComponenteMant",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblComponenteMant/""",
      """""",
      Seq()
    )
  )

  // @LINE:120
  private lazy val controllers_MnuMantencion_mantTblComponenteNew65_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblComponenteNew/")))
  )
  private lazy val controllers_MnuMantencion_mantTblComponenteNew65_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblComponenteNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblComponenteNew",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblComponenteNew/""",
      """""",
      Seq()
    )
  )

  // @LINE:121
  private lazy val controllers_MnuMantencion_mantTblComponenteNewSave66_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblComponenteNewSave/")))
  )
  private lazy val controllers_MnuMantencion_mantTblComponenteNewSave66_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblComponenteNewSave(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblComponenteNewSave",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblComponenteNewSave/""",
      """""",
      Seq()
    )
  )

  // @LINE:122
  private lazy val controllers_MnuMantencion_mantTblComponenteUpdate67_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblComponenteUpdate/")))
  )
  private lazy val controllers_MnuMantencion_mantTblComponenteUpdate67_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblComponenteUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblComponenteUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblComponenteUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:123
  private lazy val controllers_MnuMantencion_modificaComponentePorCampoAjax68_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaComponentePorCampoAjax/")))
  )
  private lazy val controllers_MnuMantencion_modificaComponentePorCampoAjax68_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.modificaComponentePorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "modificaComponentePorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaComponentePorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:124
  private lazy val controllers_MnuMantencion_mantTblComponenteDel69_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblComponenteDel/")))
  )
  private lazy val controllers_MnuMantencion_mantTblComponenteDel69_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblComponenteDel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblComponenteDel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblComponenteDel/""",
      """""",
      Seq()
    )
  )

  // @LINE:126
  private lazy val controllers_MnuMantencion_mantTblEstadoEnObraMant70_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoEnObraMant/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoEnObraMant70_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoEnObraMant(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoEnObraMant",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblEstadoEnObraMant/""",
      """""",
      Seq()
    )
  )

  // @LINE:127
  private lazy val controllers_MnuMantencion_mantTblEstadoEnObraNew71_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoEnObraNew/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoEnObraNew71_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoEnObraNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoEnObraNew",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblEstadoEnObraNew/""",
      """""",
      Seq()
    )
  )

  // @LINE:128
  private lazy val controllers_MnuMantencion_mantTblEstadoEnObraNewSave72_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoEnObraNewSave/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoEnObraNewSave72_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoEnObraNewSave(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoEnObraNewSave",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblEstadoEnObraNewSave/""",
      """""",
      Seq()
    )
  )

  // @LINE:129
  private lazy val controllers_MnuMantencion_mantTblEstadoEnObraUpdate73_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoEnObraUpdate/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoEnObraUpdate73_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoEnObraUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoEnObraUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblEstadoEnObraUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:130
  private lazy val controllers_MnuMantencion_modificaEstadoEnObraPorCampoAjax74_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaEstadoEnObraPorCampoAjax/")))
  )
  private lazy val controllers_MnuMantencion_modificaEstadoEnObraPorCampoAjax74_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.modificaEstadoEnObraPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "modificaEstadoEnObraPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaEstadoEnObraPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:131
  private lazy val controllers_MnuMantencion_mantTblEstadoEnObraDel75_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoEnObraDel/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoEnObraDel75_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoEnObraDel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoEnObraDel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblEstadoEnObraDel/""",
      """""",
      Seq()
    )
  )

  // @LINE:133
  private lazy val controllers_MnuMantencion_mantTblEstadoEnTallerMant76_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoEnTallerMant/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoEnTallerMant76_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoEnTallerMant(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoEnTallerMant",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblEstadoEnTallerMant/""",
      """""",
      Seq()
    )
  )

  // @LINE:134
  private lazy val controllers_MnuMantencion_mantTblEstadoEnTallerNew77_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoEnTallerNew/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoEnTallerNew77_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoEnTallerNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoEnTallerNew",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblEstadoEnTallerNew/""",
      """""",
      Seq()
    )
  )

  // @LINE:135
  private lazy val controllers_MnuMantencion_mantTblEstadoEnTallerNewSave78_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoEnTallerNewSave/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoEnTallerNewSave78_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoEnTallerNewSave(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoEnTallerNewSave",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblEstadoEnTallerNewSave/""",
      """""",
      Seq()
    )
  )

  // @LINE:136
  private lazy val controllers_MnuMantencion_mantTblEstadoEnTallerUpdate79_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoEnTallerUpdate/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoEnTallerUpdate79_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoEnTallerUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoEnTallerUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblEstadoEnTallerUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:137
  private lazy val controllers_MnuMantencion_modificaEstadoEnTallerPorCampoAjax80_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaEstadoEnTallerPorCampoAjax/")))
  )
  private lazy val controllers_MnuMantencion_modificaEstadoEnTallerPorCampoAjax80_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.modificaEstadoEnTallerPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "modificaEstadoEnTallerPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaEstadoEnTallerPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:138
  private lazy val controllers_MnuMantencion_mantTblEstadoEnTallerDel81_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoEnTallerDel/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoEnTallerDel81_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoEnTallerDel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoEnTallerDel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblEstadoEnTallerDel/""",
      """""",
      Seq()
    )
  )

  // @LINE:140
  private lazy val controllers_MnuMantencion_mantTblEstadoOperacionalMant82_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoOperacionalMant/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoOperacionalMant82_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoOperacionalMant(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoOperacionalMant",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblEstadoOperacionalMant/""",
      """""",
      Seq()
    )
  )

  // @LINE:141
  private lazy val controllers_MnuMantencion_mantTblEstadoOperacionalNew83_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoOperacionalNew/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoOperacionalNew83_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoOperacionalNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoOperacionalNew",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblEstadoOperacionalNew/""",
      """""",
      Seq()
    )
  )

  // @LINE:142
  private lazy val controllers_MnuMantencion_mantTblEstadoOperacionalNewSave84_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoOperacionalNewSave/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoOperacionalNewSave84_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoOperacionalNewSave(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoOperacionalNewSave",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblEstadoOperacionalNewSave/""",
      """""",
      Seq()
    )
  )

  // @LINE:143
  private lazy val controllers_MnuMantencion_mantTblEstadoOperacionalUpdate85_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoOperacionalUpdate/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoOperacionalUpdate85_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoOperacionalUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoOperacionalUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblEstadoOperacionalUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:144
  private lazy val controllers_MnuMantencion_modificaEstadoOperacionalPorCampoAjax86_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaEstadoOperacionalPorCampoAjax/")))
  )
  private lazy val controllers_MnuMantencion_modificaEstadoOperacionalPorCampoAjax86_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.modificaEstadoOperacionalPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "modificaEstadoOperacionalPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaEstadoOperacionalPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:145
  private lazy val controllers_MnuMantencion_mantTblEstadoOperacionalDel87_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblEstadoOperacionalDel/")))
  )
  private lazy val controllers_MnuMantencion_mantTblEstadoOperacionalDel87_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblEstadoOperacionalDel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblEstadoOperacionalDel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblEstadoOperacionalDel/""",
      """""",
      Seq()
    )
  )

  // @LINE:147
  private lazy val controllers_MnuMantencion_mantTblOperadorMecanicoMant88_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblOperadorMecanicoMant/")))
  )
  private lazy val controllers_MnuMantencion_mantTblOperadorMecanicoMant88_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblOperadorMecanicoMant(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblOperadorMecanicoMant",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblOperadorMecanicoMant/""",
      """""",
      Seq()
    )
  )

  // @LINE:148
  private lazy val controllers_MnuMantencion_mantTblOperadorMecanicoNew89_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblOperadorMecanicoNew/")))
  )
  private lazy val controllers_MnuMantencion_mantTblOperadorMecanicoNew89_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblOperadorMecanicoNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblOperadorMecanicoNew",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """mantTblOperadorMecanicoNew/""",
      """""",
      Seq()
    )
  )

  // @LINE:149
  private lazy val controllers_MnuMantencion_mantTblOperadorMecanicoNewSave90_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblOperadorMecanicoNewSave/")))
  )
  private lazy val controllers_MnuMantencion_mantTblOperadorMecanicoNewSave90_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblOperadorMecanicoNewSave(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblOperadorMecanicoNewSave",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblOperadorMecanicoNewSave/""",
      """""",
      Seq()
    )
  )

  // @LINE:150
  private lazy val controllers_MnuMantencion_mantTblOperadorMecanicoUpdate91_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblOperadorMecanicoUpdate/")))
  )
  private lazy val controllers_MnuMantencion_mantTblOperadorMecanicoUpdate91_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblOperadorMecanicoUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblOperadorMecanicoUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblOperadorMecanicoUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:151
  private lazy val controllers_MnuMantencion_modificaOperadorMecanicoPorCampoAjax92_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaOperadorMecanicoPorCampoAjax/")))
  )
  private lazy val controllers_MnuMantencion_modificaOperadorMecanicoPorCampoAjax92_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.modificaOperadorMecanicoPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "modificaOperadorMecanicoPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaOperadorMecanicoPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:152
  private lazy val controllers_MnuMantencion_mantTblOperadorMecanicoVig93_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblOperadorMecanicoVig/")))
  )
  private lazy val controllers_MnuMantencion_mantTblOperadorMecanicoVig93_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblOperadorMecanicoVig(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblOperadorMecanicoVig",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblOperadorMecanicoVig/""",
      """""",
      Seq()
    )
  )

  // @LINE:153
  private lazy val controllers_MnuMantencion_mantTblOperadorMecanicoVerifica94_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("mantTblOperadorMecanicoVerifica/")))
  )
  private lazy val controllers_MnuMantencion_mantTblOperadorMecanicoVerifica94_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMantencion_2.mantTblOperadorMecanicoVerifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuMantencion",
      "mantTblOperadorMecanicoVerifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """mantTblOperadorMecanicoVerifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:160
  private lazy val controllers_MnuReportes_estadosMantCobraArriendo095_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("estadosMantCobraArriendo0/")))
  )
  private lazy val controllers_MnuReportes_estadosMantCobraArriendo095_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.estadosMantCobraArriendo0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "estadosMantCobraArriendo0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """estadosMantCobraArriendo0/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU REPORTES: COBRA ARRIENDO SOBRE ESTADOS (ALZATEC UNICAMENTE)
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:161
  private lazy val controllers_MnuReportes_estadosMantCobraArriendo196_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("estadosMantCobraArriendo1/")))
  )
  private lazy val controllers_MnuReportes_estadosMantCobraArriendo196_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.estadosMantCobraArriendo1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "estadosMantCobraArriendo1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """estadosMantCobraArriendo1/""",
      """""",
      Seq()
    )
  )

  // @LINE:162
  private lazy val controllers_MnuReportes_modCobraArriendoAjax97_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modCobraArriendoAjax/")))
  )
  private lazy val controllers_MnuReportes_modCobraArriendoAjax97_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.modCobraArriendoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "modCobraArriendoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modCobraArriendoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:164
  private lazy val controllers_MnuReportes_proformaEstadoListaPeriodo98_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaEstadoListaPeriodo/")))
  )
  private lazy val controllers_MnuReportes_proformaEstadoListaPeriodo98_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.proformaEstadoListaPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "proformaEstadoListaPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """proformaEstadoListaPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:165
  private lazy val controllers_MnuReportes_proformaEstadoLista99_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaEstadoLista/")))
  )
  private lazy val controllers_MnuReportes_proformaEstadoLista99_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.proformaEstadoLista(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "proformaEstadoLista",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proformaEstadoLista/""",
      """""",
      Seq()
    )
  )

  // @LINE:166
  private lazy val controllers_MnuReportes_proformaEstadoListaGet100_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaEstadoListaGet/"), DynamicPart("d", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("h", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_proformaEstadoListaGet100_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.proformaEstadoListaGet(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "proformaEstadoListaGet",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String]),
      "GET",
      this.prefix + """proformaEstadoListaGet/""" + "$" + """d<[^/]+>,""" + "$" + """h<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:167
  private lazy val controllers_MnuReportes_proformaEstadoElimina101_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaEstadoElimina/")))
  )
  private lazy val controllers_MnuReportes_proformaEstadoElimina101_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.proformaEstadoElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "proformaEstadoElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proformaEstadoElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:169
  private lazy val controllers_MnuReportes_estadosFacturaPeriodo102_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("estadosFacturaPeriodo/")))
  )
  private lazy val controllers_MnuReportes_estadosFacturaPeriodo102_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.estadosFacturaPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "estadosFacturaPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """estadosFacturaPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:170
  private lazy val controllers_MnuReportes_estadosFacturaProyecto103_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("estadosFacturaProyecto/"), DynamicPart("archivoPDF", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_estadosFacturaProyecto103_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.estadosFacturaProyecto(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "estadosFacturaProyecto",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "POST",
      this.prefix + """estadosFacturaProyecto/""" + "$" + """archivoPDF<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:171
  private lazy val controllers_MnuReportes_estadosFacturaProyectoGet104_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("estadosFacturaProyectoGet/"), DynamicPart("d", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("a", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("u", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("e", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("f", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("p", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuReportes_estadosFacturaProyectoGet104_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.estadosFacturaProyectoGet(fakeValue[play.mvc.Http.Request], fakeValue[String], fakeValue[String], fakeValue[Double], fakeValue[Double], fakeValue[Double], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "estadosFacturaProyectoGet",
      Seq(classOf[play.mvc.Http.Request], classOf[String], classOf[String], classOf[Double], classOf[Double], classOf[Double], classOf[String]),
      "GET",
      this.prefix + """estadosFacturaProyectoGet/""" + "$" + """d<[^/]+>,""" + "$" + """a<[^/]+>,""" + "$" + """u<[^/]+>,""" + "$" + """e<[^/]+>,""" + "$" + """f<[^/]+>,""" + "$" + """p<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:173
  private lazy val controllers_MnuReportes_estadosFacturaProyectoExcel105_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("estadosFacturaProyectoExcel/")))
  )
  private lazy val controllers_MnuReportes_estadosFacturaProyectoExcel105_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.estadosFacturaProyectoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "estadosFacturaProyectoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """estadosFacturaProyectoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:174
  private lazy val controllers_MnuReportes_estadosFacturaProyectoDetalle106_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("estadosFacturaProyectoDetalle/")))
  )
  private lazy val controllers_MnuReportes_estadosFacturaProyectoDetalle106_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.estadosFacturaProyectoDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "estadosFacturaProyectoDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """estadosFacturaProyectoDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:175
  private lazy val controllers_MnuReportes_estadosFacturaProyectoDetExcel107_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("estadosFacturaProyectoDetExcel/")))
  )
  private lazy val controllers_MnuReportes_estadosFacturaProyectoDetExcel107_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.estadosFacturaProyectoDetExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "estadosFacturaProyectoDetExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """estadosFacturaProyectoDetExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:177
  private lazy val controllers_MnuReportes_enviarAlistAjustes108_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("enviarAlistAjustes/")))
  )
  private lazy val controllers_MnuReportes_enviarAlistAjustes108_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.enviarAlistAjustes(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "enviarAlistAjustes",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """enviarAlistAjustes/""",
      """""",
      Seq()
    )
  )

  // @LINE:178
  private lazy val controllers_MnuReportes_envioMasivoAlistAjustes109_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("envioMasivoAlistAjustes/")))
  )
  private lazy val controllers_MnuReportes_envioMasivoAlistAjustes109_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.envioMasivoAlistAjustes(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "envioMasivoAlistAjustes",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """envioMasivoAlistAjustes/""",
      """""",
      Seq()
    )
  )

  // @LINE:179
  private lazy val controllers_MnuReportes_proformaEstadoListaPdf110_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaEstadoListaPdf/")))
  )
  private lazy val controllers_MnuReportes_proformaEstadoListaPdf110_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.proformaEstadoListaPdf(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "proformaEstadoListaPdf",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proformaEstadoListaPdf/""",
      """""",
      Seq()
    )
  )

  // @LINE:180
  private lazy val controllers_MnuReportes_proformaEstadoListaExcel111_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaEstadoListaExcel/")))
  )
  private lazy val controllers_MnuReportes_proformaEstadoListaExcel111_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_3.proformaEstadoListaExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes3",
      "controllers.MnuReportes",
      "proformaEstadoListaExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proformaEstadoListaExcel/""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:4
    case controllers_Assets_versioned0_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned0_invoker.call(Assets_0.versioned(path, file))
      }
  
    // @LINE:9
    case controllers_HomeController_viewImg1_route(params@_) =>
      call(params.fromPath[String]("img", None)) { (img) =>
        controllers_HomeController_viewImg1_invoker.call(
          req => HomeController_1.viewImg(img, req))
      }
  
    // @LINE:10
    case controllers_HomeController_showPdf2_route(params@_) =>
      call(params.fromPath[String]("fileNamePdf", None)) { (fileNamePdf) =>
        controllers_HomeController_showPdf2_invoker.call(
          req => HomeController_1.showPdf(fileNamePdf, req))
      }
  
    // @LINE:11
    case controllers_HomeController_showPdfManuales3_route(params@_) =>
      call(params.fromPath[String]("fileNamePdf", None)) { (fileNamePdf) =>
        controllers_HomeController_showPdfManuales3_invoker.call(
          req => HomeController_1.showPdfManuales(fileNamePdf, req))
      }
  
    // @LINE:12
    case controllers_HomeController_downloadPDF4_route(params@_) =>
      call { 
        controllers_HomeController_downloadPDF4_invoker.call(
          req => HomeController_1.downloadPDF(req))
      }
  
    // @LINE:13
    case controllers_HomeController_tasasDeFechaAjax5_route(params@_) =>
      call { 
        controllers_HomeController_tasasDeFechaAjax5_invoker.call(
          req => HomeController_1.tasasDeFechaAjax(req))
      }
  
    // @LINE:14
    case controllers_HomeController_sendEmail6_route(params@_) =>
      call { 
        controllers_HomeController_sendEmail6_invoker.call(
          req => HomeController_1.sendEmail(req))
      }
  
    // @LINE:16
    case controllers_HomeController_redirShowPDF7_route(params@_) =>
      call(params.fromPath[String]("fileNamePdf", None), params.fromPath[String]("titulo", None), params.fromPath[String]("url", None)) { (fileNamePdf, titulo, url) =>
        controllers_HomeController_redirShowPDF7_invoker.call(
          req => HomeController_1.redirShowPDF(fileNamePdf, titulo, url, req))
      }
  
    // @LINE:18
    case controllers_HomeController_redirOdoShowPDF8_route(params@_) =>
      call(params.fromPath[String]("fileNamePdf", None), params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None), params.fromPath[String]("url", None)) { (fileNamePdf, desde, hasta, url) =>
        controllers_HomeController_redirOdoShowPDF8_invoker.call(
          req => HomeController_1.redirOdoShowPDF(fileNamePdf, desde, hasta, url, req))
      }
  
    // @LINE:27
    case controllers_MnuMantencion_mantWebInicio19_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantWebInicio19_invoker.call(
          req => MnuMantencion_2.mantWebInicio1(req))
      }
  
    // @LINE:28
    case controllers_MnuMantencion_mantWebReportNewSave10_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantWebReportNewSave10_invoker.call(
          req => MnuMantencion_2.mantWebReportNewSave(req))
      }
  
    // @LINE:29
    case controllers_MnuMantencion_mantWebInicio1get11_route(params@_) =>
      call(params.fromPath[Long]("id_operMec", None), params.fromPath[Long]("id_equipo", None)) { (id_operMec, id_equipo) =>
        controllers_MnuMantencion_mantWebInicio1get11_invoker.call(
          req => MnuMantencion_2.mantWebInicio1get(req, id_operMec, id_equipo))
      }
  
    // @LINE:31
    case controllers_MnuMantencion_mantWebListarReports012_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantWebListarReports012_invoker.call(
          req => MnuMantencion_2.mantWebListarReports0(req))
      }
  
    // @LINE:32
    case controllers_MnuMantencion_mantWebListarReports113_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantWebListarReports113_invoker.call(
          req => MnuMantencion_2.mantWebListarReports1(req))
      }
  
    // @LINE:33
    case controllers_MnuMantencion_mantWebListarReports1Get14_route(params@_) =>
      call(params.fromPath[String]("de", None), params.fromPath[String]("a", None), params.fromPath[Long]("id_equipo", None)) { (de, a, id_equipo) =>
        controllers_MnuMantencion_mantWebListarReports1Get14_invoker.call(
          req => MnuMantencion_2.mantWebListarReports1Get(req, de, a, id_equipo))
      }
  
    // @LINE:35
    case controllers_MnuMantencion_mantWebListarReports1Excel15_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantWebListarReports1Excel15_invoker.call(
          req => MnuMantencion_2.mantWebListarReports1Excel(req))
      }
  
    // @LINE:36
    case controllers_MnuMantencion_mantWebReportDetalle16_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantWebReportDetalle16_invoker.call(
          req => MnuMantencion_2.mantWebReportDetalle(req))
      }
  
    // @LINE:38
    case controllers_MnuMantencion_mantWebReportGrabaDocAnexo17_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantWebReportGrabaDocAnexo17_invoker.call(
          req => MnuMantencion_2.mantWebReportGrabaDocAnexo(req))
      }
  
    // @LINE:39
    case controllers_MnuMantencion_mantWebFirmaOperador18_route(params@_) =>
      call(params.fromPath[Long]("id_venta", None), params.fromPath[String]("de", None), params.fromPath[String]("a", None), params.fromPath[Long]("id_equipo", None)) { (id_venta, de, a, id_equipo) =>
        controllers_MnuMantencion_mantWebFirmaOperador18_invoker.call(
          req => MnuMantencion_2.mantWebFirmaOperador(req, id_venta, de, a, id_equipo))
      }
  
    // @LINE:40
    case controllers_MnuMantencion_mantWebGrabarFirmaOperador19_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantWebGrabarFirmaOperador19_invoker.call(
          req => MnuMantencion_2.mantWebGrabarFirmaOperador(req))
      }
  
    // @LINE:41
    case controllers_MnuMantencion_mantWebFirmaAutorizador20_route(params@_) =>
      call(params.fromPath[Long]("id_venta", None), params.fromPath[String]("de", None), params.fromPath[String]("a", None), params.fromPath[Long]("id_equipo", None)) { (id_venta, de, a, id_equipo) =>
        controllers_MnuMantencion_mantWebFirmaAutorizador20_invoker.call(
          req => MnuMantencion_2.mantWebFirmaAutorizador(req, id_venta, de, a, id_equipo))
      }
  
    // @LINE:42
    case controllers_MnuMantencion_mantWebGrabarFirmaAutorizador21_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantWebGrabarFirmaAutorizador21_invoker.call(
          req => MnuMantencion_2.mantWebGrabarFirmaAutorizador(req))
      }
  
    // @LINE:50
    case controllers_MnuMantencion_mantReportNew22_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantReportNew22_invoker.call(
          req => MnuMantencion_2.mantReportNew(req))
      }
  
    // @LINE:51
    case controllers_MnuMantencion_mantReportNewSave23_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantReportNewSave23_invoker.call(
          req => MnuMantencion_2.mantReportNewSave(req))
      }
  
    // @LINE:56
    case controllers_MnuMantencion_mantListarReports024_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantListarReports024_invoker.call(
          req => MnuMantencion_2.mantListarReports0(req))
      }
  
    // @LINE:57
    case controllers_MnuMantencion_mantListarReports125_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantListarReports125_invoker.call(
          req => MnuMantencion_2.mantListarReports1(req))
      }
  
    // @LINE:58
    case controllers_MnuMantencion_mantListarReports1Get26_route(params@_) =>
      call(params.fromPath[String]("d", None), params.fromPath[String]("a", None)) { (d, a) =>
        controllers_MnuMantencion_mantListarReports1Get26_invoker.call(
          req => MnuMantencion_2.mantListarReports1Get(req, d, a))
      }
  
    // @LINE:59
    case controllers_MnuMantencion_mantReportDetalle27_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantReportDetalle27_invoker.call(
          req => MnuMantencion_2.mantReportDetalle(req))
      }
  
    // @LINE:60
    case controllers_MnuMantencion_mantListarReports1Excel28_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantListarReports1Excel28_invoker.call(
          req => MnuMantencion_2.mantListarReports1Excel(req))
      }
  
    // @LINE:61
    case controllers_MnuMantencion_mantReportGrabaDocAnexo29_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantReportGrabaDocAnexo29_invoker.call(
          req => MnuMantencion_2.mantReportGrabaDocAnexo(req))
      }
  
    // @LINE:62
    case controllers_MnuMantencion_mantFirmaOperador30_route(params@_) =>
      call(params.fromPath[Long]("id_venta", None), params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None)) { (id_venta, desde, hasta) =>
        controllers_MnuMantencion_mantFirmaOperador30_invoker.call(
          req => MnuMantencion_2.mantFirmaOperador(req, id_venta, desde, hasta))
      }
  
    // @LINE:63
    case controllers_MnuMantencion_mantGrabarFirmaOperador31_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantGrabarFirmaOperador31_invoker.call(
          req => MnuMantencion_2.mantGrabarFirmaOperador(req))
      }
  
    // @LINE:64
    case controllers_MnuMantencion_mantFirmaAutorizador32_route(params@_) =>
      call(params.fromPath[Long]("id_venta", None), params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None)) { (id_venta, desde, hasta) =>
        controllers_MnuMantencion_mantFirmaAutorizador32_invoker.call(
          req => MnuMantencion_2.mantFirmaAutorizador(req, id_venta, desde, hasta))
      }
  
    // @LINE:65
    case controllers_MnuMantencion_mantGrabarFirmaAutorizador33_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantGrabarFirmaAutorizador33_invoker.call(
          req => MnuMantencion_2.mantGrabarFirmaAutorizador(req))
      }
  
    // @LINE:66
    case controllers_MnuMantencion_mantReportElimina34_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantReportElimina34_invoker.call(
          req => MnuMantencion_2.mantReportElimina(req))
      }
  
    // @LINE:71
    case controllers_MnuMantencion_mantHistorialReports035_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantHistorialReports035_invoker.call(
          req => MnuMantencion_2.mantHistorialReports0(req))
      }
  
    // @LINE:72
    case controllers_MnuMantencion_mantHistorialReports136_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantHistorialReports136_invoker.call(
          req => MnuMantencion_2.mantHistorialReports1(req))
      }
  
    // @LINE:73
    case controllers_MnuMantencion_mantHistorialReports1Excel37_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantHistorialReports1Excel37_invoker.call(
          req => MnuMantencion_2.mantHistorialReports1Excel(req))
      }
  
    // @LINE:78
    case controllers_MnuMantencion_mantControlMantenciones38_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantControlMantenciones38_invoker.call(
          req => MnuMantencion_2.mantControlMantenciones(req))
      }
  
    // @LINE:79
    case controllers_MnuMantencion_mantControlMantencionesExcel39_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantControlMantencionesExcel39_invoker.call(
          req => MnuMantencion_2.mantControlMantencionesExcel(req))
      }
  
    // @LINE:84
    case controllers_MnuMantencion_mantCantOperacional040_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantCantOperacional040_invoker.call(
          req => MnuMantencion_2.mantCantOperacional0(req))
      }
  
    // @LINE:85
    case controllers_MnuMantencion_mantCantOperacional141_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantCantOperacional141_invoker.call(
          req => MnuMantencion_2.mantCantOperacional1(req))
      }
  
    // @LINE:86
    case controllers_MnuMantencion_mantCantOperacional1Excel42_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantCantOperacional1Excel42_invoker.call(
          req => MnuMantencion_2.mantCantOperacional1Excel(req))
      }
  
    // @LINE:88
    case controllers_MnuMantencion_mantCantMantencion043_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantCantMantencion043_invoker.call(
          req => MnuMantencion_2.mantCantMantencion0(req))
      }
  
    // @LINE:89
    case controllers_MnuMantencion_mantCantMantencion144_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantCantMantencion144_invoker.call(
          req => MnuMantencion_2.mantCantMantencion1(req))
      }
  
    // @LINE:90
    case controllers_MnuMantencion_mantCantMantencion1Excel45_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantCantMantencion1Excel45_invoker.call(
          req => MnuMantencion_2.mantCantMantencion1Excel(req))
      }
  
    // @LINE:98
    case controllers_MnuMantencion_mantTblItemMant46_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblItemMant46_invoker.call(
          req => MnuMantencion_2.mantTblItemMant(req))
      }
  
    // @LINE:99
    case controllers_MnuMantencion_mantTblItemNew47_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblItemNew47_invoker.call(
          req => MnuMantencion_2.mantTblItemNew(req))
      }
  
    // @LINE:100
    case controllers_MnuMantencion_mantTblItemNewSave48_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblItemNewSave48_invoker.call(
          req => MnuMantencion_2.mantTblItemNewSave(req))
      }
  
    // @LINE:101
    case controllers_MnuMantencion_mantTblItemUpdate49_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblItemUpdate49_invoker.call(
          req => MnuMantencion_2.mantTblItemUpdate(req))
      }
  
    // @LINE:102
    case controllers_MnuMantencion_modificaItemPorCampoAjax50_route(params@_) =>
      call { 
        controllers_MnuMantencion_modificaItemPorCampoAjax50_invoker.call(
          req => MnuMantencion_2.modificaItemPorCampoAjax(req))
      }
  
    // @LINE:103
    case controllers_MnuMantencion_mantTblItemDel51_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblItemDel51_invoker.call(
          req => MnuMantencion_2.mantTblItemDel(req))
      }
  
    // @LINE:105
    case controllers_MnuMantencion_mantTblActividadMant52_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblActividadMant52_invoker.call(
          req => MnuMantencion_2.mantTblActividadMant(req))
      }
  
    // @LINE:106
    case controllers_MnuMantencion_mantTblActividadNew53_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblActividadNew53_invoker.call(
          req => MnuMantencion_2.mantTblActividadNew(req))
      }
  
    // @LINE:107
    case controllers_MnuMantencion_mantTblActividadNewSave54_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblActividadNewSave54_invoker.call(
          req => MnuMantencion_2.mantTblActividadNewSave(req))
      }
  
    // @LINE:108
    case controllers_MnuMantencion_mantTblActividadUpdate55_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblActividadUpdate55_invoker.call(
          req => MnuMantencion_2.mantTblActividadUpdate(req))
      }
  
    // @LINE:109
    case controllers_MnuMantencion_modificaActividadPorCampoAjax56_route(params@_) =>
      call { 
        controllers_MnuMantencion_modificaActividadPorCampoAjax56_invoker.call(
          req => MnuMantencion_2.modificaActividadPorCampoAjax(req))
      }
  
    // @LINE:110
    case controllers_MnuMantencion_mantTblActividadDel57_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblActividadDel57_invoker.call(
          req => MnuMantencion_2.mantTblActividadDel(req))
      }
  
    // @LINE:112
    case controllers_MnuMantencion_mantTblTipoActividadMant58_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblTipoActividadMant58_invoker.call(
          req => MnuMantencion_2.mantTblTipoActividadMant(req))
      }
  
    // @LINE:113
    case controllers_MnuMantencion_mantTblTipoActividadNew59_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblTipoActividadNew59_invoker.call(
          req => MnuMantencion_2.mantTblTipoActividadNew(req))
      }
  
    // @LINE:114
    case controllers_MnuMantencion_mantTblTipoActividadNewSave60_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblTipoActividadNewSave60_invoker.call(
          req => MnuMantencion_2.mantTblTipoActividadNewSave(req))
      }
  
    // @LINE:115
    case controllers_MnuMantencion_mantTblTipoActividadUpdate61_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblTipoActividadUpdate61_invoker.call(
          req => MnuMantencion_2.mantTblTipoActividadUpdate(req))
      }
  
    // @LINE:116
    case controllers_MnuMantencion_modificaTipoActividadPorCampoAjax62_route(params@_) =>
      call { 
        controllers_MnuMantencion_modificaTipoActividadPorCampoAjax62_invoker.call(
          req => MnuMantencion_2.modificaTipoActividadPorCampoAjax(req))
      }
  
    // @LINE:117
    case controllers_MnuMantencion_mantTblTipoActividadDel63_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblTipoActividadDel63_invoker.call(
          req => MnuMantencion_2.mantTblTipoActividadDel(req))
      }
  
    // @LINE:119
    case controllers_MnuMantencion_mantTblComponenteMant64_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblComponenteMant64_invoker.call(
          req => MnuMantencion_2.mantTblComponenteMant(req))
      }
  
    // @LINE:120
    case controllers_MnuMantencion_mantTblComponenteNew65_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblComponenteNew65_invoker.call(
          req => MnuMantencion_2.mantTblComponenteNew(req))
      }
  
    // @LINE:121
    case controllers_MnuMantencion_mantTblComponenteNewSave66_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblComponenteNewSave66_invoker.call(
          req => MnuMantencion_2.mantTblComponenteNewSave(req))
      }
  
    // @LINE:122
    case controllers_MnuMantencion_mantTblComponenteUpdate67_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblComponenteUpdate67_invoker.call(
          req => MnuMantencion_2.mantTblComponenteUpdate(req))
      }
  
    // @LINE:123
    case controllers_MnuMantencion_modificaComponentePorCampoAjax68_route(params@_) =>
      call { 
        controllers_MnuMantencion_modificaComponentePorCampoAjax68_invoker.call(
          req => MnuMantencion_2.modificaComponentePorCampoAjax(req))
      }
  
    // @LINE:124
    case controllers_MnuMantencion_mantTblComponenteDel69_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblComponenteDel69_invoker.call(
          req => MnuMantencion_2.mantTblComponenteDel(req))
      }
  
    // @LINE:126
    case controllers_MnuMantencion_mantTblEstadoEnObraMant70_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoEnObraMant70_invoker.call(
          req => MnuMantencion_2.mantTblEstadoEnObraMant(req))
      }
  
    // @LINE:127
    case controllers_MnuMantencion_mantTblEstadoEnObraNew71_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoEnObraNew71_invoker.call(
          req => MnuMantencion_2.mantTblEstadoEnObraNew(req))
      }
  
    // @LINE:128
    case controllers_MnuMantencion_mantTblEstadoEnObraNewSave72_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoEnObraNewSave72_invoker.call(
          req => MnuMantencion_2.mantTblEstadoEnObraNewSave(req))
      }
  
    // @LINE:129
    case controllers_MnuMantencion_mantTblEstadoEnObraUpdate73_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoEnObraUpdate73_invoker.call(
          req => MnuMantencion_2.mantTblEstadoEnObraUpdate(req))
      }
  
    // @LINE:130
    case controllers_MnuMantencion_modificaEstadoEnObraPorCampoAjax74_route(params@_) =>
      call { 
        controllers_MnuMantencion_modificaEstadoEnObraPorCampoAjax74_invoker.call(
          req => MnuMantencion_2.modificaEstadoEnObraPorCampoAjax(req))
      }
  
    // @LINE:131
    case controllers_MnuMantencion_mantTblEstadoEnObraDel75_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoEnObraDel75_invoker.call(
          req => MnuMantencion_2.mantTblEstadoEnObraDel(req))
      }
  
    // @LINE:133
    case controllers_MnuMantencion_mantTblEstadoEnTallerMant76_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoEnTallerMant76_invoker.call(
          req => MnuMantencion_2.mantTblEstadoEnTallerMant(req))
      }
  
    // @LINE:134
    case controllers_MnuMantencion_mantTblEstadoEnTallerNew77_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoEnTallerNew77_invoker.call(
          req => MnuMantencion_2.mantTblEstadoEnTallerNew(req))
      }
  
    // @LINE:135
    case controllers_MnuMantencion_mantTblEstadoEnTallerNewSave78_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoEnTallerNewSave78_invoker.call(
          req => MnuMantencion_2.mantTblEstadoEnTallerNewSave(req))
      }
  
    // @LINE:136
    case controllers_MnuMantencion_mantTblEstadoEnTallerUpdate79_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoEnTallerUpdate79_invoker.call(
          req => MnuMantencion_2.mantTblEstadoEnTallerUpdate(req))
      }
  
    // @LINE:137
    case controllers_MnuMantencion_modificaEstadoEnTallerPorCampoAjax80_route(params@_) =>
      call { 
        controllers_MnuMantencion_modificaEstadoEnTallerPorCampoAjax80_invoker.call(
          req => MnuMantencion_2.modificaEstadoEnTallerPorCampoAjax(req))
      }
  
    // @LINE:138
    case controllers_MnuMantencion_mantTblEstadoEnTallerDel81_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoEnTallerDel81_invoker.call(
          req => MnuMantencion_2.mantTblEstadoEnTallerDel(req))
      }
  
    // @LINE:140
    case controllers_MnuMantencion_mantTblEstadoOperacionalMant82_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoOperacionalMant82_invoker.call(
          req => MnuMantencion_2.mantTblEstadoOperacionalMant(req))
      }
  
    // @LINE:141
    case controllers_MnuMantencion_mantTblEstadoOperacionalNew83_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoOperacionalNew83_invoker.call(
          req => MnuMantencion_2.mantTblEstadoOperacionalNew(req))
      }
  
    // @LINE:142
    case controllers_MnuMantencion_mantTblEstadoOperacionalNewSave84_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoOperacionalNewSave84_invoker.call(
          req => MnuMantencion_2.mantTblEstadoOperacionalNewSave(req))
      }
  
    // @LINE:143
    case controllers_MnuMantencion_mantTblEstadoOperacionalUpdate85_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoOperacionalUpdate85_invoker.call(
          req => MnuMantencion_2.mantTblEstadoOperacionalUpdate(req))
      }
  
    // @LINE:144
    case controllers_MnuMantencion_modificaEstadoOperacionalPorCampoAjax86_route(params@_) =>
      call { 
        controllers_MnuMantencion_modificaEstadoOperacionalPorCampoAjax86_invoker.call(
          req => MnuMantencion_2.modificaEstadoOperacionalPorCampoAjax(req))
      }
  
    // @LINE:145
    case controllers_MnuMantencion_mantTblEstadoOperacionalDel87_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblEstadoOperacionalDel87_invoker.call(
          req => MnuMantencion_2.mantTblEstadoOperacionalDel(req))
      }
  
    // @LINE:147
    case controllers_MnuMantencion_mantTblOperadorMecanicoMant88_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblOperadorMecanicoMant88_invoker.call(
          req => MnuMantencion_2.mantTblOperadorMecanicoMant(req))
      }
  
    // @LINE:148
    case controllers_MnuMantencion_mantTblOperadorMecanicoNew89_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblOperadorMecanicoNew89_invoker.call(
          req => MnuMantencion_2.mantTblOperadorMecanicoNew(req))
      }
  
    // @LINE:149
    case controllers_MnuMantencion_mantTblOperadorMecanicoNewSave90_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblOperadorMecanicoNewSave90_invoker.call(
          req => MnuMantencion_2.mantTblOperadorMecanicoNewSave(req))
      }
  
    // @LINE:150
    case controllers_MnuMantencion_mantTblOperadorMecanicoUpdate91_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblOperadorMecanicoUpdate91_invoker.call(
          req => MnuMantencion_2.mantTblOperadorMecanicoUpdate(req))
      }
  
    // @LINE:151
    case controllers_MnuMantencion_modificaOperadorMecanicoPorCampoAjax92_route(params@_) =>
      call { 
        controllers_MnuMantencion_modificaOperadorMecanicoPorCampoAjax92_invoker.call(
          req => MnuMantencion_2.modificaOperadorMecanicoPorCampoAjax(req))
      }
  
    // @LINE:152
    case controllers_MnuMantencion_mantTblOperadorMecanicoVig93_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblOperadorMecanicoVig93_invoker.call(
          req => MnuMantencion_2.mantTblOperadorMecanicoVig(req))
      }
  
    // @LINE:153
    case controllers_MnuMantencion_mantTblOperadorMecanicoVerifica94_route(params@_) =>
      call { 
        controllers_MnuMantencion_mantTblOperadorMecanicoVerifica94_invoker.call(
          req => MnuMantencion_2.mantTblOperadorMecanicoVerifica(req))
      }
  
    // @LINE:160
    case controllers_MnuReportes_estadosMantCobraArriendo095_route(params@_) =>
      call { 
        controllers_MnuReportes_estadosMantCobraArriendo095_invoker.call(
          req => MnuReportes_3.estadosMantCobraArriendo0(req))
      }
  
    // @LINE:161
    case controllers_MnuReportes_estadosMantCobraArriendo196_route(params@_) =>
      call { 
        controllers_MnuReportes_estadosMantCobraArriendo196_invoker.call(
          req => MnuReportes_3.estadosMantCobraArriendo1(req))
      }
  
    // @LINE:162
    case controllers_MnuReportes_modCobraArriendoAjax97_route(params@_) =>
      call { 
        controllers_MnuReportes_modCobraArriendoAjax97_invoker.call(
          req => MnuReportes_3.modCobraArriendoAjax(req))
      }
  
    // @LINE:164
    case controllers_MnuReportes_proformaEstadoListaPeriodo98_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaEstadoListaPeriodo98_invoker.call(
          req => MnuReportes_3.proformaEstadoListaPeriodo(req))
      }
  
    // @LINE:165
    case controllers_MnuReportes_proformaEstadoLista99_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaEstadoLista99_invoker.call(
          req => MnuReportes_3.proformaEstadoLista(req))
      }
  
    // @LINE:166
    case controllers_MnuReportes_proformaEstadoListaGet100_route(params@_) =>
      call(params.fromPath[String]("d", None), params.fromPath[String]("h", None)) { (d, h) =>
        controllers_MnuReportes_proformaEstadoListaGet100_invoker.call(
          req => MnuReportes_3.proformaEstadoListaGet(req, d, h))
      }
  
    // @LINE:167
    case controllers_MnuReportes_proformaEstadoElimina101_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaEstadoElimina101_invoker.call(
          req => MnuReportes_3.proformaEstadoElimina(req))
      }
  
    // @LINE:169
    case controllers_MnuReportes_estadosFacturaPeriodo102_route(params@_) =>
      call { 
        controllers_MnuReportes_estadosFacturaPeriodo102_invoker.call(
          req => MnuReportes_3.estadosFacturaPeriodo(req))
      }
  
    // @LINE:170
    case controllers_MnuReportes_estadosFacturaProyecto103_route(params@_) =>
      call(params.fromPath[String]("archivoPDF", None)) { (archivoPDF) =>
        controllers_MnuReportes_estadosFacturaProyecto103_invoker.call(
          req => MnuReportes_3.estadosFacturaProyecto(req, archivoPDF))
      }
  
    // @LINE:171
    case controllers_MnuReportes_estadosFacturaProyectoGet104_route(params@_) =>
      call(params.fromPath[String]("d", None), params.fromPath[String]("a", None), params.fromPath[Double]("u", None), params.fromPath[Double]("e", None), params.fromPath[Double]("f", None), params.fromPath[String]("p", None)) { (d, a, u, e, f, p) =>
        controllers_MnuReportes_estadosFacturaProyectoGet104_invoker.call(
          req => MnuReportes_3.estadosFacturaProyectoGet(req, d, a, u, e, f, p))
      }
  
    // @LINE:173
    case controllers_MnuReportes_estadosFacturaProyectoExcel105_route(params@_) =>
      call { 
        controllers_MnuReportes_estadosFacturaProyectoExcel105_invoker.call(
          req => MnuReportes_3.estadosFacturaProyectoExcel(req))
      }
  
    // @LINE:174
    case controllers_MnuReportes_estadosFacturaProyectoDetalle106_route(params@_) =>
      call { 
        controllers_MnuReportes_estadosFacturaProyectoDetalle106_invoker.call(
          req => MnuReportes_3.estadosFacturaProyectoDetalle(req))
      }
  
    // @LINE:175
    case controllers_MnuReportes_estadosFacturaProyectoDetExcel107_route(params@_) =>
      call { 
        controllers_MnuReportes_estadosFacturaProyectoDetExcel107_invoker.call(
          req => MnuReportes_3.estadosFacturaProyectoDetExcel(req))
      }
  
    // @LINE:177
    case controllers_MnuReportes_enviarAlistAjustes108_route(params@_) =>
      call { 
        controllers_MnuReportes_enviarAlistAjustes108_invoker.call(
          req => MnuReportes_3.enviarAlistAjustes(req))
      }
  
    // @LINE:178
    case controllers_MnuReportes_envioMasivoAlistAjustes109_route(params@_) =>
      call { 
        controllers_MnuReportes_envioMasivoAlistAjustes109_invoker.call(
          req => MnuReportes_3.envioMasivoAlistAjustes(req))
      }
  
    // @LINE:179
    case controllers_MnuReportes_proformaEstadoListaPdf110_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaEstadoListaPdf110_invoker.call(
          req => MnuReportes_3.proformaEstadoListaPdf(req))
      }
  
    // @LINE:180
    case controllers_MnuReportes_proformaEstadoListaExcel111_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaEstadoListaExcel111_invoker.call(
          req => MnuReportes_3.proformaEstadoListaExcel(req))
      }
  }
}
