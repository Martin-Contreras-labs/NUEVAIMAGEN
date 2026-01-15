
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

object reporteEstadosTodosDetalle extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,List[tables.TipoEstado],Map[Long,List[List[String]]],tables.BodegaEmpresa,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
listTipoEstados: List[tables.TipoEstado], mapDatos: Map[Long,List[List[String]]], bodega: tables.BodegaEmpresa):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalEquipoDescripcion()),format.raw/*8.27*/("""
	"""),_display_(/*9.3*/modalVerCotizacion()),format.raw/*9.23*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "DETALLE POR ESTADOS DE EQUIPOS (DEVOLUCIONES)", mapDiccionario.get("BODEGA")+"/PROYECTO: "+bodega.getNombre().toUpperCase)),format.raw/*12.155*/("""

				"""),format.raw/*14.5*/("""<div class="noprint">
					<table class="table table-sm table-condensed table-fluid">
						<tr>
							<td>
								<div align="center">
									<button type="button"  class="btn btn-sm btn-success" onclick="document.getElementById('excel').submit()">
										Exportar a Excel
									</button>
									<button type="button" class="btn btn-sm btn-success" tabindex="-1" onclick="window.print(); return false;" >
										Imprimir
									</button>
									
									<button type="button"  class="btn btn-sm btn-success" 
										onclick="history.go(-1);return false;">
										Volver
									</button>
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<div class="table-responsive">
					<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
						"""),_display_(/*38.8*/for(tipoEstado <- listTipoEstados) yield /*38.42*/{_display_(Seq[Any](format.raw/*38.43*/("""
							"""),format.raw/*39.8*/("""<div class="container-fluid">
								<h5><b><font color="#008000">"""),_display_(/*40.39*/tipoEstado/*40.49*/.nombre.toUpperCase()),format.raw/*40.70*/("""</font></b></h5>
								<div class="table-responsive">
								  	<table id=""""),_display_(/*42.24*/tipoEstado/*42.34*/.nombre),format.raw/*42.41*/("""" class="table table-sm table-hover table-bordered table-condensed table-fluid">
										"""),_display_(if( mapDatos.get(tipoEstado.getId()) != null)/*43.57*/{_display_(Seq[Any](format.raw/*43.58*/("""
											"""),_display_(/*44.13*/for( (datos,nivel1) <- mapDatos.get(tipoEstado.getId()).zipWithIndex ) yield /*44.83*/ {_display_(Seq[Any](format.raw/*44.85*/("""
												"""),format.raw/*45.13*/("""<TR>
													"""),_display_(/*46.15*/for((nivel2,index2) <- datos.zipWithIndex) yield /*46.57*/ {_display_(Seq[Any](format.raw/*46.59*/("""
														"""),_display_(if(index2 == 1)/*47.31*/{_display_(Seq[Any](format.raw/*47.32*/("""
															"""),_display_(if(nivel2.equals("0") || nivel2.equals(""))/*48.60*/{_display_(Seq[Any](format.raw/*48.61*/("""
																"""),format.raw/*49.17*/("""<td style="text-align: center;">--</td>
															""")))}else/*50.21*/{_display_(Seq[Any](format.raw/*50.22*/("""
																"""),format.raw/*51.17*/("""<td style="text-align: center;"><a href="#" onclick="buscaCotizacion('"""),_display_(/*51.88*/nivel2),format.raw/*51.94*/("""')">"""),_display_(/*51.99*/nivel2),format.raw/*51.105*/("""</td>
															""")))}),format.raw/*52.17*/("""
														""")))}else/*53.20*/{_display_(Seq[Any](format.raw/*53.21*/("""
															"""),_display_(if(index2 == 2 || index2 ==3)/*54.46*/{_display_(Seq[Any](format.raw/*54.47*/("""
																"""),_display_(if(nivel1 > 3)/*55.32*/{_display_(Seq[Any](format.raw/*55.33*/("""
																	"""),format.raw/*56.18*/("""<td><a href="#" onclick="buscaEquipo('"""),_display_(/*56.57*/datos/*56.62*/.get(2)),format.raw/*56.69*/("""');">"""),_display_(/*56.75*/nivel2),format.raw/*56.81*/("""</a></td>
																""")))}else/*57.22*/{_display_(Seq[Any](format.raw/*57.23*/("""
																	"""),format.raw/*58.18*/("""<td>"""),_display_(/*58.23*/nivel2),format.raw/*58.29*/("""</td>
																""")))}),format.raw/*59.18*/("""
															""")))}else/*60.21*/{_display_(Seq[Any](format.raw/*60.22*/("""
																"""),format.raw/*61.17*/("""<td>"""),_display_(/*61.22*/nivel2),format.raw/*61.28*/("""</td>
															""")))}),format.raw/*62.17*/("""
														""")))}),format.raw/*63.16*/("""
													""")))}),format.raw/*64.15*/("""
												"""),format.raw/*65.13*/("""</TR>
											 """)))}),format.raw/*66.14*/("""
										""")))} else {null} ),format.raw/*67.12*/("""
							
										
									"""),format.raw/*70.10*/("""</table>
								</div>
							</div>
						""")))}),format.raw/*73.8*/("""
					"""),format.raw/*74.6*/("""</table>
				</div>
			</div>

	
	<form id="excel" class="formulario" method="post" action="/reporteEstadosTodosDetalleExcel/">
		<input type="hidden" name="id_bodegaEmpresa" value=""""),_display_(/*80.56*/bodega/*80.62*/.getId()),format.raw/*80.70*/("""">
	</form>


""")))}),format.raw/*84.2*/("""


"""),format.raw/*87.1*/("""<script type="text/javascript">

	$(document).ready(function() """),format.raw/*89.31*/("""{"""),format.raw/*89.32*/("""
		
		"""),_display_(/*91.4*/for(tipoEstado <- listTipoEstados) yield /*91.38*/{_display_(Seq[Any](format.raw/*91.39*/("""
			"""),format.raw/*92.4*/("""var tabla = document.getElementById(""""),_display_(/*92.42*/tipoEstado/*92.52*/.nombre),format.raw/*92.59*/("""");
			for (var i = 0; i < tabla.rows[0].cells.length; i++) """),format.raw/*93.57*/("""{"""),format.raw/*93.58*/(""" 	
				"""),format.raw/*94.5*/("""tabla.rows[0].cells[i].style.backgroundColor="#F8E6E0" ;
				tabla.rows[1].cells[i].style.backgroundColor="#F8E6E0" ;
				tabla.rows[2].cells[i].style.backgroundColor="#F8E6E0" ;
				tabla.rows[3].cells[i].style.backgroundColor="#F8E6E0" ;
				tabla.rows[4].cells[i].style.backgroundColor="#F8E6E0" ;
				
				if(i>7)"""),format.raw/*100.12*/("""{"""),format.raw/*100.13*/("""
					"""),format.raw/*101.6*/("""tabla.rows[0].cells[i].style.textAlign="center" ;
					tabla.rows[1].cells[i].style.textAlign="center" ;
					tabla.rows[2].cells[i].style.textAlign="center" ;
					tabla.rows[3].cells[i].style.textAlign="center" ;
					tabla.rows[4].cells[i].style.textAlign="center" ;
				"""),format.raw/*106.5*/("""}"""),format.raw/*106.6*/("""	
			"""),format.raw/*107.4*/("""}"""),format.raw/*107.5*/("""
			"""),format.raw/*108.4*/("""for (var i = 5; i < tabla.rows.length; i++) """),format.raw/*108.48*/("""{"""),format.raw/*108.49*/("""
				"""),format.raw/*109.5*/("""for (var j = 4; j < tabla.rows[i].cells.length; j++) """),format.raw/*109.58*/("""{"""),format.raw/*109.59*/("""
					"""),format.raw/*110.6*/("""if(j==4 || j>7)"""),format.raw/*110.21*/("""{"""),format.raw/*110.22*/("""
						"""),format.raw/*111.7*/("""tabla.rows[i].cells[j].style.textAlign="center" ;
					"""),format.raw/*112.6*/("""}"""),format.raw/*112.7*/("""else"""),format.raw/*112.11*/("""{"""),format.raw/*112.12*/("""
						"""),format.raw/*113.7*/("""tabla.rows[i].cells[j].style.textAlign="right" ;
					"""),format.raw/*114.6*/("""}"""),format.raw/*114.7*/("""
				"""),format.raw/*115.5*/("""}"""),format.raw/*115.6*/("""
			"""),format.raw/*116.4*/("""}"""),format.raw/*116.5*/("""
			
		""")))}),format.raw/*118.4*/("""
			"""),format.raw/*119.4*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*120.2*/("""}"""),format.raw/*120.3*/(""");
	
	
	
	
	
</script>




		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,listTipoEstados:List[tables.TipoEstado],mapDatos:Map[Long,List[List[String]]],bodega:tables.BodegaEmpresa): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,listTipoEstados,mapDatos,bodega)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[tables.TipoEstado],Map[Long,List[List[String]]],tables.BodegaEmpresa) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,listTipoEstados,mapDatos,bodega) => apply(mapDiccionario,mapPermiso,userMnu,listTipoEstados,mapDatos,bodega)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reporteEstadosTodosDetalle.scala.html
                  HASH: fcc6a7d811f2afb3edbe8de677b16558968de236
                  MATRIX: 1100->1|1402->210|1434->217|1450->225|1489->227|1518->231|1586->279|1616->284|1660->308|1688->311|1728->331|1759->335|1836->386|2009->537|2042->543|2907->1382|2957->1416|2996->1417|3031->1425|3126->1493|3145->1503|3187->1524|3293->1603|3312->1613|3340->1620|3504->1757|3543->1758|3583->1771|3669->1841|3709->1843|3750->1856|3796->1875|3854->1917|3894->1919|3952->1950|3991->1951|4078->2011|4117->2012|4162->2029|4241->2089|4280->2090|4325->2107|4423->2178|4450->2184|4482->2189|4510->2195|4563->2217|4602->2237|4641->2238|4714->2284|4753->2285|4812->2317|4851->2318|4897->2336|4963->2375|4977->2380|5005->2387|5038->2393|5065->2399|5115->2430|5154->2431|5200->2449|5232->2454|5259->2460|5313->2483|5353->2504|5392->2505|5437->2522|5469->2527|5496->2533|5549->2555|5596->2571|5642->2586|5683->2599|5733->2618|5789->2630|5846->2659|5921->2704|5954->2710|6164->2893|6179->2899|6208->2907|6253->2922|6283->2925|6374->2988|6403->2989|6436->2996|6486->3030|6525->3031|6556->3035|6621->3073|6640->3083|6668->3090|6756->3150|6785->3151|6819->3158|7165->3475|7195->3476|7229->3482|7531->3756|7560->3757|7593->3762|7622->3763|7654->3767|7727->3811|7757->3812|7790->3817|7872->3870|7902->3871|7936->3877|7980->3892|8010->3893|8045->3900|8128->3955|8157->3956|8190->3960|8220->3961|8255->3968|8337->4022|8366->4023|8399->4028|8428->4029|8460->4033|8489->4034|8528->4042|8560->4046|8654->4112|8683->4113
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|42->11|43->12|43->12|45->14|69->38|69->38|69->38|70->39|71->40|71->40|71->40|73->42|73->42|73->42|74->43|74->43|75->44|75->44|75->44|76->45|77->46|77->46|77->46|78->47|78->47|79->48|79->48|80->49|81->50|81->50|82->51|82->51|82->51|82->51|82->51|83->52|84->53|84->53|85->54|85->54|86->55|86->55|87->56|87->56|87->56|87->56|87->56|87->56|88->57|88->57|89->58|89->58|89->58|90->59|91->60|91->60|92->61|92->61|92->61|93->62|94->63|95->64|96->65|97->66|98->67|101->70|104->73|105->74|111->80|111->80|111->80|115->84|118->87|120->89|120->89|122->91|122->91|122->91|123->92|123->92|123->92|123->92|124->93|124->93|125->94|131->100|131->100|132->101|137->106|137->106|138->107|138->107|139->108|139->108|139->108|140->109|140->109|140->109|141->110|141->110|141->110|142->111|143->112|143->112|143->112|143->112|144->113|145->114|145->114|146->115|146->115|147->116|147->116|149->118|150->119|151->120|151->120
                  -- GENERATED --
              */
          