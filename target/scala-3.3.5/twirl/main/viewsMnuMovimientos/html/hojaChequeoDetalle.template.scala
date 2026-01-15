
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

object hojaChequeoDetalle extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],List[tables.TipoEstado],String,String,play.twirl.api.HtmlFormat.Appendable] {

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
	"""),_display_(/*11.3*/modalVerCotizacion()),format.raw/*11.23*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "HOJA DE CHEQUEO","(agrupado por equipo y cotización)")),format.raw/*14.87*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
				<div class="noprint">
						<input type="button" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.print(); return false;" value="Imprimir">
				</div>
				<br>
			</div>
			<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
				<div class="noprint">
					<form action="/hojaChequeoExcel/" method="POST" onsubmit="return validarForm();">
						<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*25.60*/bodegaOrigen/*25.72*/.getId()),format.raw/*25.80*/("""">
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
						<td colspan="2" style="text-align: left;">"""),_display_(/*59.50*/bodegaOrigen/*59.62*/.getNickCliente()),format.raw/*59.79*/("""</td>
						<th colspan="1" style="text-align: left;"></th>
						<td colspan="2" style="text-align: left;"></td>
					</TR>
					<TR>
						<th colspan="1" style="text-align: left;">"""),_display_(/*64.50*/mapDiccionario/*64.64*/.get("BODEGA")),format.raw/*64.78*/(""" """),format.raw/*64.79*/("""(PROYECTO):</th>
						<td colspan="2" style="text-align: left;">"""),_display_(/*65.50*/bodegaOrigen/*65.62*/.getNombre().toUpperCase()),format.raw/*65.88*/(""" """),format.raw/*65.89*/("""("""),_display_(/*65.91*/bodegaOrigen/*65.103*/.getNickProyecto()),format.raw/*65.121*/(""")</td>
						<th colspan="1" style="text-align: left;">DIRECCION PROYECTO:</th>
						<td colspan="2" style="text-align: left;">"""),_display_(/*67.50*/direccion),format.raw/*67.59*/("""</td>
					</TR>
					<TR>
						<th colspan="1" style="text-align: left;">TRANSPORTE:</th>
						<td colspan="2" style="text-align: left;"></td>
						<th colspan="1" style="text-align: left;">FONO CONTACTO TRANSPORTE:</th>
						<td colspan="2" style="text-align: left;"></td>
					</TR>
					<TR>
						<th colspan="6" style="text-align: left;">CONTACTOS """),_display_(/*76.60*/mapDiccionario/*76.74*/.get("BODEGA")),format.raw/*76.88*/(""": """),_display_(/*76.91*/contactos),format.raw/*76.100*/("""</th>
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
							<TH>Nro.Coti</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>KG UNIT</TH>
							<TH>M2 UNIT</TH>
							<TH>UN</TH>
							<TH>CANTIDAD</TH>
							<TH>TOTAL KG</TH>
							"""),_display_(if(bodegaOrigen.esInterna!=1)/*95.38*/{_display_(Seq[Any](format.raw/*95.39*/("""
								"""),_display_(/*96.10*/for(titEstado <- listTipoEstado) yield /*96.42*/{_display_(Seq[Any](format.raw/*96.43*/("""
									"""),format.raw/*97.10*/("""<TH style="font-size:10px; vertical-align:middle">
										"""),_display_(/*98.12*/titEstado/*98.21*/.getSigla()),format.raw/*98.32*/("""
									"""),format.raw/*99.10*/("""</TH>
								""")))}),format.raw/*100.10*/("""
							""")))} else {null} ),format.raw/*101.9*/("""
						"""),format.raw/*102.7*/("""</TR>
					</thead>
					<tbody>
						"""),_display_(/*105.8*/for(lista1 <- listEquipBodOrigen) yield /*105.41*/{_display_(Seq[Any](format.raw/*105.42*/("""
							"""),format.raw/*106.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*107.40*/lista1/*107.46*/.get(2)),format.raw/*107.53*/("""</td>
								
								"""),_display_(if(lista1.get(3).equals("0") || lista1.get(3).equals(""))/*109.67*/{_display_(Seq[Any](format.raw/*109.68*/("""
									"""),format.raw/*110.10*/("""<td style="text-align: center;">--</td>
								""")))}else/*111.14*/{_display_(Seq[Any](format.raw/*111.15*/("""
									"""),format.raw/*112.10*/("""<td style="text-align: center;">"""),_display_(/*112.43*/lista1/*112.49*/.get(3)),format.raw/*112.56*/("""</td>
								""")))}),format.raw/*113.10*/("""
								
