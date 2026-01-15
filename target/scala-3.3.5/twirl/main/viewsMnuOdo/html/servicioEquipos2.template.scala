
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

object servicioEquipos2 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.EquipoServicio],List[tables.EquipoServicio],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listEqInscritos: List[tables.EquipoServicio], listEqNoInscritos: List[tables.EquipoServicio]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MANTENCION DE EQUIPOS PARA SERVICIOS", "("+bodega.getNombre().toUpperCase()+")")),format.raw/*9.113*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style= "text-align: center;">"""),_display_(/*16.42*/mapDiccionario("BODEGA")),format.raw/*16.66*/("""/PROYECTO</TH>
							<TH style= "text-align: center;">SUCURSAL</TH>
					        <TH style= "text-align: center;">CODIGO</TH>
							<TH style= "text-align: center;">EQUIPO</TH>
							<TH style= "text-align: center;">VIGENTE</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*24.8*/for(lista1 <- listEqInscritos) yield /*24.38*/{_display_(Seq[Any](format.raw/*24.39*/("""
							"""),format.raw/*25.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*26.39*/lista1/*26.45*/.getBodega()),format.raw/*26.57*/("""</td>
								<td style="text-align:left;">"""),_display_(/*27.39*/lista1/*27.45*/.getNameSucursal()),format.raw/*27.63*/("""</td>
								<td style="text-align:center;">"""),_display_(/*28.41*/lista1/*28.47*/.getCodigo()),format.raw/*28.59*/("""</td>
								<td style="text-align:left;">"""),_display_(/*29.39*/lista1/*29.45*/.getEquipo()),format.raw/*29.57*/("""</td>
								<td  style="text-align:center;">
									<input type="hidden" id="vigente_"""),_display_(/*31.44*/lista1/*31.50*/.getId_bodegaEmpresa()),_display_(/*31.73*/lista1/*31.79*/.getId_equipo()),format.raw/*31.94*/("""" value=""""),_display_(/*31.104*/lista1/*31.110*/.getVigente()),format.raw/*31.123*/("""">
									"""),_display_(if(lista1.getVigente()==1)/*32.37*/{_display_(Seq[Any](format.raw/*32.38*/("""
										"""),format.raw/*33.11*/("""<input type="checkbox" checked onchange="modificaVigente('"""),_display_(/*33.70*/lista1/*33.76*/.getId_bodegaEmpresa()),format.raw/*33.98*/("""','"""),_display_(/*33.102*/lista1/*33.108*/.getId_equipo()),format.raw/*33.123*/("""');">
									""")))}else/*34.15*/{_display_(Seq[Any](format.raw/*34.16*/("""
										"""),format.raw/*35.11*/("""<input type="checkbox" onchange="modificaVigente('"""),_display_(/*35.62*/lista1/*35.68*/.getId_bodegaEmpresa()),format.raw/*35.90*/("""','"""),_display_(/*35.94*/lista1/*35.100*/.getId_equipo()),format.raw/*35.115*/("""');">
									""")))}),format.raw/*36.11*/("""
								"""),format.raw/*37.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*39.9*/("""
					"""),format.raw/*40.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR EQUIPO" class="btn btn-info btn-sm rounded-pill btn-block" onclick='$("#equiposNoEnBod").modal("show")'>
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/servicioEquipos0/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='equiposNoEnBod' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR EQUIPO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<table id="tablaListaEquipoNoEnBod" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>GRUPO</TH>
								<TH>CODIGO</TH>
								<TH>EQUIPO</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*75.9*/for(lista1 <- listEqNoInscritos) yield /*75.41*/{_display_(Seq[Any](format.raw/*75.42*/("""
								"""),format.raw/*76.9*/("""<TR onClick="$('#id_equipo').val('"""),_display_(/*76.44*/lista1/*76.50*/.getId_equipo()),format.raw/*76.65*/("""'); document.getElementById('formAgregaEquipo').submit();" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*77.41*/lista1/*77.47*/.getGrupo()),format.raw/*77.58*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*78.41*/lista1/*78.47*/.getCodigo()),format.raw/*78.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*79.41*/lista1/*79.47*/.getEquipo()),format.raw/*79.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*81.10*/("""
						"""),format.raw/*82.7*/("""</tbody>
					</table>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<form class="formulario" id="formAgregaEquipo" method="post" action="/servicioEquipo3/">	
		<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*95.56*/bodega/*95.62*/.getId()),format.raw/*95.70*/("""">
		<input type="hidden" id="id_equipo" name="id_equipo">
	</form>
	
""")))}),format.raw/*99.2*/("""


"""),format.raw/*102.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*103.31*/("""{"""),format.raw/*103.32*/("""
		
		  """),format.raw/*105.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*105.36*/("""{"""),format.raw/*105.37*/("""
		    	"""),format.raw/*106.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*109.20*/("""{"""),format.raw/*109.21*/("""
		        	"""),format.raw/*110.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*111.11*/("""}"""),format.raw/*111.12*/("""
		  """),format.raw/*112.5*/("""}"""),format.raw/*112.6*/(""" """),format.raw/*112.7*/(""");

		  $('#tablaListaEquipoNoEnBod').DataTable("""),format.raw/*114.45*/("""{"""),format.raw/*114.46*/("""
		    	"""),format.raw/*115.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*118.20*/("""{"""),format.raw/*118.21*/("""
		        	"""),format.raw/*119.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*120.11*/("""}"""),format.raw/*120.12*/("""
		  """),format.raw/*121.5*/("""}"""),format.raw/*121.6*/(""" """),format.raw/*121.7*/(""");

		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*124.2*/("""}"""),format.raw/*124.3*/(""");
	
	const modificaVigente = (id_bodega, id_equipo) => """),format.raw/*126.52*/("""{"""),format.raw/*126.53*/("""
		"""),format.raw/*127.3*/("""let vigente = $("#vigente_"+id_bodega+""+id_equipo).val();
		if(vigente==1)"""),format.raw/*128.17*/("""{"""),format.raw/*128.18*/("""
			"""),format.raw/*129.4*/("""modificaVigencia(id_bodega, id_equipo, 0);
			$("#vigente_"+id_bodega+""+id_equipo).val(0);
		"""),format.raw/*131.3*/("""}"""),format.raw/*131.4*/("""else"""),format.raw/*131.8*/("""{"""),format.raw/*131.9*/("""
			"""),format.raw/*132.4*/("""modificaVigencia(id_bodega, id_equipo, 1);
			$("#vigente_"+id_bodega+""+id_equipo).val(1);
		"""),format.raw/*134.3*/("""}"""),format.raw/*134.4*/("""
	"""),format.raw/*135.2*/("""}"""),format.raw/*135.3*/("""
	
	"""),format.raw/*137.2*/("""const modificaVigencia = (id_bodega, id_equipo, valor) => """),format.raw/*137.60*/("""{"""),format.raw/*137.61*/("""
		"""),format.raw/*138.3*/("""let formData = new FormData();
		formData.append('id_bodega',id_bodega);
		formData.append('id_equipo',id_equipo);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*142.10*/("""{"""),format.raw/*142.11*/("""
            """),format.raw/*143.13*/("""url: "/modVigEquipoServicioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*150.36*/("""{"""),format.raw/*150.37*/("""
	     		"""),format.raw/*151.9*/("""if(respuesta=="error")"""),format.raw/*151.31*/("""{"""),format.raw/*151.32*/("""
	     			"""),format.raw/*152.10*/("""alertify.alert(msgError, function () """),format.raw/*152.47*/("""{"""),format.raw/*152.48*/("""
		     			"""),format.raw/*153.11*/("""location.href = "/";
		     		"""),format.raw/*154.10*/("""}"""),format.raw/*154.11*/(""");
	     		"""),format.raw/*155.9*/("""}"""),format.raw/*155.10*/("""
	     	"""),format.raw/*156.8*/("""}"""),format.raw/*156.9*/("""
        """),format.raw/*157.9*/("""}"""),format.raw/*157.10*/(""");
	"""),format.raw/*158.2*/("""}"""),format.raw/*158.3*/("""
	
