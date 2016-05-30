package mainAppPackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class mainMenu {
    public void menu()
    {
        System.out.println("\n\n\n");
        String tab="%-1s";
        String m1Format =tab+"| %-6s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s | %-10s |%n";
        String m2Format =tab+"| %-6s | %-6s | %-6s | %-6s | %-6s | %-6s | %-6s | %-20s |%n";	       
	String space =tab+"|%-91s|%n";
	String line =tab+"+-------------------------------------------------------------------------------------------+%n";                               
	
        System.out.format(line,"");		
	System.out.format(m1Format,"","*)Display","W)rite", "R)Read","U)date","D)elete","F)irst","P)revious","N)ext","L)ast");
	System.out.format(space,"","");
	System.out.format(m2Format,"", "Sea)rch", "G)o To","Se)t row","Sa)ve","B)ackup","Re)store","H)elp","E)xit");
	System.out.format(line,"");
    }
    public void displayMenu() throws FileNotFoundException, ClassNotFoundException, IOException{
        mainMenu main=new mainMenu();
        MethodCollection method=new MethodCollection();
        Scanner sc=new Scanner(System.in);
        String option = null;
        String exitConfirm = "n";
        
        do
        {
            menu();
            String tab="%-1s";
            System.out.format(tab+"> Option: ","");
            option=sc.nextLine().toLowerCase();        
            switch(option)
            {
                case "*":                
                    method.display();
                    break;
                case "w":
                    method.write();
                    break;
                case "r":
                    method.read();
                    break;
                case "u":
                    method.update();
                    break;
                case "d":
                    method.delete();
                    break;
                case "f":
                    method.first();
                    break;
                case "p":
                    method.prevoius();
                    break;
                case "n":
                    method.next();
                    break;
                case "l":
                    method.last();
                    break;
                case "sea":
                    method.search();
                    break;
                case "g":
                    method.GotoPage();
                    break;
                case "se":
                    method.setRow();
                    break;
                case "sa":
                    method.save();
                    break;
                case "b":
                    method.backup();
                    break;
                case "re":
                    method.restore();
                    break;
                case "h":
                    method.help();
                    break;
                case "e":
                    method.exit(exitConfirm);
                    break;                
                default:
                    System.out.println("\nIncorect Syntax!");
            }
        }while(!exitConfirm.equals("y"));
    }
}
