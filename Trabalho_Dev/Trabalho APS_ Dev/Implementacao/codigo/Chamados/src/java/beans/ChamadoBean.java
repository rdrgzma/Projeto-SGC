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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Chamado;
import modelo.Servidor;
import modelo.Setor;
import persistencia.ChamadoDao;

/**
 *
 * @author Duda
 */
@ManagedBean(name="chamadoBean")
@SessionScoped
public class ChamadoBean implements Serializable {
    
    private Chamado chamado = new Chamado();
    private final  ChamadoDao dao = new ChamadoDao();
    private List<Chamado> listaChamados;
    private Setor setor = new Setor();
    private Servidor servidor = new Servidor();
    private String novoStatus;
   
    @PostConstruct
    public void init(){
         listaChamados = dao.listar(); 
    } 
    
    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
        this.chamado.setSetor(setor);
    }

     public List<Chamado> getListaChamados() {
        return listaChamados;
    }
    
    
    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado Chamado) {
        this.chamado = Chamado;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }
    public String getNovoStatus() {
        return novoStatus;
    }

    public void setNovoStatus(String novoStatus) {
        this.novoStatus = novoStatus;
    }
    
    public void salvar() {
        
        dao.salvar(chamado);
        enviarMensagem(FacesMessage.SEVERITY_INFO, "Chamado cadastrado com sucesso!!!");
        this.listaChamados = dao.listar(); 
        this.chamado = new Chamado();
    }
    
     public void carregar(int id) {
        chamado = dao.carregar(id);
    }
     
     public void remover() {
        dao.remover(chamado);
        enviarMensagem(FacesMessage.SEVERITY_INFO, "Chamado removido com sucesso");
        listaChamados = dao.listar(); 
    }
     
   public void alteraStatusChamado(){
        
        chamado.alteraStatus(novoStatus);
        
        dao.salvar(chamado);
         listaChamados = dao.listar(); 
       
            
    }
   
    public void alteraSetorChamado(Setor setor){
        
        if(servidor.getCargo().equals("Administrador")||servidor.getSetor().equals(setor)){
            chamado.setSetor(setor);
            dao.salvar(chamado);
            enviarMensagem(FacesMessage.SEVERITY_INFO, "Setor modificado com sucesso");
        }else{
            enviarMensagem(FacesMessage.SEVERITY_INFO, "Não é possivel modificar o setor deste chamado");
        }
    }
    
    public String cadastraChamadoForm(){
        chamado=new Chamado();
        return "cadastroChamado";
        
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
