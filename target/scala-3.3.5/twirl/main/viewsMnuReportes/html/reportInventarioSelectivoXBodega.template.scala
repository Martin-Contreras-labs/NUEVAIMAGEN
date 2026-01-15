
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

object reportInventarioSelectivoXBodega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, lista: List[List[String]], fechaCorte: String, tipo:String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""
	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalEquipoDescripcion()),format.raw/*8.27*/("""
	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, bodega.nombreTipoBodega + ": " + bodega.nombre.toUpperCase() + " - PROYECTO: "+bodega.nickProyecto + " - " +tipo, 
			"INVENTARIO POR "+mapDiccionario.get("BODEGA")+"/PROYECTO VALORIZADO - FECHA DE CORTE: " + utilities.Fechas.DDMMAA(fechaCorte))),format.raw/*12.131*/("""
		
		"""),format.raw/*14.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td width="25%">
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text" id="basic-addon1">BUSCAR</span>
					  		</div>
					  		<input type="text" class="form-control left"
								id="searchTermtablaPrincipal"
								onkeyup="doSearch3('tablaPrincipal'); sumarTotales2();">
						</div>
					</td>
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
					<td width="25%">
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text">TOTAL  CANTIDAD</span>
					  		</div>
					  		<input type="text" class="form-control right"
								id="totalCantidad"
								readonly>
						</div>
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text">TOTAL KG</span>
					  		</div>
					  		<input type="text" class="form-control right"
								id="totalKG"
								readonly>
						</div>
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text">TOTAL M2</span>
					  		</div>
					  		<input type="text" class="form-control right"
								id="totalM2"
								readonly>
						</div>
						<div class="input-group">
					  		<div class="input-group-prepend">
					    		<span class="input-group-text">TOTAL COMPRA ("""),_display_(/*69.58*/mapDiccionario/*69.72*/.get("PESOS")),format.raw/*69.85*/(""")</span>
					  		</div>
					  		<input type="text" class="form-control right"
								id="totalPesosC"
								readonly>
						</div>
						"""),_display_(if(bodega.getEsInterna()==2)/*75.36*/{_display_(Seq[Any](format.raw/*75.37*/("""
							"""),format.raw/*76.8*/("""<div class="input-group">
						  		<div class="input-group-prepend">
						    		<span class="input-group-text">TOTAL VENTA ("""),_display_(/*78.58*/mapDiccionario/*78.72*/.get("PESOS")),format.raw/*78.85*/(""")</span>
						  		</div>
						  		<input type="text" class="form-control right"
									id="totalPesosV"
									readonly>
							</div>
						""")))} else {null} ),format.raw/*84.8*/("""
					"""),format.raw/*85.6*/("""</td>
				</tr>
			</table>
		</div>
		
		<div>
			ALTERNAR COLUMNAS: 
				<a class="toggle-vis" href="#" data-column="7"><kbd id="kdb7" style="background-color: #73C6B6">GRUPO/FAMILIA</kbd></a>
				<a class="toggle-vis" href="#" data-column="8"><kbd id="kdb8" style="background-color: #73C6B6">PROPIEDAD</kbd></a>
				<a class="toggle-vis" href="#" data-column="9"><kbd id="kdb9" style="background-color: #73C6B6">Nro.Coti</kbd></a>
				<a class="toggle-vis" href="#" data-column="10"><kbd id="kdb10" style="background-color: #73C6B6">COD</kbd></a>
				<a class="toggle-vis" href="#" data-column="11"><kbd id="kdb11" style="background-color: #73C6B6">EQUIPO</kbd></a>
				<a class="toggle-vis" href="#" data-column="12"><kbd id="kdb12" style="background-color: #73C6B6">KG/UN</kbd></a>
				<a class="toggle-vis" href="#" data-column="13"><kbd id="kdb13" style="background-color: #73C6B6">M2/UN</kbd></a>
				<a class="toggle-vis" href="#" data-column="14"><kbd id="kdb14" style="background-color: #73C6B6">MON Compra</kbd></a>
				<a class="toggle-vis" href="#" data-column="15"><kbd id="kdb15" style="background-color: #73C6B6">P.U. Compra</kbd></a>
				
				"""),_display_(if(bodega.getEsInterna()==2)/*102.34*/{_display_(Seq[Any](format.raw/*102.35*/("""
					"""),format.raw/*103.6*/("""<a class="toggle-vis" href="#" data-column="16"><kbd id="kdb16" style="background-color: #73C6B6">MON Vta/"""),_display_(/*103.113*/mapDiccionario/*103.127*/.get("ARR")),format.raw/*103.138*/("""</kbd></a>
					<a class="toggle-vis" href="#" data-column="17"><kbd id="kdb17" style="background-color: #73C6B6">P.U. Venta</kbd></a>
					<a class="toggle-vis" href="#" data-column="18"><kbd id="kdb18" style="background-color: #73C6B6">UN """),_display_(/*105.108*/mapDiccionario/*105.122*/.get("ARR")),format.raw/*105.133*/("""</kbd></a>
					<a class="toggle-vis" href="#" data-column="19"><kbd id="kdb19" style="background-color: #73C6B6">P.U. """),_display_(/*106.110*/mapDiccionario/*106.124*/.get("Arriendo")),format.raw/*106.140*/("""</kbd></a>
					<a class="toggle-vis" href="#" data-column="20"><kbd id="kdb20" style="background-color: #73C6B6">UN</kbd></a>
					<a class="toggle-vis" href="#" data-column="21"><kbd id="kdb21" style="background-color: #73C6B6">CANT</kbd></a>
					<a class="toggle-vis" href="#" data-column="22"><kbd id="kdb22" style="background-color: #73C6B6">TOTAL Compra</kbd></a>
					<a class="toggle-vis" href="#" data-column="23"><kbd id="kdb23" style="background-color: #73C6B6">TOTAL Venta</kbd></a>
					<a class="toggle-vis" href="#" data-column="24"><kbd id="kdb24" style="background-color: #73C6B6">Compra ("""),_display_(/*111.113*/mapDiccionario/*111.127*/.get("PESOS")),format.raw/*111.140*/(""")</kbd></a>
					<a class="toggle-vis" href="#" data-column="25"><kbd id="kdb25" style="background-color: #73C6B6">Venta ("""),_display_(/*112.112*/mapDiccionario/*112.126*/.get("PESOS")),format.raw/*112.139*/(""")</kbd></a>
					<a class="toggle-vis" href="#" data-column="26"><kbd id="kdb26" style="background-color: #73C6B6">TOTAL KG</kbd></a>
					<a class="toggle-vis" href="#" data-column="27"><kbd id="kdb27" style="background-color: #73C6B6">TOTAL M2</kbd></a>
					<a class="toggle-vis" href="#" data-column="28"><kbd id="kdb28" style="background-color: #73C6B6">Permanencia (en Días)</kbd></a>
				""")))}else/*116.10*/{_display_(Seq[Any](format.raw/*116.11*/("""
					"""),format.raw/*117.6*/("""<a class="toggle-vis" href="#" data-column="16"><kbd id="kdb16" style="background-color: #73C6B6">UN</kbd></a>
					<a class="toggle-vis" href="#" data-column="17"><kbd id="kdb17" style="background-color: #73C6B6">CANT</kbd></a>
					<a class="toggle-vis" href="#" data-column="18"><kbd id="kdb18" style="background-color: #73C6B6">TOTAL Compra</kbd></a>
					<a class="toggle-vis" href="#" data-column="19"><kbd id="kdb19" style="background-color: #73C6B6">Compra ("""),_display_(/*120.113*/mapDiccionario/*120.127*/.get("PESOS")),format.raw/*120.140*/(""")</kbd></a>
					<a class="toggle-vis" href="#" data-column="20"><kbd id="kdb20" style="background-color: #73C6B6">TOTAL KG</kbd></a>
					<a class="toggle-vis" href="#" data-column="21"><kbd id="kdb21" style="background-color: #73C6B6">TOTAL M2</kbd></a>
					<a class="toggle-vis" href="#" data-column="22"><kbd id="kdb22" style="background-color: #73C6B6">Permanencia (en Días)</kbd></a>
				""")))}),format.raw/*124.6*/("""
				
		"""),format.raw/*126.3*/("""</div>
		
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
					<TR> 
						<TH hidden></TH>
						<TH hidden></TH>
						<TH hidden></TH>
						<TH hidden></TH>
						<TH hidden></TH>
						<TH hidden></TH>
						<TH hidden></TH>
						
				        <TH style= "text-align:right;vertical-align:top;width:10%;"></TH>
						<TH style= "text-align:right;vertical-align:top;width:10%;"></TH>
				        <TH style= "text-align:right;vertical-align:top;width:10%;"></TH>
				        <TH style= "text-align:right;vertical-align:top;width:10%;"></TH>
						<TH style= "text-align:right;vertical-align:top;width:20%;color:blue">SUBTOTALES (resultado de buscar):&nbsp</TH>
						<TH style= "text-align:right;vertical-align:top;color:blue"></TH>
						<TH style= "text-align:right;vertical-align:top;color:blue">elementos</TH>
						<TH style= "text-align:center;vertical-align:top;color:blue"></TH>
						<TH style= "text-align:right;vertical-align:top;color:blue"></TH>
						"""),_display_(if(bodega.getEsInterna()==2)/*149.36*/{_display_(Seq[Any](format.raw/*149.37*/("""
							"""),format.raw/*150.8*/("""<TH style= "text-align:right;vertical-align:top;color:blue"></TH>
							<TH style= "text-align:right;vertical-align:top;color:blue"></TH>
							<TH style= "text-align:right;vertical-align:top;color:blue"></TH>
							<TH style= "text-align:right;vertical-align:top;color:blue"></TH>
						""")))} else {null} ),format.raw/*154.8*/("""
							"""),format.raw/*155.8*/("""<TH style= "text-align:right;vertical-align:top;color:blue"></TH>
							<TH style= "text-align:right;vertical-align:top;color:blue">CANT</TH>
							<TH style= "text-align:right;vertical-align:top;color:blue">TOTAL<br>Compra</TH>
						"""),_display_(if(bodega.getEsInterna()==2)/*158.36*/{_display_(Seq[Any](format.raw/*158.37*/("""
							"""),format.raw/*159.8*/("""<TH style= "text-align:right;vertical-align:top;color:blue">TOTAL<br>Venta</TH>
						""")))} else {null} ),format.raw/*160.8*/("""
							"""),format.raw/*161.8*/("""<TH style= "text-align:right;vertical-align:top;color:blue">Compra<br>("""),_display_(/*161.80*/mapDiccionario/*161.94*/.get("PESOS")),format.raw/*161.107*/(""")</TH>
						"""),_display_(if(bodega.getEsInterna()==2)/*162.36*/{_display_(Seq[Any](format.raw/*162.37*/("""
							"""),format.raw/*163.8*/("""<TH style= "text-align:right;vertical-align:top;color:blue">Venta<br>("""),_display_(/*163.79*/mapDiccionario/*163.93*/.get("PESOS")),format.raw/*163.106*/(""")</TH>
						""")))} else {null} ),format.raw/*164.8*/("""
							"""),format.raw/*165.8*/("""<TH style= "text-align:right;vertical-align:top;color:blue">TOTAL<br>KG</TH>
							<TH style= "text-align:right;vertical-align:top;color:blue">TOTAL<br>M2</TH>
							<TH style= "text-align:right;vertical-align:top;color:blue"></TH>
							<TH style= "text-align:right;vertical-align:top;width:4%;color:blue"></TH>
					</TR>
					<TR> 
						<TH hidden></TH>
						<TH hidden></TH>
						<TH hidden></TH>
						<TH hidden></TH>
						<TH hidden></TH>
						<TH hidden></TH>
						<TH hidden></TH>
						
				        <TH style= "text-align:center;vertical-align:top;width:10%;">GRUPO/FAMILIA</TH>
						<TH style= "text-align:center;vertical-align:top;width:10%;">PROPIEDAD</TH>
				        <TH style= "text-align:center;vertical-align:top;width:10%;">Nro.Coti</TH>
				        <TH style= "text-align:center;vertical-align:top;width:10%;">COD</TH>
						<TH style= "text-align:center;vertical-align:top;width:20%;">EQUIPO</TH>
						<TH style= "text-align:center;vertical-align:top;">KG/UN</TH>
						<TH style= "text-align:center;vertical-align:top;">M2/UN</TH>
						<TH style= "text-align:center;vertical-align:top;">MON</TH>
						<TH style= "text-align:center;vertical-align:top;">P.U.<br>Compra</TH>
						"""),_display_(if(bodega.getEsInterna()==2)/*188.36*/{_display_(Seq[Any](format.raw/*188.37*/("""
							"""),format.raw/*189.8*/("""<TH style= "text-align:center;vertical-align:top;">MON</TH>
							<TH style= "text-align:center;vertical-align:top;">P.U.<br>Venta</TH>
							<TH style= "text-align:center;vertical-align:top;">UN<br>Arr</TH>
							<TH style= "text-align:center;vertical-align:top;">P.U.<br>"""),_display_(/*192.68*/mapDiccionario/*192.82*/.get("Arriendo")),format.raw/*192.98*/("""</TH>
						""")))} else {null} ),format.raw/*193.8*/("""
							"""),format.raw/*194.8*/("""<TH style= "text-align:center;vertical-align:top;">UN</TH>
							<TH style= "text-align:center;vertical-align:top;">CANT</TH>
							<TH style= "text-align:center;vertical-align:top;">TOTAL<br>Compra</TH>
						"""),_display_(if(bodega.getEsInterna()==2)/*197.36*/{_display_(Seq[Any](format.raw/*197.37*/("""
							"""),format.raw/*198.8*/("""<TH style= "text-align:center;vertical-align:top;">TOTAL<br>Venta</TH>
						""")))} else {null} ),format.raw/*199.8*/("""
							"""),format.raw/*200.8*/("""<TH style= "text-align:center;vertical-align:top;">Compra<br>("""),_display_(/*200.71*/mapDiccionario/*200.85*/.get("PESOS")),format.raw/*200.98*/(""")</TH>
						"""),_display_(if(bodega.getEsInterna()==2)/*201.36*/{_display_(Seq[Any](format.raw/*201.37*/("""
							"""),format.raw/*202.8*/("""<TH style= "text-align:center;vertical-align:top;">Venta<br>("""),_display_(/*202.70*/mapDiccionario/*202.84*/.get("PESOS")),format.raw/*202.97*/(""")</TH>
						""")))} else {null} ),format.raw/*203.8*/("""
							"""),format.raw/*204.8*/("""<TH style= "text-align:center;vertical-align:top;">TOTAL<br>KG</TH>
							<TH style= "text-align:center;vertical-align:top;">TOTAL<br>M2</TH>
							<TH style= "text-align:center;vertical-align:top;">Permanencia<br>(en Días)</TH>
							<TH style= "text-align:center;vertical-align:top;width:4%">VER</TH>
					</TR>		
				</thead>
				<tbody>
					"""),_display_(/*211.7*/for(lista1 <- lista) yield /*211.27*/{_display_(Seq[Any](format.raw/*211.28*/("""
						"""),format.raw/*212.7*/("""<TR>
							<td hidden>"""),_display_(/*213.20*/lista1/*213.26*/.get(13)),format.raw/*213.34*/("""</td>
							<td hidden>"""),_display_(/*214.20*/lista1/*214.26*/.get(14)),format.raw/*214.34*/("""</td>
							"""),_display_(if(bodega.getEsInterna()==2)/*215.37*/{_display_(Seq[Any](format.raw/*215.38*/("""
								"""),format.raw/*216.9*/("""<td hidden>"""),_display_(/*216.21*/lista1/*216.27*/.get(15)),format.raw/*216.35*/("""</td>
							""")))}else/*217.13*/{_display_(Seq[Any](format.raw/*217.14*/("""
								"""),format.raw/*218.9*/("""<td hidden>0</td>
							""")))}),format.raw/*219.9*/("""
							"""),format.raw/*220.8*/("""<td hidden>"""),_display_(/*220.20*/lista1/*220.26*/.get(18)),format.raw/*220.34*/("""</td>
							"""),_display_(if(bodega.getEsInterna()==2)/*221.37*/{_display_(Seq[Any](format.raw/*221.38*/("""
								"""),format.raw/*222.9*/("""<td hidden>"""),_display_(/*222.21*/lista1/*222.27*/.get(19)),format.raw/*222.35*/("""</td>
							""")))}else/*223.13*/{_display_(Seq[Any](format.raw/*223.14*/("""
								"""),format.raw/*224.9*/("""<td hidden>0</td>
							""")))}),format.raw/*225.9*/("""
							"""),format.raw/*226.8*/("""<td hidden>"""),_display_(/*226.20*/lista1/*226.26*/.get(28)),format.raw/*226.34*/("""</td>
							<td hidden>"""),_display_(/*227.20*/lista1/*227.26*/.get(29)),format.raw/*227.34*/("""</td>
						
						
							<td style="text-align:left;vertical-align:middle;">"""),_display_(/*230.60*/lista1/*230.66*/.get(3)),format.raw/*230.73*/("""</td>
							<td style="text-align:left;vertical-align:middle;">"""),_display_(/*231.60*/lista1/*231.66*/.get(30)),format.raw/*231.74*/("""</td>
							<td style="text-align:center;vertical-align:middle;">
								"""),_display_(if(lista1.get(24).equals("0"))/*233.40*/{_display_(Seq[Any](format.raw/*233.41*/("""
									"""),_display_(/*234.11*/lista1/*234.17*/.get(24)),format.raw/*234.25*/("""
								""")))}else/*235.14*/{_display_(Seq[Any](format.raw/*235.15*/("""
									"""),format.raw/*236.10*/("""<a href="#" onclick="verCotizacion('"""),_display_(/*236.47*/lista1/*236.53*/.get(23)),format.raw/*236.61*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*238.34*/lista1/*238.40*/.get(24)),format.raw/*238.48*/("""</font>
											</kbd>
									</a>
								""")))}),format.raw/*241.10*/("""
							"""),format.raw/*242.8*/("""</td>
							<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*243.100*/lista1/*243.106*/.get(0)),format.raw/*243.113*/("""');">"""),_display_(/*243.119*/lista1/*243.125*/.get(4)),format.raw/*243.132*/("""</a></td>
							<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*244.100*/lista1/*244.106*/.get(0)),format.raw/*244.113*/("""');">"""),_display_(/*244.119*/lista1/*244.125*/.get(5)),format.raw/*244.132*/("""</a></td>
							
							<td style="text-align:right;vertical-align:middle;">"""),_display_(/*246.61*/lista1/*246.67*/.get(26)),format.raw/*246.75*/("""</td>
							<td style="text-align:right;vertical-align:middle;">"""),_display_(/*247.61*/lista1/*247.67*/.get(27)),format.raw/*247.75*/("""</td>
							
							<td style="text-align:center;vertical-align:middle;">"""),_display_(/*249.62*/lista1/*249.68*/.get(6)),format.raw/*249.75*/("""</td>
							<td style="text-align:right;vertical-align:middle;">"""),_display_(/*250.61*/lista1/*250.67*/.get(7)),format.raw/*250.74*/("""</td>
							"""),_display_(if(bodega.getEsInterna()==2)/*251.37*/{_display_(Seq[Any](format.raw/*251.38*/("""
								"""),format.raw/*252.9*/("""<td style="text-align:center;vertical-align:middle;">"""),_display_(/*252.63*/lista1/*252.69*/.get(8)),format.raw/*252.76*/("""</td>
								<td style="text-align:right;vertical-align:middle;">"""),_display_(/*253.62*/lista1/*253.68*/.get(9)),format.raw/*253.75*/("""</td>
								<td style="text-align:center;vertical-align:middle;">"""),_display_(/*254.63*/lista1/*254.69*/.get(10)),format.raw/*254.77*/("""</td>
								<td style="text-align:right;vertical-align:middle;">"""),_display_(/*255.62*/lista1/*255.68*/.get(11)),format.raw/*255.76*/("""</td>
							""")))} else {null} ),format.raw/*256.9*/("""
							"""),format.raw/*257.8*/("""<td style="text-align:center;vertical-align:middle;">"""),_display_(/*257.62*/lista1/*257.68*/.get(12)),format.raw/*257.76*/("""</td>
							<td style="text-align:right;vertical-align:middle;">"""),_display_(/*258.61*/lista1/*258.67*/.get(13)),format.raw/*258.75*/("""</td>
							<td style="text-align:right;vertical-align:middle;">"""),_display_(/*259.61*/lista1/*259.67*/.get(14)),format.raw/*259.75*/("""</td>
							"""),_display_(if(bodega.getEsInterna()==2)/*260.37*/{_display_(Seq[Any](format.raw/*260.38*/("""
								"""),format.raw/*261.9*/("""<td style="text-align:right;vertical-align:middle;">"""),_display_(/*261.62*/lista1/*261.68*/.get(15)),format.raw/*261.76*/("""</td>
							""")))} else {null} ),format.raw/*262.9*/("""
							"""),format.raw/*263.8*/("""<td style="text-align:right;vertical-align:middle;">"""),_display_(/*263.61*/lista1/*263.67*/.get(18)),format.raw/*263.75*/("""</td>
							"""),_display_(if(bodega.getEsInterna()==2)/*264.37*/{_display_(Seq[Any](format.raw/*264.38*/("""
								"""),format.raw/*265.9*/("""<td style="text-align:right;vertical-align:middle;">"""),_display_(/*265.62*/lista1/*265.68*/.get(19)),format.raw/*265.76*/("""</td>
							""")))} else {null} ),format.raw/*266.9*/("""
							
							"""),format.raw/*268.8*/("""<td style="text-align:right;vertical-align:middle;">"""),_display_(/*268.61*/lista1/*268.67*/.get(28)),format.raw/*268.75*/("""</td>
							<td style="text-align:right;vertical-align:middle;">"""),_display_(/*269.61*/lista1/*269.67*/.get(29)),format.raw/*269.75*/("""</td>
							
							<td style="text-align:right;vertical-align:middle;">"""),_display_(/*271.61*/lista1/*271.67*/.get(22)),format.raw/*271.75*/("""</td>
							<td title='SELECCIONAR' style="text-align:center;vertical-align:middle;">
								<form id="form_"""),_display_(/*273.25*/lista1/*273.31*/.get(0)),format.raw/*273.38*/("""_"""),_display_(/*273.40*/lista1/*273.46*/.get(23)),format.raw/*273.54*/("""" class="formulario" method="post" action="/reportInventProyectoTrazaEquipoEnBod/">
									<input type="hidden" name="id_bodega" value=""""),_display_(/*274.56*/bodega/*274.62*/.getId()),format.raw/*274.70*/("""">
									<input type="hidden" name="id_equipo" value=""""),_display_(/*275.56*/lista1/*275.62*/.get(0)),format.raw/*275.69*/("""">
									<input type="hidden" name="id_cotizacion" value=""""),_display_(/*276.60*/lista1/*276.66*/.get(23)),format.raw/*276.74*/("""">
									<input type="hidden" name="tipo" value=""""),_display_(/*277.51*/tipo),format.raw/*277.55*/("""">
									<a href="#" onclick="document.getElementById('form_"""),_display_(/*278.62*/lista1/*278.68*/.get(0)),format.raw/*278.75*/("""_"""),_display_(/*278.77*/lista1/*278.83*/.get(23)),format.raw/*278.91*/("""').submit()">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</form>
							</td>
						</TR>
		 			""")))}),format.raw/*284.8*/("""
				"""),format.raw/*285.5*/("""</tbody>
			</table>
		</div>
	</div>
	
	<form id="excel" class="formulario" method="post" action="/reportInventarioSelectivoXBodegaExcel/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*291.49*/bodega/*291.55*/.getId()),format.raw/*291.63*/("""">
		<input type="hidden" name="fechaCorte" value=""""),_display_(/*292.50*/fechaCorte),format.raw/*292.60*/("""">
		<input type="hidden" name="tipo" value=""""),_display_(/*293.44*/tipo),format.raw/*293.48*/("""">
	</form>
	
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

""")))}),format.raw/*317.2*/("""


"""),format.raw/*320.1*/("""<script type="text/javascript">

	let flag = true;
	
	$(document).ready(function() """),format.raw/*324.31*/("""{"""),format.raw/*324.32*/("""
		
		"""),format.raw/*326.3*/("""sumarTotales2();
        flag = false;

		 $('#tablaPrincipal').DataTable("""),format.raw/*329.35*/("""{"""),format.raw/*329.36*/("""
		    	"""),format.raw/*330.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 10, "asc" ]],
		    	"language": """),format.raw/*335.20*/("""{"""),format.raw/*335.21*/("""
		        	"""),format.raw/*336.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*337.11*/("""}"""),format.raw/*337.12*/("""
		  """),format.raw/*338.5*/("""}"""),format.raw/*338.6*/(""" """),format.raw/*338.7*/(""");
		  
		  $('a.toggle-vis').on('click', function (e) """),format.raw/*340.48*/("""{"""),format.raw/*340.49*/("""
		        """),format.raw/*341.11*/("""e.preventDefault();
		        // Get the column API object
		        let col = $(this).attr('data-column');
		        var column = $('#tablaPrincipal').DataTable().column(col);
		        // Toggle the visibility
		        column.visible(!column.visible());
		        if(column.visible())"""),format.raw/*347.31*/("""{"""),format.raw/*347.32*/("""
					"""),format.raw/*348.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#73C6B6";
				"""),format.raw/*349.5*/("""}"""),format.raw/*349.6*/("""else"""),format.raw/*349.10*/("""{"""),format.raw/*349.11*/("""
					"""),format.raw/*350.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#D6DBDF";
				"""),format.raw/*351.5*/("""}"""),format.raw/*351.6*/("""
		  """),format.raw/*352.5*/("""}"""),format.raw/*352.6*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*355.2*/("""}"""),format.raw/*355.3*/(""");
	
	function sumarTotales2() """),format.raw/*357.27*/("""{"""),format.raw/*357.28*/("""
		"""),format.raw/*358.3*/("""var tabla = document.getElementById("tablaPrincipal");
		if(flag)"""),format.raw/*359.11*/("""{"""),format.raw/*359.12*/("""
			"""),format.raw/*360.4*/("""var sumaGranCantidades = 0;
			var sumaGranTotalesC= 0;
			var sumaGranTotalesV= 0;
			var sumaGranTotalesKG= 0;
			var sumaGranTotalesM2= 0;
		"""),format.raw/*365.3*/("""}"""),format.raw/*365.4*/("""
		"""),format.raw/*366.3*/("""var cuentaItems = 0;
		var sumaCantidades = 0;
		var sumaCompraMO = 0;
		var sumaVentaMO = 0;
		var sumaCompraMP = 0;
		var sumaVentaMP = 0;
		var sumaKG = 0;
		var sumaM2 = 0;
		
		var numero = new DecimalFormat("#,##0.00");
			for (var i = 2; i < tabla.rows.length; i++) """),format.raw/*376.48*/("""{"""),format.raw/*376.49*/(""" 
				"""),format.raw/*377.5*/("""var aux = 0;
				if(flag)"""),format.raw/*378.13*/("""{"""),format.raw/*378.14*/("""
					"""),format.raw/*379.6*/("""aux = tabla.rows[i].cells[0].textContent;
					sumaGranCantidades = parseFloat(sumaGranCantidades) + parseFloat(numero.formatBack(aux));
					aux = tabla.rows[i].cells[3].textContent;
					sumaGranTotalesC = parseFloat(sumaGranTotalesC) + parseFloat(numero.formatBack(aux));
					aux = tabla.rows[i].cells[4].textContent;
					sumaGranTotalesV = parseFloat(sumaGranTotalesV) + parseFloat(numero.formatBack(aux));
					aux = tabla.rows[i].cells[5].textContent;
					sumaGranTotalesKG = parseFloat(sumaGranTotalesKG) + parseFloat(numero.formatBack(aux));
					aux = tabla.rows[i].cells[6].textContent;
					sumaGranTotalesM2 = parseFloat(sumaGranTotalesM2) + parseFloat(numero.formatBack(aux));
				"""),format.raw/*389.5*/("""}"""),format.raw/*389.6*/("""
				
				"""),format.raw/*391.5*/("""if(tabla.rows[i].style.display != "none")"""),format.raw/*391.46*/("""{"""),format.raw/*391.47*/("""
					"""),format.raw/*392.6*/("""cuentaItems++;
					var aux = tabla.rows[i].cells[0].textContent;
					sumaCantidades = parseFloat(sumaCantidades) + parseFloat(numero.formatBack(aux));
					aux = tabla.rows[i].cells[1].textContent;
					sumaCompraMO = parseFloat(sumaCompraMO) + parseFloat(numero.formatBack(aux));
					aux = tabla.rows[i].cells[2].textContent;
					sumaVentaMO = parseFloat(sumaVentaMO) + parseFloat(numero.formatBack(aux));
					aux = tabla.rows[i].cells[3].textContent;
					sumaCompraMP = parseFloat(sumaCompraMP) + parseFloat(numero.formatBack(aux));
					aux = tabla.rows[i].cells[4].textContent;
					sumaVentaMP = parseFloat(sumaVentaMP) + parseFloat(numero.formatBack(aux));
					aux = tabla.rows[i].cells[5].textContent;
					sumaKG = parseFloat(sumaKG) + parseFloat(numero.formatBack(aux));
					aux = tabla.rows[i].cells[6].textContent;
					sumaM2 = parseFloat(sumaM2) + parseFloat(numero.formatBack(aux));
				"""),format.raw/*407.5*/("""}"""),format.raw/*407.6*/("""
			"""),format.raw/*408.4*/("""}"""),format.raw/*408.5*/("""
		
		"""),format.raw/*410.3*/("""if('"""),_display_(/*410.8*/bodega/*410.14*/.getEsInterna()),format.raw/*410.29*/("""'=='2')"""),format.raw/*410.36*/("""{"""),format.raw/*410.37*/("""
			"""),format.raw/*411.4*/("""if(flag)"""),format.raw/*411.12*/("""{"""),format.raw/*411.13*/("""
				"""),format.raw/*412.5*/("""document.getElementById('totalCantidad').value=formatStandar0(sumaGranCantidades);
				document.getElementById('totalPesosC').value=formatStandar0(sumaGranTotalesC);
				document.getElementById('totalPesosV').value=formatStandar0(sumaGranTotalesV);
				document.getElementById('totalKG').value=formatStandar0(sumaGranTotalesKG);
				document.getElementById('totalM2').value=formatStandar0(sumaGranTotalesM2);
			"""),format.raw/*417.4*/("""}"""),format.raw/*417.5*/("""
			
			"""),format.raw/*419.4*/("""tabla.rows[0].cells[12].textContent = formatStandar0(cuentaItems);
			tabla.rows[0].cells[21].textContent = formatStandar0(sumaCantidades);
			tabla.rows[0].cells[22].textContent = formatStandar0(sumaCompraMO);
			tabla.rows[0].cells[23].textContent = formatStandar0(sumaVentaMO);
			tabla.rows[0].cells[24].textContent = formatStandar0(sumaCompraMP);
			tabla.rows[0].cells[25].textContent = formatStandar0(sumaVentaMP);
			tabla.rows[0].cells[26].textContent = formatStandar0(sumaKG);
			tabla.rows[0].cells[27].textContent = formatStandar0(sumaM2);
			
			
			
		"""),format.raw/*430.3*/("""}"""),format.raw/*430.4*/("""else"""),format.raw/*430.8*/("""{"""),format.raw/*430.9*/("""
			"""),format.raw/*431.4*/("""if(flag)"""),format.raw/*431.12*/("""{"""),format.raw/*431.13*/("""
				"""),format.raw/*432.5*/("""document.getElementById('totalCantidad').value=formatStandar0(sumaGranCantidades);
				document.getElementById('totalPesosC').value=formatStandar0(sumaGranTotalesC);
				document.getElementById('totalKG').value=formatStandar0(sumaGranTotalesKG);
				document.getElementById('totalM2').value=formatStandar0(sumaGranTotalesM2);
			"""),format.raw/*436.4*/("""}"""),format.raw/*436.5*/("""
			
			"""),format.raw/*438.4*/("""tabla.rows[0].cells[12].textContent = formatStandar0(cuentaItems);
			tabla.rows[0].cells[17].textContent = formatStandar0(sumaCantidades);
			tabla.rows[0].cells[18].textContent = formatStandar0(sumaCompraMO);
			tabla.rows[0].cells[19].textContent = formatStandar0(sumaCompraMP);
			tabla.rows[0].cells[20].textContent = formatStandar0(sumaKG);
			tabla.rows[0].cells[21].textContent = formatStandar0(sumaM2);
			
			
		"""),format.raw/*446.3*/("""}"""),format.raw/*446.4*/("""
		
		
	"""),format.raw/*449.2*/("""}"""),format.raw/*449.3*/("""
	
	"""),format.raw/*451.2*/("""const verCotizacion = (id_cotizacion) => """),format.raw/*451.43*/("""{"""),format.raw/*451.44*/("""
		"""),format.raw/*452.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*454.16*/("""{"""),format.raw/*454.17*/("""
            """),format.raw/*455.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*462.36*/("""{"""),format.raw/*462.37*/("""
	     		"""),format.raw/*463.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*465.8*/("""}"""),format.raw/*465.9*/("""
        """),format.raw/*466.9*/("""}"""),format.raw/*466.10*/(""");
	"""),format.raw/*467.2*/("""}"""),format.raw/*467.3*/("""
	
"""),format.raw/*469.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,lista:List[List[String]],fechaCorte:String,tipo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,lista,fechaCorte,tipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,lista,fechaCorte,tipo) => apply(mapDiccionario,mapPermiso,userMnu,bodega,lista,fechaCorte,tipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportInventarioSelectivoXBodega.scala.html
                  HASH: abd9cd74cb5e5fd6855ca6ab2533d914a63b1e28
                  MATRIX: 1086->1|1366->188|1399->196|1415->204|1454->206|1482->209|1550->257|1580->262|1624->286|1655->290|1732->341|2027->614|2060->620|4000->2533|4023->2547|4057->2560|4255->2731|4294->2732|4329->2740|4483->2867|4506->2881|4540->2894|4731->3042|4764->3048|5982->4238|6022->4239|6056->4245|6192->4352|6217->4366|6251->4377|6522->4619|6547->4633|6581->4644|6730->4764|6755->4778|6794->4794|7431->5402|7456->5416|7492->5429|7644->5552|7669->5566|7705->5579|8125->5979|8165->5980|8199->5986|8695->6453|8720->6467|8756->6480|9183->6876|9219->6884|10389->8026|10429->8027|10465->8035|10801->8327|10837->8335|11131->8601|11171->8602|11207->8610|11338->8697|11374->8705|11474->8777|11498->8791|11534->8804|11604->8846|11644->8847|11680->8855|11779->8926|11803->8940|11839->8953|11897->8967|11933->8975|13203->10217|13243->10218|13279->10226|13584->10503|13608->10517|13646->10533|13703->10546|13739->10554|14008->10795|14048->10796|14084->10804|14206->10882|14242->10890|14333->10953|14357->10967|14392->10980|14462->11022|14502->11023|14538->11031|14628->11093|14652->11107|14687->11120|14745->11134|14781->11142|15158->11492|15195->11512|15235->11513|15270->11520|15322->11544|15338->11550|15368->11558|15421->11583|15437->11589|15467->11597|15537->11639|15577->11640|15614->11649|15654->11661|15670->11667|15700->11675|15738->11693|15778->11694|15815->11703|15872->11729|15908->11737|15948->11749|15964->11755|15994->11763|16064->11805|16104->11806|16141->11815|16181->11827|16197->11833|16227->11841|16265->11859|16305->11860|16342->11869|16399->11895|16435->11903|16475->11915|16491->11921|16521->11929|16574->11954|16590->11960|16620->11968|16727->12047|16743->12053|16772->12060|16865->12125|16881->12131|16911->12139|17045->12245|17085->12246|17124->12257|17140->12263|17170->12271|17204->12285|17244->12286|17283->12296|17348->12333|17364->12339|17394->12347|17521->12446|17537->12452|17567->12460|17648->12509|17684->12517|17818->12622|17835->12628|17865->12635|17900->12641|17917->12647|17947->12654|18085->12763|18102->12769|18132->12776|18167->12782|18184->12788|18214->12795|18320->12873|18336->12879|18366->12887|18460->12953|18476->12959|18506->12967|18609->13042|18625->13048|18654->13055|18748->13121|18764->13127|18793->13134|18863->13176|18903->13177|18940->13186|19022->13240|19038->13246|19067->13253|19162->13320|19178->13326|19207->13333|19303->13401|19319->13407|19349->13415|19444->13482|19460->13488|19490->13496|19548->13510|19584->13518|19666->13572|19682->13578|19712->13586|19806->13652|19822->13658|19852->13666|19946->13732|19962->13738|19992->13746|20062->13788|20102->13789|20139->13798|20220->13851|20236->13857|20266->13865|20324->13879|20360->13887|20441->13940|20457->13946|20487->13954|20557->13996|20597->13997|20634->14006|20715->14059|20731->14065|20761->14073|20819->14087|20863->14103|20944->14156|20960->14162|20990->14170|21084->14236|21100->14242|21130->14250|21232->14324|21248->14330|21278->14338|21417->14449|21433->14455|21462->14462|21492->14464|21508->14470|21538->14478|21705->14617|21721->14623|21751->14631|21837->14689|21853->14695|21882->14702|21972->14764|21988->14770|22018->14778|22099->14831|22125->14835|22217->14899|22233->14905|22262->14912|22292->14914|22308->14920|22338->14928|22507->15066|22540->15071|22757->15260|22773->15266|22803->15274|22883->15326|22915->15336|22989->15382|23015->15386|23891->16231|23922->16234|24034->16317|24064->16318|24098->16324|24201->16398|24231->16399|24267->16407|24436->16547|24466->16548|24507->16560|24614->16638|24644->16639|24677->16644|24706->16645|24735->16646|24819->16701|24849->16702|24889->16713|25205->17000|25235->17001|25269->17007|25371->17081|25400->17082|25433->17086|25463->17087|25497->17093|25599->17167|25628->17168|25661->17173|25690->17174|25791->17247|25820->17248|25880->17279|25910->17280|25941->17283|26035->17348|26065->17349|26097->17353|26269->17497|26298->17498|26329->17501|26631->17774|26661->17775|26695->17781|26749->17806|26779->17807|26813->17813|27538->18510|27567->18511|27605->18521|27675->18562|27705->18563|27739->18569|28676->19478|28705->19479|28737->19483|28766->19484|28800->19490|28832->19495|28848->19501|28885->19516|28921->19523|28951->19524|28983->19528|29020->19536|29050->19537|29083->19542|29523->19954|29552->19955|29588->19963|30182->20529|30211->20530|30243->20534|30272->20535|30304->20539|30341->20547|30371->20548|30404->20553|30761->20882|30790->20883|30826->20891|31276->21313|31305->21314|31341->21322|31370->21323|31402->21327|31472->21368|31502->21369|31533->21372|31660->21470|31690->21471|31732->21484|31991->21714|32021->21715|32058->21724|32211->21849|32240->21850|32277->21859|32307->21860|32339->21864|32368->21865|32399->21868
                  LINES: 28->1|34->3|36->5|36->5|36->5|37->6|37->6|39->8|39->8|41->10|42->11|43->12|45->14|100->69|100->69|100->69|106->75|106->75|107->76|109->78|109->78|109->78|115->84|116->85|133->102|133->102|134->103|134->103|134->103|134->103|136->105|136->105|136->105|137->106|137->106|137->106|142->111|142->111|142->111|143->112|143->112|143->112|147->116|147->116|148->117|151->120|151->120|151->120|155->124|157->126|180->149|180->149|181->150|185->154|186->155|189->158|189->158|190->159|191->160|192->161|192->161|192->161|192->161|193->162|193->162|194->163|194->163|194->163|194->163|195->164|196->165|219->188|219->188|220->189|223->192|223->192|223->192|224->193|225->194|228->197|228->197|229->198|230->199|231->200|231->200|231->200|231->200|232->201|232->201|233->202|233->202|233->202|233->202|234->203|235->204|242->211|242->211|242->211|243->212|244->213|244->213|244->213|245->214|245->214|245->214|246->215|246->215|247->216|247->216|247->216|247->216|248->217|248->217|249->218|250->219|251->220|251->220|251->220|251->220|252->221|252->221|253->222|253->222|253->222|253->222|254->223|254->223|255->224|256->225|257->226|257->226|257->226|257->226|258->227|258->227|258->227|261->230|261->230|261->230|262->231|262->231|262->231|264->233|264->233|265->234|265->234|265->234|266->235|266->235|267->236|267->236|267->236|267->236|269->238|269->238|269->238|272->241|273->242|274->243|274->243|274->243|274->243|274->243|274->243|275->244|275->244|275->244|275->244|275->244|275->244|277->246|277->246|277->246|278->247|278->247|278->247|280->249|280->249|280->249|281->250|281->250|281->250|282->251|282->251|283->252|283->252|283->252|283->252|284->253|284->253|284->253|285->254|285->254|285->254|286->255|286->255|286->255|287->256|288->257|288->257|288->257|288->257|289->258|289->258|289->258|290->259|290->259|290->259|291->260|291->260|292->261|292->261|292->261|292->261|293->262|294->263|294->263|294->263|294->263|295->264|295->264|296->265|296->265|296->265|296->265|297->266|299->268|299->268|299->268|299->268|300->269|300->269|300->269|302->271|302->271|302->271|304->273|304->273|304->273|304->273|304->273|304->273|305->274|305->274|305->274|306->275|306->275|306->275|307->276|307->276|307->276|308->277|308->277|309->278|309->278|309->278|309->278|309->278|309->278|315->284|316->285|322->291|322->291|322->291|323->292|323->292|324->293|324->293|348->317|351->320|355->324|355->324|357->326|360->329|360->329|361->330|366->335|366->335|367->336|368->337|368->337|369->338|369->338|369->338|371->340|371->340|372->341|378->347|378->347|379->348|380->349|380->349|380->349|380->349|381->350|382->351|382->351|383->352|383->352|386->355|386->355|388->357|388->357|389->358|390->359|390->359|391->360|396->365|396->365|397->366|407->376|407->376|408->377|409->378|409->378|410->379|420->389|420->389|422->391|422->391|422->391|423->392|438->407|438->407|439->408|439->408|441->410|441->410|441->410|441->410|441->410|441->410|442->411|442->411|442->411|443->412|448->417|448->417|450->419|461->430|461->430|461->430|461->430|462->431|462->431|462->431|463->432|467->436|467->436|469->438|477->446|477->446|480->449|480->449|482->451|482->451|482->451|483->452|485->454|485->454|486->455|493->462|493->462|494->463|496->465|496->465|497->466|497->466|498->467|498->467|500->469
                  -- GENERATED --
              */
          