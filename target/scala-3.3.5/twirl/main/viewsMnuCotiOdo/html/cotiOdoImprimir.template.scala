
package viewsMnuCotiOdo.html

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

object cotiOdoImprimir extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,Long,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
id_cotiOdo: Long, tabla: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
		
		"""),format.raw/*8.3*/("""<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*9.5*/barraTitulo(mapDiccionario, "COTIZACION ODO", "")),format.raw/*9.54*/("""
			"""),format.raw/*10.4*/("""<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  value="Imprimir" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.print(); return false;">
					</div>
					<div class="col-xs-5 col-sm-4 col-md-3 col-lg-2">
						<form id='formVta' method='post' action='/routes2/pdfCotiOdoVta/'>
							<input type="hidden" name="id_cotiOdo" value=""""),_display_(/*17.55*/id_cotiOdo),format.raw/*17.65*/("""">
							<input type="submit"  value="PDF Venta" class="btn btn-secondary btn-sm rounded-pill btn-block">
						</form>
					</div>
					<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">
						<form action="/routes2/cotiOdoExcel/" method="POST">
							<input type="hidden" name="id_cotiOdo" value=""""),_display_(/*23.55*/id_cotiOdo),format.raw/*23.65*/("""">
							<input type="submit" class="btn btn-info btn-sm rounded-pill btn-block" value="Excel">
						</form>
						
					</div>
				</div>
				<br><br>
			</div>
			
			<div id="element">
				<div class="row  justify-content-md-center">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						"""),_display_(/*35.8*/Html(tabla)),format.raw/*35.19*/("""
					"""),format.raw/*36.6*/("""</div>
				</div>
			</div>
			<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/cotiOdoListaImprimir/';">
					</div>
				</div>
			</div>
		</div>
		
		
	<!--	
	<input type="button"  value="word" class="btn btn-success btn-sm rounded-pill btn-block" onclick="exportarWord('mostrarmostrar','word');">
	<input type="button"  value="excel" class="btn btn-success btn-sm rounded-pill btn-block" onclick="exportarExcel('mostrarmostrar','excel');">
	-->
	
""")))}),format.raw/*54.2*/("""



"""),format.raw/*58.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*60.31*/("""{"""),format.raw/*60.32*/("""
		"""),format.raw/*61.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*62.2*/("""}"""),format.raw/*62.3*/(""");
	
	
	
	
	
	function exportarExcel(element, filename)"""),format.raw/*68.43*/("""{"""),format.raw/*68.44*/("""
	    """),format.raw/*69.6*/("""var html = document.getElementById(element).innerHTML;
	
	    var blob = new Blob(['ufeff', html], """),format.raw/*71.43*/("""{"""),format.raw/*71.44*/("""
	        """),format.raw/*72.10*/("""type: 'application/msexcel'
	    """),format.raw/*73.6*/("""}"""),format.raw/*73.7*/(""");
	    
	    // Specify link url
	    var url = 'data:application/vnd.ms-excel;charset=utf-8,' + encodeURIComponent(html);
	    
	    // Specify file name
	    filename = filename?filename+'.xls':filename;
	    
	    // Create download link element
	    var downloadLink = document.createElement("a");
	
	    document.body.appendChild(downloadLink);
	    
	    if(navigator.msSaveOrOpenBlob )"""),format.raw/*86.37*/("""{"""),format.raw/*86.38*/("""
	        """),format.raw/*87.10*/("""navigator.msSaveOrOpenBlob(blob, filename);
	    """),format.raw/*88.6*/("""}"""),format.raw/*88.7*/("""else"""),format.raw/*88.11*/("""{"""),format.raw/*88.12*/("""
	        """),format.raw/*89.10*/("""// Create a link to the file
	        downloadLink.href = url;
	        
	        // Setting the file name
	        downloadLink.download = filename;
	        
	        //triggering the function
	        downloadLink.click();
	    """),format.raw/*97.6*/("""}"""),format.raw/*97.7*/("""
	    
	    """),format.raw/*99.6*/("""document.body.removeChild(downloadLink);
	"""),format.raw/*100.2*/("""}"""),format.raw/*100.3*/("""
	
	"""),format.raw/*102.2*/("""function exportarWord(element, filename)"""),format.raw/*102.42*/("""{"""),format.raw/*102.43*/("""
	    """),format.raw/*103.6*/("""var preHtml = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word' xmlns='http://www.w3.org/TR/REC-html40'><head><meta charset='utf-8'><title>Export HTML To Doc</title></head><body>";
	    var postHtml = "</body></html>";
	    var html = preHtml+document.getElementById(element).innerHTML+postHtml;
	
	    var blob = new Blob(['ufeff', html], """),format.raw/*107.43*/("""{"""),format.raw/*107.44*/("""
	        """),format.raw/*108.10*/("""type: 'application/msword'
	    """),format.raw/*109.6*/("""}"""),format.raw/*109.7*/(""");
	    
	    // Specify link url
	    var url = 'data:application/vnd.ms-word;charset=utf-8,' + encodeURIComponent(html);
	    
	    // Specify file name
	    filename = filename?filename+'.doc':filename;
	    
	    // Create download link element
	    var downloadLink = document.createElement("a");
	
	    document.body.appendChild(downloadLink);
	    
	    if(navigator.msSaveOrOpenBlob )"""),format.raw/*122.37*/("""{"""),format.raw/*122.38*/("""
	        """),format.raw/*123.10*/("""navigator.msSaveOrOpenBlob(blob, filename);
	    """),format.raw/*124.6*/("""}"""),format.raw/*124.7*/("""else"""),format.raw/*124.11*/("""{"""),format.raw/*124.12*/("""
	        """),format.raw/*125.10*/("""// Create a link to the file
	        downloadLink.href = url;
	        
	        // Setting the file name
	        downloadLink.download = filename;
	        
	        //triggering the function
	        downloadLink.click();
	    """),format.raw/*133.6*/("""}"""),format.raw/*133.7*/("""
	    
	    """),format.raw/*135.6*/("""document.body.removeChild(downloadLink);
	"""),format.raw/*136.2*/("""}"""),format.raw/*136.3*/("""
	


	
"""),format.raw/*141.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,id_cotiOdo:Long,tabla:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,id_cotiOdo,tabla)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,Long,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,id_cotiOdo,tabla) => apply(mapDiccionario,mapPermiso,userMnu,id_cotiOdo,tabla)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/cotiOdoImprimir.scala.html
                  HASH: 99e5aabdb27d3ad2a0a428e2a1d794ceaad49dbe
                  MATRIX: 1026->1|1249->131|1276->133|1292->141|1331->143|1360->147|1428->195|1460->201|1537->253|1606->302|1637->306|2137->779|2168->789|2496->1090|2527->1100|2858->1405|2890->1416|2923->1422|3624->2093|3655->2097|3746->2160|3775->2161|3805->2164|3898->2230|3926->2231|4009->2286|4038->2287|4071->2293|4198->2392|4227->2393|4265->2403|4325->2436|4353->2437|4774->2830|4803->2831|4841->2841|4917->2890|4945->2891|4977->2895|5006->2896|5044->2906|5302->3137|5330->3138|5369->3150|5439->3192|5468->3193|5500->3197|5569->3237|5599->3238|5633->3244|6057->3639|6087->3640|6126->3650|6186->3682|6215->3683|6636->4075|6666->4076|6705->4086|6782->4135|6811->4136|6844->4140|6874->4141|6913->4151|7172->4382|7201->4383|7241->4395|7311->4437|7340->4438|7375->4445
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|40->9|40->9|41->10|48->17|48->17|54->23|54->23|66->35|66->35|67->36|85->54|89->58|91->60|91->60|92->61|93->62|93->62|99->68|99->68|100->69|102->71|102->71|103->72|104->73|104->73|117->86|117->86|118->87|119->88|119->88|119->88|119->88|120->89|128->97|128->97|130->99|131->100|131->100|133->102|133->102|133->102|134->103|138->107|138->107|139->108|140->109|140->109|153->122|153->122|154->123|155->124|155->124|155->124|155->124|156->125|164->133|164->133|166->135|167->136|167->136|172->141
                  -- GENERATED --
              */
          