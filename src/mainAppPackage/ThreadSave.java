package mainAppPackage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class ThreadSave extends Thread{
    private ArrayList<ProductG4> arrRecord;
    private File file;
    private File fRowSet=null;
    public ThreadSave(){}    
    public ThreadSave(ArrayList<ProductG4> arrRecord, File file)
    {
        this.arrRecord = arrRecord;
        this.file = file;
    }
    @Override
    public void run(){
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        
        try{
            fos = new FileOutputStream(this.file);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this.arrRecord);
        } catch (IOException ex) {
        } finally {
            try {
                oos.close();
                bos.close();
                fos.close();
                System.gc();
            } catch (IOException ex) {
            }catch(NullPointerException e){                
            }
        }
    }
    public void save(ArrayList<ProductG4> []arrRecords,int seprateFile) throws InterruptedException, IOException{        
        ThreadSave[] s = new ThreadSave[seprateFile];
        for (int i = 0; i <seprateFile; i++) {
            s[i] = new ThreadSave(arrRecords[i], new File("src/file/record/record" + (i+1) + ".ppg4"));            
            s[i].start();
            s[i].join();
        }
        fRowSet =new File("src/file/rowSet/RowSetShow.row");
        if(!fRowSet.exists()){
            fRowSet.createNewFile();                
        }
    }
}
