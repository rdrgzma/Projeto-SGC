/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Servidor;
import modelo.Setor;
import persistencia.ServidorDao;

/**
 *
 * @author Duda
 */
@ManagedBean(name="servidorBean")
@SessionScoped
public class ServidorBean implements Serializable {
    
    private Servidor servidor = new Servidor();
    private final  ServidorDao dao = new ServidorDao();
    private List<Servidor> listaServidor;
    private Setor setor = new Setor(); 
    
     @PostConstruct
     public void init(){
         this.listaServidor=dao.listar();
     }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor Setor) {
        this.setor = Setor;
    }
         
     public List<Servidor> getListaServidor() {
        return listaServidor;
    }
    
    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor Servidor) {
        this.servidor = Servidor;
    }

    public void salvar() {
        dao.salvar(servidor);
        enviarMensagem(FacesMessage.SEVERITY_INFO, "Servidor cadastrado com sucesso!!!");
        this.listaServidor = dao.listar();
        this.servidor= new Servidor();
    }
    
     public void carregar(int id) {
        servidor = dao.carregar(id);
    }
     
     public void remover() {
        dao.remover(servidor);
        enviarMensagem(FacesMessage.SEVERITY_INFO, "Servidor removido com sucesso");
        this.listaServidor = dao.listar();
        this.servidor=null;
    }   
   
    private void enviarMensagem(Severity sev, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(sev, msg, ""));
    }
    
    @PreDestroy
    public void encerrar() {
        dao.encerrar();
    }
}
