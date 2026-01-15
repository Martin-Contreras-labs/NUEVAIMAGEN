
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

object usuarioAgrega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.UsuarioTipo],List[List[Long]],List[tables.Sucursal],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listTipoUsuario: List[tables.UsuarioTipo], listEsPorProyecto: List[List[Long]],listSucursal: List[tables.Sucursal]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "AGREGAR NUEVO USUARIO", "")),format.raw/*9.60*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/usuarioGraba/">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align:left;">USUARIO (userName): </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									id="userName"
									name="userName"
									onkeydown="return sinReservados(window.event)"
									autocomplete="off"
									maxlength="100"
									required
									onchange="value = value.trim(); verificaUserName(value);">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">CLAVE (userKey): </td>
							<td style="text-align:left;">
								<input type="password" class="form-control left"
									name="userKey"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">NOMBRE (fullName): </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="nombre"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">CARGO: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="cargo"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">E-MAIL: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="email"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">TELEFONO: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="fono"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">TIPO USUARIO: </td>
							<td style="text-align:left;">
								<select class="custom-select" 
									name="id_tipoUsuario" 
									onchange="cambiaMensaje(value);">
									"""),_display_(/*83.11*/for(lista <- listTipoUsuario) yield /*83.40*/{_display_(Seq[Any](format.raw/*83.41*/("""
										"""),format.raw/*84.11*/("""<option value=""""),_display_(/*84.27*/lista/*84.32*/.getId()),format.raw/*84.40*/("""">"""),_display_(/*84.43*/lista/*84.48*/.getTipo()),format.raw/*84.58*/("""</option>
									""")))}),format.raw/*85.11*/("""
								"""),format.raw/*86.9*/("""</select>
							</td>
						</tr>
						<tr>
						<td style="text-align:left;">SUCURSAL: </td>
							<td style="text-align:left;">
								<select class="custom-select" 
									id="id_sucursal" 
									name="id_sucursal">
									"""),_display_(/*95.11*/for(lista <- listSucursal) yield /*95.37*/{_display_(Seq[Any](format.raw/*95.38*/("""
										"""),format.raw/*96.11*/("""<option value=""""),_display_(/*96.27*/lista/*96.32*/.getId()),format.raw/*96.40*/("""">"""),_display_(/*96.43*/lista/*96.48*/.getNombre()),format.raw/*96.60*/("""</option>
									""")))}),format.raw/*97.11*/("""
								"""),format.raw/*98.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">OBSERVACIONES: </td>
							<td style="text-align:left;">
								<textarea class='form-control form-control-sm' rows='3'
									name="observaciones"
									autocomplete="off"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div id="mensajePorProyecto" align="center"></div>
							</td>
						</tr>
					</table>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="submit"  value="GRABAR USUARIO" class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button" value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/usuarioMantencion/';">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

""")))}),format.raw/*130.2*/("""



"""),format.raw/*134.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*136.31*/("""{"""),format.raw/*136.32*/("""
	  	"""),format.raw/*137.5*/("""document.getElementById("mensajePorProyecto").innerHTML = "<kbd style='background-color: blue'>PERFIL CON ACCESO A TODAS/TODOS "+'"""),_display_(/*137.136*/mapDiccionario/*137.150*/.get("BODEGA")),format.raw/*137.164*/("""'+"S/PROYECTOS</kbd>";
	  	document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*139.2*/("""}"""),format.raw/*139.3*/(""");
	
	const verificaUserName = (userName) => """),format.raw/*141.41*/("""{"""),format.raw/*141.42*/("""
		"""),format.raw/*142.3*/("""var formData = new FormData();
	  	formData.append('userName',userName);
        $.ajax("""),format.raw/*144.16*/("""{"""),format.raw/*144.17*/("""
            """),format.raw/*145.13*/("""url: "/verificaUserNameUsuarioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*152.36*/("""{"""),format.raw/*152.37*/("""
	     		"""),format.raw/*153.9*/("""if(respuesta=="existe")"""),format.raw/*153.32*/("""{"""),format.raw/*153.33*/("""
	     			"""),format.raw/*154.10*/("""alertify.alert('El usuario (userName) ya existe, intente con otro', function () """),format.raw/*154.90*/("""{"""),format.raw/*154.91*/("""
	     				"""),format.raw/*155.11*/("""$("#userName").val("");
		     		"""),format.raw/*156.10*/("""}"""),format.raw/*156.11*/(""");
	     		"""),format.raw/*157.9*/("""}"""),format.raw/*157.10*/("""
	     		"""),format.raw/*158.9*/("""if(respuesta=="error")"""),format.raw/*158.31*/("""{"""),format.raw/*158.32*/("""
	     			"""),format.raw/*159.10*/("""alertify.alert(msgError, function () """),format.raw/*159.47*/("""{"""),format.raw/*159.48*/("""
		     			"""),format.raw/*160.11*/("""location.href = "/";
		     		"""),format.raw/*161.10*/("""}"""),format.raw/*161.11*/(""");
	     		"""),format.raw/*162.9*/("""}"""),format.raw/*162.10*/("""
	     	"""),format.raw/*163.8*/("""}"""),format.raw/*163.9*/("""
        """),format.raw/*164.9*/("""}"""),format.raw/*164.10*/(""");
	"""),format.raw/*165.2*/("""}"""),format.raw/*165.3*/("""
	
	"""),format.raw/*167.2*/("""let mapAux = new Map();
	"""),_display_(/*168.3*/for(lista <- listEsPorProyecto) yield /*168.34*/{_display_(Seq[Any](format.raw/*168.35*/("""
		"""),format.raw/*169.3*/("""mapAux.set("""),_display_(/*169.15*/lista/*169.20*/.get(0)),format.raw/*169.27*/(""","""),_display_(/*169.29*/lista/*169.34*/.get(1)),format.raw/*169.41*/(""");
	""")))}),format.raw/*170.3*/("""
	"""),format.raw/*171.2*/("""const cambiaMensaje = (id_tipoUsuario) => """),format.raw/*171.44*/("""{"""),format.raw/*171.45*/("""
		"""),format.raw/*172.3*/("""let porProyecto = mapAux.get(parseFloat(id_tipoUsuario));
		if(porProyecto==1)"""),format.raw/*173.21*/("""{"""),format.raw/*173.22*/("""
	  		"""),format.raw/*174.6*/("""document.getElementById("mensajePorProyecto").innerHTML = 
	  			"<kbd style='background-color: blue'>PERFIL CON ACCESO SOLO A "+'"""),_display_(/*175.73*/mapDiccionario/*175.87*/.get("BODEGA")),format.raw/*175.101*/("""'+"S/PROYECTOS ASIGNADOS <br> DEBE IR A MODIFICAR USUARIO Y AGREGAR "+'"""),_display_(/*175.173*/mapDiccionario/*175.187*/.get("BODEGA")),format.raw/*175.201*/("""'+"S/PROYECTOS A ACCESAR</kbd>";
	  	"""),format.raw/*176.5*/("""}"""),format.raw/*176.6*/("""else"""),format.raw/*176.10*/("""{"""),format.raw/*176.11*/("""
	  		"""),format.raw/*177.6*/("""document.getElementById("mensajePorProyecto").innerHTML = "<kbd style='background-color: blue'>PERFIL CON ACCESO A TODAS/TODOS "+'"""),_display_(/*177.137*/mapDiccionario/*177.151*/.get("BODEGA")),format.raw/*177.165*/("""'+"S/PROYECTOS</kbd>";
	  	"""),format.raw/*178.5*/("""}"""),format.raw/*178.6*/("""
	"""),format.raw/*179.2*/("""}"""),format.raw/*179.3*/("""

