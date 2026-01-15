
package viewsMnuCotiOdo.html

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

object revisarOtOdo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,Long,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
id_otOdo: Long, tabla: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "DETALLE Y ESTADO DE "+mapDiccionario.get("ORDEN_DE_TRABAJO").toUpperCase()+" ODO", "")),format.raw/*8.119*/("""
		"""),format.raw/*9.3*/("""<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  value="Imprimir" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.print(); return false;">
				</div>
				<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">
					<form action="/routes2/otOdoRevisarExcel/" method="POST">
						<input type="hidden" name="id_otOdo" value=""""),_display_(/*16.52*/id_otOdo),format.raw/*16.60*/("""">
						<input type="submit" class="btn btn-info btn-sm rounded-pill btn-block" value="Excel">
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
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/otOdoListaRevisar/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalVerTrazaOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>TRAZABILIDAD &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h5>
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
		
	
	
""")))}),format.raw/*68.2*/("""



"""),format.raw/*72.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*74.31*/("""{"""),format.raw/*74.32*/("""
		"""),format.raw/*75.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*76.2*/("""}"""),format.raw/*76.3*/(""");
	
	const trazaOt = (id_otOdo, id_servicio) =>"""),format.raw/*78.44*/("""{"""),format.raw/*78.45*/("""
		"""),format.raw/*79.3*/("""var formData = new FormData();
	  	formData.append('id_otOdo',id_otOdo);
		formData.append('id_servicio',id_servicio);
        $.ajax("""),format.raw/*82.16*/("""{"""),format.raw/*82.17*/("""
            """),format.raw/*83.13*/("""url: "/routes2/trazaServicioOtOdoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*90.36*/("""{"""),format.raw/*90.37*/("""
	     		"""),format.raw/*91.9*/("""document.getElementById('mostrarTrazaOt').innerHTML = respuesta;
	     		$('#modalVerTrazaOt').modal('show');
	     	"""),format.raw/*93.8*/("""}"""),format.raw/*93.9*/("""
        """),format.raw/*94.9*/("""}"""),format.raw/*94.10*/(""");
	"""),format.raw/*95.2*/("""}"""),format.raw/*95.3*/("""
	
	
	


	
"""),format.raw/*102.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,id_otOdo:Long,tabla:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,id_otOdo,tabla)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,Long,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,id_otOdo,tabla) => apply(mapDiccionario,mapPermiso,userMnu,id_otOdo,tabla)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotiOdo/revisarOtOdo.scala.html
                  HASH: a061ae873d58adfe35a7b59fa99472cc72cf4532
                  MATRIX: 1023->1|1244->129|1271->131|1287->139|1326->141|1355->145|1423->193|1451->195|1527->246|1663->361|1692->364|2174->819|2203->827|2522->1120|2554->1131|2586->1136|4221->2741|4252->2745|4343->2808|4372->2809|4402->2812|4495->2878|4523->2879|4599->2927|4628->2928|4658->2931|4820->3065|4849->3066|4890->3079|5161->3322|5190->3323|5226->3332|5370->3449|5398->3450|5434->3459|5463->3460|5494->3464|5522->3465|5561->3476
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|47->16|47->16|59->28|59->28|60->29|99->68|103->72|105->74|105->74|106->75|107->76|107->76|109->78|109->78|110->79|113->82|113->82|114->83|121->90|121->90|122->91|124->93|124->93|125->94|125->94|126->95|126->95|133->102
                  -- GENERATED --
              */
          