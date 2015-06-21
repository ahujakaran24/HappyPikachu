import java.awt.Image;
import java.awt.Rectangle;

public class Tile {

    private int tileX, tileY, speedX, type;
    public Image tileImage;

    private Background bg = StartActivity.getBg1();
    private Robot robot = StartActivity.getRobot();
    
    private Rectangle r;

    public Tile(int x, int y, int typeInt) {
        tileX = x * 40; //Max 800 i.e x = 20
        tileY = y * 40; // Max 480 i.e. y = 12

        type = typeInt;
        
        r = new Rectangle();

        if (type == 5) {
            tileImage = StartActivity.tiledirt;
        } else if (type == 8) {
            tileImage = StartActivity.tilegrassTop;
        } else if (type == 4) {
            tileImage = StartActivity.tilegrassLeft;

        } else if (type == 6) {
            tileImage = StartActivity.tilegrassRight;

        } else if (type == 2) {
            tileImage = StartActivity.tilegrassBot;
        }else{
        	type = 0;
        }
        
      /*  if (type == 1) {
            tileImage = StartActivity.tileocean;
        } else if (type == 2) {

            tileImage = StartActivity.tiledirt;
        }*/

    }

    public void update() {
        // TODO Auto-generated method stub
    	
    	  speedX = bg.getSpeedX() * 5;
          tileX += speedX;
          
          r.setBounds(tileX, tileY, 40, 40);
          
          if (r.intersects(Robot.yellowRed) && type != 0){
              checkVerticalCollision(Robot.rect, Robot.rect2);
              checkSideCollision(Robot.rect3, Robot.rect4, Robot.footleft, Robot.footright);
          }
          
          
          //If robot bottom is not on tile && robot is not jumping
          if(tileX>0&&!Robot.rect2.intersects(r)&&!robot.isJumped()){
        	  robot.setSpeedY(5);
        	  
          }
          
          
    }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }

    
    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot){
    	if (rtop.intersects(r)){
    		//upper collision
    		  robot.setSpeedY(4);
    		  robot.setFalling(true);
    	}
    	
    	if (rbot.intersects(r)){
    		//lower collision
    		
    		 robot.setJumped(false);
    		  robot.setFalling(false);
             robot.setSpeedY(0);  //stop falling
             robot.setCenterY(tileY - 63); //20 (mid tile) + 43 (mid robo)
    	}
    	
    	
    }
    
    public void checkSideCollision(Rectangle rleft, Rectangle rright, Rectangle leftfoot, Rectangle rightfoot) 
    {
        if (type != 5 && type != 2 && type != 0){
            if (rleft.intersects(r)) {
                robot.setCenterX(tileX + 102);
                robot.setSpeedX(0);
    
            }else if (leftfoot.intersects(r)) {
                robot.setCenterX(tileX + 85);
                robot.setSpeedX(0);
            }
            
            if (rright.intersects(r)) {
                robot.setCenterX(tileX - 62);
                robot.setSpeedX(0);
            }
            
            else if (rightfoot.intersects(r)) {
                robot.setCenterX(tileX - 45);
                robot.setSpeedX(0);
            }
        }
}
}