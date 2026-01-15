
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

object reportPipelineSelDet extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.Sucursal,tables.Comercial,List[tables.Sucursal],List[tables.Comercial],play.twirl.api.HtmlFormat.Appendable] {

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
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "REPORTE PIPELINE DETALLADO","(SELECCIONAR PERIODO MOVIL)")),format.raw/*9.91*/("""
		"""),format.raw/*10.3*/("""<form action="/routes2/reportPipelineRptDet/" method="POST" onsubmit="return validarForm();">
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
							<TR>
								<TH>SUCURSAL:</TH>
								<td>
									<input type="hidden" id="id_sucursal" name="id_sucursal" value="0">
										"""),_display_(if(mapPermiso.get("cambiarSucursal")!=null)/*43.55*/{_display_(Seq[Any](format.raw/*43.56*/("""
											"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*44.57*/{_display_(Seq[Any](format.raw/*44.58*/("""
												"""),format.raw/*45.13*/("""<select id="selSucursal" class="custom-select center"  onchange = "$('#id_sucursal').val(value); actualizaComerciales(value); ">
													<option value="0">-- TODAS --</option>
													"""),_display_(/*47.15*/for(lista <- listSucursal) yield /*47.41*/{_display_(Seq[Any](format.raw/*47.42*/("""
														"""),format.raw/*48.15*/("""<option value=""""),_display_(/*48.31*/lista/*48.36*/.getId()),format.raw/*48.44*/("""">"""),_display_(/*48.47*/lista/*48.52*/.getNombre()),format.raw/*48.64*/("""</option>
													""")))}),format.raw/*49.15*/("""
												"""),format.raw/*50.13*/("""</select>
											""")))}else/*51.17*/{_display_(Seq[Any](format.raw/*51.18*/("""
												"""),format.raw/*52.13*/("""<select id="selSucursal" class="custom-select center"  onchange = "$('#id_sucursal').val(value); ">
													<option value="0">-- TODAS --</option>
													"""),_display_(/*54.15*/for(lista <- listSucursal) yield /*54.41*/{_display_(Seq[Any](format.raw/*54.42*/("""
														"""),format.raw/*55.15*/("""<option value=""""),_display_(/*55.31*/lista/*55.36*/.getId()),format.raw/*55.44*/("""">"""),_display_(/*55.47*/lista/*55.52*/.getNombre()),format.raw/*55.64*/("""</option>
													""")))}),format.raw/*56.15*/("""
												"""),format.raw/*57.13*/("""</select>
											""")))}),format.raw/*58.13*/("""
										""")))}else/*59.16*/{_display_(Seq[Any](format.raw/*59.17*/("""
											"""),format.raw/*60.12*/("""<input id="selSucursal" type="text" class="form-control left"
												value = """"),_display_(/*61.23*/sucursal/*61.31*/.getNombre()),format.raw/*61.43*/(""""
												readonly>
										""")))}),format.raw/*63.12*/("""
								"""),format.raw/*64.9*/("""</td>
							</TR>
							<tr>
								<TH>COMERCIAL:</TH>
								<td>
									<div id="actualComerciales">
										<input type="hidden" id="id_comercial" name="id_comercial" value="0">
										"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*71.56*/{_display_(Seq[Any](format.raw/*71.57*/("""
											"""),format.raw/*72.12*/("""<select  id="selComercial" class="custom-select center" onchange="$('#id_comercial').val(value)">
												<option value="0">-- TODOS --</option>
												"""),_display_(/*74.14*/for(lista <- listComercial) yield /*74.41*/{_display_(Seq[Any](format.raw/*74.42*/("""
													"""),format.raw/*75.14*/("""<option value=""""),_display_(/*75.30*/lista/*75.35*/.getId()),format.raw/*75.43*/("""">"""),_display_(/*75.46*/lista/*75.51*/.getNameUsuario()),format.raw/*75.68*/("""</option>
												""")))}),format.raw/*76.14*/("""
											"""),format.raw/*77.12*/("""</select>
										""")))}else/*78.16*/{_display_(Seq[Any](format.raw/*78.17*/("""
											"""),format.raw/*79.12*/("""<input  id="selComercial" type="text" class="form-control left"
												value = """"),_display_(/*80.23*/comercial/*80.32*/.getNameUsuario()),format.raw/*80.49*/(""""
												readonly>
										""")))}),format.raw/*82.12*/("""
									"""),format.raw/*83.10*/("""</div>
								</td>
							</tr>
							
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

""")))}),format.raw/*104.2*/("""


"""),format.raw/*107.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*108.31*/("""{"""),format.raw/*108.32*/("""
		  """),format.raw/*109.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*110.2*/("""}"""),format.raw/*110.3*/(""");
	
	const validarForm = () =>"""),format.raw/*112.27*/("""{"""),format.raw/*112.28*/("""
		"""),format.raw/*113.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*117.20*/("""{"""),format.raw/*117.21*/("""
			"""),format.raw/*118.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*119.87*/("""{"""),format.raw/*119.88*/("""
				"""),format.raw/*120.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*122.8*/("""}"""),format.raw/*122.9*/(""");
		"""),format.raw/*123.3*/("""}"""),format.raw/*123.4*/("""
		"""),format.raw/*124.3*/("""return(flag);
	"""),format.raw/*125.2*/("""}"""),format.raw/*125.3*/("""
	
	"""),format.raw/*127.2*/("""const actualizaComerciales = (id_sucursal) =>"""),format.raw/*127.47*/("""{"""),format.raw/*127.48*/("""
		"""),format.raw/*128.3*/("""var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
        $.ajax("""),format.raw/*130.16*/("""{"""),format.raw/*130.17*/("""
            """),format.raw/*131.13*/("""url: "/actualizaComerciales2Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*138.36*/("""{"""),format.raw/*138.37*/("""
				"""),format.raw/*139.5*/("""document.getElementById('actualComerciales').innerHTML = respuesta;
				$("#formSucursal").val(id_sucursal);
	     	"""),format.raw/*141.8*/("""}"""),format.raw/*141.9*/("""
        """),format.raw/*142.9*/("""}"""),format.raw/*142.10*/(""");	
	"""),format.raw/*143.2*/("""}"""),format.raw/*143.3*/("""
	
	
	
