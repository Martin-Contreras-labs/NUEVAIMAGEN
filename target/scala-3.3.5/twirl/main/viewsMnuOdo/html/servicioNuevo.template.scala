
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

object servicioNuevo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.ClaseServicio],List[tables.UnidadServicio],List[tables.Moneda],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listClases: List[tables.ClaseServicio], listUnidades: List[tables.UnidadServicio], listMonedas: List[tables.Moneda]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "AGREGAR/CREAR NUEVO SERVICIO", "")),format.raw/*9.67*/("""
		"""),format.raw/*10.3*/("""<form action="/servicioGraba/" method="POST">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<TR>
							<td  style="text-align:left;">FAMILIA</td>
							<td style="text-align:left">
								<select class="custom-select"
									name="id_claseServicio"
									required>
									<option value=""></option>
									"""),_display_(/*21.11*/for(lista <- listClases) yield /*21.35*/{_display_(Seq[Any](format.raw/*21.36*/("""
										"""),format.raw/*22.11*/("""<option value=""""),_display_(/*22.27*/lista/*22.32*/.getId()),format.raw/*22.40*/("""">"""),_display_(/*22.43*/lista/*22.48*/.getNombre()),format.raw/*22.60*/("""</option>
									""")))}),format.raw/*23.11*/("""
								"""),format.raw/*24.9*/("""</select>
							</td>
						</TR>
						<tr>
							<td style="text-align:left; vertical-align:top">CODIGO SERVICIO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="codigo"
									name="codigo"
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
									required
									onkeydown="return sinReservados(window.event)"
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
									"""),_display_(/*57.11*/for(lista <- listUnidades) yield /*57.37*/{_display_(Seq[Any](format.raw/*57.38*/("""
										"""),format.raw/*58.11*/("""<option value=""""),_display_(/*58.27*/lista/*58.32*/.id),format.raw/*58.35*/("""">"""),_display_(/*58.38*/lista/*58.43*/.nombre),format.raw/*58.50*/("""</option>
									""")))}),format.raw/*59.11*/("""
								"""),format.raw/*60.9*/("""</select>
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
									"""),_display_(/*72.11*/for(lista <- listMonedas) yield /*72.36*/{_display_(Seq[Any](format.raw/*72.37*/("""
										"""),format.raw/*73.11*/("""<option value=""""),_display_(/*73.27*/lista/*73.32*/.id),format.raw/*73.35*/("""">"""),_display_(/*73.38*/lista/*73.43*/.getNickName()),format.raw/*73.57*/("""</option>
									""")))}),format.raw/*74.11*/("""
								"""),format.raw/*75.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">PRECIO UNITARIO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="precio"
									id="precio"
									value="0"
									onfocus="value=value.replace(/,/g,'')" 
									onfocusout = "if(value.trim()=='') value=0; formateaPrecio($('#id_moneda').val());"
									onkeydown="return ingresoDouble(window.event)"
									onchange="if(value.trim()=='') value=0; formateaPrecio($('#id_moneda').val());">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">DESCRIPCION: </td>
							<td style="text-align:left; vertical-align:top">
								<textarea class="form-control" 
									name="descripcion" 
									autocomplete="off"></textarea>
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
""")))}),format.raw/*114.2*/("""




"""),format.raw/*119.1*/("""<script type="text/javascript">

	let mapMonDec = """),format.raw/*121.18*/("""{"""),format.raw/*121.19*/("""}"""),format.raw/*121.20*/(""";

	$(document).ready(function() """),format.raw/*123.31*/("""{"""),format.raw/*123.32*/("""
		
		"""),_display_(/*125.4*/for(lista <- listMonedas) yield /*125.29*/{_display_(Seq[Any](format.raw/*125.30*/("""
			"""),format.raw/*126.4*/("""mapMonDec['"""),_display_(/*126.16*/lista/*126.21*/.id),format.raw/*126.24*/("""'] = '"""),_display_(/*126.31*/lista/*126.36*/.numeroDecimales),format.raw/*126.52*/("""';
		""")))}),format.raw/*127.4*/("""
		"""),format.raw/*128.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*129.2*/("""}"""),format.raw/*129.3*/(""");
	
	const formateaPrecio = (id_moneda) => """),format.raw/*131.40*/("""{"""),format.raw/*131.41*/("""
		"""),format.raw/*132.3*/("""$('#precio').val(formatStandar($('#precio').val(),mapMonDec[id_moneda]));
	"""),format.raw/*133.2*/("""}"""),format.raw/*133.3*/("""
	
	"""),format.raw/*135.2*/("""const verificaCodigo = (codigo) => """),format.raw/*135.37*/("""{"""),format.raw/*135.38*/("""
		"""),format.raw/*136.3*/("""var formData = new FormData();
	  	formData.append('codigo',codigo);
        $.ajax("""),format.raw/*138.16*/("""{"""),format.raw/*138.17*/("""
            """),format.raw/*139.13*/("""url: "/verificaCodigoServicioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*146.36*/("""{"""),format.raw/*146.37*/("""
	     		"""),format.raw/*147.9*/("""if(respuesta=="existe")"""),format.raw/*147.32*/("""{"""),format.raw/*147.33*/("""
	     			"""),format.raw/*148.10*/("""alertify.alert("El código de servicio ya existe, intente con otro", function () """),format.raw/*148.90*/("""{"""),format.raw/*148.91*/("""
	     				"""),format.raw/*149.11*/("""$("#codigo").val("");
		     		"""),format.raw/*150.10*/("""}"""),format.raw/*150.11*/(""");
	     		"""),format.raw/*151.9*/("""}"""),format.raw/*151.10*/("""
	     		"""),format.raw/*152.9*/("""if(respuesta=="error")"""),format.raw/*152.31*/("""{"""),format.raw/*152.32*/("""
	     			"""),format.raw/*153.10*/("""alertify.alert(msgError, function () """),format.raw/*153.47*/("""{"""),format.raw/*153.48*/("""
		     			"""),format.raw/*154.11*/("""location.href = "/";
		     		"""),format.raw/*155.10*/("""}"""),format.raw/*155.11*/(""");
	     		"""),format.raw/*156.9*/("""}"""),format.raw/*156.10*/("""
	     	"""),format.raw/*157.8*/("""}"""),format.raw/*157.9*/("""
        """),format.raw/*158.9*/("""}"""),format.raw/*158.10*/(""");
	"""),format.raw/*159.2*/("""}"""),format.raw/*159.3*/("""
	
	

