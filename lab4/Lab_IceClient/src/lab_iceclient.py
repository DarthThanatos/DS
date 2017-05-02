import sys, traceback, Ice, IceStorm
import Demo
import thread
import msvcrt
import re

client_name = "Nameless-One"

print "Ice args:", sys.argv
ic = Ice.initialize(sys.argv)
topic_manager_base = ic.stringToProxy("DemoIceStorm/TopicManager:default -h localhost -p 9999")
laboratory_room_base = ic.propertyToProxy("LaboratoryRoom.Proxy")
topic_manager = IceStorm.TopicManagerPrx.checkedCast(topic_manager_base)
laboratory_room = Demo.LaboratoryRoomPrx.checkedCast(laboratory_room_base)

adapter = ic.createObjectAdapter("MonitorAdapter") # for subscribers

observed_devices = {}
controlled_devices = {}

if not topic_manager:
    raise RuntimeError("Invalid topic manager")

class ReporterI(Demo.Reporter):
    def report(self,msg,curr):
        print "\n" + msg+ "\n" +  client_name + ">"

def createDevicesTopics(devicesNamesList):
    for deviceName in devicesNamesList:
        try:
            topic = topic_manager.create(deviceName)
        except IceStorm.TopicExists:
            pass

def list_devices(devicesNamesList, wait_for_input = True):
    print "Devices in laboratory:"
    for deviceName in devicesNamesList:
        print "\t->",deviceName
    if wait_for_input: msvcrt.getch()
    

def list_controlled_devices(wait_for_user_input = True):
    if controlled_devices.__len__() == 0:
        print "You do not control any devices"
    else:
        print "Devices that you control:"
        for deviceName in controlled_devices.keys():
            print "\t-> " + deviceName        
    if wait_for_user_input: msvcrt.getch()


def list_observed_devices(wait_for_user_input = True):
    if observed_devices.__len__() == 0:
        print "No observed devices"
    else:
        print "Devices observed by you:"
        for device in observed_devices:
            print "\t->", device
    if wait_for_user_input: msvcrt.getch()


def getListOfDeviceOperations(deviceName):
    try:
        opList = laboratory_room.getDeviceOperationsList(deviceName)
        print "Operations for device:", deviceName
        for operation_index, operationName in enumerate(opList):
            print "\t-> " + str(operation_index) + " " + operationName
        return opList
    except:
        print "Device not known"
        return []

def listDeviceOperations():
    deviceName = raw_input("Type the device whose operations you want to list:\n"+ client_name + ">")
    getListOfDeviceOperations(deviceName)
    msvcrt.getch()

def perform_action():
    list_controlled_devices(wait_for_user_input=False)
    topic = None
    device = raw_input("Type the device whose state is about to be changed\n"+ client_name + ">")
    if not device in controlled_devices:
        print "You do not control that device, please try to lock it first"
        msvcrt.getch()
        return
    try:
        topic = topic_manager.retrieve(device)
    except IceStorm.NoSuchTopic:
        print "No such device"
        msvcrt.getch()
        return
    opList = getListOfDeviceOperations(device)
    opIndex = raw_input("Type an index of an operation to be performed:\n" + client_name + ">")
    try:
        definition = opList[int(opIndex)]
    except:
        print "Not a valid operation index, returning to Menu"
        msvcrt.getch()
        return
    
    #parsing arguments of a remote operation
    operationName = re.match(r'(\w+) \(',definition).group(1)
    args = re.findall(r'((int|string|float)\s(\w+),?\s*)',definition)
    arg_list = []
    try:
	for (type_and_name,arg_type,arg_name) in args:
		arg_val = input("Type value for " + arg_name + " of type " + arg_type+" (please, input strings in quotes, e.g. \"ala\"): ")
		arg_list.append(arg_val)
    except :
	print "Not a valid value"
        msvcrt.getch()
        return
    #downcasting to proxy to the most derived type; proxy returned by LaboratoryRoom has type DevicePrx; calling its get_id() method will 
    #provide us with the most derived type of the Proxy in a stringified form, e.g. ::Demo::Camera; hence we first must skip the initial double colon symbol,
    #change the remaining to the dot symbol (.) and then prepend Prx.checkedCast suffix; this expression, when presented to eval() funtion, will produce the most derived 
    #type of our Proxy
    try:
        prx_derived_type_desc = controlled_devices[device].ice_id()
        derived_type = eval(prx_derived_type_desc[2:].replace("::",".") + "Prx.checkedCast(controlled_devices[device])")
        res = getattr(derived_type, operationName)(*arg_list)
        if res:
            print res
        msg = "\n" + client_name + " has changed the state of device " + device + ":" + controlled_devices[device].getState()
        print "msg will be sent to all subscribers"
        pub = topic.getPublisher().ice_oneway()
        reporter = Demo.ReporterPrx.uncheckedCast(pub)
        reporter.report(msg) #notify subscribers about changed state of the device
        print "Sent report"
        msvcrt.getch()
    except:
        traceback.print_exc()
        print "Sth went wrong, check exception content above"
        msvcrt.getch()
            
