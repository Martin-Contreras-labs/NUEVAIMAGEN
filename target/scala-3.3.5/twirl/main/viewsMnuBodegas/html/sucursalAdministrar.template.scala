
package viewsMnuBodegas.html

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

object sucursalAdministrar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Sucursal],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listSucursales: List[tables.Sucursal]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "SUCURSALES: CREAR/MODIFICAR/ELIMINAR", "")),format.raw/*9.75*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style="vertical-align: top;">NOMBRE DE SUCURSAL</TH>
							"""),_display_(if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1"))/*16.115*/ {_display_(Seq[Any](format.raw/*16.117*/("""
								"""),format.raw/*17.9*/("""<TH>IVA</TH>
							""")))} else {null} ),format.raw/*18.9*/("""
							"""),_display_(if(mapDiccionario.get("nEmpresa").equals("HOHE"))/*19.58*/{_display_(Seq[Any](format.raw/*19.59*/("""
								"""),format.raw/*20.9*/("""<TH>COD_UNIDNEGOCIO</TH>
								<TH>CEN_COS</TH>
								<TH>BODEGA</TH>
								<TH>UBICACION</TH>
							""")))} else {null} ),format.raw/*24.9*/("""
							"""),format.raw/*25.8*/("""<TH>EDIT</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*30.8*/for(lista1 <- listSucursales) yield /*30.37*/{_display_(Seq[Any](format.raw/*30.38*/("""
							"""),format.raw/*31.8*/("""<TR>
								<td  style="text-align:left;"><div id=""""),_display_(/*32.49*/lista1/*32.55*/.getId()),format.raw/*32.63*/("""">"""),_display_(/*32.66*/lista1/*32.72*/.getNombre()),format.raw/*32.84*/("""</div></td>
								"""),_display_(if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1"))/*33.116*/ {_display_(Seq[Any](format.raw/*33.118*/("""
									"""),_display_(if(lista1.getIvaSucursal() > 0)/*34.42*/{_display_(Seq[Any](format.raw/*34.43*/("""
										"""),format.raw/*35.11*/("""<td  style="text-align:center;">"""),_display_(/*35.44*/(utilities.DecimalFormato.formato(lista1.getIvaSucursal()*100,2))),format.raw/*35.109*/(""" """),format.raw/*35.110*/("""%</td>
									""")))}else/*36.15*/{_display_(Seq[Any](format.raw/*36.16*/("""
										"""),format.raw/*37.11*/("""<td  style="text-align:center;">No Aplica</td>
									""")))}),format.raw/*38.11*/("""
									
								""")))} else {null} ),format.raw/*40.10*/("""
								"""),_display_(if(mapDiccionario.get("nEmpresa").equals("HOHE"))/*41.59*/{_display_(Seq[Any](format.raw/*41.60*/("""
									"""),format.raw/*42.10*/("""<td  style="text-align:left;">"""),_display_(/*42.41*/lista1/*42.47*/.getCcost()),format.raw/*42.58*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*43.41*/lista1/*43.47*/.getCen_hohe()),format.raw/*43.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*44.41*/lista1/*44.47*/.getBod_hohe()),format.raw/*44.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*45.41*/lista1/*45.47*/.getUbi_hohe()),format.raw/*45.61*/("""</td>
								""")))} else {null} ),format.raw/*46.10*/("""
								"""),format.raw/*47.9*/("""<td  style="text-align:center;">
									<form id="form_"""),_display_(/*48.26*/lista1/*48.32*/.getId()),format.raw/*48.40*/("""" method="post" action="/routes2/sucursalModifica/">
										<input type="hidden" name="id_sucursal" value=""""),_display_(/*49.59*/lista1/*49.65*/.getId()),format.raw/*49.73*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*50.63*/lista1/*50.69*/.getId()),format.raw/*50.77*/("""").submit();'>
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									</form>
								</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getId()>1)/*56.31*/{_display_(Seq[Any](format.raw/*56.32*/("""
										"""),format.raw/*57.11*/("""<a href="#" onclick= "eliminarSucursal('"""),_display_(/*57.52*/lista1/*57.58*/.getId()),format.raw/*57.66*/("""')">
											<kbd style="background-color: red">X</kbd>
										</a>
									""")))} else {null} ),format.raw/*60.11*/("""
								"""),format.raw/*61.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*63.9*/("""
					"""),format.raw/*64.6*/("""</tbody>
				</table>
			</div>
		</div>	
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/sucursalAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/routes2/sucursalElimina/">
		<input type="hidden" id="form_id_sucursal" name="id_sucursal">
	</form>
""")))}),format.raw/*83.2*/("""




"""),format.raw/*88.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*89.31*/("""{"""),format.raw/*89.32*/("""
		  """),format.raw/*90.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*90.36*/("""{"""),format.raw/*90.37*/("""
		    	"""),format.raw/*91.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*93.20*/("""{"""),format.raw/*93.21*/("""
		        	"""),format.raw/*94.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*95.11*/("""}"""),format.raw/*95.12*/("""
		  """),format.raw/*96.5*/("""}"""),format.raw/*96.6*/(""" """),format.raw/*96.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*98.2*/("""}"""),format.raw/*98.3*/(""");
	const eliminarSucursal = (id_sucursal) => """),format.raw/*99.44*/("""{"""),format.raw/*99.45*/("""
		"""),format.raw/*100.3*/("""let nombre = $("#"+id_sucursal).text();
		alertify.confirm("Esta seguro de eliminar esta sucursal: "+nombre, function (e) """),format.raw/*101.83*/("""{"""),format.raw/*101.84*/("""
			"""),format.raw/*102.4*/("""if (e) """),format.raw/*102.11*/("""{"""),format.raw/*102.12*/("""
				"""),format.raw/*103.5*/("""$("#form_id_sucursal").val(id_sucursal);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*105.4*/("""}"""),format.raw/*105.5*/("""
		"""),format.raw/*106.3*/("""}"""),format.raw/*106.4*/(""");
	"""),format.raw/*107.2*/("""}"""),format.raw/*107.3*/("""
