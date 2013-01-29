package prj.pingback;

import prj.cyclo.Agent;
import prj.cyclo.TCPReactor;
import prj.httpApplication.agent.HTTPAgent;
import prj.httpApplication.app.BasicRouter;
import prj.httpApplication.app.Router;
import prj.httpApplication.app.WebApp;
import prj.pingback.handler.PingbackHandler;

import java.io.IOException;

public class Pingback
{
    public static void main(String[] args) throws IOException
    {
        TCPReactor reactor = TCPReactor.initiate();
        Router router = new BasicRouter()
        {
            {
                addRouting("/heartbeat/android", new PingbackHandler());
            }
        };
        WebApp webApp = new WebApp(router);
        Agent pingBackAgent = new HTTPAgent(reactor, webApp, 80);
        reactor.register(pingBackAgent);
        reactor.fire();
    }
}
