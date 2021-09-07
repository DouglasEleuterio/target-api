package br.com.douglas.target.services;

import br.com.douglas.target.models.Partner;
import br.com.douglas.target.repository.PartnerRepository;
import br.com.douglas.target.services.Exceptions.PartnerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.Optional;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    public Partner findById(String id){
        return partnerRepository.findById(Integer.parseInt(id)).orElseThrow(PartnerNotFoundException::new);
    }
}
