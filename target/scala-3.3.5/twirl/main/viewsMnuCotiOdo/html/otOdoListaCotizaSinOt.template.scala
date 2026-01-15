
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

object otOdoListaCotizaSinOt extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "LISTADO DE COTIZACIONES ODO","(SIN "+mapDiccionario.get("ORDEN_DE_TRABAJO")+")")),format.raw/*13.113*/("""
			"""),format.raw/*14.4*/("""<input type="hidden" id="cambiosDeEstados" name="cambiosDeEstados">
			<div class="row  justify-content-md-center">
				<div class="col-xs-11 col-sm-9 col-md-9 col-lg-9">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>Nro.COTI</TH>
								<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
								<TH>CLIENTE</TH>
								<TH>PROYECTO</TH>
								<TH>OBSERVACIONES</TH>
								<TH>DOC<br>ADJ</TH>
								<TH>SELECT</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*30.9*/for(lista1 <- listCotizaciones) yield /*30.40*/{_display_(Seq[Any](format.raw/*30.41*/("""
								"""),format.raw/*31.9*/("""<TR>
									<td style="text-align:center;">
										<a href="#" onclick="verCotizacion('"""),_display_(/*33.48*/lista1/*33.54*/.get(0)),format.raw/*33.61*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*35.34*/lista1/*35.40*/.get(1)),format.raw/*35.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td style="text-align:center;">
										<div style="display:none">"""),_display_(/*40.38*/lista1/*40.44*/.get(2)),format.raw/*40.51*/("""</div>
										"""),_display_(/*41.12*/utilities/*41.21*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*41.50*/("""
									"""),format.raw/*42.10*/("""</td>
									<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*43.75*/lista1/*43.81*/.get(3)),format.raw/*43.88*/("""')">"""),_display_(/*43.93*/lista1/*43.99*/.get(3)),format.raw/*43.106*/("""</a></td>
									<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*44.76*/lista1/*44.82*/.get(6)),format.raw/*44.89*/("""')">"""),_display_(/*44.94*/lista1/*44.100*/.get(6)),format.raw/*44.107*/("""</a></td>
									<td style="text-align:left;">"""),_display_(/*45.40*/lista1/*45.46*/.get(4)),format.raw/*45.53*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(5).equals("0"))/*47.41*/{_display_(Seq[Any](format.raw/*47.42*/("""
											"""),format.raw/*48.12*/("""--
										""")))}else/*49.16*/{_display_(Seq[Any](format.raw/*49.17*/("""
											"""),format.raw/*50.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*50.53*/lista1/*50.59*/.get(5)),format.raw/*50.66*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*53.12*/("""
									"""),format.raw/*54.10*/("""</td>
									<td  style="text-align:center;">
										<form id="otHacer_"""),_display_(/*56.30*/lista1/*56.36*/.get(0)),format.raw/*56.43*/("""" method="post" action="/routes2/otOdoHacer/">
											<input type="hidden" name="id_cotiOdo" value=""""),_display_(/*57.59*/lista1/*57.65*/.get(0)),format.raw/*57.72*/("""">
											<a href="#" onclick='document.getElementById("otHacer_"""),_display_(/*58.67*/lista1/*58.73*/.get(0)),format.raw/*58.80*/("""").submit();'>
												<kbd style="background-color: #73C6B6">select</kbd>
											</a>
										</form>
									</td>
								</TR>
				 			""")))}),format.raw/*64.10*/("""
						"""),format.raw/*65.7*/("""</tbody>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
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
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	

""")))}),format.raw/*105.2*/("""


"""),format.raw/*108.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*109.31*/("""{"""),format.raw/*109.32*/("""
		  """),format.raw/*110.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*110.36*/("""{"""),format.raw/*110.37*/("""
		    	"""),format.raw/*111.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "desc" ],[ 0, "desc" ]],
		    	"language": """),format.raw/*114.20*/("""{"""),format.raw/*114.21*/("""
		        	"""),format.raw/*115.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*116.11*/("""}"""),format.raw/*116.12*/("""
		  """),format.raw/*117.5*/("""}"""),format.raw/*117.6*/(""" """),format.raw/*117.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*119.2*/("""}"""),format.raw/*119.3*/(""");
	
	
	const verCotizacion = (id_cotiOdo) => """),format.raw/*122.40*/("""{"""),format.raw/*122.41*/("""
		"""),format.raw/*123.3*/("""var formData = new FormData();
	  	formData.append('id_cotiOdo',id_cotiOdo);
        $.ajax("""),format.raw/*125.16*/("""{"""),format.raw/*125.17*/("""
            """),format.raw/*126.13*/("""url: "/routes2/verCotiOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*133.36*/("""{"""),format.raw/*133.37*/("""
	     		"""),format.raw/*134.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*136.8*/("""}"""),format.raw/*136.9*/("""
        """),format.raw/*137.9*/("""}"""),format.raw/*137.10*/(""");
	"""),format.raw/*138.2*/("""}"""),format.raw/*138.3*/("""
	
	"""),format.raw/*140.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*140.43*/("""{"""),format.raw/*140.44*/("""
		  """),format.raw/*141.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*143.2*/("""}"""),format.raw/*143.3*/("""
	
		
	
	
	
