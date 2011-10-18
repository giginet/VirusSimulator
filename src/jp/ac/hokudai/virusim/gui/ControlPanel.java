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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jp.ac.hokudai.virusim.model.GraphType;
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

    /* 実装ヒドいけどなんとかならないですかね */
    final JSpinner nodeSpinner = new JSpinner(new SpinnerNumberModel(settings.getNodeCount(), 1, 100000, 1000));
    final JSpinner virusSpinner = new JSpinner(new SpinnerNumberModel(settings.getInitialVirusNodeCount(), 1, 100000, 5));
    final JSpinner vaccineSpinner = new JSpinner(new SpinnerNumberModel(settings.getInitialVaccineRate(), 0, 1, 0.005));
    final JSpinner minCrisisSpinner = new JSpinner(new SpinnerNumberModel(settings.getMinCrisisRate(), 0, 1, 0.005));
    final JSpinner maxCrisisSpinner = new JSpinner(new SpinnerNumberModel(settings.getMaxCrisisRate(), 0, 1, 0.005));
    final JSpinner minDetectionSpinner = new JSpinner(new SpinnerNumberModel(settings.getMinDetectionRate(), 0, 1, 0.005));
    final JSpinner maxDetectionSpinner = new JSpinner(new SpinnerNumberModel(settings.getMaxDetectionRate(), 0, 1, 0.005));
    final JSpinner minAfterDetectionSpinner = new JSpinner(new SpinnerNumberModel(settings.getMinAfterDetectionRate(), 0, 1, 0.005));
    final JSpinner maxAfterDetectionSpinner = new JSpinner(new SpinnerNumberModel(settings.getMaxAfterDetectionRate(), 0, 1, 0.005));    
    final JSpinner neighborAverageSpinner = new JSpinner(new SpinnerNumberModel(settings.getNeighborNodeAverage(), 0, settings.getNodeCount(), 1));
    final JList graphTypeList = new JList(GraphType.values());
    final JButton runButton = new JButton("Run");
    final JButton logButton = new JButton("Log");
    
    nodeSpinner.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent event){
        settings.setNodeCount((Integer)nodeSpinner.getValue());
      }
    });

    virusSpinner.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent event){
        ((SpinnerNumberModel)virusSpinner.getModel()).setMaximum((Integer)nodeSpinner.getValue());
        settings.setInitialVirusNodeCount((Integer)virusSpinner.getValue());
      }
    });
    
    vaccineSpinner.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent event){
        settings.setInitialVaccineRate((Double)vaccineSpinner.getValue());
      }
    });
    
    minCrisisSpinner.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent event){
        ((SpinnerNumberModel)minCrisisSpinner.getModel()).setMaximum((Double)maxCrisisSpinner.getValue());
        settings.setMinCrisisRate((Double)minCrisisSpinner.getValue());
      }
    });
    
    maxCrisisSpinner.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent event){
        ((SpinnerNumberModel)maxCrisisSpinner.getModel()).setMinimum((Double)minCrisisSpinner.getValue());
        settings.setMaxCrisisRate((Double)maxCrisisSpinner.getValue());
      }
    });
    
    minDetectionSpinner.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent event){
        ((SpinnerNumberModel)minDetectionSpinner.getModel()).setMaximum((Double)maxDetectionSpinner.getValue());
        settings.setMinDetectionRate((Double)minDetectionSpinner.getValue());
      }
    });
    
    maxDetectionSpinner.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent event){
        ((SpinnerNumberModel)maxDetectionSpinner.getModel()).setMinimum((Double)minDetectionSpinner.getValue());
        settings.setMaxDetectionRate((Double)maxDetectionSpinner.getValue());
      }
    });
    
    minAfterDetectionSpinner.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent event){
        ((SpinnerNumberModel)minAfterDetectionSpinner.getModel()).setMaximum((Double)maxAfterDetectionSpinner.getValue());
        settings.setMinAfterDetectionRate((Double)minAfterDetectionSpinner.getValue());
      }
    });
    
    maxAfterDetectionSpinner.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent event){
        ((SpinnerNumberModel)maxAfterDetectionSpinner.getModel()).setMinimum((Double)minAfterDetectionSpinner.getValue());
        settings.setMaxAfterDetectionRate((Double)maxAfterDetectionSpinner.getValue());
      }
    });
    
    neighborAverageSpinner.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent event){
        ((SpinnerNumberModel)neighborAverageSpinner.getModel()).setMaximum((Integer)nodeSpinner.getValue());
        settings.setNeighborNodeAverage((Integer)neighborAverageSpinner.getValue());
      }
    });
    
    graphTypeList.addListSelectionListener(new ListSelectionListener(){
      public void valueChanged(ListSelectionEvent event){
        settings.setGraphType((GraphType)graphTypeList.getSelectedValue());
      }
    });
    
    runButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        new VirusSimulator();
      }
    });
    
    logButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        new VirusSimulator("output.txt");
      }
    });
    
    panel.add(addCaption(nodeSpinner, "ノード数"));
    panel.add(addCaption(virusSpinner, "初期ウイルス数"));
    panel.add(addCaption(vaccineSpinner, "初期ワクチン率"));
    panel.add(addCaption(minCrisisSpinner, "最低発症率"));
    panel.add(addCaption(maxCrisisSpinner, "最高発症率"));
    panel.add(addCaption(minDetectionSpinner, "最低発見率"));
    panel.add(addCaption(maxDetectionSpinner, "最高発見率"));
    panel.add(addCaption(minAfterDetectionSpinner, "最低事後発見率"));
    panel.add(addCaption(maxAfterDetectionSpinner, "最高事後発見率"));
    panel.add(addCaption(neighborAverageSpinner, "平均隣接ノード数"));
    panel.add(addCaption(graphTypeList, "グラフタイプ"));
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
