
package viewsMnuCotizar.html

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

object otListaCambiarEstado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[tables.OtEstado],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listOt: List[List[String]], listEstados: List[tables.OtEstado]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "LISTADO DE "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase()+" (CAMBIAR ESTADO)","(confirmadas y pendientes de confirmar)")),format.raw/*14.163*/("""
		"""),format.raw/*15.3*/("""<form method="post" action="/cotizaConfirma/">
			<input type="hidden" id="cambiosDeEstados" name="cambiosDeEstados">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>SUCURSAL</TH>
								<TH>Nro. """),_display_(/*23.19*/mapDiccionario/*23.33*/.get("OT")),format.raw/*23.43*/("""</TH>
								<TH style="min-width:80px;">FECHA<br>"""),_display_(/*24.47*/mapDiccionario/*24.61*/.get("OT")),format.raw/*24.71*/("""</TH>
								<TH style="min-width:80px;">CONFIRMADA<br>"""),_display_(/*25.52*/mapDiccionario/*25.66*/.get("OT")),format.raw/*25.76*/("""</TH>
								<TH>Nro. COTI</TH>
								<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
								<TH>"""),_display_(/*28.14*/mapDiccionario/*28.28*/.get("BODEGA")),format.raw/*28.42*/("""/PROYECTO</TH>
								<TH>CLIENTE</TH>
								<TH>PROYECTO</TH>
								<TH>OBSERVACIONES<br>"""),_display_(/*31.31*/mapDiccionario/*31.45*/.get("OT")),format.raw/*31.55*/("""</TH>
								<TH>OBSERVACIONES<br>COTIZACION</TH>
								<TH>DOC<br>"""),_display_(/*33.21*/mapDiccionario/*33.35*/.get("OT")),format.raw/*33.45*/("""</TH>
								<TH>DOC<br>COTI</TH>
								<TH>ULTIMA<br>ACTUALIZACION</TH>
								<TH>ESTIMACION<br>DE ENTREGA</TH>
								<TH>SALDO</TH>
								<TH>ESTADO</TH>
								<TH>NOTAS</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*43.9*/for(lista1 <- listOt) yield /*43.30*/{_display_(Seq[Any](format.raw/*43.31*/("""
								"""),format.raw/*44.9*/("""<TR>
									<td style="text-align:left;">"""),_display_(/*45.40*/lista1/*45.46*/.get(15)),format.raw/*45.54*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="verOt('"""),_display_(/*47.40*/lista1/*47.46*/.get(0)),format.raw/*47.53*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*49.34*/lista1/*49.40*/.get(2)),format.raw/*49.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td style="min-width: 80px; text-align:center;">
										<div style="display:none">"""),_display_(/*54.38*/lista1/*54.44*/.get(3)),format.raw/*54.51*/("""</div>
										"""),_display_(/*55.12*/utilities/*55.21*/.Fechas.DDMMAA(lista1.get(3))),format.raw/*55.50*/("""
									"""),format.raw/*56.10*/("""</td>
									<td style="min-width: 80px; text-align:center;">
										<div style="display:none">"""),_display_(/*58.38*/lista1/*58.44*/.get(19)),format.raw/*58.52*/("""</div>
										"""),_display_(/*59.12*/utilities/*59.21*/.Fechas.DDMMAA(lista1.get(19))),format.raw/*59.51*/("""
									"""),format.raw/*60.10*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="verCotizacion('"""),_display_(/*62.48*/lista1/*62.54*/.get(1)),format.raw/*62.61*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*64.34*/lista1/*64.40*/.get(4)),format.raw/*64.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td style="min-width: 80px; text-align:center;">
										<div style="display:none">"""),_display_(/*69.38*/lista1/*69.44*/.get(5)),format.raw/*69.51*/("""</div>
										"""),_display_(/*70.12*/utilities/*70.21*/.Fechas.DDMMAA(lista1.get(5))),format.raw/*70.50*/("""
									"""),format.raw/*71.10*/("""</td>
									<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*72.74*/lista1/*72.80*/.get(14)),format.raw/*72.88*/("""')">"""),_display_(/*72.93*/lista1/*72.99*/.get(14)),format.raw/*72.107*/("""</a></td>
									<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*73.75*/lista1/*73.81*/.get(6)),format.raw/*73.88*/("""')">"""),_display_(/*73.93*/lista1/*73.99*/.get(6)),format.raw/*73.106*/("""</a></td>
									<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*74.76*/lista1/*74.82*/.get(7)),format.raw/*74.89*/("""')">"""),_display_(/*74.94*/lista1/*74.100*/.get(7)),format.raw/*74.107*/("""</a></td>
									<td style="text-align:left;">"""),_display_(/*75.40*/lista1/*75.46*/.get(8)),format.raw/*75.53*/("""</td>
									<td style="text-align:left;">"""),_display_(/*76.40*/lista1/*76.46*/.get(9)),format.raw/*76.53*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(10).equals("0"))/*78.42*/{_display_(Seq[Any](format.raw/*78.43*/("""
											"""),format.raw/*79.12*/("""--
										""")))}else/*80.16*/{_display_(Seq[Any](format.raw/*80.17*/("""
											"""),format.raw/*81.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*81.53*/lista1/*81.59*/.get(10)),format.raw/*81.67*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*84.12*/("""
									"""),format.raw/*85.10*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(11).equals("0"))/*87.42*/{_display_(Seq[Any](format.raw/*87.43*/("""
											"""),format.raw/*88.12*/("""--
										""")))}else/*89.16*/{_display_(Seq[Any](format.raw/*89.17*/("""
											"""),format.raw/*90.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*90.53*/lista1/*90.59*/.get(11)),format.raw/*90.67*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*93.12*/("""
									"""),format.raw/*94.10*/("""</td>
									<td style="min-width: 80px; text-align:center;">
										<div style="display: none;">"""),_display_(/*96.40*/utilities/*96.49*/.Fechas.AAMMDD(lista1.get(17))),format.raw/*96.79*/("""</div>
										<div id="fechaActual_"""),_display_(/*97.33*/lista1/*97.39*/.get(0)),format.raw/*97.46*/("""">"""),_display_(/*97.49*/lista1/*97.55*/.get(17)),format.raw/*97.63*/("""</div>
									</td>
									<td>
										<div style="display: none;">"""),_display_(/*100.40*/lista1/*100.46*/.get(18)),format.raw/*100.54*/("""</div>
										<input type="date" class="form-control center"
									  			name="fechaEntrega" 
												value=""""),_display_(/*103.21*/lista1/*103.27*/.get(18)),format.raw/*103.35*/(""""
									  			onblur="if(!limitaFecha(value,30,360)) value='';"
												onchange="actualizarFechaActual('"""),_display_(/*105.47*/lista1/*105.53*/.get(0)),format.raw/*105.60*/("""',value)">
									</td>
									<td style="text-align:center;">"""),_display_(/*107.42*/lista1/*107.48*/.get(20)),format.raw/*107.56*/("""</td>
									<td  style="min-width: 100px; text-align:center;">
										<select class="custom-select height23px font10px" onchange="cambiarEstado('"""),_display_(/*109.87*/lista1/*109.93*/.get(0)),format.raw/*109.100*/("""', value)">
											<option value=""""),_display_(/*110.28*/lista1/*110.34*/.get(12)),format.raw/*110.42*/("""">"""),_display_(/*110.45*/lista1/*110.51*/.get(13)),format.raw/*110.59*/("""</option>
											"""),_display_(/*111.13*/for(lista <- listEstados) yield /*111.38*/{_display_(Seq[Any](format.raw/*111.39*/("""
												"""),format.raw/*112.13*/("""<option value=""""),_display_(/*112.29*/lista/*112.34*/.id),format.raw/*112.37*/("""">"""),_display_(/*112.40*/lista/*112.45*/.estado),format.raw/*112.52*/("""</option>
											""")))}),format.raw/*113.13*/("""
										"""),format.raw/*114.11*/("""</select>
									</td>
									<td  style="min-width: 100px; text-align:center;">
										<input type="text" class="form-control left" 
											value = """"),_display_(/*118.22*/lista1/*118.28*/.get(21)),format.raw/*118.36*/(""""
											autocomplete="off" 
											maxlength="150"
											onchange="cambiarNota('"""),_display_(/*121.36*/lista1/*121.42*/.get(0)),format.raw/*121.49*/("""', value)">
									</td>
								</TR>
				 			""")))}),format.raw/*124.10*/("""
						"""),format.raw/*125.7*/("""</tbody>
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
		</form>
	</div>
	
	<div id='modalVerOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>"""),_display_(/*143.31*/mapDiccionario/*143.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*143.69*/("""</h5>
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
	
	

""")))}),format.raw/*188.2*/("""


"""),format.raw/*191.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*192.31*/("""{"""),format.raw/*192.32*/("""
		  """),format.raw/*193.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*193.36*/("""{"""),format.raw/*193.37*/("""
		    	"""),format.raw/*194.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "desc" ]],
		    	"language": """),format.raw/*197.20*/("""{"""),format.raw/*197.21*/("""
		        	"""),format.raw/*198.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*199.11*/("""}"""),format.raw/*199.12*/("""
		  """),format.raw/*200.5*/("""}"""),format.raw/*200.6*/(""" """),format.raw/*200.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*202.2*/("""}"""),format.raw/*202.3*/(""");
	
	
	const verOt = (id_ot) => """),format.raw/*205.27*/("""{"""),format.raw/*205.28*/("""
		"""),format.raw/*206.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*208.16*/("""{"""),format.raw/*208.17*/("""
            """),format.raw/*209.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*216.36*/("""{"""),format.raw/*216.37*/("""
	     		"""),format.raw/*217.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*219.8*/("""}"""),format.raw/*219.9*/("""
        """),format.raw/*220.9*/("""}"""),format.raw/*220.10*/(""");
	"""),format.raw/*221.2*/("""}"""),format.raw/*221.3*/("""
	
	"""),format.raw/*223.2*/("""const actualizarFechaActual = (id_ot, fecha) =>"""),format.raw/*223.49*/("""{"""),format.raw/*223.50*/("""
		"""),format.raw/*224.3*/("""var formData = new FormData();
		formData.append('id_ot',id_ot);
	  	formData.append('fechaEntrega',fecha);
        $.ajax("""),format.raw/*227.16*/("""{"""),format.raw/*227.17*/("""
            """),format.raw/*228.13*/("""url: "/actualFechaEnvioOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*235.36*/("""{"""),format.raw/*235.37*/("""
				"""),format.raw/*236.5*/("""if(respuesta != "error")"""),format.raw/*236.29*/("""{"""),format.raw/*236.30*/("""
					"""),format.raw/*237.6*/("""$('#fechaActual_'+id_ot).text(respuesta);
				"""),format.raw/*238.5*/("""}"""),format.raw/*238.6*/("""
	     	"""),format.raw/*239.8*/("""}"""),format.raw/*239.9*/("""
        """),format.raw/*240.9*/("""}"""),format.raw/*240.10*/(""");
	"""),format.raw/*241.2*/("""}"""),format.raw/*241.3*/("""
	
	
	"""),format.raw/*244.2*/("""const verCotizacion = (id_cotizacion) => """),format.raw/*244.43*/("""{"""),format.raw/*244.44*/("""
		"""),format.raw/*245.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*247.16*/("""{"""),format.raw/*247.17*/("""
            """),format.raw/*248.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*255.36*/("""{"""),format.raw/*255.37*/("""
	     		"""),format.raw/*256.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*258.8*/("""}"""),format.raw/*258.9*/("""
        """),format.raw/*259.9*/("""}"""),format.raw/*259.10*/(""");
	"""),format.raw/*260.2*/("""}"""),format.raw/*260.3*/("""
	
	"""),format.raw/*262.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*262.43*/("""{"""),format.raw/*262.44*/("""
		  """),format.raw/*263.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*265.2*/("""}"""),format.raw/*265.3*/("""
	
	"""),format.raw/*267.2*/("""const cambiarEstado = (id_ot, id_otEstado) => """),format.raw/*267.48*/("""{"""),format.raw/*267.49*/("""
		"""),format.raw/*268.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
	  	formData.append('id_otEstado',id_otEstado);
        $.ajax("""),format.raw/*271.16*/("""{"""),format.raw/*271.17*/("""
            """),format.raw/*272.13*/("""url: "/cambiarOtEstadoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*279.36*/("""{"""),format.raw/*279.37*/("""}"""),format.raw/*279.38*/("""
        """),format.raw/*280.9*/("""}"""),format.raw/*280.10*/(""");
	"""),format.raw/*281.2*/("""}"""),format.raw/*281.3*/("""
	
	"""),format.raw/*283.2*/("""const cambiarNota = (id_ot, nota) => """),format.raw/*283.39*/("""{"""),format.raw/*283.40*/("""
		"""),format.raw/*284.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
	  	formData.append('nota',nota);
        $.ajax("""),format.raw/*287.16*/("""{"""),format.raw/*287.17*/("""
            """),format.raw/*288.13*/("""url: "/cambiarOtNotaAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*295.36*/("""{"""),format.raw/*295.37*/("""}"""),format.raw/*295.38*/("""
        """),format.raw/*296.9*/("""}"""),format.raw/*296.10*/(""");
	"""),format.raw/*297.2*/("""}"""),format.raw/*297.3*/("""
		
	
	
	
