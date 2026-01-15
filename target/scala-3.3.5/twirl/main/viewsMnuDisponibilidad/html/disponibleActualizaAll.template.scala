
package viewsMnuDisponibilidad.html

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

object disponibleActualizaAll extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cronograma],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listaDisponibilidad: List[tables.Cronograma]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

	"""),_display_(/*7.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.51*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "DISPONIBILIDAD DE EQUIPOS EN "+mapDiccionario.get("BODEGAS")+"/PROYECTOS","")),format.raw/*9.110*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<thead style="background-color: #eeeeee">
						<TR>
							<TH>SUCURSAL</TH>
							<TH>"""),_display_(/*16.13*/mapDiccionario/*16.27*/.get("BODEGA")),format.raw/*16.41*/("""/PROYECTO</TH>
							<TH>GRUPO/FAMILIA</TH>
							<TH>CODIGO</TH>
							<TH>EQUIPO</TH>
							<TH>UN</TH>
							<TH>CANT</TH>
							<TH>EN PROYECTO DESDE</TH>
							<TH>ULTIMA MODIFICACION</TH>
							<TH>DISPONIBLE A PARTIR DE</TH>
						</TR>
					</thead>
					<tbody>
						"""),_display_(/*28.8*/for(lista1 <- listaDisponibilidad) yield /*28.42*/{_display_(Seq[Any](format.raw/*28.43*/("""
							"""),format.raw/*29.8*/("""<TR>
								<TD style= "text-align: left;">"""),_display_(/*30.41*/lista1/*30.47*/.nameSucursal),format.raw/*30.60*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*31.41*/lista1/*31.47*/.nombreBodega),format.raw/*31.60*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*32.41*/lista1/*32.47*/.nombreGrupo),format.raw/*32.59*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*33.41*/lista1/*33.47*/.codEquipo),format.raw/*33.57*/("""</TD>
								<TD style= "text-align: left;">"""),_display_(/*34.41*/lista1/*34.47*/.nombreEquipo),format.raw/*34.60*/("""</TD>
								<TD style= "text-align: center;">"""),_display_(/*35.43*/lista1/*35.49*/.unidad),format.raw/*35.56*/("""</TD>
								<TD style= "text-align: right;">"""),_display_(/*36.42*/lista1/*36.48*/.cantidad),format.raw/*36.57*/("""</TD>
								<TD style= "text-align: center;">"""),_display_(/*37.43*/lista1/*37.49*/.fechaDesde),format.raw/*37.60*/("""</TD>
								
								<TD style= "text-align: center;">
									<div id="hoy"""),_display_(/*40.23*/lista1/*40.29*/.id_movimiento),format.raw/*40.43*/("""">"""),_display_(/*40.46*/lista1/*40.52*/.fechaActualizacion),format.raw/*40.71*/("""</div>
								</TD>
								
								<TD style= "text-align: center;">
									
									"""),_display_(if(lista1.fechaDisponible.equals("No Informada"))/*45.60*/{_display_(Seq[Any](format.raw/*45.61*/("""
										"""),format.raw/*46.11*/("""<div id=""""),_display_(/*46.21*/lista1/*46.27*/.id_movimiento),format.raw/*46.41*/(""" """),_display_(/*46.43*/lista1/*46.49*/.id_bodegaEmpresa),format.raw/*46.66*/("""">
											<input type="text" class="form-control center"
												value=""""),_display_(/*48.21*/lista1/*48.27*/.fechaDisponible),format.raw/*48.43*/("""" 
												onclick="noInforma('"""),_display_(/*49.34*/lista1/*49.40*/.id_movimiento),format.raw/*49.54*/("""','"""),_display_(/*49.58*/lista1/*49.64*/.id_bodegaEmpresa),format.raw/*49.81*/("""','"""),_display_(/*49.85*/lista1/*49.91*/.id_equipo),format.raw/*49.101*/("""')">
										</div>
									""")))}else/*51.16*/{_display_(Seq[Any](format.raw/*51.17*/("""
										"""),format.raw/*52.11*/("""<input type="date" class="form-control center"
											onfocus="fechaAux = value"
								  			onblur="if(!limitaFecha(value, 720, 1440)) """),format.raw/*54.57*/("""{"""),format.raw/*54.58*/("""
														"""),format.raw/*55.15*/("""value='"""),_display_(/*55.23*/utilities/*55.32*/.Fechas.AAMMDD(lista1.fechaDisponible)),format.raw/*55.70*/("""';
													"""),format.raw/*56.14*/("""}"""),format.raw/*56.15*/(""" """),format.raw/*56.16*/("""else if(fechaAux != value)"""),format.raw/*56.42*/("""{"""),format.raw/*56.43*/("""
														"""),format.raw/*57.15*/("""actualizaFecha('"""),_display_(/*57.32*/lista1/*57.38*/.id_movimiento),format.raw/*57.52*/("""','"""),_display_(/*57.56*/lista1/*57.62*/.id_bodegaEmpresa),format.raw/*57.79*/("""','"""),_display_(/*57.83*/lista1/*57.89*/.id_equipo),format.raw/*57.99*/("""',value)
													"""),format.raw/*58.14*/("""}"""),format.raw/*58.15*/(""""
								  			value=""""),_display_(/*59.22*/utilities/*59.31*/.Fechas.AAMMDD(lista1.fechaDisponible)),format.raw/*59.69*/("""">
									""")))}),format.raw/*60.11*/("""
								"""),format.raw/*61.9*/("""</TD>
							</TR>
			 			""")))}),format.raw/*63.9*/("""
					"""),format.raw/*64.6*/("""</tbody>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/home/';">
				</div>
			</div>
		</div>
	</div>

""")))}),format.raw/*77.2*/("""


"""),format.raw/*80.1*/("""<script type="text/javascript">
	let fechaAux = "";
	$(document).ready(function() """),format.raw/*82.31*/("""{"""),format.raw/*82.32*/("""
		  """),format.raw/*83.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*83.36*/("""{"""),format.raw/*83.37*/("""
		    	"""),format.raw/*84.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"order": [[ 2, "asc" ],[ 4, "asc" ],[ 1, "asc" ]],
		    	"language": """),format.raw/*87.20*/("""{"""),format.raw/*87.21*/("""
		        	"""),format.raw/*88.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*89.11*/("""}"""),format.raw/*89.12*/("""
		  """),format.raw/*90.5*/("""}"""),format.raw/*90.6*/(""" """),format.raw/*90.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*92.2*/("""}"""),format.raw/*92.3*/(""");
	
	const actualizaFecha = (id_movimiento,id_bodega,id_equipo,fecha) =>"""),format.raw/*94.69*/("""{"""),format.raw/*94.70*/("""
		"""),format.raw/*95.3*/("""var formData = new FormData();
	  	formData.append('id_movimiento',id_movimiento);
		formData.append('id_bodega',id_bodega);
		formData.append('id_equipo',id_equipo);
		formData.append('fecha',fecha);
        $.ajax("""),format.raw/*100.16*/("""{"""),format.raw/*100.17*/("""
            """),format.raw/*101.13*/("""url: "/cambiaFechaDisponibleAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(rs)"""),format.raw/*108.29*/("""{"""),format.raw/*108.30*/("""
				"""),format.raw/*109.5*/("""document.getElementById("hoy"+id_movimiento).innerHTML = rs;
	     	"""),format.raw/*110.8*/("""}"""),format.raw/*110.9*/("""
        """),format.raw/*111.9*/("""}"""),format.raw/*111.10*/(""");
	"""),format.raw/*112.2*/("""}"""),format.raw/*112.3*/("""
	
	"""),format.raw/*114.2*/("""const noInforma = (id_movimiento,id_bodega,id_equipo) =>"""),format.raw/*114.58*/("""{"""),format.raw/*114.59*/("""
		"""),format.raw/*115.3*/("""document.getElementById(id_movimiento+" "+id_bodega).innerHTML =
			"<input type='date' class='form-control center' " +
				" onfocus='fechaAux = value' "+
				" onblur='if(!limitaFecha(value, 720, 1440)) """),format.raw/*118.50*/("""{"""),format.raw/*118.51*/(""" """),format.raw/*118.52*/("""value=\"\";"""),format.raw/*118.63*/("""}"""),format.raw/*118.64*/(""" """),format.raw/*118.65*/("""else if(fechaAux != value)"""),format.raw/*118.91*/("""{"""),format.raw/*118.92*/("""actualizaFecha("+id_movimiento+","+id_bodega+","+id_equipo+",value);"""),format.raw/*118.160*/("""}"""),format.raw/*118.161*/("""'>";
	"""),format.raw/*119.2*/("""}"""),format.raw/*119.3*/("""
		
	
	
	
"""),format.raw/*124.1*/("""</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listaDisponibilidad:List[tables.Cronograma]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listaDisponibilidad)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.Cronograma]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listaDisponibilidad) => apply(mapDiccionario,mapPermiso,userMnu,listaDisponibilidad)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuDisponibilidad/disponibleActualizaAll.scala.html
                  HASH: 67f293e0486aa3d348c8b77bb063aa59854c3a0f
                  MATRIX: 1052->1|1288->144|1321->152|1337->160|1376->162|1405->166|1473->214|1501->216|1577->267|1704->373|1734->376|2068->683|2091->697|2126->711|2438->997|2488->1031|2527->1032|2562->1040|2634->1085|2649->1091|2683->1104|2756->1150|2771->1156|2805->1169|2878->1215|2893->1221|2926->1233|2999->1279|3014->1285|3045->1295|3118->1341|3133->1347|3167->1360|3242->1408|3257->1414|3285->1421|3359->1468|3374->1474|3404->1483|3479->1531|3494->1537|3526->1548|3632->1627|3647->1633|3682->1647|3712->1650|3727->1656|3767->1675|3935->1816|3974->1817|4013->1828|4050->1838|4065->1844|4100->1858|4129->1860|4144->1866|4182->1883|4290->1964|4305->1970|4342->1986|4405->2022|4420->2028|4455->2042|4486->2046|4501->2052|4539->2069|4570->2073|4585->2079|4617->2089|4672->2126|4711->2127|4750->2138|4919->2279|4948->2280|4991->2295|5026->2303|5044->2312|5103->2350|5147->2366|5176->2367|5205->2368|5259->2394|5288->2395|5331->2410|5375->2427|5390->2433|5425->2447|5456->2451|5471->2457|5509->2474|5540->2478|5555->2484|5586->2494|5636->2516|5665->2517|5715->2540|5733->2549|5792->2587|5836->2600|5872->2609|5929->2636|5962->2642|6329->2979|6359->2982|6469->3064|6498->3065|6530->3070|6589->3101|6618->3102|6653->3110|6853->3282|6882->3283|6922->3295|7028->3373|7057->3374|7089->3379|7117->3380|7145->3381|7245->3454|7273->3455|7374->3528|7403->3529|7433->3532|7678->3748|7708->3749|7750->3762|8010->3993|8040->3994|8073->3999|8169->4067|8198->4068|8235->4077|8265->4078|8297->4082|8326->4083|8358->4087|8443->4143|8473->4144|8504->4147|8738->4352|8768->4353|8798->4354|8838->4365|8868->4366|8898->4367|8953->4393|8983->4394|9081->4462|9112->4463|9146->4469|9175->4470|9213->4480
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|47->16|47->16|47->16|59->28|59->28|59->28|60->29|61->30|61->30|61->30|62->31|62->31|62->31|63->32|63->32|63->32|64->33|64->33|64->33|65->34|65->34|65->34|66->35|66->35|66->35|67->36|67->36|67->36|68->37|68->37|68->37|71->40|71->40|71->40|71->40|71->40|71->40|76->45|76->45|77->46|77->46|77->46|77->46|77->46|77->46|77->46|79->48|79->48|79->48|80->49|80->49|80->49|80->49|80->49|80->49|80->49|80->49|80->49|82->51|82->51|83->52|85->54|85->54|86->55|86->55|86->55|86->55|87->56|87->56|87->56|87->56|87->56|88->57|88->57|88->57|88->57|88->57|88->57|88->57|88->57|88->57|88->57|89->58|89->58|90->59|90->59|90->59|91->60|92->61|94->63|95->64|108->77|111->80|113->82|113->82|114->83|114->83|114->83|115->84|118->87|118->87|119->88|120->89|120->89|121->90|121->90|121->90|123->92|123->92|125->94|125->94|126->95|131->100|131->100|132->101|139->108|139->108|140->109|141->110|141->110|142->111|142->111|143->112|143->112|145->114|145->114|145->114|146->115|149->118|149->118|149->118|149->118|149->118|149->118|149->118|149->118|149->118|149->118|150->119|150->119|155->124
                  -- GENERATED --
              */
          