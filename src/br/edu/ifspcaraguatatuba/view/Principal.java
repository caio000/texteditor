package br.edu.ifspcaraguatatuba.view;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class Principal extends JFrame {

	private static final long serialVersionUID = -4547737506163142377L;
	private JPanel contentPane;
	private JTextField txtCaminho;
	JMenuBar menuBar;
	JMenu mnArquivo;
	private JTextField txtNomeArquivo;
	private JTextField txtCaminhoArquivo;
	private JTextField txtQtdPalavras;
	//private JEditorPane textArea;
	private JTextArea textArea;
	private JTextField txtTamanhoAquivo;
	
	private String originalText;

	public Principal() {
		initComponent();
	}
	
	
	private boolean checkText (String text) {
		return originalText.equals(text);
	}
	
	private void selecionarArquivo () {
		
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fc.showOpenDialog(contentPane);
		
		try {
			// verifica se o usuario selecionou um arquivo
			if (result == JFileChooser.APPROVE_OPTION) {
				// selecionou um arquivo
				File selectedFile = fc.getSelectedFile();
				if (!selectedFile.getName().endsWith(".txt")) {
					throw new Exception("Selecione um arquivo com a extensão .TXT");
				}
				
				
				FileReader arq = new FileReader(selectedFile);
				BufferedReader br = new BufferedReader(arq);
				
				String linha = br.readLine(); // lê a primeira linha do arquivo
				String temp = "";
				while (linha != null) {
					temp +=  linha + "\n";
					linha = br.readLine();
				}
				
				originalText = temp;
				textArea.setText(temp);
				txtCaminho.setText(selectedFile.getAbsolutePath());
				txtCaminhoArquivo.setText(selectedFile.getAbsolutePath());
				txtNomeArquivo.setText(selectedFile.getName());
				
				long tamanhoArquivo = selectedFile.length();
				// TODO Verificar tamnho do arquivo e sua unidade de tamnho como Kb, Mb, Gb .....
				
				arq.close();
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, e.getMessage());
		}
		
	}
	
	private void initComponent () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		mnArquivo.add(mntmNovo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { // click da opção Arquivo -> abrir
				
				selecionarArquivo();
				
			}
		});
		mnArquivo.add(mntmAbrir);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.setEnabled(false);
		mnArquivo.add(mntmSalvar);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialog m = new Modal();
				m.setVisible(true);
			}
		});
		menuBar.add(mnAjuda);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * TODO verificar se houve alteração no texto
				 * Caso positivo exibir um alert informando o usuário que as modificações serão perdidas.
				 */
				
				int option = JOptionPane.showConfirmDialog(contentPane, "Você não salvou as alterações do arquivo, caso feche o programa as alterações serão perdidas.");
				System.out.println(option);
			}
		});
		menuBar.add(mnSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtCaminho = new JTextField();
		txtCaminho.setEditable(false);
		txtCaminho.setColumns(10);
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarArquivo();
			}
		});
		
		JLabel lblArquivo = new JLabel("Arquivo");
		lblArquivo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				// habilitar o botão salvar
				mntmSalvar.setEnabled(true);
			}
		});
		
		JLabel lblQuantidadeDePalavras = new JLabel("Quantidade de palavras");
		lblQuantidadeDePalavras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblCaminho = new JLabel("Caminho");
		lblCaminho.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNomeDoArquivo = new JLabel("Nome do arquivo");
		lblNomeDoArquivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtNomeArquivo = new JTextField();
		txtNomeArquivo.setColumns(10);
		
		txtCaminhoArquivo = new JTextField();
		txtCaminhoArquivo.setColumns(10);
		
		txtQtdPalavras = new JTextField();
		txtQtdPalavras.setColumns(10);
		
		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtTamanhoAquivo = new JTextField();
		txtTamanhoAquivo.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(62)
							.addComponent(lblArquivo)
							.addGap(18)
							.addComponent(txtCaminho, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(10, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblQuantidadeDePalavras))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(50)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblTamanho)
												.addComponent(lblCaminho))))
									.addGap(18))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNomeDoArquivo)
									.addGap(27)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtTamanhoAquivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtQtdPalavras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNomeArquivo, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
								.addComponent(txtCaminhoArquivo)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 738, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtCaminho, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
							.addComponent(lblArquivo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNomeArquivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomeDoArquivo))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTamanho)
						.addComponent(txtTamanhoAquivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCaminho)
						.addComponent(txtCaminhoArquivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantidadeDePalavras)
						.addComponent(txtQtdPalavras, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
