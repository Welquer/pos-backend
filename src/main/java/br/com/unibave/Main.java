package br.com.unibave;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("welquer-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//Insere o repositório
		Repositorio repositorio = new Repositorio();
		repositorio.setUrl("localhost");
		em.persist(repositorio);
		tx.commit();
		
		//Cria o usuário autor
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		Usuario primeiroAutor = new Usuario();
		primeiroAutor.setNome("Welquer");
		em.persist(primeiroAutor);
		tx.commit();
		
		//Cria o usuário autor
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		Usuario segundoAutor = new Usuario();
		segundoAutor.setNome("Brenda");
		em.persist(segundoAutor);
		tx.commit();
		
		//Cria o arquivo
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		Arquivo arquivo = new Arquivo();
		arquivo.setNome("arquivoUm.docx");
		arquivo.setTamanho(2000l);
		arquivo.addAutor(primeiroAutor);
		arquivo.addAutor(segundoAutor);
		arquivo.setRepositorio(repositorio);
		em.persist(arquivo);
		tx.commit();

		//Cria mais um arquivo
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		Arquivo arquivoDois = new Arquivo();
		arquivoDois.setNome("arquivoDois.doc");
		arquivoDois.setTamanho(1000l);
		arquivoDois.addAutor(segundoAutor);
		arquivoDois.setRepositorio(repositorio);
		em.persist(arquivoDois);
		tx.commit();

		//Imprime os autores dos arquivos
		em = emf.createEntityManager();
		List<Repositorio> repositorioLogs = em.createNamedQuery("repositorio.lista")
												.getResultList();
		repositorioLogs.forEach(r -> r.getArquivos()
										.forEach(a -> a.getAutores()
														.forEach(u -> System.out.println("Repositório: "+r.getUrl()+" Autor: "+u.getNome()+" ID do arquivo: "+a.getId()+" Nome do arquivo: "+a.getNome()))));
		
		//Deleta o repositório
		em = emf.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		repositorio = (Repositorio) em.createNamedQuery("repositorio.lista").getSingleResult();
		em.remove(repositorio);
		tx.commit();
		em.close();
		
		emf.close();
	}
}
