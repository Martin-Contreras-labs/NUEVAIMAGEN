
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

object bodegaFactorViga extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listBodegas: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalContactoBodega(mapDiccionario)),format.raw/*8.38*/("""
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "MANTENCION DE FACTOR VIGA PARA ESTIMAR CALCULO DE M2 DE LOSA", "")),format.raw/*13.99*/("""
		"""),format.raw/*14.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*20.13*/mapDiccionario/*20.27*/.get("BODEGA")),format.raw/*20.41*/("""/PROYECTO</TH>
							<TH>NOMBRE CLIENTE</TH>
					        <TH>NOMBRE DEL PROYECTO</TH>
							<TH>FACTOR</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*27.8*/for(lista1 <- listBodegas) yield /*27.34*/{_display_(Seq[Any](format.raw/*27.35*/("""
							"""),format.raw/*28.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*29.40*/lista1/*29.46*/.get(16)),format.raw/*29.54*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*30.73*/lista1/*30.79*/.get(5)),format.raw/*30.86*/("""')">"""),_display_(/*30.91*/lista1/*30.97*/.get(5)),format.raw/*30.104*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaCliente('"""),_display_(/*31.74*/lista1/*31.80*/.get(7)),format.raw/*31.87*/("""')">"""),_display_(/*31.92*/lista1/*31.98*/.get(7)),format.raw/*31.105*/("""</a></td>
								<td style="text-align:left;"><a href="#" onclick="buscaProyecto('"""),_display_(/*32.75*/lista1/*32.81*/.get(8)),format.raw/*32.88*/("""')">"""),_display_(/*32.93*/lista1/*32.99*/.get(8)),format.raw/*32.106*/("""</a></td>
								
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*35.37*/lista1/*35.43*/.get(12)),format.raw/*35.51*/("""</div>
									<input type="text" class="form-control height23px width120px right"
										value=""""),_display_(/*37.19*/lista1/*37.25*/.get(12)),format.raw/*37.33*/(""""
										onfocus="value=value.replace(/,/g,'')" 
										onkeydown="return ingresoDouble(window.event)"
										onchange="grabar('"""),_display_(/*40.30*/lista1/*40.36*/.get(1)),format.raw/*40.43*/("""',value); value = formatStandar(value,4);">
								</td>
							</TR>
			 			""")))}),format.raw/*43.9*/("""
					"""),format.raw/*44.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
""")))}),format.raw/*56.2*/("""




"""),format.raw/*61.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*62.31*/("""{"""),format.raw/*62.32*/("""
		  """),format.raw/*63.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*63.36*/("""{"""),format.raw/*63.37*/("""
		    	"""),format.raw/*64.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order":[[1,"asc"]],
		    	"language": """),format.raw/*67.20*/("""{"""),format.raw/*67.21*/("""
		        	"""),format.raw/*68.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*69.11*/("""}"""),format.raw/*69.12*/("""
		  """),format.raw/*70.5*/("""}"""),format.raw/*70.6*/(""" """),format.raw/*70.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*72.2*/("""}"""),format.raw/*72.3*/(""");
	
	const grabar = (id_bodegaEmpresa, valor) => """),format.raw/*74.46*/("""{"""),format.raw/*74.47*/("""
		"""),format.raw/*75.3*/("""var formData = new FormData();
		formData.append('id_bodegaEmpresa',id_bodegaEmpresa);
		formData.append('factorM2Viga',valor);
		$.ajax("""),format.raw/*78.10*/("""{"""),format.raw/*78.11*/("""
            """),format.raw/*79.13*/("""url: "/actualizaFactorVigaAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*86.36*/("""{"""),format.raw/*86.37*/("""
	     		"""),format.raw/*87.9*/("""if(respuesta=="error")"""),format.raw/*87.31*/("""{"""),format.raw/*87.32*/("""
	     			"""),format.raw/*88.10*/("""alertify.alert(msgError, function () """),format.raw/*88.47*/("""{"""),format.raw/*88.48*/("""
		     			"""),format.raw/*89.11*/("""location.href = "/";
		     		"""),format.raw/*90.10*/("""}"""),format.raw/*90.11*/(""");
	     		"""),format.raw/*91.9*/("""}"""),format.raw/*91.10*/("""
	     	"""),format.raw/*92.8*/("""}"""),format.raw/*92.9*/("""
        """),format.raw/*93.9*/("""}"""),format.raw/*93.10*/(""");
	"""),format.raw/*94.2*/("""}"""),format.raw/*94.3*/("""
	
"""),format.raw/*96.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listBodegas:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listBodegas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listBodegas) => apply(mapDiccionario,mapPermiso,userMnu,listBodegas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/bodegaFactorViga.scala.html
                  HASH: ef079557496267b57cd7c749f552341853fb2886
                  MATRIX: 1034->1|1257->131|1284->133|1300->141|1339->143|1368->147|1436->195|1466->200|1521->235|1549->238|1591->260|1620->263|1664->286|1695->290|1772->341|1888->436|1918->439|2249->743|2272->757|2307->771|2490->928|2532->954|2571->955|2606->963|2677->1007|2692->1013|2721->1021|2826->1099|2841->1105|2869->1112|2901->1117|2916->1123|2945->1130|3055->1213|3070->1219|3098->1226|3130->1231|3145->1237|3174->1244|3285->1328|3300->1334|3328->1341|3360->1346|3375->1352|3404->1359|3526->1454|3541->1460|3570->1468|3699->1570|3714->1576|3743->1584|3908->1722|3923->1728|3951->1735|4060->1814|4093->1820|4459->2156|4491->2161|4581->2223|4610->2224|4642->2229|4701->2260|4730->2261|4765->2269|4932->2408|4961->2409|5001->2421|5107->2499|5136->2500|5168->2505|5196->2506|5224->2507|5324->2580|5352->2581|5430->2631|5459->2632|5489->2635|5654->2772|5683->2773|5724->2786|5988->3022|6017->3023|6053->3032|6103->3054|6132->3055|6170->3065|6235->3102|6264->3103|6303->3114|6361->3144|6390->3145|6428->3156|6457->3157|6492->3165|6520->3166|6556->3175|6585->3176|6616->3180|6644->3181|6674->3184
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|45->14|51->20|51->20|51->20|58->27|58->27|58->27|59->28|60->29|60->29|60->29|61->30|61->30|61->30|61->30|61->30|61->30|62->31|62->31|62->31|62->31|62->31|62->31|63->32|63->32|63->32|63->32|63->32|63->32|66->35|66->35|66->35|68->37|68->37|68->37|71->40|71->40|71->40|74->43|75->44|87->56|92->61|93->62|93->62|94->63|94->63|94->63|95->64|98->67|98->67|99->68|100->69|100->69|101->70|101->70|101->70|103->72|103->72|105->74|105->74|106->75|109->78|109->78|110->79|117->86|117->86|118->87|118->87|118->87|119->88|119->88|119->88|120->89|121->90|121->90|122->91|122->91|123->92|123->92|124->93|124->93|125->94|125->94|127->96
                  -- GENERATED --
              */
          