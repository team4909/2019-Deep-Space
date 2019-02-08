package frc.team4909.robot.subsystems.leds;

import edu.wpi.first.wpilibj.I2C;

/* The I2C color codes are as follows:
'r' = Set to red, 'g' = Set to green, 'b' = Set to blue, 'u' = Rainbow, 'c' = Rainbow cycle,
'h' = Chase, 'o' = Off
'r' = 114, 'g' = 103, 'b' = 98, 'u' = 117, 'c' = 99, 'h' = 104, 'o' = 111, 't' = 116, 'p' = 112, 's' = 115
'o' -> 'r' -> 'g' -> 'b' -> 'u' -> 'c' -> 'h' -> 't' -> 'p' -> 's' -> ...
*/

public class Neopixels {

    int pixelPosition = 0;
    public static I2C arduino = new I2C(I2C.Port.kOnboard, 0x08);

}