package frc.robot.lib.drivers;

import java.util.concurrent.ThreadLocalRandom;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NeoPixel extends SubsystemBase {
	private static AddressableLED led;
	private static AddressableLEDBuffer ledBuffer;
	private int[] rgb = new int[15];
  	boolean isRGB = false;
  	int breathe = 255;
  	boolean breatheReversed = false;
  	int breatheH = 10;
  	int blinkCount = 0; 

	public NeoPixel(int dataPort, int ledLength) {
		led = new AddressableLED(dataPort);
		ledBuffer = new AddressableLEDBuffer(ledLength);
		led.setLength(ledBuffer.getLength());
		//setColor(0, 0, 0);
		//toggleRGB();
		//breathe();
		//showPercentage(0.5);
		//blink(0, 255, 0);
		led.start();
	}
	@Override
	public void periodic() {
		//toggleRGB();
		//breathe();
		//blink(0, 255, 0);
	}
	public static void setColor(int r, int g, int b) {
		for (int i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setRGB(i, r, g, b);
		}
		led.setData(ledBuffer);
	}
	public void turnOff() {
		setColor(0, 0, 0);
		led.setData(ledBuffer);
	}

	public void toggleRGB(){
		if(!isRGB){
			for (int i = 0; i < ledBuffer.getLength(); i++) {
				rgb[i] = i * 180/ledBuffer.getLength();
			}
			isRGB = true;
		}
		shiftArray(rgb);

		for (int i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, rgb[i], 255, 255); 
		}
		led.setData(ledBuffer);
	}

	public int[] shiftArray(int[] array){
		int last = array[array.length-1];
		for(int i = array.length-1; i > 0; i--){
			array[i] = array[i-1];
		}
		array[0] = last;
		return array;
	}
	public void setRandom(){
		for (int i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, (int) (Math.random()*254+1), 255, 255); 
		}
		led.setData(ledBuffer);
	}
	public void breathe(){
		for (int i = 0; i < ledBuffer.getLength(); i++) {
			ledBuffer.setHSV(i, breatheH, 255, breathe); 
		}
		if(!breatheReversed){
			breathe -= 3;
			if(breathe == 0){
			breatheReversed = true;
			breatheH =  ThreadLocalRandom.current().nextInt(1, 256);
			}
		}
		if(breatheReversed){
			breathe += 3;
			if(breathe == 255) breatheReversed = false;
		}
		led.setData(ledBuffer);
	}

	public void initialize(){
	
	}

	private void blink(int r, int g, int b) {
		int blinkRate = 6;
		if(blinkCount <= blinkRate) setColor(r, g, b);
		else if ( blinkCount <= blinkRate*2) setColor(0, 0, 0);
		blinkCount++;
		if( blinkCount > blinkRate*2 || blinkCount <= 0) blinkCount = 1;
	}
	public void showPercentage(double percent) {
		int LEDCount = (int) Math.round(percent * 7.);
		int emptyLEDCount = 7 - LEDCount;
		for (int i = 0; i < ledBuffer.getLength(); i++) {
			if(i+1 <= emptyLEDCount || i+1 >= 15-emptyLEDCount) ledBuffer.setRGB(i, 153, 0, 0);
			else ledBuffer.setRGB(i, 0, 153, 51);
		}
		led.setData(ledBuffer);
	}
}
