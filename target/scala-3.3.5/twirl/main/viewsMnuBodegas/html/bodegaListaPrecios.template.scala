
package viewsMnuBodegas.html

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

object bodegaListaPrecios extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template7[Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.ListaPrecio],List[tables.Moneda],List[tables.UnidadTiempo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
bodega: tables.BodegaEmpresa, listPrecio: List[tables.ListaPrecio], listMoneda: List[tables.Moneda], listUnidadTiempo: List[tables.UnidadTiempo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

	"""),_display_(/*6.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.51*/("""
	
	"""),_display_(/*8.3*/modalEquipoDescripcion()),format.raw/*8.27*/("""
	"""),_display_(/*9.3*/modalVerCotizacion()),format.raw/*9.23*/("""
	
	"""),format.raw/*11.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*12.4*/barraTitulo(mapDiccionario, "MANTENCION DE PRECIOS POR "+mapDiccionario.get("BODEGA")+"S/PROYECTOS", bodega.getNombre().toUpperCase())),format.raw/*12.138*/("""
		"""),format.raw/*13.3*/("""<table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
			<thead style="background-color: #eeeeee">
				<TR> 
					<TH style="vertical-align: top;">GRUPO</TH>
					<TH>NRO.COTI</TH>
					<TH>CODIGO</TH>
					<TH>EQUIPO</TH>
					<TH>MONEDA<BR>Rep/"""),_display_(/*20.25*/mapDiccionario/*20.39*/.get("Arr")),format.raw/*20.50*/("""</TH>
					<TH>PRECIO<br>Reposici&oacute;n o Venta</TH>
					<TH>TASA<br>"""),_display_(/*22.19*/mapDiccionario/*22.33*/.get("Arriendo")),format.raw/*22.49*/("""</TH>
					<TH>PRECIO<br>"""),_display_(/*23.21*/mapDiccionario/*23.35*/.get("Arriendo")),format.raw/*23.51*/("""</TH>
					<TH>UNIDAD<BR>"""),_display_(/*24.21*/mapDiccionario/*24.35*/.get("Arriendo")),format.raw/*24.51*/("""</TH>
					<TH>VALOR<br>M&iacute;nimo</TH>
					<TH>MIN<br>D&iacute;as</TH>
				</TR>
			</thead>
			<tbody>
				"""),_display_(/*30.6*/for(lista2 <- listPrecio) yield /*30.31*/{_display_(Seq[Any](format.raw/*30.32*/("""
					"""),format.raw/*31.6*/("""<tr class="align-middle">
						<td>"""),_display_(/*32.12*/lista2/*32.18*/.getNombreGrupo()),format.raw/*32.35*/("""</td>
						"""),_display_(if(lista2.getNumeroCotizacion()==0 || lista2.getNumeroCotizacion().equals(""))/*33.86*/{_display_(Seq[Any](format.raw/*33.87*/("""
							"""),format.raw/*34.8*/("""<td style="text-align: center;">--</td>
						""")))}else/*35.12*/{_display_(Seq[Any](format.raw/*35.13*/("""
							"""),format.raw/*36.8*/("""<td style="text-align: center;"><a href="#" onclick="buscaCotizacion('"""),_display_(/*36.79*/lista2/*36.85*/.getNumeroCotizacion()),format.raw/*36.107*/("""')">"""),_display_(/*36.112*/lista2/*36.118*/.getNumeroCotizacion()),format.raw/*36.140*/("""</a></td>
						""")))}),format.raw/*37.8*/("""
						
						"""),format.raw/*39.7*/("""<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*39.72*/lista2/*39.78*/.getCodigoEquipo()),format.raw/*39.96*/("""');">"""),_display_(/*39.102*/lista2/*39.108*/.getCodigoEquipo()),format.raw/*39.126*/("""</a></td>
						<td  style="text-align:left;"><a href="#" onclick="buscaEquipo('"""),_display_(/*40.72*/lista2/*40.78*/.getCodigoEquipo()),format.raw/*40.96*/("""');">"""),_display_(/*40.102*/lista2/*40.108*/.getNombreEquipo()),format.raw/*40.126*/("""</a></td>
						
						<td>
							<select class="custom-select" 
								id="NM_"""),_display_(/*44.17*/lista2/*44.23*/.getId_equipo()),_display_(/*44.39*/lista2/*44.45*/.getId_cotizacion()),format.raw/*44.64*/("""" 
								onchange="grabar(id,value,'"""),_display_(/*45.37*/lista2/*45.43*/.getId_equipo()),format.raw/*45.58*/("""','"""),_display_(/*45.62*/lista2/*45.68*/.getId_cotizacion()),format.raw/*45.87*/("""')">
								<option value=""""),_display_(/*46.25*/lista2/*46.31*/.getId_moneda()),format.raw/*46.46*/("""">"""),_display_(/*46.49*/lista2/*46.55*/.getNickNameMoneda()),format.raw/*46.75*/("""</option>
								"""),_display_(/*47.10*/for(lista <- listMoneda) yield /*47.34*/{_display_(Seq[Any](format.raw/*47.35*/("""
									"""),format.raw/*48.10*/("""<option value=""""),_display_(/*48.26*/lista/*48.31*/.id),format.raw/*48.34*/("""">"""),_display_(/*48.37*/lista/*48.42*/.nickName),format.raw/*48.51*/("""</option>
								""")))}),format.raw/*49.10*/("""
							"""),format.raw/*50.8*/("""</select>
						</td>
						<td>
							<div style="display:none">"""),_display_(/*53.35*/lista2/*53.41*/.getPrecioVenta()),format.raw/*53.58*/("""</div>
							<input type="text" class="form-control right"
								id="PV_"""),_display_(/*55.17*/lista2/*55.23*/.getId_equipo()),_display_(/*55.39*/lista2/*55.45*/.getId_cotizacion()),format.raw/*55.64*/(""""
								value=""""),_display_(/*56.17*/lista2/*56.23*/.getPrecioVenta()),format.raw/*56.40*/(""""
								onfocus="value=value.replace(/,/g,'')" 
								onkeydown="return ingresoDouble(window.event)"
								onchange="grabar(id,value,'"""),_display_(/*59.37*/lista2/*59.43*/.getId_equipo()),format.raw/*59.58*/("""','"""),_display_(/*59.62*/lista2/*59.68*/.getId_cotizacion()),format.raw/*59.87*/("""')">
						</td>
						<td>
							<div style="display:none">"""),_display_(/*62.35*/lista2/*62.41*/.getTasaArriendo()),format.raw/*62.59*/("""</div>
							<input type="text" class="form-control center"
								id="TA_"""),_display_(/*64.17*/lista2/*64.23*/.getId_equipo()),_display_(/*64.39*/lista2/*64.45*/.getId_cotizacion()),format.raw/*64.64*/("""" 
								value=""""),_display_(/*65.17*/lista2/*65.23*/.getTasaArriendo()),format.raw/*65.41*/(""""
								disabled>
						</td>
						<td>
							<div style="display:none">"""),_display_(/*69.35*/lista2/*69.41*/.getPrecioArriendo()),format.raw/*69.61*/("""</div>
							<input type="text" class="form-control right"
								id="PA_"""),_display_(/*71.17*/lista2/*71.23*/.getId_equipo()),_display_(/*71.39*/lista2/*71.45*/.getId_cotizacion()),format.raw/*71.64*/(""""
								value=""""),_display_(/*72.17*/lista2/*72.23*/.getPrecioArriendo()),format.raw/*72.43*/(""""
								onfocus="value=value.replace(/,/g,'')" 
								onkeydown="return ingresoDouble(window.event)"
								onchange="grabar(id,value,'"""),_display_(/*75.37*/lista2/*75.43*/.getId_equipo()),format.raw/*75.58*/("""','"""),_display_(/*75.62*/lista2/*75.68*/.getId_cotizacion()),format.raw/*75.87*/("""')">	
						</td>
						<td>
							<select class="custom-select" 
								id="UT_"""),_display_(/*79.17*/lista2/*79.23*/.getId_equipo()),_display_(/*79.39*/lista2/*79.45*/.getId_cotizacion()),format.raw/*79.64*/(""""
								onchange="grabar(id,value,'"""),_display_(/*80.37*/lista2/*80.43*/.getId_equipo()),format.raw/*80.58*/("""','"""),_display_(/*80.62*/lista2/*80.68*/.getId_cotizacion()),format.raw/*80.87*/("""')">
								<option value=""""),_display_(/*81.25*/lista2/*81.31*/.getId_unidadTiempo()),format.raw/*81.52*/("""">"""),_display_(/*81.55*/lista2/*81.61*/.getNombreUnidadTiempo()),format.raw/*81.85*/("""</option>
								"""),_display_(/*82.10*/for(lista <- listUnidadTiempo) yield /*82.40*/{_display_(Seq[Any](format.raw/*82.41*/("""
									"""),format.raw/*83.10*/("""<option value=""""),_display_(/*83.26*/lista/*83.31*/.id),format.raw/*83.34*/("""">"""),_display_(/*83.37*/lista/*83.42*/.nombre),format.raw/*83.49*/("""</option>
								""")))}),format.raw/*84.10*/("""
							"""),format.raw/*85.8*/("""</select>
						</td>
						<td>
							<div style="display:none">"""),_display_(/*88.35*/lista2/*88.41*/.getPrecioMinimo()),format.raw/*88.59*/("""</div>
							<input type="text" class="form-control right"
								id="PM_"""),_display_(/*90.17*/lista2/*90.23*/.getId_equipo()),_display_(/*90.39*/lista2/*90.45*/.getId_cotizacion()),format.raw/*90.64*/(""""
								value=""""),_display_(/*91.17*/lista2/*91.23*/.getPrecioMinimo()),format.raw/*91.41*/(""""
								onfocus="value=value.replace(/,/g,'')" 
								onkeydown="return ingresoDouble(window.event)"
								onchange="grabar(id,value,'"""),_display_(/*94.37*/lista2/*94.43*/.getId_equipo()),format.raw/*94.58*/("""','"""),_display_(/*94.62*/lista2/*94.68*/.getId_cotizacion()),format.raw/*94.87*/("""')">
						</td>
						<td>
							<div style="display:none">"""),_display_(/*97.35*/lista2/*97.41*/.getPermanenciaMinima()),format.raw/*97.64*/("""</div>
							<input type="text" class="form-control right"
								id="PE_"""),_display_(/*99.17*/lista2/*99.23*/.getId_equipo()),_display_(/*99.39*/lista2/*99.45*/.getId_cotizacion()),format.raw/*99.64*/(""""
								value=""""),_display_(/*100.17*/lista2/*100.23*/.getPermanenciaMinima()),format.raw/*100.46*/(""""
								onfocus="value=value.replace(/,/g,'')"
								onkeydown="return ingresoDouble(window.event)"
								onchange="grabar(id,value,'"""),_display_(/*103.37*/lista2/*103.43*/.getId_equipo()),format.raw/*103.58*/("""','"""),_display_(/*103.62*/lista2/*103.68*/.getId_cotizacion()),format.raw/*103.87*/("""')">									
						</td>
					</tr>
				 """)))}),format.raw/*106.7*/("""
			"""),format.raw/*107.4*/("""</tbody>
		</table>
		<div class="noprint">
			<div class="row justify-content-md-center" >
				<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
					<input type="button"  value="LISTO" class="btn btn-success btn-sm rounded-pill btn-block" onclick="location.href='/bodegaPrecios/'">
				</div>
			</div>
		</div>
	</div>
""")))}),format.raw/*117.2*/("""




"""),format.raw/*122.1*/("""<script type="text/javascript">
	$(document).ready(function() """),format.raw/*123.31*/("""{"""),format.raw/*123.32*/("""
		  """),format.raw/*124.5*/("""$('#tablaPrincipal').DataTable("""),format.raw/*124.36*/("""{"""),format.raw/*124.37*/("""
		    	"""),format.raw/*125.8*/(""""fixedHeader": true,
		    	"lengthMenu": [[20, 50, 100, 200, -1], [20, 50, 100, 200, "All"]],
		    	"language": """),format.raw/*127.20*/("""{"""),format.raw/*127.21*/("""
		        	"""),format.raw/*128.12*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
		        """),format.raw/*129.11*/("""}"""),format.raw/*129.12*/("""
		  """),format.raw/*130.5*/("""}"""),format.raw/*130.6*/(""" """),format.raw/*130.7*/(""");
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*132.2*/("""}"""),format.raw/*132.3*/(""");

	const grabar = (id, valor, id_equipo, id_cotizacion) => """),format.raw/*134.58*/("""{"""),format.raw/*134.59*/("""
		"""),format.raw/*135.3*/("""let auxId = id.split("_");
		let id_moneda = $("#NM_"+auxId[1]).val();
		var formData = new FormData();
		formData.append('id_bodegaEmpresa','"""),_display_(/*138.40*/bodega/*138.46*/.getId),format.raw/*138.52*/("""');
		formData.append('id_equipo',id_equipo);
		formData.append('id_cotizacion',id_cotizacion);
		formData.append('id_moneda',id_moneda);
		formData.append('campo',auxId[0]);
		formData.append('valor',valor);
		$.ajax("""),format.raw/*144.10*/("""{"""),format.raw/*144.11*/("""
            """),format.raw/*145.13*/("""url: "/actualizaListaPrecioAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*152.36*/("""{"""),format.raw/*152.37*/("""
	     		"""),format.raw/*153.9*/("""if(respuesta=="error")"""),format.raw/*153.31*/("""{"""),format.raw/*153.32*/("""
	     			"""),format.raw/*154.10*/("""alertify.alert(msgError, function () """),format.raw/*154.47*/("""{"""),format.raw/*154.48*/("""
		     			"""),format.raw/*155.11*/("""location.href = "/";
		     		"""),format.raw/*156.10*/("""}"""),format.raw/*156.11*/(""");
	     		"""),format.raw/*157.9*/("""}"""),format.raw/*157.10*/("""
	     		"""),format.raw/*158.9*/("""if(respuesta.status)"""),format.raw/*158.29*/("""{"""),format.raw/*158.30*/("""
	     			"""),format.raw/*159.10*/("""let fecha = respuesta.fecha;
	     			let numDec = respuesta.numDec;
	     			"""),_display_(if(mapDiccionario.get("nEmpresa").equals("MONTAX")||mapDiccionario.get("nEmpresa").equals("TECA"))/*161.109*/ {_display_(Seq[Any](format.raw/*161.111*/("""
	     				"""),format.raw/*162.11*/("""numDec = 4;
	     			""")))} else {null} ),format.raw/*163.11*/("""
	     			"""),format.raw/*164.10*/("""if(auxId[0]=="PV" || auxId[0]=="PA" || auxId[0]=="PM")"""),format.raw/*164.64*/("""{"""),format.raw/*164.65*/("""
	     				"""),format.raw/*165.11*/("""$("#"+id).val(formatStandar(valor,numDec));
	     			"""),format.raw/*166.10*/("""}"""),format.raw/*166.11*/("""
	     			"""),format.raw/*167.10*/("""if(auxId[0]=="PE")"""),format.raw/*167.28*/("""{"""),format.raw/*167.29*/("""
	     				"""),format.raw/*168.11*/("""$("#"+id).val(formatStandar(valor,0));
	     			"""),format.raw/*169.10*/("""}"""),format.raw/*169.11*/("""
	     			"""),format.raw/*170.10*/("""if(auxId[0]=="PA")"""),format.raw/*170.28*/("""{"""),format.raw/*170.29*/("""
	     				"""),format.raw/*171.11*/("""let numero = new DecimalFormat("#,##0.00");
	     				let pventa = numero.formatBack($("#PV_"+auxId[1]).val());
	     				let tasa = 0;
	     				if(pventa!=0)"""),format.raw/*174.24*/("""{"""),format.raw/*174.25*/("""
	     					"""),format.raw/*175.12*/("""tasa = valor/pventa*100;
	     				"""),format.raw/*176.11*/("""}"""),format.raw/*176.12*/("""
	     				"""),format.raw/*177.11*/("""$("#TA_"+auxId[1]).val(formatPorcentaje(tasa));
	     			"""),format.raw/*178.10*/("""}"""),format.raw/*178.11*/("""
	     			"""),format.raw/*179.10*/("""if(auxId[0]=="PV")"""),format.raw/*179.28*/("""{"""),format.raw/*179.29*/("""
	     				"""),format.raw/*180.11*/("""let numero = new DecimalFormat("#,##0.00");
	     				let parriendo = numero.formatBack($("#PA_"+auxId[1]).val());
	     				let tasa = 0;
	     				if(valor!=0)"""),format.raw/*183.23*/("""{"""),format.raw/*183.24*/("""
	     					"""),format.raw/*184.12*/("""tasa = parriendo/valor*100;
	     				"""),format.raw/*185.11*/("""}"""),format.raw/*185.12*/(""" 
	     				"""),format.raw/*186.11*/("""$("#TA_"+auxId[1]).val(formatPorcentaje(tasa));
	     			"""),format.raw/*187.10*/("""}"""),format.raw/*187.11*/("""
	     			"""),format.raw/*188.10*/("""if(auxId[0]=="NM")"""),format.raw/*188.28*/("""{"""),format.raw/*188.29*/("""
	     				"""),format.raw/*189.11*/("""let numero = new DecimalFormat("#,##0.00");
	     				let pventa = numero.formatBack($("#PV_"+auxId[1]).val());
	     				let parriendo = numero.formatBack($("#PA_"+auxId[1]).val());
	     				let pminimo = numero.formatBack($("#PM_"+auxId[1]).val());
	     				$("#PV_"+auxId[1]).val(formatStandar(pventa,numDec));
	     				$("#PA_"+auxId[1]).val(formatStandar(parriendo,numDec));
	     				$("#PM_"+auxId[1]).val(formatStandar(pminimo,numDec));
	     				
	     			"""),format.raw/*197.10*/("""}"""),format.raw/*197.11*/("""
	     			"""),format.raw/*198.10*/("""$("#FE_"+auxId[1]).val(fecha);
	     		"""),format.raw/*199.9*/("""}"""),format.raw/*199.10*/("""else"""),format.raw/*199.14*/("""{"""),format.raw/*199.15*/("""
	     			"""),format.raw/*200.10*/("""location.reload();
	     		"""),format.raw/*201.9*/("""}"""),format.raw/*201.10*/("""
	     	"""),format.raw/*202.8*/("""}"""),format.raw/*202.9*/("""
        """),format.raw/*203.9*/("""}"""),format.raw/*203.10*/(""");
	"""),format.raw/*204.2*/("""}"""),format.raw/*204.3*/("""
