
package viewsMnuReportes.html

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

object ajustesEpListado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.AjustesEP],tables.BodegaEmpresa,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listAjustes: List[tables.AjustesEP], bodegaEmpresa: tables.BodegaEmpresa):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "HACER AJUSTES A EP/PROFORMAS A: "+bodegaEmpresa.nombre,"(CREAR/MODIFICAR/ELIMINAR)")),format.raw/*9.117*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-8 col-lg-8">
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
					        <th style="text-align:center;">FECHA</th>
					        <th style="text-align:center;">TIPO AJUSTE</th>
					        <th style="text-align:center;">APLICAR SOBRE</th>
					        <th style="text-align:center;">MONEDA</th>
					        <th style="text-align:center;">VALOR</th>
					        <th style="text-align:center;">CONCEPTO</th>
					        <th style="text-align:center;">NOTAS</th>
					        <TH style= "text-align: center;">ADJUNTAR<br>PDF</TH>
							<TH style= "text-align: center;">PDF<br>ADJUNTO</TH>
					        <th style="text-align:center;width:5%;">EDIT</th>
					        <th style="text-align:center;width:5%;">DEL</th>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*30.8*/for(lista1 <- listAjustes) yield /*30.34*/{_display_(Seq[Any](format.raw/*30.35*/("""
							"""),format.raw/*31.8*/("""<TR>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*33.37*/utilities/*33.46*/.Fechas.AAMMDD(lista1.fechaAjuste)),format.raw/*33.80*/("""</div>
									"""),_display_(/*34.11*/lista1/*34.17*/.fechaAjuste),format.raw/*34.29*/("""
								"""),format.raw/*35.9*/("""</td>
								<td style="text-align:center;">"""),_display_(/*36.41*/lista1/*36.47*/.tipoAjuste),format.raw/*36.58*/("""</td>
								<td style="text-align:center;">"""),_display_(/*37.41*/lista1/*37.47*/.tipoAjusteVenta),format.raw/*37.63*/("""</td>
								<td style="text-align:center;">"""),_display_(/*38.41*/lista1/*38.47*/.moneda),format.raw/*38.54*/("""</td>
								<td style="text-align:center;">"""),_display_(/*39.41*/lista1/*39.47*/.totalAjuste),format.raw/*39.59*/("""</td>
								<td style="text-align:center;">"""),_display_(/*40.41*/lista1/*40.47*/.concepto),format.raw/*40.56*/("""</td>
								<td style="text-align:center;">"""),_display_(/*41.41*/lista1/*41.47*/.observaciones),format.raw/*41.61*/("""</td>
								<td style="text-align:center;">
									<form id="form_"""),_display_(/*43.26*/lista1/*43.32*/.id),format.raw/*43.35*/(""""action="/ajusteGrabaPDF/" enctype="multipart/form-data" method="POST">
										<input type="hidden" name="id_ajuste" value=""""),_display_(/*44.57*/lista1/*44.63*/.id),format.raw/*44.66*/("""">
										<input type="hidden" name="id_bodega" value=""""),_display_(/*45.57*/bodegaEmpresa/*45.70*/.id),format.raw/*45.73*/("""">
										<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											"""),_display_(if(lista1.ajustePDF.equals("0"))/*47.45*/{_display_(Seq[Any](format.raw/*47.46*/("""
												"""),format.raw/*48.13*/("""<div id="txtBtn">Adjuntar</div>
											""")))}else/*49.17*/{_display_(Seq[Any](format.raw/*49.18*/("""
												"""),format.raw/*50.13*/("""<div id="txtBtn">Reemplazar</div>
											""")))}),format.raw/*51.13*/("""
					    					"""),format.raw/*52.15*/("""<input type="file" id="docAdjunto_"""),_display_(/*52.50*/lista1/*52.56*/.id),format.raw/*52.59*/("""" name="docAdjunto" 
												onchange="LimitAttach(this.form, this.form.docAdjunto.value, '"""),_display_(/*53.76*/lista1/*53.82*/.id),format.raw/*53.85*/("""'); 
												 	document.getElementById('mostrarmostrar').style.display='none';
													$('#form_"""),_display_(/*55.24*/lista1/*55.30*/.id),format.raw/*55.33*/("""').submit();">
										</span>
									</form>
								</td>
								<td style= "text-align: center;vertical-align: middle;">
									"""),_display_(if(lista1.ajustePDF.equals("0"))/*60.43*/{_display_(Seq[Any](format.raw/*60.44*/("""
										"""),format.raw/*61.11*/("""<div id="actualizarPDF"""),_display_(/*61.34*/lista1/*61.40*/.id),format.raw/*61.43*/("""">
											--
										</div>
									""")))}else/*64.15*/{_display_(Seq[Any](format.raw/*64.16*/("""
										"""),format.raw/*65.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*65.45*/lista1/*65.51*/.ajustePDF),format.raw/*65.61*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*68.11*/("""
								"""),format.raw/*69.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if( ! (lista1.id_proformaEstado > 0) )/*71.49*/{_display_(Seq[Any](format.raw/*71.50*/("""
										"""),format.raw/*72.11*/("""<a href="#" onclick="$('#form_id_modificar').val('"""),_display_(/*72.62*/lista1/*72.68*/.id),format.raw/*72.71*/("""'); document.getElementById('form_modificar').submit();">
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									""")))} else {null} ),format.raw/*75.11*/("""
								"""),format.raw/*76.9*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick= "ajustesEpEliminar('"""),_display_(/*78.52*/lista1/*78.58*/.id),format.raw/*78.61*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*83.9*/("""
					"""),format.raw/*84.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR NUEVO AJUSTE" class="btn btn-success btn-sm rounded-pill btn-block" onclick="document.getElementById('form_agregar').submit();">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>ADJUNTO</h5>
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
	
	<form id="form_agregar" method="post" action="/ajustesEpNuevo/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*122.49*/bodegaEmpresa/*122.62*/.id),format.raw/*122.65*/("""">
	</form>
	
	<form id="form_modificar" method="post" action="/ajustesEpModificar/">
		<input type="hidden" id="form_id_modificar" name="id_ajuste">
	</form>
	
	<form id="form_eliminar" method="post" action="/ajustesEpEliminar/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*130.49*/bodegaEmpresa/*130.62*/.id),format.raw/*130.65*/("""">
		<input type="hidden" id="form_id_ajuste" name="id_ajuste">
	</form>
""")))}),format.raw/*133.2*/("""


"""),format.raw/*136.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*137.31*/("""{"""),format.raw/*137.32*/("""
		  """),format.raw/*138.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*138.36*/("""{"""),format.raw/*138.37*/("""
		    	"""),format.raw/*139.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[25, 50, 100, 200, -1], [25, 50, 100, 200, "All"]],
		    	"order": [[ 0, "desc" ]],
		    	"language": """),format.raw/*142.20*/("""{"""),format.raw/*142.21*/("""
		        	"""),format.raw/*143.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*144.11*/("""}"""),format.raw/*144.12*/("""
		  """),format.raw/*145.5*/("""}"""),format.raw/*145.6*/(""" """),format.raw/*145.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*147.2*/("""}"""),format.raw/*147.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*149.36*/("""{"""),format.raw/*149.37*/("""
		  """),format.raw/*150.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*152.2*/("""}"""),format.raw/*152.3*/("""
	
	"""),format.raw/*154.2*/("""const ajustesEpEliminar = (id_ajuste) => """),format.raw/*154.43*/("""{"""),format.raw/*154.44*/("""
		"""),format.raw/*155.3*/("""alertify.confirm("Esta seguro de eliminar este ajuste", function (e) """),format.raw/*155.72*/("""{"""),format.raw/*155.73*/("""
			"""),format.raw/*156.4*/("""if (e) """),format.raw/*156.11*/("""{"""),format.raw/*156.12*/("""
				"""),format.raw/*157.5*/("""$("#form_id_ajuste").val(id_ajuste);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*159.4*/("""}"""),format.raw/*159.5*/("""
		"""),format.raw/*160.3*/("""}"""),format.raw/*160.4*/(""");
	"""),format.raw/*161.2*/("""}"""),format.raw/*161.3*/("""
	
	"""),format.raw/*163.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file, id_Ajuste) => """),format.raw/*164.49*/("""{"""),format.raw/*164.50*/("""
		"""),format.raw/*165.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*167.35*/("""{"""),format.raw/*167.36*/("""
			"""),format.raw/*168.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*169.3*/("""}"""),format.raw/*169.4*/("""
		"""),format.raw/*170.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*171.45*/("""{"""),format.raw/*171.46*/("""
			"""),format.raw/*172.4*/("""if (extArray[i] == extencion) """),format.raw/*172.34*/("""{"""),format.raw/*172.35*/(""" """),format.raw/*172.36*/("""allowSubmit = true; break; """),format.raw/*172.63*/("""}"""),format.raw/*172.64*/("""
		"""),format.raw/*173.3*/("""}"""),format.raw/*173.4*/("""
		"""),format.raw/*174.3*/("""if (allowSubmit) """),format.raw/*174.20*/("""{"""),format.raw/*174.21*/("""
			"""),format.raw/*175.4*/("""var file = $("#docAdjunto_"+id_Ajuste)[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*177.26*/("""{"""),format.raw/*177.27*/("""
				"""),format.raw/*178.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*181.4*/("""}"""),format.raw/*181.5*/("""
		"""),format.raw/*182.3*/("""}"""),format.raw/*182.4*/("""else"""),format.raw/*182.8*/("""{"""),format.raw/*182.9*/("""
			"""),format.raw/*183.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*187.3*/("""}"""),format.raw/*187.4*/("""
	"""),format.raw/*188.2*/("""}"""),format.raw/*188.3*/("""
	
	
	
