
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

object reporteMovOdoCantLista extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","REPORTE ODO DETALLE DE MOVIMIENTOS")),format.raw/*13.126*/("""
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
								<TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*38.63*/mapDiccionario/*38.77*/.get("BODEGA")),format.raw/*38.91*/("""/PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<BR>CLIENTE</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<br>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">COMERCIAL</TH>
								
								<TH width="5%">Generar<BR></TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*47.9*/for(lista1 <- lista) yield /*47.29*/{_display_(Seq[Any](format.raw/*47.30*/("""
								"""),format.raw/*48.9*/("""<TR>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*49.62*/lista1/*49.68*/.get(10)),format.raw/*49.76*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*50.104*/lista1/*50.110*/.get(0)),format.raw/*50.117*/("""');">"""),_display_(/*50.123*/lista1/*50.129*/.get(1)),format.raw/*50.136*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoCliente('"""),_display_(/*51.105*/lista1/*51.111*/.get(8)),format.raw/*51.118*/("""');">"""),_display_(/*51.124*/lista1/*51.130*/.get(2)),format.raw/*51.137*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoProyecto('"""),_display_(/*52.106*/lista1/*52.112*/.get(9)),format.raw/*52.119*/("""');">"""),_display_(/*52.125*/lista1/*52.131*/.get(3)),format.raw/*52.138*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*53.62*/lista1/*53.68*/.get(4)),format.raw/*53.75*/("""</td>
									<td style="text-align:center;vertical-align:middle;">
										<form id="form1_"""),_display_(/*55.28*/lista1/*55.34*/.get(0)),format.raw/*55.41*/("""" class="formulario" method="post" action="/reporteMovOdoCantDetalle/">
											<input type="hidden" name="id_bodega" value=""""),_display_(/*56.58*/lista1/*56.64*/.get(0)),format.raw/*56.71*/("""">
											<input type="hidden" name="fechaDesde" value=""""),_display_(/*57.59*/fechaDesde),format.raw/*57.69*/("""">
											<input type="hidden" name="fechaHasta" value=""""),_display_(/*58.59*/fechaHasta),format.raw/*58.69*/("""">
											<a href="#" onclick="document.getElementById('form1_"""),_display_(/*59.65*/lista1/*59.71*/.get(0)),format.raw/*59.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">Generar</kbd>
											</a>
										</form>
									</td>
								</TR>
				 			""")))}),format.raw/*65.10*/("""
						"""),format.raw/*66.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


""")))}),format.raw/*74.2*/("""


"""),format.raw/*77.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*79.31*/("""{"""),format.raw/*79.32*/("""

		 """),format.raw/*81.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*81.35*/("""{"""),format.raw/*81.36*/("""
		    	"""),format.raw/*82.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*87.20*/("""{"""),format.raw/*87.21*/("""
		        	"""),format.raw/*88.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*89.11*/("""}"""),format.raw/*89.12*/("""
		  """),format.raw/*90.5*/("""}"""),format.raw/*90.6*/(""" """),format.raw/*90.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*93.2*/("""}"""),format.raw/*93.3*/(""");
	
	
	
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
                  SOURCE: app/viewsMnuOdo/reporteMovOdoCantLista.scala.html
                  HASH: 0d2cd8cec600c0fe1151c0854be33b421a52453a
                  MATRIX: 1050->1|1307->165|1339->172|1355->180|1394->182|1423->186|1491->234|1521->239|1576->274|1604->277|1646->299|1675->302|1719->325|1750->329|1827->380|1971->502|2001->505|2997->1474|3020->1488|3055->1502|3440->1861|3476->1881|3515->1882|3551->1891|3644->1957|3659->1963|3688->1971|3829->2084|3845->2090|3874->2097|3908->2103|3924->2109|3953->2116|4095->2230|4111->2236|4140->2243|4174->2249|4190->2255|4219->2262|4362->2377|4378->2383|4407->2390|4441->2396|4457->2402|4486->2409|4584->2480|4599->2486|4627->2493|4750->2589|4765->2595|4793->2602|4949->2731|4964->2737|4992->2744|5080->2805|5111->2815|5199->2876|5230->2886|5324->2953|5339->2959|5367->2966|5549->3117|5583->3124|5677->3188|5707->3191|5815->3271|5844->3272|5876->3277|5935->3308|5964->3309|5999->3317|6166->3456|6195->3457|6235->3469|6341->3547|6370->3548|6402->3553|6430->3554|6458->3555|6558->3628|6586->3629
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|69->38|69->38|69->38|78->47|78->47|78->47|79->48|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|83->52|83->52|83->52|83->52|83->52|83->52|84->53|84->53|84->53|86->55|86->55|86->55|87->56|87->56|87->56|88->57|88->57|89->58|89->58|90->59|90->59|90->59|96->65|97->66|105->74|108->77|110->79|110->79|112->81|112->81|112->81|113->82|118->87|118->87|119->88|120->89|120->89|121->90|121->90|121->90|124->93|124->93
                  -- GENERATED --
              */
          