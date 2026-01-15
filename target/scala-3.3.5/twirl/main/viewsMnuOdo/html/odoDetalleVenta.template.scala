
package viewsMnuOdo.html

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

object odoDetalleVenta extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.VentaServicio,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
ventaServicio: tables.VentaServicio, desdeAAMMDD: String, hastaAAMMDD: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

 """),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/(""" 
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "DETALLE DE REPORT DIARIO", "")),format.raw/*9.63*/(""" 
		
		"""),format.raw/*11.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
				<br>
				<div align=center><h4 style="color:blue"><strong>DETALLE - REPORT DIARIO</strong></h4></div>
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead>
							<tr>
								<td style="text-align:left;"><strong>FECHA (*):</strong></td>
						        <td style="text-align:left;" width="70%" colspan="3">"""),_display_(/*19.69*/ventaServicio/*19.82*/.getFecha()),format.raw/*19.93*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>USER_MADA:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*23.68*/ventaServicio/*23.81*/.getNomUserAdam()),format.raw/*23.98*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>OPERADOR:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*27.68*/ventaServicio/*27.81*/.getNomOperador()),format.raw/*27.98*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>"""),_display_(/*30.47*/mapDiccionario/*30.61*/.get("BODEGA")),format.raw/*30.75*/(""" """),format.raw/*30.76*/("""(*):</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*31.68*/ventaServicio/*31.81*/.getNomBodegaEmpresa()),format.raw/*31.103*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>SERVICIO (*):</strong></td>
								<td style="text-align:left" width="70%" colspan="3">"""),_display_(/*35.62*/ventaServicio/*35.75*/.getNroCotiOdo()),format.raw/*35.91*/(""" """),format.raw/*35.92*/("""- """),_display_(/*35.95*/ventaServicio/*35.108*/.getCodServicio()),format.raw/*35.125*/(""" """),format.raw/*35.126*/("""- """),_display_(/*35.129*/ventaServicio/*35.142*/.getNomServicio()),format.raw/*35.159*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>EQUIPO:</strong></td>
								<td style="text-align:left" width="70%" colspan="3">"""),_display_(/*39.62*/ventaServicio/*39.75*/.getCodEquipo()),format.raw/*39.90*/(""" """),format.raw/*39.91*/("""- """),_display_(/*39.94*/ventaServicio/*39.107*/.getNomEquipo()),format.raw/*39.122*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>Hr_INI:</strong></td>
						        <td style="text-align:left">"""),_display_(/*44.44*/ventaServicio/*44.57*/.getHoraIni()),format.raw/*44.70*/("""</td>
								<td style="text-align:left;"><strong>Hr_TER:</strong></td>
						        <td style="text-align:left">"""),_display_(/*46.44*/ventaServicio/*46.57*/.getHoraTer()),format.raw/*46.70*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong>Lect_INI:</strong></td>
						        <td style="text-align:left">"""),_display_(/*51.44*/ventaServicio/*51.57*/.getLecturaIni()),format.raw/*51.73*/("""</td>
								<td style="text-align:left;"><strong>Lect_TER:</strong></td>
						        <td style="text-align:left">"""),_display_(/*53.44*/ventaServicio/*53.57*/.getLecturaTer()),format.raw/*53.73*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"><strong><div id="unidad">CANT (*)</div></strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*58.68*/ventaServicio/*58.81*/.getCantidad()),format.raw/*58.95*/(""" """),format.raw/*58.96*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>DETALLE:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*62.68*/ventaServicio/*62.81*/.getDetalle()),format.raw/*62.94*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;"><strong>NOTAS:</strong></td>
						        <td style="text-align:left" width="70%" colspan="3">"""),_display_(/*66.68*/ventaServicio/*66.81*/.getObservaciones()),format.raw/*66.100*/("""</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"  colspan="4">
									<input type="hidden" name="firmaPDFoperador" id="firma" value="0">
									FIRMA OPERADOR:<br>
									<img src="data:image/jpeg;base64, """),_display_(/*73.45*/ventaServicio/*73.58*/.getFirmaPDFoperador()),format.raw/*73.80*/("""" />
								</td>
							</tr>
							
							<tr>
								<td style="text-align:left;"   colspan="4">
									FIRMA AUTORIZADA:<br>
									<img src="data:image/jpeg;base64, """),_display_(/*80.45*/ventaServicio/*80.58*/.getFirmaPDFautorizador()),format.raw/*80.83*/("""" />
								</td>
							</tr>
						
							<tr>
								<td colspan="4" style="text-align:center;">
									<form action="/odoListarVentas1/" method="POST">
										<input type="hidden" name="fechaDesde" value=""""),_display_(/*87.58*/desdeAAMMDD),format.raw/*87.69*/("""">
										<input type="hidden" name="fechaHasta" value=""""),_display_(/*88.58*/hastaAAMMDD),format.raw/*88.69*/("""">
										<button type="submit" class="btn btn-sm btn-success" style="font-size: 10px;">Listo</button>
									</form>
								</td>
							</tr>
						</thead>
					</table>
			</div>
		</div>

		
	</div>
""")))}),format.raw/*100.2*/("""


	
"""),format.raw/*104.1*/("""<script>


	
	$(document).ready(function() """),format.raw/*108.31*/("""{"""),format.raw/*108.32*/("""
 		"""),format.raw/*109.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	
	"""),format.raw/*111.2*/("""}"""),format.raw/*111.3*/(""" """),format.raw/*111.4*/(""");
	
	
	
	
		
		
		
</script>
	

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,ventaServicio:tables.VentaServicio,desdeAAMMDD:String,hastaAAMMDD:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,ventaServicio,desdeAAMMDD,hastaAAMMDD)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.VentaServicio,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,ventaServicio,desdeAAMMDD,hastaAAMMDD) => apply(mapDiccionario,mapPermiso,userMnu,ventaServicio,desdeAAMMDD,hastaAAMMDD)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/odoDetalleVenta.scala.html
                  HASH: 89b354d94df3d37752489e990fe90f1aeb34da4c
                  MATRIX: 1045->1|1314->177|1347->185|1363->193|1402->195|1431->199|1499->247|1528->250|1604->301|1683->360|1717->367|2193->816|2215->829|2247->840|2442->1008|2464->1021|2502->1038|2696->1205|2718->1218|2756->1235|2860->1312|2883->1326|2918->1340|2947->1341|3060->1427|3082->1440|3126->1462|3318->1627|3340->1640|3377->1656|3406->1657|3436->1660|3459->1673|3498->1690|3528->1691|3559->1694|3582->1707|3621->1724|3807->1883|3829->1896|3865->1911|3894->1912|3924->1915|3947->1928|3984->1943|4160->2092|4182->2105|4216->2118|4359->2234|4381->2247|4415->2260|4593->2411|4615->2424|4652->2440|4797->2558|4819->2571|4856->2587|5080->2784|5102->2797|5137->2811|5166->2812|5359->2978|5381->2991|5415->3004|5606->3168|5628->3181|5669->3200|5935->3439|5957->3452|6000->3474|6206->3653|6228->3666|6274->3691|6519->3909|6551->3920|6638->3980|6670->3991|6912->4202|6945->4207|7017->4250|7047->4251|7079->4255|7175->4323|7204->4324|7233->4325
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|42->11|50->19|50->19|50->19|54->23|54->23|54->23|58->27|58->27|58->27|61->30|61->30|61->30|61->30|62->31|62->31|62->31|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|66->35|70->39|70->39|70->39|70->39|70->39|70->39|70->39|75->44|75->44|75->44|77->46|77->46|77->46|82->51|82->51|82->51|84->53|84->53|84->53|89->58|89->58|89->58|89->58|93->62|93->62|93->62|97->66|97->66|97->66|104->73|104->73|104->73|111->80|111->80|111->80|118->87|118->87|119->88|119->88|131->100|135->104|139->108|139->108|140->109|142->111|142->111|142->111
                  -- GENERATED --
              */
          