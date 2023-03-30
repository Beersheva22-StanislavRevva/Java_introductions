package telran.view.test;

import java.time.LocalDate;

import telran.view.*;
import telran.view.Item.*;

public class DatesOperationsMenu {
public static Item getDatesOperationsMenu() {
	return new Menu("Dates Operations",
			Item.of("Days Afer", DatesOperationsMenu::daysAfter),
			Item.of("Days Before", DatesOperationsMenu::daysBefore),
			Item.exit());
}


static void daysOperations(boolean isBefore, InputOutput io) {
	LocalDate date = io.readDateISO("Enter date", "Wrong date");
	int days = io.readInt("Enter number of days", "Wrong number of dates", 1, Integer.MAX_VALUE);
	if (isBefore) {
		days = -days;
	}
	io.writeLine(date.plusDays(days));
}

static void daysAfter(InputOutput io) {
	daysOperations(false, io);
	
}

static void daysBefore(InputOutput io) {
	daysOperations(true, io);
	
}
}
