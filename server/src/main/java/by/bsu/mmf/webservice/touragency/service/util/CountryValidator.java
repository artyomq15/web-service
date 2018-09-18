package by.bsu.mmf.webservice.touragency.service.util;

import by.bsu.mmf.webservice.touragency.domain.Country;

import java.util.regex.Pattern;

public class CountryValidator extends Validator{

    private static final Pattern COUNTRY_NAME = Pattern.compile("^(?:[A-Z][a-zA-Z\\s?]+)(?:-\\s?[A-Z][a-zA-Z\\s?]+)*$");

    public boolean isValidName(String name) {
        return name != null && !name.equals(EMPTY) && COUNTRY_NAME.matcher(name).matches();
    }

    public boolean isValidCountry(Country country) {
        return country != null && isValidId(country.getId()) && isValidName(country.getName());
    }


}
