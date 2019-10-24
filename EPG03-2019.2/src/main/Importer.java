package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;

/**
 * Classe responsável por conter métodos necessários para a leitura de grafos a
 * partir de arquivos.
 */
public class Importer {
	
	/**
	 * Método que gera um grafo a partir de dados contidos em um arquivo GraphGML
	 * ".gml".
	 * 
	 * @param graph    a variável que irá guardar o grafo lido a partir do arquivo.
	 * @param filename o caminho para o arquivo ".gml" que contém os dados do grafo.
	 * @return um grafo gerado a partir do arquivo .gml.
	 */
	public static Graph<String,DefaultEdge> importDefaultGraphGML (Graph<String,DefaultEdge> graph, String filename) {
		VertexProvider<String> vp1 = (label, attributes) -> label;
		EdgeProvider<String, DefaultEdge> ep1 = (from, to, label, attributes) -> new DefaultEdge();
		GmlImporter<String, DefaultEdge> gmlImporter = new GmlImporter<>(vp1, ep1);
		try {
			gmlImporter.importGraph(graph, readFile(filename));
		} catch (ImportException e) {
			throw new RuntimeException(e);
		}
		return graph;
	}
	
	/**
	 * Método responsável pela leitura do arquivo ".gml" que irá gerar o grafo.
	 * 
	 * @param filename o caminho para o arquivo ".gml".
	 * @return um stream de caracteres com os dados do arquivo ".gml".
	 */
	public static Reader readFile(String filename) {
		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				contentBuilder.append(sCurrentLine).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringReader readergml = new StringReader(contentBuilder.toString());
		return readergml;
	}
}
