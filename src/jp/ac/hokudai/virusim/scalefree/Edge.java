package jp.ac.hokudai.virusim.scalefree;

import jp.ac.hokudai.virusim.model.Node;

/**
 *
 * @author takamatsu
 */
public class Edge {
    
    private Node sourceNode;
    private Node targetNode;
    
    public Edge(Node sourceNode, Node targetNode){
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }
    
    public Node getSourceNode(){
        return sourceNode;
    }
    
    public Node getTargetNode(){
        return targetNode;
    }
}
