import base.Baggage;
import base.CottonPad;
import base.Employee;
import base.Passenger;

import java.util.ArrayList;

public class SecurityCheck {
    /**
     * The SecurityCheck instance.
     */
    private static SecurityCheck instance = new SecurityCheck();
    /**
     * The port instance.
     */
    public Port port;
    /**
     * The Security Check component.
     */
    public SecurityCheck() {
        //this.innerId = id;
        //this.innerEmployee = employee;
        //this.innerFederalPolice = federalPolice;
        this.securityCheckReceipt = new SecurityCheckReceipt(this.innerEmployee, new ArrayList<CottonPad>(), 0, 0, 0, 0);
        this.port = new Port();
    }

    /**
     * Returns the instance.
     * @return The instance of the component.
     */
    public static SecurityCheck getInstance() {
        return instance;
    }
    /**
     * Returns the version string.
     * @return The version string.
     */
    public String innerGetVersion() {
        return "SecurityCheck - Version 1.0";
    }
    /**
     * The port for the hash component.
     */
    public class Port implements ISecurityCheck {
        /**
         * Gets the version of the component.
         * @return The version string.
         */
        public String getVersion() {
            return innerGetVersion();
        }

        /**
         * Get the id.
         * @return The id.
         */
        public int getId() {
            return innerId;
        }

        /**
         * Get the Employee.
         * @return The Employee.
         */
        public Employee getEmployee() {
            return innerEmployee;
        }

        /**
         * Get the Federal Police.
         * @return The Federal Police.
         */
        public FederalPolice getFederalPolice() {
            return innerFederalPolice;
        }

        /**
         * Get the Scan Pattern.
         * @return The Scan Pattern.
         */
        public String getScanPattern() {
            return innerScanPattern;
        }
        /**
         * Scans the passenger.
         * @param passenger The passenger.
         * @param scanner The scanner.
         * @param pattern The scanning pattern.
         * @return True if found.
         */
        public boolean scan(Passenger passenger, Scanner scanner, String pattern) {
            return innerScan(passenger, scanner, pattern);
        }
        /**
         * Scans the passenger.
         * @param passenger The passenger.
         * @param explosivesTraceDetection The explosive trace detection.
         * @return True if found.
         */
        public boolean scan(Passenger passenger, ExplosivesTraceDetection explosivesTraceDetection) {
            return innerScan(passenger, explosivesTraceDetection);
        }
        /**
         * Scans the baggage.
         * @param baggage The baggage.
         * @param scanner The scanner.
         * @param pattern The scanning pattern.
         * @return True if found.
         */
        public boolean scan(Baggage baggage, Scanner scanner, String pattern) {
            return innerScan(baggage, scanner, pattern);
        }
        /**
         * Notifies the ground operation
         * @param securityCheckReceipt The security check receipt.
         */
        public void notifyGroundOperations(SecurityCheckReceipt securityCheckReceipt) {
            innerNotifyGroundOperations(securityCheckReceipt);
        }
    }
    /**
     * The id.
     */
    private int innerId;
    /**
     * The employee.
     */
    private Employee innerEmployee;
    /**
     * The Federal Police.
     */
    private FederalPolice innerFederalPolice;
    /**
     * The scan pattern;
     */
    private String innerScanPattern = "glock7";
    /**
     * The Security Check Receipt
     */
    private SecurityCheckReceipt securityCheckReceipt;
    /**
     * Scans the passenger.
     * @param passenger The passenger.
     * @param scanner The scanner.
     * @param pattern The scanning pattern.
     * @return True if found.
     */
    private boolean innerScan(Passenger passenger, Scanner scanner, String pattern) {
        boolean passengerScanned = scanner.scan(passenger, pattern);
        this.securityCheckReceipt.incrementNumberOfPassengerScannedByOne();
        for(Baggage baggage : passenger.getBaggageList()) {
            if(scanner.scan(baggage, pattern)) {
                this.securityCheckReceipt.incrementNumberOfDangerousBaggageByOne();
            }
            this.securityCheckReceipt.incrementNumberOfBaggageScannedByOne();
        }
        return passengerScanned;
    }
    /**
     * Scans the passenger.
     * @param passenger The passenger.
     * @param explosivesTraceDetection The explosive trace detection.
     * @return True if found.
     */
    private boolean innerScan(Passenger passenger, ExplosivesTraceDetection explosivesTraceDetection) {
        this.securityCheckReceipt.incrementNumberOfPassengerScannedExplosivesTraceDetectorByOne();
        /**
         * Is not implemented and will always return false
         */
        return false;
    }
    /**
     * Scans the baggage.
     * @param baggage The baggage.
     * @param scanner The scanner.
     * @param pattern The scanning pattern.
     * @return True if found.
     */
    public boolean innerScan(Baggage baggage, Scanner scanner, String pattern) {
        if(scanner.scan(baggage, pattern)){
            this.securityCheckReceipt.incrementNumberOfDangerousBaggageByOne();
            this.securityCheckReceipt.incrementNumberOfBaggageScannedByOne();
            return true;
        }
        this.securityCheckReceipt.incrementNumberOfBaggageScannedByOne();
        return false;
    }
    /**
     * Notifies the ground operation
     * @param securityCheckReceipt The security check receipt.
     */
    public void innerNotifyGroundOperations(SecurityCheckReceipt securityCheckReceipt) {
        //TODO: Notify Ground Operations somehow
    }
}