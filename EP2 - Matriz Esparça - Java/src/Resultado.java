
public class Resultado implements IResultado {

	private double[] _x;
	private double[] _r;
	private int _interacao;
	
	
	public Resultado(double[] _x, double[] _r, int _interacao) {
		this._x = _x;
		this._r = _r;
		this._interacao = _interacao;
	}

	@Override
	public double[] getX() {		
		return _x;
	}

	@Override
	public void setX(double[] x) {
		_x = x;

	}

	@Override
	public double[] getR() {
		return _r;
	}

	@Override
	public void setR(double[] x) {
		_r = x;

	}

	@Override
	public int getIteracao() {
		return _interacao;
	}

	@Override
	public void setIteracao(int x) {
		_interacao = x;

	}

}
