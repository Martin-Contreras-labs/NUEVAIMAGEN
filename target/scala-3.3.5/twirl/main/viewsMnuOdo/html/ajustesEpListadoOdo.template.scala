
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

object ajustesEpListadoOdo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.AjustesEpOdo],tables.BodegaEmpresa,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listAjustes: List[tables.AjustesEpOdo], bodegaEmpresa: tables.BodegaEmpresa):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "HACER AJUSTES EP/PROFORMAS DE SERVICIOS A: "+bodegaEmpresa.nombre,"(CREAR/MODIFICAR/ELIMINAR)")),format.raw/*9.128*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-8 col-lg-8">
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
					        <th style="text-align:center;">FECHA</th>
					        <th style="text-align:center;">TIPO AJUSTE</th>
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
						"""),_display_(/*29.8*/for(lista1 <- listAjustes) yield /*29.34*/{_display_(Seq[Any](format.raw/*29.35*/("""
							"""),format.raw/*30.8*/("""<TR>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*32.37*/utilities/*32.46*/.Fechas.AAMMDD(lista1.fechaAjuste)),format.raw/*32.80*/("""</div>
									"""),_display_(/*33.11*/lista1/*33.17*/.fechaAjuste),format.raw/*33.29*/("""
								"""),format.raw/*34.9*/("""</td>
								<td style="text-align:center;">"""),_display_(/*35.41*/lista1/*35.47*/.tipoAjuste),format.raw/*35.58*/("""</td>
								<td style="text-align:center;">"""),_display_(/*36.41*/lista1/*36.47*/.moneda),format.raw/*36.54*/("""</td>
								<td style="text-align:center;">"""),_display_(/*37.41*/lista1/*37.47*/.totalAjuste),format.raw/*37.59*/("""</td>
								<td style="text-align:center;">"""),_display_(/*38.41*/lista1/*38.47*/.concepto),format.raw/*38.56*/("""</td>
								<td style="text-align:center;">"""),_display_(/*39.41*/lista1/*39.47*/.observaciones),format.raw/*39.61*/("""</td>
								<td style="text-align:center;">
									<form id="form_"""),_display_(/*41.26*/lista1/*41.32*/.id),format.raw/*41.35*/(""""action="/ajusteGrabaPDFodo/" enctype="multipart/form-data" method="POST">
										<input type="hidden" name="id_ajuste" value=""""),_display_(/*42.57*/lista1/*42.63*/.id),format.raw/*42.66*/("""">
										<input type="hidden" name="id_bodega" value=""""),_display_(/*43.57*/bodegaEmpresa/*43.70*/.id),format.raw/*43.73*/("""">
										<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											"""),_display_(if(lista1.ajustePDF.equals("0"))/*45.45*/{_display_(Seq[Any](format.raw/*45.46*/("""
												"""),format.raw/*46.13*/("""<div id="txtBtn">Adjuntar</div>
											""")))}else/*47.17*/{_display_(Seq[Any](format.raw/*47.18*/("""
												"""),format.raw/*48.13*/("""<div id="txtBtn">Reemplazar</div>
											""")))}),format.raw/*49.13*/("""
					    					"""),format.raw/*50.15*/("""<input type="file" id="docAdjunto_"""),_display_(/*50.50*/lista1/*50.56*/.id),format.raw/*50.59*/("""" name="docAdjunto" 
												onchange="LimitAttach(this.form, this.form.docAdjunto.value, '"""),_display_(/*51.76*/lista1/*51.82*/.id),format.raw/*51.85*/("""'); 
												 	document.getElementById('mostrarmostrar').style.display='none';
													$('#form_"""),_display_(/*53.24*/lista1/*53.30*/.id),format.raw/*53.33*/("""').submit();">
										</span>
									</form>
								</td>
								<td style= "text-align: center;vertical-align: middle;">
									"""),_display_(if(lista1.ajustePDF.equals("0"))/*58.43*/{_display_(Seq[Any](format.raw/*58.44*/("""
										"""),format.raw/*59.11*/("""<div id="actualizarPDF"""),_display_(/*59.34*/lista1/*59.40*/.id),format.raw/*59.43*/("""">
											--
										</div>
									""")))}else/*62.15*/{_display_(Seq[Any](format.raw/*62.16*/("""
										"""),format.raw/*63.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*63.45*/lista1/*63.51*/.ajustePDF),format.raw/*63.61*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*66.11*/("""
								"""),format.raw/*67.9*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick="$('#form_id_modificar').val('"""),_display_(/*69.61*/lista1/*69.67*/.id),format.raw/*69.70*/("""'); document.getElementById('form_modificar').submit();">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<a href="#" onclick= "ajustesEpEliminar('"""),_display_(/*74.52*/lista1/*74.58*/.id),format.raw/*74.61*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*79.9*/("""
					"""),format.raw/*80.6*/("""</tbody>
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
	
	<form id="form_agregar" method="post" action="/ajustesEpNuevoOdo/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*118.49*/bodegaEmpresa/*118.62*/.id),format.raw/*118.65*/("""">
	</form>
	
	<form id="form_modificar" method="post" action="/ajustesEpModificarOdo/">
		<input type="hidden" id="form_id_modificar" name="id_ajuste">
	</form>
	
	<form id="form_eliminar" method="post" action="/ajustesEpEliminarOdo/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*126.49*/bodegaEmpresa/*126.62*/.id),format.raw/*126.65*/("""">
		<input type="hidden" id="form_id_ajuste" name="id_ajuste">
	</form>
""")))}),format.raw/*129.2*/("""


"""),format.raw/*132.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*133.31*/("""{"""),format.raw/*133.32*/("""
		  """),format.raw/*134.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*134.36*/("""{"""),format.raw/*134.37*/("""
		    	"""),format.raw/*135.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[25, 50, 100, 200, -1], [25, 50, 100, 200, "All"]],
		    	"order": [[ 0, "desc" ]],
		    	"language": """),format.raw/*138.20*/("""{"""),format.raw/*138.21*/("""
		        	"""),format.raw/*139.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*140.11*/("""}"""),format.raw/*140.12*/("""
		  """),format.raw/*141.5*/("""}"""),format.raw/*141.6*/(""" """),format.raw/*141.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*143.2*/("""}"""),format.raw/*143.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*145.36*/("""{"""),format.raw/*145.37*/("""
		  """),format.raw/*146.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*148.2*/("""}"""),format.raw/*148.3*/("""
	
	"""),format.raw/*150.2*/("""const ajustesEpEliminar = (id_ajuste) => """),format.raw/*150.43*/("""{"""),format.raw/*150.44*/("""
		"""),format.raw/*151.3*/("""alertify.confirm("Esta seguro de eliminar este ajuste", function (e) """),format.raw/*151.72*/("""{"""),format.raw/*151.73*/("""
			"""),format.raw/*152.4*/("""if (e) """),format.raw/*152.11*/("""{"""),format.raw/*152.12*/("""
				"""),format.raw/*153.5*/("""$("#form_id_ajuste").val(id_ajuste);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*155.4*/("""}"""),format.raw/*155.5*/("""
		"""),format.raw/*156.3*/("""}"""),format.raw/*156.4*/(""");
	"""),format.raw/*157.2*/("""}"""),format.raw/*157.3*/("""
	
	"""),format.raw/*159.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file, id_Ajuste) => """),format.raw/*160.49*/("""{"""),format.raw/*160.50*/("""
		"""),format.raw/*161.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*163.35*/("""{"""),format.raw/*163.36*/("""
			"""),format.raw/*164.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*165.3*/("""}"""),format.raw/*165.4*/("""
		"""),format.raw/*166.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*167.45*/("""{"""),format.raw/*167.46*/("""
			"""),format.raw/*168.4*/("""if (extArray[i] == extencion) """),format.raw/*168.34*/("""{"""),format.raw/*168.35*/(""" """),format.raw/*168.36*/("""allowSubmit = true; break; """),format.raw/*168.63*/("""}"""),format.raw/*168.64*/("""
		"""),format.raw/*169.3*/("""}"""),format.raw/*169.4*/("""
		"""),format.raw/*170.3*/("""if (allowSubmit) """),format.raw/*170.20*/("""{"""),format.raw/*170.21*/("""
			"""),format.raw/*171.4*/("""var file = $("#docAdjunto_"+id_Ajuste)[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*173.26*/("""{"""),format.raw/*173.27*/("""
				"""),format.raw/*174.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*177.4*/("""}"""),format.raw/*177.5*/("""
		"""),format.raw/*178.3*/("""}"""),format.raw/*178.4*/("""else"""),format.raw/*178.8*/("""{"""),format.raw/*178.9*/("""
			"""),format.raw/*179.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*183.3*/("""}"""),format.raw/*183.4*/("""
	"""),format.raw/*184.2*/("""}"""),format.raw/*184.3*/("""
	
	
	
