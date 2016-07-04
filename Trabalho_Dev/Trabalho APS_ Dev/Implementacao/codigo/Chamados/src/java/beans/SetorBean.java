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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Servidor;
import modelo.Setor;
import persistencia.SetorDao;

/**
 *
 * @author Duda
 */
@ManagedBean(name="setorBean")
@SessionScoped
public class SetorBean implements Serializable {
    
    private Setor setor = new Setor();
    private  SetorDao dao = new SetorDao();
    private List<Setor> listaSetores;
    
     @PostConstruct
     public void init(){
         listaSetores=dao.listar();
     }
   
   
     public List<Setor> getListaSetores() {
        
        return  listaSetores;
    }
    

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

   
    public void salvar() {
        dao.salvar(setor);
        enviarMensagem(FacesMessage.SEVERITY_INFO, "Setor cadastrado com sucesso!!!");
        this.listaSetores = dao.listar();
       
    }
    
     public void carregar(int id) {
        setor = dao.carregar(id);
    }
     
     public void remover(int id) {
         
        dao.remover(id);
        listaSetores = dao.listar(); 
        enviarMensagem(FacesMessage.SEVERITY_INFO, "Setor removido com sucesso");

    }
      public String listaSetorFrom(){
          FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false); 
    Servidor servidor =(Servidor) session.getAttribute("usuario");
               
        if(servidor!=null){
          return "listaSetores";
        }
        return "index";
    }
      public String voltarPrincipal(){
         FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false); 
    Servidor servidor =(Servidor) session.getAttribute("usuario");
        if(servidor.getCargo().equalsIgnoreCase("ADMINISTRADOR")){
            return "menu";
        }else{
            return "menu2";
        }
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

