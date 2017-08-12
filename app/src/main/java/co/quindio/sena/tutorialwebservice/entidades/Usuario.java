package co.quindio.sena.tutorialwebservice.entidades;

/**
 * Created by CHENAO on 6/08/2017.
 */

public class Usuario {

    private Integer documento;
    private String nombre;
    private String profesion;

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
}
