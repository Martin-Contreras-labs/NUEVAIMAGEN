
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

object hojaChequeoDetallePorGrupo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],List[tables.TipoEstado],List[Long],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodegaOrigen: tables.BodegaEmpresa, listEquipBodOrigen: List[List[String]], listTipoEstado: List[tables.TipoEstado], idGrupos: List[Long],
contactos: String, direccion: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    			
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

	"""),_display_(/*8.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.51*/("""
	
	"""),_display_(/*10.3*/modalEquipoDescripcion()),format.raw/*10.27*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "HOJA DE CHEQUEO POR GRUPOS SELECCIONADOS","(agrupado por equipo)")),format.raw/*13.99*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
				<div class="noprint">
					<input type="button" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.print(); return false;" value="Imprimir">
				</div>
				<br>
			</div>
			<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
				<div class="noprint">
					<form action="/hojaChequeoDetallePorGrupoExcel/" method="POST" onsubmit="return validarForm();">
						<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*24.60*/bodegaOrigen/*24.72*/.getId()),format.raw/*24.80*/("""">
						"""),_display_(/*25.8*/for(lista <- idGrupos) yield /*25.30*/{_display_(Seq[Any](format.raw/*25.31*/("""
							"""),format.raw/*26.8*/("""<input type="hidden" name="idGrupos[]" value=""""),_display_(/*26.55*/lista),format.raw/*26.60*/("""">
						""")))}),format.raw/*27.8*/("""
						"""),format.raw/*28.7*/("""<input type="submit" id="btnSubmit" class="btn btn-info btn-sm rounded-pill btn-block" value="Excel">
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
						<td colspan="2" style="text-align: left;">"""),_display_(/*64.50*/bodegaOrigen/*64.62*/.getNickCliente()),format.raw/*64.79*/("""</td>
						<th colspan="1" style="text-align: left;"></th>
						<td colspan="2" style="text-align: left;"></td>
					</TR>
					<TR>
						<th colspan="1" style="text-align: left;">"""),_display_(/*69.50*/mapDiccionario/*69.64*/.get("BODEGA")),format.raw/*69.78*/(""" """),format.raw/*69.79*/("""(PROYECTO):</th>
						<td colspan="2" style="text-align: left;">"""),_display_(/*70.50*/bodegaOrigen/*70.62*/.getNombre().toUpperCase()),format.raw/*70.88*/(""" """),format.raw/*70.89*/("""("""),_display_(/*70.91*/bodegaOrigen/*70.103*/.getNickProyecto()),format.raw/*70.121*/(""")</td>
						<th colspan="1" style="text-align: left;">DIRECCION PROYECTO:</th>
						<td colspan="2" style="text-align: left;">"""),_display_(/*72.50*/direccion),format.raw/*72.59*/("""</td>
					</TR>
					<TR>
						<th colspan="1" style="text-align: left;">TRANSPORTE:</th>
						<td colspan="2" style="text-align: left;"></td>
						<th colspan="1" style="text-align: left;">FONO CONTACTO TRANSPORTE:</th>
						<td colspan="2" style="text-align: left;"></td>
					</TR>
					<TR>
						<th colspan="6" style="text-align: left;">CONTACTOS """),_display_(/*81.60*/mapDiccionario/*81.74*/.get("BODEGA")),format.raw/*81.88*/(""": """),_display_(/*81.91*/contactos),format.raw/*81.100*/("""</th>
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
							"""),_display_(if(bodegaOrigen.esInterna!=1)/*99.38*/{_display_(Seq[Any](format.raw/*99.39*/("""
								"""),_display_(/*100.10*/for(titEstado <- listTipoEstado) yield /*100.42*/{_display_(Seq[Any](format.raw/*100.43*/("""
									"""),format.raw/*101.10*/("""<TH style="font-size:10px; vertical-align:middle">
										"""),_display_(/*102.12*/titEstado/*102.21*/.getSigla()),format.raw/*102.32*/("""
									"""),format.raw/*103.10*/("""</TH>
								""")))}),format.raw/*104.10*/("""
							""")))} else {null} ),format.raw/*105.9*/("""
						"""),format.raw/*106.7*/("""</TR>
					</thead>
					<tbody>
						"""),_display_(/*109.8*/for(lista1 <- listEquipBodOrigen) yield /*109.41*/{_display_(Seq[Any](format.raw/*109.42*/("""
							"""),format.raw/*110.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*111.40*/lista1/*111.46*/.get(1)),format.raw/*111.53*/("""</td>
								<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*112.74*/lista1/*112.80*/.get(2)),format.raw/*112.87*/("""');">"""),_display_(/*112.93*/lista1/*112.99*/.get(2)),format.raw/*112.106*/("""</a></td>
								<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*113.74*/lista1/*113.80*/.get(2)),format.raw/*113.87*/("""');">"""),_display_(/*113.93*/lista1/*113.99*/.get(3)),format.raw/*113.106*/("""</a></td>

								<td  class="kg"  style="text-align:right;">"""),_display_(/*115.53*/lista1/*115.59*/.get(4)),format.raw/*115.66*/("""</td>
								<td  class="m2"  style="text-align:right;">"""),_display_(/*116.53*/lista1/*116.59*/.get(5)),format.raw/*116.66*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*117.42*/lista1/*117.48*/.get(6)),format.raw/*117.55*/("""</td>
								"""),_display_(if(mapPermiso.get("parametro.ocultar-cant-hoja-chequeo")!=null && mapPermiso.get("parametro.ocultar-cant-hoja-chequeo").equals("1"))/*118.142*/{_display_(Seq[Any](format.raw/*118.143*/("""
									"""),format.raw/*119.10*/("""<td class="cant" style="text-align:right;"></td>
									<td class="totKg" style="text-align:right;"></td>
								""")))}else/*121.14*/{_display_(Seq[Any](format.raw/*121.15*/("""
									"""),format.raw/*122.10*/("""<td class="cant" style="text-align:right;">"""),_display_(/*122.54*/lista1/*122.60*/.get(7)),format.raw/*122.67*/("""</td>
									<td class="totKg" style="text-align:right;"></td>
								""")))}),format.raw/*124.10*/("""
								"""),_display_(if(bodegaOrigen.esInterna!=1)/*125.39*/{_display_(Seq[Any](format.raw/*125.40*/("""
									"""),_display_(/*126.11*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*126.58*/{_display_(Seq[Any](format.raw/*126.59*/("""
										"""),format.raw/*127.11*/("""<td></td>
									""")))}),format.raw/*128.11*/("""
								""")))} else {null} ),format.raw/*129.10*/("""
							"""),format.raw/*130.8*/("""</TR>
			 			""")))}),format.raw/*131.9*/("""
					"""),format.raw/*132.6*/("""</tbody>
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
							"""),_display_(if(bodegaOrigen.esInterna!=1)/*143.38*/{_display_(Seq[Any](format.raw/*143.39*/("""
								"""),_display_(/*144.10*/for(titEstado <- listTipoEstado) yield /*144.42*/{_display_(Seq[Any](format.raw/*144.43*/("""
									"""),format.raw/*145.10*/("""<TH style="font-size:10px; vertical-align:middle">
										
									</TH>
								""")))}),format.raw/*148.10*/("""
							""")))} else {null} ),format.raw/*149.9*/("""
						"""),format.raw/*150.7*/("""</TR>
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
""")))}),format.raw/*163.2*/("""


"""),format.raw/*166.1*/("""<style>
	#tablaPrincipal td:nth-child(2) """),format.raw/*167.34*/("""{"""),format.raw/*167.35*/(""" """),format.raw/*167.36*/("""text-align: center; """),format.raw/*167.56*/("""}"""),format.raw/*167.57*/("""
	"""),format.raw/*168.2*/("""#tablaPrincipal td:nth-child(3) """),format.raw/*168.34*/("""{"""),format.raw/*168.35*/(""" """),format.raw/*168.36*/("""text-align: center; """),format.raw/*168.56*/("""}"""),format.raw/*168.57*/("""
	"""),format.raw/*169.2*/("""#tablaPrincipal td:nth-child(5) """),format.raw/*169.34*/("""{"""),format.raw/*169.35*/(""" """),format.raw/*169.36*/("""text-align: right; """),format.raw/*169.55*/("""}"""),format.raw/*169.56*/("""
	"""),format.raw/*170.2*/("""#tablaPrincipal td:nth-child(6) """),format.raw/*170.34*/("""{"""),format.raw/*170.35*/(""" """),format.raw/*170.36*/("""text-align: right; """),format.raw/*170.55*/("""}"""),format.raw/*170.56*/("""
	"""),format.raw/*171.2*/("""#tablaPrincipal td:nth-child(7) """),format.raw/*171.34*/("""{"""),format.raw/*171.35*/(""" """),format.raw/*171.36*/("""text-align: center; """),format.raw/*171.56*/("""}"""),format.raw/*171.57*/("""
	"""),format.raw/*172.2*/("""#tablaPrincipal td:nth-child(8) """),format.raw/*172.34*/("""{"""),format.raw/*172.35*/(""" """),format.raw/*172.36*/("""text-align: right; """),format.raw/*172.55*/("""}"""),format.raw/*172.56*/("""
	"""),format.raw/*173.2*/("""#tablaPrincipal td:nth-child(9) """),format.raw/*173.34*/("""{"""),format.raw/*173.35*/(""" """),format.raw/*173.36*/("""text-align: right; """),format.raw/*173.55*/("""}"""),format.raw/*173.56*/("""
	"""),format.raw/*174.2*/("""#tablaPrincipal td:nth-child(10) """),format.raw/*174.35*/("""{"""),format.raw/*174.36*/(""" """),format.raw/*174.37*/("""text-align: center; """),format.raw/*174.57*/("""}"""),format.raw/*174.58*/("""
	"""),format.raw/*175.2*/("""#tablaPrincipal td:nth-child(11) """),format.raw/*175.35*/("""{"""),format.raw/*175.36*/(""" """),format.raw/*175.37*/("""text-align: center; """),format.raw/*175.57*/("""}"""),format.raw/*175.58*/("""
	"""),format.raw/*176.2*/("""#tablaPrincipal td:nth-child(12) """),format.raw/*176.35*/("""{"""),format.raw/*176.36*/(""" """),format.raw/*176.37*/("""text-align: right; """),format.raw/*176.56*/("""}"""),format.raw/*176.57*/("""
	"""),format.raw/*177.2*/("""#tablaPrincipal td:nth-child(13) """),format.raw/*177.35*/("""{"""),format.raw/*177.36*/(""" """),format.raw/*177.37*/("""text-align: right; """),format.raw/*177.56*/("""}"""),format.raw/*177.57*/("""
	"""),format.raw/*178.2*/("""#tablaPrincipal td:nth-child(14) """),format.raw/*178.35*/("""{"""),format.raw/*178.36*/(""" """),format.raw/*178.37*/("""text-align: right; """),format.raw/*178.56*/("""}"""),format.raw/*178.57*/("""
	"""),_display_(if(bodegaOrigen.esInterna!=1)/*179.32*/{_display_(Seq[Any](format.raw/*179.33*/("""
		"""),_display_(/*180.4*/for((est,index) <- listTipoEstado.zipWithIndex) yield /*180.51*/{_display_(Seq[Any](format.raw/*180.52*/("""
			"""),format.raw/*181.4*/("""#tablaPrincipal td:nth-child("""),_display_(/*181.34*/(index+16)),format.raw/*181.44*/(""") """),format.raw/*181.46*/("""{"""),format.raw/*181.47*/(""" """),format.raw/*181.48*/("""vertical-align: middle; """),format.raw/*181.72*/("""}"""),format.raw/*181.73*/("""
		""")))}),format.raw/*182.4*/("""
	""")))} else {null} ),format.raw/*183.3*/("""
"""),format.raw/*184.1*/("""</style>

<script type="text/javascript">
	$(document).ready(function() """),format.raw/*187.31*/("""{"""),format.raw/*187.32*/("""
		"""),format.raw/*188.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*188.34*/("""{"""),format.raw/*188.35*/("""
	    	"""),format.raw/*189.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[-1, 20, 50, 100, 200], ["All", 20, 50, 100, 200]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*192.19*/("""{"""),format.raw/*192.20*/("""
	        	"""),format.raw/*193.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*194.10*/("""}"""),format.raw/*194.11*/("""
	  	"""),format.raw/*195.5*/("""}"""),format.raw/*195.6*/(""" """),format.raw/*195.7*/(""");


		const kgs = document.querySelectorAll('td.kg');
		const m2s = document.querySelectorAll('td.m2');
		const cants = document.querySelectorAll('td.cant');
		const subKg = document.querySelectorAll('td.totKg');
		let totKg = 0;
		let totTotKg = 0;
		let totM2 = 0;
		let totCant = 0;
		for (let i = 0; i < kgs.length; i++) """),format.raw/*206.40*/("""{"""),format.raw/*206.41*/("""
		  """),format.raw/*207.5*/("""const kg = kgs[i].textContent.replace(/,/g,'');
		  const m2 = m2s[i].textContent.replace(/,/g,'');
		  let cant = cants[i].textContent.replace(/,/g,'');
			if(cant == "")"""),format.raw/*210.18*/("""{"""),format.raw/*210.19*/("""
				"""),format.raw/*211.5*/("""cant = 0;
			"""),format.raw/*212.4*/("""}"""),format.raw/*212.5*/("""else"""),format.raw/*212.9*/("""{"""),format.raw/*212.10*/("""
				"""),format.raw/*213.5*/("""subKg[i].textContent = formatStandar(parseFloat(kg)*parseFloat(cant),2);
			"""),format.raw/*214.4*/("""}"""),format.raw/*214.5*/("""
		  """),format.raw/*215.5*/("""totKg += parseFloat(kg);
			totTotKg += parseFloat(kg)*parseFloat(cant);
		  totM2 += parseFloat(m2);
		  totCant += parseFloat(cant);
		"""),format.raw/*219.3*/("""}"""),format.raw/*219.4*/("""
		"""),format.raw/*220.3*/("""$("#totKg").text(formatStandar(totKg));
		$("#totM2").text(formatStandar(totM2));

		
		if(totCant == 0)"""),format.raw/*224.19*/("""{"""),format.raw/*224.20*/("""
			"""),format.raw/*225.4*/("""$("#totCant").text("");
			$("#totCant").text("");
		"""),format.raw/*227.3*/("""}"""),format.raw/*227.4*/("""else"""),format.raw/*227.8*/("""{"""),format.raw/*227.9*/("""
			"""),format.raw/*228.4*/("""$("#totCant").text(formatStandar(totCant,2));
			$("#totTotKg").text(formatStandar(totTotKg,2));
		"""),format.raw/*230.3*/("""}"""),format.raw/*230.4*/("""
		
	
		"""),format.raw/*233.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*234.2*/("""}"""),format.raw/*234.3*/(""");
	
	const validarForm = () =>"""),format.raw/*236.27*/("""{"""),format.raw/*236.28*/("""
		"""),format.raw/*237.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*239.2*/("""}"""),format.raw/*239.3*/("""
	
