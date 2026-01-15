
package viewsMnuRedimensionar.html

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

object redimensionarAsignaBodega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.BodegaEmpresa],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, bodega: tables.BodegaEmpresa, listBodegas: List[tables.BodegaEmpresa]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

"""),_display_(/*6.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.50*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "MODIFICAR ORIGEN "+mapDiccionario.get("BODEGA")+" PARA REDIMENSIONAR", "")),format.raw/*8.107*/("""
		"""),format.raw/*9.3*/("""<form action="/routes2/redimensionarAsignaBodegaUpdate/" method="POST">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td>
								<div class="row">
									<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
										<b><font color="#008000">"""),_display_(/*17.37*/mapDiccionario/*17.51*/.get("BODEGA")),format.raw/*17.65*/(""":</font></b>
									</div>
									<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
										<select class="custom-select"
												name="id_bodegaEmpresa"
												required>
											<option value=""""),_display_(/*23.28*/bodega/*23.34*/.getId()),format.raw/*23.42*/("""">"""),_display_(/*23.45*/bodega/*23.51*/.getNombre()),format.raw/*23.63*/("""</option>
											"""),_display_(/*24.13*/for(lista <- listBodegas) yield /*24.38*/{_display_(Seq[Any](format.raw/*24.39*/("""
												"""),_display_(if(lista.getVigente() > 0)/*25.40*/{_display_(Seq[Any](format.raw/*25.41*/("""
													"""),format.raw/*26.14*/("""<option value=""""),_display_(/*26.30*/lista/*26.35*/.getId()),format.raw/*26.43*/("""">"""),_display_(/*26.46*/lista/*26.51*/.getNombre()),format.raw/*26.63*/("""</option>
												""")))} else {null} ),format.raw/*27.14*/("""
											""")))}),format.raw/*28.13*/("""
										"""),format.raw/*29.11*/("""</select>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit"  value="GRABAR" class="btn btn-success btn-sm rounded-pill btn-block">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
				</div>
			</div>
		</form>
	</div>
""")))}),format.raw/*49.2*/("""




"""),format.raw/*54.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*55.31*/("""{"""),format.raw/*55.32*/("""
		  """),format.raw/*56.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*57.2*/("""}"""),format.raw/*57.3*/(""");
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listBodegas:List[tables.BodegaEmpresa]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listBodegas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.BodegaEmpresa]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listBodegas) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listBodegas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuRedimensionar/redimensionarAsignaBodega.scala.html
                  HASH: 2cb073bdb2d4136e337ef421ca05fc047952c65b
                  MATRIX: 1078->1|1339->169|1372->177|1388->185|1427->187|1455->190|1523->238|1551->240|1627->291|1751->394|1780->397|2205->795|2228->809|2263->823|2506->1039|2521->1045|2550->1053|2580->1056|2595->1062|2628->1074|2677->1096|2718->1121|2757->1122|2824->1162|2863->1163|2905->1177|2948->1193|2962->1198|2991->1206|3021->1209|3035->1214|3068->1226|3135->1249|3179->1262|3218->1273|3827->1852|3859->1857|3949->1919|3978->1920|4010->1925|4103->1991|4131->1992
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|48->17|48->17|48->17|54->23|54->23|54->23|54->23|54->23|54->23|55->24|55->24|55->24|56->25|56->25|57->26|57->26|57->26|57->26|57->26|57->26|57->26|58->27|59->28|60->29|80->49|85->54|86->55|86->55|87->56|88->57|88->57
                  -- GENERATED --
              */
          