package jp.ac.hokudai.virusim.model;

import java.util.ArrayList;
import java.util.List;
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
  
  private ArrayList<Node> nodes;
  
  /**
   * ネットワークを初期化します。コンストラクタには初期ノード数を渡します
   * @param nodeCount
   */
  public Network(int nodeCount){
    nodes = new ArrayList<Node>();
    Random r = new Random();
    SettingContainer settings = SettingContainer.getShared();
    // generate nodes
    for(int i=0;i<nodeCount;++i){
      NodeState ns = NodeState.Normal;
      if(i < settings.getInitialVirusNodeCount()){
        ns = NodeState.Crisis;
      }else if(r.nextGaussian() < settings.getInitialVaccineRate()){
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
      if(n.getState() == NodeState.Crisis){
        // 発症状態の時、隣接ノードを感染状態にする
      }else if(n.getState() == NodeState.Infection){
        // 感染状態の時、一定確率で発症状態に移行する
        if(r.nextGaussian() < n.getCrisisRate()){
          n.setState(NodeState.Crisis);
        }  
      }
      if(n.getState() == NodeState.Crisis || n.getState() == NodeState.Infection)
        if(r.nextGaussian() < n.getDetectionRate()){
          n.immune();
        }
      }
  }

}
