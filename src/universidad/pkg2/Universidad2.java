
package universidad.pkg2;

import java.time.LocalDate;
import universidad.pkg2.accesoDatos.AlumnoData;
import universidad.pkg2.accesoDatos.MateriaData;
import universidad.pkg2.entidades.Alumno;
import universidad.pkg2.entidades.Materia;
import universidad.pkg2.accesoDatos.InscripcionData;
import universidad.pkg2.entidades.Inscripcion;
public class Universidad2 {

   
    public static void main(String[] args) {
         
        //trabajamos con la clase alumno
        
//      Alumno Angel = new Alumno(8,238745679,"Quiroga","Miguel", LocalDate.of(1990,1,18),true);
//       Alumno Marce = new Alumno(10, 3748553, "Gonzalez", "Maricel", LocalDate.of(1990, 3, 12), true);
//       AlumnoData alumn = new AlumnoData();
//       alumn.guardarAlumno(Marce);
//       alumn.modificarAlumno(Marce);       
//       alumn.eliminarAlumno(Marce);

//
//       Alumno alumnoEncontrado = alumn.buscarAlumno(9);
//        if (alumnoEncontrado != null) {
//            System.out.println("dni : " + alumnoEncontrado.getDni());
//            System.out.println("apelido :" + alumnoEncontrado.getApellido());
//            System.out.println("nombre  :" + alumnoEncontrado.getNombre());
//            System.out.println("fecha de nacimiento : " + alumnoEncontrado.getFechaNac());
//        }


//        Alumno alumnoEncontrado = alumn.buscarAlumnoDni(1234);
//        if (alumnoEncontrado != null) {
//            System.out.println("dni : " + alumnoEncontrado.getDni());
//            System.out.println("apelido :" + alumnoEncontrado.getApellido());
//            System.out.println("nombre  :" + alumnoEncontrado.getNombre());
//            System.out.println("fecha de nacimiento : " + alumnoEncontrado.getFechaNac());
//        }
        
      
//        AlumnoData alumn = new AlumnoData();
//        for (Alumno alumno :alumn.listarAlumnos()) {
//            System.out.println(" Id de alumno : " + alumno.getIdAlumno());
//            System.out.println(" DNI alumno : " + alumno.getDni());
//            System.out.println(" Apellido del alumno : " + alumno.getApellido());
//            System.out.println(" Nombre del alumno  : " + alumno.getNombre());
//            System.out.println(" Fecha de nacimiento del alumno : " + alumno.getFechaNac());
//            System.out.println("-------------------------------------------------------------------");
//        }


//        AlumnoData alumn = new AlumnoData();
//        for (Alumno alumno : alumn.listarAlumnosBaja()) {
//            System.out.println(" Id de alumno : " + alumno.getIdAlumno());
//            System.out.println(" DNI alumno : " + alumno.getDni());
//            System.out.println(" Apellido del alumno : " + alumno.getApellido());
//            System.out.println(" Nombre del alumno  : " + alumno.getNombre());
//            System.out.println(" Fecha de nacimiento del alumno : " + alumno.getFechaNac());
//            System.out.println("-------------------------------------------------------------------");
//        }

//trabajamos con la clase materia

//            Materia mat = new Materia(10 ,"Redes", 3,true);
//            MateriaData nueva = new MateriaData(); // me mofifica a nombre de la materia
//            nueva.agregarMateria(mat);
//            System.out.println("Se agrego la materia : " + mat.getNombre());
//            System.out.println("Se agrego la materia : " + mat.getAnioMateria());
//            System.out.println("Se agrego la materia : " + mat.getIdMateria());
//            nueva.modificarMateria(10, 2);
//            System.out.println("  Id Materia modificada : "  + mat.getIdMateria());
//            System.out.println(" Materia modificada : "  + mat.getNombre());
//            System.out.println(" Año modificado : "  + mat.getAnioMateria());
//
//            nueva.eliminarMateria(1);

//
//        MateriaData mat = new MateriaData();
//        Materia materiaEncontrada = mat.buscarMateria(10);
//        System.out.println(" Nombre : " + materiaEncontrada.getNombre());
//        System.out.println(" Año : " + materiaEncontrada.getAnioMateria());
//


//            MateriaData mat = new MateriaData();
//        for (Materia materia :mat.listarMateriasActivas()) {
//            System.out.println(" Id de materia : " + materia.getIdMateria());
//            System.out.println(" Nombre de la Materia : " + materia.getNombre());
//            System.out.println("Año de la materia : " + materia.getAnioMateria());
//            System.out.println("-------------------------------------------------------------------");
//        }

//
//           MateriaData mat = new MateriaData();
//        for (Materia materia :mat.listarMateriasBajas()) {
//            System.out.println(" Id de materia : " + materia.getIdMateria());
//            System.out.println(" Nombre de la Materia : " + materia.getNombre());
//            System.out.println("Año de la materia : " + materia.getAnioMateria());
//            System.out.println("-------------------------------------------------------------------");
//        }
       
       
        
        //trabajamos con la clase inscripcion
        //DE ACA PARA ABAJO ES LA PRUEBA DE NORMA
        
        
//        AlumnoData alum = new AlumnoData();
//        MateriaData mat = new MateriaData();
//        InscripcionData ins = new InscripcionData();
//        Alumno Lara = alum.buscarAlumno(2);
//        Materia asig = mat.buscarMateria(2);
//        Inscripcion inscp = new Inscripcion(9, Lara, asig);//no coincide nombre, pero corre
//        ins.guardarInscripcion(inscp);
//        ins.modificarNotas(1, 3, 9); // corre perfecto
//        ins.borrarInscripcion(1, 1); // corre perfecto



   
        AlumnoData ad = new AlumnoData();
        MateriaData md = new MateriaData();
        InscripcionData id = new InscripcionData();
          
//        Alumno Miguel = ad.buscarAlumno(8);
//        Materia mate = md.buscarMateria(2);
//        Inscripcion insc = new Inscripcion(8, Miguel, mate);
//            id.guardarInscripcion(insc);
//
//           id.modificarNota(9,8,2);
//           id.borrarInscripcion(8,2);

                   
// for (Inscripcion inscripcion : id.obtenerInscipciones()) { // corre perfecto
//            System.out.println(" id : " + inscripcion.getIdInscripcion());
//            System.out.println(" Alumno : " + inscripcion.getAlumno().getApellido());
//            System.out.println("Materia : " + inscripcion.getMateria().getNombre());
//            System.out.println("Nota : " + inscripcion.getNota());
//            System.out.println("*******************************************************");
//
//        }


// for (Inscripcion inscripcion : id.obtenerInscripcionesPorAlumno(2)) { // corre perfecto
//            System.out.println(" Apellido : " + inscripcion.getAlumno().getApellido());
//            System.out.println(" Nombre  : " + inscripcion.getAlumno().getNombre());
//            System.out.println(" DNI  : " + inscripcion.getAlumno().getDni());
//            System.out.println(" Fecha de Nacimiento  : " + inscripcion.getAlumno().getFechaNac());
//            System.out.println("Materia : " + inscripcion.getMateria().getNombre());
//            System.out.println(" Nombre  : " + inscripcion.getMateria().getAnioMateria());
//            System.out.println("Nota : " + inscripcion.getNota());
//            System.out.println("*******************************************************");
//        }

//        for (Materia materia : id.obternerMateriaCursadas(7)) { // corre perfecto
//            System.out.println("nombre " + materia.getNombre());

            
//        for (Materia materia : id.obternerMateriaNoCursadas(1)) { // corre perfecto
//            System.out.println("nombre " + materia.getNombre());
//        }  



 // falta solo esta funcion
//      for (Materia materia: id.obternerAlumnoPorMateria(7)) { 
//            System.out.println("nombre " + materia.getNombre());
//        }  
        
      
    }
    }
    
    

    
    
    

    
