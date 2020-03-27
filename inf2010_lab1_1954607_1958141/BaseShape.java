package tp1;

import java.util.*;
import java.util.stream.Collectors;

public class BaseShape {
    // Vous aller apprendre plus en details le fonctionnement d'un Set lors
    // du cours sur les fonctions de hashage, vous pouvez considerez ceci comme une liste normale.
    private Set<Point2d> coords;

    // TODO Initialiser les points.
    public BaseShape() {
        this.coords=new HashSet<Point2d>();
    }

    // TODO prendre une liste de points et creer une nouvelle forme.
    public BaseShape(Collection<Point2d> coords) {
        this.coords=new HashSet<Point2d>();
        this.coords.addAll(coords);
    }

    // TODO ajouter ou retirer des coordonnees a la liste de points.
    public void add(Point2d coord) {
        this.coords.add(coord);
    }

    public void add(BaseShape shape) {
      this.coords.addAll(shape.getCoords());
    }
    public void addAll(Collection<Point2d> coords) {
        this.coords.addAll(coords);
    }
    public void remove(Point2d coord) {
        this.coords.remove(coord);
    }
    public void remove(BaseShape shape) {
        this.coords.removeAll(shape.getCoords());
    }
    public void removeAll(Collection<Point2d> coords) {
        this.coords.removeAll(coords);
    }

    // TODO retourne les coordonnees de la liste.
    public Set<Point2d> getCoords() {
        Set<Point2d> newSet=new HashSet<>(this.coords);
        return newSet;
    }

    // TODO appliquer la translation sur la forme.
    public BaseShape translate(Point2d point) {
        Set<Point2d> newSet = new HashSet<>();
        for(Point2d it1:this.coords) {
            newSet.add(it1.translate(point));
        }
        return this;
    }

    // TODO appliquer la translation sur la liste.
    public Set<Point2d> translateAll(Point2d point) {
        for(Point2d it1:this.coords) {
            it1.translate(point);
        }
        return this.getCoords();
    }

    // TODO appliquer la rotation sur la forme.
    public BaseShape rotate(Double angle) {
     Iterator<Point2d> it = coords.iterator();
      BaseShape newShape = new BaseShape();
      while(it.hasNext()){
          newShape.add(it.next().rotate(angle));
      }
      return newShape;
    }

    // TODO appliquer la rotation sur la liste.
    public Set<Point2d> rotateAll(Double angle) {
        Iterator<Point2d> it = coords.iterator();
        BaseShape newShape = new BaseShape();
        while(it.hasNext()){
            newShape.add(it.next().rotate(angle));
        }
        return newShape.getCoords();
    }

    // TODO retourner une nouvelle forme.
    public BaseShape clone() {
        return new BaseShape(this.coords);
    }
}
