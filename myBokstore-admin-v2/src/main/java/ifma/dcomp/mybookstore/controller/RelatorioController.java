package ifma.dcomp.mybookstore.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ifma.dcomp.mybookstore.component.ConexaoComponente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {
	
	  @Autowired
	  private ConexaoComponente conexaoComponente;
	
	  @GetMapping("/livros/pdf")
	  //@ResponseBody
	  public void geraRelatorio(HttpServletResponse response) throws JRException, IOException, SQLException {

		InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/livros.jasper");
	  
	    Map<String,Object> params = new HashMap<>();
	    	     
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,  obterConexaoJDBC() );

	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=relatorio-livro.pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	  }

	private Connection obterConexaoJDBC() throws SQLException {
		//return DriverManager.getConnection("jdbc:mysql://localhost/bookstore?useSSL=false", "root", "root" );
		
		return conexaoComponente.obterConexaoJDBC();
	}
	  
}
