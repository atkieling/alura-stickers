import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

	public void cria(InputStream InputStream, String nomeArquivo) throws Exception {

		// Leitura da imagem
		BufferedImage imagemOriginal = ImageIO.read(InputStream);

		// Cria nova imagem em memória com transparência e com tamanho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

		// Copiar a imagem original para nova imagem (em memória)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);

		// Configurar a fonte
		var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
		graphics.setColor(Color.YELLOW);
		graphics.setFont(fonte);

		// Escrever uma frase na nova imagem
		int framingRectangle = (int) graphics.getFontMetrics().getStringBounds("TOP!", graphics).getWidth();
		int x = novaImagem.getWidth() / 2 - framingRectangle / 2;
		int y = novaImagem.getHeight() - 78;
		graphics.drawString("TOP!", x, y);

		// Escrever a nova imagem em um arquivo
		ImageIO.write(novaImagem, "png", new File(nomeArquivo));
	}
}