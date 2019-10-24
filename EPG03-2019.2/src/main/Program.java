package main;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.alg.scoring.ClosenessCentrality;
import org.jgrapht.alg.scoring.ClusteringCoefficient;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 * Classe onde são respondidas as perguntas do exercício. Nela há métodos
 * necessário para obtenção de todas as respostas.
 */
public class Program {
	
	/**
	 * Método principal por onde o programa se inicia. Através dele são respondidas
	 * as perguntas do exercício.
	 * 
	 * @param args argumentos que podem ser passados na linha de comando
	 * (não aplicáveis nesse projeto).
	 */
	public static void main(String args[]) {
		Graph<String, DefaultEdge> g = new SimpleWeightedGraph<String, DefaultEdge>(null);
		Graph<String, DefaultEdge> graph= Importer.importDefaultGraphGML(g, "./src/antcolony1000.gml");
		System.out.println("1.5 formigas que melhor atuaram: " + conduction(graph).stream().collect(Collectors.joining(" ")));
		System.out.println("2.5 formigas mais influentes: " + mostInfluential(graph).stream().collect(Collectors.joining(" ")));
		System.out.println("3.Coeficiente de clustering: " + clusteringCoefficient(graph));
		System.out.println("4.Coeficiente de assortatividade: " + assortativityCoefficient(graph));
	}
	
	/**
	 * Método que retorna os vértices do grafo que possuem maior grau, ou seja,
	 * aqueles que possuem maior conectividade com outros vértices.
	 * 
	 * @param graph o grafo que servirá para busca dos vértices.
	 * @return um conjunto de vértices com maior conectividade a outros vértices.
	 */
	public static Set<String> conduction(Graph<String, DefaultEdge> graph) {
		Set<String> verticesConducao = new HashSet<>();
		for (int i = 0; i < 5; i++) {
			String vertex = "";
			int grau = 0;
			for (String v : graph.vertexSet()) {
				if (graph.degreeOf(v) > grau && verticesConducao.contains(v) == false) {
					vertex = v;
					grau = graph.degreeOf(v);
				}
			}
			verticesConducao.add(vertex);
		}
		return verticesConducao;
	}
	
	/**
	 * Método que calcula os cinco vértices mais influentes em um grafo, aqueles
	 * que possuem maior centralidade.
	 * 
	 * @param graph o grafo que servirá para a busca dos vértices.
	 * @return um conjunto dos vértices que satisfazem o uso do método.
	 */
	public static Set<String> mostInfluential(Graph<String, DefaultEdge> graph) {
		ClosenessCentrality<String, DefaultEdge> closec = new ClosenessCentrality<>(graph);
		Set<String> vertexSet = new HashSet<>();
		for (int i = 0; i < 5; i++) {
			double value = 0;
			String name = "";
			for (String v : graph.vertexSet()) {
				if (!vertexSet.contains(v) && closec.getScores().get(v) > value) {
					value = closec.getScores().get(v);
					name = v;
				}
			}
			vertexSet.add(name);
		}
		return vertexSet;
	}
	
	/**
	 * Método que calcula o coeficiente da tendência com que os vértices de um
	 * grafo tendem a se agrupar.
	 * 
	 * @param graph o grafo utilizado para o cálculo do coeficiente de clustering.
	 * @return um valor de ponto flutuante que representa esse coeficiente.
	 */
	public static double clusteringCoefficient(Graph<String, DefaultEdge> graph) {
		ClusteringCoefficient<String, DefaultEdge> cc = new ClusteringCoefficient<String, DefaultEdge>(graph);
		return cc.getGlobalClusteringCoefficient();
	}
	
	/**
	 * Método que calcula o coeficiente de assortatividade, ou seja, a tendência
	 * de um vértice de se ligar com outros de grau similar.
	 * 
	 * @param graph o grafo responsável pelo cálculo utilizado no método.
	 * @return um valor de ponto flutuante representativo do coeficiente.
	 */
	public static double assortativityCoefficient (Graph <String, DefaultEdge> graph) {
    	double edgeCount = graph.edgeSet().size();
        double n1 = 0, n2 = 0, dn = 0;

        for (DefaultEdge e : graph.edgeSet()) {
            int d1 = graph.degreeOf(graph.getEdgeSource(e));
            int d2 = graph.degreeOf(graph.getEdgeTarget(e));

            n1 += d1 * d2;
            n2 += d1 + d2;
            dn += d1 * d1 + d2 * d2;
        }
        n1 /= edgeCount;
        n2 = (n2 / (2 * edgeCount)) * (n2 / (2 * edgeCount));
        dn /= (2 * edgeCount);
        
        return (n1 - n2) / (dn - n2);
    }
}
