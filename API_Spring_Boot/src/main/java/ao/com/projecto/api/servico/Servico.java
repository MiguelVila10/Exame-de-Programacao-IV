package ao.com.projecto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.com.projecto.api.modelo.Mensagem;
import ao.com.projecto.api.modelo.Pessoa;
import ao.com.projecto.api.repositorio.Repositorio;

@Service
public class Servico {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

   //Metodo para cadastrar pessoas
    
    public ResponseEntity<?> cadastrar(Pessoa obj){

    if (obj.getNome().equals("")) {
        mensagem.setMensagem("O Nome precisa ser preenchido");
        return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
    }else if (obj.getIdade() < 0) {
        mensagem.setMensagem("Insira uma uma idade valida");
        return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);

         }else { 
          
            return new ResponseEntity<>(acao.save(obj),HttpStatus.CREATED);

         }


    }


// Metodo para selecionar pessoas
public ResponseEntity<?> selecionar(){
    return new ResponseEntity<>(acao.findAll(),HttpStatus.OK);
}

// Metodo para selecinar pessoas apartir do codigo
public ResponseEntity<?>selecionarPeloCodigo(int codigo){

    if (acao.countByCodigo(codigo) == 0){
        mensagem.setMensagem("Nao foi encontrado nenhum registo");

        return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);

    }else {
        return new ResponseEntity<>(acao.countByCodigo(codigo),HttpStatus.OK);
}
    }

    //Metodo para editar dados

    public ResponseEntity<?> editar(Pessoa obj){

        if (acao.countByCodigo(obj.getCodigo()) == 0){
            mensagem.setMensagem("Codigo informado nao existe");
            return new ResponseEntity<>(mensagem,HttpStatus.NOT_FOUND);
       
        }else if (obj.getNome().equals(""))  {
            mensagem.setMensagem("E necessario informar um nome");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
    
             }else if (obj.getIdade() < 0) {
                mensagem.setMensagem("Informe uma idade valida");
                return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        
             } else { 
          
                return new ResponseEntity<>(acao.save(obj),HttpStatus.OK);
                
             }
    
    
     }

        // Metodo para remover rexisto
public ResponseEntity<?>remover(int codigo){

    if (acao.countByCodigo(codigo) == 0){
        mensagem.setMensagem("Codigo nao existe");

        return new ResponseEntity<>(mensagem,HttpStatus.NOT_FOUND);

    }else {
        
        Pessoa obj = acao.findByCodigo(codigo);
        acao.delete(obj);
        mensagem.setMensagem("Pessoa removida com sucesso");
        return new ResponseEntity<>(mensagem,HttpStatus.OK);

    }
}

}
    