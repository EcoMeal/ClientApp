package ecomeal.client.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import ecomeal.client.entity.Order;

public class QRCodeEncoder {

	public static byte[] encodeObjectToString(Order order) {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(order);
			out.flush();
			byte[] data = bos.toByteArray();
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}   
		
		/*FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(order.toString());
		oos.close();*/
		//return Base64.getEncoder().encodeToString(baos.toByteArray());
	}
	
	
	public static Object decodeStringToObject(byte[] data) {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is;
		try {
			is = new ObjectInputStream(in);
			return is.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		/*byte [] data = Base64.getDecoder().decode(string);
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
		}*/
	}
	
	
	public static BitMatrix generateMatrix(final String data) {
		 try {
			return new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, 400, 400);
		} catch (WriterException e) {
			System.err.println("Failed to generate matrix");
			return null;
		}
	}
	
}
