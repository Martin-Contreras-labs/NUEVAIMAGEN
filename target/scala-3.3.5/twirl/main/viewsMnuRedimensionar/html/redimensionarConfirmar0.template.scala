
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

object redimensionarConfirmar0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String, String],Map[String,String],utilities.UserMnu,List[tables.ActaRedimensionar],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String, String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listActas: List[tables.ActaRedimensionar]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "ACTAS PARA REDIMENSIONAR PENDIENTES DE CONFIRMAR","")),format.raw/*10.86*/("""
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
								<TH>REVISAR<br>CONFIRMAR</TH>
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
										<a href="#" onclick="$('#id_actaRedimensionar').val('"""),_display_(/*43.65*/lista1/*43.71*/.getId()),format.raw/*43.79*/("""'); document.getElementById('formConfirmar').submit();">
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
	
	<form id="formConfirmar" method="post" action="/routes2/redimensionarConfirmar1/">
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
                  SOURCE: app/viewsMnuRedimensionar/redimensionarConfirmar0.scala.html
                  HASH: 907f0246875ec3636fd0e72c484bf6189bc53bbe
                  MATRIX: 1060->1|1294->142|1327->150|1343->158|1382->160|1411->164|1479->212|1509->216|1586->267|1689->349|1720->353|2316->923|2356->947|2395->948|2431->957|2504->1003|2519->1009|2552->1021|2663->1105|2678->1111|2710->1122|2755->1140|2773->1149|2827->1182|2865->1192|2937->1237|2952->1243|2992->1262|3112->1355|3151->1356|3191->1368|3228->1386|3267->1387|3307->1399|3375->1440|3390->1446|3424->1459|3548->1552|3586->1562|3724->1673|3739->1679|3768->1687|3972->1860|4006->1867|4738->2569|4768->2572|4858->2634|4887->2635|4919->2640|4978->2671|5007->2672|5042->2680|5217->2827|5246->2828|5286->2840|5392->2918|5421->2919|5453->2924|5481->2925|5509->2926|5609->2999|5637->3000|5712->3047|5741->3048|5773->3053|5904->3157|5932->3158|5966->3165
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|41->10|41->10|42->11|56->25|56->25|56->25|57->26|58->27|58->27|58->27|60->29|60->29|60->29|61->30|61->30|61->30|62->31|63->32|63->32|63->32|65->34|65->34|66->35|67->36|67->36|68->37|68->37|68->37|68->37|71->40|72->41|74->43|74->43|74->43|79->48|80->49|104->73|107->76|108->77|108->77|109->78|109->78|109->78|110->79|113->82|113->82|114->83|115->84|115->84|116->85|116->85|116->85|118->87|118->87|120->89|120->89|121->90|123->92|123->92|127->96
                  -- GENERATED --
              */
          