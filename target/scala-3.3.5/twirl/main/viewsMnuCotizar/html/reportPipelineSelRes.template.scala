
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

object reportPipelineSelRes extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.Sucursal,List[tables.Sucursal],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String, 
sucursal: tables.Sucursal, listSucursal: List[tables.Sucursal]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "REPORTE PIPELINE RESUMIDO","(SELECCIONAR PERIODO MOVIL)")),format.raw/*9.90*/("""
		"""),format.raw/*10.3*/("""<form action="/routes2/reportPipelineRptRes/" method="POST" onsubmit="return validarForm();">
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

""")))}),format.raw/*84.2*/("""


"""),format.raw/*87.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*88.31*/("""{"""),format.raw/*88.32*/("""
		  """),format.raw/*89.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*90.2*/("""}"""),format.raw/*90.3*/(""");
	
	const validarForm = () =>"""),format.raw/*92.27*/("""{"""),format.raw/*92.28*/("""
		"""),format.raw/*93.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*97.20*/("""{"""),format.raw/*97.21*/("""
			"""),format.raw/*98.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*99.87*/("""{"""),format.raw/*99.88*/("""
				"""),format.raw/*100.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*102.8*/("""}"""),format.raw/*102.9*/(""");
		"""),format.raw/*103.3*/("""}"""),format.raw/*103.4*/("""
		"""),format.raw/*104.3*/("""return(flag);
	"""),format.raw/*105.2*/("""}"""),format.raw/*105.3*/("""
	
	"""),format.raw/*107.2*/("""const actualizaComerciales = (id_sucursal) =>"""),format.raw/*107.47*/("""{"""),format.raw/*107.48*/("""
		"""),format.raw/*108.3*/("""var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
        $.ajax("""),format.raw/*110.16*/("""{"""),format.raw/*110.17*/("""
            """),format.raw/*111.13*/("""url: "/actualizaComerciales2Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*118.36*/("""{"""),format.raw/*118.37*/("""
				"""),format.raw/*119.5*/("""document.getElementById('actualComerciales').innerHTML = respuesta;
				$("#formSucursal").val(id_sucursal);
	     	"""),format.raw/*121.8*/("""}"""),format.raw/*121.9*/("""
        """),format.raw/*122.9*/("""}"""),format.raw/*122.10*/(""");	
	"""),format.raw/*123.2*/("""}"""),format.raw/*123.3*/("""
	
	
	
"""),format.raw/*127.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String,sucursal:tables.Sucursal,listSucursal:List[tables.Sucursal]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,sucursal,listSucursal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,tables.Sucursal,List[tables.Sucursal]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,sucursal,listSucursal) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta,sucursal,listSucursal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/reportPipelineSelRes.scala.html
                  HASH: e10e527acfc67143a27602698eba1567d7619c75
                  MATRIX: 1071->1|1366->203|1398->210|1414->218|1453->220|1482->224|1550->272|1578->274|1654->325|1760->411|1790->414|2434->1031|2465->1041|2734->1283|2765->1293|3030->1531|3069->1532|3153->1589|3192->1590|3233->1603|3455->1798|3497->1824|3536->1825|3579->1840|3622->1856|3636->1861|3665->1869|3695->1872|3709->1877|3742->1889|3797->1913|3838->1926|3883->1952|3922->1953|3963->1966|4156->2132|4198->2158|4237->2159|4280->2174|4323->2190|4337->2195|4366->2203|4396->2206|4410->2211|4443->2223|4498->2247|4539->2260|4592->2282|4627->2298|4666->2299|4706->2311|4817->2395|4834->2403|4867->2415|4933->2450|4969->2459|5467->2927|5497->2930|5587->2992|5616->2993|5648->2998|5741->3064|5769->3065|5828->3096|5857->3097|5887->3100|6110->3295|6139->3296|6170->3300|6298->3400|6327->3401|6360->3406|6453->3471|6482->3472|6515->3477|6544->3478|6575->3481|6618->3496|6647->3497|6679->3501|6753->3546|6783->3547|6814->3550|6935->3642|6965->3643|7007->3656|7274->3894|7304->3895|7337->3900|7481->4016|7510->4017|7547->4026|7577->4027|7610->4032|7639->4033|7674->4040
                  LINES: 28->1|35->4|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|56->25|56->25|66->35|66->35|74->43|74->43|75->44|75->44|76->45|78->47|78->47|78->47|79->48|79->48|79->48|79->48|79->48|79->48|79->48|80->49|81->50|82->51|82->51|83->52|85->54|85->54|85->54|86->55|86->55|86->55|86->55|86->55|86->55|86->55|87->56|88->57|89->58|90->59|90->59|91->60|92->61|92->61|92->61|94->63|95->64|115->84|118->87|119->88|119->88|120->89|121->90|121->90|123->92|123->92|124->93|128->97|128->97|129->98|130->99|130->99|131->100|133->102|133->102|134->103|134->103|135->104|136->105|136->105|138->107|138->107|138->107|139->108|141->110|141->110|142->111|149->118|149->118|150->119|152->121|152->121|153->122|153->122|154->123|154->123|158->127
                  -- GENERATED --
              */
          