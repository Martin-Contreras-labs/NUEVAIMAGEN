
package viewsMnuOdo.html

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

object operadorMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.OperadorServicio],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listOperadores: List[tables.OperadorServicio]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "OPERADORES (CREAR/MODIFICAR/ELIMINAR)", "")),format.raw/*9.76*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH>"""),_display_(/*15.13*/mapDiccionario/*15.27*/.get("RUT")),format.raw/*15.38*/("""</TH>
							<TH>NOMBRE</TH>
							<TH>CARGO</TH>
							<TH>E-MAIL</TH>
							<TH>TELEFONO</TH>
							<TH>NOTAS</TH>
							<TH>USER MADA</TH>
							<TH>ACTIVO</TH>
							<TH>EDIT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*27.8*/for(lista1 <- listOperadores) yield /*27.37*/{_display_(Seq[Any](format.raw/*27.38*/("""
							"""),format.raw/*28.8*/("""<TR>
								<td style="text-align:center;">"""),_display_(/*29.41*/lista1/*29.47*/.getRut()),format.raw/*29.56*/("""</td>
								<td style="text-align:left;">"""),_display_(/*30.39*/lista1/*30.45*/.getNombre()),format.raw/*30.57*/("""</td>
								<td style="text-align:center;">"""),_display_(/*31.41*/lista1/*31.47*/.getCargo()),format.raw/*31.58*/("""</td>
								<td style="text-align:center;">"""),_display_(/*32.41*/lista1/*32.47*/.getEmail()),format.raw/*32.58*/("""</td>
								<td style="text-align:center;">"""),_display_(/*33.41*/lista1/*33.47*/.getFono()),format.raw/*33.57*/("""</td>
								<td style="text-align:left;">"""),_display_(/*34.39*/lista1/*34.45*/.getNotas()),format.raw/*34.56*/("""</td>
								<td style="text-align:center;">"""),_display_(/*35.41*/lista1/*35.47*/.getUserAdamName()),format.raw/*35.65*/("""</td>
								<td  style="text-align:center;">
									<input type="hidden" id="activo_"""),_display_(/*37.43*/lista1/*37.49*/.getId()),format.raw/*37.57*/("""" value=""""),_display_(/*37.67*/lista1/*37.73*/.getActivo()),format.raw/*37.85*/("""">
									"""),_display_(if(lista1.getUserAdamName().equals(""))/*38.50*/{_display_(Seq[Any](format.raw/*38.51*/("""
										"""),_display_(if(lista1.getActivo()==1)/*39.37*/{_display_(Seq[Any](format.raw/*39.38*/("""
											"""),format.raw/*40.12*/("""<input type="checkbox" checked onchange="modificaActivo('"""),_display_(/*40.70*/lista1/*40.76*/.getId()),format.raw/*40.84*/("""');">
										""")))}else/*41.16*/{_display_(Seq[Any](format.raw/*41.17*/("""
											"""),format.raw/*42.12*/("""<input type="checkbox" onchange="modificaActivo('"""),_display_(/*42.62*/lista1/*42.68*/.getId()),format.raw/*42.76*/("""');">
										""")))}),format.raw/*43.12*/("""
									""")))}else/*44.15*/{_display_(Seq[Any](format.raw/*44.16*/("""
										"""),_display_(if(lista1.getActivo()==1)/*45.37*/{_display_(Seq[Any](format.raw/*45.38*/("""
											"""),format.raw/*46.12*/("""<input type="checkbox" checked disabled>
										""")))}else/*47.16*/{_display_(Seq[Any](format.raw/*47.17*/("""
											"""),format.raw/*48.12*/("""<input type="checkbox" disabled>
										""")))}),format.raw/*49.12*/("""
									""")))}),format.raw/*50.11*/("""


								"""),format.raw/*53.9*/("""</td>
								<td style="text-align:center;">
									"""),_display_(if(lista1.getUserAdamName().equals(""))/*55.50*/{_display_(Seq[Any](format.raw/*55.51*/("""
										"""),format.raw/*56.11*/("""<a href="/operadorModifica/"""),_display_(/*56.39*/lista1/*56.45*/.getId()),format.raw/*56.53*/("""">
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									""")))} else {null} ),format.raw/*59.11*/("""
								"""),format.raw/*60.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*62.9*/("""
					"""),format.raw/*63.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR OPERADOR" class="btn btn-info btn-sm rounded-pill btn-block" onclick="location.href='/operadorNuevo/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/operadorElimina/">
		<input type="hidden" id="form_id_operador" name="id_operadorServicio">
	</form>
""")))}),format.raw/*82.2*/("""


"""),format.raw/*85.1*/("""<script type="text/javascript">
	
	$(document).ready(function() """),format.raw/*87.31*/("""{"""),format.raw/*87.32*/("""
		  """),format.raw/*88.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*88.36*/("""{"""),format.raw/*88.37*/("""
		    	"""),format.raw/*89.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*92.20*/("""{"""),format.raw/*92.21*/("""
		        	"""),format.raw/*93.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*94.11*/("""}"""),format.raw/*94.12*/("""
		  """),format.raw/*95.5*/("""}"""),format.raw/*95.6*/(""" """),format.raw/*95.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*97.2*/("""}"""),format.raw/*97.3*/(""");
	
	const modificaActivo = (id_operador) => """),format.raw/*99.42*/("""{"""),format.raw/*99.43*/("""
		"""),format.raw/*100.3*/("""let activo = $("#activo_"+id_operador).val();
		if(activo==1)"""),format.raw/*101.16*/("""{"""),format.raw/*101.17*/("""
			"""),format.raw/*102.4*/("""modificaVigencia(id_operador, 0);
			$("#activo_"+id_operador).val(0);
		"""),format.raw/*104.3*/("""}"""),format.raw/*104.4*/("""else"""),format.raw/*104.8*/("""{"""),format.raw/*104.9*/("""
			"""),format.raw/*105.4*/("""modificaVigencia(id_operador, 1);
			$("#activo_"+id_operador).val(1);
		"""),format.raw/*107.3*/("""}"""),format.raw/*107.4*/("""
		
	"""),format.raw/*109.2*/("""}"""),format.raw/*109.3*/("""
	
	"""),format.raw/*111.2*/("""const modificaVigencia = (id_operador, valor) => """),format.raw/*111.51*/("""{"""),format.raw/*111.52*/("""
		"""),format.raw/*112.3*/("""let formData = new FormData();
		formData.append('id_operador',id_operador);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*115.10*/("""{"""),format.raw/*115.11*/("""
            """),format.raw/*116.13*/("""url: "/modVigOperadorServicioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*123.36*/("""{"""),format.raw/*123.37*/("""
	     		"""),format.raw/*124.9*/("""if(respuesta=="error")"""),format.raw/*124.31*/("""{"""),format.raw/*124.32*/("""
	     			"""),format.raw/*125.10*/("""alertify.alert(msgError, function () """),format.raw/*125.47*/("""{"""),format.raw/*125.48*/("""
		     			"""),format.raw/*126.11*/("""location.href = "/";
		     		"""),format.raw/*127.10*/("""}"""),format.raw/*127.11*/(""");
	     		"""),format.raw/*128.9*/("""}"""),format.raw/*128.10*/("""
	     	"""),format.raw/*129.8*/("""}"""),format.raw/*129.9*/("""
        """),format.raw/*130.9*/("""}"""),format.raw/*130.10*/(""");
	"""),format.raw/*131.2*/("""}"""),format.raw/*131.3*/("""
	
	
	
	
