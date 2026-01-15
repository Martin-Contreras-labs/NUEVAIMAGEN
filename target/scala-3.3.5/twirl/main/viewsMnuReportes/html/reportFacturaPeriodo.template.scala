
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

object reportFacturaPeriodo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.TasasCambio,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, tasas: tables.TasasCambio):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "PROCESO DE EP/FACTURACION PROFORMA","(SOLO CLIENTES)")),format.raw/*8.87*/("""
		"""),format.raw/*9.3*/("""<form action="/reportFacturaProyecto/0" method="POST" onsubmit="return validarForm();">
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
                  SOURCE: app/viewsMnuReportes/reportFacturaPeriodo.scala.html
                  HASH: eae7b0dca9bb8550f15d118f38258085085e2ab4
                  MATRIX: 1053->1|1310->165|1342->172|1358->180|1397->182|1426->186|1494->234|1522->236|1598->287|1701->370|1730->373|2352->968|2383->978|2652->1220|2683->1230|3117->1637|3148->1647|3325->1797|3364->1798|3400->1807|3601->1981|3615->1986|3647->1997|3962->2293|4001->2294|4037->2303|4125->2361|4160->2369|4355->2537|4369->2542|4402->2554|4874->2999|4888->3004|4921->3016|5571->3635|5602->3638|5693->3700|5723->3701|5756->3706|5850->3772|5879->3773|5939->3804|5969->3805|6000->3808|6224->4003|6254->4004|6286->4008|6415->4108|6445->4109|6478->4114|6571->4179|6600->4180|6633->4185|6662->4186|6693->4189|6736->4204|6765->4205|6797->4209|6856->4239|6886->4240|6917->4243|7140->4437|7170->4438|7205->4445|7334->4546|7366->4556|7416->4578|7448->4588|7485->4597|7514->4598|7547->4602|7577->4603|7614->4612|7647->4617|7671->4631|7705->4643|7745->4654|7775->4655|7808->4660|7922->4745|7952->4746|7996->4761|8268->5004|8298->5005|8338->5016|8389->5038|8419->5039|8460->5051|8526->5088|8556->5089|8598->5102|8659->5134|8689->5135|8731->5148|8761->5149|8794->5153|8824->5154|8860->5162|9074->5348|9103->5349|9142->5359|9172->5360|9212->5371|9242->5372|9281->5383|9311->5384|9344->5388|9374->5389|9413->5399|9538->5496|9568->5497|9621->5522|9650->5523|9683->5528|9712->5529|9743->5532
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|55->24|55->24|65->34|65->34|81->50|81->50|86->55|86->55|87->56|93->62|93->62|93->62|101->70|101->70|102->71|103->72|104->73|110->79|110->79|110->79|124->93|124->93|124->93|146->115|149->118|150->119|150->119|151->120|152->121|152->121|154->123|154->123|155->124|159->128|159->128|160->129|161->130|161->130|162->131|164->133|164->133|165->134|165->134|166->135|167->136|167->136|169->138|169->138|169->138|170->139|174->143|174->143|175->144|176->145|176->145|177->146|177->146|178->147|178->147|178->147|178->147|179->148|179->148|179->148|179->148|179->148|179->148|180->149|182->151|182->151|183->152|190->159|190->159|191->160|191->160|191->160|192->161|192->161|192->161|193->162|194->163|194->163|195->164|195->164|195->164|195->164|196->165|200->169|200->169|201->170|201->170|202->171|202->171|203->172|203->172|203->172|203->172|204->173|206->175|206->175|209->178|209->178|211->180|211->180|213->182
                  -- GENERATED --
              */
          