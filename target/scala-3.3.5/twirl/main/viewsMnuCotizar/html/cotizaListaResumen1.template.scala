
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

object cotizaListaResumen1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.Cliente,tables.Proyecto,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listCoti: List[List[String]], cliente: tables.Cliente, proyecto: tables.Proyecto):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	
	"""),format.raw/*10.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*11.4*/barraTitulo(mapDiccionario, "SELECCION DE COTIZACIONES POR CLIENTE","CLIENTE: "+cliente.getRut()+" - "+cliente.getNickName())),format.raw/*11.129*/("""
		"""),format.raw/*12.3*/("""<form method="post" action="/routes2/cotizaListaResumen2/" onsubmit="return validarForm();">
			<input type="hidden" id="cambiosDeEstados" name="cambiosDeEstados">
			<input type="hidden" name="id_cliente" value=""""),_display_(/*14.51*/cliente/*14.58*/.getId()),format.raw/*14.66*/("""">
			<input type="hidden" name="id_proyecto" value=""""),_display_(/*15.52*/proyecto/*15.60*/.getId()),format.raw/*15.68*/("""">
			
			<div class="row  justify-content-md-center">
				<div class="col-xs-11 col-sm-9 col-md-9 col-lg-9">
				<h5>CLIENTE: """),_display_(/*19.19*/cliente/*19.26*/.getNickName()),format.raw/*19.40*/("""</h5>
				<h5>PROYECTO: """),_display_(/*20.20*/proyecto/*20.28*/.getNickName()),format.raw/*20.42*/("""</h5>
				<br>
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						<thead style="background-color: #eeeeee">
							<TR>
								<TH>SUCURSAL</TH>
								<TH>COMERCIAL</TH>
								<TH>Nro.COTI</TH>
								<TH style="min-width:80px;">FECHA<br>COTIZACION</TH>
								<TH>OBSERVACIONES</TH>
								<TH>SELECCIONAR</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*34.9*/for(lista1 <- listCoti) yield /*34.32*/{_display_(Seq[Any](format.raw/*34.33*/("""
								"""),format.raw/*35.9*/("""<TR>
									<td style="text-align:left;">"""),_display_(/*36.40*/lista1/*36.46*/.get(1)),format.raw/*36.53*/("""</td>
									<td style="text-align:left;">"""),_display_(/*37.40*/lista1/*37.46*/.get(2)),format.raw/*37.53*/("""</td>
									<td style="text-align:center;">
										<a href="#" onclick="verCotizacion('"""),_display_(/*39.48*/lista1/*39.54*/.get(0)),format.raw/*39.61*/("""')">
											<kbd style="background-color: rgb(90, 200, 245)">
												<font color="green">"""),_display_(/*41.34*/lista1/*41.40*/.get(3)),format.raw/*41.47*/("""</font>
											</kbd>
										</a>
									</td>
									<td style="text-align:center;">
										<div style="display:none">"""),_display_(/*46.38*/lista1/*46.44*/.get(4)),format.raw/*46.51*/("""</div>
										"""),_display_(/*47.12*/utilities/*47.21*/.Fechas.DDMMAA(lista1.get(4))),format.raw/*47.50*/("""
									"""),format.raw/*48.10*/("""</td>
									<td style="text-align:left;">"""),_display_(/*49.40*/lista1/*49.46*/.get(5)),format.raw/*49.53*/("""</td>
									<td  style="text-align:center;">
										<input type="checkbox" id=""""),_display_(/*51.39*/lista1/*51.45*/.get(0)),format.raw/*51.52*/("""" value ="0" onchange="cambiaEstado(id,value)"></td>
									</td>
								</TR>
				 			""")))}),format.raw/*54.10*/("""
						"""),format.raw/*55.7*/("""</tbody>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="Cancelar" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit" id="btnSubmit" value='HACER RESUMEN' class="btn btn-primary btn-sm rounded-pill btn-block">
					</div>
				</div>
			</div>
		</form>
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
	
	
	

""")))}),format.raw/*96.2*/("""


"""),format.raw/*99.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*100.31*/("""{"""),format.raw/*100.32*/("""
		  """),format.raw/*101.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*101.36*/("""{"""),format.raw/*101.37*/("""
		    	"""),format.raw/*102.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 3, "desc" ],[ 2, "desc" ]],
		    	"language": """),format.raw/*105.20*/("""{"""),format.raw/*105.21*/("""
		        	"""),format.raw/*106.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*107.11*/("""}"""),format.raw/*107.12*/("""
		  """),format.raw/*108.5*/("""}"""),format.raw/*108.6*/(""" """),format.raw/*108.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*110.2*/("""}"""),format.raw/*110.3*/(""");
	
	
	const verCotizacion = (id_cotizacion) => """),format.raw/*113.43*/("""{"""),format.raw/*113.44*/("""
		"""),format.raw/*114.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*116.16*/("""{"""),format.raw/*116.17*/("""
            """),format.raw/*117.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*124.36*/("""{"""),format.raw/*124.37*/("""
	     		"""),format.raw/*125.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*127.8*/("""}"""),format.raw/*127.9*/("""
        """),format.raw/*128.9*/("""}"""),format.raw/*128.10*/(""");
	"""),format.raw/*129.2*/("""}"""),format.raw/*129.3*/("""
	
	"""),format.raw/*131.2*/("""const cambiaEstado = (id_cotizacion, valor) => """),format.raw/*131.49*/("""{"""),format.raw/*131.50*/("""
		"""),format.raw/*132.3*/("""if(valor == 0) """),format.raw/*132.18*/("""{"""),format.raw/*132.19*/("""
			"""),format.raw/*133.4*/("""document.getElementById(id_cotizacion).value = "1";
			let aux = ""+$("#cambiosDeEstados").val()+id_cotizacion + ","
			$("#cambiosDeEstados").val(aux);
		"""),format.raw/*136.3*/("""}"""),format.raw/*136.4*/("""else"""),format.raw/*136.8*/("""{"""),format.raw/*136.9*/("""
			"""),format.raw/*137.4*/("""document.getElementById(id_cotizacion).value = "0";
			let aux = ""+id_cotizacion + ","
			$("#cambiosDeEstados").val($("#cambiosDeEstados").val().replace(aux,""));
		"""),format.raw/*140.3*/("""}"""),format.raw/*140.4*/("""
		
	"""),format.raw/*142.2*/("""}"""),format.raw/*142.3*/("""
	
	"""),format.raw/*144.2*/("""const validarForm = () =>"""),format.raw/*144.27*/("""{"""),format.raw/*144.28*/("""
		"""),format.raw/*145.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*147.2*/("""}"""),format.raw/*147.3*/("""
		
		
	
	
	
"""),format.raw/*153.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listCoti:List[List[String]],cliente:tables.Cliente,proyecto:tables.Proyecto): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listCoti,cliente,proyecto)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],tables.Cliente,tables.Proyecto) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listCoti,cliente,proyecto) => apply(mapDiccionario,mapPermiso,userMnu,listCoti,cliente,proyecto)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/cotizaListaResumen1.scala.html
                  HASH: 8d6435bdbe23201e5b71bea84bfbdada95b8cb89
                  MATRIX: 1068->1|1340->180|1373->188|1389->196|1428->198|1457->202|1525->250|1558->256|1635->307|1782->432|1812->435|2053->649|2069->656|2098->664|2179->718|2196->726|2225->734|2380->862|2396->869|2431->883|2483->908|2500->916|2535->930|2997->1366|3036->1389|3075->1390|3111->1399|3182->1443|3197->1449|3225->1456|3297->1501|3312->1507|3340->1514|3461->1608|3476->1614|3504->1621|3630->1720|3645->1726|3673->1733|3834->1867|3849->1873|3877->1880|3922->1898|3940->1907|3990->1936|4028->1946|4100->1991|4115->1997|4143->2004|4256->2090|4271->2096|4299->2103|4421->2194|4455->2201|5866->3582|5896->3585|5987->3647|6017->3648|6050->3653|6110->3684|6140->3685|6176->3693|6366->3854|6396->3855|6437->3867|6544->3945|6574->3946|6607->3951|6636->3952|6665->3953|6766->4026|6795->4027|6873->4076|6903->4077|6934->4080|7061->4178|7091->4179|7133->4192|7392->4422|7422->4423|7459->4432|7612->4557|7641->4558|7678->4567|7708->4568|7740->4572|7769->4573|7801->4577|7877->4624|7907->4625|7938->4628|7982->4643|8012->4644|8044->4648|8227->4803|8256->4804|8288->4808|8317->4809|8349->4813|8544->4980|8573->4981|8606->4986|8635->4987|8667->4991|8721->5016|8751->5017|8782->5020|8866->5076|8895->5077|8936->5090
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|41->10|42->11|42->11|43->12|45->14|45->14|45->14|46->15|46->15|46->15|50->19|50->19|50->19|51->20|51->20|51->20|65->34|65->34|65->34|66->35|67->36|67->36|67->36|68->37|68->37|68->37|70->39|70->39|70->39|72->41|72->41|72->41|77->46|77->46|77->46|78->47|78->47|78->47|79->48|80->49|80->49|80->49|82->51|82->51|82->51|85->54|86->55|127->96|130->99|131->100|131->100|132->101|132->101|132->101|133->102|136->105|136->105|137->106|138->107|138->107|139->108|139->108|139->108|141->110|141->110|144->113|144->113|145->114|147->116|147->116|148->117|155->124|155->124|156->125|158->127|158->127|159->128|159->128|160->129|160->129|162->131|162->131|162->131|163->132|163->132|163->132|164->133|167->136|167->136|167->136|167->136|168->137|171->140|171->140|173->142|173->142|175->144|175->144|175->144|176->145|178->147|178->147|184->153
                  -- GENERATED --
              */
          