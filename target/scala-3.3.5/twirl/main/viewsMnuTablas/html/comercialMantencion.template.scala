
package viewsMnuTablas.html

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

object comercialMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Comercial],List[tables.Usuario],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listComercial: List[tables.Comercial], listUsuario: List[tables.Usuario]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "COMERCIALES: AGREGAR/MODIFICAR", "")),format.raw/*9.69*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-10 col-sm-8 col-md-8 col-lg-8">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH>COMERCIAL</TH>
							<TH>SUCURSAL</TH>
							<TH>VIGENTE</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*21.8*/for(lista1 <- listComercial) yield /*21.36*/{_display_(Seq[Any](format.raw/*21.37*/("""
							"""),format.raw/*22.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*23.40*/lista1/*23.46*/.getNameUsuario()),format.raw/*23.63*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*24.40*/lista1/*24.46*/.getNameSucursal()),format.raw/*24.64*/("""</td>
								<td  style="text-align:center;">
									<input type="hidden" id="vigente_"""),_display_(/*26.44*/lista1/*26.50*/.getId()),format.raw/*26.58*/("""" value=""""),_display_(/*26.68*/lista1/*26.74*/.getVigente()),format.raw/*26.87*/("""">
									"""),_display_(if(lista1.getVigente()==1)/*27.37*/{_display_(Seq[Any](format.raw/*27.38*/("""
										"""),format.raw/*28.11*/("""<input type="checkbox" checked onchange="modificaVigente('"""),_display_(/*28.70*/lista1/*28.76*/.getId()),format.raw/*28.84*/("""');">
									""")))}else/*29.15*/{_display_(Seq[Any](format.raw/*29.16*/("""
										"""),format.raw/*30.11*/("""<input type="checkbox" onchange="modificaVigente('"""),_display_(/*30.62*/lista1/*30.68*/.getId()),format.raw/*30.76*/("""');">
									""")))}),format.raw/*31.11*/("""
								"""),format.raw/*32.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*34.9*/("""
					"""),format.raw/*35.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block" onclick='$("#modalAgregaComercial").modal("show");'>
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalAgregaComercial' class="modal" role="dialog" data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
				   	<h5 class="modal-title">Seleccionar Usuario</h5>
			        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
			        	<span aria-hidden='true'>&times;</span>
			        </button>
				</div>
	     		<div class="modal-body">
		     		<table id="tablaAgregaComercial" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR> 
								<TH>Nombre (fullName)</TH>
						        <TH>Cargo/Funci&oacute;n</TH>
						        <TH>Tipo Usuario</TH>
								<TH>Sucursal</TH>
								<TH>SELECT</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*72.9*/for(lista1 <- listUsuario) yield /*72.35*/{_display_(Seq[Any](format.raw/*72.36*/("""
								"""),format.raw/*73.9*/("""<TR>
									<td  style="text-align:left;">"""),_display_(/*74.41*/lista1/*74.47*/.getNombre()),format.raw/*74.59*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*75.41*/lista1/*75.47*/.getCargo()),format.raw/*75.58*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*76.41*/lista1/*76.47*/.getTipoUsuario()),format.raw/*76.64*/("""</td>
									<td  style="text-align:left;">"""),_display_(/*77.41*/lista1/*77.47*/.getNameSucursal),format.raw/*77.63*/("""</td>
									<td  style="text-align:center;">
										<a href="#" onclick="agregaComercial('"""),_display_(/*79.50*/lista1/*79.56*/.getId()),format.raw/*79.64*/("""');" data-dismiss="modal">
											<kbd style="background-color: #73C6B6">SEL</kbd>
										</a>
									</td>
								</TR>
				 			""")))}),format.raw/*84.10*/("""
						"""),format.raw/*85.7*/("""</tbody>
					</table>
					<div align="center">
						<div class="row justify-content-md-center" >
							<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<button type="button" class="btn btn-success btn-sm rounded-pill btn-block" data-dismiss="modal">
									CERRAR
								</button>
							</div>
						</div>
					</div>
				</div>
	   		</div>
		</div>
	</div>
	
	<form id="formAgregaComercial" method="post" action="/routes2/comercialAgrega/">
		<input type="hidden" id="id_usuario" name="id_usuario">
	</form>
""")))}),format.raw/*104.2*/("""




"""),format.raw/*109.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*110.31*/("""{"""),format.raw/*110.32*/("""
		  """),format.raw/*111.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*111.36*/("""{"""),format.raw/*111.37*/("""
		    	"""),format.raw/*112.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*114.20*/("""{"""),format.raw/*114.21*/("""
		        	"""),format.raw/*115.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*116.11*/("""}"""),format.raw/*116.12*/("""
		  """),format.raw/*117.5*/("""}"""),format.raw/*117.6*/(""" """),format.raw/*117.7*/(""");
		  
		  $('#tablaAgregaComercial').DataTable("""),format.raw/*119.42*/("""{"""),format.raw/*119.43*/("""
		    	"""),format.raw/*120.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*122.20*/("""{"""),format.raw/*122.21*/("""
		        	"""),format.raw/*123.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*124.11*/("""}"""),format.raw/*124.12*/("""
		  """),format.raw/*125.5*/("""}"""),format.raw/*125.6*/(""" """),format.raw/*125.7*/(""");
		  
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*128.2*/("""}"""),format.raw/*128.3*/(""");
	
	const modificaVigente = (id_comercial) => """),format.raw/*130.44*/("""{"""),format.raw/*130.45*/("""
		"""),format.raw/*131.3*/("""let vigente = $("#vigente_"+id_comercial).val();
		if(vigente==1)"""),format.raw/*132.17*/("""{"""),format.raw/*132.18*/("""
			"""),format.raw/*133.4*/("""modificaVigencia(id_comercial, 0);
			$("#vigente_"+id_comercial).val(0);
		"""),format.raw/*135.3*/("""}"""),format.raw/*135.4*/("""else"""),format.raw/*135.8*/("""{"""),format.raw/*135.9*/("""
			"""),format.raw/*136.4*/("""modificaVigencia(id_comercial, 1);
			$("#vigente_"+id_comercial).val(1);
		"""),format.raw/*138.3*/("""}"""),format.raw/*138.4*/("""
	"""),format.raw/*139.2*/("""}"""),format.raw/*139.3*/("""
	
	"""),format.raw/*141.2*/("""const modificaVigencia = (id_comercial, valor) => """),format.raw/*141.52*/("""{"""),format.raw/*141.53*/("""
		"""),format.raw/*142.3*/("""let formData = new FormData();
		formData.append('id_comercial',id_comercial);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*145.10*/("""{"""),format.raw/*145.11*/("""
            """),format.raw/*146.13*/("""url: "/routes2/modVigComercialAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*153.36*/("""{"""),format.raw/*153.37*/("""
	     		"""),format.raw/*154.9*/("""if(respuesta=="error")"""),format.raw/*154.31*/("""{"""),format.raw/*154.32*/("""
	     			"""),format.raw/*155.10*/("""alertify.alert(msgError, function () """),format.raw/*155.47*/("""{"""),format.raw/*155.48*/("""
		     			"""),format.raw/*156.11*/("""location.href = "/";
		     		"""),format.raw/*157.10*/("""}"""),format.raw/*157.11*/(""");
	     		"""),format.raw/*158.9*/("""}"""),format.raw/*158.10*/("""
	     	"""),format.raw/*159.8*/("""}"""),format.raw/*159.9*/("""
        """),format.raw/*160.9*/("""}"""),format.raw/*160.10*/(""");
	"""),format.raw/*161.2*/("""}"""),format.raw/*161.3*/("""
	
	"""),format.raw/*163.2*/("""const agregaComercial = (id_usuario) =>"""),format.raw/*163.41*/("""{"""),format.raw/*163.42*/("""
		"""),format.raw/*164.3*/("""$("#id_usuario").val(id_usuario);
		document.getElementById("formAgregaComercial").submit();
		
	"""),format.raw/*167.2*/("""}"""),format.raw/*167.3*/("""
