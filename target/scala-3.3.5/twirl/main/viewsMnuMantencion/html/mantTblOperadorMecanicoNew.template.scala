
package viewsMnuMantencion.html

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

object mantTblOperadorMecanicoNew extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.MantActorPersonal],List[tables.MantTipoPersonal],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listActorPersonal: List[tables.MantActorPersonal], listTipoPersonal: List[tables.MantTipoPersonal]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "AGREGAR NUEVO OPERADOR MECANICO", "")),format.raw/*9.70*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<form id="form" method="post" action="/routes3/mantTblOperadorMecanicoNewSave/">
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
							<td style="text-align:left;">RUT: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="rut"
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
							<td style="text-align:left;">EMAIL CORPORATIVO: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="emailCor"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">EMAIL PERSONAL: </td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="emailPer"
									autocomplete="off"
									maxlength="100"
									onchange="value = value.trim();">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">ACTOR: </td>
							<td style="text-align:left;">
								<select class="custom-select" 
									name="id_mantActorPersonal" 
									onchange="cambiaMensaje(value);">
									"""),_display_(/*103.11*/for(lista <- listActorPersonal) yield /*103.42*/{_display_(Seq[Any](format.raw/*103.43*/("""
										"""),format.raw/*104.11*/("""<option value=""""),_display_(/*104.27*/lista/*104.32*/.getId()),format.raw/*104.40*/("""">"""),_display_(/*104.43*/lista/*104.48*/.getNombre()),format.raw/*104.60*/("""</option>
									""")))}),format.raw/*105.11*/("""
								"""),format.raw/*106.9*/("""</select>
							</td>
						</tr>
						<tr>
						<td style="text-align:left;">TIPO: </td>
							<td style="text-align:left;">
								<select class="custom-select" 
									name="id_mantTipoPersonal">
									"""),_display_(/*114.11*/for(lista <- listTipoPersonal) yield /*114.41*/{_display_(Seq[Any](format.raw/*114.42*/("""
										"""),format.raw/*115.11*/("""<option value=""""),_display_(/*115.27*/lista/*115.32*/.getId()),format.raw/*115.40*/("""">"""),_display_(/*115.43*/lista/*115.48*/.getNombre()),format.raw/*115.60*/("""</option>
									""")))}),format.raw/*116.11*/("""
								"""),format.raw/*117.9*/("""</select>
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
					</table>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="submit"  value="GRABAR USUARIO" class="btn btn-success btn-sm rounded-pill btn-block">
							</div>
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<input type="button" value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/routes3/mantTblOperadorMecanicoMant/';">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

""")))}),format.raw/*144.2*/("""



"""),format.raw/*148.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*149.31*/("""{"""),format.raw/*149.32*/("""
	  	"""),format.raw/*150.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*151.2*/("""}"""),format.raw/*151.3*/(""");
	const verificaUserName = (userName) => """),format.raw/*152.41*/("""{"""),format.raw/*152.42*/("""
		"""),format.raw/*153.3*/("""var formData = new FormData();
	  	formData.append('userName',userName);
        $.ajax("""),format.raw/*155.16*/("""{"""),format.raw/*155.17*/("""
            """),format.raw/*156.13*/("""url: "/routes3/mantTblOperadorMecanicoVerifica/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*163.36*/("""{"""),format.raw/*163.37*/("""
	     		"""),format.raw/*164.9*/("""if(respuesta=="existe")"""),format.raw/*164.32*/("""{"""),format.raw/*164.33*/("""
	     			"""),format.raw/*165.10*/("""alertify.alert('El usuario (userName) ya existe, intente con otro', function () """),format.raw/*165.90*/("""{"""),format.raw/*165.91*/("""
	     				"""),format.raw/*166.11*/("""$("#userName").val("");
		     		"""),format.raw/*167.10*/("""}"""),format.raw/*167.11*/(""");
	     		"""),format.raw/*168.9*/("""}"""),format.raw/*168.10*/("""
	     		"""),format.raw/*169.9*/("""if(respuesta=="error")"""),format.raw/*169.31*/("""{"""),format.raw/*169.32*/("""
	     			"""),format.raw/*170.10*/("""alertify.alert(msgError, function () """),format.raw/*170.47*/("""{"""),format.raw/*170.48*/("""
		     			"""),format.raw/*171.11*/("""location.href = "/";
		     		"""),format.raw/*172.10*/("""}"""),format.raw/*172.11*/(""");
	     		"""),format.raw/*173.9*/("""}"""),format.raw/*173.10*/("""
	     	"""),format.raw/*174.8*/("""}"""),format.raw/*174.9*/("""
        """),format.raw/*175.9*/("""}"""),format.raw/*175.10*/(""");
	"""),format.raw/*176.2*/("""}"""),format.raw/*176.3*/("""
"""),format.raw/*177.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listActorPersonal:List[tables.MantActorPersonal],listTipoPersonal:List[tables.MantTipoPersonal]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listActorPersonal,listTipoPersonal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.MantActorPersonal],List[tables.MantTipoPersonal]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listActorPersonal,listTipoPersonal) => apply(mapDiccionario,mapPermiso,userMnu,listActorPersonal,listTipoPersonal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantTblOperadorMecanicoNew.scala.html
                  HASH: 6b24fd001e3b4865d74f411d69d83603f32ea665
                  MATRIX: 1089->1|1379->198|1412->206|1428->214|1467->216|1495->219|1563->267|1591->269|1667->320|1753->386|1783->389|4837->3415|4885->3446|4925->3447|4965->3458|5009->3474|5024->3479|5054->3487|5085->3490|5100->3495|5134->3507|5186->3527|5223->3536|5467->3752|5514->3782|5554->3783|5594->3794|5638->3810|5653->3815|5683->3823|5714->3826|5729->3831|5763->3843|5815->3863|5852->3872|6754->4743|6786->4747|6877->4809|6907->4810|6940->4815|7034->4881|7063->4882|7135->4925|7165->4926|7196->4929|7313->5017|7343->5018|7385->5031|7666->5283|7696->5284|7733->5293|7785->5316|7815->5317|7854->5327|7963->5407|7993->5408|8033->5419|8095->5452|8125->5453|8164->5464|8194->5465|8231->5474|8282->5496|8312->5497|8351->5507|8417->5544|8447->5545|8487->5556|8546->5586|8576->5587|8615->5598|8645->5599|8681->5607|8710->5608|8747->5617|8777->5618|8809->5622|8838->5623|8867->5624
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|134->103|134->103|134->103|135->104|135->104|135->104|135->104|135->104|135->104|135->104|136->105|137->106|145->114|145->114|145->114|146->115|146->115|146->115|146->115|146->115|146->115|146->115|147->116|148->117|175->144|179->148|180->149|180->149|181->150|182->151|182->151|183->152|183->152|184->153|186->155|186->155|187->156|194->163|194->163|195->164|195->164|195->164|196->165|196->165|196->165|197->166|198->167|198->167|199->168|199->168|200->169|200->169|200->169|201->170|201->170|201->170|202->171|203->172|203->172|204->173|204->173|205->174|205->174|206->175|206->175|207->176|207->176|208->177
                  -- GENERATED --
              */
          