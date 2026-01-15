
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

object reporteMovimientosDetalleAgrupado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], bodega: tables.BodegaEmpresa, esVenta: String, concepto: String, fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalEquipoDescripcion()),format.raw/*8.27*/("""
	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "DETALLE MOVIMIENTO POR "+mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase,concepto)),format.raw/*11.141*/("""

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
								"""),_display_(if(index1 < 6)/*51.24*/{_display_(Seq[Any](format.raw/*51.25*/("""
									"""),format.raw/*52.10*/("""<TR>
										"""),_display_(/*53.12*/for(nivel2 <- nivel1) yield /*53.33*/ {_display_(Seq[Any](format.raw/*53.35*/("""
											"""),format.raw/*54.12*/("""<td style="text-align: center; vertical-align: middle">"""),_display_(/*54.68*/nivel2),format.raw/*54.74*/("""</td>
										""")))}),format.raw/*55.12*/("""
									"""),format.raw/*56.10*/("""</TR>
								""")))} else {null} ),format.raw/*57.10*/("""
							""")))}),format.raw/*58.9*/("""
						"""),format.raw/*59.7*/("""</thead>
						<tbody>
							"""),_display_(/*61.9*/for((nivel1,index1) <- lista.zipWithIndex) yield /*61.51*/ {_display_(Seq[Any](format.raw/*61.53*/("""
								"""),_display_(if(index1 > 5)/*62.24*/{_display_(Seq[Any](format.raw/*62.25*/("""
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

	
	<form id="excel" class="formulario" method="post" action="/reporteMovimientosDetalleAgrupadoExcel/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*82.49*/bodega/*82.55*/.getId()),format.raw/*82.63*/("""">
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
			"""),format.raw/*117.4*/("""tabla.rows[i].cells[tabla.rows[0].cells.length-2].style.backgroundColor="#eeeeee";
			tabla.rows[i].cells[tabla.rows[0].cells.length-2].style.textAlign="right";
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
			for (var j = 5; j < tabla.rows.length-1; j++) """),format.raw/*150.50*/("""{"""),format.raw/*150.51*/("""
				"""),format.raw/*151.5*/("""var cellsOfRow = tabla.rows[j].getElementsByTagName('td');
				var aux = cellsOfRow[i].innerHTML;
				if(aux.trim()==""||aux==null) """),format.raw/*153.35*/("""{"""),format.raw/*153.36*/("""
					"""),format.raw/*154.6*/("""aux = 0;
				"""),format.raw/*155.5*/("""}"""),format.raw/*155.6*/("""else"""),format.raw/*155.10*/("""{"""),format.raw/*155.11*/("""
					"""),format.raw/*156.6*/("""if(i>5 && i<cellsOfRow.length-2)"""),format.raw/*156.38*/("""{"""),format.raw/*156.39*/("""
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

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],bodega:tables.BodegaEmpresa,esVenta:String,concepto:String,fechaDesde:String,fechaHasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,bodega,esVenta,concepto,fechaDesde,fechaHasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,bodega,esVenta,concepto,fechaDesde,fechaHasta) => apply(mapDiccionario,mapPermiso,userMnu,lista,bodega,esVenta,concepto,fechaDesde,fechaHasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteMovimientosDetalleAgrupado.scala.html
                  HASH: 2aae971f492acc4eaebd39d4ef55888962ece41e
                  MATRIX: 1101->1|1423->230|1455->237|1471->245|1510->247|1539->251|1607->299|1637->304|1681->328|1712->332|1789->383|1948->520|1981->526|3285->1803|3343->1845|3383->1847|3434->1871|3473->1872|3511->1882|3554->1898|3591->1919|3631->1921|3671->1933|3754->1989|3781->1995|3829->2012|3867->2022|3926->2037|3965->2046|3999->2053|4056->2084|4114->2126|4154->2128|4205->2152|4244->2153|4282->2163|4325->2179|4384->2222|4424->2224|4493->2266|4532->2267|4573->2280|4639->2319|4654->2325|4682->2332|4715->2338|4742->2344|4787->2370|4826->2371|4867->2384|4899->2389|4926->2395|4975->2413|5018->2425|5056->2435|5115->2450|5156->2460|5190->2467|5420->2670|5435->2676|5464->2684|5543->2736|5574->2746|5653->2798|5684->2808|5760->2857|5788->2864|5833->2879|5863->2882|5954->2945|5983->2946|6016->2952|6148->3056|6177->3057|6208->3061|6689->3513|6719->3514|6752->3519|6832->3571|6861->3572|6892->3575|6921->3576|6955->3582|7028->3626|7058->3627|7092->3633|7178->3691|7207->3692|7241->3698|7314->3742|7344->3743|7377->3748|7646->3989|7675->3990|7709->3996|7916->4174|7946->4175|7979->4180|8093->4265|8123->4266|8156->4271|8227->4314|8256->4315|8323->4353|8353->4354|8386->4359|8458->4403|8487->4404|8519->4408|8549->4409|8582->4414|8655->4459|8684->4460|8716->4464|8753->4472|8783->4473|8816->4478|8928->4562|8957->4563|8989->4567|9026->4575|9056->4576|9089->4581|9205->4669|9234->4670|9265->4673|9294->4674|9328->4680|9495->4818|9525->4819|9557->4823|9648->4885|9678->4886|9711->4891|9872->5023|9902->5024|9936->5030|9977->5043|10006->5044|10039->5048|10069->5049|10103->5055|10164->5087|10194->5088|10229->5095|10309->5147|10338->5148|10377->5159|10406->5160|10439->5165|10535->5233|10564->5234|10596->5238|10742->5355|10772->5356|10805->5361|10883->5411|10912->5412|10944->5416|10974->5417|11007->5422|11085->5472|11114->5473|11145->5476|11174->5477|11208->5483|11268->5514|11298->5515|11334->5523|11408->5568|11438->5569|11468->5570|11505->5578|11535->5579|11725->5740|11755->5741|11796->5753|11903->5831|11933->5832|11966->5837|11995->5838|12024->5839|12125->5912|12154->5913
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|41->10|42->11|42->11|44->13|81->50|81->50|81->50|82->51|82->51|83->52|84->53|84->53|84->53|85->54|85->54|85->54|86->55|87->56|88->57|89->58|90->59|92->61|92->61|92->61|93->62|93->62|94->63|95->64|95->64|95->64|96->65|96->65|97->66|97->66|97->66|97->66|97->66|97->66|98->67|98->67|99->68|99->68|99->68|100->69|101->70|102->71|103->72|104->73|105->74|113->82|113->82|113->82|114->83|114->83|115->84|115->84|116->85|116->85|120->89|123->92|125->94|125->94|127->96|129->98|129->98|130->99|138->107|138->107|139->108|140->109|140->109|141->110|141->110|143->112|143->112|143->112|144->113|145->114|145->114|147->116|147->116|147->116|148->117|151->120|151->120|153->122|157->126|157->126|158->127|160->129|160->129|161->130|162->131|162->131|162->131|162->131|163->132|164->133|164->133|164->133|164->133|165->134|166->135|166->135|167->136|167->136|167->136|168->137|170->139|170->139|171->140|171->140|171->140|172->141|173->142|173->142|174->143|174->143|176->145|179->148|179->148|180->149|181->150|181->150|182->151|184->153|184->153|185->154|186->155|186->155|186->155|186->155|187->156|187->156|187->156|188->157|189->158|189->158|191->160|191->160|192->161|193->162|193->162|194->163|195->164|195->164|196->165|197->166|197->166|197->166|197->166|198->167|199->168|199->168|200->169|200->169|202->171|202->171|202->171|203->172|204->173|204->173|204->173|204->173|204->173|211->180|211->180|212->181|213->182|213->182|214->183|214->183|214->183|217->186|217->186
                  -- GENERATED --
              */
          