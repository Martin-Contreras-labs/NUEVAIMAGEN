
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

object reporteMovimientosListaProyectos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template12[Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,Double,Double,Double,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tabla: String, desdeDDMMAA: String, hastaDDMMAA: String, id_proyecto: String,
uf: Double, usd: Double, eur: Double, desdeAAMMDD: String, hastaAAMMDD: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(if(id_proyecto.equals("0"))/*14.31*/{_display_(Seq[Any](format.raw/*14.32*/("""
			"""),_display_(/*15.5*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","REPORTE DETALLE DE MOVIMIENTOS (VALORIZADO)")),format.raw/*15.136*/("""
		""")))}else/*16.8*/{_display_(Seq[Any](format.raw/*16.9*/("""
			"""),_display_(/*17.5*/barraTitulo(mapDiccionario, "SELECCION DE PROYECTO","DETALLE DE MOVIMIENTOS POR PROYECTO (VALORIZADO)")),format.raw/*17.108*/("""
		""")))}),format.raw/*18.4*/("""
		
		
		"""),format.raw/*21.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
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
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
				<h5>Período desde """),_display_(/*41.24*/desdeDDMMAA),format.raw/*41.35*/(""" """),format.raw/*41.36*/("""a """),_display_(/*41.39*/hastaDDMMAA),format.raw/*41.50*/("""</h5>
				<div class="table-responsive">
					"""),_display_(/*43.7*/Html(tabla)),format.raw/*43.18*/("""
				"""),format.raw/*44.5*/("""</div>
			</div>
		</div>
	</div>


""")))}),format.raw/*50.2*/("""


"""),format.raw/*53.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*55.31*/("""{"""),format.raw/*55.32*/("""

		 """),format.raw/*57.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*57.35*/("""{"""),format.raw/*57.36*/("""
		    	"""),format.raw/*58.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*63.20*/("""{"""),format.raw/*63.21*/("""
		        	"""),format.raw/*64.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*65.11*/("""}"""),format.raw/*65.12*/("""
		  """),format.raw/*66.5*/("""}"""),format.raw/*66.6*/(""" """),format.raw/*66.7*/(""");

		let cfi = 0;
		$(".cfi").each(function() """),format.raw/*69.29*/("""{"""),format.raw/*69.30*/("""
			"""),format.raw/*70.4*/("""let val = $(this).text().replace(/,/g,'');
			cfi += parseFloat(val);
		"""),format.raw/*72.3*/("""}"""),format.raw/*72.4*/(""");
		$("#cfi").text(formatStandar(cfi,0));
		
		let arr = 0;
		$(".arr").each(function() """),format.raw/*76.29*/("""{"""),format.raw/*76.30*/("""
			"""),format.raw/*77.4*/("""let val = $(this).text().replace(/,/g,'');
			arr += parseFloat(val);
		"""),format.raw/*79.3*/("""}"""),format.raw/*79.4*/(""");
		$("#arr").text(formatStandar(arr,0));
		
		let vta = 0;
		$(".vta").each(function() """),format.raw/*83.29*/("""{"""),format.raw/*83.30*/("""
			"""),format.raw/*84.4*/("""let val = $(this).text().replace(/,/g,'');
			vta += parseFloat(val);
		"""),format.raw/*86.3*/("""}"""),format.raw/*86.4*/(""");
		$("#vta").text(formatStandar(vta,0));
		
		let ajustArr = 0;
		$(".ajustArr").each(function() """),format.raw/*90.34*/("""{"""),format.raw/*90.35*/("""
			"""),format.raw/*91.4*/("""let val = $(this).text().replace(/,/g,'');
			ajustArr += parseFloat(val);
		"""),format.raw/*93.3*/("""}"""),format.raw/*93.4*/(""");
		$("#ajustArr").text(formatStandar(ajustArr,0));
		
		let ajustVta = 0;
		$(".ajustVta").each(function() """),format.raw/*97.34*/("""{"""),format.raw/*97.35*/("""
			"""),format.raw/*98.4*/("""let val = $(this).text().replace(/,/g,'');
			ajustVta += parseFloat(val);
		"""),format.raw/*100.3*/("""}"""),format.raw/*100.4*/(""");
		$("#ajustVta").text(formatStandar(ajustVta,0));
		
		let granTotal = 0;
		$(".granTotal").each(function() """),format.raw/*104.35*/("""{"""),format.raw/*104.36*/("""
			"""),format.raw/*105.4*/("""let val = $(this).text().replace(/,/g,'');
			granTotal += parseFloat(val);
		"""),format.raw/*107.3*/("""}"""),format.raw/*107.4*/(""");
		$("#granTotal").text(formatStandar(granTotal,0));

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*111.2*/("""}"""),format.raw/*111.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tabla:String,desdeDDMMAA:String,hastaDDMMAA:String,id_proyecto:String,uf:Double,usd:Double,eur:Double,desdeAAMMDD:String,hastaAAMMDD:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tabla,desdeDDMMAA,hastaDDMMAA,id_proyecto,uf,usd,eur,desdeAAMMDD,hastaAAMMDD)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,String,Double,Double,Double,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tabla,desdeDDMMAA,hastaDDMMAA,id_proyecto,uf,usd,eur,desdeAAMMDD,hastaAAMMDD) => apply(mapDiccionario,mapPermiso,userMnu,tabla,desdeDDMMAA,hastaDDMMAA,id_proyecto,uf,usd,eur,desdeAAMMDD,hastaAAMMDD)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteMovimientosListaProyectos.scala.html
                  HASH: 19f8eff03e8371ee236482d9a790abdc66ae849f
                  MATRIX: 1096->1|1444->256|1476->263|1492->271|1531->273|1560->277|1628->325|1658->330|1713->365|1742->368|1785->390|1814->393|1858->416|1889->420|1994->498|2033->499|2064->504|2217->635|2243->643|2281->644|2312->649|2437->752|2471->756|2507->765|3202->1433|3234->1444|3263->1445|3293->1448|3325->1459|3398->1506|3430->1517|3462->1522|3529->1559|3559->1562|3667->1642|3696->1643|3728->1648|3787->1679|3816->1680|3851->1688|4018->1827|4047->1828|4087->1840|4193->1918|4222->1919|4254->1924|4282->1925|4310->1926|4385->1973|4414->1974|4445->1978|4544->2050|4572->2051|4689->2140|4718->2141|4749->2145|4848->2217|4876->2218|4993->2307|5022->2308|5053->2312|5152->2384|5180->2385|5307->2484|5336->2485|5367->2489|5471->2566|5499->2567|5636->2676|5665->2677|5696->2681|5801->2758|5830->2759|5970->2870|6000->2871|6032->2875|6138->2953|6167->2954|6320->3079|6349->3080
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|46->15|47->16|47->16|48->17|48->17|49->18|52->21|72->41|72->41|72->41|72->41|72->41|74->43|74->43|75->44|81->50|84->53|86->55|86->55|88->57|88->57|88->57|89->58|94->63|94->63|95->64|96->65|96->65|97->66|97->66|97->66|100->69|100->69|101->70|103->72|103->72|107->76|107->76|108->77|110->79|110->79|114->83|114->83|115->84|117->86|117->86|121->90|121->90|122->91|124->93|124->93|128->97|128->97|129->98|131->100|131->100|135->104|135->104|136->105|138->107|138->107|142->111|142->111
                  -- GENERATED --
              */
          