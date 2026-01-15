
package viewsMnuCompras.html

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

object compraImportIconstruye0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, listIdOrgc: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "PROCESO IMPORTAR COMPRAS (DESDE ICONSTRUYE)","SELECCIONAR CENTRO DE GESTION")),format.raw/*8.110*/("""
		"""),format.raw/*9.3*/("""<form action="/compraImportIconstruye1/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">PERIODO A CONSIDERAR<br>(no mayor a 31 días)</TH>
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
							<TR>
								<TH>CENTRO GESTION</TH>
								<td>
									 <select class="custom-select" style="min-width:120px" id="pais" name="idOrgc" required>
									  	"""),_display_(/*42.14*/for(lista <- listIdOrgc) yield /*42.38*/{_display_(Seq[Any](format.raw/*42.39*/("""
									  		"""),format.raw/*43.14*/("""<option value=""""),_display_(/*43.30*/lista/*43.35*/.get(0)),format.raw/*43.42*/("""">"""),_display_(/*43.45*/lista/*43.50*/.get(1)),format.raw/*43.57*/(""" """),format.raw/*43.58*/("""- """),_display_(/*43.61*/lista/*43.66*/.get(2)),format.raw/*43.73*/("""</option>
									  	""")))}),format.raw/*44.14*/("""
									  """),format.raw/*45.12*/("""</select>
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
					<input type="submit" value='Obtener OC' class="btn btn-primary btn-sm rounded-pill btn-block">
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*62.2*/("""


"""),format.raw/*65.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*66.31*/("""{"""),format.raw/*66.32*/("""
		  """),format.raw/*67.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*68.2*/("""}"""),format.raw/*68.3*/(""");
	
	const validarForm = () =>"""),format.raw/*70.27*/("""{"""),format.raw/*70.28*/("""
		"""),format.raw/*71.3*/("""let flag = true;
		var desde = Date.parse(document.getElementById('fechaDesde').value);
		var hasta = Date.parse(document.getElementById('fechaHasta').value);
		var diffDias = Math.floor((hasta - desde)/(1000 * 60 * 60 * 24))+1;
		
		if(desde > hasta)"""),format.raw/*76.20*/("""{"""),format.raw/*76.21*/("""
			"""),format.raw/*77.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*78.87*/("""{"""),format.raw/*78.88*/("""
				"""),format.raw/*79.5*/("""return(flag);
     		"""),format.raw/*80.8*/("""}"""),format.raw/*80.9*/(""");
		"""),format.raw/*81.3*/("""}"""),format.raw/*81.4*/("""
		
		"""),format.raw/*83.3*/("""if(diffDias > 31)"""),format.raw/*83.20*/("""{"""),format.raw/*83.21*/("""
			"""),format.raw/*84.4*/("""flag = false;
			alertify.alert('EL PERIODO NO PUEDE SER MAYOR A 31 DIAS', function () """),format.raw/*85.74*/("""{"""),format.raw/*85.75*/("""
				"""),format.raw/*86.5*/("""return(flag);
     		"""),format.raw/*87.8*/("""}"""),format.raw/*87.9*/(""");
		"""),format.raw/*88.3*/("""}"""),format.raw/*88.4*/("""
		
		"""),format.raw/*90.3*/("""return(flag);
	"""),format.raw/*91.2*/("""}"""),format.raw/*91.3*/("""
	
	
	
"""),format.raw/*95.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String,listIdOrgc:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,listIdOrgc)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,listIdOrgc) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,listIdOrgc)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCompras/compraImportIconstruye0.scala.html
                  HASH: 1240397674575f005245e4a7ab365a51c6f038a9
                  MATRIX: 1055->1|1317->170|1349->177|1365->185|1404->187|1433->191|1501->239|1529->241|1605->292|1732->398|1761->401|2408->1021|2439->1031|2708->1273|2739->1283|2989->1506|3029->1530|3068->1531|3110->1545|3153->1561|3167->1566|3195->1573|3225->1576|3239->1581|3267->1588|3296->1589|3326->1592|3340->1597|3368->1604|3422->1627|3462->1639|3851->1998|3881->2001|3971->2063|4000->2064|4032->2069|4125->2135|4153->2136|4212->2167|4241->2168|4271->2171|4550->2422|4579->2423|4610->2427|4738->2527|4767->2528|4799->2533|4847->2554|4875->2555|4907->2560|4935->2561|4968->2567|5013->2584|5042->2585|5073->2589|5188->2676|5217->2677|5249->2682|5297->2703|5325->2704|5357->2709|5385->2710|5418->2716|5460->2731|5488->2732|5522->2739
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|55->24|55->24|65->34|65->34|73->42|73->42|73->42|74->43|74->43|74->43|74->43|74->43|74->43|74->43|74->43|74->43|74->43|74->43|75->44|76->45|93->62|96->65|97->66|97->66|98->67|99->68|99->68|101->70|101->70|102->71|107->76|107->76|108->77|109->78|109->78|110->79|111->80|111->80|112->81|112->81|114->83|114->83|114->83|115->84|116->85|116->85|117->86|118->87|118->87|119->88|119->88|121->90|122->91|122->91|126->95
                  -- GENERATED --
              */
          