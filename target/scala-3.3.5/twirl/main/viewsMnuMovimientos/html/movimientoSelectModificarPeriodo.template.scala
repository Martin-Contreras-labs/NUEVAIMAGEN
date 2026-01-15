
package viewsMnuMovimientos.html

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

object movimientoSelectModificarPeriodo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "LISTADO DE MOVIMIENTOS Y DESPACHOS EFECTUADOS","MODIFICAR y/o ELIMINAR (SOLO MOVIMIENTOS)")),format.raw/*8.124*/("""
		"""),format.raw/*9.3*/("""<form action="/movimientoSelectModificar/" method="POST" onsubmit="return validarForm();">
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
									    onblur="if(!limitaFecha(value,"""),_display_(/*24.45*/mapPermiso/*24.55*/.get("parametro.diasMenosGuia")),format.raw/*24.86*/(""","""),_display_(/*24.88*/mapPermiso/*24.98*/.get("parametro.diasMasGuia")),format.raw/*24.127*/(""")) value='"""),_display_(/*24.138*/fechaDesde),format.raw/*24.148*/("""';"
									    value=""""),_display_(/*25.22*/fechaDesde),format.raw/*25.32*/(""""
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
                  SOURCE: app/viewsMnuMovimientos/movimientoSelectModificarPeriodo.scala.html
                  HASH: 876ddec183a6f391ad96e1604008960f9cd70537
                  MATRIX: 1049->1|1279->138|1311->145|1327->153|1366->155|1395->159|1463->207|1491->209|1567->260|1708->380|1737->383|2386->1005|2405->1015|2457->1046|2486->1048|2505->1058|2556->1087|2595->1098|2627->1108|2679->1133|2710->1143|2979->1385|3010->1395|3429->1784|3459->1787|3549->1849|3578->1850|3610->1855|3703->1921|3731->1922|3790->1953|3819->1954|3849->1957|4072->2152|4101->2153|4132->2157|4260->2257|4289->2258|4321->2263|4413->2328|4441->2329|4473->2334|4501->2335|4531->2338|4573->2353|4601->2354|4632->2358|4690->2388|4719->2389|4749->2392|4971->2586|5000->2587|5034->2594|5162->2695|5193->2705|5242->2727|5273->2737|5309->2746|5337->2747|5369->2751|5398->2752|5434->2761|5466->2766|5489->2780|5522->2792|5561->2803|5590->2804|5622->2809|5735->2894|5764->2895|5807->2910|6078->3153|6107->3154|6146->3165|6196->3187|6225->3188|6265->3200|6330->3237|6359->3238|6401->3251|6462->3283|6492->3284|6534->3297|6564->3298|6597->3302|6627->3303|6663->3311|6877->3497|6906->3498|6945->3508|6975->3509|7015->3520|7045->3521|7084->3532|7114->3533|7147->3537|7177->3538|7216->3548|7341->3645|7371->3646|7424->3671|7453->3672|7486->3677|7515->3678|7546->3681
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|55->24|55->24|55->24|55->24|55->24|55->24|55->24|55->24|56->25|56->25|66->35|66->35|84->53|87->56|88->57|88->57|89->58|90->59|90->59|92->61|92->61|93->62|97->66|97->66|98->67|99->68|99->68|100->69|102->71|102->71|103->72|103->72|104->73|105->74|105->74|107->76|107->76|107->76|108->77|112->81|112->81|113->82|114->83|114->83|115->84|115->84|116->85|116->85|116->85|116->85|117->86|117->86|117->86|117->86|117->86|117->86|118->87|120->89|120->89|121->90|128->97|128->97|129->98|129->98|129->98|130->99|130->99|130->99|131->100|132->101|132->101|133->102|133->102|133->102|133->102|134->103|138->107|138->107|139->108|139->108|140->109|140->109|141->110|141->110|141->110|141->110|142->111|144->113|144->113|147->116|147->116|149->118|149->118|151->120
                  -- GENERATED --
              */
          