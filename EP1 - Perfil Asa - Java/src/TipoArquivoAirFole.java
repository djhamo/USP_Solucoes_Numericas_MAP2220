import java.io.BufferedReader;
import java.io.IOException;

public class TipoArquivoAirFole implements TipoArquivoPerfil {

	@Override
	public void executar(PerfilAsa perfil, BufferedReader buff) throws IOException {
		String line = null;
		//Pula a 3 linha
		for (int i = 0; i < 3; i++) {
			line = buff.readLine();
		}	
		//32 extradorso
		for (int i = 0; i <= 32; i++) {
			try {
				line = buff.readLine();
				String[] tponto = line.split("  ");
				perfil.addParExtraDorso(Double.parseDouble(tponto[1]), Double.parseDouble(tponto[2]));				
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
		//Pula a linha
		line = buff.readLine();
		//29 intradorso
		for (int i = 0; i < 25; i++) {
			try {
				line = buff.readLine();
				String[] tponto = line.split(" ");
				perfil.addParIntraDorso(Double.parseDouble(tponto[2]), Double.parseDouble(tponto[3]));				
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
		for (int i = 0; i < 4; i++) {
			try {
				line = buff.readLine();
				String[] tponto = line.split("  ");
				perfil.addParIntraDorso(Double.parseDouble(tponto[1]), Double.parseDouble(tponto[2]));				
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
	}

}
