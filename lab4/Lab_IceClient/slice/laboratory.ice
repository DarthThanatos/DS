module Demo {

    sequence<string> stringSeq;

    class DeviceState{
        float batteryLevel;
        string lastUserName;
        long operationTime;
        string formatToString();
    };

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
    
    class CameraState extends DeviceState{
        float horizontalAngle;
        float verticalAngle;
    };

    class ZoomingCameraState extends CameraState{
        int zoomLvl;
    };
    
    interface Camera extends Device{
        void turnLeft(float angle);
        CameraState turnRight(float angle);
        CameraState turnUp(float angle);
        CameraState turnDown(float angle);
    };
    

    interface ZoomingCamera extends Camera{
        ZoomingCameraState* zoom(int lvl);
    };

    class PrinterState extends DeviceState{
        int inkLevel;
        string result;
    };

    class AsciiPrinterState extends PrinterState{
        string prettyResult;
    };

    class InterpretingPrinterState extends PrinterState{
        stringSeq interpretedParts;
    };

    interface Printer extends Device{
        PrinterState* printString(string s);
        void fillInk();
    };

    interface AsciiPrinter extends Printer{
        AsciiPrinterState* prettyPrint(string s);
    };
    
    interface InterpretingPrinter extends Printer{
        InterpretingPrinterState* interpretAndPrint();
    };

    struct Measurement {
        string tower; // tower id
        float windSpeed; // knots
        short windDirection; // degrees
        float temperature; // degrees Celsius
    };

    enum diodecolor {RED, GREEN, BLUE};
    class SensorState extends DeviceState{
        float speedInMilesPerHour;
        float speedInMetresPerHour;    
        diodecolor speedColor;
    };

    class BodyTemperatureState extends SensorState{
        float temperatureInCentimeters;
        float temperatureInFahrenheits;
        diodecolor temperatureColor;
    };
    
    class MoistureSensorState extends SensorState{
        int mouistureLvl;
        diodecolor moistureColor;
    };
    
    enum speed {SLOW, MEDIUM, QUICK}; 
    
    interface Sensor extends Device{
        SensorState* measureMotion(speed speedObj);
    };
    
    enum moisture {DRY, WET, OCEAN};

    interface MoistureSensor extends Sensor{
        MoistureSensorState* measureMoisture(moisture moistureObj);
    };
    
    enum temperature {COLD, COOL, WARM};

    interface BodyTemperatureSensor extends Sensor{
        BodyTemperatureState* measureBodyTemperature(temperature temperatureObj);
    };

    interface Reporter{
        //void report(DeviceState* state);
        void report(string msg);
    };

    interface Monitor {
        void report(Measurement m);
    };
};