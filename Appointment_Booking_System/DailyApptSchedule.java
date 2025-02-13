   
import java.util.HashMap;
import java.util.Map;

/**
 * A class to represent the appointment schedule for a given day.
 * Each day is assumed to have hour-long timeslots starting at 8am and
 * with the final timeslot starting at 4pm
 **/
public class DailyApptSchedule {

  private Appointment[] apptsForTheDay;

  public static final int NUM_SLOTS = 9;
  public static final String[] TIMES = {"8am", "9am", "10am", "11am",
                                        "12noon", "1pm", "2pm", "3pm", "4pm"};

  // No-arg constructor creates an empty schedule for a day
  public DailyApptSchedule() {
    // TO-DO
    apptsForTheDay = new Appointment[NUM_SLOTS];
    for (int i = 0; i < NUM_SLOTS; i++) {
      apptsForTheDay[i] = null;
    }
  }

  // Accessor method for the appointments for the day
  public Appointment[] getAppointmentsOfTheDay() {
    return apptsForTheDay;
  }

  /**
   * A method to display the list of appointments for the day
   * In the format:
   *    time: appointment_info
   *    time: appointment_info ... etc
   * Any empty slots (null Appointments in the array) should be listed as Free
   */
  public void displayAppointments() {

    // TO-DO
    // Display each appointment time and the corresponding appointment info
    for(int i=0; i<NUM_SLOTS; i++){
      // If the appointment slot is available, show it as FREE
      if(apptsForTheDay[i]==null){
        System.out.println(TIMES[i] + ": FREE");
      }
      // If appointment slot is booked, show its corresponding info
      else{
        System.out.println(TIMES[i] + ": " + apptsForTheDay[i].toString());
      }
    }
  }

  /**
   * A method to add the given appointment to the schedule in the specified timeslot
   * @param whichSlot represents the index of the timeslot (Eg. 0 -> 8am, 1 -> 9am, ...)
   * @param appt represents the appointment object
   * @return true if it was successful and false if not successful
   */
  public boolean addAppointment(int whichSlot, Appointment appt) {
    // TO-DO
    // Add the provided appointment to the appointments of the day
      // Check if this slot is available
      if(apptsForTheDay[whichSlot]==null){
        // Book this slot by adding the provided appointment info
        apptsForTheDay[whichSlot] = appt;
        return true;
      }
    return false;
  }

  /**
   * A method to add an appointment for the given person, venue and purpose to the schedule in the specified timeslot.
   * @param whichSlot represents the index of the timeslot (Eg. 0 -> 8am, 1 -> 9am, ...)
   * @param n represents the name of the student
   * @param v represents the location of the appointment
   * @param p represents the reason for the meeting
   * @return true if it was successful and false if not successful (i.e. if the given slot is invalid or taken)
   */
  public boolean addAppointment(int whichSlot, String n, String v, String p) {
    // TO-DO
    // Check the provided slot is within the eligible time slots
    if(whichSlot>=0 && whichSlot<=8) {
      // Check that the slot is available
      if(apptsForTheDay[whichSlot]==null) {
        // Ensure all appointment info is provided
        if (!n.isEmpty() && !v.isEmpty() && !p.isEmpty()) {
          // Create a new appointment
          Appointment newAppt = new Appointment(n, v, p);
          // Add the new appointment to the appointment schedule of the day
          return addAppointment(whichSlot, newAppt);
        }
      }
      else{
        System.out.println("\nThis chosen time slot is already taken! \n");
      }
    }
    return false;
  }

  /**
   * A method to add the given appointment to the schedule in any free timeslot.
   * @param appt represents the appointment object
   * @return the index of the chosen timeslot or -1 if no free timeslot exists.
   */
  public int addAppointment(Appointment appt) {
    // TO-DO
    for(int i=0; i<NUM_SLOTS; i++){
      // Add the given appointment to this slot if the slot is available
      if(apptsForTheDay[i]==null){
        apptsForTheDay[i] = appt;
        return i;
      }
      else{
        System.out.println("\nThis chosen time slot is already taken! \n");
      }
    }
    return -1;
  }

