package br.leg.camara.helloword.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.leg.camara.helloword.models.Documento;
import br.leg.camara.helloword.repositories.DocumentoRepository;

@Service
public class DocumentoService {

    final DocumentoRepository documentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    @Transactional
    public Documento save(Documento documento) {
        return documentoRepository.save(documento);
    }
    
	public boolean existsByNome(String nome) {
		return documentoRepository.existsByNome(nome);
	}

    @Transactional
    public void delete(Documento documento) {
        documentoRepository.delete(documento);
    }

    public Page<Documento> findAll(Pageable pageable) {
        return documentoRepository.findAll(pageable);
    }

    public Optional<Documento> findById(UUID id) {
        return documentoRepository.findById(id);
    }
}
