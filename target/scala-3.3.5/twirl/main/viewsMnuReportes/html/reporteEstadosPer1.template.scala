
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

object reporteEstadosPer1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
datos: List[List[String]], desdeAAMMDD: String, hastaAAMMDD: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "DETALLE ESTADOS Y REPARACIONES POR PERIODO","desde "+utilities.Fechas.DDMMAA(desdeAAMMDD)+" hasta "+utilities.Fechas.DDMMAA(hastaAAMMDD))),format.raw/*12.170*/("""
		
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

				<a class="toggle-vis" href="#" data-column="6"><kbd id="kdb6" style="background-color: #73C6B6">Codigo</kbd></a>
				<a class="toggle-vis" href="#" data-column="7"><kbd id="kdb7" style="background-color: #73C6B6">Equipo</kbd></a>
				<a class="toggle-vis" href="#" data-column="8"><kbd id="kdb8" style="background-color: #73C6B6">Cant Equipo</kbd></a>
				<a class="toggle-vis" href="#" data-column="9"><kbd id="kdb9" style="background-color: #73C6B6">Mon Equip</kbd></a>
				<a class="toggle-vis" href="#" data-column="10"><kbd id="kdb10" style="background-color: #73C6B6">P.U. Equipo</kbd></a>
				<a class="toggle-vis" href="#" data-column="11"><kbd id="kdb11" style="background-color: #73C6B6">KG</kbd></a>
				<a class="toggle-vis" href="#" data-column="12"><kbd id="kdb12" style="background-color: #73C6B6">M2</kbd></a>
				<a class="toggle-vis" href="#" data-column="13"><kbd id="kdb13" style="background-color: #73C6B6">Estado</kbd></a>
				<a class="toggle-vis" href="#" data-column="14"><kbd id="kdb14" style="background-color: #73C6B6">Cant Estado</kbd></a>
				<a class="toggle-vis" href="#" data-column="15"><kbd id="kdb15" style="background-color: #73C6B6">Sigla</kbd></a>
				<a class="toggle-vis" href="#" data-column="16"><kbd id="kdb16" style="background-color: #73C6B6">Reparacion</kbd></a>
				<a class="toggle-vis" href="#" data-column="17"><kbd id="kdb17" style="background-color: #73C6B6">Cant Repar</kbd></a>
				<a class="toggle-vis" href="#" data-column="18"><kbd id="kdb18" style="background-color: #73C6B6">Mon Repar</kbd></a>
				<a class="toggle-vis" href="#" data-column="19"><kbd id="kdb19" style="background-color: #73C6B6">P.U. Reparacion</kbd></a>
		</div>
    				
    				
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
					<TR> 
					        <TH style= "text-align: center; vertical-align: top;">"""),_display_(/*65.69*/mapDiccionario/*65.83*/.get("Bodega")),format.raw/*65.97*/("""</TH>
							<TH style= "text-align: center; vertical-align: top;">Cliente</TH>
					        <TH style= "text-align: center; vertical-align: top;">Nro Mov</TH>
							<TH style= "text-align: center; vertical-align: top;">Ref Clie</TH>
					        <TH style= "text-align: center; vertical-align: top;">Fecha Guia</TH>
							"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1"))/*70.147*/{_display_(Seq[Any](format.raw/*70.148*/("""
								"""),format.raw/*71.9*/("""<TH style= "text-align: center; vertical-align: top;">COBRA """),_display_(/*71.70*/mapDiccionario("ARR")),format.raw/*71.91*/("""</TH>
							""")))}else/*72.13*/{_display_(Seq[Any](format.raw/*72.14*/("""
								"""),format.raw/*73.9*/("""<TH style= "display:none"></TH>
							""")))}),format.raw/*74.9*/("""
							"""),format.raw/*75.8*/("""<TH style= "text-align: center; vertical-align: top;">Codigo</TH>
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
				"""),_display_(/*93.6*/for(lista1 <- datos) yield /*93.26*/{_display_(Seq[Any](format.raw/*93.27*/("""
					"""),format.raw/*94.6*/("""<TR>
						<td style="text-align: left; vertical-align:middle;">"""),_display_(/*95.61*/lista1/*95.67*/.get(0)),format.raw/*95.74*/("""</td>
						<td style="text-align: left; vertical-align:middle;">"""),_display_(/*96.61*/lista1/*96.67*/.get(18)),format.raw/*96.75*/("""</td>
						<td style="text-align: center; vertical-align:middle;">"""),_display_(/*97.63*/lista1/*97.69*/.get(1)),format.raw/*97.76*/("""</td>
						<td style="text-align: center; vertical-align:middle;">"""),_display_(/*98.63*/lista1/*98.69*/.get(17)),format.raw/*98.77*/("""</td>
						<td style="text-align: center; vertical-align:middle;">
							<span style="display:none">"""),_display_(/*100.36*/lista1/*100.42*/.get(2)),format.raw/*100.49*/("""</span>
							"""),_display_(/*101.9*/utilities/*101.18*/.Fechas.DDMMAA(lista1.get(2))),format.raw/*101.47*/("""
						"""),format.raw/*102.7*/("""</td>
						"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1"))/*103.146*/{_display_(Seq[Any](format.raw/*103.147*/("""
							"""),format.raw/*104.8*/("""<td  style="text-align:center;">
								"""),_display_(if(lista1.get(21).equals("1"))/*105.40*/{_display_(Seq[Any](format.raw/*105.41*/("""
									"""),format.raw/*106.10*/("""<div style="display: none">"""),_display_(/*106.38*/lista1/*106.44*/.get(19)),format.raw/*106.52*/("""</div>
									<input type="hidden" id="select_"""),_display_(/*107.43*/lista1/*107.49*/.get(20)),format.raw/*107.57*/("""" value=""""),_display_(/*107.67*/lista1/*107.73*/.get(19)),format.raw/*107.81*/("""">
									"""),_display_(if(lista1.get(19).equals("1"))/*108.41*/{_display_(Seq[Any](format.raw/*108.42*/("""
										"""),format.raw/*109.11*/("""<input type="checkbox" id="selectCheck_"""),_display_(/*109.51*/lista1/*109.57*/.get(20)),format.raw/*109.65*/("""" checked onchange="modificaSelect('"""),_display_(/*109.102*/lista1/*109.108*/.get(20)),format.raw/*109.116*/("""');">
									""")))}else/*110.15*/{_display_(Seq[Any](format.raw/*110.16*/("""
										"""),format.raw/*111.11*/("""<input type="checkbox" id="selectCheck_"""),_display_(/*111.51*/lista1/*111.57*/.get(20)),format.raw/*111.65*/("""" onchange="modificaSelect('"""),_display_(/*111.94*/lista1/*111.100*/.get(20)),format.raw/*111.108*/("""');">
										""")))}),format.raw/*112.12*/("""
								""")))} else {null} ),format.raw/*113.10*/("""
							"""),format.raw/*114.8*/("""</td>
						""")))}else/*115.12*/{_display_(Seq[Any](format.raw/*115.13*/("""
							"""),format.raw/*116.8*/("""<td style= "display:none"></td>
						""")))}),format.raw/*117.8*/("""

						"""),format.raw/*119.7*/("""<td style="text-align: left; vertical-align:middle;">"""),_display_(/*119.61*/lista1/*119.67*/.get(3)),format.raw/*119.74*/("""</td>
						<td style="text-align: left; vertical-align:middle;">"""),_display_(/*120.61*/lista1/*120.67*/.get(4)),format.raw/*120.74*/("""</td>
						<td style="text-align: right; vertical-align:middle;">"""),_display_(/*121.62*/lista1/*121.68*/.get(5)),format.raw/*121.75*/("""</td>
						<td style="text-align: center; vertical-align:middle;">"""),_display_(/*122.63*/lista1/*122.69*/.get(6)),format.raw/*122.76*/("""</td>
						<td style="text-align: right; vertical-align:middle;">"""),_display_(/*123.62*/lista1/*123.68*/.get(7)),format.raw/*123.75*/("""</td>
						<td style="text-align: right; vertical-align:middle;">"""),_display_(/*124.62*/lista1/*124.68*/.get(15)),format.raw/*124.76*/("""</td>
						<td style="text-align: right; vertical-align:middle;">"""),_display_(/*125.62*/lista1/*125.68*/.get(16)),format.raw/*125.76*/("""</td>
						<td style="text-align: center; vertical-align:middle;">"""),_display_(/*126.63*/lista1/*126.69*/.get(8)),format.raw/*126.76*/("""</td>
						<td style="text-align: right; vertical-align:middle;">"""),_display_(/*127.62*/lista1/*127.68*/.get(9)),format.raw/*127.75*/("""</td>
						<td style="text-align: center; vertical-align:middle;">"""),_display_(/*128.63*/lista1/*128.69*/.get(10)),format.raw/*128.77*/("""</td>
						<td style="text-align: left; vertical-align:middle;">"""),_display_(/*129.61*/lista1/*129.67*/.get(11)),format.raw/*129.75*/("""</td>
						<td class="cant" style="text-align: right; vertical-align:middle;">"""),_display_(/*130.75*/lista1/*130.81*/.get(12)),format.raw/*130.89*/("""</td>
						<td style="text-align: center; vertical-align:middle;">"""),_display_(/*131.63*/lista1/*131.69*/.get(13)),format.raw/*131.77*/("""</td>
						<td class="pu" style="text-align: right; vertical-align:middle;">"""),_display_(/*132.73*/lista1/*132.79*/.get(14)),format.raw/*132.87*/("""</td>
						<td class="total" style="text-align: right; vertical-align:middle;">suma</td>
					</TR>
	 			""")))}),format.raw/*135.7*/("""
				"""),format.raw/*136.5*/("""</tbody>
				<tfoot style="background-color: #eeeeee">
					<TR>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						<TH></TH>
						"""),_display_(if(mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo")!=null && mapPermiso.get("parametro.cobraArriendoPorEstadoEquipo").equals("1"))/*144.146*/{_display_(Seq[Any](format.raw/*144.147*/("""
							"""),format.raw/*145.8*/("""<TH></TH>
						""")))}else/*146.12*/{_display_(Seq[Any](format.raw/*146.13*/("""
							"""),format.raw/*147.8*/("""<TH style= "display:none"></TH>
						""")))}),format.raw/*148.8*/("""
						"""),format.raw/*149.7*/("""<TH></TH>
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
	
	<form id="excel" class="formulario" method="post" action="/routes2/reporteEstadosPer1Excel/">
		<input type="hidden" name="desdeAAMMDD" value=""""),_display_(/*171.51*/desdeAAMMDD),format.raw/*171.62*/("""">
		<input type="hidden" name="hastaAAMMDD" value=""""),_display_(/*172.51*/hastaAAMMDD),format.raw/*172.62*/("""">
	</form>

""")))}),format.raw/*175.2*/("""


"""),format.raw/*178.1*/("""<script>

	$(document).ready(function() """),format.raw/*180.31*/("""{"""),format.raw/*180.32*/("""
		
		"""),format.raw/*182.3*/("""$('#tablaPrincipal thead tr')
        .clone(true)
        .addClass('filters')
        .appendTo('#tablaPrincipal thead');

		 $('#tablaPrincipal').DataTable("""),format.raw/*187.35*/("""{"""),format.raw/*187.36*/("""

				"""),format.raw/*189.5*/(""""orderCellsTop": true,
		    	"fixedHeader": true,
		    	"lengthMenu": [[-1, 20, 50, 100, 200], ["All",20, 50, 100, 200]],
		    	"paging": true,
		    	"order": [[ 0, "asc" ],[ 5, "asc" ],[ 3, "asc" ]],
				"info": true,
				"searching": true,

		    	"language": """),format.raw/*197.20*/("""{"""),format.raw/*197.21*/("""
		        	"""),format.raw/*198.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*199.11*/("""}"""),format.raw/*199.12*/(""",
		        initComplete: function () """),format.raw/*200.37*/("""{"""),format.raw/*200.38*/("""
	            """),format.raw/*201.14*/("""var api = this.api();

	            // For each column
	            api
	                .columns()
	                .eq(0)
	                .each(function (colIdx) """),format.raw/*207.42*/("""{"""),format.raw/*207.43*/("""
	                    """),format.raw/*208.22*/("""// Set the header cell to contain the input element
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
	                        .on('change', function (e) """),format.raw/*221.53*/("""{"""),format.raw/*221.54*/("""
	                            """),format.raw/*222.30*/("""// Get the search value
	                            $(this).attr('title', $(this).val());
	                            var regexr = '("""),format.raw/*224.45*/("""{"""),format.raw/*224.46*/("""search"""),format.raw/*224.52*/("""}"""),format.raw/*224.53*/(""")'; //$(this).parents('th').find('select').val();

	                            var cursorPosition = this.selectionStart;
	                            // Search the column for that value
	                            api
	                                .column(colIdx)
	                                .search(
	                                    this.value != ''
	                                        ? regexr.replace('"""),format.raw/*232.60*/("""{"""),format.raw/*232.61*/("""search"""),format.raw/*232.67*/("""}"""),format.raw/*232.68*/("""', '(((' + this.value + ')))')
	                                        : '',
	                                    this.value != '',
	                                    this.value == ''
	                                )
	                                .draw();
	                        """),format.raw/*238.26*/("""}"""),format.raw/*238.27*/(""")
	                        .on('keyup', function (e) """),format.raw/*239.52*/("""{"""),format.raw/*239.53*/("""
	                            """),format.raw/*240.30*/("""e.stopPropagation();

	                            $(this).trigger('change');
	                            $(this)
	                                .focus()[0]
	                                .setSelectionRange(cursorPosition, cursorPosition);
	                        """),format.raw/*246.26*/("""}"""),format.raw/*246.27*/(""");
	                """),format.raw/*247.18*/("""}"""),format.raw/*247.19*/(""");
	        	"""),format.raw/*248.11*/("""}"""),format.raw/*248.12*/("""
		 """),format.raw/*249.4*/("""}"""),format.raw/*249.5*/(""" """),format.raw/*249.6*/(""");
		  
	   $('a.toggle-vis').on('click', function (e) """),format.raw/*251.48*/("""{"""),format.raw/*251.49*/("""
			"""),format.raw/*252.4*/("""e.preventDefault();
			// Get the column API object
			let col = $(this).attr('data-column');
			var column = $('#tablaPrincipal').DataTable().column(col);
			// Toggle the visibility
			column.visible(!column.visible());
			if(column.visible())"""),format.raw/*258.24*/("""{"""),format.raw/*258.25*/("""
				"""),format.raw/*259.5*/("""document.getElementById('kdb'+col).style.backgroundColor = "#73C6B6";
			"""),format.raw/*260.4*/("""}"""),format.raw/*260.5*/("""else"""),format.raw/*260.9*/("""{"""),format.raw/*260.10*/("""
				"""),format.raw/*261.5*/("""document.getElementById('kdb'+col).style.backgroundColor = "#D6DBDF";
			"""),format.raw/*262.4*/("""}"""),format.raw/*262.5*/("""
		"""),format.raw/*263.3*/("""}"""),format.raw/*263.4*/(""");

		let granTotal = 0;
		document.querySelectorAll('#tablaPrincipal tr').forEach(fila => """),format.raw/*266.67*/("""{"""),format.raw/*266.68*/("""
			"""),format.raw/*267.4*/("""const cantElement = fila.querySelector('.cant');
			const puElement = fila.querySelector('.pu');
			const totalElement = fila.querySelector('.total');
			if (cantElement && puElement && totalElement) """),format.raw/*270.50*/("""{"""),format.raw/*270.51*/("""
				"""),format.raw/*271.5*/("""// Limpiar el texto de cualquier formato previo
				const cant = parseFloat(cantElement.textContent.replace(/[^\d.-]/g, '')) || 0;
				const pu = parseFloat(puElement.textContent.replace(/[^\d.-]/g, '')) || 0;
				// Calcular y formatear el resultado
				const resultado = cant * pu;
				granTotal += resultado;
				totalElement.textContent = formatStandar(resultado,0);
			"""),format.raw/*278.4*/("""}"""),format.raw/*278.5*/("""
		"""),format.raw/*279.3*/("""}"""),format.raw/*279.4*/(""");
		$("#granTotal").text(formatStandar(granTotal,0));
		document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*282.2*/("""}"""),format.raw/*282.3*/(""");

	const modificaSelect = (id_movimiento) => """),format.raw/*284.44*/("""{"""),format.raw/*284.45*/("""
		"""),format.raw/*285.3*/("""let select = $("#select_"+id_movimiento).val();
		if(select == 1)"""),format.raw/*286.18*/("""{"""),format.raw/*286.19*/("""
			"""),format.raw/*287.4*/("""modificaSelect2(id_movimiento, 0);
			const elementIdDet = "select_" + id_movimiento;
			$(`[id='$"""),format.raw/*289.13*/("""{"""),format.raw/*289.14*/("""elementIdDet"""),format.raw/*289.26*/("""}"""),format.raw/*289.27*/("""']`).each(function() """),format.raw/*289.48*/("""{"""),format.raw/*289.49*/("""
				"""),format.raw/*290.5*/("""$(this).val(0);
			"""),format.raw/*291.4*/("""}"""),format.raw/*291.5*/(""");
			const selectCheck = "selectCheck_" + id_movimiento;
			$(`[id='$"""),format.raw/*293.13*/("""{"""),format.raw/*293.14*/("""selectCheck"""),format.raw/*293.25*/("""}"""),format.raw/*293.26*/("""']`).each(function() """),format.raw/*293.47*/("""{"""),format.raw/*293.48*/("""
				"""),format.raw/*294.5*/("""$(this).prop('checked', false);
			"""),format.raw/*295.4*/("""}"""),format.raw/*295.5*/(""");
		"""),format.raw/*296.3*/("""}"""),format.raw/*296.4*/("""else"""),format.raw/*296.8*/("""{"""),format.raw/*296.9*/("""
			"""),format.raw/*297.4*/("""modificaSelect2(id_movimiento, 1);
			const elementIdDet = "select_" + id_movimiento;
			$(`[id='$"""),format.raw/*299.13*/("""{"""),format.raw/*299.14*/("""elementIdDet"""),format.raw/*299.26*/("""}"""),format.raw/*299.27*/("""']`).each(function() """),format.raw/*299.48*/("""{"""),format.raw/*299.49*/("""
				"""),format.raw/*300.5*/("""$(this).val(1);
			"""),format.raw/*301.4*/("""}"""),format.raw/*301.5*/(""");
			const selectCheck = "selectCheck_" + id_movimiento;
			$(`[id='$"""),format.raw/*303.13*/("""{"""),format.raw/*303.14*/("""selectCheck"""),format.raw/*303.25*/("""}"""),format.raw/*303.26*/("""']`).each(function() """),format.raw/*303.47*/("""{"""),format.raw/*303.48*/("""
				"""),format.raw/*304.5*/("""$(this).prop('checked', true);
			"""),format.raw/*305.4*/("""}"""),format.raw/*305.5*/(""");
		"""),format.raw/*306.3*/("""}"""),format.raw/*306.4*/("""
	"""),format.raw/*307.2*/("""}"""),format.raw/*307.3*/("""

	"""),format.raw/*309.2*/("""const modificaSelect2 = (id_movimiento, valor) => """),format.raw/*309.52*/("""{"""),format.raw/*309.53*/("""
		"""),format.raw/*310.3*/("""let formData = new FormData();
		formData.append('id_movimiento',id_movimiento);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*313.10*/("""{"""),format.raw/*313.11*/("""
			"""),format.raw/*314.4*/("""url: "/routes3/modCobraArriendoAjax/",
			type: "POST",
			method: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(respuesta)"""),format.raw/*321.32*/("""{"""),format.raw/*321.33*/("""
				"""),format.raw/*322.5*/("""if(respuesta=="error")"""),format.raw/*322.27*/("""{"""),format.raw/*322.28*/("""
					"""),format.raw/*323.6*/("""alertify.alert(msgError, function () """),format.raw/*323.43*/("""{"""),format.raw/*323.44*/("""
						"""),format.raw/*324.7*/("""location.href = "/";
					"""),format.raw/*325.6*/("""}"""),format.raw/*325.7*/(""");
				"""),format.raw/*326.5*/("""}"""),format.raw/*326.6*/("""
			"""),format.raw/*327.4*/("""}"""),format.raw/*327.5*/("""
		"""),format.raw/*328.3*/("""}"""),format.raw/*328.4*/(""");
	"""),format.raw/*329.2*/("""}"""),format.raw/*329.3*/("""
	
	
"""),format.raw/*332.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,datos:List[List[String]],desdeAAMMDD:String,hastaAAMMDD:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,datos,desdeAAMMDD,hastaAAMMDD)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,datos,desdeAAMMDD,hastaAAMMDD) => apply(mapDiccionario,mapPermiso,userMnu,datos,desdeAAMMDD,hastaAAMMDD)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteEstadosPer1.scala.html
                  HASH: eb95edc418c3befac37ed04c8e4ebde2653baf83
                  MATRIX: 1051->1|1310->167|1343->175|1359->183|1398->185|1427->189|1495->237|1525->242|1569->266|1600->270|1677->321|1865->487|1898->493|2697->1264|2721->1278|2757->1292|5259->3767|5282->3781|5317->3795|5809->4259|5849->4260|5885->4269|5973->4330|6015->4351|6052->4369|6091->4370|6127->4379|6197->4419|6232->4427|7435->5604|7471->5624|7510->5625|7543->5631|7635->5696|7650->5702|7678->5709|7771->5775|7786->5781|7815->5789|7910->5857|7925->5863|7953->5870|8048->5938|8063->5944|8092->5952|8223->6055|8239->6061|8268->6068|8311->6084|8330->6093|8381->6122|8416->6129|8596->6280|8637->6281|8673->6289|8773->6361|8813->6362|8852->6372|8908->6400|8924->6406|8954->6414|9031->6463|9047->6469|9077->6477|9115->6487|9131->6493|9161->6501|9232->6544|9272->6545|9312->6556|9380->6596|9396->6602|9426->6610|9492->6647|9509->6653|9540->6661|9580->6681|9620->6682|9660->6693|9728->6733|9744->6739|9774->6747|9831->6776|9848->6782|9879->6790|9928->6807|9983->6817|10019->6825|10056->6842|10096->6843|10132->6851|10202->6890|10238->6898|10320->6952|10336->6958|10365->6965|10459->7031|10475->7037|10504->7044|10599->7111|10615->7117|10644->7124|10740->7192|10756->7198|10785->7205|10880->7272|10896->7278|10925->7285|11020->7352|11036->7358|11066->7366|11161->7433|11177->7439|11207->7447|11303->7515|11319->7521|11348->7528|11443->7595|11459->7601|11488->7608|11584->7676|11600->7682|11630->7690|11724->7756|11740->7762|11770->7770|11878->7850|11894->7856|11924->7864|12020->7932|12036->7938|12066->7946|12172->8024|12188->8030|12218->8038|12356->8145|12389->8150|12708->8440|12749->8441|12785->8449|12826->8470|12866->8471|12902->8479|12972->8518|13007->8525|13591->9081|13624->9092|13705->9145|13738->9156|13783->9170|13814->9173|13883->9213|13913->9214|13947->9220|14135->9379|14165->9380|14199->9386|14494->9652|14524->9653|14565->9665|14672->9743|14702->9744|14769->9782|14799->9783|14842->9797|15036->9962|15066->9963|15117->9985|15834->10673|15864->10674|15923->10704|16087->10839|16117->10840|16152->10846|16182->10847|16635->11271|16665->11272|16700->11278|16730->11279|17048->11568|17078->11569|17160->11622|17190->11623|17249->11653|17548->11923|17578->11924|17627->11944|17657->11945|17699->11958|17729->11959|17761->11963|17790->11964|17819->11965|17903->12020|17933->12021|17965->12025|18239->12270|18269->12271|18302->12276|18403->12349|18432->12350|18464->12354|18494->12355|18527->12360|18628->12433|18657->12434|18688->12437|18717->12438|18837->12529|18867->12530|18899->12534|19128->12734|19158->12735|19191->12740|19593->13114|19622->13115|19653->13118|19682->13119|19833->13242|19862->13243|19938->13290|19968->13291|19999->13294|20093->13359|20123->13360|20155->13364|20282->13462|20312->13463|20353->13475|20383->13476|20433->13497|20463->13498|20496->13503|20543->13522|20572->13523|20671->13593|20701->13594|20741->13605|20771->13606|20821->13627|20851->13628|20884->13633|20947->13668|20976->13669|21009->13674|21038->13675|21070->13679|21099->13680|21131->13684|21258->13782|21288->13783|21329->13795|21359->13796|21409->13817|21439->13818|21472->13823|21519->13842|21548->13843|21647->13913|21677->13914|21717->13925|21747->13926|21797->13947|21827->13948|21860->13953|21922->13987|21951->13988|21984->13993|22013->13994|22043->13996|22072->13997|22103->14000|22182->14050|22212->14051|22243->14054|22396->14178|22426->14179|22458->14183|22675->14371|22705->14372|22738->14377|22789->14399|22819->14400|22853->14406|22919->14443|22949->14444|22984->14451|23038->14477|23067->14478|23102->14485|23131->14486|23163->14490|23192->14491|23223->14494|23252->14495|23284->14499|23313->14500|23346->14505
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|45->14|69->38|69->38|69->38|96->65|96->65|96->65|101->70|101->70|102->71|102->71|102->71|103->72|103->72|104->73|105->74|106->75|124->93|124->93|124->93|125->94|126->95|126->95|126->95|127->96|127->96|127->96|128->97|128->97|128->97|129->98|129->98|129->98|131->100|131->100|131->100|132->101|132->101|132->101|133->102|134->103|134->103|135->104|136->105|136->105|137->106|137->106|137->106|137->106|138->107|138->107|138->107|138->107|138->107|138->107|139->108|139->108|140->109|140->109|140->109|140->109|140->109|140->109|140->109|141->110|141->110|142->111|142->111|142->111|142->111|142->111|142->111|142->111|143->112|144->113|145->114|146->115|146->115|147->116|148->117|150->119|150->119|150->119|150->119|151->120|151->120|151->120|152->121|152->121|152->121|153->122|153->122|153->122|154->123|154->123|154->123|155->124|155->124|155->124|156->125|156->125|156->125|157->126|157->126|157->126|158->127|158->127|158->127|159->128|159->128|159->128|160->129|160->129|160->129|161->130|161->130|161->130|162->131|162->131|162->131|163->132|163->132|163->132|166->135|167->136|175->144|175->144|176->145|177->146|177->146|178->147|179->148|180->149|202->171|202->171|203->172|203->172|206->175|209->178|211->180|211->180|213->182|218->187|218->187|220->189|228->197|228->197|229->198|230->199|230->199|231->200|231->200|232->201|238->207|238->207|239->208|252->221|252->221|253->222|255->224|255->224|255->224|255->224|263->232|263->232|263->232|263->232|269->238|269->238|270->239|270->239|271->240|277->246|277->246|278->247|278->247|279->248|279->248|280->249|280->249|280->249|282->251|282->251|283->252|289->258|289->258|290->259|291->260|291->260|291->260|291->260|292->261|293->262|293->262|294->263|294->263|297->266|297->266|298->267|301->270|301->270|302->271|309->278|309->278|310->279|310->279|313->282|313->282|315->284|315->284|316->285|317->286|317->286|318->287|320->289|320->289|320->289|320->289|320->289|320->289|321->290|322->291|322->291|324->293|324->293|324->293|324->293|324->293|324->293|325->294|326->295|326->295|327->296|327->296|327->296|327->296|328->297|330->299|330->299|330->299|330->299|330->299|330->299|331->300|332->301|332->301|334->303|334->303|334->303|334->303|334->303|334->303|335->304|336->305|336->305|337->306|337->306|338->307|338->307|340->309|340->309|340->309|341->310|344->313|344->313|345->314|352->321|352->321|353->322|353->322|353->322|354->323|354->323|354->323|355->324|356->325|356->325|357->326|357->326|358->327|358->327|359->328|359->328|360->329|360->329|363->332
                  -- GENERATED --
              */
          