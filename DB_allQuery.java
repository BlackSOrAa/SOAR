package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DB_allQuery {
   static Connection conn; //DB와 연결
   static Statement stmt; //sql문을 실행시키기 위한 Statement 객체 얻어오기
   
   public static void main(String[] args) {
      DB_allQuery aq = new DB_allQuery();
      DBConnection dbc = new DBConnection();
      conn = dbc.getConnection();
      
      Scanner sc = new Scanner(System.in);
      boolean check = true;
      
      try {
         stmt = conn.createStatement();
         while (check) {
            System.out.println("로그 수집 내용입니다.");
            aq.select();
            System.out.println("원하는 작업을 전택하세요.");
            System.out.println("1.추가 2. 삭제 3. 수정 4.나가기");
            
            int input = sc.nextInt();
            switch (input) {
            case 1:
               aq.insert(sc);
               break;
            case 2:
               aq.delete(sc);
               break;
            case 3:
               aq.update(sc);
               break;
            case 9:
               System.out.println("종료합니다.");
               check = false;
               break;
            default:
               System.out.println("그런 번호는 없습니다. 다시 입력해주세요.");
                  
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            stmt.close();
            conn.close();
            sc.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
   private void update(Scanner sc) { //수정
      System.out.println("수정입니다.");
      System.out.println("수정할 번호를 입력하세요.");
      int number = sc.nextInt();
      System.out.println("수정할 파일 이름을 입력하세요.");
      String name = sc.next();
      System.out.println("수정할 수집 시간을 입력하세요.");
      int time = sc.nextInt();
      System.out.println("수정할 ip를 입력하세요.");
      String ip = sc.next();
      
      String sql = "UPDATE log SET file_name='"+name+"', log_time="+time+", log_ip='"+ip+"' WHERE log_no="+number;
      try {
         stmt.execute(sql);
      } catch (SQLException e) {
         System.out.println("심각한 문제가 발생하였습니다.");
      } 
      
      System.out.println("수정하였습니다.");
   }
   
   private void insert(Scanner sc) { //삽입

      System.out.println("로그를 기록합니다..");
      System.out.println("순서를 입력하세요.");
      int no = sc.nextInt();
      System.out.println("파일명을 입력하세요.");
      String name = sc.next();
      System.out.println("수집 시간을 입력하세요.");
      int time = sc.nextInt();
      System.out.println("ip를 입력하세요.");
      String ip = sc.next();
      String sql = "INSERT INTO log (log_no, file_name, log_time, log_ip) "+" VALUES("+no+",'" +name+"', "+time+", '"+ip+"')";
      try {
         stmt.execute(sql);
      } catch (SQLException e) {
         System.out.println("심각한 문제가 발생하였습니다.");
      } 
      
      System.out.println("완료하였습니다.");
   }
   public void delete (Scanner sc) { //삭제
      System.out.println("삭제할 번호를 입력하세요.");
      int input = sc.nextInt();
      try {
         stmt.execute("DELETE FROM log WHERE log_no="+input);
      } catch (SQLException e) {
      }
   }
   
   public void select() { //조회
      ResultSet rs = null;
      try {
         rs = stmt.executeQuery("SELECT * FROM log");
         while (rs.next()) {
            String no = rs.getString("log_no");
            String name = sortString (rs.getString("file_name"));
            String time = rs.getString("log_time");
            String ip = sortString(rs.getString("log_ip"));
            System.out.printf("|%5.5s |%10s|%10.10s |%10.10s |\n", no, name, time, ip);
         }
         
      } catch (Exception e) {
         System.out.println("omg...");
      } finally {
         try {
            rs.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
      } 
   }
   public static String sortString(String str) {
      if (str.length() > 5) {
         str = str.substring(0, 5);
      } else if(str.length() < 5) {
         String temp = str;
         for (int i = 0; i<5 - str.length();i++) {
            temp = " " + temp;
         }
         str = temp;
      }
      return str;
   }
}