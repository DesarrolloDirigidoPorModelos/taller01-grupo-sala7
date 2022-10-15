import modelo.Cliente;
import modelo.Medidor;
import modelo.Municipio;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("Bienvenido");
        Municipio municipio = new Municipio("Municipio de Loja");
        //Cliente cliente = new Cliente(null, null, 0, null, municipio);
        Cliente cliente = new Cliente("Carlos", "Perez", 1105050304, "Loja", municipio);
        Medidor medidor = new Medidor("1", 1, "Loja", cliente);
        cliente.comprarMedidor(medidor);
        Medidor medidor2 = new Medidor("2", 2, "Loja", cliente);
        cliente.comprarMedidor(medidor2);
        

    }
}