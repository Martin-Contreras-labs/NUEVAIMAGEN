
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

object movimientoOrigenDestinoMultiple extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],Long,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodegaOrigen: tables.BodegaEmpresa, listBodegasDestino: List[List[String]],
nuevoNumeroGuia: Long, hoy: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

	"""),_display_(/*8.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.51*/("""
	
	"""),format.raw/*10.2*/("""<div id="enCarga" class="blocker" style="display: none;"><br><br><br><br><br><br><h1>Ahora se esta cargando.....</h1></div>
	
	"""),_display_(/*12.3*/modalEquipoDescripcion()),format.raw/*12.27*/("""
	"""),_display_(/*13.3*/modalVerCotizacion()),format.raw/*13.23*/("""
	
	"""),format.raw/*15.2*/("""<script>
		let auxBueno = -1;
		let auxIndex = -1;
	</script>
	
	<form id="enviarForm" action="/movimientoOrigenDestinoMultiple1/" enctype="multipart/form-data" method="POST">
		<div id="mostrarmostrar" style="display:none;">
			"""),_display_(/*22.5*/barraTitulo(mapDiccionario, "MOVIMIENTOS ENTRE "+mapDiccionario.get("BODEGA")+" Y/O PROYECTOS", "")),format.raw/*22.104*/("""
			"""),format.raw/*23.4*/("""<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<tr>
								<td width="25%">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Origen</span>
								  		</div>
								  		<input type="hidden" name="id_bodegaOrigen" value=""""),_display_(/*33.65*/bodegaOrigen/*33.77*/.getId()),format.raw/*33.85*/("""">
	  									<input type="text" class="form-control" 
	  										value=""""),_display_(/*35.22*/bodegaOrigen/*35.34*/.getNombre().toUpperCase),format.raw/*35.58*/("""" 
	  										readonly>
									</div>
								</td>
								<td width="25%">
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Destino</span>
								  		</div>
	  										<input type="hidden" name="id_bodegaDestino" id="id_bodegaDestino">
											<input type="text" class="form-control left"
												id="nombreBodegaDestino"
												onclick='$("#listaBodegasDestino").modal("show")'
												style="background:white"
												required
												readonly>
									</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Mov</span>
								  		</div>
											<input type="text" class="form-control left"
									  				name="numeroGuia"
													id="numeroGuia"
													value = """"),_display_(/*61.24*/nuevoNumeroGuia),format.raw/*61.39*/(""""
													readonly
													required>
									</div>
								</td>
								<td>
									<div class="input-group">
								  		<div class="input-group-prepend">
								    		<span class="input-group-text" id="basic-addon1">Nro.Ref</span>
								  		</div>
								  		<input type="text" class="form-control left"
								  				name="numGuiaCliente"
								  				autocomplete="off"
								  				maxlength="50"
												readonly>
									</div>
								</td>


								<td rowspan="2" style="width: 50px; min-width: 50px; max-width: 50px; text-align: center; vertical-align: middle">
									<span class="btn btn-info btn-sm btn-file" style="font-size: 10px; display: inline-block">
										<input type="hidden" name="docAnexo" value="0">
										<div id="txtBtn">Subir Documento</div>
										<input type="file" multiple id="docAdjunto" name="docAdjunto[]" title="Adjuntar documentos"
											onchange="LimitAttach(this.form, this.form.docAdjunto.value);" disabled>
									</span>
								</td>

							</tr>


							<tr>
								<td align="center">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Sucursal</span>
										</div>
										<input type="text" class="form-control left"
											   value = """"),_display_(/*99.25*/bodegaOrigen/*99.37*/.getNameSucursal()),format.raw/*99.55*/(""""
											   readonly>
									</div>
								</td>
								<td align="center">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Sucursal</span>
										</div>
										<input type="text" id="nameSucursalDestino" class="form-control left" readonly>
									</div>
								</td>

								<td width="230px">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Fecha Movimiento</span>
										</div>
										<input type="date" class="form-control center"
										name="fechaGuia"
										id="fechaGuia"
										onblur="if(!limitaFecha(value,"""),_display_(/*120.42*/mapPermiso/*120.52*/.get("parametro.diasMenosGuia")),format.raw/*120.83*/(""","""),_display_(/*120.85*/mapPermiso/*120.95*/.get("parametro.diasMasGuia")),format.raw/*120.124*/(""")) value='"""),_display_(/*120.135*/hoy),format.raw/*120.138*/("""';"
										value=""""),_display_(/*121.19*/hoy),format.raw/*121.22*/(""""
										readonly
										required>
									</div>
								</td>
								<td width="230px">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Fecha Ini/Ter</span>
										</div>
										<input type="date" class="form-control center"
										name="fechaIniTerGuia"
										id="fechaIniTerGuia"
										onblur="if(!limitaFecha(value,"""),_display_(/*134.42*/mapPermiso/*134.52*/.get("parametro.diasMenosIniTer")),format.raw/*134.85*/(""","""),_display_(/*134.87*/mapPermiso/*134.97*/.get("parametro.diasMasIniTer")),format.raw/*134.128*/(""")) value='"""),_display_(/*134.139*/hoy),format.raw/*134.142*/("""';"
										value=""""),_display_(/*135.19*/hoy),format.raw/*135.22*/(""""
										readonly
										required>
									</div>
								</td>



							</tr>
							<tr>
								<td align="center">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Comercial</span>
										</div>
										<input type="text" class="form-control left"
											   id = "nameComercial"
											   value = """"),_display_(/*152.25*/bodegaOrigen/*152.37*/.getNameComercial()),format.raw/*152.56*/(""""
											   readonly>
									</div>
								</td>
								<td align="center">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Cliente</span>
										</div>
										<input type="text" class="form-control left"
											   id = "nickCliente"
											   value = ""
											   readonly>
									</div>
								</td>
								<td colspan="20" rowspan="3">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="basic-addon1">Observaciones</span>
										</div>
										<textarea class="form-control" rows="2"
										name="observaciones"
										autocomplete="off" readonly></textarea>
									</div>
								</td>
							</tr>

						</thead>
					</table>
				</div>
			</div>
		</div>
	</form>
	
	<div id='listaBodegasDestino' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>SELECCIONAR """),_display_(/*190.43*/mapDiccionario/*190.57*/.get("BODEGA")),format.raw/*190.71*/("""/PROYECTO DE DESTINO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<table id="tablaListaBodegasDestino" class='table table-sm table-bordered table-condensed table-hover table-fluid'>
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>SUCURSAL</TH>
								<TH>TIPO</TH>
								<TH>"""),_display_(/*201.14*/mapDiccionario/*201.28*/.get("BODEGA")),format.raw/*201.42*/("""/PROYECTO</TH>
								<TH>NOMBRE CLIENTE O PROPIETARIO</TH>
					        	<TH>NOMBRE DEL PROYECTO</TH>
					        	<TH>"""),_display_(/*204.20*/mapDiccionario/*204.34*/.get("COMUNA")),format.raw/*204.48*/(""" """),format.raw/*204.49*/("""PROYECTO</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*208.9*/for(lista1 <- listBodegasDestino) yield /*208.42*/{_display_(Seq[Any](format.raw/*208.43*/("""
								"""),format.raw/*209.9*/("""<TR onClick="seleccionaBodegaDestino('"""),_display_(/*209.48*/lista1/*209.54*/.get(1)),format.raw/*209.61*/("""','"""),_display_(/*209.65*/lista1/*209.71*/.get(17)),format.raw/*209.79*/("""')" data-dismiss="modal">
									<td  style="text-align:left;"><div id="sucursal_"""),_display_(/*210.59*/lista1/*210.65*/.get(1)),format.raw/*210.72*/("""">"""),_display_(/*210.75*/lista1/*210.81*/.get(16)),format.raw/*210.89*/("""</div></td>
									<td  style="text-align:left;">"""),_display_(/*211.41*/lista1/*211.47*/.get(4)),format.raw/*211.54*/("""</td>
									<td  style="text-align:left;"><div id="bodega_"""),_display_(/*212.57*/lista1/*212.63*/.get(1)),format.raw/*212.70*/("""">"""),_display_(/*212.73*/lista1/*212.79*/.get(5).toUpperCase()),format.raw/*212.100*/("""</div></td>
									<td  style="text-align:left;">"""),_display_(/*213.41*/lista1/*213.47*/.get(7)),format.raw/*213.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*214.41*/lista1/*214.47*/.get(8)),format.raw/*214.54*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*215.41*/lista1/*215.47*/.get(9)),format.raw/*215.54*/("""</td>
								</TR>
				 			""")))}),format.raw/*217.10*/("""
						"""),format.raw/*218.7*/("""</tbody>
					</table>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-warning' style='font-size: 10px;' data-dismiss='modal'>Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
""")))}),format.raw/*231.2*/("""



"""),format.raw/*235.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*237.31*/("""{"""),format.raw/*237.32*/("""
		  """),format.raw/*238.5*/("""$('#tablaListaBodegasDestino').DataTable("""),format.raw/*238.46*/("""{"""),format.raw/*238.47*/("""
		    	"""),format.raw/*239.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
				"order": [[ 1, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*242.20*/("""{"""),format.raw/*242.21*/("""
		        	"""),format.raw/*243.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*244.11*/("""}"""),format.raw/*244.12*/("""
		  """),format.raw/*245.5*/("""}"""),format.raw/*245.6*/(""" """),format.raw/*245.7*/(""");

		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*248.2*/("""}"""),format.raw/*248.3*/(""");
	
	const seleccionaBodegaDestino = (id_bodegaDestino, id_sucursal) =>"""),format.raw/*250.68*/("""{"""),format.raw/*250.69*/("""
		"""),format.raw/*251.3*/("""let nameBodega = $("#bodega_"+id_bodegaDestino).text();
		$("#nombreBodegaDestino").val(nameBodega);
		$("#id_bodegaDestino").val(id_bodegaDestino);
		document.getElementById('enviarForm').submit();

	"""),format.raw/*256.2*/("""}"""),format.raw/*256.3*/("""
	
