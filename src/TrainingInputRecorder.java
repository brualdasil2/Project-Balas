import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.Buffer;

public class TrainingInputRecorder {
	private byte[] gameInputs;
	private int currentIndex = 0;
	private int inputsSize = -1;
	private boolean recording = false;

	
	private byte[] longToBytes(long x) {
	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.putLong(x);
	    return buffer.array();
	}

	private long bytesToLong(byte[] bytes) {
	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.put(bytes);
	    ((Buffer) buffer).flip();//need flip 
	    return buffer.getLong();
	}
	
	
	public void startRecording() {
		gameInputs = new byte[100000];
		currentIndex = 0;
		recording = true;
		System.out.println("started rec");
	}
	
	private byte encodeCharacter(Character character) {
		byte charData = (byte)0x00;
		if (character instanceof Bruno) {
			charData = (byte)0x00;
		}
		else if (character instanceof Carol) {
			charData = (byte)0x01;
		}
		if (character instanceof Lacerda) {
			charData = (byte)0x02;
		}
		if (character instanceof Obino) {
			charData = (byte)0x03;
		}
		charData = (byte)(charData << 1);
		byte skinData = (byte)character.getSkin();
		charData |= skinData;
		return charData;
	}
	
	public void stopRecording() {
		inputsSize = currentIndex;
		recording = false;
		currentIndex = 0;
		System.out.println("ended rec. size: " + inputsSize);
	}
	
	public byte getFrameInputs() {
		byte inputs = gameInputs[currentIndex];
		currentIndex++;
		if (currentIndex == inputsSize) {
			currentIndex = 0;
		}
		return inputs; 
	}
	
	public void recordInputs(SmashPlayer p2) {
		
		boolean p2PressingJump = p2.pressingJump;
		boolean p2PressingAttack = p2.pressingAttack;
		boolean p2PressingSpecial = p2.pressingSpecial;
		boolean p2PressingUp = p2.pressingUp;
		boolean p2PressingShield = p2.pressingShield;
		boolean p2PressingAirdash = p2.pressingAirdash;
		boolean p2PressingLeft = p2.pressingLeft;
		boolean p2PressingRight = p2.pressingRight;
		
		byte[] p2InputsArray = new byte[8];
		
		p2InputsArray[0] = (byte) (p2PressingJump ? 0x80 : 0x00);
		p2InputsArray[1] = (byte) (p2PressingAttack ? 0x40 : 0x00);
		p2InputsArray[2] = (byte) (p2PressingSpecial ? 0x20 : 0x00);
		p2InputsArray[3] = (byte) (p2PressingUp ? 0x10 : 0x00);
		p2InputsArray[4] = (byte) (p2PressingShield ? 0x08 : 0x00);
		p2InputsArray[5] = (byte) (p2PressingAirdash ? 0x04 : 0x00);
		p2InputsArray[6] = (byte) (p2PressingLeft ? 0x02 : 0x00);
		p2InputsArray[7] = (byte) (p2PressingRight ? 0x01 : 0x00);
		
		byte p2InputsByte = 0x00;
		for (byte b : p2InputsArray) {
			p2InputsByte |= b;
		}
		
		gameInputs[currentIndex] = p2InputsByte;
		System.out.println("recd byte " + p2InputsByte);
		currentIndex++;
	}
	
	public boolean isRecording() {
		return recording;
	}
	
	public boolean hasRecordingSaved() {
		return (inputsSize != -1);
	}
}
