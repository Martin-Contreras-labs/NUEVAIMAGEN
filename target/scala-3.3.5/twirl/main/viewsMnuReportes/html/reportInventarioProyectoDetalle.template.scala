
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

object reportInventarioProyectoDetalle extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""
	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalEquipoDescripcion()),format.raw/*8.27*/("""
	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, bodega.nombreTipoBodega + ": " + bodega.nombre.toUpperCase() + " - PROYECTO: "+bodega.nickProyecto, 
			"EXISTENCIAS POR "+mapDiccionario.get("BODEGA")+"/PROYECTO")),format.raw/*12.64*/("""
		
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
								onkeyup="doSearch3('tablaPrincipal');">
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
						
					</td>
				</tr>
			</table>
		</div>
		
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
					<TR> 
							<TH rowspan="2" style= "text-align: center;">GRUPO/FAMILIA</TH>
							<TH rowspan="2" style= "text-align: center;">Nro.Coti</TH>
							<TH rowspan="2" style= "text-align: center;">CODIGO</TH>
							<TH rowspan="2" style= "text-align: center;">EQUIPO</TH>
							<TH rowspan="2" style= "text-align: center;">UNIDAD</TH>
							<TH rowspan="2" style= "text-align: center;">CANTIDAD</TH>
							<TH rowspan="2" style= "text-align: center;">MONEDA</TH>
							<TH colspan="2" style= "text-align: center;">PRECIO<br>REPOSICION</TH>
							<TH colspan="3" style= "text-align: center;">PRECIO<br>"""),_display_(/*61.64*/mapDiccionario/*61.78*/.get("ARRIENDO")),format.raw/*61.94*/("""</TH>
							<TH rowspan="2" style= "text-align: center;vertical-align: top;width:4%">VER</TH>
					</TR>
					<TR> 
							<TH style= "text-align: center;">P.UNITARIO</TH>
							<TH style= "text-align: center;">P.TOTAL</TH>
							<TH style= "text-align: center;">UNIDAD</TH>
							<TH style= "text-align: center;">P.UNITARIO</TH>
							<TH style= "text-align: center;">P.TOTAL</TH>
					</TR>
				</thead>
				<tbody>
					"""),_display_(/*73.7*/for(lista1 <- lista) yield /*73.27*/{_display_(Seq[Any](format.raw/*73.28*/("""
						"""),format.raw/*74.7*/("""<TR>
							<td style="text-align:left; vertical-align:middle;">"""),_display_(/*75.61*/lista1/*75.67*/.get(4)),format.raw/*75.74*/("""</td>
							<td style="text-align:center; vertical-align:middle;">
								"""),_display_(if(lista1.get(18).equals("0"))/*77.40*/{_display_(Seq[Any](format.raw/*77.41*/("""
									"""),_display_(/*78.11*/lista1/*78.17*/.get(18)),format.raw/*78.25*/("""
								""")))}else/*79.14*/{_display_(Seq[Any](format.raw/*79.15*/("""
									"""),format.raw/*80.10*/("""<a href="#" onclick="verCotizacion('"""),_display_(/*80.47*/lista1/*80.53*/.get(17)),format.raw/*80.61*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*82.34*/lista1/*82.40*/.get(18)),format.raw/*82.48*/("""</font>
											</kbd>
									</a>
								""")))}),format.raw/*85.10*/("""
							"""),format.raw/*86.8*/("""</td>
							<td style="text-align:center; vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*87.103*/lista1/*87.109*/.get(2)),format.raw/*87.116*/("""');">"""),_display_(/*87.122*/lista1/*87.128*/.get(6)),format.raw/*87.135*/("""</a></td>
							<td style="text-align:left; vertical-align:middle; max-width:250px"><a href="#" onclick="equipoDescripcion('"""),_display_(/*88.117*/lista1/*88.123*/.get(2)),format.raw/*88.130*/("""');">"""),_display_(/*88.136*/lista1/*88.142*/.get(7)),format.raw/*88.149*/("""</a></td>
							<td style="text-align:center; vertical-align:middle;">"""),_display_(/*89.63*/lista1/*89.69*/.get(8)),format.raw/*89.76*/("""</td>
							<td style="text-align:right; vertical-align:middle;">"""),_display_(/*90.62*/lista1/*90.68*/.get(9)),format.raw/*90.75*/("""</td>
							<td style="text-align:center; vertical-align:middle;">"""),_display_(/*91.63*/lista1/*91.69*/.get(10)),format.raw/*91.77*/("""</td>
							<td style="text-align:right; vertical-align:middle;">"""),_display_(/*92.62*/lista1/*92.68*/.get(11)),format.raw/*92.76*/("""</td>
							<td style="text-align:right; vertical-align:middle;">"""),_display_(/*93.62*/lista1/*93.68*/.get(12)),format.raw/*93.76*/("""</td>
							
							<td style="text-align:right; vertical-align:middle;">"""),_display_(/*95.62*/lista1/*95.68*/.get(14)),format.raw/*95.76*/("""</td>
							<td style="text-align:right; vertical-align:middle;">"""),_display_(/*96.62*/lista1/*96.68*/.get(15)),format.raw/*96.76*/("""</td>
							<td style="text-align:right; vertical-align:middle;">"""),_display_(/*97.62*/lista1/*97.68*/.get(16)),format.raw/*97.76*/("""</td>
							<td style="text-align:center; vertical-align:middle;">
								<form id="form_"""),_display_(/*99.25*/lista1/*99.31*/.get(2)),format.raw/*99.38*/("""_"""),_display_(/*99.40*/lista1/*99.46*/.get(17)),format.raw/*99.54*/("""" class="formulario" method="post" action="/reportInventProyectoTrazaEquipoEnBod/">
									<input type="hidden" name="id_bodega" value=""""),_display_(/*100.56*/bodega/*100.62*/.getId()),format.raw/*100.70*/("""">
									<input type="hidden" name="id_equipo" value=""""),_display_(/*101.56*/lista1/*101.62*/.get(2)),format.raw/*101.69*/("""">
									<input type="hidden" name="id_cotizacion" value=""""),_display_(/*102.60*/lista1/*102.66*/.get(17)),format.raw/*102.74*/("""">
									<input type="hidden" name="tipo" value="TODO">
									<a href="#" onclick="document.getElementById('form_"""),_display_(/*104.62*/lista1/*104.68*/.get(2)),format.raw/*104.75*/("""_"""),_display_(/*104.77*/lista1/*104.83*/.get(17)),format.raw/*104.91*/("""').submit()">
										<kbd style="background-color: #73C6B6">select</kbd>
									</a>
								</form>
							</td>
						</TR>
		 			""")))}),format.raw/*110.8*/("""
				"""),format.raw/*111.5*/("""</tbody>
			</table>
		</div>
	</div>
	
	<form id="excel" class="formulario" method="post" action="/reportInventarioProyectoDetalleExcel/">
		<input type="hidden" name="id_bodega" value=""""),_display_(/*117.49*/bodega/*117.55*/.getId()),format.raw/*117.63*/("""">
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

""")))}),format.raw/*141.2*/("""


"""),format.raw/*144.1*/("""<script type="text/javascript">

	let flag = true;
	
	$(document).ready(function() """),format.raw/*148.31*/("""{"""),format.raw/*148.32*/("""

		 """),format.raw/*150.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*150.35*/("""{"""),format.raw/*150.36*/("""
		    	"""),format.raw/*151.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 3, "asc" ]],
		    	"language": """),format.raw/*156.20*/("""{"""),format.raw/*156.21*/("""
		        	"""),format.raw/*157.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*158.11*/("""}"""),format.raw/*158.12*/("""
		  """),format.raw/*159.5*/("""}"""),format.raw/*159.6*/(""" """),format.raw/*159.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*162.2*/("""}"""),format.raw/*162.3*/(""");
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*164.43*/("""{"""),format.raw/*164.44*/("""
		"""),format.raw/*165.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*167.16*/("""{"""),format.raw/*167.17*/("""
            """),format.raw/*168.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*175.36*/("""{"""),format.raw/*175.37*/("""
	     		"""),format.raw/*176.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*178.8*/("""}"""),format.raw/*178.9*/("""
        """),format.raw/*179.9*/("""}"""),format.raw/*179.10*/(""");
	"""),format.raw/*180.2*/("""}"""),format.raw/*180.3*/("""
	
