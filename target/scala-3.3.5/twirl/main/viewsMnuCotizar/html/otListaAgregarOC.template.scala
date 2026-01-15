
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

object otListaAgregarOC extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listOt: List[List[String]], desdeAAMMDD: String, hastaAAMMDD: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, mapDiccionario.get("Ordenes_de_trabajo").toUpperCase(),"AGREGAR ORDENES DE COMPRA")),format.raw/*14.115*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>Nro."""),_display_(/*22.17*/mapDiccionario/*22.31*/.get("OT")),format.raw/*22.41*/("""</TH>
							<TH style="min-width:80px;">FECHA<br>"""),_display_(/*23.46*/mapDiccionario/*23.60*/.get("OT")),format.raw/*23.70*/("""</TH>
							<TH>Nro.COTI</TH>
							<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
							<TH>"""),_display_(/*26.13*/mapDiccionario/*26.27*/.get("BODEGA")),format.raw/*26.41*/("""/PROYECTO</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>NRO OC<br>CLIENTE</TH>
							<TH>ADJUNTAR<br>OC CLIENTE</TH>
							<TH>OC CLIENTE<br>ADJUNTO</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*35.8*/for(lista1 <- listOt) yield /*35.29*/{_display_(Seq[Any](format.raw/*35.30*/("""
							"""),format.raw/*36.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*37.39*/lista1/*37.45*/.get(17)),format.raw/*37.53*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if( ! lista1.get(0).equals("0"))/*39.43*/{_display_(Seq[Any](format.raw/*39.44*/("""
										"""),format.raw/*40.11*/("""<a href="#" onclick="verOt('"""),_display_(/*40.40*/lista1/*40.46*/.get(0)),format.raw/*40.53*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*42.34*/lista1/*42.40*/.get(2)),format.raw/*42.47*/("""</font>
											</kbd>
										</a>
									""")))} else {null} ),format.raw/*45.11*/("""
									
								"""),format.raw/*47.9*/("""</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*49.37*/lista1/*49.43*/.get(3)),format.raw/*49.50*/("""</div>
									"""),_display_(/*50.11*/utilities/*50.20*/.Fechas.DDMMAA(lista1.get(3))),format.raw/*50.49*/("""
								"""),format.raw/*51.9*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*53.47*/lista1/*53.53*/.get(1)),format.raw/*53.60*/("""')">
										<kbd style="background-color: rgb(90, 200, 245)">
											<font color="green">"""),_display_(/*55.33*/lista1/*55.39*/.get(4)),format.raw/*55.46*/("""</font>
										</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*60.37*/lista1/*60.43*/.get(5)),format.raw/*60.50*/("""</div>
									"""),_display_(/*61.11*/utilities/*61.20*/.Fechas.DDMMAA(lista1.get(5))),format.raw/*61.49*/("""
								"""),format.raw/*62.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*63.73*/lista1/*63.79*/.get(14)),format.raw/*63.87*/("""')">"""),_display_(/*63.92*/lista1/*63.98*/.get(14)),format.raw/*63.106*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*64.74*/lista1/*64.80*/.get(6)),format.raw/*64.87*/("""')">"""),_display_(/*64.92*/lista1/*64.98*/.get(6)),format.raw/*64.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*65.75*/lista1/*65.81*/.get(7)),format.raw/*65.88*/("""')">"""),_display_(/*65.93*/lista1/*65.99*/.get(7)),format.raw/*65.106*/("""</a></td>
								
								
								<td style="text-align:center;">
									<div id="auxNroOC_"""),_display_(/*69.29*/lista1/*69.35*/.get(0)),format.raw/*69.42*/("""" style="display:none">"""),_display_(/*69.66*/lista1/*69.72*/.get(16)),format.raw/*69.80*/("""</div>
									<input type="text" class="form-control height23px left"
											id="numeroOC_"""),_display_(/*71.26*/lista1/*71.32*/.get(0)),format.raw/*71.39*/(""""
											value=""""),_display_(/*72.20*/lista1/*72.26*/.get(16)),format.raw/*72.34*/(""""
											autocomplete="off"
											onchange="actualizaOC(id, '"""),_display_(/*74.40*/lista1/*74.46*/.get(1)),format.raw/*74.53*/("""')">
								</td>
								<td style="text-align:center;">
									<form id="form_"""),_display_(/*77.26*/lista1/*77.32*/.get(0)),format.raw/*77.39*/(""""action="/routes2/grabarOcPdf/"""),_display_(/*77.70*/lista1/*77.76*/.get(1)),format.raw/*77.83*/(""","""),_display_(/*77.85*/desdeAAMMDD),format.raw/*77.96*/(""","""),_display_(/*77.98*/hastaAAMMDD),format.raw/*77.109*/("""" enctype="multipart/form-data" method="POST">
										<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											"""),_display_(if(lista1.get(15).equals("0"))/*79.43*/{_display_(Seq[Any](format.raw/*79.44*/("""
												"""),format.raw/*80.13*/("""<div id="txtBtn">Adjuntar</div>
											""")))}else/*81.17*/{_display_(Seq[Any](format.raw/*81.18*/("""
												"""),format.raw/*82.13*/("""<div id="txtBtn">Reemplazar</div>
											""")))}),format.raw/*83.13*/("""
					    					"""),format.raw/*84.15*/("""<input type="file" id="docAdjunto_"""),_display_(/*84.50*/lista1/*84.56*/.get(0)),format.raw/*84.63*/("""" name="docAdjunto" 
												onchange="LimitAttach(this.form, this.form.docAdjunto.value, '"""),_display_(/*85.76*/lista1/*85.82*/.get(1)),format.raw/*85.89*/("""'); 
												 	document.getElementById('mostrarmostrar').style.display='none';
													$('#form_"""),_display_(/*87.24*/lista1/*87.30*/.get(0)),format.raw/*87.37*/("""').submit();">
										</span>
									</form>
								</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(15).equals("0"))/*92.41*/{_display_(Seq[Any](format.raw/*92.42*/("""
										"""),format.raw/*93.11*/("""--
									""")))}else/*94.15*/{_display_(Seq[Any](format.raw/*94.16*/("""
										"""),format.raw/*95.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*95.52*/lista1/*95.58*/.get(15)),format.raw/*95.66*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*98.11*/("""
								"""),format.raw/*99.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*101.9*/("""
					"""),format.raw/*102.6*/("""</tbody>
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
					<h5 class='modal-title'>"""),_display_(/*119.31*/mapDiccionario/*119.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*119.69*/("""</h5>
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
	


""")))}),format.raw/*163.2*/("""


"""),format.raw/*166.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*167.31*/("""{"""),format.raw/*167.32*/("""
		  """),format.raw/*168.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*168.36*/("""{"""),format.raw/*168.37*/("""
		    	"""),format.raw/*169.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "desc" ]],
		    	"language": """),format.raw/*172.20*/("""{"""),format.raw/*172.21*/("""
		        	"""),format.raw/*173.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*174.11*/("""}"""),format.raw/*174.12*/("""
		  """),format.raw/*175.5*/("""}"""),format.raw/*175.6*/(""" """),format.raw/*175.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*177.2*/("""}"""),format.raw/*177.3*/(""");
	
	
	const verOt = (id_ot) => """),format.raw/*180.27*/("""{"""),format.raw/*180.28*/("""
		"""),format.raw/*181.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*183.16*/("""{"""),format.raw/*183.17*/("""
            """),format.raw/*184.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*191.36*/("""{"""),format.raw/*191.37*/("""
	     		"""),format.raw/*192.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*194.8*/("""}"""),format.raw/*194.9*/("""
        """),format.raw/*195.9*/("""}"""),format.raw/*195.10*/(""");
	"""),format.raw/*196.2*/("""}"""),format.raw/*196.3*/("""
	
	"""),format.raw/*198.2*/("""const verCotizacion = (id_cotizacion) => """),format.raw/*198.43*/("""{"""),format.raw/*198.44*/("""
		"""),format.raw/*199.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*201.16*/("""{"""),format.raw/*201.17*/("""
            """),format.raw/*202.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*209.36*/("""{"""),format.raw/*209.37*/("""
	     		"""),format.raw/*210.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*212.8*/("""}"""),format.raw/*212.9*/("""
        """),format.raw/*213.9*/("""}"""),format.raw/*213.10*/(""");
	"""),format.raw/*214.2*/("""}"""),format.raw/*214.3*/("""
	
	"""),format.raw/*216.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*216.43*/("""{"""),format.raw/*216.44*/("""
		"""),format.raw/*217.3*/("""$('#descargaDocumento').val(nombreDOC);
		document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*219.2*/("""}"""),format.raw/*219.3*/("""
	
	"""),format.raw/*221.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file, id_coti) => """),format.raw/*222.47*/("""{"""),format.raw/*222.48*/("""
		"""),format.raw/*223.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*225.35*/("""{"""),format.raw/*225.36*/("""
			"""),format.raw/*226.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*227.3*/("""}"""),format.raw/*227.4*/("""
		"""),format.raw/*228.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*229.45*/("""{"""),format.raw/*229.46*/("""
			"""),format.raw/*230.4*/("""if (extArray[i] == extencion) """),format.raw/*230.34*/("""{"""),format.raw/*230.35*/(""" """),format.raw/*230.36*/("""allowSubmit = true; break; """),format.raw/*230.63*/("""}"""),format.raw/*230.64*/("""
		"""),format.raw/*231.3*/("""}"""),format.raw/*231.4*/("""
		"""),format.raw/*232.3*/("""if (allowSubmit) """),format.raw/*232.20*/("""{"""),format.raw/*232.21*/("""
			"""),format.raw/*233.4*/("""var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*234.26*/("""{"""),format.raw/*234.27*/("""
				"""),format.raw/*235.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*238.4*/("""}"""),format.raw/*238.5*/("""
		"""),format.raw/*239.3*/("""}"""),format.raw/*239.4*/("""else"""),format.raw/*239.8*/("""{"""),format.raw/*239.9*/("""
			"""),format.raw/*240.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*244.3*/("""}"""),format.raw/*244.4*/("""
	"""),format.raw/*245.2*/("""}"""),format.raw/*245.3*/("""
	
	"""),format.raw/*247.2*/("""const actualizaOC = (id, id_cotizacion) => """),format.raw/*247.45*/("""{"""),format.raw/*247.46*/("""
		"""),format.raw/*248.3*/("""let valor = $("#"+id).val();
		var formData = new FormData();
		formData.append('id_cotizacion', id_cotizacion);
	  	formData.append('numeroOC',valor);
        $.ajax("""),format.raw/*252.16*/("""{"""),format.raw/*252.17*/("""
            """),format.raw/*253.13*/("""url: "/routes2/oc_cotiClienteAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*260.36*/("""{"""),format.raw/*260.37*/("""
				"""),format.raw/*261.5*/("""let aux = id.replace("numeroOC_","auxNroOC_"); 
				$("#"+aux).text(valor);
			"""),format.raw/*263.4*/("""}"""),format.raw/*263.5*/("""
        """),format.raw/*264.9*/("""}"""),format.raw/*264.10*/(""");
	"""),format.raw/*265.2*/("""}"""),format.raw/*265.3*/("""
	
		
	
	
	
