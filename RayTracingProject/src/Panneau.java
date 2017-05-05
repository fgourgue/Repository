import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	
	public Window window;

	public Panneau(Window window){
		this.window = window;
		this.repaint();
	}
	
	public void paint(Graphics g){
		g.setColor(Color.red);
		for(Mur mur : window.objets.murs){
			if(mur.type == 0){
				g.drawRect(mur.position, mur.debut, 0, mur.fin - mur.debut);
			}else if(mur.type == 1){
				g.drawRect(mur.debut, mur.position, mur.fin - mur.debut, 0);
			}
		}
		g.setColor(Color.blue);
		Antenne antenne = window.objets.antenne;
		g.drawRect(antenne.xstart, antenne.ystart, antenne.xend - antenne.xstart, antenne.yend - antenne.ystart);
		g.fillRect(antenne.xstart, antenne.ystart, antenne.xend - antenne.xstart, antenne.yend - antenne.ystart);
		
		this.affichageRayon(g);
	}
	
	public void affichageRayon(Graphics g){
		
		g.setColor(Color.green);
		for(Ray ray : window.objets.rays){
			System.out.println("yes ?");
			g.drawLine(ray.xstart, ray.ystart, ray.xend, ray.yend);
		}
		
	}
}
