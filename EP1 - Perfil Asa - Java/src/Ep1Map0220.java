import java.awt.Color;
import java.io.File;
import java.io.IOException;


import javax.swing.JOptionPane;

public class Ep1Map0220 {
	public void executa() {
		File theDir = new File("report");  
		try{   
		    if (!theDir.exists())   
		    	theDir.mkdir();    
		} catch(Exception e){  
		    System.out.println("Problemas ao gerar o Diretório de relatórios"); 
		    JOptionPane.showMessageDialog(null,"Problemas ao gerar o Diretório de relatórios");
			return;
		}  

		theDir = new File("data");  
		try{   
		    if (!theDir.exists())   
		    	theDir.mkdir();    
		} catch(Exception e){  
		    System.out.println("Problemas ao gerar o Diretório de dados, lembre-se de copiar os .dat"); 
		    JOptionPane.showMessageDialog(null,"Problemas ao gerar o Diretório de dados, lembre-se de copiar os .dat");
			return;
		}  
		
		TipoArquivoPerfil tipo = new TipoArquivo101Tab();
		PerfilAsa perfil;
		PerfilSpline spline;
		FuncaoEspessura espessura; 
		try {
			perfil = CarregadorArquivo.Perfil("data/davissm.dat", tipo);
			spline = new PerfilSpline(perfil, Distribuicao100Pontos.gerar100Pontos(), 0.01);
			espessura = new FuncaoEspessura(spline.get_extradorsoX(), spline.get_Espessura()); 
			
			calcMax(spline, "davissm", espessura);
		} catch (IOException e) {
			System.out.println("Problemas ao carregar o arquivo davissm.dat do diretório data " + "data/davissm.dat");
			JOptionPane.showMessageDialog(null,"Problemas ao carregar o arquivo davissm.dat do diretório data " + "data/davissm.dat");
			return;
		}
		
		
		tipo = new TipoArquivo101();
		try {
			perfil = CarregadorArquivo.Perfil("data/fx84w127.dat", tipo);
			spline = new PerfilSpline(perfil, Distribuicao100Pontos.gerar100Pontos(), 0.01);
			espessura = new FuncaoEspessura(spline.get_extradorsoX(), spline.get_Espessura());
			calcMax(spline, "fx84w127", espessura);
		} catch (IOException e) {
			System.out.println("Problemas ao carregar o arquivo fx84w127.dat do diretório data " + "data/fx84w127.dat");
			JOptionPane.showMessageDialog(null,"Problemas ao carregar o arquivo fx84w127.dat do diretório data " + "data/fx84w127.dat");
			return;
		}
		
		
		tipo = new TipoArquivoAirFole();
		try {
			perfil = CarregadorArquivo.Perfil("data/th25816.dat", tipo);
			spline = new PerfilSpline(perfil, Distribuicao100Pontos.gerar100Pontos(), 0.01);
			espessura = new FuncaoEspessura(spline.get_extradorsoX(), spline.get_Espessura());
			calcMax(spline, "th25816", espessura);
		} catch (IOException e) {
			System.out.println("Problemas ao carregar o arquivo fx84w127.dat do diretório data " + "data/th25816.dat");
			JOptionPane.showMessageDialog(null,"Problemas ao carregar o arquivo fx84w127.dat do diretório data " + "data/th25816.dat");
			return;
		}

		JOptionPane.showMessageDialog(null,"Ep01 Executado com Sucesso");
	}
	
