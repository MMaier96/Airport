package main;

import base.*;
import engine.LogEngine;
import javafx.scene.chart.PieChart;
import sql.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private LogEngine logEngine;

    private Connection connection;
    private String driverName = "jdbc:hsqldb:";
    private String username = "sa";
    private String password = "";

    private static Database instance = new Database();
    public Port port;

    public Database() {
        port = new Port();
    }

    public static Database getInstance() {
        return instance;
    }

    public class Port implements IDatabase {
        public void initBaggage() {
            innerMethodInitBaggage();
        }

        public void initTables() {
            innerMethodinitTables();
        }

        public void closeDatabase(){
            shutdown();
        }

        public ArrayList<Baggage> getAllBaggages() {
            return innerMethodGetAllBaggages();
        }

        public void addBaggageIdentificationTag(BaggageIdentificationTag baggageIdentificationTag) {    //Done
            innerMethodAddBaggageIdentificationTag(baggageIdentificationTag);
        }

        public BaggageIdentificationTag getBaggageIdentificationTag(Baggage baggage) {
            return innerMethodGetBaggageIdentificationTag(baggage);
        }

        public void addBoardingPass(BoardingPass boardingPass) {                                        //Done
            innerMethodAddBoardingPass(boardingPass);
        }

        public ArrayList<BoardingPass> getBoardingPass(Passenger passenger) {
            return innerMethodGetBoardingPass();
        }

        public void addContainer(Container container) {                                                                     //Done
            innerMethodAddContainer(container);
        }

        public ArrayList<Container> getContainer() {
            return innerMethodGetContainer();
        }

        public void addCottonPad(CottonPad cottonPad) {
            innerMethodAddCottonPad(cottonPad);
        }

        public ArrayList<CottonPad> getCottonPad() {
            return innerMethodGetCottonPad();
        }

        public DestinationBox getDestinationBox() {
            return innerMethodGetDestinationBox();
        }

        public void addEmployee(Employee employee) {
            innerMethodAddEmployee(employee);
        }

        public ArrayList<Employee> getEmployee() {
            return innerMethodGetEmployee();
        }

        public void addFlight(Flight flight) {
            innerMethodAddFlight(flight);
        }

        public ArrayList<Flight> getFlight() {
            return innerMethodGetFlight();
        }

        public void addInvoice(Invoice invoice) {
            innerMethodAddInvoice(invoice);
        }

        public ArrayList<Invoice> getInvoice() {
            return innerMethodGetInvoice();
        }

        public void addPassenger(Passenger passenger) {
            innerMethodAddPassenger(passenger);
        }

        public ArrayList<Passenger> getAllPassenger() {
            return innerMethodGetAllPassenger();
        }

        public Storage getStorrage() {
            return innerMethodGetStorage();
        }

        public void setLogEngine(LogEngine logEngine) {
            innerSetLogEngine(logEngine);
        }

        public PassengerList getPassengerList() {
            return innerMethodGetPassengerList();
        }
    }


    private PassengerList innerMethodGetPassengerList() {
        init();
        PassengerSQL passengerSQL = new PassengerSQL(Database.instance);
        try {
            return new PassengerList(passengerSQL.buildSelectSQLStatement());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Storage innerMethodGetStorage() {
        init();
        ContainerSQL containerSQL = new ContainerSQL(Database.instance);
        try {
            return containerSQL.buildSelectSQLStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<Baggage> innerMethodGetAllBaggages() {
        init();
        BaggageSQL baggageSQL = new BaggageSQL(Database.instance);
        try {
            return baggageSQL.buildSelectSQLStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private ArrayList<Container> innerMethodGetContainer() {
        init();
        ContainerSQL containerSQL = new ContainerSQL(Database.instance);
        try {
            Storage storage = containerSQL.buildSelectSQLStatement();
            return storage.getContainerList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void innerMethodAddCottonPad(CottonPad cottonPad) {
        init();
        CottonPadSQL cottonPadSQL = new CottonPadSQL(Database.instance);
        cottonPadSQL.insert(cottonPad, logEngine);
    }

    private ArrayList<CottonPad> innerMethodGetCottonPad() {
        init();
        CottonPadSQL cottonPadSQL = new CottonPadSQL(Database.instance);
        try {
            return cottonPadSQL.buildSelectSQLStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void innerMethodAddContainer(Container container) {
        init();
        ContainerSQL containerSQL = new ContainerSQL(Database.instance);
        containerSQL.insert(container, logEngine);
    }

    private ArrayList<BoardingPass> innerMethodGetBoardingPass() {
        init();
        BoardingPassSQL boardingPassSQL = new BoardingPassSQL(Database.instance);
        try {
            return new ArrayList<BoardingPass>(boardingPassSQL.buildSelectSQLStatement());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    private DestinationBox innerMethodGetDestinationBox() {
        init();
        BaggageSQL destinationBoxSQL = new BaggageSQL(Database.instance);
        try {
            return new DestinationBox(destinationBoxSQL.buildSelectSQLStatement());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void innerMethodAddEmployee(Employee employee) {
        init();
        EmployeeSQL employeeSQL = new EmployeeSQL(Database.instance);
        employeeSQL.insert(employee, logEngine);
    }

    private ArrayList<Employee> innerMethodGetEmployee() {
        init();
        EmployeeSQL employeeSQL = new EmployeeSQL(Database.instance);
        try {
            return employeeSQL.buildSelectSQLStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void innerMethodAddFlight(Flight flight) {
        init();
        FlightSQL flightSQL = new FlightSQL(Database.instance);
        flightSQL.insert(flight, logEngine);
    }

    private ArrayList<Flight> innerMethodGetFlight() {
        init();
        FlightSQL flightSQL = new FlightSQL(Database.instance);
        try {
            return new ArrayList<Flight>(flightSQL.buildSelectSQLStatement());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void innerMethodAddInvoice(Invoice invoice) {
        init();
        InvoiceSQL invoiceSQL = new InvoiceSQL(Database.instance);
        invoiceSQL.insert(invoice, logEngine);
    }

    private ArrayList<Invoice> innerMethodGetInvoice() {
        init();
        InvoiceSQL invoiceSQL = new InvoiceSQL(Database.instance);
        try {
            return new ArrayList<Invoice>(invoiceSQL.buildSelectSQLStatement());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void innerMethodAddPassenger(Passenger passenger) {
        init();
        PassengerSQL passengerSQL = new PassengerSQL(Database.instance);
        passengerSQL.insert(passenger, logEngine);
    }

    private ArrayList<Passenger> innerMethodGetAllPassenger() {
        init();
        PassengerSQL passengerSQL = new PassengerSQL(Database.instance);
        try {
            return new ArrayList<Passenger>(passengerSQL.buildSelectSQLStatement());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void innerMethodAddBoardingPass(BoardingPass boardingPass) {
        init();
        BoardingPassSQL boardingPassSQL = new BoardingPassSQL(Database.instance);
        boardingPassSQL.insert(boardingPass, logEngine);
    }

    private BaggageIdentificationTag innerMethodGetBaggageIdentificationTag(Baggage baggage) {
        init();
        BaggageIdentificationTagSQL baggageIdentificationTagSQL = new BaggageIdentificationTagSQL(Database.instance);
        try {
            BaggageIdentificationTag baggageIdentificationTag = baggageIdentificationTagSQL.buildSelectSQLStatement(baggage);
            return baggageIdentificationTag;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void innerMethodAddBaggageIdentificationTag(BaggageIdentificationTag baggageIdentificationTag) {
        init();
        BaggageIdentificationTagSQL baggageIdentificationTagSQL = new BaggageIdentificationTagSQL(Database.instance);
        baggageIdentificationTagSQL.insert(baggageIdentificationTag, logEngine);
    }

    //Essentials
    public Connection getConnection() {
        return connection;
    }

    public void startup(String dataPath) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String databaseURL = driverName + dataPath + "records.db";
            connection = DriverManager.getConnection(databaseURL, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void shutdown() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public synchronized void update(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlStatement);
            if (result == -1)
                System.out.println("error executing " + sqlStatement);
            statement.close();
        } catch (SQLException sqle) {
            sqle.getStackTrace();
        }
    }


    public void innerSetLogEngine(LogEngine logEngine) {
        this.logEngine = logEngine;
    }

    public void innerMethodinitTables() {
        init();
        BaggageIdentificationTagSQL baggageIdentificationTagSQL = new BaggageIdentificationTagSQL(Database.instance);
        BaggageSQL baggageSQL = new BaggageSQL(Database.instance);
        BoardingPassSQL boardingPassSQL = new BoardingPassSQL(Database.instance);
        ContainerProfileSQL containerProfileSQL = new ContainerProfileSQL(Database.instance);
        ContainerSQL containerSQL = new ContainerSQL(Database.instance);
        CottonPadSQL cottonPadSQL = new CottonPadSQL(Database.instance);
        EmployeeSQL employeeSQL = new EmployeeSQL(Database.instance);
        FlightSQL flightSQL = new FlightSQL(Database.instance);
        InvoiceSQL invoiceSQL = new InvoiceSQL(Database.instance);
        PassengerSQL passengerSQL = new PassengerSQL(Database.instance);

        baggageIdentificationTagSQL.dropTableBaggageIdentificationTag(logEngine);
        baggageSQL.dropTableBaggage(logEngine);
        boardingPassSQL.dropTableBoardingPass(logEngine);
        containerProfileSQL.dropTableContainerProfile(logEngine);
        containerSQL.dropTableContainer(logEngine);

        cottonPadSQL.dropTableCottonPad(logEngine);
        employeeSQL.dropTableBaggage(logEngine);
        flightSQL.dropTableFlight(logEngine);
        invoiceSQL.dropTableInvoice(logEngine);
        passengerSQL.dropTableStorage(logEngine);

        baggageIdentificationTagSQL.createTableBaggageIdentificationTag(logEngine);
        baggageSQL.dropTableBaggage(logEngine);
        boardingPassSQL.createTableBoardingPass(logEngine);
        containerProfileSQL.createTableContainerProfile(logEngine);
        containerSQL.createTableContainer(logEngine);
        ;
        cottonPadSQL.createTableCottonPad(logEngine);
        employeeSQL.createTableBaggage(logEngine);
        flightSQL.createTableFlight(logEngine);
        invoiceSQL.createTableInvoice(logEngine);
        passengerSQL.createTablePassenger(logEngine);

    }

    public void innerMethodInitBaggage() {
        BaggageSQL sql = new BaggageSQL(Database.instance);

        String csvFile = Configuration.instance.baggage_archive;
        BufferedReader br = null;
        String line = "";
        ArrayList<String> baggages = new ArrayList<String>();
        sql.dropTableBaggage(logEngine);
        sql.createTableBaggage(logEngine);

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                for (String value : line.split(";")) {
                    baggages.add(value);
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        for (int i = 0; i < baggages.size(); ) {
            for (int j = 1; j < baggages.size(); ) {
                for (int k = 2; k < baggages.size(); k += 3) {
                    Baggage baggage = new Baggage(baggages.get(i), baggages.get(j), Double.parseDouble(baggages.get(k)), BaggageType.Normal);
                    sql.insert(baggage, logEngine);
                    i += 3;
                    j += 3;
                }
            }
        }
    }

    public void init() {
        LogEngine log = new LogEngine(Configuration.instance.logFile);
        Database.instance.innerSetLogEngine(log);
        Database.instance.startup(Configuration.instance.dataPath);
    }

    public static void main(String... args) {

        LogEngine log = new LogEngine(Configuration.instance.logFile);
        ArrayList<Passenger> list = new ArrayList<Passenger>();
        ArrayList<Baggage> baggages = new ArrayList<Baggage>();
        baggages.add(new Baggage("1", "content", 12.0, BaggageType.Normal));
        baggages.add(new Baggage("2", "contents", 15.0, BaggageType.Normal));

        list.add(new Passenger("1", "name", "content", "12.09.1996", "rei", "mos", "ci", "5646", "visa", CitizenshipCode.DEU, Gender.Male, baggages, new BoardingPass("1", Carrier.Emirates, "flight", "name", TicketClass.Economy, Source.MUC, Destination.DXB, "12.03.18", "13C", "20:15", "13C")));
        list.add(new Passenger("2", "namee", "content", "12.09.1996", "rei", "mos", "ci", "5646", "visa", CitizenshipCode.DEU, Gender.Male, baggages, new BoardingPass("1", Carrier.Emirates, "flight", "name", TicketClass.Economy, Source.MUC, Destination.DXB, "12.03.18", "13C", "20:15", "13C")));
        Flight flight = new Flight("1", Carrier.Emirates, list, new Baggage("1", "content", 12.0, BaggageType.Normal));
        Flight flight2 = new Flight("2", Carrier.Lufthansa, list, new Baggage("3", "electronics", 18.0, BaggageType.Bulky));

        Database.instance.innerSetLogEngine(log);
        Database.instance.startup(Configuration.instance.dataPath);
        FlightSQL flightSQL = new FlightSQL(Database.instance);
        BaggageSQL baggageSQL = new BaggageSQL(Database.instance);
        baggageSQL.dropTableBaggage(log);
        baggageSQL.createTableBaggage(log);
        baggageSQL.insert(new Baggage("1", "content", 12.0, BaggageType.Normal), log);
        baggageSQL.insert(new Baggage("3", "electronics", 18.0, BaggageType.Bulky), log);
        flightSQL.dropTableFlight(log);
        flightSQL.createTableFlight(log);
        flightSQL.insert(flight, log);
        flightSQL.insert(flight2,log);
        try {
            ArrayList<Flight> flights = new ArrayList<Flight>(flightSQL.buildSelectSQLStatement());
            System.out.println(flights.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.instance.shutdown();

    }
}