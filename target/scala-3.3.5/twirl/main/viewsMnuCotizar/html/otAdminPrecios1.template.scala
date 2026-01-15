
package viewsMnuCotizar.html

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

object otAdminPrecios1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listTablas: List[String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "DETALLE Y ESTADO DE "+mapDiccionario.get("ORDEN_DE_TRABAJO").toUpperCase()+" (ADMINISTRAR PRECIOS)", bodega.getNombre().toUpperCase())),format.raw/*8.167*/("""
		"""),format.raw/*9.3*/("""<!--
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1">
					<input type="button"  value="Imprimir" class="btn btn-primary btn-sm rounded-pill btn-block" onclick="window.print(); return false;">
				</div>
				<div class="col-xs-3 col-sm-1 col-md-1 col-lg-1" onsubmit="return validarForm();">
					<form action="/routes2/otAdminPrecios1Excel/" method="POST">
						<input type="hidden" name="id_bodega" value=""""),_display_(/*17.53*/bodega/*17.59*/.getId()),format.raw/*17.67*/("""">
						<input type="submit" id="btnSubmit" class="btn btn-info btn-sm rounded-pill btn-block" value="Excel">
					</form>
					
				</div>
			</div>
			<br><br>
		</div>
		-->
		<div id="element">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					"""),_display_(/*29.7*/for(tabla <- listTablas) yield /*29.31*/{_display_(Seq[Any](format.raw/*29.32*/("""
						
						"""),format.raw/*31.7*/("""<hr class="border border-primary border-3 ">
						"""),_display_(/*32.8*/Html(tabla)),format.raw/*32.19*/("""
						
					""")))}),format.raw/*34.7*/("""
					"""),format.raw/*35.6*/("""<hr class="border border-primary border-3 ">
				</div>
			</div>
		</div>
		<div class="noprint" align="left">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/otListaRevisarPeriodo/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalVerTrazaOt' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>TRAZABILIDAD DEL DESPACHO<div id="detOrigen"></div></h5>
						<input type="button"  value="Imprimir" class="btn btn-primary btn-sm rounded-pill" onclick="document.getElementById('mostrarmostrar').style.display='none'; window.print(); return false;">
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close' onclick="document.getElementById('mostrarmostrar').style.display='block'">
				          <span aria-hidden='true'>&times;</span>
				        </button>

				</div>
				<div class='modal-body'>
					<div id='mostrarTrazaOt'></div>
					<div class="noprint">
		   				<div class='row'>
							<div class='col-sm-12' style='text-align:center'>
								<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal' onclick="document.getElementById('mostrarmostrar').style.display='block'">Listo</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		
	<div id="modalStockPorEquipo" class="modal" role="dialog" data-backdrop="static" data-keyboard="false">
		<div class='modal-dialog modal-dialog-scrollable' style='max-width: 80% !important;' role='document'>
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">STOCK DISPONIBLE</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				</div>
				<div class="modal-body">
					<div id="stockPorEquipo"></div>
	   				<div class="row">
						<div class="col-sm-12" style="text-align:center">
							<button type="button" class="btn btn-sm  btn-warning" style="font-size: 10px;" data-dismiss="modal">Cancelar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
""")))}),format.raw/*95.2*/("""



"""),format.raw/*99.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*101.31*/("""{"""),format.raw/*101.32*/("""
		"""),format.raw/*102.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*103.2*/("""}"""),format.raw/*103.3*/(""");
	
	const trazaOt = (id_ot, id_equipoOrigen) =>"""),format.raw/*105.45*/("""{"""),format.raw/*105.46*/("""
		"""),format.raw/*106.3*/("""var formData = new FormData();
	  	formData.append('id_ot',id_ot);
		formData.append('id_equipoOrigen',id_equipoOrigen);
        $.ajax("""),format.raw/*109.16*/("""{"""),format.raw/*109.17*/("""
            """),format.raw/*110.13*/("""url: "/routes2/trazaEqOtAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*117.36*/("""{"""),format.raw/*117.37*/("""
	     		"""),format.raw/*118.9*/("""document.getElementById('mostrarTrazaOt').innerHTML = respuesta;
				let codOrigen = $("#codEq_"+id_equipoOrigen).text();
				let nomOrigen = $("#nomEq_"+id_equipoOrigen).text();
				document.getElementById('detOrigen').innerHTML = "Equipo de origen: "+ codOrigen + " - " +nomOrigen;



	     		$('#modalVerTrazaOt').modal('show');
	     	"""),format.raw/*126.8*/("""}"""),format.raw/*126.9*/("""
        """),format.raw/*127.9*/("""}"""),format.raw/*127.10*/(""");
	"""),format.raw/*128.2*/("""}"""),format.raw/*128.3*/("""
	
	"""),format.raw/*130.2*/("""const vistaStockPorEquipo = (id_equipo) =>"""),format.raw/*130.44*/("""{"""),format.raw/*130.45*/("""
		"""),format.raw/*131.3*/("""var formData = new FormData();
		formData.append('id_equipo',id_equipo);
        $.ajax("""),format.raw/*133.16*/("""{"""),format.raw/*133.17*/("""
            """),format.raw/*134.13*/("""url: "/tablaInvPorEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*141.36*/("""{"""),format.raw/*141.37*/("""
				"""),format.raw/*142.5*/("""document.getElementById('stockPorEquipo').innerHTML = respuesta;
				$('#modalStockPorEquipo').modal('show');
	     	"""),format.raw/*144.8*/("""}"""),format.raw/*144.9*/("""
        """),format.raw/*145.9*/("""}"""),format.raw/*145.10*/(""");
	"""),format.raw/*146.2*/("""}"""),format.raw/*146.3*/("""
	
	"""),format.raw/*148.2*/("""const validarForm = () =>"""),format.raw/*148.27*/("""{"""),format.raw/*148.28*/("""
		"""),format.raw/*149.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*151.2*/("""}"""),format.raw/*151.3*/("""
	
	"""),format.raw/*153.2*/("""const grabar = (id, valor, id_equipo, id_cotizacion) => """),format.raw/*153.58*/("""{"""),format.raw/*153.59*/("""
		"""),format.raw/*154.3*/("""let auxId = id.split("_");
		let id_moneda = $("#NM_"+auxId[1]).val();
		var formData = new FormData();
		formData.append('id_bodegaEmpresa','"""),_display_(/*157.40*/bodega/*157.46*/.getId),format.raw/*157.52*/("""');
		formData.append('id_equipo',id_equipo);
		formData.append('id_cotizacion',id_cotizacion);
		formData.append('id_moneda',id_moneda);
		formData.append('campo',auxId[0]);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*163.10*/("""{"""),format.raw/*163.11*/("""
            """),format.raw/*164.13*/("""url: "/actualizaListaPrecioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*171.36*/("""{"""),format.raw/*171.37*/("""
	     		"""),format.raw/*172.9*/("""if(respuesta=="error")"""),format.raw/*172.31*/("""{"""),format.raw/*172.32*/("""
	     			"""),format.raw/*173.10*/("""alertify.alert(msgError, function () """),format.raw/*173.47*/("""{"""),format.raw/*173.48*/("""
		     			"""),format.raw/*174.11*/("""location.href = "/";
		     		"""),format.raw/*175.10*/("""}"""),format.raw/*175.11*/(""");
	     		"""),format.raw/*176.9*/("""}"""),format.raw/*176.10*/("""
	     		"""),format.raw/*177.9*/("""if(respuesta.status)"""),format.raw/*177.29*/("""{"""),format.raw/*177.30*/("""
	     			"""),format.raw/*178.10*/("""let fecha = respuesta.fecha;
	     			let numDec = respuesta.numDec;
	     			"""),_display_(if(mapDiccionario.get("nEmpresa").equals("MONTAX")||mapDiccionario.get("nEmpresa").equals("TECA"))/*180.109*/ {_display_(Seq[Any](format.raw/*180.111*/("""
	     				"""),format.raw/*181.11*/("""numDec = 4;
	     			""")))} else {null} ),format.raw/*182.11*/("""
	     			"""),format.raw/*183.10*/("""if(auxId[0]=="PV" || auxId[0]=="PA" || auxId[0]=="PM")"""),format.raw/*183.64*/("""{"""),format.raw/*183.65*/("""
	     				"""),format.raw/*184.11*/("""$("#"+id).val(formatStandar(valor,numDec));
	     			"""),format.raw/*185.10*/("""}"""),format.raw/*185.11*/("""
	     			"""),format.raw/*186.10*/("""if(auxId[0]=="PE")"""),format.raw/*186.28*/("""{"""),format.raw/*186.29*/("""
	     				"""),format.raw/*187.11*/("""$("#"+id).val(formatStandar(valor,0));
	     			"""),format.raw/*188.10*/("""}"""),format.raw/*188.11*/("""
	     			"""),format.raw/*189.10*/("""if(auxId[0]=="PA")"""),format.raw/*189.28*/("""{"""),format.raw/*189.29*/("""
	     				"""),format.raw/*190.11*/("""let numero = new DecimalFormat("#,##0.00");
	     				let pventa = numero.formatBack($("#PV_"+auxId[1]).val());
	     				let tasa = 0;
	     				if(pventa!=0)"""),format.raw/*193.24*/("""{"""),format.raw/*193.25*/("""
	     					"""),format.raw/*194.12*/("""tasa = valor/pventa*100;
	     				"""),format.raw/*195.11*/("""}"""),format.raw/*195.12*/("""
	     				"""),format.raw/*196.11*/("""$("#TA_"+auxId[1]).val(formatPorcentaje(tasa));
	     			"""),format.raw/*197.10*/("""}"""),format.raw/*197.11*/("""
	     			"""),format.raw/*198.10*/("""if(auxId[0]=="PV")"""),format.raw/*198.28*/("""{"""),format.raw/*198.29*/("""
	     				"""),format.raw/*199.11*/("""let numero = new DecimalFormat("#,##0.00");
	     				let parriendo = numero.formatBack($("#PA_"+auxId[1]).val());
	     				let tasa = 0;
	     				if(valor!=0)"""),format.raw/*202.23*/("""{"""),format.raw/*202.24*/("""
	     					"""),format.raw/*203.12*/("""tasa = parriendo/valor*100;
	     				"""),format.raw/*204.11*/("""}"""),format.raw/*204.12*/(""" 
	     				"""),format.raw/*205.11*/("""$("#TA_"+auxId[1]).val(formatPorcentaje(tasa));
	     			"""),format.raw/*206.10*/("""}"""),format.raw/*206.11*/("""
	     			"""),format.raw/*207.10*/("""if(auxId[0]=="NM")"""),format.raw/*207.28*/("""{"""),format.raw/*207.29*/("""
	     				"""),format.raw/*208.11*/("""let numero = new DecimalFormat("#,##0.00");
	     				let pventa = numero.formatBack($("#PV_"+auxId[1]).val());
	     				let parriendo = numero.formatBack($("#PA_"+auxId[1]).val());
	     				let pminimo = numero.formatBack($("#PM_"+auxId[1]).val());
	     				$("#PV_"+auxId[1]).val(formatStandar(pventa,numDec));
	     				$("#PA_"+auxId[1]).val(formatStandar(parriendo,numDec));
	     				$("#PM_"+auxId[1]).val(formatStandar(pminimo,numDec));
	     				
	     			"""),format.raw/*216.10*/("""}"""),format.raw/*216.11*/("""
	     			"""),format.raw/*217.10*/("""$("#FE_"+auxId[1]).val(fecha);
	     		"""),format.raw/*218.9*/("""}"""),format.raw/*218.10*/("""else"""),format.raw/*218.14*/("""{"""),format.raw/*218.15*/("""
	     			"""),format.raw/*219.10*/("""location.reload();
	     		"""),format.raw/*220.9*/("""}"""),format.raw/*220.10*/("""
	     	"""),format.raw/*221.8*/("""}"""),format.raw/*221.9*/("""
        """),format.raw/*222.9*/("""}"""),format.raw/*222.10*/(""");
	"""),format.raw/*223.2*/("""}"""),format.raw/*223.3*/("""


	
