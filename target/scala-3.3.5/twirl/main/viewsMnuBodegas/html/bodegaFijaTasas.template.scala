
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

object bodegaFijaTasas extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.TasaGrupo],List[tables.TasaEquipo],List[tables.Grupo],List[tables.Equipo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listTasaGrupo: List[tables.TasaGrupo], listTasaEquipo: List[tables.TasaEquipo],
listGrupos: List[tables.Grupo], listEquipos: List[tables.Equipo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

"""),_display_(/*8.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.50*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "ASIGNAR TASAS DE "+mapDiccionario.get("ARRIENDO")+" SOBRE PRECIOS DE VENTA", "("+bodega.getNombre().toUpperCase()+")")),format.raw/*10.151*/("""
		"""),format.raw/*11.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td width="50%">
							<b><font color="#008000">TASA GLOBAL</font></b>
							<table class="table table-sm table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<TR>
										<TH>"""),_display_(/*20.16*/mapDiccionario/*20.30*/.get("BODEGA")),format.raw/*20.44*/("""/PROYECTO</TH>
										<TH>CLIENTE</TH>
								        <TH>PROYECTO</TH>
										<TH>TASA """),_display_(/*23.21*/mapDiccionario/*23.35*/.get("ARRIENDO")),format.raw/*23.51*/("""</TH>
									</TR>
								</thead>
								<tbody>
									<TR>
										<td style="text-align:left;">"""),_display_(/*28.41*/bodega/*28.47*/.getNombre()),format.raw/*28.59*/("""</td>
										<td style="text-align:left;">"""),_display_(/*29.41*/bodega/*29.47*/.getNickCliente()),format.raw/*29.64*/("""</td>
										<td style="text-align:left;">"""),_display_(/*30.41*/bodega/*30.47*/.getNickProyecto()),format.raw/*30.65*/("""</td>
										<td style="text-align:center;">
											<input type="text" class="form-control center"
												id="tasaGlobal"
												onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
												onblur = "value = formatPorcentaje(value);"
												onkeydown="return ingresoDouble(window.event)"
												onchange="modificarTasaGlobal(value)">
										</td>
									</TR>
								</tbody>
							</table>
						</td>
						<td style="text-align:center;" width="50%">
							<h5><b><font color="red">IMPORTANTE, LAS TASAS A INGRESAR SON MENSUALES (FIJA EL PRECIO DE ARRIENDO SOLO LA PRIMERA VEZ QUE SE ENTREGA UN EQUIPO).</font></b></h5>
						</td>
					</tr>
					<tr>
						<td style="vertical-align:top">
							<b><font color="#008000">TASAS POR GRUPO</font></b>
							<table id="tblTasaGrupo" class="table table-sm table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<TR>
										<TH>GRUPO</TH>
										<TH>TASA """),_display_(/*54.21*/mapDiccionario/*54.35*/.get("ARRIENDO")),format.raw/*54.51*/("""</TH>
								        <TH>DEL</TH>
									</TR>
								</thead>
								<tbody>
									"""),_display_(/*59.11*/for(lista <- listTasaGrupo) yield /*59.38*/{_display_(Seq[Any](format.raw/*59.39*/("""
										"""),format.raw/*60.11*/("""<tr>
											<td style="text-align:left;">"""),_display_(/*61.42*/lista/*61.47*/.getNombreGrupo()),format.raw/*61.64*/("""</td>
											<td style="text-align:center;">
												<input type="text" class="form-control center"
													id="tasaGrupo_"""),_display_(/*64.29*/lista/*64.34*/.getId_grupo()),format.raw/*64.48*/(""""
													onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
													onblur = "value = formatPorcentaje(value);"
													onkeydown="return ingresoDouble(window.event)"
													onchange="modificarTasaGrupo("""),_display_(/*68.44*/lista/*68.49*/.getId_grupo()),format.raw/*68.63*/(""", value)">
											</td>
											<td  style="text-align:center;">
												<a href="#" onclick= "eliminarTasaGrupo(this, """),_display_(/*71.60*/lista/*71.65*/.getId_grupo()),format.raw/*71.79*/(""")">
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
							<b><font color="#008000">TASAS POR EQUIPO</font></b>
							<table id="tblTasaEquipo" class="table table-sm table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<TR>
										<TH>CODIGO</TH>
										<TH>EQUIPO</TH>
										<TH>TASA</TH>
								        <TH>DEL</TH>
									</TR>
								</thead>
								<tbody>
									"""),_display_(/*97.11*/for(lista <- listTasaEquipo) yield /*97.39*/{_display_(Seq[Any](format.raw/*97.40*/("""
										"""),format.raw/*98.11*/("""<tr>
											<td style="text-align:left;">"""),_display_(/*99.42*/lista/*99.47*/.getCodigoEquipo()),format.raw/*99.65*/("""</td>
											<td style="text-align:left;">"""),_display_(/*100.42*/lista/*100.47*/.getNombreEquipo()),format.raw/*100.65*/("""</td>
											<td style="text-align:center;">
												<input type="text" class="form-control center"
													id="tasaEquipo_"""),_display_(/*103.30*/lista/*103.35*/.getId_equipo()),format.raw/*103.50*/(""""
													onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
													onblur = "value = formatPorcentaje(value);"
													onkeydown="return ingresoDouble(window.event)"
													onchange="modificarTasaEquipo("""),_display_(/*107.45*/lista/*107.50*/.getId_equipo()),format.raw/*107.65*/(""","""),_display_(/*107.67*/lista/*107.72*/.getId_cotizacion()),format.raw/*107.91*/(""", value)">
											</td>
											<td  style="text-align:center;">
												<a href="#" onclick= "eliminarTasaEquipo(this, """),_display_(/*110.61*/lista/*110.66*/.getId_equipo()),format.raw/*110.81*/(""","""),_display_(/*110.83*/lista/*110.88*/.getId_cotizacion()),format.raw/*110.107*/(""")">
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
	
	
	
	
	
	
	
	
	
	
	
	
	
""")))}),format.raw/*280.2*/("""




"""),format.raw/*285.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*286.31*/("""{"""),format.raw/*286.32*/("""
		"""),format.raw/*287.3*/("""$("#tasaGlobal").val(formatPorcentaje(""""),_display_(/*287.43*/(bodega.getTasaArriendo()*100)),format.raw/*287.73*/(""""));
		"""),_display_(/*288.4*/for(lista <- listTasaGrupo) yield /*288.31*/{_display_(Seq[Any](format.raw/*288.32*/("""
			"""),format.raw/*289.4*/("""$("#tasaGrupo_"""),_display_(/*289.19*/lista/*289.24*/.getId_grupo()),format.raw/*289.38*/("""").val(formatPorcentaje(""""),_display_(/*289.64*/(lista.getTasaArriendo()*100)),format.raw/*289.93*/(""""));
		""")))}),format.raw/*290.4*/("""
		"""),_display_(/*291.4*/for(lista <- listTasaEquipo) yield /*291.32*/{_display_(Seq[Any](format.raw/*291.33*/("""
			"""),format.raw/*292.4*/("""$("#tasaEquipo_"""),_display_(/*292.20*/lista/*292.25*/.getId_equipo()),format.raw/*292.40*/("""").val(formatPorcentaje(""""),_display_(/*292.66*/(lista.getTasaArriendo()*100)),format.raw/*292.95*/(""""));
		""")))}),format.raw/*293.4*/("""
		
		"""),format.raw/*295.3*/("""$('#tablaListaGrupos').DataTable("""),format.raw/*295.36*/("""{"""),format.raw/*295.37*/("""
	    	"""),format.raw/*296.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"language": """),format.raw/*298.19*/("""{"""),format.raw/*298.20*/("""
	        	"""),format.raw/*299.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*300.10*/("""}"""),format.raw/*300.11*/("""
	 	"""),format.raw/*301.4*/("""}"""),format.raw/*301.5*/(""" """),format.raw/*301.6*/(""");
		$('#tablaListaEquipos').DataTable("""),format.raw/*302.37*/("""{"""),format.raw/*302.38*/("""
	    	"""),format.raw/*303.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"language": """),format.raw/*305.19*/("""{"""),format.raw/*305.20*/("""
	        	"""),format.raw/*306.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*307.10*/("""}"""),format.raw/*307.11*/("""
	 	 """),format.raw/*308.5*/("""}"""),format.raw/*308.6*/(""" """),format.raw/*308.7*/(""");
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*310.2*/("""}"""),format.raw/*310.3*/(""");
	
	const modificarTasaGlobal = (tasa) => """),format.raw/*312.40*/("""{"""),format.raw/*312.41*/("""
		"""),format.raw/*313.3*/("""var formData = new FormData();
		formData.append('modifica','tasaGlobal');
		formData.append('id_bodegaEmpresa',"""),_display_(/*315.39*/bodega/*315.45*/.getId()),format.raw/*315.53*/(""");
	  	formData.append('tasa',tasa);
        $.ajax("""),format.raw/*317.16*/("""{"""),format.raw/*317.17*/("""
            """),format.raw/*318.13*/("""url: "/modificarTasaArriendoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*325.36*/("""{"""),format.raw/*325.37*/("""
	     		"""),format.raw/*326.9*/("""if(respuesta=="error")"""),format.raw/*326.31*/("""{"""),format.raw/*326.32*/("""
	     			"""),format.raw/*327.10*/("""alertify.alert(msgError, function () """),format.raw/*327.47*/("""{"""),format.raw/*327.48*/("""
		     			"""),format.raw/*328.11*/("""location.href = "/";
		     		"""),format.raw/*329.10*/("""}"""),format.raw/*329.11*/(""");
	     		"""),format.raw/*330.9*/("""}"""),format.raw/*330.10*/("""
	     	"""),format.raw/*331.8*/("""}"""),format.raw/*331.9*/("""
        """),format.raw/*332.9*/("""}"""),format.raw/*332.10*/(""");
	"""),format.raw/*333.2*/("""}"""),format.raw/*333.3*/("""
	
	"""),format.raw/*335.2*/("""const modificarTasaGrupo = (id_grupo, tasa) => """),format.raw/*335.49*/("""{"""),format.raw/*335.50*/("""
		"""),format.raw/*336.3*/("""var formData = new FormData();
		formData.append('modifica','tasaGrupo');
		formData.append('id_bodegaEmpresa',"""),_display_(/*338.39*/bodega/*338.45*/.getId()),format.raw/*338.53*/(""");
		formData.append('id_grupo',id_grupo);
	  	formData.append('tasa',tasa);
        $.ajax("""),format.raw/*341.16*/("""{"""),format.raw/*341.17*/("""
            """),format.raw/*342.13*/("""url: "/modificarTasaArriendoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*349.36*/("""{"""),format.raw/*349.37*/("""
	     		"""),format.raw/*350.9*/("""if(respuesta=="error")"""),format.raw/*350.31*/("""{"""),format.raw/*350.32*/("""
	     			"""),format.raw/*351.10*/("""alertify.alert(msgError, function () """),format.raw/*351.47*/("""{"""),format.raw/*351.48*/("""
		     			"""),format.raw/*352.11*/("""location.href = "/";
		     		"""),format.raw/*353.10*/("""}"""),format.raw/*353.11*/(""");
	     		"""),format.raw/*354.9*/("""}"""),format.raw/*354.10*/("""
	     	"""),format.raw/*355.8*/("""}"""),format.raw/*355.9*/("""
        """),format.raw/*356.9*/("""}"""),format.raw/*356.10*/(""");
	"""),format.raw/*357.2*/("""}"""),format.raw/*357.3*/("""
	
	"""),format.raw/*359.2*/("""const modificarTasaEquipo = (id_equipo, id_cotizacion, tasa) => """),format.raw/*359.66*/("""{"""),format.raw/*359.67*/("""
		"""),format.raw/*360.3*/("""var formData = new FormData();
		formData.append('modifica','tasaEquipo');
		formData.append('id_bodegaEmpresa',"""),_display_(/*362.39*/bodega/*362.45*/.getId()),format.raw/*362.53*/(""");
		formData.append('id_equipo',id_equipo);
		formData.append('id_cotizacion',id_cotizacion);
	  	formData.append('tasa',tasa);
        $.ajax("""),format.raw/*366.16*/("""{"""),format.raw/*366.17*/("""
            """),format.raw/*367.13*/("""url: "/modificarTasaArriendoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*374.36*/("""{"""),format.raw/*374.37*/("""
	     		"""),format.raw/*375.9*/("""if(respuesta=="error")"""),format.raw/*375.31*/("""{"""),format.raw/*375.32*/("""
	     			"""),format.raw/*376.10*/("""alertify.alert(msgError, function () """),format.raw/*376.47*/("""{"""),format.raw/*376.48*/("""
		     			"""),format.raw/*377.11*/("""location.href = "/";
		     		"""),format.raw/*378.10*/("""}"""),format.raw/*378.11*/(""");
	     		"""),format.raw/*379.9*/("""}"""),format.raw/*379.10*/("""
	     	"""),format.raw/*380.8*/("""}"""),format.raw/*380.9*/("""
        """),format.raw/*381.9*/("""}"""),format.raw/*381.10*/(""");
	"""),format.raw/*382.2*/("""}"""),format.raw/*382.3*/("""
	
	"""),format.raw/*384.2*/("""const eliminarTasaGrupo = (nodo, id_grupo) => """),format.raw/*384.48*/("""{"""),format.raw/*384.49*/("""
		"""),format.raw/*385.3*/("""alertify.confirm("Esta seguro de eliminar tasa de grupo", function (e) """),format.raw/*385.74*/("""{"""),format.raw/*385.75*/("""
			"""),format.raw/*386.4*/("""if (e) """),format.raw/*386.11*/("""{"""),format.raw/*386.12*/("""
				"""),format.raw/*387.5*/("""var formData = new FormData();
				formData.append('elimina','tasaGrupo');
				formData.append('id_bodegaEmpresa',"""),_display_(/*389.41*/bodega/*389.47*/.getId()),format.raw/*389.55*/(""");
				formData.append('id_grupo',id_grupo);
		        $.ajax("""),format.raw/*391.18*/("""{"""),format.raw/*391.19*/("""
		            """),format.raw/*392.15*/("""url: "/eliminarTasaArriendoAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(respuesta)"""),format.raw/*399.38*/("""{"""),format.raw/*399.39*/("""
			     		"""),format.raw/*400.11*/("""if(respuesta=="error")"""),format.raw/*400.33*/("""{"""),format.raw/*400.34*/("""
			     			"""),format.raw/*401.12*/("""alertify.alert(msgError, function () """),format.raw/*401.49*/("""{"""),format.raw/*401.50*/("""
				     			"""),format.raw/*402.13*/("""location.href = "/";
				     		"""),format.raw/*403.12*/("""}"""),format.raw/*403.13*/(""");
			     		"""),format.raw/*404.11*/("""}"""),format.raw/*404.12*/("""else"""),format.raw/*404.16*/("""{"""),format.raw/*404.17*/("""
			     			"""),format.raw/*405.12*/("""nodo.parentNode.parentNode.remove();
			     		"""),format.raw/*406.11*/("""}"""),format.raw/*406.12*/("""
			     	"""),format.raw/*407.10*/("""}"""),format.raw/*407.11*/("""
		        """),format.raw/*408.11*/("""}"""),format.raw/*408.12*/(""");
			"""),format.raw/*409.4*/("""}"""),format.raw/*409.5*/("""
		"""),format.raw/*410.3*/("""}"""),format.raw/*410.4*/(""");
		
	"""),format.raw/*412.2*/("""}"""),format.raw/*412.3*/("""
	
	"""),format.raw/*414.2*/("""const eliminarTasaEquipo = (nodo, id_equipo, id_cotizacion) => """),format.raw/*414.65*/("""{"""),format.raw/*414.66*/("""
		"""),format.raw/*415.3*/("""alertify.confirm("Esta seguro de eliminar tasa de equipo", function (e) """),format.raw/*415.75*/("""{"""),format.raw/*415.76*/("""
			"""),format.raw/*416.4*/("""if (e) """),format.raw/*416.11*/("""{"""),format.raw/*416.12*/("""
				"""),format.raw/*417.5*/("""var formData = new FormData();
				formData.append('elimina','tasaEquipo');
				formData.append('id_bodegaEmpresa',"""),_display_(/*419.41*/bodega/*419.47*/.getId()),format.raw/*419.55*/(""");
				formData.append('id_equipo',id_equipo);
				formData.append('id_cotizacion',id_cotizacion);
		        $.ajax("""),format.raw/*422.18*/("""{"""),format.raw/*422.19*/("""
		            """),format.raw/*423.15*/("""url: "/eliminarTasaArriendoAjax/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(respuesta)"""),format.raw/*430.38*/("""{"""),format.raw/*430.39*/("""
			     		"""),format.raw/*431.11*/("""if(respuesta=="error")"""),format.raw/*431.33*/("""{"""),format.raw/*431.34*/("""
			     			"""),format.raw/*432.12*/("""alertify.alert(msgError, function () """),format.raw/*432.49*/("""{"""),format.raw/*432.50*/("""
				     			"""),format.raw/*433.13*/("""location.href = "/";
				     		"""),format.raw/*434.12*/("""}"""),format.raw/*434.13*/(""");
			     		"""),format.raw/*435.11*/("""}"""),format.raw/*435.12*/("""else"""),format.raw/*435.16*/("""{"""),format.raw/*435.17*/("""
			     			"""),format.raw/*436.12*/("""nodo.parentNode.parentNode.remove();
			     		"""),format.raw/*437.11*/("""}"""),format.raw/*437.12*/("""
			     	"""),format.raw/*438.10*/("""}"""),format.raw/*438.11*/("""
		        """),format.raw/*439.11*/("""}"""),format.raw/*439.12*/(""");
			"""),format.raw/*440.4*/("""}"""),format.raw/*440.5*/("""
		"""),format.raw/*441.3*/("""}"""),format.raw/*441.4*/(""");
		
	"""),format.raw/*443.2*/("""}"""),format.raw/*443.3*/("""
	
	"""),format.raw/*445.2*/("""const seleccionaGrupo = (id_grupo) => """),format.raw/*445.40*/("""{"""),format.raw/*445.41*/("""
		"""),format.raw/*446.3*/("""let grupo = $("#selectGrupo_"+id_grupo).text();
		let tblTasaGrupo = document.getElementById("tblTasaGrupo");
		let flag = true;
		for(let i=1; i<tblTasaGrupo.rows.length; i++)"""),format.raw/*449.48*/("""{"""),format.raw/*449.49*/("""
			"""),format.raw/*450.4*/("""let valor1 = tblTasaGrupo.rows[i].cells[0].textContent;
			if(valor1==grupo)"""),format.raw/*451.21*/("""{"""),format.raw/*451.22*/("""
				"""),format.raw/*452.5*/("""flag=false;
			"""),format.raw/*453.4*/("""}"""),format.raw/*453.5*/("""
		"""),format.raw/*454.3*/("""}"""),format.raw/*454.4*/("""
		"""),format.raw/*455.3*/("""if(flag)"""),format.raw/*455.11*/("""{"""),format.raw/*455.12*/("""
			"""),format.raw/*456.4*/("""let tr, td, tabla;
			tabla = document.getElementById("tblTasaGrupo");
			tr = tabla.insertRow(tabla.rows.length-1);
			td = tr.insertCell(tr.cells.length);
			td.setAttribute ('style', 'text-align:left' );
		  	td.innerHTML = grupo;
		  	td = tr.insertCell(tr.cells.length);
		  	td.setAttribute ('style', 'text-align:center' );
		  	td.innerHTML = 	" <input type='text' class='form-control center'" +
		  		" value = '0.00 %'"+
		  		" id='tasaGrupo_"+id_grupo+"'"+
				" onfocus=\"value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\""+ 
				" onblur = 'value = formatPorcentaje(value);'"+
				" onkeydown='return ingresoDouble(window.event)'"+
				" onchange='modificarTasaGrupo("+id_grupo+", value)'>";
			td = tr.insertCell(tr.cells.length);
			td.setAttribute ('style', 'text-align:center' );
		  	td.innerHTML = 	" <a href='#' onclick= 'eliminarTasaGrupo(this, "+id_grupo+")'>"+
				" <kbd style='background-color: red'>X</kbd></a>";
		  	var formData = new FormData();
			formData.append('graba','tasaGrupo');
			formData.append('id_bodegaEmpresa',"""),_display_(/*477.40*/bodega/*477.46*/.getId()),format.raw/*477.54*/(""");
			formData.append('id_grupo',id_grupo);
	        $.ajax("""),format.raw/*479.17*/("""{"""),format.raw/*479.18*/("""
	            """),format.raw/*480.14*/("""url: "/grabaTasaArriendoAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*487.37*/("""{"""),format.raw/*487.38*/("""
		     		"""),format.raw/*488.10*/("""if(respuesta=="error")"""),format.raw/*488.32*/("""{"""),format.raw/*488.33*/("""
		     			"""),format.raw/*489.11*/("""alertify.alert(msgError, function () """),format.raw/*489.48*/("""{"""),format.raw/*489.49*/("""
			     			"""),format.raw/*490.12*/("""location.href = "/";
			     		"""),format.raw/*491.11*/("""}"""),format.raw/*491.12*/(""");
		     		"""),format.raw/*492.10*/("""}"""),format.raw/*492.11*/("""
		     	"""),format.raw/*493.9*/("""}"""),format.raw/*493.10*/("""
	        """),format.raw/*494.10*/("""}"""),format.raw/*494.11*/(""");
		"""),format.raw/*495.3*/("""}"""),format.raw/*495.4*/("""else"""),format.raw/*495.8*/("""{"""),format.raw/*495.9*/("""
			"""),format.raw/*496.4*/("""alertify.alert("El grupo ya esta incorporado");
		"""),format.raw/*497.3*/("""}"""),format.raw/*497.4*/("""
	"""),format.raw/*498.2*/("""}"""),format.raw/*498.3*/("""
	
	"""),format.raw/*500.2*/("""const seleccionaEquipo = (id_equipo) => """),format.raw/*500.42*/("""{"""),format.raw/*500.43*/("""
		"""),format.raw/*501.3*/("""let cod_equipo = $("#selectEquipoCod_"+id_equipo).text();
		let nom_equipo = $("#selectEquipoNom_"+id_equipo).text();
		let tblTasaEquipo = document.getElementById("tblTasaEquipo");
		let flag = true;
		for(let i=1; i<tblTasaEquipo.rows.length; i++)"""),format.raw/*505.49*/("""{"""),format.raw/*505.50*/("""
			"""),format.raw/*506.4*/("""let valor1 = tblTasaEquipo.rows[i].cells[0].textContent;
			if(valor1==cod_equipo)"""),format.raw/*507.26*/("""{"""),format.raw/*507.27*/("""
				"""),format.raw/*508.5*/("""flag=false;
			"""),format.raw/*509.4*/("""}"""),format.raw/*509.5*/("""
		"""),format.raw/*510.3*/("""}"""),format.raw/*510.4*/("""
		"""),format.raw/*511.3*/("""if(flag)"""),format.raw/*511.11*/("""{"""),format.raw/*511.12*/("""
			"""),format.raw/*512.4*/("""let tr, td, tabla;
			tabla = document.getElementById("tblTasaEquipo");
			
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
		  		" id='tasaEquipo_"+id_equipo+"'"+
				" onfocus=\"value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\""+ 
				" onblur = 'value = formatPorcentaje(value);'"+
				" onkeydown='return ingresoDouble(window.event)'"+
				" onchange='modificarTasaEquipo("+id_equipo+",0, value)'>";
				
			td = tr.insertCell(tr.cells.length);
			td.setAttribute ('style', 'text-align:center' );
		  	td.innerHTML = 	" <a href='#' onclick= 'eliminarTasaEquipo(this, "+id_equipo+",0)'>"+
				" <kbd style='background-color: red'>X</kbd></a>";
		  	var formData = new FormData();
			formData.append('graba','tasaEquipo');
			formData.append('id_bodegaEmpresa',"""),_display_(/*541.40*/bodega/*541.46*/.getId()),format.raw/*541.54*/(""");
			formData.append('id_equipo',id_equipo);
			formData.append('id_cotizacion','0');
	        $.ajax("""),format.raw/*544.17*/("""{"""),format.raw/*544.18*/("""
	            """),format.raw/*545.14*/("""url: "/grabaTasaArriendoAjax/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*552.37*/("""{"""),format.raw/*552.38*/("""
		     		"""),format.raw/*553.10*/("""if(respuesta=="error")"""),format.raw/*553.32*/("""{"""),format.raw/*553.33*/("""
		     			"""),format.raw/*554.11*/("""alertify.alert(msgError, function () """),format.raw/*554.48*/("""{"""),format.raw/*554.49*/("""
			     			"""),format.raw/*555.12*/("""location.href = "/";
			     		"""),format.raw/*556.11*/("""}"""),format.raw/*556.12*/(""");
		     		"""),format.raw/*557.10*/("""}"""),format.raw/*557.11*/("""
		     	"""),format.raw/*558.9*/("""}"""),format.raw/*558.10*/("""
	        """),format.raw/*559.10*/("""}"""),format.raw/*559.11*/(""");
		"""),format.raw/*560.3*/("""}"""),format.raw/*560.4*/("""else"""),format.raw/*560.8*/("""{"""),format.raw/*560.9*/("""
			"""),format.raw/*561.4*/("""alertify.alert("El equipo ya esta incorporado");
		"""),format.raw/*562.3*/("""}"""),format.raw/*562.4*/("""
	"""),format.raw/*563.2*/("""}"""),format.raw/*563.3*/("""
	
	
	
	
	
"""),format.raw/*569.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listTasaGrupo:List[tables.TasaGrupo],listTasaEquipo:List[tables.TasaEquipo],listGrupos:List[tables.Grupo],listEquipos:List[tables.Equipo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listTasaGrupo,listTasaEquipo,listGrupos,listEquipos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.TasaGrupo],List[tables.TasaEquipo],List[tables.Grupo],List[tables.Equipo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listTasaGrupo,listTasaEquipo,listGrupos,listEquipos) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listTasaGrupo,listTasaEquipo,listGrupos,listEquipos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/bodegaFijaTasas.scala.html
                  HASH: 82a6eb91c421d6dab003992cd1e98f3446941f93
                  MATRIX: 1121->1|1487->274|1520->282|1536->290|1575->292|1603->295|1671->343|1699->345|1776->396|1945->543|1975->546|2430->974|2453->988|2488->1002|2611->1098|2634->1112|2671->1128|2806->1236|2821->1242|2854->1254|2927->1300|2942->1306|2980->1323|3053->1369|3068->1375|3107->1393|4154->2413|4177->2427|4214->2443|4334->2536|4377->2563|4416->2564|4455->2575|4528->2621|4542->2626|4580->2643|4743->2779|4757->2784|4792->2798|5068->3047|5082->3052|5117->3066|5275->3197|5289->3202|5324->3216|5475->3336|5513->3346|6251->4057|6295->4085|6334->4086|6373->4097|6446->4143|6460->4148|6499->4166|6574->4213|6589->4218|6629->4236|6794->4373|6809->4378|6846->4393|7124->4643|7139->4648|7176->4663|7206->4665|7221->4670|7262->4689|7422->4821|7437->4826|7474->4841|7504->4843|7519->4848|7561->4867|7713->4987|7752->4997|8144->5361|8184->5362|8218->5368|9798->6904|9835->6913|10924->7975|10966->8000|11006->8001|11043->8010|11101->8040|11117->8046|11147->8054|11260->8139|11276->8145|11306->8153|11337->8156|11353->8162|11387->8174|11454->8209|11489->8216|12579->9279|12622->9305|12662->9306|12699->9315|12758->9346|12774->9352|12804->9360|12921->9449|12937->9455|12967->9463|12998->9466|13014->9472|13049->9484|13153->9560|13169->9566|13199->9574|13230->9577|13246->9583|13281->9595|13348->9630|13383->9637|13734->9957|13767->9962|13858->10024|13888->10025|13919->10028|13987->10068|14039->10098|14074->10106|14118->10133|14158->10134|14190->10138|14233->10153|14248->10158|14284->10172|14338->10198|14389->10227|14428->10235|14459->10239|14504->10267|14544->10268|14576->10272|14620->10288|14635->10293|14672->10308|14726->10334|14777->10363|14816->10371|14850->10377|14912->10410|14942->10411|14977->10418|15118->10530|15148->10531|15188->10542|15294->10619|15324->10620|15356->10624|15385->10625|15414->10626|15482->10665|15512->10666|15547->10673|15688->10785|15718->10786|15758->10797|15864->10874|15894->10875|15927->10880|15956->10881|15985->10882|16084->10953|16113->10954|16186->10998|16216->10999|16247->11002|16388->11115|16404->11121|16434->11129|16515->11181|16545->11182|16587->11195|16854->11433|16884->11434|16921->11443|16972->11465|17002->11466|17041->11476|17107->11513|17137->11514|17177->11525|17236->11555|17266->11556|17305->11567|17335->11568|17371->11576|17400->11577|17437->11586|17467->11587|17499->11591|17528->11592|17560->11596|17636->11643|17666->11644|17697->11647|17837->11759|17853->11765|17883->11773|18004->11865|18034->11866|18076->11879|18343->12117|18373->12118|18410->12127|18461->12149|18491->12150|18530->12160|18596->12197|18626->12198|18666->12209|18725->12239|18755->12240|18794->12251|18824->12252|18860->12260|18889->12261|18926->12270|18956->12271|18988->12275|19017->12276|19049->12280|19142->12344|19172->12345|19203->12348|19344->12461|19360->12467|19390->12475|19563->12619|19593->12620|19635->12633|19902->12871|19932->12872|19969->12881|20020->12903|20050->12904|20089->12914|20155->12951|20185->12952|20225->12963|20284->12993|20314->12994|20353->13005|20383->13006|20419->13014|20448->13015|20485->13024|20515->13025|20547->13029|20576->13030|20608->13034|20683->13080|20713->13081|20744->13084|20844->13155|20874->13156|20906->13160|20942->13167|20972->13168|21005->13173|21148->13288|21164->13294|21194->13302|21285->13364|21315->13365|21359->13380|21639->13631|21669->13632|21709->13643|21760->13665|21790->13666|21831->13678|21897->13715|21927->13716|21969->13729|22030->13761|22060->13762|22102->13775|22132->13776|22165->13780|22195->13781|22236->13793|22312->13840|22342->13841|22381->13851|22411->13852|22451->13863|22481->13864|22515->13870|22544->13871|22575->13874|22604->13875|22639->13882|22668->13883|22700->13887|22792->13950|22822->13951|22853->13954|22954->14026|22984->14027|23016->14031|23052->14038|23082->14039|23115->14044|23259->14160|23275->14166|23305->14174|23450->14290|23480->14291|23524->14306|23804->14557|23834->14558|23874->14569|23925->14591|23955->14592|23996->14604|24062->14641|24092->14642|24134->14655|24195->14687|24225->14688|24267->14701|24297->14702|24330->14706|24360->14707|24401->14719|24477->14766|24507->14767|24546->14777|24576->14778|24616->14789|24646->14790|24680->14796|24709->14797|24740->14800|24769->14801|24804->14808|24833->14809|24865->14813|24932->14851|24962->14852|24993->14855|25198->15031|25228->15032|25260->15036|25365->15112|25395->15113|25428->15118|25471->15133|25500->15134|25531->15137|25560->15138|25591->15141|25628->15149|25658->15150|25690->15154|26787->16223|26803->16229|26833->16237|26922->16297|26952->16298|26995->16312|27265->16553|27295->16554|27334->16564|27385->16586|27415->16587|27455->16598|27521->16635|27551->16636|27592->16648|27652->16679|27682->16680|27723->16692|27753->16693|27790->16702|27820->16703|27859->16713|27889->16714|27922->16719|27951->16720|27983->16724|28012->16725|28044->16729|28122->16779|28151->16780|28181->16782|28210->16783|28242->16787|28311->16827|28341->16828|28372->16831|28650->17080|28680->17081|28712->17085|28823->17167|28853->17168|28886->17173|28929->17188|28958->17189|28989->17192|29018->17193|29049->17196|29086->17204|29116->17205|29148->17209|30411->18444|30427->18450|30457->18458|30589->18561|30619->18562|30662->18576|30932->18817|30962->18818|31001->18828|31052->18850|31082->18851|31122->18862|31188->18899|31218->18900|31259->18912|31319->18943|31349->18944|31390->18956|31420->18957|31457->18966|31487->18967|31526->18977|31556->18978|31589->18983|31618->18984|31650->18988|31679->18989|31711->18993|31790->19044|31819->19045|31849->19047|31878->19048|31917->19059
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|42->11|51->20|51->20|51->20|54->23|54->23|54->23|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|85->54|85->54|85->54|90->59|90->59|90->59|91->60|92->61|92->61|92->61|95->64|95->64|95->64|99->68|99->68|99->68|102->71|102->71|102->71|107->76|108->77|128->97|128->97|128->97|129->98|130->99|130->99|130->99|131->100|131->100|131->100|134->103|134->103|134->103|138->107|138->107|138->107|138->107|138->107|138->107|141->110|141->110|141->110|141->110|141->110|141->110|146->115|147->116|158->127|158->127|159->128|213->182|215->184|246->215|246->215|246->215|247->216|247->216|247->216|247->216|248->217|248->217|248->217|248->217|248->217|248->217|250->219|251->220|281->250|281->250|281->250|282->251|282->251|282->251|282->251|283->252|283->252|283->252|283->252|283->252|283->252|284->253|284->253|284->253|284->253|284->253|284->253|286->255|287->256|311->280|316->285|317->286|317->286|318->287|318->287|318->287|319->288|319->288|319->288|320->289|320->289|320->289|320->289|320->289|320->289|321->290|322->291|322->291|322->291|323->292|323->292|323->292|323->292|323->292|323->292|324->293|326->295|326->295|326->295|327->296|329->298|329->298|330->299|331->300|331->300|332->301|332->301|332->301|333->302|333->302|334->303|336->305|336->305|337->306|338->307|338->307|339->308|339->308|339->308|341->310|341->310|343->312|343->312|344->313|346->315|346->315|346->315|348->317|348->317|349->318|356->325|356->325|357->326|357->326|357->326|358->327|358->327|358->327|359->328|360->329|360->329|361->330|361->330|362->331|362->331|363->332|363->332|364->333|364->333|366->335|366->335|366->335|367->336|369->338|369->338|369->338|372->341|372->341|373->342|380->349|380->349|381->350|381->350|381->350|382->351|382->351|382->351|383->352|384->353|384->353|385->354|385->354|386->355|386->355|387->356|387->356|388->357|388->357|390->359|390->359|390->359|391->360|393->362|393->362|393->362|397->366|397->366|398->367|405->374|405->374|406->375|406->375|406->375|407->376|407->376|407->376|408->377|409->378|409->378|410->379|410->379|411->380|411->380|412->381|412->381|413->382|413->382|415->384|415->384|415->384|416->385|416->385|416->385|417->386|417->386|417->386|418->387|420->389|420->389|420->389|422->391|422->391|423->392|430->399|430->399|431->400|431->400|431->400|432->401|432->401|432->401|433->402|434->403|434->403|435->404|435->404|435->404|435->404|436->405|437->406|437->406|438->407|438->407|439->408|439->408|440->409|440->409|441->410|441->410|443->412|443->412|445->414|445->414|445->414|446->415|446->415|446->415|447->416|447->416|447->416|448->417|450->419|450->419|450->419|453->422|453->422|454->423|461->430|461->430|462->431|462->431|462->431|463->432|463->432|463->432|464->433|465->434|465->434|466->435|466->435|466->435|466->435|467->436|468->437|468->437|469->438|469->438|470->439|470->439|471->440|471->440|472->441|472->441|474->443|474->443|476->445|476->445|476->445|477->446|480->449|480->449|481->450|482->451|482->451|483->452|484->453|484->453|485->454|485->454|486->455|486->455|486->455|487->456|508->477|508->477|508->477|510->479|510->479|511->480|518->487|518->487|519->488|519->488|519->488|520->489|520->489|520->489|521->490|522->491|522->491|523->492|523->492|524->493|524->493|525->494|525->494|526->495|526->495|526->495|526->495|527->496|528->497|528->497|529->498|529->498|531->500|531->500|531->500|532->501|536->505|536->505|537->506|538->507|538->507|539->508|540->509|540->509|541->510|541->510|542->511|542->511|542->511|543->512|572->541|572->541|572->541|575->544|575->544|576->545|583->552|583->552|584->553|584->553|584->553|585->554|585->554|585->554|586->555|587->556|587->556|588->557|588->557|589->558|589->558|590->559|590->559|591->560|591->560|591->560|591->560|592->561|593->562|593->562|594->563|594->563|600->569
                  -- GENERATED --
              */
          