
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

object otListaRevisarPeriodo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "REVISAR "+mapDiccionario.get("Ordenes_de_trabajo").toUpperCase(),"(confirmadas y pendientes de confirmar)")),format.raw/*8.140*/("""
		"""),format.raw/*9.3*/("""<form action="/routes2/otListaRevisar/" method="POST" onsubmit="return validarForm();">
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
							  			value=""""),_display_(/*24.21*/fechaDesde),format.raw/*24.31*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*34.21*/fechaHasta),format.raw/*34.31*/(""""
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

""")))}),format.raw/*52.2*/("""


"""),format.raw/*55.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*56.31*/("""{"""),format.raw/*56.32*/("""
		  """),format.raw/*57.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*58.2*/("""}"""),format.raw/*58.3*/(""");
	
	const validarForm = () =>"""),format.raw/*60.27*/("""{"""),format.raw/*60.28*/("""
		"""),format.raw/*61.3*/("""$("#btnSubmit").attr('disabled',true);
		let flag = true;
		var desde = document.getElementById('fechaDesde').value;
		var hasta = document.getElementById('fechaHasta').value;
		if(desde > hasta)"""),format.raw/*65.20*/("""{"""),format.raw/*65.21*/("""
			"""),format.raw/*66.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*67.87*/("""{"""),format.raw/*67.88*/("""
				"""),format.raw/*68.5*/("""$("#btnSubmit").attr('disabled',false);
				return(flag);
     		"""),format.raw/*70.8*/("""}"""),format.raw/*70.9*/(""");
		"""),format.raw/*71.3*/("""}"""),format.raw/*71.4*/("""
		"""),format.raw/*72.3*/("""return(flag);
	"""),format.raw/*73.2*/("""}"""),format.raw/*73.3*/("""
	
	"""),format.raw/*75.2*/("""const obtieneTasas = (date) =>"""),format.raw/*75.32*/("""{"""),format.raw/*75.33*/("""
		"""),format.raw/*76.3*/("""let fecha = date.split("-");
		let x = new Date(fecha[0],(fecha[1]-1),fecha[2]);
		let today = new Date();
		let  y = new Date(today.getFullYear(),today.getMonth(),today.getDate());
		if (x > y)"""),format.raw/*80.13*/("""{"""),format.raw/*80.14*/("""
	    	"""),format.raw/*81.7*/("""alertify.alert("La fecha escogida no puede ser mayor a la fecha actual");
			$("#fechaCambio").val('"""),_display_(/*82.28*/fechaHasta),format.raw/*82.38*/("""');
			obtieneTasas(""""),_display_(/*83.19*/fechaHasta),format.raw/*83.29*/("""");
	    """),format.raw/*84.6*/("""}"""),format.raw/*84.7*/("""else"""),format.raw/*84.11*/("""{"""),format.raw/*84.12*/("""
    	  	"""),format.raw/*85.9*/("""if('"""),_display_(/*85.14*/mapDiccionario/*85.28*/.get("pais")),format.raw/*85.40*/("""'=="CHILE")"""),format.raw/*85.51*/("""{"""),format.raw/*85.52*/("""
				"""),format.raw/*86.5*/("""var formData = new FormData();
			  	formData.append('fecha',date);
		        $.ajax("""),format.raw/*88.18*/("""{"""),format.raw/*88.19*/("""
		            """),format.raw/*89.15*/("""url: "/tasasDeFechaAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(respuesta)"""),format.raw/*96.38*/("""{"""),format.raw/*96.39*/("""
			     		"""),format.raw/*97.11*/("""if(respuesta=="error")"""),format.raw/*97.33*/("""{"""),format.raw/*97.34*/("""
			     			"""),format.raw/*98.12*/("""alertify.alert(msgError, function () """),format.raw/*98.49*/("""{"""),format.raw/*98.50*/("""
				     			"""),format.raw/*99.13*/("""location.href = "/";
				     		"""),format.raw/*100.12*/("""}"""),format.raw/*100.13*/(""");
			     		"""),format.raw/*101.11*/("""}"""),format.raw/*101.12*/("""else"""),format.raw/*101.16*/("""{"""),format.raw/*101.17*/("""
							"""),format.raw/*102.8*/("""let valor = respuesta.split(";");
							$("#uf").val(formatStandar(valor[0],2));
							$("#usd").val(formatStandar(valor[1],2));
							$("#eur").val(formatStandar(valor[2],2));
						"""),format.raw/*106.7*/("""}"""),format.raw/*106.8*/("""
			     	"""),format.raw/*107.10*/("""}"""),format.raw/*107.11*/("""
		        """),format.raw/*108.11*/("""}"""),format.raw/*108.12*/(""");
    	  	"""),format.raw/*109.9*/("""}"""),format.raw/*109.10*/("""else"""),format.raw/*109.14*/("""{"""),format.raw/*109.15*/("""
    	  		"""),format.raw/*110.10*/("""document.getElementById('usd').value=1;
    	  		document.getElementById('eur').value=1;
    	  	"""),format.raw/*112.9*/("""}"""),format.raw/*112.10*/("""
	    	  
	    	 
	      """),format.raw/*115.8*/("""}"""),format.raw/*115.9*/("""
		
	"""),format.raw/*117.2*/("""}"""),format.raw/*117.3*/("""
	
"""),format.raw/*119.1*/("""</script>




		
		
	
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
                  SOURCE: app/viewsMnuCotizar/otListaRevisarPeriodo.scala.html
                  HASH: 6e3efcfcb5cf7e9607d5f11349bfd1e2ec767f98
                  MATRIX: 1034->1|1264->138|1296->145|1312->153|1351->155|1380->159|1448->207|1476->209|1552->260|1709->396|1738->399|2360->994|2391->1004|2660->1246|2691->1256|3110->1645|3140->1648|3230->1710|3259->1711|3291->1716|3384->1782|3412->1783|3471->1814|3500->1815|3530->1818|3753->2013|3782->2014|3813->2018|3941->2118|3970->2119|4002->2124|4094->2189|4122->2190|4154->2195|4182->2196|4212->2199|4254->2214|4282->2215|4313->2219|4371->2249|4400->2250|4430->2253|4652->2447|4681->2448|4715->2455|4843->2556|4874->2566|4923->2588|4954->2598|4990->2607|5018->2608|5050->2612|5079->2613|5115->2622|5147->2627|5170->2641|5203->2653|5242->2664|5271->2665|5303->2670|5416->2755|5445->2756|5488->2771|5759->3014|5788->3015|5827->3026|5877->3048|5906->3049|5946->3061|6011->3098|6040->3099|6081->3112|6142->3144|6172->3145|6214->3158|6244->3159|6277->3163|6307->3164|6343->3172|6557->3358|6586->3359|6625->3369|6655->3370|6695->3381|6725->3382|6764->3393|6794->3394|6827->3398|6857->3399|6896->3409|7021->3506|7051->3507|7104->3532|7133->3533|7166->3538|7195->3539|7226->3542
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|55->24|55->24|65->34|65->34|83->52|86->55|87->56|87->56|88->57|89->58|89->58|91->60|91->60|92->61|96->65|96->65|97->66|98->67|98->67|99->68|101->70|101->70|102->71|102->71|103->72|104->73|104->73|106->75|106->75|106->75|107->76|111->80|111->80|112->81|113->82|113->82|114->83|114->83|115->84|115->84|115->84|115->84|116->85|116->85|116->85|116->85|116->85|116->85|117->86|119->88|119->88|120->89|127->96|127->96|128->97|128->97|128->97|129->98|129->98|129->98|130->99|131->100|131->100|132->101|132->101|132->101|132->101|133->102|137->106|137->106|138->107|138->107|139->108|139->108|140->109|140->109|140->109|140->109|141->110|143->112|143->112|146->115|146->115|148->117|148->117|150->119
                  -- GENERATED --
              */
          