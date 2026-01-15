
package viewsMnuQr.html

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

object qrListaEquipos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[qr.QrEquipo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listEquipQr: List[qr.QrEquipo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "LISTADO DE EQUIPOS INSCRITOS -- CODIFICACION QR","CREAR/MODIFICAR")),format.raw/*9.100*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<th style= "text-align: center;">GRUPO</th>
							<th style= "text-align: center;">CODIGO</th>
							<th style= "text-align: center;">NOMBRE/DESCRIPCION</th>
							<th style= "text-align: center;">QR</th>
							<th style= "text-align: center;">REVISAR DATOS</th>
							<th style= "text-align: center;">Activo/Inactivo</th>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*24.8*/for(lista <- listEquipQr) yield /*24.33*/{_display_(Seq[Any](format.raw/*24.34*/("""
							"""),format.raw/*25.8*/("""<TR>
								<td style="text-align: left;">"""),_display_(/*26.40*/lista/*26.45*/.grupo),format.raw/*26.51*/("""</td>
								<td style="text-align: center;">"""),_display_(/*27.42*/lista/*27.47*/.codigo),format.raw/*27.54*/("""</td>
						        <td style="text-align:left">"""),_display_(/*28.44*/lista/*28.49*/.nombre),format.raw/*28.56*/("""</td>
						        <td style="text-align:center">
						        	<a href="#" onclick="selectModal('"""),_display_(/*30.51*/lista/*30.56*/.codigo),format.raw/*30.63*/("""','"""),_display_(/*30.67*/lista/*30.72*/.nombre),format.raw/*30.79*/("""','"""),_display_(/*30.83*/lista/*30.88*/.qr),format.raw/*30.91*/("""')">
										<img src='/viewImg/"""),_display_(/*31.31*/lista/*31.36*/.qr),format.raw/*31.39*/("""' style="max-height:50px;max-width:100%">
									</a>
								</td>
								<td style="text-align:center">
									<form id="form_"""),_display_(/*35.26*/lista/*35.31*/.id_equipo),format.raw/*35.41*/(""""method="post" action="/qrRevisarDatos/">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*36.57*/lista/*36.62*/.id_equipo),format.raw/*36.72*/("""">
										<a href="#" onclick="document.getElementById('form_"""),_display_(/*37.63*/lista/*37.68*/.id_equipo),format.raw/*37.78*/("""').submit()">
											<kbd style="background-color: #73C6B6">Revisar/Agregar Datos</kbd>
										</a>
									</form>
								</td>
						        """),_display_(if(lista.activo==1)/*42.35*/{_display_(Seq[Any](format.raw/*42.36*/("""
						        	"""),format.raw/*43.16*/("""<td style="text-align:center">
						        		<font color="green"><b>ACTIVO</b></font>&nbsp;&nbsp;
										<a href="#" onclick= "cambiarEstado('"""),_display_(/*45.49*/lista/*45.54*/.id_equipo),format.raw/*45.64*/("""','"""),_display_(/*45.68*/lista/*45.73*/.activo),format.raw/*45.80*/("""')">
											<kbd style="background-color: red">C</kbd>
										</a>
						        	</td>
						        """)))}else/*49.20*/{_display_(Seq[Any](format.raw/*49.21*/("""
						        	"""),format.raw/*50.16*/("""<td style="text-align:center">
						        		<font color="red"><b>INACTIVO</b></font>&nbsp;&nbsp;
						        		<a href="#" onclick= "cambiarEstado('"""),_display_(/*52.55*/lista/*52.60*/.id_equipo),format.raw/*52.70*/("""','"""),_display_(/*52.74*/lista/*52.79*/.activo),format.raw/*52.86*/("""')">
											<kbd style="background-color: red">C</kbd>
										</a>
						        	</td>
						        """)))}),format.raw/*56.16*/("""
							"""),format.raw/*57.8*/("""</TR>
						""")))}),format.raw/*58.8*/("""
					"""),format.raw/*59.6*/("""</tbody>
				</table>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="INSCRIBIR/AGREGAR EQUIPOS" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/qrSelectEquipos/';">
						</div>
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalQr' class="modal" role="dialog" data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
				   	<h5 id="nombreEquipo" class="modal-title">
			           EQUIPO
			        </h5>
			        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
			        	<span aria-hidden='true'>&times;</span>
			        </button>
				</div>
	     		<div class="modal-body">
		     		<div align="center">
		     			<div id="imagenQr"></div>
		     			<div id="codigoQr"></div>
		     			<div id="equipoQr"></div>
						<button type="button" class="btn btn-success btn-sm rounded-pill btn-block" data-dismiss="modal" >
							CERRAR
						</button>
					</div>
				</div>
	   		</div>
		</div>
	</div>
	
	<form id="form_cambiarEstado"method="post" action="/qrCambiaEstadoEquipos/">
		<input type="hidden" id="id_equipo" name="id_equipo" value="0">
		<input type="hidden" id="activo" name="activo" value="0">
	</form>

	
""")))}),format.raw/*106.2*/("""




"""),format.raw/*111.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*112.31*/("""{"""),format.raw/*112.32*/("""
		  """),format.raw/*113.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*113.36*/("""{"""),format.raw/*113.37*/("""
		    	"""),format.raw/*114.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[10, 50, 100, 200, -1], [10, 50, 100, 200, "All"]],
				"order": [[ 5, "asc" ], [ 0, "asc" ], [ 1, "asc" ]],
		    	"language": """),format.raw/*117.20*/("""{"""),format.raw/*117.21*/("""
		        	"""),format.raw/*118.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*119.11*/("""}"""),format.raw/*119.12*/("""
		  """),format.raw/*120.5*/("""}"""),format.raw/*120.6*/(""" """),format.raw/*120.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*122.2*/("""}"""),format.raw/*122.3*/(""");
	
	const selectModal = (codigo,equipo,qr) =>"""),format.raw/*124.43*/("""{"""),format.raw/*124.44*/("""
	  """),format.raw/*125.4*/("""var qr = "/viewImg/"+qr;
	  document.getElementById('imagenQr').innerHTML = "<label><img src='"+qr+"' style='max-height:200px;max-width:100%'/></label>";
	  document.getElementById('codigoQr').innerHTML = "<h5>"+codigo+"</h5>";
      document.getElementById('equipoQr').innerHTML = "<h5>"+equipo+"</h5>";
      $("#modalQr").modal("show");
  	"""),format.raw/*130.4*/("""}"""),format.raw/*130.5*/("""

	"""),format.raw/*132.2*/("""const cambiarEstado = (id_equipo,activo) => """),format.raw/*132.46*/("""{"""),format.raw/*132.47*/("""
		"""),format.raw/*133.3*/("""alertify.confirm("Esta seguro de cambiar el estado del equipo", function (e) """),format.raw/*133.80*/("""{"""),format.raw/*133.81*/("""
			"""),format.raw/*134.4*/("""if (e) """),format.raw/*134.11*/("""{"""),format.raw/*134.12*/("""
				"""),format.raw/*135.5*/("""$("#id_equipo").val(id_equipo);
				$("#activo").val(activo);
				document.getElementById('form_cambiarEstado').submit();
			"""),format.raw/*138.4*/("""}"""),format.raw/*138.5*/("""else"""),format.raw/*138.9*/("""{"""),format.raw/*138.10*/("""
				"""),format.raw/*139.5*/("""$("#id_equipo").val(0);
				$("#activo").val(0);
			"""),format.raw/*141.4*/("""}"""),format.raw/*141.5*/("""
		"""),format.raw/*142.3*/("""}"""),format.raw/*142.4*/(""");
  	"""),format.raw/*143.4*/("""}"""),format.raw/*143.5*/("""
"""),format.raw/*144.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listEquipQr:List[qr.QrEquipo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listEquipQr)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[qr.QrEquipo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listEquipQr) => apply(mapDiccionario,mapPermiso,userMnu,listEquipQr)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/qrListaEquipos.scala.html
                  HASH: 0c5a530565b09e5d8442627154d48d0c3290074c
                  MATRIX: 1026->1|1248->130|1281->138|1297->146|1336->148|1364->151|1432->199|1460->201|1536->252|1653->348|1683->351|2361->1003|2402->1028|2441->1029|2476->1037|2547->1081|2561->1086|2588->1092|2662->1139|2676->1144|2704->1151|2780->1200|2794->1205|2822->1212|2950->1313|2964->1318|2992->1325|3023->1329|3037->1334|3065->1341|3096->1345|3110->1350|3134->1353|3196->1388|3210->1393|3234->1396|3395->1530|3409->1535|3440->1545|3565->1643|3579->1648|3610->1658|3702->1723|3716->1728|3747->1738|3946->1910|3985->1911|4029->1927|4204->2075|4218->2080|4249->2090|4280->2094|4294->2099|4322->2106|4455->2220|4494->2221|4538->2237|4719->2391|4733->2396|4764->2406|4795->2410|4809->2415|4837->2422|4978->2532|5013->2540|5056->2553|5089->2559|6767->4206|6800->4211|6891->4273|6921->4274|6954->4279|7014->4310|7044->4311|7080->4319|7280->4490|7310->4491|7351->4503|7458->4581|7488->4582|7521->4587|7550->4588|7579->4589|7680->4662|7709->4663|7785->4710|7815->4711|7847->4715|8218->5058|8247->5059|8278->5062|8351->5106|8381->5107|8412->5110|8518->5187|8548->5188|8580->5192|8616->5199|8646->5200|8679->5205|8832->5330|8861->5331|8893->5335|8923->5336|8956->5341|9036->5393|9065->5394|9096->5397|9125->5398|9159->5404|9188->5405|9217->5406
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|55->24|55->24|55->24|56->25|57->26|57->26|57->26|58->27|58->27|58->27|59->28|59->28|59->28|61->30|61->30|61->30|61->30|61->30|61->30|61->30|61->30|61->30|62->31|62->31|62->31|66->35|66->35|66->35|67->36|67->36|67->36|68->37|68->37|68->37|73->42|73->42|74->43|76->45|76->45|76->45|76->45|76->45|76->45|80->49|80->49|81->50|83->52|83->52|83->52|83->52|83->52|83->52|87->56|88->57|89->58|90->59|137->106|142->111|143->112|143->112|144->113|144->113|144->113|145->114|148->117|148->117|149->118|150->119|150->119|151->120|151->120|151->120|153->122|153->122|155->124|155->124|156->125|161->130|161->130|163->132|163->132|163->132|164->133|164->133|164->133|165->134|165->134|165->134|166->135|169->138|169->138|169->138|169->138|170->139|172->141|172->141|173->142|173->142|174->143|174->143|175->144
                  -- GENERATED --
              */
          