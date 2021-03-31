package tp1;

import java.util.Set;

public class Rectangle extends BaseShape {
    // TODO creer un rectangle avec une largeur et une longueur.
    public Rectangle(Double width, Double height) {
        super();
        for(double i = -width/2; i <= width/2;i++)
        {
            for(double j = -height/2; j <= height/2; j++) {
                this.add(new Point2d(i,j));
            }
        }
    }

    // TODO creer un rectangle avec un point contenant la largeur et longueur.
    public Rectangle(Point2d dimensions) {
        this(dimensions.X(),dimensions.Y());
    }

    private Rectangle(Set<Point2d> coords) {
        super(coords);
    }

    // TODO appliquer la translation sur la forme.
    @Override
    public Rectangle translate(Point2d point) {
        Rectangle newRectangle=new Rectangle(this.translateAll(point));
        return newRectangle;
    }

    // TODO appliquer la rotation sur la forme.
    @Override
    public Rectangle rotate(Double angle) {
        Rectangle newRectangle=new Rectangle(this.rotateAll(angle));
        return newRectangle;
    }

    // TODO retourner une nouvelle forme.
    @Override
    public Rectangle clone() {
        return new Rectangle(super.getCoords());
    }
}