"""),format.raw/*188.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listAjustes:List[tables.AjustesEpOdo],bodegaEmpresa:tables.BodegaEmpresa): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listAjustes,bodegaEmpresa)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.AjustesEpOdo],tables.BodegaEmpresa) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listAjustes,bodegaEmpresa) => apply(mapDiccionario,mapPermiso,userMnu,listAjustes,bodegaEmpresa)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/ajustesEpListadoOdo.scala.html
                  HASH: d4ac0b8d684d8803c4375d09bf18ef0392be7c0b
                  MATRIX: 1061->1|1328->175|1361->183|1377->191|1416->193|1444->196|1512->244|1540->246|1616->297|1761->421|1791->424|2729->1336|2771->1362|2810->1363|2845->1371|2953->1452|2971->1461|3026->1495|3070->1512|3085->1518|3118->1530|3154->1539|3227->1585|3242->1591|3274->1602|3347->1648|3362->1654|3390->1661|3463->1707|3478->1713|3511->1725|3584->1771|3599->1777|3629->1786|3702->1832|3717->1838|3752->1852|3850->1923|3865->1929|3889->1932|4047->2063|4062->2069|4086->2072|4172->2131|4194->2144|4218->2147|4370->2272|4409->2273|4450->2286|4517->2334|4556->2335|4597->2348|4674->2394|4717->2409|4779->2444|4794->2450|4818->2453|4941->2549|4956->2555|4980->2558|5113->2664|5128->2670|5152->2673|5350->2844|5389->2845|5428->2856|5478->2879|5493->2885|5517->2888|5584->2936|5623->2937|5662->2948|5723->2982|5738->2988|5769->2998|5890->3088|5926->3097|6059->3203|6074->3209|6098->3212|6359->3446|6374->3452|6398->3455|6535->3562|6568->3568|8069->5041|8092->5054|8117->5057|8430->5342|8453->5355|8478->5358|8583->5432|8614->5435|8705->5497|8735->5498|8768->5503|8828->5534|8858->5535|8894->5543|9070->5690|9100->5691|9141->5703|9248->5781|9278->5782|9311->5787|9340->5788|9369->5789|9470->5862|9499->5863|9568->5903|9598->5904|9631->5909|9841->6091|9870->6092|9902->6096|9972->6137|10002->6138|10033->6141|10131->6210|10161->6211|10193->6215|10229->6222|10259->6223|10292->6228|10415->6323|10444->6324|10475->6327|10504->6328|10536->6332|10565->6333|10597->6337|10799->6510|10829->6511|10860->6514|10969->6594|10999->6595|11031->6599|11104->6644|11133->6645|11164->6648|11302->6757|11332->6758|11364->6762|11423->6792|11453->6793|11483->6794|11539->6821|11569->6822|11600->6825|11629->6826|11660->6829|11706->6846|11736->6847|11768->6851|11924->6978|11954->6979|11987->6984|12191->7160|12220->7161|12251->7164|12280->7165|12312->7169|12341->7170|12373->7174|12590->7363|12619->7364|12649->7366|12678->7367|12713->7374
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|60->29|60->29|60->29|61->30|63->32|63->32|63->32|64->33|64->33|64->33|65->34|66->35|66->35|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|74->43|76->45|76->45|77->46|78->47|78->47|79->48|80->49|81->50|81->50|81->50|81->50|82->51|82->51|82->51|84->53|84->53|84->53|89->58|89->58|90->59|90->59|90->59|90->59|93->62|93->62|94->63|94->63|94->63|94->63|97->66|98->67|100->69|100->69|100->69|105->74|105->74|105->74|110->79|111->80|149->118|149->118|149->118|157->126|157->126|157->126|160->129|163->132|164->133|164->133|165->134|165->134|165->134|166->135|169->138|169->138|170->139|171->140|171->140|172->141|172->141|172->141|174->143|174->143|176->145|176->145|177->146|179->148|179->148|181->150|181->150|181->150|182->151|182->151|182->151|183->152|183->152|183->152|184->153|186->155|186->155|187->156|187->156|188->157|188->157|190->159|191->160|191->160|192->161|194->163|194->163|195->164|196->165|196->165|197->166|198->167|198->167|199->168|199->168|199->168|199->168|199->168|199->168|200->169|200->169|201->170|201->170|201->170|202->171|204->173|204->173|205->174|208->177|208->177|209->178|209->178|209->178|209->178|210->179|214->183|214->183|215->184|215->184|219->188
                  -- GENERATED --
              */
          