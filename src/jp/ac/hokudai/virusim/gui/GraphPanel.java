package jp.ac.hokudai.virusim.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

import jp.ac.hokudai.virusim.model.Network;
import jp.ac.hokudai.virusim.model.Node;
import jp.ac.hokudai.virusim.model.NodeState;
import jp.ac.hokudai.virusim.util.NodeList;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class GraphPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public GraphPanel(Network network) {
		NodeList nodes = network.getNodes();
		
		Graph<Node, String> graph = new SparseGraph<Node, String>();

		for (Node node : nodes) {
			graph.addVertex(node);
		}

		for (Node node : nodes) {
			NodeList neighbors = node.getNeighbors();

			for (Node neighbor : neighbors) {

				if (!graph.containsEdge(node.toString() + neighbor.toString())) {
					graph.addEdge(node.toString() + neighbor.toString(), node,
							neighbor);
				}
			}
		}

		Layout<Node, String> layout = new KKLayout<Node, String>(graph);
		layout.setSize(new Dimension(600, 600));

		BasicVisualizationServer<Node, String> vv = new BasicVisualizationServer<Node, String>(
				layout);
		vv.getRenderContext().setEdgeShapeTransformer(
				new EdgeShape.Line<Node, String>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.AUTO);
		
		Transformer<Node, Paint> vertexColor = new Transformer<Node, Paint>() {
			public Paint transform(Node node) {
				Color color = Color.GREEN;
				
				if(node.getState() == NodeState.Normal) { color = Color.BLUE; }
				else if(node.getState() == NodeState.Immune) { color = Color.GREEN; }
				else if(node.getState() == NodeState.Infection) { color = Color.RED; }
				
				return color;
			}
		};

		Transformer<Node, Shape> vertexPaint = new Transformer<Node, Shape>() {
			public Shape transform(Node i) {
				return new Arc2D.Double(-5, -5, 10, 10, 0, 360, Arc2D.OPEN);
			}
		};

		vv.getRenderContext().setVertexShapeTransformer(vertexPaint);
		vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
		add(vv);
		
		setSize(600, 600);
	}
}
