package prj.pingback.devicemanager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DeviceManager implements IDeviceManager
{
    private Map<String, Set<Device>> versionMap;

    public DeviceManager()
    {
        versionMap = new HashMap<>();
    }

    public void registerDevice(final Device device)
    {
        String relevantVersionNumber = device.getVersionNumber();
        Set<Device> relevantSet = versionMap.get(relevantVersionNumber);
        if (relevantSet == null)
        {
            versionMap.put(relevantVersionNumber, new HashSet<Device>()
            {
                {
                    add(device);
                }
            });
        }
        else
        {
            relevantSet.add(device);
        }
    }
}
