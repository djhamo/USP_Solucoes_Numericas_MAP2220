import java.util.ArrayList;
import java.util.List;

public class PerfilSpline {
	private double _h = 0.001;
	private PerfilAsa _perfil;
	private List<Double> _distribuicao;
	private List<Double> _tabSplineExtradorso = new ArrayList<Double>();
	private List<Double> _tabSplineIntradorso = new ArrayList<Double>();
	private List<Double> _tabDerivadaEstradorso = new ArrayList<Double>();
	private List<Double> _tabDerivadaIntradorso = new ArrayList<Double>();
	private List<Double> _tabEspessura = new ArrayList<Double>();
	private SplineCubica splineExtradorso;
	private SplineCubica splineIntradorso;
	
	public PerfilSpline(PerfilAsa perfil, List<Double> gerar100Pontos, double h) {
		_h = h;
		_perfil = perfil;
		_distribuicao = gerar100Pontos;
		
		splineExtradorso = new SplineCubica(_perfil.get_extradorsoX(), _perfil.get_extradorsoY());
		splineIntradorso = new SplineCubica(_perfil.get_intradorsoX(), _perfil.get_intradorsoY());
		for (Double ponto : _distribuicao) {
			_tabSplineExtradorso.add(splineExtradorso.calcula(ponto));
			_tabSplineIntradorso.add(splineIntradorso.calcula(ponto));
		}
		
    	for (int i = 0; i < _distribuicao.size(); i++) {
    		double _temp = _tabSplineExtradorso.get(i) - _tabSplineIntradorso.get(i);
    		_tabEspessura.add(_temp);
    	}
		
		for (Double ponto : _distribuicao) {
			_tabDerivadaEstradorso.add(
					Derivada3Pontos.calcula3PontosExtremos(
							splineExtradorso.calcula(ponto+_h), 
							splineExtradorso.calcula(ponto), 
							splineExtradorso.calcula(ponto-_h), _h));
			_tabDerivadaIntradorso.add(
					Derivada3Pontos.calcula3PontosExtremos(
							splineIntradorso.calcula(ponto+_h), 
							splineIntradorso.calcula(ponto), 
							splineIntradorso.calcula(ponto-_h), _h));
		}

		//System.out.println(_tabSplineEstradorso.size());
		//System.out.println(_tabSplineIntradorso.size());
	}
	
	public void showExtradorso() {
        printMap(_tabSplineExtradorso);
		
	}

	public void showIntradorso() {
        printMap(_tabSplineIntradorso);
	}

	
	public List<Double> get_extradorsoX() {
		return _distribuicao;
	}

	public List<Double> get_extradorsoY() {
		return _tabSplineExtradorso;
	}

	public List<Double> get_extradorsoLinha() {
		return _tabDerivadaEstradorso;
	}
	
	public List<Double> get_intradorsoX() {
		return _distribuicao;
	}

	public List<Double> get_intradorsoY() {
		return _tabSplineIntradorso;
	}
	
	public List<Double> get_intradorsoLinha() {
		return _tabDerivadaIntradorso;
	}
	
	public List<Double> get_Espessura() {
		return _tabEspessura;
	}
	
	public double get_AlturaExtradorsoSpline(double x) {
		return splineExtradorso.calcula(x);
	}
	
	public double get_AlturaIntradorsoSpline(double x) {
		return splineIntradorso.calcula(x);
	}
	
    private void printMap(List<Double> _tab) {
    	for (int i = 0; i <  _tab.size(); i++) {
    		System.out.println(_distribuicao.get(i) + " -> " + _tab.get(i));
		}

    }
}
