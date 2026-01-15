
package viewsMnuMovimientos.html

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

object movimientoSelectModificar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, desde: String, hasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalContactoBodega(mapDiccionario)),format.raw/*8.38*/("""
	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "LISTADO DE MOVIMIENTOS Y DESPACHOS EFECTUADOS","MODIFICAR y/o ELIMINAR (SOLO MOVIMIENTOS)")),format.raw/*11.124*/("""
		
		"""),format.raw/*13.3*/("""<div id="enCarga" class="blocker" style="display: block;"><br><br><br><br><br><br><h1>En proceso .....</h1></div>

		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<div id="tblPrincipal"></div>
			</div>
		</div>
		<div class="noprint">
			<div id="listo"></div>
		</div>
	</div>
	
	<div id='modalVerOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class="modal-title">"""),_display_(/*29.31*/mapDiccionario/*29.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*29.69*/("""</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">×</span>
				        </button>
			</div>
			<div class="modal-body">
				<div id="mostrarLaOt"></div>
   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalVerCotizacion' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>COTIZACION</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id='mostrarLaCotizacion'></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>MOVIMIENTO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="rutaPDF"></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	<form id="form_eliminar" method="post" action="/movimientoOrigenDestinoElimina/">
		<input type="hidden" id="form_id_guia" name="id_guia">
	</form>
	
	
""")))}),format.raw/*97.2*/("""


"""),format.raw/*100.1*/("""<script type="text/javascript">
	
	window.addEventListener('load', function() """),format.raw/*102.45*/("""{"""),format.raw/*102.46*/("""
		"""),format.raw/*103.3*/("""document.getElementById('mostrarmostrar').style.display="block";
		vistaPrincipal();
		
	"""),format.raw/*106.2*/("""}"""),format.raw/*106.3*/(""");
	
	const vistaPrincipal =() =>"""),format.raw/*108.29*/("""{"""),format.raw/*108.30*/("""
		
		"""),format.raw/*110.3*/("""document.getElementById('enCarga').style.display="block";
		
		var formData = new FormData();
		formData.append('fechaDesde','"""),_display_(/*113.34*/desde),format.raw/*113.39*/("""');
		formData.append('fechaHasta','"""),_display_(/*114.34*/hasta),format.raw/*114.39*/("""');
        $.ajax("""),format.raw/*115.16*/("""{"""),format.raw/*115.17*/("""
            """),format.raw/*116.13*/("""url: "/routes2/movimientoSelectModificar2/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(rs)"""),format.raw/*123.29*/("""{"""),format.raw/*123.30*/("""
		
				"""),format.raw/*125.5*/("""let jsonGuias = rs.jsonGuias;
				let tabla="<table id=\"tablaPrincipal\"class=\"table table-sm table-hover table-bordered table-condensed table-fluid\">"+"<thead style=\"background-color:#eeeeee\">"
				+"<TR>"+"<TH>TIPO</TH>"
				+"<TH>COMERCIAL</TH>"
				+"<TH>Nro<br>"+'"""),_display_(/*129.22*/mapDiccionario/*129.36*/.get("OT")),format.raw/*129.46*/("""'+"</TH>"
				+"<TH>Nro<br>COTI</TH>"
				+"<TH>Nro<br>MOV</TH>"
				+"<TH>Nro<br>REF</TH>"
				+"<TH style=\"min-width:80px;\">FECHA<br>MOV</TH>"
				+"<TH style=\"min-width:80px;\">FECHA<br>INI/TER</TH>"
				+ "<TH>EDIT</TH>"
				+"<TH>SUCURSAL<br>DESDE</TH>"
				+"<TH>DESDE<br>"+'"""),_display_(/*137.24*/mapDiccionario/*137.38*/.get("BODEGA")),format.raw/*137.52*/("""'+"/PROYECTO</TH>"
				+"<TH>SUCURSAL<br>HASTA</TH>"
				+"<TH>HASTA<br>"+'"""),_display_(/*139.24*/mapDiccionario/*139.38*/.get("BODEGA")),format.raw/*139.52*/("""'+"/PROYECTO</TH>"+
				"<TH>EDIT</TH>"
				+"<TH>GUIA</TH>"
				+"<TH>ANEXO</TH>"
				+"<TH>ALBUM</TH>"
				+"<TH>DEL</TH>"
				+"</TR>"+"</thead>"+"<tbody>";
				
				for(var i in jsonGuias)"""),format.raw/*147.28*/("""{"""),format.raw/*147.29*/("""
					"""),format.raw/*148.6*/("""tabla = tabla +
					"<TR>"+
						"<td style=\"text-align:left;\">"+jsonGuias[i]["tipoGuia"]+"</td>"+
						"<td style=\"text-align:left;\">"+jsonGuias[i]["nameComercial"]+"</td>"+
						
						"<td style=\"text-align:center;\">";
							if(jsonGuias[i]["numeroCotizacion"]!="0")"""),format.raw/*154.49*/("""{"""),format.raw/*154.50*/("""
								"""),format.raw/*155.9*/("""tabla+="<a href=\"#\"onclick=\"verOt('"+jsonGuias[i]["id_ot"]+"')\">"
											+"<kbd style=\"background-color:#73C6B6\">"+jsonGuias[i]["numeroOt"]+"</kbd>"
									  +"</a>";"""),format.raw/*157.20*/("""}"""),format.raw/*157.21*/("""else"""),format.raw/*157.25*/("""{"""),format.raw/*157.26*/("""tabla+="--";"""),format.raw/*157.38*/("""}"""),format.raw/*157.39*/("""
						"""),format.raw/*158.7*/("""tabla+="</td>"+
						
						
						
					"<td style=\"text-align:center;\">";
						if(jsonGuias[i]["numeroCotizacion"]!="0")"""),format.raw/*163.48*/("""{"""),format.raw/*163.49*/("""
							"""),format.raw/*164.8*/("""tabla+=jsonGuias[i]["numeroCotizacion"];"""),format.raw/*164.48*/("""}"""),format.raw/*164.49*/("""else"""),format.raw/*164.53*/("""{"""),format.raw/*164.54*/("""tabla+="--";"""),format.raw/*164.66*/("""}"""),format.raw/*164.67*/("""
					"""),format.raw/*165.6*/("""tabla+="</td>"
					+"<td style=\"text-align:center;\">"+jsonGuias[i]["numero"]+"</td>"
					+"<td style=\"text-align:center;\">"
						+jsonGuias[i]["numGuiaCliente"]
					+"</td>"
					+"<td style=\"text-align:center;\">"+"<div style=\"display:none\">"+jsonGuias[i]["fecha"]+"</div>"+""+fechaDDMMAA(jsonGuias[i]["fecha"])
					+"<td style=\"text-align:center;\">"+"<div style=\"display:none\">"+jsonGuias[i]["fechaIniTerGuia"]+"</div>"+""+fechaDDMMAA(jsonGuias[i]["fechaIniTerGuia"])
					+"</td>"
					+"<td style=\"text-align:center;\">";if(jsonGuias[i]["numeroCotizacion"]=="0")"""),format.raw/*173.84*/("""{"""),format.raw/*173.85*/("""tabla+="<form id=\"form_"+jsonGuias[i]["id"]
					+"\"method=\"post\"action=\"/movimientoOrigenDestinoModifica/\">"+"<input type=\"hidden\"name=\"id_guia\"value=\""+jsonGuias[i]["id"]+"\">"+"<a href=\"#\"onclick='document.getElementById(\"form_"
					+jsonGuias[i]["id"]+"\").submit();'>"+"<kbd style=\"background-color:#73C6B6\">edit</kbd>"+"</a>"+"</form>";"""),format.raw/*175.114*/("""}"""),format.raw/*175.115*/("""else"""),format.raw/*175.119*/("""{"""),format.raw/*175.120*/("""tabla+="--";"""),format.raw/*175.132*/("""}"""),format.raw/*175.133*/("""tabla+="</td>"
					+"<td style=\"text-align:left;\">"+jsonGuias[i]["nameSucursalOrigen"]+"</td>"+"<td style=\"text-align:left;\"><a href=\"#\"onclick=\"buscaBodega('"+jsonGuias[i]["bodegaOrigen"]
					+"')\">"+jsonGuias[i]["bodegaOrigen"]+"</a></td>"+"<td style=\"text-align:left;\">"+jsonGuias[i]["nameSucursalDestino"]+"</td>"+"<td style=\"text-align:left;\"><a href=\"#\"onclick=\"buscaBodega('"
					+jsonGuias[i]["bodegaDestino"]+"')\">"+jsonGuias[i]["bodegaDestino"]+"</a></td>"+""
					+"<td style=\"text-align:center;\">";if(jsonGuias[i]["numeroCotizacion"]=="0")"""),format.raw/*179.84*/("""{"""),format.raw/*179.85*/("""tabla+="<form id=\"form_"+jsonGuias[i]["id"]
					+"\"method=\"post\"action=\"/movimientoOrigenDestinoModifica/\">"+"<input type=\"hidden\"name=\"id_guia\"value=\""+jsonGuias[i]["id"]+"\">"+"<a href=\"#\"onclick='document.getElementById(\"form_"
					+jsonGuias[i]["id"]+"\").submit();'>"+"<kbd style=\"background-color:#73C6B6\">edit</kbd>"+"</a>"+"</form>";"""),format.raw/*181.114*/("""}"""),format.raw/*181.115*/("""else"""),format.raw/*181.119*/("""{"""),format.raw/*181.120*/("""tabla+="--";"""),format.raw/*181.132*/("""}"""),format.raw/*181.133*/("""tabla+="</td>"
					+"<td style=\"text-align:center;\">";if(jsonGuias[i]["guiaPDF"]=="0")"""),format.raw/*182.75*/("""{"""),format.raw/*182.76*/("""tabla+="--";"""),format.raw/*182.88*/("""}"""),format.raw/*182.89*/("""else"""),format.raw/*182.93*/("""{"""),format.raw/*182.94*/("""tabla+="<a href=\"#\"onclick=\"mostrarPDF('"+jsonGuias[i]["guiaPDF"]+"')\">"
					+"<kbd style=\"background-color:#85C1E9\">pdf</kbd>"+"</a>";"""),format.raw/*183.66*/("""}"""),format.raw/*183.67*/("""tabla+="</td>"
					+"<td style=\"text-align:center;\">";if(jsonGuias[i]["docAnexo"]=="0")"""),format.raw/*184.76*/("""{"""),format.raw/*184.77*/("""tabla+="--";"""),format.raw/*184.89*/("""}"""),format.raw/*184.90*/("""else"""),format.raw/*184.94*/("""{"""),format.raw/*184.95*/("""tabla+="<a href=\"#\"onclick=\"descargaDocumento('"+jsonGuias[i]["docAnexo"]+"')\">"
					+"<kbd style=\"background-color:#7F8C8D\">doc</kbd>"+"</a>";"""),format.raw/*185.66*/("""}"""),format.raw/*185.67*/("""tabla+="</td>"+"<td style=\"text-align:center;\">";if(jsonGuias[i]["fotos"]=="0")"""),format.raw/*185.148*/("""{"""),format.raw/*185.149*/("""tabla+="--";"""),format.raw/*185.161*/("""}"""),format.raw/*185.162*/("""else"""),format.raw/*185.166*/("""{"""),format.raw/*185.167*/("""tabla+="<a href=\"/muestraAlbumFotos/"
					+jsonGuias[i]["fotos"]+"\">"+"<kbd style=\"background-color:#7F8C8D\">Ver</kbd>"+"</a>";"""),format.raw/*186.94*/("""}"""),format.raw/*186.95*/("""tabla+="</td>"
					+"<td style=\"text-align:center;\">";if(jsonGuias[i]["numeroCotizacion"]=="0")"""),format.raw/*187.84*/("""{"""),format.raw/*187.85*/("""tabla+="<a href=\"#\"onclick=\"eliminarMovimiento('"+jsonGuias[i]["numero"]+"','"+jsonGuias[i]["id"]
					+"');\">"+"<kbd style=\"background-color:red\">X</kbd>"+"</a>";"""),format.raw/*188.69*/("""}"""),format.raw/*188.70*/("""else"""),format.raw/*188.74*/("""{"""),format.raw/*188.75*/("""tabla+="--";"""),format.raw/*188.87*/("""}"""),format.raw/*188.88*/("""tabla+="</td>"+"</TR>";
				"""),format.raw/*189.5*/("""}"""),format.raw/*189.6*/("""tabla+="</tbody>"+"</table>";
				
				document.getElementById('tblPrincipal').innerHTML = tabla;
		
				$('#tablaPrincipal').DataTable("""),format.raw/*193.36*/("""{"""),format.raw/*193.37*/("""
				    	"""),format.raw/*194.10*/(""""fixedHeader": true,
				    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				    	"order": [[ 6, "desc" ]],
				    	"language": """),format.raw/*197.22*/("""{"""),format.raw/*197.23*/("""
				        	"""),format.raw/*198.14*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
				        """),format.raw/*199.13*/("""}"""),format.raw/*199.14*/("""
				  """),format.raw/*200.7*/("""}"""),format.raw/*200.8*/(""" """),format.raw/*200.9*/(""").on("init.dt", function()"""),format.raw/*200.35*/("""{"""),format.raw/*200.36*/("""
						"""),format.raw/*201.7*/("""document.getElementById('listo').innerHTML = 
							"<div class=\"row justify-content-md-center\">"+
								"<div class=\"col-xs-5 col-sm-2 col-md-2 col-lg-2\">"+
									"<input type=\"button\"  value=\"LISTO\" class=\"btn btn-success btn-sm rounded-pill btn-block\" onclick=\"location.href='/home/';\">"+
								"</div>"+
							"</div>";
						

						
						document.getElementById('enCarga').style.display="none";
				"""),format.raw/*211.5*/("""}"""),format.raw/*211.6*/(""");
		
			
			
	     	"""),format.raw/*215.8*/("""}"""),format.raw/*215.9*/("""
        """),format.raw/*216.9*/("""}"""),format.raw/*216.10*/(""");

		
		
	"""),format.raw/*220.2*/("""}"""),format.raw/*220.3*/("""
	
	
	
	"""),format.raw/*224.2*/("""const verCotizacion = (id_cotizacion) => """),format.raw/*224.43*/("""{"""),format.raw/*224.44*/("""
		"""),format.raw/*225.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*227.16*/("""{"""),format.raw/*227.17*/("""
            """),format.raw/*228.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*235.36*/("""{"""),format.raw/*235.37*/("""
	     		"""),format.raw/*236.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*238.8*/("""}"""),format.raw/*238.9*/("""
        """),format.raw/*239.9*/("""}"""),format.raw/*239.10*/(""");
	"""),format.raw/*240.2*/("""}"""),format.raw/*240.3*/("""
	
	"""),format.raw/*242.2*/("""const verOt = (id_ot) => """),format.raw/*242.27*/("""{"""),format.raw/*242.28*/("""
		"""),format.raw/*243.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*245.16*/("""{"""),format.raw/*245.17*/("""
            """),format.raw/*246.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*253.36*/("""{"""),format.raw/*253.37*/("""
	     		"""),format.raw/*254.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*256.8*/("""}"""),format.raw/*256.9*/("""
        """),format.raw/*257.9*/("""}"""),format.raw/*257.10*/(""");
	"""),format.raw/*258.2*/("""}"""),format.raw/*258.3*/("""
	
	"""),format.raw/*260.2*/("""const mostrarPDF = (nombrePDF) => """),format.raw/*260.36*/("""{"""),format.raw/*260.37*/("""
		  """),format.raw/*261.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*263.2*/("""}"""),format.raw/*263.3*/("""
	
	"""),format.raw/*265.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*265.43*/("""{"""),format.raw/*265.44*/("""
		  """),format.raw/*266.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*268.2*/("""}"""),format.raw/*268.3*/("""
	
	"""),format.raw/*270.2*/("""const eliminarMovimiento = (num_guia, id_guia) => """),format.raw/*270.52*/("""{"""),format.raw/*270.53*/("""
		"""),format.raw/*271.3*/("""alertify.confirm("Esta seguro de eliminar el movimiento: "+num_guia, function (e) """),format.raw/*271.85*/("""{"""),format.raw/*271.86*/("""
			"""),format.raw/*272.4*/("""if (e) """),format.raw/*272.11*/("""{"""),format.raw/*272.12*/("""
				"""),format.raw/*273.5*/("""$("#form_id_guia").val(id_guia);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*275.4*/("""}"""),format.raw/*275.5*/("""
		"""),format.raw/*276.3*/("""}"""),format.raw/*276.4*/(""");
	"""),format.raw/*277.2*/("""}"""),format.raw/*277.3*/("""

	
