import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EP02MAP0222 {
   
	//Critério de comparação:
	// Erro
	// Iterações 
	// Tempo
	private List<String> arquivos = new ArrayList<String>();
	private List<Double> omegas = new ArrayList<Double>();
	private List<Double> erros = new ArrayList<Double>();
	
	public EP02MAP0222() {
		arquivos.add("./data/mesh3e1/mesh3e1.mtx");	
		arquivos.add("./data/bcsstk06/bcsstk06.mtx");	
		arquivos.add("./data/bcsstk21/bcsstk21.mtx");	
		arquivos.add("./data/ex5/ex5.mtx");
		arquivos.add("./data/nos2/nos2.mtx");
		arquivos.add("./data/ex33/ex33.mtx");
		arquivos.add("./data/Journals/Journals.mtx");
		arquivos.add("./data/Trefethen_20b/Trefethen_20b.mtx");	
		arquivos.add("./data/Trefethen_700/Trefethen_700.mtx");		
		
		omegas.add(0.1);
		omegas.add(0.25);
		omegas.add(0.5);
		omegas.add(0.75);
		omegas.add(1.0);
		omegas.add(1.25);
		omegas.add(1.5);
		omegas.add(1.75);
		omegas.add(1.9);
		omegas.add(1.95);
		omegas.add(1.99);
		omegas.add(2.0);

		erros.add(0.001);
		erros.add(0.0001);
		erros.add(0.000001);
	}
	
	public void Run() {
		long startTime;
		long stopTime;
        long elapsedTime;
        
        for (Double erro : erros) {
        	Algoritimos.setTOL(erro);
        	System.out.println("Tolerância: " + erro);
    		for (String arquivo : arquivos) {
            	System.out.println("Processando: " + arquivo);
    	        try {
    				SparseMatrix A = MTXReader.read(arquivo);
    				A.gerarMatrizCompleta();
    				if (!A.testaDiagonal()) throw new RuntimeException();
    				A.gerarB(1.0);
    	        	System.out.println("Matriz: " + A.getNumberOfRows() + "x" + A.getNumberOfColumns());
    	        	A.log();
    				//A.print();
    	        	
    	        	double[] _correto = new double[A.getNumberOfRows()];
    	        	Algoritimos.Una(_correto);
    	        	
    				double w_otimo = 1.0;
    				int melhor_iteracao = 20000;
    				for (double w : omegas) {
    					startTime = System.currentTimeMillis();
    			        IResultado rsSOR = Algoritimos.SOR(A, w);
    			        stopTime = System.currentTimeMillis();
    			        elapsedTime = stopTime - startTime;
    			        
    			        double _erro = Algoritimos.Norma2(Algoritimos.Subtracao(_correto, rsSOR.getX()) );
    			        System.out.println("SOR Time: " + formatMillis(elapsedTime) + " Iteracoes: " + rsSOR.getIteracao() + " w: " + w + " Erro: " + _erro);	
    			        Algoritimos.Print(rsSOR.getX());
    			        if (melhor_iteracao >= rsSOR.getIteracao()) {
    			        	w_otimo = w;
    			        	melhor_iteracao = rsSOR.getIteracao();
    			        }
    			        	
    				}
    				System.out.println("W ótimo: " + w_otimo + " Iterações: " + melhor_iteracao);
    				
    				//Pega o Pior
    				startTime = System.currentTimeMillis();
    		        IResultado rsGC = Algoritimos.GradienteConjugado(A);
    		        stopTime = System.currentTimeMillis();
    		        elapsedTime = stopTime - startTime;
    		        double _erro = Algoritimos.Norma2(Algoritimos.Subtracao(_correto, rsGC.getX()) );
    		        System.out.println("Gc Time: " + formatMillis(elapsedTime) + " Iteracoes: " + rsGC.getIteracao() + " Erro " + _erro);					
    		        Algoritimos.Print(rsGC.getX());
    				startTime = System.currentTimeMillis();
    		        //SparseMatrix C1 = new SparseMatrix(A.getNumberOfRows(), A.getNumberOfColumns());
    				SparseMatrix C1 = Algoritimos.C1RaizInversaDiagonal(A);
    		        //Algoritimos.setId(C1);
    		        IResultado rsGCPC = Algoritimos.GradienteConjugadoPreCondicionado(A, C1);
    		        stopTime = System.currentTimeMillis();
    		        elapsedTime = stopTime - startTime;
    		        _erro = Algoritimos.Norma2(Algoritimos.Subtracao(_correto, rsGCPC.getX()) );
    		        System.out.println("GcC1 Time: " + formatMillis(elapsedTime) + " Iteracoes: " + rsGCPC.getIteracao() + " Erro " + _erro);					
    		        Algoritimos.Print(rsGCPC.getX());
    		        

    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	
    		}
        	
		}
	}
	
	static public String formatMillis(long val) {
	    StringBuilder                       buf=new StringBuilder(20);
	    String                              sgn="";

	    if(val<0) { sgn="-"; val=Math.abs(val); }

	    append(buf,sgn,0,( val/3600000             ));
	    append(buf,":",2,((val%3600000)/60000      ));
	    append(buf,":",2,((val         %60000)/1000));
	    append(buf,".",3,( val                %1000));
	    return buf.toString();
	}
	
	static private void append(StringBuilder tgt, String pfx, int dgt, long val) {
	    tgt.append(pfx);
	    if(dgt>1) {
	        int pad=(dgt-1);
	        for(long xa=val; xa>9 && pad>0; xa/=10) { pad--;           }
	        for(int  xa=0;   xa<pad;        xa++  ) { tgt.append('0'); }
	        }
	    tgt.append(val);
	}
}
