package sample;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Person {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate birthday = LocalDate.now();

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

}
