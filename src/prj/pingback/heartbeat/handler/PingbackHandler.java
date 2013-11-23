package prj.pingback.heartbeat.handler;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prj.httpApplication.RawHTTPResponse;
import prj.httpApplication.app.HTTPRequestHandler;
import prj.httpparser.httpparser.RawHTTPRequest;
import prj.pingback.heartbeat.devicemanager.Device;
import prj.pingback.heartbeat.devicemanager.DeviceManager;
import prj.pingback.utils.ParseUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PingbackHandler extends HTTPRequestHandler
{
    Logger _logger;
    DeviceManager _deviceManager;

    public PingbackHandler(DeviceManager deviceManager)
    {
        _logger = LoggerFactory.getLogger(PingbackHandler.class);
        _deviceManager = deviceManager;

        _deviceManager.registerDevice(new Device("abc", "1.3.4"));
        _deviceManager.registerDevice(new Device("abcd", "1.3.6"));
        _deviceManager.registerDevice(new Device("abcde", "1.3.5"));
        _deviceManager.registerDevice(new Device("abcdef", "1.3.5"));
    }

    @Override
    public RawHTTPResponse get(RawHTTPRequest request)
    {
        _logger.debug("get request");
        return new RawHTTPResponse("HTTP/1.1", 200, "OK")
        {
            {
                _logger.debug("response {}", _deviceManager.toString());
//                setBody(_deviceManager.toString());
                Configuration cfg = new Configuration();

                String output = null;
                try
                {
                    Template template = cfg.getTemplate("heartbeat.ftl");

                    // Build the data-model
                    Map<String, Object> data = new HashMap<>();
                    data.put("message", "Hello World!");

                    //List parsing
                    List<String> countries = new ArrayList<>();
                    countries.add("India");
                    countries.add("United States");
                    countries.add("Germany");
                    countries.add("France");

                    data.put("countries", countries);

                    StringWriter stringWriter = new StringWriter();
                    template.process(data, stringWriter);

                    output = stringWriter.toString();
                }
                catch (IOException |TemplateException e)
                {
                    e.printStackTrace();
                }
                setBody(output);
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
