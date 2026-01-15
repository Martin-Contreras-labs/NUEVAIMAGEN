
package viewsMnuOdoAppWeb.html

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

object reporteMovOdoAutorizadorDetalle extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], bodega: tables.BodegaEmpresa, fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),format.raw/*6.2*/("""<!-- menues(mapDiccionario, mapPermiso, userMnu, " ") -->
	
	"""),_display_(/*8.3*/modalEquipoDescripcion()),format.raw/*8.27*/("""
	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
	<!-- barraTitulo(mapDiccionario, "DETALLE ODO MOVIMIENTO",mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase) -->

				<div class="noprint">
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
					        """),_display_(/*48.15*/for((nivel1,index1) <- lista.zipWithIndex) yield /*48.57*/ {_display_(Seq[Any](format.raw/*48.59*/("""
								"""),_display_(if(index1 < 2)/*49.24*/{_display_(Seq[Any](format.raw/*49.25*/("""
									"""),format.raw/*50.10*/("""<TR>
										"""),_display_(/*51.12*/for(nivel2 <- nivel1) yield /*51.33*/ {_display_(Seq[Any](format.raw/*51.35*/("""
											"""),format.raw/*52.12*/("""<td style="textAlign: center">"""),_display_(/*52.43*/nivel2),format.raw/*52.49*/("""</td>
										""")))}),format.raw/*53.12*/("""
									"""),format.raw/*54.10*/("""</TR>
								""")))} else {null} ),format.raw/*55.10*/("""
							""")))}),format.raw/*56.9*/("""
						"""),format.raw/*57.7*/("""</thead>
						<tbody>
							"""),_display_(/*59.9*/for((nivel1,index1) <- lista.zipWithIndex) yield /*59.51*/ {_display_(Seq[Any](format.raw/*59.53*/("""
								"""),_display_(if(index1 > 1)/*60.24*/{_display_(Seq[Any](format.raw/*60.25*/("""
									"""),format.raw/*61.10*/("""<TR>
										"""),_display_(/*62.12*/for((nivel2,index2) <- nivel1.zipWithIndex) yield /*62.55*/ {_display_(Seq[Any](format.raw/*62.57*/("""
											"""),_display_(if(index2 == 1 || index2 ==2)/*63.42*/{_display_(Seq[Any](format.raw/*63.43*/("""
												"""),format.raw/*64.13*/("""<td><a href="#" onclick="buscaEquipo('"""),_display_(/*64.52*/nivel1/*64.58*/.get(1)),format.raw/*64.65*/("""');">"""),_display_(/*64.71*/nivel2),format.raw/*64.77*/("""</a></td>
											""")))}else/*65.17*/{_display_(Seq[Any](format.raw/*65.18*/("""
												"""),format.raw/*66.13*/("""<td>"""),_display_(/*66.18*/nivel2),format.raw/*66.24*/("""</td>
											""")))}),format.raw/*67.13*/("""
										""")))}),format.raw/*68.12*/("""
									"""),format.raw/*69.10*/("""</TR>
								""")))} else {null} ),format.raw/*70.10*/("""
							 """)))}),format.raw/*71.10*/("""
						"""),format.raw/*72.7*/("""</tbody>
					
					</table>
				</div>
			</div>

	
	<form id="excel" class="formulario" method="post" action="/reporteMovOdoCantDetalleExcel/">
		<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*80.56*/bodega/*80.62*/.getId()),format.raw/*80.70*/("""">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*81.50*/fechaDesde),format.raw/*81.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*82.50*/fechaHasta),format.raw/*82.60*/("""">
	</form>


""")))}),format.raw/*86.2*/("""


"""),format.raw/*89.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*91.31*/("""{"""),format.raw/*91.32*/("""
		
		"""),format.raw/*93.3*/("""var tabla = document.getElementById("tablaPrincipal");
		
		for (var i = 2; i < tabla.rows.length; i++) """),format.raw/*95.47*/("""{"""),format.raw/*95.48*/("""
			"""),format.raw/*96.4*/("""tabla.rows[i].cells[0].style.textAlign="left";
			tabla.rows[i].cells[1].style.textAlign="center";
			tabla.rows[i].cells[2].style.textAlign="left";
			tabla.rows[i].cells[3].style.textAlign="center";
			tabla.rows[i].cells[0].style.minWidth="150px";
			tabla.rows[i].cells[2].style.minWidth="300px";
			for(var k=4;k<tabla.rows[0].cells.length;k++)"""),format.raw/*102.49*/("""{"""),format.raw/*102.50*/("""
				"""),format.raw/*103.5*/("""tabla.rows[i].cells[k].style.textAlign="center";
			"""),format.raw/*104.4*/("""}"""),format.raw/*104.5*/("""
		"""),format.raw/*105.3*/("""}"""),format.raw/*105.4*/("""
		
		"""),format.raw/*107.3*/("""for (var i = 2; i < tabla.rows.length; i++) """),format.raw/*107.47*/("""{"""),format.raw/*107.48*/(""" 
			"""),format.raw/*108.4*/("""tabla.rows[i].cells[3].style.backgroundColor="#eeeeee";
			tabla.rows[i].cells[tabla.rows[0].cells.length-1].style.backgroundColor="#eeeeee";
			tabla.rows[i].cells[tabla.rows[0].cells.length-1].style.textAlign="right";
			tabla.rows[i].cells[tabla.rows[0].cells.length-1].style.minWidth="80px";
		"""),format.raw/*112.3*/("""}"""),format.raw/*112.4*/("""
		
		"""),format.raw/*114.3*/("""var newRow = tabla.insertRow(tabla.rows.length);
		var cellsOfRow = tabla.rows[0].getElementsByTagName('td');
		
		for (var i = 0; i < cellsOfRow.length; i++) """),format.raw/*117.47*/("""{"""),format.raw/*117.48*/(""" 
			"""),format.raw/*118.4*/("""newRow.insertCell(i);
			newRow.cells[i].style.backgroundColor="#eeeeee";
			if(i==0)"""),format.raw/*120.12*/("""{"""),format.raw/*120.13*/("""
				"""),format.raw/*121.5*/("""newRow.cells[i].style.textAlign="left";
				newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ</div>TOTALES";
			"""),format.raw/*123.4*/("""}"""),format.raw/*123.5*/("""
			"""),format.raw/*124.4*/("""if(i==2)"""),format.raw/*124.12*/("""{"""),format.raw/*124.13*/("""
				"""),format.raw/*125.5*/("""newRow.cells[i].innerHTML="<div style='display:none'>ZZZZZZZZZZZZZZZZZZZZZZZ</div>";
			"""),format.raw/*126.4*/("""}"""),format.raw/*126.5*/("""
		"""),format.raw/*127.3*/("""}"""),format.raw/*127.4*/("""
		
		"""),format.raw/*129.3*/("""var numero2 = new DecimalFormat("#,##0.00");
		var numero0 = new DecimalFormat("#,##0");
		
		for (var i = 4; i < cellsOfRow.length; i++) """),format.raw/*132.47*/("""{"""),format.raw/*132.48*/("""
			"""),format.raw/*133.4*/("""var val = 0;
			for (var j = 2; j < tabla.rows.length-1; j++) """),format.raw/*134.50*/("""{"""),format.raw/*134.51*/("""
				"""),format.raw/*135.5*/("""var cellsOfRow = tabla.rows[j].getElementsByTagName('td');
				var aux = cellsOfRow[i].innerHTML;
				if(aux.trim()==""||aux==null) aux = 0;
				var val = parseFloat(val) + parseFloat(numero2.formatBack(aux));
			"""),format.raw/*139.4*/("""}"""),format.raw/*139.5*/("""
			"""),format.raw/*140.4*/("""var cellsOfRow = tabla.rows[tabla.rows.length-1].getElementsByTagName('td');
			
			newRow.cells[i].style.textAlign="center";
			cellsOfRow[i].innerHTML = numero2.format(val);
			if(i==cellsOfRow.length-1)"""),format.raw/*144.30*/("""{"""),format.raw/*144.31*/("""
				"""),format.raw/*145.5*/("""newRow.cells[i].style.textAlign="right";
			"""),format.raw/*146.4*/("""}"""),format.raw/*146.5*/("""
			
		"""),format.raw/*148.3*/("""}"""),format.raw/*148.4*/("""
		
		"""),format.raw/*150.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*150.34*/("""{"""),format.raw/*150.35*/("""
		    	"""),format.raw/*151.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 2, "asc" ]],
				"scrollY": 550,
				"scrollX": true,
		    	"language": """),format.raw/*158.20*/("""{"""),format.raw/*158.21*/("""
		        	"""),format.raw/*159.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*160.11*/("""}"""),format.raw/*160.12*/("""
		  """),format.raw/*161.5*/("""}"""),format.raw/*161.6*/(""" """),format.raw/*161.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*164.2*/("""}"""),format.raw/*164.3*/(""");
	
	
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],bodega:tables.BodegaEmpresa,fechaDesde:String,fechaHasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,bodega,fechaDesde,fechaHasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,bodega,fechaDesde,fechaHasta) => apply(mapDiccionario,mapPermiso,userMnu,lista,bodega,fechaDesde,fechaHasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdoAppWeb/reporteMovOdoAutorizadorDetalle.scala.html
                  HASH: c6a1ca5918446b6a7eb509916dc08baca290bdcc
                  MATRIX: 1086->1|1373->195|1405->202|1421->210|1460->212|1489->215|1576->277|1620->301|1651->305|3000->1627|3058->1669|3098->1671|3149->1695|3188->1696|3226->1706|3269->1722|3306->1743|3346->1745|3386->1757|3444->1788|3471->1794|3519->1811|3557->1821|3616->1836|3655->1845|3689->1852|3746->1883|3804->1925|3844->1927|3895->1951|3934->1952|3972->1962|4015->1978|4074->2021|4114->2023|4183->2065|4222->2066|4263->2079|4329->2118|4344->2124|4372->2131|4405->2137|4432->2143|4477->2169|4516->2170|4557->2183|4589->2188|4616->2194|4665->2212|4708->2224|4746->2234|4805->2249|4846->2259|4880->2266|5108->2467|5123->2473|5152->2481|5231->2533|5262->2543|5341->2595|5372->2605|5417->2620|5447->2623|5538->2686|5567->2687|5600->2693|5732->2797|5761->2798|5792->2802|6170->3151|6200->3152|6233->3157|6313->3209|6342->3210|6373->3213|6402->3214|6436->3220|6509->3264|6539->3265|6572->3270|6898->3568|6927->3569|6961->3575|7149->3734|7179->3735|7212->3740|7326->3825|7356->3826|7389->3831|7556->3970|7585->3971|7617->3975|7654->3983|7684->3984|7717->3989|7833->4077|7862->4078|7893->4081|7922->4082|7956->4088|8123->4226|8153->4227|8185->4231|8276->4293|8306->4294|8339->4299|8580->4512|8609->4513|8641->4517|8875->4722|8905->4723|8938->4728|9010->4772|9039->4773|9074->4780|9103->4781|9137->4787|9197->4818|9227->4819|9263->4827|9472->5007|9502->5008|9543->5020|9650->5098|9680->5099|9713->5104|9742->5105|9771->5106|9872->5179|9901->5180
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|39->8|39->8|41->10|79->48|79->48|79->48|80->49|80->49|81->50|82->51|82->51|82->51|83->52|83->52|83->52|84->53|85->54|86->55|87->56|88->57|90->59|90->59|90->59|91->60|91->60|92->61|93->62|93->62|93->62|94->63|94->63|95->64|95->64|95->64|95->64|95->64|95->64|96->65|96->65|97->66|97->66|97->66|98->67|99->68|100->69|101->70|102->71|103->72|111->80|111->80|111->80|112->81|112->81|113->82|113->82|117->86|120->89|122->91|122->91|124->93|126->95|126->95|127->96|133->102|133->102|134->103|135->104|135->104|136->105|136->105|138->107|138->107|138->107|139->108|143->112|143->112|145->114|148->117|148->117|149->118|151->120|151->120|152->121|154->123|154->123|155->124|155->124|155->124|156->125|157->126|157->126|158->127|158->127|160->129|163->132|163->132|164->133|165->134|165->134|166->135|170->139|170->139|171->140|175->144|175->144|176->145|177->146|177->146|179->148|179->148|181->150|181->150|181->150|182->151|189->158|189->158|190->159|191->160|191->160|192->161|192->161|192->161|195->164|195->164
                  -- GENERATED --
              */
          