package by.bsu.mmf.webservice.touragency.service.util;

public abstract class Validator {
    protected static final String EMPTY = "";

    public boolean isValidId(Long id) {
        return id != null && id > 0;
    }

}
