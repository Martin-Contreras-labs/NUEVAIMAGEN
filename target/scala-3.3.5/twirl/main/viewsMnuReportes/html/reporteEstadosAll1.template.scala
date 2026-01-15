
package viewsMnuReportes.html

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

object reporteEstadosAll1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
datos: List[List[String]], bodegaEmpresa: tables.BodegaEmpresa):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "TODO EL DETALLE POR ESTADO Y REPARACIONES",bodegaEmpresa.getNombre())),format.raw/*12.102*/("""
		
		"""),format.raw/*14.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td>
						<div align="center">
							<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
								Exportar a Excel
							</button>
							<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
								Imprimir
							</button>
							<button type="button"  class="btn btn-sm btn-success" 
								onclick="history.go(-1);return false;">
								Volver
							</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		
		<div>
			ALTERNAR COLUMNAS: 
				<a class="toggle-vis" href="#" data-column="0"><kbd id="kdb0" style="background-color: #73C6B6">"""),_display_(/*38.102*/mapDiccionario/*38.116*/.get("Bodega")),format.raw/*38.130*/("""</kbd></a>
				<a class="toggle-vis" href="#" data-column="1"><kbd id="kdb1" style="background-color: #73C6B6">Cliente</kbd></a>
				<a class="toggle-vis" href="#" data-column="2"><kbd id="kdb2" style="background-color: #73C6B6">Nro Mov</kbd></a>
				<a class="toggle-vis" href="#" data-column="3"><kbd id="kdb3" style="background-color: #73C6B6">Ref Clie</kbd></a>
				<a class="toggle-vis" href="#" data-column="4"><kbd id="kdb4" style="background-color: #73C6B6">Fecha Guia</kbd></a>
				<a class="toggle-vis" href="#" data-column="5"><kbd id="kdb5" style="background-color: #73C6B6">Codigo</kbd></a>
				<a class="toggle-vis" href="#" data-column="6"><kbd id="kdb6" style="background-color: #73C6B6">Equipo</kbd></a>
				<a class="toggle-vis" href="#" data-column="7"><kbd id="kdb7" style="background-color: #73C6B6">Cant Equipo</kbd></a>
				<a class="toggle-vis" href="#" data-column="8"><kbd id="kdb8" style="background-color: #73C6B6">Mon Equip</kbd></a>
				<a class="toggle-vis" href="#" data-column="9"><kbd id="kdb9" style="background-color: #73C6B6">P.U. Equipo</kbd></a>
				<a class="toggle-vis" href="#" data-column="10"><kbd id="kdb10" style="background-color: #73C6B6">KG</kbd></a>
				<a class="toggle-vis" href="#" data-column="11"><kbd id="kdb11" style="background-color: #73C6B6">M2</kbd></a>
				<a class="toggle-vis" href="#" data-column="12"><kbd id="kdb12" style="background-color: #73C6B6">Estado</kbd></a>
				<a class="toggle-vis" href="#" data-column="13"><kbd id="kdb13" style="background-color: #73C6B6">Cant Estado</kbd></a>
				<a class="toggle-vis" href="#" data-column="14"><kbd id="kdb14" style="background-color: #73C6B6">Sigla</kbd></a>
				<a class="toggle-vis" href="#" data-column="15"><kbd id="kdb15" style="background-color: #73C6B6">Reparacion</kbd></a>
				<a class="toggle-vis" href="#" data-column="16"><kbd id="kdb16" style="background-color: #73C6B6">Cant Repar</kbd></a>
				<a class="toggle-vis" href="#" data-column="17"><kbd id="kdb17" style="background-color: #73C6B6">Mon Repar</kbd></a>
				<a class="toggle-vis" href="#" data-column="18"><kbd id="kdb18" style="background-color: #73C6B6">P.U. Reparacion</kbd></a>
		</div>
    				
    				
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
					<TR> 
					        <TH style= "text-align: center; vertical-align: top;">"""),_display_(/*64.69*/mapDiccionario/*64.83*/.get("Bodega")),format.raw/*64.97*/("""</TH>
							<TH style= "text-align: center; vertical-align: top;">Cliente</TH>
					        <TH style= "text-align: center; vertical-align: top;">Nro Mov</TH>
							<TH style= "text-align: center; vertical-align: top;">Ref Clie</TH>
					        <TH style= "text-align: center; vertical-align: top;">Fecha Guia</TH>
							"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1"))/*69.147*/{_display_(Seq[Any](format.raw/*69.148*/("""
								"""),format.raw/*70.9*/("""<TH style= "text-align: center; vertical-align: top;">COBRA """),_display_(/*70.70*/mapDiccionario("ARR")),format.raw/*70.91*/("""</TH>
							""")))}else/*71.13*/{_display_(Seq[Any](format.raw/*71.14*/("""
								"""),format.raw/*72.9*/("""<TH style= "display:none"></TH>
							""")))}),format.raw/*73.9*/("""
							"""),format.raw/*74.8*/("""<TH style= "text-align: center; vertical-align: top;">Codigo</TH>
							<TH style= "text-align: center; vertical-align: top;">Equipo</TH>
							<TH style= "text-align: center; vertical-align: top;">Cant Equipo</TH>
							<TH style= "text-align: center; vertical-align: top;">Mon Equip</TH>
							<TH style= "text-align: center; vertical-align: top;">P.U.<br>Equipo</TH>
							<TH style= "text-align: center; vertical-align: top;">KG</TH>
							<TH style= "text-align: center; vertical-align: top;">M2</TH>
							<TH style= "text-align: center; vertical-align: top;">Estado</TH>
							<TH style= "text-align: center; vertical-align: top;">Cant Estado</TH>
							<TH style= "text-align: center; vertical-align: top;">Sigla</TH>
							<TH style= "text-align: center; vertical-align: top;">Reparacion</TH>
							<TH style= "text-align: center; vertical-align: top;">Cant Repar</TH>
							<TH style= "text-align: center; vertical-align: top;">Mon Repar</TH>
							<TH style= "text-align: center; vertical-align: top;">P.U.<br>Reparacion</TH>
							<TH style= "text-align: center; vertical-align: top;">Total<br>Reparacion</TH>
					</TR>
				</thead>
				<tbody>
				"""),_display_(/*92.6*/for(lista1 <- datos) yield /*92.26*/{_display_(Seq[Any](format.raw/*92.27*/("""
					"""),format.raw/*93.6*/("""<TR>
						<td style="text-align: left; vertical-align:middle;">"""),_display_(/*94.61*/lista1/*94.67*/.get(0)),format.raw/*94.74*/("""</td>
						<td style="text-align: left; vertical-align:middle;">"""),_display_(/*95.61*/lista1/*95.67*/.get(18)),format.raw/*95.75*/("""</td>
						<td style="text-align: center; vertical-align:middle;">"""),_display_(/*96.63*/lista1/*96.69*/.get(1)),format.raw/*96.76*/("""</td>
						<td style="text-align: center; vertical-align:middle;">"""),_display_(/*97.63*/lista1/*97.69*/.get(17)),format.raw/*97.77*/("""</td>
						<td style="text-align: center; vertical-align:middle;">
							<span style="display:none">"""),_display_(/*99.36*/lista1/*99.42*/.get(2)),format.raw/*99.49*/("""</span>
							"""),_display_(/*100.9*/utilities/*100.18*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*100.47*/("""
						"""),format.raw/*101.7*/("""</td>
						"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1"))/*102.146*/{_display_(Seq[Any](format.raw/*102.147*/("""
							"""),format.raw/*103.8*/("""<td  style="text-align:center;">
								"""),_display_(if(lista1.get(21).equals("1"))/*104.40*/{_display_(Seq[Any](format.raw/*104.41*/("""
									"""),format.raw/*105.10*/("""<div style="display: none">"""),_display_(/*105.38*/lista1/*105.44*/.get(19)),format.raw/*105.52*/("""</div>
									<input type="hidden" id="select_"""),_display_(/*106.43*/lista1/*106.49*/.get(20)),format.raw/*106.57*/("""" value=""""),_display_(/*106.67*/lista1/*106.73*/.get(19)),format.raw/*106.81*/("""">
									"""),_display_(if(lista1.get(19).equals("1"))/*107.41*/{_display_(Seq[Any](format.raw/*107.42*/("""
										"""),format.raw/*108.11*/("""<input type="checkbox" id="selectCheck_"""),_display_(/*108.51*/lista1/*108.57*/.get(20)),format.raw/*108.65*/("""" checked onchange="modificaSelect('"""),_display_(/*108.102*/lista1/*108.108*/.get(20)),format.raw/*108.116*/("""');">
									""")))}else/*109.15*/{_display_(Seq[Any](format.raw/*109.16*/("""
										"""),format.raw/*110.11*/("""<input type="checkbox" id="selectCheck_"""),_display_(/*110.51*/lista1/*110.57*/.get(20)),format.raw/*110.65*/("""" onchange="modificaSelect('"""),_display_(/*110.94*/lista1/*110.100*/.get(20)),format.raw/*110.108*/("""');">
										""")))}),format.raw/*111.12*/("""
								""")))} else {null} ),format.raw/*112.10*/("""
							"""),format.raw/*113.8*/("""</td>
						""")))}else/*114.12*/{_display_(Seq[Any](format.raw/*114.13*/("""
							"""),format.raw/*115.8*/("""<td style= "display:none"></td>
						""")))}),format.raw/*116.8*/("""
						"""),format.raw/*117.7*/("""<td style="text-align: left; vertical-align:middle;">"""),_display_(/*117.61*/lista1/*117.67*/.get(3)),format.raw/*117.74*/("""</td>
						<td style="text-align: left; vertical-align:middle;">"""),_display_(/*118.61*/lista1/*118.67*/.get(4)),format.raw/*118.74*/("""</td>
						<td style="text-align: right; vertical-align:middle;">"""),_display_(/*119.62*/lista1/*119.68*/.get(5)),format.raw/*119.75*/("""</td>
						<td style="text-align: center; vertical-align:middle;">"""),_display_(/*120.63*/lista1/*120.69*/.get(6)),format.raw/*120.76*/("""</td>
						<td style="text-align: right; vertical-align:middle;">"""),_display_(/*121.62*/lista1/*121.68*/.get(7)),format.raw/*121.75*/("""</td>
						<td style="text-align: right; vertical-align:middle;">"""),_display_(/*122.62*/lista1/*122.68*/.get(15)),format.raw/*122.76*/("""</td>
						<td style="text-align: right; vertical-align:middle;">"""),_display_(/*123.62*/lista1/*123.68*/.get(16)),format.raw/*123.76*/("""</td>
						<td style="text-align: center; vertical-align:middle;">"""),_display_(/*124.63*/lista1/*124.69*/.get(8)),format.raw/*124.76*/("""</td>
						<td style="text-align: right; vertical-align:middle;">"""),_display_(/*125.62*/lista1/*125.68*/.get(9)),format.raw/*125.75*/("""</td>
						<td style="text-align: center; vertical-align:middle;">"""),_display_(/*126.63*/lista1/*126.69*/.get(10)),format.raw/*126.77*/("""</td>
						<td style="text-align: left; vertical-align:middle;">"""),_display_(/*127.61*/lista1/*127.67*/.get(11)),format.raw/*127.75*/("""</td>
						<td class="cant" style="text-align: right; vertical-align:middle;">"""),_display_(/*128.75*/lista1/*128.81*/.get(12)),format.raw/*128.89*/("""</td>
						<td style="text-align: center; vertical-align:middle;">"""),_display_(/*129.63*/lista1/*129.69*/.get(13)),format.raw/*129.77*/("""</td>
						<td class="pu" style="text-align: right; vertical-align:middle;">"""),_display_(/*130.73*/lista1/*130.79*/.get(14)),format.raw/*130.87*/("""</td>
						<td class="total" style="text-align: right; vertical-align:middle;">suma</td>
					</TR>
	 			""")))}),format.raw/*133.7*/("""
				"""),format.raw/*134.5*/("""</tbody>
				<tfoot style="background-color: #eeeeee">
					<TR>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1"))/*142.146*/{_display_(Seq[Any](format.raw/*142.147*/("""
							"""),format.raw/*143.8*/("""<TH></TH>
						""")))}else/*144.12*/{_display_(Seq[Any](format.raw/*144.13*/("""
							"""),format.raw/*145.8*/("""<TH style= "display:none"></TH>
						""")))}),format.raw/*146.8*/("""
						"""),format.raw/*147.7*/("""<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH style= "text-align: left; vertical-align: top;">TOTAL</TH>
						<TH style= "text-align: right; vertical-align: top;" id="granTotal">total</TH>
					</TR>
				</tfoot>
			</table>
		</div>
	</div>
	
	<form id="excel" class="formulario" method="post" action="/reporteEstadosAll1Excel/">
		<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*169.56*/bodegaEmpresa/*169.69*/.getId()),format.raw/*169.77*/("""">
	</form>

""")))}),format.raw/*172.2*/("""


"""),format.raw/*175.1*/("""<script>

	$(document).ready(function() """),format.raw/*177.31*/("""{"""),format.raw/*177.32*/("""
		
		"""),format.raw/*179.3*/("""$('#tablaPrincipal thead tr')
        .clone(true)
        .addClass('filters')
        .appendTo('#tablaPrincipal thead');

		 $('#tablaPrincipal').DataTable("""),format.raw/*184.35*/("""{"""),format.raw/*184.36*/("""
				"""),format.raw/*185.5*/(""""orderCellsTop": true,
		    	"fixedHeader": true,
			 	"lengthMenu": [[-1, 20, 50, 100, 200], ["All",20, 50, 100, 200]],
		    	"paging": true,
		    	"order": [[ 0, "asc" ],[ 4, "asc" ],[ 2, "asc" ]],
				"info": true,
				"searching": true,
		    	"language": """),format.raw/*192.20*/("""{"""),format.raw/*192.21*/("""
		        	"""),format.raw/*193.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*194.11*/("""}"""),format.raw/*194.12*/(""",
		        initComplete: function () """),format.raw/*195.37*/("""{"""),format.raw/*195.38*/("""
	            """),format.raw/*196.14*/("""var api = this.api();

	            // For each column
	            api
	                .columns()
	                .eq(0)
	                .each(function (colIdx) """),format.raw/*202.42*/("""{"""),format.raw/*202.43*/("""
	                    """),format.raw/*203.22*/("""// Set the header cell to contain the input element
	                    var cell = $('.filters th').eq(
	                        $(api.column(colIdx).header()).index()
	                    );
	                    var title = $(cell).text();
	                    $(cell).html('<input type="text" class="form-control form-control-sm" placeholder="' + title + '" />');
	 
	                    // On every keypress in this input
	                    $(
	                        'input',
	                        $('.filters th').eq($(api.column(colIdx).header()).index())
	                    )
	                        .off('keyup change')
	                        .on('change', function (e) """),format.raw/*216.53*/("""{"""),format.raw/*216.54*/("""
	                            """),format.raw/*217.30*/("""// Get the search value
	                            $(this).attr('title', $(this).val());
	                            var regexr = '("""),format.raw/*219.45*/("""{"""),format.raw/*219.46*/("""search"""),format.raw/*219.52*/("""}"""),format.raw/*219.53*/(""")'; //$(this).parents('th').find('select').val();
	 
	                            var cursorPosition = this.selectionStart;
	                            // Search the column for that value
	                            api
	                                .column(colIdx)
	                                .search(
	                                    this.value != ''
	                                        ? regexr.replace('"""),format.raw/*227.60*/("""{"""),format.raw/*227.61*/("""search"""),format.raw/*227.67*/("""}"""),format.raw/*227.68*/("""', '(((' + this.value + ')))')
	                                        : '',
	                                    this.value != '',
	                                    this.value == ''
	                                )
	                                .draw();
	                        """),format.raw/*233.26*/("""}"""),format.raw/*233.27*/(""")
	                        .on('keyup', function (e) """),format.raw/*234.52*/("""{"""),format.raw/*234.53*/("""
	                            """),format.raw/*235.30*/("""e.stopPropagation();
	 
	                            $(this).trigger('change');
	                            $(this)
	                                .focus()[0]
	                                .setSelectionRange(cursorPosition, cursorPosition);
	                        """),format.raw/*241.26*/("""}"""),format.raw/*241.27*/(""");
	                """),format.raw/*242.18*/("""}"""),format.raw/*242.19*/(""");
	        	"""),format.raw/*243.11*/("""}"""),format.raw/*243.12*/("""
		 """),format.raw/*244.4*/("""}"""),format.raw/*244.5*/(""" """),format.raw/*244.6*/(""");
		  
	   $('a.toggle-vis').on('click', function (e) """),format.raw/*246.48*/("""{"""),format.raw/*246.49*/("""
			"""),format.raw/*247.4*/("""e.preventDefault();
			// Get the column API object
			let col = $(this).attr('data-column');
			var column = $('#tablaPrincipal').DataTable().column(col);
			// Toggle the visibility
			column.visible(!column.visible());
			if(column.visible())"""),format.raw/*253.24*/("""{"""),format.raw/*253.25*/("""
				"""),format.raw/*254.5*/("""document.getElementById('kdb'+col).style.backgroundColor = "#73C6B6";
			"""),format.raw/*255.4*/("""}"""),format.raw/*255.5*/("""else"""),format.raw/*255.9*/("""{"""),format.raw/*255.10*/("""
				"""),format.raw/*256.5*/("""document.getElementById('kdb'+col).style.backgroundColor = "#D6DBDF";
			"""),format.raw/*257.4*/("""}"""),format.raw/*257.5*/("""
		"""),format.raw/*258.3*/("""}"""),format.raw/*258.4*/(""");
		let granTotal = 0;
		document.querySelectorAll('#tablaPrincipal tr').forEach(fila => """),format.raw/*260.67*/("""{"""),format.raw/*260.68*/("""
			"""),format.raw/*261.4*/("""const cantElement = fila.querySelector('.cant');
			const puElement = fila.querySelector('.pu');
			const totalElement = fila.querySelector('.total');
			if (cantElement && puElement && totalElement) """),format.raw/*264.50*/("""{"""),format.raw/*264.51*/("""
				"""),format.raw/*265.5*/("""// Limpiar el texto de cualquier formato previo
				const cant = parseFloat(cantElement.textContent.replace(/[^\d.-]/g, '')) || 0;
				const pu = parseFloat(puElement.textContent.replace(/[^\d.-]/g, '')) || 0;
				// Calcular y formatear el resultado
				const resultado = cant * pu;
				granTotal += resultado;
				totalElement.textContent = formatStandar(resultado,0);
			"""),format.raw/*272.4*/("""}"""),format.raw/*272.5*/("""
		"""),format.raw/*273.3*/("""}"""),format.raw/*273.4*/(""");
		$("#granTotal").text(formatStandar(granTotal,0));
			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*276.2*/("""}"""),format.raw/*276.3*/(""");

	const modificaSelect = (id_movimiento) => """),format.raw/*278.44*/("""{"""),format.raw/*278.45*/("""
		"""),format.raw/*279.3*/("""let select = $("#select_"+id_movimiento).val();
		if(select == 1)"""),format.raw/*280.18*/("""{"""),format.raw/*280.19*/("""
			"""),format.raw/*281.4*/("""modificaSelect2(id_movimiento, 0);
			const elementIdDet = "select_" + id_movimiento;
			$(`[id='$"""),format.raw/*283.13*/("""{"""),format.raw/*283.14*/("""elementIdDet"""),format.raw/*283.26*/("""}"""),format.raw/*283.27*/("""']`).each(function() """),format.raw/*283.48*/("""{"""),format.raw/*283.49*/("""
				"""),format.raw/*284.5*/("""$(this).val(0);
			"""),format.raw/*285.4*/("""}"""),format.raw/*285.5*/(""");
			const selectCheck = "selectCheck_" + id_movimiento;
			$(`[id='$"""),format.raw/*287.13*/("""{"""),format.raw/*287.14*/("""selectCheck"""),format.raw/*287.25*/("""}"""),format.raw/*287.26*/("""']`).each(function() """),format.raw/*287.47*/("""{"""),format.raw/*287.48*/("""
				"""),format.raw/*288.5*/("""$(this).prop('checked', false);
			"""),format.raw/*289.4*/("""}"""),format.raw/*289.5*/(""");
		"""),format.raw/*290.3*/("""}"""),format.raw/*290.4*/("""else"""),format.raw/*290.8*/("""{"""),format.raw/*290.9*/("""
			"""),format.raw/*291.4*/("""modificaSelect2(id_movimiento, 1);
			const elementIdDet = "select_" + id_movimiento;
			$(`[id='$"""),format.raw/*293.13*/("""{"""),format.raw/*293.14*/("""elementIdDet"""),format.raw/*293.26*/("""}"""),format.raw/*293.27*/("""']`).each(function() """),format.raw/*293.48*/("""{"""),format.raw/*293.49*/("""
				"""),format.raw/*294.5*/("""$(this).val(1);
			"""),format.raw/*295.4*/("""}"""),format.raw/*295.5*/(""");
			const selectCheck = "selectCheck_" + id_movimiento;
			$(`[id='$"""),format.raw/*297.13*/("""{"""),format.raw/*297.14*/("""selectCheck"""),format.raw/*297.25*/("""}"""),format.raw/*297.26*/("""']`).each(function() """),format.raw/*297.47*/("""{"""),format.raw/*297.48*/("""
				"""),format.raw/*298.5*/("""$(this).prop('checked', true);
			"""),format.raw/*299.4*/("""}"""),format.raw/*299.5*/(""");
		"""),format.raw/*300.3*/("""}"""),format.raw/*300.4*/("""
	"""),format.raw/*301.2*/("""}"""),format.raw/*301.3*/("""

	"""),format.raw/*303.2*/("""const modificaSelect2 = (id_movimiento, valor) => """),format.raw/*303.52*/("""{"""),format.raw/*303.53*/("""
		"""),format.raw/*304.3*/("""let formData = new FormData();
		formData.append('id_movimiento',id_movimiento);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*307.10*/("""{"""),format.raw/*307.11*/("""
			"""),format.raw/*308.4*/("""url: "/routes3/modCobraArriendoAjax/",
			type: "POST",
			method: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(respuesta)"""),format.raw/*315.32*/("""{"""),format.raw/*315.33*/("""
				"""),format.raw/*316.5*/("""if(respuesta=="error")"""),format.raw/*316.27*/("""{"""),format.raw/*316.28*/("""
					"""),format.raw/*317.6*/("""alertify.alert(msgError, function () """),format.raw/*317.43*/("""{"""),format.raw/*317.44*/("""
						"""),format.raw/*318.7*/("""location.href = "/";
					"""),format.raw/*319.6*/("""}"""),format.raw/*319.7*/(""");
				"""),format.raw/*320.5*/("""}"""),format.raw/*320.6*/("""
			"""),format.raw/*321.4*/("""}"""),format.raw/*321.5*/("""
		"""),format.raw/*322.3*/("""}"""),format.raw/*322.4*/(""");
	"""),format.raw/*323.2*/("""}"""),format.raw/*323.3*/("""
	
"""),format.raw/*325.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,datos:List[List[String]],bodegaEmpresa:tables.BodegaEmpresa): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,datos,bodegaEmpresa)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.BodegaEmpresa) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,datos,bodegaEmpresa) => apply(mapDiccionario,mapPermiso,userMnu,datos,bodegaEmpresa)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteEstadosAll1.scala.html
                  HASH: fa4195c443442c9fe7159477c5b69b374c6378ba
                  MATRIX: 1058->1|1312->162|1345->170|1361->178|1400->180|1429->184|1497->232|1527->237|1571->261|1602->265|1679->316|1799->414|1832->420|2631->1191|2655->1205|2691->1219|5190->3691|5213->3705|5248->3719|5740->4183|5780->4184|5816->4193|5904->4254|5946->4275|5983->4293|6022->4294|6058->4303|6128->4343|6163->4351|7366->5528|7402->5548|7441->5549|7474->5555|7566->5620|7581->5626|7609->5633|7702->5699|7717->5705|7746->5713|7841->5781|7856->5787|7884->5794|7979->5862|7994->5868|8023->5876|8153->5979|8168->5985|8196->5992|8239->6008|8258->6017|8309->6046|8344->6053|8524->6204|8565->6205|8601->6213|8701->6285|8741->6286|8780->6296|8836->6324|8852->6330|8882->6338|8959->6387|8975->6393|9005->6401|9043->6411|9059->6417|9089->6425|9160->6468|9200->6469|9240->6480|9308->6520|9324->6526|9354->6534|9420->6571|9437->6577|9468->6585|9508->6605|9548->6606|9588->6617|9656->6657|9672->6663|9702->6671|9759->6700|9776->6706|9807->6714|9856->6731|9911->6741|9947->6749|9984->6766|10024->6767|10060->6775|10130->6814|10165->6821|10247->6875|10263->6881|10292->6888|10386->6954|10402->6960|10431->6967|10526->7034|10542->7040|10571->7047|10667->7115|10683->7121|10712->7128|10807->7195|10823->7201|10852->7208|10947->7275|10963->7281|10993->7289|11088->7356|11104->7362|11134->7370|11230->7438|11246->7444|11275->7451|11370->7518|11386->7524|11415->7531|11511->7599|11527->7605|11557->7613|11651->7679|11667->7685|11697->7693|11805->7773|11821->7779|11851->7787|11947->7855|11963->7861|11993->7869|12099->7947|12115->7953|12145->7961|12283->8068|12316->8073|12635->8363|12676->8364|12712->8372|12753->8393|12793->8394|12829->8402|12899->8441|12934->8448|13515->9001|13538->9014|13568->9022|13613->9036|13644->9039|13713->9079|13743->9080|13777->9086|13965->9245|13995->9246|14028->9251|14320->9514|14350->9515|14391->9527|14498->9605|14528->9606|14595->9644|14625->9645|14668->9659|14862->9824|14892->9825|14943->9847|15662->10537|15692->10538|15751->10568|15915->10703|15945->10704|15980->10710|16010->10711|16465->11137|16495->11138|16530->11144|16560->11145|16878->11434|16908->11435|16990->11488|17020->11489|17079->11519|17380->11791|17410->11792|17459->11812|17489->11813|17531->11826|17561->11827|17593->11831|17622->11832|17651->11833|17735->11888|17765->11889|17797->11893|18071->12138|18101->12139|18134->12144|18235->12217|18264->12218|18296->12222|18326->12223|18359->12228|18460->12301|18489->12302|18520->12305|18549->12306|18668->12396|18698->12397|18730->12401|18959->12601|18989->12602|19022->12607|19424->12981|19453->12982|19484->12985|19513->12986|19665->13110|19694->13111|19770->13158|19800->13159|19831->13162|19925->13227|19955->13228|19987->13232|20114->13330|20144->13331|20185->13343|20215->13344|20265->13365|20295->13366|20328->13371|20375->13390|20404->13391|20503->13461|20533->13462|20573->13473|20603->13474|20653->13495|20683->13496|20716->13501|20779->13536|20808->13537|20841->13542|20870->13543|20902->13547|20931->13548|20963->13552|21090->13650|21120->13651|21161->13663|21191->13664|21241->13685|21271->13686|21304->13691|21351->13710|21380->13711|21479->13781|21509->13782|21549->13793|21579->13794|21629->13815|21659->13816|21692->13821|21754->13855|21783->13856|21816->13861|21845->13862|21875->13864|21904->13865|21935->13868|22014->13918|22044->13919|22075->13922|22228->14046|22258->14047|22290->14051|22507->14239|22537->14240|22570->14245|22621->14267|22651->14268|22685->14274|22751->14311|22781->14312|22816->14319|22870->14345|22899->14346|22934->14353|22963->14354|22995->14358|23024->14359|23055->14362|23084->14363|23116->14367|23145->14368|23176->14371
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|45->14|69->38|69->38|69->38|95->64|95->64|95->64|100->69|100->69|101->70|101->70|101->70|102->71|102->71|103->72|104->73|105->74|123->92|123->92|123->92|124->93|125->94|125->94|125->94|126->95|126->95|126->95|127->96|127->96|127->96|128->97|128->97|128->97|130->99|130->99|130->99|131->100|131->100|131->100|132->101|133->102|133->102|134->103|135->104|135->104|136->105|136->105|136->105|136->105|137->106|137->106|137->106|137->106|137->106|137->106|138->107|138->107|139->108|139->108|139->108|139->108|139->108|139->108|139->108|140->109|140->109|141->110|141->110|141->110|141->110|141->110|141->110|141->110|142->111|143->112|144->113|145->114|145->114|146->115|147->116|148->117|148->117|148->117|148->117|149->118|149->118|149->118|150->119|150->119|150->119|151->120|151->120|151->120|152->121|152->121|152->121|153->122|153->122|153->122|154->123|154->123|154->123|155->124|155->124|155->124|156->125|156->125|156->125|157->126|157->126|157->126|158->127|158->127|158->127|159->128|159->128|159->128|160->129|160->129|160->129|161->130|161->130|161->130|164->133|165->134|173->142|173->142|174->143|175->144|175->144|176->145|177->146|178->147|200->169|200->169|200->169|203->172|206->175|208->177|208->177|210->179|215->184|215->184|216->185|223->192|223->192|224->193|225->194|225->194|226->195|226->195|227->196|233->202|233->202|234->203|247->216|247->216|248->217|250->219|250->219|250->219|250->219|258->227|258->227|258->227|258->227|264->233|264->233|265->234|265->234|266->235|272->241|272->241|273->242|273->242|274->243|274->243|275->244|275->244|275->244|277->246|277->246|278->247|284->253|284->253|285->254|286->255|286->255|286->255|286->255|287->256|288->257|288->257|289->258|289->258|291->260|291->260|292->261|295->264|295->264|296->265|303->272|303->272|304->273|304->273|307->276|307->276|309->278|309->278|310->279|311->280|311->280|312->281|314->283|314->283|314->283|314->283|314->283|314->283|315->284|316->285|316->285|318->287|318->287|318->287|318->287|318->287|318->287|319->288|320->289|320->289|321->290|321->290|321->290|321->290|322->291|324->293|324->293|324->293|324->293|324->293|324->293|325->294|326->295|326->295|328->297|328->297|328->297|328->297|328->297|328->297|329->298|330->299|330->299|331->300|331->300|332->301|332->301|334->303|334->303|334->303|335->304|338->307|338->307|339->308|346->315|346->315|347->316|347->316|347->316|348->317|348->317|348->317|349->318|350->319|350->319|351->320|351->320|352->321|352->321|353->322|353->322|354->323|354->323|356->325
                  -- GENERATED --
              */
          