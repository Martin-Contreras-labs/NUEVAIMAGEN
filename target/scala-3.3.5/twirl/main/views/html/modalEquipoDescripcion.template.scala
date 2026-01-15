
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

object modalEquipoDescripcion extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.3*/("""<div id='modalEquipoDescripcion' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>DESCRIPCION DEL EQUIPO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="atributosEquipo"></div>
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

	const equipoDescripcion = (id_equipo) => """),format.raw/*28.43*/("""{"""),format.raw/*28.44*/("""
		"""),format.raw/*29.3*/("""var formData = new FormData();
		formData.append('id_equipo', id_equipo);
	  	$.ajax("""),format.raw/*31.12*/("""{"""),format.raw/*31.13*/("""
            """),format.raw/*32.13*/("""url: "/equipoDescripcionAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*39.31*/("""{"""),format.raw/*39.32*/("""
	     		"""),format.raw/*40.9*/("""if(data!="error")"""),format.raw/*40.26*/("""{"""),format.raw/*40.27*/("""
	     			"""),format.raw/*41.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('atributosEquipo').innerHTML = data;
					$('#modalEquipoDescripcion').modal('show');
	     		"""),format.raw/*46.9*/("""}"""),format.raw/*46.10*/("""
	     	"""),format.raw/*47.8*/("""}"""),format.raw/*47.9*/("""
        """),format.raw/*48.9*/("""}"""),format.raw/*48.10*/(""");
	"""),format.raw/*49.2*/("""}"""),format.raw/*49.3*/("""
	
	"""),format.raw/*51.2*/("""const buscaEquipo = (cod_equipo) =>"""),format.raw/*51.37*/("""{"""),format.raw/*51.38*/("""
		"""),format.raw/*52.3*/("""var formData = new FormData();
		formData.append('cod_equipo', cod_equipo);
	  	$.ajax("""),format.raw/*54.12*/("""{"""),format.raw/*54.13*/("""
            """),format.raw/*55.13*/("""url: "/equipoFindIdForCodAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*62.31*/("""{"""),format.raw/*62.32*/("""
	     		"""),format.raw/*63.9*/("""if(data!="error")"""),format.raw/*63.26*/("""{"""),format.raw/*63.27*/("""
	     			"""),format.raw/*64.10*/("""equipoDescripcion(data);
	     		"""),format.raw/*65.9*/("""}"""),format.raw/*65.10*/("""
	     	"""),format.raw/*66.8*/("""}"""),format.raw/*66.9*/("""
        """),format.raw/*67.9*/("""}"""),format.raw/*67.10*/(""");
	"""),format.raw/*68.2*/("""}"""),format.raw/*68.3*/("""

"""),format.raw/*70.1*/("""</script>

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
                  SOURCE: app/views/modalEquipoDescripcion.scala.html
                  HASH: acb6476f63aaf79711cb11c1ffbc4458cc77bdfe
                  MATRIX: 1044->3|2029->960|2058->961|2088->964|2201->1049|2230->1050|2271->1063|2528->1292|2557->1293|2593->1302|2638->1319|2667->1320|2705->1330|2974->1572|3003->1573|3038->1581|3066->1582|3102->1591|3131->1592|3162->1596|3190->1597|3221->1601|3284->1636|3313->1637|3343->1640|3458->1727|3487->1728|3528->1741|3786->1971|3815->1972|3851->1981|3896->1998|3925->1999|3963->2009|4023->2042|4052->2043|4087->2051|4115->2052|4151->2061|4180->2062|4211->2066|4239->2067|4268->2069
                  LINES: 33->2|59->28|59->28|60->29|62->31|62->31|63->32|70->39|70->39|71->40|71->40|71->40|72->41|77->46|77->46|78->47|78->47|79->48|79->48|80->49|80->49|82->51|82->51|82->51|83->52|85->54|85->54|86->55|93->62|93->62|94->63|94->63|94->63|95->64|96->65|96->65|97->66|97->66|98->67|98->67|99->68|99->68|101->70
                  -- GENERATED --
              */
          