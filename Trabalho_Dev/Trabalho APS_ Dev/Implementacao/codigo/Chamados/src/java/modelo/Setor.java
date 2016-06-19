/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Duda
 */
@Entity
@Table(name="setor")
public class Setor implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String email;
    private String localSetor;
    
    @OneToOne
    @JoinColumn(name="id_servidor")
    private Servidor servidorResponsavel;
  
    
    @OneToMany(mappedBy="setor", fetch = FetchType.LAZY,cascade={CascadeType.ALL})
    private List<Servidor> servidores;
    
    @OneToMany(mappedBy="setor", fetch = FetchType.LAZY,cascade={CascadeType.ALL})
    private List<Chamado> Chamados;
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocalSetor() {
        return localSetor;
    }

    public void setLocalSetor(String localSetor) {
        this.localSetor = localSetor;
    }
     public List<Servidor> getServidores() {
        return servidores;
    }
    
    public void setServidores(List<Servidor> servidores) {
        this.servidores = servidores;
    }
    
     public List<Chamado> getChamados() {
        return Chamados;
    }

    public void setChamados(List<Chamado> Chamados) {
        this.Chamados = Chamados;
    }

    public Servidor getServidorResponsavel() {
        return servidorResponsavel;
    }

    public void setServidorResponsavel(Servidor servidorResponsavel) {
        this.servidorResponsavel = servidorResponsavel;
    }
    
    
 
   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Setor other = (Setor) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   

}
