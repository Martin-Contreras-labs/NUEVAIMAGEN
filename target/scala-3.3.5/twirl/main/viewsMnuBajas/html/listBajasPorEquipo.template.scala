
package viewsMnuBajas.html

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

object listBajasPorEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listPorEquipos: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "LISTADO DE BAJAS POR EQUIPO","")),format.raw/*9.65*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-5 col-sm-4 col-md-3 col-lg-2">
				<form action="/listBajasPorEquipoExcel/" method="POST" onsubmit="return validarForm();">
					<input type="submit" id="btnSubmit" class="btn btn-info btn-sm rounded-pill btn-block" value="Excel">
				</form>
			</div>
		</div>
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>GRUPO</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>UN</TH>
							<TH>CANT</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*31.8*/for(lista1 <- listPorEquipos) yield /*31.37*/{_display_(Seq[Any](format.raw/*31.38*/("""
							"""),format.raw/*32.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*33.40*/lista1/*33.46*/.get(1)),format.raw/*33.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*34.42*/lista1/*34.48*/.get(2)),format.raw/*34.55*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*35.40*/lista1/*35.46*/.get(3)),format.raw/*35.53*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*36.42*/lista1/*36.48*/.get(4)),format.raw/*36.55*/("""</td>
								<td  style="text-align:right;">"""),_display_(/*37.41*/lista1/*37.47*/.get(5)),format.raw/*37.54*/("""</td>
								<td  style="text-align:center;">
									<a href="/bajaEquipoPrint/"""),_display_(/*39.37*/lista1/*39.43*/.get(0)),format.raw/*39.50*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
								
							</TR>
			 			""")))}),format.raw/*45.9*/("""
					"""),format.raw/*46.6*/("""</tbody>
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


""")))}),format.raw/*60.2*/("""


"""),format.raw/*63.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*64.31*/("""{"""),format.raw/*64.32*/("""
		  """),format.raw/*65.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*65.36*/("""{"""),format.raw/*65.37*/("""
		    	"""),format.raw/*66.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*69.20*/("""{"""),format.raw/*69.21*/("""
		        	"""),format.raw/*70.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*71.11*/("""}"""),format.raw/*71.12*/("""
		  """),format.raw/*72.5*/("""}"""),format.raw/*72.6*/(""" """),format.raw/*72.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*74.2*/("""}"""),format.raw/*74.3*/(""");
	
	const validarForm = () =>"""),format.raw/*76.27*/("""{"""),format.raw/*76.28*/("""
		"""),format.raw/*77.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*79.2*/("""}"""),format.raw/*79.3*/("""
	 

		
		
	
	
	
"""),format.raw/*87.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listPorEquipos:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listPorEquipos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listPorEquipos) => apply(mapDiccionario,mapPermiso,userMnu,listPorEquipos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBajas/listBajasPorEquipo.scala.html
                  HASH: 0dd3007b4aa0b5408a23e91b2ae1871bbedf5c6b
                  MATRIX: 1034->1|1260->134|1293->142|1309->150|1348->152|1377->156|1445->204|1473->206|1549->257|1630->318|1660->321|2464->1099|2509->1128|2548->1129|2583->1137|2654->1181|2669->1187|2697->1194|2771->1241|2786->1247|2814->1254|2886->1299|2901->1305|2929->1312|3003->1359|3018->1365|3046->1372|3119->1418|3134->1424|3162->1431|3272->1514|3287->1520|3315->1527|3468->1650|3501->1656|3869->1994|3899->1997|3989->2059|4018->2060|4050->2065|4109->2096|4138->2097|4173->2105|4360->2264|4389->2265|4429->2277|4535->2355|4564->2356|4596->2361|4624->2362|4652->2363|4752->2436|4780->2437|4839->2468|4868->2469|4898->2472|4981->2528|5009->2529|5053->2546
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|62->31|62->31|62->31|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|68->37|68->37|68->37|70->39|70->39|70->39|76->45|77->46|91->60|94->63|95->64|95->64|96->65|96->65|96->65|97->66|100->69|100->69|101->70|102->71|102->71|103->72|103->72|103->72|105->74|105->74|107->76|107->76|108->77|110->79|110->79|118->87
                  -- GENERATED --
              */
          