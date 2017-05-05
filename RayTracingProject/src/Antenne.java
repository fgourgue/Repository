import java.util.ArrayList;

public class Antenne {

	public Objets objets;
	public int xstart;
	public int xend;
	public int ystart;
	public int yend;
	
	public Antenne(Objets objets, int xstart, int xend, int ystart, int yend){
		
		this.objets = objets;
		this.xstart = xstart;
		this.xend = xend;
		this.ystart = ystart;
		this.yend = yend;
		
	}
	
	public double getXCenter(){
		return this.xstart + (this.xend - this.xstart)/2;
	}
	
	public double getYCenter(){
		return this.ystart + (this.yend - this.ystart)/2;
	}
	
	public void createRays(){
		for(int i = 0; i <= 24; i+=1){
			this.objets.rays.add(new Ray(((double) i)/10, this));
		}
	}
}