 
/**
 * A class that interacts with the user, allowing them to book an appointment for a particular day/time,
 * cancel an appointment, reschedule an appointment, or display the list of appointments.
 */
import java.util.Objects;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        // Create a new weekly schedule
        WeeklyApptSchedule weeklySchedule = new WeeklyApptSchedule();

        String[] TIMES = {"8am", "9am", "10am", "11am",
                "12noon", "1pm", "2pm", "3pm", "4pm"};

        // Read user input
        Scanner scanner = new Scanner(System.in);
        // Boolean to control the application running
        boolean running = true;

        do {
            // User's desired action
            System.out.print("\nWhat do you want to do? \n(1) Book an appointment \n(2) Cancel an appointment \n(3) Reschedule an appointment \n(4) Display the weekly list of appointments you have \n(5) Quit \nEnter your choice: ");
            String userChoice = scanner.nextLine();

            try {
                int action = Integer.parseInt(userChoice);
                if (action == 1 || action == 2 || action == 3) {
                    // User's input
                    System.out.print("Please enter your name: ");
                    String studentName = scanner.nextLine();

                    // Appointment day
                    WeeklyApptSchedule.Day day = null;
                    boolean isValidDay = false;  // Flag to ensure a valid day is chosen by the user

                    do {
                        System.out.print("Please enter the day of the appointment (MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY): ");
                        String appt_day = scanner.nextLine();

                        try {
                            day = WeeklyApptSchedule.Day.valueOf(appt_day.toUpperCase());
                            // Check that the provided day is between Monday and Friday
                            if (day.ordinal() >= WeeklyApptSchedule.Day.MONDAY.ordinal() &&
                                    day.ordinal() <= WeeklyApptSchedule.Day.FRIDAY.ordinal()) {
                                isValidDay = true; // Update isValidDay
                            } else {
                                System.out.println("Invalid input.");
                            }
                        }
                        catch(Exception e){
                            System.out.println("Invalid input.");
                        }
                    }
                    while(!isValidDay);

                    // Desired appointment time slot
                    int slot = -1;
                    boolean isValidSlot = false;  // Flag to ensure a valid time slot is provided by the user

                    do {
                        System.out.print("Please enter the appointment's time slot. Choose a number in the range of 1 to 9 representing 8am-4pm: ");
                        String timeSlot = scanner.nextLine();

                        try {
                            slot = Integer.parseInt(timeSlot);
                            // Check that the provided time slot is between 1 and 9
                            if (slot>=1 && slot<=9) {
                                isValidSlot = true;
                            } else {
                                System.out.println("Invalid input.");
                            }
                        }
                        catch(Exception e){
                            System.out.println("Invalid input.");
                        }
                    }
                    while(!isValidSlot);

                    // If the user wants to book an appointment
                    if (action == 1) {
                        // Venue of the appointment
                        System.out.print("Please enter the venue of the meeting: ");
                        String venue = scanner.nextLine();

                        // Purpose of the appointment
                        System.out.print("Please enter the purpose of this appointment: ");
                        String purpose = scanner.nextLine();

                        // Book the appointment for this user
                        Appointment appt = new Appointment(studentName, venue, purpose);
                        boolean added = weeklySchedule.addAppointment(appt, day, slot - 1);

                        if(added){
                            System.out.println("\nThe appointment was successfully scheduled on " + day + " at "+TIMES[slot-1]+"!\n");
                        }
                        else{
                            System.out.println("\nThis slot has already been taken. Try again, choose another slot!\n");
                        }
                    }
                    // If the user wants to cancel an appointment
                    else if (action == 2) {
                        // Cancel the appointment at the provided time slot
                        weeklySchedule.cancelAppointment(studentName, day, slot - 1);

                    // If the user wants to reschedule an appointment
                    } else {
                        // Appointment day
                        WeeklyApptSchedule.Day newDay = null;
                        boolean isValidNewDay = false;  // Flag to ensure a valid day is chosen by the user
                        String newApptDay = "";

                        do {
                            System.out.print("Please enter the new day on which to reschedule this appointment to (MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY): ");
                            newApptDay = scanner.nextLine();

                            try {
                                newDay = WeeklyApptSchedule.Day.valueOf(newApptDay.toUpperCase());
                                // Check that the provided day is between Monday and Friday
                                if (newDay.ordinal() >= WeeklyApptSchedule.Day.MONDAY.ordinal() &&
                                        newDay.ordinal() <= WeeklyApptSchedule.Day.FRIDAY.ordinal()) {
                                    isValidNewDay = true; // Update isValidNewDay
                                } else {
                                    System.out.println("\nInvalid input.\n");
                                }
                            }
                            catch(Exception e){
                                System.out.println("\nInvalid input.\n");
                            }
                        }
                        while(!isValidNewDay);

                        // Desired appointment time slot
                        int newSlot = -1;
                        boolean isValidNewSlot = false;  // Flag to ensure a valid time slot is provided by the user

                        do {
                            System.out.print("Please enter the new time slot to reschedule this appointment to. Choose a number in the range of 1 to 9 representing 8am-4pm: ");
                            String timeSlot = scanner.nextLine();

                            try {
                                newSlot = Integer.parseInt(timeSlot);
                                // Check that the provided time slot is between 1 and 9
                                if (newSlot>=1 && newSlot<=9) {
                                    isValidNewSlot = true;
                                } else {
                                    System.out.println("\nInvalid input.\n");
                                }
                            }
                            catch(Exception e){
                                System.out.println("\nInvalid input.\n");
                            }
                        }
                        while(!isValidNewSlot);

                        // Reschedule the appointment
                        int finalSlotTime = weeklySchedule.rescheduleAppointment(studentName, day, newDay, slot - 1, newSlot - 1);
                        if(finalSlotTime!=-1){
                            System.out.println("\nThe appointment was successfully rescheduled to "+newApptDay+" at "+TIMES[finalSlotTime]+"\n");
                        }
                    }
                } else if (action == 4) {
                    // Display all appointments on the mentioned day
                    weeklySchedule.displayWeeklyAppointments();
                }
                // Close the application when the user chooses to quit
                else if(action == 5){
                    System.out.println("\nApplication closed\n");
                    running = false;
                }
                else {
                    System.out.println("\nInvalid input. Choose a valid option.\n");
                }
            }
            catch(Exception e){
                System.out.println("\nInvalid input.\n");
            }
        }
        while (running);
    }
}


