package com.mycompany.revistasdigitales.backend.reportes;

import com.mycompany.revistasdigitales.backend.database.AdministradorDB;
import com.mycompany.revistasdigitales.backend.database.ComentarioDB;
import com.mycompany.revistasdigitales.backend.database.MeGustaDB;
import com.mycompany.revistasdigitales.backend.revistas.Revista;

import java.util.List;

public class RevistasPopulares {

    private final AdministradorDB administradorDB = new AdministradorDB();
    //esta clase debe de obtener una lista de 5 revistas mas populares, esto basado en el numero de likes


    public List<Revista> revistasConLikes() {
        List<Revista> revistas = administradorDB.obtenerTodasLasRevistas();
        MeGustaDB meGustaDB = new MeGustaDB();
        for (Revista revista : revistas) {
            revista.setLikes(meGustaDB.obtenerCantidadMeGustaPorRevista(revista.getNombre()));
        }
        // Ordenar la lista de revistas por número de likes
        revistas = ordenarRevistasPorLikes(revistas);

        // Devolver las 5 primeras revistas o menos si hay menos de 5
        return revistas.size() > 5 ? revistas.subList(0, 5) : revistas;
    }

    public List<Revista> ordenarRevistasPorLikes(List<Revista> revistas) {
        revistas.sort((revista1, revista2) -> revista2.getLikes() - revista1.getLikes());
        return revistas;
    }

    public List<Revista> revistasConComentarios() {
        List<Revista> revistas = administradorDB.obtenerTodasLasRevistas();
        ComentarioDB comentarioDB = new ComentarioDB();
        for (Revista revista : revistas) {
            revista.setComentarios(comentarioDB.obtenerComentariosPorRevista(revista.getNombre()));
        }
        // Ordenar la lista de revistas por número de comentarios
        revistas = ordenarRevistasPorComentarios(revistas);

        // Devolver las 5 primeras revistas o menos si hay menos de 5
        return revistas.size() > 5 ? revistas.subList(0, 5) : revistas;
    }

    public List<Revista> ordenarRevistasPorComentarios(List<Revista> revistas) {
        revistas.sort((revista1, revista2) -> revista2.getComentarios().size() - revista1.getComentarios().size());
        return revistas;
    }

}
