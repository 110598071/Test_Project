public class GateAND extends Device {

    public void addInputPin(Device iPin1) {
        iPins.add(iPin1);
    }

    public boolean getOutput() {
        for (Device i : iPins){
            if (!i.getOutput()){
                return false;
            }
        }
        return true;


        /*int iPinSize = iPins.size();
        if(iPinSize == 2){return iPins.get(0).getOutput() && iPins.get(1).getOutput();}
        else{return iPins.get(0).getOutput() && iPins.get(1).getOutput() && iPins.get(2).getOutput();}*/
    }
}