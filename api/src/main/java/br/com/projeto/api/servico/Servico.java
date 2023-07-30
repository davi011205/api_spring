package br.com.projeto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;

@Service
public class Servico {
    
    @Autowired 
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    // Metodo para validar um cadastro/registro
    public ResponseEntity<?> validateRegister(Pessoa obj) {
        if(obj.getNome().equals("")) { //se nome = vazio
            mensagem.setMensagem("O nome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else if(obj.getIdade() < 0) { //se a idade for menor que 0
            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else { //passou nos testes de validacao e fez o registro
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    // Metodo para listar todas as pessoas
    public ResponseEntity<?> select() {
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // Metodo para listar pessoa pelo codigo
    public ResponseEntity<?> selectByCede(int codigo) {
        if(acao.countByCodigo(codigo) == 0) { //conta quantas vezes o codigo aparece, caso 0 eh pq não existe na tabela
            mensagem.setMensagem("não foi encontrada nenhuma pessoa");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
    }

    // Metodo para editar dados
    public ResponseEntity<?> edit(Pessoa obj) {
        if(acao.countByCodigo(obj.getCodigo()) == 0) {
            mensagem.setMensagem("O codigo informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        else if(obj.getNome().equals("")) {
            mensagem.setMensagem("É necessário informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else if(obj.getIdade() < 0) {
            mensagem.setMensagem("informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
    }

    // Metodo para remover registros
    public ResponseEntity<?> remove(int codigo) {
        if(acao.countByCodigo(codigo) == 0) {
            mensagem.setMensagem("O código não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        else {
            Pessoa obj = acao.findByCodigo(codigo);
            acao.delete(obj);
            
            mensagem.setMensagem("Pessoa removida com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }
}
