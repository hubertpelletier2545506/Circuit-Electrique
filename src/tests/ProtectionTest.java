package tests;

import composants.Protection;
import enums.TypeProtection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static composants.Protection.AMPERAGE_DEFAUT;
import static org.junit.jupiter.api.Assertions.*;

class ProtectionTest {
    private Protection fusible1, disj1;

    @BeforeEach
    void setUp() {
        fusible1 = new Protection(10, TypeProtection.FUSIBLE);
        disj1 = new Protection(20, TypeProtection.DISJONCTEUR);
    }

    @Test
    void setAmperageMax() {
        fusible1.setAmperageMax(5);

        int nouvelAmpMax = fusible1.getAmperageMax();
        int reponseAttendueAmp = 5;

        assertEquals(reponseAttendueAmp, nouvelAmpMax);


    }

    @Test
    void setAmperageMaxErreur() {
        fusible1.setAmperageMax(-1);

        int nouvelAmpMax2 = fusible1.getAmperageMax();
        int reponseAttendueAmp2 = AMPERAGE_DEFAUT;

        assertEquals(reponseAttendueAmp2, nouvelAmpMax2);

    }

    @Test
    void getAmperageMax() {
        int attendu = 10;
        int actual = fusible1.getAmperageMax();

        assertEquals(attendu, actual);
    }

    @Test
    void setTypeProtection() {
        fusible1.setTypeProtection(TypeProtection.DISJONCTEUR);

        TypeProtection expected=TypeProtection.DISJONCTEUR;
        TypeProtection actual=fusible1.getTypeProtection();

        assertEquals(expected,actual);
    }

    @Test
    void setTypeProtectionInvalide(){
        fusible1.setTypeProtection(null);

        TypeProtection expected=TypeProtection.DISJONCTEUR;
        TypeProtection actual=fusible1.getTypeProtection();

        assertEquals(expected,actual);
    }

    @Test
    void getTypeProtection() {
        TypeProtection actual=disj1.getTypeProtection();
        TypeProtection expected=TypeProtection.DISJONCTEUR;

        assertEquals(expected,actual);
    }
}