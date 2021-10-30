/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formula1;

import formula1.controladores.Controlador;
import formula1.controladores.ControladorHome;
import formula1.modelos.Escuderia;
import formula1.modelos.ModeloBD;
import formula1.modelos.Piloto;
import formula1.vistas.Vista;
import formula1.vistas.VistaHome;

/**
 *
 * @author 505
 */
public class Formula1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    Escuderia escuderia = new Escuderia();
    Piloto piloto = new Piloto();
    VistaHome vistaHome = new VistaHome();
    vistaHome.setVisible(true);
        
    ControladorHome controladorHome = new ControladorHome(escuderia, piloto,vistaHome);
    }
    
}
