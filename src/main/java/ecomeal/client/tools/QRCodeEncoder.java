package ecomeal.client.tools;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

import ecomeal.client.entity.Order;

public class QRCodeEncoder {

	public static void encodeObjectToString(String file, Order order) throws IOException {
		System.err.println("Order = " + order);
		FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(order.toString());
		oos.close();
		//return Base64.getEncoder().encodeToString(baos.toByteArray());
	}
	
	
	public static Object decodeStringToObject(String string) {
		byte [] data = Base64.getDecoder().decode(string);
        ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new ByteArrayInputStream(data));
			Object o  = ois.readObject();
			ois.close();
			return o;
		} catch (IOException e) {
			// TODO : Add error log
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO : Add error log
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static BitMatrix generateMatrix(final String filename) {
		 try {
			 byte[] data = Files.readAllBytes(Paths.get(filename));
			return new QRCodeWriter().encode(new String(data), BarcodeFormat.QR_CODE, 400, 400);
		} catch (WriterException e) {
			System.err.println("Failed to generate matrix");
			return null;
		} catch (IOException e) {
			System.err.println("Encoded data file not found");
			return null;
		}
	}
	
}
