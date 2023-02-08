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
import modelo.Jogos;

/**
 *
 * @author jeffn
 */
public class JogosDAO {
     private Connection con;
    
    public JogosDAO(){
        this.con = new ConectaFactory().getConection();
    }
    
    public void cadastrarJogos(Jogos obj){
        try{
            String sql = "insert into jogo (ID_NumeroJogo, dataJogo) values(?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCod());
            stmt.setInt(2, obj.getData());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "salvo o Jogo!");
            
        } catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "erro ao cadastrar jogo" + erro);
        }
       
    }
     public void excluirJogos(Jogos obj){
            try{
            String sql= "delete jogo from  where ID_NomeCampeonato=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCod());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro"+ erro);
        }
        }
      public List<Jogos> listarJogos(){
        try{
            List<Jogos> lista = new ArrayList<>();
            String slq = "select * from jogo";
            PreparedStatement stmt = con.prepareStatement(slq);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Jogos obj = new Jogos();
                obj.setCod(rs.getInt("ID_NumeroJogo"));
                obj.setData(rs.getInt("DataJogo"));
                
                lista.add(obj);
                
            }
            return lista;
        }catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados" + erro);
            return null;
        }
    }
}
