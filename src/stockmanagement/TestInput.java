package stockmanagement;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import readwrite.ThreadSaveToFile;
public class TestInput {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException  {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sfdate = new SimpleDateFormat("dd/MM/YYYY");
        String prodate = sfdate.format(date);       
        
        int seprateFile=10;
        int rowcount = 1;
        
        
        ArrayList<record.DataRecord>[] arrRecord = new ArrayList[seprateFile];        
        for (int i = 0; i < seprateFile; i++) {
            arrRecord[i] = new ArrayList<>(1_000_000);
            for (int j = 0; j < 1_000_000; j++) {
                arrRecord[i].add(new record.DataRecord(rowcount, "Pro_Name" + rowcount, rowcount + 1, rowcount + 2, prodate));
                rowcount++;
            }
            System.gc();
        }
     

        ThreadSaveToFile th=new ThreadSaveToFile();
        th.save(arrRecord, seprateFile);

    }
}
