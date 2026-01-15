
package viewsMnuCotizar.html

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

object datosContrato extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template10[Map[String,String],Map[String,String],utilities.UserMnu,forms.FormContrato,tables.Regiones,tables.Comunas,List[tables.Regiones],List[tables.Comunas],List[List[String]],List[tables.Proyecto],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
datos: forms.FormContrato, region: tables.Regiones, comuna: tables.Comunas, listRegiones: List[tables.Regiones], listComunas: List[tables.Comunas],
listBodegas: List[List[String]], listProyectos: List[tables.Proyecto]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
    	
"""),_display_(/*6.2*/main("")/*6.10*/ {_display_(Seq[Any](format.raw/*6.12*/("""

	"""),_display_(/*8.3*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*8.51*/("""
	"""),format.raw/*9.2*/("""<div id="mostrarmostrar" style="display:none;">
		"""),_display_(/*10.4*/barraTitulo(mapDiccionario, "DATOS PARA HACER CONTRATO", "")),format.raw/*10.64*/("""
		"""),format.raw/*11.3*/("""<form id='listaForm' method='post' action='/routes2/hacerContrato/' onsubmit="return validarForm();">
			<div class="row  justify-content-md-center">
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					<table class="table table-sm table-bordered table-condensed table-fluid">
						<tr>
							<td style="text-align:left;">NOMBRE CLIENTE:</td>
							<td style="text-align:left;">
								<input type="hidden" name="id_cotizacion" value=""""),_display_(/*18.59*/datos/*18.64*/.id_cotizacion),format.raw/*18.78*/("""">
								<input type="hidden" name="id_cliente" value=""""),_display_(/*19.56*/datos/*19.61*/.id_cliente),format.raw/*19.72*/("""">
								<input type="text" class="form-control left"
									name="clienteNombre"
									autocomplete="off"
									value=""""),_display_(/*23.18*/datos/*23.23*/.clienteNombre),format.raw/*23.37*/(""""
									maxlength="100"
									required>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">"""),_display_(/*29.38*/mapDiccionario/*29.52*/.get("RUT")),format.raw/*29.63*/(""" """),format.raw/*29.64*/("""CLIENTE:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteRut"
									autocomplete="off"
									value=""""),_display_(/*34.18*/datos/*34.23*/.clienteRut),format.raw/*34.34*/(""""
									maxlength="50"
									required>
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">GIRO:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteGiro"
									autocomplete="off"
									value=""""),_display_(/*45.18*/datos/*45.23*/.clienteGiro),format.raw/*45.35*/(""""
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">DIRECCION:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteDireccion"
									autocomplete="off"
									value=""""),_display_(/*55.18*/datos/*55.23*/.clienteDireccion),format.raw/*55.40*/(""""
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">NOMBRE REPRESENTANTE:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteRepresentante1"
									autocomplete="off"
									value=""""),_display_(/*65.18*/datos/*65.23*/.clienteRepresentante1),format.raw/*65.45*/(""""
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">"""),_display_(/*70.38*/mapDiccionario/*70.52*/.get("RUT")),format.raw/*70.63*/(""" """),format.raw/*70.64*/("""REPRESENTANTE:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteRutRepresentante1"
									autocomplete="off"
									value=""""),_display_(/*75.18*/datos/*75.23*/.clienteRutRepresentante1),format.raw/*75.48*/(""""
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">CARGO REPRESENTANTE:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteCargoRepresentante1"
									autocomplete="off"
									value=""""),_display_(/*85.18*/datos/*85.23*/.clienteCargoRepresentante1),format.raw/*85.50*/(""""
									maxlength="100">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">FECHA DE CONTRATO:</td>
							<td style="text-align:left;">
								"""),_display_(if(datos.fechaContrato==null)/*92.39*/{_display_(Seq[Any](format.raw/*92.40*/("""
									"""),format.raw/*93.10*/("""<input type="date" class="form-control left"
							  			name="fechaContrato"
					        			required>
								""")))}else/*96.14*/{_display_(Seq[Any](format.raw/*96.15*/("""
									"""),format.raw/*97.10*/("""<input type="date" class="form-control left"
							  			name="fechaContrato"
							  			value=""""),_display_(/*99.21*/utilities/*99.30*/.Fechas.AAMMDD(datos.fechaContrato)),format.raw/*99.65*/(""""
					        			required>
								""")))}),format.raw/*101.10*/("""
							"""),format.raw/*102.8*/("""</td>
						</tr>
						<tr>
							<td style="text-align:left;">NUMERO CONTRATO:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="numeroContrato"
									autocomplete="off"
									value=""""),_display_(/*110.18*/datos/*110.23*/.numeroContrato),format.raw/*110.38*/(""""
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">E-MAIL:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteEMail"
									autocomplete="off"
									value=""""),_display_(/*120.18*/datos/*120.23*/.clienteEMail),format.raw/*120.36*/(""""
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">TELEFONO:</td>
							<td style="text-align:left;">
								<input type="text" class="form-control left"
									name="clienteFono"
									autocomplete="off"
									value=""""),_display_(/*130.18*/datos/*130.23*/.clienteFono),format.raw/*130.35*/(""""
									maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">Notas adicionales<br>(Se agregan al final del contrato):</td>
							<td style="text-align:left;">
								<textarea class="form-control" rows="5"
									name="notasAlContrato" 
									autocomplete="off">"""),_display_(/*139.30*/datos/*139.35*/.notasAlContrato),format.raw/*139.51*/("""</textarea>
							</td>
						</tr>
						
						<!-- NEGACION -->
						"""),_display_(if(!(mapDiccionario.get("nEmpresa").equals("MONTAX") || mapDiccionario.get("nEmpresa").equals("ARRIVECO") ))/*144.116*/{_display_(Seq[Any](format.raw/*144.117*/("""
							"""),format.raw/*145.8*/("""<input type="hidden" name="clienteRegion" value=""""),_display_(/*145.58*/datos/*145.63*/.clienteRegion),format.raw/*145.77*/("""">
							<input type="hidden" name="clienteComuna" value=""""),_display_(/*146.58*/datos/*146.63*/.clienteComuna),format.raw/*146.77*/("""">
							<input type="hidden" name="clienteContacto" value=""""),_display_(/*147.60*/datos/*147.65*/.clienteContacto),format.raw/*147.81*/("""">
							<input type="hidden" name="clienteCargoContacto" value=""""),_display_(/*148.65*/datos/*148.70*/.clienteCargoContacto),format.raw/*148.91*/("""">
							<input type="hidden" name="usadosEn" value=""""),_display_(/*149.53*/datos/*149.58*/.usadosEn),format.raw/*149.67*/("""">
							<input type="hidden" name="clienteOC" value=""""),_display_(/*150.54*/datos/*150.59*/.clienteOC),format.raw/*150.69*/("""">
							"""),_display_(if(datos.clienteFechaOC==null)/*151.39*/{_display_(Seq[Any](format.raw/*151.40*/("""
								"""),format.raw/*152.9*/("""<input type="hidden" name="clienteFechaOC" value="">
							""")))}else/*153.13*/{_display_(Seq[Any](format.raw/*153.14*/("""
								"""),format.raw/*154.9*/("""<input type="hidden" name="clienteFechaOC" value=""""),_display_(/*154.60*/utilities/*154.69*/.Fechas.AAMMDD(datos.clienteFechaOC)),format.raw/*154.105*/("""">
							""")))}),format.raw/*155.9*/("""
							"""),format.raw/*156.8*/("""<input type="hidden" name="garantiaDoc" value=""""),_display_(/*156.56*/datos/*156.61*/.garantiaDoc),format.raw/*156.73*/("""">
							<input type="hidden" name="garantiaDet" value=""""),_display_(/*157.56*/datos/*157.61*/.garantiaDet),format.raw/*157.73*/("""">
							<input type="hidden" name="garantiaEquiv" value=""""),_display_(/*158.58*/datos/*158.63*/.garantiaEquiv),format.raw/*158.77*/("""">
							"""),_display_(if(datos.garantiaVenc==null)/*159.37*/{_display_(Seq[Any](format.raw/*159.38*/("""
								"""),format.raw/*160.9*/("""<input type="hidden" name="garantiaVenc" value="">
							""")))}else/*161.13*/{_display_(Seq[Any](format.raw/*161.14*/("""
								"""),format.raw/*162.9*/("""<input type="hidden" name="garantiaVenc" value=""""),_display_(/*162.58*/utilities/*162.67*/.Fechas.AAMMDD(datos.garantiaVenc)),format.raw/*162.101*/("""">
							""")))}),format.raw/*163.9*/("""
							
						""")))} else {null} ),format.raw/*165.8*/("""
						
						
						"""),format.raw/*168.7*/("""<input type="hidden" id="nomRepresEmpresa" name="nomRepresEmpresa" value=""""),_display_(/*168.82*/datos/*168.87*/.nomRepresEmpresa),format.raw/*168.104*/("""">
						<input type="hidden" id="rutRepresEmpresa" name="rutRepresEmpresa" value=""""),_display_(/*169.82*/datos/*169.87*/.rutRepresEmpresa),format.raw/*169.104*/("""">
						<input type="hidden" id="listadoPlanos" name="listadoPlanos" value=""""),_display_(/*170.76*/datos/*170.81*/.listadoPlanos),format.raw/*170.95*/("""">
						<input type="hidden" id="fechaPlanos" name="fechaPlanos" value=""""),_display_(/*171.72*/datos/*171.77*/.fechaPlanos),format.raw/*171.89*/("""">
						<input type="hidden" id="direccionObra" name="direccionObra" value=""""),_display_(/*172.76*/datos/*172.81*/.direccionObra),format.raw/*172.95*/("""">
						
						
						"""),_display_(if(mapDiccionario.get("nEmpresa").equals("CONCONCRETO ENCOFRADOS"))/*175.75*/{_display_(Seq[Any](format.raw/*175.76*/("""
							"""),format.raw/*176.8*/("""<tr>
								<td style="text-align:left;">Representante Conconcreto:</td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										autocomplete="off"
										value=""""),_display_(/*181.19*/datos/*181.24*/.nomRepresEmpresa),format.raw/*181.41*/(""""
										onchange = "$('#nomRepresEmpresa').val(value)"
										maxlength="250">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">Cédula Representante:</td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										autocomplete="off"
										value=""""),_display_(/*191.19*/datos/*191.24*/.rutRepresEmpresa),format.raw/*191.41*/(""""
										onchange = "$('#rutRepresEmpresa').val(value)"
										maxlength="50">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">Lista de Planos:</td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										autocomplete="off"
										value=""""),_display_(/*201.19*/datos/*201.24*/.listadoPlanos),format.raw/*201.38*/(""""
										onchange = "$('#listadoPlanos').val(value)"
										maxlength="250">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">Fecha de Planos:</td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										autocomplete="off"
										value=""""),_display_(/*211.19*/datos/*211.24*/.fechaPlanos),format.raw/*211.36*/(""""
										onchange = "$('#fechaPlanos').val(value)"
										maxlength="50">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">Dirección Obra:</td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										autocomplete="off"
										value=""""),_display_(/*221.19*/datos/*221.24*/.direccionObra),format.raw/*221.38*/(""""
										onchange = "$('#direccionObra').val(value)"
										maxlength="250">
								</td>
							</tr>
							
						""")))} else {null} ),format.raw/*227.8*/("""
						
						
						
						
						"""),_display_(if(mapDiccionario.get("nEmpresa").equals("ARRIVECO"))/*232.61*/{_display_(Seq[Any](format.raw/*232.62*/("""
							"""),format.raw/*233.8*/("""<tr>
								<td style="text-align: left;">"""),_display_(/*234.40*/mapDiccionario/*234.54*/.get("Region")),format.raw/*234.68*/(""": </td>
								<td style="text-align: left;">
									<input type="hidden" id="clienteRegion" name="clienteRegion" value=""""),_display_(/*236.79*/datos/*236.84*/.clienteRegion),format.raw/*236.98*/("""">
									<select class="input-large" id="selectRegion" style="text-align:left;width:100%" 
												onchange="actualizaComunas(value,'cod_comuna');">
										<option value=""""),_display_(/*239.27*/region/*239.33*/.codigo),format.raw/*239.40*/("""">"""),_display_(/*239.43*/region/*239.49*/.nombre),format.raw/*239.56*/("""</option>
										"""),_display_(/*240.12*/for(lista <- listRegiones) yield /*240.38*/{_display_(Seq[Any](format.raw/*240.39*/("""
									     	"""),format.raw/*241.16*/("""<option value=""""),_display_(/*241.32*/lista/*241.37*/.codigo),format.raw/*241.44*/("""">"""),_display_(/*241.47*/lista/*241.52*/.nombre),format.raw/*241.59*/("""</option>
										""")))}),format.raw/*242.12*/("""
									"""),format.raw/*243.10*/("""</select>
								</td>
							</tr>
							<tr>
								<td style="text-align: left;">"""),_display_(/*247.40*/mapDiccionario/*247.54*/.get("Comuna")),format.raw/*247.68*/(""": </td>
								<td style="text-align: left;">
									<input type="hidden" id="clienteComuna" name="clienteComuna" value=""""),_display_(/*249.79*/datos/*249.84*/.clienteComuna),format.raw/*249.98*/("""">
									<div id="selectComuna">
										<select class="input-large" style="text-align:left;width:100%">
											<option value=""""),_display_(/*252.28*/comuna/*252.34*/.codigo),format.raw/*252.41*/("""">"""),_display_(/*252.44*/comuna/*252.50*/.nombre),format.raw/*252.57*/("""</option>
											"""),_display_(/*253.13*/for(lista <- listComunas) yield /*253.38*/{_display_(Seq[Any](format.raw/*253.39*/("""
										     	"""),format.raw/*254.17*/("""<option value=""""),_display_(/*254.33*/lista/*254.38*/.nombre),format.raw/*254.45*/("""">"""),_display_(/*254.48*/lista/*254.53*/.nombre),format.raw/*254.60*/("""</option>
											""")))}),format.raw/*255.13*/("""
										"""),format.raw/*256.11*/("""</select>
									</div>
								</td>
							</tr>	
							<input type="hidden" name="clienteContacto" value=""""),_display_(/*260.60*/datos/*260.65*/.clienteContacto),format.raw/*260.81*/("""">
							<input type="hidden" name="clienteCargoContacto" value=""""),_display_(/*261.65*/datos/*261.70*/.clienteCargoContacto),format.raw/*261.91*/("""">
							<input type="hidden" name="usadosEn" value=""""),_display_(/*262.53*/datos/*262.58*/.usadosEn),format.raw/*262.67*/("""">
							<tr>
								<td style="text-align: left;">NUMERO O.COMPRA:</td>
								<td style="text-align: left;">
								  	<input type="text"   name="clienteOC" 	value=""""),_display_(/*266.59*/datos/*266.64*/.clienteOC),format.raw/*266.74*/("""" autocomplete="off"
								  		class="input-large" onkeydown="return sinReservados(window.event)" style="text-align:left;width:100%" maxlength="50">
							    </td>
							</tr>
							<tr>
								<td style="text-align: left;">FECHA O.COMPRA:</td>
								<td style="text-align: left;">
									"""),_display_(if(datos.clienteFechaOC==null || datos.clienteFechaOC.equals(""))/*273.76*/{_display_(Seq[Any](format.raw/*273.77*/("""
										"""),format.raw/*274.11*/("""<input type="date" name="clienteFechaOC" class="input-large" style="text-align:left;width:100%">
									""")))}else/*275.15*/{_display_(Seq[Any](format.raw/*275.16*/("""
										"""),format.raw/*276.11*/("""<input type="date" name="clienteFechaOC" 
								  		value=""""),_display_(/*277.21*/datos/*277.26*/.clienteFechaOC),format.raw/*277.41*/(""""
								  		class="input-large" style="text-align:left;width:100%">
									""")))}),format.raw/*279.11*/("""
							    """),format.raw/*280.12*/("""</td>
							</tr>
							<input type="hidden" name="garantiaDoc" value=""""),_display_(/*282.56*/datos/*282.61*/.garantiaDoc),format.raw/*282.73*/("""">
							<tr>
								<td style="text-align: left;">DETALLE GARANTIA:</td>
								<td style="text-align: left;">
								  	<textarea name="garantiaDet" rows="5" style="width:100%">"""),_display_(/*286.70*/datos/*286.75*/.garantiaDet),format.raw/*286.87*/("""</textarea>
							    </td>
							</tr>
							<input type="hidden" name="garantiaEquiv" value=""""),_display_(/*289.58*/datos/*289.63*/.garantiaEquiv),format.raw/*289.77*/("""">
							"""),_display_(if(datos.garantiaVenc==null || datos.garantiaVenc.equals(""))/*290.70*/{_display_(Seq[Any](format.raw/*290.71*/("""
								"""),format.raw/*291.9*/("""<input type="hidden" name="garantiaVenc" value="">
							""")))}else/*292.13*/{_display_(Seq[Any](format.raw/*292.14*/("""
								"""),format.raw/*293.9*/("""<input type="hidden" name="garantiaVenc" value=""""),_display_(/*293.58*/datos/*293.63*/.garantiaVenc.substring(6)),format.raw/*293.89*/("""-"""),_display_(/*293.91*/datos/*293.96*/.garantiaVenc.substring(3,5)),format.raw/*293.124*/("""-"""),_display_(/*293.126*/datos/*293.131*/.garantiaVenc.substring(0,2)),format.raw/*293.159*/("""">
							""")))}),format.raw/*294.9*/("""
						""")))} else {null} ),format.raw/*295.8*/("""
						
						
						
						
						"""),_display_(if(mapDiccionario.get("nEmpresa").equals("MONTAX"))/*300.59*/{_display_(Seq[Any](format.raw/*300.60*/("""
							"""),format.raw/*301.8*/("""<tr>
								<td style="text-align:left;">"""),_display_(/*302.39*/mapDiccionario/*302.53*/.get("Region")),format.raw/*302.67*/(""": </td>
								<td style="text-align:left;">
									<input type="hidden" id="clienteRegion" name="clienteRegion" value=""""),_display_(/*304.79*/datos/*304.84*/.clienteRegion),format.raw/*304.98*/("""">
									<select class="custom-select" 
										id="cod_region" 
										onchange="modificarCliente(id); actualizaComunas(value);">
										"""),_display_(/*308.12*/for(lista <- listRegiones) yield /*308.38*/{_display_(Seq[Any](format.raw/*308.39*/("""
											"""),format.raw/*309.12*/("""<option value=""""),_display_(/*309.28*/lista/*309.33*/.codigo),format.raw/*309.40*/("""">"""),_display_(/*309.43*/lista/*309.48*/.nombre),format.raw/*309.55*/("""</option>
										""")))}),format.raw/*310.12*/("""
									"""),format.raw/*311.10*/("""</select>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">"""),_display_(/*315.39*/mapDiccionario/*315.53*/.get("Comuna")),format.raw/*315.67*/(""": </td>
								<td style="text-align:left;">
									<input type="hidden" id="clienteComuna" name="clienteComuna" value=""""),_display_(/*317.79*/datos/*317.84*/.clienteComuna),format.raw/*317.98*/("""">
									<div id="selectComuna">
										<select class="custom-select" 
											id="cod_comuna" 
											onchange="modificarCliente(id);">
											"""),_display_(/*322.13*/for(lista <- listComunas) yield /*322.38*/{_display_(Seq[Any](format.raw/*322.39*/("""
												"""),format.raw/*323.13*/("""<option value=""""),_display_(/*323.29*/lista/*323.34*/.codigo),format.raw/*323.41*/("""">"""),_display_(/*323.44*/lista/*323.49*/.nombre),format.raw/*323.56*/("""</option>
											""")))}),format.raw/*324.13*/("""
										"""),format.raw/*325.11*/("""</select>
									</div>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">CONTACTO CLIENTE: </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										name="clienteContacto"
										autocomplete="off"
										value=""""),_display_(/*335.19*/datos/*335.24*/.clienteContacto),format.raw/*335.40*/(""""
										maxlength="50">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">CARGO CONTACTO: </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										name="clienteCargoContacto"
										autocomplete="off"
										value=""""),_display_(/*345.19*/datos/*345.24*/.clienteCargoContacto),format.raw/*345.45*/(""""
										maxlength="100">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">PARA USO EN: </td>
								<td style="text-align:left;">
									<textarea class="form-control" rows="1"
										name="usadosEn" 
										autocomplete="off">"""),_display_(/*354.31*/datos/*354.36*/.usadosEn),format.raw/*354.45*/("""</textarea>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">NUMERO O.COMPRA: </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										name="clienteOC"
										autocomplete="off"
										value=""""),_display_(/*363.19*/datos/*363.24*/.clienteOC),format.raw/*363.34*/(""""
										maxlength="50">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">FECHA O.COMPRA:</td>
								<td style="text-align:left;">
									"""),_display_(if(datos.clienteFechaOC==null)/*370.41*/{_display_(Seq[Any](format.raw/*370.42*/("""
										"""),format.raw/*371.11*/("""<input type="date" class="form-control center"
								  			name="clienteFechaOC">
									""")))}else/*373.15*/{_display_(Seq[Any](format.raw/*373.16*/("""
										"""),format.raw/*374.11*/("""<input type="date" class="form-control center"
								  			name="clienteFechaOC"
								  			value=""""),_display_(/*376.22*/utilities/*376.31*/.Fechas.AAMMDD(datos.clienteFechaOC)),format.raw/*376.67*/("""">
									""")))}),format.raw/*377.11*/("""
								"""),format.raw/*378.9*/("""</td>
							</tr>
							<tr>
								<td style="text-align:left;">DOCUMENTO GARANTIA: </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										name="garantiaDoc"
										autocomplete="off"
										value=""""),_display_(/*386.19*/datos/*386.24*/.garantiaDoc),format.raw/*386.36*/(""""
										maxlength="50">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">DETALLE GARANTIA: </td>
								<td style="text-align:left;">
									<textarea class="form-control" rows="1"
										name="garantiaDet" 
										autocomplete="off">"""),_display_(/*395.31*/datos/*395.36*/.garantiaDet),format.raw/*395.48*/("""</textarea>
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">EQUIVALENTE A: </td>
								<td style="text-align:left;">
									<input type="text" class="form-control left"
										name="garantiaEquiv"
										autocomplete="off"
										value=""""),_display_(/*404.19*/datos/*404.24*/.garantiaEquiv),format.raw/*404.38*/(""""
										maxlength="50">
								</td>
							</tr>
							<tr>
								<td style="text-align:left;">VENCIMIENTO GARANTIA:</td>
								<td style="text-align:left;">
									"""),_display_(if(datos.garantiaVenc==null)/*411.39*/{_display_(Seq[Any](format.raw/*411.40*/("""
										"""),format.raw/*412.11*/("""<input type="date" class="form-control center"
								  			name="garantiaVenc">
									""")))}else/*414.15*/{_display_(Seq[Any](format.raw/*414.16*/("""
										"""),format.raw/*415.11*/("""<input type="date" class="form-control center"
								  			name="garantiaVenc"
								  			value=""""),_display_(/*417.22*/utilities/*417.31*/.Fechas.AAMMDD(datos.garantiaVenc)),format.raw/*417.65*/("""">
									""")))}),format.raw/*418.11*/("""
								"""),format.raw/*419.9*/("""</td>
							</tr>
						""")))} else {null} ),format.raw/*421.8*/("""
					"""),format.raw/*422.6*/("""</table>
				</div>
			</div>
			<div class="noprint">
				<div class="row justify-content-md-center" >
					<div class="col-xs-5 col-sm-2 col-md-2 col-lg-2">
						<input type="submit" id="btnSubmit"  value="GENERAR CONTRATO" class="btn btn-success btn-sm rounded-pill btn-block">
					</div>
				</div>
			</div>
		</form>
	</div>

""")))}),format.raw/*435.2*/("""



"""),format.raw/*439.1*/("""<script type="text/javascript">

	let id_cliente = """"),_display_(/*441.21*/datos/*441.26*/.id_cliente),format.raw/*441.37*/("""";

	$(document).ready(function() """),format.raw/*443.31*/("""{"""),format.raw/*443.32*/("""
		  """),format.raw/*444.5*/("""$('#cod_region > option[value=""""),_display_(/*444.37*/region/*444.43*/.codigo),format.raw/*444.50*/(""""]').attr('selected', 'selected');
		  $('#cod_comuna > option[value=""""),_display_(/*445.37*/comuna/*445.43*/.codigo),format.raw/*445.50*/(""""]').attr('selected', 'selected');
		  document.getElementById('mostrarmostrar').style.display="block";
	"""),format.raw/*447.2*/("""}"""),format.raw/*447.3*/(""");
	
	const actualizaComunas = (cod_region) => """),format.raw/*449.43*/("""{"""),format.raw/*449.44*/("""
		
		"""),format.raw/*451.3*/("""var elt = document.getElementById("selectRegion");
		var region = elt.options[elt.selectedIndex].text;
		$("#clienteRegion").val(region);
		
		var formData = new FormData();
		formData.append('cod_region', cod_region);
		formData.append('id_cliente',id_cliente);
	  	$.ajax("""),format.raw/*458.12*/("""{"""),format.raw/*458.13*/("""
            """),format.raw/*459.13*/("""url: "/selComuna3Ajax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(data)"""),format.raw/*466.31*/("""{"""),format.raw/*466.32*/("""
	     		"""),format.raw/*467.9*/("""if(data=="error")"""),format.raw/*467.26*/("""{"""),format.raw/*467.27*/("""
	     			"""),format.raw/*468.10*/("""alertify.alert(msgError, function () """),format.raw/*468.47*/("""{"""),format.raw/*468.48*/("""
		     			"""),format.raw/*469.11*/("""location.href = "/";
		     		"""),format.raw/*470.10*/("""}"""),format.raw/*470.11*/(""");
	     		"""),format.raw/*471.9*/("""}"""),format.raw/*471.10*/("""else"""),format.raw/*471.14*/("""{"""),format.raw/*471.15*/("""
	     			"""),format.raw/*472.10*/("""data = data.replace(/&lt;/g,"<");
					data = data.replace(/&gt;/g,">");
					data = data.replace(/&#x27;/g,"\"");
	     			document.getElementById('selectComuna').innerHTML = data;
	     		"""),format.raw/*476.9*/("""}"""),format.raw/*476.10*/("""
	     	"""),format.raw/*477.8*/("""}"""),format.raw/*477.9*/("""
        """),format.raw/*478.9*/("""}"""),format.raw/*478.10*/(""");
	"""),format.raw/*479.2*/("""}"""),format.raw/*479.3*/("""
	
	"""),format.raw/*481.2*/("""const modificarCliente = (campo) => """),format.raw/*481.38*/("""{"""),format.raw/*481.39*/("""
		"""),format.raw/*482.3*/("""let valor = $("#"+campo).val();
		var formData = new FormData();
	  	formData.append('campo',campo);
	  	formData.append('id_cliente',id_cliente);
	  	formData.append('valor',valor);
        $.ajax("""),format.raw/*487.16*/("""{"""),format.raw/*487.17*/("""
            """),format.raw/*488.13*/("""url: "/modificaClientePorCampoAjax/",
            type: "POST",
            method: "POST",
            data: formData,
            cache: false,
            contentType: false,
	     	processData: false,
	     	success: function(respuesta)"""),format.raw/*495.36*/("""{"""),format.raw/*495.37*/("""}"""),format.raw/*495.38*/("""
        """),format.raw/*496.9*/("""}"""),format.raw/*496.10*/(""");
	"""),format.raw/*497.2*/("""}"""),format.raw/*497.3*/("""
	
	"""),format.raw/*499.2*/("""const validarForm = () =>"""),format.raw/*499.27*/("""{"""),format.raw/*499.28*/("""
		"""),format.raw/*500.3*/("""$("#btnSubmit").attr('disabled',true);
		return(true);
	"""),format.raw/*502.2*/("""}"""),format.raw/*502.3*/("""
	

"""),format.raw/*505.1*/("""</script>

		
	
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,datos:forms.FormContrato,region:tables.Regiones,comuna:tables.Comunas,listRegiones:List[tables.Regiones],listComunas:List[tables.Comunas],listBodegas:List[List[String]],listProyectos:List[tables.Proyecto]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,datos,region,comuna,listRegiones,listComunas,listBodegas,listProyectos)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,forms.FormContrato,tables.Regiones,tables.Comunas,List[tables.Regiones],List[tables.Comunas],List[List[String]],List[tables.Proyecto]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,datos,region,comuna,listRegiones,listComunas,listBodegas,listProyectos) => apply(mapDiccionario,mapPermiso,userMnu,datos,region,comuna,listRegiones,listComunas,listBodegas,listProyectos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuCotizar/datosContrato.scala.html
                  HASH: 4473cfc8288bb41e8627c447fc46accb5e5e352d
                  MATRIX: 1147->1|1556->317|1589->325|1605->333|1644->335|1673->339|1741->387|1769->389|1846->440|1927->500|1957->503|2431->950|2445->955|2480->969|2565->1027|2579->1032|2611->1043|2769->1174|2783->1179|2818->1193|2964->1312|2987->1326|3019->1337|3048->1338|3251->1514|3265->1519|3297->1530|3615->1821|3629->1826|3662->1838|3972->2121|3986->2126|4024->2143|4351->2443|4365->2448|4408->2470|4536->2571|4559->2585|4591->2596|4620->2597|4843->2793|4857->2798|4903->2823|5233->3126|5247->3131|5295->3158|5521->3357|5560->3358|5598->3368|5734->3485|5773->3486|5811->3496|5936->3594|5954->3603|6010->3638|6079->3675|6115->3683|6396->3936|6411->3941|6448->3956|6752->4232|6767->4237|6802->4250|7107->4527|7122->4532|7156->4544|7492->4852|7507->4857|7545->4873|7757->5056|7798->5057|7834->5065|7912->5115|7927->5120|7963->5134|8051->5194|8066->5199|8102->5213|8192->5275|8207->5280|8245->5296|8340->5363|8355->5368|8398->5389|8481->5444|8496->5449|8527->5458|8611->5514|8626->5519|8658->5529|8727->5570|8767->5571|8804->5580|8889->5645|8929->5646|8966->5655|9045->5706|9064->5715|9123->5751|9165->5762|9201->5770|9277->5818|9292->5823|9326->5835|9412->5893|9427->5898|9461->5910|9549->5970|9564->5975|9600->5989|9667->6028|9707->6029|9744->6038|9827->6101|9867->6102|9904->6111|9981->6160|10000->6169|10057->6203|10099->6214|10159->6230|10208->6251|10311->6326|10326->6331|10366->6348|10478->6432|10493->6437|10533->6454|10639->6532|10654->6537|10690->6551|10792->6625|10807->6630|10841->6642|10947->6720|10962->6725|10998->6739|11117->6830|11157->6831|11193->6839|11434->7052|11449->7057|11488->7074|11844->7402|11859->7407|11898->7424|12248->7746|12263->7751|12299->7765|12647->8085|12662->8090|12696->8102|13040->8418|13055->8423|13091->8437|13260->8562|13377->8651|13417->8652|13453->8660|13525->8704|13549->8718|13585->8732|13738->8857|13753->8862|13789->8876|13999->9058|14015->9064|14044->9071|14075->9074|14091->9080|14120->9087|14169->9108|14212->9134|14252->9135|14297->9151|14341->9167|14356->9172|14385->9179|14416->9182|14431->9187|14460->9194|14513->9215|14552->9225|14668->9313|14692->9327|14728->9341|14881->9466|14896->9471|14932->9485|15097->9622|15113->9628|15142->9635|15173->9638|15189->9644|15218->9651|15268->9673|15310->9698|15350->9699|15396->9716|15440->9732|15455->9737|15484->9744|15515->9747|15530->9752|15559->9759|15613->9781|15653->9792|15794->9905|15809->9910|15847->9926|15942->9993|15957->9998|16000->10019|16083->10074|16098->10079|16129->10088|16329->10260|16344->10265|16376->10275|16770->10641|16810->10642|16850->10653|16981->10764|17021->10765|17061->10776|17151->10838|17166->10843|17203->10858|17315->10938|17356->10950|17458->11024|17473->11029|17507->11041|17719->11225|17734->11230|17768->11242|17895->11341|17910->11346|17946->11360|18046->11432|18086->11433|18123->11442|18206->11505|18246->11506|18283->11515|18360->11564|18375->11569|18423->11595|18453->11597|18468->11602|18519->11630|18550->11632|18566->11637|18617->11665|18659->11676|18711->11684|18826->11771|18866->11772|18902->11780|18973->11823|18997->11837|19033->11851|19185->11975|19200->11980|19236->11994|19414->12144|19457->12170|19497->12171|19538->12183|19582->12199|19597->12204|19626->12211|19657->12214|19672->12219|19701->12226|19754->12247|19793->12257|19908->12344|19932->12358|19968->12372|20120->12496|20135->12501|20171->12515|20361->12677|20403->12702|20443->12703|20485->12716|20529->12732|20544->12737|20573->12744|20604->12747|20619->12752|20648->12759|20702->12781|20742->12792|21068->13090|21083->13095|21121->13111|21452->13414|21467->13419|21510->13440|21806->13708|21821->13713|21852->13722|22157->13999|22172->14004|22204->14014|22435->14217|22475->14218|22515->14229|22632->14326|22672->14327|22712->14338|22843->14441|22862->14450|22920->14486|22965->14499|23002->14508|23292->14770|23307->14775|23341->14787|23644->15062|23659->15067|23693->15079|24000->15358|24015->15363|24051->15377|24286->15584|24326->15585|24366->15596|24481->15691|24521->15692|24561->15703|24690->15804|24709->15813|24765->15847|24810->15860|24847->15869|24917->15895|24951->15901|25317->16236|25349->16240|25430->16293|25445->16298|25478->16309|25541->16343|25571->16344|25604->16349|25664->16381|25680->16387|25709->16394|25808->16465|25824->16471|25853->16478|25986->16583|26015->16584|26091->16631|26121->16632|26155->16638|26458->16912|26488->16913|26530->16926|26781->17148|26811->17149|26848->17158|26894->17175|26924->17176|26963->17186|27029->17223|27059->17224|27099->17235|27158->17265|27188->17266|27227->17277|27257->17278|27290->17282|27320->17283|27359->17293|27577->17483|27607->17484|27643->17492|27672->17493|27709->17502|27739->17503|27771->17507|27800->17508|27832->17512|27897->17548|27927->17549|27958->17552|28185->17750|28215->17751|28257->17764|28526->18004|28556->18005|28586->18006|28623->18015|28653->18016|28685->18020|28714->18021|28746->18025|28800->18050|28830->18051|28861->18054|28945->18110|28974->18111|29006->18115
                  LINES: 28->1|35->4|37->6|37->6|37->6|39->8|39->8|40->9|41->10|41->10|42->11|49->18|49->18|49->18|50->19|50->19|50->19|54->23|54->23|54->23|60->29|60->29|60->29|60->29|65->34|65->34|65->34|76->45|76->45|76->45|86->55|86->55|86->55|96->65|96->65|96->65|101->70|101->70|101->70|101->70|106->75|106->75|106->75|116->85|116->85|116->85|123->92|123->92|124->93|127->96|127->96|128->97|130->99|130->99|130->99|132->101|133->102|141->110|141->110|141->110|151->120|151->120|151->120|161->130|161->130|161->130|170->139|170->139|170->139|175->144|175->144|176->145|176->145|176->145|176->145|177->146|177->146|177->146|178->147|178->147|178->147|179->148|179->148|179->148|180->149|180->149|180->149|181->150|181->150|181->150|182->151|182->151|183->152|184->153|184->153|185->154|185->154|185->154|185->154|186->155|187->156|187->156|187->156|187->156|188->157|188->157|188->157|189->158|189->158|189->158|190->159|190->159|191->160|192->161|192->161|193->162|193->162|193->162|193->162|194->163|196->165|199->168|199->168|199->168|199->168|200->169|200->169|200->169|201->170|201->170|201->170|202->171|202->171|202->171|203->172|203->172|203->172|206->175|206->175|207->176|212->181|212->181|212->181|222->191|222->191|222->191|232->201|232->201|232->201|242->211|242->211|242->211|252->221|252->221|252->221|258->227|263->232|263->232|264->233|265->234|265->234|265->234|267->236|267->236|267->236|270->239|270->239|270->239|270->239|270->239|270->239|271->240|271->240|271->240|272->241|272->241|272->241|272->241|272->241|272->241|272->241|273->242|274->243|278->247|278->247|278->247|280->249|280->249|280->249|283->252|283->252|283->252|283->252|283->252|283->252|284->253|284->253|284->253|285->254|285->254|285->254|285->254|285->254|285->254|285->254|286->255|287->256|291->260|291->260|291->260|292->261|292->261|292->261|293->262|293->262|293->262|297->266|297->266|297->266|304->273|304->273|305->274|306->275|306->275|307->276|308->277|308->277|308->277|310->279|311->280|313->282|313->282|313->282|317->286|317->286|317->286|320->289|320->289|320->289|321->290|321->290|322->291|323->292|323->292|324->293|324->293|324->293|324->293|324->293|324->293|324->293|324->293|324->293|324->293|325->294|326->295|331->300|331->300|332->301|333->302|333->302|333->302|335->304|335->304|335->304|339->308|339->308|339->308|340->309|340->309|340->309|340->309|340->309|340->309|340->309|341->310|342->311|346->315|346->315|346->315|348->317|348->317|348->317|353->322|353->322|353->322|354->323|354->323|354->323|354->323|354->323|354->323|354->323|355->324|356->325|366->335|366->335|366->335|376->345|376->345|376->345|385->354|385->354|385->354|394->363|394->363|394->363|401->370|401->370|402->371|404->373|404->373|405->374|407->376|407->376|407->376|408->377|409->378|417->386|417->386|417->386|426->395|426->395|426->395|435->404|435->404|435->404|442->411|442->411|443->412|445->414|445->414|446->415|448->417|448->417|448->417|449->418|450->419|452->421|453->422|466->435|470->439|472->441|472->441|472->441|474->443|474->443|475->444|475->444|475->444|475->444|476->445|476->445|476->445|478->447|478->447|480->449|480->449|482->451|489->458|489->458|490->459|497->466|497->466|498->467|498->467|498->467|499->468|499->468|499->468|500->469|501->470|501->470|502->471|502->471|502->471|502->471|503->472|507->476|507->476|508->477|508->477|509->478|509->478|510->479|510->479|512->481|512->481|512->481|513->482|518->487|518->487|519->488|526->495|526->495|526->495|527->496|527->496|528->497|528->497|530->499|530->499|530->499|531->500|533->502|533->502|536->505
                  -- GENERATED --
              */
          