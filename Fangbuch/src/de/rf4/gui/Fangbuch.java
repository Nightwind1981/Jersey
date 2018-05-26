package de.rf4.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fangbuch extends JPanel
{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        
        Fangbuch buch = new Fangbuch();
        
        frame.setContentPane(buch);
    }
    
    public Fangbuch()
    {
        buildUI();
    }
    
    private void buildUI()
    {

    }
    
}
