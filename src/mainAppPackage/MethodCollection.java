package mainAppPackage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
public class MethodCollection {
    public static ArrayList<ProductG4> arrTemp =null;
    static Scanner sc=new Scanner(System.in);
    String fileName="src/file/record/record1.ppg4";
    String fileSetName="src/file/rowset/RowSetShow.row";
    int rowSet=readRow(fileSetName);
    int startRow=1;
    int stopRow=startRow+rowSet-1;
    int currentPage=1;
    int maxPage=1;
    
    private int id=1;
    private String name;
    private double price;
    private long qty;
    private String des;
    
    private File f;
    private FileOutputStream fos;
    private static  BufferedOutputStream bos;
    private static  BufferedInputStream bis; 
    private static ObjectOutputStream oos=null;
    
    private Date d=new Date();
    private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private String date=sdf.format(d);
    ProductG4 prd=new ProductG4();	
 
    public MethodCollection() throws FileNotFoundException, ClassNotFoundException, IOException{
        File fileSet=new File(fileSetName);
        if(!fileSet.exists()){
            fileSet.createNewFile();
            rowSet=5;
            //Write rowset to File.
            stopRow=startRow+rowSet-1;
        }
        
        bis=new BufferedInputStream( new FileInputStream(fileName));
        try(ObjectInputStream obs=new ObjectInputStream(bis)){
            arrTemp = (ArrayList<ProductG4>) obs.readObject();
            maxPage=arrTemp.size()/rowSet; 
            Collections.reverse(arrTemp); 
        }catch(IOException e){
        }
    }

    public String checkRecordLength(String Record){
        String newRecord=  null;
	String oldRecord = null;
	if(Record.length()>20){
            oldRecord = Record.substring(0, 17);
            newRecord = oldRecord + "...";
        }
        else
        {
            newRecord = Record;
        }	
        return newRecord;			
    }
    public void display()
    {                                  
        try
        {
            System.out.println("\n\n");
            String leftAlignFormat = "| %-20s | %-20s | %-20s | %-20s | %-20s |%n";
            System.out.format("+----------------------+----------------------+----------------------+----------------------+----------------------+%n");
            System.out.format("|          ID          |        Name          |         Price        |        Qty           |         Date         |%n");
            System.out.format("+----------------------+----------------------+----------------------+----------------------+----------------------+%n");
            for (int i=startRow;i<=stopRow;i++) {
                ProductG4 p = arrTemp.get(i-1);
                System.out.format(leftAlignFormat, checkRecordLength(p.getId()+"") ,checkRecordLength(p.getName()),checkRecordLength(p.getQty()+""),checkRecordLength(p.getPrice()+""),checkRecordLength(p.getDate()));
                System.out.format("+----------------------+----------------------+----------------------+----------------------+----------------------+%n");                    
            } 
        
            System.out.format("+ Page: "+currentPage+" / "+maxPage+"                                                                            Total Record: "+arrTemp.size()+"%n");
            System.out.format("+----------------------+----------------------+----------------------+----------------------+----------------------+%n");
        } catch (NullPointerException | IndexOutOfBoundsException |ClassCastException e ) {
        }
//        System.out.println("arrTemp lenght : "+arrTemp.size());
//        System.out.println("Row Set: "+rowSet);
//        System.out.println("Start Row: "+startRow);
//        System.out.println("Stop Row: "+stopRow);
//        System.out.println("Current Page: "+currentPage);
//        System.out.println("Max Page: "+maxPage);
    }
    
    public  int readRow(String fname){
        int num=0;
        try{
            FileInputStream ips = new FileInputStream(fname);
            num=ips.read();
        }catch(IOException  e){
        }
        return num;
    }
    
