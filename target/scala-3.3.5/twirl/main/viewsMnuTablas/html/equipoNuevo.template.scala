
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

object equipoNuevo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],List[tables.Atributo],tables.Grupo,List[tables.Propiedad],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, listGrupos: List[tables.Grupo], listFabrica: List[tables.Fabrica], listUnidades: List[tables.Unidad], listAtributos: List[tables.Atributo], grupo: tables.Grupo, listPropiedad: List[tables.Propiedad]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

"""),_display_(/*6.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.50*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "AGREGAR/CREAR NUEVO EQUIPO", "")),format.raw/*8.65*/("""
		"""),format.raw/*9.3*/("""<form action="/equipoGraba/" method="POST">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<TR>
							<td  style="text-align:left;">GRUPO</td>
							<td style="text-align:left">
								<select class="custom-select"
									id="id_grupo"
									name="id_grupo"
									onchange="cambiarGrupo(value)"
									required>
									<option value=""""),_display_(/*21.26*/grupo/*21.31*/.getId),format.raw/*21.37*/("""">"""),_display_(/*21.40*/grupo/*21.45*/.getNombre),format.raw/*21.55*/("""</option>
									"""),_display_(/*22.11*/for(lista <- listGrupos) yield /*22.35*/{_display_(Seq[Any](format.raw/*22.36*/("""
										"""),format.raw/*23.11*/("""<option value=""""),_display_(/*23.27*/lista/*23.32*/.getId()),format.raw/*23.40*/("""">"""),_display_(/*23.43*/lista/*23.48*/.getNombre()),format.raw/*23.60*/("""</option>
									""")))}),format.raw/*24.11*/("""
								"""),format.raw/*25.9*/("""</select>
							</td>
						</TR>
						<TR>
							<td  style="text-align:left;">PROPIEDAD</td>
							<td style="text-align:left">
								<select class="custom-select"
								id="id_propiedad"
								name="id_propiedad">
									<option value=""""),_display_(/*34.26*/listPropiedad/*34.39*/.get(0).getId()),format.raw/*34.54*/("""">"""),_display_(/*34.57*/listPropiedad/*34.70*/.get(0).getNombre()),format.raw/*34.89*/("""</option>
									"""),_display_(/*35.11*/for(lista <- listPropiedad) yield /*35.38*/{_display_(Seq[Any](format.raw/*35.39*/("""
										"""),format.raw/*36.11*/("""<option value=""""),_display_(/*36.27*/lista/*36.32*/.getId()),format.raw/*36.40*/("""">"""),_display_(/*36.43*/lista/*36.48*/.getNombre()),format.raw/*36.60*/("""</option>
									""")))}),format.raw/*37.11*/("""
								"""),format.raw/*38.9*/("""</select>
							</td>
						</TR>
						<tr>
							<td style="text-align:left; vertical-align:top">CODIGO EQUIPO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									id="codigo"
									name="codigo"
									required
									autocomplete="off"
									onkeydown="return sinReservCodigos(window.event)"
									onchange="value = value.replace(/\s/g,'').toUpperCase(); verificaCodigo(value)"
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">NOMBRE EQUIPO: </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="nombre"
									onkeydown="return sinReservados(window.event)"
									required
									autocomplete="off"
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">FABRICANTE: </td>
							<td style="text-align:left; vertical-align:top">
								<select class="custom-select" 
									name="id_fabrica" 
									required>
									"""),_display_(/*71.11*/for(lista <- listFabrica) yield /*71.36*/{_display_(Seq[Any](format.raw/*71.37*/("""
										"""),format.raw/*72.11*/("""<option value=""""),_display_(/*72.27*/lista/*72.32*/.id),format.raw/*72.35*/("""">"""),_display_(/*72.38*/lista/*72.43*/.nickName),format.raw/*72.52*/("""</option>
									""")))}),format.raw/*73.11*/("""
								"""),format.raw/*74.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">UNIDAD: </td>
							<td style="text-align:left; vertical-align:top">
								<select class="custom-select" 
									name="id_unidad"
									required>
									"""),_display_(/*83.11*/for(lista <- listUnidades) yield /*83.37*/{_display_(Seq[Any](format.raw/*83.38*/("""
										"""),format.raw/*84.11*/("""<option value=""""),_display_(/*84.27*/lista/*84.32*/.id),format.raw/*84.35*/("""">"""),_display_(/*84.38*/lista/*84.43*/.nombre),format.raw/*84.50*/("""</option>
									""")))}),format.raw/*85.11*/("""
								"""),format.raw/*86.9*/("""</select>
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">PESO (KG): </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="kg"
									autocomplete="off"
									value="0.00"
									onfocus="value=value.replace(/,/g,'')" 
									onkeydown="return ingresoDouble(window.event)">
							</td>
						</tr>
						<tr>
							<td style="text-align:left; vertical-align:top">AREA (M2): </td>
							<td style="text-align:left; vertical-align:top">
								<input type="text" class="form-control left"
									name="m2"
									autocomplete="off"
									value="0.00"
									onfocus="value=value.replace(/,/g,'')" 
									onkeydown="return ingresoDouble(window.event)">
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:left">
								<font color="#008000"> ATRIBUTOS: </font>
							</td>
						</tr>
						<div id="listAtributos">
							"""),_display_(/*117.9*/for(lista <- listAtributos) yield /*117.36*/{_display_(Seq[Any](format.raw/*117.37*/("""
								"""),format.raw/*118.9*/("""<tr>
									<td style="text-align:left">"""),_display_(/*119.39*/lista/*119.44*/.getAtributo()),format.raw/*119.58*/(""" """),format.raw/*119.59*/("""("""),_display_(/*119.61*/lista/*119.66*/.getUnidad()),format.raw/*119.78*/(""")</td>
									<td style="text-align:left">
										<input type="hidden" name="idAtributos[]" value=""""),_display_(/*121.61*/lista/*121.66*/.getId()),format.raw/*121.74*/("""">
										"""),_display_(if(lista.getEsNumerico()==1)/*122.40*/{_display_(Seq[Any](format.raw/*122.41*/("""
											"""),format.raw/*123.12*/("""<input type="text" class="form-control left"
												name="valorAtributos[]"
												onfocus="value=value.replace(/,/g,'')" 
												onkeydown="return ingresoDouble(window.event)"
												value="">
										""")))} else {null} ),format.raw/*128.12*/("""
										"""),_display_(if(lista.getEsNumerico()==0)/*129.40*/{_display_(Seq[Any](format.raw/*129.41*/("""
											"""),format.raw/*130.12*/("""<input type="text" class="form-control left"
												name="valorAtributos[]"
												autocomplete="off"
												maxlength="50">
										""")))} else {null} ),format.raw/*134.12*/("""
									"""),format.raw/*135.10*/("""</td>
								</tr>
							""")))}),format.raw/*137.9*/("""
						"""),format.raw/*138.7*/("""<div>
					</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit"  value="GRABAR" class="btn btn-success btn-sm rounded-pill btn-block">
					</div>
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="button"  value="CANCELAR" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/equipoMantencion/';">
					</div>
				</div>
			</div>
		</form>
	</div>
""")))}),format.raw/*154.2*/("""




"""),format.raw/*159.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*160.31*/("""{"""),format.raw/*160.32*/("""
		"""),format.raw/*161.3*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*162.2*/("""}"""),format.raw/*162.3*/(""");
	const cambiarGrupo = (id_grupo) => """),format.raw/*163.37*/("""{"""),format.raw/*163.38*/("""
		"""),format.raw/*164.3*/("""location.href = "/equipoNuevo/"+id_grupo;
	"""),format.raw/*165.2*/("""}"""),format.raw/*165.3*/("""
	"""),format.raw/*166.2*/("""const verificaCodigo = (codigo) => """),format.raw/*166.37*/("""{"""),format.raw/*166.38*/("""
		"""),format.raw/*167.3*/("""var formData = new FormData();
	  	formData.append('codigo',codigo);
        $.ajax("""),format.raw/*169.16*/("""{"""),format.raw/*169.17*/("""
            """),format.raw/*170.13*/("""url: "/verificaCodigoEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*177.36*/("""{"""),format.raw/*177.37*/("""
	     		"""),format.raw/*178.9*/("""if(respuesta=="existe")"""),format.raw/*178.32*/("""{"""),format.raw/*178.33*/("""
	     			"""),format.raw/*179.10*/("""alertify.alert("El código de equipo ya existe, intente con otro", function () """),format.raw/*179.88*/("""{"""),format.raw/*179.89*/("""
	     				"""),format.raw/*180.11*/("""$("#codigo").val("");
		     		"""),format.raw/*181.10*/("""}"""),format.raw/*181.11*/(""");
	     		"""),format.raw/*182.9*/("""}"""),format.raw/*182.10*/("""
	     		"""),format.raw/*183.9*/("""if(respuesta=="error")"""),format.raw/*183.31*/("""{"""),format.raw/*183.32*/("""
	     			"""),format.raw/*184.10*/("""alertify.alert(msgError, function () """),format.raw/*184.47*/("""{"""),format.raw/*184.48*/("""
		     			"""),format.raw/*185.11*/("""location.href = "/";
		     		"""),format.raw/*186.10*/("""}"""),format.raw/*186.11*/(""");
	     		"""),format.raw/*187.9*/("""}"""),format.raw/*187.10*/("""
	     	"""),format.raw/*188.8*/("""}"""),format.raw/*188.9*/("""
        """),format.raw/*189.9*/("""}"""),format.raw/*189.10*/(""");
	"""),format.raw/*190.2*/("""}"""),format.raw/*190.3*/("""
	
	

