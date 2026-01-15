
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

object generaProformaApiRelBase extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,String,String,Long,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
json: String, jsonRefer: String, id_proforma: Long, desde: String, hasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "PROFORMA FACTURA XML","COMPLETAR O MODIFICAR REFERENCIAS Y OBSERVACIONES")),format.raw/*9.107*/("""
		"""),format.raw/*10.3*/("""<form class="formulario" method="post" action="/generaProformaApiRelBase2/">
			<div class="container">
				<input type="hidden" name="fechaDesde" value=""""),_display_(/*12.52*/desde),format.raw/*12.57*/("""">
				<input type="hidden" name="fechaHasta" value=""""),_display_(/*13.52*/hasta),format.raw/*13.57*/("""">
				<input type="hidden" name="id_proforma" value=""""),_display_(/*14.53*/id_proforma),format.raw/*14.64*/("""">
				<div id="tblReferencia"></div>
				<button type='submit'>ENVIAR</button>
			</div>
		</form>
	</div>

""")))}),format.raw/*21.2*/("""
													
"""),format.raw/*23.1*/("""<script type="text/javascript">

		let jsonAux = '"""),_display_(/*25.19*/json),format.raw/*25.23*/("""';
		jsonAux = jsonAux.replace(/\n/g, "\\n").replace(/\r/g, "\\r").replace(/&quot;/g, '"');
		let json = JSON.parse(jsonAux);

		jsonAux = '"""),_display_(/*29.15*/jsonRefer),format.raw/*29.24*/("""';
		jsonAux = jsonAux.replace(/\n/g, "\\n").replace(/\r/g, "\\r").replace(/&quot;/g, '"');
		let jsonRefer = JSON.parse(jsonAux);
		const mapRefer = new Map(Object.entries(jsonRefer));

	$(document).ready(function() """),format.raw/*34.31*/("""{"""),format.raw/*34.32*/("""

		"""),format.raw/*36.3*/("""let vista = "<table id=\"referencia\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
						+ "<tr>"
				 			+ "<td colspan=\"4\">REFERENCIAS:</td>"
				 			+ "<td colspan=\"1\">"
				 				+ "<div align=\"right\">"
				 					+ "<a  class=\"btn btn-warning btn-mini\" onclick=\"agregarReferencia()\">agregar referencia</a>"
				 				+ "</div>"
				 			+ "</td>"
				 		+ "</tr>"
				 		+ "<tr>"
				 			+ "<td>NroLinRef</td>"
				 			+ "<td>TpoDocRef</td>"
				 			+ "<td>FolioRef</td>"
				 			+ "<td>FchRef</td>"
				 			+ "<td>RazonRef</td>"
							+ "<td>del</td>"
				 		+ "</tr>";
					if (json.references && Array.isArray(json.references) && json.references.length > 0) """),format.raw/*53.91*/("""{"""),format.raw/*53.92*/("""
						  """),format.raw/*54.9*/("""json.references.forEach((ref, index) => """),format.raw/*54.49*/("""{"""),format.raw/*54.50*/("""
							"""),format.raw/*55.8*/("""let refer = mapRefer.get(""+ref.reference_id);
							vista += "<tr>"
										+ "<td>"+(index+1)+"<input type=\"hidden\" name=\"nroLinRef[]\" value=\""+(index+1)+"\"></td>"
										+ "<td>"
												 +"<select name='tpoDocRef[]' >"
													+"<option value="+ref.reference_id+">"+refer+"</option>";
												
												const listaCampos = Object.entries(jsonRefer);
												listaCampos.forEach(([campo, valor]) => """),format.raw/*63.53*/("""{"""),format.raw/*63.54*/("""
													"""),format.raw/*64.14*/("""vista += "<option value="+campo+">"+valor+"</option>";
												"""),format.raw/*65.13*/("""}"""),format.raw/*65.14*/(""");
										+ "</td>"
								vista += "</select></td>"
											+ "<td><input type=\"text\" name=\"folioRef[]\" value=\""+ref.folio_ref+"\"></td>"
											+ "<td><input type=\"date\" name=\"fchRef[]\" value=\""+ref.date_ref+"\"></td>"
											+ "<td><input type=\"text\" name=\"razonRef[]\" value=\""+ref.razon_ref+"\"></td>"
											+ "<td><a class='btn btn-mini btn-danger' onclick='eliminaReferencia(this)'>X</a></td>"
						  """),format.raw/*72.9*/("""}"""),format.raw/*72.10*/(""");
					"""),format.raw/*73.6*/("""}"""),format.raw/*73.7*/("""
					
		"""),format.raw/*75.3*/("""vista += "<table id=\"comentarios\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
				 	+ "<tr>"
				 		+ "<td>OBSEVACIONES:</td>"
				 	+ "</tr>"
				 	+ "<tr>"
				 		+ "<td><textarea rows=\"4\" style=\"width:100%\" name=\"sol_observaciones\">"+json.comment+"</textarea></td>"
				 	+ "</tr>"
				+ "</table>";
					
		$("#tblReferencia").html(vista);
					
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*87.2*/("""}"""),format.raw/*87.3*/(""");
	


	let eliminaReferencia = (nodo) =>"""),format.raw/*91.35*/("""{"""),format.raw/*91.36*/("""
 			"""),format.raw/*92.5*/("""nodo.parentNode.parentNode.remove();
 			let tabla = document.getElementById('referencia');
 			for (let i = 2; i < tabla.rows.length; i++)"""),format.raw/*94.48*/("""{"""),format.raw/*94.49*/("""
 				"""),format.raw/*95.6*/("""let cellsOfRow = tabla.rows[i].getElementsByTagName('td');
 				cellsOfRow[0].innerHTML=i-1;
 			"""),format.raw/*97.5*/("""}"""),format.raw/*97.6*/("""
 	"""),format.raw/*98.3*/("""}"""),format.raw/*98.4*/("""
		
	"""),format.raw/*100.2*/("""function agregarReferencia()"""),format.raw/*100.30*/("""{"""),format.raw/*100.31*/("""
		"""),format.raw/*101.3*/("""var tabla = document.getElementById('referencia');
		var row = tabla.insertRow(tabla.rows.length);
		
		var cell=row.insertCell(0);
		var indice = tabla.rows.length-2;
		cell.innerHTML=indice+"<input type='hidden' name='nroLinRef[]' value='"+indice+"'>";
		
		cell=row.insertCell(1);
		var listado = "<select name='tpoDocRef[]'>";
		const listaCampos = Object.entries(jsonRefer);
		listaCampos.forEach(([campo, valor]) => """),format.raw/*111.43*/("""{"""),format.raw/*111.44*/("""
			"""),format.raw/*112.4*/("""listado += "<option value="+campo+">"+valor+"</option>";
		"""),format.raw/*113.3*/("""}"""),format.raw/*113.4*/(""");
		listado = listado + "</select>";
		cell.innerHTML=listado;
		
		cell=row.insertCell(2);
		cell.innerHTML="<input type='text' name='folioRef[]' value=''>";
		
		cell=row.insertCell(3);
		cell.innerHTML="<input type='date' name='fchRef[]' value=''>";
		
		cell=row.insertCell(4);
		cell.innerHTML="<input type='text' name='razonRef[]' value=''>";
		
		cell=row.insertCell(5);
		cell.innerHTML="<td><a class='btn btn-mini btn-danger' onclick='eliminaReferencia(this)'>X</a>";
		
	"""),format.raw/*129.2*/("""}"""),format.raw/*129.3*/("""
	