"""),format.raw/*227.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listTablas:List[String]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listTablas)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[String]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listTablas) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listTablas)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/otAdminPrecios1.scala.html
                  HASH: 994e198aed1bb0e631560327abd96663c910c16c
                  MATRIX: 1048->1|1294->154|1321->156|1337->164|1376->166|1405->170|1473->218|1501->220|1577->271|1761->434|1790->437|2316->936|2331->942|2360->950|2697->1261|2737->1285|2776->1286|2817->1300|2895->1352|2927->1363|2971->1377|3004->1383|5534->3883|5565->3887|5657->3950|5687->3951|5718->3954|5812->4020|5841->4021|5919->4070|5949->4071|5980->4074|6145->4210|6175->4211|6217->4224|6480->4458|6510->4459|6547->4468|6914->4807|6943->4808|6980->4817|7010->4818|7042->4822|7071->4823|7103->4827|7174->4869|7204->4870|7235->4873|7352->4961|7382->4962|7424->4975|7687->5209|7717->5210|7750->5215|7895->5332|7924->5333|7961->5342|7991->5343|8023->5347|8052->5348|8084->5352|8138->5377|8168->5378|8199->5381|8283->5437|8312->5438|8344->5442|8429->5498|8459->5499|8490->5502|8661->5645|8677->5651|8705->5657|8952->5875|8982->5876|9024->5889|9290->6126|9320->6127|9357->6136|9408->6158|9438->6159|9477->6169|9543->6206|9573->6207|9613->6218|9672->6248|9702->6249|9741->6260|9771->6261|9808->6270|9857->6290|9887->6291|9926->6301|10132->6478|10174->6480|10214->6491|10281->6513|10320->6523|10403->6577|10433->6578|10473->6589|10555->6642|10585->6643|10624->6653|10671->6671|10701->6672|10741->6683|10818->6731|10848->6732|10887->6742|10934->6760|10964->6761|11004->6772|11192->6931|11222->6932|11263->6944|11327->6979|11357->6980|11397->6991|11483->7048|11513->7049|11552->7059|11599->7077|11629->7078|11669->7089|11859->7250|11889->7251|11930->7263|11997->7301|12027->7302|12068->7314|12154->7371|12184->7372|12223->7382|12270->7400|12300->7401|12340->7412|12837->7880|12867->7881|12906->7891|12973->7930|13003->7931|13036->7935|13066->7936|13105->7946|13160->7973|13190->7974|13226->7982|13255->7983|13292->7992|13322->7993|13354->7997|13383->7998|13416->8003
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|48->17|48->17|48->17|60->29|60->29|60->29|62->31|63->32|63->32|65->34|66->35|126->95|130->99|132->101|132->101|133->102|134->103|134->103|136->105|136->105|137->106|140->109|140->109|141->110|148->117|148->117|149->118|157->126|157->126|158->127|158->127|159->128|159->128|161->130|161->130|161->130|162->131|164->133|164->133|165->134|172->141|172->141|173->142|175->144|175->144|176->145|176->145|177->146|177->146|179->148|179->148|179->148|180->149|182->151|182->151|184->153|184->153|184->153|185->154|188->157|188->157|188->157|194->163|194->163|195->164|202->171|202->171|203->172|203->172|203->172|204->173|204->173|204->173|205->174|206->175|206->175|207->176|207->176|208->177|208->177|208->177|209->178|211->180|211->180|212->181|213->182|214->183|214->183|214->183|215->184|216->185|216->185|217->186|217->186|217->186|218->187|219->188|219->188|220->189|220->189|220->189|221->190|224->193|224->193|225->194|226->195|226->195|227->196|228->197|228->197|229->198|229->198|229->198|230->199|233->202|233->202|234->203|235->204|235->204|236->205|237->206|237->206|238->207|238->207|238->207|239->208|247->216|247->216|248->217|249->218|249->218|249->218|249->218|250->219|251->220|251->220|252->221|252->221|253->222|253->222|254->223|254->223|258->227
                  -- GENERATED --
              */
          