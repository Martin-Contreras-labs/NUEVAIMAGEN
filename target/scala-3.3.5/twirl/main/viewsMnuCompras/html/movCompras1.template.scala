
package viewsMnuCompras.html

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

object movCompras1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template11[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Long,tables.Moneda,Double,Double,Double,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, lista: List[List[String]], fechaDesde: String, fechaHasta: String, id_proveedor: Long, moneda: tables.Moneda, uf: Double, usd: Double, eur: Double):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""    	
"""),_display_(/*3.2*/main("")/*3.10*/ {_display_(Seq[Any](format.raw/*3.12*/("""

	"""),_display_(/*5.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*5.51*/("""
	
	"""),_display_(/*7.3*/modalEquipoDescripcion()),format.raw/*7.27*/("""
	
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "DETALLE MOVIMIENTOS DE COMPRAS","MONEDA "+moneda.getNombre().toUpperCase())),format.raw/*10.108*/("""

				"""),format.raw/*12.5*/("""<div class="noprint">
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
					        """),_display_(/*47.15*/for((nivel1,index1) <- lista.zipWithIndex) yield /*47.57*/ {_display_(Seq[Any](format.raw/*47.59*/("""
								"""),_display_(if(index1 > 0 && index1 < 4)/*48.38*/{_display_(Seq[Any](format.raw/*48.39*/("""
									"""),format.raw/*49.10*/("""<TR>
										"""),_display_(/*50.12*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*50.55*/ {_display_(Seq[Any](format.raw/*50.57*/("""
											"""),format.raw/*51.12*/("""<th style="textAlign: center">"""),_display_(/*51.43*/nivel2),format.raw/*51.49*/("""</th>
										""")))}),format.raw/*52.12*/("""
									"""),format.raw/*53.10*/("""</TR>
								""")))} else {null} ),format.raw/*54.10*/("""
							""")))}),format.raw/*55.9*/("""
						"""),format.raw/*56.7*/("""</thead>
						<tbody>
							"""),_display_(/*58.9*/for((nivel1,index1) <- lista.zipWithIndex) yield /*58.51*/ {_display_(Seq[Any](format.raw/*58.53*/("""
								"""),_display_(if(index1 > 3)/*59.24*/{_display_(Seq[Any](format.raw/*59.25*/("""
									"""),format.raw/*60.10*/("""<TR>
										"""),_display_(/*61.12*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*61.55*/ {_display_(Seq[Any](format.raw/*61.57*/("""
											"""),_display_(if(index2 < 4)/*62.27*/{_display_(Seq[Any](format.raw/*62.28*/("""
												"""),format.raw/*63.13*/("""<td>"""),_display_(/*63.18*/nivel2),format.raw/*63.24*/("""</td>
											""")))}else if(index2 == 3)/*64.33*/{_display_(Seq[Any](format.raw/*64.34*/("""
												"""),format.raw/*65.13*/("""<td>
													<div style="display:none">"""),_display_(/*66.41*/utilities/*66.50*/.Fechas.AAMMDD(nivel2)),format.raw/*66.72*/("""</div>
													"""),_display_(/*67.15*/nivel2),format.raw/*67.21*/("""
												"""),format.raw/*68.13*/("""</td>
											""")))}else/*69.17*/{_display_(Seq[Any](format.raw/*69.18*/("""
												"""),_display_(if( index2 == 6 || index2 == nivel1.size-3)/*70.57*/{_display_(Seq[Any](format.raw/*70.58*/("""
													"""),format.raw/*71.14*/("""<td style="background-color: #eeeeee">
														"""),_display_(/*72.16*/nivel2),format.raw/*72.22*/("""
													"""),format.raw/*73.14*/("""</td>
												""")))}else/*74.18*/{_display_(Seq[Any](format.raw/*74.19*/("""
													"""),format.raw/*75.14*/("""<td>
														"""),_display_(/*76.16*/nivel2),format.raw/*76.22*/("""
													"""),format.raw/*77.14*/("""</td>
												""")))}),format.raw/*78.14*/("""
											""")))}),format.raw/*79.13*/("""
										""")))}),format.raw/*80.12*/("""
									"""),format.raw/*81.10*/("""</TR>
								""")))} else {null} ),format.raw/*82.10*/("""
							 """)))}),format.raw/*83.10*/("""
						"""),format.raw/*84.7*/("""</tbody>
					</table>
				</div>
			</div>

	
	<form id="excel" class="formulario" method="post" action="/routes2/movCompras1Excel/">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*91.50*/fechaDesde),format.raw/*91.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*92.50*/fechaHasta),format.raw/*92.60*/("""">
		<input type="hidden" id="id_proveedor" name="id_proveedor" value=""""),_display_(/*93.70*/id_proveedor),format.raw/*93.82*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*94.42*/uf),format.raw/*94.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*95.43*/usd),format.raw/*95.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*96.43*/eur),format.raw/*96.46*/("""">
	</form>


""")))}),format.raw/*100.2*/("""


"""),format.raw/*103.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*105.31*/("""{"""),format.raw/*105.32*/("""
		
		"""),format.raw/*107.3*/("""var tabla = document.getElementById("tablaPrincipal");

		for (var i = 3; i < tabla.rows.length; i++) """),format.raw/*109.47*/("""{"""),format.raw/*109.48*/("""
			"""),format.raw/*110.4*/("""tabla.rows[i].cells[0].style.textAlign="center";
			tabla.rows[i].cells[1].style.textAlign="left";
			for(var k=2;k<tabla.rows[0].cells.length;k++)"""),format.raw/*112.49*/("""{"""),format.raw/*112.50*/("""
				"""),format.raw/*113.5*/("""tabla.rows[i].cells[k].style.textAlign="center";
			"""),format.raw/*114.4*/("""}"""),format.raw/*114.5*/("""
		"""),format.raw/*115.3*/("""}"""),format.raw/*115.4*/("""

		"""),format.raw/*117.3*/("""let footer = tabla.createTFoot();
		let newRow = footer.insertRow(0);
		let cellsOfRow = tabla.rows[0].getElementsByTagName('th');
		for (var i = 0; i < cellsOfRow.length; i++) """),format.raw/*120.47*/("""{"""),format.raw/*120.48*/("""
			"""),format.raw/*121.4*/("""newRow.insertCell(i);
			newRow.cells[i].style.backgroundColor="#eeeeee";
			if(i<2)"""),format.raw/*123.11*/("""{"""),format.raw/*123.12*/("""
				"""),format.raw/*124.5*/("""newRow.cells[i].style.textAlign="left";
			"""),format.raw/*125.4*/("""}"""),format.raw/*125.5*/("""else """),format.raw/*125.10*/("""{"""),format.raw/*125.11*/("""
				"""),format.raw/*126.5*/("""newRow.cells[i].style.textAlign="center";
			"""),format.raw/*127.4*/("""}"""),format.raw/*127.5*/("""
			"""),format.raw/*128.4*/("""if(i==0)"""),format.raw/*128.12*/("""{"""),format.raw/*128.13*/("""
				"""),format.raw/*129.5*/("""newRow.cells[i].style.textAlign="left";
				newRow.cells[i].innerHTML="TOTALES";
			"""),format.raw/*131.4*/("""}"""),format.raw/*131.5*/("""
			"""),format.raw/*132.4*/("""if(i==1)"""),format.raw/*132.12*/("""{"""),format.raw/*132.13*/("""
				"""),format.raw/*133.5*/("""newRow.cells[i].style.textAlign="left";
				newRow.cells[i].innerHTML="POR COLUMNA";
			"""),format.raw/*135.4*/("""}"""),format.raw/*135.5*/("""
			"""),format.raw/*136.4*/("""if(i==2)"""),format.raw/*136.12*/("""{"""),format.raw/*136.13*/("""
				"""),format.raw/*137.5*/("""newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ</div>";
			"""),format.raw/*138.4*/("""}"""),format.raw/*138.5*/("""
		"""),format.raw/*139.3*/("""}"""),format.raw/*139.4*/("""

		"""),format.raw/*141.3*/("""newRow = footer.insertRow(1);
		cellsOfRow = tabla.rows[0].getElementsByTagName('th');
		for (var i = 0; i < cellsOfRow.length; i++) """),format.raw/*143.47*/("""{"""),format.raw/*143.48*/("""
			"""),format.raw/*144.4*/("""newRow.insertCell(i);
			newRow.cells[i].style.backgroundColor="#eeeeee";
			if(i<2)"""),format.raw/*146.11*/("""{"""),format.raw/*146.12*/("""
				"""),format.raw/*147.5*/("""newRow.cells[i].style.textAlign="left";
			"""),format.raw/*148.4*/("""}"""),format.raw/*148.5*/("""else """),format.raw/*148.10*/("""{"""),format.raw/*148.11*/("""
				"""),format.raw/*149.5*/("""newRow.cells[i].style.textAlign="center";
			"""),format.raw/*150.4*/("""}"""),format.raw/*150.5*/("""
			"""),format.raw/*151.4*/("""if(i==0)"""),format.raw/*151.12*/("""{"""),format.raw/*151.13*/("""
				"""),format.raw/*152.5*/("""newRow.cells[i].style.textAlign="left";
				newRow.cells[i].innerHTML="TOTAL";
			"""),format.raw/*154.4*/("""}"""),format.raw/*154.5*/("""
			"""),format.raw/*155.4*/("""if(i==1)"""),format.raw/*155.12*/("""{"""),format.raw/*155.13*/("""
				"""),format.raw/*156.5*/("""newRow.cells[i].style.textAlign="left";
				newRow.cells[i].innerHTML="PRECIOS";
			"""),format.raw/*158.4*/("""}"""),format.raw/*158.5*/("""
			"""),format.raw/*159.4*/("""if(i==2)"""),format.raw/*159.12*/("""{"""),format.raw/*159.13*/("""
				"""),format.raw/*160.5*/("""newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ</div>";
			"""),format.raw/*161.4*/("""}"""),format.raw/*161.5*/("""
		"""),format.raw/*162.3*/("""}"""),format.raw/*162.4*/("""

		"""),format.raw/*164.3*/("""newRow = footer.insertRow(1);
		cellsOfRow = tabla.rows[0].getElementsByTagName('th');
		for (var i = 0; i < cellsOfRow.length; i++) """),format.raw/*166.47*/("""{"""),format.raw/*166.48*/("""
			"""),format.raw/*167.4*/("""newRow.insertCell(i);
			newRow.cells[i].style.backgroundColor="#eeeeee";
			if(i<2)"""),format.raw/*169.11*/("""{"""),format.raw/*169.12*/("""
				"""),format.raw/*170.5*/("""newRow.cells[i].style.textAlign="left";
			"""),format.raw/*171.4*/("""}"""),format.raw/*171.5*/("""else """),format.raw/*171.10*/("""{"""),format.raw/*171.11*/("""
				"""),format.raw/*172.5*/("""newRow.cells[i].style.textAlign="center";
			"""),format.raw/*173.4*/("""}"""),format.raw/*173.5*/("""
			"""),format.raw/*174.4*/("""if(i==0)"""),format.raw/*174.12*/("""{"""),format.raw/*174.13*/("""
				"""),format.raw/*175.5*/("""newRow.cells[i].style.textAlign="left";
				newRow.cells[i].innerHTML="TOTAL";
			"""),format.raw/*177.4*/("""}"""),format.raw/*177.5*/("""
			"""),format.raw/*178.4*/("""if(i==1)"""),format.raw/*178.12*/("""{"""),format.raw/*178.13*/("""
				"""),format.raw/*179.5*/("""newRow.cells[i].style.textAlign="left";
				newRow.cells[i].innerHTML="KG";
			"""),format.raw/*181.4*/("""}"""),format.raw/*181.5*/("""
			"""),format.raw/*182.4*/("""if(i==2)"""),format.raw/*182.12*/("""{"""),format.raw/*182.13*/("""
				"""),format.raw/*183.5*/("""newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ</div>";
			"""),format.raw/*184.4*/("""}"""),format.raw/*184.5*/("""
		"""),format.raw/*185.3*/("""}"""),format.raw/*185.4*/("""

		"""),format.raw/*187.3*/("""var numero0 = new DecimalFormat("#,##0");
		for (var i = 6; i < cellsOfRow.length; i++) """),format.raw/*188.47*/("""{"""),format.raw/*188.48*/("""
			"""),format.raw/*189.4*/("""let cant = 0;
			let kg = 0;
			let precio = 0;
			for (var j = 3; j < tabla.rows.length-3; j++) """),format.raw/*192.50*/("""{"""),format.raw/*192.51*/("""
				"""),format.raw/*193.5*/("""cellsOfRow = tabla.rows[j].getElementsByTagName('td');
				let auxCant = cellsOfRow[i].innerHTML;

				let auxPrecio = cellsOfRow[5].innerHTML;
				auxPrecio = auxPrecio.replace(",", "").replace(",", "").replace(",", "");
				auxCant = auxCant.replace(",", "").replace(",", "").replace(",", "");
				cant = parseFloat(cant) + parseFloat(auxCant);
				precio = parseFloat(precio) + parseFloat(auxPrecio * parseFloat(auxCant));

				let auxKg = cellsOfRow[4].innerHTML;
				auxKg = auxKg.replace(",", "").replace(",", "").replace(",", "");
				kg = parseFloat(kg) + parseFloat(auxKg * parseFloat(auxCant));
			"""),format.raw/*205.4*/("""}"""),format.raw/*205.5*/("""
			"""),format.raw/*206.4*/("""//if(i != cellsOfRow.length-2 && i != cellsOfRow.length-1)"""),format.raw/*206.62*/("""{"""),format.raw/*206.63*/("""
				"""),format.raw/*207.5*/("""cellsOfRow = tabla.rows[tabla.rows.length-3].getElementsByTagName('td');
				cellsOfRow[i].innerHTML = numero0.format(cant);
			//"""),format.raw/*209.6*/("""}"""),format.raw/*209.7*/("""

			"""),format.raw/*211.4*/("""if(i != cellsOfRow.length-2 && i != cellsOfRow.length-1)"""),format.raw/*211.60*/("""{"""),format.raw/*211.61*/("""
				"""),format.raw/*212.5*/("""cellsOfRow = tabla.rows[tabla.rows.length-2].getElementsByTagName('td');
				cellsOfRow[i].innerHTML = numero0.format(kg);
			"""),format.raw/*214.4*/("""}"""),format.raw/*214.5*/("""

			"""),format.raw/*216.4*/("""if(i != cellsOfRow.length-2 && i != cellsOfRow.length-1)"""),format.raw/*216.60*/("""{"""),format.raw/*216.61*/("""
				"""),format.raw/*217.5*/("""cellsOfRow = tabla.rows[tabla.rows.length-1].getElementsByTagName('td');
				cellsOfRow[i].innerHTML = formatStandar(precio,""""),_display_(/*218.54*/moneda/*218.60*/.getNumeroDecimales()),format.raw/*218.81*/("""");
			"""),format.raw/*219.4*/("""}"""),format.raw/*219.5*/("""

		"""),format.raw/*221.3*/("""}"""),format.raw/*221.4*/("""

		"""),format.raw/*223.3*/("""let col = [];
		for(let i = 4; i < """),_display_(/*224.23*/lista/*224.28*/.get(0).size),format.raw/*224.40*/("""; i++) """),format.raw/*224.47*/("""{"""),format.raw/*224.48*/("""
			"""),format.raw/*225.4*/("""col.push(i);
		"""),format.raw/*226.3*/("""}"""),format.raw/*226.4*/("""


		"""),format.raw/*229.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*229.34*/("""{"""),format.raw/*229.35*/("""
		    	"""),format.raw/*230.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [],
				"scrollX": true,
			"columnDefs": [
				"""),format.raw/*237.5*/("""{"""),format.raw/*237.6*/("""
					"""),format.raw/*238.6*/(""""targets": col,
					"orderDataType": "dom-text",
					"type": "num-fmt",
					"render": function(data, type, row) """),format.raw/*241.42*/("""{"""),format.raw/*241.43*/("""
						"""),format.raw/*242.7*/("""if (type === 'sort') """),format.raw/*242.28*/("""{"""),format.raw/*242.29*/("""
							"""),format.raw/*243.8*/("""// Remover cualquier formato de número (comas, espacios)
							return parseFloat(data.toString().replace(/[^\d.-]/g, '')) || 0;
						"""),format.raw/*245.7*/("""}"""),format.raw/*245.8*/("""
						"""),format.raw/*246.7*/("""return data;
					"""),format.raw/*247.6*/("""}"""),format.raw/*247.7*/("""
				"""),format.raw/*248.5*/("""}"""),format.raw/*248.6*/("""
			"""),format.raw/*249.4*/("""],



			"language": """),format.raw/*253.16*/("""{"""),format.raw/*253.17*/("""
		        	"""),format.raw/*254.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*255.11*/("""}"""),format.raw/*255.12*/("""
		"""),format.raw/*256.3*/("""}"""),format.raw/*256.4*/(""" """),format.raw/*256.5*/(""");

		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*259.2*/("""}"""),format.raw/*259.3*/(""");
	
	
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],fechaDesde:String,fechaHasta:String,id_proveedor:Long,moneda:tables.Moneda,uf:Double,usd:Double,eur:Double): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,fechaDesde,fechaHasta,id_proveedor,moneda,uf,usd,eur)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Long,tables.Moneda,Double,Double,Double) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,fechaDesde,fechaHasta,id_proveedor,moneda,uf,usd,eur) => apply(mapDiccionario,mapPermiso,userMnu,lista,fechaDesde,fechaHasta,id_proveedor,moneda,uf,usd,eur)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCompras/movCompras1.scala.html
                  HASH: 050cee3ae23e56a7b695c1be5343969b2fc643f4
                  MATRIX: 1084->1|1422->246|1454->253|1470->261|1509->263|1538->267|1606->315|1636->320|1680->344|1710->348|1787->399|1913->503|1946->509|3238->1774|3296->1816|3336->1818|3401->1856|3440->1857|3478->1867|3521->1883|3580->1926|3620->1928|3660->1940|3718->1971|3745->1977|3793->1994|3831->2004|3890->2019|3929->2028|3963->2035|4020->2066|4078->2108|4118->2110|4169->2134|4208->2135|4246->2145|4289->2161|4348->2204|4388->2206|4442->2233|4481->2234|4522->2247|4554->2252|4581->2258|4638->2296|4677->2297|4718->2310|4790->2355|4808->2364|4851->2386|4899->2407|4926->2413|4967->2426|5008->2448|5047->2449|5131->2506|5170->2507|5212->2521|5293->2575|5320->2581|5362->2595|5404->2618|5443->2619|5485->2633|5532->2653|5559->2659|5601->2673|5651->2692|5695->2705|5738->2717|5776->2727|5835->2742|5876->2752|5910->2759|6121->2943|6152->2953|6231->3005|6262->3015|6361->3087|6394->3099|6465->3143|6488->3145|6560->3190|6584->3193|6656->3238|6680->3241|6726->3256|6757->3259|6849->3322|6879->3323|6913->3329|7044->3431|7074->3432|7106->3436|7282->3583|7312->3584|7345->3589|7425->3641|7454->3642|7485->3645|7514->3646|7546->3650|7752->3827|7782->3828|7814->3832|7927->3916|7957->3917|7990->3922|8061->3965|8090->3966|8124->3971|8154->3972|8187->3977|8260->4022|8289->4023|8321->4027|8358->4035|8388->4036|8421->4041|8533->4125|8562->4126|8594->4130|8631->4138|8661->4139|8694->4144|8810->4232|8839->4233|8871->4237|8908->4245|8938->4246|8971->4251|9087->4339|9116->4340|9147->4343|9176->4344|9208->4348|9370->4481|9400->4482|9432->4486|9545->4570|9575->4571|9608->4576|9679->4619|9708->4620|9742->4625|9772->4626|9805->4631|9878->4676|9907->4677|9939->4681|9976->4689|10006->4690|10039->4695|10149->4777|10178->4778|10210->4782|10247->4790|10277->4791|10310->4796|10422->4880|10451->4881|10483->4885|10520->4893|10550->4894|10583->4899|10699->4987|10728->4988|10759->4991|10788->4992|10820->4996|10982->5129|11012->5130|11044->5134|11157->5218|11187->5219|11220->5224|11291->5267|11320->5268|11354->5273|11384->5274|11417->5279|11490->5324|11519->5325|11551->5329|11588->5337|11618->5338|11651->5343|11761->5425|11790->5426|11822->5430|11859->5438|11889->5439|11922->5444|12029->5523|12058->5524|12090->5528|12127->5536|12157->5537|12190->5542|12306->5630|12335->5631|12366->5634|12395->5635|12427->5639|12544->5727|12574->5728|12606->5732|12732->5829|12762->5830|12795->5835|13431->6443|13460->6444|13492->6448|13579->6506|13609->6507|13642->6512|13800->6642|13829->6643|13862->6648|13947->6704|13977->6705|14010->6710|14164->6836|14193->6837|14226->6842|14311->6898|14341->6899|14374->6904|14528->7030|14544->7036|14587->7057|14622->7064|14651->7065|14683->7069|14712->7070|14744->7074|14808->7110|14823->7115|14857->7127|14893->7134|14923->7135|14955->7139|14998->7154|15027->7155|15060->7160|15120->7191|15150->7192|15186->7200|15366->7352|15395->7353|15429->7359|15573->7474|15603->7475|15638->7482|15688->7503|15718->7504|15754->7512|15917->7647|15946->7648|15981->7655|16027->7673|16056->7674|16089->7679|16118->7680|16150->7684|16200->7705|16230->7706|16271->7718|16378->7796|16408->7797|16439->7800|16468->7801|16497->7802|16597->7874|16626->7875
                  LINES: 28->1|33->2|34->3|34->3|34->3|36->5|36->5|38->7|38->7|40->9|41->10|41->10|43->12|78->47|78->47|78->47|79->48|79->48|80->49|81->50|81->50|81->50|82->51|82->51|82->51|83->52|84->53|85->54|86->55|87->56|89->58|89->58|89->58|90->59|90->59|91->60|92->61|92->61|92->61|93->62|93->62|94->63|94->63|94->63|95->64|95->64|96->65|97->66|97->66|97->66|98->67|98->67|99->68|100->69|100->69|101->70|101->70|102->71|103->72|103->72|104->73|105->74|105->74|106->75|107->76|107->76|108->77|109->78|110->79|111->80|112->81|113->82|114->83|115->84|122->91|122->91|123->92|123->92|124->93|124->93|125->94|125->94|126->95|126->95|127->96|127->96|131->100|134->103|136->105|136->105|138->107|140->109|140->109|141->110|143->112|143->112|144->113|145->114|145->114|146->115|146->115|148->117|151->120|151->120|152->121|154->123|154->123|155->124|156->125|156->125|156->125|156->125|157->126|158->127|158->127|159->128|159->128|159->128|160->129|162->131|162->131|163->132|163->132|163->132|164->133|166->135|166->135|167->136|167->136|167->136|168->137|169->138|169->138|170->139|170->139|172->141|174->143|174->143|175->144|177->146|177->146|178->147|179->148|179->148|179->148|179->148|180->149|181->150|181->150|182->151|182->151|182->151|183->152|185->154|185->154|186->155|186->155|186->155|187->156|189->158|189->158|190->159|190->159|190->159|191->160|192->161|192->161|193->162|193->162|195->164|197->166|197->166|198->167|200->169|200->169|201->170|202->171|202->171|202->171|202->171|203->172|204->173|204->173|205->174|205->174|205->174|206->175|208->177|208->177|209->178|209->178|209->178|210->179|212->181|212->181|213->182|213->182|213->182|214->183|215->184|215->184|216->185|216->185|218->187|219->188|219->188|220->189|223->192|223->192|224->193|236->205|236->205|237->206|237->206|237->206|238->207|240->209|240->209|242->211|242->211|242->211|243->212|245->214|245->214|247->216|247->216|247->216|248->217|249->218|249->218|249->218|250->219|250->219|252->221|252->221|254->223|255->224|255->224|255->224|255->224|255->224|256->225|257->226|257->226|260->229|260->229|260->229|261->230|268->237|268->237|269->238|272->241|272->241|273->242|273->242|273->242|274->243|276->245|276->245|277->246|278->247|278->247|279->248|279->248|280->249|284->253|284->253|285->254|286->255|286->255|287->256|287->256|287->256|290->259|290->259
                  -- GENERATED --
              */
          