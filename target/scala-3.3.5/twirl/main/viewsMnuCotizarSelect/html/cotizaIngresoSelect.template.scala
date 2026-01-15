
package viewsMnuCotizarSelect.html

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

object cotizaIngresoSelect extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Equipo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listEquipos: List[tables.Equipo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "SELECCIONAR EQUIPOS A COTIZAR", "")),format.raw/*12.68*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-8 col-sm-6 col-md-6 col-lg-6">
				<form method="post" action="/routes2/cotizaSeleccionados/" onsubmit="return validarForm();">
					<input type="hidden" id="seleccionados" name="seleccionados" value="">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH style="vertical-align: top;">GRUPO</TH>
								<TH>CODIGO</TH>
								<TH>EQUIPO</TH>
								<TH>STOCK</TH>
								<TH>SELECT</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*28.9*/for(lista1 <- listEquipos) yield /*28.35*/{_display_(Seq[Any](format.raw/*28.36*/("""
								"""),format.raw/*29.9*/("""<TR>
									<td style="text-align:left;">"""),_display_(/*30.40*/lista1/*30.46*/.getGrupo()),format.raw/*30.57*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*31.41*/lista1/*31.47*/.getCodigo()),format.raw/*31.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*32.41*/lista1/*32.47*/.getNombre()),format.raw/*32.59*/("""</td>
									<td style="text-align:center;">
										<div class="noprint">
											<a href="#" onclick="vistaStockPorEquipo('"""),_display_(/*35.55*/lista1/*35.61*/.getId()),format.raw/*35.69*/("""');" tabindex="-1">
												<kbd style="background-color: #73C6B6">stock</kbd>
											</a>
										</div>
									</td>
									<td  style="text-align:center;">
										<input type="checkbox" id=""""),_display_(/*41.39*/lista1/*41.45*/.getId()),format.raw/*41.53*/("""" value ="0" onchange="selecciona(id,value)"></td>
									</td>
								</TR>
				 			""")))}),format.raw/*44.10*/("""
						"""),format.raw/*45.7*/("""</tbody>
					</table>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
							</div>
							<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<input type="submit" id="btnSubmit" value='SELECCIONAR' class="btn btn-primary btn-sm rounded-pill btn-block">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div id="modalStockPorEquipo" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class='modal-dialog modal-dialog-scrollable' style='max-width: 80% !important;' role='document'>
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">STOCK DISPONIBLE</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				</div>
				<div class="modal-body">
					<div id="stockPorEquipo"></div>
	   				<div class="row">
						<div class="col-sm-12" style="text-align:center">
							<button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" data-dismiss="modal">Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
""")))}),format.raw/*84.2*/("""


"""),format.raw/*87.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*88.31*/("""{"""),format.raw/*88.32*/("""
		  """),format.raw/*89.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*89.36*/("""{"""),format.raw/*89.37*/("""
		    	"""),format.raw/*90.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ]],
		    	"language": """),format.raw/*93.20*/("""{"""),format.raw/*93.21*/("""
		        	"""),format.raw/*94.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*95.11*/("""}"""),format.raw/*95.12*/("""
		  """),format.raw/*96.5*/("""}"""),format.raw/*96.6*/(""" """),format.raw/*96.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*98.2*/("""}"""),format.raw/*98.3*/(""");
	
	const selecciona = (id_equipo, valor) => """),format.raw/*100.43*/("""{"""),format.raw/*100.44*/("""
		"""),format.raw/*101.3*/("""if(valor == 0) """),format.raw/*101.18*/("""{"""),format.raw/*101.19*/("""
			"""),format.raw/*102.4*/("""document.getElementById(id_equipo).value = "1";
			let aux = ""+$("#seleccionados").val()+id_equipo + ",";
			$("#seleccionados").val(aux);
		"""),format.raw/*105.3*/("""}"""),format.raw/*105.4*/("""else"""),format.raw/*105.8*/("""{"""),format.raw/*105.9*/("""
			"""),format.raw/*106.4*/("""document.getElementById(id_equipo).value = "0";
			let aux = ""+id_equipo + ",";
			$("#seleccionados").val($("#seleccionados").val().replace(aux,""));
		"""),format.raw/*109.3*/("""}"""),format.raw/*109.4*/("""
		
	"""),format.raw/*111.2*/("""}"""),format.raw/*111.3*/("""
	
	"""),format.raw/*113.2*/("""const vistaStockPorEquipo = (id_equipo) =>"""),format.raw/*113.44*/("""{"""),format.raw/*113.45*/("""
		"""),format.raw/*114.3*/("""var formData = new FormData();
		formData.append('id_equipo',id_equipo);
        $.ajax("""),format.raw/*116.16*/("""{"""),format.raw/*116.17*/("""
            """),format.raw/*117.13*/("""url: "/tablaInvPorEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*124.36*/("""{"""),format.raw/*124.37*/("""
				"""),format.raw/*125.5*/("""document.getElementById('stockPorEquipo').innerHTML = respuesta;
				$('#modalStockPorEquipo').modal('show');
	     	"""),format.raw/*127.8*/("""}"""),format.raw/*127.9*/("""
        """),format.raw/*128.9*/("""}"""),format.raw/*128.10*/(""");
	"""),format.raw/*129.2*/("""}"""),format.raw/*129.3*/("""
	
	"""),format.raw/*131.2*/("""const validarForm = () =>"""),format.raw/*131.27*/("""{"""),format.raw/*131.28*/("""
		"""),format.raw/*132.3*/("""let flag = true;
		if($("#seleccionados").val() == "")"""),format.raw/*133.38*/("""{"""),format.raw/*133.39*/("""
			"""),format.raw/*134.4*/("""flag = false;
			alertify.alert('DEBE ELEGIR AL MENOS UN EQUIPO', function () """),format.raw/*135.65*/("""{"""),format.raw/*135.66*/("""
				"""),format.raw/*136.5*/("""return(flag);
     		"""),format.raw/*137.8*/("""}"""),format.raw/*137.9*/(""");
		"""),format.raw/*138.3*/("""}"""),format.raw/*138.4*/("""
		"""),format.raw/*139.3*/("""return(flag);
	"""),format.raw/*140.2*/("""}"""),format.raw/*140.3*/("""
	
	
	
