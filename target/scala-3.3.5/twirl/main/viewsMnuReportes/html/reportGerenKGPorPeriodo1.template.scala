
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

object reportGerenKGPorPeriodo1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[String],List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
cabecera1: List[String], cabecera2: List[String], datos: List[List[String]], desde: String, hasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "TONELADAS MOVIDAS POR PERIODO","(Corresponde a toneladas movidas desde y hacia "+mapDiccionario("BODEGAS")+" externas/clientes)")),format.raw/*9.162*/("""

		"""),format.raw/*11.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td>
						NOTA: 
						<br>Si traslada desde """),_display_(/*16.30*/mapDiccionario("Bodega")),format.raw/*16.54*/(""" """),format.raw/*16.55*/("""interna a """),_display_(/*16.66*/mapDiccionario("Bodega")),format.raw/*16.90*/(""" """),format.raw/*16.91*/("""cliente, 
						queda registrado como una SALIDA en la sucursal de """),_display_(/*17.59*/mapDiccionario("Bodega")),format.raw/*17.83*/(""" """),format.raw/*17.84*/("""cliente.
						<br>Si el traslado es a la inversa queda registrado como una ENTRADA en la sucursal de """),_display_(/*18.95*/mapDiccionario("Bodega")),format.raw/*18.119*/(""" """),format.raw/*18.120*/("""cliente.
					</td>
				</tr>
				<tr>
					<td>
						<br>
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
				<thead>
					<tr>
						<th colspan="50"><h5>TONELADAS MOVIDAS: PERIODO DE """),_display_(/*46.59*/utilities/*46.68*/.Fechas.DDMMAA(desde)),format.raw/*46.89*/(""" """),format.raw/*46.90*/("""A """),_display_(/*46.93*/utilities/*46.102*/.Fechas.DDMMAA(hasta)),format.raw/*46.123*/("""</h5></th>
					</tr>
					<tr>
						"""),_display_(/*49.8*/for((lista,index) <- cabecera1.zipWithIndex) yield /*49.52*/{_display_(Seq[Any](format.raw/*49.53*/("""
							"""),_display_(if(index == 0)/*50.23*/{_display_(Seq[Any](format.raw/*50.24*/("""
								"""),format.raw/*51.9*/("""<th style="text-align:center">"""),_display_(/*51.40*/lista),format.raw/*51.45*/("""</th>
							""")))}else/*52.13*/{_display_(Seq[Any](format.raw/*52.14*/("""
								"""),format.raw/*53.9*/("""<th colspan="2" style="text-align:center">"""),_display_(/*53.52*/lista),format.raw/*53.57*/("""</th>
							""")))}),format.raw/*54.9*/("""
						""")))}),format.raw/*55.8*/("""
					"""),format.raw/*56.6*/("""</tr>
					<tr>
						"""),_display_(/*58.8*/for((lista,index) <- cabecera2.zipWithIndex) yield /*58.52*/{_display_(Seq[Any](format.raw/*58.53*/("""
							"""),format.raw/*59.8*/("""<th style="text-align:center">"""),_display_(/*59.39*/lista),format.raw/*59.44*/("""</th>
						""")))}),format.raw/*60.8*/("""
					"""),format.raw/*61.6*/("""</tr>
				</thead>
				<tbody>
					"""),_display_(/*64.7*/for(lista <- datos) yield /*64.26*/{_display_(Seq[Any](format.raw/*64.27*/("""
						"""),format.raw/*65.7*/("""<tr>
							"""),_display_(/*66.9*/for((lista2,index2) <- lista.zipWithIndex) yield /*66.51*/{_display_(Seq[Any](format.raw/*66.52*/("""
								"""),_display_(if(index2 == 0)/*67.25*/{_display_(Seq[Any](format.raw/*67.26*/("""
									"""),format.raw/*68.10*/("""<td style="text-align:left">"""),_display_(/*68.39*/lista2),format.raw/*68.45*/("""</td>
								""")))}else/*69.14*/{_display_(Seq[Any](format.raw/*69.15*/("""
									"""),format.raw/*70.10*/("""<td style="text-align:right">"""),_display_(/*70.40*/lista2),format.raw/*70.46*/("""</td>
								""")))}),format.raw/*71.10*/("""
							""")))}),format.raw/*72.9*/("""
						"""),format.raw/*73.7*/("""</tr>
					""")))}),format.raw/*74.7*/("""
				"""),format.raw/*75.5*/("""</tbody>
				<tfoot>
					"""),_display_(/*77.7*/for((lista,index) <- cabecera2.zipWithIndex) yield /*77.51*/{_display_(Seq[Any](format.raw/*77.52*/("""
						"""),_display_(if(index == 0)/*78.22*/{_display_(Seq[Any](format.raw/*78.23*/("""
							"""),format.raw/*79.8*/("""<th style="text-align:left">TOTALES</th>
						""")))}else/*80.12*/{_display_(Seq[Any](format.raw/*80.13*/("""
							"""),format.raw/*81.8*/("""<th style="text-align:right"></th>
						""")))}),format.raw/*82.8*/("""
					""")))}),format.raw/*83.7*/("""
				"""),format.raw/*84.5*/("""</tfoot>
			</table>
		</div>
	
	<form id="excel" class="formulario" method="post" action="/routes2/reportGerenKGPorPeriodo1Excel/">
		<input type="hidden" name="desde" value=""""),_display_(/*89.45*/desde),format.raw/*89.50*/("""">
		<input type="hidden" name="hasta" value=""""),_display_(/*90.45*/hasta),format.raw/*90.50*/("""">
	</form>


""")))}),format.raw/*94.2*/("""


"""),format.raw/*97.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*99.31*/("""{"""),format.raw/*99.32*/("""
		
		"""),format.raw/*101.3*/("""var tablaPrincipal = document.getElementById('tablaPrincipal');
		  
		for (var i=1; i<tablaPrincipal.rows[2].cells.length; i++)"""),format.raw/*103.60*/("""{"""),format.raw/*103.61*/("""
			"""),format.raw/*104.4*/("""let suma = 0;
		  	for (var j=3; j<tablaPrincipal.rows.length-1; j++)"""),format.raw/*105.56*/("""{"""),format.raw/*105.57*/("""
				"""),format.raw/*106.5*/("""let valor = tablaPrincipal.rows[j].cells[i].innerHTML;
				suma += parseFloat(valor);
		  	"""),format.raw/*108.6*/("""}"""),format.raw/*108.7*/("""
			"""),format.raw/*109.4*/("""tablaPrincipal.rows[tablaPrincipal.rows.length-1].cells[i].innerHTML = formatStandar2(suma);
		"""),format.raw/*110.3*/("""}"""),format.raw/*110.4*/("""
	
	  	"""),format.raw/*112.5*/("""document.getElementById('mostrarmostrar').style.display="block";

	"""),format.raw/*114.2*/("""}"""),format.raw/*114.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,cabecera1:List[String],cabecera2:List[String],datos:List[List[String]],desde:String,hasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,cabecera1,cabecera2,datos,desde,hasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[String],List[String],List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,cabecera1,cabecera2,datos,desde,hasta) => apply(mapDiccionario,mapPermiso,userMnu,cabecera1,cabecera2,datos,desde,hasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportGerenKGPorPeriodo1.scala.html
                  HASH: e40c7260a193c7e0074988075434f32ec82c3196
                  MATRIX: 1083->1|1380->205|1413->213|1429->221|1468->223|1497->227|1565->275|1593->277|1669->328|1848->486|1879->490|2051->635|2096->659|2125->660|2163->671|2208->695|2237->696|2332->764|2377->788|2406->789|2536->892|2582->916|2612->917|3462->1740|3480->1749|3522->1770|3551->1771|3581->1774|3600->1783|3643->1804|3708->1843|3768->1887|3807->1888|3857->1911|3896->1912|3932->1921|3990->1952|4016->1957|4053->1975|4092->1976|4128->1985|4198->2028|4224->2033|4268->2047|4306->2055|4339->2061|4388->2084|4448->2128|4487->2129|4522->2137|4580->2168|4606->2173|4649->2186|4682->2192|4745->2229|4780->2248|4819->2249|4853->2256|4892->2269|4950->2311|4989->2312|5041->2337|5080->2338|5118->2348|5174->2377|5201->2383|5239->2402|5278->2403|5316->2413|5373->2443|5400->2449|5446->2464|5485->2473|5519->2480|5561->2492|5593->2497|5646->2524|5706->2568|5745->2569|5794->2591|5833->2592|5868->2600|5939->2652|5978->2653|6013->2661|6085->2703|6122->2710|6154->2715|6358->2892|6384->2897|6458->2944|6484->2949|6529->2964|6559->2967|6650->3030|6679->3031|6713->3037|6870->3165|6900->3166|6932->3170|7030->3239|7060->3240|7093->3245|7212->3336|7241->3337|7273->3341|7396->3436|7425->3437|7460->3444|7555->3511|7584->3512
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|42->11|47->16|47->16|47->16|47->16|47->16|47->16|48->17|48->17|48->17|49->18|49->18|49->18|77->46|77->46|77->46|77->46|77->46|77->46|77->46|80->49|80->49|80->49|81->50|81->50|82->51|82->51|82->51|83->52|83->52|84->53|84->53|84->53|85->54|86->55|87->56|89->58|89->58|89->58|90->59|90->59|90->59|91->60|92->61|95->64|95->64|95->64|96->65|97->66|97->66|97->66|98->67|98->67|99->68|99->68|99->68|100->69|100->69|101->70|101->70|101->70|102->71|103->72|104->73|105->74|106->75|108->77|108->77|108->77|109->78|109->78|110->79|111->80|111->80|112->81|113->82|114->83|115->84|120->89|120->89|121->90|121->90|125->94|128->97|130->99|130->99|132->101|134->103|134->103|135->104|136->105|136->105|137->106|139->108|139->108|140->109|141->110|141->110|143->112|145->114|145->114
                  -- GENERATED --
              */
          