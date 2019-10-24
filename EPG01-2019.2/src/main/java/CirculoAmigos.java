

import java.util.List;
import java.util.Set;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.SimpleGraph;

public class CirculoAmigos {
	
	public static List <Set <String>> retornaCirculos (List<Pair<String,String>> paresAmigos) {
		
		Graph<String, DefaultEdge> graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		
		ConnectivityInspector<String, DefaultEdge> c = new ConnectivityInspector<>(graph);
		
		boolean flag;
		for (Pair<String, String> p : paresAmigos) {
			flag = true;
			if(p.getFirst() != null && p.getFirst() != "" && p.getFirst() != "-") {
				graph.addVertex(p.getFirst());
			} else flag = false;
				
			if(p.getSecond() != null && p.getSecond() != "" && p.getSecond() != "-") {
				graph.addVertex(p.getSecond());	
			} else flag = false;
			
			if(flag) {
				graph.addEdge(p.getFirst(), p.getSecond());
			}
		}
		
		return c.connectedSets();
	}
}
