
let msgError = "Se presento un error de conexion o inactividad por mucho tiempo, por favor vuelva a ingresar.";


function fechaDDMMAA (date){
	if(date.trim() == ""){
		return(date);
	}
	var arrDate = date.split("-");
	
	
	if(arrDate[0].length == 2){
		return(date);
	}else{
		var year = arrDate[0];
		var month = arrDate[1];
		var day = arrDate[2];
		return(day+"-"+month+"-"+year);
	}
}

function fechaAAMMDD (date){
	var arrDate = date.split("-");
	if(arrDate[0].length = 4){
		return(date);
	}else{
		var year = arrDate[2];
		var month = arrDate[1];
		var day = arrDate[0];
		return(year+"-"+month+"-"+day);
	}
}

/************************************************************/
/*			VALIDA FECHA									*/
/************************************************************/

function isValidDate(date) {
	var arrDate = date.split("-");
	var year = arrDate[0];
	var month = arrDate[1];
	var day = arrDate[2];
    var dteDate;
    month=month-1;
    dteDate=new Date(year,month,day);
    if((day==dteDate.getDate()) && (month==dteDate.getMonth()) && (year==dteDate.getFullYear())){
    	return (true);
    }else{
    	return (false);
    }
}

/************************************************************/
/*			FORMATO FECHA									*/
/************************************************************/

function fechaAAMMDD(fecha) {
	let dia = fecha.getDate().toString().padStart(2, '0');
	let mes = (fecha.getMonth() + 1).toString().padStart(2, '0');
	let anio = fecha.getFullYear();
	return(anio+"-"+mes+"-"+dia);
}

/************************************************************/
/*			FORMATO-NUMEROS									*/
/************************************************************/

function formatStandar(valor,nroDecimal){
	if(nroDecimal==0) return(formatStandar0(valor));
	if(nroDecimal==1) return(formatStandar1(valor));
	if(nroDecimal==2) return(formatStandar2(valor));
	if(nroDecimal==4) return(formatStandar4(valor));
	if(nroDecimal==6) return(formatStandar6(valor));
	var formato = new DecimalFormat("#,##0.00");
	if(valor=="") valor=0;
	return(formato.format(valor));
}
function formatStandar0(valor){
	var formato = new DecimalFormat("#,##0");
	if(valor=="") valor=0;
	return(formato.format(valor));
}
function formatStandar1(valor){
	var formato = new DecimalFormat("#,##0.0");
	if(valor=="") valor=0;
	return(formato.format(valor));
}
function formatStandar2(valor){
	var formato = new DecimalFormat("#,##0.00");
	if(valor=="") valor=0;
	return(formato.format(valor));
}
function formatStandar4(valor){
	var formato = new DecimalFormat("#,##0.0000");
	if(valor=="") valor=0;
	return(formato.format(valor));
}
function formatStandar6(valor){
	var formato = new DecimalFormat("#,##0.000000");
	if(valor=="") valor=0;
	return(formato.format(valor));
}
function formatPorcentaje(valor){
	var formato = new DecimalFormat("#,##0.00");
	if(valor=="") valor=0;
	return(formato.format(valor)+" %");
}

function ingresoDouble (e,valor) {
    k = (document.getElementById) ? e.keyCode : e.which;
    if (k==8 || k==0) return true;
    if (k==46&&valor.indexOf('.')==-1) return true;
    if (k==13) return true;
    patron = /\d/;
    n = String.fromCharCode(k);
    return patron.test(n);
}

function ingresoDoubleConNegativos (e,valor) {
    k = (document.getElementById) ? e.keyCode : e.which;
    if (k==8 || k==0) return true;
    if (k==46&&valor.indexOf('.')==-1) return true;
    if (k==13) return true;
    if (k==45&&valor.indexOf('-')==-1) return true;
    patron = /\d/;
    n = String.fromCharCode(k);
    return patron.test(n);
}

function ingresoInt (e) {
    k = (document.getElementById) ? e.keyCode : e.which;
    if (k==8 || k==0) return true;
    if (k==13) return true;
    patron = /\d/;
    n = String.fromCharCode(k);
    return patron.test(n);
}

/*			FIN-FORMATO-NUMEROS									*/
/************************************************************/


function sinReservados (e) {
    k = (document.getElementById) ? e.keyCode : e.which;
	//34 = " doble comilla
	//32 = espacio
    if (k!=47 && k!=92 && k!=44 && k!=39 && k!=96) return true;
    patron = /\d/;
    n = String.fromCharCode(k);
    return patron.test(n);
}

function limitaFecha(fecha, menos, mas) {
	var fechaMas = new Date();
	var fechaMenos = new Date();
	var fecha = new Date(fecha);
	fechaMas.setDate(fechaMas.getDate() + mas);
	fechaMenos.setDate(fechaMenos.getDate() - menos);
	if(fecha == "Invalid Date"){
		alertify.alert("Debe ingresar una fecha valida", function () {
			return false;
		});
	}else if(fecha > fechaMas){
		alertify.alert("La fecha no puede ser superior a "+mas+" días de la fecha actual", function () {
			return false;
		});
	}else if(fecha < fechaMenos){
		alertify.alert("La fecha no puede ser anterior a "+menos+" días de la fecha actual", function () {
			return false;
		});
	}else{
		return true;
	}
};



/************************************************************/
/*			BUSCADOR DE CONTENIDOS EN TABLAS				*/
/************************************************************/