"""),format.raw/*131.1*/("""</script>
				

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,json:String,jsonRefer:String,id_proforma:Long,desde:String,hasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,json,jsonRefer,id_proforma,desde,hasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String,Long,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,json,jsonRefer,id_proforma,desde,hasta) => apply(mapDiccionario,mapPermiso,userMnu,json,jsonRefer,id_proforma,desde,hasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/generaProformaApiRelBase.scala.html
                  HASH: b71a55108d1e72358a90661d2fd8b3a81fb41786
                  MATRIX: 1057->1|1329->180|1357->183|1373->191|1412->193|1441->197|1509->245|1537->247|1613->298|1737->401|1767->404|1949->559|1975->564|2056->618|2082->623|2164->678|2196->689|2336->799|2378->814|2456->865|2481->869|2649->1010|2679->1019|2924->1236|2953->1237|2984->1241|3722->1951|3751->1952|3787->1961|3855->2001|3884->2002|3919->2010|4381->2444|4410->2445|4452->2459|4547->2526|4576->2527|5046->2970|5075->2971|5110->2979|5138->2980|5174->2989|5665->3453|5693->3454|5762->3495|5791->3496|5823->3501|5990->3640|6019->3641|6052->3647|6176->3744|6204->3745|6234->3748|6262->3749|6295->3754|6352->3782|6382->3783|6413->3786|6864->4208|6894->4209|6926->4213|7013->4272|7042->4273|7552->4755|7581->4756|7612->4759
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|43->12|43->12|44->13|44->13|45->14|45->14|52->21|54->23|56->25|56->25|60->29|60->29|65->34|65->34|67->36|84->53|84->53|85->54|85->54|85->54|86->55|94->63|94->63|95->64|96->65|96->65|103->72|103->72|104->73|104->73|106->75|118->87|118->87|122->91|122->91|123->92|125->94|125->94|126->95|128->97|128->97|129->98|129->98|131->100|131->100|131->100|132->101|142->111|142->111|143->112|144->113|144->113|160->129|160->129|162->131
                  -- GENERATED --
              */
          