package jp.ac.hokudai.virusim.model;

import java.util.Random;

import jp.ac.hokudai.virusim.util.*;
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
      if(settings.getGraphType() == GraphType.Complete || settings.getGraphType() == GraphType.Random){
        double p = settings.getGraphType() == GraphType.Complete ? 1 :(double)settings.getNeighborNodeAverage()/(nodes.size() - 1);
        Iterator<Node> itrNode = nodes.iterator();
        while(itrNode.hasNext()){
          Node n2 = itrNode.next();
          if(r.nextDouble() < p){
            n.connect(n2);
          }
        }
      }else if(settings.getGraphType() == GraphType.ScaleFree){
        // スケールフリーグラフの生成
        // 実装しておいてください
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
        if(r.nextDouble() < (!n.isInfluenced() ? n.getDetectionRate() : n.getAfterDetectionRate())){
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
