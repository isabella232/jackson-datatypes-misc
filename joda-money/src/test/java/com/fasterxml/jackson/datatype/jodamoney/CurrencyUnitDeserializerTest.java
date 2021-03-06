package com.fasterxml.jackson.datatype.jodamoney;

import java.io.IOException;

import org.joda.money.CurrencyUnit;
import org.joda.money.IllegalCurrencyException;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class CurrencyUnitDeserializerTest extends ModuleTestBase
{
    private final ObjectMapper MAPPER = mapperWithModule();

    public void testShouldDeserialize() throws IOException {
        assertEquals(CurrencyUnit.EUR,
                MAPPER.readValue("\"EUR\"", CurrencyUnit.class));
    }

    public void testShouldNotDeserializeInvalidCurrency() {
        try {
            MAPPER.readValue("\"UNKNOWN\"", CurrencyUnit.class);
            fail("Should not pass");
        } catch (final IllegalCurrencyException e) {
            verifyException(e, "Unknown currency 'UNKNOWN'");
        } catch (final IOException e) {
            fail("IllegalCurrencyException should have been thrown");
        }
    }
}
