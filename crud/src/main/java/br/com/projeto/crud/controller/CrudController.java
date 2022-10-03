package br.com.projeto.crud.controller;

import br.com.projeto.crud.model.Cliente;
import br.com.projeto.crud.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/clientes"})
public class CrudController {

    @Autowired
    private CrudRepository repository;

    @GetMapping
    public List<Cliente> listarClientes(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity listarClientesById(@PathVariable Long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"nome"})
    public ResponseEntity listarClientesByNome(@RequestParam String nome){
        return repository.findByNome(nome)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente incluirCliente(@Valid @RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity alterarClienteById(@Valid @PathVariable Long id, @RequestBody Cliente cliente){
        return repository.findById(id)
                .map(record -> {
                    record.setNome(cliente.getNome());
                    record.setNasc(cliente.getNasc());
                    Cliente updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity <?> deletarClienteById(@PathVariable Long id){
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
