
package viewsMnuTablas.html

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

object usuarioMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Usuario],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listUsuarios: List[tables.Usuario]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "USUARIOS: CREAR/MODIFICAR", "")),format.raw/*9.64*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH>Usuario (userName)</TH>
							<TH>Nombre (fullName)</TH>
					        <TH>Cargo/Funci&oacute;n</TH>
					        <TH>e-mail</TH>
					        <TH>Tel&eacute;fono</TH>
					        <TH>Tipo Usuario</TH>
							<TH>Sucursal</TH>
							<TH>EDIT</TH>
							<TH>VIGENTE</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*27.8*/for(lista1 <- listUsuarios) yield /*27.35*/{_display_(Seq[Any](format.raw/*27.36*/("""
							"""),format.raw/*28.8*/("""<TR>
								<td  style="text-align:left;"><div id=""""),_display_(/*29.49*/lista1/*29.55*/.getId()),format.raw/*29.63*/("""">"""),_display_(/*29.66*/lista1/*29.72*/.getUserName()),format.raw/*29.86*/("""</div></td>
								<td  style="text-align:left;">"""),_display_(/*30.40*/lista1/*30.46*/.getNombre()),format.raw/*30.58*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*31.40*/lista1/*31.46*/.getCargo()),format.raw/*31.57*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*32.40*/lista1/*32.46*/.getEmail()),format.raw/*32.57*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*33.40*/lista1/*33.46*/.getFono()),format.raw/*33.56*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*34.40*/lista1/*34.46*/.getTipoUsuario()),format.raw/*34.63*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*35.40*/lista1/*35.46*/.getNameSucursal),format.raw/*35.62*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick="usuarioModifica('"""),_display_(/*37.49*/lista1/*37.55*/.getId()),format.raw/*37.63*/("""');">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									<input type="hidden" id="vigente_"""),_display_(/*42.44*/lista1/*42.50*/.getId()),format.raw/*42.58*/("""" value=""""),_display_(/*42.68*/lista1/*42.74*/.getVigente()),format.raw/*42.87*/("""">
									"""),_display_(if(lista1.getVigente()==1)/*43.37*/{_display_(Seq[Any](format.raw/*43.38*/("""
										"""),format.raw/*44.11*/("""<input type="checkbox" checked onchange="modificaVigente('"""),_display_(/*44.70*/lista1/*44.76*/.getId()),format.raw/*44.84*/("""');">
									""")))}else/*45.15*/{_display_(Seq[Any](format.raw/*45.16*/("""
										"""),format.raw/*46.11*/("""<input type="checkbox" onchange="modificaVigente('"""),_display_(/*46.62*/lista1/*46.68*/.getId()),format.raw/*46.76*/("""');">
									""")))}),format.raw/*47.11*/("""
								"""),format.raw/*48.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*50.9*/("""
					"""),format.raw/*51.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/usuarioAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="usuarioModifica" method="post" action="/usuarioModifica/">
		<input type="hidden" id="mod_idUser" name="id_usuario">
	</form>
""")))}),format.raw/*70.2*/("""




"""),format.raw/*75.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*76.31*/("""{"""),format.raw/*76.32*/("""
		  """),format.raw/*77.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*77.36*/("""{"""),format.raw/*77.37*/("""
		    	"""),format.raw/*78.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*80.20*/("""{"""),format.raw/*80.21*/("""
		        	"""),format.raw/*81.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*82.11*/("""}"""),format.raw/*82.12*/("""
		  """),format.raw/*83.5*/("""}"""),format.raw/*83.6*/(""" """),format.raw/*83.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*85.2*/("""}"""),format.raw/*85.3*/(""");
	
	const modificaVigente = (id_usuario) => """),format.raw/*87.42*/("""{"""),format.raw/*87.43*/("""
		"""),format.raw/*88.3*/("""let vigente = $("#vigente_"+id_usuario).val();
		if(vigente==1)"""),format.raw/*89.17*/("""{"""),format.raw/*89.18*/("""
			"""),format.raw/*90.4*/("""modificaVigencia(id_usuario, 0);
			$("#vigente_"+id_usuario).val(0);
		"""),format.raw/*92.3*/("""}"""),format.raw/*92.4*/("""else"""),format.raw/*92.8*/("""{"""),format.raw/*92.9*/("""
			"""),format.raw/*93.4*/("""modificaVigencia(id_usuario, 1);
			$("#vigente_"+id_usuario).val(1);
		"""),format.raw/*95.3*/("""}"""),format.raw/*95.4*/("""
	"""),format.raw/*96.2*/("""}"""),format.raw/*96.3*/("""
	
	"""),format.raw/*98.2*/("""const modificaVigencia = (id_usuario, valor) => """),format.raw/*98.50*/("""{"""),format.raw/*98.51*/("""
		"""),format.raw/*99.3*/("""let formData = new FormData();
		formData.append('id_usuario',id_usuario);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*102.10*/("""{"""),format.raw/*102.11*/("""
            """),format.raw/*103.13*/("""url: "/modVigUsuarioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*110.36*/("""{"""),format.raw/*110.37*/("""
	     		"""),format.raw/*111.9*/("""if(respuesta=="error")"""),format.raw/*111.31*/("""{"""),format.raw/*111.32*/("""
	     			"""),format.raw/*112.10*/("""alertify.alert(msgError, function () """),format.raw/*112.47*/("""{"""),format.raw/*112.48*/("""
		     			"""),format.raw/*113.11*/("""location.href = "/";
		     		"""),format.raw/*114.10*/("""}"""),format.raw/*114.11*/(""");
	     		"""),format.raw/*115.9*/("""}"""),format.raw/*115.10*/("""
	     	"""),format.raw/*116.8*/("""}"""),format.raw/*116.9*/("""
        """),format.raw/*117.9*/("""}"""),format.raw/*117.10*/(""");
	"""),format.raw/*118.2*/("""}"""),format.raw/*118.3*/("""
	
	"""),format.raw/*120.2*/("""const usuarioModifica = (id_usuario) => """),format.raw/*120.42*/("""{"""),format.raw/*120.43*/("""
		"""),format.raw/*121.3*/("""let nombre = $("#"+id_usuario).text();
				$("#mod_idUser").val(id_usuario);
				document.getElementById("usuarioModifica").submit();
	"""),format.raw/*124.2*/("""}"""),format.raw/*124.3*/("""
