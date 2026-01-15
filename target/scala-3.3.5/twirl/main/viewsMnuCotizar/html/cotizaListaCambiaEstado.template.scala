
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

object cotizaListaCambiaEstado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[tables.CotizaEstado],List[tables.Dibujante],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listCotizaciones: List[List[String]], listEstados: List[tables.CotizaEstado], listDibujantes: List[tables.Dibujante]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "CAMBIAR EL ESTADO DE COTIZACIONES","(solo cotizaciones pendientes de confirmar)")),format.raw/*13.114*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>COMERCIAL</TH>
							<TH>SOLUCION</TH>
							<TH>Nro.COTI</TH>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>OBSERVACIONES</TH>
							<TH>DOC</TH>
							<TH>FECHA<br>PROBABLE</TH>
							<TH>DIBUJANTE<br>PROYECTISTA</TH>
							<TH>ESTADO</TH>
							<TH>NOTAS</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*35.8*/for(lista1 <- listCotizaciones) yield /*35.39*/{_display_(Seq[Any](format.raw/*35.40*/("""
							"""),format.raw/*36.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*37.39*/lista1/*37.45*/.get(9)),format.raw/*37.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*38.39*/lista1/*38.45*/.get(10)),format.raw/*38.53*/("""</td>
								<td style="text-align:left;">"""),_display_(/*39.39*/lista1/*39.45*/.get(11)),format.raw/*39.53*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*41.47*/lista1/*41.53*/.get(0)),format.raw/*41.60*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*43.34*/lista1/*43.40*/.get(1)),format.raw/*43.47*/("""</font>
											</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*48.37*/lista1/*48.43*/.get(2)),format.raw/*48.50*/("""</div>
									"""),_display_(/*49.11*/utilities/*49.20*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*49.49*/("""
								"""),format.raw/*50.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*51.74*/lista1/*51.80*/.get(3)),format.raw/*51.87*/("""')">"""),_display_(/*51.92*/lista1/*51.98*/.get(3)),format.raw/*51.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*52.75*/lista1/*52.81*/.get(6)),format.raw/*52.88*/("""')">"""),_display_(/*52.93*/lista1/*52.99*/.get(6)),format.raw/*52.106*/("""</a></td>
								<td style="text-align:left;">"""),_display_(/*53.39*/lista1/*53.45*/.get(4)),format.raw/*53.52*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(5).equals("0"))/*55.40*/{_display_(Seq[Any](format.raw/*55.41*/("""
										"""),format.raw/*56.11*/("""--
									""")))}else/*57.15*/{_display_(Seq[Any](format.raw/*57.16*/("""
										"""),format.raw/*58.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*58.52*/lista1/*58.58*/.get(5)),format.raw/*58.65*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*61.11*/("""
								"""),format.raw/*62.9*/("""</td>
								<td style="text-align:left;">
									<input type="date" class="form-control center"
									name="fechaProbable"
									id="fechaProbable"
									onblur="if( ! limitaFecha(value,720,720))"""),format.raw/*67.51*/("""{"""),format.raw/*67.52*/("""value='"""),_display_(/*67.60*/lista1/*67.66*/.get(13)),format.raw/*67.74*/("""';"""),format.raw/*67.76*/("""}"""),format.raw/*67.77*/("""else"""),format.raw/*67.81*/("""{"""),format.raw/*67.82*/("""cambiarFechaProbable('"""),_display_(/*67.105*/lista1/*67.111*/.get(0)),format.raw/*67.118*/("""', value);"""),format.raw/*67.128*/("""}"""),format.raw/*67.129*/(""""
									value=""""),_display_(/*68.18*/lista1/*68.24*/.get(13)),format.raw/*68.32*/(""""
									required>
								</td>
								<td style="text-align:left;">
									<select class="custom-select height23px font10px" onchange="cambiarDibujante('"""),_display_(/*72.89*/lista1/*72.95*/.get(0)),format.raw/*72.102*/("""', value)">
										<option value=""""),_display_(/*73.27*/lista1/*73.33*/.get(15)),format.raw/*73.41*/("""">"""),_display_(/*73.44*/lista1/*73.50*/.get(14)),format.raw/*73.58*/("""</option>
										"""),_display_(/*74.12*/for(lista2 <- listDibujantes) yield /*74.41*/{_display_(Seq[Any](format.raw/*74.42*/("""
											"""),format.raw/*75.12*/("""<option value=""""),_display_(/*75.28*/lista2/*75.34*/.getId()),format.raw/*75.42*/("""">"""),_display_(/*75.45*/lista2/*75.51*/.getNombre()),format.raw/*75.63*/("""</option>
										""")))}),format.raw/*76.12*/("""
									"""),format.raw/*77.10*/("""</select>
								</td>
								<td  style="text-align:center;">
									<select class="custom-select height23px font10px" onchange="cambiarEstado('"""),_display_(/*80.86*/lista1/*80.92*/.get(0)),format.raw/*80.99*/("""', value)">
										<option value=""""),_display_(/*81.27*/lista1/*81.33*/.get(7)),format.raw/*81.40*/("""">"""),_display_(/*81.43*/lista1/*81.49*/.get(8)),format.raw/*81.56*/("""</option>
										"""),_display_(/*82.12*/for(lista <- listEstados) yield /*82.37*/{_display_(Seq[Any](format.raw/*82.38*/("""
											"""),format.raw/*83.12*/("""<option value=""""),_display_(/*83.28*/lista/*83.33*/.id),format.raw/*83.36*/("""">"""),_display_(/*83.39*/lista/*83.44*/.estado),format.raw/*83.51*/("""</option>
										""")))}),format.raw/*84.12*/("""
									"""),format.raw/*85.10*/("""</select>
								</td>
								<td  style="min-width: 100px; text-align:center;">
									<input type="text" class="form-control left" 
										value = """"),_display_(/*89.21*/lista1/*89.27*/.get(12)),format.raw/*89.35*/(""""
										autocomplete="off" 
										maxlength="150"
										onchange="cambiarNota('"""),_display_(/*92.35*/lista1/*92.41*/.get(0)),format.raw/*92.48*/("""', value)">
								</td>
							</TR>
			 			""")))}),format.raw/*95.9*/("""
					"""),format.raw/*96.6*/("""</tbody>
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
	
	

""")))}),format.raw/*136.2*/("""


"""),format.raw/*139.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*140.31*/("""{"""),format.raw/*140.32*/("""
		  """),format.raw/*141.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*141.36*/("""{"""),format.raw/*141.37*/("""
		    	"""),format.raw/*142.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 4, "desc" ]],
		    	"language": """),format.raw/*145.20*/("""{"""),format.raw/*145.21*/("""
		        	"""),format.raw/*146.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*147.11*/("""}"""),format.raw/*147.12*/("""
		  """),format.raw/*148.5*/("""}"""),format.raw/*148.6*/(""" """),format.raw/*148.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*150.2*/("""}"""),format.raw/*150.3*/(""");
	
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*153.43*/("""{"""),format.raw/*153.44*/("""
		"""),format.raw/*154.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*156.16*/("""{"""),format.raw/*156.17*/("""
            """),format.raw/*157.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*164.36*/("""{"""),format.raw/*164.37*/("""
	     		"""),format.raw/*165.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*167.8*/("""}"""),format.raw/*167.9*/("""
        """),format.raw/*168.9*/("""}"""),format.raw/*168.10*/(""");
	"""),format.raw/*169.2*/("""}"""),format.raw/*169.3*/("""
	
	"""),format.raw/*171.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*171.43*/("""{"""),format.raw/*171.44*/("""
		  """),format.raw/*172.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*174.2*/("""}"""),format.raw/*174.3*/("""
	
	"""),format.raw/*176.2*/("""const cambiarEstado = (id_cotizacion, id_cotizaEstado) => """),format.raw/*176.60*/("""{"""),format.raw/*176.61*/("""
		"""),format.raw/*177.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
	  	formData.append('id_cotizaEstado',id_cotizaEstado);
        $.ajax("""),format.raw/*180.16*/("""{"""),format.raw/*180.17*/("""
            """),format.raw/*181.13*/("""url: "/cambiarCotizaEstadoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*188.36*/("""{"""),format.raw/*188.37*/("""}"""),format.raw/*188.38*/("""
        """),format.raw/*189.9*/("""}"""),format.raw/*189.10*/(""");
	"""),format.raw/*190.2*/("""}"""),format.raw/*190.3*/("""

	"""),format.raw/*192.2*/("""const cambiarFechaProbable = (id_cotizacion, fechaProbable) => """),format.raw/*192.65*/("""{"""),format.raw/*192.66*/("""
		"""),format.raw/*193.3*/("""var formData = new FormData();
		formData.append('id_cotizacion',id_cotizacion);
		formData.append('fechaProbable',fechaProbable);
		$.ajax("""),format.raw/*196.10*/("""{"""),format.raw/*196.11*/("""
			"""),format.raw/*197.4*/("""url: "/cambiarCotizaFechaProbableAjax/",
			type: "POST",
			method: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(respuesta)"""),format.raw/*204.32*/("""{"""),format.raw/*204.33*/("""}"""),format.raw/*204.34*/("""
		"""),format.raw/*205.3*/("""}"""),format.raw/*205.4*/(""");
	"""),format.raw/*206.2*/("""}"""),format.raw/*206.3*/("""

	"""),format.raw/*208.2*/("""const cambiarDibujante = (id_cotizacion, id_dibujante) => """),format.raw/*208.60*/("""{"""),format.raw/*208.61*/("""
		"""),format.raw/*209.3*/("""var formData = new FormData();
		formData.append('id_cotizacion',id_cotizacion);
		formData.append('id_cotizaDibujante',id_dibujante);
		$.ajax("""),format.raw/*212.10*/("""{"""),format.raw/*212.11*/("""
			"""),format.raw/*213.4*/("""url: "/cambiarCotizaDibujanteAjax/",
			type: "POST",
			method: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(respuesta)"""),format.raw/*220.32*/("""{"""),format.raw/*220.33*/("""}"""),format.raw/*220.34*/("""
		"""),format.raw/*221.3*/("""}"""),format.raw/*221.4*/(""");
	"""),format.raw/*222.2*/("""}"""),format.raw/*222.3*/("""
	
	"""),format.raw/*224.2*/("""const cambiarNota = (id_cotizacion, nota) => """),format.raw/*224.47*/("""{"""),format.raw/*224.48*/("""
		"""),format.raw/*225.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
	  	formData.append('nota',nota);
        $.ajax("""),format.raw/*228.16*/("""{"""),format.raw/*228.17*/("""
            """),format.raw/*229.13*/("""url: "/cambiarCotizaNotaAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*236.36*/("""{"""),format.raw/*236.37*/("""}"""),format.raw/*236.38*/("""
        """),format.raw/*237.9*/("""}"""),format.raw/*237.10*/(""");
	"""),format.raw/*238.2*/("""}"""),format.raw/*238.3*/("""
		
	
	