"""),format.raw/*168.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listComercial:List[tables.Comercial],listUsuario:List[tables.Usuario]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listComercial,listUsuario)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Comercial],List[tables.Usuario]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listComercial,listUsuario) => apply(mapDiccionario,mapPermiso,userMnu,listComercial,listUsuario)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/comercialMantencion.scala.html
                  HASH: 4a03d2372af08ccdf81e15b97d47b9742ed40551
                  MATRIX: 1061->1|1325->172|1358->180|1374->188|1413->190|1441->193|1509->241|1537->243|1613->294|1698->359|1728->362|2143->751|2187->779|2226->780|2261->788|2332->832|2347->838|2385->855|2457->900|2472->906|2511->924|2628->1014|2643->1020|2672->1028|2709->1038|2724->1044|2758->1057|2824->1096|2863->1097|2902->1108|2988->1167|3003->1173|3032->1181|3071->1201|3110->1202|3149->1213|3227->1264|3242->1270|3271->1278|3318->1294|3354->1303|3411->1330|3444->1336|4954->2820|4996->2846|5035->2847|5071->2856|5143->2901|5158->2907|5191->2919|5264->2965|5279->2971|5311->2982|5384->3028|5399->3034|5437->3051|5510->3097|5525->3103|5562->3119|5686->3216|5701->3222|5730->3230|5901->3370|5935->3377|6495->3906|6528->3911|6619->3973|6649->3974|6682->3979|6742->4010|6772->4011|6808->4019|6951->4133|6981->4134|7022->4146|7129->4224|7159->4225|7192->4230|7221->4231|7250->4232|7328->4281|7358->4282|7394->4290|7537->4404|7567->4405|7608->4417|7715->4495|7745->4496|7778->4501|7807->4502|7836->4503|7942->4581|7971->4582|8048->4630|8078->4631|8109->4634|8203->4699|8233->4700|8265->4704|8369->4780|8398->4781|8430->4785|8459->4786|8491->4790|8595->4866|8624->4867|8654->4869|8683->4870|8715->4874|8794->4924|8824->4925|8855->4928|9006->5050|9036->5051|9078->5064|9347->5304|9377->5305|9414->5314|9465->5336|9495->5337|9534->5347|9600->5384|9630->5385|9670->5396|9729->5426|9759->5427|9798->5438|9828->5439|9864->5447|9893->5448|9930->5457|9960->5458|9992->5462|10021->5463|10053->5467|10121->5506|10151->5507|10182->5510|10307->5607|10336->5608|10365->5609
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|52->21|52->21|52->21|53->22|54->23|54->23|54->23|55->24|55->24|55->24|57->26|57->26|57->26|57->26|57->26|57->26|58->27|58->27|59->28|59->28|59->28|59->28|60->29|60->29|61->30|61->30|61->30|61->30|62->31|63->32|65->34|66->35|103->72|103->72|103->72|104->73|105->74|105->74|105->74|106->75|106->75|106->75|107->76|107->76|107->76|108->77|108->77|108->77|110->79|110->79|110->79|115->84|116->85|135->104|140->109|141->110|141->110|142->111|142->111|142->111|143->112|145->114|145->114|146->115|147->116|147->116|148->117|148->117|148->117|150->119|150->119|151->120|153->122|153->122|154->123|155->124|155->124|156->125|156->125|156->125|159->128|159->128|161->130|161->130|162->131|163->132|163->132|164->133|166->135|166->135|166->135|166->135|167->136|169->138|169->138|170->139|170->139|172->141|172->141|172->141|173->142|176->145|176->145|177->146|184->153|184->153|185->154|185->154|185->154|186->155|186->155|186->155|187->156|188->157|188->157|189->158|189->158|190->159|190->159|191->160|191->160|192->161|192->161|194->163|194->163|194->163|195->164|198->167|198->167|199->168
                  -- GENERATED --
              */
          