"""),format.raw/*302.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listOt:List[List[String]],listEstados:List[tables.OtEstado]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listOt,listEstados)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[tables.OtEstado]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listOt,listEstados) => apply(mapDiccionario,mapPermiso,userMnu,listOt,listEstados)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otListaCambiarEstado.scala.html
                  HASH: e5e0bf652b1b6b1380913b8bb08df206a08b3026
                  MATRIX: 1060->1|1314->162|1347->170|1363->178|1402->180|1431->184|1499->232|1529->237|1584->272|1613->275|1656->297|1685->300|1729->323|1760->327|1837->378|2018->537|2048->540|2514->979|2537->993|2568->1003|2647->1055|2670->1069|2701->1079|2785->1136|2808->1150|2839->1160|2973->1267|2996->1281|3031->1295|3154->1391|3177->1405|3208->1415|3306->1486|3329->1500|3360->1510|3624->1748|3661->1769|3700->1770|3736->1779|3807->1823|3822->1829|3851->1837|3964->1923|3979->1929|4007->1936|4133->2035|4148->2041|4176->2048|4354->2199|4369->2205|4397->2212|4442->2230|4460->2239|4510->2268|4548->2278|4676->2379|4691->2385|4720->2393|4765->2411|4783->2420|4834->2450|4872->2460|4993->2554|5008->2560|5036->2567|5162->2666|5177->2672|5205->2679|5383->2830|5398->2836|5426->2843|5471->2861|5489->2870|5539->2899|5577->2909|5683->2988|5698->2994|5727->3002|5759->3007|5774->3013|5804->3021|5915->3105|5930->3111|5958->3118|5990->3123|6005->3129|6034->3136|6146->3221|6161->3227|6189->3234|6221->3239|6237->3245|6266->3252|6342->3301|6357->3307|6385->3314|6457->3359|6472->3365|6500->3372|6615->3460|6654->3461|6694->3473|6731->3491|6770->3492|6810->3504|6878->3545|6893->3551|6922->3559|7046->3652|7084->3662|7199->3750|7238->3751|7278->3763|7315->3781|7354->3782|7394->3794|7462->3835|7477->3841|7506->3849|7630->3942|7668->3952|7798->4055|7816->4064|7867->4094|7933->4133|7948->4139|7976->4146|8006->4149|8021->4155|8050->4163|8153->4238|8169->4244|8199->4252|8346->4371|8362->4377|8392->4385|8532->4497|8548->4503|8577->4510|8672->4577|8688->4583|8718->4591|8898->4743|8914->4749|8944->4756|9011->4795|9027->4801|9057->4809|9088->4812|9104->4818|9134->4826|9184->4848|9226->4873|9266->4874|9308->4887|9352->4903|9367->4908|9392->4911|9423->4914|9438->4919|9467->4926|9521->4948|9561->4959|9751->5121|9767->5127|9797->5135|9920->5230|9936->5236|9965->5243|10047->5293|10082->5300|10759->5949|10783->5963|10829->5987|12382->7509|12413->7512|12504->7574|12534->7575|12567->7580|12627->7611|12657->7612|12693->7620|12869->7767|12899->7768|12940->7780|13047->7858|13077->7859|13110->7864|13139->7865|13168->7866|13269->7939|13298->7940|13360->7973|13390->7974|13421->7977|13532->8059|13562->8060|13604->8073|13855->8295|13885->8296|13922->8305|14059->8414|14088->8415|14125->8424|14155->8425|14187->8429|14216->8430|14248->8434|14324->8481|14354->8482|14385->8485|14537->8608|14567->8609|14609->8622|14873->8857|14903->8858|14936->8863|14989->8887|15019->8888|15053->8894|15127->8940|15156->8941|15192->8949|15221->8950|15258->8959|15288->8960|15320->8964|15349->8965|15383->8971|15453->9012|15483->9013|15514->9016|15641->9114|15671->9115|15713->9128|15972->9358|16002->9359|16039->9368|16192->9493|16221->9494|16258->9503|16288->9504|16320->9508|16349->9509|16381->9513|16451->9554|16481->9555|16514->9560|16646->9664|16675->9665|16707->9669|16782->9715|16812->9716|16843->9719|17002->9849|17032->9850|17074->9863|17335->10095|17365->10096|17395->10097|17432->10106|17462->10107|17494->10111|17523->10112|17555->10116|17621->10153|17651->10154|17682->10157|17827->10273|17857->10274|17899->10287|18158->10517|18188->10518|18218->10519|18255->10528|18285->10529|18317->10533|18346->10534|18384->10544
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|54->23|54->23|54->23|55->24|55->24|55->24|56->25|56->25|56->25|59->28|59->28|59->28|62->31|62->31|62->31|64->33|64->33|64->33|74->43|74->43|74->43|75->44|76->45|76->45|76->45|78->47|78->47|78->47|80->49|80->49|80->49|85->54|85->54|85->54|86->55|86->55|86->55|87->56|89->58|89->58|89->58|90->59|90->59|90->59|91->60|93->62|93->62|93->62|95->64|95->64|95->64|100->69|100->69|100->69|101->70|101->70|101->70|102->71|103->72|103->72|103->72|103->72|103->72|103->72|104->73|104->73|104->73|104->73|104->73|104->73|105->74|105->74|105->74|105->74|105->74|105->74|106->75|106->75|106->75|107->76|107->76|107->76|109->78|109->78|110->79|111->80|111->80|112->81|112->81|112->81|112->81|115->84|116->85|118->87|118->87|119->88|120->89|120->89|121->90|121->90|121->90|121->90|124->93|125->94|127->96|127->96|127->96|128->97|128->97|128->97|128->97|128->97|128->97|131->100|131->100|131->100|134->103|134->103|134->103|136->105|136->105|136->105|138->107|138->107|138->107|140->109|140->109|140->109|141->110|141->110|141->110|141->110|141->110|141->110|142->111|142->111|142->111|143->112|143->112|143->112|143->112|143->112|143->112|143->112|144->113|145->114|149->118|149->118|149->118|152->121|152->121|152->121|155->124|156->125|174->143|174->143|174->143|219->188|222->191|223->192|223->192|224->193|224->193|224->193|225->194|228->197|228->197|229->198|230->199|230->199|231->200|231->200|231->200|233->202|233->202|236->205|236->205|237->206|239->208|239->208|240->209|247->216|247->216|248->217|250->219|250->219|251->220|251->220|252->221|252->221|254->223|254->223|254->223|255->224|258->227|258->227|259->228|266->235|266->235|267->236|267->236|267->236|268->237|269->238|269->238|270->239|270->239|271->240|271->240|272->241|272->241|275->244|275->244|275->244|276->245|278->247|278->247|279->248|286->255|286->255|287->256|289->258|289->258|290->259|290->259|291->260|291->260|293->262|293->262|293->262|294->263|296->265|296->265|298->267|298->267|298->267|299->268|302->271|302->271|303->272|310->279|310->279|310->279|311->280|311->280|312->281|312->281|314->283|314->283|314->283|315->284|318->287|318->287|319->288|326->295|326->295|326->295|327->296|327->296|328->297|328->297|333->302
                  -- GENERATED --
              */
          