"""),format.raw/*136.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listOperadores:List[tables.OperadorServicio]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listOperadores)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.OperadorServicio]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listOperadores) => apply(mapDiccionario,mapPermiso,userMnu,listOperadores)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/operadorMantencion.scala.html
                  HASH: 687fdfd084395085976f6ae9706fd05e94048891
                  MATRIX: 1043->1|1280->145|1313->153|1329->161|1368->163|1396->166|1464->214|1492->216|1568->267|1660->339|1690->342|2000->625|2023->639|2055->650|2318->887|2363->916|2402->917|2437->925|2509->970|2524->976|2554->985|2625->1029|2640->1035|2673->1047|2746->1093|2761->1099|2793->1110|2866->1156|2881->1162|2913->1173|2986->1219|3001->1225|3032->1235|3103->1279|3118->1285|3150->1296|3223->1342|3238->1348|3277->1366|3393->1455|3408->1461|3437->1469|3474->1479|3489->1485|3522->1497|3601->1549|3640->1550|3704->1587|3743->1588|3783->1600|3868->1658|3883->1664|3912->1672|3952->1693|3991->1694|4031->1706|4108->1756|4123->1762|4152->1770|4200->1787|4234->1802|4273->1803|4337->1840|4376->1841|4416->1853|4491->1909|4530->1910|4570->1922|4645->1966|4687->1977|4725->1988|4847->2083|4886->2084|4925->2095|4980->2123|4995->2129|5024->2137|5154->2223|5190->2232|5247->2259|5280->2265|6010->2965|6040->2968|6132->3032|6161->3033|6193->3038|6252->3069|6281->3070|6316->3078|6490->3224|6519->3225|6559->3237|6665->3315|6694->3316|6726->3321|6754->3322|6782->3323|6882->3396|6910->3397|6984->3443|7013->3444|7044->3447|7134->3508|7164->3509|7196->3513|7297->3586|7326->3587|7358->3591|7387->3592|7419->3596|7520->3669|7549->3670|7582->3675|7611->3676|7643->3680|7721->3729|7751->3730|7782->3733|7931->3853|7961->3854|8003->3867|8271->4106|8301->4107|8338->4116|8389->4138|8419->4139|8458->4149|8524->4186|8554->4187|8594->4198|8653->4228|8683->4229|8722->4240|8752->4241|8788->4249|8817->4250|8854->4259|8884->4260|8916->4264|8945->4265|8982->4274
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|58->27|58->27|58->27|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|68->37|68->37|68->37|68->37|68->37|68->37|69->38|69->38|70->39|70->39|71->40|71->40|71->40|71->40|72->41|72->41|73->42|73->42|73->42|73->42|74->43|75->44|75->44|76->45|76->45|77->46|78->47|78->47|79->48|80->49|81->50|84->53|86->55|86->55|87->56|87->56|87->56|87->56|90->59|91->60|93->62|94->63|113->82|116->85|118->87|118->87|119->88|119->88|119->88|120->89|123->92|123->92|124->93|125->94|125->94|126->95|126->95|126->95|128->97|128->97|130->99|130->99|131->100|132->101|132->101|133->102|135->104|135->104|135->104|135->104|136->105|138->107|138->107|140->109|140->109|142->111|142->111|142->111|143->112|146->115|146->115|147->116|154->123|154->123|155->124|155->124|155->124|156->125|156->125|156->125|157->126|158->127|158->127|159->128|159->128|160->129|160->129|161->130|161->130|162->131|162->131|167->136
                  -- GENERATED --
              */
          