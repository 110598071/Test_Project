import java.util.Vector;

public class Device {
    protected Vector<Device> iPins;

    public Device()
    {
        iPins = new Vector<>();
    }

    public void addInputPin(Device iPin)
    {
        throw new RuntimeException("device can't get input");
    }

    public void setInput(boolean value)
    {
        throw new RuntimeException("device can't get input");
    }

    public boolean getOutput()
    {
        throw new RuntimeException("device can't get output");
    }
}