"""),format.raw/*241.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodegaOrigen:tables.BodegaEmpresa,listEquipBodOrigen:List[List[String]],listTipoEstado:List[tables.TipoEstado],idGrupos:List[Long],contactos:String,direccion:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,listTipoEstado,idGrupos,contactos,direccion)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],List[tables.TipoEstado],List[Long],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,listTipoEstado,idGrupos,contactos,direccion) => apply(mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listEquipBodOrigen,listTipoEstado,idGrupos,contactos,direccion)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMovimientos/hojaChequeoDetallePorGrupo.scala.html
                  HASH: 8a0d5441c97c291dced71368de3a128b4df17deb
                  MATRIX: 1118->1|1485->275|1525->290|1541->298|1580->300|1609->304|1677->352|1708->357|1753->381|1784->385|1861->436|1977->531|2007->534|2566->1066|2587->1078|2616->1086|2652->1096|2690->1118|2729->1119|2764->1127|2838->1174|2864->1179|2904->1189|2938->1196|4496->2727|4517->2739|4555->2756|4766->2940|4789->2954|4824->2968|4853->2969|4946->3035|4967->3047|5014->3073|5043->3074|5072->3076|5094->3088|5134->3106|5290->3235|5320->3244|5706->3603|5729->3617|5764->3631|5794->3634|5825->3643|6421->4212|6460->4213|6498->4223|6547->4255|6587->4256|6626->4266|6716->4328|6735->4337|6768->4348|6807->4358|6854->4373|6907->4382|6942->4389|7009->4429|7059->4462|7099->4463|7135->4471|7207->4515|7223->4521|7252->4528|7359->4607|7375->4613|7404->4620|7438->4626|7454->4632|7484->4639|7595->4722|7611->4728|7640->4735|7674->4741|7690->4747|7720->4754|7811->4817|7827->4823|7856->4830|7942->4888|7958->4894|7987->4901|8062->4948|8078->4954|8107->4961|8283->5108|8324->5109|8363->5119|8504->5240|8544->5241|8583->5251|8655->5295|8671->5301|8700->5308|8806->5382|8873->5421|8913->5422|8952->5433|9016->5480|9056->5481|9096->5492|9148->5512|9203->5522|9239->5530|9284->5544|9318->5550|9724->5928|9764->5929|9802->5939|9851->5971|9891->5972|9930->5982|10048->6068|10101->6077|10136->6084|10554->6471|10585->6474|10655->6515|10685->6516|10715->6517|10764->6537|10794->6538|10824->6540|10885->6572|10915->6573|10945->6574|10994->6594|11024->6595|11054->6597|11115->6629|11145->6630|11175->6631|11223->6650|11253->6651|11283->6653|11344->6685|11374->6686|11404->6687|11452->6706|11482->6707|11512->6709|11573->6741|11603->6742|11633->6743|11682->6763|11712->6764|11742->6766|11803->6798|11833->6799|11863->6800|11911->6819|11941->6820|11971->6822|12032->6854|12062->6855|12092->6856|12140->6875|12170->6876|12200->6878|12262->6911|12292->6912|12322->6913|12371->6933|12401->6934|12431->6936|12493->6969|12523->6970|12553->6971|12602->6991|12632->6992|12662->6994|12724->7027|12754->7028|12784->7029|12832->7048|12862->7049|12892->7051|12954->7084|12984->7085|13014->7086|13062->7105|13092->7106|13122->7108|13184->7141|13214->7142|13244->7143|13292->7162|13322->7163|13382->7195|13422->7196|13453->7200|13517->7247|13557->7248|13589->7252|13647->7282|13679->7292|13710->7294|13740->7295|13770->7296|13823->7320|13853->7321|13888->7325|13935->7328|13964->7329|14065->7401|14095->7402|14126->7405|14186->7436|14216->7437|14251->7444|14436->7600|14466->7601|14506->7612|14612->7689|14642->7690|14675->7695|14704->7696|14733->7697|15088->8023|15118->8024|15151->8029|15351->8200|15381->8201|15414->8206|15455->8219|15484->8220|15516->8224|15546->8225|15579->8230|15683->8306|15712->8307|15745->8312|15910->8449|15939->8450|15970->8453|16103->8557|16133->8558|16165->8562|16246->8615|16275->8616|16307->8620|16336->8621|16368->8625|16495->8724|16524->8725|16560->8733|16654->8799|16683->8800|16743->8831|16773->8832|16804->8835|16888->8891|16917->8892|16948->8895
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|41->10|41->10|43->12|44->13|44->13|45->14|55->24|55->24|55->24|56->25|56->25|56->25|57->26|57->26|57->26|58->27|59->28|95->64|95->64|95->64|100->69|100->69|100->69|100->69|101->70|101->70|101->70|101->70|101->70|101->70|101->70|103->72|103->72|112->81|112->81|112->81|112->81|112->81|130->99|130->99|131->100|131->100|131->100|132->101|133->102|133->102|133->102|134->103|135->104|136->105|137->106|140->109|140->109|140->109|141->110|142->111|142->111|142->111|143->112|143->112|143->112|143->112|143->112|143->112|144->113|144->113|144->113|144->113|144->113|144->113|146->115|146->115|146->115|147->116|147->116|147->116|148->117|148->117|148->117|149->118|149->118|150->119|152->121|152->121|153->122|153->122|153->122|153->122|155->124|156->125|156->125|157->126|157->126|157->126|158->127|159->128|160->129|161->130|162->131|163->132|174->143|174->143|175->144|175->144|175->144|176->145|179->148|180->149|181->150|194->163|197->166|198->167|198->167|198->167|198->167|198->167|199->168|199->168|199->168|199->168|199->168|199->168|200->169|200->169|200->169|200->169|200->169|200->169|201->170|201->170|201->170|201->170|201->170|201->170|202->171|202->171|202->171|202->171|202->171|202->171|203->172|203->172|203->172|203->172|203->172|203->172|204->173|204->173|204->173|204->173|204->173|204->173|205->174|205->174|205->174|205->174|205->174|205->174|206->175|206->175|206->175|206->175|206->175|206->175|207->176|207->176|207->176|207->176|207->176|207->176|208->177|208->177|208->177|208->177|208->177|208->177|209->178|209->178|209->178|209->178|209->178|209->178|210->179|210->179|211->180|211->180|211->180|212->181|212->181|212->181|212->181|212->181|212->181|212->181|212->181|213->182|214->183|215->184|218->187|218->187|219->188|219->188|219->188|220->189|223->192|223->192|224->193|225->194|225->194|226->195|226->195|226->195|237->206|237->206|238->207|241->210|241->210|242->211|243->212|243->212|243->212|243->212|244->213|245->214|245->214|246->215|250->219|250->219|251->220|255->224|255->224|256->225|258->227|258->227|258->227|258->227|259->228|261->230|261->230|264->233|265->234|265->234|267->236|267->236|268->237|270->239|270->239|272->241
                  -- GENERATED --
              */
          