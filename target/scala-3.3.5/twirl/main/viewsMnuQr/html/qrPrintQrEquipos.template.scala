
package viewsMnuQr.html

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

object qrPrintQrEquipos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[List[String]]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listado: List[List[List[String]]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "QR DE EQUIPOS -- CODIFICACION QR","")),format.raw/*9.70*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
				<div class="noprint">
					<input type="button" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.print(); return false;" value="Imprimir">
				</div>
				<br>
			</div>
			<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
				<div class="noprint">
					<form action="/qrPrintQrEquiposWord/" method="POST">
						<input type="submit" class="btn btn-info btn-sm rounded-pill btn-block" value="Exportar a Word">
					</form>
				</div>
				<br>
			</div>
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-bordered table-condensed table-fluid">
					<tbody>
						"""),_display_(/*28.8*/for(lista1 <- listado) yield /*28.30*/{_display_(Seq[Any](format.raw/*28.31*/("""
							"""),format.raw/*29.8*/("""<TR>
								"""),_display_(/*30.10*/for(lista2 <- lista1) yield /*30.31*/{_display_(Seq[Any](format.raw/*30.32*/("""
									"""),format.raw/*31.10*/("""<td style="text-align:center">
										<img src='/viewImg/"""),_display_(/*32.31*/mapDiccionario/*32.45*/.get("logoEmpresa")),format.raw/*32.64*/("""' width="auto" height="40px">
										<br>
										<img src='/viewImg/"""),_display_(/*34.31*/lista2/*34.37*/.get(0)),format.raw/*34.44*/("""' style="max-height:120px;max-width:100%">
										<br>
										"""),_display_(/*36.12*/lista2/*36.18*/.get(1).toUpperCase()),format.raw/*36.39*/("""
									"""),format.raw/*37.10*/("""</td>
									<!--
										<table>
											<tbody>
												<tr>
													<td style="border-collapse: collapse;">
														<img src="/viewImg/InqSol.png" width="auto" height="40px">
													</td>
													<td style="border:none">
														<img src="/viewImg/QR_38_equipo.png" style="max-height:120px;max-width:100%">
													</td>
												</tr>
												<tr>
													<td style="border:none">
														
													</td>
													<td style="border:none">
														B-100265
													</td>
												</tr>
											</tbody>
										</table>
									-->
								""")))}),format.raw/*60.10*/("""
							"""),format.raw/*61.8*/("""</TR>
						""")))}),format.raw/*62.8*/("""
					"""),format.raw/*63.6*/("""</tbody>
				</table>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
""")))}),format.raw/*77.2*/("""




"""),format.raw/*82.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*83.31*/("""{"""),format.raw/*83.32*/("""
		  """),format.raw/*84.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*85.2*/("""}"""),format.raw/*85.3*/(""");
	
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listado:List[List[List[String]]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listado)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[List[String]]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listado) => apply(mapDiccionario,mapPermiso,userMnu,listado)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/qrPrintQrEquipos.scala.html
                  HASH: e8cf91c4cba3ed25c95f5d5b072ae5c3ea480ebc
                  MATRIX: 1035->1|1260->133|1293->141|1309->149|1348->151|1376->154|1444->202|1472->204|1548->255|1634->321|1664->324|2440->1074|2478->1096|2517->1097|2552->1105|2593->1119|2630->1140|2669->1141|2707->1151|2795->1212|2818->1226|2858->1245|2960->1320|2975->1326|3003->1333|3099->1402|3114->1408|3156->1429|3194->1439|3859->2073|3894->2081|3937->2094|3970->2100|4353->2453|4385->2458|4475->2520|4504->2521|4536->2526|4629->2592|4657->2593
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|59->28|59->28|59->28|60->29|61->30|61->30|61->30|62->31|63->32|63->32|63->32|65->34|65->34|65->34|67->36|67->36|67->36|68->37|91->60|92->61|93->62|94->63|108->77|113->82|114->83|114->83|115->84|116->85|116->85
                  -- GENERATED --
              */
          