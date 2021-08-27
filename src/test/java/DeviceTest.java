import org.junit.*;
import static org.junit.Assert.*;

public class DeviceTest
{
    @Test
    public void testDevicePolymorphism(){
        Device device = new IPin();
        assertEquals(IPin.class.getName(), device.getClass().getName());
    }

    @Test
    public void testIPinAndOPin()
    {
        // 0 = 0
        IPin iPin = new IPin();
        iPin.setInput(false);

        OPin oPin = new OPin();
        oPin.addInputPin(iPin);

        assertEquals(false, oPin.getOutput());

        /* implement 1 = 1 test */
        IPin iPin1 = new IPin();
        iPin1.setInput(true);

        OPin oPin1 = new OPin();
        oPin1.addInputPin(iPin1);

        assertEquals(true, oPin1.getOutput());
    }

    @Test
    public void testGateNOT()
    {
        // NOT 0 = 1
        IPin iPin = new IPin();
        iPin.setInput(false);

        GateNOT gateNOT = new GateNOT();
        gateNOT.addInputPin(iPin);

        assertEquals(true, gateNOT.getOutput());

        /* implement NOT 1 = 0 test */
        IPin iPin1 = new IPin();
        iPin1.setInput(true);

        GateNOT gateNOT1 = new GateNOT();
        gateNOT1.addInputPin(iPin1);

        assertEquals(false, gateNOT1.getOutput());
    }

    @Test
    public void testGateAND()
    {
        // 0 AND 0 = 0
        IPin iPin1 = new IPin();
        IPin iPin2 = new IPin();
        iPin1.setInput(false);
        iPin2.setInput(false);

        GateAND gateAND = new GateAND();
        gateAND.addInputPin(iPin1);
        gateAND.addInputPin(iPin2);

        assertEquals(false, gateAND.getOutput());

    /* implement 0 AND 1 = 0,
                 1 AND 0 = 0,
                 1 AND 1 = 1 test */
        IPin iPin3 = new IPin();
        IPin iPin4 = new IPin();
        iPin3.setInput(false);
        iPin4.setInput(true);

        GateAND gateAND1 = new GateAND();
        gateAND1.addInputPin(iPin3);
        gateAND1.addInputPin(iPin4);

        assertEquals(false, gateAND1.getOutput());

        IPin iPin5 = new IPin();
        IPin iPin6 = new IPin();
        iPin5.setInput(true);
        iPin6.setInput(false);

        GateAND gateAND2 = new GateAND();
        gateAND2.addInputPin(iPin5);
        gateAND2.addInputPin(iPin6);

        assertEquals(false, gateAND2.getOutput());

        IPin iPin7 = new IPin();
        IPin iPin8 = new IPin();
        iPin7.setInput(true);
        iPin8.setInput(true);

        GateAND gateAND3 = new GateAND();
        gateAND3.addInputPin(iPin7);
        gateAND3.addInputPin(iPin8);

        assertEquals(true, gateAND3.getOutput());
    }

    @Test
    public void testGateOR()
    {
        // 0 OR 0 = 0
        IPin iPin1 = new IPin();
        IPin iPin2 = new IPin();
        iPin1.setInput(false);
        iPin2.setInput(false);

        GateOR gateOR = new GateOR();
        gateOR.addInputPin(iPin1);
        gateOR.addInputPin(iPin2);

        assertEquals(false, gateOR.getOutput());

    /* implement 0 OR 1 = 1,
                 1 OR 0 = 1,
                 1 OR 1 = 1 test */
        IPin iPin3 = new IPin();
        IPin iPin4 = new IPin();
        iPin3.setInput(false);
        iPin4.setInput(true);

        GateOR gateOR1 = new GateOR();
        gateOR1.addInputPin(iPin3);
        gateOR1.addInputPin(iPin4);

        assertEquals(true, gateOR1.getOutput());

        IPin iPin5 = new IPin();
        IPin iPin6 = new IPin();
        iPin5.setInput(true);
        iPin6.setInput(false);

        GateOR gateOR2 = new GateOR();
        gateOR2.addInputPin(iPin5);
        gateOR2.addInputPin(iPin6);

        assertEquals(true, gateOR2.getOutput());

        IPin iPin7 = new IPin();
        IPin iPin8 = new IPin();
        iPin7.setInput(true);
        iPin8.setInput(true);

        GateOR gateOR3 = new GateOR();
        gateOR3.addInputPin(iPin7);
        gateOR3.addInputPin(iPin8);

        assertEquals(true, gateOR3.getOutput());
    }
}