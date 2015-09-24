// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.web;

import org.m4.bgw.Boardgame;
import org.m4.bgw.Country;
import org.m4.bgw.Language;
import org.m4.bgw.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<Boardgame, String> ApplicationConversionServiceFactoryBean.getBoardgameToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.Boardgame, java.lang.String>() {
            public String convert(Boardgame boardgame) {
                return new StringBuilder().append(boardgame.getName()).append(' ').append(boardgame.getPublicationYear()).toString();
            }
        };
    }
    
    public Converter<Integer, Boardgame> ApplicationConversionServiceFactoryBean.getIdToBoardgameConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Integer, org.m4.bgw.Boardgame>() {
            public org.m4.bgw.Boardgame convert(java.lang.Integer id) {
                return Boardgame.findBoardgame(id);
            }
        };
    }
    
    public Converter<String, Boardgame> ApplicationConversionServiceFactoryBean.getStringToBoardgameConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.Boardgame>() {
            public org.m4.bgw.Boardgame convert(String id) {
                return getObject().convert(getObject().convert(id, Integer.class), Boardgame.class);
            }
        };
    }
    
    public Converter<Country, String> ApplicationConversionServiceFactoryBean.getCountryToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.Country, java.lang.String>() {
            public String convert(Country country) {
                return new StringBuilder().append(country.getIsoAlpha3()).append(' ').append(country.getName()).toString();
            }
        };
    }
    
    public Converter<String, Country> ApplicationConversionServiceFactoryBean.getIdToCountryConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.Country>() {
            public org.m4.bgw.Country convert(java.lang.String id) {
                return Country.findCountry(id);
            }
        };
    }
    
    public Converter<Language, String> ApplicationConversionServiceFactoryBean.getLanguageToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<org.m4.bgw.Language, java.lang.String>() {
            public String convert(Language language) {
                return new StringBuilder().append(language.getName()).toString();
            }
        };
    }
    
    public Converter<String, Language> ApplicationConversionServiceFactoryBean.getIdToLanguageConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, org.m4.bgw.Language>() {
            public org.m4.bgw.Language convert(java.lang.String id) {
                return Language.findLanguage(id);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getBoardgameToStringConverter());
        registry.addConverter(getIdToBoardgameConverter());
        registry.addConverter(getStringToBoardgameConverter());
        registry.addConverter(getCountryToStringConverter());
        registry.addConverter(getIdToCountryConverter());
        registry.addConverter(getLanguageToStringConverter());
        registry.addConverter(getIdToLanguageConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
