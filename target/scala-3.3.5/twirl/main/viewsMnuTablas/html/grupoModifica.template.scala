
package viewsMnuTablas.html

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

object grupoModifica extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template5[Map[String,String],Map[String,String],utilities.UserMnu,tables.Grupo,List[tables.Atributo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
grupo: tables.Grupo, listAtributosGrupo: List[tables.Atributo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
    	
"""),_display_(/*5.2*/main("")/*5.10*/ {_display_(Seq[Any](format.raw/*5.12*/("""

"""),_display_(/*7.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*7.50*/("""
	"""),format.raw/*8.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*9.4*/barraTitulo(mapDiccionario, "MODIFICAR/ELIMINAR GRUPO Y/O ATRIBUTOS ASIGNADOS", "")),format.raw/*9.87*/("""
		"""),format.raw/*10.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				<table class="table table-sm table-bordered table-condensed table-fluid">
					<tr>
						<td>
							<div class="row">
								<div class="col-xs-6 col-sm-2 col-md-2 col-lg-2">
									<b><font color="#008000">GRUPO:</font></b>
								</div>
								<div class="col-xs-6 col-sm-10 col-md-10 col-lg-10">
									<input type="hidden" name="id_grupo" value=""""),_display_(/*20.55*/grupo/*20.60*/.getId()),format.raw/*20.68*/("""">
									<input type="text" class="form-control"
										id="nombre"
										maxlength="50"
										onkeydown="return sinReservados(window.event)"
										value=""""),_display_(/*25.19*/grupo/*25.24*/.getNombre()),format.raw/*25.36*/(""""
										onchange="value = value.trim(); modificarGrupo(id)">
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<table class="table table-sm table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<tr>
										<th colspan="10" style="text-align:left">
											<font color="#008000"> LISTA DE ATRIBUTOS: </font>
										</th>
									</tr>
									<TR> 
										<TH style="vertical-align: top;">ATRIBUTO</TH>
										<TH style= "text-align: center;">UNIDAD</TH>
										<TH style= "text-align: center;">NUMERICO</TH>
										<TH style= "text-align: center;">DEL</TH>
									</TR>
								</thead>
								<tbody>
									"""),_display_(/*48.11*/for(lista1 <- listAtributosGrupo) yield /*48.44*/{_display_(Seq[Any](format.raw/*48.45*/("""
										"""),format.raw/*49.11*/("""<TR>
											<td  style="text-align:left;">"""),_display_(/*50.43*/lista1/*50.49*/.getAtributo()),format.raw/*50.63*/("""</td>
											<td  style="text-align:left;">"""),_display_(/*51.43*/lista1/*51.49*/.getUnidad()),format.raw/*51.61*/("""</td>
											<td style="text-align:center;">
										    	"""),_display_(if(lista1.getEsNumerico()==1)/*53.46*/{_display_(Seq[Any](format.raw/*53.47*/("""SI""")))} else {null} ),format.raw/*53.50*/("""
										    	"""),_display_(if(lista1.getEsNumerico()==0)/*54.46*/{_display_(Seq[Any](format.raw/*54.47*/("""NO""")))} else {null} ),format.raw/*54.50*/("""
											"""),format.raw/*55.12*/("""</td>
											<td  style="text-align:center;">
												<form id="form_"""),_display_(/*57.29*/grupo/*57.34*/.id),_display_(/*57.38*/lista1/*57.44*/.getId()),format.raw/*57.52*/("""" method="post" action="/grupoEliminaAtributo/">
													<input type="hidden" name="id_grupo" value=""""),_display_(/*58.59*/grupo/*58.64*/.id),format.raw/*58.67*/("""">
													<input type="hidden" name="id_atributo" value=""""),_display_(/*59.62*/lista1/*59.68*/.getId()),format.raw/*59.76*/("""">
													<a href="#" onclick='document.getElementById("form_"""),_display_(/*60.66*/grupo/*60.71*/.id),_display_(/*60.75*/lista1/*60.81*/.getId()),format.raw/*60.89*/("""").submit();'>
														<kbd style="background-color: red">X</kbd>
													</a>
												</form>
											</td>
										</TR>
						 			""")))}),format.raw/*66.12*/("""
								"""),format.raw/*67.9*/("""</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan=4>
							<div class="row justify-content-md-center" >
								<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3">
									<input type="button" value="Agregar Atributo" class="btn btn-info btn-sm rounded-pill btn-block" 
										onclick="location.href='/grupoNuevoAtributo/"""),_display_(/*76.56*/grupo/*76.61*/.id),format.raw/*76.64*/("""'">
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/grupoMantencion/';">
				</div>
			</div>
	</div>
	
""")))}),format.raw/*92.2*/("""




"""),format.raw/*97.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*98.31*/("""{"""),format.raw/*98.32*/("""
		  """),format.raw/*99.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*100.2*/("""}"""),format.raw/*100.3*/(""");
	
	const modificarGrupo = (campo) => """),format.raw/*102.36*/("""{"""),format.raw/*102.37*/("""
		"""),format.raw/*103.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_grupo','"""),_display_(/*106.34*/grupo/*106.39*/.getId()),format.raw/*106.47*/("""');
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*108.16*/("""{"""),format.raw/*108.17*/("""
            """),format.raw/*109.13*/("""url: "/modificaGrupoPorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*116.36*/("""{"""),format.raw/*116.37*/("""
	     		"""),format.raw/*117.9*/("""if(respuesta=="existe")"""),format.raw/*117.32*/("""{"""),format.raw/*117.33*/("""
	     			"""),format.raw/*118.10*/("""alertify.alert('El nombre de grupo ya existe, intente con otro', function () """),format.raw/*118.87*/("""{"""),format.raw/*118.88*/("""
	     				"""),format.raw/*119.11*/("""$("#nombre").val(""""),_display_(/*119.30*/grupo/*119.35*/.getNombre()),format.raw/*119.47*/("""");
		     		"""),format.raw/*120.10*/("""}"""),format.raw/*120.11*/(""");
	     		"""),format.raw/*121.9*/("""}"""),format.raw/*121.10*/("""else if(respuesta=="error")"""),format.raw/*121.37*/("""{"""),format.raw/*121.38*/("""
	     			"""),format.raw/*122.10*/("""alertify.alert(msgError, function () """),format.raw/*122.47*/("""{"""),format.raw/*122.48*/("""
		     			"""),format.raw/*123.11*/("""location.href = "/";
		     		"""),format.raw/*124.10*/("""}"""),format.raw/*124.11*/(""");
	     		"""),format.raw/*125.9*/("""}"""),format.raw/*125.10*/("""
	     	"""),format.raw/*126.8*/("""}"""),format.raw/*126.9*/("""
        """),format.raw/*127.9*/("""}"""),format.raw/*127.10*/(""");
	"""),format.raw/*128.2*/("""}"""),format.raw/*128.3*/("""
	
