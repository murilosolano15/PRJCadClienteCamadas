package com.prjcadcliente.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.prjcadcliente.dominio.Cliente;

/**
 * <b>CRUDCliente</b><br>
 * Essa classe permite manipular as informações do cliente. Aqui você 
 * encontrará os seguintes comando:
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
	
	
	//Declaração das instâncias de comunicação com o banco de dados
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	
	public String cadastrar(Cliente cliente) {
		
		String msg = "";
		
		//Criação dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.driver").newInstance(); // pega a pasta do driver de comunicação
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
				msg = "Não foi possivel cadastrar!";
				
		}
		//comandos de sql
		catch(SQLException ex){
			msg = "erro ao tentar cadastrar:"+ex.getMessage();//mostrar qual é o erro 
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
		
		//Criação dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.driver").newInstance(); // pega a pasta do driver de comunicação
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
				msg = "Não foi possivel atualizar!";
				
		}
		//comandos de sql
		catch(SQLException ex){
			msg = "erro ao tentar atualizar:"+ex.getMessage();//mostrar qual é o erro 
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
		return null;
	}
	
	public List<Cliente> PesquisarPorNome(String nome) {
		return null;
	}
	
	public Cliente PesquisarPorId(int id) {
		return null;
	}
	
	public List<Cliente> PesquisarTodos() {
		return null;
	}
	
	
	
	

}
