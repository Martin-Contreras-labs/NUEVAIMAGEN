
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

object qrEditarAtributoEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,qr.QrAtributoEquipo,List[qr.QrTipoContenido],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
atributoEquipo: qr.QrAtributoEquipo, listaTipoContenido: List[qr.QrTipoContenido], bloquearTipo: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR ATRIBUTO PARA ASOCIAR AL QR -- CODIFICACION QR","MODIFICAR")),format.raw/*9.103*/("""
		"""),format.raw/*10.3*/("""<form name="inicio" id="inicio" action="/qrEditarAtributoEquipo2/" method="POST">
			<div class="row  justify-content-md-center">
				<div class="col-xs-10 col-sm-6 col-md-6 col-lg-6">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<tbody>
							<TR>
								<td style="text-align: left;">NOMBRE</td>
								<td style="text-align:left">
						        	<input type="hidden" name="idQrAtributoEquipo" value=""""),_display_(/*18.71*/atributoEquipo/*18.85*/.id),format.raw/*18.88*/("""">
									<input type="text" class="form-control left"
											name="nombre"
											autocomplete="off"
											value=""""),_display_(/*22.20*/atributoEquipo/*22.34*/.nombre),format.raw/*22.41*/(""""
											maxlength="100"
											onchange="value = value.trim()"
											required>
						        </td>
							</TR>
							<TR>
						        <td style="text-align:left;">TIPO:</td>
						        <td style="text-align:left">
						        	"""),_display_(if(bloquearTipo==0)/*31.36*/{_display_(Seq[Any](format.raw/*31.37*/("""
										"""),format.raw/*32.11*/("""<select class="custom-select" 
											name="idTipo" 
											onchange="cambiaObservacion(value)"
											required>
											"""),_display_(/*36.13*/for(lista <- listaTipoContenido) yield /*36.45*/{_display_(Seq[Any](format.raw/*36.46*/("""
												"""),format.raw/*37.13*/("""<option value=""""),_display_(/*37.29*/lista/*37.34*/.id),format.raw/*37.37*/("""">"""),_display_(/*37.40*/lista/*37.45*/.tipo),format.raw/*37.50*/("""</option>
											""")))}),format.raw/*38.13*/("""
										"""),format.raw/*39.11*/("""</select>
							        """)))}else/*40.21*/{_display_(Seq[Any](format.raw/*40.22*/("""
							        	"""),format.raw/*41.17*/("""<select class="custom-select" name="idTipo" readonly required>
							        		<option value=""""),_display_(/*42.34*/atributoEquipo/*42.48*/.id_qrTipoContenido),format.raw/*42.67*/("""" >"""),_display_(/*42.71*/atributoEquipo/*42.85*/.tipoContenido),format.raw/*42.99*/("""</option>
							        	</select>
							        """)))}),format.raw/*44.17*/("""
						        """),format.raw/*45.15*/("""</td>
							</TR>
							<TR>
						        <td style="text-align:left;">OBSERVACION:</td>
						        <td style="text-align:left;">
									<div id="observacion">"""),_display_(/*50.33*/listaTipoContenido/*50.51*/.get(0).observaciones),format.raw/*50.72*/("""</div>
								</td>
							</TR>
						</tbody>
					</table>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-5 col-sm-4 col-md-4 col-lg-4">
								<input type="submit"  value="GRABAR CAMBIOS" class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-5 col-sm-3 col-md-3 col-lg-3">
								<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/qrListaAtributoEquipos/';">
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>


""")))}),format.raw/*71.2*/("""




"""),format.raw/*76.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*77.31*/("""{"""),format.raw/*77.32*/("""
		  """),format.raw/*78.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*79.2*/("""}"""),format.raw/*79.3*/(""");
	
	const cambiaObservacion = (idTipo) =>"""),format.raw/*81.39*/("""{"""),format.raw/*81.40*/("""
		  """),_display_(/*82.6*/for(lista <- listaTipoContenido) yield /*82.38*/{_display_(Seq[Any](format.raw/*82.39*/("""
			  """),format.raw/*83.6*/("""if("""),_display_(/*83.10*/lista/*83.15*/.id),format.raw/*83.18*/("""==idTipo.trim())"""),format.raw/*83.34*/("""{"""),format.raw/*83.35*/("""
				  """),format.raw/*84.7*/("""document.getElementById('observacion').innerHTML = """"),_display_(/*84.60*/lista/*84.65*/.observaciones),format.raw/*84.79*/("""";
			  """),format.raw/*85.6*/("""}"""),format.raw/*85.7*/("""
		  """)))}),format.raw/*86.6*/("""
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/("""
	
	
"""),format.raw/*90.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,atributoEquipo:qr.QrAtributoEquipo,listaTipoContenido:List[qr.QrTipoContenido],bloquearTipo:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,atributoEquipo,listaTipoContenido,bloquearTipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,qr.QrAtributoEquipo,List[qr.QrTipoContenido],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,atributoEquipo,listaTipoContenido,bloquearTipo) => apply(mapDiccionario,mapPermiso,userMnu,atributoEquipo,listaTipoContenido,bloquearTipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/qrEditarAtributoEquipo.scala.html
                  HASH: 9ec5ccf886177960a0ea35f9d7cbfb89ec9d0cd7
                  MATRIX: 1066->1|1359->201|1392->209|1408->217|1447->219|1475->222|1543->270|1571->272|1647->323|1767->422|1797->425|2303->904|2326->918|2350->921|2508->1052|2531->1066|2559->1073|2856->1343|2895->1344|2934->1355|3098->1492|3146->1524|3185->1525|3226->1538|3269->1554|3283->1559|3307->1562|3337->1565|3351->1570|3377->1575|3430->1597|3469->1608|3518->1638|3557->1639|3602->1656|3725->1752|3748->1766|3788->1785|3819->1789|3842->1803|3877->1817|3960->1869|4003->1884|4198->2052|4225->2070|4267->2091|4907->2701|4939->2706|5029->2768|5058->2769|5090->2774|5183->2840|5211->2841|5282->2884|5311->2885|5343->2891|5391->2923|5430->2924|5463->2930|5494->2934|5508->2939|5532->2942|5576->2958|5605->2959|5639->2966|5719->3019|5733->3024|5768->3038|5803->3046|5831->3047|5867->3053|5896->3055|5924->3056|5956->3061
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|49->18|49->18|49->18|53->22|53->22|53->22|62->31|62->31|63->32|67->36|67->36|67->36|68->37|68->37|68->37|68->37|68->37|68->37|68->37|69->38|70->39|71->40|71->40|72->41|73->42|73->42|73->42|73->42|73->42|73->42|75->44|76->45|81->50|81->50|81->50|102->71|107->76|108->77|108->77|109->78|110->79|110->79|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|114->83|114->83|114->83|115->84|115->84|115->84|115->84|116->85|116->85|117->86|118->87|118->87|121->90
                  -- GENERATED --
              */
          