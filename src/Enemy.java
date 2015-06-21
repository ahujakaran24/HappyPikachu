import java.awt.Rectangle;


public class Enemy {

	private int maxHealth, currentHealth, power, speedX, speedY=1, centerX,
			centerY;
	
	public int health = 5;
	
	private boolean once = false;
	
	public Rectangle r = new Rectangle(0,0,0,0);
	
	private boolean up = false;

	private Background bg = StartActivity.getBg1();

	private Robot robot = StartActivity.getRobot();
	
	public void update() {
		  centerY+= speedY;
		  centerX+= speedX;
		
		if(/*centerY == 60 ||*/ centerY == 0||centerX == 100)
		{
		  up=false;
		  speedY = 1;
		  
		}
		else if(/*centerY == 200||*/ centerY==400||centerX == 800)
		{
			up=true;
			speedY = -1	;
			 
		} else if(robot.isDucked()){
			 speedX = 1;
		}else{
			
			if (robot.getCenterX() >= centerX) {
			 speedX = bg.getSpeedX() * 2 + 1;
			} else {
				 speedX = bg.getSpeedX() * 2 - 1;
			}
		}
		
        r.setBounds(centerX - 25, centerY-25, 20, 20);
		
		if (r.intersects(Robot.yellowRed)){
			checkCollision();
		}
	}

	public void die() {

	}

	public void attack() {

	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
	
	private void checkCollision() {
		if (r.intersects(Robot.rect) || r.intersects(Robot.rect2) || r.intersects(Robot.rect3) || r.intersects(Robot.rect4)){
			System.out.println("collision");
			if(!robot.isDucked())
			StartActivity.state = StartActivity.GameState.Dead;
			}
		}
	
}
