
package viewsMnuMantencion.html

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

object mantTblOperadorMecanicoMant extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.MantOperadorMecanico],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listOperadorMecanico: List[tables.MantOperadorMecanico]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "USUARIOS OPERADOR MECANICO: CREAR/MODIFICAR", "")),format.raw/*9.82*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH>Usuario (userName)</TH>
							<TH>RUT</TH>
							<TH>Nombre (fullName)</TH>
					        <TH>Cargo/Funci&oacute;n</TH>
							<TH>Tel&eacute;fono</TH>
					        <TH>Email Corporativo</TH>
							<TH>Email Personal</TH>
					        <TH>Actor</TH>
							<TH>Tipo</TH>
							<TH>EDIT</TH>
							<TH>VIGENTE</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*29.8*/for(lista1 <- listOperadorMecanico) yield /*29.43*/{_display_(Seq[Any](format.raw/*29.44*/("""
							"""),format.raw/*30.8*/("""<TR>
								<td  style="text-align:left;"><div id=""""),_display_(/*31.49*/lista1/*31.55*/.getId()),format.raw/*31.63*/("""">"""),_display_(/*31.66*/lista1/*31.72*/.getUserName()),format.raw/*31.86*/("""</div></td>
								<td  style="text-align:left;">"""),_display_(/*32.40*/lista1/*32.46*/.getRut),format.raw/*32.53*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*33.40*/lista1/*33.46*/.getNombre()),format.raw/*33.58*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*34.40*/lista1/*34.46*/.getCargo()),format.raw/*34.57*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*35.40*/lista1/*35.46*/.getFono()),format.raw/*35.56*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*36.40*/lista1/*36.46*/.getEmailCor()),format.raw/*36.60*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*37.40*/lista1/*37.46*/.getEmailPer()),format.raw/*37.60*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*38.40*/lista1/*38.46*/.getNameActorPersonal()),format.raw/*38.69*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*39.40*/lista1/*39.46*/.getNameTipoPersonal),format.raw/*39.66*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick="operadorMecanicoModifica('"""),_display_(/*41.58*/lista1/*41.64*/.getId()),format.raw/*41.72*/("""');">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									<input type="hidden" id="vigente_"""),_display_(/*46.44*/lista1/*46.50*/.getId()),format.raw/*46.58*/("""" value=""""),_display_(/*46.68*/lista1/*46.74*/.getVigente()),format.raw/*46.87*/("""">
									"""),_display_(if(lista1.getVigente()==1)/*47.37*/{_display_(Seq[Any](format.raw/*47.38*/("""
										"""),format.raw/*48.11*/("""<input type="checkbox" checked onchange="modificaVigente('"""),_display_(/*48.70*/lista1/*48.76*/.getId()),format.raw/*48.84*/("""');">
									""")))}else/*49.15*/{_display_(Seq[Any](format.raw/*49.16*/("""
										"""),format.raw/*50.11*/("""<input type="checkbox" onchange="modificaVigente('"""),_display_(/*50.62*/lista1/*50.68*/.getId()),format.raw/*50.76*/("""');">
									""")))}),format.raw/*51.11*/("""
								"""),format.raw/*52.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*54.9*/("""
					"""),format.raw/*55.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes3/mantTblOperadorMecanicoNew/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="operadorMecanicoModifica" method="post" action="/routes3/mantTblOperadorMecanicoUpdate/">
		<input type="hidden" id="mod_idOperadorMecanico" name="id_operadorMecanico">
	</form>
""")))}),format.raw/*74.2*/("""




"""),format.raw/*79.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*80.31*/("""{"""),format.raw/*80.32*/("""
		  """),format.raw/*81.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*81.36*/("""{"""),format.raw/*81.37*/("""
		    	"""),format.raw/*82.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*84.20*/("""{"""),format.raw/*84.21*/("""
		        	"""),format.raw/*85.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*86.11*/("""}"""),format.raw/*86.12*/("""
		  """),format.raw/*87.5*/("""}"""),format.raw/*87.6*/(""" """),format.raw/*87.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*89.2*/("""}"""),format.raw/*89.3*/(""");
	
	const modificaVigente = (id_operadorMecanico) => """),format.raw/*91.51*/("""{"""),format.raw/*91.52*/("""
		"""),format.raw/*92.3*/("""let vigente = $("#vigente_"+id_operadorMecanico).val();
		if(vigente==1)"""),format.raw/*93.17*/("""{"""),format.raw/*93.18*/("""
			"""),format.raw/*94.4*/("""modificaVigencia(id_operadorMecanico, 0);
			$("#vigente_"+id_operadorMecanico).val(0);
		"""),format.raw/*96.3*/("""}"""),format.raw/*96.4*/("""else"""),format.raw/*96.8*/("""{"""),format.raw/*96.9*/("""
			"""),format.raw/*97.4*/("""modificaVigencia(id_operadorMecanico, 1);
			$("#vigente_"+id_operadorMecanico).val(1);
		"""),format.raw/*99.3*/("""}"""),format.raw/*99.4*/("""
	"""),format.raw/*100.2*/("""}"""),format.raw/*100.3*/("""
	
	"""),format.raw/*102.2*/("""const modificaVigencia = (id_operadorMecanico, valor) => """),format.raw/*102.59*/("""{"""),format.raw/*102.60*/("""
		"""),format.raw/*103.3*/("""let formData = new FormData();
		formData.append('id_operadorMecanico',id_operadorMecanico);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*106.10*/("""{"""),format.raw/*106.11*/("""
            """),format.raw/*107.13*/("""url: "/routes3/mantTblOperadorMecanicoVig/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*114.36*/("""{"""),format.raw/*114.37*/("""
	     		"""),format.raw/*115.9*/("""if(respuesta=="error")"""),format.raw/*115.31*/("""{"""),format.raw/*115.32*/("""
	     			"""),format.raw/*116.10*/("""alertify.alert(msgError, function () """),format.raw/*116.47*/("""{"""),format.raw/*116.48*/("""
		     			"""),format.raw/*117.11*/("""location.href = "/";
		     		"""),format.raw/*118.10*/("""}"""),format.raw/*118.11*/(""");
	     		"""),format.raw/*119.9*/("""}"""),format.raw/*119.10*/("""
	     	"""),format.raw/*120.8*/("""}"""),format.raw/*120.9*/("""
        """),format.raw/*121.9*/("""}"""),format.raw/*121.10*/(""");
	"""),format.raw/*122.2*/("""}"""),format.raw/*122.3*/("""
	
	"""),format.raw/*124.2*/("""const operadorMecanicoModifica = (id_operadorMecanico) => """),format.raw/*124.60*/("""{"""),format.raw/*124.61*/("""
		"""),format.raw/*125.3*/("""let nombre = $("#"+id_operadorMecanico).text();
				$("#mod_idOperadorMecanico").val(id_operadorMecanico);
				document.getElementById("operadorMecanicoModifica").submit();
	"""),format.raw/*128.2*/("""}"""),format.raw/*128.3*/("""
