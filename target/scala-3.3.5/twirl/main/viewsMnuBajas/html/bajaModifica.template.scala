
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

object bajaModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.ActaBaja,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
actaBaja: tables.ActaBaja, detalleBaja: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<form action="/bajaModificaGraba/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*10.5*/barraTitulo(mapDiccionario, "MODIFICAR ACTA DE BAJA","(no incluye bajas confirmadas)")),format.raw/*10.91*/("""
			"""),format.raw/*11.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="300px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro ACTA DE BAJA</span>
								  		</div>
								  		<input type="hidden" name="id_actaBaja" value=""""),_display_(/*21.61*/actaBaja/*21.69*/.getId()),format.raw/*21.77*/("""">
	  									<input type="text" class="form-control center"
	  										name="numero"
	  										value=""""),_display_(/*24.22*/actaBaja/*24.30*/.getNumero()),format.raw/*24.42*/(""""
	  										readonly>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Fecha</span>
								  		</div>
								  		<input type="date" class="form-control center"
								  			name="fecha"
								  			onblur="if(!limitaFecha(value,720,10)) value=''"
								  			value=""""),_display_(/*36.22*/utilities/*36.31*/.Fechas.AAMMDD(actaBaja.getFecha())),format.raw/*36.66*/(""""
						        			required>
									</div>
								</td>
								<td style="text-align:center;" width="70px">
									<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
										"""),_display_(if(actaBaja.getActaBajaPDF().equals("0"))/*42.53*/{_display_(Seq[Any](format.raw/*42.54*/("""
											"""),format.raw/*43.12*/("""<div id="txtBtn">Adjuntar</div>
										""")))}else/*44.16*/{_display_(Seq[Any](format.raw/*44.17*/("""
											"""),format.raw/*45.12*/("""<div id="txtBtn">Cambiar</div>
										""")))}),format.raw/*46.12*/("""
				    					"""),format.raw/*47.14*/("""<input type="file" id="docAdjunto" name="docAdjunto" onchange="LimitAttach(this.form, this.form.docAdjunto.value);">
									</span>
								</td>
								<td style="text-align:left;">
									"""),_display_(if(actaBaja.getActaBajaPDF().equals("0"))/*51.52*/{_display_(Seq[Any](format.raw/*51.53*/("""
										"""),format.raw/*52.11*/("""--
									""")))}else/*53.15*/{_display_(Seq[Any](format.raw/*53.16*/("""
										"""),format.raw/*54.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*54.52*/actaBaja/*54.60*/.getActaBajaPDF()),format.raw/*54.77*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*57.11*/("""
								"""),format.raw/*58.9*/("""</td>
							</tr>
							<tr>
								<td colspan="7">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control" 
	  										name="observaciones" 
	  										autocomplete="off">"""),_display_(/*68.34*/actaBaja/*68.42*/.getObservaciones()),format.raw/*68.61*/("""</textarea>
									</div>
								</td>
							</tr>
						</thead>
					</table>
					<hr>
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH width="10%">CODIGO</TH>
								<TH width="20%">EQUIPO</TH>
						        <TH width="3%">UN</TH>
								<TH width="8%">STOCK</TH>
								<TH width="3%">MONEDA</TH>
								<TH width="10%">P.UNITARIO<br>COMPRA</TH>
								<TH width="10%">TOTAL</TH>
								<TH width="8%">CANTIDAD<BR>(dar de baja)</TH>
								<TH width="28%">MOTIVO DE BAJA</TH>
							</TR>
						</thead>
						<tbody>
				 			"""),_display_(/*90.10*/for(lista1 <- detalleBaja) yield /*90.36*/{_display_(Seq[Any](format.raw/*90.37*/("""
								"""),format.raw/*91.9*/("""<TR>
									<td  style="text-align:left;">
										<input type="hidden" name="id_equipo[]" value=""""),_display_(/*93.59*/lista1/*93.65*/.get(0)),format.raw/*93.72*/("""">
										"""),_display_(/*94.12*/lista1/*94.18*/.get(4)),format.raw/*94.25*/("""
									"""),format.raw/*95.10*/("""</td>
									
									<td  style="text-align:left;">"""),_display_(/*97.41*/lista1/*97.47*/.get(5)),format.raw/*97.54*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*98.43*/lista1/*98.49*/.get(8)),format.raw/*98.56*/("""</td>
									<td  style="text-align:right;" class="stock">"""),_display_(/*99.56*/lista1/*99.62*/.get(9)),format.raw/*99.69*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*100.43*/lista1/*100.49*/.get(10)),format.raw/*100.57*/("""</td>
									<td  style="text-align:right;">"""),_display_(/*101.42*/lista1/*101.48*/.get(11)),format.raw/*101.56*/("""</td>
									<td  style="text-align:right;" class="total">"""),_display_(/*102.56*/lista1/*102.62*/.get(12)),format.raw/*102.70*/("""</td>
									<td  style="text-align:center;">
										<div style="display: none"><script>"""),_display_(/*104.47*/lista1/*104.53*/.get(13).replaceAll(",","")),format.raw/*104.80*/("""</script></div>
										<input type="text" class="cantidad form-control right"
											name="cantidad[]"
											id="cantidad_"""),_display_(/*107.26*/lista1/*107.32*/.get(0)),format.raw/*107.39*/("""_"""),_display_(/*107.41*/lista1/*107.47*/.get(1)),format.raw/*107.54*/(""""
											value=""""),_display_(/*108.20*/lista1/*108.26*/.get(13)),format.raw/*108.34*/(""""
											onfocus="value=value.replace(/,/g,''); cantAux = value;"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0; verificaCantidad('"""),_display_(/*112.64*/lista1/*112.70*/.get(0)),format.raw/*112.77*/("""','"""),_display_(/*112.81*/lista1/*112.87*/.get(1)),format.raw/*112.94*/("""','"""),_display_(/*112.98*/lista1/*112.104*/.get(9)),format.raw/*112.111*/("""', value);">
									</td>
									<td  style="text-align:center;">
										<input type="text" class="form-control left"
											name="motivo[]"
											value=""""),_display_(/*117.20*/lista1/*117.26*/.get(14)),format.raw/*117.34*/(""""
											autocomplete="off">
									</td>
								</TR>
				 			""")))}),format.raw/*121.10*/("""
				 			"""),format.raw/*122.9*/("""<TR style="background-color: #eeeeee">
						        <td></td>
								<td><div style="display: none">Zzzzzzzzzzzz</div> TOTALES</td>
								<td></td>
								<td><div id="totalStock" align="right">0.00</div></td>
								<td></td>
								<td></td>
								<td><div id="totalTotal" align="right">0.00</div></td>
								<td><div id="totalCantidad" align="right">0.00</div></td>
								<td></td>
							</TR>
						</tbody>
					</table>
				</div>
			</div>
			<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="submit"  id="aplica" value='Grabar' class="btn btn-primary btn-sm rounded-pill btn-block">
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
""")))}),format.raw/*154.2*/("""




"""),format.raw/*159.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*161.31*/("""{"""),format.raw/*161.32*/("""
		"""),format.raw/*162.3*/("""sumaTotales();
		$(document).ready(function() """),format.raw/*163.32*/("""{"""),format.raw/*163.33*/("""
			"""),format.raw/*164.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*164.35*/("""{"""),format.raw/*164.36*/("""
		    	"""),format.raw/*165.8*/(""""fixedHeader": true,
				"lengthMenu": [[-1], [ "All"]],
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*168.20*/("""{"""),format.raw/*168.21*/("""
		        	"""),format.raw/*169.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*170.11*/("""}"""),format.raw/*170.12*/("""
		  """),format.raw/*171.5*/("""}"""),format.raw/*171.6*/(""" """),format.raw/*171.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*173.2*/("""}"""),format.raw/*173.3*/(""");
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*175.2*/("""}"""),format.raw/*175.3*/(""");
	
	let cantAux = 0;
	const verificaCantidad = (id_equipo, id_cotizacion, stock, cantidad) =>"""),format.raw/*178.73*/("""{"""),format.raw/*178.74*/("""
		"""),format.raw/*179.3*/("""stock = stock.replace(/,/g,'');
		if(parseFloat(cantidad) > parseFloat(stock))"""),format.raw/*180.47*/("""{"""),format.raw/*180.48*/("""
			"""),format.raw/*181.4*/("""alertify.alert('La cantidad no puede ser mayor al stock disponible para dar de baja', function () """),format.raw/*181.102*/("""{"""),format.raw/*181.103*/("""
				"""),format.raw/*182.5*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(cantAux,2));
     		"""),format.raw/*183.8*/("""}"""),format.raw/*183.9*/(""");
		"""),format.raw/*184.3*/("""}"""),format.raw/*184.4*/("""else"""),format.raw/*184.8*/("""{"""),format.raw/*184.9*/("""
			"""),format.raw/*185.4*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(cantidad,2));
		"""),format.raw/*186.3*/("""}"""),format.raw/*186.4*/("""
		"""),format.raw/*187.3*/("""sumaTotales();
	"""),format.raw/*188.2*/("""}"""),format.raw/*188.3*/("""
	
	"""),format.raw/*190.2*/("""let revisaTotal = 0;
	const sumaTotales = () => """),format.raw/*191.28*/("""{"""),format.raw/*191.29*/("""
		 """),format.raw/*192.4*/("""let sum = 0;
			$(".stock").each(function() """),format.raw/*193.32*/("""{"""),format.raw/*193.33*/("""
				"""),format.raw/*194.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*196.4*/("""}"""),format.raw/*196.5*/("""); $("#totalStock").text(formatStandar(sum,2));
			sum = 0;
			$(".total").each(function() """),format.raw/*198.32*/("""{"""),format.raw/*198.33*/("""
				"""),format.raw/*199.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*201.4*/("""}"""),format.raw/*201.5*/("""); $("#totalTotal").text(formatStandar(sum,2));
			sum = 0;
			$(".cantidad").each(function() """),format.raw/*203.35*/("""{"""),format.raw/*203.36*/("""
				"""),format.raw/*204.5*/("""let val = $(this).val().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*206.4*/("""}"""),format.raw/*206.5*/(""");
			revisaTotal = sum;
			$("#totalCantidad").text(formatStandar(sum,2));
	"""),format.raw/*209.2*/("""}"""),format.raw/*209.3*/("""
	
	"""),format.raw/*211.2*/("""const validarForm = () =>"""),format.raw/*211.27*/("""{"""),format.raw/*211.28*/("""
		"""),format.raw/*212.3*/("""$("#aplica").attr('disabled',true);
		let flag = false;
		let cantTotal = $("#totalCantidad").text().replace(/,/g,'');
		if(revisaTotal>0)"""),format.raw/*215.20*/("""{"""),format.raw/*215.21*/("""
			"""),format.raw/*216.4*/("""let table = $('#tablaPrincipal').DataTable();
			table.destroy();
			return(true);
		"""),format.raw/*219.3*/("""}"""),format.raw/*219.4*/("""else"""),format.raw/*219.8*/("""{"""),format.raw/*219.9*/("""
			"""),format.raw/*220.4*/("""flag = false;
			alertify.alert('PARA GRABAR UNA BAJA AL MENOS DEBE CONSIDERAR UN EQUIPO PARA BAJA', function () """),format.raw/*221.100*/("""{"""),format.raw/*221.101*/("""
				"""),format.raw/*222.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*224.8*/("""}"""),format.raw/*224.9*/(""");
		"""),format.raw/*225.3*/("""}"""),format.raw/*225.4*/("""
		"""),format.raw/*226.3*/("""return(flag);
	"""),format.raw/*227.2*/("""}"""),format.raw/*227.3*/("""
	
	"""),format.raw/*229.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*229.43*/("""{"""),format.raw/*229.44*/("""
		  """),format.raw/*230.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*232.2*/("""}"""),format.raw/*232.3*/("""

	"""),format.raw/*234.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*235.38*/("""{"""),format.raw/*235.39*/("""
		"""),format.raw/*236.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*238.35*/("""{"""),format.raw/*238.36*/("""
			"""),format.raw/*239.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*240.3*/("""}"""),format.raw/*240.4*/("""
		"""),format.raw/*241.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*242.45*/("""{"""),format.raw/*242.46*/("""
			"""),format.raw/*243.4*/("""if (extArray[i] == extencion) """),format.raw/*243.34*/("""{"""),format.raw/*243.35*/(""" """),format.raw/*243.36*/("""allowSubmit = true; break; """),format.raw/*243.63*/("""}"""),format.raw/*243.64*/("""
		"""),format.raw/*244.3*/("""}"""),format.raw/*244.4*/("""
		"""),format.raw/*245.3*/("""if (allowSubmit) """),format.raw/*245.20*/("""{"""),format.raw/*245.21*/("""
			"""),format.raw/*246.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*248.26*/("""{"""),format.raw/*248.27*/("""
				"""),format.raw/*249.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*252.4*/("""}"""),format.raw/*252.5*/("""
		"""),format.raw/*253.3*/("""}"""),format.raw/*253.4*/("""else"""),format.raw/*253.8*/("""{"""),format.raw/*253.9*/("""
			"""),format.raw/*254.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*258.3*/("""}"""),format.raw/*258.4*/("""
	"""),format.raw/*259.2*/("""}"""),format.raw/*259.3*/("""
	
	


	
