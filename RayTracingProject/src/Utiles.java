import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Utiles {
	
	private Objets objets;
	
	public Utiles(Objets objets){
		
		this.objets = objets;
		
		this.mapDebut();
		this.antenneRecup();
	
	}
	
	public void mapDebut(){
	
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File("Murs.xml"));
			final Element element = document.getDocumentElement();
			final NodeList sousElement = element.getChildNodes();
			
			
			final int nbSousElement = sousElement.getLength();
			for (int i = 0; i<nbSousElement; i++) {
			    if(sousElement.item(i).getNodeType() == Node.ELEMENT_NODE) {
			        final Element wall = (Element) sousElement.item(i);
			     
		   
			        if(wall.getAttribute("type").intern() == "horizontal"){	
			        	//.intern() est utile à la comparaison de String
			        	final Element xstart = (Element) wall.getElementsByTagName("xstart").item(0);
			        	final Element xend = (Element) wall.getElementsByTagName("xend").item(0);
			        	final Element y = (Element) wall.getElementsByTagName("y").item(0);
			        	this.objets.murs.add(new Mur(0, Integer.parseInt(xstart.getTextContent()), Integer.parseInt(xend.getTextContent()), Integer.parseInt(y.getTextContent())));

			        }else if(wall.getAttribute("type").intern() == "vertical"){
			        	final Element ystart = (Element) wall.getElementsByTagName("ystart").item(0);
			        	final Element yend = (Element) wall.getElementsByTagName("yend").item(0);
			        	final Element x = (Element) wall.getElementsByTagName("x").item(0);
			        	this.objets.murs.add(new Mur(1, Integer.parseInt(ystart.getTextContent()), Integer.parseInt(yend.getTextContent()), Integer.parseInt(x.getTextContent())));
			        }
			        
			    }				
			}
	
		}catch(final ParserConfigurationException e){
			e.printStackTrace();
		}catch(final SAXException e){
		    e.printStackTrace();
		}catch(final IOException e){
		    e.printStackTrace();
		}
	}
	
	public void antenneRecup(){
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new File("Antenne.xml"));
			final Element antenne = document.getDocumentElement();
		        	final Element xstart = (Element) antenne.getElementsByTagName("xstart").item(0);
		        	final Element xend = (Element) antenne.getElementsByTagName("xend").item(0);
		        	final Element ystart = (Element) antenne.getElementsByTagName("ystart").item(0);
		        	final Element yend = (Element) antenne.getElementsByTagName("yend").item(0);
		        	this.objets.antenne = new Antenne(objets, Integer.parseInt(xstart.getTextContent()), Integer.parseInt(xend.getTextContent()), Integer.parseInt(ystart.getTextContent()), Integer.parseInt(yend.getTextContent()));
		}catch(final ParserConfigurationException e){
			e.printStackTrace();
		}catch(final SAXException e){
		    e.printStackTrace();
		}catch(final IOException e){
		    e.printStackTrace();
		}
	}
}