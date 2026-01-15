
package viewsMnuOdo.html

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

object servicioEquipos0 extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.EquipoServicio],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listEquipos: List[tables.EquipoServicio]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MANTENCION DE EQUIPOS PARA SERVICIOS", "(AGREGA/MODIFICA EQUIPO A "+mapDiccionario("BODEGA")+"/PROYECTO)")),format.raw/*9.139*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-10 col-md-10 col-lg-10">
				
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR> 
							<TH style= "text-align: center;">SUCURSAL</TH>
							<TH style= "text-align: center;">"""),_display_(/*17.42*/mapDiccionario("BODEGA")),format.raw/*17.66*/("""/PROYECTO</TH>
					        <TH style= "text-align: center;">CODIGO</TH>
							<TH style= "text-align: center;">EQUIPO</TH>
							<TH style= "text-align: center;">VIGENTE</TH>
					</TR>
					</thead>
					<tbody>
						"""),_display_(/*24.8*/for(lista1 <- listEquipos) yield /*24.34*/{_display_(Seq[Any](format.raw/*24.35*/("""
							"""),format.raw/*25.8*/("""<TR>
								<td style="text-align:left;">"""),_display_(/*26.39*/lista1/*26.45*/.getNameSucursal()),format.raw/*26.63*/("""</td>
								<td style="text-align:left;">"""),_display_(/*27.39*/lista1/*27.45*/.getBodega()),format.raw/*27.57*/("""</td>
								<td style="text-align:center;">"""),_display_(/*28.41*/lista1/*28.47*/.getCodigo()),format.raw/*28.59*/("""</td>
								<td style="text-align:left;">"""),_display_(/*29.39*/lista1/*29.45*/.getEquipo()),format.raw/*29.57*/("""</td>
								<td  style="text-align:center;">
									<input type="hidden" id="vigente_"""),_display_(/*31.44*/lista1/*31.50*/.getId_bodegaEmpresa()),_display_(/*31.73*/lista1/*31.79*/.getId_equipo()),format.raw/*31.94*/("""" value=""""),_display_(/*31.104*/lista1/*31.110*/.getVigente()),format.raw/*31.123*/("""">
									"""),_display_(if(lista1.getVigente()==1)/*32.37*/{_display_(Seq[Any](format.raw/*32.38*/("""
										"""),format.raw/*33.11*/("""<input type="checkbox" checked onchange="modificaVigente('"""),_display_(/*33.70*/lista1/*33.76*/.getId_bodegaEmpresa()),format.raw/*33.98*/("""','"""),_display_(/*33.102*/lista1/*33.108*/.getId_equipo()),format.raw/*33.123*/("""');">
									""")))}else/*34.15*/{_display_(Seq[Any](format.raw/*34.16*/("""
										"""),format.raw/*35.11*/("""<input type="checkbox" onchange="modificaVigente('"""),_display_(/*35.62*/lista1/*35.68*/.getId_bodegaEmpresa()),format.raw/*35.90*/("""','"""),_display_(/*35.94*/lista1/*35.100*/.getId_equipo()),format.raw/*35.115*/("""');">
									""")))}),format.raw/*36.11*/("""
								"""),format.raw/*37.9*/("""</td>
							</TR>
			 			""")))}),format.raw/*39.9*/("""
					"""),format.raw/*40.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="AGREGAR EQUIPO" class="btn btn-info btn-sm rounded-pill btn-block" onclick="location.href='/servicioEquipos1/'">
				</div>
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>
	
""")))}),format.raw/*56.2*/("""


"""),format.raw/*59.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*60.31*/("""{"""),format.raw/*60.32*/("""
		  """),format.raw/*61.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*61.36*/("""{"""),format.raw/*61.37*/("""
		    	"""),format.raw/*62.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 1, "asc" ],[ 2, "asc" ]],
		    	"language": """),format.raw/*65.20*/("""{"""),format.raw/*65.21*/("""
		        	"""),format.raw/*66.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*67.11*/("""}"""),format.raw/*67.12*/("""
		  """),format.raw/*68.5*/("""}"""),format.raw/*68.6*/(""" """),format.raw/*68.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*70.2*/("""}"""),format.raw/*70.3*/(""");
	
	const modificaVigente = (id_bodega, id_equipo) => """),format.raw/*72.52*/("""{"""),format.raw/*72.53*/("""
		"""),format.raw/*73.3*/("""let vigente = $("#vigente_"+id_bodega+""+id_equipo).val();
		if(vigente==1)"""),format.raw/*74.17*/("""{"""),format.raw/*74.18*/("""
			"""),format.raw/*75.4*/("""modificaVigencia(id_bodega, id_equipo, 0);
			$("#vigente_"+id_bodega+""+id_equipo).val(0);
		"""),format.raw/*77.3*/("""}"""),format.raw/*77.4*/("""else"""),format.raw/*77.8*/("""{"""),format.raw/*77.9*/("""
			"""),format.raw/*78.4*/("""modificaVigencia(id_bodega, id_equipo, 1);
			$("#vigente_"+id_bodega+""+id_equipo).val(1);
		"""),format.raw/*80.3*/("""}"""),format.raw/*80.4*/("""
	"""),format.raw/*81.2*/("""}"""),format.raw/*81.3*/("""
	
	"""),format.raw/*83.2*/("""const modificaVigencia = (id_bodega, id_equipo, valor) => """),format.raw/*83.60*/("""{"""),format.raw/*83.61*/("""
		"""),format.raw/*84.3*/("""let formData = new FormData();
		formData.append('id_bodega',id_bodega);
		formData.append('id_equipo',id_equipo);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*88.10*/("""{"""),format.raw/*88.11*/("""
            """),format.raw/*89.13*/("""url: "/modVigEquipoServicioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*96.36*/("""{"""),format.raw/*96.37*/("""
	     		"""),format.raw/*97.9*/("""if(respuesta=="error")"""),format.raw/*97.31*/("""{"""),format.raw/*97.32*/("""
	     			"""),format.raw/*98.10*/("""alertify.alert(msgError, function () """),format.raw/*98.47*/("""{"""),format.raw/*98.48*/("""
		     			"""),format.raw/*99.11*/("""location.href = "/";
		     		"""),format.raw/*100.10*/("""}"""),format.raw/*100.11*/(""");
	     		"""),format.raw/*101.9*/("""}"""),format.raw/*101.10*/("""
	     	"""),format.raw/*102.8*/("""}"""),format.raw/*102.9*/("""
        """),format.raw/*103.9*/("""}"""),format.raw/*103.10*/(""");
	"""),format.raw/*104.2*/("""}"""),format.raw/*104.3*/("""
	
