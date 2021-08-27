public class OPin extends Device {

    public void addInputPin(Device device) {
        iPins.add(device);
    }

    public boolean getOutput() {
        return iPins.get(0).getOutput();
    }
}
