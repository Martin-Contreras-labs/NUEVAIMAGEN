
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

object importaHojaVidaIconstruye0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[List[String]],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, listIdOrgc: List[List[String]], id_equipo: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "PROCESO IMPORTAR MANTENCIONES (DESDE ICONSTRUYE)","SELECCIONAR CENTRO DE GESTION")),format.raw/*8.115*/("""
		"""),format.raw/*9.3*/("""<form action="/importaHojaVidaIconstruye1/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">PERIODO A CONSIDERAR<br>(no mayor a 31 días)</TH>
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
							<TR>
								<TH>CENTRO GESTION</TH>
								<td>
									 <select class="custom-select" style="min-width:120px" id="pais" name="idOrgc" required>
									  	"""),_display_(/*42.14*/for(lista <- listIdOrgc) yield /*42.38*/{_display_(Seq[Any](format.raw/*42.39*/("""
									  		"""),format.raw/*43.14*/("""<option value=""""),_display_(/*43.30*/lista/*43.35*/.get(0)),format.raw/*43.42*/("""">"""),_display_(/*43.45*/lista/*43.50*/.get(1)),format.raw/*43.57*/(""" """),format.raw/*43.58*/("""- """),_display_(/*43.61*/lista/*43.66*/.get(2)),format.raw/*43.73*/("""</option>
									  	""")))}),format.raw/*44.14*/("""
									  """),format.raw/*45.12*/("""</select>
									  <input type="hidden" name="id_equipo" value=""""),_display_(/*46.58*/id_equipo),format.raw/*46.67*/("""">
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
					<input type="submit" value='Obtener OC' class="btn btn-primary btn-sm rounded-pill btn-block">
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*63.2*/("""


"""),format.raw/*66.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*67.31*/("""{"""),format.raw/*67.32*/("""
		  """),format.raw/*68.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*69.2*/("""}"""),format.raw/*69.3*/(""");
	
	const validarForm = () =>"""),format.raw/*71.27*/("""{"""),format.raw/*71.28*/("""
		"""),format.raw/*72.3*/("""let flag = true;
		var desde = Date.parse(document.getElementById('fechaDesde').value);
		var hasta = Date.parse(document.getElementById('fechaHasta').value);
		var diffDias = Math.floor((hasta - desde)/(1000 * 60 * 60 * 24))+1;
		
		if(desde > hasta)"""),format.raw/*77.20*/("""{"""),format.raw/*77.21*/("""
			"""),format.raw/*78.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*79.87*/("""{"""),format.raw/*79.88*/("""
				"""),format.raw/*80.5*/("""return(flag);
     		"""),format.raw/*81.8*/("""}"""),format.raw/*81.9*/(""");
		"""),format.raw/*82.3*/("""}"""),format.raw/*82.4*/("""
		
		"""),format.raw/*84.3*/("""if(diffDias > 31)"""),format.raw/*84.20*/("""{"""),format.raw/*84.21*/("""
			"""),format.raw/*85.4*/("""flag = false;
			alertify.alert('EL PERIODO NO PUEDE SER MAYOR A 31 DIAS', function () """),format.raw/*86.74*/("""{"""),format.raw/*86.75*/("""
				"""),format.raw/*87.5*/("""return(flag);
     		"""),format.raw/*88.8*/("""}"""),format.raw/*88.9*/(""");
		"""),format.raw/*89.3*/("""}"""),format.raw/*89.4*/("""
		
		"""),format.raw/*91.3*/("""return(flag);
	"""),format.raw/*92.2*/("""}"""),format.raw/*92.3*/("""
	
	
	
"""),format.raw/*96.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String,listIdOrgc:List[List[String]],id_equipo:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,listIdOrgc,id_equipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[List[String]],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,listIdOrgc,id_equipo) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,listIdOrgc,id_equipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/importaHojaVidaIconstruye0.scala.html
                  HASH: 8bf1a71739f225f4f9c1990a64571bdfebeaebea
                  MATRIX: 1062->1|1341->187|1373->194|1389->202|1428->204|1457->208|1525->256|1553->258|1629->309|1761->420|1790->423|2440->1046|2471->1056|2740->1298|2771->1308|3021->1531|3061->1555|3100->1556|3142->1570|3185->1586|3199->1591|3227->1598|3257->1601|3271->1606|3299->1613|3328->1614|3358->1617|3372->1622|3400->1629|3454->1652|3494->1664|3588->1731|3618->1740|4000->2092|4030->2095|4120->2157|4149->2158|4181->2163|4274->2229|4302->2230|4361->2261|4390->2262|4420->2265|4699->2516|4728->2517|4759->2521|4887->2621|4916->2622|4948->2627|4996->2648|5024->2649|5056->2654|5084->2655|5117->2661|5162->2678|5191->2679|5222->2683|5337->2770|5366->2771|5398->2776|5446->2797|5474->2798|5506->2803|5534->2804|5567->2810|5609->2825|5637->2826|5671->2833
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|55->24|55->24|65->34|65->34|73->42|73->42|73->42|74->43|74->43|74->43|74->43|74->43|74->43|74->43|74->43|74->43|74->43|74->43|75->44|76->45|77->46|77->46|94->63|97->66|98->67|98->67|99->68|100->69|100->69|102->71|102->71|103->72|108->77|108->77|109->78|110->79|110->79|111->80|112->81|112->81|113->82|113->82|115->84|115->84|115->84|116->85|117->86|117->86|118->87|119->88|119->88|120->89|120->89|122->91|123->92|123->92|127->96
                  -- GENERATED --
              */
          