package service;

import model.Customer;

import java.util.*;

public final class CustomerService {
  private static CustomerService customerService;
  private Collection<Customer> customers;

  private CustomerService() {
    customers = new HashSet<>();
  }

  public static CustomerService create() {
    if (customerService == null) {
      customerService = new CustomerService();
    }
    return customerService;
  }

  public void addCustomer(String email, String firstName, String lastName) {
    customers.add(new Customer(firstName, lastName, email));
  }

  public Customer getCustomer(String customerEmail) {
    for (Customer customer : customers) {
      if (customer.getEmail().equals(customerEmail)) {
        return customer;
      }
    }
    return null;
  }

  public Collection<Customer> getAllCustomers() {
    return customers;
  }
}
