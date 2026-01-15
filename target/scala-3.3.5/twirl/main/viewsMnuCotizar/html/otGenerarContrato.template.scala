
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

object otGenerarContrato extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listCotizaciones: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "COTIZACIONES - GENERAR CONTRATOS","(Solo sobre cotizaciones confirmadas)")),format.raw/*13.107*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
			
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>COMERCIAL</TH>
							<TH>Nro.COTI</TH>
							<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>OBSERVACIONES</TH>
							<TH>DOC<br>ADJ</TH>
							<TH>PDF<br>"""),_display_(/*28.20*/mapDiccionario/*28.34*/.get("ARR")),format.raw/*28.45*/("""</TH>
							<TH>PDF<br>VTA</TH>
							
							<TH>GENERAR<br>CONTRATO</TH>
							<TH>ADJUNTAR<br>CONTRATO</TH>
							<TH>CONTRATO<br>ADJUNTO</TH>
							
							
							
							
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*41.8*/for(lista1 <- listCotizaciones) yield /*41.39*/{_display_(Seq[Any](format.raw/*41.40*/("""
							"""),format.raw/*42.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*43.39*/lista1/*43.45*/.get(13)),format.raw/*43.53*/("""</td>
								<td style="text-align:left;">"""),_display_(/*44.39*/lista1/*44.45*/.get(14)),format.raw/*44.53*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*46.47*/lista1/*46.53*/.get(0)),format.raw/*46.60*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*48.34*/lista1/*48.40*/.get(1)),format.raw/*48.47*/("""</font>
											</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*53.37*/lista1/*53.43*/.get(2)),format.raw/*53.50*/("""</div>
									"""),_display_(/*54.11*/utilities/*54.20*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*54.49*/("""
								"""),format.raw/*55.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*56.74*/lista1/*56.80*/.get(3)),format.raw/*56.87*/("""')">"""),_display_(/*56.92*/lista1/*56.98*/.get(3)),format.raw/*56.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*57.75*/lista1/*57.81*/.get(6)),format.raw/*57.88*/("""')">"""),_display_(/*57.93*/lista1/*57.99*/.get(6)),format.raw/*57.106*/("""</a></td>
								
								<td style="text-align:left;">"""),_display_(/*59.39*/lista1/*59.45*/.get(4)),format.raw/*59.52*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(5).equals("0"))/*61.40*/{_display_(Seq[Any](format.raw/*61.41*/("""
										"""),format.raw/*62.11*/("""--
									""")))}else/*63.15*/{_display_(Seq[Any](format.raw/*63.16*/("""
										"""),format.raw/*64.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*64.52*/lista1/*64.58*/.get(5)),format.raw/*64.65*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*67.11*/("""
								"""),format.raw/*68.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(10).equals("0"))/*70.41*/{_display_(Seq[Any](format.raw/*70.42*/("""
										"""),format.raw/*71.11*/("""--
									""")))}else/*72.15*/{_display_(Seq[Any](format.raw/*72.16*/("""
										"""),format.raw/*73.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*73.52*/lista1/*73.58*/.get(10)),format.raw/*73.66*/("""')">
											<kbd style="background-color: #7F8C8D">PDF</kbd>
										</a>
									""")))}),format.raw/*76.11*/("""
								"""),format.raw/*77.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(11).equals("0"))/*79.41*/{_display_(Seq[Any](format.raw/*79.42*/("""
										"""),format.raw/*80.11*/("""--
									""")))}else/*81.15*/{_display_(Seq[Any](format.raw/*81.16*/("""
										"""),format.raw/*82.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*82.52*/lista1/*82.58*/.get(11)),format.raw/*82.66*/("""')">
											<kbd style="background-color: #7F8C8D">PDF</kbd>
										</a>
									""")))}),format.raw/*85.11*/("""
								"""),format.raw/*86.9*/("""</td>
								<td  style="text-align:center;">
									<form id="hacer_"""),_display_(/*88.27*/lista1/*88.33*/.get(0)),format.raw/*88.40*/("""" action="/routes2/datosContrato/" enctype="multipart/form-data" method="POST">
										<input type="hidden" name="id_cotizacion" value=""""),_display_(/*89.61*/lista1/*89.67*/.get(0)),format.raw/*89.74*/("""">
										<a href="#" onclick="$('#hacer_"""),_display_(/*90.43*/lista1/*90.49*/.get(0)),format.raw/*90.56*/("""').submit();">
											<kbd style="background-color: #73C6B6">Hacer</kbd>
										</a>
									</form>
								</td>
								<td style="text-align:center;">
									<form id="form_"""),_display_(/*96.26*/lista1/*96.32*/.get(0)),format.raw/*96.39*/("""" action="/routes2/grabarContratoPdf/"""),_display_(/*96.77*/lista1/*96.83*/.get(0)),format.raw/*96.90*/("""" enctype="multipart/form-data" method="POST">
										"""),_display_(if(lista1.get(12).equals("0"))/*97.42*/{_display_(Seq[Any](format.raw/*97.43*/("""
											"""),format.raw/*98.12*/("""<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
												<div id="txtBtn">Adjuntar</div>
						    					<input type="file" id="docAdjunto_"""),_display_(/*100.51*/lista1/*100.57*/.get(0)),format.raw/*100.64*/("""" name="docAdjunto" 
													onchange="LimitAttach(this.form, this.form.docAdjunto.value, '"""),_display_(/*101.77*/lista1/*101.83*/.get(0)),format.raw/*101.90*/("""'); 
													 	document.getElementById('mostrarmostrar').style.display='none';
														$('#form_"""),_display_(/*103.25*/lista1/*103.31*/.get(0)),format.raw/*103.38*/("""').submit();">
											</span>
										""")))}else/*105.16*/{_display_(Seq[Any](format.raw/*105.17*/("""
											"""),format.raw/*106.12*/("""<span class="btn btn-warning btn-sm btn-file" style="font-size: 8px;">
												<div id="txtBtn">Cambiar</div>
						    					<input type="file" id="docAdjunto_"""),_display_(/*108.51*/lista1/*108.57*/.get(0)),format.raw/*108.64*/("""" name="docAdjunto" 
													onchange="LimitAttach(this.form, this.form.docAdjunto.value, '"""),_display_(/*109.77*/lista1/*109.83*/.get(0)),format.raw/*109.90*/("""'); 
													 	document.getElementById('mostrarmostrar').style.display='none';
														$('#form_"""),_display_(/*111.25*/lista1/*111.31*/.get(0)),format.raw/*111.38*/("""').submit();">
											</span>
										""")))}),format.raw/*113.12*/("""
									"""),format.raw/*114.10*/("""</form>
								</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(12).equals("0"))/*117.41*/{_display_(Seq[Any](format.raw/*117.42*/("""
										"""),format.raw/*118.11*/("""--
									""")))}else/*119.15*/{_display_(Seq[Any](format.raw/*119.16*/("""
										"""),format.raw/*120.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*120.52*/lista1/*120.58*/.get(12)),format.raw/*120.66*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*123.11*/("""
								"""),format.raw/*124.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*126.9*/("""
					"""),format.raw/*127.6*/("""</tbody>
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
	
	

""")))}),format.raw/*167.2*/("""


"""),format.raw/*170.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*171.31*/("""{"""),format.raw/*171.32*/("""
		  """),format.raw/*172.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*172.36*/("""{"""),format.raw/*172.37*/("""
		    	"""),format.raw/*173.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 3, "desc" ]],
		    	"language": """),format.raw/*176.20*/("""{"""),format.raw/*176.21*/("""
		        	"""),format.raw/*177.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*178.11*/("""}"""),format.raw/*178.12*/("""
		  """),format.raw/*179.5*/("""}"""),format.raw/*179.6*/(""" """),format.raw/*179.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*181.2*/("""}"""),format.raw/*181.3*/(""");
	
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*184.43*/("""{"""),format.raw/*184.44*/("""
		"""),format.raw/*185.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*187.16*/("""{"""),format.raw/*187.17*/("""
            """),format.raw/*188.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*195.36*/("""{"""),format.raw/*195.37*/("""
	     		"""),format.raw/*196.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*198.8*/("""}"""),format.raw/*198.9*/("""
        """),format.raw/*199.9*/("""}"""),format.raw/*199.10*/(""");
	"""),format.raw/*200.2*/("""}"""),format.raw/*200.3*/("""
	
	"""),format.raw/*202.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*202.43*/("""{"""),format.raw/*202.44*/("""
		  """),format.raw/*203.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*205.2*/("""}"""),format.raw/*205.3*/("""
	
	"""),format.raw/*207.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file, id_cotizacion) => """),format.raw/*208.53*/("""{"""),format.raw/*208.54*/("""
		"""),format.raw/*209.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*211.35*/("""{"""),format.raw/*211.36*/("""
			"""),format.raw/*212.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*213.3*/("""}"""),format.raw/*213.4*/("""
		"""),format.raw/*214.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*215.45*/("""{"""),format.raw/*215.46*/("""
			"""),format.raw/*216.4*/("""if (extArray[i] == extencion) """),format.raw/*216.34*/("""{"""),format.raw/*216.35*/(""" """),format.raw/*216.36*/("""allowSubmit = true; break; """),format.raw/*216.63*/("""}"""),format.raw/*216.64*/("""
		"""),format.raw/*217.3*/("""}"""),format.raw/*217.4*/("""
		"""),format.raw/*218.3*/("""if (allowSubmit) """),format.raw/*218.20*/("""{"""),format.raw/*218.21*/("""
			"""),format.raw/*219.4*/("""var file = $("#docAdjunto_"+id_cotizacion)[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*221.26*/("""{"""),format.raw/*221.27*/("""
				"""),format.raw/*222.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*225.4*/("""}"""),format.raw/*225.5*/("""
		"""),format.raw/*226.3*/("""}"""),format.raw/*226.4*/("""else"""),format.raw/*226.8*/("""{"""),format.raw/*226.9*/("""
			"""),format.raw/*227.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*231.3*/("""}"""),format.raw/*231.4*/("""
	"""),format.raw/*232.2*/("""}"""),format.raw/*232.3*/("""
	
		
		
	
	
	
