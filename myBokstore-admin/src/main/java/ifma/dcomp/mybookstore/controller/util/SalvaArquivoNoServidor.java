package ifma.dcomp.mybookstore.controller.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class SalvaArquivoNoServidor {
	
	public String salvaImagem(String baseFolder, 
			MultipartFile arquivo, 
			HttpServletRequest request) {
		
		try {
			// obtem o caminho completo (real) no servidor.
			String realPath = request.getServletContext().getRealPath("/" + baseFolder );
			
			String  path = realPath + "/" + arquivo.getOriginalFilename() ;
			
			
			// transfere o arquivo para o servidor
			arquivo.transferTo( new File(path) );
			
			// retorna o endereço relativo
			return baseFolder + "/" + arquivo.getOriginalFilename();
		
		} catch (IllegalStateException | IOException e ) {
			throw new RuntimeException(e );
		}
		
	}

}