"""),format.raw/*194.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listGrupos:List[tables.Grupo],listFabrica:List[tables.Fabrica],listUnidades:List[tables.Unidad],listAtributos:List[tables.Atributo],grupo:tables.Grupo,listPropiedad:List[tables.Propiedad]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listGrupos,listFabrica,listUnidades,listAtributos,grupo,listPropiedad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Grupo],List[tables.Fabrica],List[tables.Unidad],List[tables.Atributo],tables.Grupo,List[tables.Propiedad]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listGrupos,listFabrica,listUnidades,listAtributos,grupo,listPropiedad) => apply(mapDiccionario,mapPermiso,userMnu,listGrupos,listFabrica,listUnidades,listAtributos,grupo,listPropiedad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/equipoNuevo.scala.html
                  HASH: 2170e6491a569fa1f10b413ab73b39e77493fd9b
                  MATRIX: 1127->1|1517->298|1550->306|1566->314|1605->316|1633->319|1701->367|1729->369|1805->420|1886->481|1915->484|2433->975|2447->980|2474->986|2504->989|2518->994|2549->1004|2596->1024|2636->1048|2675->1049|2714->1060|2757->1076|2771->1081|2800->1089|2830->1092|2844->1097|2877->1109|2928->1129|2964->1138|3243->1390|3265->1403|3301->1418|3331->1421|3353->1434|3393->1453|3440->1473|3483->1500|3522->1501|3561->1512|3604->1528|3618->1533|3647->1541|3677->1544|3691->1549|3724->1561|3775->1581|3811->1590|4965->2717|5006->2742|5045->2743|5084->2754|5127->2770|5141->2775|5165->2778|5195->2781|5209->2786|5239->2795|5290->2815|5326->2824|5618->3089|5660->3115|5699->3116|5738->3127|5781->3143|5795->3148|5819->3151|5849->3154|5863->3159|5891->3166|5942->3186|5978->3195|6997->4187|7041->4214|7081->4215|7118->4224|7189->4267|7204->4272|7240->4286|7270->4287|7300->4289|7315->4294|7349->4306|7482->4411|7497->4416|7527->4424|7597->4466|7637->4467|7678->4479|7948->4704|8016->4744|8056->4745|8097->4757|8293->4908|8332->4918|8391->4946|8426->4953|8988->5484|9021->5489|9112->5551|9142->5552|9173->5555|9267->5621|9296->5622|9364->5661|9394->5662|9425->5665|9496->5708|9525->5709|9555->5711|9619->5746|9649->5747|9680->5750|9793->5834|9823->5835|9865->5848|10131->6085|10161->6086|10198->6095|10250->6118|10280->6119|10319->6129|10426->6207|10456->6208|10496->6219|10556->6250|10586->6251|10625->6262|10655->6263|10692->6272|10743->6294|10773->6295|10812->6305|10878->6342|10908->6343|10948->6354|11007->6384|11037->6385|11076->6396|11106->6397|11142->6405|11171->6406|11208->6415|11238->6416|11270->6420|11299->6421|11333->6427
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|52->21|52->21|52->21|52->21|52->21|52->21|53->22|53->22|53->22|54->23|54->23|54->23|54->23|54->23|54->23|54->23|55->24|56->25|65->34|65->34|65->34|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|67->36|67->36|67->36|67->36|68->37|69->38|102->71|102->71|102->71|103->72|103->72|103->72|103->72|103->72|103->72|103->72|104->73|105->74|114->83|114->83|114->83|115->84|115->84|115->84|115->84|115->84|115->84|115->84|116->85|117->86|148->117|148->117|148->117|149->118|150->119|150->119|150->119|150->119|150->119|150->119|150->119|152->121|152->121|152->121|153->122|153->122|154->123|159->128|160->129|160->129|161->130|165->134|166->135|168->137|169->138|185->154|190->159|191->160|191->160|192->161|193->162|193->162|194->163|194->163|195->164|196->165|196->165|197->166|197->166|197->166|198->167|200->169|200->169|201->170|208->177|208->177|209->178|209->178|209->178|210->179|210->179|210->179|211->180|212->181|212->181|213->182|213->182|214->183|214->183|214->183|215->184|215->184|215->184|216->185|217->186|217->186|218->187|218->187|219->188|219->188|220->189|220->189|221->190|221->190|225->194
                  -- GENERATED --
              */
          