"""),format.raw/*265.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,actaBaja:tables.ActaBaja,detalleBaja:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,actaBaja,detalleBaja)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.ActaBaja,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,actaBaja,detalleBaja) => apply(mapDiccionario,mapPermiso,userMnu,actaBaja,detalleBaja)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBajas/bajaModifica.scala.html
                  HASH: 8d9915a01243917d70ccfed174c55c5405bdba47
                  MATRIX: 1044->1|1294->158|1327->166|1343->174|1382->176|1410->179|1478->227|1506->229|1699->396|1806->482|1837->486|2392->1014|2409->1022|2438->1030|2575->1140|2592->1148|2625->1160|3078->1586|3096->1595|3152->1630|3420->1871|3459->1872|3499->1884|3565->1931|3604->1932|3644->1944|3717->1986|3759->2000|4023->2237|4062->2238|4101->2249|4137->2266|4176->2267|4215->2278|4283->2319|4300->2327|4338->2344|4459->2434|4495->2443|4874->2795|4891->2803|4931->2822|5635->3499|5677->3525|5716->3526|5752->3535|5882->3638|5897->3644|5925->3651|5966->3665|5981->3671|6009->3678|6047->3688|6130->3744|6145->3750|6173->3757|6248->3805|6263->3811|6291->3818|6379->3879|6394->3885|6422->3892|6498->3940|6514->3946|6544->3954|6619->4001|6635->4007|6665->4015|6754->4076|6770->4082|6800->4090|6922->4184|6938->4190|6987->4217|7150->4352|7166->4358|7195->4365|7225->4367|7241->4373|7270->4380|7319->4401|7335->4407|7365->4415|7614->4636|7630->4642|7659->4649|7691->4653|7707->4659|7736->4666|7768->4670|7785->4676|7815->4683|8014->4854|8030->4860|8060->4868|8163->4939|8200->4948|9368->6085|9401->6090|9493->6153|9523->6154|9554->6157|9629->6203|9659->6204|9691->6208|9751->6239|9781->6240|9817->6248|9954->6356|9984->6357|10025->6369|10132->6447|10162->6448|10195->6453|10224->6454|10253->6455|10354->6528|10383->6529|10482->6600|10511->6601|10635->6696|10665->6697|10696->6700|10803->6778|10833->6779|10865->6783|10993->6881|11024->6882|11057->6887|11167->6969|11196->6970|11229->6975|11258->6976|11290->6980|11319->6981|11351->6985|11457->7063|11486->7064|11517->7067|11561->7083|11590->7084|11622->7088|11699->7136|11729->7137|11761->7141|11834->7185|11864->7186|11897->7191|11999->7265|12028->7266|12148->7357|12178->7358|12211->7363|12313->7437|12342->7438|12465->7532|12495->7533|12528->7538|12629->7611|12658->7612|12763->7689|12792->7690|12824->7694|12878->7719|12908->7720|12939->7723|13106->7861|13136->7862|13168->7866|13281->7951|13310->7952|13342->7956|13371->7957|13403->7961|13546->8074|13577->8075|13610->8080|13700->8142|13729->8143|13762->8148|13791->8149|13822->8152|13865->8167|13894->8168|13926->8172|13996->8213|14026->8214|14059->8219|14191->8323|14220->8324|14251->8327|14442->8489|14472->8490|14503->8493|14612->8573|14642->8574|14674->8578|14747->8623|14776->8624|14807->8627|14945->8736|14975->8737|15007->8741|15066->8771|15096->8772|15126->8773|15182->8800|15212->8801|15243->8804|15272->8805|15303->8808|15349->8825|15379->8826|15411->8830|15556->8946|15586->8947|15619->8952|15823->9128|15852->9129|15883->9132|15912->9133|15944->9137|15973->9138|16005->9142|16222->9331|16251->9332|16281->9334|16310->9335|16347->9344
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|41->10|41->10|42->11|52->21|52->21|52->21|55->24|55->24|55->24|67->36|67->36|67->36|73->42|73->42|74->43|75->44|75->44|76->45|77->46|78->47|82->51|82->51|83->52|84->53|84->53|85->54|85->54|85->54|85->54|88->57|89->58|99->68|99->68|99->68|121->90|121->90|121->90|122->91|124->93|124->93|124->93|125->94|125->94|125->94|126->95|128->97|128->97|128->97|129->98|129->98|129->98|130->99|130->99|130->99|131->100|131->100|131->100|132->101|132->101|132->101|133->102|133->102|133->102|135->104|135->104|135->104|138->107|138->107|138->107|138->107|138->107|138->107|139->108|139->108|139->108|143->112|143->112|143->112|143->112|143->112|143->112|143->112|143->112|143->112|148->117|148->117|148->117|152->121|153->122|185->154|190->159|192->161|192->161|193->162|194->163|194->163|195->164|195->164|195->164|196->165|199->168|199->168|200->169|201->170|201->170|202->171|202->171|202->171|204->173|204->173|206->175|206->175|209->178|209->178|210->179|211->180|211->180|212->181|212->181|212->181|213->182|214->183|214->183|215->184|215->184|215->184|215->184|216->185|217->186|217->186|218->187|219->188|219->188|221->190|222->191|222->191|223->192|224->193|224->193|225->194|227->196|227->196|229->198|229->198|230->199|232->201|232->201|234->203|234->203|235->204|237->206|237->206|240->209|240->209|242->211|242->211|242->211|243->212|246->215|246->215|247->216|250->219|250->219|250->219|250->219|251->220|252->221|252->221|253->222|255->224|255->224|256->225|256->225|257->226|258->227|258->227|260->229|260->229|260->229|261->230|263->232|263->232|265->234|266->235|266->235|267->236|269->238|269->238|270->239|271->240|271->240|272->241|273->242|273->242|274->243|274->243|274->243|274->243|274->243|274->243|275->244|275->244|276->245|276->245|276->245|277->246|279->248|279->248|280->249|283->252|283->252|284->253|284->253|284->253|284->253|285->254|289->258|289->258|290->259|290->259|296->265
                  -- GENERATED --
              */
          