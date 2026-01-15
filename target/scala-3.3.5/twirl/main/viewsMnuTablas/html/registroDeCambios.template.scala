
package viewsMnuTablas.html

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

object registroDeCambios extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listHistorial: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "HISTORIAL DE CAMBIOS (ULTIMOS 180 DIAS)", "")),format.raw/*9.78*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH>Nro</TH>
							<TH style="min-width:100px;">FECHA DE CAMBIO<br>Universal Time Coordinated (UTC)</TH>
							<TH>USUARIO</TH>
					        <TH>TABLA</TH>
							<TH>ID_TABLA</TH>
					        <TH>ACCION</TH>
					        <TH>DESCRIPCION</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*25.8*/for((lista1,index) <- listHistorial.zipWithIndex) yield /*25.57*/{_display_(Seq[Any](format.raw/*25.58*/("""
							"""),format.raw/*26.8*/("""<TR>
								<td  style="text-align:right;">"""),_display_(/*27.41*/(index+1)),format.raw/*27.50*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*28.42*/lista1/*28.48*/.get(0)),format.raw/*28.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*29.40*/lista1/*29.46*/.get(1)),format.raw/*29.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*30.40*/lista1/*30.46*/.get(2)),format.raw/*30.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*31.42*/lista1/*31.48*/.get(5)),format.raw/*31.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*32.40*/lista1/*32.46*/.get(3)),format.raw/*32.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*33.40*/lista1/*33.46*/.get(4)),format.raw/*33.53*/("""</td>
							</TR>
			 			""")))}),format.raw/*35.9*/("""
					"""),format.raw/*36.6*/("""</tbody>
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
""")))}),format.raw/*48.2*/("""




"""),format.raw/*53.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*54.31*/("""{"""),format.raw/*54.32*/("""
		  """),format.raw/*55.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*55.36*/("""{"""),format.raw/*55.37*/("""
		    	"""),format.raw/*56.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*59.20*/("""{"""),format.raw/*59.21*/("""
		        	"""),format.raw/*60.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*61.11*/("""}"""),format.raw/*61.12*/("""
		  """),format.raw/*62.5*/("""}"""),format.raw/*62.6*/(""" """),format.raw/*62.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*64.2*/("""}"""),format.raw/*64.3*/(""");
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listHistorial:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listHistorial)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listHistorial) => apply(mapDiccionario,mapPermiso,userMnu,listHistorial)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/registroDeCambios.scala.html
                  HASH: c027991350df8499a5a834c3cdfdd6f28eec88b3
                  MATRIX: 1034->1|1259->133|1292->141|1308->149|1347->151|1375->154|1443->202|1471->204|1547->255|1641->329|1671->332|2267->902|2332->951|2371->952|2406->960|2478->1005|2508->1014|2582->1061|2597->1067|2625->1074|2697->1119|2712->1125|2740->1132|2812->1177|2827->1183|2855->1190|2929->1237|2944->1243|2972->1250|3044->1295|3059->1301|3087->1308|3159->1353|3174->1359|3202->1366|3259->1393|3292->1399|3658->1735|3690->1740|3780->1802|3809->1803|3841->1808|3900->1839|3929->1840|3964->1848|4135->1991|4164->1992|4204->2004|4310->2082|4339->2083|4371->2088|4399->2089|4427->2090|4527->2163|4555->2164
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|56->25|56->25|56->25|57->26|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|66->35|67->36|79->48|84->53|85->54|85->54|86->55|86->55|86->55|87->56|90->59|90->59|91->60|92->61|92->61|93->62|93->62|93->62|95->64|95->64
                  -- GENERATED --
              */
          