								"""),format.raw/*115.9*/("""<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*115.74*/lista1/*115.80*/.get(4)),format.raw/*115.87*/("""');">"""),_display_(/*115.93*/lista1/*115.99*/.get(4)),format.raw/*115.106*/("""</a></td>
								<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*116.74*/lista1/*116.80*/.get(4)),format.raw/*116.87*/("""');">"""),_display_(/*116.93*/lista1/*116.99*/.get(5)),format.raw/*116.106*/("""</a></td>

								<td  class="kg"  style="text-align:right;">"""),_display_(/*118.53*/lista1/*118.59*/.get(6)),format.raw/*118.66*/("""</td>
								<td  class="m2"  style="text-align:right;">"""),_display_(/*119.53*/lista1/*119.59*/.get(7)),format.raw/*119.66*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*120.42*/lista1/*120.48*/.get(8)),format.raw/*120.55*/("""</td>
								"""),_display_(if(mapPermiso.get("parametro.ocultar-cant-hoja-chequeo")!=null && mapPermiso.get("parametro.ocultar-cant-hoja-chequeo").equals("1"))/*121.142*/{_display_(Seq[Any](format.raw/*121.143*/("""
									"""),format.raw/*122.10*/("""<td class="cant" style="text-align:right;"></td>
									<td class="totKg" style="text-align:right;"></td>
								""")))}else/*124.14*/{_display_(Seq[Any](format.raw/*124.15*/("""
									"""),format.raw/*125.10*/("""<td class="cant" style="text-align:right;">"""),_display_(/*125.54*/lista1/*125.60*/.get(9)),format.raw/*125.67*/("""</td>
									<td class="totKg" style="text-align:right;"></td>
								""")))}),format.raw/*127.10*/("""
								"""),_display_(if(bodegaOrigen.esInterna!=1)/*128.39*/{_display_(Seq[Any](format.raw/*128.40*/("""
									"""),_display_(/*129.11*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*129.58*/{_display_(Seq[Any](format.raw/*129.59*/("""
										"""),format.raw/*130.11*/("""<td></td>
									""")))}),format.raw/*131.11*/("""
								""")))} else {null} ),format.raw/*132.10*/("""
							"""),format.raw/*133.8*/("""</TR>
			 			""")))}),format.raw/*134.9*/("""
					"""),format.raw/*135.6*/("""</tbody>
					<tfoot>
						<TR>
							<TH>TOTALES</TH>
							<TH></TH>
							<TH></TH>
							<TH></TH>
							<TH id="totKg" style="text-align:right;"></TH>
					        <TH id="totM2" style="text-align:right;"></TH>
					        <TH></TH>
							<TH id="totCant" style="text-align:right;"></TH>
							<TH id="totTotKg" style="text-align:right;"></TH>
							"""),_display_(if(bodegaOrigen.esInterna!=1)/*147.38*/{_display_(Seq[Any](format.raw/*147.39*/("""
								"""),_display_(/*148.10*/for(titEstado <- listTipoEstado) yield /*148.42*/{_display_(Seq[Any](format.raw/*148.43*/("""
									"""),format.raw/*149.10*/("""<TH style="font-size:10px; vertical-align:middle">
										
									</TH>
								""")))}),format.raw/*152.10*/("""
							""")))} else {null} ),format.raw/*153.9*/("""
						"""),format.raw/*154.7*/("""</TR>
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
""")))}),format.raw/*167.2*/("""


"""),format.raw/*170.1*/("""<style>
	#tablaPrincipal td:nth-child(2) """),format.raw/*171.34*/("""{"""),format.raw/*171.35*/(""" """),format.raw/*171.36*/("""text-align: center; """),format.raw/*171.56*/("""}"""),format.raw/*171.57*/("""
	"""),format.raw/*172.2*/("""#tablaPrincipal td:nth-child(3) """),format.raw/*172.34*/("""{"""),format.raw/*172.35*/(""" """),format.raw/*172.36*/("""text-align: center; """),format.raw/*172.56*/("""}"""),format.raw/*172.57*/("""
	"""),format.raw/*173.2*/("""#tablaPrincipal td:nth-child(5) """),format.raw/*173.34*/("""{"""),format.raw/*173.35*/(""" """),format.raw/*173.36*/("""text-align: right; """),format.raw/*173.55*/("""}"""),format.raw/*173.56*/("""
	"""),format.raw/*174.2*/("""#tablaPrincipal td:nth-child(6) """),format.raw/*174.34*/("""{"""),format.raw/*174.35*/(""" """),format.raw/*174.36*/("""text-align: right; """),format.raw/*174.55*/("""}"""),format.raw/*174.56*/("""
	"""),format.raw/*175.2*/("""#tablaPrincipal td:nth-child(7) """),format.raw/*175.34*/("""{"""),format.raw/*175.35*/(""" """),format.raw/*175.36*/("""text-align: center; """),format.raw/*175.56*/("""}"""),format.raw/*175.57*/("""
	"""),format.raw/*176.2*/("""#tablaPrincipal td:nth-child(8) """),format.raw/*176.34*/("""{"""),format.raw/*176.35*/(""" """),format.raw/*176.36*/("""text-align: right; """),format.raw/*176.55*/("""}"""),format.raw/*176.56*/("""
	"""),format.raw/*177.2*/("""#tablaPrincipal td:nth-child(9) """),format.raw/*177.34*/("""{"""),format.raw/*177.35*/(""" """),format.raw/*177.36*/("""text-align: right; """),format.raw/*177.55*/("""}"""),format.raw/*177.56*/("""
	"""),format.raw/*178.2*/("""#tablaPrincipal td:nth-child(10) """),format.raw/*178.35*/("""{"""),format.raw/*178.36*/(""" """),format.raw/*178.37*/("""text-align: center; """),format.raw/*178.57*/("""}"""),format.raw/*178.58*/("""
	"""),format.raw/*179.2*/("""#tablaPrincipal td:nth-child(11) """),format.raw/*179.35*/("""{"""),format.raw/*179.36*/(""" """),format.raw/*179.37*/("""text-align: center; """),format.raw/*179.57*/("""}"""),format.raw/*179.58*/("""
	"""),format.raw/*180.2*/("""#tablaPrincipal td:nth-child(12) """),format.raw/*180.35*/("""{"""),format.raw/*180.36*/(""" """),format.raw/*180.37*/("""text-align: right; """),format.raw/*180.56*/("""}"""),format.raw/*180.57*/("""
	"""),format.raw/*181.2*/("""#tablaPrincipal td:nth-child(13) """),format.raw/*181.35*/("""{"""),format.raw/*181.36*/(""" """),format.raw/*181.37*/("""text-align: right; """),format.raw/*181.56*/("""}"""),format.raw/*181.57*/("""
	"""),format.raw/*182.2*/("""#tablaPrincipal td:nth-child(14) """),format.raw/*182.35*/("""{"""),format.raw/*182.36*/(""" """),format.raw/*182.37*/("""text-align: right; """),format.raw/*182.56*/("""}"""),format.raw/*182.57*/("""
	"""),_display_(if(bodegaOrigen.esInterna!=1)/*183.32*/{_display_(Seq[Any](format.raw/*183.33*/("""
		"""),_display_(/*184.4*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*184.51*/{_display_(Seq[Any](format.raw/*184.52*/("""
			"""),format.raw/*185.4*/("""#tablaPrincipal td:nth-child("""),_display_(/*185.34*/(index+16)),format.raw/*185.44*/(""") """),format.raw/*185.46*/("""{"""),format.raw/*185.47*/(""" """),format.raw/*185.48*/("""vertical-align: middle; """),format.raw/*185.72*/("""}"""),format.raw/*185.73*/("""
		""")))}),format.raw/*186.4*/("""
	""")))} else {null} ),format.raw/*187.3*/("""
"""),format.raw/*188.1*/("""</style>

<script type="text/javascript">
	$(document).ready(function() """),format.raw/*191.31*/("""{"""),format.raw/*191.32*/("""
		"""),format.raw/*192.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*192.34*/("""{"""),format.raw/*192.35*/("""
	    	"""),format.raw/*193.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[-1, 20, 50, 100, 200], ["All", 20, 50, 100, 200]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*196.19*/("""{"""),format.raw/*196.20*/("""
	        	"""),format.raw/*197.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*198.10*/("""}"""),format.raw/*198.11*/("""
	  	"""),format.raw/*199.5*/("""}"""),format.raw/*199.6*/(""" """),format.raw/*199.7*/(""");

		const kgs = document.querySelectorAll('td.kg');
		const m2s = document.querySelectorAll('td.m2');
		const cants = document.querySelectorAll('td.cant');
		const subKg = document.querySelectorAll('td.totKg');
		let totKg = 0;
		let totTotKg = 0;
		let totM2 = 0;
		let totCant = 0;
		for (let i = 0; i < kgs.length; i++) """),format.raw/*209.40*/("""{"""),format.raw/*209.41*/("""
		  """),format.raw/*210.5*/("""const kg = kgs[i].textContent.replace(/,/g,'');
		  const m2 = m2s[i].textContent.replace(/,/g,'');
		  let cant = cants[i].textContent.replace(/,/g,''); 
			if(cant == "")"""),format.raw/*213.18*/("""{"""),format.raw/*213.19*/("""
				"""),format.raw/*214.5*/("""cant = 0;
			"""),format.raw/*215.4*/("""}"""),format.raw/*215.5*/("""else"""),format.raw/*215.9*/("""{"""),format.raw/*215.10*/("""
				"""),format.raw/*216.5*/("""subKg[i].textContent = formatStandar(parseFloat(kg)*parseFloat(cant),2);
			"""),format.raw/*217.4*/("""}"""),format.raw/*217.5*/("""
		  """),format.raw/*218.5*/("""totKg += parseFloat(kg);
			totTotKg += parseFloat(kg)*parseFloat(cant);
		  totM2 += parseFloat(m2);
		  totCant += parseFloat(cant);
		"""),format.raw/*222.3*/("""}"""),format.raw/*222.4*/("""
		"""),format.raw/*223.3*/("""$("#totKg").text(formatStandar(totKg));
		$("#totM2").text(formatStandar(totM2));


		if(totCant == 0)"""),format.raw/*227.19*/("""{"""),format.raw/*227.20*/("""
			"""),format.raw/*228.4*/("""$("#totCant").text("");
			$("#totTotKg").text("");
		"""),format.raw/*230.3*/("""}"""),format.raw/*230.4*/("""else"""),format.raw/*230.8*/("""{"""),format.raw/*230.9*/("""
			"""),format.raw/*231.4*/("""$("#totCant").text(formatStandar(totCant,2));
			$("#totTotKg").text(formatStandar(totTotKg,2));
		"""),format.raw/*233.3*/("""}"""),format.raw/*233.4*/("""
		
		
		"""),format.raw/*236.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*237.2*/("""}"""),format.raw/*237.3*/(""");
	
	const validarForm = () =>"""),format.raw/*239.27*/("""{"""),format.raw/*239.28*/("""
		"""),format.raw/*240.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*242.2*/("""}"""),format.raw/*242.3*/("""
	
