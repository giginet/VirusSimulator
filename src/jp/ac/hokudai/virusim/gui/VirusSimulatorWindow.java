package jp.ac.hokudai.virusim.gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Container;

public class VirusSimulatorWindow extends JFrame{
  public VirusSimulatorWindow(String windowName){
    /**
     * @param windowName ウィンドウ表示名
     */
    setTitle(windowName);
    Container container = getContentPane();
    ControlPanel cp = new ControlPanel();
    container.add(cp, BorderLayout.EAST);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 500);
  }
}
