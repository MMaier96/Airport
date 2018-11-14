package base;import java.util.ArrayList;

public class Passenger {
    private String id;
    private String name;
    private String content;
    private String birthDate;
    private String street;
    private String postCode;
    private String city;
    private String picture;
    private String visa = "N/A";
    private CitizenshipCode citizenshipCode;
    private Gender gender;
    private ArrayList<Baggage> baggageList;
    private BoardingPass boardingPass;

    public Passenger(String id, String name, String content, String birthDate, String street, String postCode, String city, String picture, String visa, CitizenshipCode citizenshipCode, Gender gender, ArrayList<Baggage> baggageList, BoardingPass boardingPass) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.birthDate = birthDate;
        this.street = street;
        this.postCode = postCode;
        this.city = city;
        this.picture = picture;
        this.visa = visa;
        this.citizenshipCode = citizenshipCode;
        this.gender = gender;
        this.baggageList = baggageList;
        this.boardingPass = boardingPass;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CitizenshipCode getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(CitizenshipCode citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ArrayList<Baggage> getBaggageList() {
        return baggageList;
    }

    public void setBaggageList(ArrayList<Baggage> baggageList) {
        this.baggageList = baggageList;
    }

    public BoardingPass getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(BoardingPass boardingPass) {
        this.boardingPass = boardingPass;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", street='" + street + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", citizenshipCode=" + citizenshipCode +
                ", gender=" + gender +
                ", baggageList=" + baggageList +
                ", boardingPass=" + boardingPass +
                '}';
    }
}
