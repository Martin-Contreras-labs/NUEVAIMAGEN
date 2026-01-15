
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

object mantReportNewMecanico extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],String,List[tables.MantEstadoEnObra],List[tables.MantOperadorMecanico],List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantComponente],List[tables.MantItemIntervenido],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], fecha: String, listEstaOperacional: List[tables.MantEstadoEnObra], 
listOperMec: List[tables.MantOperadorMecanico], listTipoMantencion: List[tables.TipoMantencion],
listEstadoEnTaller: List[tables.MantEstadoEnTaller], 
listActividad: List[tables.MantActividad], listTipoActividad: List[tables.MantTipoActividad],
listComponentes: List[tables.MantComponente], listItemIntervenido: List[tables.MantItemIntervenido] ):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*5.102*/("""	
	
	"""),format.raw/*7.2*/("""<table class="table table-sm table-bordered table-condensed table-fluid">
		<thead>
			<tr>
				<td style="text-align:left;"><strong>MECANICO (*):</strong></td>
		        <td style="text-align:left" width="70%" colspan="4">
		        	<input type="hidden" id="id_mecanico" name="id_mecanico" value="0" required>
		        	<div id="nombreMecanico">
		        		<div class="input-group input-group-sm">
						  <input class="form-control" type="text" 
							onclick="$('#modalListaMecanicos').modal('show');" 
							id="valNomMecanico"
							value="--- select ---" readonly>
						  <div class="input-group-append">
						    <span class="input-group-text" onclick="$('#modalListaMecanicos').modal('show');">Buscar</span>
						  </div>
						</div> 
    				</div>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>ORIGEN/TIPO:</strong></td>
		        <td style="text-align:left" width="70%" colspan="4">
					<input class="form-control" id="tipoMecanico" type="text" value="" readonly>
		        </td>
			</tr>
			<tr>
				<td style="text-align:left;"><strong>TIPO DE MANTENCION (*):</strong></td>
		        <td style="text-align:left" width="70%" colspan="4">
		        	<select class="form-control form-control-sm" 
						id="id_tipoMantencion" 
						name="id_tipoMantencion"
						onchange="selectTipoMantencion(value)"
						style="font-weight: bold; color: blue; font-size: 14px;"
						required>
			        		<option class="blank" value="0">-- Select --</option>
		            		"""),_display_(/*42.18*/for(lista <- listTipoMantencion) yield /*42.50*/{_display_(Seq[Any](format.raw/*42.51*/("""
		                		"""),format.raw/*43.21*/("""<option value=""""),_display_(/*43.37*/lista/*43.42*/.id),format.raw/*43.45*/("""" >"""),_display_(/*43.49*/lista/*43.54*/.nombre.toUpperCase()),format.raw/*43.75*/("""</option>
							""")))}),format.raw/*44.9*/("""
			        """),format.raw/*45.12*/("""</select> 
		        </td>
			</tr>
		</thead>
	</table>
	
	<div id="viewPreventivo" style="display:none;">		
		"""),_display_(/*52.4*/mantReportNewMecanicoPreventivo(mapDiccionario, fecha, listEstaOperacional, listOperMec, listTipoMantencion,
			listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes)),format.raw/*53.91*/("""
	"""),format.raw/*54.2*/("""</div>
	
	<div id="viewCorrectivo" style="display:none;">
		"""),_display_(/*57.4*/mantReportNewMecanicoCorrectivo(mapDiccionario, fecha, listEstaOperacional, listOperMec, listTipoMantencion,
			listEstadoEnTaller,listActividad,listTipoActividad,listItemIntervenido,listComponentes)),format.raw/*58.91*/("""
	"""),format.raw/*59.2*/("""</div>
	
	<div id="modalListaMecanicos" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title">Seleccionar mecanico</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
					<table id="tablaMecanicos" class="table table-sm table-bordered table-condensed table-hover table-fluid">
				        <thead style="background-color: #eeeeee">
							<TR> 
								<th>RUT</th>
								<th>Mecanico</th>
								<th>Origen/Tipo</th>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*80.9*/for(lista <- listOperMec) yield /*80.34*/{_display_(Seq[Any](format.raw/*80.35*/("""
								"""),_display_(if(lista.getId_mantActorPersonal() == 2)/*81.50*/{_display_(Seq[Any](format.raw/*81.51*/("""
									"""),format.raw/*82.10*/("""<TR onclick="selectMecanico('"""),_display_(/*82.40*/lista/*82.45*/.getId()),format.raw/*82.53*/("""', btoa('"""),_display_(/*82.63*/lista/*82.68*/.getNombre()),format.raw/*82.80*/("""'), btoa('"""),_display_(/*82.91*/lista/*82.96*/.getNameTipoPersonal()),format.raw/*82.118*/("""') )">
										<td style="text-align:left"><a href="#">"""),_display_(/*83.52*/lista/*83.57*/.getRut()),format.raw/*83.66*/("""</a></td>	
										<td style="text-align:left"><a href="#">"""),_display_(/*84.52*/lista/*84.57*/.getNombre()),format.raw/*84.69*/("""</a></td>
										<td style="text-align:left"><a href="#">"""),_display_(/*85.52*/lista/*85.57*/.getNameTipoPersonal()),format.raw/*85.79*/("""</a></td>
									</TR>
								""")))} else {null} ),format.raw/*87.10*/("""
								
							""")))}),format.raw/*89.9*/("""
						"""),format.raw/*90.7*/("""</tbody>
					</table>
			        <br>
					<div align="center">
			           	<button type="button" class="btn btn-sm btn-success" data-dismiss="modal" style="font-size: 10px;">CERRAR</button>
				    </div>
			      </div>
			    </div>
			 </div>
		</div>
	
	
	
	"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],fecha:String,listEstaOperacional:List[tables.MantEstadoEnObra],listOperMec:List[tables.MantOperadorMecanico],listTipoMantencion:List[tables.TipoMantencion],listEstadoEnTaller:List[tables.MantEstadoEnTaller],listActividad:List[tables.MantActividad],listTipoActividad:List[tables.MantTipoActividad],listComponentes:List[tables.MantComponente],listItemIntervenido:List[tables.MantItemIntervenido]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,fecha,listEstaOperacional,listOperMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido)

  def f:((Map[String,String],String,List[tables.MantEstadoEnObra],List[tables.MantOperadorMecanico],List[tables.TipoMantencion],List[tables.MantEstadoEnTaller],List[tables.MantActividad],List[tables.MantTipoActividad],List[tables.MantComponente],List[tables.MantItemIntervenido]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,fecha,listEstaOperacional,listOperMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido) => apply(mapDiccionario,fecha,listEstaOperacional,listOperMec,listTipoMantencion,listEstadoEnTaller,listActividad,listTipoActividad,listComponentes,listItemIntervenido)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantReportNewMecanico.scala.html
                  HASH: 98ca4245c36cd83f8e0cd65f4496d3c73215401a
                  MATRIX: 1237->1|1784->452|1815->457|3357->1972|3405->2004|3444->2005|3493->2026|3536->2042|3550->2047|3574->2050|3605->2054|3619->2059|3661->2080|3709->2098|3749->2110|3888->2223|4108->2422|4137->2424|4224->2485|4444->2684|4473->2686|5340->3527|5381->3552|5420->3553|5497->3603|5536->3604|5574->3614|5631->3644|5645->3649|5674->3657|5711->3667|5725->3672|5758->3684|5796->3695|5810->3700|5854->3722|5939->3780|5953->3785|5983->3794|6072->3856|6086->3861|6119->3873|6207->3934|6221->3939|6264->3961|6342->3995|6390->4013|6424->4020
                  LINES: 28->1|37->5|39->7|74->42|74->42|74->42|75->43|75->43|75->43|75->43|75->43|75->43|75->43|76->44|77->45|84->52|85->53|86->54|89->57|90->58|91->59|112->80|112->80|112->80|113->81|113->81|114->82|114->82|114->82|114->82|114->82|114->82|114->82|114->82|114->82|114->82|115->83|115->83|115->83|116->84|116->84|116->84|117->85|117->85|117->85|119->87|121->89|122->90
                  -- GENERATED --
              */
          