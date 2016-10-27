package testen;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domein.DomeinController;
import domein.Materiaal;
import gui.GlobalFrameController;
import gui.MateriaalDetailController;
import gui.MateriaalFrameController;
import java.text.ParseException;
import javafx.event.ActionEvent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Kas De Durpel
 */
public class GuiTest {
    
    private GlobalFrameController globalFrame;
    private DomeinController domeinController;
    private MateriaalDetailController materiaalDetailController;
    
    @Mock
    private ActionEvent event;
    
    @Before
    public void Before() throws ParseException{
        Materiaal m = new Materiaal("Monopoly");
        MockitoAnnotations.initMocks(this);
        domeinController = new DomeinController();
        materiaalDetailController = new MateriaalDetailController(m, domeinController);
        ;
    }
    
    /*@Test
    public void controleerMenuItems(){
        int i = domeinController.geefAlleMaterialen().size();
      materiaalFrameController.voegMateriaalToe(event);
      Assert.assertEquals(domeinController.geefAlleMaterialen(), i+1);
    }*/
    
    
    
}
