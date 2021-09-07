package br.com.douglas.target.controllers;

import br.com.douglas.target.dtos.CartRequestDto;
import br.com.douglas.target.dtos.MessageDto;
import br.com.douglas.target.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/shopping")
public class SoppingCartController {

    @Autowired
    private CartService service;

    @PostMapping()
    public ResponseEntity<MessageDto> checkout(@RequestBody CartRequestDto cart) {
        MessageDto cartResponse = service.calculate(cart);
        return ResponseEntity.ok(cartResponse);
    }
}
