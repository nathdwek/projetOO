package sonic.model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sonic.view.Drawable;

public class Map {
	private LinkedList<Hittable> fixedHittables;
	private LinkedList<SelfUpdatable> selfUpdatables;
	private ArrayList<Hittable> movingHittables;
	private LinkedList<Drawable> drawables;
	private Sonic hero;

	public Map(String mapXML){

		fixedHittables = new LinkedList<Hittable>();
		selfUpdatables = new LinkedList<SelfUpdatable>();
		movingHittables = new ArrayList<Hittable>();
		drawables = new LinkedList<Drawable>();
		try {

			File fXmlFile = new File(mapXML);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList groundList = doc.getElementsByTagName("ground");
			NodeList slopeList = doc.getElementsByTagName("slope");
			NodeList obstacleBlockList = doc.getElementsByTagName("obstacle");
			NodeList monsterList = doc.getElementsByTagName("monster");

			createGround(groundList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		hero =new Sonic(500, 150);
		drawables.add(hero);
		movingHittables.add(hero);
		selfUpdatables.add(hero);

	}

	private void createGround(NodeList groundList) {
		for (int i = 0; i < groundList.getLength(); i++){
			Node gBNode = groundList.item(i);
			if (gBNode.getNodeType() == Node.ELEMENT_NODE) {
				Element gBElement = (Element) gBNode;
				GroundBlock groundBlock = new GroundBlock(Double.valueOf(gBElement.getAttribute("start")),
						Double.valueOf(gBElement.getAttribute("end")),
						Double.valueOf(gBElement.getAttribute("level")));
				fixedHittables.add(groundBlock);
				drawables.add(groundBlock);
			}
		}
	}

	public LinkedList<SelfUpdatable> getSelfUpdatables() {
		return selfUpdatables;
	}

	public ArrayList<Hittable> getMovingHittables() {
		return movingHittables;
	}

	public LinkedList<Hittable> getfixedHittables() {
		return fixedHittables;
	}

	public LinkedList<Drawable> getDrawables() {
		return drawables;
	}

	public Sonic getHero(){
		return hero;
	}

}
