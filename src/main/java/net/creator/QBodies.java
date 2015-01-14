package net.creator;

public class QBodies {



	public class QStar{
		private String name, desc;
		private double posx, posy;
		public QStar(String name, String desc, double x, double y){
			this.name = name;
			this.desc = desc;
			posx = x;
			posy = y;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public double getPosx() {
			return posx;
		}
		public void setPosx(double posx) {
			this.posx = posx;
		}
		public double getPosy() {
			return posy;
		}
		public void setPosy(double posy) {
			this.posy = posy;
		}
	}
	public class QPlanet{
		private String name, desc;
		private double x, y;
		private boolean anti;
		public  QPlanet(String name, String desc, double x, double y, double speed, boolean anti){
			this.name = name;
			this.desc = desc;
			this.x = x;
			this.y = y;
			this.anti = anti;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}
		public boolean isAnti() {
			return anti;
		}
		public void setAnti(boolean anti) {
			this.anti = anti;
		}
	}

}