    public void setRow() throws FileNotFoundException, ClassNotFoundException{
        System.out.print("Number of Row to Display:");
        rowSet=sc.nextInt();
        maxPage=arrTemp.size()/rowSet;
        try{
            FileOutputStream fos = new FileOutputStream(fileSetName);
            fos.write(rowSet);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        startRow=(currentPage*rowSet)-(rowSet-1); 
        stopRow=startRow+rowSet-1;          
        display();
        display();
    }
    public void GotoPage() throws FileNotFoundException, ClassNotFoundException{
        System.out.print("Goto Page:");
        currentPage=sc.nextInt();
        startRow=(currentPage*rowSet)-(rowSet-1); 
        stopRow=startRow+rowSet-1;          
        display();
    }
	    
    public void first(){
        currentPage=1; 
        startRow=(currentPage*rowSet)-(rowSet-1); 
        stopRow=startRow+rowSet-1;
        display();
    }
    public void last()
    {
        currentPage=maxPage;  
        startRow=(currentPage*rowSet)-(rowSet-1); 
        stopRow=startRow+rowSet-1;
        display();
    }
        
    public void prevoius(){
        currentPage-=1;  
        startRow=(currentPage*rowSet)-(rowSet-1); 
        stopRow=startRow+rowSet-1;
        display();
    }
    public void next(){
        currentPage+=1;  
        startRow=(currentPage*rowSet)-(rowSet-1); 
        stopRow=startRow+rowSet-1;
        display();
    }
    public static void backup()throws FileNotFoundException, IOException{
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy   HH-mm-ss");
        Date d = new Date();
        String date=df.format(d);
        File f=new File("src/file/backup/Backup ("+date+").backup");
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
            }
        }
        bos=new BufferedOutputStream( new FileOutputStream(f));
        oos=new ObjectOutputStream(bos);
        try{
            
            oos.writeObject(arrTemp);
            System.out.println("Backup Complate!");            
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
                oos.close();
                bos.close();
            } catch (Exception e) {
            }
        }        
    }
	  
    public void restore(){
	int ch2;
     //   arrTemp=null;
        File f1 = new File("src/file/Backup//");
        String[] fileName = f1.list();
        System.out.println("All availabe restore file!");
        for (int i=1;i<=fileName.length;i++){
            System.out.println(i+", "+fileName[i-1]);
        }
        System.out.print("Please input file number : ");
        ch2=sc.nextInt()-1;
        long startretore = System.currentTimeMillis();
   //     arrTemp=FileConnection.readData("src/file/Backup//"+fileName[ch2]);
    //    FileConnection.writeData("File/temp.bin",arrTemp);
        long stopretore = System.currentTimeMillis();
        System.out.println(fileName[ch2]);
        System.out.println(" Number of Object :  "+arrTemp.size()+"  Spend Time  : "+(stopretore -startretore) +" Nano");
        System.out.println("Restore Completed!\n");	
    }  
        
    public void write() throws FileNotFoundException, IOException{
        f=new File(fileName);
        fos=new FileOutputStream(f);
        bos=new BufferedOutputStream(fos);
        oos=new ObjectOutputStream(bos);
        
        try {
            Collections.reverse(arrTemp);
        } catch (NullPointerException e) {
        }
        
        try {
            for(int i=0;i<arrTemp.size();i++)
            {
                ProductG4 obj=arrTemp.get(i);
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
        System.out.print("Description: ");
        sc.nextLine();
        des=sc.nextLine();
        
        ProductG4 obj=new ProductG4(id,name,price,qty,date); 
        arrTemp.add(obj);
       // oos.writeObject(arrTemp);       
      //  System.out.println("Write Complete with "+arrTemp.size()+" records");
        oos.close();
        bos.close();
        fos.close();
        
    }  
    public void save(){
		
    } 
    public void read(){
	
    }  
    public void update(){
        
    }  
    public void delete(){
        
    }
    public void search(){
        
    }
    public void help(){
        
    }  
    public String exit(String exitConfirm){        
        System.out.println("Are you sure?   Y/N");        
        Scanner sc=new Scanner(System.in);
        exitConfirm=sc.nextLine().toLowerCase();
        if(exitConfirm.equals("y")){
            System.out.println("System Exited!");
            System.exit(0);
        }        
        return exitConfirm;        
    }
    
}
