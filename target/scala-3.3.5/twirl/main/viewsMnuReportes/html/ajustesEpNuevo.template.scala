
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

object ajustesEpNuevo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,utilities.Fechas,List[tables.TipoAjustes],List[tables.TipoAjustesVenta],Long,tables.Moneda,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodegaEmpresa: tables.BodegaEmpresa, hoy: utilities.Fechas, listAjustes: List[tables.TipoAjustes], listAjustesVenta: List[tables.TipoAjustesVenta], numDec: Long, moneda: tables.Moneda):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

"""),_display_(/*6.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.50*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "NUEVO AJUSTE A EP/PROFORMA", bodegaEmpresa.nombre)),format.raw/*8.83*/("""
		"""),format.raw/*9.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-10 col-sm-5 col-md-5 col-lg-5">
				<form id="form" action="/ajustesEpGrabar/" method="POST" onsubmit="return validarForm();">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<TR>
							<td colspan="2" style="text-align:left;">
								<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*15.62*/bodegaEmpresa/*15.75*/.id),format.raw/*15.78*/("""" required>
								<input type="hidden" name="id_bodega" value=""""),_display_(/*16.55*/bodegaEmpresa/*16.68*/.id),format.raw/*16.71*/("""" required>
								"""),_display_(/*17.10*/bodegaEmpresa/*17.23*/.nombre),format.raw/*17.30*/("""
							"""),format.raw/*18.8*/("""</td>
						</TR>
						<tr>
							<td style="text-align: left;">CUANDO APLICA AJUSTE:</td>
							<td style="text-align: left;">
						  		<input type="date" class="form-control left"
						  			name="fechaAjuste"
						  			onblur="if(!limitaFecha(value,"""),_display_(/*25.43*/mapPermiso/*25.53*/.get("parametro.diasMenosGuia")),format.raw/*25.84*/(""",70)) value='"""),_display_(/*25.98*/hoy/*25.101*/.getFechaStrAAMMDD()),format.raw/*25.121*/("""';"
						  			value=""""),_display_(/*26.20*/hoy/*26.23*/.getFechaStrAAMMDD()),format.raw/*26.43*/(""""
				        			required>
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">TIPO DE AJUSTE:</td>
							<td style="text-align: left;">
								<select class="custom-select left"
									name="id_tipoAjuste">
									"""),_display_(/*35.11*/for(lista <- listAjustes) yield /*35.36*/{_display_(Seq[Any](format.raw/*35.37*/("""
								     	"""),format.raw/*36.15*/("""<option value=""""),_display_(/*36.31*/lista/*36.36*/.id),format.raw/*36.39*/("""">"""),_display_(/*36.42*/lista/*36.47*/.ajuste),format.raw/*36.54*/("""</option>
									""")))}),format.raw/*37.11*/("""
								"""),format.raw/*38.9*/("""</select>
						    </td>
						</tr>
						<tr>
							<td style="text-align: left;">APLICA SOBRE:</td>
							<td style="text-align: left;">
								<select class="custom-select left"
									name="id_tipoAjusteVenta">
									"""),_display_(/*46.11*/for(lista <- listAjustesVenta) yield /*46.41*/{_display_(Seq[Any](format.raw/*46.42*/("""
								     	"""),format.raw/*47.15*/("""<option value=""""),_display_(/*47.31*/lista/*47.36*/.id),format.raw/*47.39*/("""">"""),_display_(/*47.42*/lista/*47.47*/.ajusteVenta),format.raw/*47.59*/("""</option>
									""")))}),format.raw/*48.11*/("""
								"""),format.raw/*49.9*/("""</select>
						    </td>
						</tr>
						<TR>
					        <td style="text-align:left;padding-left:10px">MONEDA:</td>
					        <td style="text-align:left">
					        	<input type="hidden" name="id_moneda" value=""""),_display_(/*55.61*/moneda/*55.67*/.id),format.raw/*55.70*/("""" required>
								<input type="text" class="form-control left"
									value=""""),_display_(/*57.18*/moneda/*57.24*/.nickName),format.raw/*57.33*/(""""
									readonly>
					        </td>
						</TR>
						<TR>
					        <td style="text-align:left;padding-left:10px">MONTO A AJUSTAR:</td>
					        <td style="text-align:left">
								<input type="text" class="form-control left"
									name="totalAjuste"
									onfocus="value = value.replace(/,/g,'');" 
									onkeydown="return ingresoDouble(window.event)"
									autocomplete="off"
									onchange="if(value=='') value=0; value = formatStandar(value,'"""),_display_(/*69.73*/numDec),format.raw/*69.79*/("""');"
									required>
					        </td>
						</TR>
						<TR>
					        <td style="text-align:left;padding-left:10px">MOTIVO:</td>
					        <td style="text-align:left">
								<input type="text" class="form-control left"
									name="concepto"
									autocomplete="off"
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
									onchange="$('#seModifico').val(1);"></textarea>
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

""")))}),format.raw/*107.2*/("""



"""),format.raw/*111.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*113.31*/("""{"""),format.raw/*113.32*/("""
		  """),format.raw/*114.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*115.2*/("""}"""),format.raw/*115.3*/(""");
	
	const validarForm = () =>"""),format.raw/*117.27*/("""{"""),format.raw/*117.28*/("""
		"""),format.raw/*118.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*120.2*/("""}"""),format.raw/*120.3*/("""
	

"""),format.raw/*123.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodegaEmpresa:tables.BodegaEmpresa,hoy:utilities.Fechas,listAjustes:List[tables.TipoAjustes],listAjustesVenta:List[tables.TipoAjustesVenta],numDec:Long,moneda:tables.Moneda): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodegaEmpresa,hoy,listAjustes,listAjustesVenta,numDec,moneda)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,utilities.Fechas,List[tables.TipoAjustes],List[tables.TipoAjustesVenta],Long,tables.Moneda) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodegaEmpresa,hoy,listAjustes,listAjustesVenta,numDec,moneda) => apply(mapDiccionario,mapPermiso,userMnu,bodegaEmpresa,hoy,listAjustes,listAjustesVenta,numDec,moneda)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/ajustesEpNuevo.scala.html
                  HASH: 96266be5c73a80e8ff11a3fa9c2070b19215b396
                  MATRIX: 1126->1|1501->283|1533->290|1549->298|1588->300|1616->303|1684->351|1712->353|1788->404|1887->483|1916->486|2337->880|2359->893|2383->896|2476->962|2498->975|2522->978|2570->999|2592->1012|2620->1019|2655->1027|2940->1285|2959->1295|3011->1326|3052->1340|3065->1343|3107->1363|3157->1386|3169->1389|3210->1409|3480->1652|3521->1677|3560->1678|3603->1693|3646->1709|3660->1714|3684->1717|3714->1720|3728->1725|3756->1732|3807->1752|3843->1761|4102->1993|4148->2023|4187->2024|4230->2039|4273->2055|4287->2060|4311->2063|4341->2066|4355->2071|4388->2083|4439->2103|4475->2112|4725->2335|4740->2341|4764->2344|4873->2426|4888->2432|4918->2441|5420->2916|5447->2922|6694->4138|6726->4142|6818->4205|6848->4206|6881->4211|6975->4277|7004->4278|7064->4309|7094->4310|7125->4313|7209->4369|7238->4370|7270->4374
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|46->15|46->15|46->15|47->16|47->16|47->16|48->17|48->17|48->17|49->18|56->25|56->25|56->25|56->25|56->25|56->25|57->26|57->26|57->26|66->35|66->35|66->35|67->36|67->36|67->36|67->36|67->36|67->36|67->36|68->37|69->38|77->46|77->46|77->46|78->47|78->47|78->47|78->47|78->47|78->47|78->47|79->48|80->49|86->55|86->55|86->55|88->57|88->57|88->57|100->69|100->69|138->107|142->111|144->113|144->113|145->114|146->115|146->115|148->117|148->117|149->118|151->120|151->120|154->123
                  -- GENERATED --
              */
          