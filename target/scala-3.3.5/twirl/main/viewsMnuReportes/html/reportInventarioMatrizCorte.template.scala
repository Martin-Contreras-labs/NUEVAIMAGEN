
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

object reportInventarioMatrizCorte extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[tables.Sucursal],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaHoy: String, tipo: String, listSucursal: List[tables.Sucursal], aplicaPorSucursal: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "REPORTE INVENTARIO - " + tipo + " - (matriz)" , "SOLO EQUIPOS Y " + mapDiccionario.get("BODEGA") + "/PROYECTO CON STOCK")),format.raw/*9.154*/("""
		"""),format.raw/*10.3*/("""<form action="/reportInventarioMatriz/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
								<TR>
									<TH>FECHA DE CORTE:</TH>
									<td>
										<input type="hidden" name="tipo" value=""""),_display_(/*19.52*/tipo),format.raw/*19.56*/("""">
										<input type="date" class="form-control center"
											id="fechaCorte"
								  			name="fechaCorte"
								  			value=""""),_display_(/*23.22*/fechaHoy),format.raw/*23.30*/(""""
						        			required>
									</td>
								<TR>
								</TR>
									<TH>SUCURSAL:</TH>
									<td>
										<select class="custom-select" name="id_sucursal">
										"""),_display_(if( ! mapDiccionario.get("nEmpresa").equals("SM8 DE MEXICO") && ! aplicaPorSucursal.equals("1"))/*31.108*/{_display_(Seq[Any](format.raw/*31.109*/("""
											"""),format.raw/*32.12*/("""<option value="0">TODAS</option>
										""")))} else {null} ),format.raw/*33.12*/("""
										"""),_display_(/*34.12*/for(lista <- listSucursal) yield /*34.38*/{_display_(Seq[Any](format.raw/*34.39*/("""
											"""),format.raw/*35.12*/("""<option value=""""),_display_(/*35.28*/lista/*35.33*/.getId()),format.raw/*35.41*/("""">"""),_display_(/*35.44*/lista/*35.49*/.getNombre()),format.raw/*35.61*/("""</option>
										""")))}),format.raw/*36.12*/("""
										"""),format.raw/*37.11*/("""</select>
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
					"""),_display_(if(mapDiccionario.get("nEmpresa").equals("SM8 DE MEXICO"))/*48.65*/{_display_(Seq[Any](format.raw/*48.66*/("""
						"""),format.raw/*49.7*/("""<input type="submit" id="btnSubmit" value="Exportar a Excel" class="btn btn-success rounded-pill btn-block">
					""")))}else/*50.11*/{_display_(Seq[Any](format.raw/*50.12*/("""
						"""),format.raw/*51.7*/("""<input type="submit" id="btnSubmit" value='Generar Reporte'  class="btn btn-primary rounded-pill btn-block">
					""")))}),format.raw/*52.7*/("""
				"""),format.raw/*53.5*/("""</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*58.2*/("""


"""),format.raw/*61.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*62.31*/("""{"""),format.raw/*62.32*/("""
		  """),format.raw/*63.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*64.2*/("""}"""),format.raw/*64.3*/(""");
	

	
	const validarForm = () =>"""),format.raw/*68.27*/("""{"""),format.raw/*68.28*/("""
		"""),format.raw/*69.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*71.2*/("""}"""),format.raw/*71.3*/("""
	
"""),format.raw/*73.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaHoy:String,tipo:String,listSucursal:List[tables.Sucursal],aplicaPorSucursal:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaHoy,tipo,listSucursal,aplicaPorSucursal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[tables.Sucursal],String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaHoy,tipo,listSucursal,aplicaPorSucursal) => apply(mapDiccionario,mapPermiso,userMnu,fechaHoy,tipo,listSucursal,aplicaPorSucursal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportInventarioMatrizCorte.scala.html
                  HASH: 908cb53ff39790442ccee39ae6f8c868591ac77c
                  MATRIX: 1070->1|1356->194|1389->202|1405->210|1444->212|1473->216|1541->264|1569->266|1645->317|1816->467|1846->470|2318->915|2343->919|2509->1058|2538->1066|2846->1346|2886->1347|2926->1359|3014->1403|3053->1415|3095->1441|3134->1442|3174->1454|3217->1470|3231->1475|3260->1483|3290->1486|3304->1491|3337->1503|3389->1524|3428->1535|3739->1819|3778->1820|3812->1827|3950->1946|3989->1947|4023->1954|4168->2069|4200->2074|4267->2111|4297->2114|4387->2176|4416->2177|4448->2182|4541->2248|4569->2249|4631->2283|4660->2284|4690->2287|4773->2343|4801->2344|4831->2347
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|50->19|50->19|54->23|54->23|62->31|62->31|63->32|64->33|65->34|65->34|65->34|66->35|66->35|66->35|66->35|66->35|66->35|66->35|67->36|68->37|79->48|79->48|80->49|81->50|81->50|82->51|83->52|84->53|89->58|92->61|93->62|93->62|94->63|95->64|95->64|99->68|99->68|100->69|102->71|102->71|104->73
                  -- GENERATED --
              */
          