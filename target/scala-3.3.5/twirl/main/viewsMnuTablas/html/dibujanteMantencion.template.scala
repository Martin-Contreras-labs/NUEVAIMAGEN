
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

object dibujanteMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Dibujante],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listDibujante: List[tables.Dibujante]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "DIBUJANTES/PROYECTISTAS: CREAR/MODIFICAR", "")),format.raw/*9.79*/("""
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
						"""),_display_(/*22.8*/for(lista1 <- listDibujante) yield /*22.36*/{_display_(Seq[Any](format.raw/*22.37*/("""
							"""),format.raw/*23.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*24.40*/lista1/*24.46*/.getNombre()),format.raw/*24.58*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*25.40*/lista1/*25.46*/.getObservaciones()),format.raw/*25.65*/("""</td>
								<td  style="text-align:center;">
									<a href="#" onclick="dibujanteModifica('"""),_display_(/*27.51*/lista1/*27.57*/.getId()),format.raw/*27.65*/("""');">
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
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/dibujanteAgrega/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="dibujanteModifica" method="post" action="/routes2/dibujanteModifica/">
		<input type="hidden" id="mod_idDibujante" name="id_dibujante">
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
	
	const modificaVigente = (id_dibujante) => """),format.raw/*77.44*/("""{"""),format.raw/*77.45*/("""
		"""),format.raw/*78.3*/("""let vigente = $("#vigente_"+id_dibujante).val();
		if(vigente==1)"""),format.raw/*79.17*/("""{"""),format.raw/*79.18*/("""
			"""),format.raw/*80.4*/("""modificaVigencia(id_dibujante, 0);
			$("#vigente_"+id_dibujante).val(0);
		"""),format.raw/*82.3*/("""}"""),format.raw/*82.4*/("""else"""),format.raw/*82.8*/("""{"""),format.raw/*82.9*/("""
			"""),format.raw/*83.4*/("""modificaVigencia(id_dibujante, 1);
			$("#vigente_"+id_dibujante).val(1);
		"""),format.raw/*85.3*/("""}"""),format.raw/*85.4*/("""
	"""),format.raw/*86.2*/("""}"""),format.raw/*86.3*/("""

	"""),format.raw/*88.2*/("""const modificaVigencia = (id_dibujante, valor) => """),format.raw/*88.52*/("""{"""),format.raw/*88.53*/("""
		"""),format.raw/*89.3*/("""var formData = new FormData();
		formData.append('campo',"vigente");
		formData.append('id_dibujante',id_dibujante);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*93.10*/("""{"""),format.raw/*93.11*/("""
			"""),format.raw/*94.4*/("""url: "/routes2/modificaDibujantePorCampoAjax/",
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
	
	"""),format.raw/*111.2*/("""const dibujanteModifica = (id_dibujante) => """),format.raw/*111.46*/("""{"""),format.raw/*111.47*/("""
		"""),format.raw/*112.3*/("""let nombre = $("#"+id_dibujante).text();
				$("#mod_idDibujante").val(id_dibujante);
				document.getElementById("dibujanteModifica").submit();
	"""),format.raw/*115.2*/("""}"""),format.raw/*115.3*/("""
"""),format.raw/*116.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listDibujante:List[tables.Dibujante]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listDibujante)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Dibujante]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listDibujante) => apply(mapDiccionario,mapPermiso,userMnu,listDibujante)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/dibujanteMantencion.scala.html
                  HASH: 983ef75b3946e45d65ccfb23ffdf2dd89805b8b6
                  MATRIX: 1040->1|1269->137|1302->145|1318->153|1357->155|1385->158|1453->206|1481->208|1557->259|1652->334|1682->337|2160->789|2204->817|2243->818|2278->826|2349->870|2364->876|2397->888|2469->933|2484->939|2524->958|2648->1055|2663->1061|2692->1069|2894->1244|2909->1250|2938->1258|2975->1268|2990->1274|3024->1287|3090->1326|3129->1327|3168->1338|3254->1397|3269->1403|3298->1411|3337->1431|3376->1432|3415->1443|3493->1494|3508->1500|3537->1508|3584->1524|3620->1533|3677->1560|3710->1566|4451->2277|4483->2282|4573->2344|4602->2345|4634->2350|4693->2381|4722->2382|4757->2390|4899->2504|4928->2505|4968->2517|5074->2595|5103->2596|5135->2601|5163->2602|5191->2603|5291->2676|5319->2677|5395->2725|5424->2726|5454->2729|5547->2794|5576->2795|5607->2799|5710->2875|5738->2876|5769->2880|5797->2881|5828->2885|5931->2961|5959->2962|5988->2964|6016->2965|6046->2968|6124->3018|6153->3019|6183->3022|6371->3182|6400->3183|6431->3187|6657->3384|6687->3385|6720->3390|6771->3412|6801->3413|6835->3419|6901->3456|6931->3457|6966->3464|7020->3490|7049->3491|7084->3498|7113->3499|7145->3503|7174->3504|7205->3507|7234->3508|7266->3512|7295->3513|7327->3517|7400->3561|7430->3562|7461->3565|7635->3711|7664->3712|7693->3713
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|53->22|54->23|55->24|55->24|55->24|56->25|56->25|56->25|58->27|58->27|58->27|63->32|63->32|63->32|63->32|63->32|63->32|64->33|64->33|65->34|65->34|65->34|65->34|66->35|66->35|67->36|67->36|67->36|67->36|68->37|69->38|71->40|72->41|91->60|96->65|97->66|97->66|98->67|98->67|98->67|99->68|101->70|101->70|102->71|103->72|103->72|104->73|104->73|104->73|106->75|106->75|108->77|108->77|109->78|110->79|110->79|111->80|113->82|113->82|113->82|113->82|114->83|116->85|116->85|117->86|117->86|119->88|119->88|119->88|120->89|124->93|124->93|125->94|132->101|132->101|133->102|133->102|133->102|134->103|134->103|134->103|135->104|136->105|136->105|137->106|137->106|138->107|138->107|139->108|139->108|140->109|140->109|142->111|142->111|142->111|143->112|146->115|146->115|147->116
                  -- GENERATED --
              */
          