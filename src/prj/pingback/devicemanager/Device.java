package prj.pingback.devicemanager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

public class Device
{
    private String _deviceId;
    private String _versionNumber;
    private Date _registryDate;
    private ScheduledFuture _cancellingFuture;
    public static String VERSION = "version";
    public static String DEVICE_ID = "device_id";

    public Device(String deviceId, String versionNumber)
    {
        System.out.println("new Device");
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

    public void setRegistryTime(Date date, ScheduledFuture future)
    {
        System.out.println("device " + this + " registered at " + date);
        _registryDate = date;
        _cancellingFuture = future;
    }

    public void resetCancellingFuture()
    {
        System.out.println("device " + this + " deregistration cancelled at " + new Date());
        _cancellingFuture.cancel(true);
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
    public String toString()
    {
        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put(DEVICE_ID, _deviceId);
            jsonObject.put(VERSION, _versionNumber);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
