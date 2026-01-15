
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

object reporteMovDetPorProyecto extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template12[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.Proyecto,String,String,String,String,Double,Double,Double,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], proyecto: tables.Proyecto, esVenta: String, concepto: String, fechaDesde: String, fechaHasta: String,
usd: Double, eur: Double, uf: Double):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	"""),_display_(/*10.3*/modalVerCotizacion()),format.raw/*10.23*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "DETALLE MOVIMIENTO POR PROYECTO: "+proyecto.getNickName().toUpperCase,concepto)),format.raw/*13.112*/("""

				"""),format.raw/*15.5*/("""<div class="noprint">
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
								"""),_display_(if(index1 < 5)/*51.24*/{_display_(Seq[Any](format.raw/*51.25*/("""
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
								"""),_display_(if(index1 > 4)/*62.24*/{_display_(Seq[Any](format.raw/*62.25*/("""
									"""),format.raw/*63.10*/("""<TR>
										"""),_display_(/*64.12*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*64.55*/ {_display_(Seq[Any](format.raw/*64.57*/("""
											"""),_display_(if(index2 == 1)/*65.28*/{_display_(Seq[Any](format.raw/*65.29*/("""
												"""),_display_(if(nivel2.equals("0") || nivel2.equals(""))/*66.57*/{_display_(Seq[Any](format.raw/*66.58*/("""
													"""),format.raw/*67.14*/("""<td style="text-align: center;">--</td>
												""")))}else/*68.18*/{_display_(Seq[Any](format.raw/*68.19*/("""
													"""),format.raw/*69.14*/("""<td style="text-align: center;"><a href="#" onclick="buscaCotizacion('"""),_display_(/*69.85*/nivel2),format.raw/*69.91*/("""')">"""),_display_(/*69.96*/nivel2),format.raw/*69.102*/("""</td>
												""")))}),format.raw/*70.14*/("""
											""")))}else/*71.17*/{_display_(Seq[Any](format.raw/*71.18*/("""
												"""),_display_(if(index2 == 2 || index2 ==3)/*72.43*/{_display_(Seq[Any](format.raw/*72.44*/("""
													"""),format.raw/*73.14*/("""<td><a href="#" onclick="buscaEquipo('"""),_display_(/*73.53*/nivel1/*73.59*/.get(2)),format.raw/*73.66*/("""');">"""),_display_(/*73.72*/nivel2),format.raw/*73.78*/("""</a></td>
												""")))}else/*74.18*/{_display_(Seq[Any](format.raw/*74.19*/("""
													"""),format.raw/*75.14*/("""<td>"""),_display_(/*75.19*/nivel2),format.raw/*75.25*/("""</td>
												""")))}),format.raw/*76.14*/("""
											""")))}),format.raw/*77.13*/("""
										""")))}),format.raw/*78.12*/("""
									"""),format.raw/*79.10*/("""</TR>
								""")))} else {null} ),format.raw/*80.10*/("""
							 """)))}),format.raw/*81.10*/("""
						"""),format.raw/*82.7*/("""</tbody>
						

					</table>
				</div>
			</div>

	
	<form id="excel" class="formulario" method="post" action="/routes2/reportePorProyectoDetalleValorizadoExcel/">
		<input type="hidden" name="id_proyecto" value=""""),_display_(/*91.51*/proyecto/*91.59*/.getId()),format.raw/*91.67*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*92.50*/fechaDesde),format.raw/*92.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*93.50*/fechaHasta),format.raw/*93.60*/("""">
		<input type="hidden" name="esVenta" value=""""),_display_(/*94.47*/esVenta),format.raw/*94.54*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*95.42*/uf),format.raw/*95.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*96.43*/usd),format.raw/*96.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*97.43*/eur),format.raw/*97.46*/("""">
	</form>


""")))}),format.raw/*101.2*/("""


"""),format.raw/*104.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*106.31*/("""{"""),format.raw/*106.32*/("""
		
		"""),format.raw/*108.3*/("""var tabla = document.getElementById("tablaPrincipal");
		
				/*
				for (var i = 0; i < tabla.rows.length; i++) """),format.raw/*111.49*/("""{"""),format.raw/*111.50*/(""" 	
					"""),format.raw/*112.6*/("""tabla.rows[i].cells[11].style.backgroundColor="#eeeeee";
				"""),format.raw/*113.5*/("""}"""),format.raw/*113.6*/("""
				"""),format.raw/*114.5*/("""for (var i = 5; i < tabla.rows.length; i++) """),format.raw/*114.49*/("""{"""),format.raw/*114.50*/(""" 
					"""),format.raw/*115.6*/("""tabla.rows[i].cells[tabla.rows[0].cells.length-5].style.backgroundColor="#eeeeee";
				"""),format.raw/*116.5*/("""}"""),format.raw/*116.6*/("""
				"""),format.raw/*117.5*/("""for (var i = 5; i < tabla.rows.length; i++) """),format.raw/*117.49*/("""{"""),format.raw/*117.50*/("""
					
					"""),format.raw/*119.6*/("""tabla.rows[i].cells[0].style.textAlign="left";
					tabla.rows[i].cells[1].style.textAlign="center";
					tabla.rows[i].cells[2].style.textAlign="left";
					tabla.rows[i].cells[3].style.textAlign="left";
					tabla.rows[i].cells[4].style.textAlign="right";
					tabla.rows[i].cells[5].style.textAlign="right";
					tabla.rows[i].cells[6].style.textAlign="center";
					tabla.rows[i].cells[7].style.textAlign="right";
					tabla.rows[i].cells[8].style.textAlign="right";
					tabla.rows[i].cells[9].style.textAlign="right";
					tabla.rows[i].cells[10].style.textAlign="right";
					tabla.rows[i].cells[11].style.textAlign="right";
					
					tabla.rows[i].cells[0].style.minWidth="150px";
					tabla.rows[i].cells[2].style.minWidth="80px";
					tabla.rows[i].cells[3].style.minWidth="300px";
					tabla.rows[i].cells[7].style.minWidth="60px";
					tabla.rows[i].cells[8].style.minWidth="60px";
					tabla.rows[i].cells[9].style.minWidth="60px";
					tabla.rows[i].cells[10].style.minWidth="60px";
					tabla.rows[i].cells[11].style.minWidth="60px";
					
					for(var k=12;k<tabla.rows[0].cells.length;k++)"""),format.raw/*141.52*/("""{"""),format.raw/*141.53*/("""
						"""),format.raw/*142.7*/("""tabla.rows[i].cells[k].style.textAlign="right";
					"""),format.raw/*143.6*/("""}"""),format.raw/*143.7*/("""
				"""),format.raw/*144.5*/("""}"""),format.raw/*144.6*/("""
				"""),format.raw/*145.5*/("""for(var k=0;k<tabla.rows[0].cells.length;k++)"""),format.raw/*145.50*/("""{"""),format.raw/*145.51*/("""
					"""),format.raw/*146.6*/("""tabla.rows[tabla.rows.length-1].cells[k].style.backgroundColor="#eeeeee";
				"""),format.raw/*147.5*/("""}"""),format.raw/*147.6*/("""
				
				"""),format.raw/*149.5*/("""/*
				$('#tablaPrincipal').DataTable("""),format.raw/*150.36*/("""{"""),format.raw/*150.37*/("""
				    	"""),format.raw/*151.10*/(""""fixedHeader": true,
				    	"paging": false,
						"info": false,
						"searching": false,
						"order": false,
						columnDefs:["""),format.raw/*156.19*/("""{"""),format.raw/*156.20*/("""
				            """),format.raw/*157.17*/("""targets: "_all",
				            sortable: false
				        """),format.raw/*159.13*/("""}"""),format.raw/*159.14*/("""],
				    //	"order": [[ 3, "asc" ]],
						"scrollY": 550,
						"scrollX": true,
				    	"language": """),format.raw/*163.22*/("""{"""),format.raw/*163.23*/("""
				        	"""),format.raw/*164.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*165.13*/("""}"""),format.raw/*165.14*/("""
				"""),format.raw/*166.5*/("""}"""),format.raw/*166.6*/(""" """),format.raw/*166.7*/(""");
*/
			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*169.2*/("""}"""),format.raw/*169.3*/(""");
	
	
	
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],proyecto:tables.Proyecto,esVenta:String,concepto:String,fechaDesde:String,fechaHasta:String,usd:Double,eur:Double,uf:Double): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,proyecto,esVenta,concepto,fechaDesde,fechaHasta,usd,eur,uf)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.Proyecto,String,String,String,String,Double,Double,Double) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,proyecto,esVenta,concepto,fechaDesde,fechaHasta,usd,eur,uf) => apply(mapDiccionario,mapPermiso,userMnu,lista,proyecto,esVenta,concepto,fechaDesde,fechaHasta,usd,eur,uf)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteMovDetPorProyecto.scala.html
                  HASH: 9dfc189a936e9466575245102e2adde128d2b794
                  MATRIX: 1109->1|1466->265|1498->272|1514->280|1553->282|1582->286|1650->334|1680->339|1724->363|1753->366|1794->386|1825->390|1902->441|2032->549|2065->555|3357->1820|3415->1862|3455->1864|3506->1888|3545->1889|3583->1899|3626->1915|3663->1936|3703->1938|3743->1950|3801->1981|3828->1987|3876->2004|3914->2014|3973->2029|4012->2038|4046->2045|4103->2076|4161->2118|4201->2120|4252->2144|4291->2145|4329->2155|4372->2171|4431->2214|4471->2216|4526->2244|4565->2245|4649->2302|4688->2303|4730->2317|4806->2374|4845->2375|4887->2389|4985->2460|5012->2466|5044->2471|5072->2477|5122->2496|5158->2513|5197->2514|5267->2557|5306->2558|5348->2572|5414->2611|5429->2617|5457->2624|5490->2630|5517->2636|5563->2663|5602->2664|5644->2678|5676->2683|5703->2689|5753->2708|5797->2721|5840->2733|5878->2743|5937->2758|5978->2768|6012->2775|6256->2992|6273->3000|6302->3008|6381->3060|6412->3070|6491->3122|6522->3132|6598->3181|6626->3188|6697->3232|6720->3234|6792->3279|6816->3282|6888->3327|6912->3330|6958->3345|6989->3348|7081->3411|7111->3412|7145->3418|7287->3531|7317->3532|7353->3540|7442->3601|7471->3602|7504->3607|7577->3651|7607->3652|7642->3659|7757->3746|7786->3747|7819->3752|7892->3796|7922->3797|7962->3809|9098->4916|9128->4917|9163->4924|9244->4977|9273->4978|9306->4983|9335->4984|9368->4989|9442->5034|9472->5035|9506->5041|9612->5119|9641->5120|9679->5130|9746->5168|9776->5169|9815->5179|9978->5313|10008->5314|10054->5331|10144->5392|10174->5393|10308->5498|10338->5499|10381->5513|10490->5593|10520->5594|10553->5599|10582->5600|10611->5601|10714->5676|10743->5677
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|46->15|81->50|81->50|81->50|82->51|82->51|83->52|84->53|84->53|84->53|85->54|85->54|85->54|86->55|87->56|88->57|89->58|90->59|92->61|92->61|92->61|93->62|93->62|94->63|95->64|95->64|95->64|96->65|96->65|97->66|97->66|98->67|99->68|99->68|100->69|100->69|100->69|100->69|100->69|101->70|102->71|102->71|103->72|103->72|104->73|104->73|104->73|104->73|104->73|104->73|105->74|105->74|106->75|106->75|106->75|107->76|108->77|109->78|110->79|111->80|112->81|113->82|122->91|122->91|122->91|123->92|123->92|124->93|124->93|125->94|125->94|126->95|126->95|127->96|127->96|128->97|128->97|132->101|135->104|137->106|137->106|139->108|142->111|142->111|143->112|144->113|144->113|145->114|145->114|145->114|146->115|147->116|147->116|148->117|148->117|148->117|150->119|172->141|172->141|173->142|174->143|174->143|175->144|175->144|176->145|176->145|176->145|177->146|178->147|178->147|180->149|181->150|181->150|182->151|187->156|187->156|188->157|190->159|190->159|194->163|194->163|195->164|196->165|196->165|197->166|197->166|197->166|200->169|200->169
                  -- GENERATED --
              */
          