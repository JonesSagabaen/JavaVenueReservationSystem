/**COPYRIGHT(C). Jonathan Sagabaen. All Rights Reserved.
 * The Request class represents the time-schedule of a request. 
 * @author Jonathan Sagabaen
 * @version 1.01
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Request class manages a request with the following format:
 *      Name;EventName;Year;Month;Date;Time;Duration;SeatNumber;
 */
public class Request implements Comparable
{
   /**
    * Constructs a request with the format:
    *      Name;EventName;Year;Month;Date;Time;Duration;SeatNumber;
    * and splits up the information to be saved.
    * @param aRequest a request to be processed and saved
    */
   public Request(String aRequest)
   {
      String[] requestList = aRequest.split(";");
      
      name = requestList[0];
      event = requestList[1];
      date = new GregorianCalendar(Integer.parseInt(requestList[2]), convertMonth(requestList[3]), 
                                    Integer.parseInt(requestList[4]), Integer.parseInt(requestList[5]), 0);
      duration = Integer.parseInt(requestList[6]);
      seats = Integer.parseInt(requestList[7]);
      
   }
   
   /**
    * Outputs the request data onto the console with the following format:
    *      Name;EventName;Year;Month;Date;Time;Duration;SeatNumber;
    * @return a String output in the following format: Name;EventName;Year;Month;Date;Time;Duration;SeatNumber;
    */
   public String toString()
   {
      return "" + name + ";" + event + ";" + date.get(Calendar.YEAR) + ";" + convertMonth(date.get(Calendar.MONTH))
      + ";" + date.get(Calendar.DAY_OF_MONTH) + ";" + date.get(Calendar.HOUR_OF_DAY) + ";" + duration + ";" + seats + ";";
   }
   
   /**
    * Compares the date of one request with another.
    * @return returns 1 if request r is after the request being compared to, 
    *         -1 if request is before the request being compared to,
    *         0 if requests are at the same time
    */
   public int compareTo(Object r)
   {  
      return date.compareTo((Calendar) r);
   }
   
   /**
    * Gets the name of the person of the request.
    * @return name
    */
   public String getName()
   {
      return name;
   }

   /**
    * Gets the event name of the request.
    * @return event
    */
   public String getEvent()
   {
      return event;
   }

   /**
    * Gets the date of the request.
    * @return date
    */
   public Calendar getDate()
   {
      return date;
   }

   /**
    * Gets the duration of the event from the request.
    * @return duration
    */
   public int getDuration()
   {
      return duration;
   }

   /**
    * Gets the number of seats required from the request.
    * @return seat number
    */
   public int getSeats()
   {
      return seats;
   }
   
   /**
    * Gets the room number associated with the request.
    * @return room number
    */
   public int getRoomNum()
   {
      return roomNum;
   }

   /**
    * Sets the room number associated with the request.
    * @param roomNum the room number to be set for the request
    */
   public void setRoomNum(int roomNum)
   {
      this.roomNum = roomNum;
   }
   
   /**
    * Converts the month from a String to its numeral representation.
    * @param aMonth a month to convert into its numeral representation
    * @return the number representation of the month
    */
   private int convertMonth(String aMonth)
   {
      if (aMonth.equalsIgnoreCase("January"))
         return 0;
      else if (aMonth.equalsIgnoreCase("February"))
         return 1;
      else if (aMonth.equalsIgnoreCase("March"))
         return 2;
      else if (aMonth.equalsIgnoreCase("April"))
         return 3;
      else if (aMonth.equalsIgnoreCase("May"))
         return 4;
      else if (aMonth.equalsIgnoreCase("June"))
         return 5;
      else if (aMonth.equalsIgnoreCase("July"))
         return 6;
      else if (aMonth.equalsIgnoreCase("August"))
         return 7;
      else if (aMonth.equalsIgnoreCase("September"))
         return 8;
      else if (aMonth.equalsIgnoreCase("October"))
         return 9;
      else if (aMonth.equalsIgnoreCase("November"))
         return 10;
      else if (aMonth.equalsIgnoreCase("December"))
         return 11;
      else
         return -1;
   }
   
   /**
    * Converts the month from a number representation to a String.
    * @param aMonth a month to convert into its String representation
    * @return the String representation of the month
    */
   private String convertMonth(int aMonth)
   {
      if (aMonth == 0)
         return "January";
      else if (aMonth == 1)
         return "February";
      else if (aMonth == 2)
         return "March";
      else if (aMonth == 3)
         return "April";
      else if (aMonth == 4)
         return "May";
      else if (aMonth == 5)
         return "June";
      else if (aMonth == 6)
         return "July";
      else if (aMonth == 7)
         return "August";
      else if (aMonth == 8)
         return "September";
      else if (aMonth == 9)
         return "October";
      else if (aMonth == 10)
         return "November";
      else if (aMonth == 11)
         return "December";
      else
         return "Invalid Entry";
   }

   /**
    * Name of the person making the request.
    */
   private String name;
   /**
    * Name of the event.
    */
   private String event;
   /**
    * Date of the event.
    */
   private Calendar date;
   /**
    * Duration of the event which must be in multiples of 60 minutes
    */
   private int duration;
   /**
    * Number of seats.
    */
   private int seats;
   /**
    * Room number assigned for the event of the request.
    */
   private int roomNum;

   public static void main(String[] args)
   {
      Request r1 = new Request("Matt Smith;Event 1;2009;March;29;11;120;40;");
      System.out.println("Name: " + r1.getName());
      System.out.println("Event: " + r1.getEvent());
      System.out.println("Year: " + r1.getDate().get(Calendar.YEAR));
      System.out.println("Month: " + r1.convertMonth(r1.getDate().get(Calendar.MONTH)));
      System.out.println("Day: " + r1.getDate().get(Calendar.DAY_OF_MONTH));
      System.out.println("Hour: " + r1.getDate().get(Calendar.HOUR_OF_DAY));
      System.out.println("Duration: " + r1.getDuration());
      System.out.println("Seats: " + r1.getSeats());
      System.out.println("");

      System.out.println("Print current request: ");
      System.out.println(r1.toString());      
   }

}
