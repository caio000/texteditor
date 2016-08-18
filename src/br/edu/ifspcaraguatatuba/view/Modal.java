package br.edu.ifspcaraguatatuba.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Modal extends JDialog {

	private static final long serialVersionUID = -3224581918658933626L;
	private final JPanel contentPanel = new JPanel();

	public Modal() {
		
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblAjuda = new JLabel("Ajuda");
		lblAjuda.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAjuda.setForeground(Color.BLUE);
		JLabel lblNewLabel = new JLabel("FODA SE!!!!!");
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 25));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(232, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(146))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(183)
					.addComponent(lblAjuda)
					.addContainerGap(206, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAjuda)
					.addGap(81)
					.addComponent(lblNewLabel)
					.addContainerGap(86, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnFechar = new JButton("Fechar");
				btnFechar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnFechar.setActionCommand("OK");
				buttonPane.add(btnFechar);
				getRootPane().setDefaultButton(btnFechar);
			}
		}
	}

}
