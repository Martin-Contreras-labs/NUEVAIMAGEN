
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

object otOdoGenerarContrato extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[Long],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listCotizaciones: List[List[String]], listAnios: List[Long]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "COTIZACIONES - GENERAR CONTRATOS ODO","(Solo sobre cotizaciones confirmadas)")),format.raw/*13.111*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
			
			
				<div align="center">
					"""),_display_(/*19.7*/for(anio <-listAnios) yield /*19.28*/{_display_(Seq[Any](format.raw/*19.29*/("""
						"""),format.raw/*20.7*/("""<a href="#" onclick="location.href='/routes2/otOdoGenerarContrato/"""),_display_(/*20.74*/anio),format.raw/*20.78*/("""'">
							<kbd style="background-color: #73C6B6">"""),_display_(/*21.48*/anio),format.raw/*21.52*/("""</kbd>
						</a>
					""")))}),format.raw/*23.7*/("""
				"""),format.raw/*24.5*/("""</div>
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>Nro.COTI</TH>
							<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
							<TH>CLIENTE</TH>
							<TH>PROYECTO</TH>
							<TH>OBSERVACIONES</TH>
							<TH>DOC<br>ADJ</TH>
							<TH>PDF<br>VTA</TH>
							
							<TH>GENERAR<br>CONTRATO</TH>
							<TH>ADJUNTAR<br>CONTRATO</TH>
							<TH>CONTRATO<br>ADJUNTO</TH>
							
							
							
							
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*47.8*/for(lista1 <- listCotizaciones) yield /*47.39*/{_display_(Seq[Any](format.raw/*47.40*/("""
							"""),format.raw/*48.8*/("""<TR>
								<td style="text-align:center;">
									<a href="#" onclick="verCotizacion('"""),_display_(/*50.47*/lista1/*50.53*/.get(0)),format.raw/*50.60*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*52.34*/lista1/*52.40*/.get(1)),format.raw/*52.47*/("""</font>
											</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*57.37*/lista1/*57.43*/.get(2)),format.raw/*57.50*/("""</div>
									"""),_display_(/*58.11*/utilities/*58.20*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*58.49*/("""
								"""),format.raw/*59.9*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*60.74*/lista1/*60.80*/.get(3)),format.raw/*60.87*/("""')">"""),_display_(/*60.92*/lista1/*60.98*/.get(3)),format.raw/*60.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*61.75*/lista1/*61.81*/.get(6)),format.raw/*61.88*/("""')">"""),_display_(/*61.93*/lista1/*61.99*/.get(6)),format.raw/*61.106*/("""</a></td>
								
								<td style="text-align:left;">"""),_display_(/*63.39*/lista1/*63.45*/.get(4)),format.raw/*63.52*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(5).equals("0"))/*65.40*/{_display_(Seq[Any](format.raw/*65.41*/("""
										"""),format.raw/*66.11*/("""--
									""")))}else/*67.15*/{_display_(Seq[Any](format.raw/*67.16*/("""
										"""),format.raw/*68.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*68.52*/lista1/*68.58*/.get(5)),format.raw/*68.65*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*71.11*/("""
								"""),format.raw/*72.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(10).equals("0"))/*74.41*/{_display_(Seq[Any](format.raw/*74.42*/("""
										"""),format.raw/*75.11*/("""--
									""")))}else/*76.15*/{_display_(Seq[Any](format.raw/*76.16*/("""
										"""),format.raw/*77.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*77.52*/lista1/*77.58*/.get(10)),format.raw/*77.66*/("""')">
											<kbd style="background-color: #7F8C8D">PDF</kbd>
										</a>
									""")))}),format.raw/*80.11*/("""
								"""),format.raw/*81.9*/("""</td>
								<td  style="text-align:center;">
									<form id="hacer_"""),_display_(/*83.27*/lista1/*83.33*/.get(0)),format.raw/*83.40*/("""" action="/routes2/datosContratoOdo/" enctype="multipart/form-data" method="POST">
										<input type="hidden" name="id_cotiOdo" value=""""),_display_(/*84.58*/lista1/*84.64*/.get(0)),format.raw/*84.71*/("""">
										<a href="#" onclick="$('#hacer_"""),_display_(/*85.43*/lista1/*85.49*/.get(0)),format.raw/*85.56*/("""').submit();">
											<kbd style="background-color: #73C6B6">Hacer</kbd>
										</a>
									</form>
								</td>
								<td style="text-align:center;">
									<form id="form_"""),_display_(/*91.26*/lista1/*91.32*/.get(0)),format.raw/*91.39*/("""" action="/routes2/grabarContratoPdfOdo/"""),_display_(/*91.80*/lista1/*91.86*/.get(0)),format.raw/*91.93*/("""" enctype="multipart/form-data" method="POST">
										"""),_display_(if(lista1.get(11).equals("0"))/*92.42*/{_display_(Seq[Any](format.raw/*92.43*/("""
											"""),format.raw/*93.12*/("""<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
												<div id="txtBtn">Adjuntar</div>
						    					<input type="file" id="docAdjunto_"""),_display_(/*95.51*/lista1/*95.57*/.get(0)),format.raw/*95.64*/("""" name="docAdjunto" 
													onchange="LimitAttach(this.form, this.form.docAdjunto.value, '"""),_display_(/*96.77*/lista1/*96.83*/.get(0)),format.raw/*96.90*/("""'); 
													 	document.getElementById('mostrarmostrar').style.display='none';
														$('#form_"""),_display_(/*98.25*/lista1/*98.31*/.get(0)),format.raw/*98.38*/("""').submit();">
											</span>
										""")))}else/*100.16*/{_display_(Seq[Any](format.raw/*100.17*/("""
											"""),format.raw/*101.12*/("""<span class="btn btn-warning btn-sm btn-file" style="font-size: 8px;">
												<div id="txtBtn">Cambiar</div>
						    					<input type="file" id="docAdjunto_"""),_display_(/*103.51*/lista1/*103.57*/.get(0)),format.raw/*103.64*/("""" name="docAdjunto" 
													onchange="LimitAttach(this.form, this.form.docAdjunto.value, '"""),_display_(/*104.77*/lista1/*104.83*/.get(0)),format.raw/*104.90*/("""'); 
													 	document.getElementById('mostrarmostrar').style.display='none';
														$('#form_"""),_display_(/*106.25*/lista1/*106.31*/.get(0)),format.raw/*106.38*/("""').submit();">
											</span>
										""")))}),format.raw/*108.12*/("""
									"""),format.raw/*109.10*/("""</form>
								</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.get(11).equals("0"))/*112.41*/{_display_(Seq[Any](format.raw/*112.42*/("""
										"""),format.raw/*113.11*/("""--
									""")))}else/*114.15*/{_display_(Seq[Any](format.raw/*114.16*/("""
										"""),format.raw/*115.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*115.52*/lista1/*115.58*/.get(11)),format.raw/*115.66*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*118.11*/("""
								"""),format.raw/*119.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*121.9*/("""
					"""),format.raw/*122.6*/("""</tbody>
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
	
	

""")))}),format.raw/*162.2*/("""


"""),format.raw/*165.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*166.31*/("""{"""),format.raw/*166.32*/("""
		  """),format.raw/*167.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*167.36*/("""{"""),format.raw/*167.37*/("""
		    	"""),format.raw/*168.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*171.20*/("""{"""),format.raw/*171.21*/("""
		        	"""),format.raw/*172.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*173.11*/("""}"""),format.raw/*173.12*/("""
		  """),format.raw/*174.5*/("""}"""),format.raw/*174.6*/(""" """),format.raw/*174.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*176.2*/("""}"""),format.raw/*176.3*/(""");
	
	
	const verCotizacion = (id_cotiOdo) => """),format.raw/*179.40*/("""{"""),format.raw/*179.41*/("""
		"""),format.raw/*180.3*/("""var formData = new FormData();
	  	formData.append('id_cotiOdo',id_cotiOdo);
        $.ajax("""),format.raw/*182.16*/("""{"""),format.raw/*182.17*/("""
            """),format.raw/*183.13*/("""url: "/routes2/verCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*190.36*/("""{"""),format.raw/*190.37*/("""
	     		"""),format.raw/*191.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*193.8*/("""}"""),format.raw/*193.9*/("""
        """),format.raw/*194.9*/("""}"""),format.raw/*194.10*/(""");
	"""),format.raw/*195.2*/("""}"""),format.raw/*195.3*/("""
	
	"""),format.raw/*197.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*197.43*/("""{"""),format.raw/*197.44*/("""
		  """),format.raw/*198.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*200.2*/("""}"""),format.raw/*200.3*/("""
	
	"""),format.raw/*202.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file, id_cotizacion) => """),format.raw/*203.53*/("""{"""),format.raw/*203.54*/("""
		"""),format.raw/*204.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*206.35*/("""{"""),format.raw/*206.36*/("""
			"""),format.raw/*207.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*208.3*/("""}"""),format.raw/*208.4*/("""
		"""),format.raw/*209.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*210.45*/("""{"""),format.raw/*210.46*/("""
			"""),format.raw/*211.4*/("""if (extArray[i] == extencion) """),format.raw/*211.34*/("""{"""),format.raw/*211.35*/(""" """),format.raw/*211.36*/("""allowSubmit = true; break; """),format.raw/*211.63*/("""}"""),format.raw/*211.64*/("""
		"""),format.raw/*212.3*/("""}"""),format.raw/*212.4*/("""
		"""),format.raw/*213.3*/("""if (allowSubmit) """),format.raw/*213.20*/("""{"""),format.raw/*213.21*/("""
			"""),format.raw/*214.4*/("""var file = $("#docAdjunto_"+id_cotizacion)[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*216.26*/("""{"""),format.raw/*216.27*/("""
				"""),format.raw/*217.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*220.4*/("""}"""),format.raw/*220.5*/("""
		"""),format.raw/*221.3*/("""}"""),format.raw/*221.4*/("""else"""),format.raw/*221.8*/("""{"""),format.raw/*221.9*/("""
			"""),format.raw/*222.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*226.3*/("""}"""),format.raw/*226.4*/("""
	"""),format.raw/*227.2*/("""}"""),format.raw/*227.3*/("""
	
		
		
	
	
	
