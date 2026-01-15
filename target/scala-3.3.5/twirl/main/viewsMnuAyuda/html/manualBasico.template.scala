
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

object manualBasico extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[Map[String,String],Map[String,String],utilities.UserMnu,play.twirl.api.HtmlFormat.Appendable] {

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
				<h2><b><u>DESCRIPCIÓN DEL MENU</u></b></h2><br>
				<!-- <img src="routes.Assets.at("images/menu.png")" width="auto" height="auto" /> -->
				<p class="text-uppercase font-weight-bold" style="float: left"><img src='/viewImg/menu.png' width="auto"></p>
			</div>
			<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
				<br><br>
					<h2><b><u>COMPRAS</u></b></h2>
					<h3>
						<p class="text-justify" style="font-size:16">
							Módulo de Compras, permite ingresar y confirmar compras 
							de equipos nuevos o ingresar equipos de fabricación propia o que se desconoce su compra, 
							por ejemplo al inicio se debe ingresar los inventarios iniciales.
						</p>
					</h3>
					<h2>1- En menú COMPRAS→Ingresar Compras:</h2>
						<p class="text-justify" style="font-size:14;">
								Se deben ingresar todos los equipos que se van sumando a través de las compras nuevas o 
								todo el inventario inicial que se desea controlar en las distintas unidades de negocio. Este ingreso 
								obliga asignar una """),_display_(/*29.29*/mapDiccionario/*29.43*/.get("Bodega")),format.raw/*29.57*/(""" """),format.raw/*29.58*/("""interna de destino, no es posible enviar una compra directamente a una """),_display_(/*29.130*/mapDiccionario/*29.144*/.get("Bodega")),format.raw/*29.158*/(""" 
								"""),format.raw/*30.9*/("""externa. En el ingreso inicial, solo quedan registrados como compras, no pasarán a las existencias en 
								"""),_display_(/*31.10*/mapDiccionario/*31.24*/.get("Bodegas")),format.raw/*31.39*/(""" """),format.raw/*31.40*/("""mientras estas no sean confirmadas. Es factible además incorporar un archivo de 
								respaldo en formato PDF.
						</p>
					<h2>2- En menú COMPRAS→Confirmar Compras:</h2>
						<p class="text-justify" style="font-size:14;">
								A través de esta opción del menú, se accede a confirmar los equipos ingresados en punto 1 anterior, 
								esta confirmación es por cada línea ingresada de una o más compras. Es factible confirmar selectivamente 
								todas o solo algunas líneas de una compra ingresada. Al confirmar, pasan los equipos seleccionados a los 
								inventarios de """),_display_(/*39.25*/mapDiccionario/*39.39*/.get("Bodegas")),format.raw/*39.54*/(""", y estas líneas ya no pueden ser eliminadas o modificadas del registro de compra, 
								solo se permite actualizar la moneda de compra y su valor unitario.
						</p>
					<h2>3- En menú COMPRAS→Modificar Documentos:</h2>
						<p class="text-justify" style="font-size:14;">
								Opción que permite modificar o eliminar líneas de registradas en el ingreso de las compras. 
								Solo es factible eliminar y/o cambiar las cantidades siempre y cuando la línea no haya sido 
								confirmada. Una vez confirmada solo es posible modificar moneda y precio de compra.
						</p>
					<h2>4- En menú COMPRAS→Listar Compras:</h2>
						<p class="text-justify" style="font-size:14;">
								Listado de compras ingresadas.
						</p>
					<br><br>
					<h2><b><u>MOVIMIENTOS</u></b></h2>
					<h3>
						<p class="text-justify" style="font-size:16">
							Módulo de Movimientos, permite hacer distribución de los equipos existentes a las """),_display_(/*56.91*/mapDiccionario/*56.105*/.get("Bodegas")),format.raw/*56.120*/(""" """),format.raw/*56.121*/("""correspondientes, 
							es factible hacer cualquier traslado de los mismos entre """),_display_(/*57.66*/mapDiccionario/*57.80*/.get("Bodegas")),format.raw/*57.95*/(""". Los movimientos no son borrables, en caso 
							de error se debe ingresar un nuevo movimiento para corregir.
						</p>
					</h3>
					<h2>1- En menú MOVIMIENTOS→Ingresar:</h2>
						<p class="text-justify" style="font-size:14;">
								A través de esta opción del menú, se realizan todos los movimientos de equipos entre """),_display_(/*63.95*/mapDiccionario/*63.109*/.get("Bodegas")),format.raw/*63.124*/(""", pueden 
								ser entre distintos tipos de """),_display_(/*64.39*/mapDiccionario/*64.53*/.get("Bodega")),format.raw/*64.67*/(""", internas o externas, o entre los mismos tipos. En caso de error 
								se debe ingresar un nuevo movimiento para corregir.
						</p>
					<h2>2- En menú MOVIMIENTOS→Listar Movimientos:</h2>
						<p class="text-justify" style="font-size:14;">
								Listado de movimientos efectuados, a través de la selección de uno de ellos es posible imprimir para 
								acompañar a la guía de despacho respectiva.
						</p>
					<br><br>	
					<h2><b><u>BAJAS</u></b></h2>
					<h3>
						<p class="text-justify" style="font-size:16">
							Módulo de Bajas, permite ingresar actas de bajas y confirmar bajas de equipos por línea del acta ingresado.
						</p>
					</h3>
					<h2>1- En menú BAJAS→Ingresar Actas:</h2>
						<p class="text-justify" style="font-size:14;">
								En esta opción se ingresan actas de baja, solo es posible incorporar equipos que estén asignados en 
								"""),_display_(/*82.10*/mapDiccionario/*82.24*/.get("Bodega")),format.raw/*82.38*/(""" """),format.raw/*82.39*/("""de bajas. La asignación de equipos a """),_display_(/*82.77*/mapDiccionario/*82.91*/.get("Bodega")),format.raw/*82.105*/(""" """),format.raw/*82.106*/("""de baja se hace a través del módulo de MOVIMENTOS. 
								Una vez asignado un equipo al acta de baja, ya no es posible hacer un movimiento de este equipo desde 
								"""),_display_(/*84.10*/mapDiccionario/*84.24*/.get("Bodega")),format.raw/*84.38*/(""" """),format.raw/*84.39*/("""de baja a otra """),_display_(/*84.55*/mapDiccionario/*84.69*/.get("Bodega")),format.raw/*84.83*/(""", para hacerlo solo es factible eliminando la línea del acta siempre y 
								cuando esta no este confirmada.
						</p>
					<h2>2- En menú BAJAS→Confirmar Bajas:</h2>
						<p class="text-justify" style="font-size:14;">
								A través de esta opción del menú, se accede a confirmar las bajas ingresadas en punto 1 anterior, 
								esta confirmación es por cada línea ingresada del acta. Es factible confirmar selectivamente solo 
								algunas líneas del acta ingresada. Al confirmar, los equipos seleccionados son retirados de los 
								inventarios de """),_display_(/*92.25*/mapDiccionario/*92.39*/.get("Bodegas")),format.raw/*92.54*/(""", y estas líneas ya no pueden ser eliminadas o modificadas del registro del 
								acta.
						</p>
					<h2>3- En menú BAJAS→Modificar Actas:</h2>
						<p class="text-justify" style="font-size:14;">
								Opción que permite modificar o eliminar líneas de registradas en una acta de baja determinada. 
								Solo es factible eliminar la línea seleccionada siempre y cuando la línea no haya sido confirmada.
						</p>
					<h2>4- En menú BAJAS→Listar Bajas:</h2>
						<p class="text-justify" style="font-size:14;">
								Listado de bajas ingresadas.
						</p>
					<br><br>
					<h2><b><u>"""),_display_(/*105.17*/mapDiccionario/*105.31*/.get("BODEGAS")),format.raw/*105.46*/("""</u></b></h2>
					<h3>
						<p class="text-justify" style="font-size:16">
							Módulo de """),_display_(/*108.19*/mapDiccionario/*108.33*/.get("Bodegas")),format.raw/*108.48*/(""", permite crear, modificar y administrar """),_display_(/*108.90*/mapDiccionario/*108.104*/.get("Bodegas")),format.raw/*108.119*/(""" """),format.raw/*108.120*/("""y sus listas de precio asociadas.
						</p>
					</h3>
					<h2>1- En menú """),_display_(/*111.22*/mapDiccionario/*111.36*/.get("BODEGAS")),format.raw/*111.51*/("""→Administrar """),_display_(/*111.65*/mapDiccionario/*111.79*/.get("Bodegas")),format.raw/*111.94*/(""":</h2>
						<p class="text-justify" style="font-size:14;">
								En esta opción crean y administran """),_display_(/*113.45*/mapDiccionario/*113.59*/.get("Bodegas")),format.raw/*113.74*/(""" """),format.raw/*113.75*/("""tanto internas como externas, es factible modificar nombres. 
								Una vez creada una """),_display_(/*114.29*/mapDiccionario/*114.43*/.get("Bodega")),format.raw/*114.57*/(""", no es posible cambiar si es externa o interna, en este caso solo es posible 
								crear una nueva """),_display_(/*115.26*/mapDiccionario/*115.40*/.get("Bodega")),format.raw/*115.54*/(""". La eliminación de """),_display_(/*115.75*/mapDiccionario/*115.89*/.get("Bodegas")),format.raw/*115.104*/(""" """),format.raw/*115.105*/("""solo es factible mientras no existan movimientos 
								que hayan asignado algún equipo a la misma en alguna oportunidad.
						</p>
					<h2>2- En menú """),_display_(/*118.22*/mapDiccionario/*118.36*/.get("BODEGAS")),format.raw/*118.51*/("""→Asignar Descuentos:</h2>
						<p class="text-justify" style="font-size:14;">
								A través de esta opción del menú, es posible asignar descuentos adicionales sobre la lista de precios 
								asignada a """),_display_(/*121.21*/mapDiccionario/*121.35*/.get("Bodega")),format.raw/*121.49*/(""" """),format.raw/*121.50*/("""determinada. La opción de descuentos solo es posible con """),_display_(/*121.108*/mapDiccionario/*121.122*/.get("Bodegas")),format.raw/*121.137*/(""" """),format.raw/*121.138*/("""externas. Estos 
								descuentos se definen como global para toda """),_display_(/*122.54*/mapDiccionario/*122.68*/.get("Bodega")),format.raw/*122.82*/(""", selectivo por grupos o selectivo por cada equipo.
						</p>
					<h2>3- En menú """),_display_(/*124.22*/mapDiccionario/*124.36*/.get("BODEGAS")),format.raw/*124.51*/("""→Precios por """),_display_(/*124.65*/mapDiccionario/*124.79*/.get("Bodega")),format.raw/*124.93*/(""":</h2>
						<p class="text-justify" style="font-size:14;">
								Opción que permite modificar o agregar precios por equipos. La lista de precios de """),_display_(/*126.93*/mapDiccionario/*126.107*/.get("Bodega")),format.raw/*126.121*/(""" """),format.raw/*126.122*/("""se hereda 
								de la lista general de precios, si no existe en la lista general, caso que puede ocurrir cuándo un 
								equipo es nuevo y aún no ha sido incorporado a la lista general, automáticamente se agrega a la lista 
								particular de """),_display_(/*129.24*/mapDiccionario/*129.38*/.get("Bodega")),format.raw/*129.52*/(""" """),format.raw/*129.53*/("""y a la general con valor uno tanto en reposicion como """),_display_(/*129.108*/mapDiccionario/*129.122*/.get("Arriendo")),format.raw/*129.138*/(""".
						</p>
					<br><br>
					<h2><b><u>TABLAS</u></b></h2>
					<h3>
						<p class="text-justify" style="font-size:16">
							Módulo de Bajas, permite ingresar actas de bajas y confirmar bajas de equipos por línea del acta ingresado.
						</p>
					<h2>1- En menú TABLAS→Maestro de Precios:</h2>
						<p class="text-justify" style="font-size:14;">
								Mantención de la lista de precios general de la cual """),_display_(/*139.63*/mapDiccionario/*139.77*/.get("Bodegas")),format.raw/*139.92*/(""" """),format.raw/*139.93*/("""heredan precios cada vez que se incorpora 
								un equipo nuevo a la misma.
						</p>
					<h2>2- En menú TABLAS→Grupos:</h2>
						<p class="text-justify" style="font-size:14;">
								Administración, mantención y creación de grupos en los cuales son clasificados los equipos.
						</p>
					<h2>3- En menú TABLAS→Equipos:</h2>
						<p class="text-justify" style="font-size:14;">
								Administración, mantención y creación de equipos.
						</p>
					<h2>4- En menú TABLAS→Proyectos:</h2>
						<p class="text-justify" style="font-size:14;">
								Administración, mantención y creación de proyectos, aplicable solo a """),_display_(/*152.79*/mapDiccionario/*152.93*/.get("Bodegas")),format.raw/*152.108*/(""" """),format.raw/*152.109*/("""externas.
						</p>
					<h2>5- En menú TABLAS→Clientes:</h2>
						<p class="text-justify" style="font-size:14;">
								Administración, mantención y creación de clientes internos y externos.
						</p>
					<h2>6- En menú TABLAS→Proveedores:</h2>
						<p class="text-justify" style="font-size:14;">
								Administración, mantención y creación de proveedores, aplicable solo a equipos.
						</p>
					<h2>7- En menú TABLAS→Fabricantes:</h2>
						<p class="text-justify" style="font-size:14;">
								Administración, mantención y creación de fabricantes, aplicable solo a equipos.
						</p>
					<h2>8- En menú TABLAS→Cantidad de Decimales:</h2>
						<p class="text-justify" style="font-size:14;">
								Definición de la cantidad de decimales para los cálculos de cada moneda.
						</p>
					<h2>9- En menú TABLAS→Usuarios/Perfiles:</h2>
						<p class="text-justify" style="font-size:14;">
								Administración, mantención y creación de usuarios y su asignación de perfiles creados.
						</p>
			</div>
		</div>
	</div>
	
""")))}),format.raw/*178.2*/("""




"""),format.raw/*183.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*184.31*/("""{"""),format.raw/*184.32*/("""
		  """),format.raw/*185.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*186.2*/("""}"""),format.raw/*186.3*/(""");
	

	
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
                  SOURCE: app/viewsMnuAyuda/manualBasico.scala.html
                  HASH: 3aed8954826018e5b7e289791338fd29c76a587e
                  MATRIX: 1009->1|1199->98|1232->106|1248->114|1287->116|1315->119|1383->167|1411->169|1487->220|1559->272|1588->275|2746->1406|2769->1420|2804->1434|2833->1435|2933->1507|2957->1521|2993->1535|3030->1545|3169->1657|3192->1671|3228->1686|3257->1687|3875->2278|3898->2292|3934->2307|4903->3249|4927->3263|4964->3278|4994->3279|5105->3363|5128->3377|5164->3392|5520->3721|5544->3735|5581->3750|5656->3798|5679->3812|5714->3826|6628->4713|6651->4727|6686->4741|6715->4742|6780->4780|6803->4794|6839->4808|6869->4809|7068->4981|7091->4995|7126->5009|7155->5010|7198->5026|7221->5040|7256->5054|7850->5621|7873->5635|7909->5650|8538->6251|8562->6265|8599->6280|8721->6374|8745->6388|8782->6403|8852->6445|8877->6459|8915->6474|8946->6475|9051->6552|9075->6566|9112->6581|9154->6595|9178->6609|9215->6624|9347->6728|9371->6742|9408->6757|9438->6758|9556->6848|9580->6862|9616->6876|9748->6980|9772->6994|9808->7008|9857->7029|9881->7043|9919->7058|9950->7059|10134->7215|10158->7229|10195->7244|10433->7454|10457->7468|10493->7482|10523->7483|10610->7541|10635->7555|10673->7570|10704->7571|10802->7641|10826->7655|10862->7669|10974->7753|10998->7767|11035->7782|11077->7796|11101->7810|11137->7824|11317->7976|11342->7990|11379->8004|11410->8005|11691->8258|11715->8272|11751->8286|11781->8287|11865->8342|11890->8356|11929->8372|12374->8789|12398->8803|12435->8818|12465->8819|13124->9450|13148->9464|13186->9479|13217->9480|14288->10520|14321->10525|14412->10587|14442->10588|14475->10593|14569->10659|14598->10660
                  LINES: 28->1|33->2|35->4|35->4|35->4|37->6|37->6|38->7|39->8|39->8|40->9|60->29|60->29|60->29|60->29|60->29|60->29|60->29|61->30|62->31|62->31|62->31|62->31|70->39|70->39|70->39|87->56|87->56|87->56|87->56|88->57|88->57|88->57|94->63|94->63|94->63|95->64|95->64|95->64|113->82|113->82|113->82|113->82|113->82|113->82|113->82|113->82|115->84|115->84|115->84|115->84|115->84|115->84|115->84|123->92|123->92|123->92|136->105|136->105|136->105|139->108|139->108|139->108|139->108|139->108|139->108|139->108|142->111|142->111|142->111|142->111|142->111|142->111|144->113|144->113|144->113|144->113|145->114|145->114|145->114|146->115|146->115|146->115|146->115|146->115|146->115|146->115|149->118|149->118|149->118|152->121|152->121|152->121|152->121|152->121|152->121|152->121|152->121|153->122|153->122|153->122|155->124|155->124|155->124|155->124|155->124|155->124|157->126|157->126|157->126|157->126|160->129|160->129|160->129|160->129|160->129|160->129|160->129|170->139|170->139|170->139|170->139|183->152|183->152|183->152|183->152|209->178|214->183|215->184|215->184|216->185|217->186|217->186
                  -- GENERATED --
              */
          