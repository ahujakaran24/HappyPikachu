import java.awt.Rectangle;
import java.util.ArrayList;


public class Robot {
	
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	
	private int CenterX = 100;
	private int CenterY = 382;
	private boolean jumped = false;
	private boolean falling = false;
	
	private int SpeedX = 0; 
	private int SpeedY = 0;
	
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean readyToFire = true;
	
	public static Rectangle rect = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect3 = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect4 = new Rectangle(0, 0, 0, 0);
	public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);
	public static Rectangle footleft = new Rectangle(0,0,0,0);
	public static Rectangle footright = new Rectangle(0,0,0,0);
	
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	
	
	
	
	//Metal Slug Algorithm
	public void update()
	{
		//X AXIS
		if(SpeedX < 0)  //Moving left   <-----
		{
			CenterX += SpeedX;
		}
		
		if(SpeedX <= 0)
		{    //Stable on X   -0-
			//No background scroll
			StartActivity.getBg1().setSpeedX(0);
			StartActivity.getBg2().setSpeedX(0);
		}                      //Moving Right  ---->
		
		if(CenterX <= 200 && SpeedX > 0)
		{       // --here on screen---200
					CenterX += SpeedX;      //Update x --->
					//Free movement behind 200 px of x axis
		}
		if(SpeedX>0 && CenterX>200){
					//Back scroll, just crossed free movement zone
					StartActivity.getBg1().setSpeedX(-MOVESPEED/5);
					StartActivity.getBg2().setSpeedX(-MOVESPEED/5);
				}		
		
		//Y AXIS
		CenterY += SpeedY;  //SpeedY will be -ve or +ve. Keep in mind the axis
		//For when you hit the ground back 
		/*if(CenterY + SpeedY >= GROUND){  
			CenterY = GROUND;                
		}*/
		/*   (0,0)  ---------------> (x,0)
		 *    |
		 *    |   //Karan's first screen
		 *    |
		 *   (0,y)
		 * 
		 * */
		
		//JUMPS
		if(jumped == true){
			SpeedY += 1;
			
			if(SpeedY > 3)
				jumped=true;
			/*if(CenterY+SpeedY >=GROUND){
				CenterY = GROUND;
				SpeedY = 0;
				jumped = false;
			}*/
		}
		
		//Prevent beyond going <---- X 0
		if(CenterX +SpeedX <=60)
		{
			CenterX = 61;   //Where are you running off too? o_0 (can't go back <--)
		}
		
		rect.setRect(CenterX - 34, CenterY - 63	, 68, 63);
		rect2.setRect(rect.getX(), rect.getY() + 63, 68, 63);
		rect3.setRect(rect.getX() - 26, rect.getY()+32, 26, 20);
		rect4.setRect(rect.getX() + 68, rect.getY()+32, 26, 20);
		yellowRed.setRect(CenterX - 100, CenterY - 100, 180, 180);
		footleft.setRect(CenterX - 50, CenterY + 20, 50, 15);
		footright.setRect(CenterX, CenterY + 20, 50, 15);
	}
	
	//Update() over
	
	public void moveRight(){
		//Keep going ---> Xs
		if(!ducked)
		SpeedX = MOVESPEED;
	}
	
	public void moveLeft(){
		//Keep going <---- X
		if(!ducked)
		SpeedX = -MOVESPEED;
	}
	
	public void stopRight(){
		setMovingRight(false);
		stop();
	}
	
	public void stopLeft(){
		setMovingLeft(false);
		stop();
	}
	
	
	public void stop(){
		if(!isMovingRight() && !isMovingLeft())
		{
			SpeedX = 0;
		}
		
		if(!isMovingRight() && isMovingLeft())
		{
			moveLeft();
		}
		
		if(isMovingRight() && !isMovingLeft())
		{
			moveRight();
		}
	}
	
	public void jump(){
		if(jumped == false){
			SpeedY = JUMPSPEED;   // Go up !!
			jumped = true;
		}
	}
	

	public void shoot(){
		if(readyToFire){
		Projectile p  = new Projectile(getCenterX()+61 , CenterY-25);
		projectiles.add(p);
		}
	}
	
	//Getter & Setter:
	
	
	public int getCenterX() {
		return CenterX;
	}

	public void setCenterX(int centerX) {
		CenterX = centerX;
	}

	public int getCenterY() {
		return CenterY;
	}

	public void setCenterY(int centerY) {
		CenterY = centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public int getSpeedX() {
		return SpeedX;
	}

	public void setSpeedX(int speedX) {
		SpeedX = speedX;
	}

	public int getSpeedY() {
		return SpeedY;
	}

	public void setSpeedY(int speedY) {
		SpeedY = speedY;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(ArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	

}
