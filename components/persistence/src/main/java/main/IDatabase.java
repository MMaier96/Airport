package main;

import base.*;

import java.util.ArrayList;

public interface IDatabase{
    void initBaggage();
    void initTables();
    void closeDatabase();
    ArrayList<Baggage> getAllBaggages();
    void addBaggageIdentificationTag(BaggageIdentificationTag baggageIdentificationTag);
    BaggageIdentificationTag getBaggageIdentificationTag(Baggage baggage);
    void addBoardingPass(BoardingPass boardingPass);
    ArrayList<BoardingPass> getBoardingPass(Passenger passenger);
    void addContainer(Container container);
    ArrayList<Container> getContainer();
    void addCottonPad(CottonPad cottonPad);
    ArrayList<CottonPad> getCottonPad();
    DestinationBox getDestinationBox();
    void addEmployee(Employee employee);
    ArrayList<Employee> getEmployee();
    void addFlight(Flight flight);
    ArrayList<Flight> getFlight();
    void addInvoice(Invoice invoice);
    ArrayList<Invoice> getInvoice();
    void addPassenger(Passenger passenger);
    ArrayList<Passenger> getAllPassenger();
    PassengerList getPassengerList();
    Storage getStorrage();
}