function doSearch2(tabla)
{
	var tableReg = document.getElementById(tabla);
	var searchText = document.getElementById('searchTerm'+tabla).value.toLowerCase();
	var cellsOfRow="";
	var found=false;
	var compareWith="";
	
	// Recorremos todas las filas con contenido de la tabla
	for (var i = 1; i < tableReg.rows.length; i++)
	{
		cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
		found = false;
		// Recorremos todas las celdas
		for (var j = 0; j < cellsOfRow.length && !found; j++)
		{
			compareWith = cellsOfRow[j].innerHTML.toLowerCase();
			// Buscamos el texto en el contenido de la celda
			if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
			{
				found = true;
			}
		}
		if(found)
		{
			tableReg.rows[i].style.display = '';
		} else {
			// si no ha encontrado ninguna coincidencia, esconde la
			// fila de la tabla
			tableReg.rows[i].style.display = 'none';
		}
	}
}

function doSearch2_conTot(tabla)
{
	var tableReg = document.getElementById(tabla);
	var searchText = document.getElementById('searchTerm'+tabla).value.toLowerCase();
	var cellsOfRow="";
	var found=false;
	var compareWith="";
	
	// Recorremos todas las filas con contenido de la tabla
	for (var i = 1; i < tableReg.rows.length-1; i++)
	{
		cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
		found = false;
		// Recorremos todas las celdas
		for (var j = 0; j < cellsOfRow.length && !found; j++)
		{
			compareWith = cellsOfRow[j].innerHTML.toLowerCase();
			// Buscamos el texto en el contenido de la celda
			if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
			{
				found = true;
			}
		}
		if(found)
		{
			tableReg.rows[i].style.display = '';
		} else {
			// si no ha encontrado ninguna coincidencia, esconde la
			// fila de la tabla
			tableReg.rows[i].style.display = 'none';
		}
	}
}

function doSearch3(tabla)
{
	var tableReg = document.getElementById(tabla);
	var searchText = document.getElementById('searchTerm'+tabla).value.toLowerCase();
	var cellsOfRow="";
	var found=false;
	var compareWith="";
	
	// Recorremos todas las filas con contenido de la tabla
	for (var i = 2; i < tableReg.rows.length; i++)
	{
		cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
		found = false;
		// Recorremos todas las celdas
		for (var j = 0; j < cellsOfRow.length && !found; j++)
		{
			compareWith = cellsOfRow[j].innerHTML.toLowerCase();
			// Buscamos el texto en el contenido de la celda
			if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
			{
				found = true;
			}
		}
		if(found)
		{
			tableReg.rows[i].style.display = '';
		} else {
			// si no ha encontrado ninguna coincidencia, esconde la
			// fila de la tabla
			tableReg.rows[i].style.display = 'none';
		}
	}
}

function doSearch3_conTot(tabla)
{
	var tableReg = document.getElementById(tabla);
	var searchText = document.getElementById('searchTerm'+tabla).value.toLowerCase();
	var cellsOfRow="";
	var found=false;
	var compareWith="";
	
	// Recorremos todas las filas con contenido de la tabla
	for (var i = 2; i < tableReg.rows.length-1; i++)
	{
		cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
		found = false;
		// Recorremos todas las celdas
		for (var j = 0; j < cellsOfRow.length && !found; j++)
		{
			compareWith = cellsOfRow[j].innerHTML.toLowerCase();
			// Buscamos el texto en el contenido de la celda
			if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
			{
				found = true;
			}
		}
		if(found)
		{
			tableReg.rows[i].style.display = '';
		} else {
			// si no ha encontrado ninguna coincidencia, esconde la
			// fila de la tabla
			tableReg.rows[i].style.display = 'none';
		}
	}
}

function doSearch4(tabla)
{
	var tableReg = document.getElementById(tabla);
	var searchText = document.getElementById('searchTerm'+tabla).value.toLowerCase();
	var cellsOfRow="";
	var found=false;
	var compareWith="";
	
	// Recorremos todas las filas con contenido de la tabla
	for (var i = 4; i < tableReg.rows.length; i++)
	{
		cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
		found = false;
		// Recorremos todas las celdas
		for (var j = 0; j < cellsOfRow.length && !found; j++)
		{
			compareWith = cellsOfRow[j].innerHTML.toLowerCase();
			// Buscamos el texto en el contenido de la celda
			if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
			{
				found = true;
			}
		}
		if(found)
		{
			tableReg.rows[i].style.display = '';
		} else {
			// si no ha encontrado ninguna coincidencia, esconde la
			// fila de la tabla
			tableReg.rows[i].style.display = 'none';
		}
	}
}

function doSearch5(tabla)
{
	var tableReg = document.getElementById(tabla);
	var searchText = document.getElementById('searchTerm'+tabla).value.toLowerCase();
	var cellsOfRow="";
	var found=false;
	var compareWith="";
	
	// Recorremos todas las filas con contenido de la tabla
	for (var i = 5; i < tableReg.rows.length; i++)
	{
		cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
		found = false;
		// Recorremos todas las celdas
		for (var j = 0; j < cellsOfRow.length && !found; j++)
		{
			compareWith = cellsOfRow[j].innerHTML.toLowerCase();
			// Buscamos el texto en el contenido de la celda
			if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
			{
				found = true;
			}
		}
		if(found)
		{
			tableReg.rows[i].style.display = '';
		} else {
			// si no ha encontrado ninguna coincidencia, esconde la
			// fila de la tabla
			tableReg.rows[i].style.display = 'none';
		}
	}
}



