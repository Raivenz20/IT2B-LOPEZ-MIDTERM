package Lopez;

import java.time.LocalDate;
import java.util.Scanner;
import java.sql.*;

public class Customer_Management_System {
    Scanner input = new Scanner(System.in);
    LocalDate cdate = LocalDate.now();
    config conf = new config();
    
    public void add(){
        System.out.print("|\tEnter Number of Orders: ");
        int cnum;
        while(true){
            try{
                cnum = input.nextInt();
                break;
            }catch(Exception e){
                input.next();
                System.out.print("|\tEnter Number of Orders Again: ");
            }
        }
        
        for(int x = 0; x < cnum; x++){
            boolean exit = true;
            System.out.println("+----------------------------------------------------------------------------------------------------+");
            System.out.printf("|%-25s%-23s%-27d%-25s|\n","","Enter Details of Order ", x+1, "");
            System.out.print("|\tOrder ID: ");
            int id;
            while(true){
                try{
                    id = input.nextInt();
                    if(id > 0){
                        break;
                    } else if(id == 0){
                        exit = false;
                        break;
                    } else{
                        System.out.print("|\tOrder ID: ");
                    }
                } catch(Exception e) {
                    input.next();
                    System.out.print("|\tOrder ID: ");
                }
            }
            while(exit){
                input.nextLine();
                System.out.print("|\tCustomer Name: ");
                String name = input.nextLine();
                System.out.println("|\tOrder Date: " + cdate);
                System.out.print("|\tOrder Amount: ");
                int amount;
                while(true){
                    try{
                        amount = input.nextInt();
                        break;
                    } catch(Exception e){
                        input.next();
                        System.out.print("|\tOrder Amount: ");
                    }
                }
                System.out.println("+----------------------------------------------------------------------------------------------------+");
                System.out.printf("|%-25s%-50s%-25s|\n","","Order Status Are Only (Shipped/Delivered)","");
                System.out.print("|\tOrder Status: ");
                String stat;
                while(true){
                    try{
                        stat = input.next();
                        if(stat.equalsIgnoreCase("Shipped") || stat.equalsIgnoreCase("Delivered")){
                            break;
                        } else {
                            System.out.print("|\tOrder Status: ");
                        }
                    } catch(Exception e){
                        input.next();
                        System.out.print("|\tOrder Status: ");
                    }
                }
                String amount2 = "$" + amount;
                
                String SQL = "INSERT INTO Customer_Manage (C_Id2, C_name, C_date, C_ammount, C_status) VALUES (?, ?, ?, ?, ?)";
                conf.addRecord(SQL, id, name, cdate, amount2, stat);
                exit = false;
            }
        }
    }
    
    public void edit(){
        boolean exit = true;
        System.out.println("+----------------------------------------------------------------------------------------------------+");
        System.out.printf("|%-25s%-50s%-25s|\n","","Edit Customer Information","");
        System.out.print("|\tOrder ID: ");
        int id;
        while(true){
            try{
                id = input.nextInt();
                if(doesIDexists(id, conf)){
                    break;
                } else if(id == 0){
                    exit = false;
                    break;
                } else {
                    System.out.print("|\tOrder ID: ");
                }
            } catch(Exception e){
                input.next();
                System.out.print("|\tOrder ID: ");
            }
        }
        while(exit){
            input.nextLine();
            System.out.print("|\tCustomer Name: ");
            String name = input.nextLine();
            System.out.println("|\tOrder Date: " + cdate);
            System.out.print("|\tOrder Amount: ");
            int amount;
            while(true){
                try{
                    amount = input.nextInt();
                    break;
                } catch(Exception e){
                    input.next();
                    System.out.print("|\tOrder Amount: ");
                }
            }
            System.out.println("+----------------------------------------------------------------------------------------------------+");
            System.out.printf("|%-25s%-50s%-25s|\n","","Order Status Are Only (Shipped/Delivered)","");
            System.out.print("|\tOrder Status: ");
            String stat;
            while(true){
                try{
                    stat = input.next();
                    if(stat.equalsIgnoreCase("Shipped") || stat.equalsIgnoreCase("Delivered")){
                        break;
                    } else {
                        System.out.print("|\tOrder Status: ");
                    }
                } catch(Exception e){
                    input.next();
                    System.out.print("|\tOrder Status: ");
                }
            }
            String amount2 = "$" + amount;
            String SQL = "UPDATE Customer_Manage SET C_name = ?, C_date = ?, C_ammount = ?, C_status = ? WHERE C_Id2 = ?";
            conf.updateRecord(SQL, name, cdate, amount2, stat, id);
            exit = false;
        }
    }
    
    public void delete(){
        boolean exit = true;
        System.out.println("+----------------------------------------------------------------------------------------------------+");
        System.out.printf("|%-25s%-50s%-25s|\n","","Delete Customer Information","");
        System.out.print("|\tOrder ID: ");
        int id;
        while(true){
            try{
                id = input.nextInt();
                if(doesIDexists(id, conf)){
                    break;
                } else if(id == 0){
                    exit = false;
                    break;
                } else {
                    System.out.print("|\tOrder ID: ");
                }
            } catch(Exception e){
                input.next();
                System.out.print("|\tOrder ID: ");
            }
        }
        while(exit){
            String SQL = "DELETE FROM Customer_Manage WHERE C_Id2 = ?";
            conf.deleteRecord(SQL, id);
            exit = false;
        }
    }
    
    public void view(){
        String tbl_view = "SELECT * FROM Customer_Manage";
        String[] tbl_Headers = {"Order ID", "Customer Name", "Order Date", "Order Amount", "Order Status"};
        String[] tbl_Columns = {"C_Id2", "C_name", "C_date", "C_ammount", "C_status"};
        conf.viewRecords(tbl_view, tbl_Headers, tbl_Columns);
    }
    
    private boolean doesIDexists(int id, config conf) {
        String query = "SELECT COUNT(*) FROM Customer_Manage WHERE C_Id2 = ?";
        try (Connection conn = conf.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("|\tError checking Report ID: " + e.getMessage());
        }
        return false;
    }
}
