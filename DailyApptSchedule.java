                                                                  
import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List; 

public class DailyApptSchedule {
    private LocalDate date;
    private List<Appointment> appointments;

    public DailyApptSchedule(LocalDate date) {
        this.date = date;
        this.appointments = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void addAppointment(Appointment appt) {
        if (appt.getDateTime().toLocalDate().equals(date)) {
            appointments.add(appt);
        } else {
            System.out.println("Appointment date does not match daily schedule.");
        }
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Appointments for ").append(date).append(":\n");
        for (Appointment appt : appointments) {
            sb.append(appt.toString()).append("\n");
        }
        return sb.toString();
    }
}