"""),format.raw/*280.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,desde:String,hasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,desde,hasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,desde,hasta) => apply(mapDiccionario,mapPermiso,userMnu,desde,hasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMovimientos/movimientoSelectModificar.scala.html
                  HASH: ce9db3d4a8051039267366c0118343032f6efbaa
                  MATRIX: 1042->1|1262->128|1295->136|1311->144|1350->146|1379->150|1447->198|1477->203|1532->238|1563->242|1640->293|1782->413|1815->419|2475->1052|2498->1066|2543->1090|5018->3535|5049->3538|5156->3616|5186->3617|5217->3620|5334->3709|5363->3710|5425->3743|5455->3744|5489->3750|5644->3877|5671->3882|5736->3919|5763->3924|5811->3943|5841->3944|5883->3957|6152->4197|6182->4198|6218->4206|6521->4481|6545->4495|6577->4505|6890->4790|6914->4804|6950->4818|7054->4894|7078->4908|7114->4922|7335->5114|7365->5115|7399->5121|7708->5401|7738->5402|7775->5411|7982->5589|8012->5590|8045->5594|8075->5595|8116->5607|8146->5608|8181->5615|8336->5741|8366->5742|8402->5750|8471->5790|8501->5791|8534->5795|8564->5796|8605->5808|8635->5809|8669->5815|9281->6398|9311->6399|9700->6758|9731->6759|9765->6763|9796->6764|9838->6776|9869->6777|10470->7349|10500->7350|10889->7709|10920->7710|10954->7714|10985->7715|11027->7727|11058->7728|11176->7817|11206->7818|11247->7830|11277->7831|11310->7835|11340->7836|11511->7978|11541->7979|11660->8069|11690->8070|11731->8082|11761->8083|11794->8087|11824->8088|12003->8238|12033->8239|12144->8320|12175->8321|12217->8333|12248->8334|12282->8338|12313->8339|12474->8471|12504->8472|12631->8570|12661->8571|12859->8740|12889->8741|12922->8745|12952->8746|12993->8758|13023->8759|13079->8787|13108->8788|13273->8924|13303->8925|13342->8935|13524->9088|13554->9089|13597->9103|13706->9183|13736->9184|13771->9191|13800->9192|13829->9193|13884->9219|13914->9220|13949->9227|14404->9654|14433->9655|14482->9676|14511->9677|14548->9686|14578->9687|14617->9698|14646->9699|14682->9707|14752->9748|14782->9749|14813->9752|14940->9850|14970->9851|15012->9864|15271->10094|15301->10095|15338->10104|15491->10229|15520->10230|15557->10239|15587->10240|15619->10244|15648->10245|15680->10249|15734->10274|15764->10275|15795->10278|15906->10360|15936->10361|15978->10374|16229->10596|16259->10597|16296->10606|16433->10715|16462->10716|16499->10725|16529->10726|16561->10730|16590->10731|16622->10735|16685->10769|16715->10770|16748->10775|16958->10957|16987->10958|17019->10962|17089->11003|17119->11004|17152->11009|17284->11113|17313->11114|17345->11118|17424->11168|17454->11169|17485->11172|17596->11254|17626->11255|17658->11259|17694->11266|17724->11267|17757->11272|17876->11363|17905->11364|17936->11367|17965->11368|17997->11372|18026->11373|18058->11377
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|39->8|39->8|41->10|42->11|42->11|44->13|60->29|60->29|60->29|128->97|131->100|133->102|133->102|134->103|137->106|137->106|139->108|139->108|141->110|144->113|144->113|145->114|145->114|146->115|146->115|147->116|154->123|154->123|156->125|160->129|160->129|160->129|168->137|168->137|168->137|170->139|170->139|170->139|178->147|178->147|179->148|185->154|185->154|186->155|188->157|188->157|188->157|188->157|188->157|188->157|189->158|194->163|194->163|195->164|195->164|195->164|195->164|195->164|195->164|195->164|196->165|204->173|204->173|206->175|206->175|206->175|206->175|206->175|206->175|210->179|210->179|212->181|212->181|212->181|212->181|212->181|212->181|213->182|213->182|213->182|213->182|213->182|213->182|214->183|214->183|215->184|215->184|215->184|215->184|215->184|215->184|216->185|216->185|216->185|216->185|216->185|216->185|216->185|216->185|217->186|217->186|218->187|218->187|219->188|219->188|219->188|219->188|219->188|219->188|220->189|220->189|224->193|224->193|225->194|228->197|228->197|229->198|230->199|230->199|231->200|231->200|231->200|231->200|231->200|232->201|242->211|242->211|246->215|246->215|247->216|247->216|251->220|251->220|255->224|255->224|255->224|256->225|258->227|258->227|259->228|266->235|266->235|267->236|269->238|269->238|270->239|270->239|271->240|271->240|273->242|273->242|273->242|274->243|276->245|276->245|277->246|284->253|284->253|285->254|287->256|287->256|288->257|288->257|289->258|289->258|291->260|291->260|291->260|292->261|294->263|294->263|296->265|296->265|296->265|297->266|299->268|299->268|301->270|301->270|301->270|302->271|302->271|302->271|303->272|303->272|303->272|304->273|306->275|306->275|307->276|307->276|308->277|308->277|311->280
                  -- GENERATED --
              */
          