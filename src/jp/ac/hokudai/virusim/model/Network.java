package jp.ac.hokudai.virusim.model;

import java.util.ArrayList;
import java.util.Random;

import jp.ac.hokudai.virusim.util.*;
import java.util.Collections;
import java.util.Iterator;

/**
 *  Network.java 
 *  created by giginet on 2011/10/06
 */

/**
 * @author giginet
 *
 */
public class Network{
  
  private NodeList nodes;
  
  /**
   * ネットワークを初期化します
   * @param nodeCount 初期ノード数
   */
  public Network(int nodeCount){
    nodes = new NodeList();
    Random r = new Random();
    SettingContainer settings = SettingContainer.getShared();
    // generate nodes
    for(int i=0;i<nodeCount;++i){
      NodeState ns = NodeState.Normal;
      if(i < settings.getInitialVirusNodeCount()){
        ns = NodeState.Infection;
      }else if(r.nextDouble() < settings.getInitialVaccineRate()){
        ns = NodeState.Immune;
      }
      Node n = new Node(ns);
      nodes.add(n);
    }
    // connection between nodes
    Iterator<Node> itr = nodes.iterator();
    while(itr.hasNext()){
      Node n = itr.next();
      int neighborCount = settings.getMinNeighborNodeCount() + r.nextInt()%(settings.getMaxNeightborNodeCount() - settings.getMinNeighborNodeCount());
      if(neighborCount < 0){
        neighborCount = 0;
      }else if(neighborCount > settings.getMaxNeightborNodeCount()){
        neighborCount = settings.getMaxNeightborNodeCount();
      }
      @SuppressWarnings("unchecked")
      ArrayList<Node> clone = (ArrayList<Node>)nodes.clone();
      Collections.shuffle(clone);
      Iterator<Node> cloneItr = clone.iterator();
      for(int i=0;i<neighborCount;++i){
        n.getNeighbors().add(cloneItr.next());
      }
    }
  }
  
  /**
   * ネットワークの状態を一定時間動かして遷移させます
   */
  public void run(){
    Iterator<Node> itr = nodes.iterator();
    Random r = new Random();
    while(itr.hasNext()){
      Node n = itr.next();
      if(n.getState() == NodeState.Infection){
        // 感染状態の時、一定確率で隣接ノードを感染させる
        if(r.nextDouble() < n.getCrisisRate()){
          n.influence();
        }
        // 感染状態の時、一定確率で免疫状態になる
        if(r.nextDouble() < n.getDetectionRate()){
          n.immune();
        }
      }
    }
  }

  /**
   * @return the nodes
   */
  public NodeList getNodes(){
    return nodes;
  }
  
}
