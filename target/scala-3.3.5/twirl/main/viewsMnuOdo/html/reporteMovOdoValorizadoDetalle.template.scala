
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

object reporteMovOdoValorizadoDetalle extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template11[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String,Double,Double,Double,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], bodega: tables.BodegaEmpresa, fechaDesde: String, fechaHasta: String,
uf: Double, usd: Double, eur: Double, listaAjustes: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "ODO MOVIMIENTO VALORIZADO",mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase)),format.raw/*12.134*/("""

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
								"""),_display_(if(index1 < 2)/*52.24*/{_display_(Seq[Any](format.raw/*52.25*/("""
									"""),format.raw/*53.10*/("""<TR>
										"""),_display_(/*54.12*/for(nivel2 <- nivel1) yield /*54.33*/ {_display_(Seq[Any](format.raw/*54.35*/("""
											"""),format.raw/*55.12*/("""<td style="textAlign: center">"""),_display_(/*55.43*/nivel2),format.raw/*55.49*/("""</td>
										""")))}),format.raw/*56.12*/("""
									"""),format.raw/*57.10*/("""</TR>
								""")))} else {null} ),format.raw/*58.10*/("""
							""")))}),format.raw/*59.9*/("""
						"""),format.raw/*60.7*/("""</thead>
						<tbody>
							"""),_display_(/*62.9*/for((nivel1,index1) <- lista.zipWithIndex) yield /*62.51*/ {_display_(Seq[Any](format.raw/*62.53*/("""
								"""),_display_(if(index1 > 1)/*63.24*/{_display_(Seq[Any](format.raw/*63.25*/("""
									"""),format.raw/*64.10*/("""<TR>
										"""),_display_(/*65.12*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*65.55*/ {_display_(Seq[Any](format.raw/*65.57*/("""
											"""),_display_(if(index2 == 1 || index2 ==2)/*66.42*/{_display_(Seq[Any](format.raw/*66.43*/("""
												"""),format.raw/*67.13*/("""<td><a href="#" onclick="buscaEquipo('"""),_display_(/*67.52*/nivel1/*67.58*/.get(1)),format.raw/*67.65*/("""');">"""),_display_(/*67.71*/nivel2),format.raw/*67.77*/("""</a></td>
											""")))}else/*68.17*/{_display_(Seq[Any](format.raw/*68.18*/("""
												"""),format.raw/*69.13*/("""<td>"""),_display_(/*69.18*/nivel2),format.raw/*69.24*/("""</td>
											""")))}),format.raw/*70.13*/("""
										""")))}),format.raw/*71.12*/("""
									"""),format.raw/*72.10*/("""</TR>
								""")))} else {null} ),format.raw/*73.10*/("""
							 """)))}),format.raw/*74.10*/("""
						"""),format.raw/*75.7*/("""</tbody>
					
					</table>
				</div>
			</div>

	
	<form id="excel" class="formulario" method="post" action="/reporteMovOdoValorizadoDetalleExcel/">
		<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*83.56*/bodega/*83.62*/.getId()),format.raw/*83.70*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*84.50*/fechaDesde),format.raw/*84.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*85.50*/fechaHasta),format.raw/*85.60*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*86.42*/uf),format.raw/*86.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*87.43*/usd),format.raw/*87.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*88.43*/eur),format.raw/*88.46*/("""">
	</form>


""")))}),format.raw/*92.2*/("""


"""),format.raw/*95.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*97.31*/("""{"""),format.raw/*97.32*/("""
		
		"""),format.raw/*99.3*/("""var tabla = document.getElementById("tablaPrincipal");
	
		for (var i = 0; i < tabla.rows.length; i++) """),format.raw/*101.47*/("""{"""),format.raw/*101.48*/("""
			"""),format.raw/*102.4*/("""tabla.rows[i].cells[0].style.textAlign="left";
			tabla.rows[i].cells[1].style.textAlign="center";
			tabla.rows[i].cells[2].style.textAlign="left";
			tabla.rows[i].cells[3].style.textAlign="center";
			tabla.rows[i].cells[0].style.minWidth="150px";
			tabla.rows[i].cells[2].style.minWidth="300px";
			for(var k=4;k<tabla.rows[0].cells.length;k++)"""),format.raw/*108.49*/("""{"""),format.raw/*108.50*/("""
				"""),format.raw/*109.5*/("""tabla.rows[i].cells[k].style.textAlign="center";
			"""),format.raw/*110.4*/("""}"""),format.raw/*110.5*/("""
		"""),format.raw/*111.3*/("""}"""),format.raw/*111.4*/("""
		
		
		

		"""),format.raw/*116.3*/("""var newRow = tabla.insertRow(tabla.rows.length);
		var cellsOfRow = tabla.rows[0].getElementsByTagName('td');
		
		for (var i = 0; i < cellsOfRow.length; i++) """),format.raw/*119.47*/("""{"""),format.raw/*119.48*/(""" 
			"""),format.raw/*120.4*/("""newRow.insertCell(i);
			newRow.cells[i].style.backgroundColor="#eeeeee";
			if(i==0)"""),format.raw/*122.12*/("""{"""),format.raw/*122.13*/("""
				"""),format.raw/*123.5*/("""newRow.cells[i].style.textAlign="left";
				newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ</div>TOTALES (antes de ajustes)";
			"""),format.raw/*125.4*/("""}"""),format.raw/*125.5*/("""
			"""),format.raw/*126.4*/("""if(i==2)"""),format.raw/*126.12*/("""{"""),format.raw/*126.13*/("""
				"""),format.raw/*127.5*/("""newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ</div>";
			"""),format.raw/*128.4*/("""}"""),format.raw/*128.5*/("""
		"""),format.raw/*129.3*/("""}"""),format.raw/*129.4*/("""
		
		"""),format.raw/*131.3*/("""var numero2 = new DecimalFormat("#,##0.00");
		var numero0 = new DecimalFormat("#,##0");
		
		let primerTotal = 0;
		for (var i = 4; i < cellsOfRow.length; i++) """),format.raw/*135.47*/("""{"""),format.raw/*135.48*/("""
			"""),format.raw/*136.4*/("""var val = 0;
			for (var j = 2; j < tabla.rows.length-1; j++) """),format.raw/*137.50*/("""{"""),format.raw/*137.51*/("""
				"""),format.raw/*138.5*/("""if(!(i==cellsOfRow.length-5 || i==cellsOfRow.length-4))"""),format.raw/*138.60*/("""{"""),format.raw/*138.61*/("""
					"""),format.raw/*139.6*/("""var cellsOfRow = tabla.rows[j].getElementsByTagName('td');
					var aux = cellsOfRow[i].innerHTML;
					if(aux.trim()==""||aux==null) aux = 0;
					var val = parseFloat(val) + parseFloat(numero2.formatBack(aux));
				"""),format.raw/*143.5*/("""}"""),format.raw/*143.6*/("""
			"""),format.raw/*144.4*/("""}"""),format.raw/*144.5*/("""
			"""),format.raw/*145.4*/("""var cellsOfRow = tabla.rows[tabla.rows.length-1].getElementsByTagName('td');
			newRow.cells[i].style.textAlign="center";
			if(i==cellsOfRow.length-5 || i==cellsOfRow.length-4 || i==cellsOfRow.length-2)"""),format.raw/*147.82*/("""{"""),format.raw/*147.83*/("""
				"""),format.raw/*148.5*/("""cellsOfRow[i].innerHTML = "";
			"""),format.raw/*149.4*/("""}"""),format.raw/*149.5*/("""else if(i<cellsOfRow.length-5)"""),format.raw/*149.35*/("""{"""),format.raw/*149.36*/("""
				"""),format.raw/*150.5*/("""cellsOfRow[i].innerHTML = numero2.format(val);
			"""),format.raw/*151.4*/("""}"""),format.raw/*151.5*/("""else"""),format.raw/*151.9*/("""{"""),format.raw/*151.10*/("""
				"""),format.raw/*152.5*/("""cellsOfRow[i].innerHTML = numero0.format(val);
				newRow.cells[i].style.textAlign="right";
			"""),format.raw/*154.4*/("""}"""),format.raw/*154.5*/("""
			"""),format.raw/*155.4*/("""primerTotal = val;
		"""),format.raw/*156.3*/("""}"""),format.raw/*156.4*/("""
		
		"""),format.raw/*158.3*/("""let sumAjustes = 0;
		"""),_display_(/*159.4*/for(lista <- listaAjustes) yield /*159.30*/{_display_(Seq[Any](format.raw/*159.31*/("""
			"""),format.raw/*160.4*/("""tabla = document.getElementById("tablaPrincipal");
			newRow = tabla.insertRow(tabla.rows.length);
			cellsOfRow = tabla.rows[0].getElementsByTagName('td');
			for (var i = 0; i < cellsOfRow.length; i++) """),format.raw/*163.48*/("""{"""),format.raw/*163.49*/("""
				"""),format.raw/*164.5*/("""newRow.insertCell(i);
				if(i==0)"""),format.raw/*165.13*/("""{"""),format.raw/*165.14*/("""
					"""),format.raw/*166.6*/("""newRow.cells[i].style.textAlign="left";
					newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ"""),_display_(/*167.83*/lista/*167.88*/.get(5)),format.raw/*167.95*/(""": """),_display_(/*167.98*/lista/*167.103*/.get(6)),format.raw/*167.110*/("""</div>";
				"""),format.raw/*168.5*/("""}"""),format.raw/*168.6*/("""
				"""),format.raw/*169.5*/("""if(i==1)"""),format.raw/*169.13*/("""{"""),format.raw/*169.14*/("""
					"""),format.raw/*170.6*/("""newRow.cells[i].style.textAlign="left";
					newRow.cells[i].innerHTML=""""),_display_(/*171.34*/lista/*171.39*/.get(5)),format.raw/*171.46*/("""";
				"""),format.raw/*172.5*/("""}"""),format.raw/*172.6*/("""
				"""),format.raw/*173.5*/("""if(i==2)"""),format.raw/*173.13*/("""{"""),format.raw/*173.14*/("""
					"""),format.raw/*174.6*/("""newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ"""),_display_(/*174.83*/lista/*174.88*/.get(5)),format.raw/*174.95*/(""": """),_display_(/*174.98*/lista/*174.103*/.get(6)),format.raw/*174.110*/("""</div>"""),_display_(/*174.117*/lista/*174.122*/.get(6)),format.raw/*174.129*/("""";
				"""),format.raw/*175.5*/("""}"""),format.raw/*175.6*/("""
				"""),format.raw/*176.5*/("""if(i==cellsOfRow.length-1)"""),format.raw/*176.31*/("""{"""),format.raw/*176.32*/("""
					
					"""),format.raw/*178.6*/("""var aux = """"),_display_(/*178.18*/lista/*178.23*/.get(4)),format.raw/*178.30*/("""";
					if(aux.trim()==""||aux==null) aux = 0;
					sumAjustes = parseFloat(sumAjustes) + parseFloat(numero2.formatBack(aux));
					
					
					newRow.cells[i].innerHTML=""""),_display_(/*183.34*/lista/*183.39*/.get(4)),format.raw/*183.46*/("""";
				"""),format.raw/*184.5*/("""}"""),format.raw/*184.6*/("""
			"""),format.raw/*185.4*/("""}"""),format.raw/*185.5*/("""
		""")))}),format.raw/*186.4*/("""
		
		"""),format.raw/*188.3*/("""tabla = document.getElementById("tablaPrincipal");
		newRow = tabla.insertRow(tabla.rows.length);
		cellsOfRow = tabla.rows[0].getElementsByTagName('td');
		for (var i = 0; i < cellsOfRow.length; i++) """),format.raw/*191.47*/("""{"""),format.raw/*191.48*/("""
			"""),format.raw/*192.4*/("""newRow.insertCell(i);
			newRow.cells[i].style.backgroundColor="#eeeeee";
			if(i==0)"""),format.raw/*194.12*/("""{"""),format.raw/*194.13*/("""
				"""),format.raw/*195.5*/("""newRow.cells[i].style.textAlign="left";
				newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ</div>TOTALES (despues de ajustes)";
			"""),format.raw/*197.4*/("""}"""),format.raw/*197.5*/("""
			"""),format.raw/*198.4*/("""if(i==2)"""),format.raw/*198.12*/("""{"""),format.raw/*198.13*/("""
				"""),format.raw/*199.5*/("""newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ</div>";
			"""),format.raw/*200.4*/("""}"""),format.raw/*200.5*/("""
			"""),format.raw/*201.4*/("""if(i==cellsOfRow.length-1)"""),format.raw/*201.30*/("""{"""),format.raw/*201.31*/("""
				"""),format.raw/*202.5*/("""newRow.cells[i].innerHTML=numero0.format(parseFloat(primerTotal)+parseFloat(sumAjustes));
				newRow.cells[i].style.textAlign="right";
			"""),format.raw/*204.4*/("""}"""),format.raw/*204.5*/("""
		"""),format.raw/*205.3*/("""}"""),format.raw/*205.4*/("""
		
		"""),format.raw/*207.3*/("""tabla = document.getElementById("tablaPrincipal");
		for (var i = 2; i < tabla.rows.length; i++) """),format.raw/*208.47*/("""{"""),format.raw/*208.48*/(""" 
			"""),format.raw/*209.4*/("""tabla.rows[i].cells[3].style.backgroundColor="#eeeeee";
			
			tabla.rows[i].cells[tabla.rows[0].cells.length-6].style.backgroundColor="#eeeeee";
			tabla.rows[i].cells[tabla.rows[0].cells.length-1].style.backgroundColor="#eeeeee";
			tabla.rows[i].cells[tabla.rows[0].cells.length-5].style.textAlign="center";
			

			tabla.rows[i].cells[tabla.rows[0].cells.length-6].style.minWidth="80px";
			
			tabla.rows[i].cells[tabla.rows[0].cells.length-4].style.textAlign="right";
			tabla.rows[i].cells[tabla.rows[0].cells.length-4].style.minWidth="80px";
			tabla.rows[i].cells[tabla.rows[0].cells.length-3].style.textAlign="right";
			tabla.rows[i].cells[tabla.rows[0].cells.length-3].style.minWidth="80px";
			tabla.rows[i].cells[tabla.rows[0].cells.length-2].style.textAlign="right";
			tabla.rows[i].cells[tabla.rows[0].cells.length-2].style.minWidth="80px";
			tabla.rows[i].cells[tabla.rows[0].cells.length-1].style.textAlign="right";
			tabla.rows[i].cells[tabla.rows[0].cells.length-1].style.minWidth="80px";
		"""),format.raw/*226.3*/("""}"""),format.raw/*226.4*/("""
		
		
		"""),format.raw/*229.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*229.34*/("""{"""),format.raw/*229.35*/("""
		    	"""),format.raw/*230.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 2, "asc" ]],
				"scrollY": 550,
				"scrollX": true,
		    	"language": """),format.raw/*237.20*/("""{"""),format.raw/*237.21*/("""
		        	"""),format.raw/*238.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*239.11*/("""}"""),format.raw/*239.12*/("""
		  """),format.raw/*240.5*/("""}"""),format.raw/*240.6*/(""" """),format.raw/*240.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*243.2*/("""}"""),format.raw/*243.3*/(""");
	
	
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],bodega:tables.BodegaEmpresa,fechaDesde:String,fechaHasta:String,uf:Double,usd:Double,eur:Double,listaAjustes:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,bodega,fechaDesde,fechaHasta,uf,usd,eur,listaAjustes)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String,Double,Double,Double,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,bodega,fechaDesde,fechaHasta,uf,usd,eur,listaAjustes) => apply(mapDiccionario,mapPermiso,userMnu,lista,bodega,fechaDesde,fechaHasta,uf,usd,eur,listaAjustes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/reporteMovOdoValorizadoDetalle.scala.html
                  HASH: 80825f25c0e73b0f0a4612c2cb1039eff094acd5
                  MATRIX: 1120->1|1479->267|1511->274|1527->282|1566->284|1595->288|1663->336|1693->341|1737->365|1768->369|1845->420|1997->550|2030->556|3334->1833|3392->1875|3432->1877|3483->1901|3522->1902|3560->1912|3603->1928|3640->1949|3680->1951|3720->1963|3778->1994|3805->2000|3853->2017|3891->2027|3950->2042|3989->2051|4023->2058|4080->2089|4138->2131|4178->2133|4229->2157|4268->2158|4306->2168|4349->2184|4408->2227|4448->2229|4517->2271|4556->2272|4597->2285|4663->2324|4678->2330|4706->2337|4739->2343|4766->2349|4811->2375|4850->2376|4891->2389|4923->2394|4950->2400|4999->2418|5042->2430|5080->2440|5139->2455|5180->2465|5214->2472|5448->2679|5463->2685|5492->2693|5571->2745|5602->2755|5681->2807|5712->2817|5783->2861|5806->2863|5878->2908|5902->2911|5974->2956|5998->2959|6043->2974|6073->2977|6164->3040|6193->3041|6226->3047|6358->3150|6388->3151|6420->3155|6798->3504|6828->3505|6861->3510|6941->3562|6970->3563|7001->3566|7030->3567|7071->3580|7259->3739|7289->3740|7322->3745|7436->3830|7466->3831|7499->3836|7685->3994|7714->3995|7746->3999|7783->4007|7813->4008|7846->4013|7962->4101|7991->4102|8022->4105|8051->4106|8085->4112|8275->4273|8305->4274|8337->4278|8428->4340|8458->4341|8491->4346|8575->4401|8605->4402|8639->4408|8884->4625|8913->4626|8945->4630|8974->4631|9006->4635|9238->4838|9268->4839|9301->4844|9362->4877|9391->4878|9450->4908|9480->4909|9513->4914|9591->4964|9620->4965|9652->4969|9682->4970|9715->4975|9838->5070|9867->5071|9899->5075|9948->5096|9977->5097|10011->5103|10061->5126|10104->5152|10144->5153|10176->5157|10409->5361|10439->5362|10472->5367|10535->5401|10565->5402|10599->5408|10749->5530|10764->5535|10793->5542|10824->5545|10840->5550|10870->5557|10911->5570|10940->5571|10973->5576|11010->5584|11040->5585|11074->5591|11175->5664|11190->5669|11219->5676|11254->5683|11283->5684|11316->5689|11353->5697|11383->5698|11417->5704|11522->5781|11537->5786|11566->5793|11597->5796|11613->5801|11643->5808|11679->5815|11695->5820|11725->5827|11760->5834|11789->5835|11822->5840|11877->5866|11907->5867|11947->5879|11987->5891|12002->5896|12031->5903|12231->6075|12246->6080|12275->6087|12310->6094|12339->6095|12371->6099|12400->6100|12435->6104|12469->6110|12699->6311|12729->6312|12761->6316|12875->6401|12905->6402|12938->6407|13149->6590|13178->6591|13210->6595|13247->6603|13277->6604|13310->6609|13449->6720|13478->6721|13510->6725|13565->6751|13595->6752|13628->6757|13794->6895|13823->6896|13854->6899|13883->6900|13917->6906|14043->7003|14073->7004|14106->7009|15148->8023|15177->8024|15214->8033|15274->8064|15304->8065|15340->8073|15549->8253|15579->8254|15620->8266|15727->8344|15757->8345|15790->8350|15819->8351|15848->8352|15949->8425|15978->8426
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|45->14|82->51|82->51|82->51|83->52|83->52|84->53|85->54|85->54|85->54|86->55|86->55|86->55|87->56|88->57|89->58|90->59|91->60|93->62|93->62|93->62|94->63|94->63|95->64|96->65|96->65|96->65|97->66|97->66|98->67|98->67|98->67|98->67|98->67|98->67|99->68|99->68|100->69|100->69|100->69|101->70|102->71|103->72|104->73|105->74|106->75|114->83|114->83|114->83|115->84|115->84|116->85|116->85|117->86|117->86|118->87|118->87|119->88|119->88|123->92|126->95|128->97|128->97|130->99|132->101|132->101|133->102|139->108|139->108|140->109|141->110|141->110|142->111|142->111|147->116|150->119|150->119|151->120|153->122|153->122|154->123|156->125|156->125|157->126|157->126|157->126|158->127|159->128|159->128|160->129|160->129|162->131|166->135|166->135|167->136|168->137|168->137|169->138|169->138|169->138|170->139|174->143|174->143|175->144|175->144|176->145|178->147|178->147|179->148|180->149|180->149|180->149|180->149|181->150|182->151|182->151|182->151|182->151|183->152|185->154|185->154|186->155|187->156|187->156|189->158|190->159|190->159|190->159|191->160|194->163|194->163|195->164|196->165|196->165|197->166|198->167|198->167|198->167|198->167|198->167|198->167|199->168|199->168|200->169|200->169|200->169|201->170|202->171|202->171|202->171|203->172|203->172|204->173|204->173|204->173|205->174|205->174|205->174|205->174|205->174|205->174|205->174|205->174|205->174|205->174|206->175|206->175|207->176|207->176|207->176|209->178|209->178|209->178|209->178|214->183|214->183|214->183|215->184|215->184|216->185|216->185|217->186|219->188|222->191|222->191|223->192|225->194|225->194|226->195|228->197|228->197|229->198|229->198|229->198|230->199|231->200|231->200|232->201|232->201|232->201|233->202|235->204|235->204|236->205|236->205|238->207|239->208|239->208|240->209|257->226|257->226|260->229|260->229|260->229|261->230|268->237|268->237|269->238|270->239|270->239|271->240|271->240|271->240|274->243|274->243
                  -- GENERATED --
              */
          