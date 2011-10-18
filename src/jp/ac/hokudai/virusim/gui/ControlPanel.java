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

import javax.swing.*;

import jp.ac.hokudai.virusim.simulator.VirusSimulator;
import jp.ac.hokudai.virusim.util.SettingContainer;

/**
 * @author giginet
 *
 */
public class ControlPanel extends JPanel{
  private static final long serialVersionUID = 1L;

  public ControlPanel(){
    final SettingContainer settings = SettingContainer.getShared();
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    final JSpinner nodeSpinner = new JSpinner(new SpinnerNumberModel(settings.getNodeCount(), 1, 100000, 1000));
    final JSpinner virusSpinner = new JSpinner(new SpinnerNumberModel(settings.getInitialVirusNodeCount(), 1, 100000, 5));
    final JSpinner vaccineSpinner = new JSpinner(new SpinnerNumberModel(settings.getInitialVaccineRate(), 0, 1, 0.005));
    
    
    JButton runButton = new JButton("Run");
    runButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        settings.setNodeCount((Integer)nodeSpinner.getValue());
        settings.setInitialVirusNodeCount((Integer)virusSpinner.getValue());
        settings.setInitialVaccineRate((Double)vaccineSpinner.getValue());
        new VirusSimulator();
      }
    });
    JButton logButton = new JButton("Log");
    logButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        settings.setNodeCount((Integer)nodeSpinner.getValue());
        settings.setInitialVirusNodeCount((Integer)virusSpinner.getValue());
        settings.setInitialVaccineRate((Double)vaccineSpinner.getValue());
        new VirusSimulator("output.txt");
      }
    });
    
    panel.add(addCaption(nodeSpinner, "ノード数"));
    panel.add(addCaption(virusSpinner, "初期ウイルス数"));
    panel.add(addCaption(vaccineSpinner, "初期ワクチン率"));
    panel.add(runButton);
    panel.add(logButton);
    this.add(panel);
  }
  
  /**
   * 渡されたコンポーネントに、良い感じでキャプションを付けて返します
   * @param component
   * @return
   */
  private JPanel addCaption(JComponent component, String caption){
    JPanel panel = new JPanel();
    JLabel label = new JLabel(caption);
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    panel.add(label);
    panel.add(component);
    return panel;
  }
}
