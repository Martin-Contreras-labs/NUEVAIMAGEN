
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

object rubroMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Rubro],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listRubro: List[tables.Rubro]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "RUBROS: CREAR/MODIFICAR", "")),format.raw/*9.62*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-10 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH>NOMBRE</TH>
							<TH>OBSERVACIONES</TH>
							<TH style="width: 10px">EDIT</TH>
							<TH style="width: 10px">VIGENTE</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*22.8*/for(lista1 <- listRubro) yield /*22.32*/{_display_(Seq[Any](format.raw/*22.33*/("""
							"""),format.raw/*23.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*24.40*/lista1/*24.46*/.getNombre()),format.raw/*24.58*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*25.40*/lista1/*25.46*/.getObservaciones()),format.raw/*25.65*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick="rubroModifica('"""),_display_(/*27.47*/lista1/*27.53*/.getId()),format.raw/*27.61*/("""');">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
								<td  style="text-align:center;">
									<input type="hidden" id="vigente_"""),_display_(/*32.44*/lista1/*32.50*/.getId()),format.raw/*32.58*/("""" value=""""),_display_(/*32.68*/lista1/*32.74*/.getVigente()),format.raw/*32.87*/("""">
									"""),_display_(if(lista1.getVigente()==1)/*33.37*/{_display_(Seq[Any](format.raw/*33.38*/("""
										"""),format.raw/*34.11*/("""<input type="checkbox" checked onchange="modificaVigente('"""),_display_(/*34.70*/lista1/*34.76*/.getId()),format.raw/*34.84*/("""');">
									""")))}else/*35.15*/{_display_(Seq[Any](format.raw/*35.16*/("""
										"""),format.raw/*36.11*/("""<input type="checkbox" onchange="modificaVigente('"""),_display_(/*36.62*/lista1/*36.68*/.getId()),format.raw/*36.76*/("""');">
									""")))}),format.raw/*37.11*/("""
								"""),format.raw/*38.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*40.9*/("""
					"""),format.raw/*41.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/rubroAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="rubroModifica" method="post" action="/routes2/rubroModifica/">
		<input type="hidden" id="mod_idRubro" name="id_rubro">
	</form>
""")))}),format.raw/*60.2*/("""




"""),format.raw/*65.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*66.31*/("""{"""),format.raw/*66.32*/("""
		  """),format.raw/*67.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*67.36*/("""{"""),format.raw/*67.37*/("""
		    	"""),format.raw/*68.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*70.20*/("""{"""),format.raw/*70.21*/("""
		        	"""),format.raw/*71.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*72.11*/("""}"""),format.raw/*72.12*/("""
		  """),format.raw/*73.5*/("""}"""),format.raw/*73.6*/(""" """),format.raw/*73.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*75.2*/("""}"""),format.raw/*75.3*/(""");
	
	const modificaVigente = (id_rubro) => """),format.raw/*77.40*/("""{"""),format.raw/*77.41*/("""
		"""),format.raw/*78.3*/("""let vigente = $("#vigente_"+id_rubro).val();
		if(vigente==1)"""),format.raw/*79.17*/("""{"""),format.raw/*79.18*/("""
			"""),format.raw/*80.4*/("""modificaVigencia(id_rubro, 0);
			$("#vigente_"+id_rubro).val(0);
		"""),format.raw/*82.3*/("""}"""),format.raw/*82.4*/("""else"""),format.raw/*82.8*/("""{"""),format.raw/*82.9*/("""
			"""),format.raw/*83.4*/("""modificaVigencia(id_rubro, 1);
			$("#vigente_"+id_rubro).val(1);
		"""),format.raw/*85.3*/("""}"""),format.raw/*85.4*/("""
	"""),format.raw/*86.2*/("""}"""),format.raw/*86.3*/("""

	"""),format.raw/*88.2*/("""const modificaVigencia = (id_rubro, valor) => """),format.raw/*88.48*/("""{"""),format.raw/*88.49*/("""
		"""),format.raw/*89.3*/("""var formData = new FormData();
		formData.append('campo',"vigente");
		formData.append('id_rubro',id_rubro);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*93.10*/("""{"""),format.raw/*93.11*/("""
			"""),format.raw/*94.4*/("""url: "/routes2/modificaRubroPorCampoAjax/",
			type: "POST",
			method: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(respuesta)"""),format.raw/*101.32*/("""{"""),format.raw/*101.33*/("""
				"""),format.raw/*102.5*/("""if(respuesta=="error")"""),format.raw/*102.27*/("""{"""),format.raw/*102.28*/("""
					"""),format.raw/*103.6*/("""alertify.alert(msgError, function () """),format.raw/*103.43*/("""{"""),format.raw/*103.44*/("""
						"""),format.raw/*104.7*/("""location.href = "/";
					"""),format.raw/*105.6*/("""}"""),format.raw/*105.7*/(""");
				"""),format.raw/*106.5*/("""}"""),format.raw/*106.6*/("""
			"""),format.raw/*107.4*/("""}"""),format.raw/*107.5*/("""
		"""),format.raw/*108.3*/("""}"""),format.raw/*108.4*/(""");
	"""),format.raw/*109.2*/("""}"""),format.raw/*109.3*/("""
	
	"""),format.raw/*111.2*/("""const rubroModifica = (id_rubro) => """),format.raw/*111.38*/("""{"""),format.raw/*111.39*/("""
		"""),format.raw/*112.3*/("""let nombre = $("#"+id_rubro).text();
				$("#mod_idRubro").val(id_rubro);
				document.getElementById("rubroModifica").submit();
	"""),format.raw/*115.2*/("""}"""),format.raw/*115.3*/("""
