package ifma.dcomp.mybookstore.controller.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import ifma.dcomp.mybookstore.model.DetalhesImagemLivro;

public class ArquivoUpload {
	
	private MultipartFile arquivo;
	private String baseFolder;
	private Long livroId;
	private String realPath;
	
	public ArquivoUpload(MultipartFile arquivo) {
		this.arquivo = arquivo;
	}
		
    // TODO Salva imagem no servidor
	public void salvaImagem(String baseFolder, Long livroId,
			                  HttpServletRequest request) {
		
		this.baseFolder = baseFolder;
		this.livroId = livroId;
		
		try {
			realPath = request.getServletContext().getRealPath("/" + baseFolder );
			
			criaPastaNoServidorCasoNaoExista(realPath);
			
			//String  pathArquivoServidor = realPath + "/" + arquivo.getOriginalFilename() ;
			String  pathArquivoServidor = realPath + "/" + livroId + ".png" ;
						
			// transfere o arquivo para o servidor
			arquivo.transferTo( new File(pathArquivoServidor) );
			
		} catch (IllegalStateException | IOException e ) {
			throw new RuntimeException(e );
		}
	}
	
	public DetalhesImagemLivro criaDetalhesImagemLivro() {
		
		DetalhesImagemLivro imagemLivro = new DetalhesImagemLivro();
		
		//TODO alterar o nome do método setImagemPath para setPath
		imagemLivro.setImagemPath(baseFolder );
		imagemLivro.setRealPath(realPath );
		//imagemLivro.setNomeArquivo(arquivo.getOriginalFilename() );
		imagemLivro.setNomeArquivo(livroId + ".png" );
		imagemLivro.setTipoArquivo(arquivo.getContentType() );
		imagemLivro.setTamanhoArquivo(arquivo.getSize() );
		
		return imagemLivro;
	}
	
	private void criaPastaNoServidorCasoNaoExista(String realPath) {
		File diretorio = new File(realPath);
		
		if ( !diretorio.exists() ) {
			diretorio.mkdirs();
		}
	}

}
