
package viewsMnuMantencion.html

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

object mantWebListarReports1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],List[List[String]],String,String,Map[String,String],Long,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], listado: List[List[String]], desdeAAMMDD: String, hastaAAMMDD: String, mapDelete: Map[String,String], id_userOperMec: Long, id_equipo: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("")/*3.10*/ {_display_(Seq[Any](format.raw/*3.12*/("""

	"""),format.raw/*5.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>

	<form id="excel" class="formulario" method="post" action="/routes3/mantWebListarReports1Excel/">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*8.50*/desdeAAMMDD),format.raw/*8.61*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*9.50*/hastaAAMMDD),format.raw/*9.61*/("""">
		<input type="hidden" name="id_userOperMec" value=""""),_display_(/*10.54*/id_userOperMec),format.raw/*10.68*/("""">
	</form>
	
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*15.4*/barraTitulo(mapDiccionario, "LISTA DE MIS REPORT INGRESADOS", "")),format.raw/*15.69*/("""
		"""),format.raw/*16.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
				<div align="center">
					<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
						Exportar a Excel
					</button>
				</div>
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>ID</TH>
							<TH style="min-width:80px;">FECHA</TH>
							<TH>USER_MADA</TH>
							<TH>PERFIL</TH>
							<TH>OPERADOR<br>MECANICO</TH>
							<TH>TIPO<br>MANTENCION</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>REPORT</TH>
							<TH>DETALLE</TH>
							<TH>ANEXO</TH>
							<TH>FIRMA<br>OPER/MEC</TH>
							<TH>FIRMA<br>SUPERVISOR</TH>
							
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*45.8*/for(lista1 <- listado) yield /*45.30*/{_display_(Seq[Any](format.raw/*45.31*/("""
							"""),format.raw/*46.8*/("""<TR>
								<td  style="text-align:center;">"""),_display_(/*47.42*/lista1/*47.48*/.get(0)),format.raw/*47.55*/("""</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*49.37*/utilities/*49.46*/.Fechas.AAMMDD(lista1.get(1))),format.raw/*49.75*/("""</div>
									"""),_display_(/*50.11*/utilities/*50.20*/.Fechas.DDMMAA(lista1.get(1))),format.raw/*50.49*/("""
								"""),format.raw/*51.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*52.42*/lista1/*52.48*/.get(2)),format.raw/*52.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*53.40*/lista1/*53.46*/.get(3)),format.raw/*53.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*54.40*/lista1/*54.46*/.get(4)),format.raw/*54.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*55.40*/lista1/*55.46*/.get(5).toUpperCase()),format.raw/*55.67*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*56.40*/lista1/*56.46*/.get(6)),format.raw/*56.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*57.40*/lista1/*57.46*/.get(7)),format.raw/*57.53*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(8).equals("0"))/*59.40*/{_display_(Seq[Any](format.raw/*59.41*/("""
										"""),format.raw/*60.11*/("""--
									""")))}else/*61.15*/{_display_(Seq[Any](format.raw/*61.16*/("""
										"""),format.raw/*62.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*62.45*/lista1/*62.51*/.get(8)),format.raw/*62.58*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*65.11*/("""
								"""),format.raw/*66.9*/("""</td>
								<td style="text-align:center;">
									<form id="mantReportDetalle_"""),_display_(/*68.39*/lista1/*68.45*/.get(0)),format.raw/*68.52*/("""" action="/routes3/mantWebReportDetalle/" method="POST">
										<input type="hidden" name="id_mantTransacReport" value=""""),_display_(/*69.68*/lista1/*69.74*/.get(0)),format.raw/*69.81*/("""">
										<input type="hidden" name="desdeAAMMDD" value=""""),_display_(/*70.59*/desdeAAMMDD),format.raw/*70.70*/("""">
										<input type="hidden" name="hastaAAMMDD" value=""""),_display_(/*71.59*/hastaAAMMDD),format.raw/*71.70*/("""">
									</form>
									<a href="#" onclick='$("#mantReportDetalle_"""),_display_(/*73.54*/lista1/*73.60*/.get(0)),format.raw/*73.67*/("""").submit();'>
										<kbd style="background-color: #73C6B6">detalle</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(9).equals("0"))/*78.40*/{_display_(Seq[Any](format.raw/*78.41*/("""
										"""),format.raw/*79.11*/("""<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											<form id="mantWebReportGrabaDocAnexo_"""),_display_(/*80.50*/lista1/*80.56*/.get(0)),format.raw/*80.63*/("""" action="/routes3/mantWebReportGrabaDocAnexo/" enctype="multipart/form-data" method="POST">
												<input type="hidden" name="id_mantTransacReport" value=""""),_display_(/*81.70*/lista1/*81.76*/.get(0)),format.raw/*81.83*/("""">
												<input type="hidden" name="desdeAAMMDD" value=""""),_display_(/*82.61*/desdeAAMMDD),format.raw/*82.72*/("""">
												<input type="hidden" name="hastaAAMMDD" value=""""),_display_(/*83.61*/hastaAAMMDD),format.raw/*83.72*/("""">
												<input type="hidden" name="docAnexo" value=""""),_display_(/*84.58*/lista1/*84.64*/.get(9)),format.raw/*84.71*/("""">
													<div id="txtBtn">adjuntar</div>
													<input type="file" multiple id="docAdjunto" name="docAdjunto[]" 
													onchange="LimitAttach(this.form, this.form.docAdjunto.value, '"""),_display_(/*87.77*/lista1/*87.83*/.get(0)),format.raw/*87.90*/("""');">
											</form>
										</span>
									""")))}else/*90.15*/{_display_(Seq[Any](format.raw/*90.16*/("""
										"""),format.raw/*91.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*91.52*/lista1/*91.58*/.get(9)),format.raw/*91.65*/("""')">
											<kbd style="background-color: #7F8C8D">docum</kbd>
										</a>
									""")))}),format.raw/*94.11*/("""
								"""),format.raw/*95.9*/("""</td>
								<td style="text-align:center;">
									<a href="/routes3/mantWebFirmaOperador/"""),_display_(/*97.50*/lista1/*97.56*/.get(0)),format.raw/*97.63*/(""","""),_display_(/*97.65*/desdeAAMMDD),format.raw/*97.76*/(""","""),_display_(/*97.78*/hastaAAMMDD),format.raw/*97.89*/(""","""),_display_(/*97.91*/id_equipo),format.raw/*97.100*/("""">
										<kbd style="background-color: #F0B27A">FIR</kbd>
									</a>
									<img src="data:image/jpeg;base64" height="10px" />
								</td>
								<td style="text-align:center;">
									<a href="/routes3/mantWebFirmaAutorizador/"""),_display_(/*103.53*/lista1/*103.59*/.get(0)),format.raw/*103.66*/(""","""),_display_(/*103.68*/desdeAAMMDD),format.raw/*103.79*/(""","""),_display_(/*103.81*/hastaAAMMDD),format.raw/*103.92*/(""","""),_display_(/*103.94*/id_equipo),format.raw/*103.103*/("""">
										<kbd style="background-color: #F7DC6F">FIR</kbd>
									</a>
									<img src="data:image/jpeg;base64" height="10px" />
								</td>

								
							</TR>
			 			""")))}),format.raw/*111.9*/("""
					"""),format.raw/*112.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes3/mantWebInicio1get/"""),_display_(/*119.148*/id_userOperMec),format.raw/*119.162*/(""","""),_display_(/*119.164*/id_equipo),format.raw/*119.173*/("""';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>REPORT</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="rutaPDF"></div>
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
""")))}),format.raw/*151.2*/("""




"""),format.raw/*156.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*157.31*/("""{"""),format.raw/*157.32*/("""
		  """),format.raw/*158.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*158.36*/("""{"""),format.raw/*158.37*/("""
		    	"""),format.raw/*159.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*162.20*/("""{"""),format.raw/*162.21*/("""
		        	"""),format.raw/*163.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*164.11*/("""}"""),format.raw/*164.12*/("""
		  """),format.raw/*165.5*/("""}"""),format.raw/*165.6*/(""" """),format.raw/*165.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*167.2*/("""}"""),format.raw/*167.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*169.36*/("""{"""),format.raw/*169.37*/("""
		  """),format.raw/*170.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*172.2*/("""}"""),format.raw/*172.3*/("""
	
	"""),format.raw/*174.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*174.43*/("""{"""),format.raw/*174.44*/("""
		  """),format.raw/*175.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*177.2*/("""}"""),format.raw/*177.3*/("""
	
	"""),format.raw/*179.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file, id_mantTransacReport) => """),format.raw/*180.60*/("""{"""),format.raw/*180.61*/("""
		
		"""),format.raw/*182.3*/("""if (!file) return;
		
		const $inputArchivos = document.querySelector("#docAdjunto");
		const archivosParaSubir = $inputArchivos.files;
		let tamanio = 0;
		let allowSubmit = false;
		
		for(let i=0; i<archivosParaSubir.length; i++)"""),format.raw/*189.48*/("""{"""),format.raw/*189.49*/("""
			"""),format.raw/*190.4*/("""tamanio += archivosParaSubir[i].size;
			let nombre = archivosParaSubir[i].name;
			let extencion = nombre.substring(nombre.lastIndexOf(".")).toLowerCase();
			for (let j = 0; j < extArray.length; j++) """),format.raw/*193.46*/("""{"""),format.raw/*193.47*/("""
				"""),format.raw/*194.5*/("""if (extArray[j] == extencion) """),format.raw/*194.35*/("""{"""),format.raw/*194.36*/(""" 
					"""),format.raw/*195.6*/("""allowSubmit = true;
				"""),format.raw/*196.5*/("""}"""),format.raw/*196.6*/("""
			"""),format.raw/*197.4*/("""}"""),format.raw/*197.5*/("""
			"""),format.raw/*198.4*/("""if(allowSubmit && i < archivosParaSubir.length -1)"""),format.raw/*198.54*/("""{"""),format.raw/*198.55*/("""
				"""),format.raw/*199.5*/("""allowSubmit = false;
			"""),format.raw/*200.4*/("""}"""),format.raw/*200.5*/("""
		"""),format.raw/*201.3*/("""}"""),format.raw/*201.4*/("""
		
		"""),format.raw/*203.3*/("""if (allowSubmit) """),format.raw/*203.20*/("""{"""),format.raw/*203.21*/("""
			"""),format.raw/*204.4*/("""if(tamanio > 200000000)"""),format.raw/*204.27*/("""{"""),format.raw/*204.28*/("""
				"""),format.raw/*205.5*/("""alert("Se permite máximo subir 200 MB,"
				+" lo que se intenta subir pesa: "+Math.round(tamanio/10000)/100+" MB");
				form.docAdjunto.value="";
				return;
			"""),format.raw/*209.4*/("""}"""),format.raw/*209.5*/("""
		"""),format.raw/*210.3*/("""}"""),format.raw/*210.4*/("""else"""),format.raw/*210.8*/("""{"""),format.raw/*210.9*/("""
			"""),format.raw/*211.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione solo archivos con extenciones permitidas "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
			return;
		"""),format.raw/*216.3*/("""}"""),format.raw/*216.4*/("""
		"""),format.raw/*217.3*/("""document.getElementById('enCarga').style.display="block";
		$("#mantWebReportGrabaDocAnexo_"+id_mantTransacReport).submit();
	"""),format.raw/*219.2*/("""}"""),format.raw/*219.3*/("""
	
"""),format.raw/*221.1*/("""</script>


		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],listado:List[List[String]],desdeAAMMDD:String,hastaAAMMDD:String,mapDelete:Map[String,String],id_userOperMec:Long,id_equipo:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,listado,desdeAAMMDD,hastaAAMMDD,mapDelete,id_userOperMec,id_equipo)

  def f:((Map[String,String],Map[String,String],List[List[String]],String,String,Map[String,String],Long,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,listado,desdeAAMMDD,hastaAAMMDD,mapDelete,id_userOperMec,id_equipo) => apply(mapDiccionario,mapPermiso,listado,desdeAAMMDD,hastaAAMMDD,mapDelete,id_userOperMec,id_equipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantWebListarReports1.scala.html
                  HASH: 58bfc8052b081179f0d4e17167f7b2bf3f7ee18f
                  MATRIX: 1067->1|1370->211|1397->213|1413->221|1452->223|1481->226|1793->512|1824->523|1902->575|1933->586|2016->642|2051->656|2145->724|2231->789|2261->792|3177->1682|3215->1704|3254->1705|3289->1713|3362->1759|3377->1765|3405->1772|3531->1871|3549->1880|3599->1909|3643->1926|3661->1935|3711->1964|3747->1973|3821->2020|3836->2026|3864->2033|3936->2078|3951->2084|3979->2091|4051->2136|4066->2142|4094->2149|4166->2194|4181->2200|4223->2221|4295->2266|4310->2272|4338->2279|4410->2324|4425->2330|4453->2337|4566->2423|4605->2424|4644->2435|4680->2452|4719->2453|4758->2464|4819->2498|4834->2504|4862->2511|4983->2601|5019->2610|5130->2694|5145->2700|5173->2707|5324->2831|5339->2837|5367->2844|5455->2905|5487->2916|5575->2977|5607->2988|5707->3061|5722->3067|5750->3074|5963->3260|6002->3261|6041->3272|6185->3389|6200->3395|6228->3402|6417->3564|6432->3570|6460->3577|6550->3640|6582->3651|6672->3714|6704->3725|6791->3785|6806->3791|6834->3798|7063->4000|7078->4006|7106->4013|7182->4070|7221->4071|7260->4082|7328->4123|7343->4129|7371->4136|7494->4228|7530->4237|7652->4332|7667->4338|7695->4345|7724->4347|7756->4358|7785->4360|7817->4371|7846->4373|7877->4382|8147->4624|8163->4630|8192->4637|8222->4639|8255->4650|8285->4652|8318->4663|8348->4665|8380->4674|8592->4855|8626->4861|8969->5175|9006->5189|9037->5191|9069->5200|10099->6199|10132->6204|10223->6266|10253->6267|10286->6272|10346->6303|10376->6304|10412->6312|10599->6470|10629->6471|10670->6483|10777->6561|10807->6562|10840->6567|10869->6568|10898->6569|10999->6642|11028->6643|11097->6683|11127->6684|11160->6689|11370->6871|11399->6872|11431->6876|11501->6917|11531->6918|11564->6923|11696->7027|11725->7028|11757->7032|11970->7216|12000->7217|12034->7223|12295->7455|12325->7456|12357->7460|12588->7662|12618->7663|12651->7668|12710->7698|12740->7699|12775->7706|12827->7730|12856->7731|12888->7735|12917->7736|12949->7740|13028->7790|13058->7791|13091->7796|13143->7820|13172->7821|13203->7824|13232->7825|13266->7831|13312->7848|13342->7849|13374->7853|13426->7876|13456->7877|13489->7882|13679->8044|13708->8045|13739->8048|13768->8049|13800->8053|13829->8054|13861->8058|14117->8286|14146->8287|14177->8290|14331->8416|14360->8417|14391->8420
                  LINES: 28->1|33->2|34->3|34->3|34->3|36->5|39->8|39->8|40->9|40->9|41->10|41->10|46->15|46->15|47->16|76->45|76->45|76->45|77->46|78->47|78->47|78->47|80->49|80->49|80->49|81->50|81->50|81->50|82->51|83->52|83->52|83->52|84->53|84->53|84->53|85->54|85->54|85->54|86->55|86->55|86->55|87->56|87->56|87->56|88->57|88->57|88->57|90->59|90->59|91->60|92->61|92->61|93->62|93->62|93->62|93->62|96->65|97->66|99->68|99->68|99->68|100->69|100->69|100->69|101->70|101->70|102->71|102->71|104->73|104->73|104->73|109->78|109->78|110->79|111->80|111->80|111->80|112->81|112->81|112->81|113->82|113->82|114->83|114->83|115->84|115->84|115->84|118->87|118->87|118->87|121->90|121->90|122->91|122->91|122->91|122->91|125->94|126->95|128->97|128->97|128->97|128->97|128->97|128->97|128->97|128->97|128->97|134->103|134->103|134->103|134->103|134->103|134->103|134->103|134->103|134->103|142->111|143->112|150->119|150->119|150->119|150->119|182->151|187->156|188->157|188->157|189->158|189->158|189->158|190->159|193->162|193->162|194->163|195->164|195->164|196->165|196->165|196->165|198->167|198->167|200->169|200->169|201->170|203->172|203->172|205->174|205->174|205->174|206->175|208->177|208->177|210->179|211->180|211->180|213->182|220->189|220->189|221->190|224->193|224->193|225->194|225->194|225->194|226->195|227->196|227->196|228->197|228->197|229->198|229->198|229->198|230->199|231->200|231->200|232->201|232->201|234->203|234->203|234->203|235->204|235->204|235->204|236->205|240->209|240->209|241->210|241->210|241->210|241->210|242->211|247->216|247->216|248->217|250->219|250->219|252->221
                  -- GENERATED --
              */
          