
package universidad.pkg2.accesoDatos;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JOptionPane;

import universidad.pkg2.entidades.Alumno;
import universidad.pkg2.entidades.Inscripcion;
import universidad.pkg2.entidades.Materia;




public class InscripcionData {
   private Connection con = null;
   private MateriaData md= new MateriaData();
   private AlumnoData ad=new AlumnoData();


    public InscripcionData() {
        this.con = Conexion.getConexion();
    }
    

    public void guardarInscripcion(Inscripcion inscp){ 
        String sql = " INSERT INTO inscripcion (idAlumno, idMateria,nota) VALUES (?,?,?)";

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
    
    public void modificarNotas(int idAlumno, int idMateria, double nota){ //corre
        String sql= " UPDATE inscripcion  SET nota =? "
               + " WHERE idAlumno=? AND idMateria=? ";
         try {
             PreparedStatement ps= con.prepareStatement(sql);
             ps.setDouble(1, nota);
             ps.setInt(2, idAlumno);
             ps.setInt(3, idMateria);
              int modificado=ps.executeUpdate();
           
            if (modificado>0) {
                JOptionPane.showMessageDialog(null,"Nota modificada");
            }
            ps.close();
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error al ingresar a la tabla inscripcion " + ex.getMessage());
         }
    }
    
    
    public void borrarInscripcion(int idAlumno,int idMateria){ //corre
        String sql = "DELETE FROM inscripcion WHERE idAlumno=? AND idMateria =?";
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1, idAlumno);
             ps.setInt(2, idMateria);
             int borrado = ps.executeUpdate();
             if (borrado!=0) {
                 JOptionPane.showMessageDialog(null,"Inscripcion borrada");
             }
             ps.close();
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al ingresar a la tabla inscripcion " + ex.getMessage()); 
         }
    }

    
    public List<Inscripcion> obtenerInscipciones(){ // corre perfecto
    
         ArrayList<Inscripcion> listaInscripcion =new ArrayList<>();
         String sql=  " SELECT * FROM inscripcion ";
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
             JOptionPane.showMessageDialog(null,"Error al conectar con tabla de inscripcion"+ ex.getMessage());
  }
     return listaInscripcion ;
    }
    
    
    public List<Inscripcion>obtenerInscripcionesPorAlumno(int idAlumno){ // corre
           ArrayList<Inscripcion> cursadas= new ArrayList<>();
     String sql=" SELECT * FROM inscripcion WHERE idAlumno = ? ";
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
         JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion" + ex.getMessage());
     }
     
     return cursadas;
 }
    
    
          public List<Materia> obternerMateriaCursadas(int idAlumno){ // corre
         ArrayList<Materia> listaMateria = new ArrayList<>();
         String sql= " SELECT inscripcion.idMateria ,nombre,año FROM inscripcion, "
                 + " materia WHERE inscripcion.idMateria = materia.idMateria "
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
          JOptionPane.showMessageDialog(null,"Error al conectar con tabla de inscripcion"+ ex.getMessage());
 
     }
     return listaMateria;
     }
      
      
      
 public List<Materia> obternerMateriaNoCursadas(int idAlumno){ // corre
    //N
         ArrayList<Materia> listMatNoCursa = new ArrayList<>();
         String sql= " SELECT * FROM materia WHERE estado = 1 AND idMateria NOT in "
                 + " (SELECT  idMateria  FROM inscripcion WHERE  idAlumno = ? )";
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
     return listaMateria;
     }
         
     
   public List<Alumno> obternerAlumnoPorMateria(int idMateria){
    //N
         ArrayList<Alumno> alumMatNoCursa = new ArrayList<>();
        String sql = "  SELECT a.idAlumno, dni, nombre, apellido, fechaNac, estado "
                + " FROM inscripcion i, alumno a WHERE i.idAlumno = a.idAlumno AND idMateria = ? AND a.estado = 1";
    
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
             alumno.setEstado(rs.getBoolean("estado"));
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

