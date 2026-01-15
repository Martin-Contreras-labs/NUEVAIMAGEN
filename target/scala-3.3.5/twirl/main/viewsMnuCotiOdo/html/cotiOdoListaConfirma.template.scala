
package viewsMnuCotiOdo.html

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

object cotiOdoListaConfirma extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listCotizaciones: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "LISTADO COTIZACIONES ODO PENDIENTES DE CONFIRMAR","")),format.raw/*13.86*/("""
		"""),format.raw/*14.3*/("""<form method="post" action="/routes2/cotiOdoConfirma/">
			<input type="hidden" id="cambiosDeEstados" name="cambiosDeEstados">
			<div class="row  justify-content-md-center">
				<div class="col-xs-11 col-sm-9 col-md-9 col-lg-9">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH style="min-width:100px;">Fecha/Hora (Creado)<br>Coordinated Universal Time (UTC)</TH>
								<TH>Nro.COTI</TH>
								<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
								<TH>CLIENTE</TH>
								<TH>PROYECTO</TH>
								<TH>OBSERVACIONES</TH>
								<TH>DOC<br>ADJ</TH>
								<TH>CONFIRMAR</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*32.9*/for(lista1 <- listCotizaciones) yield /*32.40*/{_display_(Seq[Any](format.raw/*32.41*/("""
								"""),format.raw/*33.9*/("""<TR>
									<td style="text-align:center;">"""),_display_(/*34.42*/lista1/*34.48*/.get(11)),format.raw/*34.56*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="verCotizacion('"""),_display_(/*36.48*/lista1/*36.54*/.get(0)),format.raw/*36.61*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*38.34*/lista1/*38.40*/.get(1)),format.raw/*38.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td style="text-align:center;">
										<div style="display:none">"""),_display_(/*43.38*/lista1/*43.44*/.get(2)),format.raw/*43.51*/("""</div>
										"""),_display_(/*44.12*/utilities/*44.21*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*44.50*/("""
									"""),format.raw/*45.10*/("""</td>
									<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*46.75*/lista1/*46.81*/.get(3)),format.raw/*46.88*/("""')">"""),_display_(/*46.93*/lista1/*46.99*/.get(3)),format.raw/*46.106*/("""</a></td>
									<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*47.76*/lista1/*47.82*/.get(6)),format.raw/*47.89*/("""')">"""),_display_(/*47.94*/lista1/*47.100*/.get(6)),format.raw/*47.107*/("""</a></td>
									<td style="text-align:left;">"""),_display_(/*48.40*/lista1/*48.46*/.get(4)),format.raw/*48.53*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(5).equals("0"))/*50.41*/{_display_(Seq[Any](format.raw/*50.42*/("""
											"""),format.raw/*51.12*/("""--
										""")))}else/*52.16*/{_display_(Seq[Any](format.raw/*52.17*/("""
											"""),format.raw/*53.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*53.53*/lista1/*53.59*/.get(5)),format.raw/*53.66*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*56.12*/("""
									"""),format.raw/*57.10*/("""</td>
									<td  style="text-align:center;">
										<input type="checkbox" id=""""),_display_(/*59.39*/lista1/*59.45*/.get(0)),format.raw/*59.52*/("""" value ="0" onchange="cambiaEstado(id,value)"></td>
									</td>
								</TR>
				 			""")))}),format.raw/*62.10*/("""
						"""),format.raw/*63.7*/("""</tbody>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit" value='CONFIRMAR' class="btn btn-primary btn-sm rounded-pill btn-block">
					</div>
				</div>
			</div>
		</form>
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
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	

""")))}),format.raw/*107.2*/("""


"""),format.raw/*110.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*111.31*/("""{"""),format.raw/*111.32*/("""
		  """),format.raw/*112.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*112.36*/("""{"""),format.raw/*112.37*/("""
		    	"""),format.raw/*113.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*116.20*/("""{"""),format.raw/*116.21*/("""
		        	"""),format.raw/*117.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*118.11*/("""}"""),format.raw/*118.12*/("""
		  """),format.raw/*119.5*/("""}"""),format.raw/*119.6*/(""" """),format.raw/*119.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*121.2*/("""}"""),format.raw/*121.3*/(""");
	
	
	const verCotizacion = (id_cotiOdo) => """),format.raw/*124.40*/("""{"""),format.raw/*124.41*/("""
		"""),format.raw/*125.3*/("""var formData = new FormData();
	  	formData.append('id_cotiOdo',id_cotiOdo);
        $.ajax("""),format.raw/*127.16*/("""{"""),format.raw/*127.17*/("""
            """),format.raw/*128.13*/("""url: "/routes2/verCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*135.36*/("""{"""),format.raw/*135.37*/("""
	     		"""),format.raw/*136.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*138.8*/("""}"""),format.raw/*138.9*/("""
        """),format.raw/*139.9*/("""}"""),format.raw/*139.10*/(""");
	"""),format.raw/*140.2*/("""}"""),format.raw/*140.3*/("""
	
	"""),format.raw/*142.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*142.43*/("""{"""),format.raw/*142.44*/("""
		  """),format.raw/*143.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*145.2*/("""}"""),format.raw/*145.3*/("""
	
	"""),format.raw/*147.2*/("""const cambiaEstado = (id_cotiOdo, valor) => """),format.raw/*147.46*/("""{"""),format.raw/*147.47*/("""
		"""),format.raw/*148.3*/("""if(valor == 0) """),format.raw/*148.18*/("""{"""),format.raw/*148.19*/("""
			"""),format.raw/*149.4*/("""document.getElementById(id_cotiOdo).value = "1";
			let aux = ""+$("#cambiosDeEstados").val()+id_cotiOdo + ","
			$("#cambiosDeEstados").val(aux);
		"""),format.raw/*152.3*/("""}"""),format.raw/*152.4*/("""else"""),format.raw/*152.8*/("""{"""),format.raw/*152.9*/("""
			"""),format.raw/*153.4*/("""document.getElementById(id_cotiOdo).value = "0";
			let aux = ""+id_cotiOdo + ","
			$("#cambiosDeEstados").val($("#cambiosDeEstados").val().replace(aux,""));
		"""),format.raw/*156.3*/("""}"""),format.raw/*156.4*/("""
		
	"""),format.raw/*158.2*/("""}"""),format.raw/*158.3*/("""
		
		
	
	
	
