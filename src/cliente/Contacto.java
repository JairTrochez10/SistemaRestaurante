package cliente;

public class Contacto {
    public int id;
    public String dni;
    public String nombre;
    public String apellido;
    public String telefono;
    public String email;
    public String direccion;
    
    public Contacto(){}
    
    public Contacto(int id){
        this.id=id;
    }
    public Contacto(String dni,String nombre,String apellido,String telefono,String email,String direccion){
        this.dni=dni;
        this.nombre=nombre;
        this.apellido=apellido;
        this.telefono=telefono;
        this.email=email;
        this.direccion=direccion;
    }
    public Contacto(int id,String dni,String nombre,String apellido,String telefono,String email,String direccion){
        this.id=id;
        this.dni=dni;
        this.nombre=nombre;
        this.apellido=apellido;
        this.telefono=telefono;
        this.email=email;
        this.direccion=direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
