/**
 *  Node.java 
 *  created by giginet on 2011/10/06
 */
package jp.ac.hokudai.virusim.models;
import jp.ac.hokudai.virusim.utils.*;
/**
 * @author giginet
 * 
 */
public class Node{
  
  private int          primaryKey;
  private boolean      vaccine;
  private double       crisisRate;
  private double       detectionRate;
  private NodeState    state;
  private NeighborList neighbors;
  
  public Node(int primaryKey, double crisisRate, double detectionRate){
    this.primaryKey    = primaryKey;
    this.vaccine       = false;
    this.crisisRate    = crisisRate;
    this.detectionRate = detectionRate;
    this.state         = NodeState.Normal;
  }
  
  public Node(int primaryKey){
    this.primaryKey    = primaryKey;
    this.vaccine       = true;
    this.crisisRate    = 0;
    this.detectionRate = 0;
    this.state         = NodeState.Immune;
  }

}
