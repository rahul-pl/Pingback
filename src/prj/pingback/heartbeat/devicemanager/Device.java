package prj.pingback.heartbeat.devicemanager;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

public class Device
{
    public static String VERSION = "version";
    public static String DEVICE_ID = "device_id";

    private Logger _logger;
    private String _deviceId;
    private String _versionNumber;
    private Date _registryDate;
    private ScheduledFuture _cancellingFuture;

    public Device(String deviceId, String versionNumber)
    {
        _logger = LoggerFactory.getLogger(Device.class.getSimpleName());
        _logger.debug("new Device | {}", this);
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
        _logger.debug("Registration | {} | {}", this, date);
        _registryDate = date;
        _cancellingFuture = future;
    }

    public void resetCancellingFuture()
    {
        _logger.debug("De-Registration cancelled | {} | {}", this, new Date());
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
