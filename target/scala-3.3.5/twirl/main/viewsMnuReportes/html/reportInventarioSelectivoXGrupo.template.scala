
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

object reportInventarioSelectivoXGrupo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.Grupo,List[List[String]],String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
grupo: tables.Grupo, lista: List[List[String]], fechaCorte: String, tipo:String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "GRUPO/FAMILIA: " + grupo.nombre.toUpperCase() + " - " +tipo, 
			"INVENTARIO POR GRUPO VALORIZADO - FECHA DE CORTE: " + utilities.Fechas.DDMMAA(fechaCorte))),format.raw/*13.95*/("""
		
		"""),format.raw/*15.3*/("""<div class="noprint">
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
					    		<span class="input-group-text" id="basic-addon1">TOTAL COMPRA ("""),_display_(/*46.76*/mapDiccionario/*46.90*/.get("PESOS")),format.raw/*46.103*/(""")</span>
					  		</div>
					  		<input type="text" class="form-control right"
								id="totalPesos"
								readonly>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<div>
			ALTERNAR COLUMNAS: 
				<a class="toggle-vis" href="#" data-column="0"><kbd id="kdb0" style="background-color: #73C6B6">PROPIEDAD</kbd></a>
				<a class="toggle-vis" href="#" data-column="1"><kbd id="kdb1" style="background-color: #73C6B6">COD</kbd></a>
				<a class="toggle-vis" href="#" data-column="2"><kbd id="kdb2" style="background-color: #73C6B6">EQUIPO</kbd></a>
				<a class="toggle-vis" href="#" data-column="3"><kbd id="kdb3" style="background-color: #73C6B6">MON</kbd></a>
				<a class="toggle-vis" href="#" data-column="4"><kbd id="kdb4" style="background-color: #73C6B6">P.U. Compra</kbd></a>
				<a class="toggle-vis" href="#" data-column="5"><kbd id="kdb5" style="background-color: #73C6B6">UN</kbd></a>
				<a class="toggle-vis" href="#" data-column="6"><kbd id="kdb6" style="background-color: #73C6B6">CANT</kbd></a>
				<a class="toggle-vis" href="#" data-column="7"><kbd id="kdb7" style="background-color: #73C6B6">TOTAL COMPRA</kbd></a>
				<a class="toggle-vis" href="#" data-column="8"><kbd id="kdb8" style="background-color: #73C6B6">COMPRA (en """),_display_(/*67.113*/mapDiccionario/*67.127*/.get("PESOS")),format.raw/*67.140*/(""")</kbd></a>
		</div>
		
		<div class="table-responsive">
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
		        <thead style="background-color: #eeeeee">
					<TR> 
					        <TH style= "text-align: right;vertical-align: top;color:blue;"></TH>
							<TH style= "text-align: right;vertical-align: top;color:blue;"></TH>
							<TH style= "text-align: right;vertical-align: top;color:blue;">SUBTOTALES (resultado de buscar):&nbsp</TH>
							<TH style= "text-align: right;vertical-align: top;color:blue;"></TH>
							<TH style= "text-align: left;vertical-align: top;color:blue;">elementos</TH>
							<TH style= "text-align: right;vertical-align: top;color:blue;"></TH>
							<TH style= "text-align: right;vertical-align: top;color:blue;">CANT</TH>
							<TH style= "text-align: right;vertical-align: top;color:blue;">TOTAL<br>COMPRA</TH>
							<TH style= "text-align: right;vertical-align: top;color:blue;">COMPRA<br>(en """),_display_(/*82.86*/mapDiccionario/*82.100*/.get("PESOS")),format.raw/*82.113*/(""")</TH>
							<TH style= "text-align: right;vertical-align: top;color:blue;"></TH>
					</TR>
					<TR> 
					        <TH style= "text-align: center;vertical-align: top;">PROPIEDAD</TH>
							<TH style= "text-align: center;vertical-align: top;">COD</TH>
							<TH style= "text-align: center;vertical-align: top;">EQUIPO</TH>
							<TH style= "text-align: center;vertical-align: top;">MON</TH>
							<TH style= "text-align: center;vertical-align: top;">P.U.<br>Compra</TH>
							<TH style= "text-align: center;vertical-align: top;">UN</TH>
							<TH style= "text-align: center;vertical-align: top;">CANT</TH>
							<TH style= "text-align: center;vertical-align: top;">TOTAL<br>COMPRA</TH>
							<TH style= "text-align: center;vertical-align: top;">COMPRA<br>(en """),_display_(/*94.76*/mapDiccionario/*94.90*/.get("PESOS")),format.raw/*94.103*/(""")</TH>
							<TH style= "text-align: center;vertical-align: top;">VER</TH>
					</TR>
				</thead>
				<tbody>
				"""),_display_(/*99.6*/for(lista1 <- lista) yield /*99.26*/{_display_(Seq[Any](format.raw/*99.27*/("""
					"""),format.raw/*100.6*/("""<TR>
						<td style="text-align:left;vertical-align:middle;">"""),_display_(/*101.59*/lista1/*101.65*/.get(10)),format.raw/*101.73*/("""</td>
						<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*102.99*/lista1/*102.105*/.get(0)),format.raw/*102.112*/("""');">"""),_display_(/*102.118*/lista1/*102.124*/.get(2)),format.raw/*102.131*/("""</a></td>
						<td style="text-align:left;vertical-align:middle;"><a href="#" onclick="equipoDescripcion('"""),_display_(/*103.99*/lista1/*103.105*/.get(0)),format.raw/*103.112*/("""');">"""),_display_(/*103.118*/lista1/*103.124*/.get(3)),format.raw/*103.131*/("""</a></td>

						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*105.60*/lista1/*105.66*/.get(4)),format.raw/*105.73*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*106.60*/lista1/*106.66*/.get(5)),format.raw/*106.73*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*107.60*/lista1/*107.66*/.get(6)),format.raw/*107.73*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*108.60*/lista1/*108.66*/.get(7)),format.raw/*108.73*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*109.60*/lista1/*109.66*/.get(8)),format.raw/*109.73*/("""</td>
						<td style="text-align:right;vertical-align:middle;">"""),_display_(/*110.60*/lista1/*110.66*/.get(9)),format.raw/*110.73*/("""</td>
						<td title='SELECCIONAR' style="text-align:center;vertical-align:middle;">
							<form id="form_"""),_display_(/*112.24*/lista1/*112.30*/.get(0)),format.raw/*112.37*/("""" class="formulario" method="post" action="/reportInventarioGeneralXEquipo/">
								<input type="hidden" name="id_equipo" value=""""),_display_(/*113.55*/lista1/*113.61*/.get(0)),format.raw/*113.68*/("""">
								<input type="hidden" name="fechaCorte" value=""""),_display_(/*114.56*/fechaCorte),format.raw/*114.66*/("""">
								<input type="hidden" name="tipo" value=""""),_display_(/*115.50*/tipo),format.raw/*115.54*/("""">
								<a href="#" onclick="document.getElementById('form_"""),_display_(/*116.61*/lista1/*116.67*/.get(0)),format.raw/*116.74*/("""').submit()">
									<kbd style="background-color: #73C6B6">select</kbd>
								</a>
							</form>
						</td>
					</TR>
	 			""")))}),format.raw/*122.7*/("""
				"""),format.raw/*123.5*/("""</tbody>
			</table>
		</div>
	</div>
	
	<form id="excel" class="formulario" method="post" action="/reportInventarioSelectivoXGrupoExcel/">
		<input type="hidden" name="id_grupo" value=""""),_display_(/*129.48*/grupo/*129.53*/.getId()),format.raw/*129.61*/("""">
		<input type="hidden" name="fechaCorte" value=""""),_display_(/*130.50*/fechaCorte),format.raw/*130.60*/("""">
		<input type="hidden" name="tipo" value=""""),_display_(/*131.44*/tipo),format.raw/*131.48*/("""">
	</form>

""")))}),format.raw/*134.2*/("""


"""),format.raw/*137.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*139.31*/("""{"""),format.raw/*139.32*/("""
            """),format.raw/*140.13*/("""sumarTotales2();
            flag = false;

		 $('#tablaPrincipal').DataTable("""),format.raw/*143.35*/("""{"""),format.raw/*143.36*/("""
		    	"""),format.raw/*144.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*149.20*/("""{"""),format.raw/*149.21*/("""
		        	"""),format.raw/*150.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*151.11*/("""}"""),format.raw/*151.12*/("""
		  """),format.raw/*152.5*/("""}"""),format.raw/*152.6*/(""" """),format.raw/*152.7*/(""");
		  
		  $('a.toggle-vis').on('click', function (e) """),format.raw/*154.48*/("""{"""),format.raw/*154.49*/("""
		        """),format.raw/*155.11*/("""e.preventDefault();
		        // Get the column API object
		        let col = $(this).attr('data-column');
		        var column = $('#tablaPrincipal').DataTable().column(col);
		        // Toggle the visibility
		        column.visible(!column.visible());
		        if(column.visible())"""),format.raw/*161.31*/("""{"""),format.raw/*161.32*/("""
					"""),format.raw/*162.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#73C6B6";
				"""),format.raw/*163.5*/("""}"""),format.raw/*163.6*/("""else"""),format.raw/*163.10*/("""{"""),format.raw/*163.11*/("""
					"""),format.raw/*164.6*/("""document.getElementById('kdb'+col).style.backgroundColor = "#D6DBDF";
				"""),format.raw/*165.5*/("""}"""),format.raw/*165.6*/("""
		  """),format.raw/*166.5*/("""}"""),format.raw/*166.6*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*169.2*/("""}"""),format.raw/*169.3*/(""");
	
	function sumarTotales() """),format.raw/*171.26*/("""{"""),format.raw/*171.27*/("""
		"""),format.raw/*172.3*/("""var tabla = document.getElementById("tablaPrincipal");
		//var sumaCantidades = 0;
		var sumaTotales= 0;
		var numero = new DecimalFormat("#,##0.00");
		for (var i = 2; i < tabla.rows.length; i++) """),format.raw/*176.47*/("""{"""),format.raw/*176.48*/(""" 
			"""),format.raw/*177.4*/("""var aux = tabla.rows[i].cells[7].textContent;
			sumaTotales = parseFloat(sumaTotales) + parseFloat(numero.formatBack(aux));
		"""),format.raw/*179.3*/("""}"""),format.raw/*179.4*/("""
		"""),format.raw/*180.3*/("""document.getElementById('totalPesos').value=formatStandar0(sumaTotales);
	"""),format.raw/*181.2*/("""}"""),format.raw/*181.3*/("""
	
	"""),format.raw/*183.2*/("""function sumarTotales2() """),format.raw/*183.27*/("""{"""),format.raw/*183.28*/("""
		"""),format.raw/*184.3*/("""var tabla = document.getElementById("tablaPrincipal");
		if(flag)"""),format.raw/*185.11*/("""{"""),format.raw/*185.12*/("""
			"""),format.raw/*186.4*/("""var sumaGranTotalesC= 0;
		"""),format.raw/*187.3*/("""}"""),format.raw/*187.4*/("""
		"""),format.raw/*188.3*/("""var cuentaItems = 0;
		var sumaCantidades = 0;
		var sumaCompraMO = 0;
		var sumaCompraMP = 0;
		
		var numero = new DecimalFormat("#,##0.00");
		
		for (var i = 2; i < tabla.rows.length; i++) """),format.raw/*195.47*/("""{"""),format.raw/*195.48*/(""" 
			"""),format.raw/*196.4*/("""var aux = 0;
			if(flag)"""),format.raw/*197.12*/("""{"""),format.raw/*197.13*/("""
				"""),format.raw/*198.5*/("""var aux = tabla.rows[i].cells[8].textContent;
				sumaGranTotalesC = parseFloat(sumaGranTotalesC) + parseFloat(numero.formatBack(aux));
			"""),format.raw/*200.4*/("""}"""),format.raw/*200.5*/("""
			"""),format.raw/*201.4*/("""if(tabla.rows[i].style.display != "none")"""),format.raw/*201.45*/("""{"""),format.raw/*201.46*/("""
				"""),format.raw/*202.5*/("""cuentaItems++;
				var aux = tabla.rows[i].cells[6].textContent;
				sumaCantidades = parseFloat(sumaCantidades) + parseFloat(numero.formatBack(aux));
				aux = tabla.rows[i].cells[7].textContent;
				sumaCompraMO = parseFloat(sumaCompraMO) + parseFloat(numero.formatBack(aux));
				aux = tabla.rows[i].cells[8].textContent;
				sumaCompraMP = parseFloat(sumaCompraMP) + parseFloat(numero.formatBack(aux));
			"""),format.raw/*209.4*/("""}"""),format.raw/*209.5*/("""
		"""),format.raw/*210.3*/("""}"""),format.raw/*210.4*/("""
		
		"""),format.raw/*212.3*/("""if(flag)"""),format.raw/*212.11*/("""{"""),format.raw/*212.12*/("""
			"""),format.raw/*213.4*/("""document.getElementById('totalPesos').value=formatStandar0(sumaGranTotalesC);
		"""),format.raw/*214.3*/("""}"""),format.raw/*214.4*/("""
		"""),format.raw/*215.3*/("""tabla.rows[0].cells[3].textContent = formatStandar0(cuentaItems);
		tabla.rows[0].cells[6].textContent = formatStandar0(sumaCantidades);
		tabla.rows[0].cells[7].textContent = formatStandar0(sumaCompraMO);
		tabla.rows[0].cells[8].textContent = formatStandar0(sumaCompraMP);
		
		
	"""),format.raw/*221.2*/("""}"""),format.raw/*221.3*/("""
	
"""),format.raw/*223.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,grupo:tables.Grupo,lista:List[List[String]],fechaCorte:String,tipo:String): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,grupo,lista,fechaCorte,tipo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Grupo,List[List[String]],String,String) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,grupo,lista,fechaCorte,tipo) => apply(mapDiccionario,mapPermiso,userMnu,grupo,lista,fechaCorte,tipo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportInventarioSelectivoXGrupo.scala.html
                  HASH: a39490569d16f56265131c09eaa2dd92f4be9562
                  MATRIX: 1077->1|1348->179|1381->187|1397->195|1436->197|1465->201|1533->249|1563->254|1607->278|1638->282|1715->333|1921->518|1954->524|3131->1674|3154->1688|3189->1701|4478->2962|4502->2976|4537->2989|5557->3982|5581->3996|5616->4009|6413->4779|6436->4793|6471->4806|6614->4923|6650->4943|6689->4944|6723->4950|6814->5013|6830->5019|6860->5027|6992->5131|7009->5137|7039->5144|7074->5150|7091->5156|7121->5163|7257->5271|7274->5277|7304->5284|7339->5290|7356->5296|7386->5303|7484->5373|7500->5379|7529->5386|7622->5451|7638->5457|7667->5464|7760->5529|7776->5535|7805->5542|7898->5607|7914->5613|7943->5620|8036->5685|8052->5691|8081->5698|8174->5763|8190->5769|8219->5776|8356->5885|8372->5891|8401->5898|8561->6030|8577->6036|8606->6043|8692->6101|8724->6111|8804->6163|8830->6167|8921->6230|8937->6236|8966->6243|9129->6375|9162->6380|9377->6567|9392->6572|9422->6580|9502->6632|9534->6642|9608->6688|9634->6692|9679->6706|9710->6709|9819->6789|9849->6790|9891->6803|9998->6881|10028->6882|10064->6890|10232->7029|10262->7030|10303->7042|10410->7120|10440->7121|10473->7126|10502->7127|10531->7128|10615->7183|10645->7184|10685->7195|11001->7482|11031->7483|11065->7489|11167->7563|11196->7564|11229->7568|11259->7569|11293->7575|11395->7649|11424->7650|11457->7655|11486->7656|11587->7729|11616->7730|11675->7760|11705->7761|11736->7764|11962->7961|11992->7962|12025->7967|12180->8094|12209->8095|12240->8098|12342->8172|12371->8173|12403->8177|12457->8202|12487->8203|12518->8206|12612->8271|12642->8272|12674->8276|12729->8303|12758->8304|12789->8307|13011->8500|13041->8501|13074->8506|13127->8530|13157->8531|13190->8536|13357->8675|13386->8676|13418->8680|13488->8721|13518->8722|13551->8727|13989->9137|14018->9138|14049->9141|14078->9142|14112->9148|14149->9156|14179->9157|14211->9161|14319->9241|14348->9242|14379->9245|14689->9527|14718->9528|14749->9531
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|44->13|46->15|77->46|77->46|77->46|98->67|98->67|98->67|113->82|113->82|113->82|125->94|125->94|125->94|130->99|130->99|130->99|131->100|132->101|132->101|132->101|133->102|133->102|133->102|133->102|133->102|133->102|134->103|134->103|134->103|134->103|134->103|134->103|136->105|136->105|136->105|137->106|137->106|137->106|138->107|138->107|138->107|139->108|139->108|139->108|140->109|140->109|140->109|141->110|141->110|141->110|143->112|143->112|143->112|144->113|144->113|144->113|145->114|145->114|146->115|146->115|147->116|147->116|147->116|153->122|154->123|160->129|160->129|160->129|161->130|161->130|162->131|162->131|165->134|168->137|170->139|170->139|171->140|174->143|174->143|175->144|180->149|180->149|181->150|182->151|182->151|183->152|183->152|183->152|185->154|185->154|186->155|192->161|192->161|193->162|194->163|194->163|194->163|194->163|195->164|196->165|196->165|197->166|197->166|200->169|200->169|202->171|202->171|203->172|207->176|207->176|208->177|210->179|210->179|211->180|212->181|212->181|214->183|214->183|214->183|215->184|216->185|216->185|217->186|218->187|218->187|219->188|226->195|226->195|227->196|228->197|228->197|229->198|231->200|231->200|232->201|232->201|232->201|233->202|240->209|240->209|241->210|241->210|243->212|243->212|243->212|244->213|245->214|245->214|246->215|252->221|252->221|254->223
                  -- GENERATED --
              */
          