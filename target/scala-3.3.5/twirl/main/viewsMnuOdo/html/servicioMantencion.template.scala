
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

object servicioMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],utilities.Fechas,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listServicios: List[List[String]], hoy: utilities.Fechas):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""

	"""),format.raw/*9.2*/("""<form id="excel" class="formulario" method="post" action="/servicioMantencionExcel/"></form>
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "SERVICIOS (CREAR/MODIFICAR/ELIMINAR)", "")),format.raw/*12.75*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
			
				<div align="center">
					<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
						Exportar a Excel
					</button>
				</div>
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style="vertical-align: top;">FAMILIA</TH>
							<TH>CODIGO</TH>
							<TH>SERVICIO</TH>
							<TH>UNIDAD</TH>
							<TH>MON</TH>
							<TH>PRECIO</TH>
							<TH style="min-width:80px;">Fecha Precio</TH>
							<TH>CANT<br>MINIMO</TH>
							<TH>PRECIO<br>ADICIONAL</TH>
							<TH>EDIT</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*39.8*/for(lista1 <- listServicios) yield /*39.36*/{_display_(Seq[Any](format.raw/*39.37*/("""
							"""),format.raw/*40.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*41.39*/lista1/*41.45*/.get(0)),format.raw/*41.52*/("""</td>
								<td style="text-align:center;">"""),_display_(/*42.41*/lista1/*42.47*/.get(1)),format.raw/*42.54*/("""</td>
								<td style="text-align:left;"><div id=""""),_display_(/*43.48*/lista1/*43.54*/.get(8)),format.raw/*43.61*/("""">"""),_display_(/*43.64*/lista1/*43.70*/.get(2)),format.raw/*43.77*/("""</div></td>
								<td style="text-align:center;">"""),_display_(/*44.41*/lista1/*44.47*/.get(3)),format.raw/*44.54*/("""</td>
								<td style="text-align:center;">"""),_display_(/*45.41*/lista1/*45.47*/.get(4)),format.raw/*45.54*/("""</td>
								<td style="text-align:right; max-width:50px !important">
								
									<div style="display:none">"""),_display_(/*48.37*/lista1/*48.43*/.get(5)),format.raw/*48.50*/("""</div>
									
									<input type="text" class="form-control right width80"
										id="precio_"""),_display_(/*51.23*/lista1/*51.29*/.get(8)),format.raw/*51.36*/(""""
										onfocus="value=value.replace(/,/g,'')" 
										onfocusout = "if(value.trim()=='') value=0; formateaPrecio(id, '"""),_display_(/*53.76*/lista1/*53.82*/.get(7)),format.raw/*53.89*/("""');"
										onkeydown="return ingresoDouble(window.event)"
										value = """"),_display_(/*55.21*/lista1/*55.27*/.get(5)),format.raw/*55.34*/(""""
										onchange="if(value.trim()=='') value=0; cambiaPrecio('"""),_display_(/*56.66*/lista1/*56.72*/.get(8)),format.raw/*56.79*/("""',id, value, 'precio'); formateaPrecio(id,'"""),_display_(/*56.123*/lista1/*56.129*/.get(7)),format.raw/*56.136*/("""');">
										
								</td>
								<td style="text-align:center;">
									<div id="fechaAAMMDD_"""),_display_(/*60.32*/lista1/*60.38*/.get(8)),format.raw/*60.45*/("""" style="display:none">"""),_display_(/*60.69*/utilities/*60.78*/.Fechas.AAMMDD(lista1.get(6))),format.raw/*60.107*/("""</div>
									<div id="fechaDDMMAA_"""),_display_(/*61.32*/lista1/*61.38*/.get(8)),format.raw/*61.45*/("""">"""),_display_(/*61.48*/utilities/*61.57*/.Fechas.DDMMAA(lista1.get(6))),format.raw/*61.86*/("""</div>
								</td>
								
								<td style="text-align:right; max-width:50px !important">
								
									<div style="display:none">"""),_display_(/*66.37*/lista1/*66.43*/.get(5)),format.raw/*66.50*/("""</div>
									
									<input type="text" class="form-control right width80"
										id="cantMinimo_"""),_display_(/*69.27*/lista1/*69.33*/.get(8)),format.raw/*69.40*/(""""
										onfocus="value=value.replace(/,/g,'')" 
										onfocusout = "if(value.trim()=='') value=0; formateaPrecio(id, '2');"
										onkeydown="return ingresoDouble(window.event)"
										value = """"),_display_(/*73.21*/lista1/*73.27*/.get(9)),format.raw/*73.34*/(""""
										onchange="if(value.trim()=='') value=0; cambiaPrecio('"""),_display_(/*74.66*/lista1/*74.72*/.get(8)),format.raw/*74.79*/("""',id, value, 'cantMinimo'); formateaPrecio(id,'2');">
										
								</td>
								<td style="text-align:right; max-width:50px !important">
								
									<div style="display:none">"""),_display_(/*79.37*/lista1/*79.43*/.get(5)),format.raw/*79.50*/("""</div>
									
									<input type="text" class="form-control right width80"
										id="precioAdicional_"""),_display_(/*82.32*/lista1/*82.38*/.get(8)),format.raw/*82.45*/(""""
										onfocus="value=value.replace(/,/g,'')" 
										onfocusout = "if(value.trim()=='') value=0; formateaPrecio(id, '"""),_display_(/*84.76*/lista1/*84.82*/.get(7)),format.raw/*84.89*/("""');"
										onkeydown="return ingresoDouble(window.event)"
										value = """"),_display_(/*86.21*/lista1/*86.27*/.get(10)),format.raw/*86.35*/(""""
										onchange="if(value.trim()=='') value=0; cambiaPrecio('"""),_display_(/*87.66*/lista1/*87.72*/.get(8)),format.raw/*87.79*/("""',id, value, 'precioAdicional'); formateaPrecio(id,'"""),_display_(/*87.132*/lista1/*87.138*/.get(7)),format.raw/*87.145*/("""');">
										
								</td>
								
								<td style="text-align:center;">
									<a href="/servicioModifica/"""),_display_(/*92.38*/lista1/*92.44*/.get(8)),format.raw/*92.51*/("""">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<a href="#" onclick= "eliminarServicio('"""),_display_(/*97.51*/lista1/*97.57*/.get(8)),format.raw/*97.64*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*102.9*/("""
					"""),format.raw/*103.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR NUEVO SERVICIO" class="btn btn-info btn-sm rounded-pill btn-block" onclick="location.href='/servicioNuevo/1';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/servicioElimina/">
		<input type="hidden" id="form_id_servicio" name="id_servicio">
	</form>
""")))}),format.raw/*122.2*/("""


"""),format.raw/*125.1*/("""<script type="text/javascript">
	
	$(document).ready(function() """),format.raw/*127.31*/("""{"""),format.raw/*127.32*/("""
		  """),format.raw/*128.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*128.36*/("""{"""),format.raw/*128.37*/("""
			"""),format.raw/*129.4*/(""""loadingRecords":		"Cargando...",

				"processing":			"Procesando...",
		    	"fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 3, "asc" ]],
		    	"language": """),format.raw/*135.20*/("""{"""),format.raw/*135.21*/("""
		        	"""),format.raw/*136.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*137.11*/("""}"""),format.raw/*137.12*/(""",
				
		  """),format.raw/*139.5*/("""}"""),format.raw/*139.6*/(""" """),format.raw/*139.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*141.2*/("""}"""),format.raw/*141.3*/(""");
	
	const eliminarServicio = (id_servicio) => """),format.raw/*143.44*/("""{"""),format.raw/*143.45*/("""
		"""),format.raw/*144.3*/("""let nombre = $("#"+id_servicio).text();
		alertify.confirm("Esta seguro de eliminar el servicio: "+nombre, function (e) """),format.raw/*145.81*/("""{"""),format.raw/*145.82*/("""
			"""),format.raw/*146.4*/("""if (e) """),format.raw/*146.11*/("""{"""),format.raw/*146.12*/("""
				"""),format.raw/*147.5*/("""$("#form_id_servicio").val(id_servicio);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*149.4*/("""}"""),format.raw/*149.5*/("""
		"""),format.raw/*150.3*/("""}"""),format.raw/*150.4*/(""");
	"""),format.raw/*151.2*/("""}"""),format.raw/*151.3*/("""
	
	"""),format.raw/*153.2*/("""const formateaPrecio = (id, nroDec) => """),format.raw/*153.41*/("""{"""),format.raw/*153.42*/("""
		"""),format.raw/*154.3*/("""$("#"+id).val(formatStandar($("#"+id).val(), nroDec));
	"""),format.raw/*155.2*/("""}"""),format.raw/*155.3*/("""
	
	"""),format.raw/*157.2*/("""const cambiaPrecio = (auxId, id, valor, concepto) => """),format.raw/*157.55*/("""{"""),format.raw/*157.56*/("""
		"""),format.raw/*158.3*/("""let fechaAAMMDD = '"""),_display_(/*158.23*/hoy/*158.26*/.getFechaStrAAMMDD()),format.raw/*158.46*/("""';
		let fechaDDMMAA = '"""),_display_(/*159.23*/hoy/*159.26*/.getFechaStrDDMMAA()),format.raw/*159.46*/("""';
		$("#fechaAAMMDD_"+auxId).text(fechaAAMMDD);
		$("#fechaDDMMAA_"+auxId).text(fechaDDMMAA);
		
		var formData = new FormData();
	  	formData.append('id_servicio',auxId);
		formData.append('valor',valor);
		formData.append('fecha',fechaAAMMDD);
		formData.append('concepto',concepto);
        $.ajax("""),format.raw/*168.16*/("""{"""),format.raw/*168.17*/("""
            """),format.raw/*169.13*/("""url: "/modPrecioServicioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*176.36*/("""{"""),format.raw/*176.37*/("""
	     		"""),format.raw/*177.9*/("""if(respuesta=="error")"""),format.raw/*177.31*/("""{"""),format.raw/*177.32*/("""
	     			"""),format.raw/*178.10*/("""alertify.alert(msgError, function () """),format.raw/*178.47*/("""{"""),format.raw/*178.48*/("""
		     			"""),format.raw/*179.11*/("""location.href = "/";
		     		"""),format.raw/*180.10*/("""}"""),format.raw/*180.11*/(""");
	     		"""),format.raw/*181.9*/("""}"""),format.raw/*181.10*/("""
	     	"""),format.raw/*182.8*/("""}"""),format.raw/*182.9*/("""
        """),format.raw/*183.9*/("""}"""),format.raw/*183.10*/(""");
	"""),format.raw/*184.2*/("""}"""),format.raw/*184.3*/("""
	
	
	
