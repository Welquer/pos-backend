package br.com.unibave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "repositorio.lista",
				query = "SELECT r FROM Repositorio r")})
public class Repositorio {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 200, nullable = false)
	private String url;
	
	@OneToMany(mappedBy = "repositorio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Arquivo> arquivos = new ArrayList<Arquivo>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}
	
	public void addArquivo(Arquivo arquivo) {
		arquivo.setRepositorio(this);
		arquivos.add(arquivo);
	}
	
	public void removeContato(Arquivo arquivo) {
		arquivos.remove(arquivo);
	}

	public List<Arquivo> getArquivos() {
		return Collections.unmodifiableList(arquivos);
	}
}