"""),format.raw/*181.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listTipoUsuario:List[tables.UsuarioTipo],listEsPorProyecto:List[List[Long]],listSucursal:List[tables.Sucursal]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listTipoUsuario,listEsPorProyecto,listSucursal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.UsuarioTipo],List[List[Long]],List[tables.Sucursal]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listTipoUsuario,listEsPorProyecto,listSucursal) => apply(mapDiccionario,mapPermiso,userMnu,listTipoUsuario,listEsPorProyecto,listSucursal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/usuarioAgrega.scala.html
                  HASH: d558a06dea2d9dabe133485aeaa924041c36b181
                  MATRIX: 1075->1|1381->214|1414->222|1430->230|1469->232|1497->235|1565->283|1593->285|1669->336|1745->392|1775->395|4195->2788|4240->2817|4279->2818|4318->2829|4361->2845|4375->2850|4404->2858|4434->2861|4448->2866|4479->2876|4530->2896|4566->2905|4832->3144|4874->3170|4913->3171|4952->3182|4995->3198|5009->3203|5038->3211|5068->3214|5082->3219|5115->3231|5166->3251|5202->3260|6205->4232|6237->4236|6329->4299|6359->4300|6392->4305|6552->4436|6577->4450|6614->4464|6735->4557|6764->4558|6838->4603|6868->4604|6899->4607|7016->4695|7046->4696|7088->4709|7357->4949|7387->4950|7424->4959|7476->4982|7506->4983|7545->4993|7654->5073|7684->5074|7724->5085|7786->5118|7816->5119|7855->5130|7885->5131|7922->5140|7973->5162|8003->5163|8042->5173|8108->5210|8138->5211|8178->5222|8237->5252|8267->5253|8306->5264|8336->5265|8372->5273|8401->5274|8438->5283|8468->5284|8500->5288|8529->5289|8561->5293|8614->5319|8662->5350|8702->5351|8733->5354|8773->5366|8788->5371|8817->5378|8847->5380|8862->5385|8891->5392|8927->5397|8957->5399|9028->5441|9058->5442|9089->5445|9196->5523|9226->5524|9260->5530|9419->5661|9443->5675|9480->5689|9581->5761|9606->5775|9643->5789|9708->5826|9737->5827|9770->5831|9800->5832|9834->5838|9994->5969|10019->5983|10056->5997|10111->6024|10140->6025|10170->6027|10199->6028|10229->6030
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|114->83|114->83|114->83|115->84|115->84|115->84|115->84|115->84|115->84|115->84|116->85|117->86|126->95|126->95|126->95|127->96|127->96|127->96|127->96|127->96|127->96|127->96|128->97|129->98|161->130|165->134|167->136|167->136|168->137|168->137|168->137|168->137|170->139|170->139|172->141|172->141|173->142|175->144|175->144|176->145|183->152|183->152|184->153|184->153|184->153|185->154|185->154|185->154|186->155|187->156|187->156|188->157|188->157|189->158|189->158|189->158|190->159|190->159|190->159|191->160|192->161|192->161|193->162|193->162|194->163|194->163|195->164|195->164|196->165|196->165|198->167|199->168|199->168|199->168|200->169|200->169|200->169|200->169|200->169|200->169|200->169|201->170|202->171|202->171|202->171|203->172|204->173|204->173|205->174|206->175|206->175|206->175|206->175|206->175|206->175|207->176|207->176|207->176|207->176|208->177|208->177|208->177|208->177|209->178|209->178|210->179|210->179|212->181
                  -- GENERATED --
              */
          