"""),format.raw/*239.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listCotizaciones:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listCotizaciones) => apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otGenerarContrato.scala.html
                  HASH: cf8091b7847d7c400d3f9c0d931b22af2e107c52
                  MATRIX: 1035->1|1263->136|1296->144|1312->152|1351->154|1380->158|1448->206|1478->211|1520->233|1549->236|1593->259|1624->263|1701->314|1826->417|1856->420|2418->955|2441->969|2473->980|2727->1208|2774->1239|2813->1240|2848->1248|2918->1291|2933->1297|2962->1305|3033->1349|3048->1355|3077->1363|3196->1455|3211->1461|3239->1468|3365->1567|3380->1573|3408->1580|3565->1710|3580->1716|3608->1723|3652->1740|3670->1749|3720->1778|3756->1787|3862->1866|3877->1872|3905->1879|3937->1884|3952->1890|3981->1897|4092->1981|4107->1987|4135->1994|4167->1999|4182->2005|4211->2012|4295->2069|4310->2075|4338->2082|4450->2167|4489->2168|4528->2179|4564->2196|4603->2197|4642->2208|4710->2249|4725->2255|4753->2262|4874->2352|4910->2361|5023->2447|5062->2448|5101->2459|5137->2476|5176->2477|5215->2488|5283->2529|5298->2535|5327->2543|5448->2633|5484->2642|5597->2728|5636->2729|5675->2740|5711->2757|5750->2758|5789->2769|5857->2810|5872->2816|5901->2824|6022->2914|6058->2923|6158->2996|6173->3002|6201->3009|6368->3149|6383->3155|6411->3162|6483->3207|6498->3213|6526->3220|6741->3408|6756->3414|6784->3421|6849->3459|6864->3465|6892->3472|7007->3560|7046->3561|7086->3573|7276->3735|7292->3741|7321->3748|7446->3845|7462->3851|7491->3858|7627->3966|7643->3972|7672->3979|7741->4028|7781->4029|7822->4041|8014->4205|8030->4211|8059->4218|8184->4315|8200->4321|8229->4328|8365->4436|8381->4442|8410->4449|8487->4494|8526->4504|8656->4606|8696->4607|8736->4618|8773->4635|8813->4636|8853->4647|8922->4688|8938->4694|8968->4702|9090->4792|9127->4801|9185->4828|9219->4834|10593->6177|10624->6180|10715->6242|10745->6243|10778->6248|10838->6279|10868->6280|10904->6288|11080->6435|11110->6436|11151->6448|11258->6526|11288->6527|11321->6532|11350->6533|11379->6534|11480->6607|11509->6608|11587->6657|11617->6658|11648->6661|11775->6759|11805->6760|11847->6773|12106->7003|12136->7004|12173->7013|12326->7138|12355->7139|12392->7148|12422->7149|12454->7153|12483->7154|12515->7158|12585->7199|12615->7200|12648->7205|12780->7309|12809->7310|12841->7314|13047->7491|13077->7492|13108->7495|13217->7575|13247->7576|13279->7580|13352->7625|13381->7626|13412->7629|13550->7738|13580->7739|13612->7743|13671->7773|13701->7774|13731->7775|13787->7802|13817->7803|13848->7806|13877->7807|13908->7810|13954->7827|13984->7828|14016->7832|14176->7963|14206->7964|14239->7969|14443->8145|14472->8146|14503->8149|14532->8150|14564->8154|14593->8155|14625->8159|14842->8348|14871->8349|14901->8351|14930->8352|14973->8367
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|59->28|59->28|59->28|72->41|72->41|72->41|73->42|74->43|74->43|74->43|75->44|75->44|75->44|77->46|77->46|77->46|79->48|79->48|79->48|84->53|84->53|84->53|85->54|85->54|85->54|86->55|87->56|87->56|87->56|87->56|87->56|87->56|88->57|88->57|88->57|88->57|88->57|88->57|90->59|90->59|90->59|92->61|92->61|93->62|94->63|94->63|95->64|95->64|95->64|95->64|98->67|99->68|101->70|101->70|102->71|103->72|103->72|104->73|104->73|104->73|104->73|107->76|108->77|110->79|110->79|111->80|112->81|112->81|113->82|113->82|113->82|113->82|116->85|117->86|119->88|119->88|119->88|120->89|120->89|120->89|121->90|121->90|121->90|127->96|127->96|127->96|127->96|127->96|127->96|128->97|128->97|129->98|131->100|131->100|131->100|132->101|132->101|132->101|134->103|134->103|134->103|136->105|136->105|137->106|139->108|139->108|139->108|140->109|140->109|140->109|142->111|142->111|142->111|144->113|145->114|148->117|148->117|149->118|150->119|150->119|151->120|151->120|151->120|151->120|154->123|155->124|157->126|158->127|198->167|201->170|202->171|202->171|203->172|203->172|203->172|204->173|207->176|207->176|208->177|209->178|209->178|210->179|210->179|210->179|212->181|212->181|215->184|215->184|216->185|218->187|218->187|219->188|226->195|226->195|227->196|229->198|229->198|230->199|230->199|231->200|231->200|233->202|233->202|233->202|234->203|236->205|236->205|238->207|239->208|239->208|240->209|242->211|242->211|243->212|244->213|244->213|245->214|246->215|246->215|247->216|247->216|247->216|247->216|247->216|247->216|248->217|248->217|249->218|249->218|249->218|250->219|252->221|252->221|253->222|256->225|256->225|257->226|257->226|257->226|257->226|258->227|262->231|262->231|263->232|263->232|270->239
                  -- GENERATED --
              */
          