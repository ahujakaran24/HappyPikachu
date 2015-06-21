import java.awt.Rectangle;

public class Projectile {

	
	private int x,y,speedX;
	private boolean visible;
	private Rectangle r;
	
	public Projectile(int startX, int startY){
		x=startX;
		y=startY;
		speedX = 7;
		visible = true;
		r = new Rectangle(0,0,0,0);
		
	}
	
	public void update(){
		x += speedX;
		r.setBounds(x, y, 10, 5); //collision rect
		if(x > 800){
			visible = false;
			r = null;
		}
        if(x<800){
        	checkCollision();
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	private void checkCollision() {
		if(r.intersects(StartActivity.g1.r)){
			visible = false;
			
			if (StartActivity.g1.health > 0) {
				StartActivity.g1.health -= 1;
			}
			if (StartActivity.g1.health == 0) {
				StartActivity.g1.setCenterX(-100);
				StartActivity.score += 5;
			}
		}
		if (r.intersects(StartActivity.g2.r)){
			visible = false;
			if (StartActivity.g2.health > 0) {
				StartActivity.g2.health -= 1;
			}
			if (StartActivity.g2.health == 0) {
				StartActivity.g2.setCenterX(-100);
				StartActivity.score += 5;
			}

		}
    }
}
