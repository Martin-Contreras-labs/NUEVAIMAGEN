
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

object reporteGerencialGrupo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Grupo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[tables.Grupo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "REPORTE GERENCIAL - SITUACIÓN GLOBAL","(selección de grupo)")),format.raw/*8.94*/("""
		"""),format.raw/*9.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-8 col-sm-4 col-md-4 col-lg-4">
				<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
							  		</div>
							  		<input type="text" class="form-control left"
										id="searchTermtablaPrincipal"
										onkeyup="doSearch2('tablaPrincipal');">
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
					        <TR> 
						        <TH style= "text-align: center;vertical-align: top;">GRUPO</TH>
								<TH style= "text-align: center;vertical-align: top;width:4%">SELECT</TH>
							</TR>
						</thead>
						<tbody>
						"""),_display_(/*37.8*/for(lista1 <- lista) yield /*37.28*/{_display_(Seq[Any](format.raw/*37.29*/("""
							"""),format.raw/*38.8*/("""<TR>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*39.61*/lista1/*39.67*/.getNombre),format.raw/*39.77*/("""</td>
								<td style="text-align:left;vertical-align:middle;">
									<form id="form_"""),_display_(/*41.26*/lista1/*41.32*/.getId),format.raw/*41.38*/("""" class="formulario" method="post" action="/reporteGerencialGrupoDetalle/">
										<input type="hidden" name="id_grupo" value=""""),_display_(/*42.56*/lista1/*42.62*/.getId),format.raw/*42.68*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*43.63*/lista1/*43.69*/.getId),format.raw/*43.75*/("""').submit()">
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*49.9*/("""
						"""),format.raw/*50.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	

""")))}),format.raw/*58.2*/("""


"""),format.raw/*61.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*63.31*/("""{"""),format.raw/*63.32*/("""

		 """),format.raw/*65.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*65.35*/("""{"""),format.raw/*65.36*/("""
		    	"""),format.raw/*66.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*71.20*/("""{"""),format.raw/*71.21*/("""
		        	"""),format.raw/*72.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*73.11*/("""}"""),format.raw/*73.12*/("""
		  """),format.raw/*74.5*/("""}"""),format.raw/*74.6*/(""" """),format.raw/*74.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*77.2*/("""}"""),format.raw/*77.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[tables.Grupo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Grupo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista) => apply(mapDiccionario,mapPermiso,userMnu,lista)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteGerencialGrupo.scala.html
                  HASH: 048e7d30086405ee607d7aa43723e788521f5621
                  MATRIX: 1040->1|1257->125|1289->132|1305->140|1344->142|1373->146|1441->194|1469->196|1545->247|1655->337|1684->340|2749->1379|2785->1399|2824->1400|2859->1408|2951->1473|2966->1479|2997->1489|3115->1580|3130->1586|3157->1592|3315->1723|3330->1729|3357->1735|3449->1800|3464->1806|3491->1812|3665->1956|3699->1963|3794->2028|3824->2031|3932->2111|3961->2112|3993->2117|4052->2148|4081->2149|4116->2157|4283->2296|4312->2297|4352->2309|4458->2387|4487->2388|4519->2393|4547->2394|4575->2395|4675->2468|4703->2469
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|68->37|68->37|68->37|69->38|70->39|70->39|70->39|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|74->43|80->49|81->50|89->58|92->61|94->63|94->63|96->65|96->65|96->65|97->66|102->71|102->71|103->72|104->73|104->73|105->74|105->74|105->74|108->77|108->77
                  -- GENERATED --
              */
          