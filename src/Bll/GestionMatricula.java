package Bll;

import Chisaguano.BaseBllCrud;
import Chisaguano.BasePersistencia;
import Chisaguano.Util;
import beu.Curso;
import beu.Estado;
import beu.Estudiante;
import beu.Matricula;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionMatricula extends BasePersistencia<Matricula> implements BaseBllCrud<Matricula>{
    private Matricula matricula;
    private final String directorio="Matriculas";

    public GestionMatricula() {
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
    
    public String calificar(float v){
        String mensaje=" ";
        int num=this.matricula.addCalificacion(v);
        switch(num){
            case 0:
                mensaje ="Todas las notas entan registradas.\n";
                break;
            case 1:
                mensaje ="Calificacion de la Unidad I fue registrada correctamente.\n";
                break;
            case 2:
                mensaje ="Calificacion de la Unidad II fue registrada correctamente.\n";
                break;
            case 3:
                mensaje ="Calificacion de la Unidad III fue registrada correctamente.\n";
                break;
            default:
                mensaje = "Hubo un error el registrar la calificacion";
                break;
        }
        return mensaje;
    }
    
    public void promediar(){
        this.matricula.calcularPromedio();
    }
    
    public String Imprimir(){
        StringBuilder sb= new StringBuilder();
        sb.append("Estudiante: ");
        sb.append(matricula.getEstudiante()).append("\n");      
        sb.append("Curso: ");
        sb.append(matricula.getCurso().getTitulo()).append("\n");
        sb.append("Promedio: ");
        sb.append(matricula.getPromedio());
        sb.append(matricula.Imprimirdetalle()).append("\n");
        return sb.toString();
    }
    
    public void archivar() throws IOException{
       this.escribir(directorio, this.matricula.getNumero(), matricula);
    }
    
    public void Configurar(Curso cr,Estudiante est){
        this.matricula.setCurso(cr);
        this.matricula.setEstudiante(est);
    }
    
    public void anularMatricula(boolean bool){
        if(bool == true){
            matricula.setEstado(Estado.Anulada);
            Util.imprimir("La Matricula se anulo exitosamente");
            float val= matricula.getCurso().getCosto()*0.10f;
            Util.imprimir("Debe pagar una valor del 10% por la anulacion"+val);
        }
    }
    
    public List<Matricula> reportar(String titulo)throws IOException{
        List<Matricula> resultado = new ArrayList<>();
        List<String> contenidos = this.leerDirectorio(directorio, titulo);
        for(String texto: contenidos){
            GsonBuilder gb = new GsonBuilder();
            gb.setPrettyPrinting();
            Gson gson=gb.create();
            try{
                Matricula m = gson.fromJson(texto,Matricula.class);
                resultado.add(m);
            }
            catch(JsonSyntaxException ex){
                Util.imprimir("El texto no se pudo convertir en Matricula");
                Util.imprimir(ex.toString()+"\n");
            }
        }
        return resultado;
    }
    
    @Override
    public void crear() {
        matricula = new Matricula();
    }

    @Override
    public void consultar(String id) throws IOException {
        String archivo= id +"j.son";
        String contenido= this.leer(directorio, archivo);
        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson=gb.create();
        matricula = gson.fromJson(contenido,Matricula.class);
    }

    @Override
    public void actualizar() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