"""),format.raw/*147.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCotizar/reportPipelineSelDet.scala.html
                  HASH: 9bfc70892dce47ea00eeeca8ac999b122f68b321
                  MATRIX: 1111->1|1474->271|1506->278|1522->286|1561->288|1590->292|1658->340|1686->342|1762->393|1869->480|1899->483|2543->1100|2574->1110|2843->1352|2874->1362|3139->1600|3178->1601|3262->1658|3301->1659|3342->1672|3564->1867|3606->1893|3645->1894|3688->1909|3731->1925|3745->1930|3774->1938|3804->1941|3818->1946|3851->1958|3906->1982|3947->1995|3992->2021|4031->2022|4072->2035|4265->2201|4307->2227|4346->2228|4389->2243|4432->2259|4446->2264|4475->2272|4505->2275|4519->2280|4552->2292|4607->2316|4648->2329|4701->2351|4736->2367|4775->2368|4815->2380|4926->2464|4943->2472|4976->2484|5042->2519|5078->2528|5350->2773|5389->2774|5429->2786|5618->2948|5661->2975|5700->2976|5742->2990|5785->3006|5799->3011|5828->3019|5858->3022|5872->3027|5910->3044|5964->3067|6004->3079|6048->3104|6087->3105|6127->3117|6240->3203|6258->3212|6296->3229|6362->3264|6400->3274|6914->3757|6945->3760|7036->3822|7066->3823|7099->3828|7193->3894|7222->3895|7282->3926|7312->3927|7343->3930|7567->4125|7597->4126|7629->4130|7758->4230|7788->4231|7821->4236|7914->4301|7943->4302|7976->4307|8005->4308|8036->4311|8079->4326|8108->4327|8140->4331|8214->4376|8244->4377|8275->4380|8396->4472|8426->4473|8468->4486|8735->4724|8765->4725|8798->4730|8942->4846|8971->4847|9008->4856|9038->4857|9071->4862|9100->4863|9135->4870
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|56->25|56->25|66->35|66->35|74->43|74->43|75->44|75->44|76->45|78->47|78->47|78->47|79->48|79->48|79->48|79->48|79->48|79->48|79->48|80->49|81->50|82->51|82->51|83->52|85->54|85->54|85->54|86->55|86->55|86->55|86->55|86->55|86->55|86->55|87->56|88->57|89->58|90->59|90->59|91->60|92->61|92->61|92->61|94->63|95->64|102->71|102->71|103->72|105->74|105->74|105->74|106->75|106->75|106->75|106->75|106->75|106->75|106->75|107->76|108->77|109->78|109->78|110->79|111->80|111->80|111->80|113->82|114->83|135->104|138->107|139->108|139->108|140->109|141->110|141->110|143->112|143->112|144->113|148->117|148->117|149->118|150->119|150->119|151->120|153->122|153->122|154->123|154->123|155->124|156->125|156->125|158->127|158->127|158->127|159->128|161->130|161->130|162->131|169->138|169->138|170->139|172->141|172->141|173->142|173->142|174->143|174->143|178->147
                  -- GENERATED --
              */
          