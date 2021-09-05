package ru.stqa.pft.soap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {
    @Test
    public void testMyIp() throws JsonProcessingException {
        GeoIp location = parseGeoIp(new GeoIPService().getGeoIPServiceSoap12().getIpLocation("5.128.106.135"));
        Assert.assertEquals(location.getCountry(), "RU");
    }

    @Test
    public void testInvalidIp() throws JsonProcessingException {
        GeoIp location = parseGeoIp(new GeoIPService().getGeoIPServiceSoap12().getIpLocation("5.128.106.xxx"));
        Assert.assertEquals(location.getCountry(), "US");
    }

    public GeoIp parseGeoIp(String xml) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(xml, GeoIp.class);
    }
}
