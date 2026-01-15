
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

object reportFacturaProyectoDetalleH extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template17[Map[String,String],Map[String,String],utilities.UserMnu,String,List[List[String]],tables.BodegaEmpresa,String,String,String,Double,Double,Double,tables.Cliente,tables.Proyecto,String,Double,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, idTipoUsuario: String,
lista: List[List[String]], bodega: tables.BodegaEmpresa, esVenta: String, fechaDesde: String, fechaHasta: String,
usd: Double, eur: Double, uf: Double,
cliente: tables.Cliente, proyecto: tables.Proyecto, oc: String,
tasaIva: Double, cantDec: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.1*/("""

"""),_display_(/*8.2*/main("")/*8.10*/ {_display_(Seq[Any](format.raw/*8.12*/("""

	"""),_display_(/*10.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*10.51*/("""
	
	"""),_display_(/*12.3*/modalEquipoDescripcion()),format.raw/*12.27*/("""
	"""),_display_(/*13.3*/modalVerCotizacion()),format.raw/*13.23*/("""
	
	"""),format.raw/*15.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*16.4*/barraTitulo(mapDiccionario, "DETALLE EP/FACTURA PROFORMA SIMPLE " + mapDiccionario.get("BODEGA") + "/PROYECTO: " + bodega.getNombre(),
			"PERIODO: desde " + utilities.Fechas.DDMMAA(fechaDesde) +" --- hasta: " +  utilities.Fechas.DDMMAA(fechaHasta))),format.raw/*17.115*/("""

				"""),format.raw/*19.5*/("""<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td style="width: 25%">
"""),format.raw/*23.38*/("""
"""),format.raw/*24.49*/("""
"""),format.raw/*25.80*/("""
"""),format.raw/*26.22*/("""
"""),format.raw/*27.60*/("""
"""),format.raw/*28.44*/("""
"""),format.raw/*29.54*/("""
"""),format.raw/*30.19*/("""
							"""),format.raw/*31.8*/("""</td>
							<td>
								<div style="text-align: center;">
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
							<td width="25%">
								HACER """),_display_(/*48.16*/mapDiccionario/*48.30*/.get("PROFORMA")),format.raw/*48.46*/(""":
								<button id="btnArr" type="button" class="btn btn-mini btn-info" tabindex="-1"
								onclick=" this.disabled = true;
								if(parseFloat(neto) > 0)"""),format.raw/*51.33*/("""{"""),format.raw/*51.34*/("""
									"""),format.raw/*52.10*/("""document.getElementById('formProforma').submit();
								"""),format.raw/*53.9*/("""}"""),format.raw/*53.10*/("""else"""),format.raw/*53.14*/("""{"""),format.raw/*53.15*/("""
									"""),format.raw/*54.10*/("""alertify.alert('No hay valor de alquiler a emitir', function () """),format.raw/*54.74*/("""{"""),format.raw/*54.75*/("""}"""),format.raw/*54.76*/(""");
								"""),format.raw/*55.9*/("""}"""),format.raw/*55.10*/("""">
									ALQUILERES
								</button>
							</td>
							</td>
						</tr>
					</table>
				</div>

				<table class="table table-sm table-bordered table-condensed table-fluid">
					<TR>
						<td style= "text-align: left; vertical-align:top; width: 44%;">
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								"""),_display_(if(!cliente.rut.equals(null))/*68.39*/{_display_(Seq[Any](format.raw/*68.40*/("""
									"""),format.raw/*69.10*/("""<tr style= "background-color: #F2F5A9;">
										<th style="text-align:left;">CLIENTE</th>
										<th style="text-align:left;">"""),_display_(/*71.41*/cliente/*71.48*/.rut),format.raw/*71.52*/(""" """),format.raw/*71.53*/("""-- """),_display_(/*71.57*/cliente/*71.64*/.nombre),format.raw/*71.71*/("""</th>
									</tr>
								""")))} else {null} ),format.raw/*73.10*/("""
								"""),format.raw/*74.9*/("""<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">"""),_display_(/*75.40*/mapDiccionario/*75.54*/.get("BODEGA")),format.raw/*75.68*/("""</th>
									<TH style="text-align:left;">"""),_display_(/*76.40*/bodega/*76.46*/.nombre.toUpperCase()),format.raw/*76.67*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">PROYECTO</th>
									<th style="text-align:left;">"""),_display_(/*80.40*/proyecto/*80.48*/.nickName.toUpperCase()),format.raw/*80.71*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">PERIODO</th>
									<th style="text-align:left;">desde """),_display_(/*84.46*/utilities/*84.55*/.Fechas.DDMMAA(fechaDesde)),format.raw/*84.81*/(""" """),format.raw/*84.82*/("""hasta """),_display_(/*84.89*/utilities/*84.98*/.Fechas.DDMMAA(fechaHasta)),format.raw/*84.124*/("""</TH>
								</tr>
								<tr style= "background-color: #F2F5A9;">
									<th style="text-align:left;">COMERCIAL</th>
									<th style="text-align:left;">"""),_display_(/*88.40*/bodega/*88.46*/.comercial),format.raw/*88.56*/("""</TH>
								</tr>
							</table>

						</td>
						<td>&nbsp;</td>
						<td colspan="7" style= "text-align: left;  width: 34%;">
							<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
								<thead style="background-color: #dddddd">
									<th colspan="2">TOTALES</th>
								</thead>
								<tbody>
									<tr>
										<td style="text-align:left;">NETO:</td>
										<td style="text-align:right;" id="NETO">445555</td>
									</tr>
									<tr>
										<td style="text-align:left;">"""),_display_(/*105.41*/mapDiccionario/*105.55*/.get("IVA")),format.raw/*105.66*/(""" """),_display_(/*105.68*/(tasaIva*100)),format.raw/*105.81*/("""%:</td>
										<td style="text-align:right;" id="IVA">232323</td>
									</tr>
									<tr>
										<th style="text-align:left;">TOTAL:</th>
										<th style="text-align:right;" id="TOTAL">232323</th>
									</tr>
								</tbody>
							</table>
						</td>
					</TR>
				</table>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #dddddd">
					        """),_display_(/*121.15*/for((nivel1,index1) <- lista.zipWithIndex) yield /*121.57*/ {_display_(Seq[Any](format.raw/*121.59*/("""
								"""),_display_(if(index1 < 4)/*122.24*/{_display_(Seq[Any](format.raw/*122.25*/("""
									"""),format.raw/*123.10*/("""<TR>
										"""),_display_(/*124.12*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*124.55*/ {_display_(Seq[Any](format.raw/*124.57*/("""
											"""),_display_(if(index2==12 || index2==14)/*125.41*/{_display_(Seq[Any](format.raw/*125.42*/("""
												"""),format.raw/*126.13*/("""<td style="text-align: center; min-width: 80px">"""),_display_(/*126.62*/nivel2),format.raw/*126.68*/("""</td>
											""")))}else/*127.17*/{_display_(Seq[Any](format.raw/*127.18*/("""
												"""),format.raw/*128.13*/("""<td style="text-align: center">"""),_display_(/*128.45*/nivel2),format.raw/*128.51*/("""</td>
											""")))}),format.raw/*129.13*/("""
										""")))}),format.raw/*130.12*/("""
									"""),format.raw/*131.10*/("""</TR>
								""")))} else {null} ),format.raw/*132.10*/("""
							""")))}),format.raw/*133.9*/("""
						"""),format.raw/*134.7*/("""</thead>
						<tbody>
							"""),_display_(/*136.9*/for((nivel1,index1) <- lista.zipWithIndex) yield /*136.51*/ {_display_(Seq[Any](format.raw/*136.53*/("""
								"""),_display_(if(index1 > 3)/*137.24*/{_display_(Seq[Any](format.raw/*137.25*/("""
									"""),format.raw/*138.10*/("""<TR>
										"""),_display_(/*139.12*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*139.55*/ {_display_(Seq[Any](format.raw/*139.57*/("""
											"""),_display_(if(index2 == 1)/*140.28*/{_display_(Seq[Any](format.raw/*140.29*/("""
												"""),_display_(if(nivel2.equals("0") || nivel2.equals(""))/*141.57*/{_display_(Seq[Any](format.raw/*141.58*/("""
													"""),format.raw/*142.14*/("""<td style="text-align: center;">--</td>
												""")))}else/*143.18*/{_display_(Seq[Any](format.raw/*143.19*/("""
													"""),format.raw/*144.14*/("""<td style="text-align: center;">"""),_display_(/*144.47*/nivel2),format.raw/*144.53*/("""</td>
												""")))}),format.raw/*145.14*/("""
											""")))}else if(index2 < 4)/*146.32*/ {_display_(Seq[Any](format.raw/*146.34*/("""
												"""),_display_(if(index2 == 2 || index2 ==3)/*147.43*/{_display_(Seq[Any](format.raw/*147.44*/("""
													"""),format.raw/*148.14*/("""<td>"""),_display_(/*148.19*/nivel2),format.raw/*148.25*/("""</td>
												""")))}else/*149.18*/{_display_(Seq[Any](format.raw/*149.19*/("""
													"""),format.raw/*150.14*/("""<td>"""),_display_(/*150.19*/nivel2),format.raw/*150.25*/("""</td>
												""")))}),format.raw/*151.14*/("""
											""")))}else/*152.17*/{_display_(Seq[Any](format.raw/*152.18*/("""
												"""),format.raw/*153.13*/("""<td style="text-align: right;">"""),_display_(/*153.45*/nivel2),format.raw/*153.51*/("""</td>
											""")))}),format.raw/*154.13*/("""
										""")))}),format.raw/*155.12*/("""
									"""),format.raw/*156.10*/("""</TR>
								""")))} else {null} ),format.raw/*157.10*/("""
							 """)))}),format.raw/*158.10*/("""
						"""),format.raw/*159.7*/("""</tbody>
						

					</table>
				</div>
			</div>

	
	<form id="excel" class="formulario" method="post" action="/reportFacturaProyectoDetalleHExcel/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*168.49*/bodega/*168.55*/.getId()),format.raw/*168.63*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*169.50*/fechaDesde),format.raw/*169.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*170.50*/fechaHasta),format.raw/*170.60*/("""">
		<input type="hidden" name="esVenta" value=""""),_display_(/*171.47*/esVenta),format.raw/*171.54*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*172.42*/uf),format.raw/*172.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*173.43*/usd),format.raw/*173.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*174.43*/eur),format.raw/*174.46*/("""">
	</form>

	<form id="formProforma" class="formulario" method="post" action="/reportFacturaProyectoDetalleHProforma/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*178.49*/bodega/*178.55*/.getId()),format.raw/*178.63*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*179.50*/fechaDesde),format.raw/*179.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*180.50*/fechaHasta),format.raw/*180.60*/("""">
		<input type="hidden" name="esVenta" value=""""),_display_(/*181.47*/esVenta),format.raw/*181.54*/("""">
		<input type="hidden" name="uf" value=""""),_display_(/*182.42*/uf),format.raw/*182.44*/("""">
		<input type="hidden" name="usd" value=""""),_display_(/*183.43*/usd),format.raw/*183.46*/("""">
		<input type="hidden" name="eur" value=""""),_display_(/*184.43*/eur),format.raw/*184.46*/("""">
		<input type="hidden" name="tipo" value="ALQUILERES">
		<input type="hidden" id="profneto" name="neto">
		<input type="hidden" id="profiva" name="iva">
		<input type="hidden" id="proftotal" name="total">

		<input type="hidden" id="profAlq" name="alq">
		<input type="hidden" id="profVta" name="vta" value="0">
		<input type="hidden" id="profServ" name="serv" value="0">
	</form>


""")))}),format.raw/*196.2*/("""


"""),format.raw/*199.1*/("""<script type="text/javascript">
		let neto = 0;
	$(document).ready(function() """),format.raw/*201.31*/("""{"""),format.raw/*201.32*/("""
		
		"""),format.raw/*203.3*/("""var tabla = document.getElementById("tablaPrincipal");
			for(var k=0;k<tabla.rows[0].cells.length;k++)"""),format.raw/*204.49*/("""{"""),format.raw/*204.50*/("""
				"""),format.raw/*205.5*/("""tabla.rows[tabla.rows.length-1].cells[k].style.backgroundColor="#dddddd";
			"""),format.raw/*206.4*/("""}"""),format.raw/*206.5*/("""
			"""),format.raw/*207.4*/("""let totArr = 0;
			"""),_display_(if(lista.size()>0)/*208.23*/{_display_(Seq[Any](format.raw/*208.24*/("""
				"""),format.raw/*209.5*/("""let val = """"),_display_(/*209.17*/lista/*209.22*/.get(lista.size()-1).get(10)),format.raw/*209.50*/("""";
				val = val.replace(/,/g,'');
				totArr = val;
			""")))} else {null} ),format.raw/*212.5*/("""

			"""),format.raw/*214.4*/("""neto = totArr;
			let iva = neto * parseFloat(""""),_display_(/*215.34*/tasaIva),format.raw/*215.41*/("""");
			let total = parseFloat(neto) + parseFloat(iva);
			$("#NETO").text(formatStandar(neto,""""),_display_(/*217.41*/cantDec),format.raw/*217.48*/(""""));
			$("#IVA").text(formatStandar(iva,""""),_display_(/*218.39*/cantDec),format.raw/*218.46*/(""""));
			$("#TOTAL").text(formatStandar(total,""""),_display_(/*219.43*/cantDec),format.raw/*219.50*/(""""));

		$("#profneto").val(formatStandar(neto,""""),_display_(/*221.43*/cantDec),format.raw/*221.50*/(""""));
		$("#profiva").val(formatStandar(iva,""""),_display_(/*222.41*/cantDec),format.raw/*222.48*/(""""));
		$("#proftotal").val(formatStandar(total,""""),_display_(/*223.45*/cantDec),format.raw/*223.52*/(""""));

		$("#profAlq").val(formatStandar(neto,""""),_display_(/*225.42*/cantDec),format.raw/*225.49*/(""""));

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*228.2*/("""}"""),format.raw/*228.3*/(""");



	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,idTipoUsuario:String,lista:List[List[String]],bodega:tables.BodegaEmpresa,esVenta:String,fechaDesde:String,fechaHasta:String,usd:Double,eur:Double,uf:Double,cliente:tables.Cliente,proyecto:tables.Proyecto,oc:String,tasaIva:Double,cantDec:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,idTipoUsuario,lista,bodega,esVenta,fechaDesde,fechaHasta,usd,eur,uf,cliente,proyecto,oc,tasaIva,cantDec)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,List[List[String]],tables.BodegaEmpresa,String,String,String,Double,Double,Double,tables.Cliente,tables.Proyecto,String,Double,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,idTipoUsuario,lista,bodega,esVenta,fechaDesde,fechaHasta,usd,eur,uf,cliente,proyecto,oc,tasaIva,cantDec) => apply(mapDiccionario,mapPermiso,userMnu,idTipoUsuario,lista,bodega,esVenta,fechaDesde,fechaHasta,usd,eur,uf,cliente,proyecto,oc,tasaIva,cantDec)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportFacturaProyectoDetalleH.scala.html
                  HASH: ac802eaac0cb5eab2927e3ff8a6e8bb6b794569d
                  MATRIX: 1169->1|1630->369|1658->372|1674->380|1713->382|1743->386|1812->434|1843->439|1888->463|1917->466|1958->486|1989->490|2066->541|2337->790|2370->796|2526->961|2555->1010|2584->1090|2613->1112|2642->1172|2671->1216|2700->1270|2729->1289|2764->1297|3396->1902|3419->1916|3456->1932|3644->2092|3673->2093|3711->2103|3796->2161|3825->2162|3857->2166|3886->2167|3924->2177|4016->2241|4045->2242|4074->2243|4112->2254|4141->2255|4562->2649|4601->2650|4639->2660|4799->2793|4815->2800|4840->2804|4869->2805|4900->2809|4916->2816|4944->2823|5018->2853|5054->2862|5161->2942|5184->2956|5219->2970|5291->3015|5306->3021|5348->3042|5535->3202|5552->3210|5596->3233|5788->3398|5806->3407|5853->3433|5882->3434|5916->3441|5934->3450|5982->3476|6170->3637|6185->3643|6216->3653|6787->4196|6811->4210|6844->4221|6874->4223|6909->4236|7446->4745|7505->4787|7546->4789|7598->4813|7638->4814|7677->4824|7721->4840|7781->4883|7822->4885|7891->4926|7931->4927|7973->4940|8050->4989|8078->4995|8120->5017|8160->5018|8202->5031|8262->5063|8290->5069|8340->5087|8384->5099|8423->5109|8483->5124|8523->5133|8558->5140|8616->5171|8675->5213|8716->5215|8768->5239|8808->5240|8847->5250|8891->5266|8951->5309|8992->5311|9048->5339|9088->5340|9173->5397|9213->5398|9256->5412|9333->5469|9373->5470|9416->5484|9477->5517|9505->5523|9556->5542|9608->5574|9649->5576|9720->5619|9760->5620|9803->5634|9836->5639|9864->5645|9907->5668|9947->5669|9990->5683|10023->5688|10051->5694|10102->5713|10139->5730|10179->5731|10221->5744|10281->5776|10309->5782|10359->5800|10403->5812|10442->5822|10502->5837|10544->5847|10579->5854|10808->6055|10824->6061|10854->6069|10934->6121|10966->6131|11046->6183|11078->6193|11155->6242|11184->6249|11256->6293|11280->6295|11353->6340|11378->6343|11451->6388|11476->6391|11673->6560|11689->6566|11719->6574|11799->6626|11831->6636|11911->6688|11943->6698|12020->6747|12049->6754|12121->6798|12145->6800|12218->6845|12243->6848|12316->6893|12341->6896|12759->7283|12790->7286|12897->7364|12927->7365|12961->7371|13093->7474|13123->7475|13156->7480|13261->7557|13290->7558|13322->7562|13388->7600|13428->7601|13461->7606|13501->7618|13516->7623|13566->7651|13667->7708|13700->7713|13776->7761|13805->7768|13928->7863|13957->7870|14028->7913|14057->7920|14132->7967|14161->7974|14237->8022|14266->8029|14339->8074|14368->8081|14445->8130|14474->8137|14549->8184|14578->8191|14681->8266|14710->8267
                  LINES: 28->1|37->6|39->8|39->8|39->8|41->10|41->10|43->12|43->12|44->13|44->13|46->15|47->16|48->17|50->19|54->23|55->24|56->25|57->26|58->27|59->28|60->29|61->30|62->31|79->48|79->48|79->48|82->51|82->51|83->52|84->53|84->53|84->53|84->53|85->54|85->54|85->54|85->54|86->55|86->55|99->68|99->68|100->69|102->71|102->71|102->71|102->71|102->71|102->71|102->71|104->73|105->74|106->75|106->75|106->75|107->76|107->76|107->76|111->80|111->80|111->80|115->84|115->84|115->84|115->84|115->84|115->84|115->84|119->88|119->88|119->88|136->105|136->105|136->105|136->105|136->105|152->121|152->121|152->121|153->122|153->122|154->123|155->124|155->124|155->124|156->125|156->125|157->126|157->126|157->126|158->127|158->127|159->128|159->128|159->128|160->129|161->130|162->131|163->132|164->133|165->134|167->136|167->136|167->136|168->137|168->137|169->138|170->139|170->139|170->139|171->140|171->140|172->141|172->141|173->142|174->143|174->143|175->144|175->144|175->144|176->145|177->146|177->146|178->147|178->147|179->148|179->148|179->148|180->149|180->149|181->150|181->150|181->150|182->151|183->152|183->152|184->153|184->153|184->153|185->154|186->155|187->156|188->157|189->158|190->159|199->168|199->168|199->168|200->169|200->169|201->170|201->170|202->171|202->171|203->172|203->172|204->173|204->173|205->174|205->174|209->178|209->178|209->178|210->179|210->179|211->180|211->180|212->181|212->181|213->182|213->182|214->183|214->183|215->184|215->184|227->196|230->199|232->201|232->201|234->203|235->204|235->204|236->205|237->206|237->206|238->207|239->208|239->208|240->209|240->209|240->209|240->209|243->212|245->214|246->215|246->215|248->217|248->217|249->218|249->218|250->219|250->219|252->221|252->221|253->222|253->222|254->223|254->223|256->225|256->225|259->228|259->228
                  -- GENERATED --
              */
          