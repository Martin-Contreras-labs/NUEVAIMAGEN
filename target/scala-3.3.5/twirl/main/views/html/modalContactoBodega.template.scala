
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

object modalContactoBodega extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[Map[String,String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
  """),format.raw/*3.3*/("""<div id='modalContactoBodega' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>CONTACTOS """),_display_(/*7.41*/mapDiccionario/*7.55*/.get("BODEGA")),format.raw/*7.69*/("""</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="contactosBodegaEmpresa"></div>
					<div class="noprint">
						<div class="row justify-content-md-center" >
							<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">
								<button type='button' class='btn btn-sm  btn-info rounded-pill btn-block' data-dismiss='modal'>Cerrar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
<script type="text/javascript">

	const modalContactoBodega = (id_bodegaEmpresa) => """),format.raw/*29.52*/("""{"""),format.raw/*29.53*/("""
		"""),format.raw/*30.3*/("""var formData = new FormData();
		formData.append('id_bodegaEmpresa', id_bodegaEmpresa);
	  	$.ajax("""),format.raw/*32.12*/("""{"""),format.raw/*32.13*/("""
            """),format.raw/*33.13*/("""url: "/bodegaContactosAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*40.31*/("""{"""),format.raw/*40.32*/("""
	     		"""),format.raw/*41.9*/("""if(data!="error")"""),format.raw/*41.26*/("""{"""),format.raw/*41.27*/("""
	     			"""),format.raw/*42.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('contactosBodegaEmpresa').innerHTML = data;
					$('#modalContactoBodega').modal('show');
	     		"""),format.raw/*47.9*/("""}"""),format.raw/*47.10*/("""
	     	"""),format.raw/*48.8*/("""}"""),format.raw/*48.9*/("""
        """),format.raw/*49.9*/("""}"""),format.raw/*49.10*/(""");
	"""),format.raw/*50.2*/("""}"""),format.raw/*50.3*/("""
	
	"""),format.raw/*52.2*/("""const buscaBodega = (nombreBodega) =>"""),format.raw/*52.39*/("""{"""),format.raw/*52.40*/("""
		"""),format.raw/*53.3*/("""var formData = new FormData();
		formData.append('nombreBodega', nombreBodega);
	  	$.ajax("""),format.raw/*55.12*/("""{"""),format.raw/*55.13*/("""
            """),format.raw/*56.13*/("""url: "/bodegaFindIdForNomAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*63.31*/("""{"""),format.raw/*63.32*/("""
	     		"""),format.raw/*64.9*/("""if(data!="error")"""),format.raw/*64.26*/("""{"""),format.raw/*64.27*/("""
	     			"""),format.raw/*65.10*/("""modalContactoBodega(data);
	     		"""),format.raw/*66.9*/("""}"""),format.raw/*66.10*/("""
	     	"""),format.raw/*67.8*/("""}"""),format.raw/*67.9*/("""
        """),format.raw/*68.9*/("""}"""),format.raw/*68.10*/(""");
	"""),format.raw/*69.2*/("""}"""),format.raw/*69.3*/("""

"""),format.raw/*71.1*/("""</script>

"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario)

  def f:((Map[String,String]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario) => apply(mapDiccionario)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/modalContactoBodega.scala.html
                  HASH: 541d09ec7c9538e6fa665933d86947a6d15f88d3
                  MATRIX: 971->1|1101->38|1130->41|1440->325|1462->339|1496->353|2199->1028|2228->1029|2258->1032|2385->1131|2414->1132|2455->1145|2710->1372|2739->1373|2775->1382|2820->1399|2849->1400|2887->1410|3160->1656|3189->1657|3224->1665|3252->1666|3288->1675|3317->1676|3348->1680|3376->1681|3407->1685|3472->1722|3501->1723|3531->1726|3650->1817|3679->1818|3720->1831|3978->2061|4007->2062|4043->2071|4088->2088|4117->2089|4155->2099|4217->2134|4246->2135|4281->2143|4309->2144|4345->2153|4374->2154|4405->2158|4433->2159|4462->2161
                  LINES: 28->1|33->2|34->3|38->7|38->7|38->7|60->29|60->29|61->30|63->32|63->32|64->33|71->40|71->40|72->41|72->41|72->41|73->42|78->47|78->47|79->48|79->48|80->49|80->49|81->50|81->50|83->52|83->52|83->52|84->53|86->55|86->55|87->56|94->63|94->63|95->64|95->64|95->64|96->65|97->66|97->66|98->67|98->67|99->68|99->68|100->69|100->69|102->71
                  -- GENERATED --
              */
          