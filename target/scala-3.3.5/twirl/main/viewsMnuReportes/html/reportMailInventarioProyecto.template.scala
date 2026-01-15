
package viewsMnuReportes.html

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

object reportMailInventarioProyecto extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(mapDiccionario: Map[String,String], mapPermiso: Map[String,String], userMnu: utilities.UserMnu,
        lista: List[List[String]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.1*/("""
"""),_display_(/*4.2*/main("")/*4.10*/ {_display_(Seq[Any](format.raw/*4.12*/("""

    """),_display_(/*6.6*/menues(mapDiccionario, mapPermiso, userMnu, " ")),format.raw/*6.54*/("""

    """),_display_(/*8.6*/modalContactoBodega(mapDiccionario)),format.raw/*8.41*/("""
    """),_display_(/*9.6*/modalContactoCliente()),format.raw/*9.28*/("""
    """),_display_(/*10.6*/modalContactoProyecto()),format.raw/*10.29*/("""

    """),format.raw/*12.5*/("""<div id="mostrarmostrar" style="display:none;">
        """),_display_(/*13.10*/barraTitulo(mapDiccionario, "SELECCION DE "+mapDiccionario.get("BODEGA")+"/PROYECTO","PARA OBTENER DETALLE")),format.raw/*13.118*/("""

        """),format.raw/*15.9*/("""<div class="row justify-content-md-center">
            <div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">

                <div class="noprint">
                    <table class="table table-sm table-condensed table-fluid">
                        <tr>
                            <td width="25%">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">BUSCAR</span>
                                    </div>
                                    <input type="text" class="form-control left"
                                    id="searchTermtablaPrincipal"
                                    onkeyup="doSearch2('tablaPrincipal');">
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>

                """),_display_(if(lista != null && lista.nonEmpty)/*35.53*/ {_display_(Seq[Any](format.raw/*35.55*/("""
                    """),format.raw/*36.21*/("""<div class="table-responsive">

                        <form class="formulario" method="post" action="/mailingInventarioProyectoEnviar/">

                            <table id="tablaPrincipal" class="table table-sm table-hover table-bordered table-condensed table-fluid">
                                <thead style="background-color: #eeeeee">
                                    <tr>
                                        <th style="text-align:center; vertical-align:top;">SUCURSAL</th>
                                        <th style="text-align:center; vertical-align:top;">
                                            """),_display_(/*45.46*/mapDiccionario/*45.60*/.getOrElse("BODEGA", "BODEGA")),format.raw/*45.90*/("""/PROYECTO
                                        </th>
                                        <th style="text-align:center; vertical-align:top;">NOMBRE<BR>CLIENTE</th>
                                        <th style="text-align:center; vertical-align:top;">NOMBRE<br>PROYECTO</th>
                                        <th style="text-align:center; vertical-align:top;">
                                            """),_display_(/*50.46*/mapDiccionario/*50.60*/.getOrElse("COMUNA", "COMUNA")),format.raw/*50.90*/("""<BR>PROYECTO
                                        </th>

                                        <th class="col-seleccionar">
                                            <div class="sel-head">
                                                <span>SELEC</span>
                                                <input
                                                type="checkbox"
                                                id="selectAll"
                                                class="circle-check"
                                                title="Seleccionar / Deseleccionar todos"
                                                style="transform: scale(1.4); cursor:pointer;"
                                                >
                                            </div>
                                        </th>
                                    </tr>
                                </thead>

                                <tbody>
                                """),_display_(/*69.34*/for(lista1 <- lista) yield /*69.54*/ {_display_(Seq[Any](format.raw/*69.56*/("""
                                    """),format.raw/*70.37*/("""<tr>
                                        <td style="text-align:left;vertical-align:middle;">
                                        """),_display_(/*72.42*/lista1/*72.48*/.get(10)),format.raw/*72.56*/("""
                                        """),format.raw/*73.41*/("""</td>
                                        <td style="text-align:left;vertical-align:middle;">
                                            <a href="#" onclick="modalContactoBodega('"""),_display_(/*75.88*/lista1/*75.94*/.get(1)),format.raw/*75.101*/("""');">"""),_display_(/*75.107*/lista1/*75.113*/.get(5)),format.raw/*75.120*/("""</a>
                                        </td>
                                        <td style="text-align:left;vertical-align:middle;">
                                            <a href="#" onclick="modalContactoCliente('"""),_display_(/*78.89*/lista1/*78.95*/.get(2)),format.raw/*78.102*/("""');">"""),_display_(/*78.108*/lista1/*78.114*/.get(7)),format.raw/*78.121*/("""</a>
                                        </td>
                                        <td style="text-align:left;vertical-align:middle;">
                                            <a href="#" onclick="modalContactoProyecto('"""),_display_(/*81.90*/lista1/*81.96*/.get(3)),format.raw/*81.103*/("""');">"""),_display_(/*81.109*/lista1/*81.115*/.get(8)),format.raw/*81.122*/("""</a>
                                        </td>
                                        <td style="text-align:left;vertical-align:middle;">
                                        """),_display_(/*84.42*/lista1/*84.48*/.get(9)),format.raw/*84.55*/("""
                                        """),format.raw/*85.41*/("""</td>

                                        <td class="col-seleccionar">
                                            <div class="sel-cell">
                                                <input
                                                type="checkbox"
                                                class="select-proyecto"
                                                name="id_bodega[]"
                                                value=""""),_display_(/*93.57*/lista1/*93.63*/.get(1)),format.raw/*93.70*/(""""
                                                style="transform: scale(1.4); cursor: pointer;"
                                                />
                                            </div>
                                        </td>
                                    </tr>
                                """)))}),format.raw/*99.34*/("""
                                """),format.raw/*100.33*/("""</tbody>
                            </table>

                            <div class="noprint" style="margin-top: 12px; text-align: center;">
                                <button type="submit" class="btn btn-primary" id="btnEnviarMail">
                                    ENVIAR
                                </button>

                            </div>

                        </form>

                        <style>
                        .circle-check"""),format.raw/*113.38*/("""{"""),format.raw/*113.39*/("""
                            """),format.raw/*114.29*/("""-webkit-appearance: none;
                            appearance: none;
                            width: 15px;
                            height: 15px;
                            border: 2px solid #555;
                            border-radius: 50%;
                            display: inline-block;
                            cursor: pointer;
                            position: relative;
                            background: #fff;
                        """),format.raw/*124.25*/("""}"""),format.raw/*124.26*/("""

                        """),format.raw/*126.25*/(""".circle-check:checked"""),format.raw/*126.46*/("""{"""),format.raw/*126.47*/("""
                            """),format.raw/*127.29*/("""background: #555;
                        """),format.raw/*128.25*/("""}"""),format.raw/*128.26*/("""

                        """),format.raw/*130.25*/("""th.col-seleccionar,
                                td.col-seleccionar """),format.raw/*131.52*/("""{"""),format.raw/*131.53*/("""
                                    """),format.raw/*132.37*/("""text-align: center !important;
                                    vertical-align: middle !important;
                                    width: 8%;
                                    padding-left: 0 !important;
                                    padding-right: 0 !important;
                                """),format.raw/*137.33*/("""}"""),format.raw/*137.34*/("""

                                """),format.raw/*139.33*/(""".sel-head,
                                .sel-cell """),format.raw/*140.43*/("""{"""),format.raw/*140.44*/("""
                                    """),format.raw/*141.37*/("""display: flex;
                                    flex-direction: column;
                                    align-items: center;
                                    justify-content: center;
                                    gap: 4px;
                                    margin: 0;
                                    padding: 0;
                                """),format.raw/*148.33*/("""}"""),format.raw/*148.34*/("""


                        """),format.raw/*151.25*/("""</style>

                    </div>

                """)))}else/*155.24*/{_display_(Seq[Any](format.raw/*155.25*/("""
                    """),format.raw/*156.21*/("""<p>No hay datos disponibles para mostrar.</p>
                """)))}),format.raw/*157.18*/("""
            """),format.raw/*158.13*/("""</div>
        </div>
    </div>
""")))}),format.raw/*161.2*/("""

"""),format.raw/*163.1*/("""<script type="text/javascript">
    $(document).ready(function() """),format.raw/*164.34*/("""{"""),format.raw/*164.35*/("""

        """),format.raw/*166.9*/("""var table = $('#tablaPrincipal').DataTable("""),format.raw/*166.52*/("""{"""),format.raw/*166.53*/("""
            """),format.raw/*167.13*/(""""fixedHeader": true,
            "paging": false,
            "info": false,
            "searching": false,
            "order": [[ 1, "asc" ]], // sigue ordenando por BODEGA/PROYECTO
            "columnDefs": [
                """),format.raw/*173.17*/("""{"""),format.raw/*173.18*/("""
                    """),format.raw/*174.21*/(""""orderable": false,
                    "targets": -1   // última columna = SELECCIONAR
                """),format.raw/*176.17*/("""}"""),format.raw/*176.18*/("""
            """),format.raw/*177.13*/("""],
            "language": """),format.raw/*178.25*/("""{"""),format.raw/*178.26*/("""
                """),format.raw/*179.17*/(""""url": "https://cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json"
            """),format.raw/*180.13*/("""}"""),format.raw/*180.14*/("""
        """),format.raw/*181.9*/("""}"""),format.raw/*181.10*/(""");


        // Mostrar contenido (evita pantalla blanca)
        document.getElementById('mostrarmostrar').style.display = "block";

        $('#selectAll').on('change', function() """),format.raw/*187.49*/("""{"""),format.raw/*187.50*/("""
            """),format.raw/*188.13*/("""var checked = this.checked;

            var rows = table.rows("""),format.raw/*190.35*/("""{"""),format.raw/*190.36*/(""" """),format.raw/*190.37*/("""'search': 'applied' """),format.raw/*190.57*/("""}"""),format.raw/*190.58*/(""").nodes();

            // Marca/desmarca y DISPARA change para que se actualicen los hidden checked[]
            $('input.select-proyecto', rows)
                .prop('checked', checked)
                .trigger('change');
        """),format.raw/*196.9*/("""}"""),format.raw/*196.10*/(""");


        $('#tablaPrincipal').on('change', 'input.select-proyecto', function() """),format.raw/*199.79*/("""{"""),format.raw/*199.80*/("""
            """),format.raw/*200.13*/("""var rows = table.rows("""),format.raw/*200.35*/("""{"""),format.raw/*200.36*/(""" """),format.raw/*200.37*/("""'search': 'applied' """),format.raw/*200.57*/("""}"""),format.raw/*200.58*/(""").nodes();
            var total = $('input.select-proyecto', rows).length;
            var seleccionados = $('input.select-proyecto:checked', rows).length;

            $('#selectAll').prop('checked', total > 0 && total === seleccionados);
        """),format.raw/*205.9*/("""}"""),format.raw/*205.10*/(""");
    """),format.raw/*206.5*/("""}"""),format.raw/*206.6*/(""");
</script>
"""))
      }
    }
  }

  def render(mapDiccionario:Map[String,String],mapPermiso:Map[String,String],userMnu:utilities.UserMnu,lista:List[List[String]]): play.twirl.api.HtmlFormat.Appendable = apply(mapDiccionario,mapPermiso,userMnu,lista)

  def f:((Map[String,String],Map[String,String],utilities.UserMnu,List[List[String]]) => play.twirl.api.HtmlFormat.Appendable) = (mapDiccionario,mapPermiso,userMnu,lista) => apply(mapDiccionario,mapPermiso,userMnu,lista)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/viewsMnuReportes/reportMailInventarioProyecto.scala.html
                  HASH: bf8c7be976b94cee1f3c018402eb6f9433173fa8
                  MATRIX: 1047->1|1272->133|1299->135|1315->143|1354->145|1386->152|1454->200|1486->207|1541->242|1572->248|1614->270|1646->276|1690->299|1723->305|1807->362|1937->470|1974->480|3005->1484|3045->1486|3094->1507|3752->2138|3775->2152|3826->2182|4275->2604|4298->2618|4349->2648|5379->3651|5415->3671|5455->3673|5520->3710|5685->3848|5700->3854|5729->3862|5798->3903|6010->4088|6025->4094|6054->4101|6088->4107|6104->4113|6133->4120|6391->4351|6406->4357|6435->4364|6469->4370|6485->4376|6514->4383|6773->4615|6788->4621|6817->4628|6851->4634|6867->4640|6896->4647|7107->4831|7122->4837|7150->4844|7219->4885|7703->5342|7718->5348|7746->5355|8098->5676|8160->5709|8654->6174|8684->6175|8742->6204|9240->6673|9270->6674|9325->6700|9375->6721|9405->6722|9463->6751|9534->6793|9564->6794|9619->6820|9719->6891|9749->6892|9815->6929|10154->7239|10184->7240|10247->7274|10329->7327|10359->7328|10425->7365|10820->7731|10850->7732|10906->7759|10985->7820|11025->7821|11075->7842|11170->7905|11212->7918|11277->7952|11307->7954|11401->8019|11431->8020|11469->8030|11541->8073|11571->8074|11613->8087|11871->8316|11901->8317|11951->8338|12084->8442|12114->8443|12156->8456|12212->8483|12242->8484|12288->8501|12397->8581|12427->8582|12464->8591|12494->8592|12705->8774|12735->8775|12777->8788|12869->8851|12899->8852|12929->8853|12978->8873|13008->8874|13270->9108|13300->9109|13412->9192|13442->9193|13484->9206|13535->9228|13565->9229|13595->9230|13644->9250|13674->9251|13951->9500|13981->9501|14016->9508|14045->9509
                  LINES: 28->1|34->3|35->4|35->4|35->4|37->6|37->6|39->8|39->8|40->9|40->9|41->10|41->10|43->12|44->13|44->13|46->15|66->35|66->35|67->36|76->45|76->45|76->45|81->50|81->50|81->50|100->69|100->69|100->69|101->70|103->72|103->72|103->72|104->73|106->75|106->75|106->75|106->75|106->75|106->75|109->78|109->78|109->78|109->78|109->78|109->78|112->81|112->81|112->81|112->81|112->81|112->81|115->84|115->84|115->84|116->85|124->93|124->93|124->93|130->99|131->100|144->113|144->113|145->114|155->124|155->124|157->126|157->126|157->126|158->127|159->128|159->128|161->130|162->131|162->131|163->132|168->137|168->137|170->139|171->140|171->140|172->141|179->148|179->148|182->151|186->155|186->155|187->156|188->157|189->158|192->161|194->163|195->164|195->164|197->166|197->166|197->166|198->167|204->173|204->173|205->174|207->176|207->176|208->177|209->178|209->178|210->179|211->180|211->180|212->181|212->181|218->187|218->187|219->188|221->190|221->190|221->190|221->190|221->190|227->196|227->196|230->199|230->199|231->200|231->200|231->200|231->200|231->200|231->200|236->205|236->205|237->206|237->206
                  -- GENERATED --
              */
          