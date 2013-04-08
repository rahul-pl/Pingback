package prj.pingback.heartbeat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prj.httpApplication.RawHTTPResponse;
import prj.httpApplication.app.HTTPRequestHandler;
import prj.httpparser.httpparser.RawHTTPRequest;
import prj.pingback.heartbeat.devicemanager.Device;
import prj.pingback.heartbeat.devicemanager.DeviceManager;
import prj.pingback.utils.ParseUtils;

import java.util.Map;

public class PingbackHandler extends HTTPRequestHandler
{
    Logger _logger;
    DeviceManager _deviceManager;

    public PingbackHandler(DeviceManager deviceManager)
    {
        _logger = LoggerFactory.getLogger(PingbackHandler.class);
        _deviceManager = deviceManager;
    }

    @Override
    public RawHTTPResponse get(RawHTTPRequest request)
    {
        _logger.debug("get request");
        return new RawHTTPResponse("HTTP/1.1", 200, "OK")
        {
            {
                _logger.debug("response {}", _deviceManager.toString());
                setBody(_deviceManager.toString());
            }
        };
    }

    @Override
    public RawHTTPResponse post(RawHTTPRequest request)
    {
        _logger.debug("post request");
        String body = request.getBody();
        Map<String, String> data = ParseUtils.parseResponse(body);
        _logger.debug("data {}", data);
        if (data.containsKey(Device.DEVICE_ID) && data.containsKey(Device.VERSION))
        {
            Device device = new Device(data.get(Device.DEVICE_ID), data.get(Device.VERSION));
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
