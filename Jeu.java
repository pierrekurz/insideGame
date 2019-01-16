
public class Jeu {// TODO Auto-generated constructor stub
	//tableau avec x = longueur, y = largeur, z = hauteur
	public static final int [][][]valide = 	
		{		// les 1 correspondent à des cases où on peut placer les cubes
				// les 0 
				{//en x = 0
					{1, 1, 1, 1, 1/* -> sommet pyramide*/},/*y=0*/
					{1, 1, 1, 1, 0},/*y=1*/
					{1, 1, 1, 0, 0},/*y=2*/
					{1, 1, 0, 0, 0},/*y=3*/
					{1, 0, 0, 0, 0} /*y=4*/
				},
				{//en x = 1
					{1, 1, 1, 1, 0},/*y=0*/
					{1, 1, 1, 0, 0},/*y=1*/
					{1, 1, 0, 0, 0},/*y=2*/
					{1, 0, 0, 0, 0},/*y=3*/
					{0, 0, 0, 0, 0} /*y=4*/
				},
				{//en x = 2
					{1, 1, 1, 0, 0},/*y=0*/
					{1, 1, 0, 0, 0},/*y=1*/
					{1, 0, 0, 0, 0},/*y=2*/
					{0, 0, 0, 0, 0},/*y=3*/
					{0, 0, 0, 0, 0} /*y=4*/
				},
				{//en x = 3
					{1, 1, 0, 0, 0},/*y=0*/
					{1, 0, 0, 0, 0},/*y=1*/
					{0, 0, 0, 0, 0},/*y=2*/
					{0, 0, 0, 0, 0},/*y=3*/
					{0, 0, 0, 0, 0} /*y=4*/
				},
				{//en x = 4
					{1, 0, 0, 0, 0},/*y=0*/
					{0, 0, 0, 0, 0},/*y=1*/
					{0, 0, 0, 0, 0},/*y=2*/
					{0, 0, 0, 0, 0},/*y=3*/
					{0, 0, 0, 0, 0} /*y=4*/
				}
		};
	
	public int [][][] placement = new int [5][5][5];;

	public Jeu() {
		
	}
	
	public void placementValide (int x, int y , int z) {
		// on considère que x, y et z ont comme valeur les coordonnées réelles du tableau
		if ( x<0 || x>4 || y<0 || y>4 || z<0 || z>4) {
			System.out.println("Placement non valide !");
			return;
		}
		
		if () {
			
		}
	}

}
//TODO Auto-generated constructor stub