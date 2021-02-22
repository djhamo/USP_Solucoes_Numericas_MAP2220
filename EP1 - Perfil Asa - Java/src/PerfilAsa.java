import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PerfilAsa {

	private Map<Double, Double> _extradorso = new TreeMap<Double, Double>();
	private Map<Double, Double> _intradorso = new TreeMap<Double, Double>();
	
	public void addParExtraDorso(double x, double fx) {
		_extradorso.put(x, fx);
	}

	public void addParIntraDorso(double x, double fx) {
		_intradorso.put(x, fx);
	}
	
	public double get_extradorso(double x) {
		return _extradorso.get(x);
	}

	public List<Double> get_extradorsoX() {
		return new ArrayList<Double>(_extradorso.keySet());
	}

	public List<Double> get_extradorsoY() {
		return new ArrayList<Double>(_extradorso.values());
	}

	public List<Double> get_intradorsoX() {
		return new ArrayList<Double>(_intradorso.keySet());
	}

	public List<Double> get_intradorsoY() {
		return new ArrayList<Double>(_intradorso.values());
	}
	
	public double get_intradorso(double x) {
		return _intradorso.get(x);
	}

	public int size_extradorso() {
		return _extradorso.size();
	}
	
	public int size_intradorso() {
		return _intradorso.size();
	}
	
	public void showExtradorso() {
        printMap(_extradorso);
		
	}

	public void showIntradorso() {
        printMap(_intradorso);
	}

    private static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("x: " + entry.getKey()
				+ "\t f(x): " + entry.getValue());
        }
    }
}
