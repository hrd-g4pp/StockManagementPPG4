package readwrite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TestWrite10000000 {
    Date d=new Date();
    private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private String date=sdf.format(d);
    public void write10000000Record() throws InterruptedException{
              
        
        int seprateFile=10;
        int rowcount = 1;
        
        
        ArrayList<record.DataRecord>[] arrRecord = new ArrayList[seprateFile];        
        for (int i = 0; i < seprateFile; i++) {
            arrRecord[i] = new ArrayList<>(1_0);  //1_000_000
            for (int j = 0; j < 1_0; j++) {
                arrRecord[i].add(new record.DataRecord(rowcount, "A" + rowcount, rowcount + 1, rowcount + 2, date));
                rowcount++;
            }
            System.gc();
        }
     

        ThreadSaveToFile th=new ThreadSaveToFile();
        th.save(arrRecord, seprateFile);
    }
}
