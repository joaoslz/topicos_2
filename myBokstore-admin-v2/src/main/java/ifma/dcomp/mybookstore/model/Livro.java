package ifma.dcomp.mybookstore.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message="{livro.form.titulo.obrigatoria}")
	@Size(min=2, message="{livro.form.titulo.tamanho}")
	private String titulo;
	
	private String autor;
	
	@ManyToOne
	private Editora editora;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataPublicacao;
	
	private String idioma;
	
	@ManyToMany
	private List<Categoria> categorias;
	
	private Integer paginas;	

	private Integer isbn;
	
	private Double peso;
	
	@NumberFormat(pattern="#,##0.00")
	private BigDecimal precoTabelado;
	
	@NumberFormat(pattern="#,##0.00")
	private BigDecimal precoAtual;
	
	private Boolean ativo=true;
	
	@Column(columnDefinition="text")
	private String descricao;
	
	private Integer quantidaEmEstoque;
		
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	private DetalhesImagemLivro detalhesImagem;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String title) {
		this.titulo = title;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String author) {
		this.autor = author;
	}

	
	public Editora getEditora() {
		return editora;
	}
	
	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}


    public List<Categoria> getCategorias() {
		return categorias;
	}
    
    public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public BigDecimal getPrecoTabelado() {
		return precoTabelado;
	}

	public void setPrecoTabelado(BigDecimal precoTabelado) {
		this.precoTabelado = precoTabelado;
	}

	public BigDecimal getPrecoAtual() {
		return precoAtual;
	}

	public void setPrecoAtual(BigDecimal nossoPreco) {
		this.precoAtual = nossoPreco;
	}

	public Boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidaEmEstoque() {
		return quantidaEmEstoque;
	}

	public void setQuantidaEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidaEmEstoque = quantidadeEmEstoque;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean ehNovo() {
		return ( this.id == null );
	}


    public DetalhesImagemLivro getDetalhesImagem() {
		return detalhesImagem;
	}
    
    public void setDetalhesImagem(DetalhesImagemLivro detalhesImage) {
		this.detalhesImagem = detalhesImage;
	}

	public boolean temFotoCapa() {
		return ( this.detalhesImagem != null ) && (this.detalhesImagem.getNomeArquivo() != null);
	}
	
	
	
}
