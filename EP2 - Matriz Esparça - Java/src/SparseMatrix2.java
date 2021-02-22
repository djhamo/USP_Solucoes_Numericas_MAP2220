import java.lang.reflect.Array;
import java.util.Arrays;

public class SparseMatrix2<T> {
	private int _rows;
	private int _coluns;
	private int _nonzeros;
	private T[][] _matriz;
	
	private final Class<? extends T> cls;

	public SparseMatrix2(Class<? extends T> cls, int _rows, int _coluns) {
        this.cls = cls;
		this._rows = _rows;
		this._coluns = _coluns;
		@SuppressWarnings("unchecked")
		T[][] array = (T[][])Array.newInstance(cls, _rows, _coluns);
        _matriz = array;
        _nonzeros = 0;
	}

	public void set(int _rows, int _coluns, T x) {
		_matriz[_rows][_coluns] = x;
		_nonzeros++;
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
			System.out.print("[ ");
			for (int j = 0; j < _coluns; j++) {
				System.out.print(_matriz[i][j] + " ][ ");
			}			
			System.out.println(" ]");
		}
		
	}
	
	
}
