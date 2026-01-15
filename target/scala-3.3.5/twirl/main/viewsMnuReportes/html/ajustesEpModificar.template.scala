
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

object ajustesEpModificar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.AjustesEP,List[tables.TipoAjustes],List[tables.TipoAjustesVenta],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
ajuste: tables.AjustesEP, listAjustes: List[tables.TipoAjustes], listAjustesVenta: List[tables.TipoAjustesVenta], numDec: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR AJUSTE A EP/PROFORMA", ajuste.bodegaEmpresa)),format.raw/*9.87*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-10 col-sm-5 col-md-5 col-lg-5">
				<form id="form" action="/ajustesEpUpdate/" method="POST" onsubmit="return validarForm();">
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
						<tr>
							<td style="text-align: left;">APLICA SOBRE:</td>
							<td style="text-align: left;">
								<select class="custom-select left"
									name="id_tipoAjusteVenta">
									<option value=""""),_display_(/*49.26*/ajuste/*49.32*/.id_tipoAjusteVenta),format.raw/*49.51*/("""">"""),_display_(/*49.54*/ajuste/*49.60*/.tipoAjusteVenta),format.raw/*49.76*/("""</option>
									"""),_display_(/*50.11*/for(lista <- listAjustesVenta) yield /*50.41*/{_display_(Seq[Any](format.raw/*50.42*/("""
								     	"""),format.raw/*51.15*/("""<option value=""""),_display_(/*51.31*/lista/*51.36*/.id),format.raw/*51.39*/("""">"""),_display_(/*51.42*/lista/*51.47*/.ajusteVenta),format.raw/*51.59*/("""</option>
									""")))}),format.raw/*52.11*/("""
								"""),format.raw/*53.9*/("""</select>
						    </td>
						</tr>
						<TR>
					        <td style="text-align:left;padding-left:10px">MONEDA:</td>
					        <td style="text-align:left">
					        	<input type="hidden" name="id_moneda" value=""""),_display_(/*59.61*/ajuste/*59.67*/.id_moneda),format.raw/*59.77*/("""" required>
								<input type="text" class="form-control left"
									value=""""),_display_(/*61.18*/ajuste/*61.24*/.moneda),format.raw/*61.31*/(""""
									readonly>
					        </td>
						</TR>
						<TR>
					        <td style="text-align:left;padding-left:10px">MONTO A AJUSTAR:</td>
					        <td style="text-align:left">
								<input type="text" class="form-control left"
									name="totalAjuste"
									value=""""),_display_(/*70.18*/ajuste/*70.24*/.totalAjuste),format.raw/*70.36*/(""""
									onfocus="value = value.replace(/,/g,'');" 
									onkeydown="return ingresoDouble(window.event)"
									autocomplete="off"
									onchange="if(value=='') value=0; value = formatStandar(value,'"""),_display_(/*74.73*/numDec),format.raw/*74.79*/("""');"
									required>
					        </td>
						</TR>
						<TR>
					        <td style="text-align:left;padding-left:10px">MOTIVO:</td>
					        <td style="text-align:left">
								<input type="text" class="form-control left"
									name="concepto"
									autocomplete="off"
									value=""""),_display_(/*84.18*/ajuste/*84.24*/.concepto),format.raw/*84.33*/(""""
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
									onchange="$('#seModifico').val(1);">"""),_display_(/*96.47*/ajuste/*96.53*/.observaciones),format.raw/*96.67*/("""</textarea>
							</td>
						</TR>
					</table>
					<div class="row justify-content-md-center" >
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
							<input type="submit" id="btnSubmit" value='GRABAR' class="btn btn-primary btn-sm rounded-pill btn-block">
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
							<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="history.go(-1);return false;">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

""")))}),format.raw/*113.2*/("""



"""),format.raw/*117.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*119.31*/("""{"""),format.raw/*119.32*/("""
		  """),format.raw/*120.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*121.2*/("""}"""),format.raw/*121.3*/(""");
	
	const validarForm = () =>"""),format.raw/*123.27*/("""{"""),format.raw/*123.28*/("""
		"""),format.raw/*124.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*126.2*/("""}"""),format.raw/*126.3*/("""
"""),format.raw/*127.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,ajuste:tables.AjustesEP,listAjustes:List[tables.TipoAjustes],listAjustesVenta:List[tables.TipoAjustesVenta],numDec:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,ajuste,listAjustes,listAjustesVenta,numDec)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.AjustesEP,List[tables.TipoAjustes],List[tables.TipoAjustesVenta],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,ajuste,listAjustes,listAjustesVenta,numDec) => apply(mapDiccionario,mapPermiso,userMnu,ajuste,listAjustes,listAjustesVenta,numDec)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/ajustesEpModificar.scala.html
                  HASH: 1a1476fe3b747ef66961dd09f8a7f36e19f5f39b
                  MATRIX: 1095->1|1413->226|1446->234|1462->242|1501->244|1529->247|1597->295|1625->297|1701->348|1804->431|1834->434|2241->814|2256->820|2280->823|2380->896|2395->902|2433->919|2526->985|2541->991|2579->1008|2627->1029|2642->1035|2677->1049|2712->1057|2997->1315|3016->1325|3068->1356|3109->1370|3128->1379|3184->1413|3234->1436|3252->1445|3307->1479|3592->1737|3607->1743|3642->1757|3672->1760|3687->1766|3719->1777|3766->1797|3807->1822|3846->1823|3889->1838|3932->1854|3946->1859|3970->1862|4000->1865|4014->1870|4042->1877|4093->1897|4129->1906|4403->2153|4418->2159|4458->2178|4488->2181|4503->2187|4540->2203|4587->2223|4633->2253|4672->2254|4715->2269|4758->2285|4772->2290|4796->2293|4826->2296|4840->2301|4873->2313|4924->2333|4960->2342|5210->2565|5225->2571|5256->2581|5365->2663|5380->2669|5408->2676|5719->2960|5734->2966|5767->2978|6004->3188|6031->3194|6361->3497|6376->3503|6406->3512|6823->3902|6838->3908|6873->3922|7447->4465|7479->4469|7571->4532|7601->4533|7634->4538|7728->4604|7757->4605|7817->4636|7847->4637|7878->4640|7962->4696|7991->4697|8020->4698
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|48->17|48->17|48->17|49->18|49->18|49->18|50->19|50->19|50->19|51->20|58->27|58->27|58->27|58->27|58->27|58->27|59->28|59->28|59->28|68->37|68->37|68->37|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|70->39|70->39|70->39|70->39|71->40|72->41|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|82->51|83->52|84->53|90->59|90->59|90->59|92->61|92->61|92->61|101->70|101->70|101->70|105->74|105->74|115->84|115->84|115->84|127->96|127->96|127->96|144->113|148->117|150->119|150->119|151->120|152->121|152->121|154->123|154->123|155->124|157->126|157->126|158->127
                  -- GENERATED --
              */
          