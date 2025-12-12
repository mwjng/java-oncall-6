package oncall;

import oncall.controller.EmergencyWorkAssignment;

public class Application {
    public static void main(String[] args) {
        EmergencyWorkAssignment emergencyWorkAssignment = new EmergencyWorkAssignment();
        emergencyWorkAssignment.run();
    }
}