"""),format.raw/*144.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listEquipos:List[tables.Equipo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listEquipos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Equipo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listEquipos) => apply(mapDiccionario,mapPermiso,userMnu,listEquipos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizarSelect/cotizaIngresoSelect.scala.html
                  HASH: a3cf1a89d2a9f3b403758a91162b12610541c6eb
                  MATRIX: 1044->1|1268->132|1301->140|1317->148|1356->150|1385->154|1453->202|1483->207|1527->231|1558->235|1635->286|1720->350|1750->353|2416->993|2458->1019|2497->1020|2533->1029|2604->1073|2619->1079|2651->1090|2724->1136|2739->1142|2772->1154|2845->1200|2860->1206|2893->1218|3053->1351|3068->1357|3097->1365|3335->1576|3350->1582|3379->1590|3499->1679|3533->1686|4964->3087|4994->3090|5084->3152|5113->3153|5145->3158|5204->3189|5233->3190|5268->3198|5442->3344|5471->3345|5511->3357|5617->3435|5646->3436|5678->3441|5706->3442|5734->3443|5834->3516|5862->3517|5938->3564|5968->3565|5999->3568|6043->3583|6073->3584|6105->3588|6275->3730|6304->3731|6336->3735|6365->3736|6397->3740|6579->3894|6608->3895|6641->3900|6670->3901|6702->3905|6773->3947|6803->3948|6834->3951|6951->4039|6981->4040|7023->4053|7286->4287|7316->4288|7349->4293|7494->4410|7523->4411|7560->4420|7590->4421|7622->4425|7651->4426|7683->4430|7737->4455|7767->4456|7798->4459|7881->4513|7911->4514|7943->4518|8050->4596|8080->4597|8113->4602|8162->4623|8191->4624|8224->4629|8253->4630|8284->4633|8327->4648|8356->4649|8391->4656
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|44->13|59->28|59->28|59->28|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|66->35|66->35|66->35|72->41|72->41|72->41|75->44|76->45|115->84|118->87|119->88|119->88|120->89|120->89|120->89|121->90|124->93|124->93|125->94|126->95|126->95|127->96|127->96|127->96|129->98|129->98|131->100|131->100|132->101|132->101|132->101|133->102|136->105|136->105|136->105|136->105|137->106|140->109|140->109|142->111|142->111|144->113|144->113|144->113|145->114|147->116|147->116|148->117|155->124|155->124|156->125|158->127|158->127|159->128|159->128|160->129|160->129|162->131|162->131|162->131|163->132|164->133|164->133|165->134|166->135|166->135|167->136|168->137|168->137|169->138|169->138|170->139|171->140|171->140|175->144
                  -- GENERATED --
              */
          