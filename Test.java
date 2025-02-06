  
import java.time.LocalDateTime;
import java.time.Month;

public class Test {
    public static void main(String[] args) {
        // Create appointments
        Appointment appt1 = new Appointment("Gym's appointment", LocalDateTime.of(2025, Month.JANUARY, 8, 10, 0));
        Appointment appt2 = new Appointment("Team meeting", LocalDateTime.of(2025, Month.JANUARY, 8, 14, 30));
        Appointment appt3 = new Appointment("Dentist appointment", LocalDateTime.of(2025, Month.JANUARY, 9, 9, 0));

        // Create weekly schedule starting on a Monday (January 6, 2025)
        WeeklyApptSchedule weeklySchedule = new WeeklyApptSchedule(LocalDateTime.of(2025, Month.JANUARY, 6, 0, 0).toLocalDate());

        // Add appointments to the schedule
        weeklySchedule.addAppointment(appt1);
        weeklySchedule.addAppointment(appt2);
        weeklySchedule.addAppointment(appt3);

        // Display weekly schedule
        weeklySchedule.displaySchedule();
    }
}

