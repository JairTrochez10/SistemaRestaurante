import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private static final String URL="jdbc:mysql://localhost:3306/rimberio?useSSL=false&serverTimezone=UTC";
    private static final String USER= "root";
    private static final String PASSWORD= "";
    
    public static Connection conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Conexion Exitosa");
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }catch(ClassNotFoundException | SQLException e){
            throw new RuntimeException("Error de Conexion: "+e.getMessage());
        }
    }
    
    
}
