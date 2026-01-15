// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes3.routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:4
package controllers {

  // @LINE:4
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:4
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:9
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def redirOdoShowPDF(fileNamePdf:String, desde:String, hasta:String, url:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "redirOdoShowPDF/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("fileNamePdf", fileNamePdf)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("desde", desde)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("hasta", hasta)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("url", url)))
    }
  
    // @LINE:13
    def tasasDeFechaAjax(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "tasasDeFechaAjax/")
    }
  
    // @LINE:14
    def sendEmail(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "sendEmail/")
    }
  
    // @LINE:12
    def downloadPDF(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "downloadPDF/")
    }
  
    // @LINE:10
    def showPdf(fileNamePdf:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "showPdf/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("fileNamePdf", fileNamePdf)))
    }
  
    // @LINE:16
    def redirShowPDF(fileNamePdf:String, titulo:String, url:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "redirShowPDF/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("fileNamePdf", fileNamePdf)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("titulo", titulo)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("url", url)))
    }
  
    // @LINE:11
    def showPdfManuales(fileNamePdf:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "showPdfManuales/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("fileNamePdf", fileNamePdf)))
    }
  
    // @LINE:9
    def viewImg(img:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "viewImg/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("img", img)))
    }
  
  }

  // @LINE:27
  class ReverseMnuMantencion(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:33
    def mantWebListarReports1Get(de:String, a:String, id_equipo:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantWebListarReports1Get/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("de", de)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("a", a)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id_equipo", id_equipo)))
    }
  
    // @LINE:152
    def mantTblOperadorMecanicoVig(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblOperadorMecanicoVig/")
    }
  
    // @LINE:142
    def mantTblEstadoOperacionalNewSave(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblEstadoOperacionalNewSave/")
    }
  
    // @LINE:133
    def mantTblEstadoEnTallerMant(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblEstadoEnTallerMant/")
    }
  
    // @LINE:110
    def mantTblActividadDel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblActividadDel/")
    }
  
    // @LINE:61
    def mantReportGrabaDocAnexo(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantReportGrabaDocAnexo/")
    }
  
    // @LINE:59
    def mantReportDetalle(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantReportDetalle/")
    }
  
    // @LINE:50
    def mantReportNew(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantReportNew/")
    }
  
    // @LINE:114
    def mantTblTipoActividadNewSave(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblTipoActividadNewSave/")
    }
  
    // @LINE:112
    def mantTblTipoActividadMant(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblTipoActividadMant/")
    }
  
    // @LINE:39
    def mantWebFirmaOperador(id_venta:Long, de:String, a:String, id_equipo:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantWebFirmaOperador/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id_venta", id_venta)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("de", de)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("a", a)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id_equipo", id_equipo)))
    }
  
    // @LINE:31
    def mantWebListarReports0(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantWebListarReports0/")
    }
  
    // @LINE:140
    def mantTblEstadoOperacionalMant(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblEstadoOperacionalMant/")
    }
  
    // @LINE:105
    def mantTblActividadMant(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblActividadMant/")
    }
  
    // @LINE:149
    def mantTblOperadorMecanicoNewSave(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblOperadorMecanicoNewSave/")
    }
  
    // @LINE:119
    def mantTblComponenteMant(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblComponenteMant/")
    }
  
    // @LINE:56
    def mantListarReports0(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantListarReports0/")
    }
  
    // @LINE:109
    def modificaActividadPorCampoAjax(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "modificaActividadPorCampoAjax/")
    }
  
    // @LINE:130
    def modificaEstadoEnObraPorCampoAjax(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "modificaEstadoEnObraPorCampoAjax/")
    }
  
    // @LINE:38
    def mantWebReportGrabaDocAnexo(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantWebReportGrabaDocAnexo/")
    }
  
    // @LINE:42
    def mantWebGrabarFirmaAutorizador(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantWebGrabarFirmaAutorizador/")
    }
  
    // @LINE:148
    def mantTblOperadorMecanicoNew(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblOperadorMecanicoNew/")
    }
  
    // @LINE:71
    def mantHistorialReports0(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantHistorialReports0/")
    }
  
    // @LINE:106
    def mantTblActividadNew(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblActividadNew/")
    }
  
    // @LINE:121
    def mantTblComponenteNewSave(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblComponenteNewSave/")
    }
  
    // @LINE:73
    def mantHistorialReports1Excel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantHistorialReports1Excel/")
    }
  
    // @LINE:136
    def mantTblEstadoEnTallerUpdate(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblEstadoEnTallerUpdate/")
    }
  
    // @LINE:86
    def mantCantOperacional1Excel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantCantOperacional1Excel/")
    }
  
    // @LINE:62
    def mantFirmaOperador(id_venta:Long, desde:String, hasta:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantFirmaOperador/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id_venta", id_venta)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("desde", desde)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("hasta", hasta)))
    }
  
    // @LINE:84
    def mantCantOperacional0(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantCantOperacional0/")
    }
  
    // @LINE:123
    def modificaComponentePorCampoAjax(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "modificaComponentePorCampoAjax/")
    }
  
    // @LINE:60
    def mantListarReports1Excel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantListarReports1Excel/")
    }
  
    // @LINE:27
    def mantWebInicio1(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantWebInicio1/")
    }
  
    // @LINE:29
    def mantWebInicio1get(id_operMec:Long, id_equipo:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantWebInicio1get/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id_operMec", id_operMec)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id_equipo", id_equipo)))
    }
  
    // @LINE:137
    def modificaEstadoEnTallerPorCampoAjax(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "modificaEstadoEnTallerPorCampoAjax/")
    }
  
    // @LINE:138
    def mantTblEstadoEnTallerDel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblEstadoEnTallerDel/")
    }
  
    // @LINE:65
    def mantGrabarFirmaAutorizador(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantGrabarFirmaAutorizador/")
    }
  
    // @LINE:72
    def mantHistorialReports1(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantHistorialReports1/")
    }
  
    // @LINE:41
    def mantWebFirmaAutorizador(id_venta:Long, de:String, a:String, id_equipo:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantWebFirmaAutorizador/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id_venta", id_venta)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("de", de)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("a", a)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id_equipo", id_equipo)))
    }
  
    // @LINE:101
    def mantTblItemUpdate(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblItemUpdate/")
    }
  
    // @LINE:85
    def mantCantOperacional1(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantCantOperacional1/")
    }
  
    // @LINE:102
    def modificaItemPorCampoAjax(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "modificaItemPorCampoAjax/")
    }
  
    // @LINE:32
    def mantWebListarReports1(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantWebListarReports1/")
    }
  
    // @LINE:147
    def mantTblOperadorMecanicoMant(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblOperadorMecanicoMant/")
    }
  
    // @LINE:113
    def mantTblTipoActividadNew(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblTipoActividadNew/")
    }
  
    // @LINE:122
    def mantTblComponenteUpdate(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblComponenteUpdate/")
    }
  
    // @LINE:127
    def mantTblEstadoEnObraNew(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblEstadoEnObraNew/")
    }
  
    // @LINE:88
    def mantCantMantencion0(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantCantMantencion0/")
    }
  
    // @LINE:134
    def mantTblEstadoEnTallerNew(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblEstadoEnTallerNew/")
    }
  
    // @LINE:100
    def mantTblItemNewSave(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblItemNewSave/")
    }
  
    // @LINE:108
    def mantTblActividadUpdate(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblActividadUpdate/")
    }
  
    // @LINE:78
    def mantControlMantenciones(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantControlMantenciones/")
    }
  
    // @LINE:153
    def mantTblOperadorMecanicoVerifica(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblOperadorMecanicoVerifica/")
    }
  
    // @LINE:116
    def modificaTipoActividadPorCampoAjax(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "modificaTipoActividadPorCampoAjax/")
    }
  
    // @LINE:144
    def modificaEstadoOperacionalPorCampoAjax(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "modificaEstadoOperacionalPorCampoAjax/")
    }
  
    // @LINE:36
    def mantWebReportDetalle(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantWebReportDetalle/")
    }
  
    // @LINE:150
    def mantTblOperadorMecanicoUpdate(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblOperadorMecanicoUpdate/")
    }
  
    // @LINE:131
    def mantTblEstadoEnObraDel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblEstadoEnObraDel/")
    }
  
    // @LINE:103
    def mantTblItemDel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblItemDel/")
    }
  
    // @LINE:40
    def mantWebGrabarFirmaOperador(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantWebGrabarFirmaOperador/")
    }
  
    // @LINE:35
    def mantWebListarReports1Excel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantWebListarReports1Excel/")
    }
  
    // @LINE:64
    def mantFirmaAutorizador(id_venta:Long, desde:String, hasta:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantFirmaAutorizador/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id_venta", id_venta)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("desde", desde)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("hasta", hasta)))
    }
  
    // @LINE:115
    def mantTblTipoActividadUpdate(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblTipoActividadUpdate/")
    }
  
    // @LINE:89
    def mantCantMantencion1(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantCantMantencion1/")
    }
  
    // @LINE:90
    def mantCantMantencion1Excel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantCantMantencion1Excel/")
    }
  
    // @LINE:79
    def mantControlMantencionesExcel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantControlMantencionesExcel/")
    }
  
    // @LINE:107
    def mantTblActividadNewSave(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblActividadNewSave/")
    }
  
    // @LINE:145
    def mantTblEstadoOperacionalDel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblEstadoOperacionalDel/")
    }
  
    // @LINE:120
    def mantTblComponenteNew(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblComponenteNew/")
    }
  
    // @LINE:117
    def mantTblTipoActividadDel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblTipoActividadDel/")
    }
  
    // @LINE:124
    def mantTblComponenteDel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblComponenteDel/")
    }
  
    // @LINE:128
    def mantTblEstadoEnObraNewSave(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblEstadoEnObraNewSave/")
    }
  
    // @LINE:143
    def mantTblEstadoOperacionalUpdate(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblEstadoOperacionalUpdate/")
    }
  
    // @LINE:141
    def mantTblEstadoOperacionalNew(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblEstadoOperacionalNew/")
    }
  
    // @LINE:99
    def mantTblItemNew(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblItemNew/")
    }
  
    // @LINE:28
    def mantWebReportNewSave(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantWebReportNewSave/")
    }
  
    // @LINE:129
    def mantTblEstadoEnObraUpdate(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblEstadoEnObraUpdate/")
    }
  
    // @LINE:135
    def mantTblEstadoEnTallerNewSave(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantTblEstadoEnTallerNewSave/")
    }
  
    // @LINE:66
    def mantReportElimina(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantReportElimina/")
    }
  
    // @LINE:151
    def modificaOperadorMecanicoPorCampoAjax(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "modificaOperadorMecanicoPorCampoAjax/")
    }
  
    // @LINE:57
    def mantListarReports1(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantListarReports1/")
    }
  
    // @LINE:51
    def mantReportNewSave(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantReportNewSave/")
    }
  
    // @LINE:126
    def mantTblEstadoEnObraMant(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblEstadoEnObraMant/")
    }
  
    // @LINE:63
    def mantGrabarFirmaOperador(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "mantGrabarFirmaOperador/")
    }
  
    // @LINE:58
    def mantListarReports1Get(d:String, a:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantListarReports1Get/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("d", d)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("a", a)))
    }
  
    // @LINE:98
    def mantTblItemMant(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "mantTblItemMant/")
    }
  
  }

  // @LINE:160
  class ReverseMnuReportes(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:173
    def estadosFacturaProyectoExcel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "estadosFacturaProyectoExcel/")
    }
  
    // @LINE:169
    def estadosFacturaPeriodo(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "estadosFacturaPeriodo/")
    }
  
    // @LINE:177
    def enviarAlistAjustes(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "enviarAlistAjustes/")
    }
  
    // @LINE:167
    def proformaEstadoElimina(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "proformaEstadoElimina/")
    }
  
    // @LINE:174
    def estadosFacturaProyectoDetalle(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "estadosFacturaProyectoDetalle/")
    }
  
    // @LINE:170
    def estadosFacturaProyecto(archivoPDF:String): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "estadosFacturaProyecto/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("archivoPDF", archivoPDF)))
    }
  
    // @LINE:179
    def proformaEstadoListaPdf(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "proformaEstadoListaPdf/")
    }
  
    // @LINE:160
    def estadosMantCobraArriendo0(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "estadosMantCobraArriendo0/")
    }
  
    // @LINE:178
    def envioMasivoAlistAjustes(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "envioMasivoAlistAjustes/")
    }
  
    // @LINE:180
    def proformaEstadoListaExcel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "proformaEstadoListaExcel/")
    }
  
    // @LINE:165
    def proformaEstadoLista(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "proformaEstadoLista/")
    }
  
    // @LINE:164
    def proformaEstadoListaPeriodo(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "proformaEstadoListaPeriodo/")
    }
  
    // @LINE:175
    def estadosFacturaProyectoDetExcel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "estadosFacturaProyectoDetExcel/")
    }
  
    // @LINE:166
    def proformaEstadoListaGet(d:String, h:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "proformaEstadoListaGet/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("d", d)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("h", h)))
    }
  
    // @LINE:161
    def estadosMantCobraArriendo1(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "estadosMantCobraArriendo1/")
    }
  
    // @LINE:162
    def modCobraArriendoAjax(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "modCobraArriendoAjax/")
    }
  
    // @LINE:171
    def estadosFacturaProyectoGet(d:String, a:String, u:Double, e:Double, f:Double, p:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "estadosFacturaProyectoGet/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("d", d)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("a", a)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Double]].unbind("u", u)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Double]].unbind("e", e)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Double]].unbind("f", f)) + "," + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("p", p)))
    }
  
  }


}
