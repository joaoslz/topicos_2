package ifma.dcomp.mybookstore.model;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DetalhesImagemLivro implements Serializable {

  	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imagemPath;

    @Column(nullable = false)
    private String nomeArquivo;

    private Long tamanhoArquivo;

    private String tipoArquivo;

	private String realPath;

    public DetalhesImagemLivro() {
    }

    public DetalhesImagemLivro(String imagemPath, String nomeArquivo, Long tamanhoArquivo, 
    		                   String tipoArquivo) {
        this.imagemPath = imagemPath;
        this.nomeArquivo = nomeArquivo;
        this.tamanhoArquivo = tamanhoArquivo;
        this.tipoArquivo = tipoArquivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getImagemPath() {
		return imagemPath;
	}

	public void setImagemPath(String imagemPath) {
		this.imagemPath = imagemPath;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Long getTamanhoArquivo() {
		return tamanhoArquivo;
	}

	public void setTamanhoArquivo(Long tamanhoArquivo) {
		this.tamanhoArquivo = tamanhoArquivo;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
	
	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	
	@Override
    public String toString() {
        return "ImagemLivro{" +
                "id=" + id +
                ", imagemPath='" + imagemPath + '\'' +
                ", nomeArquivo='" + nomeArquivo + '\'' +
                ", tamanhoArquivo=" + tamanhoArquivo +
                ", tipoArquivo='" + tipoArquivo + '\'' +
                '}';
    }
	
	//TODO refatorar o nome deste método
	public String getFotoPath() {
		//return this.imagemPath + "/" + this.nomeArquivo;
		return this.imagemPath + File.separator + this.nomeArquivo;
	}
	
	public String getRealPathComNomeDoArquivo() {
		return this.realPath + File.separator + this.nomeArquivo;
		
	}

}
