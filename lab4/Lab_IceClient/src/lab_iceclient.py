import sys, traceback, Ice
import Demo

def publish(publisher):
    topic = None
    while topc == None:
        try:
            topic = publisher.retreive("State")
        except IceStorm.NoSuchTopic:
            try:
                topic = publisher.retrieve("")
            except IceStorm.TopicExists:
                pass

status = 0
ic = None
try:
    ic = Ice.initialize(sys.argv)
    base = ic.stringToProxy("SimplePrinter:tcp -h localhost -p 10000:udp -h localhost -p 10000")
    publisher_base = ic.stringToProxy("IceStorm/TopicManager:tcp -h localhost -p 10000:udp -h localhost -p 10000")
    printer = Demo.PrinterPrx.checkedCast(base)
    publisher = ic.TopicManagerPrxHelper.checkedCast(publisher_base)
    if not printer:
        raise RuntimeError("Invalid proxy")
    if not publisher:
        raise RuntimeError("Invalid publisher")
    
 
    res = printer.printString("Hello World!")
    print "Printed hello world on the server's console"
    print "Received:",res
    print "Entering interactive loop"
    while True:
        user_option = raw_input("Type what you want to do:\n1-publish\n2-observe\n")
        if user_option == "1":
            publish(publisher)
        elif user_option == "2":
            pass
        else:
            print "invalid option, pick one more time"
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