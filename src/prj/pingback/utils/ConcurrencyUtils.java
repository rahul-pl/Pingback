package prj.pingback.utils;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConcurrencyUtils
{
    private ScheduledThreadPoolExecutor _stp;
    private static ConcurrencyUtils instance;

    private ConcurrencyUtils(ScheduledThreadPoolExecutor stp)
    {
        _stp = stp;
    }

    public static void initiate(ScheduledThreadPoolExecutor stp)
    {
        instance = new ConcurrencyUtils(stp);
    }

    public static ConcurrencyUtils getInstance()
    {
        return instance;
    }

    public ScheduledFuture scheduleOnSTP(Runnable runnable, int timeInSeconds, TimeUnit timeUnit)
    {
        return _stp.schedule(runnable, timeInSeconds, timeUnit);
    }
}
