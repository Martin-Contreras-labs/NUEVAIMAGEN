
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

object reporteMovSoloBodInternas0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "PERIODO DE MOVIMIENTOS POR " + mapDiccionario.get("BODEGA")+" INTERNAS", "")),format.raw/*9.109*/("""
		"""),format.raw/*10.3*/("""<form action="/reporteMovSoloBodInternas1/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>FECHA DESDE:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaDesde"
							  			name="fechaDesde"
							  			value=""""),_display_(/*22.21*/fechaDesde),format.raw/*22.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*32.21*/fechaHasta),format.raw/*32.31*/(""""
					        			required>
								</td>
							</TR>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row  justify-content-md-center">
				<div class="col-xs-4 col-sm-2 col-md-2 col-lg-2">
					<input type="submit" id="btnSubmit" value='Generar Reporte' class="btn btn-primary btn-sm rounded-pill btn-block">
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*50.2*/("""


"""),format.raw/*53.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*54.31*/("""{"""),format.raw/*54.32*/("""
		  """),format.raw/*55.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*56.2*/("""}"""),format.raw/*56.3*/(""");
	
	const validarForm = () =>"""),format.raw/*58.27*/("""{"""),format.raw/*58.28*/("""
		"""),format.raw/*59.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*63.20*/("""{"""),format.raw/*63.21*/("""
			"""),format.raw/*64.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*65.87*/("""{"""),format.raw/*65.88*/("""
				"""),format.raw/*66.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*68.8*/("""}"""),format.raw/*68.9*/(""");
		"""),format.raw/*69.3*/("""}"""),format.raw/*69.4*/("""
		
		"""),format.raw/*71.3*/("""var desde1 = new Date(desde);
		var hasta2 = new Date(hasta);
		var dif = hasta2 - desde1;
		var dias = Math.floor(dif / (1000 * 60 * 60 * 24));
		
		if(dias > 365)"""),format.raw/*76.17*/("""{"""),format.raw/*76.18*/("""
			"""),format.raw/*77.4*/("""flag = false;
			alertify.alert('EL PERIODO NO PUEDE SER MAYOR A 93 DIAS', function () """),format.raw/*78.74*/("""{"""),format.raw/*78.75*/("""
				"""),format.raw/*79.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*81.8*/("""}"""),format.raw/*81.9*/(""");
		"""),format.raw/*82.3*/("""}"""),format.raw/*82.4*/("""
		
		"""),format.raw/*84.3*/("""return(flag);
	"""),format.raw/*85.2*/("""}"""),format.raw/*85.3*/("""
	
"""),format.raw/*87.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteMovSoloBodInternas0.scala.html
                  HASH: 0523b08bf97eb8af281c39f635d17415c018d7c9
                  MATRIX: 1040->1|1270->138|1303->146|1319->154|1358->156|1387->160|1455->208|1483->210|1559->261|1685->366|1715->369|2266->893|2297->903|2566->1145|2597->1155|3024->1552|3054->1555|3144->1617|3173->1618|3205->1623|3298->1689|3326->1690|3385->1721|3414->1722|3444->1725|3667->1920|3696->1921|3727->1925|3855->2025|3884->2026|3916->2031|4008->2096|4036->2097|4068->2102|4096->2103|4129->2109|4321->2273|4350->2274|4381->2278|4496->2365|4525->2366|4557->2371|4649->2436|4677->2437|4709->2442|4737->2443|4770->2449|4812->2464|4840->2465|4870->2468
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|63->32|63->32|81->50|84->53|85->54|85->54|86->55|87->56|87->56|89->58|89->58|90->59|94->63|94->63|95->64|96->65|96->65|97->66|99->68|99->68|100->69|100->69|102->71|107->76|107->76|108->77|109->78|109->78|110->79|112->81|112->81|113->82|113->82|115->84|116->85|116->85|118->87
                  -- GENERATED --
              */
          