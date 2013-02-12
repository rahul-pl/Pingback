package prj.pingback.utils;

import java.util.concurrent.*;

public class ConcurrencyUtils
{
    private ScheduledThreadPoolExecutor _stp;
    private ExecutorService _workPool;
    private static ConcurrencyUtils instance;

    private ConcurrencyUtils(ScheduledThreadPoolExecutor stp)
    {
        _stp = stp;
        _workPool = Executors.newCachedThreadPool();
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

    public <RESULT_TYPE>  Future<RESULT_TYPE> executeOnWorkPool(Callable<RESULT_TYPE> callable)
    {
        return _workPool.submit(callable);
    }
}
