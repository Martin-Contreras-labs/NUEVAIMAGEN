
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

object importaHojaVidaIconstruye1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaOC: List[List[String]], id_equipo: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "PROCESO IMPORTAR MANTENCIONES (DESDE ICONSTRUYE)","SELECCIONAR ORDEN DE COMPRA")),format.raw/*9.113*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>NRO OC</TH>
							<TH>CENTRO GESTION</TH>
							<TH>PROVEEDOR</TH>
							<TH>SOLICITANTE</TH>
							<TH>IMPORTAR</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*23.8*/for(lista1 <- listaOC) yield /*23.30*/{_display_(Seq[Any](format.raw/*23.31*/("""
							"""),format.raw/*24.8*/("""<TR>
								<td  style="text-align:center;">"""),_display_(/*25.42*/lista1/*25.48*/.get(1)),format.raw/*25.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*26.40*/lista1/*26.46*/.get(2)),format.raw/*26.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*27.40*/lista1/*27.46*/.get(3)),format.raw/*27.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*28.40*/lista1/*28.46*/.get(4)),format.raw/*28.53*/("""</td>
								<td  style="text-align:center;">
									<form id=""""),_display_(/*30.21*/lista1/*30.27*/.get(0)),format.raw/*30.34*/("""" action="/importaHojaVidaIconstruye2/" method="POST">
										<input type="hidden" name="idDocumento" value=""""),_display_(/*31.59*/lista1/*31.65*/.get(0)),format.raw/*31.72*/("""">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*32.57*/id_equipo),format.raw/*32.66*/("""">
										<a href="#" onclick="$('#"""),_display_(/*33.37*/lista1/*33.43*/.get(0)),format.raw/*33.50*/("""').submit()">
											<kbd style="background-color: #73C6B6">import</kbd>
										</a>
									</form>
								</td>
								
							</TR>
			 			""")))}),format.raw/*40.9*/("""
					"""),format.raw/*41.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	

""")))}),format.raw/*60.2*/("""


"""),format.raw/*63.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*64.31*/("""{"""),format.raw/*64.32*/("""
		  """),format.raw/*65.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*65.36*/("""{"""),format.raw/*65.37*/("""
		    	"""),format.raw/*66.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*69.20*/("""{"""),format.raw/*69.21*/("""
		        	"""),format.raw/*70.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*71.11*/("""}"""),format.raw/*71.12*/("""
		  """),format.raw/*72.5*/("""}"""),format.raw/*72.6*/(""" """),format.raw/*72.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*74.2*/("""}"""),format.raw/*74.3*/(""");
	
	
	const descargaDocumento = (nombreDOC) => """),format.raw/*77.43*/("""{"""),format.raw/*77.44*/("""
		  """),format.raw/*78.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*80.2*/("""}"""),format.raw/*80.3*/("""
	
	
		
		
	
	
	
"""),format.raw/*88.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaOC:List[List[String]],id_equipo:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaOC,id_equipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaOC,id_equipo) => apply(mapDiccionario,mapPermiso,userMnu,listaOC,id_equipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/importaHojaVidaIconstruye1.scala.html
                  HASH: 75636febddc68e71829b1a7db9cb123016e5a88b
                  MATRIX: 1048->1|1284->144|1317->152|1333->160|1372->162|1401->166|1469->214|1497->216|1573->267|1703->376|1733->379|2208->828|2246->850|2285->851|2320->859|2393->905|2408->911|2436->918|2508->963|2523->969|2551->976|2623->1021|2638->1027|2666->1034|2738->1079|2753->1085|2781->1092|2875->1159|2890->1165|2918->1172|3058->1285|3073->1291|3101->1298|3187->1357|3217->1366|3283->1405|3298->1411|3326->1418|3509->1571|3542->1577|4087->2092|4117->2095|4207->2157|4236->2158|4268->2163|4327->2194|4356->2195|4391->2203|4565->2349|4594->2350|4634->2362|4740->2440|4769->2441|4801->2446|4829->2447|4857->2448|4957->2521|4985->2522|5062->2571|5091->2572|5123->2577|5254->2681|5282->2682|5326->2699
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|54->23|54->23|54->23|55->24|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|59->28|59->28|59->28|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|64->33|64->33|64->33|71->40|72->41|91->60|94->63|95->64|95->64|96->65|96->65|96->65|97->66|100->69|100->69|101->70|102->71|102->71|103->72|103->72|103->72|105->74|105->74|108->77|108->77|109->78|111->80|111->80|119->88
                  -- GENERATED --
              */
          