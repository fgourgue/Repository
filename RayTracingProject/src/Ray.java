public class Ray {

	//Les rayons sont construits comme étant des demi-droites, ils ont donc un x/y début et poursuivent leur
	//chemin a l'aide d'une eq : y = ax+b. Le a étant défini immédiatement par l'angle d'émission par rapport
	//à l'antenne, et le b étant obtenu grace aux coordonnées de l'antenne.
	
	public double a;
	public int b;
	public int xstart;
	public int ystart;
	public int xend;
	public int yend;
	public int directionV;
	public int directionH;
	public Antenne antenne;
	
	public Ray(double orientation, Antenne antenne){
		
		this.antenne = antenne;
		this.xstart = (int) antenne.getXCenter();
		this.ystart = (int) antenne.getYCenter();
		
		this.setA(orientation);
		this.setB(orientation);
		this.setDirection(orientation);
		
		//Fin de chaque rayon présente pour le test actuellement, a modifier par apres
		
		//this.xend;
		//this.yend;
	}
	
	
	//Permet de déterminer la pente en fonction de l'orientation
	public void setA(double orientation){	//OK
		if(orientation >= 2*Math.PI){
			orientation = orientation % (2*Math.PI);
		}
		
		if(orientation == 0 || orientation == Math.PI){
			this.a = 0;
		}else if(orientation == Math.PI/2 || orientation == 3*Math.PI/2){
			this.a = 1;
		}else if((orientation > 0 && orientation < Math.PI/2) || (orientation > Math.PI && orientation < 3*Math.PI/2)){
			this.a = Math.abs(Math.tan(orientation));
		}else{
			this.a = - Math.abs(Math.tan(orientation));
		}
	}
	
	//Permet de déterminer la "hauteur" du rayon
	public void setB(double orientation){	//OK
		if(orientation == 0 || orientation == Math.PI){
			this.b = ystart;
		}else if(orientation == Math.PI/2 || orientation == 3*Math.PI/2){
			this.b = xstart;
		}else{
			this.b = (int) (ystart - this.a * xstart);
		}
	}
	
	//Permet de déterminer le sens de propagation du rayon
	public void setDirection(double orientation){	//OK
		double roundDirection = orientation / (Math.PI/2);
		if((roundDirection >= 0 && roundDirection <= 1) || (roundDirection >3 && roundDirection < 4)){
			this.directionH = 1;
			//De - 90° à 90°, donc en direction des x positif plus les rayons perpendiculaire à x en direction de y pos
		}else{
			this.directionH = -1;
			//De 90° à 270°, donc vers les x < 0 et le y < 0 quand perpendiculaire a x
		}
		if(roundDirection >= 0 && roundDirection < 2){
			this.directionV = 1;
		}else{
			this.directionV = -1;
		}
	}
	
	public void interaction(){
		int xendProv = xstart;
		int yendProv = ystart;
		for(Mur mur : antenne.objets.murs){
			if(mur.type == 0){	//Horizontal
				int xRay = (int) ((mur.position - this.b)/this.a);
				if(xRay <= mur.fin && xRay >= mur.debut){
					xendProv = xRay;
					yendProv = mur.position;
				}
			}else if(mur.type == 1){	//Vertical
				int yRay = (int) (this.a * mur.position) + this.b;
				if(yRay <= mur.fin && yRay >= mur.debut){
					xendProv = mur.position;
					yendProv = yRay;
				}
			}
			this.xend = xendProv;
			this.yend = yendProv;
		}
	}	
}