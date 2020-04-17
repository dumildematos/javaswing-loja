/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Conexao;
import Model.ProdutoModel;
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
public class ProdutoController {
    
    public void Adicionar(ProdutoModel produto){
        
        Connection conectar = Conexao.openConection();
        PreparedStatement stmt = null;
        
         try{
            stmt = conectar.prepareStatement("insert into produto (nome,codbarra,preco,quantidade) values(?,?,?,?)");
                        
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCodigo());
            stmt.setFloat(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto acdicionado com sucesso");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar "+ex); 
            
        }finally{
            Conexao.closeConnection(conectar, stmt);
        }
    }
    
     public ProdutoModel Pesquisar(ProdutoModel produto, String codigo) {
        
        Connection con = Conexao.openConection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("select * from produto where codbarra like ? or nome like ?");
                        
            stmt.setString(1, codigo);
            stmt.setString(2, codigo);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                produto.setId(rs.getInt("idproduto"));
                produto.setNome(rs.getString("nome"));
                produto.setCodigo(rs.getString("codbarra"));
                produto.setPreco(rs.getFloat("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                return produto;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar "+ex); 
        }finally{
            Conexao.closeConnection(con,stmt);
        }
        return null;
         
     }
     
     public void Editar(ProdutoModel produto, int id){
        Connection con = Conexao.openConection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        
         try {
            
            stmt = con.prepareStatement("update produto set nome=?, codbarra=?, preco=?, quantidade=? where idproduto=?");
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCodigo());
            stmt.setFloat(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Editado com sucesso");
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
            
            stmt = con.prepareStatement("delete from produto where idproduto=?");
            stmt.setInt(1, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Eliminado com sucesso");
         }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao editar "+ex); 
        }finally{
            Conexao.closeConnection(con,stmt);
        }
     
     }
}
