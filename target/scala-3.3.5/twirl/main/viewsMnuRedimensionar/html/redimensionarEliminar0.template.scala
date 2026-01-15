
package viewsMnuRedimensionar.html

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

object redimensionarEliminar0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String, String],Map[String,String],utilities.UserMnu,List[tables.ActaRedimensionar],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String, String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listActas: List[tables.ActaRedimensionar]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "ELIMINAR ACTAS PARA REDIMENSIONAR","SOLO PENDIENTES DE CONFIRMAR")),format.raw/*10.99*/("""
			"""),format.raw/*11.4*/("""<input type="hidden" id="cambiosDeEstados" name="cambiosDeEstados">
			<div class="row  justify-content-md-center">
				<div class="col-xs-11 col-sm-9 col-md-9 col-lg-9">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>Nro.ACTA</TH>
								<TH style="min-width:80px;">FECHA<br>ACTA</TH>
								<TH>OBSERVACIONES</TH>
								<TH>DOC<br>ADJ</TH>
								<TH>REVISAR<br>ELIMINAR</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*25.9*/for(lista1 <- listActas) yield /*25.33*/{_display_(Seq[Any](format.raw/*25.34*/("""
								"""),format.raw/*26.9*/("""<TR>
									<td style="text-align:center;">"""),_display_(/*27.42*/lista1/*27.48*/.getNumero()),format.raw/*27.60*/("""</td>
									<td style="text-align:center;">
										<div style="display:none">"""),_display_(/*29.38*/lista1/*29.44*/.getFecha()),format.raw/*29.55*/("""</div>
										"""),_display_(/*30.12*/utilities/*30.21*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*30.54*/("""
									"""),format.raw/*31.10*/("""</td>
									<td style="text-align:left;">"""),_display_(/*32.40*/lista1/*32.46*/.getObservaciones()),format.raw/*32.65*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.getActaPDF().equals("0"))/*34.47*/{_display_(Seq[Any](format.raw/*34.48*/("""
											"""),format.raw/*35.12*/("""--
										""")))}else/*36.16*/{_display_(Seq[Any](format.raw/*36.17*/("""
											"""),format.raw/*37.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*37.53*/lista1/*37.59*/.getActaPDF()),format.raw/*37.72*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*40.12*/("""
									"""),format.raw/*41.10*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="$('#id_actaRedimensionar').val('"""),_display_(/*43.65*/lista1/*43.71*/.getId()),format.raw/*43.79*/("""'); document.getElementById('formEliminar').submit();">
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</td>
								</TR>
				 			""")))}),format.raw/*48.10*/("""
						"""),format.raw/*49.7*/("""</tbody>
					</table>
				</div>
			</div>
			
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
				</div>
			</div>
	</div>
	
	<form id="formEliminar" method="post" action="/routes2/redimensionarEliminar1/">
		<input type="hidden" id="id_actaRedimensionar" name="id_actaRedimensionar">
	</form>
			
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	

""")))}),format.raw/*73.2*/("""


"""),format.raw/*76.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*77.31*/("""{"""),format.raw/*77.32*/("""
		  """),format.raw/*78.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*78.36*/("""{"""),format.raw/*78.37*/("""
		    	"""),format.raw/*79.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "desc" ]],
		    	"language": """),format.raw/*82.20*/("""{"""),format.raw/*82.21*/("""
		        	"""),format.raw/*83.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*84.11*/("""}"""),format.raw/*84.12*/("""
		  """),format.raw/*85.5*/("""}"""),format.raw/*85.6*/(""" """),format.raw/*85.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/(""");
	
	const descargaDocumento = (nombreDOC) => """),format.raw/*89.43*/("""{"""),format.raw/*89.44*/("""
		  """),format.raw/*90.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*92.2*/("""}"""),format.raw/*92.3*/("""
	
	
	
"""),format.raw/*96.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String, String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listActas:List[tables.ActaRedimensionar]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listActas)

  def f:((Map[String, String],Map[String,String],utilities.UserMnu,List[tables.ActaRedimensionar]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listActas) => apply(mapDiccionario,mapPermiso,userMnu,listActas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuRedimensionar/redimensionarEliminar0.scala.html
                  HASH: 269f66b42a1375df6303cb34825912bd6bdbc868
                  MATRIX: 1059->1|1293->142|1326->150|1342->158|1381->160|1410->164|1478->212|1508->216|1585->267|1701->362|1732->366|2327->935|2367->959|2406->960|2442->969|2515->1015|2530->1021|2563->1033|2674->1117|2689->1123|2721->1134|2766->1152|2784->1161|2838->1194|2876->1204|2948->1249|2963->1255|3003->1274|3123->1367|3162->1368|3202->1380|3239->1398|3278->1399|3318->1411|3386->1452|3401->1458|3435->1471|3559->1564|3597->1574|3735->1685|3750->1691|3779->1699|3982->1871|4016->1878|4746->2578|4776->2581|4866->2643|4895->2644|4927->2649|4986->2680|5015->2681|5050->2689|5225->2836|5254->2837|5294->2849|5400->2927|5429->2928|5461->2933|5489->2934|5517->2935|5617->3008|5645->3009|5720->3056|5749->3057|5781->3062|5912->3166|5940->3167|5974->3174
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|41->10|41->10|42->11|56->25|56->25|56->25|57->26|58->27|58->27|58->27|60->29|60->29|60->29|61->30|61->30|61->30|62->31|63->32|63->32|63->32|65->34|65->34|66->35|67->36|67->36|68->37|68->37|68->37|68->37|71->40|72->41|74->43|74->43|74->43|79->48|80->49|104->73|107->76|108->77|108->77|109->78|109->78|109->78|110->79|113->82|113->82|114->83|115->84|115->84|116->85|116->85|116->85|118->87|118->87|120->89|120->89|121->90|123->92|123->92|127->96
                  -- GENERATED --
              */
          