"""),format.raw/*125.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listUsuarios:List[tables.Usuario]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listUsuarios)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Usuario]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listUsuarios) => apply(mapDiccionario,mapPermiso,userMnu,listUsuarios)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/usuarioMantencion.scala.html
                  HASH: 081552f354756168dd1485c0a9deb53780d88533
                  MATRIX: 1036->1|1262->134|1295->142|1311->150|1350->152|1378->155|1446->203|1474->205|1550->256|1630->316|1660->319|2287->920|2330->947|2369->948|2404->956|2484->1009|2499->1015|2528->1023|2558->1026|2573->1032|2608->1046|2686->1097|2701->1103|2734->1115|2806->1160|2821->1166|2853->1177|2925->1222|2940->1228|2972->1239|3044->1284|3059->1290|3090->1300|3162->1345|3177->1351|3215->1368|3287->1413|3302->1419|3339->1435|3461->1530|3476->1536|3505->1544|3707->1719|3722->1725|3751->1733|3788->1743|3803->1749|3837->1762|3903->1801|3942->1802|3981->1813|4067->1872|4082->1878|4111->1886|4150->1906|4189->1907|4228->1918|4306->1969|4321->1975|4350->1983|4397->1999|4433->2008|4490->2035|4523->2041|5235->2723|5267->2728|5357->2790|5386->2791|5418->2796|5477->2827|5506->2828|5541->2836|5683->2950|5712->2951|5752->2963|5858->3041|5887->3042|5919->3047|5947->3048|5975->3049|6075->3122|6103->3123|6177->3169|6206->3170|6236->3173|6327->3236|6356->3237|6387->3241|6486->3313|6514->3314|6545->3318|6573->3319|6604->3323|6703->3395|6731->3396|6760->3398|6788->3399|6819->3403|6895->3451|6924->3452|6954->3455|7101->3573|7131->3574|7173->3587|7432->3817|7462->3818|7499->3827|7550->3849|7580->3850|7619->3860|7685->3897|7715->3898|7755->3909|7814->3939|7844->3940|7883->3951|7913->3952|7949->3960|7978->3961|8015->3970|8045->3971|8077->3975|8106->3976|8138->3980|8207->4020|8237->4021|8268->4024|8431->4159|8460->4160|8489->4161
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|58->27|58->27|58->27|59->28|60->29|60->29|60->29|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|68->37|68->37|68->37|73->42|73->42|73->42|73->42|73->42|73->42|74->43|74->43|75->44|75->44|75->44|75->44|76->45|76->45|77->46|77->46|77->46|77->46|78->47|79->48|81->50|82->51|101->70|106->75|107->76|107->76|108->77|108->77|108->77|109->78|111->80|111->80|112->81|113->82|113->82|114->83|114->83|114->83|116->85|116->85|118->87|118->87|119->88|120->89|120->89|121->90|123->92|123->92|123->92|123->92|124->93|126->95|126->95|127->96|127->96|129->98|129->98|129->98|130->99|133->102|133->102|134->103|141->110|141->110|142->111|142->111|142->111|143->112|143->112|143->112|144->113|145->114|145->114|146->115|146->115|147->116|147->116|148->117|148->117|149->118|149->118|151->120|151->120|151->120|152->121|155->124|155->124|156->125
                  -- GENERATED --
              */
          