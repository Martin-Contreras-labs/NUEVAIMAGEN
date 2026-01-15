
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

object reportFacturaProyectoH extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template14[Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,Double,Double,Double,String,String,Long,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tabla: String, desdeDDMMAA: String, hastaDDMMAA: String, id_proyecto: String,
uf: Double, usd: Double, eur: Double, desdeAAMMDD: String, hastaAAMMDD: String, nroDec: Long, archivoPDF: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">

		"""),_display_(/*15.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","PROCESO DE EP/FACTURACION PROFORMA SIMPLE")),format.raw/*15.133*/("""

		"""),format.raw/*17.3*/("""<div class="noprint">
			<div class="row">
				<div class="col-1"></div>
				<div class="col-5"></div>
				<div class="col-6">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td>
								<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
									Exportar a Excel
								</button>
								<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
									Imprimir
								</button>
								<button type="button"  class="btn btn-sm btn-success"
								onclick="history.go(-1);return false;">
									Volver
								</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<form id="excel" class="formulario" method="post" action="/reportFacturaProyectoHExcel/">
			<input type="hidden" name="fechaDesde" value=""""),_display_(/*43.51*/desdeAAMMDD),format.raw/*43.62*/("""">
			<input type="hidden" name="fechaHasta" value=""""),_display_(/*44.51*/hastaAAMMDD),format.raw/*44.62*/("""">
			<input type="hidden" name="uf" value=""""),_display_(/*45.43*/uf),format.raw/*45.45*/("""">
			<input type="hidden" name="usd" value=""""),_display_(/*46.44*/usd),format.raw/*46.47*/("""">
			<input type="hidden" name="eur" value=""""),_display_(/*47.44*/eur),format.raw/*47.47*/("""">
		</form>

		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
							  		</div>
							  		<input type="text" class="form-control left"
										id="searchTermtablaPrincipal"
										onkeyup="doSearch2('tablaPrincipal');">
								</div>
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
					</table>
				</div>
				<h5>Período desde """),_display_(/*72.24*/desdeDDMMAA),format.raw/*72.35*/(""" """),format.raw/*72.36*/("""a """),_display_(/*72.39*/hastaDDMMAA),format.raw/*72.50*/("""</h5>
				<div class="table-responsive">
					"""),_display_(/*74.7*/Html(tabla)),format.raw/*74.18*/("""
				"""),format.raw/*75.5*/("""</div>
			</div>
		</div>
	</div>

	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>PROFORMA</h5>
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


""")))}),format.raw/*102.2*/("""


"""),format.raw/*105.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*107.31*/("""{"""),format.raw/*107.32*/("""

		 """),format.raw/*109.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*109.35*/("""{"""),format.raw/*109.36*/("""
		    	"""),format.raw/*110.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*115.20*/("""{"""),format.raw/*115.21*/("""
		        	"""),format.raw/*116.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*117.11*/("""}"""),format.raw/*117.12*/("""
		  """),format.raw/*118.5*/("""}"""),format.raw/*118.6*/(""" """),format.raw/*118.7*/(""");

		let cfi = 0;
		$(".cfi").each(function() """),format.raw/*121.29*/("""{"""),format.raw/*121.30*/("""
			"""),format.raw/*122.4*/("""let val = $(this).text().replace(/,/g,'');
			cfi += parseFloat(val);
		"""),format.raw/*124.3*/("""}"""),format.raw/*124.4*/(""");
		$("#cfi").text(formatStandar(cfi,""""),_display_(/*125.38*/nroDec),format.raw/*125.44*/(""""));
		
		let arr = 0;
		$(".arr").each(function() """),format.raw/*128.29*/("""{"""),format.raw/*128.30*/("""
			"""),format.raw/*129.4*/("""let val = $(this).text().replace(/,/g,'');
			arr += parseFloat(val);
		"""),format.raw/*131.3*/("""}"""),format.raw/*131.4*/(""");
		$("#arr").text(formatStandar(arr,""""),_display_(/*132.38*/nroDec),format.raw/*132.44*/(""""));
		
		let vta = 0;
		$(".vta").each(function() """),format.raw/*135.29*/("""{"""),format.raw/*135.30*/("""
			"""),format.raw/*136.4*/("""let val = $(this).text().replace(/,/g,'');
			vta += parseFloat(val);
		"""),format.raw/*138.3*/("""}"""),format.raw/*138.4*/(""");
		$("#vta").text(formatStandar(vta,""""),_display_(/*139.38*/nroDec),format.raw/*139.44*/(""""));

		let serv = 0;
		$(".serv").each(function() """),format.raw/*142.30*/("""{"""),format.raw/*142.31*/("""
			"""),format.raw/*143.4*/("""let val = $(this).text().replace(/,/g,'');
			serv += parseFloat(val);
		"""),format.raw/*145.3*/("""}"""),format.raw/*145.4*/(""");
		$("#serv").text(formatStandar(serv,""""),_display_(/*146.40*/nroDec),format.raw/*146.46*/(""""));
		
		let ajustArr = 0;
		$(".ajustArr").each(function() """),format.raw/*149.34*/("""{"""),format.raw/*149.35*/("""
			"""),format.raw/*150.4*/("""let val = $(this).text().replace(/,/g,'');
			ajustArr += parseFloat(val);
		"""),format.raw/*152.3*/("""}"""),format.raw/*152.4*/(""");
		$("#ajustArr").text(formatStandar(ajustArr,""""),_display_(/*153.48*/nroDec),format.raw/*153.54*/(""""));
		
		let ajustVta = 0;
		$(".ajustVta").each(function() """),format.raw/*156.34*/("""{"""),format.raw/*156.35*/("""
			"""),format.raw/*157.4*/("""let val = $(this).text().replace(/,/g,'');
			ajustVta += parseFloat(val);
		"""),format.raw/*159.3*/("""}"""),format.raw/*159.4*/(""");
		$("#ajustVta").text(formatStandar(ajustVta,""""),_display_(/*160.48*/nroDec),format.raw/*160.54*/(""""));

		let ajustServ = 0;
		$(".ajustServ").each(function() """),format.raw/*163.35*/("""{"""),format.raw/*163.36*/("""
			"""),format.raw/*164.4*/("""let val = $(this).text().replace(/,/g,'');
			ajustServ += parseFloat(val);
		"""),format.raw/*166.3*/("""}"""),format.raw/*166.4*/(""");
		$("#ajustServ").text(formatStandar(ajustServ,""""),_display_(/*167.50*/nroDec),format.raw/*167.56*/(""""));
		
		let granTotal = 0;
		$(".granTotal").each(function() """),format.raw/*170.35*/("""{"""),format.raw/*170.36*/("""
			"""),format.raw/*171.4*/("""let val = $(this).text().replace(/,/g,'');
			granTotal += parseFloat(val);
		"""),format.raw/*173.3*/("""}"""),format.raw/*173.4*/(""");
		$("#granTotal").text(formatStandar(granTotal,""""),_display_(/*174.50*/nroDec),format.raw/*174.56*/(""""));

		if (""""),_display_(/*176.9*/archivoPDF),format.raw/*176.19*/("""" !== "0") """),format.raw/*176.30*/("""{"""),format.raw/*176.31*/("""
			"""),format.raw/*177.4*/("""$("#rutaPDF").html(
					`<object data='/showPdf/"""),_display_(/*178.31*/archivoPDF),format.raw/*178.41*/("""' type='application/pdf' width='100%' height='720'></object>`
			);
			$("#muestraPDF").modal("show");
		"""),format.raw/*181.3*/("""}"""),format.raw/*181.4*/("""
			"""),format.raw/*182.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*183.2*/("""}"""),format.raw/*183.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tabla:String,desdeDDMMAA:String,hastaDDMMAA:String,id_proyecto:String,uf:Double,usd:Double,eur:Double,desdeAAMMDD:String,hastaAAMMDD:String,nroDec:Long,archivoPDF:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tabla,desdeDDMMAA,hastaDDMMAA,id_proyecto,uf,usd,eur,desdeAAMMDD,hastaAAMMDD,nroDec,archivoPDF)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,Double,Double,Double,String,String,Long,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tabla,desdeDDMMAA,hastaDDMMAA,id_proyecto,uf,usd,eur,desdeAAMMDD,hastaAAMMDD,nroDec,archivoPDF) => apply(mapDiccionario,mapPermiso,userMnu,tabla,desdeDDMMAA,hastaDDMMAA,id_proyecto,uf,usd,eur,desdeAAMMDD,hastaAAMMDD,nroDec,archivoPDF)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportFacturaProyectoH.scala.html
                  HASH: 803f290cafef1fdfb1ad6bac4c2721b840e6003d
                  MATRIX: 1098->1|1480->290|1512->297|1528->305|1567->307|1596->311|1664->359|1694->364|1749->399|1778->402|1821->424|1850->427|1894->450|1925->454|2003->506|2154->635|2185->639|3094->1521|3126->1532|3206->1585|3238->1596|3310->1641|3333->1643|3406->1689|3430->1692|3503->1738|3527->1741|4254->2441|4286->2452|4315->2453|4345->2456|4377->2467|4450->2514|4482->2525|4514->2530|5339->3324|5370->3327|5479->3407|5509->3408|5542->3413|5602->3444|5632->3445|5668->3453|5836->3592|5866->3593|5907->3605|6014->3683|6044->3684|6077->3689|6106->3690|6135->3691|6211->3738|6241->3739|6273->3743|6373->3815|6402->3816|6470->3856|6498->3862|6578->3913|6608->3914|6640->3918|6740->3990|6769->3991|6837->4031|6865->4037|6945->4088|6975->4089|7007->4093|7107->4165|7136->4166|7204->4206|7232->4212|7312->4263|7342->4264|7374->4268|7475->4341|7504->4342|7574->4384|7602->4390|7692->4451|7722->4452|7754->4456|7859->4533|7888->4534|7966->4584|7994->4590|8084->4651|8114->4652|8146->4656|8251->4733|8280->4734|8358->4784|8386->4790|8476->4851|8506->4852|8538->4856|8644->4934|8673->4935|8753->4987|8781->4993|8873->5056|8903->5057|8935->5061|9041->5139|9070->5140|9150->5192|9178->5198|9219->5212|9251->5222|9291->5233|9321->5234|9353->5238|9431->5288|9463->5298|9596->5403|9625->5404|9657->5408|9751->5474|9780->5475
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|46->15|46->15|48->17|74->43|74->43|75->44|75->44|76->45|76->45|77->46|77->46|78->47|78->47|103->72|103->72|103->72|103->72|103->72|105->74|105->74|106->75|133->102|136->105|138->107|138->107|140->109|140->109|140->109|141->110|146->115|146->115|147->116|148->117|148->117|149->118|149->118|149->118|152->121|152->121|153->122|155->124|155->124|156->125|156->125|159->128|159->128|160->129|162->131|162->131|163->132|163->132|166->135|166->135|167->136|169->138|169->138|170->139|170->139|173->142|173->142|174->143|176->145|176->145|177->146|177->146|180->149|180->149|181->150|183->152|183->152|184->153|184->153|187->156|187->156|188->157|190->159|190->159|191->160|191->160|194->163|194->163|195->164|197->166|197->166|198->167|198->167|201->170|201->170|202->171|204->173|204->173|205->174|205->174|207->176|207->176|207->176|207->176|208->177|209->178|209->178|212->181|212->181|213->182|214->183|214->183
                  -- GENERATED --
              */
          