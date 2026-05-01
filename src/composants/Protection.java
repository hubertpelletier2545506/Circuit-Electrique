package composants;

public class Protection {

    private int amperageMax;

    private final static int AMPERAGE_DEFAUT = 0;

    public Protection (int amperageMax){

        setAmperageMax(amperageMax);

    }

    public void setAmperageMax (int amperageMax) {

        if (amperageMax >= 0) {
            this.amperageMax = amperageMax;
        } else {

            System.out.println("Une protection ne peut pas avoir un ampérage maximal. Nouvelle valeur de l'ampérage maximal: 0");
            setAmperageMax(0);
        }
    }

}
