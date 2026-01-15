
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

object modalVerCotizacion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.2*/("""<div id='modalVerCotizacion' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable' style="max-width: 80% !important;" role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>COTIZACION</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id='mostrarLaCotizacion'></div>
	   				<div class='row'>
						<div class='col-sm-12' style='text-align:center'>
							<button type='button' class='btn btn-sm  btn-success' style='font-size: 10px;' data-dismiss='modal'>Listo</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
<script type="text/javascript">

	const modalVerCotizacion = (id_cotizacion) => """),format.raw/*26.48*/("""{"""),format.raw/*26.49*/("""
		"""),format.raw/*27.3*/("""var formData = new FormData();
	  	formData.append('id_cotizacion',id_cotizacion);
        $.ajax("""),format.raw/*29.16*/("""{"""),format.raw/*29.17*/("""
            """),format.raw/*30.13*/("""url: "/verCotizacionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*37.36*/("""{"""),format.raw/*37.37*/("""
	     		"""),format.raw/*38.9*/("""document.getElementById('mostrarLaCotizacion').innerHTML = respuesta;
	     		$('#modalVerCotizacion').modal('show');
	     	"""),format.raw/*40.8*/("""}"""),format.raw/*40.9*/("""
        """),format.raw/*41.9*/("""}"""),format.raw/*41.10*/(""");
	"""),format.raw/*42.2*/("""}"""),format.raw/*42.3*/("""
	
	"""),format.raw/*44.2*/("""const buscaCotizacion = (numero) =>"""),format.raw/*44.37*/("""{"""),format.raw/*44.38*/("""
		"""),format.raw/*45.3*/("""var formData = new FormData();
		formData.append('numero', numero);
	  	$.ajax("""),format.raw/*47.12*/("""{"""),format.raw/*47.13*/("""
            """),format.raw/*48.13*/("""url: "/cotizacionFindIdForNumAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*55.31*/("""{"""),format.raw/*55.32*/("""
	     		"""),format.raw/*56.9*/("""if(data!="error")"""),format.raw/*56.26*/("""{"""),format.raw/*56.27*/("""
	     			"""),format.raw/*57.10*/("""modalVerCotizacion(data);
	     		"""),format.raw/*58.9*/("""}"""),format.raw/*58.10*/("""
	     	"""),format.raw/*59.8*/("""}"""),format.raw/*59.9*/("""
        """),format.raw/*60.9*/("""}"""),format.raw/*60.10*/(""");
	"""),format.raw/*61.2*/("""}"""),format.raw/*61.3*/("""

"""),format.raw/*63.1*/("""</script>

"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/modalVerCotizacion.scala.html
                  HASH: 7c0173c8323afa9d7c2ca29b6081f42a4f01c159
                  MATRIX: 1040->2|1980->914|2009->915|2039->918|2165->1016|2194->1017|2235->1030|2493->1260|2522->1261|2558->1270|2710->1395|2738->1396|2774->1405|2803->1406|2834->1410|2862->1411|2893->1415|2956->1450|2985->1451|3015->1454|3122->1533|3151->1534|3192->1547|3454->1781|3483->1782|3519->1791|3564->1808|3593->1809|3631->1819|3692->1853|3721->1854|3756->1862|3784->1863|3820->1872|3849->1873|3880->1877|3908->1878|3937->1880
                  LINES: 33->2|57->26|57->26|58->27|60->29|60->29|61->30|68->37|68->37|69->38|71->40|71->40|72->41|72->41|73->42|73->42|75->44|75->44|75->44|76->45|78->47|78->47|79->48|86->55|86->55|87->56|87->56|87->56|88->57|89->58|89->58|90->59|90->59|91->60|91->60|92->61|92->61|94->63
                  -- GENERATED --
              */
          