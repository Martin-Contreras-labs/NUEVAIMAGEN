
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

object tipoReparacionAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.TipoEstado,List[tables.Moneda],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tipoEstado: tables.TipoEstado, listMonedas: List[tables.Moneda]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "NUEVO TIPO REPARACION DEL TIPO ESTADO", "")),format.raw/*9.76*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/tipoReparacionGraba/">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td colspan="2" style="text-align: left;">
								<input type="hidden" name="id_tipoEstado" value=""""),_display_(/*16.59*/tipoEstado/*16.69*/.getId()),format.raw/*16.77*/("""">
							<h5>TIPO ESTADO: """),_display_(/*17.26*/tipoEstado/*17.36*/.getSigla()),format.raw/*17.47*/(""" """),format.raw/*17.48*/("""- """),_display_(/*17.51*/tipoEstado/*17.61*/.getNombre()),format.raw/*17.73*/("""</h5>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">SIGLA: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									id="sigla"
									name="sigla"
									autocomplete="off"
									maxlength="5"
									onkeydown="return sinReservados(window.event)"
									onchange="value = value.trim();verificaSigla(value, """),_display_(/*29.63*/tipoEstado/*29.73*/.getId()),format.raw/*29.81*/(""");"
									required>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">NOMBRE: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="nombre"
									onkeydown="return sinReservados(window.event)"
									autocomplete="off"
									maxlength="50">
							</td>
						</tr>
						<tr>
						<td style="text-align:left;">MONEDA: </td>
							<td style="text-align:left;">
								<select class="custom-select" 
									name="id_moneda" 
									onchange="modificarTipoReparacion(id)">
									"""),_display_(/*49.11*/for(lista <- listMonedas) yield /*49.36*/{_display_(Seq[Any](format.raw/*49.37*/("""
										"""),format.raw/*50.11*/("""<option value=""""),_display_(/*50.27*/lista/*50.32*/.getId()),format.raw/*50.40*/("""">"""),_display_(/*50.43*/lista/*50.48*/.getNickName()),format.raw/*50.62*/("""</option>
									""")))}),format.raw/*51.11*/("""
								"""),format.raw/*52.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">PRECIO: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="precio"
									value="0"
									onchange="if(value.trim()=='') value=0"
									onkeydown="return ingresoDouble(window.event)">	
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">DESCRIPCION: </td>
							<td style="text-align:left;">
								<textarea 
									class="form-control form-control-sm" 
									rows="3" id="descripcion" 
									name="descripcion" 
									autocomplete="off"></textarea>
							</td>
						</tr>
					</table>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="submit"  value="GRABAR REPARACION" class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button" value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/tipoEstadoModifica/"""),_display_(/*82.144*/tipoEstado/*82.154*/.getId()),format.raw/*82.162*/("""';">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

""")))}),format.raw/*91.2*/("""



"""),format.raw/*95.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*96.31*/("""{"""),format.raw/*96.32*/("""
		  """),format.raw/*97.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*98.2*/("""}"""),format.raw/*98.3*/(""");
	
	const verificaSigla = (sigla, id_tipoEstado) => """),format.raw/*100.50*/("""{"""),format.raw/*100.51*/("""
		"""),format.raw/*101.3*/("""var formData = new FormData();
	  	formData.append('sigla',sigla);
	  	formData.append('id_tipoEstado',id_tipoEstado);
        $.ajax("""),format.raw/*104.16*/("""{"""),format.raw/*104.17*/("""
            """),format.raw/*105.13*/("""url: "/verificaSiglaTipoReparacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*112.36*/("""{"""),format.raw/*112.37*/("""
	     		"""),format.raw/*113.9*/("""if(respuesta=="existe")"""),format.raw/*113.32*/("""{"""),format.raw/*113.33*/("""
	     			"""),format.raw/*114.10*/("""alertify.alert('La sigla de tipo reparación asociada a este tipo de estado ya existe, intente con otra', function () """),format.raw/*114.127*/("""{"""),format.raw/*114.128*/("""
	     				"""),format.raw/*115.11*/("""$("#sigla").val("");
		     		"""),format.raw/*116.10*/("""}"""),format.raw/*116.11*/(""");
	     		"""),format.raw/*117.9*/("""}"""),format.raw/*117.10*/("""
	     		"""),format.raw/*118.9*/("""if(respuesta=="error")"""),format.raw/*118.31*/("""{"""),format.raw/*118.32*/("""
	     			"""),format.raw/*119.10*/("""alertify.alert(msgError, function () """),format.raw/*119.47*/("""{"""),format.raw/*119.48*/("""
		     			"""),format.raw/*120.11*/("""location.href = "/";
		     		"""),format.raw/*121.10*/("""}"""),format.raw/*121.11*/(""");
	     		"""),format.raw/*122.9*/("""}"""),format.raw/*122.10*/("""
	     	"""),format.raw/*123.8*/("""}"""),format.raw/*123.9*/("""
        """),format.raw/*124.9*/("""}"""),format.raw/*124.10*/(""");
	"""),format.raw/*125.2*/("""}"""),format.raw/*125.3*/("""
"""),format.raw/*126.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tipoEstado:tables.TipoEstado,listMonedas:List[tables.Moneda]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tipoEstado,listMonedas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.TipoEstado,List[tables.Moneda]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tipoEstado,listMonedas) => apply(mapDiccionario,mapPermiso,userMnu,tipoEstado,listMonedas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/tipoReparacionAgrega.scala.html
                  HASH: c6522c3016f3d009bfa4115444c147fced82ab5c
                  MATRIX: 1056->1|1311->163|1344->171|1360->179|1399->181|1427->184|1495->232|1523->234|1599->285|1691->357|1721->360|2111->723|2130->733|2159->741|2214->769|2233->779|2265->790|2294->791|2324->794|2343->804|2376->816|2795->1208|2814->1218|2843->1226|3448->1804|3489->1829|3528->1830|3567->1841|3610->1857|3624->1862|3653->1870|3683->1873|3697->1878|3732->1892|3783->1912|3819->1921|4980->3054|5000->3064|5030->3072|5145->3157|5176->3161|5266->3223|5295->3224|5327->3229|5420->3295|5448->3296|5531->3350|5561->3351|5592->3354|5755->3488|5785->3489|5827->3502|6100->3746|6130->3747|6167->3756|6219->3779|6249->3780|6288->3790|6435->3907|6466->3908|6506->3919|6565->3949|6595->3950|6634->3961|6664->3962|6701->3971|6752->3993|6782->3994|6821->4004|6887->4041|6917->4042|6957->4053|7016->4083|7046->4084|7085->4095|7115->4096|7151->4104|7180->4105|7217->4114|7247->4115|7279->4119|7308->4120|7337->4121
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|48->17|48->17|48->17|48->17|48->17|48->17|48->17|60->29|60->29|60->29|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|81->50|82->51|83->52|113->82|113->82|113->82|122->91|126->95|127->96|127->96|128->97|129->98|129->98|131->100|131->100|132->101|135->104|135->104|136->105|143->112|143->112|144->113|144->113|144->113|145->114|145->114|145->114|146->115|147->116|147->116|148->117|148->117|149->118|149->118|149->118|150->119|150->119|150->119|151->120|152->121|152->121|153->122|153->122|154->123|154->123|155->124|155->124|156->125|156->125|157->126
                  -- GENERATED --
              */
          