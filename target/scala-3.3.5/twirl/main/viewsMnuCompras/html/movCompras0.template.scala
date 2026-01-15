
package viewsMnuCompras.html

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

object movCompras0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[tables.Proveedor],tables.TasasCambio,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, listProveedor: List[tables.Proveedor], tasas: tables.TasasCambio, fechaTasa: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "PERIODO DE MOVIMIENTOS DE COMPRAS","")),format.raw/*9.71*/("""
		"""),format.raw/*10.3*/("""<form action="/routes2/movCompras1/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>FECHA DESDE:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaDesde"
							  			name="fechaDesde"
							  			value=""""),_display_(/*22.21*/fechaDesde),format.raw/*22.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*32.21*/fechaHasta),format.raw/*32.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
							<TH>PROVEEDOR:</TH>
								<td>
									<input type="hidden" id="id_proveedor" name="id_proveedor" value="0">
									<select id="selSucursal" class="custom-select center"  onchange = "$('#id_proveedor').val(value); ">
										<option value="0">-- TODOS --</option>
										"""),_display_(/*42.12*/for(lista <- listProveedor) yield /*42.39*/{_display_(Seq[Any](format.raw/*42.40*/("""
											"""),format.raw/*43.12*/("""<option value=""""),_display_(/*43.28*/lista/*43.33*/.getId()),format.raw/*43.41*/("""">"""),_display_(/*43.44*/lista/*43.49*/.getNickName()),format.raw/*43.63*/("""</option>
										""")))}),format.raw/*44.12*/("""
									"""),format.raw/*45.10*/("""</select>
								</td>
							</TR>
							<TR style="background-color: rgb(254, 255, 255)">
								<TH colspan="2"><br>
							</TR>
							<TR>
								<TH colspan="2">TASAS DE CAMBIO</TH>
							</TR>
							<TR>
								<TH>Fecha:</TH>
								<td>
									<input type="date" class="form-control center"
									id="fechaCambio"
									name="fechaCambio"
									value=""""),_display_(/*60.18*/fechaTasa),format.raw/*60.27*/(""""
									onchange="obtieneTasas(value)"
									required>
								</td>
							</TR>
							"""),_display_(if(mapDiccionario.get("pais").equals("CHILE"))/*65.55*/{_display_(Seq[Any](format.raw/*65.56*/("""
								"""),format.raw/*66.9*/("""<TR>
									<TH>Unidad Fomento (UF):</TH>
									<td>
										<input type="text" class="form-control center"
										name="uf"
										id="uf"
										value=""""),_display_(/*72.19*/tasas/*72.24*/.getClpUf()),format.raw/*72.35*/(""""
										onfocus="value=value.replace(/,/g,''); cantAux = value;"
										onkeydown="return ingresoDouble(window.event)"
										autocomplete="off"
										onchange="if(value=='') value=1; value=formatStandar(value,2);"
										required>
									</td>
								</TR>
							""")))}else/*80.13*/{_display_(Seq[Any](format.raw/*80.14*/("""
								"""),format.raw/*81.9*/("""<input type="hidden" id="uf" name="uf" value="1">
							""")))}),format.raw/*82.9*/("""
							"""),format.raw/*83.8*/("""<TR>
								<TH>D&oacutelar (USD):</TH>
								<td>
									<input type="text" class="form-control center"
									name="usd"
									id="usd"
									value=""""),_display_(/*89.18*/tasas/*89.23*/.getClpUsd()),format.raw/*89.35*/(""""
									onfocus="value=value.replace(/,/g,''); cantAux = value;"
									onkeydown="return ingresoDouble(window.event)"
									autocomplete="off"
									onchange="if(value=='') value=1; value=formatStandar(value,2);"
									required>
								</td>
							</TR>
							<TR>
								<TH>Euro (EUR):</TH>
								<td>
									<input type="text" class="form-control center"
									name="eur"
									id="eur"
									value=""""),_display_(/*103.18*/tasas/*103.23*/.getClpEur()),format.raw/*103.35*/(""""
									onfocus="value=value.replace(/,/g,''); cantAux = value;"
									onkeydown="return ingresoDouble(window.event)"
									autocomplete="off"
									onchange="if(value=='') value=1; value=formatStandar(value,2);"
									required>
								</td>
							</TR>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row  justify-content-md-center">
				<div class="col-xs-4 col-sm-2 col-md-2 col-lg-2">
					<input type="submit" value='Generar Reporte' class="btn btn-primary btn-sm rounded-pill btn-block">
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*125.2*/("""


"""),format.raw/*128.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*129.31*/("""{"""),format.raw/*129.32*/("""
		  """),format.raw/*130.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*131.2*/("""}"""),format.raw/*131.3*/(""");
	
	const validarForm = () =>"""),format.raw/*133.27*/("""{"""),format.raw/*133.28*/("""
		"""),format.raw/*134.3*/("""let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*137.20*/("""{"""),format.raw/*137.21*/("""
			"""),format.raw/*138.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*139.87*/("""{"""),format.raw/*139.88*/("""
				"""),format.raw/*140.5*/("""return(flag);
     		"""),format.raw/*141.8*/("""}"""),format.raw/*141.9*/(""");
		"""),format.raw/*142.3*/("""}"""),format.raw/*142.4*/("""
		"""),format.raw/*143.3*/("""return(flag);
	"""),format.raw/*144.2*/("""}"""),format.raw/*144.3*/("""

	"""),format.raw/*146.2*/("""const obtieneTasas = (date) =>"""),format.raw/*146.32*/("""{"""),format.raw/*146.33*/("""
		"""),format.raw/*147.3*/("""let fecha = date.split("-");
		let x = new Date(fecha[0],(fecha[1]-1),fecha[2]);
		let today = new Date();
		let  y = new Date(today.getFullYear(),today.getMonth(),today.getDate());
		if (x > y)"""),format.raw/*151.13*/("""{"""),format.raw/*151.14*/("""
			"""),format.raw/*152.4*/("""alertify.alert("La fecha escogida no puede ser mayor a la fecha actual");
			$("#fechaCambio").val('"""),_display_(/*153.28*/fechaTasa),format.raw/*153.37*/("""');
			obtieneTasas(""""),_display_(/*154.19*/fechaTasa),format.raw/*154.28*/("""");
		"""),format.raw/*155.3*/("""}"""),format.raw/*155.4*/("""else"""),format.raw/*155.8*/("""{"""),format.raw/*155.9*/("""
			"""),format.raw/*156.4*/("""if('"""),_display_(/*156.9*/mapDiccionario/*156.23*/.get("pais")),format.raw/*156.35*/("""'=="CHILE")"""),format.raw/*156.46*/("""{"""),format.raw/*156.47*/("""
				"""),format.raw/*157.5*/("""var formData = new FormData();
				formData.append('fecha',date);
				$.ajax("""),format.raw/*159.12*/("""{"""),format.raw/*159.13*/("""
					"""),format.raw/*160.6*/("""url: "/tasasDeFechaAjax/",
					type: "POST",
					method: "POST",
					data: formData,
					cache: false,
					contentType: false,
					processData: false,
					success: function(respuesta)"""),format.raw/*167.34*/("""{"""),format.raw/*167.35*/("""
						"""),format.raw/*168.7*/("""if(respuesta=="error")"""),format.raw/*168.29*/("""{"""),format.raw/*168.30*/("""
							"""),format.raw/*169.8*/("""alertify.alert(msgError, function () """),format.raw/*169.45*/("""{"""),format.raw/*169.46*/("""
								"""),format.raw/*170.9*/("""location.href = "/";
							"""),format.raw/*171.8*/("""}"""),format.raw/*171.9*/(""");
						"""),format.raw/*172.7*/("""}"""),format.raw/*172.8*/("""else"""),format.raw/*172.12*/("""{"""),format.raw/*172.13*/("""
							"""),format.raw/*173.8*/("""let valor = respuesta.split(";");
							$("#uf").val(formatStandar(valor[0],2));
							$("#usd").val(formatStandar(valor[1],2));
							$("#eur").val(formatStandar(valor[2],2));
						"""),format.raw/*177.7*/("""}"""),format.raw/*177.8*/("""
					"""),format.raw/*178.6*/("""}"""),format.raw/*178.7*/("""
				"""),format.raw/*179.5*/("""}"""),format.raw/*179.6*/(""");
			"""),format.raw/*180.4*/("""}"""),format.raw/*180.5*/("""else"""),format.raw/*180.9*/("""{"""),format.raw/*180.10*/("""
				"""),format.raw/*181.5*/("""document.getElementById('usd').value=1;
				document.getElementById('eur').value=1;
			"""),format.raw/*183.4*/("""}"""),format.raw/*183.5*/("""


		"""),format.raw/*186.3*/("""}"""),format.raw/*186.4*/("""

	"""),format.raw/*188.2*/("""}"""),format.raw/*188.3*/("""
	
"""),format.raw/*190.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String,listProveedor:List[tables.Proveedor],tasas:tables.TasasCambio,fechaTasa:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,listProveedor,tasas,fechaTasa)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[tables.Proveedor],tables.TasasCambio,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,listProveedor,tasas,fechaTasa) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,listProveedor,tasas,fechaTasa)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCompras/movCompras0.scala.html
                  HASH: e2f096b5f356c59cabc29233a722ac976f6c83ea
                  MATRIX: 1073->1|1388->223|1421->231|1437->239|1476->241|1505->245|1573->293|1601->295|1677->346|1764->413|1794->416|2338->933|2369->943|2638->1185|2669->1195|3052->1551|3095->1578|3134->1579|3174->1591|3217->1607|3231->1612|3260->1620|3290->1623|3304->1628|3339->1642|3391->1663|3429->1673|3838->2055|3868->2064|4037->2206|4076->2207|4112->2216|4310->2387|4324->2392|4356->2403|4665->2693|4704->2694|4740->2703|4828->2761|4863->2769|5055->2934|5069->2939|5102->2951|5566->3387|5581->3392|5615->3404|6244->4002|6275->4005|6366->4067|6396->4068|6429->4073|6523->4139|6552->4140|6612->4171|6642->4172|6673->4175|6856->4329|6886->4330|6918->4334|7047->4434|7077->4435|7110->4440|7159->4461|7188->4462|7221->4467|7250->4468|7281->4471|7324->4486|7353->4487|7384->4490|7443->4520|7473->4521|7504->4524|7727->4718|7757->4719|7789->4723|7918->4824|7949->4833|7999->4855|8030->4864|8064->4870|8093->4871|8125->4875|8154->4876|8186->4880|8218->4885|8242->4899|8276->4911|8316->4922|8346->4923|8379->4928|8485->5005|8515->5006|8549->5012|8768->5202|8798->5203|8833->5210|8884->5232|8914->5233|8950->5241|9016->5278|9046->5279|9083->5288|9139->5316|9168->5317|9205->5326|9234->5327|9267->5331|9297->5332|9333->5340|9547->5526|9576->5527|9610->5533|9639->5534|9672->5539|9701->5540|9735->5546|9764->5547|9796->5551|9826->5552|9859->5557|9974->5644|10003->5645|10036->5650|10065->5651|10096->5654|10125->5655|10156->5658
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|63->32|63->32|73->42|73->42|73->42|74->43|74->43|74->43|74->43|74->43|74->43|74->43|75->44|76->45|91->60|91->60|96->65|96->65|97->66|103->72|103->72|103->72|111->80|111->80|112->81|113->82|114->83|120->89|120->89|120->89|134->103|134->103|134->103|156->125|159->128|160->129|160->129|161->130|162->131|162->131|164->133|164->133|165->134|168->137|168->137|169->138|170->139|170->139|171->140|172->141|172->141|173->142|173->142|174->143|175->144|175->144|177->146|177->146|177->146|178->147|182->151|182->151|183->152|184->153|184->153|185->154|185->154|186->155|186->155|186->155|186->155|187->156|187->156|187->156|187->156|187->156|187->156|188->157|190->159|190->159|191->160|198->167|198->167|199->168|199->168|199->168|200->169|200->169|200->169|201->170|202->171|202->171|203->172|203->172|203->172|203->172|204->173|208->177|208->177|209->178|209->178|210->179|210->179|211->180|211->180|211->180|211->180|212->181|214->183|214->183|217->186|217->186|219->188|219->188|221->190
                  -- GENERATED --
              */
          