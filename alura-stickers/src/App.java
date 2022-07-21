import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws Exception {

		// Fazer uma conexão HTTP e buscar os top 250 filmes
		String apiKey = System.getenv("API_KEY");
		String url = "https://api.mocki.io/v2/" + apiKey;
		URI endereco = URI.create(url);

		HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();

        //System.out.println(body);

        // Extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream InputStream = new URL(urlImagem).openStream();

            String nomeArquivo = titulo + ".png";

            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(InputStream, nomeArquivo);

            System.out.print("\u001b[48;2;42;122;228m");
            System.out.print("\u001b[38;2;255;255;255m");
            System.out.println("Título: " + titulo);
            System.out.print("\u001b[m");

            String imdbRating = filme.get("imDbRating");
            Double imdbRatingDouble = Double.parseDouble(imdbRating);
            long roundRating = Math.round(imdbRatingDouble);
            //System.out.println(roundRating);
            System.out.println("Pôster: " + filme.get("image"));
            System.out.println("Classificação: " + filme.get("imDbRating"));

            for (int i = 0; i < roundRating; i++) {
                System.out.print("\u2b50");
            }

			System.out.println();
		}
	}
}
