
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

object reportOtSel extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.Sucursal,tables.Comercial,List[tables.Sucursal],List[tables.Comercial],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "REPORTE "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase(),"(SELECCIONAR PERIODO MOVIL)")),format.raw/*9.128*/("""
		"""),format.raw/*10.3*/("""<form action="/routes2/reportOtRpt/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">PERIODO DE """),_display_(/*17.37*/mapDiccionario/*17.51*/.get("Ordenes_de_trabajo").toUpperCase()),format.raw/*17.91*/(""" """),format.raw/*17.92*/("""A CONSIDERAR</TH>
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
										"""),_display_(if(mapPermiso.get("cambiarSucursal")!=null)/*44.55*/{_display_(Seq[Any](format.raw/*44.56*/("""
											"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*45.57*/{_display_(Seq[Any](format.raw/*45.58*/("""
												"""),format.raw/*46.13*/("""<select id="selSucursal" class="custom-select center"  onchange = "$('#id_sucursal').val(value); actualizaComerciales(value); ">
													<option value="0">-- TODAS --</option>
													"""),_display_(/*48.15*/for(lista <- listSucursal) yield /*48.41*/{_display_(Seq[Any](format.raw/*48.42*/("""
														"""),format.raw/*49.15*/("""<option value=""""),_display_(/*49.31*/lista/*49.36*/.getId()),format.raw/*49.44*/("""">"""),_display_(/*49.47*/lista/*49.52*/.getNombre()),format.raw/*49.64*/("""</option>
													""")))}),format.raw/*50.15*/("""
												"""),format.raw/*51.13*/("""</select>
											""")))}else/*52.17*/{_display_(Seq[Any](format.raw/*52.18*/("""
												"""),format.raw/*53.13*/("""<select id="selSucursal" class="custom-select center"  onchange = "$('#id_sucursal').val(value); ">
													<option value="0">-- TODAS --</option>
													"""),_display_(/*55.15*/for(lista <- listSucursal) yield /*55.41*/{_display_(Seq[Any](format.raw/*55.42*/("""
														"""),format.raw/*56.15*/("""<option value=""""),_display_(/*56.31*/lista/*56.36*/.getId()),format.raw/*56.44*/("""">"""),_display_(/*56.47*/lista/*56.52*/.getNombre()),format.raw/*56.64*/("""</option>
													""")))}),format.raw/*57.15*/("""
												"""),format.raw/*58.13*/("""</select>
											""")))}),format.raw/*59.13*/("""
										""")))}else/*60.16*/{_display_(Seq[Any](format.raw/*60.17*/("""
											"""),format.raw/*61.12*/("""<input id="selSucursal" type="text" class="form-control left"
												value = """"),_display_(/*62.23*/sucursal/*62.31*/.getNombre()),format.raw/*62.43*/(""""
												readonly>
										""")))}),format.raw/*64.12*/("""
								"""),format.raw/*65.9*/("""</td>
							</TR>
							<tr>
								<TH>COMERCIAL:</TH>
								<td>
									<div id="actualComerciales">
										<input type="hidden" id="id_comercial" name="id_comercial" value="0">
										"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*72.56*/{_display_(Seq[Any](format.raw/*72.57*/("""
											"""),format.raw/*73.12*/("""<select  id="selComercial" class="custom-select center" onchange="$('#id_comercial').val(value)">
												<option value="0">-- TODOS --</option>
												"""),_display_(/*75.14*/for(lista <- listComercial) yield /*75.41*/{_display_(Seq[Any](format.raw/*75.42*/("""
													"""),format.raw/*76.14*/("""<option value=""""),_display_(/*76.30*/lista/*76.35*/.getId()),format.raw/*76.43*/("""">"""),_display_(/*76.46*/lista/*76.51*/.getNameUsuario()),format.raw/*76.68*/("""</option>
												""")))}),format.raw/*77.14*/("""
											"""),format.raw/*78.12*/("""</select>
										""")))}else/*79.16*/{_display_(Seq[Any](format.raw/*79.17*/("""
											"""),format.raw/*80.12*/("""<input  id="selComercial" type="text" class="form-control left"
												value = """"),_display_(/*81.23*/comercial/*81.32*/.getNameUsuario()),format.raw/*81.49*/(""""
												readonly>
										""")))}),format.raw/*83.12*/("""
									"""),format.raw/*84.10*/("""</div>
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

""")))}),format.raw/*105.2*/("""


"""),format.raw/*108.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*109.31*/("""{"""),format.raw/*109.32*/("""
		  """),format.raw/*110.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*111.2*/("""}"""),format.raw/*111.3*/(""");
	
	const validarForm = () =>"""),format.raw/*113.27*/("""{"""),format.raw/*113.28*/("""
		"""),format.raw/*114.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*118.20*/("""{"""),format.raw/*118.21*/("""
			"""),format.raw/*119.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*120.87*/("""{"""),format.raw/*120.88*/("""
				"""),format.raw/*121.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*123.8*/("""}"""),format.raw/*123.9*/(""");
		"""),format.raw/*124.3*/("""}"""),format.raw/*124.4*/("""
		"""),format.raw/*125.3*/("""return(flag);
	"""),format.raw/*126.2*/("""}"""),format.raw/*126.3*/("""
	
	"""),format.raw/*128.2*/("""const actualizaComerciales = (id_sucursal) =>"""),format.raw/*128.47*/("""{"""),format.raw/*128.48*/("""
		"""),format.raw/*129.3*/("""var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
        $.ajax("""),format.raw/*131.16*/("""{"""),format.raw/*131.17*/("""
            """),format.raw/*132.13*/("""url: "/actualizaComerciales2Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*139.36*/("""{"""),format.raw/*139.37*/("""
				"""),format.raw/*140.5*/("""document.getElementById('actualComerciales').innerHTML = respuesta;
				$("#formSucursal").val(id_sucursal);
	     	"""),format.raw/*142.8*/("""}"""),format.raw/*142.9*/("""
        """),format.raw/*143.9*/("""}"""),format.raw/*143.10*/(""");	
	"""),format.raw/*144.2*/("""}"""),format.raw/*144.3*/("""
	
	
	
