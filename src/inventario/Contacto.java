package inventario;


public class Contacto {
    int id;
    int id_producto;
    int stock;
    
    public Contacto(){}
    
    public Contacto(int id){
        this.id=id;
    }

    public Contacto(int id_producto, int stock) {
        this.id_producto = id_producto;
        this.stock = stock;
    }

    public Contacto(int id, int id_producto, int stock) {
        this.id = id;
        this.id_producto = id_producto;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
}
