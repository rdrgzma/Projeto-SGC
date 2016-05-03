/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Chamado;
import persistencia.ChamadoDao;

/**
 *
 * @author Duda
 */
@ManagedBean(name="chamadoBean")
@SessionScoped
public class CadastroChamdoBean {
    
    private Chamado Chamado = new Chamado();
    private final  ChamadoDao dao = new ChamadoDao();
    private List<Chamado> listaChamados;

    public CadastroChamdoBean() {
        listaChamados = dao.listar();
    }
     
     public List<Chamado> getListaChamados() {
        return listaChamados;
    }
    

    public Chamado getChamado() {
        return Chamado;
    }

    public void setChamado(Chamado Chamado) {
        this.Chamado = Chamado;
    }

   
    public void salvar() {
        dao.salvar(Chamado);
        enviarMensagem(FacesMessage.SEVERITY_INFO, "Chamado cadastrado com sucesso!!!");
        listaChamados = dao.listar();
        getListaChamados();
    }
    
     public void carregar(int id) {
        Chamado = dao.carregar(id);
    }
     
     public void remover() {
        dao.remover(Chamado);
        enviarMensagem(FacesMessage.SEVERITY_INFO, "Chamado removido com sucesso");
        listaChamados = dao.listar(); 
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
