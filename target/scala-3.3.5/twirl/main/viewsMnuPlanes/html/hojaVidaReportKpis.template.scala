
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

object hojaVidaReportKpis extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],Map[String,String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
detalle: List[List[String]], mapUbicacion: Map[String,String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "REPORTE KPIs MANTENCIONES","")),format.raw/*9.63*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
				<form id="excel" class="formulario" method="post" action="/hojaVidaReportKpisExcel/"></form>
				
				<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td>
								<div align="center">
									<button type="button" class="btn btn-sm btn-success" onclick="window.print(); return false;">
										Imprimir
									</button>
									<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
										Exportar a Excel
									</button>
									<button type="button"  class="btn btn-sm btn-success" 
										onclick="location.href='/home/';">
										Volver
									</button>
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<th>EQUIPO</th>
							<th style="background-color:#BDBDBD;"></th>
							<th colspan="6">DIAS DE EQUIPO NO OPERATIVOS</th>
							<th style="background-color:#BDBDBD;"></th>
							<th title='Compara costo mantención con potencial de venta (solo días operativo)' colspan="6">COSTO vs VENTA MAXIMA (PLista X Días Operativos)</th>
							<th style="background-color:#BDBDBD;"></th>
							<th title='Compara costo mantención con potencial de venta (por el total de días)'colspan="6">COSTO vs VENTA ESPERADA (PLista X Total Días)</th>
							<th style="background-color:#BDBDBD;"></th>
							<th title='Compara costo mantención con venta real en los días arrendados'colspan="6">COSTO vs VENTA REAL (Venta real período)</th>
							<th style="background-color:#BDBDBD;"></th>
						</tr>
					</thead>
					<tbody>
						"""),_display_(/*52.8*/for(lista <- detalle) yield /*52.29*/{_display_(Seq[Any](format.raw/*52.30*/("""
							"""),format.raw/*53.8*/("""<tr>
							"""),_display_(if(lista.get(1)=="")/*54.29*/{_display_(Seq[Any](format.raw/*54.30*/("""
								"""),_display_(/*55.10*/for(lista2 <- lista.tail) yield /*55.35*/{_display_(Seq[Any](format.raw/*55.36*/("""
									"""),_display_(if(lista2=="    ")/*56.29*/{_display_(Seq[Any](format.raw/*56.30*/("""
										"""),format.raw/*57.11*/("""<td style="background-color:#BDBDBD;">"""),_display_(/*57.50*/lista2),format.raw/*57.56*/("""</td>
									""")))}else/*58.15*/{_display_(Seq[Any](format.raw/*58.16*/("""
										"""),format.raw/*59.11*/("""<td>"""),_display_(/*59.16*/lista2),format.raw/*59.22*/("""</td>
									""")))}),format.raw/*60.11*/("""
								""")))}),format.raw/*61.10*/("""
							""")))}else/*62.13*/{_display_(Seq[Any](format.raw/*62.14*/("""
								"""),format.raw/*63.9*/("""<td style="background-color:#BDBDBD;">"""),_display_(/*63.48*/lista/*63.53*/.get(1)),format.raw/*63.60*/("""</td>
								<td colspan="9" style="background-color:#BDBDBD;">"""),_display_(/*64.60*/lista/*64.65*/.get(2)),format.raw/*64.72*/("""</td>
								<td colspan="20" style="background-color:#BDBDBD;">
									Ubicacion actual: """),_display_(if(mapUbicacion.get(lista.get(0))!=null)/*66.69*/ {_display_(Seq[Any](format.raw/*66.71*/(""" """),_display_(/*66.73*/mapUbicacion/*66.85*/.get(lista.get(0))),format.raw/*66.103*/(""" """)))} else {null} ),format.raw/*66.105*/("""
								"""),format.raw/*67.9*/("""</td>
							""")))}),format.raw/*68.9*/("""
							"""),format.raw/*69.8*/("""</tr>
						""")))}),format.raw/*70.8*/("""
					
					"""),format.raw/*72.6*/("""</tbody>
				</table>
			</div>
		</div>	
	</div>
	
""")))}),format.raw/*78.2*/("""




"""),format.raw/*83.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*84.31*/("""{"""),format.raw/*84.32*/("""
		  
		  """),format.raw/*86.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/(""");
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,detalle:List[List[String]],mapUbicacion:Map[String,String]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,detalle,mapUbicacion)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],Map[String,String]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,detalle,mapUbicacion) => apply(mapDiccionario,mapPermiso,userMnu,detalle,mapUbicacion)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/hojaVidaReportKpis.scala.html
                  HASH: 97e4fc5ea7736bbbfbb96665c317418e30f277b9
                  MATRIX: 1054->1|1307->161|1340->169|1356->177|1395->179|1423->182|1491->230|1519->232|1595->283|1674->342|1704->345|3593->2208|3630->2229|3669->2230|3704->2238|3764->2271|3803->2272|3840->2282|3881->2307|3920->2308|3976->2337|4015->2338|4054->2349|4120->2388|4147->2394|4186->2414|4225->2415|4264->2426|4296->2431|4323->2437|4370->2453|4411->2463|4443->2476|4482->2477|4518->2486|4584->2525|4598->2530|4626->2537|4718->2602|4732->2607|4760->2614|4921->2748|4961->2750|4990->2752|5011->2764|5051->2782|5098->2784|5134->2793|5178->2807|5213->2815|5256->2828|5295->2840|5378->2893|5410->2898|5500->2960|5529->2961|5566->2971|5659->3037|5687->3038
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|83->52|83->52|83->52|84->53|85->54|85->54|86->55|86->55|86->55|87->56|87->56|88->57|88->57|88->57|89->58|89->58|90->59|90->59|90->59|91->60|92->61|93->62|93->62|94->63|94->63|94->63|94->63|95->64|95->64|95->64|97->66|97->66|97->66|97->66|97->66|97->66|98->67|99->68|100->69|101->70|103->72|109->78|114->83|115->84|115->84|117->86|118->87|118->87
                  -- GENERATED --
              */
          