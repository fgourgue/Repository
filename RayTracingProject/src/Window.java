import java.awt.Color;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Objets objets;
	public Panneau panneau = new Panneau(this);
	
	public Window(Objets objets){
		this.setTitle("RayTracing");
		this.setBackground(Color.BLACK);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(1200, 900);
	    this.setLocationRelativeTo(null);
	    this.setFocusable(false);
	    this.setResizable(false);
	    this.setVisible(true);
	    
	    this.objets = objets;
	    
	    this.getContentPane().add(this.panneau);
	    this.setVisible(true);

	}

}
