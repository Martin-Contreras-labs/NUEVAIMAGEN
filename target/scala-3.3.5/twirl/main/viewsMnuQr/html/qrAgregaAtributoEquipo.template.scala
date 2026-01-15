
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

object qrAgregaAtributoEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[qr.QrTipoContenido],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaTipoContenido: List[qr.QrTipoContenido]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "NUEVO ATRIBUTO PARA ASOCIAR AL QR -- CODIFICACION QR","CREAR NUEVO")),format.raw/*9.101*/("""
		"""),format.raw/*10.3*/("""<form name="inicio" id="inicio" action="/qrAgregaAtributoEquipo2/" method="POST">
			<div class="row  justify-content-md-center">
				<div class="col-xs-10 col-sm-6 col-md-6 col-lg-6">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<tbody>
							<TR>
								<td style="text-align: left;">NOMBRE</td>
								<td style="text-align:left">
									<input type="text" class="form-control left"
											name="nombre"
											autocomplete="off"
											maxlength="100"
											onchange="value = value.trim()"
											required>
						        </td>
							</TR>
							<TR>
						        <td style="text-align:left;">TIPO:</td>
						        <td style="text-align:left">
									<select class="custom-select" 
										name="idTipo" 
										onchange="cambiaObservacion(value)"
										required>
										"""),_display_(/*33.12*/for(lista <- listaTipoContenido) yield /*33.44*/{_display_(Seq[Any](format.raw/*33.45*/("""
											"""),format.raw/*34.12*/("""<option value=""""),_display_(/*34.28*/lista/*34.33*/.id),format.raw/*34.36*/("""">"""),_display_(/*34.39*/lista/*34.44*/.tipo),format.raw/*34.49*/("""</option>
										""")))}),format.raw/*35.12*/("""
									"""),format.raw/*36.10*/("""</select>
						        </td>
							</TR>
							<TR>
						        <td style="text-align:left;">OBSERVACION:</td>
						        <td style="text-align:left;">
									<div id="observacion">"""),_display_(/*42.33*/listaTipoContenido/*42.51*/.get(0).observaciones),format.raw/*42.72*/("""</div>
								</td>
							</TR>
						</tbody>
					</table>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-5 col-sm-4 col-md-4 col-lg-4">
								<input type="submit"  value="GRABAR NUEVO" class="btn btn-success btn-sm rounded-pill btn-block">
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


""")))}),format.raw/*63.2*/("""




"""),format.raw/*68.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*69.31*/("""{"""),format.raw/*69.32*/("""
		  """),format.raw/*70.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*71.2*/("""}"""),format.raw/*71.3*/(""");
	
	const cambiaObservacion = (idTipo) =>"""),format.raw/*73.39*/("""{"""),format.raw/*73.40*/("""
		  """),_display_(/*74.6*/for(lista <- listaTipoContenido) yield /*74.38*/{_display_(Seq[Any](format.raw/*74.39*/("""
			  """),format.raw/*75.6*/("""if("""),_display_(/*75.10*/lista/*75.15*/.id),format.raw/*75.18*/("""==idTipo.trim())"""),format.raw/*75.34*/("""{"""),format.raw/*75.35*/("""
				  """),format.raw/*76.7*/("""document.getElementById('observacion').innerHTML = """"),_display_(/*76.60*/lista/*76.65*/.observaciones),format.raw/*76.79*/("""";
			  """),format.raw/*77.6*/("""}"""),format.raw/*77.7*/("""
		  """)))}),format.raw/*78.6*/("""
	"""),format.raw/*79.2*/("""}"""),format.raw/*79.3*/("""
	
	
"""),format.raw/*82.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaTipoContenido:List[qr.QrTipoContenido]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaTipoContenido)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[qr.QrTipoContenido]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaTipoContenido) => apply(mapDiccionario,mapPermiso,userMnu,listaTipoContenido)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/qrAgregaAtributoEquipo.scala.html
                  HASH: 5f35df0d7a2320c6681495a6aae77f0716696ea5
                  MATRIX: 1041->1|1277->144|1310->152|1326->160|1365->162|1393->165|1461->213|1489->215|1565->266|1683->363|1713->366|2633->1259|2681->1291|2720->1292|2760->1304|2803->1320|2817->1325|2841->1328|2871->1331|2885->1336|2911->1341|2963->1362|3001->1372|3220->1564|3247->1582|3289->1603|3927->2211|3959->2216|4049->2278|4078->2279|4110->2284|4203->2350|4231->2351|4302->2394|4331->2395|4363->2401|4411->2433|4450->2434|4483->2440|4514->2444|4528->2449|4552->2452|4596->2468|4625->2469|4659->2476|4739->2529|4753->2534|4788->2548|4823->2556|4851->2557|4887->2563|4916->2565|4944->2566|4976->2571
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|64->33|64->33|64->33|65->34|65->34|65->34|65->34|65->34|65->34|65->34|66->35|67->36|73->42|73->42|73->42|94->63|99->68|100->69|100->69|101->70|102->71|102->71|104->73|104->73|105->74|105->74|105->74|106->75|106->75|106->75|106->75|106->75|106->75|107->76|107->76|107->76|107->76|108->77|108->77|109->78|110->79|110->79|113->82
                  -- GENERATED --
              */
          