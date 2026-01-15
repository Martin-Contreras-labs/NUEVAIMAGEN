
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

object reportInventarioBodega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], fechaCorte: String, tipo:String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalContactoBodega(mapDiccionario)),format.raw/*8.38*/("""
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO - "+tipo,"FECHA DE CORTE: " + utilities.Fechas.DDMMAA(fechaCorte))),format.raw/*13.154*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
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
										onkeyup="doSearch2('tablaPrincipal');">
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<div>
					ALTERNAR COLUMNAS: 
						<a class="toggle-vis" href="#" data-column="0"><kbd id="kdb0" style="background-color: #73C6B6">TIPO</kbd></a>
						<a class="toggle-vis" href="#" data-column="1"><kbd id="kdb1" style="background-color: #73C6B6">SUCURSAL</kbd></a> 
						<a class="toggle-vis" href="#" data-column="2"><kbd id="kdb2" style="background-color: #73C6B6">"""),_display_(/*37.104*/mapDiccionario/*37.118*/.get("BODEGA")),format.raw/*37.132*/("""/PROYECTO</kbd></a> 
						<a class="toggle-vis" href="#" data-column="3"><kbd id="kdb3" style="background-color: #73C6B6">NOMBRE CLIENTE</kbd></a> 
						<a class="toggle-vis" href="#" data-column="4"><kbd id="kdb4" style="background-color: #73C6B6">NOMBRE PROYECTO</kbd></a> 
						<a class="toggle-vis" href="#" data-column="5"><kbd id="kdb5" style="background-color: #73C6B6">"""),_display_(/*40.104*/mapDiccionario/*40.118*/.get("COMUNA")),format.raw/*40.132*/(""" """),format.raw/*40.133*/("""PROYECTO</kbd></a> 
				</div>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
					        <TR> 
						        <TH style= "text-align: center;vertical-align: top;">TIPO</TH>
								<TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
						        <TH style= "text-align: center;vertical-align: top;">"""),_display_(/*49.69*/mapDiccionario/*49.83*/.get("BODEGA")),format.raw/*49.97*/("""/PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<BR>CLIENTE</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE<br>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*52.63*/mapDiccionario/*52.77*/.get("COMUNA")),format.raw/*52.91*/("""<BR>PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;width:4%">SELECT</TH>
							</TR>
						</thead>
						<tbody>
						"""),_display_(/*57.8*/for(lista1 <- lista) yield /*57.28*/{_display_(Seq[Any](format.raw/*57.29*/("""
							"""),format.raw/*58.8*/("""<TR>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*59.61*/lista1/*59.67*/.get(4)),format.raw/*59.74*/("""</td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*60.61*/lista1/*60.67*/.get(10)),format.raw/*60.75*/("""</td>
								<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoBodega('"""),_display_(/*61.103*/lista1/*61.109*/.get(1)),format.raw/*61.116*/("""');">"""),_display_(/*61.122*/lista1/*61.128*/.get(5)),format.raw/*61.135*/("""</a></td>
								<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoCliente('"""),_display_(/*62.104*/lista1/*62.110*/.get(2)),format.raw/*62.117*/("""');">"""),_display_(/*62.123*/lista1/*62.129*/.get(7)),format.raw/*62.136*/("""</a></td>
								<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="modalContactoProyecto('"""),_display_(/*63.105*/lista1/*63.111*/.get(3)),format.raw/*63.118*/("""');">"""),_display_(/*63.124*/lista1/*63.130*/.get(8)),format.raw/*63.137*/("""</a></td>
								<td style="text-align:left;vertical-align:middle;">"""),_display_(/*64.61*/lista1/*64.67*/.get(9)),format.raw/*64.74*/("""</td>
								<td style="text-align:left;vertical-align:middle;">
									<form id="form_"""),_display_(/*66.26*/lista1/*66.32*/.get(1)),format.raw/*66.39*/("""" class="formulario" method="post" action="/reportInventarioSelectivoXBodega/">
										<input type="hidden" name="id_bodega" value=""""),_display_(/*67.57*/lista1/*67.63*/.get(1)),format.raw/*67.70*/("""">
										<input type="hidden" name="fechaCorte" value=""""),_display_(/*68.58*/fechaCorte),format.raw/*68.68*/("""">
										<input type="hidden" name="tipo" value=""""),_display_(/*69.52*/tipo),format.raw/*69.56*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*70.63*/lista1/*70.69*/.get(1)),format.raw/*70.76*/("""').submit()">
											<kbd style="background-color: #73C6B6">select</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*76.9*/("""
						"""),format.raw/*77.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	

""")))}),format.raw/*85.2*/("""


"""),format.raw/*88.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*90.31*/("""{"""),format.raw/*90.32*/("""

		 """),format.raw/*92.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*92.35*/("""{"""),format.raw/*92.36*/("""
		    	"""),format.raw/*93.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 0, "asc" ], [ 2, "asc" ]],
		    	"language": """),format.raw/*98.20*/("""{"""),format.raw/*98.21*/("""
		        	"""),format.raw/*99.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*100.11*/("""}"""),format.raw/*100.12*/("""
		  """),format.raw/*101.5*/("""}"""),format.raw/*101.6*/(""" """),format.raw/*101.7*/(""");
		  
		  $('a.toggle-vis').on('click', function (e) """),format.raw/*103.48*/("""{"""),format.raw/*103.49*/("""
		        """),format.raw/*104.11*/("""e.preventDefault();
		        // Get the column API object
		        let col = $(this).attr('data-column');
		        var column = $('#tablaPrincipal').DataTable().column(col);
		        // Toggle the visibility
		        column.visible(!column.visible());
		        if(column.visible())"""),format.raw/*110.31*/("""{"""),format.raw/*110.32*/("""
					"""),format.raw/*111.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#73C6B6";
				"""),format.raw/*112.5*/("""}"""),format.raw/*112.6*/("""else"""),format.raw/*112.10*/("""{"""),format.raw/*112.11*/("""
					"""),format.raw/*113.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#D6DBDF";
				"""),format.raw/*114.5*/("""}"""),format.raw/*114.6*/("""
		  """),format.raw/*115.5*/("""}"""),format.raw/*115.6*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*118.2*/("""}"""),format.raw/*118.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],fechaCorte:String,tipo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,fechaCorte,tipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,fechaCorte,tipo) => apply(mapDiccionario,mapPermiso,userMnu,lista,fechaCorte,tipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportInventarioBodega.scala.html
                  HASH: e301a807778e4a1b91dcae797d357f4259c0588e
                  MATRIX: 1055->1|1305->158|1337->165|1353->173|1392->175|1421->179|1489->227|1519->232|1574->267|1602->270|1644->292|1673->295|1717->318|1748->322|1825->373|1997->523|2027->526|3045->1516|3069->1530|3105->1544|3514->1925|3538->1939|3574->1953|3604->1954|4106->2429|4129->2443|4164->2457|4437->2703|4460->2717|4495->2731|4669->2879|4705->2899|4744->2900|4779->2908|4871->2973|4886->2979|4914->2986|5007->3052|5022->3058|5051->3066|5187->3174|5203->3180|5232->3187|5266->3193|5282->3199|5311->3206|5452->3319|5468->3325|5497->3332|5531->3338|5547->3344|5576->3351|5718->3465|5734->3471|5763->3478|5797->3484|5813->3490|5842->3497|5939->3567|5954->3573|5982->3580|6100->3671|6115->3677|6143->3684|6306->3820|6321->3826|6349->3833|6436->3893|6467->3903|6548->3957|6573->3961|6665->4026|6680->4032|6708->4039|6882->4183|6916->4190|7011->4255|7041->4258|7149->4338|7178->4339|7210->4344|7269->4375|7298->4376|7333->4384|7514->4537|7543->4538|7583->4550|7690->4628|7720->4629|7753->4634|7782->4635|7811->4636|7895->4691|7925->4692|7965->4703|8281->4990|8311->4991|8345->4997|8447->5071|8476->5072|8509->5076|8539->5077|8573->5083|8675->5157|8704->5158|8737->5163|8766->5164|8867->5237|8896->5238
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|68->37|68->37|68->37|71->40|71->40|71->40|71->40|80->49|80->49|80->49|83->52|83->52|83->52|88->57|88->57|88->57|89->58|90->59|90->59|90->59|91->60|91->60|91->60|92->61|92->61|92->61|92->61|92->61|92->61|93->62|93->62|93->62|93->62|93->62|93->62|94->63|94->63|94->63|94->63|94->63|94->63|95->64|95->64|95->64|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|100->69|100->69|101->70|101->70|101->70|107->76|108->77|116->85|119->88|121->90|121->90|123->92|123->92|123->92|124->93|129->98|129->98|130->99|131->100|131->100|132->101|132->101|132->101|134->103|134->103|135->104|141->110|141->110|142->111|143->112|143->112|143->112|143->112|144->113|145->114|145->114|146->115|146->115|149->118|149->118
                  -- GENERATED --
              */
          