import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import Framework.Animation;


public class StartActivity extends Applet implements Runnable, KeyListener {
	
	private static Robot robot;
	
	static enum GameState {
		Running, Dead
	}
	
	public static GameState state = GameState.Running;
	
	private long i =0;
	
	public static Gastly g1,g2;
	
	public static int score = 0;
    private Font font = new Font(null, Font.BOLD, 30);
	
	private long random;
	
	private Image image, character, background, gastly,  currentSprite, characterjumped, characterForward, characterDuck
	,character2,character3,gastly2,gastly3,gastly4,gastly5;
	
	 public static Image tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, tiledirt;
	
	private Graphics second;
	
	private URL base;
	
	private Animation anim,hanim;
	
	private static Background bg1, bg2;
	
	private ArrayList<Tile> tilearray = new ArrayList<Tile>();
	
	@Override
	public void init(){
		
		setSize(800,480);
		setBackground(Color.WHITE);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-Bot-Alpha");
		
		try{
			base = getDocumentBase();
		}catch( Exception e ){
			
		}
		character = getImage(base, "data/character.png");
		
		characterDuck = getImage(base, "data/down.png");
		characterForward = getImage(base, "data/character.png");
		character2 = getImage(base, "data/character2.png");
		character3 = getImage(base, "data/character3.png");
		characterjumped = getImage(base, "data/jumped.png");
		gastly = getImage(base, "data/heliboy.png");
		gastly2 = getImage(base, "data/heliboy2.png");
		gastly3= getImage(base, "data/heliboy3.png");
		gastly4 = getImage(base, "data/heliboy4.png");
		gastly5 = getImage(base, "data/heliboy5.png");
		
		//currentSprite = character;
		
		background = getImage(base, "data/background.png");
		
		//tileocean = getImage(base, "data/tileocean.png");
		
	    	tiledirt = getImage(base, "data/tiledirt.png");
	        tilegrassTop = getImage(base, "data/tilegrasstop.png");
	        tilegrassBot = getImage(base, "data/tilegrassbot.png");
	        tilegrassLeft = getImage(base, "data/tilegrassleft.png");
	        tilegrassRight = getImage(base, "data/tilegrassright.png");
		
		
		//Animation!!
		
		anim = new Animation();
		anim.addFrame(characterForward, 500);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);
		
		
		hanim = new Animation();
		
		hanim.addFrame(gastly, 100);
		hanim.addFrame(gastly2, 100);
		hanim.addFrame(gastly3, 100);
		hanim.addFrame(gastly4, 100);
		hanim.addFrame(gastly5, 100);
		hanim.addFrame(gastly4, 100);
		hanim.addFrame(gastly3, 100);
		hanim.addFrame(gastly2, 100);
		
