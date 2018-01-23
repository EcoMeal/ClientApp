package ecomeal.client.tools;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import com.google.zxing.WriterException;
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
        System.err.println("before write");
		oos.writeObject(order.toString());
		System.err.println("after write");
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
	
	
	public static ByteMatrix generateMatrix(final String data) {
		QRCode qr = new QRCode();
        try {
			Encoder.encode(data, ErrorCorrectionLevel.L, qr);
			ByteMatrix matrix = qr.getMatrix();
			return matrix;
		} catch (WriterException e) {
			// TODO : Add error log
			e.printStackTrace();
			return null;
		}
	}
	
}
