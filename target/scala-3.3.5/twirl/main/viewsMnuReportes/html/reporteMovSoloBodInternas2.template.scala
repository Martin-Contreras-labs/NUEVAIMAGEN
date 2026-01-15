
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

object reporteMovSoloBodInternas2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], bodega: tables.BodegaEmpresa, fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "DETALLE MOVIMIENTO POR "+mapDiccionario.get("BODEGA")+" INTERNA: "+bodega.getNombre().toUpperCase,"")),format.raw/*12.134*/("""

				"""),format.raw/*14.5*/("""<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
							  		</div>
							  		<input type="text" class="form-control left"
										id="searchTermtablaPrincipal"
										onkeyup="doSearch5('tablaPrincipal');">
								</div>
							</td>
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
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					
					
						<thead style="background-color: #eeeeee">
					        """),_display_(/*51.15*/for((nivel1,index1) <- lista.zipWithIndex) yield /*51.57*/ {_display_(Seq[Any](format.raw/*51.59*/("""
								"""),_display_(if(index1 < 5)/*52.24*/{_display_(Seq[Any](format.raw/*52.25*/("""
									"""),format.raw/*53.10*/("""<TR>
										"""),_display_(/*54.12*/for(nivel2 <- nivel1) yield /*54.33*/ {_display_(Seq[Any](format.raw/*54.35*/("""
											"""),format.raw/*55.12*/("""<td style="textAlign: center">"""),_display_(/*55.43*/nivel2),format.raw/*55.49*/("""</td>
										""")))}),format.raw/*56.12*/("""
									"""),format.raw/*57.10*/("""</TR>
								""")))} else {null} ),format.raw/*58.10*/("""
							""")))}),format.raw/*59.9*/("""
						"""),format.raw/*60.7*/("""</thead>
						
						
						<tbody>
							"""),_display_(/*64.9*/for((nivel1,index1) <- lista.zipWithIndex) yield /*64.51*/ {_display_(Seq[Any](format.raw/*64.53*/("""
								"""),_display_(if(index1 > 4)/*65.24*/{_display_(Seq[Any](format.raw/*65.25*/("""
									"""),format.raw/*66.10*/("""<TR>
										"""),_display_(/*67.12*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*67.55*/ {_display_(Seq[Any](format.raw/*67.57*/("""
											"""),_display_(if(index2 == 1 || index2 ==2)/*68.42*/{_display_(Seq[Any](format.raw/*68.43*/("""
												"""),format.raw/*69.13*/("""<td><a href="#" onclick="buscaEquipo('"""),_display_(/*69.52*/nivel1/*69.58*/.get(1)),format.raw/*69.65*/("""');">"""),_display_(/*69.71*/nivel2),format.raw/*69.77*/("""</a></td>
											""")))}else/*70.17*/{_display_(Seq[Any](format.raw/*70.18*/("""
												"""),format.raw/*71.13*/("""<td>"""),_display_(/*71.18*/nivel2),format.raw/*71.24*/("""</td>
											""")))}),format.raw/*72.13*/("""
										""")))}),format.raw/*73.12*/("""
									"""),format.raw/*74.10*/("""</TR>
								""")))} else {null} ),format.raw/*75.10*/("""
							 """)))}),format.raw/*76.10*/("""
						"""),format.raw/*77.7*/("""</tbody>
					
					</table>
				</div>
			</div>

	
	<form id="excel" class="formulario" method="post" action="/reporteMovSoloBodInternas2Excel/">
		<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*85.56*/bodega/*85.62*/.getId()),format.raw/*85.70*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*86.50*/fechaDesde),format.raw/*86.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*87.50*/fechaHasta),format.raw/*87.60*/("""">
	</form>


""")))}),format.raw/*91.2*/("""


"""),format.raw/*94.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*96.31*/("""{"""),format.raw/*96.32*/("""
		
		"""),format.raw/*98.3*/("""var tabla = document.getElementById("tablaPrincipal");
		
		for (var i = 5; i < tabla.rows.length; i++) """),format.raw/*100.47*/("""{"""),format.raw/*100.48*/("""
			"""),format.raw/*101.4*/("""tabla.rows[i].cells[0].style.textAlign="left";
			tabla.rows[i].cells[1].style.textAlign="center";
			tabla.rows[i].cells[2].style.textAlign="left";
			tabla.rows[i].cells[3].style.textAlign="right";
			tabla.rows[i].cells[4].style.textAlign="right";
			tabla.rows[i].cells[0].style.minWidth="150px";
			tabla.rows[i].cells[2].style.minWidth="300px";
			for(var k=5;k<tabla.rows[0].cells.length;k++)"""),format.raw/*108.49*/("""{"""),format.raw/*108.50*/("""
				"""),format.raw/*109.5*/("""tabla.rows[i].cells[k].style.textAlign="center";
			"""),format.raw/*110.4*/("""}"""),format.raw/*110.5*/("""
		"""),format.raw/*111.3*/("""}"""),format.raw/*111.4*/("""
		
		
		"""),format.raw/*114.3*/("""for (var i = 0; i < tabla.rows.length; i++) """),format.raw/*114.47*/("""{"""),format.raw/*114.48*/(""" 	
			"""),format.raw/*115.4*/("""tabla.rows[i].cells[0].style.backgroundColor="#eeeeee";
			tabla.rows[i].cells[1].style.backgroundColor="#eeeeee";
			tabla.rows[i].cells[2].style.backgroundColor="#eeeeee";
			tabla.rows[i].cells[3].style.backgroundColor="#eeeeee";
			tabla.rows[i].cells[4].style.backgroundColor="#eeeeee";
		"""),format.raw/*120.3*/("""}"""),format.raw/*120.4*/("""
		
		"""),format.raw/*122.3*/("""var footer = tabla.createTFoot();
		var newRow = footer.insertRow(0);
		var cellsOfRow = tabla.rows[0].getElementsByTagName('td');
		
		for (var i = 0; i < cellsOfRow.length; i++) """),format.raw/*126.47*/("""{"""),format.raw/*126.48*/(""" 
			"""),format.raw/*127.4*/("""newRow.insertCell(i);
			newRow.cells[i].style.backgroundColor="#eeeeee";
			if(i==0)"""),format.raw/*129.12*/("""{"""),format.raw/*129.13*/("""
				"""),format.raw/*130.5*/("""newRow.cells[i].style.textAlign="left";
			"""),format.raw/*131.4*/("""}"""),format.raw/*131.5*/("""else"""),format.raw/*131.9*/("""{"""),format.raw/*131.10*/("""
				"""),format.raw/*132.5*/("""newRow.cells[i].style.textAlign="center";
			"""),format.raw/*133.4*/("""}"""),format.raw/*133.5*/("""
			"""),format.raw/*134.4*/("""if(i==0)"""),format.raw/*134.12*/("""{"""),format.raw/*134.13*/("""
				"""),format.raw/*135.5*/("""newRow.cells[i].style.textAlign="left";
				newRow.cells[i].innerHTML="TOTALES";
			"""),format.raw/*137.4*/("""}"""),format.raw/*137.5*/("""
			"""),format.raw/*138.4*/("""if(i==2)"""),format.raw/*138.12*/("""{"""),format.raw/*138.13*/("""
				"""),format.raw/*139.5*/("""newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ</div>";
			"""),format.raw/*140.4*/("""}"""),format.raw/*140.5*/("""
		"""),format.raw/*141.3*/("""}"""),format.raw/*141.4*/("""
		
		"""),format.raw/*143.3*/("""var numero2 = new DecimalFormat("#,##0.00");
		var numero0 = new DecimalFormat("#,##0");
		
		for (var i = 5; i < cellsOfRow.length; i++) """),format.raw/*146.47*/("""{"""),format.raw/*146.48*/("""
			"""),format.raw/*147.4*/("""var val = 0;
			for (var j = 5; j < tabla.rows.length-1; j++) """),format.raw/*148.50*/("""{"""),format.raw/*148.51*/("""
				"""),format.raw/*149.5*/("""var cellsOfRow = tabla.rows[j].getElementsByTagName('td');
				var aux = cellsOfRow[i].innerHTML;
				if(aux.trim()==""||aux==null) """),format.raw/*151.35*/("""{"""),format.raw/*151.36*/("""
					"""),format.raw/*152.6*/("""aux = 0;
				"""),format.raw/*153.5*/("""}"""),format.raw/*153.6*/("""else"""),format.raw/*153.10*/("""{"""),format.raw/*153.11*/("""
					"""),format.raw/*154.6*/("""cellsOfRow[i].style.backgroundColor="#F9E79F";
				"""),format.raw/*155.5*/("""}"""),format.raw/*155.6*/("""
				"""),format.raw/*156.5*/("""var val = parseFloat(val) + parseFloat(numero2.formatBack(aux));
			"""),format.raw/*157.4*/("""}"""),format.raw/*157.5*/("""
			"""),format.raw/*158.4*/("""var cellsOfRow = tabla.rows[tabla.rows.length-1].getElementsByTagName('td');
			
			if(i > 4 || i < cellsOfRow.length - 1)"""),format.raw/*160.42*/("""{"""),format.raw/*160.43*/("""
				"""),format.raw/*161.5*/("""cellsOfRow[i].innerHTML = numero0.format(val);
			"""),format.raw/*162.4*/("""}"""),format.raw/*162.5*/("""
		"""),format.raw/*163.3*/("""}"""),format.raw/*163.4*/("""
		
		
		"""),format.raw/*166.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*166.34*/("""{"""),format.raw/*166.35*/("""
		    	"""),format.raw/*167.8*/(""""fixedHeader": true,
		    //	"fixedColumns":"""),format.raw/*168.25*/("""{"""),format.raw/*168.26*/(""" """),format.raw/*168.27*/("""left: 3 """),format.raw/*168.35*/("""}"""),format.raw/*168.36*/(""",
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 2, "asc" ]],
				"scrollY": 550,
				"scrollX": true,
		    	"language": """),format.raw/*175.20*/("""{"""),format.raw/*175.21*/("""
		        	"""),format.raw/*176.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*177.11*/("""}"""),format.raw/*177.12*/("""
		  """),format.raw/*178.5*/("""}"""),format.raw/*178.6*/(""" """),format.raw/*178.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*181.2*/("""}"""),format.raw/*181.3*/(""");
	
	
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],bodega:tables.BodegaEmpresa,fechaDesde:String,fechaHasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,bodega,fechaDesde,fechaHasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,bodega,fechaDesde,fechaHasta) => apply(mapDiccionario,mapPermiso,userMnu,lista,bodega,fechaDesde,fechaHasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteMovSoloBodInternas2.scala.html
                  HASH: 2f279f6224ed84d2883b323fe1b5926b840f9d2c
                  MATRIX: 1080->1|1367->195|1400->203|1416->211|1455->213|1484->217|1552->265|1582->270|1626->294|1657->298|1734->349|1886->479|1919->485|3223->1762|3281->1804|3321->1806|3372->1830|3411->1831|3449->1841|3492->1857|3529->1878|3569->1880|3609->1892|3667->1923|3694->1929|3742->1946|3780->1956|3839->1971|3878->1980|3912->1987|3983->2032|4041->2074|4081->2076|4132->2100|4171->2101|4209->2111|4252->2127|4311->2170|4351->2172|4420->2214|4459->2215|4500->2228|4566->2267|4581->2273|4609->2280|4642->2286|4669->2292|4714->2318|4753->2319|4794->2332|4826->2337|4853->2343|4902->2361|4945->2373|4983->2383|5042->2398|5083->2408|5117->2415|5347->2618|5362->2624|5391->2632|5470->2684|5501->2694|5580->2746|5611->2756|5656->2771|5686->2774|5777->2837|5806->2838|5839->2844|5972->2948|6002->2949|6034->2953|6462->3352|6492->3353|6525->3358|6605->3410|6634->3411|6665->3414|6694->3415|6731->3424|6804->3468|6834->3469|6868->3475|7190->3769|7219->3770|7253->3776|7462->3956|7492->3957|7525->3962|7639->4047|7669->4048|7702->4053|7773->4096|7802->4097|7834->4101|7864->4102|7897->4107|7970->4152|7999->4153|8031->4157|8068->4165|8098->4166|8131->4171|8243->4255|8272->4256|8304->4260|8341->4268|8371->4269|8404->4274|8520->4362|8549->4363|8580->4366|8609->4367|8643->4373|8810->4511|8840->4512|8872->4516|8963->4578|8993->4579|9026->4584|9187->4716|9217->4717|9251->4723|9292->4736|9321->4737|9354->4741|9384->4742|9418->4748|9497->4799|9526->4800|9559->4805|9655->4873|9684->4874|9716->4878|9867->5000|9897->5001|9930->5006|10008->5056|10037->5057|10068->5060|10097->5061|10134->5070|10194->5101|10224->5102|10260->5110|10334->5155|10364->5156|10394->5157|10431->5165|10461->5166|10651->5327|10681->5328|10722->5340|10829->5418|10859->5419|10892->5424|10921->5425|10950->5426|11051->5499|11080->5500
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|45->14|82->51|82->51|82->51|83->52|83->52|84->53|85->54|85->54|85->54|86->55|86->55|86->55|87->56|88->57|89->58|90->59|91->60|95->64|95->64|95->64|96->65|96->65|97->66|98->67|98->67|98->67|99->68|99->68|100->69|100->69|100->69|100->69|100->69|100->69|101->70|101->70|102->71|102->71|102->71|103->72|104->73|105->74|106->75|107->76|108->77|116->85|116->85|116->85|117->86|117->86|118->87|118->87|122->91|125->94|127->96|127->96|129->98|131->100|131->100|132->101|139->108|139->108|140->109|141->110|141->110|142->111|142->111|145->114|145->114|145->114|146->115|151->120|151->120|153->122|157->126|157->126|158->127|160->129|160->129|161->130|162->131|162->131|162->131|162->131|163->132|164->133|164->133|165->134|165->134|165->134|166->135|168->137|168->137|169->138|169->138|169->138|170->139|171->140|171->140|172->141|172->141|174->143|177->146|177->146|178->147|179->148|179->148|180->149|182->151|182->151|183->152|184->153|184->153|184->153|184->153|185->154|186->155|186->155|187->156|188->157|188->157|189->158|191->160|191->160|192->161|193->162|193->162|194->163|194->163|197->166|197->166|197->166|198->167|199->168|199->168|199->168|199->168|199->168|206->175|206->175|207->176|208->177|208->177|209->178|209->178|209->178|212->181|212->181
                  -- GENERATED --
              */
          