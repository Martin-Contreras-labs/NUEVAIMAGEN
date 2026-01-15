
package viewsMnuPlanes.html

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

object hojaVidaMantencionLista extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion],String,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaPlanes: List[tables.PlanMantencion], fecha: String, flagModal: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "PLANES DE MANTENCION EQUIPOS","CREAR/MODIFICAR")),format.raw/*9.81*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			"""),_display_(if(mapPermiso.get("planHojaVida")!=null && mapPermiso.get("importaHojaVidaIconstruye")!=null)/*11.98*/{_display_(Seq[Any](format.raw/*11.99*/("""
				"""),format.raw/*12.5*/("""<button type="button" class="btn btn-sm  btn-info" style="font-size: 10px; width: 250px" onclick="$('#modalCargaMasiva').modal('show')">CARGA MASIVA</button>
			""")))} else {null} ),format.raw/*13.5*/("""
			"""),format.raw/*14.4*/("""<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">

				<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td>
								<div align="center">
									<button type="button" class="btn btn-sm btn-success" onclick="window.print(); return false;">
										Imprimir
									</button>
									<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
										Exportar a Excel
									</button>
									<button type="button"  class="btn btn-sm btn-success"
									onclick="location.href='/home/';">
										Volver
									</button>
								</div>
							</td>
						</tr>
					</table>
				</div>

				<form id="excel" class="formulario" method="post" action="/hojaVidaMantencionListaExcel/"></form>

				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH  style= "text-align: center;">GRUPO</TH>
					        <TH  style= "text-align: center;">CODIGO</TH>
							<TH  style= "text-align: center;">EQUIPO</TH>
							
							<TH  style= "text-align: center;">PLAN</TH>
							<TH style="text-align:center">FECHA<BR>LECTURA</TH>
							<TH style="text-align:center">UN</TH>
							<TH style="text-align:center">LECTURA</TH>
							<TH style="text-align:center">ROTACION</TH>
							<TH style="text-align:center">CONSUMO<BR>PROM MES</TH>
							<TH style="text-align:center">PROXIMA<br>MANTENCION</TH>
							<TH style="text-align:center">FECHA ESTIMADA<br>MANTENCION</TH>
							
							<TH style="text-align:center">CONSUMO<BR>ESTIMADO</TH>
							
							<TH style="text-align:center" title="Verde al día. Amarillo esta entre el 25% y 10% de la rotación. Rojo se debe hacer mantención.">DIFERENCIA</TH>
								
							<TH style="text-align:center">PLAN</TH>
							<TH style="text-align:center">HOJA</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*64.8*/for(lista1 <- listaPlanes) yield /*64.34*/{_display_(Seq[Any](format.raw/*64.35*/("""
							"""),format.raw/*65.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*66.39*/lista1/*66.45*/.equipoGrupo),format.raw/*66.57*/("""</td>
								<td style="text-align:center;">"""),_display_(/*67.41*/lista1/*67.47*/.equipoCodigo),format.raw/*67.60*/("""</td>
								<td style="text-align:left;">"""),_display_(/*68.39*/lista1/*68.45*/.equipoNombre),format.raw/*68.58*/("""</td>
								
								<td style="text-align:left;">"""),_display_(/*70.39*/lista1/*70.45*/.tipoPlanNombre),format.raw/*70.60*/("""</td>
								<td style="text-align:center">
									<div style="display:none">"""),_display_(/*72.37*/utilities/*72.46*/.Fechas.AAMMDD(lista1.fechaReset)),format.raw/*72.79*/("""</div>
									<input type="hidden" class="fechaReset" value=""""),_display_(/*73.58*/utilities/*73.67*/.Fechas.AAMMDD(lista1.fechaReset)),format.raw/*73.100*/("""">
									"""),_display_(/*74.11*/lista1/*74.17*/.fechaReset),format.raw/*74.28*/("""
								"""),format.raw/*75.9*/("""</td>
								<td style="text-align:center">"""),_display_(/*76.40*/lista1/*76.46*/.unidadMantencion),format.raw/*76.63*/("""</td>
								<td style="text-align:right">
									<input type="hidden" class="estadoActual" value=""""),_display_(/*78.60*/lista1/*78.66*/.estadoActual),format.raw/*78.79*/("""">
									"""),_display_(/*79.11*/lista1/*79.17*/.estadoActual),format.raw/*79.30*/("""
								"""),format.raw/*80.9*/("""</td>
								<td style="text-align:right">"""),_display_(/*81.39*/lista1/*81.45*/.cadaNEstimado),format.raw/*81.59*/("""</td>
								<td style="text-align:right">
									<input type="hidden" class="consumoEstimadoPorMes" value=""""),_display_(/*83.69*/lista1/*83.75*/.consumoEstimadoPorMes),format.raw/*83.97*/("""">
									"""),_display_(/*84.11*/lista1/*84.17*/.consumoEstimadoPorMes),format.raw/*84.39*/("""
								"""),format.raw/*85.9*/("""</td>
								
								
								<td style="text-align:right;">
									<input type="hidden" class="proximaMantencion" value=""""),_display_(/*89.65*/lista1/*89.71*/.proximaMantencion),format.raw/*89.89*/("""">
									"""),_display_(/*90.11*/lista1/*90.17*/.proximaMantencion),format.raw/*90.35*/("""
								"""),format.raw/*91.9*/("""</td>
								
								<td style="text-align:center">
									<div class="auxFechaEstimada" style="display: none"></div>
									<input type="date" class="fechaEstimada form-control center" disabled>
								</td>
								
								<td style="text-align:right;">"""),_display_(/*98.40*/lista1/*98.46*/.consumoEstimadoAHoy),format.raw/*98.66*/("""</td>
								
								"""),_display_(if(MnuPlanes.esMayor(lista1.consumoEstimadoAHoy,lista1.proximaMantencion))/*100.84*/{_display_(Seq[Any](format.raw/*100.85*/("""
									"""),format.raw/*101.10*/("""<td style="text-align:right; background-color:red" title="Falta Mantención Urgente">"""),_display_(/*101.95*/lista1/*101.101*/.diferencia),format.raw/*101.112*/("""</td>
								""")))}else/*102.14*/{_display_(Seq[Any](format.raw/*102.15*/("""
									"""),_display_(if(MnuPlanes.pintaColor(lista1.consumoEstimadoAHoy,lista1.proximaMantencion,lista1.cadaNEstimado).equals("verde"))/*103.125*/{_display_(Seq[Any](format.raw/*103.126*/("""
										"""),format.raw/*104.11*/("""<td style="text-align:right; background-color:green" title="Mantención al Día">"""),_display_(/*104.91*/lista1/*104.97*/.diferencia),format.raw/*104.108*/("""</td>
									""")))}else/*105.15*/{_display_(Seq[Any](format.raw/*105.16*/("""
										"""),_display_(if(MnuPlanes.pintaColor(lista1.consumoEstimadoAHoy,lista1.proximaMantencion,lista1.cadaNEstimado).equals("amarillo"))/*106.129*/{_display_(Seq[Any](format.raw/*106.130*/("""
											"""),format.raw/*107.12*/("""<td style="text-align:right; background-color:yellow" title="Próximo a requerir Mantención">"""),_display_(/*107.105*/lista1/*107.111*/.diferencia),format.raw/*107.122*/("""</td>
										""")))}else/*108.16*/{_display_(Seq[Any](format.raw/*108.17*/("""
											"""),format.raw/*109.12*/("""<td style="text-align:right; background-color:red" title="Requiere Mantención">"""),_display_(/*109.92*/lista1/*109.98*/.diferencia),format.raw/*109.109*/("""</td>
										""")))}),format.raw/*110.12*/("""
									""")))}),format.raw/*111.11*/("""
								""")))}),format.raw/*112.10*/("""
								
								"""),format.raw/*114.9*/("""<td  style="text-align:center;">
									<form id="formplan_"""),_display_(/*115.30*/lista1/*115.36*/.id_tipoPlan),format.raw/*115.48*/("""&"""),_display_(/*115.50*/lista1/*115.56*/.id_equipo),format.raw/*115.66*/("""" method="post" action="/hojaVidaMantencionPlan/">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*116.57*/lista1/*116.63*/.id_equipo),format.raw/*116.73*/("""">
										<a href="#" onclick='document.getElementById("formplan_"""),_display_(/*117.67*/lista1/*117.73*/.id_tipoPlan),format.raw/*117.85*/("""&"""),_display_(/*117.87*/lista1/*117.93*/.id_equipo),format.raw/*117.103*/("""").submit();'>
											<kbd style="background-color: #73C6B6">Mant_PLAN</kbd>
										</a>
									</form>
								</td>
								<td  style="text-align:center;">
									<form id="formhoja_"""),_display_(/*123.30*/lista1/*123.36*/.id_tipoPlan),format.raw/*123.48*/("""&"""),_display_(/*123.50*/lista1/*123.56*/.id_equipo),format.raw/*123.66*/("""" method="post" action="/hojaVidaMantencionHoja/">
										<input type="hidden" name="id_equipo" value=""""),_display_(/*124.57*/lista1/*124.63*/.id_equipo),format.raw/*124.73*/("""">
										<a href="#" onclick='document.getElementById("formhoja_"""),_display_(/*125.67*/lista1/*125.73*/.id_tipoPlan),format.raw/*125.85*/("""&"""),_display_(/*125.87*/lista1/*125.93*/.id_equipo),format.raw/*125.103*/("""").submit();'>
											<kbd style="background-color: rgb(50, 215, 75)">Mant_HOJA_VIDA</kbd>
										</a>
									</form>
								</td>
							</TR>
			 			""")))}),format.raw/*131.9*/("""
					"""),format.raw/*132.6*/("""</tbody>
				</table>
			</div>
		</div>	
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id="modalCargaMasiva" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog modal-sm modal-dialog-scrollable" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
		      <!--
		        <h5 class="modal-title">Carga masiva desde IConstruye</h5>
		       -->
		       
	         <h5 class="modal-title">CARGA MASIVA</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        
			<div class="row">
				<div class="col-sm-12" style="text-align:center">

					<form id="importPlantilla" action="/detalleProductoUploadIConstruye/" method="POST" enctype="multipart/form-data">
						<span class="btn btn-primary btn-lg btn-file" style="font-size: 8px;">
							<div>PASO 1: SUBIR PROD_DET (IConstruye)</div>
							<input type="file" id="archivoXLSX" name="archivoXLSX" value="" onchange="subirArchivo(this.form, this.form.archivoXLSX.value)">
						</span>
					</form>
					
					<br>
					
					<form action="/detalleProductoDownloadMada/" method="POST">
						<input type="submit" class="btn btn-secondary btn-lg btn-file" style="font-size: 8px;" value="PASO 2: BAJAR PLANTILLA (Mada)">
						
					</form>
					
					<br>
					
					<form id="importPlantilla2" action="/detalleProductoUploadMada/" method="POST" enctype="multipart/form-data">
						<span class="btn btn-success btn-lg btn-file" style="font-size: 8px;">
							<div>PASO 3: SUBIR PLANTILLA (Mada)</div>
							<input type="file" id="archivoXLSX2" name="archivoXLSX" value="" onchange="subirArchivo2(this.form, this.form.archivoXLSX2.value)">
						</span>
					</form>

					
					
					<br>
					<button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
			<br>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	
	
	<div id="modalBajarArchivo" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog modal-sm modal-dialog-scrollable" role="document">
	    <div class="modal-content">
	    	<div class="modal-header">
		        <h5 class="modal-title">Enviar Excel de IConstruye por eMail</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		    </div>
		    <div class="modal-body">
	    
				<form action="/plantillaIConstruyeDownload/" method="POST" onsubmit="return validarForm();">
			
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH colspan="2">PERIODO A CONSIDERAR<br>(no mayor a 31 días)</TH>
							</TR>
							<TR>
								<TH>FECHA DESDE:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaDesde"
							  			name="fechaDesde"
							  			value=""""),_display_(/*225.21*/fecha),format.raw/*225.26*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>FECHA HASTA:</TH>
								<td>
									<input type="date" class="form-control center"
										id="fechaHasta"
							  			name="fechaHasta"
							  			value=""""),_display_(/*235.21*/fecha),format.raw/*235.26*/(""""
					        			required>
								</td>
							</TR>
							<TR>
								<TH>ENVIAR A EMAIL:</TH>
								<td>
									<input type="email" class="form-control center"
										id="sendToEmail"
							  			name="sendToEmail"
					        			required>
								</td>
							</TR>
						</thead>
					</table>
					<div class="row  justify-content-md-center">
						<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
							<input type="submit" value='Obtener OC' class="btn btn-primary btn-sm rounded-pill btn-block">
						</div>
					</div>
					
				</form>
			</div>
		 </div>
	  </div>
	</div>
""")))}),format.raw/*261.2*/("""




"""),format.raw/*266.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*267.31*/("""{"""),format.raw/*267.32*/("""
		  """),format.raw/*268.5*/("""calcFechaProxima();
		$('#tablaPrincipal').DataTable("""),format.raw/*269.34*/("""{"""),format.raw/*269.35*/("""
			"""),format.raw/*270.4*/(""""fixedHeader": true,
			"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
			"order": [[ 0, "asc" ],[ 1, "asc" ],[ 3, "asc" ]],
			"language": """),format.raw/*273.16*/("""{"""),format.raw/*273.17*/("""
				"""),format.raw/*274.5*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
			"""),format.raw/*275.4*/("""}"""),format.raw/*275.5*/("""
		"""),format.raw/*276.3*/("""}"""),format.raw/*276.4*/(""");

		document.getElementById('mostrarmostrar').style.display="block";
		  if('"""),_display_(/*279.10*/flagModal),format.raw/*279.19*/("""'=='1')"""),format.raw/*279.26*/("""{"""),format.raw/*279.27*/("""
			"""),format.raw/*280.4*/("""$('#modalCargaMasiva').modal('show');
		  """),format.raw/*281.5*/("""}"""),format.raw/*281.6*/("""
	"""),format.raw/*282.2*/("""}"""),format.raw/*282.3*/(""");
	
	const calcFechaProxima = () =>"""),format.raw/*284.32*/("""{"""),format.raw/*284.33*/("""
		"""),format.raw/*285.3*/("""let filas = $(".estadoActual").length;
			let hoy = new Date();
			
			for(let i=0; i<filas; i++)"""),format.raw/*288.30*/("""{"""),format.raw/*288.31*/("""
				"""),format.raw/*289.5*/("""let fecha = $(".fechaReset")[i].value;
				let proxima =  $(".proximaMantencion")[i].value;
				proxima = proxima.replace(/,/g,'');
				let actual =  $(".estadoActual")[i].value;
				actual = actual.replace(/,/g,'');
				let rotacion =  $(".consumoEstimadoPorMes")[i].value;
				rotacion = rotacion.replace(/,/g,'');
				let dif = parseFloat(proxima) - parseFloat(actual);
				if(dif <= 0 || parseFloat(rotacion) <= 0)"""),format.raw/*297.46*/("""{"""),format.raw/*297.47*/("""
					"""),format.raw/*298.6*/("""$(".auxFechaEstimada")[i].innerText = fechaAAMMDD(hoy);
					$(".fechaEstimada")[i].value = fechaAAMMDD(hoy);
				"""),format.raw/*300.5*/("""}"""),format.raw/*300.6*/("""else"""),format.raw/*300.10*/("""{"""),format.raw/*300.11*/("""
					"""),format.raw/*301.6*/("""let diasASumar = dif/rotacion * 30;
					let aux = fecha.split("-");
					let auxFecha = new Date(aux[0],parseInt(aux[1])-1,aux[2]);
					auxFecha.setDate(auxFecha.getDate() + diasASumar);
					$(".auxFechaEstimada")[i].innerText = fechaAAMMDD(auxFecha);
					$(".fechaEstimada")[i].value = fechaAAMMDD(auxFecha);
				"""),format.raw/*307.5*/("""}"""),format.raw/*307.6*/("""
			"""),format.raw/*308.4*/("""}"""),format.raw/*308.5*/("""
	"""),format.raw/*309.2*/("""}"""),format.raw/*309.3*/("""
	
	"""),format.raw/*311.2*/("""const validarForm = () =>"""),format.raw/*311.27*/("""{"""),format.raw/*311.28*/("""
		"""),format.raw/*312.3*/("""let flag = true;
		var desde = Date.parse(document.getElementById('fechaDesde').value);
		var hasta = Date.parse(document.getElementById('fechaHasta').value);
		var diffDias = Math.floor((hasta - desde)/(1000 * 60 * 60 * 24))+1;
		
		if(desde > hasta)"""),format.raw/*317.20*/("""{"""),format.raw/*317.21*/("""
			"""),format.raw/*318.4*/("""flag = false;
			alertify.alert('LA FECHA INICIAL NO PUEDE SER MAYOR A LA FECHA FINAL', function () """),format.raw/*319.87*/("""{"""),format.raw/*319.88*/("""
				"""),format.raw/*320.5*/("""return(flag);
     		"""),format.raw/*321.8*/("""}"""),format.raw/*321.9*/(""");
		"""),format.raw/*322.3*/("""}"""),format.raw/*322.4*/("""
		
		"""),format.raw/*324.3*/("""if(diffDias > 31)"""),format.raw/*324.20*/("""{"""),format.raw/*324.21*/("""
			"""),format.raw/*325.4*/("""flag = false;
			alertify.alert('EL PERIODO NO PUEDE SER MAYOR A 31 DIAS', function () """),format.raw/*326.74*/("""{"""),format.raw/*326.75*/("""
				"""),format.raw/*327.5*/("""return(flag);
     		"""),format.raw/*328.8*/("""}"""),format.raw/*328.9*/(""");
		"""),format.raw/*329.3*/("""}"""),format.raw/*329.4*/("""
		
		"""),format.raw/*331.3*/("""return(flag);
	"""),format.raw/*332.2*/("""}"""),format.raw/*332.3*/("""
	
	"""),format.raw/*334.2*/("""let extArray2 = new Array(".xls", ".xlsx");
	function subirArchivo(form, file) """),format.raw/*335.36*/("""{"""),format.raw/*335.37*/("""
		"""),format.raw/*336.3*/("""allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)
			file = file.slice(file.indexOf("\\") + 1);
		ext = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray2.length; i++) """),format.raw/*341.46*/("""{"""),format.raw/*341.47*/("""
			"""),format.raw/*342.4*/("""if (extArray2[i] == ext) """),format.raw/*342.29*/("""{"""),format.raw/*342.30*/(""" """),format.raw/*342.31*/("""allowSubmit = true; break; """),format.raw/*342.58*/("""}"""),format.raw/*342.59*/("""
		"""),format.raw/*343.3*/("""}"""),format.raw/*343.4*/("""
		"""),format.raw/*344.3*/("""if (allowSubmit) """),format.raw/*344.20*/("""{"""),format.raw/*344.21*/("""
			"""),format.raw/*345.4*/("""$("#importPlantilla").submit();
		"""),format.raw/*346.3*/("""}"""),format.raw/*346.4*/("""else"""),format.raw/*346.8*/("""{"""),format.raw/*346.9*/("""
			"""),format.raw/*347.4*/("""alertify.alert("Se permiten &uacute;nicamente archivos con la extenci&oacute;n: " 
			+ (extArray2.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.archivoXLSX.value="";
		"""),format.raw/*351.3*/("""}"""),format.raw/*351.4*/("""
	"""),format.raw/*352.2*/("""}"""),format.raw/*352.3*/("""
	"""),format.raw/*353.2*/("""function subirArchivo2(form, file) """),format.raw/*353.37*/("""{"""),format.raw/*353.38*/("""
		"""),format.raw/*354.3*/("""allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)
			file = file.slice(file.indexOf("\\") + 1);
		ext = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray2.length; i++) """),format.raw/*359.46*/("""{"""),format.raw/*359.47*/("""
			"""),format.raw/*360.4*/("""if (extArray2[i] == ext) """),format.raw/*360.29*/("""{"""),format.raw/*360.30*/(""" """),format.raw/*360.31*/("""allowSubmit = true; break; """),format.raw/*360.58*/("""}"""),format.raw/*360.59*/("""
		"""),format.raw/*361.3*/("""}"""),format.raw/*361.4*/("""
		"""),format.raw/*362.3*/("""if (allowSubmit) """),format.raw/*362.20*/("""{"""),format.raw/*362.21*/("""
			"""),format.raw/*363.4*/("""$("#importPlantilla2").submit();
		"""),format.raw/*364.3*/("""}"""),format.raw/*364.4*/("""else"""),format.raw/*364.8*/("""{"""),format.raw/*364.9*/("""
			"""),format.raw/*365.4*/("""alertify.alert("Se permiten &uacute;nicamente archivos con la extenci&oacute;n: " 
			+ (extArray2.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.archivoXLSX2.value="";
		"""),format.raw/*369.3*/("""}"""),format.raw/*369.4*/("""
	"""),format.raw/*370.2*/("""}"""),format.raw/*370.3*/("""
"""),format.raw/*371.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaPlanes:List[tables.PlanMantencion],fecha:String,flagModal:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaPlanes,fecha,flagModal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.PlanMantencion],String,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaPlanes,fecha,flagModal) => apply(mapDiccionario,mapPermiso,userMnu,listaPlanes,fecha,flagModal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPlanes/hojaVidaMantencionLista.scala.html
                  HASH: a96dad1cad115303af88db838390ef15050f70e8
                  MATRIX: 1061->1|1325->172|1358->180|1374->188|1413->190|1441->193|1509->241|1537->243|1613->294|1710->371|1740->374|1909->516|1948->517|1980->522|2185->684|2216->688|4230->2676|4272->2702|4311->2703|4346->2711|4416->2754|4431->2760|4464->2772|4537->2818|4552->2824|4586->2837|4657->2881|4672->2887|4706->2900|4786->2953|4801->2959|4837->2974|4945->3055|4963->3064|5017->3097|5108->3161|5126->3170|5181->3203|5221->3216|5236->3222|5268->3233|5304->3242|5376->3287|5391->3293|5429->3310|5559->3413|5574->3419|5608->3432|5648->3445|5663->3451|5697->3464|5733->3473|5804->3517|5819->3523|5854->3537|5993->3649|6008->3655|6051->3677|6091->3690|6106->3696|6149->3718|6185->3727|6339->3854|6354->3860|6393->3878|6433->3891|6448->3897|6487->3915|6523->3924|6814->4188|6829->4194|6870->4214|6996->4312|7036->4313|7075->4323|7188->4408|7205->4414|7239->4425|7278->4444|7318->4445|7472->4570|7513->4571|7553->4582|7661->4662|7677->4668|7711->4679|7751->4699|7791->4700|7949->4829|7990->4830|8031->4842|8153->4935|8170->4941|8204->4952|8245->4973|8285->4974|8326->4986|8434->5066|8450->5072|8484->5083|8533->5100|8576->5111|8618->5121|8664->5139|8754->5201|8770->5207|8804->5219|8834->5221|8850->5227|8882->5237|9017->5344|9033->5350|9065->5360|9162->5429|9178->5435|9212->5447|9242->5449|9258->5455|9291->5465|9516->5662|9532->5668|9566->5680|9596->5682|9612->5688|9644->5698|9779->5805|9795->5811|9827->5821|9924->5890|9940->5896|9974->5908|10004->5910|10020->5916|10053->5926|10246->6088|10280->6094|13697->9483|13724->9488|13994->9730|14021->9735|14653->10336|14686->10341|14777->10403|14807->10404|14840->10409|14922->10462|14952->10463|14984->10467|15173->10627|15203->10628|15236->10633|15335->10704|15364->10705|15395->10708|15424->10709|15532->10789|15563->10798|15599->10805|15629->10806|15661->10810|15731->10852|15760->10853|15790->10855|15819->10856|15884->10892|15914->10893|15945->10896|16071->10993|16101->10994|16134->10999|16581->11417|16611->11418|16645->11424|16787->11538|16816->11539|16849->11543|16879->11544|16913->11550|17259->11868|17288->11869|17320->11873|17349->11874|17379->11876|17408->11877|17440->11881|17494->11906|17524->11907|17555->11910|17835->12161|17865->12162|17897->12166|18026->12266|18056->12267|18089->12272|18138->12293|18167->12294|18200->12299|18229->12300|18263->12306|18309->12323|18339->12324|18371->12328|18487->12415|18517->12416|18550->12421|18599->12442|18628->12443|18661->12448|18690->12449|18724->12455|18767->12470|18796->12471|18828->12475|18936->12554|18966->12555|18997->12558|19251->12783|19281->12784|19313->12788|19367->12813|19397->12814|19427->12815|19483->12842|19513->12843|19544->12846|19573->12847|19604->12850|19650->12867|19680->12868|19712->12872|19774->12906|19803->12907|19835->12911|19864->12912|19896->12916|20138->13130|20167->13131|20197->13133|20226->13134|20256->13136|20320->13171|20350->13172|20381->13175|20635->13400|20665->13401|20697->13405|20751->13430|20781->13431|20811->13432|20867->13459|20897->13460|20928->13463|20957->13464|20988->13467|21034->13484|21064->13485|21096->13489|21159->13524|21188->13525|21220->13529|21249->13530|21281->13534|21524->13749|21553->13750|21583->13752|21612->13753|21641->13754
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|42->11|42->11|43->12|44->13|45->14|95->64|95->64|95->64|96->65|97->66|97->66|97->66|98->67|98->67|98->67|99->68|99->68|99->68|101->70|101->70|101->70|103->72|103->72|103->72|104->73|104->73|104->73|105->74|105->74|105->74|106->75|107->76|107->76|107->76|109->78|109->78|109->78|110->79|110->79|110->79|111->80|112->81|112->81|112->81|114->83|114->83|114->83|115->84|115->84|115->84|116->85|120->89|120->89|120->89|121->90|121->90|121->90|122->91|129->98|129->98|129->98|131->100|131->100|132->101|132->101|132->101|132->101|133->102|133->102|134->103|134->103|135->104|135->104|135->104|135->104|136->105|136->105|137->106|137->106|138->107|138->107|138->107|138->107|139->108|139->108|140->109|140->109|140->109|140->109|141->110|142->111|143->112|145->114|146->115|146->115|146->115|146->115|146->115|146->115|147->116|147->116|147->116|148->117|148->117|148->117|148->117|148->117|148->117|154->123|154->123|154->123|154->123|154->123|154->123|155->124|155->124|155->124|156->125|156->125|156->125|156->125|156->125|156->125|162->131|163->132|256->225|256->225|266->235|266->235|292->261|297->266|298->267|298->267|299->268|300->269|300->269|301->270|304->273|304->273|305->274|306->275|306->275|307->276|307->276|310->279|310->279|310->279|310->279|311->280|312->281|312->281|313->282|313->282|315->284|315->284|316->285|319->288|319->288|320->289|328->297|328->297|329->298|331->300|331->300|331->300|331->300|332->301|338->307|338->307|339->308|339->308|340->309|340->309|342->311|342->311|342->311|343->312|348->317|348->317|349->318|350->319|350->319|351->320|352->321|352->321|353->322|353->322|355->324|355->324|355->324|356->325|357->326|357->326|358->327|359->328|359->328|360->329|360->329|362->331|363->332|363->332|365->334|366->335|366->335|367->336|372->341|372->341|373->342|373->342|373->342|373->342|373->342|373->342|374->343|374->343|375->344|375->344|375->344|376->345|377->346|377->346|377->346|377->346|378->347|382->351|382->351|383->352|383->352|384->353|384->353|384->353|385->354|390->359|390->359|391->360|391->360|391->360|391->360|391->360|391->360|392->361|392->361|393->362|393->362|393->362|394->363|395->364|395->364|395->364|395->364|396->365|400->369|400->369|401->370|401->370|402->371
                  -- GENERATED --
              */
          