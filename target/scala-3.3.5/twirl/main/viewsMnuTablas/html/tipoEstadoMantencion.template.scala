
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

object tipoEstadoMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.TipoEstado],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listEstados: List[tables.TipoEstado]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "TIPOS DE ESTADO (DEVOLUCIONES): CREAR/MODIFICAR/ELIMINAR", "")),format.raw/*9.95*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style="vertical-align: top;">SIGLA</TH>
							<TH>ESTADO</TH>
							<TH>APLICA LISTA PRECIOS</TH>
							"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1"))/*18.147*/ {_display_(Seq[Any](format.raw/*18.149*/("""
								"""),format.raw/*19.9*/("""<TH>COBRA """),_display_(/*19.20*/mapDiccionario("ARRIENDO")),format.raw/*19.46*/("""</TH>
							""")))} else {null} ),format.raw/*20.9*/("""
							"""),format.raw/*21.8*/("""<TH>"""),_display_(/*21.13*/mapDiccionario/*21.27*/.get("BODEGA")),format.raw/*21.41*/(""" """),format.raw/*21.42*/("""ASOCIADA</TH>
							<TH>SUCURSAL</TH>
							<TH>EDIT</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*28.8*/for(lista1 <- listEstados) yield /*28.34*/{_display_(Seq[Any](format.raw/*28.35*/("""
							"""),format.raw/*29.8*/("""<TR>
								<td  style="text-align:left;"><div id=""""),_display_(/*30.49*/lista1/*30.55*/.getId()),format.raw/*30.63*/("""">"""),_display_(/*30.66*/lista1/*30.72*/.getSigla()),format.raw/*30.83*/("""</div></td>
								<td  style="text-align:left;">"""),_display_(/*31.40*/lista1/*31.46*/.getNombre()),format.raw/*31.58*/("""</td>
								<td  style="text-align:left;">
									"""),_display_(if(lista1.getReparable()==1)/*33.39*/{_display_(Seq[Any](format.raw/*33.40*/("""
										"""),format.raw/*34.11*/("""SI (aplica lista precio)
									""")))}else/*35.15*/{_display_(Seq[Any](format.raw/*35.16*/("""
										"""),format.raw/*36.11*/("""NO (no aplica lista precio)
									""")))}),format.raw/*37.11*/("""
								"""),format.raw/*38.9*/("""</td>
								"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1"))/*39.148*/ {_display_(Seq[Any](format.raw/*39.150*/("""
									"""),format.raw/*40.10*/("""<td  style="text-align: left;">
									"""),_display_(if(lista1.getCobraArriendo() == 1)/*41.45*/ {_display_(Seq[Any](format.raw/*41.47*/("""
										"""),format.raw/*42.11*/("""SI cobra
									""")))}else/*43.17*/{_display_(Seq[Any](format.raw/*43.18*/("""
										"""),format.raw/*44.11*/("""NO cobra
									""")))}),format.raw/*45.11*/("""
									"""),format.raw/*46.10*/("""</td>
								""")))} else {null} ),format.raw/*47.10*/("""
								"""),format.raw/*48.9*/("""<td  style="text-align:left;">"""),_display_(/*48.40*/lista1/*48.46*/.getBodegaAsociada()),format.raw/*48.66*/("""</td>
								"""),_display_(if(mapDiccionario.get("nEmpresa").equals("SM8 DE MEXICO"))/*49.68*/{_display_(Seq[Any](format.raw/*49.69*/("""
									"""),format.raw/*50.10*/("""<td  style="text-align:left;">TODAS</td>
								""")))}else/*51.14*/{_display_(Seq[Any](format.raw/*51.15*/("""
									"""),format.raw/*52.10*/("""<td  style="text-align:left;">"""),_display_(/*52.41*/lista1/*52.47*/.getNomSucursal()),format.raw/*52.64*/("""</td>
								""")))}),format.raw/*53.10*/("""
								"""),format.raw/*54.9*/("""<td  style="text-align:center;">
									<a href="/tipoEstadoModifica/"""),_display_(/*55.40*/lista1/*55.46*/.getId()),format.raw/*55.54*/("""">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarTipoEstado('"""),_display_(/*60.53*/lista1/*60.59*/.getId()),format.raw/*60.67*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*65.9*/("""
					"""),format.raw/*66.6*/("""</tbody>
				</table>
			</div>
		</div>	
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/tipoEstadoAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/tipoEstadoElimina/">
		<input type="hidden" id="form_id_tipoEstado" name="id_tipoEstado">
	</form>
""")))}),format.raw/*85.2*/("""




"""),format.raw/*90.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*91.31*/("""{"""),format.raw/*91.32*/("""
		  """),format.raw/*92.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*92.36*/("""{"""),format.raw/*92.37*/("""
		    	"""),format.raw/*93.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*95.20*/("""{"""),format.raw/*95.21*/("""
		        	"""),format.raw/*96.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*97.11*/("""}"""),format.raw/*97.12*/("""
		  """),format.raw/*98.5*/("""}"""),format.raw/*98.6*/(""" """),format.raw/*98.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*100.2*/("""}"""),format.raw/*100.3*/(""");
	const eliminarTipoEstado = (id_tipoEstado) => """),format.raw/*101.48*/("""{"""),format.raw/*101.49*/("""
		"""),format.raw/*102.3*/("""let sigla = $("#"+id_tipoEstado).text();
		alertify.confirm("Esta seguro de eliminar el tipo de estado: "+sigla, function (e) """),format.raw/*103.86*/("""{"""),format.raw/*103.87*/("""
			"""),format.raw/*104.4*/("""if (e) """),format.raw/*104.11*/("""{"""),format.raw/*104.12*/("""
				"""),format.raw/*105.5*/("""$("#form_id_tipoEstado").val(id_tipoEstado);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*107.4*/("""}"""),format.raw/*107.5*/("""
		"""),format.raw/*108.3*/("""}"""),format.raw/*108.4*/(""");
	"""),format.raw/*109.2*/("""}"""),format.raw/*109.3*/("""
