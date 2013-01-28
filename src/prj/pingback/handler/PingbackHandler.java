package prj.pingback.handler;

import prj.httpApplication.RawHTTPResponse;
import prj.httpApplication.app.HTTPRequestHandler;
import prj.httpparser.httpparser.RawHTTPRequest;

public class PingBackHandler extends HTTPRequestHandler
{
    @Override
    public RawHTTPResponse get(RawHTTPRequest request)
    {
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
