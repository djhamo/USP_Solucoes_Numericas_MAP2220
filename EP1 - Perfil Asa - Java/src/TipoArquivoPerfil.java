import java.io.BufferedReader;
import java.io.IOException;

public interface TipoArquivoPerfil {
	void executar(PerfilAsa perfil, BufferedReader buff) throws IOException;

}
