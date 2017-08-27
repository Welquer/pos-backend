package br.com.unibave;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(
		@NamedQuery(name = "usuario.lista",
					query = "SELECT u FROM Usuario u"))
public class Usuario {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL)
	private List<Arquivo> arquivosCriados;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Arquivo> getArquivosCriados() {
		return arquivosCriados;
	}

	public Long getId() {
		return id;
	}
	
	public void addArquivo(Arquivo arquivo) {
		arquivosCriados.add(arquivo);
	}
}
