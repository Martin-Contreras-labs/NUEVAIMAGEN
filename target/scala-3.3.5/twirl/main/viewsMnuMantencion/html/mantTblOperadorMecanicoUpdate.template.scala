
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

object mantTblOperadorMecanicoUpdate extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.MantOperadorMecanico,List[tables.MantActorPersonal],List[tables.MantTipoPersonal],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
operadorMecanico: tables.MantOperadorMecanico, listActorPersonal: List[tables.MantActorPersonal], listTipoPersonal: List[tables.MantTipoPersonal]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""

"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR OPERADOR MECANICO", "")),format.raw/*9.66*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td style="text-align:left;">USUARIO (userName): </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="userName"
								autocomplete="off"
								onkeydown="return sinReservados(window.event)"
								value=""""),_display_(/*20.17*/operadorMecanico/*20.33*/.getUserName()),format.raw/*20.47*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificaOperadorMecanico(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">CLAVE (userKey): </td>
						<td style="text-align:left;">
							<input type="password" class="form-control left"
								id="userKey"
								autocomplete="off"
								value=""""),_display_(/*31.17*/operadorMecanico/*31.33*/.getUserKey()),format.raw/*31.46*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificaOperadorMecanico(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">RUT: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="rut"
								autocomplete="off"
								onkeydown="return sinReservados(window.event)"
								value=""""),_display_(/*43.17*/operadorMecanico/*43.33*/.getRut()),format.raw/*43.42*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificaOperadorMecanico(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE (fullName): </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombre"
								autocomplete="off"
								onkeydown="return sinReservados(window.event)"
								value=""""),_display_(/*55.17*/operadorMecanico/*55.33*/.getNombre()),format.raw/*55.45*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificaOperadorMecanico(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">CARGO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="cargo"
								autocomplete="off"
								onkeydown="return sinReservados(window.event)"
								value=""""),_display_(/*67.17*/operadorMecanico/*67.33*/.getCargo()),format.raw/*67.44*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificaOperadorMecanico(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">TELEFONO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="fono"
								autocomplete="off"
								value=""""),_display_(/*78.17*/operadorMecanico/*78.33*/.getFono()),format.raw/*78.43*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificaOperadorMecanico(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">EMAIL CORPORATIVO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="emailCor"
								autocomplete="off"
								value=""""),_display_(/*89.17*/operadorMecanico/*89.33*/.getEmailCor()),format.raw/*89.47*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificaOperadorMecanico(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">EMAIL PERSONAL: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="emailPer"
								autocomplete="off"
								value=""""),_display_(/*100.17*/operadorMecanico/*100.33*/.getEmailPer()),format.raw/*100.47*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificaOperadorMecanico(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">ACTOR: </td>
						<td style="text-align:left;">
							<select class="custom-select" 
								id="id_mantActorPersonal" 
								onchange="modificaOperadorMecanico(id);">
								<option value=""""),_display_(/*111.25*/operadorMecanico/*111.41*/.getId_mantActorPersonal()),format.raw/*111.67*/("""">"""),_display_(/*111.70*/operadorMecanico/*111.86*/.getNameActorPersonal()),format.raw/*111.109*/("""</option>
								"""),_display_(/*112.10*/for(lista <- listActorPersonal) yield /*112.41*/{_display_(Seq[Any](format.raw/*112.42*/("""
									"""),format.raw/*113.10*/("""<option value=""""),_display_(/*113.26*/lista/*113.31*/.getId()),format.raw/*113.39*/("""">"""),_display_(/*113.42*/lista/*113.47*/.getNombre()),format.raw/*113.59*/("""</option>
								""")))}),format.raw/*114.10*/("""
							"""),format.raw/*115.8*/("""</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">TIPO: </td>
						<td style="text-align:left;">
							<select class="custom-select" 
								id="id_mantTipoPersonal" 
								onchange="modificaOperadorMecanico(id);">
								<option value=""""),_display_(/*124.25*/operadorMecanico/*124.41*/.getId_mantTipoPersonal()),format.raw/*124.66*/("""">"""),_display_(/*124.69*/operadorMecanico/*124.85*/.getNameTipoPersonal()),format.raw/*124.107*/("""</option>
								"""),_display_(/*125.10*/for(lista <- listTipoPersonal) yield /*125.40*/{_display_(Seq[Any](format.raw/*125.41*/("""
									"""),format.raw/*126.10*/("""<option value=""""),_display_(/*126.26*/lista/*126.31*/.getId()),format.raw/*126.39*/("""">"""),_display_(/*126.42*/lista/*126.47*/.getNombre()),format.raw/*126.59*/("""</option>
								""")))}),format.raw/*127.10*/("""
							"""),format.raw/*128.8*/("""</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">OBSERVACIONES: </td>
						<td style="text-align:left;">
							<textarea class='form-control form-control-sm' rows='3'
								id="observaciones"
								onchange="value = value.trim(); modificaOperadorMecanico(id)"
								autocomplete="off">"""),_display_(/*137.29*/operadorMecanico/*137.45*/.getObservaciones()),format.raw/*137.64*/("""</textarea>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes3/mantTblOperadorMecanicoMant/';">
				</div>
			</div>
		</div>
	</div>
	
""")))}),format.raw/*152.2*/("""



"""),format.raw/*156.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*157.31*/("""{"""),format.raw/*157.32*/("""
		 """),format.raw/*158.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*159.2*/("""}"""),format.raw/*159.3*/(""");
	let id_operadorMecanico = """"),_display_(/*160.30*/operadorMecanico/*160.46*/.getId()),format.raw/*160.54*/("""";
	const modificaOperadorMecanico = (campo) => """),format.raw/*161.46*/("""{"""),format.raw/*161.47*/("""
		"""),format.raw/*162.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_operadorMecanico',id_operadorMecanico);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*167.16*/("""{"""),format.raw/*167.17*/("""
            """),format.raw/*168.13*/("""url: "/routes3/modificaOperadorMecanicoPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*175.36*/("""{"""),format.raw/*175.37*/("""
	     		"""),format.raw/*176.9*/("""if(respuesta=="existe")"""),format.raw/*176.32*/("""{"""),format.raw/*176.33*/("""
	     			"""),format.raw/*177.10*/("""alertify.alert('El usuario (userName) ya existe, intente con otro', function () """),format.raw/*177.90*/("""{"""),format.raw/*177.91*/("""
	     				"""),format.raw/*178.11*/("""$("#userName").val(""""),_display_(/*178.32*/operadorMecanico/*178.48*/.getUserName()),format.raw/*178.62*/("""");
		     		"""),format.raw/*179.10*/("""}"""),format.raw/*179.11*/(""");
	     		"""),format.raw/*180.9*/("""}"""),format.raw/*180.10*/("""else if(respuesta=="error")"""),format.raw/*180.37*/("""{"""),format.raw/*180.38*/("""
	     			"""),format.raw/*181.10*/("""alertify.alert(msgError, function () """),format.raw/*181.47*/("""{"""),format.raw/*181.48*/("""
		     			"""),format.raw/*182.11*/("""location.href = "/";
		     		"""),format.raw/*183.10*/("""}"""),format.raw/*183.11*/(""");
	     		"""),format.raw/*184.9*/("""}"""),format.raw/*184.10*/("""
	     	"""),format.raw/*185.8*/("""}"""),format.raw/*185.9*/("""
        """),format.raw/*186.9*/("""}"""),format.raw/*186.10*/(""");
	"""),format.raw/*187.2*/("""}"""),format.raw/*187.3*/("""
	

