package usuario;


public class Contacto {
    public int id;
    public String usuario;
    public String contrasena;
    public String rol;
    
    public Contacto(){}
    
    public Contacto(int id){
        this.id=id;
    }
    public Contacto(String usuario,String contrasena,String rol){
        this.usuario=usuario;
        this.contrasena=contrasena;
        this.rol=rol;
    }
    public Contacto(int id,String usuario,String contrasena,String rol){
        this.id=id;
        this.usuario=usuario;
        this.contrasena=contrasena;
        this.rol=rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
