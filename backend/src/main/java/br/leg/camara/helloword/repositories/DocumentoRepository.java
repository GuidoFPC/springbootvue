package br.leg.camara.helloword.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.leg.camara.helloword.models.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, UUID> {

	boolean existsByNome(String nome);

}
