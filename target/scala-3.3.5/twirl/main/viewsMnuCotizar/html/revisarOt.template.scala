
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

object revisarOt extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,Long,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
id_ot: Long, tabla: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "DETALLE Y ESTADO DE "+mapDiccionario.get("ORDEN_DE_TRABAJO").toUpperCase(), "")),format.raw/*8.112*/("""
		"""),format.raw/*9.3*/("""<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  value="Imprimir" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.print(); return false;">
				</div>
				<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1" onsubmit="return validarForm();">
					<form action="/routes2/otRevisarExcel/" method="POST">
						<input type="hidden" name="id_ot" value=""""),_display_(/*16.49*/id_ot),format.raw/*16.54*/("""">
						<input type="submit" id="btnSubmit" class="btn btn-info btn-sm rounded-pill btn-block" value="Excel">
					</form>
					
				</div>
			</div>
			<br><br>
		</div>
		
		<div id="element">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					"""),_display_(/*28.7*/Html(tabla)),format.raw/*28.18*/("""
				"""),format.raw/*29.5*/("""</div>
			</div>
		</div>
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="window.history.back();">
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalVerTrazaOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>TRAZABILIDAD DEL DESPACHO<div id="detOrigen"></div></h5>
						<input type="button"  value="Imprimir" class="btn btn-primary btn-sm rounded-pill" onclick="document.getElementById('mostrarmostrar').style.display='none'; window.print(); return false;">
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close' onclick="document.getElementById('mostrarmostrar').style.display='block'">
				          <span aria-hidden='true'>&times;</span>
				        </button>

				</div>
				<div class='modal-body'>
					<div id='mostrarTrazaOt'></div>
					<div class="noprint">
		   				<div class='row'>
							<div class='col-sm-12' style='text-align:center'>
								<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal' onclick="document.getElementById('mostrarmostrar').style.display='block'">Listo</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		
	<div id="modalStockPorEquipo" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class='modal-dialog modal-dialog-scrollable' style='max-width: 80% !important;' role='document'>
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">STOCK DISPONIBLE</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				</div>
				<div class="modal-body">
					<div id="stockPorEquipo"></div>
	   				<div class="row">
						<div class="col-sm-12" style="text-align:center">
							<button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" data-dismiss="modal">Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
""")))}),format.raw/*88.2*/("""



"""),format.raw/*92.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*94.31*/("""{"""),format.raw/*94.32*/("""
		"""),format.raw/*95.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*96.2*/("""}"""),format.raw/*96.3*/(""");
	
	const trazaOt = (id_ot, id_equipoOrigen) =>"""),format.raw/*98.45*/("""{"""),format.raw/*98.46*/("""
		"""),format.raw/*99.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
		formData.append('id_equipoOrigen',id_equipoOrigen);
        $.ajax("""),format.raw/*102.16*/("""{"""),format.raw/*102.17*/("""
            """),format.raw/*103.13*/("""url: "/routes2/trazaEqOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*110.36*/("""{"""),format.raw/*110.37*/("""
	     		"""),format.raw/*111.9*/("""document.getElementById('mostrarTrazaOt').innerHTML = respuesta;
				let codOrigen = $("#codEq_"+id_equipoOrigen).text();
				let nomOrigen = $("#nomEq_"+id_equipoOrigen).text();
				document.getElementById('detOrigen').innerHTML = "Equipo de origen: "+ codOrigen + " - " +nomOrigen;



	     		$('#modalVerTrazaOt').modal('show');
	     	"""),format.raw/*119.8*/("""}"""),format.raw/*119.9*/("""
        """),format.raw/*120.9*/("""}"""),format.raw/*120.10*/(""");
	"""),format.raw/*121.2*/("""}"""),format.raw/*121.3*/("""
	
	"""),format.raw/*123.2*/("""const vistaStockPorEquipo = (id_equipo) =>"""),format.raw/*123.44*/("""{"""),format.raw/*123.45*/("""
		"""),format.raw/*124.3*/("""var formData = new FormData();
		formData.append('id_equipo',id_equipo);
        $.ajax("""),format.raw/*126.16*/("""{"""),format.raw/*126.17*/("""
            """),format.raw/*127.13*/("""url: "/tablaInvPorEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*134.36*/("""{"""),format.raw/*134.37*/("""
				"""),format.raw/*135.5*/("""document.getElementById('stockPorEquipo').innerHTML = respuesta;
				$('#modalStockPorEquipo').modal('show');
	     	"""),format.raw/*137.8*/("""}"""),format.raw/*137.9*/("""
        """),format.raw/*138.9*/("""}"""),format.raw/*138.10*/(""");
	"""),format.raw/*139.2*/("""}"""),format.raw/*139.3*/("""
	
	"""),format.raw/*141.2*/("""const validarForm = () =>"""),format.raw/*141.27*/("""{"""),format.raw/*141.28*/("""
		"""),format.raw/*142.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*144.2*/("""}"""),format.raw/*144.3*/("""


	
"""),format.raw/*148.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,id_ot:Long,tabla:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,id_ot,tabla)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,Long,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,id_ot,tabla) => apply(mapDiccionario,mapPermiso,userMnu,id_ot,tabla)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/revisarOt.scala.html
                  HASH: 9eb457edddb7b93c1196e4f12118acb265f6e266
                  MATRIX: 1020->1|1238->126|1265->128|1281->136|1320->138|1349->142|1417->190|1445->192|1521->243|1650->351|1679->354|2188->836|2214->841|2548->1149|2580->1160|2612->1165|5067->3590|5098->3594|5189->3657|5218->3658|5248->3661|5341->3727|5369->3728|5446->3777|5475->3778|5505->3781|5670->3917|5700->3918|5742->3931|6005->4165|6035->4166|6072->4175|6439->4514|6468->4515|6505->4524|6535->4525|6567->4529|6596->4530|6628->4534|6699->4576|6729->4577|6760->4580|6877->4668|6907->4669|6949->4682|7212->4916|7242->4917|7275->4922|7420->5039|7449->5040|7486->5049|7516->5050|7548->5054|7577->5055|7609->5059|7663->5084|7693->5085|7724->5088|7808->5144|7837->5145|7870->5150
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|47->16|47->16|59->28|59->28|60->29|119->88|123->92|125->94|125->94|126->95|127->96|127->96|129->98|129->98|130->99|133->102|133->102|134->103|141->110|141->110|142->111|150->119|150->119|151->120|151->120|152->121|152->121|154->123|154->123|154->123|155->124|157->126|157->126|158->127|165->134|165->134|166->135|168->137|168->137|169->138|169->138|170->139|170->139|172->141|172->141|172->141|173->142|175->144|175->144|179->148
                  -- GENERATED --
              */
          