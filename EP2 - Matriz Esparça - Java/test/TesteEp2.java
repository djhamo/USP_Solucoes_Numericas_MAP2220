import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TesteEp2 {

	@Test
	public void testCarregarArquivoOK() throws IOException {
        SparseMatrix A = MTXReader.read("./data/bcsstk01.mtx");
        
		assertEquals(A.getNumberOfRows(), 48);
		assertEquals(A.getNumberOfColumns(), 48);
		assertEquals(A.getNumberOfNonZeros(), 224);

	}

	@Test
	public void testMatrizOK() {
		SparseMatrix matrix = new SparseMatrix(2, 2);
        matrix.set(0, 0, 0.1);
        matrix.set(0, 1, 3.7);
        matrix.set(1, 0, 5.2);        
        matrix.set(1, 1, 9.1);
        
        assertEquals(matrix.get(0, 0), 0.1, 0.0001);
        assertEquals(matrix.get(0, 1), 3.7, 0.0001);
        assertEquals(matrix.get(1, 0), 5.2, 0.0001);
        assertEquals(matrix.get(1, 1), 9.1, 0.0001);
	}
	
	@Test
	public void testBMatrizOK() {
		SparseMatrix matrix = new SparseMatrix(3, 3);
        matrix.set(0, 0, 0.1);
        matrix.set(0, 1, 3.7);
        matrix.set(0, 2, 5.2);
        
        matrix.set(1, 0, 9.1);
        matrix.set(1, 1, 7.2);
        matrix.set(1, 2, 6.6);

        matrix.set(2, 0, 0.1);
        matrix.set(2, 1, 0.4);
        matrix.set(2, 2, 0.9);

		matrix.gerarB(1.0);
        double[] _b = matrix.getB();
        
        assertEquals(_b[0], 9.0, 0.0001);
        assertEquals(_b[1], 22.9, 0.0001);
        assertEquals(_b[2], 1.4, 0.0001);
	}

	@Test
	public void testAlgoritmoSOROK() {
		SparseMatrix matrix = new SparseMatrix(3, 3);
        matrix.set(0, 0, 4.0);
        matrix.set(0, 1, 3.0);
        matrix.set(0, 2, 0.0);
        
        matrix.set(1, 0, 3.0);
        matrix.set(1, 1, 4.0);
        matrix.set(1, 2, -1.0);

        matrix.set(2, 0, 0.0);
        matrix.set(2, 1, -1.0);
        matrix.set(2, 2, 4.0);

        double[] _b = { 24, 30, -24};
		matrix.setB(_b);

		IResultado result = Algoritimos.SOR(matrix, 1.25);
		double[] _x = result.getX();

		assertTrue(result instanceof Resultado);
        assertEquals(_x[0], 3.0000498, 0.001);
        assertEquals(_x[1], 4.0002586, 0.001);
        assertEquals(_x[2], -5.0003486, 0.001);
        
        System.out.println("SOR Interacoes= " + result.getIteracao());
	}

	@Test
	public void testAlgoritmoSOROK2() {
		SparseMatrix matrix = new SparseMatrix(5, 5);
        matrix.set(0, 0, 0.2);
        matrix.set(0, 1, 0.1);
        matrix.set(0, 2, 1.0);
        matrix.set(0, 3, 1.0);
        matrix.set(0, 4, 0.0);
        
        matrix.set(1, 0, 0.1);
        matrix.set(1, 1, 4.0);
        matrix.set(1, 2, -1.0);
        matrix.set(1, 3, 1.0);
        matrix.set(1, 4, -1.0);

        matrix.set(2, 0, 1.0);
        matrix.set(2, 1, -1.0);
        matrix.set(2, 2, 60.0);
        matrix.set(2, 3, 0.0);
        matrix.set(2, 4, -2.0);

        matrix.set(3, 0, 1.0);
        matrix.set(3, 1, 1.0);
        matrix.set(3, 2, 0.0);
        matrix.set(3, 3, 8.0);
        matrix.set(3, 4, 4.0);

        matrix.set(4, 0, 0.0);
        matrix.set(4, 1, -1.0);
        matrix.set(4, 2, -2.0);
        matrix.set(4, 3, 4.0);
        matrix.set(4, 4, 700.0);

        double[] _b = { 1, 2, 3, 4, 5};
		matrix.setB(_b);

		IResultado result = Algoritimos.SOR(matrix, 1.25);
		double[] _x = result.getX();

		assertTrue(result instanceof Resultado);
        assertEquals(_x[0], 7.85968827, 0.001);
        assertEquals(_x[1], 0.42288329, 0.001);
        assertEquals(_x[2], -0.07359778, 0.001);
        assertEquals(_x[3], -0.54063200, 0.001);
        assertEquals(_x[4], 0.01064344, 0.001);
        
        System.out.println("SOR 2 Interacoes= " + result.getIteracao());
	}

	@Test
	public void testAlgoritmoGradienteOK() {
		SparseMatrix matrix = new SparseMatrix(3, 3);
        matrix.set(0, 0, 4.0);
        matrix.set(0, 1, 3.0);
        matrix.set(0, 2, 0.0);
        
        matrix.set(1, 0, 3.0);
        matrix.set(1, 1, 4.0);
        matrix.set(1, 2, -1.0);

        matrix.set(2, 0, 0.0);
        matrix.set(2, 1, -1.0);
        matrix.set(2, 2, 4.0);

        double[] _b = { 24, 30, -24};
		matrix.setB(_b);

		IResultado result = Algoritimos.GradienteConjugado(matrix);
		double[] _x = result.getX();

		assertTrue(result instanceof Resultado);
        assertEquals(_x[0], 3.0000498, 0.001);
        assertEquals(_x[1], 4.0002586, 0.001);
        assertEquals(_x[2], -5.0003486, 0.001);
        
        System.out.println("Gradiente Interacoes= " + result.getIteracao());
	}

	@Test
	public void testAlgoritmoGradienteOK2() {
		SparseMatrix matrix = new SparseMatrix(5, 5);
        matrix.set(0, 0, 0.2);
        matrix.set(0, 1, 0.1);
        matrix.set(0, 2, 1.0);
        matrix.set(0, 3, 1.0);
        matrix.set(0, 4, 0.0);
        
        matrix.set(1, 0, 0.1);
        matrix.set(1, 1, 4.0);
        matrix.set(1, 2, -1.0);
        matrix.set(1, 3, 1.0);
        matrix.set(1, 4, -1.0);

        matrix.set(2, 0, 1.0);
        matrix.set(2, 1, -1.0);
        matrix.set(2, 2, 60.0);
        matrix.set(2, 3, 0.0);
        matrix.set(2, 4, -2.0);

        matrix.set(3, 0, 1.0);
        matrix.set(3, 1, 1.0);
        matrix.set(3, 2, 0.0);
        matrix.set(3, 3, 8.0);
        matrix.set(3, 4, 4.0);

        matrix.set(4, 0, 0.0);
        matrix.set(4, 1, -1.0);
        matrix.set(4, 2, -2.0);
        matrix.set(4, 3, 4.0);
        matrix.set(4, 4, 700.0);

        double[] _b = { 1, 2, 3, 4, 5};
		matrix.setB(_b);

		IResultado result = Algoritimos.GradienteConjugado(matrix);
		double[] _x = result.getX();

		assertTrue(result instanceof Resultado);
        assertEquals(_x[0], 7.85968827, 0.001);
        assertEquals(_x[1], 0.42288329, 0.001);
        assertEquals(_x[2], -0.07359778, 0.001);
        assertEquals(_x[3], -0.54063200, 0.001);
        assertEquals(_x[4], 0.01064344, 0.001);

        System.out.println("Gradiente 2 Interacoes= " + result.getIteracao());
	}

	@Test
	public void testAlgoritmoGradientePreCondicionadoOK() {
		SparseMatrix matrix = new SparseMatrix(5, 5);
        matrix.set(0, 0, 0.2);
        matrix.set(0, 1, 0.1);
        matrix.set(0, 2, 1.0);
        matrix.set(0, 3, 1.0);
        matrix.set(0, 4, 0.0);
        
        matrix.set(1, 0, 0.1);
        matrix.set(1, 1, 4.0);
        matrix.set(1, 2, -1.0);
        matrix.set(1, 3, 1.0);
        matrix.set(1, 4, -1.0);

        matrix.set(2, 0, 1.0);
        matrix.set(2, 1, -1.0);
        matrix.set(2, 2, 60.0);
        matrix.set(2, 3, 0.0);
        matrix.set(2, 4, -2.0);

        matrix.set(3, 0, 1.0);
        matrix.set(3, 1, 1.0);
        matrix.set(3, 2, 0.0);
        matrix.set(3, 3, 8.0);
        matrix.set(3, 4, 4.0);

        matrix.set(4, 0, 0.0);
        matrix.set(4, 1, -1.0);
        matrix.set(4, 2, -2.0);
        matrix.set(4, 3, 4.0);
        matrix.set(4, 4, 700.0);

        double[] _b = { 1, 2, 3, 4, 5};
		matrix.setB(_b);

		SparseMatrix C1 = new SparseMatrix(5, 5);
		C1.set(0, 0, 0.5);
		C1.set(0, 1, 0.0);
		C1.set(0, 2, 0.0);
		C1.set(0, 3, 0.0);
		C1.set(0, 4, 0.0);
        
		C1.set(1, 0, 0.0);
		C1.set(1, 1, 0.5);
		C1.set(1, 2, 0.0);
		C1.set(1, 3, 0.0);
		C1.set(1, 4, 0.0);

		C1.set(2, 0, 0.0);
		C1.set(2, 1, 0.0);
		C1.set(2, 2, 0.5);
		C1.set(2, 3, 0.0);
		C1.set(2, 4, 0.0);

		C1.set(3, 0, 0.0);
		C1.set(3, 1, 0.0);
		C1.set(3, 2, 0.0);
		C1.set(3, 3, 0.5);
		C1.set(3, 4, 0.0);

		C1.set(4, 0, 0.0);
		C1.set(4, 1, 0.0);
		C1.set(4, 2, 0.0);
		C1.set(4, 3, 0.0);
		C1.set(4, 4, 0.5);

		IResultado result = Algoritimos.GradienteConjugadoPreCondicionado(matrix, C1);
		double[] _x = result.getX();

		assertTrue(result instanceof Resultado);
        assertEquals(_x[0], 7.85968827, 0.001);
        assertEquals(_x[1], 0.42288329, 0.001);
        assertEquals(_x[2], -0.07359778, 0.001);
        assertEquals(_x[3], -0.54063200, 0.001);
        assertEquals(_x[4], 0.01064344, 0.001);

        System.out.println("Gradiente Pré Interacoes= " + result.getIteracao());
	}

}