"""),format.raw/*188.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listServicios:List[List[String]],hoy:utilities.Fechas): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listServicios,hoy)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],utilities.Fechas) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listServicios,hoy) => apply(mapDiccionario,mapPermiso,userMnu,listServicios,hoy)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/servicioMantencion.scala.html
                  HASH: 236d59b2c53446e106f8505a419e00fa931c20b0
                  MATRIX: 1049->1|1297->156|1330->164|1346->172|1385->174|1413->177|1481->225|1510->228|1683->375|1775->446|1805->449|2671->1289|2715->1317|2754->1318|2789->1326|2859->1369|2874->1375|2902->1382|2975->1428|2990->1434|3018->1441|3098->1494|3113->1500|3141->1507|3171->1510|3186->1516|3214->1523|3293->1575|3308->1581|3336->1588|3409->1634|3424->1640|3452->1647|3595->1763|3610->1769|3638->1776|3767->1878|3782->1884|3810->1891|3964->2018|3979->2024|4007->2031|4116->2113|4131->2119|4159->2126|4253->2193|4268->2199|4296->2206|4368->2250|4384->2256|4413->2263|4542->2365|4557->2371|4585->2378|4636->2402|4654->2411|4705->2440|4770->2478|4785->2484|4813->2491|4843->2494|4861->2503|4911->2532|5078->2672|5093->2678|5121->2685|5254->2791|5269->2797|5297->2804|5533->3013|5548->3019|5576->3026|5670->3093|5685->3099|5713->3106|5929->3295|5944->3301|5972->3308|6110->3419|6125->3425|6153->3432|6307->3559|6322->3565|6350->3572|6459->3654|6474->3660|6503->3668|6597->3735|6612->3741|6640->3748|6721->3801|6737->3807|6766->3814|6910->3931|6925->3937|6953->3944|7158->4122|7173->4128|7201->4135|7339->4242|7373->4248|8103->4947|8134->4950|8227->5014|8257->5015|8290->5020|8350->5051|8380->5052|8412->5056|8679->5294|8709->5295|8750->5307|8857->5385|8887->5386|8926->5397|8955->5398|8984->5399|9085->5472|9114->5473|9191->5521|9221->5522|9252->5525|9401->5645|9431->5646|9463->5650|9499->5657|9529->5658|9562->5663|9689->5762|9718->5763|9749->5766|9778->5767|9810->5771|9839->5772|9871->5776|9939->5815|9969->5816|10000->5819|10084->5875|10113->5876|10145->5880|10227->5933|10257->5934|10288->5937|10336->5957|10349->5960|10391->5980|10444->6005|10457->6008|10499->6028|10830->6330|10860->6331|10902->6344|11165->6578|11195->6579|11232->6588|11283->6610|11313->6611|11352->6621|11418->6658|11448->6659|11488->6670|11547->6700|11577->6701|11616->6712|11646->6713|11682->6721|11711->6722|11748->6731|11778->6732|11810->6736|11839->6737|11874->6744
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|43->12|43->12|44->13|70->39|70->39|70->39|71->40|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|74->43|74->43|74->43|74->43|75->44|75->44|75->44|76->45|76->45|76->45|79->48|79->48|79->48|82->51|82->51|82->51|84->53|84->53|84->53|86->55|86->55|86->55|87->56|87->56|87->56|87->56|87->56|87->56|91->60|91->60|91->60|91->60|91->60|91->60|92->61|92->61|92->61|92->61|92->61|92->61|97->66|97->66|97->66|100->69|100->69|100->69|104->73|104->73|104->73|105->74|105->74|105->74|110->79|110->79|110->79|113->82|113->82|113->82|115->84|115->84|115->84|117->86|117->86|117->86|118->87|118->87|118->87|118->87|118->87|118->87|123->92|123->92|123->92|128->97|128->97|128->97|133->102|134->103|153->122|156->125|158->127|158->127|159->128|159->128|159->128|160->129|166->135|166->135|167->136|168->137|168->137|170->139|170->139|170->139|172->141|172->141|174->143|174->143|175->144|176->145|176->145|177->146|177->146|177->146|178->147|180->149|180->149|181->150|181->150|182->151|182->151|184->153|184->153|184->153|185->154|186->155|186->155|188->157|188->157|188->157|189->158|189->158|189->158|189->158|190->159|190->159|190->159|199->168|199->168|200->169|207->176|207->176|208->177|208->177|208->177|209->178|209->178|209->178|210->179|211->180|211->180|212->181|212->181|213->182|213->182|214->183|214->183|215->184|215->184|219->188
                  -- GENERATED --
              */
          