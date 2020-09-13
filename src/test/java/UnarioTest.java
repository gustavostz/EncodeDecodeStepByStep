import codifications.EliasGamma;
import codifications.Unario;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class UnarioTest {

    @Test
    public void deveFazerOEncodeDoArquivo() throws IOException {
    	Unario unario = new Unario();
        unario.encode(new File("src/main/resources/filesToEncode/arquivoFacil.txt"));
    }

    @Test
    public void deveFazerDecodeDoArquivo() throws IOException {
        Unario unario = new Unario();
        unario.decode(new File("src/main/resources/filesEncoded/arquivoFacil.txt.cod"));
    }

}
//0000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000011111111111111111111111111111111111111111111111110000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111111111111110000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111111111100
//00000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111111111111110000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111111111111110000000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111100000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111111111110000000000000000000000000000000000000000000000000000011111111111111111111111111111111111111111111111111
//0000000000000000000000000000000000000000000000001111111111111111111111111111111111111111111111110000000000000000000000000000000000000000111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111001110000000010111111001111000011110111000001100