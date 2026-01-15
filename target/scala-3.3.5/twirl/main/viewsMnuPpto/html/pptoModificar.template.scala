
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

object pptoModificar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.Ppto,tables.BodegaEmpresa,List[tables.Grupo],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
presup: tables.Ppto, bodega: tables.BodegaEmpresa, listGrupo: List[tables.Grupo], numDecimales: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MANTENCION DE PRESUPUESTO : "+ bodega.nombre.toUpperCase(),"MODIFICAR LINEA DE PRESUPUESTO")),format.raw/*9.125*/("""
		"""),format.raw/*10.3*/("""<form action="/pptoUpdate/" method="POST">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align: left;">MES - AÑO: </td>
							<td style="text-align: left;">
								<input type="hidden" name="id" value=""""),_display_(/*17.48*/presup/*17.54*/.id),format.raw/*17.57*/("""">
								<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*18.62*/bodega/*18.68*/.id),format.raw/*18.71*/("""">
							  	<input type="month" name="anioMes" 
									value=""""),_display_(/*20.18*/presup/*20.24*/.anioMes),format.raw/*20.32*/(""""
									onblur="if(!limitaFecha(value,1800,3600)) value='"""),_display_(/*21.60*/presup/*21.66*/.anioMes),format.raw/*21.74*/("""'"
									required>
						    </td>
						</tr>
						<tr>
							<td style="text-align: left;">GRUPO - FAMILIA: </td>
							<td style="text-align: left;">
								<select class="custom-select"
									name="id_grupo">
									<option value=""""),_display_(/*30.26*/presup/*30.32*/.id_grupo),format.raw/*30.41*/("""" >"""),_display_(/*30.45*/presup/*30.51*/.nombreGrupo),format.raw/*30.63*/("""</option>
									"""),_display_(/*31.11*/for(lista <- listGrupo) yield /*31.34*/{_display_(Seq[Any](format.raw/*31.35*/("""
										"""),format.raw/*32.11*/("""<option value=""""),_display_(/*32.27*/lista/*32.32*/.getId()),format.raw/*32.40*/("""">"""),_display_(/*32.43*/lista/*32.48*/.getNombre()),format.raw/*32.60*/("""</option>
									""")))}),format.raw/*33.11*/("""
								"""),format.raw/*34.9*/("""</select>
						    </td>
						</tr>
						<tr>
							<td style="text-align: left;">APLICA COMO:</td>
							<td style="text-align: left;">
								<select class="custom-select"
									name="esVenta">
									<option value=""""),_display_(/*42.26*/presup/*42.32*/.esVenta),format.raw/*42.40*/("""" >"""),_display_(if(presup.esVenta==0)/*42.65*/{_display_(_display_(/*42.67*/mapDiccionario/*42.81*/.get("ARRIENDO")))}else/*42.102*/{_display_(Seq[Any](format.raw/*42.103*/("""VENTA""")))}),format.raw/*42.109*/("""</option>
									<option value="0">"""),_display_(/*43.29*/mapDiccionario/*43.43*/.get("ARRIENDO")),format.raw/*43.59*/("""</option>
									<option value="1">VENTA</option>
								</select>
						    </td>
						</tr>
						<tr>
							<td style="text-align: left;">MONTO (en """),_display_(/*49.49*/mapDiccionario/*49.63*/.get("PESOS")),format.raw/*49.76*/("""): </td>
							<td style="text-align: left;">
								<input type="text" class="form-control height23px width200px right"
									name="total"
									id="total"
									value=""""),_display_(/*54.18*/presup/*54.24*/.total),format.raw/*54.30*/(""""
									onfocus="value=value.replace(/,/g,'')" 
									onkeydown="return ingresoDouble(window.event)"
									onchange="if(value.trim()=='') value = 0; value = formatStandar(value,'"""),_display_(/*57.82*/numDecimales),format.raw/*57.94*/("""');">
						    </td>
						</tr>
						<tr>
							<td style="text-align: left;">CONCEPTO: </td>
							<td style="text-align: left;">
								<input type="text" class="form-control left"
									name="concepto"
									value=""""),_display_(/*65.18*/presup/*65.24*/.concepto),format.raw/*65.33*/(""""
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
						<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/pptoEditar/"""),_display_(/*79.135*/bodega/*79.141*/.getId()),format.raw/*79.149*/("""';">
					</div>
				</div>
			</div>
		</form>
	</div>
""")))}),format.raw/*85.2*/("""



"""),format.raw/*89.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*90.31*/("""{"""),format.raw/*90.32*/("""
		"""),format.raw/*91.3*/("""$("#total").val(formatStandar('"""),_display_(/*91.35*/presup/*91.41*/.total),format.raw/*91.47*/("""','"""),_display_(/*91.51*/numDecimales),format.raw/*91.63*/("""'));
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*93.2*/("""}"""),format.raw/*93.3*/(""");
	

</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,presup:tables.Ppto,bodega:tables.BodegaEmpresa,listGrupo:List[tables.Grupo],numDecimales:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,presup,bodega,listGrupo,numDecimales)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Ppto,tables.BodegaEmpresa,List[tables.Grupo],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,presup,bodega,listGrupo,numDecimales) => apply(mapDiccionario,mapPermiso,userMnu,presup,bodega,listGrupo,numDecimales)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPpto/pptoModificar.scala.html
                  HASH: 34ab8cd6092d47bac6c2d417edbcca8b7dc2a5a7
                  MATRIX: 1066->1|1358->200|1391->208|1407->216|1446->218|1474->221|1542->269|1570->271|1646->322|1788->443|1818->446|2220->821|2235->827|2259->830|2350->894|2365->900|2389->903|2482->969|2497->975|2526->983|2614->1044|2629->1050|2658->1058|2933->1306|2948->1312|2978->1321|3009->1325|3024->1331|3057->1343|3104->1363|3143->1386|3182->1387|3221->1398|3264->1414|3278->1419|3307->1427|3337->1430|3351->1435|3384->1447|3435->1467|3471->1476|3728->1706|3743->1712|3772->1720|3824->1745|3854->1747|3877->1761|3910->1782|3950->1783|3988->1789|4053->1827|4076->1841|4113->1857|4297->2014|4320->2028|4354->2041|4563->2223|4578->2229|4605->2235|4820->2423|4853->2435|5111->2666|5126->2672|5156->2681|5724->3221|5740->3227|5770->3235|5857->3292|5888->3296|5978->3358|6007->3359|6037->3362|6096->3394|6111->3400|6138->3406|6169->3410|6202->3422|6302->3495|6330->3496
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|48->17|48->17|48->17|49->18|49->18|49->18|51->20|51->20|51->20|52->21|52->21|52->21|61->30|61->30|61->30|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|63->32|63->32|63->32|63->32|64->33|65->34|73->42|73->42|73->42|73->42|73->42|73->42|73->42|73->42|73->42|74->43|74->43|74->43|80->49|80->49|80->49|85->54|85->54|85->54|88->57|88->57|96->65|96->65|96->65|110->79|110->79|110->79|116->85|120->89|121->90|121->90|122->91|122->91|122->91|122->91|122->91|122->91|124->93|124->93
                  -- GENERATED --
              */
          