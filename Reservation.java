/**COPYRIGHT(C). Jonathan Sagabaen. All Rights Reserved.
 * Reservation class implements the Request, MonthlySchedule and Room class.
 * @author Jonathan Sagabaen
 * @version 1.01
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Reservation class is the main interface to interact with the Reservation program.
 */
public class Reservation
{
   public static void main(String[] args) throws IOException
   {
      //Create two rooms of capacity 50 and 200
      Room room0 = new Room(50);
      Room room1 = new Room(200);
      
      System.out.print("Welcome to the Reservation program ! Today is ");
      String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", 
            "Aug", "Sep", "Oct", "Nov", "Dec" }; 
      int year; 
      // get today's date
      GregorianCalendar gcalendar = new GregorianCalendar() ; 
      System.out.print("Date: ");
      System.out.print(months[gcalendar.get(Calendar.MONTH)]);
      System.out.print(" " + gcalendar.get(Calendar.DATE) + " ");
      System.out.println(year = gcalendar.get(Calendar.YEAR));
      
      System.out.println("");
      System.out.println("Enter the file name that contains the existing schedule.input.txt");
      
      Scanner keyboard = new Scanner(System.in);
      Scanner keyboardLine = new Scanner(System.in);
      String fileName = keyboard.next();
      Scanner fScanner = new Scanner(new FileReader(fileName));
      
      System.out.println("");
      System.out.println("Current Reservations");
      System.out.println("");
      
      //Get input.txt
      while(fScanner.hasNextLine())
      {
         Request requestLine = new Request(fScanner.nextLine());
         if (requestLine.getDate().get(Calendar.HOUR_OF_DAY) < 9 || requestLine.getDate().get(Calendar.HOUR_OF_DAY) > 22)
         {
            System.out.println("Error: Request is not within office hours");
            System.out.println("Please enter another file name");
            fileName = keyboard.next();
            fScanner = new Scanner(new FileReader(fileName));
         }
         else
         {
            System.out.println(requestLine.toString());
            
            if (requestLine.getSeats() <= 50)
               room0.add(requestLine);
            else if (requestLine.getSeats() <= 200)
               room1.add(requestLine);
            else
            {
               System.out.println("Error: The amount of seats requested exceed room capacity");
               System.out.println("Please enter another file name");
               fileName = keyboard.next();
               fScanner = new Scanner(new FileReader(fileName));
            }
         } 
      }
      System.out.println("");

