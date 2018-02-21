package event.boarding_control;

import event.boarding_control.base.PassengerList;

public class BoardingControlScanBoardingPass {
    private PassengerList passengers;

    public BoardingControlScanBoardingPass(PassengerList passengers) {
        this.passengers = passengers;
    }

    public PassengerList getPassengers() {
        return passengers;
    }
}