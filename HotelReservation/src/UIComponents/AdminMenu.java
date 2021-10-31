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
      } while (!isValidService(selection));

      if (selection.equals("1")) {
        Collection<Customer> customers = adminResource.getAllCustomers();
        for (Customer customer : customers) {
          System.out.println(customer);
        }
      }

      if (selection.equals("2")) {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        for (IRoom room : rooms) {
          System.out.println(room);
        }
      }

      if (selection.equals("3")) {
        adminResource.displayAllReservations();
      }

      if (selection.equals("4")) {
        Collection<IRoom> toAdd = new HashSet<>();
        int flag2 = 0;
        do {
          System.out.println("Enter a Room to add: ");
          String roomID = in.nextLine();
          System.out.println("Enter the Room Price: ");
          double price = in.nextDouble();
          in.nextLine();
          String type;
          do {
            System.out.println("Enter the Room Type: ");
            type = in.nextLine();
          } while (!type.equalsIgnoreCase("single") && !type.equalsIgnoreCase("double"));
          RoomType roomType = RoomType.valueOf(type);
          toAdd.add(new Room(roomID, price, roomType));
          System.out.println("Finish adding? (yes/no)");
          String decision = in.nextLine();
          if (decision.equals("yes")) {
            flag2 = 1;
          }
          if (!decision.equals("yes") && !decision.equals("no")) {
            flag2 = 1;
            flag = 1;
          }
        } while (flag2 == 0);
        adminResource.addRoom(toAdd);
      }
      if (selection.equals("5")) {
        flag = 1;
      }
    } while (flag == 0);

    MainMenu mainMenu = new MainMenu();
    mainMenu.startMain();
  }
}
