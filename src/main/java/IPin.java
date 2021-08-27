public class IPin extends Device {
    private boolean value;

    @Override
    public void setInput(boolean value)
    {
        this.value = value;
    }

    @Override
    public boolean getOutput()
    {
        return value;
    }
}
