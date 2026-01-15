
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

object reportInventarioGrupo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Grupo],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[tables.Grupo], fechaCorte: String, tipo:String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "SELECCION DE GRUPO/FAMILIA - "+tipo,"FECHA DE CORTE: " + utilities.Fechas.DDMMAA(fechaCorte))),format.raw/*8.126*/("""
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
									<form id="form_"""),_display_(/*41.26*/lista1/*41.32*/.getId),format.raw/*41.38*/("""" class="formulario" method="post" action="/reportInventarioSelectivoXGrupo/">
										<input type="hidden" name="id_grupo" value=""""),_display_(/*42.56*/lista1/*42.62*/.getId),format.raw/*42.68*/("""">
										<input type="hidden" name="fechaCorte" value=""""),_display_(/*43.58*/fechaCorte),format.raw/*43.68*/("""">
										<input type="hidden" name="tipo" value=""""),_display_(/*44.52*/tipo),format.raw/*44.56*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*45.63*/lista1/*45.69*/.getId),format.raw/*45.75*/("""').submit()">
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*51.9*/("""
						"""),format.raw/*52.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	

""")))}),format.raw/*60.2*/("""


"""),format.raw/*63.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*65.31*/("""{"""),format.raw/*65.32*/("""

		 """),format.raw/*67.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*67.35*/("""{"""),format.raw/*67.36*/("""
		    	"""),format.raw/*68.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 0, "asc" ]],
		    	"language": """),format.raw/*73.20*/("""{"""),format.raw/*73.21*/("""
		        	"""),format.raw/*74.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*75.11*/("""}"""),format.raw/*75.12*/("""
		  """),format.raw/*76.5*/("""}"""),format.raw/*76.6*/(""" """),format.raw/*76.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*79.2*/("""}"""),format.raw/*79.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[tables.Grupo],fechaCorte:String,tipo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,fechaCorte,tipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Grupo],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,fechaCorte,tipo) => apply(mapDiccionario,mapPermiso,userMnu,lista,fechaCorte,tipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportInventarioGrupo.scala.html
                  HASH: cb28d1d20c8629019f87a4b1a70f58da28fab5a1
                  MATRIX: 1054->1|1304->158|1336->165|1352->173|1391->175|1420->179|1488->227|1516->229|1592->280|1735->402|1764->405|2829->1444|2865->1464|2904->1465|2939->1473|3031->1538|3046->1544|3077->1554|3195->1645|3210->1651|3237->1657|3398->1791|3413->1797|3440->1803|3527->1863|3558->1873|3639->1927|3664->1931|3756->1996|3771->2002|3798->2008|3972->2152|4006->2159|4101->2224|4131->2227|4239->2307|4268->2308|4300->2313|4359->2344|4388->2345|4423->2353|4590->2492|4619->2493|4659->2505|4765->2583|4794->2584|4826->2589|4854->2590|4882->2591|4982->2664|5010->2665
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|68->37|68->37|68->37|69->38|70->39|70->39|70->39|72->41|72->41|72->41|73->42|73->42|73->42|74->43|74->43|75->44|75->44|76->45|76->45|76->45|82->51|83->52|91->60|94->63|96->65|96->65|98->67|98->67|98->67|99->68|104->73|104->73|105->74|106->75|106->75|107->76|107->76|107->76|110->79|110->79
                  -- GENERATED --
              */
          