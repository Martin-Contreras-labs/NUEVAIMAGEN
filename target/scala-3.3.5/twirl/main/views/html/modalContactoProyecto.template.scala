
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

object modalContactoProyecto extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.3*/("""<div id='modalContactoProyecto' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>CONTACTOS PROYECTO</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="contactosProyecto"></div>
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

	const modalContactoProyecto = (id_proyecto) => """),format.raw/*28.49*/("""{"""),format.raw/*28.50*/("""
		"""),format.raw/*29.3*/("""var formData = new FormData();
		formData.append('id_proyecto', id_proyecto);
	  	$.ajax("""),format.raw/*31.12*/("""{"""),format.raw/*31.13*/("""
            """),format.raw/*32.13*/("""url: "/proyectoContactosAjax/",
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
	     			document.getElementById('contactosProyecto').innerHTML = data;
					$('#modalContactoProyecto').modal('show');
	     		"""),format.raw/*46.9*/("""}"""),format.raw/*46.10*/("""
	     	"""),format.raw/*47.8*/("""}"""),format.raw/*47.9*/("""
        """),format.raw/*48.9*/("""}"""),format.raw/*48.10*/(""");
	"""),format.raw/*49.2*/("""}"""),format.raw/*49.3*/("""
	
	"""),format.raw/*51.2*/("""const buscaProyecto = (nickName) =>"""),format.raw/*51.37*/("""{"""),format.raw/*51.38*/("""
		"""),format.raw/*52.3*/("""var formData = new FormData();
		formData.append('nickName', nickName);
	  	$.ajax("""),format.raw/*54.12*/("""{"""),format.raw/*54.13*/("""
            """),format.raw/*55.13*/("""url: "/proyectoFindIdForNickNameAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*62.31*/("""{"""),format.raw/*62.32*/("""
	     		"""),format.raw/*63.9*/("""if(data!="error")"""),format.raw/*63.26*/("""{"""),format.raw/*63.27*/("""
	     			"""),format.raw/*64.10*/("""modalContactoProyecto(data);
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
                  SOURCE: app/views/modalContactoProyecto.scala.html
                  HASH: 5e57d30a6bc9da537f8f2a6e4a505c2cf8264f40
                  MATRIX: 1043->3|2031->963|2060->964|2090->967|2207->1056|2236->1057|2277->1070|2534->1299|2563->1300|2599->1309|2644->1326|2673->1327|2711->1337|2981->1580|3010->1581|3045->1589|3073->1590|3109->1599|3138->1600|3169->1604|3197->1605|3228->1609|3291->1644|3320->1645|3350->1648|3461->1731|3490->1732|3531->1745|3796->1982|3825->1983|3861->1992|3906->2009|3935->2010|3973->2020|4037->2057|4066->2058|4101->2066|4129->2067|4165->2076|4194->2077|4225->2081|4253->2082|4282->2084
                  LINES: 33->2|59->28|59->28|60->29|62->31|62->31|63->32|70->39|70->39|71->40|71->40|71->40|72->41|77->46|77->46|78->47|78->47|79->48|79->48|80->49|80->49|82->51|82->51|82->51|83->52|85->54|85->54|86->55|93->62|93->62|94->63|94->63|94->63|95->64|96->65|96->65|97->66|97->66|98->67|98->67|99->68|99->68|101->70
                  -- GENERATED --
              */
          