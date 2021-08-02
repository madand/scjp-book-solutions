
package energy;

public class PowerPlant {
    Reactor core;

    public PowerPlant() {
        core = new Reactor();
    }

    public void soundEvacuateAlarm() {
        // ... impl unspecified
    }

    public int getOptimalThroughput() {
        // ... impl unspecified
        return 0;
    }

    public static void main(String[] args) {
        PowerPlant plant = new PowerPlant();
        Control ctrl = new Control(plant);
        ctrl.runSystem();
    }
}

class Reactor {
    public int getTroughput() {
        // ... impl unspecified
        return 0;
    }

    public boolean isCritical() {
        // ... impl unspecified
        return false;
    }

    void increaseTroughput() throws ReactorCritical {
        // ... impl unspecified
    }

    void decreaseTroughput() {
        // ... impl unspecified
    }
}

class ReactorCritical extends Exception { }

class Control {
    PowerPlant thePlant;

    final static int TOLERANCE = 10;

    public Control(PowerPlant thePlant) {
        this.thePlant = thePlant;
    }

    public void runSystem() {
        while (true) {
            try {
                if (needAdjustment(thePlant.getOptimalThroughput())) {
                    adjustTroughput(thePlant.getOptimalThroughput());
                }
            } catch (ReactorCritical e) {
                shutdown();
                break;
            }
        }
    }

    public boolean needAdjustment(int target) throws ReactorCritical {
        if (thePlant.core.isCritical()) {
            throw new ReactorCritical();
        }

        return Math.abs(target - thePlant.core.getTroughput()) > TOLERANCE;
    }

    public void adjustTroughput(int target) throws ReactorCritical {
        while (needAdjustment(target)) {
            if (target > thePlant.core.getTroughput()) {
                thePlant.core.increaseTroughput();
            } else {
                thePlant.core.decreaseTroughput();
            }
        }
    }

    public void shutdown() {
        thePlant.soundEvacuateAlarm();
        while (thePlant.core.getTroughput() > 0) {
                thePlant.core.decreaseTroughput();
        }
    }
}
