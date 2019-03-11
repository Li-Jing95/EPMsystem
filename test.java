import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        test te = new test();
        List<String> list = te.getSelect();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public Connection getCon() {
        //连接数据库
        String DB = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/login";
        String mysqlroot = "root";
        String mysqlrootpsw = "root";
        Connection con = null;
        try {
            Class.forName(DB);
            con = DriverManager.getConnection(url, mysqlroot, mysqlrootpsw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public List<String> getSelect() {
        String sql = "select * from admin";
        Connection con = getCon();
        List<String> list = new ArrayList<String>();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("name"));
                list.add(rs.getString("pass"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
