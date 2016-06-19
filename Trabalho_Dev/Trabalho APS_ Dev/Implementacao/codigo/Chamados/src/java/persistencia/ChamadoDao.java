/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import modelo.Chamado;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Duda
 */
public class ChamadoDao {
    private final Session sessao;
    
    public ChamadoDao() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        
    }
    
    public void salvar(Chamado l) {
        Transaction t = sessao.beginTransaction();
        sessao.saveOrUpdate(l);
        t.commit();
    }
    
    public Chamado carregar(int id) {
        return (Chamado) sessao.load(Chamado.class, id);
    }
    
    public void remover(Chamado l) {
        sessao.delete(l);
    }
    
    public List<Chamado> listar() {
        return sessao.createCriteria(Chamado.class).list();
    } 
    
    public void encerrar() {
        sessao.getSessionFactory().close();
       sessao.close(); 
    }
    
    
}
