module Demo {

    sequence<string> stringSeq;

    class DeviceState{
        float batteryLevel;
        string lastUserName;
        long operationTime;
    };

    interface Device{
        string getName();
        DeviceState* getState();
        void feedBattery();
    }

    interface LaboratoryRoom {
        stringSeq getDevicesNamesList();
    };
    
    class CameraState extends DeviceState{
        float horizontalAngle;
        float verticalAngle;
    };

    class ZoomingCameraState extens CameraState{
        int zoomLvl;
    };
    
    interface Camera extends Device{
        CameraState* turnLeft(float angle);
        CameraState* turnRight(float angle);
        CameraState* turnUp(float angle);
        CameraState* turnDown(float angle);
    };
    

    interface ZoomingCamera extends Camera{
        ZoomingCameraState* zoom(int lvl);
    }

    

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

    interface Printer enxtends Device{
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

    enum diode_color {RED, GREEN, BLUE};
    class SensorState extends DeviceState{
        float speedInMilesPerHour;
        float speedInMetresPerHour;    
        diode_color speedColor;
    };

    class BodyTemperatureState extends SensorState{
        float temperatureInCentimeters;
        float temperatureInFahrenheits;
        diode_color temperatureColor;
    };
    
    MoistureSensorState extends SensorState{
        int mouistureLvl;
        diode_color moistureColor;
    };
    
    enum speed {SLOW, MEDIUM, QUICK}; 
    
    interface Sensor extends Device{
        SensorState* measureMotion(speed object);
    };
    
    enum moisture {DRY, WET, OCEAN};

    interface MoistureSensor extends Sensor{
        MoistureSensorState* measureMoisture(moisture object);
    };
    
    enum temperature {COLD, COOL, WARM};

    interface BodyTemperatureSensor extends Sensor{
        BodyTemperatureState* measureBodyTemperature(temperature object);
    };

    interface Reporter{
        void report(DeviceState* state);
    }

    interface Monitor {
        void report(Measurement m);
    };
};