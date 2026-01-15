
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

object hojaVidaReportLista extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaPlanes: List[tables.PlanMantencion]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "HOJAS DE VIDA POR EQUIPOS","SELECCIONAR")),format.raw/*9.74*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
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
								
							<TH style="text-align:center">SELECT</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*35.8*/for(lista1 <- listaPlanes) yield /*35.34*/{_display_(Seq[Any](format.raw/*35.35*/("""
							"""),format.raw/*36.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*37.39*/lista1/*37.45*/.equipoGrupo),format.raw/*37.57*/("""</td>
								<td style="text-align:center;">"""),_display_(/*38.41*/lista1/*38.47*/.equipoCodigo),format.raw/*38.60*/("""</td>
								<td style="text-align:left;">"""),_display_(/*39.39*/lista1/*39.45*/.equipoNombre),format.raw/*39.58*/("""</td>
								
								<td style="text-align:left;">"""),_display_(/*41.39*/lista1/*41.45*/.tipoPlanNombre),format.raw/*41.60*/("""</td>
								<td style="text-align:center">
									<div style="display:none">"""),_display_(/*43.37*/utilities/*43.46*/.Fechas.AAMMDD(lista1.fechaReset)),format.raw/*43.79*/("""</div>
									<input type="hidden" class="fechaReset" value=""""),_display_(/*44.58*/utilities/*44.67*/.Fechas.AAMMDD(lista1.fechaReset)),format.raw/*44.100*/("""">
									"""),_display_(/*45.11*/lista1/*45.17*/.fechaReset),format.raw/*45.28*/("""
								"""),format.raw/*46.9*/("""</td>
								<td style="text-align:center">"""),_display_(/*47.40*/lista1/*47.46*/.unidadMantencion),format.raw/*47.63*/("""</td>
								<td style="text-align:right">
									<input type="hidden" class="estadoActual" value=""""),_display_(/*49.60*/lista1/*49.66*/.estadoActual),format.raw/*49.79*/("""">
									"""),_display_(/*50.11*/lista1/*50.17*/.estadoActual),format.raw/*50.30*/("""
								"""),format.raw/*51.9*/("""</td>
								<td style="text-align:right">"""),_display_(/*52.39*/lista1/*52.45*/.cadaNEstimado),format.raw/*52.59*/("""</td>
								<td style="text-align:right">
									<input type="hidden" class="consumoEstimadoPorMes" value=""""),_display_(/*54.69*/lista1/*54.75*/.consumoEstimadoPorMes),format.raw/*54.97*/("""">
									"""),_display_(/*55.11*/lista1/*55.17*/.consumoEstimadoPorMes),format.raw/*55.39*/("""
								"""),format.raw/*56.9*/("""</td>
								
								<td style="text-align:right;">
									<input type="hidden" class="proximaMantencion" value=""""),_display_(/*59.65*/lista1/*59.71*/.proximaMantencion),format.raw/*59.89*/("""">
									"""),_display_(/*60.11*/lista1/*60.17*/.proximaMantencion),format.raw/*60.35*/("""
								"""),format.raw/*61.9*/("""</td>
								
								<td style="text-align:center">
									<input type="date" class="fechaEstimada form-control center" disabled>
								</td>
								
								<td style="text-align:right;">"""),_display_(/*67.40*/lista1/*67.46*/.consumoEstimadoAHoy),format.raw/*67.66*/("""</td>
								
								"""),_display_(if(MnuPlanes.esMayor(lista1.consumoEstimadoAHoy,lista1.proximaMantencion))/*69.84*/{_display_(Seq[Any](format.raw/*69.85*/("""
									"""),format.raw/*70.10*/("""<td style="text-align:right; background-color:red" title="Falta Mantención Urgente">"""),_display_(/*70.95*/lista1/*70.101*/.diferencia),format.raw/*70.112*/("""</td>
								""")))}else/*71.14*/{_display_(Seq[Any](format.raw/*71.15*/("""
									"""),_display_(if(MnuPlanes.pintaColor(lista1.consumoEstimadoAHoy,lista1.proximaMantencion,lista1.cadaNEstimado).equals("verde"))/*72.125*/{_display_(Seq[Any](format.raw/*72.126*/("""
										"""),format.raw/*73.11*/("""<td style="text-align:right; background-color:green" title="Mantención al Día">"""),_display_(/*73.91*/lista1/*73.97*/.diferencia),format.raw/*73.108*/("""</td>
									""")))}else/*74.15*/{_display_(Seq[Any](format.raw/*74.16*/("""
										"""),_display_(if(MnuPlanes.pintaColor(lista1.consumoEstimadoAHoy,lista1.proximaMantencion,lista1.cadaNEstimado).equals("amarillo"))/*75.129*/{_display_(Seq[Any](format.raw/*75.130*/("""
											"""),format.raw/*76.12*/("""<td style="text-align:right; background-color:yellow" title="Próximo a requerir Mantención">"""),_display_(/*76.105*/lista1/*76.111*/.diferencia),format.raw/*76.122*/("""</td>
										""")))}else/*77.16*/{_display_(Seq[Any](format.raw/*77.17*/("""
											"""),format.raw/*78.12*/("""<td style="text-align:right; background-color:red" title="Requiere Mantención">"""),_display_(/*78.92*/lista1/*78.98*/.diferencia),format.raw/*78.109*/("""</td>
										""")))}),format.raw/*79.12*/("""
									""")))}),format.raw/*80.11*/("""
								""")))}),format.raw/*81.10*/("""
								
								"""),format.raw/*83.9*/("""<td  style="text-align:center;">
									<form id="formhoja_"""),_display_(/*84.30*/lista1/*84.36*/.id_tipoPlan),format.raw/*84.48*/("""&"""),_display_(/*84.50*/lista1/*84.56*/.id_equipo),format.raw/*84.66*/("""" method="post" action="/hojaVidaReportDetalle/">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*85.57*/lista1/*85.63*/.id_equipo),format.raw/*85.73*/("""">
										<a href="#" onclick='document.getElementById("formhoja_"""),_display_(/*86.67*/lista1/*86.73*/.id_tipoPlan),format.raw/*86.85*/("""&"""),_display_(/*86.87*/lista1/*86.93*/.id_equipo),format.raw/*86.103*/("""").submit();'>
											<kbd style="background-color: rgb(50, 215, 75)">Select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*92.9*/("""
					"""),format.raw/*93.6*/("""</tbody>
				</table>
			</div>
		</div>	
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
""")))}),format.raw/*106.2*/("""




"""),format.raw/*111.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*112.31*/("""{"""),format.raw/*112.32*/("""
		"""),format.raw/*113.3*/("""calcFechaProxima();
		  $('#tablaPrincipal').DataTable("""),format.raw/*114.36*/("""{"""),format.raw/*114.37*/("""
		    	"""),format.raw/*115.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 0, "asc" ],[ 1, "asc" ],[ 3, "asc" ]],
		    	"language": """),format.raw/*118.20*/("""{"""),format.raw/*118.21*/("""
		        	"""),format.raw/*119.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*120.11*/("""}"""),format.raw/*120.12*/("""
		  """),format.raw/*121.5*/("""}"""),format.raw/*121.6*/(""" """),format.raw/*121.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*123.2*/("""}"""),format.raw/*123.3*/(""");
	
	const calcFechaProxima = () =>"""),format.raw/*125.32*/("""{"""),format.raw/*125.33*/("""
		"""),format.raw/*126.3*/("""let filas = $(".estadoActual").length;
			let hoy = new Date();
			
			for(let i=0; i<filas; i++)"""),format.raw/*129.30*/("""{"""),format.raw/*129.31*/("""
				"""),format.raw/*130.5*/("""let fecha = $(".fechaReset")[i].value;
				let proxima =  $(".proximaMantencion")[i].value;
				proxima = proxima.replace(/,/g,'');
				let actual =  $(".estadoActual")[i].value;
				actual = actual.replace(/,/g,'');
				let rotacion =  $(".consumoEstimadoPorMes")[i].value;
				rotacion = rotacion.replace(/,/g,'');
				let dif = parseFloat(proxima) - parseFloat(actual);
				if(dif <= 0 || parseFloat(rotacion) <= 0)"""),format.raw/*138.46*/("""{"""),format.raw/*138.47*/("""
					"""),format.raw/*139.6*/("""$(".fechaEstimada")[i].value = fechaAAMMDD(hoy);
				"""),format.raw/*140.5*/("""}"""),format.raw/*140.6*/("""else"""),format.raw/*140.10*/("""{"""),format.raw/*140.11*/("""
					"""),format.raw/*141.6*/("""let diasASumar = dif/rotacion * 30;
					let aux = fecha.split("-");
					let auxFecha = new Date(aux[0],parseInt(aux[1])-1,aux[2]);
					auxFecha.setDate(auxFecha.getDate() + diasASumar);
					$(".fechaEstimada")[i].value = fechaAAMMDD(auxFecha);
				"""),format.raw/*146.5*/("""}"""),format.raw/*146.6*/("""
			"""),format.raw/*147.4*/("""}"""),format.raw/*147.5*/("""
	"""),format.raw/*148.2*/("""}"""),format.raw/*148.3*/("""
	
"""),format.raw/*150.1*/("""</script>


		
		
	
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
                  SOURCE: app/viewsMnuPlanes/hojaVidaReportLista.scala.html
                  HASH: dea56f2c17d07b1a1394d3406dee4f20b7ad1a79
                  MATRIX: 1045->1|1277->140|1310->148|1326->156|1365->158|1393->161|1461->209|1489->211|1565->262|1655->332|1685->335|2936->1560|2978->1586|3017->1587|3052->1595|3122->1638|3137->1644|3170->1656|3243->1702|3258->1708|3292->1721|3363->1765|3378->1771|3412->1784|3492->1837|3507->1843|3543->1858|3651->1939|3669->1948|3723->1981|3814->2045|3832->2054|3887->2087|3927->2100|3942->2106|3974->2117|4010->2126|4082->2171|4097->2177|4135->2194|4265->2297|4280->2303|4314->2316|4354->2329|4369->2335|4403->2348|4439->2357|4510->2401|4525->2407|4560->2421|4699->2533|4714->2539|4757->2561|4797->2574|4812->2580|4855->2602|4891->2611|5036->2729|5051->2735|5090->2753|5130->2766|5145->2772|5184->2790|5220->2799|5443->2995|5458->3001|5499->3021|5624->3119|5663->3120|5701->3130|5813->3215|5829->3221|5862->3232|5900->3251|5939->3252|6092->3377|6132->3378|6171->3389|6278->3469|6293->3475|6326->3486|6365->3506|6404->3507|6561->3636|6601->3637|6641->3649|6762->3742|6778->3748|6811->3759|6851->3780|6890->3781|6930->3793|7037->3873|7052->3879|7085->3890|7133->3907|7175->3918|7216->3928|7261->3946|7350->4008|7365->4014|7398->4026|7427->4028|7442->4034|7473->4044|7606->4150|7621->4156|7652->4166|7748->4235|7763->4241|7796->4253|7825->4255|7840->4261|7872->4271|8056->4425|8089->4431|8460->4771|8493->4776|8584->4838|8614->4839|8645->4842|8729->4897|8759->4898|8795->4906|8993->5075|9023->5076|9064->5088|9171->5166|9201->5167|9234->5172|9263->5173|9292->5174|9393->5247|9422->5248|9487->5284|9517->5285|9548->5288|9674->5385|9704->5386|9737->5391|10184->5809|10214->5810|10248->5816|10329->5869|10358->5870|10391->5874|10421->5875|10455->5881|10735->6133|10764->6134|10796->6138|10825->6139|10855->6141|10884->6142|10915->6145
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|66->35|66->35|66->35|67->36|68->37|68->37|68->37|69->38|69->38|69->38|70->39|70->39|70->39|72->41|72->41|72->41|74->43|74->43|74->43|75->44|75->44|75->44|76->45|76->45|76->45|77->46|78->47|78->47|78->47|80->49|80->49|80->49|81->50|81->50|81->50|82->51|83->52|83->52|83->52|85->54|85->54|85->54|86->55|86->55|86->55|87->56|90->59|90->59|90->59|91->60|91->60|91->60|92->61|98->67|98->67|98->67|100->69|100->69|101->70|101->70|101->70|101->70|102->71|102->71|103->72|103->72|104->73|104->73|104->73|104->73|105->74|105->74|106->75|106->75|107->76|107->76|107->76|107->76|108->77|108->77|109->78|109->78|109->78|109->78|110->79|111->80|112->81|114->83|115->84|115->84|115->84|115->84|115->84|115->84|116->85|116->85|116->85|117->86|117->86|117->86|117->86|117->86|117->86|123->92|124->93|137->106|142->111|143->112|143->112|144->113|145->114|145->114|146->115|149->118|149->118|150->119|151->120|151->120|152->121|152->121|152->121|154->123|154->123|156->125|156->125|157->126|160->129|160->129|161->130|169->138|169->138|170->139|171->140|171->140|171->140|171->140|172->141|177->146|177->146|178->147|178->147|179->148|179->148|181->150
                  -- GENERATED --
              */
          