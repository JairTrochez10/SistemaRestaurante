package inventario;


import Login.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Login.Conexion;
import inventario.Contacto;




public class ContactoInventario {
    public boolean insertar(Contacto contacto){
        String sql="INSERT INTO inventario(id_producto,stock) VALUES (?,?)";
        try(Connection cn=Conexion.getConnection();
                PreparedStatement ps= cn.prepareStatement(sql))
        {
            ps.setInt(1, contacto.id_producto);
            ps.setInt(2, contacto.stock);
            
            return ps.executeUpdate()>0;
            
        }catch(SQLException e){
            System.out.println("Error al insertar");
            return false;
    
    }
  }
    public boolean modificar(Contacto contacto){
	String sql="UPDATE inventario SET id_producto=?, stock=?,fecha_registro=? WHERE id=?";
	try(Connection cn=Conexion.getConnection();
		PreparedStatement ps=cn.prepareStatement(sql)){
		ps.setInt(1, contacto.getId_producto());
		ps.setInt(2, contacto.getStock());
                ps.setString(3, contacto.getFecha_registro());
		ps.setInt(4, contacto.getId());
		return ps.executeUpdate()>0;
	}catch(Exception e){
		System.out.println(e);
		return false;
	}
}

public boolean eliminar(int id){
	String sql="DELETE FROM inventario WHERE id=?";
	try(Connection cn=Conexion.getConnection();
		PreparedStatement ps=cn.prepareStatement(sql)){
		ps.setInt(1, id);
		return ps.executeUpdate()>0;
	}catch(Exception e){
		System.out.println(e);
		return false;
	}
}
    public Contacto buscarPorId(int id) {
        String sql = "SELECT * FROM inventario WHERE id = ?";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Contacto contacto = new Contacto();
                    contacto.id = rs.getInt("id");
                    contacto.id_producto = rs.getInt("id_producto");
                    contacto.stock = rs.getInt("stock");

                    return contacto;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }

        return null;
    }
    //0001
public ArrayList<Contacto> listarTodos() {

    // Se crea una lista vacía para guardar todos los contactos
    ArrayList<Contacto> lista = new ArrayList<>();

    // Consulta SQL:
    // selecciona las columnas id, nombre, telefono, correo y direccion
    // de la tabla contactos
    String sql = "SELECT id,id_producto,stock,fecha_registro FROM inventario";

    try {
        // Abre la conexión con la base de datos
        Connection con = Conexion.getConnection();

        // Prepara la consulta SQL
        PreparedStatement ps = con.prepareStatement(sql);

        // Ejecuta la consulta y guarda el resultado en rs
        ResultSet rs = ps.executeQuery();

        // Recorre cada fila que devolvió la consulta
        while (rs.next()) {

            // Crea un objeto Contacto con los datos de la fila actual
            Contacto c = new Contacto(
                rs.getInt("id"), 
                rs.getInt("id_producto"), 
                rs.getInt("stock"),
                    rs.getString("fecha_registro")
            );

            // Agrega ese contacto a la lista
            lista.add(c);
        }

        // Cierra el resultado de la consulta
        rs.close();

        // Cierra la consulta preparada
        ps.close();

        // Cierra la conexión con la base de datos
        con.close();

    } catch (Exception e) {
        // Si ocurre un error, lo muestra en consola
        System.out.println("Error al listar: " + e.getMessage());
    }

    // Devuelve la lista con todos los contactos encontrados
    return lista;
}
    //00002
public ArrayList<Contacto> buscarPorNombre(String nombre) {

    // Se crea una lista vacía para guardar los contactos encontrados
    ArrayList<Contacto> lista = new ArrayList<>();

    // Consulta SQL:
    // selecciona las columnas id, nombre, telefono, correo y direccion
    // de la tabla contactos
    // donde el nombre coincida con el texto buscado
    
    String sql = "SELECT id,id_producto,stock,fecha_registro  FROM inventario WHERE id_producto LIKE ?";

    // Esta era la versión para buscar por nombre o teléfono
    // String sql = "SELECT id, nombre, telefono, correo, direccion FROM contactos WHERE nombre LIKE ? or telefono LIKE ?";

    try {
        // Abre la conexión con la base de datos
        Connection con = Conexion.getConnection();

        // Prepara la consulta SQL para poder enviarle valores
        PreparedStatement ps = con.prepareStatement(sql);

        // Reemplaza el primer signo ? de la consulta
        // por el texto que escribe el usuario
        // Los % indican que puede haber cualquier cosa antes o después
        // Ejemplo: si nombre = "Luis", buscará "%Luis%"
        ps.setString(1, "%" + nombre + "%");

        // Esto solo se usaría si la consulta tuviera un segundo ?
        // por ejemplo para buscar también por teléfono
        // ps.setString(2, "%" + nombre + "%");

        // Ejecuta la consulta y guarda el resultado
        ResultSet rs = ps.executeQuery();

        // Recorre cada fila que devolvió la consulta
        while (rs.next()) {

            // Crea un objeto Contacto con los datos de la fila actual
            Contacto c = new Contacto(
                rs.getInt("id"), 
                rs.getInt("id_producto"), 
                rs.getInt("stock"),
                rs.getString("fecha_registro")
            );

            // Agrega el contacto encontrado a la lista
            lista.add(c);
        }

        // Cierra el ResultSet
        rs.close();

        // Cierra el PreparedStatement
        ps.close();

        // Cierra la conexión
        con.close();

    } catch (Exception e) {
        // Si ocurre un error, lo muestra en consola
        System.out.println("Error al buscar: " + e.getMessage());
    }

    // Devuelve la lista con los contactos encontrados
    return lista;
}
}

