package br.com.projeto.api.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Cliente;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;
import br.com.projeto.api.servico.Servico;
import jakarta.validation.Valid;

@RestController
public class Controle {

    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico;
    
    @PostMapping("/api")
    public ResponseEntity<?> create(@RequestBody Pessoa obj) {
        // cadastrar
        return servico.validateRegister(obj);
    }

    @GetMapping("/api")
    public ResponseEntity<?> selectAll() {
        // selecionar todos os registros
        return servico.select();
    }

    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> selectByCode(@PathVariable int codigo) {
        // selecionar pelo codigo
        return servico.selectByCede(codigo);
    }
    
    @PutMapping("/api")
    public ResponseEntity<?> edit(@RequestBody Pessoa obj) {
        // editar
        return servico.edit(obj);
    }

    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remove(@PathVariable int codigo) {
        // remover
        return servico.remove(codigo);
    }

    @GetMapping("/api/contarPessoas")
    public long countRegister() {
        // contar registros
        return acao.count();
    }

    @GetMapping("/api/ordenarNomes")
    public List<Pessoa> orderByName() {
        // ordena por nome
        return acao.findByOrderByNome();
    }

    @GetMapping("/api/ordenarNomesPorIdade")
    public List<Pessoa> orderSpecificNameByAge() {
        // seleciona pessoas com nome x e as ordena por idade
        return acao.findByNomeOrderByIdade("Davi");
    }

    @GetMapping("/api/nomeContem")
    public List<Pessoa> selectNameByTerm(){
        // seleciona todos os nomes que contenham x
        return acao.findByNomeContaining("l");
    }

    @GetMapping("/api/nomeComeca")
    public List<Pessoa> selectNameByStartWith(){
        return acao.findByNomeStartsWith("a");
    }

    @GetMapping("/api/nomeTermina")
    public List<Pessoa> selectNameByEndWith(){
        return acao.findByNomeStartsWith("i");
    }

    @GetMapping("/api/somaIdades")
    public int somaIdades(){
        return acao.somaIdades();
    }

    @GetMapping("/api/idadeMaiorIgual")
    public List<Pessoa> idadeMiorIgual() {
        return acao.idadeMaiorIgual(18);
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente obj) {
        
    }
}