"""),format.raw/*108.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listSucursales:List[tables.Sucursal]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listSucursales)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Sucursal]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listSucursales) => apply(mapDiccionario,mapPermiso,userMnu,listSucursales)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/sucursalAdministrar.scala.html
                  HASH: 7968046da3b0902bbd1ccb30433fefeaaeeb8bef
                  MATRIX: 1040->1|1269->137|1302->145|1318->153|1357->155|1385->158|1453->206|1481->208|1557->259|1648->330|1678->333|2152->779|2193->781|2229->790|2293->811|2378->869|2417->870|2453->879|2605->988|2640->996|2746->1076|2791->1105|2830->1106|2865->1114|2945->1167|2960->1173|2989->1181|3019->1184|3034->1190|3067->1202|3222->1329|3263->1331|3332->1373|3371->1374|3410->1385|3470->1418|3557->1483|3587->1484|3627->1505|3666->1506|3705->1517|3793->1574|3857->1594|3943->1653|3982->1654|4020->1664|4078->1695|4093->1701|4125->1712|4198->1758|4213->1764|4248->1778|4321->1824|4336->1830|4371->1844|4444->1890|4459->1896|4494->1910|4553->1925|4589->1934|4674->1992|4689->1998|4718->2006|4856->2117|4871->2123|4900->2131|4992->2196|5007->2202|5036->2210|5253->2400|5292->2401|5331->2412|5399->2453|5414->2459|5443->2467|5571->2551|5607->2560|5664->2587|5697->2593|6432->3298|6464->3303|6554->3365|6583->3366|6615->3371|6674->3402|6703->3403|6738->3411|6880->3525|6909->3526|6949->3538|7055->3616|7084->3617|7116->3622|7144->3623|7172->3624|7272->3697|7300->3698|7374->3744|7403->3745|7434->3748|7585->3870|7615->3871|7647->3875|7683->3882|7713->3883|7746->3888|7873->3987|7902->3988|7933->3991|7962->3992|7994->3996|8023->3997|8052->3998
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|48->17|49->18|50->19|50->19|51->20|55->24|56->25|61->30|61->30|61->30|62->31|63->32|63->32|63->32|63->32|63->32|63->32|64->33|64->33|65->34|65->34|66->35|66->35|66->35|66->35|67->36|67->36|68->37|69->38|71->40|72->41|72->41|73->42|73->42|73->42|73->42|74->43|74->43|74->43|75->44|75->44|75->44|76->45|76->45|76->45|77->46|78->47|79->48|79->48|79->48|80->49|80->49|80->49|81->50|81->50|81->50|87->56|87->56|88->57|88->57|88->57|88->57|91->60|92->61|94->63|95->64|114->83|119->88|120->89|120->89|121->90|121->90|121->90|122->91|124->93|124->93|125->94|126->95|126->95|127->96|127->96|127->96|129->98|129->98|130->99|130->99|131->100|132->101|132->101|133->102|133->102|133->102|134->103|136->105|136->105|137->106|137->106|138->107|138->107|139->108
                  -- GENERATED --
              */
          