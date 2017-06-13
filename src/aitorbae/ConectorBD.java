/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aitorbae;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alumno
 */
public class ConectorBD {

    private Connection conexion;
    private Statement sentencia;

    //Datos para la conexión con la BD
    private final String servidor = "localhost";
    private final String puerto = "3306";
    private final String BD = "baebuena";
    private final String usuario = "root";
    private final String clave = "123456";
    private final String URL = "jdbc:mysql://"
            + servidor + ":" + puerto + "/" + BD;

    /**
     * Constructor que inicializa los atributos internos del conector de BD
     */
    public ConectorBD() {
        this.conexion = null;
        this.sentencia = null;
    }

    /**
     * Método para crear la comunicación con la BD
     * @return true cuando hay conexión correcta y false cuando no es posible
     */
    public boolean conectar() {
        boolean estado = false;
        try{
            //Driver de MySQL
            Class.forName("com.mysql.jdbc.Driver");
            try {
                //Establecer conexión con la BD
                conexion = DriverManager.getConnection(URL, usuario, clave);
                estado = true;
            } catch (SQLException ex) {
                System.err.println("ERROR: ConectorBD.conectar()");
                System.err.println("Al intentar conectar con la BD");
                System.err.println(ex.getMessage());
            }
        }catch(ClassNotFoundException ex){
            System.err.println("ERROR: ConectorBD.conectar()");
            System.err.println("Al intentar conectar con la BD");
            System.err.println(ex.getMessage());
        }
        return estado;
    }

    /**
     * Para ejecutar sentencias SQL: SELECT
     *
     * @param sqlCambiante texto con el script sql para seleccionar registros
     * @return ResultSet con la información seleccionada o null en caso de no
     */
    public ResultSet seleccionar(String sqlCambiante) {
        ResultSet resultado = null;
        try {
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sqlCambiante);
        } catch (SQLException sqle) {
            System.err.println("ERROR: ConectorBD.seleccionar(sql)");
            System.err.println(sqle.getMessage());
        }
        return resultado;
    }
    
    /**
     * Para ejecutar sentencias SQL: INSERT, UPDATE, DELETE
     * @param sql texto con el script sql para ejecutar sobre la BD
     * @param sql2 texto con el script sql para ejecutar sobre la BD
     * @return true si tiene éxito y false en caso contrario
     */
    public boolean ejecutar(String sql1, String sql2){
        boolean estado = false;
        try{
            sentencia = conexion.createStatement();
            sentencia.execute(sql1);
            sentencia.execute(sql2);
            sentencia.close();
            estado = true;
        }catch(SQLException sqle){
            System.err.println("ERROR: ConectorBD.ejecutar(sql)");
            System.err.println(sqle.getMessage());
        }
        return estado;
    }
    
    /**
     * 
     * Para cerrar la conexión de forma correcta con la base de datos,
     * verificando que existe la conexión
     */
    public void desconectar(){
        try{
            if(conexion != null){
                conexion.close();
                conexion = null;
            }
        }catch(SQLException sqle){
            System.err.println("ERROR: ConectorBD.desconectar()");
            System.err.println(sqle.getMessage());
        }
    }
    
    /**
     * Devuelve la conexión actual que esté establecida
     * @return conexion
     */
    public Connection getConnection(){
        return conexion;
    }
}