"""),format.raw/*148.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCotizar/reportOtSel.scala.html
                  HASH: 44d7182d379365925de55fe57113c011f2de3b63
                  MATRIX: 1102->1|1465->271|1497->278|1513->286|1552->288|1581->292|1649->340|1677->342|1753->393|1898->517|1928->520|2333->898|2356->912|2417->952|2446->953|2691->1171|2722->1181|2991->1423|3022->1433|3295->1679|3334->1680|3418->1737|3457->1738|3498->1751|3720->1946|3762->1972|3801->1973|3844->1988|3887->2004|3901->2009|3930->2017|3960->2020|3974->2025|4007->2037|4062->2061|4103->2074|4148->2100|4187->2101|4228->2114|4421->2280|4463->2306|4502->2307|4545->2322|4588->2338|4602->2343|4631->2351|4661->2354|4675->2359|4708->2371|4763->2395|4804->2408|4857->2430|4892->2446|4931->2447|4971->2459|5082->2543|5099->2551|5132->2563|5198->2598|5234->2607|5506->2852|5545->2853|5585->2865|5774->3027|5817->3054|5856->3055|5898->3069|5941->3085|5955->3090|5984->3098|6014->3101|6028->3106|6066->3123|6120->3146|6160->3158|6204->3183|6243->3184|6283->3196|6396->3282|6414->3291|6452->3308|6518->3343|6556->3353|7070->3836|7101->3839|7192->3901|7222->3902|7255->3907|7349->3973|7378->3974|7438->4005|7468->4006|7499->4009|7723->4204|7753->4205|7785->4209|7914->4309|7944->4310|7977->4315|8070->4380|8099->4381|8132->4386|8161->4387|8192->4390|8235->4405|8264->4406|8296->4410|8370->4455|8400->4456|8431->4459|8552->4551|8582->4552|8624->4565|8891->4803|8921->4804|8954->4809|9098->4925|9127->4926|9164->4935|9194->4936|9227->4941|9256->4942|9291->4949
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|48->17|48->17|48->17|48->17|56->25|56->25|66->35|66->35|75->44|75->44|76->45|76->45|77->46|79->48|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|80->49|81->50|82->51|83->52|83->52|84->53|86->55|86->55|86->55|87->56|87->56|87->56|87->56|87->56|87->56|87->56|88->57|89->58|90->59|91->60|91->60|92->61|93->62|93->62|93->62|95->64|96->65|103->72|103->72|104->73|106->75|106->75|106->75|107->76|107->76|107->76|107->76|107->76|107->76|107->76|108->77|109->78|110->79|110->79|111->80|112->81|112->81|112->81|114->83|115->84|136->105|139->108|140->109|140->109|141->110|142->111|142->111|144->113|144->113|145->114|149->118|149->118|150->119|151->120|151->120|152->121|154->123|154->123|155->124|155->124|156->125|157->126|157->126|159->128|159->128|159->128|160->129|162->131|162->131|163->132|170->139|170->139|171->140|173->142|173->142|174->143|174->143|175->144|175->144|179->148
                  -- GENERATED --
              */
          