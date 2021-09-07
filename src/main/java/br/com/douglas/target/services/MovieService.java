package br.com.douglas.target.services;

import br.com.douglas.target.dtos.MessageDto;
import br.com.douglas.target.models.Movie;
import br.com.douglas.target.repository.MovieRepository;
import br.com.douglas.target.security.jwt.AuthEntryPointJwt;
import javassist.tools.rmi.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Autowired
    MovieRepository movieRepository;

    public MessageDto save(Movie movie) {
        try {
            Movie toSave = movieRepository.save(movie);
            return new MessageDto("Movie saved: ", Date.from(Instant.now()), toSave);
        } catch (Exception e) {
            logger.error("Movie not Saved: {}", e.getMessage());
            return null;
        }
    }

    public MessageDto findByTitle(String title) throws ObjectNotFoundException {
        MessageDto messageDto = null;
        try {
            List<Movie> movies = movieRepository.findByTitle(title).orElseThrow(ClassNotFoundException::new);
            messageDto = new MessageDto("Movie(s) located: | " + movies.size(), new Date(), movies);
        } catch (ClassNotFoundException e) {
            logger.info("Movie not Found: {}", title);
            throw new ObjectNotFoundException(title);
        }
        return messageDto;
    }

    public MessageDto findById(String id) throws ObjectNotFoundException {
        MessageDto messageDto = null;
        try {
            Long identifier = validateId(id);
            Movie movie = movieRepository.findById(identifier).orElseThrow(ClassNotFoundException::new);
            messageDto = new MessageDto("Movie located: ", new Date(), movie);
        } catch (ClassNotFoundException e) {
            logger.info("Movie not Found: {}", id);
            throw new ObjectNotFoundException(id);
        }
        return messageDto;
    }

    public MessageDto getTotalMovies() {
        MessageDto messageDto = new MessageDto("Total of Movies: " + movieRepository.findAll().size(), new Date(), null);
        return messageDto;
    }

    public MessageDto getTotalMoviesByGenre() {

        List<Movie> all = movieRepository.findAll();
        Map<String, Integer> map = new HashMap<>();
        int integer = 0;
        for (Movie movie : all) {
            if (map.containsKey(movie.getGenre())) {
                map.put(movie.getGenre(), integer++);
            }else {
                map.put(movie.getGenre(), 1);
            }
        }
        MessageDto messageDto = new MessageDto("Total of Movies by Genre", new Date(), map);

        return messageDto;
    }

    public MessageDto getAll() {
        MessageDto messageDto = new MessageDto("List of Movies: ", new Date(), movieRepository.findAll());
        return messageDto;
    }

    public MessageDto delete(String id) {
        MessageDto messageDto = null;
        try {
            Long identifier = validateId(id);
            Movie searched = movieRepository.findById(identifier).orElseThrow(ClassNotFoundException::new);
            movieRepository.delete(searched);
            messageDto = new MessageDto("Movie deleted: " + searched.getTitle(), new Date(), null);
        } catch (NumberFormatException e) {
            messageDto = new MessageDto("Id: " + id + " mal formated", new Date(), null);
            logger.error("Id mal formated: {}", id);
        } catch (ClassNotFoundException e) {
            messageDto = new MessageDto("Movie id: " + id + " Not located!", new Date(), null);
            logger.error("Id mal formated: {}", id);
        }
        return messageDto;

    }

    private Long validateId(String id) {
        try {
            Long identifier = Long.parseLong(id);
            return identifier;
        } catch (NumberFormatException e) {
            logger.error("Id mal formated: {}", id);
            return null;
        }
    }

}
