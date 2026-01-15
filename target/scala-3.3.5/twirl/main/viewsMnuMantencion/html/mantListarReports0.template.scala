
package viewsMnuMantencion.html

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

object mantListarReports0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "LISTA DE REPORT INGRESADOS","")),format.raw/*8.64*/("""
		"""),format.raw/*9.3*/("""<form action="/routes3/mantListarReports1/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">PERIODO A CONSIDERAR</TH>
							</TR>
							<TR>
								<TH>FECHA DESDE:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaDesde"
							  			name="fechaDesde"
							  			value=""""),_display_(/*24.21*/fechaDesde),format.raw/*24.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*34.21*/fechaHasta),format.raw/*34.31*/(""""
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
					<input type="submit" value='Generar Reporte' class="btn btn-primary btn-sm rounded-pill btn-block">
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*52.2*/("""


"""),format.raw/*55.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*56.31*/("""{"""),format.raw/*56.32*/("""
		  """),format.raw/*57.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*58.2*/("""}"""),format.raw/*58.3*/(""");
	
	const validarForm = () =>"""),format.raw/*60.27*/("""{"""),format.raw/*60.28*/("""
		"""),format.raw/*61.3*/("""let flag = true;
		var desde = Date.parse(document.getElementById('fechaDesde').value);
		var hasta = Date.parse(document.getElementById('fechaHasta').value);
		let diffDias = Math.floor((hasta - desde)/(1000 * 60 * 60 * 24))+1;
		if(desde > hasta)"""),format.raw/*65.20*/("""{"""),format.raw/*65.21*/("""
			"""),format.raw/*66.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*67.87*/("""{"""),format.raw/*67.88*/("""
				"""),format.raw/*68.5*/("""return(flag);
     		"""),format.raw/*69.8*/("""}"""),format.raw/*69.9*/(""");
		"""),format.raw/*70.3*/("""}"""),format.raw/*70.4*/("""
		
		"""),format.raw/*72.3*/("""if(diffDias > 92)"""),format.raw/*72.20*/("""{"""),format.raw/*72.21*/("""
			"""),format.raw/*73.4*/("""flag = false;
			alertify.alert('EL PERIODO NO PUEDE SER MAYOR A 92 DIAS', function () """),format.raw/*74.74*/("""{"""),format.raw/*74.75*/("""
				"""),format.raw/*75.5*/("""return(flag);
     		"""),format.raw/*76.8*/("""}"""),format.raw/*76.9*/(""");
		"""),format.raw/*77.3*/("""}"""),format.raw/*77.4*/("""
		"""),format.raw/*78.3*/("""return(flag);
	"""),format.raw/*79.2*/("""}"""),format.raw/*79.3*/("""
	
	
"""),format.raw/*82.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuMantencion/mantListarReports0.scala.html
                  HASH: 5c408d25c4d0083d32bc7af64459bbbe8538f629
                  MATRIX: 1034->1|1264->138|1296->145|1312->153|1351->155|1380->159|1448->207|1476->209|1552->260|1632->320|1661->323|2287->922|2318->932|2587->1174|2618->1184|3030->1566|3060->1569|3150->1631|3179->1632|3211->1637|3304->1703|3332->1704|3391->1735|3420->1736|3450->1739|3726->1987|3755->1988|3786->1992|3914->2092|3943->2093|3975->2098|4023->2119|4051->2120|4083->2125|4111->2126|4144->2132|4189->2149|4218->2150|4249->2154|4364->2241|4393->2242|4425->2247|4473->2268|4501->2269|4533->2274|4561->2275|4591->2278|4633->2293|4661->2294|4693->2299
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|55->24|55->24|65->34|65->34|83->52|86->55|87->56|87->56|88->57|89->58|89->58|91->60|91->60|92->61|96->65|96->65|97->66|98->67|98->67|99->68|100->69|100->69|101->70|101->70|103->72|103->72|103->72|104->73|105->74|105->74|106->75|107->76|107->76|108->77|108->77|109->78|110->79|110->79|113->82
                  -- GENERATED --
              */
          