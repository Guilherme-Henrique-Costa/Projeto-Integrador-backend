package org.example.voluntariadointeligentehub.controller;

import jakarta.validation.Valid;
import org.example.voluntariadointeligentehub.entity.PerfilInstituicao;
import org.example.voluntariadointeligentehub.service.CadastroInstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/perfil-instituicao")
@CrossOrigin(origins = "http://localhost:4200")  // Permitir requisições do frontend
public class CadastroInstituicaoController {

    @Autowired
    private CadastroInstituicaoService cadastroInstituicaoService;

    @PostMapping
    public ResponseEntity<PerfilInstituicao> cadastrarInstituicao(
            @Valid @RequestBody PerfilInstituicao perfilInstituicao) {
        // Chama o serviço para salvar a instituição
        PerfilInstituicao instituicaoCadastrada = cadastroInstituicaoService.cadastrarInstituicao(perfilInstituicao);
        // Retorna a instituição criada com status 201 Created
        return new ResponseEntity<>(instituicaoCadastrada, HttpStatus.CREATED);
    }

    // Tratamento para erros de validação dos dados enviados
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Tratamento para exceções específicas de negócio, como email ou CNPJ duplicado
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Tratamento de exceções genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Retorna o erro com o status HTTP apropriado
        return new ResponseEntity<>("Erro ao cadastrar instituição: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
