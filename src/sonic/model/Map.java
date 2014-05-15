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
	private Double deathLevel;


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

			NodeList terrains = doc.getElementsByTagName("terrain");
			if (terrains.getLength()>1){
				throw new Exception("More than 1 terrain defined!");
			}
			else{
				createTerrain(terrains.item(0));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		hero =new Sonic(500, 150);
		drawables.add(hero);
		movingHittables.add(hero);
		selfUpdatables.add(hero);

	}

	private void createTerrain(Node terrain) {
		Element terrainE = (Element) terrain;
		deathLevel = Double.valueOf(terrainE.getAttribute("deathLevel"));
		NodeList groundList = terrainE.getElementsByTagName("ground");
		NodeList slopesList = terrainE.getElementsByTagName("slope");
		NodeList obstacleBlockList = terrainE.getElementsByTagName("obstacle");
		NodeList coinsList = terrainE.getElementsByTagName("coins");
		createGround(groundList);
		createObstacles(obstacleBlockList);
		createSlopes(slopesList);
		createCoins(coinsList);
	}

	private void createCoins(NodeList coinsList) {
		for (int i = 0; i < coinsList.getLength(); i++){
			Node coinPackNode = coinsList.item(i);
			if (coinPackNode.getNodeType() == Node.ELEMENT_NODE) {
				Element coinPackElement = (Element) coinPackNode;
				Double start = Double.valueOf(coinPackElement.getAttribute("start"));
				Double end = Double.valueOf(coinPackElement.getAttribute("end"));
				Double level = Double.valueOf(coinPackElement.getAttribute("level"));
				Integer vertQty = Integer.valueOf(coinPackElement.getAttribute("vertQty"));

				Double thisX = start;
				while (thisX < end ){
					for (int j = 0; j<vertQty; j++){
						Coin coin = new Coin(thisX,level+j*Coin.getHeight());
						drawables.add(coin);
						fixedHittables.add(coin);
					}
					thisX+=Coin.getWidth();
				}
			}
		}
	}

	private void createSlopes(NodeList slopesList) {
		for (int i = 0; i < slopesList.getLength(); i++){
			Node sNode = slopesList.item(i);
			if (sNode.getNodeType() == Node.ELEMENT_NODE) {
				Element sElement = (Element) sNode;
				Slope slope = new Slope(
						Double.valueOf(sElement.getAttribute("start")),
						Double.valueOf(sElement.getAttribute("end")),
						Double.valueOf(sElement.getAttribute("startLevel")),
						Double.valueOf(sElement.getAttribute("endLevel")));

				fixedHittables.addAll(slope.getBlocks());
				drawables.addAll(slope.getBlocks());
			}
		}
	}

	private void createObstacles(NodeList obstacleBlockList) {
		for (int i = 0; i < obstacleBlockList.getLength(); i++){
			Node oBNode = obstacleBlockList.item(i);
			if (oBNode.getNodeType() == Node.ELEMENT_NODE) {
				Element oBElement = (Element) oBNode;
				Double bottom = Double.valueOf(oBElement.getAttribute("level"));
				GroundBlock obstacleBlock = new GroundBlock(
						Double.valueOf(oBElement.getAttribute("start")),
						Double.valueOf(oBElement.getAttribute("end")),
						bottom,
						Double.valueOf(oBElement.getAttribute("height"))+bottom);

				fixedHittables.add(obstacleBlock);
				drawables.add(obstacleBlock);
			}
		}
	}

	private void createGround(NodeList groundList) {
		for (int i = 0; i < groundList.getLength(); i++){
			Node gBNode = groundList.item(i);
			if (gBNode.getNodeType() == Node.ELEMENT_NODE) {
				Element gBElement = (Element) gBNode;
				GroundBlock groundBlock = new GroundBlock(
						Double.valueOf(gBElement.getAttribute("start")),
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

	public Double getDeathLevel() {
		return deathLevel;
	}

}
