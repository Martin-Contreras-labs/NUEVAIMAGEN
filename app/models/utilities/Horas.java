package models.utilities;



import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;




public class Horas{

	public static Double difHoras(String inicio, String termino) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		if(inicio.trim().equals("") || termino.trim().equals("")) {
			return ((double)0);
		}
			
        LocalTime dateTimeInicio = LocalTime.parse(inicio, formatter);
        LocalTime dateTimeFin = LocalTime.parse(termino, formatter);
        //Long horas = Duration.between(dateTimeInicio, dateTimeFin).toHours();
        //Long minutos = Duration.between(dateTimeInicio, dateTimeFin).toMinutes() % 60;
        Long minutos = Duration.between(dateTimeInicio, dateTimeFin).toMinutes();
        Double horas = (double)minutos / 60;
        return (horas);

	}
	
	
	

	

}
