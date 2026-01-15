
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
/*1.2*/import models.utilities.Fechas

object qrRevisarDatos extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template6[Map[String,String],Map[String,String],utilities.UserMnu,tables.Equipo,List[List[String]],List[qr.QrTransacEquipo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
equipo: tables.Equipo, atribEquipo: List[List[String]], listaTransac: List[qr.QrTransacEquipo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

"""),_display_(/*8.2*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.50*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario,equipo.codigo.toUpperCase()+" -- "+equipo.nombre.toUpperCase(),"REVISAR DATOS DE EQUIPO -- CODIFICACION QR")),format.raw/*10.139*/("""
		"""),format.raw/*11.3*/("""<div class="row  justify-content-md-center">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table class="table table-sm table-hover table-bordered table-condensed table-fluid">
					<tr>
						<td width="49%" style="vertical-align:top">
							<table id="tablaAtribEquipo" class="table table-sm table-hover table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<TR>
										<th colspan="3">ATRIBUTOS ASOCIADOS AL EQUIPO</th>
									</TR>
									<TR>
										<th>ATRIBUTO</th>
										<th>VALOR</th>
										<th>PUBLICAR</th>
									</TR>
								</thead>
								<tbody>
								
									<tr>
										<td style="text-align: left;">Peso (KG)</td>
										<td style="text-align: left;">"""),_display_(/*31.42*/equipo/*31.48*/.getKg()),format.raw/*31.56*/("""</td>
										<td style="text-align: left;"></td>
									</tr>
									<tr>
										<td style="text-align: left;">Area (M2)</td>
										<td style="text-align: left;">"""),_display_(/*36.42*/equipo/*36.48*/.getM2()),format.raw/*36.56*/("""</td>
										<td style="text-align: left;"></td>
									</tr>

									
									"""),_display_(/*41.11*/for(lista <- atribEquipo) yield /*41.36*/{_display_(Seq[Any](format.raw/*41.37*/("""
										"""),format.raw/*42.11*/("""<TR>
											<td style="text-align: left;">"""),_display_(/*43.43*/lista/*43.48*/.get(0)),format.raw/*43.55*/("""</td>
											<td style="text-align: left;">"""),_display_(/*44.43*/lista/*44.48*/.get(1)),format.raw/*44.55*/("""</td>
									        <td style="text-align:center">
												<div id="cambiarEstadoPubEquipo_"""),_display_(/*46.46*/lista/*46.51*/.get(2)),format.raw/*46.58*/("""_"""),_display_(/*46.60*/lista/*46.65*/.get(3)),format.raw/*46.72*/("""">
													"""),_display_(if(lista.get(4).equals("1"))/*47.43*/{_display_(Seq[Any](format.raw/*47.44*/("""
														"""),format.raw/*48.15*/("""<a href="#" onclick= "qrCambiarPubEquipo('"""),_display_(/*48.58*/lista/*48.63*/.get(2)),format.raw/*48.70*/("""','"""),_display_(/*48.74*/lista/*48.79*/.get(3)),format.raw/*48.86*/("""','"""),_display_(/*48.90*/lista/*48.95*/.get(4)),format.raw/*48.102*/("""')">
															<kbd style="background-color: green">SI</kbd>
														</a>
										        	""")))} else {null} ),format.raw/*51.21*/("""
										        	"""),_display_(if(lista.get(4).equals("0"))/*52.49*/{_display_(Seq[Any](format.raw/*52.50*/("""
														"""),format.raw/*53.15*/("""<a href="#" onclick= "qrCambiarPubEquipo('"""),_display_(/*53.58*/lista/*53.63*/.get(2)),format.raw/*53.70*/("""','"""),_display_(/*53.74*/lista/*53.79*/.get(3)),format.raw/*53.86*/("""','"""),_display_(/*53.90*/lista/*53.95*/.get(4)),format.raw/*53.102*/("""')">
															<kbd style="background-color: red">NO</kbd>
														</a>
										        	""")))} else {null} ),format.raw/*56.21*/("""
												"""),format.raw/*57.13*/("""</div>
								        	</td>
										</TR>
									""")))}),format.raw/*60.11*/("""
								"""),format.raw/*61.9*/("""</tbody>
							</table>
						</td>
						<td width="5px"></td>
						<td width="49%"  style="vertical-align:top">
							<table id="tablaAtribQr" class="table table-sm table-hover table-bordered table-condensed table-fluid">
								<thead style="background-color: #eeeeee">
									<TR>
										<th colspan="4">ATRIBUTOS ASOCIADOS AL QR</th>
									</TR>
									<TR>
										<th colspan="4">
											<form method="post" action="/qrEditEquipo/">
												<input type="hidden" name="id_equipo" value=""""),_display_(/*74.59*/equipo/*74.65*/.id),format.raw/*74.68*/("""">
											<button type="submit" class="btn btn-success btn-mini">Administrar Atributo QR</button>
										</th>
									</TR>
									<TR>
										<th>ATRIBUTO</th>
										<th>VALOR</th>
										<th>FECHA DE<br>VENCIMIENTO</th>
										<th>PUBLICAR</th>
									</TR>
								</thead>
								<tbody>
									"""),_display_(/*86.11*/for(lista <- listaTransac) yield /*86.37*/{_display_(Seq[Any](format.raw/*86.38*/("""
										"""),format.raw/*87.11*/("""<TR>
											<td style="text-align: left;">"""),_display_(/*88.43*/lista/*88.48*/.campo),format.raw/*88.54*/("""</td>
											<td style="text-align: left;">
												"""),_display_(if(lista.id_qrTipoContenido==1)/*90.45*/{_display_(Seq[Any](format.raw/*90.46*/("""
									        		"""),_display_(if(!lista.contenido.equals("0"))/*91.53*/{_display_(Seq[Any](format.raw/*91.54*/(""" """),_display_(/*91.56*/lista/*91.61*/.contenido),format.raw/*91.71*/(""" """)))} else {null} ),format.raw/*91.73*/("""
									        	""")))}else/*92.24*/{_display_(Seq[Any](format.raw/*92.25*/("""
									        		"""),_display_(if(!lista.contenido.equals("0"))/*93.53*/{_display_(Seq[Any](format.raw/*93.54*/("""
														"""),format.raw/*94.15*/("""<a href="#" onclick="verArchivo('"""),_display_(/*94.49*/lista/*94.54*/.contenido),format.raw/*94.64*/("""','"""),_display_(/*94.68*/lista/*94.73*/.extencion),format.raw/*94.83*/("""')">
															<kbd style="background-color: #73C6B6">Revisar """),_display_(/*95.64*/lista/*95.69*/.tipo),format.raw/*95.74*/(""" """),format.raw/*95.75*/("""Adjunto</kbd>
														</a>
									        		""")))} else {null} ),format.raw/*97.21*/("""
									        	""")))}),format.raw/*98.20*/("""
											"""),format.raw/*99.12*/("""</td>
											<td  style="text-align:center; min-width:80px; background-color:"""),_display_(/*100.77*/lista/*100.82*/.colorCelda),format.raw/*100.93*/("""">
												<div style="display:none;">"""),_display_(/*101.41*/lista/*101.46*/.fechaVencimiento),format.raw/*101.63*/("""</div>
												"""),_display_(/*102.14*/utilities/*102.23*/.Fechas.DDMMAA(lista.fechaVencimiento)),format.raw/*102.61*/("""
											"""),format.raw/*103.12*/("""</td>
									        <td style="text-align:center">
												<div id="cambiarEstadoPubQr_"""),_display_(/*105.42*/lista/*105.47*/.id_equipo),format.raw/*105.57*/(""" """),format.raw/*105.58*/("""_"""),_display_(/*105.60*/lista/*105.65*/.id_qrAtributoEquipo),format.raw/*105.85*/("""">
													"""),_display_(if(lista.activo == 1)/*106.36*/{_display_(Seq[Any](format.raw/*106.37*/("""
														"""),format.raw/*107.15*/("""<a href="#" onclick= "qrCambiarPubQr('"""),_display_(/*107.54*/lista/*107.59*/.id_equipo),format.raw/*107.69*/("""', '"""),_display_(/*107.74*/lista/*107.79*/.id_qrAtributoEquipo),format.raw/*107.99*/("""', '"""),_display_(/*107.104*/lista/*107.109*/.activo),format.raw/*107.116*/("""')">
															<kbd style="background-color: green">SI</kbd>
														</a>
										        	""")))} else {null} ),format.raw/*110.21*/("""
										        	"""),_display_(if(lista.activo == 0)/*111.42*/{_display_(Seq[Any](format.raw/*111.43*/("""
														"""),format.raw/*112.15*/("""<a href="#" onclick= "qrCambiarPubQr('"""),_display_(/*112.54*/lista/*112.59*/.id_equipo),format.raw/*112.69*/("""', '"""),_display_(/*112.74*/lista/*112.79*/.id_qrAtributoEquipo),format.raw/*112.99*/("""', '"""),_display_(/*112.104*/lista/*112.109*/.activo),format.raw/*112.116*/("""')">
															<kbd style="background-color: red">NO</kbd>
														</a>
										        	""")))} else {null} ),format.raw/*115.21*/("""
												"""),format.raw/*116.13*/("""</div>
								        	</td>
										</TR>
									""")))}),format.raw/*119.11*/("""
								"""),format.raw/*120.9*/("""</tbody>
							</table>
						</td>
					</tr>
				</table>
				<div class="noprint">
					<div class="row justify-content-md-center" >
						<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
							<input type="button"  value="VOLVER" class="btn btn-success btn-sm rounded-pill btn-block" onclick="window.history.back()">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	

		<div id="muestraPDF" class="modal" role="dialog" data-backdrop="static" data-keyboard="false" aria-hidden="true" style="display: none;">
			<div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">×</span>
					        </button>
					</div>
					<div class="modal-body">
						<div class='embed-responsive' style='padding-bottom:150%'>
							<div id="rutaPDF"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
""")))}),format.raw/*155.2*/("""




"""),format.raw/*160.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*161.31*/("""{"""),format.raw/*161.32*/("""
		  """),format.raw/*162.5*/("""document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*163.2*/("""}"""),format.raw/*163.3*/(""");
	
	const qrCambiarPubEquipo = (id_equipo, id_atributo, publico) =>"""),format.raw/*165.65*/("""{"""),format.raw/*165.66*/("""
		  """),format.raw/*166.5*/("""var formData = new FormData();
		  formData.append('id_equipo',id_equipo);
		  formData.append('id_atributo',id_atributo);
		  formData.append('publico',publico);
	      $.ajax("""),format.raw/*170.15*/("""{"""),format.raw/*170.16*/("""
	            """),format.raw/*171.14*/("""url: "/qrCambiarPubEquipo/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*178.37*/("""{"""),format.raw/*178.38*/("""
		     		"""),format.raw/*179.10*/("""if(respuesta.status)"""),format.raw/*179.30*/("""{"""),format.raw/*179.31*/("""
		     			"""),format.raw/*180.11*/("""if(publico==1)"""),format.raw/*180.25*/("""{"""),format.raw/*180.26*/("""
		     				"""),format.raw/*181.12*/("""document.getElementById("cambiarEstadoPubEquipo_"+id_equipo+"_"+id_atributo).innerHTML =
									"<a href='#' onclick='qrCambiarPubEquipo("+id_equipo+","+id_atributo+",0)'>"+
										"<kbd style='background-color: red'>NO</kbd>"+
									"</a>";
		     			"""),format.raw/*185.11*/("""}"""),format.raw/*185.12*/("""else"""),format.raw/*185.16*/("""{"""),format.raw/*185.17*/("""
		     				"""),format.raw/*186.12*/("""document.getElementById("cambiarEstadoPubEquipo_"+id_equipo+"_"+id_atributo).innerHTML =
									"<a href='#' onclick='qrCambiarPubEquipo("+id_equipo+","+id_atributo+",1)'>"+
										"<kbd style='background-color: green'>SI</kbd>"+
									"</a>";
		     			"""),format.raw/*190.11*/("""}"""),format.raw/*190.12*/("""
		     		"""),format.raw/*191.10*/("""}"""),format.raw/*191.11*/("""
		     	"""),format.raw/*192.9*/("""}"""),format.raw/*192.10*/("""
	        """),format.raw/*193.10*/("""}"""),format.raw/*193.11*/(""");
	  """),format.raw/*194.4*/("""}"""),format.raw/*194.5*/("""
	  
	  """),format.raw/*196.4*/("""const qrCambiarPubQr = (id_equipo, id_qrAtributoEquipo, publico) =>"""),format.raw/*196.71*/("""{"""),format.raw/*196.72*/("""
		  """),format.raw/*197.5*/("""var formData = new FormData();
		  formData.append('id_equipo',id_equipo);
		  formData.append('id_qrAtributoEquipo',id_qrAtributoEquipo);
		  formData.append('publico',publico);
	      $.ajax("""),format.raw/*201.15*/("""{"""),format.raw/*201.16*/("""
	            """),format.raw/*202.14*/("""url: "/qrCambiarPubQr/",
	            type: "POST",
	            method: "POST",
	            data: formData,
	            cache: false,
	            contentType: false,
		     	processData: false,
		     	success: function(respuesta)"""),format.raw/*209.37*/("""{"""),format.raw/*209.38*/("""
		     		"""),format.raw/*210.10*/("""if(respuesta.status)"""),format.raw/*210.30*/("""{"""),format.raw/*210.31*/("""
		     			"""),format.raw/*211.11*/("""if(publico==1)"""),format.raw/*211.25*/("""{"""),format.raw/*211.26*/("""
		     				"""),format.raw/*212.12*/("""document.getElementById("cambiarEstadoPubQr_"+id_equipo+" _"+id_qrAtributoEquipo).innerHTML = 
									"<a href='#' onclick='qrCambiarPubQr("+id_equipo+","+id_qrAtributoEquipo+",0)'>"+
										"<kbd style='background-color: red'>NO</kbd>"+
									"</a>";
		     			"""),format.raw/*216.11*/("""}"""),format.raw/*216.12*/("""else"""),format.raw/*216.16*/("""{"""),format.raw/*216.17*/("""
		     				"""),format.raw/*217.12*/("""document.getElementById("cambiarEstadoPubQr_"+id_equipo+" _"+id_qrAtributoEquipo).innerHTML = 
									"<a href='#' onclick='qrCambiarPubQr("+id_equipo+","+id_qrAtributoEquipo+",1)'>"+
										"<kbd style='background-color: green'>SI</kbd>"+
									"</a>";
		     			"""),format.raw/*221.11*/("""}"""),format.raw/*221.12*/("""
		     		"""),format.raw/*222.10*/("""}"""),format.raw/*222.11*/("""
		     	"""),format.raw/*223.9*/("""}"""),format.raw/*223.10*/("""
	        """),format.raw/*224.10*/("""}"""),format.raw/*224.11*/(""");
	  """),format.raw/*225.4*/("""}"""),format.raw/*225.5*/("""
	  
	  """),format.raw/*227.4*/("""const verArchivo = (archivo,extencion) => """),format.raw/*227.46*/("""{"""),format.raw/*227.47*/("""
			"""),format.raw/*228.4*/("""document.getElementById('rutaPDF').innerHTML="<object data='/showPdf/"+archivo+"' type='application/pdf' width='100%' height='720'></object>";
			$('#muestraPDF').modal('show');
	  """),format.raw/*230.4*/("""}"""),format.raw/*230.5*/("""



