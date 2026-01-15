
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

object reportInventarioMatriz extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[String],List[String],String,String,tables.Sucursal,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], titulos1: List[String], titulos2: List[String], tipo: String, fechaCorte: String, sucursal: tables.Sucursal):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalEquipoDescripcion()),format.raw/*8.27*/("""
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "MATRIZ DE INVENTARIO - "+tipo+" - (SOLO EQUIPOS Y " + mapDiccionario.get("BODEGA") + "/PROYECTO CON STOCK)",
			"FECHA DE CORTE: " + utilities.Fechas.DDMMAA(fechaCorte) + " -- (SUCURSAL: " + sucursal.getNombre().toUpperCase() + ")")),format.raw/*13.124*/("""
		
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
								onkeyup="doSearch3('tablaPrincipal');">
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
				        	<th></th>
				        	<th></th>
				        	<th></th>
							<th></th>
				        	<th>PESO</th>
				        	<th>SUP.</th>
				        	<th>CANT</th>
				        	"""),_display_(/*57.15*/for(lista1 <- titulos1) yield /*57.38*/{_display_(Seq[Any](format.raw/*57.39*/("""
								"""),_display_(/*58.10*/Html(lista1)),format.raw/*58.22*/("""
							""")))}),format.raw/*59.9*/("""
							"""),_display_(if(titulos2.size()>1)/*60.30*/{_display_(Seq[Any](format.raw/*60.31*/("""
								"""),format.raw/*61.9*/("""<th></th>
							""")))} else {null} ),format.raw/*62.9*/("""
						"""),format.raw/*63.7*/("""</TR>
						<TR>
				        	<th>GRUPO</th>
							<th>PROPIEDAD</th>
				        	<th>CODIGO</th>
				        	<th>EQUIPO</th>
				        	<th>KG</th>
				        	<th>M2</th>
				        	<th>TOTAL</th>
				        	"""),_display_(/*72.15*/for(lista2 <- titulos2) yield /*72.38*/{_display_(Seq[Any](format.raw/*72.39*/("""
				        		"""),format.raw/*73.15*/("""<th style="text-align:center; white-space: nowrap;vertical-align: middle">
									<div style="display: flex;flex-direction: row;">
										<span style="writing-mode: vertical-lr;transform: rotate(180deg);width: 23px;">
											"""),_display_(if(lista2.length>25)/*76.33*/{_display_(Seq[Any](format.raw/*76.34*/("""
												"""),format.raw/*77.13*/("""<a href="#" onclick="buscaBodega('"""),_display_(/*77.48*/lista2),format.raw/*77.54*/("""')">"""),_display_(/*77.59*/lista2/*77.65*/.substring(0,25)),format.raw/*77.81*/("""</a>
											""")))}else/*78.17*/{_display_(Seq[Any](format.raw/*78.18*/("""
												"""),format.raw/*79.13*/("""<a href="#" onclick="buscaBodega('"""),_display_(/*79.48*/lista2),format.raw/*79.54*/("""')">"""),_display_(/*79.59*/lista2),format.raw/*79.65*/("""</a>
											""")))}),format.raw/*80.13*/("""
				        					
										"""),format.raw/*82.11*/("""</span>
									</div>
				        		</th>
							""")))}),format.raw/*85.9*/("""
							"""),format.raw/*86.8*/("""<th>TOTAL</th>
						</TR>
				</thead>
				<tbody>
					"""),_display_(/*90.7*/for(lista3 <- lista) yield /*90.27*/{_display_(Seq[Any](format.raw/*90.28*/("""
						"""),format.raw/*91.7*/("""<TR>
							<td style="text-align:left;min-width:150px">"""),_display_(/*92.53*/lista3/*92.59*/.get(0)),format.raw/*92.66*/("""</td>
							<td style="text-align:left;min-width:150px">"""),_display_(/*93.53*/lista3/*93.59*/.get(1)),format.raw/*93.66*/("""</td>
							<td style="text-align:left;min-width:80px"><a href="#" onclick="buscaEquipo('"""),_display_(/*94.86*/lista3/*94.92*/.get(2)),format.raw/*94.99*/("""');">"""),_display_(/*94.105*/lista3/*94.111*/.get(2)),format.raw/*94.118*/("""</a></td>
							<td style="text-align:left;min-width:300px"><a href="#" onclick="buscaEquipo('"""),_display_(/*95.87*/lista3/*95.93*/.get(2)),format.raw/*95.100*/("""');">"""),_display_(/*95.106*/lista3/*95.112*/.get(3)),format.raw/*95.119*/("""</a></td>
							"""),_display_(/*96.9*/for(lista4 <- lista3.tail.tail.tail.tail) yield /*96.50*/{_display_(Seq[Any](format.raw/*96.51*/("""
								"""),_display_(if(lista4.equals("0") || lista4.trim().equals(""))/*97.60*/{_display_(Seq[Any](format.raw/*97.61*/("""
									"""),format.raw/*98.10*/("""<td style="text-align:center">"""),_display_(/*98.41*/lista4),format.raw/*98.47*/("""</td>
								""")))}else/*99.14*/{_display_(Seq[Any](format.raw/*99.15*/("""
									"""),format.raw/*100.10*/("""<td style="text-align:center; background-color: #ABEBC6">"""),_display_(/*100.68*/lista4),format.raw/*100.74*/("""</td>
								""")))}),format.raw/*101.10*/("""
								
							""")))}),format.raw/*103.9*/("""
						"""),format.raw/*104.7*/("""</TR>
					""")))}),format.raw/*105.7*/("""
				"""),format.raw/*106.5*/("""</tbody>
			</table>
		</div>
	</div>
	
	<form id="excel" class="formulario" method="post" action="/reportInventarioMatrizExcel/">
		<input type="hidden" name="fechaCorte" value=""""),_display_(/*112.50*/fechaCorte),format.raw/*112.60*/("""">
		<input type="hidden" name="tipo" value=""""),_display_(/*113.44*/tipo),format.raw/*113.48*/("""">
		<input type="hidden" name="id_sucursal" value=""""),_display_(/*114.51*/sucursal/*114.59*/.getId()),format.raw/*114.67*/("""">
	</form>

""")))}),format.raw/*117.2*/("""


"""),format.raw/*120.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*122.31*/("""{"""),format.raw/*122.32*/("""
			"""),format.raw/*123.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*124.2*/("""}"""),format.raw/*124.3*/(""");
	
	$('#tablaPrincipal').DataTable("""),format.raw/*126.33*/("""{"""),format.raw/*126.34*/("""
    	"""),format.raw/*127.6*/(""""fixedHeader": true,
    	"paging": false,
		"info": false,
		"searching": false,
    	"order": [[ 2, "asc" ]],
    	"language": """),format.raw/*132.18*/("""{"""),format.raw/*132.19*/("""
        	"""),format.raw/*133.10*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
        """),format.raw/*134.9*/("""}"""),format.raw/*134.10*/("""
  """),format.raw/*135.3*/("""}"""),format.raw/*135.4*/(""" """),format.raw/*135.5*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],titulos1:List[String],titulos2:List[String],tipo:String,fechaCorte:String,sucursal:tables.Sucursal): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,titulos1,titulos2,tipo,fechaCorte,sucursal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[String],List[String],String,String,tables.Sucursal) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,titulos1,titulos2,tipo,fechaCorte,sucursal) => apply(mapDiccionario,mapPermiso,userMnu,lista,titulos1,titulos2,tipo,fechaCorte,sucursal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportInventarioMatriz.scala.html
                  HASH: f3fc154922ca9afb3bab3e7fa106890b0801cefc
                  MATRIX: 1097->1|1423->234|1455->241|1471->249|1510->251|1539->255|1607->303|1637->308|1681->332|1709->335|1764->370|1795->374|1872->425|2155->686|2188->692|3594->2071|3633->2094|3672->2095|3709->2105|3742->2117|3781->2126|3838->2156|3877->2157|3913->2166|3974->2184|4008->2191|4256->2412|4295->2435|4334->2436|4377->2451|4659->2706|4698->2707|4739->2720|4801->2755|4828->2761|4860->2766|4875->2772|4912->2788|4952->2809|4991->2810|5032->2823|5094->2858|5121->2864|5153->2869|5180->2875|5228->2892|5285->2921|5367->2973|5402->2981|5486->3039|5522->3059|5561->3060|5595->3067|5679->3124|5694->3130|5722->3137|5807->3195|5822->3201|5850->3208|5968->3299|5983->3305|6011->3312|6045->3318|6061->3324|6090->3331|6213->3427|6228->3433|6257->3440|6291->3446|6307->3452|6336->3459|6380->3477|6437->3518|6476->3519|6563->3579|6602->3580|6640->3590|6698->3621|6725->3627|6763->3646|6802->3647|6841->3657|6927->3715|6955->3721|7002->3736|7051->3754|7086->3761|7129->3773|7162->3778|7370->3958|7402->3968|7476->4014|7502->4018|7583->4071|7601->4079|7631->4087|7676->4101|7707->4104|7799->4167|7829->4168|7861->4172|7955->4238|7984->4239|8050->4276|8080->4277|8114->4283|8272->4412|8302->4413|8341->4423|8445->4499|8475->4500|8506->4503|8535->4504|8564->4505
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|42->11|43->12|44->13|46->15|88->57|88->57|88->57|89->58|89->58|90->59|91->60|91->60|92->61|93->62|94->63|103->72|103->72|103->72|104->73|107->76|107->76|108->77|108->77|108->77|108->77|108->77|108->77|109->78|109->78|110->79|110->79|110->79|110->79|110->79|111->80|113->82|116->85|117->86|121->90|121->90|121->90|122->91|123->92|123->92|123->92|124->93|124->93|124->93|125->94|125->94|125->94|125->94|125->94|125->94|126->95|126->95|126->95|126->95|126->95|126->95|127->96|127->96|127->96|128->97|128->97|129->98|129->98|129->98|130->99|130->99|131->100|131->100|131->100|132->101|134->103|135->104|136->105|137->106|143->112|143->112|144->113|144->113|145->114|145->114|145->114|148->117|151->120|153->122|153->122|154->123|155->124|155->124|157->126|157->126|158->127|163->132|163->132|164->133|165->134|165->134|166->135|166->135|166->135
                  -- GENERATED --
              */
          