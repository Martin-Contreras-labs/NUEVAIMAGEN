
package viewsMnuReportes.html

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

object estadosMantCobraArriendo1 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.CobraArriendoEstados],tables.BodegaEmpresa,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
lista: List[tables.CobraArriendoEstados], bodegaEmpresa: tables.BodegaEmpresa):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "ESTADO EQUIPO COBRAR "+mapDiccionario("ARRIENDO")+" POR DAÑOS: MODIFICAR", bodegaEmpresa.getNameSucursal +" - "+ bodegaEmpresa.getNombre())),format.raw/*9.172*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
					        <TH>GRUPO</TH>
					        <TH>COTI</TH>
							<TH>CODIGO</TH>
					        <TH>EQUIPO</TH>
							<TH>FECHA</TH>
					        <TH>NRO MOVIM</TH>
							<TH>REF CLIENTE</TH>
							<TH>COBRA """),_display_(/*22.19*/mapDiccionario("ARR")),format.raw/*22.40*/("""</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*26.8*/for(lista1 <- lista) yield /*26.28*/{_display_(Seq[Any](format.raw/*26.29*/("""
							"""),format.raw/*27.8*/("""<TR>
								<td  style="text-align:left;">"""),_display_(/*28.40*/lista1/*28.46*/.getNombreGrupo),format.raw/*28.61*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*29.42*/lista1/*29.48*/.getNroCoti),format.raw/*29.59*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*30.40*/lista1/*30.46*/.getCodigo),format.raw/*30.56*/("""</td>
								<td  style="text-align:left;">"""),_display_(/*31.40*/lista1/*31.46*/.getNombreEquipo),format.raw/*31.62*/("""</td>
								<td  style="text-align:center; min-width: 80px; max-width: 80px;">
									<div style="display: none">"""),_display_(/*33.38*/lista1/*33.44*/.getFecha),format.raw/*33.53*/("""</div>
									"""),_display_(/*34.11*/utilities/*34.20*/.Fechas.DDMMAA(lista1.getFecha)),format.raw/*34.51*/("""
								"""),format.raw/*35.9*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*36.42*/lista1/*36.48*/.getNumGuia),format.raw/*36.59*/("""</td>
								<td  style="text-align:center;">"""),_display_(/*37.42*/lista1/*37.48*/.getNumGuiaCliente),format.raw/*37.66*/("""</td>
								<td  style="text-align:center;">
									<div style="display: none">"""),_display_(/*39.38*/lista1/*39.44*/.getCobraArriendo),format.raw/*39.61*/("""</div>
									<input type="hidden" id="select_"""),_display_(/*40.43*/lista1/*40.49*/.getId_movimiento),format.raw/*40.66*/("""" value=""""),_display_(/*40.76*/lista1/*40.82*/.getCobraArriendo),format.raw/*40.99*/("""">
									"""),_display_(if(lista1.getCobraArriendo==1)/*41.41*/{_display_(Seq[Any](format.raw/*41.42*/("""
										"""),format.raw/*42.11*/("""<input type="checkbox" checked onchange="modificaSelect('"""),_display_(/*42.69*/lista1/*42.75*/.getId_movimiento),format.raw/*42.92*/("""');">
									""")))}else/*43.15*/{_display_(Seq[Any](format.raw/*43.16*/("""
										"""),format.raw/*44.11*/("""<input type="checkbox" onchange="modificaSelect('"""),_display_(/*44.61*/lista1/*44.67*/.getId_movimiento),format.raw/*44.84*/("""');">
									""")))}),format.raw/*45.11*/("""
								"""),format.raw/*46.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*48.9*/("""
					"""),format.raw/*49.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-light btn-sm rounded-pill btn-block" onclick="location.href='/routes3/estadosMantCobraArriendo0/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*62.2*/("""




"""),format.raw/*67.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*68.31*/("""{"""),format.raw/*68.32*/("""
		  """),format.raw/*69.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*69.36*/("""{"""),format.raw/*69.37*/("""
		    	"""),format.raw/*70.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*72.20*/("""{"""),format.raw/*72.21*/("""
		        	"""),format.raw/*73.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*74.11*/("""}"""),format.raw/*74.12*/("""
		  """),format.raw/*75.5*/("""}"""),format.raw/*75.6*/(""" """),format.raw/*75.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*77.2*/("""}"""),format.raw/*77.3*/(""");
	
	const modificaSelect = (id_movimiento) => """),format.raw/*79.44*/("""{"""),format.raw/*79.45*/("""
		"""),format.raw/*80.3*/("""let select = $("#select_"+id_movimiento).val();
		if(select == 1)"""),format.raw/*81.18*/("""{"""),format.raw/*81.19*/("""
			"""),format.raw/*82.4*/("""modificaSelect2(id_movimiento, 0);
			$("#select_"+id_movimiento).val(0);
		"""),format.raw/*84.3*/("""}"""),format.raw/*84.4*/("""else"""),format.raw/*84.8*/("""{"""),format.raw/*84.9*/("""
			"""),format.raw/*85.4*/("""modificaSelect2(id_movimiento, 1);
			$("#select_"+id_movimiento).val(1);
		"""),format.raw/*87.3*/("""}"""),format.raw/*87.4*/("""
	"""),format.raw/*88.2*/("""}"""),format.raw/*88.3*/("""
	
	"""),format.raw/*90.2*/("""const modificaSelect2 = (id_movimiento, valor) => """),format.raw/*90.52*/("""{"""),format.raw/*90.53*/("""
		"""),format.raw/*91.3*/("""let formData = new FormData();
		formData.append('id_movimiento',id_movimiento);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*94.10*/("""{"""),format.raw/*94.11*/("""
            """),format.raw/*95.13*/("""url: "/routes3/modCobraArriendoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*102.36*/("""{"""),format.raw/*102.37*/("""
	     		"""),format.raw/*103.9*/("""if(respuesta=="error")"""),format.raw/*103.31*/("""{"""),format.raw/*103.32*/("""
	     			"""),format.raw/*104.10*/("""alertify.alert(msgError, function () """),format.raw/*104.47*/("""{"""),format.raw/*104.48*/("""
		     			"""),format.raw/*105.11*/("""location.href = "/";
		     		"""),format.raw/*106.10*/("""}"""),format.raw/*106.11*/(""");
	     		"""),format.raw/*107.9*/("""}"""),format.raw/*107.10*/("""
	     	"""),format.raw/*108.8*/("""}"""),format.raw/*108.9*/("""
        """),format.raw/*109.9*/("""}"""),format.raw/*109.10*/(""");
	"""),format.raw/*110.2*/("""}"""),format.raw/*110.3*/("""

