
package viewsMnuReportes.html

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

object reportFacturaResumen0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,utilities.Fechas,utilities.Fechas,Double,Double,Double,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
desde: utilities.Fechas, hasta: utilities.Fechas, uf: Double, usd: Double, eur: Double):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
	"""),format.raw/*4.2*/("""<!-- ANCLA CABECERAS DE TABLAS -->
	<style type="text/css"> 
        thead tr th """),format.raw/*6.21*/("""{"""),format.raw/*6.22*/(""" 
            """),format.raw/*7.13*/("""position: sticky;
			background-color: #eeeeee;
            top: 0;
        """),format.raw/*10.9*/("""}"""),format.raw/*10.10*/("""
    """),format.raw/*11.5*/("""</style>



	"""),_display_(/*15.3*/main("")/*15.11*/ {_display_(Seq[Any](format.raw/*15.13*/("""

		"""),_display_(/*17.4*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*17.52*/("""
		
		"""),format.raw/*19.3*/("""<div id="enCarga" class="blocker" style="display: block;"><br><br><br><br><br><br><h1>Ahora se esta cargando.....</h1></div>
		<div id="mostrarmostrar">
			"""),_display_(/*21.5*/barraTitulo(mapDiccionario, "REPORTE RESUMEN Y DETALLE POR TODOS LOS PROYECTOS AGRUPADO (INCLUIDOS AJUSTES DE EP)",
				"PERIODO: desde " + desde.getFechaStr() +" --- hasta: " +  hasta.getFechaStr())),format.raw/*22.84*/("""
				
			"""),format.raw/*24.4*/("""<div class="noprint">
				<table class="table table-sm table-condensed table-fluid">
					<tr>
						<td>
							<div align="center">
								<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
									Exportar a Excel
								</button>
								<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
									Imprimir
								</button>
								<button type="button"  class="btn btn-sm btn-success" 
									onclick="history.go(-1);return false;">
									Volver
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
			
			<form id="excel" class="formulario" method="post" action="/reportFacturaResumenExcel0/">
				<input type="hidden" name="fechaDesde" value=""""),_display_(/*46.52*/desde/*46.57*/.getFechaSql().toString()),format.raw/*46.82*/("""">
				<input type="hidden" name="fechaHasta" value=""""),_display_(/*47.52*/hasta/*47.57*/.getFechaSql().toString()),format.raw/*47.82*/("""">
				<input type="hidden" name="uf" value=""""),_display_(/*48.44*/uf),format.raw/*48.46*/("""">
				<input type="hidden" name="usd" value=""""),_display_(/*49.45*/usd),format.raw/*49.48*/("""">
				<input type="hidden" name="eur" value=""""),_display_(/*50.45*/eur),format.raw/*50.48*/("""">
			</form>
			
			
			<table id="general" class="table table-sm table-bordered table-condensed table-fluid">
				<tr>
					<td>
						<div align="left"><h5><b><font color="#008000"> RESUMEN POR PROYECTOS (INCLUYE AJUSTES A EP): </font></b></h5></div>
						
						<div id="resumenPorPJson"></div>
					</td>
				</tr>
				
				
					<tr>
						<td><br><br><br>
							<div align="left"><h5><b><font color="#008000"> RESUMEN POR FAMILIAS Y PROYECTOS (SIN AJUSTES A EP): </font></b></h5></div>
							<div id="resumenPorPyGJson"></div>
						</td>
					</tr>
					<!--
					<tr>
						<td><br><br><br>
							<div align="left"><h5><b><font color="#008000"> DETALLE POR PROYECTO (INCLUYE AJUSTES A EP): </font></b></h5></div>
							<div id="resumenPorPyGyDJson"></div>
						</td>
					</tr>
				-->
				
			
			</table>
			
		</div>
		
	""")))}),format.raw/*84.3*/("""
		
		
		
		"""),format.raw/*88.3*/("""<script type="text/javascript">
		
		window.addEventListener('load', function() """),format.raw/*90.46*/("""{"""),format.raw/*90.47*/("""
		"""),format.raw/*91.3*/("""document.getElementById('mostrarmostrar').style.display="block";
		
		
		
		let formData = new FormData();
		formData.append("fechaDesde",""""),_display_(/*96.34*/desde/*96.39*/.getFechaSql().toString()),format.raw/*96.64*/("""");
		formData.append("fechaHasta",""""),_display_(/*97.34*/hasta/*97.39*/.getFechaSql().toString()),format.raw/*97.64*/("""");
		formData.append("uf",""""),_display_(/*98.26*/uf),format.raw/*98.28*/("""");
		formData.append("usd",""""),_display_(/*99.27*/usd),format.raw/*99.30*/("""");
		formData.append("eur",""""),_display_(/*100.27*/eur),format.raw/*100.30*/("""");
		$.ajax("""),format.raw/*101.10*/("""{"""),format.raw/*101.11*/("""
		    """),format.raw/*102.7*/("""url: "/reportFacturaResumenJson/",
		    type: "POST",
		    method: "POST",
		    data: formData,
		    cache: false,
		    contentType: false,
		 	processData: false,
		 	success: function(rs)"""),format.raw/*109.26*/("""{"""),format.raw/*109.27*/("""
		 		"""),format.raw/*110.6*/("""if(rs=="error")"""),format.raw/*110.21*/("""{"""),format.raw/*110.22*/("""
		 			"""),format.raw/*111.7*/("""alertify.alert('Se presento un problema o usted no tiene permisos otorgados, por favor vuelva a ingresar y si persiste el problema, contactar a soporte', function()"""),format.raw/*111.171*/("""{"""),format.raw/*111.172*/(""" 
						"""),format.raw/*112.7*/("""location.href = "/"; 
					"""),format.raw/*113.6*/("""}"""),format.raw/*113.7*/(""");
		 		"""),format.raw/*114.6*/("""}"""),format.raw/*114.7*/("""else"""),format.raw/*114.11*/("""{"""),format.raw/*114.12*/("""
	
					"""),format.raw/*116.6*/("""let jsonPorPJson =rs["resumenPorPJson"];
					let jsonTotales =rs["resumenTotalesJson"];
					let jsonPorPyGJson = rs["resumenPorPyGJson"];
				//	let jsonPorPyGyDJson = rs["resumenPorPyGyDJson"];
		
				//	vistaPrincipal(jsonPorPJson, jsonTotales, jsonPorPyGJson, jsonPorPyGyDJson);
				
				vistaPrincipal(jsonPorPJson, jsonTotales, jsonPorPyGJson);
				
				"""),format.raw/*125.5*/("""}"""),format.raw/*125.6*/("""
			"""),format.raw/*126.4*/("""}"""),format.raw/*126.5*/("""
		"""),format.raw/*127.3*/("""}"""),format.raw/*127.4*/(""");
		
		
	"""),format.raw/*130.2*/("""}"""),format.raw/*130.3*/(""");
	
	const vistaPrincipal =(jsonPorPJson, jsonTotales, jsonPorPyGJson) =>"""),format.raw/*132.70*/("""{"""),format.raw/*132.71*/("""
		

				"""),format.raw/*135.5*/("""let tablaProyectos = ""
					+ "<table id=\"tablaProyectos\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
						+ "<thead style=\"background-color: #eeeeee\">"
							+ "<TR> "
								+ "<TH style= \"text-align:center; vertical-align:top;\">SUCURSAL</TH>"
								+ "<TH style= \"text-align:center; vertical-align:top;\">NOMBRE<br>"+'"""),_display_(/*140.80*/mapDiccionario/*140.94*/.get("BODEGA")),format.raw/*140.108*/("""'+"/PROYECTO</TH>";
										if('"""),_display_(/*141.16*/mapDiccionario/*141.30*/.get("nEmpresa")),format.raw/*141.46*/("""' == "CIHLA")"""),format.raw/*141.59*/("""{"""),format.raw/*141.60*/("""
											"""),format.raw/*142.12*/("""tablaProyectos += "<TH style= \"text-align:center;vertical-align:top;\">Nro CARGO</TH>";
										"""),format.raw/*143.11*/("""}"""),format.raw/*143.12*/("""else"""),format.raw/*143.16*/("""{"""),format.raw/*143.17*/("""
											"""),format.raw/*144.12*/("""tablaProyectos += "<TH style= \"text-align:center;vertical-align:top;\">"+'"""),_display_(/*144.88*/mapDiccionario/*144.102*/.get("RUT")),format.raw/*144.113*/("""'+"<BR>CLIENTE</TH>"
												+ "<TH style= \"text-align:center;vertical-align:top;\">NOMBRE<BR>CLIENTE</TH>";
										"""),format.raw/*146.11*/("""}"""),format.raw/*146.12*/("""
									"""),format.raw/*147.10*/("""tablaProyectos += "<TH style= \"text-align:center; vertical-align:top;\">NOMBRE<br>PROYECTO</TH>"
											+ "<TH style= \"text-align:center; vertical-align:top;\">CFI</TH>"
											+ "<TH style= \"text-align:center; vertical-align:top;\">SubTotal<br>"+'"""),_display_(/*149.85*/mapDiccionario/*149.99*/.get("ARRIENDO")),format.raw/*149.115*/("""'+"</TH>"
											+ "<TH style= \"text-align:center; vertical-align:top;\">SubTotal<br>VENTA</TH>"
											+ "<TH style= \"text-align:center; vertical-align:top;\">Ajustes<BR>("+'"""),_display_(/*151.85*/mapDiccionario/*151.99*/.get("ARRIENDO")),format.raw/*151.115*/("""'+")</TH>"
											+ "<TH style= \"text-align:center; vertical-align:top;\">Ajustes<BR>(VENTA)</TH>"
											+ "<TH style= \"text-align:center; vertical-align:top;\">TOTAL<BR>(en "+'"""),_display_(/*153.86*/mapDiccionario/*153.100*/.get("PESOS")),format.raw/*153.113*/("""'+")</TH>"
								+ "</TR>"
									+ "</thead>"
										+ "<tbody>";
											for(var i in jsonPorPJson)"""),format.raw/*157.38*/("""{"""),format.raw/*157.39*/("""
												"""),format.raw/*158.13*/("""tablaProyectos += "<TR>";
													for(var t in jsonTotales)"""),format.raw/*159.39*/("""{"""),format.raw/*159.40*/("""
														"""),format.raw/*160.15*/("""if(jsonPorPJson[i][1] == jsonTotales[t][0])"""),format.raw/*160.58*/("""{"""),format.raw/*160.59*/("""
															"""),format.raw/*161.16*/("""tablaProyectos += "<td>"+jsonPorPJson[i][14]+"</td>"
																	+ "<td>"+jsonPorPJson[i][5]+"</td>";
																if('"""),_display_(/*163.22*/mapDiccionario/*163.36*/.get("nEmpresa")),format.raw/*163.52*/("""' == 'CIHLA')"""),format.raw/*163.65*/("""{"""),format.raw/*163.66*/("""
																	"""),format.raw/*164.18*/("""tablaProyectos += "<td></td>";
																"""),format.raw/*165.17*/("""}"""),format.raw/*165.18*/("""else"""),format.raw/*165.22*/("""{"""),format.raw/*165.23*/("""
																	"""),format.raw/*166.18*/("""tablaProyectos += "<td>"+jsonPorPJson[i][6]+"</td>"
																			+ "<td>"+jsonPorPJson[i][7]+"</td>";
																"""),format.raw/*168.17*/("""}"""),format.raw/*168.18*/("""
															"""),format.raw/*169.16*/("""tablaProyectos += "<td>"+jsonPorPJson[i][8]+"</td>"
											    					+ "<td style= \"text-align:right;\">"+jsonTotales[t][3]+"</td>"
											    					+ "<td style= \"text-align:right;\">"+jsonTotales[t][1]+"</td>"
											    					+ "<td style= \"text-align:right;\">"+jsonTotales[t][2]+"</td>"
											    					+ "<td style= \"text-align:right;\">"+jsonTotales[t][5]+"</td>"
											    					+ "<td style= \"text-align:right;\">"+jsonTotales[t][6]+"</td>"
											    					+ "<td style= \"text-align:right;\">"+jsonTotales[t][4]+"</td>";
														"""),format.raw/*176.15*/("""}"""),format.raw/*176.16*/("""
													"""),format.raw/*177.14*/("""}"""),format.raw/*177.15*/("""
													"""),format.raw/*178.14*/("""tablaProyectos += "</TR>";
											"""),format.raw/*179.12*/("""}"""),format.raw/*179.13*/("""
											"""),format.raw/*180.12*/("""tablaProyectos += "<TR>"
													+ "<td style=\"background-color: #eeeeee\">TOTALES</td>"
													+ "<td style=\"background-color: #eeeeee\"></td>";
												if(! '"""),_display_(/*183.20*/mapDiccionario/*183.34*/.get("nEmpresa")),format.raw/*183.50*/("""' == 'CIHLA')"""),format.raw/*183.63*/("""{"""),format.raw/*183.64*/("""
													"""),format.raw/*184.14*/("""tablaProyectos += "<td style=\"background-color: #eeeeee\"></td>";
												"""),format.raw/*185.13*/("""}"""),format.raw/*185.14*/("""
													"""),format.raw/*186.14*/("""tablaProyectos += "<td style=\"background-color: #eeeeee\"></td>"
															+ "<td style=\"background-color: #eeeeee\"></td>"
															+ "<td style=\"background-color: #eeeeee\"></td>";
												for(var t in jsonTotales)"""),format.raw/*189.38*/("""{"""),format.raw/*189.39*/("""
													"""),format.raw/*190.14*/("""if(jsonTotales[t][0] == "SUBTOTAL")"""),format.raw/*190.49*/("""{"""),format.raw/*190.50*/("""
														"""),format.raw/*191.15*/("""tablaProyectos += "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonTotales[t][3]+"</td>"
									    					+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonTotales[t][1]+"</td>"
									    					+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonTotales[t][2]+"</td>"
									    					+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonTotales[t][5]+"</td>"
									    					+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonTotales[t][6]+"</td>"
									    					+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonTotales[t][4]+"</td>";
													"""),format.raw/*197.14*/("""}"""),format.raw/*197.15*/("""
												"""),format.raw/*198.13*/("""}"""),format.raw/*198.14*/("""
												"""),format.raw/*199.13*/("""tablaProyectos += "</TR>"
														+ "</tbody>"
														+ "</table>";
				document.getElementById('resumenPorPJson').innerHTML = tablaProyectos;
				
				
				let tablaFamilias = ""
	    					+ "<table id=\"tablaFamilias\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
	    						+ "<thead style=\"background-color: #eeeeee\">"
	    							+ "<TR>"
	    								+ "<TH style= \"text-align:center;vertical-align:top;\">FAMILIA/GRUPO</TH>"
	    								+ "<TH style= \"text-align:center;vertical-align:top;\">"+'"""),_display_(/*210.74*/mapDiccionario/*210.88*/.get("BODEGA")),format.raw/*210.102*/("""'+"/PROYECTO</TH>";
	    								if(!'"""),_display_(/*211.20*/mapDiccionario/*211.34*/.get("nEmpresa")),format.raw/*211.50*/("""' == "CIHLA")"""),format.raw/*211.63*/("""{"""),format.raw/*211.64*/("""
	    									"""),format.raw/*212.15*/("""tablaFamilias += "<TH style= \"text-align:center;vertical-align:top;\">"+'"""),_display_(/*212.90*/mapDiccionario/*212.104*/.get("RUT")),format.raw/*212.115*/("""'+" CLIENTE</TH>"
	    											+ "<TH style= \"text-align:center;vertical-align:top;\">NOMBRE CLIENTE</TH>";
	    								"""),format.raw/*214.14*/("""}"""),format.raw/*214.15*/("""
	    								"""),format.raw/*215.14*/("""tablaFamilias += "<TH style= \"text-align:center;vertical-align:top;\">NOMBRE<br>PROYECTO</TH>"
	    										+ "<TH style= \"text-align:center;vertical-align:top;\">CFI (en "+'"""),_display_(/*216.84*/mapDiccionario/*216.98*/.get("PESOS")),format.raw/*216.111*/("""'+")</TH>"
	    										+ "<TH style= \"text-align:center;vertical-align:top;\">"+'"""),_display_(/*217.76*/mapDiccionario/*217.90*/.get("ARRIENDO")),format.raw/*217.106*/("""'+" (en "+'"""),_display_(/*217.118*/mapDiccionario/*217.132*/.get("PESOS")),format.raw/*217.145*/("""'+")</TH>"
	    										+ "<TH style= \"text-align:center;vertical-align:top;\">VENTA (en "+'"""),_display_(/*218.86*/mapDiccionario/*218.100*/.get("PESOS")),format.raw/*218.113*/("""'+")</TH>"
	    										+ "<TH style= \"text-align:center;vertical-align:top;\">TOTAL<BR>(en "+'"""),_display_(/*219.89*/mapDiccionario/*219.103*/.get("PESOS")),format.raw/*219.116*/("""'+")</TH>"
	    							+ "</TR>"
	    						+ "</thead>"
	    						+ "<tbody>";
	    							for(var i in jsonPorPyGJson)"""),format.raw/*223.41*/("""{"""),format.raw/*223.42*/("""
	    								"""),format.raw/*224.14*/("""tablaFamilias += "<TR>";
	    								if(jsonPorPyGJson[i][3] == "SUBTOTAL" || jsonPorPyGJson[i][0] == "TOTAL")"""),format.raw/*225.87*/("""{"""),format.raw/*225.88*/("""
	    									"""),format.raw/*226.15*/("""tablaFamilias += "<td style=\"background-color: #eeeeee;text-align:left;\">"+jsonPorPyGJson[i][0]+"</td>"
	    											+ "<td style=\"background-color: #eeeeee;text-align:left;\">"+jsonPorPyGJson[i][1]+"</td>";
	    											if(!'"""),_display_(/*228.23*/mapDiccionario/*228.37*/.get("nEmpresa")),format.raw/*228.53*/("""' == "CIHLA")"""),format.raw/*228.66*/("""{"""),format.raw/*228.67*/("""
	    												"""),format.raw/*229.18*/("""tablaFamilias += "<td style=\"background-color: #eeeeee;text-align:left;\">"+jsonPorPyGJson[i][2]+"</td>"
	    														+ "<td style=\"background-color: #eeeeee;text-align:left;\">"+jsonPorPyGJson[i][3]+"</td>";
	    											"""),format.raw/*231.17*/("""}"""),format.raw/*231.18*/("""
	    										"""),format.raw/*232.16*/("""tablaFamilias += "<td style=\"background-color: #eeeeee;text-align:left;\">"+jsonPorPyGJson[i][8]+"</td>"
	    												+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonPorPyGJson[i][4]+"</td>"
	    												+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonPorPyGJson[i][5]+"</td>"
	    												+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonPorPyGJson[i][6]+"</td>"
	    												+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonPorPyGJson[i][7]+"</td>";
	    								"""),format.raw/*237.14*/("""}"""),format.raw/*237.15*/("""else"""),format.raw/*237.19*/("""{"""),format.raw/*237.20*/("""
	    									"""),format.raw/*238.15*/("""tablaFamilias += "<td style=\"text-align:left;\">"+jsonPorPyGJson[i][0]+"</td>"
	    											+ "<td style=\"text-align:left;\">"+jsonPorPyGJson[i][1]+"</td>";
	    											if(!'"""),_display_(/*240.23*/mapDiccionario/*240.37*/.get("nEmpresa")),format.raw/*240.53*/("""' == "CIHLA")"""),format.raw/*240.66*/("""{"""),format.raw/*240.67*/("""
	    												"""),format.raw/*241.18*/("""tablaFamilias += "<td style=\"text-align:left;\">"+jsonPorPyGJson[i][2]+"</td>"
	    														+ "<td style=\"text-align:left;\">"+jsonPorPyGJson[i][3]+"</td>";
	    											"""),format.raw/*243.17*/("""}"""),format.raw/*243.18*/("""
	    											"""),format.raw/*244.17*/("""tablaFamilias += "<td style=\"text-align:left;\">"+jsonPorPyGJson[i][8]+"</td>"
	    													+ "<td style=\"text-align:right;\">"+jsonPorPyGJson[i][4]+"</td>"
	    													+ "<td style=\"text-align:right;\">"+jsonPorPyGJson[i][5]+"</td>"
	    													+ "<td style=\"text-align:right;\">"+jsonPorPyGJson[i][6]+"</td>"
	    													+ "<td style=\"text-align:right;\">"+jsonPorPyGJson[i][7]+"</td>";
	    								"""),format.raw/*249.14*/("""}"""),format.raw/*249.15*/("""
	    								"""),format.raw/*250.14*/("""tablaFamilias += "</TR>";
	    							"""),format.raw/*251.13*/("""}"""),format.raw/*251.14*/("""
	    							"""),format.raw/*252.13*/("""tablaFamilias += "</tbody>"
	    									+ "</table>";
						
						
				document.getElementById('resumenPorPyGJson').innerHTML = tablaFamilias;
				
				/*
				let tablaDetalleProyectos = "";
					for(var i in jsonPorPyGyDJson)"""),format.raw/*260.36*/("""{"""),format.raw/*260.37*/("""
						"""),format.raw/*261.7*/("""if(jsonPorPyGyDJson[i][1] == "PROYECTO")"""),format.raw/*261.47*/("""{"""),format.raw/*261.48*/("""
							"""),format.raw/*262.8*/("""tablaDetalleProyectos += "<br>"
									+ "<table id=\"tablaDetalleProyectos\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
										+ "<tbody>"
											+ "<TR>"
												+ "<td colspan=\"20\" style=\"background-color:green;text-align:left\">"
													+ "<h5><b><font color=\"white\"> "+jsonPorPyGyDJson[i][0]+" </font></b></h5>"
												+ "</td>"
											+ "</TR>";
						"""),format.raw/*270.7*/("""}"""),format.raw/*270.8*/("""else"""),format.raw/*270.12*/("""{"""),format.raw/*270.13*/("""
							"""),format.raw/*271.8*/("""if(jsonPorPyGyDJson[i][1] == "GRUPO")"""),format.raw/*271.45*/("""{"""),format.raw/*271.46*/("""
								"""),format.raw/*272.9*/("""tablaDetalleProyectos += ""
										+ "<tr>"
											+ "<td colspan=\"20\" style=\"text-align:left;\">"
												+ "<h6><b><font color=\"blue\"> "+jsonPorPyGyDJson[i][0]+" </font></b></h6>"
											+ "</td>"
										+ "</tr>";
							"""),format.raw/*278.8*/("""}"""),format.raw/*278.9*/("""else"""),format.raw/*278.13*/("""{"""),format.raw/*278.14*/("""
								"""),format.raw/*279.9*/("""if(jsonPorPyGyDJson[i][1] == "INVENTARIO")"""),format.raw/*279.51*/("""{"""),format.raw/*279.52*/("""
									"""),format.raw/*280.10*/("""tablaDetalleProyectos += ""
											+ "<tr>"
												+ "<td colspan=\"20\" style=\"background-color: #eeeeee;text-align:left;\">"+jsonPorPyGyDJson[i][0]+"</td>"
											+ "</tr>"
											+ "<tr>"
												+ "<td style= \"text-align: center\"></td>"
												+ "<td style= \"text-align: center\"></td>"
												+ "<td style= \"text-align: center\">Nro.Coti</td>"
												+ "<td style= \"text-align: center\">CODIGO</td>"
												+ "<td style= \"text-align: center\">EQUIPO</td>"
												+ "<td style= \"text-align: center\">UN</td>"
												+ "<td style= \"text-align: center\">CANTIDAD</td>"
												+ "<td style= \"text-align: center\">MONEDA</td>"
												+ "<td style= \"text-align: center\">P.Venta</td>"
												+ "<td style= \"text-align: center\">"+'"""),_display_(/*294.54*/mapDiccionario/*294.68*/.get("ARR")),format.raw/*294.79*/("""'+" x DIA</td>"
												+ "<td style= \"text-align: center\">DIAS</td>"
												+ "<td style= \"text-align: center\">TASA CAMBIO</td>"
												+ "<td style=\"text-align: center;\">CFI<br>en "+'"""),_display_(/*297.64*/mapDiccionario/*297.78*/.get("PESOS")),format.raw/*297.91*/("""'+"</td>"
												+ "<td style= \"text-align: center\">"+'"""),_display_(/*298.54*/mapDiccionario/*298.68*/.get("ARRIENDO")),format.raw/*298.84*/("""'+" (en "+'"""),_display_(/*298.96*/mapDiccionario/*298.110*/.get("PESOS")),format.raw/*298.123*/("""'+")</td>"
												+ "<td style= \"text-align: center\">VENTA (en "+'"""),_display_(/*299.64*/mapDiccionario/*299.78*/.get("PESOS")),format.raw/*299.91*/("""'+")</td>"
											+ "</tr>";
								"""),format.raw/*301.9*/("""}"""),format.raw/*301.10*/("""else"""),format.raw/*301.14*/("""{"""),format.raw/*301.15*/("""
									"""),format.raw/*302.10*/("""if(jsonPorPyGyDJson[i][1] == "DETALLE")"""),format.raw/*302.49*/("""{"""),format.raw/*302.50*/("""
										"""),format.raw/*303.11*/("""tablaDetalleProyectos += ""
												+ "<tr>"
													+ "<td colspan=\"20\" style=\"background-color: #eeeeee;text-align:left;\">"
														+ jsonPorPyGyDJson[i][0]
													+ "</td>"
												+ "</tr>"
												+ "<tr>"
													+ "<td style= \"text-align: center\">Nro.MOVIM</td>"
													+ "<td style= \"text-align:center;min-width:65px\">FECHA</td>"
													+ "<td style= \"text-align: center;width:70px\">Nro.Coti</td>"
													+ "<td style= \"text-align: center;width:70px\">CODIGO</td>"
													+ "<td style= \"text-align: center\">EQUIPO</td>"
													+ "<td style= \"text-align: center\">UN</td>"
													+ "<td style= \"text-align: center\">CANTIDAD</td>"
													+ "<td style= \"text-align: center\">MONEDA</td>"
													+ "<td style= \"text-align: center\">P.Venta</td>"
													+ "<td style= \"text-align: center\">"+'"""),_display_(/*319.55*/mapDiccionario/*319.69*/.get("ARR")),format.raw/*319.80*/("""'+"x DIA</td>"
													+ "<td style= \"text-align: center\">DIAS</td>"
													+ "<td style= \"text-align: center\">TASA CAMBIO</td>"
													+ "<td style=\"text-align: center;\">CFI (en "+'"""),_display_(/*322.63*/mapDiccionario/*322.77*/.get("PESOS")),format.raw/*322.90*/("""'+")</td>"
													+ "<td style= \"text-align: center\">"+'"""),_display_(/*323.55*/mapDiccionario/*323.69*/.get("ARRIENDO")),format.raw/*323.85*/("""'+" (en "+'"""),_display_(/*323.97*/mapDiccionario/*323.111*/.get("PESOS")),format.raw/*323.124*/("""'+")</td>"
													+ "<td style= \"text-align: center\">VENTA (en "+'"""),_display_(/*324.65*/mapDiccionario/*324.79*/.get("PESOS")),format.raw/*324.92*/("""'+")</td>"
												+ "</tr>";
									"""),format.raw/*326.10*/("""}"""),format.raw/*326.11*/("""else"""),format.raw/*326.15*/("""{"""),format.raw/*326.16*/("""
										"""),format.raw/*327.11*/("""if(jsonPorPyGyDJson[i][0] == "TOTAL")"""),format.raw/*327.48*/("""{"""),format.raw/*327.49*/("""
											"""),format.raw/*328.12*/("""tablaDetalleProyectos += ""
													+ "<tr>"
														+ "<td colspan=\"14\" style=\"text-align:left;\">"
															+ "<h5><b><font color=\"blue\"> "+jsonPorPyGyDJson[i][0]+" </font></b></h5>"
														+ "</td>"
														+ "<td style=\"text-align:right;\">"
															+ "<h5><b><font color=\"blue\"> "+jsonPorPyGyDJson[i][14]+" </font></b></h5>"
														+ "</td>"
													+ "</tr>"
												+ "</tbody>"
											+ "</table>"
											+ "<br><br>";
													
										"""),format.raw/*341.11*/("""}"""),format.raw/*341.12*/("""else"""),format.raw/*341.16*/("""{"""),format.raw/*341.17*/("""
											"""),format.raw/*342.12*/("""if(jsonPorPyGyDJson[i][0] == "SUBTOTALES")"""),format.raw/*342.54*/("""{"""),format.raw/*342.55*/("""
												"""),format.raw/*343.13*/("""tablaDetalleProyectos += ""
														+ "<tr>"
															+ "<td style=\"text-align:left;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][0]+"</td>"
															+ "<td style=\"text-align:left;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][1]+"</td>"
															+ "<td style=\"text-align:left;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][2]+"</td>"
															+ "<td style=\"text-align:left;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][3]+"</td>"
															+ "<td style=\"text-align:left;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][4]+"</td>"
															+ "<td style=\"text-align:center;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][5]+"</td>"
															+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][6]+"</td>"
															+ "<td style=\"text-align:center;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][7]+"</td>"
															+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][8]+"</td>"
															+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][9]+"</td>"
															+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][10]+"</td>"
															+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][11]+"</td>"
															+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][12]+"</td>"
															+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][13]+"</td>"
															+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][14]+"</td>"
														+ "</tr>";
											"""),format.raw/*361.12*/("""}"""),format.raw/*361.13*/("""else"""),format.raw/*361.17*/("""{"""),format.raw/*361.18*/("""
												"""),format.raw/*362.13*/("""if(jsonPorPyGyDJson[i][0] == "AJUSTES")"""),format.raw/*362.52*/("""{"""),format.raw/*362.53*/("""
													"""),format.raw/*363.14*/("""tablaDetalleProyectos += ""
															+ "<tr>"
																+ "<td style=\"text-align:left;\"></td>"
																+ "<td style=\"text-align:left;\"></td>"
																+ "<td style=\"text-align:left;\"></td>"
																+ "<td style=\"text-align:left;\"></td>"
																+ "<td style=\"text-align:left;\">"+jsonPorPyGyDJson[i][4]+"</td>"
																+ "<td style=\"text-align:center;\">"+jsonPorPyGyDJson[i][5]+"</td>"
																+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][6]+"</td>"
																+ "<td style=\"text-align:center;\">"+jsonPorPyGyDJson[i][7]+"</td>"
																+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][8]+"</td>"
																+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][9]+"</td>"
																+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][10]+"</td>"
																+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][11]+"</td>"
																+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][12]+"</td>"
																+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][13]+"</td>"
																+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][14]+"</td>"
															+ "</tr>";
												"""),format.raw/*381.13*/("""}"""),format.raw/*381.14*/("""else"""),format.raw/*381.18*/("""{"""),format.raw/*381.19*/("""
													"""),format.raw/*382.14*/("""if(jsonPorPyGyDJson[i][0] == "SUBTOTALES2")"""),format.raw/*382.57*/("""{"""),format.raw/*382.58*/("""
														"""),format.raw/*383.15*/("""tablaDetalleProyectos += ""
																+ "<tr>"
																	+ "<td style=\"text-align:left;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][0]+"</td>"
																	+ "<td style=\"text-align:left;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][1]+"</td>"
																	+ "<td style=\"text-align:left;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][2]+"</td>"
																	+ "<td style=\"text-align:left;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][3]+"</td>"
																	+ "<td style=\"text-align:left;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][4]+"</td>"
																	+ "<td style=\"text-align:center;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][5]+"</td>"
																	+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][6]+"</td>"
																	+ "<td style=\"text-align:center;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][7]+"</td>"
																	+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][8]+"</td>"
																	+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][9]+"</td>"
																	+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][10]+"</td>"
																	+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][11]+"</td>"
																	+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][12]+"</td>"
																	+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][13]+"</td>"
																	+ "<td style=\"text-align:right;background-color: #F9E79F\">"+jsonPorPyGyDJson[i][14]+"</td>"
																+ "</tr>";
													"""),format.raw/*401.14*/("""}"""),format.raw/*401.15*/("""else"""),format.raw/*401.19*/("""{"""),format.raw/*401.20*/("""
														"""),format.raw/*402.15*/("""tablaDetalleProyectos += ""
																+ "<tr>"
																	+ "<td style=\"text-align:left;\">"+jsonPorPyGyDJson[i][0]+"</td>"
																	+ "<td style=\"text-align:left;\">"+jsonPorPyGyDJson[i][1]+"</td>"
																	+ "<td style=\"text-align:left;\">"+jsonPorPyGyDJson[i][2]+"</td>"
																	+ "<td style=\"text-align:left;\">"+jsonPorPyGyDJson[i][3]+"</td>"
																	+ "<td style=\"text-align:left;\">"+jsonPorPyGyDJson[i][4]+"</td>"
																	+ "<td style=\"text-align:center;\">"+jsonPorPyGyDJson[i][5]+"</td>"
																	+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][6]+"</td>"
																	+ "<td style=\"text-align:center;\">"+jsonPorPyGyDJson[i][7]+"</td>"
																	+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][8]+"</td>"
																	+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][9]+"</td>"
																	+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][10]+"</td>"
																	+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][11]+"</td>"
																	+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][12]+"</td>"
																	+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][13]+"</td>"
																	+ "<td style=\"text-align:right;\">"+jsonPorPyGyDJson[i][14]+"</td>"
																+ "</tr>";
													"""),format.raw/*420.14*/("""}"""),format.raw/*420.15*/("""
												"""),format.raw/*421.13*/("""}"""),format.raw/*421.14*/("""
											"""),format.raw/*422.12*/("""}"""),format.raw/*422.13*/("""
										"""),format.raw/*423.11*/("""}"""),format.raw/*423.12*/("""
									"""),format.raw/*424.10*/("""}"""),format.raw/*424.11*/("""
								"""),format.raw/*425.9*/("""}"""),format.raw/*425.10*/("""
							"""),format.raw/*426.8*/("""}"""),format.raw/*426.9*/("""
						"""),format.raw/*427.7*/("""}"""),format.raw/*427.8*/("""
					"""),format.raw/*428.6*/("""}"""),format.raw/*428.7*/("""
					"""),format.raw/*429.6*/("""document.getElementById('resumenPorPyGyDJson').innerHTML = tablaDetalleProyectos;
				*/

 				 if('"""),_display_(/*432.12*/mapDiccionario/*432.26*/.get("nEmpresa")),format.raw/*432.42*/("""' != "SM8 DE MEXICO")"""),format.raw/*432.63*/("""{"""),format.raw/*432.64*/("""
					"""),format.raw/*433.6*/("""var tabla = document.getElementById('tablaFamilias');
		 			tabla.deleteRow(tabla.rows.length-1);
		 			cellsOfRow = tabla.rows[tabla.rows.length-1].getElementsByTagName('td');
		 			cellsOfRow[0].innerHTML="TOTALES";
 				 """),format.raw/*437.7*/("""}"""),format.raw/*437.8*/("""
 				 
		 			"""),format.raw/*439.7*/("""document.getElementById('enProceso').style.display="none";
					document.getElementById('enCarga').style.display="none";
			   """),format.raw/*441.7*/("""}"""),format.raw/*441.8*/("""
		"""),format.raw/*442.3*/("""</script>
		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,desde:utilities.Fechas,hasta:utilities.Fechas,uf:Double,usd:Double,eur:Double): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,desde,hasta,uf,usd,eur)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,utilities.Fechas,utilities.Fechas,Double,Double,Double) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,desde,hasta,uf,usd,eur) => apply(mapDiccionario,mapPermiso,userMnu,desde,hasta,uf,usd,eur)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportFacturaResumen0.scala.html
                  HASH: 394fef2fc98fb9b3db73863b132f283a1444d12f
                  MATRIX: 1076->1|1354->186|1387->193|1495->274|1523->275|1564->289|1667->365|1696->366|1728->371|1768->385|1785->393|1825->395|1856->400|1925->448|1958->454|2141->611|2361->810|2397->819|3223->1618|3237->1623|3283->1648|3364->1702|3378->1707|3424->1732|3497->1778|3520->1780|3594->1827|3618->1830|3692->1877|3716->1880|4586->2720|4625->2732|4733->2812|4762->2813|4792->2816|4959->2956|4973->2961|5019->2986|5083->3023|5097->3028|5143->3053|5199->3082|5222->3084|5279->3114|5303->3117|5361->3147|5386->3150|5428->3163|5458->3164|5493->3171|5716->3365|5746->3366|5780->3372|5824->3387|5854->3388|5889->3395|6083->3559|6114->3560|6150->3568|6205->3595|6234->3596|6270->3604|6299->3605|6332->3609|6362->3610|6398->3618|6787->3979|6816->3980|6848->3984|6877->3985|6908->3988|6937->3989|6975->3999|7004->4000|7107->4074|7137->4075|7174->4084|7575->4457|7599->4471|7636->4485|7699->4520|7723->4534|7761->4550|7803->4563|7833->4564|7874->4576|8002->4675|8032->4676|8065->4680|8095->4681|8136->4693|8240->4769|8265->4783|8299->4794|8452->4918|8482->4919|8521->4929|8809->5189|8833->5203|8872->5219|9086->5405|9110->5419|9149->5435|9366->5624|9391->5638|9427->5651|9567->5762|9597->5763|9639->5776|9732->5840|9762->5841|9806->5856|9878->5899|9908->5900|9953->5916|10109->6044|10133->6058|10171->6074|10213->6087|10243->6088|10290->6106|10366->6153|10396->6154|10429->6158|10459->6159|10506->6177|10659->6301|10689->6302|10734->6318|11334->6889|11364->6890|11407->6904|11437->6905|11480->6919|11547->6957|11577->6958|11618->6970|11824->7148|11848->7162|11886->7178|11928->7191|11958->7192|12001->7206|12109->7285|12139->7286|12182->7300|12445->7534|12475->7535|12518->7549|12582->7584|12612->7585|12656->7600|13339->8254|13369->8255|13411->8268|13441->8269|13483->8282|14069->8840|14093->8854|14130->8868|14197->8907|14221->8921|14259->8937|14301->8950|14331->8951|14375->8966|14478->9041|14503->9055|14537->9066|14691->9191|14721->9192|14764->9206|14971->9385|14995->9399|15031->9412|15145->9498|15169->9512|15208->9528|15249->9540|15274->9554|15310->9567|15434->9663|15459->9677|15495->9690|15622->9789|15647->9803|15683->9816|15833->9937|15863->9938|15906->9952|16046->10063|16076->10064|16120->10079|16384->10315|16408->10329|16446->10345|16488->10358|16518->10359|16565->10377|16827->10610|16857->10611|16902->10627|17487->11183|17517->11184|17550->11188|17580->11189|17624->11204|17836->11388|17860->11402|17898->11418|17940->11431|17970->11432|18017->11450|18227->11631|18257->11632|18303->11649|18762->12079|18792->12080|18835->12094|18902->12132|18932->12133|18974->12146|19232->12375|19262->12376|19297->12383|19366->12423|19396->12424|19432->12432|19890->12862|19919->12863|19952->12867|19982->12868|20018->12876|20084->12913|20114->12914|20151->12923|20426->13170|20455->13171|20488->13175|20518->13176|20555->13185|20626->13227|20656->13228|20695->13238|21532->14047|21556->14061|21589->14072|21823->14278|21847->14292|21882->14305|21973->14368|21997->14382|22035->14398|22075->14410|22100->14424|22136->14437|22238->14511|22262->14525|22297->14538|22366->14579|22396->14580|22429->14584|22459->14585|22498->14595|22566->14634|22596->14635|22636->14646|23568->15550|23592->15564|23625->15575|23859->15781|23883->15795|23918->15808|24011->15873|24035->15887|24073->15903|24113->15915|24138->15929|24174->15942|24277->16017|24301->16031|24336->16044|24408->16087|24438->16088|24471->16092|24501->16093|24541->16104|24607->16141|24637->16142|24678->16154|25227->16674|25257->16675|25290->16679|25320->16680|25361->16692|25432->16734|25462->16735|25504->16748|27242->18457|27272->18458|27305->18462|27335->18463|27377->18476|27445->18515|27475->18516|27518->18530|28795->19778|28825->19779|28858->19783|28888->19784|28931->19798|29003->19841|29033->19842|29077->19857|30851->21602|30881->21603|30914->21607|30944->21608|30988->21623|32387->22993|32417->22994|32459->23007|32489->23008|32530->23020|32560->23021|32600->23032|32630->23033|32669->23043|32699->23044|32736->23053|32766->23054|32802->23062|32831->23063|32866->23070|32895->23071|32929->23077|32958->23078|32992->23084|33121->23185|33145->23199|33183->23215|33233->23236|33263->23237|33297->23243|33549->23467|33578->23468|33620->23482|33775->23609|33804->23610|33835->23613
                  LINES: 28->1|34->3|35->4|37->6|37->6|38->7|41->10|41->10|42->11|46->15|46->15|46->15|48->17|48->17|50->19|52->21|53->22|55->24|77->46|77->46|77->46|78->47|78->47|78->47|79->48|79->48|80->49|80->49|81->50|81->50|115->84|119->88|121->90|121->90|122->91|127->96|127->96|127->96|128->97|128->97|128->97|129->98|129->98|130->99|130->99|131->100|131->100|132->101|132->101|133->102|140->109|140->109|141->110|141->110|141->110|142->111|142->111|142->111|143->112|144->113|144->113|145->114|145->114|145->114|145->114|147->116|156->125|156->125|157->126|157->126|158->127|158->127|161->130|161->130|163->132|163->132|166->135|171->140|171->140|171->140|172->141|172->141|172->141|172->141|172->141|173->142|174->143|174->143|174->143|174->143|175->144|175->144|175->144|175->144|177->146|177->146|178->147|180->149|180->149|180->149|182->151|182->151|182->151|184->153|184->153|184->153|188->157|188->157|189->158|190->159|190->159|191->160|191->160|191->160|192->161|194->163|194->163|194->163|194->163|194->163|195->164|196->165|196->165|196->165|196->165|197->166|199->168|199->168|200->169|207->176|207->176|208->177|208->177|209->178|210->179|210->179|211->180|214->183|214->183|214->183|214->183|214->183|215->184|216->185|216->185|217->186|220->189|220->189|221->190|221->190|221->190|222->191|228->197|228->197|229->198|229->198|230->199|241->210|241->210|241->210|242->211|242->211|242->211|242->211|242->211|243->212|243->212|243->212|243->212|245->214|245->214|246->215|247->216|247->216|247->216|248->217|248->217|248->217|248->217|248->217|248->217|249->218|249->218|249->218|250->219|250->219|250->219|254->223|254->223|255->224|256->225|256->225|257->226|259->228|259->228|259->228|259->228|259->228|260->229|262->231|262->231|263->232|268->237|268->237|268->237|268->237|269->238|271->240|271->240|271->240|271->240|271->240|272->241|274->243|274->243|275->244|280->249|280->249|281->250|282->251|282->251|283->252|291->260|291->260|292->261|292->261|292->261|293->262|301->270|301->270|301->270|301->270|302->271|302->271|302->271|303->272|309->278|309->278|309->278|309->278|310->279|310->279|310->279|311->280|325->294|325->294|325->294|328->297|328->297|328->297|329->298|329->298|329->298|329->298|329->298|329->298|330->299|330->299|330->299|332->301|332->301|332->301|332->301|333->302|333->302|333->302|334->303|350->319|350->319|350->319|353->322|353->322|353->322|354->323|354->323|354->323|354->323|354->323|354->323|355->324|355->324|355->324|357->326|357->326|357->326|357->326|358->327|358->327|358->327|359->328|372->341|372->341|372->341|372->341|373->342|373->342|373->342|374->343|392->361|392->361|392->361|392->361|393->362|393->362|393->362|394->363|412->381|412->381|412->381|412->381|413->382|413->382|413->382|414->383|432->401|432->401|432->401|432->401|433->402|451->420|451->420|452->421|452->421|453->422|453->422|454->423|454->423|455->424|455->424|456->425|456->425|457->426|457->426|458->427|458->427|459->428|459->428|460->429|463->432|463->432|463->432|463->432|463->432|464->433|468->437|468->437|470->439|472->441|472->441|473->442
                  -- GENERATED --
              */
          