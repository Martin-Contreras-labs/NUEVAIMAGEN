
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

object tipoEstadoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.TipoEstado,List[tables.TipoReparacion],List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tipoEstado: tables.TipoEstado, listTipoReparacion: List[tables.TipoReparacion], listBodegas: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR TIPO DE ESTADO (DEVOLUCIONES)", "")),format.raw/*9.78*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td style="text-align:left;">SIGLA: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="sigla"
								autocomplete="off"
								value=""""),_display_(/*19.17*/tipoEstado/*19.27*/.getSigla()),format.raw/*19.38*/(""""
								maxlength="5"
								onchange="value = value.trim(); modificarTipoEstado(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombre"
								onkeydown="return sinReservados(window.event)"
								autocomplete="off"
								value=""""),_display_(/*31.17*/tipoEstado/*31.27*/.getNombre()),format.raw/*31.39*/(""""
								maxlength="50"
								onchange="value = value.trim(); modificarTipoEstado(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">"""),_display_(/*37.37*/mapDiccionario/*37.51*/.get("BODEGA")),format.raw/*37.65*/(""" """),format.raw/*37.66*/("""ASOCIADA: </td>
						<td style="text-align:left;">
							<select class="custom-select"
								id="id_bodegaAsociada"
								onchange="modificarTipoEstado(id);">
								<option value=""""),_display_(/*42.25*/tipoEstado/*42.35*/.getId_bodegaAsociada),format.raw/*42.56*/("""">"""),_display_(/*42.59*/tipoEstado/*42.69*/.getBodegaAsociada),format.raw/*42.87*/("""</option>
								"""),_display_(/*43.10*/for(lista <- listBodegas) yield /*43.35*/{_display_(Seq[Any](format.raw/*43.36*/("""
									"""),format.raw/*44.10*/("""<option value=""""),_display_(/*44.26*/lista/*44.31*/.get(1)),format.raw/*44.38*/("""">"""),_display_(/*44.41*/lista/*44.46*/.get(5)),format.raw/*44.53*/("""</option>
								""")))}),format.raw/*45.10*/("""
								"""),format.raw/*46.9*/("""<option value="0">No Asociada</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">APLICA LISTA PRECIOS: </td>
						<td style="text-align:left;">
							<select class="custom-select"
								id="reparable"
								onchange="modificarTipoEstado(id);">
									<option value="1">SI</option>
									<option value="0">NO</option>
							</select>
						</td>
					</tr>
					"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1"))/*61.145*/{_display_(Seq[Any](format.raw/*61.146*/("""
						"""),format.raw/*62.7*/("""<tr>
							<td style="text-align:left;">COBRA """),_display_(/*63.44*/mapDiccionario("ARRIENDO")),format.raw/*63.70*/(""": </td>
							<td style="text-align:left;">
								<select class="custom-select"
									id="cobraArriendo"
									onchange="modificarCobraArriendo(id);">
										<option value="1">SI</option>
										<option value="0">NO</option>
								</select>
							</td>
						</tr>
					""")))} else {null} ),format.raw/*73.7*/("""
					"""),format.raw/*74.6*/("""<tr>
						<td colspan="2">
							<table class="table table-sm table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<tr>
										<th colspan="10" style="text-align:left">
											<font color="#008000"> LISTA DE PRECIOS DEFINIDA: </font>
										</th>
									</tr>
									<tr>
										<TH>SIGLA</TH>
										<TH>NOMBRE</TH>
										<TH>MONEDA</TH>
										<TH>PRECIO</TH>
										<TH>DESCRIPCION</TH>
										<TH>Edit</TH>
										<TH>Del</TH>
									</tr>
								</thead>
								<tbody>
									"""),_display_(/*94.11*/for(lista <- listTipoReparacion) yield /*94.43*/{_display_(Seq[Any](format.raw/*94.44*/("""
										"""),format.raw/*95.11*/("""<tr>
									        <td style="text-align:left;">"""),_display_(/*96.48*/lista/*96.53*/.getSigla()),format.raw/*96.64*/("""</td>
											<td style="text-align:left;">"""),_display_(/*97.42*/lista/*97.47*/.getNombre()),format.raw/*97.59*/("""</td>
											<td style="text-align:center;">"""),_display_(/*98.44*/lista/*98.49*/.getMoneda()),format.raw/*98.61*/("""</td>
											<td style="text-align:right;">"""),_display_(/*99.43*/lista/*99.48*/.getPrecio()),format.raw/*99.60*/("""</td>
											<td style="text-align:left;">"""),_display_(/*100.42*/lista/*100.47*/.getDescripcion()),format.raw/*100.64*/("""</td>
											<td  style="text-align:center;">
												<a href="/tipoReparacionModifica/"""),_display_(/*102.47*/lista/*102.52*/.getId()),format.raw/*102.60*/(""","""),_display_(/*102.62*/tipoEstado/*102.72*/.getId()),format.raw/*102.80*/("""">
													<kbd style="background-color: #73C6B6">E</kbd>
												</a>
											</td>
											<td  style="text-align:center;">
												<a href="#" onclick= "eliminarReparacion('"""),_display_(/*107.56*/lista/*107.61*/.getId()),format.raw/*107.69*/("""')">
													<kbd style="background-color: red">X</kbd>
												</a>
											</td>
										</tr>
									""")))}),format.raw/*112.11*/("""
									"""),format.raw/*113.10*/("""<td colspan=7>
										<div align="center">
											<input type="button" class="btn btn-light btn-sm rounded-pill"
												onclick="location.href='/tipoReparacionAgrega/"""),_display_(/*116.60*/tipoEstado/*116.70*/.getId()),format.raw/*116.78*/("""'"
												value = "Agregar Reparación">
										</div>
									</td>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/tipoEstadoMantencion/';">
				</div>
			</div>
		</div>
	</div>

	<form id="form_eliminar" method="post" action="/tipoReparacionElimina/">
		<input type="hidden" id="form_id_tipoReparacion" name="id_tipoReparacion">
		<input type="hidden" id="form_id_tipoEstado" name="id_tipoEstado">
	</form>

""")))}),format.raw/*141.2*/("""



"""),format.raw/*145.1*/("""<script type="text/javascript">
	let id_tipoEstado = '"""),_display_(/*146.24*/tipoEstado/*146.34*/.getId()),format.raw/*146.42*/("""';

	$(document).ready(function() """),format.raw/*148.31*/("""{"""),format.raw/*148.32*/("""
		  """),format.raw/*149.5*/("""$('#id_bodegaAsociada > option[value=""""),_display_(/*149.44*/tipoEstado/*149.54*/.getId_bodegaAsociada()),format.raw/*149.77*/(""""]').attr('selected', 'selected');
		  $('#reparable > option[value=""""),_display_(/*150.36*/tipoEstado/*150.46*/.getReparable()),format.raw/*150.61*/(""""]').attr('selected', 'selected');
		  $('#cobraArriendo > option[value=""""),_display_(/*151.40*/tipoEstado/*151.50*/.getCobraArriendo()),format.raw/*151.69*/(""""]').attr('selected', 'selected');
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*153.2*/("""}"""),format.raw/*153.3*/(""");
	
	const modificarTipoEstado = (campo) => """),format.raw/*155.41*/("""{"""),format.raw/*155.42*/("""
		"""),format.raw/*156.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_tipoEstado',id_tipoEstado);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*161.16*/("""{"""),format.raw/*161.17*/("""
            """),format.raw/*162.13*/("""url: "/modificaTipoEstadoPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*169.36*/("""{"""),format.raw/*169.37*/("""
	     		"""),format.raw/*170.9*/("""if(respuesta=="existe")"""),format.raw/*170.32*/("""{"""),format.raw/*170.33*/("""
	     			"""),format.raw/*171.10*/("""alertify.alert('La sigla asignada ya existe, intente con otra', function () """),format.raw/*171.86*/("""{"""),format.raw/*171.87*/("""
	     				"""),format.raw/*172.11*/("""$("#sigla").val(""""),_display_(/*172.29*/tipoEstado/*172.39*/.getSigla()),format.raw/*172.50*/("""");
		     		"""),format.raw/*173.10*/("""}"""),format.raw/*173.11*/(""");
	     		"""),format.raw/*174.9*/("""}"""),format.raw/*174.10*/("""else if(respuesta=="error")"""),format.raw/*174.37*/("""{"""),format.raw/*174.38*/("""
	     			"""),format.raw/*175.10*/("""alertify.alert(msgError, function () """),format.raw/*175.47*/("""{"""),format.raw/*175.48*/("""
		     			"""),format.raw/*176.11*/("""location.href = "/";
		     		"""),format.raw/*177.10*/("""}"""),format.raw/*177.11*/(""");
	     		"""),format.raw/*178.9*/("""}"""),format.raw/*178.10*/("""
	     	"""),format.raw/*179.8*/("""}"""),format.raw/*179.9*/("""
        """),format.raw/*180.9*/("""}"""),format.raw/*180.10*/(""");
	"""),format.raw/*181.2*/("""}"""),format.raw/*181.3*/("""

	"""),format.raw/*183.2*/("""const modificarCobraArriendo = (campo) => """),format.raw/*183.44*/("""{"""),format.raw/*183.45*/("""
		"""),format.raw/*184.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
		formData.append('campo',campo);
		formData.append('id_tipoEstado',id_tipoEstado);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*189.10*/("""{"""),format.raw/*189.11*/("""
			"""),format.raw/*190.4*/("""url: "/modificaTipoEstadoPorCampoAjax/",
			type: "POST",
			method: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(respuesta)"""),format.raw/*197.32*/("""{"""),format.raw/*197.33*/("""
				"""),format.raw/*198.5*/("""if(respuesta=="existe")"""),format.raw/*198.28*/("""{"""),format.raw/*198.29*/("""
					"""),format.raw/*199.6*/("""alertify.alert('La sigla asignada ya existe, intente con otra', function () """),format.raw/*199.82*/("""{"""),format.raw/*199.83*/("""
						"""),format.raw/*200.7*/("""$("#sigla").val(""""),_display_(/*200.25*/tipoEstado/*200.35*/.getSigla()),format.raw/*200.46*/("""");
					"""),format.raw/*201.6*/("""}"""),format.raw/*201.7*/(""");
				"""),format.raw/*202.5*/("""}"""),format.raw/*202.6*/("""else if(respuesta=="error")"""),format.raw/*202.33*/("""{"""),format.raw/*202.34*/("""
					"""),format.raw/*203.6*/("""alertify.alert(msgError, function () """),format.raw/*203.43*/("""{"""),format.raw/*203.44*/("""
						"""),format.raw/*204.7*/("""location.href = "/";
					"""),format.raw/*205.6*/("""}"""),format.raw/*205.7*/(""");
				"""),format.raw/*206.5*/("""}"""),format.raw/*206.6*/("""
			"""),format.raw/*207.4*/("""}"""),format.raw/*207.5*/("""
		"""),format.raw/*208.3*/("""}"""),format.raw/*208.4*/(""");
	"""),format.raw/*209.2*/("""}"""),format.raw/*209.3*/("""
	
	"""),format.raw/*211.2*/("""const eliminarReparacion = (id_tipoReparacion) => """),format.raw/*211.52*/("""{"""),format.raw/*211.53*/("""
		"""),format.raw/*212.3*/("""alertify.confirm("Esta seguro de eliminar este tipo de reparación", function (e) """),format.raw/*212.84*/("""{"""),format.raw/*212.85*/("""
			"""),format.raw/*213.4*/("""if (e) """),format.raw/*213.11*/("""{"""),format.raw/*213.12*/("""
				"""),format.raw/*214.5*/("""$("#form_id_tipoReparacion").val(id_tipoReparacion);
				$("#form_id_tipoEstado").val(id_tipoEstado);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*217.4*/("""}"""),format.raw/*217.5*/("""
		"""),format.raw/*218.3*/("""}"""),format.raw/*218.4*/(""");
	"""),format.raw/*219.2*/("""}"""),format.raw/*219.3*/("""

"""),format.raw/*221.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tipoEstado:tables.TipoEstado,listTipoReparacion:List[tables.TipoReparacion],listBodegas:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tipoEstado,listTipoReparacion,listBodegas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.TipoEstado,List[tables.TipoReparacion],List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tipoEstado,listTipoReparacion,listBodegas) => apply(mapDiccionario,mapPermiso,userMnu,tipoEstado,listTipoReparacion,listBodegas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/tipoEstadoModifica.scala.html
                  HASH: 3561016527cee07870e6996681d6f7030665335e
                  MATRIX: 1081->1|1384->211|1412->214|1428->222|1467->224|1495->227|1563->275|1591->277|1667->328|1761->402|1791->405|2203->790|2222->800|2254->811|2659->1189|2678->1199|2711->1211|2898->1371|2921->1385|2956->1399|2985->1400|3201->1589|3220->1599|3262->1620|3292->1623|3311->1633|3350->1651|3396->1670|3437->1695|3476->1696|3514->1706|3557->1722|3571->1727|3599->1734|3629->1737|3643->1742|3671->1749|3721->1768|3757->1777|4340->2332|4380->2333|4414->2340|4489->2388|4536->2414|4868->2703|4901->2709|5506->3287|5554->3319|5593->3320|5632->3331|5711->3383|5725->3388|5757->3399|5831->3446|5845->3451|5878->3463|5954->3512|5968->3517|6001->3529|6076->3577|6090->3582|6123->3594|6198->3641|6213->3646|6252->3663|6376->3759|6391->3764|6421->3772|6451->3774|6471->3784|6501->3792|6725->3988|6740->3993|6770->4001|6923->4122|6962->4132|7169->4311|7189->4321|7219->4329|7957->5036|7989->5040|8072->5095|8092->5105|8122->5113|8185->5147|8215->5148|8248->5153|8315->5192|8335->5202|8380->5225|8478->5295|8498->5305|8535->5320|8637->5394|8657->5404|8698->5423|8831->5528|8860->5529|8934->5574|8964->5575|8995->5578|9228->5782|9258->5783|9300->5796|9572->6039|9602->6040|9639->6049|9691->6072|9721->6073|9760->6083|9865->6159|9895->6160|9935->6171|9981->6189|10001->6199|10034->6210|10076->6223|10106->6224|10145->6235|10175->6236|10231->6263|10261->6264|10300->6274|10366->6311|10396->6312|10436->6323|10495->6353|10525->6354|10564->6365|10594->6366|10630->6374|10659->6375|10696->6384|10726->6385|10758->6389|10787->6390|10818->6393|10889->6435|10919->6436|10950->6439|11171->6631|11201->6632|11233->6636|11452->6826|11482->6827|11515->6832|11567->6855|11597->6856|11631->6862|11736->6938|11766->6939|11801->6946|11847->6964|11867->6974|11900->6985|11937->6994|11966->6995|12001->7002|12030->7003|12086->7030|12116->7031|12150->7037|12216->7074|12246->7075|12281->7082|12335->7108|12364->7109|12399->7116|12428->7117|12460->7121|12489->7122|12520->7125|12549->7126|12581->7130|12610->7131|12642->7135|12721->7185|12751->7186|12782->7189|12892->7270|12922->7271|12954->7275|12990->7282|13020->7283|13053->7288|13241->7448|13270->7449|13301->7452|13330->7453|13362->7457|13391->7458|13421->7460
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|50->19|50->19|50->19|62->31|62->31|62->31|68->37|68->37|68->37|68->37|73->42|73->42|73->42|73->42|73->42|73->42|74->43|74->43|74->43|75->44|75->44|75->44|75->44|75->44|75->44|75->44|76->45|77->46|92->61|92->61|93->62|94->63|94->63|104->73|105->74|125->94|125->94|125->94|126->95|127->96|127->96|127->96|128->97|128->97|128->97|129->98|129->98|129->98|130->99|130->99|130->99|131->100|131->100|131->100|133->102|133->102|133->102|133->102|133->102|133->102|138->107|138->107|138->107|143->112|144->113|147->116|147->116|147->116|172->141|176->145|177->146|177->146|177->146|179->148|179->148|180->149|180->149|180->149|180->149|181->150|181->150|181->150|182->151|182->151|182->151|184->153|184->153|186->155|186->155|187->156|192->161|192->161|193->162|200->169|200->169|201->170|201->170|201->170|202->171|202->171|202->171|203->172|203->172|203->172|203->172|204->173|204->173|205->174|205->174|205->174|205->174|206->175|206->175|206->175|207->176|208->177|208->177|209->178|209->178|210->179|210->179|211->180|211->180|212->181|212->181|214->183|214->183|214->183|215->184|220->189|220->189|221->190|228->197|228->197|229->198|229->198|229->198|230->199|230->199|230->199|231->200|231->200|231->200|231->200|232->201|232->201|233->202|233->202|233->202|233->202|234->203|234->203|234->203|235->204|236->205|236->205|237->206|237->206|238->207|238->207|239->208|239->208|240->209|240->209|242->211|242->211|242->211|243->212|243->212|243->212|244->213|244->213|244->213|245->214|248->217|248->217|249->218|249->218|250->219|250->219|252->221
                  -- GENERATED --
              */
          