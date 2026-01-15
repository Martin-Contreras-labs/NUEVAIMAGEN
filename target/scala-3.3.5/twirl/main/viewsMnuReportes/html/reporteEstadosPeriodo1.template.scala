
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

object reporteEstadosPeriodo1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
desdeAAMMDD: String, hastaAAMMDD: String, lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "REPORTE POR ESTADOS DE EQUIPOS (DEVOLUCIONES)","PERIODO DESDE " + utilities.Fechas.DDMMAA(desdeAAMMDD) + " HASTA " + utilities.Fechas.DDMMAA(hastaAAMMDD))),format.raw/*10.187*/("""
		"""),format.raw/*11.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td>
						<div align="center">
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
						</div>
					</td>
				</tr>
			</table>
		</div>
		
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
						</tr>
					</table>
				</div>
				
				
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
					        <TR> 
						        <TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*59.63*/mapDiccionario/*59.77*/.get("BODEGA")),format.raw/*59.91*/("""/PROYECTO</TH>
						        <TH style= "text-align: center;vertical-align: top;">ESTADO</TH>
								<TH style= "text-align: center;vertical-align: top;">GRUPO</TH>
								<TH style= "text-align: center;vertical-align: top;">CODIGO</TH>
								<TH style= "text-align: center;vertical-align: top;">EQUIPO</TH>
								<TH style= "text-align: center;vertical-align: top;">UN</TH>
								<TH style= "text-align: center;vertical-align: top;">CANTIDAD<br>ESTADO</TH>
								<TH style= "text-align: center;vertical-align: top;">MON</TH>
								<TH style= "text-align: center;vertical-align: top;">PRECIO<br>REPOSICION</TH>
								<TH style= "text-align: center;vertical-align: top;">UNIDAD<br>TIEMPO</TH>
								<TH style= "text-align: center;vertical-align: top;">PRECIO<br>"""),_display_(/*69.73*/mapDiccionario/*69.87*/.get("ARRIENDO")),format.raw/*69.103*/("""</TH>
								<TH style= "text-align: center;vertical-align: top;">COTI</TH>
								<TH style= "text-align: center;vertical-align: top;">NRO.MOV</TH>
								<TH style= "text-align: center;vertical-align: top;">REF.CLIE</TH>
								<TH style= "text-align: center;vertical-align: top; width: 80px">FECHA</TH>
								<TH>COBRA """),_display_(/*74.20*/mapDiccionario("ARR")),format.raw/*74.41*/("""</TH>
							</TR>
						</thead>
						<tbody>
						"""),_display_(/*78.8*/for(lista1 <- lista) yield /*78.28*/{_display_(Seq[Any](format.raw/*78.29*/("""
							"""),format.raw/*79.8*/("""<TR>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*80.61*/lista1/*80.67*/.get(15)),format.raw/*80.75*/("""</td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*81.61*/lista1/*81.67*/.get(0)),format.raw/*81.74*/("""</td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*82.61*/lista1/*82.67*/.get(1)),format.raw/*82.74*/("""</td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*83.61*/lista1/*83.67*/.get(2)),format.raw/*83.74*/("""</td>
								<td style="text-align:center;vertical-align:middle;">"""),_display_(/*84.63*/lista1/*84.69*/.get(3)),format.raw/*84.76*/("""</td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*85.61*/lista1/*85.67*/.get(4)),format.raw/*85.74*/("""</td>
								<td style="text-align:center;vertical-align:middle;">"""),_display_(/*86.63*/lista1/*86.69*/.get(5)),format.raw/*86.76*/("""</td>
								<td style="text-align:right;vertical-align:middle;">"""),_display_(/*87.62*/lista1/*87.68*/.get(6)),format.raw/*87.75*/("""</td>
								<td style="text-align:center;vertical-align:middle;">"""),_display_(/*88.63*/lista1/*88.69*/.get(7)),format.raw/*88.76*/("""</td>
								<td style="text-align:right;vertical-align:middle;">"""),_display_(/*89.62*/lista1/*89.68*/.get(8)),format.raw/*89.75*/("""</td>
								<td style="text-align:center;vertical-align:middle;">"""),_display_(/*90.63*/lista1/*90.69*/.get(9)),format.raw/*90.76*/("""</td>
								<td style="text-align:right;vertical-align:middle;">"""),_display_(/*91.62*/lista1/*91.68*/.get(10)),format.raw/*91.76*/("""</td>
								<td style="text-align:center;vertical-align:middle;">"""),_display_(/*92.63*/lista1/*92.69*/.get(14)),format.raw/*92.77*/("""</td>
								<td style="text-align:center;vertical-align:middle;">"""),_display_(/*93.63*/lista1/*93.69*/.get(11)),format.raw/*93.77*/("""</td>
								<td style="text-align:center;vertical-align:middle;">"""),_display_(/*94.63*/lista1/*94.69*/.get(16)),format.raw/*94.77*/("""</td>
								<td style="text-align:center;vertical-align:middle;">
									<div style="display:none">"""),_display_(/*96.37*/lista1/*96.43*/.get(12)),format.raw/*96.51*/("""</div>
									"""),_display_(/*97.11*/lista1/*97.17*/.get(13)),format.raw/*97.25*/("""
								"""),format.raw/*98.9*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.get(19).equals("1"))/*100.41*/{_display_(Seq[Any](format.raw/*100.42*/("""
										"""),format.raw/*101.11*/("""<div style="display: none">"""),_display_(/*101.39*/lista1/*101.45*/.get(17)),format.raw/*101.53*/("""</div>
										<input type="hidden" id="select_"""),_display_(/*102.44*/lista1/*102.50*/.get(18)),format.raw/*102.58*/("""" value=""""),_display_(/*102.68*/lista1/*102.74*/.get(17)),format.raw/*102.82*/("""">
										"""),_display_(if(lista1.get(17).equals("1"))/*103.42*/{_display_(Seq[Any](format.raw/*103.43*/("""
											"""),format.raw/*104.12*/("""<input type="checkbox" id="selectCheck_"""),_display_(/*104.52*/lista1/*104.58*/.get(18)),format.raw/*104.66*/("""" checked onchange="modificaSelect('"""),_display_(/*104.103*/lista1/*104.109*/.get(18)),format.raw/*104.117*/("""');">
										""")))}else/*105.16*/{_display_(Seq[Any](format.raw/*105.17*/("""
											"""),format.raw/*106.12*/("""<input type="checkbox" id="selectCheck_"""),_display_(/*106.52*/lista1/*106.58*/.get(18)),format.raw/*106.66*/("""" onchange="modificaSelect('"""),_display_(/*106.95*/lista1/*106.101*/.get(18)),format.raw/*106.109*/("""');">
										""")))}),format.raw/*107.12*/("""
									""")))} else {null} ),format.raw/*108.11*/("""
								"""),format.raw/*109.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*111.9*/("""
						"""),format.raw/*112.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<form id="excel" class="formulario" method="post" action="/reporteEstadosPeriodo1Excel/">
		<input type="hidden" name="desdeAAMMDD" value=""""),_display_(/*120.51*/desdeAAMMDD),format.raw/*120.62*/("""">
		<input type="hidden" name="hastaAAMMDD" value=""""),_display_(/*121.51*/hastaAAMMDD),format.raw/*121.62*/("""">
	</form>

""")))}),format.raw/*124.2*/("""


"""),format.raw/*127.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*129.31*/("""{"""),format.raw/*129.32*/("""

		 """),format.raw/*131.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*131.35*/("""{"""),format.raw/*131.36*/("""
		    	"""),format.raw/*132.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*137.20*/("""{"""),format.raw/*137.21*/("""
		        	"""),format.raw/*138.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*139.11*/("""}"""),format.raw/*139.12*/("""
		  """),format.raw/*140.5*/("""}"""),format.raw/*140.6*/(""" """),format.raw/*140.7*/(""");
		  
		  

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*145.2*/("""}"""),format.raw/*145.3*/(""");

	const modificaSelect = (id_movimiento) => """),format.raw/*147.44*/("""{"""),format.raw/*147.45*/("""
		"""),format.raw/*148.3*/("""let select = $("#select_"+id_movimiento).val();
		if(select == 1)"""),format.raw/*149.18*/("""{"""),format.raw/*149.19*/("""
			"""),format.raw/*150.4*/("""modificaSelect2(id_movimiento, 0);
			const elementIdDet = "select_" + id_movimiento;
			$(`[id='$"""),format.raw/*152.13*/("""{"""),format.raw/*152.14*/("""elementIdDet"""),format.raw/*152.26*/("""}"""),format.raw/*152.27*/("""']`).each(function() """),format.raw/*152.48*/("""{"""),format.raw/*152.49*/("""
				"""),format.raw/*153.5*/("""$(this).val(0);
			"""),format.raw/*154.4*/("""}"""),format.raw/*154.5*/(""");
			const selectCheck = "selectCheck_" + id_movimiento;
			$(`[id='$"""),format.raw/*156.13*/("""{"""),format.raw/*156.14*/("""selectCheck"""),format.raw/*156.25*/("""}"""),format.raw/*156.26*/("""']`).each(function() """),format.raw/*156.47*/("""{"""),format.raw/*156.48*/("""
				"""),format.raw/*157.5*/("""$(this).prop('checked', false);
			"""),format.raw/*158.4*/("""}"""),format.raw/*158.5*/(""");
		"""),format.raw/*159.3*/("""}"""),format.raw/*159.4*/("""else"""),format.raw/*159.8*/("""{"""),format.raw/*159.9*/("""
			"""),format.raw/*160.4*/("""modificaSelect2(id_movimiento, 1);
			const elementIdDet = "select_" + id_movimiento;
			$(`[id='$"""),format.raw/*162.13*/("""{"""),format.raw/*162.14*/("""elementIdDet"""),format.raw/*162.26*/("""}"""),format.raw/*162.27*/("""']`).each(function() """),format.raw/*162.48*/("""{"""),format.raw/*162.49*/("""
				"""),format.raw/*163.5*/("""$(this).val(1);
			"""),format.raw/*164.4*/("""}"""),format.raw/*164.5*/(""");
			const selectCheck = "selectCheck_" + id_movimiento;
			$(`[id='$"""),format.raw/*166.13*/("""{"""),format.raw/*166.14*/("""selectCheck"""),format.raw/*166.25*/("""}"""),format.raw/*166.26*/("""']`).each(function() """),format.raw/*166.47*/("""{"""),format.raw/*166.48*/("""
				"""),format.raw/*167.5*/("""$(this).prop('checked', true);
			"""),format.raw/*168.4*/("""}"""),format.raw/*168.5*/(""");
		"""),format.raw/*169.3*/("""}"""),format.raw/*169.4*/("""
	"""),format.raw/*170.2*/("""}"""),format.raw/*170.3*/("""

	"""),format.raw/*172.2*/("""const modificaSelect2 = (id_movimiento, valor) => """),format.raw/*172.52*/("""{"""),format.raw/*172.53*/("""
		"""),format.raw/*173.3*/("""let formData = new FormData();
		formData.append('id_movimiento',id_movimiento);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*176.10*/("""{"""),format.raw/*176.11*/("""
			"""),format.raw/*177.4*/("""url: "/routes3/modCobraArriendoAjax/",
			type: "POST",
			method: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(respuesta)"""),format.raw/*184.32*/("""{"""),format.raw/*184.33*/("""
				"""),format.raw/*185.5*/("""if(respuesta=="error")"""),format.raw/*185.27*/("""{"""),format.raw/*185.28*/("""
					"""),format.raw/*186.6*/("""alertify.alert(msgError, function () """),format.raw/*186.43*/("""{"""),format.raw/*186.44*/("""
						"""),format.raw/*187.7*/("""location.href = "/";
					"""),format.raw/*188.6*/("""}"""),format.raw/*188.7*/(""");
				"""),format.raw/*189.5*/("""}"""),format.raw/*189.6*/("""
			"""),format.raw/*190.4*/("""}"""),format.raw/*190.5*/("""
		"""),format.raw/*191.3*/("""}"""),format.raw/*191.4*/(""");
	"""),format.raw/*192.2*/("""}"""),format.raw/*192.3*/("""
	
"""),format.raw/*194.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,desdeAAMMDD:String,hastaAAMMDD:String,lista:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,desdeAAMMDD,hastaAAMMDD,lista)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,desdeAAMMDD,hastaAAMMDD,lista) => apply(mapDiccionario,mapPermiso,userMnu,desdeAAMMDD,hastaAAMMDD,lista)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteEstadosPeriodo1.scala.html
                  HASH: a1b7c9b24f5bac19b2cb7660f0989d1653c5a043
                  MATRIX: 1055->1|1314->167|1341->169|1357->177|1396->179|1425->183|1493->231|1525->237|1602->288|1807->471|1837->474|3498->2108|3521->2122|3556->2136|4361->2914|4384->2928|4422->2944|4778->3273|4820->3294|4901->3349|4937->3369|4976->3370|5011->3378|5103->3443|5118->3449|5147->3457|5240->3523|5255->3529|5283->3536|5376->3602|5391->3608|5419->3615|5512->3681|5527->3687|5555->3694|5650->3762|5665->3768|5693->3775|5786->3841|5801->3847|5829->3854|5924->3922|5939->3928|5967->3935|6061->4002|6076->4008|6104->4015|6199->4083|6214->4089|6242->4096|6336->4163|6351->4169|6379->4176|6474->4244|6489->4250|6517->4257|6611->4324|6626->4330|6655->4338|6750->4406|6765->4412|6794->4420|6889->4488|6904->4494|6933->4502|7028->4570|7043->4576|7072->4584|7203->4688|7218->4694|7247->4702|7291->4719|7306->4725|7335->4733|7371->4742|7486->4829|7526->4830|7566->4841|7622->4869|7638->4875|7668->4883|7746->4933|7762->4939|7792->4947|7830->4957|7846->4963|7876->4971|7948->5015|7988->5016|8029->5028|8097->5068|8113->5074|8143->5082|8209->5119|8226->5125|8257->5133|8298->5154|8338->5155|8379->5167|8447->5207|8463->5213|8493->5221|8550->5250|8567->5256|8598->5264|8647->5281|8703->5292|8740->5301|8798->5328|8833->5335|9065->5539|9098->5550|9179->5603|9212->5614|9257->5628|9288->5631|9397->5711|9427->5712|9460->5717|9520->5748|9550->5749|9586->5757|9767->5909|9797->5910|9838->5922|9945->6000|9975->6001|10008->6006|10037->6007|10066->6008|10177->6091|10206->6092|10282->6139|10312->6140|10343->6143|10437->6208|10467->6209|10499->6213|10626->6311|10656->6312|10697->6324|10727->6325|10777->6346|10807->6347|10840->6352|10887->6371|10916->6372|11015->6442|11045->6443|11085->6454|11115->6455|11165->6476|11195->6477|11228->6482|11291->6517|11320->6518|11353->6523|11382->6524|11414->6528|11443->6529|11475->6533|11602->6631|11632->6632|11673->6644|11703->6645|11753->6666|11783->6667|11816->6672|11863->6691|11892->6692|11991->6762|12021->6763|12061->6774|12091->6775|12141->6796|12171->6797|12204->6802|12266->6836|12295->6837|12328->6842|12357->6843|12387->6845|12416->6846|12447->6849|12526->6899|12556->6900|12587->6903|12740->7027|12770->7028|12802->7032|13019->7220|13049->7221|13082->7226|13133->7248|13163->7249|13197->7255|13263->7292|13293->7293|13328->7300|13382->7326|13411->7327|13446->7334|13475->7335|13507->7339|13536->7340|13567->7343|13596->7344|13628->7348|13657->7349|13688->7352
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|40->9|41->10|41->10|42->11|90->59|90->59|90->59|100->69|100->69|100->69|105->74|105->74|109->78|109->78|109->78|110->79|111->80|111->80|111->80|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|115->84|115->84|115->84|116->85|116->85|116->85|117->86|117->86|117->86|118->87|118->87|118->87|119->88|119->88|119->88|120->89|120->89|120->89|121->90|121->90|121->90|122->91|122->91|122->91|123->92|123->92|123->92|124->93|124->93|124->93|125->94|125->94|125->94|127->96|127->96|127->96|128->97|128->97|128->97|129->98|131->100|131->100|132->101|132->101|132->101|132->101|133->102|133->102|133->102|133->102|133->102|133->102|134->103|134->103|135->104|135->104|135->104|135->104|135->104|135->104|135->104|136->105|136->105|137->106|137->106|137->106|137->106|137->106|137->106|137->106|138->107|139->108|140->109|142->111|143->112|151->120|151->120|152->121|152->121|155->124|158->127|160->129|160->129|162->131|162->131|162->131|163->132|168->137|168->137|169->138|170->139|170->139|171->140|171->140|171->140|176->145|176->145|178->147|178->147|179->148|180->149|180->149|181->150|183->152|183->152|183->152|183->152|183->152|183->152|184->153|185->154|185->154|187->156|187->156|187->156|187->156|187->156|187->156|188->157|189->158|189->158|190->159|190->159|190->159|190->159|191->160|193->162|193->162|193->162|193->162|193->162|193->162|194->163|195->164|195->164|197->166|197->166|197->166|197->166|197->166|197->166|198->167|199->168|199->168|200->169|200->169|201->170|201->170|203->172|203->172|203->172|204->173|207->176|207->176|208->177|215->184|215->184|216->185|216->185|216->185|217->186|217->186|217->186|218->187|219->188|219->188|220->189|220->189|221->190|221->190|222->191|222->191|223->192|223->192|225->194
                  -- GENERATED --
              */
          