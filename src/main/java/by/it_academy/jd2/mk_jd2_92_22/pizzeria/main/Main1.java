//package by.it_academy.jd2.mk_jd2_92_22.pizzeria.main;
//
//import java.sql.*;
//
//public class Main1 {
//    public static void main(String[] args) {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        try
//                (Connection conn =
//                         DriverManager.getConnection(
//                                 "jdbc:postgresql://localhost:8080/company",
//                                 "postgres",
//                                 "postgres");
//                 Statement stm = conn.createStatement();
//                 ResultSet rs = stm.executeQuery("SELECT id, name, job, dep\n" +
//                         "FROM structure.emp");) {
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}