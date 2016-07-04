/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.mail.Session;
import javax.servlet.http.HttpSession;
import modelo.Atendimento;
import modelo.Chamado;
import modelo.Servidor;
import modelo.Setor;
import persistencia.AtendimentoDao;
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
    private String descAtendimento="";

    private Atendimento atendimento =new Atendimento();
    private AtendimentoDao atendimentoDao = new AtendimentoDao();
    private AtendimentoBean atendimentoBean =new AtendimentoBean();
    private List<Atendimento> atendimentos;
   
    @PostConstruct
    public void init(){
         listaChamados = dao.listar(); 
              FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false); 
 servidor =(Servidor) session.getAttribute("usuario");
             
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
   
    public void salvar() {
        
        dao.salvar(chamado);
        atendimento.setDataAtendimento(Calendar.getInstance().getTime());
        atendimento.setDescricaoAtendimento(descAtendimento);
         FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false); 
    Servidor s =(Servidor) session.getAttribute("usuario");
        atendimento.setServidor(s);
        atendimento.setChamado(chamado);
        
        atendimentoDao.salvar(atendimento);
       
        
        enviarMensagem(FacesMessage.SEVERITY_INFO, "Chamado cadastrado com sucesso!!!");
        this.listaChamados = dao.listar(); 
        this.chamado = new Chamado();
        this.descAtendimento ="";
        this.atendimento =new Atendimento();
    }
    
     public void carregar(int id) {
        chamado = dao.carregar(id);
        atendimentos=atendimentoDao.listarAtendimento(chamado);
        
        
    }
     
     public void remover() {
        dao.remover(chamado);
        enviarMensagem(FacesMessage.SEVERITY_INFO, "Chamado removido com sucesso");
        listaChamados = dao.listar(); 
    }
     
   public void alteraStatusChamado(){
 //       
     //   chamado.alteraStatus(novoStatus);
        
        dao.salvar(chamado);
         listaChamados = dao.listar(); 
       
            
    }
   
    public void alteraSetorChamado(Setor setor){
        
        if(servidor.getCargo().equalsIgnoreCase("ADMINISTRADOR")||servidor.getSetor().equals(setor)){
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
    public String listaChamadoFrom(){
          FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false); 
     Servidor s =(Servidor) session.getAttribute("usuario");
               
        if(s!=null){
          return "listaChamados";
        }
        return "index";
    }
     
    private void enviarMensagem(Severity sev, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(sev, msg, ""));
    }
    
   public boolean verificaSetor(Setor se){
       FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false); 
     Servidor s =(Servidor) session.getAttribute("usuario");
       
       if((se.getNome() == s.getSetor().getNome())){
       return true;
       }   
       else{return false;}
           
       
   }
    
    public String voltarPrincipal(){
         FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false); 
       Servidor s =(Servidor) session.getAttribute("usuario");
      
        if(s.getCargo().equalsIgnoreCase("ADMINISTRADOR")){
            return "menu";
        }else{
            return "menu2";
        }
    }
    
    
    @PreDestroy
    public void encerrar() {
        dao.encerrar();
    }

    public String getDescAtendimento() {
        return descAtendimento;
    }

    public void setDescAtendimento(String descAtendimento) {
        this.descAtendimento = descAtendimento;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    
    
}
