package tp1;

import java.util.Set;

public class Ellipse extends BaseShape {
    // TODO creer une ellipse avec une largeur et une longueur.
    public Ellipse(Double widthRadius, Double heightRadius) {
        super();

        int intWidthRadius = widthRadius.intValue();
        int intHeightRadius = heightRadius.intValue();

        for(int i = 0; i < intWidthRadius; i++)
        {
            this.add(new Point2d((-(widthRadius/2)+i),
                    Math.sqrt(((1-(Math.pow((-widthRadius/2+i),2)/(Math.pow(widthRadius/2, 2))))*Math.pow(heightRadius/2, 2)))));

            this.add(new Point2d((-(widthRadius/2)+i),
                    -Math.sqrt(((1-(Math.pow((-widthRadius/2+i),2)/(Math.pow(widthRadius/2, 2))))*Math.pow(heightRadius/2, 2)))));
        }

        for(int i = 0; i < intHeightRadius; i++)
        {
            this.add(new Point2d((Math.sqrt(((1-(Math.pow((-heightRadius/2+i),2)/(Math.pow(heightRadius/2, 2))))*Math.pow(widthRadius/2, 2)))),
                    -(heightRadius/2)+i));

            this.add(new Point2d((-Math.sqrt(((1-(Math.pow((-heightRadius/2+i),2)/(Math.pow(heightRadius/2, 2))))*Math.pow(widthRadius/2, 2)))),
                    -(heightRadius/2)+i));
        }
    }

    private Ellipse(Set<Point2d> coords) {
        super(coords);
    }

    // TODO appliquer la translation sur la forme.
    @Override
    public Ellipse translate(Point2d point) {
        return new Ellipse(translateAll(point));
    }

    // TODO appliquer la rotation sur la forme.
    @Override
    public Ellipse rotate(Double angle) {
        return new Ellipse(rotateAll(angle));
    }

    // TODO retourner une nouvelle forme.
    @Override
    public Ellipse clone() { return new Ellipse(getCoords()); }
}

