import java.util.ArrayList;
import java.util.List;

public class Derivada3Pontos {

	public static List<Double> calculaTab(List<Double> x, List<Double> fx, double _h) {
		Double[] _x = {};
		_x = x.toArray(_x);
		Double[] _fx = {};
		_fx = fx.toArray(_x);
		
		List<Double> result = new ArrayList<Double>();
		for (int i = 0; i < (_x.length - 2); i=i+2) {
			result.add(calcula3Pontos(_fx[i], _fx[i+1], _fx[i+2], _h));
		}
		return result;
	}
	
	public static double calcula3Pontos(double fx, double fxh1, double fxh2, double _h) {
		
		double result = (1/(2*_h))*((-3*fx) + (4*fxh1) - fxh2);
		return result;
	}
	
	public static double calcula3PontosExtremos(double fxMaisH, double fx, double fxMenosH, double _h) {
		
		double result = (1/(2*_h))*(fxMaisH - fxMenosH);
		return result;
	}
	
	public static double closest(double of, List<Double> in) {
	    double min = Double.MAX_VALUE;
	    double closest = of;

	    for (Double v : in) {
	        final double diff = Math.abs(v - of);

	        if (diff < min) {
	            min = diff;
	            closest = v;
	        }
	    }

	    return closest;
	}
}
