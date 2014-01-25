/**COPYRIGHT(C). Jonathan Sagabaen. All Rights Reserved.
 * RoomTest is a test class for Room.java.
 * @author Jonathan Sagabaen
 * @version 1.01
 */
public class RoomTest
{
   
   public static void main(String[] args)
   {
      Room r1 = new Room(50);
      System.out.println("Add Event 1");
      r1.add(new Request("John Doe;Event 1;2020;December;14;9;60;44;"));
      System.out.println("Current Monthly Schedule of December");
      System.out.println(r1.printMonthlySchedule(11));
      
      System.out.println("Add Event 2");
      r1.add(new Request("Jon Doe;Event 2;2020;December;10;15;180;11;"));
      System.out.println("Current Monthly Schedule of December");
      System.out.println(r1.printMonthlySchedule(11));
      
      System.out.println("Cancel Event 2");
      r1.cancel(new Request("Jon Doe;Event 2;2020;December;10;15;180;11;"));
      System.out.println("Current Monthly Schedule of December");
      System.out.println(r1.printMonthlySchedule(11));
      
      
      System.out.println("--------------------------------------------------");
      
      Room r2 = new Room(200);
      System.out.println("Add Event 1");
      r2.add(new Request("Matt Smith;Event 1;2020;March;29;11;120;80;"));
      System.out.println("Current Monthly Schedule of March");
      System.out.println(r2.printMonthlySchedule(2));
      
      System.out.println("Add Event 2");
      r2.add(new Request("David Smith;Event 2;2020;March;29;11;120;63;"));
      System.out.println("Event 2 has Time Conflict");
      System.out.println("Current Monthly Schedule of March");
      System.out.println(r2.printMonthlySchedule(2));
     
      System.out.println("Add Event 3");
      r2.add(new Request("Mathew Smith;Event 3;2020;March;29;8;120;70;"));
      System.out.println("Current Monthly Schedule of March");
      System.out.println(r2.printMonthlySchedule(2));

   }
}
