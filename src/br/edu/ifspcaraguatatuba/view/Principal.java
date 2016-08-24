package br.edu.ifspcaraguatatuba.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class Teste extends JFrame {

	private static final long serialVersionUID = 7502264362257727351L;
	private JPanel contentPane;
	private JTextField txtPathFile;
	private JTextArea textArea;
	
	private String originalText = " ";
	private JTextField txtFilename;
	
	private JMenuItem mntmSalvar;
	private JTextField txtPath;
	private JTextField txtFileSize;
	
	private JLabel lblQtdPalavras;

	public Teste() {
		setTitle("Editor de texto");
		initComponent();
	}
	
	
	
	private void checkFile () {
		String text = textArea.getText();
		 
		 if (!originalText.equals(text)) {
			 String message = "Você fez modificações no arquivo deseja salvar o arquivo?";
			 int option = JOptionPane.showConfirmDialog(contentPane, message);
			 
			 if (option == 0)
				 salvarArquivo();
		 }
	}
	
	private void reset () {
		txtFilename.setText("");
		txtPathFile.setText("");
		textArea.setText("");
	}
	
	private String qtdPalavras (String text) {
		String qtd = null;
		
		String[] palavras = text.split(" "); // Gera um array de palavras
		qtd = Integer.toString(palavras.length) + " palavra(s)";
		
		return qtd;
	}
	
	private void salvarArquivo () {
		
		String texto = textArea.getText();
		if (!originalText.equals(texto)) {
			try {
				FileWriter fw = new FileWriter(txtPathFile.getText());
				PrintWriter writer = new PrintWriter(fw);
				
				writer.println(texto);
				
				writer.close();
				
				mntmSalvar.setEnabled(false);
				originalText = textArea.getText();
				JOptionPane.showMessageDialog(contentPane, "Arquivo foi salvo com sucesso!");
			}catch (Exception e) {
				JOptionPane.showMessageDialog(contentPane, e.getMessage());
			}
		}
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
				txtPathFile.setText(selectedFile.getAbsolutePath());
				txtFilename.setText(selectedFile.getName());
				txtPath.setText(selectedFile.getAbsolutePath());
				double size = selectedFile.length() / 1000;
				txtFileSize.setText(Double.toString(size));
				
				lblQtdPalavras.setText(qtdPalavras(temp));
				// TODO Verificar tamnho do arquivo e sua unidade de tamnho como Kb, Mb, Gb .....
				
				arq.close();
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, e.getMessage());
		}
		
	}
	
	
	
	public void initComponent () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//-------------------------------------------------MENU----------------------------------------------------------
		
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
		mntmNovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				checkFile();
				reset();
			}
		});
		mnArquivo.add(mntmNovo);
		
		mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addMouseListener(new MouseAdapter() { // Opção Salvar do menu
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				if (txtPathFile.getText().isEmpty()) {
					System.out.println("Esta vazio");
					
					JFileChooser fc = new JFileChooser();
					
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					
					int result = fc.showSaveDialog(contentPane);
					
					if (result == JFileChooser.APPROVE_OPTION) {
						String path = fc.getSelectedFile().getAbsolutePath() + "\\Teste.txt";
						System.out.println(path);
						
						try {
							FileWriter fw = new FileWriter(path);
							PrintWriter writer = new PrintWriter(fw);
							
							writer.print(textArea.getText());
							
							writer.close();
							
							originalText = textArea.getText();
							JOptionPane.showMessageDialog(contentPane, "Arquivo salvo com sucesso!");
							
							reset();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else
					salvarArquivo();
			}
		});
		mntmSalvar.setEnabled(false);
		mnArquivo.add(mntmSalvar);
		
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
				checkFile(); 
				dispose();
			}
		});
		menuBar.add(mnSair);
		
		//-------------------------------------------------MENU----------------------------------------------------------
		
		textArea = new JTextArea(" ");
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				mntmSalvar.setEnabled(true); // ativa o botão Salvar.
			}
		});
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		scrollPane.setBounds(10, 100, 700, 300);
		contentPane.add(scrollPane);
		
		JLabel lblAbsolutPaht = new JLabel("Caminho:");
		lblAbsolutPaht.setBounds(10, 72, 81, 14);
		contentPane.add(lblAbsolutPaht);
		
		txtPathFile = new JTextField();
		txtPathFile.setBounds(65, 69, 546, 20);
		txtPathFile.setEditable(false);
		contentPane.add(txtPathFile);
		txtPathFile.setColumns(10);
		
		JButton btnOpenFile = new JButton("...");
		btnOpenFile.addActionListener(new ActionListener() { // Botão abrir arquivo
			public void actionPerformed(ActionEvent arg0) {
				selecionarArquivo();
			}
		});
		btnOpenFile.setBounds(621, 68, 89, 23);
		contentPane.add(btnOpenFile);
		
		JLabel lblNomeDoArquivo = new JLabel("Nome do arquivo:");
		lblNomeDoArquivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDoArquivo.setBounds(10, 420, 131, 21);
		contentPane.add(lblNomeDoArquivo);
		
		txtFilename = new JTextField();
		txtFilename.setBounds(214, 422, 496, 20);
		contentPane.add(txtFilename);
		txtFilename.setColumns(10);
		
		txtPath = new JTextField();
		txtPath.setBounds(214, 453, 496, 20);
		contentPane.add(txtPath);
		txtPath.setColumns(10);
		
		JLabel lblCaminho = new JLabel("Caminho:");
		lblCaminho.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCaminho.setBounds(10, 458, 111, 14);
		contentPane.add(lblCaminho);
		
		txtFileSize = new JTextField();
		txtFileSize.setEditable(false);
		txtFileSize.setBounds(214, 484, 73, 20);
		contentPane.add(txtFileSize);
		txtFileSize.setColumns(10);
		
		JLabel lblFileSize = new JLabel("Tamanho do arquivo:");
		lblFileSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFileSize.setBounds(10, 486, 142, 14);
		contentPane.add(lblFileSize);
		
		JLabel lblSizeFile = new JLabel("");
		lblSizeFile.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSizeFile.setBounds(294, 490, 46, 14);
		contentPane.add(lblSizeFile);
		
		lblQtdPalavras = new JLabel("");
		lblQtdPalavras.setBounds(326, 536, 246, 14);
		contentPane.add(lblQtdPalavras);
		
		
	}
}
