
package views.html

import _root_.play.twirl.api.TwirlFeatureImports.*
import _root_.play.twirl.api.TwirlHelperImports.*
import scala.language.adhocExtensions
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._
import scala.jdk.CollectionConverters._

object menues extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, marcar: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),format.raw/*3.1*/("""<!DOCTYPE html>


<style>
	.dropdown-submenu """),format.raw/*7.20*/("""{"""),format.raw/*7.21*/("""
	  """),format.raw/*8.4*/("""position: relative;
	"""),format.raw/*9.2*/("""}"""),format.raw/*9.3*/("""
	"""),format.raw/*10.2*/(""".dropdown-submenu a::after """),format.raw/*10.29*/("""{"""),format.raw/*10.30*/("""
	  """),format.raw/*11.4*/("""transform: rotate(-90deg);
	  position: absolute;
	  right: 6px;
	  top: .8em;
	"""),format.raw/*15.2*/("""}"""),format.raw/*15.3*/("""
	"""),format.raw/*16.2*/(""".dropdown-submenu .dropdown-menu """),format.raw/*16.35*/("""{"""),format.raw/*16.36*/("""
	  """),format.raw/*17.4*/("""top: 0;
	  left: 100%;
	  margin-left: .1rem;
	  margin-right: .1rem;
	"""),format.raw/*21.2*/("""}"""),format.raw/*21.3*/("""
	"""),format.raw/*22.2*/(""".dropdown-submenu.pull-left > .dropdown-menu """),format.raw/*22.47*/("""{"""),format.raw/*22.48*/("""
	  """),format.raw/*23.4*/("""left: -100%;
	  margin-left: 10px;
	  -webkit-border-radius: 6px 0 6px 6px;
	     -moz-border-radius: 6px 0 6px 6px;
	          border-radius: 6px 0 6px 6px;
	"""),format.raw/*28.2*/("""}"""),format.raw/*28.3*/("""
"""),format.raw/*29.1*/("""</style>
    

   

<nav class="navbar navbar-expand-lg navbar-dark border" style="background-color: rgb(49, 106, 136);">
	<a class="navbar-brand" href="/home/"><img src="/assets/images/favicon.png" width="25px" height="auto"></a>
  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
  	</button>
 	<div class="collapse navbar-collapse" id="navbarSupportedContent">
 		<ul class="navbar-nav mr-auto">
			"""),_display_(if(userMnu.getId_usuario().toString().equals("0"))/*41.55*/{_display_(Seq[Any](format.raw/*41.56*/("""
				"""),format.raw/*42.5*/("""<li class="nav-item active">
       				<a class="nav-link" href="/inicioCPanelHome/">cPanel<span class="sr-only">(current)</span></a>
       			</li>
       		""")))} else {null} ),format.raw/*45.11*/("""
       			"""),format.raw/*46.11*/("""<li class="nav-item active">
       				<a class="nav-link" href="/home/">"""),_display_(/*47.47*/marcar),format.raw/*47.53*/(""" """),format.raw/*47.54*/("""Home<span class="sr-only">(current)</span></a>
       			</li>
       		
      		&nbsp;
      		<li class="divider-vertical border"></li>
      		
      		
      		
      		
      		
      		
      		
      		<!-- =================================== -->
	        <!-- 			MENU COTI ODO    		 -->  
	        <!-- =================================== -->
 			"""),_display_(if(mapPermiso.get("mnuCOTIODO")!=null && mapPermiso.get("parametro.mnuCOTIODO")!=null && mapPermiso.get("parametro.mnuCOTIODO").equals("1"))/*62.146*/{_display_(Seq[Any](format.raw/*62.147*/("""
	 			"""),format.raw/*63.6*/("""<li class="nav-item dropdown">
					<a id="mnuCOTIODO" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	CotiOdo
		        	</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
						"""),_display_(if(mapPermiso.get("cotiOdoImprime")!=null)/*68.50*/{_display_(Seq[Any](format.raw/*68.51*/("""
							"""),format.raw/*69.8*/("""<li class="dropdown-submenu">
								<a class="dropdown-item dropdown-toggle" href="#">Cotizaciones Odo</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("cotiOdoIngreso")!=null)/*72.53*/{_display_(Seq[Any](format.raw/*72.54*/("""
					        			"""),format.raw/*73.17*/("""<li><a class="dropdown-item" href="/routes2/cotiOdoIngreso/">Hacer Coti Odo</a></li>
									""")))} else {null} ),format.raw/*74.11*/("""
									"""),_display_(if(mapPermiso.get("cotiOdoModifica")!=null)/*75.54*/{_display_(Seq[Any](format.raw/*75.55*/("""
										"""),format.raw/*76.11*/("""<li><a class="dropdown-item" href="/routes2/cotiOdoListaModifica/">Modificar Coti Odo</a></li>
									""")))} else {null} ),format.raw/*77.11*/("""
									
									"""),_display_(if(mapPermiso.get("cotiOdoCambiarEstado")!=null)/*79.59*/{_display_(Seq[Any](format.raw/*79.60*/("""
										"""),format.raw/*80.11*/("""<a class="dropdown-item" href="/routes2/cotiOdoListaCambiaEstado/">Cambiar Estado Coti Odo</a>
									""")))} else {null} ),format.raw/*81.11*/("""
									
									"""),_display_(if(mapPermiso.get("cotiOdoImprime")!=null)/*83.53*/{_display_(Seq[Any](format.raw/*83.54*/("""
										"""),format.raw/*84.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href="/routes2/cotiOdoListaImprimir/">Imprimir Coti Odo</a></li>
										<li><a class="dropdown-item" href="/routes2/reportCotiOdoSel/">Reporte Movil Coti Odo</a></li>
									""")))} else {null} ),format.raw/*87.11*/("""
									
									"""),_display_(if(mapPermiso.get("cotiOdoConfirma")!=null)/*89.54*/{_display_(Seq[Any](format.raw/*89.55*/("""
										"""),format.raw/*90.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href="/routes2/cotiOdoListaConfirma/">Confirma Coti Odo</a></li>
									""")))} else {null} ),format.raw/*92.11*/("""
								"""),format.raw/*93.9*/("""</ul>
							</li>
						""")))} else {null} ),format.raw/*95.8*/("""
						"""),_display_(if(mapPermiso.get("otOdoRevisa")!=null)/*96.47*/{_display_(Seq[Any](format.raw/*96.48*/("""
							"""),format.raw/*97.8*/("""<li class="dropdown-submenu">
								<a class="dropdown-item dropdown-toggle" href="#">"""),_display_(/*98.60*/mapDiccionario/*98.74*/.get("Ordenes_de_trabajo")),format.raw/*98.100*/("""</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("otOdoIngreso")!=null)/*100.51*/{_display_(Seq[Any](format.raw/*100.52*/("""
										"""),format.raw/*101.11*/("""<li><a class="dropdown-item" href="/routes2/otOdoListaCotizaSinOt/">Hacer """),_display_(/*101.86*/mapDiccionario/*101.100*/.get("OT")),format.raw/*101.110*/(""" """),format.raw/*101.111*/("""Odo</a></li>
										<a class="dropdown-item" href="/routes2/otOdoListaEliminar/">Eliminar """),_display_(/*102.82*/mapDiccionario/*102.96*/.get("OT")),format.raw/*102.106*/(""" """),format.raw/*102.107*/("""Odo</a>
										<a class="dropdown-item" href="/routes2/otOdoListaCambiarEstado/">Cambiar Estado de """),_display_(/*103.96*/mapDiccionario/*103.110*/.get("OT")),format.raw/*103.120*/(""" """),format.raw/*103.121*/("""Odo</a>
										<a class="dropdown-item" href="/otEstadoMantencion/">Admin Estados</a>
									""")))} else {null} ),format.raw/*105.11*/("""
									"""),_display_(if(mapPermiso.get("otOdoRevisa")!=null)/*106.50*/{_display_(Seq[Any](format.raw/*106.51*/("""
										"""),format.raw/*107.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<a class="dropdown-item" href="/routes2/otOdoListaRevisar/">Revisar/Imprimir """),_display_(/*108.89*/mapDiccionario/*108.103*/.get("OT")),format.raw/*108.113*/(""" """),format.raw/*108.114*/("""Odo</a>
										<a class="dropdown-item" href="/routes2/reportOtOdoSel/">Reporte Movil de """),_display_(/*109.86*/mapDiccionario/*109.100*/.get("OT")),format.raw/*109.110*/(""" """),format.raw/*109.111*/("""Odo</a>
									""")))} else {null} ),format.raw/*110.11*/("""
									"""),_display_(if(mapPermiso.get("otOdoConfirma")!=null)/*111.52*/{_display_(Seq[Any](format.raw/*111.53*/("""
										"""),format.raw/*112.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<a class="dropdown-item" href="/routes2/otOdoListaConfirma/">Confirma """),_display_(/*113.82*/mapDiccionario/*113.96*/.get("OT")),format.raw/*113.106*/(""" """),format.raw/*113.107*/("""Odo</a>
									""")))} else {null} ),format.raw/*114.11*/("""
								"""),format.raw/*115.9*/("""</ul>
							</li>
						""")))} else {null} ),format.raw/*117.8*/("""
						"""),format.raw/*118.7*/("""<div class="dropdown-divider" style="border-color:black"></div>
						"""),_display_(if(mapPermiso.get("cotiOdoGeneraContrato")!=null && mapPermiso.get("parametro.otGenerarContrato")!=null && mapPermiso.get("parametro.otGenerarContrato").equals("1"))/*119.173*/{_display_(Seq[Any](format.raw/*119.174*/("""
							"""),format.raw/*120.8*/("""<a class="dropdown-item" href="/routes2/otOdoGenerarContrato/0">Generar Contrato Odo</a>
						""")))} else {null} ),format.raw/*121.8*/("""
						"""),_display_(if(mapPermiso.get("otOdoAgregaOC")!=null)/*122.49*/{_display_(Seq[Any](format.raw/*122.50*/("""
							"""),format.raw/*123.8*/("""<a class="dropdown-item" href="/routes2/otOdoListaAgregarOC/0">Agregar OC del Cliente Odo</a>
						""")))} else {null} ),format.raw/*124.8*/("""
		        	"""),format.raw/*125.12*/("""</ul>
		        </li>
			""")))} else {null} ),format.raw/*127.5*/("""
			
      		
      		"""),format.raw/*130.9*/("""<!-- =================================== -->
	        <!-- 			MENU COTIZAR 			 -->  
	        <!-- =================================== -->
 			"""),_display_(if(mapPermiso.get("mnuCOTIZAR")!=null && mapPermiso.get("parametro.mnuCOTIZAR")!=null && mapPermiso.get("parametro.mnuCOTIZAR").equals("1"))/*133.146*/{_display_(Seq[Any](format.raw/*133.147*/("""
	 			"""),format.raw/*134.6*/("""<li class="nav-item dropdown">
					<a id="mnuCOTIZAR" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	Cotizar
		        	</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
						"""),_display_(if(mapPermiso.get("cotizaImprime")!=null)/*139.49*/{_display_(Seq[Any](format.raw/*139.50*/("""
							"""),format.raw/*140.8*/("""<li class="dropdown-submenu">
								<a class="dropdown-item dropdown-toggle" href="#">Cotizaciones</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("cotizaIngreso")!=null)/*143.52*/{_display_(Seq[Any](format.raw/*143.53*/("""
										"""),_display_(if(mapPermiso.get("parametro.cotizaSel")!=null && mapPermiso.get("parametro.cotizaSel").equals("1"))/*144.112*/{_display_(Seq[Any](format.raw/*144.113*/("""
											"""),format.raw/*145.12*/("""<li><a class="dropdown-item" href="/routes2/cotizaIngresoSelect/">Hacer Cotizaci&oacute;n</a></li>
										""")))}else/*146.16*/{_display_(Seq[Any](format.raw/*146.17*/("""
											"""),format.raw/*147.12*/("""<li><a class="dropdown-item" href="/cotizaIngreso/">Hacer Cotizaci&oacute;n</a></li>
										""")))}),format.raw/*148.12*/("""
									""")))} else {null} ),format.raw/*149.11*/("""
									"""),_display_(if(mapPermiso.get("cotizaModifica")!=null)/*150.53*/{_display_(Seq[Any](format.raw/*150.54*/("""
										"""),_display_(if(mapPermiso.get("parametro.cotizaSel")!=null && mapPermiso.get("parametro.cotizaSel").equals("1"))/*151.112*/{_display_(Seq[Any](format.raw/*151.113*/("""
											"""),format.raw/*152.12*/("""<li><a class="dropdown-item" href="/routes2/cotizaListaModificaSelPeriodo/">Modificar Cotizaci&oacute;n</a></li>
										""")))}else/*153.16*/{_display_(Seq[Any](format.raw/*153.17*/("""
											"""),format.raw/*154.12*/("""<li><a class="dropdown-item" href="/cotizaListaModificaPeriodo/">Modificar Cotizaci&oacute;n</a></li>
										""")))}),format.raw/*155.12*/("""
									""")))} else {null} ),format.raw/*156.11*/("""
									"""),_display_(if(mapPermiso.get("cotizaCambiarEstado")!=null)/*157.58*/{_display_(Seq[Any](format.raw/*157.59*/("""
										"""),format.raw/*158.11*/("""<a class="dropdown-item" href="/cotizaListaCambiaEstadoPeriodo/">Cambiar Estado Cotizaciones</a>
									""")))} else {null} ),format.raw/*159.11*/("""
									"""),_display_(if(mapPermiso.get("cotizaImprime")!=null)/*160.52*/{_display_(Seq[Any](format.raw/*160.53*/("""
										"""),format.raw/*161.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href="/cotizaListaImprimirPeriodo/">Imprimir Cotizaciones</a></li>
										<li><a class="dropdown-item" href="/routes2/cotizaListaResumen0/">Hacer Resumen</a></li>
										<li><a class="dropdown-item" href="/routes2/cotizaListaBiblioteca/">Biblioteca Resumenes</a></li>
										<li><a class="dropdown-item" href="/reportCotizaSel/">Reporte Movil Cotizaciones</a></li>
										<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href="/routes2/reportPipelineSelRes/">Pipeline (Resumido)</a></li>
										<li><a class="dropdown-item" href="/routes2/reportPipelineSelDet/">Pipeline (Detallado)</a></li>
										<li><a class="dropdown-item" href="/routes2/reportPipelineSelComercial/">Pipeline Resumen por Comercial</a></li>
									""")))} else {null} ),format.raw/*170.11*/("""
									

									"""),_display_(if(mapPermiso.get("cotizaConfirma")!=null)/*173.53*/{_display_(Seq[Any](format.raw/*173.54*/("""
										"""),format.raw/*174.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href="/cotizaListaConfirma/">Confirma Cotizaciones</a></li>
									""")))} else {null} ),format.raw/*176.11*/("""
								"""),format.raw/*177.9*/("""</ul>
							</li>
						""")))} else {null} ),format.raw/*179.8*/("""
						"""),_display_(if(mapPermiso.get("otRevisa")!=null)/*180.44*/{_display_(Seq[Any](format.raw/*180.45*/("""
							"""),format.raw/*181.8*/("""<li class="dropdown-submenu">
								<a class="dropdown-item dropdown-toggle" href="#">"""),_display_(/*182.60*/mapDiccionario/*182.74*/.get("Ordenes_de_trabajo")),format.raw/*182.100*/("""</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("otIngreso")!=null)/*184.48*/{_display_(Seq[Any](format.raw/*184.49*/("""
										"""),format.raw/*185.11*/("""<a class="dropdown-item" href="/otListaCotizaSinOt/">Hacer """),_display_(/*185.71*/mapDiccionario/*185.85*/.get("OT")),format.raw/*185.95*/("""</a>
									""")))} else {null} ),format.raw/*186.11*/("""
									
									"""),_display_(if(mapPermiso.get("otElimina")!=null)/*188.48*/{_display_(Seq[Any](format.raw/*188.49*/("""
										"""),format.raw/*189.11*/("""<a class="dropdown-item" href="/otListaEliminar/">Eliminar """),_display_(/*189.71*/mapDiccionario/*189.85*/.get("OT")),format.raw/*189.95*/("""</a>
									""")))} else {null} ),format.raw/*190.11*/("""
									
									"""),_display_(if(mapPermiso.get("cotizaCambiarEstado")!=null)/*192.58*/{_display_(Seq[Any](format.raw/*192.59*/("""
										"""),format.raw/*193.11*/("""<a class="dropdown-item" href="/otListaCambiarEstadoPeriodo/">Cambiar Estado de """),_display_(/*193.92*/mapDiccionario/*193.106*/.get("OT")),format.raw/*193.116*/("""</a>
									""")))} else {null} ),format.raw/*194.11*/("""
									
									"""),_display_(if(mapPermiso.get("otIngreso")!=null)/*196.48*/{_display_(Seq[Any](format.raw/*196.49*/("""
										"""),format.raw/*197.11*/("""<a class="dropdown-item" href="/otEstadoMantencion/">Admin Estados</a>
										<a class="dropdown-item" href="/routes2/otAdminPrecios0/">Precios por """),_display_(/*198.82*/mapDiccionario/*198.96*/.get("Bodega")),format.raw/*198.110*/(""" """),format.raw/*198.111*/("""("""),_display_(/*198.113*/mapDiccionario/*198.127*/.get("OT")),format.raw/*198.137*/(""")</a>
									""")))} else {null} ),format.raw/*199.11*/("""
									
									"""),_display_(if(mapPermiso.get("otRevisa")!=null)/*201.47*/{_display_(Seq[Any](format.raw/*201.48*/("""
										"""),format.raw/*202.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<a class="dropdown-item" href="/routes2/otListaRevisarPeriodo/">Revisar/Imprimir """),_display_(/*203.93*/mapDiccionario/*203.107*/.get("OT")),format.raw/*203.117*/("""</a>
										<a class="dropdown-item" href="/routes2/reportOtSel/">Reporte Movil de """),_display_(/*204.83*/mapDiccionario/*204.97*/.get("OT")),format.raw/*204.107*/("""</a>
										<a class="dropdown-item" href="/routes2/reportOtSaldos/">Saldos por despachar (Agrupado)</a>
										<a class="dropdown-item" href="/routes2/reportOtSaldosPorOt/">Saldos por despachar (Por """),_display_(/*206.100*/mapDiccionario/*206.114*/.get("OT")),format.raw/*206.124*/(""")</a>
										<a class="dropdown-item" href="/routes2/reportOtPrecios0/">Revisar """),_display_(/*207.79*/mapDiccionario/*207.93*/.get("OT")),format.raw/*207.103*/(""" """),format.raw/*207.104*/("""por """),_display_(/*207.109*/mapDiccionario/*207.123*/.get("Bodega")),format.raw/*207.137*/("""</a>
									""")))} else {null} ),format.raw/*208.11*/("""
									
									"""),_display_(if(mapPermiso.get("otConfirma")!=null)/*210.49*/{_display_(Seq[Any](format.raw/*210.50*/("""
										"""),format.raw/*211.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<a class="dropdown-item" href="/otListaConfirma/">Confirma """),_display_(/*212.71*/mapDiccionario/*212.85*/.get("OT")),format.raw/*212.95*/("""</a>
									""")))} else {null} ),format.raw/*213.11*/("""
								"""),format.raw/*214.9*/("""</ul>
							</li>
						""")))} else {null} ),format.raw/*216.8*/("""
						"""),_display_(if(mapPermiso.get("otListarDespachos")!=null)/*217.53*/{_display_(Seq[Any](format.raw/*217.54*/("""
							"""),format.raw/*218.8*/("""<li class="dropdown-submenu">
								<a class="dropdown-item dropdown-toggle" href="#">Despachos</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("otDespacha")!=null)/*221.49*/{_display_(Seq[Any](format.raw/*221.50*/("""
										"""),format.raw/*222.11*/("""<a class="dropdown-item" href="/otListaDespacharPeriodo/">Despachar</a>
									""")))} else {null} ),format.raw/*223.11*/("""
									"""),_display_(if(mapPermiso.get("otModificarDespacho")!=null)/*224.58*/{_display_(Seq[Any](format.raw/*224.59*/("""
										"""),format.raw/*225.11*/("""<a class="dropdown-item" href="/otListaDespachoModificarPeriodo/">Modificar Despacho</a>
									""")))} else {null} ),format.raw/*226.11*/("""
									"""),_display_(if(mapPermiso.get("otListarDespachos")!=null)/*227.56*/{_display_(Seq[Any](format.raw/*227.57*/("""
										"""),format.raw/*228.11*/("""<li class="dropdown-submenu">
											<a class="dropdown-item dropdown-toggle" href="#">Listar Despachos</a>
											<ul class="dropdown-menu">
													<li><a class="dropdown-item" href="/routes2/otListarDespachosPeriodo/">Sin Imagenes</a></li>
													<li><a class="dropdown-item" href="/routes2/otListarDespachosImgPeriodo/">Con Imagenes</a></li>
											</ul>
										</li>
									""")))} else {null} ),format.raw/*235.11*/("""
								"""),format.raw/*236.9*/("""</ul>
							</li>
						""")))} else {null} ),format.raw/*238.8*/("""
						"""),format.raw/*239.7*/("""<div class="dropdown-divider" style="border-color:black"></div>
						"""),_display_(if(mapPermiso.get("cotizaGeneraContrato")!=null && mapPermiso.get("parametro.otGenerarContrato")!=null && mapPermiso.get("parametro.otGenerarContrato").equals("1"))/*240.172*/{_display_(Seq[Any](format.raw/*240.173*/("""
							"""),format.raw/*241.8*/("""<a class="dropdown-item" href="/routes2/otGenerarContratoPeriodo/">Generar Contrato</a>
						""")))} else {null} ),format.raw/*242.8*/("""
						"""),_display_(if(mapPermiso.get("otAgregaOC")!=null)/*243.46*/{_display_(Seq[Any](format.raw/*243.47*/("""
							"""),format.raw/*244.8*/("""<a class="dropdown-item" href="/routes2/otListaAgregarOCPeriodo/">Agregar OC del Cliente</a>
						""")))} else {null} ),format.raw/*245.8*/("""
		        	"""),format.raw/*246.12*/("""</ul>
		        </li>
			""")))} else {null} ),format.raw/*248.5*/("""
			
			"""),format.raw/*250.4*/("""<!-- =================================== -->
	        <!-- 			MENU COMPRAS 			 -->  
	        <!-- =================================== -->
	        """),_display_(if(mapPermiso.get("mnuCOMPRAS")!=null && mapPermiso.get("parametro.mnuCOMPRAS")!=null && mapPermiso.get("parametro.mnuCOMPRAS").equals("1"))/*253.151*/{_display_(Seq[Any](format.raw/*253.152*/("""
	 			"""),format.raw/*254.6*/("""<li class="nav-item dropdown">
					<a id="mnuCOMPRAS" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	Compras
		        	</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
		        	
		        		"""),_display_(if(mapPermiso.get("compraIngreso")!=null && mapPermiso.get("importaCompraIconstruye")!=null && mapPermiso.get("parametro.importaCompraIconstruye")!=null && mapPermiso.get("parametro.importaCompraIconstruye").equals("1"))/*260.234*/{_display_(Seq[Any](format.raw/*260.235*/("""
		        			"""),format.raw/*261.14*/("""<a class="dropdown-item" href="/compraImportIconstruye0/">Import IConstruye</a>
						""")))} else {null} ),format.raw/*262.8*/("""
						
						"""),_display_(if(mapPermiso.get("compraIngreso")!=null)/*264.49*/{_display_(Seq[Any](format.raw/*264.50*/("""
		        			"""),format.raw/*265.14*/("""<a class="dropdown-item" href="/compraIngreso/">Ingresar Compras</a>
						""")))} else {null} ),format.raw/*266.8*/("""
						"""),_display_(if(mapPermiso.get("compraConfirma")!=null)/*267.50*/{_display_(Seq[Any](format.raw/*267.51*/("""
							"""),format.raw/*268.8*/("""<a class="dropdown-item" href="/compraConfirma/">Confirmar Compras</a>
						""")))} else {null} ),format.raw/*269.8*/("""
						"""),_display_(if(mapPermiso.get("compraModifica")!=null)/*270.50*/{_display_(Seq[Any](format.raw/*270.51*/("""
							"""),format.raw/*271.8*/("""<a class="dropdown-item" href="/compraListaModifica/">Modificar Compras</a>
						""")))} else {null} ),format.raw/*272.8*/("""
						"""),_display_(if(mapPermiso.get("compraListado")!=null)/*273.49*/{_display_(Seq[Any](format.raw/*273.50*/("""
							"""),format.raw/*274.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<li class="dropdown-submenu">
								<a class="dropdown-item dropdown-toggle" href="#">Listar Compras</a>
								<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="/listComprasPorCompra/">Por Compra</a></li>
										<li><a class="dropdown-item" href="/listComprasPorEquipo/">Por Equipo</a></li>
								</ul>
								<a class="dropdown-item" href="/routes2/movCompras0/">Mov Compras</a>
							</li>
						""")))} else {null} ),format.raw/*283.8*/("""
						"""),_display_(if(mapPermiso.get("compraElimina")!=null)/*284.49*/{_display_(Seq[Any](format.raw/*284.50*/("""
							"""),format.raw/*285.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<a class="dropdown-item" href="/compraElimina/">Eliminar (No confirmadas)</a>
						""")))} else {null} ),format.raw/*287.8*/("""
		        	"""),format.raw/*288.12*/("""</ul>
		        </li>
			""")))} else {null} ),format.raw/*290.5*/("""
			
			"""),format.raw/*292.4*/("""<!-- =================================== -->
	        <!-- 		MENU MOVIMIENTOS 			 -->  
	        <!-- =================================== -->
	        """),_display_(if(mapPermiso.get("mnuMOVIMIENTOS")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS").equals("1"))/*295.163*/{_display_(Seq[Any](format.raw/*295.164*/("""
	 			"""),format.raw/*296.6*/("""<li class="nav-item dropdown">
					<a id="mnuMOVIMIENTOS" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	Movimientos
		        	</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
		        		"""),_display_(if(mapPermiso.get("movimientoSelectBodegaOrigen")!=null)/*301.70*/{_display_(Seq[Any](format.raw/*301.71*/("""
		        			"""),format.raw/*302.14*/("""<a class="dropdown-item" href="/movimientoSelectBodegaOrigen/">Ingresar</a>
						""")))} else {null} ),format.raw/*303.8*/("""
						"""),_display_(if(mapPermiso.get("movimientoListar")!=null)/*304.52*/{_display_(Seq[Any](format.raw/*304.53*/("""
							"""),format.raw/*305.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<li class="dropdown-submenu">
								<a class="dropdown-item dropdown-toggle" href="#">Listar Movimientos</a>
								<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="/movimientoListarPeriodo/">Sin Imagenes</a></li>
										<li><a class="dropdown-item" href="/movimientoListarImgPeriodo/">Con Imagenes</a></li>
								</ul>
							</li>
						""")))} else {null} ),format.raw/*313.8*/("""
						"""),_display_(if(mapPermiso.get("movimientoSelectModificar")!=null)/*314.61*/{_display_(Seq[Any](format.raw/*314.62*/("""
							"""),format.raw/*315.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<a class="dropdown-item" href="/movimientoSelectModificarPeriodo/">Modificar</a>
						""")))} else {null} ),format.raw/*317.8*/("""
						"""),_display_(if(mapPermiso.get("hojaChequeoSelectBodega")!=null)/*318.59*/{_display_(Seq[Any](format.raw/*318.60*/("""
							"""),format.raw/*319.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<a class="dropdown-item" href="/hojaChequeoSelectBodegaAgrupado/">Hoja Chequeo (Agrupado)</a>
							<a class="dropdown-item" href="/hojaChequeoSelectPorGrupo/">Hoja Chequeo por Grupo (Agrupado)</a>
							<a class="dropdown-item" href="/hojaChequeoSelectBodega/">Hoja Chequeo (Por Cotizaci&oacute;n)</a>
						""")))} else {null} ),format.raw/*323.8*/("""
						"""),_display_(if(mapPermiso.get("movimientoSelectBodegaOrigen")!=null)/*324.64*/{_display_(Seq[Any](format.raw/*324.65*/("""
							"""),format.raw/*325.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<a class="dropdown-item" href="/cierreProyectoSel/">Cierre de """),_display_(/*326.71*/mapDiccionario/*326.85*/.get("Bodega")),format.raw/*326.99*/("""/Proyectos</a>
						""")))} else {null} ),format.raw/*327.8*/("""
		        	"""),format.raw/*328.12*/("""</ul>
		        </li>
			""")))} else {null} ),format.raw/*330.5*/("""
			
			"""),format.raw/*332.4*/("""<!-- =================================== -->
	        <!-- 			MENU BAJAS 				 -->  
	        <!-- =================================== -->
			"""),_display_(if(mapPermiso.get("cambiarSucursal")==null || userMnu.aplicaPorSucursal.equals("0"))/*335.89*/{_display_(Seq[Any](format.raw/*335.90*/("""
		        """),_display_(if(mapPermiso.get("mnuBAJAS")!=null && mapPermiso.get("parametro.mnuBAJAS")!=null && mapPermiso.get("parametro.mnuBAJAS").equals("1"))/*336.146*/{_display_(Seq[Any](format.raw/*336.147*/("""
		 			"""),format.raw/*337.7*/("""<li class="nav-item dropdown">
						<a id="mnuBAJAS" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						 	Bajas
			        	</a>
			        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
			        		"""),_display_(if(mapPermiso.get("bajaIngreso")!=null)/*342.54*/{_display_(Seq[Any](format.raw/*342.55*/("""
			        			"""),format.raw/*343.15*/("""<a class="dropdown-item" href="/bajaIngreso/">Ingresar Actas</a>
							""")))} else {null} ),format.raw/*344.9*/("""
							"""),_display_(if(mapPermiso.get("bajaConfirma")!=null)/*345.49*/{_display_(Seq[Any](format.raw/*345.50*/("""
			        			"""),format.raw/*346.15*/("""<a class="dropdown-item" href="/confirmaBaja/">Confirmar Bajas</a>
							""")))} else {null} ),format.raw/*347.9*/("""
							"""),_display_(if(mapPermiso.get("bajaModifica2")!=null)/*348.50*/{_display_(Seq[Any](format.raw/*348.51*/("""
			        			"""),format.raw/*349.15*/("""<a class="dropdown-item" href="/bajaListaModifica/">Modificar Actas</a>
							""")))} else {null} ),format.raw/*350.9*/("""
							"""),_display_(if(mapPermiso.get("bajaListado")!=null)/*351.48*/{_display_(Seq[Any](format.raw/*351.49*/("""
								"""),format.raw/*352.9*/("""<div class="dropdown-divider" style="border-color:black"></div>
								<li class="dropdown-submenu">
									<a class="dropdown-item dropdown-toggle" href="#">Listar Bajas</a>
									<ul class="dropdown-menu">
											<li><a class="dropdown-item" href="/listBajasPorActa/">Por Acta</a></li>
											<li><a class="dropdown-item" href="/listBajasPorEquipo/">Por Equipo</a></li>
									</ul>
								</li>
							""")))} else {null} ),format.raw/*360.9*/("""
			        	"""),format.raw/*361.13*/("""</ul>
			        </li>
				""")))} else {null} ),format.raw/*363.6*/("""
				
				
				"""),format.raw/*366.5*/("""<!-- =================================== -->
	        	<!-- 			MENU REDIMENSIONAR 				 -->  
	        	<!-- =================================== -->
				"""),_display_(if(mapPermiso.get("mnuREDIMENSIONAR")!=null && mapPermiso.get("parametro.mnuREDIMENSIONAR")!=null && mapPermiso.get("parametro.mnuREDIMENSIONAR").equals("1"))/*369.164*/{_display_(Seq[Any](format.raw/*369.165*/("""
		 			"""),format.raw/*370.7*/("""<li class="nav-item dropdown">
						<a id="mnuBAJAS" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						 	Redimensionar
			        	</a>
			        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
							"""),_display_(if(mapPermiso.get("redimensionarIngreso")!=null)/*375.57*/{_display_(Seq[Any](format.raw/*375.58*/("""
								"""),format.raw/*376.9*/("""<a class="dropdown-item" href="/routes2/redimensionarAsignaBodega/">Asigna """),_display_(/*376.85*/mapDiccionario/*376.99*/.get("Bodega")),format.raw/*376.113*/("""</a>
								<div class="dropdown-divider" style="border-color:black"></div>
			        			<a class="dropdown-item" href="/routes2/redimensionarPrepara/">Preparar</a>
							""")))} else {null} ),format.raw/*379.9*/("""
							"""),_display_(if(mapPermiso.get("redimensionarConfirma")!=null)/*380.58*/{_display_(Seq[Any](format.raw/*380.59*/("""
			        			"""),format.raw/*381.15*/("""<a class="dropdown-item" href="/routes2/redimensionarConfirmar0/">Confirmar</a>
							""")))} else {null} ),format.raw/*382.9*/("""
							"""),_display_(if(mapPermiso.get("redimensionarEliminar")!=null)/*383.58*/{_display_(Seq[Any](format.raw/*383.59*/("""
			        			"""),format.raw/*384.15*/("""<a class="dropdown-item" href="/routes2/redimensionarEliminar0/">Eliminar</a>
							""")))} else {null} ),format.raw/*385.9*/("""
							"""),_display_(if(mapPermiso.get("redimensionarListado")!=null)/*386.57*/{_display_(Seq[Any](format.raw/*386.58*/("""
								"""),format.raw/*387.9*/("""<div class="dropdown-divider" style="border-color:black"></div>
								<li class="dropdown-submenu">
									<a class="dropdown-item dropdown-toggle" href="#">Listar</a>
									<ul class="dropdown-menu">
											<li><a class="dropdown-item" href="/routes2/redimensionarListar0/">Por Acta</a></li>
											<!-- <li><a class="dropdown-item" href="#">Por Equipo</a></li> -->
									</ul>
								</li>
							""")))} else {null} ),format.raw/*395.9*/("""
			        	"""),format.raw/*396.13*/("""</ul>
			        </li>
				""")))} else {null} ),format.raw/*398.6*/("""
			""")))} else {null} ),format.raw/*399.5*/("""
			
			"""),format.raw/*401.4*/("""<!-- =================================== -->
	        <!-- 			MENU PLANES 	   		 -->  
	        <!-- =================================== -->





	        """),_display_(if(mapPermiso.get("mnuPLANES")!=null && mapPermiso.get("parametro.mnuPLANES")!=null && mapPermiso.get("parametro.mnuPLANES").equals("1"))/*409.148*/{_display_(Seq[Any](format.raw/*409.149*/("""
					"""),_display_(if(mapPermiso.get("parametro.mnuMANTENCION")==null || mapPermiso.get("parametro.mnuMANTENCION").equals("0"))/*410.115*/{_display_(Seq[Any](format.raw/*410.116*/("""
							"""),format.raw/*411.8*/("""<li class="nav-item dropdown">
								<a id="mnuPLANES" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								 	Planes
					        	</a>
					        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
					        		"""),_display_(if(mapPermiso.get("planHojaVida")!=null)/*416.57*/{_display_(Seq[Any](format.raw/*416.58*/("""
					        			"""),format.raw/*417.17*/("""<a class="dropdown-item" href="/hojaVidaMantencionLista/0">Actualizar Hojas de Vida</a>
					        			<div class="dropdown-divider" style="border-color:black"></div>
									""")))} else {null} ),format.raw/*419.11*/("""
					        		"""),_display_(if(mapPermiso.get("planMantencion")!=null)/*420.59*/{_display_(Seq[Any](format.raw/*420.60*/("""
					        			"""),format.raw/*421.17*/("""<a class="dropdown-item" href="/hojaVidaPlanMantencion/">Planes de Mantenci&oacute;n</a>
									""")))} else {null} ),format.raw/*422.11*/("""
									"""),_display_(if(mapPermiso.get("planTipoPlan")!=null)/*423.51*/{_display_(Seq[Any](format.raw/*423.52*/("""
										"""),format.raw/*424.11*/("""<a class="dropdown-item" href="/tipoPlanMantencion/">Tipo Plan de Mantención</a>
									""")))} else {null} ),format.raw/*425.11*/("""
									"""),_display_(if(mapPermiso.get("planTipoTrabajo")!=null)/*426.54*/{_display_(Seq[Any](format.raw/*426.55*/("""
					        			"""),format.raw/*427.17*/("""<a class="dropdown-item" href="/tipoTrabajoMantencion/">Tipo de Trabajo</a>
									""")))} else {null} ),format.raw/*428.11*/("""
									"""),_display_(if(mapPermiso.get("reportesHojaVida")!=null)/*429.55*/{_display_(Seq[Any](format.raw/*429.56*/("""
										"""),format.raw/*430.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
					        			<a class="dropdown-item" href="/hojaVidaReportLista/">Hojas de Vida</a>
									""")))} else {null} ),format.raw/*432.11*/("""
									"""),_display_(if(mapPermiso.get("analisisHojaVida")!=null)/*433.55*/{_display_(Seq[Any](format.raw/*433.56*/("""
					        			"""),format.raw/*434.17*/("""<a class="dropdown-item" href="/hojaVidaReportKpis/">Reporte KPIs</a>
									""")))} else {null} ),format.raw/*435.11*/("""
									"""),_display_(if(mapPermiso.get("planGraficos")!=null)/*436.51*/{_display_(Seq[Any](format.raw/*436.52*/("""
										"""),format.raw/*437.11*/("""<li class="dropdown-submenu">
											<a class="dropdown-item dropdown-toggle" href="#">Gr&aacute;ficos</a>
											<ul class="dropdown-menu">
													<li><a class="dropdown-item" href="/hojaVidaReportGrafico/0,30">Gr&aacute;fico-30</a></li>
													<li><a class="dropdown-item" href="/hojaVidaReportGrafico/0,60">Gr&aacute;fico-60</a></li>
													<li><a class="dropdown-item" href="/hojaVidaReportGrafico/0,90">Gr&aacute;fico-90</a></li>
													<li><a class="dropdown-item" href="/hojaVidaReportGrafico/0,180">Gr&aacute;fico-180</a></li>
													<li><a class="dropdown-item" href="/hojaVidaReportGrafico/0,360">Gr&aacute;fico-360</a></li>
											</ul>
										</li>
									""")))} else {null} ),format.raw/*447.11*/("""
					        	"""),format.raw/*448.15*/("""</ul>
					        </li>
					""")))}else/*450.11*/{_display_(Seq[Any](format.raw/*450.12*/("""
							"""),format.raw/*451.8*/("""<li class="nav-item dropdown">
								<a id="mnuPLANES" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								 	Planes
					        	</a>
					        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
									<li class="dropdown-submenu">
							          	<a class="dropdown-item dropdown-toggle" href="#">Planes</a>
							            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
							        		"""),_display_(if(mapPermiso.get("planHojaVida")!=null)/*459.59*/{_display_(Seq[Any](format.raw/*459.60*/("""
							        			"""),format.raw/*460.19*/("""<a class="dropdown-item" href="/hojaVidaMantencionLista/0">Actualizar Hojas de Vida</a>
							        			<div class="dropdown-divider" style="border-color:black"></div>
											""")))} else {null} ),format.raw/*462.13*/("""
							        		"""),_display_(if(mapPermiso.get("planMantencion")!=null)/*463.61*/{_display_(Seq[Any](format.raw/*463.62*/("""
							        			"""),format.raw/*464.19*/("""<a class="dropdown-item" href="/hojaVidaPlanMantencion/">Planes de Mantenci&oacute;n</a>
											""")))} else {null} ),format.raw/*465.13*/("""
											"""),_display_(if(mapPermiso.get("planTipoPlan")!=null)/*466.53*/{_display_(Seq[Any](format.raw/*466.54*/("""
												"""),format.raw/*467.13*/("""<a class="dropdown-item" href="/tipoPlanMantencion/">Tipo Plan de Mantención</a>
											""")))} else {null} ),format.raw/*468.13*/("""
											"""),_display_(if(mapPermiso.get("planTipoTrabajo")!=null)/*469.56*/{_display_(Seq[Any](format.raw/*469.57*/("""
							        			"""),format.raw/*470.19*/("""<a class="dropdown-item" href="/tipoTrabajoMantencion/">Tipo de Trabajo</a>
											""")))} else {null} ),format.raw/*471.13*/("""
											"""),_display_(if(mapPermiso.get("reportesHojaVida")!=null)/*472.57*/{_display_(Seq[Any](format.raw/*472.58*/("""
												"""),format.raw/*473.13*/("""<div class="dropdown-divider" style="border-color:black"></div>
							        			<a class="dropdown-item" href="/hojaVidaReportLista/">Hojas de Vida</a>
											""")))} else {null} ),format.raw/*475.13*/("""
											"""),_display_(if(mapPermiso.get("analisisHojaVida")!=null)/*476.57*/{_display_(Seq[Any](format.raw/*476.58*/("""
							        			"""),format.raw/*477.19*/("""<a class="dropdown-item" href="/hojaVidaReportKpis/">Reporte KPIs</a>
											""")))} else {null} ),format.raw/*478.13*/("""
											"""),_display_(if(mapPermiso.get("planGraficos")!=null)/*479.53*/{_display_(Seq[Any](format.raw/*479.54*/("""
												"""),format.raw/*480.13*/("""<li class="dropdown-submenu">
													<a class="dropdown-item dropdown-toggle" href="#">Gr&aacute;ficos</a>
													<ul class="dropdown-menu">
															<li><a class="dropdown-item" href="/hojaVidaReportGrafico/0,30">Gr&aacute;fico-30</a></li>
															<li><a class="dropdown-item" href="/hojaVidaReportGrafico/0,60">Gr&aacute;fico-60</a></li>
															<li><a class="dropdown-item" href="/hojaVidaReportGrafico/0,90">Gr&aacute;fico-90</a></li>
															<li><a class="dropdown-item" href="/hojaVidaReportGrafico/0,180">Gr&aacute;fico-180</a></li>
															<li><a class="dropdown-item" href="/hojaVidaReportGrafico/0,360">Gr&aacute;fico-360</a></li>
													</ul>
												</li>
											""")))} else {null} ),format.raw/*490.13*/("""
							        	"""),format.raw/*491.17*/("""</ul>
							        </li>
									<li class="dropdown-submenu">
							          	<a class="dropdown-item dropdown-toggle" href="#">Mantenci&oacute;n</a>
							            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
							        			<a class="dropdown-item" href="/routes3/mantReportNew/">Ingresar Report</a>
												<a class="dropdown-item" href="/routes3/mantListarReports0/">Listar Report</a>
							        			<div class="dropdown-divider" style="border-color:black"></div>
							        			<a class="dropdown-item" href="/routes3/mantHistorialReports0/">Historial Report</a>
												<a class="dropdown-item" href="/routes3/mantControlMantenciones/">Control Mantenciones</a>
											<div class="dropdown-divider" style="border-color:black"></div>
												<a class="dropdown-item" href="/routes3/mantCantOperacional0/">Cantidad Operacional</a>
												<a class="dropdown-item" href="/routes3/mantCantMantencion0/">Cantidad Mantencion</a>
												
												
												<!--
							        			<a class="dropdown-item" href="#">menu 4</a>
												<div class="dropdown-divider" style="border-color:black"></div>
							        			<a class="dropdown-item" href="#">menu 5</a>
							        			<a class="dropdown-item" href="#">menu 6</a> -->
		
		
		
		
												<div class="dropdown-divider" style="border-color:black"></div>
												"""),_display_(if(mapPermiso.get("mnuMantTbl")!=null)/*516.52*/{_display_(Seq[Any](format.raw/*516.53*/("""
													"""),format.raw/*517.14*/("""<li class="dropdown-submenu">
														<a class="dropdown-item dropdown-toggle" href="#">Tablas</a>
														<ul class="dropdown-menu">
															"""),_display_(if(mapPermiso.get("mantTblItemMant")!=null)/*520.60*/{_display_(Seq[Any](format.raw/*520.61*/("""
																"""),format.raw/*521.17*/("""<li><a class="dropdown-item" href="/routes3/mantTblItemMant/">Items a Intervenir</a></li>
															""")))} else {null} ),format.raw/*522.17*/("""
															"""),_display_(if(mapPermiso.get("mantTblActividadMant")!=null)/*523.65*/{_display_(Seq[Any](format.raw/*523.66*/("""
																"""),format.raw/*524.17*/("""<li><a class="dropdown-item" href="/routes3/mantTblActividadMant/">Actividades</a></li>
															""")))} else {null} ),format.raw/*525.17*/("""
															"""),_display_(if(mapPermiso.get("mantTblTipoActividadMant")!=null)/*526.69*/{_display_(Seq[Any](format.raw/*526.70*/("""
																"""),format.raw/*527.17*/("""<li><a class="dropdown-item" href="/routes3/mantTblTipoActividadMant/">Tipo de Actividades</a></li>
															""")))} else {null} ),format.raw/*528.17*/("""
															"""),_display_(if(mapPermiso.get("mantTblComponenteMant")!=null)/*529.66*/{_display_(Seq[Any](format.raw/*529.67*/("""
																"""),format.raw/*530.17*/("""<li><a class="dropdown-item" href="/routes3/mantTblComponenteMant/">Componentes</a></li>
															""")))} else {null} ),format.raw/*531.17*/("""
															"""),_display_(if(mapPermiso.get("mantTblEstadoEnObraMant")!=null)/*532.68*/{_display_(Seq[Any](format.raw/*532.69*/("""
																"""),format.raw/*533.17*/("""<li><a class="dropdown-item" href="/routes3/mantTblEstadoEnObraMant/">Estados en Sitio</a></li>
															""")))} else {null} ),format.raw/*534.17*/("""
															"""),_display_(if(mapPermiso.get("mantTblEstadoEnTallerMant")!=null)/*535.70*/{_display_(Seq[Any](format.raw/*535.71*/("""
																"""),format.raw/*536.17*/("""<li><a class="dropdown-item" href="/routes3/mantTblEstadoEnTallerMant/">Estados en Taller</a></li>
															""")))} else {null} ),format.raw/*537.17*/("""
															"""),_display_(if(mapPermiso.get("mantTblEstadoOperacionalMant")!=null)/*538.73*/{_display_(Seq[Any](format.raw/*538.74*/("""
																"""),format.raw/*539.17*/("""<li><a class="dropdown-item" href="/routes3/mantTblEstadoOperacionalMant/">Estados Operacionales</a></li>
															""")))} else {null} ),format.raw/*540.17*/("""
															"""),_display_(if(mapPermiso.get("mantTblOperadorMecanicoMant")!=null)/*541.72*/{_display_(Seq[Any](format.raw/*541.73*/("""
																"""),format.raw/*542.17*/("""<div class="dropdown-divider" style="border-color:black"></div>
																<li><a class="dropdown-item" href="/routes3/mantTblOperadorMecanicoMant/">Usuarios APP</a></li>
															""")))} else {null} ),format.raw/*544.17*/("""
														"""),format.raw/*545.15*/("""</ul>
													</li>
												""")))} else {null} ),format.raw/*547.14*/("""
							        	"""),format.raw/*548.17*/("""</ul>
							        </li>
					        	</ul>
					        </li>
					""")))}),format.raw/*552.7*/("""
	 			
			""")))} else {null} ),format.raw/*554.5*/("""
			
			"""),format.raw/*556.4*/("""<!-- =================================== -->
	        <!-- 		MENU DISPONIBILIDAD 	   	 -->  
	        <!-- =================================== -->
	        """),_display_(if(mapPermiso.get("mnuDISPONIBILIDAD")!=null && mapPermiso.get("parametro.mnuDISPONIBILIDAD")!=null && mapPermiso.get("parametro.mnuDISPONIBILIDAD").equals("1"))/*559.172*/{_display_(Seq[Any](format.raw/*559.173*/("""
	 			"""),format.raw/*560.6*/("""<li class="nav-item dropdown">
					<a id="mnuDISPONIBILIDAD" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	Disponibilidad
		        	</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
		        		"""),_display_(if(mapPermiso.get("disponibleActualizaAll")!=null)/*565.64*/{_display_(Seq[Any](format.raw/*565.65*/("""
		        			"""),format.raw/*566.14*/("""<a class="dropdown-item" href="/disponibleActualizaAll/">Actualizar Fechas</a>
						""")))} else {null} ),format.raw/*567.8*/("""
						"""),_display_(if(mapPermiso.get("reportDisponibilidadAll")!=null)/*568.59*/{_display_(Seq[Any](format.raw/*568.60*/("""
							"""),format.raw/*569.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
		        			<a class="dropdown-item" href="/reportDisponibilidadAll/">Reporte</a>
						""")))} else {null} ),format.raw/*571.8*/("""
						"""),_display_(if(mapPermiso.get("disponibleActualizaAll")!=null)/*572.58*/{_display_(Seq[Any](format.raw/*572.59*/("""
							"""),_display_(if(mapPermiso.get("cambiarSucursal")==null || userMnu.aplicaPorSucursal.equals("0"))/*573.93*/{_display_(Seq[Any](format.raw/*573.94*/("""
								"""),format.raw/*574.9*/("""<div class="dropdown-divider" style="border-color:black"></div>
		        				<a class="dropdown-item" href="/disponibleInscribirEquipo/">Inscribir Equipos</a>
		        				<a class="dropdown-item" href="/disponibleEliminarEquipo/">Desinscribir Equipos</a>
							""")))} else {null} ),format.raw/*577.9*/("""
							

		        			"""),format.raw/*580.14*/("""<div class="dropdown-divider" style="border-color:black"></div>
		        			<a class="dropdown-item" href="/disponibleInscribirBodega/">Inscribir """),_display_(/*581.85*/mapDiccionario/*581.99*/.get("Bodegas")),format.raw/*581.114*/("""</a>
		        			<a class="dropdown-item" href="/disponibleEliminarBodega/">Desinscribir """),_display_(/*582.87*/mapDiccionario/*582.101*/.get("Bodegas")),format.raw/*582.116*/("""</a>
						""")))} else {null} ),format.raw/*583.8*/("""
		        	"""),format.raw/*584.12*/("""</ul>
		        </li>
			""")))} else {null} ),format.raw/*586.5*/("""
			
			"""),format.raw/*588.4*/("""<!-- =================================== -->
	        <!-- 			MENU QR 			   	 -->  
	        <!-- =================================== -->
	        """),_display_(if(mapPermiso.get("mnuQR")!=null && mapPermiso.get("parametro.mnuQR")!=null && mapPermiso.get("parametro.mnuQR").equals("1"))/*591.136*/{_display_(Seq[Any](format.raw/*591.137*/("""
	 			"""),format.raw/*592.6*/("""<li class="nav-item dropdown">
					<a id="mnuQR" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	Qr
		        	</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
		        		"""),_display_(if(mapPermiso.get("qrAdminEquipos")!=null)/*597.56*/{_display_(Seq[Any](format.raw/*597.57*/("""
		        			"""),format.raw/*598.14*/("""<a class="dropdown-item" href="/qrListaEquipos/">Administrar QR</a>
		        			<a class="dropdown-item" href="/qrListaAtributoEquipos/">Atributos QR</a>
		        			<div class="dropdown-divider" style="border-color:black"></div>
		        			<a class="dropdown-item" href="/qrPrintQrEquipos/">Print QR Equipos</a>
							<a class="dropdown-item" href="/qrRevisarDatosAllVigentes/">Revisar QR (todos)</a>
						""")))} else {null} ),format.raw/*603.8*/("""
						
						
						
						"""),_display_(if(mapPermiso.get("qrAdminPersonal")!=null)/*607.51*/{_display_(Seq[Any](format.raw/*607.52*/("""
							"""),format.raw/*608.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
		        			<a class="dropdown-item" href="/qrListaPersonal">Admin Personal</a>
		        			<a class="dropdown-item" href="/qrListaAtributoPersonal">Atributos Personal</a>
						""")))} else {null} ),format.raw/*611.8*/("""
		        	"""),format.raw/*612.12*/("""</ul>
		        </li>
			""")))} else {null} ),format.raw/*614.5*/("""

			"""),format.raw/*616.4*/("""<!-- =================================== -->
	        <!-- 		MENU PRESUPUESTOS 		   	 -->  
	        <!-- =================================== -->
	        """),_display_(if(mapPermiso.get("mnuPRESUPUESTOS")!=null && mapPermiso.get("parametro.mnuPRESUPUESTOS")!=null && mapPermiso.get("parametro.mnuPRESUPUESTOS").equals("1"))/*619.166*/{_display_(Seq[Any](format.raw/*619.167*/("""
	 			"""),format.raw/*620.6*/("""<li class="nav-item dropdown">
					<a id="mnuPRESUPUESTOS" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	Ppto
		        	</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
		        		"""),_display_(if(mapPermiso.get("pptoAdministrar")!=null)/*625.57*/{_display_(Seq[Any](format.raw/*625.58*/("""
		        			"""),format.raw/*626.14*/("""<a class="dropdown-item" href="/pptoAdministrar/">Administrar Pptos</a>
						""")))} else {null} ),format.raw/*627.8*/("""
		        		"""),_display_(if(mapPermiso.get("pptoReportes")!=null)/*628.54*/{_display_(Seq[Any](format.raw/*628.55*/("""
		        			"""),format.raw/*629.14*/("""<div class="dropdown-divider" style="border-color:black"></div>
		        			<a class="dropdown-item" href="/pptoListadoPptos/">Reporte Pptos</a>
						""")))} else {null} ),format.raw/*631.8*/("""
		        	"""),format.raw/*632.12*/("""</ul>
		        </li>
			""")))} else {null} ),format.raw/*634.5*/("""
						
			"""),format.raw/*636.4*/("""<!-- =================================== -->
	        <!-- 			MENU ODO 			   	 -->  
	        <!-- =================================== -->
	        """),_display_(if(mapPermiso.get("mnuODO")!=null && mapPermiso.get("parametro.mnuODO")!=null && mapPermiso.get("parametro.mnuODO").equals("1"))/*639.139*/{_display_(Seq[Any](format.raw/*639.140*/("""
	 			"""),format.raw/*640.6*/("""<li class="nav-item dropdown">
					<a id="mnuQR" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	Odo
		        	</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
		        		"""),_display_(if(mapPermiso.get("odoAdminClases")!=null)/*645.56*/{_display_(Seq[Any](format.raw/*645.57*/("""
		        			"""),format.raw/*646.14*/("""<a class="dropdown-item" href="/claseMantencion/">Familias</a>
						""")))} else {null} ),format.raw/*647.8*/("""
						"""),_display_(if(mapPermiso.get("odoAdminServicios")!=null)/*648.53*/{_display_(Seq[Any](format.raw/*648.54*/("""
		        			"""),format.raw/*649.14*/("""<a class="dropdown-item" href="/servicioMantencion/">Servicios</a>
						""")))} else {null} ),format.raw/*650.8*/("""
						"""),_display_(if(mapPermiso.get("odoAdminPrecios")!=null)/*651.51*/{_display_(Seq[Any](format.raw/*651.52*/("""
							"""),format.raw/*652.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<a class="dropdown-item" href="/servicioPrecios/" title="Agregar y revisar precios de servicios por una bodega">Precios por """),_display_(/*653.133*/mapDiccionario/*653.147*/.get("Bodega")),format.raw/*653.161*/(""" """),format.raw/*653.162*/("""(ODO)</a>
							<a class="dropdown-item" href="/routes2/servicioPreciosServicio/" title="Agregar y revisar precios de un servicio en varias bodegas">Precios por Servicio en Bodegas</a>
							<div class="dropdown-divider" style="border-color:black"></div>
							<a class="dropdown-item" href="/servicioEquipos0/">Inscribir Equipos</a>
							<a class="dropdown-item" href="/servicioPreciosVariable0/">Precios Variables</a>
						""")))} else {null} ),format.raw/*658.8*/("""
						"""),_display_(if(mapPermiso.get("odoAdminOperadores")!=null)/*659.54*/{_display_(Seq[Any](format.raw/*659.55*/("""
							"""),format.raw/*660.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<a class="dropdown-item" href="/operadorMantencion/">Operadores</a>
						""")))} else {null} ),format.raw/*662.8*/("""
						"""),_display_(if(mapPermiso.get("odoVentas")!=null)/*663.45*/{_display_(Seq[Any](format.raw/*663.46*/("""
							"""),format.raw/*664.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<a class="dropdown-item" href="/odoVentas/">Ingreso Report</a>
							<a class="dropdown-item" href="/odoListarVentas0/">Listar Report</a>
						""")))} else {null} ),format.raw/*667.8*/("""
						
						
						"""),_display_(if(mapPermiso.get("mnuREPORTMOVODO")!=null)/*670.51*/{_display_(Seq[Any](format.raw/*670.52*/("""
							"""),format.raw/*671.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
		        			<li class="dropdown-submenu pull-right">
								<a class="dropdown-item dropdown-toggle" href="#">Movimientos</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("reporteMovOdo0")!=null)/*675.53*/{_display_(Seq[Any](format.raw/*675.54*/("""
										"""),format.raw/*676.11*/("""<li><a class="dropdown-item" href='/reporteMovOdoCantidades/'>Por Fecha (Cantidades)</a></li>
									""")))} else {null} ),format.raw/*677.11*/("""
									"""),format.raw/*678.10*/("""<!--    NO FUNCIONA NO HABILITADO (7-9-2022)
									"""),_display_(if(mapPermiso.get("reporteMovOdo1")!=null)/*679.53*/{_display_(Seq[Any](format.raw/*679.54*/("""
										"""),format.raw/*680.11*/("""<li><a class="dropdown-item" href='/reporteMovOdoValorizado/'>Por Fecha (Valorizado)</a></li>
									""")))} else {null} ),format.raw/*681.11*/("""
									"""),format.raw/*682.10*/("""-->
								</ul>
							</li>
		        		""")))} else {null} ),format.raw/*685.14*/("""

						"""),_display_(if(mapPermiso.get("mnuPROFORMAS_SERVICIO")!=null)/*687.57*/{_display_(Seq[Any](format.raw/*687.58*/("""
							"""),format.raw/*688.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<li class="dropdown-submenu pull-right">
								<a class="dropdown-item dropdown-toggle" href="#">"""),_display_(/*690.60*/mapDiccionario/*690.74*/.get("Proforma")),format.raw/*690.90*/("""s</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("odoProformas")!=null)/*692.51*/{_display_(Seq[Any](format.raw/*692.52*/("""
										"""),format.raw/*693.11*/("""<li><a class="dropdown-item" href='/reportProfPerOdo/'>"""),_display_(/*693.67*/mapDiccionario/*693.81*/.get("tipoBodega_2")),format.raw/*693.101*/("""</a></li>
									""")))} else {null} ),format.raw/*694.11*/("""
									
									
									"""),_display_(if(mapPermiso.get("odoResumen")!=null)/*697.49*/{_display_(Seq[Any](format.raw/*697.50*/("""
										"""),format.raw/*698.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href='/reportOdoPeriodoResumen/'>Resumen y Detalle (por período)</a></li>
									""")))} else {null} ),format.raw/*700.11*/("""
									"""),_display_(if(mapPermiso.get("odoConsolidado")!=null)/*701.53*/{_display_(Seq[Any](format.raw/*701.54*/("""
										"""),format.raw/*702.11*/("""<li><a class="dropdown-item" href='/reportOdoConsolidado/'>Consolidado (varios Meses)</a></li>
									""")))} else {null} ),format.raw/*703.11*/("""
									
									"""),_display_(if(mapPermiso.get("odoProformaLista")!=null)/*705.55*/{_display_(Seq[Any](format.raw/*705.56*/("""
										"""),format.raw/*706.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href='/proformaListaOdo/'>Listado de """),_display_(/*707.78*/mapDiccionario/*707.92*/.get("Proforma")),format.raw/*707.108*/("""s</a></li>
									""")))} else {null} ),format.raw/*708.11*/("""
									"""),format.raw/*709.10*/("""<div class="dropdown-divider" style="border-color:black"></div>
									"""),_display_(if(mapPermiso.get("odoHaceAjustes")!=null)/*710.53*/{_display_(Seq[Any](format.raw/*710.54*/("""
										"""),format.raw/*711.11*/("""<li><a class="dropdown-item" href='/ajustesEpOdo/'>Hacer Ajustes EP-"""),_display_(/*711.80*/mapDiccionario/*711.94*/.get("Proforma")),format.raw/*711.110*/("""</a></li>
									""")))} else {null} ),format.raw/*712.11*/("""
									"""),_display_(if(mapPermiso.get("odoListaAjustes")!=null)/*713.54*/{_display_(Seq[Any](format.raw/*713.55*/("""
										"""),format.raw/*714.11*/("""<li><a class="dropdown-item" href='/ajustesEpRptOdo/'>Reporte Ajustes X """),_display_(/*714.84*/mapDiccionario/*714.98*/.get("Bodega")),format.raw/*714.112*/("""/Proyecto</a></li>
									""")))} else {null} ),format.raw/*715.11*/("""
									"""),_display_(if(mapPermiso.get("odoAjustesPorPeriodo")!=null)/*716.59*/{_display_(Seq[Any](format.raw/*716.60*/("""
										"""),format.raw/*717.11*/("""<li><a class="dropdown-item" href='/ajustesPeriodoEpRpt1Odo/'>Reporte Ajustes X Per&iacute;odo</a></li>
									""")))} else {null} ),format.raw/*718.11*/("""
									
									
									"""),format.raw/*721.10*/("""<div class="dropdown-divider" style="border-color:black"></div>
									<li><a class="dropdown-item" href='/reportOperadorConsol0/'>Consolidado por Operador</a></li>
									
									
									
									
								</ul>
							</li>
						""")))} else {null} ),format.raw/*729.8*/("""
		        	"""),format.raw/*730.12*/("""</ul>
		        </li>
			""")))} else {null} ),format.raw/*732.5*/("""
			
			"""),format.raw/*734.4*/("""<!-- =================================== -->
	        <!-- 			MENU BODEGAS 		   	 -->  
	        <!-- =================================== -->
	        """),_display_(if(mapPermiso.get("mnuBODEGAS")!=null && mapPermiso.get("parametro.mnuBODEGAS")!=null && mapPermiso.get("parametro.mnuBODEGAS").equals("1"))/*737.151*/{_display_(Seq[Any](format.raw/*737.152*/("""
	 			"""),format.raw/*738.6*/("""<li class="nav-item dropdown">
					<a id="mnuBODEGAS" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	"""),_display_(/*740.9*/mapDiccionario/*740.23*/.get("Bodegas")),format.raw/*740.38*/("""
		        	"""),format.raw/*741.12*/("""</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
		        		"""),_display_(if(mapPermiso.get("bodegaAdministrar")!=null)/*743.59*/{_display_(Seq[Any](format.raw/*743.60*/("""
		        			"""),format.raw/*744.14*/("""<a class="dropdown-item" href="/bodegaAdministrar/">Administrar """),_display_(/*744.79*/mapDiccionario/*744.93*/.get("Bodegas")),format.raw/*744.108*/("""</a>
							<a class="dropdown-item" href="/routes2/sucursalAdministrar/">Administrar Sucursales</a>
							<a class="dropdown-item" href="/routes2/rubroMantencion/">Administrar Rubros</a>
							<div class="dropdown-divider" style="border-color:black"></div>
						""")))} else {null} ),format.raw/*748.8*/("""
						"""),_display_(if(mapDiccionario.get("nEmpresa").equals("GLOBALRENT") && mapPermiso.get("bodegaAsignaDctos")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS").equals("1"))/*749.218*/{_display_(Seq[Any](format.raw/*749.219*/("""
		        			"""),format.raw/*750.14*/("""<a class="dropdown-item" href="/bodegaAsignaDctos/">Asignar Descuentos</a>
						""")))} else {null} ),format.raw/*751.8*/("""
						"""),_display_(if(mapPermiso.get("bodegaAsignaTasas")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS").equals("1"))/*752.163*/{_display_(Seq[Any](format.raw/*752.164*/("""
		        			"""),format.raw/*753.14*/("""<a class="dropdown-item" href="/bodegaAsignaTasas/">Asignar Tasas</a>
						""")))} else {null} ),format.raw/*754.8*/("""
						"""),_display_(if(mapPermiso.get("bodegaPrecios")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS").equals("1"))/*755.159*/{_display_(Seq[Any](format.raw/*755.160*/("""
		        			"""),format.raw/*756.14*/("""<a class="dropdown-item" href="/bodegaPrecios/">Precios por """),_display_(/*756.75*/mapDiccionario/*756.89*/.get("Bodega")),format.raw/*756.103*/("""</a>
						""")))} else {null} ),format.raw/*757.8*/("""
						"""),_display_(if(mapPermiso.get("bodegaFactorViga")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS").equals("1"))/*758.162*/{_display_(Seq[Any](format.raw/*758.163*/("""
		        			"""),format.raw/*759.14*/("""<a class="dropdown-item" href="/bodegaFactorViga/">Factor Viga (m2/ml)</a>
						""")))} else {null} ),format.raw/*760.8*/("""
						"""),_display_(if(mapPermiso.get("bodegaContactos")!=null)/*761.51*/{_display_(Seq[Any](format.raw/*761.52*/("""
		        			"""),format.raw/*762.14*/("""<div class="dropdown-divider" style="border-color:black"></div>
		        			<a class="dropdown-item" href="/bodegaContactos/">Administrar Contactos</a>
		        			<a class="dropdown-item" href="/bodegaContactosExcel/">Exportar Contactos</a>
						""")))} else {null} ),format.raw/*765.8*/("""
		        		"""),_display_(if(mapPermiso.get("bodegaVigenteNoVigente")!=null)/*766.64*/{_display_(Seq[Any](format.raw/*766.65*/("""
		        			"""),format.raw/*767.14*/("""<div class="dropdown-divider" style="border-color:black"></div>
		        			<a class="dropdown-item" href="/bodegaVigenteNoVigente/">Cambiar el Estado <br> de """),_display_(/*768.98*/mapDiccionario/*768.112*/.get("Bodegas")),format.raw/*768.127*/("""</a>
						""")))} else {null} ),format.raw/*769.8*/("""
		        	"""),format.raw/*770.12*/("""</ul>
		        </li>
			""")))} else {null} ),format.raw/*772.5*/("""
			
			"""),format.raw/*774.4*/("""<!-- =================================== -->
	        <!-- 			MENU TABLAS 		   	 -->  
	        <!-- =================================== -->
	        """),_display_(if(mapPermiso.get("mnuTABLAS")!=null && mapPermiso.get("parametro.mnuTABLAS")!=null && mapPermiso.get("parametro.mnuTABLAS").equals("1"))/*777.148*/{_display_(Seq[Any](format.raw/*777.149*/("""
	 			"""),format.raw/*778.6*/("""<li class="nav-item dropdown">
					<a id="mnuTABLAS" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	Tablas
		        	</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
		        		"""),_display_(if(mapPermiso.get("precioMantencion")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS").equals("1"))/*783.168*/{_display_(Seq[Any](format.raw/*783.169*/("""
							"""),_display_(if(mapPermiso.get("cambiarSucursal")==null || userMnu.aplicaPorSucursal.equals("0"))/*784.93*/{_display_(Seq[Any](format.raw/*784.94*/("""
								"""),format.raw/*785.9*/("""<a class="dropdown-item" href="/routes2/precioMantSelSucursal/">Maestro de Precios</a>
		        				<div class="dropdown-divider" style="border-color:black"></div>
							""")))} else {null} ),format.raw/*787.9*/("""
		        			
						""")))} else {null} ),format.raw/*789.8*/("""
						"""),_display_(if(mapPermiso.get("grupoMantencion")!=null)/*790.51*/{_display_(Seq[Any](format.raw/*790.52*/("""
		        			"""),format.raw/*791.14*/("""<a class="dropdown-item" href="/grupoMantencion/">Grupos</a>
						""")))} else {null} ),format.raw/*792.8*/("""
						"""),_display_(if(mapPermiso.get("equipoMantencion")!=null)/*793.52*/{_display_(Seq[Any](format.raw/*793.53*/("""
		        			"""),format.raw/*794.14*/("""<a class="dropdown-item" href="/equipoMantencion/">Equipos</a>
						""")))} else {null} ),format.raw/*795.8*/("""
						"""),_display_(if(mapPermiso.get("proyectoMantencion")!=null)/*796.54*/{_display_(Seq[Any](format.raw/*796.55*/("""
		        			"""),format.raw/*797.14*/("""<a class="dropdown-item" href="/proyectoMantencion/">Proyectos</a>
						""")))} else {null} ),format.raw/*798.8*/("""
						"""),_display_(if(mapPermiso.get("propiedadMantencion")!=null)/*799.55*/{_display_(Seq[Any](format.raw/*799.56*/("""
							"""),format.raw/*800.8*/("""<a class="dropdown-item" href="/routes2/propiedadMantencion/">Propiedad</a>
						""")))} else {null} ),format.raw/*801.8*/("""
						"""),_display_(if(mapPermiso.get("clienteMantencion")!=null)/*802.53*/{_display_(Seq[Any](format.raw/*802.54*/("""
							"""),format.raw/*803.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
		        			<a class="dropdown-item" href="/clienteMantencion/">Clientes/Propietarios</a>
						""")))} else {null} ),format.raw/*805.8*/("""
						"""),_display_(if(mapPermiso.get("proveedorMantencion")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS").equals("1"))/*806.165*/{_display_(Seq[Any](format.raw/*806.166*/("""
		        			"""),format.raw/*807.14*/("""<a class="dropdown-item" href="/proveedorMantencion/">Proveedores</a>
						""")))} else {null} ),format.raw/*808.8*/("""
						"""),_display_(if(mapPermiso.get("fabricaMantencion")!=null)/*809.53*/{_display_(Seq[Any](format.raw/*809.54*/("""
		        			"""),format.raw/*810.14*/("""<a class="dropdown-item" href="/fabricaMantencion/">Fabricantes</a>
						""")))} else {null} ),format.raw/*811.8*/("""
						
						
						"""),_display_(if(mapPermiso.get("bodegaContactos")!=null)/*814.51*/{_display_(Seq[Any](format.raw/*814.52*/("""
							"""),format.raw/*815.8*/("""<a class="dropdown-item" href="/allContactosCliProvFabrExcel/">Exportar Contactos</a>
						""")))} else {null} ),format.raw/*816.8*/("""
						
						"""),_display_(if(mapPermiso.get("transportistaMantencion")!=null)/*818.59*/{_display_(Seq[Any](format.raw/*818.60*/("""
							"""),format.raw/*819.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
			        		<a class="dropdown-item" href="/transportistaMantencion/">Transportistas</a>
		        		""")))} else {null} ),format.raw/*821.14*/("""
		        		
						"""),_display_(if(mapPermiso.get("tipoEstado")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS")!=null && mapPermiso.get("parametro.mnuMOVIMIENTOS").equals("1"))/*823.156*/{_display_(Seq[Any](format.raw/*823.157*/("""
		        			"""),format.raw/*824.14*/("""<div class="dropdown-divider" style="border-color:black"></div>
		        			<a class="dropdown-item" href="/tipoEstadoMantencion/">Tipo Estados Equipo</a>
							<a class="dropdown-item" href="/routes2/cotizaSolucionMantencion/">Tipo de Soluciones (coti)</a>
						""")))} else {null} ),format.raw/*827.8*/("""
						"""),_display_(if(mapPermiso.get("decimalNumero")!=null)/*828.49*/{_display_(Seq[Any](format.raw/*828.50*/("""
		        			"""),format.raw/*829.14*/("""<a class="dropdown-item" href="/decimalNumero/">Cantidad Decimales</a>
						""")))} else {null} ),format.raw/*830.8*/("""
						"""),_display_(if(mapPermiso.get("usuarioMantencion")!=null)/*831.53*/{_display_(Seq[Any](format.raw/*831.54*/("""
		        			"""),format.raw/*832.14*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<a class="dropdown-item" href="/usuarioMantencion/">Usuarios/Perfiles</a>
							<a class="dropdown-item" href="/routes2/comercialMantencion/">Comerciales</a>
							<a class="dropdown-item" href="/routes2/dibujanteMantencion/">Dibujantes/Proyectistas</a>
						""")))} else {null} ),format.raw/*836.8*/("""
						
						"""),_display_(if(mapPermiso.get("historialCambios")!=null)/*838.52*/{_display_(Seq[Any](format.raw/*838.53*/("""
		        			"""),format.raw/*839.14*/("""<div class="dropdown-divider" style="border-color:black"></div>
		        			<a class="dropdown-item" href="/registroDeCambios/">Historial de Cambios</a>
						""")))} else {null} ),format.raw/*841.8*/("""
		        	"""),format.raw/*842.12*/("""</ul>
		        </li>
			""")))} else {null} ),format.raw/*844.5*/("""
			

			
			"""),format.raw/*848.4*/("""<!-- =================================== -->
	        <!-- 			MENU REPORTES 		   	 -->  
	        <!-- =================================== -->
	        """),_display_(if(mapPermiso.get("mnuREPORTES")!=null && mapPermiso.get("parametro.mnuREPORTES")!=null && mapPermiso.get("parametro.mnuREPORTES").equals("1"))/*851.154*/{_display_(Seq[Any](format.raw/*851.155*/("""
	 			"""),format.raw/*852.6*/("""<li class="nav-item dropdown">
					<a id="mnuREPORTES" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	Reportes
		        	</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
		        		"""),_display_(if(mapPermiso.get("mnuINVENTARIOS")!=null)/*857.56*/{_display_(Seq[Any](format.raw/*857.57*/("""
		        			"""),format.raw/*858.14*/("""<li class="dropdown-submenu pull-left">
								<a class="dropdown-item dropdown-toggle" href="#">Inventarios</a>
								<ul class="dropdown-menu">
									<li class="dropdown-submenu">
										<a class="dropdown-item dropdown-toggle" href="#">"""),_display_(/*862.62*/mapDiccionario/*862.76*/.get("Arriendo")),format.raw/*862.92*/("""</a>
										<ul class="dropdown-menu">
											"""),_display_(if(mapPermiso.get("reportInventarioGeneral")!=null)/*864.64*/{_display_(Seq[Any](format.raw/*864.65*/("""
												"""),format.raw/*865.13*/("""<li><a class="dropdown-item" href='/reportInventarioEquipoCorte/"""),_display_(/*865.78*/mapDiccionario/*865.92*/.get("ARRIENDO")),format.raw/*865.108*/("""'>
													"""),_display_(/*866.15*/mapDiccionario/*866.29*/.get("Arriendo")),format.raw/*866.45*/(""": por Equipos
												</a></li>
											""")))} else {null} ),format.raw/*868.13*/("""
											"""),_display_(if(mapPermiso.get("reportInventarioSelectivo")!=null)/*869.66*/{_display_(Seq[Any](format.raw/*869.67*/("""
												"""),format.raw/*870.13*/("""<li><a class="dropdown-item" href='/reportInventarioBodegaCorte/"""),_display_(/*870.78*/mapDiccionario/*870.92*/.get("ARRIENDO")),format.raw/*870.108*/("""'>
													"""),_display_(/*871.15*/mapDiccionario/*871.29*/.get("Arriendo")),format.raw/*871.45*/(""": por """),_display_(/*871.52*/mapDiccionario/*871.66*/.get("Bodegas")),format.raw/*871.81*/("""
												"""),format.raw/*872.13*/("""</a></li>
											""")))} else {null} ),format.raw/*873.13*/("""
											"""),_display_(if(mapPermiso.get("reportInventarioGrupo")!=null)/*874.62*/{_display_(Seq[Any](format.raw/*874.63*/("""
												"""),format.raw/*875.13*/("""<li><a class="dropdown-item" href='/reportInventarioGrupoCorte/"""),_display_(/*875.77*/mapDiccionario/*875.91*/.get("ARRIENDO")),format.raw/*875.107*/("""'>
													"""),_display_(/*876.15*/mapDiccionario/*876.29*/.get("Arriendo")),format.raw/*876.45*/(""": por Grupos
												</a></li>
											""")))} else {null} ),format.raw/*878.13*/("""
											"""),_display_(if(mapPermiso.get("reportInventarioSelectivo")!=null)/*879.66*/{_display_(Seq[Any](format.raw/*879.67*/("""
												"""),format.raw/*880.13*/("""<li><a class="dropdown-item" href='/reportEquipoBodegaCorte/"""),_display_(/*880.74*/mapDiccionario/*880.88*/.get("ARRIENDO")),format.raw/*880.104*/("""'>
													"""),_display_(/*881.15*/mapDiccionario/*881.29*/.get("Arriendo")),format.raw/*881.45*/(""": por Equipos - """),_display_(/*881.62*/mapDiccionario/*881.76*/.get("Bodegas")),format.raw/*881.91*/("""
												"""),format.raw/*882.13*/("""</a></li>
											""")))} else {null} ),format.raw/*883.13*/("""
											"""),_display_(if(mapPermiso.get("inventarioMatriz")!=null)/*884.57*/{_display_(Seq[Any](format.raw/*884.58*/("""
												"""),format.raw/*885.13*/("""<div class="dropdown-divider" style="border-color:black"></div>
												<li><a class="dropdown-item" href='/reportInventarioMatrizCorte/"""),_display_(/*886.78*/mapDiccionario/*886.92*/.get("ARRIENDO")),format.raw/*886.108*/("""'>
													Matriz """),_display_(/*887.22*/mapDiccionario/*887.36*/.get("Arriendo")),format.raw/*887.52*/(""": Equipos por """),_display_(/*887.67*/mapDiccionario/*887.81*/.get("Bodegas")),format.raw/*887.96*/("""
												"""),format.raw/*888.13*/("""</a></li>
											""")))} else {null} ),format.raw/*889.13*/("""
										"""),format.raw/*890.11*/("""</ul>
									</li>
									<li class="dropdown-submenu">
										<a class="dropdown-item dropdown-toggle" href="#">Vendido</a>
										<ul class="dropdown-menu">
											"""),_display_(if(mapPermiso.get("reportInventarioGeneral")!=null)/*895.64*/{_display_(Seq[Any](format.raw/*895.65*/("""
												"""),format.raw/*896.13*/("""<li><a class="dropdown-item" href="/reportInventarioEquipoCorte/VENTA">Vendido: por Equipos</a></li>
											""")))} else {null} ),format.raw/*897.13*/("""
											"""),_display_(if(mapPermiso.get("reportInventarioSelectivo")!=null)/*898.66*/{_display_(Seq[Any](format.raw/*898.67*/("""
												"""),format.raw/*899.13*/("""<li><a class="dropdown-item" href="/reportInventarioBodegaCorte/VENTA">Vendido: por """),_display_(/*899.98*/mapDiccionario/*899.112*/.get("Bodegas")),format.raw/*899.127*/("""</a></li>
											""")))} else {null} ),format.raw/*900.13*/("""
											"""),_display_(if(mapPermiso.get("reportInventarioGrupo")!=null)/*901.62*/{_display_(Seq[Any](format.raw/*901.63*/("""
												"""),format.raw/*902.13*/("""<li><a class="dropdown-item" href="/reportInventarioGrupoCorte/VENTA">Vendido: por Grupos</a></li>
											""")))} else {null} ),format.raw/*903.13*/("""
											"""),_display_(if(mapPermiso.get("reportInventarioGeneral")!=null)/*904.64*/{_display_(Seq[Any](format.raw/*904.65*/("""
												"""),format.raw/*905.13*/("""<li><a class="dropdown-item" href="/reportEquipoBodegaCorte/VENTA">Vendido: por Equipos - """),_display_(/*905.104*/mapDiccionario/*905.118*/.get("Bodegas")),format.raw/*905.133*/("""</a></li>
											""")))} else {null} ),format.raw/*906.13*/("""
											"""),_display_(if(mapPermiso.get("inventarioMatriz")!=null)/*907.57*/{_display_(Seq[Any](format.raw/*907.58*/("""
												"""),format.raw/*908.13*/("""<div class="dropdown-divider" style="border-color:black"></div>
												<li><a class="dropdown-item" href="/reportInventarioMatrizCorte/VENTA">Matriz Vendido: Equipos por """),_display_(/*909.113*/mapDiccionario/*909.127*/.get("Bodegas")),format.raw/*909.142*/("""</a></li>
											""")))} else {null} ),format.raw/*910.13*/("""
										"""),format.raw/*911.11*/("""</ul>
									</li>
									<li class="dropdown-submenu">
										<a class="dropdown-item dropdown-toggle" href="#">Todo ("""),_display_(/*914.68*/mapDiccionario/*914.82*/.get("Arr")),format.raw/*914.93*/("""+Vend)</a>
										<ul class="dropdown-menu">
											"""),_display_(if(mapPermiso.get("reportInventarioGeneral")!=null)/*916.64*/{_display_(Seq[Any](format.raw/*916.65*/("""
												"""),format.raw/*917.13*/("""<li><a class="dropdown-item" href="/reportInventarioEquipoCorte/TODO">Todo: por Equipos</a></li>
											""")))} else {null} ),format.raw/*918.13*/("""
											"""),_display_(if(mapPermiso.get("reportInventarioSelectivo")!=null)/*919.66*/{_display_(Seq[Any](format.raw/*919.67*/("""
												"""),format.raw/*920.13*/("""<li><a class="dropdown-item" href="/reportInventarioBodegaCorte/TODO">Todo: por """),_display_(/*920.94*/mapDiccionario/*920.108*/.get("Bodegas")),format.raw/*920.123*/("""</a></li>
											""")))} else {null} ),format.raw/*921.13*/("""
											"""),_display_(if(mapPermiso.get("reportInventarioGrupo")!=null)/*922.62*/{_display_(Seq[Any](format.raw/*922.63*/("""
												"""),format.raw/*923.13*/("""<li><a class="dropdown-item" href="/reportInventarioGrupoCorte/TODO">Todo: por Grupos</a></li>
											""")))} else {null} ),format.raw/*924.13*/("""
											"""),_display_(if(mapPermiso.get("reportInventarioGeneral")!=null)/*925.64*/{_display_(Seq[Any](format.raw/*925.65*/("""
												"""),format.raw/*926.13*/("""<li><a class="dropdown-item" href="/reportEquipoBodegaCorte/TODO">Todo:  por Equipos - """),_display_(/*926.101*/mapDiccionario/*926.115*/.get("Bodegas")),format.raw/*926.130*/("""</a></li>
											""")))} else {null} ),format.raw/*927.13*/("""
											"""),_display_(if(mapPermiso.get("inventarioMatriz")!=null)/*928.57*/{_display_(Seq[Any](format.raw/*928.58*/("""
												"""),format.raw/*929.13*/("""<div class="dropdown-divider" style="border-color:black"></div>
												<li><a class="dropdown-item" href="/reportInventarioMatrizCorte/TODO">Matriz Todo: Equipos por """),_display_(/*930.109*/mapDiccionario/*930.123*/.get("Bodegas")),format.raw/*930.138*/("""</a></li>
											""")))} else {null} ),format.raw/*931.13*/("""
											"""),_display_(if(mapPermiso.get("inventarioStock")!=null)/*932.56*/{_display_(Seq[Any](format.raw/*932.57*/("""
												"""),format.raw/*933.13*/("""<div class="dropdown-divider" style="border-color:black"></div>
												<li><a class="dropdown-item" href="/reportInventarioTodo/">Equipos con Stock y Precios</a></li>
											""")))} else {null} ),format.raw/*935.13*/("""
										"""),format.raw/*936.11*/("""</ul>
									</li>
									<div class="dropdown-divider" style="border-color:black"></div>
		        					<a class="dropdown-item" href="/routes2/reportEstadoBodegas/">Estado """),_display_(/*939.86*/mapDiccionario/*939.100*/.get("Bodegas")),format.raw/*939.115*/("""</a>
								</ul>
							</li>
		        		""")))} else {null} ),format.raw/*942.14*/("""
		        		"""),_display_(if(mapPermiso.get("mnuPROYECTOS")!=null && mapPermiso.get("reportInventarioProyecto")!=null)/*943.106*/{_display_(Seq[Any](format.raw/*943.107*/("""
		        			"""),format.raw/*944.14*/("""<a class="dropdown-item" href="/reportInventarioProyecto/">Existencias</a>
							<li><a class="dropdown-item" href="/mailingInventarioProyecto">Existencias Mailing</a></li>
						""")))} else {null} ),format.raw/*946.8*/("""
		        		"""),_display_(if(mapPermiso.get("mnuGRAFICOS")!=null)/*947.53*/{_display_(Seq[Any](format.raw/*947.54*/("""
		        			"""),format.raw/*948.14*/("""<li class="dropdown-submenu pull-left">
								<a class="dropdown-item dropdown-toggle" href="#">Gr&aacuteficos</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("subMenu4")!=null)/*951.47*/{_display_(Seq[Any](format.raw/*951.48*/("""
										"""),format.raw/*952.11*/("""<li class="dropdown-submenu">
											"""),_display_(if(mapPermiso.get("reportGraficoGrupo1")!=null)/*953.60*/{_display_(Seq[Any](format.raw/*953.61*/("""
												"""),format.raw/*954.13*/("""<a class="dropdown-item" href='/reportGraficoMovResumen/0,0'>Resumen</a>
											""")))} else {null} ),format.raw/*955.13*/("""
											"""),_display_(if(mapPermiso.get("reportGraficoGrupo2")!=null)/*956.60*/{_display_(Seq[Any](format.raw/*956.61*/("""
												"""),format.raw/*957.13*/("""<a class="dropdown-item" href='/reportGraficoMovPorGrupo/0,0,0,1'>"""),_display_(/*957.80*/mapDiccionario/*957.94*/.get("tipoBodega_1")),format.raw/*957.114*/("""</a>
											""")))} else {null} ),format.raw/*958.13*/("""
											"""),_display_(if(mapPermiso.get("reportGraficoGrupo3")!=null)/*959.60*/{_display_(Seq[Any](format.raw/*959.61*/("""
												"""),format.raw/*960.13*/("""<a class="dropdown-item" href='/reportGraficoMovPorGrupo/0,0,0,2'>"""),_display_(/*960.80*/mapDiccionario/*960.94*/.get("tipoBodega_2")),format.raw/*960.114*/("""</a>
											""")))} else {null} ),format.raw/*961.13*/("""
											"""),_display_(if(mapPermiso.get("reportGraficoGrupo4")!=null)/*962.60*/{_display_(Seq[Any](format.raw/*962.61*/("""
												"""),format.raw/*963.13*/("""<a class="dropdown-item" href='/reportGraficoMovPorGrupo/0,0,0,0'>Todo el Detalle</a>
											""")))} else {null} ),format.raw/*964.13*/("""
											"""),_display_(if(mapPermiso.get("reportGraficoOcupacion1")!=null)/*965.64*/{_display_(Seq[Any](format.raw/*965.65*/("""
												"""),format.raw/*966.13*/("""<div class="dropdown-divider" style="border-color:black"></div>
												<a class="dropdown-item" href='/reportGraficoMovUso/'>Ocupaci&oacute;n por Grupo</a>
												<a class="dropdown-item" href='/reportGraficoMovUsoPropiedad/'>Ocupaci&oacute;n por Propiedad</a>
											""")))} else {null} ),format.raw/*969.13*/("""
										"""),format.raw/*970.11*/("""</li>
									""")))} else {null} ),format.raw/*971.11*/("""
								"""),format.raw/*972.9*/("""</ul>
							</li>
		        		""")))} else {null} ),format.raw/*974.14*/("""
		        		"""),_display_(if(mapPermiso.get("mnuREPORTEEJECUTIVO")!=null && mapPermiso.get("reporteEjecutivo1")!=null)/*975.106*/{_display_(Seq[Any](format.raw/*975.107*/("""
		        			"""),format.raw/*976.14*/("""<a class="dropdown-item" href="/reporteEjecutivo1/" onclick="document.getElementById('enProceso').style.display='block';">Reporte Ejecutivo</a>
		        		""")))} else {null} ),format.raw/*977.14*/("""
		        		"""),_display_(if(mapPermiso.get("mnuREPORTEGERENCIAL")!=null)/*978.61*/{_display_(Seq[Any](format.raw/*978.62*/("""
		        			"""),format.raw/*979.14*/("""<li class="dropdown-submenu pull-left">
								<a class="dropdown-item dropdown-toggle" href="#">Reporte Gerencial</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("reporteGerencial1")!=null)/*982.56*/{_display_(Seq[Any](format.raw/*982.57*/("""
										"""),format.raw/*983.11*/("""<li><a class="dropdown-item" href='/reporteGerencial/'>Reporte Gerencial</a></li>
									""")))} else {null} ),format.raw/*984.11*/("""
									"""),_display_(if(mapPermiso.get("reporteGerencial2")!=null)/*985.56*/{_display_(Seq[Any](format.raw/*985.57*/("""
										"""),format.raw/*986.11*/("""<li><a class="dropdown-item" href='/reporteGerencialGrupo/'>Reporte por Grupos</a></li>
									""")))} else {null} ),format.raw/*987.11*/("""
									"""),_display_(if(mapPermiso.get("reporteGerencial2")!=null)/*988.56*/{_display_(Seq[Any](format.raw/*988.57*/("""
										"""),format.raw/*989.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href='/reporteGerencialKG/'>Toneladas movidas por Año</a></li>
										<li><a class="dropdown-item" href='/routes2/reportGerenKGPorPeriodo0/'>Toneladas movidas por Periodo</a></li>
									""")))} else {null} ),format.raw/*992.11*/("""
									"""),_display_(if(mapPermiso.get("reporteGerencial1")!=null)/*993.56*/{_display_(Seq[Any](format.raw/*993.57*/("""
										"""),format.raw/*994.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href='/reporteGerencialVentas/'>Grafico Ventas</a></li>
									""")))} else {null} ),format.raw/*996.11*/("""
									
								"""),format.raw/*998.9*/("""</ul>
							</li>
		        		""")))} else {null} ),format.raw/*1000.14*/("""
		        		"""),_display_(if(mapPermiso.get("mnuREPORTMOVIMIENTOS")!=null)/*1001.62*/{_display_(Seq[Any](format.raw/*1001.63*/("""
		        			"""),format.raw/*1002.14*/("""<li class="dropdown-submenu pull-left">
								<a class="dropdown-item dropdown-toggle" href="#">Movimientos</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("reporteMovimientos0")!=null)/*1005.58*/{_display_(Seq[Any](format.raw/*1005.59*/("""
										"""),format.raw/*1006.11*/("""<li><a class="dropdown-item" href='/reporteMovimientosPeriodoAgrupado/'>Por Fecha (Agrupado)</a></li>
									""")))} else {null} ),format.raw/*1007.11*/("""
									"""),_display_(if(mapPermiso.get("reporteMovimientos1")!=null)/*1008.58*/{_display_(Seq[Any](format.raw/*1008.59*/("""
										"""),format.raw/*1009.11*/("""<li><a class="dropdown-item" href='/reporteMovimientosPeriodo/'>Por Fecha (Valorizado)</a></li>
									""")))} else {null} ),format.raw/*1010.11*/("""
									"""),_display_(if(mapPermiso.get("reporteMovimientos2")!=null)/*1011.58*/{_display_(Seq[Any](format.raw/*1011.59*/("""
										"""),format.raw/*1012.11*/("""<li><a class="dropdown-item" href='/reporteMovimientosListaProyectosIE/'>Ordenado por Ingreso-Egreso</a></li>
									""")))} else {null} ),format.raw/*1013.11*/("""
									
									"""),_display_(if(mapPermiso.get("reporteMovimientos0")!=null)/*1015.58*/ {_display_(Seq[Any](format.raw/*1015.60*/("""
										"""),format.raw/*1016.11*/("""<div class="dropdown-divider" style="border-color: black"></div>
										<li><a class="dropdown-item" href='/routes2/reportePorProyectoAgrupado/'>
											Por Proyecto (Agrupado)</a></li>
									""")))} else {null} ),format.raw/*1019.11*/("""
									"""),_display_(if(mapPermiso.get("reporteMovimientos1")!=null)/*1020.58*/ {_display_(Seq[Any](format.raw/*1020.60*/("""
										"""),format.raw/*1021.11*/("""<li><a class="dropdown-item" href='/routes2/reportePorProyectoValorizado/'>
											Por Proyecto (Valorizado)</a></li>
									""")))} else {null} ),format.raw/*1023.11*/("""
									"""),_display_(if(mapPermiso.get("reporteMovimientos0")!=null)/*1024.58*/ {_display_(Seq[Any](format.raw/*1024.60*/("""
										"""),format.raw/*1025.11*/("""<li><a class="dropdown-item" href='/reporteMovSoloBodInternas0/'>Solo """),_display_(/*1025.82*/mapDiccionario/*1025.96*/.get("Bodegas")),format.raw/*1025.111*/(""" """),format.raw/*1025.112*/("""Internas</a></li>
									""")))} else {null} ),format.raw/*1026.11*/("""
									
									"""),_display_(if(mapPermiso.get("reporteExcedentes")!=null)/*1028.56*/{_display_(Seq[Any](format.raw/*1028.57*/("""
										"""),format.raw/*1029.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href='/reporteExcedentesListaProyectos/'>Excedentes por """),_display_(/*1030.97*/mapDiccionario/*1030.111*/.get("Bodega")),format.raw/*1030.125*/("""/Proyecto</a></li>
										<li><a class="dropdown-item" href='/routes2/reporteExcedentesListaEquipos/'>Excedentes por Equipo</a></li>
									""")))} else {null} ),format.raw/*1032.11*/("""
								"""),format.raw/*1033.9*/("""</ul>
							</li>
		        		""")))} else {null} ),format.raw/*1035.14*/("""
		        		"""),_display_(if(mapPermiso.get("mnuREPORTESTADOS")!=null && mapPermiso.get("reportEstados")!=null)/*1036.99*/{_display_(Seq[Any](format.raw/*1036.100*/("""
							"""),format.raw/*1037.8*/("""<li class="dropdown-submenu pull-left">
								<a class="dropdown-item dropdown-toggle" href="#">Estados</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("reportEstados")!=null)/*1040.52*/{_display_(Seq[Any](format.raw/*1040.53*/("""
										"""),format.raw/*1041.11*/("""<li><a class="dropdown-item" href='/reporteEstadosTodos/'>Estados por """),_display_(/*1041.82*/mapDiccionario/*1041.96*/.get("Bodega")),format.raw/*1041.110*/("""/Proyecto</a></li>
										<li><a class="dropdown-item" href='/reporteEstadosPeriodo0/'>Estados por Periodo</a></li>
										<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href='/reporteEstadosAll0/'>Reparaciones por """),_display_(/*1044.86*/mapDiccionario/*1044.100*/.get("Bodega")),format.raw/*1044.114*/("""/Proyecto</a></li>
										<li><a class="dropdown-item" href='/routes2/reporteEstadosPer0/'>Reparaciones por Periodo</a></li>
										
									""")))} else {null} ),format.raw/*1047.11*/("""
								"""),format.raw/*1048.9*/("""</ul>
							</li>
		        		""")))} else {null} ),format.raw/*1050.14*/("""
		        		"""),_display_(if(mapPermiso.get("mnuTRAZABILIDAD")!=null && mapPermiso.get("reportTrazaEquipo")!=null)/*1051.102*/{_display_(Seq[Any](format.raw/*1051.103*/("""
		        			"""),format.raw/*1052.14*/("""<a class="dropdown-item" href="/reportTrazaEquipo/">Trazabilidad</a>
		        		""")))} else {null} ),format.raw/*1053.14*/("""

		        		"""),_display_(if(mapPermiso.get("mnuPROFORMAS")!=null)/*1055.54*/{_display_(Seq[Any](format.raw/*1055.55*/("""
		        			"""),format.raw/*1056.14*/("""<li class="dropdown-submenu pull-left">
								<a class="dropdown-item dropdown-toggle" href="#">"""),_display_(/*1057.60*/mapDiccionario/*1057.74*/.get("Proforma")),format.raw/*1057.90*/("""s</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("reportFacturaDetalleProyecto")!=null)/*1059.67*/{_display_(Seq[Any](format.raw/*1059.68*/("""
										"""),format.raw/*1060.11*/("""<li><a class="dropdown-item" href='/reportFacturaPeriodo/'>"""),_display_(/*1060.71*/mapDiccionario/*1060.85*/.get("tipoBodega_2")),format.raw/*1060.105*/("""</a></li>
										<li><a class="dropdown-item" href='/reportFacturaPeriodoH/'>Pre-Factura Simple</a></li>
									""")))} else {null} ),format.raw/*1062.11*/("""
									"""),_display_(if(mapPermiso.get("proformaResumen")!=null)/*1063.54*/{_display_(Seq[Any](format.raw/*1063.55*/("""
										"""),format.raw/*1064.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href='/reportFacturaPeriodoResumen0/'>Resumen y Detalle (agrupado)</a></li>
										<li><a class="dropdown-item" href='/reportFacturaPeriodoResumen/'>Resumen y Detalle (por sucursal)</a></li>
										<li><a class="dropdown-item" href='/reportFacturaPeriodoResumen2/'>Resumen y Detalle (por comercial)</a></li>
										<div class="dropdown-divider" style="border-color:black"></div>
									""")))} else {null} ),format.raw/*1069.11*/("""
									"""),_display_(if(mapPermiso.get("proformaConsolidado")!=null)/*1070.58*/{_display_(Seq[Any](format.raw/*1070.59*/("""
										"""),format.raw/*1071.11*/("""<li><a class="dropdown-item" href='/reportFacturaConsolidado/'>Consolidado (varios Meses)</a></li>
									""")))} else {null} ),format.raw/*1072.11*/("""
									"""),_display_(if(mapPermiso.get("proformaConsolidado")!=null)/*1073.58*/{_display_(Seq[Any](format.raw/*1073.59*/("""
										"""),format.raw/*1074.11*/("""<li><a class="dropdown-item" href='/reportFactConsolconGrupo/'>Consolidado (detalle por grupos)</a></li>
									""")))} else {null} ),format.raw/*1075.11*/("""
									"""),_display_(if(mapPermiso.get("proformaConsolidado")!=null && !mapDiccionario.get("nEmpresa").equals("SM8 DE MEXICO") && !mapDiccionario.get("nEmpresa").equals("ALZATEC"))/*1076.170*/{_display_(Seq[Any](format.raw/*1076.171*/("""
										"""),format.raw/*1077.11*/("""<li><a class="dropdown-item" href='/reportFactConsolconEquipos/'>Consolidado (detalle por equipos)</a></li>
									""")))} else {null} ),format.raw/*1078.11*/("""
									"""),_display_(if(mapPermiso.get("proformaListado")!=null && userMnu.getId_tipoUsuario().toInt != 7)/*1079.96*/{_display_(Seq[Any](format.raw/*1079.97*/("""
										"""),format.raw/*1080.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href='/proformaListaPeriodo/'>Listado de """),_display_(/*1081.82*/mapDiccionario/*1081.96*/.get("Proforma")),format.raw/*1081.112*/("""s</a></li>
										<li><a class="dropdown-item" href='/proformaListaHPeriodo/'>Listado de (Pre-Factura Simple)</a></li>

									""")))} else {null} ),format.raw/*1084.11*/("""
									"""),format.raw/*1085.10*/("""<div class="dropdown-divider" style="border-color:black"></div>
									"""),_display_(if(mapPermiso.get("proformaHaceAjustes")!=null)/*1086.58*/{_display_(Seq[Any](format.raw/*1086.59*/("""
										"""),format.raw/*1087.11*/("""<li><a class="dropdown-item" href='/ajustesEp/'>Hacer Ajustes EP-"""),_display_(/*1087.77*/mapDiccionario/*1087.91*/.get("Proforma")),format.raw/*1087.107*/("""</a></li>
									""")))} else {null} ),format.raw/*1088.11*/("""
									"""),_display_(if(mapPermiso.get("proformaListaAjustes")!=null)/*1089.59*/{_display_(Seq[Any](format.raw/*1089.60*/("""
										"""),format.raw/*1090.11*/("""<li><a class="dropdown-item" href='/ajustesEpRpt/'>Reporte Ajustes X """),_display_(/*1090.81*/mapDiccionario/*1090.95*/.get("Bodega")),format.raw/*1090.109*/("""/Proyecto</a></li>
									""")))} else {null} ),format.raw/*1091.11*/("""
									"""),_display_(if(mapPermiso.get("proformaAjustesPorPeriodo")!=null)/*1092.64*/{_display_(Seq[Any](format.raw/*1092.65*/("""
										"""),format.raw/*1093.11*/("""<li><a class="dropdown-item" href='/ajustesPeriodoEpRpt1/'>Reporte Ajustes X Per&iacute;odo</a></li>
									""")))} else {null} ),format.raw/*1094.11*/("""
									
									"""),_display_(if(mapPermiso.get("reportVentasPorPeriodo")!=null)/*1096.61*/{_display_(Seq[Any](format.raw/*1096.62*/("""
										"""),format.raw/*1097.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href='/routes2/reportVentasPorPeriodo0/'>Solo Venta por Periodo</a></li>
									""")))} else {null} ),format.raw/*1099.11*/("""

									"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1"))/*1101.149*/{_display_(Seq[Any](format.raw/*1101.150*/("""
										"""),format.raw/*1102.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href='/routes3/estadosMantCobraArriendo0/'>MANT Cobra """),_display_(/*1103.95*/mapDiccionario/*1103.109*/.get("Arriendo")),format.raw/*1103.125*/(""" """),format.raw/*1103.126*/("""por Daños</a></li>
										<li><a class="dropdown-item" href='/routes3/estadosFacturaPeriodo/'>EP Cobra """),_display_(/*1104.89*/mapDiccionario/*1104.103*/.get("Arriendo")),format.raw/*1104.119*/(""" """),format.raw/*1104.120*/("""por Daños</a></li>
										<li><a class="dropdown-item" href='/routes3/proformaEstadoListaPeriodo/'>LISTA Cobra """),_display_(/*1105.97*/mapDiccionario/*1105.111*/.get("Arriendo")),format.raw/*1105.127*/(""" """),format.raw/*1105.128*/("""por Daños</a></li>
									""")))} else {null} ),format.raw/*1106.11*/("""
									
									
								"""),format.raw/*1109.9*/("""</ul>
							</li>
		        		""")))} else {null} ),format.raw/*1111.14*/("""

						"""),_display_(if(mapDiccionario.get("nEmpresa").equals("HOHE") && mapPermiso.get("reportesHOHE")!=null)/*1113.97*/{_display_(Seq[Any](format.raw/*1113.98*/("""
							"""),format.raw/*1114.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<li class="dropdown-submenu pull-left">
								<a class="dropdown-item dropdown-toggle" href="#">Tabla_Todo</a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href='/hoheReportTodo0/'>Todo por Per&iacute;odo</a></li>
									<li><a class="dropdown-item" href='/hoheTodoResumen0/'>Resumen por Per&iacute;odo</a></li>
									<li><a class="dropdown-item" href='/hoheMatrizVerticalInventario0/'>Matriz Inventario (p_min)</a></li>
									<li><a class="dropdown-item" href='/hoheMatrizVerticalInv0Coti/'>Matriz Inventario (por_coti)</a></li>
									<li><a class="dropdown-item" href='/hoheEstadoCotiOt/'>Estado de OT</a></li>
									<li><a class="dropdown-item" href='/hoheEstadoCotiSinOt/'>Estado de COTI</a></li>
								</ul>
							</li>
						""")))} else {null} ),format.raw/*1126.8*/("""
						
						"""),_display_(if(mapDiccionario.get("nEmpresa").equals("DOM") && mapPermiso.get("reportesDOM")!=null)/*1128.95*/{_display_(Seq[Any](format.raw/*1128.96*/("""
							"""),format.raw/*1129.8*/("""<div class="dropdown-divider" style="border-color:black"></div>
							<a class="dropdown-item" href='/routes2/domStockDiarioExcel0/'>Stock Diario EXCEL</a>
						""")))} else {null} ),format.raw/*1131.8*/("""
						
		        	"""),format.raw/*1133.12*/("""</ul>
		        </li>
			""")))} else {null} ),format.raw/*1135.5*/("""
			

			
			"""),format.raw/*1139.4*/("""<!-- =================================== -->
	        <!-- 			MENU COBRANZA	 		   	 -->  
	        <!-- =================================== -->
 			 <!--"""),_display_(if(mapPermiso.get("mnuFACTURA")!=null && mapPermiso.get("parametro.mnuFACTURA")!=null && mapPermiso.get("parametro.mnuFACTURA").equals("1"))/*1142.151*/{_display_(Seq[Any](format.raw/*1142.152*/("""
 				"""),format.raw/*1143.6*/("""<li class="nav-item dropdown">
					<a id="mnuFACTURA" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	Cobranza
		        	</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
		        		"""),_display_(if(mapPermiso.get("subMenu1")!=null)/*1148.50*/{_display_(Seq[Any](format.raw/*1148.51*/("""
		        			"""),format.raw/*1149.14*/("""<li class="dropdown-submenu pull-left">
								<a class="dropdown-item dropdown-toggle" href="#">"""),_display_(/*1150.60*/mapDiccionario/*1150.74*/.get("Proforma")),format.raw/*1150.90*/("""s</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("facturaPasaVenta")!=null)/*1152.55*/{_display_(Seq[Any](format.raw/*1152.56*/("""	
										"""),format.raw/*1153.11*/("""<li><a class="dropdown-item" href='/proformaListaPasoVenta/'>Pasar a Ventas</a></li>
									""")))} else {null} ),format.raw/*1154.11*/("""
								"""),format.raw/*1155.9*/("""</ul>
							</li>
		        		""")))} else {null} ),format.raw/*1157.14*/("""
		        		"""),_display_(if(mapPermiso.get("subMenu2")!=null)/*1158.50*/{_display_(Seq[Any](format.raw/*1158.51*/("""
		        			"""),format.raw/*1159.14*/("""<li class="dropdown-submenu pull-left">
								<a class="dropdown-item dropdown-toggle" href="#">Ventas</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("facturaModificar")!=null)/*1162.55*/{_display_(Seq[Any](format.raw/*1162.56*/("""	
										"""),format.raw/*1163.11*/("""<li><a class="dropdown-item" href='/ventasLista/'>Listar/Modificar</a></li>
									""")))} else {null} ),format.raw/*1164.11*/("""
									"""),_display_(if(mapPermiso.get("facturaIngresar")!=null)/*1165.54*/{_display_(Seq[Any](format.raw/*1165.55*/("""
										"""),format.raw/*1166.11*/("""<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href='/ventasIngreso/'>Ingresar</a></li>
									""")))} else {null} ),format.raw/*1168.11*/("""
								"""),format.raw/*1169.9*/("""</ul>
							</li>
		        		""")))} else {null} ),format.raw/*1171.14*/("""
		        		"""),_display_(if(mapPermiso.get("subMenu3")!=null)/*1172.50*/{_display_(Seq[Any](format.raw/*1172.51*/("""
		        			"""),format.raw/*1173.14*/("""<li class="dropdown-submenu pull-left">
								<a class="dropdown-item dropdown-toggle" href="#">Seguimiento</a>
								<ul class="dropdown-menu">
									"""),_display_(if(mapPermiso.get("facturaReporte1")!=null)/*1176.54*/{_display_(Seq[Any](format.raw/*1176.55*/("""	
										"""),format.raw/*1177.11*/("""<li><a class="dropdown-item" href='/ivaPorPagar/'>Iva X pagar</a></li>
									""")))} else {null} ),format.raw/*1178.11*/("""
									"""),_display_(if(mapPermiso.get("facturaReporte2")!=null)/*1179.54*/{_display_(Seq[Any](format.raw/*1179.55*/("""
										"""),format.raw/*1180.11*/("""<li><a class="dropdown-item" href='/ventasMes/'>Ventas del Mes</a></li>
									""")))} else {null} ),format.raw/*1181.11*/("""
									"""),format.raw/*1182.10*/("""<div class="dropdown-divider" style="border-color:black"></div>
									<li class="dropdown-submenu">
										<a class="dropdown-item dropdown-toggle" href="#">Facturas X cobrarl</a>
										<ul class="dropdown-menu">
											"""),_display_(if(mapPermiso.get("facturaReporte3")!=null)/*1186.56*/{_display_(Seq[Any](format.raw/*1186.57*/("""
												"""),format.raw/*1187.13*/("""<li><a class="dropdown-item" href='/cobrarFacturaVencida/'>Vencidas</a></li>
											""")))} else {null} ),format.raw/*1188.13*/("""
											"""),_display_(if(mapPermiso.get("facturaReporte4")!=null)/*1189.56*/{_display_(Seq[Any](format.raw/*1189.57*/("""
												"""),format.raw/*1190.13*/("""<li><a class="dropdown-item" href='/cobrarFacturaPorVencer/'>Por Vencer</a></li>
											""")))} else {null} ),format.raw/*1191.13*/("""
											"""),_display_(if(mapPermiso.get("facturaReporte5")!=null)/*1192.56*/{_display_(Seq[Any](format.raw/*1192.57*/("""
												"""),format.raw/*1193.13*/("""<li><a class="dropdown-item" href='/cobrarFacturaTodas/'>Todas</a></li>
											""")))} else {null} ),format.raw/*1194.13*/("""
										"""),format.raw/*1195.11*/("""</ul>
									</li>
									"""),_display_(if(mapPermiso.get("facturaReporte6")!=null)/*1197.54*/{_display_(Seq[Any](format.raw/*1197.55*/("""
										"""),format.raw/*1198.11*/("""<li><a class="dropdown-item" href='/graficoVentas/'>Gr&aacute;fico de Ventas</a></li>
									""")))} else {null} ),format.raw/*1199.11*/("""
								"""),format.raw/*1200.9*/("""</ul>
							</li>
		        		""")))} else {null} ),format.raw/*1202.14*/("""
		        	"""),format.raw/*1203.12*/("""</ul>
		        </li>
 			""")))} else {null} ),format.raw/*1205.6*/(""" """),format.raw/*1205.7*/("""-->
			
			<!-- =================================== -->
	        <!-- 			MENU AYUDA	 		   	 -->  
	        <!-- =================================== -->
 			<li class="nav-item dropdown">
				<a id="mnuAYUDA" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				 	Ayuda
	        	</a>
	        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
					<li class="dropdown-submenu pull-left">
								<a class="dropdown-item dropdown-toggle" href="#">Manuales</a>
								<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="/manualBasico/">Manual B&aacute;sico</a></li>
										<a class="dropdown-item" href="/manualPrimerosPasos/">Primeros Pasos</a>
										<div class="dropdown-divider" style="border-color:black"></div>
										<li><a class="dropdown-item" href='#' onclick="mostrarPDFmanuales('MADA3-V3-BAJAS.pdf')">BAJAS</a></li>
										<li><a class="dropdown-item" href='#' onclick="mostrarPDFmanuales('MADA3-V3-BODEGAS.pdf')">"""),_display_(/*1222.103*/mapDiccionario/*1222.117*/.get("BODEGAS")),format.raw/*1222.132*/("""</a></li>
										<li><a class="dropdown-item" href='#' onclick="mostrarPDFmanuales('MADA3-V3-COMPRAS.pdf')">COMPRAS</a></li>
										<li><a class="dropdown-item" href='#' onclick="mostrarPDFmanuales('MADA3-V3-COTIZAR.pdf')">COTIZAR</a></li>
										<li><a class="dropdown-item" href='#' onclick="mostrarPDFmanuales('MADA3-V3-DISPONIBILIDAD.pdf')">DISPONIBILIDAD</a></li>
										<li><a class="dropdown-item" href='#' onclick="mostrarPDFmanuales('MADA3-V3-MOVIMIENTOS.pdf')">MOVIMIENTOS</a></li>
										<li><a class="dropdown-item" href='#' onclick="mostrarPDFmanuales('MADA_ODO.pdf')">ODO</a></li>
										<li><a class="dropdown-item" href='#' onclick="mostrarPDFmanuales('MADA3-V3-PLANES.pdf')">PLANES</a></li>
										<li><a class="dropdown-item" href='#' onclick="mostrarPDFmanuales('MADA3-V3-TABLAS.pdf')">TABLAS</a></li>
								</ul>
					</li>
					<li class="dropdown-submenu pull-left">
								<a class="dropdown-item dropdown-toggle" href="#">Tutoriales</a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="https://youtu.be/74R1kYSbw40?t=371" target="_blank">Movimientos</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/nJhv2Gp3U3w?t=275" target="_blank">Compras</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/PIyDUQNlJ2M?t=33" target="_blank">Bajas</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/11S3DbFe86w?t=274" target="_blank">Cotizar</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/zSp022p_G18?t=172" target="_blank">Planes de Mantenimiento</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/CQa_Hb4KNGw?t=285" target="_blank">Disponibilidad</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/AutMUStAqYM?t=211" target="_blank">Evaluación de Devoluciones</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/bZrkJX8VZz4?t=80" target="_blank">Reportes Gráficos</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/4J9fYgQQToE?t=46" target="_blank">Precios</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/DhJ6957ofgg?t=117" target="_blank">Facturación y Cobro a Clientes</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/ziENFavcrO8?t=64" target="_blank">Presupuestos</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/NR88UwMWxj0?t=13" target="_blank">Creación de Equipos y Grupos</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/YewC3d5MbEA?t=12" target="_blank">Revisión de Inventarios</a></li>
									<li><a class="dropdown-item" href="https://youtu.be/XMIQ40JZO_U?t=46" target="_blank">Varios</a></li>
								</ul>
					</li>
					<div class="dropdown-divider" style="border-color:black"></div>
					<li><a class="dropdown-item" href="#" onclick="$('#modalContacto').modal('show')">Contactos</a></li>
					<div class="dropdown-divider" style="border-color:black"></div>
					<li><a class="dropdown-item"  href="#" onclick="$('#modalCreditos').modal('show')">Cr&eacute;ditos</a></li>
	        	</ul>
	        </li>
										
										
										
										


			<!-- =================================== -->
	        <!-- 			MENU TOOLS	 		   	 -->  
	        <!-- =================================== -->
			"""),_display_(if(userMnu.getUserName().equals("Admin") || userMnu.getUserName().equals("PBA"))/*1266.85*/{_display_(Seq[Any](format.raw/*1266.86*/("""
				"""),format.raw/*1267.5*/("""<li class="nav-item dropdown">
					<a id="mnuADMIN" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	Tools
		        	</a>
		        	<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" style="background-color: #e3f2fd;">
	        			<a class="dropdown-item" href="/administraModulos/">Modulos</a>
	        			<a class="dropdown-item" href="/administrarEmisor/">Emisor Tributario</a>
		        	</ul>
		        </li>
			""")))} else {null} ),format.raw/*1276.5*/("""
			
			
			
 		"""),format.raw/*1280.4*/("""</ul>
 		
 		
 		<!-- =================================== -->
        <!-- 		USUARIO Y EXIT				 -->  
        <!-- =================================== -->
   		<div align="right">
    		<ul class="navbar-nav mr-auto">
				<li class="nav-item ">
					"""),_display_(if(userMnu.getId_usuario().toInt==0)/*1289.43*/{_display_(Seq[Any](format.raw/*1289.44*/("""
						"""),format.raw/*1290.7*/("""<a class="nav-link" href="/cpanel/">"""),_display_(/*1290.44*/userMnu/*1290.51*/.getUserName()),format.raw/*1290.65*/("""</a>
					""")))}else/*1291.11*/{_display_(Seq[Any](format.raw/*1291.12*/("""
						"""),format.raw/*1292.7*/("""<a class="nav-link" href="#" onclick="formUsuarioSelect2('"""),_display_(/*1292.66*/userMnu/*1292.73*/.getId_usuario()),format.raw/*1292.89*/("""');"> """),_display_(/*1292.96*/userMnu/*1292.103*/.getUserName()),format.raw/*1292.117*/("""</a>
					""")))}),format.raw/*1293.7*/("""
		      	"""),format.raw/*1294.10*/("""</li>
		      	&nbsp;
		      	<li class="divider-vertical border"></li>
		      	<li class="nav-item active">
		        	<a class="nav-link" href="/">EXIT</a>
				</li>
     		</ul>
    	</div>
 	</div>
</nav>


<div id="updateUsuario2"></div>




	<div id='muestraPDFmanuales' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>MANUAL</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="rutaPDFmanuales"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="modalCreditos" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		  <div class="modal-dialog modal-sm modal-dialog-scrollable" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
				MADA VERSIÓN 3.0
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
					<p>“Software para la administración, control y gestión de alquileres y ventas”.</p>
					<br><br>
					Propiedad de INQSOL SpA.
					<br>
					Rut 76.312.175-5, Chile.
		      </div>
		    </div>
		  </div>
	</div>

	<div id="modalContacto" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		  <div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
		    <div class="modal-content" style="width:90%">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
					<form action="/sendEmail/" method="POST">
						<table class="table table-sm table-bordered table-condensed table-hover table-fluid">
							<tbody>
								<tr>
							        <td style="text-align:left;width:35%;">NOMBRE (*):</td>
							        <td style="text-align:left">
							        	<input type="text" class="form-control form-control-sm" 
											name="nombre" autocomplete="off" required
										</td>
								</tr>
								<tr>
							        <td style="text-align:left;width:35%;">E-MAIL (*):</td>
							        <td style="text-align:left">
							        	<input type="email" class="form-control form-control-sm" 
											name="email" autocomplete="off" required
										</td>
								</tr>
								<tr>
							        <td style="text-align:left;width:35%;">TELEFONO:</td>
							        <td style="text-align:left">
							        	<input type="text" class="form-control form-control-sm" 
											name="fono" autocomplete="off"
										</td>
								</tr>
								<tr>
							        <td style="text-align:left;">MENSAJE:</td>
							        <td style="text-align:left">
							        	<textarea class="form-control form-control-sm" rows="5" 
											name="mensaje" autocomplete="off"></textarea>
							        </td>
								</tr>
							</tbody>
						</table>
						<div class="row">
							<div class="col-sm-12" style="text-align:center">
								<button type="submit" class="btn btn-sm  btn-success" style="font-size: 10px;"  onclick="this.setAttribute('disabled',true)">ENVIAR</button>
							</div>
						</div>
					</form>
		      </div>
			  <div class="modal-footer">
				Sr. Jorge Ignacio Prieto &#60;jiprieto&#64;inqsol.cl> +56 9 4614 9191<br>
		      	Sr. Pedro Barros &#60;pbarros&#64;inqsol.cl> +56 9 8299 1918
		      </div>
		    </div>
		  </div>
		</div>






<script>
		
	$('.dropdown-menu a.dropdown-toggle').on('click', function(e) """),format.raw/*1417.64*/("""{"""),format.raw/*1417.65*/("""
		"""),format.raw/*1418.3*/("""if (!$(this).next().hasClass('show')) """),format.raw/*1418.41*/("""{"""),format.raw/*1418.42*/("""
		  	"""),format.raw/*1419.6*/("""$(this).parents('.dropdown-menu').first().find('.show').removeClass("show");
		"""),format.raw/*1420.3*/("""}"""),format.raw/*1420.4*/("""
	  	"""),format.raw/*1421.5*/("""var $subMenu = $(this).next(".dropdown-menu");
	  	$subMenu.toggleClass('show');
	  	$(this).parents('li.nav-item.dropdown.show').on('hidden.bs.dropdown', function(e) """),format.raw/*1423.87*/("""{"""),format.raw/*1423.88*/("""
	    	"""),format.raw/*1424.7*/("""$('.dropdown-submenu .show').removeClass("show");
	  	"""),format.raw/*1425.5*/("""}"""),format.raw/*1425.6*/(""");
	  	return false;
	"""),format.raw/*1427.2*/("""}"""),format.raw/*1427.3*/(""");

	$(document).ready(function() """),format.raw/*1429.31*/("""{"""),format.raw/*1429.32*/("""
 	    """),format.raw/*1430.7*/("""/* if(!"""),_display_(/*1430.15*/marcar/*1430.21*/.equals("")),format.raw/*1430.32*/(""") document.getElementById(""""),_display_(/*1430.60*/marcar),format.raw/*1430.66*/("""").style.color='blue';
	    if(!"""),_display_(/*1431.11*/marcar/*1431.17*/.equals("")),format.raw/*1431.28*/(""") document.getElementById(""""),_display_(/*1431.56*/marcar),format.raw/*1431.62*/("""").style.fontSize = "1.2em"; */
	"""),format.raw/*1432.2*/("""}"""),format.raw/*1432.3*/(""");
	
	
	
	function formUsuarioSelect2(id_usuario)"""),format.raw/*1436.41*/("""{"""),format.raw/*1436.42*/("""
		"""),format.raw/*1437.3*/("""var formData = new FormData();
	  	formData.append('id_usuario',id_usuario);
        $.ajax("""),format.raw/*1439.16*/("""{"""),format.raw/*1439.17*/("""
            """),format.raw/*1440.13*/("""url: "/usuarioModalUpdate2",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1447.36*/("""{"""),format.raw/*1447.37*/("""
	     		"""),format.raw/*1448.9*/("""if(respuesta=="error")"""),format.raw/*1448.31*/("""{"""),format.raw/*1448.32*/("""
	     			"""),format.raw/*1449.10*/("""alertify.alert(msgError, function () """),format.raw/*1449.47*/("""{"""),format.raw/*1449.48*/("""
		     			"""),format.raw/*1450.11*/("""location.href = "/";
		     		"""),format.raw/*1451.10*/("""}"""),format.raw/*1451.11*/(""");
	     		"""),format.raw/*1452.9*/("""}"""),format.raw/*1452.10*/("""else if(respuesta == "noCorresponde")"""),format.raw/*1452.47*/("""{"""),format.raw/*1452.48*/("""
	     			
	     		"""),format.raw/*1454.9*/("""}"""),format.raw/*1454.10*/("""else"""),format.raw/*1454.14*/("""{"""),format.raw/*1454.15*/("""
	     			"""),format.raw/*1455.10*/("""document.getElementById('updateUsuario2').innerHTML=respuesta; 
	     			$('#modalUpdateUsuario2').modal('show');
	     		"""),format.raw/*1457.9*/("""}"""),format.raw/*1457.10*/("""
	     	"""),format.raw/*1458.8*/("""}"""),format.raw/*1458.9*/("""
        """),format.raw/*1459.9*/("""}"""),format.raw/*1459.10*/(""");
    """),format.raw/*1460.5*/("""}"""),format.raw/*1460.6*/("""
	
	"""),format.raw/*1462.2*/("""function validaUsuarioUpdate2(usuario,anterior)"""),format.raw/*1462.49*/("""{"""),format.raw/*1462.50*/("""
		"""),format.raw/*1463.3*/("""if(usuario!=anterior)"""),format.raw/*1463.24*/("""{"""),format.raw/*1463.25*/("""
			"""),format.raw/*1464.4*/("""var formData = new FormData();
		  	formData.append('userName',usuario);
	        $.ajax("""),format.raw/*1466.17*/("""{"""),format.raw/*1466.18*/("""
	            """),format.raw/*1467.14*/("""url: "/usuarioValida2",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*1474.37*/("""{"""),format.raw/*1474.38*/("""
		     		"""),format.raw/*1475.10*/("""if(respuesta=="error")"""),format.raw/*1475.32*/("""{"""),format.raw/*1475.33*/("""
		     			"""),format.raw/*1476.11*/("""alertify.alert(msgError, function () """),format.raw/*1476.48*/("""{"""),format.raw/*1476.49*/("""
			     			"""),format.raw/*1477.12*/("""location.href = "/";
			     		"""),format.raw/*1478.11*/("""}"""),format.raw/*1478.12*/(""");
		     		"""),format.raw/*1479.10*/("""}"""),format.raw/*1479.11*/("""
		     		"""),format.raw/*1480.10*/("""if(respuesta.status)"""),format.raw/*1480.30*/("""{"""),format.raw/*1480.31*/("""
		     			"""),format.raw/*1481.11*/("""alertify.alert("'El nombre de usuario ya existe, intente con otro nombre'", function () """),format.raw/*1481.99*/("""{"""),format.raw/*1481.100*/("""
		     				"""),format.raw/*1482.12*/("""$("#userNameUpdate2").val(anterior);
			     		"""),format.raw/*1483.11*/("""}"""),format.raw/*1483.12*/(""");
		     		"""),format.raw/*1484.10*/("""}"""),format.raw/*1484.11*/("""
		     	"""),format.raw/*1485.9*/("""}"""),format.raw/*1485.10*/("""
	        """),format.raw/*1486.10*/("""}"""),format.raw/*1486.11*/(""");
		"""),format.raw/*1487.3*/("""}"""),format.raw/*1487.4*/("""
	"""),format.raw/*1488.2*/("""}"""),format.raw/*1488.3*/("""
	
	"""),format.raw/*1490.2*/("""const mostrarPDFmanuales = (nombrePDF) => """),format.raw/*1490.44*/("""{"""),format.raw/*1490.45*/("""
		  """),format.raw/*1491.5*/("""document.getElementById('rutaPDFmanuales').innerHTML="<object data='/showPdfManuales/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDFmanuales').modal('show');
	"""),format.raw/*1493.2*/("""}"""),format.raw/*1493.3*/("""
	
	"""),format.raw/*1495.2*/("""//RESTRINGE EL TIEMPO DE INACTIVIDAD EN EL SISTEMA
		$(document).ready(function() """),format.raw/*1496.32*/("""{"""),format.raw/*1496.33*/("""
			"""),format.raw/*1497.4*/("""resetTimer();
		"""),format.raw/*1498.3*/("""}"""),format.raw/*1498.4*/(""");
	
	    var time;
	    window.onload = resetTimer;
	    // DOM Events
	    document.onmousemove = resetTimer;
	    document.onkeydown = resetTimer;
		document.onload = resetTimer;
		document.onclick = resetTimer;
	
	    function logout() """),format.raw/*1508.24*/("""{"""),format.raw/*1508.25*/("""
	        """),format.raw/*1509.10*/("""alert("Tiempo de inactividad excedido. Por seguridad vuelva a ingresar usuario y clave");
	        location.href = '/';
	    """),format.raw/*1511.6*/("""}"""),format.raw/*1511.7*/("""
	
	    """),format.raw/*1513.6*/("""function resetTimer() """),format.raw/*1513.28*/("""{"""),format.raw/*1513.29*/("""
	        """),format.raw/*1514.10*/("""clearTimeout(time);
	   		time = setTimeout(logout, 10800000);
	        // 1000 milliseconds = 1 second
	    """),format.raw/*1517.6*/("""}"""),format.raw/*1517.7*/("""


	
"""),format.raw/*1521.1*/("""</script>








		


"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,marcar:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,marcar)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,marcar) => apply(mapDiccionario,mapPermiso,userMnu,marcar)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/menues.scala.html
                  HASH: c3336af05dae90054412146f6b2d100d86bdf6b9
                  MATRIX: 1002->1|1208->114|1235->115|1307->160|1335->161|1365->165|1412->186|1439->187|1468->189|1523->216|1552->217|1583->221|1690->301|1718->302|1747->304|1808->337|1837->338|1868->342|1966->413|1994->414|2023->416|2096->461|2125->462|2156->466|2342->625|2370->626|2398->627|3076->1278|3115->1279|3147->1284|3352->1445|3391->1456|3493->1531|3520->1537|3549->1538|4073->2034|4113->2035|4146->2041|4565->2433|4604->2434|4639->2442|4862->2638|4901->2639|4946->2656|5085->2751|5166->2805|5205->2806|5244->2817|5393->2922|5489->2991|5528->2992|5567->3003|5716->3108|5806->3171|5845->3172|5884->3183|6211->3466|6302->3530|6341->3531|6380->3542|6602->3720|6638->3729|6707->3755|6781->3802|6820->3803|6855->3811|6971->3900|6994->3914|7042->3940|7160->4030|7200->4031|7240->4042|7343->4117|7368->4131|7401->4141|7432->4142|7554->4236|7578->4250|7611->4260|7642->4261|7773->4364|7798->4378|7831->4388|7862->4389|8006->4488|8084->4538|8124->4539|8164->4550|8344->4702|8369->4716|8402->4726|8433->4727|8554->4820|8579->4834|8612->4844|8643->4845|8706->4863|8786->4915|8826->4916|8866->4927|9039->5072|9063->5086|9096->5096|9127->5097|9190->5115|9227->5124|9297->5150|9332->5157|9597->5393|9638->5394|9674->5402|9814->5498|9891->5547|9931->5548|9967->5556|10112->5657|10153->5669|10223->5695|10273->5717|10586->6001|10627->6002|10661->6008|11080->6399|11120->6400|11156->6408|11375->6599|11415->6600|11556->6712|11597->6713|11638->6725|11772->6839|11812->6840|11853->6852|11981->6948|12037->6959|12118->7012|12158->7013|12299->7125|12340->7126|12381->7138|12529->7266|12569->7267|12610->7279|12755->7392|12811->7403|12897->7461|12937->7462|12977->7473|13129->7580|13209->7632|13249->7633|13289->7644|14231->8541|14323->8605|14363->8606|14403->8617|14621->8790|14658->8799|14728->8825|14800->8869|14840->8870|14876->8878|14993->8967|15017->8981|15066->9007|15181->9094|15221->9095|15261->9106|15349->9166|15373->9180|15405->9190|15465->9205|15551->9263|15591->9264|15631->9275|15719->9335|15743->9349|15775->9359|15835->9374|15931->9442|15971->9443|16011->9454|16120->9535|16145->9549|16178->9559|16238->9574|16324->9632|16364->9633|16404->9644|16584->9796|16608->9810|16645->9824|16676->9825|16707->9827|16732->9841|16765->9851|16826->9867|16911->9924|16951->9925|16991->9936|17175->10092|17200->10106|17233->10116|17348->10203|17372->10217|17405->10227|17641->10434|17666->10448|17699->10458|17811->10542|17835->10556|17868->10566|17899->10567|17933->10572|17958->10586|17995->10600|18055->10615|18142->10674|18182->10675|18222->10686|18384->10820|18408->10834|18440->10844|18500->10859|18537->10868|18607->10894|18688->10947|18728->10948|18764->10956|18977->11141|19017->11142|19057->11153|19184->11235|19270->11293|19310->11294|19350->11305|19494->11404|19578->11460|19618->11461|19658->11472|20111->11880|20148->11889|20218->11915|20253->11922|20517->12157|20558->12158|20594->12166|20733->12261|20807->12307|20847->12308|20883->12316|21027->12416|21068->12428|21138->12454|21174->12462|21492->12751|21533->12752|21567->12758|22184->13346|22225->13347|22268->13361|22399->13448|22483->13504|22523->13505|22566->13519|22686->13595|22764->13645|22804->13646|22840->13654|22962->13732|23040->13782|23080->13783|23116->13791|23243->13874|23320->13923|23360->13924|23396->13932|23943->14435|24020->14484|24060->14485|24096->14493|24296->14649|24337->14661|24407->14687|24443->14695|24776->14999|24817->15000|24851->15006|25299->15426|25339->15427|25382->15441|25509->15524|25589->15576|25629->15577|25665->15585|26151->16027|26240->16088|26280->16089|26316->16097|26519->16256|26606->16315|26646->16316|26682->16324|27109->16707|27201->16771|27241->16772|27277->16780|27439->16914|27463->16928|27499->16942|27565->16964|27606->16976|27676->17002|27712->17010|27966->17236|28006->17237|28181->17383|28222->17384|28257->17391|28681->17787|28721->17788|28765->17803|28882->17876|28959->17925|28999->17926|29043->17941|29162->18016|29240->18066|29280->18067|29324->18082|29448->18162|29524->18210|29564->18211|29601->18220|30068->18643|30110->18656|30182->18684|30225->18699|30565->19010|30606->19011|30641->19018|31076->19425|31116->19426|31153->19435|31257->19511|31281->19525|31318->19539|31537->19714|31623->19772|31663->19773|31707->19788|31839->19876|31925->19934|31965->19935|32009->19950|32139->20036|32224->20093|32264->20094|32301->20103|32764->20522|32806->20535|32878->20563|32927->20568|32963->20576|33285->20869|33326->20870|33470->20985|33511->20986|33547->20994|33984->21403|34024->21404|34070->21421|34293->21599|34380->21658|34420->21659|34466->21676|34610->21775|34689->21826|34729->21827|34769->21838|34905->21929|34987->21983|35027->21984|35073->22001|35204->22087|35287->22142|35327->22143|35367->22154|35574->22316|35657->22371|35697->22372|35743->22389|35868->22469|35947->22520|35987->22521|36027->22532|36788->23248|36832->23263|36887->23298|36927->23299|36963->23307|37642->23958|37682->23959|37730->23978|37957->24160|38046->24221|38086->24222|38134->24241|38280->24342|38361->24395|38401->24396|38443->24409|38581->24502|38665->24558|38705->24559|38753->24578|38886->24666|38971->24723|39011->24724|39053->24737|39264->24903|39349->24960|39389->24961|39437->24980|39564->25062|39645->25115|39685->25116|39727->25129|40508->25865|40554->25882|42057->27357|42097->27358|42140->27372|42373->27577|42413->27578|42459->27595|42610->27701|42703->27766|42743->27767|42789->27784|42938->27888|43035->27957|43075->27958|43121->27975|43282->28091|43376->28157|43416->28158|43462->28175|43612->28280|43708->28348|43748->28349|43794->28366|43951->28478|44049->28548|44089->28549|44135->28566|44295->28681|44396->28754|44436->28755|44482->28772|44649->28894|44749->28966|44789->28967|44835->28984|45072->29176|45116->29191|45199->29229|45245->29246|45348->29318|45403->29329|45439->29337|45786->29655|45827->29656|45861->29662|46309->30082|46349->30083|46392->30097|46522->30183|46609->30242|46649->30243|46685->30251|46883->30405|46969->30463|47009->30464|47130->30557|47170->30558|47207->30567|47517->30833|47569->30856|47745->31004|47769->31018|47807->31033|47926->31124|47951->31138|47989->31153|48045->31165|48086->31177|48156->31203|48192->31211|48494->31484|48535->31485|48569->31491|48985->31879|49025->31880|49068->31894|49526->32308|49626->32380|49666->32381|49702->32389|49991->32634|50032->32646|50102->32672|50135->32677|50475->32988|50516->32989|50550->32995|50979->33396|51019->33397|51062->33411|51185->33490|51267->33544|51307->33545|51350->33559|51547->33712|51588->33724|51658->33750|51697->33761|52003->34038|52044->34039|52078->34045|52495->34434|52535->34435|52578->34449|52692->34519|52773->34572|52813->34573|52856->34587|52974->34661|53053->34712|53093->34713|53129->34721|53354->34917|53379->34931|53416->34945|53447->34946|53923->35378|54005->35432|54045->35433|54081->35441|54271->35587|54344->35632|54384->35633|54420->35641|54681->35858|54774->35923|54814->35924|54850->35932|55157->36211|55197->36212|55237->36223|55386->36327|55425->36337|55550->36434|55590->36435|55630->36446|55779->36550|55818->36560|55907->36604|55993->36662|56033->36663|56069->36671|56268->36842|56292->36856|56330->36872|56449->36963|56489->36964|56529->36975|56613->37031|56637->37045|56680->37065|56745->37085|56842->37154|56882->37155|56922->37166|57154->37353|57235->37406|57275->37407|57315->37418|57465->37523|57558->37588|57598->37589|57638->37600|57807->37741|57831->37755|57870->37771|57936->37792|57975->37802|58119->37918|58159->37919|58199->37930|58296->37999|58320->38013|58359->38029|58424->38049|58506->38103|58546->38104|58586->38115|58687->38188|58711->38202|58748->38216|58822->38245|58909->38304|58949->38305|58989->38316|59148->38430|59207->38460|59492->38701|59533->38713|59603->38739|59639->38747|59960->39039|60001->39040|60035->39046|60268->39252|60292->39266|60329->39281|60370->39293|60575->39470|60615->39471|60658->39485|60751->39550|60775->39564|60813->39579|61124->39846|61371->40064|61412->40065|61455->40079|61581->40161|61773->40324|61814->40325|61857->40339|61978->40416|62166->40575|62207->40576|62250->40590|62339->40651|62363->40665|62400->40679|62456->40691|62647->40853|62688->40854|62731->40868|62857->40950|62936->41001|62976->41002|63019->41016|63314->41267|63406->41331|63446->41332|63489->41346|63678->41507|63703->41521|63741->41536|63797->41548|63838->41560|63908->41586|63944->41594|64261->41882|64302->41883|64336->41889|64873->42397|64914->42398|65035->42491|65075->42492|65112->42501|65329->42674|65395->42696|65474->42747|65514->42748|65557->42762|65669->42830|65749->42882|65789->42883|65832->42897|65946->42967|66028->43021|66068->43022|66111->43036|66229->43110|66312->43165|66352->43166|66388->43174|66515->43257|66596->43310|66636->43311|66672->43319|66878->43481|67072->43646|67113->43647|67156->43661|67277->43738|67358->43791|67398->43792|67441->43806|67560->43881|67653->43946|67693->43947|67729->43955|67866->44048|67960->44114|68000->44115|68036->44123|68248->44290|68446->44459|68487->44460|68530->44474|68841->44741|68918->44790|68958->44791|69001->44805|69123->44883|69204->44936|69244->44937|69287->44951|69665->45285|69752->45344|69792->45345|69835->45359|70040->45520|70081->45532|70151->45558|70192->45571|70517->45867|70558->45868|70592->45874|71020->46274|71060->46275|71103->46289|71380->46538|71404->46552|71442->46568|71575->46673|71615->46674|71657->46687|71750->46752|71774->46766|71813->46782|71858->46799|71882->46813|71920->46829|72013->46877|72107->46943|72147->46944|72189->46957|72282->47022|72306->47036|72345->47052|72390->47069|72414->47083|72452->47099|72487->47106|72511->47120|72548->47135|72590->47148|72657->47170|72747->47232|72787->47233|72829->47246|72921->47310|72945->47324|72984->47340|73029->47357|73053->47371|73091->47387|73183->47434|73277->47500|73317->47501|73359->47514|73448->47575|73472->47589|73511->47605|73556->47622|73580->47636|73618->47652|73663->47669|73687->47683|73724->47698|73766->47711|73833->47733|73918->47790|73958->47791|74000->47804|74169->47945|74193->47959|74232->47975|74284->47999|74308->48013|74346->48029|74389->48044|74413->48058|74450->48073|74492->48086|74559->48108|74599->48119|74859->48351|74899->48352|74941->48365|75099->48478|75193->48544|75233->48545|75275->48558|75388->48643|75413->48657|75451->48672|75518->48694|75608->48756|75648->48757|75690->48770|75846->48881|75938->48945|75978->48946|76020->48959|76140->49050|76165->49064|76203->49079|76270->49101|76355->49158|76395->49159|76437->49172|76642->49348|76667->49362|76705->49377|76772->49399|76812->49410|76967->49537|76991->49551|77024->49562|77163->49673|77203->49674|77245->49687|77399->49796|77493->49862|77533->49863|77575->49876|77684->49957|77709->49971|77747->49986|77814->50008|77904->50070|77944->50071|77986->50084|78138->50191|78230->50255|78270->50256|78312->50269|78429->50357|78454->50371|78492->50386|78559->50408|78644->50465|78684->50466|78726->50479|78927->50651|78952->50665|78990->50680|79057->50702|79141->50758|79181->50759|79223->50772|79452->50956|79492->50967|79699->51146|79724->51160|79762->51175|79852->51220|79987->51326|80028->51327|80071->51341|80296->51522|80377->51575|80417->51576|80460->51590|80686->51788|80726->51789|80766->51800|80883->51889|80923->51890|80965->51903|81095->51988|81183->52048|81223->52049|81265->52062|81360->52129|81384->52143|81427->52163|81489->52180|81577->52240|81617->52241|81659->52254|81754->52321|81778->52335|81821->52355|81883->52372|81971->52432|82011->52433|82053->52446|82196->52544|82288->52608|82328->52609|82370->52622|82698->52905|82738->52916|82799->52932|82836->52941|82913->52973|83048->53079|83089->53080|83132->53094|83334->53251|83423->53312|83463->53313|83506->53327|83744->53537|83784->53538|83824->53549|83961->53641|84045->53697|84085->53698|84125->53709|84268->53807|84352->53863|84392->53864|84432->53875|84773->54171|84857->54227|84897->54228|84937->54239|85151->54408|85198->54427|85276->54459|85367->54521|85408->54522|85452->54536|85687->54742|85728->54743|85769->54754|85927->54866|86014->54924|86055->54925|86096->54936|86248->55042|86335->55100|86376->55101|86417->55112|86583->55232|86680->55300|86722->55302|86763->55313|87012->55516|87099->55574|87141->55576|87182->55587|87360->55719|87447->55777|87489->55779|87530->55790|87630->55861|87655->55875|87694->55890|87726->55891|87800->55919|87895->55985|87936->55986|87977->55997|88166->56157|88192->56171|88230->56185|88422->56331|88460->56340|88538->56372|88666->56471|88708->56472|88745->56480|88970->56676|89011->56677|89052->56688|89152->56759|89177->56773|89215->56787|89522->57065|89548->57079|89586->57093|89781->57242|89819->57251|89897->57283|90029->57385|90071->57386|90115->57400|90243->57482|90327->57537|90368->57538|90412->57552|90540->57651|90565->57665|90604->57681|90740->57788|90781->57789|90822->57800|90911->57860|90936->57874|90980->57894|91144->58012|91227->58066|91268->58067|91309->58078|91856->58579|91943->58637|91984->58638|92025->58649|92180->58758|92267->58816|92308->58817|92349->58828|92510->58943|92710->59113|92752->59114|92793->59125|92957->59243|93082->59339|93123->59340|93164->59351|93338->59496|93363->59510|93403->59526|93582->59659|93622->59669|93772->59790|93813->59791|93854->59802|93949->59868|93974->59882|94014->59898|94080->59918|94168->59977|94209->59978|94250->59989|94349->60059|94374->60073|94412->60087|94487->60116|94580->60180|94621->60181|94662->60192|94819->60303|94919->60374|94960->60375|95001->60386|95233->60572|95413->60722|95455->60723|95496->60734|95683->60892|95709->60906|95749->60922|95781->60923|95917->61030|95943->61044|95983->61060|96015->61061|96159->61176|96185->61190|96225->61206|96257->61207|96332->61236|96390->61265|96468->61297|96595->61395|96636->61396|96673->61404|97568->62254|97699->62356|97740->62357|97777->62365|97986->62529|98035->62548|98106->62574|98148->62587|98472->62881|98514->62882|98549->62888|98971->63281|99012->63282|99056->63296|99184->63395|99209->63409|99248->63425|99372->63520|99413->63521|99455->63533|99596->63628|99634->63637|99712->63669|99791->63719|99832->63720|99876->63734|100103->63932|100144->63933|100186->63945|100318->64031|100401->64085|100442->64086|100483->64097|100683->64251|100721->64260|100799->64292|100878->64342|100919->64343|100963->64357|101194->64559|101235->64560|101277->64572|101404->64653|101487->64707|101528->64708|101569->64719|101697->64801|101737->64811|102044->65089|102085->65090|102128->65103|102263->65192|102348->65248|102389->65249|102432->65262|102571->65355|102656->65411|102697->65412|102740->65425|102870->65509|102911->65520|103014->65594|103055->65595|103096->65606|103238->65702|103276->65711|103354->65743|103396->65755|103468->65782|103498->65783|104634->66889|104660->66903|104699->66918|108128->70318|108169->70319|108203->70324|108786->70862|108831->70878|109147->71165|109188->71166|109224->71173|109290->71210|109308->71217|109345->71231|109381->71246|109422->71247|109458->71254|109546->71313|109564->71320|109603->71336|109639->71343|109658->71350|109696->71364|109739->71375|109779->71385|113923->75499|113954->75500|113986->75503|114054->75541|114085->75542|114120->75548|114228->75627|114258->75628|114292->75633|114489->75800|114520->75801|114556->75808|114639->75862|114669->75863|114720->75885|114750->75886|114814->75920|114845->75921|114881->75928|114918->75936|114935->75942|114969->75953|115026->75981|115055->75987|115117->76020|115134->76026|115168->76037|115225->76065|115254->76071|115316->76104|115346->76105|115425->76154|115456->76155|115488->76158|115610->76250|115641->76251|115684->76264|115945->76495|115976->76496|116014->76505|116066->76527|116097->76528|116137->76538|116204->76575|116235->76576|116276->76587|116336->76617|116367->76618|116407->76629|116438->76630|116505->76667|116536->76668|116584->76687|116615->76688|116649->76692|116680->76693|116720->76703|116871->76825|116902->76826|116939->76834|116969->76835|117007->76844|117038->76845|117074->76852|117104->76853|117137->76857|117214->76904|117245->76905|117277->76908|117328->76929|117359->76930|117392->76934|117511->77023|117542->77024|117586->77038|117849->77271|117880->77272|117920->77282|117972->77304|118003->77305|118044->77316|118111->77353|118142->77354|118184->77366|118245->77397|118276->77398|118318->77410|118349->77411|118389->77421|118439->77441|118470->77442|118511->77453|118629->77541|118661->77542|118703->77554|118780->77601|118811->77602|118853->77614|118884->77615|118922->77624|118953->77625|118993->77635|119024->77636|119058->77641|119088->77642|119119->77644|119149->77645|119182->77649|119254->77691|119285->77692|119319->77697|119554->77903|119584->77904|119617->77908|119729->77990|119760->77991|119793->77995|119838->78011|119868->78012|120138->78252|120169->78253|120209->78263|120363->78388|120393->78389|120430->78397|120482->78419|120513->78420|120553->78430|120691->78539|120721->78540|120755->78545
                  LINES: 28->1|33->2|34->3|38->7|38->7|39->8|40->9|40->9|41->10|41->10|41->10|42->11|46->15|46->15|47->16|47->16|47->16|48->17|52->21|52->21|53->22|53->22|53->22|54->23|59->28|59->28|60->29|72->41|72->41|73->42|76->45|77->46|78->47|78->47|78->47|93->62|93->62|94->63|99->68|99->68|100->69|103->72|103->72|104->73|105->74|106->75|106->75|107->76|108->77|110->79|110->79|111->80|112->81|114->83|114->83|115->84|118->87|120->89|120->89|121->90|123->92|124->93|126->95|127->96|127->96|128->97|129->98|129->98|129->98|131->100|131->100|132->101|132->101|132->101|132->101|132->101|133->102|133->102|133->102|133->102|134->103|134->103|134->103|134->103|136->105|137->106|137->106|138->107|139->108|139->108|139->108|139->108|140->109|140->109|140->109|140->109|141->110|142->111|142->111|143->112|144->113|144->113|144->113|144->113|145->114|146->115|148->117|149->118|150->119|150->119|151->120|152->121|153->122|153->122|154->123|155->124|156->125|158->127|161->130|164->133|164->133|165->134|170->139|170->139|171->140|174->143|174->143|175->144|175->144|176->145|177->146|177->146|178->147|179->148|180->149|181->150|181->150|182->151|182->151|183->152|184->153|184->153|185->154|186->155|187->156|188->157|188->157|189->158|190->159|191->160|191->160|192->161|201->170|204->173|204->173|205->174|207->176|208->177|210->179|211->180|211->180|212->181|213->182|213->182|213->182|215->184|215->184|216->185|216->185|216->185|216->185|217->186|219->188|219->188|220->189|220->189|220->189|220->189|221->190|223->192|223->192|224->193|224->193|224->193|224->193|225->194|227->196|227->196|228->197|229->198|229->198|229->198|229->198|229->198|229->198|229->198|230->199|232->201|232->201|233->202|234->203|234->203|234->203|235->204|235->204|235->204|237->206|237->206|237->206|238->207|238->207|238->207|238->207|238->207|238->207|238->207|239->208|241->210|241->210|242->211|243->212|243->212|243->212|244->213|245->214|247->216|248->217|248->217|249->218|252->221|252->221|253->222|254->223|255->224|255->224|256->225|257->226|258->227|258->227|259->228|266->235|267->236|269->238|270->239|271->240|271->240|272->241|273->242|274->243|274->243|275->244|276->245|277->246|279->248|281->250|284->253|284->253|285->254|291->260|291->260|292->261|293->262|295->264|295->264|296->265|297->266|298->267|298->267|299->268|300->269|301->270|301->270|302->271|303->272|304->273|304->273|305->274|314->283|315->284|315->284|316->285|318->287|319->288|321->290|323->292|326->295|326->295|327->296|332->301|332->301|333->302|334->303|335->304|335->304|336->305|344->313|345->314|345->314|346->315|348->317|349->318|349->318|350->319|354->323|355->324|355->324|356->325|357->326|357->326|357->326|358->327|359->328|361->330|363->332|366->335|366->335|367->336|367->336|368->337|373->342|373->342|374->343|375->344|376->345|376->345|377->346|378->347|379->348|379->348|380->349|381->350|382->351|382->351|383->352|391->360|392->361|394->363|397->366|400->369|400->369|401->370|406->375|406->375|407->376|407->376|407->376|407->376|410->379|411->380|411->380|412->381|413->382|414->383|414->383|415->384|416->385|417->386|417->386|418->387|426->395|427->396|429->398|430->399|432->401|440->409|440->409|441->410|441->410|442->411|447->416|447->416|448->417|450->419|451->420|451->420|452->421|453->422|454->423|454->423|455->424|456->425|457->426|457->426|458->427|459->428|460->429|460->429|461->430|463->432|464->433|464->433|465->434|466->435|467->436|467->436|468->437|478->447|479->448|481->450|481->450|482->451|490->459|490->459|491->460|493->462|494->463|494->463|495->464|496->465|497->466|497->466|498->467|499->468|500->469|500->469|501->470|502->471|503->472|503->472|504->473|506->475|507->476|507->476|508->477|509->478|510->479|510->479|511->480|521->490|522->491|547->516|547->516|548->517|551->520|551->520|552->521|553->522|554->523|554->523|555->524|556->525|557->526|557->526|558->527|559->528|560->529|560->529|561->530|562->531|563->532|563->532|564->533|565->534|566->535|566->535|567->536|568->537|569->538|569->538|570->539|571->540|572->541|572->541|573->542|575->544|576->545|578->547|579->548|583->552|585->554|587->556|590->559|590->559|591->560|596->565|596->565|597->566|598->567|599->568|599->568|600->569|602->571|603->572|603->572|604->573|604->573|605->574|608->577|611->580|612->581|612->581|612->581|613->582|613->582|613->582|614->583|615->584|617->586|619->588|622->591|622->591|623->592|628->597|628->597|629->598|634->603|638->607|638->607|639->608|642->611|643->612|645->614|647->616|650->619|650->619|651->620|656->625|656->625|657->626|658->627|659->628|659->628|660->629|662->631|663->632|665->634|667->636|670->639|670->639|671->640|676->645|676->645|677->646|678->647|679->648|679->648|680->649|681->650|682->651|682->651|683->652|684->653|684->653|684->653|684->653|689->658|690->659|690->659|691->660|693->662|694->663|694->663|695->664|698->667|701->670|701->670|702->671|706->675|706->675|707->676|708->677|709->678|710->679|710->679|711->680|712->681|713->682|716->685|718->687|718->687|719->688|721->690|721->690|721->690|723->692|723->692|724->693|724->693|724->693|724->693|725->694|728->697|728->697|729->698|731->700|732->701|732->701|733->702|734->703|736->705|736->705|737->706|738->707|738->707|738->707|739->708|740->709|741->710|741->710|742->711|742->711|742->711|742->711|743->712|744->713|744->713|745->714|745->714|745->714|745->714|746->715|747->716|747->716|748->717|749->718|752->721|760->729|761->730|763->732|765->734|768->737|768->737|769->738|771->740|771->740|771->740|772->741|774->743|774->743|775->744|775->744|775->744|775->744|779->748|780->749|780->749|781->750|782->751|783->752|783->752|784->753|785->754|786->755|786->755|787->756|787->756|787->756|787->756|788->757|789->758|789->758|790->759|791->760|792->761|792->761|793->762|796->765|797->766|797->766|798->767|799->768|799->768|799->768|800->769|801->770|803->772|805->774|808->777|808->777|809->778|814->783|814->783|815->784|815->784|816->785|818->787|820->789|821->790|821->790|822->791|823->792|824->793|824->793|825->794|826->795|827->796|827->796|828->797|829->798|830->799|830->799|831->800|832->801|833->802|833->802|834->803|836->805|837->806|837->806|838->807|839->808|840->809|840->809|841->810|842->811|845->814|845->814|846->815|847->816|849->818|849->818|850->819|852->821|854->823|854->823|855->824|858->827|859->828|859->828|860->829|861->830|862->831|862->831|863->832|867->836|869->838|869->838|870->839|872->841|873->842|875->844|879->848|882->851|882->851|883->852|888->857|888->857|889->858|893->862|893->862|893->862|895->864|895->864|896->865|896->865|896->865|896->865|897->866|897->866|897->866|899->868|900->869|900->869|901->870|901->870|901->870|901->870|902->871|902->871|902->871|902->871|902->871|902->871|903->872|904->873|905->874|905->874|906->875|906->875|906->875|906->875|907->876|907->876|907->876|909->878|910->879|910->879|911->880|911->880|911->880|911->880|912->881|912->881|912->881|912->881|912->881|912->881|913->882|914->883|915->884|915->884|916->885|917->886|917->886|917->886|918->887|918->887|918->887|918->887|918->887|918->887|919->888|920->889|921->890|926->895|926->895|927->896|928->897|929->898|929->898|930->899|930->899|930->899|930->899|931->900|932->901|932->901|933->902|934->903|935->904|935->904|936->905|936->905|936->905|936->905|937->906|938->907|938->907|939->908|940->909|940->909|940->909|941->910|942->911|945->914|945->914|945->914|947->916|947->916|948->917|949->918|950->919|950->919|951->920|951->920|951->920|951->920|952->921|953->922|953->922|954->923|955->924|956->925|956->925|957->926|957->926|957->926|957->926|958->927|959->928|959->928|960->929|961->930|961->930|961->930|962->931|963->932|963->932|964->933|966->935|967->936|970->939|970->939|970->939|973->942|974->943|974->943|975->944|977->946|978->947|978->947|979->948|982->951|982->951|983->952|984->953|984->953|985->954|986->955|987->956|987->956|988->957|988->957|988->957|988->957|989->958|990->959|990->959|991->960|991->960|991->960|991->960|992->961|993->962|993->962|994->963|995->964|996->965|996->965|997->966|1000->969|1001->970|1002->971|1003->972|1005->974|1006->975|1006->975|1007->976|1008->977|1009->978|1009->978|1010->979|1013->982|1013->982|1014->983|1015->984|1016->985|1016->985|1017->986|1018->987|1019->988|1019->988|1020->989|1023->992|1024->993|1024->993|1025->994|1027->996|1029->998|1031->1000|1032->1001|1032->1001|1033->1002|1036->1005|1036->1005|1037->1006|1038->1007|1039->1008|1039->1008|1040->1009|1041->1010|1042->1011|1042->1011|1043->1012|1044->1013|1046->1015|1046->1015|1047->1016|1050->1019|1051->1020|1051->1020|1052->1021|1054->1023|1055->1024|1055->1024|1056->1025|1056->1025|1056->1025|1056->1025|1056->1025|1057->1026|1059->1028|1059->1028|1060->1029|1061->1030|1061->1030|1061->1030|1063->1032|1064->1033|1066->1035|1067->1036|1067->1036|1068->1037|1071->1040|1071->1040|1072->1041|1072->1041|1072->1041|1072->1041|1075->1044|1075->1044|1075->1044|1078->1047|1079->1048|1081->1050|1082->1051|1082->1051|1083->1052|1084->1053|1086->1055|1086->1055|1087->1056|1088->1057|1088->1057|1088->1057|1090->1059|1090->1059|1091->1060|1091->1060|1091->1060|1091->1060|1093->1062|1094->1063|1094->1063|1095->1064|1100->1069|1101->1070|1101->1070|1102->1071|1103->1072|1104->1073|1104->1073|1105->1074|1106->1075|1107->1076|1107->1076|1108->1077|1109->1078|1110->1079|1110->1079|1111->1080|1112->1081|1112->1081|1112->1081|1115->1084|1116->1085|1117->1086|1117->1086|1118->1087|1118->1087|1118->1087|1118->1087|1119->1088|1120->1089|1120->1089|1121->1090|1121->1090|1121->1090|1121->1090|1122->1091|1123->1092|1123->1092|1124->1093|1125->1094|1127->1096|1127->1096|1128->1097|1130->1099|1132->1101|1132->1101|1133->1102|1134->1103|1134->1103|1134->1103|1134->1103|1135->1104|1135->1104|1135->1104|1135->1104|1136->1105|1136->1105|1136->1105|1136->1105|1137->1106|1140->1109|1142->1111|1144->1113|1144->1113|1145->1114|1157->1126|1159->1128|1159->1128|1160->1129|1162->1131|1164->1133|1166->1135|1170->1139|1173->1142|1173->1142|1174->1143|1179->1148|1179->1148|1180->1149|1181->1150|1181->1150|1181->1150|1183->1152|1183->1152|1184->1153|1185->1154|1186->1155|1188->1157|1189->1158|1189->1158|1190->1159|1193->1162|1193->1162|1194->1163|1195->1164|1196->1165|1196->1165|1197->1166|1199->1168|1200->1169|1202->1171|1203->1172|1203->1172|1204->1173|1207->1176|1207->1176|1208->1177|1209->1178|1210->1179|1210->1179|1211->1180|1212->1181|1213->1182|1217->1186|1217->1186|1218->1187|1219->1188|1220->1189|1220->1189|1221->1190|1222->1191|1223->1192|1223->1192|1224->1193|1225->1194|1226->1195|1228->1197|1228->1197|1229->1198|1230->1199|1231->1200|1233->1202|1234->1203|1236->1205|1236->1205|1253->1222|1253->1222|1253->1222|1297->1266|1297->1266|1298->1267|1307->1276|1311->1280|1320->1289|1320->1289|1321->1290|1321->1290|1321->1290|1321->1290|1322->1291|1322->1291|1323->1292|1323->1292|1323->1292|1323->1292|1323->1292|1323->1292|1323->1292|1324->1293|1325->1294|1448->1417|1448->1417|1449->1418|1449->1418|1449->1418|1450->1419|1451->1420|1451->1420|1452->1421|1454->1423|1454->1423|1455->1424|1456->1425|1456->1425|1458->1427|1458->1427|1460->1429|1460->1429|1461->1430|1461->1430|1461->1430|1461->1430|1461->1430|1461->1430|1462->1431|1462->1431|1462->1431|1462->1431|1462->1431|1463->1432|1463->1432|1467->1436|1467->1436|1468->1437|1470->1439|1470->1439|1471->1440|1478->1447|1478->1447|1479->1448|1479->1448|1479->1448|1480->1449|1480->1449|1480->1449|1481->1450|1482->1451|1482->1451|1483->1452|1483->1452|1483->1452|1483->1452|1485->1454|1485->1454|1485->1454|1485->1454|1486->1455|1488->1457|1488->1457|1489->1458|1489->1458|1490->1459|1490->1459|1491->1460|1491->1460|1493->1462|1493->1462|1493->1462|1494->1463|1494->1463|1494->1463|1495->1464|1497->1466|1497->1466|1498->1467|1505->1474|1505->1474|1506->1475|1506->1475|1506->1475|1507->1476|1507->1476|1507->1476|1508->1477|1509->1478|1509->1478|1510->1479|1510->1479|1511->1480|1511->1480|1511->1480|1512->1481|1512->1481|1512->1481|1513->1482|1514->1483|1514->1483|1515->1484|1515->1484|1516->1485|1516->1485|1517->1486|1517->1486|1518->1487|1518->1487|1519->1488|1519->1488|1521->1490|1521->1490|1521->1490|1522->1491|1524->1493|1524->1493|1526->1495|1527->1496|1527->1496|1528->1497|1529->1498|1529->1498|1539->1508|1539->1508|1540->1509|1542->1511|1542->1511|1544->1513|1544->1513|1544->1513|1545->1514|1548->1517|1548->1517|1552->1521
                  -- GENERATED --
              */
          