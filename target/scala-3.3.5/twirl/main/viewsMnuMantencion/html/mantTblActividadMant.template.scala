
package viewsMnuMantencion.html

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

object mantTblActividadMant extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.MantActividad],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listActividad: List[tables.MantActividad]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "ACTIVIDADES: CREAR/MODIFICAR/ELIMINAR", "")),format.raw/*9.76*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style="vertical-align: top;">NOMBRE DE ACTIVIDAD</TH>
							<TH>EDIT</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*21.8*/for(lista1 <- listActividad) yield /*21.36*/{_display_(Seq[Any](format.raw/*21.37*/("""
							"""),format.raw/*22.8*/("""<TR>
								<td  style="text-align:left;"><div id=""""),_display_(/*23.49*/lista1/*23.55*/.getId()),format.raw/*23.63*/("""">"""),_display_(/*23.66*/lista1/*23.72*/.getNombre()),format.raw/*23.84*/("""</div></td>
								<td  style="text-align:center;">
									<form id="form_"""),_display_(/*25.26*/lista1/*25.32*/.getId()),format.raw/*25.40*/("""" method="post" action="/routes3/mantTblActividadUpdate/">
										<input type="hidden" name="id_actividad" value=""""),_display_(/*26.60*/lista1/*26.66*/.getId()),format.raw/*26.74*/("""">
										<a href="#" onclick='document.getElementById("form_"""),_display_(/*27.63*/lista1/*27.69*/.getId()),format.raw/*27.77*/("""").submit();'>
											<kbd style="background-color: #73C6B6">E</kbd>
										</a>
									</form>
								</td>
								<td  style="text-align:center;">
									<a href="#" onclick= "eliminarActividad('"""),_display_(/*33.52*/lista1/*33.58*/.getId()),format.raw/*33.66*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*38.9*/("""
					"""),format.raw/*39.6*/("""</tbody>
				</table>
			</div>
		</div>	
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes3/mantTblActividadNew/';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/routes3/mantTblActividadDel/">
		<input type="hidden" id="form_id_actividad" name="id_actividad">
	</form>
""")))}),format.raw/*58.2*/("""




"""),format.raw/*63.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*64.31*/("""{"""),format.raw/*64.32*/("""
		  """),format.raw/*65.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*65.36*/("""{"""),format.raw/*65.37*/("""
		    	"""),format.raw/*66.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*68.20*/("""{"""),format.raw/*68.21*/("""
		        	"""),format.raw/*69.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*70.11*/("""}"""),format.raw/*70.12*/("""
		  """),format.raw/*71.5*/("""}"""),format.raw/*71.6*/(""" """),format.raw/*71.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*73.2*/("""}"""),format.raw/*73.3*/(""");
	const eliminarActividad = (id_actividad) => """),format.raw/*74.46*/("""{"""),format.raw/*74.47*/("""
		"""),format.raw/*75.3*/("""let nombre = $("#"+id_actividad).text();
		alertify.confirm("Esta seguro de eliminar la actividad: "+nombre, function (e) """),format.raw/*76.82*/("""{"""),format.raw/*76.83*/("""
			"""),format.raw/*77.4*/("""if (e) """),format.raw/*77.11*/("""{"""),format.raw/*77.12*/("""
				"""),format.raw/*78.5*/("""$("#form_id_actividad").val(id_actividad);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*80.4*/("""}"""),format.raw/*80.5*/("""
		"""),format.raw/*81.3*/("""}"""),format.raw/*81.4*/(""");
	"""),format.raw/*82.2*/("""}"""),format.raw/*82.3*/("""
"""),format.raw/*83.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listActividad:List[tables.MantActividad]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listActividad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.MantActividad]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listActividad) => apply(mapDiccionario,mapPermiso,userMnu,listActividad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantTblActividadMant.scala.html
                  HASH: 620bc37d569ce85be46079a4e8cf05f78e45f056
                  MATRIX: 1049->1|1282->141|1315->149|1331->157|1370->159|1398->162|1466->210|1494->212|1570->263|1662->335|1692->338|2138->758|2182->786|2221->787|2256->795|2336->848|2351->854|2380->862|2410->865|2425->871|2458->883|2563->961|2578->967|2607->975|2752->1093|2767->1099|2796->1107|2888->1172|2903->1178|2932->1186|3170->1397|3185->1403|3214->1411|3351->1518|3384->1524|4130->2240|4162->2245|4252->2307|4281->2308|4313->2313|4372->2344|4401->2345|4436->2353|4578->2467|4607->2468|4647->2480|4753->2558|4782->2559|4814->2564|4842->2565|4870->2566|4970->2639|4998->2640|5074->2688|5103->2689|5133->2692|5283->2814|5312->2815|5343->2819|5378->2826|5407->2827|5439->2832|5567->2933|5595->2934|5625->2937|5653->2938|5684->2942|5712->2943|5740->2944
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|54->23|54->23|54->23|54->23|54->23|54->23|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|64->33|64->33|64->33|69->38|70->39|89->58|94->63|95->64|95->64|96->65|96->65|96->65|97->66|99->68|99->68|100->69|101->70|101->70|102->71|102->71|102->71|104->73|104->73|105->74|105->74|106->75|107->76|107->76|108->77|108->77|108->77|109->78|111->80|111->80|112->81|112->81|113->82|113->82|114->83
                  -- GENERATED --
              */
          