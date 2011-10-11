package jp.ac.hokudai.virusim.simulator;

import jp.ac.hokudai.virusim.model.Network;
import jp.ac.hokudai.virusim.model.NodeState;
import jp.ac.hokudai.virusim.util.SettingContainer;
/**
 *  VirusSimulator.java 
 *  created by giginet on 2011/10/06
 */


/**
 * @author giginet
 *
 */
public class VirusSimulator{
  
  private Network network;
  int time = 0;
  
  public VirusSimulator(){
    SettingContainer settings = SettingContainer.getShared();
    network = new Network(settings.getNodeCount());
    while(true){
      network.run();
      System.out.println(log());
      ++time;
      if(network.getNodes().getNodeStates(NodeState.Infection).isEmpty()) break;
    }
  }
  
  private String log(){
    int countNormal    = network.getNodes().getNodeStates(NodeState.Normal).size();
    int countInfection = network.getNodes().getNodeStates(NodeState.Infection).size();
    int countImmune    = network.getNodes().getNodeStates(NodeState.Immune).size();
    return String.format("%d %d %d %d", time, countNormal, countInfection, countImmune);
  }
  
  
  /**
   * @param args
   */
  public static void main(String[] args){
    new VirusSimulator();
  }

}