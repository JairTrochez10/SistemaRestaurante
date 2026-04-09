package cliente;


import Login.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Login.Conexion;




public class ContactoCliente {
    public boolean insertar(Contacto contacto){
        String sql="INSERT INTO clientes(dni,nombre,apellido,telefono,email,direccion) VALUES (?,?,?,?,?,?)";
        try(Connection cn=Conexion.getConnection();
                PreparedStatement ps= cn.prepareStatement(sql))
        {
            ps.setString(1, contacto.dni);
            ps.setString(2, contacto.nombre);
            ps.setString(3, contacto.apellido);
            ps.setString(4, contacto.telefono);
            ps.setString(5, contacto.email);
            ps.setString(6, contacto.direccion);
            
            return ps.executeUpdate()>0;
            
        }catch(SQLException e){
            System.out.println("Error al insertar");
            return false;
    
    }
  }
    public boolean modificar(Contacto contacto){
	String sql="UPDATE clientes SET dni=?, nombre=?, apellido=?, telefono=?, email=?, direccion=? WHERE id=?";
	try(Connection cn=Conexion.getConnection();
		PreparedStatement ps=cn.prepareStatement(sql)){
		ps.setString(1, contacto.getDni());
		ps.setString(2, contacto.getNombre());
		ps.setString(3, contacto.getApellido());
                ps.setString(4, contacto.getTelefono());
                ps.setString(5, contacto.getEmail());
                ps.setString(6, contacto.getDireccion());
		ps.setInt(7, contacto.getId());
		return ps.executeUpdate()>0;
	}catch(Exception e){
		System.out.println(e);
		return false;
	}
}

public boolean eliminar(int id){
	String sql="DELETE FROM clientes WHERE id=?";
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
        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Contacto contacto = new Contacto();
                    contacto.id = rs.getInt("id");
                    contacto.dni = rs.getString("dni");
                    contacto.nombre = rs.getString("nombre");
                    contacto.apellido = rs.getString("apellido");
                    contacto.telefono = rs.getString("telefono");
                    contacto.email = rs.getString("email");
                    contacto.direccion = rs.getString("direccion");
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
    String sql = "SELECT id, dni,nombre,apellido,telefono,email,direccion FROM clientes";

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
                rs.getString("dni"),  
                rs.getString("nombre"), 
                rs.getString("apellido"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getString("direccion")
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
    
    String sql = "SELECT id, dni,nombre,apellido,telefono,email,direccion FROM clientes WHERE dni LIKE ? or nombre LIKE ? or apellido LIKE ? or telefono LIKE ?";

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
        ps.setString(2, "%" + nombre + "%");
        ps.setString(3, "%" + nombre + "%");
        ps.setString(4, "%" + nombre + "%");

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
                rs.getString("dni"),  
                rs.getString("nombre"), 
                rs.getString("apellido"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getString("direccion")
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