def subscribe(channel):
    reporter = ReporterI()
    prx = adapter.addWithUUID(reporter).ice_oneway()
    adapter.activate()
    try: 
        topic = topic_manager.retrieve(channel)
        quos = None
        topic.subscribeAndGetPublisher(quos,prx)
        observed_devices[channel] = (prx,topic)
        print "Started to observe device",channel
    except IceStorm.NoSuchTopic:
        print "invalid topic"
    
def start_observing(devicesNamesList):
    list_devices(devicesNamesList, wait_for_input=False)
    list_observed_devices(False)
    channel = raw_input("Type the device you want to observe:\n"+ client_name + ">")
    if not channel in observed_devices:
        thread.start_new_thread(subscribe, (channel,))
        msvcrt.getch()
    else:
        print "You already observe that device!"
        msvcrt.getch()

def stop_observing():
    list_observed_devices(False)
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
    
def lock_device(devicesNamesList):
    list_devices(devicesNamesList, False)
    list_controlled_devices(False)
    device = raw_input("Select device to control:\n" + client_name + ">")
    if device in controlled_devices:
        print "You already control that device"
        msvcrt.getch()
        return
    try:
        dev_prx = laboratory_room.takeControlOverDevice(device, client_name)
        controlled_devices[device] = dev_prx
    except:
        traceback.print_exc()
        return
    topic = topic_manager.retrieve(device)
    pub = topic.getPublisher().ice_oneway()
    reporter = Demo.ReporterPrx.uncheckedCast(pub)
    reporter.report(client_name + " has locked " + device) #notify subscribers about changed state of the device
    print "You successfully locked ", device
    msvcrt.getch()
        
    
def release_device():
    list_controlled_devices(False)
    device = raw_input("Select device to release:\n" + client_name + ">")
    if device not in controlled_devices:
        print "You do not control that device"
        msvcrt.getch()
        return
    try:
        dev_prx = laboratory_room.releaseDevice(device,client_name)
    except:
        traceback.print_exc()
        return
    controlled_devices.__delitem__(device)
    #letting others know that we got a lock
    topic = topic_manager.retrieve(device)
    pub = topic.getPublisher().ice_oneway()
    reporter = Demo.ReporterPrx.uncheckedCast(pub)
    reporter.report(client_name + " has released " + device) #notify subscribers about changed state of the device
    
    print "You successfully released", device
    msvcrt.getch()

    
def actionsAtExit():
    for device in controlled_devices:
        laboratory_room.releaseDevice(device, client_name)
    channels_list = observed_devices.keys()
    for channel in channels_list:
        proxy,topic = observed_devices[channel]
        topic.unsubscribe(proxy)
        observed_devices.__delitem__(channel)

status = 0
try:
    
    devicesNamesList =  laboratory_room.getDevicesNamesList()    
    createDevicesTopics(devicesNamesList)
    
    client_name = raw_input("Say your name:\n" + ">")
    print "Entering interactive loop"
    while True:
        user_option = raw_input(
            "Type what you want to do:\n"+\
            "0-try to take control over a device\n" +\
            "1-perform action on a device (caution: changes will be visible to third-party observers)\n"+\
            "2-observe\n"+\
            "3-list devices in lab\n"+\
            "4-list observed devices\n"+\
            "5-stop observing a device\n"+\
            "6-realease device's lock\n" +\
            "7-list particular device's operations list\n" +\
            "8-list controlled devices list\n" +\
            "9-Exit\n"+\
            client_name + ">"
        )
        if user_option == "0":
            lock_device(devicesNamesList)
        elif user_option == "1":
            perform_action()
        elif user_option == "2":
            start_observing(devicesNamesList)
        elif user_option == "3":
            list_devices(devicesNamesList)
        elif user_option == "4":
            list_observed_devices()
        elif user_option == "5":
            stop_observing()
        elif user_option == "6":
            release_device()
        elif user_option == "7":
            listDeviceOperations()
        elif user_option == "8":
            list_controlled_devices()
        elif user_option == "9":
            actionsAtExit()
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