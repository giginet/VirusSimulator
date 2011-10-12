package jp.ac.hokudai.virusim.gui;

import javax.swing.JFrame;

public class VirusSimulatorWindow extends JFrame{
  public VirusSimulatorWindow(String windowName){
    /**
     * @param windowName ウィンドウ表示名
     */
    setTitle(windowName);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 500);
  }
}
