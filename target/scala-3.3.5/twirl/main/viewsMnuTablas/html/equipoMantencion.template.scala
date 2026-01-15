
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

object equipoMantencion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Equipo],List[tables.Propiedad],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listEquipos: List[tables.Equipo], listPropiedad: List[tables.Propiedad]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	
	"""),_display_(/*9.3*/modalEquipoDescripcion()),format.raw/*9.27*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "EQUIPOS: CREAR/MODIFICAR/ELIMINAR", "")),format.raw/*12.72*/("""
		"""),format.raw/*13.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
			
			
				<div class='noprint'>
					<table class='table table-sm table-condensed table-fluid'>
						<tr>
							<td>
								<div align='center'>
									<button type='button'  class='btn btn-sm btn-success' onclick="document.getElementById('excel').submit()">
										Exportar a Excel
									</button>
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<form id="excel" class="formulario" method="post" action="/routes2/equipoMantencionExcel/"> </form>
						
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style="vertical-align: top;">GRUPO</TH>
							<TH>PROPIEDAD</TH>
							<TH>IMG</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>UNIDAD</TH>
							<TH>EDIT</TH>
							<TH>VIGENTE</TH>
							<TH>DEL</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*49.8*/for(lista1 <- listEquipos) yield /*49.34*/{_display_(Seq[Any](format.raw/*49.35*/("""
							"""),format.raw/*50.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*51.39*/lista1/*51.45*/.getGrupo()),format.raw/*51.56*/("""</td>
								<td style="text-align:center;">

									<select class="form-control form-control-sm"  onchange="cambiaPropiedad(value,'"""),_display_(/*54.90*/lista1/*54.96*/.getId()),format.raw/*54.104*/("""')">
										<option value=""""),_display_(/*55.27*/lista1/*55.33*/.getId_propiedad()),format.raw/*55.51*/("""">"""),_display_(/*55.54*/lista1/*55.60*/.getPropiedad()),format.raw/*55.75*/("""</option>
										"""),_display_(/*56.12*/for(lista2 <- listPropiedad) yield /*56.40*/{_display_(Seq[Any](format.raw/*56.41*/("""
											"""),format.raw/*57.12*/("""<option value=""""),_display_(/*57.28*/lista2/*57.34*/.getId()),format.raw/*57.42*/("""">"""),_display_(/*57.45*/lista2/*57.51*/.getNombre()),format.raw/*57.63*/("""</option>
										""")))}),format.raw/*58.12*/("""
									"""),format.raw/*59.10*/("""</select>
								</td>
								<td>
									"""),_display_(if(lista1.getImg()!="0")/*62.35*/{_display_(Seq[Any](format.raw/*62.36*/("""
										"""),format.raw/*63.11*/("""<a href="#" onclick="selectModal('"""),_display_(/*63.46*/lista1/*63.52*/.getId()),format.raw/*63.60*/("""','"""),_display_(/*63.64*/lista1/*63.70*/.getImg()),format.raw/*63.79*/("""')">
											<img src='/viewImg/"""),_display_(/*64.32*/lista1/*64.38*/.getImg()),format.raw/*64.47*/("""' width="auto" height="25px">
										</a>
										
									""")))} else {null} ),format.raw/*67.11*/("""
								"""),format.raw/*68.9*/("""</td>
								<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*69.74*/lista1/*69.80*/.getCodigo()),format.raw/*69.92*/("""');">"""),_display_(/*69.98*/lista1/*69.104*/.getCodigo()),format.raw/*69.116*/("""</a></td>
								<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*70.74*/lista1/*70.80*/.getCodigo()),format.raw/*70.92*/("""');"><div id=""""),_display_(/*70.107*/lista1/*70.113*/.getId()),format.raw/*70.121*/("""">"""),_display_(/*70.124*/lista1/*70.130*/.getNombre()),format.raw/*70.142*/("""</div></a></td>
								
								<td style="text-align:center;">"""),_display_(/*72.41*/lista1/*72.47*/.getUnidad()),format.raw/*72.59*/("""</td>

								<td style="text-align:center;">
									<a href="/equipoModifica/"""),_display_(/*75.36*/lista1/*75.42*/.getId()),format.raw/*75.50*/("""">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
								<td style="text-align: center">
						        	"""),_display_(if(lista1.getVigente==1)/*80.41*/{_display_(Seq[Any](format.raw/*80.42*/("""
						        		"""),format.raw/*81.17*/("""<select class="form-control form-control-sm"  onchange="cambiaEstado(value,'"""),_display_(/*81.94*/lista1/*81.100*/.getId()),format.raw/*81.108*/("""')">
								        	<option value="1">Vigente</option>
							            	<option value="0">NO</option>
								        </select>
						        	""")))}else/*85.21*/{_display_(Seq[Any](format.raw/*85.22*/("""
						        		"""),format.raw/*86.17*/("""<select class="form-control form-control-sm"  onchange="cambiaEstado(value,'"""),_display_(/*86.94*/lista1/*86.100*/.getId()),format.raw/*86.108*/("""')">
						        			<option value="0">NO</option>
								        	<option value="1">Vigente</option>
								        </select>
						        	""")))}),format.raw/*90.17*/("""
						        """),format.raw/*91.15*/("""</td>
								<td style="text-align:center;">
									<a href="#" onclick= "eliminarEquipo('"""),_display_(/*93.49*/lista1/*93.55*/.getId()),format.raw/*93.63*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*98.9*/("""
					"""),format.raw/*99.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR NUEVO EQUIPO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/equipoNuevo/1';">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalImgEquipo' class="modal" role="dialog" data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
				   	<h5 id="nombreEquipo" class="modal-title">
			           nombre de equipo
			        </h5>
			        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
			        	<span aria-hidden='true'>&times;</span>
			        </button>
				</div>
	     		<div class="modal-body">
		     		<div align="center">
		     			<div id="imagenEquipo"></div>
						<button type="button" class="btn btn-success btn-sm rounded-pill btn-block" data-dismiss="modal" >
							CERRAR
						</button>
					</div>
				</div>
	   		</div>
		</div>
	</div>
	
	<form id="form_eliminar" method="post" action="/equipoElimina/">
		<input type="hidden" id="form_id_equipo" name="id_equipo">
	</form>
""")))}),format.raw/*141.2*/("""


"""),format.raw/*144.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*145.31*/("""{"""),format.raw/*145.32*/("""
		  """),format.raw/*146.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*146.36*/("""{"""),format.raw/*146.37*/("""
		    	"""),format.raw/*147.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 0, "asc" ],[ 3, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*150.20*/("""{"""),format.raw/*150.21*/("""
		        	"""),format.raw/*151.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*152.11*/("""}"""),format.raw/*152.12*/("""
		  """),format.raw/*153.5*/("""}"""),format.raw/*153.6*/(""" """),format.raw/*153.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*155.2*/("""}"""),format.raw/*155.3*/(""");
	const eliminarEquipo = (id_equipo) => """),format.raw/*156.40*/("""{"""),format.raw/*156.41*/("""
		"""),format.raw/*157.3*/("""let nombre = $("#"+id_equipo).text();
		alertify.confirm("Esta seguro de eliminar el equipo: "+nombre, function (e) """),format.raw/*158.79*/("""{"""),format.raw/*158.80*/("""
			"""),format.raw/*159.4*/("""if (e) """),format.raw/*159.11*/("""{"""),format.raw/*159.12*/("""
				"""),format.raw/*160.5*/("""$("#form_id_equipo").val(id_equipo);
				document.getElementById("form_eliminar").submit();
			"""),format.raw/*162.4*/("""}"""),format.raw/*162.5*/("""
		"""),format.raw/*163.3*/("""}"""),format.raw/*163.4*/(""");
	"""),format.raw/*164.2*/("""}"""),format.raw/*164.3*/("""
	"""),format.raw/*165.2*/("""const selectModal = (id_equipo,imagen) =>"""),format.raw/*165.43*/("""{"""),format.raw/*165.44*/("""
		"""),format.raw/*166.3*/("""$("#nombreEquipo").text($("#"+id_equipo).text());
		document.getElementById('imagenEquipo').innerHTML = "<img src='/viewImg/"+imagen+"' width='auto' height='auto'>";
	      $("#modalImgEquipo").modal("show");
	  """),format.raw/*169.4*/("""}"""),format.raw/*169.5*/("""
	
	"""),format.raw/*171.2*/("""const cambiaEstado = (estado,id_equipo) =>"""),format.raw/*171.44*/("""{"""),format.raw/*171.45*/("""
    	"""),format.raw/*172.6*/("""var formData = new FormData();
	  	formData.append('id_equipo',id_equipo);
	  	formData.append('estado',estado);
        $.ajax("""),format.raw/*175.16*/("""{"""),format.raw/*175.17*/("""
            """),format.raw/*176.13*/("""url: "/routes2/equipoCambiaEstado/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*183.36*/("""{"""),format.raw/*183.37*/("""
	     		"""),format.raw/*184.9*/("""if(!respuesta.status)"""),format.raw/*184.30*/("""{"""),format.raw/*184.31*/("""
	     			"""),format.raw/*185.10*/("""if(respuesta.conInventario)"""),format.raw/*185.37*/("""{"""),format.raw/*185.38*/("""
	     				"""),format.raw/*186.11*/("""alertify.alert("No puede cambiar el estado debido a que existe inventario, debe no existir inventario para cambiar el estado", function () """),format.raw/*186.150*/("""{"""),format.raw/*186.151*/(""" """),format.raw/*186.152*/("""location.reload(); """),format.raw/*186.171*/("""}"""),format.raw/*186.172*/(""");
	     				
	     			"""),format.raw/*188.10*/("""}"""),format.raw/*188.11*/("""else"""),format.raw/*188.15*/("""{"""),format.raw/*188.16*/("""
	     				"""),format.raw/*189.11*/("""location.reload();
					"""),format.raw/*190.6*/("""}"""),format.raw/*190.7*/("""
	     			
				"""),format.raw/*192.5*/("""}"""),format.raw/*192.6*/("""
	     	"""),format.raw/*193.8*/("""}"""),format.raw/*193.9*/("""
        """),format.raw/*194.9*/("""}"""),format.raw/*194.10*/(""");
	"""),format.raw/*195.2*/("""}"""),format.raw/*195.3*/("""

	"""),format.raw/*197.2*/("""const cambiaPropiedad = (id_propiedad,id_equipo) =>"""),format.raw/*197.53*/("""{"""),format.raw/*197.54*/("""
		"""),format.raw/*198.3*/("""var formData = new FormData();
		formData.append('id_equipo',id_equipo);
		formData.append('id_propiedad',id_propiedad);
		$.ajax("""),format.raw/*201.10*/("""{"""),format.raw/*201.11*/("""
			"""),format.raw/*202.4*/("""url: "/routes2/equipoCambiaPropiedad/",
			type: "POST",
			method: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(respuesta)"""),format.raw/*209.32*/("""{"""),format.raw/*209.33*/("""
				"""),format.raw/*210.5*/("""if( ! respuesta.status)"""),format.raw/*210.28*/("""{"""),format.raw/*210.29*/("""
					"""),format.raw/*211.6*/("""location.reload();
				"""),format.raw/*212.5*/("""}"""),format.raw/*212.6*/("""
			"""),format.raw/*213.4*/("""}"""),format.raw/*213.5*/("""
		"""),format.raw/*214.3*/("""}"""),format.raw/*214.4*/(""");
	"""),format.raw/*215.2*/("""}"""),format.raw/*215.3*/("""
	
	
	
