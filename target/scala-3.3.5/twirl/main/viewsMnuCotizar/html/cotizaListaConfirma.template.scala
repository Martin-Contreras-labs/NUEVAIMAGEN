
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

object cotizaListaConfirma extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "LISTADO COTIZACIONES PENDIENTES DE CONFIRMAR","")),format.raw/*13.82*/("""
		"""),format.raw/*14.3*/("""<form method="post" action="/cotizaConfirma/" onsubmit="return validarForm();">
			<input type="hidden" id="cambiosDeEstados" name="cambiosDeEstados">
			<div class="row  justify-content-md-center">
				<div class="col-xs-11 col-sm-9 col-md-9 col-lg-9">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>SUCURSAL</TH>
								<TH>COMERCIAL</TH>
								<TH>SOLUCION</TH>
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
							"""),_display_(/*35.9*/for(lista1 <- listCotizaciones) yield /*35.40*/{_display_(Seq[Any](format.raw/*35.41*/("""
								"""),format.raw/*36.9*/("""<TR>
									<td style="text-align:left;">"""),_display_(/*37.40*/lista1/*37.46*/.get(13)),format.raw/*37.54*/("""</td>
									<td style="text-align:left;">"""),_display_(/*38.40*/lista1/*38.46*/.get(14)),format.raw/*38.54*/("""</td>
									<td style="text-align:left;">"""),_display_(/*39.40*/lista1/*39.46*/.get(15)),format.raw/*39.54*/("""</td>
									<td style="text-align:center;">"""),_display_(/*40.42*/lista1/*40.48*/.get(12)),format.raw/*40.56*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="verCotizacion('"""),_display_(/*42.48*/lista1/*42.54*/.get(0)),format.raw/*42.61*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*44.34*/lista1/*44.40*/.get(1)),format.raw/*44.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td style="text-align:center;">
										<div style="display:none">"""),_display_(/*49.38*/lista1/*49.44*/.get(2)),format.raw/*49.51*/("""</div>
										"""),_display_(/*50.12*/utilities/*50.21*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*50.50*/("""
									"""),format.raw/*51.10*/("""</td>
									<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*52.75*/lista1/*52.81*/.get(3)),format.raw/*52.88*/("""')">"""),_display_(/*52.93*/lista1/*52.99*/.get(3)),format.raw/*52.106*/("""</a></td>
									<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*53.76*/lista1/*53.82*/.get(6)),format.raw/*53.89*/("""')">"""),_display_(/*53.94*/lista1/*53.100*/.get(6)),format.raw/*53.107*/("""</a></td>
									<td style="text-align:left;">"""),_display_(/*54.40*/lista1/*54.46*/.get(4)),format.raw/*54.53*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(5).equals("0"))/*56.41*/{_display_(Seq[Any](format.raw/*56.42*/("""
											"""),format.raw/*57.12*/("""--
										""")))}else/*58.16*/{_display_(Seq[Any](format.raw/*58.17*/("""
											"""),format.raw/*59.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*59.53*/lista1/*59.59*/.get(5)),format.raw/*59.66*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*62.12*/("""
									"""),format.raw/*63.10*/("""</td>
									<td  style="text-align:center;">
										<input type="checkbox" id=""""),_display_(/*65.39*/lista1/*65.45*/.get(0)),format.raw/*65.52*/("""" value ="0" onchange="cambiaEstado(id,value)"></td>
									</td>
								</TR>
				 			""")))}),format.raw/*68.10*/("""
						"""),format.raw/*69.7*/("""</tbody>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit" id="btnSubmit" value='CONFIRMAR' class="btn btn-primary btn-sm rounded-pill btn-block">
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
	
	

""")))}),format.raw/*113.2*/("""


"""),format.raw/*116.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*117.31*/("""{"""),format.raw/*117.32*/("""
		  """),format.raw/*118.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*118.36*/("""{"""),format.raw/*118.37*/("""
		    	"""),format.raw/*119.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 3, "desc" ]],
		    	"language": """),format.raw/*122.20*/("""{"""),format.raw/*122.21*/("""
		        	"""),format.raw/*123.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*124.11*/("""}"""),format.raw/*124.12*/("""
		  """),format.raw/*125.5*/("""}"""),format.raw/*125.6*/(""" """),format.raw/*125.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*127.2*/("""}"""),format.raw/*127.3*/(""");
	
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*130.43*/("""{"""),format.raw/*130.44*/("""
		"""),format.raw/*131.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*133.16*/("""{"""),format.raw/*133.17*/("""
            """),format.raw/*134.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*141.36*/("""{"""),format.raw/*141.37*/("""
	     		"""),format.raw/*142.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*144.8*/("""}"""),format.raw/*144.9*/("""
        """),format.raw/*145.9*/("""}"""),format.raw/*145.10*/(""");
	"""),format.raw/*146.2*/("""}"""),format.raw/*146.3*/("""
	
	"""),format.raw/*148.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*148.43*/("""{"""),format.raw/*148.44*/("""
		  """),format.raw/*149.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*151.2*/("""}"""),format.raw/*151.3*/("""
	
	"""),format.raw/*153.2*/("""const cambiaEstado = (id_cotizacion, valor) => """),format.raw/*153.49*/("""{"""),format.raw/*153.50*/("""
		"""),format.raw/*154.3*/("""if(valor == 0) """),format.raw/*154.18*/("""{"""),format.raw/*154.19*/("""
			"""),format.raw/*155.4*/("""document.getElementById(id_cotizacion).value = "1";
			let aux = ""+$("#cambiosDeEstados").val()+id_cotizacion + ","
			$("#cambiosDeEstados").val(aux);
		"""),format.raw/*158.3*/("""}"""),format.raw/*158.4*/("""else"""),format.raw/*158.8*/("""{"""),format.raw/*158.9*/("""
			"""),format.raw/*159.4*/("""document.getElementById(id_cotizacion).value = "0";
			let aux = ""+id_cotizacion + ","
			$("#cambiosDeEstados").val($("#cambiosDeEstados").val().replace(aux,""));
		"""),format.raw/*162.3*/("""}"""),format.raw/*162.4*/("""
		
	"""),format.raw/*164.2*/("""}"""),format.raw/*164.3*/("""
	
	"""),format.raw/*166.2*/("""const validarForm = () =>"""),format.raw/*166.27*/("""{"""),format.raw/*166.28*/("""
		"""),format.raw/*167.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*169.2*/("""}"""),format.raw/*169.3*/("""
		
		
	
	
	
