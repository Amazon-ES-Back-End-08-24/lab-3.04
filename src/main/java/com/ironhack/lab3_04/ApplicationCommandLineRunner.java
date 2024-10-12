package com.ironhack.lab3_04;


import com.ironhack.lab3_04.enums.CustomerStatus;
import com.ironhack.lab3_04.model.Customer;
import com.ironhack.lab3_04.model.Flight;
import com.ironhack.lab3_04.model.FlightBooking;
import com.ironhack.lab3_04.repository.CustomerRepository;
import com.ironhack.lab3_04.repository.FlightBookingRepository;
import com.ironhack.lab3_04.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightBookingRepository flightBookingRepository;


    @Override
    public void run(String... args) throws Exception {
        Customer customer1 = new Customer("John Doe", CustomerStatus.Gold, 50000);
        Customer customer2 = new Customer("Jane Smith", CustomerStatus.Silver, 20000);
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Flight flight1 = new Flight("AA123", "Boeing 747", 300, 1000L);
        Flight flight2 = new Flight("BA456", "Airbus A320", 150, 600L);
        flightRepository.save(flight1);
        flightRepository.save(flight2);

        FlightBooking booking1 = new FlightBooking(customer1, flight1);
        FlightBooking booking2 = new FlightBooking(customer2, flight2);
        flightBookingRepository.save(booking1);
        flightBookingRepository.save(booking2);

        List<Customer> allCustomers = customerRepository.findAll();
        System.out.println("Here are all the clients:");
        allCustomers.forEach(System.out::println);
        // forEach es un method que tienen las listas, los iterables, que es lo mismo que lo siguiente, pero más fácil de leer
        // lo que haces es, sobre la lista allCustomers, llamas a .forEach() y le pasas como parámetro lo que quieres que ejecute para cada iteración

//        for (Customer customer : allCustomers) {
//            System.out.println(customer);
//        }

        List<Flight> allFlights = flightRepository.findAll();
        System.out.println("Here are all the flights:");
        allFlights.forEach(System.out::println);

        String name = "John Doe";
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerName(name);
        if (optionalCustomer.isPresent()) {
            System.out.println("Coustomer found by customer name " + name + " : " + optionalCustomer.get());
        } else {
            System.out.println("No customer found by name " + name);
        }

        List<Customer> goldCustomers = customerRepository.findByCustomerStatus(CustomerStatus.Gold);
        System.out.println("Here are all the customers with Gold status:");
        goldCustomers.forEach(System.out::println);

        // ifPresentOrElse es un método chulo que hace lo mismo que el if(optional.isPresent()) else que hemos hecho arriba en las líneas 63-67
        String flightNumber = "AA123";
        flightRepository.findByFlightNumber(flightNumber).ifPresentOrElse(flight ->
                        System.out.println("Flight found by flight number " + flightNumber + " : " + flight),
                () -> System.out.println("No flight found by flight number " + flightNumber)
        );


        List<Flight> boeingFlights = flightRepository.findByAircraftContaining("Boeing");
        System.out.println("Flights with Boeing flights:");
        boeingFlights.forEach(System.out::println);

        List<Flight> longFlights = flightRepository.findByFlightMileageGreaterThan(500L);
        System.out.println("Flights with mileage greater than 500:");
        longFlights.forEach(System.out::println);
    }
}
