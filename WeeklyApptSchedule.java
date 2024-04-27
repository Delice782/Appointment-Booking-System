
/**
 * Class that hold 5 daily appointment schedules: one for each day of the work week.
 */
public class WeeklyApptSchedule{
   
   private DailyApptSchedule[] apptsForTheWeek;
   
   public static final int NUM_DAYS = 5;
   public static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", 
     "Thursday", "Friday"};
   public enum Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY};
   
   // No-arg constructor creates an array of DailyApptSchedule 
   // for the week
   public WeeklyApptSchedule()
   {
      // TO-DO
      apptsForTheWeek = new DailyApptSchedule[NUM_DAYS];
      for (int i = 0; i < NUM_DAYS; i++) {
         apptsForTheWeek[i] = new DailyApptSchedule();
      }
   }
   
   // display weekly appointments
   // Display the set of appointments for the week by showing
   // the schedule for each day.
   public void displayWeeklyAppointments() {
      // TO-DO
      System.out.println("\nHere is your schedule for the week:");
      for(int day=0; day<NUM_DAYS; day++){
         System.out.println("\n"+DAYS[day]+": \n");
         apptsForTheWeek[day].displayAppointments();
      }
   }
   
   // Add an appointment for a given timeslot on a given day.
   // Note: You may find it helpful to invoke day.ordinal()
   // ordinal() is a method automatically defined for enumerations 
   // it gives an "index" of a particular value in the enumeration.
   public boolean addAppointment(Appointment appt, Day day, int timeSlot){
     // TO-DO
       return apptsForTheWeek[day.ordinal()].addAppointment(timeSlot, appt);
   }
   
   // Cancels (removes) the appointment in a given timeslot on
   // a given day
   public void cancelAppointment(Day day, int timeSlot) {
      // TO-DO
      boolean verify = apptsForTheWeek[day.ordinal()].removeAppointment(timeSlot);
      if(verify){
         System.out.println("\nThe appointment was successfully canceled!\n");
      }
   }

   // Cancels (removes) the appointment of a certain student in a given timeslot on
   // a given day
   public void cancelAppointment(String n, Day day, int timeSlot) {
      // TO-DO
      Appointment appt = apptsForTheWeek[day.ordinal()].getAppointmentsOfTheDay()[timeSlot];
      // Check that the student canceling the appointment and that of the student who scheduled the appointment are the same, such that no student cancels the appointment that is not theirs.
      if(appt != null) {
         if (appt.getStudentName().equals(n)) {
            cancelAppointment(day, timeSlot);
         } else {
            System.out.println("\nNo appointment with the exact details you provided was found. Double check the details you entered and try again.\n");
         }
      }
      else {
         System.out.println("\nNo appointment with the exact details you provided was found. Double check the details you entered and try again.\n");
      }
   }

   /**
    * Reschedule the appointment corresponding to the given student name
    * @param n represents the name of the student
    * @param day represents the day of the appointment to be rescheduled
    * @param oldSlot represents the old slot of the appointment to be rescheduled
    * @param newSlot represents the new time slot the appointment is going to be rescheduled to
    * @return true if operation was successful and false if not
    */
   public int rescheduleAppointment(String n, Day day, int oldSlot, int newSlot){
      // TO-DO
      // Ensure the day provided is a working week day
      if(day.ordinal()>=0 && day.ordinal()<=4) {
         // Reschedule the appointment at the mentioned day for the specified student
         return apptsForTheWeek[day.ordinal()].rescheduleAppointment(n, oldSlot, newSlot);
      }
      return -1;
   }

   /**
    * Reschedule the appointment given the old day and time slot and the new day and time slot
    * @param oldDay represents the current day of the appointment to be rescheduled
    * @param newDay represents the new day of the appointment to be rescheduled on
    * @param oldSlot represents the old slot of the appointment to be rescheduled
    * @param newSlot represents the new time slot the appointment is going to be rescheduled to
    * @return true if operation was successful and false if not
    */
   public int rescheduleAppointmentHelper(Day oldDay, Day newDay, int oldSlot, int newSlot){
      // TO-DO
      // Verify the provided old time slot if valid
      if(oldSlot>=0 && oldSlot<=8) {
         Appointment oldAppt = apptsForTheWeek[oldDay.ordinal()].getAppointmentsOfTheDay()[oldSlot];
         if(oldAppt!=null) {
            boolean removed = apptsForTheWeek[oldDay.ordinal()].removeAppointment(oldSlot);
            if(removed){
               // Verify the provided new time slot if valid
               if (newSlot >= 0 && newSlot <= 8) {
                  // Add the mentioned appointment in the provided new time slot
                  boolean added = apptsForTheWeek[newDay.ordinal()].addAppointment(newSlot, oldAppt);
                  if(added) {
                     return newSlot;
                  }
               } else {
                  // Add the mentioned appointment in any available time slot on that day
                  return apptsForTheWeek[newDay.ordinal()].addAppointment(oldAppt);
               }
            }
         }
      }
      return -1;
   }

   /**
    * Reschedule the appointment corresponding to the given student name
    * @param n represents the name of the student
    * @param oldDay represents the current day of the appointment to be rescheduled
    * @param newDay represents the new day of the appointment to be rescheduled on
    * @param oldSlot represents the old slot of the appointment to be rescheduled
    * @param newSlot represents the new time slot the appointment is going to be rescheduled to
    * @return true if operation was successful and false if not
    */
   public int rescheduleAppointment(String n, Day oldDay, Day newDay, int oldSlot, int newSlot){
      // TO-DO
      // Ensure the days provided are a working week days
      if(oldDay.ordinal() <= 4 && newDay.ordinal() <= 4){
         if(oldSlot>=0 && oldSlot<=8) {
            Appointment oldAppt = apptsForTheWeek[oldDay.ordinal()].getAppointmentsOfTheDay()[oldSlot];
            if(oldAppt!=null) {
               // Check to see that this appointment was scheduled by the said student
               if (oldAppt.getStudentName().equals(n)) {
                  // Check if the new appointment day and the old appointment day are the same
                  if (oldDay.ordinal() == newDay.ordinal()) {
                     // Reschedule the appointment
                     return apptsForTheWeek[oldDay.ordinal()].rescheduleAppointment(n, oldSlot, newSlot);
                  } else {
                     // Reschedule the appointment
                     return rescheduleAppointmentHelper(oldDay, newDay, oldSlot, newSlot);
                  }
               } else {
                  System.out.println("\nNo appointment with the exact details you provided was found. Double check the details you entered and try again.\n");
               }
            }
            else{
               System.out.println("\nNo appointment with the exact details you provided was found. Double check the details you entered and try again.\n");
            }
         }
      }
      return -1;
   }
}