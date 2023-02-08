/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import conexao.ConectaFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Clubes;
public class ClubesDAO {
     private Connection con;
    
    public ClubesDAO(){
        this.con = new ConectaFactory().getConection();
    }
    
    public void cadastrarClubes( Clubes obj){
        try{
            String sql = "insert into  (ID_NomeClube, NOME_CLUBE, EnderecoClube) values(?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCod());
            stmt.setInt(2, obj.getDataFundacao());
            stmt.setString(3, obj.getEndereco());
            
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "salvo o Jogo!");
            
        } catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "erro ao cadastrar jogo" + erro);
        }
    }
    
    public void excluirClubes(Clubes obj){
        try{
            String sql= "delete clube from  where ID_NomeClube=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCod());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar a exclusão"+ erro);
        }
    }
    public List<Clubes> listarClubes(){
        try{
            List<Clubes> lista = new ArrayList<>();
            String slq = "select * from clube";
            PreparedStatement stmt = con.prepareStatement(slq);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Clubes obj = new Clubes();
                obj.setCod(rs.getInt("ID_NomeClube"));
                obj.setDataFundacao(rs.getInt("DataFundacao"));
                obj.setEndereco(rs.getString("EnderecoClube"));
                
                lista.add(obj);
                
            }
            return lista;
        }catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados" + erro);
            return null;
        }
    }
}
