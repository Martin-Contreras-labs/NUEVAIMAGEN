
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

object reporteMovimientosDetalle extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template13[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String,String,String,Double,Double,Double,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], bodega: tables.BodegaEmpresa, esVenta: String, concepto: String, fechaDesde: String, fechaHasta: String,
usd: Double, eur: Double, uf: Double, id_proyecto: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	"""),_display_(/*10.3*/modalVerCotizacion()),format.raw/*10.23*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "DETALLE MOVIMIENTO (VALORIZADO) POR "+mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase,concepto)),format.raw/*13.154*/("""

				"""),format.raw/*15.5*/("""<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
							  		</div>
							  		<input type="text" class="form-control left"
										id="searchTermtablaPrincipal"
										onkeyup="doSearch5('tablaPrincipal');">
								</div>
							</td>
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
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
					        """),_display_(/*50.15*/for((nivel1,index1) <- lista.zipWithIndex) yield /*50.57*/ {_display_(Seq[Any](format.raw/*50.59*/("""
								"""),_display_(if(index1 < 6)/*51.24*/{_display_(Seq[Any](format.raw/*51.25*/("""
									"""),format.raw/*52.10*/("""<TR>
										"""),_display_(/*53.12*/for(nivel2 <- nivel1) yield /*53.33*/ {_display_(Seq[Any](format.raw/*53.35*/("""
											"""),format.raw/*54.12*/("""<td style="text-align: center; vertical-align: middle">"""),_display_(/*54.68*/nivel2),format.raw/*54.74*/("""</td>
										""")))}),format.raw/*55.12*/("""
									"""),format.raw/*56.10*/("""</TR>
								""")))} else {null} ),format.raw/*57.10*/("""
							""")))}),format.raw/*58.9*/("""
						"""),format.raw/*59.7*/("""</thead>
						<tbody>
							"""),_display_(/*61.9*/for((nivel1,index1) <- lista.zipWithIndex) yield /*61.51*/ {_display_(Seq[Any](format.raw/*61.53*/("""
								"""),_display_(if(index1 > 5)/*62.24*/{_display_(Seq[Any](format.raw/*62.25*/("""
									"""),format.raw/*63.10*/("""<TR>
										"""),_display_(/*64.12*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*64.55*/ {_display_(Seq[Any](format.raw/*64.57*/("""
											"""),_display_(if(index2 == 1)/*65.28*/{_display_(Seq[Any](format.raw/*65.29*/("""
												"""),_display_(if(nivel2.equals("0") || nivel2.equals(""))/*66.57*/{_display_(Seq[Any](format.raw/*66.58*/("""
													"""),format.raw/*67.14*/("""<td style="text-align: center;">--</td>
												""")))}else/*68.18*/{_display_(Seq[Any](format.raw/*68.19*/("""
													"""),format.raw/*69.14*/("""<td style="text-align: center;"><a href="#" onclick="buscaCotizacion('"""),_display_(/*69.85*/nivel2),format.raw/*69.91*/("""')">"""),_display_(/*69.96*/nivel2),format.raw/*69.102*/("""</td>
												""")))}),format.raw/*70.14*/("""
											""")))}else/*71.17*/{_display_(Seq[Any](format.raw/*71.18*/("""
												"""),_display_(if(index2 == 2 || index2 ==3)/*72.43*/{_display_(Seq[Any](format.raw/*72.44*/("""
													"""),format.raw/*73.14*/("""<td><a href="#" onclick="buscaEquipo('"""),_display_(/*73.53*/nivel1/*73.59*/.get(2)),format.raw/*73.66*/("""');">"""),_display_(/*73.72*/nivel2),format.raw/*73.78*/("""</a></td>
												""")))}else/*74.18*/{_display_(Seq[Any](format.raw/*74.19*/("""
													"""),format.raw/*75.14*/("""<td>"""),_display_(/*75.19*/nivel2),format.raw/*75.25*/("""</td>
												""")))}),format.raw/*76.14*/("""
											""")))}),format.raw/*77.13*/("""
										""")))}),format.raw/*78.12*/("""
									"""),format.raw/*79.10*/("""</TR>
								""")))} else {null} ),format.raw/*80.10*/("""
							 """)))}),format.raw/*81.10*/("""
						"""),format.raw/*82.7*/("""</tbody>
						

					</table>
				</div>
			</div>

	
	<form id="excel" class="formulario" method="post" action="/reporteMovimientosDetalleExcel/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*91.49*/bodega/*91.55*/.getId()),format.raw/*91.63*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*92.50*/fechaDesde),format.raw/*92.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*93.50*/fechaHasta),format.raw/*93.60*/("""">
		<input type="hidden" name="esVenta" value=""""),_display_(/*94.47*/esVenta),format.raw/*94.54*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*95.42*/uf),format.raw/*95.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*96.43*/usd),format.raw/*96.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*97.43*/eur),format.raw/*97.46*/("""">
	</form>


""")))}),format.raw/*101.2*/("""


"""),format.raw/*104.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*106.31*/("""{"""),format.raw/*106.32*/("""
		
		"""),format.raw/*108.3*/("""var tabla = document.getElementById("tablaPrincipal");
		
				
				for (var i = 0; i < tabla.rows.length; i++) """),format.raw/*111.49*/("""{"""),format.raw/*111.50*/("""
					"""),format.raw/*112.6*/("""tabla.rows[i].cells[11].style.backgroundColor="#eeeeee";
				"""),format.raw/*113.5*/("""}"""),format.raw/*113.6*/("""
				"""),format.raw/*114.5*/("""for (var i = 5; i < tabla.rows.length; i++) """),format.raw/*114.49*/("""{"""),format.raw/*114.50*/("""
					"""),format.raw/*115.6*/("""tabla.rows[i].cells[tabla.rows[0].cells.length-5].style.backgroundColor="#eeeeee";
				"""),format.raw/*116.5*/("""}"""),format.raw/*116.6*/("""
				"""),format.raw/*117.5*/("""for (var i = 5; i < tabla.rows.length; i++) """),format.raw/*117.49*/("""{"""),format.raw/*117.50*/("""

					"""),format.raw/*119.6*/("""tabla.rows[i].cells[0].style.textAlign="left";
					tabla.rows[i].cells[1].style.textAlign="center";
					tabla.rows[i].cells[2].style.textAlign="left";
					tabla.rows[i].cells[3].style.textAlign="left";
					tabla.rows[i].cells[4].style.textAlign="right";
					tabla.rows[i].cells[5].style.textAlign="right";
					tabla.rows[i].cells[6].style.textAlign="center";
					tabla.rows[i].cells[7].style.textAlign="right";
					tabla.rows[i].cells[8].style.textAlign="right";
					tabla.rows[i].cells[9].style.textAlign="right";
					tabla.rows[i].cells[10].style.textAlign="right";
					tabla.rows[i].cells[11].style.textAlign="right";

					tabla.rows[i].cells[0].style.minWidth="150px";
					tabla.rows[i].cells[2].style.minWidth="80px";
					tabla.rows[i].cells[3].style.minWidth="300px";
					tabla.rows[i].cells[7].style.minWidth="60px";
					tabla.rows[i].cells[8].style.minWidth="60px";
					tabla.rows[i].cells[9].style.minWidth="60px";
					tabla.rows[i].cells[10].style.minWidth="60px";
					tabla.rows[i].cells[11].style.minWidth="60px";

					for(var k=12;k<tabla.rows[0].cells.length;k++)"""),format.raw/*141.52*/("""{"""),format.raw/*141.53*/("""
						"""),format.raw/*142.7*/("""tabla.rows[i].cells[k].style.textAlign="right";
					"""),format.raw/*143.6*/("""}"""),format.raw/*143.7*/("""
				"""),format.raw/*144.5*/("""}"""),format.raw/*144.6*/("""
				"""),format.raw/*145.5*/("""for(var k=0;k<tabla.rows[0].cells.length;k++)"""),format.raw/*145.50*/("""{"""),format.raw/*145.51*/("""
					"""),format.raw/*146.6*/("""tabla.rows[tabla.rows.length-1].cells[k].style.backgroundColor="#eeeeee";
				"""),format.raw/*147.5*/("""}"""),format.raw/*147.6*/("""
				
				
				"""),format.raw/*150.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*150.36*/("""{"""),format.raw/*150.37*/("""
				    	"""),format.raw/*151.10*/(""""fixedHeader": true,
				    	"paging": false,
						"info": false,
						"searching": false,
						"order": false,
						columnDefs:["""),format.raw/*156.19*/("""{"""),format.raw/*156.20*/("""
				            """),format.raw/*157.17*/("""targets: "_all",
				            sortable: false
				        """),format.raw/*159.13*/("""}"""),format.raw/*159.14*/("""],
				    //	"order": [[ 3, "asc" ]],
						"scrollY": 550,
						"scrollX": true,
				    	"language": """),format.raw/*163.22*/("""{"""),format.raw/*163.23*/("""
				        	"""),format.raw/*164.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*165.13*/("""}"""),format.raw/*165.14*/("""
				"""),format.raw/*166.5*/("""}"""),format.raw/*166.6*/(""" """),format.raw/*166.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*169.2*/("""}"""),format.raw/*169.3*/(""");
	
	
	
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],bodega:tables.BodegaEmpresa,esVenta:String,concepto:String,fechaDesde:String,fechaHasta:String,usd:Double,eur:Double,uf:Double,id_proyecto:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,bodega,esVenta,concepto,fechaDesde,fechaHasta,usd,eur,uf,id_proyecto)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String,String,String,Double,Double,Double,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,bodega,esVenta,concepto,fechaDesde,fechaHasta,usd,eur,uf,id_proyecto) => apply(mapDiccionario,mapPermiso,userMnu,lista,bodega,esVenta,concepto,fechaDesde,fechaHasta,usd,eur,uf,id_proyecto)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteMovimientosDetalle.scala.html
                  HASH: ce539c77910f96cc8b8fa1dcda073ce934254354
                  MATRIX: 1122->1|1503->289|1535->296|1551->304|1590->306|1619->310|1687->358|1717->363|1761->387|1790->390|1831->410|1862->414|1939->465|2111->615|2144->621|3436->1886|3494->1928|3534->1930|3585->1954|3624->1955|3662->1965|3705->1981|3742->2002|3782->2004|3822->2016|3905->2072|3932->2078|3980->2095|4018->2105|4077->2120|4116->2129|4150->2136|4207->2167|4265->2209|4305->2211|4356->2235|4395->2236|4433->2246|4476->2262|4535->2305|4575->2307|4630->2335|4669->2336|4753->2393|4792->2394|4834->2408|4910->2465|4949->2466|4991->2480|5089->2551|5116->2557|5148->2562|5176->2568|5226->2587|5262->2604|5301->2605|5371->2648|5410->2649|5452->2663|5518->2702|5533->2708|5561->2715|5594->2721|5621->2727|5667->2754|5706->2755|5748->2769|5780->2774|5807->2780|5857->2799|5901->2812|5944->2824|5982->2834|6041->2849|6082->2859|6116->2866|6340->3063|6355->3069|6384->3077|6463->3129|6494->3139|6573->3191|6604->3201|6680->3250|6708->3257|6779->3301|6802->3303|6874->3348|6898->3351|6970->3396|6994->3399|7040->3414|7071->3417|7163->3480|7193->3481|7227->3487|7367->3598|7397->3599|7431->3605|7520->3666|7549->3667|7582->3672|7655->3716|7685->3717|7719->3723|7834->3810|7863->3811|7896->3816|7969->3860|7999->3861|8034->3868|9160->4965|9190->4966|9225->4973|9306->5026|9335->5027|9368->5032|9397->5033|9430->5038|9504->5083|9534->5084|9568->5090|9674->5168|9703->5169|9746->5184|9806->5215|9836->5216|9875->5226|10038->5360|10068->5361|10114->5378|10204->5439|10234->5440|10368->5545|10398->5546|10441->5560|10550->5640|10580->5641|10613->5646|10642->5647|10671->5648|10772->5721|10801->5722
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|46->15|81->50|81->50|81->50|82->51|82->51|83->52|84->53|84->53|84->53|85->54|85->54|85->54|86->55|87->56|88->57|89->58|90->59|92->61|92->61|92->61|93->62|93->62|94->63|95->64|95->64|95->64|96->65|96->65|97->66|97->66|98->67|99->68|99->68|100->69|100->69|100->69|100->69|100->69|101->70|102->71|102->71|103->72|103->72|104->73|104->73|104->73|104->73|104->73|104->73|105->74|105->74|106->75|106->75|106->75|107->76|108->77|109->78|110->79|111->80|112->81|113->82|122->91|122->91|122->91|123->92|123->92|124->93|124->93|125->94|125->94|126->95|126->95|127->96|127->96|128->97|128->97|132->101|135->104|137->106|137->106|139->108|142->111|142->111|143->112|144->113|144->113|145->114|145->114|145->114|146->115|147->116|147->116|148->117|148->117|148->117|150->119|172->141|172->141|173->142|174->143|174->143|175->144|175->144|176->145|176->145|176->145|177->146|178->147|178->147|181->150|181->150|181->150|182->151|187->156|187->156|188->157|190->159|190->159|194->163|194->163|195->164|196->165|196->165|197->166|197->166|197->166|200->169|200->169
                  -- GENERATED --
              */
          