"""),format.raw/*112.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[tables.CobraArriendoEstados],bodegaEmpresa:tables.BodegaEmpresa): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista,bodegaEmpresa)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.CobraArriendoEstados],tables.BodegaEmpresa) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista,bodegaEmpresa) => apply(mapDiccionario,mapPermiso,userMnu,lista,bodegaEmpresa)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/estadosMantCobraArriendo1.scala.html
                  HASH: f93261e2e7e79534879222201ba4605012ad2232
                  MATRIX: 1080->1|1349->177|1382->185|1398->193|1437->195|1465->198|1533->246|1561->248|1637->299|1826->467|1856->470|2360->947|2402->968|2480->1020|2516->1040|2555->1041|2590->1049|2661->1093|2676->1099|2712->1114|2786->1161|2801->1167|2833->1178|2905->1223|2920->1229|2951->1239|3023->1284|3038->1290|3075->1306|3220->1424|3235->1430|3265->1439|3309->1456|3327->1465|3379->1496|3415->1505|3489->1552|3504->1558|3536->1569|3610->1616|3625->1622|3664->1640|3775->1724|3790->1730|3828->1747|3904->1796|3919->1802|3957->1819|3994->1829|4009->1835|4047->1852|4117->1895|4156->1896|4195->1907|4280->1965|4295->1971|4333->1988|4372->2008|4411->2009|4450->2020|4527->2070|4542->2076|4580->2093|4627->2109|4663->2118|4720->2145|4753->2151|5147->2515|5179->2520|5269->2582|5298->2583|5330->2588|5389->2619|5418->2620|5453->2628|5595->2742|5624->2743|5664->2755|5770->2833|5799->2834|5831->2839|5859->2840|5887->2841|5987->2914|6015->2915|6091->2963|6120->2964|6150->2967|6243->3032|6272->3033|6303->3037|6406->3113|6434->3114|6465->3118|6493->3119|6524->3123|6627->3199|6655->3200|6684->3202|6712->3203|6743->3207|6821->3257|6850->3258|6880->3261|7032->3385|7061->3386|7102->3399|7372->3640|7402->3641|7439->3650|7490->3672|7520->3673|7559->3683|7625->3720|7655->3721|7695->3732|7754->3762|7784->3763|7823->3774|7853->3775|7889->3783|7918->3784|7955->3793|7985->3794|8017->3798|8046->3799|8076->3801
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|53->22|53->22|57->26|57->26|57->26|58->27|59->28|59->28|59->28|60->29|60->29|60->29|61->30|61->30|61->30|62->31|62->31|62->31|64->33|64->33|64->33|65->34|65->34|65->34|66->35|67->36|67->36|67->36|68->37|68->37|68->37|70->39|70->39|70->39|71->40|71->40|71->40|71->40|71->40|71->40|72->41|72->41|73->42|73->42|73->42|73->42|74->43|74->43|75->44|75->44|75->44|75->44|76->45|77->46|79->48|80->49|93->62|98->67|99->68|99->68|100->69|100->69|100->69|101->70|103->72|103->72|104->73|105->74|105->74|106->75|106->75|106->75|108->77|108->77|110->79|110->79|111->80|112->81|112->81|113->82|115->84|115->84|115->84|115->84|116->85|118->87|118->87|119->88|119->88|121->90|121->90|121->90|122->91|125->94|125->94|126->95|133->102|133->102|134->103|134->103|134->103|135->104|135->104|135->104|136->105|137->106|137->106|138->107|138->107|139->108|139->108|140->109|140->109|141->110|141->110|143->112
                  -- GENERATED --
              */
          