
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

object reportFacturaPeriodoResumen extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.TasasCambio,play.twirl.api.HtmlFormat.Appendable] {

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
		
		

		
		
		"""),format.raw/*14.3*/("""<form action="/reportFacturaResumen/" method="POST" onsubmit="return validarForm();">
		
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
                  SOURCE: app/viewsMnuReportes/reportFacturaPeriodoResumen.scala.html
                  HASH: c5d050c3dd54b31d31c4c806e49d2a75bfb47925
                  MATRIX: 1060->1|1317->165|1349->172|1365->180|1404->182|1433->186|1501->234|1529->236|1605->287|1723->384|1766->400|2389->996|2420->1006|2689->1248|2720->1258|3154->1665|3185->1675|3362->1825|3401->1826|3437->1835|3638->2009|3652->2014|3684->2025|3999->2321|4038->2322|4074->2331|4162->2389|4197->2397|4392->2565|4406->2570|4439->2582|4911->3027|4925->3032|4958->3044|5678->3733|5709->3736|5800->3798|5830->3799|5863->3804|5957->3870|5986->3871|6046->3902|6076->3903|6107->3906|6331->4101|6361->4102|6393->4106|6522->4206|6552->4207|6585->4212|6678->4277|6707->4278|6740->4283|6769->4284|6800->4287|6843->4302|6872->4303|6904->4307|6963->4337|6993->4338|7024->4341|7247->4535|7277->4536|7312->4543|7441->4644|7473->4654|7523->4676|7555->4686|7592->4695|7621->4696|7654->4700|7684->4701|7721->4710|7754->4715|7778->4729|7812->4741|7852->4752|7882->4753|7915->4758|8029->4843|8059->4844|8103->4859|8375->5102|8405->5103|8445->5114|8496->5136|8526->5137|8567->5149|8633->5186|8663->5187|8705->5200|8766->5232|8796->5233|8838->5246|8868->5247|8901->5251|8931->5252|8967->5260|9181->5446|9210->5447|9249->5457|9279->5458|9319->5469|9349->5470|9388->5481|9418->5482|9451->5486|9481->5487|9520->5497|9645->5594|9675->5595|9728->5620|9757->5621|9790->5626|9819->5627|9850->5630
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|45->14|61->30|61->30|71->40|71->40|87->56|87->56|92->61|92->61|93->62|99->68|99->68|99->68|107->76|107->76|108->77|109->78|110->79|116->85|116->85|116->85|130->99|130->99|130->99|152->121|155->124|156->125|156->125|157->126|158->127|158->127|160->129|160->129|161->130|165->134|165->134|166->135|167->136|167->136|168->137|170->139|170->139|171->140|171->140|172->141|173->142|173->142|175->144|175->144|175->144|176->145|180->149|180->149|181->150|182->151|182->151|183->152|183->152|184->153|184->153|184->153|184->153|185->154|185->154|185->154|185->154|185->154|185->154|186->155|188->157|188->157|189->158|196->165|196->165|197->166|197->166|197->166|198->167|198->167|198->167|199->168|200->169|200->169|201->170|201->170|201->170|201->170|202->171|206->175|206->175|207->176|207->176|208->177|208->177|209->178|209->178|209->178|209->178|210->179|212->181|212->181|215->184|215->184|217->186|217->186|219->188
                  -- GENERATED --
              */
          