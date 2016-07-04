/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


import java.util.List;
import modelo.Setor;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Duda
 */
public class SetorDao {
    private Session sessao;
    
    public SetorDao() {
         sessao = HibernateUtil.getSessionFactory().openSession();
        
    }
    
    public void salvar(Setor s) {
        Transaction t = sessao.beginTransaction();
        sessao.saveOrUpdate(s);
        t.commit();
    }
    
    public void atualizar(Setor s){
        sessao.update(s);
    }
    
    public Setor carregar(int id) {
        return (Setor) sessao.load(Setor.class, id);
    }
    
    public void remover(int id) {
        Transaction t = sessao.beginTransaction();
        t.begin();
        
        sessao.delete(carregar(id));
        t.commit();
    }
    
    public List<Setor> listar() {
        return sessao.createCriteria(Setor.class).list();
    } 
    
    public void encerrar() {
            sessao.close();
   
    }
    
    
}
