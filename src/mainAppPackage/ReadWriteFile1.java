package mainAppPackage;

import java.io.*;
import java.util.ArrayList;


public class ReadWriteFile1 {
	private static  BufferedOutputStream bfos;
	private static  BufferedInputStream bfis;
	static ArrayList<ProductG4> data=new ArrayList<ProductG4>();
	//This method use to write data 
	public static void writeData1(String fname,ArrayList<ProductG4> obj) throws FileNotFoundException{
		bfos=new BufferedOutputStream( new FileOutputStream(fname));
		try(ObjectOutputStream obs=new ObjectOutputStream(bfos)){
			obs.writeObject(obj);
		}catch(IOException e){
			e.printStackTrace();
			
		}
	}
	public static ArrayList<ProductG4> readData1(String fname) throws FileNotFoundException, ClassNotFoundException{
		bfis=new BufferedInputStream( new FileInputStream(fname));
		try(ObjectInputStream obs=new ObjectInputStream(bfis)){
			data = (ArrayList<ProductG4>) obs.readObject();
		}catch(IOException e){
			//e.printStackTrace();
			
		}
		return data;
	}
}
