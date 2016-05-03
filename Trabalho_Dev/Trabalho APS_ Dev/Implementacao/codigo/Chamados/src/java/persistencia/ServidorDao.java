/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


import modelo.Servidor;
import org.hibernate.Session;
/**
 *
 * @author Duda
 */
public class ServidorDao {
    private final Session sessao;
    
    public ServidorDao() {
        sessao = HibernateUtil.getSessionFactory().getCurrentSession();
        sessao.beginTransaction();
    }
    
    public void salvar(Servidor l) {
        sessao.saveOrUpdate(l);
    }
    
    public Servidor carregar(int id) {
        return (Servidor) sessao.load(Servidor.class, id);
    }
    
    public void remover(Servidor l) {
        sessao.delete(l);
    }
    
    public void encerrar() {
        sessao.getTransaction().commit();
    }
    
    
}
