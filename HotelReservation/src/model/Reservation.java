package model;

import java.util.Date;

public class Reservation {
  private Customer customer;
  private IRoom room;
  private Date checkInDate;
  private Date checkOutDate;

  public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
    if (customer == null || room == null || checkInDate == null || checkOutDate == null) {
      throw new IllegalArgumentException();
    }
    this.customer = customer;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.room = room;
  }

  public Customer getCustomer() {
    return this.customer;
  }

  public IRoom getRoom() {
    return this.room;
  }

  public Date getCheckInDate() {
    return this.checkInDate;
  }

  public Date getCheckOutDate() {
    return this.checkOutDate;
  }

  @Override
  public String toString() {
    return "Reservation [Customer = "
        + getCustomer()
        + ", room = "
        + getRoom()
        + ", checkInDate = "
        + getCheckInDate()
        + ", checkOutDate = "
        + getCheckOutDate()
        + "]";
  }
}