"""),format.raw/*106.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listEquipos:List[tables.EquipoServicio]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listEquipos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.EquipoServicio]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listEquipos) => apply(mapDiccionario,mapPermiso,userMnu,listEquipos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuOdo/servicioEquipos0.scala.html
                  HASH: 5b048559616ea0884fbbf2ff691a2a0b9affa8c6
                  MATRIX: 1039->1|1271->140|1304->148|1320->156|1359->158|1387->161|1455->209|1483->211|1559->262|1715->397|1745->400|2143->771|2188->795|2437->1018|2479->1044|2518->1045|2553->1053|2623->1096|2638->1102|2677->1120|2748->1164|2763->1170|2796->1182|2869->1228|2884->1234|2917->1246|2988->1290|3003->1296|3036->1308|3153->1398|3168->1404|3211->1427|3226->1433|3262->1448|3300->1458|3316->1464|3351->1477|3417->1516|3456->1517|3495->1528|3581->1587|3596->1593|3639->1615|3671->1619|3687->1625|3724->1640|3763->1660|3802->1661|3841->1672|3919->1723|3934->1729|3977->1751|4008->1755|4024->1761|4061->1776|4108->1792|4144->1801|4201->1828|4234->1834|4814->2384|4844->2387|4934->2449|4963->2450|4995->2455|5054->2486|5083->2487|5118->2495|5305->2654|5334->2655|5374->2667|5480->2745|5509->2746|5541->2751|5569->2752|5597->2753|5697->2826|5725->2827|5809->2883|5838->2884|5868->2887|5971->2962|6000->2963|6031->2967|6152->3061|6180->3062|6211->3066|6239->3067|6270->3071|6391->3165|6419->3166|6448->3168|6476->3169|6507->3173|6593->3231|6622->3232|6652->3235|6838->3393|6867->3394|6908->3407|7173->3644|7202->3645|7238->3654|7288->3676|7317->3677|7355->3687|7420->3724|7449->3725|7488->3736|7547->3766|7577->3767|7616->3778|7646->3779|7682->3787|7711->3788|7748->3797|7778->3798|7810->3802|7839->3803|7870->3806
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|48->17|48->17|55->24|55->24|55->24|56->25|57->26|57->26|57->26|58->27|58->27|58->27|59->28|59->28|59->28|60->29|60->29|60->29|62->31|62->31|62->31|62->31|62->31|62->31|62->31|62->31|63->32|63->32|64->33|64->33|64->33|64->33|64->33|64->33|64->33|65->34|65->34|66->35|66->35|66->35|66->35|66->35|66->35|66->35|67->36|68->37|70->39|71->40|87->56|90->59|91->60|91->60|92->61|92->61|92->61|93->62|96->65|96->65|97->66|98->67|98->67|99->68|99->68|99->68|101->70|101->70|103->72|103->72|104->73|105->74|105->74|106->75|108->77|108->77|108->77|108->77|109->78|111->80|111->80|112->81|112->81|114->83|114->83|114->83|115->84|119->88|119->88|120->89|127->96|127->96|128->97|128->97|128->97|129->98|129->98|129->98|130->99|131->100|131->100|132->101|132->101|133->102|133->102|134->103|134->103|135->104|135->104|137->106
                  -- GENERATED --
              */
          