package jp.ac.hokudai.virusim.model;
/**
 *  Node.java 
 *  created by giginet on 2011/10/06
 */

import jp.ac.hokudai.virusim.util.*;

import java.util.Iterator;
import java.util.Random;
/**
 * @author giginet
 * 
 */
public class Node{
  
  private int          primaryKey;
  private boolean      influenced;
  private double       crisisRate;
  private double       detectionRate;
  private double       afterDetectionRate;
  private NodeState    state;
  private NodeList neighbors;
  
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
    SettingContainer settings = SettingContainer.getShared();
    this.primaryKey    = r.nextInt();
    this.influenced    = false;
    this.crisisRate    = settings.getMinCrisisRate() + r.nextDouble()%(settings.getMaxCrisisRate()-settings.getMinCrisisRate());
    this.detectionRate = settings.getMinDetectionRate() + r.nextDouble()%(settings.getMaxDetectionRate()-settings.getMinDetectionRate());
    this.afterDetectionRate = settings.getMinAfterDetectionRate() + r.nextDouble()%(settings.getMaxAfterDetectionRate()-settings.getMinAfterDetectionRate());
    this.neighbors     = new NodeList();
  }
  
  /**
   * 免疫状態に移行します
   */
  public void immune(){
    if(state == NodeState.Immune) return;
    influenced = true;
    detectionRate = 0;
    crisisRate = 0;
    state = NodeState.Immune;
  }
  
  /**
   * 自身が感染状態の時、隣接ノードを感染状態にします
   */
  public void influence(){
    if(state != NodeState.Infection) return;
    Iterator<Node> itr = neighbors.iterator();
    while(itr.hasNext()){
      Node n = itr.next();
      if(n.getState() == NodeState.Normal){
        n.setState(NodeState.Infection);
      }
    }
    influenced = true;
  }

  /**
   * @return the influenced
   */
  public boolean isInfluenced(){
    return influenced;
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
  public NodeList getNeighbors(){
    return neighbors;
  }

  /**
   * @param state the state to set
   */
  public void setState(NodeState state){
    this.state = state;
  }

  /**
   * @return the afterDetectionRate
   */
  public double getAfterDetectionRate(){
    return afterDetectionRate;
  }
}