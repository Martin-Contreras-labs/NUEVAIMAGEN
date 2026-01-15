
package viewsMnuMovimientos.html

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

object hojaChequeoDetalleAgrupado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],List[tables.TipoEstado],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodegaOrigen: tables.BodegaEmpresa, listEquipBodOrigen: List[List[String]], listTipoEstado: List[tables.TipoEstado],
contactos: String, direccion: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    			
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

	"""),_display_(/*8.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.51*/("""
	
	"""),_display_(/*10.3*/modalEquipoDescripcion()),format.raw/*10.27*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "HOJA DE CHEQUEO","(agrupado por equipo)")),format.raw/*13.74*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
				<div class="noprint">
					<input type="button" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.print(); return false;" value="Imprimir">
				</div>
				<br>
			</div>
			<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
				<div class="noprint">
					<form action="/hojaChequeoAgrupadoExcel/" method="POST" onsubmit="return validarForm();">
						<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*24.60*/bodegaOrigen/*24.72*/.getId()),format.raw/*24.80*/("""">
						<input type="submit" id="btnSubmit" class="btn btn-info btn-sm rounded-pill btn-block" value="Excel">
					</form>
				</div>
				<br>
			</div>
			
			
			
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<TR>
						<th colspan="2" style="text-align: center;">AVISO</th>
						<th colspan="2" style="text-align: center;">RECEPCION</th>
						<th colspan="2" style="text-align: center;">CHEQUEO</th>
					</TR>
					<TR>
						<th colspan="1" style="text-align: center;">FECHA</th>
						<th colspan="1" style="text-align: center;">HORA</th>
						<th colspan="1" style="text-align: center;">FECHA</th>
						<th colspan="1" style="text-align: center;">HORA</th>
						<th colspan="1" style="text-align: center;">FECHA</th>
						<th colspan="1" style="text-align: center;">HORA</th>
					</TR>
					<TR>
						<td colspan="1" style="text-align: center;width:16%"><BR></td>
						<td colspan="1" style="text-align: center;width:17%"><BR></td>
						<td colspan="1" style="text-align: center;width:16%"><BR></td>
						<td colspan="1" style="text-align: center;width:17%"><BR></td>
						<td colspan="1" style="text-align: center;width:16%"><BR></td>
						<td colspan="1" style="text-align: center;width:18%"><BR></td>
					</TR>
					<TR>
						<th colspan="6" style="text-align: center;"></th>
					</TR>
					<TR>
						<th colspan="1" style="text-align: left;">CLIENTE:</th>
						<td colspan="2" style="text-align: left;">"""),_display_(/*61.50*/bodegaOrigen/*61.62*/.getNickCliente()),format.raw/*61.79*/("""</td>
						<th colspan="1" style="text-align: left;"></th>
						<td colspan="2" style="text-align: left;"></td>
					</TR>
					<TR>
						<th colspan="1" style="text-align: left;">"""),_display_(/*66.50*/mapDiccionario/*66.64*/.get("BODEGA")),format.raw/*66.78*/(""" """),format.raw/*66.79*/("""(PROYECTO):</th>
						<td colspan="2" style="text-align: left;">"""),_display_(/*67.50*/bodegaOrigen/*67.62*/.getNombre().toUpperCase()),format.raw/*67.88*/(""" """),format.raw/*67.89*/("""("""),_display_(/*67.91*/bodegaOrigen/*67.103*/.getNickProyecto()),format.raw/*67.121*/(""")</td>
						<th colspan="1" style="text-align: left;">DIRECCION PROYECTO:</th>
						<td colspan="2" style="text-align: left;">"""),_display_(/*69.50*/direccion),format.raw/*69.59*/("""</td>
					</TR>
					<TR>
						<th colspan="1" style="text-align: left;">TRANSPORTE:</th>
						<td colspan="2" style="text-align: left;"></td>
						<th colspan="1" style="text-align: left;">FONO CONTACTO TRANSPORTE:</th>
						<td colspan="2" style="text-align: left;"></td>
					</TR>
					<TR>
						<th colspan="6" style="text-align: left;">CONTACTOS """),_display_(/*78.60*/mapDiccionario/*78.74*/.get("BODEGA")),format.raw/*78.88*/(""": """),_display_(/*78.91*/contactos),format.raw/*78.100*/("""</th>
					</TR>
					<TR style="vertical-align: middle;">
						<th colspan="6" style="text-align: left;">MATERIAL PARA RECOGER</th>
					</TR>
				</table>
				<hr>
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>GRUPO</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>KG UNIT</TH>
					        <TH>M2 UNIT</TH>
					        <TH>UN</TH>
							<TH>CANTIDAD</TH>
							<TH>TOTAL KG</TH>
							"""),_display_(if(bodegaOrigen.esInterna!=1)/*96.38*/{_display_(Seq[Any](format.raw/*96.39*/("""
								"""),_display_(/*97.10*/for(titEstado <- listTipoEstado) yield /*97.42*/{_display_(Seq[Any](format.raw/*97.43*/("""
									"""),format.raw/*98.10*/("""<TH style="font-size:10px; vertical-align:middle">
										"""),_display_(/*99.12*/titEstado/*99.21*/.getSigla()),format.raw/*99.32*/("""
									"""),format.raw/*100.10*/("""</TH>
								""")))}),format.raw/*101.10*/("""
							""")))} else {null} ),format.raw/*102.9*/("""
						"""),format.raw/*103.7*/("""</TR>
					</thead>
					<tbody>
						"""),_display_(/*106.8*/for(lista1 <- listEquipBodOrigen) yield /*106.41*/{_display_(Seq[Any](format.raw/*106.42*/("""
							"""),format.raw/*107.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*108.40*/lista1/*108.46*/.get(1)),format.raw/*108.53*/("""</td>
								<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*109.74*/lista1/*109.80*/.get(2)),format.raw/*109.87*/("""');">"""),_display_(/*109.93*/lista1/*109.99*/.get(2)),format.raw/*109.106*/("""</a></td>
								<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*110.74*/lista1/*110.80*/.get(2)),format.raw/*110.87*/("""');">"""),_display_(/*110.93*/lista1/*110.99*/.get(3)),format.raw/*110.106*/("""</a></td>

								<td  class="kg" style="text-align:right;">"""),_display_(/*112.52*/lista1/*112.58*/.get(4)),format.raw/*112.65*/("""</td>
								<td  class="m2" style="text-align:right;">"""),_display_(/*113.52*/lista1/*113.58*/.get(5)),format.raw/*113.65*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*114.42*/lista1/*114.48*/.get(6)),format.raw/*114.55*/("""</td>
								"""),_display_(if(mapPermiso.get("parametro.ocultar-cant-hoja-chequeo")!=null && mapPermiso.get("parametro.ocultar-cant-hoja-chequeo").equals("1"))/*115.142*/{_display_(Seq[Any](format.raw/*115.143*/("""
									"""),format.raw/*116.10*/("""<td class="cant" style="text-align:right;"></td>
									<td class="totKg" style="text-align:right;"></td>
								""")))}else/*118.14*/{_display_(Seq[Any](format.raw/*118.15*/("""
									"""),format.raw/*119.10*/("""<td class="cant" style="text-align:right;">"""),_display_(/*119.54*/lista1/*119.60*/.get(7)),format.raw/*119.67*/("""</td>
									<td class="totKg" style="text-align:right;"></td>
								""")))}),format.raw/*121.10*/("""
								"""),_display_(if(bodegaOrigen.esInterna!=1)/*122.39*/{_display_(Seq[Any](format.raw/*122.40*/("""
									"""),_display_(/*123.11*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*123.58*/{_display_(Seq[Any](format.raw/*123.59*/("""
										"""),format.raw/*124.11*/("""<td></td>
									""")))}),format.raw/*125.11*/("""
								""")))} else {null} ),format.raw/*126.10*/("""
							"""),format.raw/*127.8*/("""</TR>
			 			""")))}),format.raw/*128.9*/("""
					"""),format.raw/*129.6*/("""</tbody>
					<tfoot>
						<TR>
							<TH>TOTALES</TH>
							<TH></TH>
							<TH></TH>
							<TH id="totKg" style="text-align:right;"></TH>
					        <TH id="totM2" style="text-align:right;"></TH>
					        <TH></TH>
							<TH id="totCant" style="text-align:right;"></TH>
							<TH id="totTotKg" style="text-align:right;"></TH>
							"""),_display_(if(bodegaOrigen.esInterna!=1)/*140.38*/{_display_(Seq[Any](format.raw/*140.39*/("""
								"""),_display_(/*141.10*/for(titEstado <- listTipoEstado) yield /*141.42*/{_display_(Seq[Any](format.raw/*141.43*/("""
									"""),format.raw/*142.10*/("""<TH style="font-size:10px; vertical-align:middle">
										
									</TH>
								""")))}),format.raw/*145.10*/("""
							""")))} else {null} ),format.raw/*146.9*/("""
						"""),format.raw/*147.7*/("""</TR>
					</tfoot>
				</table>
			</div>
		</div>
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/hojaChequeoSelectBodegaAgrupado/';">
				</div>
			</div>
		</div>
	</div>
""")))}),format.raw/*160.2*/("""


"""),format.raw/*163.1*/("""<style>
	#tablaPrincipal td:nth-child(2) """),format.raw/*164.34*/("""{"""),format.raw/*164.35*/(""" """),format.raw/*164.36*/("""text-align: center; """),format.raw/*164.56*/("""}"""),format.raw/*164.57*/("""
	"""),format.raw/*165.2*/("""#tablaPrincipal td:nth-child(3) """),format.raw/*165.34*/("""{"""),format.raw/*165.35*/(""" """),format.raw/*165.36*/("""text-align: center; """),format.raw/*165.56*/("""}"""),format.raw/*165.57*/("""
	"""),format.raw/*166.2*/("""#tablaPrincipal td:nth-child(5) """),format.raw/*166.34*/("""{"""),format.raw/*166.35*/(""" """),format.raw/*166.36*/("""text-align: right; """),format.raw/*166.55*/("""}"""),format.raw/*166.56*/("""
	"""),format.raw/*167.2*/("""#tablaPrincipal td:nth-child(6) """),format.raw/*167.34*/("""{"""),format.raw/*167.35*/(""" """),format.raw/*167.36*/("""text-align: right; """),format.raw/*167.55*/("""}"""),format.raw/*167.56*/("""
	"""),format.raw/*168.2*/("""#tablaPrincipal td:nth-child(7) """),format.raw/*168.34*/("""{"""),format.raw/*168.35*/(""" """),format.raw/*168.36*/("""text-align: center; """),format.raw/*168.56*/("""}"""),format.raw/*168.57*/("""
	"""),format.raw/*169.2*/("""#tablaPrincipal td:nth-child(8) """),format.raw/*169.34*/("""{"""),format.raw/*169.35*/(""" """),format.raw/*169.36*/("""text-align: right; """),format.raw/*169.55*/("""}"""),format.raw/*169.56*/("""
	"""),format.raw/*170.2*/("""#tablaPrincipal td:nth-child(9) """),format.raw/*170.34*/("""{"""),format.raw/*170.35*/(""" """),format.raw/*170.36*/("""text-align: right; """),format.raw/*170.55*/("""}"""),format.raw/*170.56*/("""
	"""),format.raw/*171.2*/("""#tablaPrincipal td:nth-child(10) """),format.raw/*171.35*/("""{"""),format.raw/*171.36*/(""" """),format.raw/*171.37*/("""text-align: center; """),format.raw/*171.57*/("""}"""),format.raw/*171.58*/("""
	"""),format.raw/*172.2*/("""#tablaPrincipal td:nth-child(11) """),format.raw/*172.35*/("""{"""),format.raw/*172.36*/(""" """),format.raw/*172.37*/("""text-align: center; """),format.raw/*172.57*/("""}"""),format.raw/*172.58*/("""
	"""),format.raw/*173.2*/("""#tablaPrincipal td:nth-child(12) """),format.raw/*173.35*/("""{"""),format.raw/*173.36*/(""" """),format.raw/*173.37*/("""text-align: right; """),format.raw/*173.56*/("""}"""),format.raw/*173.57*/("""
	"""),format.raw/*174.2*/("""#tablaPrincipal td:nth-child(13) """),format.raw/*174.35*/("""{"""),format.raw/*174.36*/(""" """),format.raw/*174.37*/("""text-align: right; """),format.raw/*174.56*/("""}"""),format.raw/*174.57*/("""
	"""),format.raw/*175.2*/("""#tablaPrincipal td:nth-child(14) """),format.raw/*175.35*/("""{"""),format.raw/*175.36*/(""" """),format.raw/*175.37*/("""text-align: right; """),format.raw/*175.56*/("""}"""),format.raw/*175.57*/("""
	"""),_display_(if(bodegaOrigen.esInterna!=1)/*176.32*/{_display_(Seq[Any](format.raw/*176.33*/("""
		"""),_display_(/*177.4*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*177.51*/{_display_(Seq[Any](format.raw/*177.52*/("""
			"""),format.raw/*178.4*/("""#tablaPrincipal td:nth-child("""),_display_(/*178.34*/(index+16)),format.raw/*178.44*/(""") """),format.raw/*178.46*/("""{"""),format.raw/*178.47*/(""" """),format.raw/*178.48*/("""vertical-align: middle; """),format.raw/*178.72*/("""}"""),format.raw/*178.73*/("""
		""")))}),format.raw/*179.4*/("""
	""")))} else {null} ),format.raw/*180.3*/("""
"""),format.raw/*181.1*/("""</style>

<script type="text/javascript">
	$(document).ready(function() """),format.raw/*184.31*/("""{"""),format.raw/*184.32*/("""
		"""),format.raw/*185.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*185.34*/("""{"""),format.raw/*185.35*/("""
	    	"""),format.raw/*186.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[-1, 20, 50, 100, 200], ["All", 20, 50, 100, 200]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*189.19*/("""{"""),format.raw/*189.20*/("""
	        	"""),format.raw/*190.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*191.10*/("""}"""),format.raw/*191.11*/("""
	  	"""),format.raw/*192.5*/("""}"""),format.raw/*192.6*/(""" """),format.raw/*192.7*/(""");


		const kgs = document.querySelectorAll('td.kg');
		const m2s = document.querySelectorAll('td.m2');
		const cants = document.querySelectorAll('td.cant');
		const subKg = document.querySelectorAll('td.totKg');
		let totKg = 0;
		let totTotKg = 0;
		let totM2 = 0;
		let totCant = 0;
		for (let i = 0; i < kgs.length; i++) """),format.raw/*203.40*/("""{"""),format.raw/*203.41*/("""
		  """),format.raw/*204.5*/("""const kg = kgs[i].textContent.replace(/,/g,'');
		  const m2 = m2s[i].textContent.replace(/,/g,'');
		  let cant = cants[i].textContent.replace(/,/g,'');
			if(cant == "")"""),format.raw/*207.18*/("""{"""),format.raw/*207.19*/("""
				"""),format.raw/*208.5*/("""cant = 0;
			"""),format.raw/*209.4*/("""}"""),format.raw/*209.5*/("""else"""),format.raw/*209.9*/("""{"""),format.raw/*209.10*/("""
				"""),format.raw/*210.5*/("""subKg[i].textContent = formatStandar(parseFloat(kg)*parseFloat(cant),2);
			"""),format.raw/*211.4*/("""}"""),format.raw/*211.5*/("""
		  """),format.raw/*212.5*/("""totKg += parseFloat(kg);
			totTotKg += parseFloat(kg)*parseFloat(cant);
		  totM2 += parseFloat(m2);
		  totCant += parseFloat(cant);
		"""),format.raw/*216.3*/("""}"""),format.raw/*216.4*/("""
		"""),format.raw/*217.3*/("""$("#totKg").text(formatStandar(totKg,2));
		$("#totM2").text(formatStandar(totM2,2));

		if(totCant == 0)"""),format.raw/*220.19*/("""{"""),format.raw/*220.20*/("""
			"""),format.raw/*221.4*/("""$("#totCant").text("");
			$("#totCant").text("");
		"""),format.raw/*223.3*/("""}"""),format.raw/*223.4*/("""else"""),format.raw/*223.8*/("""{"""),format.raw/*223.9*/("""
			"""),format.raw/*224.4*/("""$("#totCant").text(formatStandar(totCant,2));
			$("#totTotKg").text(formatStandar(totTotKg,2));
		"""),format.raw/*226.3*/("""}"""),format.raw/*226.4*/("""
		


		"""),format.raw/*230.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*231.2*/("""}"""),format.raw/*231.3*/(""");
	
	const validarForm = () =>"""),format.raw/*233.27*/("""{"""),format.raw/*233.28*/("""
		"""),format.raw/*234.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*236.2*/("""}"""),format.raw/*236.3*/("""
	
