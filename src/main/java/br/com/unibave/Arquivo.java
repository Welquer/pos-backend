package br.com.unibave;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(
		@NamedQuery(name = "arquivo.lista",
					query = "SELECT a FROM Arquivo a"))
public class Arquivo {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(length = 10, nullable = true)
	private Long tamanho;

	@ManyToOne
	private Repositorio repositorio;
	
	@ManyToMany
	private List<Usuario> autores = new ArrayList<Usuario>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public Repositorio getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(Repositorio repositorio) {
		this.repositorio = repositorio;
	}

	public List<Usuario> getAutores() {
		return autores;
	}

	public void addAutor(Usuario autor) {
		autores.add(autor);
	}
	
	public Long getId() {
		return id;
	}
}
