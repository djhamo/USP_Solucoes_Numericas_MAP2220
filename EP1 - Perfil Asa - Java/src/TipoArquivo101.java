import java.io.BufferedReader;
import java.io.IOException;

public class TipoArquivo101 implements TipoArquivoPerfil {

	@Override
	public void executar(PerfilAsa perfil, BufferedReader buff) throws IOException {
		String line = null;
		//Pula a primeira linha
		line = buff.readLine();
		//49 extradorso
		for (int i = 0; i < 49; i++) {
			try {
				line = buff.readLine();
				String[] tponto = line.split("    ");
				perfil.addParExtraDorso(Double.parseDouble(tponto[0]), Double.parseDouble(tponto[1]));				
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
		//48 intradorso
		for (int i = 0; i < 49; i++) {
			try {
				line = buff.readLine();
				String[] tponto = line.split("    ");
				perfil.addParIntraDorso(Double.parseDouble(tponto[0]), Double.parseDouble(tponto[1]));				
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}

	}

}
