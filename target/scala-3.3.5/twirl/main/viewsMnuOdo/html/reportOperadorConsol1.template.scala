
package viewsMnuOdo.html

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

object reportOperadorConsol1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, plantilla: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "CONSOLIDADO POR OPERADORES","(SOLO CANTIDADES)")),format.raw/*8.81*/("""
			"""),format.raw/*9.4*/("""<form action="/reportOperadorConsol1Excel/" method="POST">
				<input type="hidden" name="fechaDesde" value=""""),_display_(/*10.52*/fechaDesde),format.raw/*10.62*/("""">
				<input type="hidden" name="fechaHasta" value=""""),_display_(/*11.52*/fechaHasta),format.raw/*11.62*/("""">
				
				<div class="container ocultar" style="max-width:99.9%;text-align:center ">
					<button type="button" class="btn btn-info btn-sm" onclick="window.print()">Imprimir</button>
					<button type="submit" class="btn btn-info btn-sm">Exportar a Excel</button>
				</div>
			</form>
			
			
			
			<table id="detalleKardex" class="table table-sm table-hover table-bordered table-condensed table-fluid" width="100%">
				<thead style="background-color: #eeeeee">
				
						<tr>
							<TH style="text-align: center">"""),_display_(/*25.40*/plantilla/*25.49*/.get(0).get(0)),format.raw/*25.63*/("""</TH>
							<TH style="text-align: center">"""),_display_(/*26.40*/plantilla/*26.49*/.get(0).get(1)),format.raw/*26.63*/("""</TH>
							"""),_display_(/*27.9*/for(lista2 <- plantilla.get(0).tail.tail) yield /*27.50*/{_display_(Seq[Any](format.raw/*27.51*/("""
								"""),format.raw/*28.9*/("""<th style="text-align:center; white-space: nowrap;vertical-align: middle">
									<div style="display: flex;flex-direction: row;">
										<span style="writing-mode: vertical-lr;transform: rotate(180deg);width: 23px;">
											"""),_display_(/*31.13*/lista2),format.raw/*31.19*/("""
										"""),format.raw/*32.11*/("""</span>
									</div>
								</th>
							""")))}),format.raw/*35.9*/("""
						"""),format.raw/*36.7*/("""</tr>
				
				</thead>
				
				<tbody >
					"""),_display_(/*41.7*/for(lista <-plantilla.tail) yield /*41.34*/{_display_(Seq[Any](format.raw/*41.35*/("""
						"""),format.raw/*42.7*/("""<tr>
							<TH style="text-align: left">"""),_display_(/*43.38*/lista/*43.43*/.get(0)),format.raw/*43.50*/("""</TH>
							<TH style="text-align: center">"""),_display_(/*44.40*/lista/*44.45*/.get(1)),format.raw/*44.52*/("""</TH>
							"""),_display_(/*45.9*/for(lista2 <- lista.tail.tail) yield /*45.39*/{_display_(Seq[Any](format.raw/*45.40*/("""
								
								"""),_display_(if(!(lista2.equals("") || lista2.equals("0.00")))/*47.59*/{_display_(Seq[Any](format.raw/*47.60*/("""
									"""),format.raw/*48.10*/("""<td style="text-align: center; background-color:powderblue; width:40px">"""),_display_(/*48.83*/lista2),format.raw/*48.89*/("""</TH>
								""")))}else/*49.14*/{_display_(Seq[Any](format.raw/*49.15*/("""
									"""),format.raw/*50.10*/("""<td style="text-align: center; width:40px">"""),_display_(/*50.54*/lista2),format.raw/*50.60*/("""</TH>
								""")))}),format.raw/*51.10*/("""
								
							""")))}),format.raw/*53.9*/("""
						"""),format.raw/*54.7*/("""</tr>
					""")))}),format.raw/*55.7*/("""
				"""),format.raw/*56.5*/("""</tbody>
			</table>
			<br>
			<div align="center">
				<button type="button" class="btn btn-sm  btn-success" style="font-size: 10px;" onclick="window.history.back()">Volver</button>
			</div>
			
			
		<br><br><br><br>
	</div>
		
""")))}),format.raw/*67.2*/("""
				
"""),format.raw/*69.1*/("""<script>

    
    $(document).ready(function() """),format.raw/*72.34*/("""{"""),format.raw/*72.35*/("""
	
    	"""),format.raw/*74.6*/("""$('#detalleKardex').DataTable("""),format.raw/*74.36*/("""{"""),format.raw/*74.37*/("""
	    	"""),format.raw/*75.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[-1], ["All"]],
	    	"paging":   false,
	        "ordering": true,
	        "info":     true,
	        "bLengthChange" : false,
	    	"language": """),format.raw/*81.19*/("""{"""),format.raw/*81.20*/("""
	        	"""),format.raw/*82.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*83.10*/("""}"""),format.raw/*83.11*/("""
	    """),format.raw/*84.6*/("""}"""),format.raw/*84.7*/(""" """),format.raw/*84.8*/(""");
    	
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/(""" """),format.raw/*87.4*/(""");
    
    
    
	
</script>













		
		



"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String,plantilla:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,plantilla)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,plantilla) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,plantilla)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/reportOperadorConsol1.scala.html
                  HASH: ac34dd08a3e67005d98b415ce283fe7b4a09a2e5
                  MATRIX: 1049->1|1310->169|1342->176|1358->184|1397->186|1426->190|1494->238|1522->240|1598->291|1695->368|1725->372|1862->482|1893->492|1974->546|2005->556|2554->1078|2572->1087|2607->1101|2679->1146|2697->1155|2732->1169|2772->1183|2829->1224|2868->1225|2904->1234|3166->1469|3193->1475|3232->1486|3308->1532|3342->1539|3416->1587|3459->1614|3498->1615|3532->1622|3601->1664|3615->1669|3643->1676|3715->1721|3729->1726|3757->1733|3797->1747|3843->1777|3882->1778|3977->1846|4016->1847|4054->1857|4154->1930|4181->1936|4219->1955|4258->1956|4296->1966|4367->2010|4394->2016|4440->2031|4488->2049|4522->2056|4564->2068|4596->2073|4859->2306|4892->2312|4968->2360|4997->2361|5032->2369|5090->2399|5119->2400|5153->2407|5370->2596|5399->2597|5438->2608|5543->2685|5572->2686|5605->2692|5633->2693|5661->2694|5767->2773|5795->2774|5823->2775
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|41->10|41->10|42->11|42->11|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|59->28|62->31|62->31|63->32|66->35|67->36|72->41|72->41|72->41|73->42|74->43|74->43|74->43|75->44|75->44|75->44|76->45|76->45|76->45|78->47|78->47|79->48|79->48|79->48|80->49|80->49|81->50|81->50|81->50|82->51|84->53|85->54|86->55|87->56|98->67|100->69|103->72|103->72|105->74|105->74|105->74|106->75|112->81|112->81|113->82|114->83|114->83|115->84|115->84|115->84|118->87|118->87|118->87
                  -- GENERATED --
              */
          