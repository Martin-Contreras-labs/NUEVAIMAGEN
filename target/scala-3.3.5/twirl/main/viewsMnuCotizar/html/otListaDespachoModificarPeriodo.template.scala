
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

object otListaDespachoModificarPeriodo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "LISTA DE DESPACHOS","MODIFICAR y/o ELIMINAR (SOLO DESPACHOS DESDE "+mapDiccionario.get("OT")+")")),format.raw/*8.130*/("""
		"""),format.raw/*9.3*/("""<form action="/otListaDespachoModificar/" method="POST" onsubmit="return validarForm();">
			<br><br><br>
			<div class="row  justify-content-md-center">
				<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">PERIODO A CONSIDERAR</TH>
							</TR>
							<TR>
								<TH>FECHA DESDE:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaDesde"
							  			name="fechaDesde"
										onblur="if(!limitaFecha(value,"""),_display_(/*24.42*/mapPermiso/*24.52*/.get("parametro.diasMenosGuia")),format.raw/*24.83*/(""","""),_display_(/*24.85*/mapPermiso/*24.95*/.get("parametro.diasMasGuia")),format.raw/*24.124*/(""")) value='"""),_display_(/*24.135*/fechaDesde),format.raw/*24.145*/("""';"
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
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row  justify-content-md-center">
				<div class="col-xs-4 col-sm-2 col-md-2 col-lg-2">
					<input type="submit" id="btnSubmit" value='Generar' class="btn btn-primary btn-sm rounded-pill btn-block">
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*53.2*/("""


"""),format.raw/*56.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*57.31*/("""{"""),format.raw/*57.32*/("""
		  """),format.raw/*58.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*59.2*/("""}"""),format.raw/*59.3*/(""");
	
	const validarForm = () =>"""),format.raw/*61.27*/("""{"""),format.raw/*61.28*/("""
		"""),format.raw/*62.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*66.20*/("""{"""),format.raw/*66.21*/("""
			"""),format.raw/*67.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*68.87*/("""{"""),format.raw/*68.88*/("""
				"""),format.raw/*69.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*71.8*/("""}"""),format.raw/*71.9*/(""");
		"""),format.raw/*72.3*/("""}"""),format.raw/*72.4*/("""
		"""),format.raw/*73.3*/("""return(flag);
	"""),format.raw/*74.2*/("""}"""),format.raw/*74.3*/("""
	
	"""),format.raw/*76.2*/("""const obtieneTasas = (date) =>"""),format.raw/*76.32*/("""{"""),format.raw/*76.33*/("""
		"""),format.raw/*77.3*/("""let fecha = date.split("-");
		let x = new Date(fecha[0],(fecha[1]-1),fecha[2]);
		let today = new Date();
		let  y = new Date(today.getFullYear(),today.getMonth(),today.getDate());
		if (x > y)"""),format.raw/*81.13*/("""{"""),format.raw/*81.14*/("""
	    	"""),format.raw/*82.7*/("""alertify.alert("La fecha escogida no puede ser mayor a la fecha actual");
			$("#fechaCambio").val('"""),_display_(/*83.28*/fechaHasta),format.raw/*83.38*/("""');
			obtieneTasas(""""),_display_(/*84.19*/fechaHasta),format.raw/*84.29*/("""");
	    """),format.raw/*85.6*/("""}"""),format.raw/*85.7*/("""else"""),format.raw/*85.11*/("""{"""),format.raw/*85.12*/("""
    	  	"""),format.raw/*86.9*/("""if('"""),_display_(/*86.14*/mapDiccionario/*86.28*/.get("pais")),format.raw/*86.40*/("""'=="CHILE")"""),format.raw/*86.51*/("""{"""),format.raw/*86.52*/("""
				"""),format.raw/*87.5*/("""var formData = new FormData();
			  	formData.append('fecha',date);
		        $.ajax("""),format.raw/*89.18*/("""{"""),format.raw/*89.19*/("""
		            """),format.raw/*90.15*/("""url: "/tasasDeFechaAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(respuesta)"""),format.raw/*97.38*/("""{"""),format.raw/*97.39*/("""
			     		"""),format.raw/*98.11*/("""if(respuesta=="error")"""),format.raw/*98.33*/("""{"""),format.raw/*98.34*/("""
			     			"""),format.raw/*99.12*/("""alertify.alert(msgError, function () """),format.raw/*99.49*/("""{"""),format.raw/*99.50*/("""
				     			"""),format.raw/*100.13*/("""location.href = "/";
				     		"""),format.raw/*101.12*/("""}"""),format.raw/*101.13*/(""");
			     		"""),format.raw/*102.11*/("""}"""),format.raw/*102.12*/("""else"""),format.raw/*102.16*/("""{"""),format.raw/*102.17*/("""
							"""),format.raw/*103.8*/("""let valor = respuesta.split(";");
							$("#uf").val(formatStandar(valor[0],2));
							$("#usd").val(formatStandar(valor[1],2));
							$("#eur").val(formatStandar(valor[2],2));
						"""),format.raw/*107.7*/("""}"""),format.raw/*107.8*/("""
			     	"""),format.raw/*108.10*/("""}"""),format.raw/*108.11*/("""
		        """),format.raw/*109.11*/("""}"""),format.raw/*109.12*/(""");
    	  	"""),format.raw/*110.9*/("""}"""),format.raw/*110.10*/("""else"""),format.raw/*110.14*/("""{"""),format.raw/*110.15*/("""
    	  		"""),format.raw/*111.10*/("""document.getElementById('usd').value=1;
    	  		document.getElementById('eur').value=1;
    	  	"""),format.raw/*113.9*/("""}"""),format.raw/*113.10*/("""
	    	  
	    	 
	      """),format.raw/*116.8*/("""}"""),format.raw/*116.9*/("""
		
	"""),format.raw/*118.2*/("""}"""),format.raw/*118.3*/("""
	
"""),format.raw/*120.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,fechaDesde:String,fechaHasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta) => apply(mapDiccionario,mapPermiso,userMnu,fechaDesde,fechaHasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otListaDespachoModificarPeriodo.scala.html
                  HASH: f483a34faf7761f4cbb2c09ae2c44e3ef907b247
                  MATRIX: 1044->1|1274->138|1306->145|1322->153|1361->155|1390->159|1458->207|1486->209|1562->260|1709->386|1738->389|2383->1007|2402->1017|2454->1048|2483->1050|2502->1060|2553->1089|2592->1100|2624->1110|2675->1134|2706->1144|2975->1386|3006->1396|3425->1785|3455->1788|3545->1850|3574->1851|3606->1856|3699->1922|3727->1923|3786->1954|3815->1955|3845->1958|4068->2153|4097->2154|4128->2158|4256->2258|4285->2259|4317->2264|4409->2329|4437->2330|4469->2335|4497->2336|4527->2339|4569->2354|4597->2355|4628->2359|4686->2389|4715->2390|4745->2393|4967->2587|4996->2588|5030->2595|5158->2696|5189->2706|5238->2728|5269->2738|5305->2747|5333->2748|5365->2752|5394->2753|5430->2762|5462->2767|5485->2781|5518->2793|5557->2804|5586->2805|5618->2810|5731->2895|5760->2896|5803->2911|6074->3154|6103->3155|6142->3166|6192->3188|6221->3189|6261->3201|6326->3238|6355->3239|6397->3252|6458->3284|6488->3285|6530->3298|6560->3299|6593->3303|6623->3304|6659->3312|6873->3498|6902->3499|6941->3509|6971->3510|7011->3521|7041->3522|7080->3533|7110->3534|7143->3538|7173->3539|7212->3549|7337->3646|7367->3647|7420->3672|7449->3673|7482->3678|7511->3679|7542->3682
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|55->24|55->24|55->24|55->24|55->24|55->24|55->24|55->24|56->25|56->25|66->35|66->35|84->53|87->56|88->57|88->57|89->58|90->59|90->59|92->61|92->61|93->62|97->66|97->66|98->67|99->68|99->68|100->69|102->71|102->71|103->72|103->72|104->73|105->74|105->74|107->76|107->76|107->76|108->77|112->81|112->81|113->82|114->83|114->83|115->84|115->84|116->85|116->85|116->85|116->85|117->86|117->86|117->86|117->86|117->86|117->86|118->87|120->89|120->89|121->90|128->97|128->97|129->98|129->98|129->98|130->99|130->99|130->99|131->100|132->101|132->101|133->102|133->102|133->102|133->102|134->103|138->107|138->107|139->108|139->108|140->109|140->109|141->110|141->110|141->110|141->110|142->111|144->113|144->113|147->116|147->116|149->118|149->118|151->120
                  -- GENERATED --
              */
          