public class GateOR extends Device {

    public void addInputPin(Device iPin1) {
        iPins.add(iPin1);
    }

    public boolean getOutput() {
        for (Device i : iPins){
            if (i.getOutput()){
                return true;
            }
        }
        return false;

        /*return iPins.get(0).getOutput() || iPins.get(1).getOutput();*/
    }
}