package br.com.main;

import java.util.Arrays;
import java.util.List;

import br.com.model.bean.Usuario;
import br.com.model.dao.UsuarioDAO;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Usuario usuario = new Usuario();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuario.setId(4); //Opcional quando inserindo
		usuario.setNome("Douglas");
		usuario.setSobre_nome("Pavan");
		
		//usuarioDAO.create(usuario); //Insert
		//usuarioDAO.save(usuario); //Update
		//usuarioDAO.delete(usuario); //Delete
		
		//Select
	
		List<Usuario> usuarios = usuarioDAO.getAll(); //Todos os usuarios
		//List<Usuario> usuarios = usuarioDAO.getById(4); //Todos com o id
		//List<Usuario> usuarios = usuarioDAO.getByUsuario(usuario); //Todos os usuarios com os campos preenchidos
		for (Usuario row : usuarios) {
			System.out.println("Id:"+row.getId()+" Nome: "+row.getNome()+" "+row.getSobre_nome());
		}
	//*/
	}

}
