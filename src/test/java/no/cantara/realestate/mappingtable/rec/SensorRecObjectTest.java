package no.cantara.realestate.mappingtable.rec;

import no.cantara.realestate.mappingtable.tfm.Tfm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SensorRecObjectTest {

    private SensorRecObject recObject;
    private final Tfm tfm = new Tfm("TFM-RY02101");

    @BeforeEach
    void setUp() {
        recObject = new SensorRecObject("recid");
        recObject.setTfm(tfm);
    }

    @Test
    void verifyIdAndTFM() {
        assertEquals("recid", recObject.getRecId());
        assertEquals(tfm,recObject.getTfm());
        assertFalse(tfm.getTfm().equals(recObject.getTfm()));
        assertEquals(tfm.getTfm(),recObject.getTfm().getTfm());
    }

    @Test
    void verifyGetProperty() {
        recObject.setRealEstate("re1");
        assertEquals("recid", recObject.getRecId());
        assertEquals("recid", recObject.getProperty("recId"));
        assertEquals("re1", recObject.getProperty("realEstate"));
    }
}