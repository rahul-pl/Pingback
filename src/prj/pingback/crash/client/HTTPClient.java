package prj.pingback.crash.client;

import prj.httpparser.httpparser.RawHTTPRequest;
import prj.pingback.utils.ConcurrencyUtils;

public class HTTPClient
{
    private ConcurrencyUtils _concurrencyUtils;

    public HTTPClient(ConcurrencyUtils concurrencyUtils)
    {
        _concurrencyUtils = concurrencyUtils;
    }

    public void post(RawHTTPRequest request)
    {
    }
}
