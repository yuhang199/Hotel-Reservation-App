package model;

public class FreeRoom extends Room {
  private String roomNumber;
  private RoomType enumeration;

  public FreeRoom(String roomNumber, RoomType enumeration) {
    super(roomNumber, 0.0, enumeration);
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
}
