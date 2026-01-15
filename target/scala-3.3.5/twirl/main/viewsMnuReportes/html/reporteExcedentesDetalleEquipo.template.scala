
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

object reporteExcedentesDetalleEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.Equipo,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], equipo: tables.Equipo):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalEquipoDescripcion()),format.raw/*8.27*/("""
	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "DETALLE DE EXCEDENTES", "EQUIPO: "+equipo.getCodigo+" - "+equipo.getNombre)),format.raw/*11.108*/("""
		
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
			<div class="8 col-sm-6 col-md-6 col-lg-6">
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
										onkeyup="doSearch4('tablaPrincipal');">
								</div>
							</td>
							<td>
								<div align="center">
									<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
										Exportar a Excel
									</button>
									<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
										Imprimir
									</button>
									
									<button type="button"  class="btn btn-sm btn-success" 
										onclick="history.go(-1);return false;">
										Volver
									</button>
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
					        <TR> 
						        <TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
						        <TH style= "text-align: center;vertical-align: top;">"""),_display_(/*52.69*/mapDiccionario/*52.83*/.get("BODEGA")),format.raw/*52.97*/("""/PROYECTO</TH>
						        <TH style= "text-align: center;vertical-align: top;">EXCEDENTES</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*57.9*/for(lista1 <- lista) yield /*57.29*/{_display_(Seq[Any](format.raw/*57.30*/("""
								"""),format.raw/*58.9*/("""<TR>
									<td style="text-align:center;vertical-align:middle;">"""),_display_(/*59.64*/lista1/*59.70*/.get(0)),format.raw/*59.77*/("""</td>
									<td style="text-align:left;vertical-align:middle;">"""),_display_(/*60.62*/lista1/*60.68*/.get(1)),format.raw/*60.75*/("""</td>
									<td style="text-align:center;vertical-align:middle;">"""),_display_(/*61.64*/lista1/*61.70*/.get(2)),format.raw/*61.77*/("""</td>
								</TR>
				 			""")))}),format.raw/*63.10*/("""
						"""),format.raw/*64.7*/("""</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>
	<form id="excel" class="formulario" method="post" action="/routes2/reporteExcedentesDetalleEquipoExcel/">
		<input type="hidden" name="id_equipo" value=""""),_display_(/*72.49*/equipo/*72.55*/.getId()),format.raw/*72.63*/("""">
	</form>


""")))}),format.raw/*76.2*/("""


"""),format.raw/*79.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*81.31*/("""{"""),format.raw/*81.32*/("""
		
		"""),format.raw/*83.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*83.34*/("""{"""),format.raw/*83.35*/("""
	    	"""),format.raw/*84.7*/(""""fixedHeader": true,
	    	"paging": false,
			"info": false,
			"searching": false,
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*89.19*/("""{"""),format.raw/*89.20*/("""
	        	"""),format.raw/*90.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*91.10*/("""}"""),format.raw/*91.11*/("""
	  	"""),format.raw/*92.5*/("""}"""),format.raw/*92.6*/(""" """),format.raw/*92.7*/(""");
		


			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*97.2*/("""}"""),format.raw/*97.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],equipo:tables.Equipo): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,equipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.Equipo) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,equipo) => apply(mapDiccionario,mapPermiso,userMnu,lista,equipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteExcedentesDetalleEquipo.scala.html
                  HASH: 5492c4e61f901e88912f76e2994007d8dad48cd7
                  MATRIX: 1063->1|1303->148|1335->155|1351->163|1390->165|1419->169|1487->217|1517->222|1561->246|1592->250|1669->301|1795->405|1828->411|3375->1931|3398->1945|3433->1959|3607->2107|3643->2127|3682->2128|3718->2137|3813->2205|3828->2211|3856->2218|3950->2285|3965->2291|3993->2298|4089->2367|4104->2373|4132->2380|4192->2409|4226->2416|4470->2633|4485->2639|4514->2647|4559->2662|4589->2665|4680->2728|4709->2729|4742->2735|4801->2766|4830->2767|4864->2774|5026->2908|5055->2909|5094->2920|5199->2997|5228->2998|5260->3003|5288->3004|5316->3005|5420->3082|5448->3083
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|41->10|42->11|42->11|44->13|83->52|83->52|83->52|88->57|88->57|88->57|89->58|90->59|90->59|90->59|91->60|91->60|91->60|92->61|92->61|92->61|94->63|95->64|103->72|103->72|103->72|107->76|110->79|112->81|112->81|114->83|114->83|114->83|115->84|120->89|120->89|121->90|122->91|122->91|123->92|123->92|123->92|128->97|128->97
                  -- GENERATED --
              */
          