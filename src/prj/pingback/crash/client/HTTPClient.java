package prj.pingback.crash.client;

import prj.httpparser.httpparser.RawHTTPRequest;
import prj.pingback.utils.ConcurrencyUtils;

import java.io.*;
import java.util.concurrent.Callable;

public class HTTPClient
{
    private ConcurrencyUtils _concurrencyUtils;

    public HTTPClient(ConcurrencyUtils concurrencyUtils)
    {
        _concurrencyUtils = concurrencyUtils;
    }

    public void post(final RawHTTPRequest request)
    {
        _concurrencyUtils.executeOnWorkPool(new Callable<String>()
        {
            @Override
            public String call() throws IOException
            {
                InputStream inputStream = new ByteArrayInputStream(request.toString().getBytes());
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

                String inputLine;

                String result = "";
                while ((inputLine = in.readLine()) != null)
                {
                    result += inputLine;
                }

                in.close();
                return result;
            }
        });
    }
}
