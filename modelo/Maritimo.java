package modelo;

public class Maritimo extends Envio {

    // Constructor que recibe los parámetros y los envía a Envio
    public Maritimo(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        // Valores para Maritimo
        double tarifaBaseKm = 800.0;
        double recargoKg = 1000.0;
        
        return (this.distancia * tarifaBaseKm) + (this.peso * recargoKg);
    }
}