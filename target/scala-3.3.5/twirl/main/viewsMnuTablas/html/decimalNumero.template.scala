
package viewsMnuTablas.html

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

object decimalNumero extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Moneda],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listMonedas: List[tables.Moneda]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "NUMERO DE DECIMALES POR MONEDA (MODIFICAR)", "")),format.raw/*9.81*/("""
			"""),format.raw/*10.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-8 col-sm-4 col-md-4 col-lg-4">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH style="vertical-align: top;">MONEDA</TH>
								<TH>SIGLA</TH>
								<TH>NRO DECIMALES</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*21.9*/for(lista <- listMonedas) yield /*21.34*/{_display_(Seq[Any](format.raw/*21.35*/("""
								"""),format.raw/*22.9*/("""<TR>
									<td style="text-align:left;">"""),_display_(/*23.40*/lista/*23.45*/.getNombre()),format.raw/*23.57*/("""</td>
									<td style="text-align:center;">"""),_display_(/*24.42*/lista/*24.47*/.getNickName()),format.raw/*24.61*/("""</td>
									<td style="text-align:left;">
										<select class="custom-select"
											onchange="modificadecimal(value,'"""),_display_(/*27.46*/lista/*27.51*/.getId()),format.raw/*27.59*/("""')">
											<option value="""),_display_(/*28.27*/lista/*28.32*/.numeroDecimales),format.raw/*28.48*/(""">"""),_display_(/*28.50*/lista/*28.55*/.numeroDecimales),format.raw/*28.71*/("""</option>
						        			<option value=0>0</option>
						        			<option value=2>2</option>
						        			<option value=4>4</option>
						        			<option value=6>6</option>
										</select>
									</td>
								</TR>
				 			""")))}),format.raw/*36.10*/("""
						"""),format.raw/*37.7*/("""</tbody>
					</table>
				
				
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
				</div>
			</div>
	</div>
""")))}),format.raw/*51.2*/("""




"""),format.raw/*56.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*58.31*/("""{"""),format.raw/*58.32*/("""
		  """),format.raw/*59.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*60.2*/("""}"""),format.raw/*60.3*/(""");
	
	const modificadecimal = (numeroDecimales, id_moneda) => """),format.raw/*62.58*/("""{"""),format.raw/*62.59*/("""
		"""),format.raw/*63.3*/("""var formData = new FormData();
	  	formData.append('id_moneda',id_moneda);
	  	formData.append('numeroDecimales',numeroDecimales);
        $.ajax("""),format.raw/*66.16*/("""{"""),format.raw/*66.17*/("""
            """),format.raw/*67.13*/("""url: "/decimalModificaAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*74.36*/("""{"""),format.raw/*74.37*/("""
	     		"""),format.raw/*75.9*/("""if(respuesta=="error")"""),format.raw/*75.31*/("""{"""),format.raw/*75.32*/("""
	     			"""),format.raw/*76.10*/("""alertify.alert(msgError, function () """),format.raw/*76.47*/("""{"""),format.raw/*76.48*/("""
		     			"""),format.raw/*77.11*/("""location.href = "/";
		     		"""),format.raw/*78.10*/("""}"""),format.raw/*78.11*/(""");
	     		"""),format.raw/*79.9*/("""}"""),format.raw/*79.10*/("""
	     	"""),format.raw/*80.8*/("""}"""),format.raw/*80.9*/("""
        """),format.raw/*81.9*/("""}"""),format.raw/*81.10*/(""");
	"""),format.raw/*82.2*/("""}"""),format.raw/*82.3*/("""
"""),format.raw/*83.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listMonedas:List[tables.Moneda]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listMonedas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Moneda]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listMonedas) => apply(mapDiccionario,mapPermiso,userMnu,listMonedas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/decimalNumero.scala.html
                  HASH: 4c317a3b70c27c48718aae9b32059b1e3ae0c199
                  MATRIX: 1031->1|1255->132|1288->140|1304->148|1343->150|1371->153|1439->201|1467->203|1543->254|1640->331|1671->335|2125->763|2166->788|2205->789|2241->798|2312->842|2326->847|2359->859|2433->906|2447->911|2482->925|2639->1055|2653->1060|2682->1068|2740->1099|2754->1104|2791->1120|2820->1122|2834->1127|2871->1143|3146->1387|3180->1394|3566->1750|3598->1755|3689->1818|3718->1819|3750->1824|3843->1890|3871->1891|3961->1953|3990->1954|4020->1957|4194->2103|4223->2104|4264->2117|4524->2349|4553->2350|4589->2359|4639->2381|4668->2382|4706->2392|4771->2429|4800->2430|4839->2441|4897->2471|4926->2472|4964->2483|4993->2484|5028->2492|5056->2493|5092->2502|5121->2503|5152->2507|5180->2508|5208->2509
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|54->23|54->23|54->23|55->24|55->24|55->24|58->27|58->27|58->27|59->28|59->28|59->28|59->28|59->28|59->28|67->36|68->37|82->51|87->56|89->58|89->58|90->59|91->60|91->60|93->62|93->62|94->63|97->66|97->66|98->67|105->74|105->74|106->75|106->75|106->75|107->76|107->76|107->76|108->77|109->78|109->78|110->79|110->79|111->80|111->80|112->81|112->81|113->82|113->82|114->83
                  -- GENERATED --
              */
          