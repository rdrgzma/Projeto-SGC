/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


import java.util.List;
import modelo.Servidor;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Duda
 */
public class ServidorDao {
    private final Session sessao;
    
    public ServidorDao() {
        //sessao = HibernateUtil.getSessionFactory().getCurrentSession();
        //sessao.beginTransaction();
         sessao = HibernateUtil.getSessionFactory().openSession();
    }
    
    public void salvar(Servidor l) {
        Transaction t = sessao.beginTransaction();
        sessao.saveOrUpdate(l);
        t.commit();
       
    }
    
    public Servidor carregar(int id) {
        return (Servidor) sessao.load(Servidor.class, id);
    }
    
    public void remover(Servidor l) {
        Transaction t = sessao.beginTransaction();
        t.begin();
        sessao.delete(l);
        t.commit();
    }
    
     public List<Servidor> listar() {
        return sessao.createCriteria(Servidor.class).list();
    } 
    
    public void encerrar() {
         sessao.close();
     
    }
    
    public Servidor autentica(String login, String senha) {
        return (Servidor) sessao.createQuery("FROM Servidor WHERE login=:login AND senha=:senha").setString("login", login).setString("senha", senha).uniqueResult();
    }
    
   
}
