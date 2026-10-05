package modelo;

public class Terrestre extends Envio {

    // Constructor que recibe los parámetros y los envía a Envio
    public Terrestre(String codigo, String cliente, double peso, double distancia) {
        super(codigo, cliente, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        // Valores para Terrestre
        double tarifaBaseKm = 1500.0;
        double recargoKg = 2000.0;
        
        return (this.distancia * tarifaBaseKm) + (this.peso * recargoKg);
    }
}