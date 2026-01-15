
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

object usuarioModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,tables.Usuario,List[tables.UsuarioTipo],List[List[String]],Long,List[List[Long]],List[tables.Sucursal],tables.Sucursal,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
usuario: tables.Usuario, listTipoUsuario: List[tables.UsuarioTipo], listPermisoBodPorUsuario: List[List[String]], 
esPorPorProyecto: Long, listEsPorProyecto: List[List[Long]], listSucursal: List[tables.Sucursal], sucursal: tables.Sucursal):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

"""),_display_(/*8.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.50*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "MODIFICAR USUARIO", "")),format.raw/*10.56*/("""
		"""),format.raw/*11.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td style="text-align:left;">USUARIO (userName): </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="userName"
								autocomplete="off"
								onkeydown="return sinReservados(window.event)"
								value=""""),_display_(/*21.17*/usuario/*21.24*/.getUserName()),format.raw/*21.38*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarUsuario(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">CLAVE (userKey): </td>
						<td style="text-align:left;">
							<input type="password" class="form-control left"
								id="userKey"
								autocomplete="off"
								value=""""),_display_(/*32.17*/usuario/*32.24*/.getUserKey()),format.raw/*32.37*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarUsuario(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">NOMBRE (fullName): </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="nombre"
								autocomplete="off"
								onkeydown="return sinReservados(window.event)"
								value=""""),_display_(/*44.17*/usuario/*44.24*/.getNombre()),format.raw/*44.36*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarUsuario(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">CARGO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="cargo"
								autocomplete="off"
								onkeydown="return sinReservados(window.event)"
								value=""""),_display_(/*56.17*/usuario/*56.24*/.getCargo()),format.raw/*56.35*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarUsuario(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">E-MAIL: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="email"
								autocomplete="off"
								value=""""),_display_(/*67.17*/usuario/*67.24*/.getEmail()),format.raw/*67.35*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarUsuario(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">TELEFONO: </td>
						<td style="text-align:left;">
							<input type="text" class="form-control left"
								id="fono"
								autocomplete="off"
								value=""""),_display_(/*78.17*/usuario/*78.24*/.getFono()),format.raw/*78.34*/(""""
								maxlength="100"
								onchange="value = value.trim(); modificarUsuario(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">TIPO USUARIO: </td>
						<td style="text-align:left;">
							<select class="custom-select" 
								id="id_tipoUsuario" 
								onchange="modificarUsuario(id); cambiaMensaje(value);">
								"""),_display_(/*89.10*/for(lista <- listTipoUsuario) yield /*89.39*/{_display_(Seq[Any](format.raw/*89.40*/("""
									"""),format.raw/*90.10*/("""<option value=""""),_display_(/*90.26*/lista/*90.31*/.getId()),format.raw/*90.39*/("""">"""),_display_(/*90.42*/lista/*90.47*/.getTipo()),format.raw/*90.57*/("""</option>
								""")))}),format.raw/*91.10*/("""
							"""),format.raw/*92.8*/("""</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">SUCURSAL: </td>
						<td style="text-align:left;">
							<select class="custom-select" 
								id="id_sucursal" 
								onchange="modificarUsuario(id);">
								<option value=""""),_display_(/*101.25*/sucursal/*101.33*/.getId()),format.raw/*101.41*/("""">"""),_display_(/*101.44*/sucursal/*101.52*/.getNombre()),format.raw/*101.64*/("""</option>
								"""),_display_(/*102.10*/for(lista <- listSucursal) yield /*102.36*/{_display_(Seq[Any](format.raw/*102.37*/("""
									"""),format.raw/*103.10*/("""<option value=""""),_display_(/*103.26*/lista/*103.31*/.getId()),format.raw/*103.39*/("""">"""),_display_(/*103.42*/lista/*103.47*/.getNombre()),format.raw/*103.59*/("""</option>
								""")))}),format.raw/*104.10*/("""
							"""),format.raw/*105.8*/("""</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:left;">OBSERVACIONES: </td>
						<td style="text-align:left;">
							<textarea class='form-control form-control-sm' rows='3'
								id="observaciones"
								onchange="value = value.trim(); modificarUsuario(id)"
								autocomplete="off">"""),_display_(/*114.29*/usuario/*114.36*/.getObservaciones()),format.raw/*114.55*/("""</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div id="mensajePorProyecto" align="center"></div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<table id="selectBodegas" class="table table-sm table-bordered table-condensed table-fluid" hidden>
								<thead style="background-color: #eeeeee">
									<tr>
										<th colspan="10" style="text-align:left">
											<font color="#008000"> ACCESO A """),_display_(/*128.45*/mapDiccionario/*128.59*/.get("BODEGAS")),format.raw/*128.74*/(""": </font>
										</th>
									</tr>
									<tr>
										<TH>TIPO """),_display_(/*132.21*/mapDiccionario/*132.35*/.get("BODEGA")),format.raw/*132.49*/("""</TH>
										<TH>ACCESO A """),_display_(/*133.25*/mapDiccionario/*133.39*/.get("BODEGAS")),format.raw/*133.54*/("""</TH>
										<TH>Del</TH>
									</tr>
								</thead>
								<tbody>
									"""),_display_(/*138.11*/for(lista <- listPermisoBodPorUsuario) yield /*138.49*/{_display_(Seq[Any](format.raw/*138.50*/("""
										"""),format.raw/*139.11*/("""<tr>
									        <td style="text-align:left;">"""),_display_(/*140.48*/lista/*140.53*/.get(1)),format.raw/*140.60*/("""</td>
											<td style="text-align:left;">"""),_display_(/*141.42*/lista/*141.47*/.get(2)),format.raw/*141.54*/("""</td>
											<td  style="text-align:center;">
												<a href="#" onclick= "eliminarBodegaEmpresa('"""),_display_(/*143.59*/lista/*143.64*/.get(0)),format.raw/*143.71*/("""')">
													<kbd style="background-color: red">X</kbd>
												</a>
											</td>
										</tr>
									""")))}),format.raw/*148.11*/("""
									"""),format.raw/*149.10*/("""<td colspan=7>
										<div align="center">
											<form method="post" action="/usuarioListBodegaEmpresa/">
												<input type="hidden" name="id_usuario" value=""""),_display_(/*152.60*/usuario/*152.67*/.getId()),format.raw/*152.75*/("""">
												<input type="submit" class="btn btn-light btn-sm rounded-pill" 
													value = 'Agregar """),_display_(/*154.32*/mapDiccionario/*154.46*/.get("BODEGA")),format.raw/*154.60*/("""'>
											</form>
										</div>
									</td>
								</tbody>
							</table>
						</td>
					</tr>
					
					
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/usuarioMantencion/';">
				</div>
			</div>
		</div>
	</div>
	
	<form id="formUser" method="post" action="/usuarioBodegaEmpresaElimina/">
		<input type="hidden" id="form_id_bodegaEmpresa" name="id_bodegaEmpresa">
		<input type="hidden" id="form_id_usuario" name="id_usuario">
	</form>

""")))}),format.raw/*181.2*/("""



"""),format.raw/*185.1*/("""<script type="text/javascript">
	let id_usuario = """),_display_(/*186.20*/usuario/*186.27*/.getId()),format.raw/*186.35*/(""";

	$(document).ready(function() """),format.raw/*188.31*/("""{"""),format.raw/*188.32*/("""
		"""),format.raw/*189.3*/("""$('#id_tipoUsuario > option[value=""""),_display_(/*189.39*/usuario/*189.46*/.getId_tipoUsuario()),format.raw/*189.66*/(""""]').attr('selected', 'selected');
		if("""),_display_(/*190.7*/esPorPorProyecto),format.raw/*190.23*/("""==1)"""),format.raw/*190.27*/("""{"""),format.raw/*190.28*/("""
	  		"""),format.raw/*191.6*/("""document.getElementById("mensajePorProyecto").innerHTML = "<kbd style='background-color: blue'>PERFIL CON ACCESO SOLO A "+'"""),_display_(/*191.130*/mapDiccionario/*191.144*/.get("BODEGA")),format.raw/*191.158*/("""'+"S/PROYECTOS AGREGADOS</kbd>";
	  		document.getElementById("selectBodegas").removeAttribute("hidden");
	  	"""),format.raw/*193.5*/("""}"""),format.raw/*193.6*/("""else"""),format.raw/*193.10*/("""{"""),format.raw/*193.11*/("""
	  		"""),format.raw/*194.6*/("""document.getElementById("mensajePorProyecto").innerHTML = "<kbd style='background-color: blue'>PERFIL CON ACCESO A TODAS/TODOS "+'"""),_display_(/*194.137*/mapDiccionario/*194.151*/.get("BODEGA")),format.raw/*194.165*/("""'+"S/PROYECTOS</kbd>";
	  	"""),format.raw/*195.5*/("""}"""),format.raw/*195.6*/("""
		 """),format.raw/*196.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*197.2*/("""}"""),format.raw/*197.3*/(""");
	
	const modificarUsuario = (campo) => """),format.raw/*199.38*/("""{"""),format.raw/*199.39*/("""
		"""),format.raw/*200.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_usuario',id_usuario);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*205.16*/("""{"""),format.raw/*205.17*/("""
            """),format.raw/*206.13*/("""url: "/modificaUsuarioPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*213.36*/("""{"""),format.raw/*213.37*/("""
	     		"""),format.raw/*214.9*/("""if(respuesta=="existe")"""),format.raw/*214.32*/("""{"""),format.raw/*214.33*/("""
	     			"""),format.raw/*215.10*/("""alertify.alert('El usuario (userName) ya existe, intente con otro', function () """),format.raw/*215.90*/("""{"""),format.raw/*215.91*/("""
	     				"""),format.raw/*216.11*/("""$("#userName").val(""""),_display_(/*216.32*/usuario/*216.39*/.getUserName()),format.raw/*216.53*/("""");
		     		"""),format.raw/*217.10*/("""}"""),format.raw/*217.11*/(""");
	     		"""),format.raw/*218.9*/("""}"""),format.raw/*218.10*/("""else if(respuesta=="error")"""),format.raw/*218.37*/("""{"""),format.raw/*218.38*/("""
	     			"""),format.raw/*219.10*/("""alertify.alert(msgError, function () """),format.raw/*219.47*/("""{"""),format.raw/*219.48*/("""
		     			"""),format.raw/*220.11*/("""location.href = "/";
		     		"""),format.raw/*221.10*/("""}"""),format.raw/*221.11*/(""");
	     		"""),format.raw/*222.9*/("""}"""),format.raw/*222.10*/("""
	     	"""),format.raw/*223.8*/("""}"""),format.raw/*223.9*/("""
        """),format.raw/*224.9*/("""}"""),format.raw/*224.10*/(""");
	"""),format.raw/*225.2*/("""}"""),format.raw/*225.3*/("""
	
	"""),format.raw/*227.2*/("""const eliminarBodegaEmpresa = (id_bodegaEmpresa) => """),format.raw/*227.54*/("""{"""),format.raw/*227.55*/("""
		"""),format.raw/*228.3*/("""alertify.confirm('Esta seguro de eliminar """),_display_(/*228.46*/mapDiccionario/*228.60*/.get("BODEGA")),format.raw/*228.74*/("""', function (e) """),format.raw/*228.90*/("""{"""),format.raw/*228.91*/("""
			"""),format.raw/*229.4*/("""if (e) """),format.raw/*229.11*/("""{"""),format.raw/*229.12*/("""
				"""),format.raw/*230.5*/("""$("#form_id_bodegaEmpresa").val(id_bodegaEmpresa);
				$("#form_id_usuario").val(id_usuario);
				document.getElementById("formUser").submit();
			"""),format.raw/*233.4*/("""}"""),format.raw/*233.5*/("""
		"""),format.raw/*234.3*/("""}"""),format.raw/*234.4*/(""");
	"""),format.raw/*235.2*/("""}"""),format.raw/*235.3*/("""
	
	"""),format.raw/*237.2*/("""let mapAux = new Map();
	"""),_display_(/*238.3*/for(lista <- listEsPorProyecto) yield /*238.34*/{_display_(Seq[Any](format.raw/*238.35*/("""
		"""),format.raw/*239.3*/("""mapAux.set("""),_display_(/*239.15*/lista/*239.20*/.get(0)),format.raw/*239.27*/(""","""),_display_(/*239.29*/lista/*239.34*/.get(1)),format.raw/*239.41*/(""");
	""")))}),format.raw/*240.3*/("""
	"""),format.raw/*241.2*/("""const cambiaMensaje = (id_tipoUsuario) => """),format.raw/*241.44*/("""{"""),format.raw/*241.45*/("""
		"""),format.raw/*242.3*/("""let porProyecto = mapAux.get(parseFloat(id_tipoUsuario));
		if(porProyecto==1)"""),format.raw/*243.21*/("""{"""),format.raw/*243.22*/("""
			"""),format.raw/*244.4*/("""document.getElementById("mensajePorProyecto").innerHTML = "<kbd style='background-color: blue'>PERFIL CON ACCESO SOLO A "+'"""),_display_(/*244.128*/mapDiccionario/*244.142*/.get("BODEGA")),format.raw/*244.156*/("""'+"S/PROYECTOS AGREGADOS</kbd>";
	  		document.getElementById("selectBodegas").removeAttribute("hidden");
	  	"""),format.raw/*246.5*/("""}"""),format.raw/*246.6*/("""else"""),format.raw/*246.10*/("""{"""),format.raw/*246.11*/("""
	  		"""),format.raw/*247.6*/("""document.getElementById("mensajePorProyecto").innerHTML = "<kbd style='background-color: blue'>PERFIL CON ACCESO A TODAS/TODOS "+'"""),_display_(/*247.137*/mapDiccionario/*247.151*/.get("BODEGA")),format.raw/*247.165*/("""'+"S/PROYECTOS</kbd>";
	  		document.getElementById("selectBodegas").setAttribute("hidden","true");
	  	"""),format.raw/*249.5*/("""}"""),format.raw/*249.6*/("""
	"""),format.raw/*250.2*/("""}"""),format.raw/*250.3*/("""

