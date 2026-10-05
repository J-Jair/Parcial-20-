package modelo;

import java.util.ArrayList;
import java.util.List;

public class Logistica {
    
    private List listaEnvios;
    private int contadorCodigo;

    public Logistica() {        
        this.listaEnvios = new ArrayList();
        this.contadorCodigo = 10001; 
    }

    // Método para generar el siguiente código de manera automática
    public String generarCodigo() {
        return String.valueOf(contadorCodigo);
    }

    // Método para agregar un envío a la lista
    public void agregarEnvio(Envio envio) {
        listaEnvios.add(envio);
        contadorCodigo++; 
    }

    // Método para eliminar un envío buscando por su código
    public boolean quitarEnvio(String codigo) {
        for (int i = 0; i < listaEnvios.size(); i++) {            
            if (((Envio) listaEnvios.get(i)).getCodigo().equals(codigo)) {
                listaEnvios.remove(i);
                return true;
            }
        }
        return false;
    }

    // Método para retornar todos los envíos
    public List getListaEnvios() {
        return listaEnvios;
    }
}