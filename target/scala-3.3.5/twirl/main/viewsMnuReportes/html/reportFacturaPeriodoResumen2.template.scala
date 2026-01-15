
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

object reportFacturaPeriodoResumen2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.TasasCambio,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, tasas: tables.TasasCambio):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "RESUMEN DE EP/FACTURACION PROFORMA (POR COMERCIAL)","(SOLO CLIENTES)")),format.raw/*8.103*/("""

		"""),format.raw/*10.3*/("""<form action="/reportFacturaResumen2/" method="POST" onsubmit="return validarForm();">
		
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
							  			value=""""),_display_(/*26.21*/fechaDesde),format.raw/*26.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*36.21*/fechaHasta),format.raw/*36.31*/(""""
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
							  			value=""""),_display_(/*52.21*/fechaHasta),format.raw/*52.31*/(""""
										onchange="obtieneTasas(value)"
					        			required>
								</td>
							</TR>
							"""),_display_(if(mapDiccionario.get("pais").equals("CHILE"))/*57.55*/{_display_(Seq[Any](format.raw/*57.56*/("""
								"""),format.raw/*58.9*/("""<TR>
									<TH>Unidad Fomento (UF):</TH>
									<td>
										<input type="text" class="form-control center"
											name="uf"
											id="uf"
											value=""""),_display_(/*64.20*/tasas/*64.25*/.getClpUf()),format.raw/*64.36*/(""""
											onfocus="value=value.replace(/,/g,''); cantAux = value;" 
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="if(value=='') value=1; value=formatStandar(value,2);"
											required>
									</td>
								</TR>
							""")))}else/*72.13*/{_display_(Seq[Any](format.raw/*72.14*/("""
								"""),format.raw/*73.9*/("""<input type="hidden" id="uf" name="uf" value="1">
							""")))}),format.raw/*74.9*/("""
							"""),format.raw/*75.8*/("""<TR>
								<TH>D&oacutelar (USD):</TH>
								<td>
									<input type="text" class="form-control center"
										name="usd"
										id="usd"
										value=""""),_display_(/*81.19*/tasas/*81.24*/.getClpUsd()),format.raw/*81.36*/(""""
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
										value=""""),_display_(/*95.19*/tasas/*95.24*/.getClpEur()),format.raw/*95.36*/(""""
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

""")))}),format.raw/*117.2*/("""


"""),format.raw/*120.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*121.31*/("""{"""),format.raw/*121.32*/("""
		  """),format.raw/*122.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*123.2*/("""}"""),format.raw/*123.3*/(""");
	
	const validarForm = () =>"""),format.raw/*125.27*/("""{"""),format.raw/*125.28*/("""
		"""),format.raw/*126.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*130.20*/("""{"""),format.raw/*130.21*/("""
			"""),format.raw/*131.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*132.87*/("""{"""),format.raw/*132.88*/("""
				"""),format.raw/*133.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*135.8*/("""}"""),format.raw/*135.9*/(""");
		"""),format.raw/*136.3*/("""}"""),format.raw/*136.4*/("""
		"""),format.raw/*137.3*/("""return(flag);
	"""),format.raw/*138.2*/("""}"""),format.raw/*138.3*/("""
	
	"""),format.raw/*140.2*/("""const obtieneTasas = (date) =>"""),format.raw/*140.32*/("""{"""),format.raw/*140.33*/("""
		"""),format.raw/*141.3*/("""let fecha = date.split("-");
		let x = new Date(fecha[0],(fecha[1]-1),fecha[2]);
		let today = new Date();
		let  y = new Date(today.getFullYear(),today.getMonth(),today.getDate());
		if (x > y)"""),format.raw/*145.13*/("""{"""),format.raw/*145.14*/("""
	    	"""),format.raw/*146.7*/("""alertify.alert("La fecha escogida no puede ser mayor a la fecha actual");
			$("#fechaCambio").val('"""),_display_(/*147.28*/fechaHasta),format.raw/*147.38*/("""');
			obtieneTasas(""""),_display_(/*148.19*/fechaHasta),format.raw/*148.29*/("""");
	    """),format.raw/*149.6*/("""}"""),format.raw/*149.7*/("""else"""),format.raw/*149.11*/("""{"""),format.raw/*149.12*/("""
    	  	"""),format.raw/*150.9*/("""if('"""),_display_(/*150.14*/mapDiccionario/*150.28*/.get("pais")),format.raw/*150.40*/("""'=="CHILE")"""),format.raw/*150.51*/("""{"""),format.raw/*150.52*/("""
				"""),format.raw/*151.5*/("""var formData = new FormData();
			  	formData.append('fecha',date);
		        $.ajax("""),format.raw/*153.18*/("""{"""),format.raw/*153.19*/("""
		            """),format.raw/*154.15*/("""url: "/tasasDeFechaAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(respuesta)"""),format.raw/*161.38*/("""{"""),format.raw/*161.39*/("""
			     		"""),format.raw/*162.11*/("""if(respuesta=="error")"""),format.raw/*162.33*/("""{"""),format.raw/*162.34*/("""
			     			"""),format.raw/*163.12*/("""alertify.alert(msgError, function () """),format.raw/*163.49*/("""{"""),format.raw/*163.50*/("""
				     			"""),format.raw/*164.13*/("""location.href = "/";
				     		"""),format.raw/*165.12*/("""}"""),format.raw/*165.13*/(""");
			     		"""),format.raw/*166.11*/("""}"""),format.raw/*166.12*/("""else"""),format.raw/*166.16*/("""{"""),format.raw/*166.17*/("""
							"""),format.raw/*167.8*/("""let valor = respuesta.split(";");
							$("#uf").val(formatStandar(valor[0],2));
							$("#usd").val(formatStandar(valor[1],2));
							$("#eur").val(formatStandar(valor[2],2));
						"""),format.raw/*171.7*/("""}"""),format.raw/*171.8*/("""
			     	"""),format.raw/*172.10*/("""}"""),format.raw/*172.11*/("""
		        """),format.raw/*173.11*/("""}"""),format.raw/*173.12*/(""");
    	  	"""),format.raw/*174.9*/("""}"""),format.raw/*174.10*/("""else"""),format.raw/*174.14*/("""{"""),format.raw/*174.15*/("""
    	  		"""),format.raw/*175.10*/("""document.getElementById('usd').value=1;
    	  		document.getElementById('eur').value=1;
    	  	"""),format.raw/*177.9*/("""}"""),format.raw/*177.10*/("""
	    	  
	    	 
	      """),format.raw/*180.8*/("""}"""),format.raw/*180.9*/("""
		
	"""),format.raw/*182.2*/("""}"""),format.raw/*182.3*/("""
	
"""),format.raw/*184.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuReportes/reportFacturaPeriodoResumen2.scala.html
                  HASH: d0dd170dca56e2c663e2f6d85ab6c43476500201
                  MATRIX: 1061->1|1318->165|1350->172|1366->180|1405->182|1434->186|1502->234|1530->236|1606->287|1726->386|1757->390|2381->987|2412->997|2681->1239|2712->1249|3146->1656|3177->1666|3354->1816|3393->1817|3429->1826|3630->2000|3644->2005|3676->2016|3991->2312|4030->2313|4066->2322|4154->2380|4189->2388|4384->2556|4398->2561|4431->2573|4903->3018|4917->3023|4950->3035|5670->3724|5701->3727|5792->3789|5822->3790|5855->3795|5949->3861|5978->3862|6038->3893|6068->3894|6099->3897|6323->4092|6353->4093|6385->4097|6514->4197|6544->4198|6577->4203|6670->4268|6699->4269|6732->4274|6761->4275|6792->4278|6835->4293|6864->4294|6896->4298|6955->4328|6985->4329|7016->4332|7239->4526|7269->4527|7304->4534|7433->4635|7465->4645|7515->4667|7547->4677|7584->4686|7613->4687|7646->4691|7676->4692|7713->4701|7746->4706|7770->4720|7804->4732|7844->4743|7874->4744|7907->4749|8021->4834|8051->4835|8095->4850|8367->5093|8397->5094|8437->5105|8488->5127|8518->5128|8559->5140|8625->5177|8655->5178|8697->5191|8758->5223|8788->5224|8830->5237|8860->5238|8893->5242|8923->5243|8959->5251|9173->5437|9202->5438|9241->5448|9271->5449|9311->5460|9341->5461|9380->5472|9410->5473|9443->5477|9473->5478|9512->5488|9637->5585|9667->5586|9720->5611|9749->5612|9782->5617|9811->5618|9842->5621
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|41->10|57->26|57->26|67->36|67->36|83->52|83->52|88->57|88->57|89->58|95->64|95->64|95->64|103->72|103->72|104->73|105->74|106->75|112->81|112->81|112->81|126->95|126->95|126->95|148->117|151->120|152->121|152->121|153->122|154->123|154->123|156->125|156->125|157->126|161->130|161->130|162->131|163->132|163->132|164->133|166->135|166->135|167->136|167->136|168->137|169->138|169->138|171->140|171->140|171->140|172->141|176->145|176->145|177->146|178->147|178->147|179->148|179->148|180->149|180->149|180->149|180->149|181->150|181->150|181->150|181->150|181->150|181->150|182->151|184->153|184->153|185->154|192->161|192->161|193->162|193->162|193->162|194->163|194->163|194->163|195->164|196->165|196->165|197->166|197->166|197->166|197->166|198->167|202->171|202->171|203->172|203->172|204->173|204->173|205->174|205->174|205->174|205->174|206->175|208->177|208->177|211->180|211->180|213->182|213->182|215->184
                  -- GENERATED --
              */
          