import org.junit.Assert;
import org.junit.Test;
import base.Airplane;

import static junit.framework.TestCase.assertNotNull;

public class ComponentInitializationTest{

    @Test
    public void componentInitializationTest() {
        Airport airport = new Airport();
        airport.build();

        assertNotNull(airport.getCheckInDeskPort());
        assertNotNull(airport.getBaggageSortingUnitPort());
        assertNotNull(airport.getSecurityCheckPort());
        assertNotNull(airport.getFederalPolicePort());
        assertNotNull(airport.getCustomsPort());
        assertNotNull(airport.getServiceVehicleOilPort());
        assertNotNull(airport.getServiceVehicleNitrogenOxygenPort());
        assertNotNull(airport.getServiceVehicleFreshWaterPort());
        assertNotNull(airport.getServiceVehicleWasteWaterTankPort());
        assertNotNull(airport.getAirCargoPalletLifterPort());
        assertNotNull(airport.getSkyTankingVehiclePort());
        assertNotNull(airport.getBoardingControlPort());
        assertNotNull(airport.getPushBackVehiclePort());
        assertNotNull(airport.getSpecialGoodRoboterPort());
        assertNotNull(airport.getBaggageVehiclePort());
        assertNotNull(airport.getContainerLifterPort());
        assertNotNull(airport.getScannerPort());
        assertNotNull(airport.getGroundOperationsPort());
    }
}
