package composants;

import enums.TypeProtection;

/**
 * Représente un dispositif de protection électrique.
 * <p>
 * Une protection est définie par :
 * <ul>
 *     <li>un ampérage maximal</li>
 *     <li>un type de protection</li>
 * </ul>
 * </p>
 *
 * @author code LudovikLavoie
 * @author javadoc ChatGPT par Maxime Filion
 */
public class Protection {

    /** Ampérage maximal supporté par la protection. */
    private int amperageMax;

    /** Type de protection (ex : fusible, disjoncteur). */
    private TypeProtection typeProtection;

    /** Valeur par défaut de l'ampérage maximal. */
    public final static int AMPERAGE_DEFAUT = 0;

    /** Type de protection par défaut. */
    private final static TypeProtection TYPE_PROTECTION_DEFAUT = TypeProtection.DISJONCTEUR;

    /**
     * Constructeur de la classe Protection.
     *
     * @param amperageMax l'ampérage maximal de la protection
     * @param typeProtection le type de protection
     */
    public Protection (int amperageMax, TypeProtection typeProtection){
        setAmperageMax(amperageMax);
        setTypeProtection(typeProtection);
    }

    /**
     * Définit l'ampérage maximal de la protection.
     * <p>
     * Si la valeur fournie est négative, l'ampérage est automatiquement
     * remplacé par la valeur par défaut ({@value #AMPERAGE_DEFAUT}).
     * </p>
     *
     * @param amperageMax l'ampérage maximal à définir
     */
    public void setAmperageMax (int amperageMax) {

        if (amperageMax >= 0) {
            this.amperageMax = amperageMax;
        } else {

            System.out.println("Une protection ne peut pas avoir un ampérage maximal néagtif. Nouvelle valeur de l'ampérage maximal: " + AMPERAGE_DEFAUT + "A");
            setAmperageMax(AMPERAGE_DEFAUT);
        }
    }

    /**
     * Retourne l'ampérage maximal de la protection.
     *
     * @return l'ampérage maximal
     */
    public int getAmperageMax(){
        return amperageMax;
    }

    /**
     * Définit le type de protection.
     * <p>
     * Si le type de protection est null, le type de protection est automatiquement
     * remplacé par le type par défaut TypeProtection.DISJONCTEUR
     * <p>
     *
     * @param typeProtection le type de protection à définir
     */
    public void setTypeProtection (TypeProtection typeProtection){

        if(typeProtection != null){
            this.typeProtection = typeProtection;

        } else{
            this.typeProtection = TYPE_PROTECTION_DEFAUT;
        }
    }

    /**
     * Retourne le type de protection.
     *
     * @return le type de protection
     */
    public TypeProtection getTypeProtection(){
        return typeProtection;
    }

}