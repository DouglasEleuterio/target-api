package br.com.douglas.target.services;

import br.com.douglas.target.dtos.MessageDto;
import br.com.douglas.target.models.Category;
import br.com.douglas.target.repository.CategoryRepository;
import br.com.douglas.target.security.jwt.AuthEntryPointJwt;
import br.com.douglas.target.services.Exceptions.CategoryNotFoundException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.douglas.target.services.Utils.validateId;


import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Autowired
    CategoryRepository categoryRepository;

    public MessageDto getAll() {
        MessageDto messageDto = new MessageDto("List of Categories: ", new Date(), categoryRepository.findAll());
        return messageDto;
    }

    public MessageDto save(Category category) {
        try {
            Category toSave;
            toSave = categoryRepository.save(category);
            return new MessageDto("Category saved: ", Date.from(Instant.now()), toSave);
        } catch (Exception e) {
            logger.error("Category not Saved: {}", e.getMessage());
            return null;
        }
    }

    public MessageDto findByName(String name) throws ObjectNotFoundException {
        MessageDto messageDto = null;
        try {
            List<Category> categories = categoryRepository.findByName(name).orElseThrow(ClassNotFoundException::new);
            messageDto = new MessageDto("Categorie(s) located: | " + categories.size(), new Date(), categories);
        } catch (ClassNotFoundException e) {
            logger.info("Category not Found: {}", name);
            throw new ObjectNotFoundException(name);
        }
        return messageDto;
    }

    public MessageDto findById(String id) throws CategoryNotFoundException {
        MessageDto messageDto = null;
        try {
            Integer identifier = validateId(id);
            Category category = categoryRepository.findById(identifier).orElseThrow(ClassNotFoundException::new);
            messageDto = new MessageDto("Category located: ", new Date(), category);
        } catch (ClassNotFoundException e) {
            logger.info("Category not Found: {}", id);
            throw new CategoryNotFoundException("Category with id: " +id + ", not found");
        }
        return messageDto;
    }

    public MessageDto delete(String id) {
        MessageDto messageDto = null;
        try {
            Integer identifier = validateId(id);
            Category searched = categoryRepository.findById(identifier).orElseThrow(ClassNotFoundException::new);
            categoryRepository.delete(searched);
            messageDto = new MessageDto("Category deleted: " + searched.getName(), new Date(), null);
        } catch (NumberFormatException e) {
            messageDto = new MessageDto("Id: " + id + " mal formated", new Date(), null);
            logger.error("Id mal formated: {}", id);
        } catch (ClassNotFoundException e) {
            messageDto = new MessageDto("Category id: " + id + " Not located!", new Date(), null);
            logger.error("Category not found: {}", id);
        }
        return messageDto;
    }
}
