
package viewsMnuTablas.html

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

object usuarioListBodegaEmpresa extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,Long,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
id_usuario: Long, listBodegas: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "LISTA DE SELECCION DE PROYECTOS/@mapDiccionario.get('BODEGA')S", "")),format.raw/*9.101*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH>TIPO</TH>
							<TH>PROYECTO/"""),_display_(/*16.22*/mapDiccionario/*16.36*/.get("BODEGA")),format.raw/*16.50*/("""</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*21.8*/for(lista1 <- listBodegas) yield /*21.34*/{_display_(Seq[Any](format.raw/*21.35*/("""
							"""),format.raw/*22.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*23.40*/lista1/*23.46*/.get(4)),format.raw/*23.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*24.40*/lista1/*24.46*/.get(5)),format.raw/*24.53*/("""</td>
								<td  style="text-align:center;" width="10%">
									<form id="formUserSel_"""),_display_(/*26.33*/lista1/*26.39*/.get(1)),format.raw/*26.46*/("""_"""),_display_(/*26.48*/id_usuario),format.raw/*26.58*/("""" method="post" action="/usuarioBodegaSelect/">
										<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*27.64*/lista1/*27.70*/.get(1)),format.raw/*27.77*/("""">
										<input type="hidden" name="id_usuario" value=""""),_display_(/*28.58*/id_usuario),format.raw/*28.68*/("""">
										<a href="#" onclick='document.getElementById("formUserSel_"""),_display_(/*29.70*/lista1/*29.76*/.get(1)),format.raw/*29.83*/("""_"""),_display_(/*29.85*/id_usuario),format.raw/*29.95*/("""").submit();'>
											<kbd style="background-color: #73C6B6">Select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*35.9*/("""
					"""),format.raw/*36.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<form method="post" action="/usuarioModifica/">
						<input type="hidden" name="id_usuario" value=""""),_display_(/*44.54*/id_usuario),format.raw/*44.64*/("""">
						<input type="submit"  value="CANCELAR" class="btn btn-success btn-sm rounded-pill btn-block">
					</form>
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*52.2*/("""




"""),format.raw/*57.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*58.31*/("""{"""),format.raw/*58.32*/("""
		  """),format.raw/*59.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*59.36*/("""{"""),format.raw/*59.37*/("""
		    	"""),format.raw/*60.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*62.20*/("""{"""),format.raw/*62.21*/("""
		        	"""),format.raw/*63.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*64.11*/("""}"""),format.raw/*64.12*/("""
		  """),format.raw/*65.5*/("""}"""),format.raw/*65.6*/(""" """),format.raw/*65.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*67.2*/("""}"""),format.raw/*67.3*/(""");
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,id_usuario:Long,listBodegas:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,id_usuario,listBodegas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,Long,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,id_usuario,listBodegas) => apply(mapDiccionario,mapPermiso,userMnu,id_usuario,listBodegas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/usuarioListBodegaEmpresa.scala.html
                  HASH: 3b44bfca61d15e9fd1a75e6475f42145baa76318
                  MATRIX: 1046->1|1287->149|1320->157|1336->165|1375->167|1403->170|1471->218|1499->220|1575->271|1693->368|1723->371|2060->681|2083->695|2118->709|2219->784|2261->810|2300->811|2335->819|2406->863|2421->869|2449->876|2521->921|2536->927|2564->934|2682->1025|2697->1031|2725->1038|2754->1040|2785->1050|2923->1161|2938->1167|2966->1174|3053->1234|3084->1244|3183->1316|3198->1322|3226->1329|3255->1331|3286->1341|3461->1486|3494->1492|3794->1765|3825->1775|4011->1931|4043->1936|4133->1998|4162->1999|4194->2004|4253->2035|4282->2036|4317->2044|4459->2158|4488->2159|4528->2171|4634->2249|4663->2250|4695->2255|4723->2256|4751->2257|4851->2330|4879->2331
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|52->21|52->21|52->21|53->22|54->23|54->23|54->23|55->24|55->24|55->24|57->26|57->26|57->26|57->26|57->26|58->27|58->27|58->27|59->28|59->28|60->29|60->29|60->29|60->29|60->29|66->35|67->36|75->44|75->44|83->52|88->57|89->58|89->58|90->59|90->59|90->59|91->60|93->62|93->62|94->63|95->64|95->64|96->65|96->65|96->65|98->67|98->67
                  -- GENERATED --
              */
          