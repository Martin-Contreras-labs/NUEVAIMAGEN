
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

object movimientoListar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Guia],List[tables.Transportista],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaGuias: List[tables.Guia], listaTransporte: List[tables.Transportista], fechaDesde: String, fechaHasta: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalContactoBodega(mapDiccionario)),format.raw/*9.38*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "MOVIMIENTOS Y DESPACHOS EFECTUADOS","(SIN IMAGENES)")),format.raw/*12.86*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
			
			
			
				<div class="noprint">
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
				
				
				<form id="excel" class="formulario" method="post" action="/movimientoListarExcel/">
					<input type="hidden" name="fechaDesde" value=""""),_display_(/*41.53*/fechaDesde),format.raw/*41.63*/("""">
					<input type="hidden" name="fechaHasta" value=""""),_display_(/*42.53*/fechaHasta),format.raw/*42.63*/("""">
				</form>
		
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>TIPO</TH>
							<TH>COMERCIAL</TH>
							<TH>Nro<br>"""),_display_(/*50.20*/mapDiccionario/*50.34*/.get("OT")),format.raw/*50.44*/("""</TH>
							<TH>Nro<br>COTI</TH>
							<TH>Nro<br>MOV</TH>
							<TH>Nro<br>REF</TH>
							<TH>SELECT</TH>
							<TH style="min-width:80px;">FECHA<br>MOV</TH>
							<TH style="min-width:80px;">FECHA<br>INI/TER</TH>
							<TH>SUCURSAL<br>DESDE</TH>
							<TH>DESDE<br>"""),_display_(/*58.22*/mapDiccionario/*58.36*/.get("BODEGA")),format.raw/*58.50*/("""/PROYECTO</TH>
							<TH>SUCURSAL<br>HASTA</TH>
							<TH>HASTA<br>"""),_display_(/*60.22*/mapDiccionario/*60.36*/.get("BODEGA")),format.raw/*60.50*/("""/PROYECTO</TH>
							<TH>SELECT</TH>
							<TH>TOTAL<BR>KG</TH>
							"""),_display_(if( ! (mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) )/*63.121*/ {_display_(Seq[Any](format.raw/*63.123*/("""
								"""),format.raw/*64.9*/("""<TH>TOTAL<BR>M2</TH>
							""")))} else {null} ),format.raw/*65.9*/("""
							"""),format.raw/*66.8*/("""<TH>GUIA<br>PDF</TH>
							"""),_display_(if(!mapPermiso.get("parametro.movimientoListar-descargaXLSX").equals("1"))/*67.83*/{_display_(Seq[Any](format.raw/*67.84*/("""
								"""),format.raw/*68.9*/("""<TH>GUIA<br>EXCEL</TH>
							""")))} else {null} ),format.raw/*69.9*/("""
							"""),format.raw/*70.8*/("""<TH>ANEXO</TH>
							<TH>ALBUM</TH>
							"""),_display_(if(mapPermiso.get("parametro.movimientoListar-descargaXLSX").equals("1"))/*72.82*/{_display_(Seq[Any](format.raw/*72.83*/("""
								"""),format.raw/*73.9*/("""<TH>EXCEL</TH>
							""")))} else {null} ),format.raw/*74.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.movimientoListar-descargarXLM").equals("1"))/*75.82*/{_display_(Seq[Any](format.raw/*75.83*/("""
								"""),format.raw/*76.9*/("""<TH>XML</TH>
							""")))} else {null} ),format.raw/*77.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarApiManager").equals("1") && mapPermiso.get("enviarApiGuia")!=null)/*78.127*/{_display_(Seq[Any](format.raw/*78.128*/("""
								"""),format.raw/*79.9*/("""<TH>API<br>Manager</TH>
							""")))} else {null} ),format.raw/*80.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarApiNubox").equals("1") && mapPermiso.get("enviarApiGuia")!=null)/*81.125*/{_display_(Seq[Any](format.raw/*81.126*/("""
								"""),format.raw/*82.9*/("""<TH>API<br>Nubox</TH>
							""")))} else {null} ),format.raw/*83.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarWebFacturacion").equals("1") && mapPermiso.get("enviarApiGuia")!=null)/*84.131*/{_display_(Seq[Any](format.raw/*84.132*/("""
								"""),format.raw/*85.9*/("""<TH>WEB<br>Facturacion</TH>
								<TH>DTE</TH>
							""")))} else {null} ),format.raw/*87.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarWebMaximise")!=null && mapPermiso.get("parametro.movimientoListar-llenarWebMaximise").equals("1") && mapPermiso.get("enviarApiGuia")!=null)/*88.200*/{_display_(Seq[Any](format.raw/*88.201*/("""
								"""),format.raw/*89.9*/("""<TH>WEB<br>Maximise</TH>
								<TH>Nro.Int</TH>
							""")))} else {null} ),format.raw/*91.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarWebIConstruye")!=null && mapPermiso.get("parametro.movimientoListar-llenarWebIConstruye").equals("1"))/*92.163*/{_display_(Seq[Any](format.raw/*92.164*/("""
								"""),format.raw/*93.9*/("""<TH>ICONSTRUYE<br>GUIAS</TH>
							""")))} else {null} ),format.raw/*94.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarApiRelBase")!=null && mapPermiso.get("parametro.movimientoListar-llenarApiRelBase").equals("1") && mapPermiso.get("enviarApiGuia")!=null)/*95.198*/{_display_(Seq[Any](format.raw/*95.199*/("""
								"""),format.raw/*96.9*/("""<TH>API<br>RELBASE</TH>
							""")))} else {null} ),format.raw/*97.9*/("""
							"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarApiSapSchwager")!=null && mapPermiso.get("parametro.movimientoListar-llenarApiSapSchwager").equals("1") && mapPermiso.get("enviarApiGuia")!=null)/*98.206*/{_display_(Seq[Any](format.raw/*98.207*/("""
								"""),format.raw/*99.9*/("""<TH>API<br>SAP</TH>
								<TH>Folio</TH>
							""")))} else {null} ),format.raw/*101.9*/("""
						"""),format.raw/*102.7*/("""</TR>
					</thead>
					<tbody>
						"""),_display_(/*105.8*/for(lista1 <- listaGuias) yield /*105.33*/{_display_(Seq[Any](format.raw/*105.34*/("""
							"""),format.raw/*106.8*/("""<TR>
								<td  style="text-align:center;">"""),_display_(/*107.42*/lista1/*107.48*/.getTipoGuia()),format.raw/*107.62*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*108.42*/lista1/*108.48*/.getNameComercial()),format.raw/*108.67*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroCotizacion()!=0)/*110.46*/{_display_(Seq[Any](format.raw/*110.47*/("""
										"""),format.raw/*111.11*/("""<a href="#" onclick="verOt('"""),_display_(/*111.40*/lista1/*111.46*/.getId_ot()),format.raw/*111.57*/("""')">
											<kbd style="background-color: #73C6B6">"""),_display_(/*112.52*/lista1/*112.58*/.getNumeroOt()),format.raw/*112.72*/("""</kbd>
										</a>
									""")))}else/*114.15*/{_display_(Seq[Any](format.raw/*114.16*/("""
										"""),format.raw/*115.11*/("""--
									""")))}),format.raw/*116.11*/("""
								"""),format.raw/*117.9*/("""</td>
								<td  style="text-align:center;">
									"""),_display_(if(lista1.getNumeroCotizacion()!=0)/*119.46*/{_display_(Seq[Any](format.raw/*119.47*/("""
										"""),_display_(/*120.12*/lista1/*120.18*/.getNumeroCotizacion()),format.raw/*120.40*/("""
									""")))}else/*121.15*/{_display_(Seq[Any](format.raw/*121.16*/("""
										"""),format.raw/*122.11*/("""--
									""")))}),format.raw/*123.11*/("""
								"""),format.raw/*124.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*125.42*/lista1/*125.48*/.getNumero()),format.raw/*125.60*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*126.42*/lista1/*126.48*/.getNumGuiaCliente()),format.raw/*126.68*/("""</td>
								<td style="text-align:center;">
									<a href="/movimientoDetalleGuia/"""),_display_(/*128.43*/lista1/*128.49*/.getId()),format.raw/*128.57*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*133.37*/lista1/*133.43*/.getFecha()),format.raw/*133.54*/("""</div>
									"""),_display_(/*134.11*/utilities/*134.20*/.Fechas.DDMMAA(lista1.getFecha())),format.raw/*134.53*/("""
								"""),format.raw/*135.9*/("""</td>
								<td  style="text-align:center; min-width:80px;">
									<div style="display:none">"""),_display_(/*137.37*/lista1/*137.43*/.getFechaIniTerGuia()),format.raw/*137.64*/("""</div>
									"""),_display_(/*138.11*/utilities/*138.20*/.Fechas.DDMMAA(lista1.getFechaIniTerGuia())),format.raw/*138.63*/("""
								"""),format.raw/*139.9*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*140.40*/lista1/*140.46*/.getNameSucursalOrigen()),format.raw/*140.70*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*141.73*/lista1/*141.79*/.getBodegaOrigen()),format.raw/*141.97*/("""')">"""),_display_(/*141.102*/lista1/*141.108*/.getBodegaOrigen()),format.raw/*141.126*/("""</a></td>
								<td  style="text-align:left;">"""),_display_(/*142.40*/lista1/*142.46*/.getNameSucursalDestino()),format.raw/*142.71*/("""</td>
								<td style="text-align:left;"><a href="#" onclick="buscaBodega('"""),_display_(/*143.73*/lista1/*143.79*/.getBodegaDestino()),format.raw/*143.98*/("""')">"""),_display_(/*143.103*/lista1/*143.109*/.getBodegaDestino()),format.raw/*143.128*/("""</a></td>
								
								<td style="text-align:center;">
									<a href="/movimientoDetalleGuia/"""),_display_(/*146.43*/lista1/*146.49*/.getId()),format.raw/*146.57*/("""">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</td>
								<td style="text-align:right;">
									"""),_display_(/*151.11*/lista1/*151.17*/.getTotalKg()),format.raw/*151.30*/("""
								"""),format.raw/*152.9*/("""</td>
								"""),_display_(if( ! (mapPermiso.get("parametro.escondeLosM2")!=null && mapPermiso.get("parametro.escondeLosM2").equals("1")) )/*153.122*/ {_display_(Seq[Any](format.raw/*153.124*/("""
									"""),format.raw/*154.10*/("""<td style="text-align:right;">
										"""),_display_(/*155.12*/lista1/*155.18*/.getTotalM2()),format.raw/*155.31*/("""
									"""),format.raw/*156.10*/("""</td>
								""")))} else {null} ),format.raw/*157.10*/("""
								"""),format.raw/*158.9*/("""<td  style="text-align:center;">
									"""),_display_(if(lista1.getGuiaPDF().equals("0"))/*159.46*/{_display_(Seq[Any](format.raw/*159.47*/("""
										"""),format.raw/*160.11*/("""--
									""")))}else/*161.15*/{_display_(Seq[Any](format.raw/*161.16*/("""
										"""),format.raw/*162.11*/("""<a href="#" onclick="mostrarPDFmasAlbum('"""),_display_(/*162.53*/lista1/*162.59*/.getId()),format.raw/*162.67*/("""','"""),_display_(/*162.71*/lista1/*162.77*/.getGuiaPDF()),format.raw/*162.90*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*165.11*/("""
								"""),format.raw/*166.9*/("""</td>
								
								"""),_display_(if(!mapPermiso.get("parametro.movimientoListar-descargaXLSX").equals("1"))/*168.84*/{_display_(Seq[Any](format.raw/*168.85*/("""
									"""),format.raw/*169.10*/("""<td style="text-align:center;">
										<a href="#" onclick="descargaEnExcel('"""),_display_(/*170.50*/lista1/*170.56*/.getId()),format.raw/*170.64*/("""')">
											<kbd style="background-color: #85C1E9">xls</kbd>
										</a>
									</td>
								""")))} else {null} ),format.raw/*174.10*/("""
								
								"""),format.raw/*176.9*/("""<td style="text-align:center;">
									"""),_display_(if(lista1.getDocAnexo().equals("0"))/*177.47*/{_display_(Seq[Any](format.raw/*177.48*/("""
										"""),format.raw/*178.11*/("""--
									""")))}else/*179.15*/{_display_(Seq[Any](format.raw/*179.16*/("""
										"""),format.raw/*180.11*/("""<a href="#" onclick="descargaDocumento('"""),_display_(/*180.52*/lista1/*180.58*/.getDocAnexo()),format.raw/*180.72*/("""')">
											<kbd style="background-color: #7F8C8D">doc</kbd>
										</a>
									""")))}),format.raw/*183.11*/("""
								"""),format.raw/*184.9*/("""</td>
								
								<td style="text-align:center;">
									"""),_display_(if(lista1.getFotos().equals("0"))/*187.44*/{_display_(Seq[Any](format.raw/*187.45*/("""
										"""),format.raw/*188.11*/("""--
									""")))}else/*189.15*/{_display_(Seq[Any](format.raw/*189.16*/("""
										"""),format.raw/*190.11*/("""<a href="/muestraAlbumFotos/"""),_display_(/*190.40*/lista1/*190.46*/.getFotos()),format.raw/*190.57*/("""">
											<kbd style="background-color: #7F8C8D">Ver</kbd>
										</a>
									""")))}),format.raw/*193.11*/("""
								"""),format.raw/*194.9*/("""</td>
						
								"""),_display_(if(mapPermiso.get("parametro.movimientoListar-descargaXLSX").equals("1"))/*196.83*/{_display_(Seq[Any](format.raw/*196.84*/("""
									"""),format.raw/*197.10*/("""<td style="text-align:center;">
										<a href="#" onclick="descargaXLSX('"""),_display_(/*198.47*/lista1/*198.53*/.getId()),format.raw/*198.61*/("""','"""),_display_(/*198.65*/lista1/*198.71*/.getId_transportista()),format.raw/*198.93*/("""')">
											<kbd style="background-color: #C39BD3">xlsx</kbd>
										</a>
									</td>
								""")))} else {null} ),format.raw/*202.10*/("""
						
							
								"""),_display_(if(mapPermiso.get("parametro.movimientoListar-descargarXLM").equals("1"))/*205.83*/{_display_(Seq[Any](format.raw/*205.84*/("""
									"""),format.raw/*206.10*/("""<td style="text-align:center;">
										"""),_display_(if(lista1.getTipoGuia().equals(mapDiccionario.get("Despacho")))/*207.75*/{_display_(Seq[Any](format.raw/*207.76*/("""
											"""),format.raw/*208.12*/("""<a href="#" onclick="descargarXML('"""),_display_(/*208.48*/lista1/*208.54*/.getId()),format.raw/*208.62*/("""','"""),_display_(/*208.66*/lista1/*208.72*/.getId_transportista()),format.raw/*208.94*/("""')">
												<kbd style="background-color: #C39BD3">enviar</kbd>
											</a>
										""")))}else/*211.16*/{_display_(Seq[Any](format.raw/*211.17*/("""
											"""),format.raw/*212.12*/("""--
										""")))}),format.raw/*213.12*/("""
									"""),format.raw/*214.10*/("""</td>
								""")))} else {null} ),format.raw/*215.10*/("""
								"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarApiManager").equals("1") && mapPermiso.get("enviarApiGuia")!=null)/*216.128*/{_display_(Seq[Any](format.raw/*216.129*/("""
									"""),format.raw/*217.10*/("""<td style="text-align:center;">
										"""),_display_(if(lista1.getTipoGuia().equals(mapDiccionario.get("Despacho")))/*218.75*/{_display_(Seq[Any](format.raw/*218.76*/("""
											"""),format.raw/*219.12*/("""<a href="#" onclick= "llenarApiManager('"""),_display_(/*219.53*/lista1/*219.59*/.getId()),format.raw/*219.67*/("""','"""),_display_(/*219.71*/lista1/*219.77*/.getId_transportista()),format.raw/*219.99*/("""')">
												<kbd style="background-color: #C39BD3">enviar</kbd>
											</a>
										""")))}else/*222.16*/{_display_(Seq[Any](format.raw/*222.17*/("""
											"""),format.raw/*223.12*/("""--
										""")))}),format.raw/*224.12*/("""
									"""),format.raw/*225.10*/("""</td>
								""")))} else {null} ),format.raw/*226.10*/("""
								"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarApiNubox").equals("1") && mapPermiso.get("enviarApiGuia")!=null)/*227.126*/{_display_(Seq[Any](format.raw/*227.127*/("""
									"""),format.raw/*228.10*/("""<td style="text-align:center;">
										"""),_display_(if(lista1.getTipoGuia().equals(mapDiccionario.get("Despacho")))/*229.75*/{_display_(Seq[Any](format.raw/*229.76*/("""

											"""),_display_(if(lista1.getLinkFolio.equals("0"))/*231.48*/{_display_(Seq[Any](format.raw/*231.49*/("""
												"""),format.raw/*232.13*/("""<a href="#" onclick= "llenarApiNubox('"""),_display_(/*232.52*/lista1/*232.58*/.getId()),format.raw/*232.66*/("""','"""),_display_(/*232.70*/lista1/*232.76*/.getId_transportista()),format.raw/*232.98*/("""')">
													<kbd style="background-color: #C39BD3">enviar</kbd>
												</a>
											""")))}else/*235.17*/{_display_(Seq[Any](format.raw/*235.18*/("""
												"""),format.raw/*236.13*/("""<a href="#" onclick= "llenarApiNubox('"""),_display_(/*236.52*/lista1/*236.58*/.getId()),format.raw/*236.66*/("""','"""),_display_(/*236.70*/lista1/*236.76*/.getId_transportista()),format.raw/*236.98*/("""')">
													<kbd style="background-color: #85C1E9">reenviar</kbd>
												</a>
											""")))}),format.raw/*239.13*/("""
										""")))}else/*240.16*/{_display_(Seq[Any](format.raw/*240.17*/("""
											"""),format.raw/*241.12*/("""--
										""")))}),format.raw/*242.12*/("""
									"""),format.raw/*243.10*/("""</td>
								""")))} else {null} ),format.raw/*244.10*/("""
								"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarWebFacturacion").equals("1") && mapPermiso.get("enviarApiGuia")!=null)/*245.132*/{_display_(Seq[Any](format.raw/*245.133*/("""
									"""),format.raw/*246.10*/("""<td style="text-align:center;">
										"""),_display_(if(lista1.getTipoGuia().equals(mapDiccionario.get("Despacho")))/*247.75*/{_display_(Seq[Any](format.raw/*247.76*/("""
											"""),_display_(if(lista1.getGuiaXml().equals("0"))/*248.48*/{_display_(Seq[Any](format.raw/*248.49*/("""
												"""),format.raw/*249.13*/("""<a href="#" onclick= "llenarWebFacturacion('"""),_display_(/*249.58*/lista1/*249.64*/.getId()),format.raw/*249.72*/("""','"""),_display_(/*249.76*/lista1/*249.82*/.getId_transportista()),format.raw/*249.104*/("""')">
													<kbd style="background-color: #C39BD3">enviar</kbd>
												</a>
											""")))}else/*252.17*/{_display_(Seq[Any](format.raw/*252.18*/("""
												"""),format.raw/*253.13*/("""<a href="#" onclick= "llenarWebFacturacion('"""),_display_(/*253.58*/lista1/*253.64*/.getId()),format.raw/*253.72*/("""','"""),_display_(/*253.76*/lista1/*253.82*/.getId_transportista()),format.raw/*253.104*/("""')">
													<kbd style="background-color: #85C1E9">reenviar</kbd>
												</a>
											""")))}),format.raw/*256.13*/("""
											
										""")))}else/*258.16*/{_display_(Seq[Any](format.raw/*258.17*/("""
											"""),format.raw/*259.12*/("""--
										""")))}),format.raw/*260.12*/("""
									"""),format.raw/*261.10*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.getTipoGuia().equals(mapDiccionario.get("Despacho")))/*263.75*/{_display_(Seq[Any](format.raw/*263.76*/("""
											"""),_display_(if(!lista1.getLinkFolio().equals("0"))/*264.51*/{_display_(Seq[Any](format.raw/*264.52*/("""
												"""),format.raw/*265.13*/("""<a href=""""),_display_(/*265.23*/lista1/*265.29*/.getLinkFolio()),format.raw/*265.44*/("""" >
													<kbd style="background-color: rgb(50, 215, 75)">DTE</kbd>
												</a>
											""")))}else/*268.17*/{_display_(Seq[Any](format.raw/*268.18*/("""
												"""),format.raw/*269.13*/("""--
											""")))}),format.raw/*270.13*/("""
										""")))}else/*271.16*/{_display_(Seq[Any](format.raw/*271.17*/("""
											"""),format.raw/*272.12*/("""--
										""")))}),format.raw/*273.12*/("""
									"""),format.raw/*274.10*/("""</td>
								""")))} else {null} ),format.raw/*275.10*/("""
								
								"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarWebMaximise")!=null && mapPermiso.get("parametro.movimientoListar-llenarWebMaximise").equals("1") && mapPermiso.get("enviarApiGuia")!=null)/*277.201*/{_display_(Seq[Any](format.raw/*277.202*/("""
									"""),format.raw/*278.10*/("""<td style="text-align:center;">
										"""),_display_(if(lista1.getTipoGuia().equals(mapDiccionario.get("Despacho")))/*279.75*/{_display_(Seq[Any](format.raw/*279.76*/("""
											"""),_display_(if(lista1.getGuiaXml().equals("0"))/*280.48*/{_display_(Seq[Any](format.raw/*280.49*/("""
												"""),format.raw/*281.13*/("""<a href="#" onclick= "llenarWebMaximise('"""),_display_(/*281.55*/lista1/*281.61*/.getId()),format.raw/*281.69*/("""','"""),_display_(/*281.73*/lista1/*281.79*/.getId_transportista()),format.raw/*281.101*/("""')">
													<kbd style="background-color: #C39BD3">enviar</kbd>
												</a>
											""")))}else/*284.17*/{_display_(Seq[Any](format.raw/*284.18*/("""
												"""),format.raw/*285.13*/("""<a href="#" onclick= "llenarWebMaximise('"""),_display_(/*285.55*/lista1/*285.61*/.getId()),format.raw/*285.69*/("""','"""),_display_(/*285.73*/lista1/*285.79*/.getId_transportista()),format.raw/*285.101*/("""')">
													<kbd style="background-color: #85C1E9">reenviar</kbd>
												</a>
											""")))}),format.raw/*288.13*/("""
											
										""")))}else/*290.16*/{_display_(Seq[Any](format.raw/*290.17*/("""
											"""),format.raw/*291.12*/("""--
										""")))}),format.raw/*292.12*/("""
									"""),format.raw/*293.10*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(lista1.getTipoGuia().equals(mapDiccionario.get("Despacho")))/*295.75*/{_display_(Seq[Any](format.raw/*295.76*/("""
											"""),_display_(if(!lista1.getLinkFolio().equals("0"))/*296.51*/{_display_(Seq[Any](format.raw/*296.52*/("""
												"""),format.raw/*297.13*/("""<a href="/downGuiaMaximiseAjax/"""),_display_(/*297.45*/lista1/*297.51*/.getLinkFolio()),format.raw/*297.66*/("""" > 
												<kbd style="background-color: rgb(50, 215, 75)">"""),_display_(/*298.62*/lista1/*298.68*/.getLinkFolio()),format.raw/*298.83*/("""</kbd>
											</a> 
											""")))}else/*300.17*/{_display_(Seq[Any](format.raw/*300.18*/("""
												"""),format.raw/*301.13*/("""--
											""")))}),format.raw/*302.13*/("""
										""")))}else/*303.16*/{_display_(Seq[Any](format.raw/*303.17*/("""
											"""),format.raw/*304.12*/("""--
										""")))}),format.raw/*305.12*/("""
									"""),format.raw/*306.10*/("""</td>
								""")))} else {null} ),format.raw/*307.10*/("""
								
								"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarWebIConstruye")!=null && mapPermiso.get("parametro.movimientoListar-llenarWebIConstruye").equals("1"))/*309.164*/{_display_(Seq[Any](format.raw/*309.165*/("""
									"""),format.raw/*310.10*/("""<td style="text-align:center;">
											"""),_display_(if(lista1.getLinkFolio().equals("0"))/*311.50*/{_display_(Seq[Any](format.raw/*311.51*/("""
												"""),format.raw/*312.13*/("""<a href="#" onclick="llenarWebIConstruye('"""),_display_(/*312.56*/lista1/*312.62*/.getId()),format.raw/*312.70*/("""','"""),_display_(/*312.74*/lista1/*312.80*/.getId_transportista()),format.raw/*312.102*/("""')">
													<kbd style="background-color: #C39BD3">IC</kbd>
												</a>
											""")))}else/*315.17*/{_display_(Seq[Any](format.raw/*315.18*/("""
												"""),format.raw/*316.13*/("""<a href="#" onclick="downGuiaIconstruye('"""),_display_(/*316.55*/lista1/*316.61*/.getId()),format.raw/*316.69*/("""')">
													<kbd style="background-color: rgb(0, 143, 81)">GUIA: """),_display_(/*317.68*/lista1/*317.74*/.getLinkFolio()),format.raw/*317.89*/("""</kbd>
												</a>
											""")))}),format.raw/*319.13*/("""
									"""),format.raw/*320.10*/("""</td>
								""")))} else {null} ),format.raw/*321.10*/("""
								
								"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarApiRelBase")!=null && mapPermiso.get("parametro.movimientoListar-llenarApiRelBase").equals("1") && mapPermiso.get("enviarApiGuia")!=null)/*323.199*/{_display_(Seq[Any](format.raw/*323.200*/("""
									"""),format.raw/*324.10*/("""<td style="text-align:center;">
										"""),_display_(if(lista1.getLinkFolio().equals("0"))/*325.49*/{_display_(Seq[Any](format.raw/*325.50*/("""
											"""),format.raw/*326.12*/("""<a href="#" onclick= "llenarApiRelBase('"""),_display_(/*326.53*/lista1/*326.59*/.getId()),format.raw/*326.67*/("""','"""),_display_(/*326.71*/lista1/*326.77*/.getId_transportista()),format.raw/*326.99*/("""')">
												<kbd style="background-color: #C39BD3">enviar</kbd>
											</a>
										""")))}else/*329.16*/{_display_(Seq[Any](format.raw/*329.17*/("""
											"""),_display_(if(!lista1.getLinkFolio().equals("0"))/*330.51*/{_display_(Seq[Any](format.raw/*330.52*/("""
												"""),format.raw/*331.13*/("""<div id="pdfRelBase_"""),_display_(/*331.34*/lista1/*331.40*/.getLinkFolio()),format.raw/*331.55*/("""">
													<a href="#" onclick="pdfReBase('"""),_display_(/*332.47*/lista1/*332.53*/.getLinkFolio()),format.raw/*332.68*/("""')">
														<kbd style="background-color: rgb(50, 215, 75)">Guia: """),_display_(/*333.70*/lista1/*333.76*/.getLinkFolio()),format.raw/*333.91*/("""</kbd>
													</a>
												</div>
											""")))}else/*336.17*/{_display_(Seq[Any](format.raw/*336.18*/("""
												"""),format.raw/*337.13*/("""--
											""")))}),format.raw/*338.13*/("""
										""")))}),format.raw/*339.12*/("""
									"""),format.raw/*340.10*/("""</td>
								""")))} else {null} ),format.raw/*341.10*/("""
								
								"""),_display_(if(mapPermiso.get("parametro.movimientoListar-llenarApiSapSchwager")!=null && mapPermiso.get("parametro.movimientoListar-llenarApiSapSchwager").equals("1") && mapPermiso.get("enviarApiGuia")!=null)/*343.207*/{_display_(Seq[Any](format.raw/*343.208*/("""
									"""),format.raw/*344.10*/("""<td style="text-align:center;">
										"""),_display_(if(lista1.getLinkFolio().equals("0"))/*345.49*/{_display_(Seq[Any](format.raw/*345.50*/("""
											"""),format.raw/*346.12*/("""<a href="#" onclick= "llenarApiSapSchwager('"""),_display_(/*346.57*/lista1/*346.63*/.getId()),format.raw/*346.71*/("""','"""),_display_(/*346.75*/lista1/*346.81*/.getId_transportista()),format.raw/*346.103*/("""')">
												<kbd style="background-color: #C39BD3">enviar</kbd>
											</a>
										""")))}else/*349.16*/{_display_(Seq[Any](format.raw/*349.17*/("""
											"""),format.raw/*350.12*/("""<a href="#" onclick= "llenarApiSapSchwager('"""),_display_(/*350.57*/lista1/*350.63*/.getId()),format.raw/*350.71*/("""','"""),_display_(/*350.75*/lista1/*350.81*/.getId_transportista()),format.raw/*350.103*/("""')">
												<kbd style="background-color: #85C1E9">reenviar</kbd>
											</a>
										""")))}),format.raw/*353.12*/("""
									"""),format.raw/*354.10*/("""</td>
									<td style="text-align:center;">
										"""),_display_(if(!lista1.getLinkFolio().equals("0"))/*356.50*/{_display_(Seq[Any](format.raw/*356.51*/("""
											"""),_display_(/*357.13*/lista1/*357.19*/.getLinkFolio()),format.raw/*357.34*/("""
										""")))}else/*358.16*/{_display_(Seq[Any](format.raw/*358.17*/("""
											"""),format.raw/*359.12*/("""--
										""")))}),format.raw/*360.12*/("""
									"""),format.raw/*361.10*/("""</td>
								""")))} else {null} ),format.raw/*362.10*/("""
							"""),format.raw/*363.8*/("""</TR>
			 			""")))}),format.raw/*364.9*/("""
					"""),format.raw/*365.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalVerOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class="modal-title">"""),_display_(/*382.31*/mapDiccionario/*382.45*/.get("ORDEN_DE_TRABAJO")),format.raw/*382.69*/("""</h5>
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
					<h5 class='modal-title' id="titMuestraPdf">MOVIMIENTO</h5>
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
	
	<div id='selectSucursal' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-sm' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>Seleccione Sucursal</h5>
				</div>
				<div class='modal-body'>
					<a href="" onclick="selectSucursal('1');" data-dismiss='modal'>1 SANTIAGO</a>
					<br>
					<a href="" onclick="selectSucursal('2');" data-dismiss='modal'>2 ANTOFAGASTA</a>
				</div>
			</div>
		</div>
	</div>
	
	<form class="formulario" id="formDescargaDocumento" method="post" action="/downloadPDF/">	
		<input type="hidden" id="descargaDocumento" name="nombreArchivo">
	</form>
	
	<form class="formulario" id="formDescargaXLSX" method="post" action="/generaGuiaXLS/">	
		<input type="hidden" id="id_guiaXLSX" name="id_guia">
		<input type="hidden" id="id_transportistaXLSX" name="id_transportista"  value="0">
	</form>
	
	<form class="formulario" id="formDescargaExcel" method="post" action="/generaGuiaExcel/">	
		<input type="hidden" id="id_guiaExcel" name="id_guia">
	</form>
	
	<form class="formulario" id="formDescargaXML" method="post" action="/generaGuiaXML/">	
		<input type="hidden" id="id_guiaXML" name="id_guia">
		<input type="hidden" id="id_transportistaXML" name="id_transportista" value="0">
	</form>
	
	<form class="formulario" id="formEnviaApiManager" method="post" action="/generaGuiaApiManager/">	
		<input type="hidden" id="id_guiaManager" name="id_guia">
		<input type="hidden" id="id_transportistaManager" name="id_transportista" value="0">
	</form>
	
	<form class="formulario" id="formEnviaApiNubox" method="post" action="/generaGuiaApiNubox/">	
		<input type="hidden" id="id_guiaNubox" name="id_guia">
		<input type="hidden" id="id_transportistaNubox" name="id_transportista" value="0">
	</form>
	
	<form class="formulario" id="formEnviaWebFacturacion" method="post" action="/generaGuiaWebFacturacion/">	
		<input type="hidden" id="id_guiaFacturacion" name="id_guia">
		<input type="hidden" id="id_transportistaFacturacion" name="id_transportista" value="0">
	</form>
	
	<form class="formulario" id="formEnviaWebMaximise" method="post" action="/generaGuiaWebMaximise/">	
		<input type="hidden" id="id_guiaMaximise" name="id_guia">
		<input type="hidden" id="id_transportistaMaximise" name="id_transportista" value="0">
		<input type="hidden" id="sucursalMaximise" name="sucurMaximise" value="0">
	</form>
	
	<form class="formulario" id="formEnviaWebIConstruye" method="post" action="/generaGuiaWebIConstruye/">	
		<input type="hidden" id="id_guiaIConstruye" name="id_guia">
		<input type="hidden" id="id_transportistaIConstruye" name="id_transportista" value="0">
	</form>
	
	<form class="formulario" id="downGuiaIconstruye" method="post" action="/downGuiaIconstruye/">	
		<input type="hidden" id="id_guiaIConstruye2" name="id_guia">
	</form>
	
	<form class="formulario" id="formEnviaApiRelBase" method="post" action="/generaGuiaApiRelBase/">	
		<input type="hidden" id="id_guiaRelBase" name="id_guia">
		<input type="hidden" id="id_transportistaRelBase" name="id_transportista" value="0">
		<input type="hidden" id="id_cotizaSolucionRelBase" name="id_cotizaSolucion" value="0">
		<input type="hidden" id="descriptionRelBase" name="description" value=" ">
		<input type="hidden" id="observacionesRelBase" name="observaciones" value=" ">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*510.50*/fechaDesde),format.raw/*510.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*511.50*/fechaHasta),format.raw/*511.60*/("""">
	</form>
	
	<form class="formulario" id="formEnviaSapSchwager" method="post" action="/generaGuiaSapSchwager/">	
		<input type="hidden" id="id_guiaSapSchwager" name="id_guia">
		<input type="hidden" id="id_transportistaSapSchwager" name="id_transportista" value="0">
		<input type="hidden" name="fechaDesde" value=""""),_display_(/*517.50*/fechaDesde),format.raw/*517.60*/("""">
		<input type="hidden" name="fechaHasta" value=""""),_display_(/*518.50*/fechaHasta),format.raw/*518.60*/("""">
	</form>
	
	"""),_display_(/*521.3*/modalListaTransportistas(mapDiccionario, mapPermiso, listaTransporte)),format.raw/*521.72*/(""";

""")))}),format.raw/*523.2*/("""


"""),format.raw/*526.1*/("""<script type="text/javascript">

	let auxId_transportista = 0;
	
	const transportistaSeleccionado = () => """),format.raw/*530.42*/("""{"""),format.raw/*530.43*/("""
		"""),format.raw/*531.3*/("""var tableReg = document.getElementById("tablaListaTransporte");
		for (var i = 1; i < tableReg.rows.length; i++)"""),format.raw/*532.49*/("""{"""),format.raw/*532.50*/("""
			"""),format.raw/*533.4*/("""var cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
			let idTransporte = cellsOfRow[0].innerHTML;
			if(idTransporte == auxId_transportista)"""),format.raw/*535.43*/("""{"""),format.raw/*535.44*/("""
				"""),format.raw/*536.5*/("""tableReg.rows[i].style.backgroundColor = "yellow";
				
			"""),format.raw/*538.4*/("""}"""),format.raw/*538.5*/("""else"""),format.raw/*538.9*/("""{"""),format.raw/*538.10*/("""
				"""),format.raw/*539.5*/("""tableReg.rows[i].style.backgroundColor = "";
			"""),format.raw/*540.4*/("""}"""),format.raw/*540.5*/("""
		"""),format.raw/*541.3*/("""}"""),format.raw/*541.4*/("""
	"""),format.raw/*542.2*/("""}"""),format.raw/*542.3*/("""

	"""),format.raw/*544.2*/("""$(document).ready(function() """),format.raw/*544.31*/("""{"""),format.raw/*544.32*/("""
		 """),format.raw/*545.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*545.35*/("""{"""),format.raw/*545.36*/("""
		    	"""),format.raw/*546.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 4, "desc" ]],
		    	"language": """),format.raw/*549.20*/("""{"""),format.raw/*549.21*/("""
		        	"""),format.raw/*550.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*551.11*/("""}"""),format.raw/*551.12*/("""
		  """),format.raw/*552.5*/("""}"""),format.raw/*552.6*/(""" """),format.raw/*552.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*554.2*/("""}"""),format.raw/*554.3*/(""");
	
	const verOt = (id_ot) => """),format.raw/*556.27*/("""{"""),format.raw/*556.28*/("""
		"""),format.raw/*557.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
        $.ajax("""),format.raw/*559.16*/("""{"""),format.raw/*559.17*/("""
            """),format.raw/*560.13*/("""url: "/verOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*567.36*/("""{"""),format.raw/*567.37*/("""
	     		"""),format.raw/*568.9*/("""document.getElementById('mostrarLaOt').innerHTML = respuesta;
	     		$('#modalVerOt').modal('show');
	     	"""),format.raw/*570.8*/("""}"""),format.raw/*570.9*/("""
        """),format.raw/*571.9*/("""}"""),format.raw/*571.10*/(""");
	"""),format.raw/*572.2*/("""}"""),format.raw/*572.3*/("""
	
	"""),format.raw/*574.2*/("""const verCotizacion = (id_cotizacion) => """),format.raw/*574.43*/("""{"""),format.raw/*574.44*/("""
		"""),format.raw/*575.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*577.16*/("""{"""),format.raw/*577.17*/("""
            """),format.raw/*578.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*585.36*/("""{"""),format.raw/*585.37*/("""
	     		"""),format.raw/*586.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*588.8*/("""}"""),format.raw/*588.9*/("""
        """),format.raw/*589.9*/("""}"""),format.raw/*589.10*/(""");
	"""),format.raw/*590.2*/("""}"""),format.raw/*590.3*/("""
	
	"""),format.raw/*592.2*/("""const mostrarPDFmasAlbum = (id_guia, nombrePDF) => """),format.raw/*592.53*/("""{"""),format.raw/*592.54*/("""
		  """),format.raw/*593.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdfmasAlbum/"+id_guia+","+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*595.2*/("""}"""),format.raw/*595.3*/("""
	
	"""),format.raw/*597.2*/("""const descargaDocumento = (nombreDOC) => """),format.raw/*597.43*/("""{"""),format.raw/*597.44*/("""
		  """),format.raw/*598.5*/("""$('#descargaDocumento').val(nombreDOC);
		  document.getElementById('formDescargaDocumento').submit();
	"""),format.raw/*600.2*/("""}"""),format.raw/*600.3*/("""
	
	"""),format.raw/*602.2*/("""const descargaXLSX = (id_guia, id_transportista) => """),format.raw/*602.54*/("""{"""),format.raw/*602.55*/("""
		"""),format.raw/*603.3*/("""auxId_guia = id_guia;
		auxLlenarTransportista = 1;
		auxId_transportista = id_transportista;
		transportistaSeleccionado();
		$('#modalListaTransporte').modal('show');
	"""),format.raw/*608.2*/("""}"""),format.raw/*608.3*/("""
	
	"""),format.raw/*610.2*/("""const descargaEnExcel = (id_guia) => """),format.raw/*610.39*/("""{"""),format.raw/*610.40*/("""
		"""),format.raw/*611.3*/("""$('#id_guiaExcel').val(id_guia);
		document.getElementById('formDescargaExcel').submit();
	"""),format.raw/*613.2*/("""}"""),format.raw/*613.3*/("""
	
	"""),format.raw/*615.2*/("""const descargarXML = (id_guia, id_transportista) => """),format.raw/*615.54*/("""{"""),format.raw/*615.55*/("""
		"""),format.raw/*616.3*/("""auxId_guia = id_guia;
		auxLlenarTransportista = 2;
		auxId_transportista = id_transportista;
		transportistaSeleccionado();
		$('#modalListaTransporte').modal('show');
	"""),format.raw/*621.2*/("""}"""),format.raw/*621.3*/("""
	
	"""),format.raw/*623.2*/("""const llenarApiManager = (id_guia, id_transportista) => """),format.raw/*623.58*/("""{"""),format.raw/*623.59*/("""
		"""),format.raw/*624.3*/("""auxId_guia = id_guia;
		auxLlenarTransportista = 3;
		auxId_transportista = id_transportista;
		transportistaSeleccionado();
		$('#modalListaTransporte').modal('show');
	"""),format.raw/*629.2*/("""}"""),format.raw/*629.3*/("""
	
	"""),format.raw/*631.2*/("""const llenarApiNubox = (id_guia, id_transportista) => """),format.raw/*631.56*/("""{"""),format.raw/*631.57*/("""
		"""),format.raw/*632.3*/("""auxId_guia = id_guia;
		auxLlenarTransportista = 4;
		auxId_transportista = id_transportista;
		transportistaSeleccionado();
		$('#modalListaTransporte').modal('show');
	"""),format.raw/*637.2*/("""}"""),format.raw/*637.3*/("""
	
	"""),format.raw/*639.2*/("""const llenarWebFacturacion = (id_guia, id_transportista) => """),format.raw/*639.62*/("""{"""),format.raw/*639.63*/("""
		"""),format.raw/*640.3*/("""auxId_guia = id_guia;
		auxLlenarTransportista = 5;
		auxId_transportista = id_transportista;
		transportistaSeleccionado();
		$('#modalListaTransporte').modal('show');
	"""),format.raw/*645.2*/("""}"""),format.raw/*645.3*/("""
	
	"""),format.raw/*647.2*/("""const llenarWebMaximise = (id_guia, id_transportista) => """),format.raw/*647.59*/("""{"""),format.raw/*647.60*/("""
		"""),format.raw/*648.3*/("""auxId_guia = id_guia;
		auxLlenarTransportista = 6;
		auxId_transportista = id_transportista;
		transportistaSeleccionado();
		$('#modalListaTransporte').modal('show');
	"""),format.raw/*653.2*/("""}"""),format.raw/*653.3*/("""
	
	"""),format.raw/*655.2*/("""const llenarWebIConstruye = (id_guia, id_transportista) => """),format.raw/*655.61*/("""{"""),format.raw/*655.62*/("""
		"""),format.raw/*656.3*/("""auxId_guia = id_guia;
		auxLlenarTransportista = 7;
		auxId_transportista = id_transportista;
		transportistaSeleccionado();
		$('#modalListaTransporte').modal('show');
	"""),format.raw/*661.2*/("""}"""),format.raw/*661.3*/("""
	
	"""),format.raw/*663.2*/("""const llenarApiRelBase = (id_guia, id_transportista) => """),format.raw/*663.58*/("""{"""),format.raw/*663.59*/("""
		"""),format.raw/*664.3*/("""auxId_guia = id_guia;
		auxLlenarTransportista = 8;
		auxId_transportista = id_transportista;
		transportistaSeleccionado();
		$('#modalListaTransporte').modal('show');
	"""),format.raw/*669.2*/("""}"""),format.raw/*669.3*/("""
	
	"""),format.raw/*671.2*/("""const llenarApiSapSchwager = (id_guia, id_transportista) => """),format.raw/*671.62*/("""{"""),format.raw/*671.63*/("""
		"""),format.raw/*672.3*/("""auxId_guia = id_guia;
		auxLlenarTransportista = 9;
		auxId_transportista = id_transportista;
		transportistaSeleccionado();
		$('#modalListaTransporte').modal('show');
	"""),format.raw/*677.2*/("""}"""),format.raw/*677.3*/("""
	
	
	
	"""),format.raw/*681.2*/("""let auxId_guia = 0;
	let auxLlenarTransportista = 0;
	const seleccionaTransportista = (id_transportista) => """),format.raw/*683.56*/("""{"""),format.raw/*683.57*/("""
		"""),format.raw/*684.3*/("""$('#modalListaTransporte').modal('hide');
		
		if(auxLlenarTransportista==1)"""),format.raw/*686.32*/("""{"""),format.raw/*686.33*/("""
			"""),format.raw/*687.4*/("""$('#id_guiaXLSX').val(auxId_guia);
			$('#id_transportistaXLSX').val(id_transportista);
			document.getElementById('formDescargaXLSX').submit();
		"""),format.raw/*690.3*/("""}"""),format.raw/*690.4*/("""
		
		"""),format.raw/*692.3*/("""if(auxLlenarTransportista==2)"""),format.raw/*692.32*/("""{"""),format.raw/*692.33*/("""
			"""),format.raw/*693.4*/("""$('#id_guiaXML').val(auxId_guia);
			$('#id_transportistaXML').val(id_transportista);
			document.getElementById('formDescargaXML').submit();
		"""),format.raw/*696.3*/("""}"""),format.raw/*696.4*/("""
		
		"""),format.raw/*698.3*/("""if(auxLlenarTransportista==3)"""),format.raw/*698.32*/("""{"""),format.raw/*698.33*/("""
			"""),format.raw/*699.4*/("""$('#id_guiaManager').val(auxId_guia);
			$('#id_transportistaManager').val(id_transportista);
			document.getElementById('formEnviaApiManager').submit();
		"""),format.raw/*702.3*/("""}"""),format.raw/*702.4*/("""
		
		"""),format.raw/*704.3*/("""if(auxLlenarTransportista==4)"""),format.raw/*704.32*/("""{"""),format.raw/*704.33*/("""
			"""),format.raw/*705.4*/("""$('#id_guiaNubox').val(auxId_guia);
			$('#id_transportistaNubox').val(id_transportista);
			document.getElementById('formEnviaApiNubox').submit();
		"""),format.raw/*708.3*/("""}"""),format.raw/*708.4*/("""
		
		"""),format.raw/*710.3*/("""if(auxLlenarTransportista==5)"""),format.raw/*710.32*/("""{"""),format.raw/*710.33*/("""
			"""),format.raw/*711.4*/("""$('#id_guiaFacturacion').val(auxId_guia);
			$('#id_transportistaFacturacion').val(id_transportista);
			document.getElementById('formEnviaWebFacturacion').submit();
		"""),format.raw/*714.3*/("""}"""),format.raw/*714.4*/("""
		
		"""),format.raw/*716.3*/("""if(auxLlenarTransportista==6)"""),format.raw/*716.32*/("""{"""),format.raw/*716.33*/("""
			"""),format.raw/*717.4*/("""auxId_transportista = id_transportista;
			$('#selectSucursal').modal('show');
		"""),format.raw/*719.3*/("""}"""),format.raw/*719.4*/("""
		
		"""),format.raw/*721.3*/("""if(auxLlenarTransportista==7)"""),format.raw/*721.32*/("""{"""),format.raw/*721.33*/("""
			"""),format.raw/*722.4*/("""$('#id_guiaIConstruye').val(auxId_guia);
			$('#id_transportistaIConstruye').val(id_transportista);
			document.getElementById('formEnviaWebIConstruye').submit();
		"""),format.raw/*725.3*/("""}"""),format.raw/*725.4*/("""
		
		"""),format.raw/*727.3*/("""if(auxLlenarTransportista==8)"""),format.raw/*727.32*/("""{"""),format.raw/*727.33*/("""
			"""),format.raw/*728.4*/("""$('#id_guiaRelBase').val(auxId_guia);
			$('#id_transportistaRelBase').val(id_transportista);
			$('#modalListaTransporte').modal('hide');
			buscaSolucion(auxId_guia, id_transportista);
			$('#modalAdicRelBase').modal('show');
		"""),format.raw/*733.3*/("""}"""),format.raw/*733.4*/("""
		
		"""),format.raw/*735.3*/("""if(auxLlenarTransportista==9)"""),format.raw/*735.32*/("""{"""),format.raw/*735.33*/("""
			"""),format.raw/*736.4*/("""$('#id_guiaSapSchwager').val(auxId_guia);
			$('#id_transportistaSapSchwager').val(id_transportista);
			document.getElementById('formEnviaSapSchwager').submit();
		"""),format.raw/*739.3*/("""}"""),format.raw/*739.4*/("""
		
	"""),format.raw/*741.2*/("""}"""),format.raw/*741.3*/("""
	
	"""),format.raw/*743.2*/("""const enviarRelBase = () =>"""),format.raw/*743.29*/("""{"""),format.raw/*743.30*/("""
		"""),format.raw/*744.3*/("""$('#id_cotizaSolucionRelBase').val($('#id_cotizaSolucion').val());
			$('#descriptionRelBase').val($('#description').val());
			$('#observacionesRelBase').val($('#observaciones').val());
			document.getElementById('formEnviaApiRelBase').submit();
	"""),format.raw/*748.2*/("""}"""),format.raw/*748.3*/("""
	
	"""),format.raw/*750.2*/("""const pdfReBase = (nroFiscal) =>"""),format.raw/*750.34*/("""{"""),format.raw/*750.35*/("""
		"""),format.raw/*751.3*/("""$('#linkRelBase').text("GUIA");
		document.getElementById('rutaPDF').innerHTML="<object data='/showPdfRelBase/"+52+","+nroFiscal+"' type='application/pdf' width='100%' height='720'></object>";
		$('#muestraPDF').modal('show');
	"""),format.raw/*754.2*/("""}"""),format.raw/*754.3*/("""
	
	"""),format.raw/*756.2*/("""const selectSucursal = sucurMaximise =>"""),format.raw/*756.41*/("""{"""),format.raw/*756.42*/("""
		"""),format.raw/*757.3*/("""$('#id_guiaMaximise').val(auxId_guia);
		$('#id_transportistaMaximise').val(auxId_transportista);
		$('#sucursalMaximise').val(sucurMaximise);
		document.getElementById('formEnviaWebMaximise').submit();
	"""),format.raw/*761.2*/("""}"""),format.raw/*761.3*/("""
	
	"""),format.raw/*763.2*/("""const downGuia = nroIntGuia =>"""),format.raw/*763.32*/("""{"""),format.raw/*763.33*/("""
		"""),format.raw/*764.3*/("""var formData = new FormData();
	  	formData.append('nroIntGuia',nroIntGuia);
        $.ajax("""),format.raw/*766.16*/("""{"""),format.raw/*766.17*/("""
            """),format.raw/*767.13*/("""url: "/downGuiaMaximiseAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*774.36*/("""{"""),format.raw/*774.37*/("""
	     		
	     	"""),format.raw/*776.8*/("""}"""),format.raw/*776.9*/("""
        """),format.raw/*777.9*/("""}"""),format.raw/*777.10*/(""");
	"""),format.raw/*778.2*/("""}"""),format.raw/*778.3*/("""
	
	"""),format.raw/*780.2*/("""const downGuiaIconstruye = id_guia =>"""),format.raw/*780.39*/("""{"""),format.raw/*780.40*/("""
		"""),format.raw/*781.3*/("""$('#id_guiaIConstruye2').val(id_guia);
		document.getElementById('downGuiaIconstruye').submit();
	"""),format.raw/*783.2*/("""}"""),format.raw/*783.3*/("""
	
	
