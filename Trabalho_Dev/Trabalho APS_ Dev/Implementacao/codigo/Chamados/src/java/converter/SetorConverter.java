/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Setor;
import persistencia.SetorDao;

/**
 *
 * @author marcio
 */
@FacesConverter("setorConverter")
public class SetorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try{
        Integer id=Integer.parseInt(string);
           SetorDao dao =new SetorDao();
           return dao.carregar(id);
            
        }catch(RuntimeException ex){
            System.out.println("String: "+ string);
        return null;
        }
        //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        //To change body of generated methods, choose Tools | Templates.
        try{
            Setor setor = (Setor) o;
          Integer id = setor.getId();
            return id.toString();
        }catch (RuntimeException ex){
        System.out.println("Objeto: "+o);
        return null;
        }
    }
  
    
}
