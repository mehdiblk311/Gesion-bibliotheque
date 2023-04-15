package JUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Modele.Exemplaire;
import Modele.Oeuvre;
import Utility.BibalExceptions;
import control.ExemplaireControl;

public class ExemplaireControlTest {

    private Oeuvre oeuvre;

    @Before
    public void setUp() throws BibalExceptions {
        oeuvre = new Oeuvre();
        oeuvre.setTitre("titre");
        oeuvre.setAuteur("auteur");
        oeuvre.setCategorie("categorie");
        oeuvre.setNbResa(0);
    }

    @Test
    public void testAjouter() {
        try {
            ExemplaireControl.ajouter(oeuvre, "etat");
            ArrayList<Exemplaire> exemplaires = ExemplaireControl.find(oeuvre);
            assertNotNull(exemplaires);
            assertEquals(1, exemplaires.size());
            assertEquals("etat", exemplaires.get(0).getEtat());
        } catch (BibalExceptions e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    public void testModifier() {
        try {
            ExemplaireControl.ajouter(oeuvre, "etat");
            ArrayList<Exemplaire> exemplaires = ExemplaireControl.find(oeuvre);
            Exemplaire exemplaire = exemplaires.get(0);
            exemplaire.setEtat("newEtat");
            ExemplaireControl.modifier(exemplaire);
            exemplaires = ExemplaireControl.find(oeuvre);
            assertNotNull(exemplaires);
            assertEquals(1, exemplaires.size());
            assertEquals("newEtat", exemplaires.get(0).getEtat());
        } catch (BibalExceptions e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    public void testSupprimer() {
        try {
            ExemplaireControl.ajouter(oeuvre, "etat");
            ArrayList<Exemplaire> exemplaires = ExemplaireControl.find(oeuvre);
            Exemplaire exemplaire = exemplaires.get(0);
            ExemplaireControl.supprimer(exemplaire);
            exemplaires = ExemplaireControl.find(oeuvre);
            assertNotNull(exemplaires);
            assertTrue(exemplaires.isEmpty());
        } catch (BibalExceptions e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    public void testFindById() {
        try {
            ExemplaireControl.ajouter(oeuvre, "etat");
            ArrayList<Exemplaire> exemplaires = ExemplaireControl.find(oeuvre);
            Exemplaire exemplaire = ExemplaireControl.findById(exemplaires.get(0).getId());
            assertNotNull(exemplaire);
            assertEquals("etat", exemplaire.getEtat());
        } catch (BibalExceptions e) {
            fail("Exception not expected: " + e.getMessage());
        }
    }

    @Test
    public void testFindExemplaireDispo() {
        try {
        	ExemplaireControl.ajouter(oeuvre, "etat");
            ArrayList<Exemplaire> exemplairesDispo = ExemplaireControl.findExemplaireDispo(oeuvre);
            assertNotNull(exemplairesDispo);
            assertEquals(1, exemplairesDispo.size());
            assertEquals("etat", exemplairesDispo.get(0).getEtat());
		} catch (BibalExceptions e) {
			fail("Exception not expected: " + e.getMessage());
			}
            
    }       
}
    
