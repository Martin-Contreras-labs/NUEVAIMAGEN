
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

object otOdoListaAgregarOC extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[Long],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listOt: List[List[String]], listAnios: List[Long]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, mapDiccionario.get("Ordenes_de_trabajo").toUpperCase()+" ODO","AGREGAR ORDENES DE COMPRA")),format.raw/*14.122*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
				<div align="center">
					"""),_display_(/*19.7*/for(anio <-listAnios) yield /*19.28*/{_display_(Seq[Any](format.raw/*19.29*/("""
						"""),format.raw/*20.7*/("""<a href="#" onclick="location.href='/routes2/otOdoListaAgregarOC/"""),_display_(/*20.73*/anio),format.raw/*20.77*/("""'">
							<kbd style="background-color: #73C6B6">"""),_display_(/*21.48*/anio),format.raw/*21.52*/("""</kbd>
						</a>
					""")))}),format.raw/*23.7*/("""
				"""),format.raw/*24.5*/("""</div>
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>Nro."""),_display_(/*29.17*/mapDiccionario/*29.31*/.get("OT")),format.raw/*29.41*/("""</TH>
							<TH style="min-width:80px;">FECHA<br>"""),_display_(/*30.46*/mapDiccionario/*30.60*/.get("OT")),format.raw/*30.70*/("""</TH>
							<TH>Nro.COTI</TH>
							<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
							<TH>"""),_display_(/*33.13*/mapDiccionario/*33.27*/.get("BODEGA")),format.raw/*33.41*/("""/PROYECTO</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>NRO OC<br>CLIENTE</TH>
							<TH>ADJUNTAR<br>OC CLIENTE</TH>
							<TH>OC CLIENTE<br>ADJUNTO</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*42.8*/for(lista1 <- listOt) yield /*42.29*/{_display_(Seq[Any](format.raw/*42.30*/("""
							"""),format.raw/*43.8*/("""<TR>
								<td style="text-align:center;">
									<a href="#" onclick="verOt('"""),_display_(/*45.39*/lista1/*45.45*/.get(0)),format.raw/*45.52*/("""')">
										<kbd style="background-color: rgb(90, 200, 245)">
											<font color="green">"""),_display_(/*47.33*/lista1/*47.39*/.get(2)),format.raw/*47.46*/("""</font>
										</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*52.37*/lista1/*52.43*/.get(3)),format.raw/*52.50*/("""</div>
									"""),_display_(/*53.11*/utilities/*53.20*/.Fechas.DDMMAA(lista1.get(3))),format.raw/*53.49*/("""
								"""),format.raw/*54.9*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*56.47*/lista1/*56.53*/.get(1)),format.raw/*56.60*/("""')">
										<kbd style="background-color: rgb(90, 200, 245)">
											<font color="green">"""),_display_(/*58.33*/lista1/*58.39*/.get(4)),format.raw/*58.46*/("""</font>
										</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*63.37*/lista1/*63.43*/.get(5)),format.raw/*63.50*/("""</div>
									"""),_display_(/*64.11*/utilities/*64.20*/.Fechas.DDMMAA(lista1.get(5))),format.raw/*64.49*/("""
								"""),format.raw/*65.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*66.73*/lista1/*66.79*/.get(14)),format.raw/*66.87*/("""')">"""),_display_(/*66.92*/lista1/*66.98*/.get(14)),format.raw/*66.106*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*67.74*/lista1/*67.80*/.get(6)),format.raw/*67.87*/("""')">"""),_display_(/*67.92*/lista1/*67.98*/.get(6)),format.raw/*67.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*68.75*/lista1/*68.81*/.get(7)),format.raw/*68.88*/("""')">"""),_display_(/*68.93*/lista1/*68.99*/.get(7)),format.raw/*68.106*/("""</a></td>
								
								
								<td style="text-align:center;">
									<input type="text" class="form-control height23px left"
											id="numeroOC_"""),_display_(/*73.26*/lista1/*73.32*/.get(0)),format.raw/*73.39*/(""""
											value=""""),_display_(/*74.20*/lista1/*74.26*/.get(16)),format.raw/*74.34*/(""""
											autocomplete="off"
											onchange="actualizaOC(id, '"""),_display_(/*76.40*/lista1/*76.46*/.get(1)),format.raw/*76.53*/("""')">
								</td>
								<td style="text-align:center;">
									<form id="form_"""),_display_(/*79.26*/lista1/*79.32*/.get(0)),format.raw/*79.39*/(""""action="/routes2/grabarOcOdoPdf/"""),_display_(/*79.73*/lista1/*79.79*/.get(0)),format.raw/*79.86*/("""" enctype="multipart/form-data" method="POST">
										<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											"""),_display_(if(lista1.get(15).equals("0"))/*81.43*/{_display_(Seq[Any](format.raw/*81.44*/("""
												"""),format.raw/*82.13*/("""<div id="txtBtn">Adjuntar</div>
											""")))}else/*83.17*/{_display_(Seq[Any](format.raw/*83.18*/("""
												"""),format.raw/*84.13*/("""<div id="txtBtn">Reemplazar</div>
											""")))}),format.raw/*85.13*/("""
					    					"""),format.raw/*86.15*/("""<input type="file" id="docAdjunto_"""),_display_(/*86.50*/lista1/*86.56*/.get(0)),format.raw/*86.63*/("""" name="docAdjunto" 
												onchange="LimitAttach(this.form, this.form.docAdjunto.value, '"""),_display_(/*87.76*/lista1/*87.82*/.get(0)),format.raw/*87.89*/("""'); 
												 	document.getElementById('mostrarmostrar').style.display='none';
													$('#form_"""),_display_(/*89.24*/lista1/*89.30*/.get(0)),format.raw/*89.37*/("""').submit();">
										</span>
									</form>
								</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(15).equals("0"))/*94.41*/{_display_(Seq[Any](format.raw/*94.42*/("""
										"""),format.raw/*95.11*/("""--
									""")))}else/*96.15*/{_display_(Seq[Any](format.raw/*96.16*/("""
										"""),format.raw/*97.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*97.52*/lista1/*97.58*/.get(15)),format.raw/*97.66*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*100.11*/("""
								"""),format.raw/*101.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*103.9*/("""
					"""),format.raw/*104.6*/("""</tbody>
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
					<h5 class='modal-title'>"""),_display_(/*121.31*/mapDiccionario/*121.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*121.69*/("""</h5>
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
	


""")))}),format.raw/*165.2*/("""


"""),format.raw/*168.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*169.31*/("""{"""),format.raw/*169.32*/("""
		  """),format.raw/*170.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*170.36*/("""{"""),format.raw/*170.37*/("""
		    	"""),format.raw/*171.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*174.20*/("""{"""),format.raw/*174.21*/("""
		        	"""),format.raw/*175.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*176.11*/("""}"""),format.raw/*176.12*/("""
		  """),format.raw/*177.5*/("""}"""),format.raw/*177.6*/(""" """),format.raw/*177.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*179.2*/("""}"""),format.raw/*179.3*/(""");
	
	
	const verOt = (id_otOdo) => """),format.raw/*182.30*/("""{"""),format.raw/*182.31*/("""
		"""),format.raw/*183.3*/("""var formData = new FormData();
	  	formData.append('id_otOdo',id_otOdo);
        $.ajax("""),format.raw/*185.16*/("""{"""),format.raw/*185.17*/("""
            """),format.raw/*186.13*/("""url: "/routes2/verOtOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*193.36*/("""{"""),format.raw/*193.37*/("""
	     		"""),format.raw/*194.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*196.8*/("""}"""),format.raw/*196.9*/("""
        """),format.raw/*197.9*/("""}"""),format.raw/*197.10*/(""");
	"""),format.raw/*198.2*/("""}"""),format.raw/*198.3*/("""
	
	"""),format.raw/*200.2*/("""const verCotizacion = (id_cotiOdo) => """),format.raw/*200.40*/("""{"""),format.raw/*200.41*/("""
		"""),format.raw/*201.3*/("""var formData = new FormData();
	  	formData.append('id_cotiOdo',id_cotiOdo);
        $.ajax("""),format.raw/*203.16*/("""{"""),format.raw/*203.17*/("""
            """),format.raw/*204.13*/("""url: "/routes2/verCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*211.36*/("""{"""),format.raw/*211.37*/("""
	     		"""),format.raw/*212.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*214.8*/("""}"""),format.raw/*214.9*/("""
        """),format.raw/*215.9*/("""}"""),format.raw/*215.10*/(""");
	"""),format.raw/*216.2*/("""}"""),format.raw/*216.3*/("""
	
	"""),format.raw/*218.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*218.43*/("""{"""),format.raw/*218.44*/("""
		"""),format.raw/*219.3*/("""$('#descargaDocumento').val(nombreDOC);
		document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*221.2*/("""}"""),format.raw/*221.3*/("""
	
	"""),format.raw/*223.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file, id_ot) => """),format.raw/*224.45*/("""{"""),format.raw/*224.46*/("""
		"""),format.raw/*225.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*227.35*/("""{"""),format.raw/*227.36*/("""
			"""),format.raw/*228.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*229.3*/("""}"""),format.raw/*229.4*/("""
		"""),format.raw/*230.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*231.45*/("""{"""),format.raw/*231.46*/("""
			"""),format.raw/*232.4*/("""if (extArray[i] == extencion) """),format.raw/*232.34*/("""{"""),format.raw/*232.35*/(""" """),format.raw/*232.36*/("""allowSubmit = true; break; """),format.raw/*232.63*/("""}"""),format.raw/*232.64*/("""
		"""),format.raw/*233.3*/("""}"""),format.raw/*233.4*/("""
		"""),format.raw/*234.3*/("""if (allowSubmit) """),format.raw/*234.20*/("""{"""),format.raw/*234.21*/("""
			"""),format.raw/*235.4*/("""var file = $("#docAdjunto_"+id_ot)[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*237.26*/("""{"""),format.raw/*237.27*/("""
				"""),format.raw/*238.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*241.4*/("""}"""),format.raw/*241.5*/("""
		"""),format.raw/*242.3*/("""}"""),format.raw/*242.4*/("""else"""),format.raw/*242.8*/("""{"""),format.raw/*242.9*/("""
			"""),format.raw/*243.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*247.3*/("""}"""),format.raw/*247.4*/("""
	"""),format.raw/*248.2*/("""}"""),format.raw/*248.3*/("""
	
	"""),format.raw/*250.2*/("""const actualizaOC = (id, id_cotiOdo) => """),format.raw/*250.42*/("""{"""),format.raw/*250.43*/("""
		"""),format.raw/*251.3*/("""var formData = new FormData();
		formData.append('id_cotiOdo', id_cotiOdo);
	  	formData.append('numeroOC',$("#"+id).val());
        $.ajax("""),format.raw/*254.16*/("""{"""),format.raw/*254.17*/("""
            """),format.raw/*255.13*/("""url: "/routes2/oc_cotiOdoClienteAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*262.36*/("""{"""),format.raw/*262.37*/("""}"""),format.raw/*262.38*/("""
        """),format.raw/*263.9*/("""}"""),format.raw/*263.10*/(""");
	"""),format.raw/*264.2*/("""}"""),format.raw/*264.3*/("""
	
	
	
	
		
	
	
	
