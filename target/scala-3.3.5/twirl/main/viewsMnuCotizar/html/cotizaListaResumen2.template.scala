
package viewsMnuCotizar.html

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

object cotizaListaResumen2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,String,tables.Cliente,String,tables.Proyecto,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tabla: String, cliente: tables.Cliente, listadoIdCoti: String, proyecto: tables.Proyecto):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
		
		"""),format.raw/*8.3*/("""<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*9.5*/barraTitulo(mapDiccionario, "RESUMEN DE COTIZACION", "CONSOLIDADO")),format.raw/*9.72*/("""
			"""),format.raw/*10.4*/("""<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  value="Imprimir" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.print(); return false;">
					</div>
					<div class="col-xs-5 col-sm-4 col-md-3 col-lg-2">
						<form id='formArrVta' method='post' action='/routes2/cotizaListaResumen3/' onsubmit="return validarForm();">
							<input type="hidden" name="listadoIdCoti" value=""""),_display_(/*17.58*/listadoIdCoti),format.raw/*17.71*/("""">
							<input type="hidden" name="id_cliente" value=""""),_display_(/*18.55*/cliente/*18.62*/.getId()),format.raw/*18.70*/("""">
							<input type="hidden" name="id_proyecto" value=""""),_display_(/*19.56*/proyecto/*19.64*/.getId()),format.raw/*19.72*/("""">
							<input type="submit" id="btnSubmit1"  value="Enviar a Biblioteca" class="btn btn-secondary btn-sm rounded-pill btn-block">
						</form>
					</div>
					<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">
						<form action="/routes2/cotizaListaResumen2Excel/" method="POST" onsubmit="return validarForm2();">
							<input type="hidden" name="listadoIdCoti" value=""""),_display_(/*25.58*/listadoIdCoti),format.raw/*25.71*/("""">
							<input type="hidden" name="id_cliente" value=""""),_display_(/*26.55*/cliente/*26.62*/.getId()),format.raw/*26.70*/("""">
							<input type="hidden" name="id_proyecto" value=""""),_display_(/*27.56*/proyecto/*27.64*/.getId()),format.raw/*27.72*/("""">
							<input type="submit" id="btnSubmit2" class="btn btn-info btn-sm rounded-pill btn-block" value="Excel">
						</form>
						
					</div>
				</div>
				<br><br>
			</div>
			
			<div id="element">
				<div class="row  justify-content-md-center">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						"""),_display_(/*39.8*/Html(tabla)),format.raw/*39.19*/("""
					"""),format.raw/*40.6*/("""</div>
				</div>
			</div>
			
			<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="SALIR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
				</div>
			</div>
		</div>
		

		
""")))}),format.raw/*55.2*/("""



"""),format.raw/*59.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*61.31*/("""{"""),format.raw/*61.32*/("""
		"""),format.raw/*62.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*63.2*/("""}"""),format.raw/*63.3*/(""");
	
	const validarForm = () =>"""),format.raw/*65.27*/("""{"""),format.raw/*65.28*/("""
		"""),format.raw/*66.3*/("""$("#btnSubmit1").attr('disabled',true);
		return(true);
	"""),format.raw/*68.2*/("""}"""),format.raw/*68.3*/("""
	
	"""),format.raw/*70.2*/("""const validarForm2 = () =>"""),format.raw/*70.28*/("""{"""),format.raw/*70.29*/("""
		"""),format.raw/*71.3*/("""$("#btnSubmit2").attr('disabled',true);
		return(true);
	"""),format.raw/*73.2*/("""}"""),format.raw/*73.3*/("""
	

	
"""),format.raw/*77.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tabla:String,cliente:tables.Cliente,listadoIdCoti:String,proyecto:tables.Proyecto): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tabla,cliente,listadoIdCoti,proyecto)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,tables.Cliente,String,tables.Proyecto) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tabla,cliente,listadoIdCoti,proyecto) => apply(mapDiccionario,mapPermiso,userMnu,tabla,cliente,listadoIdCoti,proyecto)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaListaResumen2.scala.html
                  HASH: e25549835cb4779a84a7bc7b839b0623dc5381e2
                  MATRIX: 1063->1|1343->188|1370->190|1386->198|1425->200|1454->204|1522->252|1554->258|1631->310|1718->377|1749->381|2294->899|2328->912|2412->969|2428->976|2457->984|2542->1042|2559->1050|2588->1058|2991->1434|3025->1447|3109->1504|3125->1511|3154->1519|3239->1577|3256->1585|3285->1593|3632->1914|3664->1925|3697->1931|4082->2286|4113->2290|4204->2353|4233->2354|4263->2357|4356->2423|4384->2424|4443->2455|4472->2456|4502->2459|4586->2516|4614->2517|4645->2521|4699->2547|4728->2548|4758->2551|4842->2608|4870->2609|4903->2615
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|40->9|40->9|41->10|48->17|48->17|49->18|49->18|49->18|50->19|50->19|50->19|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|70->39|70->39|71->40|86->55|90->59|92->61|92->61|93->62|94->63|94->63|96->65|96->65|97->66|99->68|99->68|101->70|101->70|101->70|102->71|104->73|104->73|108->77
                  -- GENERATED --
              */
          