"""),format.raw/*244.1*/("""</script>


		
		
	
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
                  SOURCE: app/viewsMnuMovimientos/hojaChequeoDetalle.scala.html
                  HASH: 89ab1adeebd60177dc946fc3ee507a81fbdd4260
                  MATRIX: 1099->1|1444->253|1484->268|1500->276|1539->278|1568->282|1636->330|1667->335|1712->359|1741->362|1782->382|1813->386|1890->437|1994->520|2024->523|2569->1041|2590->1053|2619->1061|4174->2589|4195->2601|4233->2618|4444->2802|4467->2816|4502->2830|4531->2831|4624->2897|4645->2909|4692->2935|4721->2936|4750->2938|4772->2950|4812->2968|4968->3097|4998->3106|5384->3465|5407->3479|5442->3493|5472->3496|5503->3505|6112->4087|6151->4088|6188->4098|6236->4130|6275->4131|6313->4141|6402->4203|6420->4212|6452->4223|6490->4233|6537->4248|6590->4257|6625->4264|6692->4304|6742->4337|6782->4338|6818->4346|6890->4390|6906->4396|6935->4403|7044->4484|7084->4485|7123->4495|7196->4548|7236->4549|7275->4559|7336->4592|7352->4598|7381->4605|7428->4620|7474->4638|7567->4703|7583->4709|7612->4716|7646->4722|7662->4728|7692->4735|7803->4818|7819->4824|7848->4831|7882->4837|7898->4843|7928->4850|8019->4913|8035->4919|8064->4926|8150->4984|8166->4990|8195->4997|8270->5044|8286->5050|8315->5057|8491->5204|8532->5205|8571->5215|8712->5336|8752->5337|8791->5347|8863->5391|8879->5397|8908->5404|9014->5478|9081->5517|9121->5518|9160->5529|9224->5576|9264->5577|9304->5588|9356->5608|9411->5618|9447->5626|9492->5640|9526->5646|9949->6041|9989->6042|10027->6052|10076->6084|10116->6085|10155->6095|10273->6181|10326->6190|10361->6197|10779->6584|10810->6587|10880->6628|10910->6629|10940->6630|10989->6650|11019->6651|11049->6653|11110->6685|11140->6686|11170->6687|11219->6707|11249->6708|11279->6710|11340->6742|11370->6743|11400->6744|11448->6763|11478->6764|11508->6766|11569->6798|11599->6799|11629->6800|11677->6819|11707->6820|11737->6822|11798->6854|11828->6855|11858->6856|11907->6876|11937->6877|11967->6879|12028->6911|12058->6912|12088->6913|12136->6932|12166->6933|12196->6935|12257->6967|12287->6968|12317->6969|12365->6988|12395->6989|12425->6991|12487->7024|12517->7025|12547->7026|12596->7046|12626->7047|12656->7049|12718->7082|12748->7083|12778->7084|12827->7104|12857->7105|12887->7107|12949->7140|12979->7141|13009->7142|13057->7161|13087->7162|13117->7164|13179->7197|13209->7198|13239->7199|13287->7218|13317->7219|13347->7221|13409->7254|13439->7255|13469->7256|13517->7275|13547->7276|13607->7308|13647->7309|13678->7313|13742->7360|13782->7361|13814->7365|13872->7395|13904->7405|13935->7407|13965->7408|13995->7409|14048->7433|14078->7434|14113->7438|14160->7441|14189->7442|14290->7514|14320->7515|14351->7518|14411->7549|14441->7550|14476->7557|14661->7713|14691->7714|14731->7725|14837->7802|14867->7803|14900->7808|14929->7809|14958->7810|15312->8135|15342->8136|15375->8141|15576->8313|15606->8314|15639->8319|15680->8332|15709->8333|15741->8337|15771->8338|15804->8343|15908->8419|15937->8420|15970->8425|16135->8562|16164->8563|16195->8566|16326->8668|16356->8669|16388->8673|16470->8727|16499->8728|16531->8732|16560->8733|16592->8737|16719->8836|16748->8837|16785->8846|16879->8912|16908->8913|16968->8944|16998->8945|17029->8948|17113->9004|17142->9005|17173->9008
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|56->25|56->25|56->25|90->59|90->59|90->59|95->64|95->64|95->64|95->64|96->65|96->65|96->65|96->65|96->65|96->65|96->65|98->67|98->67|107->76|107->76|107->76|107->76|107->76|126->95|126->95|127->96|127->96|127->96|128->97|129->98|129->98|129->98|130->99|131->100|132->101|133->102|136->105|136->105|136->105|137->106|138->107|138->107|138->107|140->109|140->109|141->110|142->111|142->111|143->112|143->112|143->112|143->112|144->113|146->115|146->115|146->115|146->115|146->115|146->115|146->115|147->116|147->116|147->116|147->116|147->116|147->116|149->118|149->118|149->118|150->119|150->119|150->119|151->120|151->120|151->120|152->121|152->121|153->122|155->124|155->124|156->125|156->125|156->125|156->125|158->127|159->128|159->128|160->129|160->129|160->129|161->130|162->131|163->132|164->133|165->134|166->135|178->147|178->147|179->148|179->148|179->148|180->149|183->152|184->153|185->154|198->167|201->170|202->171|202->171|202->171|202->171|202->171|203->172|203->172|203->172|203->172|203->172|203->172|204->173|204->173|204->173|204->173|204->173|204->173|205->174|205->174|205->174|205->174|205->174|205->174|206->175|206->175|206->175|206->175|206->175|206->175|207->176|207->176|207->176|207->176|207->176|207->176|208->177|208->177|208->177|208->177|208->177|208->177|209->178|209->178|209->178|209->178|209->178|209->178|210->179|210->179|210->179|210->179|210->179|210->179|211->180|211->180|211->180|211->180|211->180|211->180|212->181|212->181|212->181|212->181|212->181|212->181|213->182|213->182|213->182|213->182|213->182|213->182|214->183|214->183|215->184|215->184|215->184|216->185|216->185|216->185|216->185|216->185|216->185|216->185|216->185|217->186|218->187|219->188|222->191|222->191|223->192|223->192|223->192|224->193|227->196|227->196|228->197|229->198|229->198|230->199|230->199|230->199|240->209|240->209|241->210|244->213|244->213|245->214|246->215|246->215|246->215|246->215|247->216|248->217|248->217|249->218|253->222|253->222|254->223|258->227|258->227|259->228|261->230|261->230|261->230|261->230|262->231|264->233|264->233|267->236|268->237|268->237|270->239|270->239|271->240|273->242|273->242|275->244
                  -- GENERATED --
              */
          