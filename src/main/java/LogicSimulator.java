import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class LogicSimulator {
    private Vector<Device> circuits;
    private Vector<Device> iPins;
    private Vector<Device> oPins;

    public LogicSimulator() {
        circuits = new Vector<>();
        iPins = new Vector<>();
        oPins = new Vector<>();
    }

    public boolean load(String filePath) {
        circuits.clear();
        iPins.clear();
        oPins.clear();

        // load file
        List<String> lines;
        try {
            Path path = Paths.get(filePath);
            lines = Files.readAllLines(path);
        }
        catch (IOException e) {
            return false;
        }

        try {
            //set iPins
            for (int i = 0; i < Integer.parseInt(lines.get(0)); i++){
                iPins.add(new IPin());
            }

            //add gates
            int gatesCount = Integer.parseInt(lines.get(1));
            Vector<String> lineInputPin = new Vector<>();
            for (int j = 0; j < gatesCount; j++){
                String[] lineSplit = lines.get(j+2).split(" ",2);
                lineInputPin.add(lineSplit[1]);
                switch (Integer.parseInt(lineSplit[0])) {
                    case 1:
                        circuits.add(new GateAND()); break;
                    case 2:
                        circuits.add(new GateOR()); break;
                    case 3:
                        circuits.add(new GateNOT()); break;
                }
            }

            //add iPins
            Boolean[] usedGate = new Boolean[gatesCount];
            Arrays.fill(usedGate,Boolean.TRUE);

            for (int k = 0; k < gatesCount; k++){
                String[] inputSplit = lineInputPin.get(k).split(" ");
                for (String pins : inputSplit) {
                    float pinParseFloat = Float.parseFloat(pins);
                    int intPins = Math.round(pinParseFloat);
                    if (intPins < 0) {
                        circuits.get(k).addInputPin(iPins.get(-intPins-1));
                    }
                    else if (intPins > 0) {
                        circuits.get(k).addInputPin(circuits.get(intPins-1));
                        usedGate[intPins-1] = false;
                    }
                }
            }

            //add oPins
            for (int p = 0; p < gatesCount; p++) {
                if (usedGate[p]) {
                    OPin opin = new OPin();
                    opin.addInputPin(circuits.get(p));
                    oPins.add(opin);
                }
            }

        }catch (NumberFormatException e){return false;}
        return  true;
    }

    public int getInputPinsSize() {
        return iPins.size();
    }

    public int getOutputPinsSize() {
        return  oPins.size();
    }

    public int getCircuitsSize() {
        return  circuits.size();
    }

    public String getSimulationResult(Vector<Boolean> inputValues) {
        String simulateResult;
        if (oPins.size() == 1){
            simulateResult = "Simulation Result:\n" + "i i i | o\n" + "1 2 3 | 1\n" + "------+--\n";
        }
        else{
            simulateResult = "Simulation Result:\n" + "i i i | o o\n" + "1 2 3 | 1 2\n" + "------+----\n";
        }

        for (int i = 0; i < iPins.size(); i++){
            iPins.get(i).setInput(inputValues.get(i));
            if (inputValues.get(i)) {simulateResult += "1 ";}
            else {simulateResult += "0 ";}
        }
        simulateResult += "| ";

        for (int j = 0; j < oPins.size(); j++){
            if (j == 1){simulateResult += " ";}

            if (oPins.get(j).getOutput()) {
                simulateResult += "1";
            }
            else {
                simulateResult += "0";
            }
        }
        simulateResult += "\n";

        return simulateResult;
    }

    public String getTruthTable() {
        String simulateResult;
        if (oPins.size() == 1){
            simulateResult = "Truth table:\n" + "i i i | o\n" + "1 2 3 | 1\n" + "------+--\n";
        }
        else{
            simulateResult = "Truth table:\n" + "i i i | o o\n" + "1 2 3 | 1 2\n" + "------+----\n";
        }

        String[] tableText = {"0 0 0","0 0 1","0 1 0","0 1 1","1 0 0","1 0 1","1 1 0","1 1 1"};
        for (int i = 0; i < tableText.length; i++){
            simulateResult += tableText[i];
            simulateResult += " | ";

            String[] tableSplit = tableText[i].split(" ");
            for (int splitText = 0 ; splitText < 3; splitText++) {
                if (Integer.parseInt(tableSplit[splitText]) == 1) {iPins.get(splitText).setInput(true);}
                else {iPins.get(splitText).setInput(false);}
            }

            for (int j = 0; j < oPins.size(); j++){
                if (j == 1){simulateResult += " ";}

                if (oPins.get(j).getOutput()) {
                    simulateResult += "1";
                }
                else {
                    simulateResult += "0";
                }
            }
            simulateResult += "\n";
        }
        return simulateResult;
    }
}