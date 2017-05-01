module Demo {

    sequence<string> stringSeq;

    interface Device{
        string getName();
        string getState();
        void feedBattery();
        stringSeq listActions();
    };

    exception AlreadyControlledException{
        string byWho;
    };

    exception NotKnownDeviceException{
        string explanation;
    };

    exception DeviceNotControlledException{
        string explanation;
    };

    interface LaboratoryRoom {
        stringSeq getDevicesNamesList();
        stringSeq getDeviceOperationsList(string deviceId) throws NotKnownDeviceException;
        Device* takeControlOverDevice(string deviceId, string userId) throws AlreadyControlledException, NotKnownDeviceException;
        void releaseDevice(string deviceId, string userId) throws NotKnownDeviceException, DeviceNotControlledException;
    };
    
    exception RotationOutOfRangeException{
        float minVertical;
        float maxVertival;
        float actual;
        string reason;
    };

    interface Camera extends Device{
        void turnLeft(float angle) throws RotationOutOfRangeException;
        void turnRight(float angle) throws RotationOutOfRangeException;
        void turnUp(float angle) throws RotationOutOfRangeException;
        void turnDown(float angle) throws RotationOutOfRangeException;
    };
    
    exception ZoomOutOfRangeException{
        int maxLvl;
        int actualLvl;
        string reason;
    };

    interface ZoomingCamera extends Camera{
        void zoom(int lvl) throws ZoomOutOfRangeException;
    };

    exception OutOfInkException{
        int actualInkLvl;
        int requiredInkLvl;
        string reason;
    };

    interface Printer extends Device{
        string printString(string s) throws OutOfInkException;
        void fillInk();
    };

    interface AsciiPrinter extends Printer{
        string prettyPrint(string s) throws OutOfInkException;
    };
    
    interface InterpretingPrinter extends Printer{
        string interpretAndPrint(string s) throws OutOfInkException;
    };

    exception BrokenDiodeException{
        float maxMeasuredValue;
        float actualMeasuredValue;
        string reason;
    };

    interface Sensor extends Device{
        void measureMotion(float speed) throws BrokenDiodeException;
    };

    interface MoistureSensor extends Sensor{
        void measureMoisture(float moisture) throws BrokenDiodeException;
    };
    
    interface BodyTemperatureSensor extends Sensor{
        void measureBodyTemperature(float temperature) throws BrokenDiodeException;
    };

    interface Reporter{
        void report(string msg);
    };

};