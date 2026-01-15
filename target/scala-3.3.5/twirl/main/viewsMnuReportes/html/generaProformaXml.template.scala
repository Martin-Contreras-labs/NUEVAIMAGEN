
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

object generaProformaXml extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,xml.FormXmlFactura,Long,List[tables.TipoReferencia],Map[String,String],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
factura: xml.FormXmlFactura, id_proforma: Long, listTipoReferencias: List[tables.TipoReferencia], mapReferencia: Map[String,String],
desde: String, hasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""

"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

	"""),_display_(/*8.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.51*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "PROFORMA FACTURA XML","COMPLETAR O MODIFICAR REFERENCIAS Y COMENTARIOS")),format.raw/*10.105*/("""

		"""),format.raw/*12.3*/("""<form class="formulario" method="post" action="/sendXMLFacura/"""),_display_(/*12.66*/id_proforma),format.raw/*12.77*/(""","""),_display_(/*12.79*/desde),format.raw/*12.84*/(""","""),_display_(/*12.86*/hasta),format.raw/*12.91*/("""">
			
			<div class="container">
				<table id="receptor" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				 	<tr>
				 		<td colspan="4">DATOS RECEPTOR:</td>
				 	</tr>
				 	<tr>
				 		<td>"""),_display_(/*20.13*/mapDiccionario("RUT")),format.raw/*20.34*/(""":</td>
				 		<td>"""),_display_(/*21.13*/factura/*21.20*/.rutRecep),format.raw/*21.29*/("""</td>
				 		<td>Dirección:</td>
				 		<td>"""),_display_(/*23.13*/factura/*23.20*/.dirRecep),format.raw/*23.29*/("""</td>
				 	</tr>
				 	<tr>
				 		<td>Razón Social:</td>
				 		<td>"""),_display_(/*27.13*/factura/*27.20*/.rznSocRecep),format.raw/*27.32*/("""</td>
				 		<td>"""),_display_(/*28.13*/mapDiccionario/*28.27*/.get("Comuna")),format.raw/*28.41*/(""":</td>
				 		<td>"""),_display_(/*29.13*/factura/*29.20*/.cmnaRecep),format.raw/*29.30*/("""</td>
				 	</tr>
				 	<tr>
				 		<td>Giro:</td>
				 		<td>"""),_display_(/*33.13*/factura/*33.20*/.giroRecep),format.raw/*33.30*/("""</td>
				 		<td>Ciudad:</td>
				 		<td>"""),_display_(/*35.13*/factura/*35.20*/.ciudadRecep),format.raw/*35.32*/("""</td>
				 	</tr>
				 	<tr>
				 		<td>Contacto:</td>
				 		<td>"""),_display_(/*39.13*/factura/*39.20*/.contacto),format.raw/*39.29*/("""</td>
				 		<td>Correo:</td>
				 		<td>"""),_display_(/*41.13*/factura/*41.20*/.correoRecep),format.raw/*41.32*/("""</td>
				 	</tr>
				</table>
				
				<table id="detalle" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				 	<tr>
				 		<td colspan="7">DETALLE:</td>
				 	</tr>
				 	<tr>
				 		<td>NroLinDet</td>
				 		<td>TpoCodigo</td>
				 		<td>VlrCodigo</td>
				 		<td>NmbItem</td>
				 		<td>QtyItem</td>
				 		<td>UnmdItem</td>
				 		<td>PrcItem</td>
				 		<td>MontoItem</td>
				 	</tr>
					 	"""),_display_(/*59.9*/for(lista <- factura.nroLinDet) yield /*59.40*/{_display_(Seq[Any](format.raw/*59.41*/("""
						 	"""),format.raw/*60.9*/("""<tr>
						 		<td>"""),_display_(/*61.15*/lista),format.raw/*61.20*/("""</td>
						 		<td>"""),_display_(/*62.15*/factura/*62.22*/.tpoCodigo.get(Integer.parseInt(lista)-1)),format.raw/*62.63*/("""</td>
						 		<td>"""),_display_(/*63.15*/factura/*63.22*/.vlrCodigo.get(Integer.parseInt(lista)-1)),format.raw/*63.63*/("""</td>
						 		<td>"""),_display_(/*64.15*/factura/*64.22*/.nmbItem.get(Integer.parseInt(lista)-1)),format.raw/*64.61*/("""</td>
						 		<td>"""),_display_(/*65.15*/factura/*65.22*/.qtyItem.get(Integer.parseInt(lista)-1)),format.raw/*65.61*/("""</td>
						 		<td>"""),_display_(/*66.15*/factura/*66.22*/.unmdItem.get(Integer.parseInt(lista)-1)),format.raw/*66.62*/("""</td>
						 		<td>"""),_display_(/*67.15*/factura/*67.22*/.prcItem.get(Integer.parseInt(lista)-1)),format.raw/*67.61*/("""</td>
						 		<td>"""),_display_(/*68.15*/factura/*68.22*/.montoItem.get(Integer.parseInt(lista)-1)),format.raw/*68.63*/("""</td>
						 	</tr>
					 	""")))}),format.raw/*70.9*/("""
				 	"""),format.raw/*71.7*/("""<tr>
				 		<td colspan="5"></td>
				 		<td colspan="2">NETO</td>
				 		<td>"""),_display_(/*74.13*/factura/*74.20*/.mntNeto),format.raw/*74.28*/("""</td>
				 	</tr>
				 	<tr>
				 		<td colspan="5"></td>
				 		<td colspan="2">IVA</td>
				 		<td>"""),_display_(/*79.13*/factura/*79.20*/.iva),format.raw/*79.24*/("""</td>
				 	</tr>
				 	<tr>
				 		<td colspan="5"></td>
				 		<td colspan="2">TOTAL</td>
				 		<td>"""),_display_(/*84.13*/factura/*84.20*/.mntTotal),format.raw/*84.29*/("""</td>
				 	</tr>
				</table>
				
				<table id="referencia" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				 	<tr>
				 		<td colspan="4">REFERENCIAS:</td>
				 		<td colspan="1">
				 			<div align="right">
				 				<a  class="btn btn-warning btn-mini" onclick="agregarReferencia()">agregar referencia</a>
				 			</div>
				 		</td>
				 	</tr>
				 	<tr>
				 		<td>NroLinRef</td>
				 		<td>TpoDocRef</td>
				 		<td>FolioRef</td>
				 		<td>FchRef</td>
				 		<td>RazonRef</td>
				 	</tr>
				 	"""),_display_(if(factura.nroLinRef!=null)/*104.35*/{_display_(Seq[Any](format.raw/*104.36*/("""
					 	"""),_display_(/*105.9*/for(lista <- factura.nroLinRef) yield /*105.40*/{_display_(Seq[Any](format.raw/*105.41*/("""
						 	"""),format.raw/*106.9*/("""<tr>
						 		<td>"""),_display_(/*107.15*/lista),format.raw/*107.20*/("""
						 			"""),format.raw/*108.11*/("""<input type="hidden" name="nroLinRef[]" value=""""),_display_(/*108.59*/lista),format.raw/*108.64*/("""">
						 		</td>
						 		<td>
							 		<select name='tpoDocRef[]' onchange="verificaReferencia(value,this)">
								
										<option value=""""),_display_(/*113.27*/factura/*113.34*/.tpoDocRef.get(Integer.parseInt(lista)-1)),format.raw/*113.75*/("""">
											"""),_display_(if(mapReferencia.get(factura.tpoDocRef.get(Integer.parseInt(lista)-1)) != null )/*114.93*/{_display_(Seq[Any](format.raw/*114.94*/("""
												"""),_display_(/*115.14*/mapReferencia/*115.27*/.get(factura.tpoDocRef.get(Integer.parseInt(lista)-1))),format.raw/*115.81*/("""
											""")))} else {null} ),format.raw/*116.13*/("""
										"""),format.raw/*117.11*/("""</option>
										"""),_display_(/*118.12*/for(referencia <- listTipoReferencias) yield /*118.50*/{_display_(Seq[Any](format.raw/*118.51*/("""
											"""),format.raw/*119.12*/("""<option value=""""),_display_(/*119.28*/referencia/*119.38*/.codigo),format.raw/*119.45*/("""">"""),_display_(/*119.48*/referencia/*119.58*/.concepto),format.raw/*119.67*/("""</option>
										""")))}),format.raw/*120.12*/(""" 
										
										
									"""),format.raw/*123.10*/("""</select>
						 		</td>
						 		<td>
						 			<input type="text" name="folioRef[]" value=""""),_display_(/*126.56*/factura/*126.63*/.folioRef.get(Integer.parseInt(lista)-1)),format.raw/*126.103*/("""">
						 		</td>
						 		<td>
						 			<input type="date" name="fchRef[]" value=""""),_display_(/*129.54*/factura/*129.61*/.fchRef.get(Integer.parseInt(lista)-1)),format.raw/*129.99*/("""">
						 		</td>
						 		<td>
						 			<input type="text" name="razonRef[]" value=""""),_display_(/*132.56*/factura/*132.63*/.razonRef.get(Integer.parseInt(lista)-1)),format.raw/*132.103*/("""">
						 		</td>
						 	</tr>
					 	""")))}),format.raw/*135.9*/("""
				 	""")))} else {null} ),format.raw/*136.8*/("""
				 """),format.raw/*137.6*/("""</table>
				 
				 	<script>
					 	function verificaReferencia(value,select)"""),format.raw/*140.49*/("""{"""),format.raw/*140.50*/("""
					 		"""),format.raw/*141.9*/("""if(value==0)"""),format.raw/*141.21*/("""{"""),format.raw/*141.22*/("""
					 			"""),format.raw/*142.10*/("""select.parentNode.parentNode.remove();
					 			var tabla = document.getElementById('referencia');
					 			
					 			for (var i = 2; i < tabla.rows.length; i++)"""),format.raw/*145.53*/("""{"""),format.raw/*145.54*/("""
					 				"""),format.raw/*146.11*/("""var cellsOfRow = tabla.rows[i].getElementsByTagName('td');
					 				cellsOfRow[0].innerHTML=i-1;
					 			"""),format.raw/*148.10*/("""}"""),format.raw/*148.11*/("""
					 		
					 			
					 		"""),format.raw/*151.9*/("""}"""),format.raw/*151.10*/("""
					 	"""),format.raw/*152.8*/("""}"""),format.raw/*152.9*/("""
				 		"""),format.raw/*153.8*/("""function agregarReferencia()"""),format.raw/*153.36*/("""{"""),format.raw/*153.37*/("""
				 			"""),format.raw/*154.9*/("""var tabla = document.getElementById('referencia');
							var row = tabla.insertRow(tabla.rows.length);
							
							var cell=row.insertCell(0);
							var indice = tabla.rows.length-2;
							cell.innerHTML=indice+"<input type='hidden' name='nroLinRef[]' value='"+indice+"'>";
							
							cell=row.insertCell(1);
							var listado = "<select name='tpoDocRef[]'>";
							"""),_display_(/*163.9*/for(referencia <- listTipoReferencias) yield /*163.47*/{_display_(Seq[Any](format.raw/*163.48*/("""
								"""),format.raw/*164.9*/("""listado = listado + "<option value='"""),_display_(/*164.46*/referencia/*164.56*/.codigo),format.raw/*164.63*/("""'>"""),_display_(/*164.66*/referencia/*164.76*/.concepto),format.raw/*164.85*/("""</option>";
							""")))}),format.raw/*165.9*/("""
							"""),format.raw/*166.8*/("""listado = listado + "</select>";
							cell.innerHTML=listado;
							
							cell=row.insertCell(2);
							cell.innerHTML="<input type='text' name='folioRef[]' value=''>";
							
							cell=row.insertCell(3);
							cell.innerHTML="<input type='date' name='fchRef[]' value=''>";
							
							cell=row.insertCell(4);
							cell.innerHTML="<input type='text' name='razonRef[]' value=''>";
							
				 		"""),format.raw/*178.8*/("""}"""),format.raw/*178.9*/("""
				 	"""),format.raw/*179.7*/("""</script>
				
				<table id="comentarios" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				 	<tr>
				 		<td>COMENTARIOS:</td>
				 	</tr>
				 	<tr>
				 		<td>
				 		<textarea rows="2" style="width:100%" name="obs">"""),_display_(/*187.58*/factura/*187.65*/.obs),format.raw/*187.69*/("""</textarea></td>
				 	</tr>
				</table>
				
				<button type='submit'>ENVIAR</button>
				
				
			</div>
	</form>

""")))}),format.raw/*197.2*/("""
													
"""),format.raw/*199.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*201.31*/("""{"""),format.raw/*201.32*/("""
		"""),format.raw/*202.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*203.2*/("""}"""),format.raw/*203.3*/(""");
	
</script>
				

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,factura:xml.FormXmlFactura,id_proforma:Long,listTipoReferencias:List[tables.TipoReferencia],mapReferencia:Map[String,String],desde:String,hasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,factura,id_proforma,listTipoReferencias,mapReferencia,desde,hasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,xml.FormXmlFactura,Long,List[tables.TipoReferencia],Map[String,String],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,factura,id_proforma,listTipoReferencias,mapReferencia,desde,hasta) => apply(mapDiccionario,mapPermiso,userMnu,factura,id_proforma,listTipoReferencias,mapReferencia,desde,hasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/generaProformaXml.scala.html
                  HASH: bb97fdaf0f904e53264d5876a37206fcc3186e45
                  MATRIX: 1102->1|1455->261|1483->264|1499->272|1538->274|1567->278|1635->326|1663->328|1740->379|1863->480|1894->484|1984->547|2016->558|2045->560|2071->565|2100->567|2126->572|2381->800|2423->821|2469->840|2485->847|2515->856|2587->901|2603->908|2633->917|2731->988|2747->995|2780->1007|2825->1025|2848->1039|2883->1053|2929->1072|2945->1079|2976->1089|3066->1152|3082->1159|3113->1169|3182->1211|3198->1218|3231->1230|3325->1297|3341->1304|3371->1313|3440->1355|3456->1362|3489->1374|3946->1805|3993->1836|4032->1837|4068->1846|4114->1865|4140->1870|4187->1890|4203->1897|4265->1938|4312->1958|4328->1965|4390->2006|4437->2026|4453->2033|4513->2072|4560->2092|4576->2099|4636->2138|4683->2158|4699->2165|4760->2205|4807->2225|4823->2232|4883->2271|4930->2291|4946->2298|5008->2339|5066->2367|5100->2374|5206->2453|5222->2460|5251->2468|5380->2570|5396->2577|5421->2581|5552->2685|5568->2692|5598->2701|6193->3268|6233->3269|6269->3278|6317->3309|6357->3310|6394->3319|6441->3338|6468->3343|6508->3354|6584->3402|6611->3407|6786->3554|6803->3561|6866->3602|6989->3697|7029->3698|7071->3712|7094->3725|7170->3779|7228->3792|7268->3803|7317->3824|7372->3862|7412->3863|7453->3875|7497->3891|7517->3901|7546->3908|7577->3911|7597->3921|7628->3930|7681->3951|7743->3984|7865->4078|7882->4085|7945->4125|8058->4210|8075->4217|8135->4255|8250->4342|8267->4349|8330->4389|8401->4429|8453->4437|8487->4443|8594->4521|8624->4522|8661->4531|8702->4543|8732->4544|8771->4554|8961->4715|8991->4716|9031->4727|9167->4834|9197->4835|9253->4863|9283->4864|9319->4872|9348->4873|9384->4881|9441->4909|9471->4910|9508->4919|9914->5298|9969->5336|10009->5337|10046->5346|10111->5383|10131->5393|10160->5400|10191->5403|10211->5413|10242->5422|10293->5442|10329->5450|10767->5860|10796->5861|10831->5868|11113->6122|11130->6129|11156->6133|11307->6253|11350->6268|11442->6331|11472->6332|11503->6335|11597->6401|11626->6402
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|43->12|43->12|43->12|43->12|43->12|43->12|43->12|51->20|51->20|52->21|52->21|52->21|54->23|54->23|54->23|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|64->33|64->33|64->33|66->35|66->35|66->35|70->39|70->39|70->39|72->41|72->41|72->41|90->59|90->59|90->59|91->60|92->61|92->61|93->62|93->62|93->62|94->63|94->63|94->63|95->64|95->64|95->64|96->65|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|101->70|102->71|105->74|105->74|105->74|110->79|110->79|110->79|115->84|115->84|115->84|135->104|135->104|136->105|136->105|136->105|137->106|138->107|138->107|139->108|139->108|139->108|144->113|144->113|144->113|145->114|145->114|146->115|146->115|146->115|147->116|148->117|149->118|149->118|149->118|150->119|150->119|150->119|150->119|150->119|150->119|150->119|151->120|154->123|157->126|157->126|157->126|160->129|160->129|160->129|163->132|163->132|163->132|166->135|167->136|168->137|171->140|171->140|172->141|172->141|172->141|173->142|176->145|176->145|177->146|179->148|179->148|182->151|182->151|183->152|183->152|184->153|184->153|184->153|185->154|194->163|194->163|194->163|195->164|195->164|195->164|195->164|195->164|195->164|195->164|196->165|197->166|209->178|209->178|210->179|218->187|218->187|218->187|228->197|230->199|232->201|232->201|233->202|234->203|234->203
                  -- GENERATED --
              */
          