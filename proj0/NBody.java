public class NBody{
    public static double readRadius(String s){
        In in = new In(s);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Planet[] readPlanets(String s){
        In in = new In(s);
        int len_of_array = in.readInt();
        /** skip the radius of universe*/
        in.readDouble();
        Planet[] p = new Planet[len_of_array];
        for(int i = 0; i < len_of_array; i += 1){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            p[i] = new Planet(xP, yP, xV, yV, mass, img);
        }
        return p;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for(Planet current: planets){
            current.draw();
        }
        StdDraw.enableDoubleBuffering();
        for(double i = 0.0; i < T; i += dt){
            StdAudio.play("audio/2001.mid");
            double[] xForces = new double[planets.length], yForces = new double[planets.length];
            for(int tmp = 0; tmp < planets.length; tmp += 1){
                xForces[tmp] = planets[tmp].calcNetForceExertedByX(planets);
                yForces[tmp] = planets[tmp].calcNetForceExertedByY(planets);
            }
            for(int tmp = 0; tmp < planets.length; tmp += 1){
                planets[tmp].update(dt, xForces[tmp], yForces[tmp]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet current : planets){
                current.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

}