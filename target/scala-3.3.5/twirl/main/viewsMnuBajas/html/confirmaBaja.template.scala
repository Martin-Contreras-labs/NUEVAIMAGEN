
package viewsMnuBajas.html

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

object confirmaBaja extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaAconfirmar: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "CONFIRMACION DE BAJAS","(rebaja inventario)")),format.raw/*9.78*/("""
		"""),format.raw/*10.3*/("""<form class="formulario" method="post" action="/bajaConfirmaIngreso/" onsubmit="return validarForm();">
			<input type="hidden" id="cambiosDeEstados" name="cambiosDeEstados">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH style="min-width:80px;">FECHA</TH>
								<TH>ACTA</TH>
								<TH>CODIGO</TH>
								<TH>EQUIPO</TH>
								<TH>UN</TH>
								<TH>CANTIDAD</TH>
								<TH>MOTIVO</TH>
								<TH>DOC</TH>
								<TH width="80px">CONFIRMA</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*29.9*/for(lista1 <- listaAconfirmar) yield /*29.39*/{_display_(Seq[Any](format.raw/*29.40*/("""
								"""),format.raw/*30.9*/("""<TR>
									<td style="text-align:center;" width="120px">
										<div style="display:none">"""),_display_(/*32.38*/lista1/*32.44*/.get(1)),format.raw/*32.51*/("""</div>
										"""),_display_(/*33.12*/utilities/*33.21*/.Fechas.DDMMAA(lista1.get(1))),format.raw/*33.50*/("""
									"""),format.raw/*34.10*/("""</td>
									<td style="text-align:center;">"""),_display_(/*35.42*/lista1/*35.48*/.get(2)),format.raw/*35.55*/("""</td>
									<td style="text-align:left;">"""),_display_(/*36.40*/lista1/*36.46*/.get(3)),format.raw/*36.53*/("""</td>
									<td style="text-align:left;">"""),_display_(/*37.40*/lista1/*37.46*/.get(4)),format.raw/*37.53*/("""</td>
									<td style="text-align:center;">"""),_display_(/*38.42*/lista1/*38.48*/.get(5)),format.raw/*38.55*/("""</td>
									<td style="text-align:right;">"""),_display_(/*39.41*/lista1/*39.47*/.get(6)),format.raw/*39.54*/("""</td>
									<td style="text-align:leftt;" width="300px">"""),_display_(/*40.55*/lista1/*40.61*/.get(7)),format.raw/*40.68*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.get(8).equals("0"))/*42.41*/{_display_(Seq[Any](format.raw/*42.42*/("""
											"""),format.raw/*43.12*/("""--
										""")))}else/*44.16*/{_display_(Seq[Any](format.raw/*44.17*/("""
											"""),format.raw/*45.12*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*45.53*/lista1/*45.59*/.get(8)),format.raw/*45.66*/("""')">
												<kbd style="background-color: #7F8C8D">doc</kbd>
											</a>
										""")))}),format.raw/*48.12*/("""
									"""),format.raw/*49.10*/("""</td>
									<td  style="text-align:center;">
										<input type="checkbox" id=""""),_display_(/*51.39*/lista1/*51.45*/.get(0)),format.raw/*51.52*/("""" value ="0" 
											onchange="cambiaEstado(id, value, '"""),_display_(/*52.48*/lista1/*52.54*/.get(9)),format.raw/*52.61*/("""','"""),_display_(/*52.65*/lista1/*52.71*/.get(6)),format.raw/*52.78*/("""', '"""),_display_(/*52.83*/lista1/*52.89*/.get(10)),format.raw/*52.97*/("""','"""),_display_(/*52.101*/lista1/*52.107*/.get(1)),format.raw/*52.114*/("""')"></td>
									</td>
								</TR>
				 			""")))}),format.raw/*55.10*/("""
						"""),format.raw/*56.7*/("""</tbody>
					</table>
				</div>
			</div>
			<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="submit"  id="aplica" onclick="document.getElementById('aplica').style.visibility = 'hidden';" value='CONFIRMAR' class="btn btn-primary btn-sm rounded-pill btn-block">
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	

""")))}),format.raw/*79.2*/("""


"""),format.raw/*82.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*83.31*/("""{"""),format.raw/*83.32*/("""
			"""),format.raw/*84.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*84.35*/("""{"""),format.raw/*84.36*/("""
		    	"""),format.raw/*85.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[-1], ["All"]],
		    	"order": [[ 1, "desc" ]],
		    	"language": """),format.raw/*88.20*/("""{"""),format.raw/*88.21*/("""
		        	"""),format.raw/*89.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*90.11*/("""}"""),format.raw/*90.12*/("""
		  """),format.raw/*91.5*/("""}"""),format.raw/*91.6*/(""" """),format.raw/*91.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*93.2*/("""}"""),format.raw/*93.3*/(""");
	
	
	const descargaDocumento = (nombreDOC) => """),format.raw/*96.43*/("""{"""),format.raw/*96.44*/("""
		  """),format.raw/*97.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*99.2*/("""}"""),format.raw/*99.3*/("""
	
	"""),format.raw/*101.2*/("""const cambiaEstado = (id_baja, valor, id_equipo, cantidad, id_actaBaja, fecha_actaBaja) => """),format.raw/*101.93*/("""{"""),format.raw/*101.94*/("""
		"""),format.raw/*102.3*/("""cantidad = cantidad.replace(/,/g,'');
		if(valor==0) """),format.raw/*103.16*/("""{"""),format.raw/*103.17*/("""
			"""),format.raw/*104.4*/("""document.getElementById(id_baja).value ="1";
			let aux = ""+$("#cambiosDeEstados").val()+id_baja + "," + id_equipo + "," + cantidad + "," + id_actaBaja + "," + fecha_actaBaja + ";"
			$("#cambiosDeEstados").val(aux);
		"""),format.raw/*107.3*/("""}"""),format.raw/*107.4*/("""else"""),format.raw/*107.8*/("""{"""),format.raw/*107.9*/("""
			"""),format.raw/*108.4*/("""document.getElementById(id_baja).value ="0";
			let aux = ""+id_baja + "," + id_equipo + "," + cantidad + "," + id_actaBaja + "," + fecha_actaBaja + ";"
			$("#cambiosDeEstados").val($("#cambiosDeEstados").val().replace(aux,""));
		"""),format.raw/*111.3*/("""}"""),format.raw/*111.4*/("""
		
	"""),format.raw/*113.2*/("""}"""),format.raw/*113.3*/("""
		
	"""),format.raw/*115.2*/("""const validarForm = () =>"""),format.raw/*115.27*/("""{"""),format.raw/*115.28*/("""
		"""),format.raw/*116.3*/("""$("#aplica").attr('disabled',true);
		let table = $('#tablaPrincipal').DataTable();
		table.destroy();
		return(true);
	"""),format.raw/*120.2*/("""}"""),format.raw/*120.3*/("""
	
	
	
"""),format.raw/*124.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaAconfirmar:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaAconfirmar)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaAconfirmar) => apply(mapDiccionario,mapPermiso,userMnu,listaAconfirmar)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBajas/confirmaBaja.scala.html
                  HASH: c33ee90af3de856589fc7a84dceb79e2b3dca677
                  MATRIX: 1028->1|1255->135|1288->143|1304->151|1343->153|1372->157|1440->205|1468->207|1544->258|1638->332|1668->335|2443->1084|2489->1114|2528->1115|2564->1124|2688->1221|2703->1227|2731->1234|2776->1252|2794->1261|2844->1290|2882->1300|2956->1347|2971->1353|2999->1360|3071->1405|3086->1411|3114->1418|3186->1463|3201->1469|3229->1476|3303->1523|3318->1529|3346->1536|3419->1582|3434->1588|3462->1595|3549->1655|3564->1661|3592->1668|3706->1755|3745->1756|3785->1768|3822->1786|3861->1787|3901->1799|3969->1840|3984->1846|4012->1853|4136->1946|4174->1956|4287->2042|4302->2048|4330->2055|4418->2116|4433->2122|4461->2129|4492->2133|4507->2139|4535->2146|4567->2151|4582->2157|4611->2165|4643->2169|4659->2175|4688->2182|4767->2230|4801->2237|5630->3036|5660->3039|5750->3101|5779->3102|5810->3106|5869->3137|5898->3138|5933->3146|6072->3257|6101->3258|6141->3270|6247->3348|6276->3349|6308->3354|6336->3355|6364->3356|6464->3429|6492->3430|6569->3479|6598->3480|6630->3485|6761->3589|6789->3590|6821->3594|6941->3685|6971->3686|7002->3689|7084->3742|7114->3743|7146->3747|7394->3967|7423->3968|7455->3972|7484->3973|7516->3977|7776->4209|7805->4210|7838->4215|7867->4216|7900->4221|7954->4246|7984->4247|8015->4250|8163->4370|8192->4371|8227->4378
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|60->29|60->29|60->29|61->30|63->32|63->32|63->32|64->33|64->33|64->33|65->34|66->35|66->35|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|71->40|71->40|71->40|73->42|73->42|74->43|75->44|75->44|76->45|76->45|76->45|76->45|79->48|80->49|82->51|82->51|82->51|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|83->52|86->55|87->56|110->79|113->82|114->83|114->83|115->84|115->84|115->84|116->85|119->88|119->88|120->89|121->90|121->90|122->91|122->91|122->91|124->93|124->93|127->96|127->96|128->97|130->99|130->99|132->101|132->101|132->101|133->102|134->103|134->103|135->104|138->107|138->107|138->107|138->107|139->108|142->111|142->111|144->113|144->113|146->115|146->115|146->115|147->116|151->120|151->120|155->124
                  -- GENERATED --
              */
          