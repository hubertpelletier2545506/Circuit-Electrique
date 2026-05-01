package composants;

import enums.TypeProtection;

public class Protection {

    private int amperageMax;
    private TypeProtection typeProtection;

    private final static int AMPERAGE_DEFAUT = 0;

    public Protection (int amperageMax, TypeProtection typeProtection){

        setAmperageMax(amperageMax);
        setTypeProtection(typeProtection);

    }

    public void setAmperageMax (int amperageMax) {

        if (amperageMax >= 0) {
            this.amperageMax = amperageMax;
        } else {

            System.out.println("Une protection ne peut pas avoir un ampérage maximal néagtif. Nouvelle valeur de l'ampérage maximal: 0");
            setAmperageMax(AMPERAGE_DEFAUT);
        }
    }

    public int getAmperageMax(){
        return amperageMax;
    }

    public void setTypeProtection (TypeProtection typeProtection){
        this.typeProtection = typeProtection
    }

    public TypeProtection getTypeProtection(){
        return typeProtection;
    }

}
