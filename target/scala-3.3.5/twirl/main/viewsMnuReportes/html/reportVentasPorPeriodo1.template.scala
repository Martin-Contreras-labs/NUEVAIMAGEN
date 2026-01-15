
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

object reportVentasPorPeriodo1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template11[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Double,Double,Double,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
datos: List[List[String]], fechaDesde: String, fechaHasta: String, usd: Double, eur: Double, uf: Double, 
desdeDDMMAA: String, hastaDDMMAA: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera1"></div><div id="msgEspera2"></div></h1></div>
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*16.4*/barraTitulo(mapDiccionario, "REPORTE VENTAS POR PERIODO","(SOLO CLIENTES)")),format.raw/*16.79*/("""
			"""),format.raw/*17.4*/("""<div class="noprint">
				<div class="row">
					<div class="col-1"></div>
				  	<div class="col-5">
				  	</div>
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
			
			<form id="excel" class="formulario" method="post" action="/routes2/reportVentasPorPeriodo1Excel/">
				<input type="hidden" name="fechaDesde" value=""""),_display_(/*44.52*/fechaDesde),format.raw/*44.62*/("""">
				<input type="hidden" name="fechaHasta" value=""""),_display_(/*45.52*/fechaHasta),format.raw/*45.62*/("""">
				<input type="hidden" name="uf" value=""""),_display_(/*46.44*/uf),format.raw/*46.46*/("""">
				<input type="hidden" name="usd" value=""""),_display_(/*47.45*/usd),format.raw/*47.48*/("""">
				<input type="hidden" name="eur" value=""""),_display_(/*48.45*/eur),format.raw/*48.48*/("""">
			</form>
			
			
			
			
			
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
				<h5>Período desde """),_display_(/*74.24*/desdeDDMMAA),format.raw/*74.35*/(""" """),format.raw/*74.36*/("""a """),_display_(/*74.39*/hastaDDMMAA),format.raw/*74.50*/("""</h5>
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
					        <TR> 
								<TH style= "text-align: center;vertical-align: top;">FECHA</TH>
						        <TH style= "text-align: center;vertical-align: top;">NRO. MOV</TH>
						        <TH style= "text-align: center;vertical-align: top;">NRO. REF</TH>
 								<TH style= "text-align: center;vertical-align: top;">GUIA<br>PDF</TH>
								<TH style= "text-align: center;vertical-align: top;">PATENTE</TH>
								<TH style= "text-align: center;vertical-align: top;">CONDUCTOR</TH>
							 	<TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
						        <TH style= "text-align: center;vertical-align: top;">"""),_display_(/*86.69*/mapDiccionario("BODEGA")),format.raw/*86.93*/(""" """),format.raw/*86.94*/("""DESTINO</TH>
								<TH style= "text-align: center;vertical-align: top;">CONCEPTO</TH>
								<TH style= "text-align: center;vertical-align: top;">TOTAL<br>NETO</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*92.9*/for(lista1 <- datos) yield /*92.29*/{_display_(Seq[Any](format.raw/*92.30*/("""
								"""),format.raw/*93.9*/("""<TR>
									<td style= "text-align: center;">
										<div style="display:none">"""),_display_(/*95.38*/utilities/*95.47*/.Fechas.AAMMDD(lista1.get(0))),format.raw/*95.76*/("""</div>
										"""),_display_(/*96.12*/utilities/*96.21*/.Fechas.DDMMAA(lista1.get(0))),format.raw/*96.50*/("""
									"""),format.raw/*97.10*/("""</td>
									<td style= "text-align: center;">"""),_display_(/*98.44*/lista1/*98.50*/.get(1)),format.raw/*98.57*/("""</td>
									<td style= "text-align: center;">"""),_display_(/*99.44*/lista1/*99.50*/.get(2)),format.raw/*99.57*/("""</td>
									<td style= "text-align: center;">
										"""),_display_(if(lista1.get(3).equals("0"))/*101.41*/{_display_(Seq[Any](format.raw/*101.42*/("""
											"""),format.raw/*102.12*/("""--
										""")))}else/*103.16*/{_display_(Seq[Any](format.raw/*103.17*/("""
											"""),format.raw/*104.12*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*104.46*/lista1/*104.52*/.get(3)),format.raw/*104.59*/("""')">
												<kbd style="background-color: #85C1E9">pdf</kbd>
											</a>
										""")))}),format.raw/*107.12*/("""
									"""),format.raw/*108.10*/("""</td>
									<td style= "text-align: left;">"""),_display_(/*109.42*/lista1/*109.48*/.get(4)),format.raw/*109.55*/("""</td>
									<td style= "text-align: left;">"""),_display_(/*110.42*/lista1/*110.48*/.get(5)),format.raw/*110.55*/("""</td>
									<td style= "text-align: center;">"""),_display_(/*111.44*/lista1/*111.50*/.get(9)),format.raw/*111.57*/("""</td>
									<td style= "text-align: left;">"""),_display_(/*112.42*/lista1/*112.48*/.get(6)),format.raw/*112.55*/("""</td>
									<td style= "text-align: left;">"""),_display_(/*113.42*/lista1/*113.48*/.get(7)),format.raw/*113.55*/("""</td>
									<td style= "text-align: right;">"""),_display_(/*114.43*/lista1/*114.49*/.get(8)),format.raw/*114.56*/("""</td>
								</TR>
				 			""")))}),format.raw/*116.10*/("""
						"""),format.raw/*117.7*/("""</tbody>
					</table>
				</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>MOVIMIENTO</h5>
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

""")))}),format.raw/*147.2*/("""


"""),format.raw/*150.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*152.31*/("""{"""),format.raw/*152.32*/("""

		 """),format.raw/*154.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*154.35*/("""{"""),format.raw/*154.36*/("""
		    	"""),format.raw/*155.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*160.20*/("""{"""),format.raw/*160.21*/("""
		        	"""),format.raw/*161.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*162.11*/("""}"""),format.raw/*162.12*/("""
		  """),format.raw/*163.5*/("""}"""),format.raw/*163.6*/(""" """),format.raw/*163.7*/(""");

		document.getElementById('mostrarmostrar').style.display="block";
			
			
	"""),format.raw/*168.2*/("""}"""),format.raw/*168.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*170.36*/("""{"""),format.raw/*170.37*/("""
		  """),format.raw/*171.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*173.2*/("""}"""),format.raw/*173.3*/("""
	
	
