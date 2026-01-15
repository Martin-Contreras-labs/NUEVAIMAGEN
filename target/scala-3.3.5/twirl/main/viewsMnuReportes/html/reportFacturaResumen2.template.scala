
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

object reportFacturaResumen2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template15[Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,Double,Double,Double,String,String,Long,String,Map[String,List[Double]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tabla: String, desdeDDMMAA: String, hastaDDMMAA: String, id_proyecto: String,
uf: Double, usd: Double, eur: Double, desdeAAMMDD: String, hastaAAMMDD: String, nroDec: Long, archivoPDF: String,
mapResumen: Map[String,List[Double]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.1*/("""    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

	"""),_display_(/*8.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.51*/("""

	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">

		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "RESUMEN DE EP/FACTURACION PROFORMA (POR COMERCIAL)","(SOLO CLIENTES)")),format.raw/*13.103*/("""

		"""),format.raw/*15.3*/("""<div class="noprint">
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

		<form id="excel" class="formulario" method="post" action="/reportFacturaResumenExcel2/">
			<input type="hidden" name="fechaDesde" value=""""),_display_(/*41.51*/desdeAAMMDD),format.raw/*41.62*/("""">
			<input type="hidden" name="fechaHasta" value=""""),_display_(/*42.51*/hastaAAMMDD),format.raw/*42.62*/("""">
			<input type="hidden" name="uf" value=""""),_display_(/*43.43*/uf),format.raw/*43.45*/("""">
			<input type="hidden" name="usd" value=""""),_display_(/*44.44*/usd),format.raw/*44.47*/("""">
			<input type="hidden" name="eur" value=""""),_display_(/*45.44*/eur),format.raw/*45.47*/("""">
		</form>

		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<h5>Período desde """),_display_(/*50.24*/desdeDDMMAA),format.raw/*50.35*/(""" """),format.raw/*50.36*/("""a """),_display_(/*50.39*/hastaDDMMAA),format.raw/*50.50*/("""</h5>
				<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td width="25%">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">BUSCAR</span>
									</div>
									<input type="text" class="form-control left"
									id="searchTermtablaResumen"
									onkeyup="doSearch2('tablaResumen');
									$('#searchTermtablaResumen').attr('id','searchTermtablaPrincipal');
									doSearch2('tablaPrincipal');
									$('#searchTermtablaPrincipal').attr('id','searchTermtablaResumen');">
								</div>
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
					</table>
				</div>
				<div class="col-xs-9 col-sm-8 col-md-7 col-lg-6">
					<h4>RESUMEN:</h4>
					<div class="table-responsive">
						<table id="tablaResumen" class="table table-sm table-hover table-bordered table-condensed table-fluid">
							<thead style="background-color: #eeeeee">
								<tr>
									<TH style= "text-align: center;vertical-align:top;">COMERCIAL</TH>
									<TH style= "text-align:center;vertical-align:top;">CFI (en """),_display_(/*81.70*/mapDiccionario/*81.84*/.get("PESOS")),format.raw/*81.97*/(""")</TH>
									<TH style= "text-align:center;vertical-align:top;">SubTotal<br>"""),_display_(/*82.74*/mapDiccionario/*82.88*/.get("ARRIENDO")),format.raw/*82.104*/("""</TH>
									<TH style= "text-align:center;vertical-align:top;">SubTotal<br>VENTA</TH>
									<TH style= "text-align:center;vertical-align:top;">Ajustes<BR>("""),_display_(/*84.74*/mapDiccionario/*84.88*/.get("ARRIENDO")),format.raw/*84.104*/(""")</TH>
									<TH style= "text-align:center;vertical-align:top;">Ajustes<BR>(VENTA)</TH>
									<TH style= "text-align:center;vertical-align:top;">TOTAL<BR>(en """),_display_(/*86.75*/mapDiccionario/*86.89*/.get("PESOS")),format.raw/*86.102*/(""")</TH>
								</tr>
							</thead>
							<tbody>
								"""),_display_(/*90.10*/for(key <- mapResumen.keys) yield /*90.37*/ {_display_(Seq[Any](format.raw/*90.39*/("""
									"""),format.raw/*91.10*/("""<tr>
										<td>"""),_display_(/*92.16*/key),format.raw/*92.19*/("""</td>
										"""),_display_(/*93.12*/for(i <- 0 until mapResumen(key).size) yield /*93.50*/ {_display_(Seq[Any](format.raw/*93.52*/("""
											"""),format.raw/*94.12*/("""<td class="resumen"""),_display_(/*94.31*/i),format.raw/*94.32*/("""" style="text-align:right;">"""),_display_(/*94.61*/mapResumen(key)/*94.76*/(i)),format.raw/*94.79*/("""</td>
										""")))}),format.raw/*95.12*/("""
									"""),format.raw/*96.10*/("""</tr>
								""")))}),format.raw/*97.10*/("""
							"""),format.raw/*98.8*/("""</tbody>
							<tfoot>
								<tr>
									<TH style="text-align: left;">TOTALES</TH>
									<TH id="resumen0" style="text-align:right;"></TH>
									<TH id="resumen1" style="text-align:right;"></TH>
									<TH id="resumen2" style="text-align:right;"></TH>
									<TH id="resumen3" style="text-align:right;"></TH>
									<TH id="resumen4" style="text-align:right;"></TH>
									<TH id="resumen5" style="text-align:right;"></TH>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
<br>

				<h4>DETALLE:</h4>
				<div class="table-responsive">
					"""),_display_(/*117.7*/Html(tabla)),format.raw/*117.18*/("""
				"""),format.raw/*118.5*/("""</div>
			</div>
		</div>
	</div>




""")))}),format.raw/*126.2*/("""


"""),format.raw/*129.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*131.31*/("""{"""),format.raw/*131.32*/("""

		 """),format.raw/*133.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*133.35*/("""{"""),format.raw/*133.36*/("""
		    	"""),format.raw/*134.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*139.20*/("""{"""),format.raw/*139.21*/("""
		        	"""),format.raw/*140.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*141.11*/("""}"""),format.raw/*141.12*/("""
		  """),format.raw/*142.5*/("""}"""),format.raw/*142.6*/(""" """),format.raw/*142.7*/(""");

		$('#tablaResumen').DataTable("""),format.raw/*144.32*/("""{"""),format.raw/*144.33*/("""
			"""),format.raw/*145.4*/(""""fixedHeader": true,
			"paging": false,
			"info": false,
			"searching": false,
			"order": [[ 0, "asc" ]],
			"language": """),format.raw/*150.16*/("""{"""),format.raw/*150.17*/("""
				"""),format.raw/*151.5*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			"""),format.raw/*152.4*/("""}"""),format.raw/*152.5*/("""
		"""),format.raw/*153.3*/("""}"""),format.raw/*153.4*/(""" """),format.raw/*153.5*/(""");

		for(i=0; i<6; i++)"""),format.raw/*155.21*/("""{"""),format.raw/*155.22*/("""
			"""),format.raw/*156.4*/("""let totVal = 0;
			$(".resumen"+i).each(function() """),format.raw/*157.36*/("""{"""),format.raw/*157.37*/("""
				"""),format.raw/*158.5*/("""let val = $(this).text().replace(/,/g,'');
				totVal += parseFloat(val);
				$(this).text(formatStandar(val,""""),_display_(/*160.38*/nroDec),format.raw/*160.44*/(""""));
			"""),format.raw/*161.4*/("""}"""),format.raw/*161.5*/(""");
			$("#resumen"+i).text(formatStandar(totVal,""""),_display_(/*162.48*/nroDec),format.raw/*162.54*/(""""));
		"""),format.raw/*163.3*/("""}"""),format.raw/*163.4*/("""

		"""),format.raw/*165.3*/("""let cfi = 0;
		$(".cfi").each(function() """),format.raw/*166.29*/("""{"""),format.raw/*166.30*/("""
			"""),format.raw/*167.4*/("""let val = $(this).text().replace(/,/g,'');
			cfi += parseFloat(val);
		"""),format.raw/*169.3*/("""}"""),format.raw/*169.4*/(""");
		$("#cfi").text(formatStandar(cfi,""""),_display_(/*170.38*/nroDec),format.raw/*170.44*/(""""));
		
		let arr = 0;
		$(".arr").each(function() """),format.raw/*173.29*/("""{"""),format.raw/*173.30*/("""
			"""),format.raw/*174.4*/("""let val = $(this).text().replace(/,/g,'');
			arr += parseFloat(val);
		"""),format.raw/*176.3*/("""}"""),format.raw/*176.4*/(""");
		$("#arr").text(formatStandar(arr,""""),_display_(/*177.38*/nroDec),format.raw/*177.44*/(""""));
		
		let vta = 0;
		$(".vta").each(function() """),format.raw/*180.29*/("""{"""),format.raw/*180.30*/("""
			"""),format.raw/*181.4*/("""let val = $(this).text().replace(/,/g,'');
			vta += parseFloat(val);
		"""),format.raw/*183.3*/("""}"""),format.raw/*183.4*/(""");
		$("#vta").text(formatStandar(vta,""""),_display_(/*184.38*/nroDec),format.raw/*184.44*/(""""));
		
		let ajustArr = 0;
		$(".ajustArr").each(function() """),format.raw/*187.34*/("""{"""),format.raw/*187.35*/("""
			"""),format.raw/*188.4*/("""let val = $(this).text().replace(/,/g,'');
			ajustArr += parseFloat(val);
		"""),format.raw/*190.3*/("""}"""),format.raw/*190.4*/(""");
		$("#ajustArr").text(formatStandar(ajustArr,""""),_display_(/*191.48*/nroDec),format.raw/*191.54*/(""""));
		
		let ajustVta = 0;
		$(".ajustVta").each(function() """),format.raw/*194.34*/("""{"""),format.raw/*194.35*/("""
			"""),format.raw/*195.4*/("""let val = $(this).text().replace(/,/g,'');
			ajustVta += parseFloat(val);
		"""),format.raw/*197.3*/("""}"""),format.raw/*197.4*/(""");
		$("#ajustVta").text(formatStandar(ajustVta,""""),_display_(/*198.48*/nroDec),format.raw/*198.54*/(""""));
		
		let granTotal = 0;
		$(".granTotal").each(function() """),format.raw/*201.35*/("""{"""),format.raw/*201.36*/("""
			"""),format.raw/*202.4*/("""let val = $(this).text().replace(/,/g,'');
			granTotal += parseFloat(val);
		"""),format.raw/*204.3*/("""}"""),format.raw/*204.4*/(""");
		$("#granTotal").text(formatStandar(granTotal,""""),_display_(/*205.50*/nroDec),format.raw/*205.56*/(""""));


			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*209.2*/("""}"""),format.raw/*209.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tabla:String,desdeDDMMAA:String,hastaDDMMAA:String,id_proyecto:String,uf:Double,usd:Double,eur:Double,desdeAAMMDD:String,hastaAAMMDD:String,nroDec:Long,archivoPDF:String,mapResumen:Map[String,List[Double]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tabla,desdeDDMMAA,hastaDDMMAA,id_proyecto,uf,usd,eur,desdeAAMMDD,hastaAAMMDD,nroDec,archivoPDF,mapResumen)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,Double,Double,Double,String,String,Long,String,Map[String,List[Double]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tabla,desdeDDMMAA,hastaDDMMAA,id_proyecto,uf,usd,eur,desdeAAMMDD,hastaAAMMDD,nroDec,archivoPDF,mapResumen) => apply(mapDiccionario,mapPermiso,userMnu,tabla,desdeDDMMAA,hastaDDMMAA,id_proyecto,uf,usd,eur,desdeAAMMDD,hastaAAMMDD,nroDec,archivoPDF,mapResumen)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportFacturaResumen2.scala.html
                  HASH: 2f8aae5d2534ddd4cc8072ef42275ba16bd62d24
                  MATRIX: 1122->1|1542->328|1574->335|1590->343|1629->345|1658->349|1726->397|1758->402|1836->454|1957->553|1988->557|2896->1438|2928->1449|3008->1502|3040->1513|3112->1558|3135->1560|3208->1606|3232->1609|3305->1655|3329->1658|3497->1799|3529->1810|3558->1811|3588->1814|3620->1825|4819->2997|4842->3011|4876->3024|4983->3104|5006->3118|5044->3134|5233->3296|5256->3310|5294->3326|5486->3491|5509->3505|5544->3518|5632->3579|5675->3606|5715->3608|5753->3618|5800->3638|5824->3641|5868->3658|5922->3696|5962->3698|6002->3710|6048->3729|6070->3730|6126->3759|6150->3774|6174->3777|6222->3794|6260->3804|6306->3819|6341->3827|6948->4407|6981->4418|7014->4423|7084->4462|7115->4465|7224->4545|7254->4546|7287->4551|7347->4582|7377->4583|7413->4591|7581->4730|7611->4731|7652->4743|7759->4821|7789->4822|7822->4827|7851->4828|7880->4829|7944->4864|7974->4865|8006->4869|8160->4994|8190->4995|8223->5000|8322->5071|8351->5072|8382->5075|8411->5076|8440->5077|8493->5101|8523->5102|8555->5106|8635->5157|8665->5158|8698->5163|8837->5274|8865->5280|8901->5288|8930->5289|9008->5339|9036->5345|9071->5352|9100->5353|9132->5357|9202->5398|9232->5399|9264->5403|9364->5475|9393->5476|9461->5516|9489->5522|9569->5573|9599->5574|9631->5578|9731->5650|9760->5651|9828->5691|9856->5697|9936->5748|9966->5749|9998->5753|10098->5825|10127->5826|10195->5866|10223->5872|10313->5933|10343->5934|10375->5938|10480->6015|10509->6016|10587->6066|10615->6072|10705->6133|10735->6134|10767->6138|10872->6215|10901->6216|10979->6266|11007->6272|11099->6335|11129->6336|11161->6340|11267->6418|11296->6419|11376->6471|11404->6477|11508->6553|11537->6554
                  LINES: 28->1|36->5|37->6|37->6|37->6|39->8|39->8|42->11|44->13|44->13|46->15|72->41|72->41|73->42|73->42|74->43|74->43|75->44|75->44|76->45|76->45|81->50|81->50|81->50|81->50|81->50|112->81|112->81|112->81|113->82|113->82|113->82|115->84|115->84|115->84|117->86|117->86|117->86|121->90|121->90|121->90|122->91|123->92|123->92|124->93|124->93|124->93|125->94|125->94|125->94|125->94|125->94|125->94|126->95|127->96|128->97|129->98|148->117|148->117|149->118|157->126|160->129|162->131|162->131|164->133|164->133|164->133|165->134|170->139|170->139|171->140|172->141|172->141|173->142|173->142|173->142|175->144|175->144|176->145|181->150|181->150|182->151|183->152|183->152|184->153|184->153|184->153|186->155|186->155|187->156|188->157|188->157|189->158|191->160|191->160|192->161|192->161|193->162|193->162|194->163|194->163|196->165|197->166|197->166|198->167|200->169|200->169|201->170|201->170|204->173|204->173|205->174|207->176|207->176|208->177|208->177|211->180|211->180|212->181|214->183|214->183|215->184|215->184|218->187|218->187|219->188|221->190|221->190|222->191|222->191|225->194|225->194|226->195|228->197|228->197|229->198|229->198|232->201|232->201|233->202|235->204|235->204|236->205|236->205|240->209|240->209
                  -- GENERATED --
              */
          