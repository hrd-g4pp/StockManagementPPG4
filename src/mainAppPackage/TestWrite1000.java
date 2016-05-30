package mainAppPackage;

import java.io.IOException;
import readwrite.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class TestWrite1000 {
    Date d=new Date();
    private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private String date=sdf.format(d);
    public void write1000() throws InterruptedException, IOException{
        int seprateFile=10;
        int rowcount = 1;
        ArrayList<ProductG4>[] arrRecord = new ArrayList[seprateFile];        
        for (int i = 0; i < seprateFile; i++) {
            arrRecord[i] = new ArrayList<>(1_000);
            for (int j = 0; j < 1_000; j++) {
                arrRecord[i].add(new ProductG4(rowcount, "CC" + rowcount, rowcount + 1, rowcount + 2, date));
                rowcount++;
            }
            System.gc();
        }     
        ThreadSave th=new ThreadSave();
        th.save(arrRecord, seprateFile);
    }
}