"""),format.raw/*234.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,equipo:tables.Equipo,atribEquipo:List[List[String]],listaTransac:List[qr.QrTransacEquipo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,equipo,atribEquipo,listaTransac)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.Equipo,List[List[String]],List[qr.QrTransacEquipo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,equipo,atribEquipo,listaTransac) => apply(mapDiccionario,mapPermiso,userMnu,equipo,atribEquipo,listaTransac)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuQr/qrRevisarDatos.scala.html
                  HASH: f69e7822f4cdc10b55a1237463f46a4607afabfa
                  MATRIX: 653->1|1104->33|1390->226|1423->234|1439->242|1478->244|1506->247|1574->295|1602->297|1679->348|1836->483|1866->486|2662->1255|2677->1261|2706->1269|2910->1446|2925->1452|2954->1460|3069->1548|3110->1573|3149->1574|3188->1585|3262->1632|3276->1637|3304->1644|3379->1692|3393->1697|3421->1704|3547->1803|3561->1808|3589->1815|3618->1817|3632->1822|3660->1829|3732->1874|3771->1875|3814->1890|3884->1933|3898->1938|3926->1945|3957->1949|3971->1954|3999->1961|4030->1965|4044->1970|4073->1977|4222->2082|4298->2131|4337->2132|4380->2147|4450->2190|4464->2195|4492->2202|4523->2206|4537->2211|4565->2218|4596->2222|4610->2227|4639->2234|4786->2337|4827->2350|4914->2406|4950->2415|5495->2933|5510->2939|5534->2942|5890->3271|5932->3297|5971->3298|6010->3309|6084->3356|6098->3361|6125->3367|6244->3459|6283->3460|6363->3513|6402->3514|6431->3516|6445->3521|6476->3531|6522->3533|6565->3557|6604->3558|6684->3611|6723->3612|6766->3627|6827->3661|6841->3666|6872->3676|6903->3680|6917->3685|6948->3695|7043->3763|7057->3768|7083->3773|7112->3774|7209->3827|7260->3847|7300->3859|7410->3941|7425->3946|7458->3957|7529->4000|7544->4005|7583->4022|7631->4042|7650->4051|7710->4089|7751->4101|7874->4196|7889->4201|7921->4211|7951->4212|7981->4214|7996->4219|8038->4239|8104->4277|8144->4278|8188->4293|8255->4332|8270->4337|8302->4347|8335->4352|8350->4357|8392->4377|8426->4382|8442->4387|8472->4394|8622->4499|8692->4541|8732->4542|8776->4557|8843->4596|8858->4601|8890->4611|8923->4616|8938->4621|8980->4641|9014->4646|9030->4651|9060->4658|9208->4761|9250->4774|9338->4830|9375->4839|10439->5872|10472->5877|10563->5939|10593->5940|10626->5945|10720->6011|10749->6012|10847->6081|10877->6082|10910->6087|11116->6264|11146->6265|11189->6279|11456->6517|11486->6518|11525->6528|11574->6548|11604->6549|11644->6560|11687->6574|11717->6575|11758->6587|12047->6847|12077->6848|12110->6852|12140->6853|12181->6865|12472->7127|12502->7128|12541->7138|12571->7139|12608->7148|12638->7149|12677->7159|12707->7160|12741->7166|12770->7167|12806->7175|12902->7242|12932->7243|12965->7248|13187->7441|13217->7442|13260->7456|13523->7690|13553->7691|13592->7701|13641->7721|13671->7722|13711->7733|13754->7747|13784->7748|13825->7760|14124->8030|14154->8031|14187->8035|14217->8036|14258->8048|14559->8320|14589->8321|14628->8331|14658->8332|14695->8341|14725->8342|14764->8352|14794->8353|14828->8359|14857->8360|14893->8368|14964->8410|14994->8411|15026->8415|15235->8596|15264->8597|15296->8601
                  LINES: 24->1|29->2|35->4|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|42->11|62->31|62->31|62->31|67->36|67->36|67->36|72->41|72->41|72->41|73->42|74->43|74->43|74->43|75->44|75->44|75->44|77->46|77->46|77->46|77->46|77->46|77->46|78->47|78->47|79->48|79->48|79->48|79->48|79->48|79->48|79->48|79->48|79->48|79->48|82->51|83->52|83->52|84->53|84->53|84->53|84->53|84->53|84->53|84->53|84->53|84->53|84->53|87->56|88->57|91->60|92->61|105->74|105->74|105->74|117->86|117->86|117->86|118->87|119->88|119->88|119->88|121->90|121->90|122->91|122->91|122->91|122->91|122->91|122->91|123->92|123->92|124->93|124->93|125->94|125->94|125->94|125->94|125->94|125->94|125->94|126->95|126->95|126->95|126->95|128->97|129->98|130->99|131->100|131->100|131->100|132->101|132->101|132->101|133->102|133->102|133->102|134->103|136->105|136->105|136->105|136->105|136->105|136->105|136->105|137->106|137->106|138->107|138->107|138->107|138->107|138->107|138->107|138->107|138->107|138->107|138->107|141->110|142->111|142->111|143->112|143->112|143->112|143->112|143->112|143->112|143->112|143->112|143->112|143->112|146->115|147->116|150->119|151->120|186->155|191->160|192->161|192->161|193->162|194->163|194->163|196->165|196->165|197->166|201->170|201->170|202->171|209->178|209->178|210->179|210->179|210->179|211->180|211->180|211->180|212->181|216->185|216->185|216->185|216->185|217->186|221->190|221->190|222->191|222->191|223->192|223->192|224->193|224->193|225->194|225->194|227->196|227->196|227->196|228->197|232->201|232->201|233->202|240->209|240->209|241->210|241->210|241->210|242->211|242->211|242->211|243->212|247->216|247->216|247->216|247->216|248->217|252->221|252->221|253->222|253->222|254->223|254->223|255->224|255->224|256->225|256->225|258->227|258->227|258->227|259->228|261->230|261->230|265->234
                  -- GENERATED --
              */
          