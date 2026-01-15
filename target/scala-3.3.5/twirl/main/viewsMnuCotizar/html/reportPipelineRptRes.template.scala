
package viewsMnuCotizar.html

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

object reportPipelineRptRes extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,String,String,String,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
detalle: List[List[String]], fechaDe: String, fechaA: String, tituloSucursal: String,
desdeAAMMDD: String, hastaAAMMDD: String, id_sucursal: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "REPORTE PIPELINE RESUMIDO "+tituloSucursal," período movil: de "+fechaDe+" a "+fechaA)),format.raw/*13.119*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
			
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
									onclick="history.go(-1);return false;">
									Volver
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
			
			<form id="excel" class="formulario" method="post" action="/routes2/reportPipelineRptResExcel/">
				<input type="hidden" name="fechaDesde" value=""""),_display_(/*39.52*/desdeAAMMDD),format.raw/*39.63*/("""">
				<input type="hidden" name="fechaHasta" value=""""),_display_(/*40.52*/hastaAAMMDD),format.raw/*40.63*/("""">
				<input type="hidden" name="id_sucursal" value=""""),_display_(/*41.53*/id_sucursal),format.raw/*41.64*/("""">
			</form>
			
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>ANIO MES<br>COTI</TH>
							<TH>COMERCIAL</TH>
							<TH>ESTADO</TH>
							<TH>TOTAL<BR>NETO</TH>
							<TH>PESO<BR>KG</TH>
							<TH>DETALLE</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*56.8*/for(lista1 <- detalle) yield /*56.30*/{_display_(Seq[Any](format.raw/*56.31*/("""
							"""),format.raw/*57.8*/("""<TR>
								<td style="text-align:center;">"""),_display_(/*58.41*/lista1/*58.47*/.get(0)),format.raw/*58.54*/("""</td>
								<td style="text-align:left;">"""),_display_(/*59.39*/lista1/*59.45*/.get(2)),format.raw/*59.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*60.39*/lista1/*60.45*/.get(1)),format.raw/*60.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*61.39*/lista1/*61.45*/.get(3)),format.raw/*61.52*/("""</td>
								<td style="text-align:left;">"""),_display_(/*62.39*/lista1/*62.45*/.get(4)),format.raw/*62.52*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick="pipelineDetalle('"""),_display_(/*64.49*/lista1/*64.55*/.get(0)),format.raw/*64.62*/("""','"""),_display_(/*64.66*/lista1/*64.72*/.get(5)),format.raw/*64.79*/("""','"""),_display_(/*64.83*/lista1/*64.89*/.get(6)),format.raw/*64.96*/("""')">
										<kbd style="background-color: #73C6B6">revisar</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*69.9*/("""
					"""),format.raw/*70.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalVerDetalle' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>LISTA DE COTIZACIONES</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id='mostrarDetalle'></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalVerCotizacion' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>COTIZACION</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id='mostrarLaCotizacion'></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	

""")))}),format.raw/*127.2*/("""


"""),format.raw/*130.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*131.31*/("""{"""),format.raw/*131.32*/("""
		  """),format.raw/*132.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*132.36*/("""{"""),format.raw/*132.37*/("""
		    	"""),format.raw/*133.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ]],
		    	"language": """),format.raw/*136.20*/("""{"""),format.raw/*136.21*/("""
		        	"""),format.raw/*137.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*138.11*/("""}"""),format.raw/*138.12*/("""
		  """),format.raw/*139.5*/("""}"""),format.raw/*139.6*/(""" """),format.raw/*139.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*141.2*/("""}"""),format.raw/*141.3*/(""");



	const pipelineDetalle = (anioMes, id_comercial, id_cotizaEstado) => """),format.raw/*145.70*/("""{"""),format.raw/*145.71*/("""
		"""),format.raw/*146.3*/("""let formData = new FormData();
		formData.append("anioMes",anioMes);
		formData.append("id_cotizaEstado",id_cotizaEstado);
		formData.append("id_comercial",id_comercial);
		$.ajax("""),format.raw/*150.10*/("""{"""),format.raw/*150.11*/("""
		    """),format.raw/*151.7*/("""url: "/routes2/reportPipelineDetalle/",
		    type: "POST",
		    method: "POST",
		    data: formData,
		    cache: false,
		    contentType: false,
		 	processData: false,
		 	success: function(rs)"""),format.raw/*158.26*/("""{"""),format.raw/*158.27*/("""
			
		 		"""),format.raw/*160.6*/("""if(rs=="error")"""),format.raw/*160.21*/("""{"""),format.raw/*160.22*/("""
		 			"""),format.raw/*161.7*/("""alertify.alert('Se presento un problema o usted no tiene permisos otorgados, por favor vuelva a ingresar y si persiste el problema, contactar a soporte', function()"""),format.raw/*161.171*/("""{"""),format.raw/*161.172*/(""" 
						"""),format.raw/*162.7*/("""location.href = "/home/"; 
					"""),format.raw/*163.6*/("""}"""),format.raw/*163.7*/(""");
		 		"""),format.raw/*164.6*/("""}"""),format.raw/*164.7*/("""else"""),format.raw/*164.11*/("""{"""),format.raw/*164.12*/("""
			
					"""),format.raw/*166.6*/("""let html =
						"<table  id='tablaDetalle' class=\"table table-sm table-bordered table-condensed table-hover table-fluid\">"
							+ "<thead>"
								+ "<tr>"
							        + "<TH>SUCURSAL</TH>"
									+ "<TH>COMERCIAL</TH>"
									+ "<TH>SOLUCION</TH>"
									+ "<TH>Nro.COTI</TH>"
									+ "<TH style='min-width:80px;'>FECHA<br>COTIZACION</TH>"
									+ "<TH>CLIENTE</TH>"
									+ "<TH>PROYECTO</TH>"
									+ "<TH>ESTADO</TH>"
									+ "<TH style=\"min-width:80px;\">FECHA<br>CONFIRMADA</TH>"
								+ "</tr>"
							+ "</thead>"
							+ "<tbody>";
						for(var i=0; i<rs.length; i++)"""),format.raw/*182.37*/("""{"""),format.raw/*182.38*/("""
							"""),format.raw/*183.8*/("""html +=
							"<tr>"
								+ "<td style=\"text-align:left;\">"+rs[i][13]+"</td>"
								+ "<td style=\"text-align:left;\">"+rs[i][14]+"</td>"
								+ "<td style=\"text-align:left;\">"+rs[i][15]+"</td>"
								+ "<td style=\"text-align:center;\">"
									+ "<a href=\"#\" onclick=\"verCotizacion('"+rs[i][0]+"')\">"
									+ "<kbd style=\"background-color: rgb(90, 200, 245)\">"
									+ "<font color=\"green\">"+rs[i][1]+"</font>"
									+ "</kbd>"
									+ "</a>"
								+ "</td>"
								+ "<td  style=\"text-align:center;\">"
									+"<div style='display:none'>"+rs[i][2]+"</div>"
									+fechaDDMMAA(rs[i][2])
								+ "</td>"
								+ "<td style=\"text-align:left;\">"+rs[i][3]+"</td>"
								+ "<td style=\"text-align:left;\">"+rs[i][6]+"</td>"
								+ "<td  style=\"text-align:center;\">"+rs[i][8]+"</td>"
								+ "<td  style=\"text-align:center;\">"
									+"<div style='display:none'>"+rs[i][9]+"</div>"
									+fechaDDMMAA(rs[i][9])
								+ "</td>"
							+ "</tr>";
						"""),format.raw/*207.7*/("""}"""),format.raw/*207.8*/("""
				
					"""),format.raw/*209.6*/("""html +=
						 "</tbody>"
						+ "</table>";
				
					$("#mostrarDetalle").html(html);
				
					$('#tablaDetalle').DataTable("""),format.raw/*215.35*/("""{"""),format.raw/*215.36*/("""
					    	"""),format.raw/*216.11*/(""""fixedHeader": true,
					    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
					    	"order": [[ 3, "desc" ]],
					    	"language": """),format.raw/*219.23*/("""{"""),format.raw/*219.24*/("""
					        	"""),format.raw/*220.15*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
					        """),format.raw/*221.14*/("""}"""),format.raw/*221.15*/("""
					  """),format.raw/*222.8*/("""}"""),format.raw/*222.9*/(""" """),format.raw/*222.10*/(""");
		
				
					$('#modalVerDetalle').modal('show');
				"""),format.raw/*226.5*/("""}"""),format.raw/*226.6*/("""
			"""),format.raw/*227.4*/("""}"""),format.raw/*227.5*/("""
		"""),format.raw/*228.3*/("""}"""),format.raw/*228.4*/(""");
		
	"""),format.raw/*230.2*/("""}"""),format.raw/*230.3*/("""
	
	"""),format.raw/*232.2*/("""const verCotizacion = (id_cotizacion) => """),format.raw/*232.43*/("""{"""),format.raw/*232.44*/("""
		"""),format.raw/*233.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*235.16*/("""{"""),format.raw/*235.17*/("""
            """),format.raw/*236.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*243.36*/("""{"""),format.raw/*243.37*/("""
	     		"""),format.raw/*244.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*246.8*/("""}"""),format.raw/*246.9*/("""
        """),format.raw/*247.9*/("""}"""),format.raw/*247.10*/(""");
	"""),format.raw/*248.2*/("""}"""),format.raw/*248.3*/("""
	
	
	
"""),format.raw/*252.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,detalle:List[List[String]],fechaDe:String,fechaA:String,tituloSucursal:String,desdeAAMMDD:String,hastaAAMMDD:String,id_sucursal:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,detalle,fechaDe,fechaA,tituloSucursal,desdeAAMMDD,hastaAAMMDD,id_sucursal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,String,String,String,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,detalle,fechaDe,fechaA,tituloSucursal,desdeAAMMDD,hastaAAMMDD,id_sucursal) => apply(mapDiccionario,mapPermiso,userMnu,detalle,fechaDe,fechaA,tituloSucursal,desdeAAMMDD,hastaAAMMDD,id_sucursal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/reportPipelineRptRes.scala.html
                  HASH: 2ad9bd8f9574e42a6f70084e863b5c285041c10c
                  MATRIX: 1079->1|1416->245|1448->252|1464->260|1503->262|1532->266|1600->314|1630->319|1672->341|1701->344|1745->367|1776->371|1853->422|1990->537|2020->540|2962->1455|2994->1466|3075->1520|3107->1531|3189->1586|3221->1597|3642->1992|3680->2014|3719->2015|3754->2023|3826->2068|3841->2074|3869->2081|3940->2125|3955->2131|3983->2138|4054->2182|4069->2188|4097->2195|4168->2239|4183->2245|4211->2252|4282->2296|4297->2302|4325->2309|4447->2404|4462->2410|4490->2417|4521->2421|4536->2427|4564->2434|4595->2438|4610->2444|4638->2451|4785->2568|4818->2574|6855->4580|6886->4583|6977->4645|7007->4646|7040->4651|7100->4682|7130->4683|7166->4691|7342->4838|7372->4839|7413->4851|7520->4929|7550->4930|7583->4935|7612->4936|7641->4937|7742->5010|7771->5011|7875->5086|7905->5087|7936->5090|8145->5270|8175->5271|8210->5278|8438->5477|8468->5478|8506->5488|8550->5503|8580->5504|8615->5511|8809->5675|8840->5676|8876->5684|8936->5716|8965->5717|9001->5725|9030->5726|9063->5730|9093->5731|9131->5741|9771->6352|9801->6353|9837->6361|10883->7379|10912->7380|10951->7391|11108->7519|11138->7520|11178->7531|11363->7687|11393->7688|11437->7703|11547->7784|11577->7785|11613->7793|11642->7794|11672->7795|11757->7852|11786->7853|11818->7857|11847->7858|11878->7861|11907->7862|11942->7869|11971->7870|12003->7874|12073->7915|12103->7916|12134->7919|12261->8017|12291->8018|12333->8031|12592->8261|12622->8262|12659->8271|12812->8396|12841->8397|12878->8406|12908->8407|12940->8411|12969->8412|13004->8419
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|70->39|70->39|71->40|71->40|72->41|72->41|87->56|87->56|87->56|88->57|89->58|89->58|89->58|90->59|90->59|90->59|91->60|91->60|91->60|92->61|92->61|92->61|93->62|93->62|93->62|95->64|95->64|95->64|95->64|95->64|95->64|95->64|95->64|95->64|100->69|101->70|158->127|161->130|162->131|162->131|163->132|163->132|163->132|164->133|167->136|167->136|168->137|169->138|169->138|170->139|170->139|170->139|172->141|172->141|176->145|176->145|177->146|181->150|181->150|182->151|189->158|189->158|191->160|191->160|191->160|192->161|192->161|192->161|193->162|194->163|194->163|195->164|195->164|195->164|195->164|197->166|213->182|213->182|214->183|238->207|238->207|240->209|246->215|246->215|247->216|250->219|250->219|251->220|252->221|252->221|253->222|253->222|253->222|257->226|257->226|258->227|258->227|259->228|259->228|261->230|261->230|263->232|263->232|263->232|264->233|266->235|266->235|267->236|274->243|274->243|275->244|277->246|277->246|278->247|278->247|279->248|279->248|283->252
                  -- GENERATED --
              */
          