"""),format.raw/*160.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listEqInscritos:List[tables.EquipoServicio],listEqNoInscritos:List[tables.EquipoServicio]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listEqInscritos,listEqNoInscritos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.EquipoServicio],List[tables.EquipoServicio]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listEqInscritos,listEqNoInscritos) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listEqInscritos,listEqNoInscritos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/servicioEquipos2.scala.html
                  HASH: 27511b6b1c0fabc8038a5128226e44268ef58bf0
                  MATRIX: 1088->1|1402->222|1435->230|1451->238|1490->240|1518->243|1586->291|1614->293|1690->344|1820->453|1850->456|2194->773|2239->797|2543->1075|2589->1105|2628->1106|2663->1114|2733->1157|2748->1163|2781->1175|2852->1219|2867->1225|2906->1243|2979->1289|2994->1295|3027->1307|3098->1351|3113->1357|3146->1369|3263->1459|3278->1465|3321->1488|3336->1494|3372->1509|3410->1519|3426->1525|3461->1538|3527->1577|3566->1578|3605->1589|3691->1648|3706->1654|3749->1676|3781->1680|3797->1686|3834->1701|3873->1721|3912->1722|3951->1733|4029->1784|4044->1790|4087->1812|4118->1816|4134->1822|4171->1837|4218->1853|4254->1862|4311->1889|4344->1895|5731->3256|5779->3288|5818->3289|5854->3298|5916->3333|5931->3339|5967->3354|6115->3475|6130->3481|6162->3492|6235->3538|6250->3544|6283->3556|6356->3602|6371->3608|6404->3620|6464->3649|6498->3656|6966->4097|6981->4103|7010->4111|7111->4182|7142->4185|7233->4247|7263->4248|7299->4256|7359->4287|7389->4288|7425->4296|7613->4455|7643->4456|7684->4468|7791->4546|7821->4547|7854->4552|7883->4553|7912->4554|7989->4602|8019->4603|8055->4611|8230->4757|8260->4758|8301->4770|8408->4848|8438->4849|8471->4854|8500->4855|8529->4856|8631->4930|8660->4931|8745->4987|8775->4988|8806->4991|8910->5066|8940->5067|8972->5071|9094->5165|9123->5166|9155->5170|9184->5171|9216->5175|9338->5269|9367->5270|9397->5272|9426->5273|9458->5277|9545->5335|9575->5336|9606->5339|9793->5497|9823->5498|9865->5511|10131->5748|10161->5749|10198->5758|10249->5780|10279->5781|10318->5791|10384->5828|10414->5829|10454->5840|10513->5870|10543->5871|10582->5882|10612->5883|10648->5891|10677->5892|10714->5901|10744->5902|10776->5906|10805->5907|10836->5910
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|55->24|55->24|55->24|56->25|57->26|57->26|57->26|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|62->31|62->31|62->31|62->31|62->31|62->31|62->31|62->31|63->32|63->32|64->33|64->33|64->33|64->33|64->33|64->33|64->33|65->34|65->34|66->35|66->35|66->35|66->35|66->35|66->35|66->35|67->36|68->37|70->39|71->40|106->75|106->75|106->75|107->76|107->76|107->76|107->76|108->77|108->77|108->77|109->78|109->78|109->78|110->79|110->79|110->79|112->81|113->82|126->95|126->95|126->95|130->99|133->102|134->103|134->103|136->105|136->105|136->105|137->106|140->109|140->109|141->110|142->111|142->111|143->112|143->112|143->112|145->114|145->114|146->115|149->118|149->118|150->119|151->120|151->120|152->121|152->121|152->121|155->124|155->124|157->126|157->126|158->127|159->128|159->128|160->129|162->131|162->131|162->131|162->131|163->132|165->134|165->134|166->135|166->135|168->137|168->137|168->137|169->138|173->142|173->142|174->143|181->150|181->150|182->151|182->151|182->151|183->152|183->152|183->152|184->153|185->154|185->154|186->155|186->155|187->156|187->156|188->157|188->157|189->158|189->158|191->160
                  -- GENERATED --
              */
          