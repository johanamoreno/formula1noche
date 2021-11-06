/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formula1.controladores;

import formula1.modelos.ConsultasEscuderia;
import formula1.modelos.ConsultasPilotos;
import formula1.modelos.Escuderia;
import formula1.modelos.Piloto;
import formula1.vistas.VistaSalida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

/**
 *
 * @author 505
 */
public class ControladorSalida implements ActionListener{
    
    VistaSalida vistaSalida = new VistaSalida();
    Escuderia escuderia = new Escuderia();
    Piloto piloto = new Piloto();

    public ControladorSalida(VistaSalida vistaSalida, Escuderia escuderia, Piloto piloto) {
        this.vistaSalida = vistaSalida;
        this.escuderia = escuderia;
        this.piloto = piloto;
        
        vistaSalida.botonRetirar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        ConsultasEscuderia consultasEscuderia = new ConsultasEscuderia();
        ConsultasPilotos consultasPilotos = new ConsultasPilotos();
        
        piloto=consultasPilotos.buscarPiloto(Integer.parseInt(vistaSalida.cajaIdPiloto.getText()));
        String fechaEntrada=piloto.getFechaIn();
        try{
            Date entrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaEntrada);
            
            Date salida = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaSalida=formato.format(salida);
            
            long tiempoDiferencia=salida.getTime()-entrada.getTime();
            TimeUnit unidadTiempo=TimeUnit.MINUTES;
            long tiempoEscuderia=unidadTiempo.convert(tiempoDiferencia, TimeUnit.MILLISECONDS);
            
            piloto.setFechaOut(fechaSalida);
            
            if(consultasPilotos.actualizar(piloto)){
            
            JOptionPane.showMessageDialog(null, "Exito en la salida, te demoraste: "+tiempoEscuderia);
            
            }else{
        
            JOptionPane.showMessageDialog(null, "Error en la salida");
            
            }
            
        }catch(ParseException error){
            
            System.out.println("uppsss."+error);
        }
    }
    
    
    
    
}
