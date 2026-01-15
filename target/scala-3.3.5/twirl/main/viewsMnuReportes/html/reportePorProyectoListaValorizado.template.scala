
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

object reportePorProyectoListaValorizado extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tabla: String, desdeDDMMAA: String, hastaDDMMAA: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalContactoBodega(mapDiccionario)),format.raw/*8.38*/("""
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","REPORTE DETALLE DE MOVIMIENTOS POR PROYECTO (VALORIZADO)")),format.raw/*13.148*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
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
						</tr>
					</table>
				</div>
				<h5>Período desde """),_display_(/*32.24*/desdeDDMMAA),format.raw/*32.35*/(""" """),format.raw/*32.36*/("""a """),_display_(/*32.39*/hastaDDMMAA),format.raw/*32.50*/("""</h5>
				<div class="table-responsive">
					"""),_display_(/*34.7*/Html(tabla)),format.raw/*34.18*/("""
				"""),format.raw/*35.5*/("""</div>
			</div>
		</div>
	</div>


""")))}),format.raw/*41.2*/("""


"""),format.raw/*44.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*46.31*/("""{"""),format.raw/*46.32*/("""

		 """),format.raw/*48.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*48.35*/("""{"""),format.raw/*48.36*/("""
		    	"""),format.raw/*49.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*54.20*/("""{"""),format.raw/*54.21*/("""
		        	"""),format.raw/*55.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*56.11*/("""}"""),format.raw/*56.12*/("""
		  """),format.raw/*57.5*/("""}"""),format.raw/*57.6*/(""" """),format.raw/*57.7*/(""");

		let cfi = 0;
		$(".cfi").each(function() """),format.raw/*60.29*/("""{"""),format.raw/*60.30*/("""
			"""),format.raw/*61.4*/("""let val = $(this).text().replace(/,/g,'');
			cfi += parseFloat(val);
		"""),format.raw/*63.3*/("""}"""),format.raw/*63.4*/(""");
		$("#cfi").text(formatStandar(cfi,0));
		
		let arr = 0;
		$(".arr").each(function() """),format.raw/*67.29*/("""{"""),format.raw/*67.30*/("""
			"""),format.raw/*68.4*/("""let val = $(this).text().replace(/,/g,'');
			arr += parseFloat(val);
		"""),format.raw/*70.3*/("""}"""),format.raw/*70.4*/(""");
		$("#arr").text(formatStandar(arr,0));
		
		let vta = 0;
		$(".vta").each(function() """),format.raw/*74.29*/("""{"""),format.raw/*74.30*/("""
			"""),format.raw/*75.4*/("""let val = $(this).text().replace(/,/g,'');
			vta += parseFloat(val);
		"""),format.raw/*77.3*/("""}"""),format.raw/*77.4*/(""");
		$("#vta").text(formatStandar(vta,0));
		
		let ajustArr = 0;
		$(".ajustArr").each(function() """),format.raw/*81.34*/("""{"""),format.raw/*81.35*/("""
			"""),format.raw/*82.4*/("""let val = $(this).text().replace(/,/g,'');
			ajustArr += parseFloat(val);
		"""),format.raw/*84.3*/("""}"""),format.raw/*84.4*/(""");
		$("#ajustArr").text(formatStandar(ajustArr,0));
		
		let ajustVta = 0;
		$(".ajustVta").each(function() """),format.raw/*88.34*/("""{"""),format.raw/*88.35*/("""
			"""),format.raw/*89.4*/("""let val = $(this).text().replace(/,/g,'');
			ajustVta += parseFloat(val);
		"""),format.raw/*91.3*/("""}"""),format.raw/*91.4*/(""");
		$("#ajustVta").text(formatStandar(ajustVta,0));
		
		let granTotal = 0;
		$(".granTotal").each(function() """),format.raw/*95.35*/("""{"""),format.raw/*95.36*/("""
			"""),format.raw/*96.4*/("""let val = $(this).text().replace(/,/g,'');
			granTotal += parseFloat(val);
		"""),format.raw/*98.3*/("""}"""),format.raw/*98.4*/(""");
		$("#granTotal").text(formatStandar(granTotal,0));

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*102.2*/("""}"""),format.raw/*102.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tabla:String,desdeDDMMAA:String,hastaDDMMAA:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tabla,desdeDDMMAA,hastaDDMMAA)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tabla,desdeDDMMAA,hastaDDMMAA) => apply(mapDiccionario,mapPermiso,userMnu,tabla,desdeDDMMAA,hastaDDMMAA)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportePorProyectoListaValorizado.scala.html
                  HASH: ad8ec47de00b56dcba7855c41e1c1c0f647ffda8
                  MATRIX: 1054->1|1301->155|1333->162|1349->170|1388->172|1417->176|1485->224|1515->229|1570->264|1598->267|1640->289|1669->292|1713->315|1744->319|1821->370|1987->514|2017->517|2678->1151|2710->1162|2739->1163|2769->1166|2801->1177|2874->1224|2906->1235|2938->1240|3005->1277|3035->1280|3143->1360|3172->1361|3204->1366|3263->1397|3292->1398|3327->1406|3494->1545|3523->1546|3563->1558|3669->1636|3698->1637|3730->1642|3758->1643|3786->1644|3861->1691|3890->1692|3921->1696|4020->1768|4048->1769|4165->1858|4194->1859|4225->1863|4324->1935|4352->1936|4469->2025|4498->2026|4529->2030|4628->2102|4656->2103|4783->2202|4812->2203|4843->2207|4947->2284|4975->2285|5112->2394|5141->2395|5172->2399|5276->2476|5304->2477|5443->2588|5472->2589|5503->2593|5608->2671|5636->2672|5789->2797|5818->2798
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|63->32|63->32|63->32|63->32|63->32|65->34|65->34|66->35|72->41|75->44|77->46|77->46|79->48|79->48|79->48|80->49|85->54|85->54|86->55|87->56|87->56|88->57|88->57|88->57|91->60|91->60|92->61|94->63|94->63|98->67|98->67|99->68|101->70|101->70|105->74|105->74|106->75|108->77|108->77|112->81|112->81|113->82|115->84|115->84|119->88|119->88|120->89|122->91|122->91|126->95|126->95|127->96|129->98|129->98|133->102|133->102
                  -- GENERATED --
              */
          