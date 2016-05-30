
package readwrite;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import record.DataRecord;
public class ThreadSaveToFile extends Thread{
    private ArrayList<DataRecord> arrRecord;
    private File file;
    public ThreadSaveToFile(){}    
    public ThreadSaveToFile(ArrayList<DataRecord> arrRecord, File file)
    {
        this.arrRecord = arrRecord;
        this.file = file;
    }
    @Override
    public void run(){
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
          //  System.out.println(this.arrRecord.size());     //To size of record 
            fos = new FileOutputStream(this.file);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this.arrRecord);
            //System.gc();
        } catch (IOException ex) {
        } finally {
            try {
                oos.close();
                bos.close();
                fos.close();
                System.gc();
                System.out.println(this.getName());     //To getName of thread is running...
            } catch (IOException ex) {
            }catch(NullPointerException e){
                
            }
        }
    }
    public void save(ArrayList<DataRecord> []arrRecords,int seprateFile) throws InterruptedException{        
        ThreadSaveToFile[] s = new ThreadSaveToFile[seprateFile];
        for (int i = 0; i <seprateFile; i++) {
            s[i] = new ThreadSaveToFile(arrRecords[i], new File("src/file/record/record" + (i+1) + ".ppg4"));
            s[i].start();
            s[i].join();
        }
    }
}
