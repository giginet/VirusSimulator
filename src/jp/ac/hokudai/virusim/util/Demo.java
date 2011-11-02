package jp.ac.hokudai.virusim.util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import jp.ac.hokudai.virusim.gui.GraphPanel;
import jp.ac.hokudai.virusim.model.Network;
import jp.ac.hokudai.virusim.model.NodeState;
import jp.ac.hokudai.virusim.scalefree.ScaleFreeNetwork;

public class Demo extends JFrame {
	private static final long serialVersionUID = 1L;
	private static GraphPanel panel = null;

	private static Network network;
	private static boolean flag = false;
	
	public static void main(String[] args) {
		network = new ScaleFreeNetwork(SettingContainer.getShared().getNodeCount());
		//final Network network = new Network(SettingContainer.getShared().getNodeCount());
		
		final Demo window = new Demo();
		window.setSize(600, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		
		JButton button = new JButton("start");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				if(flag) { 
					flag = false;
					button.setText("start");
				} else {
					flag = true;
					button.setText("stop");				
				}
			}
		});
		window.add(button, BorderLayout.SOUTH);
		
		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					if (panel != null) { window.remove(panel); }

					panel = new GraphPanel(network);
					window.getContentPane().add(panel, BorderLayout.CENTER);
					window.setVisible(true);
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		while (true) {
			if(flag) {
				if (network.getNodes().getNodeStates(NodeState.Infection).isEmpty()) {
					break;
				}
				network.run();
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
