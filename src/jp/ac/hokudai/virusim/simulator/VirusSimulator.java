package jp.ac.hokudai.virusim.simulator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
  //private static GraphPanel panel;
  //private static Chart panel;
  int time = 0;
  
  private double[][] data;
  
  /**
   * シミュレーションを実行します
   */
  public VirusSimulator(){
    SettingContainer settings = SettingContainer.getShared();
    
    data = new double[3][settings.getNodeCount()];
    
    if(settings.getGraphType() != GraphType.ScaleFree){
      network = new Network(settings.getNodeCount());
    }else{
      network = new ScaleFreeNetwork(settings.getNodeCount());
    }
    while(true){
      System.out.println(log());
      addData();
      if(network.getNodes().getNodeStates(NodeState.Infection).isEmpty()) break;
      network.run();
      ++time;
    }
    System.out.println("----------------------------");
    
   
/*  グラフ表示 実用的にゴミ。  
 *　折れ線グラフ表示書きかけ。
    if(panel != null){ window.remove(panel.getChartPanel()); }
    
    //panel = new GraphPanel(network); 
    panel = new Chart(data);
    window.getContentPane().add(panel.getChartPanel(), BorderLayout.WEST);
    window.setVisible(true);
*/
  }
  
  /*
   * 棒グラフ表示用のデータ取り
   * 書きかけ
   */
  public void addData(){
    data[0][time] = network.getNodes().getNodeStates(NodeState.Normal).size();
    data[1][time] = network.getNodes().getNodeStates(NodeState.Infection).size();
    data[2][time] = network.getNodes().getNodeStates(NodeState.Immune).size();
  }
  
  /**
   * シミュレーションを実行して、指定されたファイルに書き出します
   * @param filename ファイル名
   */
  public VirusSimulator(String filename){
    SettingContainer settings = SettingContainer.getShared();
    
    if(settings.getGraphType() != GraphType.ScaleFree){
      network = new Network(settings.getNodeCount());
    }else{
      network = new ScaleFreeNetwork(settings.getNodeCount());
    }
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
    int countNormal     		= network.getNodes().getNodeStates(NodeState.Normal).size();
    int countInfection  		= network.getNodes().getNodeStates(NodeState.Infection).size();
    int countImmune     		= network.getNodes().getNodeStates(NodeState.Immune).size();
    int countInitImmute 		= network.getInitImmute();
    int countInfluencedToImmute = network.getCountInfluencedToImmute();
    int countCrisisToImmute 	= network.getCountCrisisToImmute();
    
    return String.format("%d %d %d %d %d %d %d",
		time,
		countNormal,
		countInfection,
		countImmune,
		countInitImmute,
		countInfluencedToImmute, 
		countCrisisToImmute);
  }
  
  /**
   * @param args
   */
  public static void main(String[] args){
    window = new VirusSimulatorWindow("VirusSimulator");
    window.setVisible(true);
  }

}