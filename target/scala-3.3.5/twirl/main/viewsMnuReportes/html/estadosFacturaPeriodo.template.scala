
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

object estadosFacturaPeriodo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.TasasCambio,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, tasas: tables.TasasCambio):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "PROCESO DE EP ESTADO EQUIPO COBRAR "+mapDiccionario.get("ARRIENDO")+" POR DAÑOS","(SOLO CLIENTES)")),format.raw/*8.132*/("""
		"""),format.raw/*9.3*/("""<form action="/routes3/estadosFacturaProyecto/0" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">PERIODO A CONSIDERAR</TH>
							</TR>
							<TR>
								<TH>FECHA DESDE:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaDesde"
							  			name="fechaDesde"
										onblur="if(!limitaFecha(value,"""),_display_(/*24.42*/mapPermiso/*24.52*/.get("parametro.diasMenosGuia")),format.raw/*24.83*/(""","""),_display_(/*24.85*/mapPermiso/*24.95*/.get("parametro.diasMasGuia")),format.raw/*24.124*/(""")) value='"""),_display_(/*24.135*/fechaDesde),format.raw/*24.145*/("""';"
							  			value=""""),_display_(/*25.21*/fechaDesde),format.raw/*25.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*35.21*/fechaHasta),format.raw/*35.31*/(""""
					        			required>
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
							  			value=""""),_display_(/*51.21*/fechaHasta),format.raw/*51.31*/(""""
										onchange="obtieneTasas(value)"
					        			required>
								</td>
							</TR>
							"""),_display_(if(mapDiccionario.get("pais").equals("CHILE"))/*56.55*/{_display_(Seq[Any](format.raw/*56.56*/("""
								"""),format.raw/*57.9*/("""<TR>
									<TH>Unidad Fomento (UF):</TH>
									<td>
										<input type="text" class="form-control center"
											name="uf"
											id="uf"
											value=""""),_display_(/*63.20*/tasas/*63.25*/.getClpUf()),format.raw/*63.36*/(""""
											onfocus="value=value.replace(/,/g,''); cantAux = value;" 
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=1; value=formatStandar(value,2);"
											required>
									</td>
								</TR>
							""")))}else/*71.13*/{_display_(Seq[Any](format.raw/*71.14*/("""
								"""),format.raw/*72.9*/("""<input type="hidden" id="uf" name="uf" value="1">
							""")))}),format.raw/*73.9*/("""
							"""),format.raw/*74.8*/("""<TR>
								<TH>D&oacutelar (USD):</TH>
								<td>
									<input type="text" class="form-control center"
										name="usd"
										id="usd"
										value=""""),_display_(/*80.19*/tasas/*80.24*/.getClpUsd()),format.raw/*80.36*/(""""
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
										value=""""),_display_(/*94.19*/tasas/*94.24*/.getClpEur()),format.raw/*94.36*/(""""
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
					<input type="submit" id="btnSubmit" value='Generar Reporte' class="btn btn-primary btn-sm rounded-pill btn-block">
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*116.2*/("""


"""),format.raw/*119.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*120.31*/("""{"""),format.raw/*120.32*/("""
		  """),format.raw/*121.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*122.2*/("""}"""),format.raw/*122.3*/(""");
	
	const validarForm = () =>"""),format.raw/*124.27*/("""{"""),format.raw/*124.28*/("""
		"""),format.raw/*125.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*129.20*/("""{"""),format.raw/*129.21*/("""
			"""),format.raw/*130.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*131.87*/("""{"""),format.raw/*131.88*/("""
				"""),format.raw/*132.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*134.8*/("""}"""),format.raw/*134.9*/(""");
		"""),format.raw/*135.3*/("""}"""),format.raw/*135.4*/("""
		"""),format.raw/*136.3*/("""return(flag);
	"""),format.raw/*137.2*/("""}"""),format.raw/*137.3*/("""
	
	"""),format.raw/*139.2*/("""const obtieneTasas = (date) =>"""),format.raw/*139.32*/("""{"""),format.raw/*139.33*/("""
		"""),format.raw/*140.3*/("""let fecha = date.split("-");
		let x = new Date(fecha[0],(fecha[1]-1),fecha[2]);
		let today = new Date();
		let  y = new Date(today.getFullYear(),today.getMonth(),today.getDate());
		if (x > y)"""),format.raw/*144.13*/("""{"""),format.raw/*144.14*/("""
	    	"""),format.raw/*145.7*/("""alertify.alert("La fecha escogida no puede ser mayor a la fecha actual");
			$("#fechaCambio").val('"""),_display_(/*146.28*/fechaHasta),format.raw/*146.38*/("""');
			obtieneTasas(""""),_display_(/*147.19*/fechaHasta),format.raw/*147.29*/("""");
	    """),format.raw/*148.6*/("""}"""),format.raw/*148.7*/("""else"""),format.raw/*148.11*/("""{"""),format.raw/*148.12*/("""
    	  	"""),format.raw/*149.9*/("""if('"""),_display_(/*149.14*/mapDiccionario/*149.28*/.get("pais")),format.raw/*149.40*/("""'=="CHILE")"""),format.raw/*149.51*/("""{"""),format.raw/*149.52*/("""
				"""),format.raw/*150.5*/("""var formData = new FormData();
			  	formData.append('fecha',date);
		        $.ajax("""),format.raw/*152.18*/("""{"""),format.raw/*152.19*/("""
		            """),format.raw/*153.15*/("""url: "/tasasDeFechaAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(respuesta)"""),format.raw/*160.38*/("""{"""),format.raw/*160.39*/("""
			     		"""),format.raw/*161.11*/("""if(respuesta=="error")"""),format.raw/*161.33*/("""{"""),format.raw/*161.34*/("""
			     			"""),format.raw/*162.12*/("""alertify.alert(msgError, function () """),format.raw/*162.49*/("""{"""),format.raw/*162.50*/("""
				     			"""),format.raw/*163.13*/("""location.href = "/";
				     		"""),format.raw/*164.12*/("""}"""),format.raw/*164.13*/(""");
			     		"""),format.raw/*165.11*/("""}"""),format.raw/*165.12*/("""else"""),format.raw/*165.16*/("""{"""),format.raw/*165.17*/("""
							"""),format.raw/*166.8*/("""let valor = respuesta.split(";");
							$("#uf").val(formatStandar(valor[0],2));
							$("#usd").val(formatStandar(valor[1],2));
							$("#eur").val(formatStandar(valor[2],2));
						"""),format.raw/*170.7*/("""}"""),format.raw/*170.8*/("""
			     	"""),format.raw/*171.10*/("""}"""),format.raw/*171.11*/("""
		        """),format.raw/*172.11*/("""}"""),format.raw/*172.12*/(""");
    	  	"""),format.raw/*173.9*/("""}"""),format.raw/*173.10*/("""else"""),format.raw/*173.14*/("""{"""),format.raw/*173.15*/("""
    	  		"""),format.raw/*174.10*/("""document.getElementById('usd').value=1;
    	  		document.getElementById('eur').value=1;
    	  	"""),format.raw/*176.9*/("""}"""),format.raw/*176.10*/("""
	    	  
	    	 
	      """),format.raw/*179.8*/("""}"""),format.raw/*179.9*/("""
		
	"""),format.raw/*181.2*/("""}"""),format.raw/*181.3*/("""
	
"""),format.raw/*183.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String,tasas:tables.TasasCambio): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,tasas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.TasasCambio) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,tasas) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,tasas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/estadosFacturaPeriodo.scala.html
                  HASH: 3d55b839fc7bda7d6b07b0bdbd56389a860a9ce9
                  MATRIX: 1054->1|1311->165|1343->172|1359->180|1398->182|1427->186|1495->234|1523->236|1599->287|1748->415|1777->418|2429->1043|2448->1053|2500->1084|2529->1086|2548->1096|2599->1125|2638->1136|2670->1146|2721->1170|2752->1180|3021->1422|3052->1432|3486->1839|3517->1849|3694->1999|3733->2000|3769->2009|3970->2183|3984->2188|4016->2199|4331->2495|4370->2496|4406->2505|4494->2563|4529->2571|4724->2739|4738->2744|4771->2756|5243->3201|5257->3206|5290->3218|5940->3837|5971->3840|6062->3902|6092->3903|6125->3908|6219->3974|6248->3975|6308->4006|6338->4007|6369->4010|6593->4205|6623->4206|6655->4210|6784->4310|6814->4311|6847->4316|6940->4381|6969->4382|7002->4387|7031->4388|7062->4391|7105->4406|7134->4407|7166->4411|7225->4441|7255->4442|7286->4445|7509->4639|7539->4640|7574->4647|7703->4748|7735->4758|7785->4780|7817->4790|7854->4799|7883->4800|7916->4804|7946->4805|7983->4814|8016->4819|8040->4833|8074->4845|8114->4856|8144->4857|8177->4862|8291->4947|8321->4948|8365->4963|8637->5206|8667->5207|8707->5218|8758->5240|8788->5241|8829->5253|8895->5290|8925->5291|8967->5304|9028->5336|9058->5337|9100->5350|9130->5351|9163->5355|9193->5356|9229->5364|9443->5550|9472->5551|9511->5561|9541->5562|9581->5573|9611->5574|9650->5585|9680->5586|9713->5590|9743->5591|9782->5601|9907->5698|9937->5699|9990->5724|10019->5725|10052->5730|10081->5731|10112->5734
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|55->24|55->24|55->24|55->24|55->24|55->24|55->24|55->24|56->25|56->25|66->35|66->35|82->51|82->51|87->56|87->56|88->57|94->63|94->63|94->63|102->71|102->71|103->72|104->73|105->74|111->80|111->80|111->80|125->94|125->94|125->94|147->116|150->119|151->120|151->120|152->121|153->122|153->122|155->124|155->124|156->125|160->129|160->129|161->130|162->131|162->131|163->132|165->134|165->134|166->135|166->135|167->136|168->137|168->137|170->139|170->139|170->139|171->140|175->144|175->144|176->145|177->146|177->146|178->147|178->147|179->148|179->148|179->148|179->148|180->149|180->149|180->149|180->149|180->149|180->149|181->150|183->152|183->152|184->153|191->160|191->160|192->161|192->161|192->161|193->162|193->162|193->162|194->163|195->164|195->164|196->165|196->165|196->165|196->165|197->166|201->170|201->170|202->171|202->171|203->172|203->172|204->173|204->173|204->173|204->173|205->174|207->176|207->176|210->179|210->179|212->181|212->181|214->183
                  -- GENERATED --
              */
          