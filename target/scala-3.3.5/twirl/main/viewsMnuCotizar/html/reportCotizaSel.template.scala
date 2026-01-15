
package viewsMnuCotizar.html

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

object reportCotizaSel extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.Sucursal,tables.Comercial,List[tables.Sucursal],List[tables.Comercial],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, 
sucursal: tables.Sucursal, comercial: tables.Comercial, listSucursal: List[tables.Sucursal], listComercial: List[tables.Comercial]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "REPORTE COTIZACIONES","(SELECCIONAR PERIODO MOVIL)")),format.raw/*9.85*/("""
		"""),format.raw/*10.3*/("""<form action="/reportCotizaRpt/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">PERIODO DE COTIZACIONES A CONSIDERAR</TH>
							</TR>
							<TR>
								<TH>FECHA DESDE:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaDesde"
							  			name="fechaDesde"
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
							<TR>
								<TH>SUCURSAL:</TH>
								<td>
									<input type="hidden" id="id_sucursal" name="id_sucursal" value="0">
										"""),_display_(if(mapPermiso.get("cambiarSucursal")!=null)/*43.55*/{_display_(Seq[Any](format.raw/*43.56*/("""
											"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*44.57*/{_display_(Seq[Any](format.raw/*44.58*/("""
												"""),format.raw/*45.13*/("""<select id="selSucursal" class="custom-select center"  onchange = "$('#id_sucursal').val(value); actualizaComerciales(value); ">
													<option value="0">-- TODAS --</option>
													"""),_display_(/*47.15*/for(lista <- listSucursal) yield /*47.41*/{_display_(Seq[Any](format.raw/*47.42*/("""
														"""),format.raw/*48.15*/("""<option value=""""),_display_(/*48.31*/lista/*48.36*/.getId()),format.raw/*48.44*/("""">"""),_display_(/*48.47*/lista/*48.52*/.getNombre()),format.raw/*48.64*/("""</option>
													""")))}),format.raw/*49.15*/("""
												"""),format.raw/*50.13*/("""</select>
											""")))}else/*51.17*/{_display_(Seq[Any](format.raw/*51.18*/("""
												"""),format.raw/*52.13*/("""<select id="selSucursal" class="custom-select center"  onchange = "$('#id_sucursal').val(value); ">
													<option value="0">-- TODAS --</option>
													"""),_display_(/*54.15*/for(lista <- listSucursal) yield /*54.41*/{_display_(Seq[Any](format.raw/*54.42*/("""
														"""),format.raw/*55.15*/("""<option value=""""),_display_(/*55.31*/lista/*55.36*/.getId()),format.raw/*55.44*/("""">"""),_display_(/*55.47*/lista/*55.52*/.getNombre()),format.raw/*55.64*/("""</option>
													""")))}),format.raw/*56.15*/("""
												"""),format.raw/*57.13*/("""</select>
											""")))}),format.raw/*58.13*/("""
										""")))}else/*59.16*/{_display_(Seq[Any](format.raw/*59.17*/("""
											"""),format.raw/*60.12*/("""<input id="selSucursal" type="text" class="form-control left"
												value = """"),_display_(/*61.23*/sucursal/*61.31*/.getNombre()),format.raw/*61.43*/(""""
												readonly>
										""")))}),format.raw/*63.12*/("""
								"""),format.raw/*64.9*/("""</td>
							</TR>
							<tr>
								<TH>COMERCIAL:</TH>
								<td>
									<div id="actualComerciales">
										<input type="hidden" id="id_comercial" name="id_comercial" value="0">
										"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*71.56*/{_display_(Seq[Any](format.raw/*71.57*/("""
											"""),format.raw/*72.12*/("""<select  id="selComercial" class="custom-select center" onchange="$('#id_comercial').val(value)">
												<option value="0">-- TODOS --</option>
												"""),_display_(/*74.14*/for(lista <- listComercial) yield /*74.41*/{_display_(Seq[Any](format.raw/*74.42*/("""
													"""),format.raw/*75.14*/("""<option value=""""),_display_(/*75.30*/lista/*75.35*/.getId()),format.raw/*75.43*/("""">"""),_display_(/*75.46*/lista/*75.51*/.getNameUsuario()),format.raw/*75.68*/("""</option>
												""")))}),format.raw/*76.14*/("""
											"""),format.raw/*77.12*/("""</select>
										""")))}else/*78.16*/{_display_(Seq[Any](format.raw/*78.17*/("""
											"""),format.raw/*79.12*/("""<input  id="selComercial" type="text" class="form-control left"
												value = """"),_display_(/*80.23*/comercial/*80.32*/.getNameUsuario()),format.raw/*80.49*/(""""
												readonly>
										""")))}),format.raw/*82.12*/("""
									"""),format.raw/*83.10*/("""</div>
								</td>
							</tr>
							
							<TR style="background-color: rgb(254, 255, 255)">
								<TH colspan="2"><br>
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

""")))}),format.raw/*104.2*/("""


"""),format.raw/*107.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*108.31*/("""{"""),format.raw/*108.32*/("""
		  """),format.raw/*109.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*110.2*/("""}"""),format.raw/*110.3*/(""");
	
	const validarForm = () =>"""),format.raw/*112.27*/("""{"""),format.raw/*112.28*/("""
		"""),format.raw/*113.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*117.20*/("""{"""),format.raw/*117.21*/("""
			"""),format.raw/*118.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*119.87*/("""{"""),format.raw/*119.88*/("""
				"""),format.raw/*120.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*122.8*/("""}"""),format.raw/*122.9*/(""");
		"""),format.raw/*123.3*/("""}"""),format.raw/*123.4*/("""
		"""),format.raw/*124.3*/("""return(flag);
	"""),format.raw/*125.2*/("""}"""),format.raw/*125.3*/("""
	
	"""),format.raw/*127.2*/("""const actualizaComerciales = (id_sucursal) =>"""),format.raw/*127.47*/("""{"""),format.raw/*127.48*/("""
		"""),format.raw/*128.3*/("""var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
        $.ajax("""),format.raw/*130.16*/("""{"""),format.raw/*130.17*/("""
            """),format.raw/*131.13*/("""url: "/actualizaComerciales2Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*138.36*/("""{"""),format.raw/*138.37*/("""
				"""),format.raw/*139.5*/("""document.getElementById('actualComerciales').innerHTML = respuesta;
				$("#formSucursal").val(id_sucursal);
	     	"""),format.raw/*141.8*/("""}"""),format.raw/*141.9*/("""
        """),format.raw/*142.9*/("""}"""),format.raw/*142.10*/(""");	
	"""),format.raw/*143.2*/("""}"""),format.raw/*143.3*/("""
	
	
	
