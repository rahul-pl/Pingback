package prj.pingback.handler;

import prj.httpApplication.RawHTTPResponse;
import prj.httpApplication.app.HTTPRequestHandler;
import prj.httpparser.httpparser.RawHTTPRequest;
import prj.pingback.devicemanager.Device;
import prj.pingback.devicemanager.DeviceManager;
import prj.pingback.utils.ParseUtils;

import java.util.Map;

public class PingbackHandler extends HTTPRequestHandler
{
    DeviceManager _deviceManager;
    private static String VERSION = "version";
    private static String DEVICE_ID = "device_id";

    public PingbackHandler(DeviceManager deviceManager)
    {
        _deviceManager = deviceManager;
    }

    @Override
    public RawHTTPResponse get(RawHTTPRequest request)
    {
        return new RawHTTPResponse("HTTP/1.1", 200, "OK")
        {
            {
                setBody(_deviceManager.toString());
            }
        };
    }

    @Override
    public RawHTTPResponse post(RawHTTPRequest request)
    {
        System.out.println("post request");
        String body = request.getBody();
        Map<String, String> data = ParseUtils.parseResponse(body);
        System.out.println("data : " + data);
        if (data.containsKey(DEVICE_ID) && data.containsKey(VERSION))
        {
            Device device = new Device(data.get(DEVICE_ID), data.get(VERSION));
            _deviceManager.registerDevice(device);
        }
        return new RawHTTPResponse("HTTP/1.1", 200, "OK")
        {
            {
                setBody("Fine Request");
            }
        };
    }
}
