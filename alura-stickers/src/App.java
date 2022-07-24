import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
	public static void main(String[] args) throws Exception {

		String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
//		String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";

		var http = new ClienteHttp();
		String json = http.buscaDados(url);

		// Exibir e manipular os dados
		ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
//		ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
		List<Conteudo> conteudos = extrator.extraiConteudos(json);

		var geradora = new GeradoraDeFigurinhas();

		for (int i = 0; i < 3; i++) {

			Conteudo conteudo = conteudos.get(i);

			InputStream InputStream = new URL(conteudo.getUrlImagem()).openStream();
			String nomeArquivo = "imagemSaida/" + conteudo.getTitulo() + ".png";

			geradora.cria(InputStream, nomeArquivo);

			System.out.println("Título: " + conteudo.getTitulo());
			System.out.println();
		}
	}
}