"""),format.raw/*116.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listRubro:List[tables.Rubro]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listRubro)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Rubro]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listRubro) => apply(mapDiccionario,mapPermiso,userMnu,listRubro)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/rubroMantencion.scala.html
                  HASH: 7b316ff83d6d052da9ee96121c3a9ec43b65cc7a
                  MATRIX: 1033->1|1254->129|1287->137|1303->145|1342->147|1370->150|1438->198|1466->200|1542->251|1620->309|1650->312|2128->764|2168->788|2207->789|2242->797|2313->841|2328->847|2361->859|2433->904|2448->910|2488->929|2608->1022|2623->1028|2652->1036|2854->1211|2869->1217|2898->1225|2935->1235|2950->1241|2984->1254|3050->1293|3089->1294|3128->1305|3214->1364|3229->1370|3258->1378|3297->1398|3336->1399|3375->1410|3453->1461|3468->1467|3497->1475|3544->1491|3580->1500|3637->1527|3670->1533|4391->2224|4423->2229|4513->2291|4542->2292|4574->2297|4633->2328|4662->2329|4697->2337|4839->2451|4868->2452|4908->2464|5014->2542|5043->2543|5075->2548|5103->2549|5131->2550|5231->2623|5259->2624|5331->2668|5360->2669|5390->2672|5479->2733|5508->2734|5539->2738|5634->2806|5662->2807|5693->2811|5721->2812|5752->2816|5847->2884|5875->2885|5904->2887|5932->2888|5962->2891|6036->2937|6065->2938|6095->2941|6275->3093|6304->3094|6335->3098|6557->3291|6587->3292|6620->3297|6671->3319|6701->3320|6735->3326|6801->3363|6831->3364|6866->3371|6920->3397|6949->3398|6984->3405|7013->3406|7045->3410|7074->3411|7105->3414|7134->3415|7166->3419|7195->3420|7227->3424|7292->3460|7322->3461|7353->3464|7511->3594|7540->3595|7569->3596
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|53->22|54->23|55->24|55->24|55->24|56->25|56->25|56->25|58->27|58->27|58->27|63->32|63->32|63->32|63->32|63->32|63->32|64->33|64->33|65->34|65->34|65->34|65->34|66->35|66->35|67->36|67->36|67->36|67->36|68->37|69->38|71->40|72->41|91->60|96->65|97->66|97->66|98->67|98->67|98->67|99->68|101->70|101->70|102->71|103->72|103->72|104->73|104->73|104->73|106->75|106->75|108->77|108->77|109->78|110->79|110->79|111->80|113->82|113->82|113->82|113->82|114->83|116->85|116->85|117->86|117->86|119->88|119->88|119->88|120->89|124->93|124->93|125->94|132->101|132->101|133->102|133->102|133->102|134->103|134->103|134->103|135->104|136->105|136->105|137->106|137->106|138->107|138->107|139->108|139->108|140->109|140->109|142->111|142->111|142->111|143->112|146->115|146->115|147->116
                  -- GENERATED --
              */
          