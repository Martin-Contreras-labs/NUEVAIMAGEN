
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

object reportOTSaldosPorOt extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
tablaHtml: String, fechaHoy: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""
	
	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	

	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "SALDOS POR DESPACHAR POR OT (SOLO " + mapDiccionario.get("BODEGAS") + " VIGENTES) VS STOCK EN " + mapDiccionario.get("BODEGAS") + " INTERNAS", "FECHA: " + fechaHoy)),format.raw/*11.197*/("""
		
		
		"""),format.raw/*14.3*/("""<div class="noprint">
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
			"""),_display_(/*46.5*/Html(tablaHtml)),format.raw/*46.20*/("""
		"""),format.raw/*47.3*/("""</div>
	</div>
	
	<form id="excel" class="formulario" method="post" action="/routes2/reportOtSaldosPorOtExcel/">
	</form>
	
	
	<div id='listaOT' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>LISTADO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="listadoOT"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*75.2*/("""


"""),format.raw/*78.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*80.31*/("""{"""),format.raw/*80.32*/("""
			"""),format.raw/*81.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*82.2*/("""}"""),format.raw/*82.3*/(""");
	
	$('#tablaPrincipal').DataTable("""),format.raw/*84.33*/("""{"""),format.raw/*84.34*/("""
    	"""),format.raw/*85.6*/(""""fixedHeader": false,
    	"paging": false,
		"info": false,
		"searching": false,
    	"order": [[ 2, "asc" ]],
    	"language": """),format.raw/*90.18*/("""{"""),format.raw/*90.19*/("""
        	"""),format.raw/*91.10*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
        """),format.raw/*92.9*/("""}"""),format.raw/*92.10*/("""
  	"""),format.raw/*93.4*/("""}"""),format.raw/*93.5*/(""" """),format.raw/*93.6*/(""");
	
	
	const muestraOT = (id_bodegaOrigen, id_equipo) =>"""),format.raw/*96.51*/("""{"""),format.raw/*96.52*/("""
			"""),format.raw/*97.4*/("""var formData = new FormData();
		  	formData.append('id_bodegaOrigen',id_bodegaOrigen);
			formData.append('id_equipo',id_equipo);
	        $.ajax("""),format.raw/*100.17*/("""{"""),format.raw/*100.18*/("""
	            """),format.raw/*101.14*/("""url: "/routes2/listadoOTAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*108.37*/("""{"""),format.raw/*108.38*/("""
		     		"""),format.raw/*109.10*/("""$("#listadoOT").html(respuesta);
					$("#listaOT").modal('show');;
		     	"""),format.raw/*111.9*/("""}"""),format.raw/*111.10*/("""
	        """),format.raw/*112.10*/("""}"""),format.raw/*112.11*/(""");
	"""),format.raw/*113.2*/("""}"""),format.raw/*113.3*/("""
	
	
"""),format.raw/*116.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,tablaHtml:String,fechaHoy:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,tablaHtml,fechaHoy)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,tablaHtml,fechaHoy) => apply(mapDiccionario,mapPermiso,userMnu,tablaHtml,fechaHoy)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/reportOTSaldosPorOt.scala.html
                  HASH: 6ca3099df13fe402b72f641ed655f292c07d0aed
                  MATRIX: 1032->1|1259->135|1291->142|1307->150|1346->152|1376->157|1444->205|1478->212|1555->263|1770->456|1806->465|2861->1494|2897->1509|2927->1512|3867->2422|3897->2425|3988->2488|4017->2489|4048->2493|4141->2559|4169->2560|4234->2597|4263->2598|4296->2604|4454->2734|4483->2735|4521->2745|4624->2821|4653->2822|4684->2826|4712->2827|4740->2828|4825->2885|4854->2886|4885->2890|5061->3037|5091->3038|5134->3052|5404->3293|5434->3294|5473->3304|5577->3380|5607->3381|5646->3391|5676->3392|5708->3396|5737->3397|5770->3402
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|41->10|42->11|42->11|45->14|77->46|77->46|78->47|106->75|109->78|111->80|111->80|112->81|113->82|113->82|115->84|115->84|116->85|121->90|121->90|122->91|123->92|123->92|124->93|124->93|124->93|127->96|127->96|128->97|131->100|131->100|132->101|139->108|139->108|140->109|142->111|142->111|143->112|143->112|144->113|144->113|147->116
                  -- GENERATED --
              */
          