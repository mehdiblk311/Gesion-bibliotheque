package JUnitTests;

import org.junit.Before;
import org.junit.Test;

import Modele.*;
import Utility.*;

import static org.junit.Assert.*;

import java.util.Date;

public class EmpruntTest {
    
    private Emprunt emprunt;
    private Usager usager;
    private Oeuvre oeuvre;
    private Exemplaire exemplaire;

    @Before
    public void setUp() throws Exception {
        emprunt = new Emprunt();
        usager = new Usager();
        oeuvre = new Livre();
        exemplaire = new Exemplaire();
    }

    @Test
    public void testSetIdWithInvalidId() {
        try {
            emprunt.setId(-1);
            fail("Expected BibalExceptions to be thrown");
        } catch (BibalExceptions e) {
            assertEquals("Identifiant Oeuvre non valide !", e.getMessage());
        }
    }

    @Test
    public void testSetDateEmpruntWithNullDate() {
        try {
            emprunt.setDateEmprunt(null);
            fail("Expected BibalExceptions to be thrown");
        } catch (BibalExceptions e) {
            assertEquals("Veuillez renseigner la date d'emprunt", e.getMessage());
        }
    }

    @Test
    public void testSetDateRetourPrevuWithInvalidDate() {
        Date dateEmprunt = new Date();
        Date dateRetourPrevu = new Date(dateEmprunt.getTime() - 86400000);
        try {
            emprunt.setDateEmprunt(dateEmprunt);
            emprunt.setDateRetourPrevu(dateRetourPrevu);
            fail("Expected BibalExceptions to be thrown");
        } catch (BibalExceptions e) {
            assertEquals("La date de retour prévu ne doit pas être avant la date d'emprunt", e.getMessage());
        }
    }

    @Test
    public void testSetDateRetourEffectiveWithInvalidDate() {
        Date dateEmprunt = new Date();
        Date dateRetourEffective = new Date(dateEmprunt.getTime() - 86400000);
        try {
            emprunt.setDateEmprunt(dateEmprunt);
            emprunt.setDateRetourEffective(dateRetourEffective);
            fail("Expected BibalExceptions to be thrown");
        } catch (BibalExceptions e) {
            assertEquals("La date de retour effective ne doit pas être avant la date d'emprunt", e.getMessage());
        }
    }

    @Test
    public void testEmprunterWithExistingEmprunt() {
        Date dateEmprunt = new Date();
        Date dateRetourPrevu = new Date(dateEmprunt.getTime() + 86400000);
        try {
			emprunt.setDateEmprunt(dateEmprunt);
			emprunt.setDateRetourPrevu(dateRetourPrevu);
		} catch (BibalExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        try {
            emprunt.emprunter(usager, oeuvre, exemplaire, dateEmprunt);
            fail("Expected BibalExceptions to be thrown");
        } catch (BibalExceptions e) {
            assertEquals("Vous avez un emprunt d'exemplaire de cette oeuvre non rendu\n" +
                    "Titre Oeuvre : null\n" +
                    "Date d'emprunt : " + dateEmprunt.toString() + "\n" +
                    "Date de retour Prévu : " + dateRetourPrevu.toString(), e.getMessage());
        }
    }
}
