public class GateNOT extends Device {
    private boolean inputPin;

    public void addInputPin(Device iPin) {
        iPins.add(iPin);
    }

    public boolean getOutput() {
        return !iPins.get(0).getOutput();
    }
}