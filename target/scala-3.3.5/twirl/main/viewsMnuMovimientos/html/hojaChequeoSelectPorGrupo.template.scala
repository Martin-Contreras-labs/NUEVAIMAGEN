
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

object hojaChequeoSelectPorGrupo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[tables.Grupo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listBodegas: List[List[String]], listGrupos: List[tables.Grupo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	"""),_display_(/*10.3*/modalContactoCliente()),format.raw/*10.25*/("""
	"""),_display_(/*11.3*/modalContactoProyecto()),format.raw/*11.26*/("""
	
	"""),format.raw/*13.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*14.4*/barraTitulo(mapDiccionario, "EMISION DE HOJAS DE CHEQUEO POR GRUPOS SELECCIONADOS - "+mapDiccionario.get("BODEGA")+"S/PROYECTOS", "(agrupado solo por equipo)")),format.raw/*14.163*/("""
		"""),format.raw/*15.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
			
				<form id="formSeleccion" action="/hojaChequeoDetallePorGrupo/" method="POST">
					"""),_display_(/*19.7*/for(lista <- listGrupos) yield /*19.31*/{_display_(Seq[Any](format.raw/*19.32*/("""
						"""),format.raw/*20.7*/("""<div class="form-check form-check-inline">
						  <input class="form-check-input" type="checkbox" id="inline"""),_display_(/*21.68*/lista/*21.73*/.id),format.raw/*21.76*/("""" onchange="actualizaGrupos('"""),_display_(/*21.106*/lista/*21.111*/.id),format.raw/*21.114*/("""');">
						  <input type="hidden" name="idGrupos[]" id="grupo"""),_display_(/*22.58*/lista/*22.63*/.id),format.raw/*22.66*/("""" value="0">
						  <label class="form-check-label" for="inline"""),_display_(/*23.53*/lista/*23.58*/.id),format.raw/*23.61*/("""">"""),_display_(/*23.64*/lista/*23.69*/.nombre),format.raw/*23.76*/("""</label>
						</div>
					""")))}),format.raw/*25.7*/("""
					"""),format.raw/*26.6*/("""<input type="hidden" name="id_bodegaEmpresa" id="id_bodegaEmpresa" value="0">
				</form>
				
				<br><br>
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>TIPO</TH>
							<TH>"""),_display_(/*36.13*/mapDiccionario/*36.27*/.get("BODEGA")),format.raw/*36.41*/("""/PROYECTO</TH>
							<TH>"""),_display_(/*37.13*/mapDiccionario/*37.27*/.get("RUT")),format.raw/*37.38*/(""" """),format.raw/*37.39*/("""(Cliente o Propietario)</TH>
							<TH>NOMBRE CLIENTE O PROPIETARIO</TH>
					        <TH>NOMBRE DEL PROYECTO</TH>
					        <TH>"""),_display_(/*40.19*/mapDiccionario/*40.33*/.get("COMUNA")),format.raw/*40.47*/(""" """),format.raw/*40.48*/("""PROYECTO</TH>
							<TH>SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*45.8*/for(lista1 <- listBodegas) yield /*45.34*/{_display_(Seq[Any](format.raw/*45.35*/("""
							"""),format.raw/*46.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*47.40*/lista1/*47.46*/.get(16)),format.raw/*47.54*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*48.40*/lista1/*48.46*/.get(4)),format.raw/*48.53*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*49.73*/lista1/*49.79*/.get(5)),format.raw/*49.86*/("""')"><div id=""""),_display_(/*49.100*/lista1/*49.106*/.get(1)),format.raw/*49.113*/("""">"""),_display_(/*49.116*/lista1/*49.122*/.get(5)),format.raw/*49.129*/("""</div></a></td>
								<td style="text-align:center;"><a href="#" onclick="buscaCliente('"""),_display_(/*50.76*/lista1/*50.82*/.get(7)),format.raw/*50.89*/("""')">"""),_display_(/*50.94*/lista1/*50.100*/.get(6)),format.raw/*50.107*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*51.74*/lista1/*51.80*/.get(7)),format.raw/*51.87*/("""')">"""),_display_(/*51.92*/lista1/*51.98*/.get(7)),format.raw/*51.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*52.75*/lista1/*52.81*/.get(8)),format.raw/*52.88*/("""')">"""),_display_(/*52.93*/lista1/*52.99*/.get(8)),format.raw/*52.106*/("""</a></td>
								
								<td  style="text-align:left;">"""),_display_(/*54.40*/lista1/*54.46*/.get(9)),format.raw/*54.53*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick="selecciona('"""),_display_(/*56.44*/lista1/*56.50*/.get(1)),format.raw/*56.57*/("""')">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*61.9*/("""
					"""),format.raw/*62.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
""")))}),format.raw/*74.2*/("""




"""),format.raw/*79.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*80.31*/("""{"""),format.raw/*80.32*/("""
		  """),format.raw/*81.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*81.36*/("""{"""),format.raw/*81.37*/("""
		    	"""),format.raw/*82.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*85.20*/("""{"""),format.raw/*85.21*/("""
		        	"""),format.raw/*86.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*87.11*/("""}"""),format.raw/*87.12*/("""
		  """),format.raw/*88.5*/("""}"""),format.raw/*88.6*/(""" """),format.raw/*88.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*90.2*/("""}"""),format.raw/*90.3*/(""");
	
	let validaGrupo = "0";
	const actualizaGrupos = (id_grupo) =>"""),format.raw/*93.39*/("""{"""),format.raw/*93.40*/("""
		"""),format.raw/*94.3*/("""if($('#grupo'+id_grupo).val()==0)"""),format.raw/*94.36*/("""{"""),format.raw/*94.37*/("""
			"""),format.raw/*95.4*/("""$('#grupo'+id_grupo).val(id_grupo);
			validaGrupo = parseFloat(validaGrupo) + parseFloat(id_grupo)
		"""),format.raw/*97.3*/("""}"""),format.raw/*97.4*/("""else"""),format.raw/*97.8*/("""{"""),format.raw/*97.9*/("""
			"""),format.raw/*98.4*/("""$('#grupo'+id_grupo).val(0);
			validaGrupo = parseFloat(validaGrupo) - parseFloat(id_grupo);
		"""),format.raw/*100.3*/("""}"""),format.raw/*100.4*/("""
	"""),format.raw/*101.2*/("""}"""),format.raw/*101.3*/(""";
	
	const selecciona = (id_bodegaEmpresa) =>"""),format.raw/*103.42*/("""{"""),format.raw/*103.43*/("""
		"""),format.raw/*104.3*/("""if(validaGrupo > 0)"""),format.raw/*104.22*/("""{"""),format.raw/*104.23*/("""
			"""),format.raw/*105.4*/("""$("#id_bodegaEmpresa").val(id_bodegaEmpresa);
			document.getElementById('formSeleccion').submit();
		"""),format.raw/*107.3*/("""}"""),format.raw/*107.4*/("""else"""),format.raw/*107.8*/("""{"""),format.raw/*107.9*/("""
			"""),format.raw/*108.4*/("""alertify.alert('FALTA SELECCIONAR AL MENOS UN GRUPO', function () """),format.raw/*108.70*/("""{"""),format.raw/*108.71*/("""
     		"""),format.raw/*109.8*/("""}"""),format.raw/*109.9*/(""");
		"""),format.raw/*110.3*/("""}"""),format.raw/*110.4*/("""
	"""),format.raw/*111.2*/("""}"""),format.raw/*111.3*/("""
	
"""),format.raw/*113.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listBodegas:List[List[String]],listGrupos:List[tables.Grupo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listBodegas,listGrupos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],List[tables.Grupo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listBodegas,listGrupos) => apply(mapDiccionario,mapPermiso,userMnu,listBodegas,listGrupos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMovimientos/hojaChequeoSelectPorGrupo.scala.html
                  HASH: 9099853fce56807a9977547fbc98662ca0d454c3
                  MATRIX: 1066->1|1321->163|1354->171|1370->179|1409->181|1438->185|1506->233|1536->238|1591->273|1620->276|1663->298|1692->301|1736->324|1767->328|1844->379|2025->538|2055->541|2275->735|2315->759|2354->760|2388->767|2525->877|2539->882|2563->885|2621->915|2636->920|2661->923|2751->986|2765->991|2789->994|2881->1059|2895->1064|2919->1067|2949->1070|2963->1075|2991->1082|3049->1110|3082->1116|3448->1455|3471->1469|3506->1483|3560->1510|3583->1524|3615->1535|3644->1536|3805->1670|3828->1684|3863->1698|3892->1699|4001->1782|4043->1808|4082->1809|4117->1817|4188->1861|4203->1867|4232->1875|4304->1920|4319->1926|4347->1933|4452->2011|4467->2017|4495->2024|4537->2038|4553->2044|4582->2051|4613->2054|4629->2060|4658->2067|4776->2158|4791->2164|4819->2171|4851->2176|4867->2182|4896->2189|5006->2272|5021->2278|5049->2285|5081->2290|5096->2296|5125->2303|5236->2387|5251->2393|5279->2400|5311->2405|5326->2411|5355->2418|5440->2476|5455->2482|5483->2489|5600->2579|5615->2585|5643->2592|5789->2708|5822->2714|6188->3050|6220->3055|6310->3117|6339->3118|6371->3123|6430->3154|6459->3155|6494->3163|6665->3306|6694->3307|6734->3319|6840->3397|6869->3398|6901->3403|6929->3404|6957->3405|7057->3478|7085->3479|7180->3546|7209->3547|7239->3550|7300->3583|7329->3584|7360->3588|7489->3690|7517->3691|7548->3695|7576->3696|7607->3700|7731->3796|7760->3797|7790->3799|7819->3800|7893->3845|7923->3846|7954->3849|8002->3868|8032->3869|8064->3873|8194->3975|8223->3976|8255->3980|8284->3981|8316->3985|8411->4051|8441->4052|8477->4060|8506->4061|8539->4066|8568->4067|8598->4069|8627->4070|8658->4073
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|41->10|41->10|42->11|42->11|44->13|45->14|45->14|46->15|50->19|50->19|50->19|51->20|52->21|52->21|52->21|52->21|52->21|52->21|53->22|53->22|53->22|54->23|54->23|54->23|54->23|54->23|54->23|56->25|57->26|67->36|67->36|67->36|68->37|68->37|68->37|68->37|71->40|71->40|71->40|71->40|76->45|76->45|76->45|77->46|78->47|78->47|78->47|79->48|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|83->52|83->52|83->52|83->52|83->52|83->52|85->54|85->54|85->54|87->56|87->56|87->56|92->61|93->62|105->74|110->79|111->80|111->80|112->81|112->81|112->81|113->82|116->85|116->85|117->86|118->87|118->87|119->88|119->88|119->88|121->90|121->90|124->93|124->93|125->94|125->94|125->94|126->95|128->97|128->97|128->97|128->97|129->98|131->100|131->100|132->101|132->101|134->103|134->103|135->104|135->104|135->104|136->105|138->107|138->107|138->107|138->107|139->108|139->108|139->108|140->109|140->109|141->110|141->110|142->111|142->111|144->113
                  -- GENERATED --
              */
          