
package viewsMnuQr.html

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

object inicioQr extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[String,String,tables.Equipo,Double,String,Long,Long,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(nEmpresa: String, base: String, equipo: tables.Equipo, stock: Double, ubicacion: String, cantTransacEquipo: Long, conBtnMantencion: Long):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/mainQR("MADA_QR")/*3.19*/ {_display_(Seq[Any](format.raw/*3.21*/("""

	
	
	"""),format.raw/*7.2*/("""<div class="container">
	  	
	  	
	  	<br><br><br><br>
	  	
	  	<form>
	  		<div class="form-group row">
	    		<label for="inputEmpresa" class="col-sm-3 col-form-label"  style="font-size:30px">Propietario:</label>
			    <div class="col-sm-9">
			      <input type="text" class="form-control font-weight-bold" id="inputEmpresa" style="font-size:30px;color:blue" value=""""),_display_(/*16.127*/nEmpresa/*16.135*/.toUpperCase()),format.raw/*16.149*/("""" disabled>
			    </div>
	  		</div>
	  		<br>
	  		<div class="form-group row">
	    		<label for="inputCodigo" class="col-sm-3 col-form-label"  style="font-size:30px">Código:</label>
			    <div class="col-sm-9">
			      <input type="text" class="form-control font-weight-bold" id="inputCodigo" style="font-size:30px;color:blue" value=""""),_display_(/*23.126*/equipo/*23.132*/.codigo.toUpperCase()),format.raw/*23.153*/("""" disabled>
			    </div>
	  		</div>
	  		<br>
	  		<div class="form-group row">
	    		<label for="textAreaDecripcion" class="col-sm-3 col-form-label"  style="font-size:30px">Descripción:</label>
			    <div class="col-sm-9">
			      <textarea class="form-control font-weight-bold" id="textAreaDecripcion" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*30.135*/equipo/*30.141*/.nombre.toUpperCase()),format.raw/*30.162*/("""</textarea>
			    </div>
	  		</div>
	  		<br>
	  		<div class="form-group row">
	    		<label for="textAreaGrupo" class="col-sm-3 col-form-label"  style="font-size:30px">Grupo:</label>
			    <div class="col-sm-9">
			      <textarea class="form-control font-weight-bold" id="textAreaGrupo" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*37.130*/equipo/*37.136*/.grupo.toUpperCase()),format.raw/*37.156*/("""</textarea>
			    </div>
	  		</div>
	  		<br>
	  		"""),_display_(if(stock<2)/*41.18*/{_display_(Seq[Any](format.raw/*41.19*/("""
		  		"""),format.raw/*42.7*/("""<div class="form-group row">
		    		<label for="textAreaUbicacion" class="col-sm-3 col-form-label"  style="font-size:30px">Ubicación:</label>
				    <div class="col-sm-9">
				      <textarea class="form-control font-weight-bold" id="textAreaUbicacion" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*45.135*/ubicacion/*45.144*/.toUpperCase()),format.raw/*45.158*/("""</textarea>
				    </div>
		  		</div>
	  		""")))} else {null} ),format.raw/*48.7*/("""
	"""),format.raw/*49.2*/("""</form>
	  	
	  	
	  	<br><br>
	  	<form id="form" action="/qrImagen/" method="POST">
	  		<input type="hidden" name="nEmpresa" value=""""),_display_(/*54.51*/nEmpresa),format.raw/*54.59*/("""">
	  		<input type="hidden" name="base" value=""""),_display_(/*55.47*/base),format.raw/*55.51*/("""">
	  		<input type="hidden" name="id_equipo" value=""""),_display_(/*56.52*/equipo/*56.58*/.id),format.raw/*56.61*/("""">
	  		<input type="hidden" name="stock" value=""""),_display_(/*57.48*/stock),format.raw/*57.53*/("""">
	  		<input type="hidden" name="ubicacion" value=""""),_display_(/*58.52*/ubicacion),format.raw/*58.61*/("""">
			<button type="submit" class="btn btn-warning btn-lg btn-block" style="font-size:40px">IMAGEN</button>
		</form>
		<br><br>
		<form id="form" action="/qrAtributo/" method="POST">
			<input type="hidden" name="nEmpresa" value=""""),_display_(/*63.49*/nEmpresa),format.raw/*63.57*/("""">
	  		<input type="hidden" name="base" value=""""),_display_(/*64.47*/base),format.raw/*64.51*/("""">
	  		<input type="hidden" name="id_equipo" value=""""),_display_(/*65.52*/equipo/*65.58*/.id),format.raw/*65.61*/("""">
	  		<input type="hidden" name="stock" value=""""),_display_(/*66.48*/stock),format.raw/*66.53*/("""">
	  		<input type="hidden" name="ubicacion" value=""""),_display_(/*67.52*/ubicacion),format.raw/*67.61*/("""">
			<button type="submit" class="btn btn-primary btn-lg btn-block" style="font-size:40px">ATRIBUTOS</button>
		</form>
		<br><br>
		"""),_display_(if(stock>1)/*71.15*/{_display_(Seq[Any](format.raw/*71.16*/("""
			"""),format.raw/*72.4*/("""<form id="form" action="/qrUbicacion/" method="POST">
				<input type="hidden" name="nEmpresa" value=""""),_display_(/*73.50*/nEmpresa),format.raw/*73.58*/("""">
		  		<input type="hidden" name="base" value=""""),_display_(/*74.48*/base),format.raw/*74.52*/("""">
		  		<input type="hidden" name="id_equipo" value=""""),_display_(/*75.53*/equipo/*75.59*/.id),format.raw/*75.62*/("""">
		  		<input type="hidden" name="stock" value=""""),_display_(/*76.49*/stock),format.raw/*76.54*/("""">
	  			<input type="hidden" name="ubicacion" value=""""),_display_(/*77.53*/ubicacion),format.raw/*77.62*/("""">
				<button type="submit" class="btn btn-success btn-lg btn-block" style="font-size:40px">UBICACION</button>
			</form>
			<br><br>
		""")))} else {null} ),format.raw/*81.4*/("""
		"""),_display_(if(stock<2)/*82.15*/{_display_(Seq[Any](format.raw/*82.16*/("""
			"""),format.raw/*83.4*/("""<form id="form" action="/qrTrazabilidad/" method="POST">
				<input type="hidden" name="nEmpresa" value=""""),_display_(/*84.50*/nEmpresa),format.raw/*84.58*/("""">
		  		<input type="hidden" name="base" value=""""),_display_(/*85.48*/base),format.raw/*85.52*/("""">
		  		<input type="hidden" name="id_equipo" value=""""),_display_(/*86.53*/equipo/*86.59*/.id),format.raw/*86.62*/("""">
		  		<input type="hidden" name="stock" value=""""),_display_(/*87.49*/stock),format.raw/*87.54*/("""">
	  			<input type="hidden" name="ubicacion" value=""""),_display_(/*88.53*/ubicacion),format.raw/*88.62*/("""">
				<button type="submit" class="btn btn-success btn-lg btn-block" style="font-size:40px">TRAZABILIDAD</button>
			</form>
			<br><br>
		""")))} else {null} ),format.raw/*92.4*/("""
		"""),format.raw/*93.3*/("""<form id="form" action="/qrHistoria/" method="POST">
			<input type="hidden" name="nEmpresa" value=""""),_display_(/*94.49*/nEmpresa),format.raw/*94.57*/("""">
	  		<input type="hidden" name="base" value=""""),_display_(/*95.47*/base),format.raw/*95.51*/("""">
	  		<input type="hidden" name="id_equipo" value=""""),_display_(/*96.52*/equipo/*96.58*/.id),format.raw/*96.61*/("""">
	  		<input type="hidden" name="stock" value=""""),_display_(/*97.48*/stock),format.raw/*97.53*/("""">
	  		<input type="hidden" name="ubicacion" value=""""),_display_(/*98.52*/ubicacion),format.raw/*98.61*/("""">
			<button type="submit" class="btn btn-info btn-lg btn-block" style="font-size:40px">HOJA DE VIDA</button>
		</form>
		<br><br>
		"""),_display_(if(cantTransacEquipo>0)/*102.27*/{_display_(Seq[Any](format.raw/*102.28*/("""
			"""),format.raw/*103.4*/("""<form id="form" action="/qrAtributosQr/" method="POST">
				<input type="hidden" name="nEmpresa" value=""""),_display_(/*104.50*/nEmpresa),format.raw/*104.58*/("""">
		  		<input type="hidden" name="base" value=""""),_display_(/*105.48*/base),format.raw/*105.52*/("""">
		  		<input type="hidden" name="id_equipo" value=""""),_display_(/*106.53*/equipo/*106.59*/.id),format.raw/*106.62*/("""">
		  		<input type="hidden" name="stock" value=""""),_display_(/*107.49*/stock),format.raw/*107.54*/("""">
		  		<input type="hidden" name="ubicacion" value=""""),_display_(/*108.53*/ubicacion),format.raw/*108.62*/("""">
				<button type="submit" class="btn btn-info btn-lg btn-block" style="font-size:40px">OTROS</button>
			</form>
			<br><br>
		""")))} else {null} ),format.raw/*112.4*/("""

		"""),_display_(if(conBtnMantencion > 0)/*114.28*/{_display_(Seq[Any](format.raw/*114.29*/("""
			"""),format.raw/*115.4*/("""<button type="button" class="btn btn-warning btn-lg btn-block" style="font-size:40px" onclick="location.href='https://madainqsol.com/report?id_equipo="""),_display_(/*115.155*/equipo/*115.161*/.getId()),format.raw/*115.169*/("""'">
				Ingresar Nuevos Report
			</button>
		""")))} else {null} ),format.raw/*118.4*/("""

		
	"""),format.raw/*121.2*/("""</div>
	
	








""")))}),format.raw/*132.2*/("""
"""))
      }
    }
  }

  def render(nEmpresa:String,base:String,equipo:tables.Equipo,stock:Double,ubicacion:String,cantTransacEquipo:Long,conBtnMantencion:Long): play.twirl.api.HtmlFormat.Appendable = apply(nEmpresa,base,equipo,stock,ubicacion,cantTransacEquipo,conBtnMantencion)

  def f:((String,String,tables.Equipo,Double,String,Long,Long) => play.twirl.api.HtmlFormat.Appendable) = (nEmpresa,base,equipo,stock,ubicacion,cantTransacEquipo,conBtnMantencion) => apply(nEmpresa,base,equipo,stock,ubicacion,cantTransacEquipo,conBtnMantencion)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/inicioQr.scala.html
                  HASH: 3d6493bf6ebf0db1ce2ebcc6146a371ee3afde46
                  MATRIX: 998->1|1230->140|1257->142|1282->159|1321->161|1354->168|1753->539|1771->547|1807->561|2176->902|2192->908|2235->929|2625->1291|2641->1297|2684->1318|3058->1664|3074->1670|3116->1690|3208->1755|3247->1756|3281->1763|3617->2071|3636->2080|3672->2094|3761->2140|3790->2142|3953->2278|3982->2286|4058->2335|4083->2339|4164->2393|4179->2399|4203->2402|4280->2452|4306->2457|4387->2511|4417->2520|4676->2752|4705->2760|4781->2809|4806->2813|4887->2867|4902->2873|4926->2876|5003->2926|5029->2931|5110->2985|5140->2994|5313->3140|5352->3141|5383->3145|5513->3248|5542->3256|5619->3306|5644->3310|5726->3365|5741->3371|5765->3374|5843->3425|5869->3430|5951->3485|5981->3494|6162->3632|6204->3647|6243->3648|6274->3652|6407->3758|6436->3766|6513->3816|6538->3820|6620->3875|6635->3881|6659->3884|6737->3935|6763->3940|6845->3995|6875->4004|7059->4145|7089->4148|7217->4249|7246->4257|7322->4306|7347->4310|7428->4364|7443->4370|7467->4373|7544->4423|7570->4428|7651->4482|7681->4491|7867->4649|7907->4650|7939->4654|8072->4759|8102->4767|8180->4817|8206->4821|8289->4876|8305->4882|8330->4885|8409->4936|8436->4941|8519->4996|8550->5005|8725->5136|8782->5165|8822->5166|8854->5170|9034->5321|9051->5327|9082->5335|9173->5382|9207->5388|9258->5408
                  LINES: 28->1|33->2|34->3|34->3|34->3|38->7|47->16|47->16|47->16|54->23|54->23|54->23|61->30|61->30|61->30|68->37|68->37|68->37|72->41|72->41|73->42|76->45|76->45|76->45|79->48|80->49|85->54|85->54|86->55|86->55|87->56|87->56|87->56|88->57|88->57|89->58|89->58|94->63|94->63|95->64|95->64|96->65|96->65|96->65|97->66|97->66|98->67|98->67|102->71|102->71|103->72|104->73|104->73|105->74|105->74|106->75|106->75|106->75|107->76|107->76|108->77|108->77|112->81|113->82|113->82|114->83|115->84|115->84|116->85|116->85|117->86|117->86|117->86|118->87|118->87|119->88|119->88|123->92|124->93|125->94|125->94|126->95|126->95|127->96|127->96|127->96|128->97|128->97|129->98|129->98|133->102|133->102|134->103|135->104|135->104|136->105|136->105|137->106|137->106|137->106|138->107|138->107|139->108|139->108|143->112|145->114|145->114|146->115|146->115|146->115|146->115|149->118|152->121|163->132
                  -- GENERATED --
              */
          