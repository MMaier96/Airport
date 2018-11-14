import base.*;

import base.Baggage;
import base.BaggageSortingUnitReceipt;
import base.BoardingPass;
import base.Destination;
import base.Passenger;
import base.PassengerList;
import base.Passport;
import com.google.common.eventbus.Subscribe;
import event.Subscriber;
import event.pushback_vehicle.PushBackVehicleConnect;
import event.pushback_vehicle.PushBackVehicleDisconnect;
import event.pushback_vehicle.PushBackVehiclePushBack;
import event.service_vehicle_fresh_water.ServiceVehicleFreshWaterNotifyGroundOperations;
import event.baggage_sorting.BaggageSorting;
import event.boarding_control.BoardingControlCallPassengers;
import event.boarding_control.BoardingControlInspectPassports;
import event.boarding_control.BoardingControlNotifyGroundOperations;
import event.boarding_control.BoardingControlScanBoardingPass;
import event.security_check.SecurityCheck;
import event.service_vehicle_fresh_water.ServiceVehicleRefillFreshWater;
import event.service_vehicle_nitrogen_oxygen.ServiceVehicleNitrogenOxygenNotifyGroundOperations;
import event.service_vehicle_nitrogen_oxygen.ServiceVehicleRefillNitrogenBottle;
import event.service_vehicle_nitrogen_oxygen.ServiceVehicleRefillOxygenBottle;
import event.service_vehicle_oil.ServiceVehicleAPUOilTankIncreaseLevel;
import event.service_vehicle_oil.ServiceVehicleChangeFireExtinguisher;
import event.service_vehicle_oil.ServiceVehicleEngineOilTankIncreaseLevel;
import event.service_vehicle_oil.ServiceVehicleRefillDeIcingSystem;
import factory.*;
import event.service_vehicle_oil.*;
import event.service_vehicle_waste_water.ServiceVehiclePumpOut;
import event.service_vehicle_waste_water.ServiceVehicleWasteWaterNotifyGroundOperations;
import factory.ServiceVehicleFreshWaterFactory;
import factory.ServiceVehicleNitrogenOxygenFactory;
import factory.ServiceVehicleOilFactory;
import factory.ServiceVehicleWasteWaterTankFactory;
import logging.LogEngine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Airport extends Subscriber {

    private Object checkInDeskPort;
    private Object baggageSortingUnitPort;
    private Object securityCheckPort;
    private Object federalPolicePort;
    private Object customsPort;
    private Object serviceVehicleOilPort;
    private Object serviceVehicleNitrogenOxygenPort;
    private Object serviceVehicleFreshWaterPort;
    private Object serviceVehicleWasteWaterTankPort;
    private Object airCargoPalletLifterPort;
    private Object skyTankingVehiclePort;
    private Object boardingControlPort;
    private Object pushBackVehiclePort;
    private Object specialGoodRoboterPort;
    private Object baggageVehiclePort;
    private Object containerLifterPort;
    private Object scannerPort;
    private Object groundOperationsPort;


    // TODO: 01.02.2018  HIER ALLE FACTORYS EINFÜGEN VON JEDEM TEAM SELBST!!!
    public void build() {
        checkInDeskPort = CheckInDeskFactory.build();
        baggageSortingUnitPort = BaggageSortingUnitFactory.build();
        securityCheckPort = SecurityCheckFactory.build();
        scannerPort = ScannerFactory.build();
        federalPolicePort = FederalPoliceFactory.build();
        customsPort = CustomsFactory.build();
        serviceVehicleOilPort = ServiceVehicleOilFactory.build();
        serviceVehicleNitrogenOxygenPort = ServiceVehicleNitrogenOxygenFactory.build();
        serviceVehicleFreshWaterPort = ServiceVehicleFreshWaterFactory.build();
        serviceVehicleWasteWaterTankPort = ServiceVehicleWasteWaterTankFactory.build();
        airCargoPalletLifterPort = AirCargoPalletLifterFactory.build();
        skyTankingVehiclePort = SkyTankingVehicleFactory.build(); // TODO: 20.02.2018 java.lang.ClassNotFoundException: main.SkyTankingVehicle
//        boardingControlPort = BoardingControlFactory.build(); // TODO: 20.02.2018 Abhängigkeit zu CheckInDesk??
                                                              // TODO: 20.02.2018 Wird in BoardingControl benutzt, ist also Abhängigkeit
        // TODO: 21.02.2018 java.lang.NoSuchMethodException: SpecialGoodRoboter.getInstance()
//        at java.lang.Class.getMethod(Class.java:1786)
//        at factory.SpecialGoodRoboterFactory.build(SpecialGoodRoboterFactory.java:18)
//        at Airport.build(Airport.java:69)
//        at Application.main(Application.java:148)
        specialGoodRoboterPort = SpecialGoodRoboterFactory.build();

        baggageVehiclePort = BaggageVehicleFactory.build();
        containerLifterPort = ContainerLifterFactory.build();
//        pushBackVehiclePort = PushBackVehicleFactory.build(); //// TODO: 25.02.2018 für PushBackVehicle: ClassNotFoundException???
        groundOperationsPort = GroundOperationsCenterFactory.build();
    }

    @Subscribe
    public void receive(ServiceVehicleAPUOilTankIncreaseLevel event) {
        try {
            ArrayList<Object> aPUOilTankPorts = event.getAirplane().getBody().getaPUOilTanks();
            for (int aPUOilTankIndex = 0; aPUOilTankIndex < aPUOilTankPorts.size(); aPUOilTankIndex++) {
                Method increaseLevelOfAPUOilTank = serviceVehicleOilPort.getClass().getDeclaredMethod("increaseLevelOfAPUOilTank", Object.class);
                LogEngine.instance.write("increaseLevelOfAPUOilTank = " + increaseLevelOfAPUOilTank);

                int increasedAmount = (Integer) increaseLevelOfAPUOilTank.invoke(serviceVehicleOilPort, aPUOilTankPorts.get(aPUOilTankIndex));
                LogEngine.instance.write(event.getPhase() + " : serviceVehicleAPUOilTankIncreaseLevel = " + increasedAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(ServiceVehicleChangeFireExtinguisher event) {
        try {
            ArrayList<Object> fireExtinguisherPorts = event.getAirplane().getBody().getFireExtinguishers();
            for (int fireExtinguishersIndex = 0; fireExtinguishersIndex < fireExtinguisherPorts.size(); fireExtinguishersIndex++) {
                Method changeFireExtinguisher = serviceVehicleOilPort.getClass().getDeclaredMethod("change", Object.class);
                LogEngine.instance.write("changeFireExtinguisher = " + changeFireExtinguisher);

                boolean isChange = (Boolean) changeFireExtinguisher.invoke(serviceVehicleOilPort, fireExtinguisherPorts.get(fireExtinguishersIndex));
                LogEngine.instance.write(event.getPhase() + " : changeFireExtinguisher = " + isChange);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(ServiceVehicleRefillDeIcingSystem event) {
        try {
            ArrayList<Object> deIcingSystemPorts = event.getAirplane().getBody().getDeIcingSystems();
            for (int deIcingSystemsIndex = 0; deIcingSystemsIndex < deIcingSystemPorts.size(); deIcingSystemsIndex++) {
                Method refillDeIcingSystem = serviceVehicleOilPort.getClass().getDeclaredMethod("refill", Object.class);
                LogEngine.instance.write("refillDeIcingSystem = " + refillDeIcingSystem);

                boolean isChange = (Boolean) refillDeIcingSystem.invoke(serviceVehicleOilPort, deIcingSystemPorts.get(deIcingSystemsIndex));
                LogEngine.instance.write(event.getPhase() + " : refillDeIcingSystem = " + isChange);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(ServiceVehicleEngineOilTankIncreaseLevel event) {
        try {
            ArrayList<Object> engineOilTankPorts = event.getAirplane().getLeftWing().getEngineOilTanks();
            engineOilTankPorts.addAll(event.getAirplane().getRightWing().getEngineOilTanks());
            for (int engineOilTanksIndex = 0; engineOilTanksIndex < engineOilTankPorts.size(); engineOilTanksIndex++) {
                Method increaseLevelOfEngineOilTank = serviceVehicleOilPort.getClass().getDeclaredMethod("increaseLevelOfEngineOilTank", Object.class);
                LogEngine.instance.write("increaseLevelOfEngineOilTank = " + increaseLevelOfEngineOilTank);

                int increasedAmount = (Integer) increaseLevelOfEngineOilTank.invoke(serviceVehicleOilPort, engineOilTankPorts.get(engineOilTanksIndex));
                LogEngine.instance.write(event.getPhase() + " : serviceVehicleEngineOilTankIncreaseLevel = " + increasedAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(ServiceVehiclePumpOut event) {
        try {
            ArrayList<Object> wasteWaterTankPorts = event.getAirplane().getBody().getWasteWaterTanks();
            for (int wasteWaterTanksIndex = 0; wasteWaterTanksIndex < wasteWaterTankPorts.size(); wasteWaterTanksIndex++) {
                Method pumpOutWasteWaterTank = serviceVehicleWasteWaterTankPort.getClass().getDeclaredMethod("pumpOut", Object.class);
                LogEngine.instance.write("pumpOutWasteWaterTank = " + pumpOutWasteWaterTank);

                int amountWasteWater = (Integer) pumpOutWasteWaterTank.invoke(serviceVehicleWasteWaterTankPort, wasteWaterTankPorts.get(wasteWaterTanksIndex));
                LogEngine.instance.write(event.getPhase() + " : pumpOutWasteWaterTank = " + amountWasteWater);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(ServiceVehicleRefillFreshWater event) {
        try {
            ArrayList<Object> freshWaterTankPorts = event.getAirplane().getBody().getPotableWaterTanks();
            for (int freshWaterTanksIndex = 0; freshWaterTanksIndex < freshWaterTankPorts.size(); freshWaterTanksIndex++) {
                Method refillFreshWaterTank = serviceVehicleFreshWaterPort.getClass().getDeclaredMethod("refill", Object.class);
                LogEngine.instance.write("refillFreshWaterTank = " + refillFreshWaterTank);

                int amountFreshWater = (Integer) refillFreshWaterTank.invoke(serviceVehicleFreshWaterPort, freshWaterTankPorts.get(freshWaterTanksIndex));
                LogEngine.instance.write(event.getPhase() + " : refillFreshWaterTank = " + amountFreshWater);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(ServiceVehicleRefillNitrogenBottle event) {
        try {
            ArrayList<Object> nitrogenBottlePorts = event.getAirplane().getBody().getNitrogenBottles();
            for (int nitrogenBottleIndex = 0; nitrogenBottleIndex < nitrogenBottlePorts.size(); nitrogenBottleIndex++) {
                Method refillNitrogenBottle = serviceVehicleNitrogenOxygenPort.getClass().getDeclaredMethod("refillNitrogenBottle", Object.class);
                LogEngine.instance.write("refillNitrogenBottle = " + refillNitrogenBottle);

                int amountNitrogen = (Integer) refillNitrogenBottle.invoke(serviceVehicleNitrogenOxygenPort, nitrogenBottlePorts.get(nitrogenBottleIndex));
                LogEngine.instance.write(event.getPhase() + " : refillNitrogenBottle = " + amountNitrogen);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(ServiceVehicleRefillOxygenBottle event) {
        try {
            ArrayList<Object> oxygenBottlePorts = event.getAirplane().getBody().getOxygenBottles();
            for (int oxygenBottleIndex = 0; oxygenBottleIndex < oxygenBottlePorts.size(); oxygenBottleIndex++) {
                Method refillOxygenBottle = serviceVehicleNitrogenOxygenPort.getClass().getDeclaredMethod("refillOxygenBottle", Object.class);
                LogEngine.instance.write("refillOxygenBottle = " + refillOxygenBottle);

                int amountOxygen = (Integer) refillOxygenBottle.invoke(serviceVehicleNitrogenOxygenPort, oxygenBottlePorts.get(oxygenBottleIndex));
                LogEngine.instance.write(event.getPhase() + " : refillOxygenBottle = " + amountOxygen);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(ServiceVehicleOilNotifyGroundOperations event) {
        try {
            Method notifyGroundOperations = serviceVehicleOilPort.getClass().getDeclaredMethod("notifyGroundOperations", Object.class);
            notifyGroundOperations.invoke(serviceVehicleOilPort, event.getGroundOperationCenterPort());
            LogEngine.instance.write("ServiceVehicleOil: Notifying the groundoperations center...");

            LogEngine.instance.write("+");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(ServiceVehicleNitrogenOxygenNotifyGroundOperations event) {
        try {
            Method notifyGroundOperations = serviceVehicleNitrogenOxygenPort.getClass().getDeclaredMethod("notifyGroundOperations", Object.class);
            notifyGroundOperations.invoke(serviceVehicleNitrogenOxygenPort, event.getGroundOperationCenterPort());
            LogEngine.instance.write("ServiceVehicleNitrogenOxygen: Notifying the groundoperations center...");

            LogEngine.instance.write("+");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(ServiceVehicleWasteWaterNotifyGroundOperations event) {
        try {
            Method notifyGroundOperations = serviceVehicleWasteWaterTankPort.getClass().getDeclaredMethod("notifyGroundOperations", Object.class);
            notifyGroundOperations.invoke(serviceVehicleWasteWaterTankPort, event.getGroundOperationCenterPort());
            LogEngine.instance.write("ServiceVehicleWasteWater: Notifying the groundoperations center...");

            LogEngine.instance.write("+");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(ServiceVehicleFreshWaterNotifyGroundOperations event) {
        try {
            Method notifyGroundOperations = serviceVehicleFreshWaterPort.getClass().getDeclaredMethod("notifyGroundOperations", Object.class);
            notifyGroundOperations.invoke(serviceVehicleFreshWaterPort, event.getGroundOperationCenterPort());
            LogEngine.instance.write("ServiceVehicleFreshWater: Notifying the groundoperations center...");

            LogEngine.instance.write("+");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void receive(BoardingControlCallPassengers event) {
        try {
            // Load method 'call' from boardingControlPort with parameter of type PassengerList
            Method callPassengerMethod = boardingControlPort.getClass().getDeclaredMethod("call", PassengerList.class);
            LogEngine.instance.write("signature of BoardingControl.Port.call(PassengerList.class): " + callPassengerMethod.toGenericString());

            // Invoke method with passenger list from event
            callPassengerMethod.invoke(boardingControlPort, event.getPassengers());
            LogEngine.instance.write("--- Call all passengers to gate to start boarding");
            int passengerId = 1;
            for (Passenger passenger : event.getPassengers().getPassengerList()) { // TODO: 21.02.2018 war die Änderung hier richtig von mir?
                LogEngine.instance.write(String.format("%03d: %s", passengerId, passenger.toString()));
                passengerId++;
            }
            LogEngine.instance.write("--- Calling passengers completed");
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exc) {
            exc.printStackTrace();
        }
    }

    @Subscribe
    public void receive(BoardingControlInspectPassports event) {
        try {
            // Load method 'inspect' with the Passport parameter from BoardingControl class
            Method inspectPassportMethod = boardingControlPort.getClass().getMethod("inspect", Passport.class);
            LogEngine.instance.write("signature of BoardingControl.Port.inspect(Passport.class): " + inspectPassportMethod.toGenericString());

            // Inspect the passport for each passenger before boarding
            LogEngine.instance.write("--- Inspect the passports of all passengers");
            for (Passenger passenger : event.getPassengers().getPassengerList()) {
                boolean validPassport = (boolean) inspectPassportMethod.invoke(boardingControlPort, passenger.getPassport());
                LogEngine.instance.write(validPassport ? "Passenger " + passenger.getName() + " has a valid passport." :
                        "Security Alert: Passenger " + passenger.getName() + " has a counterfeit passport!");
            }
            LogEngine.instance.write("--- Finished inspection: All passports are valid!");
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exc) {
            exc.printStackTrace();
        }
    }

    @Subscribe
    public void receive(BoardingControlScanBoardingPass event) {
        try {
            // Load method 'scan' from BoardingControl to scan all boarding passes
            Method scanMethod = boardingControlPort.getClass().getDeclaredMethod("scan", BoardingPass.class);
            LogEngine.instance.write("signature of BoardingControl.Port.scan(BoardingPass.class): " + scanMethod.toGenericString());

            // Scan the boarding pass for each passenger and log the progress
            LogEngine.instance.write("--- Scanning all boarding passes of passengers");
            for (Passenger passenger : event.getPassengers().getPassengerList()) {
                boolean boardingPassScanned = (boolean) scanMethod.invoke(boardingControlPort, passenger.getBoardingPass());
                LogEngine.instance.write(boardingPassScanned ? "Passenger " + passenger.getName() + " is registered to flight " +
                        passenger.getBoardingPass().getFlight() + " from " + passenger.getBoardingPass().getSource() + " to " +
                        passenger.getBoardingPass().getDestination() + "!" : "Passenger " + passenger.getName() +
                        " is not registered on this flight!");
            }
            LogEngine.instance.write("--- Scanning boarding passes completed: All passengers are validly registered on this flight!");
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exc) {
            exc.printStackTrace();
        }
    }

    @Subscribe
    public void receive(BoardingControlNotifyGroundOperations event) {
        try {
            // Load method 'notifyGroundOperations' from BoardingControl to invoke receive method of GroundOperationsCenter
            Method notifyGroundOperationsMethod = boardingControlPort.getClass().getDeclaredMethod("notifyGroundOperations", Object.class);
            LogEngine.instance.write("signature of BoardingControl.Port.notifyGroundOperations(Object.class): " + notifyGroundOperationsMethod.toGenericString());

            // Invoke notification of ground operations by applying the ground operation's port to method
            notifyGroundOperationsMethod.invoke(boardingControlPort, event.getGroundOperationsPort());
            LogEngine.instance.write("--- Notifying ground operations that boarding is completed and passengers are" +
                    "on their way to the airplane");
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exc) {
            exc.printStackTrace();
        }
    }

    @Subscribe
    public void receive(SecurityCheck event) {
        try {
            // TODO: LogEngine is commented out, because it throws exceptions during a test.
            //LogEngine.instance.write("--- Starting security check");
            Method scanMethod = this.securityCheckPort.getClass().getDeclaredMethod("scan", Baggage.class, Object.class, String.class);

            //LogEngine.instance.write("--- Security check: scan baggage");
            for (Baggage bag : event.getBaggage()) {
               if((Boolean) scanMethod.invoke(this.securityCheckPort, bag, this.scannerPort, "glock7")){
                   //LogEngine.instance.write("--- Security check: Pattern glock7 found in Bag: " + bag.getId());
               }
            }

            scanMethod = this.securityCheckPort.getClass().getDeclaredMethod("scan", Passenger.class, Object.class, String.class);
            //LogEngine.instance.write("--- Security check: scan passengers");
            for (base.Passenger passenger : event.getPassengers()) {
                if((Boolean) scanMethod.invoke(this.securityCheckPort, passenger, this.scannerPort, "glock7")){
                    //LogEngine.instance.write("--- Security check: Pattern glock7 found for Passenger: " + passenger.getName());
                }
            }

            Method getReceipt = this.securityCheckPort.getClass().getDeclaredMethod("getSecurityCheckReceipt");
            //LogEngine.instance.write("--- Security check: notify ground operations");
            Object receipt = getReceipt.invoke(this.securityCheckPort);

            //Method notifyGroundOperationsMethod = this.securityCheckPort.getClass().getDeclaredMethod("notifyGroundOperations", Object.class);
            //notifyGroundOperationsMethod.invoke(this.securityCheckPort, result);

            // TODO: 25.02.2018 doesn't find method??
//            Method notifyGroundOperationMethod = this.groundOperationsPort.getClass().getDeclaredMethod("receive", BaggageSortingUnitReceipt.class);
//            notifyGroundOperationMethod.invoke(this.groundOperationsPort, receipt);


        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exc) {
            exc.printStackTrace();
        }
    }

    @Subscribe
    public void receive(BaggageSorting event) {
        try {
            // TODO: LogEngine is commented out, because it throws exceptions during a test.
//            LogEngine.instance.write("--- Baggage Sorting");
            Method executeBaggageSortingMethod = this.baggageSortingUnitPort.getClass().getDeclaredMethod("execute",
                    String.class , Destination.class, java.util.List.class, java.util.List.class, java.util.List.class);

            Object result = executeBaggageSortingMethod.invoke(this.baggageSortingUnitPort, event.getBaggageVehicleTargetPosition()
            , event.getDestination(), event.getBaggage(), event.getBaggageVehicles(), event.getBaggageTags());

            BaggageSortingUnitReceipt receipt = (BaggageSortingUnitReceipt) result;

//            LogEngine.instance.write("--- Baggage Sorting: Notify Ground Operations");
// TODO: 23.02.2018   doesn't find method??           Method notifyGroundOperationMethod = this.groundOperationsPort.getClass().getDeclaredMethod("receive", BaggageSortingUnitReceipt.class);
//            notifyGroundOperationMethod.invoke(this.groundOperationsPort, receipt);


        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exc) {
            exc.printStackTrace();
        }
    }
    
    @Subscribe
    public void receive(PushBackVehicleConnect event) {
        try {
            LogEngine.instance.write("--- PushBackVehicle gets connected");

            Method lightsOnMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("setFlashingLightOn", Boolean.TYPE);
            lightsOnMethod.invoke(this.pushBackVehiclePort, true);
            LogEngine.instance.write("Flashing Light is on");

            Method connectMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("connect", Airplane.class);
            connectMethod.invoke(this.pushBackVehiclePort, event.getAirplane());
            LogEngine.instance.write("PushBack Vehicle is connected to " + event.getAirplane());

            Method steeringPinMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("setSteeringPin", Boolean.TYPE);
            steeringPinMethod.invoke(this.pushBackVehiclePort, true);
            LogEngine.instance.write("Steering Pin is set now");

            LogEngine.instance.write("--- PushBack Vehicle is connected");

        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exc) {
            exc.printStackTrace();
        }
    }

    @Subscribe
    public void receive(PushBackVehiclePushBack event) {
        try {
            LogEngine.instance.write("--- Airplane is getting pushed back");

            Method upMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("up", Boolean.TYPE);
            upMethod.invoke(this.pushBackVehiclePort, true);
            LogEngine.instance.write("PushBack Vehicle is up now");

            Method forwardMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("forward", Integer.TYPE);
            int forward = (int) forwardMethod.invoke(this.pushBackVehiclePort, 10);
            LogEngine.instance.write("PushBack Vehicle moves forwards with " + forward + "mph");

            Method leftMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("turnLeft", Integer.TYPE);
            int left = (int) leftMethod.invoke(this.pushBackVehiclePort, 5);
            LogEngine.instance.write("PushBack Vehicle moves left with " + left + "mph");

            Method rightMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("turnRight", Integer.TYPE);
            int right = (int) rightMethod.invoke(this.pushBackVehiclePort, 10);
            LogEngine.instance.write("PushBack Vehicle moves right with " + right + "mph");

            Method backwardMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("backward", Integer.TYPE);
            int backward = (int) backwardMethod.invoke(this.pushBackVehiclePort, 10);
            LogEngine.instance.write("PushBack Vehicle moves backwards with " + backward + "mph");

            Method stopMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("stop", Integer.TYPE);
            stopMethod.invoke(this.pushBackVehiclePort, 0);
            LogEngine.instance.write("PushBack Vehicle has stopped now");

            Method downMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("down", Boolean.TYPE);
            downMethod.invoke(this.pushBackVehiclePort, false);
            LogEngine.instance.write("PushBack Vehicle is down now");

            LogEngine.instance.write("--- Airplane is pushed back");

        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exc) {
            exc.printStackTrace();
        }
    }

    @Subscribe
    public void receive(PushBackVehicleDisconnect event) {
        try {
            LogEngine.instance.write("--- PushBack Vehicle gets disconnected");

            Method steeringPinMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("removeSteeringPin", Boolean.TYPE);
            steeringPinMethod.invoke(this.pushBackVehiclePort, false);
            LogEngine.instance.write("Steering Pin is removec now");

            Method disconnectMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("disconnect", Airplane.class);
            disconnectMethod.invoke(this.pushBackVehiclePort, event.getAirplane());
            LogEngine.instance.write("PushBack Vehicle is disconnected from " + event.getAirplane());

            Method lightsOffMethod = this.pushBackVehiclePort.getClass().getDeclaredMethod("setFlashingLightOff", Boolean.TYPE);
            lightsOffMethod.invoke(this.pushBackVehiclePort, true);
            LogEngine.instance.write("Flashing Light is off");

            LogEngine.instance.write("--- PushBack Vehicle is disconnected");

        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exc) {
            exc.printStackTrace();
        }
    }

    public Object getCheckInDeskPort() {
        return checkInDeskPort;
    }

    public void setCheckInDeskPort(Object checkInDeskPort) {
        this.checkInDeskPort = checkInDeskPort;
    }

    public Object getBaggageSortingUnitPort() {
        return baggageSortingUnitPort;
    }

    public void setBaggageSortingUnitPort(Object baggageSortingUnitPort) {
        this.baggageSortingUnitPort = baggageSortingUnitPort;
    }

    public Object getSecurityCheckPort() {
        return securityCheckPort;
    }

    public void setSecurityCheckPort(Object securityCheckPort) {
        this.securityCheckPort = securityCheckPort;
    }

    public Object getFederalPolicePort() {
        return federalPolicePort;
    }

    public void setFederalPolicePort(Object federalPolicePort) {
        this.federalPolicePort = federalPolicePort;
    }

    public Object getCustomsPort() {
        return customsPort;
    }

    public void setCustomsPort(Object customsPort) {
        this.customsPort = customsPort;
    }

    public Object getServiceVehicleOilPort() {
        return serviceVehicleOilPort;
    }

    public void setServiceVehicleOilPort(Object serviceVehicleOilPort) {
        this.serviceVehicleOilPort = serviceVehicleOilPort;
    }

    public Object getServiceVehicleNitrogenOxygenPort() {
        return serviceVehicleNitrogenOxygenPort;
    }

    public void setServiceVehicleNitrogenOxygenPort(Object serviceVehicleNitrogenOxygenPort) {
        this.serviceVehicleNitrogenOxygenPort = serviceVehicleNitrogenOxygenPort;
    }

    public Object getServiceVehicleFreshWaterPort() {
        return serviceVehicleFreshWaterPort;
    }

    public void setServiceVehicleFreshWaterPort(Object serviceVehicleFreshWaterPort) {
        this.serviceVehicleFreshWaterPort = serviceVehicleFreshWaterPort;
    }

    public Object getServiceVehicleWasteWaterTankPort() {
        return serviceVehicleWasteWaterTankPort;
    }

    public void setServiceVehicleWasteWaterTankPort(Object serviceVehicleWasteWaterTankPort) {
        this.serviceVehicleWasteWaterTankPort = serviceVehicleWasteWaterTankPort;
    }

    public Object getAirCargoPalletLifterPort() {
        return airCargoPalletLifterPort;
    }

    public void setAirCargoPalletLifterPort(Object airCargoPalletLifterPort) {
        this.airCargoPalletLifterPort = airCargoPalletLifterPort;
    }

    public Object getSkyTankingVehiclePort() {
        return skyTankingVehiclePort;
    }

    public void setSkyTankingVehiclePort(Object skyTankingVehiclePort) {
        this.skyTankingVehiclePort = skyTankingVehiclePort;
    }

    public Object getBoardingControlPort() {
        return boardingControlPort;
    }

    public void setBoardingControlPort(Object boardingControlPort) {
        this.boardingControlPort = boardingControlPort;
    }

    public Object getPushBackVehiclePort() {
        return pushBackVehiclePort;
    }

    public void setPushBackVehiclePort(Object pushBackVehiclePort) {
        this.pushBackVehiclePort = pushBackVehiclePort;
    }

    public Object getSpecialGoodRoboterPort() {
        return specialGoodRoboterPort;
    }

    public void setSpecialGoodRoboterPort(Object specialGoodRoboterPort) {
        this.specialGoodRoboterPort = specialGoodRoboterPort;
    }

    public Object getBaggageVehiclePort() {
        return baggageVehiclePort;
    }

    public void setBaggageVehiclePort(Object baggageVehiclePort) {
        this.baggageVehiclePort = baggageVehiclePort;
    }

    public Object getContainerLifterPort() {
        return containerLifterPort;
    }

    public void setContainerLifterPort(Object containerLifterPort) {
        this.containerLifterPort = containerLifterPort;
    }

    public Object getScannerPort() {
        return scannerPort;
    }

    public void setScannerPort(Object scannerPort) {
        this.scannerPort = scannerPort;
    }

    public Object getGroundOperationsPort() {
        return groundOperationsPort;
    }

    public void setGroundOperationsPort(Object groundOperationsPort) {
        this.groundOperationsPort = groundOperationsPort;
    }
}