"""),format.raw/*238.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodegaOrigen:tables.BodegaEmpresa,listEquipBodOrigen:List[List[String]],listTipoEstado:List[tables.TipoEstado],contactos:String,direccion:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,listTipoEstado,contactos,direccion)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],List[tables.TipoEstado],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,listTipoEstado,contactos,direccion) => apply(mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,listTipoEstado,contactos,direccion)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMovimientos/hojaChequeoDetalleAgrupado.scala.html
                  HASH: 0a369601c409a731941bb4b1f6637b6267d2d381
                  MATRIX: 1107->1|1452->253|1492->268|1508->276|1547->278|1576->282|1644->330|1675->335|1720->359|1751->363|1828->414|1919->484|1949->487|2501->1012|2522->1024|2551->1032|4118->2572|4139->2584|4177->2601|4388->2785|4411->2799|4446->2813|4475->2814|4568->2880|4589->2892|4636->2918|4665->2919|4694->2921|4716->2933|4756->2951|4912->3080|4942->3089|5328->3448|5351->3462|5386->3476|5416->3479|5447->3488|6043->4057|6082->4058|6119->4068|6167->4100|6206->4101|6244->4111|6333->4173|6351->4182|6383->4193|6422->4203|6469->4218|6522->4227|6557->4234|6624->4274|6674->4307|6714->4308|6750->4316|6822->4360|6838->4366|6867->4373|6974->4452|6990->4458|7019->4465|7053->4471|7069->4477|7099->4484|7210->4567|7226->4573|7255->4580|7289->4586|7305->4592|7335->4599|7425->4661|7441->4667|7470->4674|7555->4731|7571->4737|7600->4744|7675->4791|7691->4797|7720->4804|7896->4951|7937->4952|7976->4962|8117->5083|8157->5084|8196->5094|8268->5138|8284->5144|8313->5151|8419->5225|8486->5264|8526->5265|8565->5276|8629->5323|8669->5324|8709->5335|8761->5355|8816->5365|8852->5373|8897->5387|8931->5393|9337->5771|9377->5772|9415->5782|9464->5814|9504->5815|9543->5825|9661->5911|9714->5920|9749->5927|10167->6314|10198->6317|10268->6358|10298->6359|10328->6360|10377->6380|10407->6381|10437->6383|10498->6415|10528->6416|10558->6417|10607->6437|10637->6438|10667->6440|10728->6472|10758->6473|10788->6474|10836->6493|10866->6494|10896->6496|10957->6528|10987->6529|11017->6530|11065->6549|11095->6550|11125->6552|11186->6584|11216->6585|11246->6586|11295->6606|11325->6607|11355->6609|11416->6641|11446->6642|11476->6643|11524->6662|11554->6663|11584->6665|11645->6697|11675->6698|11705->6699|11753->6718|11783->6719|11813->6721|11875->6754|11905->6755|11935->6756|11984->6776|12014->6777|12044->6779|12106->6812|12136->6813|12166->6814|12215->6834|12245->6835|12275->6837|12337->6870|12367->6871|12397->6872|12445->6891|12475->6892|12505->6894|12567->6927|12597->6928|12627->6929|12675->6948|12705->6949|12735->6951|12797->6984|12827->6985|12857->6986|12905->7005|12935->7006|12995->7038|13035->7039|13066->7043|13130->7090|13170->7091|13202->7095|13260->7125|13292->7135|13323->7137|13353->7138|13383->7139|13436->7163|13466->7164|13501->7168|13548->7171|13577->7172|13678->7244|13708->7245|13739->7248|13799->7279|13829->7280|13864->7287|14049->7443|14079->7444|14119->7455|14225->7532|14255->7533|14288->7538|14317->7539|14346->7540|14701->7866|14731->7867|14764->7872|14964->8043|14994->8044|15027->8049|15068->8062|15097->8063|15129->8067|15159->8068|15192->8073|15296->8149|15325->8150|15358->8155|15523->8292|15552->8293|15583->8296|15717->8401|15747->8402|15779->8406|15860->8459|15889->8460|15921->8464|15950->8465|15982->8469|16109->8568|16138->8569|16174->8577|16268->8643|16297->8644|16357->8675|16387->8676|16418->8679|16502->8735|16531->8736|16562->8739
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|41->10|41->10|43->12|44->13|44->13|45->14|55->24|55->24|55->24|92->61|92->61|92->61|97->66|97->66|97->66|97->66|98->67|98->67|98->67|98->67|98->67|98->67|98->67|100->69|100->69|109->78|109->78|109->78|109->78|109->78|127->96|127->96|128->97|128->97|128->97|129->98|130->99|130->99|130->99|131->100|132->101|133->102|134->103|137->106|137->106|137->106|138->107|139->108|139->108|139->108|140->109|140->109|140->109|140->109|140->109|140->109|141->110|141->110|141->110|141->110|141->110|141->110|143->112|143->112|143->112|144->113|144->113|144->113|145->114|145->114|145->114|146->115|146->115|147->116|149->118|149->118|150->119|150->119|150->119|150->119|152->121|153->122|153->122|154->123|154->123|154->123|155->124|156->125|157->126|158->127|159->128|160->129|171->140|171->140|172->141|172->141|172->141|173->142|176->145|177->146|178->147|191->160|194->163|195->164|195->164|195->164|195->164|195->164|196->165|196->165|196->165|196->165|196->165|196->165|197->166|197->166|197->166|197->166|197->166|197->166|198->167|198->167|198->167|198->167|198->167|198->167|199->168|199->168|199->168|199->168|199->168|199->168|200->169|200->169|200->169|200->169|200->169|200->169|201->170|201->170|201->170|201->170|201->170|201->170|202->171|202->171|202->171|202->171|202->171|202->171|203->172|203->172|203->172|203->172|203->172|203->172|204->173|204->173|204->173|204->173|204->173|204->173|205->174|205->174|205->174|205->174|205->174|205->174|206->175|206->175|206->175|206->175|206->175|206->175|207->176|207->176|208->177|208->177|208->177|209->178|209->178|209->178|209->178|209->178|209->178|209->178|209->178|210->179|211->180|212->181|215->184|215->184|216->185|216->185|216->185|217->186|220->189|220->189|221->190|222->191|222->191|223->192|223->192|223->192|234->203|234->203|235->204|238->207|238->207|239->208|240->209|240->209|240->209|240->209|241->210|242->211|242->211|243->212|247->216|247->216|248->217|251->220|251->220|252->221|254->223|254->223|254->223|254->223|255->224|257->226|257->226|261->230|262->231|262->231|264->233|264->233|265->234|267->236|267->236|269->238
                  -- GENERATED --
              */
          