"""),format.raw/*175.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCotizar/cotizaListaConfirma.scala.html
                  HASH: f1fafaef952b5362a9e315766d65e2f7b2b8a8cc
                  MATRIX: 1037->1|1265->136|1298->144|1314->152|1353->154|1382->158|1450->206|1480->211|1522->233|1551->236|1595->259|1626->263|1703->314|1802->392|1832->395|2734->1271|2781->1302|2820->1303|2856->1312|2927->1356|2942->1362|2971->1370|3043->1415|3058->1421|3087->1429|3159->1474|3174->1480|3203->1488|3277->1535|3292->1541|3321->1549|3442->1643|3457->1649|3485->1656|3611->1755|3626->1761|3654->1768|3815->1902|3830->1908|3858->1915|3903->1933|3921->1942|3971->1971|4009->1981|4116->2061|4131->2067|4159->2074|4191->2079|4206->2085|4235->2092|4347->2177|4362->2183|4390->2190|4422->2195|4438->2201|4467->2208|4543->2257|4558->2263|4586->2270|4700->2357|4739->2358|4779->2370|4816->2388|4855->2389|4895->2401|4963->2442|4978->2448|5006->2455|5130->2548|5168->2558|5281->2644|5296->2650|5324->2657|5446->2748|5480->2755|7057->4301|7088->4304|7179->4366|7209->4367|7242->4372|7302->4403|7332->4404|7368->4412|7544->4559|7574->4560|7615->4572|7722->4650|7752->4651|7785->4656|7814->4657|7843->4658|7944->4731|7973->4732|8051->4781|8081->4782|8112->4785|8239->4883|8269->4884|8311->4897|8570->5127|8600->5128|8637->5137|8790->5262|8819->5263|8856->5272|8886->5273|8918->5277|8947->5278|8979->5282|9049->5323|9079->5324|9112->5329|9244->5433|9273->5434|9305->5438|9381->5485|9411->5486|9442->5489|9486->5504|9516->5505|9548->5509|9731->5664|9760->5665|9792->5669|9821->5670|9853->5674|10048->5841|10077->5842|10110->5847|10139->5848|10171->5852|10225->5877|10255->5878|10286->5881|10370->5937|10399->5938|10440->5951
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|66->35|66->35|66->35|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|73->42|73->42|73->42|75->44|75->44|75->44|80->49|80->49|80->49|81->50|81->50|81->50|82->51|83->52|83->52|83->52|83->52|83->52|83->52|84->53|84->53|84->53|84->53|84->53|84->53|85->54|85->54|85->54|87->56|87->56|88->57|89->58|89->58|90->59|90->59|90->59|90->59|93->62|94->63|96->65|96->65|96->65|99->68|100->69|144->113|147->116|148->117|148->117|149->118|149->118|149->118|150->119|153->122|153->122|154->123|155->124|155->124|156->125|156->125|156->125|158->127|158->127|161->130|161->130|162->131|164->133|164->133|165->134|172->141|172->141|173->142|175->144|175->144|176->145|176->145|177->146|177->146|179->148|179->148|179->148|180->149|182->151|182->151|184->153|184->153|184->153|185->154|185->154|185->154|186->155|189->158|189->158|189->158|189->158|190->159|193->162|193->162|195->164|195->164|197->166|197->166|197->166|198->167|200->169|200->169|206->175
                  -- GENERATED --
              */
          