package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public final class ReservationService {
  private Collection<Reservation> reservations;
  private Collection<IRoom> rooms;
  private static ReservationService reservationService;

  private ReservationService() {
    reservations = new HashSet<>();
    rooms = new HashSet<>();
  }

  public static ReservationService create() {
    if (reservationService == null) {
      reservationService = new ReservationService();
    }
    return reservationService;
  }

  public void addRoom(IRoom room) {
    rooms.add(room);
  }

  public IRoom getARoom(String roomId) {
    for (IRoom room : rooms) {
      if (room.getRoomNumber().equals(roomId)) {
        return room;
      }
    }
    return null;
  }

  public Reservation reserveARoom(
      Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
    Reservation reserved = new Reservation(customer, room, checkInDate, checkOutDate);
    if (!reservations.contains(reserved)) {
      reservations.add(reserved);
    }
    return reserved;
  }

  public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
    Collection<IRoom> freeRooms = new ArrayList<>();
    Collection<IRoom> reservedRooms = getReservedRooms(checkInDate, checkOutDate);
    for (IRoom room : rooms) {
      if (!reservedRooms.contains(room)) {
        freeRooms.add(room);
      }
    }
    return freeRooms;
  }

  Set<IRoom> getReservedRooms(Date checkInDate, Date checkOutDate) {
    Set<IRoom> reservedRooms = new HashSet<>();
    for (Reservation r : reservations) {
      IRoom room = r.getRoom();
      if (r.getCheckInDate().before(checkOutDate) && r.getCheckOutDate().after(checkInDate)) {
        reservedRooms.add(room);
      }
    }
    return reservedRooms;
  }

  public Collection<Reservation> getCustomersReservation(Customer customer) {
    Collection<Reservation> customersReservation = new ArrayList<>();
    for (Reservation reservation : reservations) {
      if (reservation.getCustomer().equals(customer)) {
        customersReservation.add(reservation);
      }
    }
    return customersReservation;
  }

  public void printAllReservation() {
    for (Reservation reservation : reservations) {
      System.out.println(reservation);
    }
  }

  public Collection<IRoom> getAllRooms() {
    return rooms;
  }
}
