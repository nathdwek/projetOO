package sonic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import sonic.model.Coin;
import sonic.view.Drawable;

public class CoinSprite implements Drawable {

	private Coin coin;
	private Image sprite = Toolkit.getDefaultToolkit().getImage("src/sonic/sprites/coins.gif");

	public CoinSprite(Coin coin){
		this.coin = coin;
	}

	public void draw(Graphics g, JPanel p, Integer left, Integer top,
			Integer windowWidth, Integer windowHeight) {
		int posX =  coin.getPosition().getX().intValue();
		int posY = coin.getPosition().getY().intValue();
		int thisLeft = posX-coin.getSize(2).intValue()- left;
		int width = Double.valueOf(coin.getSize(2)+coin.getSize(0)).intValue();
		int thisTop = posY+coin.getSize(1).intValue()- top;
		int height = Double.valueOf(coin.getSize(1)+coin.getSize(3)).intValue();
		g.drawImage(sprite ,thisLeft  , windowHeight-thisTop,width,height, p);
		g.finalize();

	}

}
