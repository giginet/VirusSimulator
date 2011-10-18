package jp.ac.hokudai.virusim.simulator;

import jp.ac.hokudai.virusim.gui.VirusSimulatorWindow;
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
  
  /**
   * Simulatorを初期化します
   */
  public VirusSimulator(){
    SettingContainer settings = SettingContainer.getShared();
    network = new Network(settings.getNodeCount());
    while(true){
      System.out.println(log());
      if(network.getNodes().getNodeStates(NodeState.Infection).isEmpty()) break;
      network.run();
      ++time;
    }
  }
  
  /**
   * 現在の状況をロギングします
   * @return 現在の状況をdumpした文字列
   */
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
    VirusSimulatorWindow window = new VirusSimulatorWindow("VirusSimulator");
    window.setVisible(true);
  }

}