"""),format.raw/*190.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,operadorMecanico:tables.MantOperadorMecanico,listActorPersonal:List[tables.MantActorPersonal],listTipoPersonal:List[tables.MantTipoPersonal]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,operadorMecanico,listActorPersonal,listTipoPersonal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.MantOperadorMecanico,List[tables.MantActorPersonal],List[tables.MantTipoPersonal]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,operadorMecanico,listActorPersonal,listTipoPersonal) => apply(mapDiccionario,mapPermiso,userMnu,operadorMecanico,listActorPersonal,listTipoPersonal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuMantencion/mantTblOperadorMecanicoUpdate.scala.html
                  HASH: 3226e22db342c17b9db144e62935967208eba065
                  MATRIX: 1120->1|1457->245|1485->248|1501->256|1540->258|1568->261|1636->309|1664->311|1740->362|1822->424|1852->427|2335->883|2360->899|2395->913|2766->1257|2791->1273|2825->1286|3231->1665|3256->1681|3286->1690|3709->2086|3734->2102|3767->2114|4177->2497|4202->2513|4234->2524|4591->2854|4616->2870|4647->2880|5017->3223|5042->3239|5077->3253|5445->3593|5471->3609|5507->3623|5896->3984|5922->4000|5970->4026|6001->4029|6027->4045|6073->4068|6120->4087|6168->4118|6208->4119|6247->4129|6291->4145|6306->4150|6336->4158|6367->4161|6382->4166|6416->4178|6467->4197|6503->4205|6803->4477|6829->4493|6876->4518|6907->4521|6933->4537|6978->4559|7025->4578|7072->4608|7112->4609|7151->4619|7195->4635|7210->4640|7240->4648|7271->4651|7286->4656|7320->4668|7371->4687|7407->4695|7758->5018|7784->5034|7825->5053|8251->5448|8283->5452|8374->5514|8404->5515|8436->5519|8530->5585|8559->5586|8619->5618|8645->5634|8675->5642|8752->5690|8782->5691|8813->5694|9058->5910|9088->5911|9130->5924|9416->6181|9446->6182|9483->6191|9535->6214|9565->6215|9604->6225|9713->6305|9743->6306|9783->6317|9832->6338|9858->6354|9894->6368|9936->6381|9966->6382|10005->6393|10035->6394|10091->6421|10121->6422|10160->6432|10226->6469|10256->6470|10296->6481|10355->6511|10385->6512|10424->6523|10454->6524|10490->6532|10519->6533|10556->6542|10586->6543|10618->6547|10647->6548|10679->6552
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|62->31|62->31|62->31|74->43|74->43|74->43|86->55|86->55|86->55|98->67|98->67|98->67|109->78|109->78|109->78|120->89|120->89|120->89|131->100|131->100|131->100|142->111|142->111|142->111|142->111|142->111|142->111|143->112|143->112|143->112|144->113|144->113|144->113|144->113|144->113|144->113|144->113|145->114|146->115|155->124|155->124|155->124|155->124|155->124|155->124|156->125|156->125|156->125|157->126|157->126|157->126|157->126|157->126|157->126|157->126|158->127|159->128|168->137|168->137|168->137|183->152|187->156|188->157|188->157|189->158|190->159|190->159|191->160|191->160|191->160|192->161|192->161|193->162|198->167|198->167|199->168|206->175|206->175|207->176|207->176|207->176|208->177|208->177|208->177|209->178|209->178|209->178|209->178|210->179|210->179|211->180|211->180|211->180|211->180|212->181|212->181|212->181|213->182|214->183|214->183|215->184|215->184|216->185|216->185|217->186|217->186|218->187|218->187|221->190
                  -- GENERATED --
              */
          