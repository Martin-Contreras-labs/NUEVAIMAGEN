
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

object reporteMovimientosPeriodo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.TasasCambio,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, tasas: tables.TasasCambio):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "PERIODO DE MOVIMIENTOS (VALORIZADO) POR " + mapDiccionario.get("BODEGA")+"/PROYECTO","(SOLO CLIENTES)")),format.raw/*8.136*/("""
		"""),format.raw/*9.3*/("""<form action="/reporteMovimientosListaProyectos/" method="POST" onsubmit="return validarForm();">
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
							  			value=""""),_display_(/*24.21*/fechaDesde),format.raw/*24.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*34.21*/fechaHasta),format.raw/*34.31*/(""""
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
							  			value=""""),_display_(/*50.21*/fechaHasta),format.raw/*50.31*/(""""
										onchange="obtieneTasas(value)"
					        			required>
								</td>
							</TR>
							"""),_display_(if(mapDiccionario.get("pais").equals("CHILE"))/*55.55*/{_display_(Seq[Any](format.raw/*55.56*/("""
								"""),format.raw/*56.9*/("""<TR>
									<TH>Unidad Fomento (UF):</TH>
									<td>
										<input type="text" class="form-control center"
											name="uf"
											id="uf"
											value=""""),_display_(/*62.20*/tasas/*62.25*/.getClpUf()),format.raw/*62.36*/(""""
											onfocus="value=value.replace(/,/g,''); cantAux = value;" 
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=1; value=formatStandar(value,2);"
											required>
									</td>
								</TR>
							""")))}else/*70.13*/{_display_(Seq[Any](format.raw/*70.14*/("""
								"""),format.raw/*71.9*/("""<input type="hidden" id="uf" name="uf" value="1">
							""")))}),format.raw/*72.9*/("""
							"""),format.raw/*73.8*/("""<TR>
								<TH>D&oacutelar (USD):</TH>
								<td>
									<input type="text" class="form-control center"
										name="usd"
										id="usd"
										value=""""),_display_(/*79.19*/tasas/*79.24*/.getClpUsd()),format.raw/*79.36*/(""""
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
										value=""""),_display_(/*93.19*/tasas/*93.24*/.getClpEur()),format.raw/*93.36*/(""""
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

""")))}),format.raw/*115.2*/("""


"""),format.raw/*118.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*119.31*/("""{"""),format.raw/*119.32*/("""
		  """),format.raw/*120.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*121.2*/("""}"""),format.raw/*121.3*/(""");
	
	const validarForm = () =>"""),format.raw/*123.27*/("""{"""),format.raw/*123.28*/("""
		"""),format.raw/*124.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*128.20*/("""{"""),format.raw/*128.21*/("""
			"""),format.raw/*129.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*130.87*/("""{"""),format.raw/*130.88*/("""
				"""),format.raw/*131.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*133.8*/("""}"""),format.raw/*133.9*/(""");
		"""),format.raw/*134.3*/("""}"""),format.raw/*134.4*/("""
		"""),format.raw/*135.3*/("""return(flag);
	"""),format.raw/*136.2*/("""}"""),format.raw/*136.3*/("""
	
	"""),format.raw/*138.2*/("""const obtieneTasas = (date) =>"""),format.raw/*138.32*/("""{"""),format.raw/*138.33*/("""
		"""),format.raw/*139.3*/("""let fecha = date.split("-");
		let x = new Date(fecha[0],(fecha[1]-1),fecha[2]);
		let today = new Date();
		let  y = new Date(today.getFullYear(),today.getMonth(),today.getDate());
		if (x > y)"""),format.raw/*143.13*/("""{"""),format.raw/*143.14*/("""
	    	"""),format.raw/*144.7*/("""alertify.alert("La fecha escogida no puede ser mayor a la fecha actual");
			$("#fechaCambio").val('"""),_display_(/*145.28*/fechaHasta),format.raw/*145.38*/("""');
			obtieneTasas(""""),_display_(/*146.19*/fechaHasta),format.raw/*146.29*/("""");
	    """),format.raw/*147.6*/("""}"""),format.raw/*147.7*/("""else"""),format.raw/*147.11*/("""{"""),format.raw/*147.12*/("""
    	  	"""),format.raw/*148.9*/("""if('"""),_display_(/*148.14*/mapDiccionario/*148.28*/.get("pais")),format.raw/*148.40*/("""'=="CHILE")"""),format.raw/*148.51*/("""{"""),format.raw/*148.52*/("""
				"""),format.raw/*149.5*/("""var formData = new FormData();
			  	formData.append('fecha',date);
		        $.ajax("""),format.raw/*151.18*/("""{"""),format.raw/*151.19*/("""
		            """),format.raw/*152.15*/("""url: "/tasasDeFechaAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(respuesta)"""),format.raw/*159.38*/("""{"""),format.raw/*159.39*/("""
			     		"""),format.raw/*160.11*/("""if(respuesta=="error")"""),format.raw/*160.33*/("""{"""),format.raw/*160.34*/("""
			     			"""),format.raw/*161.12*/("""alertify.alert(msgError, function () """),format.raw/*161.49*/("""{"""),format.raw/*161.50*/("""
				     			"""),format.raw/*162.13*/("""location.href = "/";
				     		"""),format.raw/*163.12*/("""}"""),format.raw/*163.13*/(""");
			     		"""),format.raw/*164.11*/("""}"""),format.raw/*164.12*/("""else"""),format.raw/*164.16*/("""{"""),format.raw/*164.17*/("""
							"""),format.raw/*165.8*/("""let valor = respuesta.split(";");
							$("#uf").val(formatStandar(valor[0],2));
							$("#usd").val(formatStandar(valor[1],2));
							$("#eur").val(formatStandar(valor[2],2));
						"""),format.raw/*169.7*/("""}"""),format.raw/*169.8*/("""
			     	"""),format.raw/*170.10*/("""}"""),format.raw/*170.11*/("""
		        """),format.raw/*171.11*/("""}"""),format.raw/*171.12*/(""");
    	  	"""),format.raw/*172.9*/("""}"""),format.raw/*172.10*/("""else"""),format.raw/*172.14*/("""{"""),format.raw/*172.15*/("""
    	  		"""),format.raw/*173.10*/("""document.getElementById('usd').value=1;
    	  		document.getElementById('eur').value=1;
    	  	"""),format.raw/*175.9*/("""}"""),format.raw/*175.10*/("""
	    	  
	    	 
	      """),format.raw/*178.8*/("""}"""),format.raw/*178.9*/("""
		
	"""),format.raw/*180.2*/("""}"""),format.raw/*180.3*/("""
	
"""),format.raw/*182.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuReportes/reporteMovimientosPeriodo.scala.html
                  HASH: c6647efea5294d28e87e1079e2b604179c1784b6
                  MATRIX: 1058->1|1315->165|1347->172|1363->180|1402->182|1431->186|1499->234|1527->236|1603->287|1756->419|1785->422|2417->1027|2448->1037|2717->1279|2748->1289|3182->1696|3213->1706|3390->1856|3429->1857|3465->1866|3666->2040|3680->2045|3712->2056|4027->2352|4066->2353|4102->2362|4190->2420|4225->2428|4420->2596|4434->2601|4467->2613|4939->3058|4953->3063|4986->3075|5636->3694|5667->3697|5758->3759|5788->3760|5821->3765|5915->3831|5944->3832|6004->3863|6034->3864|6065->3867|6289->4062|6319->4063|6351->4067|6480->4167|6510->4168|6543->4173|6636->4238|6665->4239|6698->4244|6727->4245|6758->4248|6801->4263|6830->4264|6862->4268|6921->4298|6951->4299|6982->4302|7205->4496|7235->4497|7270->4504|7399->4605|7431->4615|7481->4637|7513->4647|7550->4656|7579->4657|7612->4661|7642->4662|7679->4671|7712->4676|7736->4690|7770->4702|7810->4713|7840->4714|7873->4719|7987->4804|8017->4805|8061->4820|8333->5063|8363->5064|8403->5075|8454->5097|8484->5098|8525->5110|8591->5147|8621->5148|8663->5161|8724->5193|8754->5194|8796->5207|8826->5208|8859->5212|8889->5213|8925->5221|9139->5407|9168->5408|9207->5418|9237->5419|9277->5430|9307->5431|9346->5442|9376->5443|9409->5447|9439->5448|9478->5458|9603->5555|9633->5556|9686->5581|9715->5582|9748->5587|9777->5588|9808->5591
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|55->24|55->24|65->34|65->34|81->50|81->50|86->55|86->55|87->56|93->62|93->62|93->62|101->70|101->70|102->71|103->72|104->73|110->79|110->79|110->79|124->93|124->93|124->93|146->115|149->118|150->119|150->119|151->120|152->121|152->121|154->123|154->123|155->124|159->128|159->128|160->129|161->130|161->130|162->131|164->133|164->133|165->134|165->134|166->135|167->136|167->136|169->138|169->138|169->138|170->139|174->143|174->143|175->144|176->145|176->145|177->146|177->146|178->147|178->147|178->147|178->147|179->148|179->148|179->148|179->148|179->148|179->148|180->149|182->151|182->151|183->152|190->159|190->159|191->160|191->160|191->160|192->161|192->161|192->161|193->162|194->163|194->163|195->164|195->164|195->164|195->164|196->165|200->169|200->169|201->170|201->170|202->171|202->171|203->172|203->172|203->172|203->172|204->173|206->175|206->175|209->178|209->178|211->180|211->180|213->182
                  -- GENERATED --
              */
          