
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

object ajustesEpModificarOdo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.AjustesEpOdo,List[tables.TipoAjustes],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
ajuste: tables.AjustesEpOdo, listAjustes: List[tables.TipoAjustes], numDec: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR AJUSTE A EP/PROFORMA SERVICIO", ajuste.bodegaEmpresa)),format.raw/*9.96*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-10 col-sm-5 col-md-5 col-lg-5">
				<form id="form" action="/ajustesEpUpdateOdo/" method="POST">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<TR>
							<td colspan="2" style="text-align:left;">
								<input type="hidden" name="id" value=""""),_display_(/*16.48*/ajuste/*16.54*/.id),format.raw/*16.57*/("""" required>
								<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*17.62*/ajuste/*17.68*/.id_bodegaEmpresa),format.raw/*17.85*/("""" required>
								<input type="hidden" name="id_bodega" value=""""),_display_(/*18.55*/ajuste/*18.61*/.id_bodegaEmpresa),format.raw/*18.78*/("""" required>
								"""),_display_(/*19.10*/ajuste/*19.16*/.bodegaEmpresa),format.raw/*19.30*/("""
							"""),format.raw/*20.8*/("""</td>
						</TR>
						<tr>
							<td style="text-align: left;">CUANDO APLICA AJUSTE:</td>
							<td style="text-align: left;">
						  		<input type="date" class="form-control left"
						  			name="fechaAjuste"
						  			onblur="if(!limitaFecha(value,"""),_display_(/*27.43*/mapPermiso/*27.53*/.get("parametro.diasMenosGuia")),format.raw/*27.84*/(""",70)) value='"""),_display_(/*27.98*/utilities/*27.107*/.Fechas.AAMMDD(ajuste.fechaAjuste)),format.raw/*27.141*/("""';"
						  			value=""""),_display_(/*28.20*/utilities/*28.29*/.Fechas.AAMMDD(ajuste.fechaAjuste)),format.raw/*28.63*/(""""
				        			required>
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">TIPO DE AJUSTE:</td>
							<td style="text-align: left;">
								<select class="custom-select left"
									name="id_tipoAjuste">
									<option value=""""),_display_(/*37.26*/ajuste/*37.32*/.id_tipoAjuste),format.raw/*37.46*/("""">"""),_display_(/*37.49*/ajuste/*37.55*/.tipoAjuste),format.raw/*37.66*/("""</option>
									"""),_display_(/*38.11*/for(lista <- listAjustes) yield /*38.36*/{_display_(Seq[Any](format.raw/*38.37*/("""
								     	"""),format.raw/*39.15*/("""<option value=""""),_display_(/*39.31*/lista/*39.36*/.id),format.raw/*39.39*/("""">"""),_display_(/*39.42*/lista/*39.47*/.ajuste),format.raw/*39.54*/("""</option>
									""")))}),format.raw/*40.11*/("""
								"""),format.raw/*41.9*/("""</select>
						    </td>
						</tr>
						<TR>
					        <td style="text-align:left;padding-left:10px">MONEDA:</td>
					        <td style="text-align:left">
					        	<input type="hidden" name="id_moneda" value=""""),_display_(/*47.61*/ajuste/*47.67*/.id_moneda),format.raw/*47.77*/("""" required>
								<input type="text" class="form-control left"
									value=""""),_display_(/*49.18*/ajuste/*49.24*/.moneda),format.raw/*49.31*/(""""
									readonly>
					        </td>
						</TR>
						<TR>
					        <td style="text-align:left;padding-left:10px">MONTO A AJUSTAR:</td>
					        <td style="text-align:left">
								<input type="text" class="form-control left"
									name="totalAjuste"
									value=""""),_display_(/*58.18*/ajuste/*58.24*/.totalAjuste),format.raw/*58.36*/(""""
									onfocus="value = value.replace(/,/g,'');" 
									onkeydown="return ingresoDouble(window.event)"
									autocomplete="off"
									onchange="if(value=='') value=0; value = formatStandar(value,'"""),_display_(/*62.73*/numDec),format.raw/*62.79*/("""');"
									required>
					        </td>
						</TR>
						<TR>
					        <td style="text-align:left;padding-left:10px">MOTIVO:</td>
					        <td style="text-align:left">
								<input type="text" class="form-control left"
									name="concepto"
									autocomplete="off"
									value=""""),_display_(/*72.18*/ajuste/*72.24*/.concepto),format.raw/*72.33*/(""""
									onchange="value = value.trim();"
									required>
					        </td>
						</TR>
						<TR>
					        <td style="text-align:left;padding-left:10px">NOTAS:</td>
					        <td style="text-align:left">
								<textarea class="form-control left" 
									rows="4"
  									name="observaciones" 
  									autocomplete="off"
									onchange="$('#seModifico').val(1);">"""),_display_(/*84.47*/ajuste/*84.53*/.observaciones),format.raw/*84.67*/("""</textarea>
							</td>
						</TR>
					</table>
					<div class="row justify-content-md-center" >
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
							<input type="submit" value='GRABAR' class="btn btn-primary btn-sm rounded-pill btn-block">
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
							<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="history.go(-1);return false;">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

""")))}),format.raw/*101.2*/("""



"""),format.raw/*105.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*107.31*/("""{"""),format.raw/*107.32*/("""
		  """),format.raw/*108.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*109.2*/("""}"""),format.raw/*109.3*/(""");
	

</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,ajuste:tables.AjustesEpOdo,listAjustes:List[tables.TipoAjustes],numDec:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,ajuste,listAjustes,numDec)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.AjustesEpOdo,List[tables.TipoAjustes],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,ajuste,listAjustes,numDec) => apply(mapDiccionario,mapPermiso,userMnu,ajuste,listAjustes,numDec)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/ajustesEpModificarOdo.scala.html
                  HASH: ed3b659d62b6b021f1c3d7b15b14cc77c94f9f06
                  MATRIX: 1066->1|1338->180|1371->188|1387->196|1426->198|1454->201|1522->249|1550->251|1626->302|1738->394|1768->397|2145->747|2160->753|2184->756|2284->829|2299->835|2337->852|2430->918|2445->924|2483->941|2531->962|2546->968|2581->982|2616->990|2901->1248|2920->1258|2972->1289|3013->1303|3032->1312|3088->1346|3138->1369|3156->1378|3211->1412|3496->1670|3511->1676|3546->1690|3576->1693|3591->1699|3623->1710|3670->1730|3711->1755|3750->1756|3793->1771|3836->1787|3850->1792|3874->1795|3904->1798|3918->1803|3946->1810|3997->1830|4033->1839|4283->2062|4298->2068|4329->2078|4438->2160|4453->2166|4481->2173|4792->2457|4807->2463|4840->2475|5077->2685|5104->2691|5434->2994|5449->3000|5479->3009|5896->3399|5911->3405|5946->3419|6505->3947|6537->3951|6629->4014|6659->4015|6692->4020|6786->4086|6815->4087
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|48->17|48->17|48->17|49->18|49->18|49->18|50->19|50->19|50->19|51->20|58->27|58->27|58->27|58->27|58->27|58->27|59->28|59->28|59->28|68->37|68->37|68->37|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|70->39|70->39|70->39|70->39|71->40|72->41|78->47|78->47|78->47|80->49|80->49|80->49|89->58|89->58|89->58|93->62|93->62|103->72|103->72|103->72|115->84|115->84|115->84|132->101|136->105|138->107|138->107|139->108|140->109|140->109
                  -- GENERATED --
              */
          