"""),format.raw/*219.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listEquipos:List[tables.Equipo],listPropiedad:List[tables.Propiedad]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listEquipos,listPropiedad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Equipo],List[tables.Propiedad]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listEquipos,listPropiedad) => apply(mapDiccionario,mapPermiso,userMnu,listEquipos,listPropiedad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/equipoMantencion.scala.html
                  HASH: 9c74d75e398593ab2d28e4dfe3a8abc7492bddfb
                  MATRIX: 1057->1|1320->171|1353->179|1369->187|1408->189|1437->193|1505->241|1535->246|1579->270|1610->274|1687->325|1776->393|1806->396|2878->1442|2920->1468|2959->1469|2994->1477|3064->1520|3079->1526|3111->1537|3274->1673|3289->1679|3319->1687|3377->1718|3392->1724|3431->1742|3461->1745|3476->1751|3512->1766|3560->1787|3604->1815|3643->1816|3683->1828|3726->1844|3741->1850|3770->1858|3800->1861|3815->1867|3848->1879|3900->1900|3938->1910|4036->1981|4075->1982|4114->1993|4176->2028|4191->2034|4220->2042|4251->2046|4266->2052|4296->2061|4359->2097|4374->2103|4404->2112|4514->2178|4550->2187|4656->2266|4671->2272|4704->2284|4737->2290|4753->2296|4787->2308|4897->2391|4912->2397|4945->2409|4988->2424|5004->2430|5034->2438|5065->2441|5081->2447|5115->2459|5207->2524|5222->2530|5255->2542|5364->2624|5379->2630|5408->2638|5603->2806|5642->2807|5687->2824|5791->2901|5807->2907|5837->2915|6009->3068|6048->3069|6093->3086|6197->3163|6213->3169|6243->3177|6420->3323|6463->3338|6584->3432|6599->3438|6628->3446|6765->3553|6798->3559|8317->5047|8348->5050|8439->5112|8469->5113|8502->5118|8562->5149|8592->5150|8628->5158|8829->5330|8859->5331|8900->5343|9007->5421|9037->5422|9070->5427|9099->5428|9128->5429|9229->5502|9258->5503|9329->5545|9359->5546|9390->5549|9535->5665|9565->5666|9597->5670|9633->5677|9663->5678|9696->5683|9819->5778|9848->5779|9879->5782|9908->5783|9940->5787|9969->5788|9999->5790|10069->5831|10099->5832|10130->5835|10370->6047|10399->6048|10431->6052|10502->6094|10532->6095|10566->6101|10723->6229|10753->6230|10795->6243|11063->6482|11093->6483|11130->6492|11180->6513|11210->6514|11249->6524|11305->6551|11335->6552|11375->6563|11544->6702|11575->6703|11606->6704|11655->6723|11686->6724|11738->6747|11768->6748|11801->6752|11831->6753|11871->6764|11923->6788|11952->6789|11995->6804|12024->6805|12060->6813|12089->6814|12126->6823|12156->6824|12188->6828|12217->6829|12248->6832|12328->6883|12358->6884|12389->6887|12548->7017|12578->7018|12610->7022|12828->7211|12858->7212|12891->7217|12943->7240|12973->7241|13007->7247|13058->7270|13087->7271|13119->7275|13148->7276|13179->7279|13208->7280|13240->7284|13269->7285|13304->7292
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|40->9|40->9|42->11|43->12|43->12|44->13|80->49|80->49|80->49|81->50|82->51|82->51|82->51|85->54|85->54|85->54|86->55|86->55|86->55|86->55|86->55|86->55|87->56|87->56|87->56|88->57|88->57|88->57|88->57|88->57|88->57|88->57|89->58|90->59|93->62|93->62|94->63|94->63|94->63|94->63|94->63|94->63|94->63|95->64|95->64|95->64|98->67|99->68|100->69|100->69|100->69|100->69|100->69|100->69|101->70|101->70|101->70|101->70|101->70|101->70|101->70|101->70|101->70|103->72|103->72|103->72|106->75|106->75|106->75|111->80|111->80|112->81|112->81|112->81|112->81|116->85|116->85|117->86|117->86|117->86|117->86|121->90|122->91|124->93|124->93|124->93|129->98|130->99|172->141|175->144|176->145|176->145|177->146|177->146|177->146|178->147|181->150|181->150|182->151|183->152|183->152|184->153|184->153|184->153|186->155|186->155|187->156|187->156|188->157|189->158|189->158|190->159|190->159|190->159|191->160|193->162|193->162|194->163|194->163|195->164|195->164|196->165|196->165|196->165|197->166|200->169|200->169|202->171|202->171|202->171|203->172|206->175|206->175|207->176|214->183|214->183|215->184|215->184|215->184|216->185|216->185|216->185|217->186|217->186|217->186|217->186|217->186|217->186|219->188|219->188|219->188|219->188|220->189|221->190|221->190|223->192|223->192|224->193|224->193|225->194|225->194|226->195|226->195|228->197|228->197|228->197|229->198|232->201|232->201|233->202|240->209|240->209|241->210|241->210|241->210|242->211|243->212|243->212|244->213|244->213|245->214|245->214|246->215|246->215|250->219
                  -- GENERATED --
              */
          