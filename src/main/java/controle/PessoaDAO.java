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
import modelo.Pessoa;
import visao.frmLogin;
import visao.frmMenu;

public class PessoaDAO {
    private Connection con;
    
    public PessoaDAO(){
        this.con = new ConectaFactory().getConection();
    }

    
    public void cadastrarPessoa(Pessoa obj){
        try{
            String sql = "insert into pessoas (ID_PESSOAS, Endereço, Sexo, Data_Nascimento, NOME_PESSOA) values(?,?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getId_pessoa());
            stmt.setString(2, obj.getEndereco());
            stmt.setString(3, obj.getSexo());
            stmt.setInt(4, obj.getDataNascimento());
            stmt.setString(5, obj.getNome());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "salvo a pessoa!");
            
        } catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "erro ao cadastrar Pessoa" + erro);
        }
       
    }
     public void excluirPessoa(Pessoa obj){
            try{
            String sql= "delete pessoas from  where ID_PESSOAS=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId_pessoa());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro"+ erro);
        }
        }
     
     public void efetuarLogin(int Codigo){
        try{
            String sql = "select * from pessoas where ID_PESSOAS=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, Codigo);
            
            ResultSet rs= stmt.executeQuery();
            
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema!");
                frmMenu tela = new frmMenu();
                tela.setVisible(true);
                
            }else{
                JOptionPane.showMessageDialog(null, "Dados incorretos!");
                new frmLogin().setVisible(true);
                
            }
            
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            
        }
    }
      public List<Pessoa> listarPessoa(){
        try{
            List<Pessoa> lista = new ArrayList<>();
            String slq = "select * from pessoas";
            PreparedStatement stmt = con.prepareStatement(slq);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Pessoa obj = new Pessoa();
                obj.setId_pessoa(rs.getInt("ID_PESSOAS"));
                obj.setEndereco(rs.getString("Endereço"));
                obj.setSexo(rs.getString("Sexo"));
                obj.setDataNascimento(rs.getInt("Data_Nascimento"));
                obj.setNome(rs.getString("NOME_PESSOA"));
                
                
                lista.add(obj);
                
            }
            return lista;
        }catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados" + erro);
            return null;
        }
    }
}
