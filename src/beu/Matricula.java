package beu;

import Chisaguano.Persona;
import Chisaguano.unidad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class Matricula {
    private final String numero;
    private Calendar fecha;
    private Estado estado;
    private Persona estudiante;
    private Curso curso;
    private List<Calificacion> calificaciones = new ArrayList<>();
    //Informacion;
    private float promedio;

    public Matricula() {
        fecha=Calendar.getInstance();
        estado=Estado.Registrada;
        UUID numeroAleatorio = UUID.randomUUID();
        this.numero= numeroAleatorio.toString();
    }

    public String getNumero() {
        return numero;
    }
    
    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Persona getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Persona estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }
    
    public void calcularPromedio(){
        if (this.calificaciones.isEmpty()){
            return;
        }
        float suma=0;
        for(Calificacion c: calificaciones){
            suma+=c.valor;//Se puede acceder porque es una clase interna
        }
        int divisor =this.calificaciones.size();
        promedio= (float) suma/(float)3;
        if(divisor==3){
            if(promedio>14){
                this.estado=Estado.Aprobada;
            }
        }
    }
    
    //Este metodo registra un calificacion y retorna un numero
    //1 si es la nota de la Unidad I
    //2 si es la nota de la Unidad II
    //3 si es la nota de la Unidad III
    //0 si es la nota de la Unidad I
    
    public int addCalificacion(float v){
        Calificacion cal=new Calificacion();
        int cuentanotas= this.calificaciones.size();
        switch(cuentanotas){
            case 0:
                cal.setUnidad(unidad.I);
                break;
            case 1:
                cal.setUnidad(unidad.II);
                break;
            case 2:
                cal.setUnidad(unidad.III);
                break;
            default:return 0;//En caso de tener todas las notas      
        }
        cal.setValor(v);
        cal.setFecha(Calendar.getInstance());
        this.calificaciones.add(cal);
        this.calcularPromedio();
        return this.calificaciones.size();//Retorna el tamaña de la lista
    }
    
    @Override
    public String toString() {
        return estudiante.toString()+" #"+numero;
    }
    
    public String tosave(){
        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson= gb.create();
        return gson.toJson(this);
    }
    
    public List<Calificacion> getCalificacione(){
        return this.calificaciones;
    }
    
    public String Imprimirdetalle(){
        String str="\n\t"+this.estudiante;
        for(Calificacion c: this.calificaciones){
            str+="\t\t"+ c.getValor();
        }
        str+="\t\t"+this.promedio+"\n";
        return str;
    }
    
    class Calificacion{
        private Calendar fecha;
        private float valor;
        private unidad unidad;

        public Calificacion() {
        }
        
        public Calendar getFecha() {
            return fecha;
        }

        public void setFecha(Calendar fecha) {
            this.fecha = fecha;
        }

        public float getValor() {
            return valor;
        }

        public void setValor(float valor) {
            this.valor = valor;
        }

        public unidad getUnidad() {
            return unidad;
        }

        public void setUnidad(unidad unidad) {
            this.unidad = unidad;
        }   
    }  
}
