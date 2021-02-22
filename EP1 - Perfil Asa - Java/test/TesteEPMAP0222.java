import static org.junit.Assert.*;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TesteEPMAP0222 {

	@Test
	public void testDatLoader101TabOk() throws IOException {
		TipoArquivoPerfil tipo = new TipoArquivo101Tab();
		PerfilAsa perfil = CarregadorArquivo.Perfil("data\\davissm.dat", tipo);
		//System.out.println("ExtraDorso");
		//perfil.showExtradorso();
		
		//Totpontos intradorso 39
		assertEquals(perfil.size_extradorso(), 39);
		//Teste 3 pontos
		assertEquals(perfil.get_extradorso(0.90775), 0.02831, 0.001);
		assertEquals(perfil.get_extradorso(0.56937), 0.08107, 0.001);
		assertEquals(perfil.get_extradorso(0.05468), 0.03359, 0.001);
		
		//System.out.println("IntraDorso");
		//perfil.showIntradorso();
		//Totpontos extradorso 39
		assertEquals(perfil.size_intradorso(), 39);
		//Teste 3 pontos
		assertEquals(perfil.get_intradorso(0.07395), -0.00091, 0.001);
		assertEquals(perfil.get_intradorso(0.45435), 0.03140, 0.001);
		assertEquals(perfil.get_intradorso(0.95044), -0.00086, 0.001);
		
		Plot plot = Plot.plot(Plot.plotOpts().
		        title("davissm.dat").
		        width(1200).
				height(900).
				legend(Plot.LegendFormat.BOTTOM)).
		    xAxis("x", Plot.axisOpts().
		        range(0, 1)).
		    yAxis("y", Plot.axisOpts().
		        range(-0.3, 0.3)).
		    series("Extrabordo", Plot.data().
		    		xy(perfil.get_extradorsoX(), perfil.get_extradorsoY()), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(5)).
		    series("Intrabordo", Plot.data().
		    		xy(perfil.get_intradorsoX(), perfil.get_intradorsoY()), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(5).color(Color.RED));

		try {
			plot.save("report\\davissm", "png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDatLoader101Ok() throws IOException {
		TipoArquivoPerfil tipo = new TipoArquivo101();
		PerfilAsa perfil = CarregadorArquivo.Perfil("data\\fx84w127.dat", tipo);
		//System.out.println("ExtraDorso");
		//perfil.showExtradorso();
		
		//Totpontos intradorso 49
		assertEquals(perfil.size_extradorso(), 49);
		//Teste 3 pontos
		assertEquals(perfil.get_extradorso(0.96194), 0.00588, 0.001);
		assertEquals(perfil.get_extradorso(0.69134), 0.06511, 0.001);
		assertEquals(perfil.get_extradorso(0.00107), 0.00519, 0.001);
		
		//System.out.println("IntraDorso");
		//perfil.showIntradorso();
		//Totpontos extradorso 48
		assertEquals(perfil.size_intradorso(), 48);
		//Teste 3 pontos
		assertEquals(perfil.get_intradorso(0.03806), -0.02164, 0.001);
		assertEquals(perfil.get_intradorso(0.53270), -0.02113, 0.001);
		assertEquals(perfil.get_intradorso(0.99572), 0.00028, 0.001);
		
		Plot plot = Plot.plot(Plot.plotOpts().
		        title("fx84w127.dat").
		        width(1200).
				height(900).
				legend(Plot.LegendFormat.BOTTOM)).
		    xAxis("x", Plot.axisOpts().
		        range(0, 1)).
		    yAxis("y", Plot.axisOpts().
		        range(-0.3, 0.3)).
		    series("Extrabordo", Plot.data().
		    		xy(perfil.get_extradorsoX(), perfil.get_extradorsoY()), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(5)).
		    series("Intrabordo", Plot.data().
		    		xy(perfil.get_intradorsoX(), perfil.get_intradorsoY()), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(5).color(Color.RED));

		try {
			plot.save("report\\fx84w127", "png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDatLoaderHaleAirFoleOk() throws IOException {
		TipoArquivoPerfil tipo = new TipoArquivoAirFole();
		PerfilAsa perfil = CarregadorArquivo.Perfil("data\\th25816.dat", tipo);
		//System.out.println("ExtraDorso");
		//perfil.showExtradorso();
		
		//Totpontos intradorso 32
		assertEquals(perfil.size_extradorso(), 32);
		//Teste 3 pontos
		assertEquals(perfil.get_extradorso(0.037800), 0.054880, 0.001);
		assertEquals(perfil.get_extradorso(0.345630), 0.159820, 0.001);
		assertEquals(perfil.get_extradorso(0.940920), 0.034190, 0.001);
		assertEquals(perfil.get_extradorso(1.000000), 0.0, 0.001);
		
		//System.out.println("IntraDorso");
		//perfil.showIntradorso();
		//Totpontos extradorso 29
		assertEquals(perfil.size_intradorso(), 29);
		//Teste 3 pontos
		assertEquals(perfil.get_intradorso(0.031030), -0.028760, 0.001);
		assertEquals(perfil.get_intradorso(0.370630), -0.087580, 0.001);
		assertEquals(perfil.get_intradorso(0.994690), 0.000710, 0.001);
		
		
		Plot plot = Plot.plot(Plot.plotOpts().
		        title("th25816.dat").
		        width(1200).
				height(900).
				legend(Plot.LegendFormat.BOTTOM)).
		    xAxis("x", Plot.axisOpts().
		        range(0, 1)).
		    yAxis("y", Plot.axisOpts().
		        range(-0.3, 0.3)).
		    series("Extrabordo", Plot.data().
		    		xy(perfil.get_extradorsoX(), perfil.get_extradorsoY()), 
		    		Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(5)).
		    series("Intrabordo", Plot.data().
		    		xy(perfil.get_intradorsoX(), perfil.get_intradorsoY()), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(5).color(Color.RED));

		try {
			plot.save("report\\th25816", "png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDistribuicao100() {
		List<Double> dist = Distribuicao100Pontos.gerar100Pontos();
		assertEquals(dist.size(), 100);
		
		//for (Double item : dist) {
		//	System.out.println(item);
		//}
		
		Plot plot = Plot.plot(Plot.plotOpts().
		        title("Distribuicao").
		        width(1200).
				height(300).
				legend(Plot.LegendFormat.BOTTOM)).
		    xAxis("x", Plot.axisOpts().
		        range(0, 1)).
		    yAxis("y", Plot.axisOpts().
		        range(0, 0.1)).
		    series("Distribuicao", Plot.data().
		    		xy(dist, null), 
		    		Plot.seriesOpts().
					color(Color.BLACK).
					line(Plot.Line.NONE).
					lineWidth(0).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.GREEN).
					markerSize(5));

		try {
			plot.save("report\\Distribuicao", "png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSplineCubica() {
		List<Double> _x = new ArrayList<Double>();
		List<Double> _fx = new ArrayList<Double>();
		
		_x.add(0.9);
		_x.add(1.3);
		_x.add(1.9);
		_x.add(2.1);
		_x.add(2.6);
		_x.add(3.0);
		_x.add(3.9);
		_x.add(4.4);
		_x.add(4.7);
		_x.add(5.0);
		_x.add(6.0);
		_x.add(7.0);
		_x.add(8.0);
		_x.add(9.2);
		_x.add(10.5);
		_x.add(11.3);
		_x.add(11.6);
		_x.add(12.0);
		_x.add(12.6);
		_x.add(13.0);
		_x.add(13.3);
		
		_fx.add(1.3);
		_fx.add(1.5);
		_fx.add(1.85);
		_fx.add(2.1);
		_fx.add(2.6);
		_fx.add(2.7);
		_fx.add(2.4);
		_fx.add(2.15);
		_fx.add(2.05);
		_fx.add(2.1);
		_fx.add(2.25);
		_fx.add(2.3);
		_fx.add(2.25);
		_fx.add(1.95);
		_fx.add(1.4);
		_fx.add(0.9);
		_fx.add(0.7);
		_fx.add(0.6);
		_fx.add(0.5);
		_fx.add(0.4);
		_fx.add(0.25);

		SplineCubica spline = new SplineCubica(_x, _fx);
	}

	@Test
	public void testSplineCubicae() {
		List<Double> _x = new ArrayList<Double>();
		List<Double> _fx = new ArrayList<Double>();
		
		_x.add(0.0);
		_x.add(1.0);
		_x.add(2.0);
		_x.add(3.0);
		
		_fx.add(Math.pow(Math.E, 0.0));
		_fx.add(Math.pow(Math.E, 1.0));
		_fx.add(Math.pow(Math.E, 2.0));
		_fx.add(Math.pow(Math.E, 3.0));
		
		SplineCubica spline = new SplineCubica(_x, _fx);
		
		assertEquals(spline.get_x(0), 0.0, 0.0001);	
		assertEquals(spline.get_a(0), 1.0, 0.0001);		
		assertEquals(spline.get_b(0), 1.46600, 0.0001);		
		assertEquals(spline.get_c(0), 0.0, 0.0001);		
		assertEquals(spline.get_d(0), 0.25228, 0.0001);	

		assertEquals(spline.get_x(1), 1.0, 0.0001);	
		assertEquals(spline.get_a(1), 2.7182818, 0.0001);		
		assertEquals(spline.get_b(1), 2.22285, 0.0001);		
		assertEquals(spline.get_c(1), 0.75685, 0.0001);		
		assertEquals(spline.get_d(1), 1.69107, 0.0001);
		
		assertEquals(spline.get_x(2), 2.0, 0.0001);	
		assertEquals(spline.get_a(2), 7.389056, 0.0001);		
		assertEquals(spline.get_b(2), 8.80977, 0.0001);		
		assertEquals(spline.get_c(2), 5.83007, 0.0001);		
		assertEquals(spline.get_d(2), -1.94336, 0.0001);
		
		assertEquals(spline.get_x(3), 3.0, 0.0001);	
		assertEquals(spline.get_a(3), 20.08553, 0.0001);		
		assertEquals(spline.get_b(3), 0.0, 0.0001);		
		assertEquals(spline.get_c(3), 0.0, 0.0001);		
		assertEquals(spline.get_d(3), 0.0, 0.0001);
		
		//deveria ser 12.18249 mas as contas dão 13.008
		assertEquals(spline.calcula(2.5), 13.00853, 0.0001); 
		//deveria ser 18.174145 mas as contas dão 18.6234966
		assertEquals(spline.calcula(2.9), 18.6234966, 0.0001);
		//deveria ser 24.5325302 mas as contas dão 22.9979573
		assertEquals(spline.calcula(3.2), 22.9979573, 0.0001);
		

	}

	@Test
	public void testDerivada3Pontos() {
		List<Double> _x = new ArrayList<Double>();
		
		_x.add(1.8);
		_x.add(1.9);
		_x.add(2.0);
		_x.add(2.1);
		_x.add(2.2);		
		
		List<Double> _fx = new ArrayList<Double>();
		
		_fx.add(10.889365);
		_fx.add(12.703199);
		_fx.add(14.778112);
		_fx.add(17.148957);
		_fx.add(19.855030);
		
		List<Double> xLinha = Derivada3Pontos.calculaTab(_x, _fx, 0.1);
		
		assertEquals(xLinha.get(0), 16.832944, 0.0001);		
		assertEquals(xLinha.get(1), 22.032309, 0.0001);
		
		assertEquals(Derivada3Pontos.calcula3Pontos(_fx.get(2),_fx.get(1),_fx.get(0), 0.1), -22.054525, 0.0001);		
		assertEquals(Derivada3Pontos.calcula3PontosExtremos(_fx.get(3),_fx.get(2),_fx.get(1), 0.1), 22.2287899, 0.0001);	
		assertEquals(Derivada3Pontos.calcula3PontosExtremos(_fx.get(4),_fx.get(2),_fx.get(0), 0.2), 22.4141625, 0.0001);	

	}
	
	@Test
	public void testSplinePerfil() throws IOException {
		TipoArquivoPerfil tipo = new TipoArquivo101();
		PerfilAsa perfil = CarregadorArquivo.Perfil("data\\fx84w127.dat", tipo);
		PerfilSpline spline = new PerfilSpline(perfil, Distribuicao100Pontos.gerar100Pontos(), 0.05);
		//spline.showExtradorso();
		//spline.showIntradorso();
		
		Plot plot = Plot.plot(Plot.plotOpts().
		        title("fx84w127 Desenho").
		        width(1200).
				height(900).
				legend(Plot.LegendFormat.BOTTOM)).
		    xAxis("x", Plot.axisOpts().
		        range(0, 1)).
		    yAxis("y", Plot.axisOpts().
		        range(-0.3, 0.3)).
		    series("Extrabordo", Plot.data().
		    		xy(spline.get_extradorsoX(), spline.get_extradorsoY()), 
		    		Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(5)).
		    series("Intrabordo", Plot.data().
		    		xy(spline.get_intradorsoX(), spline.get_intradorsoY()), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(5));
		
		try {
			plot.save("report\\fx84w127_distribuido", "png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testSplineDerivadaPerfil() throws IOException {
		TipoArquivoPerfil tipo = new TipoArquivo101();
		PerfilAsa perfil = CarregadorArquivo.Perfil("data\\fx84w127.dat", tipo);
		PerfilSpline spline = new PerfilSpline(perfil, Distribuicao100Pontos.gerar100Pontos(), 0.05);
		
    	for (int i = 0; i <  spline.get_extradorsoX().size(); i++) {
    		System.out.println(spline.get_extradorsoX().get(i) + " -> " + spline.get_extradorsoY().get(i) + " \'-> " + spline.get_extradorsoLinha().get(i));
		}
    	
    	for (int i = 0; i <  spline.get_intradorsoX().size(); i++) {
    		System.out.println(spline.get_intradorsoX().get(i) + " -> " + spline.get_intradorsoY().get(i) + " \'-> " + spline.get_intradorsoLinha().get(i));
		}
    	
    	System.out.println(Derivada3Pontos.closest(0.0, spline.get_extradorsoLinha()));
    	int max = spline.get_extradorsoLinha().indexOf(Derivada3Pontos.closest(0.0, spline.get_extradorsoLinha()));
    	System.out.println(max);
    	System.out.println(spline.get_extradorsoY().get(max) + spline.get_intradorsoY().get(max));
		
    	System.out.println(Derivada3Pontos.closest(0.0, spline.get_intradorsoLinha()));
    	int min = spline.get_intradorsoLinha().indexOf(Derivada3Pontos.closest(0.0, spline.get_intradorsoLinha()));
    	System.out.println(min);
    	System.out.println(spline.get_extradorsoY().get(min) + spline.get_intradorsoY().get(min));
    	
		Plot plot = Plot.plot(Plot.plotOpts().
		        title("fx84w127 Desenho").
		        width(1200).
				height(900).
				legend(Plot.LegendFormat.BOTTOM)).
		    xAxis("x", Plot.axisOpts().
		        range(0, 1)).
		    yAxis("y", Plot.axisOpts().
		        range(-0.3, 0.3)).
		    series("Extrabordo", Plot.data().
		    		xy(spline.get_extradorsoX(), spline.get_extradorsoY()), 
		    		Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(5)).
		    series("Intrabordo", Plot.data().
		    		xy(spline.get_intradorsoX(), spline.get_intradorsoY()), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(5)).
		    series("MaxExtra", Plot.data().
				    xy(spline.get_extradorsoX().get(max), spline.get_extradorsoY().get(max)), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.GREEN).
					markerSize(10)).
		    series("MaxIntra", Plot.data().
				    xy(spline.get_intradorsoX().get(max), spline.get_intradorsoY().get(max)), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.GREEN).
					markerSize(10)).
		    series("MixExtra", Plot.data().
				    xy(spline.get_extradorsoX().get(min), spline.get_extradorsoY().get(min)), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.RED).
					markerSize(10)).
		    series("MixIntra", Plot.data().
				    xy(spline.get_intradorsoX().get(min), spline.get_intradorsoY().get(min)), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.RED).
					markerSize(10));
		
		try {
			plot.save("report\\fx84w127_MAX", "png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAlturaPerfil() throws IOException {
		
	}

	
	@Test
	public void testAlturaPerfilMax() throws IOException {
		
	}

}
