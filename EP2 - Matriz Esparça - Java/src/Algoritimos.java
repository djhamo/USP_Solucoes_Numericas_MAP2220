
public class Algoritimos {
	public static double TOL = 0.000001;
	public static int _Interacoes = 200000;

	
	public static double getTOL() {
		return TOL;
	}

	public static void setTOL(double tOL) {
		TOL = tOL;
	}

	public static IResultado SOR(SparseMatrix A, double w) {
		int _n = A.getNumberOfRows();
		double[] _x = new double[_n];
		double[] _xO = new double[_n];
		Zera(_xO);
		double[] _b = A.getB();
				
		int k = 1;
		
		while (k <= _Interacoes) {
			for (int i = 0; i < _n; i++) {
				double _Eax = 0.0; 
				double _EaXO = 0.0;
				for (int j = 0; j <= i - 1; j++) {
					_Eax += A.get(i, j) * _x[j];
					
				}
				for (int j = i + 1; j < _n; j++) {
					_EaXO += A.get(i, j) * _xO[j];
					
				}
				_x[i] = ((1.0 - w) * _xO[i]) 
						+ ((w / A.get(i, i))*(_b[i] - _Eax - _EaXO));
			}
			if (Norma2(Subtracao(_x, _xO)) < TOL) {
				Resultado result = new Resultado(_x, null, k);
				return result;
			}
			k++;
			Iguala(_xO, _x);
		}
		
		//Erro Estorou as iterações.
		return new ResultadoNull();
	}

	public static IResultado GradienteConjugado(SparseMatrix A) {
		int _n = A.getNumberOfRows();

		double[] _x = new double[_n];
		Zera(_x);
		double[] _b = A.getB();
		
		double[] _r = new double[_n];
		//Iguala(_r, Subtracao(_b, Multiplicacao(A, _x)));
		Iguala(_r, _b);
		double[] _rAnt = new double[_n];
		
		double[] _v = new double[_n];
		Iguala(_v, _r);
		
		int k = 1;
		
		while (k <= _Interacoes) {
			if (Norma2(_v) < TOL) {
				Resultado result = new Resultado(_x, _r, k);
				return result;
			}
			
			double[] _u = Multiplicacao(A, _v);
			double _t = ProdutoInterno(_r, _r) / ProdutoInterno(_v, _u);
			_x = Adicao(_x, Multiplicacao(_t, _v));
			
			//Virada do Passo
			Iguala(_rAnt, _r);
			
			_r = Subtracao(_rAnt, Multiplicacao(_t, _u));
			double _s = ProdutoInterno(_r, _r) / ProdutoInterno(_rAnt, _rAnt);
			_v = Adicao(_r, Multiplicacao(_s, _v));
			
			k++;
		}
		
		//Erro Estorou as iterações.
		return new ResultadoNull();
	}

	public static IResultado GradienteConjugadoPreCondicionado(SparseMatrix A, SparseMatrix C1) {
		int _n = A.getNumberOfRows();

		double[] _x = new double[_n];
		Zera(_x);
		double[] _b = A.getB();
		
		double[] _r = new double[_n];
		//Iguala(_r, Subtracao(_b, Multiplicacao(A, _x)));
		Iguala(_r, _b);
		
		double[] _w = Multiplicacao(C1, _r);
		double[] _v = Multiplicacao(C1, _w);
		
		double alfa = ProdutoInterno(_w, _w);
		
		int k = 1;
		
		while (k <= _Interacoes) {
			if (Norma2(_v) < TOL) {
				Resultado result = new Resultado(_x, _r, k);
				return result;
			}
			
			double[] _u = Multiplicacao(A, _v);
			double _t = alfa / ProdutoInterno(_v, _u);
			_x = Adicao(_x, Multiplicacao(_t, _v));
			
			_r = Subtracao(_r, Multiplicacao(_t, _u));
			_w = Multiplicacao(C1, _r);
			double beta = ProdutoInterno(_w, _w);

			if (Math.abs(beta) < TOL && Norma2(_r) < TOL) {
				Resultado result = new Resultado(_x, _r, k);
				return result;
			}

			double _s = beta / alfa;
			_v = Adicao(Multiplicacao(C1, _w), Multiplicacao(_s, _v));
			
			alfa = beta;
			k++;
		}
		
		//Erro Estorou as iterações.
		return new ResultadoNull();
	}

	
	public static void Print(double[] _x) {
		if (_x == null) return;
		for (int i = 0; i < _x.length; i++) {
			System.out.print(_x[i] + " ");			
		}
		System.out.println();
	}

