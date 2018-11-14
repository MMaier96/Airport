public class BoardingControlReceipt {
    private Object checkInDeskPort;
    private PassengerList boardedPassengerList;

    public BoardingControlReceipt(Object checkInDeskPort, PassengerList list) {
        this.checkInDeskPort = checkInDeskPort;
        this.boardedPassengerList = list;
    }

    public PassengerList getBoardedPassengerList() {
        return boardedPassengerList;
    }

    public void setBoardedPassengerList(PassengerList boardedPassengerList) {
        this.boardedPassengerList = boardedPassengerList;
    }

    public Object getCheckInDeskPort() {
        return checkInDeskPort;
    }

    public void setCheckInDesk(Object checkInDeskPort) {
        this.checkInDeskPort = checkInDeskPort;
    }
}
