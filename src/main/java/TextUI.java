import java.util.Scanner;
import java.util.Vector;

public class TextUI {

    public void displayMenu() {
        boolean loopControl = true;
        boolean loadFile = false;

        LogicSimulator logicSimulator;
        logicSimulator = new LogicSimulator();

        while (loopControl) {
            System.out.print(menuText());
            Scanner sc = new Scanner(System.in);
            try {
                int cc = sc.nextInt();
                switch (cc){
                    case 1:
                        loadFile = true;
                        System.out.println("Please key in a file path:");
                        Scanner loadScanner = new Scanner(System.in);
                        String filePath = loadScanner.next();
                        if (!logicSimulator.load(filePath)){
                            System.out.println("File not Found!");
                        }
                        else{
                            System.out.printf("Circuit: %d input pins, %d output pins and %d gates%n",
                                    logicSimulator.getInputPinsSize(),
                                    logicSimulator.getOutputPinsSize(),
                                    logicSimulator.getCircuitsSize());
                        }
                        break;

                    case 2:
                        if(!loadFile) {System.out.println("Please load an lcf file first!");}
                        else{
                            Vector<Boolean> inputValues = new Vector<>();
                            for (int i = 1; i <= 3; i++){
                                boolean keyLoop = true;
                                while(keyLoop){
                                    System.out.printf("Please key in the value of input pin %d:%n",i);
                                    Scanner pinScanner = new Scanner(System.in);
                                    try {
                                        int inputPin = pinScanner.nextInt();
                                        if(inputPin == 1 || inputPin == 0){
                                            keyLoop = false;
                                            switch (inputPin){
                                                case 0:
                                                    inputValues.add(false);
                                                    break;
                                                case 1:
                                                    inputValues.add(true);
                                                    break;
                                            }
                                        }
                                        else{System.out.println("Input pin must be 0/1");}
                                    }catch(Exception e){System.out.println("Input pin must be Integer!");}
                                }
                            }
                            System.out.println(logicSimulator.getSimulationResult(inputValues));
                        }
                        break;

                    case 3:
                        if(!loadFile) {System.out.println("Please load an lcf file first!");}
                        else{System.out.println(logicSimulator.getTruthTable());}
                        break;

                    case 4:
                        loopControl = false;
                        break;

                    default:
                        System.out.println("Command must be 1 to 4!");
                }
            }catch (Exception e){System.out.println("Command must be Integer number!");}
            System.out.println("");
        }
    }

    private String menuText(){
        StringBuilder menuText = new StringBuilder();
        menuText.append("1. Load Logic Circuit File\n");
        menuText.append("2. Simulation\n");
        menuText.append("3. Display Truth Table\n");
        menuText.append("4. Exit\n");
        menuText.append("Command：\n");

        String getMenu = menuText.toString();
        return getMenu;
    }
}
