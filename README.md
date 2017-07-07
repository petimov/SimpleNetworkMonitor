# SimpleNetworkMonitor

## Overview

A simple network monitor is an application with a GUI that includes three graphic elements:

1. The current type of traffic is a pie chart showing the types of services / protocols collected in 5 seconds.

2. Brief network information is an abstract factory that collects information about the internal ip-address, mask, gateway, external ip-address, DNS server ip-address from the network interface. Also, the field displays the maximum incoming / outgoing speed on the interface.

3. Current speed - a linear chart showing the current speed on the interface in 1 second in real time. The update interval is 60 seconds.

![GitHub Logo](/images/Selection_006.png)

At the core of the application are [jNetPcap](https://github.com/ruedigergad/clj-net-pcap/tree/master/jnetpcap), which include native library helps capture packet from physical/logical interface and [JFreeChart](https://github.com/jfree/jfreechart) that helps draw pie chart and linear-real-time chart.

## Download

[Linux](https://github.com/timmyventura/SimpleNetworkMonitor/raw/master/dist/simple_network_monitor_linux.tar.gz)

[Windows 32 bit](https://github.com/timmyventura/SimpleNetworkMonitor/raw/master/dist/simple_network_monitor_win32.zip)

[Windows 64 bit](https://github.com/timmyventura/SimpleNetworkMonitor/raw/master/dist/simple_network_monitor_win64.zip)
