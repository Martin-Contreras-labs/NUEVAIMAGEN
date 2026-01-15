
package viewsMnuPpto.html

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

object pptoEditar extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.Ppto],Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listPpto: List[tables.Ppto], numDecimales: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MANTENCION DE PRESUPUESTO : "+ bodega.nombre.toUpperCase(),"CREAR/MODIFICAR/ELIMINAR")),format.raw/*9.119*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>  
						    <TH style= "text-align: center;">AÑO-MES<BR></TH>
							<TH style= "text-align: center;">GRUPO/FAMILIA</TH>
							<TH style= "text-align: center;">VENTA<br>(en """),_display_(/*17.55*/mapDiccionario/*17.69*/.get("PESOS")),format.raw/*17.82*/(""")</TH>
							<TH style= "text-align: center;">"""),_display_(/*18.42*/mapDiccionario/*18.56*/.get("ARRIENDO")),format.raw/*18.72*/("""<br>(en """),_display_(/*18.81*/mapDiccionario/*18.95*/.get("PESOS")),format.raw/*18.108*/(""")</TH>
							<TH style= "text-align: center;">TOTAL<br>(en """),_display_(/*19.55*/mapDiccionario/*19.69*/.get("PESOS")),format.raw/*19.82*/(""")</TH>
							<TH style= "text-align: center;">CONCEPTO</TH>
							<TH style= "text-align: center;" width="5%" >EDITAR<BR></TH>
							<TH style= "text-align: center;" width="5%" >BORRAR<BR></TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*26.8*/for(lista1 <- listPpto) yield /*26.31*/{_display_(Seq[Any](format.raw/*26.32*/("""
							"""),format.raw/*27.8*/("""<TR>
								<td style= "text-align: left;">"""),_display_(/*28.41*/lista1/*28.47*/.anioMes),format.raw/*28.55*/("""</td>
								<td style= "text-align: left;">"""),_display_(/*29.41*/lista1/*29.47*/.nombreGrupo),format.raw/*29.59*/("""</td>
								<td style= "text-align: right;">"""),_display_(/*30.42*/lista1/*30.48*/.ventaConFormato),format.raw/*30.64*/("""</td>
								<td style= "text-align: right;">"""),_display_(/*31.42*/lista1/*31.48*/.arriendoConFormato),format.raw/*31.67*/("""</td>
								<td style= "text-align: right;">"""),_display_(/*32.42*/lista1/*32.48*/.totalConFormato),format.raw/*32.64*/("""</td>
								<td style= "text-align: left;">"""),_display_(/*33.41*/lista1/*33.47*/.concepto),format.raw/*33.56*/("""</td>
								<td style= "text-align: center;">
									<a href="#" onclick= "modificar('"""),_display_(/*35.44*/lista1/*35.50*/.id),format.raw/*35.53*/("""')">
										<kbd style="background-color: #73C6B6">E</kbd>
									</a>
								</td>
								<td style= "text-align: center;">
									<a href="#" onclick= "eliminar('"""),_display_(/*40.43*/lista1/*40.49*/.id),format.raw/*40.52*/("""')">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
			 			""")))}),format.raw/*45.9*/("""
					"""),format.raw/*46.6*/("""</tbody>
					<tfoot>
						<TR>
							<TH style= "text-align: left;">TOTALES</TH>
							<TH style= "text-align: left;"></TH>
							<TH style= "text-align: right;">0</TH>
							<TH style= "text-align: right;">0</TH>
							<TH style= "text-align: right;">0</TH>
							<TH style= "text-align: center;"></TH>
							<TH style= "text-align: center;"></TH>
							<TH style= "text-align: left;"></TH>
						</TR>
		 			</tfoot>
				</table>
				
				<div class="noprint">
					<form id="form" action="/pptoAgregar/" method="POST">
						<div class="row justify-content-md-center" >
							<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*65.62*/bodega/*65.68*/.getId()),format.raw/*65.76*/("""">
								<input type="submit"  value="AGREGAR" class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
								<input type="button"  value="VOLVER" class="btn btn-light btn-sm rounded-pill btn-block" onclick="window.history.back();">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<form id="formModificarLinea" action="/pptoModificar/" method="POST">
		<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*79.56*/bodega/*79.62*/.id),format.raw/*79.65*/("""">
		<input type="hidden" id="selectModifica" name="id_ppto"value="0">
	</form>
	
	<form id="formEliminarLinea" action="/pptoEliminar/" method="POST">
		<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*84.56*/bodega/*84.62*/.id),format.raw/*84.65*/("""">
		<input type="hidden" id="selectElimina" name="id_ppto"value="0">
	</form>
	
""")))}),format.raw/*88.2*/("""




"""),format.raw/*93.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*94.31*/("""{"""),format.raw/*94.32*/("""
		  """),format.raw/*95.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*95.36*/("""{"""),format.raw/*95.37*/("""
		    	"""),format.raw/*96.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*98.20*/("""{"""),format.raw/*98.21*/("""
		        	"""),format.raw/*99.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*100.11*/("""}"""),format.raw/*100.12*/("""
		  """),format.raw/*101.5*/("""}"""),format.raw/*101.6*/(""" """),format.raw/*101.7*/(""");

			let tabla = document.getElementById("tablaPrincipal");
	        let totVta = 0;
	        let totArr = 0;
	        let totTot = 0;
	        for (let i = 2; i < tabla.rows.length-1; i++) """),format.raw/*107.56*/("""{"""),format.raw/*107.57*/(""" 
				"""),format.raw/*108.5*/("""var aux = tabla.rows[i].cells[2].textContent;
				totVta = parseFloat(totVta) + parseFloat(aux.replace(/,/g,''));
				aux = tabla.rows[i].cells[3].textContent;
				totArr = parseFloat(totArr) + parseFloat(aux.replace(/,/g,''));
				aux = tabla.rows[i].cells[4].textContent;
				totTot = parseFloat(totTot) + parseFloat(aux.replace(/,/g,''));
			"""),format.raw/*114.4*/("""}"""),format.raw/*114.5*/("""
	        """),format.raw/*115.10*/("""tabla.rows[tabla.rows.length-1].cells[2].textContent = formatStandar(totVta,'"""),_display_(/*115.88*/numDecimales),format.raw/*115.100*/("""');
	        tabla.rows[tabla.rows.length-1].cells[3].textContent = formatStandar(totArr,'"""),_display_(/*116.88*/numDecimales),format.raw/*116.100*/("""');
	        tabla.rows[tabla.rows.length-1].cells[4].textContent = formatStandar(totTot,'"""),_display_(/*117.88*/numDecimales),format.raw/*117.100*/("""');

		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*120.2*/("""}"""),format.raw/*120.3*/(""");
	
	const modificar = (id_ppto) => """),format.raw/*122.33*/("""{"""),format.raw/*122.34*/("""
		  """),format.raw/*123.5*/("""$("#selectModifica").val(id_ppto);
		  $("#formModificarLinea").submit();
	"""),format.raw/*125.2*/("""}"""),format.raw/*125.3*/("""
	  
	"""),format.raw/*127.2*/("""const eliminar = (id_ppto) => """),format.raw/*127.32*/("""{"""),format.raw/*127.33*/("""
		"""),format.raw/*128.3*/("""alertify.confirm("Esta seguro de eliminar esta línea de presupuesto", function (e) """),format.raw/*128.86*/("""{"""),format.raw/*128.87*/("""
			"""),format.raw/*129.4*/("""if (e) """),format.raw/*129.11*/("""{"""),format.raw/*129.12*/("""
				"""),format.raw/*130.5*/("""$("#selectElimina").val(id_ppto);
			 	$("#formEliminarLinea").submit();
			"""),format.raw/*132.4*/("""}"""),format.raw/*132.5*/("""
		"""),format.raw/*133.3*/("""}"""),format.raw/*133.4*/(""");
	"""),format.raw/*134.2*/("""}"""),format.raw/*134.3*/("""
	
"""),format.raw/*136.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listPpto:List[tables.Ppto],numDecimales:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listPpto,numDecimales)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.Ppto],Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listPpto,numDecimales) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listPpto,numDecimales)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuPpto/pptoEditar.scala.html
                  HASH: 15e7fa686ac42e6caa60d7209fd0b48812457dcd
                  MATRIX: 1050->1|1319->177|1352->185|1368->193|1407->195|1435->198|1503->246|1531->248|1607->299|1743->414|1773->417|2245->862|2268->876|2302->889|2377->937|2400->951|2437->967|2473->976|2496->990|2531->1003|2619->1064|2642->1078|2676->1091|2945->1334|2984->1357|3023->1358|3058->1366|3130->1411|3145->1417|3174->1425|3247->1471|3262->1477|3295->1489|3369->1536|3384->1542|3421->1558|3495->1605|3510->1611|3550->1630|3624->1677|3639->1683|3676->1699|3749->1745|3764->1751|3794->1760|3912->1851|3927->1857|3951->1860|4152->2034|4167->2040|4191->2043|4328->2150|4361->2156|5089->2857|5104->2863|5133->2871|5672->3383|5687->3389|5711->3392|5944->3598|5959->3604|5983->3607|6095->3689|6127->3694|6217->3756|6246->3757|6278->3762|6337->3793|6366->3794|6401->3802|6543->3916|6572->3917|6612->3929|6719->4007|6749->4008|6782->4013|6811->4014|6840->4015|7061->4207|7091->4208|7125->4214|7498->4559|7527->4560|7566->4570|7672->4648|7707->4660|7826->4751|7861->4763|7980->4854|8015->4866|8118->4941|8147->4942|8213->4979|8243->4980|8276->4985|8379->5060|8408->5061|8442->5067|8501->5097|8531->5098|8562->5101|8674->5184|8704->5185|8736->5189|8772->5196|8802->5197|8835->5202|8939->5278|8968->5279|8999->5282|9028->5283|9060->5287|9089->5288|9120->5291
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|48->17|48->17|48->17|49->18|49->18|49->18|49->18|49->18|49->18|50->19|50->19|50->19|57->26|57->26|57->26|58->27|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|66->35|66->35|66->35|71->40|71->40|71->40|76->45|77->46|96->65|96->65|96->65|110->79|110->79|110->79|115->84|115->84|115->84|119->88|124->93|125->94|125->94|126->95|126->95|126->95|127->96|129->98|129->98|130->99|131->100|131->100|132->101|132->101|132->101|138->107|138->107|139->108|145->114|145->114|146->115|146->115|146->115|147->116|147->116|148->117|148->117|151->120|151->120|153->122|153->122|154->123|156->125|156->125|158->127|158->127|158->127|159->128|159->128|159->128|160->129|160->129|160->129|161->130|163->132|163->132|164->133|164->133|165->134|165->134|167->136
                  -- GENERATED --
              */
          