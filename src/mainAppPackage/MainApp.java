package mainAppPackage;
import java.io.FileNotFoundException;
import java.io.IOException;
public class MainApp  {
    public static void main(String[] arg) throws ClassNotFoundException, FileNotFoundException, InterruptedException, IOException{		
//        TestWrite1000 wr=new TestWrite1000();
//        wr.write1000();
    
        
        mainMenu main=new mainMenu();
        main.displayMenu();
        
        
    }
}