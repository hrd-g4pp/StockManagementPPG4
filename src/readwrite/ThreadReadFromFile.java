
package readwrite;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import record.DataRecord;

public class ThreadReadFromFile extends Thread{
    private ArrayList<DataRecord> arrRecord;
    private File file;
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    ObjectInputStream ois = null;
    ArrayList<record.DataRecord>[] arrRead=null;
    public ThreadReadFromFile(){}    
    public ThreadReadFromFile(ArrayList<DataRecord> arrRecord, File file)
    {
        this.arrRecord = arrRecord;
        this.file = file;
    }
    @Override
    public void run(){
//        FileInputStream fis = null;
//        BufferedInputStream bis = null;
//        ObjectInputStream ois = null;
        try {
          //  System.out.println(this.arrRecord.size());     //To size of record 
            fis = new FileInputStream(this.file);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);
        //    ois.readObject(this.arrRecord);
          arrRecord=(ArrayList<DataRecord>)ois.readObject();
            //System.gc();
            System.out.println("Finished....");
        } catch (IOException | ClassNotFoundException ex) {
        } finally {
            try {
                ois.close();
                bis.close();
                fis.close();
                System.gc();
                System.out.println(this.getName());     //To getName of thread is running...
            } catch (IOException ex) {
            }catch(NullPointerException e){
                
            }
        }
    }
    public void read(ArrayList<DataRecord> []arrRecords,int seprateFile) throws InterruptedException, IOException, ClassNotFoundException,NullPointerException{        
      //  ArrayList<record.DataRecord>[] arrRead = new ArrayList[seprateFile];     
      arrRead=new ArrayList[10];
        for (int i = 0; i < seprateFile; i++) {
            arrRead[i] = new ArrayList<>(1_000_000);
            for (int j = 0; j < 1_000_000; j++) {
                try {
                    arrRead[i]=(ArrayList<DataRecord>)ois.readObject();
                } catch (NullPointerException e) {
                }catch(Exception e){
                    
                }
            }
            System.gc();
        }
        
        
        
        
        ThreadReadFromFile[] s = new ThreadReadFromFile[seprateFile];
        
        
        
        for (int i = 0; i <seprateFile; i++) {
            try {
                s[i] = new ThreadReadFromFile(arrRecords[i], new File("src/file/record/record" + (i+1) + ".ppg4"));
            } catch (NullPointerException e) {
            }catch(Exception e){
                
            }
            s[i].start();
            s[i].join();
        }
        
        
    }
    public void read() throws InterruptedException, IOException, ClassNotFoundException{
        try {
            read(arrRead, 10);
        } catch (NullPointerException e) {
        }catch(Exception e){
            
        }
    }
}
