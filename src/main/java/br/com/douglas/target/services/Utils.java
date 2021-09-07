package br.com.douglas.target.services;

import br.com.douglas.target.security.jwt.AuthEntryPointJwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    public static Integer validateId(String id) {
        try {
            Integer identifier = Integer.parseInt(id);
            return identifier;
        } catch (NumberFormatException e) {
            logger.error("Id mal formated: {}", id);
            return null;
        }
    }
}
