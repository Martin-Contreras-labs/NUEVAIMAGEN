
package viewsMnuQr.html

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
/*1.2*/import models.utilities.Fechas

object qrRevisarDatosALL extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaTransac: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

"""),_display_(/*8.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.50*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario,"REVISAR ATRIBUTOS QR","DE TODOS LOS EQUIPOS VIGENTES")),format.raw/*10.86*/("""
		"""),format.raw/*11.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
				<div class="noprint">
					<input type="button" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.print(); return false;" value="Imprimir">
				</div>
				<br>
			</div>
			<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
				<div class="noprint">
					<form action="/qrRevisarDatosAllVigentesExcel/" method="POST">
						<input type="submit" class="btn btn-info btn-sm rounded-pill btn-block" value="Exportar a Excel">
					</form>
				</div>
				<br>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="tablaAtribQr" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<th colspan="10">ATRIBUTOS ASOCIADOS AL QR</th>
						</TR>
						<TR>
							<th>CODIGO</th>
							<th>EQUIPO</th>
							<th>ATRIBUTO</th>
							<th>VALOR</th>
							<th>FECHA DE<br>VENCIMIENTO</th>
							<th>PUBLICAR</th>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*42.8*/for(lista <- listaTransac) yield /*42.34*/{_display_(Seq[Any](format.raw/*42.35*/("""
							"""),format.raw/*43.8*/("""<TR>
								<td style="text-align: left;">"""),_display_(/*44.40*/lista/*44.45*/.get(0)),format.raw/*44.52*/("""</td>
								<td style="text-align: left;">"""),_display_(/*45.40*/lista/*45.45*/.get(1)),format.raw/*45.52*/("""</td>
								<td style="text-align: left;">"""),_display_(/*46.40*/lista/*46.45*/.get(2)),format.raw/*46.52*/("""</td>
								<td style="text-align: left;">
									"""),_display_(if(lista.get(3).equals("1"))/*48.39*/{_display_(Seq[Any](format.raw/*48.40*/("""
										"""),_display_(if(!lista.get(4).equals("0"))/*49.41*/{_display_(Seq[Any](format.raw/*49.42*/(""" """),_display_(/*49.44*/lista/*49.49*/.get(4)),format.raw/*49.56*/(""" """)))} else {null} ),format.raw/*49.58*/("""
									""")))}else/*50.15*/{_display_(Seq[Any](format.raw/*50.16*/("""
										"""),_display_(if(!lista.get(4).equals("0"))/*51.41*/{_display_(Seq[Any](format.raw/*51.42*/("""
											"""),format.raw/*52.12*/("""<a href="#" onclick="verArchivo('"""),_display_(/*52.46*/lista/*52.51*/.get(4)),format.raw/*52.58*/("""','"""),_display_(/*52.62*/lista/*52.67*/.get(5)),format.raw/*52.74*/("""')">
												<kbd style="background-color: #73C6B6">Revisar """),_display_(/*53.61*/lista/*53.66*/.get(6)),format.raw/*53.73*/(""" """),format.raw/*53.74*/("""Adjunto</kbd>
											</a>
										""")))} else {null} ),format.raw/*55.12*/("""
									""")))}),format.raw/*56.11*/("""
								"""),format.raw/*57.9*/("""</td>
								<td  style="text-align:center; min-width:80px; background-color:"""),_display_(/*58.74*/lista/*58.79*/.get(7)),format.raw/*58.86*/("""">
									<div style="display:none;">"""),_display_(/*59.38*/lista/*59.43*/.get(8)),format.raw/*59.50*/("""</div>
									"""),_display_(/*60.11*/utilities/*60.20*/.Fechas.DDMMAA(lista.get(8))),format.raw/*60.48*/("""
								"""),format.raw/*61.9*/("""</td>
								<td style="text-align:center">
									<div id="cambiarEstadoPubQr_"""),_display_(/*63.39*/lista/*63.44*/.get(9)),format.raw/*63.51*/(""" """),format.raw/*63.52*/("""_"""),_display_(/*63.54*/lista/*63.59*/.get(10)),format.raw/*63.67*/("""">
										"""),_display_(if(lista.get(11).equals("1"))/*64.41*/{_display_(Seq[Any](format.raw/*64.42*/("""
											"""),format.raw/*65.12*/("""<a href="#" onclick= "qrCambiarPubQr('"""),_display_(/*65.51*/lista/*65.56*/.get(9)),format.raw/*65.63*/("""', '"""),_display_(/*65.68*/lista/*65.73*/.get(10)),format.raw/*65.81*/("""', '"""),_display_(/*65.86*/lista/*65.91*/.get(11)),format.raw/*65.99*/("""')">
												<kbd style="background-color: green">SI</kbd>
											</a>
										""")))} else {null} ),format.raw/*68.12*/("""
										"""),_display_(if(lista.get(11).equals("0"))/*69.41*/{_display_(Seq[Any](format.raw/*69.42*/("""
											"""),format.raw/*70.12*/("""<a href="#" onclick= "qrCambiarPubQr('"""),_display_(/*70.51*/lista/*70.56*/.get(9)),format.raw/*70.63*/("""', '"""),_display_(/*70.68*/lista/*70.73*/.get(10)),format.raw/*70.81*/("""', '"""),_display_(/*70.86*/lista/*70.91*/.get(11)),format.raw/*70.99*/("""')">
												<kbd style="background-color: red">NO</kbd>
											</a>
										""")))} else {null} ),format.raw/*73.12*/("""
									"""),format.raw/*74.10*/("""</div>
								</td>
							</TR>
						""")))}),format.raw/*77.8*/("""
					"""),format.raw/*78.6*/("""</tbody>
				</table>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="VOLVER" class="btn btn-success btn-sm rounded-pill btn-block" onclick="window.history.back()">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	

		<div id="muestraPDF" class="modal" role="dialog" data-backdrop="static" data-keyboard="false" aria-hidden="true" style="display: none;">
			<div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">×</span>
					        </button>
					</div>
					<div class="modal-body">
						<div class='embed-responsive' style='padding-bottom:150%'>
							<div id="rutaPDF"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
""")))}),format.raw/*110.2*/("""




"""),format.raw/*115.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*116.31*/("""{"""),format.raw/*116.32*/("""

		"""),format.raw/*118.3*/("""$('#tablaAtribQr').DataTable("""),format.raw/*118.32*/("""{"""),format.raw/*118.33*/("""
			"""),format.raw/*119.4*/(""""fixedHeader": true,
			"paging": false,
			"info": false,
			"searching": true,
			"order": [],
			"language": """),format.raw/*124.16*/("""{"""),format.raw/*124.17*/("""
				"""),format.raw/*125.5*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			"""),format.raw/*126.4*/("""}"""),format.raw/*126.5*/("""
		"""),format.raw/*127.3*/("""}"""),format.raw/*127.4*/(""" """),format.raw/*127.5*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*129.2*/("""}"""),format.raw/*129.3*/(""");
	
	const qrCambiarPubEquipo = (id_equipo, id_atributo, publico) =>"""),format.raw/*131.65*/("""{"""),format.raw/*131.66*/("""
		  """),format.raw/*132.5*/("""var formData = new FormData();
		  formData.append('id_equipo',id_equipo);
		  formData.append('id_atributo',id_atributo);
		  formData.append('publico',publico);
	      $.ajax("""),format.raw/*136.15*/("""{"""),format.raw/*136.16*/("""
	            """),format.raw/*137.14*/("""url: "/qrCambiarPubEquipo/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*144.37*/("""{"""),format.raw/*144.38*/("""
		     		"""),format.raw/*145.10*/("""if(respuesta.status)"""),format.raw/*145.30*/("""{"""),format.raw/*145.31*/("""
		     			"""),format.raw/*146.11*/("""if(publico==1)"""),format.raw/*146.25*/("""{"""),format.raw/*146.26*/("""
		     				"""),format.raw/*147.12*/("""document.getElementById("cambiarEstadoPubEquipo_"+id_equipo+"_"+id_atributo).innerHTML =
									"<a href='#' onclick='qrCambiarPubEquipo("+id_equipo+","+id_atributo+",0)'>"+
										"<kbd style='background-color: red'>NO</kbd>"+
									"</a>";
		     			"""),format.raw/*151.11*/("""}"""),format.raw/*151.12*/("""else"""),format.raw/*151.16*/("""{"""),format.raw/*151.17*/("""
		     				"""),format.raw/*152.12*/("""document.getElementById("cambiarEstadoPubEquipo_"+id_equipo+"_"+id_atributo).innerHTML =
									"<a href='#' onclick='qrCambiarPubEquipo("+id_equipo+","+id_atributo+",1)'>"+
										"<kbd style='background-color: green'>SI</kbd>"+
									"</a>";
		     			"""),format.raw/*156.11*/("""}"""),format.raw/*156.12*/("""
		     		"""),format.raw/*157.10*/("""}"""),format.raw/*157.11*/("""
		     	"""),format.raw/*158.9*/("""}"""),format.raw/*158.10*/("""
	        """),format.raw/*159.10*/("""}"""),format.raw/*159.11*/(""");
	  """),format.raw/*160.4*/("""}"""),format.raw/*160.5*/("""
	  
	  """),format.raw/*162.4*/("""const qrCambiarPubQr = (id_equipo, id_qrAtributoEquipo, publico) =>"""),format.raw/*162.71*/("""{"""),format.raw/*162.72*/("""
		  """),format.raw/*163.5*/("""var formData = new FormData();
		  formData.append('id_equipo',id_equipo);
		  formData.append('id_qrAtributoEquipo',id_qrAtributoEquipo);
		  formData.append('publico',publico);
	      $.ajax("""),format.raw/*167.15*/("""{"""),format.raw/*167.16*/("""
	            """),format.raw/*168.14*/("""url: "/qrCambiarPubQr/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*175.37*/("""{"""),format.raw/*175.38*/("""
		     		"""),format.raw/*176.10*/("""if(respuesta.status)"""),format.raw/*176.30*/("""{"""),format.raw/*176.31*/("""
		     			"""),format.raw/*177.11*/("""if(publico==1)"""),format.raw/*177.25*/("""{"""),format.raw/*177.26*/("""
		     				"""),format.raw/*178.12*/("""document.getElementById("cambiarEstadoPubQr_"+id_equipo+" _"+id_qrAtributoEquipo).innerHTML = 
									"<a href='#' onclick='qrCambiarPubQr("+id_equipo+","+id_qrAtributoEquipo+",0)'>"+
										"<kbd style='background-color: red'>NO</kbd>"+
									"</a>";
		     			"""),format.raw/*182.11*/("""}"""),format.raw/*182.12*/("""else"""),format.raw/*182.16*/("""{"""),format.raw/*182.17*/("""
		     				"""),format.raw/*183.12*/("""document.getElementById("cambiarEstadoPubQr_"+id_equipo+" _"+id_qrAtributoEquipo).innerHTML = 
									"<a href='#' onclick='qrCambiarPubQr("+id_equipo+","+id_qrAtributoEquipo+",1)'>"+
										"<kbd style='background-color: green'>SI</kbd>"+
									"</a>";
		     			"""),format.raw/*187.11*/("""}"""),format.raw/*187.12*/("""
		     		"""),format.raw/*188.10*/("""}"""),format.raw/*188.11*/("""
		     	"""),format.raw/*189.9*/("""}"""),format.raw/*189.10*/("""
	        """),format.raw/*190.10*/("""}"""),format.raw/*190.11*/(""");
	  """),format.raw/*191.4*/("""}"""),format.raw/*191.5*/("""
	  
	  """),format.raw/*193.4*/("""const verArchivo = (archivo,extencion) => """),format.raw/*193.46*/("""{"""),format.raw/*193.47*/("""
			"""),format.raw/*194.4*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+archivo+"' type='application/pdf' width='100%' height='720'></object>";
			$('#muestraPDF').modal('show');
	  """),format.raw/*196.4*/("""}"""),format.raw/*196.5*/("""



"""),format.raw/*200.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaTransac:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaTransac)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaTransac) => apply(mapDiccionario,mapPermiso,userMnu,listaTransac)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/qrRevisarDatosALL.scala.html
                  HASH: 92f4cf4d0bfd0614afb8542e114d69232bfe9b25
                  MATRIX: 653->1|1068->33|1292->164|1325->172|1341->180|1380->182|1408->185|1476->233|1504->235|1581->286|1684->368|1714->371|2831->1462|2873->1488|2912->1489|2947->1497|3018->1541|3032->1546|3060->1553|3132->1598|3146->1603|3174->1610|3246->1655|3260->1660|3288->1667|3398->1750|3437->1751|3505->1792|3544->1793|3573->1795|3587->1800|3615->1807|3661->1809|3695->1824|3734->1825|3802->1866|3841->1867|3881->1879|3942->1913|3956->1918|3984->1925|4015->1929|4029->1934|4057->1941|4149->2006|4163->2011|4191->2018|4220->2019|4305->2060|4347->2071|4383->2080|4489->2159|4503->2164|4531->2171|4598->2211|4612->2216|4640->2223|4684->2240|4702->2249|4751->2277|4787->2286|4897->2369|4911->2374|4939->2381|4968->2382|4997->2384|5011->2389|5040->2397|5110->2440|5149->2441|5189->2453|5255->2492|5269->2497|5297->2504|5329->2509|5343->2514|5372->2522|5404->2527|5418->2532|5447->2540|5581->2630|5649->2671|5688->2672|5728->2684|5794->2723|5808->2728|5836->2735|5868->2740|5882->2745|5911->2753|5943->2758|5957->2763|5986->2771|6118->2859|6156->2869|6227->2910|6260->2916|7285->3910|7318->3915|7409->3977|7439->3978|7471->3982|7529->4011|7559->4012|7591->4016|7732->4128|7762->4129|7795->4134|7894->4205|7923->4206|7954->4209|7983->4210|8012->4211|8113->4284|8142->4285|8240->4354|8270->4355|8303->4360|8509->4537|8539->4538|8582->4552|8849->4790|8879->4791|8918->4801|8967->4821|8997->4822|9037->4833|9080->4847|9110->4848|9151->4860|9440->5120|9470->5121|9503->5125|9533->5126|9574->5138|9865->5400|9895->5401|9934->5411|9964->5412|10001->5421|10031->5422|10070->5432|10100->5433|10134->5439|10163->5440|10199->5448|10295->5515|10325->5516|10358->5521|10580->5714|10610->5715|10653->5729|10916->5963|10946->5964|10985->5974|11034->5994|11064->5995|11104->6006|11147->6020|11177->6021|11218->6033|11517->6303|11547->6304|11580->6308|11610->6309|11651->6321|11952->6593|11982->6594|12021->6604|12051->6605|12088->6614|12118->6615|12157->6625|12187->6626|12221->6632|12250->6633|12286->6641|12357->6683|12387->6684|12419->6688|12628->6869|12657->6870|12689->6874
                  LINES: 24->1|29->2|35->4|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|42->11|73->42|73->42|73->42|74->43|75->44|75->44|75->44|76->45|76->45|76->45|77->46|77->46|77->46|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|82->51|82->51|83->52|83->52|83->52|83->52|83->52|83->52|83->52|84->53|84->53|84->53|84->53|86->55|87->56|88->57|89->58|89->58|89->58|90->59|90->59|90->59|91->60|91->60|91->60|92->61|94->63|94->63|94->63|94->63|94->63|94->63|94->63|95->64|95->64|96->65|96->65|96->65|96->65|96->65|96->65|96->65|96->65|96->65|96->65|99->68|100->69|100->69|101->70|101->70|101->70|101->70|101->70|101->70|101->70|101->70|101->70|101->70|104->73|105->74|108->77|109->78|141->110|146->115|147->116|147->116|149->118|149->118|149->118|150->119|155->124|155->124|156->125|157->126|157->126|158->127|158->127|158->127|160->129|160->129|162->131|162->131|163->132|167->136|167->136|168->137|175->144|175->144|176->145|176->145|176->145|177->146|177->146|177->146|178->147|182->151|182->151|182->151|182->151|183->152|187->156|187->156|188->157|188->157|189->158|189->158|190->159|190->159|191->160|191->160|193->162|193->162|193->162|194->163|198->167|198->167|199->168|206->175|206->175|207->176|207->176|207->176|208->177|208->177|208->177|209->178|213->182|213->182|213->182|213->182|214->183|218->187|218->187|219->188|219->188|220->189|220->189|221->190|221->190|222->191|222->191|224->193|224->193|224->193|225->194|227->196|227->196|231->200
                  -- GENERATED --
              */
          