"""),format.raw/*163.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listClases:List[tables.ClaseServicio],listUnidades:List[tables.UnidadServicio],listMonedas:List[tables.Moneda]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listClases,listUnidades,listMonedas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.ClaseServicio],List[tables.UnidadServicio],List[tables.Moneda]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listClases,listUnidades,listMonedas) => apply(mapDiccionario,mapPermiso,userMnu,listClases,listUnidades,listMonedas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/servicioNuevo.scala.html
                  HASH: 68ab9d0e49f5c6ca84f720cde4cc7bbcf855008e
                  MATRIX: 1083->1|1390->215|1423->223|1439->231|1478->233|1506->236|1574->284|1602->286|1678->337|1761->400|1791->403|2279->864|2319->888|2358->889|2397->900|2440->916|2454->921|2483->929|2513->932|2527->937|2560->949|2611->969|2647->978|3805->2109|3847->2135|3886->2136|3925->2147|3968->2163|3982->2168|4006->2171|4036->2174|4050->2179|4078->2186|4129->2206|4165->2215|4530->2553|4571->2578|4610->2579|4649->2590|4692->2606|4706->2611|4730->2614|4760->2617|4774->2622|4809->2636|4860->2656|4896->2665|6338->4076|6371->4081|6450->4131|6480->4132|6510->4133|6572->4166|6602->4167|6636->4174|6678->4199|6718->4200|6750->4204|6790->4216|6805->4221|6830->4224|6865->4231|6880->4236|6918->4252|6955->4258|6986->4261|7080->4327|7109->4328|7182->4372|7212->4373|7243->4376|7346->4451|7375->4452|7407->4456|7471->4491|7501->4492|7532->4495|7645->4579|7675->4580|7717->4593|7985->4832|8015->4833|8052->4842|8104->4865|8134->4866|8173->4876|8282->4956|8312->4957|8352->4968|8412->4999|8442->5000|8481->5011|8511->5012|8548->5021|8599->5043|8629->5044|8668->5054|8734->5091|8764->5092|8804->5103|8863->5133|8893->5134|8932->5145|8962->5146|8998->5154|9027->5155|9064->5164|9094->5165|9126->5169|9155->5170|9189->5176
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|53->22|53->22|53->22|53->22|53->22|53->22|54->23|55->24|88->57|88->57|88->57|89->58|89->58|89->58|89->58|89->58|89->58|89->58|90->59|91->60|103->72|103->72|103->72|104->73|104->73|104->73|104->73|104->73|104->73|104->73|105->74|106->75|145->114|150->119|152->121|152->121|152->121|154->123|154->123|156->125|156->125|156->125|157->126|157->126|157->126|157->126|157->126|157->126|157->126|158->127|159->128|160->129|160->129|162->131|162->131|163->132|164->133|164->133|166->135|166->135|166->135|167->136|169->138|169->138|170->139|177->146|177->146|178->147|178->147|178->147|179->148|179->148|179->148|180->149|181->150|181->150|182->151|182->151|183->152|183->152|183->152|184->153|184->153|184->153|185->154|186->155|186->155|187->156|187->156|188->157|188->157|189->158|189->158|190->159|190->159|194->163
                  -- GENERATED --
              */
          