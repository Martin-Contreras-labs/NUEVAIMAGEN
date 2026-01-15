
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

object reporteMovOdoCantDetalle extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], bodega: tables.BodegaEmpresa, fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalEquipoDescripcion()),format.raw/*8.27*/("""
	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "DETALLE ODO MOVIMIENTO",mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase)),format.raw/*11.131*/("""

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
								"""),_display_(if(index1 < 2)/*51.24*/{_display_(Seq[Any](format.raw/*51.25*/("""
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
								"""),_display_(if(index1 > 1)/*62.24*/{_display_(Seq[Any](format.raw/*62.25*/("""
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

	
	<form id="excel" class="formulario" method="post" action="/reporteMovOdoCantDetalleExcel/">
		<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*82.56*/bodega/*82.62*/.getId()),format.raw/*82.70*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*83.50*/fechaDesde),format.raw/*83.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*84.50*/fechaHasta),format.raw/*84.60*/("""">
	</form>


""")))}),format.raw/*88.2*/("""


"""),format.raw/*91.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*93.31*/("""{"""),format.raw/*93.32*/("""
		
		"""),format.raw/*95.3*/("""var tabla = document.getElementById("tablaPrincipal");
		
		for (var i = 2; i < tabla.rows.length; i++) """),format.raw/*97.47*/("""{"""),format.raw/*97.48*/("""
			"""),format.raw/*98.4*/("""tabla.rows[i].cells[0].style.textAlign="left";
			tabla.rows[i].cells[1].style.textAlign="center";
			tabla.rows[i].cells[2].style.textAlign="left";
			tabla.rows[i].cells[3].style.textAlign="center";
			tabla.rows[i].cells[0].style.minWidth="150px";
			tabla.rows[i].cells[2].style.minWidth="300px";
			for(var k=4;k<tabla.rows[0].cells.length;k++)"""),format.raw/*104.49*/("""{"""),format.raw/*104.50*/("""
				"""),format.raw/*105.5*/("""tabla.rows[i].cells[k].style.textAlign="center";
			"""),format.raw/*106.4*/("""}"""),format.raw/*106.5*/("""
		"""),format.raw/*107.3*/("""}"""),format.raw/*107.4*/("""
		
		"""),format.raw/*109.3*/("""for (var i = 2; i < tabla.rows.length; i++) """),format.raw/*109.47*/("""{"""),format.raw/*109.48*/(""" 
			"""),format.raw/*110.4*/("""tabla.rows[i].cells[3].style.backgroundColor="#eeeeee";
			tabla.rows[i].cells[tabla.rows[0].cells.length-1].style.backgroundColor="#eeeeee";
			tabla.rows[i].cells[tabla.rows[0].cells.length-1].style.textAlign="right";
			tabla.rows[i].cells[tabla.rows[0].cells.length-1].style.minWidth="80px";
		"""),format.raw/*114.3*/("""}"""),format.raw/*114.4*/("""
		
		"""),format.raw/*116.3*/("""var newRow = tabla.insertRow(tabla.rows.length);
		var cellsOfRow = tabla.rows[0].getElementsByTagName('td');
		
		for (var i = 0; i < cellsOfRow.length; i++) """),format.raw/*119.47*/("""{"""),format.raw/*119.48*/(""" 
			"""),format.raw/*120.4*/("""newRow.insertCell(i);
			newRow.cells[i].style.backgroundColor="#eeeeee";
			if(i==0)"""),format.raw/*122.12*/("""{"""),format.raw/*122.13*/("""
				"""),format.raw/*123.5*/("""newRow.cells[i].style.textAlign="left";
				newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ</div>TOTALES";
			"""),format.raw/*125.4*/("""}"""),format.raw/*125.5*/("""
			"""),format.raw/*126.4*/("""if(i==2)"""),format.raw/*126.12*/("""{"""),format.raw/*126.13*/("""
				"""),format.raw/*127.5*/("""newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ</div>";
			"""),format.raw/*128.4*/("""}"""),format.raw/*128.5*/("""
		"""),format.raw/*129.3*/("""}"""),format.raw/*129.4*/("""
		
		"""),format.raw/*131.3*/("""var numero2 = new DecimalFormat("#,##0.00");
		var numero0 = new DecimalFormat("#,##0");
		
		for (var i = 4; i < cellsOfRow.length; i++) """),format.raw/*134.47*/("""{"""),format.raw/*134.48*/("""
			"""),format.raw/*135.4*/("""var val = 0;
			for (var j = 2; j < tabla.rows.length-1; j++) """),format.raw/*136.50*/("""{"""),format.raw/*136.51*/("""
				"""),format.raw/*137.5*/("""var cellsOfRow = tabla.rows[j].getElementsByTagName('td');
				var aux = cellsOfRow[i].innerHTML;
				if(aux.trim()==""||aux==null) aux = 0;
				var val = parseFloat(val) + parseFloat(numero2.formatBack(aux));
			"""),format.raw/*141.4*/("""}"""),format.raw/*141.5*/("""
			"""),format.raw/*142.4*/("""var cellsOfRow = tabla.rows[tabla.rows.length-1].getElementsByTagName('td');
			
			newRow.cells[i].style.textAlign="center";
			cellsOfRow[i].innerHTML = numero2.format(val);
			if(i==cellsOfRow.length-1)"""),format.raw/*146.30*/("""{"""),format.raw/*146.31*/("""
				"""),format.raw/*147.5*/("""newRow.cells[i].style.textAlign="right";
			"""),format.raw/*148.4*/("""}"""),format.raw/*148.5*/("""
			
		"""),format.raw/*150.3*/("""}"""),format.raw/*150.4*/("""
		
		"""),format.raw/*152.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*152.34*/("""{"""),format.raw/*152.35*/("""
		    	"""),format.raw/*153.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 2, "asc" ]],
				"scrollY": 550,
				"scrollX": true,
		    	"language": """),format.raw/*160.20*/("""{"""),format.raw/*160.21*/("""
		        	"""),format.raw/*161.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*162.11*/("""}"""),format.raw/*162.12*/("""
		  """),format.raw/*163.5*/("""}"""),format.raw/*163.6*/(""" """),format.raw/*163.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*166.2*/("""}"""),format.raw/*166.3*/(""");
	
	
	
	
	
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
                  SOURCE: app/viewsMnuOdo/reporteMovOdoCantDetalle.scala.html
                  HASH: 151211ece4249cfe021dd8e774d470041bb62d92
                  MATRIX: 1073->1|1360->195|1392->202|1408->210|1447->212|1476->216|1544->264|1574->269|1618->293|1649->297|1726->348|1875->475|1908->481|3212->1758|3270->1800|3310->1802|3361->1826|3400->1827|3438->1837|3481->1853|3518->1874|3558->1876|3598->1888|3656->1919|3683->1925|3731->1942|3769->1952|3828->1967|3867->1976|3901->1983|3958->2014|4016->2056|4056->2058|4107->2082|4146->2083|4184->2093|4227->2109|4286->2152|4326->2154|4395->2196|4434->2197|4475->2210|4541->2249|4556->2255|4584->2262|4617->2268|4644->2274|4689->2300|4728->2301|4769->2314|4801->2319|4828->2325|4877->2343|4920->2355|4958->2365|5017->2380|5058->2390|5092->2397|5320->2598|5335->2604|5364->2612|5443->2664|5474->2674|5553->2726|5584->2736|5629->2751|5659->2754|5750->2817|5779->2818|5812->2824|5944->2928|5973->2929|6004->2933|6382->3282|6412->3283|6445->3288|6525->3340|6554->3341|6585->3344|6614->3345|6648->3351|6721->3395|6751->3396|6784->3401|7110->3699|7139->3700|7173->3706|7361->3865|7391->3866|7424->3871|7538->3956|7568->3957|7601->3962|7768->4101|7797->4102|7829->4106|7866->4114|7896->4115|7929->4120|8045->4208|8074->4209|8105->4212|8134->4213|8168->4219|8335->4357|8365->4358|8397->4362|8488->4424|8518->4425|8551->4430|8792->4643|8821->4644|8853->4648|9087->4853|9117->4854|9150->4859|9222->4903|9251->4904|9286->4911|9315->4912|9349->4918|9409->4949|9439->4950|9475->4958|9684->5138|9714->5139|9755->5151|9862->5229|9892->5230|9925->5235|9954->5236|9983->5237|10084->5310|10113->5311
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|41->10|42->11|42->11|44->13|81->50|81->50|81->50|82->51|82->51|83->52|84->53|84->53|84->53|85->54|85->54|85->54|86->55|87->56|88->57|89->58|90->59|92->61|92->61|92->61|93->62|93->62|94->63|95->64|95->64|95->64|96->65|96->65|97->66|97->66|97->66|97->66|97->66|97->66|98->67|98->67|99->68|99->68|99->68|100->69|101->70|102->71|103->72|104->73|105->74|113->82|113->82|113->82|114->83|114->83|115->84|115->84|119->88|122->91|124->93|124->93|126->95|128->97|128->97|129->98|135->104|135->104|136->105|137->106|137->106|138->107|138->107|140->109|140->109|140->109|141->110|145->114|145->114|147->116|150->119|150->119|151->120|153->122|153->122|154->123|156->125|156->125|157->126|157->126|157->126|158->127|159->128|159->128|160->129|160->129|162->131|165->134|165->134|166->135|167->136|167->136|168->137|172->141|172->141|173->142|177->146|177->146|178->147|179->148|179->148|181->150|181->150|183->152|183->152|183->152|184->153|191->160|191->160|192->161|193->162|193->162|194->163|194->163|194->163|197->166|197->166
                  -- GENERATED --
              */
          