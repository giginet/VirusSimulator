/**
 * ControlPanel.java
 * VirusSimulator
 *
 * Created by giginet on 2011/10/12
 * 
 */
package jp.ac.hokudai.virusim.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import jp.ac.hokudai.virusim.simulator.VirusSimulator;

/**
 * @author giginet
 *
 */
public class ControlPanel extends JPanel{
  private static final long serialVersionUID = 1L;

  public ControlPanel(){
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    JButton runButton = new JButton("Run");
    runButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        new VirusSimulator();
      }
    });
    JButton logButton = new JButton("Log");
    
    panel.add(runButton);
    panel.add(logButton);
    this.add(panel);
  }
}
