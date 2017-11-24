import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ManipulationDate {

	public static void main(String[] args) {
		
		LocalDate aujourdhui= LocalDate.now();
		System.out.println(aujourdhui.getDayOfMonth()+ "/"+ aujourdhui.getMonthValue()+"/" + aujourdhui.getYear());
		System.out.println(aujourdhui.getDayOfMonth()+ " "+ aujourdhui.getMonth()+" " + aujourdhui.getYear());
		LocalDate dateRetour= aujourdhui.plusDays(2);
		System.out.println(dateRetour);
		System.out.println(LocalDate.parse("2017-12-30"));
		LocalDate uneAutreDate = LocalDate.parse("2018-01-02");
		
		// exemple de formattage
		 LocalDate date = LocalDate.of(2017, 03, 06);
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
		  String text = date.format(formatter);
		  
		  LocalDate parsedDate = LocalDate.parse(text, formatter);

		System.out.println("date avec format: " + text);
		
		LocalDate maDate = LocalDate.of(2017,12,27);
		System.out.println(maDate);
		
		// différence en jours entre deux dates 
		long diff =maDate.until(uneAutreDate,ChronoUnit.DAYS);
		System.out.println("nb jours:" + diff);
}
}