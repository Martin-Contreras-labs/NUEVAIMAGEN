
package viewsMnuTablas.html

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

object precioMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Precio],List[tables.Moneda],List[tables.UnidadTiempo],tables.Sucursal,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listPrecio: List[tables.Precio], listMoneda: List[tables.Moneda], listUnidadTiempo: List[tables.UnidadTiempo],
sucursal: tables.Sucursal):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

	"""),_display_(/*8.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.51*/("""
	
	"""),_display_(/*10.3*/modalEquipoDescripcion()),format.raw/*10.27*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "MAESTRO DE PRECIOS - SUCURSAL: "+ sucursal.getNombre(), "(a partir de esta lista, se pasa el precio al cliente por equipo, solo la primera vez que se entrega)")),format.raw/*13.193*/("""
		"""),format.raw/*14.3*/("""<br>
		<div class="noprint" align="center">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input id="btnCargaExcel" type="button"  value="Actualizar desde un Excel" class="btn btn-info btn-sm rounded-pill btn-block" 
						onclick="$('#modalCargaMasiva').modal('show');">&nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</div>
						
		<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
			<thead style="background-color: #eeeeee">
				<TR> 
					<TH style="vertical-align: top;">GRUPO</TH>
					<TH>CODIGO</TH>
					<TH>EQUIPO</TH>
					<TH>MONEDA<BR>Compra</TH>
					<TH>PRECIO<br>Compra</TH>
					<TH>MONEDA<BR>Rep/"""),_display_(/*32.25*/mapDiccionario/*32.39*/.get("Arr")),format.raw/*32.50*/("""</TH>
					<TH>PRECIO<br>Reposici&oacute;n o Venta</TH>
					<TH>TASA<br>"""),_display_(/*34.19*/mapDiccionario/*34.33*/.get("Arriendo")),format.raw/*34.49*/("""</TH>
					<TH>PRECIO<br>"""),_display_(/*35.21*/mapDiccionario/*35.35*/.get("Arriendo")),format.raw/*35.51*/("""</TH>
					<TH>UNIDAD<BR>"""),_display_(/*36.21*/mapDiccionario/*36.35*/.get("Arriendo")),format.raw/*36.51*/("""</TH>
					<TH style="min-width:80px;">Ultimo<br>Cambio</TH>
					<TH>VALOR<br>M&iacute;nimo</TH>
					<TH>MINIMO<br>en D&iacute;as</TH>
				</TR>
			</thead>
			<tbody>
				"""),_display_(/*43.6*/for(lista2 <- listPrecio) yield /*43.31*/{_display_(Seq[Any](format.raw/*43.32*/("""
					"""),format.raw/*44.6*/("""<tr class="align-middle">
						<td>"""),_display_(/*45.12*/lista2/*45.18*/.getNombreGrupo()),format.raw/*45.35*/("""</td>
						<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*46.72*/lista2/*46.78*/.getCodigoEquipo()),format.raw/*46.96*/("""');">"""),_display_(/*46.102*/lista2/*46.108*/.getCodigoEquipo()),format.raw/*46.126*/("""</a></td>
						<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*47.72*/lista2/*47.78*/.getCodigoEquipo()),format.raw/*47.96*/("""');">"""),_display_(/*47.102*/lista2/*47.108*/.getNombreEquipo()),format.raw/*47.126*/("""</a></td>
						
						<td style="text-align:center;">"""),_display_(/*49.39*/lista2/*49.45*/.getMonedaCompra()),format.raw/*49.63*/("""</td>
						<td style="text-align:right;">"""),_display_(/*50.38*/lista2/*50.44*/.getPrecioCompra()),format.raw/*50.62*/("""</td>
						<td>
							<select class="custom-select" 
								id="NM_"""),_display_(/*53.17*/lista2/*53.23*/.getId_equipo()),format.raw/*53.38*/("""" 
								onchange="grabar(id,value,'"""),_display_(/*54.37*/lista2/*54.43*/.getId_equipo()),format.raw/*54.58*/("""')">
								<option value=""""),_display_(/*55.25*/lista2/*55.31*/.getId_moneda()),format.raw/*55.46*/("""">"""),_display_(/*55.49*/lista2/*55.55*/.getNombreMoneda()),format.raw/*55.73*/("""</option>
								"""),_display_(/*56.10*/for(lista <- listMoneda) yield /*56.34*/{_display_(Seq[Any](format.raw/*56.35*/("""
									"""),format.raw/*57.10*/("""<option value=""""),_display_(/*57.26*/lista/*57.31*/.id),format.raw/*57.34*/("""">"""),_display_(/*57.37*/lista/*57.42*/.nickName),format.raw/*57.51*/("""</option>
								""")))}),format.raw/*58.10*/("""
							"""),format.raw/*59.8*/("""</select>
						</td>
						<td>
							<div style="display:none">"""),_display_(/*62.35*/lista2/*62.41*/.getPrecioVenta()),format.raw/*62.58*/("""</div>
							<input type="text" class="form-control right"
								id="PV_"""),_display_(/*64.17*/lista2/*64.23*/.getId_equipo()),format.raw/*64.38*/(""""
								value=""""),_display_(/*65.17*/lista2/*65.23*/.getPrecioVenta()),format.raw/*65.40*/(""""
								onfocus="value=value.replace(/,/g,'')" 
								onkeydown="return ingresoDouble(window.event)"
								onchange="grabar(id,value,'"""),_display_(/*68.37*/lista2/*68.43*/.getId_equipo()),format.raw/*68.58*/("""')">
						</td>
						<td>
							<div style="display:none">"""),_display_(/*71.35*/lista2/*71.41*/.getTasaArriendo()),format.raw/*71.59*/("""</div>
							<input type="text" class="form-control center"
								id="TA_"""),_display_(/*73.17*/lista2/*73.23*/.getId_equipo()),format.raw/*73.38*/("""" 
								value=""""),_display_(/*74.17*/lista2/*74.23*/.getTasaArriendo()),format.raw/*74.41*/(""""
								disabled>
						</td>
						<td>
							<div style="display:none">"""),_display_(/*78.35*/lista2/*78.41*/.getPrecioArriendo()),format.raw/*78.61*/("""</div>
							<input type="text" class="form-control right"
								id="PA_"""),_display_(/*80.17*/lista2/*80.23*/.getId_equipo()),format.raw/*80.38*/(""""
								value=""""),_display_(/*81.17*/lista2/*81.23*/.getPrecioArriendo()),format.raw/*81.43*/(""""
								onfocus="value=value.replace(/,/g,'')" 
								onkeydown="return ingresoDouble(window.event)"
								onchange="grabar(id,value,'"""),_display_(/*84.37*/lista2/*84.43*/.getId_equipo()),format.raw/*84.58*/("""')">	
						</td>
						<td>
							<select class="custom-select" 
								id="UT_"""),_display_(/*88.17*/lista2/*88.23*/.getId_equipo()),format.raw/*88.38*/(""""
								onchange="grabar(id,value,'"""),_display_(/*89.37*/lista2/*89.43*/.getId_equipo()),format.raw/*89.58*/("""')">
								<option value=""""),_display_(/*90.25*/lista2/*90.31*/.getId_unidadTiempo()),format.raw/*90.52*/("""">"""),_display_(/*90.55*/lista2/*90.61*/.getNombreUnidadTiempo()),format.raw/*90.85*/("""</option>
								"""),_display_(/*91.10*/for(lista <- listUnidadTiempo) yield /*91.40*/{_display_(Seq[Any](format.raw/*91.41*/("""
									"""),format.raw/*92.10*/("""<option value=""""),_display_(/*92.26*/lista/*92.31*/.id),format.raw/*92.34*/("""">"""),_display_(/*92.37*/lista/*92.42*/.nombre),format.raw/*92.49*/("""</option>
								""")))}),format.raw/*93.10*/("""
							"""),format.raw/*94.8*/("""</select>
						</td>
						<td class="center">
							<div style="display:none">"""),_display_(/*97.35*/lista2/*97.41*/.getFecha().substring(6,10)),format.raw/*97.68*/("""-"""),_display_(/*97.70*/lista2/*97.76*/.getFecha().substring(3,5)),format.raw/*97.102*/("""-"""),_display_(/*97.104*/lista2/*97.110*/.getFecha().substring(0,2)),format.raw/*97.136*/("""</div>
							<div id="FE_"""),_display_(/*98.21*/lista2/*98.27*/.getId_equipo()),format.raw/*98.42*/("""">"""),_display_(/*98.45*/lista2/*98.51*/.getFecha()),format.raw/*98.62*/("""</div>
						</td>
						<td>
							<div style="display:none">"""),_display_(/*101.35*/lista2/*101.41*/.getPrecioMinimo()),format.raw/*101.59*/("""</div>
							<input type="text" class="form-control right"
								id="PM_"""),_display_(/*103.17*/lista2/*103.23*/.getId_equipo()),format.raw/*103.38*/(""""
								value=""""),_display_(/*104.17*/lista2/*104.23*/.getPrecioMinimo()),format.raw/*104.41*/(""""
								onfocus="value=value.replace(/,/g,'')" 
								onkeydown="return ingresoDouble(window.event)"
								onchange="grabar(id,value,'"""),_display_(/*107.37*/lista2/*107.43*/.getId_equipo()),format.raw/*107.58*/("""')">
						</td>
						<td>
							<div style="display:none">"""),_display_(/*110.35*/lista2/*110.41*/.getPermanenciaMinima()),format.raw/*110.64*/("""</div>
							<input type="text" class="form-control right"
								id="PE_"""),_display_(/*112.17*/lista2/*112.23*/.getId_equipo()),format.raw/*112.38*/(""""
								value=""""),_display_(/*113.17*/lista2/*113.23*/.getPermanenciaMinima()),format.raw/*113.46*/(""""
								onfocus="value=value.replace(/,/g,'')" 
								onkeydown="return ingresoInt(window.event)"
								onchange="grabar(id,value,'"""),_display_(/*116.37*/lista2/*116.43*/.getId_equipo()),format.raw/*116.58*/("""')">									
						</td>
					</tr>
				 """)))}),format.raw/*119.7*/("""
			"""),format.raw/*120.4*/("""</tbody>
		</table>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/'">
				</div>
			</div>
		</div>
	</div>
	
	<div id="modalCargaMasiva" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
	  <div class="modal-dialog modal-sm modal-dialog-scrollable" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Carga masiva de compras</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        
			<div class="row">
				<div class="col-sm-12" style="text-align:center">
					<form action="/routes2/precioPlantilla/" method="POST"  onsubmit="return validarForm2();">
						<input type="hidden" name="id_sucursal" value=""""),_display_(/*145.55*/sucursal/*145.63*/.getId()),format.raw/*145.71*/("""">
						<button type="submit" id="btnSubmit2" class="btn btn-sm  btn-success" style="font-size: 10px;">DESCARGAR PLANTILLA</button>
					</form>
					<br>
					<form id="precioCargaPlantilla" action="/routes2/precioCargaPlantilla/" method="POST" enctype="multipart/form-data">
						<input type="hidden" name="id_sucursal" value=""""),_display_(/*150.55*/sucursal/*150.63*/.getId()),format.raw/*150.71*/("""">
						<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
							<div>SUBIR ARCHIVO</div>
							<input type="file" id="archivoXLSX" name="archivoXLSX" value="" onchange="subirArchivo(this.form, this.form.archivoXLSX.value,this)">
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
""")))}),format.raw/*165.2*/("""




"""),format.raw/*170.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*171.31*/("""{"""),format.raw/*171.32*/("""
		  """),format.raw/*172.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*172.36*/("""{"""),format.raw/*172.37*/("""
		    	"""),format.raw/*173.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 2, "asc" ],[ 1, "asc" ]],
		    	"language": """),format.raw/*176.20*/("""{"""),format.raw/*176.21*/("""
		        	"""),format.raw/*177.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*178.11*/("""}"""),format.raw/*178.12*/("""
		  """),format.raw/*179.5*/("""}"""),format.raw/*179.6*/(""" """),format.raw/*179.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*181.2*/("""}"""),format.raw/*181.3*/(""");

	const grabar = (id, valor, id_equipo) => """),format.raw/*183.43*/("""{"""),format.raw/*183.44*/("""
		"""),format.raw/*184.3*/("""let auxId = id.split("_");
		let id_moneda = $("#NM_"+auxId[1]).val();
		var formData = new FormData();
		formData.append('id_sucursal',""""),_display_(/*187.35*/sucursal/*187.43*/.getId()),format.raw/*187.51*/("""");
		formData.append('id_equipo',id_equipo);
		formData.append('id_moneda',id_moneda);
		formData.append('campo',auxId[0]);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*192.10*/("""{"""),format.raw/*192.11*/("""
            """),format.raw/*193.13*/("""url: "/actualizaPrecioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*200.36*/("""{"""),format.raw/*200.37*/("""
	     		"""),format.raw/*201.9*/("""if(respuesta=="error")"""),format.raw/*201.31*/("""{"""),format.raw/*201.32*/("""
	     			"""),format.raw/*202.10*/("""alertify.alert(msgError, function () """),format.raw/*202.47*/("""{"""),format.raw/*202.48*/("""
		     			"""),format.raw/*203.11*/("""location.href = "/";
		     		"""),format.raw/*204.10*/("""}"""),format.raw/*204.11*/(""");
	     		"""),format.raw/*205.9*/("""}"""),format.raw/*205.10*/("""
	     		"""),format.raw/*206.9*/("""if(respuesta.status)"""),format.raw/*206.29*/("""{"""),format.raw/*206.30*/("""
	     			"""),format.raw/*207.10*/("""let fecha = respuesta.fecha;
	     			let numDec = respuesta.numDec;
	     			"""),_display_(if(mapDiccionario.get("nEmpresa").equals("MONTAX")||mapDiccionario.get("nEmpresa").equals("TECA"))/*209.109*/ {_display_(Seq[Any](format.raw/*209.111*/("""
	     				"""),format.raw/*210.11*/("""numDec = 4;
	     			""")))} else {null} ),format.raw/*211.11*/("""
	     			"""),format.raw/*212.10*/("""if(auxId[0]=="PV" || auxId[0]=="PA" || auxId[0]=="PM")"""),format.raw/*212.64*/("""{"""),format.raw/*212.65*/("""
	     				"""),format.raw/*213.11*/("""$("#"+id).val(formatStandar(valor,numDec));
	     			"""),format.raw/*214.10*/("""}"""),format.raw/*214.11*/("""
	     			"""),format.raw/*215.10*/("""if(auxId[0]=="PE")"""),format.raw/*215.28*/("""{"""),format.raw/*215.29*/("""
	     				"""),format.raw/*216.11*/("""$("#"+id).val(formatStandar(valor,0));
	     			"""),format.raw/*217.10*/("""}"""),format.raw/*217.11*/("""
	     			"""),format.raw/*218.10*/("""if(auxId[0]=="PA")"""),format.raw/*218.28*/("""{"""),format.raw/*218.29*/("""
	     				"""),format.raw/*219.11*/("""let numero = new DecimalFormat("#,##0.00");
	     				let pventa = numero.formatBack($("#PV_"+auxId[1]).val());
	     				let tasa = 0;
	     				if(pventa!=0)"""),format.raw/*222.24*/("""{"""),format.raw/*222.25*/("""
	     					"""),format.raw/*223.12*/("""tasa = valor/pventa*100;
	     				"""),format.raw/*224.11*/("""}"""),format.raw/*224.12*/(""" 
	     				"""),format.raw/*225.11*/("""$("#TA_"+auxId[1]).val(formatPorcentaje(tasa));
	     			"""),format.raw/*226.10*/("""}"""),format.raw/*226.11*/("""
	     			"""),format.raw/*227.10*/("""if(auxId[0]=="PV")"""),format.raw/*227.28*/("""{"""),format.raw/*227.29*/("""
	     				"""),format.raw/*228.11*/("""let numero = new DecimalFormat("#,##0.00");
	     				let parriendo = numero.formatBack($("#PA_"+auxId[1]).val());
	     				let tasa = 0;
	     				if(valor!=0)"""),format.raw/*231.23*/("""{"""),format.raw/*231.24*/("""
	     					"""),format.raw/*232.12*/("""tasa = parriendo/valor*100;
	     				"""),format.raw/*233.11*/("""}"""),format.raw/*233.12*/(""" 
	     				"""),format.raw/*234.11*/("""$("#TA_"+auxId[1]).val(formatPorcentaje(tasa));
	     			"""),format.raw/*235.10*/("""}"""),format.raw/*235.11*/("""
	     			"""),format.raw/*236.10*/("""if(auxId[0]=="NM")"""),format.raw/*236.28*/("""{"""),format.raw/*236.29*/("""
	     				"""),format.raw/*237.11*/("""let numero = new DecimalFormat("#,##0.00");
	     				let pventa = numero.formatBack($("#PV_"+auxId[1]).val());
	     				let parriendo = numero.formatBack($("#PA_"+auxId[1]).val());
	     				let pminimo = numero.formatBack($("#PM_"+auxId[1]).val());
	     				$("#PV_"+auxId[1]).val(formatStandar(pventa,numDec));
	     				$("#PA_"+auxId[1]).val(formatStandar(parriendo,numDec));
	     				$("#PM_"+auxId[1]).val(formatStandar(pminimo,numDec));
	     				
	     			"""),format.raw/*245.10*/("""}"""),format.raw/*245.11*/("""
	     			"""),format.raw/*246.10*/("""$("#FE_"+auxId[1]).val(fecha);
	     		"""),format.raw/*247.9*/("""}"""),format.raw/*247.10*/("""else"""),format.raw/*247.14*/("""{"""),format.raw/*247.15*/("""
	     			"""),format.raw/*248.10*/("""location.reload();
	     		"""),format.raw/*249.9*/("""}"""),format.raw/*249.10*/("""
	     	"""),format.raw/*250.8*/("""}"""),format.raw/*250.9*/("""
        """),format.raw/*251.9*/("""}"""),format.raw/*251.10*/(""");
	"""),format.raw/*252.2*/("""}"""),format.raw/*252.3*/("""
	
	"""),format.raw/*254.2*/("""const validarForm2 = () =>"""),format.raw/*254.28*/("""{"""),format.raw/*254.29*/("""
		"""),format.raw/*255.3*/("""$("#btnSubmit2").attr('disabled',true);
		return(true);
	"""),format.raw/*257.2*/("""}"""),format.raw/*257.3*/("""
	
	"""),format.raw/*259.2*/("""let extArray2 = new Array(".xls", ".xlsx");
	function subirArchivo(form, file, nodo) """),format.raw/*260.42*/("""{"""),format.raw/*260.43*/("""
		"""),format.raw/*261.3*/("""allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)
			file = file.slice(file.indexOf("\\") + 1);
		ext = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray2.length; i++) """),format.raw/*266.46*/("""{"""),format.raw/*266.47*/("""
			"""),format.raw/*267.4*/("""if (extArray2[i] == ext) """),format.raw/*267.29*/("""{"""),format.raw/*267.30*/(""" """),format.raw/*267.31*/("""allowSubmit = true; break; """),format.raw/*267.58*/("""}"""),format.raw/*267.59*/("""
		"""),format.raw/*268.3*/("""}"""),format.raw/*268.4*/("""
		"""),format.raw/*269.3*/("""if (allowSubmit) """),format.raw/*269.20*/("""{"""),format.raw/*269.21*/("""
			"""),format.raw/*270.4*/("""$("#precioCargaPlantilla").submit();
		"""),format.raw/*271.3*/("""}"""),format.raw/*271.4*/("""else"""),format.raw/*271.8*/("""{"""),format.raw/*271.9*/("""
			"""),format.raw/*272.4*/("""alertify.alert("Se permiten &uacute;nicamente archivos con la extenci&oacute;n: " 
			+ (extArray2.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.archivoXLSX.value="";
		"""),format.raw/*276.3*/("""}"""),format.raw/*276.4*/("""
	"""),format.raw/*277.2*/("""}"""),format.raw/*277.3*/("""
"""),format.raw/*278.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listPrecio:List[tables.Precio],listMoneda:List[tables.Moneda],listUnidadTiempo:List[tables.UnidadTiempo],sucursal:tables.Sucursal): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listPrecio,listMoneda,listUnidadTiempo,sucursal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Precio],List[tables.Moneda],List[tables.UnidadTiempo],tables.Sucursal) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listPrecio,listMoneda,listUnidadTiempo,sucursal) => apply(mapDiccionario,mapPermiso,userMnu,listPrecio,listMoneda,listUnidadTiempo,sucursal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/precioMantencion.scala.html
                  HASH: 4f116e1d8e17601d19f876ae76e3443a5c44d3d3
                  MATRIX: 1096->1|1424->236|1457->244|1473->252|1512->254|1541->258|1609->306|1640->311|1685->335|1716->339|1793->390|2004->579|2034->582|2789->1310|2812->1324|2844->1335|2945->1409|2968->1423|3005->1439|3058->1465|3081->1479|3118->1495|3171->1521|3194->1535|3231->1551|3432->1726|3473->1751|3512->1752|3545->1758|3609->1795|3624->1801|3662->1818|3766->1895|3781->1901|3820->1919|3854->1925|3870->1931|3910->1949|4018->2030|4033->2036|4072->2054|4106->2060|4122->2066|4162->2084|4244->2139|4259->2145|4298->2163|4368->2206|4383->2212|4422->2230|4520->2301|4535->2307|4571->2322|4637->2361|4652->2367|4688->2382|4744->2411|4759->2417|4795->2432|4825->2435|4840->2441|4879->2459|4925->2478|4965->2502|5004->2503|5042->2513|5085->2529|5099->2534|5123->2537|5153->2540|5167->2545|5197->2554|5247->2573|5282->2581|5376->2648|5391->2654|5429->2671|5532->2747|5547->2753|5583->2768|5628->2786|5643->2792|5681->2809|5849->2950|5864->2956|5900->2971|5989->3033|6004->3039|6043->3057|6147->3134|6162->3140|6198->3155|6244->3174|6259->3180|6298->3198|6402->3275|6417->3281|6458->3301|6561->3377|6576->3383|6612->3398|6657->3416|6672->3422|6713->3442|6881->3583|6896->3589|6932->3604|7042->3687|7057->3693|7093->3708|7158->3746|7173->3752|7209->3767|7265->3796|7280->3802|7322->3823|7352->3826|7367->3832|7412->3856|7458->3875|7504->3905|7543->3906|7581->3916|7624->3932|7638->3937|7662->3940|7692->3943|7706->3948|7734->3955|7784->3974|7819->3982|7928->4064|7943->4070|7991->4097|8020->4099|8035->4105|8083->4131|8113->4133|8129->4139|8177->4165|8231->4192|8246->4198|8282->4213|8312->4216|8327->4222|8359->4233|8451->4297|8467->4303|8507->4321|8611->4397|8627->4403|8664->4418|8710->4436|8726->4442|8766->4460|8935->4601|8951->4607|8988->4622|9078->4684|9094->4690|9139->4713|9243->4789|9259->4795|9296->4810|9342->4828|9358->4834|9403->4857|9569->4995|9585->5001|9622->5016|9696->5059|9728->5063|10818->6125|10836->6133|10866->6141|11226->6473|11244->6481|11274->6489|11789->6973|11822->6978|11913->7040|11943->7041|11976->7046|12036->7077|12066->7078|12102->7086|12303->7258|12333->7259|12374->7271|12481->7349|12511->7350|12544->7355|12573->7356|12602->7357|12703->7430|12732->7431|12807->7477|12837->7478|12868->7481|13034->7619|13052->7627|13082->7635|13279->7803|13309->7804|13351->7817|13612->8049|13642->8050|13679->8059|13730->8081|13760->8082|13799->8092|13865->8129|13895->8130|13935->8141|13994->8171|14024->8172|14063->8183|14093->8184|14130->8193|14179->8213|14209->8214|14248->8224|14454->8401|14496->8403|14536->8414|14603->8436|14642->8446|14725->8500|14755->8501|14795->8512|14877->8565|14907->8566|14946->8576|14993->8594|15023->8595|15063->8606|15140->8654|15170->8655|15209->8665|15256->8683|15286->8684|15326->8695|15514->8854|15544->8855|15585->8867|15649->8902|15679->8903|15720->8915|15806->8972|15836->8973|15875->8983|15922->9001|15952->9002|15992->9013|16182->9174|16212->9175|16253->9187|16320->9225|16350->9226|16391->9238|16477->9295|16507->9296|16546->9306|16593->9324|16623->9325|16663->9336|17160->9804|17190->9805|17229->9815|17296->9854|17326->9855|17359->9859|17389->9860|17428->9870|17483->9897|17513->9898|17549->9906|17578->9907|17615->9916|17645->9917|17677->9921|17706->9922|17738->9926|17793->9952|17823->9953|17854->9956|17939->10013|17968->10014|18000->10018|18114->10103|18144->10104|18175->10107|18429->10332|18459->10333|18491->10337|18545->10362|18575->10363|18605->10364|18661->10391|18691->10392|18722->10395|18751->10396|18782->10399|18828->10416|18858->10417|18890->10421|18957->10460|18986->10461|19018->10465|19047->10466|19079->10470|19321->10684|19350->10685|19380->10687|19409->10688|19438->10689
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|41->10|41->10|43->12|44->13|44->13|45->14|63->32|63->32|63->32|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|74->43|74->43|74->43|75->44|76->45|76->45|76->45|77->46|77->46|77->46|77->46|77->46|77->46|78->47|78->47|78->47|78->47|78->47|78->47|80->49|80->49|80->49|81->50|81->50|81->50|84->53|84->53|84->53|85->54|85->54|85->54|86->55|86->55|86->55|86->55|86->55|86->55|87->56|87->56|87->56|88->57|88->57|88->57|88->57|88->57|88->57|88->57|89->58|90->59|93->62|93->62|93->62|95->64|95->64|95->64|96->65|96->65|96->65|99->68|99->68|99->68|102->71|102->71|102->71|104->73|104->73|104->73|105->74|105->74|105->74|109->78|109->78|109->78|111->80|111->80|111->80|112->81|112->81|112->81|115->84|115->84|115->84|119->88|119->88|119->88|120->89|120->89|120->89|121->90|121->90|121->90|121->90|121->90|121->90|122->91|122->91|122->91|123->92|123->92|123->92|123->92|123->92|123->92|123->92|124->93|125->94|128->97|128->97|128->97|128->97|128->97|128->97|128->97|128->97|128->97|129->98|129->98|129->98|129->98|129->98|129->98|132->101|132->101|132->101|134->103|134->103|134->103|135->104|135->104|135->104|138->107|138->107|138->107|141->110|141->110|141->110|143->112|143->112|143->112|144->113|144->113|144->113|147->116|147->116|147->116|150->119|151->120|176->145|176->145|176->145|181->150|181->150|181->150|196->165|201->170|202->171|202->171|203->172|203->172|203->172|204->173|207->176|207->176|208->177|209->178|209->178|210->179|210->179|210->179|212->181|212->181|214->183|214->183|215->184|218->187|218->187|218->187|223->192|223->192|224->193|231->200|231->200|232->201|232->201|232->201|233->202|233->202|233->202|234->203|235->204|235->204|236->205|236->205|237->206|237->206|237->206|238->207|240->209|240->209|241->210|242->211|243->212|243->212|243->212|244->213|245->214|245->214|246->215|246->215|246->215|247->216|248->217|248->217|249->218|249->218|249->218|250->219|253->222|253->222|254->223|255->224|255->224|256->225|257->226|257->226|258->227|258->227|258->227|259->228|262->231|262->231|263->232|264->233|264->233|265->234|266->235|266->235|267->236|267->236|267->236|268->237|276->245|276->245|277->246|278->247|278->247|278->247|278->247|279->248|280->249|280->249|281->250|281->250|282->251|282->251|283->252|283->252|285->254|285->254|285->254|286->255|288->257|288->257|290->259|291->260|291->260|292->261|297->266|297->266|298->267|298->267|298->267|298->267|298->267|298->267|299->268|299->268|300->269|300->269|300->269|301->270|302->271|302->271|302->271|302->271|303->272|307->276|307->276|308->277|308->277|309->278
                  -- GENERATED --
              */
          