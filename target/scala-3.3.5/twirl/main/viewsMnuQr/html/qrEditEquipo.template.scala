
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

object qrEditEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,qr.QrEquipo,List[qr.QrTransacEquipo],List[qr.QrAtributoEquipo],List[qr.QrAtributoEquipo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
qrEquipo: qr.QrEquipo, listaTransac: List[qr.QrTransacEquipo], listaCampos:List[qr.QrAtributoEquipo], listaCamposFiltrado: List[qr.QrAtributoEquipo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario,"PUBLICAR DATOS AL EQUIPO -- CODIFICACION QR","CREAR/MODIFICAR/ELIMINAR")),format.raw/*9.104*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-10 col-sm-8 col-md-8 col-lg-8">
				<table class="table table-bordered ">
					<TR>
						<td style='text-align:left;vertical-align:top'>
							<h5><font color="blue">CODIGO: """),_display_(/*15.40*/qrEquipo/*15.48*/.codigo.toUpperCase()),format.raw/*15.69*/("""</font></h5>
							<h5><font color="blue">EQUIPO: """),_display_(/*16.40*/qrEquipo/*16.48*/.nombre.toUpperCase()),format.raw/*16.69*/("""</font></h5>
						</td>
					</TR>
				</table>
				<input type="hidden" id="auxiliarDeCambio">

				<table id="tablaCampos" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<th colspan="7">ATRIBUTOS ASOCIADOS AL EQUIPO</th>
						</TR>
						<TR>
							<th hidden="true"> IdCampo</th>
							<th hidden="true"> tipo</th>
							<th>ORDEN</th>
							<th>ATRIBUTO</th>
							<th>CONTENIDO</th>
							<th>FECHA DE<br>VENCIMIENTO</th>
							<th>DIAS PRE<br>AVISO</th>
							<th>DEL</th>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*39.8*/for(lista <- listaTransac) yield /*39.34*/{_display_(Seq[Any](format.raw/*39.35*/("""
							"""),format.raw/*40.8*/("""<TR>
								<td hidden="true">"""),_display_(/*41.28*/lista/*41.33*/.id_qrAtributoEquipo),format.raw/*41.53*/("""</td>
								<td hidden="true">"""),_display_(/*42.28*/lista/*42.33*/.id_qrTipoContenido),format.raw/*42.52*/("""</td>
								<td width="5%">
									<input type="text" class="form-control center"
										id="orden"
										value=""""),_display_(/*46.19*/lista/*46.24*/.orden),format.raw/*46.30*/(""""
										onkeydown="return ingresoInt(window.event)"
										onchange="if(value.trim().length==0) value=0; actualizaPorCampo('"""),_display_(/*48.77*/lista/*48.82*/.id_qrAtributoEquipo),format.raw/*48.102*/("""', id, value)">
								</td>
								<td width="20%" style="text-align:left;"><b>"""),_display_(/*50.54*/lista/*50.59*/.campo),format.raw/*50.65*/("""</b></td>
								<td width="70%" style="text-align:left;">
						        	"""),_display_(if(lista.id_qrTipoContenido==1)/*52.48*/{_display_(Seq[Any](format.raw/*52.49*/("""
						        		"""),_display_(if(!lista.contenido.equals("0"))/*53.50*/{_display_(Seq[Any](format.raw/*53.51*/("""
											"""),format.raw/*54.12*/("""<textarea class="form-control" style="text-align:left;" rows="1"
												id="contenido"
												onfocus="$('#auxiliarDeCambio').val(value)"
												onchange="actualizaPorCampo('"""),_display_(/*57.43*/lista/*57.48*/.id_qrAtributoEquipo),format.raw/*57.68*/("""', id, value)" 
		  										autocomplete="off">"""),_display_(/*58.35*/lista/*58.40*/.contenido),format.raw/*58.50*/("""</textarea>
					        			""")))}else/*59.22*/{_display_(Seq[Any](format.raw/*59.23*/("""
											"""),format.raw/*60.12*/("""<textarea class="form-control" style="text-align:left;" rows="1"
												id="contenido"
												onchange="actualizaPorCampo('"""),_display_(/*62.43*/lista/*62.48*/.id_qrAtributoEquipo),format.raw/*62.68*/("""', id, value)" 
		  										autocomplete="off"></textarea>
					        			""")))}),format.raw/*64.18*/("""
						        	""")))}else/*65.21*/{_display_(Seq[Any](format.raw/*65.22*/("""
						        		"""),_display_(if(lista.contenido.equals("0"))/*66.49*/{_display_(Seq[Any](format.raw/*66.50*/("""
						        			"""),format.raw/*67.18*/("""<span class="btn btn-warning btn-sm btn-file">Adjuntar """),_display_(/*67.74*/lista/*67.79*/.tipo),format.raw/*67.84*/("""
												"""),format.raw/*68.13*/("""<input type="file"
						        					id="anexo"""),_display_(/*69.30*/lista/*69.35*/.id_qrAtributoEquipo),format.raw/*69.55*/("""" 
													onfocus="$('#auxiliarDeCambio').val(value)"
						        					onchange="grabarArchivo(this, id, '"""),_display_(/*71.56*/lista/*71.61*/.extencion),format.raw/*71.71*/("""', '"""),_display_(/*71.76*/lista/*71.81*/.id_qrAtributoEquipo),format.raw/*71.101*/("""','"""),_display_(/*71.105*/lista/*71.110*/.tipo),format.raw/*71.115*/("""')">
						        			</span>
						        		""")))}else/*73.22*/{_display_(Seq[Any](format.raw/*73.23*/("""
						        			"""),format.raw/*74.18*/("""<span class="btn btn-warning btn-sm btn-file">Cambiar """),_display_(/*74.73*/lista/*74.78*/.tipo),format.raw/*74.83*/("""
												"""),format.raw/*75.13*/("""<input type="file"
						        					id="anexo"""),_display_(/*76.30*/lista/*76.35*/.id_qrAtributoEquipo),format.raw/*76.55*/("""" 
													onfocus="$('#auxiliarDeCambio').val(value)"
						        					onchange="grabarArchivo(this, id, '"""),_display_(/*78.56*/lista/*78.61*/.extencion),format.raw/*78.71*/("""', '"""),_display_(/*78.76*/lista/*78.81*/.id_qrAtributoEquipo),format.raw/*78.101*/("""','"""),_display_(/*78.105*/lista/*78.110*/.tipo),format.raw/*78.115*/("""')">
						        			</span>&nbsp;&nbsp;&nbsp;&nbsp;
						        			<button class='btn btn-info btn-sm' onclick="verArchivo('"""),_display_(/*80.76*/lista/*80.81*/.contenido),format.raw/*80.91*/("""','"""),_display_(/*80.95*/lista/*80.100*/.extencion),format.raw/*80.110*/("""')" >
												Revisar """),_display_(/*81.22*/lista/*81.27*/.tipo),format.raw/*81.32*/(""" """),format.raw/*81.33*/("""Adjunto
											</button>
						        		""")))}),format.raw/*83.18*/("""
						        	""")))}),format.raw/*84.17*/("""
					        	"""),format.raw/*85.15*/("""</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none;">"""),_display_(/*87.38*/lista/*87.43*/.fechaVencimiento),format.raw/*87.60*/("""</div>
									<input type="date" class="form-control center"
										id="fechaVencimiento"
										value=""""),_display_(/*90.19*/lista/*90.24*/.fechaVencimiento),format.raw/*90.41*/(""""
										onblur="actualizaPorCampo('"""),_display_(/*91.39*/lista/*91.44*/.id_qrAtributoEquipo),format.raw/*91.64*/("""', id, value)">
								</td>
								<td>
									<input type="text" class="form-control center"
										id="diasPreAviso"
										value=""""),_display_(/*96.19*/lista/*96.24*/.diasPreAviso),format.raw/*96.37*/(""""
										onkeydown="return ingresoInt(window.event)"
										onchange="if(value.trim().length==0) value=0; actualizaPorCampo('"""),_display_(/*98.77*/lista/*98.82*/.id_qrAtributoEquipo),format.raw/*98.102*/("""', id, value)">
								</td>
								<td width="5%" style="text-align:center;">
									<a href="#" onclick= "eliminar(this, '"""),_display_(/*101.49*/lista/*101.54*/.id_qrAtributoEquipo),format.raw/*101.74*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
						""")))}),format.raw/*106.8*/("""
						"""),format.raw/*107.7*/("""<TR>
							<td hidden="true"></td>
							<td hidden="true"></td>
							<td width="5%"></td>
					        <td width="30%" style="text-align:left;">
								<select class="custom-select" 
									onchange="agregarCampo(value)">
									<option value=""></option>
									"""),_display_(/*115.11*/for(lista <- listaCamposFiltrado) yield /*115.44*/{_display_(Seq[Any](format.raw/*115.45*/("""
										"""),format.raw/*116.11*/("""<option value=""""),_display_(/*116.27*/lista/*116.32*/.id),format.raw/*116.35*/("""">"""),_display_(/*116.38*/lista/*116.43*/.nombre),format.raw/*116.50*/("""</option>
									""")))}),format.raw/*117.11*/("""
								"""),format.raw/*118.9*/("""</select>
					        </td>
					        <td width="60%" style="text-align:left;"></td>
							<td width="30%" style="text-align:left;">
							<td style="text-align:left;">
					        <td width="5%"></td>
						</TR>
					</tbody>
				</table>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button" value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href = '/qrListaEquipos/';">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	

		<div id="muestraPDF" class="modal" role="dialog" data-backdrop="static" data-keyboard="false" aria-hidden="true" style="display: none;">
			<div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">×</span>
					        </button>
					</div>
					<div class="modal-body">
						<div class='embed-responsive' style='padding-bottom:150%'>
							<div id="rutaPDF"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
""")))}),format.raw/*157.2*/("""




"""),format.raw/*162.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*163.31*/("""{"""),format.raw/*163.32*/("""
		  """),format.raw/*164.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*165.2*/("""}"""),format.raw/*165.3*/(""");

	const agregarCampo = (idCampo) => """),format.raw/*167.36*/("""{"""),format.raw/*167.37*/("""
		  	"""),format.raw/*168.6*/("""var tabla = document.getElementById('tablaCampos');
		  	var cellsOfRow = tabla.rows[tabla.rows.length-1].getElementsByTagName('td');
		  	//llena la linea de la tabla
		  	var idTipo="";
		  	var nombre="";
		  	"""),_display_(/*173.7*/for(lista <- listaCampos) yield /*173.32*/{_display_(Seq[Any](format.raw/*173.33*/("""
        		"""),format.raw/*174.11*/("""if("""),_display_(/*174.15*/lista/*174.20*/.id),format.raw/*174.23*/("""==idCampo)"""),format.raw/*174.33*/("""{"""),format.raw/*174.34*/("""
        			"""),format.raw/*175.12*/("""cellsOfRow[0].innerHTML=""""),_display_(/*175.38*/lista/*175.43*/.id),format.raw/*175.46*/("""";
        			cellsOfRow[1].innerHTML=""""),_display_(/*176.38*/lista/*176.43*/.id_qrTipoContenido),format.raw/*176.62*/("""";
        			cellsOfRow[2].innerHTML="";
        			cellsOfRow[3].innerHTML="<b>"""),_display_(/*178.41*/lista/*178.46*/.nombre),format.raw/*178.53*/("""</b>";
        			cellsOfRow[4].setAttribute("style","text-align:left;");
        			if("""),_display_(/*180.16*/lista/*180.21*/.id_qrTipoContenido),format.raw/*180.40*/("""==1)"""),format.raw/*180.44*/("""{"""),format.raw/*180.45*/("""
        				"""),format.raw/*181.13*/("""cellsOfRow[4].innerHTML=
								"<textarea class='form-control' style='text-align:left;' rows='1' "+
										" id='contenido' "+
										" onfocus=\"$('#auxiliarDeCambio').val(value)\" "+
										" onchange='actualizaPorCampo("+idCampo+",id,value)' "+
										" autocomplete='off'></textarea>";
        			"""),format.raw/*187.12*/("""}"""),format.raw/*187.13*/("""else"""),format.raw/*187.17*/("""{"""),format.raw/*187.18*/("""
        				
        				"""),format.raw/*189.13*/("""cellsOfRow[4].innerHTML=
								"<span class='btn btn-warning btn-sm btn-file'>Adjuntar """),_display_(/*190.66*/lista/*190.71*/.tipoContenido),format.raw/*190.85*/(""" """),format.raw/*190.86*/(""""+
										" <input type='file' "+
						        				" id='anexo"""),_display_(/*192.31*/lista/*192.36*/.id),format.raw/*192.39*/("""' "+
												" value='0' "+
												" onfocus=\"$('#auxiliarDeCambio').val(value)\" "+
						        				" onchange='grabarArchivo(this,id,\""""),_display_(/*195.56*/lista/*195.61*/.extencion),format.raw/*195.71*/("""\","""),_display_(/*195.75*/lista/*195.80*/.id),format.raw/*195.83*/(""",\""""),_display_(/*195.87*/lista/*195.92*/.tipoContenido),format.raw/*195.106*/("""\")'> "+
						        "</span>";
        			"""),format.raw/*197.12*/("""}"""),format.raw/*197.13*/("""
					"""),format.raw/*198.6*/("""cellsOfRow[5].setAttribute("style","text-align:center;");
					cellsOfRow[5].innerHTML=
							"<input type=\"date\" class=\"form-control center\" " +
							" id=\"fechaVencimiento\" " +
							" onblur='actualizaPorCampo("+idCampo+", id, value)'>";

					cellsOfRow[6].setAttribute("style","text-align:center;");
					cellsOfRow[6].innerHTML=
							"<input type=\"text\" class=\"form-control center\" " +
							" id=\"diasPreAviso\" " +
							" onkeydown=\"return ingresoInt(window.event)\""+
							" onchange='if(value.trim().length==0) value=0; actualizaPorCampo("+idCampo+", id, value)'>";

					cellsOfRow[7].setAttribute("style","text-align:center;");
        			cellsOfRow[7].innerHTML=
									"<a href='#' onclick='eliminar(this,"+idCampo+")'>"+
										"<kbd style='background-color: red'>X</kbd>"+
									"</a>";
        		"""),format.raw/*216.11*/("""}"""),format.raw/*216.12*/("""
			""")))}),format.raw/*217.5*/("""
		  	"""),format.raw/*218.6*/("""//grabo el campo en la base
		  	var formData = new FormData();
		  	formData.append('idEquipo','"""),_display_(/*220.35*/qrEquipo/*220.43*/.id_equipo),format.raw/*220.53*/("""');
		  	formData.append('idCampo',idCampo);
            $.ajax("""),format.raw/*222.20*/("""{"""),format.raw/*222.21*/("""
                """),format.raw/*223.17*/("""url: "/qrAgregaCampoEquipo/",
                type: "POST",
                method: "POST",
                data: formData,
                cache: false,
                contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*230.37*/("""{"""),format.raw/*230.38*/("""
		     		"""),format.raw/*231.10*/("""if(respuesta.status)"""),format.raw/*231.30*/("""{"""),format.raw/*231.31*/("""
		     			"""),format.raw/*232.11*/("""cellsOfRow[2].innerHTML=
								"<input type='text' class='form-control center' "+
										" id='orden' "+
										" value='"+respuesta.orden+"' "+
										" onkeydown='return ingresoInt(window.event)' "+
										" onchange='if(value.trim().length==0) value=0;actualizaPorCampo("+idCampo+",id,value)'>";
		     			agregarLinea();
		     		"""),format.raw/*239.10*/("""}"""),format.raw/*239.11*/("""else"""),format.raw/*239.15*/("""{"""),format.raw/*239.16*/("""
		     			"""),format.raw/*240.11*/("""tabla.deleteRow(tabla.rows.length-1);
		     			alert("se presento un problema");
		     			agregarLinea();
		     		"""),format.raw/*243.10*/("""}"""),format.raw/*243.11*/("""
		     	"""),format.raw/*244.9*/("""}"""),format.raw/*244.10*/("""
	        """),format.raw/*245.10*/("""}"""),format.raw/*245.11*/("""); 
            
	"""),format.raw/*247.2*/("""}"""),format.raw/*247.3*/("""
	

	"""),format.raw/*250.2*/("""const agregarLinea = () => """),format.raw/*250.29*/("""{"""),format.raw/*250.30*/("""
		"""),format.raw/*251.3*/("""var tabla = document.getElementById('tablaCampos');
	  	//agrega nueva linea a la tabla
		var newRow = tabla.insertRow(tabla.rows.length);
	  	for(var i=0;i<6;i++)"""),format.raw/*254.25*/("""{"""),format.raw/*254.26*/("""
	  		"""),format.raw/*255.6*/("""newRow.insertCell(i);
	  	"""),format.raw/*256.5*/("""}"""),format.raw/*256.6*/("""
		"""),format.raw/*257.3*/("""let flag=0;
		var selectCampo=
			"<select class='custom-select' "+
				"onchange='agregarCampo(value)'>"+
				"<option value=''></option>";
        	"""),_display_(/*262.11*/for(lista <- listaCampos) yield /*262.36*/{_display_(Seq[Any](format.raw/*262.37*/("""
        		"""),format.raw/*263.11*/("""//reconoce campos ya agregados
        		flag=0;
        		for(var i=2;i<tabla.rows.length-1;i++)"""),format.raw/*265.49*/("""{"""),format.raw/*265.50*/("""
			  		"""),format.raw/*266.8*/("""var auxCellOfRow = tabla.rows[i].getElementsByTagName('td');
			  		if(auxCellOfRow[0].innerHTML=="""),_display_(/*267.39*/lista/*267.44*/.id),format.raw/*267.47*/(""")"""),format.raw/*267.48*/("""{"""),format.raw/*267.49*/("""
			  			"""),format.raw/*268.9*/("""flag = 1;
			  			break;
			  		"""),format.raw/*270.8*/("""}"""),format.raw/*270.9*/("""
			  	"""),format.raw/*271.7*/("""}"""),format.raw/*271.8*/("""
        		"""),format.raw/*272.11*/("""if(flag==0)"""),format.raw/*272.22*/("""{"""),format.raw/*272.23*/("""
        			"""),format.raw/*273.12*/("""selectCampo = selectCampo + "<option value='"""),_display_(/*273.57*/lista/*273.62*/.id),format.raw/*273.65*/("""'>"""),_display_(/*273.68*/lista/*273.73*/.nombre),format.raw/*273.80*/("""</option>";
        		"""),format.raw/*274.11*/("""}"""),format.raw/*274.12*/("""
			""")))}),format.raw/*275.5*/("""
        """),format.raw/*276.9*/("""selectCampo = selectCampo + "</select>";
        var cellsOfRow = tabla.rows[tabla.rows.length-1].getElementsByTagName('td');
        cellsOfRow[0].setAttribute("width","5%");
        cellsOfRow[0].setAttribute("hidden","true");
        cellsOfRow[1].setAttribute("width","5%");
        cellsOfRow[1].setAttribute("hidden","true");
        cellsOfRow[2].setAttribute("width","5%");
        cellsOfRow[3].setAttribute("width","30%");
        cellsOfRow[3].setAttribute("style","text-align:left;");
        cellsOfRow[4].setAttribute("width","60%");
        cellsOfRow[5].setAttribute("width","5%");
        cellsOfRow[3].innerHTML=selectCampo;
	"""),format.raw/*288.2*/("""}"""),format.raw/*288.3*/("""
	
	"""),format.raw/*290.2*/("""const actualizaPorCampo = (idCampo,campoMySql,valor) => """),format.raw/*290.58*/("""{"""),format.raw/*290.59*/("""
    	  """),format.raw/*291.8*/("""var formData = new FormData();
		  	formData.append('idEquipo','"""),_display_(/*292.35*/qrEquipo/*292.43*/.id_equipo),format.raw/*292.53*/("""');
		  	formData.append('idCampo',idCampo);
		  	formData.append('campoMySql',campoMySql);
		  	formData.append('valor',valor);
            $.ajax("""),format.raw/*296.20*/("""{"""),format.raw/*296.21*/("""
                """),format.raw/*297.17*/("""url: "/qrActualizaPorCampo/",
                type: "POST",
                method: "POST",
                data: formData,
                cache: false,
                contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*304.37*/("""{"""),format.raw/*304.38*/("""
		     		"""),format.raw/*305.10*/("""if(!respuesta.status)"""),format.raw/*305.31*/("""{"""),format.raw/*305.32*/("""
		     			"""),format.raw/*306.11*/("""alert("se presento un problema");
		     		"""),format.raw/*307.10*/("""}"""),format.raw/*307.11*/("""
		     	"""),format.raw/*308.9*/("""}"""),format.raw/*308.10*/("""
	        """),format.raw/*309.10*/("""}"""),format.raw/*309.11*/("""); 
     """),format.raw/*310.6*/("""}"""),format.raw/*310.7*/("""

	"""),format.raw/*312.2*/("""const eliminar = (nodo,idCampo) => """),format.raw/*312.37*/("""{"""),format.raw/*312.38*/("""
		"""),format.raw/*313.3*/("""alertify.confirm("Esta seguro de eliminar ", function (e) """),format.raw/*313.61*/("""{"""),format.raw/*313.62*/("""
			"""),format.raw/*314.4*/("""if (e) """),format.raw/*314.11*/("""{"""),format.raw/*314.12*/("""
				"""),format.raw/*315.5*/("""var formData = new FormData();
				formData.append('idEquipo','"""),_display_(/*316.34*/qrEquipo/*316.42*/.id_equipo),format.raw/*316.52*/("""');
			  	formData.append('idCampo',idCampo);
		        $.ajax("""),format.raw/*318.18*/("""{"""),format.raw/*318.19*/("""
		            """),format.raw/*319.15*/("""url: "/qrEliminaCampoEquipo/",
		            type: "POST",
		            method: "POST",
		            data: formData,
		            cache: false,
		            contentType: false,
			     	processData: false,
			     	success: function(respuesta)"""),format.raw/*326.38*/("""{"""),format.raw/*326.39*/("""
			     		"""),format.raw/*327.11*/("""if(!respuesta.status)"""),format.raw/*327.32*/("""{"""),format.raw/*327.33*/("""
			     			"""),format.raw/*328.12*/("""alert("se presento un problema");
			     		"""),format.raw/*329.11*/("""}"""),format.raw/*329.12*/("""
			     	"""),format.raw/*330.10*/("""}"""),format.raw/*330.11*/("""
		        """),format.raw/*331.11*/("""}"""),format.raw/*331.12*/("""); 
		        nodo.parentNode.parentNode.remove();
			"""),format.raw/*333.4*/("""}"""),format.raw/*333.5*/("""
		"""),format.raw/*334.3*/("""}"""),format.raw/*334.4*/(""");
	"""),format.raw/*335.2*/("""}"""),format.raw/*335.3*/("""
	
	"""),format.raw/*337.2*/("""const grabarArchivo = (nodo,id,extencion,idCampo,tipo) => """),format.raw/*337.60*/("""{"""),format.raw/*337.61*/("""
		"""),format.raw/*338.3*/("""var file = $("#"+id)[0].files[0];
		var fileName = file.name;
		var fileExtencion = fileName.substring(fileName.lastIndexOf('.') + 1);
		if(extencion.trim().toUpperCase() == fileExtencion.trim().toUpperCase())"""),format.raw/*341.75*/("""{"""),format.raw/*341.76*/("""
			"""),format.raw/*342.4*/("""var formData = new FormData();
            formData.append('anexo',file);
            formData.append('extencion',fileExtencion);
            formData.append('idEquipo','"""),_display_(/*345.42*/qrEquipo/*345.50*/.id_equipo),format.raw/*345.60*/("""');
		  	formData.append('idCampo',idCampo);
            $.ajax("""),format.raw/*347.20*/("""{"""),format.raw/*347.21*/("""
                """),format.raw/*348.17*/("""url: "/qrGrabaAnexoEquipo/",
                type: "POST",
                method: "POST",
                data: formData,
                cache: false,
                contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*355.37*/("""{"""),format.raw/*355.38*/("""
		     		"""),format.raw/*356.10*/("""if(respuesta.status)"""),format.raw/*356.30*/("""{"""),format.raw/*356.31*/("""
		     			"""),format.raw/*357.11*/("""nodo.parentNode.parentNode.innerHTML=
							"<span class='btn btn-warning btn-sm btn-file'>Cambiar "+tipo+
									"<input type='file' "+
						        			" id='"+id+"' "+ 
											" onfocus=\"$('#auxiliarDeCambio').val(value)\" "+
											" onchange='grabarArchivo(this,id,\""+extencion+"\","+idCampo+",\""+tipo+"\")'>"+
							"</span>&nbsp;&nbsp;&nbsp;&nbsp;"+
						    "<button class='btn btn-info btn-sm' onclick='verArchivo(\""+respuesta.archivo+"\",\""+extencion+"\")'>"+
								"Revisar "+tipo+" Adjunto</button>";
	        			actualizaPorCampo(idCampo,'contenido',respuesta.archivo);
		     		"""),format.raw/*367.10*/("""}"""),format.raw/*367.11*/("""else"""),format.raw/*367.15*/("""{"""),format.raw/*367.16*/("""
		     			"""),format.raw/*368.11*/("""alert("se presento un problema");
		     		"""),format.raw/*369.10*/("""}"""),format.raw/*369.11*/("""
		     	"""),format.raw/*370.9*/("""}"""),format.raw/*370.10*/("""
	        """),format.raw/*371.10*/("""}"""),format.raw/*371.11*/("""); 
		"""),format.raw/*372.3*/("""}"""),format.raw/*372.4*/("""else"""),format.raw/*372.8*/("""{"""),format.raw/*372.9*/("""
			"""),format.raw/*373.4*/("""alert("Se permiten únicamente archivos con la extención: ." 
					+ extencion + "\nPor favor, seleccione otro archivo "
					+ "e intente de nuevo.");
		"""),format.raw/*376.3*/("""}"""),format.raw/*376.4*/("""
	"""),format.raw/*377.2*/("""}"""),format.raw/*377.3*/("""


	"""),format.raw/*380.2*/("""const verArchivo = (archivo,extencion) => """),format.raw/*380.44*/("""{"""),format.raw/*380.45*/("""
		"""),format.raw/*381.3*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+archivo+"' type='application/pdf' width='100%' height='720'></object>";
		$('#muestraPDF').modal('show');
	"""),format.raw/*383.2*/("""}"""),format.raw/*383.3*/("""



"""),format.raw/*387.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,qrEquipo:qr.QrEquipo,listaTransac:List[qr.QrTransacEquipo],listaCampos:List[qr.QrAtributoEquipo],listaCamposFiltrado:List[qr.QrAtributoEquipo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,qrEquipo,listaTransac,listaCampos,listaCamposFiltrado)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,qr.QrEquipo,List[qr.QrTransacEquipo],List[qr.QrAtributoEquipo],List[qr.QrAtributoEquipo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,qrEquipo,listaTransac,listaCampos,listaCamposFiltrado) => apply(mapDiccionario,mapPermiso,userMnu,qrEquipo,listaTransac,listaCampos,listaCamposFiltrado)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/qrEditEquipo.scala.html
                  HASH: 66639b09f1049ff9560197a3ba5051520c663af6
                  MATRIX: 1095->1|1435->248|1468->256|1484->264|1523->266|1551->269|1619->317|1647->319|1723->370|1844->470|1874->473|2145->717|2162->725|2204->746|2283->798|2300->806|2342->827|3000->1459|3042->1485|3081->1486|3116->1494|3175->1526|3189->1531|3230->1551|3290->1584|3304->1589|3344->1608|3496->1733|3510->1738|3537->1744|3696->1876|3710->1881|3752->1901|3862->1984|3876->1989|3903->1995|4037->2102|4076->2103|4153->2153|4192->2154|4232->2166|4449->2356|4463->2361|4504->2381|4581->2431|4595->2436|4626->2446|4678->2479|4717->2480|4757->2492|4918->2626|4932->2631|4973->2651|5082->2729|5122->2750|5161->2751|5237->2800|5276->2801|5322->2819|5405->2875|5419->2880|5445->2885|5486->2898|5561->2946|5575->2951|5616->2971|5758->3086|5772->3091|5803->3101|5835->3106|5849->3111|5891->3131|5923->3135|5938->3140|5965->3145|6035->3196|6074->3197|6120->3215|6202->3270|6216->3275|6242->3280|6283->3293|6358->3341|6372->3346|6413->3366|6555->3481|6569->3486|6600->3496|6632->3501|6646->3506|6688->3526|6720->3530|6735->3535|6762->3540|6918->3669|6932->3674|6963->3684|6994->3688|7009->3693|7041->3703|7095->3730|7109->3735|7135->3740|7164->3741|7241->3787|7289->3804|7332->3819|7459->3919|7473->3924|7511->3941|7651->4054|7665->4059|7703->4076|7770->4116|7784->4121|7825->4141|7997->4286|8011->4291|8045->4304|8204->4436|8218->4441|8260->4461|8417->4590|8432->4595|8474->4615|8611->4721|8646->4728|8950->5004|9000->5037|9040->5038|9080->5049|9124->5065|9139->5070|9164->5073|9195->5076|9210->5081|9239->5088|9291->5108|9328->5117|10591->6349|10624->6354|10715->6416|10745->6417|10778->6422|10872->6488|10901->6489|10969->6528|10999->6529|11033->6535|11274->6749|11316->6774|11356->6775|11396->6786|11428->6790|11443->6795|11468->6798|11507->6808|11537->6809|11578->6821|11632->6847|11647->6852|11672->6855|11740->6895|11755->6900|11796->6919|11906->7001|11921->7006|11950->7013|12067->7102|12082->7107|12123->7126|12156->7130|12186->7131|12228->7144|12572->7459|12602->7460|12635->7464|12665->7465|12720->7491|12838->7581|12853->7586|12889->7600|12919->7601|13014->7668|13029->7673|13054->7676|13232->7826|13247->7831|13279->7841|13311->7845|13326->7850|13351->7853|13383->7857|13398->7862|13435->7876|13509->7921|13539->7922|13573->7928|14446->8772|14476->8773|14512->8778|14546->8784|14672->8882|14690->8890|14722->8900|14815->8964|14845->8965|14891->8982|15174->9236|15204->9237|15243->9247|15292->9267|15322->9268|15362->9279|15739->9627|15769->9628|15802->9632|15832->9633|15872->9644|16018->9761|16048->9762|16085->9771|16115->9772|16154->9782|16184->9783|16230->9801|16259->9802|16292->9807|16348->9834|16378->9835|16409->9838|16601->10001|16631->10002|16665->10008|16719->10034|16748->10035|16779->10038|16958->10189|17000->10214|17040->10215|17080->10226|17206->10323|17236->10324|17272->10332|17399->10431|17414->10436|17439->10439|17469->10440|17499->10441|17536->10450|17596->10482|17625->10483|17660->10490|17689->10491|17729->10502|17769->10513|17799->10514|17840->10526|17913->10571|17928->10576|17953->10579|17984->10582|17999->10587|18028->10594|18079->10616|18109->10617|18145->10622|18182->10631|18854->11275|18883->11276|18915->11280|19000->11336|19030->11337|19066->11345|19159->11410|19177->11418|19209->11428|19386->11576|19416->11577|19462->11594|19745->11848|19775->11849|19814->11859|19864->11880|19894->11881|19934->11892|20006->11935|20036->11936|20073->11945|20103->11946|20142->11956|20172->11957|20209->11966|20238->11967|20269->11970|20333->12005|20363->12006|20394->12009|20481->12067|20511->12068|20543->12072|20579->12079|20609->12080|20642->12085|20734->12149|20752->12157|20784->12167|20876->12230|20906->12231|20950->12246|21226->12493|21256->12494|21296->12505|21346->12526|21376->12527|21417->12539|21490->12583|21520->12584|21559->12594|21589->12595|21629->12606|21659->12607|21741->12661|21770->12662|21801->12665|21830->12666|21862->12670|21891->12671|21923->12675|22010->12733|22040->12734|22071->12737|22309->12946|22339->12947|22371->12951|22570->13122|22588->13130|22620->13140|22713->13204|22743->13205|22789->13222|23071->13475|23101->13476|23140->13486|23189->13506|23219->13507|23259->13518|23900->14130|23930->14131|23963->14135|23993->14136|24033->14147|24105->14190|24135->14191|24172->14200|24202->14201|24241->14211|24271->14212|24305->14218|24334->14219|24366->14223|24395->14224|24427->14228|24608->14381|24637->14382|24667->14384|24696->14385|24728->14389|24799->14431|24829->14432|24860->14435|25066->14613|25095->14614|25127->14618
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|46->15|46->15|46->15|47->16|47->16|47->16|70->39|70->39|70->39|71->40|72->41|72->41|72->41|73->42|73->42|73->42|77->46|77->46|77->46|79->48|79->48|79->48|81->50|81->50|81->50|83->52|83->52|84->53|84->53|85->54|88->57|88->57|88->57|89->58|89->58|89->58|90->59|90->59|91->60|93->62|93->62|93->62|95->64|96->65|96->65|97->66|97->66|98->67|98->67|98->67|98->67|99->68|100->69|100->69|100->69|102->71|102->71|102->71|102->71|102->71|102->71|102->71|102->71|102->71|104->73|104->73|105->74|105->74|105->74|105->74|106->75|107->76|107->76|107->76|109->78|109->78|109->78|109->78|109->78|109->78|109->78|109->78|109->78|111->80|111->80|111->80|111->80|111->80|111->80|112->81|112->81|112->81|112->81|114->83|115->84|116->85|118->87|118->87|118->87|121->90|121->90|121->90|122->91|122->91|122->91|127->96|127->96|127->96|129->98|129->98|129->98|132->101|132->101|132->101|137->106|138->107|146->115|146->115|146->115|147->116|147->116|147->116|147->116|147->116|147->116|147->116|148->117|149->118|188->157|193->162|194->163|194->163|195->164|196->165|196->165|198->167|198->167|199->168|204->173|204->173|204->173|205->174|205->174|205->174|205->174|205->174|205->174|206->175|206->175|206->175|206->175|207->176|207->176|207->176|209->178|209->178|209->178|211->180|211->180|211->180|211->180|211->180|212->181|218->187|218->187|218->187|218->187|220->189|221->190|221->190|221->190|221->190|223->192|223->192|223->192|226->195|226->195|226->195|226->195|226->195|226->195|226->195|226->195|226->195|228->197|228->197|229->198|247->216|247->216|248->217|249->218|251->220|251->220|251->220|253->222|253->222|254->223|261->230|261->230|262->231|262->231|262->231|263->232|270->239|270->239|270->239|270->239|271->240|274->243|274->243|275->244|275->244|276->245|276->245|278->247|278->247|281->250|281->250|281->250|282->251|285->254|285->254|286->255|287->256|287->256|288->257|293->262|293->262|293->262|294->263|296->265|296->265|297->266|298->267|298->267|298->267|298->267|298->267|299->268|301->270|301->270|302->271|302->271|303->272|303->272|303->272|304->273|304->273|304->273|304->273|304->273|304->273|304->273|305->274|305->274|306->275|307->276|319->288|319->288|321->290|321->290|321->290|322->291|323->292|323->292|323->292|327->296|327->296|328->297|335->304|335->304|336->305|336->305|336->305|337->306|338->307|338->307|339->308|339->308|340->309|340->309|341->310|341->310|343->312|343->312|343->312|344->313|344->313|344->313|345->314|345->314|345->314|346->315|347->316|347->316|347->316|349->318|349->318|350->319|357->326|357->326|358->327|358->327|358->327|359->328|360->329|360->329|361->330|361->330|362->331|362->331|364->333|364->333|365->334|365->334|366->335|366->335|368->337|368->337|368->337|369->338|372->341|372->341|373->342|376->345|376->345|376->345|378->347|378->347|379->348|386->355|386->355|387->356|387->356|387->356|388->357|398->367|398->367|398->367|398->367|399->368|400->369|400->369|401->370|401->370|402->371|402->371|403->372|403->372|403->372|403->372|404->373|407->376|407->376|408->377|408->377|411->380|411->380|411->380|412->381|414->383|414->383|418->387
                  -- GENERATED --
              */
          