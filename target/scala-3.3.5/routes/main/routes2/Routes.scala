// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes2.routes

package routes2

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:4
  Assets_10: controllers.Assets,
  // @LINE:13
  HomeController_11: controllers.HomeController,
  // @LINE:30
  MnuRedimensionar_1: controllers.MnuRedimensionar,
  // @LINE:52
  MnuMovimientos_8: controllers.MnuMovimientos,
  // @LINE:60
  MnuCotiOdo_5: controllers.MnuCotiOdo,
  // @LINE:129
  MnuCotizar_3: controllers.MnuCotizar,
  // @LINE:161
  MnuTablas_2: controllers.MnuTablas,
  // @LINE:193
  MnuBodegas_12: controllers.MnuBodegas,
  // @LINE:203
  MnuCompras_4: controllers.MnuCompras,
  // @LINE:227
  MnuReportes_6: controllers.MnuReportes,
  // @LINE:247
  MnuCotizarSelect_7: controllers.MnuCotizarSelect,
  // @LINE:314
  MnuOdoAppWeb_0: controllers.MnuOdoAppWeb,
  // @LINE:321
  MnuOdo_9: controllers.MnuOdo,
  val prefix: String
) extends GeneratedRouter {

  @javax.inject.Inject()
  def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:4
    Assets_10: controllers.Assets,
    // @LINE:13
    HomeController_11: controllers.HomeController,
    // @LINE:30
    MnuRedimensionar_1: controllers.MnuRedimensionar,
    // @LINE:52
    MnuMovimientos_8: controllers.MnuMovimientos,
    // @LINE:60
    MnuCotiOdo_5: controllers.MnuCotiOdo,
    // @LINE:129
    MnuCotizar_3: controllers.MnuCotizar,
    // @LINE:161
    MnuTablas_2: controllers.MnuTablas,
    // @LINE:193
    MnuBodegas_12: controllers.MnuBodegas,
    // @LINE:203
    MnuCompras_4: controllers.MnuCompras,
    // @LINE:227
    MnuReportes_6: controllers.MnuReportes,
    // @LINE:247
    MnuCotizarSelect_7: controllers.MnuCotizarSelect,
    // @LINE:314
    MnuOdoAppWeb_0: controllers.MnuOdoAppWeb,
    // @LINE:321
    MnuOdo_9: controllers.MnuOdo
  ) = this(errorHandler, Assets_10, HomeController_11, MnuRedimensionar_1, MnuMovimientos_8, MnuCotiOdo_5, MnuCotizar_3, MnuTablas_2, MnuBodegas_12, MnuCompras_4, MnuReportes_6, MnuCotizarSelect_7, MnuOdoAppWeb_0, MnuOdo_9, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    routes2.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Assets_10, HomeController_11, MnuRedimensionar_1, MnuMovimientos_8, MnuCotiOdo_5, MnuCotizar_3, MnuTablas_2, MnuBodegas_12, MnuCompras_4, MnuReportes_6, MnuCotizarSelect_7, MnuOdoAppWeb_0, MnuOdo_9, prefix)
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
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redimensionarAsignaBodega/""", """controllers.MnuRedimensionar.redimensionarAsignaBodega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redimensionarAsignaBodegaUpdate/""", """controllers.MnuRedimensionar.redimensionarAsignaBodegaUpdate(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redimensionarPrepara/""", """controllers.MnuRedimensionar.redimensionarPrepara(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redimensionarNuevo/""", """controllers.MnuRedimensionar.redimensionarNuevo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redimensionarConfirmar0/""", """controllers.MnuRedimensionar.redimensionarConfirmar0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redimensionarConfirmar1/""", """controllers.MnuRedimensionar.redimensionarConfirmar1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redimensionarConfirmar2/""", """controllers.MnuRedimensionar.redimensionarConfirmar2(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redimensionarEliminar0/""", """controllers.MnuRedimensionar.redimensionarEliminar0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redimensionarEliminar1/""", """controllers.MnuRedimensionar.redimensionarEliminar1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redimensionarEliminar2/""", """controllers.MnuRedimensionar.redimensionarEliminar2(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redimensionarListar0/""", """controllers.MnuRedimensionar.redimensionarListar0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """redimensionarListar1/""", """controllers.MnuRedimensionar.redimensionarListar1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movValySubePlantilla/""", """controllers.MnuMovimientos.movValySubePlantilla(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movPlantilla/""", """controllers.MnuMovimientos.movPlantilla(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoModifyXCampo/""", """controllers.MnuCotiOdo.cotiOdoModifyXCampo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoIngreso/""", """controllers.MnuCotiOdo.cotiOdoIngreso(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoNuevo/""", """controllers.MnuCotiOdo.cotiOdoNuevo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """existeNumeroCotiOdoAjax/""", """controllers.MnuCotiOdo.existeNumeroCotiOdoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoModificaJson/""", """controllers.MnuCotiOdo.cotiOdoModificaJson(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoListaModifica/""", """controllers.MnuCotiOdo.cotiOdoListaModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoElimina/""", """controllers.MnuCotiOdo.cotiOdoElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoModifica/""", """controllers.MnuCotiOdo.cotiOdoModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoUpdate/""", """controllers.MnuCotiOdo.cotiOdoUpdate(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verCotiOdoAjax/""", """controllers.MnuCotiOdo.verCotiOdoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoListaCambiaEstado/""", """controllers.MnuCotiOdo.cotiOdoListaCambiaEstado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cambiarCotiOdoEstadoAjax/""", """controllers.MnuCotiOdo.cambiarCotiOdoEstadoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoListaImprimir/""", """controllers.MnuCotiOdo.cotiOdoListaImprimir(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoImprimir/""" + "$" + """id_cotiOdo<[^/]+>""", """controllers.MnuCotiOdo.cotiOdoImprimir(request:Request, id_cotiOdo:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoExcel/""", """controllers.MnuCotiOdo.cotiOdoExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """pdfCotiOdoVta/""", """controllers.MnuCotiOdo.pdfCotiOdoVta(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportCotiOdoSel/""", """controllers.MnuCotiOdo.reportCotiOdoSel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportCotiOdoRpt/""", """controllers.MnuCotiOdo.reportCotiOdoRpt(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoListaConfirma/""", """controllers.MnuCotiOdo.cotiOdoListaConfirma(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoConfirma/""", """controllers.MnuCotiOdo.cotiOdoConfirma(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otOdoListaCotizaSinOt/""", """controllers.MnuCotiOdo.otOdoListaCotizaSinOt(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otOdoHacer/""", """controllers.MnuCotiOdo.otOdoHacer(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otOdoGrabar/""", """controllers.MnuCotiOdo.otOdoGrabar(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otOdoListaEliminar/""", """controllers.MnuCotiOdo.otOdoListaEliminar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """verOtOdoAjax/""", """controllers.MnuCotiOdo.verOtOdoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualFechaEnvioOtOdoAjax/""", """controllers.MnuCotiOdo.actualFechaEnvioOtOdoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualOperServOtOdoAjax/""", """controllers.MnuCotiOdo.actualOperServOtOdoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otOdoElimina/""", """controllers.MnuCotiOdo.otOdoElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otOdoListaCambiarEstado/""", """controllers.MnuCotiOdo.otOdoListaCambiarEstado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cambiarOtOdoEstadoAjax/""", """controllers.MnuCotiOdo.cambiarOtOdoEstadoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otOdoListaRevisar/""", """controllers.MnuCotiOdo.otOdoListaRevisar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """revisarOtOdo/""", """controllers.MnuCotiOdo.revisarOtOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """trazaServicioOtOdoAjax/""", """controllers.MnuCotiOdo.trazaServicioOtOdoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otOdoRevisarExcel/""", """controllers.MnuCotiOdo.otOdoRevisarExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOtOdoSel/""", """controllers.MnuCotiOdo.reportOtOdoSel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOtOdoRpt/""", """controllers.MnuCotiOdo.reportOtOdoRpt(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otOdoListaConfirma/""", """controllers.MnuCotiOdo.otOdoListaConfirma(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otOdoConfirma/""", """controllers.MnuCotiOdo.otOdoConfirma(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otOdoListaAgregarOC/""" + "$" + """year<[^/]+>""", """controllers.MnuCotiOdo.otOdoListaAgregarOC(request:Request, year:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabarOcOdoPdf/""" + "$" + """id_otOdo<[^/]+>""", """controllers.MnuCotiOdo.grabarOcOdoPdf(request:Request, id_otOdo:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """oc_cotiOdoClienteAjax/""", """controllers.MnuCotiOdo.oc_cotiOdoClienteAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otOdoGenerarContrato/""" + "$" + """year<[^/]+>""", """controllers.MnuCotiOdo.otOdoGenerarContrato(request:Request, year:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabarContratoPdfOdo/""" + "$" + """id_cotiOdo<[^/]+>""", """controllers.MnuCotiOdo.grabarContratoPdfOdo(request:Request, id_cotiOdo:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """datosContratoOdo/""", """controllers.MnuCotiOdo.datosContratoOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hacerContratoOdo/""", """controllers.MnuCotiOdo.hacerContratoOdo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoPlantilla/""", """controllers.MnuCotiOdo.cotiOdoPlantilla(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiOdoValidarPlantilla/""", """controllers.MnuCotiOdo.cotiOdoValidarPlantilla(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOtSaldos/""", """controllers.MnuCotizar.reportOtSaldos(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOtSaldosExcel/""", """controllers.MnuCotizar.reportOtSaldosExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """listadoOTAjax/""", """controllers.MnuCotizar.listadoOTAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOtSaldosPorOt/""", """controllers.MnuCotizar.reportOtSaldosPorOt(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOtSaldosPorOtExcel/""", """controllers.MnuCotizar.reportOtSaldosPorOtExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otAdminPrecios0/""", """controllers.MnuCotizar.otAdminPrecios0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otAdminPrecios1/""", """controllers.MnuCotizar.otAdminPrecios1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otCambiarEstadoTerminadas/""", """controllers.MnuCotizar.otCambiarEstadoTerminadas(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaResumen0/""", """controllers.MnuCotizar.cotizaListaResumen0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaResumen00/""", """controllers.MnuCotizar.cotizaListaResumen00(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaResumen1/""", """controllers.MnuCotizar.cotizaListaResumen1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaResumen2/""", """controllers.MnuCotizar.cotizaListaResumen2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaResumen2Excel/""", """controllers.MnuCotizar.cotizaListaResumen2Excel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaResumen3/""", """controllers.MnuCotizar.cotizaListaResumen3(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaBiblioteca/""", """controllers.MnuCotizar.cotizaListaBiblioteca(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiBibliotecaElimina/""", """controllers.MnuCotizar.cotiBibliotecaElimina(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOtPrecios0/""", """controllers.MnuCotizar.reportOtPrecios0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOtPrecios1/""", """controllers.MnuCotizar.reportOtPrecios1(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """comercialMantencion/""", """controllers.MnuTablas.comercialMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modVigComercialAjax/""", """controllers.MnuTablas.modVigComercialAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """comercialAgrega/""", """controllers.MnuTablas.comercialAgrega(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """precioMantSelSucursal/""", """controllers.MnuTablas.precioMantSelSucursal(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """equipoMantencionExcel/""", """controllers.MnuTablas.equipoMantencionExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """dibujanteMantencion/""", """controllers.MnuTablas.dibujanteMantencion(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """dibujanteAgrega/""", """controllers.MnuTablas.dibujanteAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """dibujanteGraba/""", """controllers.MnuTablas.dibujanteGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """propiedadElimina/""", """controllers.MnuTablas.propiedadElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """dibujanteModifica/""", """controllers.MnuTablas.dibujanteModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaDibujantePorCampoAjax/""", """controllers.MnuTablas.modificaDibujantePorCampoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """propiedadMantencion/""", """controllers.MnuTablas.propiedadMantencion(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """propiedadAgrega/""", """controllers.MnuTablas.propiedadAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """propiedadGraba/""", """controllers.MnuTablas.propiedadGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """propiedadModifica/""", """controllers.MnuTablas.propiedadModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaPropiedadPorCampoAjax/""", """controllers.MnuTablas.modificaPropiedadPorCampoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """rubroMantencion/""", """controllers.MnuBodegas.rubroMantencion(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """rubroAgrega/""", """controllers.MnuBodegas.rubroAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """rubroGraba/""", """controllers.MnuBodegas.rubroGraba(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """rubroModifica/""", """controllers.MnuBodegas.rubroModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaRubroPorCampoAjax/""", """controllers.MnuBodegas.modificaRubroPorCampoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movCompras0/""", """controllers.MnuCompras.movCompras0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movCompras1/""", """controllers.MnuCompras.movCompras1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movCompras1Excel/""", """controllers.MnuCompras.movCompras1Excel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraVistaSelBodDestAjax/""", """controllers.MnuCompras.compraVistaSelBodDestAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraVistaSelBodDestAjax2/""", """controllers.MnuCompras.compraVistaSelBodDestAjax2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraPlantilla/""", """controllers.MnuCompras.compraPlantilla(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraCargaPlantilla/""", """controllers.MnuCompras.compraCargaPlantilla(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """compraValidarPlantilla/""", """controllers.MnuCompras.compraValidarPlantilla(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """precioPlantilla/""", """controllers.MnuTablas.precioPlantilla(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """precioCargaPlantilla/""", """controllers.MnuTablas.precioCargaPlantilla(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteExcedentesListaEquipos/""", """controllers.MnuReportes.reporteExcedentesListaEquipos(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteExcedentesDetalleEquipo/""", """controllers.MnuReportes.reporteExcedentesDetalleEquipo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteExcedentesDetalleEquipoExcel/""", """controllers.MnuReportes.reporteExcedentesDetalleEquipoExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proyectoMantencionExcel/""", """controllers.MnuTablas.proyectoMantencionExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """equipoCambiaEstado/""", """controllers.MnuTablas.equipoCambiaEstado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """equipoCambiaPropiedad/""", """controllers.MnuTablas.equipoCambiaPropiedad(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """homeOdoAutorizaVentasWebExcel/""", """controllers.HomeController.homeOdoAutorizaVentasWebExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """coti8columnas/""", """controllers.MnuCotizar.coti8columnas(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """coti8columnasValida/""", """controllers.MnuCotizar.coti8columnasValida(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaIngresoSelect/""", """controllers.MnuCotizarSelect.cotizaIngresoSelect(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaSeleccionados/""", """controllers.MnuCotizarSelect.cotizaSeleccionados(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotiValidarPlantillaSel/""", """controllers.MnuCotizarSelect.cotiValidarPlantillaSel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaModificaJsonSel/""", """controllers.MnuCotizarSelect.cotizaModificaJsonSel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """actualizaListaCotiSelAjax/""", """controllers.MnuCotizarSelect.actualizaListaCotiSelAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaModificaSelPeriodo/""", """controllers.MnuCotizarSelect.cotizaListaModificaSelPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaListaModificaSel/""", """controllers.MnuCotizarSelect.cotizaListaModificaSel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaModificaSel/""", """controllers.MnuCotizarSelect.cotizaModificaSel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportPipelineSelRes/""", """controllers.MnuCotizar.reportPipelineSelRes(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportPipelineRptRes/""", """controllers.MnuCotizar.reportPipelineRptRes(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportPipelineDetalle/""", """controllers.MnuCotizar.reportPipelineDetalle(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportPipelineRptResExcel/""", """controllers.MnuCotizar.reportPipelineRptResExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportPipelineSelDet/""", """controllers.MnuCotizar.reportPipelineSelDet(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportPipelineRptDet/""", """controllers.MnuCotizar.reportPipelineRptDet(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportPipelineRptDetExcel/""", """controllers.MnuCotizar.reportPipelineRptDetExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportPipelineSelComercial/""", """controllers.MnuCotizar.reportPipelineSelComercial(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportPipelineRptComercial/""", """controllers.MnuCotizar.reportPipelineRptComercial(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportPipelineRptComercialExcel/""", """controllers.MnuCotizar.reportPipelineRptComercialExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListarDespachosPeriodo/""", """controllers.MnuCotizar.otListarDespachosPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListarDespachos/""", """controllers.MnuCotizar.otListarDespachos(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otDetalleDespacho/""", """controllers.MnuCotizar.otDetalleDespacho(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListarDespachosImgPeriodo/""", """controllers.MnuCotizar.otListarDespachosImgPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListarDespachosImg/""", """controllers.MnuCotizar.otListarDespachosImg(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otDetalleDespachoImg/""", """controllers.MnuCotizar.otDetalleDespachoImg(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaRevisarPeriodo/""", """controllers.MnuCotizar.otListaRevisarPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaRevisar/""", """controllers.MnuCotizar.otListaRevisar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaRevisarExcel/""", """controllers.MnuCotizar.otListaRevisarExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """revisarOt/""", """controllers.MnuCotizar.revisarOt(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """trazaEqOtAjax/""", """controllers.MnuCotizar.trazaEqOtAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otRevisarExcel/""", """controllers.MnuCotizar.otRevisarExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOtSel/""", """controllers.MnuCotizar.reportOtSel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportOtRpt/""", """controllers.MnuCotizar.reportOtRpt(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otGenerarContratoPeriodo/""", """controllers.MnuCotizar.otGenerarContratoPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otGenerarContrato/""", """controllers.MnuCotizar.otGenerarContrato(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabarContratoPdf/""" + "$" + """id_coti<[^/]+>""", """controllers.MnuCotizar.grabarContratoPdf(request:Request, id_coti:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """datosContrato/""", """controllers.MnuCotizar.datosContrato(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hacerContrato/""", """controllers.MnuCotizar.hacerContrato(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaAgregarOCPeriodo/""", """controllers.MnuCotizar.otListaAgregarOCPeriodo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """otListaAgregarOC/""", """controllers.MnuCotizar.otListaAgregarOC(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """grabarOcPdf/""" + "$" + """id_ot<[^/]+>,""" + "$" + """de<[^/]+>,""" + "$" + """a<[^/]+>""", """controllers.MnuCotizar.grabarOcPdf(request:Request, id_ot:Long, de:String, a:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """oc_cotiClienteAjax/""", """controllers.MnuCotizar.oc_cotiClienteAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaSolucionMantencion/""", """controllers.MnuCotizar.cotizaSolucionMantencion(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaSolucionElimina/""", """controllers.MnuCotizar.cotizaSolucionElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaSolucionModifica/""", """controllers.MnuCotizar.cotizaSolucionModifica(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaCotizaSolucionPorCampoAjax/""", """controllers.MnuCotizar.modificaCotizaSolucionPorCampoAjax(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaSolucionAgrega/""", """controllers.MnuCotizar.cotizaSolucionAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """cotizaSolucionNuevo/""", """controllers.MnuCotizar.cotizaSolucionNuevo(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovOdoAutorizador/""", """controllers.MnuOdoAppWeb.reporteMovOdoAutorizador(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteMovOdoAutorizadorDetalle/""", """controllers.MnuOdoAppWeb.reporteMovOdoAutorizadorDetalle(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioPreciosServicio/""", """controllers.MnuOdo.servicioPreciosServicio(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioListaPreciosServicio/""" + "$" + """id_serv<[^/]+>""", """controllers.MnuOdo.servicioListaPreciosServicio(id_serv:Long, request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """servicioAgregaPrecioServicio/""", """controllers.MnuOdo.servicioAgregaPrecioServicio(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportePorProyectoAgrupado/""", """controllers.MnuReportes.reportePorProyectoAgrupado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportePorProyectoListaAgrupado/""", """controllers.MnuReportes.reportePorProyectoListaAgrupado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportePorProyectoDetalleAgrupado/""", """controllers.MnuReportes.reportePorProyectoDetalleAgrupado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportePorProyectoDetalleAgrupadoExcel/""", """controllers.MnuReportes.reportePorProyectoDetalleAgrupadoExcel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportEstadoBodegas/""", """controllers.MnuReportes.reportEstadoBodegas(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportEstadoBodegasExcel/""", """controllers.MnuReportes.reportEstadoBodegasExcel(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """envioMasivoListadoProforma2/""", """controllers.MnuReportes.envioMasivoListadoProforma2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """proformaListaPdf/""", """controllers.MnuReportes.proformaListaPdf(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportGerenKGPorPeriodo0/""", """controllers.MnuReportes.reportGerenKGPorPeriodo0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportGerenKGPorPeriodo1/""", """controllers.MnuReportes.reportGerenKGPorPeriodo1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportGerenKGPorPeriodo1Excel/""", """controllers.MnuReportes.reportGerenKGPorPeriodo1Excel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportVentasPorPeriodo0/""", """controllers.MnuReportes.reportVentasPorPeriodo0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportVentasPorPeriodo1/""", """controllers.MnuReportes.reportVentasPorPeriodo1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportVentasPorPeriodo1Excel/""", """controllers.MnuReportes.reportVentasPorPeriodo1Excel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEstadosPer0/""", """controllers.MnuReportes.reporteEstadosPer0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEstadosPer1/""", """controllers.MnuReportes.reporteEstadosPer1(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reporteEstadosPer1Excel/""", """controllers.MnuReportes.reporteEstadosPer1Excel(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportePorProyectoValorizado/""", """controllers.MnuReportes.reportePorProyectoValorizado(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reportePorProyectoListaValorizado/""", """controllers.MnuReportes.reportePorProyectoListaValorizado(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sucursalAdministrar/""", """controllers.MnuBodegas.sucursalAdministrar(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sucursalElimina/""", """controllers.MnuBodegas.sucursalElimina(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sucursalModifica/""", """controllers.MnuBodegas.sucursalModifica(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sucursalAgrega/""", """controllers.MnuBodegas.sucursalAgrega(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sucursalNuevo/""", """controllers.MnuBodegas.sucursalNuevo(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """modificaSucursalPorCampoAjax/""", """controllers.MnuBodegas.modificaSucursalPorCampoAjax(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """movimientoSelectModificar2/""", """controllers.MnuMovimientos.movimientoSelectModificar2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteMantencion2/""", """controllers.MnuTablas.clienteMantencion2(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """clienteCambiaEstado/""", """controllers.MnuTablas.clienteCambiaEstado(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """domStockDiarioExcel0/""", """controllers.MnuReportes.domStockDiarioExcel0(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """domStockDiarioExcel1/""", """controllers.MnuReportes.domStockDiarioExcel1(request:Request)"""),
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
    Assets_10.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:13
  private lazy val controllers_HomeController_viewImg1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("viewImg/"), DynamicPart("img", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_viewImg1_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_11.viewImg(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
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

  // @LINE:14
  private lazy val controllers_HomeController_showPdf2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("showPdf/"), DynamicPart("fileNamePdf", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_showPdf2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_11.showPdf(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.HomeController",
      "showPdf",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """showPdf/""" + "$" + """fileNamePdf<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private lazy val controllers_HomeController_showPdfManuales3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("showPdfManuales/"), DynamicPart("fileNamePdf", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_showPdfManuales3_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_11.showPdfManuales(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.HomeController",
      "showPdfManuales",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """showPdfManuales/""" + "$" + """fileNamePdf<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private lazy val controllers_HomeController_downloadPDF4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("downloadPDF/")))
  )
  private lazy val controllers_HomeController_downloadPDF4_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_11.downloadPDF(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.HomeController",
      "downloadPDF",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """downloadPDF/""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private lazy val controllers_HomeController_tasasDeFechaAjax5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tasasDeFechaAjax/")))
  )
  private lazy val controllers_HomeController_tasasDeFechaAjax5_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_11.tasasDeFechaAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.HomeController",
      "tasasDeFechaAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """tasasDeFechaAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private lazy val controllers_HomeController_sendEmail6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sendEmail/")))
  )
  private lazy val controllers_HomeController_sendEmail6_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_11.sendEmail(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.HomeController",
      "sendEmail",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """sendEmail/""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private lazy val controllers_HomeController_redirShowPDF7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redirShowPDF/"), DynamicPart("fileNamePdf", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("titulo", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("url", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_redirShowPDF7_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_11.redirShowPDF(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.HomeController",
      "redirShowPDF",
      Seq(classOf[String], classOf[String], classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """redirShowPDF/""" + "$" + """fileNamePdf<[^/]+>,""" + "$" + """titulo<[^/]+>,""" + "$" + """url<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private lazy val controllers_HomeController_redirOdoShowPDF8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redirOdoShowPDF/"), DynamicPart("fileNamePdf", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("desde", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("hasta", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("url", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_HomeController_redirOdoShowPDF8_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_11.redirOdoShowPDF(fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.HomeController",
      "redirOdoShowPDF",
      Seq(classOf[String], classOf[String], classOf[String], classOf[String], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """redirOdoShowPDF/""" + "$" + """fileNamePdf<[^/]+>,""" + "$" + """desde<[^/]+>,""" + "$" + """hasta<[^/]+>,""" + "$" + """url<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:30
  private lazy val controllers_MnuRedimensionar_redimensionarAsignaBodega9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redimensionarAsignaBodega/")))
  )
  private lazy val controllers_MnuRedimensionar_redimensionarAsignaBodega9_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuRedimensionar_1.redimensionarAsignaBodega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuRedimensionar",
      "redimensionarAsignaBodega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """redimensionarAsignaBodega/""",
      """""",
      Seq()
    )
  )

  // @LINE:31
  private lazy val controllers_MnuRedimensionar_redimensionarAsignaBodegaUpdate10_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redimensionarAsignaBodegaUpdate/")))
  )
  private lazy val controllers_MnuRedimensionar_redimensionarAsignaBodegaUpdate10_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuRedimensionar_1.redimensionarAsignaBodegaUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuRedimensionar",
      "redimensionarAsignaBodegaUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """redimensionarAsignaBodegaUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:33
  private lazy val controllers_MnuRedimensionar_redimensionarPrepara11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redimensionarPrepara/")))
  )
  private lazy val controllers_MnuRedimensionar_redimensionarPrepara11_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuRedimensionar_1.redimensionarPrepara(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuRedimensionar",
      "redimensionarPrepara",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """redimensionarPrepara/""",
      """""",
      Seq()
    )
  )

  // @LINE:34
  private lazy val controllers_MnuRedimensionar_redimensionarNuevo12_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redimensionarNuevo/")))
  )
  private lazy val controllers_MnuRedimensionar_redimensionarNuevo12_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuRedimensionar_1.redimensionarNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuRedimensionar",
      "redimensionarNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """redimensionarNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:35
  private lazy val controllers_MnuRedimensionar_redimensionarConfirmar013_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redimensionarConfirmar0/")))
  )
  private lazy val controllers_MnuRedimensionar_redimensionarConfirmar013_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuRedimensionar_1.redimensionarConfirmar0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuRedimensionar",
      "redimensionarConfirmar0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """redimensionarConfirmar0/""",
      """""",
      Seq()
    )
  )

  // @LINE:36
  private lazy val controllers_MnuRedimensionar_redimensionarConfirmar114_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redimensionarConfirmar1/")))
  )
  private lazy val controllers_MnuRedimensionar_redimensionarConfirmar114_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuRedimensionar_1.redimensionarConfirmar1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuRedimensionar",
      "redimensionarConfirmar1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """redimensionarConfirmar1/""",
      """""",
      Seq()
    )
  )

  // @LINE:37
  private lazy val controllers_MnuRedimensionar_redimensionarConfirmar215_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redimensionarConfirmar2/")))
  )
  private lazy val controllers_MnuRedimensionar_redimensionarConfirmar215_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuRedimensionar_1.redimensionarConfirmar2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuRedimensionar",
      "redimensionarConfirmar2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """redimensionarConfirmar2/""",
      """""",
      Seq()
    )
  )

  // @LINE:39
  private lazy val controllers_MnuRedimensionar_redimensionarEliminar016_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redimensionarEliminar0/")))
  )
  private lazy val controllers_MnuRedimensionar_redimensionarEliminar016_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuRedimensionar_1.redimensionarEliminar0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuRedimensionar",
      "redimensionarEliminar0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """redimensionarEliminar0/""",
      """""",
      Seq()
    )
  )

  // @LINE:40
  private lazy val controllers_MnuRedimensionar_redimensionarEliminar117_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redimensionarEliminar1/")))
  )
  private lazy val controllers_MnuRedimensionar_redimensionarEliminar117_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuRedimensionar_1.redimensionarEliminar1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuRedimensionar",
      "redimensionarEliminar1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """redimensionarEliminar1/""",
      """""",
      Seq()
    )
  )

  // @LINE:41
  private lazy val controllers_MnuRedimensionar_redimensionarEliminar218_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redimensionarEliminar2/")))
  )
  private lazy val controllers_MnuRedimensionar_redimensionarEliminar218_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuRedimensionar_1.redimensionarEliminar2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuRedimensionar",
      "redimensionarEliminar2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """redimensionarEliminar2/""",
      """""",
      Seq()
    )
  )

  // @LINE:43
  private lazy val controllers_MnuRedimensionar_redimensionarListar019_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redimensionarListar0/")))
  )
  private lazy val controllers_MnuRedimensionar_redimensionarListar019_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuRedimensionar_1.redimensionarListar0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuRedimensionar",
      "redimensionarListar0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """redimensionarListar0/""",
      """""",
      Seq()
    )
  )

  // @LINE:44
  private lazy val controllers_MnuRedimensionar_redimensionarListar120_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("redimensionarListar1/")))
  )
  private lazy val controllers_MnuRedimensionar_redimensionarListar120_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuRedimensionar_1.redimensionarListar1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuRedimensionar",
      "redimensionarListar1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """redimensionarListar1/""",
      """""",
      Seq()
    )
  )

  // @LINE:52
  private lazy val controllers_MnuMovimientos_movValySubePlantilla21_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movValySubePlantilla/")))
  )
  private lazy val controllers_MnuMovimientos_movValySubePlantilla21_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_8.movValySubePlantilla(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuMovimientos",
      "movValySubePlantilla",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movValySubePlantilla/""",
      """""",
      Seq()
    )
  )

  // @LINE:53
  private lazy val controllers_MnuMovimientos_movPlantilla22_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movPlantilla/")))
  )
  private lazy val controllers_MnuMovimientos_movPlantilla22_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_8.movPlantilla(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuMovimientos",
      "movPlantilla",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movPlantilla/""",
      """""",
      Seq()
    )
  )

  // @LINE:60
  private lazy val controllers_MnuCotiOdo_cotiOdoModifyXCampo23_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoModifyXCampo/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoModifyXCampo23_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoModifyXCampo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoModifyXCampo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiOdoModifyXCampo/""",
      """""",
      Seq()
    )
  )

  // @LINE:61
  private lazy val controllers_MnuCotiOdo_cotiOdoIngreso24_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoIngreso/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoIngreso24_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoIngreso(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoIngreso",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotiOdoIngreso/""",
      """""",
      Seq()
    )
  )

  // @LINE:62
  private lazy val controllers_MnuCotiOdo_cotiOdoNuevo25_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoNuevo/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoNuevo25_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiOdoNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:63
  private lazy val controllers_MnuCotiOdo_existeNumeroCotiOdoAjax26_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("existeNumeroCotiOdoAjax/")))
  )
  private lazy val controllers_MnuCotiOdo_existeNumeroCotiOdoAjax26_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.existeNumeroCotiOdoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "existeNumeroCotiOdoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """existeNumeroCotiOdoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:64
  private lazy val controllers_MnuCotiOdo_cotiOdoModificaJson27_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoModificaJson/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoModificaJson27_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoModificaJson(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoModificaJson",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiOdoModificaJson/""",
      """""",
      Seq()
    )
  )

  // @LINE:66
  private lazy val controllers_MnuCotiOdo_cotiOdoListaModifica28_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoListaModifica/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoListaModifica28_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoListaModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoListaModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotiOdoListaModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:67
  private lazy val controllers_MnuCotiOdo_cotiOdoElimina29_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoElimina/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoElimina29_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiOdoElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:68
  private lazy val controllers_MnuCotiOdo_cotiOdoModifica30_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoModifica/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoModifica30_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiOdoModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:69
  private lazy val controllers_MnuCotiOdo_cotiOdoUpdate31_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoUpdate/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoUpdate31_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoUpdate(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoUpdate",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiOdoUpdate/""",
      """""",
      Seq()
    )
  )

  // @LINE:70
  private lazy val controllers_MnuCotiOdo_verCotiOdoAjax32_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verCotiOdoAjax/")))
  )
  private lazy val controllers_MnuCotiOdo_verCotiOdoAjax32_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.verCotiOdoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "verCotiOdoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verCotiOdoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:72
  private lazy val controllers_MnuCotiOdo_cotiOdoListaCambiaEstado33_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoListaCambiaEstado/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoListaCambiaEstado33_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoListaCambiaEstado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoListaCambiaEstado",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotiOdoListaCambiaEstado/""",
      """""",
      Seq()
    )
  )

  // @LINE:73
  private lazy val controllers_MnuCotiOdo_cambiarCotiOdoEstadoAjax34_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cambiarCotiOdoEstadoAjax/")))
  )
  private lazy val controllers_MnuCotiOdo_cambiarCotiOdoEstadoAjax34_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cambiarCotiOdoEstadoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cambiarCotiOdoEstadoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cambiarCotiOdoEstadoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:75
  private lazy val controllers_MnuCotiOdo_cotiOdoListaImprimir35_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoListaImprimir/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoListaImprimir35_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoListaImprimir(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoListaImprimir",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotiOdoListaImprimir/""",
      """""",
      Seq()
    )
  )

  // @LINE:76
  private lazy val controllers_MnuCotiOdo_cotiOdoImprimir36_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoImprimir/"), DynamicPart("id_cotiOdo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoImprimir36_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoImprimir(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoImprimir",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """cotiOdoImprimir/""" + "$" + """id_cotiOdo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:77
  private lazy val controllers_MnuCotiOdo_cotiOdoExcel37_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoExcel/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoExcel37_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiOdoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:78
  private lazy val controllers_MnuCotiOdo_pdfCotiOdoVta38_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("pdfCotiOdoVta/")))
  )
  private lazy val controllers_MnuCotiOdo_pdfCotiOdoVta38_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.pdfCotiOdoVta(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "pdfCotiOdoVta",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """pdfCotiOdoVta/""",
      """""",
      Seq()
    )
  )

  // @LINE:80
  private lazy val controllers_MnuCotiOdo_reportCotiOdoSel39_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportCotiOdoSel/")))
  )
  private lazy val controllers_MnuCotiOdo_reportCotiOdoSel39_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.reportCotiOdoSel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "reportCotiOdoSel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportCotiOdoSel/""",
      """""",
      Seq()
    )
  )

  // @LINE:81
  private lazy val controllers_MnuCotiOdo_reportCotiOdoRpt40_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportCotiOdoRpt/")))
  )
  private lazy val controllers_MnuCotiOdo_reportCotiOdoRpt40_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.reportCotiOdoRpt(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "reportCotiOdoRpt",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportCotiOdoRpt/""",
      """""",
      Seq()
    )
  )

  // @LINE:83
  private lazy val controllers_MnuCotiOdo_cotiOdoListaConfirma41_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoListaConfirma/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoListaConfirma41_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoListaConfirma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoListaConfirma",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotiOdoListaConfirma/""",
      """""",
      Seq()
    )
  )

  // @LINE:84
  private lazy val controllers_MnuCotiOdo_cotiOdoConfirma42_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoConfirma/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoConfirma42_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoConfirma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoConfirma",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiOdoConfirma/""",
      """""",
      Seq()
    )
  )

  // @LINE:86
  private lazy val controllers_MnuCotiOdo_otOdoListaCotizaSinOt43_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otOdoListaCotizaSinOt/")))
  )
  private lazy val controllers_MnuCotiOdo_otOdoListaCotizaSinOt43_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.otOdoListaCotizaSinOt(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "otOdoListaCotizaSinOt",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otOdoListaCotizaSinOt/""",
      """""",
      Seq()
    )
  )

  // @LINE:87
  private lazy val controllers_MnuCotiOdo_otOdoHacer44_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otOdoHacer/")))
  )
  private lazy val controllers_MnuCotiOdo_otOdoHacer44_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.otOdoHacer(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "otOdoHacer",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otOdoHacer/""",
      """""",
      Seq()
    )
  )

  // @LINE:88
  private lazy val controllers_MnuCotiOdo_otOdoGrabar45_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otOdoGrabar/")))
  )
  private lazy val controllers_MnuCotiOdo_otOdoGrabar45_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.otOdoGrabar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "otOdoGrabar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otOdoGrabar/""",
      """""",
      Seq()
    )
  )

  // @LINE:90
  private lazy val controllers_MnuCotiOdo_otOdoListaEliminar46_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otOdoListaEliminar/")))
  )
  private lazy val controllers_MnuCotiOdo_otOdoListaEliminar46_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.otOdoListaEliminar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "otOdoListaEliminar",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otOdoListaEliminar/""",
      """""",
      Seq()
    )
  )

  // @LINE:91
  private lazy val controllers_MnuCotiOdo_verOtOdoAjax47_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("verOtOdoAjax/")))
  )
  private lazy val controllers_MnuCotiOdo_verOtOdoAjax47_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.verOtOdoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "verOtOdoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """verOtOdoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:92
  private lazy val controllers_MnuCotiOdo_actualFechaEnvioOtOdoAjax48_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualFechaEnvioOtOdoAjax/")))
  )
  private lazy val controllers_MnuCotiOdo_actualFechaEnvioOtOdoAjax48_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.actualFechaEnvioOtOdoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "actualFechaEnvioOtOdoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualFechaEnvioOtOdoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:93
  private lazy val controllers_MnuCotiOdo_actualOperServOtOdoAjax49_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualOperServOtOdoAjax/")))
  )
  private lazy val controllers_MnuCotiOdo_actualOperServOtOdoAjax49_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.actualOperServOtOdoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "actualOperServOtOdoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualOperServOtOdoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:95
  private lazy val controllers_MnuCotiOdo_otOdoElimina50_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otOdoElimina/")))
  )
  private lazy val controllers_MnuCotiOdo_otOdoElimina50_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.otOdoElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "otOdoElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otOdoElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:97
  private lazy val controllers_MnuCotiOdo_otOdoListaCambiarEstado51_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otOdoListaCambiarEstado/")))
  )
  private lazy val controllers_MnuCotiOdo_otOdoListaCambiarEstado51_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.otOdoListaCambiarEstado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "otOdoListaCambiarEstado",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otOdoListaCambiarEstado/""",
      """""",
      Seq()
    )
  )

  // @LINE:98
  private lazy val controllers_MnuCotiOdo_cambiarOtOdoEstadoAjax52_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cambiarOtOdoEstadoAjax/")))
  )
  private lazy val controllers_MnuCotiOdo_cambiarOtOdoEstadoAjax52_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cambiarOtOdoEstadoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cambiarOtOdoEstadoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cambiarOtOdoEstadoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:100
  private lazy val controllers_MnuCotiOdo_otOdoListaRevisar53_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otOdoListaRevisar/")))
  )
  private lazy val controllers_MnuCotiOdo_otOdoListaRevisar53_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.otOdoListaRevisar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "otOdoListaRevisar",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otOdoListaRevisar/""",
      """""",
      Seq()
    )
  )

  // @LINE:101
  private lazy val controllers_MnuCotiOdo_revisarOtOdo54_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("revisarOtOdo/")))
  )
  private lazy val controllers_MnuCotiOdo_revisarOtOdo54_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.revisarOtOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "revisarOtOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """revisarOtOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:102
  private lazy val controllers_MnuCotiOdo_trazaServicioOtOdoAjax55_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("trazaServicioOtOdoAjax/")))
  )
  private lazy val controllers_MnuCotiOdo_trazaServicioOtOdoAjax55_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.trazaServicioOtOdoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "trazaServicioOtOdoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """trazaServicioOtOdoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:103
  private lazy val controllers_MnuCotiOdo_otOdoRevisarExcel56_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otOdoRevisarExcel/")))
  )
  private lazy val controllers_MnuCotiOdo_otOdoRevisarExcel56_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.otOdoRevisarExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "otOdoRevisarExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otOdoRevisarExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:105
  private lazy val controllers_MnuCotiOdo_reportOtOdoSel57_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOtOdoSel/")))
  )
  private lazy val controllers_MnuCotiOdo_reportOtOdoSel57_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.reportOtOdoSel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "reportOtOdoSel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportOtOdoSel/""",
      """""",
      Seq()
    )
  )

  // @LINE:106
  private lazy val controllers_MnuCotiOdo_reportOtOdoRpt58_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOtOdoRpt/")))
  )
  private lazy val controllers_MnuCotiOdo_reportOtOdoRpt58_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.reportOtOdoRpt(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "reportOtOdoRpt",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportOtOdoRpt/""",
      """""",
      Seq()
    )
  )

  // @LINE:108
  private lazy val controllers_MnuCotiOdo_otOdoListaConfirma59_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otOdoListaConfirma/")))
  )
  private lazy val controllers_MnuCotiOdo_otOdoListaConfirma59_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.otOdoListaConfirma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "otOdoListaConfirma",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otOdoListaConfirma/""",
      """""",
      Seq()
    )
  )

  // @LINE:109
  private lazy val controllers_MnuCotiOdo_otOdoConfirma60_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otOdoConfirma/")))
  )
  private lazy val controllers_MnuCotiOdo_otOdoConfirma60_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.otOdoConfirma(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "otOdoConfirma",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otOdoConfirma/""",
      """""",
      Seq()
    )
  )

  // @LINE:111
  private lazy val controllers_MnuCotiOdo_otOdoListaAgregarOC61_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otOdoListaAgregarOC/"), DynamicPart("year", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuCotiOdo_otOdoListaAgregarOC61_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.otOdoListaAgregarOC(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "otOdoListaAgregarOC",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """otOdoListaAgregarOC/""" + "$" + """year<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:112
  private lazy val controllers_MnuCotiOdo_grabarOcOdoPdf62_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabarOcOdoPdf/"), DynamicPart("id_otOdo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuCotiOdo_grabarOcOdoPdf62_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.grabarOcOdoPdf(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "grabarOcOdoPdf",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "POST",
      this.prefix + """grabarOcOdoPdf/""" + "$" + """id_otOdo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:113
  private lazy val controllers_MnuCotiOdo_oc_cotiOdoClienteAjax63_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("oc_cotiOdoClienteAjax/")))
  )
  private lazy val controllers_MnuCotiOdo_oc_cotiOdoClienteAjax63_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.oc_cotiOdoClienteAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "oc_cotiOdoClienteAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """oc_cotiOdoClienteAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:115
  private lazy val controllers_MnuCotiOdo_otOdoGenerarContrato64_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otOdoGenerarContrato/"), DynamicPart("year", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuCotiOdo_otOdoGenerarContrato64_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.otOdoGenerarContrato(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "otOdoGenerarContrato",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "GET",
      this.prefix + """otOdoGenerarContrato/""" + "$" + """year<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:116
  private lazy val controllers_MnuCotiOdo_grabarContratoPdfOdo65_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabarContratoPdfOdo/"), DynamicPart("id_cotiOdo", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuCotiOdo_grabarContratoPdfOdo65_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.grabarContratoPdfOdo(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "grabarContratoPdfOdo",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "POST",
      this.prefix + """grabarContratoPdfOdo/""" + "$" + """id_cotiOdo<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:117
  private lazy val controllers_MnuCotiOdo_datosContratoOdo66_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("datosContratoOdo/")))
  )
  private lazy val controllers_MnuCotiOdo_datosContratoOdo66_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.datosContratoOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "datosContratoOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """datosContratoOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:118
  private lazy val controllers_MnuCotiOdo_hacerContratoOdo67_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hacerContratoOdo/")))
  )
  private lazy val controllers_MnuCotiOdo_hacerContratoOdo67_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.hacerContratoOdo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "hacerContratoOdo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hacerContratoOdo/""",
      """""",
      Seq()
    )
  )

  // @LINE:120
  private lazy val controllers_MnuCotiOdo_cotiOdoPlantilla68_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoPlantilla/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoPlantilla68_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoPlantilla(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoPlantilla",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiOdoPlantilla/""",
      """""",
      Seq()
    )
  )

  // @LINE:121
  private lazy val controllers_MnuCotiOdo_cotiOdoValidarPlantilla69_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiOdoValidarPlantilla/")))
  )
  private lazy val controllers_MnuCotiOdo_cotiOdoValidarPlantilla69_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotiOdo_5.cotiOdoValidarPlantilla(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotiOdo",
      "cotiOdoValidarPlantilla",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiOdoValidarPlantilla/""",
      """""",
      Seq()
    )
  )

  // @LINE:129
  private lazy val controllers_MnuCotizar_reportOtSaldos70_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOtSaldos/")))
  )
  private lazy val controllers_MnuCotizar_reportOtSaldos70_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportOtSaldos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportOtSaldos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportOtSaldos/""",
      """""",
      Seq()
    )
  )

  // @LINE:130
  private lazy val controllers_MnuCotizar_reportOtSaldosExcel71_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOtSaldosExcel/")))
  )
  private lazy val controllers_MnuCotizar_reportOtSaldosExcel71_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportOtSaldosExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportOtSaldosExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportOtSaldosExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:131
  private lazy val controllers_MnuCotizar_listadoOTAjax72_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("listadoOTAjax/")))
  )
  private lazy val controllers_MnuCotizar_listadoOTAjax72_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.listadoOTAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "listadoOTAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """listadoOTAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:132
  private lazy val controllers_MnuCotizar_reportOtSaldosPorOt73_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOtSaldosPorOt/")))
  )
  private lazy val controllers_MnuCotizar_reportOtSaldosPorOt73_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportOtSaldosPorOt(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportOtSaldosPorOt",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportOtSaldosPorOt/""",
      """""",
      Seq()
    )
  )

  // @LINE:133
  private lazy val controllers_MnuCotizar_reportOtSaldosPorOtExcel74_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOtSaldosPorOtExcel/")))
  )
  private lazy val controllers_MnuCotizar_reportOtSaldosPorOtExcel74_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportOtSaldosPorOtExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportOtSaldosPorOtExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportOtSaldosPorOtExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:135
  private lazy val controllers_MnuCotizar_otAdminPrecios075_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otAdminPrecios0/")))
  )
  private lazy val controllers_MnuCotizar_otAdminPrecios075_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otAdminPrecios0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otAdminPrecios0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otAdminPrecios0/""",
      """""",
      Seq()
    )
  )

  // @LINE:136
  private lazy val controllers_MnuCotizar_otAdminPrecios176_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otAdminPrecios1/")))
  )
  private lazy val controllers_MnuCotizar_otAdminPrecios176_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otAdminPrecios1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otAdminPrecios1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otAdminPrecios1/""",
      """""",
      Seq()
    )
  )

  // @LINE:138
  private lazy val controllers_MnuCotizar_otCambiarEstadoTerminadas77_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otCambiarEstadoTerminadas/")))
  )
  private lazy val controllers_MnuCotizar_otCambiarEstadoTerminadas77_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otCambiarEstadoTerminadas(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otCambiarEstadoTerminadas",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otCambiarEstadoTerminadas/""",
      """""",
      Seq()
    )
  )

  // @LINE:144
  private lazy val controllers_MnuCotizar_cotizaListaResumen078_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaResumen0/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaResumen078_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotizaListaResumen0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotizaListaResumen0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaListaResumen0/""",
      """""",
      Seq()
    )
  )

  // @LINE:145
  private lazy val controllers_MnuCotizar_cotizaListaResumen0079_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaResumen00/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaResumen0079_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotizaListaResumen00(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotizaListaResumen00",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaListaResumen00/""",
      """""",
      Seq()
    )
  )

  // @LINE:146
  private lazy val controllers_MnuCotizar_cotizaListaResumen180_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaResumen1/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaResumen180_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotizaListaResumen1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotizaListaResumen1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaListaResumen1/""",
      """""",
      Seq()
    )
  )

  // @LINE:147
  private lazy val controllers_MnuCotizar_cotizaListaResumen281_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaResumen2/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaResumen281_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotizaListaResumen2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotizaListaResumen2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaListaResumen2/""",
      """""",
      Seq()
    )
  )

  // @LINE:148
  private lazy val controllers_MnuCotizar_cotizaListaResumen2Excel82_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaResumen2Excel/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaResumen2Excel82_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotizaListaResumen2Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotizaListaResumen2Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaListaResumen2Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:149
  private lazy val controllers_MnuCotizar_cotizaListaResumen383_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaResumen3/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaResumen383_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotizaListaResumen3(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotizaListaResumen3",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaListaResumen3/""",
      """""",
      Seq()
    )
  )

  // @LINE:150
  private lazy val controllers_MnuCotizar_cotizaListaBiblioteca84_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaBiblioteca/")))
  )
  private lazy val controllers_MnuCotizar_cotizaListaBiblioteca84_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotizaListaBiblioteca(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotizaListaBiblioteca",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaListaBiblioteca/""",
      """""",
      Seq()
    )
  )

  // @LINE:151
  private lazy val controllers_MnuCotizar_cotiBibliotecaElimina85_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiBibliotecaElimina/")))
  )
  private lazy val controllers_MnuCotizar_cotiBibliotecaElimina85_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotiBibliotecaElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotiBibliotecaElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiBibliotecaElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:154
  private lazy val controllers_MnuCotizar_reportOtPrecios086_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOtPrecios0/")))
  )
  private lazy val controllers_MnuCotizar_reportOtPrecios086_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportOtPrecios0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportOtPrecios0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportOtPrecios0/""",
      """""",
      Seq()
    )
  )

  // @LINE:155
  private lazy val controllers_MnuCotizar_reportOtPrecios187_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOtPrecios1/")))
  )
  private lazy val controllers_MnuCotizar_reportOtPrecios187_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportOtPrecios1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportOtPrecios1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportOtPrecios1/""",
      """""",
      Seq()
    )
  )

  // @LINE:161
  private lazy val controllers_MnuTablas_comercialMantencion88_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("comercialMantencion/")))
  )
  private lazy val controllers_MnuTablas_comercialMantencion88_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.comercialMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "comercialMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """comercialMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:162
  private lazy val controllers_MnuTablas_modVigComercialAjax89_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modVigComercialAjax/")))
  )
  private lazy val controllers_MnuTablas_modVigComercialAjax89_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.modVigComercialAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "modVigComercialAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modVigComercialAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:163
  private lazy val controllers_MnuTablas_comercialAgrega90_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("comercialAgrega/")))
  )
  private lazy val controllers_MnuTablas_comercialAgrega90_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.comercialAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "comercialAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """comercialAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:164
  private lazy val controllers_MnuTablas_precioMantSelSucursal91_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("precioMantSelSucursal/")))
  )
  private lazy val controllers_MnuTablas_precioMantSelSucursal91_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.precioMantSelSucursal(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "precioMantSelSucursal",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """precioMantSelSucursal/""",
      """""",
      Seq()
    )
  )

  // @LINE:166
  private lazy val controllers_MnuTablas_equipoMantencionExcel92_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("equipoMantencionExcel/")))
  )
  private lazy val controllers_MnuTablas_equipoMantencionExcel92_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.equipoMantencionExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "equipoMantencionExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """equipoMantencionExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:172
  private lazy val controllers_MnuTablas_dibujanteMantencion93_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("dibujanteMantencion/")))
  )
  private lazy val controllers_MnuTablas_dibujanteMantencion93_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.dibujanteMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "dibujanteMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """dibujanteMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:173
  private lazy val controllers_MnuTablas_dibujanteAgrega94_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("dibujanteAgrega/")))
  )
  private lazy val controllers_MnuTablas_dibujanteAgrega94_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.dibujanteAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "dibujanteAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """dibujanteAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:174
  private lazy val controllers_MnuTablas_dibujanteGraba95_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("dibujanteGraba/")))
  )
  private lazy val controllers_MnuTablas_dibujanteGraba95_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.dibujanteGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "dibujanteGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """dibujanteGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:175
  private lazy val controllers_MnuTablas_propiedadElimina96_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("propiedadElimina/")))
  )
  private lazy val controllers_MnuTablas_propiedadElimina96_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.propiedadElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "propiedadElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """propiedadElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:176
  private lazy val controllers_MnuTablas_dibujanteModifica97_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("dibujanteModifica/")))
  )
  private lazy val controllers_MnuTablas_dibujanteModifica97_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.dibujanteModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "dibujanteModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """dibujanteModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:177
  private lazy val controllers_MnuTablas_modificaDibujantePorCampoAjax98_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaDibujantePorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaDibujantePorCampoAjax98_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.modificaDibujantePorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "modificaDibujantePorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaDibujantePorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:183
  private lazy val controllers_MnuTablas_propiedadMantencion99_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("propiedadMantencion/")))
  )
  private lazy val controllers_MnuTablas_propiedadMantencion99_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.propiedadMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "propiedadMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """propiedadMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:184
  private lazy val controllers_MnuTablas_propiedadAgrega100_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("propiedadAgrega/")))
  )
  private lazy val controllers_MnuTablas_propiedadAgrega100_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.propiedadAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "propiedadAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """propiedadAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:185
  private lazy val controllers_MnuTablas_propiedadGraba101_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("propiedadGraba/")))
  )
  private lazy val controllers_MnuTablas_propiedadGraba101_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.propiedadGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "propiedadGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """propiedadGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:186
  private lazy val controllers_MnuTablas_propiedadModifica102_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("propiedadModifica/")))
  )
  private lazy val controllers_MnuTablas_propiedadModifica102_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.propiedadModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "propiedadModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """propiedadModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:187
  private lazy val controllers_MnuTablas_modificaPropiedadPorCampoAjax103_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaPropiedadPorCampoAjax/")))
  )
  private lazy val controllers_MnuTablas_modificaPropiedadPorCampoAjax103_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.modificaPropiedadPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "modificaPropiedadPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaPropiedadPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:193
  private lazy val controllers_MnuBodegas_rubroMantencion104_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rubroMantencion/")))
  )
  private lazy val controllers_MnuBodegas_rubroMantencion104_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_12.rubroMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuBodegas",
      "rubroMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """rubroMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:194
  private lazy val controllers_MnuBodegas_rubroAgrega105_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rubroAgrega/")))
  )
  private lazy val controllers_MnuBodegas_rubroAgrega105_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_12.rubroAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuBodegas",
      "rubroAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """rubroAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:195
  private lazy val controllers_MnuBodegas_rubroGraba106_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rubroGraba/")))
  )
  private lazy val controllers_MnuBodegas_rubroGraba106_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_12.rubroGraba(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuBodegas",
      "rubroGraba",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """rubroGraba/""",
      """""",
      Seq()
    )
  )

  // @LINE:196
  private lazy val controllers_MnuBodegas_rubroModifica107_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rubroModifica/")))
  )
  private lazy val controllers_MnuBodegas_rubroModifica107_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_12.rubroModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuBodegas",
      "rubroModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """rubroModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:197
  private lazy val controllers_MnuBodegas_modificaRubroPorCampoAjax108_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaRubroPorCampoAjax/")))
  )
  private lazy val controllers_MnuBodegas_modificaRubroPorCampoAjax108_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_12.modificaRubroPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuBodegas",
      "modificaRubroPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaRubroPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:203
  private lazy val controllers_MnuCompras_movCompras0109_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movCompras0/")))
  )
  private lazy val controllers_MnuCompras_movCompras0109_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_4.movCompras0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCompras",
      "movCompras0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """movCompras0/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 compras
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:204
  private lazy val controllers_MnuCompras_movCompras1110_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movCompras1/")))
  )
  private lazy val controllers_MnuCompras_movCompras1110_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_4.movCompras1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCompras",
      "movCompras1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movCompras1/""",
      """""",
      Seq()
    )
  )

  // @LINE:205
  private lazy val controllers_MnuCompras_movCompras1Excel111_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movCompras1Excel/")))
  )
  private lazy val controllers_MnuCompras_movCompras1Excel111_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_4.movCompras1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCompras",
      "movCompras1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movCompras1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:208
  private lazy val controllers_MnuCompras_compraVistaSelBodDestAjax112_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraVistaSelBodDestAjax/")))
  )
  private lazy val controllers_MnuCompras_compraVistaSelBodDestAjax112_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_4.compraVistaSelBodDestAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCompras",
      "compraVistaSelBodDestAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """compraVistaSelBodDestAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:209
  private lazy val controllers_MnuCompras_compraVistaSelBodDestAjax2113_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraVistaSelBodDestAjax2/")))
  )
  private lazy val controllers_MnuCompras_compraVistaSelBodDestAjax2113_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_4.compraVistaSelBodDestAjax2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCompras",
      "compraVistaSelBodDestAjax2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """compraVistaSelBodDestAjax2/""",
      """""",
      Seq()
    )
  )

  // @LINE:211
  private lazy val controllers_MnuCompras_compraPlantilla114_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraPlantilla/")))
  )
  private lazy val controllers_MnuCompras_compraPlantilla114_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_4.compraPlantilla(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCompras",
      "compraPlantilla",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """compraPlantilla/""",
      """""",
      Seq()
    )
  )

  // @LINE:212
  private lazy val controllers_MnuCompras_compraCargaPlantilla115_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraCargaPlantilla/")))
  )
  private lazy val controllers_MnuCompras_compraCargaPlantilla115_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_4.compraCargaPlantilla(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCompras",
      "compraCargaPlantilla",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """compraCargaPlantilla/""",
      """""",
      Seq()
    )
  )

  // @LINE:213
  private lazy val controllers_MnuCompras_compraValidarPlantilla116_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("compraValidarPlantilla/")))
  )
  private lazy val controllers_MnuCompras_compraValidarPlantilla116_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCompras_4.compraValidarPlantilla(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCompras",
      "compraValidarPlantilla",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """compraValidarPlantilla/""",
      """""",
      Seq()
    )
  )

  // @LINE:219
  private lazy val controllers_MnuTablas_precioPlantilla117_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("precioPlantilla/")))
  )
  private lazy val controllers_MnuTablas_precioPlantilla117_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.precioPlantilla(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "precioPlantilla",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """precioPlantilla/""",
      """""",
      Seq()
    )
  )

  // @LINE:220
  private lazy val controllers_MnuTablas_precioCargaPlantilla118_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("precioCargaPlantilla/")))
  )
  private lazy val controllers_MnuTablas_precioCargaPlantilla118_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.precioCargaPlantilla(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "precioCargaPlantilla",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """precioCargaPlantilla/""",
      """""",
      Seq()
    )
  )

  // @LINE:227
  private lazy val controllers_MnuReportes_reporteExcedentesListaEquipos119_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteExcedentesListaEquipos/")))
  )
  private lazy val controllers_MnuReportes_reporteExcedentesListaEquipos119_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reporteExcedentesListaEquipos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reporteExcedentesListaEquipos",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteExcedentesListaEquipos/""",
      """""",
      Seq()
    )
  )

  // @LINE:228
  private lazy val controllers_MnuReportes_reporteExcedentesDetalleEquipo120_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteExcedentesDetalleEquipo/")))
  )
  private lazy val controllers_MnuReportes_reporteExcedentesDetalleEquipo120_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reporteExcedentesDetalleEquipo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reporteExcedentesDetalleEquipo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteExcedentesDetalleEquipo/""",
      """""",
      Seq()
    )
  )

  // @LINE:229
  private lazy val controllers_MnuReportes_reporteExcedentesDetalleEquipoExcel121_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteExcedentesDetalleEquipoExcel/")))
  )
  private lazy val controllers_MnuReportes_reporteExcedentesDetalleEquipoExcel121_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reporteExcedentesDetalleEquipoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reporteExcedentesDetalleEquipoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteExcedentesDetalleEquipoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:231
  private lazy val controllers_MnuTablas_proyectoMantencionExcel122_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proyectoMantencionExcel/")))
  )
  private lazy val controllers_MnuTablas_proyectoMantencionExcel122_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.proyectoMantencionExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "proyectoMantencionExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proyectoMantencionExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:232
  private lazy val controllers_MnuTablas_equipoCambiaEstado123_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("equipoCambiaEstado/")))
  )
  private lazy val controllers_MnuTablas_equipoCambiaEstado123_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.equipoCambiaEstado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "equipoCambiaEstado",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """equipoCambiaEstado/""",
      """""",
      Seq()
    )
  )

  // @LINE:233
  private lazy val controllers_MnuTablas_equipoCambiaPropiedad124_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("equipoCambiaPropiedad/")))
  )
  private lazy val controllers_MnuTablas_equipoCambiaPropiedad124_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.equipoCambiaPropiedad(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "equipoCambiaPropiedad",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """equipoCambiaPropiedad/""",
      """""",
      Seq()
    )
  )

  // @LINE:235
  private lazy val controllers_HomeController_homeOdoAutorizaVentasWebExcel125_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("homeOdoAutorizaVentasWebExcel/")))
  )
  private lazy val controllers_HomeController_homeOdoAutorizaVentasWebExcel125_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_11.homeOdoAutorizaVentasWebExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.HomeController",
      "homeOdoAutorizaVentasWebExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """homeOdoAutorizaVentasWebExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:243
  private lazy val controllers_MnuCotizar_coti8columnas126_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("coti8columnas/")))
  )
  private lazy val controllers_MnuCotizar_coti8columnas126_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.coti8columnas(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "coti8columnas",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """coti8columnas/""",
      """""",
      Seq()
    )
  )

  // @LINE:244
  private lazy val controllers_MnuCotizar_coti8columnasValida127_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("coti8columnasValida/")))
  )
  private lazy val controllers_MnuCotizar_coti8columnasValida127_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.coti8columnasValida(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "coti8columnasValida",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """coti8columnasValida/""",
      """""",
      Seq()
    )
  )

  // @LINE:247
  private lazy val controllers_MnuCotizarSelect_cotizaIngresoSelect128_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaIngresoSelect/")))
  )
  private lazy val controllers_MnuCotizarSelect_cotizaIngresoSelect128_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizarSelect_7.cotizaIngresoSelect(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizarSelect",
      "cotizaIngresoSelect",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaIngresoSelect/""",
      """""",
      Seq()
    )
  )

  // @LINE:248
  private lazy val controllers_MnuCotizarSelect_cotizaSeleccionados129_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaSeleccionados/")))
  )
  private lazy val controllers_MnuCotizarSelect_cotizaSeleccionados129_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizarSelect_7.cotizaSeleccionados(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizarSelect",
      "cotizaSeleccionados",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaSeleccionados/""",
      """""",
      Seq()
    )
  )

  // @LINE:249
  private lazy val controllers_MnuCotizarSelect_cotiValidarPlantillaSel130_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotiValidarPlantillaSel/")))
  )
  private lazy val controllers_MnuCotizarSelect_cotiValidarPlantillaSel130_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizarSelect_7.cotiValidarPlantillaSel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizarSelect",
      "cotiValidarPlantillaSel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotiValidarPlantillaSel/""",
      """""",
      Seq()
    )
  )

  // @LINE:250
  private lazy val controllers_MnuCotizarSelect_cotizaModificaJsonSel131_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaModificaJsonSel/")))
  )
  private lazy val controllers_MnuCotizarSelect_cotizaModificaJsonSel131_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizarSelect_7.cotizaModificaJsonSel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizarSelect",
      "cotizaModificaJsonSel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaModificaJsonSel/""",
      """""",
      Seq()
    )
  )

  // @LINE:251
  private lazy val controllers_MnuCotizarSelect_actualizaListaCotiSelAjax132_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("actualizaListaCotiSelAjax/")))
  )
  private lazy val controllers_MnuCotizarSelect_actualizaListaCotiSelAjax132_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizarSelect_7.actualizaListaCotiSelAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizarSelect",
      "actualizaListaCotiSelAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """actualizaListaCotiSelAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:254
  private lazy val controllers_MnuCotizarSelect_cotizaListaModificaSelPeriodo133_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaModificaSelPeriodo/")))
  )
  private lazy val controllers_MnuCotizarSelect_cotizaListaModificaSelPeriodo133_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizarSelect_7.cotizaListaModificaSelPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizarSelect",
      "cotizaListaModificaSelPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaListaModificaSelPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:255
  private lazy val controllers_MnuCotizarSelect_cotizaListaModificaSel134_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaListaModificaSel/")))
  )
  private lazy val controllers_MnuCotizarSelect_cotizaListaModificaSel134_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizarSelect_7.cotizaListaModificaSel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizarSelect",
      "cotizaListaModificaSel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaListaModificaSel/""",
      """""",
      Seq()
    )
  )

  // @LINE:256
  private lazy val controllers_MnuCotizarSelect_cotizaModificaSel135_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaModificaSel/")))
  )
  private lazy val controllers_MnuCotizarSelect_cotizaModificaSel135_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizarSelect_7.cotizaModificaSel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizarSelect",
      "cotizaModificaSel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaModificaSel/""",
      """""",
      Seq()
    )
  )

  // @LINE:258
  private lazy val controllers_MnuCotizar_reportPipelineSelRes136_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportPipelineSelRes/")))
  )
  private lazy val controllers_MnuCotizar_reportPipelineSelRes136_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportPipelineSelRes(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportPipelineSelRes",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportPipelineSelRes/""",
      """""",
      Seq()
    )
  )

  // @LINE:259
  private lazy val controllers_MnuCotizar_reportPipelineRptRes137_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportPipelineRptRes/")))
  )
  private lazy val controllers_MnuCotizar_reportPipelineRptRes137_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportPipelineRptRes(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportPipelineRptRes",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportPipelineRptRes/""",
      """""",
      Seq()
    )
  )

  // @LINE:260
  private lazy val controllers_MnuCotizar_reportPipelineDetalle138_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportPipelineDetalle/")))
  )
  private lazy val controllers_MnuCotizar_reportPipelineDetalle138_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportPipelineDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportPipelineDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportPipelineDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:261
  private lazy val controllers_MnuCotizar_reportPipelineRptResExcel139_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportPipelineRptResExcel/")))
  )
  private lazy val controllers_MnuCotizar_reportPipelineRptResExcel139_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportPipelineRptResExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportPipelineRptResExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportPipelineRptResExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:263
  private lazy val controllers_MnuCotizar_reportPipelineSelDet140_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportPipelineSelDet/")))
  )
  private lazy val controllers_MnuCotizar_reportPipelineSelDet140_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportPipelineSelDet(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportPipelineSelDet",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportPipelineSelDet/""",
      """""",
      Seq()
    )
  )

  // @LINE:264
  private lazy val controllers_MnuCotizar_reportPipelineRptDet141_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportPipelineRptDet/")))
  )
  private lazy val controllers_MnuCotizar_reportPipelineRptDet141_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportPipelineRptDet(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportPipelineRptDet",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportPipelineRptDet/""",
      """""",
      Seq()
    )
  )

  // @LINE:265
  private lazy val controllers_MnuCotizar_reportPipelineRptDetExcel142_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportPipelineRptDetExcel/")))
  )
  private lazy val controllers_MnuCotizar_reportPipelineRptDetExcel142_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportPipelineRptDetExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportPipelineRptDetExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportPipelineRptDetExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:267
  private lazy val controllers_MnuCotizar_reportPipelineSelComercial143_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportPipelineSelComercial/")))
  )
  private lazy val controllers_MnuCotizar_reportPipelineSelComercial143_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportPipelineSelComercial(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportPipelineSelComercial",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportPipelineSelComercial/""",
      """""",
      Seq()
    )
  )

  // @LINE:268
  private lazy val controllers_MnuCotizar_reportPipelineRptComercial144_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportPipelineRptComercial/")))
  )
  private lazy val controllers_MnuCotizar_reportPipelineRptComercial144_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportPipelineRptComercial(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportPipelineRptComercial",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportPipelineRptComercial/""",
      """""",
      Seq()
    )
  )

  // @LINE:269
  private lazy val controllers_MnuCotizar_reportPipelineRptComercialExcel145_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportPipelineRptComercialExcel/")))
  )
  private lazy val controllers_MnuCotizar_reportPipelineRptComercialExcel145_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportPipelineRptComercialExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportPipelineRptComercialExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportPipelineRptComercialExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:272
  private lazy val controllers_MnuCotizar_otListarDespachosPeriodo146_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListarDespachosPeriodo/")))
  )
  private lazy val controllers_MnuCotizar_otListarDespachosPeriodo146_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otListarDespachosPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otListarDespachosPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otListarDespachosPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:273
  private lazy val controllers_MnuCotizar_otListarDespachos147_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListarDespachos/")))
  )
  private lazy val controllers_MnuCotizar_otListarDespachos147_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otListarDespachos(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otListarDespachos",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otListarDespachos/""",
      """""",
      Seq()
    )
  )

  // @LINE:274
  private lazy val controllers_MnuCotizar_otDetalleDespacho148_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otDetalleDespacho/")))
  )
  private lazy val controllers_MnuCotizar_otDetalleDespacho148_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otDetalleDespacho(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otDetalleDespacho",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otDetalleDespacho/""",
      """""",
      Seq()
    )
  )

  // @LINE:276
  private lazy val controllers_MnuCotizar_otListarDespachosImgPeriodo149_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListarDespachosImgPeriodo/")))
  )
  private lazy val controllers_MnuCotizar_otListarDespachosImgPeriodo149_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otListarDespachosImgPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otListarDespachosImgPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otListarDespachosImgPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:277
  private lazy val controllers_MnuCotizar_otListarDespachosImg150_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListarDespachosImg/")))
  )
  private lazy val controllers_MnuCotizar_otListarDespachosImg150_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otListarDespachosImg(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otListarDespachosImg",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otListarDespachosImg/""",
      """""",
      Seq()
    )
  )

  // @LINE:278
  private lazy val controllers_MnuCotizar_otDetalleDespachoImg151_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otDetalleDespachoImg/")))
  )
  private lazy val controllers_MnuCotizar_otDetalleDespachoImg151_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otDetalleDespachoImg(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otDetalleDespachoImg",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otDetalleDespachoImg/""",
      """""",
      Seq()
    )
  )

  // @LINE:280
  private lazy val controllers_MnuCotizar_otListaRevisarPeriodo152_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaRevisarPeriodo/")))
  )
  private lazy val controllers_MnuCotizar_otListaRevisarPeriodo152_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otListaRevisarPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otListaRevisarPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otListaRevisarPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:281
  private lazy val controllers_MnuCotizar_otListaRevisar153_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaRevisar/")))
  )
  private lazy val controllers_MnuCotizar_otListaRevisar153_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otListaRevisar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otListaRevisar",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otListaRevisar/""",
      """""",
      Seq()
    )
  )

  // @LINE:282
  private lazy val controllers_MnuCotizar_otListaRevisarExcel154_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaRevisarExcel/")))
  )
  private lazy val controllers_MnuCotizar_otListaRevisarExcel154_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otListaRevisarExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otListaRevisarExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otListaRevisarExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:283
  private lazy val controllers_MnuCotizar_revisarOt155_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("revisarOt/")))
  )
  private lazy val controllers_MnuCotizar_revisarOt155_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.revisarOt(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "revisarOt",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """revisarOt/""",
      """""",
      Seq()
    )
  )

  // @LINE:284
  private lazy val controllers_MnuCotizar_trazaEqOtAjax156_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("trazaEqOtAjax/")))
  )
  private lazy val controllers_MnuCotizar_trazaEqOtAjax156_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.trazaEqOtAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "trazaEqOtAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """trazaEqOtAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:285
  private lazy val controllers_MnuCotizar_otRevisarExcel157_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otRevisarExcel/")))
  )
  private lazy val controllers_MnuCotizar_otRevisarExcel157_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otRevisarExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otRevisarExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otRevisarExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:287
  private lazy val controllers_MnuCotizar_reportOtSel158_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOtSel/")))
  )
  private lazy val controllers_MnuCotizar_reportOtSel158_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportOtSel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportOtSel",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportOtSel/""",
      """""",
      Seq()
    )
  )

  // @LINE:288
  private lazy val controllers_MnuCotizar_reportOtRpt159_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportOtRpt/")))
  )
  private lazy val controllers_MnuCotizar_reportOtRpt159_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.reportOtRpt(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "reportOtRpt",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportOtRpt/""",
      """""",
      Seq()
    )
  )

  // @LINE:290
  private lazy val controllers_MnuCotizar_otGenerarContratoPeriodo160_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otGenerarContratoPeriodo/")))
  )
  private lazy val controllers_MnuCotizar_otGenerarContratoPeriodo160_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otGenerarContratoPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otGenerarContratoPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otGenerarContratoPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:291
  private lazy val controllers_MnuCotizar_otGenerarContrato161_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otGenerarContrato/")))
  )
  private lazy val controllers_MnuCotizar_otGenerarContrato161_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otGenerarContrato(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otGenerarContrato",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otGenerarContrato/""",
      """""",
      Seq()
    )
  )

  // @LINE:292
  private lazy val controllers_MnuCotizar_grabarContratoPdf162_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabarContratoPdf/"), DynamicPart("id_coti", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuCotizar_grabarContratoPdf162_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.grabarContratoPdf(fakeValue[play.mvc.Http.Request], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "grabarContratoPdf",
      Seq(classOf[play.mvc.Http.Request], classOf[Long]),
      "POST",
      this.prefix + """grabarContratoPdf/""" + "$" + """id_coti<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:293
  private lazy val controllers_MnuCotizar_datosContrato163_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("datosContrato/")))
  )
  private lazy val controllers_MnuCotizar_datosContrato163_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.datosContrato(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "datosContrato",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """datosContrato/""",
      """""",
      Seq()
    )
  )

  // @LINE:294
  private lazy val controllers_MnuCotizar_hacerContrato164_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hacerContrato/")))
  )
  private lazy val controllers_MnuCotizar_hacerContrato164_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.hacerContrato(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "hacerContrato",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """hacerContrato/""",
      """""",
      Seq()
    )
  )

  // @LINE:296
  private lazy val controllers_MnuCotizar_otListaAgregarOCPeriodo165_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaAgregarOCPeriodo/")))
  )
  private lazy val controllers_MnuCotizar_otListaAgregarOCPeriodo165_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otListaAgregarOCPeriodo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otListaAgregarOCPeriodo",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """otListaAgregarOCPeriodo/""",
      """""",
      Seq()
    )
  )

  // @LINE:297
  private lazy val controllers_MnuCotizar_otListaAgregarOC166_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("otListaAgregarOC/")))
  )
  private lazy val controllers_MnuCotizar_otListaAgregarOC166_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.otListaAgregarOC(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "otListaAgregarOC",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """otListaAgregarOC/""",
      """""",
      Seq()
    )
  )

  // @LINE:298
  private lazy val controllers_MnuCotizar_grabarOcPdf167_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("grabarOcPdf/"), DynamicPart("id_ot", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("de", """[^/]+""", encodeable=true), StaticPart(","), DynamicPart("a", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuCotizar_grabarOcPdf167_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.grabarOcPdf(fakeValue[play.mvc.Http.Request], fakeValue[Long], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "grabarOcPdf",
      Seq(classOf[play.mvc.Http.Request], classOf[Long], classOf[String], classOf[String]),
      "POST",
      this.prefix + """grabarOcPdf/""" + "$" + """id_ot<[^/]+>,""" + "$" + """de<[^/]+>,""" + "$" + """a<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:299
  private lazy val controllers_MnuCotizar_oc_cotiClienteAjax168_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("oc_cotiClienteAjax/")))
  )
  private lazy val controllers_MnuCotizar_oc_cotiClienteAjax168_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.oc_cotiClienteAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "oc_cotiClienteAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """oc_cotiClienteAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:303
  private lazy val controllers_MnuCotizar_cotizaSolucionMantencion169_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaSolucionMantencion/")))
  )
  private lazy val controllers_MnuCotizar_cotizaSolucionMantencion169_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotizaSolucionMantencion(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotizaSolucionMantencion",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaSolucionMantencion/""",
      """""",
      Seq()
    )
  )

  // @LINE:304
  private lazy val controllers_MnuCotizar_cotizaSolucionElimina170_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaSolucionElimina/")))
  )
  private lazy val controllers_MnuCotizar_cotizaSolucionElimina170_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotizaSolucionElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotizaSolucionElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaSolucionElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:305
  private lazy val controllers_MnuCotizar_cotizaSolucionModifica171_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaSolucionModifica/")))
  )
  private lazy val controllers_MnuCotizar_cotizaSolucionModifica171_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotizaSolucionModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotizaSolucionModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaSolucionModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:306
  private lazy val controllers_MnuCotizar_modificaCotizaSolucionPorCampoAjax172_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaCotizaSolucionPorCampoAjax/")))
  )
  private lazy val controllers_MnuCotizar_modificaCotizaSolucionPorCampoAjax172_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.modificaCotizaSolucionPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "modificaCotizaSolucionPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaCotizaSolucionPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:307
  private lazy val controllers_MnuCotizar_cotizaSolucionAgrega173_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaSolucionAgrega/")))
  )
  private lazy val controllers_MnuCotizar_cotizaSolucionAgrega173_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotizaSolucionAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotizaSolucionAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """cotizaSolucionAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:308
  private lazy val controllers_MnuCotizar_cotizaSolucionNuevo174_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("cotizaSolucionNuevo/")))
  )
  private lazy val controllers_MnuCotizar_cotizaSolucionNuevo174_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuCotizar_3.cotizaSolucionNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuCotizar",
      "cotizaSolucionNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """cotizaSolucionNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:314
  private lazy val controllers_MnuOdoAppWeb_reporteMovOdoAutorizador175_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovOdoAutorizador/")))
  )
  private lazy val controllers_MnuOdoAppWeb_reporteMovOdoAutorizador175_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.reporteMovOdoAutorizador(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuOdoAppWeb",
      "reporteMovOdoAutorizador",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteMovOdoAutorizador/""",
      """""",
      Seq()
    )
  )

  // @LINE:315
  private lazy val controllers_MnuOdoAppWeb_reporteMovOdoAutorizadorDetalle176_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteMovOdoAutorizadorDetalle/")))
  )
  private lazy val controllers_MnuOdoAppWeb_reporteMovOdoAutorizadorDetalle176_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdoAppWeb_0.reporteMovOdoAutorizadorDetalle(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuOdoAppWeb",
      "reporteMovOdoAutorizadorDetalle",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteMovOdoAutorizadorDetalle/""",
      """""",
      Seq()
    )
  )

  // @LINE:321
  private lazy val controllers_MnuOdo_servicioPreciosServicio177_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioPreciosServicio/")))
  )
  private lazy val controllers_MnuOdo_servicioPreciosServicio177_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_9.servicioPreciosServicio(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuOdo",
      "servicioPreciosServicio",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioPreciosServicio/""",
      """""",
      Seq()
    )
  )

  // @LINE:322
  private lazy val controllers_MnuOdo_servicioListaPreciosServicio178_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioListaPreciosServicio/"), DynamicPart("id_serv", """[^/]+""", encodeable=true)))
  )
  private lazy val controllers_MnuOdo_servicioListaPreciosServicio178_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_9.servicioListaPreciosServicio(fakeValue[Long], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuOdo",
      "servicioListaPreciosServicio",
      Seq(classOf[Long], classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """servicioListaPreciosServicio/""" + "$" + """id_serv<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:323
  private lazy val controllers_MnuOdo_servicioAgregaPrecioServicio179_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("servicioAgregaPrecioServicio/")))
  )
  private lazy val controllers_MnuOdo_servicioAgregaPrecioServicio179_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuOdo_9.servicioAgregaPrecioServicio(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuOdo",
      "servicioAgregaPrecioServicio",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """servicioAgregaPrecioServicio/""",
      """""",
      Seq()
    )
  )

  // @LINE:331
  private lazy val controllers_MnuReportes_reportePorProyectoAgrupado180_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportePorProyectoAgrupado/")))
  )
  private lazy val controllers_MnuReportes_reportePorProyectoAgrupado180_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportePorProyectoAgrupado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportePorProyectoAgrupado",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportePorProyectoAgrupado/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU REPORTES
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:332
  private lazy val controllers_MnuReportes_reportePorProyectoListaAgrupado181_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportePorProyectoListaAgrupado/")))
  )
  private lazy val controllers_MnuReportes_reportePorProyectoListaAgrupado181_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportePorProyectoListaAgrupado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportePorProyectoListaAgrupado",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportePorProyectoListaAgrupado/""",
      """""",
      Seq()
    )
  )

  // @LINE:333
  private lazy val controllers_MnuReportes_reportePorProyectoDetalleAgrupado182_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportePorProyectoDetalleAgrupado/")))
  )
  private lazy val controllers_MnuReportes_reportePorProyectoDetalleAgrupado182_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportePorProyectoDetalleAgrupado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportePorProyectoDetalleAgrupado",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportePorProyectoDetalleAgrupado/""",
      """""",
      Seq()
    )
  )

  // @LINE:334
  private lazy val controllers_MnuReportes_reportePorProyectoDetalleAgrupadoExcel183_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportePorProyectoDetalleAgrupadoExcel/")))
  )
  private lazy val controllers_MnuReportes_reportePorProyectoDetalleAgrupadoExcel183_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportePorProyectoDetalleAgrupadoExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportePorProyectoDetalleAgrupadoExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportePorProyectoDetalleAgrupadoExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:336
  private lazy val controllers_MnuReportes_reportEstadoBodegas184_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportEstadoBodegas/")))
  )
  private lazy val controllers_MnuReportes_reportEstadoBodegas184_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportEstadoBodegas(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportEstadoBodegas",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportEstadoBodegas/""",
      """""",
      Seq()
    )
  )

  // @LINE:337
  private lazy val controllers_MnuReportes_reportEstadoBodegasExcel185_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportEstadoBodegasExcel/")))
  )
  private lazy val controllers_MnuReportes_reportEstadoBodegasExcel185_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportEstadoBodegasExcel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportEstadoBodegasExcel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportEstadoBodegasExcel/""",
      """""",
      Seq()
    )
  )

  // @LINE:340
  private lazy val controllers_MnuReportes_envioMasivoListadoProforma2186_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("envioMasivoListadoProforma2/")))
  )
  private lazy val controllers_MnuReportes_envioMasivoListadoProforma2186_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.envioMasivoListadoProforma2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "envioMasivoListadoProforma2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """envioMasivoListadoProforma2/""",
      """""",
      Seq()
    )
  )

  // @LINE:341
  private lazy val controllers_MnuReportes_proformaListaPdf187_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("proformaListaPdf/")))
  )
  private lazy val controllers_MnuReportes_proformaListaPdf187_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.proformaListaPdf(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "proformaListaPdf",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """proformaListaPdf/""",
      """""",
      Seq()
    )
  )

  // @LINE:344
  private lazy val controllers_MnuReportes_reportGerenKGPorPeriodo0188_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportGerenKGPorPeriodo0/")))
  )
  private lazy val controllers_MnuReportes_reportGerenKGPorPeriodo0188_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportGerenKGPorPeriodo0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportGerenKGPorPeriodo0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportGerenKGPorPeriodo0/""",
      """""",
      Seq()
    )
  )

  // @LINE:345
  private lazy val controllers_MnuReportes_reportGerenKGPorPeriodo1189_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportGerenKGPorPeriodo1/")))
  )
  private lazy val controllers_MnuReportes_reportGerenKGPorPeriodo1189_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportGerenKGPorPeriodo1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportGerenKGPorPeriodo1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportGerenKGPorPeriodo1/""",
      """""",
      Seq()
    )
  )

  // @LINE:346
  private lazy val controllers_MnuReportes_reportGerenKGPorPeriodo1Excel190_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportGerenKGPorPeriodo1Excel/")))
  )
  private lazy val controllers_MnuReportes_reportGerenKGPorPeriodo1Excel190_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportGerenKGPorPeriodo1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportGerenKGPorPeriodo1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportGerenKGPorPeriodo1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:348
  private lazy val controllers_MnuReportes_reportVentasPorPeriodo0191_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportVentasPorPeriodo0/")))
  )
  private lazy val controllers_MnuReportes_reportVentasPorPeriodo0191_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportVentasPorPeriodo0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportVentasPorPeriodo0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportVentasPorPeriodo0/""",
      """""",
      Seq()
    )
  )

  // @LINE:349
  private lazy val controllers_MnuReportes_reportVentasPorPeriodo1192_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportVentasPorPeriodo1/")))
  )
  private lazy val controllers_MnuReportes_reportVentasPorPeriodo1192_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportVentasPorPeriodo1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportVentasPorPeriodo1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportVentasPorPeriodo1/""",
      """""",
      Seq()
    )
  )

  // @LINE:350
  private lazy val controllers_MnuReportes_reportVentasPorPeriodo1Excel193_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportVentasPorPeriodo1Excel/")))
  )
  private lazy val controllers_MnuReportes_reportVentasPorPeriodo1Excel193_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportVentasPorPeriodo1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportVentasPorPeriodo1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportVentasPorPeriodo1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:352
  private lazy val controllers_MnuReportes_reporteEstadosPer0194_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEstadosPer0/")))
  )
  private lazy val controllers_MnuReportes_reporteEstadosPer0194_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reporteEstadosPer0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reporteEstadosPer0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reporteEstadosPer0/""",
      """""",
      Seq()
    )
  )

  // @LINE:353
  private lazy val controllers_MnuReportes_reporteEstadosPer1195_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEstadosPer1/")))
  )
  private lazy val controllers_MnuReportes_reporteEstadosPer1195_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reporteEstadosPer1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reporteEstadosPer1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteEstadosPer1/""",
      """""",
      Seq()
    )
  )

  // @LINE:354
  private lazy val controllers_MnuReportes_reporteEstadosPer1Excel196_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reporteEstadosPer1Excel/")))
  )
  private lazy val controllers_MnuReportes_reporteEstadosPer1Excel196_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reporteEstadosPer1Excel(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reporteEstadosPer1Excel",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reporteEstadosPer1Excel/""",
      """""",
      Seq()
    )
  )

  // @LINE:357
  private lazy val controllers_MnuReportes_reportePorProyectoValorizado197_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportePorProyectoValorizado/")))
  )
  private lazy val controllers_MnuReportes_reportePorProyectoValorizado197_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportePorProyectoValorizado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportePorProyectoValorizado",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """reportePorProyectoValorizado/""",
      """""",
      Seq()
    )
  )

  // @LINE:358
  private lazy val controllers_MnuReportes_reportePorProyectoListaValorizado198_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reportePorProyectoListaValorizado/")))
  )
  private lazy val controllers_MnuReportes_reportePorProyectoListaValorizado198_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.reportePorProyectoListaValorizado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "reportePorProyectoListaValorizado",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """reportePorProyectoListaValorizado/""",
      """""",
      Seq()
    )
  )

  // @LINE:364
  private lazy val controllers_MnuBodegas_sucursalAdministrar199_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sucursalAdministrar/")))
  )
  private lazy val controllers_MnuBodegas_sucursalAdministrar199_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_12.sucursalAdministrar(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuBodegas",
      "sucursalAdministrar",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """sucursalAdministrar/""",
      """ ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 MENU BODEGA
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""",
      Seq()
    )
  )

  // @LINE:365
  private lazy val controllers_MnuBodegas_sucursalElimina200_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sucursalElimina/")))
  )
  private lazy val controllers_MnuBodegas_sucursalElimina200_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_12.sucursalElimina(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuBodegas",
      "sucursalElimina",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """sucursalElimina/""",
      """""",
      Seq()
    )
  )

  // @LINE:366
  private lazy val controllers_MnuBodegas_sucursalModifica201_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sucursalModifica/")))
  )
  private lazy val controllers_MnuBodegas_sucursalModifica201_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_12.sucursalModifica(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuBodegas",
      "sucursalModifica",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """sucursalModifica/""",
      """""",
      Seq()
    )
  )

  // @LINE:367
  private lazy val controllers_MnuBodegas_sucursalAgrega202_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sucursalAgrega/")))
  )
  private lazy val controllers_MnuBodegas_sucursalAgrega202_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_12.sucursalAgrega(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuBodegas",
      "sucursalAgrega",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """sucursalAgrega/""",
      """""",
      Seq()
    )
  )

  // @LINE:368
  private lazy val controllers_MnuBodegas_sucursalNuevo203_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sucursalNuevo/")))
  )
  private lazy val controllers_MnuBodegas_sucursalNuevo203_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_12.sucursalNuevo(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuBodegas",
      "sucursalNuevo",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """sucursalNuevo/""",
      """""",
      Seq()
    )
  )

  // @LINE:369
  private lazy val controllers_MnuBodegas_modificaSucursalPorCampoAjax204_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("modificaSucursalPorCampoAjax/")))
  )
  private lazy val controllers_MnuBodegas_modificaSucursalPorCampoAjax204_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuBodegas_12.modificaSucursalPorCampoAjax(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuBodegas",
      "modificaSucursalPorCampoAjax",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """modificaSucursalPorCampoAjax/""",
      """""",
      Seq()
    )
  )

  // @LINE:376
  private lazy val controllers_MnuMovimientos_movimientoSelectModificar2205_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("movimientoSelectModificar2/")))
  )
  private lazy val controllers_MnuMovimientos_movimientoSelectModificar2205_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuMovimientos_8.movimientoSelectModificar2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuMovimientos",
      "movimientoSelectModificar2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """movimientoSelectModificar2/""",
      """""",
      Seq()
    )
  )

  // @LINE:377
  private lazy val controllers_MnuTablas_clienteMantencion2206_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteMantencion2/")))
  )
  private lazy val controllers_MnuTablas_clienteMantencion2206_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.clienteMantencion2(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "clienteMantencion2",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """clienteMantencion2/""",
      """""",
      Seq()
    )
  )

  // @LINE:378
  private lazy val controllers_MnuTablas_clienteCambiaEstado207_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("clienteCambiaEstado/")))
  )
  private lazy val controllers_MnuTablas_clienteCambiaEstado207_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuTablas_2.clienteCambiaEstado(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuTablas",
      "clienteCambiaEstado",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """clienteCambiaEstado/""",
      """""",
      Seq()
    )
  )

  // @LINE:382
  private lazy val controllers_MnuReportes_domStockDiarioExcel0208_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("domStockDiarioExcel0/")))
  )
  private lazy val controllers_MnuReportes_domStockDiarioExcel0208_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.domStockDiarioExcel0(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "domStockDiarioExcel0",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """domStockDiarioExcel0/""",
      """ SOLO DOM:""",
      Seq()
    )
  )

  // @LINE:383
  private lazy val controllers_MnuReportes_domStockDiarioExcel1209_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("domStockDiarioExcel1/")))
  )
  private lazy val controllers_MnuReportes_domStockDiarioExcel1209_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      MnuReportes_6.domStockDiarioExcel1(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "routes2",
      "controllers.MnuReportes",
      "domStockDiarioExcel1",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """domStockDiarioExcel1/""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:4
    case controllers_Assets_versioned0_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned0_invoker.call(Assets_10.versioned(path, file))
      }
  
    // @LINE:13
    case controllers_HomeController_viewImg1_route(params@_) =>
      call(params.fromPath[String]("img", None)) { (img) =>
        controllers_HomeController_viewImg1_invoker.call(
          req => HomeController_11.viewImg(img, req))
      }
  
    // @LINE:14
    case controllers_HomeController_showPdf2_route(params@_) =>
      call(params.fromPath[String]("fileNamePdf", None)) { (fileNamePdf) =>
        controllers_HomeController_showPdf2_invoker.call(
          req => HomeController_11.showPdf(fileNamePdf, req))
      }
  
    // @LINE:15
    case controllers_HomeController_showPdfManuales3_route(params@_) =>
      call(params.fromPath[String]("fileNamePdf", None)) { (fileNamePdf) =>
        controllers_HomeController_showPdfManuales3_invoker.call(
          req => HomeController_11.showPdfManuales(fileNamePdf, req))
      }
  
    // @LINE:16
    case controllers_HomeController_downloadPDF4_route(params@_) =>
      call { 
        controllers_HomeController_downloadPDF4_invoker.call(
          req => HomeController_11.downloadPDF(req))
      }
  
    // @LINE:17
    case controllers_HomeController_tasasDeFechaAjax5_route(params@_) =>
      call { 
        controllers_HomeController_tasasDeFechaAjax5_invoker.call(
          req => HomeController_11.tasasDeFechaAjax(req))
      }
  
    // @LINE:18
    case controllers_HomeController_sendEmail6_route(params@_) =>
      call { 
        controllers_HomeController_sendEmail6_invoker.call(
          req => HomeController_11.sendEmail(req))
      }
  
    // @LINE:20
    case controllers_HomeController_redirShowPDF7_route(params@_) =>
      call(params.fromPath[String]("fileNamePdf", None), params.fromPath[String]("titulo", None), params.fromPath[String]("url", None)) { (fileNamePdf, titulo, url) =>
        controllers_HomeController_redirShowPDF7_invoker.call(
          req => HomeController_11.redirShowPDF(fileNamePdf, titulo, url, req))
      }
  
    // @LINE:22
    case controllers_HomeController_redirOdoShowPDF8_route(params@_) =>
      call(params.fromPath[String]("fileNamePdf", None), params.fromPath[String]("desde", None), params.fromPath[String]("hasta", None), params.fromPath[String]("url", None)) { (fileNamePdf, desde, hasta, url) =>
        controllers_HomeController_redirOdoShowPDF8_invoker.call(
          req => HomeController_11.redirOdoShowPDF(fileNamePdf, desde, hasta, url, req))
      }
  
    // @LINE:30
    case controllers_MnuRedimensionar_redimensionarAsignaBodega9_route(params@_) =>
      call { 
        controllers_MnuRedimensionar_redimensionarAsignaBodega9_invoker.call(
          req => MnuRedimensionar_1.redimensionarAsignaBodega(req))
      }
  
    // @LINE:31
    case controllers_MnuRedimensionar_redimensionarAsignaBodegaUpdate10_route(params@_) =>
      call { 
        controllers_MnuRedimensionar_redimensionarAsignaBodegaUpdate10_invoker.call(
          req => MnuRedimensionar_1.redimensionarAsignaBodegaUpdate(req))
      }
  
    // @LINE:33
    case controllers_MnuRedimensionar_redimensionarPrepara11_route(params@_) =>
      call { 
        controllers_MnuRedimensionar_redimensionarPrepara11_invoker.call(
          req => MnuRedimensionar_1.redimensionarPrepara(req))
      }
  
    // @LINE:34
    case controllers_MnuRedimensionar_redimensionarNuevo12_route(params@_) =>
      call { 
        controllers_MnuRedimensionar_redimensionarNuevo12_invoker.call(
          req => MnuRedimensionar_1.redimensionarNuevo(req))
      }
  
    // @LINE:35
    case controllers_MnuRedimensionar_redimensionarConfirmar013_route(params@_) =>
      call { 
        controllers_MnuRedimensionar_redimensionarConfirmar013_invoker.call(
          req => MnuRedimensionar_1.redimensionarConfirmar0(req))
      }
  
    // @LINE:36
    case controllers_MnuRedimensionar_redimensionarConfirmar114_route(params@_) =>
      call { 
        controllers_MnuRedimensionar_redimensionarConfirmar114_invoker.call(
          req => MnuRedimensionar_1.redimensionarConfirmar1(req))
      }
  
    // @LINE:37
    case controllers_MnuRedimensionar_redimensionarConfirmar215_route(params@_) =>
      call { 
        controllers_MnuRedimensionar_redimensionarConfirmar215_invoker.call(
          req => MnuRedimensionar_1.redimensionarConfirmar2(req))
      }
  
    // @LINE:39
    case controllers_MnuRedimensionar_redimensionarEliminar016_route(params@_) =>
      call { 
        controllers_MnuRedimensionar_redimensionarEliminar016_invoker.call(
          req => MnuRedimensionar_1.redimensionarEliminar0(req))
      }
  
    // @LINE:40
    case controllers_MnuRedimensionar_redimensionarEliminar117_route(params@_) =>
      call { 
        controllers_MnuRedimensionar_redimensionarEliminar117_invoker.call(
          req => MnuRedimensionar_1.redimensionarEliminar1(req))
      }
  
    // @LINE:41
    case controllers_MnuRedimensionar_redimensionarEliminar218_route(params@_) =>
      call { 
        controllers_MnuRedimensionar_redimensionarEliminar218_invoker.call(
          req => MnuRedimensionar_1.redimensionarEliminar2(req))
      }
  
    // @LINE:43
    case controllers_MnuRedimensionar_redimensionarListar019_route(params@_) =>
      call { 
        controllers_MnuRedimensionar_redimensionarListar019_invoker.call(
          req => MnuRedimensionar_1.redimensionarListar0(req))
      }
  
    // @LINE:44
    case controllers_MnuRedimensionar_redimensionarListar120_route(params@_) =>
      call { 
        controllers_MnuRedimensionar_redimensionarListar120_invoker.call(
          req => MnuRedimensionar_1.redimensionarListar1(req))
      }
  
    // @LINE:52
    case controllers_MnuMovimientos_movValySubePlantilla21_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movValySubePlantilla21_invoker.call(
          req => MnuMovimientos_8.movValySubePlantilla(req))
      }
  
    // @LINE:53
    case controllers_MnuMovimientos_movPlantilla22_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movPlantilla22_invoker.call(
          req => MnuMovimientos_8.movPlantilla(req))
      }
  
    // @LINE:60
    case controllers_MnuCotiOdo_cotiOdoModifyXCampo23_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoModifyXCampo23_invoker.call(
          req => MnuCotiOdo_5.cotiOdoModifyXCampo(req))
      }
  
    // @LINE:61
    case controllers_MnuCotiOdo_cotiOdoIngreso24_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoIngreso24_invoker.call(
          req => MnuCotiOdo_5.cotiOdoIngreso(req))
      }
  
    // @LINE:62
    case controllers_MnuCotiOdo_cotiOdoNuevo25_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoNuevo25_invoker.call(
          req => MnuCotiOdo_5.cotiOdoNuevo(req))
      }
  
    // @LINE:63
    case controllers_MnuCotiOdo_existeNumeroCotiOdoAjax26_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_existeNumeroCotiOdoAjax26_invoker.call(
          req => MnuCotiOdo_5.existeNumeroCotiOdoAjax(req))
      }
  
    // @LINE:64
    case controllers_MnuCotiOdo_cotiOdoModificaJson27_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoModificaJson27_invoker.call(
          req => MnuCotiOdo_5.cotiOdoModificaJson(req))
      }
  
    // @LINE:66
    case controllers_MnuCotiOdo_cotiOdoListaModifica28_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoListaModifica28_invoker.call(
          req => MnuCotiOdo_5.cotiOdoListaModifica(req))
      }
  
    // @LINE:67
    case controllers_MnuCotiOdo_cotiOdoElimina29_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoElimina29_invoker.call(
          req => MnuCotiOdo_5.cotiOdoElimina(req))
      }
  
    // @LINE:68
    case controllers_MnuCotiOdo_cotiOdoModifica30_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoModifica30_invoker.call(
          req => MnuCotiOdo_5.cotiOdoModifica(req))
      }
  
    // @LINE:69
    case controllers_MnuCotiOdo_cotiOdoUpdate31_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoUpdate31_invoker.call(
          req => MnuCotiOdo_5.cotiOdoUpdate(req))
      }
  
    // @LINE:70
    case controllers_MnuCotiOdo_verCotiOdoAjax32_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_verCotiOdoAjax32_invoker.call(
          req => MnuCotiOdo_5.verCotiOdoAjax(req))
      }
  
    // @LINE:72
    case controllers_MnuCotiOdo_cotiOdoListaCambiaEstado33_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoListaCambiaEstado33_invoker.call(
          req => MnuCotiOdo_5.cotiOdoListaCambiaEstado(req))
      }
  
    // @LINE:73
    case controllers_MnuCotiOdo_cambiarCotiOdoEstadoAjax34_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cambiarCotiOdoEstadoAjax34_invoker.call(
          req => MnuCotiOdo_5.cambiarCotiOdoEstadoAjax(req))
      }
  
    // @LINE:75
    case controllers_MnuCotiOdo_cotiOdoListaImprimir35_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoListaImprimir35_invoker.call(
          req => MnuCotiOdo_5.cotiOdoListaImprimir(req))
      }
  
    // @LINE:76
    case controllers_MnuCotiOdo_cotiOdoImprimir36_route(params@_) =>
      call(params.fromPath[Long]("id_cotiOdo", None)) { (id_cotiOdo) =>
        controllers_MnuCotiOdo_cotiOdoImprimir36_invoker.call(
          req => MnuCotiOdo_5.cotiOdoImprimir(req, id_cotiOdo))
      }
  
    // @LINE:77
    case controllers_MnuCotiOdo_cotiOdoExcel37_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoExcel37_invoker.call(
          req => MnuCotiOdo_5.cotiOdoExcel(req))
      }
  
    // @LINE:78
    case controllers_MnuCotiOdo_pdfCotiOdoVta38_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_pdfCotiOdoVta38_invoker.call(
          req => MnuCotiOdo_5.pdfCotiOdoVta(req))
      }
  
    // @LINE:80
    case controllers_MnuCotiOdo_reportCotiOdoSel39_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_reportCotiOdoSel39_invoker.call(
          req => MnuCotiOdo_5.reportCotiOdoSel(req))
      }
  
    // @LINE:81
    case controllers_MnuCotiOdo_reportCotiOdoRpt40_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_reportCotiOdoRpt40_invoker.call(
          req => MnuCotiOdo_5.reportCotiOdoRpt(req))
      }
  
    // @LINE:83
    case controllers_MnuCotiOdo_cotiOdoListaConfirma41_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoListaConfirma41_invoker.call(
          req => MnuCotiOdo_5.cotiOdoListaConfirma(req))
      }
  
    // @LINE:84
    case controllers_MnuCotiOdo_cotiOdoConfirma42_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoConfirma42_invoker.call(
          req => MnuCotiOdo_5.cotiOdoConfirma(req))
      }
  
    // @LINE:86
    case controllers_MnuCotiOdo_otOdoListaCotizaSinOt43_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_otOdoListaCotizaSinOt43_invoker.call(
          req => MnuCotiOdo_5.otOdoListaCotizaSinOt(req))
      }
  
    // @LINE:87
    case controllers_MnuCotiOdo_otOdoHacer44_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_otOdoHacer44_invoker.call(
          req => MnuCotiOdo_5.otOdoHacer(req))
      }
  
    // @LINE:88
    case controllers_MnuCotiOdo_otOdoGrabar45_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_otOdoGrabar45_invoker.call(
          req => MnuCotiOdo_5.otOdoGrabar(req))
      }
  
    // @LINE:90
    case controllers_MnuCotiOdo_otOdoListaEliminar46_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_otOdoListaEliminar46_invoker.call(
          req => MnuCotiOdo_5.otOdoListaEliminar(req))
      }
  
    // @LINE:91
    case controllers_MnuCotiOdo_verOtOdoAjax47_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_verOtOdoAjax47_invoker.call(
          req => MnuCotiOdo_5.verOtOdoAjax(req))
      }
  
    // @LINE:92
    case controllers_MnuCotiOdo_actualFechaEnvioOtOdoAjax48_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_actualFechaEnvioOtOdoAjax48_invoker.call(
          req => MnuCotiOdo_5.actualFechaEnvioOtOdoAjax(req))
      }
  
    // @LINE:93
    case controllers_MnuCotiOdo_actualOperServOtOdoAjax49_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_actualOperServOtOdoAjax49_invoker.call(
          req => MnuCotiOdo_5.actualOperServOtOdoAjax(req))
      }
  
    // @LINE:95
    case controllers_MnuCotiOdo_otOdoElimina50_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_otOdoElimina50_invoker.call(
          req => MnuCotiOdo_5.otOdoElimina(req))
      }
  
    // @LINE:97
    case controllers_MnuCotiOdo_otOdoListaCambiarEstado51_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_otOdoListaCambiarEstado51_invoker.call(
          req => MnuCotiOdo_5.otOdoListaCambiarEstado(req))
      }
  
    // @LINE:98
    case controllers_MnuCotiOdo_cambiarOtOdoEstadoAjax52_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cambiarOtOdoEstadoAjax52_invoker.call(
          req => MnuCotiOdo_5.cambiarOtOdoEstadoAjax(req))
      }
  
    // @LINE:100
    case controllers_MnuCotiOdo_otOdoListaRevisar53_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_otOdoListaRevisar53_invoker.call(
          req => MnuCotiOdo_5.otOdoListaRevisar(req))
      }
  
    // @LINE:101
    case controllers_MnuCotiOdo_revisarOtOdo54_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_revisarOtOdo54_invoker.call(
          req => MnuCotiOdo_5.revisarOtOdo(req))
      }
  
    // @LINE:102
    case controllers_MnuCotiOdo_trazaServicioOtOdoAjax55_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_trazaServicioOtOdoAjax55_invoker.call(
          req => MnuCotiOdo_5.trazaServicioOtOdoAjax(req))
      }
  
    // @LINE:103
    case controllers_MnuCotiOdo_otOdoRevisarExcel56_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_otOdoRevisarExcel56_invoker.call(
          req => MnuCotiOdo_5.otOdoRevisarExcel(req))
      }
  
    // @LINE:105
    case controllers_MnuCotiOdo_reportOtOdoSel57_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_reportOtOdoSel57_invoker.call(
          req => MnuCotiOdo_5.reportOtOdoSel(req))
      }
  
    // @LINE:106
    case controllers_MnuCotiOdo_reportOtOdoRpt58_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_reportOtOdoRpt58_invoker.call(
          req => MnuCotiOdo_5.reportOtOdoRpt(req))
      }
  
    // @LINE:108
    case controllers_MnuCotiOdo_otOdoListaConfirma59_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_otOdoListaConfirma59_invoker.call(
          req => MnuCotiOdo_5.otOdoListaConfirma(req))
      }
  
    // @LINE:109
    case controllers_MnuCotiOdo_otOdoConfirma60_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_otOdoConfirma60_invoker.call(
          req => MnuCotiOdo_5.otOdoConfirma(req))
      }
  
    // @LINE:111
    case controllers_MnuCotiOdo_otOdoListaAgregarOC61_route(params@_) =>
      call(params.fromPath[Long]("year", None)) { (year) =>
        controllers_MnuCotiOdo_otOdoListaAgregarOC61_invoker.call(
          req => MnuCotiOdo_5.otOdoListaAgregarOC(req, year))
      }
  
    // @LINE:112
    case controllers_MnuCotiOdo_grabarOcOdoPdf62_route(params@_) =>
      call(params.fromPath[Long]("id_otOdo", None)) { (id_otOdo) =>
        controllers_MnuCotiOdo_grabarOcOdoPdf62_invoker.call(
          req => MnuCotiOdo_5.grabarOcOdoPdf(req, id_otOdo))
      }
  
    // @LINE:113
    case controllers_MnuCotiOdo_oc_cotiOdoClienteAjax63_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_oc_cotiOdoClienteAjax63_invoker.call(
          req => MnuCotiOdo_5.oc_cotiOdoClienteAjax(req))
      }
  
    // @LINE:115
    case controllers_MnuCotiOdo_otOdoGenerarContrato64_route(params@_) =>
      call(params.fromPath[Long]("year", None)) { (year) =>
        controllers_MnuCotiOdo_otOdoGenerarContrato64_invoker.call(
          req => MnuCotiOdo_5.otOdoGenerarContrato(req, year))
      }
  
    // @LINE:116
    case controllers_MnuCotiOdo_grabarContratoPdfOdo65_route(params@_) =>
      call(params.fromPath[Long]("id_cotiOdo", None)) { (id_cotiOdo) =>
        controllers_MnuCotiOdo_grabarContratoPdfOdo65_invoker.call(
          req => MnuCotiOdo_5.grabarContratoPdfOdo(req, id_cotiOdo))
      }
  
    // @LINE:117
    case controllers_MnuCotiOdo_datosContratoOdo66_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_datosContratoOdo66_invoker.call(
          req => MnuCotiOdo_5.datosContratoOdo(req))
      }
  
    // @LINE:118
    case controllers_MnuCotiOdo_hacerContratoOdo67_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_hacerContratoOdo67_invoker.call(
          req => MnuCotiOdo_5.hacerContratoOdo(req))
      }
  
    // @LINE:120
    case controllers_MnuCotiOdo_cotiOdoPlantilla68_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoPlantilla68_invoker.call(
          req => MnuCotiOdo_5.cotiOdoPlantilla(req))
      }
  
    // @LINE:121
    case controllers_MnuCotiOdo_cotiOdoValidarPlantilla69_route(params@_) =>
      call { 
        controllers_MnuCotiOdo_cotiOdoValidarPlantilla69_invoker.call(
          req => MnuCotiOdo_5.cotiOdoValidarPlantilla(req))
      }
  
    // @LINE:129
    case controllers_MnuCotizar_reportOtSaldos70_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportOtSaldos70_invoker.call(
          req => MnuCotizar_3.reportOtSaldos(req))
      }
  
    // @LINE:130
    case controllers_MnuCotizar_reportOtSaldosExcel71_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportOtSaldosExcel71_invoker.call(
          req => MnuCotizar_3.reportOtSaldosExcel(req))
      }
  
    // @LINE:131
    case controllers_MnuCotizar_listadoOTAjax72_route(params@_) =>
      call { 
        controllers_MnuCotizar_listadoOTAjax72_invoker.call(
          req => MnuCotizar_3.listadoOTAjax(req))
      }
  
    // @LINE:132
    case controllers_MnuCotizar_reportOtSaldosPorOt73_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportOtSaldosPorOt73_invoker.call(
          req => MnuCotizar_3.reportOtSaldosPorOt(req))
      }
  
    // @LINE:133
    case controllers_MnuCotizar_reportOtSaldosPorOtExcel74_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportOtSaldosPorOtExcel74_invoker.call(
          req => MnuCotizar_3.reportOtSaldosPorOtExcel(req))
      }
  
    // @LINE:135
    case controllers_MnuCotizar_otAdminPrecios075_route(params@_) =>
      call { 
        controllers_MnuCotizar_otAdminPrecios075_invoker.call(
          req => MnuCotizar_3.otAdminPrecios0(req))
      }
  
    // @LINE:136
    case controllers_MnuCotizar_otAdminPrecios176_route(params@_) =>
      call { 
        controllers_MnuCotizar_otAdminPrecios176_invoker.call(
          req => MnuCotizar_3.otAdminPrecios1(req))
      }
  
    // @LINE:138
    case controllers_MnuCotizar_otCambiarEstadoTerminadas77_route(params@_) =>
      call { 
        controllers_MnuCotizar_otCambiarEstadoTerminadas77_invoker.call(
          req => MnuCotizar_3.otCambiarEstadoTerminadas(req))
      }
  
    // @LINE:144
    case controllers_MnuCotizar_cotizaListaResumen078_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaResumen078_invoker.call(
          req => MnuCotizar_3.cotizaListaResumen0(req))
      }
  
    // @LINE:145
    case controllers_MnuCotizar_cotizaListaResumen0079_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaResumen0079_invoker.call(
          req => MnuCotizar_3.cotizaListaResumen00(req))
      }
  
    // @LINE:146
    case controllers_MnuCotizar_cotizaListaResumen180_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaResumen180_invoker.call(
          req => MnuCotizar_3.cotizaListaResumen1(req))
      }
  
    // @LINE:147
    case controllers_MnuCotizar_cotizaListaResumen281_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaResumen281_invoker.call(
          req => MnuCotizar_3.cotizaListaResumen2(req))
      }
  
    // @LINE:148
    case controllers_MnuCotizar_cotizaListaResumen2Excel82_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaResumen2Excel82_invoker.call(
          req => MnuCotizar_3.cotizaListaResumen2Excel(req))
      }
  
    // @LINE:149
    case controllers_MnuCotizar_cotizaListaResumen383_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaResumen383_invoker.call(
          req => MnuCotizar_3.cotizaListaResumen3(req))
      }
  
    // @LINE:150
    case controllers_MnuCotizar_cotizaListaBiblioteca84_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaListaBiblioteca84_invoker.call(
          req => MnuCotizar_3.cotizaListaBiblioteca(req))
      }
  
    // @LINE:151
    case controllers_MnuCotizar_cotiBibliotecaElimina85_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotiBibliotecaElimina85_invoker.call(
          req => MnuCotizar_3.cotiBibliotecaElimina(req))
      }
  
    // @LINE:154
    case controllers_MnuCotizar_reportOtPrecios086_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportOtPrecios086_invoker.call(
          req => MnuCotizar_3.reportOtPrecios0(req))
      }
  
    // @LINE:155
    case controllers_MnuCotizar_reportOtPrecios187_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportOtPrecios187_invoker.call(
          req => MnuCotizar_3.reportOtPrecios1(req))
      }
  
    // @LINE:161
    case controllers_MnuTablas_comercialMantencion88_route(params@_) =>
      call { 
        controllers_MnuTablas_comercialMantencion88_invoker.call(
          req => MnuTablas_2.comercialMantencion(req))
      }
  
    // @LINE:162
    case controllers_MnuTablas_modVigComercialAjax89_route(params@_) =>
      call { 
        controllers_MnuTablas_modVigComercialAjax89_invoker.call(
          req => MnuTablas_2.modVigComercialAjax(req))
      }
  
    // @LINE:163
    case controllers_MnuTablas_comercialAgrega90_route(params@_) =>
      call { 
        controllers_MnuTablas_comercialAgrega90_invoker.call(
          req => MnuTablas_2.comercialAgrega(req))
      }
  
    // @LINE:164
    case controllers_MnuTablas_precioMantSelSucursal91_route(params@_) =>
      call { 
        controllers_MnuTablas_precioMantSelSucursal91_invoker.call(
          req => MnuTablas_2.precioMantSelSucursal(req))
      }
  
    // @LINE:166
    case controllers_MnuTablas_equipoMantencionExcel92_route(params@_) =>
      call { 
        controllers_MnuTablas_equipoMantencionExcel92_invoker.call(
          req => MnuTablas_2.equipoMantencionExcel(req))
      }
  
    // @LINE:172
    case controllers_MnuTablas_dibujanteMantencion93_route(params@_) =>
      call { 
        controllers_MnuTablas_dibujanteMantencion93_invoker.call(
          req => MnuTablas_2.dibujanteMantencion(req))
      }
  
    // @LINE:173
    case controllers_MnuTablas_dibujanteAgrega94_route(params@_) =>
      call { 
        controllers_MnuTablas_dibujanteAgrega94_invoker.call(
          req => MnuTablas_2.dibujanteAgrega(req))
      }
  
    // @LINE:174
    case controllers_MnuTablas_dibujanteGraba95_route(params@_) =>
      call { 
        controllers_MnuTablas_dibujanteGraba95_invoker.call(
          req => MnuTablas_2.dibujanteGraba(req))
      }
  
    // @LINE:175
    case controllers_MnuTablas_propiedadElimina96_route(params@_) =>
      call { 
        controllers_MnuTablas_propiedadElimina96_invoker.call(
          req => MnuTablas_2.propiedadElimina(req))
      }
  
    // @LINE:176
    case controllers_MnuTablas_dibujanteModifica97_route(params@_) =>
      call { 
        controllers_MnuTablas_dibujanteModifica97_invoker.call(
          req => MnuTablas_2.dibujanteModifica(req))
      }
  
    // @LINE:177
    case controllers_MnuTablas_modificaDibujantePorCampoAjax98_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaDibujantePorCampoAjax98_invoker.call(
          req => MnuTablas_2.modificaDibujantePorCampoAjax(req))
      }
  
    // @LINE:183
    case controllers_MnuTablas_propiedadMantencion99_route(params@_) =>
      call { 
        controllers_MnuTablas_propiedadMantencion99_invoker.call(
          req => MnuTablas_2.propiedadMantencion(req))
      }
  
    // @LINE:184
    case controllers_MnuTablas_propiedadAgrega100_route(params@_) =>
      call { 
        controllers_MnuTablas_propiedadAgrega100_invoker.call(
          req => MnuTablas_2.propiedadAgrega(req))
      }
  
    // @LINE:185
    case controllers_MnuTablas_propiedadGraba101_route(params@_) =>
      call { 
        controllers_MnuTablas_propiedadGraba101_invoker.call(
          req => MnuTablas_2.propiedadGraba(req))
      }
  
    // @LINE:186
    case controllers_MnuTablas_propiedadModifica102_route(params@_) =>
      call { 
        controllers_MnuTablas_propiedadModifica102_invoker.call(
          req => MnuTablas_2.propiedadModifica(req))
      }
  
    // @LINE:187
    case controllers_MnuTablas_modificaPropiedadPorCampoAjax103_route(params@_) =>
      call { 
        controllers_MnuTablas_modificaPropiedadPorCampoAjax103_invoker.call(
          req => MnuTablas_2.modificaPropiedadPorCampoAjax(req))
      }
  
    // @LINE:193
    case controllers_MnuBodegas_rubroMantencion104_route(params@_) =>
      call { 
        controllers_MnuBodegas_rubroMantencion104_invoker.call(
          req => MnuBodegas_12.rubroMantencion(req))
      }
  
    // @LINE:194
    case controllers_MnuBodegas_rubroAgrega105_route(params@_) =>
      call { 
        controllers_MnuBodegas_rubroAgrega105_invoker.call(
          req => MnuBodegas_12.rubroAgrega(req))
      }
  
    // @LINE:195
    case controllers_MnuBodegas_rubroGraba106_route(params@_) =>
      call { 
        controllers_MnuBodegas_rubroGraba106_invoker.call(
          req => MnuBodegas_12.rubroGraba(req))
      }
  
    // @LINE:196
    case controllers_MnuBodegas_rubroModifica107_route(params@_) =>
      call { 
        controllers_MnuBodegas_rubroModifica107_invoker.call(
          req => MnuBodegas_12.rubroModifica(req))
      }
  
    // @LINE:197
    case controllers_MnuBodegas_modificaRubroPorCampoAjax108_route(params@_) =>
      call { 
        controllers_MnuBodegas_modificaRubroPorCampoAjax108_invoker.call(
          req => MnuBodegas_12.modificaRubroPorCampoAjax(req))
      }
  
    // @LINE:203
    case controllers_MnuCompras_movCompras0109_route(params@_) =>
      call { 
        controllers_MnuCompras_movCompras0109_invoker.call(
          req => MnuCompras_4.movCompras0(req))
      }
  
    // @LINE:204
    case controllers_MnuCompras_movCompras1110_route(params@_) =>
      call { 
        controllers_MnuCompras_movCompras1110_invoker.call(
          req => MnuCompras_4.movCompras1(req))
      }
  
    // @LINE:205
    case controllers_MnuCompras_movCompras1Excel111_route(params@_) =>
      call { 
        controllers_MnuCompras_movCompras1Excel111_invoker.call(
          req => MnuCompras_4.movCompras1Excel(req))
      }
  
    // @LINE:208
    case controllers_MnuCompras_compraVistaSelBodDestAjax112_route(params@_) =>
      call { 
        controllers_MnuCompras_compraVistaSelBodDestAjax112_invoker.call(
          req => MnuCompras_4.compraVistaSelBodDestAjax(req))
      }
  
    // @LINE:209
    case controllers_MnuCompras_compraVistaSelBodDestAjax2113_route(params@_) =>
      call { 
        controllers_MnuCompras_compraVistaSelBodDestAjax2113_invoker.call(
          req => MnuCompras_4.compraVistaSelBodDestAjax2(req))
      }
  
    // @LINE:211
    case controllers_MnuCompras_compraPlantilla114_route(params@_) =>
      call { 
        controllers_MnuCompras_compraPlantilla114_invoker.call(
          req => MnuCompras_4.compraPlantilla(req))
      }
  
    // @LINE:212
    case controllers_MnuCompras_compraCargaPlantilla115_route(params@_) =>
      call { 
        controllers_MnuCompras_compraCargaPlantilla115_invoker.call(
          req => MnuCompras_4.compraCargaPlantilla(req))
      }
  
    // @LINE:213
    case controllers_MnuCompras_compraValidarPlantilla116_route(params@_) =>
      call { 
        controllers_MnuCompras_compraValidarPlantilla116_invoker.call(
          req => MnuCompras_4.compraValidarPlantilla(req))
      }
  
    // @LINE:219
    case controllers_MnuTablas_precioPlantilla117_route(params@_) =>
      call { 
        controllers_MnuTablas_precioPlantilla117_invoker.call(
          req => MnuTablas_2.precioPlantilla(req))
      }
  
    // @LINE:220
    case controllers_MnuTablas_precioCargaPlantilla118_route(params@_) =>
      call { 
        controllers_MnuTablas_precioCargaPlantilla118_invoker.call(
          req => MnuTablas_2.precioCargaPlantilla(req))
      }
  
    // @LINE:227
    case controllers_MnuReportes_reporteExcedentesListaEquipos119_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteExcedentesListaEquipos119_invoker.call(
          req => MnuReportes_6.reporteExcedentesListaEquipos(req))
      }
  
    // @LINE:228
    case controllers_MnuReportes_reporteExcedentesDetalleEquipo120_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteExcedentesDetalleEquipo120_invoker.call(
          req => MnuReportes_6.reporteExcedentesDetalleEquipo(req))
      }
  
    // @LINE:229
    case controllers_MnuReportes_reporteExcedentesDetalleEquipoExcel121_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteExcedentesDetalleEquipoExcel121_invoker.call(
          req => MnuReportes_6.reporteExcedentesDetalleEquipoExcel(req))
      }
  
    // @LINE:231
    case controllers_MnuTablas_proyectoMantencionExcel122_route(params@_) =>
      call { 
        controllers_MnuTablas_proyectoMantencionExcel122_invoker.call(
          req => MnuTablas_2.proyectoMantencionExcel(req))
      }
  
    // @LINE:232
    case controllers_MnuTablas_equipoCambiaEstado123_route(params@_) =>
      call { 
        controllers_MnuTablas_equipoCambiaEstado123_invoker.call(
          req => MnuTablas_2.equipoCambiaEstado(req))
      }
  
    // @LINE:233
    case controllers_MnuTablas_equipoCambiaPropiedad124_route(params@_) =>
      call { 
        controllers_MnuTablas_equipoCambiaPropiedad124_invoker.call(
          req => MnuTablas_2.equipoCambiaPropiedad(req))
      }
  
    // @LINE:235
    case controllers_HomeController_homeOdoAutorizaVentasWebExcel125_route(params@_) =>
      call { 
        controllers_HomeController_homeOdoAutorizaVentasWebExcel125_invoker.call(
          req => HomeController_11.homeOdoAutorizaVentasWebExcel(req))
      }
  
    // @LINE:243
    case controllers_MnuCotizar_coti8columnas126_route(params@_) =>
      call { 
        controllers_MnuCotizar_coti8columnas126_invoker.call(
          req => MnuCotizar_3.coti8columnas(req))
      }
  
    // @LINE:244
    case controllers_MnuCotizar_coti8columnasValida127_route(params@_) =>
      call { 
        controllers_MnuCotizar_coti8columnasValida127_invoker.call(
          req => MnuCotizar_3.coti8columnasValida(req))
      }
  
    // @LINE:247
    case controllers_MnuCotizarSelect_cotizaIngresoSelect128_route(params@_) =>
      call { 
        controllers_MnuCotizarSelect_cotizaIngresoSelect128_invoker.call(
          req => MnuCotizarSelect_7.cotizaIngresoSelect(req))
      }
  
    // @LINE:248
    case controllers_MnuCotizarSelect_cotizaSeleccionados129_route(params@_) =>
      call { 
        controllers_MnuCotizarSelect_cotizaSeleccionados129_invoker.call(
          req => MnuCotizarSelect_7.cotizaSeleccionados(req))
      }
  
    // @LINE:249
    case controllers_MnuCotizarSelect_cotiValidarPlantillaSel130_route(params@_) =>
      call { 
        controllers_MnuCotizarSelect_cotiValidarPlantillaSel130_invoker.call(
          req => MnuCotizarSelect_7.cotiValidarPlantillaSel(req))
      }
  
    // @LINE:250
    case controllers_MnuCotizarSelect_cotizaModificaJsonSel131_route(params@_) =>
      call { 
        controllers_MnuCotizarSelect_cotizaModificaJsonSel131_invoker.call(
          req => MnuCotizarSelect_7.cotizaModificaJsonSel(req))
      }
  
    // @LINE:251
    case controllers_MnuCotizarSelect_actualizaListaCotiSelAjax132_route(params@_) =>
      call { 
        controllers_MnuCotizarSelect_actualizaListaCotiSelAjax132_invoker.call(
          req => MnuCotizarSelect_7.actualizaListaCotiSelAjax(req))
      }
  
    // @LINE:254
    case controllers_MnuCotizarSelect_cotizaListaModificaSelPeriodo133_route(params@_) =>
      call { 
        controllers_MnuCotizarSelect_cotizaListaModificaSelPeriodo133_invoker.call(
          req => MnuCotizarSelect_7.cotizaListaModificaSelPeriodo(req))
      }
  
    // @LINE:255
    case controllers_MnuCotizarSelect_cotizaListaModificaSel134_route(params@_) =>
      call { 
        controllers_MnuCotizarSelect_cotizaListaModificaSel134_invoker.call(
          req => MnuCotizarSelect_7.cotizaListaModificaSel(req))
      }
  
    // @LINE:256
    case controllers_MnuCotizarSelect_cotizaModificaSel135_route(params@_) =>
      call { 
        controllers_MnuCotizarSelect_cotizaModificaSel135_invoker.call(
          req => MnuCotizarSelect_7.cotizaModificaSel(req))
      }
  
    // @LINE:258
    case controllers_MnuCotizar_reportPipelineSelRes136_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportPipelineSelRes136_invoker.call(
          req => MnuCotizar_3.reportPipelineSelRes(req))
      }
  
    // @LINE:259
    case controllers_MnuCotizar_reportPipelineRptRes137_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportPipelineRptRes137_invoker.call(
          req => MnuCotizar_3.reportPipelineRptRes(req))
      }
  
    // @LINE:260
    case controllers_MnuCotizar_reportPipelineDetalle138_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportPipelineDetalle138_invoker.call(
          req => MnuCotizar_3.reportPipelineDetalle(req))
      }
  
    // @LINE:261
    case controllers_MnuCotizar_reportPipelineRptResExcel139_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportPipelineRptResExcel139_invoker.call(
          req => MnuCotizar_3.reportPipelineRptResExcel(req))
      }
  
    // @LINE:263
    case controllers_MnuCotizar_reportPipelineSelDet140_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportPipelineSelDet140_invoker.call(
          req => MnuCotizar_3.reportPipelineSelDet(req))
      }
  
    // @LINE:264
    case controllers_MnuCotizar_reportPipelineRptDet141_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportPipelineRptDet141_invoker.call(
          req => MnuCotizar_3.reportPipelineRptDet(req))
      }
  
    // @LINE:265
    case controllers_MnuCotizar_reportPipelineRptDetExcel142_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportPipelineRptDetExcel142_invoker.call(
          req => MnuCotizar_3.reportPipelineRptDetExcel(req))
      }
  
    // @LINE:267
    case controllers_MnuCotizar_reportPipelineSelComercial143_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportPipelineSelComercial143_invoker.call(
          req => MnuCotizar_3.reportPipelineSelComercial(req))
      }
  
    // @LINE:268
    case controllers_MnuCotizar_reportPipelineRptComercial144_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportPipelineRptComercial144_invoker.call(
          req => MnuCotizar_3.reportPipelineRptComercial(req))
      }
  
    // @LINE:269
    case controllers_MnuCotizar_reportPipelineRptComercialExcel145_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportPipelineRptComercialExcel145_invoker.call(
          req => MnuCotizar_3.reportPipelineRptComercialExcel(req))
      }
  
    // @LINE:272
    case controllers_MnuCotizar_otListarDespachosPeriodo146_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListarDespachosPeriodo146_invoker.call(
          req => MnuCotizar_3.otListarDespachosPeriodo(req))
      }
  
    // @LINE:273
    case controllers_MnuCotizar_otListarDespachos147_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListarDespachos147_invoker.call(
          req => MnuCotizar_3.otListarDespachos(req))
      }
  
    // @LINE:274
    case controllers_MnuCotizar_otDetalleDespacho148_route(params@_) =>
      call { 
        controllers_MnuCotizar_otDetalleDespacho148_invoker.call(
          req => MnuCotizar_3.otDetalleDespacho(req))
      }
  
    // @LINE:276
    case controllers_MnuCotizar_otListarDespachosImgPeriodo149_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListarDespachosImgPeriodo149_invoker.call(
          req => MnuCotizar_3.otListarDespachosImgPeriodo(req))
      }
  
    // @LINE:277
    case controllers_MnuCotizar_otListarDespachosImg150_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListarDespachosImg150_invoker.call(
          req => MnuCotizar_3.otListarDespachosImg(req))
      }
  
    // @LINE:278
    case controllers_MnuCotizar_otDetalleDespachoImg151_route(params@_) =>
      call { 
        controllers_MnuCotizar_otDetalleDespachoImg151_invoker.call(
          req => MnuCotizar_3.otDetalleDespachoImg(req))
      }
  
    // @LINE:280
    case controllers_MnuCotizar_otListaRevisarPeriodo152_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaRevisarPeriodo152_invoker.call(
          req => MnuCotizar_3.otListaRevisarPeriodo(req))
      }
  
    // @LINE:281
    case controllers_MnuCotizar_otListaRevisar153_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaRevisar153_invoker.call(
          req => MnuCotizar_3.otListaRevisar(req))
      }
  
    // @LINE:282
    case controllers_MnuCotizar_otListaRevisarExcel154_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaRevisarExcel154_invoker.call(
          req => MnuCotizar_3.otListaRevisarExcel(req))
      }
  
    // @LINE:283
    case controllers_MnuCotizar_revisarOt155_route(params@_) =>
      call { 
        controllers_MnuCotizar_revisarOt155_invoker.call(
          req => MnuCotizar_3.revisarOt(req))
      }
  
    // @LINE:284
    case controllers_MnuCotizar_trazaEqOtAjax156_route(params@_) =>
      call { 
        controllers_MnuCotizar_trazaEqOtAjax156_invoker.call(
          req => MnuCotizar_3.trazaEqOtAjax(req))
      }
  
    // @LINE:285
    case controllers_MnuCotizar_otRevisarExcel157_route(params@_) =>
      call { 
        controllers_MnuCotizar_otRevisarExcel157_invoker.call(
          req => MnuCotizar_3.otRevisarExcel(req))
      }
  
    // @LINE:287
    case controllers_MnuCotizar_reportOtSel158_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportOtSel158_invoker.call(
          req => MnuCotizar_3.reportOtSel(req))
      }
  
    // @LINE:288
    case controllers_MnuCotizar_reportOtRpt159_route(params@_) =>
      call { 
        controllers_MnuCotizar_reportOtRpt159_invoker.call(
          req => MnuCotizar_3.reportOtRpt(req))
      }
  
    // @LINE:290
    case controllers_MnuCotizar_otGenerarContratoPeriodo160_route(params@_) =>
      call { 
        controllers_MnuCotizar_otGenerarContratoPeriodo160_invoker.call(
          req => MnuCotizar_3.otGenerarContratoPeriodo(req))
      }
  
    // @LINE:291
    case controllers_MnuCotizar_otGenerarContrato161_route(params@_) =>
      call { 
        controllers_MnuCotizar_otGenerarContrato161_invoker.call(
          req => MnuCotizar_3.otGenerarContrato(req))
      }
  
    // @LINE:292
    case controllers_MnuCotizar_grabarContratoPdf162_route(params@_) =>
      call(params.fromPath[Long]("id_coti", None)) { (id_coti) =>
        controllers_MnuCotizar_grabarContratoPdf162_invoker.call(
          req => MnuCotizar_3.grabarContratoPdf(req, id_coti))
      }
  
    // @LINE:293
    case controllers_MnuCotizar_datosContrato163_route(params@_) =>
      call { 
        controllers_MnuCotizar_datosContrato163_invoker.call(
          req => MnuCotizar_3.datosContrato(req))
      }
  
    // @LINE:294
    case controllers_MnuCotizar_hacerContrato164_route(params@_) =>
      call { 
        controllers_MnuCotizar_hacerContrato164_invoker.call(
          req => MnuCotizar_3.hacerContrato(req))
      }
  
    // @LINE:296
    case controllers_MnuCotizar_otListaAgregarOCPeriodo165_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaAgregarOCPeriodo165_invoker.call(
          req => MnuCotizar_3.otListaAgregarOCPeriodo(req))
      }
  
    // @LINE:297
    case controllers_MnuCotizar_otListaAgregarOC166_route(params@_) =>
      call { 
        controllers_MnuCotizar_otListaAgregarOC166_invoker.call(
          req => MnuCotizar_3.otListaAgregarOC(req))
      }
  
    // @LINE:298
    case controllers_MnuCotizar_grabarOcPdf167_route(params@_) =>
      call(params.fromPath[Long]("id_ot", None), params.fromPath[String]("de", None), params.fromPath[String]("a", None)) { (id_ot, de, a) =>
        controllers_MnuCotizar_grabarOcPdf167_invoker.call(
          req => MnuCotizar_3.grabarOcPdf(req, id_ot, de, a))
      }
  
    // @LINE:299
    case controllers_MnuCotizar_oc_cotiClienteAjax168_route(params@_) =>
      call { 
        controllers_MnuCotizar_oc_cotiClienteAjax168_invoker.call(
          req => MnuCotizar_3.oc_cotiClienteAjax(req))
      }
  
    // @LINE:303
    case controllers_MnuCotizar_cotizaSolucionMantencion169_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaSolucionMantencion169_invoker.call(
          req => MnuCotizar_3.cotizaSolucionMantencion(req))
      }
  
    // @LINE:304
    case controllers_MnuCotizar_cotizaSolucionElimina170_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaSolucionElimina170_invoker.call(
          req => MnuCotizar_3.cotizaSolucionElimina(req))
      }
  
    // @LINE:305
    case controllers_MnuCotizar_cotizaSolucionModifica171_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaSolucionModifica171_invoker.call(
          req => MnuCotizar_3.cotizaSolucionModifica(req))
      }
  
    // @LINE:306
    case controllers_MnuCotizar_modificaCotizaSolucionPorCampoAjax172_route(params@_) =>
      call { 
        controllers_MnuCotizar_modificaCotizaSolucionPorCampoAjax172_invoker.call(
          req => MnuCotizar_3.modificaCotizaSolucionPorCampoAjax(req))
      }
  
    // @LINE:307
    case controllers_MnuCotizar_cotizaSolucionAgrega173_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaSolucionAgrega173_invoker.call(
          req => MnuCotizar_3.cotizaSolucionAgrega(req))
      }
  
    // @LINE:308
    case controllers_MnuCotizar_cotizaSolucionNuevo174_route(params@_) =>
      call { 
        controllers_MnuCotizar_cotizaSolucionNuevo174_invoker.call(
          req => MnuCotizar_3.cotizaSolucionNuevo(req))
      }
  
    // @LINE:314
    case controllers_MnuOdoAppWeb_reporteMovOdoAutorizador175_route(params@_) =>
      call { 
        controllers_MnuOdoAppWeb_reporteMovOdoAutorizador175_invoker.call(
          req => MnuOdoAppWeb_0.reporteMovOdoAutorizador(req))
      }
  
    // @LINE:315
    case controllers_MnuOdoAppWeb_reporteMovOdoAutorizadorDetalle176_route(params@_) =>
      call { 
        controllers_MnuOdoAppWeb_reporteMovOdoAutorizadorDetalle176_invoker.call(
          req => MnuOdoAppWeb_0.reporteMovOdoAutorizadorDetalle(req))
      }
  
    // @LINE:321
    case controllers_MnuOdo_servicioPreciosServicio177_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioPreciosServicio177_invoker.call(
          req => MnuOdo_9.servicioPreciosServicio(req))
      }
  
    // @LINE:322
    case controllers_MnuOdo_servicioListaPreciosServicio178_route(params@_) =>
      call(params.fromPath[Long]("id_serv", None)) { (id_serv) =>
        controllers_MnuOdo_servicioListaPreciosServicio178_invoker.call(
          req => MnuOdo_9.servicioListaPreciosServicio(id_serv, req))
      }
  
    // @LINE:323
    case controllers_MnuOdo_servicioAgregaPrecioServicio179_route(params@_) =>
      call { 
        controllers_MnuOdo_servicioAgregaPrecioServicio179_invoker.call(
          req => MnuOdo_9.servicioAgregaPrecioServicio(req))
      }
  
    // @LINE:331
    case controllers_MnuReportes_reportePorProyectoAgrupado180_route(params@_) =>
      call { 
        controllers_MnuReportes_reportePorProyectoAgrupado180_invoker.call(
          req => MnuReportes_6.reportePorProyectoAgrupado(req))
      }
  
    // @LINE:332
    case controllers_MnuReportes_reportePorProyectoListaAgrupado181_route(params@_) =>
      call { 
        controllers_MnuReportes_reportePorProyectoListaAgrupado181_invoker.call(
          req => MnuReportes_6.reportePorProyectoListaAgrupado(req))
      }
  
    // @LINE:333
    case controllers_MnuReportes_reportePorProyectoDetalleAgrupado182_route(params@_) =>
      call { 
        controllers_MnuReportes_reportePorProyectoDetalleAgrupado182_invoker.call(
          req => MnuReportes_6.reportePorProyectoDetalleAgrupado(req))
      }
  
    // @LINE:334
    case controllers_MnuReportes_reportePorProyectoDetalleAgrupadoExcel183_route(params@_) =>
      call { 
        controllers_MnuReportes_reportePorProyectoDetalleAgrupadoExcel183_invoker.call(
          req => MnuReportes_6.reportePorProyectoDetalleAgrupadoExcel(req))
      }
  
    // @LINE:336
    case controllers_MnuReportes_reportEstadoBodegas184_route(params@_) =>
      call { 
        controllers_MnuReportes_reportEstadoBodegas184_invoker.call(
          req => MnuReportes_6.reportEstadoBodegas(req))
      }
  
    // @LINE:337
    case controllers_MnuReportes_reportEstadoBodegasExcel185_route(params@_) =>
      call { 
        controllers_MnuReportes_reportEstadoBodegasExcel185_invoker.call(
          req => MnuReportes_6.reportEstadoBodegasExcel(req))
      }
  
    // @LINE:340
    case controllers_MnuReportes_envioMasivoListadoProforma2186_route(params@_) =>
      call { 
        controllers_MnuReportes_envioMasivoListadoProforma2186_invoker.call(
          req => MnuReportes_6.envioMasivoListadoProforma2(req))
      }
  
    // @LINE:341
    case controllers_MnuReportes_proformaListaPdf187_route(params@_) =>
      call { 
        controllers_MnuReportes_proformaListaPdf187_invoker.call(
          req => MnuReportes_6.proformaListaPdf(req))
      }
  
    // @LINE:344
    case controllers_MnuReportes_reportGerenKGPorPeriodo0188_route(params@_) =>
      call { 
        controllers_MnuReportes_reportGerenKGPorPeriodo0188_invoker.call(
          req => MnuReportes_6.reportGerenKGPorPeriodo0(req))
      }
  
    // @LINE:345
    case controllers_MnuReportes_reportGerenKGPorPeriodo1189_route(params@_) =>
      call { 
        controllers_MnuReportes_reportGerenKGPorPeriodo1189_invoker.call(
          req => MnuReportes_6.reportGerenKGPorPeriodo1(req))
      }
  
    // @LINE:346
    case controllers_MnuReportes_reportGerenKGPorPeriodo1Excel190_route(params@_) =>
      call { 
        controllers_MnuReportes_reportGerenKGPorPeriodo1Excel190_invoker.call(
          req => MnuReportes_6.reportGerenKGPorPeriodo1Excel(req))
      }
  
    // @LINE:348
    case controllers_MnuReportes_reportVentasPorPeriodo0191_route(params@_) =>
      call { 
        controllers_MnuReportes_reportVentasPorPeriodo0191_invoker.call(
          req => MnuReportes_6.reportVentasPorPeriodo0(req))
      }
  
    // @LINE:349
    case controllers_MnuReportes_reportVentasPorPeriodo1192_route(params@_) =>
      call { 
        controllers_MnuReportes_reportVentasPorPeriodo1192_invoker.call(
          req => MnuReportes_6.reportVentasPorPeriodo1(req))
      }
  
    // @LINE:350
    case controllers_MnuReportes_reportVentasPorPeriodo1Excel193_route(params@_) =>
      call { 
        controllers_MnuReportes_reportVentasPorPeriodo1Excel193_invoker.call(
          req => MnuReportes_6.reportVentasPorPeriodo1Excel(req))
      }
  
    // @LINE:352
    case controllers_MnuReportes_reporteEstadosPer0194_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEstadosPer0194_invoker.call(
          req => MnuReportes_6.reporteEstadosPer0(req))
      }
  
    // @LINE:353
    case controllers_MnuReportes_reporteEstadosPer1195_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEstadosPer1195_invoker.call(
          req => MnuReportes_6.reporteEstadosPer1(req))
      }
  
    // @LINE:354
    case controllers_MnuReportes_reporteEstadosPer1Excel196_route(params@_) =>
      call { 
        controllers_MnuReportes_reporteEstadosPer1Excel196_invoker.call(
          req => MnuReportes_6.reporteEstadosPer1Excel(req))
      }
  
    // @LINE:357
    case controllers_MnuReportes_reportePorProyectoValorizado197_route(params@_) =>
      call { 
        controllers_MnuReportes_reportePorProyectoValorizado197_invoker.call(
          req => MnuReportes_6.reportePorProyectoValorizado(req))
      }
  
    // @LINE:358
    case controllers_MnuReportes_reportePorProyectoListaValorizado198_route(params@_) =>
      call { 
        controllers_MnuReportes_reportePorProyectoListaValorizado198_invoker.call(
          req => MnuReportes_6.reportePorProyectoListaValorizado(req))
      }
  
    // @LINE:364
    case controllers_MnuBodegas_sucursalAdministrar199_route(params@_) =>
      call { 
        controllers_MnuBodegas_sucursalAdministrar199_invoker.call(
          req => MnuBodegas_12.sucursalAdministrar(req))
      }
  
    // @LINE:365
    case controllers_MnuBodegas_sucursalElimina200_route(params@_) =>
      call { 
        controllers_MnuBodegas_sucursalElimina200_invoker.call(
          req => MnuBodegas_12.sucursalElimina(req))
      }
  
    // @LINE:366
    case controllers_MnuBodegas_sucursalModifica201_route(params@_) =>
      call { 
        controllers_MnuBodegas_sucursalModifica201_invoker.call(
          req => MnuBodegas_12.sucursalModifica(req))
      }
  
    // @LINE:367
    case controllers_MnuBodegas_sucursalAgrega202_route(params@_) =>
      call { 
        controllers_MnuBodegas_sucursalAgrega202_invoker.call(
          req => MnuBodegas_12.sucursalAgrega(req))
      }
  
    // @LINE:368
    case controllers_MnuBodegas_sucursalNuevo203_route(params@_) =>
      call { 
        controllers_MnuBodegas_sucursalNuevo203_invoker.call(
          req => MnuBodegas_12.sucursalNuevo(req))
      }
  
    // @LINE:369
    case controllers_MnuBodegas_modificaSucursalPorCampoAjax204_route(params@_) =>
      call { 
        controllers_MnuBodegas_modificaSucursalPorCampoAjax204_invoker.call(
          req => MnuBodegas_12.modificaSucursalPorCampoAjax(req))
      }
  
    // @LINE:376
    case controllers_MnuMovimientos_movimientoSelectModificar2205_route(params@_) =>
      call { 
        controllers_MnuMovimientos_movimientoSelectModificar2205_invoker.call(
          req => MnuMovimientos_8.movimientoSelectModificar2(req))
      }
  
    // @LINE:377
    case controllers_MnuTablas_clienteMantencion2206_route(params@_) =>
      call { 
        controllers_MnuTablas_clienteMantencion2206_invoker.call(
          req => MnuTablas_2.clienteMantencion2(req))
      }
  
    // @LINE:378
    case controllers_MnuTablas_clienteCambiaEstado207_route(params@_) =>
      call { 
        controllers_MnuTablas_clienteCambiaEstado207_invoker.call(
          req => MnuTablas_2.clienteCambiaEstado(req))
      }
  
    // @LINE:382
    case controllers_MnuReportes_domStockDiarioExcel0208_route(params@_) =>
      call { 
        controllers_MnuReportes_domStockDiarioExcel0208_invoker.call(
          req => MnuReportes_6.domStockDiarioExcel0(req))
      }
  
    // @LINE:383
    case controllers_MnuReportes_domStockDiarioExcel1209_route(params@_) =>
      call { 
        controllers_MnuReportes_domStockDiarioExcel1209_invoker.call(
          req => MnuReportes_6.domStockDiarioExcel1(req))
      }
  }
}
