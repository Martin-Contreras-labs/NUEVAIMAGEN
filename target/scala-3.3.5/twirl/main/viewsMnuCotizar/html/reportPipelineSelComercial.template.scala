
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

object reportPipelineSelComercial extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.Sucursal,tables.Comercial,List[tables.Sucursal],List[tables.Comercial],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, 
sucursal: tables.Sucursal, comercial: tables.Comercial, listSucursal: List[tables.Sucursal], listComercial: List[tables.Comercial]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "PIPELINE RESUMEN Y DETALLE POR COMERCIAL","(SELECCIONAR PERIODO MOVIL)")),format.raw/*9.105*/("""
		"""),format.raw/*10.3*/("""<form action="/routes2/reportPipelineRptComercial/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">PERIODO DE COTIZACIONES A CONSIDERAR</TH>
							</TR>
							<TR>
								<TH>FECHA DESDE:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaDesde"
							  			name="fechaDesde"
							  			value=""""),_display_(/*25.21*/fechaDesde),format.raw/*25.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*35.21*/fechaHasta),format.raw/*35.31*/(""""
					        			required>
								</td>
							</TR>
							<TR style="background-color: rgb(254, 255, 255)">
								<TH colspan="2"><br>
							</TR>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row  justify-content-md-center">
				<div class="col-xs-4 col-sm-2 col-md-2 col-lg-2">
					<input type="submit" id="btnSubmit" value='Generar Reporte' class="btn btn-primary btn-sm rounded-pill btn-block">
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*56.2*/("""


"""),format.raw/*59.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*60.31*/("""{"""),format.raw/*60.32*/("""
		  """),format.raw/*61.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*62.2*/("""}"""),format.raw/*62.3*/(""");
	
	const validarForm = () =>"""),format.raw/*64.27*/("""{"""),format.raw/*64.28*/("""
		"""),format.raw/*65.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*69.20*/("""{"""),format.raw/*69.21*/("""
			"""),format.raw/*70.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*71.87*/("""{"""),format.raw/*71.88*/("""
				"""),format.raw/*72.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*74.8*/("""}"""),format.raw/*74.9*/(""");
		"""),format.raw/*75.3*/("""}"""),format.raw/*75.4*/("""
		"""),format.raw/*76.3*/("""return(flag);
	"""),format.raw/*77.2*/("""}"""),format.raw/*77.3*/("""
	
	"""),format.raw/*79.2*/("""const actualizaComerciales = (id_sucursal) =>"""),format.raw/*79.47*/("""{"""),format.raw/*79.48*/("""
		"""),format.raw/*80.3*/("""var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
        $.ajax("""),format.raw/*82.16*/("""{"""),format.raw/*82.17*/("""
            """),format.raw/*83.13*/("""url: "/actualizaComerciales2Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*90.36*/("""{"""),format.raw/*90.37*/("""
				"""),format.raw/*91.5*/("""document.getElementById('actualComerciales').innerHTML = respuesta;
				$("#formSucursal").val(id_sucursal);
	     	"""),format.raw/*93.8*/("""}"""),format.raw/*93.9*/("""
        """),format.raw/*94.9*/("""}"""),format.raw/*94.10*/(""");	
	"""),format.raw/*95.2*/("""}"""),format.raw/*95.3*/("""
	
	
	
"""),format.raw/*99.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String,sucursal:tables.Sucursal,comercial:tables.Comercial,listSucursal:List[tables.Sucursal],listComercial:List[tables.Comercial]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,sucursal,comercial,listSucursal,listComercial)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.Sucursal,tables.Comercial,List[tables.Sucursal],List[tables.Comercial]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,sucursal,comercial,listSucursal,listComercial) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,sucursal,comercial,listSucursal,listComercial)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/reportPipelineSelComercial.scala.html
                  HASH: b714dbc10420569f2e2fb9ee4c11901f73e28e7b
                  MATRIX: 1117->1|1480->271|1512->278|1528->286|1567->288|1596->292|1664->340|1692->342|1768->393|1890->494|1920->497|2570->1120|2601->1130|2870->1372|2901->1382|3427->1878|3457->1881|3547->1943|3576->1944|3608->1949|3701->2015|3729->2016|3788->2047|3817->2048|3847->2051|4070->2246|4099->2247|4130->2251|4258->2351|4287->2352|4319->2357|4411->2422|4439->2423|4471->2428|4499->2429|4529->2432|4571->2447|4599->2448|4630->2452|4703->2497|4732->2498|4762->2501|4882->2593|4911->2594|4952->2607|5218->2845|5247->2846|5279->2851|5422->2967|5450->2968|5486->2977|5515->2978|5547->2983|5575->2984|5609->2991
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|56->25|56->25|66->35|66->35|87->56|90->59|91->60|91->60|92->61|93->62|93->62|95->64|95->64|96->65|100->69|100->69|101->70|102->71|102->71|103->72|105->74|105->74|106->75|106->75|107->76|108->77|108->77|110->79|110->79|110->79|111->80|113->82|113->82|114->83|121->90|121->90|122->91|124->93|124->93|125->94|125->94|126->95|126->95|130->99
                  -- GENERATED --
              */
          