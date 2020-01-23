package beu;

import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Chisaguano.Persona{
    private String carrera;
    private final List<String> clubes = new ArrayList<>();

    public Estudiante() {
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    public void addClub(String club){
        if(! club.isEmpty()){
            this.clubes.add(club);
        }
    }
    
    public List<String> getClubes(){
        return this.clubes;
    }
}
