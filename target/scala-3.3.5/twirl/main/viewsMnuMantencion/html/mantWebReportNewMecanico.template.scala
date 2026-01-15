
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

object mantWebReportNewMecanico extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template15[Map[String,String],String,List[tables.MantEstadoEnObra],tables.MantOperadorMecanico,List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantComponente],List[tables.MantItemIntervenido],String,String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], fecha: String, listEstaOperacional: List[tables.MantEstadoEnObra], operMec: tables.MantOperadorMecanico, listTipoMantencion: List[tables.TipoMantencion], listEstadoEnTaller: List[tables.MantEstadoEnTaller], listActividad: List[tables.MantActividad], listTipoActividad: List[tables.MantTipoActividad], listComponentes: List[tables.MantComponente], listItemIntervenido: List[tables.MantItemIntervenido], idEquipo: String, cod_nameEquipo: String, id_bodega: String, suc_nameBodega: String, nameTipoBodega: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""	
	"""),format.raw/*3.2*/("""<table class="table table-sm table-bordered table-condensed table-fluid">
		<thead>
			<tr>
				<td style="text-align:left;"><strong>MECANICO (*):</strong></td>
		        <td style="text-align:left;  width: 70%" colspan="4">
		        	<input type="hidden" id="id_mecanico" name="id_mecanico" value=""""),_display_(/*8.77*/operMec/*8.84*/.getId()),format.raw/*8.92*/("""" required>
					<input type="hidden" id="id_operador" name="id_operador" value="0" required>
					<input type="text" class="form-control" value=""""),_display_(/*10.54*/operMec/*10.61*/.getNombre()),format.raw/*10.73*/("""" readonly>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>TIPO DE MANTENCION (*):</strong></td>
		        <td style="text-align:left;  width: 70%" colspan="4">
		        	<select class="form-control form-control-sm" 
						id="id_tipoMantencion" 
						name="id_tipoMantencion"
						onchange="selectTipoMantencion(value)"
						style="font-weight: bold; color: blue; font-size: 14px;"
						required>
			        		<option class="blank" value="0">-- Select --</option>
		            		"""),_display_(/*23.18*/for(lista <- listTipoMantencion) yield /*23.50*/{_display_(Seq[Any](format.raw/*23.51*/("""
		                		"""),format.raw/*24.21*/("""<option value=""""),_display_(/*24.37*/lista/*24.42*/.id),format.raw/*24.45*/("""" >"""),_display_(/*24.49*/lista/*24.54*/.nombre.toUpperCase()),format.raw/*24.75*/("""</option>
							""")))}),format.raw/*25.9*/("""
			        """),format.raw/*26.12*/("""</select> 
		        </td>
			</tr>
		</thead>
	</table>
	
	<div id="viewPreventivo" style="display:none;">		
		"""),_display_(/*33.4*/mantWebReportNewMecanicoPreventivo(mapDiccionario, fecha, listEstaOperacional, operMec, listTipoMantencion,
			listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes)),format.raw/*34.91*/("""
	"""),format.raw/*35.2*/("""</div>
	
	<div id="viewCorrectivo" style="display:none;">
		"""),_display_(/*38.4*/mantWebReportNewMecanicoCorrectivo(mapDiccionario, fecha, listEstaOperacional, operMec, listTipoMantencion,
			listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes,
			idEquipo, cod_nameEquipo, id_bodega, suc_nameBodega, nameTipoBodega)),format.raw/*40.72*/("""
	"""),format.raw/*41.2*/("""</div>

	<div style="display: none">
		<canvas id="signature-pad" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
		<canvas id="signature-pad2" class="signature-pad" width=350 height=90 style="border:1px solid #000000;"></canvas>
	</div>

		<input type="hidden" id="nombreEquipo_oper" value="0">
		<input type="hidden" id="nombreBodega_oper" value="0">



	"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],fecha:String,listEstaOperacional:List[tables.MantEstadoEnObra],operMec:tables.MantOperadorMecanico,listTipoMantencion:List[tables.TipoMantencion],listEstadoEnTaller:List[tables.MantEstadoEnTaller],listActividad:List[tables.MantActividad],listTipoActividad:List[tables.MantTipoActividad],listComponentes:List[tables.MantComponente],listItemIntervenido:List[tables.MantItemIntervenido],idEquipo:String,cod_nameEquipo:String,id_bodega:String,suc_nameBodega:String,nameTipoBodega:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,fecha,listEstaOperacional,operMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido,idEquipo,cod_nameEquipo,id_bodega,suc_nameBodega,nameTipoBodega)

  def f:((Map[String,String],String,List[tables.MantEstadoEnObra],tables.MantOperadorMecanico,List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantComponente],List[tables.MantItemIntervenido],String,String,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,fecha,listEstaOperacional,operMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido,idEquipo,cod_nameEquipo,id_bodega,suc_nameBodega,nameTipoBodega) => apply(mapDiccionario,fecha,listEstaOperacional,operMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido,idEquipo,cod_nameEquipo,id_bodega,suc_nameBodega,nameTipoBodega)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantWebReportNewMecanico.scala.html
                  HASH: ea206305a9e90be83cd6b86071544b5494c747ff
                  MATRIX: 1269->1|1910->549|1939->552|2266->853|2281->860|2309->868|2483->1015|2499->1022|2532->1034|3073->1548|3121->1580|3160->1581|3209->1602|3252->1618|3266->1623|3290->1626|3321->1630|3335->1635|3377->1656|3425->1674|3465->1686|3604->1799|3823->1997|3852->1999|3939->2060|4230->2330|4259->2332
                  LINES: 28->1|33->2|34->3|39->8|39->8|39->8|41->10|41->10|41->10|54->23|54->23|54->23|55->24|55->24|55->24|55->24|55->24|55->24|55->24|56->25|57->26|64->33|65->34|66->35|69->38|71->40|72->41
                  -- GENERATED --
              */
          