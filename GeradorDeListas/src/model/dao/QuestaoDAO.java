
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
            stmt = con.prepareStatement("INSERT INTO Questao (ano,disciplina,conteudo,enunciado,fonte,gabarito)VALUES(?,?,?,?,?,?)");
            stmt.setInt(1, q.getAno());
            stmt.setString(2, q.getDisciplina());
            stmt.setString(3, q.getConteudo());
            stmt.setString(4, q.getEnunciado());
            stmt.setString(5, q.getFonte());
            stmt.setString(6, q.getGabarito());
            
            
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
            
            stmt = con.prepareStatement("UPDATE Questao SET ano = ?,disciplina = ?,conteudo = ?,enunciado = ?,fonte = ?,gabarito = ? WHERE id = ?");
            stmt.setInt(1, q.getAno());
            stmt.setString(2, q.getDisciplina());
            stmt.setString(3, q.getConteudo());
            stmt.setString(4, q.getEnunciado());
            stmt.setString(5, q.getFonte());
            stmt.setString(6, q.getGabarito());
            stmt.setInt(7, q.getId());
            
            
            
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
