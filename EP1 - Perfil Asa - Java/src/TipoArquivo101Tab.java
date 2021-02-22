import java.io.BufferedReader;
import java.io.IOException;

public class TipoArquivo101Tab implements TipoArquivoPerfil {

	@Override
	public void executar(PerfilAsa perfil, BufferedReader buff) throws IOException {
		String line = null;
		//Pula a primeira linha
		line = buff.readLine();
		//39 extradorso
		for (int i = 0; i < 40; i++) {
			try {
				line = buff.readLine();
				String[] tponto = line.split("  ");
				perfil.addParExtraDorso(Double.parseDouble(tponto[1]), Double.parseDouble(tponto[2]));				
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
		//39 intradorso
		for (int i = 0; i < 40; i++) {
			try {
				line = buff.readLine();
				String[] tponto = line.split("    ");
				perfil.addParIntraDorso(Double.parseDouble(tponto[1]), Double.parseDouble(tponto[2]));				
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
	}

}
