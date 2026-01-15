
package viewsMnuBajas.html

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

object bajaListaModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.ActaBaja],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listActas: List[tables.ActaBaja]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR ACTAS DE BAJA","")),format.raw/*9.61*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-10 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>NUMERO</TH>
							<TH>OBSERVACIONES</TH>
							<TH>DOCUMENTO</TH>
							<TH>EDIT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*23.8*/for(lista1 <- listActas) yield /*23.32*/{_display_(Seq[Any](format.raw/*23.33*/("""
							"""),format.raw/*24.8*/("""<TR>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*26.37*/utilities/*26.46*/.Fechas.AAMMDD(lista1.getFecha())),format.raw/*26.79*/("""</div>
									"""),_display_(/*27.11*/utilities/*27.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*27.53*/("""
								"""),format.raw/*28.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*29.42*/lista1/*29.48*/.getNumero()),format.raw/*29.60*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*30.40*/lista1/*30.46*/.getObservaciones()),format.raw/*30.65*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.getActaBajaPDF().equals("0"))/*32.50*/{_display_(Seq[Any](format.raw/*32.51*/("""
										"""),format.raw/*33.11*/("""--
									""")))}else/*34.15*/{_display_(Seq[Any](format.raw/*34.16*/("""
										"""),format.raw/*35.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*35.52*/lista1/*35.58*/.getActaBajaPDF()),format.raw/*35.75*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*38.11*/("""
								"""),format.raw/*39.9*/("""</td>
								<td  style="text-align:center;">
									<a href="/bajaModifica/"""),_display_(/*41.34*/lista1/*41.40*/.getId()),format.raw/*41.48*/("""">
										<kbd style="background-color: #73C6B6">edit</kbd>
									</a>
								</td>
								
							</TR>
			 			""")))}),format.raw/*47.9*/("""
					"""),format.raw/*48.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	

""")))}),format.raw/*67.2*/("""


"""),format.raw/*70.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*71.31*/("""{"""),format.raw/*71.32*/("""
		  """),format.raw/*72.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*72.36*/("""{"""),format.raw/*72.37*/("""
		    	"""),format.raw/*73.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "desc" ],[ 1, "desc" ]],
		    	"language": """),format.raw/*76.20*/("""{"""),format.raw/*76.21*/("""
		        	"""),format.raw/*77.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*78.11*/("""}"""),format.raw/*78.12*/("""
		  """),format.raw/*79.5*/("""}"""),format.raw/*79.6*/(""" """),format.raw/*79.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*81.2*/("""}"""),format.raw/*81.3*/(""");
	
	
	const descargaDocumento = (nombreDOC) => """),format.raw/*84.43*/("""{"""),format.raw/*84.44*/("""
		  """),format.raw/*85.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/("""
	
	
		
		
	
	
	
"""),format.raw/*95.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listActas:List[tables.ActaBaja]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listActas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.ActaBaja]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listActas) => apply(mapDiccionario,mapPermiso,userMnu,listActas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBajas/bajaListaModifica.scala.html
                  HASH: a67a93ecd24364f3d7fb98639e24bbf7cb58b186
                  MATRIX: 1036->1|1260->132|1293->140|1309->148|1348->150|1377->154|1445->202|1473->204|1549->255|1626->312|1656->315|2141->774|2181->798|2220->799|2255->807|2363->888|2381->897|2435->930|2479->947|2497->956|2551->989|2587->998|2661->1045|2676->1051|2709->1063|2781->1108|2796->1114|2836->1133|2958->1228|2997->1229|3036->1240|3072->1257|3111->1258|3150->1269|3218->1310|3233->1316|3271->1333|3392->1423|3428->1432|3535->1512|3550->1518|3579->1526|3730->1647|3763->1653|4305->2165|4335->2168|4425->2230|4454->2231|4486->2236|4545->2267|4574->2268|4609->2276|4798->2437|4827->2438|4867->2450|4973->2528|5002->2529|5034->2534|5062->2535|5090->2536|5190->2609|5218->2610|5295->2659|5324->2660|5356->2665|5487->2769|5515->2770|5559->2787
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|54->23|54->23|54->23|55->24|57->26|57->26|57->26|58->27|58->27|58->27|59->28|60->29|60->29|60->29|61->30|61->30|61->30|63->32|63->32|64->33|65->34|65->34|66->35|66->35|66->35|66->35|69->38|70->39|72->41|72->41|72->41|78->47|79->48|98->67|101->70|102->71|102->71|103->72|103->72|103->72|104->73|107->76|107->76|108->77|109->78|109->78|110->79|110->79|110->79|112->81|112->81|115->84|115->84|116->85|118->87|118->87|126->95
                  -- GENERATED --
              */
          