
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

object reportOtPrecios1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listTablas: List[String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "DETALLE Y ESTADO DE "+mapDiccionario.get("ORDEN_DE_TRABAJO").toUpperCase(), bodega.getNombre().toUpperCase())),format.raw/*8.142*/("""
		"""),format.raw/*9.3*/("""<!--
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  value="Imprimir" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.print(); return false;">
				</div>
				<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1" onsubmit="return validarForm();">
					<form action="/routes2/otAdminPrecios1Excel/" method="POST">
						<input type="hidden" name="id_bodega" value=""""),_display_(/*17.53*/bodega/*17.59*/.getId()),format.raw/*17.67*/("""">
						<input type="submit" id="btnSubmit" class="btn btn-info btn-sm rounded-pill btn-block" value="Excel">
					</form>
					
				</div>
			</div>
			<br><br>
		</div>
		-->
		<div id="element">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					"""),_display_(/*29.7*/for(tabla <- listTablas) yield /*29.31*/{_display_(Seq[Any](format.raw/*29.32*/("""
						
						"""),format.raw/*31.7*/("""<hr class="border border-primary border-3 ">
						"""),_display_(/*32.8*/Html(tabla)),format.raw/*32.19*/("""
						
					""")))}),format.raw/*34.7*/("""
					"""),format.raw/*35.6*/("""<hr class="border border-primary border-3 ">
				</div>
			</div>
		</div>
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/otListaRevisarPeriodo/';">
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
	
	
""")))}),format.raw/*95.2*/("""



"""),format.raw/*99.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*101.31*/("""{"""),format.raw/*101.32*/("""
		"""),format.raw/*102.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*103.2*/("""}"""),format.raw/*103.3*/(""");
	
	const trazaOt = (id_ot, id_equipoOrigen) =>"""),format.raw/*105.45*/("""{"""),format.raw/*105.46*/("""
		"""),format.raw/*106.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
		formData.append('id_equipoOrigen',id_equipoOrigen);
        $.ajax("""),format.raw/*109.16*/("""{"""),format.raw/*109.17*/("""
            """),format.raw/*110.13*/("""url: "/routes2/trazaEqOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*117.36*/("""{"""),format.raw/*117.37*/("""
	     		"""),format.raw/*118.9*/("""document.getElementById('mostrarTrazaOt').innerHTML = respuesta;
				let codOrigen = $("#codEq_"+id_equipoOrigen).text();
				let nomOrigen = $("#nomEq_"+id_equipoOrigen).text();
				document.getElementById('detOrigen').innerHTML = "Equipo de origen: "+ codOrigen + " - " +nomOrigen;



	     		$('#modalVerTrazaOt').modal('show');
	     	"""),format.raw/*126.8*/("""}"""),format.raw/*126.9*/("""
        """),format.raw/*127.9*/("""}"""),format.raw/*127.10*/(""");
	"""),format.raw/*128.2*/("""}"""),format.raw/*128.3*/("""
	
	"""),format.raw/*130.2*/("""const vistaStockPorEquipo = (id_equipo) =>"""),format.raw/*130.44*/("""{"""),format.raw/*130.45*/("""
		"""),format.raw/*131.3*/("""var formData = new FormData();
		formData.append('id_equipo',id_equipo);
        $.ajax("""),format.raw/*133.16*/("""{"""),format.raw/*133.17*/("""
            """),format.raw/*134.13*/("""url: "/tablaInvPorEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*141.36*/("""{"""),format.raw/*141.37*/("""
				"""),format.raw/*142.5*/("""document.getElementById('stockPorEquipo').innerHTML = respuesta;
				$('#modalStockPorEquipo').modal('show');
	     	"""),format.raw/*144.8*/("""}"""),format.raw/*144.9*/("""
        """),format.raw/*145.9*/("""}"""),format.raw/*145.10*/(""");
	"""),format.raw/*146.2*/("""}"""),format.raw/*146.3*/("""
	
	"""),format.raw/*148.2*/("""const validarForm = () =>"""),format.raw/*148.27*/("""{"""),format.raw/*148.28*/("""
		"""),format.raw/*149.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*151.2*/("""}"""),format.raw/*151.3*/("""
	
	


	
"""),format.raw/*157.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listTablas:List[String]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listTablas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[String]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listTablas) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listTablas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/reportOtPrecios1.scala.html
                  HASH: 64ae420122a27001c631a0cd828fcadbb16150af
                  MATRIX: 1049->1|1295->154|1322->156|1338->164|1377->166|1406->170|1474->218|1502->220|1578->271|1737->409|1766->412|2292->911|2307->917|2336->925|2673->1236|2713->1260|2752->1261|2793->1275|2871->1327|2903->1338|2947->1352|2980->1358|5510->3858|5541->3862|5633->3925|5663->3926|5694->3929|5788->3995|5817->3996|5895->4045|5925->4046|5956->4049|6121->4185|6151->4186|6193->4199|6456->4433|6486->4434|6523->4443|6890->4782|6919->4783|6956->4792|6986->4793|7018->4797|7047->4798|7079->4802|7150->4844|7180->4845|7211->4848|7328->4936|7358->4937|7400->4950|7663->5184|7693->5185|7726->5190|7871->5307|7900->5308|7937->5317|7967->5318|7999->5322|8028->5323|8060->5327|8114->5352|8144->5353|8175->5356|8259->5412|8288->5413|8325->5422
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|48->17|48->17|48->17|60->29|60->29|60->29|62->31|63->32|63->32|65->34|66->35|126->95|130->99|132->101|132->101|133->102|134->103|134->103|136->105|136->105|137->106|140->109|140->109|141->110|148->117|148->117|149->118|157->126|157->126|158->127|158->127|159->128|159->128|161->130|161->130|161->130|162->131|164->133|164->133|165->134|172->141|172->141|173->142|175->144|175->144|176->145|176->145|177->146|177->146|179->148|179->148|179->148|180->149|182->151|182->151|188->157
                  -- GENERATED --
              */
          