
package viewsMnuCotiOdo.html

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

object otOdoListaCambiarEstado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[tables.OtEstado],List[tables.OperadorServicio],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listCotizaciones: List[List[String]], listEstados: List[tables.OtEstado], listOperadoresServicio: List[tables.OperadorServicio]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "LISTADO DE "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase()+" ODO (CAMBIAR ESTADO)","(confirmadas y pendientes de confirmar)")),format.raw/*14.167*/("""
			"""),format.raw/*15.4*/("""<input type="hidden" id="cambiosDeEstados" name="cambiosDeEstados">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>Nro. """),_display_(/*21.19*/mapDiccionario/*21.33*/.get("OT")),format.raw/*21.43*/("""</TH>
								<TH style="min-width:80px;">FECHA<br>"""),_display_(/*22.47*/mapDiccionario/*22.61*/.get("OT")),format.raw/*22.71*/("""</TH>
								<TH>Nro. COTI</TH>
								<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
								<TH>"""),_display_(/*25.14*/mapDiccionario/*25.28*/.get("BODEGA")),format.raw/*25.42*/("""/PROYECTO</TH>
								<TH>CLIENTE</TH>
								<TH>PROYECTO</TH>
								<TH>OBSERVACIONES<br>"""),_display_(/*28.31*/mapDiccionario/*28.45*/.get("OT")),format.raw/*28.55*/("""</TH>
								<TH>OBSERVACIONES<br>COTIZACION</TH>
								<TH>DOC<br>"""),_display_(/*30.21*/mapDiccionario/*30.35*/.get("OT")),format.raw/*30.45*/("""</TH>
								<TH>DOC<br>COTI</TH>
								<TH>ULTIMA<br>ACTUALIZACION</TH>
								<TH>FECHA<br>DE SERVICIO</TH>
								<TH>OPERADOR</TH>
								<TH>ESTADO</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*39.9*/for(lista1 <- listCotizaciones) yield /*39.40*/{_display_(Seq[Any](format.raw/*39.41*/("""
								"""),format.raw/*40.9*/("""<TR>
									<td style="text-align:center;">
										<a href="#" onclick="verOt('"""),_display_(/*42.40*/lista1/*42.46*/.get(0)),format.raw/*42.53*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*44.34*/lista1/*44.40*/.get(2)),format.raw/*44.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td style="min-width: 80px; text-align:center;">
										<div style="display:none">"""),_display_(/*49.38*/lista1/*49.44*/.get(3)),format.raw/*49.51*/("""</div>
										"""),_display_(/*50.12*/utilities/*50.21*/.Fechas.DDMMAA(lista1.get(3))),format.raw/*50.50*/("""
									"""),format.raw/*51.10*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="verCotizacion('"""),_display_(/*53.48*/lista1/*53.54*/.get(1)),format.raw/*53.61*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*55.34*/lista1/*55.40*/.get(4)),format.raw/*55.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td style="min-width: 80px; text-align:center;">
										<div style="display:none">"""),_display_(/*60.38*/lista1/*60.44*/.get(5)),format.raw/*60.51*/("""</div>
										"""),_display_(/*61.12*/utilities/*61.21*/.Fechas.DDMMAA(lista1.get(5))),format.raw/*61.50*/("""
									"""),format.raw/*62.10*/("""</td>
									<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*63.74*/lista1/*63.80*/.get(14)),format.raw/*63.88*/("""')">"""),_display_(/*63.93*/lista1/*63.99*/.get(14)),format.raw/*63.107*/("""</a></td>
									<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*64.75*/lista1/*64.81*/.get(6)),format.raw/*64.88*/("""')">"""),_display_(/*64.93*/lista1/*64.99*/.get(6)),format.raw/*64.106*/("""</a></td>
									<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*65.76*/lista1/*65.82*/.get(7)),format.raw/*65.89*/("""')">"""),_display_(/*65.94*/lista1/*65.100*/.get(7)),format.raw/*65.107*/("""</a></td>
									<td style="text-align:left;">"""),_display_(/*66.40*/lista1/*66.46*/.get(8)),format.raw/*66.53*/("""</td>
									<td style="text-align:left;">"""),_display_(/*67.40*/lista1/*67.46*/.get(9)),format.raw/*67.53*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(10).equals("0"))/*69.42*/{_display_(Seq[Any](format.raw/*69.43*/("""
											"""),format.raw/*70.12*/("""--
										""")))}else/*71.16*/{_display_(Seq[Any](format.raw/*71.17*/("""
											"""),format.raw/*72.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*72.53*/lista1/*72.59*/.get(10)),format.raw/*72.67*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*75.12*/("""
									"""),format.raw/*76.10*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(11).equals("0"))/*78.42*/{_display_(Seq[Any](format.raw/*78.43*/("""
											"""),format.raw/*79.12*/("""--
										""")))}else/*80.16*/{_display_(Seq[Any](format.raw/*80.17*/("""
											"""),format.raw/*81.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*81.53*/lista1/*81.59*/.get(11)),format.raw/*81.67*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*84.12*/("""
									"""),format.raw/*85.10*/("""</td>
									<td style="min-width: 80px; text-align:center;">
										<div style="display: none;">"""),_display_(/*87.40*/lista1/*87.46*/.get(16)),format.raw/*87.54*/("""</div>
										<div id="fechaActual_"""),_display_(/*88.33*/lista1/*88.39*/.get(0)),format.raw/*88.46*/("""">"""),_display_(/*88.49*/utilities/*88.58*/.Fechas.DDMMAA(lista1.get(16))),format.raw/*88.88*/("""</div>
									</td>
									<td>
										<div style="display: none;">"""),_display_(/*91.40*/lista1/*91.46*/.get(17)),format.raw/*91.54*/("""</div>
										<input type="date" class="form-control center"
									  			name="fechaEntrega" 
												value=""""),_display_(/*94.21*/lista1/*94.27*/.get(17)),format.raw/*94.35*/(""""
									  			onblur="if(!limitaFecha(value,30,360)) value='';"
												onchange="actualizarFechaActual('"""),_display_(/*96.47*/lista1/*96.53*/.get(0)),format.raw/*96.60*/("""',value)">
									</td>
									<td>
										<select name="id_operadorServicio" class="custom-select"  style="width: 150px;"
											onchange="actualizarOperadorServicio('"""),_display_(/*100.51*/lista1/*100.57*/.get(0)),format.raw/*100.64*/("""',value)">
											<option value=""""),_display_(/*101.28*/lista1/*101.34*/.get(15)),format.raw/*101.42*/(""""> """),_display_(/*101.46*/lista1/*101.52*/.get(18)),format.raw/*101.60*/("""</option>
												"""),_display_(/*102.14*/for(lista2 <- listOperadoresServicio) yield /*102.51*/{_display_(Seq[Any](format.raw/*102.52*/("""
													"""),format.raw/*103.14*/("""<option value=""""),_display_(/*103.30*/lista2/*103.36*/.getId()),format.raw/*103.44*/("""">"""),_display_(/*103.47*/lista2/*103.53*/.getNombre()),format.raw/*103.65*/("""</option>
												""")))}),format.raw/*104.14*/("""
												"""),format.raw/*105.13*/("""<option value="0"></option>
										</select>
									</td>
									<td  style="min-width: 100px; text-align:center;">
										<select class="custom-select height23px font10px" onchange="cambiarEstado('"""),_display_(/*109.87*/lista1/*109.93*/.get(0)),format.raw/*109.100*/("""', value)">
											<option value=""""),_display_(/*110.28*/lista1/*110.34*/.get(12)),format.raw/*110.42*/("""">"""),_display_(/*110.45*/lista1/*110.51*/.get(13)),format.raw/*110.59*/("""</option>
											"""),_display_(/*111.13*/for(lista <- listEstados) yield /*111.38*/{_display_(Seq[Any](format.raw/*111.39*/("""
												"""),format.raw/*112.13*/("""<option value=""""),_display_(/*112.29*/lista/*112.34*/.id),format.raw/*112.37*/("""">"""),_display_(/*112.40*/lista/*112.45*/.estado),format.raw/*112.52*/("""</option>
											""")))}),format.raw/*113.13*/("""
										"""),format.raw/*114.11*/("""</select>
									</td>
								</TR>
				 			""")))}),format.raw/*117.10*/("""
						"""),format.raw/*118.7*/("""</tbody>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
				</div>
			</div>
	</div>
	
	<div id='modalVerOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>"""),_display_(/*135.31*/mapDiccionario/*135.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*135.69*/("""</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id='mostrarLaOt'></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalVerCotizacion' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>COTIZACION</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id='mostrarLaCotizacion'></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	

""")))}),format.raw/*180.2*/("""


"""),format.raw/*183.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*184.31*/("""{"""),format.raw/*184.32*/("""
		  """),format.raw/*185.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*185.36*/("""{"""),format.raw/*185.37*/("""
		    	"""),format.raw/*186.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*189.20*/("""{"""),format.raw/*189.21*/("""
		        	"""),format.raw/*190.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*191.11*/("""}"""),format.raw/*191.12*/("""
		  """),format.raw/*192.5*/("""}"""),format.raw/*192.6*/(""" """),format.raw/*192.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*194.2*/("""}"""),format.raw/*194.3*/(""");
	
	
	const verOt = (id_otOdo) => """),format.raw/*197.30*/("""{"""),format.raw/*197.31*/("""
		"""),format.raw/*198.3*/("""var formData = new FormData();
	  	formData.append('id_otOdo',id_otOdo);
        $.ajax("""),format.raw/*200.16*/("""{"""),format.raw/*200.17*/("""
            """),format.raw/*201.13*/("""url: "/routes2/verOtOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*208.36*/("""{"""),format.raw/*208.37*/("""
	     		"""),format.raw/*209.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*211.8*/("""}"""),format.raw/*211.9*/("""
        """),format.raw/*212.9*/("""}"""),format.raw/*212.10*/(""");
	"""),format.raw/*213.2*/("""}"""),format.raw/*213.3*/("""
	
	"""),format.raw/*215.2*/("""const actualizarFechaActual = (id_ot, fecha) =>"""),format.raw/*215.49*/("""{"""),format.raw/*215.50*/("""
		"""),format.raw/*216.3*/("""var formData = new FormData();
		formData.append('id_ot',id_ot);
	  	formData.append('fechaEntrega',fecha);
        $.ajax("""),format.raw/*219.16*/("""{"""),format.raw/*219.17*/("""
            """),format.raw/*220.13*/("""url: "/routes2/actualFechaEnvioOtOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*227.36*/("""{"""),format.raw/*227.37*/("""
				"""),format.raw/*228.5*/("""if(respuesta != "error")"""),format.raw/*228.29*/("""{"""),format.raw/*228.30*/("""
					"""),format.raw/*229.6*/("""$('#fechaActual_'+id_ot).text(respuesta);
				"""),format.raw/*230.5*/("""}"""),format.raw/*230.6*/("""
	     	"""),format.raw/*231.8*/("""}"""),format.raw/*231.9*/("""
        """),format.raw/*232.9*/("""}"""),format.raw/*232.10*/(""");
	"""),format.raw/*233.2*/("""}"""),format.raw/*233.3*/("""
	
	"""),format.raw/*235.2*/("""const actualizarOperadorServicio = (id_ot, id_operadorServicio) =>"""),format.raw/*235.68*/("""{"""),format.raw/*235.69*/("""
		"""),format.raw/*236.3*/("""var formData = new FormData();
		formData.append('id_ot',id_ot);
	  	formData.append('id_operadorServicio',id_operadorServicio);
        $.ajax("""),format.raw/*239.16*/("""{"""),format.raw/*239.17*/("""
            """),format.raw/*240.13*/("""url: "/routes2/actualOperServOtOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*247.36*/("""{"""),format.raw/*247.37*/("""
				"""),format.raw/*248.5*/("""if(respuesta == "error")"""),format.raw/*248.29*/("""{"""),format.raw/*248.30*/("""
					"""),format.raw/*249.6*/("""location.reload();
				"""),format.raw/*250.5*/("""}"""),format.raw/*250.6*/("""
	     	"""),format.raw/*251.8*/("""}"""),format.raw/*251.9*/("""
        """),format.raw/*252.9*/("""}"""),format.raw/*252.10*/(""");
	"""),format.raw/*253.2*/("""}"""),format.raw/*253.3*/("""
	
	"""),format.raw/*255.2*/("""const verCotizacion = (id_cotiOdo) => """),format.raw/*255.40*/("""{"""),format.raw/*255.41*/("""
		"""),format.raw/*256.3*/("""var formData = new FormData();
	  	formData.append('id_cotiOdo',id_cotiOdo);
        $.ajax("""),format.raw/*258.16*/("""{"""),format.raw/*258.17*/("""
            """),format.raw/*259.13*/("""url: "/routes2/verCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*266.36*/("""{"""),format.raw/*266.37*/("""
	     		"""),format.raw/*267.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*269.8*/("""}"""),format.raw/*269.9*/("""
        """),format.raw/*270.9*/("""}"""),format.raw/*270.10*/(""");
	"""),format.raw/*271.2*/("""}"""),format.raw/*271.3*/("""
	
	"""),format.raw/*273.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*273.43*/("""{"""),format.raw/*273.44*/("""
		  """),format.raw/*274.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*276.2*/("""}"""),format.raw/*276.3*/("""
	
	"""),format.raw/*278.2*/("""const cambiarEstado = (id_otOdo, id_otEstado) => """),format.raw/*278.51*/("""{"""),format.raw/*278.52*/("""
		"""),format.raw/*279.3*/("""var formData = new FormData();
	  	formData.append('id_otOdo',id_otOdo);
	  	formData.append('id_otEstado',id_otEstado);
        $.ajax("""),format.raw/*282.16*/("""{"""),format.raw/*282.17*/("""
            """),format.raw/*283.13*/("""url: "/routes2/cambiarOtOdoEstadoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*290.36*/("""{"""),format.raw/*290.37*/("""}"""),format.raw/*290.38*/("""
        """),format.raw/*291.9*/("""}"""),format.raw/*291.10*/(""");
	"""),format.raw/*292.2*/("""}"""),format.raw/*292.3*/("""
		
		
	
	
	
