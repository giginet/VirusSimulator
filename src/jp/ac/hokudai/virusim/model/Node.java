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
  
  public Node(NodeState ns){
    init();
    this.state = ns;
    if(ns == NodeState.Immune){
      immune();
    }
   }
  
  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + primaryKey;
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj){
    if(this == obj)
      return true;
    if(obj == null)
      return false;
    if(getClass() != obj.getClass())
      return false;
    Node other = (Node) obj;
    if(primaryKey != other.primaryKey)
      return false;
    return true;
  }

  private void init(){
    Random r = new Random();
    this.primaryKey    = r.nextInt();
    this.vaccine       = true;
    this.crisisRate    = 0;
    this.detectionRate = 0;
  }
  
  /**
   * 免疫状態に移行します
   */
  public void immune(){
    vaccine = true;
    detectionRate = 0;
    crisisRate = 0;
    state = NodeState.Immune;
  }

  /**
   * @return the vaccine
   */
  public boolean isVaccine(){
    return vaccine;
  }

  /**
   * @return the crisisRate
   */
  public double getCrisisRate(){
    return crisisRate;
  }

  /**
   * @return the detectionRate
   */
  public double getDetectionRate(){
    return detectionRate;
  }

  /**
   * @return the state
   */
  public NodeState getState(){
    return state;
  }

  /**
   * @return the neighbors
   */
  public NeighborList getNeighbors(){
    return neighbors;
  }

  /**
   * @param state the state to set
   */
  public void setState(NodeState state){
    this.state = state;
  }
}