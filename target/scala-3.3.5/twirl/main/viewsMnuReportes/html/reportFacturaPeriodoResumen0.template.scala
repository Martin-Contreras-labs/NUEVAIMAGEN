
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

object reportFacturaPeriodoResumen0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.TasasCambio,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, tasas: tables.TasasCambio):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "RESUMEN DE EP/FACTURACION PROFORMA (POR PERIODO)","(SOLO CLIENTES)")),format.raw/*8.101*/("""
		
		

		
		
		"""),format.raw/*14.3*/("""<form action="/reportFacturaResumen0/" method="POST" onsubmit="return validarForm();">
		
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
							  			value=""""),_display_(/*30.21*/fechaDesde),format.raw/*30.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*40.21*/fechaHasta),format.raw/*40.31*/(""""
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
							  			value=""""),_display_(/*56.21*/fechaHasta),format.raw/*56.31*/(""""
										onchange="obtieneTasas(value)"
					        			required>
								</td>
							</TR>
							"""),_display_(if(mapDiccionario.get("pais").equals("CHILE"))/*61.55*/{_display_(Seq[Any](format.raw/*61.56*/("""
								"""),format.raw/*62.9*/("""<TR>
									<TH>Unidad Fomento (UF):</TH>
									<td>
										<input type="text" class="form-control center"
											name="uf"
											id="uf"
											value=""""),_display_(/*68.20*/tasas/*68.25*/.getClpUf()),format.raw/*68.36*/(""""
											onfocus="value=value.replace(/,/g,''); cantAux = value;" 
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=1; value=formatStandar(value,2);"
											required>
									</td>
								</TR>
							""")))}else/*76.13*/{_display_(Seq[Any](format.raw/*76.14*/("""
								"""),format.raw/*77.9*/("""<input type="hidden" id="uf" name="uf" value="1">
							""")))}),format.raw/*78.9*/("""
							"""),format.raw/*79.8*/("""<TR>
								<TH>D&oacutelar (USD):</TH>
								<td>
									<input type="text" class="form-control center"
										name="usd"
										id="usd"
										value=""""),_display_(/*85.19*/tasas/*85.24*/.getClpUsd()),format.raw/*85.36*/(""""
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
										value=""""),_display_(/*99.19*/tasas/*99.24*/.getClpEur()),format.raw/*99.36*/(""""
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
					<input type="submit" id="btnSubmit" value='Generar Reporte' onclick="document.getElementById('enProceso').style.display='block';" class="btn btn-primary btn-sm rounded-pill btn-block">
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*121.2*/("""


"""),format.raw/*124.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*125.31*/("""{"""),format.raw/*125.32*/("""
		  """),format.raw/*126.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*127.2*/("""}"""),format.raw/*127.3*/(""");
	
	const validarForm = () =>"""),format.raw/*129.27*/("""{"""),format.raw/*129.28*/("""
		"""),format.raw/*130.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*134.20*/("""{"""),format.raw/*134.21*/("""
			"""),format.raw/*135.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*136.87*/("""{"""),format.raw/*136.88*/("""
				"""),format.raw/*137.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*139.8*/("""}"""),format.raw/*139.9*/(""");
		"""),format.raw/*140.3*/("""}"""),format.raw/*140.4*/("""
		"""),format.raw/*141.3*/("""return(flag);
	"""),format.raw/*142.2*/("""}"""),format.raw/*142.3*/("""
	
	"""),format.raw/*144.2*/("""const obtieneTasas = (date) =>"""),format.raw/*144.32*/("""{"""),format.raw/*144.33*/("""
		"""),format.raw/*145.3*/("""let fecha = date.split("-");
		let x = new Date(fecha[0],(fecha[1]-1),fecha[2]);
		let today = new Date();
		let  y = new Date(today.getFullYear(),today.getMonth(),today.getDate());
		if (x > y)"""),format.raw/*149.13*/("""{"""),format.raw/*149.14*/("""
	    	"""),format.raw/*150.7*/("""alertify.alert("La fecha escogida no puede ser mayor a la fecha actual");
			$("#fechaCambio").val('"""),_display_(/*151.28*/fechaHasta),format.raw/*151.38*/("""');
			obtieneTasas(""""),_display_(/*152.19*/fechaHasta),format.raw/*152.29*/("""");
	    """),format.raw/*153.6*/("""}"""),format.raw/*153.7*/("""else"""),format.raw/*153.11*/("""{"""),format.raw/*153.12*/("""
    	  	"""),format.raw/*154.9*/("""if('"""),_display_(/*154.14*/mapDiccionario/*154.28*/.get("pais")),format.raw/*154.40*/("""'=="CHILE")"""),format.raw/*154.51*/("""{"""),format.raw/*154.52*/("""
				"""),format.raw/*155.5*/("""var formData = new FormData();
			  	formData.append('fecha',date);
		        $.ajax("""),format.raw/*157.18*/("""{"""),format.raw/*157.19*/("""
		            """),format.raw/*158.15*/("""url: "/tasasDeFechaAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(respuesta)"""),format.raw/*165.38*/("""{"""),format.raw/*165.39*/("""
			     		"""),format.raw/*166.11*/("""if(respuesta=="error")"""),format.raw/*166.33*/("""{"""),format.raw/*166.34*/("""
			     			"""),format.raw/*167.12*/("""alertify.alert(msgError, function () """),format.raw/*167.49*/("""{"""),format.raw/*167.50*/("""
				     			"""),format.raw/*168.13*/("""location.href = "/";
				     		"""),format.raw/*169.12*/("""}"""),format.raw/*169.13*/(""");
			     		"""),format.raw/*170.11*/("""}"""),format.raw/*170.12*/("""else"""),format.raw/*170.16*/("""{"""),format.raw/*170.17*/("""
							"""),format.raw/*171.8*/("""let valor = respuesta.split(";");
							$("#uf").val(formatStandar(valor[0],2));
							$("#usd").val(formatStandar(valor[1],2));
							$("#eur").val(formatStandar(valor[2],2));
						"""),format.raw/*175.7*/("""}"""),format.raw/*175.8*/("""
			     	"""),format.raw/*176.10*/("""}"""),format.raw/*176.11*/("""
		        """),format.raw/*177.11*/("""}"""),format.raw/*177.12*/(""");
    	  	"""),format.raw/*178.9*/("""}"""),format.raw/*178.10*/("""else"""),format.raw/*178.14*/("""{"""),format.raw/*178.15*/("""
    	  		"""),format.raw/*179.10*/("""document.getElementById('usd').value=1;
    	  		document.getElementById('eur').value=1;
    	  	"""),format.raw/*181.9*/("""}"""),format.raw/*181.10*/("""
	    	  
	    	 
	      """),format.raw/*184.8*/("""}"""),format.raw/*184.9*/("""
		
	"""),format.raw/*186.2*/("""}"""),format.raw/*186.3*/("""
	
"""),format.raw/*188.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuReportes/reportFacturaPeriodoResumen0.scala.html
                  HASH: a43df6d90999f0a9d02e6ec87cad9467fdf7f668
                  MATRIX: 1061->1|1318->165|1350->172|1366->180|1405->182|1434->186|1502->234|1530->236|1606->287|1724->384|1767->400|2391->997|2422->1007|2691->1249|2722->1259|3156->1666|3187->1676|3364->1826|3403->1827|3439->1836|3640->2010|3654->2015|3686->2026|4001->2322|4040->2323|4076->2332|4164->2390|4199->2398|4394->2566|4408->2571|4441->2583|4913->3028|4927->3033|4960->3045|5680->3734|5711->3737|5802->3799|5832->3800|5865->3805|5959->3871|5988->3872|6048->3903|6078->3904|6109->3907|6333->4102|6363->4103|6395->4107|6524->4207|6554->4208|6587->4213|6680->4278|6709->4279|6742->4284|6771->4285|6802->4288|6845->4303|6874->4304|6906->4308|6965->4338|6995->4339|7026->4342|7249->4536|7279->4537|7314->4544|7443->4645|7475->4655|7525->4677|7557->4687|7594->4696|7623->4697|7656->4701|7686->4702|7723->4711|7756->4716|7780->4730|7814->4742|7854->4753|7884->4754|7917->4759|8031->4844|8061->4845|8105->4860|8377->5103|8407->5104|8447->5115|8498->5137|8528->5138|8569->5150|8635->5187|8665->5188|8707->5201|8768->5233|8798->5234|8840->5247|8870->5248|8903->5252|8933->5253|8969->5261|9183->5447|9212->5448|9251->5458|9281->5459|9321->5470|9351->5471|9390->5482|9420->5483|9453->5487|9483->5488|9522->5498|9647->5595|9677->5596|9730->5621|9759->5622|9792->5627|9821->5628|9852->5631
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|45->14|61->30|61->30|71->40|71->40|87->56|87->56|92->61|92->61|93->62|99->68|99->68|99->68|107->76|107->76|108->77|109->78|110->79|116->85|116->85|116->85|130->99|130->99|130->99|152->121|155->124|156->125|156->125|157->126|158->127|158->127|160->129|160->129|161->130|165->134|165->134|166->135|167->136|167->136|168->137|170->139|170->139|171->140|171->140|172->141|173->142|173->142|175->144|175->144|175->144|176->145|180->149|180->149|181->150|182->151|182->151|183->152|183->152|184->153|184->153|184->153|184->153|185->154|185->154|185->154|185->154|185->154|185->154|186->155|188->157|188->157|189->158|196->165|196->165|197->166|197->166|197->166|198->167|198->167|198->167|199->168|200->169|200->169|201->170|201->170|201->170|201->170|202->171|206->175|206->175|207->176|207->176|208->177|208->177|209->178|209->178|209->178|209->178|210->179|212->181|212->181|215->184|215->184|217->186|217->186|219->188
                  -- GENERATED --
              */
          