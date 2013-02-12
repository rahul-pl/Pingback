package prj.pingback;

import prj.cyclo.Agent;
import prj.cyclo.TCPReactor;
import prj.httpApplication.agent.HTTPAgent;
import prj.httpApplication.app.BasicRouter;
import prj.httpApplication.app.Router;
import prj.httpApplication.app.WebApp;
import prj.pingback.crash.client.HTTPClient;
import prj.pingback.crash.handler.CrashHandler;
import prj.pingback.heartbeat.devicemanager.DeviceManager;
import prj.pingback.heartbeat.handler.PingbackHandler;
import prj.pingback.utils.ConcurrencyUtils;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

public class Pingback
{
    private static ScheduledThreadPoolExecutor _stp;
    private static TCPReactor _reactor;

    public static void main(String[] args) throws IOException
    {
        _reactor = TCPReactor.initiate();

        setupThreads();

        ConcurrencyUtils.initiate(_stp);

        Agent pingBackAgent = initiateAgent();

        _reactor.register(pingBackAgent);
        _reactor.fire();
    }

    private static Agent initiateAgent()
    {
        final ConcurrencyUtils concurrencyUtils = ConcurrencyUtils.getInstance();
        Router router = new BasicRouter()
        {
            {
                addRouting("/heartbeat/android", new PingbackHandler(new DeviceManager(concurrencyUtils)));
                addRouting("/crash/android", new CrashHandler(new HTTPClient(concurrencyUtils)));
            }
        };
        WebApp webApp = new WebApp(router);
        return new HTTPAgent(_reactor, webApp, 80, _stp);
    }

    private static void setupThreads()
    {
        Thread.currentThread().setName("Reactor");
        ThreadFactory appThreadFactory = new ThreadFactory()
        {
            @Override
            public Thread newThread(Runnable r)
            {
                Thread t = new Thread(r);
                t.setName("pb");
                return t;
            }
        };
        _stp = new ScheduledThreadPoolExecutor(1, appThreadFactory);
        _stp.setRemoveOnCancelPolicy(true);
    }
}
