package com.prjcadcliente.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prjcadcliente.dominio.Cliente;

/**
 * <b>CRUDCliente</b><br>
 * Essa classe permite manipular as informa��es do cliente. Aqui voc� 
 * encontrar� os seguintes comando:
 * <ul>
 * <li>Cadastro</li>
 * <li>Pesquisar por nome e por id</li>
 * <li>Atualizar</li>
 * <li>Deletar</li>
 * </ul>
 * @author murilo.soliva
 *
 */
public class CRUDCliente {
	
	
	//Declara��o das inst�ncias de comunica��o com o banco de dados
	
	private Connection con = null; // Estabelecer a comunica��o com o banco de dados 
	private ResultSet rs = null; // 
	private PreparedStatement pst = null;
	
	
	public String cadastrar(Cliente cliente) {
		
		String msg = "";
		
		//Cria��o dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.driver").newInstance(); // pega a pasta do driver de comunica��o
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root",""); // entrar na porta, para realizar a conexao remota 
			
			String consulta = "INSERT INTO tbcliente(nome,email,telefone,idade)values(?,?,?,?)"; // pegar da tabela as coisas e trazer o resultado
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getTelefone());
			pst.setInt(4, cliente.getIdade());
			
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Cadastro realizado com sucesso!";
			else
				msg = "N�o foi possivel cadastrar!";
				
		}
		//comandos de sql
		catch(SQLException ex){
			msg = "erro ao tentar cadastrar:"+ex.getMessage();//mostrar qual � o erro 
		}
		
		//erro geral
		catch(Exception e) {
			msg = "Erro inesperado: "+e.getMessage();
		}
		
		//close critico 
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}
		
		
		return msg;
	}
	
	public String atualizar(Cliente cliente) {
String msg = "";
		
		//Cria��o dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.driver").newInstance(); // pega a pasta do driver de comunica��o
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root",""); // entrar na porta, para realizar a conexao remota 
			
			String consulta = "UPDATE tbcliente SET nome=?,email=?,telefone=?,idade=? WHERE id=?"; // pegar da tabela as coisas e trazer o resultado
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getTelefone());
			pst.setInt(4, cliente.getIdade());
			pst.setInt(5, cliente.getId());
			
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Atualizado com sucesso!";
			else
				msg = "N�o foi possivel atualizar!";
				
		}
		//comandos de sql
		catch(SQLException ex){
			msg = "erro ao tentar atualizar:"+ex.getMessage();//mostrar qual � o erro 
		}
		
		//erro geral
		catch(Exception e) {
			msg = "Erro inesperado: "+e.getMessage();
		}
		
		//close critico 
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}
		
		
		return msg;
	}
	
	public String deletar(Cliente cliente) {
String msg = "";
		
		//Cria��o dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.driver").newInstance(); // pega a pasta do driver de comunica��o
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root",""); // entrar na porta, para realizar a conexao remota 
			
			String consulta = "DELETE FROM tbcliente WHERE id=?"; // pegar da tabela as coisas e trazer o resultado
			
			pst = con.prepareStatement(consulta);
			
			pst.setInt(1, cliente.getId());
			
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Deletado com sucesso!";
			else
				msg = "N�o foi possivel Deletar!";
				
		}
		//comandos de sql
		catch(SQLException ex){
			msg = "erro ao tentar Deletar:"+ex.getMessage();//mostrar qual � o erro 
		}
		
		//erro geral
		catch(Exception e) {
			msg = "Erro inesperado: "+e.getMessage();
		}
		
		//close critico 
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}
		
		
		return msg;
	}
	
	public List<Cliente> PesquisarPorNome(String nome) {
		
		List<Cliente> lista = new ArrayList<Cliente>(); //array � um do lado do outro com"," lista � um embaixo do outro
		
		try {
			//carregar o drive de comunica��o com o banco de dados 
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//Chamar o gerenciado de driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root","");
			
			//Vamos criar a consulta para selecionar os cliente por nome 
			String consulta = "select * from tbcliente where nome=?";
			
			pst = con.prepareStatement(consulta);			
			
			pst.setString(1, nome);

			//Vamos executar a consulta e guardar o resultado na vari�vel rs 
			rs = pst.executeQuery();
			
			/*
			 * vamos pegar um cliente por vez que esta no rs e adicion�-lo a lista de clientes para, ent�o retorn�-la
			 */
			while(rs.next()) {
				lista.add(new Cliente(
						rs.getInt(0),
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4)
						));
				
			}//Fim do while
			
		}//fim do try
		
		catch(SQLException ex) {
		ex.printStackTrace();	
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		finally {
			try {con.close();} catch(Exception e) {e.printStackTrace();}
		}
		
		return lista;
	}
	
	public Cliente PesquisarPorId(int id) {
		return null;
	}
	
	public List<Cliente> PesquisarTodos() {
		
		List<Cliente> lista = new ArrayList<Cliente>(); //array � um do lado do outro com"," lista � um embaixo do outro
		
		try {
			//carregar o drive de comunica��o com o banco de dados 
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//Chamar o gerenciado de driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root","");
			
			//Vamos criar a consulta para selecionar os cliente por nome 
			String consulta = "select * from tbcliente";
			
			pst = con.prepareStatement(consulta);			
			

			//Vamos executar a consulta e guardar o resultado na vari�vel rs 
			rs = pst.executeQuery();
			
			/*
			 * vamos pegar um cliente por vez que esta no rs e adicion�-lo a lista de clientes para, ent�o retorn�-la
			 */
			while(rs.next()) {
				lista.add(new Cliente(
						rs.getInt(0),
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4)
						));
				
			}//Fim do while
			
		}//fim do try
		
		catch(SQLException ex) {
		ex.printStackTrace();	
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		finally {
			try {con.close();} catch(Exception e) {e.printStackTrace();}
		}
		
		return lista;
	}
	
	
	
	

}
