#include <Pixy2.h>
#define X_CENTER         (pixy.frameWidth/2)
Pixy2 pixy;
int incomingbyte;
int a, b, c, d;
int constant;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  Serial.print("Starting...\n");
  pixy.init();
  // Turn on both lamps, upper and lower for maximum exposure
  pixy.setLamp(1, 1);
  // change to the line_tracking program.  Note, changeProg can use partial strings, so for example,
  // you can change to the line_tracking program by calling changeProg("line") instead of the whole
  // string changeProg("line_tracking")
  pixy.changeProg("line");
//  pixy.setMode(LINE_MODE_WHITE_LINE); should implement later
  a = 1;
  b = 2;
  c = 3;
  d = 4;
  constant = 7;
  incomingbyte = 0;
}
void loop() {
  // put your main code here, to run repeatedly:
  int8_t res;
  int32_t error; 
  int left, right;
  char buf[96];
  res = pixy.line.getMainFeatures(); // magic method by pixy that gets and follows the main vector
  if(Serial.available() > 0){ // if command calls for data
    incomingbyte = Serial.read(); 
    
    if (res<=0)  // no vector is found
  {
//      Serial.println("no line detected"); // serial monitor
      Serial.write(a); // case 1
        return;
  }
   else if(abs(X_CENTER - pixy.line.vectors->m_x1) > abs(X_CENTER - pixy.line.vectors->m_x0 + constant)){ // veering off to right, want to move left
//    Serial.println("Shifting to the right");
    Serial.write(b); // case 2
    return;
   }
   
   else if(abs(X_CENTER - pixy.line.vectors->m_x0) > abs(X_CENTER - pixy.line.vectors->m_x1) + constant){ // veering off to left, want to move right
//    Serial.println("Shifting to the left");
    Serial.write(c); // case 3
    return;
   }
   else{ // moving straight, don't change anything
//    Serial.println("On Line");
    Serial.write(d); // case 4
    return;
   }
  
  }
 else{ // not calling the command
//    Serial.println("Pixyfollow is not running");  
    return;
    }
}
