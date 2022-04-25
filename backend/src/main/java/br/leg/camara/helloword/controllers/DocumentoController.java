package br.leg.camara.helloword.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.leg.camara.helloword.dtos.DocumentoDTO;
import br.leg.camara.helloword.models.Documento;
import br.leg.camara.helloword.services.DocumentoService;
import br.leg.camara.helloword.util.Constantes;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // Acessado de qualquer origem
@RequestMapping("/documentos")
public class DocumentoController {

	final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @PostMapping
	public ResponseEntity<Object> incluir(@RequestBody @Valid DocumentoDTO dto) {
        
		if (documentoService.existsByNome(dto.getNome())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Documento" + dto.getNome() + " ja existe!");
		}

    	Documento documento = new Documento();
        BeanUtils.copyProperties(dto, documento);
		documento.setDtRegistro(LocalDateTime.now(ZoneId.of(Constantes.ZONE_ID)));
        return ResponseEntity.status(HttpStatus.CREATED).body(documentoService.save(documento));
    }

    @GetMapping
	public ResponseEntity<Page<Documento>> recuperarTodos(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(documentoService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> recuperarPorId(@PathVariable(value = "id") UUID id) {
		Optional<Documento> DocumentoOptional = documentoService.findById(id);
		if (!DocumentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Documento nao encontrado.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(DocumentoOptional.get());
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable(value = "id") UUID id){
        Optional<Documento> DocumentoOptional = documentoService.findById(id);
        if (!DocumentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Documento nao encontrado.");
        }
        documentoService.delete(DocumentoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Documento deletado com sucesso.");
    }
    
    @PutMapping("/{id}")
	public ResponseEntity<Object> alterar(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid DocumentoDTO documentoDTO){
    	
		Optional<Documento> DocumentoOptional = documentoService.findById(id);
		if (!DocumentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Documento nao encontrado.");
		}
		
		Documento documento = new Documento();
		BeanUtils.copyProperties(documentoDTO, documento);
		documento.setId(DocumentoOptional.get().getId());
		documento.setDtRegistro(DocumentoOptional.get().getDtRegistro());
		documento.setDtAlteracao(LocalDateTime.now(ZoneId.of(Constantes.ZONE_ID)));
		return ResponseEntity.status(HttpStatus.OK).body(documentoService.save(documento));
	}
}
