
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

object reportFacturaResumen extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,utilities.Fechas,utilities.Fechas,Double,Double,Double,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
desde: utilities.Fechas, hasta: utilities.Fechas, uf: Double, usd: Double, eur: Double, nroDec: Long):play.twirl.api.HtmlFormat.Appendable = {
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
			"""),_display_(/*21.5*/barraTitulo(mapDiccionario, "REPORTE RESUMEN Y DETALLE POR TODOS LOS PROYECTOS POR FAMILIA (INCLUIDOS AJUSTES DE EP)",
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
			
			<form id="excel" class="formulario" method="post" action="/reportFacturaResumenExcel/">
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
						"""),_display_(if(! mapDiccionario.get("nEmpresa").equals("SM8 DE MEXICO"))/*66.68*/ {_display_(Seq[Any](format.raw/*66.70*/("""
							"""),format.raw/*67.8*/("""<div align="left"><h5><b><font color="#008000"> RESUMEN POR FAMILIAS Y PROYECTOS (SIN AJUSTES A EP): </font></b></h5></div>
						""")))} else {null} ),format.raw/*68.8*/("""
							"""),format.raw/*69.8*/("""<div id="resumenPorPyGJson"></div>
						</td>
					</tr>
			
			</table>
			
		</div>
		
	""")))}),format.raw/*77.3*/("""
		
		
		
		"""),format.raw/*81.3*/("""<script type="text/javascript">
		
		window.addEventListener('load', function() """),format.raw/*83.46*/("""{"""),format.raw/*83.47*/("""
		"""),format.raw/*84.3*/("""document.getElementById('mostrarmostrar').style.display="block";
		
		
		
		let formData = new FormData();
		formData.append("fechaDesde",""""),_display_(/*89.34*/desde/*89.39*/.getFechaSql().toString()),format.raw/*89.64*/("""");
		formData.append("fechaHasta",""""),_display_(/*90.34*/hasta/*90.39*/.getFechaSql().toString()),format.raw/*90.64*/("""");
		formData.append("uf",""""),_display_(/*91.26*/uf),format.raw/*91.28*/("""");
		formData.append("usd",""""),_display_(/*92.27*/usd),format.raw/*92.30*/("""");
		formData.append("eur",""""),_display_(/*93.27*/eur),format.raw/*93.30*/("""");
		$.ajax("""),format.raw/*94.10*/("""{"""),format.raw/*94.11*/("""
		    """),format.raw/*95.7*/("""url: "/reportFacturaResumenJson/",
		    type: "POST",
		    method: "POST",
		    data: formData,
		    cache: false,
		    contentType: false,
		 	processData: false,
		 	success: function(rs)"""),format.raw/*102.26*/("""{"""),format.raw/*102.27*/("""
		 		"""),format.raw/*103.6*/("""if(rs=="error")"""),format.raw/*103.21*/("""{"""),format.raw/*103.22*/("""
		 			"""),format.raw/*104.7*/("""alertify.alert('Se presento un problema o usted no tiene permisos otorgados, por favor vuelva a ingresar y si persiste el problema, contactar a soporte', function()"""),format.raw/*104.171*/("""{"""),format.raw/*104.172*/(""" 
						"""),format.raw/*105.7*/("""location.href = "/"; 
					"""),format.raw/*106.6*/("""}"""),format.raw/*106.7*/(""");
		 		"""),format.raw/*107.6*/("""}"""),format.raw/*107.7*/("""else"""),format.raw/*107.11*/("""{"""),format.raw/*107.12*/("""
	
					"""),format.raw/*109.6*/("""let jsonPorPJson =rs["resumenPorPJson"];
					let jsonTotales =rs["resumenTotalesJson"];
					let jsonPorPyGJson = rs["resumenPorPyGJson"];
				vistaPrincipal(jsonPorPJson, jsonTotales, jsonPorPyGJson);
				
				"""),format.raw/*114.5*/("""}"""),format.raw/*114.6*/("""
			"""),format.raw/*115.4*/("""}"""),format.raw/*115.5*/("""
		"""),format.raw/*116.3*/("""}"""),format.raw/*116.4*/(""");
		
		
	"""),format.raw/*119.2*/("""}"""),format.raw/*119.3*/(""");
	
	const vistaPrincipal =(jsonPorPJson, jsonTotales, jsonPorPyGJson) =>"""),format.raw/*121.70*/("""{"""),format.raw/*121.71*/("""
		

				"""),format.raw/*124.5*/("""let tablaProyectos = ""
					+ "<table id=\"tablaProyectos\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
						+ "<thead style=\"background-color: #eeeeee\">"
							+ "<TR> "
								+ "<TH style= \"text-align:center; vertical-align:top;\">SUCURSAL</TH>"
								+ "<TH style= \"text-align:center; vertical-align:top;\">NOMBRE<br>"+'"""),_display_(/*129.80*/mapDiccionario/*129.94*/.get("BODEGA")),format.raw/*129.108*/("""'+"/PROYECTO</TH>";
										if('"""),_display_(/*130.16*/mapDiccionario/*130.30*/.get("nEmpresa")),format.raw/*130.46*/("""' == "CIHLA")"""),format.raw/*130.59*/("""{"""),format.raw/*130.60*/("""
											"""),format.raw/*131.12*/("""tablaProyectos += "<TH style= \"text-align:center;vertical-align:top;\">Nro CARGO</TH>";
										"""),format.raw/*132.11*/("""}"""),format.raw/*132.12*/("""else"""),format.raw/*132.16*/("""{"""),format.raw/*132.17*/("""
											"""),format.raw/*133.12*/("""tablaProyectos += "<TH style= \"text-align:center;vertical-align:top;\">"+'"""),_display_(/*133.88*/mapDiccionario/*133.102*/.get("RUT")),format.raw/*133.113*/("""'+"<BR>CLIENTE</TH>"
												+ "<TH style= \"text-align:center;vertical-align:top;\">NOMBRE<BR>CLIENTE</TH>";
										"""),format.raw/*135.11*/("""}"""),format.raw/*135.12*/("""
									"""),format.raw/*136.10*/("""tablaProyectos += "<TH style= \"text-align:center; vertical-align:top;\">NOMBRE<br>PROYECTO</TH>"
											+ "<TH style= \"text-align:center; vertical-align:top;\">CFI</TH>"
											+ "<TH style= \"text-align:center; vertical-align:top;\">SubTotal<br>"+'"""),_display_(/*138.85*/mapDiccionario/*138.99*/.get("ARRIENDO")),format.raw/*138.115*/("""'+"</TH>"
											+ "<TH style= \"text-align:center; vertical-align:top;\">SubTotal<br>VENTA</TH>"
											+ "<TH style= \"text-align:center; vertical-align:top;\">Ajustes<BR>("+'"""),_display_(/*140.85*/mapDiccionario/*140.99*/.get("ARRIENDO")),format.raw/*140.115*/("""'+")</TH>"
											+ "<TH style= \"text-align:center; vertical-align:top;\">Ajustes<BR>(VENTA)</TH>"
											+ "<TH style= \"text-align:center; vertical-align:top;\">TOTAL<BR>(en "+'"""),_display_(/*142.86*/mapDiccionario/*142.100*/.get("PESOS")),format.raw/*142.113*/("""'+")</TH>"
								+ "</TR>"
									+ "</thead>"
										+ "<tbody>";
											for(var i in jsonPorPJson)"""),format.raw/*146.38*/("""{"""),format.raw/*146.39*/("""
												"""),format.raw/*147.13*/("""tablaProyectos += "<TR>";
													for(var t in jsonTotales)"""),format.raw/*148.39*/("""{"""),format.raw/*148.40*/("""
														"""),format.raw/*149.15*/("""if(jsonPorPJson[i][1] == jsonTotales[t][0])"""),format.raw/*149.58*/("""{"""),format.raw/*149.59*/("""
															"""),format.raw/*150.16*/("""tablaProyectos += "<td>"+jsonPorPJson[i][14]+"</td>"
																	+ "<td>"+jsonPorPJson[i][5]+"</td>";
																if('"""),_display_(/*152.22*/mapDiccionario/*152.36*/.get("nEmpresa")),format.raw/*152.52*/("""' == 'CIHLA')"""),format.raw/*152.65*/("""{"""),format.raw/*152.66*/("""
																	"""),format.raw/*153.18*/("""tablaProyectos += "<td></td>";
																"""),format.raw/*154.17*/("""}"""),format.raw/*154.18*/("""else"""),format.raw/*154.22*/("""{"""),format.raw/*154.23*/("""
																	"""),format.raw/*155.18*/("""tablaProyectos += "<td>"+jsonPorPJson[i][6]+"</td>"
																			+ "<td>"+jsonPorPJson[i][7]+"</td>";
																"""),format.raw/*157.17*/("""}"""),format.raw/*157.18*/("""
															"""),format.raw/*158.16*/("""tablaProyectos += "<td>"+jsonPorPJson[i][8]+"</td>"
											    					+ "<td style= \"text-align:right;\">"+jsonTotales[t][3]+"</td>"
											    					+ "<td style= \"text-align:right;\">"+jsonTotales[t][1]+"</td>"
											    					+ "<td style= \"text-align:right;\">"+jsonTotales[t][2]+"</td>"
											    					+ "<td style= \"text-align:right;\">"+jsonTotales[t][5]+"</td>"
											    					+ "<td style= \"text-align:right;\">"+jsonTotales[t][6]+"</td>"
											    					+ "<td style= \"text-align:right;\">"+jsonTotales[t][4]+"</td>";
														"""),format.raw/*165.15*/("""}"""),format.raw/*165.16*/("""
													"""),format.raw/*166.14*/("""}"""),format.raw/*166.15*/("""
													"""),format.raw/*167.14*/("""tablaProyectos += "</TR>";
											"""),format.raw/*168.12*/("""}"""),format.raw/*168.13*/("""
											"""),format.raw/*169.12*/("""tablaProyectos += "<TR>"
													+ "<td style=\"background-color: #eeeeee\">TOTALES</td>"
													+ "<td style=\"background-color: #eeeeee\"></td>";
												if(! '"""),_display_(/*172.20*/mapDiccionario/*172.34*/.get("nEmpresa")),format.raw/*172.50*/("""' == 'CIHLA')"""),format.raw/*172.63*/("""{"""),format.raw/*172.64*/("""
													"""),format.raw/*173.14*/("""tablaProyectos += "<td style=\"background-color: #eeeeee\"></td>";
												"""),format.raw/*174.13*/("""}"""),format.raw/*174.14*/("""
													"""),format.raw/*175.14*/("""tablaProyectos += "<td style=\"background-color: #eeeeee\"></td>"
															+ "<td style=\"background-color: #eeeeee\"></td>"
															+ "<td style=\"background-color: #eeeeee\"></td>";
												for(var t in jsonTotales)"""),format.raw/*178.38*/("""{"""),format.raw/*178.39*/("""
													"""),format.raw/*179.14*/("""if(jsonTotales[t][0] == "SUBTOTAL")"""),format.raw/*179.49*/("""{"""),format.raw/*179.50*/("""
														"""),format.raw/*180.15*/("""tablaProyectos += "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonTotales[t][3]+"</td>"
									    					+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonTotales[t][1]+"</td>"
									    					+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonTotales[t][2]+"</td>"
									    					+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonTotales[t][5]+"</td>"
									    					+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonTotales[t][6]+"</td>"
									    					+ "<td style=\"background-color: #eeeeee;text-align:right;\">"+jsonTotales[t][4]+"</td>";
													"""),format.raw/*186.14*/("""}"""),format.raw/*186.15*/("""
												"""),format.raw/*187.13*/("""}"""),format.raw/*187.14*/("""
												"""),format.raw/*188.13*/("""tablaProyectos += "</TR>"
														+ "</tbody>"
														+ "</table>";
				document.getElementById('resumenPorPJson').innerHTML = tablaProyectos;
				
				if('"""),_display_(/*193.10*/mapDiccionario/*193.24*/.get("nEmpresa")),format.raw/*193.40*/("""' != "SM8 DE MEXICO") """),format.raw/*193.62*/("""{"""),format.raw/*193.63*/("""
					"""),format.raw/*194.6*/("""let tablaFamilias = ""
	    					+ "<table id=\"tablaFamilias\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
	    						+ "<thead style=\"background-color: #eeeeee\">"
	    							+ "<TR>"
	    								+ "<TH style= \"text-align:center;vertical-align:top;\">FAMILIA/GRUPO</TH>"
					+ "<TH style= \"text-align:center;vertical-align:top;\">SUCURSAL</TH>"
	    								+ "<TH style= \"text-align:center;vertical-align:top;\">"+'"""),_display_(/*200.74*/mapDiccionario/*200.88*/.get("BODEGA")),format.raw/*200.102*/("""'+"/PROYECTO</TH>";
	    								if(!'"""),_display_(/*201.20*/mapDiccionario/*201.34*/.get("nEmpresa")),format.raw/*201.50*/("""' == "CIHLA")"""),format.raw/*201.63*/("""{"""),format.raw/*201.64*/("""
	    									"""),format.raw/*202.15*/("""tablaFamilias += "<TH style= \"text-align:center;vertical-align:top;\">"+'"""),_display_(/*202.90*/mapDiccionario/*202.104*/.get("RUT")),format.raw/*202.115*/("""'+" CLIENTE</TH>"
	    											+ "<TH style= \"text-align:center;vertical-align:top;\">NOMBRE CLIENTE</TH>";
	    								"""),format.raw/*204.14*/("""}"""),format.raw/*204.15*/("""
	    								"""),format.raw/*205.14*/("""tablaFamilias += "<TH style= \"text-align:center;vertical-align:top;\">NOMBRE<br>PROYECTO</TH>"
	    										+ "<TH style= \"text-align:center;vertical-align:top;\">CFI (en "+'"""),_display_(/*206.84*/mapDiccionario/*206.98*/.get("PESOS")),format.raw/*206.111*/("""'+")</TH>"
	    										+ "<TH style= \"text-align:center;vertical-align:top;\">"+'"""),_display_(/*207.76*/mapDiccionario/*207.90*/.get("ARRIENDO")),format.raw/*207.106*/("""'+" (en "+'"""),_display_(/*207.118*/mapDiccionario/*207.132*/.get("PESOS")),format.raw/*207.145*/("""'+")</TH>"
	    										+ "<TH style= \"text-align:center;vertical-align:top;\">VENTA (en "+'"""),_display_(/*208.86*/mapDiccionario/*208.100*/.get("PESOS")),format.raw/*208.113*/("""'+")</TH>"
	    										+ "<TH style= \"text-align:center;vertical-align:top;\">TOTAL<BR>(en "+'"""),_display_(/*209.89*/mapDiccionario/*209.103*/.get("PESOS")),format.raw/*209.116*/("""'+")</TH>"
	    							+ "</TR>"
	    						+ "</thead>"
	    						+ "<tbody>";
			
			
									let totCfi = 0;
									let totArr = 0;
									let totVta = 0;
									let totTot = 0;
			
									let auxGrupo = "0";
	    							for(var i in jsonPorPyGJson)"""),format.raw/*221.41*/("""{"""),format.raw/*221.42*/("""
		
										"""),format.raw/*223.11*/("""if(jsonPorPyGJson[i][0] != "") """),format.raw/*223.42*/("""{"""),format.raw/*223.43*/("""
											"""),format.raw/*224.12*/("""auxGrupo = jsonPorPyGJson[i][0];
										"""),format.raw/*225.11*/("""}"""),format.raw/*225.12*/("""else"""),format.raw/*225.16*/("""{"""),format.raw/*225.17*/("""
											
		    								"""),format.raw/*227.15*/("""if(jsonPorPyGJson[i][3] != "SUBTOTAL" && jsonPorPyGJson[i][3] != "TOTAL")"""),format.raw/*227.88*/("""{"""),format.raw/*227.89*/("""
			
												"""),format.raw/*229.13*/("""let cfi = jsonPorPyGJson[i][4].replace(/,/g,'');
												let arr = jsonPorPyGJson[i][5].replace(/,/g,'');
												let vta = jsonPorPyGJson[i][6].replace(/,/g,'');
												let tot = jsonPorPyGJson[i][7].replace(/,/g,'');
												
												if(tot.length > 1)"""),format.raw/*234.31*/("""{"""),format.raw/*234.32*/("""
													"""),format.raw/*235.14*/("""totCfi += parseFloat(cfi);
													totArr += parseFloat(arr);
													totVta += parseFloat(vta);
													totTot += parseFloat(tot);
												"""),format.raw/*239.13*/("""}"""),format.raw/*239.14*/("""
												
												
			
												"""),format.raw/*243.13*/("""tablaFamilias += "<TR>";
		    											tablaFamilias += "<td style=\"text-align:left;\">"+auxGrupo+"</td>"
														+ "<td style=\"text-align:left;\">"+jsonPorPyGJson[i][9]+"</td>"
		    											+ "<td style=\"text-align:left;\">"+jsonPorPyGJson[i][1]+"</td>";
		    											if(!'"""),_display_(/*247.24*/mapDiccionario/*247.38*/.get("nEmpresa")),format.raw/*247.54*/("""' == "CIHLA")"""),format.raw/*247.67*/("""{"""),format.raw/*247.68*/("""
		    												"""),format.raw/*248.19*/("""tablaFamilias += "<td style=\"text-align:left;\">"+jsonPorPyGJson[i][2]+"</td>"
		    														+ "<td style=\"text-align:left;\">"+jsonPorPyGJson[i][3]+"</td>";
		    											"""),format.raw/*250.18*/("""}"""),format.raw/*250.19*/("""
		    											"""),format.raw/*251.18*/("""tablaFamilias += "<td style=\"text-align:left;\">"+jsonPorPyGJson[i][8]+"</td>"
		    													+ "<td style=\"text-align:right;\">"+jsonPorPyGJson[i][4]+"</td>"
		    													+ "<td style=\"text-align:right;\">"+jsonPorPyGJson[i][5]+"</td>"
		    													+ "<td style=\"text-align:right;\">"+jsonPorPyGJson[i][6]+"</td>"
		    													+ "<td style=\"text-align:right;\">"+jsonPorPyGJson[i][7]+"</td>";
												tablaFamilias += "</TR>";
		    								"""),format.raw/*257.15*/("""}"""),format.raw/*257.16*/("""
						
										"""),format.raw/*259.11*/("""}"""),format.raw/*259.12*/("""
		
	    							"""),format.raw/*261.13*/("""}"""),format.raw/*261.14*/("""
	    							"""),format.raw/*262.13*/("""tablaFamilias += "</tbody><tfoot>"
											+ "<tr>"
												+ "<td>TOTALES</td>"
												+ "<td></td>"
												+ "<td></td>"
												+ "<td></td>"
												+ "<td style=\"text-align:right;\">"+formatStandar(totCfi,'"""),_display_(/*268.73*/nroDec),format.raw/*268.79*/("""')+"</td>"
												+ "<td style=\"text-align:right;\">"+formatStandar(totArr,'"""),_display_(/*269.73*/nroDec),format.raw/*269.79*/("""')+"</td>"
												+ "<td style=\"text-align:right;\">"+formatStandar(totVta,'"""),_display_(/*270.73*/nroDec),format.raw/*270.79*/("""')+"</td>"
												+ "<td style=\"text-align:right;\">"+formatStandar(totTot,'"""),_display_(/*271.73*/nroDec),format.raw/*271.79*/("""')+"</td>"
											+ "</tr>"
											+ "<tr></tr>"
	    									+ "</tfoot></table>";
						
					document.getElementById('resumenPorPyGJson').innerHTML = tablaFamilias;
				"""),format.raw/*277.5*/("""}"""),format.raw/*277.6*/("""
				
				"""),format.raw/*279.5*/("""if('"""),_display_(/*279.10*/mapDiccionario/*279.24*/.get("nEmpresa")),format.raw/*279.40*/("""' != "SM8 DE MEXICO")"""),format.raw/*279.61*/("""{"""),format.raw/*279.62*/("""
					"""),format.raw/*280.6*/("""var tabla = document.getElementById('tablaFamilias');
		 			tabla.deleteRow(tabla.rows.length-1);
 				 """),format.raw/*282.7*/("""}"""),format.raw/*282.8*/("""
 				 	
		 			"""),format.raw/*284.7*/("""document.getElementById('enProceso').style.display="none";
					document.getElementById('enCarga').style.display="none";
 				 
			   """),format.raw/*287.7*/("""}"""),format.raw/*287.8*/("""
		"""),format.raw/*288.3*/("""</script>
		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,desde:utilities.Fechas,hasta:utilities.Fechas,uf:Double,usd:Double,eur:Double,nroDec:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,desde,hasta,uf,usd,eur,nroDec)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,utilities.Fechas,utilities.Fechas,Double,Double,Double,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,desde,hasta,uf,usd,eur,nroDec) => apply(mapDiccionario,mapPermiso,userMnu,desde,hasta,uf,usd,eur,nroDec)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportFacturaResumen.scala.html
                  HASH: 2d3d016c72dd5a81ac78f10ea92a08057a6228c2
                  MATRIX: 1080->1|1372->200|1405->207|1513->288|1541->289|1582->303|1685->379|1714->380|1746->385|1786->399|1803->407|1843->409|1874->414|1943->462|1976->468|2159->625|2382->827|2418->836|3243->1634|3257->1639|3303->1664|3384->1718|3398->1723|3444->1748|3517->1794|3540->1796|3614->1843|3638->1846|3712->1893|3736->1896|4194->2327|4234->2329|4269->2337|4443->2468|4478->2476|4600->2568|4639->2580|4747->2660|4776->2661|4806->2664|4973->2804|4987->2809|5033->2834|5097->2871|5111->2876|5157->2901|5213->2930|5236->2932|5293->2962|5317->2965|5374->2995|5398->2998|5439->3011|5468->3012|5502->3019|5725->3213|5755->3214|5789->3220|5833->3235|5863->3236|5898->3243|6092->3407|6123->3408|6159->3416|6214->3443|6243->3444|6279->3452|6308->3453|6341->3457|6371->3458|6407->3466|6647->3678|6676->3679|6708->3683|6737->3684|6768->3687|6797->3688|6835->3698|6864->3699|6967->3773|6997->3774|7034->3783|7435->4156|7459->4170|7496->4184|7559->4219|7583->4233|7621->4249|7663->4262|7693->4263|7734->4275|7862->4374|7892->4375|7925->4379|7955->4380|7996->4392|8100->4468|8125->4482|8159->4493|8312->4617|8342->4618|8381->4628|8669->4888|8693->4902|8732->4918|8946->5104|8970->5118|9009->5134|9226->5323|9251->5337|9287->5350|9427->5461|9457->5462|9499->5475|9592->5539|9622->5540|9666->5555|9738->5598|9768->5599|9813->5615|9969->5743|9993->5757|10031->5773|10073->5786|10103->5787|10150->5805|10226->5852|10256->5853|10289->5857|10319->5858|10366->5876|10519->6000|10549->6001|10594->6017|11194->6588|11224->6589|11267->6603|11297->6604|11340->6618|11407->6656|11437->6657|11478->6669|11684->6847|11708->6861|11746->6877|11788->6890|11818->6891|11861->6905|11969->6984|11999->6985|12042->6999|12305->7233|12335->7234|12378->7248|12442->7283|12472->7284|12516->7299|13199->7953|13229->7954|13271->7967|13301->7968|13343->7981|13541->8151|13565->8165|13603->8181|13654->8203|13684->8204|13718->8210|14210->8674|14234->8688|14271->8702|14338->8741|14362->8755|14400->8771|14442->8784|14472->8785|14516->8800|14619->8875|14644->8889|14678->8900|14832->9025|14862->9026|14905->9040|15112->9219|15136->9233|15172->9246|15286->9332|15310->9346|15349->9362|15390->9374|15415->9388|15451->9401|15575->9497|15600->9511|15636->9524|15763->9623|15788->9637|15824->9650|16115->9912|16145->9913|16188->9927|16248->9958|16278->9959|16319->9971|16391->10014|16421->10015|16454->10019|16484->10020|16540->10047|16642->10120|16672->10121|16718->10138|17022->10413|17052->10414|17095->10428|17283->10587|17313->10588|17385->10631|17708->10926|17732->10940|17770->10956|17812->10969|17842->10970|17890->10989|18102->11172|18132->11173|18179->11191|18681->11664|18711->11665|18758->11683|18788->11684|18833->11700|18863->11701|18905->11714|19171->11952|19199->11958|19310->12041|19338->12047|19449->12130|19477->12136|19588->12219|19616->12225|19825->12406|19854->12407|19892->12417|19925->12422|19949->12436|19987->12452|20037->12473|20067->12474|20101->12480|20233->12584|20262->12585|20305->12600|20467->12734|20496->12735|20527->12738
                  LINES: 28->1|34->3|35->4|37->6|37->6|38->7|41->10|41->10|42->11|46->15|46->15|46->15|48->17|48->17|50->19|52->21|53->22|55->24|77->46|77->46|77->46|78->47|78->47|78->47|79->48|79->48|80->49|80->49|81->50|81->50|97->66|97->66|98->67|99->68|100->69|108->77|112->81|114->83|114->83|115->84|120->89|120->89|120->89|121->90|121->90|121->90|122->91|122->91|123->92|123->92|124->93|124->93|125->94|125->94|126->95|133->102|133->102|134->103|134->103|134->103|135->104|135->104|135->104|136->105|137->106|137->106|138->107|138->107|138->107|138->107|140->109|145->114|145->114|146->115|146->115|147->116|147->116|150->119|150->119|152->121|152->121|155->124|160->129|160->129|160->129|161->130|161->130|161->130|161->130|161->130|162->131|163->132|163->132|163->132|163->132|164->133|164->133|164->133|164->133|166->135|166->135|167->136|169->138|169->138|169->138|171->140|171->140|171->140|173->142|173->142|173->142|177->146|177->146|178->147|179->148|179->148|180->149|180->149|180->149|181->150|183->152|183->152|183->152|183->152|183->152|184->153|185->154|185->154|185->154|185->154|186->155|188->157|188->157|189->158|196->165|196->165|197->166|197->166|198->167|199->168|199->168|200->169|203->172|203->172|203->172|203->172|203->172|204->173|205->174|205->174|206->175|209->178|209->178|210->179|210->179|210->179|211->180|217->186|217->186|218->187|218->187|219->188|224->193|224->193|224->193|224->193|224->193|225->194|231->200|231->200|231->200|232->201|232->201|232->201|232->201|232->201|233->202|233->202|233->202|233->202|235->204|235->204|236->205|237->206|237->206|237->206|238->207|238->207|238->207|238->207|238->207|238->207|239->208|239->208|239->208|240->209|240->209|240->209|252->221|252->221|254->223|254->223|254->223|255->224|256->225|256->225|256->225|256->225|258->227|258->227|258->227|260->229|265->234|265->234|266->235|270->239|270->239|274->243|278->247|278->247|278->247|278->247|278->247|279->248|281->250|281->250|282->251|288->257|288->257|290->259|290->259|292->261|292->261|293->262|299->268|299->268|300->269|300->269|301->270|301->270|302->271|302->271|308->277|308->277|310->279|310->279|310->279|310->279|310->279|310->279|311->280|313->282|313->282|315->284|318->287|318->287|319->288
                  -- GENERATED --
              */
          