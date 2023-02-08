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
import modelo.Confederacao;


public class ConfederacaoDAO {
     private Connection con;
    
    public ConfederacaoDAO(){
        this.con = new ConectaFactory().getConection();
    }
    
    public void cadastrarConfederacao(Confederacao obj){
        try{
            String sql = "insert into confederacao (ID_Confederacao, Estado, NOME_CONFEDERACAO) values(?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCodigo());
            stmt.setString(2, obj.getEstado());
            stmt.setString(3, obj.getNome());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "salvo a confederação!");
            
        } catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "erro ao cadastrar confederação" + erro);
        }
       
    }
    public void excluirConfederacao(Confederacao obj){
            try{
            String sql= "delete confederacao from  where ID_confederacao=?";
            
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
    public List<Confederacao> listarConfederacao(){
        try{
            List<Confederacao> lista = new ArrayList<>();
            String slq = "select * from confederacao";
            PreparedStatement stmt = con.prepareStatement(slq);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Confederacao obj = new Confederacao();
                obj.setCodigo(rs.getInt("ID_confederacao"));
                obj.setEstado(rs.getString("Estado"));
                obj.setNome(rs.getString("NOME_CONFEDERACAO"));
                
                lista.add(obj);
                
            }
            return lista;
        }catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados" + erro);
            return null;
        }
    }
}
