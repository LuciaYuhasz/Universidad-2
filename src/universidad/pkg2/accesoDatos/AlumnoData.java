
package universidad.pkg2.accesoDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import universidad.pkg2.entidades.Alumno;


public class AlumnoData {
    private Connection con = null;

    public AlumnoData() {
        con = Conexion.getConexion();
    }
   public void guardarAlumno(Alumno alumno){  //agrego alumno sentencia insert
       String sql= " INSERT INTO alumno(dni,apellido,nombre,fechaNac, estado) " 
               + " VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps= con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3,alumno.getNombre());
            ps.setDate (4, Date.valueOf( alumno.getFechaNac()));
            ps.setBoolean(5, alumno.isEstado());
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            if (rs.next()) {
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null,"Alumno Agregado exitosamente");
                
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a tabla alumno" + ex.getMessage());
        }
   }
    
    
    
    public void modificarAlumno(Alumno alumno){ //mofifico alumno sentencia update
       String sql= " UPDATE alumno SET dni=?,apellido =?,nombre =?, fechaNac =? "
               + " WHERE idAlumno=?";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3,alumno.getNombre());
            ps.setDate (4, Date.valueOf( alumno.getFechaNac()));
            ps.setInt(5, alumno.getIdAlumno());
            int modificado=ps.executeUpdate();
           
            if (modificado == 1) {
                JOptionPane.showMessageDialog(null,"Alumno modificado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a tabla alumno" + ex.getMessage());
        }
   }
    
    public void eliminarAlumno(Alumno alumno){ // elimino alumno sentencia update
       String sql= " UPDATE alumno SET estado = 0 WHERE idAlumno=? ";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            
            ps.setInt(1, alumno.getIdAlumno());
            int modificado=ps.executeUpdate();
           
            if (modificado == 1) {
                JOptionPane.showMessageDialog(null,"Alumno eliminado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a tabla alumno" + ex.getMessage());
        }
   }
    
    public Alumno buscarAlumno(int id){  //busca alumno con id = 2 sentencia select
        String sql= "SELECT dni, apellido, nombre,fechaNac FROM alumno WHERE idAlumno= ? AND estado = 1";
        Alumno alumno = null;
         try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(id);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alumno.setEstado(true);
            }else{
                JOptionPane.showMessageDialog(null,"Alumno No encontrado");
                ps.close();
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a tabla alumno" + ex.getMessage());
        }
        
        return alumno;
    }
    

    public Alumno buscarAlumnoDni(int  dni) { // busco alumno por dni
        String sql= " SELECT idAlumno,dni, apellido, nombre,fechaNac FROM alumno  WHERE dni= ? AND estado = 1 ";
        Alumno alumno = null;
         try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alumno.setEstado(true);
            }else{
                JOptionPane.showMessageDialog(null,"Alumno No encontrado");
                ps.close();
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a tabla alumno" + ex.getMessage());
        }
        
        return alumno;
    }
 
     public List<Alumno>listarAlumnos() { //lista de alumnos activos
        String sql= "SELECT idAlumno,dni, apellido, nombre,fechaNac FROM alumno WHERE  estado = 1";
        ArrayList<Alumno> alumnos = new ArrayList<>();
                
         try {
        PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setIdAlumno(rs.getInt("idAlumno"));
                    alumno.setDni(rs.getInt("dni"));
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    alumno.setEstado(true);
                    alumnos.add(alumno);
                    
                }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a tabla alumno");
        
        }
        return alumnos;
}
      public List<Alumno>listarAlumnosBaja() { //lista de alumnos activos
        String sql= "SELECT idAlumno,dni, apellido, nombre,fechaNac FROM alumno WHERE  estado = 0";
        ArrayList<Alumno> alumnos = new ArrayList<>();
                
         try {
        PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setIdAlumno(rs.getInt("idAlumno"));
                    alumno.setDni(rs.getInt("dni"));
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                    alumno.setEstado(true);
                    alumnos.add(alumno);
                    
                }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a tabla alumno" + ex.getMessage());
        
        }
        return alumnos;
      }
}
