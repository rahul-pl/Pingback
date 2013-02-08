package prj.pingback.devicemanager;

public class Device
{
    private String _deviceId;
    private String _versionNumber;

    public Device(String deviceId, String versionNumber)
    {
        _deviceId = deviceId;
        _versionNumber = versionNumber;
    }

    public String getDeviceId()
    {
        return _deviceId;
    }

    public String getVersionNumber()
    {
        return _versionNumber;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Device)
        {
            return _deviceId.equals(((Device) obj)._deviceId);
        }
        else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return _deviceId.hashCode();
    }
}
