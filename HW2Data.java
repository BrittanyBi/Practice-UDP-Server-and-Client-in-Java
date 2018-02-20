public class HW2Data {
   String[][] items = {{"New Inspiron 15", "$379.99", "157"},
                       {"New Inspiron 17", "$449.99", "128"},
                       {"New Inspiron 15R", "$549.99", "202"},
                       {"New Inspiron 15z Ultrabook", "$749.99", "315"},
                       {"XPS 14 Ultrabook", "$999.99", "261"},
                       {"New XPS 12 UltrabookXPS", "$1199.99", "178"}};
   
   public HW2Data(){
   }
      
   public String toString(){
      String table = "";
            
      for(String[] item : items){
         for(String data : item){
            table += data + "\t";
         }
         table += "\n";
      }
      return table;
   }
}