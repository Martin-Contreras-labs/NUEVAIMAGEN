
package viewsMnuReportes.html

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

object reporteMovimientosDetalleIE extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], bodega: tables.BodegaEmpresa, esVenta: String, concepto: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalEquipoDescripcion()),format.raw/*8.27*/("""
	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "DETALLE MOVIMIENTO POR "+mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase,concepto)),format.raw/*11.141*/("""

				"""),format.raw/*13.5*/("""<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td width="25%">
								<div class="input-group">
							  		<div class="input-group-prepend">
							    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
							  		</div>
							  		<input type="text" class="form-control left"
										id="searchTermtablaPrincipal"
										onkeyup="doSearch5('tablaPrincipal');">
								</div>
							</td>
							<td>
								<div align="center">
									<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
										Exportar a Excel
									</button>
									<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
										Imprimir
									</button>
									
									<button type="button"  class="btn btn-sm btn-success" 
										onclick="history.go(-1);return false;">
										Volver
									</button>
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							"""),_display_(/*48.9*/for((nivel1,index1) <- lista.zipWithIndex) yield /*48.51*/ {_display_(Seq[Any](format.raw/*48.53*/("""
								"""),_display_(if(index1 < 6)/*49.24*/{_display_(Seq[Any](format.raw/*49.25*/("""
									"""),format.raw/*50.10*/("""<TR>
										"""),_display_(/*51.12*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*51.55*/ {_display_(Seq[Any](format.raw/*51.57*/("""
											"""),_display_(if(index2 == 0)/*52.28*/{_display_(Seq[Any](format.raw/*52.29*/("""
												"""),format.raw/*53.13*/("""<td style="min-width:150px">"""),_display_(/*53.42*/nivel2),format.raw/*53.48*/("""</td>
											""")))} else {null} ),format.raw/*54.13*/("""
											"""),_display_(if(index2 == 2)/*55.28*/{_display_(Seq[Any](format.raw/*55.29*/("""
												"""),format.raw/*56.13*/("""<td>"""),_display_(/*56.18*/nivel2),format.raw/*56.24*/("""</td>
											""")))} else {null} ),format.raw/*57.13*/("""
											"""),_display_(if(index2 == 3)/*58.28*/{_display_(Seq[Any](format.raw/*58.29*/("""
												"""),format.raw/*59.13*/("""<td style="min-width:300px">"""),_display_(/*59.42*/nivel2),format.raw/*59.48*/("""</td>
											""")))} else {null} ),format.raw/*60.13*/("""

											"""),_display_(if(index2 != 0 && index2 != 2 && index2 != 3)/*62.58*/{_display_(Seq[Any](format.raw/*62.59*/("""
												"""),format.raw/*63.13*/("""<td style="min-width:50px">"""),_display_(/*63.41*/nivel2),format.raw/*63.47*/("""</td>
											""")))} else {null} ),format.raw/*64.13*/("""
										""")))}),format.raw/*65.12*/("""
									"""),format.raw/*66.10*/("""</TR>
								""")))} else {null} ),format.raw/*67.10*/("""
							""")))}),format.raw/*68.9*/("""
						"""),format.raw/*69.7*/("""</thead>
						<tbody>
							"""),_display_(/*71.9*/for((nivel1,index1) <- lista.zipWithIndex) yield /*71.51*/ {_display_(Seq[Any](format.raw/*71.53*/("""
								"""),_display_(if(index1 > 5)/*72.24*/{_display_(Seq[Any](format.raw/*72.25*/("""
									"""),format.raw/*73.10*/("""<TR>
									"""),_display_(/*74.11*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*74.54*/ {_display_(Seq[Any](format.raw/*74.56*/("""
										"""),_display_(if(index2 == 0)/*75.27*/{_display_(Seq[Any](format.raw/*75.28*/("""
											"""),format.raw/*76.12*/("""<td style="min-width:150px">"""),_display_(/*76.41*/nivel2),format.raw/*76.47*/("""</td>
										""")))} else {null} ),format.raw/*77.12*/("""
										"""),_display_(if(index2 == 2)/*78.27*/{_display_(Seq[Any](format.raw/*78.28*/("""
											"""),format.raw/*79.12*/("""<td><a href="#" onclick="buscaEquipo('"""),_display_(/*79.51*/nivel1/*79.57*/.get(2)),format.raw/*79.64*/("""');">"""),_display_(/*79.70*/nivel2),format.raw/*79.76*/("""</a></td>
										""")))} else {null} ),format.raw/*80.12*/("""
										"""),_display_(if(index2 == 3)/*81.27*/{_display_(Seq[Any](format.raw/*81.28*/("""
											"""),format.raw/*82.12*/("""<td style="min-width:300px"><a href="#" onclick="buscaEquipo('"""),_display_(/*82.75*/nivel1/*82.81*/.get(2)),format.raw/*82.88*/("""');">"""),_display_(/*82.94*/nivel2),format.raw/*82.100*/("""</a></td>
										""")))} else {null} ),format.raw/*83.12*/("""
										"""),_display_(if(index2 != 0 && index2 != 2 && index2 != 3)/*84.57*/{_display_(Seq[Any](format.raw/*84.58*/("""
											"""),format.raw/*85.12*/("""<td style="min-width:50px; text-align:center">"""),_display_(/*85.59*/nivel2),format.raw/*85.65*/("""</td>
										""")))} else {null} ),format.raw/*86.12*/("""
									""")))}),format.raw/*87.11*/("""
								"""),format.raw/*88.9*/("""</TR>
								""")))} else {null} ),format.raw/*89.10*/("""
							 """)))}),format.raw/*90.10*/("""
							 """),format.raw/*91.9*/("""<TR>
								"""),_display_(/*92.10*/for((nivel2, index2) <- lista.get(0).zipWithIndex) yield /*92.60*/ {_display_(Seq[Any](format.raw/*92.62*/("""
									"""),_display_(if(index2 == 3)/*93.26*/{_display_(Seq[Any](format.raw/*93.27*/("""
										"""),format.raw/*94.11*/("""<td style="background-color: #eeeeee"><div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ</div></td>
									""")))}else/*95.15*/{_display_(Seq[Any](format.raw/*95.16*/("""
										"""),format.raw/*96.11*/("""<td style="background-color: #eeeeee"></td>
									""")))}),format.raw/*97.11*/("""

								""")))}),format.raw/*99.10*/("""
							"""),format.raw/*100.8*/("""</TR>
						</tbody>
					</table>
				</div>
	</div>

	
	<form id="excel" class="formulario" method="post" action="/reporteMovimientosIEExcel/">
		<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*108.56*/bodega/*108.62*/.getId()),format.raw/*108.70*/("""">
		<input type="hidden" name="esVenta" value=""""),_display_(/*109.47*/esVenta),format.raw/*109.54*/("""">
	</form>


""")))}),format.raw/*113.2*/("""


"""),format.raw/*116.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*118.31*/("""{"""),format.raw/*118.32*/("""
		
		
		
		"""),format.raw/*122.3*/("""var tabla = document.getElementById("tablaPrincipal");
		var l=tabla.rows.length-1;
		tabla.rows[l].cells[0].textContent="TOTAL ELEMENTOS";
		for (var i = 11; i < tabla.rows[l].cells.length; i++) """),format.raw/*125.57*/("""{"""),format.raw/*125.58*/("""
			"""),format.raw/*126.4*/("""var total=0;
			for (var k = 5; k<l;k++) """),format.raw/*127.29*/("""{"""),format.raw/*127.30*/("""
				"""),format.raw/*128.5*/("""var aux=parseFloat(tabla.rows[k].cells[i].textContent);
				if(isNaN(aux)) aux=0;
				total= parseFloat(aux)+parseFloat(total);
				if(isNaN(tabla.rows[5].cells[i].textContent))"""),format.raw/*131.50*/("""{"""),format.raw/*131.51*/("""
					"""),format.raw/*132.6*/("""tabla.rows[k].cells[i].style.backgroundColor="#eeeeee";
				"""),format.raw/*133.5*/("""}"""),format.raw/*133.6*/("""
				"""),format.raw/*134.5*/("""if(!isNaN(parseFloat(tabla.rows[k].cells[i].textContent)))"""),format.raw/*134.63*/("""{"""),format.raw/*134.64*/("""
					"""),format.raw/*135.6*/("""tabla.rows[k].cells[i].title='"""),_display_(/*135.37*/mapDiccionario/*135.51*/.get("Guia")),format.raw/*135.63*/(""": '+tabla.rows[0].cells[i].textContent+
						"\n Fecha:"+tabla.rows[1].cells[i].textContent+
						"\n Codigo:"+tabla.rows[k].cells[1].textContent+
						"\n Equipo:"+tabla.rows[k].cells[2].textContent;
				"""),format.raw/*139.5*/("""}"""),format.raw/*139.6*/("""
			"""),format.raw/*140.4*/("""}"""),format.raw/*140.5*/("""
			
			"""),format.raw/*142.4*/("""tabla.rows[l].cells[i].textContent=total;
			
			tabla.rows[l].cells[i].style.textAlign="center";
			
			tabla.rows[l+0].cells[i].title='"""),_display_(/*146.37*/mapDiccionario/*146.51*/.get("Guia")),format.raw/*146.63*/(""": '+ tabla.rows[0].cells[i].textContent+
						"\n Fecha:"+tabla.rows[1].cells[i].textContent;
		"""),format.raw/*148.3*/("""}"""),format.raw/*148.4*/("""
		
		
		"""),format.raw/*151.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*151.34*/("""{"""),format.raw/*151.35*/("""
		    	"""),format.raw/*152.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 3, "asc" ]],
				"scrollY": 550,
				"scrollX": true,
		    	"language": """),format.raw/*159.20*/("""{"""),format.raw/*159.21*/("""
		        	"""),format.raw/*160.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*161.11*/("""}"""),format.raw/*161.12*/("""
		  """),format.raw/*162.5*/("""}"""),format.raw/*162.6*/(""" """),format.raw/*162.7*/(""");


			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*166.2*/("""}"""),format.raw/*166.3*/(""");
	
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],bodega:tables.BodegaEmpresa,esVenta:String,concepto:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,bodega,esVenta,concepto)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,bodega,esVenta,concepto) => apply(mapDiccionario,mapPermiso,userMnu,lista,bodega,esVenta,concepto)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteMovimientosDetalleIE.scala.html
                  HASH: 1d4dba30ad707599778d60b19a5a5db315b72e29
                  MATRIX: 1081->1|1363->190|1395->197|1411->205|1450->207|1479->211|1547->259|1577->264|1621->288|1652->292|1729->343|1888->480|1921->486|3206->1745|3264->1787|3304->1789|3355->1813|3394->1814|3432->1824|3475->1840|3534->1883|3574->1885|3629->1913|3668->1914|3709->1927|3765->1956|3792->1962|3854->1980|3909->2008|3948->2009|3989->2022|4021->2027|4048->2033|4110->2051|4165->2079|4204->2080|4245->2093|4301->2122|4328->2128|4390->2146|4476->2205|4515->2206|4556->2219|4611->2247|4638->2253|4700->2271|4743->2283|4781->2293|4840->2308|4879->2317|4913->2324|4970->2355|5028->2397|5068->2399|5119->2423|5158->2424|5196->2434|5238->2449|5297->2492|5337->2494|5391->2521|5430->2522|5470->2534|5526->2563|5553->2569|5614->2586|5668->2613|5707->2614|5747->2626|5813->2665|5828->2671|5856->2678|5889->2684|5916->2690|5981->2711|6035->2738|6074->2739|6114->2751|6204->2814|6219->2820|6247->2827|6280->2833|6308->2839|6373->2860|6457->2917|6496->2918|6536->2930|6610->2977|6637->2983|6698->3000|6740->3011|6776->3020|6835->3035|6876->3045|6912->3054|6953->3068|7019->3118|7059->3120|7112->3146|7151->3147|7190->3158|7322->3271|7361->3272|7400->3283|7485->3337|7527->3348|7563->3356|7792->3557|7808->3563|7838->3571|7915->3620|7944->3627|7990->3642|8021->3645|8113->3708|8143->3709|8183->3721|8408->3917|8438->3918|8470->3922|8540->3963|8570->3964|8603->3969|8809->4146|8839->4147|8873->4153|8961->4213|8990->4214|9023->4219|9110->4277|9140->4278|9174->4284|9233->4315|9257->4329|9291->4341|9527->4549|9556->4550|9588->4554|9617->4555|9653->4563|9819->4701|9843->4715|9877->4727|10002->4824|10031->4825|10068->4834|10128->4865|10158->4866|10194->4874|10403->5054|10433->5055|10474->5067|10581->5145|10611->5146|10644->5151|10673->5152|10702->5153|10804->5227|10833->5228
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|41->10|42->11|42->11|44->13|79->48|79->48|79->48|80->49|80->49|81->50|82->51|82->51|82->51|83->52|83->52|84->53|84->53|84->53|85->54|86->55|86->55|87->56|87->56|87->56|88->57|89->58|89->58|90->59|90->59|90->59|91->60|93->62|93->62|94->63|94->63|94->63|95->64|96->65|97->66|98->67|99->68|100->69|102->71|102->71|102->71|103->72|103->72|104->73|105->74|105->74|105->74|106->75|106->75|107->76|107->76|107->76|108->77|109->78|109->78|110->79|110->79|110->79|110->79|110->79|110->79|111->80|112->81|112->81|113->82|113->82|113->82|113->82|113->82|113->82|114->83|115->84|115->84|116->85|116->85|116->85|117->86|118->87|119->88|120->89|121->90|122->91|123->92|123->92|123->92|124->93|124->93|125->94|126->95|126->95|127->96|128->97|130->99|131->100|139->108|139->108|139->108|140->109|140->109|144->113|147->116|149->118|149->118|153->122|156->125|156->125|157->126|158->127|158->127|159->128|162->131|162->131|163->132|164->133|164->133|165->134|165->134|165->134|166->135|166->135|166->135|166->135|170->139|170->139|171->140|171->140|173->142|177->146|177->146|177->146|179->148|179->148|182->151|182->151|182->151|183->152|190->159|190->159|191->160|192->161|192->161|193->162|193->162|193->162|197->166|197->166
                  -- GENERATED --
              */
          