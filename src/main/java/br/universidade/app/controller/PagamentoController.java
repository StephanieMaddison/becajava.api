package br.universidade.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.universidade.app.model.Pagamento;
import br.universidade.app.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
	private final PagamentoService _service;
	
	@Autowired
	public PagamentoController(PagamentoService service) {
		_service = service;
	}
	
	@PostMapping
    public ResponseEntity inserir(@RequestBody Pagamento pagamento) {
        try {
            _service.inserir(pagamento);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pagamento realizado com sucesso!");

        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.CREATED).body("N�o foi poss�vel o realizar o pagamento!");
        }
    }

    @GetMapping
    public ResponseEntity listar() {
        try {
            Iterable<Pagamento> pagamentos = _service.listar();
            return ResponseEntity.status(HttpStatus.OK).body(pagamentos);
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.CREATED).body("N�o existe nenhum pagamento!");
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity listarUm(@PathVariable Long id) {
        try {
            Optional<Pagamento> pagamento = _service.listarUm(id);
            return ResponseEntity.status(HttpStatus.OK).body(pagamento);
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.CREATED).body("pagamento n�o existe!");
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody Pagamento pagamento) {
        try {
            _service.atualizar(id, pagamento);
            return ResponseEntity.status(HttpStatus.OK).body("pagamento atualizado com sucesso!!!");
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.CREATED).body("N�o foi poss�vel a atualiza��o!");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        try {
            _service.excluir(id);
            return ResponseEntity.status(HttpStatus.OK).body("pagamento exclu�dada com sucesso!!!");
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.CREATED).body("N�o � poss�vel excluir!");
        }
    }
}


