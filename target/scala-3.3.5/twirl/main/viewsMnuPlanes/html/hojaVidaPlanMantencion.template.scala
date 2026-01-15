
package viewsMnuPlanes.html

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

object hojaVidaPlanMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaPlanes: List[tables.PlanMantencion]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "PLANES DE MANTENCION EQUIPOS","CREAR/MODIFICAR/ELIMINAR")),format.raw/*9.90*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH  style= "text-align: center;">GRUPO</TH>
					        <TH  style= "text-align: center;">CODIGO</TH>
							<TH  style= "text-align: center;">EQUIPO</TH>
							
							<TH  style= "text-align: center;">PLAN</TH>
							<TH style="text-align:center">FECHA<BR>LECTURA</TH>
							<TH style="text-align:center">UN</TH>
							<TH style="text-align:center">LECTURA</TH>
							<TH style="text-align:center">ROTACION</TH>
							<TH style="text-align:center">CONSUMO<BR>PROM MES</TH>
							<TH style="text-align:center">PROXIMA<br>MANTENCION</TH>
							<TH style="text-align:center">FECHA ESTIMADA<br>MANTENCION</TH>
							
							<TH style="text-align:center">CONSUMO<BR>ESTIMADO</TH>
							
							<TH style="text-align:center" title="Verde al día. Amarillo esta entre el 25% y 10% de la rotación. Rojo se debe hacer mantención.">DIFERENCIA</TH>
								
							<TH style="text-align:center" width="5%" >EDIT</TH>
							<TH style="text-align:center" width="5%" >DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*37.8*/for(lista1 <- listaPlanes) yield /*37.34*/{_display_(Seq[Any](format.raw/*37.35*/("""
							"""),format.raw/*38.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*39.39*/lista1/*39.45*/.equipoGrupo),format.raw/*39.57*/("""</td>
								<td style="text-align:center;">"""),_display_(/*40.41*/lista1/*40.47*/.equipoCodigo),format.raw/*40.60*/("""</td>
								<td style="text-align:left;">"""),_display_(/*41.39*/lista1/*41.45*/.equipoNombre),format.raw/*41.58*/("""</td>
								
								<td style="text-align:left;">"""),_display_(/*43.39*/lista1/*43.45*/.tipoPlanNombre),format.raw/*43.60*/("""</td>
								<td style="text-align:center">
									<div style="display:none">"""),_display_(/*45.37*/utilities/*45.46*/.Fechas.AAMMDD(lista1.fechaReset)),format.raw/*45.79*/("""</div>
									<input type="hidden" class="fechaReset" value=""""),_display_(/*46.58*/utilities/*46.67*/.Fechas.AAMMDD(lista1.fechaReset)),format.raw/*46.100*/("""">
									"""),_display_(/*47.11*/lista1/*47.17*/.fechaReset),format.raw/*47.28*/("""
								"""),format.raw/*48.9*/("""</td>
								<td style="text-align:center">"""),_display_(/*49.40*/lista1/*49.46*/.unidadMantencion),format.raw/*49.63*/("""</td>
								<td style="text-align:right">
									<input type="hidden" class="estadoActual" value=""""),_display_(/*51.60*/lista1/*51.66*/.estadoActual),format.raw/*51.79*/("""">
									"""),_display_(/*52.11*/lista1/*52.17*/.estadoActual),format.raw/*52.30*/("""
								"""),format.raw/*53.9*/("""</td>
								<td style="text-align:right">"""),_display_(/*54.39*/lista1/*54.45*/.cadaNEstimado),format.raw/*54.59*/("""</td>
								<td style="text-align:right">
									<input type="hidden" class="consumoEstimadoPorMes" value=""""),_display_(/*56.69*/lista1/*56.75*/.consumoEstimadoPorMes),format.raw/*56.97*/("""">
									"""),_display_(/*57.11*/lista1/*57.17*/.consumoEstimadoPorMes),format.raw/*57.39*/("""
								"""),format.raw/*58.9*/("""</td>
								
								
								<td style="text-align:right;">
									<input type="hidden" class="proximaMantencion" value=""""),_display_(/*62.65*/lista1/*62.71*/.proximaMantencion),format.raw/*62.89*/("""">
									"""),_display_(/*63.11*/lista1/*63.17*/.proximaMantencion),format.raw/*63.35*/("""
								"""),format.raw/*64.9*/("""</td>
								
								<td style="text-align:center">
									<input type="date" class="fechaEstimada form-control center" disabled>
								</td>
								
								<td style="text-align:right;">"""),_display_(/*70.40*/lista1/*70.46*/.consumoEstimadoAHoy),format.raw/*70.66*/("""</td>
								
								"""),_display_(if(MnuPlanes.esMayor(lista1.consumoEstimadoAHoy,lista1.proximaMantencion))/*72.84*/{_display_(Seq[Any](format.raw/*72.85*/("""
									"""),format.raw/*73.10*/("""<td style="text-align:right; background-color:red" title="Falta Mantención Urgente">"""),_display_(/*73.95*/lista1/*73.101*/.diferencia),format.raw/*73.112*/("""</td>
								""")))}else/*74.14*/{_display_(Seq[Any](format.raw/*74.15*/("""
									"""),_display_(if(MnuPlanes.pintaColor(lista1.consumoEstimadoAHoy,lista1.proximaMantencion,lista1.cadaNEstimado).equals("verde"))/*75.125*/{_display_(Seq[Any](format.raw/*75.126*/("""
										"""),format.raw/*76.11*/("""<td style="text-align:right; background-color:green" title="Mantención al Día">"""),_display_(/*76.91*/lista1/*76.97*/.diferencia),format.raw/*76.108*/("""</td>
									""")))}else/*77.15*/{_display_(Seq[Any](format.raw/*77.16*/("""
										"""),_display_(if(MnuPlanes.pintaColor(lista1.consumoEstimadoAHoy,lista1.proximaMantencion,lista1.cadaNEstimado).equals("amarillo"))/*78.129*/{_display_(Seq[Any](format.raw/*78.130*/("""
											"""),format.raw/*79.12*/("""<td style="text-align:right; background-color:yellow" title="Próximo a requerir Mantención">"""),_display_(/*79.105*/lista1/*79.111*/.diferencia),format.raw/*79.122*/("""</td>
										""")))}else/*80.16*/{_display_(Seq[Any](format.raw/*80.17*/("""
											"""),format.raw/*81.12*/("""<td style="text-align:right; background-color:red" title="Requiere Mantención">"""),_display_(/*81.92*/lista1/*81.98*/.diferencia),format.raw/*81.109*/("""</td>
										""")))}),format.raw/*82.12*/("""
									""")))}),format.raw/*83.11*/("""
								""")))}),format.raw/*84.10*/("""
								
								"""),format.raw/*86.9*/("""<td  style="text-align:center;">
									<form id="form_"""),_display_(/*87.26*/lista1/*87.32*/.id_tipoPlan),format.raw/*87.44*/("""&"""),_display_(/*87.46*/lista1/*87.52*/.id_equipo),format.raw/*87.62*/("""" method="post" action="/hojaVidaPlanModifica/">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*88.57*/lista1/*88.63*/.id_equipo),format.raw/*88.73*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*89.63*/lista1/*89.69*/.id_tipoPlan),format.raw/*89.81*/("""&"""),_display_(/*89.83*/lista1/*89.89*/.id_equipo),format.raw/*89.99*/("""").submit();'>
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									</form>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarTipo('"""),_display_(/*95.47*/lista1/*95.53*/.id_tipoPlan),format.raw/*95.65*/("""','"""),_display_(/*95.69*/lista1/*95.75*/.id_equipo),format.raw/*95.85*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*100.9*/("""
					"""),format.raw/*101.6*/("""</tbody>
				</table>
			</div>
		</div>	
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<form method="post" action="/hojaVidaPlanCrear/">
						<input type="submit"  value="INCORPORAR EQUIPO" class="btn btn-success btn-sm rounded-pill btn-block">
					</form>
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/hojaVidaPlanElimina/">
		<input type="hidden" id="form_id_tipoPlan" name="id_tipoPlan">
		<input type="hidden" id="form_id_equipo" name="id_equipo">
	</form>
""")))}),format.raw/*123.2*/("""




"""),format.raw/*128.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*129.31*/("""{"""),format.raw/*129.32*/("""
		"""),format.raw/*130.3*/("""calcFechaProxima();
		  $('#tablaPrincipal').DataTable("""),format.raw/*131.36*/("""{"""),format.raw/*131.37*/("""
		    	"""),format.raw/*132.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 0, "asc" ],[ 1, "asc" ],[ 3, "asc" ]],
		    	"language": """),format.raw/*135.20*/("""{"""),format.raw/*135.21*/("""
		        	"""),format.raw/*136.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*137.11*/("""}"""),format.raw/*137.12*/("""
		  """),format.raw/*138.5*/("""}"""),format.raw/*138.6*/(""" """),format.raw/*138.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*140.2*/("""}"""),format.raw/*140.3*/(""");
	
	const calcFechaProxima = () =>"""),format.raw/*142.32*/("""{"""),format.raw/*142.33*/("""
		"""),format.raw/*143.3*/("""let filas = $(".estadoActual").length;
			let hoy = new Date();
			
			for(let i=0; i<filas; i++)"""),format.raw/*146.30*/("""{"""),format.raw/*146.31*/("""
				"""),format.raw/*147.5*/("""let fecha = $(".fechaReset")[i].value;
				let proxima =  $(".proximaMantencion")[i].value;
				proxima = proxima.replace(/,/g,'');
				let actual =  $(".estadoActual")[i].value;
				actual = actual.replace(/,/g,'');
				let rotacion =  $(".consumoEstimadoPorMes")[i].value;
				rotacion = rotacion.replace(/,/g,'');
				let dif = parseFloat(proxima) - parseFloat(actual);
				if(dif <= 0 || parseFloat(rotacion) <= 0)"""),format.raw/*155.46*/("""{"""),format.raw/*155.47*/("""
					"""),format.raw/*156.6*/("""$(".fechaEstimada")[i].value = fechaAAMMDD(hoy);
				"""),format.raw/*157.5*/("""}"""),format.raw/*157.6*/("""else"""),format.raw/*157.10*/("""{"""),format.raw/*157.11*/("""
					"""),format.raw/*158.6*/("""let diasASumar = dif/rotacion * 30;
					let aux = fecha.split("-");
					let auxFecha = new Date(aux[0],parseInt(aux[1])-1,aux[2]);
					auxFecha.setDate(auxFecha.getDate() + diasASumar);
					$(".fechaEstimada")[i].value = fechaAAMMDD(auxFecha);
				"""),format.raw/*163.5*/("""}"""),format.raw/*163.6*/("""
			"""),format.raw/*164.4*/("""}"""),format.raw/*164.5*/("""
	"""),format.raw/*165.2*/("""}"""),format.raw/*165.3*/("""
	
	"""),format.raw/*167.2*/("""const eliminarTipo = (id_tipoPlan, id_equipo) => """),format.raw/*167.51*/("""{"""),format.raw/*167.52*/("""
		"""),format.raw/*168.3*/("""alertify.confirm("Esta seguro de eliminar este plan de mantención", function (e) """),format.raw/*168.84*/("""{"""),format.raw/*168.85*/("""
			"""),format.raw/*169.4*/("""if (e) """),format.raw/*169.11*/("""{"""),format.raw/*169.12*/("""
				"""),format.raw/*170.5*/("""$("#form_id_tipoPlan").val(id_tipoPlan);
				$("#form_id_equipo").val(id_equipo);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*173.4*/("""}"""),format.raw/*173.5*/("""
		"""),format.raw/*174.3*/("""}"""),format.raw/*174.4*/(""");
	"""),format.raw/*175.2*/("""}"""),format.raw/*175.3*/("""
"""),format.raw/*176.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaPlanes:List[tables.PlanMantencion]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaPlanes)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaPlanes) => apply(mapDiccionario,mapPermiso,userMnu,listaPlanes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/hojaVidaPlanMantencion.scala.html
                  HASH: 1610ebbfc47a038fb2cec39c51ad852b6a60209e
                  MATRIX: 1048->1|1280->140|1313->148|1329->156|1368->158|1396->161|1464->209|1492->211|1568->262|1674->348|1704->351|3031->1652|3073->1678|3112->1679|3147->1687|3217->1730|3232->1736|3265->1748|3338->1794|3353->1800|3387->1813|3458->1857|3473->1863|3507->1876|3587->1929|3602->1935|3638->1950|3746->2031|3764->2040|3818->2073|3909->2137|3927->2146|3982->2179|4022->2192|4037->2198|4069->2209|4105->2218|4177->2263|4192->2269|4230->2286|4360->2389|4375->2395|4409->2408|4449->2421|4464->2427|4498->2440|4534->2449|4605->2493|4620->2499|4655->2513|4794->2625|4809->2631|4852->2653|4892->2666|4907->2672|4950->2694|4986->2703|5140->2830|5155->2836|5194->2854|5234->2867|5249->2873|5288->2891|5324->2900|5547->3096|5562->3102|5603->3122|5728->3220|5767->3221|5805->3231|5917->3316|5933->3322|5966->3333|6004->3352|6043->3353|6196->3478|6236->3479|6275->3490|6382->3570|6397->3576|6430->3587|6469->3607|6508->3608|6665->3737|6705->3738|6745->3750|6866->3843|6882->3849|6915->3860|6955->3881|6994->3882|7034->3894|7141->3974|7156->3980|7189->3991|7237->4008|7279->4019|7320->4029|7365->4047|7450->4105|7465->4111|7498->4123|7527->4125|7542->4131|7573->4141|7705->4246|7720->4252|7751->4262|7843->4327|7858->4333|7891->4345|7920->4347|7935->4353|7966->4363|8199->4569|8214->4575|8247->4587|8278->4591|8293->4597|8324->4607|8462->4714|8496->4720|9316->5509|9349->5514|9440->5576|9470->5577|9501->5580|9585->5635|9615->5636|9651->5644|9849->5813|9879->5814|9920->5826|10027->5904|10057->5905|10090->5910|10119->5911|10148->5912|10249->5985|10278->5986|10343->6022|10373->6023|10404->6026|10530->6123|10560->6124|10593->6129|11040->6547|11070->6548|11104->6554|11185->6607|11214->6608|11247->6612|11277->6613|11311->6619|11591->6871|11620->6872|11652->6876|11681->6877|11711->6879|11740->6880|11772->6884|11850->6933|11880->6934|11911->6937|12021->7018|12051->7019|12083->7023|12119->7030|12149->7031|12182->7036|12350->7176|12379->7177|12410->7180|12439->7181|12471->7185|12500->7186|12529->7187
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|68->37|68->37|68->37|69->38|70->39|70->39|70->39|71->40|71->40|71->40|72->41|72->41|72->41|74->43|74->43|74->43|76->45|76->45|76->45|77->46|77->46|77->46|78->47|78->47|78->47|79->48|80->49|80->49|80->49|82->51|82->51|82->51|83->52|83->52|83->52|84->53|85->54|85->54|85->54|87->56|87->56|87->56|88->57|88->57|88->57|89->58|93->62|93->62|93->62|94->63|94->63|94->63|95->64|101->70|101->70|101->70|103->72|103->72|104->73|104->73|104->73|104->73|105->74|105->74|106->75|106->75|107->76|107->76|107->76|107->76|108->77|108->77|109->78|109->78|110->79|110->79|110->79|110->79|111->80|111->80|112->81|112->81|112->81|112->81|113->82|114->83|115->84|117->86|118->87|118->87|118->87|118->87|118->87|118->87|119->88|119->88|119->88|120->89|120->89|120->89|120->89|120->89|120->89|126->95|126->95|126->95|126->95|126->95|126->95|131->100|132->101|154->123|159->128|160->129|160->129|161->130|162->131|162->131|163->132|166->135|166->135|167->136|168->137|168->137|169->138|169->138|169->138|171->140|171->140|173->142|173->142|174->143|177->146|177->146|178->147|186->155|186->155|187->156|188->157|188->157|188->157|188->157|189->158|194->163|194->163|195->164|195->164|196->165|196->165|198->167|198->167|198->167|199->168|199->168|199->168|200->169|200->169|200->169|201->170|204->173|204->173|205->174|205->174|206->175|206->175|207->176
                  -- GENERATED --
              */
          