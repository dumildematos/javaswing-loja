/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Conexao;
import Model.ClienteModel;
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
public class ClienteController {
    
    public void Adicionar(ClienteModel cliente){
        
        Connection conectar = Conexao.openConection();
        PreparedStatement stmt = null;
        
         try{
            stmt = conectar.prepareStatement("insert into cliente (nome,telefone,morada) values(?,?,?)");
                        
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getMorada());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar "+ex); 
            
        }finally{
            Conexao.closeConnection(conectar, stmt);
        }
    }
    
     public ClienteModel Pesquisar(ClienteModel cliente, String telefone) {
        
        Connection con = Conexao.openConection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("select * from cliente where telefone like ?");
                        
            stmt.setString(1, telefone);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                cliente.setId(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setMorada(rs.getString("morada"));
                return cliente;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar "+ex); 
        }finally{
            Conexao.closeConnection(con,stmt);
        }
        return null;
         
     }
     
     public void Editar(ClienteModel cliente, int id){
        Connection con = Conexao.openConection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
         try {
            
            stmt = con.prepareStatement("update cliente set nome=?, telefone=?, morada=? where idcliente=?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getMorada());
            stmt.setInt(4, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente Editado com sucesso");
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
            
            stmt = con.prepareStatement("delete from cliente where idcliente=?");
            stmt.setInt(1, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente Eliminado com sucesso");
         }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao editar "+ex); 
        }finally{
            Conexao.closeConnection(con,stmt);
        }
     
     }
    
}