"""),format.raw/*252.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,usuario:tables.Usuario,listTipoUsuario:List[tables.UsuarioTipo],listPermisoBodPorUsuario:List[List[String]],esPorPorProyecto:Long,listEsPorProyecto:List[List[Long]],listSucursal:List[tables.Sucursal],sucursal:tables.Sucursal): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,usuario,listTipoUsuario,listPermisoBodPorUsuario,esPorPorProyecto,listEsPorProyecto,listSucursal,sucursal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Usuario,List[tables.UsuarioTipo],List[List[String]],Long,List[List[Long]],List[tables.Sucursal],tables.Sucursal) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,usuario,listTipoUsuario,listPermisoBodPorUsuario,esPorPorProyecto,listEsPorProyecto,listSucursal,sucursal) => apply(mapDiccionario,mapPermiso,userMnu,usuario,listTipoUsuario,listPermisoBodPorUsuario,esPorPorProyecto,listEsPorProyecto,listSucursal,sucursal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/usuarioModifica.scala.html
                  HASH: 8638b77b2de7c71367e3b378cfc749688d552152
                  MATRIX: 1133->1|1563->338|1596->346|1612->354|1651->356|1679->359|1747->407|1775->409|1852->460|1925->512|1955->515|2438->971|2454->978|2489->992|2852->1328|2868->1335|2902->1348|3317->1736|3333->1743|3366->1755|3768->2130|3784->2137|3816->2148|4164->2469|4180->2476|4212->2487|4561->2809|4577->2816|4608->2826|4988->3179|5033->3208|5072->3209|5110->3219|5153->3235|5167->3240|5196->3248|5226->3251|5240->3256|5271->3266|5321->3285|5356->3293|5644->3553|5662->3561|5692->3569|5723->3572|5741->3580|5775->3592|5822->3611|5865->3637|5905->3638|5944->3648|5988->3664|6003->3669|6033->3677|6064->3680|6079->3685|6113->3697|6164->3716|6200->3724|6543->4039|6560->4046|6601->4065|7078->4514|7102->4528|7139->4543|7242->4618|7266->4632|7302->4646|7360->4676|7384->4690|7421->4705|7536->4792|7591->4830|7631->4831|7671->4842|7751->4894|7766->4899|7795->4906|7870->4953|7885->4958|7914->4965|8050->5073|8065->5078|8094->5085|8247->5206|8286->5216|8487->5389|8504->5396|8534->5404|8672->5514|8696->5528|8732->5542|9450->6229|9482->6233|9561->6284|9578->6291|9608->6299|9670->6332|9700->6333|9731->6336|9795->6372|9812->6379|9854->6399|9922->6440|9960->6456|9993->6460|10023->6461|10057->6467|10210->6591|10235->6605|10272->6619|10410->6729|10439->6730|10472->6734|10502->6735|10536->6741|10696->6872|10721->6886|10758->6900|10813->6927|10842->6928|10874->6932|10968->6998|10997->6999|11068->7041|11098->7042|11129->7045|11356->7243|11386->7244|11428->7257|11697->7497|11727->7498|11764->7507|11816->7530|11846->7531|11885->7541|11994->7621|12024->7622|12064->7633|12113->7654|12130->7661|12166->7675|12208->7688|12238->7689|12277->7700|12307->7701|12363->7728|12393->7729|12432->7739|12498->7776|12528->7777|12568->7788|12627->7818|12657->7819|12696->7830|12726->7831|12762->7839|12791->7840|12828->7849|12858->7850|12890->7854|12919->7855|12951->7859|13032->7911|13062->7912|13093->7915|13164->7958|13188->7972|13224->7986|13269->8002|13299->8003|13331->8007|13367->8014|13397->8015|13430->8020|13605->8167|13634->8168|13665->8171|13694->8172|13726->8176|13755->8177|13787->8181|13840->8207|13888->8238|13928->8239|13959->8242|13999->8254|14014->8259|14043->8266|14073->8268|14088->8273|14117->8280|14153->8285|14183->8287|14254->8329|14284->8330|14315->8333|14422->8411|14452->8412|14484->8416|14637->8540|14662->8554|14699->8568|14837->8678|14866->8679|14899->8683|14929->8684|14963->8690|15123->8821|15148->8835|15185->8849|15317->8953|15346->8954|15376->8956|15405->8957|15435->8959
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|42->11|52->21|52->21|52->21|63->32|63->32|63->32|75->44|75->44|75->44|87->56|87->56|87->56|98->67|98->67|98->67|109->78|109->78|109->78|120->89|120->89|120->89|121->90|121->90|121->90|121->90|121->90|121->90|121->90|122->91|123->92|132->101|132->101|132->101|132->101|132->101|132->101|133->102|133->102|133->102|134->103|134->103|134->103|134->103|134->103|134->103|134->103|135->104|136->105|145->114|145->114|145->114|159->128|159->128|159->128|163->132|163->132|163->132|164->133|164->133|164->133|169->138|169->138|169->138|170->139|171->140|171->140|171->140|172->141|172->141|172->141|174->143|174->143|174->143|179->148|180->149|183->152|183->152|183->152|185->154|185->154|185->154|212->181|216->185|217->186|217->186|217->186|219->188|219->188|220->189|220->189|220->189|220->189|221->190|221->190|221->190|221->190|222->191|222->191|222->191|222->191|224->193|224->193|224->193|224->193|225->194|225->194|225->194|225->194|226->195|226->195|227->196|228->197|228->197|230->199|230->199|231->200|236->205|236->205|237->206|244->213|244->213|245->214|245->214|245->214|246->215|246->215|246->215|247->216|247->216|247->216|247->216|248->217|248->217|249->218|249->218|249->218|249->218|250->219|250->219|250->219|251->220|252->221|252->221|253->222|253->222|254->223|254->223|255->224|255->224|256->225|256->225|258->227|258->227|258->227|259->228|259->228|259->228|259->228|259->228|259->228|260->229|260->229|260->229|261->230|264->233|264->233|265->234|265->234|266->235|266->235|268->237|269->238|269->238|269->238|270->239|270->239|270->239|270->239|270->239|270->239|270->239|271->240|272->241|272->241|272->241|273->242|274->243|274->243|275->244|275->244|275->244|275->244|277->246|277->246|277->246|277->246|278->247|278->247|278->247|278->247|280->249|280->249|281->250|281->250|283->252
                  -- GENERATED --
              */
          