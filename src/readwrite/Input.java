package readwrite;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Input {    
    private File f;
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
    public  ArrayList<record.DataRecord> arrInput=new ArrayList<record.DataRecord>();
    
    public void Write() throws FileNotFoundException, IOException{
        f=new File("record/record1.ppg4");
        fos=new FileOutputStream(f);
        bos=new BufferedOutputStream(fos);
        oos=new ObjectOutputStream(bos);
        
        try {
            for(int i=0;i<arrInput.size();i++)
            {
                record.DataRecord obj=arrInput.get(i);
                String st[]={obj.getId()+""};
                id=Integer.valueOf(st[0])+1;
            }
        } catch (NullPointerException e) {
        }
        
        
        System.out.println("\n\nCreating new record...");
        System.out.println("ID:"+id);
        System.out.print("Name: ");
        name=sc.nextLine();
        System.out.print("Price: ");           
        price=sc.nextDouble();
        System.out.print("Qty: ");           
        qty=sc.nextLong();
        
        record.DataRecord obj=new record.DataRecord(id,name,price,qty,date); 
        arrInput.add(obj);
        oos.writeObject(arrInput);       
        System.out.println("Write Complete with "+arrInput.size()+" records");
        oos.close();
        bos.close();
        fos.close();
    }
}
