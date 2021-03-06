import javax.swing.ImageIcon;

/**
 * @author Sebas Lavigne
 */
public class SweeperIcons {
	
	/*
	 * http://haikudou.blogspot.com/2015/01/a-haiku-minesweeper-update-and-theme.html
	 * 
	 */
	
	public static final int BASE = 0;
	public static final int WORRY = 1;
	public static final int WIN = 2;
	public static final int LOSE = 3;

	private ImageIcon[] numTile;
	private ImageIcon mineTile;
	private ImageIcon mineHitTile;
	
	private ImageIcon unmarked;
	private ImageIcon flag;
	private ImageIcon question;
	
	private ImageIcon[] smiley;
	private ImageIcon[] smileyDown;
	
	public SweeperIcons() {
		numTile = new ImageIcon[9];
		for (int i = 0; i < numTile.length; i++) {
			numTile[i] = new ImageIcon(Principal.class.getResource("icon/mine"+i+".png"));
		}
		mineTile = new ImageIcon(Principal.class.getResource("icon/mine.png"));
		mineHitTile = new ImageIcon(Principal.class.getResource("icon/hit.png"));
		
		unmarked = new ImageIcon(Principal.class.getResource("/icon/unmarked.png"));
		flag = new ImageIcon(Principal.class.getResource("icon/flag.png"));
		question = new ImageIcon(Principal.class.getResource("icon/question.png"));
		
		smiley = new ImageIcon[4];
		smiley[BASE] = new ImageIcon(Principal.class.getResource("icon/smiley-base.png"));
		smiley[WORRY] = new ImageIcon(Principal.class.getResource("icon/smiley-worry.png"));
		smiley[WIN] = new ImageIcon(Principal.class.getResource("icon/smiley-win.png"));
		smiley[LOSE] = new ImageIcon(Principal.class.getResource("icon/smiley-lose.png"));
		
		smileyDown = new ImageIcon[4];
		smileyDown[BASE] = new ImageIcon(Principal.class.getResource("icon/smiley-basedown.png"));
		smileyDown[WORRY] = new ImageIcon(Principal.class.getResource("icon/smiley-worry.png"));
		smileyDown[WIN] = new ImageIcon(Principal.class.getResource("icon/smiley-windown.png"));
		smileyDown[LOSE] = new ImageIcon(Principal.class.getResource("icon/smiley-losedown.png"));
		
	}
	
	public ImageIcon getNumTile(int num) {
		return numTile[num];
	}
	
	public ImageIcon getMineTile() {
		return mineTile;
	}
	
	public ImageIcon getMineHitTile() {
		return mineHitTile;
	}
	
	public ImageIcon unmarked() {
		return unmarked;
	}
	
	public ImageIcon flag() {
		return flag;
	}
	
	public ImageIcon question() {
		return question;
	}
	
	public ImageIcon getSmiley(int index) {
		return smiley[index];
	}
	
	public ImageIcon getSmileyDownVersion(int index) {
		return smileyDown[index];
	}
	
}
