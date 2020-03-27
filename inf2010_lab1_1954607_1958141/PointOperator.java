package tp1;

import java.util.*;

public final class PointOperator {
    // TODO appliquer la translation sur le vecteur d'entree.
    public static Double[] translate(Double[] vector, Double[] translateVector) {
        for(int i=0;i<vector.length;i++)
        {
            vector[i]+=translateVector[i];
        }
        return vector;
    }

    // TODO appliquer la rotation sur le vecteur d'entree.
    public static Double[] rotate(Double[] vector, Double[][] rotationMatrix) {
        Double[] newVector=new Double[vector.length];
        for(int i=0;i<vector.length;i++) {
            double result=0;
            for(int j=0;j<vector.length;j++) {
                result+=rotationMatrix[i][j]*vector[j];
            }
            newVector[i]=result;
        }
       return newVector;
    }

    // TODO appliquer le facteur de division sur le vecteur d'entree.
    public static Double[] divide(Double[] vector, Double divider) {
        for(int i=0;i<vector.length;i++) {
            vector[i]/=divider;
        }
        return vector;
    }

    // TODO appliquer le facteur de multiplication sur le vecteur d'entree.
    public static Double[] multiply(Double[] vector, Double multiplier) {
        for(int i=0;i<vector.length;i++) {
            vector[i]*=multiplier;
        }
        return vector;
    }

    // TODO appliquer le facteur d'addition sur le vecteur d'entree.
    public static Double[] add(Double[] vector, Double adder) {
        for(int i=0;i<vector.length;i++) {
            vector[i]+=adder;
        }
        return vector;
    }

    // TODO retourne la coordonnee avec les plus grandes valeurs en X et en Y.
    public static Point2d getMaxCoord(Collection<Point2d> coords) {
        Double maximumX= Double.MIN_VALUE;
        Double maximumY=Double.MIN_VALUE;
        for(Point2d point:coords) {
            if(point.X()>maximumX) {
                maximumX=point.X();
            }
            if(point.Y()>maximumY) {
                maximumY=point.Y();
            }
        }
        return new Point2d(maximumX,maximumY);
    }

    // TODO retourne la coordonnee avec les plus petites valeurs en X et en Y.
    public static Point2d getMinCoord(Collection<Point2d> coords) {
        Double minimumX=Double.MAX_VALUE;
        Double minimumY=Double.MAX_VALUE;
        for(Point2d point:coords) {
            if(point.X()<minimumX) {
                minimumX=point.X();
            }
            if(point.Y()<minimumY) {
                minimumY=point.Y();
            }
        }
        return new Point2d(minimumX,minimumY);
    }
}
