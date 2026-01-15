
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

object bodegaFijaDctos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.DctoGrupo],List[tables.DctoEquipo],List[tables.Grupo],List[tables.Equipo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listDctoGrupo: List[tables.DctoGrupo], listDctoEquipo: List[tables.DctoEquipo],
listGrupos: List[tables.Grupo], listEquipos: List[tables.Equipo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""

"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

"""),_display_(/*8.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.50*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "ASIGNAR TASAS DE DESCUENTOS SOBRE PRECIOS DE "+mapDiccionario.get("ARRIENDO"), "("+bodega.getNombre().toUpperCase()+")")),format.raw/*10.153*/("""
		"""),format.raw/*11.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td width="50%">
							<b><font color="#008000">DCTO GLOBAL</font></b>
							<table class="table table-sm table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<TR>
										<TH>"""),_display_(/*20.16*/mapDiccionario/*20.30*/.get("BODEGA")),format.raw/*20.44*/("""/PROYECTO</TH>
										<TH>CLIENTE</TH>
								        <TH>PROYECTO</TH>
										<TH>DCTO """),_display_(/*23.21*/mapDiccionario/*23.35*/.get("ARRIENDO")),format.raw/*23.51*/("""</TH>
									</TR>
								</thead>
								<tbody>
									<TR>
										<td style="text-align:left;">"""),_display_(/*28.41*/bodega/*28.47*/.getNombre()),format.raw/*28.59*/("""</td>
										<td style="text-align:left;">"""),_display_(/*29.41*/bodega/*29.47*/.getNickCliente()),format.raw/*29.64*/("""</td>
										<td style="text-align:left;">"""),_display_(/*30.41*/bodega/*30.47*/.getNickProyecto()),format.raw/*30.65*/("""</td>
										<td style="text-align:center;">
											<input type="text" class="form-control center"
												id="dctoGlobal"
												onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
												onblur = "value = formatPorcentaje(value);"
												onkeydown="return ingresoDouble(window.event)"
												onchange="modificarDctoGlobal(value)">
										</td>
									</TR>
								</tbody>
							</table>
						</td>
						<td style="text-align:center;"  width="50%">
							<h5><b><font color="red"> IMPORTANTE, LOS DECUENTOS APLICAN SOBRE EL PRECIO DE ARRIENDO DETERMINADO (MODIFICA EL PRECIO CADA VEZ QUE SE GENERA UN REPORTE)</font></b></h5>
						</td>
					</tr>
					<tr>
						<td style="vertical-align:top">
							<b><font color="#008000">DCTOS POR GRUPO</font></b>
							<table id="tblDctoGrupo" class="table table-sm table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<TR>
										<TH>GRUPO</TH>
										<TH>DCTO """),_display_(/*54.21*/mapDiccionario/*54.35*/.get("ARRIENDO")),format.raw/*54.51*/("""</TH>
								        <TH>DEL</TH>
									</TR>
								</thead>
								<tbody>
									"""),_display_(/*59.11*/for(lista <- listDctoGrupo) yield /*59.38*/{_display_(Seq[Any](format.raw/*59.39*/("""
										"""),format.raw/*60.11*/("""<tr>
											<td style="text-align:left;">"""),_display_(/*61.42*/lista/*61.47*/.getNombreGrupo()),format.raw/*61.64*/("""</td>
											<td style="text-align:center;">
												<input type="text" class="form-control center"
													id="dctoGrupo_"""),_display_(/*64.29*/lista/*64.34*/.getId_grupo()),format.raw/*64.48*/(""""
													onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
													onblur = "value = formatPorcentaje(value);"
													onkeydown="return ingresoDouble(window.event)"
													onchange="modificarDctoGrupo('"""),_display_(/*68.45*/lista/*68.50*/.getId_grupo()),format.raw/*68.64*/("""', value)">
											</td>
											<td  style="text-align:center;">
												<a href="#" onclick= "eliminarDctoGrupo(this, '"""),_display_(/*71.61*/lista/*71.66*/.getId_grupo()),format.raw/*71.80*/("""')">
													<kbd style="background-color: red">X</kbd>
												</a>
											</td>
										</tr>
									""")))}),format.raw/*76.11*/("""
									"""),format.raw/*77.10*/("""<tr>
										<td colspan="3" style="text-align:center;">
											<input type="button"  value="agregar grupo" class="btn btn-light btn-sm rounded-pill btn-block" onclick="$('#listaGrupos').modal('show');">
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td style="vertical-align:top">
							<b><font color="#008000">DCTOS POR EQUIPO</font></b>
							<table id="tblDctoEquipo" class="table table-sm table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<TR>
										<TH>CODIGO</TH>
										<TH>EQUIPO</TH>
										<TH>DCTO</TH>
								        <TH>DEL</TH>
									</TR>
								</thead>
								<tbody>
									"""),_display_(/*97.11*/for(lista <- listDctoEquipo) yield /*97.39*/{_display_(Seq[Any](format.raw/*97.40*/("""
										"""),format.raw/*98.11*/("""<tr>
											<td style="text-align:left;">"""),_display_(/*99.42*/lista/*99.47*/.getCodigoEquipo()),format.raw/*99.65*/("""</td>
											<td style="text-align:left;">"""),_display_(/*100.42*/lista/*100.47*/.getNombreEquipo()),format.raw/*100.65*/("""</td>
											<td style="text-align:center;">
												<input type="text" class="form-control center"
													id="dctoEquipo_"""),_display_(/*103.30*/lista/*103.35*/.getId_equipo()),format.raw/*103.50*/(""""
													onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
													onblur = "value = formatPorcentaje(value);"
													onkeydown="return ingresoDouble(window.event)"
													onchange="modificarDctoEquipo('"""),_display_(/*107.46*/lista/*107.51*/.getId_equipo()),format.raw/*107.66*/("""','"""),_display_(/*107.70*/lista/*107.75*/.getId_cotizacion()),format.raw/*107.94*/("""', value)">
											</td>
											<td  style="text-align:center;">
												<a href="#" onclick= "eliminarDctoEquipo(this, '"""),_display_(/*110.62*/lista/*110.67*/.getId_equipo()),format.raw/*110.82*/("""','"""),_display_(/*110.86*/lista/*110.91*/.getId_cotizacion()),format.raw/*110.110*/("""')">
													<kbd style="background-color: red">X</kbd>
												</a>
											</td>
										</tr>
									""")))}),format.raw/*115.11*/("""
									"""),format.raw/*116.10*/("""<tr>
										<td colspan="4" style="text-align:center;">
											<input type="button"  value="agregar equipo" class="btn btn-light btn-sm rounded-pill btn-block" onclick="$('#listaEquipo').modal('show');">
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
				
				"""),_display_(if(mapDiccionario("nEmpresa").equals("BI"))/*127.49*/{_display_(Seq[Any](format.raw/*127.50*/("""
					"""),format.raw/*128.6*/("""<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align:center;">
								<b><font color="#008000">130.1 EQUIPOS ANTI CAIDAS ST. BI</font></b>
							</td>
							<td style="text-align:center;">
								<b><font color="#008000">114.2 IF BASE ST. BI</font></b>
							</td>
						</tr>
						<tr>
							<td>
								<table class="table table-sm table-bordered table-condensed table-fluid">
									<tr style="background-color: #eeeeee">
										<th colspan="5">Meses</th>
									</tr>
									<tr style="background-color: #eeeeee">
										<th>0 a 3</th>
										<th>4 a 5</th>
										<th>6 a 7</th>
										<th>8 a 9</th>
										<th>10 o +</th>
									</tr>
									<tr>
										<td>11.67 %</td>
										<td>9.00 %</td>
										<td>8.33 %</td>
										<td>6.88 %</td>
										<td>7.00 %</td>
									</tr>
								</table>		
							</td>
							<td>
								<table class="table table-sm table-bordered table-condensed table-fluid">
									<tr style="background-color: #eeeeee">
										<th colspan="5">Meses</th>
									</tr>
									<tr style="background-color: #eeeeee">
										<th>0 a 6</th>
										<th>7 a 9</th>
										<th>10 a 12</th>
										<th>13 a 15</th>
										<th>15 o +</th>
									</tr>
									<tr>
										<td>5.83 %</td>
										<td>5.00 %</td>
										<td>4.58 %</td>
										<td>4.33 %</td>
										<td>4.17 %</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				""")))} else {null} ),format.raw/*182.6*/("""
				
			"""),format.raw/*184.4*/("""</div>
		</div>
				
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div id='listaGrupos' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR GRUPO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<table id="tablaListaGrupos" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>GRUPO</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*215.9*/for(lista1 <- listGrupos) yield /*215.34*/{_display_(Seq[Any](format.raw/*215.35*/("""
								"""),format.raw/*216.9*/("""<TR onClick="seleccionaGrupo("""),_display_(/*216.39*/lista1/*216.45*/.getId()),format.raw/*216.53*/(""")" data-dismiss="modal">
									<td style="text-align:left;"><div id="selectGrupo_"""),_display_(/*217.61*/lista1/*217.67*/.getId()),format.raw/*217.75*/("""">"""),_display_(/*217.78*/lista1/*217.84*/.getNombre()),format.raw/*217.96*/("""</div></td>
								</TR>
				 			""")))}),format.raw/*219.10*/("""
						"""),format.raw/*220.7*/("""</tbody>
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
	
	<div id='listaEquipo' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR EQUIPO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<table id="tablaListaEquipos" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>CODIGO</TH>
								<TH>EQUIPO</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*250.9*/for(lista1 <- listEquipos) yield /*250.35*/{_display_(Seq[Any](format.raw/*250.36*/("""
								"""),format.raw/*251.9*/("""<TR onClick="seleccionaEquipo("""),_display_(/*251.40*/lista1/*251.46*/.getId()),format.raw/*251.54*/(""")" data-dismiss="modal">
									<td style="text-align:left;"><div id="selectEquipoCod_"""),_display_(/*252.65*/lista1/*252.71*/.getId()),format.raw/*252.79*/("""">"""),_display_(/*252.82*/lista1/*252.88*/.getCodigo()),format.raw/*252.100*/("""</div></td>
									<td style="text-align:left;"><div id="selectEquipoNom_"""),_display_(/*253.65*/lista1/*253.71*/.getId()),format.raw/*253.79*/("""">"""),_display_(/*253.82*/lista1/*253.88*/.getNombre()),format.raw/*253.100*/("""</div></td>
								</TR>
				 			""")))}),format.raw/*255.10*/("""
						"""),format.raw/*256.7*/("""</tbody>
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
	
""")))}),format.raw/*268.2*/("""




"""),format.raw/*273.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*274.31*/("""{"""),format.raw/*274.32*/("""
		"""),format.raw/*275.3*/("""$("#dctoGlobal").val(formatPorcentaje(""""),_display_(/*275.43*/(bodega.getTasaDescto()*100)),format.raw/*275.71*/(""""));
		"""),_display_(/*276.4*/for(lista <- listDctoGrupo) yield /*276.31*/{_display_(Seq[Any](format.raw/*276.32*/("""
			"""),format.raw/*277.4*/("""$("#dctoGrupo_"""),_display_(/*277.19*/lista/*277.24*/.getId_grupo()),format.raw/*277.38*/("""").val(formatPorcentaje(""""),_display_(/*277.64*/(lista.getTasaDescto()*100)),format.raw/*277.91*/(""""));
		""")))}),format.raw/*278.4*/("""
		"""),_display_(/*279.4*/for(lista <- listDctoEquipo) yield /*279.32*/{_display_(Seq[Any](format.raw/*279.33*/("""
			"""),format.raw/*280.4*/("""$("#dctoEquipo_"""),_display_(/*280.20*/lista/*280.25*/.getId_equipo()),format.raw/*280.40*/("""").val(formatPorcentaje(""""),_display_(/*280.66*/(lista.getTasaDescto()*100)),format.raw/*280.93*/(""""));
		""")))}),format.raw/*281.4*/("""
		
		"""),format.raw/*283.3*/("""$('#tablaListaGrupos').DataTable("""),format.raw/*283.36*/("""{"""),format.raw/*283.37*/("""
	    	"""),format.raw/*284.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"language": """),format.raw/*286.19*/("""{"""),format.raw/*286.20*/("""
	        	"""),format.raw/*287.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*288.10*/("""}"""),format.raw/*288.11*/("""
	 	"""),format.raw/*289.4*/("""}"""),format.raw/*289.5*/(""" """),format.raw/*289.6*/(""");
		$('#tablaListaEquipos').DataTable("""),format.raw/*290.37*/("""{"""),format.raw/*290.38*/("""
	    	"""),format.raw/*291.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"language": """),format.raw/*293.19*/("""{"""),format.raw/*293.20*/("""
	        	"""),format.raw/*294.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*295.10*/("""}"""),format.raw/*295.11*/("""
	 	 """),format.raw/*296.5*/("""}"""),format.raw/*296.6*/(""" """),format.raw/*296.7*/(""");
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*298.2*/("""}"""),format.raw/*298.3*/(""");
	
	const modificarDctoGlobal = (dcto) => """),format.raw/*300.40*/("""{"""),format.raw/*300.41*/("""
		"""),format.raw/*301.3*/("""var formData = new FormData();
		formData.append('modifica','dctoGlobal');
		formData.append('id_bodegaEmpresa',"""),_display_(/*303.39*/bodega/*303.45*/.getId()),format.raw/*303.53*/(""");
	  	formData.append('dcto',dcto);
        $.ajax("""),format.raw/*305.16*/("""{"""),format.raw/*305.17*/("""
            """),format.raw/*306.13*/("""url: "/modificarDctoArriendoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*313.36*/("""{"""),format.raw/*313.37*/("""
	     		"""),format.raw/*314.9*/("""if(respuesta=="error")"""),format.raw/*314.31*/("""{"""),format.raw/*314.32*/("""
	     			"""),format.raw/*315.10*/("""alertify.alert(msgError, function () """),format.raw/*315.47*/("""{"""),format.raw/*315.48*/("""
		     			"""),format.raw/*316.11*/("""location.href = "/";
		     		"""),format.raw/*317.10*/("""}"""),format.raw/*317.11*/(""");
	     		"""),format.raw/*318.9*/("""}"""),format.raw/*318.10*/("""
	     	"""),format.raw/*319.8*/("""}"""),format.raw/*319.9*/("""
        """),format.raw/*320.9*/("""}"""),format.raw/*320.10*/(""");
	"""),format.raw/*321.2*/("""}"""),format.raw/*321.3*/("""
	
	"""),format.raw/*323.2*/("""const modificarDctoGrupo = (id_grupo, dcto) => """),format.raw/*323.49*/("""{"""),format.raw/*323.50*/("""
		"""),format.raw/*324.3*/("""var formData = new FormData();
		formData.append('modifica','dctoGrupo');
		formData.append('id_bodegaEmpresa',"""),_display_(/*326.39*/bodega/*326.45*/.getId()),format.raw/*326.53*/(""");
		formData.append('id_grupo',id_grupo);
	  	formData.append('dcto',dcto);
        $.ajax("""),format.raw/*329.16*/("""{"""),format.raw/*329.17*/("""
            """),format.raw/*330.13*/("""url: "/modificarDctoArriendoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*337.36*/("""{"""),format.raw/*337.37*/("""
	     		"""),format.raw/*338.9*/("""if(respuesta=="error")"""),format.raw/*338.31*/("""{"""),format.raw/*338.32*/("""
	     			"""),format.raw/*339.10*/("""alertify.alert(msgError, function () """),format.raw/*339.47*/("""{"""),format.raw/*339.48*/("""
		     			"""),format.raw/*340.11*/("""location.href = "/";
		     		"""),format.raw/*341.10*/("""}"""),format.raw/*341.11*/(""");
	     		"""),format.raw/*342.9*/("""}"""),format.raw/*342.10*/("""
	     	"""),format.raw/*343.8*/("""}"""),format.raw/*343.9*/("""
        """),format.raw/*344.9*/("""}"""),format.raw/*344.10*/(""");
	"""),format.raw/*345.2*/("""}"""),format.raw/*345.3*/("""
	
	"""),format.raw/*347.2*/("""const modificarDctoEquipo = (id_equipo, id_cotizacion, dcto) => """),format.raw/*347.66*/("""{"""),format.raw/*347.67*/("""
		"""),format.raw/*348.3*/("""var formData = new FormData();
		formData.append('modifica','dctoEquipo');
		formData.append('id_bodegaEmpresa',"""),_display_(/*350.39*/bodega/*350.45*/.getId()),format.raw/*350.53*/(""");
		formData.append('id_equipo',id_equipo);
		formData.append('id_cotizacion',id_cotizacion);
	  	formData.append('dcto',dcto);
        $.ajax("""),format.raw/*354.16*/("""{"""),format.raw/*354.17*/("""
            """),format.raw/*355.13*/("""url: "/modificarDctoArriendoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*362.36*/("""{"""),format.raw/*362.37*/("""
	     		"""),format.raw/*363.9*/("""if(respuesta=="error")"""),format.raw/*363.31*/("""{"""),format.raw/*363.32*/("""
	     			"""),format.raw/*364.10*/("""alertify.alert(msgError, function () """),format.raw/*364.47*/("""{"""),format.raw/*364.48*/("""
		     			"""),format.raw/*365.11*/("""location.href = "/";
		     		"""),format.raw/*366.10*/("""}"""),format.raw/*366.11*/(""");
	     		"""),format.raw/*367.9*/("""}"""),format.raw/*367.10*/("""
	     	"""),format.raw/*368.8*/("""}"""),format.raw/*368.9*/("""
        """),format.raw/*369.9*/("""}"""),format.raw/*369.10*/(""");
	"""),format.raw/*370.2*/("""}"""),format.raw/*370.3*/("""
	
	"""),format.raw/*372.2*/("""const eliminarDctoGrupo = (nodo, id_grupo) => """),format.raw/*372.48*/("""{"""),format.raw/*372.49*/("""
		"""),format.raw/*373.3*/("""alertify.confirm("Esta seguro de eliminar descuento de grupo", function (e) """),format.raw/*373.79*/("""{"""),format.raw/*373.80*/("""
			"""),format.raw/*374.4*/("""if (e) """),format.raw/*374.11*/("""{"""),format.raw/*374.12*/("""
				"""),format.raw/*375.5*/("""var formData = new FormData();
				formData.append('elimina','dctoGrupo');
				formData.append('id_bodegaEmpresa',"""),_display_(/*377.41*/bodega/*377.47*/.getId()),format.raw/*377.55*/(""");
				formData.append('id_grupo',id_grupo);
		        $.ajax("""),format.raw/*379.18*/("""{"""),format.raw/*379.19*/("""
		            """),format.raw/*380.15*/("""url: "/eliminarDctoArriendoAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(respuesta)"""),format.raw/*387.38*/("""{"""),format.raw/*387.39*/("""
			     		"""),format.raw/*388.11*/("""if(respuesta=="error")"""),format.raw/*388.33*/("""{"""),format.raw/*388.34*/("""
			     			"""),format.raw/*389.12*/("""alertify.alert(msgError, function () """),format.raw/*389.49*/("""{"""),format.raw/*389.50*/("""
				     			"""),format.raw/*390.13*/("""location.href = "/";
				     		"""),format.raw/*391.12*/("""}"""),format.raw/*391.13*/(""");
			     		"""),format.raw/*392.11*/("""}"""),format.raw/*392.12*/("""else"""),format.raw/*392.16*/("""{"""),format.raw/*392.17*/("""
			     			"""),format.raw/*393.12*/("""nodo.parentNode.parentNode.remove();
			     		"""),format.raw/*394.11*/("""}"""),format.raw/*394.12*/("""
			     	"""),format.raw/*395.10*/("""}"""),format.raw/*395.11*/("""
		        """),format.raw/*396.11*/("""}"""),format.raw/*396.12*/(""");
			"""),format.raw/*397.4*/("""}"""),format.raw/*397.5*/("""
		"""),format.raw/*398.3*/("""}"""),format.raw/*398.4*/(""");
		
	"""),format.raw/*400.2*/("""}"""),format.raw/*400.3*/("""
	
	"""),format.raw/*402.2*/("""const eliminarDctoEquipo = (nodo, id_equipo, id_cotizacion) => """),format.raw/*402.65*/("""{"""),format.raw/*402.66*/("""
		"""),format.raw/*403.3*/("""alertify.confirm("Esta seguro de eliminar descuento de equipo", function (e) """),format.raw/*403.80*/("""{"""),format.raw/*403.81*/("""
			"""),format.raw/*404.4*/("""if (e) """),format.raw/*404.11*/("""{"""),format.raw/*404.12*/("""
				"""),format.raw/*405.5*/("""var formData = new FormData();
				formData.append('elimina','dctoEquipo');
				formData.append('id_bodegaEmpresa',"""),_display_(/*407.41*/bodega/*407.47*/.getId()),format.raw/*407.55*/(""");
				formData.append('id_equipo',id_equipo);
				formData.append('id_cotizacion',id_cotizacion);
		        $.ajax("""),format.raw/*410.18*/("""{"""),format.raw/*410.19*/("""
		            """),format.raw/*411.15*/("""url: "/eliminarDctoArriendoAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(respuesta)"""),format.raw/*418.38*/("""{"""),format.raw/*418.39*/("""
			     		"""),format.raw/*419.11*/("""if(respuesta=="error")"""),format.raw/*419.33*/("""{"""),format.raw/*419.34*/("""
			     			"""),format.raw/*420.12*/("""alertify.alert(msgError, function () """),format.raw/*420.49*/("""{"""),format.raw/*420.50*/("""
				     			"""),format.raw/*421.13*/("""location.href = "/";
				     		"""),format.raw/*422.12*/("""}"""),format.raw/*422.13*/(""");
			     		"""),format.raw/*423.11*/("""}"""),format.raw/*423.12*/("""else"""),format.raw/*423.16*/("""{"""),format.raw/*423.17*/("""
			     			"""),format.raw/*424.12*/("""nodo.parentNode.parentNode.remove();
			     		"""),format.raw/*425.11*/("""}"""),format.raw/*425.12*/("""
			     	"""),format.raw/*426.10*/("""}"""),format.raw/*426.11*/("""
		        """),format.raw/*427.11*/("""}"""),format.raw/*427.12*/(""");
			"""),format.raw/*428.4*/("""}"""),format.raw/*428.5*/("""
		"""),format.raw/*429.3*/("""}"""),format.raw/*429.4*/(""");
		
	"""),format.raw/*431.2*/("""}"""),format.raw/*431.3*/("""
	
	"""),format.raw/*433.2*/("""const seleccionaGrupo = (id_grupo) => """),format.raw/*433.40*/("""{"""),format.raw/*433.41*/("""
		"""),format.raw/*434.3*/("""let grupo = $("#selectGrupo_"+id_grupo).text();
		let tblDctoGrupo = document.getElementById("tblDctoGrupo");
		let flag = true;
		for(let i=1; i<tblDctoGrupo.rows.length; i++)"""),format.raw/*437.48*/("""{"""),format.raw/*437.49*/("""
			"""),format.raw/*438.4*/("""let valor1 = tblDctoGrupo.rows[i].cells[0].textContent;
			if(valor1==grupo)"""),format.raw/*439.21*/("""{"""),format.raw/*439.22*/("""
				"""),format.raw/*440.5*/("""flag=false;
			"""),format.raw/*441.4*/("""}"""),format.raw/*441.5*/("""
		"""),format.raw/*442.3*/("""}"""),format.raw/*442.4*/("""
		"""),format.raw/*443.3*/("""if(flag)"""),format.raw/*443.11*/("""{"""),format.raw/*443.12*/("""
			"""),format.raw/*444.4*/("""let tr, td, tabla;
			tabla = document.getElementById("tblDctoGrupo");
			tr = tabla.insertRow(tabla.rows.length-1);
			td = tr.insertCell(tr.cells.length);
			td.setAttribute ('style', 'text-align:left' );
		  	td.innerHTML = grupo;
		  	td = tr.insertCell(tr.cells.length);
		  	td.setAttribute ('style', 'text-align:center' );
		  	td.innerHTML = 	" <input type='text' class='form-control center'" +
		  		" value = '0.00 %'"+
		  		" id='dctoGrupo_"+id_grupo+"'"+
				" onfocus=\"value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\""+ 
				" onblur = 'value = formatPorcentaje(value);'"+
				" onkeydown='return ingresoDouble(window.event)'"+
				" onchange='modificarDctoGrupo("+id_grupo+", value)'>";
			td = tr.insertCell(tr.cells.length);
			td.setAttribute ('style', 'text-align:center' );
		  	td.innerHTML = 	" <a href='#' onclick= 'eliminarDctoGrupo(this, "+id_grupo+")'>"+
				" <kbd style='background-color: red'>X</kbd></a>";
		  	var formData = new FormData();
			formData.append('graba','dctoGrupo');
			formData.append('id_bodegaEmpresa',"""),_display_(/*465.40*/bodega/*465.46*/.getId()),format.raw/*465.54*/(""");
			formData.append('id_grupo',id_grupo);
	        $.ajax("""),format.raw/*467.17*/("""{"""),format.raw/*467.18*/("""
	            """),format.raw/*468.14*/("""url: "/grabaDctoArriendoAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*475.37*/("""{"""),format.raw/*475.38*/("""
		     		"""),format.raw/*476.10*/("""if(respuesta=="error")"""),format.raw/*476.32*/("""{"""),format.raw/*476.33*/("""
		     			"""),format.raw/*477.11*/("""alertify.alert(msgError, function () """),format.raw/*477.48*/("""{"""),format.raw/*477.49*/("""
			     			"""),format.raw/*478.12*/("""location.href = "/";
			     		"""),format.raw/*479.11*/("""}"""),format.raw/*479.12*/(""");
		     		"""),format.raw/*480.10*/("""}"""),format.raw/*480.11*/("""
		     	"""),format.raw/*481.9*/("""}"""),format.raw/*481.10*/("""
	        """),format.raw/*482.10*/("""}"""),format.raw/*482.11*/(""");
		"""),format.raw/*483.3*/("""}"""),format.raw/*483.4*/("""else"""),format.raw/*483.8*/("""{"""),format.raw/*483.9*/("""
			"""),format.raw/*484.4*/("""alertify.alert("El grupo ya esta incorporado");
		"""),format.raw/*485.3*/("""}"""),format.raw/*485.4*/("""
	"""),format.raw/*486.2*/("""}"""),format.raw/*486.3*/("""
	
	"""),format.raw/*488.2*/("""const seleccionaEquipo = (id_equipo) => """),format.raw/*488.42*/("""{"""),format.raw/*488.43*/("""
		"""),format.raw/*489.3*/("""let cod_equipo = $("#selectEquipoCod_"+id_equipo).text();
		let nom_equipo = $("#selectEquipoNom_"+id_equipo).text();
		let tblDctoEquipo = document.getElementById("tblDctoEquipo");
		let flag = true;
		for(let i=1; i<tblDctoEquipo.rows.length; i++)"""),format.raw/*493.49*/("""{"""),format.raw/*493.50*/("""
			"""),format.raw/*494.4*/("""let valor1 = tblDctoEquipo.rows[i].cells[0].textContent;
			if(valor1==cod_equipo)"""),format.raw/*495.26*/("""{"""),format.raw/*495.27*/("""
				"""),format.raw/*496.5*/("""flag=false;
			"""),format.raw/*497.4*/("""}"""),format.raw/*497.5*/("""
		"""),format.raw/*498.3*/("""}"""),format.raw/*498.4*/("""
		"""),format.raw/*499.3*/("""if(flag)"""),format.raw/*499.11*/("""{"""),format.raw/*499.12*/("""
			"""),format.raw/*500.4*/("""let tr, td, tabla;
			tabla = document.getElementById("tblDctoEquipo");
			
			tr = tabla.insertRow(tabla.rows.length-1);
			
			td = tr.insertCell(tr.cells.length);
			td.setAttribute ('style', 'text-align:left' );
		  	td.innerHTML = cod_equipo;
		  	
		  	td = tr.insertCell(tr.cells.length);
			td.setAttribute ('style', 'text-align:left' );
		  	td.innerHTML = nom_equipo;
		  	
		  	td = tr.insertCell(tr.cells.length);
		  	td.setAttribute ('style', 'text-align:center' );
		  	td.innerHTML = 	" <input type='text' class='form-control center'" +
		  		" value = '0.00 %'"+
		  		" id='dctoEquipo_"+id_equipo+"'"+
				" onfocus=\"value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\""+ 
				" onblur = 'value = formatPorcentaje(value);'"+
				" onkeydown='return ingresoDouble(window.event)'"+
				" onchange='modificarDctoEquipo("+id_equipo+",0, value)'>";
				
			td = tr.insertCell(tr.cells.length);
			td.setAttribute ('style', 'text-align:center' );
		  	td.innerHTML = 	" <a href='#' onclick= 'eliminarDctoEquipo(this, "+id_equipo+",0)'>"+
				" <kbd style='background-color: red'>X</kbd></a>";
		  	var formData = new FormData();
			formData.append('graba','dctoEquipo');
			formData.append('id_bodegaEmpresa',"""),_display_(/*529.40*/bodega/*529.46*/.getId()),format.raw/*529.54*/(""");
			formData.append('id_equipo',id_equipo);
			formData.append('id_cotizacion','0');
	        $.ajax("""),format.raw/*532.17*/("""{"""),format.raw/*532.18*/("""
	            """),format.raw/*533.14*/("""url: "/grabaDctoArriendoAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*540.37*/("""{"""),format.raw/*540.38*/("""
		     		"""),format.raw/*541.10*/("""if(respuesta=="error")"""),format.raw/*541.32*/("""{"""),format.raw/*541.33*/("""
		     			"""),format.raw/*542.11*/("""alertify.alert(msgError, function () """),format.raw/*542.48*/("""{"""),format.raw/*542.49*/("""
			     			"""),format.raw/*543.12*/("""location.href = "/";
			     		"""),format.raw/*544.11*/("""}"""),format.raw/*544.12*/(""");
		     		"""),format.raw/*545.10*/("""}"""),format.raw/*545.11*/("""
		     	"""),format.raw/*546.9*/("""}"""),format.raw/*546.10*/("""
	        """),format.raw/*547.10*/("""}"""),format.raw/*547.11*/(""");
		"""),format.raw/*548.3*/("""}"""),format.raw/*548.4*/("""else"""),format.raw/*548.8*/("""{"""),format.raw/*548.9*/("""
			"""),format.raw/*549.4*/("""alertify.alert("El equipo ya esta incorporado");
		"""),format.raw/*550.3*/("""}"""),format.raw/*550.4*/("""
	"""),format.raw/*551.2*/("""}"""),format.raw/*551.3*/("""
	
	
	
	
	
"""),format.raw/*557.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listDctoGrupo:List[tables.DctoGrupo],listDctoEquipo:List[tables.DctoEquipo],listGrupos:List[tables.Grupo],listEquipos:List[tables.Equipo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listDctoGrupo,listDctoEquipo,listGrupos,listEquipos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.DctoGrupo],List[tables.DctoEquipo],List[tables.Grupo],List[tables.Equipo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listDctoGrupo,listDctoEquipo,listGrupos,listEquipos) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listDctoGrupo,listDctoEquipo,listGrupos,listEquipos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/bodegaFijaDctos.scala.html
                  HASH: 176725a533de9ba20cba504708a1442edf49e529
                  MATRIX: 1121->1|1487->274|1515->277|1531->285|1570->287|1598->290|1666->338|1694->340|1771->391|1942->540|1972->543|2427->971|2450->985|2485->999|2608->1095|2631->1109|2668->1125|2803->1233|2818->1239|2851->1251|2924->1297|2939->1303|2977->1320|3050->1366|3065->1372|3104->1390|4160->2419|4183->2433|4220->2449|4340->2542|4383->2569|4422->2570|4461->2581|4534->2627|4548->2632|4586->2649|4749->2785|4763->2790|4798->2804|5075->3054|5089->3059|5124->3073|5284->3206|5298->3211|5333->3225|5485->3346|5523->3356|6261->4067|6305->4095|6344->4096|6383->4107|6456->4153|6470->4158|6509->4176|6584->4223|6599->4228|6639->4246|6804->4383|6819->4388|6856->4403|7135->4654|7150->4659|7187->4674|7219->4678|7234->4683|7275->4702|7437->4836|7452->4841|7489->4856|7521->4860|7536->4865|7578->4884|7731->5005|7770->5015|8162->5379|8202->5380|8236->5386|9816->6922|9853->6931|10942->7993|10984->8018|11024->8019|11061->8028|11119->8058|11135->8064|11165->8072|11278->8157|11294->8163|11324->8171|11355->8174|11371->8180|11405->8192|11472->8227|11507->8234|12597->9297|12640->9323|12680->9324|12717->9333|12776->9364|12792->9370|12822->9378|12939->9467|12955->9473|12985->9481|13016->9484|13032->9490|13067->9502|13171->9578|13187->9584|13217->9592|13248->9595|13264->9601|13299->9613|13366->9648|13401->9655|13728->9951|13761->9956|13852->10018|13882->10019|13913->10022|13981->10062|14031->10090|14066->10098|14110->10125|14150->10126|14182->10130|14225->10145|14240->10150|14276->10164|14330->10190|14379->10217|14418->10225|14449->10229|14494->10257|14534->10258|14566->10262|14610->10278|14625->10283|14662->10298|14716->10324|14765->10351|14804->10359|14838->10365|14900->10398|14930->10399|14965->10406|15106->10518|15136->10519|15176->10530|15282->10607|15312->10608|15344->10612|15373->10613|15402->10614|15470->10653|15500->10654|15535->10661|15676->10773|15706->10774|15746->10785|15852->10862|15882->10863|15915->10868|15944->10869|15973->10870|16072->10941|16101->10942|16174->10986|16204->10987|16235->10990|16376->11103|16392->11109|16422->11117|16503->11169|16533->11170|16575->11183|16842->11421|16872->11422|16909->11431|16960->11453|16990->11454|17029->11464|17095->11501|17125->11502|17165->11513|17224->11543|17254->11544|17293->11555|17323->11556|17359->11564|17388->11565|17425->11574|17455->11575|17487->11579|17516->11580|17548->11584|17624->11631|17654->11632|17685->11635|17825->11747|17841->11753|17871->11761|17992->11853|18022->11854|18064->11867|18331->12105|18361->12106|18398->12115|18449->12137|18479->12138|18518->12148|18584->12185|18614->12186|18654->12197|18713->12227|18743->12228|18782->12239|18812->12240|18848->12248|18877->12249|18914->12258|18944->12259|18976->12263|19005->12264|19037->12268|19130->12332|19160->12333|19191->12336|19332->12449|19348->12455|19378->12463|19551->12607|19581->12608|19623->12621|19890->12859|19920->12860|19957->12869|20008->12891|20038->12892|20077->12902|20143->12939|20173->12940|20213->12951|20272->12981|20302->12982|20341->12993|20371->12994|20407->13002|20436->13003|20473->13012|20503->13013|20535->13017|20564->13018|20596->13022|20671->13068|20701->13069|20732->13072|20837->13148|20867->13149|20899->13153|20935->13160|20965->13161|20998->13166|21141->13281|21157->13287|21187->13295|21278->13357|21308->13358|21352->13373|21632->13624|21662->13625|21702->13636|21753->13658|21783->13659|21824->13671|21890->13708|21920->13709|21962->13722|22023->13754|22053->13755|22095->13768|22125->13769|22158->13773|22188->13774|22229->13786|22305->13833|22335->13834|22374->13844|22404->13845|22444->13856|22474->13857|22508->13863|22537->13864|22568->13867|22597->13868|22632->13875|22661->13876|22693->13880|22785->13943|22815->13944|22846->13947|22952->14024|22982->14025|23014->14029|23050->14036|23080->14037|23113->14042|23257->14158|23273->14164|23303->14172|23448->14288|23478->14289|23522->14304|23802->14555|23832->14556|23872->14567|23923->14589|23953->14590|23994->14602|24060->14639|24090->14640|24132->14653|24193->14685|24223->14686|24265->14699|24295->14700|24328->14704|24358->14705|24399->14717|24475->14764|24505->14765|24544->14775|24574->14776|24614->14787|24644->14788|24678->14794|24707->14795|24738->14798|24767->14799|24802->14806|24831->14807|24863->14811|24930->14849|24960->14850|24991->14853|25196->15029|25226->15030|25258->15034|25363->15110|25393->15111|25426->15116|25469->15131|25498->15132|25529->15135|25558->15136|25589->15139|25626->15147|25656->15148|25688->15152|26785->16221|26801->16227|26831->16235|26920->16295|26950->16296|26993->16310|27263->16551|27293->16552|27332->16562|27383->16584|27413->16585|27453->16596|27519->16633|27549->16634|27590->16646|27650->16677|27680->16678|27721->16690|27751->16691|27788->16700|27818->16701|27857->16711|27887->16712|27920->16717|27949->16718|27981->16722|28010->16723|28042->16727|28120->16777|28149->16778|28179->16780|28208->16781|28240->16785|28309->16825|28339->16826|28370->16829|28648->17078|28678->17079|28710->17083|28821->17165|28851->17166|28884->17171|28927->17186|28956->17187|28987->17190|29016->17191|29047->17194|29084->17202|29114->17203|29146->17207|30409->18442|30425->18448|30455->18456|30587->18559|30617->18560|30660->18574|30930->18815|30960->18816|30999->18826|31050->18848|31080->18849|31120->18860|31186->18897|31216->18898|31257->18910|31317->18941|31347->18942|31388->18954|31418->18955|31455->18964|31485->18965|31524->18975|31554->18976|31587->18981|31616->18982|31648->18986|31677->18987|31709->18991|31788->19042|31817->19043|31847->19045|31876->19046|31915->19057
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|42->11|51->20|51->20|51->20|54->23|54->23|54->23|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|85->54|85->54|85->54|90->59|90->59|90->59|91->60|92->61|92->61|92->61|95->64|95->64|95->64|99->68|99->68|99->68|102->71|102->71|102->71|107->76|108->77|128->97|128->97|128->97|129->98|130->99|130->99|130->99|131->100|131->100|131->100|134->103|134->103|134->103|138->107|138->107|138->107|138->107|138->107|138->107|141->110|141->110|141->110|141->110|141->110|141->110|146->115|147->116|158->127|158->127|159->128|213->182|215->184|246->215|246->215|246->215|247->216|247->216|247->216|247->216|248->217|248->217|248->217|248->217|248->217|248->217|250->219|251->220|281->250|281->250|281->250|282->251|282->251|282->251|282->251|283->252|283->252|283->252|283->252|283->252|283->252|284->253|284->253|284->253|284->253|284->253|284->253|286->255|287->256|299->268|304->273|305->274|305->274|306->275|306->275|306->275|307->276|307->276|307->276|308->277|308->277|308->277|308->277|308->277|308->277|309->278|310->279|310->279|310->279|311->280|311->280|311->280|311->280|311->280|311->280|312->281|314->283|314->283|314->283|315->284|317->286|317->286|318->287|319->288|319->288|320->289|320->289|320->289|321->290|321->290|322->291|324->293|324->293|325->294|326->295|326->295|327->296|327->296|327->296|329->298|329->298|331->300|331->300|332->301|334->303|334->303|334->303|336->305|336->305|337->306|344->313|344->313|345->314|345->314|345->314|346->315|346->315|346->315|347->316|348->317|348->317|349->318|349->318|350->319|350->319|351->320|351->320|352->321|352->321|354->323|354->323|354->323|355->324|357->326|357->326|357->326|360->329|360->329|361->330|368->337|368->337|369->338|369->338|369->338|370->339|370->339|370->339|371->340|372->341|372->341|373->342|373->342|374->343|374->343|375->344|375->344|376->345|376->345|378->347|378->347|378->347|379->348|381->350|381->350|381->350|385->354|385->354|386->355|393->362|393->362|394->363|394->363|394->363|395->364|395->364|395->364|396->365|397->366|397->366|398->367|398->367|399->368|399->368|400->369|400->369|401->370|401->370|403->372|403->372|403->372|404->373|404->373|404->373|405->374|405->374|405->374|406->375|408->377|408->377|408->377|410->379|410->379|411->380|418->387|418->387|419->388|419->388|419->388|420->389|420->389|420->389|421->390|422->391|422->391|423->392|423->392|423->392|423->392|424->393|425->394|425->394|426->395|426->395|427->396|427->396|428->397|428->397|429->398|429->398|431->400|431->400|433->402|433->402|433->402|434->403|434->403|434->403|435->404|435->404|435->404|436->405|438->407|438->407|438->407|441->410|441->410|442->411|449->418|449->418|450->419|450->419|450->419|451->420|451->420|451->420|452->421|453->422|453->422|454->423|454->423|454->423|454->423|455->424|456->425|456->425|457->426|457->426|458->427|458->427|459->428|459->428|460->429|460->429|462->431|462->431|464->433|464->433|464->433|465->434|468->437|468->437|469->438|470->439|470->439|471->440|472->441|472->441|473->442|473->442|474->443|474->443|474->443|475->444|496->465|496->465|496->465|498->467|498->467|499->468|506->475|506->475|507->476|507->476|507->476|508->477|508->477|508->477|509->478|510->479|510->479|511->480|511->480|512->481|512->481|513->482|513->482|514->483|514->483|514->483|514->483|515->484|516->485|516->485|517->486|517->486|519->488|519->488|519->488|520->489|524->493|524->493|525->494|526->495|526->495|527->496|528->497|528->497|529->498|529->498|530->499|530->499|530->499|531->500|560->529|560->529|560->529|563->532|563->532|564->533|571->540|571->540|572->541|572->541|572->541|573->542|573->542|573->542|574->543|575->544|575->544|576->545|576->545|577->546|577->546|578->547|578->547|579->548|579->548|579->548|579->548|580->549|581->550|581->550|582->551|582->551|588->557
                  -- GENERATED --
              */
          