/**
 * SettingContainer.java
 * VirusSimulator
 *
 * Created by giginet on 2011/10/11
 * 
 */
package jp.ac.hokudai.virusim.util;

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
  private final int NODE_COUNT = 20000;
  
  /**
   * ネットワーク中に初期生成する感染状態のノードの数を指定します
   */
  private final int INITIAL_VIRUS_NODE_COUNT = 10;
  
  /**
   * 初期状態のワクチン導入率を指定します。<br>
   * これはユーザーのセキュリティソフトのインストール率を擬似的に表したものです
   */
  private final double INITIAL_VACCINE_RATE = 0.01;
  
  /**
   * 感染したノードの発症率の最小値、最大値を指定します。<br>
   * ノード毎に初期状態として指定した範囲の中から発症率が設定されます。<br>
   * 感染時、毎時間発症するかどうかを判定します。<br>
   * この発症率はユーザーのアクティブ率を擬似的に表したものです。<br>
   * 発症率が高い方が、頻繁にコンピュータを利用することを表します
   */
  private final double MIN_CRISIS_RATE = 0.05;
  private final double MAX_CRISIS_RATE = 0.2;
  
  /**
   * 発症したノードの感染発見率の最小値、最大値を指定します。<br>
   * ノード毎に初期状態として指定した範囲の中から発見率が設定されます。<br>
   * 発症時、毎時間免疫状態に移行するかどうかを判定します。<br>
   * この発見率はユーザーのセキュリティ意識を擬似的に表したものです。<br>
   * 発見率が高い方が、ウイルスの発症に気付き、対策を取りやすいことを表します。
   */
  private final double MIN_DETECTION_RATE = 0.0;
  private final double MAX_DETECTION_RATE = 0.01;

  /**
   * 他のノードを感染させた後のノードの感染発見率の最小値、最大値を指定します。<br>
   * ノード毎に初期状態として指定した範囲の中から発見率が設定されます。<br>
   * 発症時、毎時間免疫状態に移行するかどうかを判定します。<br>
   * この発見率はユーザーのセキュリティ意識を擬似的に表したものです。<br>
   * 発見率が高い方が、ウイルスの発症に気付き、対策を取りやすいことを表します<br>
   * 通常は、発見率より事後発見率の方が高く設定されます。
   */
  private final double MIN_AFTER_DETECTION_RATE = 0.1;
  private final double MAX_AFTER_DETECTION_RATE = 0.2;
  
  /**
   * ノードと隣接する他のノードの数を指定します。<br>
   * ノート毎に初期状態として設定した範囲の中から隣接ノード数が設定されます。<br>
   * このノード数に応じて、重複無しでノードと他のノードとのコネクションが張られます<br>
   * この隣接ノード数は電子メールを媒介とするウィルスを仮定したときに、ユーザーのメールの送信先の数を擬似的に表したものです<br>
   */
  private final int MIN_NEIGHBOR_NODE_COUNT = 1;
  private final int MAX_NEIGHBOR_NODE_COUNT = 30;
  
  /**
   * @return the shared
   */
  public static SettingContainer getShared(){
    return shared;
  }
  /**
   * @return the nODE_COUNT
   */
  public int getNodeCount(){
    return NODE_COUNT;
  }
  /**
   * @return the vIRUS_NODE_COUNT
   */
  public int getInitialVirusNodeCount(){
    return INITIAL_VIRUS_NODE_COUNT;
  }
  /**
   * @return the iNITIAL_VACCINE_RATE
   */
  public double getInitialVaccineRate(){
    return INITIAL_VACCINE_RATE;
  }
  /**
   * @return the mIN_CRISIS_RATE
   */
  public double getMinCrisisRate(){
    return MIN_CRISIS_RATE;
  }
  /**
   * @return the mAX_CRISIS_RATE
   */
  public double getMaxCrisisRate(){
    return MAX_CRISIS_RATE;
  }
  /**
   * @return the mIN_DETECTION_RATE
   */
  public double getMinDetectionRate(){
    return MIN_DETECTION_RATE;
  }
  /**
   * @return the mAX_DETECTION_RATE
   */
  public double getMaxDetectionRate(){
    return MAX_DETECTION_RATE;
  }
  
  /**
   * @return the mIN_AFTER_DETECTION_RATE
   */
  public double getMinAfterDetectionRate(){
    return MIN_AFTER_DETECTION_RATE;
  }
  /**
   * @return the mAX_AFTER_DETECTION_RATE
   */
  public double getMaxAfterDetectionRate(){
    return MAX_AFTER_DETECTION_RATE;
  }
  /**
   * @return the mIN_NEIGHBOR_NODE_COUNT
   */
  public int getMinNeighborNodeCount(){
    return MIN_NEIGHBOR_NODE_COUNT;
  }
  /**
   * @return the mAX_NEIGHBOR_NODE_COUNT
   */
  public int getMaxNeightborNodeCount(){
    return MAX_NEIGHBOR_NODE_COUNT;
  }
  
}
