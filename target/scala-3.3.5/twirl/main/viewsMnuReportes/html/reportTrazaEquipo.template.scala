
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

object reportTrazaEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Equipo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[tables.Equipo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "TRAZABILIDAD DE EQUIPOS","(SELECCION DE EQUIPOS)")),format.raw/*12.83*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
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
							<td>
								<div align="center">
									<button type="button"  class="btn btn-sm btn-success" 
										onclick="history.go(-1);return false;">
										Volver
									</button>
								</div>
							</td>
							<td width="25%">
							</div>
						</tr>
					</table>
				</div>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
					        <TR>
								<TH style= "text-align: center;">GRUPO</TH>
						        <TH style= "text-align: center;">CODIGO</TH>
								<TH style= "text-align: center;">EQUIPO</TH>
								<TH style= "text-align: center;">DETALLE</TH>	
							</TR>
						</thead>
						<tbody>
						"""),_display_(/*53.8*/for(lista1 <- lista) yield /*53.28*/{_display_(Seq[Any](format.raw/*53.29*/("""
							"""),format.raw/*54.8*/("""<TR>
								<td style="text-align: left;">"""),_display_(/*55.40*/lista1/*55.46*/.getGrupo()),format.raw/*55.57*/("""</td>
								<td style="text-align: left;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*56.80*/lista1/*56.86*/.getId()),format.raw/*56.94*/("""');">"""),_display_(/*56.100*/lista1/*56.106*/.getCodigo()),format.raw/*56.118*/("""</a></td>
								<td style="text-align: left;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*57.80*/lista1/*57.86*/.getId()),format.raw/*57.94*/("""');">"""),_display_(/*57.100*/lista1/*57.106*/.getNombre()),format.raw/*57.118*/("""</a></td>
								<td style="text-align:center;vertical-align:middle;">
									<form id="form1_"""),_display_(/*59.27*/lista1/*59.33*/.getId()),format.raw/*59.41*/("""" class="formulario" method="post" action="/reportTrazaEquipo2/">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*60.57*/lista1/*60.63*/.getId()),format.raw/*60.71*/("""">
										<a href="#" onclick="document.getElementById('form1_"""),_display_(/*61.64*/lista1/*61.70*/.getId()),format.raw/*61.78*/("""').submit()">
											<kbd style="background-color: #73C6B6">Select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*67.9*/("""
						"""),format.raw/*68.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	
""")))}),format.raw/*76.2*/("""


"""),format.raw/*79.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*80.31*/("""{"""),format.raw/*80.32*/("""
		 """),format.raw/*81.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*81.35*/("""{"""),format.raw/*81.36*/("""
		    	"""),format.raw/*82.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*87.20*/("""{"""),format.raw/*87.21*/("""
		        	"""),format.raw/*88.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*89.11*/("""}"""),format.raw/*89.12*/("""
		  """),format.raw/*90.5*/("""}"""),format.raw/*90.6*/(""" """),format.raw/*90.7*/(""");
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*92.2*/("""}"""),format.raw/*92.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[tables.Equipo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Equipo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista) => apply(mapDiccionario,mapPermiso,userMnu,lista)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportTrazaEquipo.scala.html
                  HASH: fa8e28c213f6ae90f8eb505b3024dfe87481a6d0
                  MATRIX: 1037->1|1255->126|1288->134|1304->142|1343->144|1372->148|1440->196|1470->201|1514->225|1545->229|1622->280|1722->359|1752->362|3137->1721|3173->1741|3212->1742|3247->1750|3318->1794|3333->1800|3365->1811|3477->1896|3492->1902|3521->1910|3555->1916|3571->1922|3605->1934|3721->2023|3736->2029|3765->2037|3799->2043|3815->2049|3849->2061|3974->2159|3989->2165|4018->2173|4167->2295|4182->2301|4211->2309|4304->2375|4319->2381|4348->2389|4522->2533|4556->2540|4652->2606|4682->2609|4772->2671|4801->2672|4832->2676|4891->2707|4920->2708|4955->2716|5135->2868|5164->2869|5204->2881|5310->2959|5339->2960|5371->2965|5399->2966|5427->2967|5525->3038|5553->3039
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|44->13|84->53|84->53|84->53|85->54|86->55|86->55|86->55|87->56|87->56|87->56|87->56|87->56|87->56|88->57|88->57|88->57|88->57|88->57|88->57|90->59|90->59|90->59|91->60|91->60|91->60|92->61|92->61|92->61|98->67|99->68|107->76|110->79|111->80|111->80|112->81|112->81|112->81|113->82|118->87|118->87|119->88|120->89|120->89|121->90|121->90|121->90|123->92|123->92
                  -- GENERATED --
              */
          