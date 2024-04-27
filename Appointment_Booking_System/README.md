
# Appointment-Booking-System

# Overview

This Java application allows students to book tutoring or office-hour appointments with the course Faculty Instructor (FI) or faculty. It also enables the FI or faculty member to list appointments, while students can cancel or reschedule appointments.

## Classes

-**Appointment**: Holds information about the appointment, including the student's name, venue, and purpose of the meeting. Contains various accessor and mutator methods.

-**DailyApptSchedule** : Manages a daily schedule of appointments, represented by an array. Provides methods to add, display, remove, and reschedule appointments.

-**WeeklyApptSchedule**: Maintains a weekly schedule of appointments, consisting of five DailyApptSchedule objects for each day of the workweek. Offers functionality to reschedule appointments for a specific day.

-**Test**: Contains test methods to verify the functionality of the 'Appointment' class. It includes tests for getting and setting the studet name, venue and appointment purpose. 

## Tasks
1. Implement and test various overloaded addAppointment methods in the DailyApptSchedule class.
2. Define methods in the DailyApptSchedule class to remove appointments by index or student name. Fully test these methods.
3. Add a method to the DailyApptSchedule class to reschedule appointments. Provide the method header and perform comprehensive testing.
4. Complete the implementation of the WeeklyApptSchedule class to manage a weekly schedule of appointments.
5. Add a method to the WeeklyApptSchedule class to allow rescheduling of appointments for a specific day. Provide the method header and thoroughly test the method.
6. Write an application (main method) that interacts with the user, enabling booking, cancellation, rescheduling, and display of appointments.

## Usage

1. Compile the Java files using a Java compiler.
2. Run the main method in the application class to start the program.
3. Follow the prompts to interact with the booking appointments system.
4. Test all functionalities to ensure proper operation.

## Author
Delice Ishimwe
