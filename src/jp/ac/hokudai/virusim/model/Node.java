package jp.ac.hokudai.virusim.model;
/**
 *  Node.java 
 *  created by giginet on 2011/10/06
 */

import jp.ac.hokudai.virusim.util.*;
import java.util.Random;
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
  
  public Node(double crisisRate, double detectionRate){
    init();
    this.vaccine       = false;
    this.crisisRate    = crisisRate;
    this.detectionRate = detectionRate;
    this.state         = NodeState.Normal;
  }
  
  public Node(){
    init();
    this.vaccine       = true;
    this.crisisRate    = 0;
    this.detectionRate = 0;
    this.state         = NodeState.Immune;
   }
  
  private void init(){
    Random r = new Random();
    this.primaryKey = r.nextInt();
  }

}
