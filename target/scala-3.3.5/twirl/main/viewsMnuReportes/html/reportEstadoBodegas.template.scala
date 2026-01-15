
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

object reportEstadoBodegas extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
datos: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalContactoBodega(mapDiccionario)),format.raw/*8.38*/("""
	"""),_display_(/*9.3*/modalContactoCliente()),format.raw/*9.25*/("""
	"""),_display_(/*10.3*/modalContactoProyecto()),format.raw/*10.26*/("""
	
	"""),format.raw/*12.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*13.4*/barraTitulo(mapDiccionario, "ESTADO DE "+mapDiccionario.get("BODEGAS")+"/PROYECTO","(solo externas)")),format.raw/*13.105*/("""
		
		"""),format.raw/*15.3*/("""<div class="noprint">
			<table class="table table-sm table-condensed table-fluid">
				<tr>
					<td>
						<div align="center">
							<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
								Exportar a Excel
							</button>
							<button type="button"  class="btn btn-sm btn-success" 
								onclick="location.href='/home/'">
								Cerrar
							</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<form id="excel" class="formulario" method="post" action="/routes2/reportEstadoBodegasExcel/">
		</form>
			
		<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="noprint">
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
						</tr>
					</table>
				</div>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				        <thead style="background-color: #eeeeee">
							<TR> 
						        <TH colspan="7" style= "text-align: center;vertical-align: top;"></TH>
								<TH colspan="3" style= "text-align: center;vertical-align: top;">PRIMER DESPACHO</TH>
								<TH colspan="3" style= "text-align: center;vertical-align: top;">ULTIMA DEVOLUCION</TH>
								<TH style= "text-align: center;vertical-align: top;"></TH>
							</TR>
					        <TR> 
						        <TH style= "text-align: center;vertical-align: top;">SUCURSAL</TH>
						        <TH style= "text-align: center;vertical-align: top;">"""),_display_(/*66.69*/mapDiccionario/*66.83*/.get("BODEGA")),format.raw/*66.97*/("""/PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">VIGENTE</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE CLIENTE</TH>
								<TH style= "text-align: center;vertical-align: top;">RUT CLIENTE</TH>
								<TH style= "text-align: center;vertical-align: top;">NOMBRE PROYECTO</TH>
								<TH style= "text-align: center;vertical-align: top;">COMERCIAL</TH>
								<TH style= "text-align: center;vertical-align: top;">NRO GUIA</TH>
								<TH style= "text-align: center;vertical-align: top;">REF GUIA</TH>
								<TH style= "text-align: center;vertical-align: top; min-width: 80px;">FECHA GUIA</TH>
								<TH style= "text-align: center;vertical-align: top;">NRO GUIA</TH>
								<TH style= "text-align: center;vertical-align: top;">REF GUIA</TH>
								<TH style= "text-align: center;vertical-align: top; min-width: 80px;">FECHA GUIA</TH>
								<TH style= "text-align: center;vertical-align: top;">CANTIDAD<br>EQUIPOS</TH>
							</TR>
						</thead>
						<tbody>
							"""),_display_(/*82.9*/for(lista1 <- datos) yield /*82.29*/{_display_(Seq[Any](format.raw/*82.30*/("""
								"""),format.raw/*83.9*/("""<TR>
									<td style="text-align:center;">"""),_display_(/*84.42*/lista1/*84.48*/.get(0)),format.raw/*84.55*/("""</td>
									<td style="text-align:left;">"""),_display_(/*85.40*/lista1/*85.46*/.get(1)),format.raw/*85.53*/("""</td>
									<td style="text-align:center;">"""),_display_(/*86.42*/lista1/*86.48*/.get(2)),format.raw/*86.55*/("""</td>
									<td style="text-align:left;">"""),_display_(/*87.40*/lista1/*87.46*/.get(3)),format.raw/*87.53*/("""</td>
									<td style="text-align:center;">"""),_display_(/*88.42*/lista1/*88.48*/.get(4)),format.raw/*88.55*/("""</td>
									<td style="text-align:left;">"""),_display_(/*89.40*/lista1/*89.46*/.get(5)),format.raw/*89.53*/("""</td>
									<td style="text-align:left;">"""),_display_(/*90.40*/lista1/*90.46*/.get(6)),format.raw/*90.53*/("""</td>
									<td style="text-align:center;">"""),_display_(/*91.42*/lista1/*91.48*/.get(7)),format.raw/*91.55*/("""</td>
									<td style="text-align:center;">"""),_display_(/*92.42*/lista1/*92.48*/.get(8)),format.raw/*92.55*/("""</td>
									<td style="text-align:center;">
										<div style="display: none;">"""),_display_(/*94.40*/lista1/*94.46*/.get(9)),format.raw/*94.53*/("""</div>
										"""),_display_(/*95.12*/utilities/*95.21*/.Fechas.DDMMAA(lista1.get(9))),format.raw/*95.50*/("""
									"""),format.raw/*96.10*/("""</td>
									<td style="text-align:center;">"""),_display_(/*97.42*/lista1/*97.48*/.get(10)),format.raw/*97.56*/("""</td>
									<td style="text-align:center;">"""),_display_(/*98.42*/lista1/*98.48*/.get(11)),format.raw/*98.56*/("""</td>
									<td style="text-align:center;">
										<div style="display: none;">"""),_display_(/*100.40*/lista1/*100.46*/.get(12)),format.raw/*100.54*/("""</div>
										"""),_display_(/*101.12*/utilities/*101.21*/.Fechas.DDMMAA(lista1.get(12))),format.raw/*101.51*/("""
									"""),format.raw/*102.10*/("""</td>
									<td style="text-align:right;">"""),_display_(/*103.41*/lista1/*103.47*/.get(13)),format.raw/*103.55*/("""</td>
								</TR>
				 			""")))}),format.raw/*105.10*/("""
						"""),format.raw/*106.7*/("""</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


""")))}),format.raw/*114.2*/("""


"""),format.raw/*117.1*/("""<script type="text/javascript">
	let flag = true;
	$(document).ready(function() """),format.raw/*119.31*/("""{"""),format.raw/*119.32*/("""

		 """),format.raw/*121.4*/("""$('#tablaPrincipal').DataTable("""),format.raw/*121.35*/("""{"""),format.raw/*121.36*/("""
		    	"""),format.raw/*122.8*/(""""fixedHeader": true,
		    	"paging": false,
				"info": false,
				"searching": false,
		    	"order": [[ 1, "asc" ]],
		    	"language": """),format.raw/*127.20*/("""{"""),format.raw/*127.21*/("""
		        	"""),format.raw/*128.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*129.11*/("""}"""),format.raw/*129.12*/("""
		  """),format.raw/*130.5*/("""}"""),format.raw/*130.6*/(""" """),format.raw/*130.7*/(""");

			document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*133.2*/("""}"""),format.raw/*133.3*/(""");
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,datos:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,datos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,datos) => apply(mapDiccionario,mapPermiso,userMnu,datos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportEstadoBodegas.scala.html
                  HASH: 435bd3f0da8a9bcd705a0a6c62268c5ccd9fbcca
                  MATRIX: 1038->1|1255->125|1287->132|1303->140|1342->142|1371->146|1439->194|1469->199|1524->234|1552->237|1594->259|1623->262|1667->285|1698->289|1775->340|1898->441|1931->447|3903->2392|3926->2406|3961->2420|5017->3450|5053->3470|5092->3471|5128->3480|5201->3526|5216->3532|5244->3539|5316->3584|5331->3590|5359->3597|5433->3644|5448->3650|5476->3657|5548->3702|5563->3708|5591->3715|5665->3762|5680->3768|5708->3775|5780->3820|5795->3826|5823->3833|5895->3878|5910->3884|5938->3891|6012->3938|6027->3944|6055->3951|6129->3998|6144->4004|6172->4011|6285->4097|6300->4103|6328->4110|6373->4128|6391->4137|6441->4166|6479->4176|6553->4223|6568->4229|6597->4237|6671->4284|6686->4290|6715->4298|6829->4384|6845->4390|6875->4398|6921->4416|6940->4425|6992->4455|7031->4465|7105->4511|7121->4517|7151->4525|7212->4554|7247->4561|7342->4625|7373->4628|7482->4708|7512->4709|7545->4714|7605->4745|7635->4746|7671->4754|7839->4893|7869->4894|7910->4906|8017->4984|8047->4985|8080->4990|8109->4991|8138->4992|8239->5065|8268->5066
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|46->15|97->66|97->66|97->66|113->82|113->82|113->82|114->83|115->84|115->84|115->84|116->85|116->85|116->85|117->86|117->86|117->86|118->87|118->87|118->87|119->88|119->88|119->88|120->89|120->89|120->89|121->90|121->90|121->90|122->91|122->91|122->91|123->92|123->92|123->92|125->94|125->94|125->94|126->95|126->95|126->95|127->96|128->97|128->97|128->97|129->98|129->98|129->98|131->100|131->100|131->100|132->101|132->101|132->101|133->102|134->103|134->103|134->103|136->105|137->106|145->114|148->117|150->119|150->119|152->121|152->121|152->121|153->122|158->127|158->127|159->128|160->129|160->129|161->130|161->130|161->130|164->133|164->133
                  -- GENERATED --
              */
          