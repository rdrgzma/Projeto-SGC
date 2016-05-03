/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


import modelo.Setor;
import org.hibernate.Session;
/**
 *
 * @author Duda
 */
public class SetorDao {
    private final Session sessao;
    
    public SetorDao() {
        sessao = HibernateUtil.getSessionFactory().getCurrentSession();
        sessao.beginTransaction();
    }
    
    public void salvar(Setor l) {
        sessao.saveOrUpdate(l);
    }
    
    public Setor carregar(int id) {
        return (Setor) sessao.load(Setor.class, id);
    }
    
    public void remover(Setor l) {
        sessao.delete(l);
    }
    
    public void encerrar() {
        sessao.getTransaction().commit();
    }
    
    
}
