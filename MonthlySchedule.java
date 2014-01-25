/**COPYRIGHT(C). Jonathan Sagabaen. All Rights Reserved.
 * MonthlySchedule class manages reservation schedules for a particular month.
 * @author Jonathan Sagabaen
 * @version 1.01
 */
import java.util.ArrayList;
import java.util.Calendar;

/**
 * MonthlySchedule class keeps track of requests for a certain month.
 */
public class MonthlySchedule
{
   /**
    * Constructs an ArrayList of schedules for a certain month. 
    */
   public MonthlySchedule()
   {
      monthlySchedule = new ArrayList<Request>();

   }
   
   /**
    * Outputs the array and its contents onto the console.
    * @return the monthlySchedule array and its contents
    */
   public String toString()
   {
      String list = "";
      for(int i = 0; i < monthlySchedule.size(); i++)
         list += monthlySchedule.get(i).toString() + "\n";
      return list;
   }

   /**
    * Checks if a request can be added to the monthly schedule.  If it can, the request will be added
    * and method will return true.  If not, the request will not be added and the method will return false.  
    * @param request the request to be added to the monthly schedule
    * @return true if request can be added, else false if request cannot be added
    */
   public boolean add(Request request)
   {
      int position = 0;
      for (int i = 0; i < monthlySchedule.size(); i++)
      {
         if (timeConflict(monthlySchedule.get(i), request))
               return false;
       
         if (monthlySchedule.get(i).getDate().compareTo(request.getDate()) == -1)
         {
            position++;
         } 
      }
      monthlySchedule.add(position, request);
      return true;
         
   }
   
   /**
    * Removes an existing request from the monthly schedule.
    * @param name the name of the request to be canceled
    * @return true if request can be canceled, else false if request cannot be canceled
    */
   public boolean cancel(String name)
   {
      for (Request e: monthlySchedule)
      {
         String[] temp = e.toString().split(";");
         if (name.equalsIgnoreCase(temp[0]))
         {
            monthlySchedule.remove(e);
            return true;
         }
      }  
         return false;
   }
   
   /**
    * Checks two requests to see if they have a time conflict with one another.
    * @param oldRequest the request that already exists in the monthly schedule
    * @param newRequest the new request to compare with the already existing request
    * @return true if a time conflict exists, else false if there is no time conflict
    */
   private boolean timeConflict(Request oldRequest, Request newRequest)
   {
      int orStartTime = oldRequest.getDate().get(Calendar.HOUR_OF_DAY);
      int orEndTime = oldRequest.getDate().get(Calendar.HOUR_OF_DAY) + oldRequest.getDuration() / 60;
      int nrStartTime = newRequest.getDate().get(Calendar.HOUR_OF_DAY);
      int nrEndTime = newRequest.getDate().get(Calendar.HOUR_OF_DAY) + newRequest.getDuration() / 60;
      
      if ((newRequest.getDate().get(Calendar.DAY_OF_MONTH) == oldRequest.getDate().get(Calendar.DAY_OF_MONTH))
            && ((nrStartTime < orEndTime) && (nrStartTime >= orStartTime)))
            return true;
      if ((newRequest.getDate().get(Calendar.DAY_OF_MONTH) == oldRequest.getDate().get(Calendar.DAY_OF_MONTH))
            && ((nrEndTime > orStartTime) && (nrEndTime <= orEndTime)))
            return true;
            
         return false;
   }

   /**
    * An ArrayList to keep track of schedules of a particular month.
    */
   private ArrayList<Request> monthlySchedule; 


   public static void main(String[] args)
   {
      MonthlySchedule m1 = new MonthlySchedule();
      System.out.println("Add event: John Doe;Event 1;2009;December;31;8;60;40;");
      m1.add(new Request("John Doe;Event 1;2020;December;31;8;60;40;"));
      
      System.out.println("Add event: Steve Blow;Event 2;2009;January;9;8;60;12;");
      m1.add(new Request("Steve Blow;Event 2;2020;January;9;8;60;12;"));
      System.out.println("Print current schedule: ");
      System.out.println(m1.toString());
      
      System.out.println("");
      System.out.println("Add event: Jerry Fro;Event 3;2009;March;18;20;60;30;");
      m1.add(new Request("Jerry Fro;Event 3;2020;March;18;20;60;30;"));
      System.out.println("Print current schedule: ");
      System.out.println(m1.toString());
      
      System.out.println("");
      System.out.println("Add event: Don Joe;Event 4;2009;December;31;10;180;150;");
      m1.add(new Request("Don Joe;Event 3;2020;December;31;10;180;150;"));
      System.out.println("Print current schedule: ");
      System.out.println(m1.toString());
      
      System.out.println("");
      System.out.println("Add event: Mike Row;Event 2;2009;December;31;9;60;150;");
      m1.add(new Request("Mike Row;Event 5;2020;December;31;9;60;150;"));
      System.out.println("Print current schedule: ");
      System.out.println(m1.toString());
   }

}
