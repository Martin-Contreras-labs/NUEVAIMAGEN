
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

object reportTrazaEquipo2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], id_equipo: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	"""),_display_(/*10.3*/modalContactoBodega(mapDiccionario)),format.raw/*10.38*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "TRAZABILIDAD POR EQUIPO","")),format.raw/*13.61*/("""
		
		"""),format.raw/*15.3*/("""<div class="noprint">
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
							<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
								Exportar a Excel
							</button>
							<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
								Imprimir
							</button>
							
							<button type="button"  class="btn btn-sm btn-success" onclick="history.go(-1);return false;">
								Volver
							</button>
						</div>
					</td>
					<td width="25%">
					</div>
						
					</td>
				</tr>
			</table>
		</div>
		
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
			        <TR> 
						<TH style= "text-align: center;vertical-align: top;">FECHA</TH>
						<TH style= "text-align: center;vertical-align: top;">NRO. COTI</TH>
				        <TH style= "text-align: center;vertical-align: top;">NRO. MOV</TH>
				        <TH style= "text-align: center;vertical-align: top;">NRO. REF</TH>
				        <TH style= "text-align: center;vertical-align: top;">TIPO</TH>
				        <TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
						<TH style= "text-align: center;vertical-align: top;">DESDE</TH>
						<TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
						<TH style= "text-align: center;vertical-align: top;">HASTA</TH>
						<TH style= "text-align: center;vertical-align: top;">CODIGO</TH>
						<TH style= "text-align: center;vertical-align: top;">EQUIPO</TH>
						<TH  style= "text-align: center;vertical-align: top;" width="8%">CANTIDAD</TH>
					</TR>
				</thead>
				<tbody>
					"""),_display_(/*69.7*/for(lista1 <- lista) yield /*69.27*/{_display_(Seq[Any](format.raw/*69.28*/("""
						"""),format.raw/*70.7*/("""<TR>
							<td style= "text-align: center;">
								<div style="display:none">"""),_display_(/*72.36*/utilities/*72.45*/.Fechas.AAMMDD(lista1.get(0))),format.raw/*72.74*/("""</div>
								"""),_display_(/*73.10*/lista1/*73.16*/.get(0)),format.raw/*73.23*/("""
							"""),format.raw/*74.8*/("""</td>
							<td style= "text-align: center;">"""),_display_(/*75.42*/lista1/*75.48*/.get(11)),format.raw/*75.56*/("""</td>
							<td style= "text-align: center;">"""),_display_(/*76.42*/lista1/*76.48*/.get(6)),format.raw/*76.55*/("""</td>
							<td style= "text-align: center;">"""),_display_(/*77.42*/lista1/*77.48*/.get(8)),format.raw/*77.55*/("""</td>
							<td style= "text-align: center;">"""),_display_(/*78.42*/lista1/*78.48*/.get(7)),format.raw/*78.55*/("""</td>
							
							<td style= "text-align: left;">"""),_display_(/*80.40*/lista1/*80.46*/.get(9)),format.raw/*80.53*/("""</td>
							<td style= "text-align: left;"><a href="#" onclick="buscaBodega('"""),_display_(/*81.74*/lista1/*81.80*/.get(1)),format.raw/*81.87*/("""')">"""),_display_(/*81.92*/lista1/*81.98*/.get(1)),format.raw/*81.105*/("""</a></td>
							<td style= "text-align: left;">"""),_display_(/*82.40*/lista1/*82.46*/.get(10)),format.raw/*82.54*/("""</td>
							<td style= "text-align: left;"><a href="#" onclick="buscaBodega('"""),_display_(/*83.74*/lista1/*83.80*/.get(2)),format.raw/*83.87*/("""')">"""),_display_(/*83.92*/lista1/*83.98*/.get(2)),format.raw/*83.105*/("""</a></td>
							
							<td style= "text-align: center;"><a href="#" onclick="buscaEquipo('"""),_display_(/*85.76*/lista1/*85.82*/.get(3)),format.raw/*85.89*/("""');">"""),_display_(/*85.95*/lista1/*85.101*/.get(3)),format.raw/*85.108*/("""</a></td>
							<td style= "text-align: left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*86.74*/lista1/*86.80*/.get(3)),format.raw/*86.87*/("""');">"""),_display_(/*86.93*/lista1/*86.99*/.get(4)),format.raw/*86.106*/("""</a></td>
							
							<td style= "text-align: right;">"""),_display_(/*88.41*/lista1/*88.47*/.get(5)),format.raw/*88.54*/("""</td>
						</TR>
		 			""")))}),format.raw/*90.8*/("""
				"""),format.raw/*91.5*/("""</tbody>
			</table>
		</div>
		
		<form id="excel" class="formulario" method="post" action="/reportTrazaEquipo2Excel/">
			<input type="hidden" name="id_equipo" value=""""),_display_(/*96.50*/id_equipo),format.raw/*96.59*/("""">
		</form>
		
	</div>
	

""")))}),format.raw/*102.2*/("""


"""),format.raw/*105.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*106.31*/("""{"""),format.raw/*106.32*/("""
		 """),format.raw/*107.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*107.35*/("""{"""),format.raw/*107.36*/("""
		    	"""),format.raw/*108.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 0, "desc" ],[ 1, "desc" ]],
		    	"language": """),format.raw/*113.20*/("""{"""),format.raw/*113.21*/("""
		        	"""),format.raw/*114.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*115.11*/("""}"""),format.raw/*115.12*/("""
		  """),format.raw/*116.5*/("""}"""),format.raw/*116.6*/(""" """),format.raw/*116.7*/(""");
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*118.2*/("""}"""),format.raw/*118.3*/(""");
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],id_equipo:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,id_equipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,id_equipo) => apply(mapDiccionario,mapPermiso,userMnu,lista,id_equipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportTrazaEquipo2.scala.html
                  HASH: 69c7fcf48bc28a6b4d279b40fd30b0e1c872859a
                  MATRIX: 1042->1|1276->142|1309->150|1325->158|1364->160|1393->164|1461->212|1491->217|1535->241|1564->244|1620->279|1651->283|1728->334|1806->391|1839->397|4052->2584|4088->2604|4127->2605|4161->2612|4269->2693|4287->2702|4337->2731|4380->2747|4395->2753|4423->2760|4458->2768|4532->2815|4547->2821|4576->2829|4650->2876|4665->2882|4693->2889|4767->2936|4782->2942|4810->2949|4884->2996|4899->3002|4927->3009|5007->3062|5022->3068|5050->3075|5156->3154|5171->3160|5199->3167|5231->3172|5246->3178|5275->3185|5351->3234|5366->3240|5395->3248|5501->3327|5516->3333|5544->3340|5576->3345|5591->3351|5620->3358|5740->3451|5755->3457|5783->3464|5816->3470|5832->3476|5861->3483|5971->3566|5986->3572|6014->3579|6047->3585|6062->3591|6091->3598|6176->3656|6191->3662|6219->3669|6274->3694|6306->3699|6503->3869|6533->3878|6592->3906|6623->3909|6714->3971|6744->3972|6776->3976|6836->4007|6866->4008|6902->4016|7085->4170|7115->4171|7156->4183|7263->4261|7293->4262|7326->4267|7355->4268|7384->4269|7483->4340|7512->4341
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|43->12|44->13|44->13|46->15|100->69|100->69|100->69|101->70|103->72|103->72|103->72|104->73|104->73|104->73|105->74|106->75|106->75|106->75|107->76|107->76|107->76|108->77|108->77|108->77|109->78|109->78|109->78|111->80|111->80|111->80|112->81|112->81|112->81|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|114->83|114->83|114->83|116->85|116->85|116->85|116->85|116->85|116->85|117->86|117->86|117->86|117->86|117->86|117->86|119->88|119->88|119->88|121->90|122->91|127->96|127->96|133->102|136->105|137->106|137->106|138->107|138->107|138->107|139->108|144->113|144->113|145->114|146->115|146->115|147->116|147->116|147->116|149->118|149->118
                  -- GENERATED --
              */
          