"""),format.raw/*164.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listCotizaciones:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listCotizaciones) => apply(mapDiccionario,mapPermiso,userMnu,listCotizaciones)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/cotiOdoListaConfirma.scala.html
                  HASH: af1a2b85cedabfe58238a17a461ea9639aa9f91e
                  MATRIX: 1038->1|1266->136|1299->144|1315->152|1354->154|1383->158|1451->206|1481->211|1523->233|1552->236|1596->259|1627->263|1704->314|1807->396|1837->399|2636->1172|2683->1203|2722->1204|2758->1213|2831->1259|2846->1265|2875->1273|2996->1367|3011->1373|3039->1380|3165->1479|3180->1485|3208->1492|3369->1626|3384->1632|3412->1639|3457->1657|3475->1666|3525->1695|3563->1705|3670->1785|3685->1791|3713->1798|3745->1803|3760->1809|3789->1816|3901->1901|3916->1907|3944->1914|3976->1919|3992->1925|4021->1932|4097->1981|4112->1987|4140->1994|4254->2081|4293->2082|4333->2094|4370->2112|4409->2113|4449->2125|4517->2166|4532->2172|4560->2179|4684->2272|4722->2282|4835->2368|4850->2374|4878->2381|5000->2472|5034->2479|6596->4010|6627->4013|6718->4075|6748->4076|6781->4081|6841->4112|6871->4113|6907->4121|7097->4282|7127->4283|7168->4295|7275->4373|7305->4374|7338->4379|7367->4380|7396->4381|7497->4454|7526->4455|7601->4501|7631->4502|7662->4505|7783->4597|7813->4598|7855->4611|8119->4846|8149->4847|8186->4856|8339->4981|8368->4982|8405->4991|8435->4992|8467->4996|8496->4997|8528->5001|8598->5042|8628->5043|8661->5048|8793->5152|8822->5153|8854->5157|8927->5201|8957->5202|8988->5205|9032->5220|9062->5221|9094->5225|9271->5374|9300->5375|9332->5379|9361->5380|9393->5384|9582->5545|9611->5546|9644->5551|9673->5552|9714->5565
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|63->32|63->32|63->32|64->33|65->34|65->34|65->34|67->36|67->36|67->36|69->38|69->38|69->38|74->43|74->43|74->43|75->44|75->44|75->44|76->45|77->46|77->46|77->46|77->46|77->46|77->46|78->47|78->47|78->47|78->47|78->47|78->47|79->48|79->48|79->48|81->50|81->50|82->51|83->52|83->52|84->53|84->53|84->53|84->53|87->56|88->57|90->59|90->59|90->59|93->62|94->63|138->107|141->110|142->111|142->111|143->112|143->112|143->112|144->113|147->116|147->116|148->117|149->118|149->118|150->119|150->119|150->119|152->121|152->121|155->124|155->124|156->125|158->127|158->127|159->128|166->135|166->135|167->136|169->138|169->138|170->139|170->139|171->140|171->140|173->142|173->142|173->142|174->143|176->145|176->145|178->147|178->147|178->147|179->148|179->148|179->148|180->149|183->152|183->152|183->152|183->152|184->153|187->156|187->156|189->158|189->158|195->164
                  -- GENERATED --
              */
          