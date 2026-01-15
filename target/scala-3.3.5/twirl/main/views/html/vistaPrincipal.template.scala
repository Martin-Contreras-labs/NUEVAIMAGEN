
package views.html

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

object vistaPrincipal extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu, moroso: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""

"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""
	"""),_display_(/*5.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*5.51*/("""
	"""),_display_(/*6.3*/barraTitulo(mapDiccionario, "SISTEMA: MADA - EMPRESA: "+mapDiccionario.get("nEmpresa"), "Manejo y Administracion de Activos")),format.raw/*6.128*/("""
	"""),format.raw/*7.2*/("""<style>
		.fondo1"""),format.raw/*8.10*/("""{"""),format.raw/*8.11*/("""
		   """),format.raw/*9.6*/("""background: url('/assets/images/area5-fondo.jpg') no-repeat center center;
		   background-size: cover;
		   width: 100% ;
		   text-align: center;
		 
		"""),format.raw/*14.3*/("""}"""),format.raw/*14.4*/("""
		"""),format.raw/*15.3*/(""".fondo2"""),format.raw/*15.10*/("""{"""),format.raw/*15.11*/("""
		   """),format.raw/*16.6*/("""background: url('/assets/images/fondoOpera.jpg') no-repeat center center;
		   background-size: cover;
		   width: 100% ;
		   text-align: center;
		 
		"""),format.raw/*21.3*/("""}"""),format.raw/*21.4*/("""
	"""),format.raw/*22.2*/("""</style>
	"""),_display_(if(mapDiccionario("nEmpresa").equals("OLA")||mapDiccionario("nEmpresa").equals("OLA TENERIFE"))/*23.98*/{_display_(Seq[Any](format.raw/*23.99*/("""
		"""),format.raw/*24.3*/("""<body class="fondo2"></body>
	""")))}else/*25.7*/{_display_(Seq[Any](format.raw/*25.8*/("""
		"""),format.raw/*26.3*/("""<body class="fondo1"></body>
	""")))}),format.raw/*27.3*/("""
	"""),format.raw/*28.2*/("""<div id='cobranza' class="modal" role="dialog" data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
				   	<h5 id="nombreEquipo" class="modal-title">
			           RECORDATORIO
			        </h5>
			        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
			        	<span aria-hidden='true'>&times;</span>
			        </button>
				</div>
	     		<div class="modal-body">
		     		<div align="center">
		     			<h5>ACTUALMENTE PRESENTA FACTURAS IMPAGAS CON INQSOL SPA, POR FAVOR REGULARIZAR</h5>
		     			<button type="button" class="btn btn-success btn-sm rounded-pill btn-block" data-dismiss="modal" >
							CERRAR
						</button>
					</div>
				</div>
	   		</div>
		</div>
	</div>
""")))}),format.raw/*50.2*/("""

"""),format.raw/*52.1*/("""<script type="text/javascript" charset="utf-8">
	  $(document).ready(function () """),format.raw/*53.34*/("""{"""),format.raw/*53.35*/("""
	        	"""),format.raw/*54.11*/("""if('"""),_display_(/*54.16*/moroso),format.raw/*54.22*/("""'=='1')"""),format.raw/*54.29*/("""{"""),format.raw/*54.30*/("""
	        		"""),format.raw/*55.12*/("""$('#cobranza').modal('show');
	        	"""),format.raw/*56.11*/("""}"""),format.raw/*56.12*/("""
	   """),format.raw/*57.5*/("""}"""),format.raw/*57.6*/(""");
</script>"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,moroso:Long): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,moroso)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,Long) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,moroso) => apply(mapDiccionario,mapPermiso,userMnu,moroso)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/vistaPrincipal.scala.html
                  HASH: 078c22681a5dbcb48c6210570bd93cdd8167252e
                  MATRIX: 1008->1|1212->112|1240->115|1256->123|1295->125|1323->128|1391->176|1419->179|1565->304|1593->306|1637->323|1665->324|1697->330|1878->484|1906->485|1936->488|1971->495|2000->496|2033->502|2213->655|2241->656|2270->658|2403->764|2442->765|2472->768|2525->803|2563->804|2593->807|2654->838|2683->840|3555->1682|3584->1684|3693->1765|3722->1766|3761->1777|3793->1782|3820->1788|3855->1795|3884->1796|3924->1808|3992->1848|4021->1849|4053->1854|4081->1855
                  LINES: 28->1|33->2|35->4|35->4|35->4|36->5|36->5|37->6|37->6|38->7|39->8|39->8|40->9|45->14|45->14|46->15|46->15|46->15|47->16|52->21|52->21|53->22|54->23|54->23|55->24|56->25|56->25|57->26|58->27|59->28|81->50|83->52|84->53|84->53|85->54|85->54|85->54|85->54|85->54|86->55|87->56|87->56|88->57|88->57
                  -- GENERATED --
              */
          