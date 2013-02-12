package prj.pingback.crash.handler;

import prj.httpApplication.RawHTTPResponse;
import prj.httpApplication.app.HTTPRequestHandler;
import prj.httpparser.httpparser.RawHTTPRequest;

public class CrashHandler extends HTTPRequestHandler
{
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
