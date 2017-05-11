import java.util.ArrayList;


public class Objets{

	public ArrayList<Mur> murs = new ArrayList<Mur>();
	public Antenne antenne;
	public ArrayList<Ray> rays = new ArrayList<Ray>();
	//public Recepteur recepteur;
	
	public Objets(){
		
		Utiles util = new Utiles(this);
		
		antenne.createRays();
		
		for(Ray ray : rays){
			ray.interaction();
		}
		
	}
	
}