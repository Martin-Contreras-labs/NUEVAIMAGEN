
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

object servicioModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.ClaseServicio],List[tables.UnidadServicio],List[tables.Moneda],tables.Servicio,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listClases: List[tables.ClaseServicio], listUnidades: List[tables.UnidadServicio], listMonedas: List[tables.Moneda], servicio: tables.Servicio):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR SERVICIO", "")),format.raw/*9.57*/("""
		"""),format.raw/*10.3*/("""<form action="/servicioUpdate/" method="POST">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<input type="hidden" name="id" value=""""),_display_(/*13.45*/servicio/*13.53*/.getId()),format.raw/*13.61*/("""">
					<input type="hidden" name="cambioElprecio" value="0">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<TR>
							<td  style="text-align:left;">FAMILIA</td>
							<td style="text-align:left">
								<select class="custom-select"
									name="id_claseServicio"
									required>
									<option value=""""),_display_(/*22.26*/servicio/*22.34*/.getId_claseServicio()),format.raw/*22.56*/("""">"""),_display_(/*22.59*/servicio/*22.67*/.getNombreClase()),format.raw/*22.84*/("""</option>
									"""),_display_(/*23.11*/for(lista <- listClases) yield /*23.35*/{_display_(Seq[Any](format.raw/*23.36*/("""
										"""),format.raw/*24.11*/("""<option value=""""),_display_(/*24.27*/lista/*24.32*/.getId()),format.raw/*24.40*/("""">"""),_display_(/*24.43*/lista/*24.48*/.getNombre()),format.raw/*24.60*/("""</option>
									""")))}),format.raw/*25.11*/("""
								"""),format.raw/*26.9*/("""</select>
							</td>
						</TR>
						<tr>
							<td style="text-align:left; vertical-align:top">CODIGO SERVICIO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="codigo"
									name="codigo"
									value = """"),_display_(/*35.20*/servicio/*35.28*/.getCodigo()),format.raw/*35.40*/(""""
									required
									autocomplete="off"
									onkeydown="return sinReservCodigos(window.event)"
									onchange="value=value.replace(/\s/g,'').toUpperCase(); verificaCodigo(value)"
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">NOMBRE SERVICIO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="nombre"
									value = """"),_display_(/*48.20*/servicio/*48.28*/.getNombre()),format.raw/*48.40*/(""""
									onkeydown="return sinReservados(window.event)"
									required
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">UNIDAD: </td>
							<td style="text-align:left; vertical-align:top">
								<select class="custom-select" 
									name="id_unidadServicio"
									required>
									<option value=""""),_display_(/*61.26*/servicio/*61.34*/.getId_unidadServicio()),format.raw/*61.57*/("""">"""),_display_(/*61.60*/servicio/*61.68*/.getNombreUnidad()),format.raw/*61.86*/("""</option>
									"""),_display_(/*62.11*/for(lista <- listUnidades) yield /*62.37*/{_display_(Seq[Any](format.raw/*62.38*/("""
										"""),format.raw/*63.11*/("""<option value=""""),_display_(/*63.27*/lista/*63.32*/.id),format.raw/*63.35*/("""">"""),_display_(/*63.38*/lista/*63.43*/.nombre),format.raw/*63.50*/("""</option>
									""")))}),format.raw/*64.11*/("""
								"""),format.raw/*65.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">MONEDA: </td>
							<td style="text-align:left; vertical-align:top">
								<select class="custom-select" 
									name="id_moneda"
									id="id_moneda"
									onchange="formateaPrecio(value)"
									required>
									<option value=""""),_display_(/*76.26*/servicio/*76.34*/.getId_moneda()),format.raw/*76.49*/("""">"""),_display_(/*76.52*/servicio/*76.60*/.getNickMoneda()),format.raw/*76.76*/("""</option>
									"""),_display_(/*77.11*/for(lista <- listMonedas) yield /*77.36*/{_display_(Seq[Any](format.raw/*77.37*/("""
										"""),format.raw/*78.11*/("""<option value=""""),_display_(/*78.27*/lista/*78.32*/.id),format.raw/*78.35*/("""">"""),_display_(/*78.38*/lista/*78.43*/.getNickName()),format.raw/*78.57*/("""</option>
									""")))}),format.raw/*79.11*/("""
								"""),format.raw/*80.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">PRECIO UNITARIO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="precio"
									id="precio"
									value = """"),_display_(/*89.20*/servicio/*89.28*/.getPrecio()),format.raw/*89.40*/(""""
									onfocus="value=value.replace(/,/g,'')" 
									onfocusout = "if(value.trim()=='') value=0; formateaPrecio($('#id_moneda').val());"
									onkeydown="return ingresoDouble(window.event)"
									onchange="if(value.trim()=='') value=0; formateaPrecio($('#id_moneda').val()); $('#cambioElprecio').val('1');">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">DESCRIPCION: </td>
							<td style="text-align:left; vertical-align:top">
								<textarea class="form-control" 
									name="descripcion" 
									autocomplete="off">"""),_display_(/*101.30*/servicio/*101.38*/.getDescripcion()),format.raw/*101.55*/("""</textarea>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit"  value="GRABAR" class="btn btn-success btn-sm rounded-pill btn-block">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/servicioMantencion/';">
					</div>
				</div>
			</div>
		</form>
	</div>
""")))}),format.raw/*119.2*/("""




"""),format.raw/*124.1*/("""<script type="text/javascript">

	let mapMonDec = """),format.raw/*126.18*/("""{"""),format.raw/*126.19*/("""}"""),format.raw/*126.20*/(""";

	$(document).ready(function() """),format.raw/*128.31*/("""{"""),format.raw/*128.32*/("""
		"""),format.raw/*129.3*/("""$('#precio').val(formatStandar($('#precio').val(),'"""),_display_(/*129.55*/servicio/*129.63*/.getNroDecimal()),format.raw/*129.79*/("""'));
		"""),_display_(/*130.4*/for(lista <- listMonedas) yield /*130.29*/{_display_(Seq[Any](format.raw/*130.30*/("""
			"""),format.raw/*131.4*/("""mapMonDec['"""),_display_(/*131.16*/lista/*131.21*/.id),format.raw/*131.24*/("""'] = '"""),_display_(/*131.31*/lista/*131.36*/.numeroDecimales),format.raw/*131.52*/("""';
		""")))}),format.raw/*132.4*/("""
		"""),format.raw/*133.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*134.2*/("""}"""),format.raw/*134.3*/(""");
	
	const formateaPrecio = (id_moneda) => """),format.raw/*136.40*/("""{"""),format.raw/*136.41*/("""
		"""),format.raw/*137.3*/("""$('#precio').val(formatStandar($('#precio').val(),mapMonDec[id_moneda]));
	"""),format.raw/*138.2*/("""}"""),format.raw/*138.3*/("""
	
	
	"""),format.raw/*141.2*/("""const verificaCodigo = (codigo) => """),format.raw/*141.37*/("""{"""),format.raw/*141.38*/("""
		"""),format.raw/*142.3*/("""var formData = new FormData();
	  	formData.append('codigo',codigo);
        $.ajax("""),format.raw/*144.16*/("""{"""),format.raw/*144.17*/("""
            """),format.raw/*145.13*/("""url: "/verificaCodigoServicioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*152.36*/("""{"""),format.raw/*152.37*/("""
	     		"""),format.raw/*153.9*/("""if(respuesta=="existe")"""),format.raw/*153.32*/("""{"""),format.raw/*153.33*/("""
	     			"""),format.raw/*154.10*/("""alertify.alert("El código de servicio ya existe, intente con otro", function () """),format.raw/*154.90*/("""{"""),format.raw/*154.91*/("""
	     				"""),format.raw/*155.11*/("""$("#codigo").val("");
		     		"""),format.raw/*156.10*/("""}"""),format.raw/*156.11*/(""");
	     		"""),format.raw/*157.9*/("""}"""),format.raw/*157.10*/("""
	     		"""),format.raw/*158.9*/("""if(respuesta=="error")"""),format.raw/*158.31*/("""{"""),format.raw/*158.32*/("""
	     			"""),format.raw/*159.10*/("""alertify.alert(msgError, function () """),format.raw/*159.47*/("""{"""),format.raw/*159.48*/("""
		     			"""),format.raw/*160.11*/("""location.href = "/";
		     		"""),format.raw/*161.10*/("""}"""),format.raw/*161.11*/(""");
	     		"""),format.raw/*162.9*/("""}"""),format.raw/*162.10*/("""
	     	"""),format.raw/*163.8*/("""}"""),format.raw/*163.9*/("""
        """),format.raw/*164.9*/("""}"""),format.raw/*164.10*/(""");
	"""),format.raw/*165.2*/("""}"""),format.raw/*165.3*/("""
	
	

