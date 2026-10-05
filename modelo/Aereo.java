package modelo;

public class Aereo extends Envio {

    // Constructor que recibe los parámetros y los envía a Envio
    public Aereo(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        // Valores para Aereo
        double tarifaBaseKm = 5000.0;
        double recargoKg = 4000.0;
        
        return (this.distancia * tarifaBaseKm) + (this.peso * recargoKg);
    }
}