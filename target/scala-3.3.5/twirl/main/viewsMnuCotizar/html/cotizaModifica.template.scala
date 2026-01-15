
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

object cotizaModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template17[Map[String,String],Map[String,String],utilities.UserMnu,forms.FormCotiza,List[tables.Cliente],List[tables.Proyecto],String,List[tables.Regiones],String,List[tables.Sucursal],List[tables.Comercial],String,List[tables.CotizaSolucion],tables.Cotizacion,Double,String,List[tables.Dibujante],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
formCotiza: forms.FormCotiza, listClientes: List[tables.Cliente], listProyectos: List[tables.Proyecto], numDecParaTot: String, listRegiones: List[tables.Regiones],
jsonListUnTiempo: String,
listSucursal: List[tables.Sucursal], listComercial: List[tables.Comercial], 
jsonDetalle: String, listSoluciones: List[tables.CotizaSolucion], cotizacion: tables.Cotizacion, iva: Double, jsonListMoneda: String,
listDibujantes: List[tables.Dibujante]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*8.1*/("""

"""),_display_(/*10.2*/main("")/*10.10*/ {_display_(Seq[Any](format.raw/*10.12*/("""
"""),format.raw/*11.1*/("""<div id="bloquear2" class="blocker" style="display:none;"><br><br><br><br><br><br><h1>Se esta actualizando... </h1></div>
"""),_display_(/*12.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*12.50*/("""
"""),format.raw/*13.1*/("""<div id="bloquear2" class="blocker" style="display:none;"><br><br><br><br><br><br><h1>Se esta actualizando... </h1></div>
	"""),_display_(/*14.3*/modalEquipoDescripcion()),format.raw/*14.27*/("""

	"""),format.raw/*16.2*/("""<!-- MODAL CREA CLIENTES -->
		"""),_display_(/*17.4*/modalClienteNuevo(mapDiccionario, listRegiones)),format.raw/*17.51*/("""
		"""),format.raw/*18.3*/("""<script>
			const clienteGrabaAjax = (id_cliente) =>"""),format.raw/*19.44*/("""{"""),format.raw/*19.45*/("""
				"""),format.raw/*20.5*/("""$('#id_cliente').val(id_cliente);
				$('#rutCliente').val($("#clienteRut").val());
				$('#nombreCliente').val($("#clienteNickName").val());
				let tabla = document.getElementById('tablaListaClientes');
				let row = tabla.insertRow(tabla.rows.length);
				let cell = row.insertCell(0);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#clienteRut").val();
				cell = row.insertCell(1);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#clienteNickName").val();
				cell = row.insertCell(2);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#clienteNombre").val();
				row.setAttribute("onClick", "$('#id_cliente').val('" + id_cliente + "'); $('#rutCliente').val('" + $("#clienteRut").val() + "');$('#nombreCliente').val('" + $("#clienteNickName").val() + "');");
				row.setAttribute("data-dismiss", "modal");
			"""),format.raw/*36.4*/("""}"""),format.raw/*36.5*/("""
		"""),format.raw/*37.3*/("""</script>
	<!-- FIN MODAL CREA CLIENTES -->
	
	<!-- MODAL CREA PROYECTOS -->
		"""),_display_(/*41.4*/modalProyectoNuevo(mapDiccionario, listRegiones)),format.raw/*41.52*/("""
		"""),format.raw/*42.3*/("""<script>
			const proyectoGrabaAjax = (id_proyecto) =>"""),format.raw/*43.46*/("""{"""),format.raw/*43.47*/("""
				"""),format.raw/*44.5*/("""$('#id_proyecto').val(id_proyecto);
				$('#nombreProyecto').val($("#proyectoNickName").val());
				let tabla = document.getElementById('tablaListaProyectos');
				let row = tabla.insertRow(tabla.rows.length);
				let cell = row.insertCell(0);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#proyectoNickName").val();
				cell = row.insertCell(1);
				cell.style.textAlign = "left";
				cell.innerHTML = $("#proyectoNombre").val();
				cell = row.insertCell(2);
				cell.style.textAlign = "left";
				cell.innerHTML = "";
				row.setAttribute("onClick", "$('#id_proyecto').val('" + id_proyecto + "'); $('#nombreProyecto').val('" + $("#proyectoNickName").val() + "');");
				row.setAttribute("data-dismiss", "modal");
			"""),format.raw/*59.4*/("""}"""),format.raw/*59.5*/("""
		"""),format.raw/*60.3*/("""</script>
	<!-- FIN MODAL CREA PROYECTOS -->
	
	<form action="/cotizarUpdate/" enctype="multipart/form-data" method="POST" onsubmit="return validarForm();">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*65.5*/barraTitulo(mapDiccionario, "MODIFICAR COTIZACION", "")),format.raw/*65.60*/("""
			"""),format.raw/*66.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="230px">
									<input type="hidden" id="id_cotizacion" name="id_cotizacion" value=""""),_display_(/*72.79*/formCotiza/*72.89*/.id_cotizacion),format.raw/*72.103*/("""">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Cotizacion</span>
								  		</div>
								  		<input type="text" class="form-control left"
								  				name="numeroCoti"
												value = """"),_display_(/*79.23*/formCotiza/*79.33*/.numeroCoti),format.raw/*79.44*/(""""
												style="width:100px;" 
												onkeydown="return ingresoInt(window.event)"
												onchange="if(value.trim()=='')"""),format.raw/*82.43*/("""{"""),format.raw/*82.44*/("""value='"""),_display_(/*82.52*/formCotiza/*82.62*/.numeroCoti),format.raw/*82.73*/("""';"""),format.raw/*82.75*/("""}"""),format.raw/*82.76*/("""else"""),format.raw/*82.80*/("""{"""),format.raw/*82.81*/("""validarNumero(value);"""),format.raw/*82.102*/("""}"""),format.raw/*82.103*/("""">
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Fecha</span>
								  		</div>
								  		<input type="date" class="form-control center"
								  			name="fechaCoti" 
								  			id="fechaCoti"
								  			onblur="if(!limitaFecha(value,720,10)) value='"""),_display_(/*93.61*/formCotiza/*93.71*/.fechaCoti),format.raw/*93.81*/("""';"
								  			value=""""),_display_(/*94.22*/formCotiza/*94.32*/.fechaCoti),format.raw/*94.42*/(""""
						        			required>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*101.65*/mapDiccionario/*101.79*/.get("RUT")),format.raw/*101.90*/(""" """),format.raw/*101.91*/("""Cliente</span>
								  		</div>
								  		<input type="hidden" id="id_cliente" name="id_cliente" value=""""),_display_(/*103.76*/formCotiza/*103.86*/.id_cliente),format.raw/*103.97*/("""">
										"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*104.69*/ {_display_(Seq[Any](format.raw/*104.71*/("""
											"""),_display_(if(formCotiza.id_cliente>0)/*105.40*/{_display_(Seq[Any](format.raw/*105.41*/("""
												"""),format.raw/*106.13*/("""<input type="text" class="form-control"
			  										id="rutCliente"
			  										value=""""),_display_(/*108.24*/formCotiza/*108.34*/.rutCliente),format.raw/*108.45*/(""""
			  										readonly>
											""")))}else/*110.17*/{_display_(Seq[Any](format.raw/*110.18*/("""
												"""),format.raw/*111.13*/("""<input type="text" class="form-control"
			  										id="rutCliente"
			  										value=""""),_display_(/*113.24*/formCotiza/*113.34*/.rutCliente),format.raw/*113.45*/("""" 
			  										style="background:white"
			  										onclick="$('#listaCliente').modal('show');"
			  										readonly>
											""")))}),format.raw/*117.13*/("""
										""")))}else/*118.16*/{_display_(Seq[Any](format.raw/*118.17*/("""
											"""),format.raw/*119.12*/("""<input type="text" class="form-control"
		  										id="rutCliente"
		  										value=""""),_display_(/*121.23*/formCotiza/*121.33*/.rutCliente),format.raw/*121.44*/("""" 
		  										style="background:white"
		  										onclick="$('#listaCliente').modal('show');"
		  										readonly>
										""")))}),format.raw/*125.12*/("""
									"""),format.raw/*126.10*/("""</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Cliente</span>
								  		</div>
											"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*133.70*/ {_display_(Seq[Any](format.raw/*133.72*/("""
												"""),_display_(if(formCotiza.id_cliente>0)/*134.41*/{_display_(Seq[Any](format.raw/*134.42*/("""
													"""),format.raw/*135.14*/("""<input type="text" class="form-control left"
													id="nombreCliente"
													value=""""),_display_(/*137.22*/formCotiza/*137.32*/.nickCliente),format.raw/*137.44*/(""""
													required
													readonly>
												""")))}else/*140.18*/{_display_(Seq[Any](format.raw/*140.19*/("""
													"""),format.raw/*141.14*/("""<input type="text" class="form-control left"
													id="nombreCliente"
													value=""""),_display_(/*143.22*/formCotiza/*143.32*/.nickCliente),format.raw/*143.44*/(""""
													style="background:white"
													onclick='$("#listaCliente").modal("show")'
													required
													readonly>
												""")))}),format.raw/*148.14*/("""
											""")))}else/*149.17*/{_display_(Seq[Any](format.raw/*149.18*/("""
												"""),format.raw/*150.13*/("""<input type="text" class="form-control left"
													id="nombreCliente"
													value=""""),_display_(/*152.22*/formCotiza/*152.32*/.nickCliente),format.raw/*152.44*/(""""
													style="background:white"
													onclick='$("#listaCliente").modal("show")'
													required
													readonly>
											""")))}),format.raw/*157.13*/("""
									"""),format.raw/*158.10*/("""</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Proyecto</span>
								  		</div>
										<input type="hidden" id="id_proyecto" name="id_proyecto" value=""""),_display_(/*165.76*/formCotiza/*165.86*/.id_proyecto),format.raw/*165.98*/("""">
										"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*166.69*/ {_display_(Seq[Any](format.raw/*166.71*/("""
											"""),format.raw/*167.12*/("""<input type="text" class="form-control left"
												id="nombreProyecto"
												value=""""),_display_(/*169.21*/formCotiza/*169.31*/.nickProyecto),format.raw/*169.44*/(""""
												readonly>
										""")))}else/*171.16*/{_display_(Seq[Any](format.raw/*171.17*/("""
											"""),format.raw/*172.12*/("""<input type="text" class="form-control left"
												id="nombreProyecto"
												value=""""),_display_(/*174.21*/formCotiza/*174.31*/.nickProyecto),format.raw/*174.44*/(""""
												style="background:white"
												onclick='$("#listaProyecto").modal("show")'
												readonly>
										""")))}),format.raw/*178.12*/("""
									"""),format.raw/*179.10*/("""</div>
								</td>
								<td style="text-align:center;" width="70px">
									<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
										"""),_display_(if(formCotiza.cotizacionPDF.equals("0"))/*183.52*/{_display_(Seq[Any](format.raw/*183.53*/("""
											"""),format.raw/*184.12*/("""<div id="txtBtn">Adjuntar</div>
										""")))}else/*185.16*/{_display_(Seq[Any](format.raw/*185.17*/("""
											"""),format.raw/*186.12*/("""<div id="txtBtn">Cambiar</div>
										""")))}),format.raw/*187.12*/("""
				    					"""),format.raw/*188.14*/("""<input type="file" id="docAdjunto" name="docAdjunto" onchange="LimitAttach(this.form, this.form.docAdjunto.value);">
									</span>
								</td>
								<td style="text-align:left;">
									"""),_display_(if(formCotiza.cotizacionPDF.equals("0"))/*192.51*/{_display_(Seq[Any](format.raw/*192.52*/("""
										"""),format.raw/*193.11*/("""--
									""")))}else/*194.15*/{_display_(Seq[Any](format.raw/*194.16*/("""
										"""),format.raw/*195.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*195.52*/formCotiza/*195.62*/.cotizacionPDF),format.raw/*195.76*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*198.11*/("""
								"""),format.raw/*199.9*/("""</td>
								
								
							</tr>
							<tr>
							
							
								
								<td align="center"  colspan="2">
									<input type="hidden" id="id_sucursal" name="id_sucursal" value=""""),_display_(/*208.75*/cotizacion/*208.85*/.getId_sucursal()),format.raw/*208.102*/("""">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Sucursal</span>
								  		</div>
										"""),_display_(if(mapPermiso.get("cambiarSucursal")!=null)/*213.55*/{_display_(Seq[Any](format.raw/*213.56*/("""
											"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*214.57*/{_display_(Seq[Any](format.raw/*214.58*/("""
												"""),format.raw/*215.13*/("""<select id="selSucursal" class="custom-select"  style="width: 80px;" onchange = "$('#id_sucursal').val(value); actualizaComerciales(value);">
													<option value=""""),_display_(/*216.30*/cotizacion/*216.40*/.getId_sucursal()),format.raw/*216.57*/("""">"""),_display_(/*216.60*/cotizacion/*216.70*/.getNameSucursal()),format.raw/*216.88*/("""</option>
													"""),_display_(/*217.15*/for(lista <- listSucursal) yield /*217.41*/{_display_(Seq[Any](format.raw/*217.42*/("""
														"""),format.raw/*218.15*/("""<option value=""""),_display_(/*218.31*/lista/*218.36*/.getId()),format.raw/*218.44*/("""">"""),_display_(/*218.47*/lista/*218.52*/.getNombre()),format.raw/*218.64*/("""</option>
													""")))}),format.raw/*219.15*/("""
												"""),format.raw/*220.13*/("""</select>
											""")))}else/*221.17*/{_display_(Seq[Any](format.raw/*221.18*/("""
												"""),format.raw/*222.13*/("""<select id="selSucursal" class="custom-select"  style="width: 80px;" onchange = "$('#id_sucursal').val(value);">
													<option value=""""),_display_(/*223.30*/cotizacion/*223.40*/.getId_sucursal()),format.raw/*223.57*/("""">"""),_display_(/*223.60*/cotizacion/*223.70*/.getNameSucursal()),format.raw/*223.88*/("""</option>
													"""),_display_(/*224.15*/for(lista <- listSucursal) yield /*224.41*/{_display_(Seq[Any](format.raw/*224.42*/("""
														"""),format.raw/*225.15*/("""<option value=""""),_display_(/*225.31*/lista/*225.36*/.getId()),format.raw/*225.44*/("""">"""),_display_(/*225.47*/lista/*225.52*/.getNombre()),format.raw/*225.64*/("""</option>
													""")))}),format.raw/*226.15*/("""
												"""),format.raw/*227.13*/("""</select>
											""")))}),format.raw/*228.13*/("""
										""")))}else/*229.16*/{_display_(Seq[Any](format.raw/*229.17*/("""
											"""),format.raw/*230.12*/("""<input id="selSucursal" type="text" class="form-control left"
												value = """"),_display_(/*231.23*/cotizacion/*231.33*/.getNameSucursal()),format.raw/*231.51*/(""""
												readonly>
										""")))}),format.raw/*233.12*/("""
									"""),format.raw/*234.10*/("""</div>
								</td>
								
								
								
								<td colspan="20" rowspan="6">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Observaciones</span>
								  		</div>
	  									<textarea class="form-control" rows="9"
	  										name="observaciones" 
	  										autocomplete="off">"""),_display_(/*246.34*/formCotiza/*246.44*/.observaciones),format.raw/*246.58*/("""</textarea>
									</div>
								</td>
							</tr>
							<tr>
							
							
								<td align="center"  colspan="2">
									<div id="actualComerciales">
										<input type="hidden" id="id_comercial" name="id_comercial" value=""""),_display_(/*255.78*/cotizacion/*255.88*/.getId_comercial()),format.raw/*255.106*/("""">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">Comercial</span>
									  		</div>
											"""),_display_(if(mapPermiso.get("cambiarComercial")!=null)/*260.57*/{_display_(Seq[Any](format.raw/*260.58*/("""
												"""),format.raw/*261.13*/("""<select id="selComercial" class="custom-select"  style="width: 80px;" onchange="$('#id_comercial').val(value)">
													<option value=""""),_display_(/*262.30*/cotizacion/*262.40*/.getId_comercial()),format.raw/*262.58*/("""">"""),_display_(/*262.61*/cotizacion/*262.71*/.getNameComercial()),format.raw/*262.90*/("""</option>
													"""),_display_(/*263.15*/for(lista <- listComercial) yield /*263.42*/{_display_(Seq[Any](format.raw/*263.43*/("""
														"""),format.raw/*264.15*/("""<option value=""""),_display_(/*264.31*/lista/*264.36*/.getId()),format.raw/*264.44*/("""">"""),_display_(/*264.47*/lista/*264.52*/.getNameUsuario()),format.raw/*264.69*/("""</option>
													""")))}),format.raw/*265.15*/("""
												"""),format.raw/*266.13*/("""</select>
											""")))}else/*267.17*/{_display_(Seq[Any](format.raw/*267.18*/("""
												"""),format.raw/*268.13*/("""<input id="selComercial" type="text" class="form-control left"
													value = """"),_display_(/*269.24*/cotizacion/*269.34*/.getNameComercial()),format.raw/*269.53*/(""""
													readonly>
											""")))}),format.raw/*271.13*/("""
										"""),format.raw/*272.11*/("""</div>
									</div>
								</td>
									
									
									
							</tr>
							<tr>
								<td align="center"  colspan="2">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Tipo de Solucion</span>
										</div>
										<select id="selSolucion" class="custom-select"  style="width: 80px;" name="id_cotizaSolucion">
											<option value=""""),_display_(/*286.28*/cotizacion/*286.38*/.getId_cotizaSolucion()),format.raw/*286.61*/("""">"""),_display_(/*286.64*/cotizacion/*286.74*/.getNameCotizaSolucion()),format.raw/*286.98*/("""</option>
											"""),_display_(/*287.13*/for(lista <- listSoluciones) yield /*287.41*/{_display_(Seq[Any](format.raw/*287.42*/("""
												"""),format.raw/*288.13*/("""<option value=""""),_display_(/*288.29*/lista/*288.34*/.getId()),format.raw/*288.42*/("""">"""),_display_(/*288.45*/lista/*288.50*/.getSolucion()),format.raw/*288.64*/("""</option>
											""")))}),format.raw/*289.13*/("""
										"""),format.raw/*290.11*/("""</select>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Fecha Probable</span>
										</div>
										<input type="date" class="form-control center"
											   name="fechaProbable"
											   id="fechaProbable"
											   onblur="if(!limitaFecha(value,720,720)) value='"""),_display_(/*303.63*/cotizacion/*303.73*/.getFechaProbable()),format.raw/*303.92*/("""';"
											   value=""""),_display_(/*304.23*/cotizacion/*304.33*/.getFechaProbable()),format.raw/*304.52*/(""""
											   required>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"  colspan="2">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Dibujante/Proyectista</span>
										</div>
										<input type="hidden" id="id_dibujante" name="id_dibujante" value="0">
										<select id="selSolucion" class="custom-select"  style="width: 80px;" onchange="$('#id_dibujante').val(value)">
											<option value=""""),_display_(/*317.28*/cotizacion/*317.38*/.getId_dibujante()),format.raw/*317.56*/("""">"""),_display_(/*317.59*/cotizacion/*317.69*/.getNombreDibujante()),format.raw/*317.90*/("""</option>
											"""),_display_(/*318.13*/for(lista <- listDibujantes) yield /*318.41*/{_display_(Seq[Any](format.raw/*318.42*/("""
												"""),format.raw/*319.13*/("""<option value=""""),_display_(/*319.29*/lista/*319.34*/.getId()),format.raw/*319.42*/("""">"""),_display_(/*319.45*/lista/*319.50*/.getNombre()),format.raw/*319.62*/("""</option>
											""")))}),format.raw/*320.13*/("""
										"""),format.raw/*321.11*/("""</select>
									</div>
								</td>
							</tr>
							<tr>
								<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*326.84*/formCotiza/*326.94*/.id_bodegaEmpresa),format.raw/*326.111*/("""">
								"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*327.67*/ {_display_(Seq[Any](format.raw/*327.69*/("""
									"""),format.raw/*328.10*/("""<td align="center"  colspan="2">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*331.66*/mapDiccionario/*331.80*/.get("BODEGA")),format.raw/*331.94*/("""/PROYECTO</span>
									  		</div>
									  		<input type="text" class="form-control left"
													value = """"),_display_(/*334.24*/formCotiza/*334.34*/.nombreBodega),format.raw/*334.47*/(""""
													readonly>
										</div>
									</td>
								""")))} else {null} ),format.raw/*338.10*/("""
							"""),format.raw/*339.8*/("""</tr>
							<tr>
								<input type="hidden" id="id_bodegaEmpresa" name="id_bodegaEmpresa" value=""""),_display_(/*341.84*/formCotiza/*341.94*/.id_bodegaEmpresa),format.raw/*341.111*/("""">
								"""),_display_(if(mapPermiso.get("parametro.cotizaPorTasa").equals("1"))/*342.67*/ {_display_(Seq[Any](format.raw/*342.69*/("""
									"""),format.raw/*343.10*/("""<td align="center"  colspan="2">
										<div class="input-group">
									  		<div class="input-group-prepend">
									    		<span class="input-group-text" id="basic-addon1">"""),_display_(/*346.66*/mapDiccionario/*346.80*/.get("BODEGA")),format.raw/*346.94*/("""/PROYECTO</span>
									  		</div>
									  		<input type="text" class="form-control left"
													value = """"),_display_(/*349.24*/formCotiza/*349.34*/.nombreBodega),format.raw/*349.47*/(""""
													readonly>
										</div>
									</td>
								""")))} else {null} ),format.raw/*353.10*/("""
							"""),format.raw/*354.8*/("""</tr>
						</thead>
					</table>
					<hr>
					<div id='modalTasaGlobal' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
						<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
							<div class='modal-content'>
								<div class='modal-header'>
								        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
								          <span aria-hidden='true'>&times;</span>
								        </button>
								</div>
								<div class='modal-body'>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">APLICAR TASA GLOBAL</span>
								  		</div>
								  		<input type="text" class="form-control center"
											id="tasaGlobal"
											value="0.00 %"
											onfocus="value = value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="value = formatPorcentaje(value); if(value=='') """),format.raw/*377.69*/("""{"""),format.raw/*377.70*/("""value='0.00 %';"""),format.raw/*377.85*/("""}"""),format.raw/*377.86*/("""">
									</div>
									<br>
									<div class='row'>
										<div class='col-sm-12' style='text-align:center'>
											<input type="button" class="btn btn-sm btn-primary" value='Aplicar' onclick='$("#modalTasaGlobal").modal("hide"); aplicarTasaGlobal();'>
											<input type='button' class='btn btn-sm btn-warning' value='Cancelar' data-dismiss='modal'>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div id='modalPermGlobal' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
						<div class='modal-dialog modal-dialog-scrollable' role='document' style="width:300px">
							<div class='modal-content'>
								<div class='modal-header'>
								        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
								          <span aria-hidden='true'>&times;</span>
								        </button>
								</div>
								<div class='modal-body'>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">APLICAR PERMANENCIA GLOBAL</span>
								  		</div>
								  		<input type="text" class="form-control center"
											id="permGlobal"
											value="0.00"
											onfocus="value = value.replace(/,/g,'')"
											onkeydown="return ingresoDouble(window.event)"
											autocomplete="off"
											onchange="value = formatStandar(value,2); if(value=='') """),format.raw/*410.68*/("""{"""),format.raw/*410.69*/("""value='0.00';"""),format.raw/*410.82*/("""}"""),format.raw/*410.83*/("""">
									</div>
									<br>
									<div class='row'>
										<div class='col-sm-12' style='text-align:center'>
											<input type="button" class="btn btn-sm btn-primary" value='Aplicar' onclick='$("#modalPermGlobal").modal("hide"); aplicarPermGlobal();'>
											<input type='button' class='btn btn-sm btn-warning' value='Cancelar' data-dismiss='modal'>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					
					"""),_display_(if( ! (mapPermiso.get("parametro.cotizar_bloqueaVtaReposi") !=null && mapPermiso.get("parametro.cotizar_bloqueaVtaReposi") == "1") )/*425.139*/{_display_(Seq[Any](format.raw/*425.140*/("""
						"""),format.raw/*426.7*/("""<div class="noprint" align="center">
							<div id="importar" class="row justify-content-md-center" style="display: true;">
								<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
									<input id="btnCopiaCoti" type="button"  value="Restablecer Precios" class="btn btn-info btn-sm rounded-pill btn-block" 
										onclick="location.reload();">&nbsp;&nbsp;&nbsp;
								</div>
							</div>
						</div>
					""")))} else {null} ),format.raw/*434.7*/("""
					
					"""),format.raw/*436.6*/("""<div class="noprint" align="center">
						<div id="importar" class="row justify-content-md-center" style="display: none;">
							<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<input id="btnCopiaCoti" type="button"  value="Copiar desde otra cotización" class="btn btn-warning btn-sm rounded-pill btn-block" 
									onclick="listadoCotizaciones()">&nbsp;&nbsp;&nbsp;
							</div>
							<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<input id="btnCargaExcel" type="button"  value="Copiar desde un Excel" class="btn btn-info btn-sm rounded-pill btn-block" 
									onclick="$('#modalCargaMasiva').modal('show');">&nbsp;&nbsp;&nbsp;
							</div>
							
							

						"""),_display_(if(mapPermiso.get("parametro.coti8columnas")!=null && mapPermiso.get("parametro.coti8columnas").equals("1"))/*449.116*/ {_display_(Seq[Any](format.raw/*449.118*/("""
							"""),format.raw/*450.8*/("""<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<input id="btnImport8column" type="button"  value="Import 8 Columnas Excel" class="btn btn-primary btn-sm rounded-pill btn-block" 
									onclick="$('#modalImport8column').modal('show');">&nbsp;&nbsp;&nbsp;
							</div>
						""")))} else {null} ),format.raw/*454.8*/("""
			
			
			
						"""),format.raw/*458.7*/("""</div>
					</div>
					
					
					
					<div id="tblPrincipal"></div>
				</div>
			</div>
			
			
			<div class="noprint" align="left">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  id="verifica" value='Verificar' class="btn btn-success btn-sm rounded-pill btn-block" onclick="verificaCotizacion();" >
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="button"  id="modifica" value='Modificar' class="btn btn-warning btn-sm rounded-pill btn-block" onclick="modificaCotizacion();" style="visibility:hidden">
					</div>
					<div class="col-xs-5 col-sm-1 col-md-1 col-lg-1">
						<input type="submit"  id="aplica" value='Aplicar Cambios' class="btn btn-primary btn-sm rounded-pill btn-block" style="visibility:hidden">
					</div>
				</div>
			</div>
			
		</div>
	</form>
	
	<div id='listaCliente' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR CLIENTE</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div class="noprint" align="center">
						<a href="#" onclick="$('#listaCliente').modal('hide'); $('#modalClienteNuevo').modal('show');">
							<kbd style="background-color: #73C6B6">Agregar Nuevo</kbd>
						</a>
					</div>
					<table id="tablaListaClientes" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>"""),_display_(/*506.14*/mapDiccionario/*506.28*/.get("RUT")),format.raw/*506.39*/("""</TH>
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*512.9*/for(lista1 <- listClientes) yield /*512.36*/{_display_(Seq[Any](format.raw/*512.37*/("""
								"""),format.raw/*513.9*/("""<TR onClick="$('#id_cliente').val("""),_display_(/*513.44*/lista1/*513.50*/.getId()),format.raw/*513.58*/("""); $('#rutCliente').val('"""),_display_(/*513.84*/lista1/*513.90*/.getRut()),format.raw/*513.99*/("""');$('#nombreCliente').val('"""),_display_(/*513.128*/lista1/*513.134*/.getNickName()),format.raw/*513.148*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*514.41*/lista1/*514.47*/.getRut()),format.raw/*514.56*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*515.41*/lista1/*515.47*/.getNickName()),format.raw/*515.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*516.41*/lista1/*516.47*/.getNombre()),format.raw/*516.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*518.10*/("""
						"""),format.raw/*519.7*/("""</tbody>
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
								<TH>NICK</TH>
								<TH>NOMBRE</TH>
								<TH>"""),_display_(/*551.14*/mapDiccionario/*551.28*/.get("Comuna")),format.raw/*551.42*/("""</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*555.9*/for(lista1 <- listProyectos) yield /*555.37*/{_display_(Seq[Any](format.raw/*555.38*/("""
								"""),format.raw/*556.9*/("""<TR onClick="$('#id_proyecto').val("""),_display_(/*556.45*/lista1/*556.51*/.getId()),format.raw/*556.59*/("""); $('#nombreProyecto').val('"""),_display_(/*556.89*/lista1/*556.95*/.getNickName()),format.raw/*556.109*/("""');" data-dismiss="modal">
									<td  style="text-align:left;">"""),_display_(/*557.41*/lista1/*557.47*/.getNickName()),format.raw/*557.61*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*558.41*/lista1/*558.47*/.getNombre()),format.raw/*558.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*559.41*/lista1/*559.47*/.getComuna()),format.raw/*559.59*/("""</td>
								</TR>
				 			""")))}),format.raw/*561.10*/("""
						"""),format.raw/*562.7*/("""</tbody>
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
	
	<div id='modalStockPorEquipo' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style='max-width: 80% !important;' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>STOCK DISPONIBLE</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="stockPorEquipo"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
""")))}),format.raw/*599.2*/("""



"""),format.raw/*603.1*/("""<script type="text/javascript">

	let factorVta = 1;
	let factorArr = 1;

	let mapMoneda = new Map();

	$(document).ready(function() """),format.raw/*610.31*/("""{"""),format.raw/*610.32*/("""
		
		"""),format.raw/*612.3*/("""let jsonListUnTiempo = """),_display_(/*612.27*/Html(jsonListUnTiempo)),format.raw/*612.49*/(""";
		let jsonDetalle = """),_display_(/*613.22*/Html(jsonDetalle)),format.raw/*613.39*/(""";
		let monedas = "";
		for(var i in jsonListUnTiempo)"""),format.raw/*615.33*/("""{"""),format.raw/*615.34*/("""
			"""),format.raw/*616.4*/("""monedas += "<option value=\""+jsonListUnTiempo[i]["id"]+"\">"+jsonListUnTiempo[i]["nombre"]+"</option>";
		"""),format.raw/*617.3*/("""}"""),format.raw/*617.4*/("""
		
		"""),format.raw/*619.3*/("""let jsonListMoneda = """),_display_(/*619.25*/Html(jsonListMoneda)),format.raw/*619.45*/(""";
		let moneda = "";
		for(var i in jsonListMoneda)"""),format.raw/*621.31*/("""{"""),format.raw/*621.32*/("""
			"""),format.raw/*622.4*/("""moneda += "<option value=\""+jsonListMoneda[i]["id"]+"\">"+jsonListMoneda[i]["nickName"]+"</option>";
			mapMoneda.set(jsonListMoneda[i]["id"], jsonListMoneda[i]["numeroDecimales"]);
		"""),format.raw/*624.3*/("""}"""),format.raw/*624.4*/("""
		
		"""),format.raw/*626.3*/("""let escondeLosM2 = 0;
		"""),_display_(if(mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1"))/*627.110*/ {_display_(Seq[Any](format.raw/*627.112*/("""
			"""),format.raw/*628.4*/("""escondeLosM2 = 1;
		""")))} else {null} ),format.raw/*629.4*/("""
		
		"""),format.raw/*631.3*/("""let tabla = "<table id=\"tablaPrincipal\" class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"
			+ "	<thead style=\"background-color: #eeeeee\">"
			+ "<TR><TH colspan=\"8\" style=\"background-color: white\"></TH>"
			+ "<TH style=\"text-align:center\">";
				if( ! ('"""),_display_(/*635.14*/mapPermiso/*635.24*/.get("parametro.cotizar_bloqueaVtaReposi")),format.raw/*635.66*/("""'!=null && '"""),_display_(/*635.79*/mapPermiso/*635.89*/.get("parametro.cotizar_bloqueaVtaReposi")),format.raw/*635.131*/("""' == "1") ) """),format.raw/*635.143*/("""{"""),format.raw/*635.144*/("""
					"""),format.raw/*636.6*/("""tabla += "<div id=\"titAplicarFactorRepos\">Aplica_Factor</div>"
						+ "<input type=\"text\" class=\"form-control rounded-pill height23px right\""
						+ " style='background-color: #ffc107'"
						+ " id=\"btnAplicarFactorRepos\""
						+ " value=\"1.00\""
						+ " onkeydown=\"return ingresoDouble(window.event)\""
						+ " onfocus=\"value = value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\""
						+ " onchange=\"if(value=='' || value==0) """),format.raw/*643.48*/("""{"""),format.raw/*643.49*/(""""
							+ " value = formatStandar(factorVta,2);"
							+ """"),format.raw/*645.11*/("""}"""),format.raw/*645.12*/("""else"""),format.raw/*645.16*/("""{"""),format.raw/*645.17*/(""""
								+ " aplicarFactorGlobalRepos(value);"
								+ " value = formatStandar(value,2);"
							+ """"),format.raw/*648.11*/("""}"""),format.raw/*648.12*/("""\">";
				"""),format.raw/*649.5*/("""}"""),format.raw/*649.6*/("""
			"""),format.raw/*650.4*/("""tabla += "</TH>"
			+ "<TH style=\"background-color: white\"></TH>"
			+ "<TH style=\"text-align:center\">"
			+ "<br><input id=\"btnAplicarTasa\" type=\"button\"  value=\"Aplica Tasa\" class=\"btn btn-warning btn-sm rounded-pill btn-block\" onclick='$(\"#modalTasaGlobal\").modal(\"show\")'>"
			+ "</TH>"
			+ "<TH style=\"text-align:center\">"
				+ "<div id=\"titAplicarFactorArr\">Aplica_Factor</div><input type=\"text\" class=\"form-control rounded-pill height23px right\""
						+ " style='background-color: #ffc107'"
						+ " id=\"btnAplicarFactorRepos\""
						+ " value=\"1.00\""
						+ " onkeydown=\"return ingresoDouble(window.event)\""
						+ " onfocus=\"value = value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\""
						+ " onchange=\"if(value=='' || value==0) """),format.raw/*662.48*/("""{"""),format.raw/*662.49*/(""""
							+ " value = formatStandar(factorArr,2);"
							+ """"),format.raw/*664.11*/("""}"""),format.raw/*664.12*/("""else"""),format.raw/*664.16*/("""{"""),format.raw/*664.17*/(""""
								+ " aplicarFactorGlobal(value);"
								+ " value = formatStandar(value,2);"
							+ """"),format.raw/*667.11*/("""}"""),format.raw/*667.12*/("""\">"
			+ "</TH>"
			+ "<TH style=\"text-align:center\">"
			+ "<br><input id=\"btnAplicarPerm\" type=\"button\"  value=\"Aplica Perm\" class=\"btn btn-warning btn-sm rounded-pill btn-block\" onclick='$(\"#modalPermGlobal\").modal(\"show\")'>"
			+ "</TH>"
			+ "<TH colspan=\"12\" style=\"background-color: white\"></TH>"
			+ "</TR>"
			+ "<TR>"
			+ "<TH>GRUPO</TH>"
			+ "<TH>CODIGO</TH>"
			+ "<TH style=\"min-width:30%\">EQUIPO</TH>"
			+ "<TH>STOCK</TH>"
			+ "<TH>UN</TH>"
			+ "<TH>CANT</TH>"
			+ "<TH>SOLO<br>VENTA</TH>"
			+ "<TH>MON</TH>"
			+ "<TH>P.UNITARIO<br>VTA/REPOS</TH>"
			+ '<TH>UN<br>"""),_display_(/*684.18*/mapDiccionario/*684.32*/.get("ARR")),format.raw/*684.43*/("""</TH>'
			+ '<TH>TASA<br>"""),_display_(/*685.20*/mapDiccionario/*685.34*/.get("ARR")),format.raw/*685.45*/("""/VTA</TH>'
			+ '<TH>P.UNITARIO<br>"""),_display_(/*686.26*/mapDiccionario/*686.40*/.get("ARR")),format.raw/*686.51*/("""</TH>'
			+ "<TH>PERMAN</TH>"
			+ "<TH>P.TOTAL<br>REPOSICION</TH>"
			+ '<TH>P.TOTAL<br>"""),_display_(/*689.23*/mapDiccionario/*689.37*/.get("ARRIENDO")),format.raw/*689.53*/("""</TH>'
			+ "<TH>P.TOTAL<br>VENTA</TH>"
			+ "<TH>TOT.KG</TH>";


			if(escondeLosM2 == 1) """),format.raw/*694.26*/("""{"""),format.raw/*694.27*/("""
				"""),format.raw/*695.5*/("""tabla += "<TH style = \"display:none\">TOT.M2</TH>";
			"""),format.raw/*696.4*/("""}"""),format.raw/*696.5*/("""else"""),format.raw/*696.9*/("""{"""),format.raw/*696.10*/("""
				"""),format.raw/*697.5*/("""tabla += "<TH>TOT.M2</TH>";
			"""),format.raw/*698.4*/("""}"""),format.raw/*698.5*/("""

			"""),format.raw/*700.4*/("""tabla += "<TH style = \"display:none\">kg</TH>"
				+ "<TH style = \"display:none\">m2</TH>"
				+ "<TH style = \"display:none\">id_equipo</TH>"
				+ "<TH style = \"display:none\">nrodecimal</TH>"
				+ "</TR>"
				+ "</thead>"
				+ "<tbody>";

			for(var i in jsonDetalle)"""),format.raw/*708.29*/("""{"""),format.raw/*708.30*/("""
				"""),format.raw/*709.5*/("""tabla += "<TR>"
					+ "<td style=\"text-align:left;\">"+jsonDetalle[i][23]+"</td>"
					+ "<td style=\"text-align:left;\">"
					+ "<input type=\"hidden\" name=\"id_equipo[]\" value=\""+jsonDetalle[i][0]+"\">"
					+ "<a href=\"#\" onclick=\"equipoDescripcion('"+jsonDetalle[i][0]+"');\">"+jsonDetalle[i][1]+"</a>"
					+ "</td>"
					+ "<td style= \"text-align: left;\"><a href=\"#\" onclick=\"equipoDescripcion('"+jsonDetalle[i][0]+"');\">"+jsonDetalle[i][2]+"</a></td>"
					+ "<td style=\"text-align:center;\">"
					+ "<div class=\"noprint\">"
					+ "<a href=\"#\" onclick=\"vistaStockPorEquipo('"+jsonDetalle[i][0]+"');\">"
					+ "<kbd style=\"background-color: #73C6B6\">stock</kbd>"
					+ "</a>"
					+ "</div>"
					+ "</td>"
					+ "<td style=\"text-align:center;\">"+jsonDetalle[i][3]+"</td>"
					+ "<td style=\"text-align:center;\">"
					+ "<div style=\"display:none\" id=\"hiddencantidad_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][25]+"</div>"
					+ "<input type=\"text\" class=\"cantidad form-control height23px right width80px\""
					+ "name=\"cantidad[]\""
					+ "id=\"cantidad_"+jsonDetalle[i][0]+"\""
					+ "value=\""+jsonDetalle[i][10]+"\""
					+ "onfocus=\"value=value.replace(/,/g,''); cantAux = value;\" "
					+ "onblur = \"value = formatStandar(value, '2');\""
					+ "onkeydown=\"return ingresoDouble(window.event)\""
					+ "autocomplete=\"off\""
					+ "onchange=\"if(value=='') value=0; calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');\">"
					+ "</td>"
					+ "<td style=\"text-align:center;\">"
					+ "<input type=\"hidden\" name=\"esVenta[]\" id=\"esVenta_"+jsonDetalle[i][0]+"\"  value=\""+jsonDetalle[i][11]+"\">";


					if(jsonDetalle[i][11] == "0")"""),format.raw/*740.35*/("""{"""),format.raw/*740.36*/("""
						"""),format.raw/*741.7*/("""tabla += "<input type=\"checkbox\" id=\"checkbox_"+jsonDetalle[i][0]+"\" onchange=\"checkVenta('"+jsonDetalle[i][0]+"'); "
							+ "calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');\">"
					"""),format.raw/*743.6*/("""}"""),format.raw/*743.7*/("""else"""),format.raw/*743.11*/("""{"""),format.raw/*743.12*/("""
						"""),format.raw/*744.7*/("""tabla += "<input type=\"checkbox\" id=\"checkbox_"+jsonDetalle[i][0]+"\" onchange=\"checkVenta('"+jsonDetalle[i][0]+"');"
							+ "calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');\" checked>";
					"""),format.raw/*746.6*/("""}"""),format.raw/*746.7*/("""

					"""),format.raw/*748.6*/("""tabla += "</td>"
						+ "<td style=\"text-align:center;\">" //+jsonDetalle[i][4]
						
						+ "<input type=\"hidden\" id=\"id_moneda_"+jsonDetalle[i][0]+"\" name=\"id_moneda[]\" value=\""+jsonDetalle[i][8]+"\">"
						+ "<select class=\"custom-select\"  style=\"width: 80px;\""
						+ "onchange=\"$('#id_moneda_"+jsonDetalle[i][0]+"').val(value);calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');\">"
						+ "<option value=\""+jsonDetalle[i][8]+"\">"+jsonDetalle[i][4]+"</option>"+moneda
						+"</td>"
						+ "<td style=\"text-align:center;\">"
						+ "<div style=\"display:none\" id=\"hiddenpuVentaRepos_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][26]+"</div>";
						
						if( '"""),_display_(/*759.13*/mapPermiso/*759.23*/.get("parametro.cotizar_bloqueaVtaReposi")),format.raw/*759.65*/("""'!=null && '"""),_display_(/*759.78*/mapPermiso/*759.88*/.get("parametro.cotizar_bloqueaVtaReposi")),format.raw/*759.130*/("""' == "1") """),format.raw/*759.140*/("""{"""),format.raw/*759.141*/("""
							"""),format.raw/*760.8*/("""tabla += "<input type=\"text\" class=\"form-control height23px right width100px\""
							+ "name=\"puVentaRepos[]\""
							+ "id=\"puVentaRepos_"+jsonDetalle[i][0]+"\""
							+ "value=\""+jsonDetalle[i][5]+"\""
							+ " readonly>";
						"""),format.raw/*765.7*/("""}"""),format.raw/*765.8*/("""else"""),format.raw/*765.12*/("""{"""),format.raw/*765.13*/("""
							"""),format.raw/*766.8*/("""tabla += "<input type=\"text\" class=\"form-control height23px right width100px\""
							+ "name=\"puVentaRepos[]\""
							+ "id=\"puVentaRepos_"+jsonDetalle[i][0]+"\""
							+ "value=\""+jsonDetalle[i][5]+"\""
							+ "onfocus=\"value=value.replace(/,/g,'');\" "
							+ "onblur = \"value = formatStandar(value, '"+jsonDetalle[i][20]+"');\""
							+ "onkeydown=\"return ingresoDouble(window.event)\""
							+ "autocomplete=\"off\""
							+ "onchange=\"if(value=='') """),format.raw/*774.36*/("""{"""),format.raw/*774.37*/("""value=0;"""),format.raw/*774.45*/("""}"""),format.raw/*774.46*/(""" """),format.raw/*774.47*/("""else """),format.raw/*774.52*/("""{"""),format.raw/*774.53*/("""calculaTasa(1,'"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"'); calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');"""),format.raw/*774.183*/("""}"""),format.raw/*774.184*/("""\">";
						"""),format.raw/*775.7*/("""}"""),format.raw/*775.8*/("""
						"""),format.raw/*776.7*/("""tabla += "</td>"
						+ "<td style=\"text-align:center;\">"
						+ "<input type=\"hidden\" id=\"id_unidadTiempo_"+jsonDetalle[i][0]+"\" name=\"id_unidadTiempo[]\" value=\""+jsonDetalle[i][9]+"\">"
						+ "<select class=\"custom-select\"  style=\"width: 80px;\""
						+ "onchange=\"$('#id_unidadTiempo_"+jsonDetalle[i][0]+"').val(value);\">"
						+ "<option value=\""+jsonDetalle[i][9]+"\">"+jsonDetalle[i][6]+"</option>"+monedas
						+ "</td>"
						+ "<td>"
						+ "<div style=\"display:none\" id=\"hiddentasaArr_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][27]+"</div>"
						+ "<input type=\"text\" class=\"tasaGlobal form-control height23px right width80px\""
						+ "id=\"tasaArr_"+jsonDetalle[i][0]+"\""
						+ "value=\""+jsonDetalle[i][24]+"\""
						+ "onfocus=\"value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\""
						+ "onblur = \"value = formatPorcentaje(value);\""
						+ "onkeydown=\"return ingresoDouble(window.event)\""
						+ "autocomplete=\"off\""
						+ "onchange=\"if(value=='') """),format.raw/*792.35*/("""{"""),format.raw/*792.36*/("""value='0.00 %';"""),format.raw/*792.51*/("""}"""),format.raw/*792.52*/(""" """),format.raw/*792.53*/("""else """),format.raw/*792.58*/("""{"""),format.raw/*792.59*/("""calculaTasa(2,'"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"'); calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');"""),format.raw/*792.189*/("""}"""),format.raw/*792.190*/("""\">"
						+ "</td>"
						+ "<td style=\"text-align:center;\">"
						+ "<div style=\"display:none\" id=\"hiddenpuArriendo_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][28]+"</div>"
						+ "<input type=\"text\" class=\"factorGlobal form-control height23px right width80px\""
						+ "name=\"puArriendo[]\""
						+ "id=\"puArriendo_"+jsonDetalle[i][0]+"\""
						+ "value=\""+jsonDetalle[i][7]+"\""
						+ "onfocus=\"value=value.replace(/,/g,'');\" "
						+ "onblur = \"value = formatStandar(value, '"+jsonDetalle[i][20]+"');\""
						+ "onkeydown=\"return ingresoDouble(window.event)\""
						+ "autocomplete=\"off\""
						+ "onchange=\"if(value=='') """),format.raw/*804.35*/("""{"""),format.raw/*804.36*/("""value=0;"""),format.raw/*804.44*/("""}"""),format.raw/*804.45*/(""" """),format.raw/*804.46*/("""else """),format.raw/*804.51*/("""{"""),format.raw/*804.52*/("""calculaTasa(1,'"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"'); calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');"""),format.raw/*804.182*/("""}"""),format.raw/*804.183*/("""\">"
						+ "</td>"
						+ "<td style=\"text-align:center;\">"
						+ "<div style=\"display:none\" id=\"hiddenpermanencia_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][29]+"</div>"
						+ "<input type=\"text\" class=\"permGlobal form-control height23px right\""
						+ "name=\"permanencia[]\""
						+ "id=\"permanencia_"+jsonDetalle[i][0]+"\""
						+ "value=\""+jsonDetalle[i][12]+"\""
						+ "onfocus=\"value=value.replace(/,/g,'');\" "
						+ "onblur = \"$('#hiddenpermanencia_"+jsonDetalle[i][0]+"').text(value); value = formatStandar(value, '2');\""
						+ "onkeydown=\"return ingresoDouble(window.event)\""
						+ "autocomplete=\"off\""
						+ "onchange=\"if(value=='') value=0; calculaLinea('"+jsonDetalle[i][0]+"', '"+jsonDetalle[i][20]+"');\">"
						+ "</td>"
						+ "<td class=\"totRepos\" style=\"text-align:right;\" id=\"totRepos_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][13]+"</td>"
						+ "<td class=\"totArrie\" style=\"text-align:right;\" id=\"totArrie_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][14]+"</td>"
						+ "<td class=\"totVenta\" style=\"text-align:right;\" id=\"totVenta_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][15]+"</td>"
						+ "<td class=\"totKg\" style=\"text-align:right;\" id=\"totKg_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][18]+"</td>";
					
					if(escondeLosM2 == 1) """),format.raw/*823.28*/("""{"""),format.raw/*823.29*/("""
						"""),format.raw/*824.7*/("""tabla +="<td class=\"totM2\" style=\"display:none; text-align:right;\" id=\"totM2_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][19]+"</td>";
					"""),format.raw/*825.6*/("""}"""),format.raw/*825.7*/("""else"""),format.raw/*825.11*/("""{"""),format.raw/*825.12*/("""
						"""),format.raw/*826.7*/("""tabla +="<td class=\"totM2\" style = \"text-align:right;\" id=\"totM2_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][19]+"</td>";
					"""),format.raw/*827.6*/("""}"""),format.raw/*827.7*/("""
					
						"""),format.raw/*829.7*/("""tabla +="<td style = \"display:none\" id=\"uniKg_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][16]+"</td>"
						+ "<td style = \"display:none\" id=\"uniM2_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][17]+"</td>"
						+ "<td style = \"display:none\" id=\"idEquipo_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][0]+"</td>"
						+ "<td style = \"display:none\" id=\"nroDecimales_"+jsonDetalle[i][0]+"\">"+jsonDetalle[i][20]+"</td>"
						+ ""
						+ "</TR>";
			"""),format.raw/*835.4*/("""}"""),format.raw/*835.5*/("""

			"""),format.raw/*837.4*/("""tabla +="</tbody>"
				+ "<tfoot id=\"tfoot\" style=\"background-color: #eeeeee; display:none\">"
				+ "<TR> "
				+ "<TH colspan=\"13\" style= \"text-align: right;\">SUB-TOTALES </TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"subtotalRepos\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"subtotalArrie\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"subtotalVenta\">0.00</div></TH>"
				+ "<TH></TH>";
				
			if(escondeLosM2 == 1) """),format.raw/*846.26*/("""{"""),format.raw/*846.27*/("""
				"""),format.raw/*847.5*/("""tabla +="<TH style = \"display:none\"></TH>";
			"""),format.raw/*848.4*/("""}"""),format.raw/*848.5*/("""else"""),format.raw/*848.9*/("""{"""),format.raw/*848.10*/("""
				"""),format.raw/*849.5*/("""tabla +="<TH></TH>";
			"""),format.raw/*850.4*/("""}"""),format.raw/*850.5*/("""
			
			"""),format.raw/*852.4*/("""tabla +="<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "</TR>"
				+ "<TR> "
				+ "<TH colspan=\"13\" style=\"text-align:right;\">DESCUENTOS</TH>"
				+ "<TH style=\"text-align:right;\"></TH>"
				+ "<TH style=\"text-align:center;\">"
				+ "<input type=\"text\" class=\"form-control height23px right\""
				+ "id=\"dctoArriendo\""
				+ "name=\"dctoArriendo\""
				+ "value=\""""),_display_(/*863.17*/formCotiza/*863.27*/.dctoArriendo),format.raw/*863.40*/(""" """),format.raw/*863.41*/("""%\""
				+ "onfocus=\"value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\" "
				+ "onblur = \"value = formatPorcentaje(value);\""
				+ "onkeydown=\"return ingresoDouble(window.event)\""
				+ "onchange=\"sumaTotales(); dctoObligado=1;\">"
				+ "</td>"
				+ "</TH>"
				+ "<TH style=\"text-align:center;\">"
				+ "<input type=\"text\" class=\"form-control height23px right\""
				+ "id=\"dctoVenta\""
				+ "name=\"dctoVenta\""
				+ "value=\""""),_display_(/*874.17*/formCotiza/*874.27*/.dctoVenta),format.raw/*874.37*/(""" """),format.raw/*874.38*/("""%\""
				+ "onfocus=\"value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')\" "
				+ "onblur = \"value = formatPorcentaje(value);\""
				+ "onkeydown=\"return ingresoDouble(window.event)\""
				+ "onchange=\"sumaTotales(); dctoObligado=1;\">"
				+ "</td>"
				+ "</TH>"
				+ "<TH></TH>";
				
			if(escondeLosM2 == 1) """),format.raw/*883.26*/("""{"""),format.raw/*883.27*/("""
				"""),format.raw/*884.5*/("""tabla +="<TH style = \"display:none\"></TH>";
			"""),format.raw/*885.4*/("""}"""),format.raw/*885.5*/("""else"""),format.raw/*885.9*/("""{"""),format.raw/*885.10*/("""
				"""),format.raw/*886.5*/("""tabla +="<TH></TH>";
			"""),format.raw/*887.4*/("""}"""),format.raw/*887.5*/("""
			
			"""),format.raw/*889.4*/("""tabla +="<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "</TR>"
				+ "<TR>"
				+ "<TH colspan=\"5\" style= \"text-align: right;\">Total cantidad</TH>"
				+ "<TH colspan=\"1\" style= \"text-align: right;\"><div id=\"granTotCant\">0.00</div></TH>"
				+ "<TH colspan=\"7\" style= \"text-align: right;\">TOTALES </TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"granTotRepos\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"granTotArrie\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"granTotVenta\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"granTotKg\">0.00</div></TH>";
				
			if(escondeLosM2 == 1) """),format.raw/*902.26*/("""{"""),format.raw/*902.27*/("""
				"""),format.raw/*903.5*/("""tabla +="<TH style=\"display:none; text-align:right;\"><div id=\"granTotM2\">0.00</div></TH>";
			"""),format.raw/*904.4*/("""}"""),format.raw/*904.5*/("""else"""),format.raw/*904.9*/("""{"""),format.raw/*904.10*/("""
				"""),format.raw/*905.5*/("""tabla +="<TH style = \"text-align:right;\"><div id=\"granTotM2\">0.00</div></TH>";
			"""),format.raw/*906.4*/("""}"""),format.raw/*906.5*/("""
			
			"""),format.raw/*908.4*/("""tabla +="<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "</TR>"
				+ "<TR id='tIva' style='display:none'>"
				+ "<TH colspan=\"13\" style= \"text-align: right;\">IVA </TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"ivaRepos\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"ivaArrie\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"ivaVenta\">0.00</div></TH>"
				+ "<TH></TH>";
				
			if(escondeLosM2 == 1) """),format.raw/*919.26*/("""{"""),format.raw/*919.27*/("""
				"""),format.raw/*920.5*/("""tabla +="<TH style = \"display:none\"></TH>";
			"""),format.raw/*921.4*/("""}"""),format.raw/*921.5*/("""else"""),format.raw/*921.9*/("""{"""),format.raw/*921.10*/("""
				"""),format.raw/*922.5*/("""tabla +="<TH></TH>";
			"""),format.raw/*923.4*/("""}"""),format.raw/*923.5*/("""
			
			
			"""),format.raw/*926.4*/("""tabla +="<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "</TR>"
				+ "<TR id='tTotal' style='display:none'>"
				+ "<TH colspan=\"13\" style= \"text-align: right;\">TOTAL CON IVA </TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"totalConIvaRepos\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"totalConIvaArrie\">0.00</div></TH>"
				+ "<TH style=\"text-align:right;\"><div id=\"totalConIvaVenta\">0.00</div></TH>"
				+ "<TH></TH>";
				
			if(escondeLosM2 == 1) """),format.raw/*937.26*/("""{"""),format.raw/*937.27*/("""
				"""),format.raw/*938.5*/("""tabla +="<TH style = \"display:none\"></TH>";
			"""),format.raw/*939.4*/("""}"""),format.raw/*939.5*/("""else"""),format.raw/*939.9*/("""{"""),format.raw/*939.10*/("""
				"""),format.raw/*940.5*/("""tabla +="<TH></TH>";
			"""),format.raw/*941.4*/("""}"""),format.raw/*941.5*/("""
			
			"""),format.raw/*943.4*/("""tabla +="<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "<TH style = \"display:none\"></TH>"
				+ "</TR>"
				+ "</tfoot>"
				+ "</table>";

		document.getElementById('tblPrincipal').innerHTML = tabla;
		
		$('#tablaListaClientes').DataTable("""),format.raw/*952.38*/("""{"""),format.raw/*952.39*/("""
	    	"""),format.raw/*953.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 1, "asc" ]],
	    	"language": """),format.raw/*956.19*/("""{"""),format.raw/*956.20*/("""
	        	"""),format.raw/*957.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*958.10*/("""}"""),format.raw/*958.11*/("""
	  	"""),format.raw/*959.5*/("""}"""),format.raw/*959.6*/(""" """),format.raw/*959.7*/(""");

		$('#tablaListaProyectos').DataTable("""),format.raw/*961.39*/("""{"""),format.raw/*961.40*/("""
	    	"""),format.raw/*962.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ]],
	    	"language": """),format.raw/*965.19*/("""{"""),format.raw/*965.20*/("""
	        	"""),format.raw/*966.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*967.10*/("""}"""),format.raw/*967.11*/("""
	  	"""),format.raw/*968.5*/("""}"""),format.raw/*968.6*/(""" """),format.raw/*968.7*/(""");

		sumaTotales();
		
		$('#tablaPrincipal').DataTable("""),format.raw/*972.34*/("""{"""),format.raw/*972.35*/("""
		    	"""),format.raw/*973.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*976.20*/("""{"""),format.raw/*976.21*/("""
		        	"""),format.raw/*977.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*978.11*/("""}"""),format.raw/*978.12*/("""
		"""),format.raw/*979.3*/("""}"""),format.raw/*979.4*/(""" """),format.raw/*979.5*/(""");
		  
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*982.2*/("""}"""),format.raw/*982.3*/(""");
	
	
	const validarNumero = (numero) =>"""),format.raw/*985.35*/("""{"""),format.raw/*985.36*/("""
		"""),format.raw/*986.3*/("""var formData = new FormData();
		formData.append('numeroCoti',numero);
        $.ajax("""),format.raw/*988.16*/("""{"""),format.raw/*988.17*/("""
            """),format.raw/*989.13*/("""url: "/existeNumeroCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*996.36*/("""{"""),format.raw/*996.37*/("""
				"""),format.raw/*997.5*/("""if(respuesta!="OK")"""),format.raw/*997.24*/("""{"""),format.raw/*997.25*/("""
					"""),format.raw/*998.6*/("""$("#numeroCoti").val(""""),_display_(/*998.29*/formCotiza/*998.39*/.numeroCoti),format.raw/*998.50*/("""");
					alertify.alert(respuesta, function () """),format.raw/*999.44*/("""{"""),format.raw/*999.45*/(""" """),format.raw/*999.46*/("""}"""),format.raw/*999.47*/(""");
				"""),format.raw/*1000.5*/("""}"""),format.raw/*1000.6*/("""
	     	"""),format.raw/*1001.8*/("""}"""),format.raw/*1001.9*/("""
        """),format.raw/*1002.9*/("""}"""),format.raw/*1002.10*/(""");
	"""),format.raw/*1003.2*/("""}"""),format.raw/*1003.3*/("""
	
	"""),format.raw/*1005.2*/("""const vistaStockPorEquipo = (id_equipo) =>"""),format.raw/*1005.44*/("""{"""),format.raw/*1005.45*/("""
		"""),format.raw/*1006.3*/("""var formData = new FormData();
		formData.append('id_equipo',id_equipo);
        $.ajax("""),format.raw/*1008.16*/("""{"""),format.raw/*1008.17*/("""
            """),format.raw/*1009.13*/("""url: "/tablaInvPorEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1016.36*/("""{"""),format.raw/*1016.37*/("""
				"""),format.raw/*1017.5*/("""document.getElementById('stockPorEquipo').innerHTML = respuesta;
				$('#modalStockPorEquipo').modal('show');
	     	"""),format.raw/*1019.8*/("""}"""),format.raw/*1019.9*/("""
        """),format.raw/*1020.9*/("""}"""),format.raw/*1020.10*/(""");
	"""),format.raw/*1021.2*/("""}"""),format.raw/*1021.3*/("""
	
	
	"""),format.raw/*1024.2*/("""const sumaTotales = () =>"""),format.raw/*1024.27*/("""{"""),format.raw/*1024.28*/("""
		"""),format.raw/*1025.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let tabla = document.getElementById("tablaPrincipal");
		let totCant = 0;
		
		let cant = 0;
		$(".cantidad").each(function() """),format.raw/*1030.34*/("""{"""),format.raw/*1030.35*/("""
			"""),format.raw/*1031.4*/("""let val = $(this).val().replace(/,/g,'');
			cant += parseFloat(val);
		"""),format.raw/*1033.3*/("""}"""),format.raw/*1033.4*/(""");
		
		let repos = 0;
		$(".totRepos").each(function() """),format.raw/*1036.34*/("""{"""),format.raw/*1036.35*/("""
			"""),format.raw/*1037.4*/("""let val = $(this).text().replace(/,/g,'');
			repos += parseFloat(val);
		"""),format.raw/*1039.3*/("""}"""),format.raw/*1039.4*/("""); $("#subtotalRepos").text(formatStandar(repos,'"""),_display_(/*1039.54*/numDecParaTot),format.raw/*1039.67*/("""'));
		
		let arr = 0;
		$(".totArrie").each(function() """),format.raw/*1042.34*/("""{"""),format.raw/*1042.35*/("""
			"""),format.raw/*1043.4*/("""let val = $(this).text().replace(/,/g,'');
			arr += parseFloat(val);
		"""),format.raw/*1045.3*/("""}"""),format.raw/*1045.4*/("""); $("#subtotalArrie").text(formatStandar(arr,'"""),_display_(/*1045.52*/numDecParaTot),format.raw/*1045.65*/("""'));
		
		let vta = 0;
		$(".totVenta").each(function() """),format.raw/*1048.34*/("""{"""),format.raw/*1048.35*/("""
			"""),format.raw/*1049.4*/("""let val = $(this).text().replace(/,/g,'');
			vta += parseFloat(val);
		"""),format.raw/*1051.3*/("""}"""),format.raw/*1051.4*/("""); $("#subtotalVenta").text(formatStandar(vta,'"""),_display_(/*1051.52*/numDecParaTot),format.raw/*1051.65*/("""'));
		
		let kg = 0;
		$(".totKg").each(function() """),format.raw/*1054.31*/("""{"""),format.raw/*1054.32*/("""
			"""),format.raw/*1055.4*/("""let val = $(this).text().replace(/,/g,'');
			kg += parseFloat(val);
		"""),format.raw/*1057.3*/("""}"""),format.raw/*1057.4*/(""");
		
		let m2 = 0;
		$(".totM2").each(function() """),format.raw/*1060.31*/("""{"""),format.raw/*1060.32*/("""
			"""),format.raw/*1061.4*/("""let val = $(this).text().replace(/,/g,'');
			m2 += parseFloat(val);
		"""),format.raw/*1063.3*/("""}"""),format.raw/*1063.4*/(""");
		
		let dctoArriendo = $("#dctoArriendo").val().replace(/,/g,'').replace("%","").trim();
		let dctoVenta = $("#dctoVenta").val().replace(/,/g,'').replace("%","").trim();
		
		$("#granTotRepos").text(formatStandar(repos,'"""),_display_(/*1068.49*/numDecParaTot),format.raw/*1068.62*/("""'));
		$("#granTotArrie").text(formatStandar((arr*(1-dctoArriendo/100)),'"""),_display_(/*1069.70*/numDecParaTot),format.raw/*1069.83*/("""'));
		$("#granTotVenta").text(formatStandar((vta*(1-dctoVenta/100)),'"""),_display_(/*1070.67*/numDecParaTot),format.raw/*1070.80*/("""'));
		$("#granTotKg").text(formatStandar(kg,2));
		$("#granTotM2").text(formatStandar(m2,2));
		$("#granTotCant").text(formatStandar(cant,2));
		
	"""),format.raw/*1075.2*/("""}"""),format.raw/*1075.3*/("""
	
	"""),format.raw/*1077.2*/("""const checkVenta = (id_equipo) =>"""),format.raw/*1077.35*/("""{"""),format.raw/*1077.36*/("""
		"""),format.raw/*1078.3*/("""let esVenta = $("#esVenta_"+id_equipo).val();
		if(esVenta==0)"""),format.raw/*1079.17*/("""{"""),format.raw/*1079.18*/("""
			"""),format.raw/*1080.4*/("""$("#esVenta_"+id_equipo).val('1');
		"""),format.raw/*1081.3*/("""}"""),format.raw/*1081.4*/("""else"""),format.raw/*1081.8*/("""{"""),format.raw/*1081.9*/("""
			"""),format.raw/*1082.4*/("""$("#esVenta_"+id_equipo).val('0');
		"""),format.raw/*1083.3*/("""}"""),format.raw/*1083.4*/("""
	"""),format.raw/*1084.2*/("""}"""),format.raw/*1084.3*/("""
	
	"""),format.raw/*1086.2*/("""let calculaTasa = (flag, id_equipo, numDec) => """),format.raw/*1086.49*/("""{"""),format.raw/*1086.50*/("""
		"""),format.raw/*1087.3*/("""if(flag==1)"""),format.raw/*1087.14*/("""{"""),format.raw/*1087.15*/("""
			"""),format.raw/*1088.4*/("""let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			let uniArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let uniTasa = parseFloat(uniArr)/parseFloat(uniVta);
			$("#tasaArr_"+id_equipo).val(formatStandar(uniTasa * 100,2)+" %");
			$("#hiddentasaArr_"+id_equipo).text(uniTasa);
		"""),format.raw/*1093.3*/("""}"""),format.raw/*1093.4*/("""else if(flag==2)"""),format.raw/*1093.20*/("""{"""),format.raw/*1093.21*/("""
			"""),format.raw/*1094.4*/("""let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			let uniTasa = $("#tasaArr_"+id_equipo).val().replace(/,/g,'').replace(/%/g,'').replace(/ /g,'');
			let uniArr = parseFloat(uniVta)*parseFloat(uniTasa)/100;
			$("#puArriendo_"+id_equipo).val(formatStandar(uniArr,numDec));
			$("#hiddentasaArr_"+id_equipo).text(uniTasa);
		"""),format.raw/*1099.3*/("""}"""),format.raw/*1099.4*/("""else"""),format.raw/*1099.8*/("""{"""),format.raw/*1099.9*/("""
			"""),format.raw/*1100.4*/("""let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			let uniArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let uniTasa = parseFloat(uniArr)/parseFloat(uniVta);
			$("#tasaArr_"+id_equipo).val(formatStandar(uniTasa * 100,2)+" %");
			$("#hiddentasaArr_"+id_equipo).text(uniTasa);
		"""),format.raw/*1105.3*/("""}"""),format.raw/*1105.4*/("""
		
	"""),format.raw/*1107.2*/("""}"""),format.raw/*1107.3*/("""
	
	"""),format.raw/*1109.2*/("""const calculaLinea = (id_equipo, numDec) =>"""),format.raw/*1109.45*/("""{"""),format.raw/*1109.46*/("""
		"""),format.raw/*1110.3*/("""let cant = $("#cantidad_"+id_equipo).val().replace(/,/g,'');
		let ckeckEsVta = $("#checkbox_"+id_equipo).val();
		let esVenta = $("#esVenta_"+id_equipo).val();
		let uniVta = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
		let uniArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
		let perm = $("#permanencia_"+id_equipo).val().replace(/,/g,'');
		let uniKg = $("#uniKg_"+id_equipo).text().replace(/,/g,'');
		let uniM2 = $("#uniM2_"+id_equipo).text().replace(/,/g,'');
		let totRepos = parseFloat(cant) * parseFloat(uniVta);
		let totArr = 0;
		let totVta = 0;
		let totKg = 0;
		let totM2 = 0;
		if(esVenta == 0)"""),format.raw/*1123.19*/("""{"""),format.raw/*1123.20*/("""
			 """),format.raw/*1124.5*/("""totRepos = parseFloat(cant) * parseFloat(uniVta);
			 totArr = parseFloat(cant) * parseFloat(perm) * parseFloat(uniArr);
			 totVta = 0;
			 totKg = parseFloat(cant) * parseFloat(uniKg);
			 totM2 = parseFloat(cant) * parseFloat(uniM2);
		"""),format.raw/*1129.3*/("""}"""),format.raw/*1129.4*/("""else"""),format.raw/*1129.8*/("""{"""),format.raw/*1129.9*/("""
			 """),format.raw/*1130.5*/("""totRepos = 0;
			 totArr = 0;
			 totVta = parseFloat(cant) * parseFloat(uniVta);
			 totKg = parseFloat(cant) * parseFloat(uniKg);
			 totM2 = parseFloat(cant) * parseFloat(uniM2);
		"""),format.raw/*1135.3*/("""}"""),format.raw/*1135.4*/("""
		
		"""),format.raw/*1137.3*/("""let idMon = $("#id_moneda_"+id_equipo).val();
		numDec = mapMoneda.get(parseFloat(idMon));
		
		$("#cantidad_"+id_equipo).val(formatStandar(cant,2));
		$("#puVentaRepos_"+id_equipo).val(formatStandar(uniVta,numDec));
		$("#puArriendo_"+id_equipo).val(formatStandar(uniArr,numDec))
		$("#totRepos_"+id_equipo).text(formatStandar(totRepos,numDec));
		$("#totArrie_"+id_equipo).text(formatStandar(totArr,numDec));
		$("#totVenta_"+id_equipo).text(formatStandar(totVta,numDec));
		$("#totKg_"+id_equipo).text(formatStandar(totKg,2));
		$("#totM2_"+id_equipo).text(formatStandar(totM2,2));
		
		$("#hiddencantidad_"+id_equipo).text(cant);
		$("#hiddenpuVentaRepos_"+id_equipo).text(uniVta);
		$("#hiddenpuArriendo_"+id_equipo).text(uniArr);
		
		if(verificando == 1)"""),format.raw/*1153.23*/("""{"""),format.raw/*1153.24*/("""
			"""),format.raw/*1154.4*/("""sumaTotales();
		"""),format.raw/*1155.3*/("""}"""),format.raw/*1155.4*/("""
		
	"""),format.raw/*1157.2*/("""}"""),format.raw/*1157.3*/("""
	
	"""),format.raw/*1159.2*/("""let verificando = 0;
	const verificaCotizacion = () =>"""),format.raw/*1160.34*/("""{"""),format.raw/*1160.35*/("""
		"""),format.raw/*1161.3*/("""sumaTotales();
		let granTotRepos = $("#granTotRepos").text().replace(/,/g,'');
		let granTotArrie = $("#granTotArrie").text().replace(/,/g,'');
		let granTotVenta = $("#granTotVenta").text().replace(/,/g,'');
		let sumaAux = parseFloat(granTotRepos) + parseFloat(granTotArrie) + parseFloat(granTotVenta);
		if(sumaAux > 0)"""),format.raw/*1166.18*/("""{"""),format.raw/*1166.19*/("""
			"""),format.raw/*1167.4*/("""let tabla = document.getElementById("tablaPrincipal");
			for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1168.40*/("""{"""),format.raw/*1168.41*/("""
				"""),format.raw/*1169.5*/("""let cant = $(".cantidad",tabla.rows[i].cells[5]).val().replace(/,/g,'');
				let arr = tabla.rows[i].cells[14].innerHTML.replace(/,/g,'');
				let vta = tabla.rows[i].cells[15].innerHTML.replace(/,/g,'');
				if((parseFloat(cant)+parseFloat(arr)+parseFloat(vta))<=0)"""),format.raw/*1172.62*/("""{"""),format.raw/*1172.63*/("""
					"""),format.raw/*1173.6*/("""tabla.rows[i].style.display = 'none';
				"""),format.raw/*1174.5*/("""}"""),format.raw/*1174.6*/("""
			"""),format.raw/*1175.4*/("""}"""),format.raw/*1175.5*/("""
			
			"""),format.raw/*1177.4*/("""let iva = parseFloat(""""),_display_(/*1177.27*/iva),format.raw/*1177.30*/("""");
			
			$("#ivaRepos").text(formatStandar(granTotRepos*iva),'"""),_display_(/*1179.58*/numDecParaTot),format.raw/*1179.71*/("""');
			$("#ivaArrie").text(formatStandar(granTotArrie*iva),'"""),_display_(/*1180.58*/numDecParaTot),format.raw/*1180.71*/("""');
			$("#ivaVenta").text(formatStandar(granTotVenta*iva),'"""),_display_(/*1181.58*/numDecParaTot),format.raw/*1181.71*/("""');
			
			$("#totalConIvaRepos").text(formatStandar(formatStandar(granTotRepos*(1+iva)),'"""),_display_(/*1183.84*/numDecParaTot),format.raw/*1183.97*/("""'));
			$("#totalConIvaArrie").text(formatStandar(formatStandar(granTotArrie*(1+iva)),'"""),_display_(/*1184.84*/numDecParaTot),format.raw/*1184.97*/("""'));
			$("#totalConIvaVenta").text(formatStandar(formatStandar(granTotVenta*(1+iva)),'"""),_display_(/*1185.84*/numDecParaTot),format.raw/*1185.97*/("""'));
			
			"""),_display_(if(mapPermiso.get("parametro.cotizaciones-con-iva")!=null && mapPermiso.get("parametro.cotizaciones-con-iva").equals("1"))/*1187.127*/{_display_(Seq[Any](format.raw/*1187.128*/("""
				"""),format.raw/*1188.5*/("""document.getElementById('tIva').style.display = '';
				document.getElementById('tTotal').style.display = '';
			""")))} else {null} ),format.raw/*1190.5*/("""
			
			"""),format.raw/*1192.4*/("""verificando = 1;
			document.getElementById('tfoot').style.display = '';
			document.getElementById('modifica').style.visibility = 'visible';
			document.getElementById('aplica').style.visibility = 'visible';
			document.getElementById('verifica').style.visibility = 'hidden';
			document.getElementById('selSucursal').disabled = true;
			document.getElementById('selComercial').disabled = true;
			$('#btnAplicarTasa').hide();
			$('#titAplicarFactorRepos').hide();
			$('#btnAplicarFactorRepos').hide();
			$('#titAplicarFactorArr').hide();
			$('#btnAplicarFactorArr').hide();
			$('#btnAplicarPerm').hide();
			
		"""),format.raw/*1206.3*/("""}"""),format.raw/*1206.4*/("""else"""),format.raw/*1206.8*/("""{"""),format.raw/*1206.9*/("""
			"""),format.raw/*1207.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*1207.35*/("""{"""),format.raw/*1207.36*/("""
		    	"""),format.raw/*1208.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*1211.20*/("""{"""),format.raw/*1211.21*/("""
		        	"""),format.raw/*1212.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*1213.11*/("""}"""),format.raw/*1213.12*/("""
			"""),format.raw/*1214.4*/("""}"""),format.raw/*1214.5*/(""" """),format.raw/*1214.6*/(""");
			alertify.alert('NO PUEDE VERIFICAR UNA COTIZACION SIN CANTIDADES', function () """),format.raw/*1215.83*/("""{"""),format.raw/*1215.84*/(""" """),format.raw/*1215.85*/("""}"""),format.raw/*1215.86*/(""");
		"""),format.raw/*1216.3*/("""}"""),format.raw/*1216.4*/("""
	"""),format.raw/*1217.2*/("""}"""),format.raw/*1217.3*/("""
	
	"""),format.raw/*1219.2*/("""const modificaCotizacion = () =>"""),format.raw/*1219.34*/("""{"""),format.raw/*1219.35*/("""
		"""),format.raw/*1220.3*/("""let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1221.39*/("""{"""),format.raw/*1221.40*/("""
			"""),format.raw/*1222.4*/("""tabla.rows[i].style.display = '';
		"""),format.raw/*1223.3*/("""}"""),format.raw/*1223.4*/("""
		"""),format.raw/*1224.3*/("""$('#tablaPrincipal').DataTable("""),format.raw/*1224.34*/("""{"""),format.raw/*1224.35*/("""
		    	"""),format.raw/*1225.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*1228.20*/("""{"""),format.raw/*1228.21*/("""
		        	"""),format.raw/*1229.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*1230.11*/("""}"""),format.raw/*1230.12*/("""
		"""),format.raw/*1231.3*/("""}"""),format.raw/*1231.4*/(""" """),format.raw/*1231.5*/(""");
		document.getElementById('tfoot').style.display = 'none';
		document.getElementById('modifica').style.visibility = 'hidden';
		document.getElementById('aplica').style.visibility = 'hidden';
		document.getElementById('verifica').style.visibility = 'visible';
		document.getElementById('selSucursal').disabled = false;
		document.getElementById('selComercial').disabled = false;
		$('#btnAplicarTasa').show();
		$('#titAplicarFactorRepos').show();
		$('#btnAplicarFactorRepos').show();
		$('#titAplicarFactorArr').show();
		$('#btnAplicarFactorArr').show();
		$('#btnAplicarPerm').show();
		
		verificando = 0;
	"""),format.raw/*1246.2*/("""}"""),format.raw/*1246.3*/("""

	"""),format.raw/*1248.2*/("""const validarForm = () =>"""),format.raw/*1248.27*/("""{"""),format.raw/*1248.28*/("""
		"""),format.raw/*1249.3*/("""$("#aplica").attr('disabled',true);
		let flag = true;
		sumaTotales();
		let granTotRepos = $("#granTotRepos").text().replace(/,/g,'');
		let granTotArrie = $("#granTotArrie").text().replace(/,/g,'');
		let granTotVenta = $("#granTotVenta").text().replace(/,/g,'');
		let sumaAux = parseFloat(granTotRepos) + parseFloat(granTotArrie) + parseFloat(granTotVenta);
		
		
		if(sumaAux <= 0)"""),format.raw/*1258.19*/("""{"""),format.raw/*1258.20*/("""
			"""),format.raw/*1259.4*/("""flag = false;
			alertify.alert('NO PUEDE GENERAR UNA COTIZACION SIN CANTIDADES', function () """),format.raw/*1260.81*/("""{"""),format.raw/*1260.82*/("""
				"""),format.raw/*1261.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*1263.8*/("""}"""),format.raw/*1263.9*/(""");
		"""),format.raw/*1264.3*/("""}"""),format.raw/*1264.4*/("""else if($("#id_cliente").val()=='0')"""),format.raw/*1264.40*/("""{"""),format.raw/*1264.41*/("""
			"""),format.raw/*1265.4*/("""flag = false;
			alertify.alert('NO PUEDE GENERAR UNA COTIZACION SIN ASIGNAR UN CLIENTE', function () """),format.raw/*1266.89*/("""{"""),format.raw/*1266.90*/("""
				"""),format.raw/*1267.5*/("""$("#aplica").attr('disabled',false);
				return(flag);
     		"""),format.raw/*1269.8*/("""}"""),format.raw/*1269.9*/(""");
		"""),format.raw/*1270.3*/("""}"""),format.raw/*1270.4*/("""else if ((sumaAux-parseFloat(granTotRepos))<=0)"""),format.raw/*1270.51*/("""{"""),format.raw/*1270.52*/("""
			"""),format.raw/*1271.4*/("""flag = false;
				alertify.alert('NO PUEDE GENERAR UNA COTIZACION QUE SUMA CERO, REVISAR PERMANENCIA', function () """),format.raw/*1272.102*/("""{"""),format.raw/*1272.103*/("""
					"""),format.raw/*1273.6*/("""$("#aplica").attr('disabled',false);
					return(flag);
	     		"""),format.raw/*1275.9*/("""}"""),format.raw/*1275.10*/(""");
		"""),format.raw/*1276.3*/("""}"""),format.raw/*1276.4*/("""else"""),format.raw/*1276.8*/("""{"""),format.raw/*1276.9*/("""
			"""),format.raw/*1277.4*/("""let tabla = document.getElementById("tablaPrincipal");
			for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1278.40*/("""{"""),format.raw/*1278.41*/("""
				"""),format.raw/*1279.5*/("""let cant = $(".cantidad",tabla.rows[i].cells[5]).val().replace(/,/g,'');
				let arr = tabla.rows[i].cells[13].innerHTML.replace(/,/g,'');
				let vta = tabla.rows[i].cells[14].innerHTML.replace(/,/g,'');
				if((parseFloat(cant)+parseFloat(arr)+parseFloat(vta))<=0)"""),format.raw/*1282.62*/("""{"""),format.raw/*1282.63*/("""
					"""),format.raw/*1283.6*/("""tabla.deleteRow(i);
					i--;
				"""),format.raw/*1285.5*/("""}"""),format.raw/*1285.6*/("""
			"""),format.raw/*1286.4*/("""}"""),format.raw/*1286.5*/("""
			"""),format.raw/*1287.4*/("""return(true);
		"""),format.raw/*1288.3*/("""}"""),format.raw/*1288.4*/("""
		"""),format.raw/*1289.3*/("""return(flag);
	"""),format.raw/*1290.2*/("""}"""),format.raw/*1290.3*/("""
	
	"""),format.raw/*1292.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*1292.43*/("""{"""),format.raw/*1292.44*/("""
		  """),format.raw/*1293.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*1295.2*/("""}"""),format.raw/*1295.3*/("""

	"""),format.raw/*1297.2*/("""let extArray = new Array(".gif", ".jpg", ".png", ".jpeg", ".pdf", ".xls", ".doc", ".xlsx", ".docx", ".tar", ".zip", ".rar");
	const LimitAttach = (form, file) => """),format.raw/*1298.38*/("""{"""),format.raw/*1298.39*/("""
		"""),format.raw/*1299.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*1301.35*/("""{"""),format.raw/*1301.36*/("""
			"""),format.raw/*1302.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*1303.3*/("""}"""),format.raw/*1303.4*/("""
		"""),format.raw/*1304.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*1305.45*/("""{"""),format.raw/*1305.46*/("""
			"""),format.raw/*1306.4*/("""if (extArray[i] == extencion) """),format.raw/*1306.34*/("""{"""),format.raw/*1306.35*/(""" """),format.raw/*1306.36*/("""allowSubmit = true; break; """),format.raw/*1306.63*/("""}"""),format.raw/*1306.64*/("""
		"""),format.raw/*1307.3*/("""}"""),format.raw/*1307.4*/("""
		"""),format.raw/*1308.3*/("""if (allowSubmit) """),format.raw/*1308.20*/("""{"""),format.raw/*1308.21*/("""
			"""),format.raw/*1309.4*/("""var file = $("#docAdjunto")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*1311.26*/("""{"""),format.raw/*1311.27*/("""
				"""),format.raw/*1312.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.docAdjunto.value="";
			"""),format.raw/*1315.4*/("""}"""),format.raw/*1315.5*/("""else"""),format.raw/*1315.9*/("""{"""),format.raw/*1315.10*/("""
				"""),format.raw/*1316.5*/("""alert("Documento subido con éxito");
				$("#txtBtn").text("Cambiar Documento");
			"""),format.raw/*1318.4*/("""}"""),format.raw/*1318.5*/("""
		"""),format.raw/*1319.3*/("""}"""),format.raw/*1319.4*/("""else"""),format.raw/*1319.8*/("""{"""),format.raw/*1319.9*/("""
			"""),format.raw/*1320.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.docAdjunto.value="";
		"""),format.raw/*1324.3*/("""}"""),format.raw/*1324.4*/("""
	"""),format.raw/*1325.2*/("""}"""),format.raw/*1325.3*/("""
	
	
	"""),format.raw/*1328.2*/("""const aplicarTasaGlobal = () => """),format.raw/*1328.34*/("""{"""),format.raw/*1328.35*/("""
		"""),format.raw/*1329.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		$(".tasaGlobal").each(function() """),format.raw/*1330.36*/("""{"""),format.raw/*1330.37*/("""
			 """),format.raw/*1331.5*/("""$(this).val($("#tasaGlobal").val());
		"""),format.raw/*1332.3*/("""}"""),format.raw/*1332.4*/(""");
		let tasa = $("#tasaGlobal").val();
		tasa = tasa.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'');
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1336.39*/("""{"""),format.raw/*1336.40*/("""
			"""),format.raw/*1337.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			$("#puArriendo_"+id_equipo).val(formatStandar(parseFloat(prepos*tasa/100),nroDecimal));
			calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1342.3*/("""}"""),format.raw/*1342.4*/("""
		"""),format.raw/*1343.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		
		$('#tablaPrincipal').DataTable("""),format.raw/*1347.34*/("""{"""),format.raw/*1347.35*/("""
	    	"""),format.raw/*1348.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1351.19*/("""{"""),format.raw/*1351.20*/("""
	        	"""),format.raw/*1352.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1353.10*/("""}"""),format.raw/*1353.11*/("""
		"""),format.raw/*1354.3*/("""}"""),format.raw/*1354.4*/(""");
	"""),format.raw/*1355.2*/("""}"""),format.raw/*1355.3*/("""
	
	"""),format.raw/*1357.2*/("""const aplicarFactorGlobal = (valor) => """),format.raw/*1357.41*/("""{"""),format.raw/*1357.42*/("""
		"""),format.raw/*1358.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let factorGlobal = valor/factorArr;
		factorArr = valor;
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1362.39*/("""{"""),format.raw/*1362.40*/("""
			"""),format.raw/*1363.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let pArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			$("#puArriendo_"+id_equipo).val(formatStandar(parseFloat(pArr*factorGlobal),nroDecimal));
			if(parseFloat(prepos)>0)"""),format.raw/*1368.28*/("""{"""),format.raw/*1368.29*/("""
				"""),format.raw/*1369.5*/("""$("#tasaArr_"+id_equipo).val(formatStandar(parseFloat(pArr*factorGlobal/prepos*100),2) + "%");
			"""),format.raw/*1370.4*/("""}"""),format.raw/*1370.5*/("""
			"""),format.raw/*1371.4*/("""calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1372.3*/("""}"""),format.raw/*1372.4*/("""
		"""),format.raw/*1373.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		$('#tablaPrincipal').DataTable("""),format.raw/*1376.34*/("""{"""),format.raw/*1376.35*/("""
	    	"""),format.raw/*1377.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1380.19*/("""{"""),format.raw/*1380.20*/("""
	        	"""),format.raw/*1381.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1382.10*/("""}"""),format.raw/*1382.11*/("""
		"""),format.raw/*1383.3*/("""}"""),format.raw/*1383.4*/(""");
	"""),format.raw/*1384.2*/("""}"""),format.raw/*1384.3*/("""
	
	"""),format.raw/*1386.2*/("""const aplicarFactorGlobalRepos = (valor) => """),format.raw/*1386.46*/("""{"""),format.raw/*1386.47*/("""
		"""),format.raw/*1387.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let factorGlobalRepos = valor/factorVta;
		factorVta = valor;
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1391.39*/("""{"""),format.raw/*1391.40*/("""
			"""),format.raw/*1392.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let pArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			prepos = prepos * factorGlobalRepos;
			$("#puVentaRepos_"+id_equipo).val(formatStandar(parseFloat(prepos),nroDecimal));
			if(parseFloat(prepos*factorGlobalRepos)>0)"""),format.raw/*1398.46*/("""{"""),format.raw/*1398.47*/("""
				"""),format.raw/*1399.5*/("""$("#tasaArr_"+id_equipo).val(formatStandar(parseFloat(pArr/prepos*100),2) + "%");
			"""),format.raw/*1400.4*/("""}"""),format.raw/*1400.5*/("""
			"""),format.raw/*1401.4*/("""calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1402.3*/("""}"""),format.raw/*1402.4*/("""
		"""),format.raw/*1403.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		$('#tablaPrincipal').DataTable("""),format.raw/*1406.34*/("""{"""),format.raw/*1406.35*/("""
	    	"""),format.raw/*1407.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1410.19*/("""{"""),format.raw/*1410.20*/("""
	        	"""),format.raw/*1411.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1412.10*/("""}"""),format.raw/*1412.11*/("""
		"""),format.raw/*1413.3*/("""}"""),format.raw/*1413.4*/(""");
	"""),format.raw/*1414.2*/("""}"""),format.raw/*1414.3*/("""
	
	"""),format.raw/*1416.2*/("""const aplicarPermGlobal = () => """),format.raw/*1416.34*/("""{"""),format.raw/*1416.35*/("""
		"""),format.raw/*1417.3*/("""$("#tablaPrincipal").dataTable().fnDestroy();
		let permGlobal = $("#permGlobal").val();
		permGlobal = permGlobal.replace(/,/g,'');
		let tabla = document.getElementById("tablaPrincipal");
		for(i=2; i<tabla.rows.length-5; i++)"""),format.raw/*1421.39*/("""{"""),format.raw/*1421.40*/("""
			"""),format.raw/*1422.4*/("""let id_equipo = tabla.rows[i].cells[20].innerHTML;
			let nroDecimal = tabla.rows[i].cells[21].innerHTML;
			let pArr = $("#puArriendo_"+id_equipo).val().replace(/,/g,'');
			let prepos = $("#puVentaRepos_"+id_equipo).val().replace(/,/g,'');
			$("#permanencia_"+id_equipo).val(formatStandar(parseFloat(permGlobal),2));
			calculaLinea(id_equipo,nroDecimal);
		"""),format.raw/*1428.3*/("""}"""),format.raw/*1428.4*/("""
		"""),format.raw/*1429.3*/("""sumaTotales();
		document.getElementById('selSucursal').disabled = true;
		document.getElementById('selComercial').disabled = true;
		$('#tablaPrincipal').DataTable("""),format.raw/*1432.34*/("""{"""),format.raw/*1432.35*/("""
	    	"""),format.raw/*1433.7*/(""""fixedHeader": true,
	    	"lengthMenu": [[15, 50, 100, 200, -1], [15, 50, 100, 200, "All"]],
	    	"order": [[ 0, "asc" ],[ 2, "asc" ]],
	    	"language": """),format.raw/*1436.19*/("""{"""),format.raw/*1436.20*/("""
	        	"""),format.raw/*1437.11*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
	        """),format.raw/*1438.10*/("""}"""),format.raw/*1438.11*/("""
		"""),format.raw/*1439.3*/("""}"""),format.raw/*1439.4*/(""");
	"""),format.raw/*1440.2*/("""}"""),format.raw/*1440.3*/("""
	
	"""),format.raw/*1442.2*/("""const actualizaComerciales = (id_sucursal) =>"""),format.raw/*1442.47*/("""{"""),format.raw/*1442.48*/("""
		"""),format.raw/*1443.3*/("""var formData = new FormData();
		formData.append('id_sucursal',id_sucursal);
        $.ajax("""),format.raw/*1445.16*/("""{"""),format.raw/*1445.17*/("""
            """),format.raw/*1446.13*/("""url: "/actualizaComercialesAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*1453.36*/("""{"""),format.raw/*1453.37*/("""
				"""),format.raw/*1454.5*/("""document.getElementById('actualComerciales').innerHTML = respuesta;
	     	"""),format.raw/*1455.8*/("""}"""),format.raw/*1455.9*/("""
        """),format.raw/*1456.9*/("""}"""),format.raw/*1456.10*/(""");	
	"""),format.raw/*1457.2*/("""}"""),format.raw/*1457.3*/("""
	
	
	


	
