
package viewsMnuBodegas.html

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

object sucursalAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Map[String,String],Map[String,String],utilities.UserMnu,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

"""),_display_(/*6.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.50*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "AGREGAR/CREAR NUEVA SUCURSAL", "")),format.raw/*8.67*/("""
		"""),format.raw/*9.3*/("""<form action="/routes2/sucursalNuevo/" method="POST">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td>
								<div class="row">
									<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
										<b><font color="#008000">SUCURSAL:</font></b>
									</div>
									<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
										<input type="text" class="form-control" 
											name="nombreSucursal" 
											maxlength="50" 
											onkeydown="return sinReservados(window.event)"
											required>
									</div>
								</div>
								"""),_display_(if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1"))/*27.116*/ {_display_(Seq[Any](format.raw/*27.118*/("""
									"""),format.raw/*28.10*/("""<div class="row">
										<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
											<b><font color="#008000">IVA:</font></b>
										</div>
										<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
											<input type="text" class="form-control" 
												name="ivaSucursal" 
												autocomplete="off"
												value="0.00 %"
												onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
												onblur = "value = formatPorcentaje(value);"
												onkeydown="return ingresoDouble(window.event)">
										</div>
									</div>
								""")))}else/*42.14*/{_display_(Seq[Any](format.raw/*42.15*/("""
									"""),format.raw/*43.10*/("""<input type="hidden" name="ivaSucursal" value="0.00 %">
								""")))}),format.raw/*44.10*/("""
								"""),_display_(if(mapDiccionario.get("nEmpresa").equals("HOHE"))/*45.59*/ {_display_(Seq[Any](format.raw/*45.61*/("""
									"""),format.raw/*46.10*/("""<div class="row">
										<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
											<b><font color="#008000">COD_UNIDNEGOCIO:</font></b>
										</div>
										<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
											<input type="text" class="form-control"
											name="ccost"
											maxlength="50"
											value=""
											onkeydown="return sinReservados(window.event)">
										</div>
									</div>
									<div class="row">
										<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
											<b><font color="#008000">CEN_COS:</font></b>
										</div>
										<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
											<input type="text" class="form-control"
											name="cen_hohe"
											maxlength="50"
											value=""
											onkeydown="return sinReservados(window.event)">
										</div>
									</div>
									<div class="row">
										<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
											<b><font color="#008000">BODEGA:</font></b>
										</div>
										<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
											<input type="text" class="form-control"
											name="bod_hohe"
											maxlength="50"
											value=""
											onkeydown="return sinReservados(window.event)">
										</div>
									</div><div class="row">
										<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
											<b><font color="#008000">UBICACION:</font></b>
										</div>
										<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
											<input type="text" class="form-control"
											name="ubi_hohe"
											maxlength="50"
											value=""
											onkeydown="return sinReservados(window.event)">
										</div>
									</div>
								""")))} else {null} ),format.raw/*93.10*/("""

							"""),format.raw/*95.8*/("""</td>

						</tr>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit"  value="GRABAR SUCURSAL" class="btn btn-success btn-sm rounded-pill btn-block">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
				</div>
			</div>
		</form>
	</div>
""")))}),format.raw/*113.2*/("""




"""),format.raw/*118.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*119.31*/("""{"""),format.raw/*119.32*/("""
		  """),format.raw/*120.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*121.2*/("""}"""),format.raw/*121.3*/(""");
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu) => apply(mapDiccionario,mapPermiso,userMnu)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/sucursalAgrega.scala.html
                  HASH: 80a82af56c1cd4464f2d34e4080df3980f97010e
                  MATRIX: 1013->1|1203->98|1236->106|1252->114|1291->116|1319->119|1387->167|1415->169|1491->220|1574->283|1603->286|2446->1101|2487->1103|2525->1113|3144->1713|3183->1714|3221->1724|3317->1789|3403->1848|3443->1850|3481->1860|5277->3612|5313->3621|5885->4162|5918->4167|6009->4229|6039->4230|6072->4235|6166->4301|6195->4302
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|58->27|58->27|59->28|73->42|73->42|74->43|75->44|76->45|76->45|77->46|124->93|126->95|144->113|149->118|150->119|150->119|151->120|152->121|152->121
                  -- GENERATED --
              */
          