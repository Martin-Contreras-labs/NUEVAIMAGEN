
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

object ajustesEpRptDetalleOdo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.AjustesEpOdo],tables.BodegaEmpresa,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listAjustes: List[tables.AjustesEpOdo], bodegaEmpresa: tables.BodegaEmpresa):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "REPORTE AJUSTES A EP/PROFORMAS SERVICIOS A: "+bodegaEmpresa.nombre,"")),format.raw/*9.103*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
					        <th style="text-align:center; min-width:80px;">FECHA</th>
					        <th style="text-align:center;">TIPO AJUSTE</th>
					        <th style="text-align:center;">MONEDA</th>
					        <th style="text-align:center;">VALOR</th>
					        <th style="text-align:center;">CONCEPTO</th>
					        <th style="text-align:center;">NOTAS</th>
							<TH style= "text-align: center;">PDF<br>ADJUNTO</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*26.8*/for(lista1 <- listAjustes) yield /*26.34*/{_display_(Seq[Any](format.raw/*26.35*/("""
							"""),format.raw/*27.8*/("""<TR>
								<td style="text-align:center;">
									<div style="display:none">"""),_display_(/*29.37*/utilities/*29.46*/.Fechas.AAMMDD(lista1.fechaAjuste)),format.raw/*29.80*/("""</div>
									"""),_display_(/*30.11*/lista1/*30.17*/.fechaAjuste),format.raw/*30.29*/("""
								"""),format.raw/*31.9*/("""</td>
								<td style="text-align:center;">"""),_display_(/*32.41*/lista1/*32.47*/.tipoAjuste),format.raw/*32.58*/("""</td>
								<td style="text-align:center;">"""),_display_(/*33.41*/lista1/*33.47*/.moneda),format.raw/*33.54*/("""</td>
								<td style="text-align:right;">"""),_display_(/*34.40*/lista1/*34.46*/.totalAjuste),format.raw/*34.58*/("""</td>
								<td style="text-align:left;">"""),_display_(/*35.39*/lista1/*35.45*/.concepto),format.raw/*35.54*/("""</td>
								<td style="text-align:left;">"""),_display_(/*36.39*/lista1/*36.45*/.observaciones),format.raw/*36.59*/("""</td>
								<td style= "text-align: center;vertical-align: middle;">
									"""),_display_(if(lista1.ajustePDF.equals("0"))/*38.43*/{_display_(Seq[Any](format.raw/*38.44*/("""
										"""),format.raw/*39.11*/("""<div id="actualizarPDF"""),_display_(/*39.34*/lista1/*39.40*/.id),format.raw/*39.43*/("""">
											--
										</div>
									""")))}else/*42.15*/{_display_(Seq[Any](format.raw/*42.16*/("""
										"""),format.raw/*43.11*/("""<a href="#" onclick="mostrarPDF('"""),_display_(/*43.45*/lista1/*43.51*/.ajustePDF),format.raw/*43.61*/("""')">
											<kbd style="background-color: #85C1E9">pdf</kbd>
										</a>
									""")))}),format.raw/*46.11*/("""
								"""),format.raw/*47.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*49.9*/("""
					"""),format.raw/*50.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="VOLVER" class="btn btn-success btn-sm rounded-pill btn-block" onclick="window.history.back()">
				</div>
			</div>
		</div>
	</div>
	
	<div id='muestraPDF' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>AJUSTE EP/PROFORMA</h5>
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
	
""")))}),format.raw/*84.2*/("""


"""),format.raw/*87.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*88.31*/("""{"""),format.raw/*88.32*/("""
		  """),format.raw/*89.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*89.36*/("""{"""),format.raw/*89.37*/("""
		    	"""),format.raw/*90.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[25, 50, 100, 200, -1], [25, 50, 100, 200, "All"]],
		    	"order": [[ 0, "desc" ]],
		    	"language": """),format.raw/*93.20*/("""{"""),format.raw/*93.21*/("""
		        	"""),format.raw/*94.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*95.11*/("""}"""),format.raw/*95.12*/("""
		  """),format.raw/*96.5*/("""}"""),format.raw/*96.6*/(""" """),format.raw/*96.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*98.2*/("""}"""),format.raw/*98.3*/(""");
	
	const mostrarPDF = (nombrePDF) => """),format.raw/*100.36*/("""{"""),format.raw/*100.37*/("""
		  """),format.raw/*101.5*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+nombrePDF+"' type='application/pdf' width='100%' height='720'></object>";
		  $('#muestraPDF').modal('show');
	"""),format.raw/*103.2*/("""}"""),format.raw/*103.3*/("""
	
	
"""),format.raw/*106.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listAjustes:List[tables.AjustesEpOdo],bodegaEmpresa:tables.BodegaEmpresa): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listAjustes,bodegaEmpresa)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.AjustesEpOdo],tables.BodegaEmpresa) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listAjustes,bodegaEmpresa) => apply(mapDiccionario,mapPermiso,userMnu,listAjustes,bodegaEmpresa)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/ajustesEpRptDetalleOdo.scala.html
                  HASH: 523b55409505a6a8009bd8cd71e04255c631f409
                  MATRIX: 1064->1|1331->175|1364->183|1380->191|1419->193|1447->196|1515->244|1543->246|1619->297|1739->396|1769->399|2533->1137|2575->1163|2614->1164|2649->1172|2757->1253|2775->1262|2830->1296|2874->1313|2889->1319|2922->1331|2958->1340|3031->1386|3046->1392|3078->1403|3151->1449|3166->1455|3194->1462|3266->1507|3281->1513|3314->1525|3385->1569|3400->1575|3430->1584|3501->1628|3516->1634|3551->1648|3691->1761|3730->1762|3769->1773|3819->1796|3834->1802|3858->1805|3925->1853|3964->1854|4003->1865|4064->1899|4079->1905|4110->1915|4231->2005|4267->2014|4324->2041|4357->2047|5517->3177|5547->3180|5637->3242|5666->3243|5698->3248|5757->3279|5786->3280|5821->3288|5996->3435|6025->3436|6065->3448|6171->3526|6200->3527|6232->3532|6260->3533|6288->3534|6388->3607|6416->3608|6485->3648|6515->3649|6548->3654|6758->3836|6787->3837|6820->3842
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|57->26|57->26|57->26|58->27|60->29|60->29|60->29|61->30|61->30|61->30|62->31|63->32|63->32|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|69->38|69->38|70->39|70->39|70->39|70->39|73->42|73->42|74->43|74->43|74->43|74->43|77->46|78->47|80->49|81->50|115->84|118->87|119->88|119->88|120->89|120->89|120->89|121->90|124->93|124->93|125->94|126->95|126->95|127->96|127->96|127->96|129->98|129->98|131->100|131->100|132->101|134->103|134->103|137->106
                  -- GENERATED --
              */
          