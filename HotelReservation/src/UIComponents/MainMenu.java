package UIComponents;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class MainMenu {
  public static void main(String[] args) throws ParseException {
    MainMenu mainMenu = new MainMenu();
    mainMenu.startMain();
  }

  public static boolean isValidService(String service) {
    if (service.equals("1")
        || service.equals("2")
        || service.equals("3")
        || service.equals("4")
        || service.equals("5")) {
      return true;
    }
    return false;
  }

  public void startMain() throws ParseException {
    HotelResource hotelResource = HotelResource.create();
    AdminResource adminResource = AdminResource.create();
    String emailRegex = "^(.+)@(.+).com$";
    Pattern emailPattern = Pattern.compile(emailRegex);

    Scanner in = new Scanner(System.in);
    System.out.println("Welcome to the Scatman's world!");
    String selection;
    do {
      do {
        System.out.println("-------------------------------");
        System.out.println("Please Select A Service:");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        selection = in.nextLine();
      } while (!isValidService(selection));

      switch (selection) {
        case "1" -> {
          System.out.println("-------------------------------");
          String checkIn;
          String checkOut;
          do {
            System.out.println("Please Enter CheckIn Date: (MM/DD/YYYY)");
            checkIn = in.nextLine();
            if (!isDateValid(checkIn)) {
              System.out.println("Invalid Date!");
            }
          } while (!isDateValid(checkIn));
          do {
            System.out.println("Please Enter CheckOut Date: (MM/DD/YYYY)");
            checkOut = in.nextLine();
            if (!isDateValid(checkOut)) {
              System.out.println("Invalid Date!");
            }
          } while (!isDateValid(checkOut));
          SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
          Date checkInDate = formatter.parse(checkIn);
          Date checkOutDate = formatter.parse(checkOut);
          while (checkOutDate.before(checkInDate)) {
            System.out.println("CheckOut Date must be after CheckIn Date!");
            do {
              System.out.println("Please Enter CheckOut Date: (MM/DD/YYYY)");
              checkOut = in.nextLine();
              if (!isDateValid(checkOut)) {
                System.out.println("Invalid Date!");
              }
            } while (!isDateValid(checkOut));
            checkOutDate = formatter.parse(checkOut);
          }
          Collection<IRoom> freeRooms = hotelResource.findARoom(checkInDate, checkOutDate);
          for (IRoom room : freeRooms) {
            System.out.println(room);
          }
          String email;
          String room;
          do {
            System.out.println("Please Enter Email:");
            email = in.nextLine();
            if (!emailPattern.matcher(email).matches()) {
              System.out.println("Invalid Email!");
            }
          } while (!emailPattern.matcher(email).matches());
          if (hotelResource.getCustomer(email) == null
                  && adminResource.getCustomer(email) == null) {
            System.out.println("PLEASE CREATE YOUR ACCOUNT FIRST.");
          } else {
            do {
              System.out.println("Please Select Room:");
              room = in.nextLine();
              if (hotelResource.getRoom(room) == null) {
                System.out.println("The Room Doesn't exist!");
              }
            } while (hotelResource.getRoom(room) == null);
            hotelResource.bookARoom(email, hotelResource.getRoom(room), checkInDate, checkOutDate);
          }
        }
        case "2" -> {
          String email2;
          do {
            System.out.println("Please Enter Email:");
            email2 = in.nextLine();
            if (!emailPattern.matcher(email2).matches()) {
              System.out.println("Invalid Email!");
            }
          } while (!emailPattern.matcher(email2).matches());
          if (hotelResource.getCustomer(email2) == null) {
            System.out.println("PLEASE CREATE YOUR ACCOUNT FIRST.");
          } else {
            Collection<Reservation> reservations = hotelResource.getCustomersReservation(email2);
            for (Reservation reservation : reservations) {
              System.out.println(reservation);
            }
          }
        }
        case "3" -> {
          String email3;
          do {
            System.out.println("Please Enter Email:");
            email3 = in.nextLine();
            if (!emailPattern.matcher(email3).matches()) {
              System.out.println("Invalid Email!");
            }
          } while (!emailPattern.matcher(email3).matches());
          System.out.println("Please Enter First Name:");
          String firstName = in.nextLine();
          System.out.println("Please Enter Last Name:");
          String lastName = in.nextLine();
          hotelResource.createACustomer(email3, firstName, lastName);
        }
        case "4" -> {
          AdminMenu adminMenu = new AdminMenu();
          adminMenu.startAdminMain();
        }
        case "5" -> System.exit(0);
      }
    } while (true);
  }

  public static boolean isDateValid(String date) {
    try {
      DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
      df.setLenient(false);
      df.parse(date);
      return true;
    } catch (ParseException e) {
      return false;
    }
  }
}
