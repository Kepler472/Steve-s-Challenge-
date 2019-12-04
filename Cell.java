
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Cell {
	
	private CellType type;
	
	private Collectable item;
	
	private ImageView cellImage;
	
	private ImageView itemImage;
	
	private static final String GRID_IMAGES = "";
	
	public Cell(CellType type, Collectable item){

		this.type = type;
		this.item = item;

		try {
			FileInputStream fis = null;
			
			// sets the cellImage
			switch (type) {
			case WALL :
				fis = new FileInputStream(GRID_IMAGES + "wall.png");
				break;
			case DOOR :
				fis = new FileInputStream(GRID_IMAGES + "door.png");
				break;
			case FIRE:
				fis = new FileInputStream(GRID_IMAGES + "fire.png");
				break;
			case WATER :
				fis = new FileInputStream(GRID_IMAGES + "water.jpg");
				break;
			case GOAL :
				fis = new FileInputStream(GRID_IMAGES + "goal.png");
				break;
			case TELEPORTER:
				fis = new FileInputStream(GRID_IMAGES + "teleporter.png");
				break;
			default:
				fis = new FileInputStream(GRID_IMAGES + "floor.png");    
			}
			
			cellImage = new ImageView(new Image(fis));
			
			// sets the item image
			if(item != null) {
				switch (item) {
				case KEY :
					fis = new FileInputStream(GRID_IMAGES + "key.png");
					break;
				case FIRE_BOOTS :
					fis = new FileInputStream(GRID_IMAGES + "fire_boots.png");
					break;
				case WATER_BOOTS :
					fis = new FileInputStream(GRID_IMAGES + "water_boots.png");
					break;
				case LIFE :
					fis = new FileInputStream(GRID_IMAGES + "life.png");
					break;
				default: 
					fis = new FileInputStream(GRID_IMAGES + "empty.png");    
				}
				itemImage = new ImageView(new Image(fis));
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: could not load image(s).");
			e.printStackTrace();
			System.exit(-1);
		}

	}
	
	public void removeItem() {
		this.item = null;
	}

	public Collectable getItem() {
		return item;
	}

	public void setItem(Collectable item) {
		this.item = item;
	}

	public CellType getType() {
		return type;
	}

	public void setType(CellType type) {
		this.type = type;
	}

	public ImageView getCellImage() {
		return cellImage;
	}

	public void setCellImage(ImageView cellImage) {
		this.cellImage = cellImage;
	}

	public ImageView getItemImage() {
		return itemImage;
	}

	public void setItemImage(ImageView itemImage) {
		this.itemImage = itemImage;
	}

}
