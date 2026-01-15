
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

object redimensionarListar0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String, String],Map[String,String],utilities.UserMnu,List[tables.ActaRedimensionar],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String, String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listActas: List[tables.ActaRedimensionar]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "ACTAS DE REDIMENSIONAR CONFIRMADAS","")),format.raw/*10.72*/("""
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
								<TH style="min-width:80px;">FECHA<br>CONFIRMADA</TH>
								<TH>REVISAR<br>CONFIRMAR</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*26.9*/for(lista1 <- listActas) yield /*26.33*/{_display_(Seq[Any](format.raw/*26.34*/("""
								"""),format.raw/*27.9*/("""<TR>
									<td style="text-align:center;">"""),_display_(/*28.42*/lista1/*28.48*/.getNumero()),format.raw/*28.60*/("""</td>
									<td style="text-align:center;">
										<div style="display:none">"""),_display_(/*30.38*/lista1/*30.44*/.getFecha()),format.raw/*30.55*/("""</div>
										"""),_display_(/*31.12*/utilities/*31.21*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*31.54*/("""
									"""),format.raw/*32.10*/("""</td>
									<td style="text-align:left;">"""),_display_(/*33.40*/lista1/*33.46*/.getObservaciones()),format.raw/*33.65*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.getActaPDF().equals("0"))/*35.47*/{_display_(Seq[Any](format.raw/*35.48*/("""
											"""),format.raw/*36.12*/("""--
										""")))}else/*37.16*/{_display_(Seq[Any](format.raw/*37.17*/("""
											"""),format.raw/*38.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*38.53*/lista1/*38.59*/.getActaPDF()),format.raw/*38.72*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*41.12*/("""
									"""),format.raw/*42.10*/("""</td>
									<td style="text-align:center;">
										<div style="display:none">"""),_display_(/*44.38*/lista1/*44.44*/.getFechaConfirma()),format.raw/*44.63*/("""</div>
										"""),_display_(/*45.12*/utilities/*45.21*/.Fechas.DDMMAA(lista1.getFechaConfirma())),format.raw/*45.62*/("""
									"""),format.raw/*46.10*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="$('#id_actaRedimensionar').val('"""),_display_(/*48.65*/lista1/*48.71*/.getId()),format.raw/*48.79*/("""'); document.getElementById('formConfirmadas').submit();">
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</td>
								</TR>
				 			""")))}),format.raw/*53.10*/("""
						"""),format.raw/*54.7*/("""</tbody>
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
	
	<form id="formConfirmadas" method="post" action="/routes2/redimensionarListar1/">
		<input type="hidden" id="id_actaRedimensionar" name="id_actaRedimensionar">
	</form>
			
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	

""")))}),format.raw/*78.2*/("""


"""),format.raw/*81.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*82.31*/("""{"""),format.raw/*82.32*/("""
		  """),format.raw/*83.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*83.36*/("""{"""),format.raw/*83.37*/("""
		    	"""),format.raw/*84.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "desc" ]],
		    	"language": """),format.raw/*87.20*/("""{"""),format.raw/*87.21*/("""
		        	"""),format.raw/*88.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*89.11*/("""}"""),format.raw/*89.12*/("""
		  """),format.raw/*90.5*/("""}"""),format.raw/*90.6*/(""" """),format.raw/*90.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*92.2*/("""}"""),format.raw/*92.3*/(""");
	
	const descargaDocumento = (nombreDOC) => """),format.raw/*94.43*/("""{"""),format.raw/*94.44*/("""
		  """),format.raw/*95.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*97.2*/("""}"""),format.raw/*97.3*/("""
	
	
	
"""),format.raw/*101.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuRedimensionar/redimensionarListar0.scala.html
                  HASH: 7d46604354d703a3ec247cbf6271c73d70ef2036
                  MATRIX: 1057->1|1291->142|1324->150|1340->158|1379->160|1408->164|1476->212|1506->216|1583->267|1672->335|1703->339|2360->970|2400->994|2439->995|2475->1004|2548->1050|2563->1056|2596->1068|2707->1152|2722->1158|2754->1169|2799->1187|2817->1196|2871->1229|2909->1239|2981->1284|2996->1290|3036->1309|3156->1402|3195->1403|3235->1415|3272->1433|3311->1434|3351->1446|3419->1487|3434->1493|3468->1506|3592->1599|3630->1609|3741->1693|3756->1699|3796->1718|3841->1736|3859->1745|3921->1786|3959->1796|4097->1907|4112->1913|4141->1921|4347->2096|4381->2103|5112->2804|5142->2807|5232->2869|5261->2870|5293->2875|5352->2906|5381->2907|5416->2915|5591->3062|5620->3063|5660->3075|5766->3153|5795->3154|5827->3159|5855->3160|5883->3161|5983->3234|6011->3235|6086->3282|6115->3283|6147->3288|6278->3392|6306->3393|6341->3400
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|41->10|41->10|42->11|57->26|57->26|57->26|58->27|59->28|59->28|59->28|61->30|61->30|61->30|62->31|62->31|62->31|63->32|64->33|64->33|64->33|66->35|66->35|67->36|68->37|68->37|69->38|69->38|69->38|69->38|72->41|73->42|75->44|75->44|75->44|76->45|76->45|76->45|77->46|79->48|79->48|79->48|84->53|85->54|109->78|112->81|113->82|113->82|114->83|114->83|114->83|115->84|118->87|118->87|119->88|120->89|120->89|121->90|121->90|121->90|123->92|123->92|125->94|125->94|126->95|128->97|128->97|132->101
                  -- GENERATED --
              */
          