package factory;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestGroundControlsCenterFactory {
    private Object componentPort;

    @Test
    public void build() {
        componentPort = GroundOperationsCenterFactory.build();
        assertNotNull(componentPort);
    }
}
