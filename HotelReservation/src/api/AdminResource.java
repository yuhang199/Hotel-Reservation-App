package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
  private static AdminResource adminResource;
  private ReservationService reservationService;
  private CustomerService customerService;

  private AdminResource() {
    reservationService = ReservationService.create();
    customerService = CustomerService.create();
  }

  public static AdminResource create() {
    if (adminResource == null) {
      adminResource = new AdminResource();
    }
    return adminResource;
  }

  public Customer getCustomer(String email) {
    return customerService.getCustomer(email);
  }

  public void addRoom(Collection<IRoom> rooms) {
    for (IRoom room : rooms) {
      reservationService.addRoom(room);
    }
  }

  public Collection<IRoom> getAllRooms() {
    return reservationService.getAllRooms();
  }

  public Collection<Customer> getAllCustomers() {
    return customerService.getAllCustomers();
  }

  public void displayAllReservations() {
    reservationService.printAllReservation();
  }
}
