      
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WeeklyApptSchedule {
    private LocalDate startDate;
    private Map<DayOfWeek, DailyApptSchedule> weeklySchedule;

    public WeeklyApptSchedule(LocalDate startDate) {
        this.startDate = startDate;
        this.weeklySchedule = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            LocalDate dayOfWeekDate = startDate.with(dayOfWeek(day));
            weeklySchedule.put(day, new DailyApptSchedule(dayOfWeekDate));
        }
    }

    private static DayOfWeek dayOfWeek(DayOfWeek day) {
        return day;
    }

    public void addAppointment(Appointment appt) {
        DayOfWeek day = appt.getDateTime().getDayOfWeek();
        weeklySchedule.get(day).addAppointment(appt);
    }

    public void displaySchedule() {
        for (DailyApptSchedule schedule : weeklySchedule.values()) {
            System.out.println(schedule);
        }
    }
}

