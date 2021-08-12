package org.primefaces.test;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import lombok.Data;
import org.primefaces.event.RowEditEvent;

@Data
@Named
@ViewScoped
public class TestView implements Serializable {
    
    private String string;
    private Integer integer;
    private BigDecimal decimal;
    private LocalDateTime localDateTime;
    private TestLazyModel model;
    
    @PostConstruct  
    public void init() {
        string = "Welcome to PrimeFaces!!!";
        model = new TestLazyModel();
    }
    
    public TestLazyModel getModel() {
        return model;
    }

    public void onRowEdit(RowEditEvent<ExampleData> event) {
        ExampleData d = event.getObject();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Changed to "+d.getSampleValue()));
    }
}
