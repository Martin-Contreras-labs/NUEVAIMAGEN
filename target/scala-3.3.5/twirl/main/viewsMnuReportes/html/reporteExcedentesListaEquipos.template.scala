
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

object reporteExcedentesListaEquipos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "SELECCION DE EQUIPOS","REPORTE DE EXCEDENTES")),format.raw/*10.79*/("""
		"""),format.raw/*11.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
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
						        <TH style= "text-align: center;vertical-align: top;">CODIGO</TH>
						        <TH style= "text-align: center;vertical-align: top;">EQUIPO</TH>
						        <TH style= "text-align: center;vertical-align: top;">EXCEDENTES</TH>
								<TH width="5%" >SELECT</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*41.9*/for(lista1 <- lista) yield /*41.29*/{_display_(Seq[Any](format.raw/*41.30*/("""
								"""),format.raw/*42.9*/("""<TR>
									<td style="text-align:center;vertical-align:middle;">"""),_display_(/*43.64*/lista1/*43.70*/.get(1)),format.raw/*43.77*/("""</td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*44.62*/lista1/*44.68*/.get(2)),format.raw/*44.75*/("""</td>
									<td style="text-align:center;vertical-align:middle;">"""),_display_(/*45.64*/lista1/*45.70*/.get(3)),format.raw/*45.77*/("""</td>
									<td style="text-align:center;vertical-align:middle;">
										<form id="form0_"""),_display_(/*47.28*/lista1/*47.34*/.get(0)),format.raw/*47.41*/("""" class="formulario" method="post" action="/routes2/reporteExcedentesDetalleEquipo/">
											<input type="hidden" name="id_equipo" value=""""),_display_(/*48.58*/lista1/*48.64*/.get(0)),format.raw/*48.71*/("""">
											<a href="#" onclick="document.getElementById('form0_"""),_display_(/*49.65*/lista1/*49.71*/.get(0)),format.raw/*49.78*/("""').submit()">
												<kbd style="background-color: #73C6B6">select</kbd>
											</a>
										</form>
									</td>
								</TR>
				 			""")))}),format.raw/*55.10*/("""
						"""),format.raw/*56.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


""")))}),format.raw/*64.2*/("""


"""),format.raw/*67.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*69.31*/("""{"""),format.raw/*69.32*/("""

		 """),format.raw/*71.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*71.35*/("""{"""),format.raw/*71.36*/("""
		    	"""),format.raw/*72.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*77.20*/("""{"""),format.raw/*77.21*/("""
		        	"""),format.raw/*78.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*79.11*/("""}"""),format.raw/*79.12*/("""
		  """),format.raw/*80.5*/("""}"""),format.raw/*80.6*/(""" """),format.raw/*80.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*83.2*/("""}"""),format.raw/*83.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista) => apply(mapDiccionario,mapPermiso,userMnu,lista)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteExcedentesListaEquipos.scala.html
                  HASH: 519bd848a457684c4f5e144422f3cc3e32e4cfff
                  MATRIX: 1048->1|1265->125|1297->132|1313->140|1352->142|1381->146|1449->194|1481->200|1558->251|1654->326|1684->329|2869->1488|2905->1508|2944->1509|2980->1518|3075->1586|3090->1592|3118->1599|3212->1666|3227->1672|3255->1679|3351->1748|3366->1754|3394->1761|3517->1857|3532->1863|3560->1870|3730->2013|3745->2019|3773->2026|3867->2093|3882->2099|3910->2106|4091->2256|4125->2263|4219->2327|4249->2330|4357->2410|4386->2411|4418->2416|4477->2447|4506->2448|4541->2456|4708->2595|4737->2596|4777->2608|4883->2686|4912->2687|4944->2692|4972->2693|5000->2694|5100->2767|5128->2768
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|40->9|41->10|41->10|42->11|72->41|72->41|72->41|73->42|74->43|74->43|74->43|75->44|75->44|75->44|76->45|76->45|76->45|78->47|78->47|78->47|79->48|79->48|79->48|80->49|80->49|80->49|86->55|87->56|95->64|98->67|100->69|100->69|102->71|102->71|102->71|103->72|108->77|108->77|109->78|110->79|110->79|111->80|111->80|111->80|114->83|114->83
                  -- GENERATED --
              */
          