"""),format.raw/*271.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listOt:List[List[String]],desdeAAMMDD:String,hastaAAMMDD:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listOt,desdeAAMMDD,hastaAAMMDD)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listOt,desdeAAMMDD,hastaAAMMDD) => apply(mapDiccionario,mapPermiso,userMnu,listOt,desdeAAMMDD,hastaAAMMDD)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otListaAgregarOC.scala.html
                  HASH: 9bdded446866b3dae03e6fb10f939c6f8c4fd110
                  MATRIX: 1048->1|1308->168|1341->176|1357->184|1396->186|1425->190|1493->238|1523->243|1578->278|1607->281|1650->303|1679->306|1723->329|1754->333|1831->384|1964->495|1994->498|2336->813|2359->827|2390->837|2468->888|2491->902|2522->912|2652->1015|2675->1029|2710->1043|2957->1264|2994->1285|3033->1286|3068->1294|3138->1337|3153->1343|3182->1351|3297->1439|3336->1440|3375->1451|3431->1480|3446->1486|3474->1493|3600->1592|3615->1598|3643->1605|3738->1656|3784->1675|3893->1757|3908->1763|3936->1770|3980->1787|3998->1796|4048->1825|4084->1834|4203->1926|4218->1932|4246->1939|4370->2036|4385->2042|4413->2049|4569->2178|4584->2184|4612->2191|4656->2208|4674->2217|4724->2246|4760->2255|4865->2333|4880->2339|4909->2347|4941->2352|4956->2358|4986->2366|5096->2449|5111->2455|5139->2462|5171->2467|5186->2473|5215->2480|5326->2564|5341->2570|5369->2577|5401->2582|5416->2588|5445->2595|5568->2691|5583->2697|5611->2704|5662->2728|5677->2734|5706->2742|5830->2839|5845->2845|5873->2852|5921->2873|5936->2879|5965->2887|6063->2958|6078->2964|6106->2971|6217->3055|6232->3061|6260->3068|6318->3099|6333->3105|6361->3112|6390->3114|6422->3125|6451->3127|6484->3138|6678->3305|6717->3306|6758->3319|6825->3367|6864->3368|6905->3381|6982->3427|7025->3442|7087->3477|7102->3483|7130->3490|7253->3586|7268->3592|7296->3599|7429->3705|7444->3711|7472->3718|7643->3862|7682->3863|7721->3874|7757->3891|7796->3892|7835->3903|7903->3944|7918->3950|7947->3958|8068->4048|8104->4057|8162->4084|8196->4090|8853->4719|8877->4733|8923->4757|10472->6275|10503->6278|10594->6340|10624->6341|10657->6346|10717->6377|10747->6378|10783->6386|10959->6533|10989->6534|11030->6546|11137->6624|11167->6625|11200->6630|11229->6631|11258->6632|11359->6705|11388->6706|11450->6739|11480->6740|11511->6743|11622->6825|11652->6826|11694->6839|11945->7061|11975->7062|12012->7071|12149->7180|12178->7181|12215->7190|12245->7191|12277->7195|12306->7196|12338->7200|12408->7241|12438->7242|12469->7245|12596->7343|12626->7344|12668->7357|12927->7587|12957->7588|12994->7597|13147->7722|13176->7723|13213->7732|13243->7733|13275->7737|13304->7738|13336->7742|13406->7783|13436->7784|13467->7787|13597->7889|13626->7890|13658->7894|13858->8065|13888->8066|13919->8069|14028->8149|14058->8150|14090->8154|14163->8199|14192->8200|14223->8203|14361->8312|14391->8313|14423->8317|14482->8347|14512->8348|14542->8349|14598->8376|14628->8377|14659->8380|14688->8381|14719->8384|14765->8401|14795->8402|14827->8406|14928->8478|14958->8479|14991->8484|15195->8660|15224->8661|15255->8664|15284->8665|15316->8669|15345->8670|15377->8674|15594->8863|15623->8864|15653->8866|15682->8867|15714->8871|15786->8914|15816->8915|15847->8918|16043->9085|16073->9086|16115->9099|16383->9338|16413->9339|16446->9344|16553->9423|16582->9424|16619->9433|16649->9434|16681->9438|16710->9439|16750->9451
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|53->22|53->22|53->22|54->23|54->23|54->23|57->26|57->26|57->26|66->35|66->35|66->35|67->36|68->37|68->37|68->37|70->39|70->39|71->40|71->40|71->40|71->40|73->42|73->42|73->42|76->45|78->47|80->49|80->49|80->49|81->50|81->50|81->50|82->51|84->53|84->53|84->53|86->55|86->55|86->55|91->60|91->60|91->60|92->61|92->61|92->61|93->62|94->63|94->63|94->63|94->63|94->63|94->63|95->64|95->64|95->64|95->64|95->64|95->64|96->65|96->65|96->65|96->65|96->65|96->65|100->69|100->69|100->69|100->69|100->69|100->69|102->71|102->71|102->71|103->72|103->72|103->72|105->74|105->74|105->74|108->77|108->77|108->77|108->77|108->77|108->77|108->77|108->77|108->77|108->77|110->79|110->79|111->80|112->81|112->81|113->82|114->83|115->84|115->84|115->84|115->84|116->85|116->85|116->85|118->87|118->87|118->87|123->92|123->92|124->93|125->94|125->94|126->95|126->95|126->95|126->95|129->98|130->99|132->101|133->102|150->119|150->119|150->119|194->163|197->166|198->167|198->167|199->168|199->168|199->168|200->169|203->172|203->172|204->173|205->174|205->174|206->175|206->175|206->175|208->177|208->177|211->180|211->180|212->181|214->183|214->183|215->184|222->191|222->191|223->192|225->194|225->194|226->195|226->195|227->196|227->196|229->198|229->198|229->198|230->199|232->201|232->201|233->202|240->209|240->209|241->210|243->212|243->212|244->213|244->213|245->214|245->214|247->216|247->216|247->216|248->217|250->219|250->219|252->221|253->222|253->222|254->223|256->225|256->225|257->226|258->227|258->227|259->228|260->229|260->229|261->230|261->230|261->230|261->230|261->230|261->230|262->231|262->231|263->232|263->232|263->232|264->233|265->234|265->234|266->235|269->238|269->238|270->239|270->239|270->239|270->239|271->240|275->244|275->244|276->245|276->245|278->247|278->247|278->247|279->248|283->252|283->252|284->253|291->260|291->260|292->261|294->263|294->263|295->264|295->264|296->265|296->265|302->271
                  -- GENERATED --
              */
          