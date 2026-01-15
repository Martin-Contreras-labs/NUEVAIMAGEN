
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

object pptoAdministrar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], numDecimales: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MANTENCION DE PRESUPUESTOS DE "+mapDiccionario.get("BODEGA")+"S/PROYECTOS","SELECCIONAR/MODIFICAR")),format.raw/*9.132*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>  
						    <TH style= "text-align: center;">SUCURSAL<BR></TH>
							<TH style= "text-align: center;">"""),_display_(/*16.42*/mapDiccionario/*16.56*/.get("BODEGA")),format.raw/*16.70*/("""/PROYECTO"<BR></TH>
							<TH style= "text-align: center;">NOMBRE<BR>CLIENTE o PROP</TH>
							<TH style= "text-align: center;">NOMBRE<br>PROYECTO</TH>
							<TH style= "text-align: center;">VENTA<br>(en """),_display_(/*19.55*/mapDiccionario/*19.69*/.get("PESOS")),format.raw/*19.82*/(""")</TH>
							<TH style= "text-align: center;">"""),_display_(/*20.42*/mapDiccionario/*20.56*/.get("ARRIENDO")),format.raw/*20.72*/("""<br>(en """),_display_(/*20.81*/mapDiccionario/*20.95*/.get("PESOS")),format.raw/*20.108*/(""")</TH>
							<TH style= "text-align: center;">TOTAL<br>(en """),_display_(/*21.55*/mapDiccionario/*21.69*/.get("PESOS")),format.raw/*21.82*/(""")</TH>
							<TH style= "text-align: center;" width="5%" >EDITAR<BR></TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*26.8*/for(lista1 <- lista) yield /*26.28*/{_display_(Seq[Any](format.raw/*26.29*/("""
							"""),format.raw/*27.8*/("""<TR>
								<td style= "text-align: left;">"""),_display_(/*28.41*/lista1/*28.47*/.get(7)),format.raw/*28.54*/("""</td>
								<td style= "text-align: left;">"""),_display_(/*29.41*/lista1/*29.47*/.get(1)),format.raw/*29.54*/("""</td>
								<td style= "text-align: left;">"""),_display_(/*30.41*/lista1/*30.47*/.get(2)),format.raw/*30.54*/("""</td>
								<td style= "text-align: left;">"""),_display_(/*31.41*/lista1/*31.47*/.get(3)),format.raw/*31.54*/("""</td>
								<td style= "text-align: right;">"""),_display_(/*32.42*/lista1/*32.48*/.get(4)),format.raw/*32.55*/("""</td>
								<td style= "text-align: right;">"""),_display_(/*33.42*/lista1/*33.48*/.get(5)),format.raw/*33.55*/("""</td>
								<td style= "text-align: right;">"""),_display_(/*34.42*/lista1/*34.48*/.get(6)),format.raw/*34.55*/("""</td>
								<td  style="text-align:center;">
									<a href="/pptoEditar/"""),_display_(/*36.32*/lista1/*36.38*/.get(0)),format.raw/*36.45*/("""">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*41.9*/("""
					"""),format.raw/*42.6*/("""</tbody>
					<tfoot>
			 			<TR>
							<TH style= "text-align: left;">TOTALES</TH>
							<TH style= "text-align: left;"></TH>
							<TH style= "text-align: left;"></TH>
							<TH style= "text-align: left;"></TH>
							<TH style= "text-align: right;">0</TH>
							<TH style= "text-align: right;">0</TH>
							<TH style= "text-align: right;">0</TH>
							<TH style= "text-align: center;"></TH>
						</TR>
		 			</tfoot>
				</table>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
""")))}),format.raw/*67.2*/("""




"""),format.raw/*72.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*73.31*/("""{"""),format.raw/*73.32*/("""
		  """),format.raw/*74.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*74.36*/("""{"""),format.raw/*74.37*/("""
		    	"""),format.raw/*75.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[1,"asc"]],
		    	"language": """),format.raw/*78.20*/("""{"""),format.raw/*78.21*/("""
		        	"""),format.raw/*79.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*80.11*/("""}"""),format.raw/*80.12*/("""
		  """),format.raw/*81.5*/("""}"""),format.raw/*81.6*/(""" """),format.raw/*81.7*/(""");

			let tabla = document.getElementById("tablaPrincipal");
	        let totVta = 0;
	        let totArr = 0;
	        let totTot = 0;
	        for (let i = 1; i < tabla.rows.length-1; i++) """),format.raw/*87.56*/("""{"""),format.raw/*87.57*/(""" 
				"""),format.raw/*88.5*/("""var aux = tabla.rows[i].cells[4].textContent;
				totVta = parseFloat(totVta) + parseFloat(aux.replace(/,/g,''));
				aux = tabla.rows[i].cells[5].textContent;
				totArr = parseFloat(totArr) + parseFloat(aux.replace(/,/g,''));
				aux = tabla.rows[i].cells[6].textContent;
				totTot = parseFloat(totTot) + parseFloat(aux.replace(/,/g,''));
			"""),format.raw/*94.4*/("""}"""),format.raw/*94.5*/("""
	        """),format.raw/*95.10*/("""tabla.rows[tabla.rows.length-1].cells[4].textContent = formatStandar(totVta,'"""),_display_(/*95.88*/numDecimales),format.raw/*95.100*/("""');
	        tabla.rows[tabla.rows.length-1].cells[5].textContent = formatStandar(totArr,'"""),_display_(/*96.88*/numDecimales),format.raw/*96.100*/("""');
	        tabla.rows[tabla.rows.length-1].cells[6].textContent = formatStandar(totTot,'"""),_display_(/*97.88*/numDecimales),format.raw/*97.100*/("""');

		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*100.2*/("""}"""),format.raw/*100.3*/(""");
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],numDecimales:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,numDecimales)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,numDecimales) => apply(mapDiccionario,mapPermiso,userMnu,lista,numDecimales)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPpto/pptoAdministrar.scala.html
                  HASH: ae08062cec78975d3c98579d47079610645766a0
                  MATRIX: 1035->1|1272->145|1305->153|1321->161|1360->163|1388->166|1456->214|1484->216|1560->267|1709->395|1739->398|2140->772|2163->786|2198->800|2433->1008|2456->1022|2490->1035|2565->1083|2588->1097|2625->1113|2661->1122|2684->1136|2719->1149|2807->1210|2830->1224|2864->1237|3011->1358|3047->1378|3086->1379|3121->1387|3193->1432|3208->1438|3236->1445|3309->1491|3324->1497|3352->1504|3425->1550|3440->1556|3468->1563|3541->1609|3556->1615|3584->1622|3658->1669|3673->1675|3701->1682|3775->1729|3790->1735|3818->1742|3892->1789|3907->1795|3935->1802|4040->1880|4055->1886|4083->1893|4222->2002|4255->2008|5056->2779|5088->2784|5178->2846|5207->2847|5239->2852|5298->2883|5327->2884|5362->2892|5530->3032|5559->3033|5599->3045|5705->3123|5734->3124|5766->3129|5794->3130|5822->3131|6042->3323|6071->3324|6104->3330|6476->3675|6504->3676|6542->3686|6647->3764|6681->3776|6799->3867|6833->3879|6951->3970|6985->3982|7088->4057|7117->4058
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|50->19|50->19|50->19|51->20|51->20|51->20|51->20|51->20|51->20|52->21|52->21|52->21|57->26|57->26|57->26|58->27|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|65->34|65->34|65->34|67->36|67->36|67->36|72->41|73->42|98->67|103->72|104->73|104->73|105->74|105->74|105->74|106->75|109->78|109->78|110->79|111->80|111->80|112->81|112->81|112->81|118->87|118->87|119->88|125->94|125->94|126->95|126->95|126->95|127->96|127->96|128->97|128->97|131->100|131->100
                  -- GENERATED --
              */
          