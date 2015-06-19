
public class Robot {
	
	private int CenterX = 100;
	private int CenterY = 382;
	private boolean jumped = false;
	
	private int SpeedX = 0;
	private int SpeedY = 1;
	
	
	//Metal Slug Algorithm
	public void update()
	{
		
		//X AXIS
		
		if(SpeedX < 0)  //Moving left   <-----
		{
			CenterX += SpeedX;
		}else if(SpeedX == 0){    //Stable on X   -0-
			//No background scroll
		}else{                       //Moving Right  ---->
				if(CenterX <= 150){       // --here on screen---150
					CenterX += SpeedX;      //Update x --->
				}else{
					//Back scroll            
				}		
		}
		
		
		//Y AXIS
		
		if(CenterY + SpeedY >= 382){   //Threshold reached
			CenterY = 382;                
		}else{
			CenterY+=SpeedY; //Keep going below
		}
		
		
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
			
			if(CenterY+SpeedY >=382){
				CenterY = 382;
				SpeedY = 0;
				jumped = false;
			}
		}
		
		//Prevent beyond going <---- X 0
		
		if(CenterX +SpeedX <=60)
		{
			CenterX = 61;   //Where are you running off too? o_0 (can't go back <--)
			 
		}
		
	}
	
	//Update() over
	
	public void moveRight(){
		//Keep going ---> Xs
		SpeedX = 6;
	}
	
	public void moveLeft(){
		//Keep going <---- X
		SpeedX = -6;
	}
	
	public void stop(){
		SpeedX = 0;
	}
	
	public void jump(){
		if(jumped == false){
			SpeedY = -15;   // Go up !!
			jumped = true;
			
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
	
	

}
