package MVC;

import Controlador.Controlador;
import Modelo.Modelo;
import Vista.VistaPalindromo;

public class MVC {
    public static void main(String[] args) {
        Modelo model = new Modelo();
        VistaPalindromo view = new VistaPalindromo();
        Controlador controller = new Controlador(view, model);
        
        controller.startView();
        view.setVisible(true);
    }
    
}
