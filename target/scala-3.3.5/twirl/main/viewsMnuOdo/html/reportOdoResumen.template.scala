
package viewsMnuOdo.html

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

object reportOdoResumen extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template12[Map[String,String],Map[String,String],utilities.UserMnu,utilities.Fechas,utilities.Fechas,Double,Double,Double,List[List[String]],Map[String, List[List[String]]],Map[String, List[List[String]]],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
desde: utilities.Fechas, hasta: utilities.Fechas, uf: Double, usd: Double, eur: Double, 
totalesPorProyecto: List[List[String]], mapAgrupado: Map[String, List[List[String]]],  mapDetallado: Map[String, List[List[String]]], cantDec: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
	"""),format.raw/*6.2*/("""<!-- ANCLA CABECERAS DE TABLAS -->
	<style type="text/css"> 
        thead tr th """),format.raw/*8.21*/("""{"""),format.raw/*8.22*/(""" 
            """),format.raw/*9.13*/("""position: sticky;
			background-color: #eeeeee;
            top: 0;
        """),format.raw/*12.9*/("""}"""),format.raw/*12.10*/("""
    """),format.raw/*13.5*/("""</style>


	"""),_display_(/*16.3*/main("")/*16.11*/ {_display_(Seq[Any](format.raw/*16.13*/("""

		"""),_display_(/*18.4*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*18.52*/("""
		"""),format.raw/*19.3*/("""<div id="mostrarmostrar">
			"""),_display_(/*20.5*/barraTitulo(mapDiccionario, "REPORTE ODO RESUMEN Y DETALLE POR TODOS LOS PROYECTOS (INCLUIDOS AJUSTES DE EP)",
				"PERIODO: desde " + desde.getFechaStr() +" --- hasta: " +  hasta.getFechaStr())),format.raw/*21.84*/("""
				
			"""),format.raw/*23.4*/("""<div class="noprint">
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
			
			<form id="excel" class="formulario" method="post" action="/reportOdoResumenExcel/">
				<input type="hidden" name="fechaDesde" value=""""),_display_(/*45.52*/desde/*45.57*/.getFechaSql().toString()),format.raw/*45.82*/("""">
				<input type="hidden" name="fechaHasta" value=""""),_display_(/*46.52*/hasta/*46.57*/.getFechaSql().toString()),format.raw/*46.82*/("""">
				<input type="hidden" name="uf" value=""""),_display_(/*47.44*/uf),format.raw/*47.46*/("""">
				<input type="hidden" name="usd" value=""""),_display_(/*48.45*/usd),format.raw/*48.48*/("""">
				<input type="hidden" name="eur" value=""""),_display_(/*49.45*/eur),format.raw/*49.48*/("""">
			</form>
			
			
			<table id="general" class="table table-sm table-bordered table-condensed table-fluid">
				<tr>
					<td>
						<div align="left"><h5><b><font color="#008000"> RESUMEN POR PROYECTOS (INCLUYE AJUSTES A EP): </font></b></h5></div>
						
						<div class="table-responsive">
							<table id="tablaResumen" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						        <thead style="background-color: #eeeeee">
							        <TR> 
								        <TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
										<TH style= "text-align: center;vertical-align: top;">"""),_display_(/*63.65*/mapDiccionario/*63.79*/.get("BODEGA")),format.raw/*63.93*/("""/PROYECTO</TH>
										<TH style= "text-align: center;vertical-align: top;">NOMBRE<BR>CLIENTE</TH>
										<TH style= "text-align: center;vertical-align: top;">NOMBRE<br>PROYECTO</TH>
										<TH style= "text-align: center;vertical-align: top;">COMERCIAL</TH>
										
										<TH style= "text-align:center;vertical-align:top;">SUBTOTAL<BR>(en """),_display_(/*68.79*/mapDiccionario/*68.93*/.get("PESOS")),format.raw/*68.106*/(""")</TH>
										<TH style= "text-align:center;vertical-align:top;">AJUSTES<BR>(en """),_display_(/*69.78*/mapDiccionario/*69.92*/.get("PESOS")),format.raw/*69.105*/(""")</TH>
										<TH style= "text-align:center;vertical-align:top;">TOTAL<BR>(en """),_display_(/*70.76*/mapDiccionario/*70.90*/.get("PESOS")),format.raw/*70.103*/(""")</TH>
									</TR>
								</thead>
								<tbody>
									"""),_display_(/*74.11*/for(lista1 <- totalesPorProyecto) yield /*74.44*/{_display_(Seq[Any](format.raw/*74.45*/("""
										"""),format.raw/*75.11*/("""<TR>
											<td style="text-align:left;vertical-align:middle;">"""),_display_(/*76.64*/lista1/*76.70*/.get(10)),format.raw/*76.78*/("""</td>
											<td style="text-align:left;vertical-align:middle;">"""),_display_(/*77.64*/lista1/*77.70*/.get(1)),format.raw/*77.77*/("""</td>
											<td style="text-align:left;vertical-align:middle;">"""),_display_(/*78.64*/lista1/*78.70*/.get(2)),format.raw/*78.77*/("""</td>
											<td style="text-align:left;vertical-align:middle;">"""),_display_(/*79.64*/lista1/*79.70*/.get(3)),format.raw/*79.77*/("""</td>
											<td style="text-align:left;vertical-align:middle;">"""),_display_(/*80.64*/lista1/*80.70*/.get(4)),format.raw/*80.77*/("""</td>
											<td style= "text-align:right;" class="subtotal">"""),_display_(/*81.61*/lista1/*81.67*/.get(5)),format.raw/*81.74*/("""</td>
											<td style= "text-align:right;" class="ajuste">"""),_display_(/*82.59*/lista1/*82.65*/.get(6)),format.raw/*82.72*/("""</td>
											<td style= "text-align:right;" class="total">"""),_display_(/*83.58*/lista1/*83.64*/.get(7)),format.raw/*83.71*/("""</td>
										</TR>
						 			""")))}),format.raw/*85.12*/("""
								"""),format.raw/*86.9*/("""</tbody>
								<tfoot>
									<TR>
										<td style="background-color: #eeeeee">TOTALES</td>
										<td style="background-color: #eeeeee"></td>
										<td style="background-color: #eeeeee"></td>
										<td style="background-color: #eeeeee"></td>
										<td style="background-color: #eeeeee"></td>
										<td style="background-color: #eeeeee;text-align:right;" id="subtotal"></td>
										<td style="background-color: #eeeeee;text-align:right;" id="ajuste"></td>
										<td style="background-color: #eeeeee;text-align:right;" id="total"></td>
									</TR>
								</tfoot>
							</table>
						</div>
					</td>
				</tr>
				
				<tr>
					<td>
						<br><br><br>
						<div align="left"><h5><b><font color="#008000"> DETALLE POR PROYECTO (INCLUYE AJUSTES A EP): </font></b></h5></div>	
						<div class="table-responsive">
							"""),_display_(/*109.9*/for(lista1 <- totalesPorProyecto) yield /*109.42*/{_display_(Seq[Any](format.raw/*109.43*/("""
								"""),_display_(if(mapAgrupado.get(lista1.get(0))!=null && mapDetallado.get(lista1.get(0))!=null)/*110.91*/{_display_(Seq[Any](format.raw/*110.92*/("""
									
									"""),format.raw/*112.10*/("""<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
										<TR>
											<td colspan="20" style="background-color:green;text-align:left">
												<h5><b><font color="white"> """),_display_(/*115.42*/lista1/*115.48*/.get(1)),format.raw/*115.55*/(""" """),format.raw/*115.56*/("""</font></b></h5>
											</td>
										</TR>
									</table>

									<table id="tablaAgrupado" class="table table-sm table-hover table-bordered table-condensed table-fluid" >
										<thead class="header">
											<TR> 
												<TH colspan="13" style="text-align:left;vertical-align:top;background-color:#eeeeee;">RESUMEN AGRUPADO:</TH>
											</TR>
								        	<TR> 
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">FAMILIA</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">NRO.COTI</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">COD-SERVICIO</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">SERVICIO</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">COD-EQUIPO</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">EQUIPO</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">UN</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CANT</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TOTAL</TH>
												
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CANT MIN</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TOTAL MINIMO</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TOTAL ADICIONAL</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">GRAN TOTAL</TH>
		
											</TR>
										</thead>
										<tbody>
											"""),_display_(/*144.13*/for((inicial,index) <- mapAgrupado.get(lista1.get(0)).zipWithIndex) yield /*144.80*/{_display_(Seq[Any](format.raw/*144.81*/("""
												"""),format.raw/*145.13*/("""<tr>
													<td style="text-align: left;">"""),_display_(/*146.45*/inicial/*146.52*/.get(0)),format.raw/*146.59*/("""</td>
													<td style="text-align: center;">"""),_display_(/*147.47*/inicial/*147.54*/.get(12)),format.raw/*147.62*/("""</td>
													<td style="text-align: center;">"""),_display_(/*148.47*/inicial/*148.54*/.get(1)),format.raw/*148.61*/("""</td>
													<td style="text-align: left;">"""),_display_(/*149.45*/inicial/*149.52*/.get(2)),format.raw/*149.59*/("""</td>
													<td style="text-align: center;">"""),_display_(/*150.47*/inicial/*150.54*/.get(3)),format.raw/*150.61*/("""</td>
													<td style="text-align: left;">"""),_display_(/*151.45*/inicial/*151.52*/.get(4)),format.raw/*151.59*/("""</td>
													<td style="text-align: center;">"""),_display_(/*152.47*/inicial/*152.54*/.get(5)),format.raw/*152.61*/("""</td>
													<td style="text-align: right;" class=""""),_display_(/*153.53*/lista1/*153.59*/.get(0)),format.raw/*153.66*/("""_resumenTot1">"""),_display_(/*153.81*/inicial/*153.88*/.get(6)),format.raw/*153.95*/("""</td>
													<td style="text-align: right;" class=""""),_display_(/*154.53*/lista1/*154.59*/.get(0)),format.raw/*154.66*/("""_resumenTot2">"""),_display_(/*154.81*/inicial/*154.88*/.get(7)),format.raw/*154.95*/("""</td>
													
													<td style="text-align: right;" class=""""),_display_(/*156.53*/lista1/*156.59*/.get(0)),format.raw/*156.66*/("""_resumenTot21">"""),_display_(/*156.82*/inicial/*156.89*/.get(8)),format.raw/*156.96*/("""</td>
													<td style="text-align: right;" class=""""),_display_(/*157.53*/lista1/*157.59*/.get(0)),format.raw/*157.66*/("""_resumenTot22">"""),_display_(/*157.82*/inicial/*157.89*/.get(9)),format.raw/*157.96*/("""</td>
													<td style="text-align: right;" class=""""),_display_(/*158.53*/lista1/*158.59*/.get(0)),format.raw/*158.66*/("""_resumenTot23">"""),_display_(/*158.82*/inicial/*158.89*/.get(10)),format.raw/*158.97*/("""</td>
													<td style="text-align: right;" class=""""),_display_(/*159.53*/lista1/*159.59*/.get(0)),format.raw/*159.66*/("""_resumenTot24">"""),_display_(/*159.82*/inicial/*159.89*/.get(11)),format.raw/*159.97*/("""</td>
							
								 			""")))}),format.raw/*161.14*/("""
								 			"""),format.raw/*162.13*/("""<TR style="background-color: #9FF781">
												<td style="background-color: #9FF781" colspan="7">TOTALES NETO (sin ajustes)</td>
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*164.75*/lista1/*164.81*/.get(0)),format.raw/*164.88*/("""_resumenTot1"></div></td>
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*165.75*/lista1/*165.81*/.get(0)),format.raw/*165.88*/("""_resumenTot2"></div></td>
												
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*167.75*/lista1/*167.81*/.get(0)),format.raw/*167.88*/("""_resumenTot21"></div></td>
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*168.75*/lista1/*168.81*/.get(0)),format.raw/*168.88*/("""_resumenTot22"></div></td>
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*169.75*/lista1/*169.81*/.get(0)),format.raw/*169.88*/("""_resumenTot23"></div></td>
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*170.75*/lista1/*170.81*/.get(0)),format.raw/*170.88*/("""_resumenTot24"></div></td>
											</tr>
										</tbody>
									</table>
									
									<br>
				  
									<table id="tablaDetallado" class="table table-sm table-hover table-bordered table-condensed table-fluid" >
										<thead class="header">
											<TR> 
												<TH colspan="7" style="text-align:left;vertical-align:top;background-color:#eeeeee;">DETALLE DE LOS SERVICIOS:</TH>
												<TH colspan="2" style="text-align:left;vertical-align:top;background-color:#eeeeee;">EQUIPOS ASOCIADOS:</TH>
												<TH colspan="2" style="text-align:left;vertical-align:top;background-color:#eeeeee;">HORARIO:</TH>
												<TH colspan="3" style="text-align:left;vertical-align:top;background-color:#eeeeee;">LECTURA:</TH>
												<TH colspan="6" style="text-align:left;vertical-align:top;background-color:#eeeeee;">VALORIZACION:</TH>
											</TR>
								        	<TR> 
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">ID</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">FAMILIA</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">NRO.COTI</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">COD-SERVICIO</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">SERVICIO</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">FECHA</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">DETALLE</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">COD-EQUIPO</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">EQUIPO</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">HR INI</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">HR TER</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">UN</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">LECT INI</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">LECT TER</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">CANT</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">MON</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">PU</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">SUBTOTAL</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TASA</TH>
												<TH style="text-align:center;vertical-align:top;background-color:#eeeeee;">TOTAL</TH>
											</TR>
										</thead>
										<tbody>
											"""),_display_(/*210.13*/for((inicial,index) <- mapDetallado.get(lista1.get(0)).zipWithIndex) yield /*210.81*/{_display_(Seq[Any](format.raw/*210.82*/("""
												"""),format.raw/*211.13*/("""<tr>
													<td style="text-align: center;">"""),_display_(/*212.47*/inicial/*212.54*/.get(6)),format.raw/*212.61*/("""</td>
													<td style="text-align: left;">"""),_display_(/*213.45*/inicial/*213.52*/.get(1)),format.raw/*213.59*/("""</td>
													<td style="text-align: center;">"""),_display_(/*214.47*/inicial/*214.54*/.get(24)),format.raw/*214.62*/("""</td>
													<td style="text-align: center;">"""),_display_(/*215.47*/inicial/*215.54*/.get(2)),format.raw/*215.61*/("""</td>
													<td style="text-align: left;">"""),_display_(/*216.45*/inicial/*216.52*/.get(3)),format.raw/*216.59*/("""</td>
													<td style="text-align: center;">
														<div style="display: none;">"""),_display_(/*218.44*/utilities/*218.53*/.Fechas.AAMMDD(inicial.get(4))),format.raw/*218.83*/("""</div>
														"""),_display_(/*219.16*/inicial/*219.23*/.get(4)),format.raw/*219.30*/("""
													"""),format.raw/*220.14*/("""</td>
													<td style="text-align: left;">"""),_display_(/*221.45*/inicial/*221.52*/.get(5)),format.raw/*221.59*/("""</td>
													<td style="text-align: center;">"""),_display_(/*222.47*/inicial/*222.54*/.get(9)),format.raw/*222.61*/("""</td>
													<td style="text-align: left;">"""),_display_(/*223.45*/inicial/*223.52*/.get(10)),format.raw/*223.60*/("""</td>
													<td style="text-align: center;">"""),_display_(/*224.47*/inicial/*224.54*/.get(7)),format.raw/*224.61*/("""</td>
													<td style="text-align: center;">"""),_display_(/*225.47*/inicial/*225.54*/.get(8)),format.raw/*225.61*/("""</td>
													<td style="text-align: center;">"""),_display_(/*226.47*/inicial/*226.54*/.get(11)),format.raw/*226.62*/("""</td>
													
													<td style="text-align: right;">"""),_display_(/*228.46*/inicial/*228.53*/.get(12)),format.raw/*228.61*/("""</td>
													<td style="text-align: right;">"""),_display_(/*229.46*/inicial/*229.53*/.get(13)),format.raw/*229.61*/("""</td>
													<td style="text-align: right;">"""),_display_(/*230.46*/inicial/*230.53*/.get(14)),format.raw/*230.61*/("""</td>
													
													<td style="text-align: right;">"""),_display_(/*232.46*/inicial/*232.53*/.get(15)),format.raw/*232.61*/("""</td>
													
													
													<td style="text-align: right;">"""),_display_(/*235.46*/inicial/*235.53*/.get(16)),format.raw/*235.61*/("""</td>
													<td style="text-align: right;" class=""""),_display_(/*236.53*/lista1/*236.59*/.get(0)),format.raw/*236.66*/("""_total5">"""),_display_(/*236.76*/inicial/*236.83*/.get(17)),format.raw/*236.91*/("""</td>
													<td style="text-align: right;">"""),_display_(/*237.46*/inicial/*237.53*/.get(18)),format.raw/*237.61*/("""</td>
													<td style="text-align: right;" class=""""),_display_(/*238.53*/lista1/*238.59*/.get(0)),format.raw/*238.66*/("""_total7">"""),_display_(/*238.76*/inicial/*238.83*/.get(19)),format.raw/*238.91*/("""</td>
												</TR>
								 			""")))}),format.raw/*240.14*/("""
								 			"""),format.raw/*241.13*/("""<TR style="background-color: #9FF781">
												<td style="background-color: #9FF781" colspan="13">TOTALES NETO (sin ajustes)</td>
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*243.75*/lista1/*243.81*/.get(0)),format.raw/*243.88*/("""_total1"></div></td>
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*244.75*/lista1/*244.81*/.get(0)),format.raw/*244.88*/("""_total2"></div></td>
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*245.75*/lista1/*245.81*/.get(0)),format.raw/*245.88*/("""_total3"></div></td>
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*246.75*/lista1/*246.81*/.get(0)),format.raw/*246.88*/("""_total4"></div></td>
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*247.75*/lista1/*247.81*/.get(0)),format.raw/*247.88*/("""_total5"></div></td>
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*248.75*/lista1/*248.81*/.get(0)),format.raw/*248.88*/("""_total6"></div></td>
												<td style="background-color: #9FF781"><div align="right" id=""""),_display_(/*249.75*/lista1/*249.81*/.get(0)),format.raw/*249.88*/("""_total7"></div></td>
											</tr>
										</tbody>
									</table>
								""")))} else {null} ),format.raw/*253.10*/("""
							""")))}),format.raw/*254.9*/("""
					
						"""),format.raw/*256.7*/("""</div>
					</td>
				</tr>
			</table>
			
		</div>
		
	""")))}),format.raw/*263.3*/("""
		
		
		
		"""),format.raw/*267.3*/("""<script type="text/javascript">
			  $(document).ready(function () """),format.raw/*268.36*/("""{"""),format.raw/*268.37*/("""
				
			"""),format.raw/*270.4*/("""let subtotal = 0;
			$(".subtotal").each(function() """),format.raw/*271.35*/("""{"""),format.raw/*271.36*/("""
				"""),format.raw/*272.5*/("""let val = $(this).text().replace(/,/g,'');
				subtotal += parseFloat(val);
			"""),format.raw/*274.4*/("""}"""),format.raw/*274.5*/(""");
			$("#subtotal").text(formatStandar(subtotal,0));
			
			let ajuste = 0;
			$(".ajuste").each(function() """),format.raw/*278.33*/("""{"""),format.raw/*278.34*/("""
				"""),format.raw/*279.5*/("""let val = $(this).text().replace(/,/g,'');
				ajuste += parseFloat(val);
			"""),format.raw/*281.4*/("""}"""),format.raw/*281.5*/(""");
			$("#ajuste").text(formatStandar(ajuste,0));
			
			let total = 0;
			$(".total").each(function() """),format.raw/*285.32*/("""{"""),format.raw/*285.33*/("""
				"""),format.raw/*286.5*/("""let val = $(this).text().replace(/,/g,'');
				total += parseFloat(val);
			"""),format.raw/*288.4*/("""}"""),format.raw/*288.5*/(""");
			$("#total").text(formatStandar(total,0));
			
			
			
			"""),_display_(/*293.5*/for(lista1 <- totalesPorProyecto) yield /*293.38*/{_display_(Seq[Any](format.raw/*293.39*/("""
				
				"""),format.raw/*295.5*/("""subtotal = 0;
				$("."""),_display_(/*296.10*/lista1/*296.16*/.get(0)),format.raw/*296.23*/("""_resumenTot1").each(function() """),format.raw/*296.54*/("""{"""),format.raw/*296.55*/("""
					"""),format.raw/*297.6*/("""let val = $(this).text().replace(/,/g,'');
					subtotal += parseFloat(val);
				"""),format.raw/*299.5*/("""}"""),format.raw/*299.6*/(""");
				$("#"""),_display_(/*300.10*/lista1/*300.16*/.get(0)),format.raw/*300.23*/("""_resumenTot1").text(formatStandar(subtotal,'2'));
				
				subtotal = 0;
				$("."""),_display_(/*303.10*/lista1/*303.16*/.get(0)),format.raw/*303.23*/("""_resumenTot2").each(function() """),format.raw/*303.54*/("""{"""),format.raw/*303.55*/("""
					"""),format.raw/*304.6*/("""let val = $(this).text().replace(/,/g,'');
					subtotal += parseFloat(val);
				"""),format.raw/*306.5*/("""}"""),format.raw/*306.6*/(""");
				$("#"""),_display_(/*307.10*/lista1/*307.16*/.get(0)),format.raw/*307.23*/("""_resumenTot2").text(formatStandar(subtotal,'"""),_display_(/*307.68*/cantDec),format.raw/*307.75*/("""'));
				
				subtotal = 0;
				$("."""),_display_(/*310.10*/lista1/*310.16*/.get(0)),format.raw/*310.23*/("""_total7").each(function() """),format.raw/*310.49*/("""{"""),format.raw/*310.50*/("""
					"""),format.raw/*311.6*/("""let val = $(this).text().replace(/,/g,'');
					subtotal += parseFloat(val);
				"""),format.raw/*313.5*/("""}"""),format.raw/*313.6*/(""");
				$("#"""),_display_(/*314.10*/lista1/*314.16*/.get(0)),format.raw/*314.23*/("""_total7").text(formatStandar(subtotal,'"""),_display_(/*314.63*/cantDec),format.raw/*314.70*/("""'));
				
				
				subtotal = 0;
				$("."""),_display_(/*318.10*/lista1/*318.16*/.get(0)),format.raw/*318.23*/("""_resumenTot21").each(function() """),format.raw/*318.55*/("""{"""),format.raw/*318.56*/("""
					"""),format.raw/*319.6*/("""let val = $(this).text().replace(/,/g,'');
					subtotal += parseFloat(val);
				"""),format.raw/*321.5*/("""}"""),format.raw/*321.6*/(""");
				$("#"""),_display_(/*322.10*/lista1/*322.16*/.get(0)),format.raw/*322.23*/("""_resumenTot21").text(formatStandar(subtotal,'2'));
				
				subtotal = 0;
				$("."""),_display_(/*325.10*/lista1/*325.16*/.get(0)),format.raw/*325.23*/("""_resumenTot22").each(function() """),format.raw/*325.55*/("""{"""),format.raw/*325.56*/("""
					"""),format.raw/*326.6*/("""let val = $(this).text().replace(/,/g,'');
					subtotal += parseFloat(val);
				"""),format.raw/*328.5*/("""}"""),format.raw/*328.6*/(""");
				$("#"""),_display_(/*329.10*/lista1/*329.16*/.get(0)),format.raw/*329.23*/("""_resumenTot22").text(formatStandar(subtotal,'"""),_display_(/*329.69*/cantDec),format.raw/*329.76*/("""'));
				
				subtotal = 0;
				$("."""),_display_(/*332.10*/lista1/*332.16*/.get(0)),format.raw/*332.23*/("""_resumenTot23").each(function() """),format.raw/*332.55*/("""{"""),format.raw/*332.56*/("""
					"""),format.raw/*333.6*/("""let val = $(this).text().replace(/,/g,'');
					subtotal += parseFloat(val);
				"""),format.raw/*335.5*/("""}"""),format.raw/*335.6*/(""");
				$("#"""),_display_(/*336.10*/lista1/*336.16*/.get(0)),format.raw/*336.23*/("""_resumenTot23").text(formatStandar(subtotal,'"""),_display_(/*336.69*/cantDec),format.raw/*336.76*/("""'));
				
				subtotal = 0;
				$("."""),_display_(/*339.10*/lista1/*339.16*/.get(0)),format.raw/*339.23*/("""_resumenTot24").each(function() """),format.raw/*339.55*/("""{"""),format.raw/*339.56*/("""
					"""),format.raw/*340.6*/("""let val = $(this).text().replace(/,/g,'');
					subtotal += parseFloat(val);
				"""),format.raw/*342.5*/("""}"""),format.raw/*342.6*/(""");
				$("#"""),_display_(/*343.10*/lista1/*343.16*/.get(0)),format.raw/*343.23*/("""_resumenTot24").text(formatStandar(subtotal,'"""),_display_(/*343.69*/cantDec),format.raw/*343.76*/("""'));
				
			""")))}),format.raw/*345.5*/("""
			"""),format.raw/*346.4*/("""/*
			
			
			
			
			
			
			/*
			subtotal = 0;
			$(".resumenTotal1").each(function() """),format.raw/*355.40*/("""{"""),format.raw/*355.41*/("""
				"""),format.raw/*356.5*/("""let val = $(this).text().replace(/,/g,'');
				subtotal += parseFloat(val);
			"""),format.raw/*358.4*/("""}"""),format.raw/*358.5*/(""");
			$("#resumenTotal1").text(formatStandar(subtotal,0));
			
			let ajuste = 0;
			$(".ajustes").each(function() """),format.raw/*362.34*/("""{"""),format.raw/*362.35*/("""
				"""),format.raw/*363.5*/("""let val = $(this).text().replace(/,/g,'');
				ajuste += parseFloat(val);
			"""),format.raw/*365.4*/("""}"""),format.raw/*365.5*/(""");
			$("#resumenTotal12").text(formatStandar(subtotal+ajuste,0));
			$("#totalTotal").val(subtotal+ajuste);
			
				*/
				
				
				
				
				
				
				
				

		 				
			        document.getElementById('mostrarmostrar').style.display="block"; 
			   """),format.raw/*381.7*/("""}"""),format.raw/*381.8*/(""");
		</script>
		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,desde:utilities.Fechas,hasta:utilities.Fechas,uf:Double,usd:Double,eur:Double,totalesPorProyecto:List[List[String]],mapAgrupado:Map[String, List[List[String]]],mapDetallado:Map[String, List[List[String]]],cantDec:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,desde,hasta,uf,usd,eur,totalesPorProyecto,mapAgrupado,mapDetallado,cantDec)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,utilities.Fechas,utilities.Fechas,Double,Double,Double,List[List[String]],Map[String, List[List[String]]],Map[String, List[List[String]]],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,desde,hasta,uf,usd,eur,totalesPorProyecto,mapAgrupado,mapDetallado,cantDec) => apply(mapDiccionario,mapPermiso,userMnu,desde,hasta,uf,usd,eur,totalesPorProyecto,mapAgrupado,mapDetallado,cantDec)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/reportOdoResumen.scala.html
                  HASH: 484506786c51e5ba6ee5474d490ab225f3561fd7
                  MATRIX: 1155->1|1583->336|1617->344|1725->425|1753->426|1794->440|1897->516|1926->517|1958->522|1997->535|2014->543|2054->545|2085->550|2154->598|2184->601|2240->631|2455->825|2491->834|3312->1628|3326->1633|3372->1658|3453->1712|3467->1717|3513->1742|3586->1788|3609->1790|3683->1837|3707->1840|3781->1887|3805->1890|4465->2523|4488->2537|4523->2551|4905->2906|4928->2920|4963->2933|5074->3017|5097->3031|5132->3044|5241->3126|5264->3140|5299->3153|5391->3218|5440->3251|5479->3252|5518->3263|5613->3331|5628->3337|5657->3345|5753->3414|5768->3420|5796->3427|5892->3496|5907->3502|5935->3509|6031->3578|6046->3584|6074->3591|6170->3660|6185->3666|6213->3673|6306->3739|6321->3745|6349->3752|6440->3816|6455->3822|6483->3829|6573->3892|6588->3898|6616->3905|6680->3938|6716->3947|7605->4809|7655->4842|7695->4843|7814->4934|7854->4935|7903->4955|8149->5173|8165->5179|8194->5186|8224->5187|10045->6980|10129->7047|10169->7048|10211->7061|10288->7110|10305->7117|10334->7124|10414->7176|10431->7183|10461->7191|10541->7243|10558->7250|10587->7257|10665->7307|10682->7314|10711->7321|10791->7373|10808->7380|10837->7387|10915->7437|10932->7444|10961->7451|11041->7503|11058->7510|11087->7517|11173->7575|11189->7581|11218->7588|11261->7603|11278->7610|11307->7617|11393->7675|11409->7681|11438->7688|11481->7703|11498->7710|11527->7717|11627->7789|11643->7795|11672->7802|11716->7818|11733->7825|11762->7832|11848->7890|11864->7896|11893->7903|11937->7919|11954->7926|11983->7933|12069->7991|12085->7997|12114->8004|12158->8020|12175->8027|12205->8035|12291->8093|12307->8099|12336->8106|12380->8122|12397->8129|12427->8137|12486->8164|12528->8177|12763->8384|12779->8390|12808->8397|12936->8497|12952->8503|12981->8510|13122->8623|13138->8629|13167->8636|13296->8737|13312->8743|13341->8750|13470->8851|13486->8857|13515->8864|13644->8965|13660->8971|13689->8978|16669->11930|16754->11998|16794->11999|16836->12012|16915->12063|16932->12070|16961->12077|17039->12127|17056->12134|17085->12141|17165->12193|17182->12200|17212->12208|17292->12260|17309->12267|17338->12274|17416->12324|17433->12331|17462->12338|17585->12433|17604->12442|17656->12472|17706->12494|17723->12501|17752->12508|17795->12522|17873->12572|17890->12579|17919->12586|17999->12638|18016->12645|18045->12652|18123->12702|18140->12709|18170->12717|18250->12769|18267->12776|18296->12783|18376->12835|18393->12842|18422->12849|18502->12901|18519->12908|18549->12916|18642->12981|18659->12988|18689->12996|18768->13047|18785->13054|18815->13062|18894->13113|18911->13120|18941->13128|19034->13193|19051->13200|19081->13208|19188->13287|19205->13294|19235->13302|19321->13360|19337->13366|19366->13373|19404->13383|19421->13390|19451->13398|19530->13449|19547->13456|19577->13464|19663->13522|19679->13528|19708->13535|19746->13545|19763->13552|19793->13560|19862->13597|19904->13610|20140->13818|20156->13824|20185->13831|20308->13926|20324->13932|20353->13939|20476->14034|20492->14040|20521->14047|20644->14142|20660->14148|20689->14155|20812->14250|20828->14256|20857->14263|20980->14358|20996->14364|21025->14371|21148->14466|21164->14472|21193->14479|21322->14563|21362->14572|21403->14585|21492->14643|21532->14655|21628->14722|21658->14723|21695->14732|21776->14784|21806->14785|21839->14790|21946->14869|21975->14870|22113->14979|22143->14980|22176->14985|22281->15062|22310->15063|22442->15166|22472->15167|22505->15172|22609->15248|22638->15249|22729->15313|22779->15346|22819->15347|22857->15357|22908->15380|22924->15386|22953->15393|23013->15424|23043->15425|23077->15431|23186->15512|23215->15513|23255->15525|23271->15531|23300->15538|23410->15620|23426->15626|23455->15633|23515->15664|23545->15665|23579->15671|23688->15752|23717->15753|23757->15765|23773->15771|23802->15778|23875->15823|23904->15830|23969->15867|23985->15873|24014->15880|24069->15906|24099->15907|24133->15913|24242->15994|24271->15995|24311->16007|24327->16013|24356->16020|24424->16060|24453->16067|24523->16109|24539->16115|24568->16122|24629->16154|24659->16155|24693->16161|24802->16242|24831->16243|24871->16255|24887->16261|24916->16268|25027->16351|25043->16357|25072->16364|25133->16396|25163->16397|25197->16403|25306->16484|25335->16485|25375->16497|25391->16503|25420->16510|25494->16556|25523->16563|25588->16600|25604->16606|25633->16613|25694->16645|25724->16646|25758->16652|25867->16733|25896->16734|25936->16746|25952->16752|25981->16759|26055->16805|26084->16812|26149->16849|26165->16855|26194->16862|26255->16894|26285->16895|26319->16901|26428->16982|26457->16983|26497->16995|26513->17001|26542->17008|26616->17054|26645->17061|26690->17075|26722->17079|26840->17168|26870->17169|26903->17174|27010->17253|27039->17254|27183->17369|27213->17370|27246->17375|27351->17452|27380->17453|27660->17705|27689->17706
                  LINES: 28->1|35->4|37->6|39->8|39->8|40->9|43->12|43->12|44->13|47->16|47->16|47->16|49->18|49->18|50->19|51->20|52->21|54->23|76->45|76->45|76->45|77->46|77->46|77->46|78->47|78->47|79->48|79->48|80->49|80->49|94->63|94->63|94->63|99->68|99->68|99->68|100->69|100->69|100->69|101->70|101->70|101->70|105->74|105->74|105->74|106->75|107->76|107->76|107->76|108->77|108->77|108->77|109->78|109->78|109->78|110->79|110->79|110->79|111->80|111->80|111->80|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|116->85|117->86|140->109|140->109|140->109|141->110|141->110|143->112|146->115|146->115|146->115|146->115|175->144|175->144|175->144|176->145|177->146|177->146|177->146|178->147|178->147|178->147|179->148|179->148|179->148|180->149|180->149|180->149|181->150|181->150|181->150|182->151|182->151|182->151|183->152|183->152|183->152|184->153|184->153|184->153|184->153|184->153|184->153|185->154|185->154|185->154|185->154|185->154|185->154|187->156|187->156|187->156|187->156|187->156|187->156|188->157|188->157|188->157|188->157|188->157|188->157|189->158|189->158|189->158|189->158|189->158|189->158|190->159|190->159|190->159|190->159|190->159|190->159|192->161|193->162|195->164|195->164|195->164|196->165|196->165|196->165|198->167|198->167|198->167|199->168|199->168|199->168|200->169|200->169|200->169|201->170|201->170|201->170|241->210|241->210|241->210|242->211|243->212|243->212|243->212|244->213|244->213|244->213|245->214|245->214|245->214|246->215|246->215|246->215|247->216|247->216|247->216|249->218|249->218|249->218|250->219|250->219|250->219|251->220|252->221|252->221|252->221|253->222|253->222|253->222|254->223|254->223|254->223|255->224|255->224|255->224|256->225|256->225|256->225|257->226|257->226|257->226|259->228|259->228|259->228|260->229|260->229|260->229|261->230|261->230|261->230|263->232|263->232|263->232|266->235|266->235|266->235|267->236|267->236|267->236|267->236|267->236|267->236|268->237|268->237|268->237|269->238|269->238|269->238|269->238|269->238|269->238|271->240|272->241|274->243|274->243|274->243|275->244|275->244|275->244|276->245|276->245|276->245|277->246|277->246|277->246|278->247|278->247|278->247|279->248|279->248|279->248|280->249|280->249|280->249|284->253|285->254|287->256|294->263|298->267|299->268|299->268|301->270|302->271|302->271|303->272|305->274|305->274|309->278|309->278|310->279|312->281|312->281|316->285|316->285|317->286|319->288|319->288|324->293|324->293|324->293|326->295|327->296|327->296|327->296|327->296|327->296|328->297|330->299|330->299|331->300|331->300|331->300|334->303|334->303|334->303|334->303|334->303|335->304|337->306|337->306|338->307|338->307|338->307|338->307|338->307|341->310|341->310|341->310|341->310|341->310|342->311|344->313|344->313|345->314|345->314|345->314|345->314|345->314|349->318|349->318|349->318|349->318|349->318|350->319|352->321|352->321|353->322|353->322|353->322|356->325|356->325|356->325|356->325|356->325|357->326|359->328|359->328|360->329|360->329|360->329|360->329|360->329|363->332|363->332|363->332|363->332|363->332|364->333|366->335|366->335|367->336|367->336|367->336|367->336|367->336|370->339|370->339|370->339|370->339|370->339|371->340|373->342|373->342|374->343|374->343|374->343|374->343|374->343|376->345|377->346|386->355|386->355|387->356|389->358|389->358|393->362|393->362|394->363|396->365|396->365|412->381|412->381
                  -- GENERATED --
              */
          