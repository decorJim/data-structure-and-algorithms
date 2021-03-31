package tp1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class LetterFactory {
    final static Double maxHeight = 200.0;
    final static Double maxWidth = maxHeight / 2;
    final static Double halfMaxHeight = maxHeight / 2;
    final static Double halfMaxWidth = maxWidth / 2;
    final static Double stripeThickness = maxHeight / 10;

    // TODO
    public static BaseShape create_H() {

        BaseShape hShape=new BaseShape();
        Rectangle leftSide=new Rectangle(maxWidth/4,maxHeight).translate(new Point2d(maxWidth/2,0.0));
        Rectangle middle=new Rectangle(maxWidth,maxWidth/4);
        Rectangle rightSide=new Rectangle(maxWidth/4,maxHeight).translate(new Point2d(-maxWidth/2,0.0));
        hShape.add(leftSide);
        hShape.add(middle);
        hShape.add(rightSide);
        return hShape;
    }

    // TODO
    public static BaseShape create_e() {

        Double spacing = maxWidth/2;
        BaseShape middle = new Rectangle(maxWidth, stripeThickness);
        BaseShape curveForm = new Ellipse(maxWidth, maxHeight);

        for(int i = 0; i < 2*stripeThickness; i++)
        {
            middle.add(new Ellipse(maxWidth-i,maxHeight-i));
        }
        BaseShape toErase = new Rectangle(maxWidth, stripeThickness*2).translate(new Point2d(spacing,stripeThickness*3/2));
        curveForm.add(middle);
        curveForm.remove(toErase);
        return curveForm;
    }

    public static BaseShape create_l() {
        BaseShape lShape = new Rectangle(stripeThickness, maxHeight);
        return lShape;
    }

    // TODO
    public static BaseShape create_o() {
        BaseShape oShape = new Ellipse(maxWidth, maxHeight);
        for(int i = 0; i < stripeThickness*2; i++)
        {
            oShape.add(new Ellipse(maxWidth - i, maxHeight - i));
        }
        return oShape;
    }

    // On vous donne la lettre W comme exemple.
    public static BaseShape create_W() {

        Double degrees15 = Math.toRadians(8);
        Double spacing = stripeThickness * 2;
        BaseShape mainStripe = new Rectangle(stripeThickness, maxHeight);
        BaseShape leftStripe = mainStripe.rotate(-degrees15).translate(new Point2d(-spacing, 0.0));
        BaseShape middleLeftStripe = mainStripe.rotate(degrees15).translate(new Point2d(-spacing / 3, 0.0));
        BaseShape middleRightStripe = mainStripe.rotate(-degrees15).translate(new Point2d(spacing / 3, 0.0));
        BaseShape rightStripe = mainStripe.rotate(degrees15).translate(new Point2d(spacing, 0.0));
        leftStripe.add(middleLeftStripe);
        leftStripe.add(middleRightStripe);
        leftStripe.add(rightStripe);
        return leftStripe;
    }

    // TODO
    public static BaseShape create_r() {

        Double spacing = maxWidth/2;
        Double ajustement = stripeThickness/2;
        BaseShape rShape = new Rectangle(stripeThickness, maxHeight).translate(new Point2d(-spacing+stripeThickness/2,0.0));
        BaseShape ellipse = new BaseShape();
        for(int i = 0; i <= stripeThickness*2; i++)
        {
            ellipse.add(new Ellipse(maxWidth-i,maxWidth-i).translate(new Point2d(0.0,-spacing+ajustement)));
        }
        Rectangle toErase = new Rectangle(maxWidth,maxWidth).translate(new Point2d(stripeThickness,ajustement));
        ellipse.remove(toErase);
        rShape.add(ellipse);
        return rShape;
    }

    // TODO
    public static BaseShape create_d() {

        Double spacing = (maxWidth/2);
        BaseShape dShape = new Rectangle(stripeThickness, maxHeight).translate(new Point2d(spacing-stripeThickness/2,0.0));
        for(int i = 0; i < 2*stripeThickness; i++) {
            dShape.add(new Ellipse(maxWidth-i,maxWidth-i).translate(new Point2d(0.0,spacing)));
        }
        return dShape;
    }
}