"""),format.raw/*242.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listCotizaciones:List[List[String]],listEstados:List[tables.CotizaEstado],listDibujantes:List[tables.Dibujante]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones,listEstados,listDibujantes)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[tables.CotizaEstado],List[tables.Dibujante]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listCotizaciones,listEstados,listDibujantes) => apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones,listEstados,listDibujantes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaListaCambiaEstado.scala.html
                  HASH: 82ef12b53e366d6dd8bd389b2a9344fe5e54034e
                  MATRIX: 1090->1|1398->216|1431->224|1447->232|1486->234|1515->238|1583->286|1613->291|1655->313|1684->316|1728->339|1759->343|1836->394|1968->504|1998->507|2706->1189|2753->1220|2792->1221|2827->1229|2897->1272|2912->1278|2940->1285|3011->1329|3026->1335|3055->1343|3126->1387|3141->1393|3170->1401|3289->1493|3304->1499|3332->1506|3458->1605|3473->1611|3501->1618|3658->1748|3673->1754|3701->1761|3745->1778|3763->1787|3813->1816|3849->1825|3955->1904|3970->1910|3998->1917|4030->1922|4045->1928|4074->1935|4185->2019|4200->2025|4228->2032|4260->2037|4275->2043|4304->2050|4379->2098|4394->2104|4422->2111|4534->2196|4573->2197|4612->2208|4648->2225|4687->2226|4726->2237|4794->2278|4809->2284|4837->2291|4958->2381|4994->2390|5230->2598|5259->2599|5294->2607|5309->2613|5338->2621|5368->2623|5397->2624|5429->2628|5458->2629|5509->2652|5525->2658|5554->2665|5593->2675|5623->2676|5669->2695|5684->2701|5713->2709|5901->2870|5916->2876|5945->2883|6010->2921|6025->2927|6054->2935|6084->2938|6099->2944|6128->2952|6176->2973|6221->3002|6260->3003|6300->3015|6343->3031|6358->3037|6387->3045|6417->3048|6432->3054|6465->3066|6517->3087|6555->3097|6732->3247|6747->3253|6775->3260|6840->3298|6855->3304|6883->3311|6913->3314|6928->3320|6956->3327|7004->3348|7045->3373|7084->3374|7124->3386|7167->3402|7181->3407|7205->3410|7235->3413|7249->3418|7277->3425|7329->3446|7367->3456|7552->3614|7567->3620|7596->3628|7715->3720|7730->3726|7758->3733|7835->3780|7868->3786|9242->5129|9273->5132|9364->5194|9394->5195|9427->5200|9487->5231|9517->5232|9553->5240|9729->5387|9759->5388|9800->5400|9907->5478|9937->5479|9970->5484|9999->5485|10028->5486|10129->5559|10158->5560|10236->5609|10266->5610|10297->5613|10424->5711|10454->5712|10496->5725|10755->5955|10785->5956|10822->5965|10975->6090|11004->6091|11041->6100|11071->6101|11103->6105|11132->6106|11164->6110|11234->6151|11264->6152|11297->6157|11429->6261|11458->6262|11490->6266|11577->6324|11607->6325|11638->6328|11821->6482|11851->6483|11893->6496|12158->6732|12188->6733|12218->6734|12255->6743|12285->6744|12317->6748|12346->6749|12377->6752|12469->6815|12499->6816|12530->6819|12699->6959|12729->6960|12761->6964|12980->7154|13010->7155|13040->7156|13071->7159|13100->7160|13132->7164|13161->7165|13192->7168|13279->7226|13309->7227|13340->7230|13513->7374|13543->7375|13575->7379|13790->7565|13820->7566|13850->7567|13881->7570|13910->7571|13942->7575|13971->7576|14003->7580|14077->7625|14107->7626|14138->7629|14299->7761|14329->7762|14371->7775|14634->8009|14664->8010|14694->8011|14731->8020|14761->8021|14793->8025|14822->8026|14858->8034
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|66->35|66->35|66->35|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|72->41|72->41|72->41|74->43|74->43|74->43|79->48|79->48|79->48|80->49|80->49|80->49|81->50|82->51|82->51|82->51|82->51|82->51|82->51|83->52|83->52|83->52|83->52|83->52|83->52|84->53|84->53|84->53|86->55|86->55|87->56|88->57|88->57|89->58|89->58|89->58|89->58|92->61|93->62|98->67|98->67|98->67|98->67|98->67|98->67|98->67|98->67|98->67|98->67|98->67|98->67|98->67|98->67|99->68|99->68|99->68|103->72|103->72|103->72|104->73|104->73|104->73|104->73|104->73|104->73|105->74|105->74|105->74|106->75|106->75|106->75|106->75|106->75|106->75|106->75|107->76|108->77|111->80|111->80|111->80|112->81|112->81|112->81|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|114->83|114->83|114->83|114->83|115->84|116->85|120->89|120->89|120->89|123->92|123->92|123->92|126->95|127->96|167->136|170->139|171->140|171->140|172->141|172->141|172->141|173->142|176->145|176->145|177->146|178->147|178->147|179->148|179->148|179->148|181->150|181->150|184->153|184->153|185->154|187->156|187->156|188->157|195->164|195->164|196->165|198->167|198->167|199->168|199->168|200->169|200->169|202->171|202->171|202->171|203->172|205->174|205->174|207->176|207->176|207->176|208->177|211->180|211->180|212->181|219->188|219->188|219->188|220->189|220->189|221->190|221->190|223->192|223->192|223->192|224->193|227->196|227->196|228->197|235->204|235->204|235->204|236->205|236->205|237->206|237->206|239->208|239->208|239->208|240->209|243->212|243->212|244->213|251->220|251->220|251->220|252->221|252->221|253->222|253->222|255->224|255->224|255->224|256->225|259->228|259->228|260->229|267->236|267->236|267->236|268->237|268->237|269->238|269->238|273->242
                  -- GENERATED --
              */
          