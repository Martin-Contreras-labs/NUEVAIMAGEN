
package viewsMnuToolsAdmin.html

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

object administraModulos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Parametros],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listado: List[tables.Parametros]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "ACTIVAR/DESACTIVAR MODULOS","update parametros set valor = 0/1 where id = (XX);")),format.raw/*9.114*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
			        		<th style="text-align:center;">ID</th>
			        		<th style="text-align:center;">MODULO</th>
			        		<th style="text-align:center;">DESCRIPCION</th>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*21.8*/for(lista <- listado) yield /*21.29*/{_display_(Seq[Any](format.raw/*21.30*/("""
							"""),format.raw/*22.8*/("""<TR>
								<td style="text-align:center;">"""),_display_(/*23.41*/lista/*23.46*/.id),format.raw/*23.49*/("""</td>
								<td style="text-align:left;min-width:300px;">
									"""),_display_(if(lista.valor==1 && lista.esModificable==1)/*25.55*/{_display_(Seq[Any](format.raw/*25.56*/("""
										"""),format.raw/*26.11*/("""<input type="checkbox" id=""""),_display_(/*26.39*/lista/*26.44*/.nombre),format.raw/*26.51*/("""" onchange="cambiar(id)" checked> """),_display_(/*26.86*/lista/*26.91*/.nombre),format.raw/*26.98*/("""<br>
									""")))} else {null} ),format.raw/*27.11*/("""
									"""),_display_(if(lista.valor==0 && lista.esModificable==1)/*28.55*/{_display_(Seq[Any](format.raw/*28.56*/("""
										"""),format.raw/*29.11*/("""<input type="checkbox" id=""""),_display_(/*29.39*/lista/*29.44*/.nombre),format.raw/*29.51*/("""" onchange="cambiar(id)"> """),_display_(/*29.78*/lista/*29.83*/.nombre),format.raw/*29.90*/("""<br>
									""")))} else {null} ),format.raw/*30.11*/("""
									"""),_display_(if(lista.valor==1 && lista.esModificable==0)/*31.55*/{_display_(Seq[Any](format.raw/*31.56*/("""
										"""),format.raw/*32.11*/("""<input type="checkbox" id=""""),_display_(/*32.39*/lista/*32.44*/.nombre),format.raw/*32.51*/("""" onchange="cambiar(id)" checked disabled> """),_display_(/*32.95*/lista/*32.100*/.nombre),format.raw/*32.107*/("""<br>
									""")))} else {null} ),format.raw/*33.11*/("""
									"""),_display_(if(lista.valor==0 && lista.esModificable==0)/*34.55*/{_display_(Seq[Any](format.raw/*34.56*/("""
										"""),format.raw/*35.11*/("""<input type="checkbox" id=""""),_display_(/*35.39*/lista/*35.44*/.nombre),format.raw/*35.51*/("""" onchange="cambiar(id)" disabled> """),_display_(/*35.87*/lista/*35.92*/.nombre),format.raw/*35.99*/("""<br>
									""")))} else {null} ),format.raw/*36.11*/("""
				        		"""),format.raw/*37.15*/("""</td>
				        		<td style="text-align:left;">"""),_display_(/*38.45*/lista/*38.50*/.observaciones),format.raw/*38.64*/("""</td>
							</TR>
						""")))}),format.raw/*40.8*/("""
					"""),format.raw/*41.6*/("""</tbody>
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
	
	<form id="form_cambiarEstado"method="post" action="/qrCambiaEstadoEquipos/">
		<input type="hidden" id="id_equipo" name="id_equipo" value="0">
		<input type="hidden" id="activo" name="activo" value="0">
	</form>

	
""")))}),format.raw/*60.2*/("""




"""),format.raw/*65.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*66.31*/("""{"""),format.raw/*66.32*/("""
		  """),format.raw/*67.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*68.2*/("""}"""),format.raw/*68.3*/(""");
	
	const cambiar = (nombreParametro) => """),format.raw/*70.39*/("""{"""),format.raw/*70.40*/("""
		"""),format.raw/*71.3*/("""var valor = 0;
		if(document.getElementById(nombreParametro).checked)"""),format.raw/*72.55*/("""{"""),format.raw/*72.56*/("""
			"""),format.raw/*73.4*/("""valor = 1;
		"""),format.raw/*74.3*/("""}"""),format.raw/*74.4*/("""
		"""),format.raw/*75.3*/("""var formData = new FormData();
	  	formData.append('nombreParametro',nombreParametro);
		formData.append('valor',valor);
        $.ajax("""),format.raw/*78.16*/("""{"""),format.raw/*78.17*/("""
            """),format.raw/*79.13*/("""url: "/modificarModulosAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*86.36*/("""{"""),format.raw/*86.37*/("""}"""),format.raw/*86.38*/("""
        """),format.raw/*87.9*/("""}"""),format.raw/*87.10*/(""");
	"""),format.raw/*88.2*/("""}"""),format.raw/*88.3*/("""
	
	
"""),format.raw/*91.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listado:List[tables.Parametros]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listado)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Parametros]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listado) => apply(mapDiccionario,mapPermiso,userMnu,listado)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuToolsAdmin/administraModulos.scala.html
                  HASH: 11f4dc5058f898b6f789392199b86e675ad75c78
                  MATRIX: 1043->1|1267->132|1300->140|1316->148|1355->150|1383->153|1451->201|1479->203|1555->254|1686->364|1716->367|2227->852|2264->873|2303->874|2338->882|2410->927|2424->932|2448->935|2589->1049|2628->1050|2667->1061|2722->1089|2736->1094|2764->1101|2826->1136|2840->1141|2868->1148|2927->1163|3009->1218|3048->1219|3087->1230|3142->1258|3156->1263|3184->1270|3238->1297|3252->1302|3280->1309|3339->1324|3421->1379|3460->1380|3499->1391|3554->1419|3568->1424|3596->1431|3667->1475|3682->1480|3711->1487|3770->1502|3852->1557|3891->1558|3930->1569|3985->1597|3999->1602|4027->1609|4090->1645|4104->1650|4132->1657|4191->1672|4234->1687|4311->1737|4325->1742|4360->1756|4416->1782|4449->1788|5047->2356|5079->2361|5169->2423|5198->2424|5230->2429|5323->2495|5351->2496|5422->2539|5451->2540|5481->2543|5578->2612|5607->2613|5638->2617|5678->2630|5706->2631|5736->2634|5900->2770|5929->2771|5970->2784|6231->3017|6260->3018|6289->3019|6325->3028|6354->3029|6385->3033|6413->3034|6445->3039
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|54->23|54->23|54->23|56->25|56->25|57->26|57->26|57->26|57->26|57->26|57->26|57->26|58->27|59->28|59->28|60->29|60->29|60->29|60->29|60->29|60->29|60->29|61->30|62->31|62->31|63->32|63->32|63->32|63->32|63->32|63->32|63->32|64->33|65->34|65->34|66->35|66->35|66->35|66->35|66->35|66->35|66->35|67->36|68->37|69->38|69->38|69->38|71->40|72->41|91->60|96->65|97->66|97->66|98->67|99->68|99->68|101->70|101->70|102->71|103->72|103->72|104->73|105->74|105->74|106->75|109->78|109->78|110->79|117->86|117->86|117->86|118->87|118->87|119->88|119->88|122->91
                  -- GENERATED --
              */
          