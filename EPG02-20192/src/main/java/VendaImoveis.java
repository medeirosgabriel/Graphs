import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.io.CSVFormat;
import org.jgrapht.io.CSVImporter;

public class VendaImoveis {

	Graph<String, DefaultWeightedEdge> distrito;

	public static Graph<String, DefaultWeightedEdge> importWeightedGraphCSV(Graph<String, DefaultWeightedEdge> graph,
			String filename) {
		// WEIGHTED EDGE LIST
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine = br.readLine();
			while ((sCurrentLine = br.readLine()) != null) {
				String[] attributes = sCurrentLine.split(",");
				graph.addVertex(attributes[0]);
				graph.addVertex(attributes[1]);
				DefaultWeightedEdge e = new DefaultWeightedEdge();
				graph.addEdge(attributes[0], attributes[1], e);
				graph.setEdgeWeight(e, new Double(attributes[2]).doubleValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return graph;
	}

	VendaImoveis(String fileName) {
		/*
		 * Adicione aqui o c�digo que cria uma instancia da classe grafo desejada a
		 * partir de um arquivo cujo nome est� definido em filename. Veja m�todos para
		 * importa��o de arquivos em MyJGraphTUtil.java
		 */
		distrito = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		distrito = importWeightedGraphCSV(distrito, fileName);

	}

	public String localizaImovel(String pontodeInteresse, Set<String> imoveis) {
		String resultado = null;
		/*
		 * Adicione aqui o c�digo que recebe um ponto de interesse como entrada, uma
		 * lista de im�veis e retorna o im�vel dispon�vel mais pr�ximo a este ponto de
		 * interesse. Note que tanto o ponto de interesse quanto o im�vel a ser
		 * selecionado s�o v�rtices do grafo.
		 */
		if (pontodeInteresse != null && this.distrito.vertexSet().contains(pontodeInteresse) && imoveis != null) {
			DijkstraShortestPath<String, DefaultWeightedEdge> dsp = new DijkstraShortestPath<>(this.distrito);
			ShortestPathAlgorithm.SingleSourcePaths<String, DefaultWeightedEdge> paths = dsp.getPaths(pontodeInteresse);

			double peso = Integer.MAX_VALUE;
			resultado = null;
			for (String target : imoveis) {
				if (paths.getWeight(target) < peso) {
					resultado = target;
					peso = paths.getWeight(target);
				}
			}
		}
		
		return resultado;
	}

}
