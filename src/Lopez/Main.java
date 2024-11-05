package Lopez;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Customer_Management_System cms = new Customer_Management_System();
        boolean exit = true;
        
        do{
            System.out.println("+----------------------------------------------------------------------------------------------------+");
            System.out.printf("|%-25s%-50s%-25s|\n","","**Customer Management System**","");
            System.out.printf("|%-5s%-95s|\n","","1. Add");
            System.out.printf("|%-5s%-95s|\n","","2. Edit");
            System.out.printf("|%-5s%-95s|\n","","3. Delete");
            System.out.printf("|%-5s%-95s|\n","","4. View");
            System.out.printf("|%-5s%-95s|\n","","5. Exit");
            System.out.printf("|%-5sEnter Choice: ","");
            int choice;
            while(true){
                try{
                    choice = input.nextInt();
                    if(choice>0 && choice<6){
                        break;
                    }else{
                        System.out.printf("|%-5sEnter Choice Again: ","");
                    }
                }catch(Exception e){
                    input.next();
                    System.out.printf("|%-5sEnter Choice Again: ","");
                }
            }
            switch(choice){
                case 1:
                    cms.add();
                break;
                case 2:
                    cms.view();
                    cms.edit();
                    cms.view();
                break;
                case 3:
                    cms.view();
                    cms.delete();
                    cms.view();
                break;
                case 4:
                    cms.view();
                break;
                default:
                    System.out.printf("|%-5sDo You Really Want to Exit (Yes/No): ","");
                    String exit2;
                    while(true){
                        try{
                            exit2 = input.next();
                            if(exit2.equalsIgnoreCase("yes")){
                                exit = false;
                                break;
                            }else{
                                break;
                            }
                        }catch(Exception e){
                            System.out.print("|\tEnter (Yes/No) Again: ");
                        }
                    }
                break;
            }
        }while(exit);
    }
}
