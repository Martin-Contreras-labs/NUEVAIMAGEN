
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

object qrSelectEquipos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[qr.QrEquipo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listEquipQr: List[qr.QrEquipo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "SELECCION EQUIPOS NO INSCRITOS -- CODIFICACION QR","SELECCIONAR")),format.raw/*9.98*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-10 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<th>GRUPO</th>
							<th>CODIGO</th>
							<th>NOMBRE/DESCRIPCION</th>
							<th>SELECT</th>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*22.8*/for(lista <- listEquipQr) yield /*22.33*/{_display_(Seq[Any](format.raw/*22.34*/("""
							"""),format.raw/*23.8*/("""<TR>
								<td style="text-align: left;">"""),_display_(/*24.40*/lista/*24.45*/.grupo),format.raw/*24.51*/("""</td>
								<td style="text-align: left;">"""),_display_(/*25.40*/lista/*25.45*/.codigo),format.raw/*25.52*/("""</td>
						        <td style="text-align:left">"""),_display_(/*26.44*/lista/*26.49*/.nombre),format.raw/*26.56*/("""</td>
						        <td style="text-align:center">
									<form id="form_"""),_display_(/*28.26*/lista/*28.31*/.id_equipo),format.raw/*28.41*/("""" method="post" action="/qrAgregaEquipo/">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*29.57*/lista/*29.62*/.id_equipo),format.raw/*29.72*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*30.63*/lista/*30.68*/.id_equipo),format.raw/*30.78*/("""').submit()">
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</form>
					        	</td>
							</TR>
						""")))}),format.raw/*36.8*/("""
					"""),format.raw/*37.6*/("""</tbody>
				</table>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="VOLVER" class="btn btn-success btn-sm rounded-pill btn-block" onclick="window.history.back()">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	

	
""")))}),format.raw/*53.2*/("""




"""),format.raw/*58.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*59.31*/("""{"""),format.raw/*59.32*/("""
		  """),format.raw/*60.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*60.36*/("""{"""),format.raw/*60.37*/("""
		    	"""),format.raw/*61.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*63.20*/("""{"""),format.raw/*63.21*/("""
		        	"""),format.raw/*64.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*65.11*/("""}"""),format.raw/*65.12*/("""
		  """),format.raw/*66.5*/("""}"""),format.raw/*66.6*/(""" """),format.raw/*66.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*68.2*/("""}"""),format.raw/*68.3*/(""");
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listEquipQr:List[qr.QrEquipo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listEquipQr)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[qr.QrEquipo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listEquipQr) => apply(mapDiccionario,mapPermiso,userMnu,listEquipQr)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/qrSelectEquipos.scala.html
                  HASH: 18875bbcb6ec0d800a1467187b9c4d7576e32b1a
                  MATRIX: 1027->1|1249->130|1282->138|1298->146|1337->148|1365->151|1433->199|1461->201|1537->252|1651->346|1681->349|2123->765|2164->790|2203->791|2238->799|2309->843|2323->848|2350->854|2422->899|2436->904|2464->911|2540->960|2554->965|2582->972|2685->1048|2699->1053|2730->1063|2856->1162|2870->1167|2901->1177|2993->1242|3007->1247|3038->1257|3217->1406|3250->1412|3636->1768|3668->1773|3758->1835|3787->1836|3819->1841|3878->1872|3907->1873|3942->1881|4084->1995|4113->1996|4153->2008|4259->2086|4288->2087|4320->2092|4348->2093|4376->2094|4476->2167|4504->2168
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|53->22|54->23|55->24|55->24|55->24|56->25|56->25|56->25|57->26|57->26|57->26|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|67->36|68->37|84->53|89->58|90->59|90->59|91->60|91->60|91->60|92->61|94->63|94->63|95->64|96->65|96->65|97->66|97->66|97->66|99->68|99->68
                  -- GENERATED --
              */
          