package sql;

import base.*;
import engine.LogEngine;
import main.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassengerSQL {

    private Database instance;
    public PassengerSQL(Database instance) {
        this.instance = instance;
    }

    public void dropTableStorage(LogEngine logEngine) {
        String sqlStatement = "DROP TABLE passenger IF EXISTS";
        logEngine.write("main.Database", "dropTablePassenger", "-", sqlStatement);
        instance.update(sqlStatement);
    }

    public void createTablePassenger(LogEngine logEngine) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE passenger").append(" ( ");
        sqlStringBuilder.append("id VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("name TEXT NOT NULL").append(",");
        sqlStringBuilder.append("content TEXT NOT NULL").append(",");
        sqlStringBuilder.append("birthdate TEXT NOT NULL").append(",");
        sqlStringBuilder.append("street TEXT NOT NULL").append(",");
        sqlStringBuilder.append("postcode TEXT NOT NULL").append(",");
        sqlStringBuilder.append("city TEXT NOT NULL").append(",");
        sqlStringBuilder.append("picture TEXT NOT NULL").append(",");
        sqlStringBuilder.append("visa TEXT NOT NULL").append(",");
        sqlStringBuilder.append("citizenship TEXT NOT NULL").append(",");
        sqlStringBuilder.append("gender TEXT NOT NULL").append(",");
        sqlStringBuilder.append("baggage TEXT NOT NULL").append(",");
        sqlStringBuilder.append("boardingpassid VARCHAR(36) NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(")");
        logEngine.write("main.Database", "createTablePassenger", "-", sqlStringBuilder.toString());
        instance.update(sqlStringBuilder.toString());
    }

    public String buildInsertSQLStatement(Passenger passenger) {

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("INSERT INTO storage (id,name,content,birthdate,street,postcode,city,picture,visa,citizenship,gender,baggage,boardingpassid) VALUES (");
        sqlStringBuilder.append("'").append(passenger.getId()).append("'").append(",");
        sqlStringBuilder.append("'").append(passenger.getName()).append("'").append(",");
        sqlStringBuilder.append("'").append(passenger.getContent()).append("'").append(",");
        sqlStringBuilder.append("'").append(passenger.getBirthDate()).append("'").append(",");
        sqlStringBuilder.append("'").append(passenger.getStreet()).append("'").append(",");
        sqlStringBuilder.append("'").append(passenger.getPostCode()).append("'").append(",");
        sqlStringBuilder.append("'").append(passenger.getCity()).append("'").append(",");
        sqlStringBuilder.append("'").append(passenger.getPicture()).append("'").append(",");
        sqlStringBuilder.append("'").append(passenger.getVisa()).append("'").append(",");
        sqlStringBuilder.append("'").append(passenger.getCitizenshipCode().toString()).append("'").append(",");
        sqlStringBuilder.append("'").append(passenger.getGender().toString()).append("'").append(",");
        sqlStringBuilder.append("'").append(getStringfromListWithSemicolon(passenger)).append("'").append(",");
        sqlStringBuilder.append("'").append(passenger.getBoardingPass().getId()).append("'");
        sqlStringBuilder.append(")");
        return sqlStringBuilder.toString();
    }

    public void insert(Passenger passenger, LogEngine logEngine) {
        System.out.println(passenger.hashCode());
        logEngine.write("main.Database", "insert", "Passenger = " + passenger.getId(), buildInsertSQLStatement(passenger));
        instance.update(buildInsertSQLStatement(passenger));
    }

    public String buildUpdateSQLStatement(Passenger passenger) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("UPDATE storage SET name = '").append(passenger.getName()).append("'").append(",");
        sqlStringBuilder.append("content = '").append(passenger.getContent()).append("'").append(",");
        sqlStringBuilder.append("birthdate = '").append(passenger.getBirthDate()).append("'").append(",");
        sqlStringBuilder.append("street = '").append(passenger.getStreet()).append("'").append(",");
        sqlStringBuilder.append("postcode = '").append(passenger.getPostCode()).append("'").append(",");
        sqlStringBuilder.append("city = '").append(passenger.getCity()).append("'").append(",");
        sqlStringBuilder.append("picture = '").append(passenger.getPicture()).append("'").append(",");
        sqlStringBuilder.append("picture = '").append(passenger.getVisa()).append("'").append(",");
        sqlStringBuilder.append("citizenship = '").append(passenger.getCitizenshipCode().toString()).append("'").append(",");
        sqlStringBuilder.append("gender = '").append(passenger.getGender().toString()).append("'").append(",");
        sqlStringBuilder.append("baggage = '").append(getStringfromListWithSemicolon(passenger)).append("'").append(",");
        sqlStringBuilder.append("boardingpassid = '").append(passenger.getBoardingPass().getId()).append("'").append(",");
        sqlStringBuilder.append("WHERE id = '").append(passenger.getId()).append("'");
        return sqlStringBuilder.toString();
    }


    private String getStringfromListWithSemicolon(Passenger passenger) {
        boolean processedFirst = false;
        StringBuilder listbuilder = new StringBuilder();
        for (Baggage record : passenger.getBaggageList()) {
            if (processedFirst)
                listbuilder.append(";");
            listbuilder.append(BaggageSQL.getCSVFromObject(record));
            processedFirst = true;
        }
        return listbuilder.toString();
    }

    private String getStringfromListWithColon(Passenger passenger) {
        boolean processedFirst = false;
        StringBuilder listbuilder = new StringBuilder();
        for (Baggage record : passenger.getBaggageList()) {
            if (processedFirst)
                listbuilder.append(":");
            listbuilder.append(BaggageSQL.getCSVFromObject(record));
            processedFirst = true;
        }
        return listbuilder.toString();
    }




    public String getCSVfromObject(Passenger passenger) {

        StringBuilder sb = new StringBuilder();
        BoardingPass boardingPass = passenger.getBoardingPass();

        sb.append(passenger.getId()).append(",");
        sb.append(passenger.getName()).append(",");
        sb.append(passenger.getContent()).append(",");
        sb.append(passenger.getBirthDate()).append(",");
        sb.append(passenger.getStreet()).append(",");
        sb.append(passenger.getPostCode()).append(",");
        sb.append(passenger.getCity()).append(",");
        sb.append(passenger.getPicture()).append(",");
        sb.append(passenger.getVisa()).append(",");
        sb.append(passenger.getCitizenshipCode().toString()).append(",");
        sb.append(passenger.getGender().toString()).append(",");
        sb.append(boardingPass.getId()).append(",");
        sb.append(boardingPass.getCarrier().toString()).append(",");
        sb.append(boardingPass.getFlight()).append(",");
        sb.append(boardingPass.getPassengerName()).append(",");
        sb.append(boardingPass.getTicketClass().toString()).append(",");
        sb.append(boardingPass.getSource().toString()).append(",");
        sb.append(boardingPass.getDestination().toString()).append(",");
        sb.append(boardingPass.getDate()).append(",");
        sb.append(boardingPass.getGate()).append(",");
        sb.append(boardingPass.getBoardingTime()).append(",");
        sb.append(boardingPass.getSeat()).append(",");
        sb.append("<").append(getStringfromListWithColon(passenger)).append(">");

        return sb.toString();
    }

    public static ArrayList<Passenger> getObjectfromCSV(String list) {
        ArrayList<Passenger> result = new ArrayList<Passenger>();
        ArrayList<String> passengerlist = new ArrayList<String>(Arrays.asList(list.split(";")));
        for (String passengers: passengerlist){
            ArrayList<Baggage> baggageArrayList = new ArrayList<Baggage>();
            String[] listOfPassengers = passengers.split("<.*?>");
            String listofbaggages = passengers.substring(listOfPassengers[0].length()+1,passengers.length()-1);
            String[] passenger = listOfPassengers[0].split(",");
            String[] baggageseperatedColon = listofbaggages.split(":");
            for (int i = 0 ; i<baggageseperatedColon.length; i++){
                String[]  baggageSeperatedComma = baggageseperatedColon[i].split(",");
                baggageArrayList.add(new Baggage(baggageSeperatedComma[0],baggageSeperatedComma[1],Double.parseDouble(baggageSeperatedComma[2]),BaggageType.valueOf(baggageSeperatedComma[3])));
            }
            result.add(new Passenger(passenger[0],passenger[1],passenger[2],passenger[3],passenger[4],passenger[5],passenger[6],passenger[7],passenger[8],CitizenshipCode.valueOf(passenger[9]),Gender.valueOf(passenger[10]),baggageArrayList,new BoardingPass(passenger[11],Carrier.valueOf(passenger[12]),passenger[13],passenger[14],TicketClass.valueOf(passenger[15]),Source.valueOf(passenger[16]),Destination.valueOf(passenger[17]),passenger[18],passenger[19],passenger[20], passenger[21])));
        }

        return result;
    }

    public ArrayList<Passenger> buildSelectSQLStatement() throws SQLException {

        ArrayList<Passenger> allbagages = new ArrayList<Passenger>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM passenger INNER JOIN boardingpass ON passenger.boardingpassid=boardingpass.id");
        Statement statement = instance.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sb.toString());
        while (rs.next()) {
            String uuid = rs.getString("passenger.id");
            String carrier = rs.getString("carrier");
            String flight = rs.getString("flight");
            String passengername = rs.getString("passengername");
            String ticketclass = rs.getString("ticketclass");
            String source = rs.getString("source");
            String destination = rs.getString("destination");
            String date = rs.getString("date");
            String gate = rs.getString("gate");
            String boardingtime = rs.getString("boardingtime");
            String seat = rs.getString("seat");
            String boardingpassid = rs.getString("boardingpass.id");
            String name = rs.getString("name");
            String content = rs.getString("content");
            String birthdate = rs.getString("birthdate");
            String street = rs.getString("street");
            String postcode = rs.getString("postcode");
            String city = rs.getString("city");
            String citizenship = rs.getString("citizenship");
            String gender = rs.getString("gender");
            String baggage = rs.getString("baggage");
            String picture = rs.getString("picture");
            String visa = rs.getString("visa");

            Passenger passenger = new Passenger(uuid, name, content, birthdate, street, postcode, city, picture, visa, CitizenshipCode.valueOf(citizenship), Gender.valueOf(gender), BaggageSQL.getObjectfromCSV(baggage), new BoardingPass(boardingpassid, Carrier.valueOf(carrier), flight, passengername, TicketClass.valueOf(ticketclass), Source.valueOf(source), Destination.valueOf(destination), date, gate, boardingtime, seat));
            allbagages.add(passenger);

        }
        statement.close();

        return allbagages;
    }

}
