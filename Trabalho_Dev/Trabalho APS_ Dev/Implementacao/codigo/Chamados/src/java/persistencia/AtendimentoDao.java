/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import modelo.Atendimento;
import modelo.Chamado;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author marcio
 */
public class AtendimentoDao {
    private Session sessao;
    
    public AtendimentoDao() {
         sessao = HibernateUtil.getSessionFactory().openSession();
        
    }
    
    public void salvar(Atendimento a) {
        Transaction t = sessao.beginTransaction();
        sessao.saveOrUpdate(a);
        t.commit();
    }
    
    public void atualizar(Atendimento a){
        sessao.update(a);
    }
    
    public Atendimento carregar(int id) {
        return (Atendimento) sessao.load(Atendimento.class, id);
    }
    
    public void remover(int id) {
        Transaction t = sessao.beginTransaction();
        t.begin();
        
        sessao.delete(carregar(id));
        t.commit();
    }
    
    public List<Atendimento> listar() {
        return sessao.createCriteria(Atendimento.class).list();
    } 
    
    public List<Atendimento>listarAtendimento(Chamado c){
        Query consulta = sessao.createQuery("from Atendimento where chamado = :chamado");
        consulta.setParameter("chamado", c);
        return consulta.list();
    }
    
    public void encerrar() {
            sessao.close();
   
    }
    
    
}
