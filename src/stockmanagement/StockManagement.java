package stockmanagement;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import mainAppPackage.FilePath;
import readwrite.TestWrite10000000;
import readwrite.ThreadReadFromFile;
import record.DataRecord;

public class StockManagement {
    public  ArrayList<DataRecord> arrReadWrite=new ArrayList<DataRecord>();
    public  ArrayList<DataRecord> arrDisplay=new ArrayList<DataRecord>();
    private File f;
    private FileInputStream fis;
    private BufferedInputStream bis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private BufferedOutputStream bos;
    private ObjectOutputStream oos;
    
    private Date d=new Date();
    private int id=1;
    private String name;
    private double price;
    private long qty;
    private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private String date=sdf.format(d);
    private Scanner sc=new Scanner(System.in);
    public static int recordSparete=1000000000;
    
    public void readFile() throws Exception
    {    
        f=new File("src/file/record/record1.ppg4");
        fis=new FileInputStream(f);
        bis=new BufferedInputStream(fis);
        
        //We need to catch EOF error. Because sometime our file has no record.
        try {
            ois=new ObjectInputStream(bis);
            arrReadWrite=(ArrayList<DataRecord>)ois.readObject();
        } catch (EOFException e) {
        }
    }
  
    

    public void writeFile() throws Exception,NullPointerException
    {        
        f=new File(FilePath.FILE_PATH);
        
        //Read Object
     //   readFile();
     
     fos=new FileOutputStream(f);
       bos=new BufferedOutputStream(fos);
       oos=new ObjectOutputStream(bos);
     
       if(arrReadWrite.size()>0)
       {
            recordSparete=arrReadWrite.size();
       }
        
//        Write Object
//        
//                    
//        Get ID        
        try {
            for(int i=0;i<arrReadWrite.size();i++)
            {
                DataRecord obj=arrReadWrite.get(i);
                String st[]={obj.getId()+""};
                id=Integer.valueOf(st[0])+1;
            }
            
        } catch (NullPointerException e) {
        }


    DataRecord objRecord;
    for(int i=1;i<=10_000_00;i++){
        objRecord=new DataRecord(id,"C"+i,30.10,100,date);
        arrReadWrite.add(objRecord);
        id++;
    }
    
        oos.writeObject(arrReadWrite);       
        System.out.println("Write Complete with "+arrReadWrite.size()+" records");
        oos.close();
        bos.close();
        fos.close();
    }
    public static void main(String[] args) throws Exception {
        StockManagement s=new StockManagement();  

      //  s.readFile();
     //s.displayRecord();
      //  s.writeFile();
     //   Thread.sleep(100000);
      //  s.displayRecord();
     //  s.displayMenu();
      
       // s.displayRecordBy(false, false, false, false, true);
   //   s.readFile();
     //   s.displayRecord();
     //   s.displayRecord();
    // DisplayRecord d=new DisplayRecord();
    // d.displayRecord();
    
    
     
        
//    
//        ThreadReadFromFile r=new ThreadReadFromFile();
//        try {
//            r.read();
//        } catch (NullPointerException e) {
//        }catch(Exception e){
//            
//        }

    //    TestWrite10000000 obj=new TestWrite10000000();
    //    obj.write10000000Record();
        
       
        
    }
}
