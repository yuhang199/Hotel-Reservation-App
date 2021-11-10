package model;

import java.util.regex.Pattern;

public class Customer {
  private final String firstName;
  private final String lastName;
  private final String email;
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

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Customer)) {
      return false;
    }
    return ((Customer) other).getFirstName().equals(getFirstName())
        && ((Customer) other).getLastName().equals(getLastName())
        && ((Customer) other).getEmail().equals(getEmail());
  }

  @Override
  public int hashCode() {
    return (int) (firstName.hashCode() * lastName.hashCode() + email.hashCode());
  }
}
