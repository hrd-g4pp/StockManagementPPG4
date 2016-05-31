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
    public static ArrayList<ProductG4> arrSearch=null;
    static Scanner sc=new Scanner(System.in);
    String fileName="src/file/record/record1.ppg4";
    String fileSetName="src/file/rowset/RowSetShow.row";
    int rowSet=readRow(fileSetName);
    int startRow=1;
    int stopRow=startRow+rowSet-1;
    int currentPage=1;
    int maxPage=1;
    public static boolean seachVisible=false;
    
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
        File fileNa=new File(fileName);
        if(!fileSet.exists()){
            fileSet.createNewFile();
            rowSet=5;
            //Write to rowset fiel....
            
            stopRow=startRow+rowSet-1;
        }
        if(!fileNa.exists()){
            fileNa.createNewFile();         
        }
    
        	bis=new BufferedInputStream( new FileInputStream(fileNa));
            try(ObjectInputStream obs=new ObjectInputStream(bis)){
                arrTemp = (ArrayList<ProductG4>) obs.readObject();
                maxPage=arrTemp.size()/rowSet;  
                
                if((maxPage*rowSet)<arrTemp.size()){
                    maxPage+=1;
                }

        	    
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
        if(!seachVisible){
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
            	e.printStackTrace();
            }
        }
        else{

        	try
            {
                System.out.println("\n\n");
                String leftAlignFormat = "| %-20s | %-20s | %-20s | %-20s | %-20s |%n";
                System.out.format("+----------------------+----------------------+----------------------+----------------------+----------------------+%n");
                System.out.format("|          ID          |        Name          |         Price        |        Qty           |         Date         |%n");
                System.out.format("+----------------------+----------------------+----------------------+----------------------+----------------------+%n");
                for (int i=0;i<arrSearch.size();i++) {
                    ProductG4 p = arrSearch.get(i);
                    System.out.format(leftAlignFormat, checkRecordLength(p.getId()+"") ,checkRecordLength(p.getName()),checkRecordLength(p.getQty()+""),checkRecordLength(p.getPrice()+""),checkRecordLength(p.getDate()));
                    System.out.format("+----------------------+----------------------+----------------------+----------------------+----------------------+%n");                    
                } 
            
                System.out.format("+ Page: "+currentPage+" / "+maxPage+"                                                                            Total Record: "+arrSearch.size()+"%n");
                System.out.format("+----------------------+----------------------+----------------------+----------------------+----------------------+%n");
            } catch (NullPointerException | IndexOutOfBoundsException |ClassCastException e ) {
            	e.printStackTrace();
            }
        	
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
        if((maxPage*rowSet)<arrTemp.size()){
            maxPage+=1;
        } 
        
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
    	if((maxPage*rowSet)<arrTemp.size()){
            maxPage+=1;
        }
    	
    	currentPage=maxPage;  
	    startRow=(currentPage*rowSet)-(rowSet-1); 
	    stopRow=arrTemp.size();
	    
	    
        display();
    }
        
    public void prevoius(){
        currentPage-=1;  
        startRow=(currentPage*rowSet)-(rowSet-1);
        if(currentPage<1){
        	startRow=1;
        	currentPage=1;
        }
        stopRow=startRow+rowSet-1;
        display();
    }
    public void next(){
    	if((maxPage*rowSet)<arrTemp.size()){
            maxPage+=1;
        }
    	
        currentPage+=1;  
        startRow=(currentPage*rowSet)-(rowSet-1); 
        if(stopRow==arrTemp.size()){
        	last();
        }
        else if(stopRow>arrTemp.size()){
        	startRow=1;
        	currentPage=1;
        	 display();
        }
        else{
        	startRow=(currentPage*rowSet)-(rowSet-1); 	
            stopRow=startRow+rowSet-1;
            display();
        }
        
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
      //  System.out.println("ArrTemp before write "+arrTemp.size());
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
        
        ProductG4 obj=new ProductG4(id,name,price,qty,date,des); 
        arrTemp.add(obj);
        
    //    System.out.println("ArrTemp before write "+arrTemp.size());
        
        maxPage=arrTemp.size()/rowSet;
        if((maxPage*rowSet)<arrTemp.size()){
            maxPage+=1;
        } 
        
        try{
            FileOutputStream fos = new FileOutputStream(fileSetName);
            fos.write(rowSet);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
                
        Collections.reverse(arrTemp);
        display();
    }  
    public void save() throws InterruptedException, IOException{
    	//int seprateFile=10;
//        int rowcount = 1;
//        ArrayList<ProductG4>[] arrRecord = new ArrayList[seprateFile];        
//        for (int i = 0; i < seprateFile; i++) {
//            arrRecord[i] = new ArrayList<>(1_000);
//            for (int j = 0; j < 1_000; j++) {
//                arrRecord[i]=arrTemp.get(j);
//                rowcount++;
//            }
//            System.gc();
//        }     
//        ThreadSave th=new ThreadSave();
//        th.save(arrTemp, seprateFile);
    	
    	FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ObjectOutputStream oos = null;
        Collections.reverse(arrTemp);
        try{
            fos = new FileOutputStream(fileName);
            
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(arrTemp);
            Collections.reverse(arrTemp);
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
    public void read(){
    	boolean found=false;
    	System.out.print("Product ID: ");
    	id=sc.nextInt();
    	for(int  i=0;i<arrTemp.size();i++){
        	if(id==arrTemp.get(i).getId()){
        		
        		name=arrTemp.get(i).getName();
        		qty=arrTemp.get(i).getQty();
        		price=arrTemp.get(i).getPrice();
        		date=arrTemp.get(i).getDate();
        		des=arrTemp.get(i).getDes();
        		found=true;
        		break;
        		//Put something here for update this method....................
        	}
        }
    	
    	if(found){
    		System.out.println("\n\n\nProduct Detail: ");
        	System.out.println("Last Update: "+date);
        	System.out.println("Product ID: "+id);
        	System.out.println("Product Name: "+name);
        	System.out.println("Product Price: "+price);
        	System.out.println("Product Qty: "+qty);
        	System.out.println("Product Description: "+des); 
    	}
    	else{
    		System.out.println("No record found!"); 
    	}
    }  
    public void update(){
    	boolean found=false;
    	System.out.print("Product ID: ");
    	id=sc.nextInt();
    	for(int  i=0;i<arrTemp.size();i++){
        	if(id==arrTemp.get(i).getId()){
        		
        		name=arrTemp.get(i).getName();
        		qty=arrTemp.get(i).getQty();
        		price=arrTemp.get(i).getPrice();
        		date=arrTemp.get(i).getDate();
        		des=arrTemp.get(i).getDes();
        		found=true;
        		break;
        	}
        	else{  
        		
        	}
        }
    	if(found){
    		String op;
        	System.out.println("\n\n\nProduct Detail: ");
        	System.out.println("Last Update: "+date);
        	System.out.println("Product ID: "+id);
        	System.out.println("Product Name: "+name);
        	System.out.println("Product Price: "+price);
        	System.out.println("Product Qty: "+qty);
        	System.out.println("Product Description: "+des); 
        	String con;
        	do{
        		System.out.println("\nWhat do you want to update?"); 
            	System.out.println("\n(A)All\t(N)Name\t(Q)Qty)\t(P)Price\t(D)Description\n"); 
            	Scanner sc=new Scanner(System.in);
            	
            	op=sc.nextLine().toLowerCase();
            	if(op.equals("a")){
            		System.out.print("Input Name: ");
                	name=sc.nextLine();
                	System.out.print("Input Qty: ");
                	qty=sc.nextInt();
                	System.out.print("Input Price: ");
                	price=sc.nextDouble();
                	System.out.print("Input Description: ");
                	sc.nextLine();
                	des=sc.nextLine();
            	}
            	else if(op.equals("n")){
            		System.out.print("Input Name: ");
                	name=sc.nextLine();
            	}
            	else if(op.equals("q")){
            		System.out.print("Input Qty: ");
                	qty=sc.nextInt();
            	}
            	else if(op.equals("p")){
            		System.out.print("Input Price: ");
                	price=sc.nextDouble();
            	}
            	else if(op.equals("d")){
            		System.out.print("Input Description: ");
                	des=sc.nextLine();
            	}
            	else{
            		System.out.println("Wrong Syntax!\n ");
            	}
            	
        	}while(!op.equals("a")&& !op.equals("n")&&!op.equals("q")&&!op.equals("p")&&!op.equals("d"));
        	System.out.print("Are you sure? Y/N");
        	Scanner ss=new Scanner(System.in);
        	con=ss.nextLine().toLowerCase();
        	if(con.equals("y")){
        		for(int  i=0;i<arrTemp.size();i++){
                	if(id==arrTemp.get(i).getId()){
                		arrTemp.get(i).setName(name);
                		arrTemp.get(i).setQty(qty);
                		arrTemp.get(i).setPrice(price);
                		arrTemp.get(i).setDes(des);
                		break;
                		//Put something here for update this method....................
                	}
                }
        	}
        	System.out.println("\nUpdated successful ");
    	}
    	else{
    		System.out.println("\nNo record found!");
    	}
        
    }  
    public void delete(){
    	boolean found=false;
    	System.out.print("Product ID: ");
    	id=sc.nextInt();
    	for(int  i=0;i<arrTemp.size();i++){
        	if(id==arrTemp.get(i).getId()){        		
        		name=arrTemp.get(i).getName();
        		qty=arrTemp.get(i).getQty();
        		price=arrTemp.get(i).getPrice();
        		date=arrTemp.get(i).getDate();
        		des=arrTemp.get(i).getDes();
        		found=true;
        		break;
        	}
        	else{  
        		
        	}
        }
    	if(found){
        	System.out.println("\n\n\nProduct Detail: ");
        	System.out.println("Last Update: "+date);
        	System.out.println("Product ID: "+id);
        	System.out.println("Product Name: "+name);
        	System.out.println("Product Price: "+price);
        	System.out.println("Product Qty: "+qty);
        	System.out.println("Product Description: "+des); 
        	String con;
        	do{
        		
	        	System.out.print("Are you sure? Y/N");
	        	Scanner ss=new Scanner(System.in);
	        	con=ss.nextLine().toLowerCase();
	        	if(con.equals("y")){
	        		for(int  i=0;i<arrTemp.size();i++){
	                	if(id==arrTemp.get(i).getId()){ 
	                		for (int j =arrTemp.size()-1; j >= 0; j--) {
	                		    arrTemp.remove(arrTemp.get(i));
	                		    
	                		 //   System.out.println("Array to delete is :"+arrTemp.get(i));
	                		    break;
	                		}
	                		break;
	                	}
	                }
	        		maxPage=arrTemp.size()/rowSet;
	                if((maxPage*rowSet)<arrTemp.size()){
	                    maxPage+=1;
	                }
	                currentPage=maxPage;  
	                startRow=(currentPage*rowSet)-(rowSet-1); 
	                stopRow=startRow+rowSet-2;
	             //   startRow=(currentPage*rowSet)-(rowSet-1); 
	              //  stopRow-=1;
	                
	        		System.out.println("\nDeleted successful ");
	        	}
	        	else if(con.equals("n")){
	        		System.out.println("\nRecord not deleted!");
	        	}
	        	else{
	        		System.out.println("\nWrong syntax!");
	        	}
        	
        	}while(!con.equals("y")&& !con.equals("n"));
    	}
    	else{
    		System.out.println("\nNo record found!");
    	}
    }
    public void search(){
    	boolean searchFound=false;
    	arrSearch=new ArrayList<ProductG4>();
    	String nameSearch;
    	System.out.print("Name to search: ");
    	Scanner ss=new Scanner(System.in);
    	nameSearch=ss.nextLine().toLowerCase();
    	
    	for(int i=0;i<arrTemp.size();i++){
        	if(arrTemp.get(i).getName().toLowerCase().contains(nameSearch)){
        		arrSearch.add(arrTemp.get(i));
        		searchFound=true;
        	}
        	else{
        	//	searchFound=false;
        	}
        }
    	if(searchFound){
    		seachVisible=true;
    		display();
    	}
    	else{
    		System.out.println("\nNo record found!\n");
    	}
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
