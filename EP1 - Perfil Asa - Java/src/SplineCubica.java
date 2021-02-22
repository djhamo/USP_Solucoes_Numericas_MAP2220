import java.util.List;

public class SplineCubica {

	private Double[] _x = {};
	private Double[] _a = {};
	private double[] _b;
	private double[] _c;
	private double[] _d;
	
	public SplineCubica(List<Double> x, List<Double> fx) {
		
		_x = x.toArray(_x);
		_a = fx.toArray(_a);
		int _n = _x.length;
		_b = new double[_n];
		_c = new double[_n];
		_d = new double[_n];
		
		double[] _h = new double[_n];
		double[] _alpha = new double[_n];
		
		for (int i = 0; i < (_n - 1); i++) {
			_h[i] = (_x[i + 1] - _x[i]);
		}
		
		for (int i = 1; i < (_n - 1); i++) {
			double _temps = ((_a[i + 1] - _a[i])*3)/_h[i];
			double _tempd = ((_a[i] - _a[i - 1])*3)/_h[i - 1];
			_alpha[i] = (_temps - _tempd);
		}
	
		double[] _l = new double[_n];
		double[] _u = new double[_n];
		double[] _z = new double[_n];
		
		_l[0] = 1.0;
		_u[0] = 0.0;
		_z[0] = 0.0;
		
		for (int i = 1; i < (_n - 1); i++) {
			_l[i] = (2*(_x[i + 1] - _x[i - 1])) - (_h[i - 1] * _u[i -1]);
			_u[i] = (_h[i] / _l[i]);
			_z[i] = ((_alpha[i] -(_h[i - 1]*_z[i -1]))/ _l[i]);
		}
		
		_l[_n - 1] = 1.0;
		_z[_n - 1] = 0.0;
		_c[_n - 1] = 0.0;
		
		for (int j = _n - 2; j >= 0; j--) {
			double _tempc = _z[j] - (_u[j] * _c[j + 1]);
			_c[j] = _tempc;
			double _tempb = ((_a[j + 1] - _a[j])/_h[j]) - ((_h[j]/3)*(_c[j + 1] + (2*_c[j])));
			_b[j] = _tempb;
			double _tempd = (_c[j + 1] - _c[j])/(3*_h[j]);
			_d[j] = _tempd;
		}
		
		/*
		for (int i = 0; i < x.size(); i++) {
			System.out.println(_x[i] + "\t" + _a[i] + "\t" + _b[i] + "\t" + _c[i] + "\t" + _d[i]);
		}
		*/
	}

	public double get_x(int i) {
		return _x[i];
	}

	public double get_a(int i) {
		return _a[i];
	}

	public double get_b(int i) {
		return _b[i];
	}

	public double get_c(int i) {
		return _c[i];
	}

	public double get_d(int i) {
		return _d[i];
	}
	
	public double calcula(double x) {
		int j = findPolinomio(x);
		double result = _a[j] + _b[j]*(x - _x[j]) + _c[j]*Math.pow((x - _x[j]), 2) + _d[j]*Math.pow((x - _x[j]), 3);
		return result;
	}
	
	private int findPolinomio(double x) {
		int result = 0;
		for (result = 0; result < _x.length; result++) {
			if (x < _x[result]) {
				result--;
				break;
			}
		}
		if (result < 0 ) result = 0;
		if (result >= _x.length ) result = _x.length - 2;
		return result;
	}
}
