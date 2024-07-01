package ao.com.projecto.api.controler;

import org.springframework.web.bind.annotation.RestController;

import ao.com.projecto.api.modelo.Pessoa;
import ao.com.projecto.api.repositorio.Repositorio;
import ao.com.projecto.api.servico.Servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class Controle {
    
    @Autowired
    private Repositorio acao;

    @Autowired
     private Servico servico;

@PostMapping("/api")
public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj){
    return servico.cadastrar(obj);
}
@GetMapping("/api")

public ResponseEntity<?> selecionar() {
   
    return servico.selecionar();
}

@GetMapping("/api/{codigo}")
public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo){

       return servico.selecionarPeloCodigo(codigo);
}
@PutMapping("/api")
public ResponseEntity<?> editar(@RequestBody Pessoa obj){
    return servico.editar(obj);
}


@DeleteMapping("/api/{codigo}")
public ResponseEntity<?>remover(@PathVariable int codigo){
    return servico.remover(codigo);
}

@GetMapping("/api/contador")
public long contador(){
return acao.count();

}
 
@GetMapping("/api/ordenarNames")
public List<Pessoa> ordenarNames(){

    return acao.findByOrderByNome();
}

@GetMapping("")

    public String mensagem(){
        return "Saja Bem Vindo ";
    }

@PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p){
        return p;
    }

@GetMapping("/status")
    public ResponseEntity<?> status (){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
 