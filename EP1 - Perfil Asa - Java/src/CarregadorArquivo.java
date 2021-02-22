import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CarregadorArquivo {

	public static PerfilAsa Perfil(String nomeArquivo, TipoArquivoPerfil tipo) throws IOException {
		File mFile = new File(nomeArquivo);
		FileReader fileReader;
		PerfilAsa perfil = new PerfilAsa();
		try {
			fileReader = new FileReader(mFile);
			BufferedReader reader = new BufferedReader(fileReader);
			
			tipo.executar(perfil, reader);
			
			reader.close();
		} catch (Exception e) {
			throw new IOException("Erro no carregamento do Perfil");
		}
		return perfil ;
	}

}
