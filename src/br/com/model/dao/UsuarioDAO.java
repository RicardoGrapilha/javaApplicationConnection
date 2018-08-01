package br.com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.model.bean.Usuario;
import br.com.model.factory.ConnectionFactory;

public class UsuarioDAO {

		public void create(Usuario usuario) {		
				
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = null;
			
			try {	
				String sql = "INSERT INTO usuario (nome, sobre_nome) VALUES(? , ?)";
				stmt = con.prepareStatement(sql);
				
				//stmt.setLong(1, usuario.getId());
				stmt.setString(1, usuario.getNome());
				stmt.setString(2, usuario.getSobre_nome());
			
				stmt.executeUpdate();
				
				System.out.println("Salvo com sucesso usuario: "+usuario.getNome()+" "
																+usuario.getSobre_nome());
			} catch (SQLException e) {
				System.out.println("Erro ao salvar usuario: "+usuario.getNome()+" "
						+usuario.getSobre_nome()+" erro: "+e);
			}finally {
				ConnectionFactory.closeConnection(con, stmt);
			}

		}
		public void update(Usuario usuario) {		
			
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = null;
			
			try {	
				String sql = "UPDATE usuario set nome = ?, sobre_nome = ? WHERE id = ?";
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, usuario.getNome());
				stmt.setString(2, usuario.getSobre_nome());
				stmt.setLong(3, usuario.getId());
			
				stmt.executeUpdate();
				
				System.out.println("Salvo com sucesso usuario: "+usuario.getNome()+" "
																+usuario.getSobre_nome());
			} catch (SQLException e) {
				System.out.println("Erro ao salvar usuario: "+usuario.getNome()+" "
						+usuario.getSobre_nome()+" erro: "+e);
			}finally {
				ConnectionFactory.closeConnection(con, stmt);
			}

		}
		public void delete(Usuario usuario) {		
			
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = null;
			
			try {	
				String sql = "DELETE FROM usuario WHERE id = ?";
				stmt = con.prepareStatement(sql);
				
				stmt.setLong(1, usuario.getId());
			
				stmt.executeUpdate();
				
				System.out.println("Deletado com sucesso usuario com id: "+usuario.getId());
			} catch (SQLException e) {
				System.out.println("Erro ao salvar usuario: "+usuario.getNome()+" "
						+usuario.getSobre_nome()+" erro: "+e);
			}finally {
				ConnectionFactory.closeConnection(con, stmt);
			}

		}
		public Usuario getById(int id) {		
			
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Usuario usuario = new Usuario();
			try {	
				String sql = "SELECT * FROM usuario WHERE id = ? ";
				stmt = con.prepareStatement(sql);	
				
				stmt.setLong(1, id);
				
				rs = stmt.executeQuery(); 
				
				if (rs.next()) {
					
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setSobre_nome(rs.getString("sobre_nome"));
					
				}				
				System.out.println("Recuperado com sucesso usuarios");
			} catch (SQLException e) {
				System.out.println("Erro ao buscar usuarios, erro: "+e);
			}finally {
				ConnectionFactory.closeConnection(con, stmt);
			}

			return usuario;
		}
		public List<Usuario> getByUsuario(Usuario usuario) {		
			
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			List<Usuario> usuarios = new ArrayList<>();
			
			try {	
				String sql = "SELECT * FROM usuario WHERE 1=1";
				UsuarioIndexName indexQuery = new UsuarioIndexName();
				int position = 0;
				if(usuario.getId() > 0) { 
					sql += " AND id = ? ";
					position += 1;
					indexQuery.setId(position);
				}
				if(usuario.getNome() != "") {
					sql += " AND nome = ? ";
					position += 1;
					indexQuery.setNome(position);
				}
				if(usuario.getSobre_nome() != "") {
					sql += " AND sobre_nome = ? ";
					position += 1;
					indexQuery.setSobre_nome(position);
				}
						
				
				stmt = con.prepareStatement(sql); //Instancia a query montada
				
				if(usuario.getId() > 0)
					stmt.setLong(indexQuery.getId(), usuario.getId());
				
				if(usuario.getNome() != "") 
					stmt.setString(indexQuery.getNome(), usuario.getNome());
				
				if(usuario.getSobre_nome() != "") 
					stmt.setString(indexQuery.getSobre_nome(), usuario.getSobre_nome());
				
				rs = stmt.executeQuery(); 
				
				while (rs.next()) {
					
					Usuario usu = new Usuario();
					
					usu.setId(rs.getInt("id"));
					usu.setNome(rs.getString("nome"));
					usu.setSobre_nome(rs.getString("sobre_nome"));
					
					usuarios.add(usu);
				}				
				System.out.println("Recuperado com sucesso usuarios");
			} catch (SQLException e) {
				System.out.println("Erro ao buscar usuarios, erro: "+e);
			}finally {
				ConnectionFactory.closeConnection(con, stmt);
			}

			return usuarios;
		}
		public List<Usuario> getAll() {		
			
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			List<Usuario> usuarios = new ArrayList<>();
			
			try {	
				String sql = "SELECT * FROM usuario";
				stmt = con.prepareStatement(sql);			
				
				rs = stmt.executeQuery(); 
				
				while (rs.next()) {
					
					Usuario usuario = new Usuario();
					
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setSobre_nome(rs.getString("sobre_nome"));
					
					usuarios.add(usuario);
				}				
				System.out.println("Recuperado com sucesso usuarios");
			} catch (SQLException e) {
				System.out.println("Erro ao buscar usuarios, erro: "+e);
			}finally {
				ConnectionFactory.closeConnection(con, stmt);
			}

			return usuarios;
		}
		
		private interface ObjectUsuario {
	        int getNome();
	        int getSobre_nome();
	        int getId();
	        void setNome(int index);
	        void setSobre_nome(int index);
	        void setId(int index);
	    }
		private class UsuarioIndexName implements ObjectUsuario {
			 private int nome, sobre_nome, id;
			 public int getNome(){
				 return nome;
			 }
			 public int getSobre_nome(){
				 return sobre_nome;
			 }
			 public int getId(){
				 return id;
			 }
			 public void setNome(int index){
				 this.nome = index; 
			 }
			 public void setSobre_nome(int index){
				 this.sobre_nome = index;
			 }
			 public void setId(int index){
				 this.id = index;
			 }
	    }
		 
}
