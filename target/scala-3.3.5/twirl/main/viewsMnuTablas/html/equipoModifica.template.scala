
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

object equipoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template9[Map[String,String],Map[String,String],utilities.UserMnu,tables.Equipo,List[tables.Grupo],List[tables.Atributo],List[tables.Fabrica],List[tables.Unidad],List[tables.Propiedad],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
equipo: tables.Equipo, listGrupos: List[tables.Grupo], listAtributos: List[tables.Atributo], listFabrica: List[tables.Fabrica],
listUnidades: List[tables.Unidad], listPropiedad: List[tables.Propiedad]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

"""),_display_(/*8.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.50*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "MODIFICAR EQUIPO Y/O ATRIBUTOS", "")),format.raw/*10.69*/("""
		"""),format.raw/*11.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<TR>
						<td  style="text-align:left;">GRUPO</td>
						<td style="text-align:left">
							<select class="custom-select" 
								id="id_grupo" 
								onchange="cambiarGrupo(value)">
								<option value=""""),_display_(/*20.25*/equipo/*20.31*/.getId_grupo),format.raw/*20.43*/("""">"""),_display_(/*20.46*/equipo/*20.52*/.getGrupo()),format.raw/*20.63*/("""</option>
								"""),_display_(/*21.10*/for(lista <- listGrupos) yield /*21.34*/{_display_(Seq[Any](format.raw/*21.35*/("""
									"""),format.raw/*22.10*/("""<option value=""""),_display_(/*22.26*/lista/*22.31*/.getId()),format.raw/*22.39*/("""">"""),_display_(/*22.42*/lista/*22.47*/.getNombre()),format.raw/*22.59*/("""</option>
								""")))}),format.raw/*23.10*/("""
							"""),format.raw/*24.8*/("""</select>
						</td>
						<td style="text-align: center">
							<form id="imagen" enctype="multipart/form-data" class="formulario" method="post" action="/grabaImgEquipo/">
								<input type="hidden" name="id_equipo" value=""""),_display_(/*28.55*/equipo/*28.61*/.getId()),format.raw/*28.69*/("""">
								"""),_display_(if(equipo.getImg()!="0")/*29.34*/{_display_(Seq[Any](format.raw/*29.35*/("""
									"""),format.raw/*30.10*/("""<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
										<div id="txtBtn">Cambiar Imagen</div>
				    					<input type="file" id="img" name="img" onchange="LimitAttach(this.form, this.form.img.value);">
									</span>
								""")))}else/*34.14*/{_display_(Seq[Any](format.raw/*34.15*/("""
									"""),format.raw/*35.10*/("""<span class="btn btn-info btn-sm btn-file" style="font-size: 8px;">
										<div id="txtBtn">Adjuntar Imagen</div>
				    					<input type="file" id="img" name="img" onchange="LimitAttach(this.form, this.form.img.value);">
									</span>
								""")))}),format.raw/*39.10*/("""
							"""),format.raw/*40.8*/("""</form>
						</td>
					</TR>
					<TR>
						<td  style="text-align:left;">PROPIEDAD</td>
						<td style="text-align:left">
							<select class="custom-select"
							id="id_propiedad"
							onchange="cambiaPropiedad(value)">
								<option value=""""),_display_(/*49.25*/equipo/*49.31*/.getId_propiedad),format.raw/*49.47*/("""">"""),_display_(/*49.50*/equipo/*49.56*/.getPropiedad()),format.raw/*49.71*/("""</option>
								"""),_display_(/*50.10*/for(lista <- listPropiedad) yield /*50.37*/{_display_(Seq[Any](format.raw/*50.38*/("""
									"""),format.raw/*51.10*/("""<option value=""""),_display_(/*51.26*/lista/*51.31*/.getId()),format.raw/*51.39*/("""">"""),_display_(/*51.42*/lista/*51.47*/.getNombre()),format.raw/*51.59*/("""</option>
								""")))}),format.raw/*52.10*/("""
							"""),format.raw/*53.8*/("""</select>
						</td>
					</TR>
					<tr>
						<td style="text-align:left; vertical-align:top">CODIGO EQUIPO: </td>
						<td style="text-align:left; vertical-align:top; max-width:80px">
							<input type="text" class="form-control left"
								id="codigo"
								autocomplete="off"
								value=""""),_display_(/*62.17*/equipo/*62.23*/.getCodigo()),format.raw/*62.35*/(""""
							    onkeydown="return sinReservCodigos(window.event)"
								maxlength="50"
								onchange="value=value.replace(/\s/g,'').toUpperCase(); modificarEquipo(id)">
						</td>
						"""),_display_(if(equipo.getImg()!="0")/*67.32*/{_display_(Seq[Any](format.raw/*67.33*/("""
							"""),format.raw/*68.8*/("""<td rowspan="100" style="text-align: center; vertical-align: middle">
								<img src='/viewImg/"""),_display_(/*69.29*/equipo/*69.35*/.getImg()),format.raw/*69.44*/("""' style="max-width:200px; height:auto">
							</td>
						""")))}else/*71.12*/{_display_(Seq[Any](format.raw/*71.13*/("""
							"""),format.raw/*72.8*/("""<td rowspan="100" style="text-align: center; vertical-align: middle"></td>
						""")))}),format.raw/*73.8*/("""
					"""),format.raw/*74.6*/("""</tr>
					<tr>
						<td style="text-align:left; vertical-align:top">NOMBRE EQUIPO: </td>
						<td style="text-align:left; vertical-align:top">
							<input type="text" class="form-control left"
								id="nombre"
								autocomplete="off"
								value=""""),_display_(/*81.17*/equipo/*81.23*/.getNombre()),format.raw/*81.35*/(""""
								onkeydown="return sinReservados(window.event)"
								maxlength="100"
								onchange="value = value.trim(); modificarEquipo(id)">
						</td>
					</tr>
					<tr>
						<td style="text-align:left; vertical-align:top">FABRICANTE: </td>
						<td style="text-align:left; vertical-align:top">
							<select class="custom-select" 
								id="id_fabrica" 
								onchange="modificarEquipo(id)">
								"""),_display_(/*93.10*/for(lista <- listFabrica) yield /*93.35*/{_display_(Seq[Any](format.raw/*93.36*/("""
									"""),format.raw/*94.10*/("""<option value=""""),_display_(/*94.26*/lista/*94.31*/.id),format.raw/*94.34*/("""">"""),_display_(/*94.37*/lista/*94.42*/.nickName),format.raw/*94.51*/("""</option>
								""")))}),format.raw/*95.10*/("""
							"""),format.raw/*96.8*/("""</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:left; vertical-align:top">UNIDAD: </td>
						<td style="text-align:left; vertical-align:top">
							<select class="custom-select" 
								id="id_unidad" 
								onchange="modificarEquipo(id)">
								"""),_display_(/*105.10*/for(lista <- listUnidades) yield /*105.36*/{_display_(Seq[Any](format.raw/*105.37*/("""
									"""),format.raw/*106.10*/("""<option value=""""),_display_(/*106.26*/lista/*106.31*/.id),format.raw/*106.34*/("""">"""),_display_(/*106.37*/lista/*106.42*/.nombre),format.raw/*106.49*/("""</option>
								""")))}),format.raw/*107.10*/("""
							"""),format.raw/*108.8*/("""</select>
						</td>
					</tr>
					<tr>
						<td style="text-align:left; vertical-align:top">PESO (KG): </td>
						<td style="text-align:left; vertical-align:top">
							<input type="text" class="form-control left"
								id="kg"
								autocomplete="off"
								value=""""),_display_(/*117.17*/equipo/*117.23*/.getKg()),format.raw/*117.31*/(""""
								onfocus="value=value.replace(/,/g,'')" 
								onkeydown="return ingresoDouble(window.event)"
								onchange="modificarEquipo(id); value = formatStandar(value,2);">
						</td>
					</tr>
					<tr>
						<td style="text-align:left; vertical-align:top">AREA (M2): </td>
						<td style="text-align:left; vertical-align:top">
							<input type="text" class="form-control left"
								id="m2"
								autocomplete="off"
								value=""""),_display_(/*129.17*/equipo/*129.23*/.getM2()),format.raw/*129.31*/(""""
								onfocus="value=value.replace(/,/g,'')" 
								onkeydown="return ingresoDouble(window.event)"
								onchange="modificarEquipo(id); value = formatStandar(value,2);">
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align:left">
							<font color="#008000"> ATRIBUTOS: 
							"""),_display_(if(listAtributos.size()==0)/*138.36*/ {_display_(Seq[Any](format.raw/*138.38*/("""
								"""),format.raw/*139.9*/("""No hay atributos definidos para el grupo """),_display_(/*139.51*/equipo/*139.57*/.getGrupo()),format.raw/*139.68*/("""
							""")))} else {null} ),format.raw/*140.9*/("""</font>
						</td>
					</tr>
					"""),_display_(/*143.7*/for(lista <- listAtributos) yield /*143.34*/{_display_(Seq[Any](format.raw/*143.35*/("""
						"""),format.raw/*144.7*/("""<tr>
							<td style="text-align:left">"""),_display_(/*145.37*/lista/*145.42*/.getAtributo()),format.raw/*145.56*/(""" """),format.raw/*145.57*/("""("""),_display_(/*145.59*/lista/*145.64*/.getUnidad()),format.raw/*145.76*/(""")</td>
							<td style="text-align:left">
								"""),_display_(if(lista.getEsNumerico()==1)/*147.38*/{_display_(Seq[Any](format.raw/*147.39*/("""
									"""),format.raw/*148.10*/("""<input type="text" class="form-control left"
										id="atrib_"""),_display_(/*149.22*/lista/*149.27*/.getId()),format.raw/*149.35*/(""""
										value=""""),_display_(/*150.19*/lista/*150.24*/.getValorEnEquipo()),format.raw/*150.43*/(""""
										onfocus="value=value.replace(/,/g,'')" 
										onkeydown="return ingresoDouble(window.event)"
										onchange="modificarAtributo(id, '"""),_display_(/*153.45*/lista/*153.50*/.getEsNumerico),format.raw/*153.64*/("""')">
								""")))}else/*154.14*/{_display_(Seq[Any](format.raw/*154.15*/("""
									"""),format.raw/*155.10*/("""<input type="text" class="form-control left"
										id="atrib_"""),_display_(/*156.22*/lista/*156.27*/.getId()),format.raw/*156.35*/(""""
										autocomplete="off"
										value=""""),_display_(/*158.19*/lista/*158.24*/.getValorEnEquipo()),format.raw/*158.43*/(""""
										maxlength="50"
										onchange="value = value.trim(); modificarAtributo(id, '"""),_display_(/*160.67*/lista/*160.72*/.getEsNumerico),format.raw/*160.86*/("""')">
								""")))}),format.raw/*161.10*/("""
							"""),format.raw/*162.8*/("""</td>
						</tr>
					""")))}),format.raw/*164.7*/("""
				"""),format.raw/*165.5*/("""</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/equipoMantencion/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*177.2*/("""



"""),format.raw/*181.1*/("""<script type="text/javascript">
	let id_equipo = '"""),_display_(/*182.20*/equipo/*182.26*/.getId()),format.raw/*182.34*/("""';
	let indexGrupo = 0;

	$(document).ready(function() """),format.raw/*185.31*/("""{"""),format.raw/*185.32*/("""
		  """),format.raw/*186.5*/("""$('#id_grupo > option[value=""""),_display_(/*186.35*/equipo/*186.41*/.getId_grupo()),format.raw/*186.55*/(""""]').attr('selected', 'selected');
		  $('#id_fabrica > option[value=""""),_display_(/*187.37*/equipo/*187.43*/.getId_fabrica()),format.raw/*187.59*/(""""]').attr('selected', 'selected');
		  $('#id_unidad > option[value=""""),_display_(/*188.36*/equipo/*188.42*/.getId_unidad()),format.raw/*188.57*/(""""]').attr('selected', 'selected');
		  indexGrupo = document.getElementById("id_grupo").selectedIndex;
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*191.2*/("""}"""),format.raw/*191.3*/(""");
	
	const cambiarGrupo = (id_grupo) => """),format.raw/*193.37*/("""{"""),format.raw/*193.38*/("""
		"""),format.raw/*194.3*/("""alertify.confirm("Esta seguro de cambiar, debe tener presente que al" +
				" cambiar de grupo se pierden los atributos, deben ser nuevamente llenados para el equipo", function (e) """),format.raw/*195.110*/("""{"""),format.raw/*195.111*/("""
			"""),format.raw/*196.4*/("""if (e) """),format.raw/*196.11*/("""{"""),format.raw/*196.12*/("""
				"""),format.raw/*197.5*/("""location.href = "/cambiarGrupoEquipo/"+id_grupo+","+id_equipo;
			"""),format.raw/*198.4*/("""}"""),format.raw/*198.5*/("""else"""),format.raw/*198.9*/("""{"""),format.raw/*198.10*/("""
				"""),format.raw/*199.5*/("""document.getElementById("id_grupo").selectedIndex = indexGrupo;
			"""),format.raw/*200.4*/("""}"""),format.raw/*200.5*/("""
		"""),format.raw/*201.3*/("""}"""),format.raw/*201.4*/(""");
	"""),format.raw/*202.2*/("""}"""),format.raw/*202.3*/("""
	
	"""),format.raw/*204.2*/("""const modificarEquipo = (campo) => """),format.raw/*204.37*/("""{"""),format.raw/*204.38*/("""
		"""),format.raw/*205.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_equipo',id_equipo);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*210.16*/("""{"""),format.raw/*210.17*/("""
            """),format.raw/*211.13*/("""url: "/modificaEquipoPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*218.36*/("""{"""),format.raw/*218.37*/("""
	     		"""),format.raw/*219.9*/("""if(respuesta=="existe")"""),format.raw/*219.32*/("""{"""),format.raw/*219.33*/("""
	     			"""),format.raw/*220.10*/("""alertify.alert('El código de equipo ya existe, intente con otro', function () """),format.raw/*220.88*/("""{"""),format.raw/*220.89*/("""
	     				"""),format.raw/*221.11*/("""$("#codigo").val(""""),_display_(/*221.30*/equipo/*221.36*/.getCodigo()),format.raw/*221.48*/("""");
		     		"""),format.raw/*222.10*/("""}"""),format.raw/*222.11*/(""");
	     		"""),format.raw/*223.9*/("""}"""),format.raw/*223.10*/("""else if(respuesta=="error")"""),format.raw/*223.37*/("""{"""),format.raw/*223.38*/("""
	     			"""),format.raw/*224.10*/("""alertify.alert(msgError, function () """),format.raw/*224.47*/("""{"""),format.raw/*224.48*/("""
		     			"""),format.raw/*225.11*/("""location.href = "/";
		     		"""),format.raw/*226.10*/("""}"""),format.raw/*226.11*/(""");
	     		"""),format.raw/*227.9*/("""}"""),format.raw/*227.10*/("""
	     	"""),format.raw/*228.8*/("""}"""),format.raw/*228.9*/("""
        """),format.raw/*229.9*/("""}"""),format.raw/*229.10*/(""");
	"""),format.raw/*230.2*/("""}"""),format.raw/*230.3*/("""
	
	"""),format.raw/*232.2*/("""const modificarAtributo = (atrib_id_atributo, esNumerico) => """),format.raw/*232.63*/("""{"""),format.raw/*232.64*/("""
		"""),format.raw/*233.3*/("""let valor = $("#"+atrib_id_atributo).val(); 
		var formData = new FormData();
		let aux = atrib_id_atributo.split("_");
	  	formData.append('id_atributo',aux[1]);
	  	formData.append('id_equipo',id_equipo);
	  	formData.append('valor',valor);
	  	formData.append('esNumerico',esNumerico);
        $.ajax("""),format.raw/*240.16*/("""{"""),format.raw/*240.17*/("""
            """),format.raw/*241.13*/("""url: "/modificaAtributoEquipoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*248.36*/("""{"""),format.raw/*248.37*/("""
	     		"""),format.raw/*249.9*/("""if(respuesta=="error")"""),format.raw/*249.31*/("""{"""),format.raw/*249.32*/("""
	     			"""),format.raw/*250.10*/("""alertify.alert(msgError, function () """),format.raw/*250.47*/("""{"""),format.raw/*250.48*/("""
		     			"""),format.raw/*251.11*/("""location.href = "/";
		     		"""),format.raw/*252.10*/("""}"""),format.raw/*252.11*/(""");
	     		"""),format.raw/*253.9*/("""}"""),format.raw/*253.10*/("""
	     	"""),format.raw/*254.8*/("""}"""),format.raw/*254.9*/("""
        """),format.raw/*255.9*/("""}"""),format.raw/*255.10*/(""");
	"""),format.raw/*256.2*/("""}"""),format.raw/*256.3*/("""

	"""),format.raw/*258.2*/("""const cambiaPropiedad = (id_propiedad) =>"""),format.raw/*258.43*/("""{"""),format.raw/*258.44*/("""
		"""),format.raw/*259.3*/("""var formData = new FormData();
		formData.append('id_equipo',id_equipo);
		formData.append('id_propiedad',id_propiedad);
		$.ajax("""),format.raw/*262.10*/("""{"""),format.raw/*262.11*/("""
			"""),format.raw/*263.4*/("""url: "/routes2/equipoCambiaPropiedad/",
			type: "POST",
			method: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(respuesta)"""),format.raw/*270.32*/("""{"""),format.raw/*270.33*/("""
				"""),format.raw/*271.5*/("""if( ! respuesta.status)"""),format.raw/*271.28*/("""{"""),format.raw/*271.29*/("""
					"""),format.raw/*272.6*/("""location.reload();
				"""),format.raw/*273.5*/("""}"""),format.raw/*273.6*/("""
			"""),format.raw/*274.4*/("""}"""),format.raw/*274.5*/("""
		"""),format.raw/*275.3*/("""}"""),format.raw/*275.4*/(""");
	"""),format.raw/*276.2*/("""}"""),format.raw/*276.3*/("""

	"""),format.raw/*278.2*/("""let extArray = new Array(".gif", ".jpg", ".png",".jpeg");
	const LimitAttach = (form, file) => """),format.raw/*279.38*/("""{"""),format.raw/*279.39*/("""
		"""),format.raw/*280.3*/("""let allowSubmit = false;
		if (!file) return;
		while (file.indexOf("\\") != -1)"""),format.raw/*282.35*/("""{"""),format.raw/*282.36*/("""
			"""),format.raw/*283.4*/("""file = file.slice(file.indexOf("\\") + 1);
		"""),format.raw/*284.3*/("""}"""),format.raw/*284.4*/("""
		"""),format.raw/*285.3*/("""let extencion = file.slice(file.lastIndexOf(".")).toLowerCase();
		for (var i = 0; i < extArray.length; i++) """),format.raw/*286.45*/("""{"""),format.raw/*286.46*/("""
			"""),format.raw/*287.4*/("""if (extArray[i] == extencion) """),format.raw/*287.34*/("""{"""),format.raw/*287.35*/(""" """),format.raw/*287.36*/("""allowSubmit = true; break; """),format.raw/*287.63*/("""}"""),format.raw/*287.64*/("""
		"""),format.raw/*288.3*/("""}"""),format.raw/*288.4*/("""
		"""),format.raw/*289.3*/("""if (allowSubmit) """),format.raw/*289.20*/("""{"""),format.raw/*289.21*/("""	        
			"""),format.raw/*290.4*/("""var file = $("#img")[0].files[0];
			var fileSize = file.size; //tamaño del archivo
			if(fileSize>100000000)"""),format.raw/*292.26*/("""{"""),format.raw/*292.27*/("""
				"""),format.raw/*293.5*/("""alert("Se permiten únicamente archivos que no superen los 100 MB,"
				+" el que se intenta subir pesa: "+Math.round(file.size/10)/100+" KB");
				form.img.value="";
			"""),format.raw/*296.4*/("""}"""),format.raw/*296.5*/("""else"""),format.raw/*296.9*/("""{"""),format.raw/*296.10*/("""
				"""),format.raw/*297.5*/("""document.getElementById('imagen').submit();
			"""),format.raw/*298.4*/("""}"""),format.raw/*298.5*/("""
		"""),format.raw/*299.3*/("""}"""),format.raw/*299.4*/("""else"""),format.raw/*299.8*/("""{"""),format.raw/*299.9*/("""
			"""),format.raw/*300.4*/("""alert("Se permiten únicamente archivos con la extención: " 
			+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
			+ "e intente de nuevo.");
			form.img.value="";
		"""),format.raw/*304.3*/("""}"""),format.raw/*304.4*/("""
	"""),format.raw/*305.2*/("""}"""),format.raw/*305.3*/("""
	
	

"""),format.raw/*309.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,equipo:tables.Equipo,listGrupos:List[tables.Grupo],listAtributos:List[tables.Atributo],listFabrica:List[tables.Fabrica],listUnidades:List[tables.Unidad],listPropiedad:List[tables.Propiedad]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,equipo,listGrupos,listAtributos,listFabrica,listUnidades,listPropiedad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Equipo,List[tables.Grupo],List[tables.Atributo],List[tables.Fabrica],List[tables.Unidad],List[tables.Propiedad]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,equipo,listGrupos,listAtributos,listFabrica,listUnidades,listPropiedad) => apply(mapDiccionario,mapPermiso,userMnu,equipo,listGrupos,listAtributos,listFabrica,listUnidades,listPropiedad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/equipoModifica.scala.html
                  HASH: 79b5aa85089c87138bd45b8bdbe244db1aed78ed
                  MATRIX: 1131->1|1523->300|1556->308|1572->316|1611->318|1639->321|1707->369|1735->371|1812->422|1898->487|1928->490|2349->884|2364->890|2397->902|2427->905|2442->911|2474->922|2520->941|2560->965|2599->966|2637->976|2680->992|2694->997|2723->1005|2753->1008|2767->1013|2800->1025|2850->1044|2885->1052|3141->1281|3156->1287|3185->1295|3248->1331|3287->1332|3325->1342|3599->1597|3638->1598|3676->1608|3959->1860|3994->1868|4276->2123|4291->2129|4328->2145|4358->2148|4373->2154|4409->2169|4455->2188|4498->2215|4537->2216|4575->2226|4618->2242|4632->2247|4661->2255|4691->2258|4705->2263|4738->2275|4788->2294|4823->2302|5154->2606|5169->2612|5202->2624|5443->2838|5482->2839|5517->2847|5642->2945|5657->2951|5687->2960|5770->3024|5809->3025|5844->3033|5956->3115|5989->3121|6277->3382|6292->3388|6325->3400|6767->3815|6808->3840|6847->3841|6885->3851|6928->3867|6942->3872|6966->3875|6996->3878|7010->3883|7040->3892|7090->3911|7125->3919|7430->4196|7473->4222|7513->4223|7552->4233|7596->4249|7611->4254|7636->4257|7667->4260|7682->4265|7711->4272|7762->4291|7798->4299|8106->4579|8122->4585|8152->4593|8628->5041|8644->5047|8674->5055|9037->5390|9078->5392|9115->5401|9185->5443|9201->5449|9234->5460|9287->5469|9351->5506|9395->5533|9435->5534|9470->5541|9539->5582|9554->5587|9590->5601|9620->5602|9650->5604|9665->5609|9699->5621|9807->5701|9847->5702|9886->5712|9980->5778|9995->5783|10025->5791|10073->5811|10088->5816|10129->5835|10310->5988|10325->5993|10361->6007|10399->6025|10439->6026|10478->6036|10572->6102|10587->6107|10617->6115|10694->6164|10709->6169|10750->6188|10871->6281|10886->6286|10922->6300|10968->6314|11004->6322|11059->6346|11092->6351|11459->6687|11491->6691|11570->6742|11586->6748|11616->6756|11700->6811|11730->6812|11763->6817|11821->6847|11837->6853|11873->6867|11972->6938|11988->6944|12026->6960|12124->7030|12140->7036|12177->7051|12378->7224|12407->7225|12477->7266|12507->7267|12538->7270|12749->7451|12780->7452|12812->7456|12848->7463|12878->7464|12911->7469|13005->7535|13034->7536|13066->7540|13096->7541|13129->7546|13224->7613|13253->7614|13284->7617|13313->7618|13345->7622|13374->7623|13406->7627|13470->7662|13500->7663|13531->7666|13756->7862|13786->7863|13828->7876|14096->8115|14126->8116|14163->8125|14215->8148|14245->8149|14284->8159|14391->8237|14421->8238|14461->8249|14508->8268|14524->8274|14558->8286|14600->8299|14630->8300|14669->8311|14699->8312|14755->8339|14785->8340|14824->8350|14890->8387|14920->8388|14960->8399|15019->8429|15049->8430|15088->8441|15118->8442|15154->8450|15183->8451|15220->8460|15250->8461|15282->8465|15311->8466|15343->8470|15433->8531|15463->8532|15494->8535|15827->8839|15857->8840|15899->8853|16167->9092|16197->9093|16234->9102|16285->9124|16315->9125|16354->9135|16420->9172|16450->9173|16490->9184|16549->9214|16579->9215|16618->9226|16648->9227|16684->9235|16713->9236|16750->9245|16780->9246|16812->9250|16841->9251|16872->9254|16942->9295|16972->9296|17003->9299|17162->9429|17192->9430|17224->9434|17442->9623|17472->9624|17505->9629|17557->9652|17587->9653|17621->9659|17672->9682|17701->9683|17733->9687|17762->9688|17793->9691|17822->9692|17854->9696|17883->9697|17914->9700|18038->9795|18068->9796|18099->9799|18208->9879|18238->9880|18270->9884|18343->9929|18372->9930|18403->9933|18541->10042|18571->10043|18603->10047|18662->10077|18692->10078|18722->10079|18778->10106|18808->10107|18839->10110|18868->10111|18899->10114|18945->10131|18975->10132|19016->10145|19154->10254|19184->10255|19217->10260|19414->10429|19443->10430|19475->10434|19505->10435|19538->10440|19613->10487|19642->10488|19673->10491|19702->10492|19734->10496|19763->10497|19795->10501|20005->10683|20034->10684|20064->10686|20093->10687|20127->10693
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|42->11|51->20|51->20|51->20|51->20|51->20|51->20|52->21|52->21|52->21|53->22|53->22|53->22|53->22|53->22|53->22|53->22|54->23|55->24|59->28|59->28|59->28|60->29|60->29|61->30|65->34|65->34|66->35|70->39|71->40|80->49|80->49|80->49|80->49|80->49|80->49|81->50|81->50|81->50|82->51|82->51|82->51|82->51|82->51|82->51|82->51|83->52|84->53|93->62|93->62|93->62|98->67|98->67|99->68|100->69|100->69|100->69|102->71|102->71|103->72|104->73|105->74|112->81|112->81|112->81|124->93|124->93|124->93|125->94|125->94|125->94|125->94|125->94|125->94|125->94|126->95|127->96|136->105|136->105|136->105|137->106|137->106|137->106|137->106|137->106|137->106|137->106|138->107|139->108|148->117|148->117|148->117|160->129|160->129|160->129|169->138|169->138|170->139|170->139|170->139|170->139|171->140|174->143|174->143|174->143|175->144|176->145|176->145|176->145|176->145|176->145|176->145|176->145|178->147|178->147|179->148|180->149|180->149|180->149|181->150|181->150|181->150|184->153|184->153|184->153|185->154|185->154|186->155|187->156|187->156|187->156|189->158|189->158|189->158|191->160|191->160|191->160|192->161|193->162|195->164|196->165|208->177|212->181|213->182|213->182|213->182|216->185|216->185|217->186|217->186|217->186|217->186|218->187|218->187|218->187|219->188|219->188|219->188|222->191|222->191|224->193|224->193|225->194|226->195|226->195|227->196|227->196|227->196|228->197|229->198|229->198|229->198|229->198|230->199|231->200|231->200|232->201|232->201|233->202|233->202|235->204|235->204|235->204|236->205|241->210|241->210|242->211|249->218|249->218|250->219|250->219|250->219|251->220|251->220|251->220|252->221|252->221|252->221|252->221|253->222|253->222|254->223|254->223|254->223|254->223|255->224|255->224|255->224|256->225|257->226|257->226|258->227|258->227|259->228|259->228|260->229|260->229|261->230|261->230|263->232|263->232|263->232|264->233|271->240|271->240|272->241|279->248|279->248|280->249|280->249|280->249|281->250|281->250|281->250|282->251|283->252|283->252|284->253|284->253|285->254|285->254|286->255|286->255|287->256|287->256|289->258|289->258|289->258|290->259|293->262|293->262|294->263|301->270|301->270|302->271|302->271|302->271|303->272|304->273|304->273|305->274|305->274|306->275|306->275|307->276|307->276|309->278|310->279|310->279|311->280|313->282|313->282|314->283|315->284|315->284|316->285|317->286|317->286|318->287|318->287|318->287|318->287|318->287|318->287|319->288|319->288|320->289|320->289|320->289|321->290|323->292|323->292|324->293|327->296|327->296|327->296|327->296|328->297|329->298|329->298|330->299|330->299|330->299|330->299|331->300|335->304|335->304|336->305|336->305|340->309
                  -- GENERATED --
              */
          