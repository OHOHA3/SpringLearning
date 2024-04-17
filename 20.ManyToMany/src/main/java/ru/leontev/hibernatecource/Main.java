package ru.leontev.hibernatecource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.leontev.hibernatecource.model.Actor;
import ru.leontev.hibernatecource.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Movie movie = new Movie("Pulp fiction", 1994);
            Actor actor1 = new Actor("Harvey Keitel", 81);
            Actor actor2 = new Actor("Samuel L. Jackson", 72);

            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
            session.save(movie);
            session.save(actor1);
            session.save(actor2);

            Movie movie1 = session.get(Movie.class, 1);
            System.out.println(movie1.getActors());

            Movie movie2 = new Movie("Reservoir Dogs", 1992);
            Actor actor3 = session.get(Actor.class, 1);
            movie2.setActors(new ArrayList<>(Collections.singletonList(actor3)));
            actor3.getMovies().add(movie2);
            session.save(movie2);

            Actor actor4 = session.get(Actor.class, 2);
            Movie movieToDelete = actor4.getMovies().get(0);
            actor4.getMovies().remove(movieToDelete);
            movieToDelete.getActors().remove(actor4);

            session.get(Actor.class, 2).
                    setMovies(new ArrayList<>(Collections.singletonList(session
                            .get(Movie.class, 1))));

            session.getTransaction().commit();
        }
    }
}
