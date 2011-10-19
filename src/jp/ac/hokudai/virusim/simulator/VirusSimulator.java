package jp.ac.hokudai.virusim.simulator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import jp.ac.hokudai.virusim.gui.GraphPanel;
import jp.ac.hokudai.virusim.gui.VirusSimulatorWindow;
import jp.ac.hokudai.virusim.model.GraphType;
import jp.ac.hokudai.virusim.model.Network;
import jp.ac.hokudai.virusim.model.NodeState;
import jp.ac.hokudai.virusim.scalefree.ScaleFreeNetwork;
import jp.ac.hokudai.virusim.util.SettingContainer;


/**
 * @author giginet
 *
 */
public class VirusSimulator{
  
  private Network network;
  private static VirusSimulatorWindow window;
  private static GraphPanel panel;
  int time = 0;
  
  /**
   * シミュレーションを実行します
   */
  public VirusSimulator(){
    SettingContainer settings = SettingContainer.getShared();
    
    if(settings.getGraphType() != GraphType.ScaleFree){
      network = new Network(settings.getNodeCount());
    }else{
      network = new ScaleFreeNetwork(settings.getNodeCount());
    }
    while(true){
      System.out.println(log());
      if(network.getNodes().getNodeStates(NodeState.Infection).isEmpty()) break;
      network.run();
      ++time;
    }
    System.out.println("----------------------------");
    
/*  グラフ表示 実用的にゴミ。  
    if(panel != null){ window.remove(panel); }
    
    panel = new GraphPanel(network); 
    window.getContentPane().add(panel, BorderLayout.WEST);
    window.setVisible(true);
*/
  }
  
  /**
   * シミュレーションを実行して、指定されたファイルに書き出します
   * @param filename ファイル名
   */
  public VirusSimulator(String filename){
    SettingContainer settings = SettingContainer.getShared();
    network = new Network(settings.getNodeCount());
    try{
      FileWriter fw = new FileWriter(filename);
      
      BufferedWriter bw = new BufferedWriter(fw);
      while(true){
        bw.write(log() + "\n");
        if(network.getNodes().getNodeStates(NodeState.Infection).isEmpty()) break;
        network.run();
        ++time;
      }
      bw.close();
    }catch(IOException e){
      System.out.println(e);
    }
    System.out.println("----------------------------");
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
    window = new VirusSimulatorWindow("VirusSimulator");
    window.setVisible(true);
  }

}