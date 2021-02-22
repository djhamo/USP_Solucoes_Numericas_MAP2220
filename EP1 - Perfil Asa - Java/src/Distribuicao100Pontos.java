import java.util.ArrayList;
import java.util.List;
public class Distribuicao100Pontos {
	private static int tot_pontos = 100;
	public static List<Double> gerar100Pontos() {
		List<Double> result = new ArrayList<Double>();
	
		double intervalo = Math.PI / tot_pontos;
		double passo = intervalo;
		double fx;
		for (int i = 0; i < tot_pontos; i++) {
			fx = (1 - Math.cos(passo))/2;
			result.add(fx);
			passo += intervalo;
		}
		return result;
	}
}
