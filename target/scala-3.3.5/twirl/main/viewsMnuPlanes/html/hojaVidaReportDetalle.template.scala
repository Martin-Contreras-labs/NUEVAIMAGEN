
package viewsMnuPlanes.html

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

object hojaVidaReportDetalle extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion],List[String],List[String],List[tables.HojaVida],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
allPlan: List[tables.PlanMantencion], listAtributos: List[String], listCompra: List[String], listDetalle: List[tables.HojaVida]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),format.raw/*4.1*/("""<!-- ANCLA CABECERAS DE TABLAS -->
	<style type="text/css"> 
        thead tr th """),format.raw/*6.21*/("""{"""),format.raw/*6.22*/(""" 
            """),format.raw/*7.13*/("""position: sticky;
			background-color: #eeeeee;
            top: 0;
        """),format.raw/*10.9*/("""}"""),format.raw/*10.10*/("""
    """),format.raw/*11.5*/("""</style>

    	
"""),_display_(/*14.2*/main("")/*14.10*/ {_display_(Seq[Any](format.raw/*14.12*/("""

"""),_display_(/*16.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*16.50*/("""
	"""),format.raw/*17.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*18.4*/barraTitulo(mapDiccionario, "HOJA DE VIDA POR EQUIPO: "+allPlan.get(0).equipoCodigo+" - "+allPlan.get(0).equipoNombre,"")),format.raw/*18.125*/("""
		"""),format.raw/*19.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
				<div class="noprint">
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
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<tr>
						<td colspan="2">
							<b>DETALLE DEL EQUIPO</b>
						</td>
						<td colspan="6">
							<b>ULTIMA COMPRA</b>
						</td>
					</tr>
					<tr>
						<td>GRUPO:</td>
						<td>"""),_display_(/*55.12*/allPlan/*55.19*/.get(0).equipoGrupo),format.raw/*55.38*/("""</td>
						<td>"""),_display_(/*56.12*/mapDiccionario/*56.26*/.get("RUT")),format.raw/*56.37*/("""-PROV:</td>
						<td>"""),_display_(/*57.12*/listCompra/*57.22*/.get(0)),format.raw/*57.29*/("""</td>
						<td>FECHA:</td>
						<td>"""),_display_(/*59.12*/listCompra/*59.22*/.get(3)),format.raw/*59.29*/("""</td>
						<td>MONEDA:</td>
						<td>"""),_display_(/*61.12*/listCompra/*61.22*/.get(5)),format.raw/*61.29*/("""</td>
					</tr>
					<tr>
						<td>CODIGO EQUIPO:</td>
						<td>"""),_display_(/*65.12*/allPlan/*65.19*/.get(0).equipoCodigo),format.raw/*65.39*/("""</td>
						<td>PROVEEDOR:</td>
						<td>"""),_display_(/*67.12*/listCompra/*67.22*/.get(1)),format.raw/*67.29*/("""</td>
						<td>PDF:</td>
						<td>
							"""),_display_(if(listCompra.get(4)!="0")/*70.35*/{_display_(Seq[Any](format.raw/*70.36*/("""
								"""),format.raw/*71.9*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*71.43*/listCompra/*71.53*/.get(4)),format.raw/*71.60*/("""')">
									<kbd style="background-color: #85C1E9">pdf</kbd>
								</a>
							""")))}else/*74.13*/{_display_(Seq[Any](format.raw/*74.14*/("""
							"""),format.raw/*75.8*/("""---
							""")))}),format.raw/*76.9*/("""
						"""),format.raw/*77.7*/("""</td>
						<td>PRECIO:</td>
						<td>"""),_display_(/*79.12*/listCompra/*79.22*/.get(6)),format.raw/*79.29*/("""</td>
					</tr>
					<tr>
						<td>NOMBRE EQUIPO:</td>
						<td>"""),_display_(/*83.12*/allPlan/*83.19*/.get(0).equipoNombre),format.raw/*83.39*/("""</td>
						<td>FACTURA:</td>
						<td>"""),_display_(/*85.12*/listCompra/*85.22*/.get(2)),format.raw/*85.29*/("""</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="20">
							<b>ATRIBUTOS DEL EQUIPO</b>
						</td>
					</tr>
					<tr>
						<td colspan="20">
							"""),_display_(/*96.9*/for(lista <- listAtributos) yield /*96.36*/{_display_(Seq[Any](format.raw/*96.37*/("""
									"""),_display_(/*97.11*/lista),format.raw/*97.16*/("""|
								""")))}),format.raw/*98.10*/("""
						"""),format.raw/*99.7*/("""</td>
					</tr>
				</table>
				<table id="planes" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<tr>
						<td colspan="20">
							<b>PLAN DE MANTENCION</b>
						</td>
					</tr>
					<tr>
						<TH style="text-align:center">Plan</TH>
						<TH style="text-align:center">Fecha<br>Lectura</TH>
						<TH style="text-align:center">Unidad</TH>
						<TH style="text-align:center">Cantidad<br>Lectura</TH>
						<TH style="text-align:center">Rotación (Cada X Cantidad<br>Correspone Mantención)</TH>
						<TH style="text-align:center">Consumo Promedio<br>Mensual Estimado</TH>
						<TH style="text-align:center">Corresponde Próxima<br>Mantención a los</TH>
						<TH style="text-align:center">Fecha Estimada<br>Mantencion</TH>
					</tr>
					"""),_display_(/*118.7*/for(lista <- allPlan) yield /*118.28*/{_display_(Seq[Any](format.raw/*118.29*/("""
						"""),format.raw/*119.7*/("""<tr>
							<td style="text-align:center">"""),_display_(/*120.39*/lista/*120.44*/.tipoPlanNombre),format.raw/*120.59*/("""</td>
							<td style="text-align:center">
								<input type="hidden" class="fechaReset" value=""""),_display_(/*122.57*/lista/*122.62*/.fechaReset),format.raw/*122.73*/("""">
								"""),_display_(/*123.10*/lista/*123.15*/.fechaReset),format.raw/*123.26*/("""
							"""),format.raw/*124.8*/("""</td>
							<td style="text-align:center">"""),_display_(/*125.39*/lista/*125.44*/.unidadMantencion),format.raw/*125.61*/("""</td>
							<td style="text-align:right">
								<input type="hidden" class="estadoActual" value=""""),_display_(/*127.59*/lista/*127.64*/.estadoActual),format.raw/*127.77*/("""">
								"""),_display_(/*128.10*/lista/*128.15*/.estadoActual),format.raw/*128.28*/("""
							"""),format.raw/*129.8*/("""</td>
							<td style="text-align:center">"""),_display_(/*130.39*/lista/*130.44*/.cadaNEstimado),format.raw/*130.58*/("""</td>
							<td style="text-align:center">
								<input type="hidden" class="consumoEstimadoPorMes" value=""""),_display_(/*132.68*/lista/*132.73*/.consumoEstimadoPorMes),format.raw/*132.95*/("""">
								"""),_display_(/*133.10*/lista/*133.15*/.consumoEstimadoPorMes),format.raw/*133.37*/("""
							"""),format.raw/*134.8*/("""</td>
							<td style="text-align:right">
								<input type="hidden" class="proximaMantencion" value=""""),_display_(/*136.64*/lista/*136.69*/.proximaMantencion),format.raw/*136.87*/("""">
								"""),_display_(/*137.10*/lista/*137.15*/.proximaMantencion),format.raw/*137.33*/("""
							"""),format.raw/*138.8*/("""</td>
							<td style="text-align:center">
								<input type="date" class="fechaEstimada form-control center" disabled>
							</td>
						</tr>
					""")))}),format.raw/*143.7*/("""
				"""),format.raw/*144.5*/("""</table>
				
				<br>
				
				<table id="detalle" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead>
						<tr>
							<td colspan="12">
								<h5><b><font color="#008000"> REGISTRO DE GASTOS Y OTROS ASOCIADOS</font></b></h5>
							</td>
						</tr>
						<tr>
							<th style="text-align:center">Tipo<br>Mantención</th>
							<th style="text-align:center">Tipo<br>Plan</th>
							<th style="text-align:center">Fecha<br>Inicio</th>
							<th style="text-align:center">Tipo<br>Trabajo</th>
							<th style="text-align:center">Moneda<br></th>
							<th style="text-align:center">Costo<br>Neto</th>
							<th style="text-align:center">Prestador<br>Servicio</th>
							<th style="text-align:center">Número<br>Documento</th>
							<th style="text-align:center">Fecha<br>Documento</th>
							<th style="text-align:center">Archivo<br></th>
							<th style="text-align:center">Fecha<br>Termino</th>
						</tr>
					</thead>
					<tbody>
						"""),_display_(/*170.8*/for(det <- listDetalle) yield /*170.31*/{_display_(Seq[Any](format.raw/*170.32*/("""
							"""),format.raw/*171.8*/("""<tr>
								<td style="text-align:center">"""),_display_(/*172.40*/det/*172.43*/.tipoMantencionNombre),format.raw/*172.64*/("""</td>
								<td style="text-align:center">"""),_display_(/*173.40*/det/*173.43*/.tipoPlanNombre),format.raw/*173.58*/("""</td>
								<td style="text-align:center">"""),_display_(/*174.40*/utilities/*174.49*/.Fechas.DDMMAA(det.fechaInicio)),format.raw/*174.80*/("""</td>
								<td style="text-align:center">"""),_display_(/*175.40*/det/*175.43*/.tipoTrabajoNombre),format.raw/*175.61*/("""</td>
								<td style="text-align:center">"""),_display_(/*176.40*/det/*176.43*/.monedaNickName),format.raw/*176.58*/("""</td>
								<td style="text-align:right">"""),_display_(/*177.39*/det/*177.42*/.costoNeto),format.raw/*177.52*/("""</td>
								<td style="text-align:center">"""),_display_(/*178.40*/det/*178.43*/.proveedorNickName),format.raw/*178.61*/("""</td>
								<td style="text-align:right">"""),_display_(/*179.39*/det/*179.42*/.numeroDocumento),format.raw/*179.58*/("""</td>
								<td style="text-align:center">
									"""),_display_(if(det.fechaDocumento != null)/*181.41*/{_display_(Seq[Any](format.raw/*181.42*/("""
										"""),_display_(/*182.12*/utilities/*182.21*/.Fechas.DDMMAA(det.fechaDocumento)),format.raw/*182.55*/("""
									""")))} else {null} ),format.raw/*183.11*/("""
								"""),format.raw/*184.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(!det.documentoPDF.equals("0"))/*186.44*/{_display_(Seq[Any](format.raw/*186.45*/("""
										"""),format.raw/*187.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*187.45*/det/*187.48*/.documentoPDF),format.raw/*187.61*/("""')">
											<kbd style="background-color: #85C1E9">VER</kbd>
										</a>
									""")))}else/*190.15*/{_display_(Seq[Any](format.raw/*190.16*/("""
										"""),format.raw/*191.11*/("""---
									""")))}),format.raw/*192.11*/("""
								"""),format.raw/*193.9*/("""</td>
								<td style="text-align:center">"""),_display_(/*194.40*/utilities/*194.49*/.Fechas.DDMMAA(det.fechaTermino)),format.raw/*194.81*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left">Trabajo hecho:<br>.</TH>
								<td colspan="10" style="text-align:left">"""),_display_(/*198.51*/det/*198.54*/.trabajoHecho),format.raw/*198.67*/("""</td>
							</tr>
						""")))}),format.raw/*200.8*/("""
					"""),format.raw/*201.6*/("""</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'></h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="rutaPDF"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<form id="excel" class="formulario" method="post" action="/hojaVidaReportDetalleExcel/">
		<input type="hidden" name="id_equipo" value=""""),_display_(/*229.49*/allPlan/*229.56*/.get(0).id_equipo),format.raw/*229.73*/("""">
	</form>
	
""")))}),format.raw/*232.2*/("""




"""),format.raw/*237.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*238.31*/("""{"""),format.raw/*238.32*/("""
		"""),format.raw/*239.3*/("""calcFechaProxima();
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*241.2*/("""}"""),format.raw/*241.3*/(""");
	
	const calcFechaProxima = () => """),format.raw/*243.33*/("""{"""),format.raw/*243.34*/("""
	    """),format.raw/*244.6*/("""let filas = $(".estadoActual").length;
	    let hoy = new Date();
	
	    for (let i = 0; i < filas; i++) """),format.raw/*247.38*/("""{"""),format.raw/*247.39*/("""
	        """),format.raw/*248.10*/("""let fecha = $(".fechaReset")[i]?.value;
	        let proxima = parseFloat($(".proximaMantencion")[i]?.value.replace(/,/g, '') || 0);
	        let actual = parseFloat($(".estadoActual")[i]?.value.replace(/,/g, '') || 0);
	        let rotacion = parseFloat($(".consumoEstimadoPorMes")[i]?.value.replace(/,/g, '') || 0);
	
	        let dif = proxima - actual;
	
	        if (dif <= 0 || rotacion <= 0) """),format.raw/*255.41*/("""{"""),format.raw/*255.42*/("""
	            """),format.raw/*256.14*/("""$(".fechaEstimada")[i].value = fechaAAMMDD(hoy);
	        """),format.raw/*257.10*/("""}"""),format.raw/*257.11*/(""" """),format.raw/*257.12*/("""else """),format.raw/*257.17*/("""{"""),format.raw/*257.18*/("""
	            """),format.raw/*258.14*/("""let diasASumar = (dif / rotacion) * 30;
	            let auxFecha;
	            try """),format.raw/*260.18*/("""{"""),format.raw/*260.19*/("""
	                """),format.raw/*261.18*/("""let aux = fecha.split("-");
	                auxFecha = new Date(aux[2], parseInt(aux[1]) - 1, aux[0]);
	                auxFecha.setDate(auxFecha.getDate() + diasASumar);
	            """),format.raw/*264.14*/("""}"""),format.raw/*264.15*/(""" """),format.raw/*264.16*/("""catch (error) """),format.raw/*264.30*/("""{"""),format.raw/*264.31*/("""
	                """),format.raw/*265.18*/("""auxFecha = hoy;
	            """),format.raw/*266.14*/("""}"""),format.raw/*266.15*/("""
	
	            """),format.raw/*268.14*/("""$(".fechaEstimada")[i].value = fechaAAMMDD(auxFecha);
	        """),format.raw/*269.10*/("""}"""),format.raw/*269.11*/("""
	    """),format.raw/*270.6*/("""}"""),format.raw/*270.7*/("""
	"""),format.raw/*271.2*/("""}"""),format.raw/*271.3*/("""
	
	"""),format.raw/*273.2*/("""const mostrarPDF = (nombrePDF) => """),format.raw/*273.36*/("""{"""),format.raw/*273.37*/("""
		  """),format.raw/*274.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*276.2*/("""}"""),format.raw/*276.3*/("""
	
	
