
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

object reporteExcedentesDetalle extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[List[String]], bodega: tables.BodegaEmpresa):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalEquipoDescripcion()),format.raw/*8.27*/("""
	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "DETALLE DE EXCEDENTES", mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase)),format.raw/*11.131*/("""

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
										onkeyup="doSearch4('tablaPrincipal');">
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
				        """),_display_(/*47.14*/for((nivel1,index1) <- lista.zipWithIndex) yield /*47.56*/ {_display_(Seq[Any](format.raw/*47.58*/("""
							"""),format.raw/*48.8*/("""<TR>
								"""),_display_(/*49.10*/for((nivel2,index) <- nivel1.zipWithIndex) yield /*49.52*/ {_display_(Seq[Any](format.raw/*49.54*/("""
									"""),_display_(if(index==1)/*50.23*/{_display_(Seq[Any](format.raw/*50.24*/("""
										"""),format.raw/*51.11*/("""<td style="text-align:center">"""),_display_(/*51.42*/nivel2),format.raw/*51.48*/("""</td>
									""")))}else/*52.15*/{_display_(Seq[Any](format.raw/*52.16*/("""
									"""),_display_(if(index==0)/*53.23*/{_display_(Seq[Any](format.raw/*53.24*/("""
										"""),format.raw/*54.11*/("""<td style="min-width:150px">"""),_display_(/*54.40*/nivel2),format.raw/*54.46*/("""</td>
									""")))}else/*55.15*/{_display_(Seq[Any](format.raw/*55.16*/("""
										
									"""),_display_(if(index==2)/*57.23*/{_display_(Seq[Any](format.raw/*57.24*/("""
										
										"""),_display_(if(index1 > 2)/*59.26*/{_display_(Seq[Any](format.raw/*59.27*/("""
											"""),format.raw/*60.12*/("""<td style="min-width:80px"><a href="#" onclick="buscaEquipo('"""),_display_(/*60.74*/nivel1/*60.80*/.get(2)),format.raw/*60.87*/("""');">"""),_display_(/*60.93*/nivel2),format.raw/*60.99*/("""</a></td>
										""")))}else/*61.16*/{_display_(Seq[Any](format.raw/*61.17*/("""
											"""),format.raw/*62.12*/("""<td style="min-width:80px">"""),_display_(/*62.40*/nivel2),format.raw/*62.46*/("""</td>
										""")))}),format.raw/*63.12*/("""
										
									""")))}else/*65.15*/{_display_(Seq[Any](format.raw/*65.16*/("""
									"""),_display_(if(index==3)/*66.23*/{_display_(Seq[Any](format.raw/*66.24*/("""
										
										"""),_display_(if(index1 > 2)/*68.26*/{_display_(Seq[Any](format.raw/*68.27*/("""
											"""),format.raw/*69.12*/("""<td style="min-width:300px"><a href="#" onclick="buscaEquipo('"""),_display_(/*69.75*/nivel1/*69.81*/.get(2)),format.raw/*69.88*/("""');">"""),_display_(/*69.94*/nivel2),format.raw/*69.100*/("""</a></td>
										""")))}else/*70.16*/{_display_(Seq[Any](format.raw/*70.17*/("""
											"""),format.raw/*71.12*/("""<td style="min-width:300px">"""),_display_(/*71.41*/nivel2),format.raw/*71.47*/("""</td>
										""")))}),format.raw/*72.12*/("""
										
									""")))}else/*74.15*/{_display_(Seq[Any](format.raw/*74.16*/("""
									
									"""),_display_(if(index==4||index==5)/*76.33*/{_display_(Seq[Any](format.raw/*76.34*/("""
										"""),format.raw/*77.11*/("""<td style="text-align:right">"""),_display_(/*77.41*/nivel2),format.raw/*77.47*/("""</td>
									""")))}else/*78.15*/{_display_(Seq[Any](format.raw/*78.16*/("""
									"""),_display_(if(index==6)/*79.23*/{_display_(Seq[Any](format.raw/*79.24*/("""
										"""),format.raw/*80.11*/("""<td style="text-align:center">"""),_display_(/*80.42*/nivel2),format.raw/*80.48*/("""</td>
									""")))}else/*81.15*/{_display_(Seq[Any](format.raw/*81.16*/("""
									"""),_display_(if(index>6)/*82.22*/{_display_(Seq[Any](format.raw/*82.23*/("""
										"""),format.raw/*83.11*/("""<td style="min-width:60px;text-align:right">"""),_display_(/*83.56*/nivel2),format.raw/*83.62*/("""</td>
									""")))}else/*84.15*/{_display_(Seq[Any](format.raw/*84.16*/("""
										"""),format.raw/*85.11*/("""<td style="min-width:50px">"""),_display_(/*85.39*/nivel2),format.raw/*85.45*/("""</td>
									""")))}),format.raw/*86.11*/("""
									""")))}),format.raw/*87.11*/("""
									""")))}),format.raw/*88.11*/("""
									""")))}),format.raw/*89.11*/("""
									""")))}),format.raw/*90.11*/("""
									""")))}),format.raw/*91.11*/("""
									""")))}),format.raw/*92.11*/("""
								""")))}),format.raw/*93.10*/("""
							"""),format.raw/*94.8*/("""</TR>
						 """)))}),format.raw/*95.9*/("""
						 """),format.raw/*96.8*/("""<TR>
							"""),_display_(/*97.9*/for(nivel2 <- lista.get(0).tail.tail) yield /*97.46*/ {_display_(Seq[Any](format.raw/*97.48*/("""
								"""),format.raw/*98.9*/("""<td style="background-color:#F8E6E0"></td>
							""")))}),format.raw/*99.9*/("""
						"""),format.raw/*100.7*/("""</TR>
					</table>
				</div>
			</div>

	
	<form id="excel" class="formulario" method="post" action="/reporteExcedentesDetalleExcel/">
		<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*107.56*/bodega/*107.62*/.getId()),format.raw/*107.70*/("""">
	</form>


""")))}),format.raw/*111.2*/("""


"""),format.raw/*114.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*116.31*/("""{"""),format.raw/*116.32*/("""
		
		"""),format.raw/*118.3*/("""var tabla = document.getElementById("tablaPrincipal");
		for (var i = 0; i < tabla.rows[0].cells.length; i++) """),format.raw/*119.56*/("""{"""),format.raw/*119.57*/(""" 	
			"""),format.raw/*120.4*/("""tabla.rows[0].cells[i].style.backgroundColor="#F8E6E0" ;
			tabla.rows[1].cells[i].style.backgroundColor="#F8E6E0" ;
			tabla.rows[2].cells[i].style.backgroundColor="#F8E6E0" ;
			tabla.rows[3].cells[i].style.backgroundColor="#F8E6E0" ;
		"""),format.raw/*124.3*/("""}"""),format.raw/*124.4*/("""
		"""),format.raw/*125.3*/("""var l=tabla.rows.length-1;
		tabla.rows[l].cells[0].textContent="";
		tabla.rows[l].cells[0].colSpan="3";
		for (var i = 0; i < tabla.rows.length; i++) """),format.raw/*128.47*/("""{"""),format.raw/*128.48*/("""
			"""),format.raw/*129.4*/("""for (var j = 11; j < tabla.rows[i].cells.length; j++) """),format.raw/*129.58*/("""{"""),format.raw/*129.59*/("""
				"""),format.raw/*130.5*/("""tabla.rows[i].cells[j].style.textAlign="center";
			"""),format.raw/*131.4*/("""}"""),format.raw/*131.5*/("""
		"""),format.raw/*132.3*/("""}"""),format.raw/*132.4*/("""
		"""),format.raw/*133.3*/("""for (var i = 11; i < tabla.rows[l].cells.length+2; i++) """),format.raw/*133.59*/("""{"""),format.raw/*133.60*/("""
			"""),format.raw/*134.4*/("""var total=0;
			for (var k = 4; k<l;k++) """),format.raw/*135.29*/("""{"""),format.raw/*135.30*/("""
				"""),format.raw/*136.5*/("""var aux=parseFloat(tabla.rows[k].cells[i].textContent);
				if(isNaN(aux)) aux=0;
				total= parseFloat(aux)+parseFloat(total);
				if(isNaN(tabla.rows[3].cells[i].textContent))"""),format.raw/*139.50*/("""{"""),format.raw/*139.51*/("""
					"""),format.raw/*140.6*/("""tabla.rows[k].cells[i].style.backgroundColor="#F8E6E0";
				"""),format.raw/*141.5*/("""}"""),format.raw/*141.6*/("""
				"""),format.raw/*142.5*/("""if(!isNaN(parseFloat(tabla.rows[k].cells[i].textContent)))"""),format.raw/*142.63*/("""{"""),format.raw/*142.64*/("""
					"""),format.raw/*143.6*/("""tabla.rows[k].cells[i].title='"""),_display_(/*143.37*/mapDiccionario/*143.51*/.get("Guia")),format.raw/*143.63*/(""": '+tabla.rows[0].cells[i].textContent+
						"\n Fecha:"+tabla.rows[1].cells[i].textContent+
						"\n Codigo:"+tabla.rows[k].cells[1].textContent+
						"\n Equipo:"+tabla.rows[k].cells[2].textContent;
				"""),format.raw/*147.5*/("""}"""),format.raw/*147.6*/("""
			"""),format.raw/*148.4*/("""}"""),format.raw/*148.5*/("""
			
			"""),format.raw/*150.4*/("""tabla.rows[l].cells[i-2].textContent=total;
			
			tabla.rows[l].cells[i-2].style.textAlign="center";
			
			tabla.rows[l+0].cells[i-2].title='"""),_display_(/*154.39*/mapDiccionario/*154.53*/.get("Guia")),format.raw/*154.65*/(""": '+ tabla.rows[0].cells[i].textContent+
						"\n Fecha:"+tabla.rows[1].cells[i].textContent;
		"""),format.raw/*156.3*/("""}"""),format.raw/*156.4*/("""
		


			"""),format.raw/*160.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*161.2*/("""}"""),format.raw/*161.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]],bodega:tables.BodegaEmpresa): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,bodega)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,bodega) => apply(mapDiccionario,mapPermiso,userMnu,lista,bodega)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteExcedentesDetalle.scala.html
                  HASH: e1c7bde57b648f4d164ae4ea98e5bac07ac82085
                  MATRIX: 1064->1|1311->155|1343->162|1359->170|1398->172|1427->176|1495->224|1525->229|1569->253|1600->257|1677->308|1826->435|1859->441|3102->1657|3160->1699|3200->1701|3235->1709|3276->1723|3334->1765|3374->1767|3424->1790|3463->1791|3502->1802|3560->1833|3587->1839|3626->1859|3665->1860|3715->1883|3754->1884|3793->1895|3849->1924|3876->1930|3915->1950|3954->1951|4015->1985|4054->1986|4118->2023|4157->2024|4197->2036|4286->2098|4301->2104|4329->2111|4362->2117|4389->2123|4433->2148|4472->2149|4512->2161|4567->2189|4594->2195|4642->2212|4687->2238|4726->2239|4776->2262|4815->2263|4879->2300|4918->2301|4958->2313|5048->2376|5063->2382|5091->2389|5124->2395|5152->2401|5196->2426|5235->2427|5275->2439|5331->2468|5358->2474|5406->2491|5451->2517|5490->2518|5560->2561|5599->2562|5638->2573|5695->2603|5722->2609|5761->2629|5800->2630|5850->2653|5889->2654|5928->2665|5986->2696|6013->2702|6052->2722|6091->2723|6140->2745|6179->2746|6218->2757|6290->2802|6317->2808|6356->2828|6395->2829|6434->2840|6489->2868|6516->2874|6563->2890|6605->2901|6647->2912|6689->2923|6731->2934|6773->2945|6815->2956|6856->2966|6891->2974|6935->2988|6970->2996|7009->3009|7062->3046|7102->3048|7138->3057|7219->3108|7254->3115|7474->3307|7490->3313|7520->3321|7566->3336|7597->3339|7689->3402|7719->3403|7753->3409|7892->3519|7922->3520|7956->3526|8223->3765|8252->3766|8283->3769|8464->3921|8494->3922|8526->3926|8609->3980|8639->3981|8672->3986|8752->4038|8781->4039|8812->4042|8841->4043|8872->4046|8957->4102|8987->4103|9019->4107|9089->4148|9119->4149|9152->4154|9358->4331|9388->4332|9422->4338|9510->4398|9539->4399|9572->4404|9659->4462|9689->4463|9723->4469|9782->4500|9806->4514|9840->4526|10076->4734|10105->4735|10137->4739|10166->4740|10202->4748|10374->4892|10398->4906|10432->4918|10557->5015|10586->5016|10623->5025|10717->5091|10746->5092
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|41->10|42->11|42->11|44->13|78->47|78->47|78->47|79->48|80->49|80->49|80->49|81->50|81->50|82->51|82->51|82->51|83->52|83->52|84->53|84->53|85->54|85->54|85->54|86->55|86->55|88->57|88->57|90->59|90->59|91->60|91->60|91->60|91->60|91->60|91->60|92->61|92->61|93->62|93->62|93->62|94->63|96->65|96->65|97->66|97->66|99->68|99->68|100->69|100->69|100->69|100->69|100->69|100->69|101->70|101->70|102->71|102->71|102->71|103->72|105->74|105->74|107->76|107->76|108->77|108->77|108->77|109->78|109->78|110->79|110->79|111->80|111->80|111->80|112->81|112->81|113->82|113->82|114->83|114->83|114->83|115->84|115->84|116->85|116->85|116->85|117->86|118->87|119->88|120->89|121->90|122->91|123->92|124->93|125->94|126->95|127->96|128->97|128->97|128->97|129->98|130->99|131->100|138->107|138->107|138->107|142->111|145->114|147->116|147->116|149->118|150->119|150->119|151->120|155->124|155->124|156->125|159->128|159->128|160->129|160->129|160->129|161->130|162->131|162->131|163->132|163->132|164->133|164->133|164->133|165->134|166->135|166->135|167->136|170->139|170->139|171->140|172->141|172->141|173->142|173->142|173->142|174->143|174->143|174->143|174->143|178->147|178->147|179->148|179->148|181->150|185->154|185->154|185->154|187->156|187->156|191->160|192->161|192->161
                  -- GENERATED --
              */
          