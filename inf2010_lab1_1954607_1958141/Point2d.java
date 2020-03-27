package tp1;


public class Point2d extends AbstractPoint {
    private final Integer X = 0;
    private final Integer Y = 1;

    // TODO creer un point en 2d avec 2 donnees
    public Point2d(Double x, Double y) {
        super(new Double[] {x,y});
    }

    // TODO creer un point a partir d'un vecteur de donnees
    public Point2d(Double[] vector) {
        super(vector.clone());
    }
    public Double X() { return vector[X];}
    public Double Y() { return vector[Y];}

    // TODO prendre un vecteur de donnees et appliquer la translation.
    @Override
    public Point2d translate(Double[] translateVector) {
        Point2d newPoint=new Point2d(PointOperator.translate(this.vector.clone(),translateVector));
        return newPoint;
    }

    // TODO prendre un point et appliquer la translation.
    public Point2d translate(Point2d translateVector) {
        Point2d newPoint=new Point2d(PointOperator.translate(this.vector,translateVector.vector));
        return newPoint;
    }

    // TODO prendre un vecteur de donnees et appliquer la translation.
    @Override
    public Point2d rotate(Double[][] rotationMatrix) {
        Point2d newPoint=new Point2d(PointOperator.rotate(this.vector.clone(),rotationMatrix));
        return newPoint;
    }

    // TODO prendre un angle de rotation, creer une matrice et appliquer la rotation.
    public Point2d rotate(Double angle) {
        Double[][] matrix= {
                {Math.cos(angle),(-1*Math.sin(angle))},
                {Math.sin(angle),Math.cos(angle)}};
        return new Point2d(PointOperator.rotate(this.vector,matrix));
    }

    // TODO prendre un facteur de division et l'appliquer.
    @Override
    public Point2d divide(Double divider) {
        Point2d newPoint=new Point2d(PointOperator.divide(this.vector.clone(),divider));
        return newPoint;
    }

    // TODO prendre un facteur de multiplication et l'appliquer.
    @Override
    public Point2d multiply(Double multiplier) {
        Point2d newPoint=new Point2d(PointOperator.multiply(this.vector.clone(),multiplier));
        return newPoint;
    }

    // TODO prendre un facteur d'addition et l'appliquer.
    @Override
    public Point2d add(Double adder) {
        Point2d newPoint=new Point2d(PointOperator.add(this.vector.clone(),adder));
        return newPoint;
    }

    // TODO creer un nouveau point.
    @Override
    public Point2d clone() {

       return new Point2d(vector);

    }
}