"""),format.raw/*786.1*/("""</script>

		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaGuias:List[tables.Guia],listaTransporte:List[tables.Transportista],fechaDesde:String,fechaHasta:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaGuias,listaTransporte,fechaDesde,fechaHasta)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Guia],List[tables.Transportista],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaGuias,listaTransporte,fechaDesde,fechaHasta) => apply(mapDiccionario,mapPermiso,userMnu,listaGuias,listaTransporte,fechaDesde,fechaHasta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMovimientos/movimientoListar.scala.html
                  HASH: d0f64b7115bf5c4c732a69e79e1b91cb111354f2
                  MATRIX: 1078->1|1384->214|1417->222|1433->230|1472->232|1501->236|1569->284|1599->289|1654->324|1685->328|1762->379|1865->461|1895->464|2861->1403|2892->1413|2974->1468|3005->1478|3284->1730|3307->1744|3338->1754|3641->2030|3664->2044|3699->2058|3796->2128|3819->2142|3854->2156|4068->2342|4109->2344|4145->2353|4217->2382|4252->2390|4382->2493|4421->2494|4457->2503|4531->2534|4566->2542|4711->2660|4750->2661|4786->2670|4852->2693|4961->2775|5000->2776|5036->2785|5100->2806|5255->2933|5295->2934|5331->2943|5406->2975|5559->3100|5599->3101|5635->3110|5708->3140|5867->3271|5907->3272|5943->3281|6043->3338|6271->3538|6311->3539|6347->3548|6448->3606|6639->3769|6679->3770|6715->3779|6795->3816|7021->4014|7061->4015|7097->4024|7172->4056|7406->4262|7446->4263|7482->4272|7577->4323|7612->4330|7679->4370|7721->4395|7761->4396|7797->4404|7871->4450|7887->4456|7923->4470|7998->4517|8014->4523|8055->4542|8175->4634|8215->4635|8255->4646|8312->4675|8328->4681|8361->4692|8445->4748|8461->4754|8497->4768|8553->4804|8593->4805|8633->4816|8678->4829|8715->4838|8835->4930|8875->4931|8915->4943|8931->4949|8975->4971|9010->4986|9050->4987|9090->4998|9135->5011|9172->5020|9247->5067|9263->5073|9297->5085|9372->5132|9388->5138|9430->5158|9546->5246|9562->5252|9592->5260|9806->5446|9822->5452|9855->5463|9900->5480|9919->5489|9974->5522|10011->5531|10138->5630|10154->5636|10197->5657|10242->5674|10261->5683|10326->5726|10363->5735|10436->5780|10452->5786|10498->5810|10604->5888|10620->5894|10660->5912|10694->5917|10711->5923|10752->5941|10829->5990|10845->5996|10892->6021|10998->6099|11014->6105|11055->6124|11089->6129|11106->6135|11148->6154|11277->6255|11293->6261|11323->6269|11493->6411|11509->6417|11544->6430|11581->6439|11737->6566|11779->6568|11818->6578|11888->6620|11904->6626|11939->6639|11978->6649|12038->6664|12075->6673|12181->6751|12221->6752|12261->6763|12298->6780|12338->6781|12378->6792|12448->6834|12464->6840|12494->6848|12526->6852|12542->6858|12577->6871|12699->6961|12736->6970|12862->7068|12902->7069|12941->7079|13050->7160|13066->7166|13096->7174|13245->7278|13291->7296|13397->7374|13437->7375|13477->7386|13514->7403|13554->7404|13594->7415|13663->7456|13679->7462|13715->7476|13837->7566|13874->7575|14000->7673|14040->7674|14080->7685|14117->7702|14157->7703|14197->7714|14254->7743|14270->7749|14303->7760|14423->7848|14460->7857|14583->7952|14623->7953|14662->7963|14768->8041|14784->8047|14814->8055|14846->8059|14862->8065|14906->8087|15056->8192|15182->8290|15222->8291|15261->8301|15395->8407|15435->8408|15476->8420|15540->8456|15556->8462|15586->8470|15618->8474|15634->8480|15678->8502|15798->8602|15838->8603|15879->8615|15925->8629|15964->8639|16024->8654|16181->8782|16222->8783|16261->8793|16395->8899|16435->8900|16476->8912|16545->8953|16561->8959|16591->8967|16623->8971|16639->8977|16683->8999|16803->9099|16843->9100|16884->9112|16930->9126|16969->9136|17029->9151|17184->9277|17225->9278|17264->9288|17398->9394|17438->9395|17515->9444|17555->9445|17597->9458|17664->9497|17680->9503|17710->9511|17742->9515|17758->9521|17802->9543|17925->9646|17965->9647|18007->9660|18074->9699|18090->9705|18120->9713|18152->9717|18168->9723|18212->9745|18345->9846|18381->9862|18421->9863|18462->9875|18508->9889|18547->9899|18607->9914|18768->10046|18809->10047|18848->10057|18982->10163|19022->10164|19098->10212|19138->10213|19180->10226|19253->10271|19269->10277|19299->10285|19331->10289|19347->10295|19392->10317|19515->10420|19555->10421|19597->10434|19670->10479|19686->10485|19716->10493|19748->10497|19764->10503|19809->10525|19942->10626|19990->10654|20030->10655|20071->10667|20117->10681|20156->10691|20305->10812|20345->10813|20424->10864|20464->10865|20506->10878|20544->10888|20560->10894|20597->10909|20725->11017|20765->11018|20807->11031|20854->11046|20890->11062|20930->11063|20971->11075|21017->11089|21056->11099|21116->11114|21355->11324|21396->11325|21435->11335|21569->11441|21609->11442|21685->11490|21725->11491|21767->11504|21837->11546|21853->11552|21883->11560|21915->11564|21931->11570|21976->11592|22099->11695|22139->11696|22181->11709|22251->11751|22267->11757|22297->11765|22329->11769|22345->11775|22390->11797|22523->11898|22571->11926|22611->11927|22652->11939|22698->11953|22737->11963|22886->12084|22926->12085|23005->12136|23045->12137|23087->12150|23147->12182|23163->12188|23200->12203|23294->12269|23310->12275|23347->12290|23407->12330|23447->12331|23489->12344|23536->12359|23572->12375|23612->12376|23653->12388|23699->12402|23738->12412|23798->12427|24000->12600|24041->12601|24080->12611|24189->12692|24229->12693|24271->12706|24342->12749|24358->12755|24388->12763|24420->12767|24436->12773|24481->12795|24600->12894|24640->12895|24682->12908|24752->12950|24768->12956|24798->12964|24898->13036|24914->13042|24951->13057|25019->13093|25058->13103|25118->13118|25355->13326|25396->13327|25435->13337|25543->13417|25583->13418|25624->13430|25693->13471|25709->13477|25739->13485|25771->13489|25787->13495|25831->13517|25951->13617|25991->13618|26070->13669|26110->13670|26152->13683|26201->13704|26217->13710|26254->13725|26331->13774|26347->13780|26384->13795|26486->13869|26502->13875|26539->13890|26619->13950|26659->13951|26701->13964|26748->13979|26792->13991|26831->14001|26891->14016|27136->14232|27177->14233|27216->14243|27324->14323|27364->14324|27405->14336|27478->14381|27494->14387|27524->14395|27556->14399|27572->14405|27617->14427|27737->14527|27777->14528|27818->14540|27891->14585|27907->14591|27937->14599|27969->14603|27985->14609|28030->14631|28160->14729|28199->14739|28323->14835|28363->14836|28404->14849|28420->14855|28457->14870|28493->14886|28533->14887|28574->14899|28620->14913|28659->14923|28719->14938|28755->14946|28800->14960|28834->14966|29491->15595|29515->15609|29561->15633|35182->21226|35214->21236|35294->21288|35326->21298|35672->21616|35704->21626|35784->21678|35816->21688|35859->21704|35950->21773|35985->21777|36016->21780|36151->21886|36181->21887|36212->21890|36353->22002|36383->22003|36415->22007|36595->22158|36625->22159|36658->22164|36745->22223|36774->22224|36806->22228|36836->22229|36869->22234|36945->22282|36974->22283|37005->22286|37034->22287|37064->22289|37093->22290|37124->22293|37182->22322|37212->22323|37244->22327|37304->22358|37334->22359|37370->22367|37546->22514|37576->22515|37617->22527|37724->22605|37754->22606|37787->22611|37816->22612|37845->22613|37946->22686|37975->22687|38035->22718|38065->22719|38096->22722|38207->22804|38237->22805|38279->22818|38530->23040|38560->23041|38597->23050|38734->23159|38763->23160|38800->23169|38830->23170|38862->23174|38891->23175|38923->23179|38993->23220|39023->23221|39054->23224|39181->23322|39211->23323|39253->23336|39512->23566|39542->23567|39579->23576|39732->23701|39761->23702|39798->23711|39828->23712|39860->23716|39889->23717|39921->23721|40001->23772|40031->23773|40064->23778|40294->23980|40323->23981|40355->23985|40425->24026|40455->24027|40488->24032|40620->24136|40649->24137|40681->24141|40762->24193|40792->24194|40823->24197|41021->24367|41050->24368|41082->24372|41148->24409|41178->24410|41209->24413|41328->24504|41357->24505|41389->24509|41470->24561|41500->24562|41531->24565|41729->24735|41758->24736|41790->24740|41875->24796|41905->24797|41936->24800|42134->24970|42163->24971|42195->24975|42278->25029|42308->25030|42339->25033|42537->25203|42566->25204|42598->25208|42687->25268|42717->25269|42748->25272|42946->25442|42975->25443|43007->25447|43093->25504|43123->25505|43154->25508|43352->25678|43381->25679|43413->25683|43501->25742|43531->25743|43562->25746|43760->25916|43789->25917|43821->25921|43906->25977|43936->25978|43967->25981|44165->26151|44194->26152|44226->26156|44315->26216|44345->26217|44376->26220|44574->26390|44603->26391|44639->26399|44776->26507|44806->26508|44837->26511|44942->26587|44972->26588|45004->26592|45179->26739|45208->26740|45242->26746|45300->26775|45330->26776|45362->26780|45534->26924|45563->26925|45597->26931|45655->26960|45685->26961|45717->26965|45901->27121|45930->27122|45964->27128|46022->27157|46052->27158|46084->27162|46262->27312|46291->27313|46325->27319|46383->27348|46413->27349|46445->27353|46641->27521|46670->27522|46704->27528|46762->27557|46792->27558|46824->27562|46933->27643|46962->27644|46996->27650|47054->27679|47084->27680|47116->27684|47309->27849|47338->27850|47372->27856|47430->27885|47460->27886|47492->27890|47750->28120|47779->28121|47813->28127|47871->28156|47901->28157|47933->28161|48126->28326|48155->28327|48188->28332|48217->28333|48249->28337|48305->28364|48335->28365|48366->28368|48642->28616|48671->28617|48703->28621|48764->28653|48794->28654|48825->28657|49081->28885|49110->28886|49142->28890|49210->28929|49240->28930|49271->28933|49503->29137|49532->29138|49564->29142|49623->29172|49653->29173|49684->29176|49805->29268|49835->29269|49877->29282|50139->29515|50169->29516|50214->29533|50243->29534|50280->29543|50310->29544|50342->29548|50371->29549|50403->29553|50469->29590|50499->29591|50530->29594|50656->29692|50685->29693|50718->29698
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|44->13|72->41|72->41|73->42|73->42|81->50|81->50|81->50|89->58|89->58|89->58|91->60|91->60|91->60|94->63|94->63|95->64|96->65|97->66|98->67|98->67|99->68|100->69|101->70|103->72|103->72|104->73|105->74|106->75|106->75|107->76|108->77|109->78|109->78|110->79|111->80|112->81|112->81|113->82|114->83|115->84|115->84|116->85|118->87|119->88|119->88|120->89|122->91|123->92|123->92|124->93|125->94|126->95|126->95|127->96|128->97|129->98|129->98|130->99|132->101|133->102|136->105|136->105|136->105|137->106|138->107|138->107|138->107|139->108|139->108|139->108|141->110|141->110|142->111|142->111|142->111|142->111|143->112|143->112|143->112|145->114|145->114|146->115|147->116|148->117|150->119|150->119|151->120|151->120|151->120|152->121|152->121|153->122|154->123|155->124|156->125|156->125|156->125|157->126|157->126|157->126|159->128|159->128|159->128|164->133|164->133|164->133|165->134|165->134|165->134|166->135|168->137|168->137|168->137|169->138|169->138|169->138|170->139|171->140|171->140|171->140|172->141|172->141|172->141|172->141|172->141|172->141|173->142|173->142|173->142|174->143|174->143|174->143|174->143|174->143|174->143|177->146|177->146|177->146|182->151|182->151|182->151|183->152|184->153|184->153|185->154|186->155|186->155|186->155|187->156|188->157|189->158|190->159|190->159|191->160|192->161|192->161|193->162|193->162|193->162|193->162|193->162|193->162|193->162|196->165|197->166|199->168|199->168|200->169|201->170|201->170|201->170|205->174|207->176|208->177|208->177|209->178|210->179|210->179|211->180|211->180|211->180|211->180|214->183|215->184|218->187|218->187|219->188|220->189|220->189|221->190|221->190|221->190|221->190|224->193|225->194|227->196|227->196|228->197|229->198|229->198|229->198|229->198|229->198|229->198|233->202|236->205|236->205|237->206|238->207|238->207|239->208|239->208|239->208|239->208|239->208|239->208|239->208|242->211|242->211|243->212|244->213|245->214|246->215|247->216|247->216|248->217|249->218|249->218|250->219|250->219|250->219|250->219|250->219|250->219|250->219|253->222|253->222|254->223|255->224|256->225|257->226|258->227|258->227|259->228|260->229|260->229|262->231|262->231|263->232|263->232|263->232|263->232|263->232|263->232|263->232|266->235|266->235|267->236|267->236|267->236|267->236|267->236|267->236|267->236|270->239|271->240|271->240|272->241|273->242|274->243|275->244|276->245|276->245|277->246|278->247|278->247|279->248|279->248|280->249|280->249|280->249|280->249|280->249|280->249|280->249|283->252|283->252|284->253|284->253|284->253|284->253|284->253|284->253|284->253|287->256|289->258|289->258|290->259|291->260|292->261|294->263|294->263|295->264|295->264|296->265|296->265|296->265|296->265|299->268|299->268|300->269|301->270|302->271|302->271|303->272|304->273|305->274|306->275|308->277|308->277|309->278|310->279|310->279|311->280|311->280|312->281|312->281|312->281|312->281|312->281|312->281|312->281|315->284|315->284|316->285|316->285|316->285|316->285|316->285|316->285|316->285|319->288|321->290|321->290|322->291|323->292|324->293|326->295|326->295|327->296|327->296|328->297|328->297|328->297|328->297|329->298|329->298|329->298|331->300|331->300|332->301|333->302|334->303|334->303|335->304|336->305|337->306|338->307|340->309|340->309|341->310|342->311|342->311|343->312|343->312|343->312|343->312|343->312|343->312|343->312|346->315|346->315|347->316|347->316|347->316|347->316|348->317|348->317|348->317|350->319|351->320|352->321|354->323|354->323|355->324|356->325|356->325|357->326|357->326|357->326|357->326|357->326|357->326|357->326|360->329|360->329|361->330|361->330|362->331|362->331|362->331|362->331|363->332|363->332|363->332|364->333|364->333|364->333|367->336|367->336|368->337|369->338|370->339|371->340|372->341|374->343|374->343|375->344|376->345|376->345|377->346|377->346|377->346|377->346|377->346|377->346|377->346|380->349|380->349|381->350|381->350|381->350|381->350|381->350|381->350|381->350|384->353|385->354|387->356|387->356|388->357|388->357|388->357|389->358|389->358|390->359|391->360|392->361|393->362|394->363|395->364|396->365|413->382|413->382|413->382|541->510|541->510|542->511|542->511|548->517|548->517|549->518|549->518|552->521|552->521|554->523|557->526|561->530|561->530|562->531|563->532|563->532|564->533|566->535|566->535|567->536|569->538|569->538|569->538|569->538|570->539|571->540|571->540|572->541|572->541|573->542|573->542|575->544|575->544|575->544|576->545|576->545|576->545|577->546|580->549|580->549|581->550|582->551|582->551|583->552|583->552|583->552|585->554|585->554|587->556|587->556|588->557|590->559|590->559|591->560|598->567|598->567|599->568|601->570|601->570|602->571|602->571|603->572|603->572|605->574|605->574|605->574|606->575|608->577|608->577|609->578|616->585|616->585|617->586|619->588|619->588|620->589|620->589|621->590|621->590|623->592|623->592|623->592|624->593|626->595|626->595|628->597|628->597|628->597|629->598|631->600|631->600|633->602|633->602|633->602|634->603|639->608|639->608|641->610|641->610|641->610|642->611|644->613|644->613|646->615|646->615|646->615|647->616|652->621|652->621|654->623|654->623|654->623|655->624|660->629|660->629|662->631|662->631|662->631|663->632|668->637|668->637|670->639|670->639|670->639|671->640|676->645|676->645|678->647|678->647|678->647|679->648|684->653|684->653|686->655|686->655|686->655|687->656|692->661|692->661|694->663|694->663|694->663|695->664|700->669|700->669|702->671|702->671|702->671|703->672|708->677|708->677|712->681|714->683|714->683|715->684|717->686|717->686|718->687|721->690|721->690|723->692|723->692|723->692|724->693|727->696|727->696|729->698|729->698|729->698|730->699|733->702|733->702|735->704|735->704|735->704|736->705|739->708|739->708|741->710|741->710|741->710|742->711|745->714|745->714|747->716|747->716|747->716|748->717|750->719|750->719|752->721|752->721|752->721|753->722|756->725|756->725|758->727|758->727|758->727|759->728|764->733|764->733|766->735|766->735|766->735|767->736|770->739|770->739|772->741|772->741|774->743|774->743|774->743|775->744|779->748|779->748|781->750|781->750|781->750|782->751|785->754|785->754|787->756|787->756|787->756|788->757|792->761|792->761|794->763|794->763|794->763|795->764|797->766|797->766|798->767|805->774|805->774|807->776|807->776|808->777|808->777|809->778|809->778|811->780|811->780|811->780|812->781|814->783|814->783|817->786
                  -- GENERATED --
              */
          