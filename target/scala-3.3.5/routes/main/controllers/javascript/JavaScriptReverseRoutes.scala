// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes3.routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:4
package controllers.javascript {

  // @LINE:4
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:4
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:9
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def redirOdoShowPDF: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.redirOdoShowPDF",
      """
        function(fileNamePdf0,desde1,hasta2,url3) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "redirOdoShowPDF/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("fileNamePdf", fileNamePdf0)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("desde", desde1)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("hasta", hasta2)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("url", url3))})
        }
      """
    )
  
    // @LINE:13
    def tasasDeFechaAjax: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.tasasDeFechaAjax",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "tasasDeFechaAjax/"})
        }
      """
    )
  
    // @LINE:14
    def sendEmail: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.sendEmail",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "sendEmail/"})
        }
      """
    )
  
    // @LINE:12
    def downloadPDF: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.downloadPDF",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "downloadPDF/"})
        }
      """
    )
  
    // @LINE:10
    def showPdf: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.showPdf",
      """
        function(fileNamePdf0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showPdf/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("fileNamePdf", fileNamePdf0))})
        }
      """
    )
  
    // @LINE:16
    def redirShowPDF: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.redirShowPDF",
      """
        function(fileNamePdf0,titulo1,url2) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "redirShowPDF/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("fileNamePdf", fileNamePdf0)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("titulo", titulo1)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("url", url2))})
        }
      """
    )
  
    // @LINE:11
    def showPdfManuales: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.showPdfManuales",
      """
        function(fileNamePdf0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "showPdfManuales/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("fileNamePdf", fileNamePdf0))})
        }
      """
    )
  
    // @LINE:9
    def viewImg: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.viewImg",
      """
        function(img0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "viewImg/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("img", img0))})
        }
      """
    )
  
  }

  // @LINE:27
  class ReverseMnuMantencion(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:33
    def mantWebListarReports1Get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebListarReports1Get",
      """
        function(de0,a1,id_equipo2) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebListarReports1Get/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("de", de0)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("a", a1)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id_equipo", id_equipo2))})
        }
      """
    )
  
    // @LINE:152
    def mantTblOperadorMecanicoVig: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblOperadorMecanicoVig",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblOperadorMecanicoVig/"})
        }
      """
    )
  
    // @LINE:142
    def mantTblEstadoOperacionalNewSave: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoOperacionalNewSave",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoOperacionalNewSave/"})
        }
      """
    )
  
    // @LINE:133
    def mantTblEstadoEnTallerMant: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoEnTallerMant",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoEnTallerMant/"})
        }
      """
    )
  
    // @LINE:110
    def mantTblActividadDel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblActividadDel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblActividadDel/"})
        }
      """
    )
  
    // @LINE:61
    def mantReportGrabaDocAnexo: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantReportGrabaDocAnexo",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantReportGrabaDocAnexo/"})
        }
      """
    )
  
    // @LINE:59
    def mantReportDetalle: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantReportDetalle",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantReportDetalle/"})
        }
      """
    )
  
    // @LINE:50
    def mantReportNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantReportNew",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantReportNew/"})
        }
      """
    )
  
    // @LINE:114
    def mantTblTipoActividadNewSave: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblTipoActividadNewSave",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblTipoActividadNewSave/"})
        }
      """
    )
  
    // @LINE:112
    def mantTblTipoActividadMant: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblTipoActividadMant",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblTipoActividadMant/"})
        }
      """
    )
  
    // @LINE:39
    def mantWebFirmaOperador: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebFirmaOperador",
      """
        function(id_venta0,de1,a2,id_equipo3) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebFirmaOperador/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id_venta", id_venta0)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("de", de1)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("a", a2)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id_equipo", id_equipo3))})
        }
      """
    )
  
    // @LINE:31
    def mantWebListarReports0: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebListarReports0",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebListarReports0/"})
        }
      """
    )
  
    // @LINE:140
    def mantTblEstadoOperacionalMant: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoOperacionalMant",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoOperacionalMant/"})
        }
      """
    )
  
    // @LINE:105
    def mantTblActividadMant: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblActividadMant",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblActividadMant/"})
        }
      """
    )
  
    // @LINE:149
    def mantTblOperadorMecanicoNewSave: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblOperadorMecanicoNewSave",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblOperadorMecanicoNewSave/"})
        }
      """
    )
  
    // @LINE:119
    def mantTblComponenteMant: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblComponenteMant",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblComponenteMant/"})
        }
      """
    )
  
    // @LINE:56
    def mantListarReports0: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantListarReports0",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantListarReports0/"})
        }
      """
    )
  
    // @LINE:109
    def modificaActividadPorCampoAjax: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.modificaActividadPorCampoAjax",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "modificaActividadPorCampoAjax/"})
        }
      """
    )
  
    // @LINE:130
    def modificaEstadoEnObraPorCampoAjax: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.modificaEstadoEnObraPorCampoAjax",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "modificaEstadoEnObraPorCampoAjax/"})
        }
      """
    )
  
    // @LINE:38
    def mantWebReportGrabaDocAnexo: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebReportGrabaDocAnexo",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebReportGrabaDocAnexo/"})
        }
      """
    )
  
    // @LINE:42
    def mantWebGrabarFirmaAutorizador: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebGrabarFirmaAutorizador",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebGrabarFirmaAutorizador/"})
        }
      """
    )
  
    // @LINE:148
    def mantTblOperadorMecanicoNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblOperadorMecanicoNew",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblOperadorMecanicoNew/"})
        }
      """
    )
  
    // @LINE:71
    def mantHistorialReports0: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantHistorialReports0",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantHistorialReports0/"})
        }
      """
    )
  
    // @LINE:106
    def mantTblActividadNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblActividadNew",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblActividadNew/"})
        }
      """
    )
  
    // @LINE:121
    def mantTblComponenteNewSave: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblComponenteNewSave",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblComponenteNewSave/"})
        }
      """
    )
  
    // @LINE:73
    def mantHistorialReports1Excel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantHistorialReports1Excel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantHistorialReports1Excel/"})
        }
      """
    )
  
    // @LINE:136
    def mantTblEstadoEnTallerUpdate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoEnTallerUpdate",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoEnTallerUpdate/"})
        }
      """
    )
  
    // @LINE:86
    def mantCantOperacional1Excel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantCantOperacional1Excel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantCantOperacional1Excel/"})
        }
      """
    )
  
    // @LINE:62
    def mantFirmaOperador: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantFirmaOperador",
      """
        function(id_venta0,desde1,hasta2) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantFirmaOperador/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id_venta", id_venta0)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("desde", desde1)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("hasta", hasta2))})
        }
      """
    )
  
    // @LINE:84
    def mantCantOperacional0: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantCantOperacional0",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantCantOperacional0/"})
        }
      """
    )
  
    // @LINE:123
    def modificaComponentePorCampoAjax: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.modificaComponentePorCampoAjax",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "modificaComponentePorCampoAjax/"})
        }
      """
    )
  
    // @LINE:60
    def mantListarReports1Excel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantListarReports1Excel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantListarReports1Excel/"})
        }
      """
    )
  
    // @LINE:27
    def mantWebInicio1: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebInicio1",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebInicio1/"})
        }
      """
    )
  
    // @LINE:29
    def mantWebInicio1get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebInicio1get",
      """
        function(id_operMec0,id_equipo1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebInicio1get/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id_operMec", id_operMec0)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id_equipo", id_equipo1))})
        }
      """
    )
  
    // @LINE:137
    def modificaEstadoEnTallerPorCampoAjax: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.modificaEstadoEnTallerPorCampoAjax",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "modificaEstadoEnTallerPorCampoAjax/"})
        }
      """
    )
  
    // @LINE:138
    def mantTblEstadoEnTallerDel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoEnTallerDel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoEnTallerDel/"})
        }
      """
    )
  
    // @LINE:65
    def mantGrabarFirmaAutorizador: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantGrabarFirmaAutorizador",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantGrabarFirmaAutorizador/"})
        }
      """
    )
  
    // @LINE:72
    def mantHistorialReports1: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantHistorialReports1",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantHistorialReports1/"})
        }
      """
    )
  
    // @LINE:41
    def mantWebFirmaAutorizador: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebFirmaAutorizador",
      """
        function(id_venta0,de1,a2,id_equipo3) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebFirmaAutorizador/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id_venta", id_venta0)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("de", de1)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("a", a2)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id_equipo", id_equipo3))})
        }
      """
    )
  
    // @LINE:101
    def mantTblItemUpdate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblItemUpdate",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblItemUpdate/"})
        }
      """
    )
  
    // @LINE:85
    def mantCantOperacional1: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantCantOperacional1",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantCantOperacional1/"})
        }
      """
    )
  
    // @LINE:102
    def modificaItemPorCampoAjax: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.modificaItemPorCampoAjax",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "modificaItemPorCampoAjax/"})
        }
      """
    )
  
    // @LINE:32
    def mantWebListarReports1: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebListarReports1",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebListarReports1/"})
        }
      """
    )
  
    // @LINE:147
    def mantTblOperadorMecanicoMant: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblOperadorMecanicoMant",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblOperadorMecanicoMant/"})
        }
      """
    )
  
    // @LINE:113
    def mantTblTipoActividadNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblTipoActividadNew",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblTipoActividadNew/"})
        }
      """
    )
  
    // @LINE:122
    def mantTblComponenteUpdate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblComponenteUpdate",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblComponenteUpdate/"})
        }
      """
    )
  
    // @LINE:127
    def mantTblEstadoEnObraNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoEnObraNew",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoEnObraNew/"})
        }
      """
    )
  
    // @LINE:88
    def mantCantMantencion0: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantCantMantencion0",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantCantMantencion0/"})
        }
      """
    )
  
    // @LINE:134
    def mantTblEstadoEnTallerNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoEnTallerNew",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoEnTallerNew/"})
        }
      """
    )
  
    // @LINE:100
    def mantTblItemNewSave: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblItemNewSave",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblItemNewSave/"})
        }
      """
    )
  
    // @LINE:108
    def mantTblActividadUpdate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblActividadUpdate",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblActividadUpdate/"})
        }
      """
    )
  
    // @LINE:78
    def mantControlMantenciones: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantControlMantenciones",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantControlMantenciones/"})
        }
      """
    )
  
    // @LINE:153
    def mantTblOperadorMecanicoVerifica: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblOperadorMecanicoVerifica",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblOperadorMecanicoVerifica/"})
        }
      """
    )
  
    // @LINE:116
    def modificaTipoActividadPorCampoAjax: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.modificaTipoActividadPorCampoAjax",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "modificaTipoActividadPorCampoAjax/"})
        }
      """
    )
  
    // @LINE:144
    def modificaEstadoOperacionalPorCampoAjax: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.modificaEstadoOperacionalPorCampoAjax",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "modificaEstadoOperacionalPorCampoAjax/"})
        }
      """
    )
  
    // @LINE:36
    def mantWebReportDetalle: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebReportDetalle",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebReportDetalle/"})
        }
      """
    )
  
    // @LINE:150
    def mantTblOperadorMecanicoUpdate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblOperadorMecanicoUpdate",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblOperadorMecanicoUpdate/"})
        }
      """
    )
  
    // @LINE:131
    def mantTblEstadoEnObraDel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoEnObraDel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoEnObraDel/"})
        }
      """
    )
  
    // @LINE:103
    def mantTblItemDel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblItemDel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblItemDel/"})
        }
      """
    )
  
    // @LINE:40
    def mantWebGrabarFirmaOperador: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebGrabarFirmaOperador",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebGrabarFirmaOperador/"})
        }
      """
    )
  
    // @LINE:35
    def mantWebListarReports1Excel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebListarReports1Excel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebListarReports1Excel/"})
        }
      """
    )
  
    // @LINE:64
    def mantFirmaAutorizador: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantFirmaAutorizador",
      """
        function(id_venta0,desde1,hasta2) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantFirmaAutorizador/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id_venta", id_venta0)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("desde", desde1)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("hasta", hasta2))})
        }
      """
    )
  
    // @LINE:115
    def mantTblTipoActividadUpdate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblTipoActividadUpdate",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblTipoActividadUpdate/"})
        }
      """
    )
  
    // @LINE:89
    def mantCantMantencion1: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantCantMantencion1",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantCantMantencion1/"})
        }
      """
    )
  
    // @LINE:90
    def mantCantMantencion1Excel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantCantMantencion1Excel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantCantMantencion1Excel/"})
        }
      """
    )
  
    // @LINE:79
    def mantControlMantencionesExcel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantControlMantencionesExcel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantControlMantencionesExcel/"})
        }
      """
    )
  
    // @LINE:107
    def mantTblActividadNewSave: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblActividadNewSave",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblActividadNewSave/"})
        }
      """
    )
  
    // @LINE:145
    def mantTblEstadoOperacionalDel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoOperacionalDel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoOperacionalDel/"})
        }
      """
    )
  
    // @LINE:120
    def mantTblComponenteNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblComponenteNew",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblComponenteNew/"})
        }
      """
    )
  
    // @LINE:117
    def mantTblTipoActividadDel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblTipoActividadDel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblTipoActividadDel/"})
        }
      """
    )
  
    // @LINE:124
    def mantTblComponenteDel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblComponenteDel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblComponenteDel/"})
        }
      """
    )
  
    // @LINE:128
    def mantTblEstadoEnObraNewSave: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoEnObraNewSave",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoEnObraNewSave/"})
        }
      """
    )
  
    // @LINE:143
    def mantTblEstadoOperacionalUpdate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoOperacionalUpdate",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoOperacionalUpdate/"})
        }
      """
    )
  
    // @LINE:141
    def mantTblEstadoOperacionalNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoOperacionalNew",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoOperacionalNew/"})
        }
      """
    )
  
    // @LINE:99
    def mantTblItemNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblItemNew",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblItemNew/"})
        }
      """
    )
  
    // @LINE:28
    def mantWebReportNewSave: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantWebReportNewSave",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantWebReportNewSave/"})
        }
      """
    )
  
    // @LINE:129
    def mantTblEstadoEnObraUpdate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoEnObraUpdate",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoEnObraUpdate/"})
        }
      """
    )
  
    // @LINE:135
    def mantTblEstadoEnTallerNewSave: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoEnTallerNewSave",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoEnTallerNewSave/"})
        }
      """
    )
  
    // @LINE:66
    def mantReportElimina: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantReportElimina",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantReportElimina/"})
        }
      """
    )
  
    // @LINE:151
    def modificaOperadorMecanicoPorCampoAjax: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.modificaOperadorMecanicoPorCampoAjax",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "modificaOperadorMecanicoPorCampoAjax/"})
        }
      """
    )
  
    // @LINE:57
    def mantListarReports1: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantListarReports1",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantListarReports1/"})
        }
      """
    )
  
    // @LINE:51
    def mantReportNewSave: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantReportNewSave",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantReportNewSave/"})
        }
      """
    )
  
    // @LINE:126
    def mantTblEstadoEnObraMant: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblEstadoEnObraMant",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblEstadoEnObraMant/"})
        }
      """
    )
  
    // @LINE:63
    def mantGrabarFirmaOperador: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantGrabarFirmaOperador",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "mantGrabarFirmaOperador/"})
        }
      """
    )
  
    // @LINE:58
    def mantListarReports1Get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantListarReports1Get",
      """
        function(d0,a1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantListarReports1Get/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("d", d0)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("a", a1))})
        }
      """
    )
  
    // @LINE:98
    def mantTblItemMant: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuMantencion.mantTblItemMant",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "mantTblItemMant/"})
        }
      """
    )
  
  }

  // @LINE:160
  class ReverseMnuReportes(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:173
    def estadosFacturaProyectoExcel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.estadosFacturaProyectoExcel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "estadosFacturaProyectoExcel/"})
        }
      """
    )
  
    // @LINE:169
    def estadosFacturaPeriodo: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.estadosFacturaPeriodo",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "estadosFacturaPeriodo/"})
        }
      """
    )
  
    // @LINE:177
    def enviarAlistAjustes: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.enviarAlistAjustes",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "enviarAlistAjustes/"})
        }
      """
    )
  
    // @LINE:167
    def proformaEstadoElimina: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.proformaEstadoElimina",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "proformaEstadoElimina/"})
        }
      """
    )
  
    // @LINE:174
    def estadosFacturaProyectoDetalle: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.estadosFacturaProyectoDetalle",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "estadosFacturaProyectoDetalle/"})
        }
      """
    )
  
    // @LINE:170
    def estadosFacturaProyecto: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.estadosFacturaProyecto",
      """
        function(archivoPDF0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "estadosFacturaProyecto/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("archivoPDF", archivoPDF0))})
        }
      """
    )
  
    // @LINE:179
    def proformaEstadoListaPdf: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.proformaEstadoListaPdf",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "proformaEstadoListaPdf/"})
        }
      """
    )
  
    // @LINE:160
    def estadosMantCobraArriendo0: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.estadosMantCobraArriendo0",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "estadosMantCobraArriendo0/"})
        }
      """
    )
  
    // @LINE:178
    def envioMasivoAlistAjustes: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.envioMasivoAlistAjustes",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "envioMasivoAlistAjustes/"})
        }
      """
    )
  
    // @LINE:180
    def proformaEstadoListaExcel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.proformaEstadoListaExcel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "proformaEstadoListaExcel/"})
        }
      """
    )
  
    // @LINE:165
    def proformaEstadoLista: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.proformaEstadoLista",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "proformaEstadoLista/"})
        }
      """
    )
  
    // @LINE:164
    def proformaEstadoListaPeriodo: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.proformaEstadoListaPeriodo",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "proformaEstadoListaPeriodo/"})
        }
      """
    )
  
    // @LINE:175
    def estadosFacturaProyectoDetExcel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.estadosFacturaProyectoDetExcel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "estadosFacturaProyectoDetExcel/"})
        }
      """
    )
  
    // @LINE:166
    def proformaEstadoListaGet: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.proformaEstadoListaGet",
      """
        function(d0,h1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "proformaEstadoListaGet/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("d", d0)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("h", h1))})
        }
      """
    )
  
    // @LINE:161
    def estadosMantCobraArriendo1: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.estadosMantCobraArriendo1",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "estadosMantCobraArriendo1/"})
        }
      """
    )
  
    // @LINE:162
    def modCobraArriendoAjax: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.modCobraArriendoAjax",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "modCobraArriendoAjax/"})
        }
      """
    )
  
    // @LINE:171
    def estadosFacturaProyectoGet: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.MnuReportes.estadosFacturaProyectoGet",
      """
        function(d0,a1,u2,e3,f4,p5) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "estadosFacturaProyectoGet/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("d", d0)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("a", a1)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Double]].javascriptUnbind + """)("u", u2)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Double]].javascriptUnbind + """)("e", e3)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Double]].javascriptUnbind + """)("f", f4)) + "," + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("p", p5))})
        }
      """
    )
  
  }


}