		currentSprite = anim.getImage();
		
	}
	
	@Override
	public void start(){
		
		bg1 = new Background(0,0);
		bg2 = new Background(2160,0);
		robot = new Robot();
		g1 = new Gastly(340,100);
		g2 = new Gastly(700,200);
		
	/*	for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 12; j++) {

				if (j == 11) {
					Tile t = new Tile(i, j, 2); //land
					tilearray.add(t);

				} if (j == 10) {
					Tile t = new Tile(i, j, 1); //ocean
					tilearray.add(t);

				}
			}
		}*/
		
	    try {
            loadMap("data/map1.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	private void loadMap(String filename) throws IOException{
		// TODO Auto-generated method stub
		
		  ArrayList lines = new ArrayList();
	        int width = 0;
	        int height = 0;

	        BufferedReader reader = new BufferedReader(new FileReader(filename));
	        
	        while (true) {
	            String line = reader.readLine();
	            // no more lines to read
	            if (line == null) {
	                reader.close();
	                break;
	            }
	            if (!line.startsWith("!")) {
	                lines.add(line);
	                width = Math.max(width, line.length());
	            }
	        }
	        height 
	        = lines.size();
		
	        for (int j = 0; j < 12; j++) {
	            String line = (String) lines.get(j);
	            for (int i = 0; i < width; i++) {
	                System.out.println(i + "is i ");
	                if (i < line.length()) {
	                    char ch = line.charAt(i);
	                    Tile t = new Tile(i, j, Character.getNumericValue(ch));
	                    tilearray.add(t);
	                }

	            }
	        }
	}

	@Override
	public void stop(){
	}
	
	@Override
	public void destroy(){
		
	}
	

	@Override
	public void run() {
		
		if(state == GameState.Running)
		{
		
		//Game loop
		while(true){
			
		 
			if(robot.isJumped() || robot.isFalling()){
				currentSprite = characterjumped;
			}else if(robot.isDucked()){
				currentSprite = characterDuck;
			}else{
				currentSprite = anim.getImage();
			}
				
			ArrayList projectiles = robot.getProjectiles();
			
			for(int i = 0;i < projectiles.size(); i++){
				Projectile p = (Projectile) projectiles.get(i);
				
				if(p.isVisible()){
					p.update();
				}else{
					projectiles.remove(i);
					
				   //Memory deallocate how?
				}
				
			}
			
			g1.update();
			g2.update();
			bg1.update();
			bg2.update();
			robot.update();
			
			updateTiles();
			animate();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (robot.getCenterY() > 500) {
				state = GameState.Dead;
			}
			
		}//loop
	}//if
		
	}

	//Called in game loop by repaint();
	//double buffering --> Saving last instance of screen to reduce/remove flickering
		@Override 
		public void update(Graphics g){
			if(image == null)
			{
				//used in Double Buffering
				image = createImage(this.getWidth(), this.getHeight());
				second = image.getGraphics();
			}
			
			second.setColor(getBackground());
			second.fillRect(0, 0, getWidth(), getHeight());
			second.setColor(getForeground());
			
			paint(second);
			enemy(second);
		
		
			
			
			
			
			g.drawImage(image, 0, 0, this);
			
			
		}
		
		//Called from update(Graphics)
		 @Override
		public void paint(Graphics g){
			if (state == GameState.Running) {
			g.drawImage(background, bg1.getBgX(), bg1.getBgY(),this);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(),this);
			paintTiles(g);

			
			ArrayList projectiles = robot.getProjectiles();
			
			for(int i = 0; i < projectiles.size(); i++){
				Projectile p = (Projectile)projectiles.get(i);
		     	g.setColor(Color.YELLOW);
				g.fillRect(p.getX(), p.getY(), 10, 5);
			}
			
			/*g.drawRect((int)robot.rect.getX(), (int)robot.rect.getY(), (int)robot.rect.getWidth(), (int)robot.rect.getHeight());
			g.drawRect((int)robot.rect2.getX(), (int)robot.rect2.getY(), (int)robot.rect2.getWidth(), (int)robot.rect2.getHeight());
			g.drawRect((int)robot.rect3.getX(), (int)robot.rect3.getY(), (int)robot.rect3.getWidth(), (int)robot.rect3.getHeight());
			g.drawRect((int)robot.rect4.getX(), (int)robot.rect4.getY(), (int)robot.rect4.getWidth(), (int)robot.rect4.getHeight());
			g.drawRect((int)robot.yellowRed.getX(), (int)robot.yellowRed.getY(), (int)robot.yellowRed.getWidth(), (int)robot.yellowRed.getHeight());
			g.drawRect((int)robot.footleft.getX(), (int)robot.footleft.getY(), (int)robot.footleft.getWidth(), (int)robot.footleft.getHeight());
			g.drawRect((int)robot.footright.getX(), (int)robot.footright.getY(), (int)robot.footright.getWidth(), (int)robot.footright.getHeight());*/
			
			
			g.drawImage(currentSprite, robot.getCenterX()-61,robot.getCenterY()-63, this);
			
			
			g.drawImage(hanim.getImage(),g1.getCenterX()-48, g1.getCenterY()-48,this );
			g.drawImage(hanim.getImage(),g2.getCenterX()-48, g2.getCenterY()-48,this );
			
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 740, 30);	
		}else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 360, 240);
		}
			
			
		}
		
		//Called from update(Graphics)
		public void enemy(Graphics g){
			
		}

	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			    
				robot.jump();
			break;
		
		case KeyEvent.VK_DOWN:
			
			if(!robot.isJumped())
			{
				robot.setDucked(true);
				robot.setSpeedX(0);
			}
			break;
			
		case KeyEvent.VK_LEFT:
			robot.moveLeft();
			robot.setMovingLeft(true);
			break;
			
		case KeyEvent.VK_RIGHT:
			robot.moveRight();
			robot.setMovingRight(true);
			break;
			
		case KeyEvent.VK_CONTROL:
			if(!robot.isDucked() && !robot.isJumped()){
				robot.shoot();
				robot.setReadyToFire(false);
			}
			break;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			break;
		
		case KeyEvent.VK_DOWN:
			currentSprite = anim.getImage();
			robot.setDucked(false);
			break;
			
		case KeyEvent.VK_LEFT:
			robot.stopLeft();
			break;
			
		case KeyEvent.VK_RIGHT:
			robot.stopRight();
			break;
			
		case KeyEvent.VK_CONTROL:
			
			robot.setReadyToFire(true);
			break;
		}
		
	}

	public static Background getBg1() {
		return bg1;
	}

	public static void setBg1(Background bg1) {
		StartActivity.bg1 = bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static void setBg2(Background bg2) {
		StartActivity.bg2 = bg2;
	}
	
	
	//Getter and Setter
	
	//animate
	
	public void animate(){
		anim.update(10);
		hanim.update(50);
	}
	
	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}
	}
	
	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}

	public static Robot getRobot() {
		return robot;
	}

	public static void setRobot(Robot robot) {
		StartActivity.robot = robot;
	}
	
	
}
