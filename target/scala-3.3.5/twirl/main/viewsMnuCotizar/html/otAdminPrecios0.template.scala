
package viewsMnuCotizar.html

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

object otAdminPrecios0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalContactoBodega(mapDiccionario)),format.raw/*8.38*/("""
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "LISTADO "+mapDiccionario.get("BODEGA")+"/PROYECTO VIGENTES CON "+mapDiccionario.get("OT"),"PARA OBTENER DETALLE")),format.raw/*13.146*/("""
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
								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*41.63*/mapDiccionario/*41.77*/.get("COMUNA")),format.raw/*41.91*/("""<BR>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;width:4%">SELECT</TH>
							</TR>
						</thead>
						<tbody>
						"""),_display_(/*46.8*/for(lista1 <- lista) yield /*46.28*/{_display_(Seq[Any](format.raw/*46.29*/("""
							"""),format.raw/*47.8*/("""<TR>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*48.61*/lista1/*48.67*/.get(15)),format.raw/*48.75*/("""</td>
								<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*49.103*/lista1/*49.109*/.get(1)),format.raw/*49.116*/("""');">"""),_display_(/*49.122*/lista1/*49.128*/.get(5)),format.raw/*49.135*/("""</a></td>
								<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoCliente('"""),_display_(/*50.104*/lista1/*50.110*/.get(2)),format.raw/*50.117*/("""');">"""),_display_(/*50.123*/lista1/*50.129*/.get(7)),format.raw/*50.136*/("""</a></td>
								<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoProyecto('"""),_display_(/*51.105*/lista1/*51.111*/.get(3)),format.raw/*51.118*/("""');">"""),_display_(/*51.124*/lista1/*51.130*/.get(8)),format.raw/*51.137*/("""</a></td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*52.61*/lista1/*52.67*/.get(9)),format.raw/*52.74*/("""</td>
								<td style="text-align:left;vertical-align:middle;">
									<form id="form_"""),_display_(/*54.26*/lista1/*54.32*/.get(1)),format.raw/*54.39*/("""" class="formulario" method="post" action="/routes2/otAdminPrecios1/">
										<input type="hidden" name="id_bodega" value=""""),_display_(/*55.57*/lista1/*55.63*/.get(1)),format.raw/*55.70*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*56.63*/lista1/*56.69*/.get(1)),format.raw/*56.76*/("""').submit()">
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*62.9*/("""
						"""),format.raw/*63.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	

""")))}),format.raw/*71.2*/("""


"""),format.raw/*74.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*76.31*/("""{"""),format.raw/*76.32*/("""

		 """),format.raw/*78.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*78.35*/("""{"""),format.raw/*78.36*/("""
		    	"""),format.raw/*79.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*84.20*/("""{"""),format.raw/*84.21*/("""
		        	"""),format.raw/*85.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*86.11*/("""}"""),format.raw/*86.12*/("""
		  """),format.raw/*87.5*/("""}"""),format.raw/*87.6*/(""" """),format.raw/*87.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*90.2*/("""}"""),format.raw/*90.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista) => apply(mapDiccionario,mapPermiso,userMnu,lista)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otAdminPrecios0.scala.html
                  HASH: 14bd9ed3191a7e4d12f75c7d1d7850308d5ff21f
                  MATRIX: 1033->1|1250->125|1282->132|1298->140|1337->142|1366->146|1434->194|1464->199|1519->234|1547->237|1589->259|1618->262|1662->285|1693->289|1770->340|1934->482|1964->485|2966->1460|2989->1474|3024->1488|3297->1734|3320->1748|3355->1762|3529->1910|3565->1930|3604->1931|3639->1939|3731->2004|3746->2010|3775->2018|3911->2126|3927->2132|3956->2139|3990->2145|4006->2151|4035->2158|4176->2271|4192->2277|4221->2284|4255->2290|4271->2296|4300->2303|4442->2417|4458->2423|4487->2430|4521->2436|4537->2442|4566->2449|4663->2519|4678->2525|4706->2532|4824->2623|4839->2629|4867->2636|5021->2763|5036->2769|5064->2776|5156->2841|5171->2847|5199->2854|5373->2998|5407->3005|5502->3070|5532->3073|5640->3153|5669->3154|5701->3159|5760->3190|5789->3191|5824->3199|5991->3338|6020->3339|6060->3351|6166->3429|6195->3430|6227->3435|6255->3436|6283->3437|6383->3510|6411->3511
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|69->38|69->38|69->38|72->41|72->41|72->41|77->46|77->46|77->46|78->47|79->48|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|83->52|83->52|83->52|85->54|85->54|85->54|86->55|86->55|86->55|87->56|87->56|87->56|93->62|94->63|102->71|105->74|107->76|107->76|109->78|109->78|109->78|110->79|115->84|115->84|116->85|117->86|117->86|118->87|118->87|118->87|121->90|121->90
                  -- GENERATED --
              */
          