  /**
   * A method to add an appointment for the given person, venue and purpose
   * @param n represents the name of the student
   * @param v represents the location of the appointment
   * @param p represents the reason for the meeting
   * @return the index of the chosen timeslot or -1 if no free timeslot exists.
   */
  public int addAppointment(String n, String v, String p) {
    // TO-DO
    for(int i=0; i<NUM_SLOTS; i++){
      // Add the given appointment to this slot if the slot is available
      boolean verify = addAppointment(i, n, v, p);
      // Return the index of the chosen timeslot if the appointment was successfully scheduled
      if(verify){
        return i;
      }
    }
    return -1;
  }

  /**
   * Remove the appointment in the given slot
   * @param slot the index of the timeslot
   * @return true if operation was successful and false if not
   */
  public boolean removeAppointment(int slot){
    // TO-DO
    // Check that this slot is not already free
    if(apptsForTheDay[slot]!=null){
      // Remove the appointment in the given slot
      apptsForTheDay[slot] = null;
      return true;
    }
    else{
      System.out.println("\nThe appointment mentioned is not found! Double check the details entered and try again!\n");
    }
    return false;
  }

  /**
   * Remove the appointment corresponding to the given student name
   * @param n represents the name of the student
   * @return true if operation was successful and false if not
   */
  public boolean removeAppointment(String n){
      // TO-DO
    // Find the appointment with the provided student name
    for(int i=0; i<NUM_SLOTS; i++){
      // Check if this appointment has the provided student name
      if(apptsForTheDay[i].getStudentName().equals(n)){
        // Remove this appointment from the scheduled appointments of the day
        return removeAppointment(i);
      }
    }
    return false;
  }

  /**
   * Reschedule the appointment given the time slots
   * @param oldSlot represents the old slot of the appointment to be rescheduled
   * @param newSlot represents the new time slot the appointment is going to be rescheduled to
   * @return the update time slot of the appointment
   */
  public int rescheduleAppointment(int oldSlot, int newSlot){
    // TO-DO
    // Appointment at the old slot
    Appointment oldAppt = apptsForTheDay[oldSlot];
    // Remove this appointment from the scheduled appointments of the day
    boolean removed = removeAppointment(oldSlot);

    // Schedule the student's appointment at the new time slot provided
    if (removed) {
      if(newSlot>=0 && newSlot <=8) {
        boolean added = addAppointment(newSlot, oldAppt);
        if(added){
          return newSlot;
        }
        else{
          return -1;
        }
      }
      // In case the new slot time is not provided, the student's appointment is added at any other available time
      else if(newSlot==-1){
          return addAppointment(oldAppt);
      }
    }
    return -1;
  }

  /**
   * Reschedule the appointment corresponding to the given student name
   * @param n represents the name of the student
   * @param oldSlot represents the old slot of the appointment to be rescheduled
   * @param newSlot represents the new time slot the appointment is going to be rescheduled to
   * @return the update time slot of the appointment
   */
  public int rescheduleAppointment(String n, int oldSlot, int newSlot){
    // TO-DO
    // Verify the provided old time slot if valid
    if(oldSlot>=0 && oldSlot<=8) {
      // Check if the appointment at the old time slot belongs to the provided student name
      if (apptsForTheDay[oldSlot]!=null && apptsForTheDay[oldSlot].getStudentName().equals(n)) {
        return rescheduleAppointment(oldSlot, newSlot);
      }
    }
    // If the old time slot is not valid, or it doesn't belong to the provided student, find the correct time slot that the mentioned student scheduled their appointment
    else{
      for(int i=0; i<NUM_SLOTS; i++){
        if(apptsForTheDay[i]!=null && apptsForTheDay[i].getStudentName().equals(n)){
          return rescheduleAppointment(i, newSlot);
        }
      }
    }
    return -1;
  }
}





