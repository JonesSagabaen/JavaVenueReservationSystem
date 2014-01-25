/**COPYRIGHT(C). Jonathan Sagabaen. All Rights Reserved.
 * The Room class represents a room in the building.
 * @author Jonathan Sagabaen
 * @version 1.01
 */
import java.util.Calendar;

/**
 * Room class represents a room that can be reserved for events.
 */
public class Room
{
   /**
    * Constructs a monthly schedules for a room with a certain capacity.
    * @param aCapacity the maximum capacity for the room
    */
   public Room(int aCapacity)
   {
      capacity = aCapacity;
      monthlySchedule = new MonthlySchedule[12];
      for (int i = 0; i < 12; i++)
         monthlySchedule[i] = new MonthlySchedule();
   }
   
   /**
    * Adds a request to the schedule for the room.
    * @param request the request to be added to the monthly schedule
    * @return true if request can be added, else false if request cannot be added
    */
   public boolean add(Request request)
   {
      int monthNumber = request.getDate().get(Calendar.MONTH);
      return monthlySchedule[monthNumber].add(request);
   }
   
   /**
    * Cancels an existing request from the schedule for the room.
    * @param request the request to be canceled from the monthly schedule
    * @return true if request can be canceled, else false if request cannot be canceled
    */
   public boolean cancel(Request request)
   {
      int monthNumber = request.getDate().get(Calendar.MONTH);
      String name = request.getName();
      return monthlySchedule[monthNumber].cancel(name);
   }
   
   /**
    * Prints the monthly schedule of a particular month.
    * @param m the schedule of the month to be printed
    * @return the monthly schedule
    */
   public String printMonthlySchedule(int m)
   {
      return monthlySchedule[m].toString();
   }

   /**
    * The capacity that the room can hold.
    */
   private int capacity;
   /**
    * An array of monthly schedules reserved for the room.
    */
   private MonthlySchedule[] monthlySchedule;
}
