import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SparseMatrix {
	private int _rows;
	private int _coluns;
	private int _nonzeros;
	private double[][] _matriz;
	private double[] _b;
	
	public SparseMatrix(int _rows, int _coluns) {
		this._rows = _rows;
		this._coluns = _coluns;
		_matriz = new double[_rows][_coluns];
		_b = new double[_rows];
        for (int i = 0; i < _rows; i++) {
        	_b[i] = 0.0;
        	for (int j = 0; j < _coluns; j++) {
				_matriz[i][j] = 0.0;
			}			
			
		}
        _nonzeros = 0;
	}

	public void set(int _rows, int _coluns, double x) {
		_matriz[_rows][_coluns] = x;
		_nonzeros++;
	}
	
	public double get(int _rows, int _coluns) {
		return _matriz[_rows][_coluns];
	}

	public int getNumberOfColumns() {
		return _coluns;
	}

	public int getNumberOfRows() {
		return _rows;
	}

	public long getNumberOfNonZeros() {
		return _nonzeros;
	}

	public void print() {
		for (int i = 0; i < _rows; i++) {
			System.out.print("[");
			for (int j = 0; j < _coluns; j++) {
				System.out.print(_matriz[i][j] + "]");
				if (j < _coluns - 1) {
					System.out.print("[");
				}
			}
			System.out.println();
		}
		
	}
	
	public void log() throws IOException {
					  
	    BufferedWriter writer = new BufferedWriter(new FileWriter("./resultados/log.txt"));
		for (int i = 0; i < _rows; i++) {
			String str = "[";
			for (int j = 0; j < _coluns; j++) {
				str += _matriz[i][j] + "]";
				if (j < _coluns - 1) {
					str += "[";
				}
			}
			str += "\n";
		    writer.write(str);
		}
	     
	    writer.close();
	}

	public void gerarB(double xis) {
        for (int i = 0; i < _rows; i++) {
        	for (int j = 0; j < _coluns; j++) {
        		_b[i] += _matriz[i][j] * xis;
			}			
			
		}				
	}
	
	public void gerarMatrizCompleta() {
        for (int i = 1; i < _rows; i++) {
        	for (int j = 0; j < i; j++) {
        		_matriz[j][i] = _matriz[i][j];
			}			
			
		}				
	}
	
	
	public void setB(double[] _b) {
		this._b = _b;
	}

	public double[] getB() {
		return _b;
	}
	
	public boolean testaDiagonal() {
        for (int i = 1; i < _rows; i++) {
        	if (_matriz[i][i] == 0.0) return false;
        }
        return true;
	}
	
}
