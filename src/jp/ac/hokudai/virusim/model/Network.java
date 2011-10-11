package jp.ac.hokudai.virusim.model;

import java.util.ArrayList;

import jp.ac.hokudai.virusim.model.*;
/**
 *  Network.java 
 *  created by giginet on 2011/10/06
 */

/**
 * @author giginet
 *
 */
public class Network{
  
  private ArrayList<Node> nodes;
  
  public Network(int nodeCount){
    nodes = new ArrayList<Node>();
    for(int i=0;i<nodeCount;++i){
      Node n = new Node();
      nodes.add(n);
    }
  }

}
