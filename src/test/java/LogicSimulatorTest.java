import org.junit.*;
import static org.junit.Assert.*;
import java.util.Vector;

public class LogicSimulatorTest
{
    String file1Path;
    String file2Path;

    @Before
    public void setUp()
    {
        file1Path = "src/File1.lcf";
        file2Path = "src/File2.lcf";
    }

    @Test
    public void testLoad()
    {
        LogicSimulator logicSimulator = new LogicSimulator();

        assertFalse(logicSimulator.load("FILE_NOT_EXIST"));
        assertTrue(logicSimulator.load(file1Path));
        assertEquals(3, logicSimulator.getInputPinsSize()); // replace it with your own
        assertEquals(1, logicSimulator.getOutputPinsSize()); // replace it with your own
        assertEquals(3, logicSimulator.getCircuitsSize()); // replace it with your own

        assertTrue(logicSimulator.load(file2Path));
        assertEquals(3, logicSimulator.getInputPinsSize()); // replace it with your own
        assertEquals(2, logicSimulator.getOutputPinsSize()); // replace it with your own
        assertEquals(5, logicSimulator.getCircuitsSize()); // replace it with your own
    }

    @Test
    public void testGetSimulationResult()
    {
        LogicSimulator logicSimulator = new LogicSimulator();

        logicSimulator.load(file1Path);

        Vector<Boolean> inputValues = new Vector<>();
        inputValues.add(false);
        inputValues.add(true);
        inputValues.add(true);

        assertEquals("Simulation Result:\n" +
                "i i i | o\n" +
                "1 2 3 | 1\n" +
                "------+--\n" +
                "0 1 1 | 0\n", logicSimulator.getSimulationResult(inputValues));

        inputValues = new Vector<>();
        inputValues.add(true);
        inputValues.add(false);
        inputValues.add(false);

        assertEquals("Simulation Result:\n" +
                "i i i | o\n" +
                "1 2 3 | 1\n" +
                "------+--\n" +
                "1 0 0 | 1\n", logicSimulator.getSimulationResult(inputValues));
    }

    @Test
    public void testGetTruthTable()
    {
        LogicSimulator logicSimulator = new LogicSimulator();

        logicSimulator.load(file1Path);

        assertEquals("Truth table:\n" +
                "i i i | o\n" +
                "1 2 3 | 1\n" +
                "------+--\n" +
                "0 0 0 | 0\n" +
                "0 0 1 | 0\n" +
                "0 1 0 | 0\n" +
                "0 1 1 | 0\n" +
                "1 0 0 | 1\n" +
                "1 0 1 | 1\n" +
                "1 1 0 | 0\n" +
                "1 1 1 | 0\n", logicSimulator.getTruthTable());

        logicSimulator = new LogicSimulator();

        logicSimulator.load(file2Path);

        assertEquals("Truth table:\n" +
                "i i i | o o\n" +
                "1 2 3 | 1 2\n" +
                "------+----\n" +
                "0 0 0 | 0 1\n" +
                "0 0 1 | 0 1\n" +
                "0 1 0 | 0 1\n" +
                "0 1 1 | 0 1\n" +
                "1 0 0 | 1 0\n" +
                "1 0 1 | 1 0\n" +
                "1 1 0 | 0 1\n" +
                "1 1 1 | 0 1\n", logicSimulator.getTruthTable());
    }
}