"""),format.raw/*192.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listAjustes:List[tables.AjustesEP],bodegaEmpresa:tables.BodegaEmpresa): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listAjustes,bodegaEmpresa)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.AjustesEP],tables.BodegaEmpresa) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listAjustes,bodegaEmpresa) => apply(mapDiccionario,mapPermiso,userMnu,listAjustes,bodegaEmpresa)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/ajustesEpListado.scala.html
                  HASH: 2955123cdf46c34b2dc6e084426b8ea0e7efdc49
                  MATRIX: 1060->1|1324->172|1357->180|1373->188|1412->190|1440->193|1508->241|1536->243|1612->294|1746->407|1776->410|2777->1385|2819->1411|2858->1412|2893->1420|3001->1501|3019->1510|3074->1544|3118->1561|3133->1567|3166->1579|3202->1588|3275->1634|3290->1640|3322->1651|3395->1697|3410->1703|3447->1719|3520->1765|3535->1771|3563->1778|3636->1824|3651->1830|3684->1842|3757->1888|3772->1894|3802->1903|3875->1949|3890->1955|3925->1969|4023->2040|4038->2046|4062->2049|4217->2177|4232->2183|4256->2186|4342->2245|4364->2258|4388->2261|4540->2386|4579->2387|4620->2400|4687->2448|4726->2449|4767->2462|4844->2508|4887->2523|4949->2558|4964->2564|4988->2567|5111->2663|5126->2669|5150->2672|5283->2778|5298->2784|5322->2787|5520->2958|5559->2959|5598->2970|5648->2993|5663->2999|5687->3002|5754->3050|5793->3051|5832->3062|5893->3096|5908->3102|5939->3112|6060->3202|6096->3211|6217->3305|6256->3306|6295->3317|6373->3368|6388->3374|6412->3377|6597->3518|6633->3527|6757->3624|6772->3630|6796->3633|6933->3740|6966->3746|8464->5216|8487->5229|8512->5232|8819->5511|8842->5524|8867->5527|8972->5601|9003->5604|9094->5666|9124->5667|9157->5672|9217->5703|9247->5704|9283->5712|9459->5859|9489->5860|9530->5872|9637->5950|9667->5951|9700->5956|9729->5957|9758->5958|9859->6031|9888->6032|9957->6072|9987->6073|10020->6078|10230->6260|10259->6261|10291->6265|10361->6306|10391->6307|10422->6310|10520->6379|10550->6380|10582->6384|10618->6391|10648->6392|10681->6397|10804->6492|10833->6493|10864->6496|10893->6497|10925->6501|10954->6502|10986->6506|11188->6679|11218->6680|11249->6683|11358->6763|11388->6764|11420->6768|11493->6813|11522->6814|11553->6817|11691->6926|11721->6927|11753->6931|11812->6961|11842->6962|11872->6963|11928->6990|11958->6991|11989->6994|12018->6995|12049->6998|12095->7015|12125->7016|12157->7020|12313->7147|12343->7148|12376->7153|12580->7329|12609->7330|12640->7333|12669->7334|12701->7338|12730->7339|12762->7343|12979->7532|13008->7533|13038->7535|13067->7536|13102->7543
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|61->30|61->30|61->30|62->31|64->33|64->33|64->33|65->34|65->34|65->34|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|72->41|72->41|72->41|74->43|74->43|74->43|75->44|75->44|75->44|76->45|76->45|76->45|78->47|78->47|79->48|80->49|80->49|81->50|82->51|83->52|83->52|83->52|83->52|84->53|84->53|84->53|86->55|86->55|86->55|91->60|91->60|92->61|92->61|92->61|92->61|95->64|95->64|96->65|96->65|96->65|96->65|99->68|100->69|102->71|102->71|103->72|103->72|103->72|103->72|106->75|107->76|109->78|109->78|109->78|114->83|115->84|153->122|153->122|153->122|161->130|161->130|161->130|164->133|167->136|168->137|168->137|169->138|169->138|169->138|170->139|173->142|173->142|174->143|175->144|175->144|176->145|176->145|176->145|178->147|178->147|180->149|180->149|181->150|183->152|183->152|185->154|185->154|185->154|186->155|186->155|186->155|187->156|187->156|187->156|188->157|190->159|190->159|191->160|191->160|192->161|192->161|194->163|195->164|195->164|196->165|198->167|198->167|199->168|200->169|200->169|201->170|202->171|202->171|203->172|203->172|203->172|203->172|203->172|203->172|204->173|204->173|205->174|205->174|205->174|206->175|208->177|208->177|209->178|212->181|212->181|213->182|213->182|213->182|213->182|214->183|218->187|218->187|219->188|219->188|223->192
                  -- GENERATED --
              */
          