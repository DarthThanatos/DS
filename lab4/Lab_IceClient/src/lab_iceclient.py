import sys, traceback, Ice, IceStorm
import Demo
import thread
import msvcrt

client_name = "Nameless-One"

print "Ice args:", sys.argv
ic = Ice.initialize(sys.argv)
topic_manager_base = ic.stringToProxy("DemoIceStorm/TopicManager:default -h localhost -p 9999")
topic_manager = IceStorm.TopicManagerPrx.checkedCast(topic_manager_base)
adapter = ic.createObjectAdapter("MonitorAdapter")

observed_devices = {}

if not topic_manager:
    raise RuntimeError("Invalid topic manager")

class MonitorI(Demo.Monitor):
    def report(self,m,curr):
        print "Measurement report:\n" +\
            "  Tower: " + str(m.tower) + "\n" +\
            "  W Spd: " + str(m.windSpeed) + "\n" +\
            "  W Dir: " + str(m.windDirection) + "\n" +\
            "   Temp: " + str(m.temperature) + "\n" +\
            client_name + ">"

def perform_action():
    topic = None
    channel = raw_input("Type the device whose state is about to be changed\n"+ client_name + ">")
    while topic == None:
        try:
            topic = topic_manager.retrieve(channel)
        except IceStorm.NoSuchTopic:
            try:
                topic = topic_manager.create(channel)
            except IceStorm.TopicExists:
                pass
    pub = topic.getPublisher().ice_oneway()
    monitor = Demo.MonitorPrx.uncheckedCast(pub)
    m = Demo.Measurement(tower="tower")
    monitor.report(m)
    print "Sent report"
    msvcrt.getch()
            
def subscribe(channel):
    monitor = MonitorI()
    prx = adapter.addWithUUID(monitor).ice_oneway()
    adapter.activate()
    try: 
        topic = topic_manager.retrieve(channel)
        quos = None
        topic.subscribeAndGetPublisher(quos,prx)
        #ic.shutdownOnInterrupt()
        observed_devices[channel] = (prx,topic)
        print "Started to observe device",channel
        #ic.waitForShutdown()
    except IceStorm.NoSuchTopic:
        print "invalid topic"
        msvcrt.getch()
    
def list_observed_devices():
    if observed_devices.__len__() == 0:
        print "No observed devices"
    else:
        for device in observed_devices:
            print device
    msvcrt.getch()

def start_observing():
    channel = raw_input("Type the device you want to observe:\n"+ client_name + ">")
    if not channel in observed_devices:
        thread.start_new_thread(subscribe, (channel,))
        msvcrt.getch()
    else:
        print "You already observe that device!"
        msvcrt.getch()

def stop_observing():
    channel = raw_input("Type the device you want to leave alone:\n"+ client_name + ">")
    try:
        proxy,topic = observed_devices[channel]
        topic.unsubscribe(proxy)
        observed_devices.__delitem__(channel)
        print "Stopped observing device", channel
        msvcrt.getch()
    except KeyError:
        print "You do not observe such a device, notihing to stop"
        msvcrt.getch()
    

def list_devices():
    print "to-do"
    msvcrt.getch()

def lock_device():
    print "to-do"
    msvcrt.getch()
    
def release_device():
    print "to-do"
    msvcrt.getch()

status = 0
try:
    #base = ic.stringToProxy("SimplePrinter:tcp -h localhost -p 10000:udp -h localhost -p 10000")
    base = ic.propertyToProxy("SimplePrinter.Proxy")
    printer = Demo.PrinterPrx.checkedCast(base)
    
    if not printer:
        raise RuntimeError("Invalid proxy")
    
    res = printer.printString("Hello World!")
    print "Printed hello world on the server's console"
    print "Received:",res
    
    client_name = raw_input("Say your name:\n" + ">")
    print "Entering interactive loop"
    while True:
        user_option = raw_input(
            "Type what you want to do:\n"+\
            "0-try to take control over a device" +\
            "1-perform action on a device (caution: changes will be visible to third-party observers)\n"+\
            "2-observe\n"+\
            "3-list devices in lab\n"+\
            "4-list observed devices\n"+\
            "5-stop observing a device\n"+\
            "6-realease device's lock" +\
            "7-Exit\n"+\
            client_name + ">"
        )
        if user_option == "0":
            lock_device()
        elif user_option == "1":
            perform_action()
        elif user_option == "2":
            start_observing()
        elif user_option == "3":
            list_devices()
        elif user_option == "4":
            list_observed_devices()
        elif user_option == "5":
            stop_observing()
        elif user_option == "6":
            release_device()
        elif user_option == "7":
            break
        else:
            print "invalid option, pick one more time"
            msvcrt.getch()
except:
    traceback.print_exc()
    status = 1
 
if ic:
    # Clean up
    try:
        ic.destroy()
    except:
        traceback.print_exc()
        status = 1
 
sys.exit(status)