"""),format.raw/*234.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listCotizaciones:List[List[String]],listAnios:List[Long]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones,listAnios)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[Long]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listCotizaciones,listAnios) => apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones,listAnios)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/otOdoGenerarContrato.scala.html
                  HASH: 7515704959d7d6e6725dcb74dff7012f79c8db76
                  MATRIX: 1049->1|1300->159|1333->167|1349->175|1388->177|1417->181|1485->229|1515->234|1557->256|1586->259|1630->282|1661->286|1738->337|1867->444|1897->447|2064->588|2101->609|2140->610|2174->617|2268->684|2293->688|2371->739|2396->743|2450->767|2482->772|3101->1365|3148->1396|3187->1397|3222->1405|3340->1496|3355->1502|3383->1509|3509->1608|3524->1614|3552->1621|3709->1751|3724->1757|3752->1764|3796->1781|3814->1790|3864->1819|3900->1828|4006->1907|4021->1913|4049->1920|4081->1925|4096->1931|4125->1938|4236->2022|4251->2028|4279->2035|4311->2040|4326->2046|4355->2053|4439->2110|4454->2116|4482->2123|4594->2208|4633->2209|4672->2220|4708->2237|4747->2238|4786->2249|4854->2290|4869->2296|4897->2303|5018->2393|5054->2402|5167->2488|5206->2489|5245->2500|5281->2517|5320->2518|5359->2529|5427->2570|5442->2576|5471->2584|5592->2674|5628->2683|5728->2756|5743->2762|5771->2769|5938->2909|5953->2915|5981->2922|6053->2967|6068->2973|6096->2980|6311->3168|6326->3174|6354->3181|6422->3222|6437->3228|6465->3235|6580->3323|6619->3324|6659->3336|6848->3498|6863->3504|6891->3511|7015->3608|7030->3614|7058->3621|7193->3729|7208->3735|7236->3742|7305->3791|7345->3792|7386->3804|7578->3968|7594->3974|7623->3981|7748->4078|7764->4084|7793->4091|7929->4199|7945->4205|7974->4212|8051->4257|8090->4267|8220->4369|8260->4370|8300->4381|8337->4398|8377->4399|8417->4410|8486->4451|8502->4457|8532->4465|8654->4555|8691->4564|8749->4591|8783->4597|10157->5940|10188->5943|10279->6005|10309->6006|10342->6011|10402->6042|10432->6043|10468->6051|10658->6212|10688->6213|10729->6225|10836->6303|10866->6304|10899->6309|10928->6310|10957->6311|11058->6384|11087->6385|11162->6431|11192->6432|11223->6435|11344->6527|11374->6528|11416->6541|11680->6776|11710->6777|11747->6786|11900->6911|11929->6912|11966->6921|11996->6922|12028->6926|12057->6927|12089->6931|12159->6972|12189->6973|12222->6978|12354->7082|12383->7083|12415->7087|12621->7264|12651->7265|12682->7268|12791->7348|12821->7349|12853->7353|12926->7398|12955->7399|12986->7402|13124->7511|13154->7512|13186->7516|13245->7546|13275->7547|13305->7548|13361->7575|13391->7576|13422->7579|13451->7580|13482->7583|13528->7600|13558->7601|13590->7605|13750->7736|13780->7737|13813->7742|14017->7918|14046->7919|14077->7922|14106->7923|14138->7927|14167->7928|14199->7932|14416->8121|14445->8122|14475->8124|14504->8125|14547->8140
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|50->19|50->19|50->19|51->20|51->20|51->20|52->21|52->21|54->23|55->24|78->47|78->47|78->47|79->48|81->50|81->50|81->50|83->52|83->52|83->52|88->57|88->57|88->57|89->58|89->58|89->58|90->59|91->60|91->60|91->60|91->60|91->60|91->60|92->61|92->61|92->61|92->61|92->61|92->61|94->63|94->63|94->63|96->65|96->65|97->66|98->67|98->67|99->68|99->68|99->68|99->68|102->71|103->72|105->74|105->74|106->75|107->76|107->76|108->77|108->77|108->77|108->77|111->80|112->81|114->83|114->83|114->83|115->84|115->84|115->84|116->85|116->85|116->85|122->91|122->91|122->91|122->91|122->91|122->91|123->92|123->92|124->93|126->95|126->95|126->95|127->96|127->96|127->96|129->98|129->98|129->98|131->100|131->100|132->101|134->103|134->103|134->103|135->104|135->104|135->104|137->106|137->106|137->106|139->108|140->109|143->112|143->112|144->113|145->114|145->114|146->115|146->115|146->115|146->115|149->118|150->119|152->121|153->122|193->162|196->165|197->166|197->166|198->167|198->167|198->167|199->168|202->171|202->171|203->172|204->173|204->173|205->174|205->174|205->174|207->176|207->176|210->179|210->179|211->180|213->182|213->182|214->183|221->190|221->190|222->191|224->193|224->193|225->194|225->194|226->195|226->195|228->197|228->197|228->197|229->198|231->200|231->200|233->202|234->203|234->203|235->204|237->206|237->206|238->207|239->208|239->208|240->209|241->210|241->210|242->211|242->211|242->211|242->211|242->211|242->211|243->212|243->212|244->213|244->213|244->213|245->214|247->216|247->216|248->217|251->220|251->220|252->221|252->221|252->221|252->221|253->222|257->226|257->226|258->227|258->227|265->234
                  -- GENERATED --
              */
          