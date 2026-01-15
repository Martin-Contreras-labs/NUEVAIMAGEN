
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

object madaApis extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("")/*3.10*/ {_display_(Seq[Any](format.raw/*3.12*/("""



	"""),format.raw/*7.2*/("""<br><br>
	<div class="row justify-content-md-center" >
		<div class="col-xs-12 col-sm-11 col-md-11 col-lg-11">
		
			<h2>MADA - APIs (ejemplo con DEMO)</h2>
			<br>
			<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
				<tbody>
					<TR>
						<th  style="text-align:left;">AUTENTICACION</th>
						<td  style="text-align:left;">
							Credenciales: Demo:z5BI%OImmQ#2<br>
							Encode base 64 : RGVtbzp6NUJJJU9JbW1RIzI=<br>
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Basic &lt;json credenciales base 64&gt;' 'https://madainqsol.com/authenticar/'<br>
							Retorna: json con token<br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">TESTEO TOKEN</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/pingToken/'<br>
							Retorna: json<br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">1- LISTADO DE BODEGAS</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/listadoDeBodegas/'<br>
							Retorna: json con la lista de bodegas internas y bodegas cliente vigentes y no vigentes<br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">2- MOVIMIENTOS ENTRE FECHAS</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/movimientosEntreFechas/?desdeAAMMDD=2021-12-01&hastaAAMMDD=2021-12-31'<br>
							Retorna: json con todos los movimientos de traslado (entradas y salidas bodegas internas) ymovimientos de arriendo y venta (bodegas cliente)<br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">3- FIND BODEGA</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/findBodega/?id_bodegaEmpresa=XX'<br>
							Retorna: json<br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">4- FIND CLIENTE</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/findCliente/?id_cliente=XX'<br>
							Retorna: json<br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">5- FIND PROYECTO</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/findProyecto/?id_proyecto=XX'<br>
							Retorna: json<br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">6- INVENTARIOS</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/inventarios_al_dia/?fechaCorte=2021-12-31'<br>
							Retorna: jsoncon los inventarios a la fecha indicada de la existencia tanto en bodegas internas como en bodegas cliente<br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">7- ESTADOS DE PAGO (por periodo)</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/estadoDePagoPorPeriodo/?desdeAAMMDD=2021-12-01&hastaAAMMDD=2021-12-31&uf=31200&usd=810&eur=930'<br>
							Retorna: json con los estados de pago bodegas cliente, más los inventarios iniciales, movimientos del período y ajustes, que componen los estados de pago<br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">8- RESUMEN ESTADOS DE PAGO (por periodo)</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/totalesEPporPeriodo/?desdeAAMMDD=2021-12-01&hastaAAMMDD=2021-12-31&uf=31200&usd=810&eur=930'<br>
							Retorna: json con una lista resumen de estados de pago con los totales por bodegas cliente<br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">9- MATRIZ INVENTARIO (por cotizacion)</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/matrizInventarioPorCoti/?fechaCorte=2021-12-01'<br>
							Retorna: json con una Matriz de Inventarios por Cotizacion (detalle por equipo, bodega y precios)<br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">10- CONSOLIDADO (detalle por grupos)</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/consolidadoPorGrupoMeses/?fechaCorte=2021-12-01&cantMeses=3'<br>
							Retorna: json con el consolidado de proformas por grupo (no considera ajustes a EP) <br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">11- CONSOLIDADO (detalle por equipos)</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/consolidadoPorEquipoMeses/?fechaCorte=2021-12-01&cantMeses=3'<br>
							Retorna: json con el consolidado de proformas por grupo (no considera ajustes a EP) <br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">12- LISTADO DE AJUSTES POR PERIODO</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/ajustesPorPeriodo/?desdeAAMMDD=2021-12-01&hastaAAMMDD=2021-12-31'<br>
							Retorna: json con la lista de ajustes aplicados por periodo <br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">13- ESTADO BODEGAS</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/estadoBodegasJson/'<br>
							Retorna: json con los estados de bodegas (solo bodegas externas) <br>
						</td>
					</TR>
					<TR>
						<th  style="text-align:left;">14- RESUMEN POR PERIODOS</th>
						<td  style="text-align:left;">
							Llamada: curl -X POST -H 'Content-type:application/json' -H 'Authorization: Bearer &lt;token&gt;' 'https://madainqsol.com/hoheTodoResumenJson/?desdeAAMMDD=2021-12-01&hastaAAMMDD=2021-12-31'<br>
							Retorna: json con un resumen de movimientos entre bodegas valorizado<br>
						</td>
					</TR>
				</tbody>
			</table>
		</div>
	</div>	
	                    	
	                    	
	     



""")))}),format.raw/*139.2*/("""

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
                  SOURCE: app/views/madaApis.scala.html
                  HASH: 07482877ef28ee7d695c03bc0add65846c7c04b4
                  MATRIX: 941->1|1037->4|1064->6|1080->14|1119->16|1150->21|8070->6910
                  LINES: 28->1|33->2|34->3|34->3|34->3|38->7|170->139
                  -- GENERATED --
              */
          