"""),format.raw/*129.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listOperadorMecanico:List[tables.MantOperadorMecanico]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listOperadorMecanico)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.MantOperadorMecanico]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listOperadorMecanico) => apply(mapDiccionario,mapPermiso,userMnu,listOperadorMecanico)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantTblOperadorMecanicoMant.scala.html
                  HASH: 6aaec21ac3dd33313ef4fb8207f5d7413e7586aa
                  MATRIX: 1063->1|1310->155|1343->163|1359->171|1398->173|1426->176|1494->224|1522->226|1598->277|1696->355|1726->358|2398->1004|2449->1039|2488->1040|2523->1048|2603->1101|2618->1107|2647->1115|2677->1118|2692->1124|2727->1138|2805->1189|2820->1195|2848->1202|2920->1247|2935->1253|2968->1265|3040->1310|3055->1316|3087->1327|3159->1372|3174->1378|3205->1388|3277->1433|3292->1439|3327->1453|3399->1498|3414->1504|3449->1518|3521->1563|3536->1569|3580->1592|3652->1637|3667->1643|3708->1663|3839->1767|3854->1773|3883->1781|4085->1956|4100->1962|4129->1970|4166->1980|4181->1986|4215->1999|4281->2038|4320->2039|4359->2050|4445->2109|4460->2115|4489->2123|4528->2143|4567->2144|4606->2155|4684->2206|4699->2212|4728->2220|4775->2236|4811->2245|4868->2272|4901->2278|5686->3033|5718->3038|5808->3100|5837->3101|5869->3106|5928->3137|5957->3138|5992->3146|6134->3260|6163->3261|6203->3273|6309->3351|6338->3352|6370->3357|6398->3358|6426->3359|6526->3432|6554->3433|6637->3488|6666->3489|6696->3492|6796->3564|6825->3565|6856->3569|6973->3659|7001->3660|7032->3664|7060->3665|7091->3669|7208->3759|7236->3760|7266->3762|7295->3763|7327->3767|7413->3824|7443->3825|7474->3828|7639->3964|7669->3965|7711->3978|7987->4225|8017->4226|8054->4235|8105->4257|8135->4258|8174->4268|8240->4305|8270->4306|8310->4317|8369->4347|8399->4348|8438->4359|8468->4360|8504->4368|8533->4369|8570->4378|8600->4379|8632->4383|8661->4384|8693->4388|8780->4446|8810->4447|8841->4450|9043->4624|9072->4625|9101->4626
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|60->29|60->29|60->29|61->30|62->31|62->31|62->31|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|72->41|72->41|72->41|77->46|77->46|77->46|77->46|77->46|77->46|78->47|78->47|79->48|79->48|79->48|79->48|80->49|80->49|81->50|81->50|81->50|81->50|82->51|83->52|85->54|86->55|105->74|110->79|111->80|111->80|112->81|112->81|112->81|113->82|115->84|115->84|116->85|117->86|117->86|118->87|118->87|118->87|120->89|120->89|122->91|122->91|123->92|124->93|124->93|125->94|127->96|127->96|127->96|127->96|128->97|130->99|130->99|131->100|131->100|133->102|133->102|133->102|134->103|137->106|137->106|138->107|145->114|145->114|146->115|146->115|146->115|147->116|147->116|147->116|148->117|149->118|149->118|150->119|150->119|151->120|151->120|152->121|152->121|153->122|153->122|155->124|155->124|155->124|156->125|159->128|159->128|160->129
                  -- GENERATED --
              */
          