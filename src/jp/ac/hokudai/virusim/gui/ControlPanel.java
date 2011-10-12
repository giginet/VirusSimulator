/**
 * ControlPanel.java
 * VirusSimulator
 *
 * Created by giginet on 2011/10/12
 * 
 */
package jp.ac.hokudai.virusim.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author giginet
 *
 */
public class ControlPanel extends JPanel{
  private static final long serialVersionUID = 1L;

  public ControlPanel(){
    JButton testButton = new JButton("ぼたん");
    this.add(testButton);
  }
}