"""),format.raw/*182.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,lista:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,lista)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,lista) => apply(mapDiccionario,mapPermiso,userMnu,bodega,lista)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportInventarioProyectoDetalle.scala.html
                  HASH: 86354841b32a9e15308990d605c2a81f0cb895b0
                  MATRIX: 1071->1|1318->155|1351->163|1367->171|1406->173|1434->176|1502->224|1532->229|1576->253|1607->257|1684->308|1897->500|1930->506|3797->2346|3820->2360|3857->2376|4312->2805|4348->2825|4387->2826|4421->2833|4513->2898|4528->2904|4556->2911|4690->3018|4729->3019|4767->3030|4782->3036|4811->3044|4844->3058|4883->3059|4921->3069|4985->3106|5000->3112|5029->3120|5155->3219|5170->3225|5199->3233|5279->3282|5314->3290|5450->3398|5466->3404|5495->3411|5529->3417|5545->3423|5574->3430|5728->3556|5744->3562|5773->3569|5807->3575|5823->3581|5852->3588|5951->3660|5966->3666|5994->3673|6088->3740|6103->3746|6131->3753|6226->3821|6241->3827|6270->3835|6364->3902|6379->3908|6408->3916|6502->3983|6517->3989|6546->3997|6648->4072|6663->4078|6692->4086|6786->4153|6801->4159|6830->4167|6924->4234|6939->4240|6968->4248|7087->4340|7102->4346|7130->4353|7159->4355|7174->4361|7203->4369|7370->4508|7386->4514|7416->4522|7502->4580|7518->4586|7547->4593|7637->4655|7653->4661|7683->4669|7831->4789|7847->4795|7876->4802|7906->4804|7922->4810|7952->4818|8121->4956|8154->4961|8370->5149|8386->5155|8416->5163|9292->6008|9323->6011|9435->6094|9465->6095|9498->6100|9558->6131|9588->6132|9624->6140|9792->6279|9822->6280|9863->6292|9970->6370|10000->6371|10033->6376|10062->6377|10091->6378|10192->6451|10221->6452|10297->6499|10327->6500|10358->6503|10485->6601|10515->6602|10557->6615|10816->6845|10846->6846|10883->6855|11036->6980|11065->6981|11102->6990|11132->6991|11164->6995|11193->6996|11224->6999
                  LINES: 28->1|34->3|36->5|36->5|36->5|37->6|37->6|39->8|39->8|41->10|42->11|43->12|45->14|92->61|92->61|92->61|104->73|104->73|104->73|105->74|106->75|106->75|106->75|108->77|108->77|109->78|109->78|109->78|110->79|110->79|111->80|111->80|111->80|111->80|113->82|113->82|113->82|116->85|117->86|118->87|118->87|118->87|118->87|118->87|118->87|119->88|119->88|119->88|119->88|119->88|119->88|120->89|120->89|120->89|121->90|121->90|121->90|122->91|122->91|122->91|123->92|123->92|123->92|124->93|124->93|124->93|126->95|126->95|126->95|127->96|127->96|127->96|128->97|128->97|128->97|130->99|130->99|130->99|130->99|130->99|130->99|131->100|131->100|131->100|132->101|132->101|132->101|133->102|133->102|133->102|135->104|135->104|135->104|135->104|135->104|135->104|141->110|142->111|148->117|148->117|148->117|172->141|175->144|179->148|179->148|181->150|181->150|181->150|182->151|187->156|187->156|188->157|189->158|189->158|190->159|190->159|190->159|193->162|193->162|195->164|195->164|196->165|198->167|198->167|199->168|206->175|206->175|207->176|209->178|209->178|210->179|210->179|211->180|211->180|213->182
                  -- GENERATED --
              */
          