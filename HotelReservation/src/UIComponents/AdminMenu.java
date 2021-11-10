package UIComponents;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.text.ParseException;
import java.util.*;

public final class AdminMenu {
  public static void main(String args[]) throws ParseException {
    AdminMenu adminMenu = new AdminMenu();
    adminMenu.startAdminMain();
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

  public void startAdminMain() throws ParseException {
    AdminResource adminResource = AdminResource.create();
    int flag = 0;

    Scanner in = new Scanner(System.in);
    System.out.println("You are now in the administration interface.");
    String selection;
    do {
      do {
        System.out.println("-------------------------------");
        System.out.println("Please Select An Operation:");
        System.out.println("1. See all Customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        selection = in.nextLine();
        if (!isValidService(selection)) {
          System.out.println("Invalid selection!");
        }
      } while (!isValidService(selection));

      switch (selection) {
        case "1" -> {
          Collection<Customer> customers = adminResource.getAllCustomers();
          for (Customer customer : customers) {
            System.out.println(customer);
          }
        }
        case "2" -> {
          Collection<IRoom> rooms = adminResource.getAllRooms();
          for (IRoom room : rooms) {
            System.out.println(room);
          }
        }
        case "3" -> adminResource.displayAllReservations();
        case "4" -> {
          Collection<IRoom> toAdd = new HashSet<>();
          int flag2 = 0;
          do {
            System.out.println("Enter a Room to add: ");
            String roomID = in.nextLine();
            System.out.println("Enter the Room Price: ");
            String priceStr;
            double price = 0;
            int flag3 = 0;
            do {
              try {
                priceStr = in.nextLine();
                price = Double.parseDouble(priceStr);
                flag3 = 0;
              } catch (NumberFormatException e) {
                System.out.println("Invalid price! Please enter a number");
                flag3 = 1;
              }
            } while (flag3 != 0);
            System.out.println(price);
            String type;
            do {
              System.out.println("Enter the Room Type: (single/double)");
              type = in.nextLine();
              if (!type.equalsIgnoreCase("single") && !type.equalsIgnoreCase("double")) {
                System.out.println("Invalid Room Type!");
              }
            } while (!type.equalsIgnoreCase("single") && !type.equalsIgnoreCase("double"));
            RoomType roomType;
            if (type.equalsIgnoreCase("single")) {
              roomType = RoomType.SINGLE;
            } else {
              roomType = RoomType.DOUBLE;
            }
            IRoom newRoom = new Room(roomID, price, roomType);
            if (!toAdd.contains(newRoom)) {
              toAdd.add(newRoom);
            }
            System.out.println("Finish adding? (yes/no)");
            String decision = in.nextLine();
            if (decision.equalsIgnoreCase("yes")) {
              flag2 = 1;
            }
            if (!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no")) {
              flag2 = 1;
            }
          } while (flag2 == 0);
          adminResource.addRoom(toAdd);
        }
        case "5" -> flag = 1;
      }
    } while (flag == 0);

    MainMenu mainMenu = new MainMenu();
    mainMenu.startMain();
  }
}
