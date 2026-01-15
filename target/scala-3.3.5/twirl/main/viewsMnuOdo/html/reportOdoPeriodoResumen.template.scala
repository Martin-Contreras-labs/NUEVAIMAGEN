
package viewsMnuOdo.html

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

object reportOdoPeriodoResumen extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.TasasCambio,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, tasas: tables.TasasCambio):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "RESUMEN ODO DE EP/FACTURACION PROFORMA (POR PERIODO)","(SOLO CLIENTES)")),format.raw/*8.105*/("""
		"""),format.raw/*9.3*/("""<form action="/reportOdoResumen/" method="POST" onsubmit="return validarForm();">
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
					<input type="submit" value='Generar Reporte' class="btn btn-primary btn-sm rounded-pill btn-block">
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
		"""),format.raw/*124.3*/("""let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*127.20*/("""{"""),format.raw/*127.21*/("""
			"""),format.raw/*128.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*129.87*/("""{"""),format.raw/*129.88*/("""
				"""),format.raw/*130.5*/("""return(flag);
     		"""),format.raw/*131.8*/("""}"""),format.raw/*131.9*/(""");
		"""),format.raw/*132.3*/("""}"""),format.raw/*132.4*/("""
		"""),format.raw/*133.3*/("""return(flag);
	"""),format.raw/*134.2*/("""}"""),format.raw/*134.3*/("""
	
	"""),format.raw/*136.2*/("""const obtieneTasas = (date) =>"""),format.raw/*136.32*/("""{"""),format.raw/*136.33*/("""
		"""),format.raw/*137.3*/("""let fecha = date.split("-");
		let x = new Date(fecha[0],(fecha[1]-1),fecha[2]);
		let today = new Date();
		let  y = new Date(today.getFullYear(),today.getMonth(),today.getDate());
		if (x > y)"""),format.raw/*141.13*/("""{"""),format.raw/*141.14*/("""
	    	"""),format.raw/*142.7*/("""alertify.alert("La fecha escogida no puede ser mayor a la fecha actual");
			$("#fechaCambio").val('"""),_display_(/*143.28*/fechaHasta),format.raw/*143.38*/("""');
			obtieneTasas(""""),_display_(/*144.19*/fechaHasta),format.raw/*144.29*/("""");
	    """),format.raw/*145.6*/("""}"""),format.raw/*145.7*/("""else"""),format.raw/*145.11*/("""{"""),format.raw/*145.12*/("""
    	  	"""),format.raw/*146.9*/("""if('"""),_display_(/*146.14*/mapDiccionario/*146.28*/.get("pais")),format.raw/*146.40*/("""'=="CHILE")"""),format.raw/*146.51*/("""{"""),format.raw/*146.52*/("""
				"""),format.raw/*147.5*/("""var formData = new FormData();
			  	formData.append('fecha',date);
		        $.ajax("""),format.raw/*149.18*/("""{"""),format.raw/*149.19*/("""
		            """),format.raw/*150.15*/("""url: "/tasasDeFechaAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(respuesta)"""),format.raw/*157.38*/("""{"""),format.raw/*157.39*/("""
			     		"""),format.raw/*158.11*/("""if(respuesta=="error")"""),format.raw/*158.33*/("""{"""),format.raw/*158.34*/("""
			     			"""),format.raw/*159.12*/("""alertify.alert(msgError, function () """),format.raw/*159.49*/("""{"""),format.raw/*159.50*/("""
				     			"""),format.raw/*160.13*/("""location.href = "/";
				     		"""),format.raw/*161.12*/("""}"""),format.raw/*161.13*/(""");
			     		"""),format.raw/*162.11*/("""}"""),format.raw/*162.12*/("""else"""),format.raw/*162.16*/("""{"""),format.raw/*162.17*/("""
							"""),format.raw/*163.8*/("""let valor = respuesta.split(";");
							$("#uf").val(formatStandar(valor[0],2));
							$("#usd").val(formatStandar(valor[1],2));
							$("#eur").val(formatStandar(valor[2],2));
						"""),format.raw/*167.7*/("""}"""),format.raw/*167.8*/("""
			     	"""),format.raw/*168.10*/("""}"""),format.raw/*168.11*/("""
		        """),format.raw/*169.11*/("""}"""),format.raw/*169.12*/(""");
    	  	"""),format.raw/*170.9*/("""}"""),format.raw/*170.10*/("""else"""),format.raw/*170.14*/("""{"""),format.raw/*170.15*/("""
    	  		"""),format.raw/*171.10*/("""document.getElementById('usd').value=1;
    	  		document.getElementById('eur').value=1;
    	  	"""),format.raw/*173.9*/("""}"""),format.raw/*173.10*/("""
	    	  
	    	 
	      """),format.raw/*176.8*/("""}"""),format.raw/*176.9*/("""
		
	"""),format.raw/*178.2*/("""}"""),format.raw/*178.3*/("""
	
"""),format.raw/*180.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuOdo/reportOdoPeriodoResumen.scala.html
                  HASH: b92512e9e7c8ad7692d3badee75112f11bf1345a
                  MATRIX: 1051->1|1308->165|1340->172|1356->180|1395->182|1424->186|1492->234|1520->236|1596->287|1718->388|1747->391|2363->980|2394->990|2663->1232|2694->1242|3128->1649|3159->1659|3336->1809|3375->1810|3411->1819|3612->1993|3626->1998|3658->2009|3973->2305|4012->2306|4048->2315|4136->2373|4171->2381|4366->2549|4380->2554|4413->2566|4885->3011|4899->3016|4932->3028|5567->3632|5598->3635|5689->3697|5719->3698|5752->3703|5846->3769|5875->3770|5935->3801|5965->3802|5996->3805|6179->3959|6209->3960|6241->3964|6370->4064|6400->4065|6433->4070|6482->4091|6511->4092|6544->4097|6573->4098|6604->4101|6647->4116|6676->4117|6708->4121|6767->4151|6797->4152|6828->4155|7051->4349|7081->4350|7116->4357|7245->4458|7277->4468|7327->4490|7359->4500|7396->4509|7425->4510|7458->4514|7488->4515|7525->4524|7558->4529|7582->4543|7616->4555|7656->4566|7686->4567|7719->4572|7833->4657|7863->4658|7907->4673|8179->4916|8209->4917|8249->4928|8300->4950|8330->4951|8371->4963|8437->5000|8467->5001|8509->5014|8570->5046|8600->5047|8642->5060|8672->5061|8705->5065|8735->5066|8771->5074|8985->5260|9014->5261|9053->5271|9083->5272|9123->5283|9153->5284|9192->5295|9222->5296|9255->5300|9285->5301|9324->5311|9449->5408|9479->5409|9532->5434|9561->5435|9594->5440|9623->5441|9654->5444
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|55->24|55->24|65->34|65->34|81->50|81->50|86->55|86->55|87->56|93->62|93->62|93->62|101->70|101->70|102->71|103->72|104->73|110->79|110->79|110->79|124->93|124->93|124->93|146->115|149->118|150->119|150->119|151->120|152->121|152->121|154->123|154->123|155->124|158->127|158->127|159->128|160->129|160->129|161->130|162->131|162->131|163->132|163->132|164->133|165->134|165->134|167->136|167->136|167->136|168->137|172->141|172->141|173->142|174->143|174->143|175->144|175->144|176->145|176->145|176->145|176->145|177->146|177->146|177->146|177->146|177->146|177->146|178->147|180->149|180->149|181->150|188->157|188->157|189->158|189->158|189->158|190->159|190->159|190->159|191->160|192->161|192->161|193->162|193->162|193->162|193->162|194->163|198->167|198->167|199->168|199->168|200->169|200->169|201->170|201->170|201->170|201->170|202->171|204->173|204->173|207->176|207->176|209->178|209->178|211->180
                  -- GENERATED --
              */
          