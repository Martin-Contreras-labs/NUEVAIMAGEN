
package viewsMnuOdo.html

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

object ajustesEpNuevoOdo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,utilities.Fechas,List[tables.TipoAjustes],Long,tables.Moneda,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodegaEmpresa: tables.BodegaEmpresa, hoy: utilities.Fechas, listAjustes: List[tables.TipoAjustes], numDec: Long, moneda: tables.Moneda):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

"""),_display_(/*6.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.50*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "NUEVO AJUSTE A EP/PROFORMA SERVICIO", bodegaEmpresa.nombre)),format.raw/*8.92*/("""
		"""),format.raw/*9.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-10 col-sm-5 col-md-5 col-lg-5">
				<form id="form" action="/ajustesEpGrabarOdo/" method="POST">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<TR>
							<td colspan="2" style="text-align:left;">
								<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*15.62*/bodegaEmpresa/*15.75*/.id),format.raw/*15.78*/("""" required>
								<input type="hidden" name="id_bodega" value=""""),_display_(/*16.55*/bodegaEmpresa/*16.68*/.id),format.raw/*16.71*/("""" required>
								"""),_display_(/*17.10*/bodegaEmpresa/*17.23*/.nombre),format.raw/*17.30*/("""
							"""),format.raw/*18.8*/("""</td>
						</TR>
						<tr>
							<td style="text-align: left;">CUANDO APLICA AJUSTE:</td>
							<td style="text-align: left;">
						  		<input type="date" class="form-control left"
						  			name="fechaAjuste"
						  			onblur="if(!limitaFecha(value,"""),_display_(/*25.43*/mapPermiso/*25.53*/.get("parametro.diasMenosGuia")),format.raw/*25.84*/(""",70)) value='"""),_display_(/*25.98*/hoy/*25.101*/.getFechaStrAAMMDD()),format.raw/*25.121*/("""';"
						  			value=""""),_display_(/*26.20*/hoy/*26.23*/.getFechaStrAAMMDD()),format.raw/*26.43*/(""""
				        			required>
							</td>
						</tr>
						<tr>
							<td style="text-align: left;">TIPO DE AJUSTE:</td>
							<td style="text-align: left;">
								<select class="custom-select left"
									name="id_tipoAjuste">
									"""),_display_(/*35.11*/for(lista <- listAjustes) yield /*35.36*/{_display_(Seq[Any](format.raw/*35.37*/("""
								     	"""),format.raw/*36.15*/("""<option value=""""),_display_(/*36.31*/lista/*36.36*/.id),format.raw/*36.39*/("""">"""),_display_(/*36.42*/lista/*36.47*/.ajuste),format.raw/*36.54*/("""</option>
									""")))}),format.raw/*37.11*/("""
								"""),format.raw/*38.9*/("""</select>
						    </td>
						</tr>
						<TR>
					        <td style="text-align:left;padding-left:10px">MONEDA:</td>
					        <td style="text-align:left">
					        	<input type="hidden" name="id_moneda" value=""""),_display_(/*44.61*/moneda/*44.67*/.id),format.raw/*44.70*/("""" required>
								<input type="text" class="form-control left"
									value=""""),_display_(/*46.18*/moneda/*46.24*/.nickName),format.raw/*46.33*/(""""
									readonly>
					        </td>
						</TR>
						<TR>
					        <td style="text-align:left;padding-left:10px">MONTO A AJUSTAR:</td>
					        <td style="text-align:left">
								<input type="text" class="form-control left"
									name="totalAjuste"
									onfocus="value = value.replace(/,/g,'');" 
									onkeydown="return ingresoDouble(window.event)"
									autocomplete="off"
									onchange="if(value=='') value=0; value = formatStandar(value,'"""),_display_(/*58.73*/numDec),format.raw/*58.79*/("""');"
									required>
					        </td>
						</TR>
						<TR>
					        <td style="text-align:left;padding-left:10px">MOTIVO:</td>
					        <td style="text-align:left">
								<input type="text" class="form-control left"
									name="concepto"
									autocomplete="off"
									onchange="value = value.trim();"
									required>
					        </td>
						</TR>
						<TR>
					        <td style="text-align:left;padding-left:10px">NOTAS:</td>
					        <td style="text-align:left">
								<textarea class="form-control left" 
									rows="4"
  									name="observaciones" 
  									autocomplete="off"
									onchange="$('#seModifico').val(1);"></textarea>
							</td>
						</TR>
					</table>
					<div class="row justify-content-md-center" >
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
							<input type="submit" value='GRABAR' class="btn btn-primary btn-sm rounded-pill btn-block">
						</div>
						<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
							<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="history.go(-1);return false;">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

""")))}),format.raw/*96.2*/("""



"""),format.raw/*100.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*102.31*/("""{"""),format.raw/*102.32*/("""
		  """),format.raw/*103.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*104.2*/("""}"""),format.raw/*104.3*/(""");
	

</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodegaEmpresa:tables.BodegaEmpresa,hoy:utilities.Fechas,listAjustes:List[tables.TipoAjustes],numDec:Long,moneda:tables.Moneda): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodegaEmpresa,hoy,listAjustes,numDec,moneda)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,utilities.Fechas,List[tables.TipoAjustes],Long,tables.Moneda) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodegaEmpresa,hoy,listAjustes,numDec,moneda) => apply(mapDiccionario,mapPermiso,userMnu,bodegaEmpresa,hoy,listAjustes,numDec,moneda)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/ajustesEpNuevoOdo.scala.html
                  HASH: d6e1406add152d31331b974e802d6f6679c2f112
                  MATRIX: 1094->1|1420->234|1452->241|1468->249|1507->251|1535->254|1603->302|1631->304|1707->355|1815->443|1844->446|2235->810|2257->823|2281->826|2374->892|2396->905|2420->908|2468->929|2490->942|2518->949|2553->957|2838->1215|2857->1225|2909->1256|2950->1270|2963->1273|3005->1293|3055->1316|3067->1319|3108->1339|3378->1582|3419->1607|3458->1608|3501->1623|3544->1639|3558->1644|3582->1647|3612->1650|3626->1655|3654->1662|3705->1682|3741->1691|3991->1914|4006->1920|4030->1923|4139->2005|4154->2011|4184->2020|4686->2495|4713->2501|5944->3702|5976->3706|6068->3769|6098->3770|6131->3775|6225->3841|6254->3842
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|46->15|46->15|46->15|47->16|47->16|47->16|48->17|48->17|48->17|49->18|56->25|56->25|56->25|56->25|56->25|56->25|57->26|57->26|57->26|66->35|66->35|66->35|67->36|67->36|67->36|67->36|67->36|67->36|67->36|68->37|69->38|75->44|75->44|75->44|77->46|77->46|77->46|89->58|89->58|127->96|131->100|133->102|133->102|134->103|135->104|135->104
                  -- GENERATED --
              */
          