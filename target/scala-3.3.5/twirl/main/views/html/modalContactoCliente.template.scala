
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

object modalContactoCliente extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.3*/("""<div id='modalContactoCliente' class='modal' role='dialog' data-backdrop='static' data-keyboard='false'>
		<div class='modal-dialog modal-dialog-scrollable modal-lg' role='document'>
			<div class='modal-content'>
				<div class='modal-header'>
					<h5 class='modal-title'>CONTACTOS CLIENTE</h5>
				        <button type='button' class='close' data-dismiss='modal' aria-label='Close'>
				          <span aria-hidden='true'>&times;</span>
				        </button>
				</div>
				<div class='modal-body'>
					<div id="contactosCliente"></div>
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

	const modalContactoCliente = (id_cliente) => """),format.raw/*28.47*/("""{"""),format.raw/*28.48*/("""
		"""),format.raw/*29.3*/("""var formData = new FormData();
		formData.append('id_cliente', id_cliente);
	  	$.ajax("""),format.raw/*31.12*/("""{"""),format.raw/*31.13*/("""
            """),format.raw/*32.13*/("""url: "/clienteContactosAjax/",
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
	     			document.getElementById('contactosCliente').innerHTML = data;
					$('#modalContactoCliente').modal('show');
	     		"""),format.raw/*46.9*/("""}"""),format.raw/*46.10*/("""
	     	"""),format.raw/*47.8*/("""}"""),format.raw/*47.9*/("""
        """),format.raw/*48.9*/("""}"""),format.raw/*48.10*/(""");
	"""),format.raw/*49.2*/("""}"""),format.raw/*49.3*/("""
	
	"""),format.raw/*51.2*/("""const buscaCliente = (nickName) =>"""),format.raw/*51.36*/("""{"""),format.raw/*51.37*/("""
		"""),format.raw/*52.3*/("""var formData = new FormData();
		formData.append('nickName', nickName);
	  	$.ajax("""),format.raw/*54.12*/("""{"""),format.raw/*54.13*/("""
            """),format.raw/*55.13*/("""url: "/clienteFindIdForNickNameAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*62.31*/("""{"""),format.raw/*62.32*/("""
	     		"""),format.raw/*63.9*/("""if(data!="error")"""),format.raw/*63.26*/("""{"""),format.raw/*63.27*/("""
	     			"""),format.raw/*64.10*/("""modalContactoCliente(data);
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
                  SOURCE: app/views/modalContactoCliente.scala.html
                  HASH: 927d4710dc26872e55c94b06bb30e434aa4d9f3d
                  MATRIX: 1042->3|2025->958|2054->959|2084->962|2199->1049|2228->1050|2269->1063|2525->1291|2554->1292|2590->1301|2635->1318|2664->1319|2702->1329|2970->1570|2999->1571|3034->1579|3062->1580|3098->1589|3127->1590|3158->1594|3186->1595|3217->1599|3279->1633|3308->1634|3338->1637|3449->1720|3478->1721|3519->1734|3783->1970|3812->1971|3848->1980|3893->1997|3922->1998|3960->2008|4023->2044|4052->2045|4087->2053|4115->2054|4151->2063|4180->2064|4211->2068|4239->2069|4268->2071
                  LINES: 33->2|59->28|59->28|60->29|62->31|62->31|63->32|70->39|70->39|71->40|71->40|71->40|72->41|77->46|77->46|78->47|78->47|79->48|79->48|80->49|80->49|82->51|82->51|82->51|83->52|85->54|85->54|86->55|93->62|93->62|94->63|94->63|94->63|95->64|96->65|96->65|97->66|97->66|98->67|98->67|99->68|99->68|101->70
                  -- GENERATED --
              */
          