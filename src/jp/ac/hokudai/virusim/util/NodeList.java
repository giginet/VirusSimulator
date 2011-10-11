package jp.ac.hokudai.virusim.util;
/**
 *  NeighborContainer.java 
 *  created by giginet on 2011/10/06
 */


import java.util.*;

import jp.ac.hokudai.virusim.model.*;

/**
 * @author giginet
 *
 */
public class NodeList extends ArrayList<Node>{
  
  private static final long serialVersionUID = 1L;

  /**
   * Nodeの中から、指定のNodeStateのNodeのみをArrayListで返します
   * @param st NodeState
   * @return ArrayList<Node>
   */
  public ArrayList<Node> getNodeStates(NodeState st){
    ArrayList<Node> list = new ArrayList<Node>();
    Iterator<Node> itr = this.iterator();
    while(itr.hasNext()){
      Node n = itr.next();
      if(n.getState() == st){
        list.add(n);
      }
    }
    return list;
  }
  
}
