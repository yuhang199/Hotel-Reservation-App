package model;

import java.util.Date;

public class Reservation {
  private final Customer customer;
  private final IRoom room;
  private final Date checkInDate;
  private final Date checkOutDate;

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

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Reservation)) {
      return false;
    }
    return ((Reservation) other).getCustomer().equals(getCustomer())
            && ((Reservation) other).getRoom().equals(getRoom())
            && ((Reservation) other).getCheckInDate().equals(getCheckInDate())
            && ((Reservation) other).getCheckOutDate().equals(getCheckOutDate());
  }

  @Override
  public int hashCode() {
    return (int) customer.hashCode() * room.hashCode();
  }
}