"""),format.raw/*149.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCotiOdo/otOdoListaCotizaSinOt.scala.html
                  HASH: 93bf58a7f95755f542d4ac36aeb850e06115de70
                  MATRIX: 1039->1|1267->136|1300->144|1316->152|1355->154|1384->158|1452->206|1482->211|1524->233|1553->236|1597->259|1628->263|1705->314|1836->423|1867->427|2506->1040|2553->1071|2592->1072|2628->1081|2748->1174|2763->1180|2791->1187|2917->1286|2932->1292|2960->1299|3121->1433|3136->1439|3164->1446|3209->1464|3227->1473|3277->1502|3315->1512|3422->1592|3437->1598|3465->1605|3497->1610|3512->1616|3541->1623|3653->1708|3668->1714|3696->1721|3728->1726|3744->1732|3773->1739|3849->1788|3864->1794|3892->1801|4006->1888|4045->1889|4085->1901|4122->1919|4161->1920|4201->1932|4269->1973|4284->1979|4312->1986|4436->2079|4474->2089|4578->2166|4593->2172|4621->2179|4753->2284|4768->2290|4796->2297|4892->2366|4907->2372|4935->2379|5117->2530|5151->2537|6536->3891|6567->3894|6658->3956|6688->3957|6721->3962|6781->3993|6811->3994|6847->4002|7037->4163|7067->4164|7108->4176|7215->4254|7245->4255|7278->4260|7307->4261|7336->4262|7437->4335|7466->4336|7541->4382|7571->4383|7602->4386|7723->4478|7753->4479|7795->4492|8059->4727|8089->4728|8126->4737|8279->4862|8308->4863|8345->4872|8375->4873|8407->4877|8436->4878|8468->4882|8538->4923|8568->4924|8601->4929|8733->5033|8762->5034|8802->5046
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|61->30|61->30|61->30|62->31|64->33|64->33|64->33|66->35|66->35|66->35|71->40|71->40|71->40|72->41|72->41|72->41|73->42|74->43|74->43|74->43|74->43|74->43|74->43|75->44|75->44|75->44|75->44|75->44|75->44|76->45|76->45|76->45|78->47|78->47|79->48|80->49|80->49|81->50|81->50|81->50|81->50|84->53|85->54|87->56|87->56|87->56|88->57|88->57|88->57|89->58|89->58|89->58|95->64|96->65|136->105|139->108|140->109|140->109|141->110|141->110|141->110|142->111|145->114|145->114|146->115|147->116|147->116|148->117|148->117|148->117|150->119|150->119|153->122|153->122|154->123|156->125|156->125|157->126|164->133|164->133|165->134|167->136|167->136|168->137|168->137|169->138|169->138|171->140|171->140|171->140|172->141|174->143|174->143|180->149
                  -- GENERATED --
              */
          