      //Executes a loop for the user's action
      boolean done = false;
      while (!done)
      {
         System.out.println("Enter one of the following option");
         System.out.println("add  cancel  display  quit");
         String action = keyboard.next();
         
         if (action.equalsIgnoreCase("add"))
         {
            System.out.println("");
            System.out.println("Enter your request ");
            System.out.println("Please enter request in the following format: Full Name;Event Name;Month;Day;Hour;Duration;Number of Seats;");
            String requestLine = keyboardLine.nextLine();
            String[] temp = requestLine.toString().split(";");
            if (temp.length != 7)
               System.out.println("Error: Improper request format");
            else
            {              
               String name = temp[0];
               String eventName = temp[1];
               String month = temp[2];
               if (month.equalsIgnoreCase("January") || month.equalsIgnoreCase("February") || month.equalsIgnoreCase("March") || month.equalsIgnoreCase("April") 
                  || month.equalsIgnoreCase("May") || month.equalsIgnoreCase("June") || month.equalsIgnoreCase("July") || month.equalsIgnoreCase("August") 
                  || month.equalsIgnoreCase("September") || month.equalsIgnoreCase("October") || month.equalsIgnoreCase("November") || month.equalsIgnoreCase("December"))
               {
                  int day = Integer.parseInt(temp[3]);
                  if (day == 0 || day > 31)
                     System.out.println("Error: Invalid date entered");
                  else
                  {  
                     int hour = Integer.parseInt(temp[4]);
                     if (hour < 9 || hour > 22)
                        System.out.println("Error: Request is not within office hours");
                     else
                     {
                        int duration = Integer.parseInt(temp[5]);
                        if (duration % 60 != 0)
                           System.out.println("Error: Invalid duration entry");
                        else
                        {
                           int seats = Integer.parseInt(temp[6]);
                           if (seats == 0 || seats > 200)
                              System.out.println("Error: The amount of seats requested exceed room capacity");
                           else
                           {
                              if (seats <= 50 && room0.add(new Request(name + ";" + eventName + ";" + year + ";" + month + ";" + day + ";" + hour + ";" + duration + ";" + seats + ";")))
                              {
                                 room0.add(new Request(name + ";" + eventName + ";" + year + ";" + month + ";" + day + ";" + hour + ";" + duration + ";" + seats + ";"));
                                 System.out.println("Request succesffuly added to room0");
                                 System.out.println("");
                              }
                              else if (seats > 50 && seats <= 200 && room1.add(new Request(name + ";" + eventName + ";" + year + ";" + month + ";" + day + ";" + hour + ";" + duration + ";" + seats + ";")))
                              {
                                 room1.add(new Request(name + ";" + eventName + ";" + year + ";" + month + ";" + day + ";" + hour + ";" + duration + ";" + seats + ";"));
                                 System.out.println("Request succesffuly added to room1");
                                 System.out.println("");
                              }
                              else
                                 System.out.println("Error: Request conflicted with an existing event");
                           }
                        }
                     }
                  }
               }
               else
                  System.out.println("Error: Invalid month entered");
            }
         }
         else if (action.equalsIgnoreCase("cancel"))
         {
            System.out.println("Enter the room you reserved [0 or 1]");
            int roomNumber = keyboard.nextInt();
            if (roomNumber < 0 || roomNumber > 1)
               System.out.println("Error: Invalid number entered");
            else
            {
               System.out.println("Enter the month");
               String month = keyboard.next();
               int monthNumber;
               if (month.equalsIgnoreCase("January"))
                  monthNumber = 0;
               else if (month.equalsIgnoreCase("February"))
                  monthNumber = 1;
               else if (month.equalsIgnoreCase("March"))
                  monthNumber = 2;
               else if (month.equalsIgnoreCase("April"))
                  monthNumber = 3;
               else if (month.equalsIgnoreCase("May"))
                  monthNumber = 4;
               else if (month.equalsIgnoreCase("June"))
                  monthNumber = 5;
               else if (month.equalsIgnoreCase("July"))
                  monthNumber = 6;
               else if (month.equalsIgnoreCase("August"))
                  monthNumber = 7;
               else if (month.equalsIgnoreCase("September"))
                  monthNumber = 8;
               else if (month.equalsIgnoreCase("October"))
                  monthNumber = 9;
               else if (month.equalsIgnoreCase("November"))
                  monthNumber = 10;
               else if (month.equalsIgnoreCase("December"))
                  monthNumber = 11;
               else
                  monthNumber = -1;
               
               if (monthNumber == -1)
                  System.out.println("Error: Invalid month number entered");
               else
               {
                  System.out.println("Please enter the full name of the person who made the request");
                  String name = keyboardLine.nextLine();
                  
                  if (roomNumber == 0)
                  {
                     fScanner = new Scanner(room0.printMonthlySchedule(monthNumber));
                     while(fScanner.hasNextLine())
                     {
                        Request requestLine = new Request(fScanner.nextLine());
                        String[] temp = requestLine.toString().split(";");                        
                        if (temp[0].equalsIgnoreCase(name))
                        {
                           room0.cancel(requestLine);
                           System.out.println("Request successfully cancelled");
                           System.out.println("");
                        }
                        else
                           System.out.println("Error: Request could not be found");
                     }
                  }
                  else if (roomNumber == 1)
                  {
                     fScanner = new Scanner(room1.printMonthlySchedule(monthNumber));
                     while(fScanner.hasNextLine())
                     {
                        Request requestLine = new Request(fScanner.nextLine());
                        String[] temp = requestLine.toString().split(";");
                        if (temp[0].equalsIgnoreCase(name))
                        {
                           room1.cancel(requestLine);
                           System.out.println("Request successfully cancelled");
                           System.out.println("");
                        }
                        else
                           System.out.println("Error: Request could not be found");
                     }
                  }
                  else
                     System.out.println("Error: Request could not be found");
               }
            }
         }
         else if (action.equalsIgnoreCase("display"))
         {
            System.out.println("Enter the Month of the Reservation");
            String month = keyboard.next();
            int monthNumber;
            if (month.equalsIgnoreCase("January"))
               monthNumber = 0;
            else if (month.equalsIgnoreCase("February"))
               monthNumber = 1;
            else if (month.equalsIgnoreCase("March"))
               monthNumber = 2;
            else if (month.equalsIgnoreCase("April"))
               monthNumber = 3;
            else if (month.equalsIgnoreCase("May"))
               monthNumber = 4;
            else if (month.equalsIgnoreCase("June"))
               monthNumber = 5;
            else if (month.equalsIgnoreCase("July"))
               monthNumber = 6;
            else if (month.equalsIgnoreCase("August"))
               monthNumber = 7;
            else if (month.equalsIgnoreCase("September"))
               monthNumber = 8;
            else if (month.equalsIgnoreCase("October"))
               monthNumber = 9;
            else if (month.equalsIgnoreCase("November"))
               monthNumber = 10;
            else if (month.equalsIgnoreCase("December"))
               monthNumber = 11;
            else
               monthNumber = -1;
            
            if (monthNumber == -1)
               System.out.println("Error: Entered an invalid month");
            else
            {
               System.out.println("/* Room 0 */");
               System.out.println(month);
               System.out.println(room0.printMonthlySchedule(monthNumber));
               System.out.println("/* Room 1*/");
               System.out.println(month);
               System.out.println(room1.printMonthlySchedule(monthNumber));
            }
         }
         else if (action.equalsIgnoreCase("quit"))
         {
            PrintWriter fWrite = new PrintWriter("output.txt");
            for (int i = 0; i < 12; i++)
            {
               String month;
               if (i == 0)
                  month = "January";
               else if (i == 1)
                  month = "February";
               else if (i == 2)
                  month = "March";
               else if (i == 3)
                  month = "April";
               else if (i == 4)
                  month = "May";
               else if (i == 5)
                  month = "June";
               else if (i == 6)
                  month = "July";
               else if (i == 7)
                  month = "August";
               else if (i == 8)
                  month = "September";
               else if (i == 9)
                  month = "October";
               else if (i == 10)
                  month = "November";
               else
                  month = "December";
               
               fWrite.println("/* Room 0 */");
               fWrite.println(month);
               fWrite.println(room0.printMonthlySchedule(i));
               
               fWrite.println("/* Room 1*/");
               fWrite.println(month);
               fWrite.println(room1.printMonthlySchedule(i));
            }
            fWrite.close();
            done = true;
         }
         else
            System.out.println("Error: Entered invalid action");
      }
      fScanner.close();
      System.out.println("");
      System.out.println("Thank you for using this reservation system !");
   }

}
