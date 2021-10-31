package model;

import java.util.regex.Pattern;

public class Customer {
  private String firstName;
  private String lastName;
  private String email;
  private final String emailRegex = "^(.+)@(.+).com$";
  private final Pattern pattern = Pattern.compile(emailRegex);

  public Customer(String firstName, String lastName, String email) {
    if (!pattern.matcher(email).matches()
        || firstName == null
        || lastName == null
        || email == null) {
      throw new IllegalArgumentException();
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getEmail() {
    return this.email;
  }

  @Override
  public String toString() {
    return "Customer [firstName = "
        + getFirstName()
        + ", lastName = "
        + getLastName()
        + ", email = "
        + getEmail()
        + "]";
  }
}
