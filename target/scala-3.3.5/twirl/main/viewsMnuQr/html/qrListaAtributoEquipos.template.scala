
package viewsMnuQr.html

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

object qrListaAtributoEquipos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[qr.QrAtributoEquipo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listAtributos: List[qr.QrAtributoEquipo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "ATRIBUTOS PARA ASOCIAR AL QR -- CODIFICACION QR","CREAR/MODIFICAR/ELIMINAR")),format.raw/*9.109*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-10 col-sm-6 col-md-6 col-lg-6">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<th style= "text-align: center;">CAMPO</th>
							<th style= "text-align: center;">TIPO</th>
							<th style= "text-align: center;">OBSERVACIONES</th>
							<th style= "text-align: center;">EDIT</th>
							<th style= "text-align: center;">DEL</th>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*23.8*/for(lista <- listAtributos) yield /*23.35*/{_display_(Seq[Any](format.raw/*23.36*/("""
							"""),format.raw/*24.8*/("""<TR>
								<td style="text-align: left;">"""),_display_(/*25.40*/lista/*25.45*/.nombre),format.raw/*25.52*/("""</td>
								<td style="text-align: center;">"""),_display_(/*26.42*/lista/*26.47*/.tipoContenido),format.raw/*26.61*/("""</td>
						        <td style="text-align:left">"""),_display_(/*27.44*/lista/*27.49*/.observaciones),format.raw/*27.63*/("""</td>
								<td style="text-align:center;">
									<a href="/qrEditarAtributoEquipo/"""),_display_(/*29.44*/lista/*29.49*/.id),format.raw/*29.52*/("""">
										<kbd style="background-color: #73C6B6">Edit</kbd>
									</a>
								</td>
								<td style="text-align:center;">
									<a href="/qrDeleteAtributoEquipo/"""),_display_(/*34.44*/lista/*34.49*/.id),format.raw/*34.52*/("""">
										<kbd style="background-color: red">X</kbd>
									</a>
								</td>
							</TR>
						""")))}),format.raw/*39.8*/("""
					"""),format.raw/*40.6*/("""</tbody>
				</table>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-4 col-md-4 col-lg-4">
							<input type="button"  value="AGREGAR NUEVO ATRIBUTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/qrAgregaAtributoEquipo/';">
						</div>
						<div class="col-xs-5 col-sm-3 col-md-3 col-lg-3">
							<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
""")))}),format.raw/*57.2*/("""




"""),format.raw/*62.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*63.31*/("""{"""),format.raw/*63.32*/("""
		  """),format.raw/*64.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*64.36*/("""{"""),format.raw/*64.37*/("""
		    	"""),format.raw/*65.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[10, 50, 100, 200, -1], [10, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*67.20*/("""{"""),format.raw/*67.21*/("""
		        	"""),format.raw/*68.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*69.11*/("""}"""),format.raw/*69.12*/("""
		  """),format.raw/*70.5*/("""}"""),format.raw/*70.6*/(""" """),format.raw/*70.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*72.2*/("""}"""),format.raw/*72.3*/(""");
	
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listAtributos:List[qr.QrAtributoEquipo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listAtributos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[qr.QrAtributoEquipo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listAtributos) => apply(mapDiccionario,mapPermiso,userMnu,listAtributos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/qrListaAtributoEquipos.scala.html
                  HASH: 62da3d377b2a1969ee84fe8bef7cf95ffe1d0e58
                  MATRIX: 1042->1|1274->140|1307->148|1323->156|1362->158|1390->161|1458->209|1486->211|1562->262|1688->367|1718->370|2317->943|2360->970|2399->971|2434->979|2505->1023|2519->1028|2547->1035|2621->1082|2635->1087|2670->1101|2746->1150|2760->1155|2795->1169|2911->1258|2925->1263|2949->1266|3150->1440|3164->1445|3188->1448|3322->1552|3355->1558|3975->2148|4007->2153|4097->2215|4126->2216|4158->2221|4217->2252|4246->2253|4281->2261|4423->2375|4452->2376|4492->2388|4598->2466|4627->2467|4659->2472|4687->2473|4715->2474|4815->2547|4843->2548
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|54->23|54->23|54->23|55->24|56->25|56->25|56->25|57->26|57->26|57->26|58->27|58->27|58->27|60->29|60->29|60->29|65->34|65->34|65->34|70->39|71->40|88->57|93->62|94->63|94->63|95->64|95->64|95->64|96->65|98->67|98->67|99->68|100->69|100->69|101->70|101->70|101->70|103->72|103->72
                  -- GENERATED --
              */
          