
package views.html

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

object muestraAlbumFotos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, listado: List[List[String]], carpeta: String, titulo: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""

"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""
	"""),_display_(/*5.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*5.51*/("""
	"""),_display_(/*6.3*/barraTitulo(mapDiccionario, "IMAGENES: "+titulo+carpeta, "Manejo y Administracion de Activos")),format.raw/*6.97*/("""
	
	"""),format.raw/*8.2*/("""<div class="row  justify-content-md-center">
		<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
			<div class="noprint">
				<input type="button" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.print(); return false;" value="Imprimir">
			</div>
			<br>
		</div>
		<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
			<div class="noprint">
				<form action="/muestraAlbumFotosWord/" method="POST" onsubmit="return validarForm();">
					<input type="hidden" name="carpeta" value=""""),_display_(/*18.50*/carpeta),format.raw/*18.57*/("""">
					<input type="submit" id="btnSubmit" class="btn btn-info btn-sm rounded-pill btn-block" value="Exportar a Word">
				</form>
			</div>
			<br>
		</div>
	</div>
			

	<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
		<tbody>
				"""),_display_(/*29.6*/for(lista1 <- listado) yield /*29.28*/{_display_(Seq[Any](format.raw/*29.29*/("""
					"""),format.raw/*30.6*/("""<TR>
						"""),_display_(/*31.8*/for(lista2 <- lista1) yield /*31.29*/{_display_(Seq[Any](format.raw/*31.30*/("""
							"""),format.raw/*32.8*/("""<td style="text-align:center">
								<a href="#" onclick="selectModal('"""),_display_(/*33.44*/lista2),format.raw/*33.50*/("""','"""),_display_(/*33.54*/carpeta),format.raw/*33.61*/("""')">
									<img src='/viewImgAlbum/"""),_display_(/*34.35*/lista2),format.raw/*34.41*/(""","""),_display_(/*34.43*/carpeta),format.raw/*34.50*/("""' style="max-height:150px;max-width:100%">
									<br>
									"""),_display_(/*36.11*/lista2),format.raw/*36.17*/("""
								"""),format.raw/*37.9*/("""</a>
							</td>
						""")))}),format.raw/*39.8*/("""
					"""),format.raw/*40.6*/("""</TR>
				""")))}),format.raw/*41.6*/("""
		"""),format.raw/*42.3*/("""</tbody>
	</table>
	<div class="noprint">
		<div class="row justify-content-md-center" >
			<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
				<input type="button"  value="VOLVER" class="btn btn-success btn-sm rounded-pill btn-block" onclick="window.history.back()">
			</div>
		</div>
	</div>
	
	<div id='modalFoto' class="modal" role="dialog" data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
			        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
			        	<span aria-hidden='true'>&times;</span>
			        </button>
				</div>
	     		<div class="modal-body">
		     		<div align="center">
		     			<div id="imagenFoto"></div>
		     			<div id="nombreFoto"></div>
						<button type="button" class="btn btn-success btn-sm rounded-pill btn-block" data-dismiss="modal" >
							CERRAR
						</button>
					</div>
				</div>
	   		</div>
		</div>
	</div>
	
	
""")))}),format.raw/*74.2*/("""

"""),format.raw/*76.1*/("""<script type="text/javascript">
	const selectModal = (archivo,carpeta) =>"""),format.raw/*77.42*/("""{"""),format.raw/*77.43*/("""
		  """),format.raw/*78.5*/("""var qr = "/viewImg/"+qr;
		  document.getElementById('imagenFoto').innerHTML = "<label><img src='/viewImgAlbum/"+archivo+","+carpeta+"' style='max-height:400%;max-width:100%'></label>";
	    document.getElementById('nombreFoto').innerHTML = "<h5>"+archivo+"</h5>";
	    $("#modalFoto").modal("show");
	"""),format.raw/*82.2*/("""}"""),format.raw/*82.3*/("""
	
	"""),format.raw/*84.2*/("""const validarForm = () =>"""),format.raw/*84.27*/("""{"""),format.raw/*84.28*/("""
		"""),format.raw/*85.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/("""
"""),format.raw/*88.1*/("""</script>
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listado:List[List[String]],carpeta:String,titulo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listado,carpeta,titulo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listado,carpeta,titulo) => apply(mapDiccionario,mapPermiso,userMnu,listado,carpeta,titulo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/muestraAlbumFotos.scala.html
                  HASH: 1b5cd5801df49eec69e10868be52cf3056648285
                  MATRIX: 1039->1|1291->160|1319->163|1335->171|1374->173|1402->176|1470->224|1498->227|1612->321|1642->325|2172->828|2200->835|2500->1109|2538->1131|2577->1132|2610->1138|2648->1150|2685->1171|2724->1172|2759->1180|2860->1254|2887->1260|2918->1264|2946->1271|3012->1310|3039->1316|3068->1318|3096->1325|3190->1392|3217->1398|3253->1407|3308->1432|3341->1438|3382->1449|3412->1452|4471->2481|4500->2483|4601->2556|4630->2557|4662->2562|4991->2864|5019->2865|5050->2869|5103->2894|5132->2895|5162->2898|5245->2954|5273->2955|5301->2956
                  LINES: 28->1|33->2|35->4|35->4|35->4|36->5|36->5|37->6|37->6|39->8|49->18|49->18|60->29|60->29|60->29|61->30|62->31|62->31|62->31|63->32|64->33|64->33|64->33|64->33|65->34|65->34|65->34|65->34|67->36|67->36|68->37|70->39|71->40|72->41|73->42|105->74|107->76|108->77|108->77|109->78|113->82|113->82|115->84|115->84|115->84|116->85|118->87|118->87|119->88
                  -- GENERATED --
              */
          