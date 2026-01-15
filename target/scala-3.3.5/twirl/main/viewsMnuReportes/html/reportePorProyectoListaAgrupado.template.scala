
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

object reportePorProyectoListaAgrupado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalContactoBodega(mapDiccionario)),format.raw/*8.38*/("""
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "SELECCION DE PROYECTO","REPORTE DETALLE DE MOVIMIENTOS")),format.raw/*13.89*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
							  		</div>
							  		<input type="text" class="form-control left"
										id="searchTermtablaPrincipal"
										onkeyup="doSearch2('tablaPrincipal');">
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
					        <TR> 
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<br>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*38.63*/mapDiccionario/*38.77*/.get("COMUNA")),format.raw/*38.91*/("""<BR>PROYECTO</TH>
								<TH width="5%" >"""),_display_(/*39.26*/mapDiccionario/*39.40*/.get("ARRIENDO")),format.raw/*39.56*/("""<BR></TH>
								<TH width="5%" >VENTA<BR></TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*44.9*/for(lista1 <- lista) yield /*44.29*/{_display_(Seq[Any](format.raw/*44.30*/("""
								"""),format.raw/*45.9*/("""<TR>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoProyecto('"""),_display_(/*46.106*/lista1/*46.112*/.get(0)),format.raw/*46.119*/("""');">"""),_display_(/*46.125*/lista1/*46.131*/.get(1)),format.raw/*46.138*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*47.62*/lista1/*47.68*/.get(2)),format.raw/*47.75*/("""</td>
									<td style="text-align:center;vertical-align:middle;">
										<form id="form0_"""),_display_(/*49.28*/lista1/*49.34*/.get(1)),format.raw/*49.41*/("""" class="formulario" method="post" action="/routes2/reportePorProyectoDetalleAgrupado/">
											<input type="hidden" name="id_proyecto" value=""""),_display_(/*50.60*/lista1/*50.66*/.get(0)),format.raw/*50.73*/("""">
											<input type="hidden" name="fechaDesde" value=""""),_display_(/*51.59*/fechaDesde),format.raw/*51.69*/("""">
											<input type="hidden" name="fechaHasta" value=""""),_display_(/*52.59*/fechaHasta),format.raw/*52.69*/("""">
											<input type="hidden" name="esVenta" value="0">
											<a href="#" onclick="document.getElementById('form0_"""),_display_(/*54.65*/lista1/*54.71*/.get(1)),format.raw/*54.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">"""),_display_(/*55.53*/mapDiccionario/*55.67*/.get("Arriendos")),format.raw/*55.84*/("""</kbd>
											</a>
										</form>
									</td>
									<td style="text-align:center;vertical-align:middle;">
										<form id="form1_"""),_display_(/*60.28*/lista1/*60.34*/.get(1)),format.raw/*60.41*/("""" class="formulario" method="post" action="/routes2/reportePorProyectoDetalleAgrupado/">
											<input type="hidden" name="id_proyecto" value=""""),_display_(/*61.60*/lista1/*61.66*/.get(0)),format.raw/*61.73*/("""">
											<input type="hidden" name="fechaDesde" value=""""),_display_(/*62.59*/fechaDesde),format.raw/*62.69*/("""">
											<input type="hidden" name="fechaHasta" value=""""),_display_(/*63.59*/fechaHasta),format.raw/*63.69*/("""">
											<input type="hidden" name="esVenta" value="1">
											<a href="#" onclick="document.getElementById('form1_"""),_display_(/*65.65*/lista1/*65.71*/.get(1)),format.raw/*65.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">Ventas</kbd>
											</a>
										</form>
									</td>
								</TR>
				 			""")))}),format.raw/*71.10*/("""
						"""),format.raw/*72.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


""")))}),format.raw/*80.2*/("""


"""),format.raw/*83.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*85.31*/("""{"""),format.raw/*85.32*/("""

		 """),format.raw/*87.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*87.35*/("""{"""),format.raw/*87.36*/("""
		    	"""),format.raw/*88.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*93.20*/("""{"""),format.raw/*93.21*/("""
		        	"""),format.raw/*94.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*95.11*/("""}"""),format.raw/*95.12*/("""
		  """),format.raw/*96.5*/("""}"""),format.raw/*96.6*/(""" """),format.raw/*96.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*99.2*/("""}"""),format.raw/*99.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],fechaDesde:String,fechaHasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,fechaDesde,fechaHasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,fechaDesde,fechaHasta) => apply(mapDiccionario,mapPermiso,userMnu,lista,fechaDesde,fechaHasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportePorProyectoListaAgrupado.scala.html
                  HASH: 11ff2b5f190d6933fb2f26507a155d8aabd67b05
                  MATRIX: 1064->1|1321->165|1353->172|1369->180|1408->182|1437->186|1505->234|1535->239|1590->274|1618->277|1660->299|1689->302|1733->325|1764->329|1841->380|1947->465|1977->468|2983->1447|3006->1461|3041->1475|3111->1518|3134->1532|3171->1548|3296->1647|3332->1667|3371->1668|3407->1677|3545->1787|3561->1793|3590->1800|3624->1806|3640->1812|3669->1819|3767->1890|3782->1896|3810->1903|3933->1999|3948->2005|3976->2012|4151->2160|4166->2166|4194->2173|4282->2234|4313->2244|4401->2305|4432->2315|4584->2440|4599->2446|4627->2453|4720->2519|4743->2533|4781->2550|4954->2696|4969->2702|4997->2709|5172->2857|5187->2863|5215->2870|5303->2931|5334->2941|5422->3002|5453->3012|5605->3137|5620->3143|5648->3150|5829->3300|5863->3307|5957->3371|5987->3374|6095->3454|6124->3455|6156->3460|6215->3491|6244->3492|6279->3500|6446->3639|6475->3640|6515->3652|6621->3730|6650->3731|6682->3736|6710->3737|6738->3738|6838->3811|6866->3812
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|69->38|69->38|69->38|70->39|70->39|70->39|75->44|75->44|75->44|76->45|77->46|77->46|77->46|77->46|77->46|77->46|78->47|78->47|78->47|80->49|80->49|80->49|81->50|81->50|81->50|82->51|82->51|83->52|83->52|85->54|85->54|85->54|86->55|86->55|86->55|91->60|91->60|91->60|92->61|92->61|92->61|93->62|93->62|94->63|94->63|96->65|96->65|96->65|102->71|103->72|111->80|114->83|116->85|116->85|118->87|118->87|118->87|119->88|124->93|124->93|125->94|126->95|126->95|127->96|127->96|127->96|130->99|130->99
                  -- GENERATED --
              */
          