"""),format.raw/*130.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,grupo:tables.Grupo,listAtributosGrupo:List[tables.Atributo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,grupo,listAtributosGrupo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Grupo,List[tables.Atributo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,grupo,listAtributosGrupo) => apply(mapDiccionario,mapPermiso,userMnu,grupo,listAtributosGrupo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuTablas/grupoModifica.scala.html
                  HASH: a3719d582467be3d88aade0326ac136b7d476131
                  MATRIX: 1046->1|1300->162|1333->170|1349->178|1388->180|1416->183|1484->231|1512->233|1588->284|1691->367|1721->370|2211->833|2225->838|2254->846|2455->1020|2469->1025|2502->1037|3251->1759|3300->1792|3339->1793|3378->1804|3452->1851|3467->1857|3502->1871|3577->1919|3592->1925|3625->1937|3746->2031|3785->2032|3832->2035|3905->2081|3944->2082|3991->2085|4031->2097|4136->2175|4150->2180|4174->2184|4189->2190|4218->2198|4352->2305|4366->2310|4390->2313|4481->2377|4496->2383|4525->2391|4620->2459|4634->2464|4658->2468|4673->2474|4702->2482|4887->2636|4923->2645|5301->2996|5315->3001|5339->3004|5756->3391|5788->3396|5878->3458|5907->3459|5939->3464|6033->3530|6062->3531|6131->3571|6161->3572|6192->3575|6354->3709|6369->3714|6399->3722|6483->3777|6513->3778|6555->3791|6822->4029|6852->4030|6889->4039|6941->4062|6971->4063|7010->4073|7116->4150|7146->4151|7186->4162|7233->4181|7248->4186|7282->4198|7324->4211|7354->4212|7393->4223|7423->4224|7479->4251|7509->4252|7548->4262|7614->4299|7644->4300|7684->4311|7743->4341|7773->4342|7812->4353|7842->4354|7878->4362|7907->4363|7944->4372|7974->4373|8006->4377|8035->4378|8066->4381
                  LINES: 28->1|34->3|36->5|36->5|36->5|38->7|38->7|39->8|40->9|40->9|41->10|51->20|51->20|51->20|56->25|56->25|56->25|79->48|79->48|79->48|80->49|81->50|81->50|81->50|82->51|82->51|82->51|84->53|84->53|84->53|85->54|85->54|85->54|86->55|88->57|88->57|88->57|88->57|88->57|89->58|89->58|89->58|90->59|90->59|90->59|91->60|91->60|91->60|91->60|91->60|97->66|98->67|107->76|107->76|107->76|123->92|128->97|129->98|129->98|130->99|131->100|131->100|133->102|133->102|134->103|137->106|137->106|137->106|139->108|139->108|140->109|147->116|147->116|148->117|148->117|148->117|149->118|149->118|149->118|150->119|150->119|150->119|150->119|151->120|151->120|152->121|152->121|152->121|152->121|153->122|153->122|153->122|154->123|155->124|155->124|156->125|156->125|157->126|157->126|158->127|158->127|159->128|159->128|161->130
                  -- GENERATED --
              */
          