"""),format.raw/*258.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodegaOrigen:tables.BodegaEmpresa,listBodegasDestino:List[List[String]],nuevoNumeroGuia:Long,hoy:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listBodegasDestino,nuevoNumeroGuia,hoy)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[List[String]],Long,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listBodegasDestino,nuevoNumeroGuia,hoy) => apply(mapDiccionario,mapPermiso,userMnu,bodegaOrigen,listBodegasDestino,nuevoNumeroGuia,hoy)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMovimientos/movimientoOrigenDestinoMultiple.scala.html
                  HASH: 5ced33e7613a2af8dfd60c0c427d6bf4bb78436a
                  MATRIX: 1086->1|1388->210|1421->218|1437->226|1476->228|1505->232|1573->280|1604->284|1758->412|1803->436|1832->439|1873->459|1904->463|2160->693|2281->792|2312->796|2859->1316|2880->1328|2909->1336|3013->1413|3034->1425|3079->1449|4043->2386|4079->2401|5437->3732|5458->3744|5497->3762|6259->4496|6279->4506|6332->4537|6362->4539|6382->4549|6434->4578|6474->4589|6500->4592|6550->4614|6575->4617|7040->5054|7060->5064|7115->5097|7145->5099|7165->5109|7219->5140|7259->5151|7285->5154|7335->5176|7360->5179|7802->5593|7824->5605|7865->5624|9088->6819|9112->6833|9148->6847|9650->7321|9674->7335|9710->7349|9861->7472|9885->7486|9921->7500|9951->7501|10042->7565|10092->7598|10132->7599|10169->7608|10236->7647|10252->7653|10281->7660|10313->7664|10329->7670|10359->7678|10471->7762|10487->7768|10516->7775|10547->7778|10563->7784|10593->7792|10673->7844|10689->7850|10718->7857|10808->7919|10824->7925|10853->7932|10884->7935|10900->7941|10944->7962|11024->8014|11040->8020|11069->8027|11143->8073|11159->8079|11188->8086|11262->8132|11278->8138|11307->8145|11368->8174|11403->8181|11731->8478|11763->8482|11855->8545|11885->8546|11918->8551|11988->8592|12018->8593|12054->8601|12239->8757|12269->8758|12310->8770|12417->8848|12447->8849|12480->8854|12509->8855|12538->8856|12640->8930|12669->8931|12770->9003|12800->9004|12831->9007|13060->9208|13089->9209|13120->9212
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|41->10|43->12|43->12|44->13|44->13|46->15|53->22|53->22|54->23|64->33|64->33|64->33|66->35|66->35|66->35|92->61|92->61|130->99|130->99|130->99|151->120|151->120|151->120|151->120|151->120|151->120|151->120|151->120|152->121|152->121|165->134|165->134|165->134|165->134|165->134|165->134|165->134|165->134|166->135|166->135|183->152|183->152|183->152|221->190|221->190|221->190|232->201|232->201|232->201|235->204|235->204|235->204|235->204|239->208|239->208|239->208|240->209|240->209|240->209|240->209|240->209|240->209|240->209|241->210|241->210|241->210|241->210|241->210|241->210|242->211|242->211|242->211|243->212|243->212|243->212|243->212|243->212|243->212|244->213|244->213|244->213|245->214|245->214|245->214|246->215|246->215|246->215|248->217|249->218|262->231|266->235|268->237|268->237|269->238|269->238|269->238|270->239|273->242|273->242|274->243|275->244|275->244|276->245|276->245|276->245|279->248|279->248|281->250|281->250|282->251|287->256|287->256|289->258
                  -- GENERATED --
              */
          