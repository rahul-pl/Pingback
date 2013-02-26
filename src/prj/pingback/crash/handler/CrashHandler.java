package prj.pingback.crash.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prj.httpApplication.RawHTTPResponse;
import prj.httpApplication.app.HTTPRequestHandler;
import prj.httpparser.httpparser.RawHTTPRequest;
import prj.pingback.crash.client.HTTPClient;

public class CrashHandler extends HTTPRequestHandler
{
    private Logger _logger;
    private HTTPClient _client;

    public CrashHandler(HTTPClient client)
    {
        _logger = LoggerFactory.getLogger(CrashHandler.class.getSimpleName());
        _client = client;
    }

    @Override
    public RawHTTPResponse post(RawHTTPRequest request)
    {
        _logger.debug("request is : {}", request.toString().replace("\r", "\\r").replace("\n", "\\n"));
        _client.post(request);
        return new RawHTTPResponse("HTTP/1.1", 200, "OK")
        {
            {
                setBody("Fine Request");
            }
        };
    }
}