	private void calcMax(PerfilSpline spline, String nome, FuncaoEspessura alturas) {
    	int max = spline.get_extradorsoLinha().indexOf(Derivada3Pontos.closest(0.0, spline.get_extradorsoLinha()));
    	int min = spline.get_intradorsoLinha().indexOf(Derivada3Pontos.closest(0.0, spline.get_intradorsoLinha()));
    	    	/*
		JOptionPane.showMessageDialog(null,"Max = " + max + " Min = " + min);
    	if (max == min ) {
    		JOptionPane.showMessageDialog(null,"Max = Min");
    	}
    	*/
    	
		int maxAlturaD = alturas.posMaxAltura();
		//JOptionPane.showMessageDialog(null,"Soma Deriv = " + maxSomaDeriv);
    	
		Plot plot = Plot.plot(Plot.plotOpts().
		        title(nome).
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
					markerSize(2)).
		    series("Intrabordo", Plot.data().
		    		xy(spline.get_intradorsoX(), spline.get_intradorsoY()), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(2)).
		    series("DerivadaExtradorso_ext", Plot.data().
				    xy(spline.get_extradorsoX().get(max), spline.get_extradorsoY().get(max)), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.GREEN).
					markerSize(10)).
		    series("DerivadaExtradorso_intra", Plot.data().
				    xy(spline.get_intradorsoX().get(max), spline.get_intradorsoY().get(max)), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.GREEN).
					markerSize(10)).
		    series("DerivadaIntradorso_ext", Plot.data().
				    xy(spline.get_extradorsoX().get(min), spline.get_extradorsoY().get(min)), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.RED).
					markerSize(10)).
		    series("DerivadaIntradorso_int", Plot.data().
				    xy(spline.get_intradorsoX().get(min), spline.get_intradorsoY().get(min)), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.RED).
					markerSize(10)).
		    series("MaxEspessura_ext", Plot.data().
				    xy(alturas.get_AlturaX().get(maxAlturaD), spline.get_AlturaExtradorsoSpline(alturas.get_AlturaX().get(maxAlturaD))), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.YELLOW).
					markerSize(10)).
		    series("MaxEspessura_int", Plot.data().
				    xy(alturas.get_AlturaX().get(maxAlturaD), spline.get_AlturaIntradorsoSpline(alturas.get_AlturaX().get(maxAlturaD))), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.YELLOW).
					markerSize(10))
    
		    ;
		
		try {
			plot.save("report/"+nome+"_MAX", "png");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Erro ao gravar imagem de relatório " + "report/"+nome+"_MAX");
		}

		plot = Plot.plot(Plot.plotOpts().
		        title(nome).
		        width(1200).
				height(900).
				legend(Plot.LegendFormat.BOTTOM)).
		    xAxis("x", Plot.axisOpts().
		        range(0, 1)).
		    yAxis("y", Plot.axisOpts().
		        range(-0.3, 0.3)).
		    series("Altura", Plot.data().
		    		xy(alturas.get_AlturaX(), alturas.get_AlturaY()), 
		    		Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(2)).
		    series("Max", Plot.data().
		    		xy(alturas.get_AlturaX().get(maxAlturaD), alturas.get_AlturaY().get(maxAlturaD)), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.PINK).
					markerSize(10));

		try {
			plot.save("report/"+nome+"_Alturas", "png");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Erro ao gravar imagem de relatório " + "./report/"+nome+"_Alturas");
		}
		System.out.println(nome + " Posição = " + alturas.get_AlturaX().get(maxAlturaD)+ " Espessura = " + alturas.get_AlturaY().get(maxAlturaD));
		//JOptionPane.showMessageDialog(null,nome + " Posição = " + alturas.get_AlturaX().get(maxAlturaD)+ " Espessura = " + alturas.get_AlturaY().get(maxAlturaD));

		plot = Plot.plot(Plot.plotOpts().
		        title(nome).
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
					markerSize(2)).
		    series("Intrabordo", Plot.data().
		    		xy(spline.get_intradorsoX(), spline.get_intradorsoY()), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerSize(2)).
		    series("MaxEspessura_ext", Plot.data().
				    xy(alturas.get_AlturaX().get(maxAlturaD), spline.get_AlturaExtradorsoSpline(alturas.get_AlturaX().get(maxAlturaD))), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.YELLOW).
					markerSize(10)).
		    series("MaxEspessura_int", Plot.data().
				    xy(alturas.get_AlturaX().get(maxAlturaD), spline.get_AlturaIntradorsoSpline(alturas.get_AlturaX().get(maxAlturaD))), Plot.seriesOpts().
					line(Plot.Line.NONE).
					marker(Plot.Marker.DIAMOND).
					markerColor(Color.YELLOW).
					markerSize(10))
		    ;
		
		try {
			plot.save("report/"+nome+"_SOMAX", "png");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Erro ao gravar imagem de relatório " + "report/"+nome+"_MAX");
		}

	}

}
