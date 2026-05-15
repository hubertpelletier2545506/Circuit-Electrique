package tests;

import composants.Protection;
import enums.TypeProtection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static composants.Protection.AMPERAGE_DEFAUT;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour la classe {@link Protection}.
 * <p>
 * Cette classe vérifie le bon fonctionnement des méthodes de gestion
 * de l'ampérage maximal ainsi que du type de protection.
 * </p>
 *
 * Les tests sont réalisés à l'aide du framework JUnit 5.
 *
 * @author code Hubert Pelletier
 * @author javadoc chatGPT par Maxime Filion
 */
class ProtectionTest {

    /**
     * Objet représentant un fusible utilisé dans les tests.
     */
    private Protection fusible1;

    /**
     * Objet représentant un disjoncteur utilisé dans les tests.
     */
    private Protection disj1;

    /**
     * Initialise les objets nécessaires avant chaque test.
     * <p>
     * Un fusible de 10 ampères et un disjoncteur de 20 ampères
     * sont créés pour les différents scénarios de test.
     * </p>
     */
    @BeforeEach
    void setUp() {
        fusible1 = new Protection(10, TypeProtection.FUSIBLE);
        disj1 = new Protection(20, TypeProtection.DISJONCTEUR);
    }

    /**
     * Vérifie que la méthode {@code setAmperageMax}
     * modifie correctement l'ampérage maximal
     * lorsqu'une valeur valide est fournie.
     */
    @Test
    void setAmperageMax() {
        fusible1.setAmperageMax(5);

        int nouvelAmpMax = fusible1.getAmperageMax();
        int reponseAttendueAmp = 5;

        assertEquals(reponseAttendueAmp, nouvelAmpMax);
    }

    /**
     * Vérifie que la méthode {@code setAmperageMax}
     * attribue la valeur par défaut lorsque
     * l'ampérage fourni est invalide.
     */
    @Test
    void setAmperageMaxErreur() {
        fusible1.setAmperageMax(-1);

        int nouvelAmpMax2 = fusible1.getAmperageMax();
        int reponseAttendueAmp2 = AMPERAGE_DEFAUT;

        assertEquals(reponseAttendueAmp2, nouvelAmpMax2);
    }

    /**
     * Vérifie que la méthode {@code getAmperageMax}
     * retourne correctement l'ampérage maximal.
     */
    @Test
    void getAmperageMax() {
        int attendu = 10;
        int actual = fusible1.getAmperageMax();

        assertEquals(attendu, actual);
    }

    /**
     * Vérifie que la méthode {@code setTypeProtection}
     * modifie correctement le type de protection
     * lorsqu'une valeur valide est fournie.
     */
    @Test
    void setTypeProtection() {
        fusible1.setTypeProtection(TypeProtection.DISJONCTEUR);

        TypeProtection expected = TypeProtection.DISJONCTEUR;
        TypeProtection actual = fusible1.getTypeProtection();

        assertEquals(expected, actual);
    }

    /**
     * Vérifie que la méthode {@code setTypeProtection}
     * conserve la valeur précédente lorsqu'une valeur
     * invalide ({@code null}) est fournie.
     */
    @Test
    void setTypeProtectionInvalide() {
        fusible1.setTypeProtection(null);

        TypeProtection expected = TypeProtection.DISJONCTEUR;
        TypeProtection actual = fusible1.getTypeProtection();

        assertEquals(expected, actual);
    }

    /**
     * Vérifie que la méthode {@code getTypeProtection}
     * retourne correctement le type de protection.
     */
    @Test
    void getTypeProtection() {
        TypeProtection actual = disj1.getTypeProtection();
        TypeProtection expected = TypeProtection.DISJONCTEUR;

        assertEquals(expected, actual);
    }
}