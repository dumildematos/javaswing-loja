/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Connection.Conexao;
import Model.FuncionarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author dumilde.matos
 */
public class FuncionarioController {
        public void Adicionar(FuncionarioModel funcionario){
        
        Connection conectar = Conexao.openConection();
        PreparedStatement stmt = null;
        
         try{
            stmt = conectar.prepareStatement("insert into funcionario (nome,usuario,senha,funcao) values(?,?,?,?)");
                        
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getUsuario());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getFuncao());

            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Funcionario adicionado com sucesso");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar "+ex); 
            
        }finally{
            Conexao.closeConnection(conectar, stmt);
        }
    }
    
     public FuncionarioModel Pesquisar(FuncionarioModel funcionario, String usuario) {
        
        Connection con = Conexao.openConection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("select * from funcionario where usuario like ? ");
                        
            stmt.setString(1, usuario);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                funcionario.setId(rs.getInt("idfuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setUsuario(rs.getString("usuario"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setFuncao(rs.getString("funcao"));

                return funcionario;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar "+ex); 
        }finally{
            Conexao.closeConnection(con,stmt);
        }
        return null;
         
     }
     
     public void Editar(FuncionarioModel funcionario, int id){
        Connection con = Conexao.openConection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
         try {
            
            stmt = con.prepareStatement("update funcionario set nome=?, usuario=?, senha=?, funcao=? where idfuncionario=?");
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getUsuario());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getFuncao());
            stmt.setInt(5, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário Editado com sucesso");
         }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao editar "+ex); 
        }finally{
            Conexao.closeConnection(con,stmt);
        }
     }
     
     public void Apagar(int id){
         
        Connection con = Conexao.openConection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
         try {
            
            stmt = con.prepareStatement("delete from funcionario where idfuncionario=?");
            stmt.setInt(1, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionário Eliminado com sucesso");
         }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao editar "+ex); 
        }finally{
            Conexao.closeConnection(con,stmt);
        }
     
     }
}
