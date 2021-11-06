/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formula1.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author 505
 */
public class ConsultasPilotos extends ModeloBD {
    
    PreparedStatement consultaSQL;
    ResultSet resultadoSQL;
    
    public boolean registrarPiloto(Piloto piloto){
        
        Connection conexion=conectarBD();
        String query="INSERT INTO pilotos(nombres,apellidos,salario,fecha_inicio,fecha_salida,id_escuderia)"
                + "VALUES (?,?,?,?,?,?)";
        
        try{
            //Peparate que voy con toa
            consultaSQL=conexion.prepareStatement(query);
            
            //Ajusto la consulta
            consultaSQL.setString(1,piloto.getNombres());
            consultaSQL.setString(2,piloto.getApellidos());
            consultaSQL.setInt(3,piloto.getSalario());
            consultaSQL.setString(4,piloto.getFechaIn());
            consultaSQL.setString(5,piloto.getFechaOut());
            consultaSQL.setString(6,piloto.getIdEscuderia());
            
            //Ejecuto la consulta
            int resultado=consultaSQL.executeUpdate();
            
            //validando el resultado
            if(resultado>0){
              return true;  
            }else{
               return false; 
            }
            
        }catch(Exception error){
            System.out.println("upsss... "+error);
            return false;
        }
        
    }
    
    public Piloto buscarPiloto(int id){
       
        Connection conexion=conectarBD();
        String query="SELECT * from pilotos where id=?";
        
        try{
            
            //Peparate que voy con toa
            consultaSQL=conexion.prepareStatement(query);
            
            //Ajusto la consulta
            consultaSQL.setInt(1, id);
            
            //Ejecuto la consulta
            resultadoSQL=consultaSQL.executeQuery();
            
            //Orgaizo el resultado
            Piloto piloto= new Piloto();
            if(resultadoSQL.next()){
                
                piloto.setId(resultadoSQL.getInt("id"));
                piloto.setNombres(resultadoSQL.getString("nombres"));
                piloto.setApellidos(resultadoSQL.getString("apellidos"));
                piloto.setSalario(resultadoSQL.getInt("salario"));
                piloto.setFechaIn(resultadoSQL.getString("fecha_inicio"));
                piloto.setFechaOut(resultadoSQL.getString("fecha_salida"));
                piloto.setIdEscuderia(resultadoSQL.getString("id_escuderia"));
                
                return piloto;
                
                
            }else{
                return null;
            }
            
         
            
        }catch(Exception error){
            System.out.println("upsss... "+error);
            return null;
        }
        
       
   } 
    
    public boolean actualizar(Piloto piloto){
        Connection conexion=conectarBD();
        String query="UPDATE pilotos SET fecha_salida=? WHERE id=?";
        
        try{
            consultaSQL=conexion.prepareStatement(query);
            
            consultaSQL.setString(1, piloto.getFechaOut());
            consultaSQL.setInt(2, piloto.getId());
            
            int resultado=consultaSQL.executeUpdate();
            
            if(resultado>0){
              return true;  
            }else{
               return false; 
            }
            
            
        
        }catch(Exception error){
            System.out.println("upss"+error);
            return false;
        }

    }
    
    
}
