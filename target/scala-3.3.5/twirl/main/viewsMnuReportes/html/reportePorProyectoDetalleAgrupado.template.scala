
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

object reportePorProyectoDetalleAgrupado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.Proyecto,String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], proyecto: tables.Proyecto, esVenta: String, concepto: String, fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalEquipoDescripcion()),format.raw/*8.27*/("""
	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "DETALLE MOVIMIENTO POR PROYECTO: "+proyecto.getNombre().toUpperCase,concepto)),format.raw/*11.110*/("""

				"""),format.raw/*13.5*/("""<div class="noprint">
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
					        """),_display_(/*50.15*/for((nivel1,index1) <- lista.zipWithIndex) yield /*50.57*/ {_display_(Seq[Any](format.raw/*50.59*/("""
								"""),_display_(if(index1 < 7)/*51.24*/{_display_(Seq[Any](format.raw/*51.25*/("""
									"""),format.raw/*52.10*/("""<TR>
										"""),_display_(/*53.12*/for(nivel2 <- nivel1) yield /*53.33*/ {_display_(Seq[Any](format.raw/*53.35*/("""
											"""),format.raw/*54.12*/("""<td style="textAlign: center">"""),_display_(/*54.43*/nivel2),format.raw/*54.49*/("""</td>
										""")))}),format.raw/*55.12*/("""
									"""),format.raw/*56.10*/("""</TR>
								""")))} else {null} ),format.raw/*57.10*/("""
							""")))}),format.raw/*58.9*/("""
						"""),format.raw/*59.7*/("""</thead>
						<tbody>
							"""),_display_(/*61.9*/for((nivel1,index1) <- lista.zipWithIndex) yield /*61.51*/ {_display_(Seq[Any](format.raw/*61.53*/("""
								"""),_display_(if(index1 > 6)/*62.24*/{_display_(Seq[Any](format.raw/*62.25*/("""
									"""),format.raw/*63.10*/("""<TR>
										"""),_display_(/*64.12*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*64.55*/ {_display_(Seq[Any](format.raw/*64.57*/("""
											"""),_display_(if(index2 == 1 || index2 ==2)/*65.42*/{_display_(Seq[Any](format.raw/*65.43*/("""
												"""),format.raw/*66.13*/("""<td><a href="#" onclick="buscaEquipo('"""),_display_(/*66.52*/nivel1/*66.58*/.get(1)),format.raw/*66.65*/("""');">"""),_display_(/*66.71*/nivel2),format.raw/*66.77*/("""</a></td>
											""")))}else/*67.17*/{_display_(Seq[Any](format.raw/*67.18*/("""
												"""),format.raw/*68.13*/("""<td>"""),_display_(/*68.18*/nivel2),format.raw/*68.24*/("""</td>
											""")))}),format.raw/*69.13*/("""
										""")))}),format.raw/*70.12*/("""
									"""),format.raw/*71.10*/("""</TR>
								""")))} else {null} ),format.raw/*72.10*/("""
							 """)))}),format.raw/*73.10*/("""
						"""),format.raw/*74.7*/("""</tbody>
					
					</table>
				</div>
			</div>

	
	<form id="excel" class="formulario" method="post" action="/routes2/reportePorProyectoDetalleAgrupadoExcel/">
		<input type="hidden" name="id_proyecto" value=""""),_display_(/*82.51*/proyecto/*82.59*/.getId()),format.raw/*82.67*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*83.50*/fechaDesde),format.raw/*83.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*84.50*/fechaHasta),format.raw/*84.60*/("""">
		<input type="hidden" name="esVenta" value=""""),_display_(/*85.47*/esVenta),format.raw/*85.54*/("""">
	</form>


""")))}),format.raw/*89.2*/("""


"""),format.raw/*92.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*94.31*/("""{"""),format.raw/*94.32*/("""
		
		"""),format.raw/*96.3*/("""var tabla = document.getElementById("tablaPrincipal");
		
		for (var i = 5; i < tabla.rows.length; i++) """),format.raw/*98.47*/("""{"""),format.raw/*98.48*/("""
			"""),format.raw/*99.4*/("""tabla.rows[i].cells[0].style.textAlign="left";
			tabla.rows[i].cells[1].style.textAlign="center";
			tabla.rows[i].cells[2].style.textAlign="left";
			tabla.rows[i].cells[3].style.textAlign="right";
			tabla.rows[i].cells[4].style.textAlign="right";
			tabla.rows[i].cells[5].style.textAlign="right";
			tabla.rows[i].cells[0].style.minWidth="150px";
			tabla.rows[i].cells[2].style.minWidth="300px";
			for(var k=6;k<tabla.rows[0].cells.length-1;k++)"""),format.raw/*107.51*/("""{"""),format.raw/*107.52*/("""
				"""),format.raw/*108.5*/("""tabla.rows[i].cells[k].style.textAlign="center";
			"""),format.raw/*109.4*/("""}"""),format.raw/*109.5*/("""
		"""),format.raw/*110.3*/("""}"""),format.raw/*110.4*/("""
		
		"""),format.raw/*112.3*/("""for (var i = 0; i < tabla.rows.length; i++) """),format.raw/*112.47*/("""{"""),format.raw/*112.48*/(""" 	
			"""),format.raw/*113.4*/("""tabla.rows[i].cells[5].style.backgroundColor="#eeeeee";
		"""),format.raw/*114.3*/("""}"""),format.raw/*114.4*/("""
		
		"""),format.raw/*116.3*/("""for (var i = 5; i < tabla.rows.length; i++) """),format.raw/*116.47*/("""{"""),format.raw/*116.48*/(""" 
			"""),format.raw/*117.4*/("""tabla.rows[i].cells[tabla.rows[0].cells.length-1].style.backgroundColor="#eeeeee";
			tabla.rows[i].cells[tabla.rows[0].cells.length-1].style.textAlign="right";
			tabla.rows[i].cells[tabla.rows[0].cells.length-1].style.textAlign="right";
		"""),format.raw/*120.3*/("""}"""),format.raw/*120.4*/("""
		
		"""),format.raw/*122.3*/("""var footer = tabla.createTFoot();
		var newRow = footer.insertRow(0);
		var cellsOfRow = tabla.rows[0].getElementsByTagName('td');
		
		for (var i = 0; i < cellsOfRow.length; i++) """),format.raw/*126.47*/("""{"""),format.raw/*126.48*/(""" 
			"""),format.raw/*127.4*/("""newRow.insertCell(i);
			newRow.cells[i].style.backgroundColor="#eeeeee";
			if(i==0)"""),format.raw/*129.12*/("""{"""),format.raw/*129.13*/("""
				"""),format.raw/*130.5*/("""newRow.cells[i].style.textAlign="left";
			"""),format.raw/*131.4*/("""}"""),format.raw/*131.5*/("""else if(i==5 || i>cellsOfRow.length-3)"""),format.raw/*131.43*/("""{"""),format.raw/*131.44*/("""
				"""),format.raw/*132.5*/("""newRow.cells[i].style.textAlign="right";
			"""),format.raw/*133.4*/("""}"""),format.raw/*133.5*/("""else"""),format.raw/*133.9*/("""{"""),format.raw/*133.10*/("""
				"""),format.raw/*134.5*/("""newRow.cells[i].style.textAlign="center";
			"""),format.raw/*135.4*/("""}"""),format.raw/*135.5*/("""
			"""),format.raw/*136.4*/("""if(i==0)"""),format.raw/*136.12*/("""{"""),format.raw/*136.13*/("""
				"""),format.raw/*137.5*/("""newRow.cells[i].style.textAlign="left";
				newRow.cells[i].innerHTML="TOTALES";
			"""),format.raw/*139.4*/("""}"""),format.raw/*139.5*/("""
			"""),format.raw/*140.4*/("""if(i==2)"""),format.raw/*140.12*/("""{"""),format.raw/*140.13*/("""
				"""),format.raw/*141.5*/("""newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ</div>";
			"""),format.raw/*142.4*/("""}"""),format.raw/*142.5*/("""
		"""),format.raw/*143.3*/("""}"""),format.raw/*143.4*/("""
		
		"""),format.raw/*145.3*/("""var numero2 = new DecimalFormat("#,##0.00");
		var numero0 = new DecimalFormat("#,##0");
		
		for (var i = 5; i < cellsOfRow.length; i++) """),format.raw/*148.47*/("""{"""),format.raw/*148.48*/("""
			"""),format.raw/*149.4*/("""var val = 0;
			for (var j = 6; j < tabla.rows.length-1; j++) """),format.raw/*150.50*/("""{"""),format.raw/*150.51*/("""
				"""),format.raw/*151.5*/("""var cellsOfRow = tabla.rows[j].getElementsByTagName('td');
				var aux = cellsOfRow[i].innerHTML;
				if(aux.trim()==""||aux==null) """),format.raw/*153.35*/("""{"""),format.raw/*153.36*/("""
					"""),format.raw/*154.6*/("""aux = 0;
				"""),format.raw/*155.5*/("""}"""),format.raw/*155.6*/("""else"""),format.raw/*155.10*/("""{"""),format.raw/*155.11*/("""
					"""),format.raw/*156.6*/("""if(i>5 && i<cellsOfRow.length-1)"""),format.raw/*156.38*/("""{"""),format.raw/*156.39*/("""
						"""),format.raw/*157.7*/("""cellsOfRow[i].style.backgroundColor="#F9E79F";
					"""),format.raw/*158.6*/("""}"""),format.raw/*158.7*/("""
					
				"""),format.raw/*160.5*/("""}"""),format.raw/*160.6*/("""
				"""),format.raw/*161.5*/("""var val = parseFloat(val) + parseFloat(numero2.formatBack(aux));
			"""),format.raw/*162.4*/("""}"""),format.raw/*162.5*/("""
			"""),format.raw/*163.4*/("""var cellsOfRow = tabla.rows[tabla.rows.length-1].getElementsByTagName('td');
			if(i == 5 || i > cellsOfRow.length-3)"""),format.raw/*164.41*/("""{"""),format.raw/*164.42*/("""
				"""),format.raw/*165.5*/("""cellsOfRow[i].innerHTML = numero2.format(val);
			"""),format.raw/*166.4*/("""}"""),format.raw/*166.5*/("""else"""),format.raw/*166.9*/("""{"""),format.raw/*166.10*/("""
				"""),format.raw/*167.5*/("""cellsOfRow[i].innerHTML = numero0.format(val);
			"""),format.raw/*168.4*/("""}"""),format.raw/*168.5*/("""
		"""),format.raw/*169.3*/("""}"""),format.raw/*169.4*/("""
		
		"""),format.raw/*171.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*171.34*/("""{"""),format.raw/*171.35*/("""
		    	"""),format.raw/*172.8*/(""""fixedHeader": true,
		    //	"fixedColumns":"""),format.raw/*173.25*/("""{"""),format.raw/*173.26*/(""" """),format.raw/*173.27*/("""left: 3 """),format.raw/*173.35*/("""}"""),format.raw/*173.36*/(""",
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 2, "asc" ]],
				"scrollY": 550,
				"scrollX": true,
		    	"language": """),format.raw/*180.20*/("""{"""),format.raw/*180.21*/("""
		        	"""),format.raw/*181.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*182.11*/("""}"""),format.raw/*182.12*/("""
		  """),format.raw/*183.5*/("""}"""),format.raw/*183.6*/(""" """),format.raw/*183.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*186.2*/("""}"""),format.raw/*186.3*/(""");
	
	
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],proyecto:tables.Proyecto,esVenta:String,concepto:String,fechaDesde:String,fechaHasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,proyecto,esVenta,concepto,fechaDesde,fechaHasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.Proyecto,String,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,proyecto,esVenta,concepto,fechaDesde,fechaHasta) => apply(mapDiccionario,mapPermiso,userMnu,lista,proyecto,esVenta,concepto,fechaDesde,fechaHasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportePorProyectoDetalleAgrupado.scala.html
                  HASH: 0e395848e34e4fda085134909f7037c0b2a0ba47
                  MATRIX: 1096->1|1415->227|1447->234|1463->242|1502->244|1531->248|1599->296|1629->301|1673->325|1704->329|1781->380|1909->486|1942->492|3246->1769|3304->1811|3344->1813|3395->1837|3434->1838|3472->1848|3515->1864|3552->1885|3592->1887|3632->1899|3690->1930|3717->1936|3765->1953|3803->1963|3862->1978|3901->1987|3935->1994|3992->2025|4050->2067|4090->2069|4141->2093|4180->2094|4218->2104|4261->2120|4320->2163|4360->2165|4429->2207|4468->2208|4509->2221|4575->2260|4590->2266|4618->2273|4651->2279|4678->2285|4723->2311|4762->2312|4803->2325|4835->2330|4862->2336|4911->2354|4954->2366|4992->2376|5051->2391|5092->2401|5126->2408|5366->2621|5383->2629|5412->2637|5491->2689|5522->2699|5601->2751|5632->2761|5708->2810|5736->2817|5781->2832|5811->2835|5902->2898|5931->2899|5964->2905|6096->3009|6125->3010|6156->3014|6637->3466|6667->3467|6700->3472|6780->3524|6809->3525|6840->3528|6869->3529|6903->3535|6976->3579|7006->3580|7040->3586|7126->3644|7155->3645|7189->3651|7262->3695|7292->3696|7325->3701|7594->3942|7623->3943|7657->3949|7866->4129|7896->4130|7929->4135|8043->4220|8073->4221|8106->4226|8177->4269|8206->4270|8273->4308|8303->4309|8336->4314|8408->4358|8437->4359|8469->4363|8499->4364|8532->4369|8605->4414|8634->4415|8666->4419|8703->4427|8733->4428|8766->4433|8878->4517|8907->4518|8939->4522|8976->4530|9006->4531|9039->4536|9155->4624|9184->4625|9215->4628|9244->4629|9278->4635|9445->4773|9475->4774|9507->4778|9598->4840|9628->4841|9661->4846|9822->4978|9852->4979|9886->4985|9927->4998|9956->4999|9989->5003|10019->5004|10053->5010|10114->5042|10144->5043|10179->5050|10259->5102|10288->5103|10327->5114|10356->5115|10389->5120|10485->5188|10514->5189|10546->5193|10692->5310|10722->5311|10755->5316|10833->5366|10862->5367|10894->5371|10924->5372|10957->5377|11035->5427|11064->5428|11095->5431|11124->5432|11158->5438|11218->5469|11248->5470|11284->5478|11358->5523|11388->5524|11418->5525|11455->5533|11485->5534|11675->5695|11705->5696|11746->5708|11853->5786|11883->5787|11916->5792|11945->5793|11974->5794|12075->5867|12104->5868
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|41->10|42->11|42->11|44->13|81->50|81->50|81->50|82->51|82->51|83->52|84->53|84->53|84->53|85->54|85->54|85->54|86->55|87->56|88->57|89->58|90->59|92->61|92->61|92->61|93->62|93->62|94->63|95->64|95->64|95->64|96->65|96->65|97->66|97->66|97->66|97->66|97->66|97->66|98->67|98->67|99->68|99->68|99->68|100->69|101->70|102->71|103->72|104->73|105->74|113->82|113->82|113->82|114->83|114->83|115->84|115->84|116->85|116->85|120->89|123->92|125->94|125->94|127->96|129->98|129->98|130->99|138->107|138->107|139->108|140->109|140->109|141->110|141->110|143->112|143->112|143->112|144->113|145->114|145->114|147->116|147->116|147->116|148->117|151->120|151->120|153->122|157->126|157->126|158->127|160->129|160->129|161->130|162->131|162->131|162->131|162->131|163->132|164->133|164->133|164->133|164->133|165->134|166->135|166->135|167->136|167->136|167->136|168->137|170->139|170->139|171->140|171->140|171->140|172->141|173->142|173->142|174->143|174->143|176->145|179->148|179->148|180->149|181->150|181->150|182->151|184->153|184->153|185->154|186->155|186->155|186->155|186->155|187->156|187->156|187->156|188->157|189->158|189->158|191->160|191->160|192->161|193->162|193->162|194->163|195->164|195->164|196->165|197->166|197->166|197->166|197->166|198->167|199->168|199->168|200->169|200->169|202->171|202->171|202->171|203->172|204->173|204->173|204->173|204->173|204->173|211->180|211->180|212->181|213->182|213->182|214->183|214->183|214->183|217->186|217->186
                  -- GENERATED --
              */
          