"""),format.raw/*176.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,datos:List[List[String]],fechaDesde:String,fechaHasta:String,usd:Double,eur:Double,uf:Double,desdeDDMMAA:String,hastaDDMMAA:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,datos,fechaDesde,fechaHasta,usd,eur,uf,desdeDDMMAA,hastaDDMMAA)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,Double,Double,Double,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,datos,fechaDesde,fechaHasta,usd,eur,uf,desdeDDMMAA,hastaDDMMAA) => apply(mapDiccionario,mapPermiso,userMnu,datos,fechaDesde,fechaHasta,usd,eur,uf,desdeDDMMAA,hastaDDMMAA)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportVentasPorPeriodo1.scala.html
                  HASH: 1ab91b37f8d0060ecdf3d7300469a268c3e181c2
                  MATRIX: 1092->1|1430->246|1462->253|1478->261|1517->263|1546->267|1614->315|1644->320|1699->355|1728->358|1771->380|1800->383|1844->406|1875->410|2121->630|2217->705|2248->709|3218->1652|3249->1662|3330->1716|3361->1726|3434->1772|3457->1774|3531->1821|3555->1824|3629->1871|3653->1874|4354->2548|4386->2559|4415->2560|4445->2563|4477->2574|5336->3406|5381->3430|5410->3431|5654->3649|5690->3669|5729->3670|5765->3679|5877->3764|5895->3773|5945->3802|5990->3820|6008->3829|6058->3858|6096->3868|6172->3917|6187->3923|6215->3930|6291->3979|6306->3985|6334->3992|6451->4081|6491->4082|6532->4094|6570->4112|6610->4113|6651->4125|6713->4159|6729->4165|6758->4172|6883->4265|6922->4275|6997->4322|7013->4328|7042->4335|7117->4382|7133->4388|7162->4395|7239->4444|7255->4450|7284->4457|7359->4504|7375->4510|7404->4517|7479->4564|7495->4570|7524->4577|7600->4625|7616->4631|7645->4638|7706->4667|7741->4674|8633->5535|8664->5538|8756->5601|8786->5602|8819->5607|8879->5638|8909->5639|8945->5647|9113->5786|9143->5787|9184->5799|9291->5877|9321->5878|9354->5883|9383->5884|9412->5885|9520->5965|9549->5966|9618->6006|9648->6007|9681->6012|9891->6194|9920->6195|9953->6200
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|47->16|47->16|48->17|75->44|75->44|76->45|76->45|77->46|77->46|78->47|78->47|79->48|79->48|105->74|105->74|105->74|105->74|105->74|117->86|117->86|117->86|123->92|123->92|123->92|124->93|126->95|126->95|126->95|127->96|127->96|127->96|128->97|129->98|129->98|129->98|130->99|130->99|130->99|132->101|132->101|133->102|134->103|134->103|135->104|135->104|135->104|135->104|138->107|139->108|140->109|140->109|140->109|141->110|141->110|141->110|142->111|142->111|142->111|143->112|143->112|143->112|144->113|144->113|144->113|145->114|145->114|145->114|147->116|148->117|178->147|181->150|183->152|183->152|185->154|185->154|185->154|186->155|191->160|191->160|192->161|193->162|193->162|194->163|194->163|194->163|199->168|199->168|201->170|201->170|202->171|204->173|204->173|207->176
                  -- GENERATED --
              */
          