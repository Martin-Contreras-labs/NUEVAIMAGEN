
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

object atributosQrEquipo extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[String,String,tables.Equipo,Double,String,List[qr.QrTransacEquipo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(nEmpresa: String, base: String, equipo: tables.Equipo, stock: Double, ubicacion: String, listaTransac: List[qr.QrTransacEquipo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/mainQR("MADA_QR")/*3.19*/ {_display_(Seq[Any](format.raw/*3.21*/("""



"""),format.raw/*7.1*/("""<div class="container">
  	
  	
  	<br><br><br><br>
  	

  		<div class="form-group row">
    		<label for="inputEmpresa" class="col-sm-3 col-form-label"  style="font-size:30px">Propietario:</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control font-weight-bold" id="inputEmpresa" style="font-size:30px;color:blue" value=""""),_display_(/*16.126*/nEmpresa/*16.134*/.toUpperCase()),format.raw/*16.148*/("""" disabled>
		    </div>
  		</div>
  		<br>
  		<div class="form-group row">
    		<label for="inputCodigo" class="col-sm-3 col-form-label"  style="font-size:30px">Código:</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control font-weight-bold" id="inputCodigo" style="font-size:30px;color:blue" value=""""),_display_(/*23.125*/equipo/*23.131*/.codigo.toUpperCase()),format.raw/*23.152*/("""" disabled>
		    </div>
  		</div>
  		<br>
  		<div class="form-group row">
    		<label for="textAreaDecripcion" class="col-sm-3 col-form-label"  style="font-size:30px">Descripción:</label>
		    <div class="col-sm-9">
		      <textarea class="form-control font-weight-bold" id="textAreaDecripcion" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*30.134*/equipo/*30.140*/.nombre.toUpperCase()),format.raw/*30.161*/("""</textarea>
		    </div>
  		</div>
  		<br>
  		<div class="form-group row">
    		<label for="textAreaGrupo" class="col-sm-3 col-form-label"  style="font-size:30px">Grupo:</label>
		    <div class="col-sm-9">
		      <textarea class="form-control font-weight-bold" id="textAreaGrupo" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*37.129*/equipo/*37.135*/.grupo.toUpperCase()),format.raw/*37.155*/("""</textarea>
		    </div>
  		</div>
  		<br>
  		"""),_display_(if(stock<2)/*41.17*/{_display_(Seq[Any](format.raw/*41.18*/("""
	  		"""),format.raw/*42.6*/("""<div class="form-group row">
	    		<label for="textAreaUbicacion" class="col-sm-3 col-form-label"  style="font-size:30px">Ubicación:</label>
			    <div class="col-sm-9">
			      <textarea class="form-control font-weight-bold" id="textAreaUbicacion" rows="2" style="font-size:30px;color:blue" disabled>"""),_display_(/*45.134*/ubicacion/*45.143*/.toUpperCase()),format.raw/*45.157*/("""</textarea>
			    </div>
	  		</div>
  		""")))} else {null} ),format.raw/*48.6*/("""
  		"""),_display_(/*49.6*/for(lista <-listaTransac) yield /*49.31*/{_display_(Seq[Any](format.raw/*49.32*/("""
  			"""),format.raw/*50.6*/("""<br>
	  		<div class="form-group row">
	    		<label for=""""),_display_(/*52.21*/lista/*52.26*/.campo),format.raw/*52.32*/("""" class="col-sm-5 col-form-label"  style="font-size:30px">"""),_display_(/*52.91*/lista/*52.96*/.campo.toUpperCase()),format.raw/*52.116*/(""":</label>
			    <div class="col-sm-7">
			    	"""),_display_(if(lista.id_qrTipoContenido==1)/*54.41*/{_display_(Seq[Any](format.raw/*54.42*/("""
		        		"""),_display_(if(!lista.contenido.equals("0"))/*55.46*/{_display_(Seq[Any](format.raw/*55.47*/(""" 
		        			"""),format.raw/*56.14*/("""<input type="text" class="form-control font-weight-bold" id=""""),_display_(/*56.76*/lista/*56.81*/.campo),format.raw/*56.87*/("""" style="font-size:30px;color:blue" value=""""),_display_(/*56.131*/lista/*56.136*/.contenido.toUpperCase()),format.raw/*56.160*/("""" disabled>
		        		""")))} else {null} ),format.raw/*57.14*/("""
		        	""")))}else/*58.17*/{_display_(Seq[Any](format.raw/*58.18*/("""
		        		"""),_display_(if(!lista.contenido.equals("0"))/*59.46*/{_display_(Seq[Any](format.raw/*59.47*/("""
		        		"""),format.raw/*60.13*/("""<form id="form" action="/qrImagenQr/" method="POST">
					  		<input type="hidden" name="nEmpresa" value=""""),_display_(/*61.55*/nEmpresa),format.raw/*61.63*/("""">
					  		<input type="hidden" name="base" value=""""),_display_(/*62.51*/base),format.raw/*62.55*/("""">
					  		<input type="hidden" name="id_equipo" value=""""),_display_(/*63.56*/equipo/*63.62*/.id),format.raw/*63.65*/("""">
					  		<input type="hidden" name="stock" value=""""),_display_(/*64.52*/stock),format.raw/*64.57*/("""">
					  		<input type="hidden" name="ubicacion" value=""""),_display_(/*65.56*/ubicacion),format.raw/*65.65*/("""">
					  		<input type="hidden" name="archivo" value=""""),_display_(/*66.54*/lista/*66.59*/.contenido),format.raw/*66.69*/("""">
					  		<input type="hidden" name="tipo" value=""""),_display_(/*67.51*/lista/*67.56*/.tipo),format.raw/*67.61*/("""">
							<button type="submit" class="btn btn-warning btn-lg btn-block" style="font-size:40px">
								"""),_display_(if(lista.tipo.equals("Archivo PDF"))/*69.46*/{_display_(Seq[Any](format.raw/*69.47*/("""
									"""),format.raw/*70.10*/("""download PDF
								""")))}else/*71.14*/{_display_(Seq[Any](format.raw/*71.15*/("""
									"""),format.raw/*72.10*/("""mostrar IMG
								""")))}),format.raw/*73.10*/("""
							"""),format.raw/*74.8*/("""</button>
						</form>
		        		""")))} else {null} ),format.raw/*76.14*/("""
		        	""")))}),format.raw/*77.13*/("""
			      
			    """),format.raw/*79.8*/("""</div>
	  		</div>
  		""")))}),format.raw/*81.6*/("""
  		
  		"""),format.raw/*83.5*/("""<div align="center">
	  		<br><br>
	  		<button type="button" class="btn btn-info btn-lg" style="font-size:30px" onclick="history.go(-1)">VOLVER</button>
		</div>
	
	
</div>









""")))}),format.raw/*99.2*/("""
"""))
      }
    }
  }

  def render(nEmpresa:String,base:String,equipo:tables.Equipo,stock:Double,ubicacion:String,listaTransac:List[qr.QrTransacEquipo]): play.twirl.api.HtmlFormat.Appendable = apply(nEmpresa,base,equipo,stock,ubicacion,listaTransac)

  def f:((String,String,tables.Equipo,Double,String,List[qr.QrTransacEquipo]) => play.twirl.api.HtmlFormat.Appendable) = (nEmpresa,base,equipo,stock,ubicacion,listaTransac) => apply(nEmpresa,base,equipo,stock,ubicacion,listaTransac)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/atributosQrEquipo.scala.html
                  HASH: 639f928fa80ab0f17219af2fd20535c3d09abc75
                  MATRIX: 1022->1|1245->131|1272->133|1297->150|1336->152|1366->156|1747->509|1765->517|1801->531|2163->865|2179->871|2222->892|2605->1247|2621->1253|2664->1274|3031->1613|3047->1619|3089->1639|3177->1700|3216->1701|3249->1707|3582->2012|3601->2021|3637->2035|3723->2078|3755->2084|3796->2109|3835->2110|3868->2116|3954->2175|3968->2180|3995->2186|4081->2245|4095->2250|4137->2270|4244->2350|4283->2351|4356->2397|4395->2398|4438->2413|4527->2475|4541->2480|4568->2486|4640->2530|4655->2535|4701->2559|4770->2584|4806->2601|4845->2602|4918->2648|4957->2649|4998->2662|5132->2769|5161->2777|5241->2830|5266->2834|5351->2892|5366->2898|5390->2901|5471->2955|5497->2960|5582->3018|5612->3027|5695->3083|5709->3088|5740->3098|5820->3151|5834->3156|5860->3161|6029->3303|6068->3304|6106->3314|6151->3340|6190->3341|6228->3351|6280->3372|6315->3380|6396->3417|6440->3430|6485->3448|6539->3472|6576->3482|6790->3666
                  LINES: 28->1|33->2|34->3|34->3|34->3|38->7|47->16|47->16|47->16|54->23|54->23|54->23|61->30|61->30|61->30|68->37|68->37|68->37|72->41|72->41|73->42|76->45|76->45|76->45|79->48|80->49|80->49|80->49|81->50|83->52|83->52|83->52|83->52|83->52|83->52|85->54|85->54|86->55|86->55|87->56|87->56|87->56|87->56|87->56|87->56|87->56|88->57|89->58|89->58|90->59|90->59|91->60|92->61|92->61|93->62|93->62|94->63|94->63|94->63|95->64|95->64|96->65|96->65|97->66|97->66|97->66|98->67|98->67|98->67|100->69|100->69|101->70|102->71|102->71|103->72|104->73|105->74|107->76|108->77|110->79|112->81|114->83|130->99
                  -- GENERATED --
              */
          