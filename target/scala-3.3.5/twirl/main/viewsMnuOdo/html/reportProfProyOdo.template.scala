
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

object reportProfProyOdo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template12[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Double,Double,Double,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
totalesPorProyecto: List[List[String]], fechaDesde: String, fechaHasta: String, usd: Double, eur: Double, uf: Double, desdeDDMMAA: String, hastaDDMMAA: String, archivoPDF: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""
	
	"""),_display_(/*6.3*/modalContactoBodega(mapDiccionario)),format.raw/*6.38*/("""
	"""),_display_(/*7.3*/modalContactoCliente()),format.raw/*7.25*/("""
	"""),_display_(/*8.3*/modalContactoProyecto()),format.raw/*8.26*/("""

	"""),_display_(/*10.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*10.51*/("""
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","PROCESO DE EP/FACTURACION SERVICIOS")),format.raw/*12.127*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
		
		
		
		<div class="noprint">
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
									onclick="history.go(-1); return false;">
									Volver
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
			
			<form id="excel" class="formulario" method="post" action="/reportProfProyOdoExcel/">
				<input type="hidden" name="desdeAAMMDD" value=""""),_display_(/*39.53*/fechaDesde),format.raw/*39.63*/("""">
				<input type="hidden" name="hastaAAMMDD" value=""""),_display_(/*40.53*/fechaHasta),format.raw/*40.63*/("""">
				<input type="hidden" name="uf" value=""""),_display_(/*41.44*/uf),format.raw/*41.46*/("""">
				<input type="hidden" name="usd" value=""""),_display_(/*42.45*/usd),format.raw/*42.48*/("""">
				<input type="hidden" name="eur" value=""""),_display_(/*43.45*/eur),format.raw/*43.48*/("""">
			</form>
		
		
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
				<h5>Período desde """),_display_(/*64.24*/desdeDDMMAA),format.raw/*64.35*/(""" """),format.raw/*64.36*/("""a """),_display_(/*64.39*/hastaDDMMAA),format.raw/*64.50*/("""</h5>
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
					        <TR> 
						        <TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
 								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*70.64*/mapDiccionario/*70.78*/.get("BODEGA")),format.raw/*70.92*/("""/PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<BR>CLIENTE</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<br>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">COMERCIAL</TH>
								
								<TH style= "text-align:center;vertical-align:top;">SUBTOTAL<BR>(en """),_display_(/*75.77*/mapDiccionario/*75.91*/.get("PESOS")),format.raw/*75.104*/(""")</TH>
								<TH style= "text-align:center;vertical-align:top;">AJUSTES<BR>(en """),_display_(/*76.76*/mapDiccionario/*76.90*/.get("PESOS")),format.raw/*76.103*/(""")</TH>
								<TH style= "text-align:center;vertical-align:top;">TOTAL<BR>(en """),_display_(/*77.74*/mapDiccionario/*77.88*/.get("PESOS")),format.raw/*77.101*/(""")</TH>
								
								<TH width="5%" >DETALLE<BR></TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*83.9*/for(lista1 <- totalesPorProyecto) yield /*83.42*/{_display_(Seq[Any](format.raw/*83.43*/("""
								"""),format.raw/*84.9*/("""<TR>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*85.62*/lista1/*85.68*/.get(10)),format.raw/*85.76*/("""</td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*86.104*/lista1/*86.110*/.get(0)),format.raw/*86.117*/("""');">"""),_display_(/*86.123*/lista1/*86.129*/.get(1)),format.raw/*86.136*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoCliente('"""),_display_(/*87.105*/lista1/*87.111*/.get(8)),format.raw/*87.118*/("""');">"""),_display_(/*87.124*/lista1/*87.130*/.get(2)),format.raw/*87.137*/("""</a></td>
									<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoProyecto('"""),_display_(/*88.106*/lista1/*88.112*/.get(9)),format.raw/*88.119*/("""');">"""),_display_(/*88.125*/lista1/*88.131*/.get(3)),format.raw/*88.138*/("""</a></td>
									
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*90.62*/lista1/*90.68*/.get(4)),format.raw/*90.75*/("""</td>
									<td style= "text-align:right;" class="subtotal">"""),_display_(/*91.59*/lista1/*91.65*/.get(5)),format.raw/*91.72*/("""</td>
									<td style= "text-align:right;" class="ajuste">"""),_display_(/*92.57*/lista1/*92.63*/.get(6)),format.raw/*92.70*/("""</td>
									<td style= "text-align:right;" class="total">"""),_display_(/*93.56*/lista1/*93.62*/.get(7)),format.raw/*93.69*/("""</td>
									
									<td style="text-align:center;vertical-align:middle;">
										<form id="form0_"""),_display_(/*96.28*/lista1/*96.34*/.get(0)),format.raw/*96.41*/("""" class="formulario" method="post" action="/reportProfProyDetalleOdo/">
											<input type="hidden" name="id_bodega" value=""""),_display_(/*97.58*/lista1/*97.64*/.get(0)),format.raw/*97.71*/("""">
											<input type="hidden" name="fechaDesde" value=""""),_display_(/*98.59*/fechaDesde),format.raw/*98.69*/("""">
											<input type="hidden" name="fechaHasta" value=""""),_display_(/*99.59*/fechaHasta),format.raw/*99.69*/("""">
											<input type="hidden" name="uf" value=""""),_display_(/*100.51*/uf),format.raw/*100.53*/("""">
											<input type="hidden" name="usd" value=""""),_display_(/*101.52*/usd),format.raw/*101.55*/("""">
											<input type="hidden" name="eur" value=""""),_display_(/*102.52*/eur),format.raw/*102.55*/("""">
											<a href="#" onclick="document.getElementById('form0_"""),_display_(/*103.65*/lista1/*103.71*/.get(0)),format.raw/*103.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">Emitir</kbd>
											</a>
										</form>
									</td>
								</TR>
				 			""")))}),format.raw/*109.10*/("""
						"""),format.raw/*110.7*/("""</tbody>
						<tfoot>
							<TR>
								<td style="background-color: #eeeeee">TOTALES</td>
								<td style="background-color: #eeeeee"></td>
								<td style="background-color: #eeeeee"></td>
								<td style="background-color: #eeeeee"></td>
								<td style="background-color: #eeeeee"></td>
								<td style="background-color: #eeeeee;text-align:right;" id="subtotal"></td>
								<td style="background-color: #eeeeee;text-align:right;" id="ajuste"></td>
								<td style="background-color: #eeeeee;text-align:right;" id="total"></td>
								<td style="background-color: #eeeeee;text-align:right;">
							</TR>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SERVICIO PROFORMA</h5>
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


""")))}),format.raw/*152.2*/("""


"""),format.raw/*155.1*/("""<script type="text/javascript">
	
	$(document).ready(function() """),format.raw/*157.31*/("""{"""),format.raw/*157.32*/("""

		 """),format.raw/*159.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*159.35*/("""{"""),format.raw/*159.36*/("""
		    	"""),format.raw/*160.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*165.20*/("""{"""),format.raw/*165.21*/("""
		        	"""),format.raw/*166.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*167.11*/("""}"""),format.raw/*167.12*/("""
		  """),format.raw/*168.5*/("""}"""),format.raw/*168.6*/(""" """),format.raw/*168.7*/(""");

		let subtotal = 0;
		$(".subtotal").each(function() """),format.raw/*171.34*/("""{"""),format.raw/*171.35*/("""
			"""),format.raw/*172.4*/("""let val = $(this).text().replace(/,/g,'');
			subtotal += parseFloat(val);
		"""),format.raw/*174.3*/("""}"""),format.raw/*174.4*/(""");
		$("#subtotal").text(formatStandar(subtotal,0));
		
		let ajuste = 0;
		$(".ajuste").each(function() """),format.raw/*178.32*/("""{"""),format.raw/*178.33*/("""
			"""),format.raw/*179.4*/("""let val = $(this).text().replace(/,/g,'');
			ajuste += parseFloat(val);
		"""),format.raw/*181.3*/("""}"""),format.raw/*181.4*/(""");
		$("#ajuste").text(formatStandar(ajuste,0));
		
		let total = 0;
		$(".total").each(function() """),format.raw/*185.31*/("""{"""),format.raw/*185.32*/("""
			"""),format.raw/*186.4*/("""let val = $(this).text().replace(/,/g,'');
			total += parseFloat(val);
		"""),format.raw/*188.3*/("""}"""),format.raw/*188.4*/(""");
		$("#total").text(formatStandar(total,0));
		
		document.getElementById('mostrarmostrar').style.display="block";
		
		if(""""),_display_(/*193.8*/archivoPDF),format.raw/*193.18*/(""""!="0")"""),format.raw/*193.25*/("""{"""),format.raw/*193.26*/("""
			 """),format.raw/*194.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"""),_display_(/*194.75*/archivoPDF),format.raw/*194.85*/("""' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
		"""),format.raw/*196.3*/("""}"""),format.raw/*196.4*/("""
		
	"""),format.raw/*198.2*/("""}"""),format.raw/*198.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,totalesPorProyecto:List[List[String]],fechaDesde:String,fechaHasta:String,usd:Double,eur:Double,uf:Double,desdeDDMMAA:String,hastaDDMMAA:String,archivoPDF:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,totalesPorProyecto,fechaDesde,fechaHasta,usd,eur,uf,desdeDDMMAA,hastaDDMMAA,archivoPDF)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Double,Double,Double,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,totalesPorProyecto,fechaDesde,fechaHasta,usd,eur,uf,desdeDDMMAA,hastaDDMMAA,archivoPDF) => apply(mapDiccionario,mapPermiso,userMnu,totalesPorProyecto,fechaDesde,fechaHasta,usd,eur,uf,desdeDDMMAA,hastaDDMMAA,archivoPDF)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/reportProfProyOdo.scala.html
                  HASH: de88d57341decb6ed81488742f0afb6cd8c59f7a
                  MATRIX: 1088->1|1458->278|1490->285|1506->293|1545->295|1575->300|1630->335|1658->338|1700->360|1728->363|1771->386|1801->390|1870->438|1899->440|1976->491|2121->614|2151->617|3031->1470|3062->1480|3144->1535|3175->1545|3248->1591|3271->1593|3345->1640|3369->1643|3443->1690|3467->1693|4103->2302|4135->2313|4164->2314|4194->2317|4226->2328|4622->2697|4645->2711|4680->2725|5052->3070|5075->3084|5110->3097|5219->3179|5242->3193|5277->3206|5384->3286|5407->3300|5442->3313|5575->3420|5624->3453|5663->3454|5699->3463|5792->3529|5807->3535|5836->3543|5973->3652|5989->3658|6018->3665|6052->3671|6068->3677|6097->3684|6239->3798|6255->3804|6284->3811|6318->3817|6334->3823|6363->3830|6506->3945|6522->3951|6551->3958|6585->3964|6601->3970|6630->3977|6738->4058|6753->4064|6781->4071|6872->4135|6887->4141|6915->4148|7004->4210|7019->4216|7047->4223|7135->4284|7150->4290|7178->4297|7311->4403|7326->4409|7354->4416|7510->4545|7525->4551|7553->4558|7641->4619|7672->4629|7760->4690|7791->4700|7872->4753|7896->4755|7978->4809|8003->4812|8085->4866|8110->4869|8205->4936|8221->4942|8250->4949|8432->5099|8467->5106|9987->6595|10018->6598|10111->6662|10141->6663|10174->6668|10234->6699|10264->6700|10300->6708|10468->6847|10498->6848|10539->6860|10646->6938|10676->6939|10709->6944|10738->6945|10767->6946|10853->7003|10883->7004|10915->7008|11020->7085|11049->7086|11183->7191|11213->7192|11245->7196|11348->7271|11377->7272|11505->7371|11535->7372|11567->7376|11669->7450|11698->7451|11852->7578|11884->7588|11920->7595|11950->7596|11983->7601|12081->7671|12113->7681|12242->7782|12271->7783|12304->7788|12333->7789
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|38->7|39->8|39->8|41->10|41->10|42->11|43->12|43->12|44->13|70->39|70->39|71->40|71->40|72->41|72->41|73->42|73->42|74->43|74->43|95->64|95->64|95->64|95->64|95->64|101->70|101->70|101->70|106->75|106->75|106->75|107->76|107->76|107->76|108->77|108->77|108->77|114->83|114->83|114->83|115->84|116->85|116->85|116->85|117->86|117->86|117->86|117->86|117->86|117->86|118->87|118->87|118->87|118->87|118->87|118->87|119->88|119->88|119->88|119->88|119->88|119->88|121->90|121->90|121->90|122->91|122->91|122->91|123->92|123->92|123->92|124->93|124->93|124->93|127->96|127->96|127->96|128->97|128->97|128->97|129->98|129->98|130->99|130->99|131->100|131->100|132->101|132->101|133->102|133->102|134->103|134->103|134->103|140->109|141->110|183->152|186->155|188->157|188->157|190->159|190->159|190->159|191->160|196->165|196->165|197->166|198->167|198->167|199->168|199->168|199->168|202->171|202->171|203->172|205->174|205->174|209->178|209->178|210->179|212->181|212->181|216->185|216->185|217->186|219->188|219->188|224->193|224->193|224->193|224->193|225->194|225->194|225->194|227->196|227->196|229->198|229->198
                  -- GENERATED --
              */
          