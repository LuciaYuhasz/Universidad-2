/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.pkg2.accesoDatos;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import universidad.pkg2.entidades.Inscripcion;
import universidad.pkg2.entidades.Alumno;
import universidad.pkg2.entidades.Inscripcion;
import universidad.pkg2.entidades.Materia;


public class InscripcionData {
     
 private Connection con = null;

 private MateriaData md= new MateriaData();
 private AlumnoData ad =new AlumnoData();



    public InscripcionData() {
        this.con = Conexion.getConexion();
    }
    
     public void guardarInscripcion(Inscripcion inscp){ //inscribo alumno en materia
        String sql = " INSERT INTO inscripcion (idAlumno, idMateria,nota) VALUES (?,?,?) ";
         try {
             PreparedStatement ps= con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,inscp.getAlumno().getIdAlumno());
            ps.setInt(2, inscp.getMateria().getIdMateria());
            ps.setDouble(3,inscp.getNota());
            ps.executeUpdate();
             ResultSet rs = ps.getGeneratedKeys();
             if (rs.next()) {
                 inscp.setIdInscripcion(rs.getInt(1));
                 JOptionPane.showMessageDialog(null, "ALUMNO INSCRIPTO CORRECTAMENTE");
             }
            ps.close();
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error al conectar con tabla de inscripcion" + ex.getMessage());
         }
    }
    public List<Inscripcion> obtenerInscipciones(){
    
         ArrayList<Inscripcion> listaInscripcion =new ArrayList<>();
         String sql=  " SELEcT * FROM inscripcion ";
          try {
         
         PreparedStatement ps= con.prepareStatement(sql);
         ResultSet rs=ps.executeQuery();
         
         while(rs.next()){
             Inscripcion ins= new Inscripcion();
             ins.setIdInscripcion(rs.getInt("idInscripcion")) ;
             Alumno alu= ad.buscarAlumno(rs.getInt("idAlumno"));
             Materia mat= md.buscarMateria(rs.getInt("idMateria"));
             ins.setAlumno(alu);
             ins.setMateria(mat);
             ins.setNota(rs.getDouble("nota"));
             listaInscripcion.add(ins);
             
         }
         ps.close();
         
     } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error al conectar con tabla de inscripcion"+ ex);
  }
     return listaInscripcion ;
    }
      
      public List<Inscripcion>obtenerInscripcionesPorAlumno(int idAlumno){
     ArrayList<Inscripcion> cursadas= new ArrayList<>();
     String sql="SELECT * FROM inscripcion WHERE idAlumno = ?";
     try {
         PreparedStatement ps=con.prepareStatement(sql);
         ps.setInt(1, idAlumno);
         ResultSet rs= ps.executeQuery();
         while(rs.next()){
             
             
             Inscripcion insc= new Inscripcion();
             insc.setIdInscripcion(rs.getInt("idInscripcion"));
             
              Alumno alu= ad.buscarAlumno(rs.getInt("idAlumno"));
             Materia mat= md.buscarMateria(rs.getInt("idMateria"));
             insc.setAlumno(alu);
             insc.setMateria(mat);
             insc.setNota(rs.getDouble("nota"));
             cursadas.add(insc);  
             
         }
         
         ps.close();
     } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion");
     }
     
     return cursadas;
 }
   
       public List<Materia> obtenerMateriasCursadas( int idAlumno){
         
     ArrayList<Materia> materias= new ArrayList<>();
     
     String sql = " SELECT inscripcion.idMateria, año FROM inscripcion, "
    +" materia WHERE inscripcion.idMateria = materia.idMateria AND inscripcion.idAlumno = ?; ";
     try {
         PreparedStatement ps= con.prepareStatement(sql);
         ps.setInt(1, idAlumno);
         ResultSet rs=ps.executeQuery();
         while(rs.next()){
         Materia materia=new Materia ();
         materia.setIdMateria(rs.getInt("idMateria"));
         materia.setNombre(rs.getString("nombre"));
         materia.setAnioMateria(rs.getInt("año"));
         
         materias.add(materia);
     }
         ps.close();
         
         
         
     } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,"RError al acceder a la tabla Inscripcion");
     }
        return materias;

}
       
        public List<Materia>obtenerMateriasNOCursadas(int idAlumno){
    
    ArrayList<Materia> materias=new ArrayList<>();
    
    String sql = " SELECT * FROM materia WHERE estado = 1 AND idMateria not in "
            + " (SELECT idMateria FORM inscripcion WHERE idAlumno =?) ";
    
            
     try {
         
     
         PreparedStatement ps = con.prepareStatement(sql);
    
            ps.setInt(1,idAlumno);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                
                Materia materia= new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materias.add(materia);
                
            }
            ps.close();
             } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, "Error ");
     }
     return materias;
}
        
         public List<Inscripcion> obtenerInscipcionesPorAlumnos(int idAlumno){
    //N
         ArrayList<Inscripcion> listaInscripcion =new ArrayList<>();
         String sql=  " SELEcT * FROM inscripcion WHERE idAlumno = ? ";
          try {
         
         PreparedStatement ps= con.prepareStatement(sql);
         ps.setInt(1, idAlumno);
         ResultSet rs=ps.executeQuery();
         
         while(rs.next()){
             Inscripcion ins= new Inscripcion();
             ins.setIdInscripcion(rs.getInt("idInscripcion")) ;
             Alumno alu= ad.buscarAlumno(rs.getInt("idAlumno"));
             Materia mat= md.buscarMateria(rs.getInt("idMateria"));
             ins.setAlumno(alu);
             ins.setMateria(mat);
             ins.setNota(rs.getDouble("nota"));
             listaInscripcion.add(ins);
             
         }
         ps.close();
         
     } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error al conectar con tabla de inscripcion"+ ex);
  }
     return listaInscripcion ;
    }
         
          public List<Materia> obternerMateriaCursadas(int idAlumno){
    //N
         ArrayList<Materia> listaMateria = new ArrayList<>();
         String sql= " SELECT inscripcion.idMateria ,nombre,año FROM inscripcion,"
                 +" materia WHERE inscripcion.idMateria = materia.idMateria "
                 + " AND inscripcion.idAlumno = ? ";
          try {
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setInt(1, idAlumno);
         ResultSet rs=ps.executeQuery();
         while(rs.next()){
             Materia materia =new Materia();
             materia.setIdMateria(rs.getInt("idMateria"));
             materia.setNombre(rs.getNString("nombre"));
             materia.setAnioMateria(rs.getInt("año"));
             listaMateria.add(materia);
             
             
         }
         ps.close();
         
     } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Error al conectar con tabla de inscripcion"+ ex);
 
     }
     return listaMateria;
     
        
        
    }
          public List<Materia> obternerMateriaNoCursadas(int idAlumno){
    //N
         ArrayList<Materia> listMatNoCursa = new ArrayList<>();
         String sql= " SELECT * materia WHERE estado = 1 AND idMaterian NOT in "
                 + " SELECT idMateria FROM inscripcion WHERE idAlumno = ? ";
          try {
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setInt(1, idAlumno);
         ResultSet rs=ps.executeQuery();
         while(rs.next()){
             Materia materia =new Materia();
             materia.setIdMateria(rs.getInt("idMateria"));
             materia.setNombre(rs.getNString("nombre"));
             materia.setAnioMateria(rs.getInt("año"));
             listMatNoCursa.add(materia);
          }
         ps.close();
         
     } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Error al conectar con tabla de inscripcion"+ ex);
 
     }
     return listMatNoCursa;
     
        
        
    }
     
   public List<Alumno> obternerAlumnoPorMateria(int idMateria){
    //N
         ArrayList<Alumno> alumMatNoCursa = new ArrayList<>();
        String sql = " SELECT a.idAlumno, dni, nombre, apellido, fechaNac, estado "
                +" FROM inscripcion i, alumno a WHERE i.idAlumno = a.idAlumno AND idMateria = ? AND a.estado = 1";
    
          try {
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setInt(1, idMateria);
         ResultSet rs=ps.executeQuery();
         while(rs.next()){
             Alumno alumno =new Alumno();
             alumno.setIdAlumno(rs.getInt("idAlumno"));
             alumno.setNombre(rs.getNString("nombre"));
             alumno.setApellido(rs.getNString("apellido"));
             alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
            // alumno.setActivo(rs.getBoolean("estado"));//----no funciona------
             alumMatNoCursa.add(alumno);
             
          }
         ps.close();
         
     } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Error al conectar con tabla de inscripcion"+ ex);
 
     }
     return alumMatNoCursa;
     
        
        
    }
   
   
}
