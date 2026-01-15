
package viewsMnuTablas.html

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

object tipoReparacionModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.TipoReparacion,tables.TipoEstado,List[tables.Moneda],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tipoReparacion: tables.TipoReparacion, tipoEstado: tables.TipoEstado, listMonedas: List[tables.Moneda]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR TIPO REPARACION DEL TIPO ESTADO", "")),format.raw/*9.80*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td colspan="2" style="text-align: left;">
							<input type="hidden" name="id_tipoEstado" value=""""),_display_(/*15.58*/tipoEstado/*15.68*/.getId()),format.raw/*15.76*/("""">
							<h5>TIPO ESTADO: """),_display_(/*16.26*/tipoEstado/*16.36*/.getSigla()),format.raw/*16.47*/(""" """),format.raw/*16.48*/("""- """),_display_(/*16.51*/tipoEstado/*16.61*/.getNombre()),format.raw/*16.73*/("""</h5>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">SIGLA: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="sigla"
								autocomplete="off"
								onkeydown="return sinReservados(window.event)"
								maxlength="5"
								value = """"),_display_(/*27.19*/tipoReparacion/*27.33*/.getSigla()),format.raw/*27.44*/(""""
								onchange="modificarTipoReparacion(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombre"
								autocomplete="off"
								onkeydown="return sinReservados(window.event)"
								maxlength="50"
								value = """"),_display_(/*39.19*/tipoReparacion/*39.33*/.getNombre()),format.raw/*39.45*/(""""
								onchange="modificarTipoReparacion(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">MONEDA: </td>
						<td style="text-align:left;">
							<select class="custom-select" 
								id="id_moneda" 
								onchange="modificarTipoReparacion(id)">
								"""),_display_(/*49.10*/for(lista <- listMonedas) yield /*49.35*/{_display_(Seq[Any](format.raw/*49.36*/("""
									"""),format.raw/*50.10*/("""<option value=""""),_display_(/*50.26*/lista/*50.31*/.getId()),format.raw/*50.39*/("""">"""),_display_(/*50.42*/lista/*50.47*/.getNickName()),format.raw/*50.61*/("""</option>
								""")))}),format.raw/*51.10*/("""
							"""),format.raw/*52.8*/("""</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">PRECIO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="precio"
								value=""""),_display_(/*60.17*/tipoReparacion/*60.31*/.getPrecio()),format.raw/*60.43*/(""""
								onfocus="value=value.replace(/,/g,'')" 
								onkeydown="return ingresoDouble(window.event)"
								onchange="if(value.trim()=='') value=0; modificarTipoReparacion(id)">	
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">DESCRIPCION: </td>
						<td style="text-align:left;">
							<textarea 
								class="form-control form-control-sm" 
								rows="3" id="descripcion" 
								id="descripcion" 
								autocomplete="off"
								onchange="modificarTipoReparacion(id)">"""),_display_(/*74.49*/tipoReparacion/*74.63*/.getDescripcion()),format.raw/*74.80*/("""</textarea>
						</td>
					</tr>
				</table>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" 
								onclick="location.href='/tipoEstadoModifica/"""),_display_(/*82.54*/tipoEstado/*82.64*/.getId()),format.raw/*82.72*/("""';">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*90.2*/("""



"""),format.raw/*94.1*/("""<script type="text/javascript">

	let id_tipoReparacion = """),_display_(/*96.27*/tipoReparacion/*96.41*/.getId()),format.raw/*96.49*/(""";

	$(document).ready(function() """),format.raw/*98.31*/("""{"""),format.raw/*98.32*/("""
		"""),format.raw/*99.3*/("""$('#id_moneda > option[value=""""),_display_(/*99.34*/tipoReparacion/*99.48*/.getId_moneda()),format.raw/*99.63*/(""""]').attr('selected', 'selected');
		 document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*101.2*/("""}"""),format.raw/*101.3*/(""");
	
	const modificarTipoReparacion = (campo) => """),format.raw/*103.45*/("""{"""),format.raw/*103.46*/("""
		"""),format.raw/*104.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_tipoReparacion',id_tipoReparacion);
	  	formData.append('valor',valor);
	  	formData.append('id_tipoEstado',"""),_display_(/*109.38*/tipoEstado/*109.48*/.getId()),format.raw/*109.56*/(""");
        $.ajax("""),format.raw/*110.16*/("""{"""),format.raw/*110.17*/("""
            """),format.raw/*111.13*/("""url: "/modificaTipoReparacionPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*118.36*/("""{"""),format.raw/*118.37*/("""
	     		"""),format.raw/*119.9*/("""if(respuesta=="existe")"""),format.raw/*119.32*/("""{"""),format.raw/*119.33*/("""
	     			"""),format.raw/*120.10*/("""alertify.alert('El nombre corto de proyecto ya existe, intente con otro', function () """),format.raw/*120.96*/("""{"""),format.raw/*120.97*/("""
	     				"""),format.raw/*121.11*/("""$("#sigla").val(""""),_display_(/*121.29*/tipoReparacion/*121.43*/.getSigla()),format.raw/*121.54*/("""");
		     		"""),format.raw/*122.10*/("""}"""),format.raw/*122.11*/(""");
	     		"""),format.raw/*123.9*/("""}"""),format.raw/*123.10*/("""else if(respuesta=="error")"""),format.raw/*123.37*/("""{"""),format.raw/*123.38*/("""
	     			"""),format.raw/*124.10*/("""alertify.alert(msgError, function () """),format.raw/*124.47*/("""{"""),format.raw/*124.48*/("""
		     			"""),format.raw/*125.11*/("""location.href = "/";
		     		"""),format.raw/*126.10*/("""}"""),format.raw/*126.11*/(""");
	     		"""),format.raw/*127.9*/("""}"""),format.raw/*127.10*/("""
	     	"""),format.raw/*128.8*/("""}"""),format.raw/*128.9*/("""
        """),format.raw/*129.9*/("""}"""),format.raw/*129.10*/(""");
	"""),format.raw/*130.2*/("""}"""),format.raw/*130.3*/("""
	
	
"""),format.raw/*133.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tipoReparacion:tables.TipoReparacion,tipoEstado:tables.TipoEstado,listMonedas:List[tables.Moneda]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tipoReparacion,tipoEstado,listMonedas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.TipoReparacion,tables.TipoEstado,List[tables.Moneda]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tipoReparacion,tipoEstado,listMonedas) => apply(mapDiccionario,mapPermiso,userMnu,tipoReparacion,tipoEstado,listMonedas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/tipoReparacionModifica.scala.html
                  HASH: 8b47ad825b75fee79528bad1f59a34255afdd80d
                  MATRIX: 1080->1|1374->202|1407->210|1423->218|1462->220|1490->223|1558->271|1586->273|1662->324|1758->400|1788->403|2108->696|2127->706|2156->714|2211->742|2230->752|2262->763|2291->764|2321->767|2340->777|2373->789|2716->1105|2739->1119|2771->1130|3161->1493|3184->1507|3217->1519|3531->1806|3572->1831|3611->1832|3649->1842|3692->1858|3706->1863|3735->1871|3765->1874|3779->1879|3814->1893|3864->1912|3899->1920|4142->2136|4165->2150|4198->2162|4732->2669|4755->2683|4793->2700|5151->3031|5170->3041|5199->3049|5299->3119|5330->3123|5416->3182|5439->3196|5468->3204|5529->3237|5558->3238|5588->3241|5646->3272|5669->3286|5705->3301|5837->3405|5866->3406|5944->3455|5974->3456|6005->3459|6267->3693|6287->3703|6317->3711|6364->3729|6394->3730|6436->3743|6712->3990|6742->3991|6779->4000|6831->4023|6861->4024|6900->4034|7015->4120|7045->4121|7085->4132|7131->4150|7155->4164|7188->4175|7230->4188|7260->4189|7299->4200|7329->4201|7385->4228|7415->4229|7454->4239|7520->4276|7550->4277|7590->4288|7649->4318|7679->4319|7718->4330|7748->4331|7784->4339|7813->4340|7850->4349|7880->4350|7912->4354|7941->4355|7974->4360
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|47->16|47->16|47->16|47->16|47->16|47->16|47->16|58->27|58->27|58->27|70->39|70->39|70->39|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|81->50|82->51|83->52|91->60|91->60|91->60|105->74|105->74|105->74|113->82|113->82|113->82|121->90|125->94|127->96|127->96|127->96|129->98|129->98|130->99|130->99|130->99|130->99|132->101|132->101|134->103|134->103|135->104|140->109|140->109|140->109|141->110|141->110|142->111|149->118|149->118|150->119|150->119|150->119|151->120|151->120|151->120|152->121|152->121|152->121|152->121|153->122|153->122|154->123|154->123|154->123|154->123|155->124|155->124|155->124|156->125|157->126|157->126|158->127|158->127|159->128|159->128|160->129|160->129|161->130|161->130|164->133
                  -- GENERATED --
              */
          