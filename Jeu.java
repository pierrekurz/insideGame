import java.util.Scanner;

public class Jeu {// TODO Auto-generated constructor stub
	//tableau avec x = longueur, y = largeur, z = hauteur
	public static final int [][][]valide = 	
		{		// les 1 correspondent à des cases où on peut placer les cubes
				// les 0 
				{//en x = 0
					{1, 1, 1, 1, 1 /* -> sommet pyramide*/},/*y=0*/
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
	
	// a modifier si on prend des points avec des coordonnées
	public static int [][][] placement = {
			{//en x = 0
				{5, 5, 5, 5, 5 /* -> sommet pyramide*/},/*y=0*/
				{5, 5, 5, 5, 5 },/*y=1*/
				{5, 5, 5, 5, 5 },/*y=2*/
				{5, 5, 5, 5, 5 },/*y=3*/
				{5, 5, 5, 5, 5 } /*y=4*/
			},
			{//en x = 1
				{5, 5, 5, 5, 5 },/*y=0*/
				{5, 5, 5, 5, 5 },/*y=1*/
				{5, 5, 5, 5, 5 },/*y=2*/
				{5, 5, 5, 5, 5 },/*y=3*/
				{5, 5, 5, 5, 5 } /*y=4*/
			},
			{//en x = 2
				{5, 5, 5, 5, 5 },/*y=0*/
				{5, 5, 5, 5, 5 },/*y=1*/
				{5, 5, 5, 5, 5 },/*y=2*/
				{5, 5, 5, 5, 5 },/*y=3*/
				{5, 5, 5, 5, 5 } /*y=4*/
			},
			{//en x = 3
				{5, 5, 5, 5, 5 },/*y=0*/
				{5, 5, 5, 5, 5 },/*y=1*/
				{5, 5, 5, 5, 5 },/*y=2*/
				{5, 5, 5, 5, 5 },/*y=3*/
				{5, 5, 5, 5, 5 } /*y=4*/
			},
			{//en x = 4
				{5, 5, 5, 5, 5 },/*y=0*/
				{5, 5, 5, 5, 5 },/*y=1*/
				{5, 5, 5, 5, 5 },/*y=2*/
				{5, 5, 5, 5, 5 },/*y=3*/
				{5, 5, 5, 5, 5 } /*y=4*/
			}
	};
	
	public static int xbis, ybis, zbis;
	
	//le joueur 1 a 13 cubes clairs et 5 cubes foncés
	public static int [] J1 = {13, 5};
	public static int [] J2 = {5, 13};
	
	//le joueur 2 a 13 cubes foncés et 5 cubes clairs

	public Jeu() {
	}
	
	//vérifi si on a le droit de placer un cube à l'endroit indiqué
	public static boolean placementValide (int x, int y , int z, int typeCube) {
		// on considère que x, y et z ont comme valeur les coordonnées réelles du tableau (entre 0 et 4)
		
		// si le joueur n'a plus de cube de cette couleur :
		if (typeCube == 0) {
			System.out.println("Placement non valide !");
			return false;
		}
		
		// si les valeur données sont trop grande, trop petite, invalide ou que le point est déjà pris 
		if ( x<0 || x>4 || y<0 || y>4 || z<0 || z>4 || valide [x][y][z] == 0 || placement[x][y][z] != 5) {
			System.out.println("Placement non valide !");
			return false;
		}
		// si la somme de x, y et z fait moins de 4 (ne fait pas parti de la base de la pyramide) :
		if (x + y + z < 4 ) {
			//si les cubes dont on a besoin pour placer le nouveau cube ne sont pas tous présent :
			if ((placement[x+1][y][z] == 5) || (placement[x][y+1][z] == 5) || (placement[x][y][z+1] == 5)) {
				System.out.println("Placement non valide !");
				return false;
			}
		}
		return true;
	}
	
	//dans le cas où on forme un triangle de la même couleur :
	public static boolean triangleCouleurForme (int x, int y, int z) {
		if (x == 0) {
			xbis = 0;
			if (y==0) {
				ybis = 0;
				if (z!=0) {
					zbis = z-1;
					//si les trois cubes forment un triangle de la même couleur
					if (	(placement[x+1][y][zbis] == 1) && (placement[x][y+1][zbis] == 1) && (placement[x][y][zbis+1] == 1) ||
							(placement[x+1][y][zbis] == 0) && (placement[x][y+1][zbis] == 0) && (placement[x][y][zbis+1] == 0)) {
						return true;
					}
				}
			} else {
				ybis = y-1;
				if (z==0) {
					zbis = 0;
					if (	(placement[x+1][ybis][z] == 1) && (placement[x][ybis+1][z] == 1) && (placement[x][ybis][z+1] == 1) ||
							(placement[x+1][ybis][z] == 0) && (placement[x][ybis+1][z] == 0) && (placement[x][ybis][z+1] == 0)) {
						return true;
					}
				}else {
					zbis = z-1;
					if (	(placement[x+1][ybis][z] == 1) && (placement[x][ybis+1][z] == 1) && (placement[x][ybis][z+1] == 1) ||
							(placement[x+1][ybis][z] == 0) && (placement[x][ybis+1][z] == 0) && (placement[x][ybis][z+1] == 0)) {
						zbis = z;
						return true;
					}
					else if ((placement[x+1][y][zbis] == 1) && (placement[x][y+1][zbis] == 1) && (placement[x][y][zbis+1] == 1) ||
							 (placement[x+1][y][zbis] == 0) && (placement[x][y+1][zbis] == 0) && (placement[x][y][zbis+1] == 0)) {
						ybis = y;
						return true;
					}
				}
			}
		} else {
			xbis = x-1;
			if (y==0) {
				ybis = 0;
				if (z==0) {
					zbis = 0;
					if (	(placement[xbis+1][y][z] == 1) && (placement[xbis][y+1][z] == 1) && (placement[xbis][y][z+1] == 1) ||
							(placement[xbis+1][y][z] == 0) && (placement[xbis][y+1][z] == 0) && (placement[xbis][y][z+1] == 0)) {
						return true;
					}
				} else {
					zbis = z-1;
					//si les trois cubes forment un triangle de la même couleur
					if (	(placement[xbis+1][y][z] == 1) && (placement[xbis][y+1][z] == 1) && (placement[xbis][y][z+1] == 1) ||
							(placement[xbis+1][y][z] == 0) && (placement[xbis][y+1][z] == 0) && (placement[xbis][y][z+1] == 0)) {
						zbis = z;
						return true;
					}
					else if ((placement[x+1][y][zbis] == 1) && (placement[x][y+1][zbis] == 1) && (placement[x][y][zbis+1] == 1) ||
							 (placement[x+1][y][zbis] == 0) && (placement[x][y+1][zbis] == 0) && (placement[x][y][zbis+1] == 0)){
						xbis = x;
						return true;
					}
				}
			} else {
				ybis = y-1;
				if (z==0) {
					zbis = 0;
					if (	(placement[xbis+1][y][z] == 1) && (placement[xbis][y+1][z] == 1) && (placement[xbis][y][z+1] == 1) ||
							(placement[xbis+1][y][z] == 0) && (placement[xbis][y+1][z] == 0) && (placement[xbis][y][z+1] == 0)) {
						ybis = y;
						return true;
					}
					else if ((placement[x+1][ybis][z] == 1) && (placement[x][ybis+1][z] == 1) && (placement[x][ybis][z+1] == 1) ||
							 (placement[x+1][ybis][z] == 0) && (placement[x][ybis+1][z] == 0) && (placement[x][ybis][z+1] == 0)) {
						xbis = x;
						return true;
					}
				}else {
					zbis = z-1;
					if (	(placement[xbis+1][y][z] == 1) && (placement[xbis][y+1][z] == 1) && (placement[xbis][y][z+1] == 1) ||
							(placement[xbis+1][y][z] == 0) && (placement[xbis][y+1][z] == 0) && (placement[xbis][y][z+1] == 0)) {
						ybis = y;
						zbis = z;
						return true;
					}
					else if((placement[x+1][ybis][z] == 1) && (placement[x][ybis+1][z] == 1) && (placement[x][ybis][z+1] == 1) ||
							(placement[x+1][ybis][z] == 0) && (placement[x][ybis+1][z] == 0) && (placement[x][ybis][z+1] == 0)) {
						xbis = x;
						zbis = z;
						return true;
					}
					else if((placement[x+1][y][zbis] == 1) && (placement[x][y+1][zbis] == 1) && (placement[x][y][zbis+1] == 1) ||
							(placement[x+1][y][zbis] == 0) && (placement[x][y+1][zbis] == 0) && (placement[x][y][zbis+1] == 0)) {
						ybis = y;
						xbis = x;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean estFini () {
		if (placement[0][0][0] == 0 || placement[0][0][0] == 1) {
			return true;
		}
		return false;
	}
	
	public static int calcul() {
		int i = 0;
		int clair = 0;
		int J1 = 0;
		//face 1
		while (i<15 || clair<8) {
			for (int l = 0 ; l<5 ; l++) {
				for (int m = 0 ; m<5 ; m++) {
					if (placement[0][l][m] == 0) {
						clair++;
					}
				}
			}
			i++;
		}
		
		if (clair >= 8) {
			J1++;
		}
		
		i = 0;
		clair = 0;
		//face 2
		while (i<15 || clair<8) {
			for (int l = 0 ; l<5 ; l++) {
				for (int m = 0 ; m<5 ; m++) {
					if (placement[l][0][m] == 0) {
						clair++;
					}
				}
			}
			i++;
		}
		
		if (clair >= 8) {
			J1++;
		}
		
		i = 0;
		clair = 0;
		//face 3
		if (J1 < 2) {
			while (i<15 || clair<8) {
				for (int l = 0 ; l<5 ; l++) {
					for (int m = 0 ; m<5 ; m++) {
						if (placement[l][m][0] == 0) {
							clair++;
						}
					}
				}
				i++;
			}
		}
		return J1;
	}
	
	//jouer une partie en donnant des coordonnées x, y et z
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int couleur, x, y, z;
		
		System.out.println("Bienvenu sur le jeu inside !\n");
		System.out.println("Le joueur ayant la couleur clair commence");
		System.out.println("Pour poser une couleur clair tapez 0, pour poser une couleur foncé tapez 1");
		
		while (placement[0][0][0] != 1 || placement[0][0][0] != 0) {
			do {
				System.out.println("Pour quitter tapez 007");
				System.out.println("Joueur 1 : quelle couleur voulez-vous poser ?");
				couleur = sc.nextInt();
				if (couleur == 007) {
					sc.close();
					System.out.println("Partie terminée !");
					return;
				}
				System.out.println("Joueur 1 : Coordonnée x :");
				x = sc.nextInt();
				System.out.println("Joueur 1 : Coordonnée y :");
				y = sc.nextInt();
				System.out.println("Joueur 1 : Coordonnée z :");
				z = sc.nextInt();
			}while (couleur < 0 || couleur > 1 || placementValide(x, y, z, J1[couleur]) == false);
			
			placement [x][y][z] = couleur ;
			J1 [couleur] -= 1;
			
			if (estFini()) {
				int gagne = calcul();
				if (gagne == 2) {
					System.out.println("Le joueur 1 gagne la partie.\nBravo !!!");
					sc.close();
					return;
				}else {
					System.out.println("Le joueur 2 gagne la partie.\nBravo !!!");
				}
			}
			
			if (triangleCouleurForme(x, y, z)) {
				do {
					System.out.println("Pour quitter tapez 007");
					System.out.println("Joueur 1 : quelle couleur voulez-vous poser ?");
					couleur = sc.nextInt();
					if (couleur == 007) {
						sc.close();
						System.out.println("Partie terminée !");
						return;
					}
				}while (couleur < 0 || couleur > 1 || placementValide(xbis, ybis, zbis, J2[couleur]) == false);
				
				placement [xbis][ybis][zbis] = couleur ;
				J2 [couleur] -= 1;
			}
			
			if (estFini()) {
				int gagne = calcul();
				if (gagne == 2) {
					System.out.println("Le joueur 1 gagne la partie.\nBravo !!!");
					sc.close();
					return;
				}else {
					System.out.println("Le joueur 2 gagne la partie.\nBravo !!!");
				}
			}
			
			do {
				System.out.println("Pour quitter tapez 007");
				System.out.println("Joueur 2 : quelle couleur voulez-vous poser ?");
				couleur = sc.nextInt();
				if (couleur == 007) {
					sc.close();
					System.out.println("Partie terminée !");
					return;
				}
				System.out.println("Joueur 2 : Coordonnée x :");
				x = sc.nextInt();
				System.out.println("Joueur 2 : Coordonnée y :");
				y = sc.nextInt();
				System.out.println("Joueur 2 : Coordonnée z :");
				z = sc.nextInt();
			}while (couleur < 0 || couleur > 1 || placementValide(x, y, z, J2[couleur]) == false);
			
			placement [x][y][z] = couleur ;
			J2 [couleur] -= 1;
			
			if (estFini()) {
				int gagne = calcul();
				if (gagne == 2) {
					System.out.println("Le joueur 1 gagne la partie.\nBravo !!!");
					sc.close();
					return;
				}else {
					System.out.println("Le joueur 2 gagne la partie.\nBravo !!!");
				}
			}
			
			if (triangleCouleurForme(x, y, z)) {
				do {
					System.out.println("Pour quitter tapez 007");
					System.out.println("Joueur 2 : quelle couleur voulez-vous poser ?");
					couleur = sc.nextInt();
					if (couleur == 007) {
						sc.close();
						System.out.println("Partie terminée !");
						return;
					}
					System.out.println();
				}while (couleur < 0 || couleur > 1 || placementValide(xbis, ybis, zbis, J2[couleur]) == false);
				
				placement [xbis][ybis][zbis] = couleur ;
				J1 [couleur] -= 1;
			}
			
			if (estFini()) {
				int gagne = calcul();
				if (gagne == 2) {
					System.out.println("Le joueur 1 gagne la partie.\nBravo !!!");
					sc.close();
					return;
				}else {
					System.out.println("Le joueur 2 gagne la partie.\nBravo !!!");
				}
			}
		}
		System.out.println("");
		sc.close();
	}
	
	

}
//TODO Auto-generated constructor stub