"""),format.raw/*279.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,allPlan:List[tables.PlanMantencion],listAtributos:List[String],listCompra:List[String],listDetalle:List[tables.HojaVida]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,allPlan,listAtributos,listCompra,listDetalle)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion],List[String],List[String],List[tables.HojaVida]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,allPlan,listAtributos,listCompra,listDetalle) => apply(mapDiccionario,mapPermiso,userMnu,allPlan,listAtributos,listCompra,listDetalle)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/hojaVidaReportDetalle.scala.html
                  HASH: 9314202742afeb61c6e767c55932f0fe0a7f3038
                  MATRIX: 1095->1|1414->227|1441->228|1549->309|1577->310|1618->324|1721->400|1750->401|1782->406|1825->423|1842->431|1882->433|1911->436|1980->484|2009->486|2086->537|2229->658|2259->661|3387->1762|3403->1769|3443->1788|3487->1805|3510->1819|3542->1830|3592->1853|3611->1863|3639->1870|3705->1909|3724->1919|3752->1926|3819->1966|3838->1976|3866->1983|3961->2051|3977->2058|4018->2078|4088->2121|4107->2131|4135->2138|4233->2209|4272->2210|4308->2219|4369->2253|4388->2263|4416->2270|4523->2358|4562->2359|4597->2367|4639->2379|4673->2386|4740->2426|4759->2436|4787->2443|4882->2511|4898->2518|4939->2538|5007->2579|5026->2589|5054->2596|5263->2779|5306->2806|5345->2807|5383->2818|5409->2823|5451->2834|5485->2841|6296->3625|6334->3646|6374->3647|6409->3654|6480->3697|6495->3702|6532->3717|6660->3817|6675->3822|6708->3833|6748->3845|6763->3850|6796->3861|6832->3869|6904->3913|6919->3918|6958->3935|7087->4036|7102->4041|7137->4054|7177->4066|7192->4071|7227->4084|7263->4092|7335->4136|7350->4141|7386->4155|7525->4266|7540->4271|7584->4293|7624->4305|7639->4310|7683->4332|7719->4340|7853->4446|7868->4451|7908->4469|7948->4481|7963->4486|8003->4504|8039->4512|8224->4666|8257->4671|9278->5665|9318->5688|9358->5689|9394->5697|9466->5741|9479->5744|9522->5765|9595->5810|9608->5813|9645->5828|9718->5873|9737->5882|9790->5913|9863->5958|9876->5961|9916->5979|9989->6024|10002->6027|10039->6042|10111->6086|10124->6089|10156->6099|10229->6144|10242->6147|10282->6165|10354->6209|10367->6212|10405->6228|10518->6313|10558->6314|10598->6326|10617->6335|10673->6369|10729->6380|10766->6389|10883->6478|10923->6479|10963->6490|11025->6524|11038->6527|11073->6540|11187->6634|11227->6635|11267->6646|11313->6660|11350->6669|11423->6714|11442->6723|11496->6755|11666->6897|11679->6900|11714->6913|11771->6939|11805->6945|12797->7909|12814->7916|12853->7933|12899->7948|12932->7953|13023->8015|13053->8016|13084->8019|13202->8109|13231->8110|13297->8147|13327->8148|13361->8154|13495->8259|13525->8260|13564->8270|13992->8669|14022->8670|14065->8684|14152->8742|14182->8743|14212->8744|14246->8749|14276->8750|14319->8764|14432->8848|14462->8849|14509->8867|14723->9052|14753->9053|14783->9054|14826->9068|14856->9069|14903->9087|14961->9116|14991->9117|15036->9133|15128->9196|15158->9197|15192->9203|15221->9204|15251->9206|15280->9207|15312->9211|15375->9245|15405->9246|15438->9251|15648->9433|15677->9434|15710->9439
                  LINES: 28->1|34->3|35->4|37->6|37->6|38->7|41->10|41->10|42->11|45->14|45->14|45->14|47->16|47->16|48->17|49->18|49->18|50->19|86->55|86->55|86->55|87->56|87->56|87->56|88->57|88->57|88->57|90->59|90->59|90->59|92->61|92->61|92->61|96->65|96->65|96->65|98->67|98->67|98->67|101->70|101->70|102->71|102->71|102->71|102->71|105->74|105->74|106->75|107->76|108->77|110->79|110->79|110->79|114->83|114->83|114->83|116->85|116->85|116->85|127->96|127->96|127->96|128->97|128->97|129->98|130->99|149->118|149->118|149->118|150->119|151->120|151->120|151->120|153->122|153->122|153->122|154->123|154->123|154->123|155->124|156->125|156->125|156->125|158->127|158->127|158->127|159->128|159->128|159->128|160->129|161->130|161->130|161->130|163->132|163->132|163->132|164->133|164->133|164->133|165->134|167->136|167->136|167->136|168->137|168->137|168->137|169->138|174->143|175->144|201->170|201->170|201->170|202->171|203->172|203->172|203->172|204->173|204->173|204->173|205->174|205->174|205->174|206->175|206->175|206->175|207->176|207->176|207->176|208->177|208->177|208->177|209->178|209->178|209->178|210->179|210->179|210->179|212->181|212->181|213->182|213->182|213->182|214->183|215->184|217->186|217->186|218->187|218->187|218->187|218->187|221->190|221->190|222->191|223->192|224->193|225->194|225->194|225->194|229->198|229->198|229->198|231->200|232->201|260->229|260->229|260->229|263->232|268->237|269->238|269->238|270->239|272->241|272->241|274->243|274->243|275->244|278->247|278->247|279->248|286->255|286->255|287->256|288->257|288->257|288->257|288->257|288->257|289->258|291->260|291->260|292->261|295->264|295->264|295->264|295->264|295->264|296->265|297->266|297->266|299->268|300->269|300->269|301->270|301->270|302->271|302->271|304->273|304->273|304->273|305->274|307->276|307->276|310->279
                  -- GENERATED --
              */
          