/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import conexao.ConectaFactory;
import java.awt.image.ColorModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Campeonato;

public class CampeonatoDAO {
    private Connection con;
    
    public CampeonatoDAO(){
        this.con = new ConectaFactory().getConection();
    }
    
    public void cadastrarCampeonato(Campeonato obj){
        try{
            String sql = "insert into campeonato (ID_NomeCampeonato, ano, NOME_CAMPEONATO) values(?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCodigo());
            stmt.setInt(2, obj.getData());
            stmt.setString(3, obj.getNome());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "salvo o campeonato!");
            
        } catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "erro ao cadastrar campeonato" + erro);
        }
       
    }
     public void excluirCampeonato(Campeonato obj){
            try{
            String sql= "delete campeonato from  where ID_NomeCampeonato=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCodigo());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro"+ erro);
        }
        }
      public List<Campeonato> listarCampeonatos(){
        try{
            List<Campeonato> lista = new ArrayList<>();
            String slq = "select * from campeonato";
            PreparedStatement stmt = con.prepareStatement(slq);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Campeonato obj = new Campeonato();
                obj.setCodigo(rs.getInt("ID_NomeCompleto"));
                obj.setData(rs.getInt("ano"));
                obj.setNome(rs.getString("NOME_CAMPEONATO"));
                
                lista.add(obj);
                
            }
            return lista;
        }catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados" + erro);
            return null;
        }
    }
}
