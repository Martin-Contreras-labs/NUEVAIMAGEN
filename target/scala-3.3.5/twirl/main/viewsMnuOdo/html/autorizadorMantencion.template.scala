
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

object autorizadorMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.OperadorServicio],play.twirl.api.HtmlFormat.Appendable] {

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
							<TH>USER ASOCIADO</TH>
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
									"""),_display_(if(lista1.getActivo()==1)/*38.36*/{_display_(Seq[Any](format.raw/*38.37*/("""
										"""),format.raw/*39.11*/("""<input type="checkbox" checked onchange="modificaActivo('"""),_display_(/*39.69*/lista1/*39.75*/.getId()),format.raw/*39.83*/("""');">
									""")))}else/*40.15*/{_display_(Seq[Any](format.raw/*40.16*/("""
										"""),format.raw/*41.11*/("""<input type="checkbox" onchange="modificaActivo('"""),_display_(/*41.61*/lista1/*41.67*/.getId()),format.raw/*41.75*/("""');">
									""")))}),format.raw/*42.11*/("""
								"""),format.raw/*43.9*/("""</td>
								<td style="text-align:center;">
									<a href="/operadorModifica/"""),_display_(/*45.38*/lista1/*45.44*/.getId()),format.raw/*45.52*/("""">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*50.9*/("""
					"""),format.raw/*51.6*/("""</tbody>
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
""")))}),format.raw/*70.2*/("""


"""),format.raw/*73.1*/("""<script type="text/javascript">
	
	$(document).ready(function() """),format.raw/*75.31*/("""{"""),format.raw/*75.32*/("""
		  """),format.raw/*76.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*76.36*/("""{"""),format.raw/*76.37*/("""
		    	"""),format.raw/*77.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*80.20*/("""{"""),format.raw/*80.21*/("""
		        	"""),format.raw/*81.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*82.11*/("""}"""),format.raw/*82.12*/("""
		  """),format.raw/*83.5*/("""}"""),format.raw/*83.6*/(""" """),format.raw/*83.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*85.2*/("""}"""),format.raw/*85.3*/(""");
	
	const modificaActivo = (id_operador) => """),format.raw/*87.42*/("""{"""),format.raw/*87.43*/("""
		"""),format.raw/*88.3*/("""let activo = $("#activo_"+id_operador).val();
		if(activo==1)"""),format.raw/*89.16*/("""{"""),format.raw/*89.17*/("""
			"""),format.raw/*90.4*/("""modificaVigencia(id_operador, 0);
			$("#activo_"+id_operador).val(0);
		"""),format.raw/*92.3*/("""}"""),format.raw/*92.4*/("""else"""),format.raw/*92.8*/("""{"""),format.raw/*92.9*/("""
			"""),format.raw/*93.4*/("""modificaVigencia(id_operador, 1);
			$("#activo_"+id_operador).val(1);
		"""),format.raw/*95.3*/("""}"""),format.raw/*95.4*/("""
		
	"""),format.raw/*97.2*/("""}"""),format.raw/*97.3*/("""
	
	"""),format.raw/*99.2*/("""const modificaVigencia = (id_operador, valor) => """),format.raw/*99.51*/("""{"""),format.raw/*99.52*/("""
		"""),format.raw/*100.3*/("""let formData = new FormData();
		formData.append('id_operador',id_operador);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*103.10*/("""{"""),format.raw/*103.11*/("""
            """),format.raw/*104.13*/("""url: "/modVigOperadorServicioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*111.36*/("""{"""),format.raw/*111.37*/("""
	     		"""),format.raw/*112.9*/("""if(respuesta=="error")"""),format.raw/*112.31*/("""{"""),format.raw/*112.32*/("""
	     			"""),format.raw/*113.10*/("""alertify.alert(msgError, function () """),format.raw/*113.47*/("""{"""),format.raw/*113.48*/("""
		     			"""),format.raw/*114.11*/("""location.href = "/";
		     		"""),format.raw/*115.10*/("""}"""),format.raw/*115.11*/(""");
	     		"""),format.raw/*116.9*/("""}"""),format.raw/*116.10*/("""
	     	"""),format.raw/*117.8*/("""}"""),format.raw/*117.9*/("""
        """),format.raw/*118.9*/("""}"""),format.raw/*118.10*/(""");
	"""),format.raw/*119.2*/("""}"""),format.raw/*119.3*/("""
	
	
	
	
"""),format.raw/*124.1*/("""</script>


		
		
	
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
                  SOURCE: app/viewsMnuOdo/autorizadorMantencion.scala.html
                  HASH: 692bcb302f72fd29d08a18dbc499bec418b19b2c
                  MATRIX: 1046->1|1283->145|1316->153|1332->161|1371->163|1399->166|1467->214|1495->216|1571->267|1663->339|1693->342|2003->625|2026->639|2058->650|2325->891|2370->920|2409->921|2444->929|2516->974|2531->980|2561->989|2632->1033|2647->1039|2680->1051|2753->1097|2768->1103|2800->1114|2873->1160|2888->1166|2920->1177|2993->1223|3008->1229|3039->1239|3110->1283|3125->1289|3157->1300|3230->1346|3245->1352|3284->1370|3400->1459|3415->1465|3444->1473|3481->1483|3496->1489|3529->1501|3594->1539|3633->1540|3672->1551|3757->1609|3772->1615|3801->1623|3840->1643|3879->1644|3918->1655|3995->1705|4010->1711|4039->1719|4086->1735|4122->1744|4232->1827|4247->1833|4276->1841|4415->1950|4448->1956|5178->2656|5208->2659|5300->2723|5329->2724|5361->2729|5420->2760|5449->2761|5484->2769|5658->2915|5687->2916|5727->2928|5833->3006|5862->3007|5894->3012|5922->3013|5950->3014|6050->3087|6078->3088|6152->3134|6181->3135|6211->3138|6300->3199|6329->3200|6360->3204|6460->3277|6488->3278|6519->3282|6547->3283|6578->3287|6678->3360|6706->3361|6738->3366|6766->3367|6797->3371|6874->3420|6903->3421|6934->3424|7083->3544|7113->3545|7155->3558|7423->3797|7453->3798|7490->3807|7541->3829|7571->3830|7610->3840|7676->3877|7706->3878|7746->3889|7805->3919|7835->3920|7874->3931|7904->3932|7940->3940|7969->3941|8006->3950|8036->3951|8068->3955|8097->3956|8134->3965
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|58->27|58->27|58->27|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|68->37|68->37|68->37|68->37|68->37|68->37|69->38|69->38|70->39|70->39|70->39|70->39|71->40|71->40|72->41|72->41|72->41|72->41|73->42|74->43|76->45|76->45|76->45|81->50|82->51|101->70|104->73|106->75|106->75|107->76|107->76|107->76|108->77|111->80|111->80|112->81|113->82|113->82|114->83|114->83|114->83|116->85|116->85|118->87|118->87|119->88|120->89|120->89|121->90|123->92|123->92|123->92|123->92|124->93|126->95|126->95|128->97|128->97|130->99|130->99|130->99|131->100|134->103|134->103|135->104|142->111|142->111|143->112|143->112|143->112|144->113|144->113|144->113|145->114|146->115|146->115|147->116|147->116|148->117|148->117|149->118|149->118|150->119|150->119|155->124
                  -- GENERATED --
              */
          