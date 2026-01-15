
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

object bajaIngreso extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,Long,List[List[String]],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
numeroActaBaja: Long, listEquipBodOrigen: List[List[String]], fecha: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<form action="/bajaNuevo/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*10.5*/barraTitulo(mapDiccionario, "INGRESAR ACTA DE BAJA","")),format.raw/*10.60*/("""
			"""),format.raw/*11.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td style="width: 300px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro ACTA DE BAJA</span>
								  		</div>
								  		<input type="hidden" name="id_actaBaja" value="0">
	  									<input type="text" class="form-control center"
	  										name="numero"
	  										value=""""),_display_(/*24.22*/numeroActaBaja),format.raw/*24.36*/(""""
	  										readonly>
									</div>
								</td>
								<td style="width: 230px;">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Fecha</span>
								  		</div>
								  		<input type="date" class="form-control center"
								  			name="fecha"
								  			onblur="if(!limitaFecha(value,720,10)) value=''"
								  			value=""""),_display_(/*36.22*/fecha),format.raw/*36.27*/(""""
						        			required>
									</div>
								</td>
								<td>
									<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
										<div id="txtBtn">Adjuntar</div>
				    					<input type="file" id="docAdjunto" name="docAdjunto" onchange="LimitAttach(this.form, this.form.docAdjunto.value);">
									</span>
								</td>
							</tr>
							<tr>
								<td colspan="6">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control" 
	  										name="observaciones" 
	  										autocomplete="off"></textarea>
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
				 			"""),_display_(/*77.10*/for(lista1 <- listEquipBodOrigen) yield /*77.43*/{_display_(Seq[Any](format.raw/*77.44*/("""
								"""),format.raw/*78.9*/("""<TR>
									<td  style="text-align:left;">
										<input type="hidden" name="id_equipo[]" value=""""),_display_(/*80.59*/lista1/*80.65*/.get(0)),format.raw/*80.72*/("""">
										"""),_display_(/*81.12*/lista1/*81.18*/.get(4)),format.raw/*81.25*/("""
									"""),format.raw/*82.10*/("""</td>
									
									<td  style="text-align:left;">"""),_display_(/*84.41*/lista1/*84.47*/.get(5)),format.raw/*84.54*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*85.43*/lista1/*85.49*/.get(8)),format.raw/*85.56*/("""</td>
									<td  style="text-align:right;" class="stock">"""),_display_(/*86.56*/lista1/*86.62*/.get(9)),format.raw/*86.69*/("""</td>
									<td  style="text-align:center;">"""),_display_(/*87.43*/lista1/*87.49*/.get(10)),format.raw/*87.57*/("""</td>
									<td  style="text-align:right;">"""),_display_(/*88.42*/lista1/*88.48*/.get(11)),format.raw/*88.56*/("""</td>
									<td  style="text-align:right;" class="total">"""),_display_(/*89.56*/lista1/*89.62*/.get(12)),format.raw/*89.70*/("""</td>
									<td  style="text-align:center;">
										<input type="text" class="cantidad form-control right"
											name="cantidad[]"
											id="cantidad_"""),_display_(/*93.26*/lista1/*93.32*/.get(0)),format.raw/*93.39*/("""_"""),_display_(/*93.41*/lista1/*93.47*/.get(1)),format.raw/*93.54*/(""""
											value="0.00"
											onfocus="value=value.replace(/,/g,''); cantAux = value;" 
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=0; verificaCantidad('"""),_display_(/*98.64*/lista1/*98.70*/.get(0)),format.raw/*98.77*/("""','"""),_display_(/*98.81*/lista1/*98.87*/.get(1)),format.raw/*98.94*/("""','"""),_display_(/*98.98*/lista1/*98.104*/.get(9)),format.raw/*98.111*/("""', value);">
									</td>
									<td  style="text-align:center;">
										<input type="text" class="form-control left"
											name="motivo[]"
											value=""
											autocomplete="off">
									</td>
									
									
								</TR>
				 			""")))}),format.raw/*109.10*/("""
				 			"""),format.raw/*110.9*/("""<TR style="background-color: #eeeeee">
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
	
""")))}),format.raw/*138.2*/("""




"""),format.raw/*143.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*145.31*/("""{"""),format.raw/*145.32*/("""
		"""),format.raw/*146.3*/("""sumaTotales();

		$('#tablaPrincipal').DataTable("""),format.raw/*148.34*/("""{"""),format.raw/*148.35*/("""
		    	"""),format.raw/*149.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[-1], [ "All"]],
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*152.20*/("""{"""),format.raw/*152.21*/("""
		        	"""),format.raw/*153.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*154.11*/("""}"""),format.raw/*154.12*/("""
		  """),format.raw/*155.5*/("""}"""),format.raw/*155.6*/(""" """),format.raw/*155.7*/(""");

		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*158.2*/("""}"""),format.raw/*158.3*/(""");
	
	let cantAux = 0;
	const verificaCantidad = (id_equipo, id_cotizacion, stock, cantidad) =>"""),format.raw/*161.73*/("""{"""),format.raw/*161.74*/("""
		"""),format.raw/*162.3*/("""stock = stock.replace(/,/g,'');
		if(parseFloat(cantidad) > parseFloat(stock))"""),format.raw/*163.47*/("""{"""),format.raw/*163.48*/("""
			"""),format.raw/*164.4*/("""alertify.alert('La cantidad no puede ser mayor al stock disponible para dar de baja', function () """),format.raw/*164.102*/("""{"""),format.raw/*164.103*/("""
				"""),format.raw/*165.5*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(cantAux,2));
     		"""),format.raw/*166.8*/("""}"""),format.raw/*166.9*/(""");
		"""),format.raw/*167.3*/("""}"""),format.raw/*167.4*/("""else"""),format.raw/*167.8*/("""{"""),format.raw/*167.9*/("""
			"""),format.raw/*168.4*/("""$("#cantidad_"+id_equipo+"_"+id_cotizacion).val(formatStandar(cantidad,2));
		"""),format.raw/*169.3*/("""}"""),format.raw/*169.4*/("""
		"""),format.raw/*170.3*/("""sumaTotales();
	"""),format.raw/*171.2*/("""}"""),format.raw/*171.3*/("""
	
	"""),format.raw/*173.2*/("""let revisaTotal = 0;
	const sumaTotales = () => """),format.raw/*174.28*/("""{"""),format.raw/*174.29*/("""
		 """),format.raw/*175.4*/("""let sum = 0;
			$(".stock").each(function() """),format.raw/*176.32*/("""{"""),format.raw/*176.33*/("""
				"""),format.raw/*177.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*179.4*/("""}"""),format.raw/*179.5*/("""); $("#totalStock").text(formatStandar(sum,2));
			sum = 0;
			$(".total").each(function() """),format.raw/*181.32*/("""{"""),format.raw/*181.33*/("""
				"""),format.raw/*182.5*/("""let val = $(this).text().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*184.4*/("""}"""),format.raw/*184.5*/("""); $("#totalTotal").text(formatStandar(sum,2));
			sum = 0;
			$(".cantidad").each(function() """),format.raw/*186.35*/("""{"""),format.raw/*186.36*/("""
				"""),format.raw/*187.5*/("""let val = $(this).val().replace(/,/g,'');
				sum += parseFloat(val);
			"""),format.raw/*189.4*/("""}"""),format.raw/*189.5*/(""");
			revisaTotal = sum;
			$("#totalCantidad").text(formatStandar(sum,2));
	"""),format.raw/*192.2*/("""}"""),format.raw/*192.3*/("""
	
	"""),format.raw/*194.2*/("""const validarForm = () =>"""),format.raw/*194.27*/("""{"""),format.raw/*194.28*/("""
		"""),format.raw/*195.3*/("""$("#aplica").attr('disabled',true);


		let flag = false;
		if(revisaTotal>0)"""),format.raw/*199.20*/("""{"""),format.raw/*199.21*/("""
			"""),format.raw/*200.4*/("""let table = $('#tablaPrincipal').DataTable();
			table.destroy();
			return(true);
		"""),format.raw/*203.3*/("""}"""),format.raw/*203.4*/("""else"""),format.raw/*203.8*/("""{"""),format.raw/*203.9*/("""
			"""),format.raw/*204.4*/("""flag = false;
			alertify.alert('PARA GRABAR UNA BAJA AL MENOS DEBE CONSIDERAR UN EQUIPO PARA BAJA', function () """),format.raw/*205.100*/("""{"""),format.raw/*205.101*/("""
				"""),format.raw/*206.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*208.8*/("""}"""),format.raw/*208.9*/(""");
		"""),format.raw/*209.3*/("""}"""),format.raw/*209.4*/("""
		"""),format.raw/*210.3*/("""return(flag);
	"""),format.raw/*211.2*/("""}"""),format.raw/*211.3*/("""
	
	

	"""),format.raw/*215.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*216.38*/("""{"""),format.raw/*216.39*/("""
		"""),format.raw/*217.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*219.35*/("""{"""),format.raw/*219.36*/("""
			"""),format.raw/*220.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*221.3*/("""}"""),format.raw/*221.4*/("""
		"""),format.raw/*222.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*223.45*/("""{"""),format.raw/*223.46*/("""
			"""),format.raw/*224.4*/("""if (extArray[i] == extencion) """),format.raw/*224.34*/("""{"""),format.raw/*224.35*/(""" """),format.raw/*224.36*/("""allowSubmit = true; break; """),format.raw/*224.63*/("""}"""),format.raw/*224.64*/("""
		"""),format.raw/*225.3*/("""}"""),format.raw/*225.4*/("""
		"""),format.raw/*226.3*/("""if (allowSubmit) """),format.raw/*226.20*/("""{"""),format.raw/*226.21*/("""
			"""),format.raw/*227.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*229.26*/("""{"""),format.raw/*229.27*/("""
				"""),format.raw/*230.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*233.4*/("""}"""),format.raw/*233.5*/("""
		"""),format.raw/*234.3*/("""}"""),format.raw/*234.4*/("""else"""),format.raw/*234.8*/("""{"""),format.raw/*234.9*/("""
			"""),format.raw/*235.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*239.3*/("""}"""),format.raw/*239.4*/("""
	"""),format.raw/*240.2*/("""}"""),format.raw/*240.3*/("""
	
	


	
"""),format.raw/*246.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,numeroActaBaja:Long,listEquipBodOrigen:List[List[String]],fecha:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,numeroActaBaja,listEquipBodOrigen,fecha)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,Long,List[List[String]],String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,numeroActaBaja,listEquipBodOrigen,fecha) => apply(mapDiccionario,mapPermiso,userMnu,numeroActaBaja,listEquipBodOrigen,fecha)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBajas/bajaIngreso.scala.html
                  HASH: c31dd988bd9f61e08dba5db981269d5abc4ec60b
                  MATRIX: 1039->1|1306->175|1339->183|1355->191|1394->193|1422->196|1490->244|1518->246|1703->405|1779->460|1810->464|2482->1109|2517->1123|2978->1557|3004->1562|4405->2936|4454->2969|4493->2970|4529->2979|4659->3082|4674->3088|4702->3095|4743->3109|4758->3115|4786->3122|4824->3132|4907->3188|4922->3194|4950->3201|5025->3249|5040->3255|5068->3262|5156->3323|5171->3329|5199->3336|5274->3384|5289->3390|5318->3398|5392->3445|5407->3451|5436->3459|5524->3520|5539->3526|5568->3534|5762->3701|5777->3707|5805->3714|5834->3716|5849->3722|5877->3729|6150->3975|6165->3981|6193->3988|6224->3992|6239->3998|6267->4005|6298->4009|6314->4015|6343->4022|6636->4283|6673->4292|7670->5258|7703->5263|7795->5326|7825->5327|7856->5330|7934->5379|7964->5380|8000->5388|8140->5499|8170->5500|8211->5512|8318->5590|8348->5591|8381->5596|8410->5597|8439->5598|8539->5670|8568->5671|8692->5766|8722->5767|8753->5770|8860->5848|8890->5849|8922->5853|9050->5951|9081->5952|9114->5957|9224->6039|9253->6040|9286->6045|9315->6046|9347->6050|9376->6051|9408->6055|9514->6133|9543->6134|9574->6137|9618->6153|9647->6154|9679->6158|9756->6206|9786->6207|9818->6211|9891->6255|9921->6256|9954->6261|10056->6335|10085->6336|10205->6427|10235->6428|10268->6433|10370->6507|10399->6508|10522->6602|10552->6603|10585->6608|10686->6681|10715->6682|10820->6759|10849->6760|10881->6764|10935->6789|10965->6790|10996->6793|11102->6870|11132->6871|11164->6875|11277->6960|11306->6961|11338->6965|11367->6966|11399->6970|11542->7083|11573->7084|11606->7089|11696->7151|11725->7152|11758->7157|11787->7158|11818->7161|11861->7176|11890->7177|11925->7184|12116->7346|12146->7347|12177->7350|12286->7430|12316->7431|12348->7435|12421->7480|12450->7481|12481->7484|12619->7593|12649->7594|12681->7598|12740->7628|12770->7629|12800->7630|12856->7657|12886->7658|12917->7661|12946->7662|12977->7665|13023->7682|13053->7683|13085->7687|13230->7803|13260->7804|13293->7809|13497->7985|13526->7986|13557->7989|13586->7990|13618->7994|13647->7995|13679->7999|13896->8188|13925->8189|13955->8191|13984->8192|14021->8201
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|41->10|41->10|42->11|55->24|55->24|67->36|67->36|108->77|108->77|108->77|109->78|111->80|111->80|111->80|112->81|112->81|112->81|113->82|115->84|115->84|115->84|116->85|116->85|116->85|117->86|117->86|117->86|118->87|118->87|118->87|119->88|119->88|119->88|120->89|120->89|120->89|124->93|124->93|124->93|124->93|124->93|124->93|129->98|129->98|129->98|129->98|129->98|129->98|129->98|129->98|129->98|140->109|141->110|169->138|174->143|176->145|176->145|177->146|179->148|179->148|180->149|183->152|183->152|184->153|185->154|185->154|186->155|186->155|186->155|189->158|189->158|192->161|192->161|193->162|194->163|194->163|195->164|195->164|195->164|196->165|197->166|197->166|198->167|198->167|198->167|198->167|199->168|200->169|200->169|201->170|202->171|202->171|204->173|205->174|205->174|206->175|207->176|207->176|208->177|210->179|210->179|212->181|212->181|213->182|215->184|215->184|217->186|217->186|218->187|220->189|220->189|223->192|223->192|225->194|225->194|225->194|226->195|230->199|230->199|231->200|234->203|234->203|234->203|234->203|235->204|236->205|236->205|237->206|239->208|239->208|240->209|240->209|241->210|242->211|242->211|246->215|247->216|247->216|248->217|250->219|250->219|251->220|252->221|252->221|253->222|254->223|254->223|255->224|255->224|255->224|255->224|255->224|255->224|256->225|256->225|257->226|257->226|257->226|258->227|260->229|260->229|261->230|264->233|264->233|265->234|265->234|265->234|265->234|266->235|270->239|270->239|271->240|271->240|277->246
                  -- GENERATED --
              */
          