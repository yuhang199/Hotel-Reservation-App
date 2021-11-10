package model;

public class Room implements IRoom {
  private final String roomNumber;
  private final Double price;
  private final RoomType type;

  public Room(String roomNumber, Double price, RoomType enumeration) {
    if (roomNumber == null || enumeration == null) {
      throw new IllegalArgumentException();
    }
    this.roomNumber = roomNumber;
    this.price = price;
    this.type = enumeration;
  }

  @Override
  public String getRoomNumber() {
    return this.roomNumber;
  }

  @Override
  public Double getRoomPrice() {
    return this.price;
  }

  @Override
  public RoomType getRoomType() {
    return this.type;
  }

  @Override
  public boolean isFree() {
    return this.price == 0;
  }

  @Override
  public String toString() {
    return "Room [roomNumber = "
        + this.getRoomNumber()
        + ", roomType = "
        + this.getRoomType()
        + ", price = "
        + this.getRoomPrice()
        + "]";
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Room)) {
      return false;
    }
    return ((Room) other).getRoomNumber().equals(getRoomNumber())
        && ((Room) other).getRoomType().equals(getRoomType())
        && ((Room) other).getRoomPrice().equals(getRoomPrice());
  }

  @Override
  public int hashCode() {
    return (int) (roomNumber.hashCode() * Math.round(price) + 1);
  }
}
