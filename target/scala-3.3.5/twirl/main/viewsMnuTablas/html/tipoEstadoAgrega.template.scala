
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

object tipoEstadoAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listBodegas: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "AGREGAR NUEVO TIPO DE ESTADO (DEVOLUCIONES)", "")),format.raw/*9.82*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/tipoEstadoGraba/">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align:left;">SIGLA: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									id="sigla"
									name="sigla"
									autocomplete="off"
									maxlength="5"
									onchange="value = value.trim(); verificaSigla(value);">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">NOMBRE: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="nombre"
									onkeydown="return sinReservados(window.event)"
									autocomplete="off"
									maxlength="50"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">"""),_display_(/*37.38*/mapDiccionario/*37.52*/.get("BODEGA")),format.raw/*37.66*/(""" """),format.raw/*37.67*/("""ASOCIADA: </td>
							<td style="text-align:left;">
								<select class="custom-select" 
									name="id_bodegaAsociada">
									<option value="0">No asociada</option>
									"""),_display_(/*42.11*/for(lista <- listBodegas) yield /*42.36*/{_display_(Seq[Any](format.raw/*42.37*/("""
										"""),format.raw/*43.11*/("""<option value=""""),_display_(/*43.27*/lista/*43.32*/.get(1)),format.raw/*43.39*/("""">"""),_display_(/*43.42*/lista/*43.47*/.get(5)),format.raw/*43.54*/("""</option>
									""")))}),format.raw/*44.11*/("""
								"""),format.raw/*45.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">APLICA LISTA PRECIOS: </td>
							<td style="text-align:left;">
								<select class="custom-select" 
									name="reparable">
									<option value="1">SI</option>
									<option value="0">NO</option>
								</select>
							</td>
						</tr>
						"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1"))/*58.146*/{_display_(Seq[Any](format.raw/*58.147*/("""
							"""),format.raw/*59.8*/("""<tr>
								<td style="text-align:left;">COBRA """),_display_(/*60.45*/mapDiccionario("ARRIENDO")),format.raw/*60.71*/(""": </td>
								<td style="text-align:left;">
									<select class="custom-select"
										name="cobraArriendo">
											<option value="1">SI</option>
											<option value="0">NO</option>
									</select>
								</td>
							</tr>
						""")))} else {null} ),format.raw/*69.8*/("""
					"""),format.raw/*70.6*/("""</table>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="submit"  value="GRABAR ESTADO" class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button" value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/tipoEstadoMantencion/';">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

""")))}),format.raw/*86.2*/("""



"""),format.raw/*90.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*92.31*/("""{"""),format.raw/*92.32*/("""
		  """),format.raw/*93.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*94.2*/("""}"""),format.raw/*94.3*/(""");
	
	const verificaSigla = (sigla) => """),format.raw/*96.35*/("""{"""),format.raw/*96.36*/("""
		"""),format.raw/*97.3*/("""var formData = new FormData();
	  	formData.append('sigla',sigla);
        $.ajax("""),format.raw/*99.16*/("""{"""),format.raw/*99.17*/("""
            """),format.raw/*100.13*/("""url: "/verificaSiglaTipoEstadoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*107.36*/("""{"""),format.raw/*107.37*/("""
	     		"""),format.raw/*108.9*/("""if(respuesta=="existe")"""),format.raw/*108.32*/("""{"""),format.raw/*108.33*/("""
	     			"""),format.raw/*109.10*/("""alertify.alert('La sigla de tipo estado ya existe, intente con otra', function () """),format.raw/*109.92*/("""{"""),format.raw/*109.93*/("""
	     				"""),format.raw/*110.11*/("""$("#sigla").val("");
		     		"""),format.raw/*111.10*/("""}"""),format.raw/*111.11*/(""");
	     		"""),format.raw/*112.9*/("""}"""),format.raw/*112.10*/("""
	     		"""),format.raw/*113.9*/("""if(respuesta=="error")"""),format.raw/*113.31*/("""{"""),format.raw/*113.32*/("""
	     			"""),format.raw/*114.10*/("""alertify.alert(msgError, function () """),format.raw/*114.47*/("""{"""),format.raw/*114.48*/("""
		     			"""),format.raw/*115.11*/("""location.href = "/";
		     		"""),format.raw/*116.10*/("""}"""),format.raw/*116.11*/(""");
	     		"""),format.raw/*117.9*/("""}"""),format.raw/*117.10*/("""
	     	"""),format.raw/*118.8*/("""}"""),format.raw/*118.9*/("""
        """),format.raw/*119.9*/("""}"""),format.raw/*119.10*/(""");
	"""),format.raw/*120.2*/("""}"""),format.raw/*120.3*/("""

"""),format.raw/*122.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listBodegas:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listBodegas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listBodegas) => apply(mapDiccionario,mapPermiso,userMnu,listBodegas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/tipoEstadoAgrega.scala.html
                  HASH: 6a826f283f52f29a9feb1c5e46161e4974d90f8f
                  MATRIX: 1033->1|1256->131|1289->139|1305->147|1344->149|1372->152|1440->200|1468->202|1544->253|1642->331|1672->334|2670->1305|2693->1319|2728->1333|2757->1334|2969->1519|3010->1544|3049->1545|3088->1556|3131->1572|3145->1577|3173->1584|3203->1587|3217->1592|3245->1599|3296->1619|3332->1628|3839->2107|3879->2108|3914->2116|3990->2165|4037->2191|4332->2443|4365->2449|4946->3000|4977->3004|5068->3067|5097->3068|5129->3073|5222->3139|5250->3140|5317->3179|5346->3180|5376->3183|5486->3265|5515->3266|5557->3279|5826->3519|5856->3520|5893->3529|5945->3552|5975->3553|6014->3563|6125->3645|6155->3646|6195->3657|6254->3687|6284->3688|6323->3699|6353->3700|6390->3709|6441->3731|6471->3732|6510->3742|6576->3779|6606->3780|6646->3791|6705->3821|6735->3822|6774->3833|6804->3834|6840->3842|6869->3843|6906->3852|6936->3853|6968->3857|6997->3858|7027->3860
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|68->37|68->37|68->37|68->37|73->42|73->42|73->42|74->43|74->43|74->43|74->43|74->43|74->43|74->43|75->44|76->45|89->58|89->58|90->59|91->60|91->60|100->69|101->70|117->86|121->90|123->92|123->92|124->93|125->94|125->94|127->96|127->96|128->97|130->99|130->99|131->100|138->107|138->107|139->108|139->108|139->108|140->109|140->109|140->109|141->110|142->111|142->111|143->112|143->112|144->113|144->113|144->113|145->114|145->114|145->114|146->115|147->116|147->116|148->117|148->117|149->118|149->118|150->119|150->119|151->120|151->120|153->122
                  -- GENERATED --
              */
          