"""),format.raw/*205.1*/("""</script>


		
		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,bodega:tables.BodegaEmpresa,listPrecio:List[tables.ListaPrecio],listMoneda:List[tables.Moneda],listUnidadTiempo:List[tables.UnidadTiempo]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,bodega,listPrecio,listMoneda,listUnidadTiempo)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,tables.BodegaEmpresa,List[tables.ListaPrecio],List[tables.Moneda],List[tables.UnidadTiempo]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,bodega,listPrecio,listMoneda,listUnidadTiempo) => apply(mapDiccionario,mapPermiso,userMnu,bodega,listPrecio,listMoneda,listUnidadTiempo)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuBodegas/bodegaListaPrecios.scala.html
                  HASH: 8d86639ab9b048e040e30ee72d0ec5ae83e23f07
                  MATRIX: 1109->1|1445->244|1472->246|1488->254|1527->256|1556->260|1624->308|1654->313|1698->337|1726->340|1766->360|1797->364|1874->415|2030->549|2060->552|2386->851|2409->865|2441->876|2542->950|2565->964|2602->980|2655->1006|2678->1020|2715->1036|2768->1062|2791->1076|2828->1092|2968->1206|3009->1231|3048->1232|3081->1238|3145->1275|3160->1281|3198->1298|3316->1389|3355->1390|3390->1398|3460->1449|3499->1450|3534->1458|3632->1529|3647->1535|3691->1557|3724->1562|3740->1568|3784->1590|3831->1607|3872->1621|3964->1686|3979->1692|4018->1710|4052->1716|4068->1722|4108->1740|4216->1821|4231->1827|4270->1845|4304->1851|4320->1857|4360->1875|4469->1957|4484->1963|4520->1979|4535->1985|4575->2004|4641->2043|4656->2049|4692->2064|4723->2068|4738->2074|4778->2093|4834->2122|4849->2128|4885->2143|4915->2146|4930->2152|4971->2172|5017->2191|5057->2215|5096->2216|5134->2226|5177->2242|5191->2247|5215->2250|5245->2253|5259->2258|5289->2267|5339->2286|5374->2294|5468->2361|5483->2367|5521->2384|5624->2460|5639->2466|5675->2482|5690->2488|5730->2507|5775->2525|5790->2531|5828->2548|5996->2689|6011->2695|6047->2710|6078->2714|6093->2720|6133->2739|6222->2801|6237->2807|6276->2825|6380->2902|6395->2908|6431->2924|6446->2930|6486->2949|6532->2968|6547->2974|6586->2992|6690->3069|6705->3075|6746->3095|6849->3171|6864->3177|6900->3193|6915->3199|6955->3218|7000->3236|7015->3242|7056->3262|7224->3403|7239->3409|7275->3424|7306->3428|7321->3434|7361->3453|7471->3536|7486->3542|7522->3558|7537->3564|7577->3583|7642->3621|7657->3627|7693->3642|7724->3646|7739->3652|7779->3671|7835->3700|7850->3706|7892->3727|7922->3730|7937->3736|7982->3760|8028->3779|8074->3809|8113->3810|8151->3820|8194->3836|8208->3841|8232->3844|8262->3847|8276->3852|8304->3859|8354->3878|8389->3886|8483->3953|8498->3959|8537->3977|8640->4053|8655->4059|8691->4075|8706->4081|8746->4100|8791->4118|8806->4124|8845->4142|9013->4283|9028->4289|9064->4304|9095->4308|9110->4314|9150->4333|9239->4395|9254->4401|9298->4424|9401->4500|9416->4506|9452->4522|9467->4528|9507->4547|9553->4565|9569->4571|9614->4594|9782->4734|9798->4740|9835->4755|9867->4759|9883->4765|9924->4784|9998->4827|10030->4831|10384->5154|10417->5159|10508->5221|10538->5222|10571->5227|10631->5258|10661->5259|10697->5267|10840->5381|10870->5382|10911->5394|11018->5472|11048->5473|11081->5478|11110->5479|11139->5480|11240->5553|11269->5554|11359->5615|11389->5616|11420->5619|11591->5762|11607->5768|11635->5774|11882->5992|11912->5993|11954->6006|12220->6243|12250->6244|12287->6253|12338->6275|12368->6276|12407->6286|12473->6323|12503->6324|12543->6335|12602->6365|12632->6366|12671->6377|12701->6378|12738->6387|12787->6407|12817->6408|12856->6418|13062->6595|13104->6597|13144->6608|13211->6630|13250->6640|13333->6694|13363->6695|13403->6706|13485->6759|13515->6760|13554->6770|13601->6788|13631->6789|13671->6800|13748->6848|13778->6849|13817->6859|13864->6877|13894->6878|13934->6889|14122->7048|14152->7049|14193->7061|14257->7096|14287->7097|14327->7108|14413->7165|14443->7166|14482->7176|14529->7194|14559->7195|14599->7206|14789->7367|14819->7368|14860->7380|14927->7418|14957->7419|14998->7431|15084->7488|15114->7489|15153->7499|15200->7517|15230->7518|15270->7529|15767->7997|15797->7998|15836->8008|15903->8047|15933->8048|15966->8052|15996->8053|16035->8063|16090->8090|16120->8091|16156->8099|16185->8100|16222->8109|16252->8110|16284->8114|16313->8115|16342->8116
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|42->11|43->12|43->12|44->13|51->20|51->20|51->20|53->22|53->22|53->22|54->23|54->23|54->23|55->24|55->24|55->24|61->30|61->30|61->30|62->31|63->32|63->32|63->32|64->33|64->33|65->34|66->35|66->35|67->36|67->36|67->36|67->36|67->36|67->36|67->36|68->37|70->39|70->39|70->39|70->39|70->39|70->39|70->39|71->40|71->40|71->40|71->40|71->40|71->40|75->44|75->44|75->44|75->44|75->44|76->45|76->45|76->45|76->45|76->45|76->45|77->46|77->46|77->46|77->46|77->46|77->46|78->47|78->47|78->47|79->48|79->48|79->48|79->48|79->48|79->48|79->48|80->49|81->50|84->53|84->53|84->53|86->55|86->55|86->55|86->55|86->55|87->56|87->56|87->56|90->59|90->59|90->59|90->59|90->59|90->59|93->62|93->62|93->62|95->64|95->64|95->64|95->64|95->64|96->65|96->65|96->65|100->69|100->69|100->69|102->71|102->71|102->71|102->71|102->71|103->72|103->72|103->72|106->75|106->75|106->75|106->75|106->75|106->75|110->79|110->79|110->79|110->79|110->79|111->80|111->80|111->80|111->80|111->80|111->80|112->81|112->81|112->81|112->81|112->81|112->81|113->82|113->82|113->82|114->83|114->83|114->83|114->83|114->83|114->83|114->83|115->84|116->85|119->88|119->88|119->88|121->90|121->90|121->90|121->90|121->90|122->91|122->91|122->91|125->94|125->94|125->94|125->94|125->94|125->94|128->97|128->97|128->97|130->99|130->99|130->99|130->99|130->99|131->100|131->100|131->100|134->103|134->103|134->103|134->103|134->103|134->103|137->106|138->107|148->117|153->122|154->123|154->123|155->124|155->124|155->124|156->125|158->127|158->127|159->128|160->129|160->129|161->130|161->130|161->130|163->132|163->132|165->134|165->134|166->135|169->138|169->138|169->138|175->144|175->144|176->145|183->152|183->152|184->153|184->153|184->153|185->154|185->154|185->154|186->155|187->156|187->156|188->157|188->157|189->158|189->158|189->158|190->159|192->161|192->161|193->162|194->163|195->164|195->164|195->164|196->165|197->166|197->166|198->167|198->167|198->167|199->168|200->169|200->169|201->170|201->170|201->170|202->171|205->174|205->174|206->175|207->176|207->176|208->177|209->178|209->178|210->179|210->179|210->179|211->180|214->183|214->183|215->184|216->185|216->185|217->186|218->187|218->187|219->188|219->188|219->188|220->189|228->197|228->197|229->198|230->199|230->199|230->199|230->199|231->200|232->201|232->201|233->202|233->202|234->203|234->203|235->204|235->204|236->205
                  -- GENERATED --
              */
          