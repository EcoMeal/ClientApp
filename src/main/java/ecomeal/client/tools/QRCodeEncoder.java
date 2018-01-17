package ecomeal.client.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

public class QRCodeEncoder {

	public static String encodeObjectToString(Object object) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.close();
			return Base64.getEncoder().encodeToString(baos.toByteArray());
		} catch (IOException e) {
			// TODO : Add error log
			e.printStackTrace();
			return null;
		}
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