"""),format.raw/*169.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listClases:List[tables.ClaseServicio],listUnidades:List[tables.UnidadServicio],listMonedas:List[tables.Moneda],servicio:tables.Servicio): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listClases,listUnidades,listMonedas,servicio)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.ClaseServicio],List[tables.UnidadServicio],List[tables.Moneda],tables.Servicio) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listClases,listUnidades,listMonedas,servicio) => apply(mapDiccionario,mapPermiso,userMnu,listClases,listUnidades,listMonedas,servicio)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/servicioModifica.scala.html
                  HASH: 4a22a5c5c9e2e76a5e0621e0ef5c3d557dc22ab6
                  MATRIX: 1102->1|1436->242|1469->250|1485->258|1524->260|1552->263|1620->311|1648->313|1724->364|1797->417|1827->420|2048->614|2065->622|2094->630|2474->983|2491->991|2534->1013|2564->1016|2581->1024|2619->1041|2666->1061|2706->1085|2745->1086|2784->1097|2827->1113|2841->1118|2870->1126|2900->1129|2914->1134|2947->1146|2998->1166|3034->1175|3357->1471|3374->1479|3407->1491|3918->1975|3935->1983|3968->1995|4403->2403|4420->2411|4464->2434|4494->2437|4511->2445|4550->2463|4597->2483|4639->2509|4678->2510|4717->2521|4760->2537|4774->2542|4798->2545|4828->2548|4842->2553|4870->2560|4921->2580|4957->2589|5330->2935|5347->2943|5383->2958|5413->2961|5430->2969|5467->2985|5514->3005|5555->3030|5594->3031|5633->3042|5676->3058|5690->3063|5714->3066|5744->3069|5758->3074|5793->3088|5844->3108|5880->3117|6203->3413|6220->3421|6253->3433|6866->4018|6884->4026|6923->4043|7518->4607|7551->4612|7630->4662|7660->4663|7690->4664|7752->4697|7782->4698|7813->4701|7893->4753|7911->4761|7949->4777|7984->4785|8026->4810|8066->4811|8098->4815|8138->4827|8153->4832|8178->4835|8213->4842|8228->4847|8266->4863|8303->4869|8334->4872|8428->4938|8457->4939|8530->4983|8560->4984|8591->4987|8694->5062|8723->5063|8757->5069|8821->5104|8851->5105|8882->5108|8995->5192|9025->5193|9067->5206|9335->5445|9365->5446|9402->5455|9454->5478|9484->5479|9523->5489|9632->5569|9662->5570|9702->5581|9762->5612|9792->5613|9831->5624|9861->5625|9898->5634|9949->5656|9979->5657|10018->5667|10084->5704|10114->5705|10154->5716|10213->5746|10243->5747|10282->5758|10312->5759|10348->5767|10377->5768|10414->5777|10444->5778|10476->5782|10505->5783|10539->5789
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|44->13|44->13|44->13|53->22|53->22|53->22|53->22|53->22|53->22|54->23|54->23|54->23|55->24|55->24|55->24|55->24|55->24|55->24|55->24|56->25|57->26|66->35|66->35|66->35|79->48|79->48|79->48|92->61|92->61|92->61|92->61|92->61|92->61|93->62|93->62|93->62|94->63|94->63|94->63|94->63|94->63|94->63|94->63|95->64|96->65|107->76|107->76|107->76|107->76|107->76|107->76|108->77|108->77|108->77|109->78|109->78|109->78|109->78|109->78|109->78|109->78|110->79|111->80|120->89|120->89|120->89|132->101|132->101|132->101|150->119|155->124|157->126|157->126|157->126|159->128|159->128|160->129|160->129|160->129|160->129|161->130|161->130|161->130|162->131|162->131|162->131|162->131|162->131|162->131|162->131|163->132|164->133|165->134|165->134|167->136|167->136|168->137|169->138|169->138|172->141|172->141|172->141|173->142|175->144|175->144|176->145|183->152|183->152|184->153|184->153|184->153|185->154|185->154|185->154|186->155|187->156|187->156|188->157|188->157|189->158|189->158|189->158|190->159|190->159|190->159|191->160|192->161|192->161|193->162|193->162|194->163|194->163|195->164|195->164|196->165|196->165|200->169
                  -- GENERATED --
              */
          