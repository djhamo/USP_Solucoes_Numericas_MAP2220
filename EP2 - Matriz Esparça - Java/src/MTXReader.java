import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MTXReader {
    
	//Quero usar uma classe para matriz para agrupar funções
	//no Futuro quero aceitar outros tipos numéricos como frações
    public static SparseMatrix read(String filename) throws IOException {
        String typecode;
        SparseMatrix matrix;
    	
    	File file = new File(filename);
    	BufferedReader br = new BufferedReader(new FileReader(file));
        
        // read type code initial line
        String line = br.readLine();
        typecode = line;
        
        // read comment lines if any
        boolean comment = true;
        while (comment) {
            line = br.readLine();
            comment = line.startsWith("%");
        }
        
        // line now contains the size information which needs to be parsed
        String[] str = line.split("( )+");
        int nRows = (Integer.valueOf(str[0].trim())).intValue();
        int nColumns = (Integer.valueOf(str[1].trim())).intValue();
        int nNonZeros = (Integer.valueOf(str[2].trim())).intValue();
        
        matrix = new SparseMatrix(nRows, nColumns);
        while (true) {
            line = br.readLine();
            if (line == null)  break;
            str = line.split("( )+");
            int i = (Integer.valueOf(str[0].trim())).intValue();
            int j = (Integer.valueOf(str[1].trim())).intValue();
            double x = (Double.valueOf(str[2].trim())).doubleValue();
            matrix.set(i-1, j-1, x);
        }
        
        if (matrix.getNumberOfNonZeros() != nNonZeros) {
        	System.out.println("Erro!");
        }
        br.close();
        
        return matrix;
    }
        
}
