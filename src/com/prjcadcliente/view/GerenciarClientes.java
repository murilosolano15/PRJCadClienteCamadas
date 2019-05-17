package com.prjcadcliente.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.prjcadcliente.dominio.Cliente;
import com.prjcadcliente.persistencia.CRUDCliente;

public class GerenciarClientes extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtIdade;
	private JTable tbClienteCadastrado;
	private Cliente cliente;
	private CRUDCliente crud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarClientes frame = new GerenciarClientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GerenciarClientes() {
		setTitle("Gerenciar Cliente");
		
		// Vamos instanciar as classes Cliente e CRUD
		cliente = new Cliente();
		crud = new CRUDCliente();
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(20, 39, 431, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome do Cliente:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(20, 14, 150, 14);
		contentPane.add(lblNome);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(20, 70, 48, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(20, 95, 431, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTelefone.setBounds(20, 126, 89, 14);
		contentPane.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(20, 151, 135, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Passar os dados do formulário para o objeto cliente 
				cliente.setNome(txtNome.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				String retorno = crud.cadastrar(cliente);
				JOptionPane.showMessageDialog(null,retorno);
				txtNome.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
				txtIdade.setText("");
				
				
				
			}
		});
		btnCadastrar.setBounds(20, 238, 101, 29);
		contentPane.add(btnCadastrar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesquisar.setBounds(131, 238, 100, 29);
		contentPane.add(btnPesquisar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = JOptionPane.showInputDialog("Digite o Id do cliente");
				
				//Passar os dados do formulário para o objeto cliente 
				cliente.setNome(txtNome.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				cliente.setId(Integer.parseInt(id));
				
				String retorno = crud.atualizar(cliente);
				
				JOptionPane.showMessageDialog(null,retorno);
				
				txtNome.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
				txtIdade.setText("");
				id="0";
				
				
				
			}
		});
		btnAtualizar.setBounds(241, 238, 100, 29);
		contentPane.add(btnAtualizar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			String id = JOptionPane.showInputDialog("Digite o Id do cliente para apagar");
			
			cliente.setId(Integer.parseInt(id));
			
			JOptionPane.showMessageDialog(null, crud.deletar(cliente));
			
			
			}
		});
		btnDeletar.setBounds(351, 238, 100, 29);
		contentPane.add(btnDeletar);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdade.setBounds(20, 182, 89, 14);
		contentPane.add(lblIdade);
		
		txtIdade = new JTextField();
		txtIdade.setBounds(20, 207, 75, 20);
		contentPane.add(txtIdade);
		txtIdade.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 278, 431, 117);
		contentPane.add(scrollPane);
		
		String[] colunas = {"Id","Nome","E-mail","Telefone","Idade"};
		
		Object[][] dados = crud.PesquisarTodos().toArray(new Object[][] {});
		
		
		//Vamos construir o modelo de dados para exibir na tabela
		DefaultTableModel modelo = new DefaultTableModel(dados,colunas);                                                                                                     
		
	
		tbClienteCadastrado = new JTable(modelo);
		scrollPane.setViewportView(tbClienteCadastrado);
	}
}