"""),format.raw/*1464.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,formCotiza:forms.FormCotiza,listClientes:List[tables.Cliente],listProyectos:List[tables.Proyecto],numDecParaTot:String,listRegiones:List[tables.Regiones],jsonListUnTiempo:String,listSucursal:List[tables.Sucursal],listComercial:List[tables.Comercial],jsonDetalle:String,listSoluciones:List[tables.CotizaSolucion],cotizacion:tables.Cotizacion,iva:Double,jsonListMoneda:String,listDibujantes:List[tables.Dibujante]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,formCotiza,listClientes,listProyectos,numDecParaTot,listRegiones,jsonListUnTiempo,listSucursal,listComercial,jsonDetalle,listSoluciones,cotizacion,iva,jsonListMoneda,listDibujantes)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,forms.FormCotiza,List[tables.Cliente],List[tables.Proyecto],String,List[tables.Regiones],String,List[tables.Sucursal],List[tables.Comercial],String,List[tables.CotizaSolucion],tables.Cotizacion,Double,String,List[tables.Dibujante]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,formCotiza,listClientes,listProyectos,numDecParaTot,listRegiones,jsonListUnTiempo,listSucursal,listComercial,jsonDetalle,listSoluciones,cotizacion,iva,jsonListMoneda,listDibujantes) => apply(mapDiccionario,mapPermiso,userMnu,formCotiza,listClientes,listProyectos,numDecParaTot,listRegiones,jsonListUnTiempo,listSucursal,listComercial,jsonDetalle,listSoluciones,cotizacion,iva,jsonListMoneda,listDibujantes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaModifica.scala.html
                  HASH: 72a775b5ed9b9569177bcb310b6a1ec811cfccfe
                  MATRIX: 1245->2|1876->540|1905->543|1922->551|1962->553|1990->554|2139->677|2208->725|2236->726|2386->850|2431->874|2461->877|2519->909|2587->956|2617->959|2697->1011|2726->1012|2758->1017|3631->1863|3659->1864|3689->1867|3795->1947|3864->1995|3894->1998|3976->2052|4005->2053|4037->2058|4796->2790|4824->2791|4854->2794|5091->3005|5167->3060|5198->3064|5584->3423|5603->3433|5639->3447|5965->3746|5984->3756|6016->3767|6178->3901|6207->3902|6242->3910|6261->3920|6293->3931|6323->3933|6352->3934|6384->3938|6413->3939|6463->3960|6493->3961|6934->4375|6953->4385|6984->4395|7036->4420|7055->4430|7086->4440|7345->4671|7369->4685|7402->4696|7432->4697|7569->4806|7589->4816|7622->4827|7721->4898|7762->4900|7830->4940|7870->4941|7912->4954|8034->5048|8054->5058|8087->5069|8150->5112|8190->5113|8232->5126|8354->5220|8374->5230|8407->5241|8578->5380|8614->5396|8654->5397|8695->5409|8815->5501|8835->5511|8868->5522|9035->5657|9074->5667|9383->5948|9424->5950|9493->5991|9533->5992|9576->6006|9702->6104|9722->6114|9756->6126|9840->6190|9880->6191|9923->6205|10049->6303|10069->6313|10103->6325|10289->6479|10326->6496|10366->6497|10408->6510|10534->6608|10554->6618|10588->6630|10773->6783|10812->6793|11128->7081|11148->7091|11182->7103|11281->7174|11322->7176|11363->7188|11488->7285|11508->7295|11543->7308|11602->7347|11642->7348|11683->7360|11808->7457|11828->7467|11863->7480|12023->7608|12062->7618|12292->7820|12332->7821|12373->7833|12440->7880|12480->7881|12521->7893|12595->7935|12638->7949|12902->8185|12942->8186|12982->8197|13019->8214|13059->8215|13099->8226|13168->8267|13188->8277|13224->8291|13346->8381|13383->8390|13600->8579|13620->8589|13660->8606|13924->8842|13964->8843|14049->8900|14089->8901|14131->8914|14330->9085|14350->9095|14389->9112|14420->9115|14440->9125|14480->9143|14532->9167|14575->9193|14615->9194|14659->9209|14703->9225|14718->9230|14748->9238|14779->9241|14794->9246|14828->9258|14884->9282|14926->9295|14972->9321|15012->9322|15054->9335|15224->9477|15244->9487|15283->9504|15314->9507|15334->9517|15374->9535|15426->9559|15469->9585|15509->9586|15553->9601|15597->9617|15612->9622|15642->9630|15673->9633|15688->9638|15722->9650|15778->9674|15820->9687|15874->9709|15910->9725|15950->9726|15991->9738|16103->9822|16123->9832|16163->9850|16230->9885|16269->9895|16687->10285|16707->10295|16743->10309|17010->10548|17030->10558|17071->10576|17342->10819|17382->10820|17424->10833|17593->10974|17613->10984|17653->11002|17684->11005|17704->11015|17745->11034|17797->11058|17841->11085|17881->11086|17925->11101|17969->11117|17984->11122|18014->11130|18045->11133|18060->11138|18099->11155|18155->11179|18197->11192|18243->11218|18283->11219|18325->11232|18439->11318|18459->11328|18500->11347|18569->11384|18609->11395|19082->11840|19102->11850|19147->11873|19178->11876|19198->11886|19244->11910|19294->11932|19339->11960|19379->11961|19421->11974|19465->11990|19480->11995|19510->12003|19541->12006|19556->12011|19592->12025|19646->12047|19686->12058|20184->12528|20204->12538|20245->12557|20299->12583|20319->12593|20360->12612|20923->13147|20943->13157|20983->13175|21014->13178|21034->13188|21077->13209|21127->13231|21172->13259|21212->13260|21254->13273|21298->13289|21313->13294|21343->13302|21374->13305|21389->13310|21423->13322|21477->13344|21517->13355|21693->13503|21713->13513|21753->13530|21850->13599|21891->13601|21930->13611|22139->13792|22163->13806|22199->13820|22345->13938|22365->13948|22400->13961|22511->14027|22547->14035|22676->14136|22696->14146|22736->14163|22833->14232|22874->14234|22913->14244|23122->14425|23146->14439|23182->14453|23328->14571|23348->14581|23383->14594|23494->14660|23530->14668|24641->15750|24671->15751|24715->15766|24745->15767|26244->17237|26274->17238|26316->17251|26346->17252|26982->17859|27023->17860|27058->17867|27520->18285|27560->18297|28394->19102|28436->19104|28472->19112|28804->19400|28851->19419|30897->21437|30921->21451|30954->21462|31083->21564|31127->21591|31167->21592|31204->21601|31267->21636|31283->21642|31313->21650|31367->21676|31383->21682|31414->21691|31472->21720|31489->21726|31526->21740|31621->21807|31637->21813|31668->21822|31742->21868|31758->21874|31794->21888|31868->21934|31884->21940|31918->21952|31979->21981|32014->21988|33306->23252|33330->23266|33366->23280|33449->23336|33494->23364|33534->23365|33571->23374|33635->23410|33651->23416|33681->23424|33739->23454|33755->23460|33792->23474|33887->23541|33903->23547|33939->23561|34013->23607|34029->23613|34063->23625|34137->23671|34153->23677|34187->23689|34248->23718|34283->23725|35617->25028|35649->25032|35811->25165|35841->25166|35875->25172|35927->25196|35971->25218|36022->25241|36061->25258|36144->25312|36174->25313|36206->25317|36341->25424|36370->25425|36404->25431|36454->25453|36496->25473|36576->25524|36606->25525|36638->25529|36851->25714|36880->25715|36914->25721|37074->25852|37116->25854|37148->25858|37213->25879|37247->25885|37574->26184|37594->26194|37658->26236|37699->26249|37719->26259|37784->26301|37826->26313|37857->26314|37891->26320|38375->26775|38405->26776|38494->26836|38524->26837|38557->26841|38587->26842|38719->26945|38749->26946|38787->26956|38816->26957|38848->26961|39663->27747|39693->27748|39782->27808|39812->27809|39845->27813|39875->27814|40002->27912|40032->27913|40669->28522|40693->28536|40726->28547|40780->28573|40804->28587|40837->28598|40901->28634|40925->28648|40958->28659|41076->28749|41100->28763|41138->28779|41258->28870|41288->28871|41321->28876|41405->28932|41434->28933|41466->28937|41496->28938|41529->28943|41588->28974|41617->28975|41650->28980|41954->29255|41984->29256|42017->29261|43757->30972|43787->30973|43822->30980|44057->31187|44086->31188|44119->31192|44149->31193|44184->31200|44427->31415|44456->31416|44491->31423|45220->32124|45240->32134|45304->32176|45345->32189|45365->32199|45430->32241|45470->32251|45501->32252|45537->32260|45808->32503|45837->32504|45870->32508|45900->32509|45936->32517|46440->32992|46470->32993|46507->33001|46537->33002|46567->33003|46601->33008|46631->33009|46791->33139|46822->33140|46862->33152|46891->33153|46926->33160|47977->34182|48007->34183|48051->34198|48081->34199|48111->34200|48145->34205|48175->34206|48335->34336|48366->34337|49047->34989|49077->34990|49114->34998|49144->34999|49174->35000|49208->35005|49238->35006|49398->35136|49429->35137|50770->36449|50800->36450|50835->36457|51004->36598|51033->36599|51066->36603|51096->36604|51131->36611|51288->36740|51317->36741|51358->36754|51834->37202|51863->37203|51896->37208|52407->37690|52437->37691|52470->37696|52547->37745|52576->37746|52608->37750|52638->37751|52671->37756|52723->37780|52752->37781|52788->37789|53277->38250|53297->38260|53332->38273|53362->38274|53850->38734|53870->38744|53902->38754|53932->38755|54291->39085|54321->39086|54354->39091|54431->39140|54460->39141|54492->39145|54522->39146|54555->39151|54607->39175|54636->39176|54672->39184|55453->39936|55483->39937|55516->39942|55642->40040|55671->40041|55703->40045|55733->40046|55766->40051|55880->40137|55909->40138|55945->40146|56510->40682|56540->40683|56573->40688|56650->40737|56679->40738|56711->40742|56741->40743|56774->40748|56826->40772|56855->40773|56895->40785|57496->41357|57526->41358|57559->41363|57636->41412|57665->41413|57697->41417|57727->41418|57760->41423|57812->41447|57841->41448|57877->41456|58188->41738|58218->41739|58253->41746|58425->41889|58455->41890|58495->41901|58601->41978|58631->41979|58664->41984|58693->41985|58722->41986|58793->42028|58823->42029|58858->42036|59030->42179|59060->42180|59100->42191|59206->42268|59236->42269|59269->42274|59298->42275|59327->42276|59413->42333|59443->42334|59479->42342|59667->42501|59697->42502|59738->42514|59845->42592|59875->42593|59906->42596|59935->42597|59964->42598|60068->42674|60097->42675|60167->42716|60197->42717|60228->42720|60343->42806|60373->42807|60415->42820|60683->43059|60713->43060|60746->43065|60794->43084|60824->43085|60858->43091|60909->43114|60929->43124|60962->43135|61038->43182|61068->43183|61098->43184|61128->43185|61164->43192|61194->43193|61231->43201|61261->43202|61299->43211|61330->43212|61363->43216|61393->43217|61426->43221|61498->43263|61529->43264|61561->43267|61679->43355|61710->43356|61753->43369|62017->43603|62048->43604|62082->43609|62228->43726|62258->43727|62296->43736|62327->43737|62360->43741|62390->43742|62425->43748|62480->43773|62511->43774|62543->43777|62747->43951|62778->43952|62811->43956|62912->44028|62942->44029|63028->44085|63059->44086|63092->44090|63195->44164|63225->44165|63304->44215|63340->44228|63426->44284|63457->44285|63490->44289|63591->44361|63621->44362|63698->44410|63734->44423|63820->44479|63851->44480|63884->44484|63985->44556|64015->44557|64092->44605|64128->44618|64210->44670|64241->44671|64274->44675|64374->44746|64404->44747|64484->44797|64515->44798|64548->44802|64648->44873|64678->44874|64932->45099|64968->45112|65071->45186|65107->45199|65207->45270|65243->45283|65420->45431|65450->45432|65483->45436|65546->45469|65577->45470|65609->45473|65701->45535|65732->45536|65765->45540|65831->45577|65861->45578|65894->45582|65924->45583|65957->45587|66023->45624|66053->45625|66084->45627|66114->45628|66147->45632|66224->45679|66255->45680|66287->45683|66328->45694|66359->45695|66392->45699|66733->46011|66763->46012|66809->46028|66840->46029|66873->46033|67246->46377|67276->46378|67309->46382|67339->46383|67372->46387|67713->46699|67743->46700|67777->46705|67807->46706|67840->46710|67913->46753|67944->46754|67976->46757|68637->47388|68668->47389|68702->47394|68970->47633|69000->47634|69033->47638|69063->47639|69097->47644|69310->47828|69340->47829|69375->47835|70166->48596|70197->48597|70230->48601|70276->48618|70306->48619|70340->48624|70370->48625|70403->48629|70487->48683|70518->48684|70550->48687|70903->49010|70934->49011|70967->49015|71091->49109|71122->49110|71156->49115|71452->49381|71483->49382|71518->49388|71589->49430|71619->49431|71652->49435|71682->49436|71719->49444|71771->49467|71797->49470|71891->49535|71927->49548|72017->49609|72053->49622|72143->49683|72179->49696|72299->49787|72335->49800|72452->49888|72488->49901|72605->49989|72641->50002|72806->50137|72848->50138|72882->50143|73041->50257|73078->50265|73725->50883|73755->50884|73788->50888|73818->50889|73851->50893|73912->50924|73943->50925|73980->50933|74169->51092|74200->51093|74242->51105|74350->51183|74381->51184|74414->51188|74444->51189|74474->51190|74589->51275|74620->51276|74651->51277|74682->51278|74716->51283|74746->51284|74777->51286|74807->51287|74840->51291|74902->51323|74933->51324|74965->51327|75088->51420|75119->51421|75152->51425|75217->51461|75247->51462|75279->51465|75340->51496|75371->51497|75408->51505|75597->51664|75628->51665|75670->51677|75778->51755|75809->51756|75841->51759|75871->51760|75901->51761|76544->52375|76574->52376|76606->52379|76661->52404|76692->52405|76724->52408|77141->52795|77172->52796|77205->52800|77329->52894|77360->52895|77394->52900|77485->52962|77515->52963|77549->52968|77579->52969|77645->53005|77676->53006|77709->53010|77841->53112|77872->53113|77906->53118|77997->53180|78027->53181|78061->53186|78091->53187|78168->53234|78199->53235|78232->53239|78378->53354|78410->53355|78445->53361|78538->53425|78569->53426|78603->53431|78633->53432|78666->53436|78696->53437|78729->53441|78853->53535|78884->53536|78918->53541|79214->53807|79245->53808|79280->53814|79343->53848|79373->53849|79406->53853|79436->53854|79469->53858|79514->53874|79544->53875|79576->53878|79620->53893|79650->53894|79683->53898|79754->53939|79785->53940|79819->53945|79952->54049|79982->54050|80014->54053|80206->54215|80237->54216|80269->54219|80379->54299|80410->54300|80443->54304|80517->54349|80547->54350|80579->54353|80718->54462|80749->54463|80782->54467|80842->54497|80873->54498|80904->54499|80961->54526|80992->54527|81024->54530|81054->54531|81086->54534|81133->54551|81164->54552|81197->54556|81343->54672|81374->54673|81408->54678|81613->54854|81643->54855|81676->54859|81707->54860|81741->54865|81854->54949|81884->54950|81916->54953|81946->54954|81979->54958|82009->54959|82042->54963|82260->55152|82290->55153|82321->55155|82351->55156|82386->55162|82448->55194|82479->55195|82511->55198|82622->55279|82653->55280|82687->55285|82755->55324|82785->55325|83016->55526|83047->55527|83080->55531|83417->55839|83447->55840|83479->55843|83677->56011|83708->56012|83744->56019|83930->56175|83961->56176|84002->56187|84109->56264|84140->56265|84172->56268|84202->56269|84235->56273|84265->56274|84298->56278|84367->56317|84398->56318|84430->56321|84660->56521|84691->56522|84724->56526|85116->56888|85147->56889|85181->56894|85308->56992|85338->56993|85371->56997|85438->57035|85468->57036|85500->57039|85695->57204|85726->57205|85762->57212|85948->57368|85979->57369|86020->57380|86127->57457|86158->57458|86190->57461|86220->57462|86253->57466|86283->57467|86316->57471|86390->57515|86421->57516|86453->57519|86688->57724|86719->57725|86752->57729|87193->58140|87224->58141|87258->58146|87372->58231|87402->58232|87435->58236|87502->58274|87532->58275|87564->58278|87759->58443|87790->58444|87826->58451|88012->58607|88043->58608|88084->58619|88191->58696|88222->58697|88254->58700|88284->58701|88317->58705|88347->58706|88380->58710|88442->58742|88473->58743|88505->58746|88763->58974|88794->58975|88827->58979|89217->59340|89247->59341|89279->59344|89474->59509|89505->59510|89541->59517|89727->59673|89758->59674|89799->59685|89906->59762|89937->59763|89969->59766|89999->59767|90032->59771|90062->59772|90095->59776|90170->59821|90201->59822|90233->59825|90355->59917|90386->59918|90429->59931|90696->60168|90727->60169|90761->60174|90865->60249|90895->60250|90933->60259|90964->60260|90998->60265|91028->60266|91068->60277
                  LINES: 28->2|38->8|40->10|40->10|40->10|41->11|42->12|42->12|43->13|44->14|44->14|46->16|47->17|47->17|48->18|49->19|49->19|50->20|66->36|66->36|67->37|71->41|71->41|72->42|73->43|73->43|74->44|89->59|89->59|90->60|95->65|95->65|96->66|102->72|102->72|102->72|109->79|109->79|109->79|112->82|112->82|112->82|112->82|112->82|112->82|112->82|112->82|112->82|112->82|112->82|123->93|123->93|123->93|124->94|124->94|124->94|131->101|131->101|131->101|131->101|133->103|133->103|133->103|134->104|134->104|135->105|135->105|136->106|138->108|138->108|138->108|140->110|140->110|141->111|143->113|143->113|143->113|147->117|148->118|148->118|149->119|151->121|151->121|151->121|155->125|156->126|163->133|163->133|164->134|164->134|165->135|167->137|167->137|167->137|170->140|170->140|171->141|173->143|173->143|173->143|178->148|179->149|179->149|180->150|182->152|182->152|182->152|187->157|188->158|195->165|195->165|195->165|196->166|196->166|197->167|199->169|199->169|199->169|201->171|201->171|202->172|204->174|204->174|204->174|208->178|209->179|213->183|213->183|214->184|215->185|215->185|216->186|217->187|218->188|222->192|222->192|223->193|224->194|224->194|225->195|225->195|225->195|225->195|228->198|229->199|238->208|238->208|238->208|243->213|243->213|244->214|244->214|245->215|246->216|246->216|246->216|246->216|246->216|246->216|247->217|247->217|247->217|248->218|248->218|248->218|248->218|248->218|248->218|248->218|249->219|250->220|251->221|251->221|252->222|253->223|253->223|253->223|253->223|253->223|253->223|254->224|254->224|254->224|255->225|255->225|255->225|255->225|255->225|255->225|255->225|256->226|257->227|258->228|259->229|259->229|260->230|261->231|261->231|261->231|263->233|264->234|276->246|276->246|276->246|285->255|285->255|285->255|290->260|290->260|291->261|292->262|292->262|292->262|292->262|292->262|292->262|293->263|293->263|293->263|294->264|294->264|294->264|294->264|294->264|294->264|294->264|295->265|296->266|297->267|297->267|298->268|299->269|299->269|299->269|301->271|302->272|316->286|316->286|316->286|316->286|316->286|316->286|317->287|317->287|317->287|318->288|318->288|318->288|318->288|318->288|318->288|318->288|319->289|320->290|333->303|333->303|333->303|334->304|334->304|334->304|347->317|347->317|347->317|347->317|347->317|347->317|348->318|348->318|348->318|349->319|349->319|349->319|349->319|349->319|349->319|349->319|350->320|351->321|356->326|356->326|356->326|357->327|357->327|358->328|361->331|361->331|361->331|364->334|364->334|364->334|368->338|369->339|371->341|371->341|371->341|372->342|372->342|373->343|376->346|376->346|376->346|379->349|379->349|379->349|383->353|384->354|407->377|407->377|407->377|407->377|440->410|440->410|440->410|440->410|455->425|455->425|456->426|464->434|466->436|479->449|479->449|480->450|484->454|488->458|536->506|536->506|536->506|542->512|542->512|542->512|543->513|543->513|543->513|543->513|543->513|543->513|543->513|543->513|543->513|543->513|544->514|544->514|544->514|545->515|545->515|545->515|546->516|546->516|546->516|548->518|549->519|581->551|581->551|581->551|585->555|585->555|585->555|586->556|586->556|586->556|586->556|586->556|586->556|586->556|587->557|587->557|587->557|588->558|588->558|588->558|589->559|589->559|589->559|591->561|592->562|629->599|633->603|640->610|640->610|642->612|642->612|642->612|643->613|643->613|645->615|645->615|646->616|647->617|647->617|649->619|649->619|649->619|651->621|651->621|652->622|654->624|654->624|656->626|657->627|657->627|658->628|659->629|661->631|665->635|665->635|665->635|665->635|665->635|665->635|665->635|665->635|666->636|673->643|673->643|675->645|675->645|675->645|675->645|678->648|678->648|679->649|679->649|680->650|692->662|692->662|694->664|694->664|694->664|694->664|697->667|697->667|714->684|714->684|714->684|715->685|715->685|715->685|716->686|716->686|716->686|719->689|719->689|719->689|724->694|724->694|725->695|726->696|726->696|726->696|726->696|727->697|728->698|728->698|730->700|738->708|738->708|739->709|770->740|770->740|771->741|773->743|773->743|773->743|773->743|774->744|776->746|776->746|778->748|789->759|789->759|789->759|789->759|789->759|789->759|789->759|789->759|790->760|795->765|795->765|795->765|795->765|796->766|804->774|804->774|804->774|804->774|804->774|804->774|804->774|804->774|804->774|805->775|805->775|806->776|822->792|822->792|822->792|822->792|822->792|822->792|822->792|822->792|822->792|834->804|834->804|834->804|834->804|834->804|834->804|834->804|834->804|834->804|853->823|853->823|854->824|855->825|855->825|855->825|855->825|856->826|857->827|857->827|859->829|865->835|865->835|867->837|876->846|876->846|877->847|878->848|878->848|878->848|878->848|879->849|880->850|880->850|882->852|893->863|893->863|893->863|893->863|904->874|904->874|904->874|904->874|913->883|913->883|914->884|915->885|915->885|915->885|915->885|916->886|917->887|917->887|919->889|932->902|932->902|933->903|934->904|934->904|934->904|934->904|935->905|936->906|936->906|938->908|949->919|949->919|950->920|951->921|951->921|951->921|951->921|952->922|953->923|953->923|956->926|967->937|967->937|968->938|969->939|969->939|969->939|969->939|970->940|971->941|971->941|973->943|982->952|982->952|983->953|986->956|986->956|987->957|988->958|988->958|989->959|989->959|989->959|991->961|991->961|992->962|995->965|995->965|996->966|997->967|997->967|998->968|998->968|998->968|1002->972|1002->972|1003->973|1006->976|1006->976|1007->977|1008->978|1008->978|1009->979|1009->979|1009->979|1012->982|1012->982|1015->985|1015->985|1016->986|1018->988|1018->988|1019->989|1026->996|1026->996|1027->997|1027->997|1027->997|1028->998|1028->998|1028->998|1028->998|1029->999|1029->999|1029->999|1029->999|1030->1000|1030->1000|1031->1001|1031->1001|1032->1002|1032->1002|1033->1003|1033->1003|1035->1005|1035->1005|1035->1005|1036->1006|1038->1008|1038->1008|1039->1009|1046->1016|1046->1016|1047->1017|1049->1019|1049->1019|1050->1020|1050->1020|1051->1021|1051->1021|1054->1024|1054->1024|1054->1024|1055->1025|1060->1030|1060->1030|1061->1031|1063->1033|1063->1033|1066->1036|1066->1036|1067->1037|1069->1039|1069->1039|1069->1039|1069->1039|1072->1042|1072->1042|1073->1043|1075->1045|1075->1045|1075->1045|1075->1045|1078->1048|1078->1048|1079->1049|1081->1051|1081->1051|1081->1051|1081->1051|1084->1054|1084->1054|1085->1055|1087->1057|1087->1057|1090->1060|1090->1060|1091->1061|1093->1063|1093->1063|1098->1068|1098->1068|1099->1069|1099->1069|1100->1070|1100->1070|1105->1075|1105->1075|1107->1077|1107->1077|1107->1077|1108->1078|1109->1079|1109->1079|1110->1080|1111->1081|1111->1081|1111->1081|1111->1081|1112->1082|1113->1083|1113->1083|1114->1084|1114->1084|1116->1086|1116->1086|1116->1086|1117->1087|1117->1087|1117->1087|1118->1088|1123->1093|1123->1093|1123->1093|1123->1093|1124->1094|1129->1099|1129->1099|1129->1099|1129->1099|1130->1100|1135->1105|1135->1105|1137->1107|1137->1107|1139->1109|1139->1109|1139->1109|1140->1110|1153->1123|1153->1123|1154->1124|1159->1129|1159->1129|1159->1129|1159->1129|1160->1130|1165->1135|1165->1135|1167->1137|1183->1153|1183->1153|1184->1154|1185->1155|1185->1155|1187->1157|1187->1157|1189->1159|1190->1160|1190->1160|1191->1161|1196->1166|1196->1166|1197->1167|1198->1168|1198->1168|1199->1169|1202->1172|1202->1172|1203->1173|1204->1174|1204->1174|1205->1175|1205->1175|1207->1177|1207->1177|1207->1177|1209->1179|1209->1179|1210->1180|1210->1180|1211->1181|1211->1181|1213->1183|1213->1183|1214->1184|1214->1184|1215->1185|1215->1185|1217->1187|1217->1187|1218->1188|1220->1190|1222->1192|1236->1206|1236->1206|1236->1206|1236->1206|1237->1207|1237->1207|1237->1207|1238->1208|1241->1211|1241->1211|1242->1212|1243->1213|1243->1213|1244->1214|1244->1214|1244->1214|1245->1215|1245->1215|1245->1215|1245->1215|1246->1216|1246->1216|1247->1217|1247->1217|1249->1219|1249->1219|1249->1219|1250->1220|1251->1221|1251->1221|1252->1222|1253->1223|1253->1223|1254->1224|1254->1224|1254->1224|1255->1225|1258->1228|1258->1228|1259->1229|1260->1230|1260->1230|1261->1231|1261->1231|1261->1231|1276->1246|1276->1246|1278->1248|1278->1248|1278->1248|1279->1249|1288->1258|1288->1258|1289->1259|1290->1260|1290->1260|1291->1261|1293->1263|1293->1263|1294->1264|1294->1264|1294->1264|1294->1264|1295->1265|1296->1266|1296->1266|1297->1267|1299->1269|1299->1269|1300->1270|1300->1270|1300->1270|1300->1270|1301->1271|1302->1272|1302->1272|1303->1273|1305->1275|1305->1275|1306->1276|1306->1276|1306->1276|1306->1276|1307->1277|1308->1278|1308->1278|1309->1279|1312->1282|1312->1282|1313->1283|1315->1285|1315->1285|1316->1286|1316->1286|1317->1287|1318->1288|1318->1288|1319->1289|1320->1290|1320->1290|1322->1292|1322->1292|1322->1292|1323->1293|1325->1295|1325->1295|1327->1297|1328->1298|1328->1298|1329->1299|1331->1301|1331->1301|1332->1302|1333->1303|1333->1303|1334->1304|1335->1305|1335->1305|1336->1306|1336->1306|1336->1306|1336->1306|1336->1306|1336->1306|1337->1307|1337->1307|1338->1308|1338->1308|1338->1308|1339->1309|1341->1311|1341->1311|1342->1312|1345->1315|1345->1315|1345->1315|1345->1315|1346->1316|1348->1318|1348->1318|1349->1319|1349->1319|1349->1319|1349->1319|1350->1320|1354->1324|1354->1324|1355->1325|1355->1325|1358->1328|1358->1328|1358->1328|1359->1329|1360->1330|1360->1330|1361->1331|1362->1332|1362->1332|1366->1336|1366->1336|1367->1337|1372->1342|1372->1342|1373->1343|1377->1347|1377->1347|1378->1348|1381->1351|1381->1351|1382->1352|1383->1353|1383->1353|1384->1354|1384->1354|1385->1355|1385->1355|1387->1357|1387->1357|1387->1357|1388->1358|1392->1362|1392->1362|1393->1363|1398->1368|1398->1368|1399->1369|1400->1370|1400->1370|1401->1371|1402->1372|1402->1372|1403->1373|1406->1376|1406->1376|1407->1377|1410->1380|1410->1380|1411->1381|1412->1382|1412->1382|1413->1383|1413->1383|1414->1384|1414->1384|1416->1386|1416->1386|1416->1386|1417->1387|1421->1391|1421->1391|1422->1392|1428->1398|1428->1398|1429->1399|1430->1400|1430->1400|1431->1401|1432->1402|1432->1402|1433->1403|1436->1406|1436->1406|1437->1407|1440->1410|1440->1410|1441->1411|1442->1412|1442->1412|1443->1413|1443->1413|1444->1414|1444->1414|1446->1416|1446->1416|1446->1416|1447->1417|1451->1421|1451->1421|1452->1422|1458->1428|1458->1428|1459->1429|1462->1432|1462->1432|1463->1433|1466->1436|1466->1436|1467->1437|1468->1438|1468->1438|1469->1439|1469->1439|1470->1440|1470->1440|1472->1442|1472->1442|1472->1442|1473->1443|1475->1445|1475->1445|1476->1446|1483->1453|1483->1453|1484->1454|1485->1455|1485->1455|1486->1456|1486->1456|1487->1457|1487->1457|1494->1464
                  -- GENERATED --
              */
          