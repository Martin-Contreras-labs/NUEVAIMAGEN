
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

object reporteMovimientosDetallePorProyecto extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template13[Map[String,String],Map[String,String],utilities.UserMnu,List[List[List[String]]],tables.Proyecto,String,String,String,String,Double,Double,Double,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[List[String]]], proyecto: tables.Proyecto, esVenta: String, concepto: String, fechaDesde: String, fechaHasta: String,
usd: Double, eur: Double, uf: Double, id_proyecto: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	"""),_display_(/*10.3*/modalVerCotizacion()),format.raw/*10.23*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "DETALLE MOVIMIENTO POR PROYECTO: "+proyecto.getNickName().toUpperCase,concepto)),format.raw/*13.112*/("""

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
				
				<hr size="1px" color="black" />
				"""),_display_(/*48.6*/for((lista1,index) <- lista.zipWithIndex) yield /*48.47*/ {_display_(Seq[Any](format.raw/*48.49*/("""
					"""),format.raw/*49.6*/("""<div class="table-responsive">
						<table id="tablaPrincipal_"""),_display_(/*50.34*/index),format.raw/*50.39*/("""" class="table table-sm table-hover table-bordered table-condensed table-fluid">
							<thead style="background-color: #eeeeee">
						        """),_display_(/*52.16*/for((nivel1,index1) <- lista1.zipWithIndex) yield /*52.59*/ {_display_(Seq[Any](format.raw/*52.61*/("""
									"""),_display_(if(index1 < 5)/*53.25*/{_display_(Seq[Any](format.raw/*53.26*/("""
										"""),format.raw/*54.11*/("""<TR>
											"""),_display_(/*55.13*/for(nivel2 <- nivel1) yield /*55.34*/ {_display_(Seq[Any](format.raw/*55.36*/("""
												"""),format.raw/*56.13*/("""<th style="text-align: center; vertical-align: middle">"""),_display_(/*56.69*/nivel2),format.raw/*56.75*/("""</th>
											""")))}),format.raw/*57.13*/("""
										"""),format.raw/*58.11*/("""</TR>
									""")))} else {null} ),format.raw/*59.11*/("""
								""")))}),format.raw/*60.10*/("""
							"""),format.raw/*61.8*/("""</thead>
							<tbody>
								"""),_display_(/*63.10*/for((nivel1,index1) <- lista1.zipWithIndex) yield /*63.53*/ {_display_(Seq[Any](format.raw/*63.55*/("""
									"""),_display_(if(index1 > 4)/*64.25*/{_display_(Seq[Any](format.raw/*64.26*/("""
										"""),format.raw/*65.11*/("""<TR>
											"""),_display_(/*66.13*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*66.56*/ {_display_(Seq[Any](format.raw/*66.58*/("""
												"""),_display_(if(index2 == 1)/*67.29*/{_display_(Seq[Any](format.raw/*67.30*/("""
													"""),_display_(if(nivel2.equals("0") || nivel2.equals(""))/*68.58*/{_display_(Seq[Any](format.raw/*68.59*/("""
														"""),format.raw/*69.15*/("""<td style="text-align: center;">--</td>
													""")))}else/*70.19*/{_display_(Seq[Any](format.raw/*70.20*/("""
														"""),format.raw/*71.15*/("""<td style="text-align: center;"><a href="#" onclick="buscaCotizacion('"""),_display_(/*71.86*/nivel2),format.raw/*71.92*/("""')">"""),_display_(/*71.97*/nivel2),format.raw/*71.103*/("""</td>
													""")))}),format.raw/*72.15*/("""
												""")))}else/*73.18*/{_display_(Seq[Any](format.raw/*73.19*/("""
													"""),_display_(if(index2 == 2 || index2 ==3)/*74.44*/{_display_(Seq[Any](format.raw/*74.45*/("""
														"""),format.raw/*75.15*/("""<td><a href="#" onclick="buscaEquipo('"""),_display_(/*75.54*/nivel1/*75.60*/.get(2)),format.raw/*75.67*/("""');">"""),_display_(/*75.73*/nivel2),format.raw/*75.79*/("""</a></td>
													""")))}else/*76.19*/{_display_(Seq[Any](format.raw/*76.20*/("""
														"""),format.raw/*77.15*/("""<td>"""),_display_(/*77.20*/nivel2),format.raw/*77.26*/("""</td>
													""")))}),format.raw/*78.15*/("""
												""")))}),format.raw/*79.14*/("""
											""")))}),format.raw/*80.13*/("""
										"""),format.raw/*81.11*/("""</TR>
									""")))} else {null} ),format.raw/*82.11*/("""
								 """)))}),format.raw/*83.11*/("""
							"""),format.raw/*84.8*/("""</tbody>
						</table>
					</div>
					<hr size="1px" color="black" />
				""")))}),format.raw/*88.6*/("""
				
			"""),format.raw/*90.4*/("""</div>

	
	<form id="excel" class="formulario" method="post" action="/reporteMovimientosDetalleExcel/">
		<input type="hidden" name="id_proyecto" value=""""),_display_(/*94.51*/proyecto/*94.59*/.getId()),format.raw/*94.67*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*95.50*/fechaDesde),format.raw/*95.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*96.50*/fechaHasta),format.raw/*96.60*/("""">
		<input type="hidden" name="esVenta" value=""""),_display_(/*97.47*/esVenta),format.raw/*97.54*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*98.42*/uf),format.raw/*98.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*99.43*/usd),format.raw/*99.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*100.43*/eur),format.raw/*100.46*/("""">
	</form>


""")))}),format.raw/*104.2*/("""


"""),format.raw/*107.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*109.31*/("""{"""),format.raw/*109.32*/("""
		
		"""),_display_(/*111.4*/for((lista1,index) <- lista.zipWithIndex) yield /*111.45*/ {_display_(Seq[Any](format.raw/*111.47*/("""
			"""),format.raw/*112.4*/("""var tabla = document.getElementById("tablaPrincipal_"""),_display_(/*112.57*/index),format.raw/*112.62*/("""");
				for (var i = 0; i < tabla.rows.length; i++) """),format.raw/*113.49*/("""{"""),format.raw/*113.50*/(""" 	
					"""),format.raw/*114.6*/("""tabla.rows[i].cells[11].style.backgroundColor="#eeeeee";
				"""),format.raw/*115.5*/("""}"""),format.raw/*115.6*/("""
				"""),format.raw/*116.5*/("""for (var i = 5; i < tabla.rows.length; i++) """),format.raw/*116.49*/("""{"""),format.raw/*116.50*/(""" 
					"""),format.raw/*117.6*/("""tabla.rows[i].cells[tabla.rows[0].cells.length-5].style.backgroundColor="#eeeeee";
				"""),format.raw/*118.5*/("""}"""),format.raw/*118.6*/("""
				"""),format.raw/*119.5*/("""for (var i = 5; i < tabla.rows.length; i++) """),format.raw/*119.49*/("""{"""),format.raw/*119.50*/("""
					
					"""),format.raw/*121.6*/("""tabla.rows[i].cells[0].style.textAlign="left";
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
					
					for(var k=12;k<tabla.rows[0].cells.length -3;k++)"""),format.raw/*143.55*/("""{"""),format.raw/*143.56*/("""
						"""),format.raw/*144.7*/("""tabla.rows[i].cells[k].style.textAlign="right";
					"""),format.raw/*145.6*/("""}"""),format.raw/*145.7*/("""
				"""),format.raw/*146.5*/("""}"""),format.raw/*146.6*/("""
				"""),format.raw/*147.5*/("""for(var k=0;k<tabla.rows[0].cells.length-3;k++)"""),format.raw/*147.52*/("""{"""),format.raw/*147.53*/("""
					"""),format.raw/*148.6*/("""tabla.rows[tabla.rows.length-1].cells[k].style.backgroundColor="#eeeeee";
				"""),format.raw/*149.5*/("""}"""),format.raw/*149.6*/("""
				
				
		""")))}),format.raw/*152.4*/("""
		

			"""),format.raw/*155.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*156.2*/("""}"""),format.raw/*156.3*/(""");
	
	
	
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[List[String]]],proyecto:tables.Proyecto,esVenta:String,concepto:String,fechaDesde:String,fechaHasta:String,usd:Double,eur:Double,uf:Double,id_proyecto:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,proyecto,esVenta,concepto,fechaDesde,fechaHasta,usd,eur,uf,id_proyecto)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[List[String]]],tables.Proyecto,String,String,String,String,Double,Double,Double,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,proyecto,esVenta,concepto,fechaDesde,fechaHasta,usd,eur,uf,id_proyecto) => apply(mapDiccionario,mapPermiso,userMnu,lista,proyecto,esVenta,concepto,fechaDesde,fechaHasta,usd,eur,uf,id_proyecto)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteMovimientosDetallePorProyecto.scala.html
                  HASH: 055cb97fbbfef49beca3d65c7fb47aebb77de3c0
                  MATRIX: 1134->1|1518->292|1550->299|1566->307|1605->309|1634->313|1702->361|1732->366|1776->390|1805->393|1846->413|1877->417|1954->468|2084->576|2117->582|3241->1680|3298->1721|3338->1723|3371->1729|3462->1793|3488->1798|3660->1943|3719->1986|3759->1988|3811->2013|3850->2014|3889->2025|3933->2042|3970->2063|4010->2065|4051->2078|4134->2134|4161->2140|4210->2158|4249->2169|4309->2185|4350->2195|4385->2203|4445->2236|4504->2279|4544->2281|4596->2306|4635->2307|4674->2318|4718->2335|4777->2378|4817->2380|4873->2409|4912->2410|4997->2468|5036->2469|5079->2484|5156->2542|5195->2543|5238->2558|5336->2629|5363->2635|5395->2640|5423->2646|5474->2666|5511->2684|5550->2685|5621->2729|5660->2730|5703->2745|5769->2784|5784->2790|5812->2797|5845->2803|5872->2809|5919->2837|5958->2838|6001->2853|6033->2858|6060->2864|6111->2884|6156->2898|6200->2911|6239->2922|6299->2938|6341->2949|6376->2957|6484->3035|6520->3044|6701->3198|6718->3206|6747->3214|6826->3266|6857->3276|6936->3328|6967->3338|7043->3387|7071->3394|7142->3438|7165->3440|7237->3485|7261->3488|7334->3533|7359->3536|7405->3551|7436->3554|7528->3617|7558->3618|7592->3625|7650->3666|7691->3668|7723->3672|7804->3725|7831->3730|7912->3782|7942->3783|7978->3791|8067->3852|8096->3853|8129->3858|8202->3902|8232->3903|8267->3910|8382->3997|8411->3998|8444->4003|8517->4047|8547->4048|8587->4060|9726->5170|9756->5171|9791->5178|9872->5231|9901->5232|9934->5237|9963->5238|9996->5243|10072->5290|10102->5291|10136->5297|10242->5375|10271->5376|10316->5390|10352->5398|10446->5464|10475->5465
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|46->15|79->48|79->48|79->48|80->49|81->50|81->50|83->52|83->52|83->52|84->53|84->53|85->54|86->55|86->55|86->55|87->56|87->56|87->56|88->57|89->58|90->59|91->60|92->61|94->63|94->63|94->63|95->64|95->64|96->65|97->66|97->66|97->66|98->67|98->67|99->68|99->68|100->69|101->70|101->70|102->71|102->71|102->71|102->71|102->71|103->72|104->73|104->73|105->74|105->74|106->75|106->75|106->75|106->75|106->75|106->75|107->76|107->76|108->77|108->77|108->77|109->78|110->79|111->80|112->81|113->82|114->83|115->84|119->88|121->90|125->94|125->94|125->94|126->95|126->95|127->96|127->96|128->97|128->97|129->98|129->98|130->99|130->99|131->100|131->100|135->104|138->107|140->109|140->109|142->111|142->111|142->111|143->112|143->112|143->112|144->113|144->113|145->114|146->115|146->115|147->116|147->116|147->116|148->117|149->118|149->118|150->119|150->119|150->119|152->121|174->143|174->143|175->144|176->145|176->145|177->146|177->146|178->147|178->147|178->147|179->148|180->149|180->149|183->152|186->155|187->156|187->156
                  -- GENERATED --
              */
          