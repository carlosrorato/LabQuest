
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Questao;



/**
 *
 * @author carlosrorato
 */
public class QuestaoDAO {
    
    
    public void create(Questao q){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            stmt = con.prepareStatement("INSERT INTO Questao (ano,dificuldade,disciplina,conteudo,enunciado,fonte,gabarito)VALUES(?,?,?,?,?,?,?)");
            stmt.setInt(1, q.getAno());
            stmt.setInt(2, q.getDificuldade());
            stmt.setString(3, q.getDisciplina());
            stmt.setString(4, q.getConteudo());
            stmt.setString(5, q.getEnunciado());
            stmt.setString(6, q.getFonte());
            stmt.setString(7, q.getGabarito());
            
            
            stmt.executeUpdate(); 
            
            JOptionPane.showMessageDialog(null,"Salvo com sucesso!");
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public List<Questao> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Questao> questoes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Questao");
            rs = stmt.executeQuery();
            
            
            while(rs.next()){
                
                Questao q = new Questao();
                
                q.setAno(rs.getInt("ano"));
                q.setId(rs.getInt("id"));
                q.setDificuldade(rs.getInt("dificuldade"));
                q.setConteudo(rs.getString("conteudo"));
                q.setDisciplina(rs.getString("disciplina"));
                q.setEnunciado(rs.getString("enunciado"));
                q.setFonte(rs.getString("fonte"));
                q.setGabarito(rs.getString("gabarito"));
                
                
                questoes.add(q);
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao adicionar usuario na lista: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return questoes;
        
    }
    
    public List<Questao> search(String criterio,String palavra){
        int flag = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Questao> questoes = new ArrayList<>();
        
        try {
            if(criterio.equals("Disciplina")) stmt = con.prepareStatement("SELECT * FROM Questao WHERE disciplina = ?");
            else if(criterio.equals("Conteúdo")) stmt = con.prepareStatement("SELECT * FROM Questao WHERE conteudo = ?");
            else if(criterio.equals("Fonte")) stmt = con.prepareStatement("SELECT * FROM Questao WHERE fonte = ?");         
            
            stmt.setString(1, palavra);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Questao q = new Questao();
                
                q.setAno(rs.getInt("ano"));
                q.setId(rs.getInt("id"));
                q.setConteudo(rs.getString("conteudo"));
                q.setDisciplina(rs.getString("disciplina"));
                q.setEnunciado(rs.getString("enunciado"));
                q.setFonte(rs.getString("fonte"));
                q.setGabarito(rs.getString("gabarito"));
                
                
                questoes.add(q);
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao adicionar usuario na lista: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return questoes;
        
    }
    
    public void update(Questao q){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = con.prepareStatement("UPDATE Questao SET ano = ?,dificuldade = ?,disciplina = ?,conteudo = ?,enunciado = ?,fonte = ?,gabarito = ? WHERE id = ?");
            stmt.setInt(1, q.getAno());
            stmt.setInt(2, q.getDificuldade());
            stmt.setString(3, q.getDisciplina());
            stmt.setString(4, q.getConteudo());
            stmt.setString(5, q.getEnunciado());
            stmt.setString(6, q.getFonte());
            stmt.setString(7, q.getGabarito());
            stmt.setInt(8, q.getId());
            
            
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    
    public void delete(Questao q){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            stmt = con.prepareStatement("DELETE FROM Questao WHERE id = ?");
            stmt.setInt(1, q.getId());
            
            
            stmt.executeUpdate();
            
            
            JOptionPane.showMessageDialog(null,"Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
}
