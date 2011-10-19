package jp.ac.hokudai.virusim.scalefree;

import java.util.ArrayList;
import java.util.Random;

import jp.ac.hokudai.virusim.model.Network;
import jp.ac.hokudai.virusim.model.Node;
import jp.ac.hokudai.virusim.model.NodeState;
import jp.ac.hokudai.virusim.util.NodeList;
import jp.ac.hokudai.virusim.util.SettingContainer;

public class ScaleFreeNetwork extends Network {

	private int nodeCount;
	private int seedCount = 2;
	private int branchCount = 1;
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private static final Random rand = new Random();

	public ScaleFreeNetwork(int nodeCount) {
		nodes = new NodeList();
		this.nodeCount = nodeCount;

		initialize();
	}

	public void initialize() {
		SettingContainer settings = SettingContainer.getShared();

		for (int i = 0; i < nodeCount; ++i) {
			NodeState nodeState = NodeState.Normal;

			if (i < settings.getInitialVirusNodeCount()) {
				nodeState = NodeState.Infection;
			} else if (rand.nextDouble() < settings.getInitialVaccineRate()) {
				nodeState = NodeState.Immune;
				++countInitImmute;
			}

			Node node = new Node(nodeState);
			nodes.add(node);
		}
		
		createSeedGraph();
		expand();
	}
	
	public void addEdge(Node sourceNode, Node targetNode){
        edges.add(new Edge(sourceNode, targetNode));
        sourceNode.getNeighbors().add(targetNode);
        targetNode.getNeighbors().add(sourceNode);
    }

	private void createSeedGraph() {
		for (int i=0; i < seedCount; ++i) {
			for (int j=0; j < seedCount; ++j) {
				addEdge(nodes.get(i), nodes.get(j));
			}
		}
	}

	private void expand() {
		for (int i = seedCount; i < nodeCount; ++i) {
			Node node = nodes.get(i);

			int r = edges.size();
			for (int j = 0; j < branchCount; ++j) {
				int t = rand.nextInt(r);
				Edge selectedEdge = edges.get(t);
				Node selectedNode = (rand.nextDouble() > 0.4) ? selectedEdge.getSourceNode() : selectedEdge.getTargetNode();

				addEdge(node, selectedNode);

			}
		}
	}
}