	public static double[] Adicao(double[] _x, double[] _y) {
		if(_x == null)
			return null;
		if(_y == null)
			return null;
		int n = _x.length;
		double[] _result = new double[n];
		
		for (int i = 0; i < n; i++) {
			_result[i] = _x[i] + _y[i];
			
		}
		return _result;
	}

	public static double[] Subtracao(double[] _x, double[] _y) {
		if(_x == null)
			return null;
		if(_y == null)
			return null;
		int n = _x.length;
		double[] _result = new double[n];
		
		for (int i = 0; i < n; i++) {
			_result[i] = _x[i] - _y[i];
			
		}
		return _result;
	}

	public static double[] Multiplicacao(SparseMatrix A, double[] _x) {
		if(_x == null)
			return null;
		int n = A.getNumberOfColumns();
		double[] _result = new double[n];
		
		for (int i = 0; i < n; i++) {
			_result[i] = 0.0;
			for (int j = 0; j < _x.length; j++) {
				_result[i] += A.get(i, j) * _x[j];
				
			}
			
		}
		return _result;
	}
	
	public static double[] Multiplicacao(double _t, double[] _x) {
		if(_x == null)
			return null;
		int n = _x.length;
		double[] _result = new double[n];
		
		for (int i = 0; i < n; i++) {
			_result[i] = _t * _x[i];
				
		}
		return _result;
	}
	
	public static double Norma2(double[] _x) {
		if(_x == null)
			return 0.0;
		int n = _x.length;
		double _result = 0.0;
		
		for (int i = 0; i < n; i++) {
			_result += Math.pow(_x[i], 2);
			
		}
		_result = Math.sqrt(_result);
		return _result;
	}

	public static double NormaInfinita(double[] _x) {
		if(_x == null)
			return 0.0;
		int n = _x.length;
		double _result = 0.0;
		
		for (int i = 0; i < n; i++) {
			if (_result < Math.abs(_x[i]))
				_result = Math.abs(_x[i]);
			
		}
		return _result;
	}

	public static double ProdutoInterno(double[] _x, double[] _y) {
		if(_x == null)
			return 0.0;
		if(_y == null)
			return 0.0;
		int n = _x.length;
		double _result = 0.0;
		
		for (int i = 0; i < n; i++) {
			_result += _x[i] * _y[i];
			
		}

		return _result;
	}
	
	
	public static void Iguala(double[] _x, double[] _y) {
		if(_x == null)
			return ;
		if(_y == null)
			return ;
		for (int i = 0; i < _x.length; i++) {
			_x[i] = _y[i];
		}

	}

	public static void Zera(double[] _x) {
		if(_x == null)
			return ;
		for (int i = 0; i < _x.length; i++) {
			_x[i] = 0.0;
		}

	}
	
	public static void Una(double[] _x) {
		if(_x == null)
			return ;
		for (int i = 0; i < _x.length; i++) {
			_x[i] = 1.0;
		}

	}
	
	public static void setId(SparseMatrix A) {
		for (int i = 0; i < A.getNumberOfRows(); i++) {
			for (int j = 0; j < A.getNumberOfColumns(); j++) {
				if (i == j) A.set(i, j, 1.0);
				else A.set(i, j, 0.0);
			}
		}		
	}

	public static SparseMatrix C1RaizInversaDiagonal(SparseMatrix A) {
		SparseMatrix _result = new SparseMatrix(A.getNumberOfRows(), A.getNumberOfColumns());
		for (int i = 0; i < A.getNumberOfRows(); i++) {
			for (int j = 0; j < A.getNumberOfColumns(); j++) {
				if (i == j) _result.set(i, j, Math.pow(A.get(i, j), -0.5));
				else _result.set(i, j, 0.0);
			}
		}		
		return _result;
	}
	
}
