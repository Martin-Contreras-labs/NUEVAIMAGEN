
package viewsMnuCotizar.html

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

object otHacer extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template8[Map[String,String],Map[String,String],utilities.UserMnu,forms.FormOt,List[List[String]],String,List[tables.Proyecto],List[tables.Regiones],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
formOt: forms.FormOt, listBodegas: List[List[String]], tabla: String, listProyectos: List[tables.Proyecto], listRegiones: List[tables.Regiones]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
		
		"""),format.raw/*9.3*/("""<!-- MODAL CREA PROYECTOS -->
			"""),_display_(/*10.5*/modalProyectoNuevo(mapDiccionario, listRegiones)),format.raw/*10.53*/("""
			"""),format.raw/*11.4*/("""<script>
				const proyectoGrabaAjax = (id_proyecto) =>"""),format.raw/*12.47*/("""{"""),format.raw/*12.48*/("""
					"""),format.raw/*13.6*/("""$('#id_proyecto').val(id_proyecto);
					$('#nombreProyecto').val($("#proyectoNickName").val());
					let tabla = document.getElementById('tablaListaProyectos');
					let row = tabla.insertRow(tabla.rows.length);
					let cell = row.insertCell(0);
					cell.style.textAlign = "left";
					cell.innerHTML = $("#proyectoNickName").val();
					cell = row.insertCell(1);
					cell.style.textAlign = "left";
					cell.innerHTML = $("#proyectoNombre").val();
					row.setAttribute("onClick", "selectProyecto('" + id_proyecto + "')");
					row.setAttribute("data-dismiss", "modal");
					$("#id_proyecto").val(id_proyecto);
					$("#nickProyecto").val($("#proyectoNickName").val());
				"""),format.raw/*27.5*/("""}"""),format.raw/*27.6*/("""
			"""),format.raw/*28.4*/("""</script>
		<!-- FIN MODAL CREA PROYECTOS -->
		

		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*33.5*/barraTitulo(mapDiccionario, "COTIZACION PARA EMITIR "+mapDiccionario.get("ORDEN_DE_TRABAJO"), "")),format.raw/*33.102*/("""
			"""),format.raw/*34.4*/("""<form action="/otGrabar/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
				<h6>"""),_display_(/*35.10*/mapDiccionario/*35.24*/.get("ORDEN_DE_TRABAJO")),format.raw/*35.48*/(""":</h6>
				<div class="row  justify-content-md-center">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
							<thead style="background-color: #eeeeee">
								<tr>
									<td width="18%">
										<input type="hidden" id="id_ot" name="id_ot" value=""""),_display_(/*42.64*/formOt/*42.70*/.id_ot),format.raw/*42.76*/("""">
										<input type="hidden" id="id_cotizacion" name="id_cotizacion" value=""""),_display_(/*43.80*/formOt/*43.86*/.id_cotizacion),format.raw/*43.100*/("""">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">Nro. """),_display_(/*46.71*/mapDiccionario/*46.85*/.get("Orden_de_trabajo")),format.raw/*46.109*/("""</span>
									  		</div>
									  		<input type="text" class="form-control center"
									  				name="numeroOt"
													value = """"),_display_(/*50.24*/formOt/*50.30*/.numeroOt),format.raw/*50.39*/(""""
													readonly>
										</div>
									</td>
									<td width="18%">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">Fecha</span>
									  		</div>
									  		<input type="date" class="form-control center"
									  			name="fechaOt" 
									  			id="fechaOt"
									  			onblur="if(!limitaFecha(value,720,10)) value='"""),_display_(/*62.62*/formOt/*62.68*/.fechaOt),format.raw/*62.76*/("""';"
									  			value=""""),_display_(/*63.23*/formOt/*63.29*/.fechaOt),format.raw/*63.37*/(""""
							        			required>
										</div>
									</td>
									<td width="32%">
										<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*68.86*/formOt/*68.92*/.id_bodegaEmpresa),format.raw/*68.109*/("""">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*71.66*/mapDiccionario/*71.80*/.get("BODEGA")),format.raw/*71.94*/("""/PROYECTO</span>
									  		</div>
											"""),_display_(if(formOt.id_bodegaEmpresa > 0)/*73.44*/ {_display_(Seq[Any](format.raw/*73.46*/("""
										  		"""),format.raw/*74.15*/("""<input type="text" class="form-control left"
														value = """"),_display_(/*75.25*/formOt/*75.31*/.nombreBodega),format.raw/*75.44*/(""""
														readonly>
											""")))}else/*77.17*/{_display_(Seq[Any](format.raw/*77.18*/("""
												"""),format.raw/*78.13*/("""<input type="text" class="form-control"
			  										id="nombreBodega"
			  										value="" 
			  										style="background:white"
			  										onclick="$('#listaBodega').modal('show');"
			  										readonly>
											""")))}),format.raw/*84.13*/("""
										"""),format.raw/*85.11*/("""</div>
									</td>
									
									
									<td width="32%">
										<input type="hidden" id="id_proyecto" name="id_proyecto" value=""""),_display_(/*90.76*/formOt/*90.82*/.id_proyecto),format.raw/*90.94*/("""">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">NOMBRE PROYECTO</span>
									  		</div>
											<input type="text" class="form-control"
		  										id="nickProyecto"
		  										value=""""),_display_(/*97.23*/formOt/*97.29*/.nickProyecto),format.raw/*97.42*/("""" 
		  										style="background:white"
		  										onclick="$('#listaProyecto').modal('show');"
		  										readonly>
										</div>
									</td>
									
									<td align="center">
										<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
											<div id="txtBtn">Adjuntar</div>
					    					<input type="file" id="docAdjunto" name="docAdjunto" onchange="LimitAttach(this.form, this.form.docAdjunto.value);">
										</span>
									</td>
								</tr>
								<tr>
									<td width="30%">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">Fecha Entrega</span>
									  		</div>
											<input type="date" class="form-control center"
									  			name="fechaEnvio" 
									  			onblur="if(!limitaFecha(value,30,360)) value='';">
											
										</div>
									</td>
									<td colspan="20">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
									  		</div>
		  									<textarea class="form-control" 
		  										name="observaciones" 
		  										autocomplete="off">"""),_display_(/*130.35*/formOt/*130.41*/.observaciones),format.raw/*130.55*/("""</textarea>
										</div>
									</td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<hr>
				<h6>COTIZACION:</h6>
				<div class="row  justify-content-md-center">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						"""),_display_(/*142.8*/Html(tabla)),format.raw/*142.19*/("""
					"""),format.raw/*143.6*/("""</div>
				</div>
				
				<div class="noprint" align="left">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
							<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
						</div>
						<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
							<input type="submit"  id="aplica" value='GENERAR """),_display_(/*152.58*/mapDiccionario/*152.72*/.get("OT")),format.raw/*152.82*/("""' class="btn btn-success btn-sm rounded-pill btn-block">
						</div>
					</div>
				</div>
			</form>
		</div>
		
		<div id='listaBodega' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
			<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
				<div class='modal-content'>
					<div class='modal-header'>
						<h5 class='modal-title'>SELECCIONAR """),_display_(/*163.44*/mapDiccionario/*163.58*/.get("BODEGA")),format.raw/*163.72*/("""/PROYECTO</h5>
					        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
					          <span aria-hidden='true'>&times;</span>
					        </button>
					</div>
					<div class='modal-body'>
						<table id="tablaListaBodegas" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
							<thead style="background-color: #eeeeee">
								<TR>
									<TH>SUCURSAL</TH>
									<TH>"""),_display_(/*173.15*/mapDiccionario/*173.29*/.get("BODEGA")),format.raw/*173.43*/("""/PROYECTO</TH>
									<TH>CLIENTE</TH>
							        <TH>PROYECTO</TH>
								</TR>
							</thead>
							<tbody>
								"""),_display_(/*179.10*/for(lista1 <- listBodegas) yield /*179.36*/{_display_(Seq[Any](format.raw/*179.37*/("""
									"""),format.raw/*180.10*/("""<TR onclick="selectBodega("""),_display_(/*180.37*/lista1/*180.43*/.get(1)),format.raw/*180.50*/(""")">
										<td  style="text-align:left;">"""),_display_(/*181.42*/lista1/*181.48*/.get(16)),format.raw/*181.56*/("""</td>
										<td  style="text-align:left;"><div id="bodega_"""),_display_(/*182.58*/lista1/*182.64*/.get(1)),format.raw/*182.71*/("""">"""),_display_(/*182.74*/lista1/*182.80*/.get(5)),format.raw/*182.87*/("""</div></td>
										<td  style="text-align:left;">"""),_display_(/*183.42*/lista1/*183.48*/.get(7)),format.raw/*183.55*/("""</td>
										<td  style="text-align:left;">"""),_display_(/*184.42*/lista1/*184.48*/.get(8)),format.raw/*184.55*/("""</td>
									</TR>
					 			""")))}),format.raw/*186.11*/("""
							"""),format.raw/*187.8*/("""</tbody>
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
		
		<div id='listaProyecto' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
			<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
				<div class='modal-content'>
					<div class='modal-header'>
						<h5 class='modal-title'>SELECCIONAR PROYECTO</h5>
					        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
					          <span aria-hidden='true'>&times;</span>
					        </button>
					</div>
					<div class='modal-body'>
						<div class="noprint" align="center">
							<a href="#" onclick="$('#listaProyecto').modal('hide'); $('#modalProyectoNuevo').modal('show');">
								<kbd style="background-color: #73C6B6">Agregar Nuevo</kbd>
							</a>
						</div>
						<table id="tablaListaProyectos" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
							<thead style="background-color: #eeeeee">
								<TR>
									<TH>NOMBRE CORTO</TH>
									<TH>NOMBRE LARGO</TH>
								</TR>
							</thead>
							<tbody>
								"""),_display_(/*222.10*/for(lista1 <- listProyectos) yield /*222.38*/{_display_(Seq[Any](format.raw/*222.39*/("""
									"""),format.raw/*223.10*/("""<TR onclick="selectProyecto("""),_display_(/*223.39*/lista1/*223.45*/.id),format.raw/*223.48*/(""")">
										<td  style="text-align:left;"><div id="proyecto_"""),_display_(/*224.60*/lista1/*224.66*/.id),format.raw/*224.69*/("""">"""),_display_(/*224.72*/lista1/*224.78*/.nickName),format.raw/*224.87*/("""</div></td>
										<td  style="text-align:left;">"""),_display_(/*225.42*/lista1/*225.48*/.nombre),format.raw/*225.55*/("""</td>
									</TR>
					 			""")))}),format.raw/*227.11*/("""
							"""),format.raw/*228.8*/("""</tbody>
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
		

		
		
	
	
""")))}),format.raw/*245.2*/("""



"""),format.raw/*249.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*251.31*/("""{"""),format.raw/*251.32*/("""
		
		"""),format.raw/*253.3*/("""$('#tablaListaBodegas').DataTable("""),format.raw/*253.37*/("""{"""),format.raw/*253.38*/("""
	    	"""),format.raw/*254.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*257.19*/("""{"""),format.raw/*257.20*/("""
	        	"""),format.raw/*258.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*259.10*/("""}"""),format.raw/*259.11*/("""
	  	"""),format.raw/*260.5*/("""}"""),format.raw/*260.6*/(""" """),format.raw/*260.7*/(""");

		$('#tablaListaProyectos').DataTable("""),format.raw/*262.39*/("""{"""),format.raw/*262.40*/("""
	    	"""),format.raw/*263.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ]],
	    	"language": """),format.raw/*266.19*/("""{"""),format.raw/*266.20*/("""
	        	"""),format.raw/*267.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*268.10*/("""}"""),format.raw/*268.11*/("""
	  	"""),format.raw/*269.5*/("""}"""),format.raw/*269.6*/(""" """),format.raw/*269.7*/(""");

		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*272.2*/("""}"""),format.raw/*272.3*/(""");
	
	const selectBodega = (id_bodega) =>"""),format.raw/*274.37*/("""{"""),format.raw/*274.38*/("""
		"""),format.raw/*275.3*/("""$("#id_bodegaEmpresa").val(id_bodega);
		$("#nombreBodega").val($("#bodega_"+id_bodega).text());
		$('#listaBodega').modal('hide');
	"""),format.raw/*278.2*/("""}"""),format.raw/*278.3*/("""
	
	"""),format.raw/*280.2*/("""const selectProyecto = (id_proyecto) =>"""),format.raw/*280.41*/("""{"""),format.raw/*280.42*/("""
		"""),format.raw/*281.3*/("""$("#id_proyecto").val(id_proyecto);
		$("#nickProyecto").val($("#proyecto_"+id_proyecto).text());
		$('#listaProyecto').modal('hide');
	"""),format.raw/*284.2*/("""}"""),format.raw/*284.3*/("""
	
	"""),format.raw/*286.2*/("""const validarForm = () =>"""),format.raw/*286.27*/("""{"""),format.raw/*286.28*/("""
		"""),format.raw/*287.3*/("""$("#aplica").attr('disabled',true);
		let flag = true;
		let id_bodegaEmpresa = $("#id_bodegaEmpresa").val();
		if(id_bodegaEmpresa == 0)"""),format.raw/*290.28*/("""{"""),format.raw/*290.29*/("""
			"""),format.raw/*291.4*/("""flag = false;
			alertify.alert('DEBE ASOCIAR LA ORDEN A """),_display_(/*292.45*/mapDiccionario/*292.59*/.get("BODEGA")),format.raw/*292.73*/("""/PROYECTO', function () """),format.raw/*292.97*/("""{"""),format.raw/*292.98*/("""
				"""),format.raw/*293.5*/("""document.getElementById("nombreBodega").focus();
				$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*296.8*/("""}"""),format.raw/*296.9*/(""");
		"""),format.raw/*297.3*/("""}"""),format.raw/*297.4*/("""
		"""),format.raw/*298.3*/("""return(flag);
	"""),format.raw/*299.2*/("""}"""),format.raw/*299.3*/("""
	
	"""),format.raw/*301.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*302.38*/("""{"""),format.raw/*302.39*/("""
		"""),format.raw/*303.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*305.35*/("""{"""),format.raw/*305.36*/("""
			"""),format.raw/*306.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*307.3*/("""}"""),format.raw/*307.4*/("""
		"""),format.raw/*308.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*309.45*/("""{"""),format.raw/*309.46*/("""
			"""),format.raw/*310.4*/("""if (extArray[i] == extencion) """),format.raw/*310.34*/("""{"""),format.raw/*310.35*/(""" """),format.raw/*310.36*/("""allowSubmit = true; break; """),format.raw/*310.63*/("""}"""),format.raw/*310.64*/("""
		"""),format.raw/*311.3*/("""}"""),format.raw/*311.4*/("""
		"""),format.raw/*312.3*/("""if (allowSubmit) """),format.raw/*312.20*/("""{"""),format.raw/*312.21*/("""
			"""),format.raw/*313.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*315.26*/("""{"""),format.raw/*315.27*/("""
				"""),format.raw/*316.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*319.4*/("""}"""),format.raw/*319.5*/("""else"""),format.raw/*319.9*/("""{"""),format.raw/*319.10*/("""
				"""),format.raw/*320.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*322.4*/("""}"""),format.raw/*322.5*/("""
		"""),format.raw/*323.3*/("""}"""),format.raw/*323.4*/("""else"""),format.raw/*323.8*/("""{"""),format.raw/*323.9*/("""
			"""),format.raw/*324.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*328.3*/("""}"""),format.raw/*328.4*/("""
	"""),format.raw/*329.2*/("""}"""),format.raw/*329.3*/("""
	

	
"""),format.raw/*333.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,formOt:forms.FormOt,listBodegas:List[List[String]],tabla:String,listProyectos:List[tables.Proyecto],listRegiones:List[tables.Regiones]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,formOt,listBodegas,tabla,listProyectos,listRegiones)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,forms.FormOt,List[List[String]],String,List[tables.Proyecto],List[tables.Regiones]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,formOt,listBodegas,tabla,listProyectos,listRegiones) => apply(mapDiccionario,mapPermiso,userMnu,formOt,listBodegas,tabla,listProyectos,listRegiones)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otHacer.scala.html
                  HASH: 7d69fd55cccce0760dc0ceb6a899b5d8a2af1173
                  MATRIX: 1089->1|1424->243|1451->245|1467->253|1506->255|1535->259|1603->307|1637->315|1697->349|1766->397|1797->401|1880->456|1909->457|1942->463|2649->1143|2677->1144|2708->1148|2838->1252|2957->1349|2988->1353|3128->1466|3151->1480|3196->1504|3581->1862|3596->1868|3623->1874|3732->1956|3747->1962|3783->1976|3966->2132|3989->2146|4035->2170|4204->2312|4219->2318|4249->2327|4717->2768|4732->2774|4761->2782|4814->2808|4829->2814|4858->2822|5058->2995|5073->3001|5112->3018|5290->3169|5313->3183|5348->3197|5455->3277|5495->3279|5538->3294|5634->3363|5649->3369|5683->3382|5744->3424|5783->3425|5824->3438|6088->3671|6127->3682|6297->3825|6312->3831|6345->3843|6670->4141|6685->4147|6719->4160|7999->5412|8015->5418|8051->5432|8339->5693|8372->5704|8406->5710|8861->6137|8885->6151|8917->6161|9345->6561|9369->6575|9405->6589|9876->7032|9900->7046|9936->7060|10092->7188|10135->7214|10175->7215|10214->7225|10269->7252|10285->7258|10314->7265|10387->7310|10403->7316|10433->7324|10524->7387|10540->7393|10569->7400|10600->7403|10616->7409|10645->7416|10726->7469|10742->7475|10771->7482|10846->7529|10862->7535|10891->7542|10954->7573|10990->7581|12368->8931|12413->8959|12453->8960|12492->8970|12549->8999|12565->9005|12590->9008|12681->9071|12697->9077|12722->9080|12753->9083|12769->9089|12800->9098|12881->9151|12897->9157|12926->9164|12989->9195|13025->9203|13374->9521|13406->9525|13498->9588|13528->9589|13562->9595|13625->9629|13655->9630|13690->9637|13862->9780|13892->9781|13932->9792|14038->9869|14068->9870|14101->9875|14130->9876|14159->9877|14230->9919|14260->9920|14295->9927|14467->10070|14497->10071|14537->10082|14643->10159|14673->10160|14706->10165|14735->10166|14764->10167|14864->10239|14893->10240|14963->10281|14993->10282|15024->10285|15185->10418|15214->10419|15246->10423|15314->10462|15344->10463|15375->10466|15539->10602|15568->10603|15600->10607|15654->10632|15684->10633|15715->10636|15881->10773|15911->10774|15943->10778|16029->10836|16053->10850|16089->10864|16142->10888|16172->10889|16205->10894|16348->11009|16377->11010|16410->11015|16439->11016|16470->11019|16513->11034|16542->11035|16574->11039|16765->11201|16795->11202|16826->11205|16935->11285|16965->11286|16997->11290|17070->11335|17099->11336|17130->11339|17268->11448|17298->11449|17330->11453|17389->11483|17419->11484|17449->11485|17505->11512|17535->11513|17566->11516|17595->11517|17626->11520|17672->11537|17702->11538|17734->11542|17879->11658|17909->11659|17942->11664|18146->11840|18175->11841|18207->11845|18237->11846|18270->11851|18382->11935|18411->11936|18442->11939|18471->11940|18503->11944|18532->11945|18564->11949|18781->12138|18810->12139|18840->12141|18869->12142|18903->12148
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|40->9|41->10|41->10|42->11|43->12|43->12|44->13|58->27|58->27|59->28|64->33|64->33|65->34|66->35|66->35|66->35|73->42|73->42|73->42|74->43|74->43|74->43|77->46|77->46|77->46|81->50|81->50|81->50|93->62|93->62|93->62|94->63|94->63|94->63|99->68|99->68|99->68|102->71|102->71|102->71|104->73|104->73|105->74|106->75|106->75|106->75|108->77|108->77|109->78|115->84|116->85|121->90|121->90|121->90|128->97|128->97|128->97|161->130|161->130|161->130|173->142|173->142|174->143|183->152|183->152|183->152|194->163|194->163|194->163|204->173|204->173|204->173|210->179|210->179|210->179|211->180|211->180|211->180|211->180|212->181|212->181|212->181|213->182|213->182|213->182|213->182|213->182|213->182|214->183|214->183|214->183|215->184|215->184|215->184|217->186|218->187|253->222|253->222|253->222|254->223|254->223|254->223|254->223|255->224|255->224|255->224|255->224|255->224|255->224|256->225|256->225|256->225|258->227|259->228|276->245|280->249|282->251|282->251|284->253|284->253|284->253|285->254|288->257|288->257|289->258|290->259|290->259|291->260|291->260|291->260|293->262|293->262|294->263|297->266|297->266|298->267|299->268|299->268|300->269|300->269|300->269|303->272|303->272|305->274|305->274|306->275|309->278|309->278|311->280|311->280|311->280|312->281|315->284|315->284|317->286|317->286|317->286|318->287|321->290|321->290|322->291|323->292|323->292|323->292|323->292|323->292|324->293|327->296|327->296|328->297|328->297|329->298|330->299|330->299|332->301|333->302|333->302|334->303|336->305|336->305|337->306|338->307|338->307|339->308|340->309|340->309|341->310|341->310|341->310|341->310|341->310|341->310|342->311|342->311|343->312|343->312|343->312|344->313|346->315|346->315|347->316|350->319|350->319|350->319|350->319|351->320|353->322|353->322|354->323|354->323|354->323|354->323|355->324|359->328|359->328|360->329|360->329|364->333
                  -- GENERATED --
              */
          