"""),format.raw/*273.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listOt:List[List[String]],listAnios:List[Long]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listOt,listAnios)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[Long]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listOt,listAnios) => apply(mapDiccionario,mapPermiso,userMnu,listOt,listAnios)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/otOdoListaAgregarOC.scala.html
                  HASH: 6c8a1b5c42cdfb7d0c1910c205a574aa0ff3f5eb
                  MATRIX: 1048->1|1289->149|1322->157|1338->165|1377->167|1406->171|1474->219|1504->224|1559->259|1588->262|1631->284|1660->287|1704->310|1735->314|1812->365|1952->483|1982->486|2145->623|2182->644|2221->645|2255->652|2348->718|2373->722|2451->773|2476->777|2530->801|2562->806|2785->1002|2808->1016|2839->1026|2917->1077|2940->1091|2971->1101|3101->1204|3124->1218|3159->1232|3406->1453|3443->1474|3482->1475|3517->1483|3627->1566|3642->1572|3670->1579|3794->1676|3809->1682|3837->1689|3993->1818|4008->1824|4036->1831|4080->1848|4098->1857|4148->1886|4184->1895|4303->1987|4318->1993|4346->2000|4470->2097|4485->2103|4513->2110|4669->2239|4684->2245|4712->2252|4756->2269|4774->2278|4824->2307|4860->2316|4965->2394|4980->2400|5009->2408|5041->2413|5056->2419|5086->2427|5196->2510|5211->2516|5239->2523|5271->2528|5286->2534|5315->2541|5426->2625|5441->2631|5469->2638|5501->2643|5516->2649|5545->2656|5730->2814|5745->2820|5773->2827|5821->2848|5836->2854|5865->2862|5963->2933|5978->2939|6006->2946|6117->3030|6132->3036|6160->3043|6221->3077|6236->3083|6264->3090|6458->3257|6497->3258|6538->3271|6605->3319|6644->3320|6685->3333|6762->3379|6805->3394|6867->3429|6882->3435|6910->3442|7033->3538|7048->3544|7076->3551|7209->3657|7224->3663|7252->3670|7423->3814|7462->3815|7501->3826|7537->3843|7576->3844|7615->3855|7683->3896|7698->3902|7727->3910|7849->4000|7886->4009|7944->4036|7978->4042|8635->4671|8659->4685|8705->4709|10254->6227|10285->6230|10376->6292|10406->6293|10439->6298|10499->6329|10529->6330|10565->6338|10755->6499|10785->6500|10826->6512|10933->6590|10963->6591|10996->6596|11025->6597|11054->6598|11155->6671|11184->6672|11249->6708|11279->6709|11310->6712|11427->6800|11457->6801|11499->6814|11761->7047|11791->7048|11828->7057|11965->7166|11994->7167|12031->7176|12061->7177|12093->7181|12122->7182|12154->7186|12221->7224|12251->7225|12282->7228|12403->7320|12433->7321|12475->7334|12739->7569|12769->7570|12806->7579|12959->7704|12988->7705|13025->7714|13055->7715|13087->7719|13116->7720|13148->7724|13218->7765|13248->7766|13279->7769|13409->7871|13438->7872|13470->7876|13668->8045|13698->8046|13729->8049|13838->8129|13868->8130|13900->8134|13973->8179|14002->8180|14033->8183|14171->8292|14201->8293|14233->8297|14292->8327|14322->8328|14352->8329|14408->8356|14438->8357|14469->8360|14498->8361|14529->8364|14575->8381|14605->8382|14637->8386|14789->8509|14819->8510|14852->8515|15056->8691|15085->8692|15116->8695|15145->8696|15177->8700|15206->8701|15238->8705|15455->8894|15484->8895|15514->8897|15543->8898|15575->8902|15644->8942|15674->8943|15705->8946|15874->9086|15904->9087|15946->9100|16217->9342|16247->9343|16277->9344|16314->9353|16344->9354|16376->9358|16405->9359|16451->9377
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|50->19|50->19|50->19|51->20|51->20|51->20|52->21|52->21|54->23|55->24|60->29|60->29|60->29|61->30|61->30|61->30|64->33|64->33|64->33|73->42|73->42|73->42|74->43|76->45|76->45|76->45|78->47|78->47|78->47|83->52|83->52|83->52|84->53|84->53|84->53|85->54|87->56|87->56|87->56|89->58|89->58|89->58|94->63|94->63|94->63|95->64|95->64|95->64|96->65|97->66|97->66|97->66|97->66|97->66|97->66|98->67|98->67|98->67|98->67|98->67|98->67|99->68|99->68|99->68|99->68|99->68|99->68|104->73|104->73|104->73|105->74|105->74|105->74|107->76|107->76|107->76|110->79|110->79|110->79|110->79|110->79|110->79|112->81|112->81|113->82|114->83|114->83|115->84|116->85|117->86|117->86|117->86|117->86|118->87|118->87|118->87|120->89|120->89|120->89|125->94|125->94|126->95|127->96|127->96|128->97|128->97|128->97|128->97|131->100|132->101|134->103|135->104|152->121|152->121|152->121|196->165|199->168|200->169|200->169|201->170|201->170|201->170|202->171|205->174|205->174|206->175|207->176|207->176|208->177|208->177|208->177|210->179|210->179|213->182|213->182|214->183|216->185|216->185|217->186|224->193|224->193|225->194|227->196|227->196|228->197|228->197|229->198|229->198|231->200|231->200|231->200|232->201|234->203|234->203|235->204|242->211|242->211|243->212|245->214|245->214|246->215|246->215|247->216|247->216|249->218|249->218|249->218|250->219|252->221|252->221|254->223|255->224|255->224|256->225|258->227|258->227|259->228|260->229|260->229|261->230|262->231|262->231|263->232|263->232|263->232|263->232|263->232|263->232|264->233|264->233|265->234|265->234|265->234|266->235|268->237|268->237|269->238|272->241|272->241|273->242|273->242|273->242|273->242|274->243|278->247|278->247|279->248|279->248|281->250|281->250|281->250|282->251|285->254|285->254|286->255|293->262|293->262|293->262|294->263|294->263|295->264|295->264|304->273
                  -- GENERATED --
              */
          