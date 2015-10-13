
#include <Wire.h>  // Comes with Arduino IDE
//#include <LiquidCrystal_I2C.h>


#include <DS3231.h>
#include "DHT.h" 
#include <SPI.h>
#include <Ethernet.h>

#include <SD.h>
File myFile;

// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:

byte mac[] = { 
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress ip(192,168,1, 177);
IPAddress gateway(192,168,1, 1);
IPAddress subnet(255, 255, 0, 0);

// Initialize the Ethernet server library
// with the IP address and port you want to use 
// (port 80 is default for HTTP):
EthernetServer server(80);

#define LluviaPIN 10

#define DHTPIN 8 
#define DHTTYPE DHT11 
DHT dht(DHTPIN, DHTTYPE); 



DS3231  rtc(SDA, SCL);

//LiquidCrystal_I2C lcd(0x27, 2, 1, 0, 4, 5, 6, 7, 3, POSITIVE);  // Set the LCD I2C address

String inputString = "";       
boolean stringComplete = false;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  
  Serial.print("Initializing SD card...");
  // On the Ethernet Shield, CS is pin 4. It's set as an output by default.
  // Note that even if it's not used as the CS pin, the hardware SS pin 
  // (10 on most Arduino boards, 53 on the Mega) must be left as an output 
  // or the SD library functions will not work. 
   pinMode(10, OUTPUT);
   
  if (!SD.begin(4)) {
    Serial.println("initialization failed!");
    return;
  }
  Serial.println("initialization done.");
  
  pinMode(A0,INPUT);
  //lcd.begin(16,2);
  //lcd.backlight();

  rtc.begin();

  dht.begin();
  
  Ethernet.begin(mac, ip);
  server.begin();
  Serial.print("server is at ");
  Serial.println(Ethernet.localIP());

}

int contador = 0;

void loop() {
  

    grabarLog();
    contador = 0;
  
  EthernetClient client = server.available();
  if (client) {
    while (client.connected()) {
      if (client.available()) {
        client.print(rtc.getDateStr());
        client.print(",");
        client.print(rtc.getTimeStr());
        client.print(",");        
        client.print(rtc.getTemp());
        client.print(",");
        client.print(dht.readHumidity());        
        client.print(",");     
        client.print(dht.readTemperature());    
        client.print(",");     
        client.print(analogRead(A0));         
        client.stop();     
      }
      
    }
  }
  

  
  contador = contador + 1;

  delay(1000);
}



void grabarLog() {

  myFile = SD.open("sensor.txt", FILE_WRITE);

  myFile.print(rtc.getDateStr());
  myFile.print(",");
  myFile.print(rtc.getTimeStr());
  myFile.print(",");
  myFile.print(rtc.getTemp());
  myFile.print(",");
  myFile.print(dht.readHumidity());
  myFile.print(",");
  myFile.print(dht.readTemperature());  
  myFile.print(",");
  myFile.println(analogRead(A0));  
  myFile.close();  

  Serial.print(rtc.getDateStr());
  Serial.print(",");
  Serial.print(rtc.getTimeStr());
  Serial.print(",");
  Serial.print(rtc.getTemp());
  Serial.print(",");
  Serial.print(dht.readHumidity());
  Serial.print(",");
  Serial.print(dht.readTemperature());
  Serial.print(",");
  Serial.println(analogRead(A0));  

}

void enviarDatos() {
    EthernetClient client = server.available();
    if (client.connect("192.168.1.101", 80)) {
    Serial.println("connected");
    // Make a HTTP request:
    
    String link = "GET /temperatura/temp.php?hora=";
    link += rtc.getTimeStr();
    link += "&temp=";
    
    client.print(link);
    client.print(rtc.getTemp());
    client.print("&humedad=");
    client.print(dht.readHumidity());
    client.print("&temp2=");
    client.print(dht.readTemperature());
    //client.println("Host: www.google.com");
    client.println("Connection: close");
    client.println();
  } 
  else {
    // kf you didn't get a connection to the server:
    Serial.println("connection failed");
  }
  
}



