import javax.swing.ImageIcon;

/**
 * @author Sebas Lavigne
 */
public class SweeperIcons {
	
	/*
	 * http://haikudou.blogspot.com/2015/01/a-haiku-minesweeper-update-and-theme.html
	 * 
	 */

	private ImageIcon[] numTile;
	private ImageIcon mineTile;
	
	private ImageIcon unmarked;
	private ImageIcon flag;
	private ImageIcon question;
	
	public SweeperIcons() {
		numTile = new ImageIcon[9];
		for (int i = 0; i < numTile.length; i++) {
			numTile[i] = new ImageIcon("icon/mine"+i+".png");
		}
		mineTile = new ImageIcon("icon/mine.png");
		
		unmarked = new ImageIcon("icon/unmarked.png");
		flag = new ImageIcon("icon/flag.png");
		question = new ImageIcon("icon/question.png");
	}
	
	public ImageIcon getNumTile(int num) {
		return numTile[num];
	}
	
	public ImageIcon getMineTile() {
		return mineTile;
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
	
}
