public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double grav_constant = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double num = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos);
		return Math.sqrt(num);
	}

	public double calcForceExertedBy(Planet p){
		double distance = this.calcDistance(p);
		return Planet.grav_constant * this.mass * p.mass / (distance * distance);
	}

	public double calcForceExertedByX(Planet p){
		return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
	}

	public double calcForceExertedByY(Planet p){
		return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] p){
		double sum_x = 0.0;
		for(int i = 0; i < p.length; i += 1){
			if(this.equals(p[i])){
				continue;
			}else{
				sum_x += this.calcForceExertedByX(p[i]);
			}
		}
		return sum_x;
	}

	public double calcNetForceExertedByY(Planet[] p){
		double sum_y = 0;
		/** for(Planet p_i : p) */
		for(int i = 0; i < p.length; i += 1){
			if(this.equals(p[i])){
				continue;
			}else{
				sum_y += this.calcForceExertedByY(p[i]);
			}
		}
		return sum_y;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel += aX * dt;
		this.yyVel += aY * dt;
		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;
		return ;
	}

	public void draw(){
		String full_string = "images/" + this.imgFileName;
		StdDraw.picture(xxPos, yyPos, full_string);
	}

}