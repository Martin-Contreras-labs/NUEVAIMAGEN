
package viewsMnuMantencion.html

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

object mantCantOperacional1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, listado: List[List[String]], desdeAAMMDD: String, hastaAAMMDD: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""

"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),format.raw/*8.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>En proceso.....<div id="msgEspera"></div></h1></div>
	
	<form id="excel" class="formulario" method="post" action="/routes3/mantCantOperacional1Excel/">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*11.50*/desdeAAMMDD),format.raw/*11.61*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*12.50*/hastaAAMMDD),format.raw/*12.61*/("""">
		
	</form>
	
	
	<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*18.4*/barraTitulo(mapDiccionario, "CANTIDADES OPERACIONAL Y CORRECTIVO","DESDE: "+utilities.Fechas.DDMMAA(desdeAAMMDD)+" HASTA: "+utilities.Fechas.DDMMAA(hastaAAMMDD))),format.raw/*18.165*/("""
		"""),format.raw/*19.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
				<div align="center">
					<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
						Exportar a Excel
					</button>
				</div>
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH></TH>
							<TH>CONSUMO<BR>REPORT OPER</TH>
							<TH>HORAS<BR>OPERADOR</TH>
							<TH></TH>
							<TH>CONSUMO<BR>REPORT MEC</TH>
							<TH>HORAS<BR>MECANICO</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*42.8*/for(lista1 <- listado) yield /*42.30*/{_display_(Seq[Any](format.raw/*42.31*/("""
							"""),format.raw/*43.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*44.40*/lista1/*44.46*/.get(1)),format.raw/*44.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*45.40*/lista1/*45.46*/.get(2)),format.raw/*45.53*/("""</td>
								<td></td>
								<td  style="text-align:right;">"""),_display_(/*47.41*/lista1/*47.47*/.get(3)),format.raw/*47.54*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*48.41*/lista1/*48.47*/.get(4)),format.raw/*48.54*/("""</td>
								<td></td>
								<td  style="text-align:right;">"""),_display_(/*50.41*/lista1/*50.47*/.get(5)),format.raw/*50.54*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*51.41*/lista1/*51.47*/.get(6)),format.raw/*51.54*/("""</td>
							</TR>
			 			""")))}),format.raw/*53.9*/("""
					"""),format.raw/*54.6*/("""</tbody>
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

""")))}),format.raw/*67.2*/("""




"""),format.raw/*72.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*73.31*/("""{"""),format.raw/*73.32*/("""
		  """),format.raw/*74.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*74.36*/("""{"""),format.raw/*74.37*/("""
		    	"""),format.raw/*75.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "asc" ],[ 0, "asc" ]],
		    	"language": """),format.raw/*78.20*/("""{"""),format.raw/*78.21*/("""
		        	"""),format.raw/*79.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*80.11*/("""}"""),format.raw/*80.12*/("""
		  """),format.raw/*81.5*/("""}"""),format.raw/*81.6*/(""" """),format.raw/*81.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*83.2*/("""}"""),format.raw/*83.3*/(""");

	
	
	
</script>


		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listado:List[List[String]],desdeAAMMDD:String,hastaAAMMDD:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD) => apply(mapDiccionario,mapPermiso,userMnu,listado,desdeAAMMDD,hastaAAMMDD)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantCantOperacional1.scala.html
                  HASH: 941b7d2da7569dec7ab852bb2324e2ba9b6e0c19
                  MATRIX: 1055->1|1316->169|1344->172|1360->180|1399->182|1428->186|1496->234|1526->238|1839->524|1871->535|1950->587|1982->598|2079->669|2262->830|2292->833|3054->1569|3092->1591|3131->1592|3166->1600|3237->1644|3252->1650|3280->1657|3352->1702|3367->1708|3395->1715|3486->1779|3501->1785|3529->1792|3602->1838|3617->1844|3645->1851|3736->1915|3751->1921|3779->1928|3852->1974|3867->1980|3895->1987|3952->2014|3985->2020|4352->2357|4384->2362|4474->2424|4503->2425|4535->2430|4594->2461|4623->2462|4658->2470|4842->2626|4871->2627|4911->2639|5017->2717|5046->2718|5078->2723|5106->2724|5134->2725|5234->2798|5262->2799
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|39->8|42->11|42->11|43->12|43->12|49->18|49->18|50->19|73->42|73->42|73->42|74->43|75->44|75->44|75->44|76->45|76->45|76->45|78->47|78->47|78->47|79->48|79->48|79->48|81->50|81->50|81->50|82->51|82->51|82->51|84->53|85->54|98->67|103->72|104->73|104->73|105->74|105->74|105->74|106->75|109->78|109->78|110->79|111->80|111->80|112->81|112->81|112->81|114->83|114->83
                  -- GENERATED --
              */
          