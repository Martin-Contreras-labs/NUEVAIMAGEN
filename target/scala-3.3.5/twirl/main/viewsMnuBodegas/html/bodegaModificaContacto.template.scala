
package viewsMnuBodegas.html

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

object bodegaModificaContacto extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.ContactoBodegaEmpresa],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listContactos: List[tables.ContactoBodegaEmpresa]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MANTENCION DE CONTACTOS DE "+mapDiccionario.get("BODEGA")+"/PROYECTO", "(CREAR/MODIFICAR/ELIMINAR)")),format.raw/*9.133*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">

							<table class="table table-sm table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<tr>
										<th colspan="10" style="text-align:left">
											<font color="#008000"> """),_display_(/*17.36*/mapDiccionario/*17.50*/.get("BODEGA")),format.raw/*17.64*/("""/PROYECTO: """),_display_(/*17.76*/bodega/*17.82*/.getNombre().toUpperCase()),format.raw/*17.108*/(""" """),format.raw/*17.109*/("""</font>
										</th>
									</tr>
									<tr>
										<TH>NOMBRE</TH>
										<TH>CARGO</TH>
										<TH>TELEFONO<br>FIJO</TH>
										<TH>TELEFONO<br>MOVIL</TH>
										<TH>E-MAIL</TH>
										<TH>Edit</TH>
										<TH>Del</TH>
									</tr>
								</thead>
								<tbody>
									"""),_display_(/*31.11*/for(lista <- listContactos) yield /*31.38*/{_display_(Seq[Any](format.raw/*31.39*/("""
										"""),format.raw/*32.11*/("""<tr>
									        <td style="text-align:left;">"""),_display_(/*33.48*/lista/*33.53*/.getNombre()),format.raw/*33.65*/("""</td>
											<td style="text-align:left;">"""),_display_(/*34.42*/lista/*34.47*/.getCargo()),format.raw/*34.58*/("""</td>
											<td style="text-align:left;">"""),_display_(/*35.42*/lista/*35.47*/.getFijo()),format.raw/*35.57*/("""</td>
											<td style="text-align:left;">"""),_display_(/*36.42*/lista/*36.47*/.getMovil()),format.raw/*36.58*/("""</td>
											<td style="text-align:left;">"""),_display_(/*37.42*/lista/*37.47*/.getMail()),format.raw/*37.57*/("""</td>
											<td  style="text-align:center;" width="10%">
												<a href="/bodegaContactoModifica/"""),_display_(/*39.47*/lista/*39.52*/.getId()),format.raw/*39.60*/(""","""),_display_(/*39.62*/bodega/*39.68*/.getId()),format.raw/*39.76*/("""">
													<kbd style="background-color: #73C6B6">E</kbd>
												</a>
											</td>
											<td  style="text-align:center;">
												<a href="#" onclick= "eliminarContacto("""),_display_(/*44.53*/lista/*44.58*/.getId()),format.raw/*44.66*/(""")">
													<kbd style="background-color: red">X</kbd>
												</a>
											</td>
										</tr>
									""")))}),format.raw/*49.11*/("""
									"""),format.raw/*50.10*/("""<td colspan=7>
										<div align="center">
											<input type="button" class="btn btn-light btn-sm rounded-pill" 
												onclick="location.href='/bodegaContactoAgrega/"""),_display_(/*53.60*/bodega/*53.66*/.getId()),format.raw/*53.74*/("""'"
												value = "Agregar Contacto">
										</div>
									</td>
								</tbody>
							</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/bodegaContactos/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*70.2*/("""



"""),format.raw/*74.1*/("""<script type="text/javascript">
	let id_bodega = """),_display_(/*75.19*/bodega/*75.25*/.getId()),format.raw/*75.33*/(""";

	$(document).ready(function() """),format.raw/*77.31*/("""{"""),format.raw/*77.32*/("""
		  """),format.raw/*78.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*79.2*/("""}"""),format.raw/*79.3*/(""");
	
	const eliminarContacto = (id_contacto) => """),format.raw/*81.44*/("""{"""),format.raw/*81.45*/("""
		"""),format.raw/*82.3*/("""alertify.confirm("Esta seguro de eliminar el contacto", function (e) """),format.raw/*82.72*/("""{"""),format.raw/*82.73*/("""
			"""),format.raw/*83.4*/("""if (e) """),format.raw/*83.11*/("""{"""),format.raw/*83.12*/("""
				"""),format.raw/*84.5*/("""location.href = "/bodegaContactoElimina/" + id_contacto + "," + id_bodega;
			"""),format.raw/*85.4*/("""}"""),format.raw/*85.5*/("""
		"""),format.raw/*86.3*/("""}"""),format.raw/*86.4*/(""");
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/("""

"""),format.raw/*89.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listContactos:List[tables.ContactoBodegaEmpresa]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listContactos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.ContactoBodegaEmpresa]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listContactos) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listContactos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/bodegaModificaContacto.scala.html
                  HASH: 44ab51c530f69644f568cffb55ed0546bb897b6f
                  MATRIX: 1077->1|1348->179|1381->187|1397->195|1436->197|1464->200|1532->248|1560->250|1636->301|1786->430|1816->433|2178->768|2201->782|2236->796|2275->808|2290->814|2338->840|2368->841|2703->1149|2746->1176|2785->1177|2824->1188|2903->1240|2917->1245|2950->1257|3024->1304|3038->1309|3070->1320|3144->1367|3158->1372|3189->1382|3263->1429|3277->1434|3309->1445|3383->1492|3397->1497|3428->1507|3563->1615|3577->1620|3606->1628|3635->1630|3650->1636|3679->1644|3899->1837|3913->1842|3942->1850|4093->1970|4131->1980|4338->2160|4353->2166|4382->2174|4846->2608|4877->2612|4954->2662|4969->2668|4998->2676|5059->2709|5088->2710|5120->2715|5213->2781|5241->2782|5317->2830|5346->2831|5376->2834|5473->2903|5502->2904|5533->2908|5568->2915|5597->2916|5629->2921|5734->2999|5762->3000|5792->3003|5820->3004|5851->3008|5879->3009|5908->3011
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|48->17|48->17|48->17|48->17|48->17|48->17|48->17|62->31|62->31|62->31|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|68->37|68->37|68->37|70->39|70->39|70->39|70->39|70->39|70->39|75->44|75->44|75->44|80->49|81->50|84->53|84->53|84->53|101->70|105->74|106->75|106->75|106->75|108->77|108->77|109->78|110->79|110->79|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|115->84|116->85|116->85|117->86|117->86|118->87|118->87|120->89
                  -- GENERATED --
              */
          