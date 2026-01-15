
package viewsMnuBodegas.html

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

object sucursalModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,tables.Sucursal,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
sucursal: tables.Sucursal):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR SUCURSAL", "")),format.raw/*9.57*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td>
							<div class="row">
								<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
									<b><font color="#008000">SUCURSAL:</font></b>
								</div>
								<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
									<input type="hidden" name="id_sucursal" value=""""),_display_(/*20.58*/sucursal/*20.66*/.getId()),format.raw/*20.74*/("""">
									<input type="text" class="form-control"
										id="nombre"
										maxlength="50"
										value=""""),_display_(/*24.19*/sucursal/*24.27*/.getNombre()),format.raw/*24.39*/(""""
										onkeydown="return sinReservados(window.event)"
										onchange="value = value.trim(); modificarSucursal(id)">
								</div>
							</div>
							"""),_display_(if(mapPermiso.get("parametro.ivaPorBodega")!=null && mapPermiso.get("parametro.ivaPorBodega").equals("1"))/*29.115*/ {_display_(Seq[Any](format.raw/*29.117*/("""
								"""),format.raw/*30.9*/("""<div class="row">
									<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
										<b><font color="#008000">IVA:</font></b>
									</div>
									<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
										<input type="text" class="form-control" 
											id="ivaSucursal" 
											autocomplete="off"
											value=""""),_display_(/*38.20*/(sucursal.getIvaSucursal()*100)),format.raw/*38.51*/(""" """),format.raw/*38.52*/("""%"
											onfocus="value=value.replace(/,/g,'').replace(/%/g,'').replace(/ /g,'')" 
											onblur = "value = formatPorcentaje(value);"
											onkeydown="return ingresoDouble(window.event)"
											onchange="modificarSucursal(id)">
									</div>
								</div>
							""")))} else {null} ),format.raw/*45.9*/("""
							"""),_display_(if(mapDiccionario.get("nEmpresa").equals("HOHE"))/*46.58*/ {_display_(Seq[Any](format.raw/*46.60*/("""
								"""),format.raw/*47.9*/("""<div class="row">
									<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
										<b><font color="#008000">COD_UNIDNEGOCIO:</font></b>
									</div>
									<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
										<input type="hidden" name="ccost" value=""""),_display_(/*52.53*/sucursal/*52.61*/.getId()),format.raw/*52.69*/("""">
										<input type="text" class="form-control"
										id="ccost"
										maxlength="50"
										value=""""),_display_(/*56.19*/sucursal/*56.27*/.getCcost()),format.raw/*56.38*/(""""
										onkeydown="return sinReservados(window.event)"
										onchange="value = value.trim(); modificarHohe(id)">
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
										<b><font color="#008000">CEN_COS:</font></b>
									</div>
									<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
										<input type="hidden" name="cen_hohe" value=""""),_display_(/*66.56*/sucursal/*66.64*/.getId()),format.raw/*66.72*/("""">
										<input type="text" class="form-control"
										id="cen_hohe"
										maxlength="50"
										value=""""),_display_(/*70.19*/sucursal/*70.27*/.getCen_hohe()),format.raw/*70.41*/(""""
										onkeydown="return sinReservados(window.event)"
										onchange="value = value.trim(); modificarHohe(id)">
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
										<b><font color="#008000">BODEGA:</font></b>
									</div>
									<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
										<input type="hidden" name="bod_hohe" value=""""),_display_(/*80.56*/sucursal/*80.64*/.getId()),format.raw/*80.72*/("""">
										<input type="text" class="form-control"
										id="bod_hohe"
										maxlength="50"
										value=""""),_display_(/*84.19*/sucursal/*84.27*/.getBod_hohe()),format.raw/*84.41*/(""""
										onkeydown="return sinReservados(window.event)"
										onchange="value = value.trim(); modificarHohe(id)">
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
										<b><font color="#008000">UBICACION:</font></b>
									</div>
									<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
										<input type="hidden" name="ubi_hohe" value=""""),_display_(/*94.56*/sucursal/*94.64*/.getId()),format.raw/*94.72*/("""">
										<input type="text" class="form-control"
										id="ubi_hohe"
										maxlength="50"
										value=""""),_display_(/*98.19*/sucursal/*98.27*/.getUbi_hohe()),format.raw/*98.41*/(""""
										onkeydown="return sinReservados(window.event)"
										onchange="value = value.trim(); modificarHohe(id)">
									</div>
								</div>
								"cen_cos"
								"bodega"
								"ubicacion"
							""")))} else {null} ),format.raw/*106.9*/("""
						"""),format.raw/*107.7*/("""</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/routes2/sucursalAdministrar/';">
				</div>
			</div>
	</div>
	
