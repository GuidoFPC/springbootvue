package br.leg.camara.helloword.dtos;

import javax.validation.constraints.NotBlank;

public class DocumentoDTO {

    @NotBlank
	private String nome;

    @NotBlank
	private String tipo; // memorando, circular, decreto, lei

    @NotBlank
	private String classificacao; // publico, sigiloso

    @NotBlank
	private String conteudo; // publico, sigiloso
    
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
}