"""),format.raw/*110.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listEstados:List[tables.TipoEstado]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listEstados)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.TipoEstado]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listEstados) => apply(mapDiccionario,mapPermiso,userMnu,listEstados)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/tipoEstadoMantencion.scala.html
                  HASH: ec96e0451d51a85f0eb5891084c9a4f1a35418ba
                  MATRIX: 1042->1|1270->136|1303->144|1319->152|1358->154|1386->157|1454->205|1482->207|1558->258|1669->349|1699->352|2252->877|2293->879|2329->888|2367->899|2414->925|2471->939|2506->947|2538->952|2561->966|2596->980|2625->981|2777->1107|2819->1133|2858->1134|2893->1142|2973->1195|2988->1201|3017->1209|3047->1212|3062->1218|3094->1229|3172->1280|3187->1286|3220->1298|3330->1381|3369->1382|3408->1393|3466->1432|3505->1433|3544->1444|3613->1482|3649->1491|3830->1644|3871->1646|3909->1656|4012->1732|4052->1734|4091->1745|4133->1770|4172->1771|4211->1782|4261->1801|4299->1811|4358->1826|4394->1835|4452->1866|4467->1872|4508->1892|4608->1965|4647->1966|4685->1976|4758->2030|4797->2031|4835->2041|4893->2072|4908->2078|4946->2095|4992->2110|5028->2119|5127->2191|5142->2197|5171->2205|5379->2386|5394->2392|5423->2400|5560->2507|5593->2513|6320->3210|6352->3215|6442->3277|6471->3278|6503->3283|6562->3314|6591->3315|6626->3323|6768->3437|6797->3438|6837->3450|6943->3528|6972->3529|7004->3534|7032->3535|7060->3536|7161->3609|7190->3610|7269->3660|7299->3661|7330->3664|7485->3790|7515->3791|7547->3795|7583->3802|7613->3803|7646->3808|7777->3911|7806->3912|7837->3915|7866->3916|7898->3920|7927->3921|7956->3922
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|49->18|49->18|50->19|50->19|50->19|51->20|52->21|52->21|52->21|52->21|52->21|59->28|59->28|59->28|60->29|61->30|61->30|61->30|61->30|61->30|61->30|62->31|62->31|62->31|64->33|64->33|65->34|66->35|66->35|67->36|68->37|69->38|70->39|70->39|71->40|72->41|72->41|73->42|74->43|74->43|75->44|76->45|77->46|78->47|79->48|79->48|79->48|79->48|80->49|80->49|81->50|82->51|82->51|83->52|83->52|83->52|83->52|84->53|85->54|86->55|86->55|86->55|91->60|91->60|91->60|96->65|97->66|116->85|121->90|122->91|122->91|123->92|123->92|123->92|124->93|126->95|126->95|127->96|128->97|128->97|129->98|129->98|129->98|131->100|131->100|132->101|132->101|133->102|134->103|134->103|135->104|135->104|135->104|136->105|138->107|138->107|139->108|139->108|140->109|140->109|141->110
                  -- GENERATED --
              */
          