""")))}),format.raw/*120.2*/("""




"""),format.raw/*125.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*126.31*/("""{"""),format.raw/*126.32*/("""
		  """),format.raw/*127.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*128.2*/("""}"""),format.raw/*128.3*/(""");
	
	const modificarSucursal = (campo) => """),format.raw/*130.39*/("""{"""),format.raw/*130.40*/("""
		"""),format.raw/*131.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_sucursal','"""),_display_(/*134.37*/sucursal/*134.45*/.getId()),format.raw/*134.53*/("""');
	  	formData.append('valor',valor);

        $.ajax("""),format.raw/*137.16*/("""{"""),format.raw/*137.17*/("""
            """),format.raw/*138.13*/("""url: "/routes2/modificaSucursalPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*145.36*/("""{"""),format.raw/*145.37*/("""
	     		"""),format.raw/*146.9*/("""if(respuesta=="existe")"""),format.raw/*146.32*/("""{"""),format.raw/*146.33*/("""
	     			"""),format.raw/*147.10*/("""alertify.alert('El nombre de sucursal ya existe, intente con otro', function () """),format.raw/*147.90*/("""{"""),format.raw/*147.91*/("""
	     				"""),format.raw/*148.11*/("""$("#nombre").val(""""),_display_(/*148.30*/sucursal/*148.38*/.getNombre()),format.raw/*148.50*/("""");
		     		"""),format.raw/*149.10*/("""}"""),format.raw/*149.11*/(""");
	     		"""),format.raw/*150.9*/("""}"""),format.raw/*150.10*/("""else if(respuesta=="error")"""),format.raw/*150.37*/("""{"""),format.raw/*150.38*/("""
	     			"""),format.raw/*151.10*/("""alertify.alert(msgError, function () """),format.raw/*151.47*/("""{"""),format.raw/*151.48*/("""
		     			"""),format.raw/*152.11*/("""location.href = "/";
		     		"""),format.raw/*153.10*/("""}"""),format.raw/*153.11*/(""");
	     		"""),format.raw/*154.9*/("""}"""),format.raw/*154.10*/("""
	     	"""),format.raw/*155.8*/("""}"""),format.raw/*155.9*/("""
        """),format.raw/*156.9*/("""}"""),format.raw/*156.10*/(""");
	"""),format.raw/*157.2*/("""}"""),format.raw/*157.3*/("""

	"""),format.raw/*159.2*/("""const modificarHohe = (campo) => """),format.raw/*159.35*/("""{"""),format.raw/*159.36*/("""
		"""),format.raw/*160.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
		formData.append('campo',campo);
		formData.append('id_sucursal','"""),_display_(/*163.35*/sucursal/*163.43*/.getId()),format.raw/*163.51*/("""');
		formData.append('valor',valor);

		$.ajax("""),format.raw/*166.10*/("""{"""),format.raw/*166.11*/("""
			"""),format.raw/*167.4*/("""url: "/routes2/modificaSucursalPorCampoAjax/",
			type: "POST",
			method: "POST",
			data: formData,
			cache: false,
			contentType: false,
			processData: false,
			success: function(respuesta)"""),format.raw/*174.32*/("""{"""),format.raw/*174.33*/("""
				"""),format.raw/*175.5*/("""if(respuesta=="existe")"""),format.raw/*175.28*/("""{"""),format.raw/*175.29*/("""
					"""),format.raw/*176.6*/("""alertify.alert('El nombre de sucursal ya existe, intente con otro', function () """),format.raw/*176.86*/("""{"""),format.raw/*176.87*/("""
						"""),format.raw/*177.7*/("""$("#nombre").val(""""),_display_(/*177.26*/sucursal/*177.34*/.getNombre()),format.raw/*177.46*/("""");
					"""),format.raw/*178.6*/("""}"""),format.raw/*178.7*/(""");
				"""),format.raw/*179.5*/("""}"""),format.raw/*179.6*/("""else if(respuesta=="error")"""),format.raw/*179.33*/("""{"""),format.raw/*179.34*/("""
					"""),format.raw/*180.6*/("""alertify.alert(msgError, function () """),format.raw/*180.43*/("""{"""),format.raw/*180.44*/("""
						"""),format.raw/*181.7*/("""location.href = "/";
					"""),format.raw/*182.6*/("""}"""),format.raw/*182.7*/(""");
				"""),format.raw/*183.5*/("""}"""),format.raw/*183.6*/("""
			"""),format.raw/*184.4*/("""}"""),format.raw/*184.5*/("""
		"""),format.raw/*185.3*/("""}"""),format.raw/*185.4*/(""");
	"""),format.raw/*186.2*/("""}"""),format.raw/*186.3*/("""
	
"""),format.raw/*188.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,sucursal:tables.Sucursal): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,sucursal)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Sucursal) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,sucursal) => apply(mapDiccionario,mapPermiso,userMnu,sucursal)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/sucursalModifica.scala.html
                  HASH: 5e530bc706ea4362d7daf8aa448830dd4a211eab
                  MATRIX: 1031->1|1248->125|1281->133|1297->141|1336->143|1364->146|1432->194|1460->196|1536->247|1609->300|1639->303|2135->772|2152->780|2181->788|2325->905|2342->913|2375->925|2671->1193|2712->1195|2748->1204|3110->1539|3162->1570|3191->1571|3519->1856|3604->1914|3644->1916|3680->1925|3977->2195|3994->2203|4023->2211|4167->2328|4184->2336|4216->2347|4668->2772|4685->2780|4714->2788|4861->2908|4878->2916|4913->2930|5364->3354|5381->3362|5410->3370|5557->3490|5574->3498|5609->3512|6063->3939|6080->3947|6109->3955|6256->4075|6273->4083|6308->4097|6567->4312|6602->4319|6993->4679|7026->4684|7117->4746|7147->4747|7180->4752|7274->4818|7303->4819|7375->4862|7405->4863|7436->4866|7601->5003|7619->5011|7649->5019|7734->5075|7764->5076|7806->5089|8084->5338|8114->5339|8151->5348|8203->5371|8233->5372|8272->5382|8381->5462|8411->5463|8451->5474|8498->5493|8516->5501|8550->5513|8592->5526|8622->5527|8661->5538|8691->5539|8747->5566|8777->5567|8816->5577|8882->5614|8912->5615|8952->5626|9011->5656|9041->5657|9080->5668|9110->5669|9146->5677|9175->5678|9212->5687|9242->5688|9274->5692|9303->5693|9334->5696|9396->5729|9426->5730|9457->5733|9618->5866|9636->5874|9666->5882|9743->5930|9773->5931|9805->5935|10030->6131|10060->6132|10093->6137|10145->6160|10175->6161|10209->6167|10318->6247|10348->6248|10383->6255|10430->6274|10448->6282|10482->6294|10519->6303|10548->6304|10583->6311|10612->6312|10668->6339|10698->6340|10732->6346|10798->6383|10828->6384|10863->6391|10917->6417|10946->6418|10981->6425|11010->6426|11042->6430|11071->6431|11102->6434|11131->6435|11163->6439|11192->6440|11223->6443
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|55->24|55->24|55->24|60->29|60->29|61->30|69->38|69->38|69->38|76->45|77->46|77->46|78->47|83->52|83->52|83->52|87->56|87->56|87->56|97->66|97->66|97->66|101->70|101->70|101->70|111->80|111->80|111->80|115->84|115->84|115->84|125->94|125->94|125->94|129->98|129->98|129->98|137->106|138->107|151->120|156->125|157->126|157->126|158->127|159->128|159->128|161->130|161->130|162->131|165->134|165->134|165->134|168->137|168->137|169->138|176->145|176->145|177->146|177->146|177->146|178->147|178->147|178->147|179->148|179->148|179->148|179->148|180->149|180->149|181->150|181->150|181->150|181->150|182->151|182->151|182->151|183->152|184->153|184->153|185->154|185->154|186->155|186->155|187->156|187->156|188->157|188->157|190->159|190->159|190->159|191->160|194->163|194->163|194->163|197->166|197->166|198->167|205->174|205->174|206->175|206->175|206->175|207->176|207->176|207->176|208->177|208->177|208->177|208->177|209->178|209->178|210->179|210->179|210->179|210->179|211->180|211->180|211->180|212->181|213->182|213->182|214->183|214->183|215->184|215->184|216->185|216->185|217->186|217->186|219->188
                  -- GENERATED --
              */
          