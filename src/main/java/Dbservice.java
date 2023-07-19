import java.sql.*;

public class Dbservice {
    String Url = "jdbc:postgresql://localhost:5432/bot";
    String Dbuser = "postgres";
    String Dbpassword = "1809";

    public void addUser(String firstname, String username, String chat_id) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection1 = DriverManager.getConnection(Url, Dbuser, Dbpassword);
        PreparedStatement preparedStatement = connection1.prepareStatement("select * from islomusers where username=?;");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        Users users = null;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username2 = resultSet.getString("username");
            String firstname1 = resultSet.getString("firstname");
            String chat_id1 = resultSet.getString("chat_id");
            users = new Users(id, firstname1, username2, chat_id1);
        }
        if (users == null) {
            Connection connection = DriverManager.getConnection(Url, Dbuser, Dbpassword);
            PreparedStatement preparedStatement1 = connection.prepareStatement("insert into islomusers(firstname,username,chat_id) values(?,?,?)");
            preparedStatement1.setString(1 , firstname);
            preparedStatement1.setString(2, username);
            preparedStatement1.setString(3, chat_id);
            preparedStatement1.executeUpdate();
        }

    }
}