"""),format.raw/*298.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listCotizaciones:List[List[String]],listEstados:List[tables.OtEstado],listOperadoresServicio:List[tables.OperadorServicio]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones,listEstados,listOperadoresServicio)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[tables.OtEstado],List[tables.OperadorServicio]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listCotizaciones,listEstados,listOperadoresServicio) => apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones,listEstados,listOperadoresServicio)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/otOdoListaCambiarEstado.scala.html
                  HASH: fabb4647586c1dba5d108a422de3c6f3c5cd4313
                  MATRIX: 1093->1|1412->227|1445->235|1461->243|1500->245|1529->249|1597->297|1627->302|1682->337|1711->340|1754->362|1783->365|1827->388|1858->392|1935->443|2120->606|2151->610|2541->973|2564->987|2595->997|2674->1049|2697->1063|2728->1073|2862->1180|2885->1194|2920->1208|3043->1304|3066->1318|3097->1328|3195->1399|3218->1413|3249->1423|3489->1637|3536->1668|3575->1669|3611->1678|3723->1763|3738->1769|3766->1776|3892->1875|3907->1881|3935->1888|4113->2039|4128->2045|4156->2052|4201->2070|4219->2079|4269->2108|4307->2118|4428->2212|4443->2218|4471->2225|4597->2324|4612->2330|4640->2337|4818->2488|4833->2494|4861->2501|4906->2519|4924->2528|4974->2557|5012->2567|5118->2646|5133->2652|5162->2660|5194->2665|5209->2671|5239->2679|5350->2763|5365->2769|5393->2776|5425->2781|5440->2787|5469->2794|5581->2879|5596->2885|5624->2892|5656->2897|5672->2903|5701->2910|5777->2959|5792->2965|5820->2972|5892->3017|5907->3023|5935->3030|6050->3118|6089->3119|6129->3131|6166->3149|6205->3150|6245->3162|6313->3203|6328->3209|6357->3217|6481->3310|6519->3320|6634->3408|6673->3409|6713->3421|6750->3439|6789->3440|6829->3452|6897->3493|6912->3499|6941->3507|7065->3600|7103->3610|7233->3713|7248->3719|7277->3727|7343->3766|7358->3772|7386->3779|7416->3782|7434->3791|7485->3821|7587->3896|7602->3902|7631->3910|7777->4029|7792->4035|7821->4043|7960->4155|7975->4161|8003->4168|8211->4348|8227->4354|8256->4361|8322->4399|8338->4405|8368->4413|8400->4417|8416->4423|8446->4431|8497->4454|8551->4491|8591->4492|8634->4506|8678->4522|8694->4528|8724->4536|8755->4539|8771->4545|8805->4557|8860->4580|8902->4593|9139->4802|9155->4808|9185->4815|9252->4854|9268->4860|9298->4868|9329->4871|9345->4877|9375->4885|9425->4907|9467->4932|9507->4933|9549->4946|9593->4962|9608->4967|9633->4970|9664->4973|9679->4978|9708->4985|9762->5007|9802->5018|9882->5066|9917->5073|10584->5712|10608->5726|10654->5750|12207->7272|12238->7275|12329->7337|12359->7338|12392->7343|12452->7374|12482->7375|12518->7383|12708->7544|12738->7545|12779->7557|12886->7635|12916->7636|12949->7641|12978->7642|13007->7643|13108->7716|13137->7717|13202->7753|13232->7754|13263->7757|13380->7845|13410->7846|13452->7859|13714->8092|13744->8093|13781->8102|13918->8211|13947->8212|13984->8221|14014->8222|14046->8226|14075->8227|14107->8231|14183->8278|14213->8279|14244->8282|14396->8405|14426->8406|14468->8419|14743->8665|14773->8666|14806->8671|14859->8695|14889->8696|14923->8702|14997->8748|15026->8749|15062->8757|15091->8758|15128->8767|15158->8768|15190->8772|15219->8773|15251->8777|15346->8843|15376->8844|15407->8847|15580->8991|15610->8992|15652->9005|15925->9249|15955->9250|15988->9255|16041->9279|16071->9280|16105->9286|16156->9309|16185->9310|16221->9318|16250->9319|16287->9328|16317->9329|16349->9333|16378->9334|16410->9338|16477->9376|16507->9377|16538->9380|16659->9472|16689->9473|16731->9486|16995->9721|17025->9722|17062->9731|17215->9856|17244->9857|17281->9866|17311->9867|17343->9871|17372->9872|17404->9876|17474->9917|17504->9918|17537->9923|17669->10027|17698->10028|17730->10032|17808->10081|17838->10082|17869->10085|18034->10221|18064->10222|18106->10235|18378->10478|18408->10479|18438->10480|18475->10489|18505->10490|18537->10494|18566->10495|18607->10508
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|52->21|52->21|52->21|53->22|53->22|53->22|56->25|56->25|56->25|59->28|59->28|59->28|61->30|61->30|61->30|70->39|70->39|70->39|71->40|73->42|73->42|73->42|75->44|75->44|75->44|80->49|80->49|80->49|81->50|81->50|81->50|82->51|84->53|84->53|84->53|86->55|86->55|86->55|91->60|91->60|91->60|92->61|92->61|92->61|93->62|94->63|94->63|94->63|94->63|94->63|94->63|95->64|95->64|95->64|95->64|95->64|95->64|96->65|96->65|96->65|96->65|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|100->69|100->69|101->70|102->71|102->71|103->72|103->72|103->72|103->72|106->75|107->76|109->78|109->78|110->79|111->80|111->80|112->81|112->81|112->81|112->81|115->84|116->85|118->87|118->87|118->87|119->88|119->88|119->88|119->88|119->88|119->88|122->91|122->91|122->91|125->94|125->94|125->94|127->96|127->96|127->96|131->100|131->100|131->100|132->101|132->101|132->101|132->101|132->101|132->101|133->102|133->102|133->102|134->103|134->103|134->103|134->103|134->103|134->103|134->103|135->104|136->105|140->109|140->109|140->109|141->110|141->110|141->110|141->110|141->110|141->110|142->111|142->111|142->111|143->112|143->112|143->112|143->112|143->112|143->112|143->112|144->113|145->114|148->117|149->118|166->135|166->135|166->135|211->180|214->183|215->184|215->184|216->185|216->185|216->185|217->186|220->189|220->189|221->190|222->191|222->191|223->192|223->192|223->192|225->194|225->194|228->197|228->197|229->198|231->200|231->200|232->201|239->208|239->208|240->209|242->211|242->211|243->212|243->212|244->213|244->213|246->215|246->215|246->215|247->216|250->219|250->219|251->220|258->227|258->227|259->228|259->228|259->228|260->229|261->230|261->230|262->231|262->231|263->232|263->232|264->233|264->233|266->235|266->235|266->235|267->236|270->239|270->239|271->240|278->247|278->247|279->248|279->248|279->248|280->249|281->250|281->250|282->251|282->251|283->252|283->252|284->253|284->253|286->255|286->255|286->255|287->256|289->258|289->258|290->259|297->266|297->266|298->267|300->269|300->269|301->270|301->270|302->271|302->271|304->273|304->273|304->273|305->274|307->276|307->276|309->278|309->278|309->278|310->279|313->282|313->282|314->283|321->290|321->290|321->290|322->291|322->291|323->292|323->292|329->298
                  -- GENERATED --
              */
          