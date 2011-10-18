/**
 * SettingContainer.java
 * VirusSimulator
 *
 * Created by giginet on 2011/10/11
 * 
 */
package jp.ac.hokudai.virusim.util;

import jp.ac.hokudai.virusim.model.GraphType;

/**
 * @author giginet
 *
 */
public class SettingContainer{
  // シングルトン
  private static SettingContainer shared = new SettingContainer();
  private SettingContainer(){};
  
  /**
   * ネットワーク中に初期生成するノードの数を指定します
   */
  private int nodeCount = 5000;
  
  /**
   * ネットワーク中に初期生成する感染状態のノードの数を指定します
   */
  private int initialVirusNodeCount = 10;
  
  /**
   * 初期状態のワクチン導入率を指定します。<br>
   * これはユーザーのセキュリティソフトのインストール率を擬似的に表したものです
   */
  private double initialVaccineRate = 0.01;
  
  /**
   * 感染したノードの発症率の最小値、最大値を指定します。<br>
   * ノード毎に初期状態として指定した範囲の中から発症率が設定されます。<br>
   * 感染時、毎時間発症するかどうかを判定します。<br>
   * この発症率はユーザーのアクティブ率を擬似的に表したものです。<br>
   * 発症率が高い方が、頻繁にコンピュータを利用することを表します
   */
  private double minCrisisRate = 0.05;
  private double maxCrisisRate = 0.2;
  
  /**
   * 発症したノードの感染発見率の最小値、最大値を指定します。<br>
   * ノード毎に初期状態として指定した範囲の中から発見率が設定されます。<br>
   * 発症時、毎時間免疫状態に移行するかどうかを判定します。<br>
   * この発見率はユーザーのセキュリティ意識を擬似的に表したものです。<br>
   * 発見率が高い方が、ウイルスの発症に気付き、対策を取りやすいことを表します。
   */
  private double minDetectionRate = 0.0;
  private double maxDetectionRate = 0.01;

  /**
   * 他のノードを感染させた後のノードの感染発見率の最小値、最大値を指定します。<br>
   * ノード毎に初期状態として指定した範囲の中から発見率が設定されます。<br>
   * 発症時、毎時間免疫状態に移行するかどうかを判定します。<br>
   * この発見率はユーザーのセキュリティ意識を擬似的に表したものです。<br>
   * 発見率が高い方が、ウイルスの発症に気付き、対策を取りやすいことを表します<br>
   * 通常は、発見率より事後発見率の方が高く設定されます。
   */
  private double minAfterDetectionRate = 0.1;
  private double maxAfterDetectionRate = 0.2;
  
  /**
   * 生成するグラフの種類を指定します。<br>
   * それぞれ、完全グラフ、ランダムグラフ、スケールフリーグラフに対応します
   */
  private GraphType graphType = GraphType.Random;
  
  /**
   * ノードと隣接する他のノードの平均次数を指定します。<br>
   * ノート毎に初期状態として設定した次数を元にポアソン分布により、ノード数を決定します<br>
   * このノード数に応じて、重複無しでノードと他のノードとのコネクションが張られます<br>
   * この隣接ノード数は電子メールを媒介とするウィルスを仮定したときに、ユーザーのメールの送信先の数を擬似的に表したものです<br>
   */
  private int neighborNodeAverage = 10;
  
  /**
   * シミュレーター全体で共通の設定を返します
   * @return the shared
   */
  public static SettingContainer getShared(){
    return shared;
  }

  /**
   * @return the nodeCount
   */
  public int getNodeCount(){
    return nodeCount;
  }

  /**
   * @param nodeCount the nodeCount to set
   */
  public void setNodeCount(int nodeCount){
    this.nodeCount = nodeCount;
  }

  /**
   * @return the initialVirusNodeCount
   */
  public int getInitialVirusNodeCount(){
    return initialVirusNodeCount;
  }

  /**
   * @param initialVirusNodeCount the initialVirusNodeCount to set
   */
  public void setInitialVirusNodeCount(int initialVirusNodeCount){
    this.initialVirusNodeCount = initialVirusNodeCount;
  }

  /**
   * @return the initialVaccineRate
   */
  public double getInitialVaccineRate(){
    return initialVaccineRate;
  }

  /**
   * @param initialVaccineRate the initialVaccineRate to set
   */
  public void setInitialVaccineRate(double initialVaccineRate){
    this.initialVaccineRate = initialVaccineRate;
  }

  /**
   * @return the minCrisisRate
   */
  public double getMinCrisisRate(){
    return minCrisisRate;
  }

  /**
   * @param minCrisisRate the minCrisisRate to set
   */
  public void setMinCrisisRate(double minCrisisRate){
    this.minCrisisRate = minCrisisRate;
  }

  /**
   * @return the maxCrisisRate
   */
  public double getMaxCrisisRate(){
    return maxCrisisRate;
  }

  /**
   * @param maxCrisisRate the maxCrisisRate to set
   */
  public void setMaxCrisisRate(double maxCrisisRate){
    this.maxCrisisRate = maxCrisisRate;
  }

  /**
   * @return the minDetectionRate
   */
  public double getMinDetectionRate(){
    return minDetectionRate;
  }

  /**
   * @param minDetectionRate the minDetectionRate to set
   */
  public void setMinDetectionRate(double minDetectionRate){
    this.minDetectionRate = minDetectionRate;
  }

  /**
   * @return the maxDetectionRate
   */
  public double getMaxDetectionRate(){
    return maxDetectionRate;
  }

  /**
   * @param maxDetectionRate the maxDetectionRate to set
   */
  public void setMaxDetectionRate(double maxDetectionRate){
    this.maxDetectionRate = maxDetectionRate;
  }

  /**
   * @return the minAfterDetectionRate
   */
  public double getMinAfterDetectionRate(){
    return minAfterDetectionRate;
  }

  /**
   * @param minAfterDetectionRate the minAfterDetectionRate to set
   */
  public void setMinAfterDetectionRate(double minAfterDetectionRate){
    this.minAfterDetectionRate = minAfterDetectionRate;
  }

  /**
   * @return the maxAfterDetectionRate
   */
  public double getMaxAfterDetectionRate(){
    return maxAfterDetectionRate;
  }

  /**
   * @param maxAfterDetectionRate the maxAfterDetectionRate to set
   */
  public void setMaxAfterDetectionRate(double maxAfterDetectionRate){
    this.maxAfterDetectionRate = maxAfterDetectionRate;
  }

  /**
   * @return the graphType
   */
  public GraphType getGraphType(){
    return graphType;
  }

  /**
   * @param graphType the graphType to set
   */
  public void setGraphType(GraphType graphType){
    this.graphType = graphType;
  }

  /**
   * @return the neighborNodeAverage
   */
  public int getNeighborNodeAverage(){
    return neighborNodeAverage;
  }

  /**
   * @param neighborNodeAverage the neighborNodeAverage to set
   */
  public void setNeighborNodeAverage(int neighborNodeAverage){
    this.neighborNodeAverage = neighborNodeAverage;
  }
  
}
