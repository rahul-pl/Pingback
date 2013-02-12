package prj.pingback.crash.handler;

import prj.httpApplication.RawHTTPResponse;
import prj.httpApplication.app.HTTPRequestHandler;
import prj.httpparser.httpparser.RawHTTPRequest;
import prj.pingback.crash.client.HTTPClient;

public class CrashHandler extends HTTPRequestHandler
{
    private HTTPClient _client;

    public CrashHandler(HTTPClient client)
    {
        _client = client;
    }

    @Override
    public RawHTTPResponse post(RawHTTPRequest request)
    {
        System.out.println("request is : " + request.toString().replace("\r", "\\r").replace("\n", "\\n"));
        _client.post(request);
        return new RawHTTPResponse("HTTP/1.1", 200, "OK")
        {
            {
                setBody("Fine Request");
            }
        };
    }
}