"""),format.raw/*147.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String,sucursal:tables.Sucursal,comercial:tables.Comercial,listSucursal:List[tables.Sucursal],listComercial:List[tables.Comercial]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,sucursal,comercial,listSucursal,listComercial)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.Sucursal,tables.Comercial,List[tables.Sucursal],List[tables.Comercial]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,sucursal,comercial,listSucursal,listComercial) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,sucursal,comercial,listSucursal,listComercial)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/reportCotizaSel.scala.html
                  HASH: c19f69f319b57d4d82c8d4fe622eb08cfcb463e9
                  MATRIX: 1106->1|1469->271|1501->278|1517->286|1556->288|1585->292|1653->340|1681->342|1757->393|1858->474|1888->477|2519->1081|2550->1091|2819->1333|2850->1343|3115->1581|3154->1582|3238->1639|3277->1640|3318->1653|3540->1848|3582->1874|3621->1875|3664->1890|3707->1906|3721->1911|3750->1919|3780->1922|3794->1927|3827->1939|3882->1963|3923->1976|3968->2002|4007->2003|4048->2016|4241->2182|4283->2208|4322->2209|4365->2224|4408->2240|4422->2245|4451->2253|4481->2256|4495->2261|4528->2273|4583->2297|4624->2310|4677->2332|4712->2348|4751->2349|4791->2361|4902->2445|4919->2453|4952->2465|5018->2500|5054->2509|5326->2754|5365->2755|5405->2767|5594->2929|5637->2956|5676->2957|5718->2971|5761->2987|5775->2992|5804->3000|5834->3003|5848->3008|5886->3025|5940->3048|5980->3060|6024->3085|6063->3086|6103->3098|6216->3184|6234->3193|6272->3210|6338->3245|6376->3255|6890->3738|6921->3741|7012->3803|7042->3804|7075->3809|7169->3875|7198->3876|7258->3907|7288->3908|7319->3911|7543->4106|7573->4107|7605->4111|7734->4211|7764->4212|7797->4217|7890->4282|7919->4283|7952->4288|7981->4289|8012->4292|8055->4307|8084->4308|8116->4312|8190->4357|8220->4358|8251->4361|8372->4453|8402->4454|8444->4467|8711->4705|8741->4706|8774->4711|8918->4827|8947->4828|8984->4837|9014->4838|9047->4843|9076->4844|9111->4851
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|56->25|56->25|66->35|66->35|74->43|74->43|75->44|75->44|76->45|78->47|78->47|78->47|79->48|79->48|79->48|79->48|79->48|79->48|79->48|80->49|81->50|82->51|82->51|83->52|85->54|85->54|85->54|86->55|86->55|86->55|86->55|86->55|86->55|86->55|87->56|88->57|89->58|90->59|90->59|91->60|92->61|92->61|92->61|94->63|95->64|102->71|102->71|103->72|105->74|105->74|105->74|106->75|106->75|106->75|106->75|106->75|106->75|106->75|107->76|108->77|109->78|109->78|110->79|111->80|111->80|111->80|113->82|114->83|135->104|138->107|139->108|139->108|140->109|141->110|141->110|143->112|143->112|144->113|148->117|148->117|149->118|150->119|150->119|151->120|153->122|153->122|154->123|154->123|155->124|156->125|156->125|158->127|158->127|158->127|159->128|161->130|161->130|162->131|169->138|169->138|170->139|172->141|172->141|173->142|173->142|174->143|174->143|178->147
                  -- GENERATED --
              */
          