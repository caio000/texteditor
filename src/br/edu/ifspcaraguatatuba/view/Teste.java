package br.edu.ifspcaraguatatuba.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Teste extends JFrame {

	private static final long serialVersionUID = 7502264362257727351L;
	private JPanel contentPane;
	private JTextField txtPathFile;
	private JTextArea textArea;
	
	private String originalText;

	public Teste() {
		initComponent();
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
				
				long tamanhoArquivo = selectedFile.length();
				// TODO Verificar tamnho do arquivo e sua unidade de tamnho como Kb, Mb, Gb .....
				
				arq.close();
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, e.getMessage());
		}
		
	}
	
	
	
	public void initComponent () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		scrollPane.setBounds(10, 150, 700, 400);
		contentPane.add(scrollPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 784, 21);
		contentPane.add(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addMouseListener(new MouseAdapter() { // Opção Abrir do menu
			@Override
			public void mousePressed(MouseEvent e) {
				selecionarArquivo();
			}
		});
		mnArquivo.add(mntmAbrir);
		
		JMenuItem mntmNovo = new JMenuItem("Novo");
		mnArquivo.add(mntmNovo);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.addMouseListener(new MouseAdapter() { // Obção ajuda do menu
			@Override
			public void mousePressed(MouseEvent e) {
				JDialog ajuda = new Modal();
				ajuda.setLocationRelativeTo(contentPane);
				ajuda.setVisible(true);
			}
		});
		menuBar.add(mnAjuda);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.addMouseListener(new MouseAdapter() { // Opção sair do menu
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		menuBar.add(mnSair);
		
		JLabel lblAbsolutPaht = new JLabel("Caminho:");
		lblAbsolutPaht.setBounds(10, 72, 81, 14);
		contentPane.add(lblAbsolutPaht);
		
		txtPathFile = new JTextField();
		txtPathFile.setBounds(101, 69, 400, 20);
		txtPathFile.setEditable(false);
		contentPane.add(txtPathFile);
		txtPathFile.setColumns(10);
		
		JButton btnOpenFile = new JButton("...");
		btnOpenFile.addActionListener(new ActionListener() { // Botão abrir arquivo
			public void actionPerformed(ActionEvent arg0) {
				selecionarArquivo();
			}
		});
		btnOpenFile.setBounds(538, 68, 89, 23);
		contentPane.add(btnOpenFile);
		
		
	}
}
