
package viewsMnuPpto.html

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

object pptoAgregar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.Grupo],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listGrupo: List[tables.Grupo], numDecimales: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MANTENCION DE PRESUPUESTO : "+ bodega.nombre.toUpperCase(),"AGREGAR AL PRESUPUESTO")),format.raw/*9.117*/("""
		"""),format.raw/*10.3*/("""<form action="/pptoGrabar/" method="POST">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align: left;">MES - AÑO: </td>
							<td style="text-align: left;">
								<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*17.62*/bodega/*17.68*/.id),format.raw/*17.71*/("""">
							  	<input type="month" name="anioMes" 
									onblur="if(!limitaFecha(value,1800,3600)) value=''"
									required>
						    </td>
						</tr>
						<tr>
							<td style="text-align: left;">GRUPO - FAMILIA: </td>
							<td style="text-align: left;">
								<select class="custom-select"
									name="id_grupo">
									<option value="0">No Aplica</option>
									"""),_display_(/*29.11*/for(lista <- listGrupo) yield /*29.34*/{_display_(Seq[Any](format.raw/*29.35*/("""
										"""),format.raw/*30.11*/("""<option value=""""),_display_(/*30.27*/lista/*30.32*/.getId()),format.raw/*30.40*/("""">"""),_display_(/*30.43*/lista/*30.48*/.getNombre()),format.raw/*30.60*/("""</option>
									""")))}),format.raw/*31.11*/("""
								"""),format.raw/*32.9*/("""</select>
						    </td>
						</tr>
						<tr>
							<td style="text-align: left;">APLICA COMO:</td>
							<td style="text-align: left;">
								<select class="custom-select"
									name="esVenta">
									<option value="0">"""),_display_(/*40.29*/mapDiccionario/*40.43*/.get("ARRIENDO")),format.raw/*40.59*/("""</option>
									<option value="1">VENTA</option>
								</select>
						    </td>
						</tr>
						<tr>
							<td style="text-align: left;">MONTO (en """),_display_(/*46.49*/mapDiccionario/*46.63*/.get("PESOS")),format.raw/*46.76*/("""): </td>
							<td style="text-align: left;">
								<input type="text" class="form-control height23px width200px right"
									name="total"
									value="0"
									onfocus="value=value.replace(/,/g,'')" 
									onkeydown="return ingresoDouble(window.event)"
									onchange="if(value.trim()=='') value = 0; value = formatStandar(value,'"""),_display_(/*53.82*/numDecimales),format.raw/*53.94*/("""');">
						    </td>
						</tr>
						<tr>
							<td style="text-align: left;">CONCEPTO: </td>
							<td style="text-align: left;">
								<input type="text" class="form-control left"
									name="concepto"
									required
									autocomplete="off">
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
						<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/pptoEditar/"""),_display_(/*74.135*/bodega/*74.141*/.getId()),format.raw/*74.149*/("""';">
					</div>
				</div>
			</div>
		</form>
	</div>
""")))}),format.raw/*80.2*/("""



"""),format.raw/*84.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*85.31*/("""{"""),format.raw/*85.32*/("""
		"""),format.raw/*86.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/(""");
	

</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listGrupo:List[tables.Grupo],numDecimales:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listGrupo,numDecimales)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.Grupo],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listGrupo,numDecimales) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listGrupo,numDecimales)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPpto/pptoAgregar.scala.html
                  HASH: 95e7efd4891a6a6c087f219dac9d836d90864a2e
                  MATRIX: 1052->1|1323->179|1356->187|1372->195|1411->197|1439->200|1507->248|1535->250|1611->301|1745->414|1775->417|2191->806|2206->812|2230->815|2643->1201|2682->1224|2721->1225|2760->1236|2803->1252|2817->1257|2846->1265|2876->1268|2890->1273|2923->1285|2974->1305|3010->1314|3270->1547|3293->1561|3330->1577|3514->1734|3537->1748|3571->1761|3948->2111|3981->2123|4761->2875|4777->2881|4807->2889|4894->2946|4925->2950|5015->3012|5044->3013|5074->3016|5167->3082|5195->3083
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|48->17|48->17|48->17|60->29|60->29|60->29|61->30|61->30|61->30|61->30|61->30|61->30|61->30|62->31|63->32|71->40|71->40|71->40|77->46|77->46|77->46|84->53|84->53|105->74|105->74|105->74|111->80|115->84|116->85|116->85|117->86|118->87|118->87
                  -- GENERATED --
              */
          