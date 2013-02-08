package prj.pingback.handler;

import prj.httpApplication.RawHTTPResponse;
import prj.httpApplication.app.HTTPRequestHandler;
import prj.httpparser.httpparser.RawHTTPRequest;
import prj.pingback.devicemanager.DeviceManager;

public class PingbackHandler extends HTTPRequestHandler
{
    DeviceManager _deviceManager;

    public PingbackHandler(DeviceManager deviceManager)
    {
        _deviceManager = deviceManager;
    }

    @Override
    public RawHTTPResponse get(RawHTTPRequest request)
    {
        System.out.println(request.getHttpVersion());
        System.out.println(request.getResourceAddress());
        System.out.println(request.getRequestType());
        return new RawHTTPResponse("HTTP/1.1", 200, "OK")
        {
            {
                setBody("Fine Request");
            }
        };
    }

    @Override
    public RawHTTPResponse post(RawHTTPRequest request)
    {
        return new RawHTTPResponse("HTTP/1.1", 200, "OK")
        {
            {
                setBody("Fine Request");
            }
        };
    }
}
