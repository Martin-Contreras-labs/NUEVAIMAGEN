
package viewsMnuAyuda.html

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

object manualPrimerosPasos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Map[String,String],Map[String,String],utilities.UserMnu,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
    	
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

"""),_display_(/*6.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.50*/("""
	"""),format.raw/*7.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*8.4*/barraTitulo(mapDiccionario, "GUIA MANUAL BASICO","")),format.raw/*8.56*/("""
		"""),format.raw/*9.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<h3><b><u>IMPORTANTE</u></b></h3>
					<h3>
						<p class="text-justify" style="font-size:16">
							Al iniciar por primera vez el ingreso de datos al sistema, se recomienda seguir los siguientes pasos.<BR>
							Nota: solo es una recomendación de orden, ya que por las características propias de este software, es 
							totalmente flexible y no requiere de orden específico para operarlo.
						</p>
					</h3>
					<br>
					<h2><b>1- Primer Paso: en menú TABLAS→Usuarios/Perfiles:</b></h2>
						<p class="text-justify" style="font-size:14;">
								Agregar los usuarios y perfiles asociados a cada uno.
						</p>
					<br>
					<h2><b>2- Segundo Paso: en menú TABLAS→Grupos:</b></h2>
						<p class="text-justify" style="font-size:14;">
								Agregar los grupos en los cuales se clasificaran los equipos que se vayan ingresando al sistema.
						</p>
					<br>
					<h2><b>3- Tercer Paso: en menú TABLAS→Proyectos:</b></h2>
						<p class="text-justify" style="font-size:14;">
								Agregar los proyectos actuales a los cuales se genera facturas pro-forma por los equipos asignados.
						</p>
					<br>
					<h2><b>4- Quinto Paso: en menú COMPRAS→Ingresar Compras:</b></h2>
						<p class="text-justify" style="font-size:14;">
								Incorporar el máximo de las existencias o equipos que se deseen administrar. 
								No es necesario ingresar todo ya que siempre es posible ir agregando más equipos. 
								Se recomienda sean ingresados a """),_display_(/*39.42*/mapDiccionario/*39.56*/.get("Bodega")),format.raw/*39.70*/(""" """),format.raw/*39.71*/("""de disponibles.
						</p>
					<br>
					<h2><b>5- Sexto Paso: en menú COMPRAS→Confirmar Compras:</b></h2>
						<p class="text-justify" style="font-size:14;">
								Confirmar todo lo ingresado, previa verificación. Es importante entender que después de confirmado 
								no es posible modificar desde la compra.
						</p>
					<br>
					<h2><b>6- Séptimo Paso: en menú TABLAS→Maestro Precios:</b></h2>
						<p class="text-justify" style="font-size:14;">
								Presionar botón agregar, seleccionar todos si hay pendientes y confirmar. 
								A continuación actualizar los precios de Reposicion y """),_display_(/*51.64*/mapDiccionario/*51.78*/.get("Arriendo")),format.raw/*51.94*/(""" """),format.raw/*51.95*/("""de cada unidad.
						</p>
					<br>
					<h2><b>7- Octavo Paso: en menú """),_display_(/*54.38*/mapDiccionario/*54.52*/.get("Bodegas")),format.raw/*54.67*/("""→Administrar """),_display_(/*54.81*/mapDiccionario/*54.95*/.get("Bodegas")),format.raw/*54.110*/(""":</b></h2>
						<p class="text-justify" style="font-size:14;">
								Agregar """),_display_(/*56.18*/mapDiccionario/*56.32*/.get("Bodegas")),format.raw/*56.47*/(""" """),format.raw/*56.48*/("""internas y externas a las que serán asignados los equipos de disponibles. 
								Tener presente que la diferencia entre """),_display_(/*57.49*/mapDiccionario/*57.63*/.get("Bodega")),format.raw/*57.77*/(""" """),format.raw/*57.78*/("""interna y externa es: solo las """),_display_(/*57.110*/mapDiccionario/*57.124*/.get("Bodegas")),format.raw/*57.139*/(""" """),format.raw/*57.140*/("""externas 
								son unidades a las cuales se cobra """),_display_(/*58.45*/mapDiccionario/*58.59*/.get("Arriendo")),format.raw/*58.75*/(""" """),format.raw/*58.76*/("""(en general son proyectos).
						</P>
					<br>
					<h2><b>8- Noveno Paso: en menú Movimientos→Ingresar:</b></h2>
						<p class="text-justify" style="font-size:14;">
								Comenzar con el ingreso de movimientos entre """),_display_(/*63.55*/mapDiccionario/*63.69*/.get("Bodegas")),format.raw/*63.84*/(""".
						</p>
					<br><br><br>
			</div>
		</div>
	</div>
	

	
""")))}),format.raw/*72.2*/("""




"""),format.raw/*77.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*78.31*/("""{"""),format.raw/*78.32*/("""
		  """),format.raw/*79.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*80.2*/("""}"""),format.raw/*80.3*/(""");
	
	
</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu) => apply(mapDiccionario,mapPermiso,userMnu)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuAyuda/manualPrimerosPasos.scala.html
                  HASH: d91358163f72e024988989cc91a6616d0ad5c085
                  MATRIX: 1016->1|1206->98|1239->106|1255->114|1294->116|1322->119|1390->167|1418->169|1494->220|1566->272|1595->275|3189->1842|3212->1856|3247->1870|3276->1871|3911->2479|3934->2493|3971->2509|4000->2510|4101->2584|4124->2598|4160->2613|4201->2627|4224->2641|4261->2656|4369->2737|4392->2751|4428->2766|4457->2767|4607->2890|4630->2904|4665->2918|4694->2919|4754->2951|4778->2965|4815->2980|4845->2981|4926->3035|4949->3049|4986->3065|5015->3066|5265->3289|5288->3303|5324->3318|5418->3382|5450->3387|5540->3449|5569->3450|5601->3455|5694->3521|5722->3522
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|70->39|70->39|70->39|70->39|82->51|82->51|82->51|82->51|85->54|85->54|85->54|85->54|85->54|85->54|87->56|87->56|87->56|87->56|88->57|88->57|88->57|88->57|88->57|88->57|88->57|88->57|89->58|89->58|89->58|89->58|94->63|94->63|94->63|103->72|108->77|109->78|109->78|110->79|111->80|111->80
                  -- GENERATED --
              */
          